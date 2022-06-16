package dao;

import dto.RevisorDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RevisorDAO {
    private static final String SQL_INSERT = "insert into Revisor (nombre, apPaterno, apMaterno, email, nombreUsuario, contrasenia) "
            + " values(?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "update Revisor set nombre=?, apPaterno=?, apMaterno=?, email=?, nombreUsuario=?, contrasenia=? "
            + " where idRevisor=?";
    private static final String SQL_DELETE = "delete from Revisor where idRevisor=?";
    private static final String SQL_SELECT = "select * from Revisor where idRevisor=?";
    private static final String SQL_SELECT_ALL = "select * from Revisor";

    private Connection conexion;

    public RevisorDAO() {
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
            Logger.getLogger(RevisorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void create(RevisorDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_INSERT);
            ps.setString(1, dto.getEntidad().getNombre());
            ps.setString(2, dto.getEntidad().getApPaterno());
            ps.setString(3, dto.getEntidad().getApMaterno());
            ps.setString(4, dto.getEntidad().getEmail());
            ps.setString(5, dto.getEntidad().getNombreUsuario());
            ps.setString(6, dto.getEntidad().getContrasenia());
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

    public void update(RevisorDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_UPDATE);
            ps.setString(1, dto.getEntidad().getNombre());
            ps.setString(2, dto.getEntidad().getApPaterno());
            ps.setString(3, dto.getEntidad().getApMaterno());
            ps.setString(4, dto.getEntidad().getEmail());
            ps.setString(5, dto.getEntidad().getNombreUsuario());
            ps.setString(6, dto.getEntidad().getContrasenia());
            ps.setInt(7, dto.getEntidad().getIdRevisor());
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

    public void delete(RevisorDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_DELETE);
            ps.setInt(1, dto.getEntidad().getIdRevisor());
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
    
    public RevisorDTO read(RevisorDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List lista = null;
        try {
            ps = conexion.prepareStatement(SQL_SELECT);
            ps.setInt(1, dto.getEntidad().getIdRevisor());
            rs = ps.executeQuery();
            lista = obtenerResultados(rs);
            if (!lista.isEmpty()){
                return (RevisorDTO) lista.get(0);
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
        RevisorDTO dto = new RevisorDTO();
        dto.getEntidad().setIdRevisor(1);
        /*dto.getEntidad().setNombre("David");
        dto.getEntidad().setApPaterno("Meza");
        dto.getEntidad().setApMaterno("Albarran");
        dto.getEntidad().setEmail("mezaAzul@gmail.com");
        dto.getEntidad().setNombreUsuario("nuevoUsuario");
        dto.getEntidad().setContrasenia("nuevoContra");*/

        RevisorDAO dao = new RevisorDAO();
        try {
            //dao.create(dto);
            //dao.update(dto);
            //dao.delete(dto);
            //System.out.println(dao.readAll());
            System.out.println(dao.read(dto));
        } catch (SQLException ex) {
            Logger.getLogger(RevisorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List obtenerResultados(ResultSet rs) throws SQLException{
        List resultados = new ArrayList();
        while (rs.next()) {            
            RevisorDTO dto = new RevisorDTO();
            dto.getEntidad().setIdRevisor(rs.getInt("idRevisor"));
            dto.getEntidad().setNombre(rs.getString("nombre"));
            dto.getEntidad().setApPaterno(rs.getString("apPaterno"));
            dto.getEntidad().setApMaterno(rs.getString("apMaterno"));
            dto.getEntidad().setEmail(rs.getString("email"));
            dto.getEntidad().setNombreUsuario(rs.getString("nombreUsuario"));
            dto.getEntidad().setContrasenia(rs.getString("contrasenia"));
            resultados.add(dto);
        }
        return resultados;
    }
}