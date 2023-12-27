package main.model.itens;

import main.enums.TipoEnum;

public class Tese extends ItemBiblioteca {
    public Tese(String titulo, String autor, int anoPublicacao, int quantidadeExemplares, String categoria) {
        super(titulo, autor, anoPublicacao, quantidadeExemplares, categoria);
        this.emprestavel = false;
        this.tipoItem = TipoEnum.TESE.toString();
    }
}