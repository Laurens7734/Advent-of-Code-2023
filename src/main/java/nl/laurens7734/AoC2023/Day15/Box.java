package nl.laurens7734.AoC2023.Day15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Box {
    private int number;
    private List<Lens> lenses;
    private HashMap<String,Lens> findLens;

    public Box(int number){
        this.number = number;
        lenses = new ArrayList<>();
        findLens = new HashMap<>();
    }

    public void addLens(Lens newLens){
        if(findLens.containsKey(newLens.getLabel())){
            findLens.get(newLens.getLabel()).setFocalLength(newLens.getFocalLength());
        }else{
            lenses.add(newLens);
            findLens.put(newLens.getLabel(),newLens);
        }
    }

    public void removeLens(String label){
        if(!findLens.containsKey(label))
            return;
        for(int i = 0; i < lenses.size();i++){
            if(lenses.get(i).getLabel().equals(label)){
                lenses.remove(i);
                break;
            }
        }
        findLens.remove(label);
    }

    public long calculateFocalPower(){
        long sum = 0;
        for(int i = 0; i < lenses.size();i++){
            sum += (long) (number + 1) *(i+1)*lenses.get(i).getFocalLength();
        }
        return sum;
    }
}
