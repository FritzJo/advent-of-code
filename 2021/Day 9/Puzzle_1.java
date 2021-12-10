import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

class Puzzle_1 {
    public static boolean isLow(int x, int y, int[][] array) {
        int value = array[x][y];
        for(int i = -1; i<=1; i++) {
            for(int j = -1; j<=1; j++) {
                if (x+i >= array.length || x+i<0 || y+j<0 || y+j >= array[x+i].length || i==j || i*-1 == j) // wonky, but it works!
                    continue;
                if (array[x+i][y+j] <= value){
                    return false;
                }
            }   
        }
        return true;
    }
    public static void main(String[] args) {
        List<String> lines = readFile("./Day 9/input.txt");
        
        // Read Input to array
        int[][] map = new int[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                map[i][j] = Integer.parseInt("" + lines.get(i).charAt(j));
            }
        }

        // Look for low points
        int riskSum = 0;
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                if (isLow(i, j, map)){
                   riskSum += map[i][j] + 1;
                }
            }
        }

        System.out.println(riskSum);
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
