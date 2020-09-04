import edu.duke.*;
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {

    
    public static void main(String[] args)
    {
        URLResource file = new  URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
   	for (String item : file.words()) {
   	  
       	   String itemLower = item.toLowerCase();
       	   int pos = itemLower.indexOf("youtube.com");
       	   if (pos != -1) {
           	int lastIndex = itemLower.lastIndexOf("\"", pos);
           	int nextIndex = itemLower.indexOf("\"", pos);
           	System.out.println(item.substring(lastIndex+1, nextIndex));
               }
   	}
    
    }
}




