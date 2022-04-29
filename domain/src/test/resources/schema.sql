CREATE TABLE IF NOT EXISTS Organization (
        version    INTEGER NOT NULL         COMMENT 'Служебное поле hibernate',
        id          INTEGER                 COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
        name        VARCHAR(50) NOT NULL    COMMENT 'Название организации',
        full_name   VARCHAR(255) NOT NULL   COMMENT 'Полное название',
        inn         INTEGER NOT NULL        COMMENT 'ИНН организации',
        kpp         INTEGER NOT NULL        COMMENT 'КПП организации',
        address     VARCHAR  NOT NULL       COMMENT 'Адрес организации',
        phone       VARCHAR                 COMMENT 'Номер телефона организации',
        is_active   BOOLEAN
    );
COMMENT ON TABLE Organization IS 'Организация';

CREATE TABLE IF NOT EXISTS Office (
    version    INTEGER NOT NULL          COMMENT 'Служебное поле hibernate',
    id          INTEGER                  COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(255) NOT NULL    COMMENT 'Название офиса',
    org_id      INTEGER      NOT NULL    COMMENT 'Организация',
    address     VARCHAR(255) NOT NULL    COMMENT 'Адрес офиса',
    phone       VARCHAR(25)              COMMENT 'Номер телефона офиса',
    is_active   BOOLEAN
);
COMMENT ON TABLE Office  IS 'Офис';

CREATE TABLE IF NOT EXISTS User (
    version    INTEGER NOT NULL                 COMMENT 'Служебное поле hibernate',
    id                  INTEGER                 COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    office_id           INTEGER NOT NULL        COMMENT 'Офис',
    first_name          VARCHAR(255) NOT NULL   COMMENT 'Имя',
    second_name         VARCHAR(255)            COMMENT 'Фамилия',
    middle_name         VARCHAR(255)            COMMENT 'Отчество',
    position            VARCHAR(255) NOT NULL   COMMENT 'Должность',
    phone               VARCHAR(25)             COMMENT 'Номер телефона пользователя',
    country_id          INTEGER     NOT NULL    COMMENT 'Гражданство',
    is_identified       BOOLEAN                 COMMENT 'идентифицируется'
    );
COMMENT ON TABLE User  IS 'Пользователь';

CREATE TABLE IF NOT EXISTS Document (
    version        INTEGER NOT NULL     COMMENT 'Служебное поле hibernate',
    id                  INTEGER         COMMENT 'Уникальный идентификатор' PRIMARY KEY,
    doc_number          VARCHAR         COMMENT 'номер документа',
    doc_date            DATE            COMMENT 'Дата выдачи документа',
    doc_type_id         INTEGER         COMMENT 'Тип документа'
);
COMMENT ON TABLE Document  IS 'Документ';

CREATE TABLE IF NOT EXISTS Country (
    version    INTEGER NOT NULL    COMMENT 'Служебное поле hibernate',
    id      INTEGER                COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    name    VARCHAR(100) NOT NULL  COMMENT 'Название страны',
    code    INTEGER                COMMENT 'Код страны'
);
COMMENT ON TABLE Country IS 'Страна';

CREATE TABLE IF NOT EXISTS Document_type (
    version    INTEGER NOT NULL    COMMENT 'Служебное поле hibernate',
    id      INTEGER                COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    name    VARCHAR(100) NOT NULL  COMMENT 'Название документа',
    code    INTEGER                COMMENT 'Код страны'
);
COMMENT ON TABLE Document_type IS 'Тип документа';

CREATE INDEX IX_DOCUMENT_ID  ON Document(id);
-- ALTER TABLE User ADD FOREIGN KEY (doc_id) REFERENCES Document(id);

CREATE INDEX IX_OFFICE_ID  ON Office(id);
ALTER TABLE User ADD FOREIGN KEY (office_id) REFERENCES Office(id);

CREATE INDEX IX_ORGANIZATION_ID ON Organization(id);
ALTER TABLE Office ADD FOREIGN KEY (org_id) REFERENCES Organization(id);

CREATE INDEX IX_COUNTRY_ID ON Country(id);
ALTER TABLE User ADD FOREIGN KEY (country_id) REFERENCES Country(id);

CREATE INDEX IX_DOCUMENT_TYPE_ID  ON Document_type(id);
ALTER TABLE Document ADD FOREIGN KEY (doc_type_id) REFERENCES Document_type(id);