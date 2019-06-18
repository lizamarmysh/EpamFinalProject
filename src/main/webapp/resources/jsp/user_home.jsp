<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
        <section class="main">
            <div class="wrapper-demo">
                <div id="dd" class="wrapper-dropdown-3" tabindex="1">
                    <span>${student.name} ${student.surname}</span>
                    <ul class="dropdown">
                        <li><a href="#"><i><img src="/resources/pictures/student_icon.png" class="icon-account icon-large"></i>${student.name} ${student.surname}</a></li>
                        <li onclick="location.href='/exit';"><a href="/exit"><i><img src="/resources/pictures/exit.svg" class="icon-exit icon-large"></i>Exit</a></li>
                    </ul>
                </div>
                ​
            </div>
        </section>
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
                                            <li class="twobottomlevelTaskReady"
                                                value="${personalTask.id}">${personalTask.task.name}</li>
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
                                            <li class="twobottomlevelTaskNew"
                                                value="${personalTask.id}">${personalTask.task.name}</li>
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
                <label id="subject">Subject: ${subject.name}</label>

                <label id="teacherName">Teacher: ${teacher.name} ${teacher.surname}</label>
            </h4>

            <div id="task" class="textTask" align="center">${activeTask.text}</div>

            <input id="readyBtn" type="submit" value="Ready">
        </div>
    </form:form>
    <footer>
        © 2019 Elizabeth Marmysh. All rights reserved.
    </footer>
</div>
<script type="text/javascript">
    function DropDown(el) {
        this.dd = el;
        this.placeholder = this.dd.children('span');
        this.opts = this.dd.find('ul.dropdown > li');
        this.val = '';
        this.index = -1;
        this.initEvents();
    }
    DropDown.prototype = {
        initEvents: function () {
            var obj = this;
            obj.dd.on('click', function (event) {
                $(this).toggleClass('active');
                return false;
            });
            obj.opts.on('click', function () {
                var opt = $(this);
                obj.val = opt.text();
                obj.index = opt.index();
                obj.placeholder.text(obj.val);
            });
        },
        getValue: function () {
            return this.val;
        },
        getIndex: function () {
            return this.index;
        }
    };
    $(function () {
        var dd = new DropDown($('#dd'));
        $(document).click(function () {
            $('.wrapper-dropdown-3').removeClass('active');
        });
    });
</script>
</body>
</html>
