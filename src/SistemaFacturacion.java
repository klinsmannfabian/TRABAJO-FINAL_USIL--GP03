
import java.util.ArrayList;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SistemaFacturacion {
    private static ArrayList<Factura> facturas = new ArrayList<>();
    private static ArrayList<Cliente> clientes = new ArrayList<>();
    private static ArrayList<Historial> historial = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) throws ParseException {
        UsuarioAdmin admin = new UsuarioAdmin("admin", "admin123", "Admin");
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
                    consultarFacturas();
                    break;
                case 3:
                    registrarPago();
                    break;
                case 4:
                    Reporte.generarReporte(facturas);
                    break;
                case 5:
                    System.out.print("Ingrese RUC del cliente : ");
                    String ruc = scanner.nextLine();
                    System.out.print("Ingrese Razón Sociales del clientes : ");
                    String razonSocial = scanner.nextLine();
                    admin.agregarCliente(clientes, ruc, razonSocial);
                    Historial.registrarAccion(historial, admin.getNombreUsuario(), "Agregó un cliente");
                    break;
                case 6:
                    Historial.mostrarHistorial(historial);
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
        System.out.println("2. Consultar y Anular Facturas");
        System.out.println("3. Registrar Pago");
        System.out.println("4. Generar Reporte");
        System.out.println("5. Agregar Cliente");
        System.out.println("6. Mostrar Historial");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public static void emitirFactura() throws ParseException {
        System.out.print("Ingrese RUC del cliente: ");
        String ruc = scanner.nextLine();
        System.out.print("Ingrese Razón Social del cliente: ");
        String razonSocial = scanner.nextLine();
        Cliente cliente = new Cliente(ruc, razonSocial);

        System.out.print("Ingrese fecha de servicio (dd/MM/yyyy): ");
        String fechaServicioStr = scanner.nextLine();
        Date fechaServicio = dateFormat.parse(fechaServicioStr);

        System.out.print("Ingrese descripción del servicio: ");
        String descripcion = scanner.nextLine();

        System.out.print("Ingrese precio del servicio: ");
        double precio = scanner.nextDouble();

        System.out.print("Ingrese días de crédito: ");
        int creditoDias = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        Servicio servicio = new Servicio(fechaServicio, descripcion, precio);
        Factura factura = new Factura(cliente, servicio, creditoDias);
        facturas.add(factura);

        Historial.registrarAccion(historial, "Sistema", "Emitió una factura");
        System.out.println("\nFactura emitida con éxito. Código: " + factura.getCodigo());
    }

    public static void consultarFacturas() {
        System.out.print("Ingrese código de la factura a consultar: ");
        String codigo = scanner.nextLine();
        Factura facturaEncontrada = buscarFactura(codigo);

        if (facturaEncontrada != null) {
            facturaEncontrada.mostrarDetalles();
            if (!facturaEncontrada.isPagado() && !facturaEncontrada.isAnulada()) {
                System.out.print("¿Desea anular esta factura? (Sí/No): ");
                String respuesta = scanner.nextLine();
                if (respuesta.equalsIgnoreCase("Sí")) {
                    System.out.print("Ingrese motivo de la anulación: ");
                    String motivo = scanner.nextLine();
                    facturaEncontrada.anularFactura(motivo);
                    Historial.registrarAccion(historial, "Sistema", "Anuló una factura");
                    System.out.println("Factura anulada con éxito.");
                }
            }
        } else {
            System.out.println("Factura no encontrada.");
        }
    }

    public static Factura buscarFactura(String codigo) {
        for (Factura factura : facturas) {
            if (factura.getCodigo().equals(codigo)) {
                return factura;
            }
        }
        return null;
    }

    public static void registrarPago() {
        System.out.print("Ingrese código de la factura a pagar: ");
        String codigo = scanner.nextLine();
        Factura facturaEncontrada = buscarFactura(codigo);
        if (facturaEncontrada != null && !facturaEncontrada.isPagado()) {
            System.out.print("Ingrese monto del pago: ");
            double monto = scanner.nextDouble();
            scanner.nextLine();
            facturaEncontrada.registrarPago(monto);
            Historial.registrarAccion(historial, "Sistema", "Registró un pago");
            System.out.println("Pago registrado correctamente.");
        } else {
            System.out.println("Factura no encontrada o ya está pagada.");
        }
    }
}