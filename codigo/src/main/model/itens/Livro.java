package main.model.itens;


import main.enums.TipoEnum;

public class Livro extends ItemBiblioteca {
    public Livro(String titulo, String autor, int anoPublicacao, int quantidadeExemplares, String categoria) {
        super(titulo, autor, anoPublicacao, quantidadeExemplares, categoria);
        this.tipoItem = TipoEnum.LIVRO.toString();
        this.periodoEmprestimo = 10;
    }
}