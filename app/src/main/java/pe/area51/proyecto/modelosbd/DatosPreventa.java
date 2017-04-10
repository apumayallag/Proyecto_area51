package pe.area51.proyecto.modelosbd;

/**
 * Created by Alexis P on 07/04/2017.
 */

public class DatosPreventa {
    private int id, cant;
    private String tienda, producto, precio_uni, precio_tot;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTienda() {
        return tienda;
    }

    public void setTienda(String tienda) {
        this.tienda = tienda;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getPrecio_uni() {
        return precio_uni;
    }

    public void setPrecio_uni(String precio_uni) {
        this.precio_uni = precio_uni;
    }

    public String getPrecio_tot() {
        return precio_tot;
    }

    public void setPrecio_tot(String precio_tot) {
        this.precio_tot = precio_tot;
    }
}
