use inventario;
-- ----------------------------------------- --
delimiter $$
create procedure ListarProducto()
begin
	select 
    p.idProd, 
    p.nomProd as Producto, 
    c.nomCat as Categoria, 
    p.canProd, 
    p.desProd, 
    pr.nomProv as Proveedor 
    from Producto p 
	join Categoria c on  p.idCat = c.idCat 
	join Proveedor pr on p.idProv = pr.idProv 
    order by p.idProd asc;
end$$
delimiter ;

call ListarProducto();

-- ----------------------------------------- --

delimiter $$
create procedure AgregarProducto(
in nombre varchar(50),
in cantidad int,
in descripcion varchar(50),
in idCategoria int,
in idProveedor int
)
begin
	INSERT INTO Producto(nomProd, canProd, desProd, idCat, idProv) VALUES(nombre,cantidad,descripcion,idCategoria,idProveedor);
end$$
delimiter ;

call AgregarProducto('Leche',60,'Leche de vaca gloria',1,1);

-- ----------------------------------------- --

Drop procedure if exists ActualizarProducto;
delimiter $$
create procedure ActualizarProducto(
in id int,
in producto varchar(100),
in categoria int,
in cantidad int,
in descripcion varchar(100),
in proveedor int)
begin
	update Producto 
    set 
    nomProd = producto, 
    canProd = cantidad, 
    desProd = descripcion, 
    idCat = categoria, 
    idProv = proveedor 
    where idProd = id;
end $$
delimiter ;

call ActualizarProducto(13,'Yogurt',1,1,'Yogurt hecho de fresa, leche y agua',1);

-- ----------------------------------------- --

Drop procedure if exists buscarProducto;
delimiter $$
create procedure buscarProducto(
in producto varchar(100), 
in categoria varchar(100),
in proveedor varchar(100))
begin
	select 
    p.idProd, 
    p.nomProd as Producto, 
    c.nomCat as Categoria, 
    p.canProd, 
    p.desProd, 
    pr.nomProv as Proveedor 
    from Producto p 
	join Categoria c on  p.idCat = c.idCat 
	join Proveedor pr on p.idProv = pr.idProv
    where 1 = 1
    and p.nomProd like concat('%', producto, '%')
    and c.nomCat like concat('%', categoria, '%')
    and pr.nomProv like concat('%', proveedor, '%');
end$$
delimiter ;

-- ----------------------------------------- --

drop procedure if exists actulizarCantidadProducto;
delimiter $$
create procedure actulizarCantidadProducto(
in id int,
in cantidad int)
begin
	update Producto 
    set canProd = canProd + cantidad 
    where idProd = id;
end$$
delimiter ;

call actulizarCantidadProducto(13,152);
call ListarProducto();
select * from Alerta;

-- ----------------------------------------- --

drop procedure if exists EliminarProducto;
delimiter $$
create procedure EliminarProducto(
in id int)
begin
	delete from Producto where idProd = id;
end$$
delimiter ;

call EliminarProducto(7);
call ListarProducto();

-- ----------------------------------------- --

drop trigger if exists controlCantidadProducto;
delimiter $$
create trigger controlCantidadProducto
before update on Producto
for each row
begin
	if new.canProd < 0 then
		signal sqlstate '45000'
        set message_text = 'No tienes mas productos por el momento';
    
	elseif new.canProd < 10 then
		insert into alerta(
        idProd,
        cantidad,
        mensaje,
        fecha)
        values(
        new.idProd,
        new.canProd,
        'Stock bajo, abastecer',
        now());
	end if;
end$$
delimiter ;

-- ----------------------------------------- --



