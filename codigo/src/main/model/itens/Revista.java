package main.model.itens;

import main.enums.TipoEnum;

public class Revista extends ItemBiblioteca {
    public Revista(String titulo, String autor, int anoPublicacao, int quantidadeExemplares, String categoria)
    {
        super(titulo, autor, anoPublicacao, quantidadeExemplares, categoria);
        this.emprestavel = false;
        this.tipoItem = TipoEnum.REVISTA.toString();
    }
}
