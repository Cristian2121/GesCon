package controlador.jefeComite;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "jefeComiteCrearMiembro", urlPatterns = {"/jefeComiteCrearMiembro"})
public class jefeComiteCrearMiembro extends HttpServlet {

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
        
        int idJefeComite = Integer.parseInt(request.getParameter("idJefeComite"));
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' ></script>");
            out.println("<title>Crear miembro</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'><br/><br/>");
            out.println("<header align='center'><h1>Crear miembro</h1></header><br/><br/>");
            out.println("<div class='mb-3'>");
            out.println("<form action='jefeComiteMiembroCreado' method='post'>");
            out.println("<div class='mb-3'><input type='text' class='form-control' name='nombre' placeholder='Nombre' pattern='[a-zA-z ]+' required maxlength='60'></div>");
            out.println("<div class='mb-3'><input type='text' class='form-control' name='apPaterno' placeholder='Apellido paterno' required pattern='[a-zA-Z ]+' maxlength='60'></div>");
            out.println("<div class='mb-3'><input type='text' class='form-control' name='apMaterno' placeholder='Apellido materno' required pattern='[a-zA-Z ]+' maxlength='60'></div>");
            out.println("<div class='mb-3'><input type='text' class='form-control' name='email' placeholder='Email' required pattern='[a-zA-Z0-9_@.]+' maxlength='60'></div>");
            out.println("<div class='mb-3'><input type='text' class='form-control' name='nombreUsuario' placeholder='Nombre de usuario' required pattern='[a-zA-Z0-9_]+' maxlength='60'></div>");
            out.println("<div class='mb-3'><input type='text' class='form-control' name='contrasenia' placeholder='Contraseña' required pattern='[a-zA-Z0-9]+' maxlength='60'></div>");
            out.println("<input type='hidden' value='" + idJefeComite + "' name='idJefeComite'>");
            out.println("<div class='mb-3' align='center'>");
            out.println("<input type='submit' value='Crear' class='btn btn-success'>");
            out.println("<a href='jefeComiteAdminMiembro?idJefeComite=" + idJefeComite + "' class='btn btn-secondary'>Regresar</a>");
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
