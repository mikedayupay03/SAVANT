package objects;

public class SurveyItemAdaptiveCapacity {
	
	private String category;
	private String mainQuestion;
	private String subQuestion;
	private String value2Desc;
	private String value3Desc;
	private String value4Desc;
	private String value5Desc;
	private String image2;
	private String image3;
	private String image4;
	private String image5;
	
	public SurveyItemAdaptiveCapacity(String category, String mainQuestion, String subQuestion, String value2Desc, String value3Desc, String value4Desc, String value5Desc, String image2, String image3, String image4, String image5) {
		this.category = category;
		this.mainQuestion = mainQuestion;
		this.subQuestion = subQuestion;
		this.value2Desc = value2Desc;
		this.value3Desc = value3Desc;
		this.value4Desc = value4Desc;
		this.value5Desc = value5Desc;
		this.image2 = image2;
		this.image3 = image3;
		this.image4 = image4;
		this.image5 = image5;
	}

	public String getCategory() {
		return category;
	}

	public String getMainQuestion() {
		return mainQuestion;
	}

	public String getSubQuestion() {
		return subQuestion;
	}

	public String getValue2Desc() {
		return value2Desc;
	}

	public String getValue3Desc() {
		return value3Desc;
	}

	public String getValue4Desc() {
		return value4Desc;
	}

	public String getValue5Desc() {
		return value5Desc;
	}
	
	public String getImage2() {
		return image2;
	}
	
	public String getImage3() {
		return image3;
	}
	
	public String getImage4() {
		return image4;
	}
	
	public String getImage5() {
		return image5;
	}


}
