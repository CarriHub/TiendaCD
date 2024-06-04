/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package gestion;

import entidades.Categoria;
import entidades.ControladorEntidades;
import entidades.Producto;
import entidades.Sexo;
import entidades.Talla;
import static gestion.EditarCategoria.TAM_BUFFER;
import static gestion.GestionProductos.TAM_BUFFER;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "EditarProducto", urlPatterns = {"/admin/EditarProducto"})
@MultipartConfig
public class EditarProducto extends HttpServlet {

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
        String vista = "/admin/gestionProductos.jsp";
        ControladorEntidades control = new ControladorEntidades();
        //Mostrar producto
        String idProductoStr = request.getParameter("idEditar");
        if (idProductoStr != null) {
            int idProducto = Integer.parseInt(idProductoStr);
            request.setAttribute("productoEditar", control.getProducto(idProducto));
            List<Categoria> categorias = control.getCategorias();
            request.setAttribute("categorias", categorias);
            request.getServletContext().getRequestDispatcher("/admin/editarProducto.jsp").forward(request, response);
        }
        //Editar producto
        String idProductoEditarStr = request.getParameter("idProEdit");
        if (idProductoEditarStr != null) {
            int idProEdit = Integer.parseInt(idProductoEditarStr);
            String nombrePro = request.getParameter("nombrePro");

            Part parte = request.getPart("imagenPro");
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

            Categoria categoria = control.getCategoria(Integer.parseInt(request.getParameter("catProId")));

            Sexo sexo = Sexo.valueOf(request.getParameter("sexoPro"));
            String descrip = request.getParameter("descripPro");
            float precio = Float.parseFloat(request.getParameter("precioPro"));

            ArrayList<Talla> tallas = new ArrayList<>();
            String[] tallaStrings = request.getParameterValues("tallaPro[]");
            for (String tallaString : tallaStrings) {
                try {
                    Talla talla = Talla.valueOf(tallaString);
                    tallas.add(talla);
                } catch (IllegalArgumentException e) {
                    System.err.println("Invalid talla value: " + tallaString);
                }
            }

            Producto productoEditar = control.getProducto(idProEdit);
            productoEditar.setCategoria(categoria);
            productoEditar.setDescripcion(descrip);
            productoEditar.setImagen(nombreFichero);
            productoEditar.setNombre(nombrePro);
            productoEditar.setPrecio(precio);
            productoEditar.setTalla(tallas);
            productoEditar.setSexo(sexo);
            control.editarProducto(productoEditar);
            List<Producto> productos = control.getProductos();
            request.setAttribute("productos", productos);
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
