<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="/css/createJob.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <main>
        <section>
            <nav>
                <h1>Editar</h1>
            </nav>
            <div class="table-container">
                <form:form class="form-container" action="/jobs/process/edit/${job.id}" method="post" modelAttribute="job">
                    <input type="hidden" name="_method" value="PUT"/>
                    <div class="form-group">
                        <form:label path="description" for="job">Tarea:</form:label>
                        <form:input path="description" id="job" type="text"/>
                        <form:errors path="description"/>
                    </div>
                    <div class="form-group">
                        <form:label path="users[0].id" for="assigned">Asignado:</form:label>
                        <form:select path="users[0].id" id="assigned">
                            <form:options items="${userList}" itemValue="id" itemLabel="name"/>
                        </form:select>
                        <br/>
                        <form:errors path="users[0].id"/>
                    </div>
                    <div class="form-group">
                        <form:label path="priority" for="priority">Prioridad:</form:label>
                        <form:select path="priority" id="priority">
                            <form:options items="${priorities}" />
                        </form:select>
                        <form:errors path="priority"/>
                    </div>
                    <div class="form-group">
                        <a href="/jobs"><button type="button">Volver a tareas</button></a>
                        <button type="submit">Guardar Tarea</button>
                    </div>
                </form:form>
            </div>
        </section>
    </main>
</body>
</html>