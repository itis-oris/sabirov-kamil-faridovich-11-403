<#import "layout/base.ftlh" as base>
<@base.layout>
    <div class="hero">
        <h1>Добро пожаловать в мир квестов</h1>
        <p>Выбери квест и проверь свои знания!</p>
    </div>

    <h2 class="mb-4">Доступные квесты</h2>
    <div class="quest-grid">
        <#list quests as quest>
            <div class="quest-card">
                <h3>${quest.title}</h3>
                <p>${quest.description}...</p>
                <div class="mb-3">
                    <span class="badge badge-primary">${quest.category}</span>
                    <span class="badge
                        <#if quest.difficulty == 'EASY'>badge-easy
                        <#elseif quest.difficulty == 'MEDIUM'>badge-medium
                        <#elseif quest.difficulty == 'HARD'>badge-hard
                        <#else>badge-secondary
                        </#if>">${quest.difficulty}
                    </span>
                </div>
                <a href="${ctx}/quests/${quest.id}" class="btn btn-outline btn-sm">Подробнее</a>
            </div>
        </#list>
    </div>
</@base.layout>