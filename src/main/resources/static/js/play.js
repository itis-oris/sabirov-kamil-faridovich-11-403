(function() {
    'use strict';

    const questDataScript = document.getElementById('quest-data');
    if (!questDataScript) {
        console.error('Данные квеста не найдены!');
        return;
    }

    const data = JSON.parse(questDataScript.textContent);
    const { questId, ctx, csrfHeader, csrfToken, tasks } = data;

    let currentTaskIndex = 0;
    let score = 0;

    const progressBar = document.getElementById('progress-fill');
    const questionText = document.getElementById('question-text');
    const answersContainer = document.getElementById('answers-container');
    const gameArea = document.getElementById('game-area');
    const resultArea = document.getElementById('result-area');
    const finalScore = document.getElementById('final-score');
    const totalTasks = document.getElementById('total-tasks');

    function showTask(task) {
        const progressPercent = (currentTaskIndex / tasks.length) * 100;
        progressBar.style.width = progressPercent + '%';
        questionText.textContent = task.question;
        answersContainer.innerHTML = '';

        task.answers.forEach(ans => {
            const btn = document.createElement('button');
            btn.className = 'btn btn-outline';
            btn.textContent = ans.text;
            btn.onclick = () => handleAnswerClick(btn, ans.id);
            answersContainer.appendChild(btn);
        });
    }

    function handleAnswerClick(btn, answerId) {
        document.querySelectorAll('#answers-container button').forEach(b => {
            b.disabled = true;
        });

        fetch(ctx + '/api/play/check', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                [csrfHeader]: csrfToken
            },
            body: JSON.stringify({ answerId: answerId })
        })
            .then(response => response.json())
            .then(result => {
                if (result.correct) {
                    btn.className = 'btn btn-success';
                    btn.textContent += ' ✅';
                    score++;
                } else {
                    btn.className = 'btn btn-danger';
                    btn.textContent += ' ❌';
                }

                setTimeout(() => {
                    currentTaskIndex++;
                    if (currentTaskIndex < tasks.length) {
                        showTask(tasks[currentTaskIndex]);
                    } else {
                        finishGame();
                    }
                }, 1000);
            })
            .catch(err => console.error('Ошибка проверки ответа:', err));
    }

    function finishGame() {
        gameArea.style.display = 'none';
        progressBar.style.width = '100%';

        fetch(ctx + '/api/play/finish', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                [csrfHeader]: csrfToken
            },
            body: JSON.stringify({ questId: questId, score: score })
        })
            .then(response => response.json())
            .then(() => {
                finalScore.textContent = score;
                totalTasks.textContent = tasks.length;
                resultArea.style.display = 'block';
            })
            .catch(err => console.error('Ошибка завершения квеста:', err));
    }

    document.addEventListener('DOMContentLoaded', () => {
        totalTasks.textContent = tasks.length;
        if (tasks.length > 0) {
            showTask(tasks[0]);
        } else {
            questionText.textContent = 'В этом квесте нет заданий';
        }
    });
})();