<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: lizam
  Date: 05.06.2019
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en">

</head>
<meta charset="UTF-8">
<title>
    ${teacher.name}
    ${teacher.surname}
</title>
<link rel="stylesheet" href="/resources/css/style.css" type="text/css" media="screen"/>
</head>

<body>

<div class="form-style-home">
    <h2>
        ${teacher.name}
        ${teacher.surname}
    </h2>
    <c:if test="${errorSend.equalsIgnoreCase(false)}">
        <dialog open id="successfulSendTask">
            <img id="successfulSendTaskImg" src="/resources/pictures/OK.png">
            <h1>The task was successfully sent to the student!</h1>
            <p><input type="button" id="closeDialogSendTask" value="Ok"></p>
        </dialog>
    </c:if>
    <c:if test="${errorCreate.equalsIgnoreCase(false)}">
        <dialog open id="successfulCreateTask">
            <img id="successfulCreateTaskImg" src="/resources/pictures/OK.png">
            <h1>Task successfully created!</h1>
            <p><input type="button" id="closeDialogCreateTask" value="Ok"></p>
        </dialog>
    </c:if>
    <input hidden id="toggle" value="${isChange}"/>

    <div class="menu">
        <ul id="myULTask">
            <li><span class="caret">Tasks</span>
                <ul class="nested">
                    <li><span id="bottomlevel" class="caret">All tasks</span>
                        <ul id="newUl" class="nested">
                            <c:forEach items="${tasks}" var="task">
                                <a href="/task?id=${task.id}">
                                    <li class="twobottomlevelTask" id="allTaskBtn" value="${task.id}">${task.name}</li>
                                </a>
                            </c:forEach>
                        </ul>
                    </li>
                    <li>
                        <span id="createTaskBtn">Create task</span>
                    </li>
                    <li>
                        <span id="searchTaskBtn">Search task</span>
                    </li>
                </ul>
            </li>
        </ul>

        <ul id="myULStudent">
            <li><span class="caret">Student</span>
                <ul class="nested">
                    <li><span id="bottomlevel" class="caret">All students</span>
                        <ul id="studentUl" class="nested">
                            <c:forEach items="${students}" var="student">
                                <a href="/student?id=${student.id}">
                                    <li class="twobottomlevelStudent" id="allStudentBtn" value="${student.name}">${student.name} ${student.surname}</li>
                                </a>
                            </c:forEach>

                        </ul>
                    </li>
                    <li>
                        <span id="sendTaskBtn">Send task</span>
                    </li>
                    <li>
                        <span id="searchStudentBtn">Search student</span>
                    </li>
                </ul>
            </li>
        </ul>
    </div>

    <div class="content" id="allTask">
        <h3 id="taskName">${activeTask.name}</h3>
        <h4>
            <label>Subject: ${activeTask.subject.name}</label>
        </h4>

        <div class="textTask" align="center">${activeTask.text}</div>
    </div>


    <div class="content" id="createTask">
        <h3>Create task</h3>

        <form:form method="get" action="/create" class="form-style-1" modelAttribute="TaskForm">
            <input name="taskName" id="name" type="text" placeholder="Task name" required>

            <select name="subjectId" class="subject">
                <option disabled selected value>Subject</option>
                <c:forEach items="${subjects}" var="subject">
                    <option value="${subject.id}">${subject.name}</option>
                </c:forEach>
            </select>

            <textarea name="taskText" placeholder="Text" required></textarea>
            <input id="sendBtn" type="submit" value="Create">
        </form:form>
    </div>


    <div class="content" id="searchTask">
        <h3>Search task</h3>
        <h4>To search, specify the following parameters:</h4>
        <form method="get" action="/searchTask" class="form-style-1">
            <input name="name" id="name" type="text" placeholder="Task name">

            <select name="subjectId" class="subject">
                <option selected value>All subjects</option>
                <c:forEach items="${subjects}" var="subject">
                    <option value="${subject.id}">${subject.name}</option>
                </c:forEach>
            </select>

            <input id="sendBtn" type="submit" value="Search">
        </form>
    </div>

    <div class="content" id="responseToTaskSearch">
        <h3>Response to task search</h3>
        <h4>The following matches were found:</h4>
        <form method="post" action="/responseSearch" class="form-style-1">
            <div class="responseSearch" align="center">
                <table>
                    <tr>
                        <th>Task name</th>
                        <th>Subject</th>
                        <th>Text</th>
                    </tr>
                    <c:forEach items="${tasksAfterSearch}" var="task">
                        <tr>
                            <td>${task.name}</td>
                            <td>${task.subject.name}</td>
                            <td>${task.text}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </form>
    </div>

    <div class="content" id="allStudent">
        <h3 id="studentName">${activeStudent.name} ${activeStudent.surname}</h3>
        <h4>
            <label>Group: </label>
            <label id="group">${activeStudent.group.name}</label>
        </h4>

        <div class="textTask" align="center">
            <table>
                <tr>
                    <th>Task name</th>
                    <th>Teacher</th>
                    <th>Status</th>
                </tr>
                <c:forEach items="${personalTasks}" var="personalTask">
                    <tr>
                        <td>${personalTask.task.name}</td>
                        <td>${personalTask.teacher.name} ${personalTask.teacher.surname}</td>
                        <td>${personalTask.status}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

    <div class="content" id="sendTask">
        <h3>Send the task to the student</h3>
        <h4>Select a student and an appropriate assignment for him:</h4>
        <form:form method="get" action="/send" class="form-style-1" modelAttribute="PersonalTaskForm">
            <input hidden name="teacherId" value="${teacher.id}">
            <input hidden name="status" value="new">
            <input hidden id="sendStudentId" name="studentId" value="">
            <input hidden id="sendTaskId" name="taskId" value="">

            <div class="leftDiv">
                <ol class="rounded">
                    <c:forEach items="${students}" var="student">
                        <li class="studentSend" value="${student.id}">${student.name} ${student.surname}</li>
                    </c:forEach>
                </ol>
            </div>
            <div class="rightDiv">
                <ol class="rounded">
                    <c:forEach items="${tasks}" var="task">
                        <li class="taskSend" value="${task.id}">${task.name}</li>
                    </c:forEach>
                </ol>
            </div>
            <p>Send task "<label id="taskName4Send"></label>" to <label id="studentName4Send"></label>?</p>
            <input id="sendBtn" type="submit" value="Send">
        </form:form>
    </div>

    <div class="content" id="searchStudent">
        <h3>Search student</h3>
        <h4>To search, specify the following parameters:</h4>
        <form method="get" action="/searchStudent" class="form-style-1">
            <input name="name" id="name" type="text" placeholder="Student name">
            <input name="surname" id="surname" type="text" placeholder="Student surname">

            <select name="groupId" class="group">
                <option selected value>All groups</option>
                <c:forEach items="${groups}" var="group">
                    <option value="${group.id}">${group.name}</option>
                </c:forEach>
            </select>

            <input id="sendBtn" type="submit" value="Search"/>
        </form>
    </div>

    <div class="content" id="responseToStudentSearch">
        <h3>Response to student search</h3>
        <h4>The following matches were found:</h4>
        <form class="form-style-1">
            <div class="responseSearch" align="center">
                <table>
                    <tr>
                        <th>Student name</th>
                        <th>Student surname</th>
                        <th>Group</th>
                    </tr>
                    <c:forEach items="${studentsAfterSearch}" var="student">
                        <tr>
                            <td>${student.name}</td>
                            <td>${student.surname}</td>
                            <td>${student.group.name}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </form>
    </div>

    <footer>
        © 2019 Elizabeth Marmysh. All rights reserved.
    </footer>
</div>

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" language="javascript" src="/resources/js/script.js"></script>
</body>
</html>