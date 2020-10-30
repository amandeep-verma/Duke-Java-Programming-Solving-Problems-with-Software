
import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
/**
 * Write a description of WeatherUS here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WeatherUS {
    public static CSVRecord hottestHourInFile(CSVParser parser)
    {
        
        CSVRecord hottest = null;
        for (CSVRecord record: parser)
        {
            hottest= getLargestofTwo(hottest,record);
        }
        return hottest;
    }
    
    public static CSVRecord getLargestofTwo(CSVRecord reclargest,CSVRecord rec)
    {
        if( reclargest==null)
            {
                reclargest=rec;
            }
            else
            {
                double currentTemp = Double.parseDouble(rec.get("TemperatureF"));
                double hottestTemp = Double.parseDouble(reclargest.get("TemperatureF"));
                if (currentTemp >hottestTemp)
                {
                    reclargest= rec;
                }
           }
           return reclargest;
    }
    public static void testerfile()
    {
        
        FileResource fr = new FileResource("/Users/amandeepverma/Downloads/nc_weather/2015/weather-2015-02-06.csv");
        CSVParser parser = fr.getCSVParser();
        CSVRecord rec = hottestHourInFile(parser);
        System.out.println("-------------------------------");
        System.out.println("The highest temperature is "+ rec.get("TemperatureF") +" at " + rec.get("TimeEST"));
        
    }
    
    public static void testerDirectory()
    {
        DirectoryResource dr = new DirectoryResource();
        
        CSVRecord reclargest=null;
        for( File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord rec = hottestHourInFile(parser);
            
            reclargest= getLargestofTwo(reclargest,rec);
            
        }
        
        System.out.println("-------------------------------");
        System.out.println("The highest temperature is "+reclargest.get("TemperatureF") +" at " + reclargest.get("DateUTC"));
        
    }
    
}
