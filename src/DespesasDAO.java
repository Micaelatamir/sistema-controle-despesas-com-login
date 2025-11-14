package dao;
import model.Despesas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;

public class DespesasDAO {
    private Connection conexao;

    public DespesasDAO(Connection conexao) {
        this.conexao = conexao;

    }

    public void inserir(Despesas despesas) throws SQLException {
        String sql = "INSERT INTO Despesas (id, usuarioId, descricao, categoria, valor, data_cadastro) values (?,?,?,?,?,?)";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, despesas.getId());
        stmt.setInt(2, despesas.getUsuarioId());
        stmt.setString(3, despesas.getdescricao());
        stmt.setString(4, despesas.getCategoria());
        stmt.setDouble(5, despesas.getValor());
        stmt.setObject(6, despesas.getData_cadastro());
        stmt.execute();
        stmt.close();

    }

    public List<Despesas> listarDespesas() throws SQLException {
        List<Despesas> listaDespesas = new ArrayList<>();
        String sql = "SELECT * FROM Despesas";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Despesas d =new Despesas(
                    rs.getInt("id"),



            )
        }

    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM Despesas WHERE id = ?";
    }
}
