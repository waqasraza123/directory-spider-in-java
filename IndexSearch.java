import java.util.*;
import java.util.Map.Entry;
import java.io.*;

public class IndexSearch implements Runnable{

private boolean boo;

  public void IndexSearch(HashMap files) throws IOException
  {
    Scanner in = new Scanner(System.in);
      
    while(true)
    {
      System.out.println("Enter text to search. Press E for exit.");
      String s = in.nextLine();
      
      if(s.equalsIgnoreCase("E"))
      {
        return;
      }
      
      //to traverse the map
      Set set = files.entrySet();
      Iterator i = set.iterator();
      
      while(i.hasNext()) 
      {
        Entry curr = (Entry) i.next();
        String val = (String) curr.getValue();
        File file = new File(val);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = null;
        while ((line = br.readLine()) != null) 
        {
          if(line.contains(s))
          {
        	  boo = true;
        	  System.out.printf("[%s] found in file: %s\n", s, val);
          }
        }
      }
      
      //if the desired text not found
      if(!boo){
      	  System.out.println("Could not find");
        }
      
    }
  }

  public void printIndex(HashMap files)
  {
    Set set = files.entrySet();
    Iterator s = set.iterator();
    System.out.println("Mapping: ");

    while(s.hasNext()) 
    {
      Entry curr = (Entry) s.next();
      System.out.print(curr.getKey() + " : " + curr.getValue() + "\n");
      // System.out.println(curr.getValue());
    }
  }
    
  public void run()
  {
    
    HashMap<String,String> listOfFiles = new HashMap();

    try
    {
      BufferedReader reader = new BufferedReader(new FileReader("log.txt"));
      String line;

      while((line = reader.readLine()) != null)
      {
        listOfFiles.put(line.split(" : ")[0], line.split(" : ")[1]);
      }

      IndexSearch(listOfFiles);
      reader.close();
    }
    catch (FileNotFoundException FNFexc)
    {
      System.out.println("Error reading log file.");
      FNFexc.printStackTrace();
      
    }
    catch (IOException IOexc)
    {
      System.out.println("Error reading log file.");
      IOexc.printStackTrace();
    }

    System.out.println("[IndexSearch] finished.");
    
  }
}