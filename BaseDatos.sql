create table cliente (contrasena varchar(255) not null, estado bit not null, cliente_id bigint not null, primary key (cliente_id)) engine=InnoDB;
create table cuenta (numero_de_cuenta bigint not null auto_increment, estado bit not null, saldo_inicial bigint not null, tipo_de_cuenta varchar(255) not null, cliente_id bigint not null, primary key (numero_de_cuenta)) engine=InnoDB;
create table movimiento (id bigint not null auto_increment, fecha datetime(6) not null, saldo bigint not null, tipo_movimiento varchar(255) not null, valor bigint not null, cuenta_id bigint not null, primary key (id)) engine=InnoDB;
create table persona (id bigint not null auto_increment, direccion varchar(255) not null, edad integer not null, genero varchar(1) not null, identificacion varchar(255) not null, nombre varchar(255) not null, telefono varchar(255) not null, primary key (id)) engine=InnoDB;
alter table persona add constraint UK_r5vsms84ih2viwd6tatk9o5pq unique (identificacion);
alter table cliente add constraint FK33cy8185r4ecu0qd6tyra3drb foreign key (cliente_id) references persona (id);
alter table cuenta add constraint FK4p224uogyy5hmxvn8fwa2jlug foreign key (cliente_id) references cliente (cliente_id);
alter table movimiento add constraint FK4ea11fe7p3xa1kwwmdgi9f2fi foreign key (cuenta_id) references cuenta (numero_de_cuenta);
