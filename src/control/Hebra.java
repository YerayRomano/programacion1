package control;
import application.Controlador;
public class Hebra extends Thread {
	public void run() {
			Controlador controlla = new Controlador();
			System.out.println("maricon");
			controlla.reproducir("reloj.wav",1);
	}
}
