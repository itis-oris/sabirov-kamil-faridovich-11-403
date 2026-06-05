# Quest Platform

Веб-платформа для создания и прохождения интерактивных квестов. Администраторы создают квесты с заданиями и вариантами ответов, пользователи проходят квесты, получают email-подтверждения и оставляют отзывы.

## Стек

**Backend:**
- Java 17, Spring Boot 3, Spring MVC (MPA), Spring Security
- Spring Data JPA + JPQL, PostgreSQL
- Redis — кэширование
- OkHttp — отправка email через SMTP
- SpringDoc OpenAPI — Swagger UI

**Frontend:**
- FreeMarker — шаблонизатор
- HTML5 + CSS3 + JavaScript
- AJAX (Fetch API) — асинхронные запросы

## Архитектура

Трёхслойная архитектура Spring MVC:
Controller → Service → Repository → Database
→ DTO/Form Entity  

**Ключевые принципы:**
- Разделение ответственности по слоям
- DTO для передачи данных, Form-классы для валидации
- Конвертеры для маппинга Entity ↔ DTO
- Кастомные репозитории для сложных запросов (Criteria Builder)

## Доменная модель

7 JPA-сущностей:

- **User** — email, username, password (BCrypt)
- **Role** — ROLE_USER, ROLE_ADMIN
- **Quest** — title, description, difficulty, category, связь OneToMany с Task
- **Task** — question, связь ManyToOne с Quest, OneToMany с Answer
- **Answer** — text, correct (boolean), связь ManyToOne с Task
- **Review** — rating (1-5), content, createdAt, связь ManyToOne с User и Quest
- **UserQuestProgress** — score, completed, finishedAt, связь ManyToOne с User и Quest

## Роли и доступ

| Роль | Возможности |
|------|-------------|
| **ROLE_USER** | Прохождение квестов, отзывы, профиль, история прохождений |
| **ROLE_ADMIN** | Полный CRUD квестов, управление заданиями и ответами |

**Spring Security:** form-login, BCrypt, CSRF-защита, разграничение через `.hasRole("ADMIN")`

## REST API

### Quest API
- `GET /api/quests` — список квестов
- `GET /api/quests/{id}` — квест по ID
- `POST /api/quests` — создать (ADMIN)


### Play API (AJAX)
- `POST /api/play/check` — проверить ответ
- `POST /api/play/finish` — завершить квест

**Swagger UI:** http://localhost:8080/quest_platform/swagger-ui/index.html

## Ключевые возможности

**AJAX** — прохождение квеста без перезагрузки страницы.

**Внешний API** — email через OkHttp при завершении квеста.

**Кэширование (Redis)** — список квестов кэшируется через `@Cacheable`, инвалидация через `@CacheEvict`.

**Criteria Builder** — динамическая фильтрация по сложности и категории на `/quests/search`. Запрос строится программно в `QuestRepositoryImpl`.

**Нестандартный SQL** — JPQL с `JOIN` и `HAVING AVG(r.rating) >= :minRating` для популярных квестов на `/quests/popular`.

**Конвертеры** — кастомные Spring-конвертеры для маппинга Entity ↔ DTO и преобразования типов.

**Валидация** — серверная через `@Valid` и `BindingResult`, отображение ошибок в шаблонах.

**Защита форм** — CSRF-токены в каждой форме, POST/Redirect/GET паттерн.

**Динамический JavaScript** — добавление заданий/ответов через делегирование событий, автоматическое обновление индексов.


# Тестирование

Интеграционные тесты через IntelliJ IDEA HTTP Client (`quests-api.http`):

- Аутентификация (регистрация, вход, выход)
- CRUD квестов через REST API
- Прохождение квеста (AJAX)
- Защита эндпоинтов (403)
- Обработка ошибок (404, валидация)
