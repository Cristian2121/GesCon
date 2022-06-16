package controlador.asistente;

import dao.AsistenciasDAO;
import dao.EventoDAO;
import dto.AsistenciasDTO;
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

@WebServlet(name = "asistenteInscribirEvento", urlPatterns = {"/asistenteInscribirEvento"})
public class asistenteInscribirEvento extends HttpServlet {

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
            out.println("<title>Inscribir evento</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");

            EventoDAO dao = new EventoDAO();
            EventoDTO dto = new EventoDTO();
            EventoDTO leido = new EventoDTO();
            dto.getEntidad().setIdEvento(idEvento);

            AsistenciasDAO dao2 = new AsistenciasDAO();
            AsistenciasDTO dto2 = new AsistenciasDTO();
            AsistenciasDTO leido2 = new AsistenciasDTO();

            try {
                List listaAsistente = dao2.readAll();
                boolean inscrito = false;

                if (listaAsistente != null) {
                    for (int i = 0; i < listaAsistente.size(); i++) {
                        dto2 = (AsistenciasDTO) listaAsistente.get(i);

                        if (idAsistente == dto2.getEntidad().getIdAsistente() && idEvento == dto2.getEntidad().getIdEvento()) {
                            inscrito = true;
                        }
                    }
                }

                if (inscrito) {
                    out.println("<div class='alert alert-info' role='alert'>");
                    out.println("El evento ya ha sido inscrito anteriormente. <a href='asistenteServlet?idAsistente=" + idAsistente + "' class='alert-link'>Volver</a>");
                    out.println("</div>");
                } else {
                    leido = dao.read(dto);
                    leido.getEntidad().setCupo(leido.getEntidad().getCupo() - 1);
                    dao.update(leido);

                    leido2.getEntidad().setIdAsistente(idAsistente);
                    leido2.getEntidad().setIdEvento(idEvento);
                    dao2.create(leido2);
                    out.println("<div class='alert alert-info' role='alert'>");
                    out.println("Evento inscrito. <a href='asistenteServlet?idAsistente=" + idAsistente + "' class='alert-link'>Volver</a>");
                    out.println("</div>");
                }
            } catch (SQLException ex) {
                out.println("<div class='alert alert-danger' role='alert'>");
                out.println("El evento no pudo inscribirse. <a href='asistenteServlet?idAsistente=" + idAsistente + "' class='alert-link'>Volver</a>");
                out.println("</div>");
                Logger.getLogger(asistenteInscribirEvento.class.getName()).log(Level.SEVERE, null, ex);
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
