
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    
    public static void  printAllGenes(String dna)
    {
        int startIndex =0;
        while(true)
        {
            String currentGene = findGene(dna, startIndex);
            if ( currentGene == "")
            {
                System.out.println("I made it");
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene,startIndex) + currentGene.length();
        }
    }
    
    
    public static String findGene(String dna, int index)
    {
        int start = dna.indexOf("ATG", index);
        if (start < 0)
            return "";
        int endTAA = findStopCodon(dna, start, "TAA");
        int endTGA = findStopCodon(dna, start, "TGA");
        int endTAG = findStopCodon(dna, start, "TAG");
        
        int end = Math.min(endTGA,(Math.min(endTAA, endTAG)));
        
        if (endTAA ==-1)
        {
            end = Math.min(endTGA, endTAG);
            if ( end == -1)
                end = Math.max(endTGA, endTAG);
        }
        else if( endTGA ==-1)
        {
            end = Math.min(endTAA, endTAG);
            if ( end == -1)
                end = endTAA;
        }
        else if( endTAG ==-1)
            end = Math.min(endTAA, endTGA);
        
            
        
        if (end ==-1)
            return "";
        return dna.substring(start, end+3);
        
    }
    
    /*This method returns the index of the first occurrence of stopCodon that appears 
    past startIndex and is a multiple of 3 away from startIndex. If there is no such
    topCodon, this method returns the length of the dna strand.*/
    public static int findStopCodon(String dna, int startIndex,String stopCodon)
    {
        int currIndex = dna.indexOf(stopCodon, startIndex+3);
        while( currIndex != -1)
        {
            int diff = currIndex - startIndex;
            if (diff %3 ==0)
            {
                return currIndex;
            }
            else
                currIndex = dna.indexOf(stopCodon, currIndex+1);
        }
        return -1;
    }
    
    public static void testFindStopCodon()
    
    {
        printAllGenes("ATGTAAGATGCCCTAGT");
       // printAllGenes("acaagtttgtacaaaaaagcagaagggccgtcaaggcccaccatgcctattggatccaaagagaggccaacattttttgaaatttttaagacacgctgcaacaaagcagatttaggaccaataagtcttaattggtttgaagaactttcttcagaagctccaccctataattctgaacctgcagaagaatctgaacataaaaacaacaattacgaaccaaacctatttaaaactccacaaaggaaaccatcttataatcagctggcttcaactccaataatattcaaagagcaagggctgactctgccgctgtaccaatctcctgtaaaagaattagataaattcaaattagacttaggaaggaatgttcccaatagtagacataaaagtcttcgcacagtgaaaactaaaatggatcaagcagatgatgtttcctgtccacttctaaattcttgtcttagtgaaagtcctgttgttctacaatgtacacatgtaacaccacaaagagataagtcagtggtatgtgggagtttgtttcatacaccaaagtttgtgaagggtcgtcagacaccaaaacatatttctgaaagtctaggagctgaggtggatcctgatatgtcttggtcaagttctttagctacaccacccacccttagttctactgtgctcatagtcagaaatgaagaagcatctgaaactgtatttcctcatgatactactgctaatgtgaaaagctatttttccaatcatgatgaaagtctgaagaaaaatgatagatttatcgcttctgtgacagacagtgaaaacacaaatcaaagagaagctgcaagtcatggatttggaaaaacatcagggaattcatttaaagtaaatagctgcaaagaccacattggaaagtcaatgccaaatgtcctagaagatgaagtatatgaaacagttgtagatacctctgaagaagatagtttttcattatgtttttctaaatgtagaacaaaaaatctacaaaaagtaagaactagcaagactaggaaaaaaattttccatgaagcaaacgctgatgaatgtgaaaaatctaaaaaccaagtgaaagaaaaatactcatttgtatctgaagtggaaccaaatgatactgatccattagattcaaatgtagcaaatcagaagccctttgagagtggaagtgacaaaatctccaaggaagttgtaccgtctttggcctgtgaatggtctcaactaaccctttcaggtctaaatggagcccagatggagaaaatacccctattgcatatttcttcatgtgaccaaaatatttcagaaaaagacctattagacacagagaacaaaagaaagaaagattttcttacttcagagaattctttgccacgtatttctagcctaccaaaatcagagaagccattaaatgaggaaacagtggtaaataagagagatgaagagcagcatcttgaatctcatacagactgcattcttgcagtaaagcaggcaatatctggaacttctccagtggcttcttcatttcagggtatcaaaaagtctatattcagaataagagaatcacctaaagagactttcaatgcaagtttttcaggtcatatgactgatccaaactttaaaaaagaaactgaagcctctgaaagtggactggaaatacatactgtttgctcacagaaggaggactccttatgtccaaatttaattgataatggaagctggccagccaccaccacacagaattctgtagctttgaagaatgcaggtttaatatccactttgaaaaagaaaacaaataagtttatttatgctatacatgatgaaacatcttataaaggaaaaaaaataccgaaagaccaaaaatcagaactaattaactgttcagcccagtttgaagcaaatgcttttgaagcaccacttacatttgcaaatgctgattcaggtttattgcattcttctgtgaaaagaagctgttcacagaatgattctgaagaaccaactttgtccttaactagctcttttgggacaattctgaggaaatgttctagaaatgaaacatgttctaataatacagtaatctctcaggatcttgattataaagaagcaaaatgtaataaggaaaaactacagttatttattaccccagaagctgattctctgtcatgcctgcaggaaggacagtgtgaaaatgatccaaaaagcaaaaaagtttcagatataaaagaagaggtcttggctgcagcatgtcacccagtacaacattcaaaagtggaatacagtgatactgactttcaatcccagaaaagtcttttatatgatcatgaaaatgccagcactcttattttaactcctacttccaaggatgttctgtcaaacctagtcatgatttctagaggcaaagaatcatacaaaatgtcagacaagctcaaaggtaacaattatgaatctgatgttgaattaaccaaaaatattcccatggaaaagaatcaagatgtatgtgctttaaatgaaaattataaaaacgttgagctgttgccacctgaaaaatacatgagagtagcatcaccttcaagaaaggtacaattcaaccaaaacacaaatctaagagtaatccaaaaaaatcaagaagaaactacttcaatttcaaaaataactgtcaatccagactctgaagaacttttctcagacaatgagaataattttgtcttccaagtagctaatgaaaggaataatcttgctttaggaaatactaaggaacttcatgaaacagacttgacttgtgtaaacgaacccattttcaagaactctaccatggttttatatggagacacaggtgataaacaagcaacccaagtgtcaattaaaaaagatttggtttatgttcttgcagaggagaacaaaaatagtgtaaagcagcatataaaaatgactctaggtcaagatttaaaatcggacatctccttgaatatagataaaataccagaaaaaaataatgattacatgaacaaatgggcaggactcttaggtccaatttcaaatcacagttttggaggtagcttcagaacagcttcaaataaggaaatcaagctctctgaacataacattaagaagagcaaaatgttcttcaaagatattgaagaacaatatcctactagtttagcttgtgttgaaattgtaaataccttggcattagataatcaaaagaaactgagcaagcctcagtcaattaatactgtatctgcacatttacagagtagtgtagttgtttctgattgtaaaaatagtcatataacccctcagatgttattttccaagcaggattttaattcaaaccataatttaacacctagccaaaaggcagaaattacagaactttctactatattagaagaatcaggaagtcagtttgaatttactcagtttagaaaaccaagctacatattgcagaagagtacatttgaagtgcctgaaaaccagatgactatcttaaagaccacttctgaggaatgcagagatgctgatcttcatgtcataatgaatgccccatcgattggtcaggtagacagcagcaagcaatttgaaggtacagttgaaattaaacggaagtttgctggcctgttgaaaaatgactgtaacaaaagtgcttctggttatttaacagatgaaaatgaagtggggtttaggggcttttattctgctcatggcacaaaactgaatgtttctactgaagctctgcaaaaagctgtgaaactgtttagtgatattgagaatattagtgaggaaacttctgcagaggtacatccaataagtttatcttcaagtaaatgtcatgattctgttgtttcaatgtttaagatagaaaatcataatgataaaactgtaagtgaaaaaaataataaatgccaactgatattacaaaataatattgaaatgac".toUpperCase());
        
    }
}
