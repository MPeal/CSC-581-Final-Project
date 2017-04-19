import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class review 
{
	private String review;
	private String[] sentences;
	private int sentenceCount;
	private String[] taggedSentences;
	
	public String[] negVerbs = new String[100]; //negative verbs
	public String[] posVerbs = new String[58]; //positive verbs
	public String[] posJJ = new String[60]; //positive adjectives
	public String[] negJJ = new String[55]; //negative adjectives
	public String[] negAdv = new String[4]; //negative adverbs
	public String[] nAdv = new String[38]; //negative '-ly' adverbs
	public String[] posAdv = new String[50]; //positive adverbs
	
	private int pvcount;
	private int nvcount;
	private int pjjcount;
	private int njjcount;
	private int padvcount;
	private int nadvcount;
	private double pRatio; //positivity ratio
	private String cl; //class, i.e. negative, positive or neutral
	
	public review(String r) //constructor
	{
		this.review = r;
		pvcount = 0;
		nvcount = 0;
		pjjcount = 0;
		njjcount = 0;
		padvcount = 0;
		nadvcount = 0;
		pRatio = 0;
		cl = "";
	}
	
	@Override
	public String toString()
	{
		return this.review;
	}
	
	public String[] sentencizer()
	{
		String r = review.toString();
		this.sentences = r.split("(?<=[.!?])\\s*");
		//System.out.println(Arrays.toString(sentences));
		sentenceCount = sentences.length;
		return sentences;
	}
	
	public String[] sentenceTag() throws Exception
	{
		taggedSentences = new String[sentences.length];
		for (int i=0; i < sentences.length; i++)
		{
	        MaxentTagger tagger = new MaxentTagger("tagger folder/bidirectional-distsim-wsj-0-18.tagger");        
	        String tagged = tagger.tagString(sentences[i]);
	        taggedSentences[i] = tagged;
		}
		//System.out.println(Arrays.toString(taggedSentences));
		return taggedSentences;
	}
	
	public ArrayList<String[]> sentenceSplit()
	{
		ArrayList<String[]> arrList = new ArrayList<String[]>();
		for (int i=0; i < taggedSentences.length; i++)
		{
			String[] words = taggedSentences[i].split(" ");
			//System.out.println("Word array: "+Arrays.toString(words));
			arrList.add(words);
		}
		return arrList;
	}
	
	public void fillNegVerbs() throws Exception
	{
		File nVerbs = new File("negative verbs.txt");
	    FileInputStream fis = null;
	    BufferedInputStream bis = null;
	    DataInputStream dis = null;
	    
	    fis = new FileInputStream(nVerbs);
	      
	    bis = new BufferedInputStream(fis);
	    dis = new DataInputStream(bis);
	    BufferedReader br = new BufferedReader(new InputStreamReader(dis));
	    String line;
	    int i=0;
	    while((line = br.readLine()) != null)
    	{
	    	negVerbs[i] = line;
	    	i++;
    	}
	    br.close();
	}
	
	public void fillPosVerbs() throws Exception
	{
		File pVerbs = new File("positive verbs.txt");
	    FileInputStream fis = null;
	    BufferedInputStream bis = null;
	    DataInputStream dis = null;
	    
	    fis = new FileInputStream(pVerbs);
	      
	    bis = new BufferedInputStream(fis);
	    dis = new DataInputStream(bis);
	    BufferedReader br = new BufferedReader(new InputStreamReader(dis));
	    String line;
	    int i=0;
	    while((line = br.readLine()) != null)
    	{
	    	posVerbs[i] = line;
	    	i++;
    	}
	    br.close();
	}

	public void fillPosJJ() throws Exception
	{
		File pJJ = new File("positive adjectives.txt");
	    FileInputStream fis = null;
	    BufferedInputStream bis = null;
	    DataInputStream dis = null;
	    
	    fis = new FileInputStream(pJJ);
	      
	    bis = new BufferedInputStream(fis);
	    dis = new DataInputStream(bis);
	    BufferedReader br = new BufferedReader(new InputStreamReader(dis));
	    String line;
	    int i=0;
	    while((line = br.readLine()) != null)
    	{
	    	posJJ[i] = line;
	    	i++;
    	}
	    br.close();
	}
	
	public void fillNegJJ() throws Exception
	{
		File nJJ = new File("negative adjectives.txt");
	    FileInputStream fis = null;
	    BufferedInputStream bis = null;
	    DataInputStream dis = null;
	    
	    fis = new FileInputStream(nJJ);
	      
	    bis = new BufferedInputStream(fis);
	    dis = new DataInputStream(bis);
	    BufferedReader br = new BufferedReader(new InputStreamReader(dis));
	    String line;
	    int i=0;
	    while((line = br.readLine()) != null)
    	{
	    	negJJ[i] = line;
	    	i++;
    	}
	    br.close();
	}
	
	public void fillNegAdv() throws Exception
	{
		File nAdv = new File("negative adverbs.txt");
	    FileInputStream fis = null;
	    BufferedInputStream bis = null;
	    DataInputStream dis = null;
	    
	    fis = new FileInputStream(nAdv);
	      
	    bis = new BufferedInputStream(fis);
	    dis = new DataInputStream(bis);
	    BufferedReader br = new BufferedReader(new InputStreamReader(dis));
	    String line;
	    int i=0;
	    while((line = br.readLine()) != null)
    	{
	    	negAdv[i] = line;
	    	i++;
    	}
	    br.close();
	}

	public void fillpAdv() throws Exception
	{
		File pAdv = new File("positive adverbs.txt");
	    FileInputStream fis = null;
	    BufferedInputStream bis = null;
	    DataInputStream dis = null;
	    
	    fis = new FileInputStream(pAdv);
	      
	    bis = new BufferedInputStream(fis);
	    dis = new DataInputStream(bis);
	    BufferedReader br = new BufferedReader(new InputStreamReader(dis));
	    String line;
	    int i=0;
	    while((line = br.readLine()) != null)
    	{
	    	posAdv[i] = line;
	    	i++;
    	}
	    br.close();
	}
	
	public void fillnAdv() throws Exception
	{
		File f = new File("nadverbs.txt");
	    FileInputStream fis = null;
	    BufferedInputStream bis = null;
	    DataInputStream dis = null;
	    
	    fis = new FileInputStream(f);
	      
	    bis = new BufferedInputStream(fis);
	    dis = new DataInputStream(bis);
	    BufferedReader br = new BufferedReader(new InputStreamReader(dis));
	    String line;
	    int i=0;
	    while((line = br.readLine()) != null)
    	{
	    	nAdv[i] = line;
	    	i++;
    	}
	    br.close();
	}
	
	public void fillWordDB() throws Exception
	{
		fillNegVerbs();
		fillPosVerbs();
		fillPosJJ();
		fillNegJJ();
		fillNegAdv();
		fillpAdv();
		fillnAdv();
	}

	public boolean nAdverbCheck(String word) //checks whether a word is matching negative adverb
	{
		boolean badAd = false;
		word = word.toLowerCase();
		for(int j=0; j<negAdv.length; j++) //compare to each stored negAdv
		{
			if (word.equals(negAdv[j])) 
				{
					badAd = true; //if word matches stored adverbs, boolean value is true
				}
		}
	return badAd;		
	}

	public void negJJCount(String[] sentence)
	{		
		for (int i=0; i<sentence.length; i++)//for every word in the sentence...
		{
			if(sentence[i].contains("/JJR") || sentence[i].contains("/JJS")) //if word is marked as adjective
			{
				//slice off the POStag to get root word
				String isoWord = sentence[i].substring(0,sentence[i].length()-4);
				isoWord= isoWord.toLowerCase();
				for(int j=0; j<negJJ.length; j++) //compare to each stored negJJ
				{
					if (isoWord.equals(negJJ[j])) 
					{
						String prevWord = "";
						//check if previous word is affecting negative adverb
						if (i>0)
						{
							prevWord = sentence[i-1];
							prevWord = prevWord.substring(0, prevWord.length()-3);
						}
						//check if previous word is affecting negative adverb
						
						//if the previous word is a negative adverb...
						if (nAdverbCheck(prevWord) == true)
						{
							pjjcount++; //+1 to opposite adjective count
						}
						else //if there are no affecting adverbs
						{
							njjcount++; //+1 to negative adjective count
						}
					}
				}
			}
			else if (sentence[i].contains("/JJ"))
			{
				//slice off the POStag to get root word
				String isoWord = sentence[i].substring(0,sentence[i].length()-3);
				isoWord= isoWord.toLowerCase();
				//System.out.println(isoWord);
				for(int j=0; j<negJJ.length; j++) //compare to each stored negJJ
				{
					if (isoWord.equals(negJJ[j])) 
					{
						String prevWord = "";
						//check if previous word is affecting negative adverb
						if (i>0)
						{
							prevWord = sentence[i-1];
							prevWord = prevWord.substring(0, prevWord.length()-3);
						}
						//check if previous word is affecting negative adverb
						
						//if the previous word is a negative adverb...
						if (nAdverbCheck(prevWord) == true)
						{
							pjjcount++; //+1 to opposite adjective count
						}
						else //if there are no affecting adverbs
						{
							njjcount++; //+1 to negative adjective count
						}
					}
				}
			}
		}
	}

	public void posJJCount(String[] sentence)
	{
		for (int i=0; i<sentence.length; i++) //for every word in the sentence...
		{
			if(sentence[i].contains("/JJS") || sentence[i].contains("/JJR")) //if word is marked as adjective
			{
				//slice off the POStag to get root word
				String isoWord = sentence[i].substring(0,sentence[i].length()-4);
				isoWord= isoWord.toLowerCase();
				//System.out.println(isoWord);
				for(int j=0; j<posJJ.length; j++) //compare to each stored posJJ
				{
					if (isoWord.equals(posJJ[j]))
					{
						//check if previous word is affecting negative adverb
						String prevWord = "";
						//check if previous word is affecting negative adverb
						if (i>0)
						{
							prevWord = sentence[i-1];
							prevWord = prevWord.substring(0, prevWord.length()-3);
						}
						//if the previous word is a negative adverb...
						if (nAdverbCheck(prevWord) == true)
						{
							njjcount++; //+1 to opposite adjective count
						}
						else //if there are no affecting adverbs
						{
							pjjcount++; //+1 to negative adjective count
						}
					}
				}
			}
			else if (sentence[i].contains("/JJ"))
			{
				//slice off the POStag to get root word
				String isoWord = sentence[i].substring(0,sentence[i].length()-3);
				isoWord= isoWord.toLowerCase();
				//System.out.println(isoWord);
				for(int j=0; j<posJJ.length; j++) //compare to each stored posJJ
				{
					if (isoWord.equals(posJJ[j]))
					{
						//check if previous word is affecting negative adverb
						String prevWord = "";
						//check if previous word is affecting negative adverb
						if (i>0)
						{
							prevWord = sentence[i-1];
							prevWord = prevWord.substring(0, prevWord.length()-3);
						}
						//if the previous word is a negative adverb...
						if (nAdverbCheck(prevWord) == true)
						{
							njjcount++; //+1 to opposite adjective count
						}
						else //if there are no affecting adverbs
						{
							pjjcount++; //+1 to negative adjective count
						}
					}
				}
			}
		}
	}

	public void posVerbCount(String[] sentence)
	{		
		for (int i=0; i<sentence.length; i++) //for every word in the sentence...
		{
			String isoWord = "";
			//if word is marked as a verb
			
			//verbs have different markings, so account for each marking
			if(sentence[i].contains("/VBD") || sentence[i].contains("/VBG") || sentence[i].contains("/VBN") || sentence[i].contains("/VBP") || sentence[i].contains("/VBZ"))
			{
				//slice off the POStag to get root word
				isoWord = sentence[i].substring(0, (sentence[i].length())-4);
				isoWord= isoWord.toLowerCase();
				//System.out.println("Verb found: "+isoWord);
				for(int j=0; j<posVerbs.length; j++) //compare to each stored posVerbs
				{
					//if there is a match...
					if (isoWord.equals(posVerbs[j]))
					{
						String prevWord = "";
						//check if previous word is affecting negative adverb
						if (i>0)
						{
							prevWord = sentence[i-1];
							prevWord = prevWord.substring(0, prevWord.length()-4);
						}
						
						//if the previous word is a negative adverb...
						if (nAdverbCheck(prevWord) == true)
						{
							nvcount++; //+1 to opposite verb count
						}
						else //if there are no affecting adverbs
						{
							pvcount++; //+1 to positive verb count
						}
					}
				}
			}
			else if(sentence[i].contains("/VB")) //the basic form verb mark 
			{
				//slice off the POStag to get root word
				isoWord = sentence[i].substring(0,sentence[i].length()-3);
				isoWord= isoWord.toLowerCase();
				//System.out.println("Verb found: "+isoWord);
				for(int j=0; j<posVerbs.length; j++) //compare to each stored posVerb
				{
					if (isoWord.equals(posVerbs[j]))
					{
						String prevWord = "";
						//check if previous word is affecting negative adverb
						if (i>0)
						{
							prevWord = sentence[i-1];
							prevWord = prevWord.substring(0, prevWord.length()-3);
						}
						
						//if the previous word is a negative adverb...
						if (nAdverbCheck(prevWord) == true)
						{
							nvcount++; //+1 to opposite verb count
						}
						else //if there are no affecting adverbs
						{
							pvcount++; //+1 to positive verb count
						}
					}
				}
			}
		}
	}

	public void negVerbCount(String[] sentence)
	{		
		for (int i=0; i<sentence.length; i++) //for every word in the sentence...
		{
			String isoWord = "";
			//if word is marked as a verb
			
			//verbs have different markings, so account for each marking
			if(sentence[i].contains("/VBD") || sentence[i].contains("/VBG") || sentence[i].contains("/VBN") || sentence[i].contains("/VBP") || sentence[i].contains("/VBZ"))
			{
				//slice off the POStag to get root word
				isoWord = sentence[i].substring(0, (sentence[i].length())-4);
				isoWord= isoWord.toLowerCase();
				//System.out.println("Verb found: "+isoWord);
				for(int j=0; j<negVerbs.length; j++) //compare to each stored posVerbs
				{
					//if there is a match...
					if (isoWord.equals(negVerbs[j]))
					{
						//check if previous word is affecting negative adverb
						String prevWord = "";
						//check if previous word is affecting negative adverb
						if (i>0)
						{
							prevWord = sentence[i-1];
							prevWord = prevWord.substring(0, prevWord.length()-4);
						}
						
						//if the previous word is a negative adverb...
						if (nAdverbCheck(prevWord) == true)
						{
							pvcount++; //+1 to opposite verb count
						}
						else //if there are no affecting adverbs
						{
							nvcount++; //+1 to positive verb count
						}
					}
				}
			}
			else if(sentence[i].contains("/VB")) //the basic form verb mark 
			{
				//slice off the POStag to get root word
				isoWord = sentence[i].substring(0,sentence[i].length()-3);
				isoWord= isoWord.toLowerCase();
				//System.out.println("Verb found: "+isoWord);
				for(int j=0; j<negVerbs.length; j++) //compare to each stored posVerb
				{
					if (isoWord.equals(negVerbs[j]))
					{
						String prevWord = "";
						//check if previous word is affecting negative adverb
						if (i>0)
						{
							prevWord = sentence[i-1];
							prevWord = prevWord.substring(0, prevWord.length()-3);
						}
						
						//if the previous word is a negative adverb...
						if (nAdverbCheck(prevWord) == true)
						{
							pvcount++; //+1 to opposite verb count
						}
						else //if there are no affecting adverbs
						{
							nvcount++; //+1 to positive verb count
						}
					}
				}
			}
		}
	}

	public void posAdvCount(String[] sentence)
	{
		for (int i=0; i<sentence.length; i++) //for every word in the sentence...
		{
			String isoWord = "";
			
			//if the word is marked as a comparative or superlative adverb
			if(sentence[i].contains("/RBR") || sentence[i].contains("/RBS"))
			{
				//slice off the POStag to get root word
				isoWord = sentence[i].substring(0, (sentence[i].length())-4);
				isoWord= isoWord.toLowerCase();
				
				for(int j=0; j<posAdv.length; j++) //compare to each stored posAdv
				{
					//if there is a match...
					if (isoWord.equals(posAdv[j]))
					{
						String prevWord = "";
						//check if previous word is affecting negative adverb
						if (i>0)
						{
							prevWord = sentence[i-1];
							prevWord = prevWord.substring(0, prevWord.length()-4);
						}
						
						//if the previous word is a negative adverb...
						if (nAdverbCheck(prevWord) == true)
						{
							nadvcount++; //+1 to opposite advverb count
						}
						else //if there are no affecting adverbs
						{
							padvcount++; //+1 to positive adverb count
						}
					}
				}
			}
			else if(sentence[i].contains("/RB"))
			{
				//slice off the POStag to get root word
				isoWord = sentence[i].substring(0, (sentence[i].length())-3);
				isoWord= isoWord.toLowerCase();
				//System.out.println(isoWord);
				
				for(int j=0; j<posAdv.length; j++) //compare to each stored posAdv
				{
					//if there is a match...
					if (isoWord.equals(posAdv[j]))
					{
						String prevWord = "";
						//check if previous word is affecting negative adverb
						if (i>0)
						{
							prevWord = sentence[i-1];
							prevWord = prevWord.substring(0, prevWord.length()-4);
						}
						
						//if the previous word is a negative adverb...
						if (nAdverbCheck(prevWord) == true)
						{
							nadvcount++; //+1 to opposite advverb count
						}
						else //if there are no affecting adverbs
						{
							padvcount++; //+1 to positive adverb count
						}
					}
				}
				
			}
			
		}
	}
	
	public void nAdvCount(String[] sentence)
	{
		for (int i=0; i<sentence.length; i++) //for every word in the sentence...
		{
			String isoWord = "";
			
			//if the word is marked as a comparative or superlative adverb
			if(sentence[i].contains("/RBR") || sentence[i].contains("/RBS"))
			{
				//slice off the POStag to get root word
				isoWord = sentence[i].substring(0, (sentence[i].length())-4);
				isoWord= isoWord.toLowerCase();
				
				for(int j=0; j<nAdv.length; j++) //compare to each stored nAdv
				{
					//if there is a match...
					if (isoWord.equals(nAdv[j]))
					{
						String prevWord = "";
						//check if previous word is affecting negative adverb
						if (i>0)
						{
							prevWord = sentence[i-1];
							prevWord = prevWord.substring(0, prevWord.length()-4);
						}
						
						//if the previous word is a negative adverb...
						if (nAdverbCheck(prevWord) == true)
						{
							padvcount++; //+1 to opposite adverb count
						}
						else //if there are no affecting adverbs
						{
							nadvcount++; //+1 to positive adverb count
						}
					}
				}
			}
			else if(sentence[i].contains("/RB"))
			{
				//slice off the POStag to get root word
				isoWord = sentence[i].substring(0, (sentence[i].length())-3);
				isoWord= isoWord.toLowerCase();
				//System.out.println(isoWord);
				
				for(int j=0; j<nAdv.length; j++) //compare to each stored nAdv
				{
					//if there is a match...
					if (isoWord.equals(nAdv[j]))
					{
						String prevWord = "";
						//check if previous word is affecting negative adverb
						if (i>0)
						{
							prevWord = sentence[i-1];
							prevWord = prevWord.substring(0, prevWord.length()-4);
						}
						
						//if the previous word is a negative adverb...
						if (nAdverbCheck(prevWord) == true)
						{
							padvcount++; //+1 to opposite adverb count
						}
						else //if there are no affecting adverbs
						{
							nadvcount++; //+1 to negative adverb count
						}
					}
				}
				
			}			
		}
	}
	
	public int getPosAdv()
	{
		return padvcount;
	}
	
	public int getNegAdv()
	{
		return nadvcount;
	}
	
	public int getNegJJ()
	{
		return njjcount;
	}
	
	public int getPosJJ()
	{
		return pjjcount;
	}
	
	public int getPosVerbs()
	{
		return pvcount;
	}
	
	public int getNegVerbs()
	{
		return nvcount;
	}

	public double getRatio()
	{
		double pi = pvcount+pjjcount+padvcount; //all positive instances
		double ni = nvcount+njjcount+nadvcount; //all negative instances
		
		if (pi == 0 && ni ==0) //if there are no instances either way
		{
			pRatio = 1.0;
		}
		else
		{
			pRatio = pi/ni;
		}
		
		return pRatio;
	}
	
	public String classify()
	{
		double pRatio = getRatio();
		
		if (pRatio >= 0.75 && pRatio <= 1.25)
		{
			cl = "neutral";
		}
		else if(pRatio < 0.75)
		{
			cl = "negative";
		}
		else if (pRatio > 1.25)
		{
			cl = "positive";
		}
		
		return cl;
	}

}