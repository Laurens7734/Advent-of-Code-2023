package nl.laurens7734.AoC2023.Day8;

import nl.laurens7734.AoC2023.Day;
import org.javatuples.Pair;

import java.util.*;

public class Day8 implements Day {
    @Override
    public String answerOne(List<String> input) {
        String instructions = input.get(0);
        HashMap<String, Node> nodes = generateNodes(input.subList(2,input.size()));
        for(Node n : nodes.values()){
            n.setLeft(nodes.get(n.getLeftName()));
            n.setRight(nodes.get(n.getRightName()));
        }
        long stepCount = 0;
        Node start = nodes.get("AAA");
        while(!start.getName().equals("ZZZ")){
            char instruction = instructions.charAt((int)stepCount%instructions.length());
            if(instruction == 'L')
                start = start.getLeft();
            else
                start = start.getRight();
            stepCount++;
        }
        return "" + stepCount;
    }

    @Override
    public String answerTwo(List<String> input) {
        String instructions = input.get(0);
        HashMap<String, Node> nodes = generateNodes(input.subList(2,input.size()));
        for(Node n : nodes.values()){
            n.setLeft(nodes.get(n.getLeftName()));
            n.setRight(nodes.get(n.getRightName()));
        }

        List<Node> currentNodes = nodes.values().stream().filter(n -> n.getName().endsWith("A")).toList();
        List<LoopInfo> loops = new ArrayList<>();
        for(Node n : currentNodes){
            loops.add(getLoopInfo(n,instructions));
        }
        while(loops.size() > 1){
            loops = mergeLoops(loops);
        }
        return "" + loops.get(0).getLowest();
    }

    private HashMap<String,Node> generateNodes(List<String> input){
        HashMap<String,Node> result = new HashMap<>();
        for(String s : input){
            String[] nodes = s.substring(0,s.length()-1).split((" (= \\()|(, )"));
            result.put(nodes[0],new Node(nodes[0],nodes[1],nodes[2]));
        }
        return result;
    }

    private LoopInfo getLoopInfo(Node n, String instructions){
        long steps = 0;
        HashMap<String,Long> visited = new HashMap<>();
        List<Long> exitLocations = new ArrayList<>();
        Node current = n;
        while(true){
            long instructionIndex = steps%instructions.length();
            String id = current.getName()+instructionIndex;
            if(visited.containsKey(id)){
                return new LoopInfo(steps-visited.get(id),exitLocations.stream().filter(num -> num>visited.get(id)).toList());
            }
            visited.put(id,steps);
            if(current.getName().endsWith("Z"))
                exitLocations.add(steps);
            if(instructions.charAt((int)instructionIndex) == 'L')
                current = current.getLeft();
            else
                current = current.getRight();
            steps++;
        }
    }

    private List<LoopInfo> mergeLoops(List<LoopInfo> loops){
        List<LoopInfo> result = new ArrayList<>();
        for(int i = 0; i < loops.size(); i+=2){
            if(i+1 == loops.size()){
                result.add(loops.get(i));
                break;
            }
            LoopInfo loop1 = loops.get(i);
            LoopInfo loop2 = loops.get(i+1);
            List<Long> loop1Numbers = loop1.getNext();
            List<Long> loop2Numbers = loop2.getNext();
            while(loop1Numbers.stream().noneMatch(loop2Numbers::contains)){
                long mainMax = loop1Numbers.stream().max(Long::compare).get();
                long loopMax = loop2Numbers.stream().max(Long::compare).get();
                if(mainMax > loopMax)
                    loop2Numbers = loop2.getNext();
                else
                    loop1Numbers = loop1.getNext();
            }
            List<Long> newMatches = loop1Numbers.stream().filter(loop2Numbers::contains).toList();
            result.add(new LoopInfo(loop1.getExpandedSize(),newMatches));
        }
        return result;
    }
}
