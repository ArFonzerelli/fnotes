<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>FNotes</title>
</head>
<body>
<a href="/users/all">Пользователи</a>
<div>
    <a href="/logout">Выход</a>
</div>

<form method="post">
    <input type="text" name="title" placeholder="Заголовок">
    <input type="text" name="text" placeholder="Текст заметки">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit">Добавить</button>
</form>

<strong>Мои заметки</strong>
    <#list notes as note>
    <div>
        <strong>${note.title}</strong>
        <span>${note.text}</span>
    </div>
    </#list>

</body>
</html>