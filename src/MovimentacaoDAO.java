import model.Movimentacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MovimentacaoDAO {
    public void cadastrar(Movimentacao movimentacao) throws SQLException {
        if (movimentacao == null) {
            throw new IllegalArgumentException(
                    "A movimentação não pode ser nula."
            );
        }

        if (movimentacao.getDescricao() == null ||
                movimentacao.getDescricao().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "A descrição da movimentação é obrigatória."
            );
        }

        if (movimentacao.getValor() <= 0) {
            throw new IllegalArgumentException(
                    "O valor deve ser maior que zero."
            );
        }

        String sql = "INSERT INTO movimentacoes " +
                "(descricao, valor, tipo, categoria, data_movimentacao) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, movimentacao.getDescricao());
            comando.setDouble(2, movimentacao.getValor());
            comando.setString(3, movimentacao.getTipo());
            comando.setString(4, movimentacao.getCategoria());

            java.time.LocalDate dataConvertida =
                    java.time.LocalDate.parse(
                            movimentacao.getData(),
                            java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")
                    );

            comando.setDate(5, java.sql.Date.valueOf(dataConvertida));

            comando.executeUpdate();

            System.out.println("Movimentação cadastrada com sucesso!");

        } catch (java.time.format.DateTimeParseException e) {
            throw new IllegalArgumentException(
                    "Data inválida. Informe a data no formato dd/MM/yyyy.", e
            );
        }
    }
    public void listar() throws SQLException {
        String sql = "SELECT * FROM movimentacoes";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql);
             java.sql.ResultSet resultado = comando.executeQuery()) {

            int quantidade = 0;

            while (resultado.next()) {
                quantidade++;

                System.out.println("-------------------------");
                System.out.println("ID: " + resultado.getInt("id"));
                System.out.println("Descrição: " + resultado.getString("descricao"));
                System.out.println("Valor: R$ " + resultado.getBigDecimal("valor"));
                System.out.println("Tipo: " + resultado.getString("tipo"));
                System.out.println("Categoria: " + resultado.getString("categoria"));
                System.out.println("Data: " + resultado.getDate("data_movimentacao"));
            }

            if (quantidade == 0) {
                System.out.println("Nenhuma movimentação cadastrada.");
            }
        }
    }
    public void atualizar(int id, String descricao, double valor,
                          String categoria, String data) throws SQLException {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "A descrição da movimentação é obrigatória.");
        }

        if (valor <= 0) {
            throw new IllegalArgumentException(
                    "O valor deve ser maior que zero.");
        }
        String sql = "UPDATE movimentacoes " +
                "SET descricao = ?, valor = ?, categoria = ?, data_movimentacao = ? " +
                "WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, descricao);
            comando.setDouble(2, valor);
            comando.setString(3, categoria);

            java.time.LocalDate dataConvertida =
                    java.time.LocalDate.parse(
                            data,
                            java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")
                    );

            comando.setDate(4, java.sql.Date.valueOf(dataConvertida));
            comando.setInt(5, id);

            int linhasAlteradas = comando.executeUpdate();

            if (linhasAlteradas == 0) {
                throw new SQLException(
                        "Não foi possível atualizar: movimentação não encontrada para o ID " + id
                );
            }

            System.out.println("Movimentação atualizada com sucesso!");
        } catch (java.time.format.DateTimeParseException e) {
            throw new IllegalArgumentException(
                    "Data inválida. Informe a data no formato dd/MM/yyyy.", e
            );
        }
    }
    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM movimentacoes WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, id);

            int linhasExcluidas = comando.executeUpdate();

            if (linhasExcluidas == 0) {
                throw new SQLException(
                        "Não foi possível excluir: movimentação não encontrada para o ID " + id
                );
            }

            System.out.println("Movimentação excluída com sucesso!");
        }
    }
}
