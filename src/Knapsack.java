import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Knapsack {
	
	protected static final File dataset = new File("dataset.txt");
	protected static int capacity = 0;
	protected static ArrayList<Item> items = new ArrayList<>();
	protected static ArrayList<ArrayList<Item>> combinations = new ArrayList<>();
	//protected static ArrayList<ArrayList<Item>> subsets = new ArrayList<>();
	protected static ArrayList<ArrayList<Integer>> binaryCombinations = new ArrayList<>();
	protected static ArrayList<Integer> binary = new ArrayList<>();
	protected static ArrayList<Item> bestSubset = new ArrayList<>();
	public static int maxValue = 0;
	protected static int[] bestBinarySet;
	//protected static int finalTotalValue = 0;
	//protected static int finalTotalWeight = 0;
	protected static int globalI = 0;
	
	
	public static void extract() throws IOException{
		boolean first = true;
		BufferedReader brItem = null;
	
		try {
			brItem = new BufferedReader(new FileReader(dataset));
			
			String currentLine;
			int i = 1;
			while ((currentLine = brItem.readLine()) != null) {
				if(first){
					capacity = Integer.parseInt(currentLine);
					first = false;
				}
				else{
					String[] line = currentLine.split("\\s+");
					int value = Integer.parseInt(line[0]);
					int weight = Integer.parseInt(line[1]);
					Item item = new Item(i, value, weight);
					items.add(item);
					i++;
				}
			}
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
			
	}
	
	public static ArrayList<Integer> convertToBinary(int  x, ArrayList<Integer> binary){
		int rest = x%2;
		binary.add(rest);
		int div = x/2;
		if(div != 0) convertToBinary(div, binary);
		else {
			if(binary.size() <  3){
				while(binary.size() != items.size()){
					binary.add(0);
				}
			}
			//binaryCombinations.add(binary);
			
		}	
		//if(x == 0) System.out.println(binary);
		return binary;
	}
	
	public static  ArrayList<Item>  generateCombinations(int i){
		//double numOfComb = Math.pow(2, items.size());
		//for(int i = 0; i < numOfComb; i ++){
			 ArrayList<Integer> binary = convertToBinary(i, new ArrayList<Integer>());
			 ArrayList<Item> newCombination = new ArrayList<>();
				for(Item item : items){
					Item newItem = new Item(0, 0, 0);
					newItem.id = item.id;
					newItem.value = item.value;
					newItem.weight = item.weight;
					newItem.take = item.take;
					newCombination.add(newItem);
				}
				for(int  j= 0; j < binary.size(); j++){
					int binaryTake = binary.get(j);
					if(binaryTake == 0) newCombination.get(j).take = false;
					if(binaryTake == 1) newCombination.get(j).take = true;
				}
				 return newCombination;
		//}
		//System.out.println(binaryCombinations);
		/*for(int i = 0; i < numOfComb; i++){
			ArrayList<Integer> binary = binaryCombinations.get(i);
			ArrayList<Item> newCombination = new ArrayList<>();
			for(Item item : items){
				Item newItem = new Item(0, 0, 0);
				newItem.id = item.id;
				newItem.value = item.value;
				newItem.weight = item.weight;
				newItem.take = item.take;
				newCombination.add(newItem);
			}
			for(int  j= 0; j < binary.size(); j++){
				int binaryTake = binary.get(j);
				if(binaryTake == 0) newCombination.get(j).take = false;
				if(binaryTake == 1) newCombination.get(j).take = true;
			}
			//System.out.println(newCombination);
			combinations.add(newCombination);
			
		}
		//System.out.println(combinations);*/
	}
	
	public static void bruteForce( ArrayList<Item> subset){
		//ArrayList<Item> bestSubset = new ArrayList<>();
		//ArrayList<Item> finalBestSubset = new ArrayList<>();
		//int maxValue = - 1;
		//for(int i = 0; i < combinations.size(); i++){
			//if(i%1000 == 0) System.out.println(i);
			//globalI = i;
			//ArrayList<Item> subset = combinations.get(i);
			int totalWeight = 0;
			int totalValue = 0;
			for(Item item : subset){
				if(item.take == true) {
					totalWeight = totalWeight +item.weight;
					totalValue = totalValue + item.value;
				}
			}
			if(totalWeight <= capacity){
				if(totalValue > maxValue) {
					 //System.out.println(subset);
					// System.out.println(totalValue);
					bestSubset = subset;
					//System.out.println(subset);
					maxValue = totalValue;
					//System.out.println(maxValue);
				}
			} 
			//else System.out.println("Exceed");
		//}
		//System.out.println(bestSubset);
		//for(int i = 0; i < bestSubset.size(); i++){
		//	if(bestSubset.get(i).take == true) finalBestSubset.add(bestSubset.get(i));
		//}
		//return bestSubset;
	}
	
	public static void totalValueAndWeight(ArrayList<Item> subset){
		int totalWeight = 0;
		int totalValue = 0;
		for(Item item : subset){
			if(item.take == true){
				totalWeight = totalWeight +item.weight;
				totalValue = totalValue + item.value;
			}
		}
		System.out.println("Total Value: " + totalValue + " Total Weight: " + totalWeight);
	}
	
	public static void main(String[] args) throws IOException{
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {  public void run() {
		       System.out.println("I still work"); 
	    }}, 0, 2000);
		
		double startTime = System.currentTimeMillis();

		extract();
		
		for(int i = 0; i < Math.pow(2, items.size()); i++){
			ArrayList<Item> subset = generateCombinations(i);
			bruteForce(subset);
			//System.out.println(bestSubset);
		}
		
		//for(int i = bestSubset.size()-1; i > -1; i--){
		//	if(i == bestSubset.size()-1) System.out.print("[" + bestSubset.get(i) + ", ");
		//	else if(i == 0) System.out.print(bestSubset.get(i) + "]");
			//else System.out.print(bestSubset.get(i) + ", ");
		//}
		
		System.out.print(bestSubset);
		
		System.out.println("");
		
		totalValueAndWeight(bestSubset);
		
		System.out.println("Capacity: " + capacity);
		
		double endTime   = System.currentTimeMillis();
		double totalTime = endTime - startTime;
		System.out.println(totalTime/1000 + " seconds");
		
		timer.cancel();
	
	}

}
