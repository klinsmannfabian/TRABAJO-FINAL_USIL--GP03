import java.util.ArrayList;
public class UsuarioAdmin extends Usuario {

    public UsuarioAdmin(String nombreUsuario, String contrasena, String rol) {
        super(nombreUsuario, contrasena, rol);
    }

    public void agregarCliente(ArrayList<Cliente> clientes, String ruc, String razonSocial) {
        Cliente cliente = new Cliente(ruc, razonSocial);
        clientes.add(cliente);
        System.out.println("Cliente agregado con éxito: " + razonSocial);
    }

    public void eliminarFactura(ArrayList<Factura> facturas, String codigo) {
        Factura factura = facturas.stream().filter(f -> f.getCodigo().equals(codigo)).findFirst().orElse(null);
        if (factura != null) {
            facturas.remove(factura);
            System.out.println("Factura eliminada con éxito: " + codigo);
        } else {
            System.out.println("Factura no encontrada.");
        }
    }
}

