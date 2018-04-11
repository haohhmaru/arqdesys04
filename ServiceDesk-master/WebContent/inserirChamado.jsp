<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="br.usjt.arqsw.model.Fila"%>
<%@ page import="br.usjt.arqsw.service.FilaService"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Selecionar Fila</title>

    <!-- Favicon -->
    <link rel="shortcut icon" href="assets/img/favicon.png" type="image/x-icon">
    
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/css/style.css" rel="stylesheet">
</head>

<body>
    <% ArrayList<Fila> filas = new ArrayList<Fila>();
       FilaService service = new FilaService();
       filas = service.listarFilas();
       request.setAttribute("filas", filas);
    %>
    <!-- Barra superior com os menus de navegação -->
    <c:import url="menu.jsp" />
    <!-- Container Principal -->
    <div id="main" class="container">
        <h3 class="page-header orange">Inserir Chamados</h3>
        <c:if test="${mensagem == 'sucesso'}">
            <div class="alert alert-success" role="alert">Chamado cadastrado com sucesso!</div>
        </c:if>
        <c:if test="${mensagem == 'falha'}">
            <div class="alert alert-danger" role="alert">Falha ao cadastrar o chamado.</div>
        </c:if>
        <form action="action.do" method="post">
            <div class="row justify-content-md-center">
                <div class="form-group col-md-8">
                    <label for="fila">Escolha a Fila:</label>
                    <select class="form-control" name="id">
                        <c:forEach var="fila" items="${filas}">
                            <option value="${fila.id}">${fila.nome}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group col-md-8">
                    <label for="descricao">Descrição:</label>
                    <textarea class="form-control" name="descricao" id="descricao" rows="4"></textarea>
                </div>
            </div>
            <div id="actions" class="row">
                <div class="col-md-12">
                    <button type="submit" class="btn btn-primary" name="command" value="InserirChamado">Adicionar</button>
                    <a href="index.jsp" class="btn btn-default">Cancelar</a>
                </div>
            </div>
        </form>
    </div>
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
</body>

</html>