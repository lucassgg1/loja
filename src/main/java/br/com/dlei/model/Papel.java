package br.com.dlei.model;

import java.util.List;

public class Papel {

	private Long id;
	private String tipoPapel;
	private List<User> usuarios;// Listará os usuários por papel (ADMIN, USER) - ponte com a [classe model/User] 

	// Construtores
	public Papel() {
		
	}

	public Papel(Long id, String tipoPapel) {
		super();
		this.id = id;
		this.tipoPapel = tipoPapel;
	}

	// Getters e Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoPapel() {
		return tipoPapel;
	}

	public void setTipoPapel(String tipoPapel) {
		this.tipoPapel = tipoPapel;
	}

	//ponte com a [classe model/User]
	public List<User> getUsuarios() {
		return usuarios;
	}

	//ponte com a [classe model/User]
	public void setUsuarios(List<User> usuarios) {
		this.usuarios = usuarios;
	}

}
