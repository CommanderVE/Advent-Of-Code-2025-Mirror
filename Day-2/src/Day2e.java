
import java.util.ArrayList;

//correct answer:   19058204438
//my answer:        19058204483

public class Day2e{

    static String input = "82853534-82916516,2551046-2603239,805115-902166,3643-7668,4444323719-4444553231,704059-804093,32055-104187,7767164-7799624,25-61,636-1297,419403897-419438690,66-143,152-241,965984-1044801,1-19,376884-573880,9440956-9477161,607805-671086,255-572,3526071225-3526194326,39361322-39455443,63281363-63350881,187662-239652,240754-342269,9371-26138,1720-2729,922545-957329,3477773-3688087,104549-119841";
    //static String input = "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124";
    static ArrayList<Long> invalidIDs = new ArrayList<>();
    
    public static void main(String[] args) {
        String[] ranges = input.split(",");

        for(String range : ranges){
            System.out.println("Range: " + range);
            String[] parts = range.split("-");
            long start = Long.parseLong(parts[0]);
            long end = Long.parseLong(parts[1]);
            validation(start, end);
        }
        System.out.println("Die Summe aller Invaliden IDs ist: " + sumInvalid());
    }

    static void validation(long start, long end){
        long index = start;
        ArrayList<Integer> pointOfInterests = new ArrayList<>();
        String s_index;
        //String[] parts;

        while (index <= end){
            s_index = String.valueOf(index);
            
            pointOfInterests = searchPOIs(s_index);
            evaluateSections(s_index, pointOfInterests);

            index++;
        }
    }

    // Sucht ausgehend von der ersten Ziffer sich wiederholdene Ziffern und markiert diese 
    static ArrayList<Integer> searchPOIs(String s_index){

        ArrayList<Integer> pointOfInterests = new ArrayList<>();
        char reference;

        reference = s_index.charAt(0);
        
        for(int i = 0; i < s_index.length(); i++){
            if(s_index.charAt(i) == reference){
                pointOfInterests.add(i); // Bekommt Stelle von POI
            }
        }
        return pointOfInterests;
    }

    // Geht von den POIs aus nach rechts und gleicht Zahlen ab
    static void evaluateSections(String s_index, ArrayList<Integer> poi){
        if(poi.size() == s_index.length()){ // Alle Ziffern sind POI also alle gleich
            System.out.println(s_index + " IS INVALID");
            invalidIDs.add(Long.parseLong(s_index));
            return;
        }

        for(int i = 1; i < poi.size(); i++){
            int patternLength = poi.get(i); // Abstand vom Anfang = mögliche Musterlänge

            if (s_index.length() % patternLength != 0) {
                continue;
            }

            String pattern = s_index.substring(0, patternLength);
            boolean validPattern = true;
            int numSegments = (s_index.length() / patternLength);

            for (int segment = 1; segment < numSegments; segment++) {
                int start = segment * patternLength;
                String currentSegment = s_index.substring(start, start + patternLength);
                if (!currentSegment.equals(pattern)) {
                    validPattern = false;
                    break;
                }
            }
            if (validPattern) {
                System.out.println(s_index + " IS INVALID");
                invalidIDs.add(Long.parseLong(s_index));
                return;
            }
        }        
    }

    static long sumInvalid(){
        long result = 0;
        for(long id : invalidIDs){
            result += id;
        }
        return result;
    }
  
}