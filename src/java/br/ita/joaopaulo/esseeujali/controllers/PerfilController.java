package br.ita.joaopaulo.esseeujali.controllers;

import br.ita.joaopaulo.esseeujali.model.PontuacaoService;
import br.ita.joaopaulo.esseeujali.model.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "PerfilController", urlPatterns = {"/perfil"})
public class PerfilController extends BaseController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if(!isUsuarioLogado(request, response)) {
            return;
        }
        
        Usuario usuario = getUsuarioLogado(request);
        
        PontuacaoService service = new PontuacaoService();
        request.setAttribute("pontuacao", service.consultar(usuario.getCodigo()));
        
        view("perfil", request, response);
    }       
   
}
