package controlador.miembroComite;

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

@WebServlet(name = "miembroAdminProgramas", urlPatterns = {"/miembroAdminProgramas"})
public class miembroAdminProgramas extends HttpServlet {

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
        
        int idMiembro = Integer.parseInt(request.getParameter("idMiembro"));
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' ></script>");
            out.println("<title>Administrar programas</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'><br /><br />");
            out.println("<header><h1 align='center'>Administrar programas</h1></header>");
            out.println("<div><a href='miembroComiteServlet?idMiembro=" + idMiembro + "' class='btn btn-secondary'>Regresar</a><br /><br />");
            out.println("<div><a href='miembroCrearPrograma?idMiembro=" + idMiembro + "' class='btn btn-success'>Nuevo programa</a><br /><br />");
            out.println("<table class='table table-striped'>");
            out.println("<tr><td>Nombre</td><td>Evento asociado</td><td>Acciones</td></tr>");
            
            ProgramaDAO dao = new ProgramaDAO();
            ProgramaDTO dto = new ProgramaDTO();
            
            try {
                List lista = dao.readAll();
                
                for(int i = 0; i < lista.size(); i++){
                    dto = (ProgramaDTO) lista.get(i);
                    
                    out.println("<tr>");
                    out.println("<td>" + dto.getEntidad().getNombrePrograma() + "</td>");
                    out.println("<td>" + dto.getEntidad().getIdEvento() + "</td>");
                    out.println("<td><a href='miembroVerPrograma?idPrograma=" + dto.getEntidad().getIdPrograma() + "&idMiembro=" + idMiembro + "' class='btn btn-info'>Ver</a>" + " | "
                            + "<a href='miembroModificarPrograma?idPrograma=" + dto.getEntidad().getIdPrograma() + "&idMiembro=" + idMiembro + "' class='btn btn-warning'>Modificar</a>" + " | "
                            + "<a href='miembroEliminarPrograma?idPrograma=" + dto.getEntidad().getIdPrograma() + "&idMiembro=" + idMiembro + "' class='btn btn-danger'>Eliminar</a></td>");
                    out.println("</tr>");
                }
                
                out.println("</table></div>");
            } catch (SQLException ex) {
                Logger.getLogger(miembroAdminProgramas.class.getName()).log(Level.SEVERE, null, ex);
            }
            
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
