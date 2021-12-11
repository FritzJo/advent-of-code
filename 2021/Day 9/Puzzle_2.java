import java.util.List;
import java.util.Queue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

class Point {
    int i;
    int j;
    int value;

    public Point(int i, int j, int value) {
        this.i = i;
        this.j = j;
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Point other = (Point) obj;
        if (this.i != other.i || this.j != other.j || this.value != other.value) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.i;
        hash = 53 * hash + this.j;
        hash = 53 * hash + this.value;
        return hash;
    }

    public String toString(){
        return "(" + i + ", " + j + " -> " + value + " ) ";
    }
}

class Puzzle_2 {
    public static int getBasin(int[][] array, int x, int y) {
        HashSet<Point> visited = new HashSet<>();
        Queue<Point> toVisit = new LinkedList<Point>();
        Point point = new Point(x, y, array[x][y]);
        toVisit.add(point);
        while(!toVisit.isEmpty()) {
            Point currentPoint = toVisit.poll();
            if(visited.contains(currentPoint)){
                continue;
            }
            visited.add(currentPoint);
            x = currentPoint.i;
            y = currentPoint.j;
            for(int i = -1; i<=1; i++) {
                for(int j = -1; j<=1; j++) {
                    if (x+i >= array.length || x+i<0 || y+j<0 || y+j >= array[x+i].length || i==j || i*-1 == j) // wonky, but it works!
                        continue;
                    int v =  array[x+i][y+j];
                    Point p = new Point(x+i, y+j, v);
                    if (!toVisit.contains(p) && !visited.contains(p) && v != 9){
                        toVisit.add(p);
                    }
                }   
            }
        }
        return visited.size();
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

        List<Integer> basinSizes = new LinkedList<>();
    
        // Look for low points
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                if (isLow(i, j, map)){
                    basinSizes.add(getBasin(map, i, j));
                }
            }
        }
        // Maybe this can be solved in a more elegant way, but I'm tired
        Collections.sort(basinSizes);
        Collections.reverse(basinSizes);
        System.out.println(basinSizes.stream().limit(3).mapToInt(i -> i).reduce(1, (acc, value) -> acc * value));
    }

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
