<#import "../parts/auth.ftl" as a>

<@a.auth_pages>

<title>Регистрация</title>

<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-login">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-6">
                            <a href="/login" id="login-form-link">Вход</a>
                        </div>
                        <div class="col-xs-6">
                            <a href="/register"  class="active" id="register-form-link">Регистрация</a>
                        </div>
                    </div>
                    <hr>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <form id="register-form" action="/register" method="post" role="form" style="display: block;">
                                <div class="form-group">
                                    <#if username_failed??><div class="error_msg">${username_failed}</div></#if>
                                    <#if username_exists??><div class="error_msg">${username_exists}</div></#if>
                                    <input type="text" name="username" id="username" tabindex="1" class="form-control" placeholder="Логин" value="">
                                </div>
                                <div class="form-group">
                                    <#if email_failed??><div class="error_msg">${email_failed}</div></#if>
                                    <#if email_exists??><div class="error_msg">${email_exists}</div></#if>
                                    <input type="text" name="email" id="email" tabindex="1" class="form-control" placeholder="Почтовый адрес" value="">
                                </div>
                                <div class="form-group">
                                    <#if passwordsMatch_failed??><div class="error_msg">${passwordsMatch_failed}</div></#if>
                                    <#if password_failed??><div class="error_msg">${password_failed}</div></#if>
                                    <input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Пароль">
                                </div>
                                <div class="form-group">
                                    <input type="password" name="confirmPassword" id="confirmPassword" tabindex="2" class="form-control" placeholder="Повторите пароль">
                                </div>
                                <div class="form-group">
                                    <#if captcha_failed??><div class="error_msg">${captcha_failed}</div></#if>
                                    <div class="g-recaptcha" data-sitekey="6Ld8sIQUAAAAALnP4LAOyK3oj6dBl1vamJqrOyQ5"></div>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-register" value="Зарегистрироваться">
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