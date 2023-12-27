package main.emprestimo;

import main.enums.TipoEnum;
import main.model.itens.ItemBiblioteca;
import main.model.usuario.Usuario;

public class EmprestimoService {

    public boolean isElegivelAEmprestimo(Usuario usuario, ItemBiblioteca itemBiblioteca) throws Exception {

        if (usuario == null) {
            return false;
        }

        final var quantidadeLivros = usuario.getItensEmprestados()
                .stream()
                .filter(livro -> livro.getTipoItem().equals(TipoEnum.LIVRO.toString())).count();

        if (quantidadeLivros >= 3) {
            throw new Exception("O Usuário já tem 3 livros em sua posse, não é possível realizar um novo empréstimo. ");
        }

        if (usuario.getItensEmprestados().stream().anyMatch(item -> item.getDataEmprestimo()
                .isAfter(item.getDataDevolucao()))) {
            throw new Exception("Não é possível realizar o empréstimo do livro " + itemBiblioteca.getTitulo() +
                    " O usuário possui empréstimo em atraso.");
        }

        if (itemBiblioteca.getTipoItem().equals(TipoEnum.LIVRO.toString()) && itemBiblioteca.getExemplaresDisponiveis() <= 1){
            throw new Exception("Não é possível realizar o empréstimo do livro " + itemBiblioteca.getTitulo() +
                    "É o último exemplar disponível. ");
        }

//        if (itemBiblioteca.getTipoItem().equals(TipoEnum.LIVRO.toString())) {
//            if(usuario.getItensEmprestados().stream().anyMatch(item -> item.equals(itemBiblioteca))){
//                throw new Exception("O Usuário já possui o mesmo exemplar do livro " + itemBiblioteca.getTitulo() + " em sua posse");
//            }
//        }

        return true;
    }
}
