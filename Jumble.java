import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.ArrayList;
public class Jumble 
{
	public static String bubbleSort(char[] x) 
	{
	    int newLowest = 0;            // index of first comparison
	    int newHighest = x.length-1;  // index of last comparison
	  
	    while (newLowest < newHighest) 
	    {
	        int highest = newHighest;
	        int lowest  = newLowest;
	        newLowest = x.length;    // start higher than any legal index
	        for (int i=lowest; i<highest; i++) 
	        {
	            if (x[i] > x[i+1]) 
	            {
	               // exchange elements
	               char temp = x[i];  x[i] = x[i+1];  x[i+1] = temp;
	               if (i<newLowest) 
	               {
	                   newLowest = i-1;
	                   if (newLowest < 0) 
	                   {
	                       newLowest = 0;
	                   }
	               }
	               else if (i>newHighest) 
	               {
	                   newHighest = i+1;
	               }
	            }
	        }
	    }
	    return new String(x);
	}
	
	public static void main(String[] args) 
	{
		try
		{
			BufferedReader dictBufferedReader = new BufferedReader(new FileReader("dict.txt"));
			HashMap<String, ArrayList<String>> dict = new HashMap<String, ArrayList<String>>();
			ArrayList<String> tempArrayList;
			String dictWord, key;
			int count = 0;
			
			System.out.print("Setting up dictionary...");
			
			while((dictWord = dictBufferedReader.readLine()) != null)
			{
				dictWord = dictWord.toLowerCase();
				key = bubbleSort(dictWord.toCharArray());
				if(dict.containsKey(key))
				{
					tempArrayList = dict.get(key);
					if(!tempArrayList.contains(dictWord))
						dict.get(key).add(dictWord);
				}
				else
				{
					ArrayList<String> t = new ArrayList<String>();
					t.add(dictWord);
					dict.put(key, t);
				}
				if(count % 2500 == 0)
					System.out.print(".");
				count++;
			}
			System.out.println();
			
			String jumbledWord;
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Please input string to decode:");
			while(!(jumbledWord = in.readLine()).equals("quit"))
			{
				key = bubbleSort(jumbledWord.toCharArray());
				
				if(dict.containsKey(key))
				{
					System.out.println("Options:");
					for(String s : dict.get(key))
						System.out.println(s);
				}
				else
				{
					System.out.println("No Words Found...");
				}
				
				System.out.println("Please input string to decode:");
			}
		}
		catch(Exception e)
		{
			System.err.println("Failed");
			return;
		}
	}
}
