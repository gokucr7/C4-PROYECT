package Clases;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Permite que los formularios Swing se subscriban a cambios de catálogos
 * (programas, roles) y refresquen sus componentes tras operaciones CRUD.
 */
public final class CatalogoRefresher {

    private static final List<Runnable> LISTENERS = new CopyOnWriteArrayList<>();

    private CatalogoRefresher() {
    }

    public static void registrar(Runnable listener) {
        if (listener != null) {
            LISTENERS.add(listener);
        }
    }

    public static void remover(Runnable listener) {
        if (listener != null) {
            LISTENERS.remove(listener);
        }
    }

    public static void notificarCambios() {
        for (Runnable listener : LISTENERS) {
            try {
                listener.run();
            } catch (Exception ex) {
                System.err.println("Error al refrescar catálogo: " + ex.getMessage());
            }
        }
    }
}
