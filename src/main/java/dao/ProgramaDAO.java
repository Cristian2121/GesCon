package dao;

import dto.ProgramaDTO;
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

public class ProgramaDAO {
    private static final String SQL_INSERT = "insert into Programa (nombrePrograma, moderador, idEvento) "
            + " values(?, ?, ?)";
    private static final String SQL_UPDATE = "update Programa set nombrePrograma=?, moderador=?, idEvento=? "
            + " where idPrograma=?";
    private static final String SQL_DELETE = "delete from Programa where idPrograma=?";
    private static final String SQL_SELECT = "select * from Programa where idPrograma=?";
    private static final String SQL_SELECT_ALL = "select * from Programa";

    private Connection conexion;

    public ProgramaDAO() {
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
            Logger.getLogger(ProgramaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void create(ProgramaDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_INSERT);
            ps.setString(1, dto.getEntidad().getNombrePrograma());
            ps.setString(2, dto.getEntidad().getModerador());
            ps.setInt(3, dto.getEntidad().getIdEvento());
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

    public void update(ProgramaDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_UPDATE);
            ps.setString(1, dto.getEntidad().getNombrePrograma());
            ps.setString(2, dto.getEntidad().getModerador());
            ps.setInt(3, dto.getEntidad().getIdEvento());
            ps.setInt(4, dto.getEntidad().getIdPrograma());
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

    public void delete(ProgramaDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_DELETE);
            ps.setInt(1, dto.getEntidad().getIdPrograma());
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
    
    public ProgramaDTO read(ProgramaDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List lista = null;
        try {
            ps = conexion.prepareStatement(SQL_SELECT);
            ps.setInt(1, dto.getEntidad().getIdPrograma());
            rs = ps.executeQuery();
            lista = obtenerResultados(rs);
            if (!lista.isEmpty()){
                return (ProgramaDTO) lista.get(0);
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
        ProgramaDTO dto = new ProgramaDTO();
        dto.getEntidad().setIdPrograma(1);
        /*dto.getEntidad().setNombrePrograma("ExpoCinema");
        dto.getEntidad().setModerador("Juan Gabriel");
        dto.getEntidad().setIdEvento(1);*/

        ProgramaDAO dao = new ProgramaDAO();
        try {
            //dao.create(dto);
            //dao.update(dto);
            //dao.delete(dto);
            //System.out.println(dao.readAll());
            System.out.println(dao.read(dto));
        } catch (SQLException ex) {
            Logger.getLogger(ProgramaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List obtenerResultados(ResultSet rs) throws SQLException{
        List resultados = new ArrayList();
        while (rs.next()) {            
            ProgramaDTO dto = new ProgramaDTO();
            dto.getEntidad().setIdPrograma(rs.getInt("idPrograma"));
            dto.getEntidad().setNombrePrograma(rs.getString("nombrePrograma"));
            dto.getEntidad().setModerador(rs.getString("moderador"));
            dto.getEntidad().setIdEvento(rs.getInt("idEvento"));
            resultados.add(dto);
        }
        return resultados;
    }
}