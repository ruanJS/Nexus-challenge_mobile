const express = require('express');
const { login, register, forgotPassword } = require('../controllers/authController');
const router = express.Router();

// Rota de login
router.post('/login', (req, res, next) => {
    console.log("Requisição de login recebida:", req.body);
    login(req, res, next);
});

// Rota de registro
router.post('/register', (req, res, next) => {
    console.log("Requisição de registro recebida:", req.body);
    register(req, res, next);
});

// Rota de recuperação de senha
router.post('/forgot-password', (req, res, next) => {
    console.log("Requisição de recuperação de senha recebida:", req.body);
    forgotPassword(req, res, next);
});

module.exports = router;
