package objects;

public class SurveyItem {
	
	private int id;
	private String text;
	private String answer;
	
	public SurveyItem(int id, String text, String answer) {
		this.id = id;
		this.text = text;
		this.answer = answer;
	}

	public int getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public String getAnswer() {
		return answer;
	}
}
