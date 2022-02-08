import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Anagrams {

	final Integer[] primes = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83,
			89, 97, 101 };

	Map<Character, Integer> letterTable;
	Map<Long, ArrayList<String>> anagramTable;

	public Anagrams() {
		buildLetterTable();
		anagramTable = new HashMap<Long, ArrayList<String>>();

	}

	private void buildLetterTable() {
		letterTable = new HashMap<Character, Integer>();
		Character[] alphabets = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
				'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

		for (int i = 0; i <= 25; i++) {
			letterTable.put(alphabets[i], primes[i]);
		}

	}

	private void addWord(String s) {

		Long hashValue = this.myHashCode(s);
		if (anagramTable.get(hashValue) == null) {
			ArrayList<String> addToList = new ArrayList<String>();
			addToList.add(s);
			anagramTable.put(hashValue, addToList);
		} else {
			anagramTable.get(hashValue).add(s);
		}
	}

	private Long myHashCode(String s) {
		long key = 1;
		for (int i = 0; i < s.length(); i++) {
			key = key * (long) letterTable.get(s.charAt(i));
		}
		return key;
	}

	private void processFile(String s) throws IOException {

		FileInputStream fileStream = new FileInputStream(s);
		BufferedReader buffReader = new BufferedReader(new InputStreamReader(fileStream));
		String stringLine;

		while ((stringLine = buffReader.readLine()) != null) {
			this.addWord(stringLine);
		}
		buffReader.close();
	}

	private ArrayList<Map.Entry<Long, ArrayList<String>>> getMaxEntries() {

		ArrayList<Map.Entry<Long, ArrayList<String>>> lists = new ArrayList<>();
		int max = 0;

		for (Map.Entry<Long, ArrayList<String>> entry : anagramTable.entrySet()) {

			if (entry.getValue().size() > max) {
				lists.clear();
				lists.add(entry);
				max = entry.getValue().size();
			} else if (entry.getValue().size() == max) {
				lists.add(entry);
			}
		}
		return lists;
	}

	public static void main(String[] args) {
		Anagrams anagram = new Anagrams();
		final long startTime = System.nanoTime();

		try {
			anagram.processFile("words_alpha.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntries = anagram.getMaxEntries();
		long key = maxEntries.get(0).getKey();
		int length = maxEntries.get(0).getValue().size();
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime / 1000000000);
		System.out.println("Elapsed Time : " + seconds);
		System.out.println("Key of max anagrams: " + key);
		System.out.println("List of max anagrams: " + maxEntries.get(0).getValue());
		System.out.println("Length of list of max anagrams : " + length);
	}
}