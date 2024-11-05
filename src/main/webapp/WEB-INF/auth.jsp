<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Caveat:wght@400..700&family=Radio+Canada+Big:ital,wght@0,400..700;1,400..700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/auth.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>Comencemos</h1>
    <main>
        <section>
            <c:if test="${not empty successMessage}">
                <div class="alert alert-success">
                    ${successMessage}
                </div>
            </c:if>
            <h2>Registrate</h2>
            <form:form class="form-container" action="/auth/process/register" method="post" modelAttribute="user">
                <div class="form-group">
                    <form:label path="name" for="name">Nombre:</form:label>
                    <form:input path="name" name="name" id="name" type="text"/>
                    <form:errors path="name" class="errors"/>
                </div>
                <div class="form-group">
                    <form:label path="email" for="email">Correo:</form:label>
                    <form:input path="email" name="email" id="email" type="text"/>
                    <form:errors path="email" class="errors"/>
                </div>
                <div class="form-group">
                    <form:label path="passwordForm" for="password">Contraseña:</form:label>
                    <form:input path="passwordForm" name ="password" id="password" type="password"/>
                    <form:errors path="passwordForm" class="errors"/>
                </div>
                <div class="form-group">
                    <form:label path="passwordConfirm" for="password-confirm">Confirma la contraseña:</form:label>
                    <form:input path="passwordConfirm" name="passwordConfirm" id="password-confirm" type="password"/>
                    <form:errors path="passwordConfirm" class="errors"/>
                </div>
                <button type="submit">Registrarse</button>
            </form:form>
        </section>
        <section>
            <h2>Inicia Sesión</h2>
            <form:form class="form-container" action="/session/process/login" method="post" modelAttribute="session">
                <div class="form-group">
                    <form:label path="email" for="email">Correo:</form:label>
                    <form:input path="email" name="email" id="email" type="text"/>
                    <form:errors path="email" class="errors"/>
                </div>
                <div class="form-group">
                    <form:label path="password" for="password">Contraseña:</form:label>
                    <form:input path="password" name="password" id="password" type="password"/>
                    <form:errors path="password" class="errors"/>
                </div>
                <button type="submit">Iniciar Sesión</button>
            </form:form>
        </section>
    </main>
</body>
</html>