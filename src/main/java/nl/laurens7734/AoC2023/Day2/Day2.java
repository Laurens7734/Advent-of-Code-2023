package nl.laurens7734.AoC2023.Day2;

import nl.laurens7734.AoC2023.Day;

import java.util.List;

public class Day2 implements Day {
    int red = 12;
    int green = 13;
    int blue = 14;
    @Override
    public String answerOne(List<String> input) {
        long result = 0;
        checkGames:
        for (String s : input){
            String[] separateGame = s.split(":");
            long id = Long.parseLong(separateGame[0].substring(5));
            String[] uniqueCubes = separateGame[1].split("(; )|(, )");
            for(String st : uniqueCubes){
                String[] parts = st.trim().split(" ");
                int number = Integer.parseInt(parts[0]);
                if(parts[1].equals("red") && red < number)
                    continue checkGames;
                if(parts[1].equals("green") && green < number)
                    continue checkGames;
                if(parts[1].equals("blue") && blue < number)
                    continue checkGames;
            }
            result += id;
        }
        return "" + result;
    }

    @Override
    public String answerTwo(List<String> input) {
        long result = 0;
        for (String s : input){
            red = 0;
            green = 0;
            blue = 0;
            String[] separateGame = s.split(":");
            String[] uniqueCubes = separateGame[1].split("(; )|(, )");
            for(String st : uniqueCubes){
                String[] parts = st.trim().split(" ");
                int number = Integer.parseInt(parts[0]);
                if(parts[1].equals("red") && red < number)
                    red = number;
                if(parts[1].equals("green") && green < number)
                    green = number;
                if(parts[1].equals("blue") && blue < number)
                    blue = number;
            }
            result += (long)red * green * blue;
        }
        return "" + result;
    }
}
