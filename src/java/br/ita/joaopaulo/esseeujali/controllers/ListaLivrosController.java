package br.ita.joaopaulo.esseeujali.controllers;

import br.ita.joaopaulo.esseeujali.model.LivroService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ListaLivrosController", urlPatterns = {"/livros"})
public class ListaLivrosController extends BaseController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if(!isUsuarioLogado(request, response)) {
            return;
        }
        
        LivroService service = new LivroService();
        request.setAttribute("livros", service.listar());
        
        view("livros", request, response);
    }       
   
}
