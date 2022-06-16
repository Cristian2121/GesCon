package dao;

import dto.ArticuloDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArticuloDAO {
    private static final String SQL_INSERT = "insert into Articulo (rutaDocumento, evaluacion, idAutor, idRevisor, idJefeComite, idPrograma) "
            + " values(?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "update Articulo set rutaDocumento=?, evaluacion=?, idAutor=?, idRevisor=?, idJefeComite=?, idPrograma=? "
            + " where idArticulo=?";
    private static final String SQL_DELETE = "delete from Articulo where idArticulo=?";
    private static final String SQL_SELECT = "select * from Articulo where idArticulo=?";
    private static final String SQL_SELECT_ALL = "select * from Articulo";

    private Connection conexion;

    public ArticuloDAO() {
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
            Logger.getLogger(ArticuloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void create(ArticuloDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_INSERT);
            ps.setString(1, dto.getEntidad().getRutaDocumento());
            ps.setInt(2, dto.getEntidad().getEvaluacion());
            ps.setInt(3, dto.getEntidad().getIdAutor());
            ps.setInt(4, dto.getEntidad().getIdRevisor());
            ps.setInt(5, dto.getEntidad().getIdJefeComite());
            ps.setInt(6, dto.getEntidad().getIdPrograma());
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

    public void update(ArticuloDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_UPDATE);
            ps.setString(1, dto.getEntidad().getRutaDocumento());
            ps.setInt(2, dto.getEntidad().getEvaluacion());
            ps.setInt(3, dto.getEntidad().getIdAutor());
            ps.setInt(4, dto.getEntidad().getIdRevisor());
            ps.setInt(5, dto.getEntidad().getIdJefeComite());
            ps.setInt(6, dto.getEntidad().getIdPrograma());
            ps.setInt(7, dto.getEntidad().getIdArticulo());
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

    public void delete(ArticuloDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_DELETE);
            ps.setInt(1, dto.getEntidad().getIdArticulo());
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
    
    public ArticuloDTO read(ArticuloDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List lista = null;
        try {
            ps = conexion.prepareStatement(SQL_SELECT);
            ps.setInt(1, dto.getEntidad().getIdArticulo());
            rs = ps.executeQuery();
            lista = obtenerResultados(rs);
            if (!lista.isEmpty()){
                return (ArticuloDTO) lista.get(0);
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
        ArticuloDTO dto = new ArticuloDTO();
        dto.getEntidad().setIdArticulo(1);
        /*dto.getEntidad().setRutaDocumento("C://crdaf/documents/8080/");
        dto.getEntidad().setEvaluacion(2);
        dto.getEntidad().setIdAutor(1);
        dto.getEntidad().setIdRevisor(1);
        dto.getEntidad().setIdJefeComite(1);
        dto.getEntidad().setIdPrograma(1);*/
        
        ArticuloDAO dao = new ArticuloDAO();
        try {
            //dao.create(dto);
            //dao.update(dto);
            //dao.delete(dto);
            //System.out.println(dao.readAll());
            System.out.println(dao.read(dto));
        } catch (SQLException ex) {
            Logger.getLogger(ArticuloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List obtenerResultados(ResultSet rs) throws SQLException{
        List resultados = new ArrayList();
        while (rs.next()) {            
            ArticuloDTO dto = new ArticuloDTO();
            dto.getEntidad().setIdArticulo(rs.getInt("idArticulo"));
            dto.getEntidad().setRutaDocumento(rs.getString("rutaDocumento"));
            dto.getEntidad().setEvaluacion(rs.getInt("evaluacion"));
            dto.getEntidad().setIdAutor(rs.getInt("idAutor"));
            dto.getEntidad().setIdRevisor(rs.getInt("idRevisor"));
            dto.getEntidad().setIdJefeComite(rs.getInt("idJefeComite"));
            dto.getEntidad().setIdPrograma(rs.getInt("idPrograma"));
            resultados.add(dto);
        }
        return resultados;
    }
}