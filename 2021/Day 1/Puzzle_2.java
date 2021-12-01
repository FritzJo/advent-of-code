import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

class Puzzle_2 {
    public static void main(String[] args) {
        List<Integer> inputData = readFile("./Day 1/input.txt");
        int increaseCounter = 0;
        int lastSum = inputData.get(0) + inputData.get(1) +inputData.get(2);
        for(int i = 2; i<inputData.size()-1; i++) {
            int newSum = inputData.get(i-1) + inputData.get(i) +inputData.get(i+1);
            if (lastSum < newSum){
                increaseCounter++;
            }
            lastSum = newSum;
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
