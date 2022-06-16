package dao;

import dto.ArchivoDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArchivoDAO {
    private static final String SQL_INSERT = "insert into Archivo (rutaDocumento, tipoDocumento, idPrograma) "
            + " values(?, ?, ?)";
    private static final String SQL_UPDATE = "update Archivo set rutaDocumento=?, tipoDocumento=?, idPrograma=? "
            + " where idArchivo=?";
    private static final String SQL_DELETE = "delete from Archivo where idArchivo=?";
    private static final String SQL_SELECT = "select * from Archivo where idArchivo=?";
    private static final String SQL_SELECT_ALL = "select * from Archivo";

    private Connection conexion;

    public ArchivoDAO() {
    }

    private void obtenerConexion() {
        //obtener conexion
        String usuario = "bicksurmibtsfq";
        String clave = "aa3081a6fa072598810addd40f767694c119a9c432b84532f06cad053aee0e0e";
        String url = "jdbc:postgresql://ec2-52-72-56-59.compute-1.amazonaws.com:5432/db078gl523aprf?sslmode=require";
        String driverBD = "org.postgresql.Driver";

        try {
            Class.forName(driverBD);
            conexion = DriverManager.getConnection(url, usuario, clave);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ArchivoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void create(ArchivoDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_INSERT);
            ps.setString(1, dto.getEntidad().getRutaDocumento());
            ps.setInt(2, dto.getEntidad().getTipoDocumento());
            ps.setInt(3, dto.getEntidad().getIdPrograma());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }

    public void update(ArchivoDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_UPDATE);
            ps.setString(1, dto.getEntidad().getRutaDocumento());
            ps.setInt(2, dto.getEntidad().getTipoDocumento());
            ps.setInt(3, dto.getEntidad().getIdPrograma());
            ps.setInt(4, dto.getEntidad().getIdArchivo());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }

    public void delete(ArchivoDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_DELETE);
            ps.setInt(1, dto.getEntidad().getIdArchivo());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }

    public List readAll() throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List lista = null;
        try {
            ps = conexion.prepareStatement(SQL_SELECT_ALL);
            rs = ps.executeQuery();
            lista = obtenerResultados(rs);
            if (lista.size() > 0){
                return lista;
            }else{
                return null;
            }  
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }
    
    public ArchivoDTO read(ArchivoDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List lista = null;
        try {
            ps = conexion.prepareStatement(SQL_SELECT);
            ps.setInt(1, dto.getEntidad().getIdArchivo());
            rs = ps.executeQuery();
            lista = obtenerResultados(rs);
            if (!lista.isEmpty()){
                return (ArchivoDTO) lista.get(0);
            }else{
                return null;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }

    public static void main(String[] args) {
        ArchivoDTO dto = new ArchivoDTO();
        dto.getEntidad().setIdArchivo(1);
        /*dto.getEntidad().setRutaDocumento("C://localhost/8080/projects");
        dto.getEntidad().setTipoDocumento(2);
        dto.getEntidad().setIdPrograma(1);*/

        ArchivoDAO dao = new ArchivoDAO();
        try {
            //dao.create(dto);
            //dao.update(dto);
            //dao.delete(dto);
            //System.out.println(dao.readAll());
            System.out.println(dao.read(dto));
        } catch (SQLException ex) {
            Logger.getLogger(ArchivoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List obtenerResultados(ResultSet rs) throws SQLException{
        List resultados = new ArrayList();
        while (rs.next()) {            
            ArchivoDTO dto = new ArchivoDTO();
            dto.getEntidad().setIdArchivo(rs.getInt("idArchivo"));
            dto.getEntidad().setRutaDocumento(rs.getString("rutaDocumento"));
            dto.getEntidad().setTipoDocumento(rs.getInt("tipoDocumento"));
            dto.getEntidad().setIdPrograma(rs.getInt("idPrograma"));
            resultados.add(dto);
        }
        return resultados;
    }
}