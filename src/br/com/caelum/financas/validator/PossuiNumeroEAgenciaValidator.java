package br.com.caelum.financas.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.caelum.financas.modelo.Conta;

public class PossuiNumeroEAgenciaValidator implements ConstraintValidator<PossuiNumeroEAgencia,Conta>{
	
	public void initialize(PossuiNumeroEAgencia anotacao) {
	}
	
	public boolean isValid(Conta conta, ConstraintValidatorContext ctx) {
		return !(conta.getAgencia() == null ^ conta.getNumero() == null);
	}

}
