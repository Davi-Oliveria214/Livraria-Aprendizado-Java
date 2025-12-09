package menu;

import excecoes.LivrariaExcecao;
import gerenciador.GerenciarLivro;
import modelo.Livro;
import servico.LivroServico;

import java.util.Scanner;

public class LivroMenu {
    private final LivroServico livroServico;
    private final Scanner sc = new Scanner(System.in);

    public LivroMenu() {
        this.livroServico = new LivroServico();
    }

    public void entrada() {
        int opcao;
        do {
            System.out.println("Escolha uma das opções\n 1 - Adicionar um novo livro\n 2 - Ver todos os livros\n 3 - Excluir livro\n 4 - Editar Livro\n 0 - sair");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    sc.nextLine();
                    adicionar();
                    break;
                case 2:
                    livrosCadastrados();
                    break;
                case 3:
                    excluirLivro();
                    break;
                case 4:
                    editarLivro();
                    break;
                case 0:
                    System.out.println("Fechando programa");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while (opcao != 0);
    }

    private void adicionar() {
        int opcao = 0;

        do {
            try {
                System.out.println("\n--Adicionar um novo livro--\n");

                System.out.println("Digite o nome do Autor");
                livroServico.verificarAutor(sc.nextLine());

                System.out.println("Digite o nome do Livro");
                livroServico.verificarLivro(sc.nextLine());

                System.out.println("Digite o preço do Livro");
                double preco = sc.nextDouble();
                livroServico.verificarPreco(preco);

                livroServico.addLivro();

                System.out.println("Deseja cadastrar outro livro ?\n 1-Sim\n 2-Não");
                opcao = sc.nextInt();
                sc.nextLine();
            } catch (LivrariaExcecao e) {
                System.out.println(e.getMessage());
            }
        } while (opcao == 1);

        System.out.println("Voltando ao menu principal");
    }

    private void livrosCadastrados() {
        if (!GerenciarLivro.getLivros().isEmpty()) {
            System.out.println("\n|--Todos os livros cadastrados--|\n");
            for (Livro livro : GerenciarLivro.getLivros()) {
                System.out.println(livro.toString());
            }
            System.out.println("\n==Quantidade de livros cadastrados: " + GerenciarLivro.getLivros().size() + "==\n");
        } else {
            System.out.println("\n==Nenhum livro cadastrado==\n");
        }
    }

    private void excluirLivro() {
        int opcao = 0;
        do {
            try {
                System.out.println("\nDigite o id do livro que deseja excluir");
                livroServico.excluirLivro(sc.nextInt());
                System.out.println("\n==Livro excluído com sucesso==\n");

                System.out.println("Deseja cadastrar outro livro ?\n 1-Sim\n 2-Não");
                opcao = sc.nextInt();
                sc.nextLine();
            } catch (LivrariaExcecao e) {
                System.out.println(e.getMessage());
            }
        } while (opcao != 1);
    }

    private void editarLivro() {
        System.out.println("Digite o id do livro que deseja editar");
        int id = sc.nextInt();

        System.out.println("Escolha uma das opções de edição\n 1 - Alterar nome do Autor\n 2 - Alterar nome do Livro\n 3 - Alterar Preço");
        int opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1:
                System.out.println("Digite o novo nome do Autor");
                livroServico.editarNomeAutor(id, sc.nextLine());
                break;
            case 2:
                System.out.println("Digite o novo nome do Livro");
                livroServico.editarNomeLivro(id, sc.nextLine());
                break;
            case 3:
                System.out.println("Digite o novo preço do livro");
                livroServico.editarPrecoLivro(id, sc.nextDouble());
                sc.nextLine();
                break;
            default:
                System.out.println("Opção inválida");
        }
    }
}