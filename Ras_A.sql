create database Ras_A
go

use Ras_A


--------------------------------------------------------TABLAS PADRE------------------------------------------------------------------

create table Cargo(
cod_cargo_empleado int identity(1,1) primary key, 
cargo  varchar(50) not null unique, 
)

create table Pregunta_seguridad(
cod_pregunta_seg int identity(1,1) primary key,
pregunta varchar(100) not null unique,
)

create table Tipo_telefono(
cod_tipo int identity(1,1) primary key, 
tipo  varchar(30) not null unique,
)

create table Estado_camara(
cod_estado_camaras int identity(1,1) primary key, 
estado_camara varchar(30) not null unique,
)

create table Agrupacion(
cod_agrupacion int identity(1,1) primary key, 
nombre_agrupacion varchar(50) not null unique,
)

create table Tez(
cod_tez int identity(1,1) primary key,
tez varchar(50) not null unique,
)

create table Asistencia(
cod_asistencia int identity(1,1) primary key, 
asistencia varchar(30) not null unique,
)

create table Visitas(
cod_visita int identity(1,1) primary key,
nombre_visita varchar(80) not null,
apellido_visita varchar(80) not null,
huella binary(250) unique,
direccion varchar(100) not null,
)

create table Estado_recluso(
cod_estado int identity(1,1) primary key, 
estado_recluso varchar(50) not null unique,
)

Create table Crimen(
cod_crimen int identity(1,1) primary key, 
crimen varchar(100) not null unique,
)

Create table Horario(
cod_horario int identity(1,1) primary key, 
horario varchar(20) not null,
hora_apertura varchar(30) not null,
hora_cierre varchar(30) not null,
)

Create table Foto_empleado(
cod_foto int identity(1,1) primary key,
url varchar(100) unique,
)

create table Tipo_foto(
cod_tipo int identity(1,1) primary key,
tipo varchar(50) unique,
)

create table Estado_empleado(
cod_estado int identity(1,1) primary key,
estado varchar(50) unique,
)

-------------------------------------------------------TABLAS PADRE E HIJO-------------------------------------------------------------

create table Empleado(
cod_empleado int identity(1,1) primary key, 
nombre_empleado  varchar(80) not null,
apellido_empleado varchar(80) not null, 
nombre_usuario varchar(20) not null unique,
cotraseña varchar(20) not null, 
cod_cargo_empleado int not null  foreign key(cod_cargo_empleado) references Cargo(cod_cargo_empleado),
cod_pregunta_seg int  not null foreign key (cod_pregunta_seg) references Pregunta_seguridad(cod_pregunta_seg), 
resp_seguridad varchar(50) not null, 
edad_empleado numeric(3) check (edad_empleado > 18), 
huella binary(300),
cod_foto int foreign key(cod_foto) references Foto_empleado(cod_foto),
cod_estado int not null foreign key (cod_estado) references Estado_empleado(cod_estado),
)

create table Sector(
cod_sector int identity(1,1) primary key, 
nombre_sector varchar(80) not null unique,
cod_empleado int not null foreign key (cod_empleado) references Empleado(cod_empleado),
cod_agrupacion int not null foreign key (cod_agrupacion) references Agrupacion(cod_agrupacion),
cod_horario int not null foreign key (cod_horario) references horario(cod_horario),
)

Create table Reclusos(
cod_recluso int identity(1,1) primary key,
nombre_recluso varchar(80) not null,
apellido_recluso varchar(80) not null,
edad_recluso int not null check (edad_recluso > 18),  
cod_estado int not null foreign key(Cod_estado) references Estado_recluso(Cod_estado),
cod_sector int not null foreign key (cod_sector) references sector(cod_sector), 
cod_asistencia int not null foreign key(cod_asistencia) references Asistencia(cod_asistencia),
cod_tez int not null foreign key(cod_tez) references Tez(cod_tez),
altura numeric(3,2) not null check(altura > 0),
peso numeric(5,2) not null check (peso > 0),
alias varchar(60),
huella binary(260),
codigo_barra  int, 
)

Create table Historial_recluso(
cod_historial int identity(1,1) primary key,
cod_recluso int not null  foreign key (cod_recluso) references Reclusos(cod_recluso),
fecha date not null default getdate(),
)

create table Citas(
cod_citas int identity(1,1) primary key,
cod_visita int not null foreign key (cod_visita) references Visitas(cod_visita),
cod_recluso int not null foreign key(cod_recluso) references Reclusos(cod_recluso),
cod_asistencia int not null foreign key (cod_asistencia) references Asistencia(cod_asistencia),
fecha_cita date not null default getdate(),
hora_cita time not null,
)

Create table Presupuesto(
cod_presupuesto int identity(1,1) primary key, 
fecha date not null default getdate(), 
año numeric(4) not null, 
cod_empleado int not null foreign key (cod_empleado) references Empleado(cod_empleado),
total money,
)



---------------------------------------------------TABLAS HIJO------------------------------------------------------------------

create table Camaras(
cod_camara int identity(1,1) primary key, 
cod_sector int not null foreign key (cod_sector) references Sector(cod_sector), 
cod_estado_camaras int not null foreign key (cod_estado_camaras) references Estado_camara(cod_estado_camaras), 
)

create table Reporte_comun(
cod_reporte int identity(1,1) primary key,
cod_empleado int not null foreign key (cod_empleado) references Empleado(cod_empleado), 
fecha_reporte date not null default getdate(), 
descripcion_reporte varchar(500) not null,
)

create table Telefono_empleado(
cod_telefono_empleado int identity(1,1) primary key,
telefono numeric(8) not null unique,
cod_tipo int not null foreign key (cod_tipo) references Tipo_telefono(cod_tipo), 
cod_empleado int not null foreign key (cod_empleado) references Empleado(cod_empleado), 
)

create table Detalle_presupuesto(
cod_detalle int identity(1,1) primary key,
cod_presupuesto int not null foreign key (cod_presupuesto) references Presupuesto(cod_presupuesto),
recurso varchar(50) not null,
gasto money not null, 
)

Create table Detalle_historial(
cod_detalle int identity(1,1) primary key, 
cod_historial int not null foreign key (cod_historial) references Historial_recluso(cod_historial),
cod_crimen int not null foreign key(cod_crimen) references Crimen(cod_crimen),
descripcion varchar(500) not null,
fecha date not null default getdate(),
)

Create table Foto_recluso(
cod_foto int identity(1,1) primary key, 
cod_recluso int not null foreign key(cod_recluso) references Reclusos(cod_recluso),
url varchar(100) null unique,
cod_tipo_foto int not null foreign key (cod_tipo_foto) references Tipo_foto(cod_tipo),
)

Create table Telefono_visita(
cod_telefono_visita int identity(1,1) primary key,
telefono numeric(8) not null unique, 
cod_tipo int not null foreign key(cod_tipo) references  Tipo_telefono(cod_tipo),
cod_visita int not null foreign key (cod_visita) references Visitas(cod_visita),
)

create table Reporte_conducta(
cod_reporte_conducta int identity(1,1) primary key,
cod_recluso int not null foreign key (cod_recluso) references Reclusos(cod_recluso),
cod_empleado int not null foreign key (cod_empleado) references Empleado(cod_empleado),
fecha date not null default getdate(),
descripcion varchar(500) not null,
)

-------------------------------------------------------------- REGISTROS ---------------------------------------------------------------------

--Agrupacion:
INSERT INTO Agrupacion values ('Civil')
INSERT INTO Agrupacion values ('MaoMao')
INSERT INTO Agrupacion values ('Salvatrucha')
INSERT INTO Agrupacion values ('Dieciocho')

--Asistencia:
INSERT INTO Asistencia values ('Ausente')
INSERT INTO Asistencia values ('Presente')

--Cargo
INSERT INTO Cargo (cargo) values ('Administrador')
INSERT INTO Cargo (cargo) VALUES ('Jefe de seguridad')
INSERT INTO Cargo(Cargo) VALUES ('Jefe de sector')
INSERT INTO Cargo(cargo) VALUES ('Director')

--Crimen
INSERT INTO Crimen values ('Robo')
INSERT INTO Crimen values ('Asesinato')
INSERT INTO Crimen values ('Extorcion')

--Pregunta seguridad
INSERT INTO Pregunta_seguridad (pregunta) values ('¿Cual fue tu lugar de nacimiento?')
INSERT INTO Pregunta_seguridad values('¿Donde naciste?')
INSERT INTO Pregunta_seguridad(pregunta) VALUES('¿cual fue tu primera novia?');

--Foto empleado
INSERT INTO Foto_empleado values('fsd')
INSERT INTO Foto_empleado values('dss')
INSERT INTO Foto_empleado values('fssdgd')
INSERT INTO Foto_empleado values('ddgd')

--Tipo telefono
INSERT INTO Tipo_telefono values('Fijo')
INSERT INTO Tipo_telefono values('Celular')
INSERT INTO Tipo_telefono values('Fax')

--Estado empleado
INSERT INTO Estado_empleado VALUES('Activo')
INSERT INTO Estado_empleado VALUES('Inactivo')

--Visitas
INSERT INTO Visitas values('Andres','Ramos', 1000 ,'Ayutuxtepeque')
INSERT INTO Visitas values('Lucia','Perez', 100 ,'San Sebastian')
INSERT INTO Visitas values('Gloria','Concepcion', 001001 ,'Ayutuxtepeque')

--Empleado
INSERT INTO Empleado VALUES ('Julio', 'Hernandez', 'admin', '123', 1, 1, 'Usulutan', 25, null, 1, 1)
INSERT INTO Empleado VALUES ('Anderson', 'MCfly','seguridad','Gby' , 2, 3, 'Marcela', 22, null, 2, 1)
INSERT INTO Empleado VALUES ('Alex', 'Mlina','sector','456' , 3, 2, 'En hospital', 32, null, 3, 1)
INSERT INTO Empleado VALUES ('Kevin', 'Guitierrez', 'direc', '321', 4, 3, 'Patricia', 20, null, 4, 2)

--Telefono empleado
INSERT INTO Telefono_empleado VALUES(22515685, 1, 1)
INSERT INTO Telefono_empleado VALUES(75114545, 2, 1)
INSERT INTO Telefono_empleado VALUES(75521532, 2, 2)

--Telefono visitas
INSERT INTO Telefono_visita VALUES(22594646, 1, 1)
INSERT INTO Telefono_visita VALUES(22256366, 1, 2)

--Estado camara
INSERT INTO Estado_camara VALUES ('Encendida')
INSERT INTO Estado_camara VALUES ('Apagada')
INSERT INTO Estado_camara VALUES ('Desconectada')
INSERT INTO Estado_camara VALUES ('En espera')

--Estado recluso
INSERT INTO Estado_recluso values('Capturado')
INSERT INTO Estado_recluso values('Confianza')
INSERT INTO Estado_recluso values('Libertad condicional')



--Tipo foto recluso
INSERT INTO Tipo_foto VALUES('Frontal')
INSERT INTO Tipo_foto VALUES('Perfil 1')
INSERT INTO Tipo_foto VALUES('Perfil 2')

--Horario
INSERT INTO Horario values('Horario1', '10:30', '17:00')
INSERT INTO Horario values('Horario2', '11:25', '17:00')
INSERT INTO Horario values('Horario3', '9:30', '17:00')

--SECTOR
INSERT INTO Sector values('Block A', 3, 1, 1)
INSERT INTO Sector values('Block B', 3, 2, 3)
INSERT INTO Sector values('Block C', 3, 2, 3)
INSERT INTO Sector values('Block D', 3, 1, 2)

--TEZ
INSERT INTO Tez values('Trigueño')
INSERT INTO Tez values('Blanco')
INSERT INTO Tez values('Moreno')
INSERT INTO Tez values('Caucasico')

--Reclusos
INSERT INTO Reclusos VALUES ('Emerson','Barrera',  25, 1, 1, 2, 2, 1.70, 130.25,'ElMan',null,null)
INSERT INTO Reclusos VALUES ('Santiago','Martinez',29, 2, 2, 2, 3, 1.68, 160.52, 'Santi',null,null)
INSERT INTO Reclusos VALUES ('Hector','Chavez',    32, 3, 2, 2, 1, 1.75, 120.62, 'H',null,null)

--Foto recluso
INSERT INTO Foto_recluso VALUES (1,'sdfgh',1)
INSERT INTO Foto_recluso VALUES (1,'sdfgfgh',2)
INSERT INTO Foto_recluso VALUES (1,'sdfkkgh',3)
INSERT INTO Foto_recluso VALUES (2,'sdfnfdgh',1)
INSERT INTO Foto_recluso VALUES (2,'sdfdtgh',2)
INSERT INTO Foto_recluso VALUES (2,'sdfpoiigh',3)



---------------------------------------Tablas extra [no ejecutar ya que seran llenadas desde el sistema]--------------------------------
--Camaras
INSERT INTO Camaras VALUES(1, 1)
INSERT INTO Camaras VALUES(2, 1)
INSERT INTO Camaras VALUES(3, 1)
INSERT INTO Camaras VALUES(4, 1)

--Citas
INSERT INTO Citas VALUES(1, 1, 1, '29-05-2016', '13:00')
INSERT INTO Citas VALUES(2, 2, 1, '30-05-2016', '14:00')
INSERT INTO Citas VALUES(3, 2, 1, '31-05-2016', '15:00')

--Presupuesto
INSERT INTO Presupuesto VALUES('29-05-2016', 2015, 4, 500)
INSERT INTO Presupuesto VALUES('29-05-2016', 2016, 4, 700)

--Detalle presupuesto
INSERT INTO Detalle_presupuesto VAlUES (2, 'Luz', 100)
INSERT INTO Detalle_presupuesto VAlUES (2, 'Agua', 70)
INSERT INTO Detalle_presupuesto VAlUES (2, 'Alimento', 150)
INSERT INTO Detalle_presupuesto VAlUES (2, 'Ropa', 65)

--Historial recluso
INSERT INTO Historial_recluso VALUES (1, '29-05-2016')
INSERT INTO Historial_recluso VALUES (2, '29-05-2016')
INSERT INTO Historial_recluso VALUES (3, '29-05-2016')

--Detalle Historial
INSERT INTO Detalle_historial VALUES(1, 2, 'Asesinado a menor', '29-05-2016')
INSERT INTO Detalle_historial VALUES(1, 3, 'Tienda', '29-05-2016')
INSERT INTO Detalle_historial VALUES(2, 3, 'Familia', '29-05-2016')


--Reporte conducta
INSERT INTO Reporte_conducta VALUES(1, 2, '29-05-2016', 'mal comportamiento')

--Reporte comun
INSERT INTO Reporte_comun VALUES(2, '29-05-2016', 'Pelea de reclusos')
INSERT INTO Reporte_comun VALUES(4, '29-05-2016', 'Pago de recibos')

 
-----------------------------------------------------------CONSULTAS-------------------------------------------------------------------------

SELECT cod_sector, nombre_empleado, nombre_agrupacion, horario 
FROM(((Sector AS s
inner join Empleado AS e ON s.cod_empleado = e.cod_empleado)
inner join Agrupacion AS a ON s.cod_agrupacion = a.cod_agrupacion)
inner join Horario AS h ON s.cod_horario = h.cod_horario)

select cod_empleado, nombre_empleado, apellido_empleado, nombre_usuario, cotraseña, cargo, pregunta, resp_seguridad, edad_empleado, huella
from (
(Empleado as e
inner join Pregunta_seguridad as p on e.cod_pregunta_seg = p.cod_pregunta_seg)
inner join Cargo as c on e.cod_cargo_empleado = c.cod_cargo_empleado)

SELECT cod_sector, (e.nombre_empleado + ' ' + e.apellido_empleado) nombre  , a.nombre_agrupacion, h.horario 
FROM(((Sector AS s
inner join Empleado AS e ON e.cod_empleado = s.cod_empleado)
inner join Agrupacion AS a ON a.cod_agrupacion = s.cod_agrupacion)
inner join Horario AS h ON h.cod_horario = s.cod_horario)

SELECT Sector.cod_sector, Empleado.nombre_empleado, Agrupacion.nombre_agrupacion, Horario.horario 
FROM(((Sector 
inner join Empleado ON Empleado.cod_empleado = Sector.cod_empleado) 
inner join Agrupacion ON Agrupacion.cod_agrupacion = Sector.cod_agrupacion) 
inner join Horario ON Horario.cod_horario = Sector.cod_horario)

select  * from Presupuesto

SELECT TOP 3 fecha, año FROM Presupuesto ORDER BY fecha DESC

SELECT cod_presupuesto, año, (Nombre_empleado + apellido_empleado), fecha FROM Presupuesto, Empleado WHERE Empleado.cod_empleado = Presupuesto.cod_empleado
SELECT nombre_usuario, cotraseña, cod_empleado, url ,nombre_empleado, apellido_empleado, edad_empleado,Cargo    FROM Empleado, Cargo, Foto_empleado where  Cargo.cod_cargo_empleado= Empleado.cod_cargo_empleado AND Foto_empleado.cod_foto =  Empleado.cod_foto 
--------------------------------------Consustas PascalRas Anderson -------------------------------------------------------------------



SELECT crimen ,descripcion, fecha from Crimen, Detalle_historial  where  Crimen.cod_crimen = Detalle_historial.cod_crimen
SELECT crimen ,descripcion from Crimen, Detalle_historial  where  Crimen.cod_crimen = Detalle_historial.cod_crimen
SELECT crimen  from Crimen, Detalle_historial  where  Crimen.cod_crimen = Detalle_historial.cod_crimen
SELECT crimen  from Crimen, Detalle_historial, Historial_recluso  where  Crimen.cod_crimen = Detalle_historial.cod_crimen
select  crimen, descripcion from Historial_recluso,Detalle_historial,Crimen where  Crimen.cod_crimen = Detalle_historial.cod_crimen AND Historial_recluso.cod_historial = Detalle_historial.cod_historial


SELECT cod_sector,nombre_recluso, apellido_recluso, edad_recluso, altura,peso,tez ,alias, estado_recluso  FROM Reclusos,Tez, Estado_recluso, Historial_recluso, Detalle_historial, Crimen where tez.cod_tez = Reclusos.cod_tez AND Estado_recluso.cod_estado = Reclusos.cod_estado AND Historial_recluso.cod_historial  = Detalle_historial.cod_historial AND Crimen.cod_crimen = Detalle_historial.cod_crimen

SELECT nombre_recluso,descripcion FROM Historial_recluso, Reclusos , Detalle_historial WHERE Reclusos.cod_recluso = Historial_recluso.cod_recluso AND Historial_recluso.cod_historial= Detalle_historial.cod_historial
SELECT  descripcion,nombre_recluso From   Detalle_historial,Reclusos,Historial_recluso WHERE Reclusos.cod_recluso = Historial_recluso.cod_recluso AND Historial_recluso.cod_historial = Detalle_historial.cod_historial
select MAX(cod_recluso)cod_recluso from Reclusos 


select cod_recluso, nombre_recluso,apellido_recluso,edad_recluso, altura, peso , tez, alias ,estado_recluso codigo_barra from Reclusos, Tez, Estado_recluso Where  Tez.cod_tez= Reclusos.cod_tez AND Estado_recluso.cod_estado = Reclusos.cod_estado  AND nombre_recluso like'S%'
select cod_recluso, nombre_recluso,apellido_recluso,edad_recluso, altura, peso , tez, alias ,estado_recluso,codigo_barra FROM Reclusos, Tez, Estado_recluso Where  Tez.cod_tez= Reclusos.cod_tez AND Estado_recluso.cod_estado = Reclusos.cod_estado
select cod_recluso,nombre_recluso,estado_recluso,codigo_barra FROM Reclusos, Tez, Estado_recluso Where  Tez.cod_tez= Reclusos.cod_tez AND Estado_recluso.cod_estado = Reclusos.cod_estado

select  cod_tipo, nombre_recluso  from Tipo_foto, Foto_recluso, Reclusos Where Tipo_foto.cod_tipo = Foto_recluso.cod_tipo_foto AND Reclusos.cod_recluso = Foto_recluso.cod_recluso AND  nombre_recluso like 'S%'
select * from Foto_recluso
select nombre_recluso, cod_tipo ,url  from Foto_recluso, Tipo_foto, Reclusos Where Tipo_foto.cod_tipo = Foto_recluso.cod_tipo_foto AND Reclusos.cod_recluso = Foto_recluso.cod_recluso AND  nombre_recluso like 'S%' ------------ consulta con error Ayuuuudaa :0-------------------

insert Foto_recluso(cod_recluso,cod_tipo_foto,url,url_bit)
select 1,  3 ,'/PNG_Reclusos/1x-01.jpg' ,* from openrowset(bulk N'C:\Users\Works\Pictures\PNG ras\1x-01.jpg',single_blob)rs;

insert Foto_recluso(cod_recluso,cod_tipo_foto,url,url_bit)
select 1,  2 ,'/PNG_Reclusos/2x-01.jpg', * from openrowset(bulk N'C:\Users\Works\Pictures\PNG ras\2x-01.jpg',single_blob)rs;

insert Foto_recluso(cod_recluso,cod_tipo_foto,url,url_bit)
select 1,  1 ,'/PNG_Reclusos/3x-01.jpg' ,* from openrowset(bulk N'C:\Users\Works\Pictures\PNG ras\3x-01.jpg',single_blob)rs;




insert Foto_recluso(cod_recluso,cod_tipo_foto,url,url_bit)
select 2,  3 ,'/PNG_Reclusos/A3x-01.jpg' ,* from openrowset(bulk N'C:\Users\Works\Documents\NetBeansProjects\General_Examples_packaje\src\PNG_Reclusos\A3x-01.jpg',single_blob)rs;

insert Foto_recluso(cod_recluso,cod_tipo_foto,url,url_bit)
select 2,  2 ,'/PNG_Reclusos/A2x-01.jpg', * from openrowset(bulk N'C:\Users\Works\Documents\NetBeansProjects\General_Examples_packaje\src\PNG_Reclusos\A2x-01.jpg',single_blob)rs;

insert Foto_recluso(cod_recluso,cod_tipo_foto,url,url_bit)
select 2,  1 ,'/PNG_Reclusos/A1x-01.jpg' ,* from openrowset(bulk N'C:\Users\Works\Documents\NetBeansProjects\General_Examples_packaje\src\PNG_Reclusos\A1x-01.jpg',single_blob)rs;



insert Foto_recluso(cod_recluso,cod_tipo_foto,url,url_bit)
select 3,  3 ,'/PNG_Reclusos/3F3.jpg' ,* from openrowset(bulk N'C:\Users\Works\Documents\NetBeansProjects\General_Examples_packaje\src\PNG_Reclusos\3F3.jpg',single_blob)rs;

insert Foto_recluso(cod_recluso,cod_tipo_foto,url,url_bit)
select 3,  2 ,'/PNG_Reclusos/3F2.jpg', * from openrowset(bulk N'C:\Users\Works\Documents\NetBeansProjects\General_Examples_packaje\src\PNG_Reclusos\3F2.jpg',single_blob)rs;

insert Foto_recluso(cod_recluso,cod_tipo_foto,url,url_bit)
select 3,  1 ,'/PNG_Reclusos/3F1-01.jpg' ,* from openrowset(bulk N'C:\Users\Works\Documents\NetBeansProjects\General_Examples_packaje\src\PNG_Reclusos\3F1-01.jpg',single_blob)rs;


select * from Detalle_historial
select * from Crimen
select * from Reclusos

select * from Empleado
select * from  Foto_recluso

delete from Foto_recluso

update Foto_recluso set cod_tipo_foto = 1 , url_bit = 'oie no' where  cod_recluso =1 AND cod_foto = 1
select url_bit, nombre_recluso from Foto_recluso, Reclusos where Reclusos.cod_recluso = Foto_recluso.cod_recluso  AND  nombre_recluso = 'Santiago'
--------------------------------------
alter table  Foto_recluso
add url_bit image
select *from Tipo_foto
----------------------------------------- consultas testing-----------------------------------------------

select url_bit, cod_tipo_foto  from Tipo_foto, Foto_recluso, Reclusos where Tipo_foto.cod_tipo = Foto_recluso.cod_tipo_foto AND Reclusos.cod_recluso = Foto_recluso.cod_recluso AND  cod_tipo_foto = 1 AND nombre_recluso = 'Santiago' 