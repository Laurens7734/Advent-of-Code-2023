package nl.laurens7734.AoC2023;

import nl.laurens7734.AoC2023.Day1.Day1;
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
            default -> null;
        };
    }
}
