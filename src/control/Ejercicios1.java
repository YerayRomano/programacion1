package control;
import java.util.Scanner;
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
		int numa [] = this.validarNumeros();
		this.juegoNumeros(numa);
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
	public int [] validarNumeros() {
		Scanner lopuesto = new Scanner(System.in);
		int primero;
		int segundo;
		int paso;
		String entrada;
		int medio;
		int losnum [] = new int [2];
		boolean correcto = false;
		paso = 0;
		primero = 0;
		segundo = 0;
		boolean enprueba = false;
		String [] lasentradas = new String [2];
		while(correcto == false) {
			if(enprueba == false) {
				System.out.println("pon el intrevalo con el fomrato min;max>");
				entrada = lopuesto.nextLine();
				lasentradas = entrada.split(";");
				System.out.println(lasentradas [0]);
				if(lasentradas.length == 2) {
					enprueba = true;
				}
			}
			switch(paso) {
			case 0:
				try {
					primero = Integer.parseInt(lasentradas [0]);
					if(primero != 0) {
						paso++;
					}
				} catch(NumberFormatException e) {
					paso = 0;
					enprueba = false;
					System.out.println("Error, el primer parametro no es un numero");
				}
			break;
			case 1:
				try {
					segundo = Integer.parseInt(lasentradas [1]);
					if(primero != 0) {
						paso++;
						if(primero == segundo) {
							System.out.println("Error, los dos numeros son iguales");
							paso = 0;
							enprueba = false;
						}
						if(primero > segundo) {
							medio = segundo;
							segundo = primero;
							primero = medio;
						}
						if(paso == 2) {
							correcto = true;
						}
					}
				} catch(NumberFormatException e) {
					paso = 0;
					enprueba = false;
					System.out.println("Error, el segundo parametro no es un numero");
				}
			break;
			}
		}
		losnum [0] = primero;
		losnum [1] = segundo;
		return losnum;
	}
	public void juegoNumeros(int [] numeros) {
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
