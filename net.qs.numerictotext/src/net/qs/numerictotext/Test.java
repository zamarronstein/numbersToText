package net.qs.numerictotext;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

import net.sf.jasperreports.engine.JRScriptletException;

public class Test {
	
	public static void main(String [] args) throws JRScriptletException {
		
		NumeroALetraScriptlet scriptlet = new NumeroALetraScriptlet();
		
		for (int i = 0; i < 10; i++) {
			
			// BigDecimal numeroRandom = new BigDecimal(BigInteger.valueOf(new Random().nextInt(1000000000)), 0);
			
			BigDecimal max = new BigDecimal(10000000);
			BigDecimal diez = new BigDecimal(10);
			BigDecimal randFromDouble = new BigDecimal(Math.random());
			BigDecimal numeroRandom = randFromDouble.multiply(max)
					.multiply(diez)
					.multiply(diez)
					.multiply(diez)
					.multiply(diez)
					.multiply(diez)
					.multiply(diez)
					.multiply(diez)
					.multiply(diez)
					.multiply(diez)
					.multiply(diez)
					.multiply(diez)
					.multiply(diez);
			
			numeroRandom = numeroRandom.setScale(0, BigDecimal.ROUND_DOWN);

			System.out.println("Numero " + numeroRandom + " a letra: " + scriptlet.convertir(numeroRandom));
		}

		// System.out.println("Numero 320313155432543905 a letra: " + scriptlet.convertir(new BigDecimal(320313155432543905L)));
	}
}
