package pe.area51.proyecto.modelosbd;

/**
 * Created by User on 23/03/2017.
 */

public class DatosTienda {

    private int id,id_tiend;
    private String nomTienda, direcTienda, telefTienda;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_tiend() {
        return id_tiend;
    }

    public void setId_tiend(int id_tiend) {
        this.id_tiend = id_tiend;
    }

    public String getNomTienda() {
        return nomTienda;
    }

    public void setNomTienda(String nomTienda) {
        this.nomTienda = nomTienda;
    }

    public String getDirecTienda() {
        return direcTienda;
    }

    public void setDirecTienda(String direcTienda) {
        this.direcTienda = direcTienda;
    }

    public String getTelefTienda() {
        return telefTienda;
    }

    public void setTelefTienda(String telefTienda) {
        this.telefTienda = telefTienda;
    }
}
