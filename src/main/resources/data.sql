INSERT INTO ORGANIZATION(version,id,name,full_name,inn,kpp,address,phone,is_active)
VALUES ( 0,1,'BELL','BELL INTEGRATOR',123456789,987654,'Уфа ул.Свердлова 92','9371627419',true );

INSERT INTO ORGANIZATION(version,id,name,full_name,inn,kpp,address,phone,is_active)
VALUES ( 0,2,'СБЕР','ОАО СберБанк',456123787,654321,'Уфа ул.Цурюпы 12','937123456',true );

INSERT INTO ORGANIZATION(version,id,name,full_name,inn,kpp,address,phone,is_active)
VALUES ( 0,3,'СБЕР','ЗАО СберБанк',456123789,654321,'Уфа ул.Цурюпы 1','8937123456',false );

INSERT INTO Office(version,id,name,org_id,address,phone,is_active)
VALUES ( 0,1,'Офис Сбера1',2,'Уфа ул.Цурюпы 12','8937987456123',true );

INSERT INTO Office(version,id,name,org_id,address,phone,is_active)
VALUES ( 0,2,'Офис Сбера2',2,'Уфа ул.Цурюпы 122','8937987456123',true );

INSERT INTO Office(version,id,name,org_id,address,phone,is_active)
VALUES ( 0,3,'офис Bell1',1,'Уфа Свердлова','12321321321',true);

INSERT INTO Office(version,id,name,org_id,address,phone,is_active)
VALUES ( 0,4,'офис Bell2',1,'Уфа ул.Цурюпы 15','789456123',true);