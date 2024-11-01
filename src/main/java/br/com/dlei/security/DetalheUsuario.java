package br.com.dlei.security;

import java.util.ArrayList;
import java.util.List;

import br.com.dlei.model.Papel;
import br.com.dlei.model.User;

public class DetalheUsuario {

	private User usuario;

	private List<String> papeis;

	public DetalheUsuario(User usuario) {
		this.usuario = usuario;
		this.papeis = new ArrayList<>();
		for (Papel p : usuario.getPapeis()) {
			this.papeis.add(p.getTipoPapel());
		}
	}

	// Ponte com a classe model/User
	public boolean isAtivo() {
		return usuario.isAtivo();
	}

	// Ponte com a classe model/User
	public String getNome() {
		return usuario.getNome();
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	public List<String> getPapeis() {
		return papeis;
	}

	public void setPapeis(List<String> papeis) {
		this.papeis = papeis;
	}

}
