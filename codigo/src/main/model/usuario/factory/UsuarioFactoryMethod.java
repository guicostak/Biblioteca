package main.model.usuario.factory;

import main.model.curso.Curso;
import main.model.usuario.Usuario;

public interface UsuarioFactoryMethod {

    Usuario criarUsuario(String nome, Curso cursoAluno);
}
