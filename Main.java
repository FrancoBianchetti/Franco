import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Tienda tienda = new Tienda();

        tienda.agregarProducto(new Producto("Granizo", 5.0, 30));
        tienda.agregarProducto(new Producto("Venganza", 6.0, 25));
        tienda.agregarProducto(new Producto("Jhon_Wick", 8.0, 20));
        tienda.agregarProducto(new Producto("Avatar", 7.0, 15));
        tienda.agregarProducto(new Producto("The_Office", 12.0, 15));
        tienda.agregarProducto(new Producto("El_Novato", 15.0, 20));
        tienda.agregarProducto(new Producto("Game_Of_Thrones", 18.0, 20));
        tienda.agregarProducto(new Producto("Silo", 15.0, 0));
        tienda.agregarProducto(new Producto("White_Collar", 17.0, 20));

        Scanner scanner = new Scanner(System.in);
        boolean autenticado = false;

        while (true) {
            System.out.println("- - - - - - MENU DE LA TIENDA - - - - - -");
            System.out.println("1. Ver catalogo");
            System.out.println("2. Comprar productos");
            System.out.println("3. Salir");

            if (!autenticado) {
                System.out.println("4. Iniciar sesión (para administrador)");
            } else {
                System.out.println("5. Agregar producto (administrador)");
                System.out.println("6. Quitar producto (administrador)");
                System.out.println("7. Cerrar sesión (administrador)");
            }

            System.out.print("Seleccione una opcion: ");
            int opcion = scanner.nextInt();

            if (opcion == 1) {
                tienda.mostrarCatalogo();

            } else if (opcion == 2) {
                List<Producto> productosComprados = new ArrayList<>();
                boolean seleccionandoProductos = true;

                while (seleccionandoProductos) {
                    tienda.mostrarCatalogo();
                    System.out.println("Ingrese el nombre del producto que desea comprar o escriba 'salir' para finalizar la compra:");

                    String nombreProducto = scanner.next();

                    if (nombreProducto.equalsIgnoreCase("salir")) {
                        seleccionandoProductos = false;

                    } else {
                        Producto producto = buscarProducto(tienda, nombreProducto);
                        if (producto != null) {
                            if (producto.getStock() > 0) {
                                productosComprados.add(producto);
                                System.out.println("Producto agregado al carrito.");
                            } else {
                                System.out.println("El producto escogido no posee stock disponible");
                            }
                        } else {
                            System.out.println("El producto elejido no existe en el catalogo.");
                        }
                    }
                }

                if (!productosComprados.isEmpty()) {
                    Ticket ticket = tienda.comprarProductos(productosComprados);
                    ticket.imprimirTicket();
                }

            } else if (opcion == 3) {
                break;

            } else if (opcion == 4) {
                System.out.print("Ingrese el email: ");
                String email = scanner.next();
                System.out.print("Ingrese el nombre de usuario: ");
                String username = scanner.next();
                System.out.print("Ingrese la contraseña: ");
                String password = scanner.next();
                autenticado = Admin.autenticar(username, password, email);

                if (autenticado) {
                    System.out.println("Inicio de sesión exitoso.");
                } else {
                    System.out.println("Nombre de usuario o contraseña incorrectos.");
                }

            } else if (opcion == 5) {
                if (autenticado) {
                    System.out.print("Ingrese el nombre del nuevo producto: ");
                    String nombreProducto = scanner.next();
                    System.out.print("Ingrese el precio del nuevo producto: ");
                    double precioProducto = scanner.nextDouble();
                    System.out.print("Ingrese el stock del nuevo producto: ");
                    int stockProducto = scanner.nextInt();

                    Producto productoNuevo = new Producto(nombreProducto, precioProducto, stockProducto);
                    tienda.agregarProducto(productoNuevo);
                    System.out.println("Producto agregado correctamente.");
                } else {
                    System.out.println("Acceso no autorizado.");
                }

            } else if (opcion == 6) {
                if (autenticado) {
                    tienda.mostrarCatalogo();
                    System.out.print("Ingrese el nombre del producto que desea quitar del catalogo: ");
                    String nombreProducto = scanner.next();

                    Producto producto = buscarProducto(tienda, nombreProducto);
                    if (producto != null) {
                        tienda.quitarProducto(producto);
                        System.out.println("Producto quitado del catalogo correctamente.");
                    } else {
                        System.out.println("No se encontró el producto en el catálogo.");
                    }
                } else {
                    System.out.println("Acceso no autorizado.");
                }

            } else if (opcion == 7) {
                if (autenticado) {
                    autenticado = false;
                    System.out.println("Cierre de sesión exitoso.");
                } else {
                    System.out.println("Acceso no autorizado.");
                }
            } else {
                System.out.println("Opción inválida.");
            }
        }

        System.out.println("Gracias por visitar la tienda y espero que vuelva. ¡Hasta luego!");
    }

    private static Producto buscarProducto(Tienda tienda, String nombreProducto) {
        for (Producto producto : tienda.catalogo) {
            if (producto.getNombre().equalsIgnoreCase(nombreProducto)) {
                return producto;
            }
        }
        return null;
    }
}