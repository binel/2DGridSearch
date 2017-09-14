
public class Coord {
	public int x; 
	public int y;
	
	public Coord(int x, int y) {
		this.x = x;
		this.y = y; 
	}
	
	@Override
	public int hashCode() {
		return Integer.hashCode(x) + Integer.hashCode(y); 
	}
	
	@Override 
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(((Coord) obj).x == this.x && ((Coord) obj).y == this.y) {
			return true; 
		}
		return false; 
	}
}
