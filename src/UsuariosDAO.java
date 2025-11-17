package dao;
import model.Usuarios;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class UsuariosDAO {
private Connection conexao;
public UsuariosDAO(Connection conexao) {
    this.conexao= conexao;

}
public void salvar(Usuarios usuario) throws SQLException {
    String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";
    PreparedStatement stmt = conexao.prepareStatement(sql);
    stmt.setString(1, usuario.getNome());
    stmt.setString(2, usuario.getEmail());
    stmt.setString(3, usuario.getSenha());
    stmt.executeUpdate();
    stmt.close();
}


public Usuarios login(String email, String senha) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, email);
        stmt.setString(2, senha);

        ResultSet rs = stmt.executeQuery();
        Usuarios usuario = null;

        if (rs.next()) {
            usuario = new Usuarios(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha")
            );
        }

        rs.close();
        stmt.close();

        return usuario;
    }





public List<Usuarios> listartodos() throws SQLException {
    List<Usuarios> usuarios = new ArrayList<>();
    String sql = "SELECT * FROM usuarios";
    PreparedStatement stmt = conexao.prepareStatement(sql);
    ResultSet rs = stmt.executeQuery();

    while (rs.next()) {
        Usuarios u = new Usuarios(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("email"),
                rs.getString("senha")
        );
        usuarios.add(u);
    }
    rs.close();
    stmt.close();

    return usuarios;
}
public void deletarUsuario(int id)  throws SQLException{

    String sql = "DELETE FROM usuarios WHERE id=?";
    PreparedStatement stmt = conexao.prepareStatement(sql);
    stmt.setInt(1, id);
    stmt.executeUpdate();
    stmt.close();
}


}

