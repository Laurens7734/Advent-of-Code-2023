package nl.laurens7734.AoC2023.Day12;

import nl.laurens7734.AoC2023.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day12 implements Day {
    @Override
    public String answerOne(List<String> input) {
        long total = 0;
        for(String s : input){
            String[] split = s.split("( )|(,)");
            String arrangement = split[0];
            List<Integer> numbers = Arrays.stream(split).filter(x -> !x.contains("?")).map(Integer::parseInt).toList();
            Line line = new Line(arrangement,numbers);
            long combinations = line.countPossibilities();
            total += combinations;
        }
        return "" + total;
    }

    @Override
    public String answerTwo(List<String> input) {
        long total = 0;
        int repeatAmount = 5;
        for(String s : input){
            String[] split = s.split("( )|(,)");
            String arrangement = lengthenArrangement(split[0],repeatAmount);
            List<Integer> numbers = lengthenNumbers(Arrays.stream(split).filter(x -> !x.contains("?")).map(Integer::parseInt).toList(),repeatAmount);
            Line line = new Line(arrangement,numbers);
            long combinations = line.countPossibilities();
            total += combinations;
        }

        return "" + total;
    }

    private String lengthenArrangement(String input, int amount){
        StringBuilder builder = new StringBuilder(input);
        for(int i = 1; i < amount; i++){
            builder.append("?");
            builder.append(input);
        }
        return builder.toString();
    }

    private List<Integer> lengthenNumbers(List<Integer> numbers, int amount){
        List<Integer> newList = new ArrayList<>();
        for(int i = 0; i < amount; i++){
            newList.addAll(numbers);
        }
        return newList;
    }
}
