package controlador.autor;

import dao.ArticuloDAO;
import dto.ArticuloDTO;
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

@WebServlet(name = "autorServlet", urlPatterns = {"/autorServlet"})
public class autorServlet extends HttpServlet {

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
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' ></script>");
            out.println("<title>Autor</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<header><h1 align='center'>Administrar artículos</h1></header>");
            out.println("<div><a href='index.html' class='btn btn-secondary'>Regresar</a><br /><br />");
            out.println("<div><a href='autorSubirArt?idAutor=" + idAutor + "' class='btn btn-primary'>Subir articulo</a></div><br /><br />");
            out.println("<table class='table table-striped'>");
            out.println("<tr><td>Nombre</td><td>Estado</td><td>Acciones</td></tr>");
            
            ArticuloDAO dao = new ArticuloDAO();
            ArticuloDTO dto = new ArticuloDTO();
            
            try {
                List lista = dao.readAll();
                
                for(int i = 0; i < lista.size(); i++){
                    dto = (ArticuloDTO) lista.get(i);
                    
                    String ruta = dto.getEntidad().getRutaDocumento();
                    String nombreArticulo = ruta.substring(ruta.lastIndexOf("\\") + 1, ruta.length() - 4);
                    int evaluacion = dto.getEntidad().getEvaluacion();
                    
                    if(dto.getEntidad().getIdAutor() == idAutor && evaluacion != 4){
                        out.println("<tr>");
                        out.println("<td>" + nombreArticulo + "</td>");
                        if(evaluacion == 1){
                            out.println("<td>Aceptado</td>");
                            out.println("<td><a href='autorEnviarVF?idArticulo=" + dto.getEntidad().getIdArticulo() + "&idAutor=" + idAutor + "' class='btn btn-success'>Enviar VF</a>" + " | "
                                + "<a href='autorEliminarArt?idArticulo=" + dto.getEntidad().getIdArticulo() + "&idAutor=" + idAutor + "' class='btn btn-danger'>Eliminar</a></td>");
                        } else if(evaluacion == 2){
                            out.println("<td>Rechazado</td>");
                            out.println("<td><a href='autorEliminarArt?idArticulo=" + dto.getEntidad().getIdArticulo() + "&idAutor=" + idAutor + "' class='btn btn-danger'>Eliminar</a></td>");
                        } else if(evaluacion == 3){
                            out.println("<td>Pendiente</td>");
                            out.println("<td><a href='autorEliminarArt?idArticulo=" + dto.getEntidad().getIdArticulo() + "&idAutor=" + idAutor + "' class='btn btn-danger'>Eliminar</a></td>");
                        }   
                        out.println("</tr>");
                    }
                }
                
                out.println("</table></div>");
            } catch (SQLException ex) {
                Logger.getLogger(autorServlet.class.getName()).log(Level.SEVERE, null, ex);
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
