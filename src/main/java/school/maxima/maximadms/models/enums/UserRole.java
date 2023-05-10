package school.maxima.maximadms.models.enums;

import lombok.Getter;

public enum UserRole {
    USER_ROLE("Пользователь"),
    ADMIN_ROLE("Администратор");

    @Getter
    private final String named;

    UserRole(String named) {
        this.named = named;
    }
}
