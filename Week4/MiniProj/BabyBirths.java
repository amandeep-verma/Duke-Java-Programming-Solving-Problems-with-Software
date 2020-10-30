import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
/**
 * Write a description of BabyBirths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BabyBirths {
    public static void printNames()
    {
        FileResource fr = new FileResource();
        for (CSVRecord rec:fr.getCSVParser(false))
        {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <=100)
            {
                System.out.println("Name "+rec.get(0)+
                                    " Gender "+rec.get(1)+
                                    " Num Born "+rec.get(2));
                                }

        }
    
    }
    
    
    public static void totalBirths(FileResource fr)
    {
        
        int totalBirth =0;
        int totalmales = 0;
        int totalfemales = 0;
        int girlNames =0;
        int boyNames =0;
        for (CSVRecord rec:fr.getCSVParser(false))
        {
             int numBorn = Integer.parseInt(rec.get(2));
             totalBirth += numBorn;
             if(rec.get(1).equals("M"))
             {
                totalmales+=numBorn;
                boyNames++;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
            }
             else
             {
                totalfemales+=numBorn;
                girlNames++;
            }
        }
        System.out.println(" total births = " +  totalBirth);
        System.out.println(" males = " +  totalmales+" females = " +  totalfemales);
        System.out.println(" boyNames = " +  boyNames+" girlNames = " +  girlNames);
        
    }
                                                                                                                                                                    
    public static int getRank(int year,String name,String gender)
    {
        int rank=0;
        //FileResource fr = new FileResource("/Users/amandeepverma/Downloads/MiniProj/us_babynames/us_babynames_test/yob2012short.csv");
        FileResource fr = new FileResource("/Users/amandeepverma/Downloads/MiniProj/us_babynames/us_babynames_by_year/yob"+year+".csv");
        for (CSVRecord rec:fr.getCSVParser(false))
        {
            if(rec.get(1).equals(gender))
            {
                rank++;
                if(rec.get(0).equals(name))
                {   
                    return rank;
                }
            }
        }
        return -1;
    }
    
    public static String getName(int year, int rank,String gender)
    {
        int counter=0;
        //FileResource fr = new FileResource("/Users/amandeepverma/Downloads/MiniProj/us_babynames/us_babynames_test/yob2014short.csv");
        FileResource fr = new FileResource("/Users/amandeepverma/Downloads/MiniProj/us_babynames/us_babynames_by_year/yob"+year+".csv");
        for (CSVRecord rec:fr.getCSVParser(false))
        {
            if(rec.get(1).equals(gender))
            {
                counter++;
                if(counter==rank)
                {   
                    return rec.get(0);
                }
            }
        }
        return "NO NAME";
    }
     
    public static void whatIsNameInYear(String name, int year, int newYear, String gender)
    {
        int RankInYear = getRank(year, name, gender);
        String nameInNewYear = getName(newYear, RankInYear, gender);
        System.out.println(name+" born in "+year+" would be "+nameInNewYear+" if was born in "+newYear);
        
    
    }
    
    public static int getRankFromParser(CSVParser parser,String name,String gender)
    {
        int rank=0;
        for (CSVRecord rec:parser)
        {
            if(rec.get(1).equals(gender))
            {
                rank++;
                if(rec.get(0).equals(name))
                {   
                    return rank;
                }
            }
        }
        return -1;
    }
    
    public static int yearOfHighestRank(String name,String gender)
    {
        String bestYear = "-1";
        int bestRank =-1;
        DirectoryResource dr = new DirectoryResource();
        for( File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser(false);
            int rank = getRankFromParser(parser, name, gender);
            if(rank !=-1)
            {
                if (bestRank==-1)
                {
                    bestRank = rank;
                    bestYear = f.getName();
                }
                else if( rank < bestRank)
                {
                    bestRank = rank;
                    bestYear = f.getName();
                }
                
            }
        }
        System.out.println("Best ranking year is "+bestYear);
        return bestRank;
    }
    
    
    public static double getAverageRank(String name, String gender)
    {
        int sumRank=0;
        int count=0;
        DirectoryResource dr = new DirectoryResource();
        for( File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser(false);
            int rank = getRankFromParser(parser, name, gender);
            if(rank !=-1)
            {
                count++;
                sumRank += rank;
            }
            
        }
        
        return ((double)sumRank*1.0)/count;
    }
    
    public static int getTotalBirthsRankedHigher(int year,String name,String gender)
    {
        int counter=0;
        //FileResource fr = new FileResource("/Users/amandeepverma/Downloads/MiniProj/us_babynames/us_babynames_test/yob2012short.csv");
        FileResource fr = new FileResource("/Users/amandeepverma/Downloads/MiniProj/us_babynames/us_babynames_by_year/yob"+year+".csv");
        for (CSVRecord rec:fr.getCSVParser(false))
        {
            if(rec.get(1).equals(gender))
            {
                if(rec.get(0).equals(name))
                {
                    return counter;
                }
                counter =counter + Integer.parseInt(rec.get(2));
            }
        }
        return counter;
        
    }
    public static void testTotalBirths()
    {
        FileResource fr = new FileResource();
        totalBirths(fr);
    
    }
    
    
}
