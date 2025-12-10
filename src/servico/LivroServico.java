package servico;

import excecoes.LivrariaExcecao;
import excecoes.LivroInvalido;
import gerenciador.GerenciarLivro;
import modelo.Livro;

import java.util.ArrayList;
import java.util.List;

public class LivroServico {
    private int id = 1;

    private String nomeLivro;
    private String nomeAutor;
    private double preco;

    public void addLivro() {
        Livro livro = new Livro(id++, nomeLivro, nomeAutor, preco);
        GerenciarLivro.addLivro(livro);
    }

    public void verificarAutor(String nomeAutor) throws LivrariaExcecao {
        if (nomeAutor.isEmpty()) {
            throw new LivroInvalido("Digite o nome do Autor");
        }

        this.nomeAutor = nomeAutor;
    }

    public void verificarLivro(String nome) throws LivrariaExcecao {
        if (nome.isEmpty()) {
            throw new LivroInvalido("Digite o nome do Livro");
        }

        if (!GerenciarLivro.getLivros().isEmpty()) {
            for (Livro livro : GerenciarLivro.getLivros()) {
                if (livro.getNome().equals(nome) && livro.getAutor().equals(nomeAutor)) {
                    throw new LivroInvalido("Esse livro já está cadastrado");
                }
            }
        }

        this.nomeLivro = nome;
    }

    public void verificarPreco(double preco) throws LivrariaExcecao {
        if (preco <= 0) {
            throw new LivroInvalido("O valor do livro não pode ser menor ou igual a 0");
        }

        this.preco = preco;
    }

    public void excluirLivro(int id) throws LivrariaExcecao {
        boolean isLivro = false;
        if (GerenciarLivro.getLivros().isEmpty()) {
            throw new LivroInvalido("Não há livros para remover");
        }

        for (int i = 0; i < GerenciarLivro.getLivros().size(); i++) {
            if (GerenciarLivro.getLivros().get(i).getId() == id) {
                GerenciarLivro.getLivros().remove(i);
                isLivro = true;
                break;
            }
        }

        if (!isLivro) {
            throw new LivroInvalido("Livro não encontrado");
        }

        /*
        removeIf() pode remover multiplos elementos que corresponde ao requisito da condição desejada
        GerenciarLivro.getLivros().removeIf(livro -> livro.getId() == id);
        */
    }

    public void editarNomeLivro(String novoNome) throws LivrariaExcecao {
        Livro livro = procurarNomeLivroAutor(nomeLivro, nomeAutor);

        if (livro == null) {
            throw new LivroInvalido("==Nenhum livro encontrado==");
        }

        for (int i = 0; i < GerenciarLivro.getLivros().size(); i++) {
            if (GerenciarLivro.getLivros().get(i).getId() == livro.getId()) {
                GerenciarLivro.getLivros().get(i).setNome(novoNome);
            }
        }
    }

    public void editarNomeAutor(String nomeAutor) throws LivrariaExcecao {
        Livro livro = procurarNomeLivroAutor(nomeLivro, nomeAutor);

        for (int i = 0; i < GerenciarLivro.getLivros().size(); i++) {
            if (GerenciarLivro.getLivros().get(i).getId() == livro.getId()) {
                validarLivro(GerenciarLivro.getLivros().get(i).getNome(), nomeAutor);

                GerenciarLivro.getLivros().get(i).setAutor(nomeAutor);
            }
        }
    }

    public void editarPrecoLivro(double preco) throws LivrariaExcecao {
        Livro livro = procurarNomeLivroAutor(nomeLivro, nomeAutor);

        if (preco <= 0) {
            throw new LivroInvalido("O novo preço do livro não pode ser menor ou igual a zero");
        }

        for (int i = 0; i < GerenciarLivro.getLivros().size(); i++) {
            if (GerenciarLivro.getLivros().get(i).getId() == livro.getId()) {
                GerenciarLivro.getLivros().get(i).setPreco(preco);
            }
        }
    }

    private void validarLivro(String nomeLivro, String nomeAutor) throws LivrariaExcecao {
        for (Livro livro : GerenciarLivro.getLivros()) {
            if (livro.getNome().equals(nomeLivro) && livro.getAutor().equals(nomeAutor)) {
                throw new LivroInvalido("Esse livro com o autor, já está cadastrado");
            }
        }
    }

    public void verificarLivros() throws LivrariaExcecao {
        if (GerenciarLivro.getLivros().isEmpty()) {
            throw new LivroInvalido("==Nenhum livro cadastrado no atual momento==");
        }
    }

    public List<String> procurarNomeLivro(String nomeLivro) throws LivrariaExcecao {
        List<String> lista = new ArrayList<>();

        for (Livro livro : GerenciarLivro.getLivros()) {
            if (livro.getNome().equals(nomeLivro)) {
                lista.add(livro.toString());
            }
        }

        if (lista.isEmpty()) {
            throw new LivroInvalido("==Nenhum livro encontrado==");
        }

        return lista;
    }

    public Livro procurarNomeLivroAutor(String nomeLivro, String nomeAutor) throws LivrariaExcecao {
        for (Livro livro : GerenciarLivro.getLivros()) {
            if (livro.getNome().equals(nomeLivro) && livro.getAutor().equals(nomeAutor)) {
                return livro;
            }
        }

        return null;
    }

    public List<Livro> mostrarTodosLivros() throws LivrariaExcecao {
        if (GerenciarLivro.getLivros().isEmpty()) {
            throw new LivroInvalido("==Nenhum livro cadastrado==");
        }

        return GerenciarLivro.getLivros();
    }
}