const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');
const oracledb = require('oracledb');
const nodemailer = require('nodemailer');
const db = require('../config/db');

async function login(req, res) {
  const { email, password } = req.body;
  const connection = await db.initialize();

  console.log("Requisição de login recebida:", req.body);

  try {
    const result = await connection.execute(
      `SELECT * FROM users WHERE email = :email`,
      [email]
    );

    if (result.rows.length === 0) {
      console.log("Usuário não encontrado para o email:", email);
      return res.status(400).json({ message: 'Usuário não encontrado' });
    }

    const user = result.rows[0];
    const isMatch = await bcrypt.compare(password, user.PASSWORD);

    if (!isMatch) {
      console.log("Senha inválida para o usuário:", email);
      return res.status(400).json({ message: 'Senha inválida' });
    }

    const token = jwt.sign({ id: user.ID }, process.env.JWT_SECRET, { expiresIn: '1h' });
    console.log("Login bem-sucedido para o usuário:", email);

    res.json({ token });
  } catch (err) {
    console.error("Erro ao fazer login:", err);
    res.status(500).json({ message: 'Erro ao fazer login' });
  } finally {
    await connection.close();
  }
}

async function register(req, res) {
  const { email, password } = req.body;
  const connection = await db.initialize();

  console.log("Requisição de registro recebida:", req.body);

  try {
    const hashedPassword = await bcrypt.hash(password, 10);

    await connection.execute(
      `INSERT INTO users (email, password) VALUES (:email, :password)`,
      [email, hashedPassword],
      { autoCommit: true }
    );

    console.log("Usuário registrado com sucesso:", email);
    res.status(201).json({ message: 'Usuário registrado com sucesso' });
  } catch (err) {
    console.error("Erro ao registrar usuário:", err);
    res.status(500).json({ message: 'Erro ao registrar usuário' });
  } finally {
    await connection.close();
  }
}

async function forgotPassword(req, res) {
  const { email } = req.body;
  const connection = await db.initialize();

  console.log("Requisição de recuperação de senha recebida:", req.body);

  try {
    const result = await connection.execute(
      `SELECT * FROM users WHERE email = :email`,
      [email]
    );

    if (result.rows.length === 0) {
      console.log("Usuário não encontrado para o email:", email);
      return res.status(400).json({ message: 'Usuário não encontrado' });
    }

    const token = jwt.sign({ id: result.rows[0].ID }, process.env.JWT_SECRET, { expiresIn: '1h' });
    console.log("Token de recuperação gerado para o usuário:", email);

    const transporter = nodemailer.createTransport({
      service: 'gmail',
      auth: {
        user: process.env.EMAIL_USER,
        pass: process.env.EMAIL_PASS,
      },
    });

    const mailOptions = {
      from: process.env.EMAIL_USER,
      to: email,
      subject: 'Recuperação de Senha',
      text: `Clique no link para resetar sua senha: http://localhost:3000/reset-password?token=${token}`,
    };

    await transporter.sendMail(mailOptions);
    console.log("E-mail de recuperação enviado para:", email);

    res.json({ message: 'E-mail de recuperação enviado com sucesso' });
  } catch (err) {
    console.error("Erro ao enviar e-mail de recuperação:", err);
    res.status(500).json({ message: 'Erro ao enviar e-mail de recuperação' });
  } finally {
    await connection.close();
  }
}

module.exports = { login, register, forgotPassword };
