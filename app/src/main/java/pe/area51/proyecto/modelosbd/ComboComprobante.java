package pe.area51.proyecto.modelosbd;

/**
 * Created by AlexisP on 12/04/2017.
 */

public class ComboComprobante {
    String tipo, texto;

    public ComboComprobante(String tipo, String texto) {
        this.tipo = tipo;
        this.texto = texto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
