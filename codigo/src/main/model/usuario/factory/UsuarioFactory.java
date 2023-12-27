package main.model.usuario.factory;

import main.model.curso.Curso;
import main.model.usuario.Usuario;

public class UsuarioFactory implements UsuarioFactoryMethod {
    @Override
    public Usuario criarUsuario(String nome, Curso cursoAluno) {
        return new Usuario(nome, cursoAluno);
    }
}
