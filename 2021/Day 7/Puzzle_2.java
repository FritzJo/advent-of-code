import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;


class Puzzle_2 {
    public static void main(String[] args) {
        List<String> lines = readFile("./Day 7/input.txt");
        List<Integer> crabs = Arrays.stream(lines.get(0).split(",")).map(l -> Integer.parseInt(l)).toList();
        int average = (int) (crabs.stream().mapToDouble(d -> d).average().orElse(0.0));
        int totalFuel = crabs.stream()
            .map(c -> Math.abs(c-average)*(1+Math.abs(c-average))/2)
            .mapToInt(Integer::intValue).sum();
        System.out.println(totalFuel);
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

