import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProdutoGUI extends Application {
    private ProdutoDAO produtoDAO;
    private ObservableList<Produto> produtos;
    private TableView<Produto> tableView;
    private TextField nomeInput, quantidadeInput, precoInput;
    private ComboBox<String> statusComboBox;
    private Connection conexaoDB;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage palco) {
        conexaoDB = ConexaoDB.conectar();
        produtoDAO = new ProdutoDAO(conexaoDB);
        produtos = FXCollections.observableArrayList(produtoDAO.listarTodos());

        palco.setTitle("Gerenciamento de Estoque");

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setSpacing(10);

        HBox nomeProdutoBox = new HBox();
        nomeProdutoBox.setSpacing(10);
        Label nomeLabel = new Label("Produto:");
        nomeInput = new TextField();
        nomeProdutoBox.getChildren().addAll(nomeLabel, nomeInput);

        HBox quantidadeBox = new HBox();
        quantidadeBox.setSpacing(10);
        Label quantidadeLabel = new Label("Quantidade");
        quantidadeInput = new TextField();
        quantidadeBox.getChildren().addAll(quantidadeLabel, quantidadeInput);

        HBox precoBox = new HBox();
        precoBox.setSpacing(10);
        Label precoLabel = new Label("Preço");
        precoInput = new TextField();
        precoBox.getChildren().addAll(precoLabel, precoInput);

        HBox statusBox = new HBox();
        statusBox.setSpacing(10);
        Label statusLabel = new Label("Status");
        statusComboBox = new ComboBox<>();
        statusComboBox.getItems().addAll("Estoque Normal", "Estoque Baixo");
        statusBox.getChildren().addAll(statusLabel, statusComboBox);

        Button addButton = new Button("Adicionar");
        addButton.setOnAction(e -> {
            String preco = precoInput.getText().replace(',', '.');
            Produto produto = new Produto(nomeInput.getText(),
                    Integer.parseInt(quantidadeInput.getText()),
                    Double.parseDouble(preco),
                    statusComboBox.getValue());

            produtoDAO.inserir(produto);
            produtos.setAll(produtoDAO.listarTodos());
            limparCampos();
        });

        Button updateButton = new Button("Atualizar");
        updateButton.setOnAction(e -> {
            Produto selectedProduto = tableView.getSelectionModel().getSelectedItem();
            if (selectedProduto != null) {
                selectedProduto.setNome(nomeInput.getText());
                selectedProduto.setQuantidade(Integer.parseInt(quantidadeInput.getText()));
                String preco = precoInput.getText().replace(',', '.');
                selectedProduto.setPreco(Double.parseDouble(preco));
                selectedProduto.setStatus(statusComboBox.getValue());
                produtoDAO.atualizar(selectedProduto);
                produtos.setAll(produtoDAO.listarTodos());
                limparCampos();
            }
        });

        Button deleteButton = new Button("Excluir");
        deleteButton.setOnAction(e -> {
            Produto selectedProduto = tableView.getSelectionModel().getSelectedItem();
            if (selectedProduto != null) {
                produtoDAO.excluir(selectedProduto.getId());
                produtos.setAll(produtoDAO.listarTodos());
                limparCampos();
            }
        });

        Button clearButton = new Button("Limpar");
        clearButton.setOnAction(e -> limparCampos());

        tableView = new TableView<>();
        tableView.setItems(produtos);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        List<TableColumn<Produto, ?>> columns = List.of(
                criarColuna("ID", "id"),
                criarColuna("Produto", "nome"),
                criarColuna("Quantidade", "quantidade"),
                criarColuna("Preço", "preco"),
                criarColuna("Status", "status")
        );

        tableView.getColumns().addAll(columns);
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                nomeInput.setText(newSelection.getNome());
                quantidadeInput.setText(String.valueOf(newSelection.getQuantidade()));
                precoInput.setText(String.valueOf(newSelection.getPreco()));
                statusComboBox.setValue(newSelection.getStatus());
            }
        });

        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        buttonBox.getChildren().addAll(addButton, updateButton, deleteButton, clearButton);

        vbox.getChildren().addAll(nomeProdutoBox, quantidadeBox, precoBox, statusBox, buttonBox, tableView);

        Scene scene = new Scene(vbox, 800, 600);
        scene.getStylesheets().add("styles-produtos.css");
        palco.setScene(scene);
        palco.show();
    }

    @Override
    public void stop() {
        try {
            conexaoDB.close();
        } catch (SQLException e) {
            System.err.println("Erro ao fechar a conexão: " + e.getMessage());
        }
    }

    private void limparCampos() {
        nomeInput.clear();
        quantidadeInput.clear();
        precoInput.clear();
        statusComboBox.setValue(null);
    }

    private TableColumn<Produto, String> criarColuna(String title, String property) {
        TableColumn<Produto, String> col = new TableColumn<>(title);
        col.setCellValueFactory(new PropertyValueFactory<>(property));
        return col;
    }
}