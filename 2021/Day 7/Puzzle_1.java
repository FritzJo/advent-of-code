import java.util.List;
import java.util.stream.Collector;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;


class Puzzle_1 {
    public static void main(String[] args) {
        List<String> lines = readFile("./Day 7/input_test_1.txt");
        List<Integer> crabs = Arrays.stream(lines.get(0).split(",")).map(l -> Integer.parseInt(l)).toList();
        List<Integer> modifiableCrabs = new ArrayList<Integer>(crabs); // I hate Java for things like that...
        Collections.sort(modifiableCrabs);
        int median = modifiableCrabs.get((int)(modifiableCrabs.size()/2));
        System.out.println("Median: " + median);
        int totalFuel = crabs.stream().map(c -> Math.abs(c-median)).mapToInt(Integer::intValue).sum();
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
