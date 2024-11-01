package br.com.dlei.model;

import java.util.Date;
import java.util.List;

public class User {
	
	private Long id;
	private String nome;	
	private String cpf; 	
	private Date dataNascimento;	
	private String email;
	private String login;
	private String password;		
	private boolean ativo;
	private String telefone;
	private String endereco;
	private String numEndereco;
	private String complEndereco;
	private String bairro;
	private String cidade;
	private String estado;
	private String uf;
	private String cep;
	
	
	
	//gerar relacionamento [ManyToMany - muitos para muitos] entre classes
	private List<Papel> papeis;//Poderá ser ADMIN, USER
	
	
	//Construtores
	public User() {
		super();
	}

	public User(String nome, String cpf, Date dataNascimento, String email, String login, String password,
			boolean ativo, String telefone, String endereco, String numEndereco, String complEndereco, String bairro,
			String cidade, String estado, String uf, String cep) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.email = email;
		this.login = login;
		this.password = password;
		this.ativo = ativo;
		this.telefone = telefone;
		this.endereco = endereco;
		this.numEndereco = numEndereco;
		this.complEndereco = complEndereco;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.uf = uf;
		this.cep = cep;
	}


	//Getters e Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}	

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumEndereco() {
		return numEndereco;
	}

	public void setNumEndereco(String numEndereco) {
		this.numEndereco = numEndereco;
	}

	public String getComplEndereco() {
		return complEndereco;
	}

	public void setComplEndereco(String complEndereco) {
		this.complEndereco = complEndereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}	

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	//Getters e Setters do RELACIONAMENTO entre classes
	public List<Papel> getPapeis() {
		return papeis;
	}

	public void setPapeis(List<Papel> papeis) {
		this.papeis = papeis;
	}

	@Override
	public String toString() {
		return "User [nome=" + nome + ", cpf=" + cpf + ", dataNascimento=" + dataNascimento + ", email=" + email
				+ ", login=" + login + ", password=" + password + ", ativo=" + ativo + ", telefone=" + telefone
				+ ", endereco=" + endereco + ", numEndereco=" + numEndereco + ", complEndereco=" + complEndereco
				+ ", bairro=" + bairro + ", cidade=" + cidade + ", estado=" + estado + ", uf=" + uf + ", cep=" + cep
				+ "]";
	}	
	
}

