package data;

public class Document {
	String label;
	String review;
	String guessLabel;

	public Document(String label, String review) {
		this.label = label;
		this.review = review;
	}

	public String getLabel() {
		return label;
	}

	public String getReview() {
		return review;
	}

	public void setGuessLabel(String guessLabel) {
		this.guessLabel = guessLabel;
	}

	public String getGuessLabel() {
		return guessLabel;
	}
}
