package controlador.revisor;

import dao.ArticuloDAO;
import dao.AutorDAO;
import dto.ArticuloDTO;
import dto.AutorDTO;
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

@WebServlet(name = "revisorVerArt", urlPatterns = {"/revisorVerArt"})
public class revisorVerArt extends HttpServlet {

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

        int idArticulo = Integer.parseInt(request.getParameter("idArticulo"));
        int idRevisor = Integer.parseInt(request.getParameter("idRevisor"));

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' ></script>");
            out.println("<title>Ver articulo</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'><br /><br />");
            out.println("<div><a href='revisorServlet?idRevisor=" + idRevisor + "' class='btn btn-secondary'>Regresar</a><br /><br />");

            ArticuloDAO dao = new ArticuloDAO();
            ArticuloDTO dto = new ArticuloDTO();
            ArticuloDTO leido = new ArticuloDTO();
            dto.getEntidad().setIdArticulo(idArticulo);

            AutorDAO dao2 = new AutorDAO();
            AutorDTO dto2 = new AutorDTO();
            AutorDTO autor = new AutorDTO();

            try {
                leido = dao.read(dto);

                dto2.getEntidad().setIdAutor(leido.getEntidad().getIdAutor());
                autor = dao2.read(dto2);

                String ruta = leido.getEntidad().getRutaDocumento();
                String nombreArticulo = ruta.substring(ruta.lastIndexOf("\\") + 1, ruta.length());

                out.println("<h2>Nombre del articulo: " + nombreArticulo + "</h2>");
                out.println("<h2>Nombre del autor: " + autor.getEntidad().getNombre() + " " + autor.getEntidad().getApPaterno() + "</h2><br />");
                //out.println("<embed src='Tarea 1_1.pdf' type='application/pdf' width='100%' height='600px'></embed>");
                out.println("<embed src='" + nombreArticulo + "' type='application/pdf' width='100%' height='600px'></embed>");
            } catch (SQLException ex) {
                Logger.getLogger(revisorVerArt.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            out.println("<div class='mb-3' align='center'>");
            out.println("<a href='revisorAceptarArt?idArticulo=" + idArticulo + "&idRevisor=" + idRevisor + "' class='btn btn-success'>Aceptar</a>");
            out.println("<a href='revisorRechazarArt?idArticulo=" + idArticulo + "&idRevisor=" + idRevisor + "' class='btn btn-danger'>Rechazar</a>");
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
