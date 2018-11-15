<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>FNotes</title>
</head>
<body>

<div>
    <form action="/logout" method="post">
        <input type="submit" value="Выход">
    </form>
</div>

<form method="post">
    <input type="text" name="title" placeholder="Заголовок">
    <input type="text" name="text" placeholder="Текст заметки">
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