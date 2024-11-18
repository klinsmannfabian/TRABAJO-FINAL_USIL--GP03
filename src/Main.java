
import java.util.ArrayList;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Main {
    private static ArrayList<Factura> facturas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) throws ParseException {
        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    emitirFactura();
                    break;
                case 2:
                    cuentasPorCobrar();
                    break;
                case 3:
                    anularFactura();
                    break;
                case 4:
                    registrarPago();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    public static void mostrarMenu() {
        System.out.println("\n--- Sistema de Facturación ---");
        System.out.println("1. Emisión de Factura");
        System.out.println("2. Cuentas por Cobrar");
        System.out.println("3. Anular Factura");
        System.out.println("4. Registrar Pago");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public static void emitirFactura() throws ParseException {
        System.out.print("Ingrese RUC del cliente : ");
        String ruc = scanner.nextLine();
        System.out.print("Ingrese Razón Social del cliente: ");
        String razonSocial = scanner.nextLine();
        Cliente cliente = new Cliente(ruc, razonSocial);

        System.out.print("Ingrese fecha de servicio (dd/MM/yyyy): ");
        String fechaServicio = scanner.nextLine();
        System.out.print("Ingrese descripción del servicio: ");
        String descripcion = scanner.nextLine();
        System.out.print("Ingrese costo del servicio: ");
        double costo = scanner.nextDouble();
        System.out.print("Ingrese días de crédito: ");
        int credito = scanner.nextInt();
        scanner.nextLine();

        Date fechaRegistro = new Date();
        Factura factura = new Factura(cliente, fechaServicio, descripcion, costo, credito, fechaRegistro);
        facturas.add(factura);

        System.out.println("\nFactura registrada con código: " + factura.getCodigo());
    }

    public static void cuentasPorCobrar() throws ParseException {
        System.out.print("Ingrese código de la factura a consultar: ");
        String codigo = scanner.nextLine();
        Factura facturaEncontrada = buscarFactura(codigo.trim());

        if (facturaEncontrada != null && !facturaEncontrada.isAnulada()) {
            long diasVencidos = (new Date().getTime() - facturaEncontrada.getFechaRegistro().getTime()) / (1000 * 60 * 60 * 24);
            String estado = (diasVencidos > facturaEncontrada.getCreditoDias()) ? "Vencido" : "Vigente";
            System.out.println("Estado de la factura: " + estado);
            facturaEncontrada.mostrarDetalles();
        } else {
            System.out.println("Factura no encontrada o anulada.");
        }
    }

    public static Factura buscarFactura(String codigo) {
        for (Factura factura : facturas) {
            if (factura.getCodigo().equalsIgnoreCase(codigo)) {
                return factura;
            }
        }
        return null;
    }

    public static void anularFactura() {
        System.out.print("Ingrese código de la factura a anular: ");
        String codigo = scanner.nextLine();
        Factura facturaEncontrada = buscarFactura(codigo);

        if (facturaEncontrada != null && !facturaEncontrada.isPagado()) {
            System.out.print("Ingrese motivo de anulación: ");
            String motivo = scanner.nextLine();
            facturaEncontrada.anularFactura(motivo);
            System.out.println("Factura anulada exitosamente.");
        } else {
            System.out.println("Factura no encontrada o ya pagada.");
        }
    }

    public static void registrarPago() {
        System.out.print("Ingrese código de la factura a pagar: ");
        String codigo = scanner.nextLine();
        Factura facturaEncontrada = buscarFactura(codigo);
        if (facturaEncontrada != null && !facturaEncontrada.isPagado()) {
            facturaEncontrada.registrarPago();
            System.out.println("Pago registrado correctamente.");
        } else {
            System.out.println("Factura no encontrada o ya está pagada.");
}
}

}