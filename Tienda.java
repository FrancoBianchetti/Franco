import java.util.ArrayList;
import java.util.List;

public class Tienda {
    public List<Producto> catalogo;
    public List<Ticket> tickets;

    public Tienda() {
        catalogo = new ArrayList<>();
        tickets = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        catalogo.add(producto);
    }

    public void quitarProducto(Producto producto) {
        catalogo.remove(producto);
    }

    public void mostrarCatalogo() {
        System.out.println("- - - - - - - - - CATÁLOGO - - - - - - - - -");
        for (Producto producto : catalogo) {
            System.out.println("- " + producto.getNombre() + " $" + producto.getPrecio() + " (Stock: " + producto.getStock() + ")");
        }
        System.out.println("- - - - - - - - - - - - - - - - - - - - -");
    }

    public Ticket comprarProductos(List<Producto> productos) {
        double total = 0;
        for (Producto producto : productos) {
            total += producto.getPrecio();
            producto.reducirStock(1);
        }

        Ticket ticket = new Ticket(productos, total);
        tickets.add(ticket);
        return ticket;
    }
}
