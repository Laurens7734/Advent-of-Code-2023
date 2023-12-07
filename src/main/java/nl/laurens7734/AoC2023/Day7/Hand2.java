package nl.laurens7734.AoC2023.Day7;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Hand2 implements Comparable<Hand2>{
    private String cards;
    private int type;
    private int bid;

    public Hand2(String cards, int bid){
        this.cards = cards;
        this.bid = bid;
        calculateType();
    }

    private void calculateType(){
        HashMap<Character,Integer> chars = new HashMap<>();
        for(char c : cards.toCharArray()){
            if(chars.containsKey(c))
                chars.replace(c,chars.get(c)+1);
            else
                chars.put(c,1);
        }
        int jokers = 0;
        if(chars.containsKey('J')){
            jokers = chars.get('J');
            chars.remove('J');
            if(jokers == 5){
                type = 7;
                return;
            }
        }
        List<Integer> nums = chars.values().stream().sorted(Comparator.reverseOrder()).toList();
        int most = nums.get(0)+jokers;
        if(most > 3){
            type = most+2;
            return;
        }
        if(most == 3){
            if(nums.get(1) == 2)
                type = 5;
            else
                type = 4;
            return;
        }
        if(most == 2){
            if(nums.get(1) == 2)
                type = 3;
            else
                type = 2;
            return;
        }
        type = 1;
    }

    public String getCards() {
        return cards;
    }

    public int getType() {
        return type;
    }

    public int getBid() {
        return bid;
    }

    public int compareTo(Hand2 other){
        if(type < other.type)
            return -1;
        if(type > other.type)
            return 1;
        char[] myHand = cards.toCharArray();
        char[] otherHand = other.cards.toCharArray();
        for(int i = 0; i < myHand.length; i++){
            int myCard = cardAsNumber(myHand[i]);
            int otherCard = cardAsNumber(otherHand[i]);
            if(myCard > otherCard)
                return 1;
            if(otherCard > myCard)
                return -1;
        }
        return 0;
    }

    private int cardAsNumber(char a){
        return switch (a) {
            case 'A' -> 14;
            case 'K' -> 13;
            case 'Q' -> 12;
            case 'J' -> 1;
            case 'T' -> 10;
            default -> (a - '0');
        };
    }
}
