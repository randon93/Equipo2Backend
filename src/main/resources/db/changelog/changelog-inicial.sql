-- liquibase formatted sql

-- changeset Jorge:1
-- comment: Script inicial biblioteca
CREATE TABLE persona(
	id int primary key auto_increment,
    tipo_identificacion varchar(10) not null,
    identificacion varchar(30) not null unique,
    nombre varchar(100) not null,
    apellido varchar(100) not null,
    direccion varchar(100) not null,
    telefono varchar(20) not null
);

CREATE TABLE usuario(
	id int primary key auto_increment,    
    correo varchar(100) not null unique,
    clave varchar(100) not null,
    rol char not null,
    id_persona int not null,
		foreign key(id_persona) references persona(id)
);

CREATE TABLE libro(
	id int primary key auto_increment,
    isbn varchar(100) not null unique,
    nombre varchar(100) not null,
    cantidad_total int not null, -- Stock de inventario
    cantidad_disponible int not null
);

CREATE TABLE prestamo(
	id int primary key auto_increment,
    id_usuario_cliente int not null,
		foreign key(id_usuario_cliente) references usuario(id),
	id_usuario_bibliotecario int not null,
		foreign key(id_usuario_bibliotecario) references usuario(id),
	id_libro int not null,
		foreign key(id_libro) references libro(id),
	fecha_prestamo date not null,
    fecha_entrega date,
    fecha_entregado date,
    observaciones text not null
);

-- changeset Jorge:2
-- comment: Agregando usuario bibliotecario por defecto
INSERT INTO persona VALUES (null,"CC","12145636","Sara","Lopez","Calle 34A","8742536");
INSERT INTO usuario VALUES (null,"admin@gmail.com","$2a$10$poXHyM9jxDzsGPp/JrLr9OiXACdyWFJ0j61BtTacelGaiBA1r3emG","B",1);-- La clave es admin