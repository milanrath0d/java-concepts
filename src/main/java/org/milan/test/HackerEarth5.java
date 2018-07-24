package org.milan.test;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerEarth5{
	
static Map<String, Integer> treeMap=new TreeMap<>();	

public static void main(String args[] ) throws Exception {
        
		Scanner sn = new Scanner(System.in);
        int N = sn.nextInt();
        sn.nextLine();
        
        
        for(int i=0;i<N;i++){
        	String tweet=sn.nextLine();
        	extractHashTags(tweet);
        }
        treeMap=sortByValues(treeMap);
        int count=0;
        Iterator<Entry<String, Integer>> it=treeMap.entrySet().iterator();
        while(count<5){
        	System.out.println(it.next().getKey());
        	count++;
        }
        
	
} 
		private static void extractHashTags(String tweet){
			Pattern pattern = Pattern.compile("#\\w+");

			Matcher matcher = pattern.matcher(tweet);
			while (matcher.find())
			{
				String str=matcher.group();
				if(treeMap.containsKey(str)){
					int count=treeMap.get(str);
					treeMap.put(str,count+1);
				}
				else{
					treeMap.put(str, 1);
				}
				
			}
		}
		
		public static <K, V extends Comparable<V>> Map<K, V> 
	    sortByValues(final Map<K, V> map) {
	    Comparator<K> valueComparator = 
	             new Comparator<K>() {
	      public int compare(K k1, K k2) {
	        int compare = 
	              map.get(k2).compareTo(map.get(k1));
	        if (compare == 0) 
	          return 1;
	        else 
	          return compare;
	      }
	    };
	 
	    Map<K, V> sortedByValues = 
	      new TreeMap<K, V>(valueComparator);
	    sortedByValues.putAll(map);
	    return sortedByValues;
	  }
		
	 private void sortLogicForTreeMapBasedOnValues(){
		 List<String> list = new ArrayList<>();
		 while(!treeMap.isEmpty()){
			 
	            int max = Integer.MIN_VALUE;
	            String ht = "";
	            for (String hs : treeMap.keySet()) {
	 
	                if (treeMap.get(hs)>max){
	                    max = treeMap.get(hs);
	                    ht = hs;
	                }
	            }
	 
	            treeMap.remove(ht,max);
	            list.add(ht);
	        }
	 
	 }
		
}
