<#import "../parts/common.ftl" as c>
<@c.page>

<link rel="stylesheet" type="text/css" href="/static/css/cards.css">

<title>Заметки</title>

<hr>
<div class="container">
    <#list notes as note>

        <div class="col-xs-3">
            <div class="note note-${note.importance}">
                <div class="shape">
                    <a class="shape-text" href="/delete">X</a>
                </div>
                <div class="note-content">
                    <h3 class="lead">
                        ${note.title}
                    </h3>
                    <p>
                        ${note.text}
                    </p>
                </div>
            </div>
        </div>
    </#list>

</div>

</@c.page>





