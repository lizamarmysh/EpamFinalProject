<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>
        ${student.name}
        ${student.surname}
    </title>
    <link rel="stylesheet" href="/resources/css/style.css" type="text/css" media="screen"/>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" language="javascript" src="/resources/js/script.js"></script>
</head>

<body>
<div class="form-style-home">
    <h2>
        ${student.name}
        ${student.surname}
    </h2>

    <form:form method="post" action="/homeStudent">
        <div class="menu">
            <ul id="myUL">
                <li><span class="caret">My tasks</span>
                    <ul class="nested">
                        <li><span id="bottomlevel" class="caret">Ready tasks</span>

                            <ul id="readyUl" class="nested">
                                <c:forEach items="${student.personalTasks}" var="personalTask">
                                    <c:if test="${personalTask.status.equalsIgnoreCase('Ready')}">
                                        <a href="/task?id=${personalTask.task.id}">
                                            <li class="twobottomlevelTask" value="${personalTask.task.id}" >${personalTask.task.name}</li>
                                        </a>
                                    </c:if>
                                </c:forEach>
                            </ul>

                        </li>
                        <li><span id="bottomlevel" class="caret">New tasks</span>
                            <ul id="newUl" class="nested">
                                <c:forEach items="${student.personalTasks}" var="personalTask">
                                    <c:if test="${personalTask.status.equalsIgnoreCase('New')}">
                                        <a href="/task?id=${personalTask.taskId}">
                                            <li class="twobottomlevelTask" value="${personalTask.task.name}">${personalTask.task.name}</li>
                                        </a>
                                    </c:if>
                                </c:forEach>
                            </ul>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
        <div class="content">
            <h3 id="taskName">${activeTask.name}</h3>
            <h4>
                <label>Subject: ${activeTask.subject.name} </label>
                <label id="subject"></label>

                <label>Teacher: ${teacher.name} ${teacher.surname}</label>
                <label id="teacherName"></label>
            </h4>

            <div id="task" class="textTask" align="center">${activeTask.text}</div>

            <input id="sendBtn" type="submit" value="Ready">
        </div>
        <footer>
            © 2019 Elizabeth Marmysh. All rights reserved.
        </footer>
    </form:form>
</div>

</body>
</html>
