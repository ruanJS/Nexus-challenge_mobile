const jwt = require('jsonwebtoken');

function authenticateToken(req, res, next) {
    // Obtendo o token do cabeçalho
    const token = req.header('Authorization')?.replace('Bearer ', '');

    console.log("Token recebido:", token);

    if (!token) {
        console.log("Acesso negado: Token não fornecido");
        return res.status(401).json({ message: 'Acesso negado' });
    }

    try {
        // Verificando o token
        const verified = jwt.verify(token, process.env.JWT_SECRET);
        req.user = verified;
        console.log("Token verificado com sucesso:", verified);
        next();
    } catch (err) {
        console.error("Erro ao verificar o token:", err);
        res.status(400).json({ message: 'Token inválido' });
    }
}

module.exports = authenticateToken;
