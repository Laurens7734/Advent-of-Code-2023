package nl.laurens7734.AoC2023.Day9;

import nl.laurens7734.AoC2023.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day9 implements Day {
    @Override
    public String answerOne(List<String> input) {
        long total = 0;
        for(String line : input){
            List<Long> numbers = Arrays.stream(line.split(" ")).map(Long::parseLong).toList();
            long prediction = predictNext(numbers);
            total += prediction;
        }
        return "" + total;
    }

    @Override
    public String answerTwo(List<String> input) {
        long total = 0;
        for(String line : input){
            List<Long> numbers = Arrays.stream(line.split(" ")).map(Long::parseLong).toList();
            long prediction = predictPrevious(numbers);
            total += prediction;
        }
        return "" + total;
    }

    private long predictNext(List<Long> startingSequence){
        List<List<Long>> allSequences = new ArrayList<>();
        allSequences.add(startingSequence);
        while(allSequences.get(allSequences.size()-1).stream().anyMatch(x -> x != 0)){
            allSequences.add(nextSequence(allSequences.get(allSequences.size()-1)));
        }
        long result = 0;
        for(int i = allSequences.size()-1; i >= 0 ; i--){
            List<Long> sequence = allSequences.get(i);
            result += sequence.get(sequence.size()-1);
        }
        return result;
    }

    private long predictPrevious(List<Long> startingSequence){
        List<List<Long>> allSequences = new ArrayList<>();
        allSequences.add(startingSequence);
        while(allSequences.get(allSequences.size()-1).stream().anyMatch(x -> x != 0)){
            allSequences.add(nextSequence(allSequences.get(allSequences.size()-1)));
        }
        long result = 0;
        for(int i = allSequences.size()-1; i >= 0 ; i--){
            List<Long> sequence = allSequences.get(i);
            result = sequence.get(0)-result;
        }
        return result;
    }

    private List<Long> nextSequence(List<Long> current){
        List<Long> result = new ArrayList<>();
        for(int i = 0; i < current.size()-1;i++){
            result.add(current.get(i+1)-current.get(i));
        }
        return result;
    }
}
