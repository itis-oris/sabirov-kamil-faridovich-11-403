<#import "../layout/base.ftlh" as base>
<@base.layout>
    <div class="card" style="max-width: 500px; margin: 2rem auto;">
        <div class="card-header">Регистрация</div>
        <div class="card-body">

            <#if error?has_content>
                <div class="alert alert-danger" style="
                    background-color: #ffeaea;
                    border: 1px solid #e74c3c;
                    color: #c0392b;
                    padding: 1rem;
                    border-radius: 4px;
                    margin-bottom: 1.5rem;
                ">
                    ${error}
                </div>
            </#if>

            <form action="${ctx}/auth/register" method="post">

                <div class="form-group mb-3">
                    <label for="username" style="
                        display: block;
                        margin-bottom: 0.5rem;
                        font-weight: 600;
                        color: #2c3e50;
                    ">Имя *</label>
                    <input
                            type="text"
                            id="username"
                            name="username"
                            class="form-control"
                            value="${form.username!''}"
                            required
                            minlength="3"
                            maxlength="50"
                            placeholder="Введите имя"
                            style="
                                    width: 100%;
                                    padding: 0.75rem;
                                    border: <#if bindingResult?? && bindingResult.hasFieldErrors('username')>2px solid #e74c3c<#else>1px solid #ddd</#if>;
                                    border-radius: 4px;
                                    font-size: 1rem;
                                    "
                    >
                    <#if bindingResult?? && bindingResult.hasFieldErrors('username')>
                        <small style="color: #e74c3c; font-size: 0.875rem; display: block; margin-top: 0.25rem;">
                            ${bindingResult.getFieldError('username').defaultMessage}
                        </small>
                    </#if>
                </div>

                <div class="form-group mb-3">
                    <label for="email" style="
                        display: block;
                        margin-bottom: 0.5rem;
                        font-weight: 600;
                        color: #2c3e50;
                    ">Email *</label>
                    <input
                            type="email"
                            id="email"
                            name="email"
                            class="form-control"
                            value="${form.email!''}"
                            required
                            placeholder="example@email.com"
                            style="
                                    width: 100%;
                                    padding: 0.75rem;
                                    border: <#if bindingResult?? && bindingResult.hasFieldErrors('email')>2px solid #e74c3c<#else>1px solid #ddd</#if>;
                                    border-radius: 4px;
                                    font-size: 1rem;
                                    "
                    >
                    <#if bindingResult?? && bindingResult.hasFieldErrors('email')>
                        <small style="color: #e74c3c; font-size: 0.875rem; display: block; margin-top: 0.25rem;">
                            ${bindingResult.getFieldError('email').defaultMessage}
                        </small>
                    </#if>
                </div>

                <div class="form-group mb-4">
                    <label for="password" style="
                        display: block;
                        margin-bottom: 0.5rem;
                        font-weight: 600;
                        color: #2c3e50;
                    ">Пароль *</label>
                    <input
                            type="password"
                            id="password"
                            name="password"
                            class="form-control"
                            required
                            minlength="6"
                            maxlength="100"
                            placeholder="Минимум 6 символов"
                            style="
                                    width: 100%;
                                    padding: 0.75rem;
                                    border: <#if bindingResult?? && bindingResult.hasFieldErrors('password')>2px solid #e74c3c<#else>1px solid #ddd</#if>;
                                    border-radius: 4px;
                                    font-size: 1rem;
                                    "
                    >
                    <#if bindingResult?? && bindingResult.hasFieldErrors('password')>
                        <small style="color: #e74c3c; font-size: 0.875rem; display: block; margin-top: 0.25rem;">
                            ${bindingResult.getFieldError('password').defaultMessage}
                        </small>
                    </#if>
                    <small style="color: #7f8c8d; font-size: 0.8rem; display: block; margin-top: 0.25rem;">
                        Пароль должен содержать не менее 6 символов
                    </small>
                </div>

                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

                <button
                        type="submit"
                        class="btn btn-success"
                        style="
                        background-color: #27ae60;
                        color: white;
                        padding: 0.75rem 2rem;
                        border: none;
                        border-radius: 4px;
                        font-size: 1rem;
                        font-weight: 500;
                        cursor: pointer;
                        width: 100%;
                        transition: background-color 0.3s;
                    "
                        onmouseover="this.style.backgroundColor='#229954'"
                        onmouseout="this.style.backgroundColor='#27ae60'"
                >
                    Зарегистрироваться
                </button>
            </form>
        </div>
    </div>
</@base.layout>