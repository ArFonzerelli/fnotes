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

                                <div class="form-group">
                                    <#if email_failed??><div class="error_msg">${email_failed}</div></#if>
                                    <#if email_not_found??><div class="error_msg">${email_not_found}</div></#if>
                                    <label for="email" class="form-label">Введите Email, указанный при регистрации</label>
                                    <input type="text" name="email" id="email" tabindex="2" class="form-control" placeholder="Email" value="">
                                </div>

                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="Восстановить">
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

