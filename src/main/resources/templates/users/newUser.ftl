<#import "../parts/common.ftl" as c>
<@c.page>

<#--<link rel="stylesheet" type="text/css" href="/static/css/editUser.css">-->
<link rel="stylesheet" type="text/css" href="/static/css/style.css">

<title>Новый пользователь</title>


<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-user">
                <div class="panel-heading">
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <form id="user-form" action="/users/save" method="post" role="form" style="display: block;">
                                <input type="hidden" name="id" value="${user.id}"/>
                                <div class="form-group">
                                    <label for="username" class="usr-label">Имя пользователя:</label>
                                    <input type="text" name="username" id="title" tabindex="1" class="form-control" placeholder="${user.username}" value="${user.username}"/>
                                </div>
                                <div class="form-group">
                                    <label for="password" class="usr-label">Новый пароль:</label>
                                    <input type="text" name="password" id="password" tabindex="2" class="form-control"/>
                                </div>
                                <div class="form-group">
                                    <label  class="usr-label">Активен:</label>
                                    <input type="checkbox" name="enabled" ${user.enabled?string("checked", "")}/>
                                </div>
                                <div class="form-group">
                                    <label class="usr-label">Роли:</label>
                                        <#list roles as role>
                                                <label class="usr-label">
                                                    <input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}
                                                </label>
                                        </#list>
                                </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-6 col-sm-offset-3">
                                <input type="submit" name="edit-user-submit" id="edit-user-submit" tabindex="4" class="form-control btn btn-edit-user" value="Подтвердить изменения">
                            </div>
                        </div>
                    </div>
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                </div>
            </div>
        </div>
    </div>
</div>
</@c.page>

