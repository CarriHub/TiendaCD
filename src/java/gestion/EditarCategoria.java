/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package gestion;

import entidades.Categoria;
import entidades.ControladorEntidades;
import static gestion.GestionCategorias.TAM_BUFFER;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Carri
 */
@WebServlet(name = "EditarCategoria", urlPatterns = {"/admin/EditarCategoria"})
@MultipartConfig
public class EditarCategoria extends HttpServlet {

    public static final int TAM_BUFFER = 4 * 1024;

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
        String vista = "/admin/gestionCategorias.jsp";
        ControladorEntidades control = new ControladorEntidades();
        //Mostrar categoria
        String idCategoriaStr = request.getParameter("idEditar");
        if (idCategoriaStr != null) {
            int idCategoria = Integer.parseInt(idCategoriaStr);
            request.setAttribute("categoriaEditar", control.getCategoria(idCategoria));
            request.getServletContext().getRequestDispatcher("/admin/editarCategoria.jsp").forward(request, response);
        }
        //Editar categoria
        String idCategoriaEditarStr = request.getParameter("idCatEdit");
        if (idCategoriaEditarStr != null) {
            int idCatEdit = Integer.parseInt(idCategoriaEditarStr);
            String nuevoNombre = request.getParameter("nombreEditar");

            Part parte = request.getPart("imagenEditar");
            String nombreFichero = parte.getSubmittedFileName();
            InputStream entrada = parte.getInputStream();
            String ruta = getServletContext().getRealPath("/") + nombreFichero;
            FileOutputStream salida = new FileOutputStream(ruta);
            byte[] buffer = new byte[TAM_BUFFER];
            while (entrada.available() > 0) {
                int tam = entrada.read(buffer);
                salida.write(buffer, 0, tam);
            }
            salida.close();
            entrada.close();

            Categoria catEdit = control.getCategoria(idCatEdit);
            catEdit.setNombre(nuevoNombre);
            catEdit.setImagen(nombreFichero);
            control.editarCategoria(catEdit);
            
            request.setAttribute("categorias", control.getCategorias());

            request.getServletContext().getRequestDispatcher(vista).forward(request, response);
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
