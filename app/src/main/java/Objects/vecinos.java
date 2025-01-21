package Objects;

import java.io.Serializable;

public class vecinos implements Serializable {
    private String documento;
    private String nombre;
    private String apellido;
    private String direccion;
    private int codigoBarrio;

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCodigoBarrio() {
        return codigoBarrio;
    }

    public void setCodigoBarrio(int codigoBarrio) {
        this.codigoBarrio = codigoBarrio;
    }
}