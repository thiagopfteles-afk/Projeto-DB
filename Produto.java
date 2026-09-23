public class Produto {
	private int id;
	private String nome;
	private int quantidade;
	private double preco;
	private String status;

	public Produto(String nome, int quantidade, double preco, String status) {
		this.nome = nome;
		this.quantidade= quantidade;
		this.preco = preco;
		this.status = status;
	}

	public Produto(){    // construtor vazio para criar um objeto 

	}

	public int getI(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome){
		this.nome = nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade= quantidade;
	}

	public double getPreco(){
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}	