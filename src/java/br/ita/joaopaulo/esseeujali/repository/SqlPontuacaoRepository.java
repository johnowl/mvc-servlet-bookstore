package br.ita.joaopaulo.esseeujali.repository;

import br.ita.joaopaulo.esseeujali.model.Pontuacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlPontuacaoRepository extends SqlRepository implements PontuacaoRepository {

    @Override
    public List<Pontuacao> consultarRanking() {
        List<Pontuacao> lista = new ArrayList<>();
        final String sql = "SELECT u.nome, u.pontuacao, string_agg(t.estilo, ',') " +
            "FROM Usuarios u LEFT JOIN Trofeus t ON t.codigoUsuario = u.codigo " +
            "GROUP BY u.nome, u.pontuacao ORDER BY u.pontuacao DESC LIMIT 10";
        
        try (Connection c = getConnection();
             PreparedStatement stm = c.prepareStatement(sql)) {            
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Pontuacao p = new Pontuacao();
                    p.setNome(rs.getString(1));
                    p.setPontos(rs.getInt(2));
                    p.setTrofeus(tratarTrofeus(rs.getString(3)));
                    lista.add(p);
                }
            }            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
    
    @Override
    public Map<String, Integer> consultarEstilosLidos(int codigoUsuario) {
        Map<String, Integer> lista = new HashMap<>();
        final String sql = "SELECT estilo, COUNT(estilo) FROM Leituras WHERE " + 
                "codigoUsuario = ? GROUP BY estilo";
        try (Connection c = getConnection();
             PreparedStatement stm = c.prepareStatement(sql)) {            
            stm.setInt(1, codigoUsuario);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    lista.put(rs.getString(1), rs.getInt(2));
                }
            }            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
    
    @Override
    public List<Integer> consultarPaginasLidas(int codigoUsuario) {
        List<Integer> lista = new ArrayList<>();
        final String sql = "SELECT paginas FROM Leituras WHERE codigoUsuario = ?";
        try (Connection c = getConnection();
             PreparedStatement stm = c.prepareStatement(sql)) {            
            stm.setInt(1, codigoUsuario);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    lista.add(rs.getInt(1));
                }
            }            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
    
    @Override
    public void atualizarPontuacao(int codigoUsuario, int pontuacao) {
        final String sql = "UPDATE Usuarios SET pontuacao = ? WHERE codigo = ?";
        try (Connection c = getConnection();
             PreparedStatement stm = c.prepareStatement(sql)) {            
            stm.setInt(1, pontuacao);
            stm.setInt(2, codigoUsuario);
            stm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    private void inserirTrofeu(int codigoUsuario, String trofeu) {
        final String sql = "INSERT INTO Trofeus(codigoUsuario, estilo) VALUES(?, ?)";
        try (Connection c = getConnection();
             PreparedStatement stm = c.prepareStatement(sql)) {            
            stm.setInt(1, codigoUsuario);
            stm.setString(2, trofeu);
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void apagarTrofeus(int codigoUsuario) {
        final String sql = "DELETE FROM Trofeus WHERE codigoUsuario = ?";
        try (Connection c = getConnection();
             PreparedStatement stm = c.prepareStatement(sql)) {            
            stm.setInt(1, codigoUsuario);
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void registrarTrofeus(int codigoUsuario, List<String> trofeus) {
        for(String trofeu : trofeus) {
            inserirTrofeu(codigoUsuario, trofeu);
        }
    }

    @Override
    public Pontuacao consultar(int codigoUsuario) {
        
        final String sql = "SELECT u.nome, u.pontuacao, string_agg(t.estilo, ',') " +
            "FROM Usuarios u LEFT JOIN Trofeus t ON t.codigoUsuario = u.codigo " +
            "WHERE u.codigo = ? GROUP BY u.nome, u.pontuacao";
        
        try (Connection c = getConnection();
             PreparedStatement stm = c.prepareStatement(sql)) {  
            stm.setInt(1, codigoUsuario);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    Pontuacao p = new Pontuacao();
                    p.setNome(rs.getString(1));
                    p.setPontos(rs.getInt(2));                    
                    p.setTrofeus(tratarTrofeus(rs.getString(3)));
                    return p;
                }
            }            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return null;
    }

    private List<String> tratarTrofeus(String trofeus) {
        
        if(trofeus == null || "".equals(trofeus)) {
            return new ArrayList<>();
        }
        
        return Arrays.asList(trofeus.split(","));        
    }
}
    
 