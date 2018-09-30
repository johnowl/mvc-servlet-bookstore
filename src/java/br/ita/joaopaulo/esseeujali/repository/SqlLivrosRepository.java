package br.ita.joaopaulo.esseeujali.repository;

import br.ita.joaopaulo.esseeujali.model.Livro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlLivrosRepository extends SqlRepository implements LivrosRepository {

    @Override
    public List<Livro> listar() {
        List<Livro> lista = new ArrayList<>();
        String sql = "SELECT codigo, titulo, autor, foto, estilo, paginas FROM Livros ORDER BY titulo";
        try (Connection c = getConnection();
             PreparedStatement stm = c.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {  
            
            while (rs.next()) {
                lista.add(popularLivro(rs));
            }
                
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    @Override
    public Livro consultarPorCodigo(int codigo) {
        String sql = "SELECT codigo, titulo, autor, foto, estilo, paginas FROM Livros WHERE codigo = ?";
        try (Connection c = getConnection();
             PreparedStatement stm = c.prepareStatement(sql)) {
            
            stm.setInt(1, codigo);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return popularLivro(rs);
                }
                return null;
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void registrarLeitura(Livro livro, int codigoUsuario) {
        String sql = "INSERT INTO Leituras VALUES(?, ?, ?, ?, ?)";
        try (Connection c = getConnection();
             PreparedStatement stm = c.prepareStatement(sql)) {
            
            stm.setInt(1, livro.getCodigo());
            stm.setInt(2, codigoUsuario);
            stm.setDate(3, getDataHoraAtual());
            stm.setString(4, livro.getEstilo());
            stm.setInt(5, livro.getPaginas());
            stm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public boolean livroFoiLido(int codigoLivro, int codigoUsuario) {
        String sql = "SELECT * FROM Leituras WHERE codigoLivro = ? AND codigoUsuario = ?";
        
        try (Connection c = getConnection();
             PreparedStatement stm = c.prepareStatement(sql)) {
            stm.setInt(1, codigoLivro);
            stm.setInt(2, codigoUsuario);
            try (ResultSet rs = stm.executeQuery()) {
               return rs.next();
            }            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Livro popularLivro(ResultSet rs) throws SQLException {
        Livro livro = new Livro();
        livro.setCodigo(rs.getInt("codigo"));
        livro.setTitulo(rs.getString("titulo"));
        livro.setAutor(rs.getString("autor"));
        livro.setFoto(rs.getString("foto"));
        livro.setEstilo(rs.getString("estilo"));
        livro.setPaginas(rs.getInt("paginas"));
        return livro;
    }
}
