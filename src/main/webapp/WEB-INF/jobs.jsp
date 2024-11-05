<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="/css/jobs.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <main>
        <section>
            <nav>
                <h1>Bienvenido ${currentUser.name}</h1>
                <form action="/session/logout" method="post">
                    <button>Cerrar sesión</button>
                </form>
            </nav>
            <div class="order-container">
                <a href="/jobs?sort=desc"><button>Ordenar por Prioridad (Baja a Alta)</button></a>
                <a href="/jobs?sort=asc"><button>Ordenar por Prioridad (Alta a Baja)</button></a>
            </div>
            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>Tarea</th>
                            <th>Creador</th>
                            <th>Asignado</th>
                            <th>Prioridad</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="job" items="${jobs}">
                            <tr>
                                <td><a href="/jobs/details/${job.id}">${job.description}</a></td>
                                <td>${job.creator}</td>
                                <td>
                                    <c:forEach var="user" items="${job.users}">
                                        <p>${user.name}</p>
                                    </c:forEach>
                                </td>
                                <td>${job.priority}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </section>
        <div class="button-container">
            <form action="/jobs/add" method="get">
                <button class="add-button">Crear taea</button>
            </form>
        </div>
    </main>
</body>
</html>