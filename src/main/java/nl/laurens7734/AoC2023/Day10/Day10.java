package nl.laurens7734.AoC2023.Day10;

import nl.laurens7734.AoC2023.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day10 implements Day {
    List<Character> north = new ArrayList<>(Arrays.asList('|', 'J', 'L', 'S'));
    List<Character> south = new ArrayList<>(Arrays.asList('|', '7', 'F', 'S'));
    List<Character> east = new ArrayList<>(Arrays.asList('-', 'F', 'L', 'S'));
    List<Character> west = new ArrayList<>(Arrays.asList('-', 'J', '7', 'S'));

    @Override
    public String answerOne(List<String> input) {
        char[][] area = new char[input.size()][input.get(0).length()];
        int startX=-1, startY=-1;
        for(int i = 0; i < input.size();i++) {
            area[i] = input.get(i).toCharArray();
            if (input.get(i).contains("S")){
                startX = input.get(i).indexOf('S');
                startY = i;
            }
        }

        List<Pipe> currentLocation = new ArrayList<>();
        int steps =1;

        for(char c : directions('S')){
            int[] offset = coordinateOffset(c);
            if(startX+offset[0] < 0 || startX+offset[0] >= input.get(0).length() || startY+offset[1] < 0 || startY+offset[1] >= input.size())
                continue;
            if(connectsToStart(area[startY+offset[1]][startX+offset[0]],reverseDirection(c))){
                currentLocation.add(new Pipe(startX+offset[0],startY+offset[1],c));
            }
        }

        while(!currentLocation.get(0).equals(currentLocation.get(1))){
            List<Pipe> nextLocation = new ArrayList<>();
            for(Pipe p : currentLocation){
                char nextDirection = nextDirection(p.getDirection(),area[p.getY()][p.getX()]);
                int[] offset = coordinateOffset(nextDirection);
                nextLocation.add(new Pipe(p.getX()+offset[0],p.getY()+offset[1],nextDirection));
            }
            currentLocation = nextLocation;
            steps++;
        }
        return "" + steps;
    }

    @Override
    public String answerTwo(List<String> input) {
        char[][] area = new char[input.size()][input.get(0).length()];
        char[][] pipeMap = new char[input.size()][input.get(0).length()];
        char[] empty = ".".repeat(input.get(0).length()).toCharArray();
        int startX=-1, startY=-1;
        for(int i = 0; i < input.size();i++) {
            area[i] = input.get(i).toCharArray();
            pipeMap[i] = empty.clone();
            if (input.get(i).contains("S")){
                startX = input.get(i).indexOf('S');
                startY = i;
            }
        }

        List<Pipe> currentLocation = new ArrayList<>();
        List<Character> startDirections = new ArrayList<>();

        for(char c : directions('S')){
            int[] offset = coordinateOffset(c);
            if(startX+offset[0] < 0 || startX+offset[0] >= input.get(0).length() || startY+offset[1] < 0 || startY+offset[1] >= input.size())
                continue;
            if(connectsToStart(area[startY+offset[1]][startX+offset[0]],reverseDirection(c))){
                currentLocation.add(new Pipe(startX+offset[0],startY+offset[1],c));
                startDirections.add(c);
            }
        }

        pipeMap[startY][startX] = 'P';
        area[startY][startX] = startShape(startDirections);

        while(!currentLocation.get(0).equals(currentLocation.get(1))){
            List<Pipe> nextLocation = new ArrayList<>();
            for(Pipe p : currentLocation){
                pipeMap[p.getY()][p.getX()] = 'P';
                char nextDirection = nextDirection(p.getDirection(),area[p.getY()][p.getX()]);
                int[] offset = coordinateOffset(nextDirection);
                nextLocation.add(new Pipe(p.getX()+offset[0],p.getY()+offset[1],nextDirection));
            }
            currentLocation = nextLocation;
        }
        pipeMap[currentLocation.get(0).getY()][currentLocation.get(0).getX()] = 'P';

        FindTopLeftPipe:
        for(int i = 0; i < input.size();i++){
            for(int j = 0; j < area[0].length; j++){
                if(pipeMap[i][j] == 'P'){
                    startX = j;
                    startY = i;
                    break FindTopLeftPipe;
                }
            }
        }

        char currentDirection = 'E';
        int[] travelOffset = coordinateOffset(currentDirection);
        int[] outOffset = coordinateOffset(outDirection(currentDirection));
        int[] inOffset = coordinateOffset(reverseDirection(outDirection(currentDirection)));

        int currentX = startX+travelOffset[0];
        int currentY = startY+travelOffset[1];
        while(!(currentX == startX && currentY == startY)){
            char shape = area[currentY][currentX];
            if(isCorner(shape)){
                currentDirection = nextDirection(currentDirection,shape);
                int[] newTravelOffset = coordinateOffset(currentDirection);
                if(newTravelOffset[0] == outOffset[0] && newTravelOffset[1] == outOffset[1]){
                    placeChar(pipeMap, 'I', currentX+inOffset[0],currentY+inOffset[1]);
                    placeChar(pipeMap, 'I', currentX+travelOffset[0],currentY+travelOffset[1]);
                }
                if(newTravelOffset[0] == inOffset[0] && newTravelOffset[1] == inOffset[1]){
                    placeChar(pipeMap, 'O', currentX+outOffset[0],currentY+outOffset[1]);
                    placeChar(pipeMap, 'O', currentX+travelOffset[0],currentY+travelOffset[1]);
                }
                travelOffset = newTravelOffset;
                outOffset = coordinateOffset(outDirection(currentDirection));
                inOffset = coordinateOffset(reverseDirection(outDirection(currentDirection)));
            }
            else{
                placeChar(pipeMap, 'I', currentX+inOffset[0],currentY+inOffset[1]);
                placeChar(pipeMap, 'O', currentX+outOffset[0],currentY+outOffset[1]);
            }
            currentX += travelOffset[0];
            currentY += travelOffset[1];
        }

        long inside = 0;
        for(int i = 0; i < pipeMap.length; i++){
            for(int j = 0; j < pipeMap[0].length; j++){
                if(pipeMap[i][j] == '.')
                    findSide(pipeMap, j,i);
                if(pipeMap[i][j] == 'I')
                    inside++;
            }
        }
        return "" + inside;
    }

    private boolean connectsToStart(char shape, char startDirection){
        return directions(shape).contains(startDirection);
    }

    private char nextDirection(char direction, char shape){
        List<Character> directions = directions(shape);
        directions.remove((Character) reverseDirection(direction));
        return directions.get(0);
    }

    private boolean isCorner(char shape){
        List<Character> corners = new ArrayList<>(Arrays.asList('F', '7', 'L', 'J'));
        return corners.contains(shape);
    }

    private List<Character> directions(char shape){
        List<Character> result = new ArrayList<>();
        if(north.contains(shape))
            result.add('N');
        if(south.contains(shape))
            result.add('S');
        if(east.contains(shape))
            result.add('E');
        if(west.contains(shape))
            result.add('W');
        return result;
    }

    private char reverseDirection(char direction){
        if(direction == 'N')
            return 'S';
        if(direction == 'S')
            return 'N';
        if(direction == 'W')
            return 'E';
        if(direction == 'E')
            return 'W';
        return 'X';
    }

    private char outDirection(char direction){
        if(direction == 'N')
            return 'W';
        if(direction == 'S')
            return 'E';
        if(direction == 'W')
            return 'S';
        if(direction == 'E')
            return 'N';
        return 'X';
    }

    private int[] coordinateOffset(char direction){
        if(direction == 'N')
            return new int[]{0, -1};
        if(direction == 'S')
            return new int[]{0, 1};
        if(direction == 'W')
            return new int[]{-1, 0};
        if(direction == 'E')
            return new int[]{1, 0};
        return new int[]{0, 0};
    }

    private char startShape(List<Character> directions){
        if(directions.contains('N')){
            if(directions.contains('E'))
                return 'L';
            if(directions.contains('W'))
                return 'J';
            if(directions.contains('S'))
                return '|';
        }
        if(directions.contains('S')){
            if(directions.contains('E'))
                return 'F';
            if(directions.contains('W'))
                return '7';
        }
        return '-';
    }

    private void placeChar(char[][] area, char toPlace, int x, int y){
        if(x < 0 || y < 0 || x >= area[0].length || y >= area.length)
            return;
        if(area[y][x] != '.')
            return;
        area[y][x] = toPlace;
    }

    private void findSide(char[][] area, int x, int y){
        for(int i = y; i >= 0; i--){
            if(area[i][x] != '.'){
                area[y][x] = area[i][x];
                return;
            }
        }
        area[y][x] = 'O';
    }
}
