<#import "../parts/common.ftl" as c>
<@c.page>

<link rel="stylesheet" type="text/css" href="/static/css/style.css">


<title>Новая заметка</title>


<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-note">
                <div class="panel-heading">
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <form id="note-form" action="/notes/create" method="post" role="form" style="display: block;">
                                <div class="form-group">
                                    <input type="text" name="title" id="title" tabindex="1" class="form-control" <#if note??>value="${note.title}"<#else>placeholder="Заголовок"</#if>>
                                </div>
                                <hr>
                                <#if text_failed??><div class="error_msg">${text_failed}</div></#if>
                                <div class="form-group">
                                    <textarea rows="10" name="text" id="text" class="form-control note-textarea" <#if !note??>placeholder="Заметка..."</#if>><#if note??>${note.text}</#if></textarea>
                                </div>
                                <div class="form-group">
                                    <input type="text" name="category" id="category" tabindex="2" class="form-control" <#if note??>value="${note.category}"<#else>placeholder="Укажите категорию"</#if>>
                                </div>
                                <div class="form-group">
                                    <div class="importance">
                                        <label class="importance">Важность:</label>
                                        <#list importances as importance>
                                            <label class="importance">
                                                <input type="radio" name="importance" value="${importance}" <#if note??>${(importance == note.importance)?string("checked", "")}<#else>${importance.normal?string("checked", "")}</#if>>${importance.translation}
                                            </label>
                                        </#list>
                                    </div>
                                </div>
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="edit-note-submit" id="edit-note-submit" tabindex="4" class="form-control btn btn-edit-note" value="Добавить заметку">
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
</@c.page>

