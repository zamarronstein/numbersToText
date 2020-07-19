package net.qs.numerictotext;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

import net.sf.jasperreports.engine.JRScriptletException;

public class Test {
	
	public static void main(String [] args) throws JRScriptletException {
		
		NumeroALetraScriptlet scriptlet = new NumeroALetraScriptlet();
		
		for (int i = 0; i < 10; i++) {
			
			BigDecimal numeroRandom = new BigDecimal(BigInteger.valueOf(new Random().nextInt(10000000)), 0);
			

			System.out.println("Numero " + numeroRandom + " a letra: " + scriptlet.convertir(numeroRandom));
		}
	}
}
