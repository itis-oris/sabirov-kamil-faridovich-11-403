<#import "../layout/base.ftlh" as base>
<@base.layout>
    <div class="card" style="max-width: 500px; margin: 2rem auto;">
        <div class="card-header text-center">
            <h3>Мой профиль</h3>
        </div>
        <div class="card-body">

            <div class="mb-3">
                <label style="font-weight: 600; color: #7f8c8d;">Имя пользователя</label>
                <p class="form-control-plaintext" style="font-size: 1.1rem; color: #2c3e50;">
                    ${currentUser.username}
                </p>
            </div>

            <div class="mb-4">
                <label style="font-weight: 600; color: #7f8c8d;">Email</label>
                <p class="form-control-plaintext" style="font-size: 1.1rem; color: #2c3e50;">
                    ${currentUser.email}
                </p>
            </div>

            <hr>

            <div class="text-center" style="margin-top: 1.5rem;">
                <a href="${ctx}/profile/history" class="btn btn-outline">📜 История прохождения</a>
            </div>
            <br>
            <div class="text-center">
                <form action="${ctx}/auth/logout" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <button type="submit" class="btn btn-outline">
                        Выйти из аккаунта
                    </button>
                </form>
            </div>

        </div>
    </div>
</@base.layout>