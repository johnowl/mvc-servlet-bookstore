package br.ita.joaopaulo.esseeujali.model;

import br.ita.joaopaulo.esseeujali.repository.SqlUsuarioRepository;

public class UsuarioService {

    private final SqlUsuarioRepository repository;
    
    public UsuarioService() {
        this.repository = new SqlUsuarioRepository();
    }
    
    public Usuario consultarUsuario(String email, String senha) {
        
        if(email == null || "".equals(email)) {
            throw new IllegalArgumentException("O campo e-mail é obrigatório.");
        }
        
        if(senha == null || "".equals(email)) {
            throw new IllegalArgumentException("O campo senha é obrigatório.");
        }
        
        return this.repository.consultarUsuario(email, senha);
    }
    
}
