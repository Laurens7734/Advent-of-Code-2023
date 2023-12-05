package nl.laurens7734.AoC2023.Day5;

import nl.laurens7734.AoC2023.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;

public class Day5 implements Day {
    @Override
    public String answerOne(List<String> input) {
        List<Long> seeds = Arrays.stream(input.get(0).split(": ")[1].split(" ")).map(Long::parseLong).toList();
        List<List<Range>> maps = gatherMaps(input);
        List<Long> results = new ArrayList<>();
        for(long seed : seeds){
            results.add(mapNumber(seed,maps));
        }
        return results.stream().min(Long::compare).get().toString();
    }

    @Override
    public String answerTwo(List<String> input) {
        List<Range> seeds = gatherSeeds(input.get(0));
        List<List<Range>> maps = gatherMaps(input);
        List<Long> results = new ArrayList<>();
        for(Range seed : seeds){
            results.add(mapRange(seed,maps));
        }
        return results.stream().min(Long::compare).get().toString();
    }

    private List<List<Range>> gatherMaps(List<String> input){
        List<List<Range>> result = new ArrayList<>();
        List<Range> currentList = null;
        for(String line : input){
            if(line.isEmpty()){
                if(currentList != null)
                    result.add(currentList);
                currentList = new ArrayList<>();
                continue;
            }
            if(line.charAt(0) < '0' || line.charAt(0) > '9')
                continue;
            List<Long> numbers = Arrays.stream(line.split(" ")).map(Long::parseLong).toList();
            currentList.add(new Range(numbers.get(1),numbers.get(0),numbers.get(2)));
        }
        result.add(currentList);
        return result;
    }

    private long mapNumber(long number,List<List<Range>> maps){
        long newNum = number;
        for(List<Range> map : maps){
            for(Range r : map){
                if(r.inRange(newNum)){
                    newNum = r.getDestination(newNum);
                    break;
                }
            }
        }
        return newNum;
    }

    private Long mapRange(Range input, List<List<Range>> maps){
        List<Range> current = new ArrayList<>();
        current.add(input);
        List<Range> next = new ArrayList<>();
        for(List<Range> map : maps){
            for(int i = 0; i < current.size(); i++){
                boolean foundOverlap = false;
                for(Range r : map){
                    if(r.hasOverlap(current.get(i))){
                        current.addAll(r.remaining(current.get(i)));
                        next.add(r.getDestination(current.get(i)));
                        foundOverlap = true;
                    }
                }
                if(!foundOverlap)
                    next.add(current.get(i));
            }
            current = next;
            next = new ArrayList<>();
        }
        return current.stream().map(Range::getStart).min(Long::compare).get();
    }

    private List<Range> gatherSeeds(String input){
        List<Range> result = new ArrayList<>();
        List<Long> numbers = Arrays.stream(input.split(": ")[1].split(" ")).map(Long::parseLong).toList();
        for(int i = 0; i < numbers.size()-1; i+=2){
            result.add(new Range(numbers.get(i),0,numbers.get(i+1)));
        }
        return result;
    }
}
