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

    <h2  >
        ${student.name}
        ${student.surname}
    </h2>

    <c:if test="${errorReady.equalsIgnoreCase(false)}">
        <dialog open id="successfulReady">
            <img id="successfulReadyImg" src="/resources/pictures/OK.png">
            <h1>The task is ready!</h1>
            <p><input type="button" id="closeDialogReady" value="Ok"></p>
        </dialog>
    </c:if>


    <form:form method="get" action="/ready" modelAttribute="PersonalTaskForm">
        <input hidden id="hideReady" value="${isHide}"/>
        <input hidden id="statusTask" value="${statusTask}">
        <input hidden id="idPersonalTask" name="id" value=${personalTask.id}>
        <input hidden name="studentId" value="${student.id}">
        <input hidden name="taskId" value="${activeTask.id}">
        <input hidden name="teacherId" value="${teacher.id}">
        <input hidden name="status" value="ready">

        <div class="menu">
            <ul id="myUL">
                <li><span class="caret">My tasks</span>
                    <ul class="nested">
                        <li><span id="bottomlevel" class="caret">Ready tasks</span>

                            <ul id="readyUl" class="nested">
                                <c:forEach items="${student.personalTasks}" var="personalTask">
                                    <c:if test="${personalTask.status.equalsIgnoreCase('ready')}">
                                        <a href="/task?id=${personalTask.id}">
                                            <li class="twobottomlevelTaskReady" value="${personalTask.id}" >${personalTask.task.name}</li>
                                        </a>
                                    </c:if>
                                </c:forEach>
                            </ul>

                        </li>
                        <li><span id="bottomlevel" class="caret">New tasks</span>
                            <ul id="newUl" class="nested">
                                <c:forEach items="${student.personalTasks}" var="personalTask">
                                    <c:if test="${personalTask.status.equalsIgnoreCase('new')}">
                                        <a href="/task?id=${personalTask.id}">
                                            <li class="twobottomlevelTaskNew" value="${personalTask.id}">${personalTask.task.name}</li>
                                        </a>
                                    </c:if>
                                </c:forEach>
                            </ul>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
        <div class="content" id="personalTask">
            <h3 id="taskName">${activeTask.name}</h3>
            <h4>
                <label id="subject">Subject: ${activeTask.subject.name} ${activeTask.subjectId}</label>

                <label id="teacherName">Teacher: ${teacher.name} ${teacher.surname}</label>
            </h4>

            <div id="task" class="textTask" align="center">${activeTask.text}</div>

            <input id="readyBtn" type="submit" value="Ready">
        </div>
        <footer>
            © 2019 Elizabeth Marmysh. All rights reserved.
        </footer>
    </form:form>
</div>

</body>
</html>
