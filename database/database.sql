use cnweb_20201;
create table user(
	id int not null primary key auto_increment,
    account varchar(50) not null,
    password varchar(50) not null,
    fullName varchar(50) not null,
    phone varchar(50) null,
    email varchar(50) null,
    address varchar(255) null
);
create table product(
	id int not null primary key auto_increment,
    name varchar(255) not null,
    description varchar(255) null,
    image varchar(255) null,
    price int not null,
    category varchar(50) not null
);
create table orders(
	id int not null primary key auto_increment,
    userId int not null,
    type int not null,
    time timestamp null
);
create table productInOrder(
    orderId int not null,
    productId int not null,
    quantity int not null,
    primary key(orderId, productId)
);
alter table orders add constraint fk_orders_user foreign key (userId) references user(id);
alter table productInOrder add constraint fk_productInOrder_orders foreign key(orderId) references orders(id);
alter table productInOrder add constraint fk_productInOrder_product foreign key(productId) references product(id);

insert into user(account, password, fullName, phone, email, address)
values ("quyenhaha","quyenhaha","le ngoc quyen","0357000598","quyen@gmail.com","Ha Noi");
insert into user(account, password, fullName, phone, email, address)
values ("quyenhihi","quyenhihi","ngoc quyen le","0357000598","quyen@gmail.com","Ha Noi");
insert into user(account, password, fullName, phone, email, address)
values ("quyenhuhu","quyenhuhu","quyen le ngoc","0357000598","quyen@gmail.com","Ha Noi");
alter table orders add personTakeOrder varchar(55) null;
alter table orders add phone varchar(55) null;
alter table orders add address varchar(255) null;
insert into product(name, description, image, price, category) values("game1","game1","game1", 1000,"hanh-dong");
insert into product(name, description, image, price, category) values("game2","game2","game2", 1000,"hanh-dong");
insert into product(name, description, image, price, category) values("game3","game3","game3", 1000,"the-thao");
insert into product(name, description, image, price, category) values("game1","game1","game1", 1000,"nhap-vai");
insert into orders(userId, type, time, personTakeOrder, phone, address) 
	values(1, 0, '2020-12-31', "NHA", "23356898", "Ha Noi");
insert into orders(userId, type, time, personTakeOrder, phone, address) 
	values(2, 0, '2020-12-30', "LNQ", "6565656898", "Ha Noi");
insert into orders(userId, type, time, personTakeOrder, phone, address) 
	values(10, 0, '2019-12-31', "NQH", "6693366898", "Ha Noi");
insert into orders(userId, type, time, personTakeOrder, phone, address) 
	values(12, 1, '2019-01-01', "Nguyen Quang Hung", "123456789", "Gia Lam - Ha Noi");
insert into productInOrder(orderId, productId, quantity) value(1,1,3);
insert into productInOrder(orderId, productId, quantity) value(1,2,1);
insert into productInOrder(orderId, productId, quantity) value(1,3,1);
insert into productInOrder(orderId, productId, quantity) value(3,3,1);
insert into productInOrder(orderId, productId, quantity) value(3,4,1);
insert into productInOrder(orderId, productId, quantity) value(4,4,1);
insert into productInOrder(orderId, productId, quantity) value(2,1,1);