package Objects;

public class denuncias {
    private int idDenuncia;
    private String documento;
    private int idSitio;
    private String descripcion;
    private String estado;
    private int aceptaResponsabilidad;

    public int getIdDenuncias() {
        return idDenuncia;
    }

    public void setIdDenuncias(int idDenuncia) {
        this.idDenuncia = idDenuncia;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
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

    public int getAceptaResponsabilidad() {
        return aceptaResponsabilidad;
    }

    public void setAceptaResponsabilidad(int aceptaResponsabilidad) {
        this.aceptaResponsabilidad = aceptaResponsabilidad;
    }
}
