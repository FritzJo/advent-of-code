import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;


class Puzzle_2 {
    public static void main(String[] args) {
        List<String> lines = readFile("./Day 6/input.txt");
        long[] population = new long[9];
        
        // Initial population
        String[] input = lines.get(0).split(",");
        for (int i = 0; i < input.length; i++) {
            switch (input[i]) {
                case "0":
                    population[0]++;
                    break;
                case "1":
                    population[1]++;
                    break;
                case "2":
                    population[2]++;
                    break;
                case "3":
                    population[3]++;
                    break;
                case "4":
                    population[4]++;
                    break;
                case "5":
                    population[5]++;
                    break;
            }
        }

        // Pass days
        for (int day = 0; day < 256; day++){
            long[] newPopulation = new long[9];
            for (int i = 0; i < population.length-1; i++){
                newPopulation[i] = population[i+1]; 
            }
            newPopulation[6]+=population[0];
            newPopulation[8]+=population[0];
            population=newPopulation;
        }

        System.out.println(Arrays.stream(population).sum());
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
