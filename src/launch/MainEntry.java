package launch;

import java.util.ArrayList;

import data.Data;
import naivebayes.NaiveBayesClassifier;
import naivebayes.Vocabulary;

public class MainEntry {

	public static void main(String[] args) {
		
		Data data = new Data("Baby");
//		data.writeDataToFile("dataTest.txt");
		ArrayList<Data> listData = data.splitData(3);
		
		
		NaiveBayesClassifier nb = new NaiveBayesClassifier(listData.get(0), 0.5);
		
		nb.classifier();
		
		System.out.println(nb.numberPercentFalse());
				
//		String[] arr = s.split(" ");
//		
//		for( String s : arr){
//			s.s
//		}
		
		
		
	}

}
