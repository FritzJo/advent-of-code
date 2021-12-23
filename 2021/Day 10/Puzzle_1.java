import java.util.List;
import java.util.Stack;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

class Puzzle_1 {
        public static void main(String[] args) {
        List<String> lines = readFile("./Day 10/input.txt");
        int score = 0;
        for (String l : lines){
            score += evaluateLine(l);
        }
        System.out.println(score);
    }

    public static int evaluateLine(String line){
        Stack<Character> stack = new Stack<Character>();
        for(char c : line.toCharArray()){
            switch (c){
                case '(':
                case '<':
                case '{':
                case '[':
                    stack.add(c);
                    break;
                default:
                    if (stack.pop() != getReverse(c)) {
                        return calculateScore(c);
                    }
            }
        }
        return 0;
    }

    public static int calculateScore(char c) {
        switch (c){
            case '(':
            case ')':
                return 3;
            case '<':
            case '>':
                return 25137;
            case '{':
            case '}':
                return 1197;
            case '[':
            case ']':
                return 57;
            default:
                return 0;
        }
    }

    public static char getReverse(char c){
        switch (c){
            case '(':
                return ')';
            case '<':
                return '>';
            case '{':
                return '}';
            case '[':
                return ']';
            case ')':
                return '(';
            case '>':
                return '<';
            case '}':
                return '{';
            case ']':
                return '[';
            default:
                return ' ';
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
