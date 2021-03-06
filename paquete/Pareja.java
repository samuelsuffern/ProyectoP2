package paquete;

public class Pareja {

	private Jugador jug1;
	private Jugador jug2;
	private Integer piedras = 0;
	private Integer id;
	private String equipo;

	public Jugador getJug1() {
		return jug1;
	}

	public void setJug1(Jugador jug1) {
		this.jug1 = jug1;
	}

	public Jugador getJug2() {
		return jug2;
	}

	public void setJug2(Jugador jug2) {
		this.jug2 = jug2;
	}

	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPiedras() {
		return piedras;
	}

	public void addPiedras(Integer piedras) {
		this.piedras = this.piedras + piedras;
	}

	public String toString(){
		return "P " + id +" "+jug1.getId()+" "+jug2.getId() + " " + equipo;
	}

}
