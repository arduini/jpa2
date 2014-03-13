package br.com.caelum.financas.teste;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import br.com.caelum.financas.infra.ValidatorUtil;
import br.com.caelum.financas.modelo.Conta;

public class TestaValidacaoNumeroEAgencia {
	public static void main(String[] args) {
		
		Validator validator = new ValidatorUtil().getValidator();
		
		Conta conta = new Conta();
		conta.setAgencia("uma agência qualquer.");
		
		Set<ConstraintViolation<Conta>> erros = validator.validate(conta);
		for(ConstraintViolation<Conta> erro : erros) {
			System.out.println("Erro: " + erro.getPropertyPath() + " - "+ erro.getMessage());
		}

		System.out.println("Fim");
	}
}
