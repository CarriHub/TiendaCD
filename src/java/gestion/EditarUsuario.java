/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package gestion;

import Utilidades.Encriptador;
import entidades.ControladorEntidades;
import entidades.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carri
 */
@WebServlet(name = "EditarUsuario", urlPatterns = {"/EditarUsuario"})
public class EditarUsuario extends HttpServlet {

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
        String vista = "/editarUsuario.jsp";
        
        ControladorEntidades control = new ControladorEntidades();
        Usuario usuarioEditar = control.getUsuario(parseInt(request.getParameter("idEditar")));
        request.setAttribute("usuarioEditar", usuarioEditar);
        
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String contrasena = Encriptador.encrypt(request.getParameter("contrasena"));
        String direccion = request.getParameter("direccion");
        String email = request.getParameter("email");
        
        if(nombre != null){
            usuarioEditar.setNombre(nombre);
            usuarioEditar.setApellido(apellido);
            usuarioEditar.setContraseña(contrasena);
            usuarioEditar.setDireccion(direccion);
            usuarioEditar.setEmail(email);
            control.editarUsuario(usuarioEditar);
            
            request.getServletContext().getRequestDispatcher("GestionUsuarios").forward(request, response);
        }
        
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
