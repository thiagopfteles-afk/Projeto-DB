import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try (Connection conexao = ConexaoDB.conectar()) {
            ProdutoDAO produtoDAO = new ProdutoDAO(conexao);

            mostrarProdutos(produtoDAO);

            Produto novoProduto1 = new Produto("Notebook", 10, 1999.99, "Em estoque");
            Produto novoProduto2 = new Produto("Samsung", 20, 6999.99, "Em estoque");
            Produto novoProduto3 = new Produto("Motorola", 45, 8999.99, "Em estoque");

            produtoDAO.inserir(novoProduto1);
            produtoDAO.inserir(novoProduto2);
            produtoDAO.inserir(novoProduto3);

            mostrarProdutos(produtoDAO);

            Produto produtoConsultado = produtoDAO.consultarPorId(1);
            if (produtoConsultado != null) {
                System.out.println("Produto encontrado: " + produtoConsultado.getNome());
            } else {
                System.out.println("Produto não encontrado");
            }

        } catch (Exception e) {
            System.err.println("Erro geral: " + e.getMessage());
        }
    }

    private static void mostrarProdutos(ProdutoDAO produtoDAO) {
        List<Produto> todosProdutos = produtoDAO.listarTodos();
        if (todosProdutos.isEmpty()) {
            System.out.println("Nenhum produto encontrado");
        } else {
            System.out.println("Lista de produtos:");
            for (Produto p : todosProdutos) {
                System.out.println(p.getId() + ": " + p.getNome() + " - " + p.getPreco());
            }
        } // fechando o else
    }
}