<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" language="javascript" src="/resources/js/script.js"></script>
</head>

<body>
<c:if test="${error.equalsIgnoreCase(true)}">
    <dialog open id="unsuccessfulRegistration">
        <img id="unsuccessfulRegistrationImg" src="/resources/pictures/NO.png">
        <h1>The account has not been registered!</h1>
        <p><input type="button" id="closeDialogRegistration" value="Ok"></p>
    </dialog>
</c:if>

<input hidden id="hideRegistration" value="${isHide}"/>

<div id="registrationForm" class="form-style-0">

    <h2>Create your account</h2>

    <form:form id="registrationForm" method="post" action="/registration" modelAttribute="RegistrationForm">
        <div class="register-switch">

            <input type="radio" name="role" value="student" id="student" class="register-switch-input" checked>
            <label for="student" class="register-switch-label-student">
                <img id="iconS" src="/resources/pictures/student_icon.png">
                <span>Student</span>
            </label>

            <input type="radio" name="role" value="teacher" id="teacher" class="register-switch-input">
            <label for="teacher" class="register-switch-label-teacher">
                <img id="iconT" src="/resources/pictures/teacher_icon.png">
                <span>Teacher</span>
            </label>
        </div>

        <input id="name" type="text" name="name" placeholder="Name" required>
        <input id="surname" type="text" name="surname" placeholder="Surname" required>
        <select class="group" name="groupId">
            <option disabled selected value>Group</option>
            <c:forEach items="${groups}" var="group">
                <option value="${group.id}">${group.name}</option>
            </c:forEach>

        </select>
        <select class="specialization" name="specialization">
            <option disabled selected value>Specialization</option>
            <option value="Mathematics">Mathematics</option>
            <option value="Physics">Physics</option>
        </select>
        <input id="login" name="login" type="text" placeholder="Login" required>
        <input id="password" name="password" type="password" placeholder="Password" required>

        <input id="registration" type="submit" value="Sign up">
        <p class="regtext">Already have an account? <a href="/index.jsp">Enter the login</a>!</p>
    </form:form>
</div>
<footer>
    © 2019 Elizabeth Marmysh. All rights reserved.
</footer>
</body>
</html>
