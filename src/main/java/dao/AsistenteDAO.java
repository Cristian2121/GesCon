package dao;

import dto.AsistenteDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AsistenteDAO {
    private static final String SQL_INSERT = "insert into Asistente (nombre, apPaterno, apMaterno, email, nombreUsuario, contrasenia) "
            + " values(?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "update Asistente set nombre=?, apPaterno=?, apMaterno=?, email=?, nombreUsuario=?, contrasenia=? "
            + " where idAsistente=?";
    private static final String SQL_DELETE = "delete from Asistente where idAsistente=?";
    private static final String SQL_SELECT = "select * from Asistente where idAsistente=?";
    private static final String SQL_SELECT_ALL = "select * from Asistente";

    private Connection conexion;

    public AsistenteDAO() {
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
            Logger.getLogger(AsistenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void create(AsistenteDTO dto) throws SQLException {
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

    public void update(AsistenteDTO dto) throws SQLException {
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
            ps.setInt(7, dto.getEntidad().getIdAsistente());
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

    public void delete(AsistenteDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_DELETE);
            ps.setInt(1, dto.getEntidad().getIdAsistente());
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
    
    public AsistenteDTO read(AsistenteDTO dto) throws SQLException {
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
                return (AsistenteDTO) lista.get(0);
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
        AsistenteDTO dto = new AsistenteDTO();
        dto.getEntidad().setIdAsistente(1);
        /*dto.getEntidad().setNombre("Fernanda");
        dto.getEntidad().setApPaterno("Tapia");
        dto.getEntidad().setApMaterno("Dolores");
        dto.getEntidad().setEmail("ferTapia@gmail.com");
        dto.getEntidad().setNombreUsuario("ferchaNuevo");
        dto.getEntidad().setContrasenia("fer2121Nuevo");*/

        AsistenteDAO dao = new AsistenteDAO();
        try {
            //dao.create(dto);
            //dao.update(dto);
            //dao.delete(dto);
            //System.out.println(dao.readAll());
            System.out.println(dao.read(dto));
        } catch (SQLException ex) {
            Logger.getLogger(AsistenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List obtenerResultados(ResultSet rs) throws SQLException{
        List resultados = new ArrayList();
        while (rs.next()) {            
            AsistenteDTO dto = new AsistenteDTO();
            dto.getEntidad().setIdAsistente(rs.getInt("idAsistente"));
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

