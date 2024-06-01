package P1;

public abstract class Empleado {
    private String nombre;
    private String rol;

    // Constructor básico
    public Empleado(String nombre, String rol) {
        this.nombre = nombre;
        this.rol = rol;
    }

    // Getters y Setters para los atributos
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    // Método abstracto que las subclases deberán implementar
    public abstract void realizarTareas();
}