package main.model.itens.factory;

import main.model.itens.ItemBiblioteca;
import main.model.itens.Tese;

public class TeseFactory implements ItemFactoryMethod {
    @Override
    public ItemBiblioteca criarItem(String titulo, String autor, int anoPublicacao, int quantidadeExemplares, String categoria) {
        return new Tese(titulo, autor, anoPublicacao, quantidadeExemplares, categoria);
    }
}
