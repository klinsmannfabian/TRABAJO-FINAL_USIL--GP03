import java.util.Date;
import java.util.ArrayList;
public class Historial {
    private Date fechaAccion;
    private String usuario;
    private String accion;

    public Historial(Date fechaAccion, String usuario, String accion) {
        this.fechaAccion = fechaAccion;
        this.usuario = usuario;
        this.accion = accion;
    }

    public Date getFechaAccion() {
        return fechaAccion;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getAccion() {
        return accion;
    }

    public static void registrarAccion(ArrayList<Historial> historial, String usuario, String accion) {
        Historial nuevaAccion = new Historial(new Date(), usuario, accion);
        historial.add(nuevaAccion);
        System.out.println("Acción registrada: " + accion);
    }

    public static void mostrarHistorial(ArrayList<Historial> historial) {
        System.out.println("\n--- Historial de Acciones ---");
        for (Historial h : historial) {
            System.out.println(h.getFechaAccion() + " - Usuario: " + h.getUsuario() + " - Acción: " + h.getAccion());
        }
    }
}
