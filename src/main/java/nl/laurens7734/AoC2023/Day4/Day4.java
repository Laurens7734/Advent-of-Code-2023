package nl.laurens7734.AoC2023.Day4;

import nl.laurens7734.AoC2023.Day;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Day4 implements Day {
    @Override
    public String answerOne(List<String> input) {
        long result = 0;
        for(String s : input){
            String[] split = s.split("(:)|[|]");
            List<Integer> winningNumbers = getNumbers(split[1]);
            List<Integer> myNumbers = getNumbers(split[2]);
            int matches = amountOfWinningNumbers(winningNumbers,myNumbers);
            if(matches > 0)
                result += 1L << (matches-1);
        }
        return "" + result;
    }

    @Override
    public String answerTwo(List<String> input) {
        int[] numberOfCards = new int[input.size()];
        for(int i = 0; i < input.size();i++){
            String s = input.get(i);
            numberOfCards[i]++;
            String[] split = s.split("(:)|[|]");
            List<Integer> winningNumbers = getNumbers(split[1]);
            List<Integer> myNumbers = getNumbers(split[2]);
            int matches = amountOfWinningNumbers(winningNumbers,myNumbers);
            for(int j = 1; j <= matches; j++){
                numberOfCards[i+j] += numberOfCards[i];
            }
        }
        return "" + IntStream.of(numberOfCards).sum();
    }

    private List<Integer> getNumbers(String input){
        List<Integer> result = new ArrayList<>();
        String[] numbers = input.split(" ");
        for(String num : numbers){
            if(!num.isEmpty())
                result.add(Integer.parseInt(num));
        }
        return result;
    }

    private int amountOfWinningNumbers(List<Integer> winners, List<Integer> numbers){
        int count = 0;
        for(Integer i : winners){
            if(numbers.contains(i))
                count++;
        }
        return count;
    }
}
