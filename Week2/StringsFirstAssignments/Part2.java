
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public static String findSimpleGene(String dna, String startCodon, String stopCodon)
    {
        int start = dna.indexOf(startCodon);
        if (start < 0)
            return "";
        int end = dna.indexOf(stopCodon,start+3);
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
            "gatgctataat", 
            "ATGCCTAG", 
            "",
            "ATCCCTAAG", 
            "ATGCCCCTAA" };
        String gene ="";
        for (int i = 0; i < 6; i++) {
            System.out.println("DNA strand is " +dna[i]);
            boolean isLowCase = false;
            if( dna[i].length()>0 && dna[i].charAt(0)  > 90)
            {
               isLowCase = true;
            }
            if(isLowCase)
            {
                gene = findSimpleGene(dna[i].toUpperCase(),"ATG","TAA");
                System.out.println("Gene is " + gene.toLowerCase());
            }
            else
            {
                gene = findSimpleGene(dna[i],"ATG","TAA");
                System.out.println("Gene is " + gene);
            }

        }
    }

}
