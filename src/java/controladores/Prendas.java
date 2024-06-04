/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;

import entidades.Categoria;
import entidades.ControladorEntidades;
import entidades.Producto;
import entidades.Sexo;
import entidades.Usuario;
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
@WebServlet(name = "Prendas", urlPatterns = {"/Prendas"})
public class Prendas extends HttpServlet {

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
        String vista = "/prendas.jsp";
        HttpSession sesion = request.getSession();
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");
        request.setAttribute("mostrarFiltro", true);
        ControladorEntidades control = new ControladorEntidades();
        List<Producto> productos = control.getProductos();
        //Favoritos
        if (request.getParameter("favoritos") != null) {
            productos = usuario.getDeseado();
            request.setAttribute("mostrarFiltro", false);
        }
        //Filtro
        String filtro = request.getParameter("filtro");
        if (filtro != null) {
            List<Producto> productosFiltrados = new ArrayList<>();
            if (filtro.equals("novedades")) {
                int contador = 0;
                for (int i = productos.size() - 1; i >= 0 && contador != 12; i--) {
                    productosFiltrados.add(productos.get(i));
                    contador++;
                }
            } else {
                boolean esSexo = false;
                Sexo sexoFiltro = null;
                Categoria categoriaFiltro = null;
                for (Sexo sexo : Sexo.values()) {
                    if (sexo.name().equalsIgnoreCase(filtro)) {
                        esSexo = true;
                        sexoFiltro = Sexo.valueOf(filtro);
                        break;
                    }
                }
                if (esSexo) {
                    for (Producto producto : productos) {
                        if (producto.getSexo() == sexoFiltro) {
                            productosFiltrados.add(producto);
                        }
                    }
                } else {
                    categoriaFiltro = control.getCategoria(Integer.parseInt(filtro));
                    for (Producto producto : productos) {
                        if (producto.getCategoria().getIdCategoria() == categoriaFiltro.getIdCategoria()) {
                            productosFiltrados.add(producto);
                        }
                    }
                }
            }
            productos = productosFiltrados;
        }

        request.setAttribute("productos", productos);
        request.setAttribute("categorias", control.getCategorias());
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
