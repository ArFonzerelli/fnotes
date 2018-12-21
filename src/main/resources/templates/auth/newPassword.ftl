<#import "../parts/auth.ftl" as a>

<@a.auth_pages>

<title>Восстановление пароля</title>

<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-login">
                <div class="panel-heading">
                    <div>
                        <label class="title">Восстановление пароля</label>
                    </div>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <form id="login-form" action="/recover_password" method="post" role="form" style="display: block;">

                                <input type="hidden" name="id" value="${user_id}"/>

                                <div class="form-group">
                                    <#if passwordsMatch_failed??><div class="error_msg">${passwordsMatch_failed}</div></#if>
                                    <#if password_failed??><div class="error_msg">${password_failed}</div></#if>
                                    <label for="password" class="form-label">Новый пароль:</label>
                                    <input type="password" name="password" id="password" tabindex="2" placeholder="Пароль" class="form-control"/>
                                </div>

                                <div class="form-group">
                                    <input type="password" name="confirmPassword" id="confirmPassword" tabindex="2" class="form-control" placeholder="Повторите пароль">
                                </div>

                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="Задать пароль">
                                        </div>
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

</@a.auth_pages>

