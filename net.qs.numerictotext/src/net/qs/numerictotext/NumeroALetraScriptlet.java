package net.qs.numerictotext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;

public class NumeroALetraScriptlet extends JRDefaultScriptlet {
	
	private final static Integer TAMANIO_PERIODOS = 6;
	private final static Integer TAMANIO_CLASE = 3;
	private final static String UN = "UN";
	private final static String DIEZ = "DIEZ";
	private final static String ONCE = "ONCE";
	private final static String DOCE = "DOCE";
	private final static String TRECE = "TRECE";
	private final static String CATORCE = "CATORCE";
	private final static String QUINCE = "QUINCE";
	private final static String VEINTE = "VEINTE";

	private Map<Integer, Equivalencia> equivalencias;
	private Map<Integer, Escala> escalas;

	public NumeroALetraScriptlet() {
		
		super();
		equivalencias = new HashMap<Integer, Equivalencia>();
		escalas = new HashMap<Integer, Escala>();
		initEquivalencias();
		initUnidades();
	}
	
	private void initEquivalencias() {
		equivalencias.clear();
		//equivalencias.put(0, new Equivalencia("CERO", ""));
		equivalencias.put(1, new Equivalencia("UNO", "DIECI", "CIENTO"));
		equivalencias.put(2, new Equivalencia("DOS", "VEINTI", "DOSCIENTOS"));
		equivalencias.put(3, new Equivalencia("TRES", "TREINTA", "TRECIENTOS"));
		equivalencias.put(4, new Equivalencia("CUATRO", "CUARENTA", "CUATROCIENTOS"));
		equivalencias.put(5, new Equivalencia("CINCO", "CINCUENTA", "QUINIENTOS"));
		equivalencias.put(6, new Equivalencia("SEIS", "SESENTA", "SEISCIENTOS"));
		equivalencias.put(7, new Equivalencia("SIETE", "SETENTA", "SETECIENTOS"));
		equivalencias.put(8, new Equivalencia("OCHO", "OCHENTA", "OCHOCIENTOS"));
		equivalencias.put(9, new Equivalencia("NUEVE", "NOVENTA", "NOVECIENTOS"));
	}
	
	
	/**
	 * Método para inicializar el tipo de unidades
	 * Por ejemplo: unidades de millar, unidades de millón, etc.
	 * */
	private void initUnidades() {
		// TODO Auto-generated method stub
		escalas.put(0, new Escala(""));
		escalas.put(1, new Escala("MIL", "MILES"));
		escalas.put(2, new Escala("MILLÓN", "MILLONES"));
		escalas.put(3, new Escala("MIL MILLONES"));
		escalas.put(4, new Escala("BILLÓN", "BILLONES"));
		escalas.put(5, new Escala("MIL BILLONES"));
	}
	
	/**
	 * Método para convertir un número (máximo 18 digitos a letras)
	 * @param numero
	 * */
	public String convertir(BigDecimal numero) throws JRScriptletException{
		
		String numeroConvertido = "";
		
		if (numero != null) {
			
			String strNumero = numero.toString();

			if ((strNumero.length() > 0) && (strNumero.length() <= 18)) {
				
				numeroConvertido = procesar(strNumero);
			}
		}		

		return numeroConvertido.toString();
	}
	
	private String procesar(String strNumero) {
		
		String conversion = "";
		String reverseNumero = reverse(strNumero);
		List<String> clases = separateInClases(reverseNumero);
		List<String> clasesConvertidas = new ArrayList<>();
		
		if (!clases.isEmpty()) {
			for (int i = 0; i < clases.size(); i++) {
				String text =  numberToText(clases.get(i), i);
				clasesConvertidas.add(text);
			} 
		}
		
		if (!clasesConvertidas.isEmpty()) {
			
			for (int i = clasesConvertidas.size() - 1; i >=0; i--) {
				
				conversion += clasesConvertidas.get(i) + " ";
			}
		}
		
		return conversion;
	}
	
	private String reverse(String str) {
		
		String reverse = "";
		for(int i = str.length() - 1; i>=0; i--) {
			
			reverse += str.charAt(i);
		}
		
		return reverse;
	}
	
	private List<String> separateInClases(String reverseNumero) {

		List<String> clases = new ArrayList<>();

		for(int i = 0; i < reverseNumero.length(); i+=3) {
			//System.out.println(">>> " + i + " - " + (i+2));
			if ((i + 3) >= reverseNumero.length() ) // es el ultimo
			{
				clases.add(reverse(reverseNumero.substring(i)));
			} else {
				clases.add(reverse(reverseNumero.substring(i, i+3)));
			}
		}
		
		return clases;
	}

	private String numberToText(String clase, Integer numeroClase) {
		// TODO Auto-generated method stub
		String text = "";
		
		Boolean enterDiezToQuince = Boolean.FALSE;

		try {
	
			int numero = Integer.parseInt(clase);
			int centenas = numero / 100;

			if (centenas > 0) {
				
				numero -= centenas * 100;
			}
			
			int decenas = numero / 10;
			
			if (decenas > 0) {
				numero -= decenas * 10;
			}

			int unidades = numero;
			
			if (centenas > 0) {
				text+= equivalencias.get(centenas).getNombreCentenas() + " ";
			}
			
			if (decenas > 0) {
				
				if (decenas == 1 && ((unidades >= 0) && (unidades <=5))) {
					switch(decenas *10 + unidades) {
					
						case 10: text+=DIEZ; break;
						case 11: text+=ONCE; break;
						case 12: text+=DOCE; break;
						case 13: text+=TRECE; break;
						case 14: text+=CATORCE; break;
						case 15: text+=QUINCE; break;
					}
					enterDiezToQuince = Boolean.TRUE;
				} else {
					
					if (decenas == 1 || decenas == 2) {
						
						if (decenas == 2 && unidades == 0) {
							text+= equivalencias.get(2).getNombreDecenas();
						} else {
							
							text+=equivalencias.get(decenas).getNombreDecenas();
						}
					} else {
						
						text+=equivalencias.get(decenas).getNombreDecenas() + " ";
					}
				}
			}
			
			if (!enterDiezToQuince &&  unidades > 0) {
				
				if (decenas == 1 || decenas == 2) {
					
					text+= equivalencias.get(unidades).getNombreUnidades();
				} else if (decenas >= 3){
					
					text+= "Y " + equivalencias.get(unidades).getNombreUnidades();
				} else if (unidades == 1 && numeroClase == 1) {
						
						text+= UN;
					} else {
						
						text+= equivalencias.get(unidades).getNombreUnidades();
					}
				}
			text += " " + getNombreEscala(numeroClase);
			
		} catch (Exception e) {
			
		}

		return text;
	}

	private String getNombreEscala(Integer numeroClase) {
		// TODO Auto-generated method stub
		return escalas.get(numeroClase).getNombreSingular();
	}
}
