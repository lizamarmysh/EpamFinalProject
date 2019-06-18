<%--
  Created by IntelliJ IDEA.
  User: lizam
  Date: 05.06.2019
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <title>ERROR</title>
        <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script type="text/javascript" language="javascript" src="/resources/js/script.js"></script>
    </head>

    <body>
        <div class="form-style-0">
            <c:if test="${errorSearch.equalsIgnoreCase('true')}">
                <dialog open class="unsuccessful">
                    <img class="unsuccessfulImg" src="/resources/pictures/NO.png">
                    <h1>Search error. Check the correctness of the entered data to search!</h1>
                    <p><input type="button" class="closeDialog" value="Ok"></p>
                </dialog>
            </c:if>

            <c:if test="${errorReady.equalsIgnoreCase('true')}">
                <dialog open class="unsuccessful">
                    <img class="unsuccessfulImg" src="/resources/pictures/NO.png">
                    <h1>The task can not be marked as "ready". Check the correctness of the entered data or connect to the server.</h1>
                    <p><input type="button" class="closeDialog" value="Ok"></p>
                </dialog>
            </c:if>

            <c:if test="${errorCreate.equalsIgnoreCase('true')}">
                <dialog open class="unsuccessful">
                    <img class="unsuccessfulImg" src="/resources/pictures/NO.png">
                    <h1>Task cannot be created. Check the correctness of the entered data or connect to the server.</h1>
                    <p><input type="button" class="closeDialog" value="Ok"></p>
                </dialog>
            </c:if>

            <c:if test="${errorSend.equalsIgnoreCase('true')}">
                <dialog open class="unsuccessful">
                    <img class="unsuccessfulImg" src="/resources/pictures/NO.png">
                    <h1>The task can not be sent to the student. Check the correctness of the entered data or connect to the server.</h1>
                    <p><input type="button" class="closeDialog" value="Ok"></p>
                </dialog>
            </c:if>

            <c:if test="${errorRegister.equalsIgnoreCase('true')}">
                <dialog open class="unsuccessful">
                    <img class="unsuccessfulImg" src="/resources/pictures/NO.png">
                    <h1>Registration failed. Try again later. Check the correctness of the entered data or connect to the server.</h1>
                    <p><input type="button" class="closeDialog" value="Ok"></p>
                </dialog>
            </c:if>

            <c:if test="${errorViewStudent.equalsIgnoreCase('true')}">
                <dialog open class="unsuccessful">
                    <img class="unsuccessfulImg" src="/resources/pictures/NO.png">
                    <h1>Cannot view current user data. Check the connection to the server.</h1>
                    <p><input type="button" class="closeDialog" value="Ok"></p>
                </dialog>
            </c:if>

            <c:if test="${errorViewTask.equalsIgnoreCase('true')}">
                <dialog open class="unsuccessful">
                    <img class="unsuccessfulImg" src="/resources/pictures/NO.png">
                    <h1>Unable to view current task data. Check the connection to the server.</h1>
                    <p><input type="button" class="closeDialog" value="Ok"></p>
                </dialog>
            </c:if>

            <c:if test="${errorLogin.equalsIgnoreCase('true')}">
                <dialog open class="unsuccessful">
                    <img class="unsuccessfulImg" src="/resources/pictures/NO.png">
                    <h1>Wrong login or password. Check the correctness of the entered data or connect to the server.</h1>
                    <p><input type="button" class="closeDialog" value="Ok"></p>
                </dialog>
            </c:if>
        </div>
        <footer>
            © 2019 Elizabeth Marmysh. All rights reserved.
        </footer>
    </body>
</html>
