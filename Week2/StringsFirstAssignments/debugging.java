
/**
 * Write a description of debugging here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class debugging {
    public  static void findAbc(String input) 
    {
        int index = input.indexOf("abc");
        while (true) {
            if (index == -1 || index >= input.length() - 3){
                break;
            }
            System.out.println("index " + index);
            System.out.println(index);
            String found = input.substring(index+1, index+4);
            System.out.println(found);
            index = input.indexOf("abc",index+3);
            System.out.println("index after updating " + index);
        }
    }
    public static void test() {
        System.out.println("----------------");
        findAbc("aabcabcabcabca");
    }

}
