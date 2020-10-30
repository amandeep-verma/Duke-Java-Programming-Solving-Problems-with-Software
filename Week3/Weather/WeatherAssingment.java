import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
/**
 * Write a description of WeatherAssingment here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class WeatherAssingment {
    public static CSVRecord coldestHourInFile(CSVParser parser)
    {
        CSVRecord coldest = null;
        for(CSVRecord current: parser)
        {
            coldest= getLowestofTwo(coldest,current);
        }
        
        return coldest;
    }
    
    
    public static CSVRecord getLowestofTwo(CSVRecord coldest,CSVRecord rec)
    {
        if( coldest==null)
            {
                coldest=rec;
            }
            else
            {
                double currentTemp = Double.parseDouble(rec.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldest.get("TemperatureF"));
                
                // to ingore the bogus values by not letting it updated;
                if( currentTemp == -9999)
                {   
                    return coldest;
                }
                if (currentTemp <coldestTemp)
                {
                    coldest= rec;
                }
           }
           return coldest;
    }
    
    public static CSVRecord getLowestofTwoHumidity(CSVRecord lowest,CSVRecord rec)
    {
        
        if(rec.get("Humidity").equals("N/A"))
        {
            
        }
        else if( lowest==null)
            {
                lowest=rec;
            }
        else
            {
                double currentH = Double.parseDouble(rec.get("Humidity"));
                double lowestH = Double.parseDouble(lowest.get("Humidity"));
                
                if (currentH <lowestH)
                {
                    lowest= rec;
                }
           }
        return lowest;
    }
    
    public static double averageTemperatureInFile(CSVParser  parser)
    {
        double TotalTemp=0;
        int count =0;
        for(CSVRecord current: parser)
        {
            double currentTemp = Double.parseDouble(current.get("TemperatureF"));
            if( currentTemp == -9999)
            {   
                continue;
            }
            else
            {
                TotalTemp+=currentTemp;
                count++;
            }
        }
        return TotalTemp/count;
    }
    
    public static void testAverageTemperatureInFile()
    {
        FileResource  file = new FileResource ();
        CSVParser parser = file.getCSVParser();
        Double averageT = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is "+averageT);
    }
    
    public static double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value)
    {
        double TotalTemp=0;
        int count =0;
        for(CSVRecord current: parser)
        {
            if(current.get("Humidity")=="N/A")
            {
                continue;
            }
            double currentH = Double.parseDouble(current.get("Humidity"));
            if (currentH>= value)
            {
                double currentTemp = Double.parseDouble(current.get("TemperatureF"));
                if( currentTemp == -9999)
                {   
                    continue;
                }
                else
                {
                    TotalTemp+=currentTemp;
                    count++;
                }
            }
        }
        
        return TotalTemp/count;
    }
    
    public static void testAverageTemperatureWithHighHumidityInFile()
    {
        FileResource  file = new FileResource ();
        CSVParser parser = file.getCSVParser();
        double avgT = averageTemperatureWithHighHumidityInFile(parser, 80);
        if(Double.isNaN(avgT))
            System.out.println("No temperatures with that humidity");
        else
            System.out.println("Average Temp when high Humidity is "+avgT);
        
    }
    
    public static CSVRecord lowestHumidityInFile(CSVParser  parser)
    {
        CSVRecord lowest = null;
        for(CSVRecord current: parser)
        {
            lowest=  getLowestofTwoHumidity(lowest,current);
        }
        
        return lowest;
    }
    
    public static void testLowestHumidityInFile()
    {
        FileResource  file = new FileResource ();
        CSVParser parser = file.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was "+csv.get("Humidity")+" at "+csv.get("DateUTC"));
    }
    public static void testfile()
    {
        FileResource  file = new FileResource ();
        CSVParser parser = file.getCSVParser();
        CSVRecord coldest = coldestHourInFile(parser);
        System.out.println("-------------------------------");
        System.out.println("The coldest temperature is "+ coldest.get("TemperatureF") +" at " + coldest.get("TimeEST"));
    
    }
    
    public static CSVRecord lowestHumidityInManyFiles()
    {
        DirectoryResource dr = new DirectoryResource();
        
        CSVRecord lowestH=null;
        for( File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord rec = lowestHumidityInFile(parser);
            
            lowestH= getLowestofTwoHumidity(lowestH,rec);
            
        }
        return lowestH;
    }
    
    public static void testLowestHumidityInManyFiles() 
    {
        CSVRecord record = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was "+record.get("Humidity") +" at " + record.get("DateUTC"));
    
    }
    
    public static String fileWithColdestTemperature()
    {
        DirectoryResource dr = new DirectoryResource();
        File coldestTFile = null;
        CSVRecord coldest=null;
        for( File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord rec = coldestHourInFile(parser);
            
            if( coldestTFile==null)
            {
                coldestTFile=f;
                coldest=rec;
            }
            else
            {
                double currentTemp = Double.parseDouble(rec.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldest.get("TemperatureF"));
                
                // to ingore the bogus values by not letting it updated;
                if( currentTemp == -9999)
                {   
                    continue;
                }
                if (currentTemp <coldestTemp)
                {
                    coldest=rec;
                    coldestTFile=f;
                }
            }
        }
        return coldestTFile.getAbsolutePath();
        
    }
    
    public static void testFileWithColdestTemperature()
    {
        File coldestTempFile = new File(fileWithColdestTemperature());
        System.out.println("Coldest day was in file weather-"+coldestTempFile.getName());
        FileResource fr = new FileResource(coldestTempFile);
        CSVParser parser = fr.getCSVParser();
        CSVRecord rec = coldestHourInFile(parser);
        System.out.println("Coldest temperature on that day was "+rec.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        parser = fr.getCSVParser();
        for( CSVRecord record : parser)
        {
            System.out.println(record.get("DateUTC")+" "+record.get("TemperatureF"));
        }
        
        
    }
}
