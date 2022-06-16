package controlador.asistente;

import dao.AsistenciasDAO;
import dao.EventoDAO;
import dto.AsistenciasDTO;
import dto.EventoDTO;
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

@WebServlet(name = "asistenteBajaEvento", urlPatterns = {"/asistenteBajaEvento"})
public class asistenteBajaEvento extends HttpServlet {

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
        
        int idAsistente = Integer.parseInt(request.getParameter("idAsistente"));
        int idEvento = Integer.parseInt(request.getParameter("idEvento"));
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' ></script>");
            out.println("<title>Baja evento</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            
            AsistenciasDAO dao = new AsistenciasDAO();
            AsistenciasDTO dto = new AsistenciasDTO();
            dto.getEntidad().setIdAsistente(idAsistente);
            dto.getEntidad().setIdEvento(idEvento);
            
            EventoDAO dao2 = new EventoDAO();
            EventoDTO dto2 = new EventoDTO();
            EventoDTO evento = new EventoDTO();
            dto2.getEntidad().setIdEvento(idEvento);
            
            try {
                dao.delete(dto);
                
                evento = dao2.read(dto2);
                evento.getEntidad().setCupo(evento.getEntidad().getCupo() + 1);
                dao2.update(evento);
                
                out.println("<div class='alert alert-info' role='alert'>");
                out.println("El evento se dió de baja. <a href='asistenteServlet?idAsistente=" + idAsistente + "' class='alert-link'>Volver</a>");
                out.println("</div>");
            } catch (SQLException ex) {
                out.println("<div class='alert alert-danger' role='alert'>");
                out.println("El evento no pudo darse baja. <a href='asistenteServlet?idAsistente=" + idAsistente + "' class='alert-link'>Volver</a>");
                out.println("</div>");
                Logger.getLogger(asistenteBajaEvento.class.getName()).log(Level.SEVERE, null, ex);
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
