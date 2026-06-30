<#import "../layout/base.ftlh" as base>
<@base.layout>
    <div class="card" style="max-width: 900px; margin: 2rem auto;">
        <div class="card-header" style="display: flex; justify-content: space-between; align-items: center;">
            <h3>📜 История прохождения</h3>
            <a href="${ctx}/profile" class="btn btn-outline btn-sm">← В профиль</a>
        </div>
        <div class="card-body">

            <#if history?has_content>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th style="width: 40%;">Квест</th>
                        <th style="width: 25%;">Дата завершения</th>
                        <th style="width: 15%;">Результат</th>
                        <th style="width: 20%;">Статус</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list history as progress>
                        <tr>
                            <td style="font-weight: 500; color: #2c3e50;">
                                ${progress.questTitle}
                            </td>
                            <td style="color: #7f8c8d;">
                                ${progress.finishedAtFormatted! "—"}
                            </td>
                            <td>
                                <span class="badge badge-primary">${progress.score} очков</span>
                            </td>
                            <td>
                                <#if progress.completed>
                                    <span class="badge badge-secondary" style="background-color: #27ae60;">✅ Пройден</span>
                                <#else>
                                    <span class="badge badge-secondary" style="background-color: #f39c12;"> В процессе</span>
                                </#if>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            <#else>
                <div style="text-align: center; padding: 3rem 1rem; color: #7f8c8d;">
                    <div style="font-size: 3rem; margin-bottom: 1rem;">🎮</div>
                    <h4>Вы ещё не проходили квесты</h4>
                    <p>Выберите квест на главной странице и начните приключение!</p>
                    <a href="${ctx}/" class="btn btn-primary mt-3">Перейти к квестам</a>
                </div>
            </#if>
        </div>
    </div>
</@base.layout>