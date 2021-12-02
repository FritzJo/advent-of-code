import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

enum Direction {
    UP, DOWN, FORWARD
}

class Instruction {
    public Direction d;
    public int value;

    public Instruction(String input){
        switch (input.split(" ")[0].toLowerCase()){
            case "forward":
                d = Direction.FORWARD;
                break;
            case "down":
                d = Direction.DOWN;
                break;
            case "up":
                d = Direction.UP;
                break;
        }
        value = Integer.parseInt(input.split(" ")[1]);
    }
}

class Puzzle_1 {
    public static void main(String[] args) {
        List<Instruction> instructions = readFile("./Day 2/input.txt").stream()
                                            .map(a -> new Instruction(a))
                                            .toList();

        int depth = 0;
        int hPosition = 0;

        for(int i = 0; i < instructions.size(); i++) {
            Instruction ins = instructions.get(i);
            switch (ins.d) {
                case DOWN:
                    depth += ins.value;
                    break;
                case UP:
                    depth -= ins.value;
                    break;
                case FORWARD:
                    hPosition += ins.value;
                    break;
            }
        }
        System.out.println("Depth: " + depth);
        System.out.println("Horizontal Position: " + hPosition);
        System.out.println("Result: " + (depth*hPosition));
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
