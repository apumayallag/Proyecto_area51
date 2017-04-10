package pe.area51.proyecto.modelosbd;

import java.util.ArrayList;

/**
 * Created by Alexis P on 05/04/2017.
 */

public class DatosProducto extends ArrayList<DatosProducto> {

    private int id;
    private String id_tienda, producto, precio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_tienda() {
        return id_tienda;
    }

    public void setId_tienda(String id_tienda) {
        this.id_tienda = id_tienda;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
