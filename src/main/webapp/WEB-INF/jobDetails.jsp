<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="/css/jobDetails.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <main>
        <nav>
            <h1>Tarea: ${jobDetail.description}</h1>
        </nav>
        <section class="primary-section">
            <div class="details-container">
                <div>
                    <h2>Creador:</h2>
                    <h2>Asignado:</h2>
                    <h2>Prioridad:</h2>
                    <form action="/jobs/edit/${jobDetail.id}">
                        <button>Edit</button>
                    </form>
                </div>
                <div>
                    <h2 class="details-item">${jobDetail.creator}</h2>
                    <c:forEach var="user" items="${jobDetail.users}">
                        <h2 class="details-item">${user.name}</h2>
                    </c:forEach>
                    <h2 class="details-item">${jobDetail.priority}</h2>
                    <form action="/jobs/delete/${id}" method="post">
                        <input type="hidden" name="_method" value="DELETE"/>
                        <button>Eliminar</button>
                    </form>
                </div>
            </div>
        </section>
        <section class="secondary-section">
            <form action="/jobs" method="get"><button>Volver a tareas</button></form>
            <button>Completado</button>
        </section>
    </main>
</body>
</html>