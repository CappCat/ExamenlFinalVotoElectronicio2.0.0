package ec.edu.puce.elecciones.dominio;

public class Estudiante {
	private int id;
	private String nombre;
	private String clavePersonal;
	private Curso curso;
    private Candidato voto;
    private String cedula;
    private boolean haVotado = false; 

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

	public String getClavePersonal() {
		return clavePersonal;
	}

	public void setClavePersonal(String clavePersonal) {
		this.clavePersonal = clavePersonal;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}


	public Candidato getVoto() {
		return voto;
	}

	public void setVoto(Candidato voto) {
		this.voto = voto;
	}

	public boolean isHaVotado() {
		return haVotado;
	}

	public void setHaVotado(boolean haVotado) {
		this.haVotado = haVotado;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	
	

}
