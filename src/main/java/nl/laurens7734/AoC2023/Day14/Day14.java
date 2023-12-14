package nl.laurens7734.AoC2023.Day14;

import nl.laurens7734.AoC2023.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day14 implements Day {

    int[] north = {0,-1};
    int[] south = {0,1};
    int[] east = {1,0};
    int[] west = {-1,0};
    @Override
    public String answerOne(List<String> input) {
        char[][] grid = new char[input.size()][];
        for(int i = 0; i < input.size();i++){
            grid[i] = input.get(i).toCharArray();
        }
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length;j++){
                if(grid[i][j] == 'O')
                    moveRock(grid,j,i,north);
            }
        }
        long totalLoad = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length;j++){
                if(grid[i][j] == 'O')
                    totalLoad+=grid.length-i;
            }
        }
        return "" + totalLoad;
    }

    @Override
    public String answerTwo(List<String> input) {
        int amountOfCycles = 1_000_000_000;
        char[][] grid = new char[input.size()][];
        for(int i = 0; i < input.size();i++){
            grid[i] = input.get(i).toCharArray();
        }
        boolean loopFound = false;
        List<char[][]> foundGrids = new ArrayList<>();
        for(int i = 0; i < amountOfCycles; i++){
            foundGrids.add(copyGrid(grid));
            cycle(grid);
            if(!loopFound){
                for(int j = 0; j < foundGrids.size(); j++){
                    if(Arrays.deepEquals(foundGrids.get(j),grid)){
                        int loopSize = 1+i-j;
                        i = amountOfCycles-(amountOfCycles-i)%loopSize;
                        loopFound = true;
                        break;
                    }
                }
            }

        }
        long totalLoad = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length;j++){
                if(grid[i][j] == 'O')
                    totalLoad+=grid.length-i;
            }
        }
        return "" + totalLoad;
    }

    private char[][] copyGrid(char[][] grid){
        char[][] newArray = grid.clone();
        for(int i =0 ; i < grid.length; i++){
            newArray[i] = grid[i].clone();
        }
        return newArray;
    }

    private void cycle(char[][] grid){
        tilt(grid,north);
        tilt(grid,west);
        tilt(grid,south);
        tilt(grid,east);
    }

    private void tilt(char[][] grid, int[] direction){
        boolean flipLoop = direction[0]+direction[1] > 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length;j++){
                int x = flipLoop?grid[0].length-(j+1):j;
                int y = flipLoop?grid.length-(i+1):i;
                if(grid[y][x] == 'O')
                    moveRock(grid,x,y,direction);
            }
        }
    }

    private void moveRock(char[][] grid, int x, int y, int[] direction){
        if(y+direction[1] < 0 || y+direction[1] >= grid.length||x+direction[0]<0||x+direction[0]>=grid[0].length)
            return;
        char next = grid[y+direction[1]][x+direction[0]];
        while(next == '.'){
            grid[y][x] = '.';
            y+=direction[1];
            x+=direction[0];
            grid[y][x] = 'O';
            if(y+direction[1] < 0 || y+direction[1] >= grid.length||x+direction[0]<0||x+direction[0]>=grid[0].length)
                break;
            next = grid[y+direction[1]][x+direction[0]];
        }
    }
}
