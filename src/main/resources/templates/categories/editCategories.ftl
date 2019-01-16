<#import "../parts/common.ftl" as c>
<@c.page>

<link rel="stylesheet" type="text/css" href="/static/css/style.css">

<title>Редактирование категорий</title>

<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-user">
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
        <#if categoryName_failed??><div class="error_msg">${categoryName_failed}</div></#if>
        <form action="/categories/add" method="post">
            <table class="table table-striped">
                <tr>
                    <td>
                        <input type="text" class="form-control" name="categoryName" placeholder="Новая категория">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                    </td>
                    <td>
                        <button type="submit" class="btn btn-edit-user">Добавить</button>
                    </td>
                </tr>
            </table>
        </form>
        <#if editCategoryName_failed??><div class="error_msg">${editCategoryName_failed}</div></#if>
        <table class="table table-striped">
                    <#if categories??>
                    <#list categories as category>
                    <tr>
                        <td>
                            <form action="/categories/edit" method="post">
                            <input type="hidden" id="id" name="id" value="${category.id}">
                            <input type="text" class="form-control" name="editCategoryName" value="${category.name}">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                        </td>
                        <td>
                            <button type="submit" class="btn btn-edit-user">Переименовать</button>
                        </td>
                        <td>
                            <form action="/categories/delete" method="post">
                                <input type="hidden" id="id" name="id" value="${category.id}">
                                <button type="submit" class="btn btn-edit-user">Удалить</button>
                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                            </form>
                        </td>
                    </tr>
                    </#list>
                    </table>
                    </#if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</@c.page>
