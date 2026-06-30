<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quest Platform</title>
    <link rel="stylesheet" href="${ctx}/css/style.css">
</head>
    <div class="error-container" style="text-align: center; padding: 4rem 2rem;">
        <div class="error-icon" style="
            font-size: 6rem;
            color: #e74c3c;
            margin-bottom: 1rem;
        ">💥</div>

        <h1 style="color: #2c3e50; margin-bottom: 1rem;">Внутренняя ошибка сервера</h1>

        <p style="color: #7f8c8d; font-size: 1.1rem; max-width: 500px; margin: 0 auto 2rem;">
            На сервере произошла непредвиденная ошибка.
            Мы уже работаем над этим. Пожалуйста, попробуйте позже.
        </p>

        <div style="
            background: #ffeaea;
            border-left: 4px solid #e74c3c;
            padding: 1rem 1.5rem;
            text-align: left;
            max-width: 600px;
            margin: 0 auto 2rem;
            border-radius: 4px;
        ">
            <strong style="color: #c0392b;">Детали ошибки:</strong>
            <#if message??>
                <p style="margin: 0.5rem 0 0; color: #c0392b; font-family: monospace;">
                    ${message}
                </p>
            </#if>
        </div>

        <div>
            <button onclick="location.reload()" class="btn btn-primary" style="margin-right: 1rem;">
                🔄 Обновить страницу
            </button>
            <a href="${ctx}/" class="btn btn-outline">
                🏠 На главную
            </a>
        </div>
    </div>
</html>