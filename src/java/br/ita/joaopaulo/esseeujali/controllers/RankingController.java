package br.ita.joaopaulo.esseeujali.controllers;

import br.ita.joaopaulo.esseeujali.model.PontuacaoService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "RankingController", urlPatterns = {"/ranking"})
public class RankingController extends BaseController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if(!isUsuarioLogado(request, response)) {
            return;
        }
        
        PontuacaoService service = new PontuacaoService();
        request.setAttribute("ranking", service.consultarRanking());
        
        view("ranking", request, response);
    }       
   
}
