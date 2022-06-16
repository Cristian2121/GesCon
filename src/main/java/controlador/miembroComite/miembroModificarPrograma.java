package controlador.miembroComite;

import dao.EventoDAO;
import dao.ProgramaDAO;
import dto.EventoDTO;
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

@WebServlet(name = "miembroModificarPrograma", urlPatterns = {"/miembroModificarPrograma"})
public class miembroModificarPrograma extends HttpServlet {

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

        int idPrograma = Integer.parseInt(request.getParameter("idPrograma"));
        int idMiembro = Integer.parseInt(request.getParameter("idMiembro"));

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' ></script>");
            out.println("<title>Modificar programa</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<header align='center'><h1>Modificar programa</h1></header><br/><br/>");
            out.println("<div class='mb-3'>");

            ProgramaDAO dao = new ProgramaDAO();
            ProgramaDTO dto = new ProgramaDTO();
            ProgramaDTO leido = new ProgramaDTO();
            dto.getEntidad().setIdPrograma(idPrograma);

            EventoDAO dao2 = new EventoDAO();
            EventoDTO dto2 = new EventoDTO();

            try {
                leido = dao.read(dto);
                List lista = dao2.readAll();

                out.println("<form action='miembroProgramaModificado' method='post'>");
                out.println("<div class='mb-3'><input type='text' class='form-control' name='nombre' placeholder='Nombre programa' value='" + leido.getEntidad().getNombrePrograma() + "' pattern='[a-zA-z ]+' required maxlength='60'></div>");
                out.println("<div class='mb-3'><input type='text' class='form-control' name='moderador' placeholder='Nombre moderador' value='" + leido.getEntidad().getModerador() + "' required maxlength='60'></div>");
                out.println("<div class='mb-3'><div class='input-group mb-3'>");
                out.println("<label class='input-group-text' for='grupoTipo'>Evento asociado</label>");
                out.println("<select class='form-select' id='grupoTipo' name='idEvento' required>");

                for (int i = 0; i < lista.size(); i++) {
                    dto2 = (EventoDTO) lista.get(i);
                    
                    if(dto2.getEntidad().getIdEvento() == leido.getEntidad().getIdEvento()){
                        out.println("<option value='" + dto2.getEntidad().getIdEvento() + "' selected>" + dto2.getEntidad().getNombreEvento() + "</option>");
                    } else{
                        out.println("<option value='" + dto2.getEntidad().getIdEvento() + "'>" + dto2.getEntidad().getNombreEvento() + "</option>");
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(miembroModificarPrograma.class.getName()).log(Level.SEVERE, null, ex);
            }

            out.println("</select>");
            out.println("</div></div>");
            out.println("<input type='hidden' value='" + idMiembro + "' name='idMiembro'>");
            out.println("<input type='hidden' value='" + idPrograma + "' name='idPrograma'>");
            out.println("<div class='mb-3' align='center'>");
            out.println("<input type='submit' value='Modificar' class='btn btn-success'>");
            out.println("<a href='miembroAdminProgramas?idMiembro=" + idMiembro + "' class='btn btn-secondary'>Regresar</a>");
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
