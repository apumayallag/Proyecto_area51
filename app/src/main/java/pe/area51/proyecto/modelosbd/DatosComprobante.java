package pe.area51.proyecto.modelosbd;

/**
 * Created by User on 22/03/2017.
 */

public class DatosComprobante {
    private int id,id_user;
    private String tipo, nomRaz, DniRuc, direc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNomRaz() {
        return nomRaz;
    }

    public void setNomRaz(String nomRaz) {
        this.nomRaz = nomRaz;
    }

    public String getDniRuc() {
        return DniRuc;
    }

    public void setDniRuc(String dniRuc) {
        DniRuc = dniRuc;
    }

    public String getDirec() {
        return direc;
    }

    public void setDirec(String direc) {
        this.direc = direc;
    }
}
