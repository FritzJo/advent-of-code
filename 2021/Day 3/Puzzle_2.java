import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;



class Puzzle_2 {
    public static void main(String[] args) {
        String fileName = "./Day 3/input.txt";
        // Oxygen scan
        List<String> reports = readFile(fileName);

        int index = 0;
        while (reports.size() > 1) {
            char cv = mostCommonValue(reports, index, 1);
            final int currentIndex = index;
            reports = reports.stream().filter(r -> r.charAt(currentIndex) == cv).toList();
            index++;
        }
        int oxygen = Integer.parseInt(reports.get(0), 2);
        System.out.println("Oxygen: " + oxygen);

        // CO2
        reports = readFile(fileName);
        index = 0;
        while (reports.size() > 1) {
            char cv = mostCommonValue(reports, index, 0);
            final int currentIndex = index;
            reports = reports.stream().filter(r -> r.charAt(currentIndex) == cv).toList();
            index++;
        }
        int co2 = Integer.parseInt(reports.get(0), 2);
        System.out.println("CO2: " + co2);
        System.out.println("Life support rating: " + (co2*oxygen));
    }

    public static char mostCommonValue(List<String> inputs, int index, int base) {
        int cv = 0;
        for(int i = 0; i < inputs.size(); i++) {
            if (inputs.get(i).charAt(index) == '0') {
                cv--;
            } else {
                cv++;
            }
        }
        if (base == 1) 
            return cv < 0 ? '0' : '1';
        else
            return cv < 0 ? '1' : '0';
    }

    public static List<String> readFile(String fileName){
        File file = new File(fileName);
        List<String> content = new LinkedList<>();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null)
                content.add(st);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
