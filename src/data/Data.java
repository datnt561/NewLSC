package data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Data {
	protected ArrayList<Document> data;
	protected ArrayList<Label> labels;
	private String nameDomain;

	public Data(String namedomain) {
		this.nameDomain = namedomain;
		namedomain = "ACL2015-Chen-Datasets/" + namedomain + ".txt";
		data = readDataFromFile(namedomain);

		// tinh so label va xac suat
		computeNumberLabel();
		computeProbabiLabels();

	}

	public Data(ArrayList<Document> listDocument) {

		data = listDocument;
		// tinh so label va xac suat
		computeNumberLabel();
		computeProbabiLabels();

	}

	public ArrayList<Document> readDataFromFile(String nameFile) {
		String line = null;
		ArrayList<Document> dataSet = new ArrayList<Document>();
		try {
			FileReader fileReader = new FileReader(nameFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			Document document = null;

			while ((line = bufferedReader.readLine()) != null) {
				if (!line.contains("Domain") && !line.contains("NEU")) {
					document = this.splitLineData(line);
					dataSet.add(document);
				}
			}

			setNullToLabelGuess();
			fileReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + nameFile);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return dataSet;
	}

	private void setNullToLabelGuess() {
		for (Document d : data) {
			d.setGuessLabel(null);
		}
	}

	private Document splitLineData(String line) {
		String[] review = new String[4];
		int j = 0;
		for (String s : line.split("\t")) {
			review[j] = s;
			j++;
		}

		Document lineData = new Document(review[1], review[3]);

		return lineData;
	}

	public String getNameDomain() {
		return nameDomain;
	}

	public ArrayList<Document> getDocumentsByLabel(String label) {
		ArrayList<Document> listDocument = new ArrayList<Document>();
		for (Document d : data) {
			if (d.label.equals(label)) {
				listDocument.add(d);
			}
		}

		return listDocument;
	}

	public ArrayList<String> getReviewsByLabel(String label) {
		ArrayList<String> listReview = new ArrayList<String>();
		for (Document d : data) {
			listReview.add(d.getReview());
		}
		return listReview;
	}

	public void computeNumberLabel() {
		HashSet<String> labelsInDocument = new HashSet<String>();

		for (Document d : data)
			labelsInDocument.add(d.getLabel());

		labels = new ArrayList<Label>();
		Label l;
		for (String s : labelsInDocument) {
			l = new Label(s);
			labels.add(l);
		}
	}

	protected void computeProbabiLabels() {
		double probabi;
		ArrayList<Document> listDocument;
		for (Label l : labels) {
			listDocument = getDocumentsByLabel(l.getLabel());
			probabi = (double) listDocument.size() / data.size();
			l.setProbability(probabi);
		}
	}

	public void writeDataToFile(String nameFile) {
		FileWriter writer;
		try {
			writer = new FileWriter(nameFile);
			writer.write(nameDomain);
			writer.write("\n");
			for (Label l : labels) {
				writer.write(l.getLabel());
				writer.write("\t");
				writer.write(String.valueOf(l.getProbability()));
				writer.write("\n");
			}
			for (Document d : data) {
				if (d.getGuessLabel() == null)
					writer.write("NULL");
				else
					writer.write(d.getGuessLabel());
				writer.write("\t");
				writer.write(d.getLabel());
				writer.write("\t");
				writer.write(d.getReview());
				writer.write("\n");
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<String> splitDataSetToWordByLabel(String label) {
		ArrayList<String> listWord = new ArrayList<String>();
		ArrayList<Document> listDocument = this.getDocumentsByLabel(label);
		ArrayList<String> words;

		for (Document d : listDocument) {
			if (d.getLabel().equals(label)) {
				words = Util.Utility.splitReviewtoWords(d.getReview());
				for (String w : words)
					listWord.add(w);
			}
		}
		return listWord;
	}

	public ArrayList<String> listWordInDataSet() {
		ArrayList<String> listWords = new ArrayList<String>();

		listWords.addAll(splitDataSetToWordByLabel("POS"));
		listWords.addAll(splitDataSetToWordByLabel("NEG"));

		return listWords;
	}

	public ArrayList<Document> getDocuments() {
		return data;
	}

	public double getProLabel(String label) {
		for (Label l : labels) {
			if (l.getLabel().equals(label))
				return l.getProbability();
		}
		return 0;
	}

	public ArrayList<Data> splitData(int n) {
		ArrayList<Data> listData = new ArrayList<Data>();
		int nextIndex = 0;
		int newIndex = data.size() / n;
		ArrayList<Document> documents;
		Data d;
		for (int i = 1; i < n; i++) {
			documents = (ArrayList<Document>) data.subList(nextIndex, i * newIndex);
			nextIndex = i * newIndex;
			d = new Data(documents);
			d.nameDomain = nameDomain;
			
			listData.add(d);

		}

		return listData;
	}

}
