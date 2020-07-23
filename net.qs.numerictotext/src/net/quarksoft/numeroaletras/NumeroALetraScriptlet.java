package net.quarksoft.numeroaletras;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;

public class NumeroALetraScriptlet extends JRDefaultScriptlet {
	
	private final static String CERO = "CERO";
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

	/**
	 * Método constructor que inicializa las equivalencias y las escalas
	 * */
	public NumeroALetraScriptlet() {
		
		super();
		equivalencias = new HashMap<Integer, Equivalencia>();
		escalas = new HashMap<Integer, Escala>();
		initEquivalencias();
		initEscalas();
	}
	
	/**
	 * Método que inicializa las equivalencias de números
	 * */
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
	private void initEscalas() {
		// TODO Auto-generated method stub
		escalas.put(0, new Escala(""));
		escalas.put(1, new Escala("MIL", "MIL"));
		escalas.put(2, new Escala("MILLÓN", "MILLONES"));
		escalas.put(3, new Escala("MIL", "MIL"));
		escalas.put(4, new Escala("BILLÓN", "BILLONES"));
		escalas.put(5, new Escala("MIL", "MIL"));
		escalas.put(6, new Escala("TRILLÓN", "TRILLONES"));
		escalas.put(7, new Escala("MIL", "MIL"));
	}
	
	/**
	 * Método para convertir un número (máximo 18 digitos a letras)
	 * @param numero
	 * */
	public String convertir(BigDecimal numero) throws JRScriptletException{
		
		String numeroConvertido = "";
		
		if (numero != null) {
			
			String strNumero = numero.toString();
			
			if (strNumero.compareTo("0") == 0) {
				numeroConvertido = CERO;
			} else if ((strNumero.length() > 0) && (strNumero.length() <= 24)) {
				
				numeroConvertido = procesar(strNumero);
			}
		}		

		return numeroConvertido.toString();
	}
	
	/**
	 * Método para procesar la cadena a convertir
	 * @param strNumero
	 * */
	private String procesar(String strNumero) {
		
		String conversion = "";
		String reverseNumero = reverse(strNumero); // hacemos el reverse de la cadena
		List<String> clases = separateInClases(reverseNumero); // Separamos las cadenas en clases
		List<String> clasesConvertidas = new ArrayList<>(); // Lista para guardar las clases ya convertidas
		
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
	
	/**
	 * Método para hacer el reverse de una cadena
	 * @param str
	 * */
	private String reverse(String str) {
		
		String reverse = "";
		for(int i = str.length() - 1; i>=0; i--) {
			
			reverse += str.charAt(i);
		}
		
		return reverse;
	}
	
	/**
	 * Método para separar la cadena (al revés) en clases de a tres carácteres cada una
	 * @param reverseNumero
	 * */
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

	/**
	 * Método para convertir un número a texto
	 * @param clase
	 * @para numeroClase
	 * */
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
					text+= " ";
					enterDiezToQuince = Boolean.TRUE;
				} else {
					
					if (decenas == 1 || decenas == 2) {
						
						if (decenas == 2 && unidades == 0) {
							text+= VEINTE + " ";
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
					
					if (unidades == 1 && numeroClase > 0) {
						text+=UN;
					} else {
						
						text+= equivalencias.get(unidades).getNombreUnidades() + " ";
					}
				} else if (decenas >= 3){
					
					if (unidades == 1 && numeroClase > 0) {

						text+= "Y " + UN + " ";
					} else {
						
						text+= "Y " + equivalencias.get(unidades).getNombreUnidades() + " ";
					}
				} else {
					
					if (unidades == 1 && numeroClase > 0) {
						if ((unidades == 1 && decenas == 0 && centenas == 0) && (numeroClase == 1)) {
							
							text+="";
						} else {
							
							text+=UN + " ";
						}
					} else {
						text+= equivalencias.get(unidades).getNombreUnidades() + " ";
					}
				}
			}
			
			text+= getNombreEscala(unidades, decenas, centenas, numeroClase);
		} catch (Exception e) {
			
		}

		return text;
	}

	/**
	 * Método para obtener el nombre de la escala, ya sea miles, millones, billones, etcétera, 
	 * dependiendo del número de clase
	 * */
	private String getNombreEscala(Integer unidades, Integer decenas, Integer centenas, Integer numeroClase) {

		String escala = "";

		if ((unidades == 1) && (decenas == 0) && (centenas == 0) && (numeroClase > 0)) {
			escala = escalas.get(numeroClase).getNombreSingular();
		} else if (numeroClase == 1) {
			
			escala = escalas.get(numeroClase).getNombreSingular();
		} else {
			
			if (escalas.get(numeroClase).getNombrePlural() != null) {
				
				escala = escalas.get(numeroClase).getNombrePlural();
			} else {
				escala = "";
			}
		}
	
		return escala;
	}
}
