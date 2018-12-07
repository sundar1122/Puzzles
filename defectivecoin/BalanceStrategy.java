import java.util.List;

public class BalanceStrategy {
	List<Balance> balanceList;
	List<Outcome> outcomeList;
	
	public BalanceStrategy(List<Balance> balanceList, List<Outcome> outcomeList) {
		this.balanceList = balanceList;
		this.outcomeList = outcomeList;
	}
	
	/**
	 * Given a balance result such as LLR, it gives the strategy.
	 * @param result
	 * @return
	 */
	public String findDefective(String result) {
		Outcome r = new Outcome(result);
		for (int i = 0; i < outcomeList.size(); i++) {
			if (r.equals(outcomeList.get(i))) {
				
				return "Coin is " + i + ", and it is " + heavyString(i, r);
			}
		}
		return "No defective coin.";
	}
	
	public String heavyString(int coin, Outcome o) {
		for (int i = 0; i < balanceList.size(); i++) {
			char c = o.getOutcome(i);
			if (balanceList.get(i).containsLeft(coin)) {
				return (c == 'L') ? "Heavy." : "Light.";
			}
			else if (balanceList.get(i).containsRight(coin)) {
				return (c == 'R') ? "Heavy." : "Light.";
			}
		}
		return "Non-Defective.";
	}
	
	public Balance getBalance(int i) {
		return balanceList.get(i);
	}
	
	public List<Balance> getBalanceList() {
		return this.balanceList;
	}
	
	public String toString() {
		StringBuffer b = new StringBuffer();
		b.append("Scales: \n");
		for (Balance balance : balanceList) {
			b.append(balance.toString() + "\n");
		}
		
		b.append("List of Outcomes: ");
		b.append(outcomeList + "\n");
		return b.toString();
	}
	
}
