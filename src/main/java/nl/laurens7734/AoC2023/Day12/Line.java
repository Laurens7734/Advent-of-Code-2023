package nl.laurens7734.AoC2023.Day12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Line {
    private String symbols;
    private final List<Range> ranges;

    private final List<Integer> numbers;

    public Line(String symbols,List<Integer> numbers){
        this.numbers = numbers;
        ranges = new ArrayList<>();
        int minLength = minSize(numbers);
        symbols = symbols.replace('.', ' ').strip();
        if(symbols.length() == minLength){
            StringBuilder result = new StringBuilder();
            for(int num : numbers){
                result.append("#".repeat(num));
                result.append(" ");
            }
            this.symbols = result.toString().strip();
        }else{
            int offSet = 0;
            this.symbols = symbols;
            for(int i : numbers){
                ranges.add(new Range(offSet,symbols.length()-(1+minLength-(offSet+i)),i));
                offSet += i+1;
            }
            fillKnown();
            while(shrinkRanges()){
                realignRanges();
                fillKnown();
            }
        }
    }

    public long countPossibilities(){
        if(ranges.isEmpty())
            return 1;
        HashMap<String,Boolean> possibilities = allPossibilities(new StringBuilder(symbols),ranges);
        return possibilities.size();
    }

    private HashMap<String,Boolean> allPossibilities(StringBuilder start, List<Range> ranges){
        Range toUse = ranges.get(0);
        HashMap<String,Boolean> answer = new HashMap<>();
        mainLoop:
        for(int i = toUse.getLeftMost(); i<=toUse.getRightMost()-(toUse.getSize()-1);i++){
            StringBuilder currentBuilder = new StringBuilder(start);
            for(int j = 0; j < toUse.getSize(); j++){
                if(currentBuilder.toString().charAt(i+j) == ' ')
                    continue mainLoop;
            }
            currentBuilder.replace(i,i+(toUse.getSize()),"#".repeat(toUse.getSize()));
            if(ranges.size() > 1){
                answer.putAll(allPossibilities(currentBuilder,ranges.subList(1,ranges.size())));
            }else{
                String possibility = currentBuilder.toString().replace('?', ' ');
                if(validate(possibility,numbers))
                    answer.put(possibility,true);
            }
        }
        return answer;
    }

    private void realignRanges(){
        for(int i = 1; i < ranges.size(); i++){
            Range current = ranges.get(i);
            current.setLeftMost(ranges.get(i-1).getLeftMost()+ranges.get(i-1).getSize());
            ranges.get(i-1).setRightMost(current.getRightMost()-current.getSize());
        }
    }

    private void fillKnown(){
        StringBuilder result = new StringBuilder(symbols);
        for(Range num : ranges){
            if(num.hasKnownFields()){
                result.replace(num.startOfKnown(),num.endOfKnown(),"#".repeat(num.endOfKnown()-num.startOfKnown()));
            }
        }
        if(symbols.startsWith("#"))
            result.replace(0,ranges.get(0).getSize()+1,"#".repeat(ranges.get(0).getSize())+" ");
        if(symbols.endsWith("#"))
            result.replace(symbols.length()-(ranges.get(ranges.size()-1).getSize()+1),symbols.length()," " + "#".repeat(ranges.get(ranges.size()-1).getSize()));
        symbols = result.toString();
    }

    private boolean shrinkRanges(){
        boolean changes = false;
        for(int i = 0; i < symbols.length(); i++) {
            if(symbols.charAt(i) == ' '){
                if(shrinkToGap(i))
                    changes = true;
            }
        }
        int start = -1;
        boolean inBlock = false;
        for(int i = 0; i < symbols.length(); i++){
            if(inBlock){
                if(symbols.charAt(i) != '#'){
                    if(assignToExisting(start, i-1))
                        changes = true;
                    inBlock = false;
                }
            }else{
                if(symbols.charAt(i) == '#'){
                    inBlock = true;
                    start = i;
                }
            }
        }
        if(inBlock){
            if(assignToExisting(start, symbols.length()-1))
                changes = true;
        }
        return changes;
    }

    private boolean shrinkToGap(int gap){
        boolean changes = false;
        for(Range r : ranges){
            if(r.checkGap(gap))
                changes = true;
        }
        return changes;
    }

    private boolean assignToExisting(int start,int end){
        List<Range> possibleRanges = ranges.stream().filter(x -> x.getSize()>= (end-start)+1 && x.getLeftMost() <= start && x.getRightMost() >= end).toList();
        if(possibleRanges.size() == 1){
            Range r = possibleRanges.get(0);
            if(r.getLeftMost() == end-(r.getSize()-1) && r.getRightMost() == start+(r.getSize()-1))
                return false;
            return (r.setLeftMost(end-(r.getSize()-1)) || r.setRightMost(start+(r.getSize()-1)));
        }
        return false;
    }

    private int minSize(List<Integer> numbers){
        int minLength = numbers.size()-1;
        for(Integer i : numbers)
            minLength += i;
        return minLength;
    }
    private boolean validate(String line, List<Integer> number){
        List<String> split = Arrays.stream(line.split(" ")).filter(x -> !x.isEmpty()).toList();
        if(split.size() != number.size())
            return false;
        for(int i = 0; i < split.size(); i++){
            if(number.get(i) != split.get(i).length())
                return false;
        }
        return true;
    }
}
