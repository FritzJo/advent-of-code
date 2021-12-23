import java.util.List;
import java.util.Stack;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

class Puzzle_2 {
        public static void main(String[] args) {
        List<String> lines = readFile("./Day 10/input.txt");
        LinkedList <Long> scores = new LinkedList<>();
        for (String l : lines){
            long v = evaluateLine(l);
            if (v != -1) {
                scores.add(evaluateLine(l));
            }
        }
        Collections.sort(scores);
        System.out.println(scores.get(scores.size()/2));
    }

    public static long evaluateLine(String line){
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
                        return -1;
                    }
            }
        }
        long score = 0;
        while (!stack.empty()){
            score *= 5;
            char c = stack.pop();
            switch (c){
                case '(':
                    score += 1;
                    break;
                case '<':
                    score += 4;
                    break;
                case '{':
                    score += 3;
                    break;
                case '[':
                    score += 2;
                    break;
            }
        }
        if (score<0){
            System.out.println(score);
            System.out.println();
        }
        return score;
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
