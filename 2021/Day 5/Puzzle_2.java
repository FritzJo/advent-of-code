import java.util.List;
import java.util.Set;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;

class Puzzle_2 {
    public static void main(String[] args) {
        List<String> lines = readFile("./Day 5/input.txt");
        Set<String> crossedPoints = new HashSet<>(); 
        Set<String> overlappedPoints = new HashSet<>(); 
        int countOverlaps = 0;
        for (String l : lines) {
            String n1 = l.split(" -> ")[0];
            String n2 = l.split(" -> ")[1];
            int p1x = Integer.parseInt(n1.split(",")[0]);
            int p1y = Integer.parseInt(n1.split(",")[1]);
            int p2x = Integer.parseInt(n2.split(",")[0]);
            int p2y = Integer.parseInt(n2.split(",")[1]);

            // Cover straight cases
            if (p1x == p2x || p1y == p2y){
                for(int i = p1x; i != p2x; i+=(p2x-p1x)/Math.abs(p2x-p1x)){
                    String newPoint = i + "," + p1y;
                    if (crossedPoints.contains(newPoint) && !overlappedPoints.contains(newPoint)){
                        countOverlaps++;
                        overlappedPoints.add(newPoint);
                    } else {
                        crossedPoints.add(newPoint);
                    }
                }
                
                for(int i = p1y; i != p2y; i+=(p2y-p1y)/Math.abs(p2y-p1y)){
                    String newPoint = p1x + "," + i ;
                    if (crossedPoints.contains(newPoint) && !overlappedPoints.contains(newPoint)){
                        countOverlaps++;
                        overlappedPoints.add(newPoint);
                    } else {
                        crossedPoints.add(newPoint);
                    }
                }
            // Cover diagonal cases
            } else {
                int xDirection = (p2x-p1x)/Math.abs(p2x-p1x);
                int yDirection = (p2y-p1y)/Math.abs(p2y-p1y);
                
                for(int i = p1x; i != p2x; i+=xDirection){
                    String newPoint = i + "," + (p1y + yDirection * Math.abs((p1x - i)));
                    if (crossedPoints.contains(newPoint) && !overlappedPoints.contains(newPoint)){
                        countOverlaps++;
                        overlappedPoints.add(newPoint);
                    } else {
                        crossedPoints.add(newPoint);
                    }
                }
            }
            // Add last point manually for some reason 
            String newPoint = p2x + "," + p2y;
            if (crossedPoints.contains(newPoint) && !overlappedPoints.contains(newPoint)){
                countOverlaps++;
                overlappedPoints.add(newPoint);
            } else {
                crossedPoints.add(newPoint);
            }
        }
        System.out.println("Overlaps: " +  countOverlaps);
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
