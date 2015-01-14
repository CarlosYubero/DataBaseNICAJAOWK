
public class Respuesta {

	private String text;
	private boolean is_correct;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isIs_correct() {
		return is_correct;
	}
	public void setIs_correct(boolean is_correct) {
		this.is_correct = is_correct;
	}
	public Respuesta(String text, boolean is_correct) {
		super();
		this.text = text;
		this.is_correct = is_correct;
	}
	
	
}
