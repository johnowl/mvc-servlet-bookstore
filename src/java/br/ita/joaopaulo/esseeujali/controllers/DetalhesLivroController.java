package br.ita.joaopaulo.esseeujali.controllers;

import br.ita.joaopaulo.esseeujali.model.LivroService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "DetalhesLivroController", urlPatterns = {"/livro"})
public class DetalhesLivroController extends BaseController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if(!isUsuarioLogado(request, response)) {
            return;
        }
        
        int codigoLivro;
        
        try {
            codigoLivro = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            view("livro", request, response);
            return;
        }        
        
        LivroService service = new LivroService();
        request.setAttribute("livro", service.consultarPorCodigo(codigoLivro));
        
        view("livro", request, response);
    }       
   
}
