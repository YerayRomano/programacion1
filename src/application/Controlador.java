package application;

import javafx.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import control.Ejercicios1;
import javafx.fxml.FXML;
import javafx.*;
import javafx.scene.control.TextField;
import modelo.Intento;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import control.Hebra;

public class Controlador {
	@FXML
	TextField minimo;
	@FXML
	TextField maximo;
	@FXML
	TextField intento;
	@FXML
	TextArea consola;
	@FXML
	TextArea intentos;
	@FXML
	Button jugar;
	@FXML
	Button rendirse;
	@FXML
	Button probar;
	private int objetivo;
	private int[] numeros;
	private long comienzo;
	private Intento lodicho;
	private int intento1 = 1;
	private Clip clippy;

	public void manejaBoton(ActionEvent evento) {
		if (this.numeros == null) {
			int minimu = 0;
			int maximu = 0;
			boolean vontade = true;
			try {
				minimu = Integer.parseInt(minimo.getText());
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				consola.setText(consola.getText() + "\nerror no has puesto un minimo");
				vontade = false;
			}
			try {
				maximu = Integer.parseInt(maximo.getText());
			} catch (NumberFormatException e) {
				consola.setText(consola.getText() + "\nerror no has puesto un maximo");
				vontade = false;
			}
			if (vontade == true) {
				Ejercicios1 ej1 = new Ejercicios1();
				if (minimu == maximu) {
					consola.setText(consola.getText() + "\nerror el minimo y el maximo son iguales");
				} else {
					this.numeros = new int[2];
					this.numeros[0] = minimu;
					this.numeros[1] = maximu;
					Arrays.sort(this.numeros);
					this.objetivo = ej1.generarNumero(this.numeros[0], this.numeros[1]);
					comienzo = System.nanoTime();
					consola.setText(consola.getText() + "\nnumero generado");
					String soundName = "reloj.wav";
					jugar.setDisable(true);
					probar.setDisable(false);
					rendirse.setDisable(false);
					this.reproducir(soundName, 1);
				}
			}
		}

	}

	public void manejaSalir(ActionEvent evento) {
		if (this.clippy != null) {
			clippy.close();
		}
		System.exit(-1);
	}

	public void limpiarCuadro(ActionEvent evento) {
		consola.setText("");
	}

	public void limpiarIntentos(ActionEvent evento) {
		intentos.setText("");
	}

	public void intentarJugar(ActionEvent evento) {
		if (this.numeros != null) {
			try {
				Integer.parseInt(intento.getText());
				this.JugarClassOfIllusion(this.numeros[0], this.numeros[1]);
			} catch (NumberFormatException e) {
				consola.setText(consola.getText() + "\nError, el intento no es un numero");
			}
		} else {
			consola.appendText("\nError, no has puesto un intervalo");
		}
	}

	private void JugarClassOfIllusion(int minimo, int maximo) {
		int elintento = Integer.parseInt(intento.getText());
		lodicho = new Intento(elintento, new Date());
		intentos.appendText("intento nº" + intento1 + " has intentado el numero: " + lodicho.getIntento()
				+ " a fecha de " + lodicho.getFecha().toString() + "\n");
		intento1++;
		if (elintento == this.objetivo) {
			consola.setText(consola.getText() + "\nfelicidades, el numero era el " + elintento);
			long finale = (System.nanoTime() - this.comienzo) / 1000000000;
			if (this.clippy != null) {
				clippy.close();
			}
			consola.setText(consola.getText() + "\nhas tardado " + finale + " segundo/s");
			if (this.clippy != null) {
				clippy.close();
			}
			jugar.setDisable(false);
			probar.setDisable(true);
			rendirse.setDisable(true);
			this.numeros = null;
			this.objetivo = 0;
		} else {
			if (elintento < this.objetivo) {
				consola.setText(consola.getText() + "\nError, te has quedado corto");
			} else {
				consola.setText(consola.getText() + "\nError, te has quedado largo");
			}
		}
	}

	public void rendrise(ActionEvent evento) {
		if (this.objetivo != 0) {
			consola.appendText("\nte has rendido, el numero era el: " + this.objetivo);
			this.objetivo = 0;
			if (this.clippy != null) {
				clippy.close();
			}
			this.numeros = null;
			jugar.setDisable(false);
			probar.setDisable(true);
			rendirse.setDisable(true);
			this.reproducir("nelson.wav");
		} else {
			consola.appendText("\nno estas jungado, no tienes de que rendirte");
		}
	}

	public void reproducir(String soundName) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
			Clip clippy = AudioSystem.getClip();
			clippy.open(audioInputStream);
			clippy.start();
		} catch (UnsupportedAudioFileException e) {

		} catch (IOException e) {

		} catch (LineUnavailableException e) {

		}
	}

	public void reproducir(String soundName, int loop) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
			this.clippy = AudioSystem.getClip();
			clippy.open(audioInputStream);
			if (loop == 0) {
				clippy.start();
			} else {
				clippy.loop(50);
			}
		} catch (UnsupportedAudioFileException e) {

		} catch (IOException e) {

		} catch (LineUnavailableException e) {

		}
	}
}
