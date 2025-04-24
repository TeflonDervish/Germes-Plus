
insert into product_for_individual (name, size, price) values ('name', '123', '123');
insert into product_for_individual (name, size, price) values ('name', '123', '123');
insert into product_for_individual (name, size, price) values ('name', '123', '123');
insert into product_for_individual (name, size, price) values ('name', '123', '123');

insert into urls (id, urls) values (1, 'http://localhost:8090/api/images/289a5ddc-4b2e-465e-b759-ff50e6bca105_pngtree-picture-of-a-blue-bird-on-a-black-background-image_2937385.jpg'),
(2, 'http://localhost:8090/api/images/289a5ddc-4b2e-465e-b759-ff50e6bca105_pngtree-picture-of-a-blue-bird-on-a-black-background-image_2937385.jpg'),
(3, 'http://localhost:8090/api/images/289a5ddc-4b2e-465e-b759-ff50e6bca105_pngtree-picture-of-a-blue-bird-on-a-black-background-image_2937385.jpg'),
(4, 'http://localhost:8090/api/images/289a5ddc-4b2e-465e-b759-ff50e6bca105_pngtree-picture-of-a-blue-bird-on-a-black-background-image_2937385.jpg');


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