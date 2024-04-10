package model;

public class Point {
    public int row, col;

    public Point(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public boolean equals(Point p){
        return this.row == p.row && this.col==p.col;
    }

    public String toString(){
        return "("+String.valueOf(this.row)+" , " + String.valueOf(this.col)+")";
    }
}
