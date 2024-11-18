
import java.util.Date;
public class Servicio {
    private Date fechaServicio;
    private String descripcion;
    private double precio;

    public Servicio(Date fechaServicio, String descripcion, double precio) {
        this.fechaServicio = fechaServicio;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Date getFechaServicio() {
        return fechaServicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }
}