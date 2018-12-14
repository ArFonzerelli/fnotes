<#include "../parts/variables.ftl">
<#import "../parts/common.ftl" as c>
<@c.page>

<link rel="stylesheet" type="text/css" href="/static/css/style.css">

<title>Заметки</title>

<hr>
    <div class="col-md-2">
        <h3>Категории</h3>
        <div role="tabpanel">
                <ul class="nav nav-pills brand-pills nav-stacked" role="tablist">
                    <li role="presentation" class="brand-nav <#if current_url == "/notes/all">active</#if>"><a href="/notes/all">Все заметки</a></li>
                    <#if categories??>
                        <#list categories as category>
                            <li role="presentation" class="brand-nav <#if current_url == "/notes/category/${category.id}">active</#if>"><a href="/notes/category/${category.id}">${category}</a></li>
                        </#list>
                    </#if>
                    <li><a class="edit-category" href="/categories/edit">Редактировать категории</a></li>
                </ul>
        </div>
    </div>

    <div class="col-md-8">

    <#if notes??>
    <#list notes as note>

        <div class="col-xs-3">
            <div class="note note-${note.importance}">
                <div class="shape">
                    <form action="/notes/delete" method="post">
                        <label class="delete-btn">X
                            <input type="hidden" id="id" name="id" value="${note.id}">
                            <button type="submit" hidden></button>
                        </label>
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                    </form>
                </div>

                <div class="note-content">
                    <h3 class="lead">
                            ${note.title}
                    </h3>
                    <p>
                        ${note.text}
                    </p>
                </div>
                <a class="open-link" href="/notes/edit?id=${note.id}">Открыть</a>
            </div>
        </div>
    </#list>
        <#elseif no_notes??>
            <div class="title"><label>${no_notes}</label></div>
    </#if>

    </div>

</@c.page>





