package nl.laurens7734.AoC2023.Day11;

import nl.laurens7734.AoC2023.Day;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day11 implements Day {
    @Override
    public String answerOne(List<String> input) {
        int expansionSize = 1;
        List<Integer> rows = new ArrayList<>(IntStream.range(0, input.size()).boxed().toList());
        List<Integer> columns = new ArrayList<>(IntStream.range(0, input.get(0).length()).boxed().toList());
        List<Galaxy> galaxies = new ArrayList<>();

        for(int i = 0; i < input.size(); i++){
            for(int j = 0; j < input.get(0).length(); j++){
                if(input.get(i).charAt(j) == '#'){
                    galaxies.add(new Galaxy(j,i));
                    rows.remove((Integer)i);
                    columns.remove((Integer)j);
                }
            }
        }
        rows = calculateExpansion(rows,expansionSize);
        columns = calculateExpansion(columns,expansionSize);

        for(int row : rows)
            galaxies.forEach(g -> g.expandY(row,expansionSize));
        for(int column : columns)
            galaxies.forEach(g -> g.expandX(column,expansionSize));


        long sum = 0;
        for(int i = 0; i < galaxies.size()-1; i++){
            for(int j = i+1; j < galaxies.size(); j++){
                sum += galaxies.get(i).distance(galaxies.get(j));
            }
        }
        return "" + sum;
    }

    @Override
    public String answerTwo(List<String> input) {
        int expansionSize = 999999;
        List<Integer> rows = new ArrayList<>(IntStream.range(0, input.size()).boxed().toList());
        List<Integer> columns = new ArrayList<>(IntStream.range(0, input.get(0).length()).boxed().toList());
        List<Galaxy> galaxies = new ArrayList<>();

        for(int i = 0; i < input.size(); i++){
            for(int j = 0; j < input.get(0).length(); j++){
                if(input.get(i).charAt(j) == '#'){
                    galaxies.add(new Galaxy(j,i));
                    rows.remove((Integer)i);
                    columns.remove((Integer)j);
                }
            }
        }
        rows = calculateExpansion(rows,expansionSize);
        columns = calculateExpansion(columns,expansionSize);

        for(int row : rows)
            galaxies.forEach(g -> g.expandY(row,expansionSize));
        for(int column : columns)
            galaxies.forEach(g -> g.expandX(column,expansionSize));


        long sum = 0;
        for(int i = 0; i < galaxies.size()-1; i++){
            for(int j = i+1; j < galaxies.size(); j++){
                sum += galaxies.get(i).distance(galaxies.get(j));
            }
        }
        return "" + sum;
    }

    public List<Integer> calculateExpansion(List<Integer> rowsToExpand, int amountOfExpansion)
    {
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < rowsToExpand.size(); i++){
            result.add(rowsToExpand.get(i)+i*amountOfExpansion);
        }
        return result;
    }
}
