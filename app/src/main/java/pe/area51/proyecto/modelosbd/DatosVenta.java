package pe.area51.proyecto.modelosbd;

/**
 * Created by Alexis P on 10/04/2017.
 */

public class DatosVenta {
    private int id;
    private String id_preventa, id_tienda, total_pago;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_preventa() {
        return id_preventa;
    }

    public void setId_preventa(String id_preventa) {
        this.id_preventa = id_preventa;
    }

    public String getId_tienda() {
        return id_tienda;
    }

    public void setId_tienda(String id_tienda) {
        this.id_tienda = id_tienda;
    }

    public String getTotal_pago() {
        return total_pago;
    }

    public void setTotal_pago(String total_pago) {
        this.total_pago = total_pago;
    }
}
