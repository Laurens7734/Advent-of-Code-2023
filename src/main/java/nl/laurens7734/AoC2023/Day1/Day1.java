package nl.laurens7734.AoC2023.Day1;

import nl.laurens7734.AoC2023.Day;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;

public class Day1 implements Day {
    List<Pair<String,String>> replacements;

    public Day1(){
        replacements = new ArrayList<>();
        replacements.add(new Pair<String, String>("one", "1"));
        replacements.add(new Pair<String, String>("two", "2"));
        replacements.add(new Pair<String, String>("three", "3"));
        replacements.add(new Pair<String, String>("four", "4"));
        replacements.add(new Pair<String, String>("five", "5"));
        replacements.add(new Pair<String, String>("six", "6"));
        replacements.add(new Pair<String, String>("seven", "7"));
        replacements.add(new Pair<String, String>("eight", "8"));
        replacements.add(new Pair<String, String>("nine", "9"));
    }
    @Override
    public String answerOne(List<String> input) {
        int result = 0;
        for(String s : input){
            char num1 = findFirst(s);
            char num2 = findLast(s);
            result += Integer.parseInt(""+num1+num2);
        }
        return "" + result;
    }

    @Override
    public String answerTwo(List<String> input) {
        int result = 0;
        for(String s : input){
            String replaced = replaceNames(s.toLowerCase());
            char num1 = findFirst(replaced);
            char num2 = findLast(replaced);
            result += Integer.parseInt(""+num1+num2);
        }
        return "" + result;
    }

    private char findFirst(String line){
        for(int i = 0; i < line.length(); i++){
            char c = line.toCharArray()[i];
            if(c >= '0' && c <= '9'){
                return c;
            }
        }
        return 'a';
    }
    private char findLast(String line){
        for(int i = 1; i <= line.length(); i++){
            char c = line.toCharArray()[line.length() - i];
            if(c >= '0' && c <= '9'){
                return c;
            }
        }
        return 'a';
    }

    private String replaceNames(String s){
        String result = s;
        for (Pair<String,String> replacement:replacements) {
            result = result.replace(replacement.getValue0(),replacement.getValue0()+replacement.getValue1()+replacement.getValue0());
        }
        return result;
    }
}
