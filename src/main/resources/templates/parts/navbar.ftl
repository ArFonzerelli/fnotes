<#include "variables.ftl">

<#--<link rel="stylesheet" type="text/css" href="/static/css/navbar.css">-->
<link rel="stylesheet" type="text/css" href="/static/css/style.css">

<#if logged_in>
<nav class="navbar navbar-default nav-margin-bottom" role="navigation">
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
        </ul>
        <div class="col-sm-3 col-md-3">
            <form class="navbar-form" role="search" method="get" action="/notes/search">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Поиск" name="query">
                    <div class="input-group-btn">
                        <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                    </div>
                </div>
            </form>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li><a class="nav-link" href="/profile/?id=${user.id}">Профиль</a></li>
            <li><a href="/logout">Выход</a></li>
        </ul>
    </div>
</nav>
</#if>