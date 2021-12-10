import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

class Puzzle_1 {
    public static void main(String[] args) {
        List<String> lines = readFile("./Day 8/input.txt");
        int counter = 0;
        for (String l : lines) {
            List<String> output = Arrays.asList(l.split(" \\| ")[1].split(" "));
            for (String o : output) {
                if (o.length() == 2 || o.length() == 3 || o.length() == 4 || o.length() == 7) {
                    counter++;
                }
            }
        }
        System.out.println(counter);
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
