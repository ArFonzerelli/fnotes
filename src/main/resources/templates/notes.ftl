<#import "parts/common.ftl" as c>
<@c.page>

    <title>FNotes</title>

<a href="/users/all">Пользователи</a>
<div>
    <a href="/logout">Выход</a>
</div>

<form method="post">
    <div><input type="text" name="title" placeholder="Заголовок"></div>
    <div><input type="text" name="text" placeholder="Текст заметки"></div>
    <div><label>Важность: </label>
    <#list importances as importance>
        <label><input type="radio" name="importance" value="${importance}" ${importance.normal?string("checked", "")}>${importance}</label>
    </#list>
    </div>
    <div><label>Категория: </label><input type="text" name="category" placeholder="Обычные заметки"></div>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit">Добавить</button>
</form>

<strong>Мои заметки</strong>
    <#list notes as note>
    <div>
        <div><strong>${note.title}</strong></div>
        <#--<div>${note.importance}</div>-->
        <#--<div>${note.category}</div>-->
        <span>${note.text}</span>
    </div>
    </#list>
</@c.page>