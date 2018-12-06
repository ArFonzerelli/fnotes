<#import "../parts/common.ftl" as c>
<@c.page>

<link rel="stylesheet" type="text/css" href="/static/css/editUser.css">

<title>Смена пароля</title>

<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-user">
                <div class="panel-heading">
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <form id="user-form" action="/profile/change_password" method="post" role="form" style="display: block;">
                                <input type="hidden" name="id" value="${user.id}"/>
                                <div class="username">
                                    <label class="username">Смена пароля</label>
                                </div>
                                <div class="form-group">
                                    <label for="password" class="usr-label">Старый пароль:</label>
                                        <#if oldPasswordCorrect_failed??><div class="error_msg">${oldPasswordCorrect_failed}</div></#if>
                                    <input type="password" class="form-control" name="oldPassword" id="oldPassword" tabindex="2" placeholder="Пароль"/>
                                </div>
                                <div class="form-group">
                                    <label for="password" class="usr-label">Новый пароль:</label>
                                        <#if passwordsMatch_failed??><div class="error_msg">${passwordsMatch_failed}</div></#if>
                                        <#if password_failed??><div class="error_msg">${password_failed}</div></#if>
                                    <input type="password" name="password" id="password" tabindex="2" placeholder="Пароль" class="form-control"/>
                                </div>
                                <div class="form-group">
                                    <input type="password" name="confirmPassword" id="confirmPassword" tabindex="2" class="form-control" placeholder="Повторите пароль">
                                </div>
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

</@c.page>
