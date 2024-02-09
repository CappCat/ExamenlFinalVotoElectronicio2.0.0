package ec.edu.puce.elecciones.dominio;


public class Candidato {
	private int id;
	private String nombre;
	private int votos;

	public void aumentarVotos() {
		this.votos++;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getVotos() {
		return votos;
	}

	public void setVotos(int votos) {
		this.votos = votos;
	}
		
}
