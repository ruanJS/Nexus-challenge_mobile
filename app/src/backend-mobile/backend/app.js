const express = require('express');
const dotenv = require('dotenv');
const authRoutes = require('./routes/authRoutes');

dotenv.config();

const app = express();

// Middleware para logar requisições recebidas
app.use((req, res, next) => {
    console.log(`Recebendo requisição: ${req.method} ${req.url}`);
    next();
});

app.use(express.json());

app.use('/api/auth', authRoutes);

// Rota de teste
app.get('/', (req, res) => {
    console.log("Rota principal acessada");
    res.send("Olá, mundo!");
});

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => console.log(`Servidor rodando na porta ${PORT}`));
