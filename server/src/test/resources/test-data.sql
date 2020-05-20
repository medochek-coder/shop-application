insert into product (id, name, description, price, price_sale)
values (1, 'Пирог', 'Вкусный', 2.0, 1.5);

insert into product (id, name, description, price, price_sale)
values (2, 'Пирог 2', 'Вкусный 2', 3.0, 2.5);

insert into product (id, name, description, price)
values (3, 'Пирог 3', 'Вкусный 3', 3.0);

insert into basket (id)
values (1);

insert into basket (id)
values (2);

insert into basket_row (id, count, product_id, basket_id)
values (99, 2, 2, 2);

insert into basket (id)
values (3);

insert into basket_row (id, count, product_id, basket_id)
values (98, 2, 2, 3);

insert into basket (id)
values (44);

insert into basket (id)
values (45);

insert into basket_row (id, count, product_id, basket_id)
values (97, 2, 2, 45);

insert into basket (id)
values (46);

insert into basket_row (id, count, product_id, basket_id)
values (96, 2, 2, 46);

insert into basket (id)
values (47);
