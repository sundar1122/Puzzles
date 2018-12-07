import java.util.ArrayList;
import java.util.List;

/**
 * This is a balance class
 * @author sarunapu
 * The balance class contains a balance with two scales with list of objects in the left and right scale.
 * 
 */
public class Balance {
	private List<Integer> rightScale;
	private List<Integer> leftScale;
	
	public Balance() {
		rightScale = new ArrayList<>();
		leftScale = new ArrayList<>();
	}
	
	public void addRight(int coin) {
		rightScale.add(coin);
	}
	
	public boolean containsRight(int coin) {
		return rightScale.contains(coin);
	}
	
	public boolean containsLeft(int coin) {
		return leftScale.contains(coin);
	}
	
	public void addLeft(int coin) {
		leftScale.add(coin);
	}
	
	public String toString() {
		StringBuffer b = new StringBuffer();
		b.append("Left Scale: ");
		b.append(leftScale);
		b.append(", Right Scale: ");
		b.append(rightScale);
		return b.toString();
	}
	
	public boolean isBalanced() {
	//	System.out.println(this);
		return (rightScale.size() == leftScale.size());
	}
}
