package controlador.miembroComite;

import dao.EventoDAO;
import dto.EventoDTO;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

@WebServlet(name = "miembroCreaListaAsistentes", urlPatterns = {"/miembroCreaListaAsistentes"})
public class miembroCreaListaAsistentes extends HttpServlet {

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
        
        /*try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Lista asistentes</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" + idEvento + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }*/
        EventoDAO dao = new EventoDAO();
        EventoDTO dto = new EventoDTO();
        EventoDTO leido = new EventoDTO();
        dto.getEntidad().setIdEvento(idEvento);
        
        Map<String, Object> map = new HashMap<String, Object>();
        ServletOutputStream sos = null;
        
        try {
            File reporte;
            byte[] b;
            leido = dao.read(dto);
            map.put("idEvento", idEvento);
            map.put("nombreEvento", leido.getEntidad().getNombreEvento());
            map.put("fecha", leido.getEntidad().getFecha());
            
            sos = response.getOutputStream();
            reporte = new File(getServletConfig().getServletContext().getRealPath("/reportes/ListaAsistentes.jasper"));
            b = JasperRunManager.runReportToPdf(reporte.getPath(), map, dao.obtenerConexion());
            
            response.setContentType("application/pdf");
            response.setContentLength(b.length);
            sos.write(b, 0, b.length);
            sos.flush();
            sos.close();
        } catch (SQLException | JRException ex) {
            Logger.getLogger(miembroCreaListaAsistentes.class.getName()).log(Level.SEVERE, null, ex);
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
