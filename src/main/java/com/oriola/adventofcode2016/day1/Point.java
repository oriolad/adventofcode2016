package com.oriola.adventofcode2016.day1;

import java.util.Objects;

/**
 * Created by Oriola on 16-12-03.
 */
public class Point {

    int x;
    int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        if (y != point.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
       return Objects.hash(x,y);
    }
}
