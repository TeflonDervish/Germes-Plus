
insert into product_for_individual (name, size, price) values ('name1', '123', '123');
insert into urls (id, urls) values
(1, "http://212.192.217.207:8090/api/images/99433a72-7eab-4e87-975c-1acbd093354a_2025-05-11 23.01.34.jpg");
insert into urls (id, urls) values
    (1, "http://212.192.217.207:8090/api/images/885aad6e-7ab5-43a3-a60c-c6f2c1d9616d_2025-05-11 23.01.44.jpg");

insert into product_for_individual (name, size, price) values ('name', '123', '123');
insert into urls (id, urls) values
    (2, "http://212.192.217.207:8090/api/images/a669d41b-8f96-4423-a6c6-c85cd5f5214f_лежебока 1.jpg");
insert into urls (id, urls) values
    (2, "http://212.192.217.207:8090/api/images/c09613c5-7189-4fa6-bf47-1c0194fa5ee9_лежебока 2.jpg");


insert into product_for_individual (name, size, price) values ('name', '123', '123');
insert into urls (id, urls) values
    (3, "http://212.192.217.207:8090/api/images/54d2e3cf-4d85-4812-a33b-624714c0ea48_Клик-клак 1.jpg");
insert into urls (id, urls) values
    (3, "http://212.192.217.207:8090/api/images/07d48608-a799-486d-8e02-8c5528e2a5a1_Клик-клак 2.jpg");

insert into product_for_individual (name, size, price) values ('name', '123', '123');insert into urls (id, urls) values
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