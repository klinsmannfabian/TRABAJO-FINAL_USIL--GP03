
import java.util.Date;

public class Factura {
    private static int contadorFacturas = 1;
    private String codigo;
    private Cliente cliente;
    private Servicio servicio;
    private int creditoDias;
    private boolean pagado;
    private boolean anulada;
    private String motivoAnulacion;

    public Factura(Cliente cliente, Servicio servicio, int creditoDias) {
        this.codigo = "E001-" + contadorFacturas++;
        this.cliente = cliente;
        this.servicio = servicio;
        this.creditoDias = creditoDias;
        this.pagado = false;
        this.anulada = false;
        this.motivoAnulacion = "";
    }

    public String getCodigo() {
        return codigo;
    }

    public boolean isPagado() {
        return pagado;
    }

    public boolean isAnulada() {
        return anulada;
    }

    public void registrarPago(double monto) {
        if (monto >= servicio.getPrecio()) {
            this.pagado = true;
        }
    }

    public void anularFactura(String motivo) {
        this.anulada = true;
        this.motivoAnulacion = motivo;
    }

    public void mostrarDetalles() {
        System.out.println("\nFactura: " + codigo);
        System.out.println("Cliente: " + cliente.getRazonSocial());
        System.out.println("Descripción del Servicio: " + servicio.getDescripcion());
        System.out.println("Fecha de Servicio: " + servicio.getFechaServicio());
        System.out.println("Total: $" + servicio.getPrecio() + " | Estado: " + (pagado ? "Pagado" : anulada ? "Anulada (Motivo: " + motivoAnulacion + ")" : "Pendiente"));
    }
}