package controlador.jefeComite;

import dao.ArticuloDAO;
import dto.ArticuloDTO;
import java.io.File;
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

@WebServlet(name = "jefeComiteServlet", urlPatterns = {"/jefeComiteServlet"})
public class jefeComiteServlet extends HttpServlet {

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
            out.println("<title>Jefe de comite</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<header><h1 align='center'>Administrar artículos</h1></header>");
            out.println("<div><a href='index.html' class='btn btn-secondary'>Regresar</a><br /><br />");
            out.println("<div><a href='jefeComiteAdminMiembro?idJefeComite=" + idJefeComite + "' class='btn btn-success'>Gestionar cómite</a>");
            out.println("<a href='jefeComiteContactar' class='btn btn-primary'>Contactar</a><br /><br />");
            out.println("<table class='table table-striped'>");
            out.println("<tr><td>Artículo</td><td>Estado</td><td>Acciones</td></tr>");

            ArticuloDAO dao = new ArticuloDAO();
            ArticuloDTO dto = new ArticuloDTO();

            try {
                List lista = dao.readAll();

                for (int i = 0; i < lista.size(); i++) {
                    dto = (ArticuloDTO) lista.get(i);

                    File articulo = new File(dto.getEntidad().getRutaDocumento());
                    int evaluacion = dto.getEntidad().getEvaluacion();

                    out.println("<tr>");
                    out.println("<td>" + articulo.getName() + "</td>");
                    if (evaluacion == 1) {
                        out.println("<td>Aceptado</td>");
                    } else if (evaluacion == 2) {
                        out.println("<td>Rechazado</td>");
                    } else if (evaluacion == 3) {
                        out.println("<td>Pendiente</td>");
                    } else if (evaluacion == 4){
                        out.println("<td>Versión final</td>");
                    }
                    out.println("<td><a href='jefeComiteAsignarRevisor?idArticulo=" + dto.getEntidad().getIdArticulo() + "&nombreArt=" + articulo.getName() + "&idJefeComite=" + idJefeComite + "' class='btn btn-primary'>Asignar revisor</a>" + " | "
                            + "<a href='jefeComiteAceptarArt?idArticulo=" + dto.getEntidad().getIdArticulo() + "&idJefeComite=" + idJefeComite + "' class='btn btn-success'>Aceptar</a>" + " | "
                            + "<a href='jefeComiteRechazarArt?idArticulo=" + dto.getEntidad().getIdArticulo() + "&idJefeComite=" + idJefeComite + "' class='btn btn-warning'>Rechazar</a>" + " | "
                            + "<a href='jefeComiteDescartarArt?idArticulo=" + dto.getEntidad().getIdArticulo() + "&idJefeComite=" + idJefeComite + "' class='btn btn-danger'>Descartar</a></td>");
                    out.println("</tr>");
                }
                out.println("</table></div>");

            } catch (SQLException ex) {
                Logger.getLogger(jefeComiteServlet.class.getName()).log(Level.SEVERE, null, ex);
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
