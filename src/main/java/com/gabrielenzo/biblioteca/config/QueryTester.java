package com.gabrielenzo.biblioteca.config;

import com.gabrielenzo.biblioteca.model.Usuario;
import com.gabrielenzo.biblioteca.repository.CategoriaRepository;
import com.gabrielenzo.biblioteca.repository.EmprestimoRepository;
import com.gabrielenzo.biblioteca.repository.LivroRepository;
import com.gabrielenzo.biblioteca.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class QueryTester implements CommandLineRunner {

    private final LivroRepository livroRepository;
    private final UsuarioRepository usuarioRepository;
    private final EmprestimoRepository emprestimoRepository;
    private final CategoriaRepository categoriaRepository;

    public QueryTester(
            LivroRepository livroRepository,
            UsuarioRepository usuarioRepository,
            EmprestimoRepository emprestimoRepository,
            CategoriaRepository categoriaRepository
    ) {
        this.livroRepository = livroRepository;
        this.usuarioRepository = usuarioRepository;
        this.emprestimoRepository = emprestimoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public void run(String... args) {

        // ============================================================
        // CONSULTA 1
        // Todos os livros disponíveis, ordenados pelo título
        // ============================================================

        System.out.println("\n========================================");
        System.out.println("CONSULTA 1 - LIVROS DISPONÍVEIS");
        System.out.println("========================================");

        livroRepository
                .findByQuantidadeDisponivelGreaterThanOrderByTituloAsc(0)
                .forEach(livro -> {
                    System.out.println(
                            livro.getTitulo()
                                    + " | Disponíveis: "
                                    + livro.getQuantidadeDisponivel()
                    );
                });


        // ============================================================
        // CONSULTA 2
        // Livros de uma determinada categoria
        // ============================================================

        System.out.println("\n========================================");
        System.out.println("CONSULTA 2 - LIVROS POR CATEGORIA");
        System.out.println("========================================");

        livroRepository
                .findByCategoriaNome("Literatura")
                .forEach(livro -> {
                    System.out.println(
                            livro.getTitulo()
                                    + " | Categoria: "
                                    + livro.getCategoria().getNome()
                    );
                });


        // ============================================================
        // CONSULTA 3
        // Usuários cujo nome contém determinado texto
        // ============================================================

        System.out.println("\n========================================");
        System.out.println("CONSULTA 3 - USUÁRIOS POR NOME");
        System.out.println("========================================");

        usuarioRepository
                .findByNomeContainingIgnoreCase("Silva")
                .forEach(usuario -> {
                    System.out.println(
                            usuario.getNome()
                                    + " | CPF: "
                                    + usuario.getCpf()
                    );
                });


        // ============================================================
        // CONSULTA 4
        // Empréstimos ativos de um usuário, incluindo os livros
        // ============================================================

        System.out.println("\n========================================");
        System.out.println("CONSULTA 4 - EMPRÉSTIMOS ATIVOS DO USUÁRIO");
        System.out.println("========================================");

        Usuario joao = usuarioRepository
                .findByNomeContainingIgnoreCase("João Silva")
                .get(0);

        emprestimoRepository
                .findEmprestimosAtivosPorUsuario(joao.getId())
                .forEach(emprestimo -> {

                    System.out.println(
                            "Empréstimo ID: "
                                    + emprestimo.getId()
                    );

                    System.out.println(
                            "Usuário: "
                                    + emprestimo.getUsuario().getNome()
                    );

                    System.out.println(
                            "Data do empréstimo: "
                                    + emprestimo.getDataEmprestimo()
                    );

                    System.out.println(
                            "Devolução prevista: "
                                    + emprestimo.getDataDevolucaoPrevista()
                    );

                    emprestimo.getItens().forEach(item -> {
                        System.out.println(
                                "  Livro: "
                                        + item.getLivro().getTitulo()
                        );
                    });
                });


        // ============================================================
        // CONSULTA 5
        // Livros de um determinado autor, ordenados por ano
        // ============================================================

        System.out.println("\n========================================");
        System.out.println("CONSULTA 5 - LIVROS POR AUTOR");
        System.out.println("========================================");

        livroRepository
                .findLivrosPorAutor("Machado de Assis")
                .forEach(livro -> {
                    System.out.println(
                            livro.getAnoPublicacao()
                                    + " | "
                                    + livro.getTitulo()
                    );
                });


        // ============================================================
        // CONSULTA 6
        // Empréstimos atrasados, incluindo usuário e livros
        // ============================================================

        System.out.println("\n========================================");
        System.out.println("CONSULTA 6 - EMPRÉSTIMOS ATRASADOS");
        System.out.println("========================================");

        emprestimoRepository
                .findEmprestimosAtrasados()
                .forEach(emprestimo -> {

                    System.out.println(
                            "Usuário: "
                                    + emprestimo.getUsuario().getNome()
                    );

                    System.out.println(
                            "Empréstimo ID: "
                                    + emprestimo.getId()
                    );

                    System.out.println(
                            "Devolução prevista: "
                                    + emprestimo.getDataDevolucaoPrevista()
                    );

                    System.out.println(
                            "Status: "
                                    + emprestimo.getStatus()
                    );

                    emprestimo.getItens().forEach(item -> {
                        System.out.println(
                                "  Livro: "
                                        + item.getLivro().getTitulo()
                        );
                    });
                });


        // ============================================================
        // CONSULTA 7
        // Quantidade de livros por categoria
        // ============================================================

        System.out.println("\n========================================");
        System.out.println("CONSULTA 7 - QUANTIDADE DE LIVROS POR CATEGORIA");
        System.out.println("========================================");

        categoriaRepository
                .contarLivrosPorCategoria()
                .forEach(resultado -> {
                    System.out.println(
                            resultado.getNome()
                                    + " | Livros: "
                                    + resultado.getQuantidade()
                    );
                });
    }
}