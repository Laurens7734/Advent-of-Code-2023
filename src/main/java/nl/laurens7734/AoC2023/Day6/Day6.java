package nl.laurens7734.AoC2023.Day6;

import nl.laurens7734.AoC2023.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Day6 implements Day {
    @Override
    public String answerOne(List<String> input) {
        List<Race> races = gatherRaces(input);
        long result = 1;
        for (Race r: races) {
            result *= ammountOfWins(r);
        }
        return "" + result;
    }

    @Override
    public String answerTwo(List<String> input) {
        Race r = mergeTimes(input);
        return "" + ammountOfWins(r);
    }

    private List<Race> gatherRaces(List<String> input){
        List<String> times = Arrays.stream(input.get(0).split(" ")).filter(Predicate.not(String::isEmpty)).toList();
        List<String> distances = Arrays.stream(input.get(1).split(" ")).filter(Predicate.not(String::isEmpty)).toList();
        List<Race> races = new ArrayList<>();
        for(int i = 1; i < times.size(); i++){
            races.add(new Race(Integer.parseInt(times.get(i)), Integer.parseInt(distances.get(i))));
        }
        return races;
    }

    private Race mergeTimes(List<String> input){
        String time = input.get(0).replace(" ","").split(":")[1];
        String distance = input.get(1).replace(" ","").split(":")[1];;
        return new Race(Long.parseLong(time),Long.parseLong(distance));
    }

    private long ammountOfWins(Race r){
        for(int i = 1; i < r.getTime(); i++){
            if(i * (r.getTime()-i) > r.getDistance())
                return r.getTime()-(2L*i) +1;
        }
        return -1;
    }
}
