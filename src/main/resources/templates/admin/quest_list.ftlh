<#import "../layout/base.ftlh" as base>
<@base.layout>
    <div class="page-header">
        <h2>Управление квестами</h2>
        <a href="${ctx}/admin/quests/create" class="btn btn-primary">Создать квест</a>
    </div>

    <table class="data-table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Название</th>
            <th>Сложность</th>
            <th>Действия</th>
        </tr>
        </thead>
        <tbody>
        <#list quests as quest>
            <tr>
                <td>${quest.id}</td>
                <td>${quest.title}</td>
                <td>${quest.difficulty}</td>
                <td class="action-buttons">
                    <a href="${ctx}/admin/quests/edit/${quest.id}" class="btn btn-warning btn-sm">Ред.</a>
                    <form action="${ctx}/admin/quests/delete/${quest.id}" method="post" class="inline-form">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Удалить?')">Удалить</button>
                    </form>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</@base.layout>