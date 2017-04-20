package pe.area51.proyecto.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 14/03/2017.
 */

public class ManagerOpenHelper extends SQLiteOpenHelper {
    public static final String EMPTY = "";

    public ManagerOpenHelper(Context context) {
        //1 = versión 1 de nuestra base de datos
        super(context, "proyecto", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tb_login (id integer primary key autoincrement, nombre varchar(100), apellido varchar(100), correo varchar(100), telef varchar(100), usuario varchar(100), passw varchar(100))");
        db.execSQL("insert into tb_login (nombre, apellido, correo, telef, usuario, passw) values('Nombre', 'Apellido', 'correo@dominio.ext', '123456789', 'admin', 'admin')");
        db.execSQL("create table tb_comprobante (id_comprobante integer primary key autoincrement, id_user integer(100), tipo varchar(100), razon varchar(100), direc varchar(100), doc_ident varchar(100))");
        db.execSQL("insert into tb_comprobante (id_user, tipo, razon, direc, doc_ident) values('1', 'Comprobante de Pago', 'Nombre o Razon Social', 'Direccion', 'DNI o RUC')");
        db.execSQL("create table tb_tienda (id integer primary key autoincrement, id_tienda integer(100), tienda_nombre varchar(100), tienda_direc varchar(100), tienda_telef varchar(100))");
        db.execSQL("insert into tb_tienda (id_tienda, tienda_nombre, tienda_direc, tienda_telef) values('1', 'Tienda01', 'Direccion de la tienda', 'Telefono de la tienda'),('2','Tienda02', 'Direccion de la tienda', 'Telefono de la tienda'),('3','Tienda03', 'Direccion de la tienda', 'Telefono de la tienda')");
        db.execSQL("create table tb_producto_tienda (id integer primary key autoincrement, id_tienda integer(100), producto varchar(100), precio varchar(100))");
        db.execSQL("insert into tb_producto_tienda (id_tienda, producto, precio) values('1', 'Paquete de Leche', '10')");
        db.execSQL("insert into tb_producto_tienda (id_tienda, producto, precio) values('1', 'Paquete de Arroz', '20')");
        db.execSQL("insert into tb_producto_tienda (id_tienda, producto, precio) values('1', 'Paquete de Azucar', '30')");
        db.execSQL("insert into tb_producto_tienda (id_tienda, producto, precio) values('1', 'Paquete de latas', '40')");
        db.execSQL("insert into tb_producto_tienda (id_tienda, producto, precio) values('2', 'Lata de Atun', '20')");
        db.execSQL("insert into tb_producto_tienda (id_tienda, producto, precio) values('2', 'Lata de durazon', '30')");
        db.execSQL("insert into tb_producto_tienda (id_tienda, producto, precio) values('2', 'Lata de Pepino', '10')");
        db.execSQL("insert into tb_producto_tienda (id_tienda, producto, precio) values('2', 'Lata de rabano', '5')");
        db.execSQL("insert into tb_producto_tienda (id_tienda, producto, precio) values('3', 'Bolsa de Basura', '1')");
        db.execSQL("insert into tb_producto_tienda (id_tienda, producto, precio) values('3', 'Bolsa de Regalo', '2')");
        db.execSQL("insert into tb_producto_tienda (id_tienda, producto, precio) values('3', 'Bolsa de Rafia', '3')");
        db.execSQL("insert into tb_producto_tienda (id_tienda, producto, precio) values('3', 'Bolsa de Plastico', '4')");
        db.execSQL("create table tb_pre_venta (id integer primary key autoincrement,id_detalle varchar(100), tienda varchar(100), producto varchar(100), precio_unit varchar(100), cant integer(100), precio_total varchar(100))");
        db.execSQL("create table tb_venta (id integer primary key autoincrement,id_preventa varchar(100), id_tienda varchar(100), total_pago varchar(100))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion == 2) {
            //Sentencia a realizar
        }
    }
}
