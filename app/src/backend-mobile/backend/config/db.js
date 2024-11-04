const oracledb = require('oracledb');

oracledb.outFormat = oracledb.OUT_FORMAT_OBJECT;

const dbConfig = {
  user: process.env.DB_USER,
  password: process.env.DB_PASSWORD,
  connectString: `${process.env.DB_HOST}:${process.env.DB_PORT}/${process.env.DB_SID}`
};

async function initialize() {
  try {
    const connection = await oracledb.getConnection(dbConfig);
    console.log('Conectado ao banco de dados Oracle');
    return connection;
  } catch (err) {
    console.error("Erro ao conectar ao banco de dados Oracle:", err);
  }
}

module.exports = { initialize };
