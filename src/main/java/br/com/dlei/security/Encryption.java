package br.com.dlei.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//vinculada a depedência adicionada no "pom.xml"
import org.apache.commons.codec.binary.Hex;

//responsável por criptografar a senha do usuário
public class Encryption {

	public static String converterParaMD5(String senha) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(senha.getBytes()); // pega a senha passada e gera a criptografia
		byte[] digest = md.digest(); // transforma a cripografia gerada em um Array de bytes
		String resultado = new String(Hex.encodeHex(digest)); // converte o Array de bytes em hexadecimal
		return resultado; // retorna a senha criptografada que é repassada pelo método
	}

	public static boolean compararSenha(String senha, String senhaGravada) throws NoSuchAlgorithmException {
		String resultado = converterParaMD5(senha);
		boolean ehIgual = resultado.equals(senhaGravada);
		return ehIgual;
	}

}