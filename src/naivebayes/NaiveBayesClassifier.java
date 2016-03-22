package naivebayes;

import java.util.ArrayList;

import data.Data;
import data.Document;

public class NaiveBayesClassifier {
	
	private Data dataSets ;
	private double lamda;
	
	public NaiveBayesClassifier(Data dataSet, double lamda){
		this.dataSets = dataSet;
		this.lamda = lamda;
	}
	
	public void classifier(Data dataSets){
		Vocabulary voca = new Vocabulary(dataSets);
		voca.computeProbabilityVoca(lamda);
		double probabiPos;
		double probabiNeg;
		String review;
		ArrayList<String> words;
		Word w;
		for(Document d : dataSets.getDocuments()){
			probabiPos = 1;
			probabiNeg = 1;
			review = d.getReview();
			words = Util.Utility.splitReviewtoWords(review);
			for(String s : words){
				w = voca.searchWord(s);
				if(w!= null){
					probabiPos *= w.getProPOS();
					probabiNeg *= w.getProNEG();
				}
			}
			probabiPos *= dataSets.getProLabel("POS");
			probabiNeg *= dataSets.getProLabel("NEG");
			if(probabiPos >= probabiNeg){
				d.setGuessLabel("POS");
			}
			else{
				d.setGuessLabel("NEG");
			}
			
		}
		dataSets.writeDataToFile("dataclassifier.txt");
		
	}
	
	public int numberPercentFalse(){
		int count = 0;
		for(Document d : dataSets.getDocuments()){
			if(!d.getGuessLabel().equals(d.getLabel()))
				count++;
		}
		
		return count;
	}
	
}
