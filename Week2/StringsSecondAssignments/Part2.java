
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public static int howMany(String a, String b)
    {
        int count =0;
        int startIndex = 0;
        while(true)
        {
            startIndex = b.indexOf(a,startIndex);
            if (startIndex == -1)
                break;
            count++;
            startIndex = startIndex+a.length();
        }
        
        return count;
    }
    
    
    public static void testHowMany()
    {
        
        System.out.println(howMany("GAA", "ATGAACGAATTGAATC"));
        
    }
            
}
