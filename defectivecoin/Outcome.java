import java.util.ArrayList;
import java.util.List;

public class Outcome implements Comparable<Outcome> {
	private int length;
	private char [] outcome; // L: left, E: equal, R: right
	public static final char [] RESULT =
		{'E', 'L', 'R'};
	
	public Outcome(String s) {
		this.length = s.length();
		outcome = new char [length];
		for (int i = 0; i < length; i++) {
			outcome[i] = s.charAt(i);
		}
	}
	
	private char mirror(char c) {
		if (c == 'R') return 'L';
		if (c == 'L') return 'R';
		return c;
	}
	
	public boolean isMirror(Outcome o) {
		if (o.length != this.length) return false;
		for (int i = 0; i < length; i++) {
			if (this.outcome[i] != mirror(o.outcome[i])) return false;
		}
		return true;
	}
	
	public char getOutcome(int i) {
		return outcome[i];
	}
	
	public Outcome getMirror() {
		Outcome o = new Outcome(this.toString());
		for (int i = 0; i < o.length; i++) {
			o.outcome[i] = mirror(o.outcome[i]);
		}
		return o;
	}
	
	@Override
	public boolean equals(Object o2) {
		if (o2 == null) return false;
		boolean retval = false;
		if (o2 instanceof Outcome) {
			Outcome o = (Outcome) o2;
			retval = (this.hashCode() == o.hashCode());
		}
		else retval = false;
		
		return retval;
	}
	
	public boolean isAllE() {
		for (int i = 0; i < length; i++) {
			if (this.outcome[i] != 'E') return false;
		}
		return true;
	}
	
	@Override
    public int hashCode() {
    	String a = this.toString();
    	String revStr = this.getMirror().toString();
    	if (a.compareTo(revStr) > 1) a = revStr;
    	return a.hashCode();
    }
	
	
	public String toString() {
		StringBuffer b = new StringBuffer();
		for (int i = 0; i < length; i++) {
			b.append(outcome[i]);
		}
		return b.toString();
	}

	@Override
	public int compareTo(Outcome o) {
		if (this.equals(o)) return 0;
		if (this.length < o.length) return -1;
		if (this.length > o.length) return 1;
		for (int i = 0; i < this.length; i++) {
			if (this.outcome[i] < o.outcome[i]) return -1;
			else if (this.outcome[i] > o.outcome[i]) return 1;
		}
		// TODO Auto-generated method stub
		return 0;
	}
	
	private static String concatList(List<String> sList) {
		StringBuffer b = new StringBuffer();
		for (String s : sList) b.append(s);
		return b.toString();
	}
	
	public static List<Outcome> generateOutcomes(int nAttempt, int nCoin) {
		List<Outcome> oList = new ArrayList<>();
		List<String> resultList = new ArrayList<>();
		for (char c : Outcome.RESULT) resultList.add(new String("" + c));
		DictionaryIterator<String> c = new DictionaryIterator<>(resultList, nAttempt);
		while (c.hasNext()) {
			List<String> s = c.next();
			String concat = concatList(s);
			Outcome o = new Outcome(concat);
			if (nCoin <= 12 && o.isAllE()) continue;
			if (oList.contains(o)) continue;
			oList.add(o);
			// System.out.println("Added:" + o);
			
		}
		return oList;
	}
	
	/**
	 * In any attempt column, the number of Ls and Rs should be the same.
	 * For each attempt, store the list of balls on the left scale and the list of balls on the right scale.
	 * @param oList
	 * @return
	 */
	public static BalanceStrategy getBalanceStrategy(List<Outcome> oList, int attempts) {
		List<Balance> balanceList = new ArrayList<>();
		for (int i = 0; i < attempts; i++) {
			Balance b = new Balance();
			for (int j = 0; j < oList.size(); j++) {
				Outcome o = oList.get(j);
				char c = o.outcome[i];
				if (c == 'L') b.addLeft(j);
				else if (c == 'R') b.addRight(j);
			}
			if (!b.isBalanced()) return null;
			balanceList.add(b);
		}
		return new BalanceStrategy(balanceList, oList);
	}
	
}
