package dao;

import dto.OrganizadorComiteDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrganizadorComiteDAO {
    private static final String SQL_INSERT = "insert into OrganizadorComite (nombre, apPaterno, apMaterno, email, nombreUsuario, contrasenia, idJefeComite) "
            + " values(?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "update OrganizadorComite set nombre=?, apPaterno=?, apMaterno=?, email=?, nombreUsuario=?, contrasenia=?, idJefeComite=? "
            + " where idOrganizador=?";
    private static final String SQL_DELETE = "delete from OrganizadorComite where idOrganizador=?";
    private static final String SQL_SELECT = "select * from OrganizadorComite where idOrganizador=?";
    private static final String SQL_SELECT_ALL = "select * from OrganizadorComite";

    private Connection conexion;

    public OrganizadorComiteDAO() {
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
            Logger.getLogger(OrganizadorComiteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void create(OrganizadorComiteDTO dto) throws SQLException {
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
            ps.setInt(7, dto.getEntidad().getIdJefeComite());
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

    public void update(OrganizadorComiteDTO dto) throws SQLException {
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
            ps.setInt(7, dto.getEntidad().getIdJefeComite());
            ps.setInt(8, dto.getEntidad().getIdOrganizador());
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

    public void delete(OrganizadorComiteDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_DELETE);
            ps.setInt(1, dto.getEntidad().getIdOrganizador());
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
    
    public OrganizadorComiteDTO read(OrganizadorComiteDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List lista = null;
        try {
            ps = conexion.prepareStatement(SQL_SELECT);
            ps.setInt(1, dto.getEntidad().getIdOrganizador());
            rs = ps.executeQuery();
            lista = obtenerResultados(rs);
            if (!lista.isEmpty()){
                return (OrganizadorComiteDTO) lista.get(0);
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
        OrganizadorComiteDTO dto = new OrganizadorComiteDTO();
        dto.getEntidad().setIdOrganizador(1);
        /*dto.getEntidad().setNombre("Marcos");
        dto.getEntidad().setApPaterno("Lopez");
        dto.getEntidad().setApMaterno("Quintana");
        dto.getEntidad().setEmail("lopezQ@gmail.com");
        dto.getEntidad().setNombreUsuario("amo21N");
        dto.getEntidad().setContrasenia("starAzulN");
        dto.getEntidad().setIdJefeComite(1);*/

        OrganizadorComiteDAO dao = new OrganizadorComiteDAO();
        try {
            //dao.create(dto);
            //dao.update(dto);
            //dao.delete(dto);
            //System.out.println(dao.readAll());
            System.out.println(dao.read(dto));
        } catch (SQLException ex) {
            Logger.getLogger(OrganizadorComiteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List obtenerResultados(ResultSet rs) throws SQLException{
        List resultados = new ArrayList();
        while (rs.next()) {            
            OrganizadorComiteDTO dto = new OrganizadorComiteDTO();
            dto.getEntidad().setIdOrganizador(rs.getInt("idOrganizador"));
            dto.getEntidad().setNombre(rs.getString("nombre"));
            dto.getEntidad().setApPaterno(rs.getString("apPaterno"));
            dto.getEntidad().setApMaterno(rs.getString("apMaterno"));
            dto.getEntidad().setEmail(rs.getString("email"));
            dto.getEntidad().setNombreUsuario(rs.getString("nombreUsuario"));
            dto.getEntidad().setContrasenia(rs.getString("contrasenia"));
            dto.getEntidad().setIdJefeComite(rs.getInt("idJefeComite"));
            resultados.add(dto);
        }
        return resultados;
    }
}