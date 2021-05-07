Drop database if exists Actividad15;
create database Actividad15;
use Actividad15;
create table Autores(
	IdAutor int not null,
    Nombre varchar(25),
    Apellido varchar(25),
    primary key (idAutor)
);

insert into Autores(IdAutor, Nombre, Apellido)
values
(1,'Harvey','Deitel'),
(2,'Paul','Deitel'),
(3,'Tem','Nieto'),
(4,'Sean','Santry');

select * from Autores;

CREATE USER 'Act15'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON actividad15.autores TO 'Act15'@'localhost';