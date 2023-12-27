package main.model.itens.factory;

import main.model.itens.CD;
import main.model.itens.ItemBiblioteca;

public class CDFactory implements ItemFactoryMethod {
    @Override
    public ItemBiblioteca criarItem(String titulo, String autor, int anoPublicacao, int quantidadeExemplares, String categoria) {
        return new CD(titulo, autor, anoPublicacao, quantidadeExemplares, categoria);
    }
}
