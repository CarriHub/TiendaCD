/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;

import entidades.ControladorEntidades;
import entidades.EstadoPedido;
import entidades.Pedido;
import entidades.Producto;
import entidades.Usuario;
import entidades.UsuarioProductoCarrito;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
@WebServlet(name = "Carrito", urlPatterns = {"/Carrito"})
public class Carrito extends HttpServlet {

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
        String vista = "/users/carrito.jsp";

        ControladorEntidades control = new ControladorEntidades();
        HttpSession sesion = request.getSession();
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");

        //eliminar producto carrito
        String productoEliminarStr = request.getParameter("idEliminar");
        if (productoEliminarStr != null) {
            List<UsuarioProductoCarrito> carrito = control.getUsuarioProductoCarritos();
            UsuarioProductoCarrito eliminar = null;
            int indice = 0;
            for (UsuarioProductoCarrito linea : carrito) {
                if (linea.getUsuario().getIdUsuario() == usuario.getIdUsuario()) {
                    if (linea.getProducto().getIdProducto() == Integer.parseInt(productoEliminarStr)) {
                        eliminar = linea;
                        break;
                    }
                }
                indice++;
            }
            if (eliminar != null) {
                control.eliminarUsuarioProductoCarrito(eliminar.getId());
                usuario.getCarrito().remove(control.getProducto(Integer.parseInt(productoEliminarStr)));
            }

        }

        List<UsuarioProductoCarrito> carritoUsuario = new ArrayList<>();
        for (UsuarioProductoCarrito linea : control.getUsuarioProductoCarritos()) {
            if (linea.getUsuario().getIdUsuario() == usuario.getIdUsuario()) {
                carritoUsuario.add(linea);
            }
        }
        //Añadir pedido
        if (request.getParameter("confirmar") != null) {
            if (!carritoUsuario.isEmpty()) {
                Date fechapedido = new Date();
                Pedido pedido = new Pedido(fechapedido, EstadoPedido.PROCESANDO, usuario, carritoUsuario);
                control.crearPedido(pedido);
                List<Pedido> pedidos = usuario.getPedidos();
                pedidos.add(pedido);
                usuario.setPedidos(pedidos);
                control.editarUsuario(usuario);
                for (UsuarioProductoCarrito usuarioProductoCarrito : carritoUsuario) {
                    control.eliminarUsuarioProductoCarrito(usuarioProductoCarrito.getId());
                }
                carritoUsuario.clear();
            }
        }

        double dinero = 0.00;
        for (UsuarioProductoCarrito sacarProducto : carritoUsuario) {
            dinero += sacarProducto.getProducto().getPrecio();
        }
        String formatoString = String.format("%.2f", dinero);
        request.setAttribute("carritoUsuario", carritoUsuario);
        request.setAttribute("dinero", formatoString);
        request.setAttribute("pedidosUsuario", usuario.getPedidos());

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
