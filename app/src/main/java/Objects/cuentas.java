package Objects;

import java.io.Serializable;

public class cuentas implements Serializable {

    private String documento;
    private String nombre;
    private String apellido;
    private String correo;
    private String contrasenia;
    private Number esVecino;
    private String direccion;
    private Integer codBarrio;


    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Number getEsVecino() {
        return esVecino;
    }

    public void setEsVecino(Number esVecino) {
        this.esVecino = esVecino;
    }

    public Integer getCodBarrio() {
        return codBarrio;
    }

    public void setCodBarrio(Integer codigoBarrio) {
        this.codBarrio = codigoBarrio;
    }
}
