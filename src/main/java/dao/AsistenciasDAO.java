package dao;

import dto.AsistenciasDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AsistenciasDAO {
    private static final String SQL_INSERT = "insert into Asistencias (idAsistente, idEvento) "
            + " values(?, ?)";
    private static final String SQL_UPDATE = "update Asistencias set idAsistente=?, idEvento=? "
            + " where idAsistente=?";
    private static final String SQL_DELETE = "delete from Asistencias where idAsistente=? and idEvento=?";
    private static final String SQL_SELECT = "select * from Asistencias where idAsistente=?";
    private static final String SQL_SELECT_ALL = "select * from Asistencias";

    private Connection conexion;

    public AsistenciasDAO() {
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
            Logger.getLogger(AsistenciasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void create(AsistenciasDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_INSERT);
            ps.setInt(1, dto.getEntidad().getIdAsistente());
            ps.setInt(2, dto.getEntidad().getIdEvento());
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

    public void update(AsistenciasDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_UPDATE);
            ps.setInt(1, dto.getEntidad().getIdAsistente());
            ps.setInt(2, dto.getEntidad().getIdEvento());
            ps.setInt(3, dto.getEntidad().getIdAsistente());
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

    public void delete(AsistenciasDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_DELETE);
            ps.setInt(1, dto.getEntidad().getIdAsistente());
            ps.setInt(2, dto.getEntidad().getIdEvento());
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
    
    public AsistenciasDTO read(AsistenciasDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List lista = null;
        try {
            ps = conexion.prepareStatement(SQL_SELECT);
            ps.setInt(1, dto.getEntidad().getIdAsistente());
            rs = ps.executeQuery();
            lista = obtenerResultados(rs);
            if (!lista.isEmpty()){
                return (AsistenciasDTO) lista.get(0);
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
        AsistenciasDTO dto = new AsistenciasDTO();
        dto.getEntidad().setIdAsistente(1);
        /*dto.getEntidad().setIdEvento(1);
        dto.getEntidad().setIdArchivo(1);*/

        AsistenciasDAO dao = new AsistenciasDAO();
        try {
            //dao.create(dto);
            //dao.update(dto);
            //dao.delete(dto);
            //System.out.println(dao.readAll());
            System.out.println(dao.read(dto));
        } catch (SQLException ex) {
            Logger.getLogger(AsistenciasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List obtenerResultados(ResultSet rs) throws SQLException{
        List resultados = new ArrayList();
        while (rs.next()) {            
            AsistenciasDTO dto = new AsistenciasDTO();
            dto.getEntidad().setIdAsistente(rs.getInt("idAsistente"));
            dto.getEntidad().setIdEvento(rs.getInt("idEvento"));
            resultados.add(dto);
        }
        return resultados;
    }
}