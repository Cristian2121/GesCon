package controlador.autor;

// https://stackoverflow.com/questions/2422468/how-can-i-upload-files-to-a-server-using-jsp-servlet
import dao.ArticuloDAO;
import dto.ArticuloDTO;
/*import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;*/
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "autorArtSubido", urlPatterns = {"/autorArtSubido"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 100)             // 100MB
public class autorArtSubido extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            int idAutor = Integer.parseInt(request.getParameter("idAutor"));
            int programa = Integer.parseInt(request.getParameter("programa"));
            Part filePart = request.getPart("articulo");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            InputStream fileContent = filePart.getInputStream();

            File archivo;
            String filePath = request.getRealPath("/");

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' ></script>");
            out.println("<title>Subir articulo</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'><br /><br />");

            archivo = new File(filePath + fileName);

            if (archivo.exists()) {
                out.println("<div class='alert alert-info' role='alert'>");
                out.println("Ya hay un articulo con ese nombre, por lo tanto no se registro el articulo. <a href='autorServlet?idAutor=" + idAutor + "' class='alert-link'>Volver</a>");
                out.println("</div>");
            } else {
                ArticuloDAO dao = new ArticuloDAO();
                ArticuloDTO dto = new ArticuloDTO();
                dto.getEntidad().setRutaDocumento(archivo.getAbsolutePath());
                dto.getEntidad().setEvaluacion(3);
                dto.getEntidad().setIdAutor(idAutor);
                dto.getEntidad().setIdRevisor(1);
                dto.getEntidad().setIdJefeComite(1);
                dto.getEntidad().setIdPrograma(programa);

                try {
                    dao.create(dto);

                    FileOutputStream outputStream = new FileOutputStream(archivo, false);
                    int read;
                    byte[] bytes = new byte[1024];
                    while ((read = fileContent.read(bytes)) != -1) {
                        outputStream.write(bytes, 0, read);
                    }

                    out.println("<div class='alert alert-info' role='alert'>");
                    out.println("Articulo subido. <a href='autorServlet?idAutor=" + idAutor + "' class='alert-link'>Volver</a>");
                    out.println("</div>");
                } catch (SQLException ex) {
                    out.println("<div class='alert alert-info' role='alert'>");
                    out.println("El articulo no pudo subirse. <a href='autorServlet?idAutor=" + idAutor + "' class='alert-link'>Volver</a>");
                    out.println("</div>");
                    Logger.getLogger(autorArtSubido.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }// try printwriter
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
