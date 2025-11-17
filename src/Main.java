package Main;

import dao.DespesasDAO;
import dao.UsuariosDAO;
import model.Usuarios;
import model.Despesas;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
      try{  Connection conexao = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/sistema_controle_despesas_com_login",
                "postgres",
                "977310607"
        );

          UsuariosDAO usuarioDAO = new UsuariosDAO(conexao);
          Usuarios usuariosLogado = null;
          DespesasDAO DespesasDAO = new DespesasDAO(conexao);




          int opcao = 0;
        while (opcao != 6) {
            System.out.println("=========MENU==========");
            System.out.println("1 - Cadastro usuário");
            System.out.println("2 - Fazer login");
            System.out.println("3 - Cadastra despesas");
            System.out.println("4 - Listar despesas");
            System.out.println("5 - excluir despesas");
            System.out.println("6 - Sair");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Senha: ");
                    String senha = sc.nextLine();

                    Usuarios novo = new Usuarios(0, nome, email, senha);
                    usuarioDAO.salvar(novo);
                    System.out.println(" Usuário cadastrado com sucesso!");

                    break;

                case 2:
                    System.out.println("Login");
                    String emaillogin = sc.nextLine();
                    System.out.println("Senha");
                    String senhalogin = sc.nextLine();
                    usuariosLogado = usuarioDAO.login(emaillogin, senhalogin);

                    if (usuariosLogado == null) {
                        System.out.println("Email ou senha incorretos!");
                    break;
                    }

                        System.out.println("Login realizado com sucesso!");
                        break;


                case 3:
                    if (usuariosLogado == null) {
                        System.out.println("Você precisa fazer login para cadastrar despesas!");
                        break;
                    }

                    System.out.print("Descrição: ");
                    String descricao = sc.nextLine();
                    System.out.print("Categoria: ");
                    String categoria = sc.nextLine();
                    System.out.print("Valor: ");
                    double valor = sc.nextDouble();
                    sc.nextLine();
                    LocalDate data_cadastro = LocalDate.now();

                    Despesas nova = new Despesas(
                            0,
                            usuariosLogado.getId(),
                            descricao,
                            categoria,
                            valor,
                            data_cadastro
                    );

                    DespesasDAO.inserir(nova);
                    System.out.println("Despesas Cadastradas com sucesso!");
                    break;

                case 4:
                    if (usuariosLogado == null) {
                        System.out.println("Você precisa fazer login para listar despesas!");
                        break;
                    }

                    for (Despesas d : DespesasDAO.listaDespesas()) {
                        if (d.getUsuarioId() == usuariosLogado.getId()) {
                            System.out.println(
                                    "ID: " + d.getId() +
                                            " | Descrição: " + d.getDescricao() +
                                            " | Valor: " + d.getValor()
                            );
                        }
                    }
                    break;

                case 5:
                    if (usuariosLogado == null) {
                        System.out.println("Você precisa fazer login para excluir despesas!");
                        break;
                    }

                    System.out.println("=== Suas despesas ===");
                    Boolean achoualguma = false;

                    for (Despesas d : DespesasDAO.listaDespesas()) {
                        if (d.getUsuarioId() == usuariosLogado.getId()) {
                            achoualguma = true;
                            System.out.println(
                                    "ID: " + d.getId() +
                                            " | Descrição: " + d.getDescricao() +
                                            " | Valor: " + d.getValor()
                            );
                        }
                    }
                    if (!achoualguma) {
                        System.out.println("Você não tem despesas cadastradas");
                        break;
                    }



                    System.out.print("Digite o ID da despesa que deseja excluir: ");
                    int idExcluir = sc.nextInt();
                    sc.nextLine();

                    boolean encontrado = false;
                    for (Despesas d : DespesasDAO.listaDespesas()) {
                        if (d.getId() == idExcluir &&  d.getUsuarioId() == usuariosLogado.getId()) {
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("Despesas não encontrada");
                        break;
                    }


                    DespesasDAO.excluir(idExcluir); // n
                    System.out.println("Despesa excluída com sucesso!");
                    break;
            }
        }
            conexao.close();

        }catch(SQLException e){
            e.printStackTrace();

        }
    }
}