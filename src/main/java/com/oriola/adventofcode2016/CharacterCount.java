package com.oriola.adventofcode2016;

/**
 * Created by Oriola on 16-12-06.
 */
public class CharacterCount implements Comparable<CharacterCount>{

    private Character character;
    private Integer count;

    public CharacterCount(Character character){
        this.character = character;
        count = 1;
    }

    public void increment(){
        count++;
    }

    public Character getCharacter(){
        return character;
    }

    public Integer getCount(){
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        if(o instanceof CharacterCount) {
            CharacterCount that = (CharacterCount) o;

            if (!this.character.equals(that.character)) return false;
            if (!this.count.equals(that.count)) return true;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return character.hashCode();
    }

    @Override
    public int compareTo(CharacterCount other) {
        if(other.count > this.count){
            return -1; // negative = this is less than other
        } else if(other.count == this.count){
            if(other.character > this.character){
                return 1; // positive = this is greater than other
            }else if(other.character < this.character){
                return -1; // negative = this is less than other
            }
            return 0; // 0 = same
        }else{ // positive = this is greater than other
            return 1;
        }

    }


}
