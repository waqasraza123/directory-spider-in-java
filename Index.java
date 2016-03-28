import java.util.*;
import java.util.Map.Entry;
import java.io.*;

public class Index implements Runnable
{
  public static Collection<File> chain = new ArrayList<File>();

  public void addTree(File file, Collection<File> chain) 
  {
    File[] children = file.listFiles();
    if (children != null) 
    {
      for (File child : children) 
      {
        addTree(child, chain);
      }
    }
    else
    {
      chain.add(file);
    }
  }

  public void IndexSearch(HashMap files) throws IOException
  {
    Scanner in = new Scanner(System.in);
      
    while(true)
    {
      System.out.println("Enter text to search. Press E for exit.");
      String s = in.nextLine();
      
      if(s.equalsIgnoreCase("E"))
      {
        System.out.println("A log file is created containing the indexed files.");
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
            System.out.printf("[%s] found in file: %s\n", s, val);
          }
        }
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
    }
  }

  public static void setChain(Collection<File> givenChain)
  {
    chain = givenChain;
  }
    
  public void run() 
  {
    
    try 
    {
      BufferedWriter out = new BufferedWriter(new FileWriter("log.txt"));

      Iterator itr = chain.iterator();
      while(itr.hasNext())
      {
        File file_ = (File) itr.next();

        String fileName = file_.getName();
        String filePath = file_.getPath();
        
        out.write(fileName + " : " + filePath);
        out.newLine();
        
      }
      out.close();
    }
    catch(IOException ex)
    {
      //ex.printStackTrace();
    }
  }
}
