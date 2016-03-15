package Util;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

public class Utility {
	public static final boolean containsDigit(String s) {
		boolean containsDigit = false;

		if (s != null && !s.isEmpty()) {
			for (char c : s.toCharArray()) {
				if (containsDigit = Character.isDigit(c)) {
					break;
				}
			}
		}

		return containsDigit;
	}

	public static ArrayList<String> splitReviewtoWords(String review) {
		ArrayList<String> words = new ArrayList<String>();
		ArrayList<Integer> special = new ArrayList<Integer>();
		for (int i = 0; i < review.length(); i++)
			if (!((review.charAt(i) >= 65 && review.charAt(i) <= 90)
					|| (review.charAt(i) >= 97 && review.charAt(i) <= 122))) {
				special.add(i);
			}
		int temp = 0;
		String word;
		for (int i : special) {
			word = review.substring(temp, i);
			temp = i + 1;
			if (word.length() > 2)
				words.add(word.toLowerCase());
		}
		return words;

	}

	public static int countWordInList(String word, ArrayList<String> listWord) {

		return Collections.frequency(listWord, word);
	}

	public static void writerListWordToFile(ArrayList<String> listWords, String nameFile) {

		FileWriter writer;
		try {
			writer = new FileWriter(nameFile);
			for (String str : listWords) {
				writer.write(str);
				writer.write("\n");
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static ArrayList<String> removeAllEle(String key, ArrayList<String> listWord) {
		ArrayList<String> newListWord = new ArrayList(listWord);
		for (String s : listWord)
			if (s.equals(key))
				listWord.remove(s);
		return listWord;
	}
	
	public static double decimalFormat(double num, int n){
		DecimalFormat decimalFormat = new DecimalFormat();
		StringBuffer sb = new StringBuffer();
		sb.append("0.");
		for(int i = 0; i < n; i++)
			sb.append("#");
		decimalFormat.applyPattern(sb.toString());
		decimalFormat.setMaximumFractionDigits(9);
		double result = Double.valueOf(decimalFormat.format(num));
		
		return result;
	}
}
