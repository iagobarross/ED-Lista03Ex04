package model;

public class Cliente {

	private String CPF;
	private String nome;
	private String idade;
	private String limiteCredito;

	public Cliente(String CPF, String nome, String idade, String limiteCredito) {
		this.CPF = CPF;
		this.nome = nome;
		this.idade = idade;
		this.limiteCredito = limiteCredito;
	}

	public String getCPF() {
		return CPF;
	}

	public String getNome() {
		return nome;
	}

	public String getIdade() {
		return idade;
	}

	public String getLimiteCredito() {
		return limiteCredito;
	}

}
