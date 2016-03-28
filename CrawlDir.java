import java.util.*;
import java.util.Map.Entry;
import java.io.*;

public class CrawlDir implements Runnable
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

  public static Collection<File> getChain()
  {
    return chain;
  }
    
  public void run() 
  {
    addTree(new File("E:\\Waqas\\WAQAS\\STUDY\\Semester 6\\Accounting"), chain);
  }
}
