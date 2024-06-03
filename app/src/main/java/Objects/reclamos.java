package Objects;

public class reclamos {
    private int idReclamo;
    private int documento;
    private int idSitio;
    private int idDesperfecto;
    private String descripcion;
    private String estado;
    private int idReclamoUnificado;

    public int getIdReclamo() {
        return idReclamo;
    }

    public void setIdReclamo(int idReclamo) {
        this.idReclamo = idReclamo;
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

    public int getIdDesperfecto() {
        return idDesperfecto;
    }

    public void setIdDesperfecto(int idDesperfecto) {
        this.idDesperfecto = idDesperfecto;
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

    public int getIdReclamoUnificado() {
        return idReclamoUnificado;
    }

    public void setIdReclamoUnificado(int idReclamoUnificado) {
        this.idReclamoUnificado = idReclamoUnificado;
    }
}
