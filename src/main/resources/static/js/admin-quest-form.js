(function() {
    'use strict';

    document.addEventListener('DOMContentLoaded', () => {
        const container = document.getElementById('quest-form-container');
        if (!container) return;

        const ctx = container.dataset.ctx;
        const tasksContainer = document.getElementById('tasks-container');
        const addTaskBtn = document.getElementById('add-task-btn');

        let taskCount = parseInt(container.dataset.taskCount) || 0;

        addTaskBtn.addEventListener('click', () => {
            const taskHtml = createTaskHTML(taskCount);
            tasksContainer.insertAdjacentHTML('beforeend', taskHtml);
            taskCount++;
            updateTaskNumbers();
        });

        tasksContainer.addEventListener('click', (e) => {
            if (e.target.classList.contains('remove-task-btn') ||
                e.target.closest('.remove-task-btn')) {
                e.preventDefault();
                const taskBlocks = tasksContainer.querySelectorAll('.task-block');
                if (taskBlocks.length > 1) {
                    const btn = e.target.classList.contains('remove-task-btn')
                        ? e.target
                        : e.target.closest('.remove-task-btn');
                    btn.closest('.task-block').remove();
                    updateTaskNumbers();
                } else {
                    alert('Должно быть хотя бы одно задание!');
                }
            }

            if (e.target.classList.contains('remove-answer-btn') ||
                e.target.closest('.remove-answer-btn')) {
                e.preventDefault();
                const btn = e.target.classList.contains('remove-answer-btn')
                    ? e.target
                    : e.target.closest('.remove-answer-btn');
                btn.closest('.answer-row').remove();
                updateAnswerRadios(btn.closest('.answers-container'));
            }

            if (e.target.classList.contains('add-answer-btn')) {
                e.preventDefault();
                const answersContainer = e.target.previousElementSibling;
                const answerCount = answersContainer.querySelectorAll('.answer-row').length;
                const taskIndex = parseInt(e.target.closest('.task-block').dataset.taskIndex);

                const answerHtml = createAnswerHTML(taskIndex, answerCount);
                answersContainer.insertAdjacentHTML('beforeend', answerHtml);
            }
        });

        function updateTaskNumbers() {
            const taskBlocks = tasksContainer.querySelectorAll('.task-block');
            taskBlocks.forEach((block, index) => {
                block.dataset.taskIndex = index;
                const title = block.querySelector('.task-title');
                title.textContent = `Задание #${index + 1}`;

                const questionInput = block.querySelector('input[name*="question"]');
                if (questionInput) {
                    questionInput.name = `tasks[${index}].question`;
                }

                const radios = block.querySelectorAll('input[type="radio"]');
                radios.forEach(radio => {
                    radio.name = `tasks[${index}].correctAnswerIndex`;
                });

                const answerInputs = block.querySelectorAll('.answer-input');
                answerInputs.forEach((input, ansIndex) => {
                    input.name = `tasks[${index}].answers[${ansIndex}].text`;
                });

                block.dataset.taskIndex = index;
            });
        }

        function updateAnswerRadios(answersContainer) {
            const rows = answersContainer.querySelectorAll('.answer-row');
            const taskIndex = parseInt(answersContainer.closest('.task-block').dataset.taskIndex);

            rows.forEach((row, index) => {
                const input = row.querySelector('.answer-input');
                if (input) {
                    input.name = `tasks[${taskIndex}].answers[${index}].text`;
                }

                const radio = row.querySelector('input[type="radio"]');
                if (radio) {
                    radio.value = index;
                    radio.name = `tasks[${taskIndex}].correctAnswerIndex`;
                }
            });
        }

        function createTaskHTML(index) {
            return `
                <div class="task-block" data-task-index="${index}">
                    <div class="task-header">
                        <strong class="task-title">Задание #${index + 1}</strong>
                        <button type="button" class="btn btn-danger btn-sm remove-task-btn"> Удалить</button>
                    </div>
                    <div class="form-group mb-3">
                        <label class="form-label">Вопрос *</label>
                        <input type="text" name="tasks[${index}].question" class="form-control" required placeholder="Введите текст вопроса">
                    </div>
                    <label class="form-label">Варианты ответов *</label>
                    <div class="answers-container">
                        <div class="answer-row">
                            <input type="text" name="tasks[${index}].answers[0].text" class="form-control answer-input" required placeholder="Текст ответа">
                            <label class="answer-label">
                                <input type="radio" name="tasks[${index}].correctAnswerIndex" value="0" checked required> ✅ Верный
                            </label>
                            <button type="button" class="btn btn-danger btn-sm remove-answer-btn">✕</button>
                        </div>
                        <div class="answer-row">
                            <input type="text" name="tasks[${index}].answers[1].text" class="form-control answer-input" required placeholder="Текст ответа">
                            <label class="answer-label">
                                <input type="radio" name="tasks[${index}].correctAnswerIndex" value="1" required> ✅ Верный
                            </label>
                            <button type="button" class="btn btn-danger btn-sm remove-answer-btn">✕</button>
                        </div>
                    </div>
                    <button type="button" class="btn btn-outline btn-sm add-answer-btn">+ Добавить вариант ответа</button>
                </div>
            `;
        }

        function createAnswerHTML(taskIndex, answerIndex) {
            return `
                <div class="answer-row">
                    <input type="text" name="tasks[${taskIndex}].answers[${answerIndex}].text" class="form-control answer-input" required placeholder="Текст ответа">
                    <label class="answer-label">
                        <input type="radio" name="tasks[${taskIndex}].correctAnswerIndex" value="${answerIndex}" required> ✅ Верный
                    </label>
                    <button type="button" class="btn btn-danger btn-sm remove-answer-btn">✕</button>
                </div>
            `;
        }
    });
})();