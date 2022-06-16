package controlador.miembroComite;

import dao.EventoDAO;
import dto.EventoDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "miembroEventoCreado", urlPatterns = {"/miembroEventoCreado"})
public class miembroEventoCreado extends HttpServlet {

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

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date fecha = null;

        String nombre = request.getParameter("nombre");
        int cupo = Integer.parseInt(request.getParameter("cupo"));
        int tipoEvento = Integer.parseInt(request.getParameter("tipoEvento"));
        int idMiembro = Integer.parseInt(request.getParameter("idMiembro"));

        try {
            Date parsed = sdf.parse(request.getParameter("fecha"));
            fecha = new java.sql.Date(parsed.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(miembroEventoCreado.class.getName()).log(Level.SEVERE, null, ex);
        }

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' ></script>");
            out.println("<title>Evento creado</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            
            EventoDTO dto = new EventoDTO();
            EventoDAO dao = new EventoDAO();
            
            dto.getEntidad().setNombreEvento(nombre);
            dto.getEntidad().setCupo(cupo);
            dto.getEntidad().setFecha(fecha);
            dto.getEntidad().setTipoEvento(tipoEvento);
            dto.getEntidad().setIdOrganizador(idMiembro);
            
            try {
                dao.create(dto);
                out.println("<div class='alert alert-info' role='alert'>");
                out.println("Evento registrado con éxito <a href='miembroComiteServlet?idMiembro=" + idMiembro + "' class='alert-link'>Volver</a>");
                out.println("</div>");
            } catch (SQLException ex) {
                out.println("<div class='alert alert-danger' role='alert'>");
                out.println("No se pudo registrar el evento <a href='miembroComiteServlet?idMiembro=" + idMiembro + "' class='alert-link'>Volver</a>");
                out.println("</div>");
                Logger.getLogger(miembroEventoCreado.class.getName()).log(Level.SEVERE, null, ex);
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
