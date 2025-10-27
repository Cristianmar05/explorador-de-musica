import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Cancion {
    String nombre;
    String artista;
    String genero;
    String region;

    public Cancion(String nombre, String artista, String genero, String region) {
        this.nombre = nombre;
        this.artista = artista;
        this.genero = genero;
        this.region = region;
    }

    @Override
    public String toString() {
        return "🎵 " + nombre + " | Artista: " + artista + " | Género: " + genero + " | Región: " + region;
    }
}

public class ExploradorMusica {
    static HashMap<String, Cancion> biblioteca = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n=== EXPLORADOR DE MÚSICA ===");
            System.out.println("1. Registrar canción");
            System.out.println("2. Buscar canción");
            System.out.println("3. Listar todas las canciones");
            System.out.println("4. Eliminar canción");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> registrar();
                case 2 -> menuBusqueda();
                case 3 -> listar();
                case 4 -> eliminar();
                case 5 -> System.out.println("👋 Saliendo del sistema...");
                default -> System.out.println("❌ Opción no válida");
            }
        } while (opcion != 5);
    }

    // === Registrar canción ===
    static void registrar() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Artista: ");
        String artista = sc.nextLine();
        System.out.print("Género: ");
        String genero = sc.nextLine();
        System.out.print("Región: ");
        String region = sc.nextLine();

        biblioteca.put(nombre.toLowerCase(), new Cancion(nombre, artista, genero, region));
        System.out.println("✅ Canción registrada con éxito.");
    }

    // === Submenú de búsqueda ===
    static void menuBusqueda() {
        System.out.println("\n--- Tipo de búsqueda ---");
        System.out.println("1. Por nombre");
        System.out.println("2. Por artista");
        System.out.println("3. Por género");
        System.out.println("4. Por región");
        System.out.print("Seleccione: ");
        int tipo = sc.nextInt();
        sc.nextLine();

        switch (tipo) {
            case 1 -> buscarPorNombre();
            case 2 -> buscarPorCampo("artista");
            case 3 -> buscarPorCampo("genero");
            case 4 -> buscarPorCampo("region");
            default -> System.out.println("❌ Opción inválida.");
        }
    }

    // === Buscar por nombre (clave directa) ===
    static void buscarPorNombre() {
        System.out.print("Ingrese el nombre de la canción: ");
        String nombre = sc.nextLine().toLowerCase();

        Cancion c = biblioteca.get(nombre);
        if (c != null) {
            System.out.println("✅ Canción encontrada:");
            System.out.println(c);
        } else {
            System.out.println("❌ No se encontró la canción.");
        }
    }

    // === Buscar por otros campos (artista, género, región) ===
    static void buscarPorCampo(String campo) {
        System.out.print("Ingrese " + campo + " a buscar: ");
        String valor = sc.nextLine().toLowerCase();

        boolean encontrado = false;
        for (Cancion c : biblioteca.values()) {
            switch (campo) {
                case "artista" -> {
                    if (c.artista.toLowerCase().contains(valor)) {
                        System.out.println(c);
                        encontrado = true;
                    }
                }
                case "genero" -> {
                    if (c.genero.toLowerCase().contains(valor)) {
                        System.out.println(c);
                        encontrado = true;
                    }
                }
                case "region" -> {
                    if (c.region.toLowerCase().contains(valor)) {
                        System.out.println(c);
                        encontrado = true;
                    }
                }
            }
        }

        if (!encontrado) {
            System.out.println("❌ No se encontraron resultados para ese " + campo + ".");
        }
    }

    // === Listar canciones ordenadas ===
    static void listar() {
        if (biblioteca.isEmpty()) {
            System.out.println("📭 No hay canciones registradas.");
            return;
        }

        System.out.println("\n=== LISTA DE CANCIONES (Orden alfabético) ===");
        biblioteca.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(e -> System.out.println(e.getValue()));
    }

    // === Eliminar ===
    static void eliminar() {
        System.out.print("Ingrese el nombre de la canción a eliminar: ");
        String nombre = sc.nextLine().toLowerCase();

        if (biblioteca.remove(nombre) != null) {
            System.out.println("🗑️ Canción eliminada con éxito.");
        } else {
            System.out.println("❌ No se encontró esa canción.");
        }
    }
}
