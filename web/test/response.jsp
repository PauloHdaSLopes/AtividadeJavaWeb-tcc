<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página Teste</title>
    </head>
    <body>
        <h1>Testando conexão Banco de Dados</h1>
        <jsp:useBean id="produto" scope="session" class="tcc.stock.model.Produto" />
        <jsp:setProperty name="produto" property="id"/>
        <h2>Valor digitado: <jsp:getProperty name="request" property="id" /></h2>
    </body>
</html>
