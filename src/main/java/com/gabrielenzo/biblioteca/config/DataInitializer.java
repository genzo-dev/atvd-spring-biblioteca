package com.gabrielenzo.biblioteca.config;

import com.gabrielenzo.biblioteca.model.Autor;
import com.gabrielenzo.biblioteca.model.Categoria;
import com.gabrielenzo.biblioteca.model.Emprestimo;
import com.gabrielenzo.biblioteca.model.ItemEmprestimo;
import com.gabrielenzo.biblioteca.model.Livro;
import com.gabrielenzo.biblioteca.model.StatusEmprestimo;
import com.gabrielenzo.biblioteca.model.Usuario;
import com.gabrielenzo.biblioteca.repository.AutorRepository;
import com.gabrielenzo.biblioteca.repository.CategoriaRepository;
import com.gabrielenzo.biblioteca.repository.EmprestimoRepository;
import com.gabrielenzo.biblioteca.repository.ItemEmprestimoRepository;
import com.gabrielenzo.biblioteca.repository.LivroRepository;
import com.gabrielenzo.biblioteca.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@Order(1)
public class DataInitializer implements CommandLineRunner {

    private final CategoriaRepository categoriaRepository;
    private final AutorRepository autorRepository;
    private final LivroRepository livroRepository;
    private final UsuarioRepository usuarioRepository;
    private final EmprestimoRepository emprestimoRepository;
    private final ItemEmprestimoRepository itemEmprestimoRepository;

    public DataInitializer(
            CategoriaRepository categoriaRepository,
            AutorRepository autorRepository,
            LivroRepository livroRepository,
            UsuarioRepository usuarioRepository,
            EmprestimoRepository emprestimoRepository,
            ItemEmprestimoRepository itemEmprestimoRepository
    ) {
        this.categoriaRepository = categoriaRepository;
        this.autorRepository = autorRepository;
        this.livroRepository = livroRepository;
        this.usuarioRepository = usuarioRepository;
        this.emprestimoRepository = emprestimoRepository;
        this.itemEmprestimoRepository = itemEmprestimoRepository;
    }

    @Override
    public void run(String... args) {

        if (categoriaRepository.count() > 0) {
            return;
        }

        // ========================================
        // 1. CATEGORIAS
        // ========================================

        Categoria tecnologia = new Categoria();
        tecnologia.setNome("Tecnologia");
        tecnologia.setDescricao("Livros sobre tecnologia e programação");

        Categoria literatura = new Categoria();
        literatura.setNome("Literatura");
        literatura.setDescricao("Obras literárias clássicas e contemporâneas");

        Categoria historia = new Categoria();
        historia.setNome("História");
        historia.setDescricao("Livros sobre acontecimentos históricos");

        categoriaRepository.save(tecnologia);
        categoriaRepository.save(literatura);
        categoriaRepository.save(historia);

        // ========================================
        // 2. AUTORES
        // ========================================

        Autor robertMartin = new Autor();
        robertMartin.setNome("Robert C. Martin");
        robertMartin.setDataNascimento(LocalDate.of(1952, 12, 5));
        robertMartin.setNacionalidade("Americana");
        robertMartin.setBiografia("Autor e engenheiro de software conhecido por obras sobre desenvolvimento de software.");

        Autor davidThomas = new Autor();
        davidThomas.setNome("David Thomas");
        davidThomas.setDataNascimento(LocalDate.of(1956, 5, 8));
        davidThomas.setNacionalidade("Americana");
        davidThomas.setBiografia("Programador e coautor de obras sobre desenvolvimento de software.");

        Autor machado = new Autor();
        machado.setNome("Machado de Assis");
        machado.setDataNascimento(LocalDate.of(1839, 6, 21));
        machado.setNacionalidade("Brasileira");
        machado.setBiografia("Escritor brasileiro e um dos principais nomes da literatura brasileira.");

        Autor georgeOrwell = new Autor();
        georgeOrwell.setNome("George Orwell");
        georgeOrwell.setDataNascimento(LocalDate.of(1903, 6, 25));
        georgeOrwell.setNacionalidade("Britânica");
        georgeOrwell.setBiografia("Escritor e jornalista britânico, autor de importantes obras de ficção e crítica social.");

        autorRepository.save(robertMartin);
        autorRepository.save(davidThomas);
        autorRepository.save(machado);
        autorRepository.save(georgeOrwell);

        // ========================================
        // 3. LIVROS
        // ========================================

        Livro cleanCode = new Livro();
        cleanCode.setIsbn("9780132350884");
        cleanCode.setTitulo("Clean Code");
        cleanCode.setEditora("Prentice Hall");
        cleanCode.setAnoPublicacao(2008);
        cleanCode.setNumeroPaginas(464);
        cleanCode.setQuantidadeTotal(5);
        cleanCode.setQuantidadeDisponivel(3);
        cleanCode.setCategoria(tecnologia);
        cleanCode.getAutores().add(robertMartin);

        Livro pragmaticProgrammer = new Livro();
        pragmaticProgrammer.setIsbn("9780135957059");
        pragmaticProgrammer.setTitulo("The Pragmatic Programmer");
        pragmaticProgrammer.setEditora("Addison-Wesley");
        pragmaticProgrammer.setAnoPublicacao(2019);
        pragmaticProgrammer.setNumeroPaginas(352);
        pragmaticProgrammer.setQuantidadeTotal(4);
        pragmaticProgrammer.setQuantidadeDisponivel(4);
        pragmaticProgrammer.setCategoria(tecnologia);
        pragmaticProgrammer.getAutores().add(davidThomas);

        Livro domCasmurro = new Livro();
        domCasmurro.setIsbn("9788535914849");
        domCasmurro.setTitulo("Dom Casmurro");
        domCasmurro.setEditora("Companhia das Letras");
        domCasmurro.setAnoPublicacao(1899);
        domCasmurro.setNumeroPaginas(288);
        domCasmurro.setQuantidadeTotal(3);
        domCasmurro.setQuantidadeDisponivel(0);
        domCasmurro.setCategoria(literatura);
        domCasmurro.getAutores().add(machado);

        Livro memoriasPostumas = new Livro();
        memoriasPostumas.setIsbn("9788535910660");
        memoriasPostumas.setTitulo("Memórias Póstumas de Brás Cubas");
        memoriasPostumas.setEditora("Companhia das Letras");
        memoriasPostumas.setAnoPublicacao(1881);
        memoriasPostumas.setNumeroPaginas(256);
        memoriasPostumas.setQuantidadeTotal(4);
        memoriasPostumas.setQuantidadeDisponivel(4);
        memoriasPostumas.setCategoria(literatura);
        memoriasPostumas.getAutores().add(machado);

        Livro nineteenEightyFour = new Livro();
        nineteenEightyFour.setIsbn("9780451524935");
        nineteenEightyFour.setTitulo("1984");
        nineteenEightyFour.setEditora("Signet Classics");
        nineteenEightyFour.setAnoPublicacao(1949);
        nineteenEightyFour.setNumeroPaginas(328);
        nineteenEightyFour.setQuantidadeTotal(5);
        nineteenEightyFour.setQuantidadeDisponivel(2);
        nineteenEightyFour.setCategoria(literatura);
        nineteenEightyFour.getAutores().add(georgeOrwell);

        Livro historiaBrasil = new Livro();
        historiaBrasil.setIsbn("9788508120301");
        historiaBrasil.setTitulo("História do Brasil");
        historiaBrasil.setEditora("Ática");
        historiaBrasil.setAnoPublicacao(2010);
        historiaBrasil.setNumeroPaginas(400);
        historiaBrasil.setQuantidadeTotal(2);
        historiaBrasil.setQuantidadeDisponivel(2);
        historiaBrasil.setCategoria(historia);
        historiaBrasil.getAutores().add(machado);

        livroRepository.save(cleanCode);
        livroRepository.save(pragmaticProgrammer);
        livroRepository.save(domCasmurro);
        livroRepository.save(memoriasPostumas);
        livroRepository.save(nineteenEightyFour);
        livroRepository.save(historiaBrasil);

        // ========================================
        // 4. USUÁRIOS
        // ========================================

        Usuario joao = new Usuario();
        joao.setNome("João Silva");
        joao.setCpf("11111111111");
        joao.setEmail("joao.silva@email.com");
        joao.setTelefone("84999990001");
        joao.setDataCadastro(LocalDate.of(2026, 1, 10));
        joao.setEndereco("Rua A, 100");
        joao.setAtivo(true);

        Usuario maria = new Usuario();
        maria.setNome("Maria Silva Santos");
        maria.setCpf("22222222222");
        maria.setEmail("maria.santos@email.com");
        maria.setTelefone("84999990002");
        maria.setDataCadastro(LocalDate.of(2026, 2, 15));
        maria.setEndereco("Rua B, 200");
        maria.setAtivo(true);

        Usuario carlos = new Usuario();
        carlos.setNome("Carlos Oliveira");
        carlos.setCpf("33333333333");
        carlos.setEmail("carlos.oliveira@email.com");
        carlos.setTelefone("84999990003");
        carlos.setDataCadastro(LocalDate.of(2026, 3, 20));
        carlos.setEndereco("Rua C, 300");
        carlos.setAtivo(true);

        usuarioRepository.save(joao);
        usuarioRepository.save(maria);
        usuarioRepository.save(carlos);

        // ========================================
        // 5. EMPRÉSTIMOS
        // ========================================

        Emprestimo emprestimoJoao = new Emprestimo();
        emprestimoJoao.setUsuario(joao);
        emprestimoJoao.setDataEmprestimo(LocalDate.of(2026, 9, 10));
        emprestimoJoao.setDataDevolucaoPrevista(LocalDate.of(2026, 9, 30));
        emprestimoJoao.setStatus(StatusEmprestimo.ATIVO);
        emprestimoJoao.setValorMulta(BigDecimal.ZERO);

        Emprestimo emprestimoMaria = new Emprestimo();
        emprestimoMaria.setUsuario(maria);
        emprestimoMaria.setDataEmprestimo(LocalDate.of(2026, 9, 1));
        emprestimoMaria.setDataDevolucaoPrevista(LocalDate.of(2026, 9, 10));
        emprestimoMaria.setDataDevolucaoEfetiva(LocalDate.of(2026, 9, 9));
        emprestimoMaria.setStatus(StatusEmprestimo.DEVOLVIDO);
        emprestimoMaria.setValorMulta(BigDecimal.ZERO);

        Emprestimo emprestimoCarlos = new Emprestimo();
        emprestimoCarlos.setUsuario(carlos);
        emprestimoCarlos.setDataEmprestimo(LocalDate.of(2026, 8, 1));
        emprestimoCarlos.setDataDevolucaoPrevista(LocalDate.of(2026, 8, 15));
        emprestimoCarlos.setStatus(StatusEmprestimo.ATIVO);
        emprestimoCarlos.setValorMulta(BigDecimal.valueOf(10.00));

        emprestimoRepository.save(emprestimoJoao);
        emprestimoRepository.save(emprestimoMaria);
        emprestimoRepository.save(emprestimoCarlos);

        // ========================================
        // 6. ITENS DOS EMPRÉSTIMOS
        // ========================================

        ItemEmprestimo itemJoao1 = new ItemEmprestimo();
        itemJoao1.setEmprestimo(emprestimoJoao);
        itemJoao1.setLivro(cleanCode);

        ItemEmprestimo itemJoao2 = new ItemEmprestimo();
        itemJoao2.setEmprestimo(emprestimoJoao);
        itemJoao2.setLivro(nineteenEightyFour);

        ItemEmprestimo itemMaria = new ItemEmprestimo();
        itemMaria.setEmprestimo(emprestimoMaria);
        itemMaria.setLivro(domCasmurro);

        ItemEmprestimo itemCarlos = new ItemEmprestimo();
        itemCarlos.setEmprestimo(emprestimoCarlos);
        itemCarlos.setLivro(pragmaticProgrammer);

        itemEmprestimoRepository.save(itemJoao1);
        itemEmprestimoRepository.save(itemJoao2);
        itemEmprestimoRepository.save(itemMaria);
        itemEmprestimoRepository.save(itemCarlos);
    }
}