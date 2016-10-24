package modelo;
import java.util.Date;
public class Intento {
	private int intento;
	private Date fecha;
	public int getIntento() {
		return intento;
	}
	public void setIntento(int intento) {
		this.intento = intento;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Intento(int intento, Date fecha) {
		super();
		this.intento = intento;
		this.fecha = fecha;
	}
}
