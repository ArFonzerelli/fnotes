<#import "../parts/common.ftl" as c>
<@c.page>

<link rel="stylesheet" type="text/css" href="/static/css/editUser.css">


<title>Профиль: ${user.username}</title>


<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-user">
                <div class="panel-heading">
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <form id="user-form" action="/profile/update" method="post" role="form" style="display: block;">
                                <input type="hidden" name="id" value="${user.id}"/>
                                <input type="hidden" name="username" value="${user.username}"/>
                                <div class="username">
                                    <label class="username">${user.username}</label>
                                </div>
                                <div class="form-group">
                                    <label for="username" class="usr-label">Почтовый адрес:</label>
                                        <#if email_failed??><div class="error_msg">${email_failed}</div></#if>
                                    <input type="text" name="email" id="email" tabindex="2" class="form-control" placeholder="<#if user.email??>${user.email}<#else>Почтовый адрес</#if>" value="<#if user.email??>${user.email}</#if>"/>
                                </div>
                                <a href="/profile/change_password?id=${user.id}">Смена пароля</a>
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

