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

@WebServlet(name = "jefeComiteVerMiembro", urlPatterns = {"/jefeComiteVerMiembro"})
public class jefeComiteVerMiembro extends HttpServlet {

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
            out.println("<title>Ver miembro</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'><br/><br/>");
            out.println("<header align='center'><h1>Ver miembro</h1></header><br/><br/>");

            OrganizadorComiteDAO dao = new OrganizadorComiteDAO();
            OrganizadorComiteDTO dto = new OrganizadorComiteDTO();
            OrganizadorComiteDTO dto2 = new OrganizadorComiteDTO();
            dto.getEntidad().setIdOrganizador(idOrganizador);

            try {
                dto2 = dao.read(dto);
                
                out.println("<table class='table'>");
                out.println("<tr><td>Nombre</td><td>" + dto2.getEntidad().getNombre() + "</td></tr>");
                out.println("<tr><td>Apellido paterno</td><td>" + dto2.getEntidad().getApPaterno()+ "</td></tr>");
                out.println("<tr><td>Apellido materno</td><td>" + dto2.getEntidad().getApMaterno()+ "</td></tr>");
                out.println("<tr><td>Email</td><td>" + dto2.getEntidad().getEmail()+ "</td></tr>");
                out.println("<tr><td>Nombre usuario</td><td>" + dto2.getEntidad().getNombreUsuario()+ "</td></tr>");
                out.println("<tr><td>Contraseña</td><td>" + dto2.getEntidad().getContrasenia()+ "</td></tr>");
                out.println("<tr><td>ID del jefe a cargo</td><td>" + dto2.getEntidad().getIdJefeComite()+ "</td></tr>");
                out.println("</table>");
            } catch (SQLException ex) {
                Logger.getLogger(jefeComiteVerMiembro.class.getName()).log(Level.SEVERE, null, ex);
            }

            out.println("<div class='mb-3' align='center'>");
            out.println("<a href='jefeComiteAdminMiembro?idJefeComite=" + idJefeComite + "' class='btn btn-secondary'>Regresar</a>");
            out.println("</div>");
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
