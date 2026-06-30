<#import "../layout/base.ftlh" as base>
<@base.layout>
    <#if loginMessage?has_content>
        <div class="alert alert-error" style="max-width: 500px;margin: 2rem auto; background-color: #e06767; color: #9e0d0d; padding: 1rem; border-radius: 4px; margin-bottom: 1.5rem;">
            ${loginMessage}
        </div>
    </#if>
    <div class="card" style="max-width: 500px; margin: 2rem auto;">
        <div class="card-header">Вход</div>
        <div class="card-body">
            <form action="${ctx}/auth/login" method="post">
                <div class="form-group">
                    <label>Email</label>
                    <input type="text" name="username" class="form-control" required>
                </div>
                <div class="form-group">
                    <label>Пароль</label>
                    <input type="password" name="password" class="form-control" required>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <button type="submit" class="btn btn-primary">Войти</button>
                <a href="${ctx}/auth/register">Зарегестрироваться</a>
            </form>
        </div>
    </div>
</@base.layout>