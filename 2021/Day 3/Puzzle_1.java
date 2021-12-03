import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;



class Puzzle_1 {
    public static void main(String[] args) {
        List<String> report = readFile("./Day 3/input.txt");
        int reportLength = report.get(0).length();

        int[] gammaRate = new int[reportLength];

        for(int i = 0; i < report.size(); i++) {
            for (int j = 0; j < report.get(i).length(); j++){
                if (report.get(i).charAt(j) == '0') {
                    gammaRate[j]--;
                } else {
                    gammaRate[j]++;
                }
            }
        }
        String resultGamma = "";
        String resultEpsilon = "";
        for(int i = 0; i < gammaRate.length; i++) {
            resultGamma += gammaRate[i] < 0 ? 0 : 1;
            resultEpsilon += gammaRate[i] < 0 ? 1 : 0;
        }
        System.out.println("Gamma: " + Integer.parseInt(resultGamma, 2));
        System.out.println("Epsilon: " + Integer.parseInt(resultEpsilon, 2));
        System.out.println("Power Consumption: " + Integer.parseInt(resultGamma, 2)*Integer.parseInt(resultEpsilon, 2));
        
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
