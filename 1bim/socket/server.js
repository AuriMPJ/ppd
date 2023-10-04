// Importa as bibliotecas necessárias
require('dotenv').config();

var express = require('express'),
    app = express(),

bodyParser = require('body-parser');

var port = process.env.PORT || 8080; // Define a porta padrão como 8080 caso não seja fornecida via variável de ambiente

var emitir = require('./middleware');

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

var router = require('./routes');

app.use(emitir);// Utiliza o middleware de emissão de notificações
app.use(process.env.API_BASE_ROUTE, router); // Define a rota base '/api' para o roteador

// Inicia o servidor na porta definida
app.listen(port);
console.log('Conectado à porta ' + port);