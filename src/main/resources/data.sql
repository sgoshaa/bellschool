INSERT INTO ORGANIZATION(version, id, name, full_name, inn, kpp, address, phone, is_active)
VALUES (0, 1, 'BELL', 'BELL INTEGRATOR', 123456789, 987654, 'Уфа ул.Свердлова 92', '9371627419', true);

INSERT INTO ORGANIZATION(version, id, name, full_name, inn, kpp, address, phone, is_active)
VALUES (0, 2, 'СБЕР', 'ОАО СберБанк', 456123787, 654321, 'Уфа ул.Цурюпы 12', '937123456', true);

INSERT INTO ORGANIZATION(version, id, name, full_name, inn, kpp, address, phone, is_active)
VALUES (0, 3, 'СБЕР', 'ЗАО СберБанк', 456123789, 654321, 'Уфа ул.Цурюпы 1', '8937123456', false);

INSERT INTO Office(version, id, name, org_id, address, phone, is_active)
VALUES (0, 1, 'Офис Сбера1', 2, 'Уфа ул.Цурюпы 12', '8937987456123', true);

INSERT INTO Office(version, id, name, org_id, address, phone, is_active)
VALUES (0, 2, 'Офис Сбера2', 2, 'Уфа ул.Цурюпы 122', '8937987456123', false);

INSERT INTO Office(version, id, name, org_id, address, phone, is_active)
VALUES (0, 3, 'офис Bell1', 1, 'Уфа Свердлова', '12321321321', true);

INSERT INTO Office(version, id, name, org_id, address, phone, is_active)
VALUES (0, 4, 'офис Bell2', 1, 'Уфа ул.Цурюпы 15', '789456123', true);

INSERT INTO Country(version, id, name, code)
VALUES (0, 1, 'Российская Федерация', 643);

INSERT INTO Country(version, id, name, code)
VALUES (0, 2, 'США', 622);

INSERT INTO Document_type(version, id, name, code)
VALUES (0, 1, 'Паспорт гражданина РФ', 21);

INSERT INTO Document_type(version, id, name, code)
VALUES (0, 2, 'Водительское удостоверение', 22);

INSERT INTO User(version, id, office_id, first_name, second_name, middle_name, position, phone, country_id,is_identified)
VALUES (0, 1, 1, 'Спирин', 'Игорь', 'Александрович', 'программист', '123213213', 1, TRUE);

INSERT INTO Document(version, id, doc_number, doc_date, doc_type_id)
VALUES (0, 1, '232323234', '2022-03-24', 1);

INSERT INTO User(version, id, office_id, first_name, second_name, middle_name, position, phone, country_id,is_identified)
VALUES (0, 2, 1, 'Иванов', 'Иван', 'Иванович', 'водитель', '54568797', 1, TRUE);

INSERT INTO Document(version, id, doc_number, doc_date, doc_type_id)
VALUES (0, 2, '789456123', '2022-02-22', 2);