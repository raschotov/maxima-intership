package school.maxima.maximadms.models.enums;

import lombok.Getter;

public enum FieldType {

    TEXT_SINGLE_LINE("Текст однострочный"),
    NUMBER("Число"),
    TEXT_MULTILINE("Текст многострочный"),
    DROP_DOWN_LIST("Выпадающий список");

    @Getter
    private final String named;

    FieldType(String named) {
        this.named = named;
    }
}
