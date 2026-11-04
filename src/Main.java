import model.Movimentacao;
import model.Receita;
import model.Despesa;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MovimentacaoDAO dao = new MovimentacaoDAO();

        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n===== FINCONTROL =====");
            System.out.println("1 - Cadastrar receita");
            System.out.println("2 - Cadastrar despesa");
            System.out.println("3 - Listar movimentações");
            System.out.println("4 - Atualizar movimentação");
            System.out.println("5 - Excluir movimentação");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        cadastrarReceita(scanner, dao);
                        break;
                    case 2:
                        cadastrarDespesa(scanner, dao);
                        break;
                    case 3:
                        dao.listar();
                        break;
                    case 4:
                        atualizar(scanner, dao);
                        break;
                    case 5:
                        excluir(scanner, dao);
                        break;
                    case 0:
                        System.out.println("Sistema encerrado.");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Erro: digite um número válido.");
            } catch (SQLException e) {
                System.out.println("Erro no banco de dados: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Erro nos dados: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void cadastrarReceita(
            Scanner scanner, MovimentacaoDAO dao) throws SQLException {

        System.out.print("Descrição da receita: ");
        String descricao = scanner.nextLine();

        System.out.print("Valor: ");
        double valor = Double.parseDouble(
                scanner.nextLine().replace(",", "."));

        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();

        System.out.print("Data (dd/MM/yyyy): ");
        String data = scanner.nextLine();

        Movimentacao receita = new Receita(
                0, descricao, valor, categoria, data);

        dao.cadastrar(receita);
    }

    private static void cadastrarDespesa(
            Scanner scanner, MovimentacaoDAO dao) throws SQLException {

        System.out.print("Descrição da despesa: ");
        String descricao = scanner.nextLine();

        System.out.print("Valor: ");
        double valor = Double.parseDouble(
                scanner.nextLine().replace(",", "."));

        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();

        System.out.print("Data (dd/MM/yyyy): ");
        String data = scanner.nextLine();

        Movimentacao despesa = new Despesa(
                0, descricao, valor, categoria, data);

        dao.cadastrar(despesa);
    }

    private static void atualizar(
            Scanner scanner, MovimentacaoDAO dao) throws SQLException {

        System.out.print("ID da movimentação: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Nova descrição: ");
        String descricao = scanner.nextLine();

        System.out.print("Novo valor: ");
        double valor = Double.parseDouble(
                scanner.nextLine().replace(",", "."));

        System.out.print("Nova categoria: ");
        String categoria = scanner.nextLine();

        System.out.print("Nova data (dd/MM/yyyy): ");
        String data = scanner.nextLine();

        dao.atualizar(id, descricao, valor, categoria, data);
    }

    private static void excluir(
            Scanner scanner, MovimentacaoDAO dao) throws SQLException {

        System.out.print("ID da movimentação que deseja excluir: ");
        int id = Integer.parseInt(scanner.nextLine());

        dao.excluir(id);
    }
}
