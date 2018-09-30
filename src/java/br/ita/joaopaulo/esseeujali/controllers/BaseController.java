package br.ita.joaopaulo.esseeujali.controllers;

import br.ita.joaopaulo.esseeujali.model.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseController extends HttpServlet {

    private static final String SESSAO_USUARIO = "login";
    
    protected void view(String viewName, HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {                
        
        request.getRequestDispatcher("/WEB-INF/views/" + viewName + ".jsp")
                .forward(request, response);
        
    }
    
    protected Usuario getUsuarioLogado(HttpServletRequest request)  {
        return (Usuario)request.getSession().getAttribute(SESSAO_USUARIO);
    }
    
    protected void setUsuarioLogado(HttpServletRequest request, Usuario usuario)  {
        request.getSession().setAttribute(SESSAO_USUARIO, usuario);
    }  
    
    protected boolean isUsuarioLogado(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Usuario usuario = this.getUsuarioLogado(request);
        
        if(usuario == null) {
            response.sendRedirect("login?expired=true"); 
            return false;
        }
        
        return true;
    }
    
}
