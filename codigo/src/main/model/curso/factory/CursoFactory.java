package main.model.curso.factory;

import main.model.curso.Curso;

import java.util.List;

public class CursoFactory implements CursoFactoryMethod{
    @Override
    public Curso criarCurso(String categoria, List<String> relacionados) {
        return new Curso(categoria, relacionados);
    }
}
