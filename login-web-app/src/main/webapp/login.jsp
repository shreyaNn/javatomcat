<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang='en'>
<head>
    <meta charset='UTF-8'>
    <meta name='viewport' content='width=device-width, initial-scale=1.0'>
    <title>Login Page</title>
    <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>
    <link href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css' rel='stylesheet'>
    <style>
        body, html {
            height: 100%;
            margin: 0;
            background: linear-gradient(120deg, #84fab0 0%, #8fd3f4 100%);
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .login-container {
            background-color: #ffffff;
            border-radius: 15px;
            padding: 40px;
            box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 450px;
        }
        .welcome-text {
            color: #333;
            font-weight: 300;
            margin-bottom: 2rem;
            text-align: center;
	    font-size: 2.5rem;  /* Increased font size */
        }
        .form-input {
            border: 1px solid #e0e0e0;
            border-radius: 8px;
            padding: 15px 20px;
            margin-bottom: 20px;
            width: 100%;
	    font-size: 1.1rem;  /* Increased font size */
        }
        .btn-login {
            width: 100%;
            padding: 15px;
            border-radius: 30px;
            background: linear-gradient(to right, #667eea, #764ba2);
            border: none;
            color: white;
            font-weight: bold;
	    font-size: 1.2rem;  /* Increased font size */
            transition: transform 0.3s ease;
        }
        .btn-login:hover {
            transform: translateY(-3px);
            box-shadow: 0 4px 8px rgba(0,0,0,0.2);
        }
        .input-icon {
            position: absolute;
            top: 50%;
            left: 15px;
            transform: translateY(-50%);
            color: #aaa;
	    font-size: 1.2rem;  /* Increased font size */
        }
        .input-with-icon {
            position: relative;
        }
        .input-with-icon input {
            padding-left: 45px;
        }
    </style>
</head>
<body>
    <div class='login-container'>
        <h1 class='welcome-text'>Welcome Back!</h1>
        <form method='post' action='login'>
            <div class='input-with-icon'>
                <i class='fas fa-user input-icon'></i>
                <input type='text' class='form-input' id='username' name='username' placeholder='Username' required>
            </div>
            <div class='input-with-icon'>
                <i class='fas fa-lock input-icon'></i>
                <input type='password' class='form-input' id='password' name='password' placeholder='Password' required>
            </div>
            <button type='submit' class='btn btn-login'>
                Login
            </button>
        </form>
    </div>
    <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js'></script>
</body>
</html>
