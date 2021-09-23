package Echec;


public class Position {

	private int x;
	private int y;
	static final int min=1; // 1............
	static final int max=8; // ............8

	
	
	public Position(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		Position temp =(Position)obj;
		if (x==temp.x && y==temp.y)
			return true;
		return false;
	}



	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int[] getPosition()
	{
		int[] temp= {getX(),getY()};
		return temp;
	}
	
	public void setPosition(int x,int y)
	{
		setX(x);
		setY(y);
	}



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ("[x="+x+" ,y="+y+"]");
	}


	
	// Cette méthode fait une copie légale réelle de la classe position.
    // en utilisant  les coordonnées actuelles (getX() et getY())
	
	@Override
	protected Position clone() {
		// TODO Auto-generated method stub
		Position temp =new Position(this.getX(),this.getY());
		
		
		return temp;
	}
	
	
	
	protected boolean inBounds()
	{		
		if(x>=min && x<=max &&  y>=min && y<=max)
			return true;
		return false;
	}
	
	
	
	
	
}
