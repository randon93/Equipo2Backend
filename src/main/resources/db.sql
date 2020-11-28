CREATE DATABASE biblioteca_ceiba;
USE biblioteca_ceiba;

-- CC -> Cedula extranjeria
CREATE TABLE persona(
	id int primary key auto_increment,
    tipo_identificacion varchar(10) not null,
    identificacion varchar(30) not null unique,
    nombre varchar(100) not null,
    apellido varchar(100) not null,
    direccion varchar(100) not null,
    telefono varchar(20) not null
);

-- rol --> 'B' Bibliotecario y 'C' Cliente
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

-- Controlar el mismo usuario solo puede llevarse 1 copia del libro
-- Si la fecha_entregado is null no está entregado
-- Dejar texto por defecto observaciones algun texto
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