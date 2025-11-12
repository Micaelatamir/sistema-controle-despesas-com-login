package dao;
import model.Usuario;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class UsuarioDAO {
private Connection Conexao;
public UsuarioDAO(Connection conexao) {
    this.Conexao= conexao;

}
public void salvar(Usuario usuario) throws SQLException {
    String sql = "INSERT INTO usuario (nome, email, senha) VALUES (?, ?, ?)";
}
public List<Usuario> listartodos() throws SQLException {
    String sql = "SELECT * FROM usuario";
}
public void deletarUsuario(int id)  throws SQLException{
    String sql = "DELETE FROM usuario WHERE id=?";
}

}

