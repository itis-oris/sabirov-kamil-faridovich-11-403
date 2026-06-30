<nav class="navbar">
    <a class="navbar-brand" href="${ctx}/">QuestPlatform</a>
    <ul class="nav-links">
        <li><a href="${ctx}/quests/popular">Популярное</a></li>
        <li><a href="${ctx}/quests/search">🔍 Поиск</a></li>

        <#if isAuthenticated>
            <li><a href="${ctx}/profile">Профиль</a></li>

            <#if isAdmin>
                <li><a href="${ctx}/admin/quests">Админка</a></li>
            </#if>
        <#else>
            <li><a href="${ctx}/auth/login">Вход</a></li>
        </#if>
    </ul>
</nav>