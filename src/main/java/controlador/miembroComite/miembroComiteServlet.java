package controlador.miembroComite;

import dao.EventoDAO;
import dto.EventoDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "miembroComiteServlet", urlPatterns = {"/miembroComiteServlet"})
public class miembroComiteServlet extends HttpServlet {

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
        
        int idMiembro = Integer.parseInt(request.getParameter("idMiembro"));
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' ></script>");
            out.println("<title>Miembro Comite</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'><br /><br />");
            out.println("<header><h1 align='center'>Administrar eventos</h1></header>");
            out.println("<div><a href='index.html' class='btn btn-secondary'>Regresar</a><br /><br />");
            out.println("<div><a href='miembroCrearEvento?idMiembro=" + idMiembro + "' class='btn btn-success'>Nuevo evento</a>");
            out.println("<a href='miembroAdminProgramas?idMiembro=" + idMiembro + "' class='btn btn-primary'>Programas</a><br /><br />");
            out.println("<table class='table table-striped'>");
            out.println("<tr><td>Nombre</td><td>Cupo</td><td>Acciones</td></tr>");
            
            EventoDAO dao = new EventoDAO();
            EventoDTO dto = new EventoDTO();
            
            try {
                List lista = dao.readAll();
                
                for(int i = 0; i < lista.size(); i++){
                    dto = (EventoDTO) lista.get(i);
                    
                    out.println("<tr>");
                    out.println("<td>" + dto.getEntidad().getNombreEvento() + "</td>");
                    out.println("<td>" + dto.getEntidad().getCupo() + "</td>");
                    out.println("<td><a href='miembroCreaListaAsistentes?idEvento=" + dto.getEntidad().getIdEvento() + "' class='btn btn-secondary'>Asistentes</a>" + " | "
                            + "<a href='miembroCreaCredenciales?idEvento=" + dto.getEntidad().getIdEvento() + "' class='btn btn-primary'>Credenciales</a>" + " | "
                            + "<a href='miembroCreaConstancias?idEvento=" + dto.getEntidad().getIdEvento() + "' class='btn btn-dark'>Constancias</a>" + " | "
                            + "<a href='miembroVerEvento?idEvento=" + dto.getEntidad().getIdEvento() + "&idMiembro=" + idMiembro + "' class='btn btn-info'>Ver</a>" + " | "
                            + "<a href='miembroModificarEvento?idEvento=" + dto.getEntidad().getIdEvento() + "&idMiembro=" + idMiembro + "' class='btn btn-warning'>Modificar</a>" + " | "
                            + "<a href='miembroEliminarEvento?idEvento=" + dto.getEntidad().getIdEvento() + "&idMiembro=" + idMiembro + "' class='btn btn-danger'>Eliminar</a>");
                    out.println("<tr>");
                }
            } catch (SQLException ex) {
                Logger.getLogger(miembroComiteServlet.class.getName()).log(Level.SEVERE, null, ex);
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

}
