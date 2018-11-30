<#import "../parts/common.ftl" as c>
<@c.page>

<link rel="stylesheet" type="text/css" href="/static/css/editNote.css">


<title>${note.title}</title>


<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-note">
                <div class="panel-heading">
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <form id="note-form" action="/notes/update" method="post" role="form" style="display: block;">
                                <input type="hidden" name="id" value="${note.id}"/>
                                <div class="form-group">
                                    <input type="text" name="title" id="title" tabindex="1" class="form-control" placeholder="${note.title}" value="${note.title}">
                                </div>
                                <hr>
                                <#if text_failed??><div class="error_msg">${text_failed}</div></#if>
                                <div class="form-group">
                                    <textarea rows="10" name="text" id="text" class="form-control note-textarea">${note.text}</textarea>
                                </div>
                                <div class="form-group">
                                    <input type="text" name="category" id="category" tabindex="2" class="form-control" placeholder="${note.category}" value="${note.category}">
                                </div>
                                <div class="form-group">
                                    <div class="importance">
                                        <label class="importance">Важность:</label>
                                        <#list importances as importance>
                                            <label class="importance">
                                                <input type="radio" name="importance" value="${importance}" ${(importance == note.importance)?string("checked", "")}>${importance.translation}
                                            </label>
                                        </#list>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-6 col-sm-offset-3">
                                        <input type="submit" name="edit-note-submit" id="edit-note-submit" tabindex="4" class="form-control btn btn-edit-note" value="Редактировать">
                                    </div>
                                </div>
                        </div>
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</@c.page>

