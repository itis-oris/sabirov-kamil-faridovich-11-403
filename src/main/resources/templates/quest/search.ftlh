<#import "../layout/base.ftlh" as base>
<@base.layout>
    <div class="card" style="max-width: 900px; margin: 2rem auto;">
        <div class="card-header">🔍 Поиск и фильтрация квестов</div>
        <div class="card-body">

            <form action="${ctx}/quests/search" method="get" style="
                background: #f8f9fa;
                padding: 1.5rem;
                border-radius: 8px;
                margin-bottom: 2rem;
            ">
                <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(200px, 1fr)); gap: 1rem;">

                    <div>
                        <label style="font-weight: 600; display: block; margin-bottom: 0.5rem;">Сложность</label>
                        <select name="difficulty" class="form-control">
                            <option value="">Все</option>
                            <option value="EASY" <#if selectedDifficulty?? && selectedDifficulty == 'EASY'>selected</#if>>🟢 Лёгкий</option>
                            <option value="MEDIUM" <#if selectedDifficulty?? && selectedDifficulty == 'MEDIUM'>selected</#if>>🟡 Средний</option>
                            <option value="HARD" <#if selectedDifficulty?? && selectedDifficulty == 'HARD'>selected</#if>>🔴 Сложный</option>
                        </select>
                    </div>

                    <div>
                        <label style="font-weight: 600; display: block; margin-bottom: 0.5rem;">Категория</label>
                        <input type="text" name="category" value="${selectedCategory!''}"
                               class="form-control" placeholder="Например: История">
                    </div>
                </div>

                <div style="margin-top: 1rem; display: flex; gap: 0.5rem;">
                    <button type="submit" class="btn btn-primary">🔍 Найти</button>
                    <a href="${ctx}/quests/search" class="btn btn-outline">Сбросить</a>
                </div>
            </form>

            <#if quests?has_content>
                <p style="color: #7f8c8d; margin-bottom: 1rem;">
                    Найдено квестов: <strong>${quests?size}</strong>
                </p>

                <div style="display: grid; grid-template-columns: repeat(auto-fill, minmax(250px, 1fr)); gap: 1rem;">
                    <#list quests as quest>
                        <div class="quest-card">
                            <h4 style="margin: 0 0 0.5rem; color: #2c3e50;">${quest.title}</h4>
                            <p style="color: #7f8c8d; font-size: 0.9rem; margin-bottom: 1rem;">
                                ${quest.description}...
                            </p>
                            <div style="margin-bottom: 1rem;">
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
            <#else>
                <div style="text-align: center; padding: 3rem; color: #7f8c8d;">
                    <p style="font-size: 1.2rem;">Квесты не найдены</p>
                    <p>Попробуйте изменить параметры поиска или сбросить фильтры</p>
                </div>
            </#if>
        </div>
    </div>
</@base.layout>