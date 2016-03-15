package launch;

import java.util.ArrayList;

import data.Data;
import naivebayes.NaiveBayesClassifier;
import naivebayes.Vocabulary;

public class MainEntry {

	public static void main(String[] args) {
		
		Data data = new Data("AlarmClock");
//		data.writeDataToFile("dataTest.txt");
		
		
		NaiveBayesClassifier nb = new NaiveBayesClassifier(data, 0.5);
		
		nb.classifier();
		
		System.out.println(nb.numberPercentFalse());
		
		String s = "jfkj wef hwe we hoiwefhwie w iefweifwef hw hfwief heh";
		
//		String[] arr = s.split(" ");
//		
//		for( String s : arr){
//			s.s
//		}
		
		
		
	}

}
