package school.maxima.maximadms.models.enums;

import lombok.Getter;

public enum CredentialType {
    
    NATURAL_PERSON("Физическое лицо"),
    LEGAL_PERSON("Юридическое лицо");

    @Getter
    private final String named;

    CredentialType(String named) {
        this.named = named;
    }
}
