package Objects;

public class denuncias {
    private int idDenuncias;
    private int documento;
    private int idSitio;
    private String descripcion;
    private String estado;
    private int aceptaREsponsabilidad;

    public int getIdDenuncias() {
        return idDenuncias;
    }

    public void setIdDenuncias(int idDenuncias) {
        this.idDenuncias = idDenuncias;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public int getIdSitio() {
        return idSitio;
    }

    public void setIdSitio(int idSitio) {
        this.idSitio = idSitio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getAceptaREsponsabilidad() {
        return aceptaREsponsabilidad;
    }

    public void setAceptaREsponsabilidad(int aceptaREsponsabilidad) {
        this.aceptaREsponsabilidad = aceptaREsponsabilidad;
    }
}
