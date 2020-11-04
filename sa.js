var express = require('express');
var app = express();
var bodyParser = require('body-parser');
var mysql = require('mysql')

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
    extended: true
}));

app.get('/', function(req, res){
    return res.send({ error: true, message: 'Test Web API' })
});

var dbConn = mysql.createConnection({
    host: 'localhost',
    user: 'root', 
    password: '',
    database: 'quiz04'
});

dbConn.connect();

app.get('/company/:cp_name', function(req, res){
    var cp_name	 = req.params.cp_name;
    var sql = "SELECT model.md_id, model.md_name FROM company JOIN brand ON(company.cp_id = brand.cp_id) JOIN model ON(brand.br_id = model.br_id) WHERE company.cp_name LIKE '%"+ cp_name + "%'";
    if(!cp_name){
        return res.status(400).send({error: true, message: "ERROR"});
    }else{
        dbConn.query(sql, function(error, results, fields){
            if(error) throw error;
            return res.send(results);
        });
    }    
});

app.get('/brand/:br_name', function(req, res){
    var br_name	 = req.params.br_name;
    var sql = "SELECT limitedmodel.lm_id, limitedmodel.lm_name, limitedmodel.lm_price FROM company JOIN brand ON(company.cp_id = brand.cp_id) JOIN model ON(brand.br_id = model.br_id) JOIN limitedmodel ON(model.md_id = limitedmodel.md_id) WHERE brand.br_name LIKE '%"+ br_name + "%'";
    if(!br_name){
        return res.status(400).send({error: true, message: "ERROR"});
    }else{
        dbConn.query(sql, function(error, results, fields){
            if(error) throw error;
            return res.send(results);
        });
    }    
});




app.listen(3000, function(){
    console.log('Node app is running on port 3000');
});


module.exports = app;