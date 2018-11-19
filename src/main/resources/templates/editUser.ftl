<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>${user.username}</title>
</head>
<body>
    <form action="/users/save_user" method="post">
        <div><input type="hidden" name="id" value="${user.id}"/></div>
        <div><label>Имя пользователя: <input type="text" name="username" value="${user.username}"/></label></div>
        <div><label>Новый пароль: <input type="text" name="password"/></label></div>
        <div><label>Активен: <input type="checkbox" name="enabled" ${user.enabled?string("checked", "")}></label></div>
        <div><label>Роли: </label>
        <#list roles as role>
            <div>
                <label>
                    <input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}
                </label>
            </div>
        </#list>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <div><input type="submit" value="Подтвердить изменения"></div>
    </form>
</body>
</html>