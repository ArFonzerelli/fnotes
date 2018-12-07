<#import "../parts/common.ftl" as c>
<@c.page>

<#--<link rel="stylesheet" type="text/css" href="/static/css/allUsers.css">-->
<link rel="stylesheet" type="text/css" href="/static/css/style.css">

<title>Пользователи</title>

<div class="col-md-2"></div>
    <div class="col-md-8">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Имя пользователя</th>
                <th>Почтовый адрес</th>
                <th>Активен</th>
                <th>Роли</th>
            </tr>
            </thead>
        <#list users as user>
            <tr>
                <td>${user.username}</td>
                <td><#if user.email??>${user.email}</#if></td>
                <td>${user.enabled?string('Да', 'Нет')}</td>
                <td><#list user.roles as role>${role}<#sep>, </#list></td>
                <td><a href="/users/manage?id=${user.id}">Управлять</a></td>
            </tr>
        </#list>
        </table>

    </div>
<div class="col-md-2"></div>

</@c.page>