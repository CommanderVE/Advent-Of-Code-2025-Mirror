
import java.util.ArrayList;


public class Day2{

    static String input = "82853534-82916516,2551046-2603239,805115-902166,3643-7668,4444323719-4444553231,704059-804093,32055-104187,7767164-7799624,25-61,636-1297,419403897-419438690,66-143,152-241,965984-1044801,1-19,376884-573880,9440956-9477161,607805-671086,255-572,3526071225-3526194326,39361322-39455443,63281363-63350881,187662-239652,240754-342269,9371-26138,1720-2729,922545-957329,3477773-3688087,104549-119841";
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
        String s_index;
        String[] parts;

        //Zählt hoch
        while (index <= end){
            s_index = String.valueOf(index);
            if((s_index.length() % 2) == 0){ // Nur Zahlen mit gerader Anzahl an Ziffern
                parts = new String[] {s_index.substring(0, s_index.length()/2), s_index.substring(s_index.length()/2)}; // Part 1 = erste Hälfte der Zahl; Part 2 = zweite Häflte der Zahl
                
                if(parts[0].equals(parts[1])){
                    invalidIDs.add(index);
                    System.out.println(parts[0] + "-" + parts[1] + " IS INVALID");
                }
            }
            index++;
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