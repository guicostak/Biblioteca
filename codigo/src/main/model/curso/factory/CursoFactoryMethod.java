package main.model.curso.factory;

import main.model.curso.Curso;

import java.util.List;

public interface CursoFactoryMethod {

    Curso criarCurso(String categoria, List<String> relacionados);
}
