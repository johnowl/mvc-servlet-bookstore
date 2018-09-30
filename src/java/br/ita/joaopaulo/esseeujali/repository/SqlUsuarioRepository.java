package br.ita.joaopaulo.esseeujali.repository;

import br.ita.joaopaulo.esseeujali.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlUsuarioRepository extends SqlRepository {

    public Usuario consultarUsuario(String email, String senha) {
        String sql = "SELECT codigo, email, nome, senha FROM Usuarios WHERE email = ? AND senha = ?";
        try (Connection c = getConnection();
                PreparedStatement stm = c.prepareStatement(sql)) {

            stm.setString(1, email);
            stm.setString(2, senha);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return popularUsuario(rs);
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Usuario popularUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setCodigo(rs.getInt("codigo"));
        usuario.setEmail(rs.getString("email"));
        usuario.setNome(rs.getString("nome"));
        usuario.setSenha(rs.getString("senha"));
        return usuario;
    }

}
