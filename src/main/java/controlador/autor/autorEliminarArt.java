package controlador.autor;

import dao.ArticuloDAO;
import dto.ArticuloDTO;
import java.io.File;
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

@WebServlet(name = "autorEliminarArt", urlPatterns = {"/autorEliminarArt"})
public class autorEliminarArt extends HttpServlet {

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

        int idAutor = Integer.parseInt(request.getParameter("idAutor"));
        int idArticulo = Integer.parseInt(request.getParameter("idArticulo"));

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' ></script>");
            out.println("<title>Eliminar articulo</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");

            ArticuloDAO dao = new ArticuloDAO();
            ArticuloDTO dto = new ArticuloDTO();
            ArticuloDTO leido = new ArticuloDTO();
            dto.getEntidad().setIdArticulo(idArticulo);

            try {
                leido = dao.read(dto);  // Conservamos los datos en una instancia
                dao.delete(dto);        // eliminamos el registro
                out.println("<div class='alert alert-info' role='alert'>");
                out.println("El articulo se elimino. <a href='autorServlet?idAutor=" + idAutor + "' class='alert-link'>Volver</a>");
                out.println("</div>");
                
                File archivo = new File(leido.getEntidad().getRutaDocumento());

                if (archivo.exists()) {
                    if (archivo.delete()) {
                        out.println("<div class='alert alert-info' role='alert'>");
                        out.println("El archivo fue eliminado del servidor.");
                        out.println("</div>");
                    } else {
                        out.println("<div class='alert alert-danger' role='alert'>");
                        out.println("El archivo no pudo ser borrado del servidor.");
                        out.println("</div>");
                    }
                } else {
                    out.println("<div class='alert alert-danger' role='alert'>");
                    out.println("El archivo no existe, por lo que no pudo ser borrado.");
                    out.println("</div>");
                }
            } catch (SQLException ex) {
                Logger.getLogger(autorEliminarArt.class.getName()).log(Level.SEVERE, null, ex);
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
