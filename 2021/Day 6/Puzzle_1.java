import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

class Lanternfish {
    private int timer;

    public Lanternfish(){
        this(8);
    }

    public Lanternfish(int timer){
        this.timer = timer;    
    }

    public boolean passDay() {
        if (this.timer == 0) {
            this.timer = 6;
            return true;
        } else {
            this.timer--;
            return false;
        }
    }

    public String toString(){
        return "" + this.timer;
    }
}

class Puzzle_1 {
    public static void main(String[] args) {
        List<String> lines = readFile("./Day 6/input.txt");
        List<Lanternfish> fish = new LinkedList<>();
        
        String[] input = lines.get(0).split(",");
        for (int i = 0; i < input.length; i++) {
            fish.add(new Lanternfish(Integer.parseInt(input[i])));    
        }

        for (int day = 0; day < 80; day++){
            List<Lanternfish> newFish = new LinkedList<>();
            for (Lanternfish f : fish){
                if (f.passDay()) {
                    newFish.add(new Lanternfish());
                }
            }
            fish.addAll(newFish);
            //System.out.println(fish);
        }
        System.out.println(fish.size());
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
