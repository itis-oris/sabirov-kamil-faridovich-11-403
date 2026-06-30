<#import "../layout/base.ftlh" as base>
<@base.layout>
    <div class="card max-width-900 margin-auto margin-2rem">
        <div class="card-header">
            <#if id??>Редактирование квеста<#else>Создание нового квеста</#if>
        </div>
        <div class="card-body">
            <#if errorMessage?has_content>
                <div class="alert alert-danger">${errorMessage}</div>
            </#if>

            <#assign formAction>
                <#if id??>${ctx}/admin/quests/edit/${id}<#else>${ctx}/admin/quests/create</#if>
            </#assign>

            <form action="${formAction}" method="post" id="questForm">

                <h4 class="section-header-lg">1. Информация о квесте</h4>

                <div class="form-group mb-3">
                    <label class="form-label">Название *</label>
                    <input type="text" name="title" value="${form.title!''}" class="form-control" required>
                </div>
                <div class="form-group mb-3">
                    <label class="form-label">Описание *</label>
                    <textarea name="description" class="form-control" rows="3" required>${form.description!''}</textarea>
                </div>
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label>Сложность *</label>
                        <select name="difficulty" class="form-control" required>
                            <option value="">-- Выбрать --</option>
                            <option value="EASY" <#if form.difficulty?? && form.difficulty == 'EASY'>selected</#if>>
                                🟢 Лёгкий
                            </option>
                            <option value="MEDIUM" <#if form.difficulty?? && form.difficulty == 'MEDIUM'>selected</#if>>
                                🟡 Средний
                            </option>
                            <option value="HARD" <#if form.difficulty?? && form.difficulty == 'HARD'>selected</#if>>
                                🔴 Сложный
                            </option>
                        </select>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label>Категория *</label>
                        <input type="text" name="category" value="${form.category!''}" class="form-control" required>
                    </div>
                </div>

                <h4 class="section-header-lg">2. Задания и ответы</h4>

                <div id="quest-form-container"
                     data-task-count="${(form.tasks?size)!0}"
                     data-ctx="${ctx}">

                    <div id="tasks-container">
                        <#list form.tasks as taskForm>
                            <#assign taskIndex = taskForm?index>
                            <@renderTask taskIndex, taskForm />
                        </#list>
                        <#if !form.tasks?has_content>
                            <@renderTask 0, {} />
                        </#if>
                    </div>
                </div>

                <button type="button" class="btn btn-secondary btn-sm" id="add-task-btn">
                    + Добавить ещё одно задание
                </button>

                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

                <hr style="margin: 2rem 0;">

                <div class="d-flex gap-2">
                    <button type="submit" class="btn btn-success">💾 Сохранить квест</button>
                    <a href="${ctx}/admin/quests" class="btn btn-outline">Отмена</a>
                </div>
            </form>
        </div>
    </div>

    <#macro renderTask index task>
        <div class="task-block" data-task-index="${index}">
            <div class="task-header">
                <strong class="task-title">Задание #${index + 1}</strong>
                <button type="button" class="btn btn-danger btn-sm remove-task-btn"> Удалить</button>
            </div>

            <div class="form-group mb-3">
                <label class="form-label">Вопрос *</label>
                <input type="text"
                       name="tasks[${index}].question"
                       value="${task.question!''}"
                       class="form-control"
                       required
                       placeholder="Введите текст вопроса">
            </div>

            <label class="form-label">Варианты ответов *</label>
            <div class="answers-container" data-answer-count="${(task.answers?size)!2}">
                <#list task.answers?default([]) as answer>
                    <@renderAnswer index, answer?index, answer />
                </#list>
                <#if !task.answers?has_content>
                    <@renderAnswer index, 0, {} />
                    <@renderAnswer index, 1, {} />
                </#if>
            </div>
            <button type="button" class="btn btn-outline btn-sm add-answer-btn">+ Добавить вариант ответа</button>
        </div>
    </#macro>

    <#macro renderAnswer taskIndex, answerIndex, answer>
        <div class="answer-row">
            <input type="text"
                   name="tasks[${taskIndex}].answers[${answerIndex}].text"
                   value="${answer.text!''}"
                   class="form-control answer-input"
                   required
                   placeholder="Текст ответа">

            <label class="answer-label">
                <input type="radio"
                       name="tasks[${taskIndex}].correctAnswerIndex"
                       value="${answerIndex}"
                       <#if answer.correct?? && answer.correct>checked</#if>
                        <#if !answer.correct?? && answerIndex == 0>checked</#if>
                       required>
                ✅ Верный
            </label>

            <button type="button" class="btn btn-danger btn-sm remove-answer-btn">✕</button>
        </div>
    </#macro>

    <script src="${ctx}/js/admin-quest-form.js"></script>
</@base.layout>