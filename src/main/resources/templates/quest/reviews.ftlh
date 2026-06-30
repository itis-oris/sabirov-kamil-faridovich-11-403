<#import "../layout/base.ftlh" as base>
<@base.layout>
    <div class="card" style="max-width: 800px; margin: 2rem auto;">
        <div class="card-header" style="display: flex; justify-content: space-between; align-items: center;">
            <h3 style="margin: 0;">💬 Отзывы: ${quest.title}</h3>
            <a href="${ctx}/quests/${quest.id}" class="btn btn-outline btn-sm">← Назад к квесту</a>
        </div>
        <div class="card-body">

            <#if successMessage?has_content>
                <div class="alert alert-success" style="background-color: #eaffea; border: 1px solid #27ae60; color: #229954; padding: 1rem; border-radius: 4px; margin-bottom: 1.5rem;">
                    ${successMessage}
                </div>
            </#if>

            <#if isAuthenticated>
                <div style="background: #f8f9fa; padding: 1.5rem; border-radius: 8px; margin-bottom: 2rem; border: 1px solid #dee2e6;">
                    <h4 style="margin-top: 0; margin-bottom: 1rem; color: #2c3e50;">Оставить отзыв</h4>
                    <form action="${ctx}/quests/${quest.id}/reviews" method="post">
                        <div class="form-group mb-3">
                            <label style="font-weight: 600; display: block; margin-bottom: 0.5rem;">Ваша оценка *</label>
                            <select name="rating" class="form-control" required style="max-width: 150px;">
                                <option value="5" <#if reviewForm.rating?? && reviewForm.rating == 5>selected</#if>>⭐⭐⭐⭐⭐ (5)</option>
                                <option value="4" <#if reviewForm.rating?? && reviewForm.rating == 4>selected</#if>>⭐⭐⭐⭐ (4)</option>
                                <option value="3" <#if reviewForm.rating?? && reviewForm.rating == 3>selected</#if>>⭐⭐⭐ (3)</option>
                                <option value="2" <#if reviewForm.rating?? && reviewForm.rating == 2>selected</#if>>⭐⭐ (2)</option>
                                <option value="1" <#if reviewForm.rating?? && reviewForm.rating == 1>selected</#if>>⭐ (1)</option>
                            </select>
                            <#if bindingResult?? && bindingResult.hasFieldErrors('rating')>
                                <small style="color: #e74c3c;">${bindingResult.getFieldError('rating').defaultMessage}</small>
                            </#if>
                        </div>

                        <div class="form-group mb-3">
                            <label style="font-weight: 600; display: block; margin-bottom: 0.5rem;">Ваш отзыв *</label>
                            <textarea name="content" class="form-control" rows="4" required placeholder="Расскажите о своих впечатлениях...">${reviewForm.content!''}</textarea>
                            <#if bindingResult?? && bindingResult.hasFieldErrors('content')>
                                <small style="color: #e74c3c;">${bindingResult.getFieldError('content').defaultMessage}</small>
                            </#if>
                        </div>

                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <button type="submit" class="btn btn-primary">Отправить отзыв</button>
                    </form>
                </div>
            <#else>
                <div style="background: #fff3cd; padding: 1rem; border-radius: 4px; margin-bottom: 2rem; border: 1px solid #ffeeba; color: #856404;">
                    <strong>Внимание:</strong> Чтобы оставить отзыв, необходимо <a href="${ctx}/auth/login" style="color: #856404; text-decoration: underline;">войти в систему</a>.
                </div>
            </#if>

            <h4 style="color: #2c3e50; border-bottom: 2px solid #ecf0f1; padding-bottom: 0.5rem; margin-bottom: 1rem;">
                Все отзывы (${reviews?size})
            </h4>

            <#if reviews?has_content>
                <#list reviews as review>
                    <div style="background: white; border: 1px solid #dee2e6; border-radius: 8px; padding: 1.5rem; margin-bottom: 1rem;">
                        <div style="display: flex; justify-content: space-between; align-items: flex-start; margin-bottom: 0.75rem;">
                            <div>
                                <strong style="color: #2c3e50; font-size: 1.1rem;">${review.user.username}</strong>
                            </div>
                            <div style="color: #f39c12; font-size: 1.2rem; letter-spacing: 2px;">
                                <#list 1..review.rating as i>★</#list>

                            </div>
                        </div>
                        <p style="color: #34495e; margin: 0; line-height: 1.6; white-space: pre-wrap;">
                            ${review.content}
                        </p>
                    </div>
                </#list>
            <#else>
                <div style="text-align: center; padding: 2rem; color: #7f8c8d;">
                    <p style="font-size: 1.1rem;">Пока нет ни одного отзыва.</p>
                    <p>Будьте первым, кто поделится впечатлениями!</p>
                </div>
            </#if>
        </div>
    </div>
</@base.layout>