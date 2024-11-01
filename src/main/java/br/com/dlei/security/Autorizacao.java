package br.com.dlei.security;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Autorizacao {
	private Map<String, List<String>> auth = new HashMap<String, List<String>>();

	public Autorizacao() {
		auth.put("ADMIN", Arrays.asList(new String[] { "/auth/user", "/auth/biblio", "/auth/admin" }));
		auth.put("USER", Arrays.asList(new String[] { "/auth/user" }));
	}

	public boolean temAutorizacao(DetalheUsuario detalheUsuario, String path) {
		boolean autorizado = false;
		for (String papelUsuario : detalheUsuario.getPapeis()) {
			List<String> confPapel = auth.get(papelUsuario);
			for (String url : confPapel) {
				if (path.contains(url)) {
					autorizado = true;
					break;
				}
			}
			if (autorizado)
				break;
		}
		return autorizado;
	}

	public String indexPerfil(DetalheUsuario detalheUsuario) {
		String redirectURL = "";
		if (verificarPerfil(detalheUsuario, "ADMIN")) {
			redirectURL = "auth/admin/admin-index.jsp";
		} else if (verificarPerfil(detalheUsuario, "USER")) {
			redirectURL = "auth/user/user-index.jsp";
		}
		return redirectURL;
	}

	private boolean verificarPerfil(DetalheUsuario detalheUsuario, String papel) {
		boolean temPerfil = detalheUsuario.getPapeis().contains(papel);
		return temPerfil;
	}

}
