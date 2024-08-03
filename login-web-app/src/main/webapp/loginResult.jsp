<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang='en'>
<head>
    <meta charset='UTF-8'>
    <meta name='viewport' content='width=device-width, initial-scale=1.0'>
    <title>Login Result</title>
    <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>
    <link href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css' rel='stylesheet'>
    <style>
        body {
            background: linear-gradient(120deg, #a1c4fd 0%, #c2e9fb 100%);
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .card {
            border: none;
            border-radius: 1rem;
            box-shadow: 0 0.5rem 1rem 0 rgba(0, 0, 0, 0.1);
        }
        .card-title {
            font-weight: 700;
        }
        .btn-primary {
            background-color: #4e73df;
            border-color: #4e73df;
        }
        .btn-primary:hover {
            background-color: #2e59d9;
            border-color: #2e59d9;
        }
        .icon-large {
            font-size: 4rem;
        }
    </style>
</head>
<body>
    <div class='container'>
        <div class='row justify-content-center'>
            <div class='col-md-6'>
                <div class='card'>
                    <div class='card-body p-5'>
                        <% if ((Boolean)request.getAttribute("loginSuccess")) { %>
                            <div class='text-center mb-4'>
                                <i class='fas fa-check-circle text-success icon-large'></i>
                            </div>
                            <h2 class='card-title text-center text-success mb-4'>Login Successful!</h2>
                            <p class='text-center fs-5'>Welcome, <%= request.getAttribute("username") %>!</p>
                            <div class='text-center mt-4'>
                                <a href='<%= request.getContextPath() %>' class='btn btn-primary'>Back to Login</a>
                            </div>
                        <% } else { %>
                            <div class='text-center mb-4'>
                                <i class='fas fa-times-circle text-danger icon-large'></i>
                            </div>
                            <h2 class='card-title text-center text-danger mb-4'>Login Failed</h2>
                            <p class='text-center'>Invalid username or password. Please try again.</p>
                            <div class='text-center mt-4'>
                                <a href='<%= request.getContextPath() %>' class='btn btn-primary'>Back to Login</a>
                            </div>
                        <% } %>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js'></script>
</body>
</html>
