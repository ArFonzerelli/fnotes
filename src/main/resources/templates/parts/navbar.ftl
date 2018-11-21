<#include "security.ftl">

<#if logged_in>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/notes">FNotes</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/notes">Заметки</a>
            </li>
            <#if isAdmin>
            <li class="nav-item">
                <a class="nav-link" href="/users/all">Пользователи</a>
            </li>
            </#if>
        </ul>
        <div class="navbar-text">Вы вошли как: ${username}</div>
        <div><a class="nav-link" href="/logout">Выход</a>
    </div>
</nav>
</#if>