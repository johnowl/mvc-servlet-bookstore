package br.ita.joaopaulo.esseeujali.controllers;

import br.ita.joaopaulo.esseeujali.model.Usuario;
import br.ita.joaopaulo.esseeujali.model.UsuarioService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends BaseController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        view("login", request, response);

    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        
        UsuarioService service = new UsuarioService();
        Usuario usuario;
        
        try {
            usuario = service.consultarUsuario(email, senha);
            if(usuario == null) {
                erroLogin(request, response);
                return;
            }
        } catch(IllegalArgumentException ex) {            
            erroLogin(request, response);
            return;
        }
                
        setUsuarioLogado(request, usuario);
        response.sendRedirect("livros");
    
    }
    
    private void erroLogin(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setAttribute("erroLogin", true);
        view("login", request, response);
        
    }
   
}
