import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import org.json.JSONObject;

public class FileClassifier {
	
	Map<String, Map<String, Integer>> map;
	Map<String, Integer> cnts;
	public FileClassifier() throws FileNotFoundException {
		// TODO Auto-generated constructor stub
		map = new HashMap<>();
		cnts = new HashMap<>();
		
		File file = new File("src/shuffled-full-set-hashed.csv");
		Scanner sc = new Scanner(file);
		
		int totalCount = 0;
		while(sc.hasNextLine()){
			sc.nextLine();
			totalCount++;
		}
		
		int modelCount = totalCount * 9 / 10;
		int testCount = totalCount - modelCount;
		sc.close();
		
		
		sc = new Scanner(file);
		int currCount = 0;
		while(sc.hasNextLine() /*&& currCount < modelCount*/){
			String line = sc.nextLine();
			int comma = line.indexOf(",");
			String type = line.substring(0, comma);
			String[] words = line.substring(comma+1).split(" ");
			if(!map.containsKey(type)){
				map.put(type, new HashMap<>());
				cnts.put(type, 0);
			}
			for(String word : words){
				if(!map.get(type).containsKey(word)){
					map.get(type).put(word, 1);
				} else {
					map.get(type).put(word, map.get(type).get(word) + 1);
				}
			}
			cnts.put(type, cnts.get(type) + words.length);
			currCount++;
		}
		sc.close();
		
		
//		JSONObject mapJSON = new JSONObject(map);
//		JSONObject cntsJSON = new JSONObject(cnts);
//		PrintWriter pw = new PrintWriter("map.json");
//		pw.println(mapJSON.toString());
//		pw.close();
//		pw = new PrintWriter("cnts.json");
//		pw.println(cntsJSON.toString());
//		pw.close();
		//System.out.println(mapJSON.toString());
		/*
		sc = new Scanner(file);
		
		int correct = 0;
		
		while(sc.hasNextLine()){
			String line = sc.nextLine();
			int comma = line.indexOf(",");
			String type = line.substring(0, comma);
			String[] words = line.substring(comma+1).split(" ");
			
			Map<String, Double> points = new HashMap<>();
			
			for(String key : map.keySet()){
				points.put(key, 0.0);
			}
			
			for(String word : words){
				for(String key : map.keySet()){
					if(map.get(key).containsKey(word)){
						points.put(key, points.get(key) + (double)(map.get(key).get(word)) / cnts.get(key));
					}
				}				
			}
			
			String decision = "";
			double max = 0.0;
			for(String key : points.keySet()){
				if(points.get(key) > max){
					max = points.get(key);
					decision = key;
				}
			}
			System.out.println(type + " " + decision); 
			if(type.equals(decision)){
				correct++;
			}
			
//			double total = 0.0;
//			for(String key : points.keySet()){
//				System.out.println(key + ": " + points.get(key));
//				total += points.get(key);
//			}
//			System.out.println(total);
//			break;
		}
		
		System.out.println("Accuracy: " + ((double)correct/totalCount));
		
		sc.close();*/
		
	}
	
	public void getSomeFile() throws FileNotFoundException{
		File file = new File("src/shuffled-full-set-hashed.csv");
		Scanner sc = new Scanner(file);
		Random rand = new Random();
		int next = rand.nextInt(1000);
		for(int i = 0; i < next; i++){
			sc.nextLine();
		}
	
		String s = sc.nextLine();
		s = s.substring(s.indexOf(",") + 1);
		PrintWriter out = new PrintWriter("testfile.txt");
		out.print(s);
		out.close();
		
		sc.close();
	}
	
	
	public static void main(String[] args) throws IOException{
		
		FileClassifier fc = new FileClassifier();
		fc.getSomeFile();
		
	}
}
