import java.util.List;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

class Board {
    String[][] board = new String[5][5];

    public Board(String inputString){
        String[] input = inputString.split("\n");
        for (int i = 0; i < input.length; i++){
            this.board[i] = input[i].strip().replace("  ", " ").split(" ");
        }
       //System.out.println(this.toString());
    }

    public void crossValue(String value){
        for (int i = 0; i < this.board.length; i++){
            for (int j = 0; j < this.board[i].length; j++){
                if (this.board[i][j].equals(value)){
                    this.board[i][j] = "";
                }
            }
        }
    }

    public boolean isCompleted(){
        for (int i = 0; i < this.board.length; i++){
            if(Arrays.asList(this.board[i]).stream().filter(f -> f.equals("")).toList().size() == 5)
                return true;
        }


        for (int i = 0; i < this.board[0].length; i++){
            final int currentIndex = i;
            if(Arrays.asList(this.board).stream().map(r -> r[currentIndex]).filter(f -> f.equals("")).toList().size() == 5)
                return true;
            //for (int j = 0; j < this.board[0].length; j++){
                //System.out.print(this.board[j][i]+ " ");
            //}
        }
        return false;
    }

    public int getResult(int lastNumber){
        int total = 0;
        for (int i = 0; i < this.board[0].length; i++){
            for (int j = 0; j < this.board[0].length; j++){
                if (!this.board[i][j].equals("")){
                    total += Integer.parseInt(this.board[i][j]);
                }
            }
        }
        return total * lastNumber;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.board.length; i++){
            for (int j = 0; j < this.board[i].length; j++){
                sb.append(this.board[i][j]+ " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}

class Puzzle_1 {
    public static void main(String[] args) {
        List<String> report = readFile("./Day 4/input.txt");

        // Load numbers
        String[] numbers = report.get(0).split(",");

        // Load board data 
        List<String> boardData = report.stream().skip(1).toList();
        List<Board> boards = new LinkedList<>();
        for (int i = 0; i < boardData.size(); i = i + 6){
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j <= 6 && i+j< boardData.size(); j++){
                String line = boardData.get(i+j);
                if (!line.equals("")){
                    sb.append(line + "\n");
                }
            }
            boards.add(new Board(sb.toString()));   
        }

        // Cross out numbers
        for (String n : numbers){
            for (Board b : boards){
                b.crossValue(n);
                if (b.isCompleted()){
                    int num = Integer.parseInt(n);
                    System.out.println(b.getResult(num));
                    return;
                }
            }
        }
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
