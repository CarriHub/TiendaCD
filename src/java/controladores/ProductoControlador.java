/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;

import entidades.Comentario;
import entidades.ControladorEntidades;
import entidades.Producto;
import entidades.Talla;
import entidades.Usuario;
import entidades.UsuarioProductoCarrito;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Carri
 */
@WebServlet(name = "ProductoControlador", urlPatterns = {"/ProductoControlador"})
public class ProductoControlador extends HttpServlet {

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

        String vista = "/producto.jsp";
        ControladorEntidades control = new ControladorEntidades();
        HttpSession sesion = request.getSession();
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");

        String idProductoStr = request.getParameter("idProducto");
        if (idProductoStr == null) {
            response.sendRedirect("CarriDesign");
            return;
        }
        int idProducto = Integer.parseInt(idProductoStr);
        Producto producto = control.getProducto(idProducto);

        if (request.getParameter("carrito") != null) {
            Talla talla = Talla.valueOf(request.getParameter("talla"));
            UsuarioProductoCarrito nuevoProductoCarrito = new UsuarioProductoCarrito(usuario, producto, talla);
            control.crearUsuarioProductoCarrito(nuevoProductoCarrito);
        }

        if (request.getParameter("favorito") != null) {
            List<Producto> favoritos = usuario.getDeseado();
            if (favoritos == null) {
                favoritos = new ArrayList<>();
            }
            boolean encontrado = false;
            int indiceLista = 0;
            for (Producto favorito : favoritos) {
                if (favorito.getIdProducto() == producto.getIdProducto()) {
                    encontrado = true;
                    break;
                }
                indiceLista++;
            }
            if (!encontrado) {
                favoritos.add(producto);
            } else {
                favoritos.remove(indiceLista);
            }
            usuario.setDeseado(favoritos);
            control.editarUsuario(usuario);
        }

        if (request.getParameter("comentario") != null) {
            Producto productoComentario = producto;
            Comentario comentario = new Comentario(request.getParameter("comentario"), usuario, productoComentario);
            control.crearComentario(comentario);
        }
        
        producto = control.getProducto(idProducto);
        request.setAttribute("producto", producto);

        request.getServletContext().getRequestDispatcher(vista).forward(request, response);

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
