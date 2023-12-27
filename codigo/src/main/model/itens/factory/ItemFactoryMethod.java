package main.model.itens.factory;

import main.model.itens.ItemBiblioteca;

public interface ItemFactoryMethod {

    ItemBiblioteca criarItem(String titulo, String autor, int anoPublicacao,
                             int quantidadeExemplares, String categoria
    );
}
