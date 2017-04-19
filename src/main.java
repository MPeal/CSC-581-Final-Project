import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class main 
{

	public static void main(String[] args) throws Exception
	{
		// TODO Auto-generated method stub
		
		//FILE NAME GOES HERE
		File f = new File("Reviews/griddle.txt");
		
	    FileInputStream fis = null;
	    BufferedInputStream bis = null;
	    DataInputStream dis = null;
	    
	    fis = new FileInputStream(f);
	      
	    bis = new BufferedInputStream(fis);
	    dis = new DataInputStream(bis);
	    BufferedReader br = new BufferedReader(new InputStreamReader(dis));
	    String line;
	    String txt = ""; //this serves as the text for the review
	    while((line = br.readLine()) != null)
	    {
	    	txt+= line;
	    }
	    review r = new review(txt); //create a review object using the text
	    br.close();
	    
	    r.sentencizer(); //break the review into sentences
	    r.sentenceTag(); //POS tag the sentences
	    
	    //Splits sentences word-by-word.  Each sentence is an array of words.
	    //These word arrays are then stored into an ArrayList, where each index is one sentence
	    ArrayList<String[]> splitSents = r.sentenceSplit();
	    //System.out.println("First sentence: "+Arrays.toString(splitSents.get(0)));
	    
	    r.fillWordDB(); //readies word databases
	    
	    //for each sentence, find negative or positive adj/adv/verb instances
	    for(int i=0; i<splitSents.size(); i++) //for each sentence...
	    {
	    	String[] s = splitSents.get(i); 
	    	System.out.println("MAIN PRINT "+Arrays.toString(splitSents.get(i)));
	    	r.negJJCount(s);
	    	r.posJJCount(s);
	    	r.posVerbCount(s);
	    	r.negVerbCount(s);
	    	r.posAdvCount(s);
	    	r.nAdvCount(s);
	    }
	    
	    //after counting each sentence above, assign vars to different instance counts
    	int njj = r.getNegJJ();
    	int pjj = r.getPosJJ();
    	int pv = r.getPosVerbs();
    	int nv = r.getNegVerbs();
    	int nadv = r.getNegAdv();
    	int padv = r.getPosAdv();
    	
    	System.out.println("---------MAIN TEST----------");
    	System.out.println("Negative adjective instances: "+njj);
    	System.out.println("Positive adjective instances: "+pjj);
    	System.out.println("Negative verb instances: "+nv);
    	System.out.println("Positive verb instances: "+pv);
    	System.out.println("Positive adverb instances: "+padv);
    	System.out.println("Negative adverb instances: "+nadv);
    	    	
    	double pRatio = r.getRatio(); //determine positivity ratio
    	System.out.println("Positivity ratio is: "+pRatio);
    	
    	String sentiment = r.classify(); //classify the review
    	System.out.println("Based on p-ratio, this review is: "+sentiment);	    
	}
}