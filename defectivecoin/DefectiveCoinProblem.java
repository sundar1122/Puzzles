import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DefectiveCoinProblem {
	private int nCoin;
	private int nAttempt;
	List <Outcome> outcomeList;
	List <BalanceStrategy> strategyList;
	public DefectiveCoinProblem(int nCoin, int nAttempt) {
		this.nCoin = nCoin;
		this.nAttempt = nAttempt;
		this.init();
		
	}
	
	private void init() {
		this.strategyList = new ArrayList<>();
		this.outcomeList = Outcome.generateOutcomes(this.nAttempt, this.nCoin);
		this.generateStrategies();
	}
	
	private void generateStrategies() {
		CombineIterator<Outcome> iter = new CombineIterator<>(outcomeList, nCoin);
		while (iter.hasNext()) {
			List<Outcome> outList = iter.next();
			List<Integer> intList = new ArrayList<>();
			intList.add(0);
			intList.add(1);

			DictionaryIterator<Integer> dIter = new DictionaryIterator<>(intList, nCoin);
			while (dIter.hasNext()) {
				List<Integer> choiceList = dIter.next();
				List<Outcome> newOutList = getChoice(outList, choiceList);
				BalanceStrategy s = Outcome.getBalanceStrategy(newOutList, nAttempt);
				if (s != null) {
					this.strategyList.add(s);
				}
			}
		}
	}
	
	private List<Outcome> getChoice(List<Outcome> oList, List<Integer> iList) {
		List<Outcome> newOutcomeList = new ArrayList<>();
		for(int i = 0; i < oList.size(); i++) {
			Outcome o = oList.get(i);
			int choice = iList.get(i);
			if (choice == 1) o = o.getMirror();
			newOutcomeList.add(o);
		}
		return newOutcomeList;
	}
	
	
	public static void main(String [] args) {
		DefectiveCoinProblem d = new DefectiveCoinProblem(12, 3);
		
		List<BalanceStrategy> sList = d.strategyList;
		
	//	for (BalanceStrategy bs : sList) System.out.println(bs);
		// System.out.println(sList.size());
		if (sList.size() == 0) return;
		// System.out.println(b);
		
		Scanner s = new Scanner(System.in);
		String string;
		String ss;
		while (true) {
			int ran = (int)( Math.random() * sList.size());
			BalanceStrategy b = sList.get(ran);
			StringBuffer stringBuffer = new StringBuffer();
			List<Balance> balanceList = b.getBalanceList();
			for (Balance balance : balanceList) {
				System.out.println(balance);
				System.out.println("Enter Balance position: (L/E/R), Q to Quit: ");
	
				while (true) {
					ss = s.nextLine();
					if (ss.equals("L")) {
						stringBuffer.append(ss);
						break;
					}
					if (ss.equals("E")) {
						stringBuffer.append(ss);
						break;
					}
					if (ss.equals("R")) {
						stringBuffer.append(ss);
						break;
					}
					if (ss.equals("Q")) return;
				}
			}
			string = stringBuffer.toString();
			String str = b.findDefective(string);
			System.out.println(str);
			System.out.println("\n\n");
		
		}
	}

}
