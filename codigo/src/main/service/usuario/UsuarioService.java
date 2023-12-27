package main.service.usuario;

import main.model.itens.ItemBiblioteca;

public interface UsuarioService {

    void imprimirListaUsuarios();

    void adicionarInteressePorItemEmprestado(int userId, ItemBiblioteca item);

    void adicionarInteresseManualmente(int userId);

    void imprimirInteressesPorIdUsuario(int userId);
}
