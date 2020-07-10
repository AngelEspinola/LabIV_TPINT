DROP database IF EXISTS BDTPINT;
CREATE database BDTPINT;
use BDTPINT;

DROP TABLE IF EXISTS `roles`;
DROP TABLE IF EXISTS `usuarios`;
DROP TABLE IF EXISTS `materias`;
DROP TABLE IF EXISTS `provincias`;
DROP TABLE IF EXISTS `localidades`;
DROP TABLE IF EXISTS `docentes`;
DROP TABLE IF EXISTS `alumnos`;
DROP TABLE IF EXISTS `cursos`;
DROP TABLE IF EXISTS `alumnoxcurso`;

/* TABLAS */
CREATE TABLE roles(
  ID int NOT NULL AUTO_INCREMENT,
  descripcion varchar(45)NOT NULL,
  PRIMARY KEY PK_Roles(ID),
  unique key UK_Roles(descripcion)
); 
CREATE TABLE materias(
  ID int NOT NULL AUTO_INCREMENT,
  nombre varchar(45)NOT NULL,
  PRIMARY KEY PK_Materias(ID),
  unique key UK_Materias(nombre)
); 
CREATE TABLE provincias(
  ID int NOT NULL AUTO_INCREMENT,
  nombre varchar(45)NOT NULL,
  PRIMARY KEY PK_Provincias(ID),
  unique key UK_Provincias(nombre)
);
CREATE TABLE localidades(
  ID int NOT NULL AUTO_INCREMENT,
  id_provincia int NOT NULL,
  nombre varchar(45)NOT NULL,
  PRIMARY KEY PK_Provincias(ID),
  unique key UK_Localidades(nombre),
  constraint foreign key FK_Localidades(id_provincia) references provincias(ID)
);
CREATE TABLE docentes(
  ID int NOT NULL AUTO_INCREMENT,
  legajo varchar(45)NOT NULL,
  dni varchar(10)NOT NULL,
  nombre varchar(45)NOT NULL,
  apellido varchar(45)NOT NULL,
  nacimiento date NOT NULL,
  calle varchar(45) NOT NULL,
  numero varchar(5) not null,
  id_localidad int NOT NULL,
  id_provincia int NOT NULL,
  email varchar(45) NOT NULL,
  telefono varchar(15) NOT NULL,
  estado bit NOT NULL default 1,
  constraint PRIMARY KEY PK_Docentes(ID),
  constraint unique key UK_DNI(dni),
  constraint unique key UK_Legajo(legajo),
  constraint foreign key(id_localidad) references localidades(ID),
  constraint foreign key(id_provincia) references provincias(ID)
);
create table usuarios(
	ID int(4)auto_increment,
    id_docente int, 
    usuario varchar(20), 
    clave varchar(20), 
    estado bit,
    id_rol int,
    constraint primary key PK_Usuarios(usuario), 
    constraint unique key UK_Usuarios(id), 
    constraint foreign key FK_Usuario(id_docente) references docentes(ID),
    constraint foreign key FK_Rol(id_rol) references roles(ID)
);
CREATE TABLE alumnos(
  ID int NOT NULL AUTO_INCREMENT,
  legajo varchar(45) NOT NULL,
  dni varchar(10) NOT NULL,
  nombre varchar(45) NOT NULL,
  apellido varchar(45) NOT NULL,
  nacimiento date NOT NULL,
  calle varchar(45) NOT NULL,
  numero varchar(5) not null,
  id_localidad int NOT NULL,
  id_provincia int NOT NULL,
  email varchar(45) NOT NULL,
  telefono varchar(15) NOT NULL,
  estado bit NOT NULL default 1,
  constraint PRIMARY KEY PK_Alumnos(ID),
  constraint Unique key UK_DNI(dni), /*(dni,legajo),NO puede estar anotado a dos carreras, corresponde UN DNI por legajo*/
  constraint unique key UK_Legajo(legajo),/*el numero de legajo es unico siempre, podria ser la clave tambien*/
  constraint foreign key(id_provincia) references provincias(ID),
  constraint foreign key(id_localidad) references localidades(ID)
);


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cursos` (
  `idcursos` int(11) NOT NULL AUTO_INCREMENT,
  `docente` int(11) NOT NULL,
  `cuatrimestre` int(11) NOT NULL,
  `año` int(11) NOT NULL,
  `materia` int(11) NOT NULL,
  `baja` int(11) NOT NULL,
  PRIMARY KEY (`idcursos`),
  KEY `cursos_FK_idx` (`docente`),
  CONSTRAINT `cursos_FK` FOREIGN KEY (`docente`) REFERENCES `docentes` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `materia_FK` FOREIGN KEY (`materia`) REFERENCES `materias` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

CREATE TABLE `alumnoxcurso`(
  `id_curso` int(11) NOT NULL,
  `id_alumno` int NOT NULL,
  `nota1` INT,
  `nota2` INT,
  `recuperatorio1` INT,
  `recuperatorio2` INT,
  constraint primary key PK_alumnosxcursos(`id_curso`, `id_alumno`),
  constraint foreign key FK_Cursos(`id_curso`) references `cursos`(`idcursos`),
  constraint foreign key FK_Alumnos(`id_alumno`) references `alumnos`(`ID`)
);


/* TRIGGERS */
CREATE TRIGGER `crearusuarios` AFTER INSERT ON `docentes`
FOR EACH ROW
INSERT INTO `usuarios`(`id_docente`,`usuario`,`clave`,`estado`,`id_rol`)
VALUES(new.ID,new.legajo,new.dni,1,2);

USE `bdtpint`;
CREATE  OR REPLACE VIEW `alumnonotas` AS
SELECT
        `alumnos`.`ID`,
        `alumnos`.`legajo`,
        `alumnos`.`nombre`,
        `alumnos`.`apellido`,
        `axc`.`id_curso` AS `id_curso`,
        `axc`.`nota1`,
        `axc`.`nota2`,
        `axc`.`recuperatorio1`,
        `axc`.`recuperatorio2`,
        `axc`.`estado`
    FROM `alumnoxcurso` AS `axc`
    JOIN `alumnos`
    ON `alumnos`.`ID`=`axc`.`id_alumno`;
USE `bdtpint`;
CREATE 
     OR REPLACE ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `alumnonotas` AS
    SELECT 
        `alumnos`.`ID` AS `ID`,
        `alumnos`.`legajo` AS `legajo`,
        `alumnos`.`nombre` AS `nombre`,
        `alumnos`.`apellido` AS `apellido`,
        `axc`.`id_curso` AS `id_curso`,
        `axc`.`nota1` AS `nota1`,
        `axc`.`nota2` AS `nota2`,
        `axc`.`recuperatorio1` AS `recuperatorio1`,
        `axc`.`recuperatorio2` AS `recuperatorio2`,
        `axc`.`estado` AS `estado`
    FROM
        (`alumnoxcurso` `axc`
        JOIN `alumnos` ON ((`alumnos`.`ID` = `axc`.`id_alumno`)));

/*
CREATE TABLE cursos(
  ID int NOT NULL AUTO_INCREMENT,
  id_docente int NOT NULL,
  id_materia int not null,
  cuatrimestre int NOT NULL,
  anio int NOT NULL,
  estado bit not null,
  constraint primary key PK_Cursos(id_docente, id_materia, cuatrimestre, anio, estado),//no estoy seguro sobre el estado si es parte de la clave o no
  constraint foreign key FK_docente(id_docente) REFERENCES docentes(ID),
  constraint foreign key FK_materia(id_materia) references materias(ID)
);
*/
/*
CREATE TABLE alumnosXcurso(
	id_curso int not null,
    id_alumno int not null,
    nota1 decimal(18,2),
    nota2 decimal(18,2),
    recuperatorio1 decimal(18,2),
    recuperatorio2 decimal(18,2),
    promedio_1 decimal(18,2), /*todas las notas existentes*/
 /*   promedio_2 decimal(18,2), /*Resultado final*/
/*  condicion varchar(12),
    final decimal(18,2),
    comentarios varchar(200),
    estado bit not null,
    constraint primary key PK_alumnosCursos(id_curso, id_alumno),
    constraint foreign key FK_Cursos(id_curso) references cursos(ID),
    constraint foreign key FK_Alumnos(id_alumno) references alumnos(ID)
);


delimiter //
CREATE TRIGGER BorrarCursos AFTER UPDATE ON Docentes
FOR EACH ROW
begin
if (new.estado = 0)
then
	update cursos set estado = 0 where id_docente = new.ID and (new.anio >= year(now()));
end if;
end; //
delimiter ;

delimiter //
CREATE TRIGGER BorrarCursos_Alumno AFTER UPDATE ON alumnos
FOR EACH ROW
begin
if (new.estado = 0)
then
	update alumnosXcurso set estado = 0 where id_alumno = new.ID;
end if;
end; //
delimiter ;

delimiter //
CREATE TRIGGER CalcularPromedio AFTER UPDATE ON alumnosXcurso
FOR EACH ROW
begin
if (old.estado = new.estado)
then
	update alumnosXcurso set promedio_1 = (isnull(new.nota1,0) + isnull(new.nota2,0) + isnull(new.recuperatorio1,0) + isnull(new.recuperatorio2,0))/(if(isnull(new.nota1,0) > 0, 1,0) + if(isnull(new.nota2,0)>0,1,0) + if(isnull(new.recuperatorio2,0)>0,1,0) + if(isnull(new.recuperatorio2,0)>0,1,0))
							, promedio_2 = (isnull(recuperatorio1,isnull(new.nota1,0)) + isnull(recuperatorio2,isnull(new.nota2,0)))/ (if(isnull(recuperatorio1,isnull(new.nota1,0)) > 0, 1,0) + if(isnull(recuperatorio2,isnull(new.nota2,0)) > 0, 1,0))
    where id_alumno = new.ID and id_curso = new.id_curso;
end if;
end; //
delimiter ;
*/

INSERT INTO `BDTPINT`.`roles` (`ID`, `descripcion`) VALUES ('1', 'Administrador'),('2', 'Docente');

INSERT INTO `BDTPINT`.`provincias` (`nombre`) VALUES ('Buenos Aires'),('Catamarca'),('Chaco'),('Chubut'),('Córdoba'),('Corrientes'),('Entre Ríos'),
('Formosa'),('Jujuy'),('La Pampa'),('La Rioja'),('Mendoza'),('Misiones'),('Neuquén'),('Río Negro'),('Salta'),('San Juan'),('San Luis'),
('Santa Cruz'),('Santa Fe'),('Santiago del Estero'),('Tierra del Fuego'),('Tucumán'),('CABA');

INSERT INTO `BDTPINT`.`localidades` (`id_provincia`,`nombre`) VALUES (1,'Bancalari'),(1,'Don Torcuato'),(1,'El Talar'),(1,'La Plata'),(1,'Pacheco'),
(1,'Villa de Mayo'),(1,'Virreyes'),(2,'San Fernando del Valle de Catamarca'),(3,'Resistencia'),(4,'Rawson'),(5,'Córdoba'),(6,'Corrientes'),
(7,'Paraná'),(8,'Formosa'),(9,'San Salvador de Jujuy'),(10,'Santa Rosa'),(11,'La Rioja'),(12,'Mendoza'),(13,'Misiones'),(14,'Neuquén'),
(15,'Río Negro'),(16,'Salta'),(17,'San Juan'),(18,'San Luis'),(19,'Río Gallegos'),(20,'Santa Fe'),(21,'Santiago del Estero'),(22,'Ushuaia'),
(23,'San Miguel de Tucumán'),(24,'Saavedra');

INSERT INTO `BDTPINT`.`materias` (`nombre`) VALUES ('Introducción a la Informática'),('Introducción a la Programación'),('Matemática (TSP)'),('Arquitectura y Sistemas Operativos'),('Estadística'),
('Inglés I'),('Inglés II'),('Laboratorio de Computación I'),('Laboratorio de Computación II'),('Matemática'),('Metodología de la Investigación'),('Programación I'),('Programación II');

INSERT INTO `BDTPINT`.`docentes` (`legajo`, `dni`, `nombre`, `apellido`, `nacimiento`, `calle`, `numero`, `id_localidad`, `id_provincia`, `email`, `telefono`)
VALUES('21706', '33123456', 'Juan', 'Suarez', '1980/12/31', 'Calle', '500', 1, 4,'mes@gmail.com','1511223344');

INSERT INTO `bdtpint`.`usuarios` (`ID`, `id_docente`, `usuario`, `clave`, `estado`, `id_rol`) VALUES ('1', '1', '21706', '33123456', b'1', b'1');

INSERT INTO `bdtpint`.`cursos` (`idcursos`, `docente`, `cuatrimestre`, `año`) VALUES ('1', '1', '1', '2020');

INSERT INTO `bdtpint`.`alumnos` (`ID`, `legajo`, `dni`, `nombre`, `apellido`, `nacimiento`, `calle`, `numero`, `id_localidad`, `id_provincia`, `email`, `telefono`, `estado`)
VALUES('1', '10000', '12345678', 'Juan', 'Perez', '1990-05-25', '25 de Mayo', '1', '9', '3', 'jperez@gmail.com', '1500001111', b'1'),
('2', '10001', '12345679', 'Marcos', 'Rojo', '1995-04-25', '3 de Febrero', '2', '6', '1', 'mrojo@gmail.com', '1511110000', b'1'),
('3', '10002', '12345680', 'Monica', 'Argento', '1989-08-29', 'Calle verdadra', '3', '4', '1', 'margento@gmail.com', '1512345678', b'1');

INSERT INTO `bdtpint`.`alumnosxcursos` (`id_curso`, `id_alumno`, `nota1`, `nota2`) VALUES ('1', '1', '10', '10');
INSERT INTO `bdtpint`.`alumnosxcursos` (`id_curso`, `id_alumno`) VALUES ('1', '2'),('1', '3');
