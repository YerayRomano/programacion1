package modelo;

import java.util.Date;

public class Estudiante extends Persona {
	private int grupo;
    private String fecha_ingreso;
	public String getFecha_ingreso() {
		return fecha_ingreso;
	}

	public void setFecha_ingreso(String fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}

	public Estudiante(String nif, String nombre, char sexo, Date fecha, int grupo,String fecha_ingreso) {
		super(nif, nombre, sexo, fecha);
		this.grupo = grupo;
		this.fecha_ingreso = fecha_ingreso;
	}

	public int getGrupo() {
		return grupo;
	}

	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}

}
