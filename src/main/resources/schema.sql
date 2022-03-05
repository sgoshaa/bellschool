CREATE TABLE IF NOT EXISTS Organizations (
        id          INTEGER                 COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
        name        VARCHAR(50) NOT NULL    COMMENT 'Название организации',
        full_name   VARCHAR(255) NOT NULL   COMMENT 'Полное название',
        inn         INTEGER NOT NULL        COMMENT 'ИНН организации',
        kpp         INTEGER NOT NULL        COMMENT 'КПП организации',
        address     VARCHAR  NOT NULL       COMMENT 'Адрес организации',
        phone       VARCHAR                 COMMENT 'Номер телефона организации',
        is_active   BOOLEAN
    );
COMMENT ON TABLE Organizations IS 'Организации';

CREATE TABLE IF NOT EXISTS Offices (
    id          INTEGER                  COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(255) NOT NULL    COMMENT 'Название офиса',
    org_id      INTEGER      NOT NULL    COMMENT 'Организация',
    address     VARCHAR(255) NOT NULL    COMMENT 'Адрес офиса',
    phone       VARCHAR(25)              COMMENT 'Номер телефона офиса',
    is_active   BOOLEAN
);
COMMENT ON TABLE Offices  IS 'Офисы';

CREATE TABLE IF NOT EXISTS Users (
    id                  INTEGER                 COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    office_id            INTEGER NOT NULL       COMMENT 'Офис',
    first_name          VARCHAR(255) NOT NULL   COMMENT 'Имя',
    second_name         VARCHAR(255)            COMMENT 'Фамилия',
    middle_name         VARCHAR(255)            COMMENT 'Отчество',
    position            VARCHAR(255) NOT NULL   COMMENT 'Должность',
    phone               VARCHAR(25)             COMMENT 'Номер телефона пользователя',
    doc_id              INTEGER NOT NULL        COMMENT 'Документ'
    );
COMMENT ON TABLE Users  IS 'Пользователи';

CREATE TABLE IF NOT EXISTS Documents (
    id                  INTEGER         COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    doc_code            INTEGER         COMMENT 'Код документа',
    doc_name            VARCHAR(100)    COMMENT 'Название документа',
    doc_number          VARCHAR         COMMENT 'номер документа',
    doc_date            DATE            COMMENT 'Дата выдачи документа',
    citizenship_сode    VARCHAR(25)     COMMENT 'Гражданство'
);
COMMENT ON TABLE Documents  IS 'Документы';

CREATE TABLE IF NOT EXISTS Countries (
    id      INTEGER                COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    name    VARCHAR(100) NOT NULL  COMMENT 'Название страны',
    code    INTEGER                COMMENT 'Код страны'
);
COMMENT ON TABLE Countries  IS 'Страны';

