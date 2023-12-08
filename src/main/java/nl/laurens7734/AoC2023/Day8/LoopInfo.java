package nl.laurens7734.AoC2023.Day8;

import java.util.ArrayList;
import java.util.List;

public class LoopInfo {
    private final List<Long> startingList;
    private int expansions;
    private final long size;

    public LoopInfo(long size, List<Long> startingList){
        this.size = size;
        this.startingList = startingList;
        expansions = 0;
    }

    public long getExpandedSize() {
        return size*expansions;
    }

    public List<Long> getNext(){
        long additionalSize = size*expansions;
        expansions++;
        return startingList.stream().map(l -> l + additionalSize).toList();
    }

    public long getLowest(){
        return startingList.stream().min(Long::compare).get();
    }
}
