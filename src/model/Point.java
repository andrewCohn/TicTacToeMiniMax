package model;

public class Point {
    public int row, col;

    public Point(int row, int col) {
        this.row = row;
        this.col = col;
    }
    // this is just for testing- makes assertEquals work correctly
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Point p = (Point) obj;
        return this.row == p.row && this.col == p.col;
    }

    public String toString(){
        return "("+String.valueOf(this.row)+" , " + String.valueOf(this.col)+")";
    }
}
