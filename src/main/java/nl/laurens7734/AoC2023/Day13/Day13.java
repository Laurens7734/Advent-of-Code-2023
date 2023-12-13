package nl.laurens7734.AoC2023.Day13;

import nl.laurens7734.AoC2023.Day;

import java.util.ArrayList;
import java.util.List;

public class Day13 implements Day {
    @Override
    public String answerOne(List<String> input) {
        List<String> block = new ArrayList<>();
        long score = 0;
        for(String s : input){
            if(s.isEmpty()){
                score += calculateBlockScore(block);
                block = new ArrayList<>();
            }else{
                block.add(s);
            }
        }
        if(!block.isEmpty())
            score += calculateBlockScore(block);
        return "" + score;
    }

    @Override
    public String answerTwo(List<String> input) {
        List<String> block = new ArrayList<>();
        long score = 0;
        for(String s : input){
            if(s.isEmpty()){
                score += calculateSmudgedScore(block);
                block = new ArrayList<>();
            }else{
                block.add(s);
            }
        }
        if(!block.isEmpty())
            score += calculateSmudgedScore(block);
        return "" + score;
    }

    private long calculateSmudgedScore(List<String> block){
        for(int i = 0; i < block.size()-1;i++){
            if(countErrorsInLine(block.get(i),block.get(i+1)) <= 1)
                if(countErrorsHorizontal(block,i) == 1)
                    return (i+1)*100L;
            if(i+3 < block.size() && block.get(i).equals(block.get(i+3)))
                if(countErrorsHorizontal(block,i+1) == 1)
                    return (i+2)*100L;
        }
        verticalLoop:
        for(int i = 0; i < block.get(0).length()-1;i++){
            int errorCount = 0;
            for(String s : block){
                if(!(s.charAt(i) == s.charAt(i+1)))
                    errorCount++;
                if(errorCount > 1)
                    continue verticalLoop;
            }
            if(countErrorsVertical(block,i) == 1)
                return i+1;
        }
        verticalLoop2:
        for(int i = 0; i < block.get(0).length()-3;i++){
            for(String s : block){
                if(!(s.charAt(i) == s.charAt(i+3)))
                    continue verticalLoop2;
            }
            if(countErrorsVertical(block,i+1) == 1)
                return i+2;
        }
        return -1;
    }

    private int countErrorsHorizontal(List<String> block, int start){
        int count = 0;
        for(int i = 0; i <= start;i++){
            if(block.size() == start+1+i )
                break;
            if(!block.get(start-i).equals(block.get(start+1+i)))
                count += countErrorsInLine(block.get(start-i),block.get(start+1+i));
        }
        return count;
    }

    private int countErrorsVertical(List<String> block, int start){
        int count = 0;
        for(String s : block){
            String left = s.substring(0,start+1);
            String right = new StringBuilder(s.substring(start+1)).reverse().toString();
            if(left.length() > right.length()){
                left = left.substring(left.length()-right.length());
            }else{
                right = right.substring(right.length()-left.length());
            }
            count += countErrorsInLine(left,right);
        }
        return count;
    }

    private int countErrorsInLine(String line1, String line2){
        int count = 0;
        for(int i = 0; i < line1.length();i++){
            if(line1.charAt(i) != line2.charAt(i))
                count++;
        }
        return count;
    }

    private long calculateBlockScore(List<String> block){
        for(int i = 0; i < block.size()-1;i++){
            if(block.get(i).equals(block.get(i+1)))
                if(isHorizontalSplit(block,i))
                    return (i+1)*100L;
        }
        verticalLoop:
        for(int i = 0; i < block.get(0).length()-1;i++){
            for(String s : block){
                if(!(s.charAt(i) == s.charAt(i+1)))
                    continue verticalLoop;
            }
            if(isVerticalSplit(block,i))
                return i+1;
        }
        return -1;
    }

    private boolean isVerticalSplit(List<String> block, int start){
        for(String s : block){
            String left = s.substring(0,start+1);
            String right = new StringBuilder(s.substring(start+1)).reverse().toString();
            if(left.length() > right.length()){
                if(!left.endsWith(right))
                    return false;
            }else{
                if(!right.endsWith(left))
                    return false;
            }
        }
        return true;
    }

    private boolean isHorizontalSplit(List<String> block, int start){
        for(int i = 0; i <= start;i++){
            if(block.size() == start+1+i )
                break;
            if(!block.get(start-i).equals(block.get(start+1+i)))
                return false;
        }
        return true;
    }
}
