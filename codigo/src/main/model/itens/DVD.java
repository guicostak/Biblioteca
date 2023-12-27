package main.model.itens;

import main.enums.TipoEnum;

public class DVD extends ItemBiblioteca {
    public DVD(String titulo, String autor, int anoPublicacao, int quantidadeExemplares, String categoria) {
        super(titulo, autor, anoPublicacao, quantidadeExemplares, categoria);
        this.tipoItem = TipoEnum.DVD.toString();
        this.periodoEmprestimo = 10;
    }
}
