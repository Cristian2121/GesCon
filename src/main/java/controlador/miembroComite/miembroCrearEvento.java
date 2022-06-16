package controlador.miembroComite;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "miembroCrearEvento", urlPatterns = {"/miembroCrearEvento"})
public class miembroCrearEvento extends HttpServlet {

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
            out.println("<title>Crear evento</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<header align='center'><h1>Crear evento</h1></header><br/><br/>");
            out.println("<div class='mb-3'>");
            out.println("<form action='miembroEventoCreado' method='post'>");
            out.println("<div class='mb-3'><input type='text' class='form-control' name='nombre' placeholder='Nombre evento' pattern='[a-zA-z ]+' required maxlength='60'></div>");
            out.println("<div class='mb-3'><label>Cupo limite</label><input type='number' class='form-control' name='cupo' placeholder='Cupo' required min='15' max='35'></div>");
            out.println("<div class='mb-3'><input type='date' class='form-control' name='fecha' required></div>");
            out.println("<div class='mb-3'><div class='input-group mb-3'>");
            out.println("<label class='input-group-text' for='grupoTipo'>Tipo de evento</label>");
            out.println("<select class='form-select' id='grupoTipo' name='tipoEvento' required>");
            out.println("<option value='1'>Ponencia</option>");
            out.println("<option value='2'>Talleres</option>");
            out.println("<option value='3'>Seminarios</option>");
            out.println("</select>");
            out.println("</div></div>");
            out.println("<input type='hidden' value='" + idMiembro + "' name='idMiembro'>");
            out.println("<div class='mb-3' align='center'>");
            out.println("<input type='submit' value='Crear' class='btn btn-success'>");
            out.println("<a href='miembroComiteServlet?idMiembro=" + idMiembro + "' class='btn btn-secondary'>Regresar</a>");
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
