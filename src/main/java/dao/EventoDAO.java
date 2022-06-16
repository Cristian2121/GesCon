package dao;

import dto.EventoDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventoDAO {
    private static final String SQL_INSERT = "insert into Evento (nombreEvento, cupo, fecha, tipoEvento, idOrganizador) "
            + " values(?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "update Evento set nombreEvento=?, cupo=?, fecha=?, tipoEvento=?, idOrganizador=? "
            + " where idEvento=?";
    private static final String SQL_DELETE = "delete from Evento where idEvento=?";
    private static final String SQL_SELECT = "select * from Evento where idEvento=?";
    private static final String SQL_SELECT_ALL = "select * from Evento";

    private Connection conexion;

    public EventoDAO() {
    }

    public Connection obtenerConexion() {
        //obtener conexion
        String usuario = "bicksurmibtsfq";
        String clave = "aa3081a6fa072598810addd40f767694c119a9c432b84532f06cad053aee0e0e";
        String url = "jdbc:postgresql://ec2-52-72-56-59.compute-1.amazonaws.com:5432/db078gl523aprf?sslmode=require";
        String driverBD = "org.postgresql.Driver";

        try {
            Class.forName(driverBD);
            conexion = DriverManager.getConnection(url, usuario, clave);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EventoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conexion;
    }

    public void create(EventoDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_INSERT);
            ps.setString(1, dto.getEntidad().getNombreEvento());
            ps.setInt(2, dto.getEntidad().getCupo());
            ps.setDate(3, dto.getEntidad().getFecha());
            ps.setInt(4, dto.getEntidad().getTipoEvento());
            ps.setInt(5, dto.getEntidad().getIdOrganizador());
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

    public void update(EventoDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_UPDATE);
            ps.setString(1, dto.getEntidad().getNombreEvento());
            ps.setInt(2, dto.getEntidad().getCupo());
            ps.setDate(3, dto.getEntidad().getFecha());
            ps.setInt(4, dto.getEntidad().getTipoEvento());
            ps.setInt(5, dto.getEntidad().getIdOrganizador());
            ps.setInt(6, dto.getEntidad().getIdEvento());
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

    public void delete(EventoDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_DELETE);
            ps.setInt(1, dto.getEntidad().getIdEvento());
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
    
    public EventoDTO read(EventoDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List lista = null;
        try {
            ps = conexion.prepareStatement(SQL_SELECT);
            ps.setInt(1, dto.getEntidad().getIdEvento());
            rs = ps.executeQuery();
            lista = obtenerResultados(rs);
            if (!lista.isEmpty()){
                return (EventoDTO) lista.get(0);
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
        EventoDTO dto = new EventoDTO();
        Date fecha = new Date();
        long tiempo = fecha.getTime();
        java.sql.Date fechaSQL = new java.sql.Date(tiempo);
        System.out.println(fechaSQL);
        dto.getEntidad().setIdEvento(1);
        /*dto.getEntidad().setNombreEvento("ExpoCinema");
        dto.getEntidad().setCupo(25);
        dto.getEntidad().setFecha(fechaSQL);
        dto.getEntidad().setTipoEvento(2);
        dto.getEntidad().setIdOrganizador(1);*/

        EventoDAO dao = new EventoDAO();
        try {
            //dao.create(dto);
            //dao.update(dto);
            //dao.delete(dto);
            //System.out.println(dao.readAll());
            System.out.println(dao.read(dto));
        } catch (SQLException ex) {
            Logger.getLogger(EventoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List obtenerResultados(ResultSet rs) throws SQLException{
        List resultados = new ArrayList();
        while (rs.next()) {            
            EventoDTO dto = new EventoDTO();
            dto.getEntidad().setIdEvento(rs.getInt("idEvento"));
            dto.getEntidad().setNombreEvento(rs.getString("nombreEvento"));
            dto.getEntidad().setCupo(rs.getInt("cupo"));
            dto.getEntidad().setFecha(rs.getDate("fecha"));
            dto.getEntidad().setTipoEvento(rs.getInt("tipoEvento"));
            dto.getEntidad().setIdOrganizador(rs.getInt("idOrganizador"));
            resultados.add(dto);
        }
        return resultados;
    }
}