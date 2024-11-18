
import java.util.Date;

public class Factura {
    private static int contadorFacturas = 1;
    private String codigo;
    private Cliente cliente;
    private String fechaServicio;
    private String descripcionServicio;
    private double costoServicio;
    private int creditoDias;
    private boolean pagado;
    private boolean anulada;
    private String motivoAnulacion;
    private Date fechaRegistro;

    public Factura(Cliente cliente, String fechaServicio, String descripcionServicio,
                   double costoServicio, int creditoDias, Date fechaRegistro) {
        this.codigo = "E001-" + contadorFacturas++;
        this.cliente = cliente;
        this.fechaServicio = fechaServicio;
        this.descripcionServicio = descripcionServicio;
        this.costoServicio = costoServicio;
        this.creditoDias = creditoDias;
        this.pagado = false;
        this.anulada = false;
        this.motivoAnulacion = "";
        this.fechaRegistro = fechaRegistro;
    }

    public String getCodigo() {
        return codigo;
    }

    public boolean isAnulada() {
        return anulada;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public boolean isPagado() {
        return pagado;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public int getCreditoDias() {
        return creditoDias;
    }

    public void registrarPago() {
        this.pagado = true;
    }

    public void anularFactura(String motivo) {
        this.anulada = true;
        this.motivoAnulacion = motivo;
    }

    public void mostrarDetalles() {
        System.out.println("\nCódigo de Factura: " + codigo);
        System.out.println("Cliente: " + cliente.getRazonSocial() + " (RUC: " + cliente.getRuc() + ")");
        System.out.println("Fecha de Servicio: " + fechaServicio);
        System.out.println("Descripción del Servicio: " + descripcionServicio);
        System.out.println("Costo del Servicio: $" + costoServicio);
        System.out.println("Estado: " + (anulada ? "Anulada (Motivo: " + motivoAnulacion + ")" :
                (pagado ? "Pagado" : "Pendiente de pago")));
    }

}
