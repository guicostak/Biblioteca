package main.model.itens.factory;

import main.model.itens.ItemBiblioteca;
import main.model.itens.Revista;

public class RevistaFactory implements ItemFactoryMethod {
    @Override
    public ItemBiblioteca criarItem(String titulo, String autor, int anoPublicacao, int quantidadeExemplares, String categoria) {
        return new Revista(titulo, autor, anoPublicacao, quantidadeExemplares, categoria);
    }
}
