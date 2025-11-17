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
        String sql = "INSERT INTO despesas (usuario_Id, descricao, categoria, valor, data_cadastro) values (?,?,?,?,?)";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, despesas.getUsuarioId());
        stmt.setString(2, despesas.getDescricao());
        stmt.setString(3, despesas.getCategoria());
        stmt.setDouble(4, despesas.getValor());
        stmt.setObject(5, despesas.getData_cadastro());

        stmt.execute();
        stmt.close();

    }

    public List<Despesas> listaDespesas() throws SQLException {
        List<Despesas> listaDespesas = new ArrayList<>();

        String sql = "SELECT * FROM despesas";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Despesas d = new Despesas(
                    rs.getInt("id"),
                    rs.getInt("usuario_Id"),
                    rs.getString("descricao"),
                    rs.getString("categoria"),
                    rs.getDouble("valor"),
                    rs.getObject("data_cadastro", java.time.LocalDate.class)
            );

            listaDespesas.add(d);

        }


        rs.close();
        stmt.close();
        return listaDespesas;
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM despesas WHERE id = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.execute();
        stmt.close();
    }
}

