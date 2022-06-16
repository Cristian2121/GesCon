package controlador.acceso;

import dao.AsistenteDAO;
import dao.AutorDAO;
import dao.JefeComiteDAO;
import dao.OrganizadorComiteDAO;
import dao.RevisorDAO;
import dto.AsistenteDTO;
import dto.AutorDTO;
import dto.JefeComiteDTO;
import dto.OrganizadorComiteDTO;
import dto.RevisorDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "loginServlet", urlPatterns = {"/loginServlet"})
public class loginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String nombre = request.getParameter("nombreUsuario");
        String contrasenia = request.getParameter("contrasenia");
        int tipoUsuario = Integer.parseInt(request.getParameter("tipoUsuario"));

        if (tipoUsuario == 1) {
            verificarAsistente(response, nombre, contrasenia);
        } else if(tipoUsuario == 2){
            verificarJefeComite(response, nombre, contrasenia);
        } else if(tipoUsuario == 3){
            verificarRevisor(response, nombre, contrasenia);
        } else if(tipoUsuario == 4){
            verificarAutor(response, nombre, contrasenia);
        } else{
            verificarOrganizadorComite(response, nombre, contrasenia);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void verificarAsistente(HttpServletResponse response, String nombre, String contrasenia)
            throws ServletException, IOException {
        AsistenteDTO dto = new AsistenteDTO();
        AsistenteDAO dao = new AsistenteDAO();
        boolean bandera = false;
        
        try {
            List lista = dao.readAll();
            
            for(int i = 0; i < lista.size(); i++){
                dto = (AsistenteDTO) lista.get(i);
                
                if(nombre.compareTo(dto.getEntidad().getNombreUsuario()) == 0 && contrasenia.compareTo(dto.getEntidad().getContrasenia()) == 0){
                    bandera = true;
                    break;
                }
            }
            
            if(bandera){
                response.sendRedirect("asistenteServlet?idAsistente=" + dto.getEntidad().getIdAsistente());
                return;
            } else{
                response.sendRedirect("errorLoginServlet");
            }
        } catch (SQLException ex) {
            Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void verificarJefeComite(HttpServletResponse response, String nombre, String contrasenia)
            throws ServletException, IOException {
        JefeComiteDTO dto = new JefeComiteDTO();
        JefeComiteDAO dao = new JefeComiteDAO();
        boolean bandera = false;
        
        try {
            List lista = dao.readAll();
            
            for(int i = 0; i < lista.size(); i++){
                dto = (JefeComiteDTO) lista.get(i);
                
                if(nombre.compareTo(dto.getEntidad().getNombreUsuario()) == 0 && contrasenia.compareTo(dto.getEntidad().getContrasenia()) == 0){
                    bandera = true;
                    break;
                }
            }
            
            if(bandera){
                response.sendRedirect("jefeComiteServlet?idJefeComite=" + dto.getEntidad().getIdJefeComite());
                return; // Para reiterar que es lo último en el código
            } else{
                response.sendRedirect("errorLoginServlet");
            }
        } catch (SQLException ex) {
            Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void verificarRevisor(HttpServletResponse response, String nombre, String contrasenia)
            throws ServletException, IOException {
        RevisorDTO dto = new RevisorDTO();
        RevisorDAO dao = new RevisorDAO();
        boolean bandera = false;
        
        try {
            List lista = dao.readAll();
            
            for(int i = 0; i < lista.size(); i++){
                dto = (RevisorDTO) lista.get(i);
                
                if(nombre.compareTo(dto.getEntidad().getNombreUsuario()) == 0 && contrasenia.compareTo(dto.getEntidad().getContrasenia()) == 0){
                    bandera = true;
                    break;
                }
            }
            
            if(bandera){
                response.sendRedirect("revisorServlet?idRevisor=" + dto.getEntidad().getIdRevisor());
                return;
            } else{
                response.sendRedirect("errorLoginServlet");
            }
        } catch (SQLException ex) {
            Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void verificarAutor(HttpServletResponse response, String nombre, String contrasenia)
            throws ServletException, IOException {
        AutorDTO dto = new AutorDTO();
        AutorDAO dao = new AutorDAO();
        boolean bandera = false;
        
        try {
            List lista = dao.readAll();
            
            for(int i = 0; i < lista.size(); i++){
                dto = (AutorDTO) lista.get(i);
                
                if(nombre.compareTo(dto.getEntidad().getNombreUsuario()) == 0 && contrasenia.compareTo(dto.getEntidad().getContrasenia()) == 0){
                    bandera = true;
                    break;
                }
            }
            
            if(bandera){
                response.sendRedirect("autorServlet?idAutor=" + dto.getEntidad().getIdAutor());
                return;
            } else{
                response.sendRedirect("errorLoginServlet");
            }
        } catch (SQLException ex) {
            Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void verificarOrganizadorComite(HttpServletResponse response, String nombre, String contrasenia)
            throws ServletException, IOException {
        OrganizadorComiteDTO dto = new OrganizadorComiteDTO();
        OrganizadorComiteDAO dao = new OrganizadorComiteDAO();
        boolean bandera = false;
        
        try {
            List lista = dao.readAll();
            
            for(int i = 0; i < lista.size(); i++){
                dto = (OrganizadorComiteDTO) lista.get(i);
                
                if(nombre.compareTo(dto.getEntidad().getNombreUsuario()) == 0 && contrasenia.compareTo(dto.getEntidad().getContrasenia()) == 0){
                    bandera = true;
                    break;
                }
            }
            
            if(bandera){
                response.sendRedirect("miembroComiteServlet?idMiembro=" + dto.getEntidad().getIdOrganizador());
                return;
            } else{
                response.sendRedirect("errorLoginServlet");
            }
        } catch (SQLException ex) {
            Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
