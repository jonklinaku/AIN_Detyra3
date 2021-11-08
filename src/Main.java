import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Main {

    private static final int binSize = 50;
    private static final int numberOfElements = 10;
    private static final List<List<List<Integer>>> solutionPool = new ArrayList<>();
    private static HashMap<Integer,Integer> elements = new HashMap<>();
    public static void main(String[] args) {
        generateElementWeights();
        System.out.println(elements+": element weights");
        for (int i = 0; i < 1000; i++) {
            List<List<Integer>> potentialSolution = generateRandomSolution();

            if (uniqueSolution(potentialSolution)) {
                solutionPool.add(potentialSolution);
                System.out.println(solutionPool.get(i) + ": Accepted Solution");
            } else
                i--;
        }
        System.out.println(solutionPool.size());

    }
    private static boolean uniqueSolution(List<List<Integer>> potentialSolution){
        for (int i = 0; i < solutionPool.size(); i++) {
            if(solutionPool.get(i).hashCode() == potentialSolution.hashCode()){
                System.out.println("Solution: " +solutionPool.get(i)+" ignored due to collision");
                return false;
            }

        }
        return true;
    }
    private static List<List<Integer>> generateRandomSolution(){
        List<Integer> random = new ArrayList<>();
        List<List<Integer>> bins = new ArrayList<>();
        for (int i = 0; i < numberOfElements; i++) {
            random.add(i);
        }
         Collections.shuffle(random);
        random.forEach(element ->{
            boolean elementInserted = false;
            for (int i = 0; i < bins.size(); i++) {
                if (canPutInBin(elements.get(element),bins.get(i))){
                    bins.get(i).add(element);
                    elementInserted = true;
                    break;
                }

            }
            if (!elementInserted){
                List<Integer> newBin = new ArrayList<>();
                newBin.add(element);
                bins.add(newBin);
            }
        } );
        return bins;
    }
    private static boolean canPutInBin(int valueOfElement,List<Integer> bin){
        int binSum = 0;
        for (int i = 0; i < bin.size(); i++) {
            binSum+=elements.get(bin.get(i));
        }
        if (binSum+valueOfElement>binSize)
            return false;
        else
            return true;
    }

    private static void generateElementWeights(){
        for (int i = 0; i < numberOfElements; i++) {

            elements.put(i,((int)(Math.random() * 10)+1) * 5);
        }
    }
}
