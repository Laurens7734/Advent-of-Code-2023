package nl.laurens7734.AoC2023.Day3;

import nl.laurens7734.AoC2023.Day;

import java.util.ArrayList;
import java.util.List;

public class Day3 implements Day {
    @Override
    public String answerOne(List<String> input) {
        long sum = 0;
        List<PartNumber> partNumbers = findNumbers(input);
        List<Coordinate> symbols = findSymbols(input);
        for(Coordinate c : symbols){
            for(PartNumber p : partNumbers){
                if(p.isNextTo(c) && !p.isAdded()){
                    sum += p.getValue();
                    p.setAdded(true);
                }
            }
        }
        return "" + sum;
    }

    @Override
    public String answerTwo(List<String> input) {
        long sum = 0;
        List<PartNumber> partNumbers = findNumbers(input);
        List<Coordinate> symbols = findGearSymbols(input);
        for(Coordinate c : symbols){
            int nextTo = 0;
            long gearRatio = 1;
            for(PartNumber p : partNumbers){
                if(p.isNextTo(c)){
                    gearRatio *= p.getValue();
                    nextTo++;
                }
            }
            if(nextTo == 2)
                sum += gearRatio;
        }
        return "" + sum;
    }

    public List<PartNumber> findNumbers(List<String> input){
        List<PartNumber> result = new ArrayList<>();

        for(int i = 0; i < input.size(); i++){
            String s = input.get(i);
            PartNumber next = null;
            for(int j = 0; j < s.length();j++){
                char c = s.charAt(j);
                if(c >= '0' && c <= '9'){
                    if(next == null)
                        next = new PartNumber(j,i);
                    next.addDigit(c);
                }
                else{
                    if(next != null){
                        result.add(next);
                        next = null;
                    }
                }
            }
            if(next != null)
                result.add(next);
        }
        return result;
    }

    public List<Coordinate> findSymbols(List<String> input){
        List<Coordinate> result = new ArrayList<>();

        for(int i = 0; i < input.size(); i++){
            String s = input.get(i);
            for(int j = 0; j < s.length();j++) {
                char c = s.charAt(j);
                if((c >= '0' && c <= '9')||c == '.')
                    continue;
                result.add(new Coordinate(j,i));
            }
        }
        return result;
    }

    public List<Coordinate> findGearSymbols(List<String> input){
        List<Coordinate> result = new ArrayList<>();

        for(int i = 0; i < input.size(); i++){
            String s = input.get(i);
            for(int j = 0; j < s.length();j++) {
                char c = s.charAt(j);
                if(c == '*')
                    result.add(new Coordinate(j,i));
            }
        }
        return result;
    }
}
