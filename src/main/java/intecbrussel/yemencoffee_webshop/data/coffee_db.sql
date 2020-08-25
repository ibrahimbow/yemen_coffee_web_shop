create table administrators
(
    id             int auto_increment
        primary key,
    admin_email    varchar(255) null,
    admin_fullname varchar(255) null,
    admin_password varchar(255) null,
    admin_username varchar(255) null,
    constraint UK_3ja8q5wbw6lqvfcvuq8p9kymi
        unique (admin_username),
    constraint UK_o9p91xfmo8xj9uf0hyxjk1rd
        unique (admin_email)
);
-- insert the admin info to be able to use CMS
Insert into administrators ( admin_email, admin_fullname, admin_password, admin_username)
values ( 'bart@b.com ','bbg','bbg','bbg');


Insert into administrators ( admin_email, admin_fullname, admin_password, admin_username)
values ( 'bbg@b.com ','bbg','bbg','ibrahim');
create table cart_items
(
    id int auto_increment
        primary key
);

create table cart
(
    id           int not null
        primary key,
    cartItems_id int null,
    constraint FKqp12xv63c6vx9h7xyvlbcyrw3
        foreign key (cartItems_id) references cart_items (id)
);

create table customers
(
    id        bigint auto_increment
        primary key,
    address   varchar(255) not null,
    city      varchar(255) not null,
    country   varchar(255) not null,
    email     varchar(255) not null,
    full_name varchar(255) not null,
    password  varchar(255) null,
    phone     int          null,
    zipcode   int          not null,
    cart_id   int          null,
    constraint UK_rfbvkrffamfql7cjmen8v976v
        unique (email),
    constraint FK1y63n6caw4ic3oofwgammh3b7
        foreign key (cart_id) references cart (id)
);

create table cart_customerslist
(
    Cart_id          int    not null,
    customersList_id bigint not null,
    constraint UK_s6ckh4day5mgwi4kvjfk3sfxg
        unique (customersList_id),
    constraint FK9m5gav91mjp4l0s99iva41x1q
        foreign key (Cart_id) references cart (id),
    constraint FKnesdswf6jgescjupp5e89uuw7
        foreign key (customersList_id) references customers (id)
);

create table hibernate_sequence
(
    next_val bigint null
);

create table orders
(
    id           int auto_increment
        primary key,
    deliver_date datetime(6) null,
    order_date   datetime(6) null,
    order_number int         null,
    quantity     int         null,
    total_price  double      null,
    cart_id      int         null,
    customer_id  bigint      null,
    constraint FKpxtb8awmi0dk6smoh2vp1litg
        foreign key (customer_id) references customers (id),
    constraint FKtpihbdn6ws0hu56camb0bg2to
        foreign key (cart_id) references cart (id)
);

create table payment
(
    id          int auto_increment
        primary key,
    credit_card int          null,
    cvv         int          null,
    ex_month    int          null,
    ex_year     int          null,
    name_card   varchar(255) null,
    customer_id bigint       null,
    constraint FKaa38f32k3gt0b826sc8ga5yfi
        foreign key (customer_id) references customers (id)
);

create table products
(
    id                  bigint auto_increment
        primary key,
    product_amount      varchar(255) null,
    image               varchar(255) null,
    product_price       double       null,
    product_description varchar(255) null,
    product_name        varchar(255) null,
    rate                int          null,
    cartItems_id        int          null,
    constraint FKja42j3ghe4rtsw37xctg1d8k4
        foreign key (cartItems_id) references cart_items (id)
);


