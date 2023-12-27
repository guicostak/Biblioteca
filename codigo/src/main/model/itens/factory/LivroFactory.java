package main.model.itens.factory;

import main.model.itens.ItemBiblioteca;
import main.model.itens.Livro;

public class LivroFactory implements ItemFactoryMethod {
    @Override
    public ItemBiblioteca criarItem(String titulo, String autor, int anoPublicacao, int quantidadeExemplares, String categoria) {
        return new Livro(titulo, autor, anoPublicacao, quantidadeExemplares, categoria);
    }
}
