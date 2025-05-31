
insert into product_for_individual (name, size, price) values ('name1', '123', '123');
insert into urls (id, urls) values
(1, "http://212.192.217.207:8090/api/images/fcab1070-16b4-4d69-8294-7b89aa0aa88e_01.jpg");
insert into urls (id, urls) values
    (1, "http://212.192.217.207:8090/api/images/fcab1070-16b4-4d69-8294-7b89aa0aa88e_01.jpg");

insert into product_for_individual (name, size, price) values ('name2', '123', '123');
insert into urls (id, urls) values
    (2, "http://212.192.217.207:8090/api/images/a669d41b-8f96-4423-a6c6-c85cd5f5214f_лежебока 1.jpg");
insert into urls (id, urls) values
    (2, "http://212.192.217.207:8090/api/images/c09613c5-7189-4fa6-bf47-1c0194fa5ee9_лежебока 2.jpg");


insert into product_for_individual (name, size, price) values ('name3', '123', '123');
insert into urls (id, urls) values
    (3, "http://212.192.217.207:8090/api/images/54d2e3cf-4d85-4812-a33b-624714c0ea48_Клик-клак 1.jpg");
insert into urls (id, urls) values
    (3, "http://212.192.217.207:8090/api/images/07d48608-a799-486d-8e02-8c5528e2a5a1_Клик-клак 2.jpg");

insert into product_for_individual (name, size, price) values ('name4', '123', '123');insert into urls (id, urls) values
    (4, "http://212.192.217.207:8090/api/images/68549eb7-9723-4037-8012-f14d63e55b39_Венеция 1.jpg");
insert into urls (id, urls) values
    (4, "http://212.192.217.207:8090/api/images/9f14221a-c0cf-4721-813b-f7a23c4b5b0a_Венеция 2.jpg");

-- insert into urls (id, urls) values
-- (1, 'http://localhost:8090/api/images/289a5ddc-4b2e-465e-b759-ff50e6bca105_pngtree-picture-of-a-blue-bird-on-a-black-background-image_2937385.jpg'),
-- (2, 'http://localhost:8090/api/images/289a5ddc-4b2e-465e-b759-ff50e6bca105_pngtree-picture-of-a-blue-bird-on-a-black-background-image_2937385.jpg'),
-- (3, 'http://localhost:8090/api/images/289a5ddc-4b2e-465e-b759-ff50e6bca105_pngtree-picture-of-a-blue-bird-on-a-black-background-image_2937385.jpg'),
-- (4, 'http://localhost:8090/api/images/289a5ddc-4b2e-465e-b759-ff50e6bca105_pngtree-picture-of-a-blue-bird-on-a-black-background-image_2937385.jpg');


insert into fabric (
    name,
    city,
    address,
    point_on_the_map,
    phone_number,
    email) values
('Мебельная фабрика "Гермес-Плюс"',
'г. Салават',
'г. Салават, ул. Якутова, д. 2А',
'53.385670, 55.906382',
'+7 (917) 342-93-80',
'germes_plus@gmail.com');

insert into point_of_sale (
name,
address,
point_on_the_map,
phone_number,
email,
opening_hours) values
('Торговый центр "Парус"',
'г. Уфа, ул. Трамвайная, д. 2, ТЦ "Парус", 1 этаж',
'54.796703, 56.074847',
'+7 (123) 456-78-90',
'info@magazin.ru',
'пн-вс, 10:00-20:00');


INSERT INTO germesplus.product_for_individual (armrests, article, basis, box, characteristics, configuration, depth,
                                               description, filling, mechanism, name, overall_dimensions, planting,
                                               price, seat_depth, size, sleeping_space)
VALUES (null, '238790', 'Металлокаркас', 'Есть', null, 'Прямой', '1000 мм', 'Диван крутой тралалеро тралала', 'НБП',
        'Тройной выкатной', 'Диван Габидул', '2200 мм', '650 мм', 50000, '670 мм', '2200 мм', '1900');


INSERT INTO germesplus.product_for_individual (armrests, article, basis, box, characteristics, configuration, depth,
                                               description, filling, mechanism, name, overall_dimensions, planting,
                                               price, seat_depth, size, sleeping_space)
VALUES (null, '999098', 'Металлокаркас', 'Отсутсвует', null, 'Угловой', '900 мм', 'Диван для балерины Капучины', 'ППУ',
        'Еврокнижка', 'Диван Глорбо Фрутодрило', '2350 мм', '700 мм', 123000, '900 мм', '123', '1900 мм');

INSERT INTO germesplus.product_for_individual (armrests, article, basis, box, characteristics, configuration, depth,
                                               description, filling, mechanism, name, overall_dimensions, planting,
                                               price, seat_depth, size, sleeping_space)
VALUES (null, '48574363', 'Металлокаркас', 'Есть', null, 'Прямой', '950 мм', 'Диван для Бобардиро Крокодило', 'ППУ',
        'Аккордеон', 'Диван Аккордоен', '1650 мм', '90 мм', 123498, '899 мм', null, '2000 мм');