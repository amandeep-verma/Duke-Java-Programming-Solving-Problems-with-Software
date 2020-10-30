
import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
/**
 * Write a description of WhatCountriesExport here.a 
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WhatCountriesExport {
    public static void listExporters(CSVParser parser, String exportOfInterest)
    {
        for(CSVRecord record: parser)
        {
            String export = record.get("Exports");
            if(export.contains(exportOfInterest))
            {
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    
    }
    public static String countryInfo(CSVParser parser, String country)
    {
        
        for(CSVRecord record: parser)
        {
            String countryinCSV = record.get("Country");
            if(countryinCSV.equals(country))
            {
                return countryinCSV+": "+record.get("Exports")+": "+record.get("Value (dollars)");
            }
        }
        return "NOT FOUND";
    }
    
    public static void listExportersTwoProducts( CSVParser parser,String exportItem1, String exportItem2)
    {
        for(CSVRecord record: parser)
        {
            String export = record.get("Exports");
            if(export.contains(exportItem1)  && export.contains(exportItem2))
            {
                String country = record.get("Country");
                System.out.println(country);
            }
        }
        
    }
    
    
    public static int numberOfExporters(CSVParser parser,String exportItem)
    {
        int count =0;
        for(CSVRecord record: parser)
        {
            String export = record.get("Exports");
            if(export.contains(exportItem))
            {
                count++;
            }
        }
        
        return count;
    
    }
    
    public static void  bigExporters(CSVParser parser,String amount)
    {
     for(CSVRecord record: parser)
        {
            String value = record.get("Value (dollars)");
            if(value.length() > amount.length())
            {
                String country = record.get("Country");
                System.out.println(country +" "+value);
            }
        }
    
    
    }
     public static void tester()
     {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "coffee");
        //Each time you want to use the parser with another method, you will 
        //need to reset the parser by calling fr.getCSVParser() 
        parser = fr.getCSVParser();
        System.out.println(countryInfo(parser, "Nauru"));
        
        System.out.println("-------------------------------");
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "cotton", "flowers");
        
        System.out.println("-------------------------------");
        parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser, "cocoa"));
        
        System.out.println("-------------------------------");
        parser = fr.getCSVParser();
        bigExporters(parser,"$999,999,999,999");
     }
     
}
