package main.service.item;

public interface ItemService {

    void cadastrarItem();

    void editarItem(final int id);

    boolean emprestarItem(int idUsuario, int itemId) throws Exception;
}
