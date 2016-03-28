import java.util.*;
import java.util.Map.Entry;
import java.io.*;

public class Main
{
	public static Collection<File> chain = new ArrayList<File>();

	public static void main(String[] args) throws InterruptedException
	{
		Thread crawlThread = new Thread(new CrawlDir());
		Thread indexThread = new Thread(new Index());
		Thread searchThread = new Thread(new IndexSearch());

		searchThread.start();

		crawlThread.start();
		crawlThread.join();

		chain = CrawlDir.getChain();

		Index.setChain(chain);
		indexThread.start();
		indexThread.join();
		
		
	}
}