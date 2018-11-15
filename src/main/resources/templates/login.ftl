<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>FNotes Авторизация</title>
</head>
<body>
    <#if message??><div>${message}</div></#if>
    <form action="/login" method="post">
        <div><label>Имя пользователя : <input type="text" name="username"/></label></div>
        <div><label>Пароль:<input type="text" name="password"/></label></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <div><input type="submit" value="Вход"/></div>
    </form>
<a href="/register">Регистрация</a>
</body>
</html>