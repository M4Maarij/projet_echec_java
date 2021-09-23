
package echec;

public class Position {
    private int x;
    private int y;
    static final int min = 1; // 1............8
    static final int max = 8;  // 1............8

    public Position(int x,int y) {
        this.x=x;
        this.y=y;
    }

    @Override
    public boolean equals(Object obj) {
        Position temp =(Position)obj;
        return this.x == temp.x && this.y == temp.y;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int var1) {
        this.x = var1;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int var1) {
        this.y = var1;
    }

    // array of two integers
    public int[] getPosition(){
        return new int[]{getX(),getY()};
    }

    public void setPosition(int x,int y) {
        setX(x);
        setY(y);
    }

    public String toString() {
        return "[x=" + this.x + " ,y=" + this.y + "]";
    }


      // Cette méthode fait une copie légale réelle de la classe position.
    // en utilisant  les coordonnées actuelles (getX() et getY())
    @Override
    protected Position clone() {
        return new Position(this.getX(), this.getY());
    }
    protected boolean inBounds() {
        return  x >= 1 &&  x <= 8 &&  y >= 1 &&  y <= 8;
    }
}
