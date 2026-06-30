<#import "../layout/base.ftlh" as base>
<@base.layout>
    <div class="card">
        <div class="card-body">
            <h1>${quest.title}</h1>
            <p>${quest.description}</p>
            <hr style="margin: 2rem 0;">
            <h3 class="mb-3">Задания</h3>
            <ul class="list-group">
                <#list quest.tasks as task>
                    <li class="list-group-item">${task.question}</li>
                </#list>
            </ul>
            <hr style="margin: 2rem 0;">
            <a href="${ctx}/quests/${quest.id}/play" class="btn btn-success btn-lg mt-3">Начать квест</a>
            <a href="${ctx}/quests/${quest.id}/reviews" class="btn btn-outline btn-lg">
                💬 Отзывы (${quest.reviews?size})
            </a>
        </div>
    </div>
</@base.layout>