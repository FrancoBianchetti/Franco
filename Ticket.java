import java.util.Date;
import java.util.List;

public class Ticket {
    private List<Producto> productos;
    private Date fecha;
    private double total;

    public Ticket(List<Producto> productos, double total) {
        this.productos = productos;
        this.fecha = new Date();
        this.total = total;
    }

    public void imprimirTicket() {
        System.out.println("- - - - - - - - - TICKET - - - - - - - - -");
        System.out.println("Fecha: " + fecha);
        System.out.println("Productos comprados:");
        for (Producto producto : productos) {
            System.out.println("- " + producto.getNombre() + " $" + producto.getPrecio());
        }
        System.out.println("Total: $" + total);
        System.out.println("- - - - - - - - - - - - - - - - - - - - - -");
    }
}
