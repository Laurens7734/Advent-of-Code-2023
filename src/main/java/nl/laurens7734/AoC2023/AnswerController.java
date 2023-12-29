package nl.laurens7734.AoC2023;

import nl.laurens7734.AoC2023.Day1.Day1;
import nl.laurens7734.AoC2023.Day10.Day10;
import nl.laurens7734.AoC2023.Day11.Day11;
import nl.laurens7734.AoC2023.Day12.Day12;
import nl.laurens7734.AoC2023.Day13.Day13;
import nl.laurens7734.AoC2023.Day14.Day14;
import nl.laurens7734.AoC2023.Day15.Day15;
import nl.laurens7734.AoC2023.Day2.Day2;
import nl.laurens7734.AoC2023.Day3.Day3;
import nl.laurens7734.AoC2023.Day4.Day4;
import nl.laurens7734.AoC2023.Day5.Day5;
import nl.laurens7734.AoC2023.Day6.Day6;
import nl.laurens7734.AoC2023.Day7.Day7;
import nl.laurens7734.AoC2023.Day8.Day8;
import nl.laurens7734.AoC2023.Day9.Day9;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
public class AnswerController {
    @PostMapping("answer")
    public String getAnswer(@RequestBody AoCRequestBody request){
        Day d = getDay(request.getDay());
        if(d == null)
            return "Day not supported";

        if(request.getPuzzle() == 1){
            return d.answerOne(request.getInput());
        }
        if(request.getPuzzle() == 2){
            return d.answerTwo(request.getInput());
        }
        return "no valid puzzle";
    }

    @GetMapping("supporteddays")
    public List<Integer> getSupportedDays(){
        List<Integer> answer = new ArrayList<>();
        int i = 1;
        while(getDay(i) != null){
            answer.add(i);
            i++;
        }
        return answer;
    }

    private Day getDay(int daynum){
        return switch (daynum) {
            case 1 -> new Day1();
            case 2 -> new Day2();
            case 3 -> new Day3();
            case 4 -> new Day4();
            case 5 -> new Day5();
            case 6 -> new Day6();
            case 7 -> new Day7();
            case 8 -> new Day8();
            case 9 -> new Day9();
            case 10 -> new Day10();
            case 11 -> new Day11();
            case 12 -> new Day12();
            case 13 -> new Day13();
            case 14 -> new Day14();
            case 15 -> new Day15();
            //case 16 -> new Day16();
            //case 17 -> new Day17();
            //case 18 -> new Day18();
            //case 19 -> new Day19();
            //case 20 -> new Day20();
            //case 21 -> new Day21();
            //case 22 -> new Day22();
            //case 23 -> new Day23();
            //case 24 -> new Day24();
            //case 25 -> new Day25();
            default -> null;
        };
    }
}
