package main.model.itens.factory;

import main.model.itens.DVD;
import main.model.itens.ItemBiblioteca;

public class DVDFactory implements ItemFactoryMethod {
    @Override
    public ItemBiblioteca criarItem(String titulo, String autor, int anoPublicacao, int quantidadeExemplares, String categoria) {
        return new DVD(titulo, autor, anoPublicacao, quantidadeExemplares, categoria);
    }
}
