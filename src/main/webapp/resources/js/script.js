$(document).ready(function () {
    $(".caret").click(function () {
        var toggler = document.getElementsByClassName("caret");
        var i;

        for (i = 0; i < toggler.length; i++) {
            toggler[i].addEventListener("click", function () {
                this.parentElement.querySelector(".nested").classList.toggle("active");
                this.classList.toggle("caret-down");
            });
        }
    });
});
// All--Task
$(window).on("load", function () {
    if (document.getElementById("toggle").value == "allTask") {
        $("#allTask").css("display", "block");
        $("#createTask").css('display', 'none');
        $("#searchTask").css('display', 'none');
        $("#responseToTaskSearch").css('display', 'none');
        $("#allStudent").css('display', 'none');
        $("#sendTask").css('display', 'none');
        $("#searchStudent").css('display', 'none');
        $("#responseToStudentSearch").css('display', 'none');
    }
});

// Create--Task
$(document).ready(function () {
    $("#createTaskBtn").click(function () {
        $("#allTask").css("display", "none");
        $("#createTask").css('display', 'block');
        $("#searchTask").css('display', 'none');
        $("#responseToTaskSearch").css('display', 'none');
        $("#allStudent").css('display', 'none');
        $("#sendTask").css('display', 'none');
        $("#searchStudent").css('display', 'none');
        $("#responseToStudentSearch").css('display', 'none');
    });
});

$(document).ready(function () {
    $("#closeDialogCreateTask").click(function () {
        document.querySelector('dialog').close();
        $("#allTask").css("display", "none");
        $("#createTask").css('display', 'block');
        $("#searchTask").css('display', 'none');
        $("#responseToTaskSearch").css('display', 'none');
        $("#allStudent").css('display', 'none');
        $("#sendTask").css('display', 'none');
        $("#searchStudent").css('display', 'none');
        $("#responseToStudentSearch").css('display', 'none');
    })
});

// Search--Task
$(document).ready(function () {
    $("#searchTaskBtn").click(function () {
        $("#allTask").css("display", "none");
        $("#createTask").css('display', 'none');
        $("#searchTask").css('display', 'block');
        $("#responseToTaskSearch").css('display', 'none');
        $("#allStudent").css('display', 'none');
        $("#sendTask").css('display', 'none');
        $("#searchStudent").css('display', 'none');
        $("#responseToStudentSearch").css('display', 'none');
    });
});

// All--Student
$(window).on("load", function () {
    if (document.getElementById("toggle").value == "allStudent") {
        $("#allTask").css("display", "none");
        $("#createTask").css('display', 'none');
        $("#searchTask").css('display', 'none');
        $("#responseToTaskSearch").css('display', 'none');
        $("#allStudent").css('display', 'block');
        $("#sendTask").css('display', 'none');
        $("#searchStudent").css('display', 'none');
        $("#responseToStudentSearch").css('display', 'none');
    }
});

// Send--Task
$(document).ready(function () {
    $("#sendTaskBtn").click(function () {
        $("#allTask").css("display", "none");
        $("#createTask").css('display', 'none');
        $("#searchTask").css('display', 'none');
        $("#responseToTaskSearch").css('display', 'none');
        $("#allStudent").css('display', 'none');
        $("#sendTask").css('display', 'block');
        $("#searchStudent").css('display', 'none');
        $("#responseToStudentSearch").css('display', 'none');
    });
});

$(document).ready(function () {
    $("#closeDialogSendTask").click(function () {
        document.querySelector('dialog').close();
        $("#allTask").css("display", "none");
        $("#createTask").css('display', 'none');
        $("#searchTask").css('display', 'none');
        $("#responseToTaskSearch").css('display', 'none');
        $("#allStudent").css('display', 'none');
        $("#sendTask").css('display', 'block');
        $("#searchStudent").css('display', 'none');
        $("#responseToStudentSearch").css('display', 'none');
    })
});

// $(window).on("load",function () {
//     if (document.getElementById("toggle").value == "sendTask") {
//         $("#allTask").css("display", "none");
//         $("#createTask").css('display', 'none');
//         $("#searchTask").css('display', 'none');
//         $("#responseToTaskSearch").css('display','none');
//         $("#allStudent").css('display', 'none');
//         $("#sendTask").css('display', 'block');
//         $("#searchStudent").css('display', 'none');
//         $("#responseToStudentSearch").css('display','none');
//     }
// });

$(document).ready(function () {
    $("#closeDialogSendTask").click(function () {
        document.querySelector('dialog').close();
        $("#allTask").css("display", "none");
        $("#createTask").css('display', 'none');
        $("#searchTask").css('display', 'none');
        $("#responseToTaskSearch").css('display', 'none');
        $("#allStudent").css('display', 'none');
        $("#sendTask").css('display', 'block');
        $("#searchStudent").css('display', 'none');
        $("#responseToStudentSearch").css('display', 'none');
    })
});

// Search--Student
$(document).ready(function () {
    $("#searchStudentBtn").click(function () {
        $("#allTask").css("display", "none");
        $("#createTask").css('display', 'none');
        $("#searchTask").css('display', 'none');
        $("#responseToTaskSearch").css('display', 'none');
        $("#allStudent").css('display', 'none');
        $("#sendTask").css('display', 'none');
        $("#searchStudent").css('display', 'block');
        $("#responseToStudentSearch").css('display', 'none');
    });
});

// Registration
$(document).ready(function () {
    $(".register-switch-label-teacher").click(function () {
        $(".specialization").css("display", "block");
        $(".group").css('display', 'none');
    });
});

$(document).ready(function () {
    if (document.getElementById("hideRegistration").value == "true") {
        $(".form-style-0").css('display', "none");
    }
});

$(document).ready(function () {
    $("#closeDialogRegistration").click(function () {
        document.querySelector('dialog').close();
        $(".form-style-0").css("display", "block");
    });
});

$(document).ready(function () {
    $(".register-switch-label-student").click(function () {
        $(".group").css("display", "block");
        $(".specialization").css('display', 'none');
    });
});

$(document).ready(function () {
    $("#readyUl li").click(function () {
        document.getElementById("taskName").innerHTML = $(this).text();
    });
});

$(document).ready(function () {
    $(".studentSend").click(function () {
        var value = $(this).val();
        var text = $(this).text();
        document.getElementById("studentName4Send").innerHTML = text;
        $("#sendStudentId").val(value);
    })
});

$(document).ready(function () {
    $(".taskSend").click(function () {
        var value = $(this).val();
        var text = $(this).text();
        console.log(value);
        document.getElementById("taskName4Send").innerHTML = text;
        $("#sendTaskId").val(value);
    })
});