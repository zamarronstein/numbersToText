package net.quarksoft.numeroaletras;

public class Equivalencia {
	private String nombreUnidades;
	private String nombreDecenas;
	private String nombreCentenas;
	
	public Equivalencia(String nombreUnidades) {
		super();
		this.nombreUnidades = nombreUnidades;
	}

	public Equivalencia(String nombreUnidades, String nombreDecenas) {
		super();
		this.nombreUnidades = nombreUnidades;
		this.nombreDecenas = nombreDecenas;
	}

	public Equivalencia(String nombreUnidades, String nombreDecenas, String nombreCentenas) {
		super();
		this.nombreUnidades = nombreUnidades;
		this.nombreDecenas = nombreDecenas;
		this.nombreCentenas = nombreCentenas;
	}

	public String getNombreUnidades() {
		return nombreUnidades;
	}

	public void setNombreUnidades(String nombreUnidades) {
		this.nombreUnidades = nombreUnidades;
	}

	public String getNombreDecenas() {
		return nombreDecenas;
	}

	public void setNombreDecenas(String nombreDecenas) {
		this.nombreDecenas = nombreDecenas;
	}

	public String getNombreCentenas() {
		return nombreCentenas;
	}

	public void setNombreCentenas(String nombreCentenas) {
		this.nombreCentenas = nombreCentenas;
	}
}