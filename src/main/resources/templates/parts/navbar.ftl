<#include "security.ftl">

<link rel="stylesheet" type="text/css" href="/static/css/navbar.css">

<#if logged_in>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/notes/all">FNotes</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a class="nav-link" href="/notes/all">Заметки</a></li>
            <#if isAdmin>
            <li><a class="nav-link" href="/users/all">Пользователи</a></li>
            </#if>
            <#if .main_template_name == "notes/notesPage.ftl">
            <li><a class="nav-link" href="/notes/new">Новая заметка</a></li>
            </#if>
            <#if .main_template_name == "users/allUsers.ftl">
            <li><a class="nav-link" href="/users/new">Новый пользователь</a></li>
            </#if>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li class="navbar-text">Вы вошли как: ${username}</li>
            <li><a class="nav-link" href="/logout">Выход</a>
        </ul>
    </div>
</nav>
</#if>