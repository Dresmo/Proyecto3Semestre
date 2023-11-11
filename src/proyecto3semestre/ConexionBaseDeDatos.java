/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto3semestre;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.spire.data.table.DataTable;
import com.spire.data.table.common.JdbcAdapter;
import com.spire.xls.ExcelVersion;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;
import java.awt.Desktop;
import java.io.File;

/**
 *
 * @author Sergio Diaz
 */
public class ConexionBaseDeDatos {
    private Connection connection ;
    
    public Connection crearConeccion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            /*connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306",
                    "root", "1234567");*/
            connection = DriverManager.getConnection(
                    "jdbc:mysql://sql10.freemysqlhosting.net:3306",
                    "sql10659714", "imUKKfT9iI");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    public void guardar(Usuario usuario,Connection connection){
        try {
            String sql="Insert into sql10659714.usuarios (nombre,apellido,usuario,contrasena,rol) values(?,?,?,?,?)";
            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getUsuario());
            ps.setString(4, usuario.getContrasena());
            ps.setString(5, usuario.getRol());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void borrar(String usuario, Connection connection){
        try {
            String sql="delete from sql10659714.usuarios where usuario=?";
            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void actualizar(Usuario usuario,Connection connection){
        try {
            String sql="Update sql10659714.usuarios set nombre=?,apellido=?,contrasena=?, rol=? where usuario=?";
            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getContrasena());
            ps.setString(4, usuario.getRol());
            ps.setString(5, usuario.getUsuario());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Usuario buscarUsuario(String nombreUsuario,Connection connection){
        Usuario usuario= null;
        try {
            usuario= new Usuario();
            String sql="Select * from sql10659714.usuarios where usuario='"+nombreUsuario+"'";
            PreparedStatement ps=connection.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setRol(rs.getString("rol"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }
    
    public List<Usuario> buscarUsuarios(Connection connection){
        List<Usuario> usuarios= new ArrayList();
        try {
            
            String sql="Select * from sql10659714.usuarios" ;
            PreparedStatement ps=connection.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Usuario usuario= new Usuario();
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setRol(rs.getString("rol"));
            usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }
    public void exportarDatos(Connection connection){
        try {
            String sql="Select * from sql10659714.usuarios";
            PreparedStatement ps=connection.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            Workbook wb = new Workbook();
            Worksheet sheet = wb.getWorksheets().get(0);
            DataTable dataTable = new DataTable();
            JdbcAdapter jdbcAdapter = new JdbcAdapter();
            jdbcAdapter.fillDataTable(dataTable, rs);
            sheet.insertDataTable(dataTable, true, 1, 1);
            sheet.getAllocatedRange().autoFitColumns();
            wb.saveToFile("output/usuarios.xlsx", ExcelVersion.Version2016);
            Desktop.getDesktop().open(new File("output/usuarios.xlsx"));
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ConexionBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
    public void exportarDatos2(Connection connection){
        try {
            String sql="Select * from sql10659714.usuarios";
            PreparedStatement ps=connection.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Datos");

            int rowNum = 0;
            while (rs.next()) {
                Row row = sheet.createRow(rowNum++);
                int columnNum = 0;
                
                row.createCell(columnNum++).setCellValue(rs.getString("nombre"));
                row.createCell(columnNum++).setCellValue(rs.getString("apellido"));
                row.createCell(columnNum++).setCellValue(rs.getString("usuario"));
                row.createCell(columnNum++).setCellValue(rs.getString("contrasena"));
                row.createCell(columnNum++).setCellValue(rs.getString("rol"));
            }

            
            try (FileOutputStream outputStream = new FileOutputStream("datos.xlsx")) {
                workbook.write(outputStream);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ConexionBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ConexionBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
}
