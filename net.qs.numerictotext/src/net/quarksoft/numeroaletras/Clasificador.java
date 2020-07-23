package net.quarksoft.numeroaletras;

public class Clasificador {
	Integer unidades;
	Integer decenas;
	Integer centenas;
	
	public Clasificador(Integer unidades) {
		super();
		this.unidades = unidades;
		this.decenas = 0;
		this.centenas = 0;
	}
	
	public Clasificador(String strUnidades) {
		super();
		try {
			this.unidades = Integer.parseInt(strUnidades);
		} catch(Exception e) {
			this.unidades = 0;
		}
	}
	
	public Clasificador(Integer unidades, Integer decenas) {
		super();
		this.unidades = unidades;
		this.decenas = decenas;
		this.centenas = 0;
	}
	
	
	public Clasificador(String strUnidades, String strDecenas) {
		super();
		try {
			this.unidades = Integer.parseInt(strUnidades);
			this.decenas = Integer.parseInt(strDecenas);
		} catch(Exception e) {
			this.unidades = 0;
			this.decenas = 0;
		}
		this.centenas = 0;
	}
	
	public Clasificador(Integer unidades, Integer decenas, Integer centenas) {
		super();
		this.unidades = unidades;
		this.decenas = decenas;
		this.centenas = centenas;
	}
	
	public Clasificador(String strUnidades, String strDecenas, String strCentenas) {
		super();
		try {
			this.unidades = Integer.parseInt(strUnidades);
			this.decenas = Integer.parseInt(strDecenas);
			this.centenas = Integer.parseInt(strCentenas);
		} catch(Exception e) {
			this.unidades = 0;
			this.decenas = 0;
			this.centenas = 0;
		}
	}

	public Integer getUnidades() {
		return unidades;
	}

	public void setUnidades(Integer unidades) {
		this.unidades = unidades;
	}

	public Integer getDecenas() {
		return decenas;
	}

	public void setDecenas(Integer decenas) {
		this.decenas = decenas;
	}

	public Integer getCentenas() {
		return centenas;
	}

	public void setCentenas(Integer centenas) {
		this.centenas = centenas;
	}
}