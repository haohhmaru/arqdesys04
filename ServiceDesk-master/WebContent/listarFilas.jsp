<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Listar Chamados</title>

    <!-- Favicon -->
    <link rel="shortcut icon" href="assets/img/favicon.png" type="image/x-icon">
    
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/css/style.css" rel="stylesheet">
</head>

<body>
    <!-- Barra superior com os menus de navegação -->
    <c:import url="menu.jsp" />
    <!-- Container Principal -->
    <div id="main" class="container">
        <c:if test="${mensagem == 'sucesso'}">
            <div class="alert alert-success" role="alert">Chamado(s) fechados com sucesso!</div>
        </c:if>
        <h2 class="page-header orange">Consultar Chamados</h2>
        <form action="action.do" method="post">
            <div class="row">
                <div class="form-group col-md-4">
                    <label for="fila">Escolha a Fila:</label>
                    <select class="form-control" name="id">
                        <c:forEach var="fila" items="${filas}">
                            <option value="${fila.id}">${fila.nome}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div id="actions" class="row">
                <div class="col-md-12">
                    <button type="submit" class="btn btn-primary" name="command" value="ListarChamados">Listar Chamados</button>
                    <a href="index.jsp" class="btn btn-default">Cancelar</a>
                </div>
            </div>
        </form>
    </div>
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
</body>

</html>