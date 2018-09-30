/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ita.joaopaulo.esseeujali.repository;

import br.ita.joaopaulo.esseeujali.model.Livro;
import java.util.List;


public interface LivrosRepository {

    Livro consultarPorCodigo(int codigo);

    List<Livro> listar();

    boolean livroFoiLido(int codigoLivro, int codigoUsuario);

    void registrarLeitura(Livro livro, int codigoUsuario);
    
}
