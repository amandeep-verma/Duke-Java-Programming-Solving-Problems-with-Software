import edu.duke.*;
import java.io.*;
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    
    
    
    public static StorageResource getAllGenes(String dna)
    {
        
        StorageResource geneList = new StorageResource();
        
        int startIndex =0;
        while(true)
        {
            String currentGene = findGene(dna, startIndex);
            if ( currentGene == "")
            {
                break;
            }
            
            geneList.add(currentGene);
            startIndex = dna.indexOf(currentGene,startIndex) + currentGene.length();
        }
        
        return geneList;
    }
    
    
    public static void printAllGenes(String dna)
    {
        int startIndex =0;
        while(true)
        {
            String currentGene = findGene(dna, startIndex);
            if ( currentGene == "")
            {
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene,startIndex) + currentGene.length();
        }
    }
    
    
    public static String findGene(String dnaOrignal, int index)
    {
        String dna = dnaOrignal.toUpperCase();
        int start = dna.indexOf("ATG", index);
        if (start < 0)
            return "";
        int endTAA = findStopCodon(dna, start, "TAA");
        int endTGA = findStopCodon(dna, start, "TGA");
        int endTAG = findStopCodon(dna, start, "TAG");
        
        int end = Math.min(endTGA,(Math.min(endTAA, endTAG)));
        
        if (endTAA ==-1)
        {
            end = Math.min(endTGA, endTAG);
            if ( end == -1)
                end = Math.max(endTGA, endTAG);
        }
        else if( endTGA ==-1)
        {
            end = Math.min(endTAA, endTAG);
            if ( end == -1)
                end = endTAA;
        }
        else if( endTAG ==-1)
            end = Math.min(endTAA, endTGA);
        
            
        
        if (end ==-1)
            return "";
        return dnaOrignal.substring(start, end+3);
        
    }
    
    /*This method returns the index of the first occurrence of stopCodon that appears 
    past startIndex and is a multiple of 3 away from startIndex. If there is no such
    topCodon, this method returns the length of the dna strand.*/
    public static int findStopCodon(String dna, int startIndex,String stopCodon)
    {
        int currIndex = dna.indexOf(stopCodon, startIndex+3);
        while( currIndex != -1)
        {
            int diff = currIndex - startIndex;
            if (diff %3 ==0)
            {
                return currIndex;
            }
            else
                currIndex = dna.indexOf(stopCodon, currIndex+1);
        }
        return -1;
    }
    public static double cgRatio(String dna)
    {
        int countC=0;
        int countG=0;
        String lowdna = dna.toUpperCase();
        for( int i =0; i<dna.length();i++)
        {
            if (lowdna.charAt(i) == 'C')
                countC++;
            if (lowdna.charAt(i) == 'G')
                countG++;
        }
        //System.out.println("-----"+countC+" "+countG);
        return ((double)countC+countG*1.0)/dna.length();
    }
    
    public static int countCTG( String dna)
    {
        int Count=0;
        int index=-1;
        while(true)
        {
            index = dna.indexOf("CTG",index+1);
            if(index ==-1)
            	break;
            Count++;
            
        }
        
        return Count;
    }
    
    public static void processGenes(StorageResource sr)
    {
        int countL=0;
        int countCG=0;
        int longest=0;
        System.out.println(" Strings in srlonger than 9 characters");
        for( String g: sr.data())
        {
            if(g.length()>60)
            {
                System.out.println(g);
                countL++;
            }
        }
        System.out.println("Strings in sr whose C-G-ratio is higher than 0.35");
        for( String g: sr.data())
        {
            if (cgRatio(g) >0.35)
            {
                System.out.println(g);
                System.out.println("The CG ratio is "+cgRatio(g));
                countCG++;
            }
            if (g.length()>longest)
            {
                longest=g.length();
            }
        }
        System.out.println("number of Stringslonger than 60 characters= "+countL);
        System.out.println("number of strings whose C-G-ratio is higher than 0.35= "+countCG);
        System.out.println("length of the longest gene "+longest);
    }
    
    public static void testProcessGenes()
    {
        URLResource file = new  URLResource("https://users.cs.duke.edu/~rodger/GRch38dnapart.fa");
        FileResource fr = new FileResource("brca1line.fa");
        String dna = file.asString();
        //String dna = "nonCodingDNAxxxMyGeneATGmyGenexTAAxxGeneATGTAACATGTAAATGCendTAATAAnonCodingDNAxTAGxTGA";
        //String dna = "oneAtGMyGeneOneAATGGGGTAATGATAGAACCCGGYGGGGTAGGGCTGCCCATGendOneTAAnonCodingDnaTAGTGAZZZtaaTwoATGMyGeneTwoCATGGGGTAATGATAGCCatgCCCFalseStartTAATGATGendTwoTAGnonCodingDNATAACCCThreeATGMyGeneThreeATGGGGTAATGATAGATGccendThreeTAAnonCodingDNAccTAAfalsecccFourATGMyGeneFourATGGGGTAATGATAGCendFourTAGnonCodingdnaFiveAtgMyGeneFiveATGGGGTAATGATAGCendFiveTGAnonCodingdnaSixATGmyGeneSixATATGGGGTAATGATAGAendSixTAAnoncodingdnaSevenATGMyGeneSevenCcATGGGGTAATGATAGendSeventaAnoncodingdnaEightATGmyGeneEightATGGGGTAATGATAGGGendEighttaAnoncodingdnaCcccWrongtgaCtaaCtagCCcgNineATgmyGeneNineATGGGGTAATGATAGTaaAendNineTAAnonCodingDnaCcccTenATGmyGeneTenGATGGGGTAATGATAGCCHasFakeATGFAKEatgcendTentaanonCodingDnaCtagCtganonCodingDnaxxxElevenATGmyGeneElevenCATGGGGTAATGATAGTAAxxGeneATGTAACATGTAAATGCendElevenTAAnonCodingDnaxTAGxtgaTwelveATGmyGeneTwelveCATGGGGTAATGATAGCTheLastGeneIsATGTAGendTwelvetgaATGTAG";
        
        System.out.println("StorageResource genes");
        StorageResource genes = getAllGenes(dna);
        for( String g: genes.data())
        {
            System.out.println(g);
        }
        System.out.println("processGenes(genes)");
        processGenes(genes);
        System.out.println(" times codon CTG appear "+countCTG(dna));
        //System.out.println("cgRatio "+cgRatio("ATGmyGeneTwelveCATGGGGTAATGATAGCTheLastGeneIsATGTAG"));
        //printAllGenes("oneAtGMyGeneOneAATGGGGTAATGATAGAACCCGGYGGGGTAGGGCTGCCCATGendOneTAAnonCodingDnaTAGTGAZZZtaaTwoATGMyGeneTwoCATGGGGTAATGATAGCCatgCCCFalseStartTAATGATGendTwoTAGnonCodingDNATAACCCThreeATGMyGeneThreeATGGGGTAATGATAGATGccendThreeTAAnonCodingDNAccTAAfalsecccFourATGMyGeneFourATGGGGTAATGATAGCendFourTAGnonCodingdnaFiveAtgMyGeneFiveATGGGGTAATGATAGCendFiveTGAnonCodingdnaSixATGmyGeneSixATATGGGGTAATGATAGAendSixTAAnoncodingdnaSevenATGMyGeneSevenCcATGGGGTAATGATAGendSeventaAnoncodingdnaEightATGmyGeneEightATGGGGTAATGATAGGGendEighttaAnoncodingdnaCcccWrongtgaCtaaCtagCCcgNineATgmyGeneNineATGGGGTAATGATAGTaaAendNineTAAnonCodingDnaCcccTenATGmyGeneTenGATGGGGTAATGATAGCCHasFakeATGFAKEatgcendTentaanonCodingDnaCtagCtganonCodingDnaxxxElevenATGmyGeneElevenCATGGGGTAATGATAGTAAxxGeneATGTAACATGTAAATGCendElevenTAAnonCodingDnaxTAGxtgaTwelveATGmyGeneTwelveCATGGGGTAATGATAGCTheLastGeneIsATGTAGendTwelvetgaATGTAG");
 /*       System.out.println("Testing getAllGene on");
        StorageResource genes = getAllGenes(dna);
        for( String g: genes.data())
        {
            System.out.println(g);
        }
        */
        
        
    }
    
}
