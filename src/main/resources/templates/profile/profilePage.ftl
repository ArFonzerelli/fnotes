<#import "../parts/common.ftl" as c>
<@c.page>

<link rel="stylesheet" type="text/css" href="/static/css/style.css">

<title>Профиль: ${user.username}</title>


<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-user">
                <div class="panel-heading">
                    <div>
                        <label class="title">${user.username}</label>
                    </div>
                </div>
                    <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <form id="user-form" action="/profile/update" method="post" role="form" style="display: block;">
                                <input type="hidden" name="id" value="${user.id}"/>

                                <div class="form-group">
                                    <#if email_failed??><div class="error_msg">${email_failed}</#if></div>
                                    <label for="email" class="form-label">Почтовый адрес:</label>
                                    <input type="text" name="email" id="email" tabindex="2" class="form-control" placeholder="<#if user.email??>${user.email}<#else>Почтовый адрес</#if>" value="<#if user.email??>${user.email}</#if>"/>
                                    <div class="changepass-link"><a href="/profile/change_password?id=${user.id}">Смена пароля</a></div>
                                </div>

                                <div class="form-group">
                                    <div class="changepass-link"></div>
                                </div>

                                <div class="row">
                                    <div class="col-sm-6 col-sm-offset-3">
                                        <input type="submit" name="edit-user-submit" id="edit-user-submit" tabindex="4" class="form-control btn btn-edit-user" value="Подтвердить изменения">
                                    </div>
                                </div>

                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                            </form>
                        </div>
                    </div>
                    </div>
            </div>
        </div>
    </div>
</div>

</@c.page>

