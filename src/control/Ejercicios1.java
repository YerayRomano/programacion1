package control;
import java.util.Scanner;
import javafx.scene.control.TextArea;
import java.io.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import modelo.Intento;
public class Ejercicios1 {

	public static void main(String[] args) {
		Ejercicios1 ej1 = new Ejercicios1();

		/*
		 * for (int i=0; i < 100 ; i++) ej1.lanzarDado();
		 */
		// int x = 1000;//
		// ej1.listaNPrimerosEnteros(x);
		// ej1.bucleReloj();
		// int x = 134;
		// int y = 138;

		// int resultado = ej1.sumaIntervalo(x, y);

		// System.out.println(" Suma en intervalo " + x + ", " + y + " es " +
		// resultado);
		// int z = 50;
		// ej1.fibonacci(z);
		/*
		 * int cuantasLanzadas = 500; int[] salida =
		 * ej1.lanzadas(cuantasLanzadas); ej1.mostrarLanzadas(salida,
		 * cuantasLanzadas);
		 */
		// int numero = 7771;
		// ej1.listarPrimos(100);
		//ej1.crearHebras(3);
		//ej1.juegoNumero();
		ej1.JugarAdivinar();
	}
	public void convierteAEnteros() {
		String [] numeros ={"213","123","125","458","fefa"};
		for(int i=0;i<numeros.length;i++) {
			try {
				Integer.parseInt(numeros [i]);
			} catch (NumberFormatException e){
				e.printStackTrace();
			}
		}
	}
	public void JugarAdivinar() {
		//int numa [] = this.validarNumeros();
		//this.juegoNumeros(numa);
	}
	public void crearHebras(int cuantas) {
		for (int i = 0; i < cuantas; i++) {
			Thread hebra = new Hebra();
			hebra.setName("Hebra" + i);
			hebra.start();
		}
		System.out.println("creadas las hebras");

	}

	public void creaListaPersonas() {
		// crea 3 personas
		// define el array para 20 personas
		// asigna las personas al array
		// muestra el nif de las 3 personas
	}

	public void listarPrimos(int cuantos) {
		// int contador = 0;
		int candidato = 1;
		// while (contador < cuantos) {
		for (int contador = 0; contador < cuantos; contador++) {
			if (esPrimo(candidato))
				System.out.println(candidato);
			candidato++;

		}

	}

	public boolean esPrimo(int numero) {
		for (int i = 2; i < numero; i++) {
			if (numero % i == 0) {
				// System.out.println(numero + " NO es primo !!" + i);

				return false;
			}

		}
		// System.out.println(numero + " es primo !!");
		return true;

	}

	public void fibonacci(int n) {
		float a, b, c;
		a = 0;
		b = 1;

		/*
		 * for (int i = 0; i < n; i++) { c=a+b; System.out.print(c + ", "); a=b;
		 * }
		 */
		int i = 0;
		while (i < n) {
			c = a + b;
			System.out.print(c + ", " + "\n");
			a = b;
			b = c;
			i++;
		}

	}
	public String [] validarNumeros(int min,int max,TextArea lapantalla) {
		int primero;
		int segundo;
		int paso;
		int medio;
		String [] errores = new String [18];
		int cont = 0;
		int losnum [] = new int [2];
		boolean correcto = false;
		paso = 0;
		primero = 0;
		segundo = 0;
		while(correcto == false) {
			switch(paso) {
			case 0:
					primero = min;
					if(primero != 0) {
						paso++;
					} else {
						//consola.setText(consola.getText()+"\nerror no has puesto un minimo");"Error, debes de poner un mínimo";
						cont++;
						break;
					}
			break;
			case 1:
					segundo = max;
					if(segundo != 0) {
						paso++;
						if(primero == segundo) {
							errores [cont] = "Error, los dos numeros son iguales";
							cont++;
							paso = 0;
							break;
						}
						if(primero > segundo) {
							medio = segundo;
							segundo = primero;
							primero = medio;
						}
						if(paso == 2) {
							correcto = true;
						}
					} else {
						errores [cont] = "Error, debes de poner un maximo";
					}
					cont = 0;
			break;
			}
		}
		if(cont != 0) {
			return errores;
		} else {
			return null;
		}
	}
	public void juegoNumeros(int [] numeros) {
		String tunombre = "";
		long puntua = 0;
		System.out.printf("ok, jugaremos en el intervalo [%d,%d]\npuedes escribir salir para salir\n",numeros [0],numeros [1]);
		Intento [] lodicho = new Intento [200];
		String entrada = "";
		if(numeros.length != 2) {
			System.out.println("Error, array no valido");
		}
		Scanner lopuesto = new Scanner(System.in);
		int objetivo = this.generarNumero(numeros [0], numeros [1]);
		long principio_t = System.nanoTime();
		long final_t;
		long finaliza;
		int intento = 0;
		while(!entrada.equals("salir")) {
			boolean correcto = true;
			System.out.println("numero>");
			entrada = lopuesto.nextLine();
			int elintento = 0;
			if(!entrada.equals("salir")) {
				try {
					elintento = Integer.parseInt(entrada);
				} catch(NumberFormatException e) {
					correcto = false;
					System.out.println("Error, no es un numero");
				}
				if(correcto == true) {
					if(intento < 199) {
						intento = 0;
					}
					lodicho [intento] = new Intento(elintento,new Date());
					intento++;
					if(elintento == objetivo) {
						System.out.println("felicidades, el numero era el "+elintento);
						final_t = System.nanoTime();
						finaliza = final_t - principio_t;
						finaliza /= 1000000000;
						System.out.println("has terminado en "+finaliza+" segundos");
						boolean facil = false;
						String lalinea;
						String [] loscampos = new String [3];
						FileReader fite;
						BufferedReader buffa;
						Intento [] losmejores;
						String [] nombres;
						int [] medio;
						try {
							fite = new FileReader("puntua.txt");
							buffa = new BufferedReader(fite);
							losmejores = new Intento [10];
							nombres = new String [10];
							medio = new int [2];
							int cont = 0;
							while(true) {
								try {
									lalinea = buffa.readLine();
									loscampos = lalinea.split("@");
									if(loscampos.length != 2) {
										facil = true;
										System.out.println("el fichero de puntauciones está corrupto, van a pagar justos por pecadores");
										File loborrado = new File("puntua.txt");
										loborrado.delete();
									} else {
										nombres [cont] = loscampos [0];
										try {
											medio [0] = Integer.parseInt(loscampos [1]);
										} catch(NumberFormatException e) {
											System.out.println("error, puntuacion no numerica, fichero corrupto, van a pagar justos por pecadores");
											facil = true;
											break;
										}
										
									}
									losmejores [cont++] = new Intento(medio [0],null);
									if(cont == 10) {
										break;
									}
								} catch (IOException e) {
									break;
								}
								if(facil == false) {
									Arrays.sort(losmejores);
								}
								puntua = finaliza;
								boolean listo = true;
								try {
									buffa.close();
									fite.close();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									listo = false;
								}
								if(listo == true) {
									this.elRecord(tunombre,puntua);
								}
							}
						} catch(FileNotFoundException e) {
							System.out.println("Eres el primero en jugar, tienes el record asegurado");
							this.elRecord(tunombre,puntua);
							facil = true;
						}
						break;
					}
					if(elintento < objetivo) {
						System.out.println("error, el numero es demasiado corto, prueba uno mas alto");
					}
					if(elintento > objetivo) {
						System.out.println("error, el numero es demasiado largo, prueba uno mas bajo");
					}
				}
			}
		}
	}
	public void elRecord(String sunombre,long puntua) {
		try {
			Scanner lopuesto = new Scanner(System.in);
			FileWriter lotuyo = new FileWriter("puntuta.txt");
			String tunombre = "";
			boolean valido = false;
			while(valido == false) {
				System.out.println("tu nombre>");
				tunombre = lopuesto.nextLine();
				if(tunombre.length() != 0) {
					valido = true;
				} else {
					System.out.println("Error, no puedes poner un nombre vacio");
				}
			}
			lotuyo.write(sunombre + "@"+puntua+"\n");
			lotuyo.close();
		} catch (IOException e) {
			System.out.println("Error, no se pudo abrir el fichero para escritura");
		}
	}
	public int lanzarDado() {

		int valor = (int) (1 + Math.random() * 6);
		// System.out.println(valor);
		return valor;

	}
	public int generarNumero(int min,int max) {
		return min + new Random().nextInt(max-min);
	}
	public void listaNPrimerosEnteros(int n) {
		for (int i = 0; i < n; i++)
			System.out.println(i + 1);

	}

	public void bucleReloj() {
		int h = 0, m = 0, s = 0;
		while (h < 24) // bucle de horas
		{
			m = 0;
			while (m < 60) {
				s = 0;
				while (s < 60) {
					System.out.println(h + " : " + m + " : " + s);
					s++;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				m++; // m= m + 1;
			}
			h++;
		}

	}

}
