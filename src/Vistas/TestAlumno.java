package Vistas;

import Modelo.Alumno;
import Modelo.Conexion;
import Persistencia.AlumnoData;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public class TestAlumno {

    public static void main(String[] args) {
        System.out.println("  SISTEMA DE GESTIÓN UNIVERSIDAD DE LA PUNTA\n");
        System.out.println("Test conexión a la base de datos:");
      
        Connection conexion = Conexion.getConexion();
        
        if(conexion != null)
            System.out.println("\tConexión Exitosa...\n");
        else {
            System.out.println("\tError de Conexión...\n");
            return;
        }
        
        AlumnoData alumnoData = new AlumnoData();
        
        System.out.println("Test Alumno Data\n\n* Buscar por DNI");

        Alumno camila = alumnoData.buscarAlumnoPorDni(43621472);
        if (camila == null) {
            camila = new Alumno(
                43621472,
                "Biarnes",
                "Camila",
                LocalDate.of(2001, 3, 15),
                true
            );
            alumnoData.guardarAlumno(camila);
            
            System.out.println("→ Camila Biarnes agregada con ID: " + camila.getId_alumno());
        } else {
            System.out.println("→ Camila Biarnes ya existe con ID: " + camila.getId_alumno());
        }

        Alumno franco = alumnoData.buscarAlumnoPorDni(42111222);
        if (franco == null) {
            franco = new Alumno(
                42111222,
                "Romero",
                "Franco",
                LocalDate.of(2000, 8, 22),
                true
            );
            alumnoData.guardarAlumno(franco);
            
            System.out.println("→ Franco Romero agregado con ID: " + franco.getId_alumno());
        } else {
            System.out.println("→ Franco Romero ya existe con ID: " + franco.getId_alumno());
        }
     
        Alumno andres = alumnoData.buscarAlumnoPorDni(42222034);
        if (andres == null) {
            andres = new Alumno(
                42222034,
                "Ferra",
                "Andres",
                LocalDate.of(2001, 11, 10),
                true
            );
            alumnoData.guardarAlumno(andres);
            
            System.out.println("→ Andres Ferra agregado con ID: " + andres.getId_alumno());
        } else {
            System.out.println("→ Andres Ferra ya existe con ID: " + andres.getId_alumno());
        }
        
        System.out.println("\n3. LISTADO DE TODOS LOS ALUMNOS ACTIVOS:");
        System.out.println("----------------------------------------------------------");
        List<Alumno> alumnos = alumnoData.listarAlumnos();
        
        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos registrados.");
        } else {
            System.out.println("Total de alumnos activos: "
                    + alumnos.size() + "\n");
         
            System.out.println(String.format("%-5s %-12s %-20s %-20s %-15s", 
                    "ID", "DNI", "APELLIDO", "NOMBRE", "FECHA NAC."));
            System.out.println("----------------------------------------------------------");
            
            for (Alumno a : alumnos) {
                System.out.println(String.format("%-5d %-12d %-20s %-20s %-15s",
                    a.getId_alumno(),
                    a.getDni(),
                    a.getApellido(),
                    a.getNombre_alumno(),
                    a.getFechaNacimiento()
                ));
            }
        }

        System.out.println("              PRUEBAS FINALIZADAS");
        System.out.println("==========================================================");
        System.out.println();
        System.out.println("\t Integrantes del Grupo:");
        System.out.println("   • Biarnes, Camila (DNI: 43.621.472)");
        System.out.println("   • Romero, Franco (DNI: 42.111.222)");
        System.out.println("   • Ferra, Andrés (DNI: 42.222.034)");
        System.out.println();
     
    }
}