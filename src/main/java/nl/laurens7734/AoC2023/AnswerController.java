package nl.laurens7734.AoC2023;

import nl.laurens7734.AoC2023.Day1.Day1;
import nl.laurens7734.AoC2023.Day2.Day2;
import nl.laurens7734.AoC2023.Day3.Day3;
import nl.laurens7734.AoC2023.Day4.Day4;
import nl.laurens7734.AoC2023.Day5.Day5;
import nl.laurens7734.AoC2023.Day6.Day6;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(maxAge = 3600)
public class AnswerController {
    @GetMapping("answer")
    public String getAwnser(@RequestBody AoCRequestBody request){
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

    private Day getDay(int daynum){
        return switch (daynum) {
            case 1 -> new Day1();
            case 2 -> new Day2();
            case 3 -> new Day3();
            case 4 -> new Day4();
            case 5 -> new Day5();
            case 6 -> new Day6();
            default -> null;
        };
    }
}
