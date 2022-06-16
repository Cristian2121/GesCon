package controlador.jefeComite;

import dao.OrganizadorComiteDAO;
import dto.OrganizadorComiteDTO;
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

@WebServlet(name = "jefeComiteAdminMiembro", urlPatterns = {"/jefeComiteAdminMiembro"})
public class jefeComiteAdminMiembro extends HttpServlet {

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
        
        int idJefeComite = Integer.parseInt(request.getParameter("idJefeComite"));
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' ></script>");
            out.println("<title>Gestión miembro</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<header><h1 align='center'>Miembros del cómite</h1></header>");
            out.println("<div><a href='jefeComiteServlet?idJefeComite=" + idJefeComite + "' class='btn btn-secondary'>Regresar</a><br /><br />");
            out.println("<div><a href='jefeComiteCrearMiembro?idJefeComite=" + idJefeComite + "' class='btn btn-success'>Nuevo miembro</a><br /><br />");
            out.println("<table class='table table-striped'>");
            out.println("<tr><td>Usuario</td><td>Email</td><td>Acciones</td></tr>");

            OrganizadorComiteDAO dao = new OrganizadorComiteDAO();
            OrganizadorComiteDTO dto = new OrganizadorComiteDTO();

            try {
                List lista = dao.readAll();

                for (int i = 0; i < lista.size(); i++) {
                    dto = (OrganizadorComiteDTO) lista.get(i);

                    out.println("<tr>");
                    out.println("<td>" + dto.getEntidad().getNombreUsuario() + "</td>");
                    out.println("<td>" + dto.getEntidad().getEmail()+ "</td>");
                    out.println("<td><a href='jefeComiteVerMiembro?idOrganizador=" + dto.getEntidad().getIdOrganizador() + "&idJefeComite=" + idJefeComite + "' class='btn btn-primary'>Ver</a>" + " | "
                            + "<a href='jefeComiteModificarMiembro?idOrganizador=" + dto.getEntidad().getIdOrganizador() + "&idJefeComite=" + idJefeComite + "' class='btn btn-warning'>Modificar</a>" + " | "
                            + "<a href='jefeComiteEliminarMiembro?idOrganizador=" + dto.getEntidad().getIdOrganizador() + "&idJefeComite=" + idJefeComite + "' class='btn btn-danger'>Eliminar</a></td>");
                    out.println("</tr>");
                }
                out.println("</table></div>");

            } catch (SQLException ex) {
                Logger.getLogger(jefeComiteAdminMiembro.class.getName()).log(Level.SEVERE, null, ex);
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
