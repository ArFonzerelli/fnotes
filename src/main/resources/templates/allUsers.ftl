<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Пользователи</title>
</head>
<body>
    <table border="1">
            <thead>
            <tr>
                <th>Имя пользователя</th>
                <th>Активен</th>
                <th>Роли</th>
                <th>Редактировать</th>
                <th>Удалить</th>
            </tr>
            </thead>
            <#list users as user>
            <tr>
                <td>${user.username}</td>
                <td>${user.enabled?string('Да', 'Нет')}</td>
                <td><#list user.roles as role>${role}<#sep>, </#list></td>
                <td><a href="/users/edit_user?id=${user.id}">Редактировать</a></td>
                <td><div><a href="/users/delete_user?id=${user.id}">Удалить</a></div></td>
            </tr>
            </#list>
    </table>
</body>
</html>