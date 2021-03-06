<#import "../parts/auth.ftl" as a>

<@a.auth_pages>

<title>Авторизация</title>

<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-login">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-6">
                            <a href="/login" class="active" id="login-form-link">Вход</a>
                        </div>
                        <div class="col-xs-6">
                            <a href="/register" id="register-form-link">Регистрация</a>
                        </div>
                    </div>
                    <hr>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <form id="login-form" action="/login" method="post" role="form" style="display: block;">
                                <#if ok_msg??><div class="ok_msg">${ok_msg}</div> </#if>
                                <#if error_msg??><div class="error_msg">${error_msg}</div> </#if>
                                <div class="form-group">
                                    <input type="text" name="username" id="username" tabindex="1" class="form-control" placeholder="Логин" value="">
                                </div>
                                <div class="form-group">
                                    <input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Пароль">
                                </div>
                                <div class="form-group text-center">
                                    <input type="checkbox" tabindex="3" class="" name="remember-me">
                                    <label for="remember">Запомнить</label>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="Вход">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="text-center">
                                                <a href="/forgot_password" tabindex="5" class="forgot-password">Забыли пароль?</a>
                                            </div>
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

