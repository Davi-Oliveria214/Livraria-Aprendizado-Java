package modelo;

public class Livro {
    private int id;
    private String nome;
    private String autor;
    private double preco;

    public Livro() {
    }

    public Livro(int id, String nome, String autor, double preco) {
        this.id = id;
        this.nome = nome;
        this.autor = autor;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "Nome = '" + nome + '\'' +
                ", Autor = '" + autor + '\'' +
                ", Preco = " + preco +
                '}';
    }
}