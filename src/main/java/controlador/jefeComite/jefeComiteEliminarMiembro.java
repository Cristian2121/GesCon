package controlador.jefeComite;

import dao.OrganizadorComiteDAO;
import dto.OrganizadorComiteDTO;
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

@WebServlet(name = "jefeComiteEliminarMiembro", urlPatterns = {"/jefeComiteEliminarMiembro"})
public class jefeComiteEliminarMiembro extends HttpServlet {

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
        
        int idOrganizador = Integer.parseInt(request.getParameter("idOrganizador"));
        int idJefeComite = Integer.parseInt(request.getParameter("idJefeComite"));
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' ></script>");
            out.println("<title>Eliminar miembro</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            
            OrganizadorComiteDAO dao = new OrganizadorComiteDAO();
            OrganizadorComiteDTO dto = new OrganizadorComiteDTO();
            dto.getEntidad().setIdOrganizador(idOrganizador);
            
            try {
                dao.delete(dto);
                out.println("<div class='alert alert-info' role='alert'>");
                out.println("Organizador eliminado con ??xito <a href='jefeComiteAdminMiembro?idJefeComite=" + idJefeComite + "' class='alert-link'>Volver</a>");
                out.println("</div>");
            } catch (SQLException ex) {
                out.println("<div class='alert alert-danger' role='alert'>");
                out.println("No se pudo eliminar el Organizador <a href='jefeComiteAdminMiembro?idJefeComite=" + idJefeComite + "' class='alert-link'>Volver</a>");
                out.println("</div>");
                Logger.getLogger(jefeComiteMiembroCreado.class.getName()).log(Level.SEVERE, null, ex);
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
