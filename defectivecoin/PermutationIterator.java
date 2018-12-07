import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class PermutationIterator <E> implements Iterator<List<E>> {
	List<E> objList = new ArrayList<>();
	List<E> nextList = null;

	private int iVal;
	private int [] cVal;
	
	public PermutationIterator(List<E> inList) {
		this.objList = inList;
		this.cVal = new int[inList.size()];
		for (int i = 0; i < inList.size(); i++) cVal[i] = 0;
		this.iVal = 0;
	}
	

	
	private List<E> clonedList() {
		List<E> newList = new ArrayList<>();
		for (E obj : this.objList) {
			newList.add(obj);
		}
		return newList;
	}

	public List<E> next() {
		List<E> retVal = null;
		if (iVal == 0) {
			iVal ++;
			return clonedList();
		}
		while (iVal < objList.size()) {
			if (this.cVal[iVal] < iVal) {
				if (iVal % 2 == 0) {
					Collections.swap(objList, 0, iVal);
				}
				else {
					Collections.swap(objList, cVal[iVal], iVal);
				}
				cVal[iVal] += 1;
				iVal = 1;
				retVal = clonedList();
				break;
			}
			else {
				cVal[iVal] = 0;
				iVal ++;
			}
		}
		return retVal;
	}
	
	public boolean hasNext() {
		return (iVal < this.objList.size());
	}
	
	public static void main(String [] args) {
		List<String> strList = new ArrayList<>();
		strList.add("A");
		strList.add("B");
		strList.add("C");
		
		Iterator<List<String>> iter = new PermutationIterator<String>(strList);
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
	}
}
