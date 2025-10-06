package Persistencia;

import Modelo.Alumno;
import Modelo.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class AlumnoData {

    private final Connection conect;

    public AlumnoData() {
        conect = Conexion.getConexion();
    }

    public void guardarAlumno(Alumno alumno) {
        String sql = "INSERT INTO alumno (dni, apellido, nombre_alumno, fechaNacimiento, estado_alumno) "
                + "VALUES(?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){            
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre_alumno());
            ps.setDate(4, Date.valueOf(alumno.getFechaNacimiento()));
            ps.setBoolean(5, alumno.isEstado_alumno());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            
            if (rs.next()) {
                alumno.setId_alumno(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Alumno agregado exitosamente");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la Tabla Alumno: " + ex.getMessage());
        }
    }

    public void modificarAlumno(Alumno alumno) {
        String sql = "UPDATE alumno SET dni = ?, apellido = ?, nombre_alumno = ?, fechaNacimiento = ? "
                + "WHERE id_alumno = ?";
        try (PreparedStatement ps = conect.prepareStatement(sql)){            
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre_alumno());
            ps.setDate(4, Date.valueOf(alumno.getFechaNacimiento()));
            ps.setInt(5, alumno.getId_alumno());
            
            int exito = ps.executeUpdate();
            
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Alumno modificado exitosamente");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder Tabla alumno: " + ex.getMessage());
        }
    }

    public void eliminarAlumno(int id) {
        String sql = "UPDATE alumno SET estado_alumno = 0 WHERE id_alumno = ?";
        try (PreparedStatement ps = conect.prepareStatement(sql)){
            ps.setInt(1, id);
            
            int exito = ps.executeUpdate();
            
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Borrado de alumno (lógico) realizado");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la Tabla alumno: " + ex.getMessage());
        }
    }

    /*public Alumno buscarAlumnoPorId(int id) {
        String sql = "SELECT dni, apellido, nombre_alumno, fechaNacimiento FROM alumno "
                + "WHERE id_alumno = ? AND estado_alumno = 1";
        Alumno alumno = null;
        try (PreparedStatement ps = conect.prepareStatement(sql)){     
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                alumno = new Alumno();
                alumno.setId_alumno(id);
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre_alumno(rs.getString("nombre_alumno"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado_alumno(true);
            } else {
                JOptionPane.showMessageDialog(null, "No existe el alumno buscado");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la Tabla alumno: " + ex.getMessage());
        }   
        return alumno;
    }
    
    public Alumno buscarAlumnoPorDni(int dni) {
        String sql = "SELECT id_alumno, dni, apellido, nombre_alumno, fechaNacimiento FROM alumno "
                + "WHERE dni = ? AND estado_alumno = 1";
        Alumno alumno = null;
        try (PreparedStatement ps = conect.prepareStatement(sql)){
            ps.setInt(1, dni);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                alumno = new Alumno();
                alumno.setId_alumno(rs.getInt("id_alumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre_alumno(rs.getString("nombre_alumno"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado_alumno(true);
            } else {
                JOptionPane.showMessageDialog(null, "No existe el alumno buscado");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la Tabla alumno: " + ex.getMessage());
        }   
        return alumno;
    }*/
    
    public Alumno buscarAlumnoPorId(int id) {
        return buscarAlumno("id_alumno", id, "ID");
    }

    public Alumno buscarAlumnoPorDni(int dni) {
        return buscarAlumno("dni", dni, "DNI");
    }
    
    public Alumno buscarAlumno(String campo, int valor, String tipoMensaje) {
        /*era DNI */
        String sql = "SELECT id_alumno, dni, apellido, nombre_alumno, " + 
                "fechaNacimiento FROM alumno WHERE " + campo + 
                "= ? AND estado_alumno = 1";
        Alumno alumno = null;
        
        try (PreparedStatement ps = conect.prepareStatement(sql)){
            ps.setInt(1, valor);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                alumno = new Alumno();
                alumno.setId_alumno(rs.getInt("id_alumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre_alumno(rs.getString("nombre_alumno"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado_alumno(true);
            } else {
                JOptionPane.showMessageDialog(null, "No existe el alumno buscado");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la Tabla alumno: " + ex.getMessage());
        }   
        return alumno;
    }
    
    public List<Alumno> listarAlumnos() {
        String sql = "SELECT id_alumno, dni, apellido, nombre_alumno, fechaNacimiento FROM alumno "
                + "WHERE estado_alumno = 1";
        ArrayList<Alumno> alumnos = new ArrayList<>();
        
        try (PreparedStatement ps = conect.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setId_alumno(rs.getInt("id_alumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre_alumno(rs.getString("nombre_alumno"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado_alumno(true);
                alumnos.add(alumno);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la Tabla alumno: " + ex.getMessage());
        }
        return alumnos;
    }
}