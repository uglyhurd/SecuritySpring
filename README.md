# SecuritySpring

Remember SpringSecurity


ПРОВЕРКИ ДОСТУПА (sec:authorize)

Авторизация:

html
<!-- Авторизован ли пользователь -->
<div sec:authorize="isAuthenticated()">
    Видно только залогиненным
</div>

<div sec:authorize="isAnonymous()">
    Видно только гостям
</div>

<div sec:authorize="!isAuthenticated()">
    Видно только неавторизованным
</div>
Роли:

html
<!-- Проверка одной роли -->
<div sec:authorize="hasRole('ADMIN')">
    Только для админов
</div>

<!-- Любая из ролей -->
<div sec:authorize="hasAnyRole('ADMIN', 'MODERATOR')">
    Для админов или модераторов
</div>

<!-- Все перечисленные роли -->
<div sec:authorize="hasRole('ADMIN') and hasRole('MANAGER')">
    Только для админов, которые также менеджеры
</div>
Права (authorities):

html
<!-- Конкретное право -->
<div sec:authorize="hasAuthority('CREATE_USER')">
    Только с правом создания пользователей
</div>

<!-- Любое из прав -->
<div sec:authorize="hasAnyAuthority('READ', 'WRITE')">
    С правом чтения или записи
</div>
Дополнительные проверки:

html
<!-- Полная аутентификация (не через remember-me) -->
<div sec:authorize="isFullyAuthenticated()">
    Не через "запомнить меня"
</div>

<!-- Только через remember-me -->
<div sec:authorize="isRememberMe()">
    Только через "запомнить меня"
</div>

<!-- По IP адресу -->
<div sec:authorize="hasIpAddress('192.168.1.0/24')">
    Только с определенного IP
</div>
👤 ПОЛУЧЕНИЕ ДАННЫХ (sec:authentication)

Основная информация:

html
<!-- Имя пользователя (логин) -->
<p>Welcome, <span sec:authentication="name"></span></p>

<!-- Все authorities (роли/права) -->
<p>Your roles: <span sec:authentication="authorities"></span></p>
Детали аутентификации:

html
<!-- Principal объект (твой PersonDetails) -->
<p>Username: <span sec:authentication="principal.username"></span></p>
<p>Email: <span sec:authentication="principal.email"></span></p>
<p>Full name: <span sec:authentication="principal.fullName"></span></p>

<!-- Детали сессии -->
<p>Remote IP: <span sec:authentication="details.remoteAddress"></span></p>
<p>Session ID: <span sec:authentication="details.sessionId"></span></p>

<!-- Credentials (обычно [PROTECTED]) -->
<p>Password: <span sec:authentication="credentials"></span></p>
