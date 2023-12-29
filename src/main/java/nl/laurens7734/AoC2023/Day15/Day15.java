package nl.laurens7734.AoC2023.Day15;

import nl.laurens7734.AoC2023.Day;

import java.util.HashMap;
import java.util.List;

public class Day15 implements Day {
    @Override
    public String answerOne(List<String> input) {
        String[] parts = input.get(0).split(",");
        long sum = 0;
        for(String s : parts){
            sum += hash(s);
        }
        return "" + sum;
    }

    @Override
    public String answerTwo(List<String> input) {
        String[] parts = input.get(0).split(",");
        HashMap<Integer,Box> boxes = new HashMap<>();
        for(int i = 0; i < 256; i++)
            boxes.put(i,new Box(i));
        for(String s : parts){
            String[] split = s.split("(=)|(-)");
            String label = split[0];
            int box = hash(label);
            if(s.contains("=")){
                int focalLength = Integer.parseInt(split[1]);
                Lens newLens = new Lens(label,focalLength);
                boxes.get(box).addLens(newLens);
            }else{
                boxes.get(box).removeLens(label);
            }
        }
        long sum = 0;
        for(Box b : boxes.values()){
            sum += b.calculateFocalPower();
        }
        return "" + sum;
    }

    private int hash(String input){
        int result = 0;
        for(char c : input.toCharArray()){
            result += c;
            result *= 17;
            result %= 256;
        }
        return result;
    }
}
