package gerenciador;

import modelo.Livro;

import java.util.ArrayList;
import java.util.List;

public class GerenciarLivro {
    private static List<Livro> livros = new ArrayList<>();

    public static List<Livro> getLivros() {
        return livros;
    }

    public static void setLivros(ArrayList<Livro> livros) {
        GerenciarLivro.livros = livros;
    }

    public static void addLivro(Livro livro){
        GerenciarLivro.getLivros().add(livro);
    }
}