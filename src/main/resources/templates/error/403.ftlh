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
            color: #f39c12;
            margin-bottom: 1rem;
        ">🔒</div>

        <h1 style="color: #2c3e50; margin-bottom: 1rem;">Доступ запрещён</h1>

        <p style="color: #7f8c8d; font-size: 1.1rem; max-width: 500px; margin: 0 auto 2rem;">
            У вас недостаточно прав для просмотра этой страницы.
            Если вы считаете, что это ошибка, обратитесь к администратору.
        </p>

        <#if spring.security.authentication?.authenticated??>
            <div style="margin-bottom: 2rem;">
                <a href="${ctx}/profile" class="btn btn-outline">
                    👤 Мой профиль
                </a>
            </div>
        <#else>
            <div style="margin-bottom: 2rem;">
                <a href="${ctx}/auth/login" class="btn btn-primary">
                    🔐 Войти
                </a>
            </div>
        </#if>

        <a href="${ctx}/" class="btn btn-outline">
            🏠 На главную
        </a>
    </div>
</html>