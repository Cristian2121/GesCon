package controlador.asistente;

import dao.EventoDAO;
import dao.ProgramaDAO;
import dto.EventoDTO;
import dto.ProgramaDTO;
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

@WebServlet(name = "asistenteVerEvento", urlPatterns = {"/asistenteVerEvento"})
public class asistenteVerEvento extends HttpServlet {

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

        int idEvento = Integer.parseInt(request.getParameter("idEvento"));
        int idAsistente = Integer.parseInt(request.getParameter("idAsistente"));

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' ></script>");
            out.println("<title>Ver evento</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'><br /><br />");

            EventoDAO dao = new EventoDAO();
            EventoDTO dto = new EventoDTO();
            EventoDTO leido = new EventoDTO();
            dto.getEntidad().setIdEvento(idEvento);

            ProgramaDAO dao2 = new ProgramaDAO();
            ProgramaDTO dto2 = new ProgramaDTO();

            try {
                leido = dao.read(dto);
                int tipoEvento = leido.getEntidad().getTipoEvento();

                out.println("<header><h1>Evento: " + leido.getEntidad().getNombreEvento() + "</h1></header>");
                out.println("<h4><b>Cupo: </b>" + leido.getEntidad().getCupo() + "</h4>");
                out.println("<h4><b>Fecha: </b>" + leido.getEntidad().getFecha() + "</h4>");
                out.println("<div>");
                if (tipoEvento == 1) {
                    out.println("<h4><b>Tipo evento: </b>Ponencia</h4><br /><br />");
                } else if (tipoEvento == 2) {
                    out.println("<h4><b>Tipo evento: </b> Taller</h4><br /><br />");
                } else {
                    out.println("<h4><b>Tipo evento: </b> Seminario</h4><br /><br />");
                }
                out.println("<h4>Programas por presentar: </h4>");

                List listaProgramas = dao2.readAll();
                out.println("<table class='table table-striped'>");
                out.println("<tr><td>Nombre programa</td><td>Moderador</td></tr>");
                for (int i = 0; i < listaProgramas.size(); i++) {
                    dto2 = (ProgramaDTO) listaProgramas.get(i);

                    if (leido.getEntidad().getIdEvento() == dto2.getEntidad().getIdEvento()) {
                        out.println("<tr>");
                        out.println("<td>" + dto2.getEntidad().getNombrePrograma() + "</td>");
                        out.println("<td>" + dto2.getEntidad().getModerador() + "</td>");
                        out.println("</tr>");
                    }
                }
                out.println("</table>");
                out.println("</div>");

                out.println("<div class='mb-3' align='center'>");
                out.println("<a href='asistenteInscribirEvento?idEvento=" + idEvento + "&idAsistente=" + idAsistente + "' class='btn btn-warning'>Inscrbirme</a>");
                out.println("<a href='asistenteServlet?idAsistente=" + idAsistente + "' class='btn btn-secondary'>Regresar</a>");
                out.println("</div>");
            } catch (SQLException ex) {
                Logger.getLogger(asistenteVerEvento.class.getName()).log(Level.SEVERE, null, ex);
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
