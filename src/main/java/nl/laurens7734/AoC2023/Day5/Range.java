package nl.laurens7734.AoC2023.Day5;

import java.util.ArrayList;
import java.util.List;

public class Range {
    private final long sourceStart;
    private final long destinationStart;
    private final long amount;

    public Range(long source, long destination, long size){
        sourceStart = source;
        destinationStart = destination;
        amount = size;
    }

    public boolean inRange(long num){
        if(num < sourceStart)
            return false;
        if(num > getEnd())
            return false;
        return true;
    }

    public boolean hasOverlap(Range r){
        if(r.getEnd() < sourceStart)
            return false;
        if(r.sourceStart > getEnd())
            return false;
        return true;
    }

    public List<Range> remaining(Range start){
        List<Range> remainder = new ArrayList<>();
        if(!hasOverlap(start)){
            remainder.add(start);
            return remainder;
        }
        if(start.sourceStart < sourceStart)
            remainder.add(new Range(start.sourceStart,0, sourceStart-start.sourceStart));
        if(start.getEnd() > getEnd())
            remainder.add(new Range(getEnd()+1,0, start.getEnd()-getEnd()));
        return remainder;
    }

    public long getDestination(long source){
        if(!inRange(source))
            return -1;
        return destinationStart+(source-sourceStart);
    }

    public Range getDestination(Range source){
        if(!hasOverlap(source))
            return null;
        long newStart = 0;
        long newLength = 0;

        if(source.getEnd() > getEnd())
            newLength = getEnd();
        else
            newLength = source.getEnd();

        if(source.sourceStart > sourceStart){
            newStart = getDestination(source.sourceStart);
            newLength -= source.sourceStart;
        }
        else{
            newStart = destinationStart;
            newLength -= sourceStart;
        }
        return new Range(newStart,0,newLength);
    }

    private long getEnd(){
        return (sourceStart+amount)-1;
    }

    public long getStart(){
        return sourceStart;
    }
}
