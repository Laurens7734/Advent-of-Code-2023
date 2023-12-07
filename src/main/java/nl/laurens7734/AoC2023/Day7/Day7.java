package nl.laurens7734.AoC2023.Day7;

import nl.laurens7734.AoC2023.Day;

import java.util.ArrayList;
import java.util.List;

public class Day7 implements Day {
    @Override
    public String answerOne(List<String> input) {
        List<Hand> hands = new ArrayList<>();
        for(String s : input){
            String[] split = s.split(" ");
            hands.add(new Hand(split[0], Integer.parseInt(split[1])));
        }
        hands = hands.stream().sorted().toList();
        long result = 0;
        for(long i = 0; i < hands.size();i++){
            result += (i+1) * hands.get((int)i).getBid();
        }
        return "" + result;
    }

    @Override
    public String answerTwo(List<String> input) {
        List<Hand2> hands = new ArrayList<>();
        for(String s : input){
            String[] split = s.split(" ");
            hands.add(new Hand2(split[0], Integer.parseInt(split[1])));
        }
        hands = hands.stream().sorted().toList();
        long result = 0;
        for(long i = 0; i < hands.size();i++){
            result += (i+1) * hands.get((int)i).getBid();
        }
        return "" + result;
    }
}
