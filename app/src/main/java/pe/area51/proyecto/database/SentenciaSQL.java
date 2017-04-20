package pe.area51.proyecto.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import pe.area51.proyecto.modelosbd.ComboComprobante;
import pe.area51.proyecto.modelosbd.DatosComprobante;
import pe.area51.proyecto.modelosbd.DatosLogin;
import pe.area51.proyecto.modelosbd.DatosPreventa;
import pe.area51.proyecto.modelosbd.DatosProducto;
import pe.area51.proyecto.modelosbd.DatosTienda;
import pe.area51.proyecto.modelosbd.DatosVenta;

public class SentenciaSQL {
    private ManagerOpenHelper conexion;

    public SentenciaSQL(Context context) {
        conexion = new ManagerOpenHelper(context);
    }

    public void registrarUser(String nom, String ape, String correo, String telf, String user, String passw) {
        //Asignamos el permiso de escritura a la base de datos
        SQLiteDatabase db = conexion.getWritableDatabase();
        //Creamos las variables con los parametros que vamos a registrar
        ContentValues contentValues = new ContentValues();
        //Agregamos los datos que vasmoa a guardar
        contentValues.put("nombre", nom);
        contentValues.put("apellido", ape);
        contentValues.put("correo", correo);
        contentValues.put("telef", telf);
        contentValues.put("usuario", user);
        contentValues.put("passw", passw);
        //Registrar los datos a nuestra base de datos
        db.insert("tb_login", null, contentValues);
    }

    public void actualizarUser(String nom, String ape, String telf, String correo, int id) {
        //Asignamos el permiso de escritura a la base de datos
        SQLiteDatabase db = conexion.getWritableDatabase();
        //Creamos las variables con los parametros a registrar
        ContentValues contentValues = new ContentValues();
        //Validamos que traiga algun dato
        if (!nom.equals("")) {
            contentValues.put("nombre", nom);
        }
        if (!ape.equals("")) {
            contentValues.put("apellido", ape);
        }
        if (!telf.equals("")) {
            contentValues.put("telef", telf);
        }
        if (!correo.equals("")) {
            contentValues.put("correo", correo);
        }
        //acutaliczamos toda la base de datos
        db.update("tb_login", contentValues, "id=" + id, null);
    }

    public ArrayList<DatosLogin> obtener() {
        //asignamos permiso de solo lectura
        SQLiteDatabase db = conexion.getReadableDatabase();
        //Creamos nuestra sentencia SQL
        Cursor cursor = db.rawQuery("select * from tb_login", null);
        //Creamos una lista de tipo Datos
        ArrayList<DatosLogin> lista = new ArrayList<>();
        //Ejecutamos para verificar si es que hay registros
        if (cursor.moveToFirst()) {
            do {
                //Creamos un objeto donde se almacenaran los datos
                DatosLogin datos = new DatosLogin();
                //Setteamos todos los datos
                datos.setId(cursor.getInt(cursor.getColumnIndex("id")));
                datos.setNomb(cursor.getString(cursor.getColumnIndex("nombre")));
                datos.setApel(cursor.getString(cursor.getColumnIndex("apellido")));
                datos.setCorreo(cursor.getString(cursor.getColumnIndex("correo")));
                datos.setTelf(cursor.getString(cursor.getColumnIndex("telef")));
                datos.setUser(cursor.getString(cursor.getColumnIndex("usuario")));
                datos.setPass(cursor.getString(cursor.getColumnIndex("passw")));
                //Añadimos los datos a la lista
                lista.add(datos);
                //Recorre el bucle siempre y cuando haya registros
            } while (cursor.moveToNext());
        }
        return lista;
    }

    public DatosLogin ListarporId(int id) {
        //Asignamos el permiso de lectura a la base de datos
        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_login where id=" + id, null);
        if (cursor.moveToFirst()) {
            do {
                DatosLogin datosLogin = new DatosLogin();
                datosLogin.setId(id);
                datosLogin.setNomb(cursor.getString(cursor.getColumnIndex("nombre")));
                datosLogin.setApel(cursor.getString(cursor.getColumnIndex("apellido")));
                datosLogin.setTelf(cursor.getString(cursor.getColumnIndex("telef")));
                datosLogin.setCorreo(cursor.getString(cursor.getColumnIndex("correo")));
                return datosLogin;
            } while (cursor.moveToNext());
        }
        return null;
    }

    public DatosLogin obtenerDatoPorUser(String Usuario) {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_login where usuario=" + Usuario, null);
        if (cursor.moveToFirst()) {
            do {
                DatosLogin datos = new DatosLogin();
                datos.setUser(Usuario);
                datos.setPass(cursor.getString(cursor.getColumnIndex("passw")));
                return datos;
            } while (cursor.moveToNext());
        }
        return null;
    }

    public String obtenerIdconUser(String userName) {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.query("tb_login", null, " usuario=?", new String[]{userName}, null, null, null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            cursor.close();
            return "No Existe";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("id"));
        cursor.close();
        return password;
    }

    public String obtenerPassconUser(String userName) {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.query("tb_login", null, " usuario=?", new String[]{userName}, null, null, null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            cursor.close();
            return "No Existe";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("passw"));
        cursor.close();
        return password;
    }

    public String obtenerNombreconUser(String userName) {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.query("tb_login", null, " usuario=?", new String[]{userName}, null, null, null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            cursor.close();
            return "No Existe";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("nombre"));
        cursor.close();
        return password;
    }

    public String seencuentra(String userName) {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.query("tb_login", null, " usuario=?", new String[]{userName}, null, null, null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            cursor.close();
            return "No Existe";
        }
        cursor.moveToFirst();
        String password = "SI";
        cursor.close();
        return password;
    }

    public void registrarComprobante(int id_user, String tipo, String razon, String direc, String doc_ident) {
        //Asignamos el permiso de escritura a la base de datos
        SQLiteDatabase db = conexion.getWritableDatabase();
        //Creamos las variables con los parametros que vamos a registrar
        ContentValues contentValues = new ContentValues();
        //Agregamos los datos que vasmoa a guardar
        contentValues.put("id_user", id_user);
        contentValues.put("tipo", tipo);
        contentValues.put("razon", razon);
        contentValues.put("direc", direc);
        contentValues.put("doc_ident", doc_ident);
        //Registrar los datos a nuestra base de datos
        db.insert("tb_comprobante", null, contentValues);
    }

    public ArrayList<DatosComprobante> obtenerComprobante() {
        //asignamos permiso de solo lectura
        SQLiteDatabase db = conexion.getReadableDatabase();
        //Creamos nuestra sentencia SQL
        Cursor cursor = db.rawQuery("select * from tb_comprobante", null);
        //Creamos una lista de tipo Datos
        ArrayList<DatosComprobante> lista = new ArrayList<>();
        //Ejecutamos para verificar si es que hay registros
        if (cursor.moveToFirst()) {
            do {
                //Creamos un objeto donde se almacenaran los datos
                DatosComprobante datos = new DatosComprobante();
                //Setteamos todos los datos
                datos.setId_user(cursor.getInt(cursor.getColumnIndex("id_user")));
                datos.setTipo(cursor.getString(cursor.getColumnIndex("tipo")));
                datos.setNomRaz(cursor.getString(cursor.getColumnIndex("razon")));
                datos.setDirec(cursor.getString(cursor.getColumnIndex("direc")));
                datos.setDniRuc(cursor.getString(cursor.getColumnIndex("doc_ident")));
                //Añadimos los datos a la lista
                lista.add(datos);
                //Recorre el bucle siempre y cuando haya registros
            } while (cursor.moveToNext());
        }
        return lista;
    }

    public ArrayList<DatosTienda> obtenerTienda() {
        //asignamos permiso de solo lectura
        SQLiteDatabase db = conexion.getReadableDatabase();
        //Creamos nuestra sentencia SQL
        Cursor cursor = db.rawQuery("select * from tb_tienda", null);
        //Creamos una lista de tipo Datos
        ArrayList<DatosTienda> lista = new ArrayList<>();
        //Ejecutamos para verificar si es que hay registros
        if (cursor.moveToFirst()) {
            do {
                //Creamos un objeto donde se almacenaran los datos
                DatosTienda datos = new DatosTienda();
                //Setteamos todos los datos
                datos.setId_tiend(cursor.getInt(cursor.getColumnIndex("id_tienda")));
                datos.setNomTienda(cursor.getString(cursor.getColumnIndex("tienda_nombre")));
                datos.setDirecTienda(cursor.getString(cursor.getColumnIndex("tienda_direc")));
                datos.setTelefTienda(cursor.getString(cursor.getColumnIndex("tienda_telef")));
                //Añadimos los datos a la lista
                lista.add(datos);
                //Recorre el bucle siempre y cuando haya registros
            } while (cursor.moveToNext());
        }
        return lista;
    }

    public ArrayList<String> obtenerComprobantePorUsuario(int id_user) {
        //asignamos permiso de solo lectura
        SQLiteDatabase db = conexion.getReadableDatabase();
        //Creamos nuestra sentencia SQL
        Cursor cursor = db.rawQuery("select * from tb_comprobante where id_user=" + id_user, null);
        //Creamos una lista de tipo Datos
        ArrayList<String> lista = new ArrayList<>();
        //Ejecutamos para verificar si es que hay registros
        if (cursor.moveToFirst()) {
            do {
                lista.add(cursor.getString(cursor.getColumnIndex("tipo")) + " - " +
                        cursor.getString(cursor.getColumnIndex("razon")));
                //Recorre el bucle siempre y cuando haya registros
            } while (cursor.moveToNext());
        }
        return lista;
    }

    public void eliminarComprobante(int id) {
        SQLiteDatabase db = conexion.getWritableDatabase();
        db.delete("tb_comprobante", "id=" + id, null);
    }

    public ArrayList<DatosProducto> obtenerProducto(int id) {
        //asignamos permiso de solo lectura
        SQLiteDatabase db = conexion.getReadableDatabase();
        //Creamos nuestra sentencia SQL
        Cursor cursor = db.rawQuery("select * from tb_producto_tienda where id_tienda="+id, null);
        //Creamos una lista de tipo Datos
        ArrayList<DatosProducto> lista = new ArrayList<>();
        //Ejecutamos para verificar si es que hay registros
        if (cursor.moveToFirst()) {
            do {
                //Creamos un objeto donde se almacenaran los datos
                DatosProducto datos = new DatosProducto();
                //Setteamos todos los datos
                datos.setProducto(cursor.getString(cursor.getColumnIndex("producto")));
                datos.setPrecio(cursor.getString(cursor.getColumnIndex("precio")));
                //Añadimos los datos a la lista
                lista.add(datos);
                //Recorre el bucle siempre y cuando haya registros
            } while (cursor.moveToNext());
        }
        return lista;
    }

    public void registrarPreventa(String tienda,String id_detalle, String product, String precioUni, String cant, String PrecioTot) {
        //Asignamos el permiso de escritura a la base de datos
        SQLiteDatabase db = conexion.getWritableDatabase();
        //Creamos las variables con los parametros que vamos a registrar
        ContentValues contentValues = new ContentValues();
        //Agregamos los datos que vasmoa a guardar
        contentValues.put("tienda", tienda);
        contentValues.put("id_detalle", id_detalle);
        contentValues.put("producto", product);
        contentValues.put("precio_unit", precioUni);
        contentValues.put("cant", cant);
        contentValues.put("precio_total", PrecioTot);
        //Registrar los datos a nuestra base de datos
        db.insert("tb_pre_venta", null, contentValues);
    }

    public ArrayList<DatosPreventa> listarPreVenta() {
        //asignamos permiso de solo lectura
        SQLiteDatabase db = conexion.getReadableDatabase();
        //Creamos nuestra sentencia SQL
        Cursor cursor = db.rawQuery("select * from tb_pre_venta", null);
        //Creamos una lista de tipo Datos
        ArrayList<DatosPreventa> lista = new ArrayList<>();
        //Ejecutamos para verificar si es que hay registros
        if (cursor.moveToFirst()) {
            do {
                //Creamos un objeto donde se almacenaran los datos
                DatosPreventa datos = new DatosPreventa();
                //Setteamos todos los datos
                datos.setProducto(cursor.getString(cursor.getColumnIndex("producto")));
                datos.setPrecio_uni(cursor.getString(cursor.getColumnIndex("precio_unit")));
                datos.setCant(cursor.getInt(cursor.getColumnIndex("cant")));
                datos.setPrecio_tot(cursor.getString(cursor.getColumnIndex("precio_total")));
                //Añadimos los datos a la lista
                lista.add(datos);
                //Recorre el bucle siempre y cuando haya registros
            } while (cursor.moveToNext());
        }
        return lista;
    }

    public String obtenerTiendaconId(String id) {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.query("tb_pre_venta", null, " id_detalle=?", new String[]{id}, null, null, null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            cursor.close();
            return "No Existe";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("tienda"));
        cursor.close();
        return password;
    }

    public DatosTienda obtenerDatoporTienda(String tienda) {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_tienda where tienda_nombre=" +"'"+ tienda+"'", null);
        if (cursor.moveToFirst()) {
            do {
                DatosTienda datos = new DatosTienda();
                datos.setDirecTienda(cursor.getString(cursor.getColumnIndex("tienda_direc")));
                datos.setTelefTienda(cursor.getString(cursor.getColumnIndex("tienda_telef")));
                return datos;
            } while (cursor.moveToNext());
        }
        return null;
    }

    public void registrarVenta(String id_preventa, String id_tienda, String total_pago) {
        //Asignamos el permiso de escritura a la base de datos
        SQLiteDatabase db = conexion.getWritableDatabase();
        //Creamos las variables con los parametros que vamos a registrar
        ContentValues contentValues = new ContentValues();
        //Agregamos los datos que vasmoa a guardar
        contentValues.put("id_preventa", id_preventa);
        contentValues.put("id_tienda", id_tienda);
        contentValues.put("total_pago", total_pago);
        //Registrar los datos a nuestra base de datos
        db.insert("tb_venta", null, contentValues);
    }

    public ArrayList<DatosPreventa> listarPreVentaporID(String id_pre) {
        //asignamos permiso de solo lectura
        SQLiteDatabase db = conexion.getReadableDatabase();
        //Creamos nuestra sentencia SQL
        Cursor cursor = db.rawQuery("select * from tb_pre_venta where id_detalle="+"'"+id_pre+"'", null);
        //Creamos una lista de tipo Datos
        ArrayList<DatosPreventa> lista = new ArrayList<>();
        //Ejecutamos para verificar si es que hay registros
        if (cursor.moveToFirst()) {
            do {
                //Creamos un objeto donde se almacenaran los datos
                DatosPreventa datos = new DatosPreventa();
                //Setteamos todos los datos
                datos.setProducto(cursor.getString(cursor.getColumnIndex("producto")));
                datos.setPrecio_uni(cursor.getString(cursor.getColumnIndex("precio_unit")));
                datos.setCant(cursor.getInt(cursor.getColumnIndex("cant")));
                datos.setPrecio_tot(cursor.getString(cursor.getColumnIndex("precio_total")));
                //Añadimos los datos a la lista
                lista.add(datos);
                //Recorre el bucle siempre y cuando haya registros
            } while (cursor.moveToNext());
        }
        return lista;
    }

    public ArrayList<DatosVenta> ListarVentas() {
        //asignamos permiso de solo lectura
        SQLiteDatabase db = conexion.getReadableDatabase();
        //Creamos nuestra sentencia SQL
        Cursor cursor = db.rawQuery("select * from tb_venta", null);
        //Creamos una lista de tipo Datos
        ArrayList<DatosVenta> lista = new ArrayList<>();
        //Ejecutamos para verificar si es que hay registros
        if (cursor.moveToFirst()) {
            do {
                //Creamos un objeto donde se almacenaran los datos
                DatosVenta datos = new DatosVenta();
                //Setteamos todos los datos
                datos.setId_tienda(cursor.getString(cursor.getColumnIndex("id_tienda")));
                datos.setId_preventa(cursor.getString(cursor.getColumnIndex("id_preventa")));
                datos.setTotal_pago(cursor.getString(cursor.getColumnIndex("total_pago")));
                //Añadimos los datos a la lista
                lista.add(datos);
                //Recorre el bucle siempre y cuando haya registros
            } while (cursor.moveToNext());
        }
        return lista;
    }

    public ArrayList<ComboComprobante> obtenerComprobporUser(int id_user) {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_comprobante where id_user ="+id_user, null);
        ArrayList<ComboComprobante> lista = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                lista.add(new ComboComprobante(cursor.getString(cursor.getColumnIndex("tipo")), cursor.getString(cursor.getColumnIndex("razon"))));
            } while (cursor.moveToNext());
        }
        return lista;
    }

    public DatosComprobante obtenerComprobanteporRazon(String razon) {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_comprobante where razon ="+"'"+ razon+"'", null);
        if (cursor.moveToFirst()) {
            do {
                DatosComprobante datos = new DatosComprobante();
                datos.setTipo(cursor.getString(cursor.getColumnIndex("tipo")));
                datos.setDniRuc(cursor.getString(cursor.getColumnIndex("doc_ident")));
                datos.setDirec(cursor.getString(cursor.getColumnIndex("direc")));
                return datos;
            } while (cursor.moveToNext());
        }
        return null;
    }

    public String obtenerIdconRazon(String razon) {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.query("tb_comprobante", null, " razon=?", new String[]{razon}, null, null, null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            cursor.close();
            return "No Existe";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("id_comprobante"));
        cursor.close();
        return password;
    }

    public void actualizarcomprobante(String razon, String direc, String DniRuc, int id) {
        //Asignamos el permiso de escritura a la base de datos
        SQLiteDatabase db = conexion.getWritableDatabase();
        //Creamos las variables con los parametros a registrar
        ContentValues contentValues = new ContentValues();
        //Validamos que traiga algun dato
        if (!razon.equals("")) {
            contentValues.put("razon", razon);
        }
        if (!direc.equals("")) {
            contentValues.put("direc", direc);
        }
        if (!DniRuc.equals("")) {
            contentValues.put("doc_ident", DniRuc);
        }
        //acutaliczamos toda la base de datos
        db.update("tb_comprobante", contentValues, "id_comprobante=" + id, null);
    }
}
