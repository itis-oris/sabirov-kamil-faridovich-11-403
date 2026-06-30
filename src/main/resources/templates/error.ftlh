<#import "layout/base.ftlh" as base>
<@base.layout>
    <div class="error-container" style="text-align: center; padding: 4rem 2rem;">
        <div class="error-icon" style="
            font-size: 6rem;
            color: #e74c3c;
            margin-bottom: 1rem;
        ">⚠️</div>

        <h1 style="color: #2c3e50; margin-bottom: 1rem;">
            <#if status??>
                <#if status == 404>Страница не найдена
                <#elseif status == 403>Доступ запрещён
                <#elseif status == 500>Внутренняя ошибка сервера
                <#else>Ошибка ${status}
                </#if>
            <#else>
                Произошла ошибка
            </#if>
        </h1>

        <p style="color: #7f8c8d; font-size: 1.1rem; max-width: 500px; margin: 0 auto 2rem;">
            <#if message??>
                ${message}
            <#else>
                К сожалению, что-то пошло не так. Попробуйте обновить страницу или вернуться на главную.
            </#if>
        </p>

        <div style="margin-top: 2rem;">
            <a href="${ctx}/" class="btn btn-primary" style="margin-right: 1rem;">
                🏠 На главную
            </a>
            <button onclick="history.back()" class="btn btn-outline">
                ← Назад
            </button>
        </div>
    </div>
</@base.layout>