<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>LogIn</title>
    <link rel="stylesheet" href="./resources/css/style.css" type="text/css" media="screen"/>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" language="javascript" src="/resources/js/script.js"></script>
</head>

<body>
<div class="form-style-0">
    <h2>Login to your account
        <a href="registration"><input id="registr" type="button" value="Reistration"></a>
    </h2>

    <form name="login" method="post" action="/home">
        <div class="register-switch">

            <input type="radio" name="role" id="student" class="register-switch-input" value="student" checked>
            <label for="student" class="register-switch-label-student">
                <img id="iconS" src="resources/pictures/student_icon.png">
                <span>Student</span>
            </label>

            <input type="radio" name="role" id="teacher" value="teacher" class="register-switch-input">
            <label for="teacher" class="register-switch-label-teacher">
                <img id="iconT" src="resources/pictures/teacher_icon.png">
                <span>Teacher</span>
            </label>

        </div>

        <input type="text" name="login" placeholder="Login" />
        <input type="password" name="password" placeholder="Password" />

        <input id="submit" type="submit" value="Sign in"/>
    </form>
    </div>
    <footer>
        © 2019 Elizabeth Marmysh. All rights reserved.
    </footer>
</body>
</html>