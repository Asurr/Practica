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