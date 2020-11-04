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