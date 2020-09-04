
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    
    public static String lastPart(String a, String b)
    {
        int firstoccurance = b.indexOf(a);
        if (firstoccurance<0)
            return b;
        if (firstoccurance+a.length()>=b.length())
            return "";
        return b.substring(firstoccurance+a.length());
        
    }
    public static boolean twoOccurrences(String a, String b)
    {
        
        int firstoccurance = b.indexOf(a);
        if(firstoccurance<0)
            return false;
        int secondoccurance = b.indexOf(a,firstoccurance+1);
        if(secondoccurance<0)
            return false;
        return true;
    }
    
    public static void testing()
    {
        String[] a={"by", "A story by Abby Long",
            "a", "banana",
            "atg", "ctgtatgta"};
        
        for( int i =0; i<a.length/2 ;i ++)
        {
            System.out.println("st1 "+a[i*2] +"\nst2 "+a[i*2+1]+"\nst3 "+twoOccurrences(a[i*2],a[i*2+1]));
        }
        
        String[] b = {"an", "banana", "zoo", "forest"};
        for( int i =0; i<b.length/2 ;i ++)
        {
            System.out.println("st1 "+b[i*2] +"\nst2 "+b[i*2+1]+"\nst3 "+lastPart(b[i*2],b[i*2+1]));
        }

    }
}
