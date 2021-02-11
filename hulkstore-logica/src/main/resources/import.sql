-- Usuarios
insert into Usuario(usuario_id, nombre, identificacion, correo, celular) values (1, 'Lenin Alomoto', '1234567890','lenin.alomoto@hotmail.com', '2125551212');
insert into Usuario(usuario_id, nombre, identificacion, correo, celular) values (2, 'Wilson Lobato', '0123456789','wilson.lobato@gmail.com', '2125551212');

-- Productos 
insert into Producto(producto_id, nombre, cantidad, precio, fecha_ingreso, estado) values (1, 'Jarro Simpson', 1, 20.33, SYSDATE, 'ACT');
insert into Producto(producto_id, nombre, cantidad, precio, fecha_ingreso, estado) values (2, 'Revista Superman', 0, 100, SYSDATE, 'ACT');
insert into Producto(producto_id, nombre, cantidad, precio, fecha_ingreso, estado) values (3, 'Camiseta Goku', 3, 10, SYSDATE, 'INA');
insert into Producto(producto_id, nombre, cantidad, precio, fecha_ingreso, estado) values (4, 'Legolas', 5, 15.25, SYSDATE, 'ACT');
insert into Producto(producto_id, nombre, cantidad, precio, fecha_ingreso, estado) values (5, 'Batman', 25, 12, SYSDATE, 'ACT');

-- Pedidos 
insert into Pedido(pedido_id, usuario_id, producto_id, cantidad, subtotal, fecha_compra) values (1, 2, 5, 2, 24,SYSDATE);
