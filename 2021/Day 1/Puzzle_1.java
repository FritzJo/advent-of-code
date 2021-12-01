import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

class Puzzle_1 {
    public static void main(String[] args) {
        List<Integer> inputData = readFile("./Day 1/input.txt");
        int increaseCounter = 0;
        for(int i = 1; i<inputData.size(); i++) {
            if (inputData.get(i-1) < inputData.get(i)){
                increaseCounter++;
            }
        }
        System.out.println(increaseCounter);
    }

    public static List<Integer> readFile(String fileName){
        File file = new File(fileName);
        List<Integer> content = new LinkedList<>();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null)
                content.add(Integer.parseInt(st));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
