import java.util.ArrayList;


public class Pregunta {

	private int id;
	private String text;
	private ArrayList<Respuesta> respuestas;
	
	
	Pregunta(int id, String text,  ArrayList<Respuesta> respuestas){
		
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Pregunta(String text) {
		super();
		this.text = text;
	}
	
	
	
}
