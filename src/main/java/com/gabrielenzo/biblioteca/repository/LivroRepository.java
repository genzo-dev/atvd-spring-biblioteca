package com.gabrielenzo.biblioteca.repository;

import com.gabrielenzo.biblioteca.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findByQuantidadeDisponivelGreaterThanOrderByTituloAsc(
            Integer quantidade
    );

    List<Livro> findByCategoriaNome(String nome);

    @Query ("""
    SELECT l
    FROM Livro l
    JOIN l.autores a
    WHERE LOWER(a.nome) LIKE LOWER(CONCAT('%', :nome, '%'))
    ORDER BY l.anoPublicacao ASC
""")
List<Livro> findLivrosPorAutor(String nome);
}