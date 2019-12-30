package paquete;
import java.util.LinkedList;


public class Lances {
    private Salida salida;
	private Pareja pareja1;
	private Pareja pareja2;
    private Jugador jugMano;


    public void setSalida(Salida salida){
        this.salida = salida;

    }

    public void setData(Pareja pareja1, Pareja pareja2){
        this.pareja1 = pareja1;
        this.pareja2 = pareja2;

    }

    public void nextMano(Jugador jugador){
        this.jugMano = jugador;
    }


    public int piedrasJuegos(Integer juego) {
		if (juego == 1) {
			return 3;
		}else if (juego != 9) {
			return 2;
		}

		return 0;
	}


    public void resuelveGrande() {

		Baraja mano11 = pareja1.getJug1().getMano();

		Baraja mano12 = pareja1.getJug2().getMano();
		Baraja mano21 = pareja2.getJug1().getMano();
		Baraja mano22 = pareja2.getJug2().getMano();

		int ganador = 0;
		int valor = 1;
		char grande = 'R';

		while (ganador == 0) {
			LinkedList<Integer> par1 = new LinkedList<Integer>();
			LinkedList<Integer> par2 = new LinkedList<Integer>();

			int numero = calculaNumCartas(mano11, grande);
			par1.add(numero);
			numero = calculaNumCartas(mano12, grande);
			par1.add(numero);
			numero = calculaNumCartas(mano21, grande);
			par2.add(numero);
			numero = calculaNumCartas(mano22, grande);
			par2.add(numero);

			/*
			 * Tenemos que comprobar que pareja gana en funcion del lance de grande
			 */

			if (par1.get(0) > par2.get(0) && par1.get(0) > par2.get(1)) { // gana la pareja1

				pareja1.addPiedras(3);
//				resPartida("Grande 3 0 ");

				salida.print("Grande 3 0");

				ganador = 1;
			} else if (par1.get(1) > par2.get(0) && par1.get(1) > par2.get(1)) {
				pareja1.addPiedras(3);
//				resPartida("Grande 3 0 ");

				ganador = 1;

				salida.print("Grande 3 0");

				//salida.println("Gana j12 por grande: " + par1.get(1) + "," + grande);
			} else if (par2.get(0) > par1.get(0) && par2.get(0) > par1.get(1)) {
				pareja2.addPiedras(3);
				// resPartida("Grande 0 3 ");

				ganador = 1;
				salida.print("Grande 0 3");

				//salida.println("Gana j21 por grande: " + par2.get(0) + "," + grande);
			} else if (par2.get(1) > par1.get(0) && par2.get(1) > par1.get(1)) {
				pareja2.addPiedras(3);
				// resPartida("Grande 0 3 ");

				ganador = 1;

				salida.print("Grande 0 3");

				//salida.println("Gana j22 por grande: " + par2.get(1) + "," + grande);
			} else { // empate
				switch (valor) {
				case 1:
					grande = 'C';
					break;
				case 2:
					grande = 'S';
					break;
				case 3:
					grande = '7';
					break;
				case 4:
					grande = '6';
					break;
				case 5:
					grande = '5';
					break;
				case 6:
					grande = '4';
					break;
				case 7:
					grande = '1';
					break;

				default:
					ganador = 1;
					pareja1.addPiedras(1);
					pareja2.addPiedras(1);
					salida.print("Grande 1 1");
					break;
				}

				if (ganador == 0) {
					valor++;
					ganador = 0;
				}
			}

		}

	}

    public void resuelveGrandeComandos() {

		Baraja mano11 = pareja1.getJug1().getMano();

		Baraja mano12 = pareja1.getJug2().getMano();
		Baraja mano21 = pareja2.getJug1().getMano();
		Baraja mano22 = pareja2.getJug2().getMano();

		int ganador = 0;
		int valor = 1;
		char grande = 'R';

		while (ganador == 0) {
			LinkedList<Integer> par1 = new LinkedList<Integer>();
			LinkedList<Integer> par2 = new LinkedList<Integer>();

			int numero = calculaNumCartas(mano11, grande);
			par1.add(numero);
			numero = calculaNumCartas(mano12, grande);
			par1.add(numero);
			numero = calculaNumCartas(mano21, grande);
			par2.add(numero);
			numero = calculaNumCartas(mano22, grande);
			par2.add(numero);

			/*
			 * Tenemos que comprobar que pareja gana en funcion del lance de grande
			 */

			if (par1.get(0) > par2.get(0) && par1.get(0) > par2.get(1)) { // gana la pareja1

				pareja1.addPiedras(3);
//				resPartida("Grande 3 0 ");

				salida.print("3 0 0 0 - 3 0");

				ganador = 1;
			} else if (par1.get(1) > par2.get(0) && par1.get(1) > par2.get(1)) {
				pareja1.addPiedras(3);
//				resPartida("Grande 3 0 ");

				ganador = 1;

				salida.print("0 0 3 0 - 3 0");

				//salida.println("Gana j12 por grande: " + par1.get(1) + "," + grande);
			} else if (par2.get(0) > par1.get(0) && par2.get(0) > par1.get(1)) {
				pareja2.addPiedras(3);
				// resPartida("Grande 0 3 ");

				ganador = 1;
				salida.print("0 3 0 0 - 0 3");

				//salida.println("Gana j21 por grande: " + par2.get(0) + "," + grande);
			} else if (par2.get(1) > par1.get(0) && par2.get(1) > par1.get(1)) {
				pareja2.addPiedras(3);
				// resPartida("Grande 0 3 ");

				ganador = 1;

				salida.print("0 0 0 3 - 0 3");

				//salida.println("Gana j22 por grande: " + par2.get(1) + "," + grande);
			} else { // empate
				switch (valor) {
				case 1:
					grande = 'C';
					break;
				case 2:
					grande = 'S';
					break;
				case 3:
					grande = '7';
					break;
				case 4:
					grande = '6';
					break;
				case 5:
					grande = '5';
					break;
				case 6:
					grande = '4';
					break;
				case 7:
					grande = '1';
					break;

				default:
				ganador = 1;
				if (par1.get(0) > par1.get(1)) {
					if (par2.get(0) > par2.get(1)) {
						salida.print("1 1 0 0 - 1 1");
					} else {
						salida.print("1 0 0 1 - 1 1");
					}
				} else {
					if (par2.get(0) > par2.get(1)) {
						salida.print("0 1 1 0 - 1 1");
					} else {
						salida.print("0 0 1 1 - 1 1");
					}

				}
					break;
				}

				if (ganador == 0) {
					valor++;
					ganador = 0;
				}
			}

		}

	}

	/*
	 *
	 */

	public void resuelveChica() {
		Baraja mano11 = pareja1.getJug1().getMano();
		Baraja mano12 = pareja1.getJug2().getMano();
		Baraja mano21 = pareja2.getJug1().getMano();
		Baraja mano22 = pareja2.getJug2().getMano();

		int ganador = 0;
		int valor = 1;
		char chica = '1';

		while (ganador == 0) {
			LinkedList<Integer> par1 = new LinkedList<Integer>();
			LinkedList<Integer> par2 = new LinkedList<Integer>();

			int numero = calculaNumCartas(mano11, chica);
			par1.add(numero);
			numero = calculaNumCartas(mano12, chica);
			par1.add(numero);
			numero = calculaNumCartas(mano21, chica);
			par2.add(numero);
			numero = calculaNumCartas(mano22, chica);
			par2.add(numero);

			/*
			 * Tenemos que comprobar que pareja gana en funcion del lance de chica
			 */

			if (par1.get(0) > par2.get(0) && par1.get(0) > par2.get(1)) { // gana la pareja1

				pareja1.addPiedras(3);
				// resPartida("Chica 3 0\n");

				salida.print("Chica 3 0");

				//salida.println("Gana j11 por chica: " + par1.get(0) + "," + chica);

				ganador = 1;
			} else if (par1.get(1) > par2.get(0) && par1.get(1) > par2.get(1)) {
				pareja1.addPiedras(3);
				// resPartida("Chica 3 0\n");

				ganador = 1;
				salida.print("Chica 3 0");

				//salida.println("Gana j12 por chica: " + par1.get(1) + "," + chica);
			} else if (par2.get(0) > par1.get(0) && par2.get(0) > par1.get(1)) {
				pareja2.addPiedras(3);
				// resPartida("Chica 0 3\n");

				ganador = 1;
				salida.print("Chica 0 3");

				//salida.println("Gana j21 por chica: " + par2.get(0) + "," + chica);
			} else if (par2.get(1) > par1.get(0) && par2.get(1) > par1.get(1)) {
				pareja2.addPiedras(3);
				// resPartida("Chica 0 3\n");

				ganador = 1;

				salida.print("Chica 0 3");

				//salida.println("Gana j22 por chica: " + par2.get(1) + "," + chica);
			} else { // empate
				switch (valor) {
				case 1:
					chica = '4';
					break;
				case 2:
					chica = '5';
					break;
				case 3:
					chica = '6';
					break;
				case 4:
					chica = '7';
					break;
				case 5:
					chica = 'S';
					break;
				case 6:
					chica = 'C';
					break;
				case 7:
					chica = 'R';
					break;

				default:
					ganador = 1;
					pareja1.addPiedras(1);
					pareja2.addPiedras(1);
					salida.print("Chica 1 1");
				// resPartida("Chica 0 0\n");
					break;
				}

				if (ganador == 0) {
					valor++;
					ganador = 0;
				}
			}

		}

	} // FIN RESUELVECHICA()


	public void resuelveChicaComandos() {
		Baraja mano11 = pareja1.getJug1().getMano();
		Baraja mano12 = pareja1.getJug2().getMano();
		Baraja mano21 = pareja2.getJug1().getMano();
		Baraja mano22 = pareja2.getJug2().getMano();

		int ganador = 0;
		int valor = 1;
		char chica = '1';

		while (ganador == 0) {
			LinkedList<Integer> par1 = new LinkedList<Integer>();
			LinkedList<Integer> par2 = new LinkedList<Integer>();

			int numero = calculaNumCartas(mano11, chica);
			par1.add(numero);
			numero = calculaNumCartas(mano12, chica);
			par1.add(numero);
			numero = calculaNumCartas(mano21, chica);
			par2.add(numero);
			numero = calculaNumCartas(mano22, chica);
			par2.add(numero);

			/*
			 * Tenemos que comprobar que pareja gana en funcion del lance de chica
			 */

			if (par1.get(0) > par2.get(0) && par1.get(0) > par2.get(1)) { // gana la pareja1

				pareja1.addPiedras(3);
				// resPartida("Chica 3 0\n");
				if (par1.get(0) > par1.get(1)) {
					salida.print("3 0 0 0 - 3 0");

				} else {
					salida.print("0 0 3 0 - 3 0");
				}

				//salida.println("Gana j11 por chica: " + par1.get(0) + "," + chica);

				ganador = 1;
			} else if (par1.get(1) > par2.get(0) && par1.get(1) > par2.get(1)) {
				pareja1.addPiedras(3);
				// resPartida("Chica 3 0\n");

				ganador = 1;
				if (par1.get(0) > par1.get(1)) {
					salida.print("3 0 0 0 - 3 0");

				} else {
					salida.print("0 0 3 0 - 3 0");
				}
				//salida.println("Gana j12 por chica: " + par1.get(1) + "," + chica);
			} else if (par2.get(0) > par1.get(0) && par2.get(0) > par1.get(1)) {
				pareja2.addPiedras(3);
				// resPartida("Chica 0 3\n");

				ganador = 1;
				if (par2.get(0) > par2.get(1)) {
					salida.print("0 3 0 0 - 0 3");

				} else {
					salida.print("0 0 0 3 - 0 3");
				}
				//salida.println("Gana j21 por chica: " + par2.get(0) + "," + chica);
			} else if (par2.get(1) > par1.get(0) && par2.get(1) > par1.get(1)) {
				pareja2.addPiedras(3);
				// resPartida("Chica 0 3\n");

				ganador = 1;

				if (par2.get(0) > par2.get(1)) {
					salida.print("0 3 0 0 - 0 3");

				} else {
					salida.print("0 0 0 3 - 0 3");
				}
				//salida.println("Gana j22 por chica: " + par2.get(1) + "," + chica);
			} else { // empate
				switch (valor) {
				case 1:
					chica = '4';
					break;
				case 2:
					chica = '5';
					break;
				case 3:
					chica = '6';
					break;
				case 4:
					chica = '7';
					break;
				case 5:
					chica = 'S';
					break;
				case 6:
					chica = 'C';
					break;
				case 7:
					chica = 'R';
					break;

				default:
					ganador = 1;
					if (par1.get(0) > par1.get(1)) {
						if (par2.get(0) > par2.get(1)) {
							salida.print("1 1 0 0 - 1 1");
						} else {
							salida.print("1 0 0 1 - 1 1");
						}
					} else {
						if (par2.get(0) > par2.get(1)) {
							salida.print("0 1 1 0 - 1 1");
						} else {
							salida.print("0 0 1 1 - 1 1");
						}
	
					}
					// resPartida("Chica 0 0\n");
					break;
				}

				if (ganador == 0) {
					valor++;
					ganador = 0;
				}
			}

		}

	} // FIN RESUELVECHICA()

	public void resuelvePares() {
		int piedrasP1 = 0;
		int piedrasP2 = 0;
		piedrasP1 += cuentaPares(pareja1.getJug1().getMano().getBarajaList());
		piedrasP1 += cuentaPares(pareja1.getJug2().getMano().getBarajaList());
		piedrasP2 += cuentaPares(pareja2.getJug1().getMano().getBarajaList());
		piedrasP2 += cuentaPares(pareja2.getJug2().getMano().getBarajaList());

		salida.print("Pares " + piedrasP1 + " " + piedrasP2);
		pareja1.addPiedras(piedrasP1);
		pareja2.addPiedras(piedrasP2);

	} // fin resuelvePares()

	public void resuelveParesComandos() {
		int piedrasP1 = 0;
		int pJ11 = 0;
		int pJ12 = 0;
		int pJ21 = 0;
		int pJ22 = 0;
		int piedrasP2 = 0;
		pJ11 = cuentaPares(pareja1.getJug1().getMano().getBarajaList());
		piedrasP1 += pJ11;
		pJ12 = cuentaPares(pareja1.getJug2().getMano().getBarajaList());
		piedrasP1 += pJ12;
		pJ21= cuentaPares(pareja2.getJug1().getMano().getBarajaList());
		piedrasP2 += pJ21;
		pJ22 = cuentaPares(pareja2.getJug2().getMano().getBarajaList());
		piedrasP2 += pJ22;

		salida.print(pJ11+ " " +pJ21 + " " + pJ12 + " " + pJ22  +" - "+piedrasP1 + " " + piedrasP2);
		pareja1.addPiedras(piedrasP1);
		pareja2.addPiedras(piedrasP2);
	} // fin resuelvePares()


	public void resuelveJuego(){
		int puntosP1J1 = 0,puntosP1J2,puntosP2J1 = 0,puntosP2J2=0;
		int juegoP1J1=0,juegoP1J2=0,juegoP2J1=0,juegoP2J2=0;

		puntosP1J1= pareja1.getJug1().getMano().getPuntos();
		juegoP1J1 = calculaJuego(puntosP1J1);

		puntosP1J2=pareja1.getJug2().getMano().getPuntos();
		juegoP1J2 = calculaJuego(puntosP1J2);
		puntosP2J1=pareja2.getJug1().getMano().getPuntos();
		juegoP2J1 = calculaJuego(puntosP2J1);
		puntosP2J2=pareja2.getJug2().getMano().getPuntos();
		juegoP2J2 = calculaJuego(puntosP2J2);


		if(juegoP1J1 < juegoP2J1 && juegoP1J1 < juegoP2J2) {
			int piedrasP1 = 2;
			piedrasP1 += piedrasJuegos(juegoP1J1);
			piedrasP1 += piedrasJuegos(juegoP1J2);
			pareja1.addPiedras(piedrasP1);


			int piedrasP2 = 0;
			piedrasP2 += piedrasJuegos(juegoP2J1);
			piedrasP2 += piedrasJuegos(juegoP2J2);

			pareja2.addPiedras(piedrasP2);


			salida.print("Juego " + piedrasP1 + " " + piedrasP2 + " ");

		}else if(juegoP1J2 < juegoP2J1 && juegoP1J2 < juegoP2J2) {
			int piedrasP1 = 2;
			piedrasP1 += piedrasJuegos(juegoP1J1);
			piedrasP1 += piedrasJuegos(juegoP1J2);
			pareja1.addPiedras(piedrasP1);


			int piedrasP2 = 0;
			piedrasP2 += piedrasJuegos(juegoP2J1);
			piedrasP2 += piedrasJuegos(juegoP2J2);

			pareja2.addPiedras(piedrasP2);

			salida.print("Juego " + piedrasP1 + " " + piedrasP2 + " ");



		}else if(juegoP2J1 < juegoP1J1 && juegoP2J1 < juegoP1J2) {
			int piedrasP1 = 0;
			piedrasP1 += piedrasJuegos(juegoP1J1);
			piedrasP1 += piedrasJuegos(juegoP1J2);
			pareja1.addPiedras(piedrasP1);


			int piedrasP2 = 2;
			piedrasP2 += piedrasJuegos(juegoP2J1);
			piedrasP2 += piedrasJuegos(juegoP2J2);

			pareja2.addPiedras(piedrasP2);

			salida.print("Juego " + piedrasP1 + " " + piedrasP2 + " ");


		}else if(juegoP2J2 < juegoP1J1 && juegoP2J2 < juegoP1J2) {
			int piedrasP1 = 0;
			piedrasP1 += piedrasJuegos(juegoP1J1);
			piedrasP1 += piedrasJuegos(juegoP1J2);
			pareja1.addPiedras(piedrasP1);


			int piedrasP2 = 2;
			piedrasP2 += piedrasJuegos(juegoP2J1);
			piedrasP2 += piedrasJuegos(juegoP2J2);

			pareja2.addPiedras(piedrasP2);

			salida.print("Juego " + piedrasP1 + " " + piedrasP2 + " ");


		} else {

			if(juegoP1J1 + juegoP1J2 < juegoP2J1 + juegoP2J2) {
				int piedrasP1 = 2;
				piedrasP1 += piedrasJuegos(juegoP1J1);
				piedrasP1 += piedrasJuegos(juegoP1J2);
				pareja1.addPiedras(piedrasP1);


				int piedrasP2 = 0;
				piedrasP2 += piedrasJuegos(juegoP2J1);
				piedrasP2 += piedrasJuegos(juegoP2J2);

				pareja2.addPiedras(piedrasP2);

				salida.print("Juego " + piedrasP1 + " " + piedrasP2 + " ");


			} else if (juegoP2J1 + juegoP2J2 < juegoP1J1 + juegoP1J2) {
				int piedrasP1 = 0;
				piedrasP1 += piedrasJuegos(juegoP1J1);
				piedrasP1 += piedrasJuegos(juegoP1J2);
				pareja1.addPiedras(piedrasP1);


				int piedrasP2 = 2;
				piedrasP2 += piedrasJuegos(juegoP2J1);
				piedrasP2 += piedrasJuegos(juegoP2J2);

				pareja2.addPiedras(piedrasP2);

				salida.print("Juego " + piedrasP1 + " " + piedrasP2 + " ");


			} else {
				jugMano.getPareja().addPiedras(2);

				int piedrasP1 = 0;

				piedrasP1 += piedrasJuegos(juegoP1J1);
				piedrasP1 += piedrasJuegos(juegoP1J2);

				int piedrasP2 = 0;
				piedrasP2 += piedrasJuegos(juegoP2J1);
				piedrasP2 += piedrasJuegos(juegoP2J2);

				pareja1.addPiedras(piedrasP1);
				pareja2.addPiedras(piedrasP2);



				salida.print("Juego " + piedrasP1 + " " + piedrasP2 + " ");


			}

		}


		salida.print("- " + pareja1.getPiedras() + " " + pareja2.getPiedras() + "\n");


	}

	public void resuelveJuegoComandos(){
		int puntosP1J1 = 0,puntosP1J2,puntosP2J1 = 0,puntosP2J2=0;
		int juegoP1J1=0,juegoP1J2=0,juegoP2J1=0,juegoP2J2=0;

		puntosP1J1= pareja1.getJug1().getMano().getPuntos();
		juegoP1J1 = calculaJuego(puntosP1J1);

		puntosP1J2=pareja1.getJug2().getMano().getPuntos();
		juegoP1J2 = calculaJuego(puntosP1J2);
		puntosP2J1=pareja2.getJug1().getMano().getPuntos();
		juegoP2J1 = calculaJuego(puntosP2J1);
		puntosP2J2=pareja2.getJug2().getMano().getPuntos();
		juegoP2J2 = calculaJuego(puntosP2J2);


		if(juegoP1J1 < juegoP2J1 && juegoP1J1 < juegoP2J2) {
			int piedrasP1 = 2;
			piedrasP1 += piedrasJuegos(juegoP1J1);
			piedrasP1 += piedrasJuegos(juegoP1J2);
			pareja1.addPiedras(piedrasP1);


			int piedrasP2 = 0;
			piedrasP2 += piedrasJuegos(juegoP2J1);
			piedrasP2 += piedrasJuegos(juegoP2J2);

			pareja2.addPiedras(piedrasP2);


			salida.print(piedrasJuegos(juegoP1J1)+ " "+piedrasJuegos(juegoP2J1)+ " "+piedrasJuegos(juegoP1J2)+ " "+piedrasJuegos(juegoP2J2)+ " - "+  piedrasP1 + " " + piedrasP2 + " ");

		}else if(juegoP1J2 < juegoP2J1 && juegoP1J2 < juegoP2J2) {
			int piedrasP1 = 2;
			piedrasP1 += piedrasJuegos(juegoP1J1);
			piedrasP1 += piedrasJuegos(juegoP1J2);
			pareja1.addPiedras(piedrasP1);


			int piedrasP2 = 0;
			piedrasP2 += piedrasJuegos(juegoP2J1);
			piedrasP2 += piedrasJuegos(juegoP2J2);

			pareja2.addPiedras(piedrasP2);

			salida.print(piedrasJuegos(juegoP1J1)+ " "+piedrasJuegos(juegoP2J1)+ " "+piedrasJuegos(juegoP1J2)+ " "+piedrasJuegos(juegoP2J2)+ " - "+  piedrasP1 + " " + piedrasP2 + " ");



		}else if(juegoP2J1 < juegoP1J1 && juegoP2J1 < juegoP1J2) {
			int piedrasP1 = 0;
			piedrasP1 += piedrasJuegos(juegoP1J1);
			piedrasP1 += piedrasJuegos(juegoP1J2);
			pareja1.addPiedras(piedrasP1);


			int piedrasP2 = 2;
			piedrasP2 += piedrasJuegos(juegoP2J1);
			piedrasP2 += piedrasJuegos(juegoP2J2);

			pareja2.addPiedras(piedrasP2);

			salida.print(piedrasJuegos(juegoP1J1)+ " "+piedrasJuegos(juegoP2J1)+ " "+piedrasJuegos(juegoP1J2)+ " "+piedrasJuegos(juegoP2J2)+ " - "+  piedrasP1 + " " + piedrasP2 + " ");


		}else if(juegoP2J2 < juegoP1J1 && juegoP2J2 < juegoP1J2) {
			int piedrasP1 = 0;
			piedrasP1 += piedrasJuegos(juegoP1J1);
			piedrasP1 += piedrasJuegos(juegoP1J2);
			pareja1.addPiedras(piedrasP1);


			int piedrasP2 = 2;
			piedrasP2 += piedrasJuegos(juegoP2J1);
			piedrasP2 += piedrasJuegos(juegoP2J2);

			pareja2.addPiedras(piedrasP2);

			salida.print(piedrasJuegos(juegoP1J1)+ " "+piedrasJuegos(juegoP2J1)+ " "+piedrasJuegos(juegoP1J2)+ " "+piedrasJuegos(juegoP2J2)+ " - "+  piedrasP1 + " " + piedrasP2 + " ");



		} else {

			if(juegoP1J1 + juegoP1J2 < juegoP2J1 + juegoP2J2) {
				int piedrasP1 = 2;
				piedrasP1 += piedrasJuegos(juegoP1J1);
				piedrasP1 += piedrasJuegos(juegoP1J2);
				pareja1.addPiedras(piedrasP1);


				int piedrasP2 = 0;
				piedrasP2 += piedrasJuegos(juegoP2J1);
				piedrasP2 += piedrasJuegos(juegoP2J2);

				pareja2.addPiedras(piedrasP2);

				salida.print(piedrasJuegos(juegoP1J1)+ " "+piedrasJuegos(juegoP2J1)+ " "+piedrasJuegos(juegoP1J2)+ " "+piedrasJuegos(juegoP2J2)+ " - "+  piedrasP1 + " " + piedrasP2 + " ");


			} else if (juegoP2J1 + juegoP2J2 < juegoP1J1 + juegoP1J2) {
				int piedrasP1 = 0;
				piedrasP1 += piedrasJuegos(juegoP1J1);
				piedrasP1 += piedrasJuegos(juegoP1J2);
				pareja1.addPiedras(piedrasP1);


				int piedrasP2 = 2;
				piedrasP2 += piedrasJuegos(juegoP2J1);
				piedrasP2 += piedrasJuegos(juegoP2J2);

				pareja2.addPiedras(piedrasP2);

				salida.print(piedrasJuegos(juegoP1J1)+ " "+piedrasJuegos(juegoP2J1)+ " "+piedrasJuegos(juegoP1J2)+ " "+piedrasJuegos(juegoP2J2)+ " - "+  piedrasP1 + " " + piedrasP2 + " ");


			} else {
				jugMano.getPareja().addPiedras(2);

				int piedrasP1 = 0;
				int piedrasP2 = 0;

				if (pareja1.equals(jugMano.getPareja())) {
					piedrasP1 = 2;
				} else {
					piedrasP2 = 2;

				}

				piedrasP1 += piedrasJuegos(juegoP1J1);
				piedrasP1 += piedrasJuegos(juegoP1J2);

				piedrasP2 += piedrasJuegos(juegoP2J1);
				piedrasP2 += piedrasJuegos(juegoP2J2);

				pareja1.addPiedras(piedrasP1);
				pareja2.addPiedras(piedrasP2);



				salida.print(piedrasJuegos(juegoP1J1)+ " "+piedrasJuegos(juegoP2J1)+ " "+piedrasJuegos(juegoP1J2)+ " "+piedrasJuegos(juegoP2J2)+ " - "+  piedrasP1 + " " + piedrasP2 + " ");


			}

		}



	}


	private int calculaJuego(int puntos) {
		int juego=0;
		switch (puntos) {
		case 31:
			juego=1;
			break;
		case 32:
			juego=2;
			break;
		case 33:
			juego=8;
			break;
		case 34:
			juego=7;
			break;
		case 35:
			juego=6;
			break;
		case 36:
			juego=5;
			break;
		case 37:
			juego=4;
			break;
		case 40:
			juego=3;
			break;
		default:
			juego=9;
			break;
		}
		return juego;
	}


	/*
	 * Pares: 2 cartas iguales; Medias: 3 cartas iguales; Duplex: 2 parejas de
	 * cartas iguales o 4 cartas
	 */

	public int cuentaPares(LinkedList<Carta> lista) {
		int iguales = 1;
		int nParejas = 0;
		LinkedList<Carta> aux = new LinkedList<Carta>(lista);

		for (int i = 0; i < aux.size(); i++) {
			char id = aux.get(i).getId();
			aux.remove(i);
			i--;


			for (int j = i+1; j < aux.size(); j++) {
				char id2 = aux.get(j).getId();
				//System.out.println("id: " + id + "// " + id2);


				if ( (id - id2) == 0){
					iguales = iguales + 1;
					aux.remove(j);

					j--;
				} else if ( (id == 'R' && id2 == '3') || (id == '3' && id2 == 'R')) {
					iguales = iguales + 1;
					aux.remove(j);

					j--;
				} else if ((id == '1' && id2 == '2') || (id == '2' && id2 == '1')) {
					iguales = iguales + 1;
					aux.remove(j);

					j--;
				}
			}

			//System.out.println("Iguales ->" + iguales);


			if (iguales == 4) {
				return 3;

			} else if (iguales == 3) {
				return 2;

			} else if (iguales == 2) {
				nParejas++;
				iguales = 1;
			}

			//System.out.println("Nparejas: " + nParejas);


		}

		if ( nParejas == 2) {
			return 3;
		} else if (nParejas == 1) {
			return 1;
		}


		return 0;
	}

	/*
	 * Calcula el numero de cartas en una lista de cartas conociendo el id.
	 * Devolvemos el numero
	 */
	public int calculaNumCartas(Baraja mano, char id) {
		int numero = 0;

		for (Carta carta : mano.getBarajaList()) {

			switch (id) {

			case 'R':
				if (carta.getId() == 'R' || carta.getId() == '3') {
					numero++;
				}
				break;

			case '1':
				if (carta.getId() == '1' || carta.getId() == '2') {
					numero++;
				}
				break;

			default:
				if (carta.getId() == id) {
					numero++;
				}
				break;

			}
		}
		return numero;
	}



}