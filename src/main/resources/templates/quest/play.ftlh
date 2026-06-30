<#import "../layout/base.ftlh" as base>
<@base.layout>
    <div class="card" style="max-width: 800px; margin: 2rem auto; text-align: center;">
        <div class="card-header">
            <h2>Прохождение: ${quest.title}</h2>
        </div>
        <div class="card-body">

            <div id="progress-bar" style="
                height: 10px;
                background: #ecf0f1;
                border-radius: 5px;
                margin-bottom: 2rem;
                overflow: hidden;">
                <div id="progress-fill" style="
                    width: 0%;
                    height: 100%;
                    background: #3498db;
                    transition: width 0.3s;">
                </div>
            </div>

            <div id="game-area">
                <h3 id="question-text" style="min-height: 60px; margin-bottom: 2rem; color: #2c3e50;">
                    Загрузка вопроса...
                </h3>

                <div id="answers-container" style="
                    display: flex;
                    flex-direction: column;
                    gap: 1rem;
                    max-width: 500px;
                    margin: 0 auto;">
                </div>
            </div>

            <div id="result-area" style="display: none; margin-top: 2rem;">
                <h2 style="color: #27ae60;">Поздравляем! </h2>
                <p style="font-size: 1.2rem;">Вы прошли квест "${quest.title}".</p>
                <p>Ваш результат: <strong id="final-score">0</strong> из <strong id="total-tasks">0</strong>.</p>
                <p style="color: #7f8c8d; font-size: 0.9rem;">Письмо с подтверждением отправлено на ваш Email.</p>
                <a href="${ctx}/" class="btn btn-primary mt-3">На главную</a>
            </div>

            <script type="application/json" id="quest-data">
                {
                    "questId": ${quest.id},
                "ctx": "${ctx}",
                "csrfHeader": "${_csrf.headerName}",
                "csrfToken": "${_csrf.token}",
                "tasks": [
                <#list quest.tasks as task>
                    {
                        "id": ${task.id},
                        "question": "${task.question?js_string}",
                        "answers": [
                            <#list task.answers as ans>
                            {"id": ${ans.id}, "text": "${ans.text?js_string}"}<#if ans_has_next>,</#if>
                </#list>
                        ]
                    }<#if task_has_next>,</#if>
                </#list>
                ]
            }
            </script>

        </div>
    </div>

    <script src="${ctx}/js/play.js"></script>
</@base.layout>