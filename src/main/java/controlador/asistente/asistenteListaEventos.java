package controlador.asistente;

import dao.AsistenciasDAO;
import dao.EventoDAO;
import dto.AsistenciasDTO;
import dto.EventoDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "asistenteListaEventos", urlPatterns = {"/asistenteListaEventos"})
public class asistenteListaEventos extends HttpServlet {

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

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' ></script>");
            out.println("<title>Eventos inscritos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<header><h1 align='center'>Eventos inscritos</h1></header>");
            out.println("<div><a href='asistenteServlet?idAsistente=" + idAsistente + "' class='btn btn-secondary'>Regresar</a><br /><br />");
            out.println("<table class='table table-striped'>");
            out.println("<tr><td>Nombre</td><td>Fecha</td><td>Tipo</td><td>Acciones</td></tr>");

            EventoDAO dao = new EventoDAO();
            EventoDTO dto = new EventoDTO();

            AsistenciasDAO dao2 = new AsistenciasDAO();
            AsistenciasDTO dto2 = new AsistenciasDTO();

            try {
                List lista = dao.readAll();
                List asistencia = dao2.readAll();

                List<Integer> eventosAsistidos = new ArrayList<Integer>();
                for (int j = 0; j < asistencia.size(); j++) {
                    dto2 = (AsistenciasDTO) asistencia.get(j);

                    if (idAsistente == dto2.getEntidad().getIdAsistente()) {
                        eventosAsistidos.add(dto2.getEntidad().getIdEvento());
                    }
                }

                for (int i = 0; i < lista.size(); i++) {
                    dto = (EventoDTO) lista.get(i);

                    for (int a = 0; a < eventosAsistidos.size(); a++) {
                        if (dto.getEntidad().getIdEvento() == eventosAsistidos.get(a)) {
                            int tipoEvento = dto.getEntidad().getTipoEvento();

                            out.println("<tr>");
                            out.println("<td>" + dto.getEntidad().getNombreEvento() + "</td>");
                            out.println("<td>" + dto.getEntidad().getFecha() + "</td>");
                            if (tipoEvento == 1) {
                                out.println("<td>Ponencia</td>");
                            } else if (tipoEvento == 2) {
                                out.println("<td>Taller</td>");
                            } else {
                                out.println("<td>Seminario</td>");
                            }
                            out.println("<td><a href='asistenteBajaEvento?idAsistente=" + idAsistente + "&idEvento=" + dto.getEntidad().getIdEvento() + "' class='btn btn-danger'>Dar de baja</a></td>");
                            out.println("</tr>");
                        }
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(asistenteListaEventos.class.getName()).log(Level.SEVERE, null, ex);
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
