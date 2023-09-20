/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto3semestre;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sergio Diaz
 */
public class ConexionBaseDeDatos {
    private Connection connection ;
    
    public Connection crearConeccion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306",
                    "root", "1234567");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    public void guardar(Usuario usuario,Connection connection){
        try {
            String sql="Insert into myschema.usuarios (nombre,apellido,usuario,contrasena) values(?,?,?,?)";
            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getUsuario());
            ps.setString(4, usuario.getContrasena());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void borrar(String usuario, Connection connection){
        try {
            String sql="delete from myschema.usuarios where usuario=?";
            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void actualizar(Usuario usuario,Connection connection){
        try {
            String sql="Update myschema.usuarios set nombre=?,apellido=?,contrasena=? where usuario=?";
            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getContrasena());
            ps.setString(4, usuario.getUsuario());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Usuario buscarUsuario(String nombreUsuario,Connection connection){
        Usuario usuario= null;
        try {
            usuario= new Usuario();
            String sql="Select * from myschema.usuarios where usuario='"+nombreUsuario+"'";
            PreparedStatement ps=connection.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setContrasena(rs.getString("contrasena"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }
    
    public List<Usuario> buscarUsuarios(Connection connection){
        List<Usuario> usuarios= new ArrayList();
        try {
            
            String sql="Select * from myschema.usuarios" ;
            PreparedStatement ps=connection.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Usuario usuario= new Usuario();
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setContrasena(rs.getString("contrasena"));
            usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }
}
