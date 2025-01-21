package Objects;

import java.util.List;

public class rubros {
    private int idRubro;
    private String descripcion;
    private String categoria;
    private boolean isProfesional;
    private List<String> promociones;

    public int getIdRubro() {
        return idRubro;
    }

    public void setIdRubro(int idRubro) {
        this.idRubro = idRubro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean esProfesional() {
        return isProfesional;
    }

    public void setEsProfesional(boolean isProfesional) {
        this.isProfesional = isProfesional;
    }

    public List<String> getPromociones() {
        return promociones;
    }

    public void setPromociones(List<String> promociones) {
        this.promociones = promociones;
    }
}
