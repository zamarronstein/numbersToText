package net.qs.numerictotext;

class Escala {

	String nombreSingular;
	String nombrePlural;
	
	public Escala(String nombreSingular) {
		super();
		this.nombreSingular = nombreSingular;
	}
	
	public Escala(String nombreSingular, String nombrePlural) {
		super();
		this.nombreSingular = nombreSingular;
		this.nombrePlural = nombrePlural;
	}

	public String getNombreSingular() {
		return nombreSingular;
	}

	public void setNombreSingular(String nombreSingular) {
		this.nombreSingular = nombreSingular;
	}

	public String getNombrePlural() {
		return nombrePlural;
	}

	public void setNombrePlural(String nombrePlural) {
		this.nombrePlural = nombrePlural;
	}
}