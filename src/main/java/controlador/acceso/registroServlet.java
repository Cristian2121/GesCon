package controlador.acceso;

import dao.AsistenteDAO;
import dao.AutorDAO;
import dao.RevisorDAO;
import dto.AsistenteDTO;
import dto.AutorDTO;
import dto.RevisorDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "registroServlet", urlPatterns = {"/registroServlet"})
public class registroServlet extends HttpServlet {

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
        String nombre = request.getParameter("nombre");
        String apPaterno = request.getParameter("apPaterno");
        String apMaterno = request.getParameter("apMaterno");
        String email = request.getParameter("email");
        String nombreUsuario = request.getParameter("nombreUsuario");
        String contrasenia = request.getParameter("contrasenia");
        int tipoUsuario = Integer.parseInt(request.getParameter("tipoUsuario"));

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' ></script>");
            out.println("<title>Respuesta registro</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");

            if (tipoUsuario == 1) {
                registrarAsistente(response, nombre, apPaterno, apMaterno, email, nombreUsuario, contrasenia);
            } else if (tipoUsuario == 2) {
                registrarAutor(response, nombre, apPaterno, apMaterno, email, nombreUsuario, contrasenia);
            } else {
                registrarRevisor(response, nombre, apPaterno, apMaterno, email, nombreUsuario, contrasenia);
            }

            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
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

    private void registrarAsistente(HttpServletResponse response, String nombre, String apPaterno, String apMaterno, String email, String nombreUsuario, String contrasenia)
            throws ServletException, IOException {
        AsistenteDTO dto = new AsistenteDTO();
        AsistenteDAO dao = new AsistenteDAO();

        dto.getEntidad().setNombre(nombre);
        dto.getEntidad().setApPaterno(apPaterno);
        dto.getEntidad().setApMaterno(apMaterno);
        dto.getEntidad().setEmail(email);
        dto.getEntidad().setNombreUsuario(nombreUsuario);
        dto.getEntidad().setContrasenia(contrasenia);

        PrintWriter out = response.getWriter();
        try {
            dao.create(dto);
            out.println("<div class='alert alert-info' role='alert'>");
            out.println("Asistente registrado con éxito <a href='index.html' class='alert-link'>Volver</a>");
            out.println("</div>");
        } catch (SQLException ex) {
            out.println("<div class='alert alert-danger' role='alert'>");
            out.println("No se pudo registrar el Asistente <a href='index.html' class='alert-link'>Volver</a>");
            out.println("</div>");
            Logger.getLogger(registroServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void registrarAutor(HttpServletResponse response, String nombre, String apPaterno, String apMaterno, String email, String nombreUsuario, String contrasenia)
            throws ServletException, IOException {
        AutorDTO dto = new AutorDTO();
        AutorDAO dao = new AutorDAO();
        
        dto.getEntidad().setNombre(nombre);
        dto.getEntidad().setApPaterno(apPaterno);
        dto.getEntidad().setApMaterno(apMaterno);
        dto.getEntidad().setEmail(email);
        dto.getEntidad().setNombreUsuario(nombreUsuario);
        dto.getEntidad().setContrasenia(contrasenia);

        PrintWriter out = response.getWriter();
        try {
            dao.create(dto);
            out.println("<div class='alert alert-info' role='alert'>");
            out.println("Autor registrado con éxito <a href='index.html' class='alert-link'>Volver</a>");
            out.println("</div>");
        } catch (SQLException ex) {
            out.println("<div class='alert alert-danger' role='alert'>");
            out.println("No se pudo registrar el Autor <a href='index.html' class='alert-link'>Volver</a>");
            out.println("</div>");
            Logger.getLogger(registroServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void registrarRevisor(HttpServletResponse response, String nombre, String apPaterno, String apMaterno, String email, String nombreUsuario, String contrasenia)
            throws ServletException, IOException {
        RevisorDTO dto = new RevisorDTO();
        RevisorDAO dao = new RevisorDAO();
        
        dto.getEntidad().setNombre(nombre);
        dto.getEntidad().setApPaterno(apPaterno);
        dto.getEntidad().setApMaterno(apMaterno);
        dto.getEntidad().setEmail(email);
        dto.getEntidad().setNombreUsuario(nombreUsuario);
        dto.getEntidad().setContrasenia(contrasenia);

        PrintWriter out = response.getWriter();
        try {
            dao.create(dto);
            out.println("<div class='alert alert-info' role='alert'>");
            out.println("Revisor registrado con éxito <a href='index.html' class='alert-link'>Volver</a>");
            out.println("</div>");
        } catch (SQLException ex) {
            out.println("<div class='alert alert-danger' role='alert'>");
            out.println("No se pudo registrar el Revisor <a href='index.html' class='alert-link'>Volver</a>");
            out.println("</div>");
            Logger.getLogger(registroServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
