DROP DATABASE IF EXISTS Inventario;
create database Inventario;

use Inventario;

DROP table IF EXISTS Usuario;
create table Usuario(
codUsu int auto_increment primary key,
nomUsu varchar(50) not null,
contUsu varchar(255) not null
);

DROP table IF EXISTS Empleados;
create table Empleados(
codEmp int auto_increment primary key,
nomEmp varchar(50) not null,
apeEmp varchar(50) not null,
edadEmp int not null,
dniEmp varchar(8) not null,
nacEmp date not null,
celEmp varchar(9) not null,
corrEmp varchar(50) not null,
dirEmp varchar(50) not null
);

Drop table if exists Categoria;
create table Categoria (
idCat int auto_increment primary key,
nomCat varchar(100) not null,
desCat varchar(200) not null
);

Drop table if exists Proveedor;
create table Proveedor (
idProv int auto_increment primary key,
nomProv varchar(50) not null,
rucProv varchar(11) not null,
telProv varchar(15) not null,
corrProv varchar(120) not null,
dirProv varchar(200) not null
);

Drop table if exists Producto;
create table Producto(
idProd int auto_increment primary key,
nomProd varchar(50) not null,
canProd int not null,
desProd varchar(50) not null,
idCat int,
idProv int,
foreign key (idCat) references Categoria(idCat),
foreign key (idProv) references Proveedor(idProv)
);

Drop table if exists Alerta;
create table Alerta(
id int auto_increment primary key,
idProd int,
cantidad int,
mensaje varchar(100),
fecha datetime,
foreign key (idProd) references Producto(idProd)
);

































#SELECT @@global.time_zone, @@session.time_zone;
#select date_format(nacEmp, '%d/%m/%Y') as fecha_corregida from Empleados;
#SET GLOBAL time_zone = '-05:00';
#SET time_zone = '-05:00';
#SET @@global.time_zone = '+00:00';
#SET @@session.time_zone = '+00:00';
#SET GLOBAL time_zone = '-05:00';
#SELECT @@global.time_zone, @@session.time_zone;
#SELECT @@global.time_zone, @@session.time_zone;