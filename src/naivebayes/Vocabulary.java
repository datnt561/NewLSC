package naivebayes;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import Util.Utility;
import data.Data;


public class Vocabulary {
	private HashSet<Word> voca;
	private String domain;
	private int size;

	public Vocabulary(Data dataset) {
		domain = dataset.getNameDomain();

		voca = new HashSet<Word>();

		ArrayList<String> listWordInPos = dataset.splitDataSetToWordByLabel("POS");
		ArrayList<String> listWordInNeg = dataset.splitDataSetToWordByLabel("NEG");
		Word w;
		int numberWordInPOS;
		int numberWordInNEG;
		HashSet<String> listWordInData = new HashSet<String>(listWordInPos);
		listWordInData.addAll(listWordInNeg);
		for (String s : listWordInData) {
			w = new Word(s);
			voca.add(w);
		}

		for (Word w2 : voca) {
			numberWordInPOS = Util.Utility.countWordInList(w2.getWord(), listWordInPos);
			numberWordInNEG = Util.Utility.countWordInList(w2.getWord(), listWordInNeg);
			w2.setTimeInPOS(numberWordInPOS);
			w2.setTimeInNEG(numberWordInNEG);
		}
	}

	public HashSet<Word> getVoca() {
		return voca;
	}

	public String getDomain() {
		return domain;
	}

	public int getSize() {
		return size;
	}

	public Word searchWord(String w) {
		Word result = null;
		for (Word w1 : voca) {
			if (w1.getWord().equalsIgnoreCase(w))
				result = w1;
		}
		return result;
	}

	public void computeProbabilityVoca(double lamda) {
		double probabi;
		int sumWordPOS = 0;
		int sumWordNEG = 0;
		for (Word w : voca) {
			sumWordPOS += w.getTimeInPOS();
			sumWordNEG += w.getTimeInNEG();
		}
		for (Word w : voca) {
			probabi = (double) (lamda + w.getTimeInPOS()) / (lamda * voca.size() + sumWordPOS);
			probabi = Utility.decimalFormat(probabi, 9);
			w.setProPOS(probabi);
			probabi = (double) (lamda + w.getTimeInNEG()) / (lamda * voca.size() + sumWordNEG);
			probabi = Utility.decimalFormat(probabi, 9);
			w.setProNEG(probabi);

		}
	}
	
	public void writeVocaToFile(String nameFile) {
		FileWriter writer;
		try {
			writer = new FileWriter(nameFile);
			writer.write(domain);
			writer.write("\n");
			for (Word w : voca) {
				writer.write(w.getWord());
				writer.write("\t");
				writer.write(String.valueOf(w.getTimeInPOS()));
				writer.write("\t");
				writer.write(String.valueOf(w.getTimeInNEG()));
				writer.write("\t");
				writer.write(String.valueOf(w.getProPOS()));
				writer.write("\t");
				// xac suat de tu w xuat hien trong NEG
				writer.write(String.valueOf(w.getProNEG()));
				writer.write("\n");
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
