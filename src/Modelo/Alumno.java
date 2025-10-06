package Modelo;

import java.time.LocalDate;


public class Alumno {

    private int id_alumno;
    private int dni;
    private String apellido;
    private String nombre_alumno;
    private LocalDate fechaNacimiento;
    private boolean estado_alumno;

    public Alumno() {}

    public Alumno(int id_alumno, int dni, String apellido, String nombre_alumno,
            LocalDate fechaNacimiento, boolean estado_alumno) {
        this.id_alumno = id_alumno;
        this.dni = dni;
        this.apellido = apellido;
        this.nombre_alumno = nombre_alumno;
        this.fechaNacimiento = fechaNacimiento;
        this.estado_alumno = estado_alumno;
    }

    // Constructor sin atributo id_alumno:
    public Alumno(int dni, String apellido, String nombre_alumno, 
            LocalDate fechaNacimiento, boolean estado_alumno) {
        this.dni = dni;
        this.apellido = apellido;
        this.nombre_alumno = nombre_alumno;
        this.fechaNacimiento = fechaNacimiento;
        this.estado_alumno = estado_alumno;
    }

    public int getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(int id_alumno) {
        this.id_alumno = id_alumno;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre_alumno() {
        return nombre_alumno;
    }

    public void setNombre_alumno(String nombre_alumno) {
        this.nombre_alumno = nombre_alumno;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean isEstado_alumno() {
        return estado_alumno;
    }

    public void setEstado_alumno(boolean estado_alumno) {
        this.estado_alumno = estado_alumno;
    }

    @Override
    public String toString() {
        return "Alumno{" + "id_alumno=" + id_alumno + ", dni=" + dni + 
                ", apellido=" + apellido + ", nombre_alumno=" + nombre_alumno 
                + ", fechaNacimiento=" + fechaNacimiento + ", estado_alumno=" 
                + estado_alumno + '}';
    }
}