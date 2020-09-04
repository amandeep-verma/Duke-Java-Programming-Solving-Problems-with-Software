
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    
    /*
    Finds the index position of the start codon “ATG”. 
    If there is no “ATG”, return the empty string.
    Finds the index position of the first stop codon “TAA” appearing after 
    the “ATG” that was found. If there is no such “TAA”, return the empty 
    string.
    If the length of the substring between the “ATG” and “TAA” is a multiple 
    of 3, then return the substring that starts with that “ATG” and ends with
    that “TAA”.
    */
    public static String findSimpleGene(String dna)
    {
       
        int start = dna.indexOf("ATG");
        if (start < 0)
            return "";
        int end = dna.indexOf("TAA",start+3);
        if (end < 0)
            return "";
            
        if((end-start) %3 ==0)
        {
            return dna.substring(start, end+3);
        }
        return "";
    }
    
    public static void testSimpleGene()
    {
        String[] dna = { "CCCATGGGGTCTTAAGAGAGTTT", 
            "GGGTTTAAATAATAATAG", 
            "ATGCCTAG", 
            "",
            "ATCCCTAAG", 
            "ATGCCCCTAA" };
	String gene ="";
	for (int i = 0; i < 6; i++) {
	    System.out.println("DNA strand is " +dna[i]);
	    gene = findSimpleGene(dna[i]);
	    System.out.println("Gene is " + gene);
	}
    }
    public static void main(String[] args) {
        
        
    }
}
