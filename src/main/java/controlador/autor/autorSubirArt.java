package controlador.autor;

import dao.ProgramaDAO;
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

@WebServlet(name = "autorSubirArt", urlPatterns = {"/autorSubirArt"})
public class autorSubirArt extends HttpServlet {

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
            out.println("<title>Subir articulo</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'><br /><br />");
            out.println("<header><h1 align='center'>Selecciona un archivo de tu ordenador</h1></header><br /><br />");
            
            out.println("<div class='mb-3'>");
            out.println("<form action='autorArtSubido' method='post' enctype='multipart/form-data'>");
            out.println("<div class='mb-3'><input type='file' class='form-control' name='articulo' required size='45'></div>");
            out.println("<div class='mb-3'><div class='input-group mb-3'>");
            out.println("<label class='input-group-text' for='grupoTipo'>¿Quién eres?</label>");
            out.println("<select class='form-select' id='grupoTipo' name='programa'>");
            
            ProgramaDAO dao = new ProgramaDAO();
            ProgramaDTO dto = new ProgramaDTO();
            
            try {
                List lista = dao.readAll();
                
                for(int i = 0; i < lista.size(); i++){
                    dto = (ProgramaDTO) lista.get(i);
                    
                    out.println("<option value='" + dto.getEntidad().getIdPrograma() + "'>" + dto.getEntidad().getNombrePrograma() + "</option>");
                }
            } catch (SQLException ex) {
                Logger.getLogger(autorSubirArt.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            out.println("</select>");
            out.println("</div></div>");
            out.println("<input type='hidden' value='" + idAutor + "' name='idAutor'>");
            out.println("<div class='mb-3' align='center'>");
            out.println("<input type='submit' value='Subir' class='btn btn-success'>");
            out.println("<a href='autorServlet?idAutor=" + idAutor + "' class='btn btn-secondary'>Regresar</a>");
            out.println("</div>");
            out.println("</form>");
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
