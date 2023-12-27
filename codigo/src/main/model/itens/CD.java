package main.model.itens;

import main.enums.TipoEnum;

public class CD extends ItemBiblioteca {
    public CD(String titulo, String autor, int anoPublicacao, int quantidadeExemplares, String categoria) {
        super(titulo, autor, anoPublicacao, quantidadeExemplares, categoria);
        this.tipoItem = TipoEnum.CD.toString();
        this.periodoEmprestimo = 10;
    }
}
