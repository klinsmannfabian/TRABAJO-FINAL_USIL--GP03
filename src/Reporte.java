
import java.util.ArrayList;
public class Reporte {
    public static void generarReporte(ArrayList<Factura> facturas) {
        long totalEmitidas = facturas.size();
        long totalAnuladas = facturas.stream().filter(Factura::isAnulada).count();
        long totalPendientes = facturas.stream().filter(f -> !f.isPagado() && !f.isAnulada()).count();
        long totalPagadas = facturas.stream().filter(Factura::isPagado).count();

        System.out.println("\n--- Reporte de Facturas ---");
        System.out.println("Total de facturas emitidas: " + totalEmitidas);
        System.out.println("Total de facturas anuladas: " + totalAnuladas);
        System.out.println("Total de facturas pendientes de pago: " + totalPendientes);
        System.out.println("Total de facturas pagadas: " + totalPagadas);
    }
}
