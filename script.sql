drop table cliente if exists;
drop table catalogo if exists;
drop table articulo if exists;
drop table pedido if exists;
drop table pedidoarticulo if exists;

create table CLIENTE
(
   idCliente integer not null,
   nombre VARCHAR(255) NOT NULL,
   primerApellido VARCHAR(255) NOT NULL,
   segundoApellido VARCHAR(255) NOT NULL,
   direccion VARCHAR(255) NOT NULL,
   dni VARCHAR(255) NOT NULL,
   telefono VARCHAR(255) NOT NULL,
   cuenta VARCHAR(255) NOT NULL,
   password VARCHAR(255) NOT NULL,
   primary key(idCliente)
);


create table CATALOGO
(
   idCatalogo integer not null,
   nombre VARCHAR(255) NOT NULL,
   primary key(idCatalogo)
);

create table ARTICULO
(
   idArticulo integer not null,
   idCatalogo integer not null,
   nombreArticulo VARCHAR(255) NOT NULL,
   fabricante VARCHAR(255) NOT NULL,
   fechaFabricacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
   stock integer DEFAULT 0,
   precio double DEFAULT 0,
   primary key(idArticulo),
   FOREIGN KEY (idCatalogo) REFERENCES CATALOGO(idCatalogo)

);

create table PEDIDO
(
   idPedido integer not null,
   idCliente int not null,
   fechaPedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
   primary key(idPedido),
   FOREIGN KEY (idCliente) REFERENCES CLIENTE(idCliente)
);


create table PEDIDOARTICULO
(
   pedidoId integer not null,
   articuloId integer not null,
   cantidad integer DEFAULT 0,
   FOREIGN KEY (pedidoId) REFERENCES PEDIDO(idPedido),
   FOREIGN KEY (articuloId) REFERENCES ARTICULO(idArticulo),
   primary key(pedidoId,articuloId)
);
insert into CATALOGO VALUES(1,'PRIMAVERA 2017');
insert into CATALOGO VALUES(2,'VERANO 2017');
insert into CATALOGO VALUES(3,'OTOÑO 2017');
insert into CATALOGO VALUES(4,'INVIERNO 2017');
insert into CATALOGO VALUES(5,'PRIMAVERA 2018');
insert into CATALOGO VALUES(6,'VERANO 2018');
insert into CATALOGO VALUES(7,'OTOÑO 2018');
insert into CATALOGO VALUES(8,'INVIERNO 2018');
insert into CATALOGO VALUES(9,'PRIMAVERA 2019');
insert into CATALOGO VALUES(10,'VERANO 2019');


insert into CLIENTE VALUES(1,'ENRIQUE','PASTOR','GALDOS','Calle ave del paraiso 7','71754769F','6130607950','232323123213215545345','$2a$10$wJrn5vr8hbg5kPR6XWGP1.Dzp2Gs7VAS.8c.xSlwxuJ2idcaYPS5C');
insert into CLIENTE VALUES(2,'JAVIER','MAROTO','FERNANDEZ','Callejón Rostà exterminau curibells arruguen 179','34832634P','6975085782','232323123213215545345','$2a$10$wJrn5vr8hbg5kPR6XWGP1.Dzp2Gs7VAS.8c.xSlwxuJ2idcaYPS5C');
insert into CLIENTE VALUES(3,'MAITE','FIGEROA','GONZALEZ','Calle Escritor Conde Zamora s/n','64280842M','6576747772','232323123213215545345','$2a$10$wJrn5vr8hbg5kPR6XWGP1.Dzp2Gs7VAS.8c.xSlwxuJ2idcaYPS5C');
insert into CLIENTE VALUES(4,'AMADOR','RIVAS','VALDES','Avenida del Llano 52','30770086L','6934784198','232323123213215545345','$2a$10$dtiBjODWFalhLhKiFm59euvVpL.VdCZXieoFFsVc/8Fo.yga5dsAK');
insert into CLIENTE VALUES(5,'VICENTE','MAROTO','VIDAL','Calle Antonio Mª Manrique 3','52897943J','6991758782','232323123213215545345','$2a$10$dtiBjODWFalhLhKiFm59euvVpL.VdCZXieoFFsVc/8Fo.yga5dsAK');
insert into CLIENTE VALUES(6,'ANTONIO','RECIO','BERNARDO','Avenida Ciudad de Aranjuez 18','18611062Z','6297797818','232323123213215545345','$2a$10$dtiBjODWFalhLhKiFm59euvVpL.VdCZXieoFFsVc/8Fo.yga5dsAK');
insert into CLIENTE VALUES(7,'LOLA','TRUJILLO','TRAPIELLO','Avenida Pintor Sorolla 125 4ºG','20256129Y','6565433846','232323123213215545345','$2a$10$dtiBjODWFalhLhKiFm59euvVpL.VdCZXieoFFsVc/8Fo.yga5dsAK');
insert into CLIENTE VALUES(8,'COQUE','CALATRAVA','RODRIGUEZ','Avenida Navarra 5','34707631X','6330022908','232323123213215545345','$2a$10$dtiBjODWFalhLhKiFm59euvVpL.VdCZXieoFFsVc/8Fo.yga5dsAK');
insert into CLIENTE VALUES(9,'RAQUEL','VILLANUEVA','MONTES','Calle Illa de Buda 55','94734003Q','6900466084','232323123213215545345','$2a$10$dtiBjODWFalhLhKiFm59euvVpL.VdCZXieoFFsVc/8Fo.yga5dsAK');
insert into CLIENTE VALUES(10,'NINES','CHACON','CARRASCO','Avenida Manuel Rodriguez Ayuso 170','69270716Y','6936426361','232323123213215545345','$2a$10$dtiBjODWFalhLhKiFm59euvVpL.VdCZXieoFFsVc/8Fo.yga5dsAK');


insert into ARTICULO VALUES(1,1,'CHOCOLATE','NESTLE',CURRENT_TIMESTAMP,5,2.5);
insert into ARTICULO VALUES(2,1,'SOBRE SOPA','GALLINA BLANCO',CURRENT_TIMESTAMP,5,0.8);
insert into ARTICULO VALUES(3,1,'LASAÑA','COCINERA',CURRENT_TIMESTAMP,5,2.75);
insert into ARTICULO VALUES(4,2,'ESPARRAGOS','CARRETILLA',CURRENT_TIMESTAMP,5,2.5);
insert into ARTICULO VALUES(5,2,'PIZZA BARBACOA','TARRADIELLAS',CURRENT_TIMESTAMP,5,3.5);
insert into ARTICULO VALUES(6,3,'ZUMO NARANJA','JUVER',CURRENT_TIMESTAMP,5,0.75);
insert into ARTICULO VALUES(7,5,'PASTA LARGA','GALLO',CURRENT_TIMESTAMP,5,0.85);
insert into ARTICULO VALUES(8,6,'ATUN','CALVO',CURRENT_TIMESTAMP,5,1.85);
insert into ARTICULO VALUES(9,6,'OSITOS GOMINOLA','HARIBO',CURRENT_TIMESTAMP,5,1.25);
insert into ARTICULO VALUES(10,7,'ARROZ','SOS',CURRENT_TIMESTAMP,5,1.25);



insert into PEDIDO VALUES(1,1,CURRENT_TIMESTAMP);
insert into PEDIDO VALUES(2,1,CURRENT_TIMESTAMP);
insert into PEDIDO VALUES(3,2,CURRENT_TIMESTAMP);
insert into PEDIDO VALUES(4,3,CURRENT_TIMESTAMP);
insert into PEDIDO VALUES(5,4,CURRENT_TIMESTAMP);
insert into PEDIDO VALUES(6,4,CURRENT_TIMESTAMP);
insert into PEDIDO VALUES(7,4,CURRENT_TIMESTAMP);
insert into PEDIDO VALUES(8,6,CURRENT_TIMESTAMP);
insert into PEDIDO VALUES(9,7,CURRENT_TIMESTAMP);
insert into PEDIDO VALUES(10,9,CURRENT_TIMESTAMP);


insert into PEDIDOARTICULO VALUES(1,1,3);
insert into PEDIDOARTICULO VALUES(1,2,4);
insert into PEDIDOARTICULO VALUES(1,3,1);
insert into PEDIDOARTICULO VALUES(2,2,1);
insert into PEDIDOARTICULO VALUES(1,4,1);
insert into PEDIDOARTICULO VALUES(2,1,1);
insert into PEDIDOARTICULO VALUES(3,1,1);
insert into PEDIDOARTICULO VALUES(4,2,3);
insert into PEDIDOARTICULO VALUES(4,1,1);
insert into PEDIDOARTICULO VALUES(5,2,2);