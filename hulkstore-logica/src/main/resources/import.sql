-- Usuarios
insert into Usuario(usuario_id, nombre, correo, celular, contrasenia) values (1, 'Lenin Alomoto', 'lenin.alomoto@hotmail.com', '2125551212', '1234');
insert into Usuario(usuario_id, nombre, correo, celular, contrasenia) values (2, 'Wilson Lobato', 'wilson.lobato@gmail.com', '2125551212', '1234');

-- Productos 
insert into Producto(producto_id, nombre, descripcion, cantidad, precio, fecha_ingreso, estado) values (1, 'Jarro Simpson', 'Esta es una descripción', 1, 20.33, SYSDATE, 'ACT');
insert into Producto(producto_id, nombre, descripcion, cantidad, precio, fecha_ingreso, estado) values (2, 'Revista Superman', 'Esta es una descripción', 0, 100, SYSDATE, 'ACT');
insert into Producto(producto_id, nombre, descripcion, cantidad, precio, fecha_ingreso, estado) values (3, 'Camiseta Goku', 'Esta es una descripción', 3, 10, SYSDATE, 'INA');
insert into Producto(producto_id, nombre, descripcion, cantidad, precio, fecha_ingreso, estado) values (4, 'Legolas', 'Esta es una descripción', 5, 15.25, SYSDATE, 'ACT');
insert into Producto(producto_id, nombre, descripcion, cantidad, precio, fecha_ingreso, estado) values (5, 'Batman', 'Esta es una descripción', 25, 12, SYSDATE, 'ACT');

-- Pedidos 
insert into Pedido(pedido_id, usuario_id, producto_id, cantidad, subtotal, fecha_compra) values (1, 2, 5, 2, 24,SYSDATE);
