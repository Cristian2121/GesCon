package controlador.asistente;

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

@WebServlet(name = "asistenteServlet", urlPatterns = {"/asistenteServlet"})
public class asistenteServlet extends HttpServlet {

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
            out.println("<title>Asistente</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<header><h1 align='center'>Lista de eventos</h1></header>");
            out.println("<div><a href='index.html' class='btn btn-secondary'>Regresar</a><br /><br />");
            out.println("<div><a href='asistenteListaEventos?idAsistente=" + idAsistente + "' class='btn btn-info'>Eventos inscritos</a></div><br /><br />");
            out.println("<table class='table table-hover'>");

            EventoDAO dao = new EventoDAO();
            EventoDTO dto = new EventoDTO();

            try {
                List lista = dao.readAll();

                for (int i = 0; i < lista.size(); i++) {
                    out.println("<tr>");
                    dto = (EventoDTO) lista.get(i);

                    if (dto != null) {
                        out.println("<td>");
                        out.println("<div align='center'>");
                        out.println("<img src='https://live.staticflickr.com/65535/48982597597_ab7c2e6691_b.jpg' width='200' height='200'><br /><br />");
                        out.println("</div>");
                        out.println("</td>");
                        out.println("<td>");
                        out.println("<div align='center'>");
                        out.println("<h1>Evento: " + dto.getEntidad().getNombreEvento() + "</h1>");
                        out.println("<h4>Fecha: " + dto.getEntidad().getFecha()+ "</h4>");
                        out.println("<h4>Cupo disponible: " + dto.getEntidad().getCupo() + "</h4><br />");
                        
                        if(dto.getEntidad().getCupo() == 0){
                            out.println("<div class='alert alert.danger' role='alert'>");
                            out.println("Ya no hay cupo disponible.");
                            out.println("</div>");
                        } else{
                            out.println("<a href='asistenteVerEvento?idAsistente=" + idAsistente + "&idEvento=" + dto.getEntidad().getIdEvento() + "' class='btn btn-success'>Más información</a>");
                        }
                        out.println("</div>");
                        out.println("</td>");
                    }
                    out.println("</tr>");
                }

            } catch (SQLException ex) {
                Logger.getLogger(asistenteServlet.class.getName()).log(Level.SEVERE, null, ex);
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
