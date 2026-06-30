<#import "../layout/base.ftlh" as base>
<@base.layout>
    <div class="page-header">
        <h2>Популярные квесты</h2>
    </div>

    <p class="page-header">
        Здесь собраны квесты с рейтингом 4.0 и выше.
    </p>

    <#if quests?has_content>
        <div class="quest-grid">
            <#list quests as quest>
                <div class="quest-card">
                    <h3 class="quest-card-title">${quest.title}</h3>
                    <p class="quest-card-description">${quest.description}</p>

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

                    <div class="d-flex justify-content-between align-items-center">
                        <a href="${ctx}/quests/${quest.id}" class="btn btn-primary btn-sm">
                            Подробнее
                        </a>
                        <a href="${ctx}/quests/${quest.id}/reviews" class="btn btn-outline btn-sm">
                            💬 Отзывы
                        </a>
                    </div>
                </div>
            </#list>
        </div>
    <#else>
        <div class="search-empty">
            <div style="font-size: 4rem; margin-bottom: 1rem;"></div>
            <p class="search-empty-title">Пока нет популярных квестов</p>
            <p>Как только квесты наберут рейтинг 4.0+, они появятся здесь</p>
            <a href="${ctx}/" class="btn btn-primary mt-3">Перейти к квестам</a>
        </div>
    </#if>
</@base.layout>