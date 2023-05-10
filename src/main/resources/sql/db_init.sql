CREATE TABLE users
(
    id          SERIAL PRIMARY KEY,
    login       VARCHAR(255) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    created_at  TIMESTAMP    NOT NULL,
    modified_at TIMESTAMP,
    removed_at  TIMESTAMP,
    user_role   VARCHAR(255) NOT NULL DEFAULT 'USER_ROLE',
    first_name  VARCHAR(255) NOT NULL,
    last_name   VARCHAR(255) NOT NULL,
    sur_name    VARCHAR(255) NOT NULL,
    telegram    VARCHAR(255),
    email       VARCHAR(255) UNIQUE,
    last_visit  TIMESTAMP    NOT NULL,
    is_removed  BOOLEAN               DEFAULT FALSE
);

CREATE TABLE document_templates
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    created_at  TIMESTAMP    NOT NULL,
    modified_at TIMESTAMP,
    removed_at  TIMESTAMP,
    version     INTEGER      NOT NULL,
    is_removed  BOOLEAN DEFAULT FALSE
);

CREATE TABLE document_fields
(
    id                    SERIAL PRIMARY KEY,
    name                  VARCHAR(255) NOT NULL,
    created_at            TIMESTAMP    NOT NULL,
    modified_at           TIMESTAMP,
    removed_at            TIMESTAMP,
    type                  VARCHAR(255) NOT NULL,
    placeholder           VARCHAR(255) NOT NULL,
    default_value         VARCHAR(255) NOT NULL,
    document_templates_id INTEGER REFERENCES document_templates (id),
    is_removed            BOOLEAN DEFAULT FALSE

);

CREATE TABLE credentials
(
    id              SERIAL PRIMARY KEY,
    text            TEXT         NOT NULL,
    version         INTEGER      NOT NULL,
    created_at      TIMESTAMP    NOT NULL,
    modified_at     TIMESTAMP,
    removed_at      TIMESTAMP,
    credential_type VARCHAR(255) NOT NULL,
    is_removed      BOOLEAN DEFAULT FALSE
);

CREATE TABLE contractors
(
    id            SERIAL PRIMARY KEY,
    sur_name      VARCHAR(255) NOT NULL,
    first_name    VARCHAR(255) NOT NULL,
    last_name     VARCHAR(255) NOT NULL,
    b_date        TIMESTAMP    NOT NULL,
    created_at    TIMESTAMP    NOT NULL,
    modified_at   TIMESTAMP,
    removed_at    TIMESTAMP,
    phone         VARCHAR(255) NOT NULL,
    telegram      VARCHAR(255),
    email         VARCHAR(255) NOT NULL,
    credential_id INTEGER REFERENCES credentials (id),
    country       VARCHAR(255) NOT NULL,
    is_removed    BOOLEAN DEFAULT FALSE
);

CREATE TABLE comments
(
    id            SERIAL PRIMARY KEY,
    text          TEXT      NOT NULL,
    created_at    TIMESTAMP NOT NULL,
    modified_at   TIMESTAMP,
    removed_at    TIMESTAMP,
    contractor_id INTEGER REFERENCES contractors (id),
    is_removed    BOOLEAN DEFAULT FALSE
);

CREATE TABLE documents
(
    id                       SERIAL PRIMARY KEY,
    name                     VARCHAR(255) NOT NULL,
    created_at               TIMESTAMP    NOT NULL,
    modified_at              TIMESTAMP,
    removed_at               TIMESTAMP,
    internal_registry_number VARCHAR(255) NOT NULL,
    template_id              INTEGER REFERENCES document_templates (id),
    contractor_id            INTEGER REFERENCES contractors (id),
    user_id                  INTEGER REFERENCES users (id),
    is_removed               BOOLEAN DEFAULT FALSE
);

CREATE TABLE files
(
    id            SERIAL PRIMARY KEY,
    incoming_file BYTEA     NOT NULL,
    created_at    TIMESTAMP NOT NULL,
    modified_at   TIMESTAMP,
    removed_at    TIMESTAMP,
    document_id   INTEGER REFERENCES documents (id),
    is_removed    BOOLEAN DEFAULT FALSE
);
