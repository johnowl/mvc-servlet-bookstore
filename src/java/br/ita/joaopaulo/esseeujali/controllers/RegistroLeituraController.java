package br.ita.joaopaulo.esseeujali.controllers;

import br.ita.joaopaulo.esseeujali.model.LivroService;
import br.ita.joaopaulo.esseeujali.model.PontuacaoService;
import br.ita.joaopaulo.esseeujali.model.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "RegistrarLeituraController", urlPatterns = {"/registrar"})
public class RegistroLeituraController extends BaseController {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if(!isUsuarioLogado(request, response)) {
            return;
        }
        
        int codigoLivro = Integer.parseInt(request.getParameter("codigoLivro"));
        Usuario usuario = getUsuarioLogado(request);
       
        
        LivroService service = new LivroService();
        service.registrarLeitura(codigoLivro, usuario.getCodigo());
        
        PontuacaoService pontuacao = new PontuacaoService();
        pontuacao.atualizarRanking(usuario.getCodigo());
        
        response.sendRedirect("perfil"); 
    }       
   
}
