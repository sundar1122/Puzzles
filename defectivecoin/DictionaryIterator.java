import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author sarunapu
 * This class providers an iterator to generate all the possible dictionary values of a given list.  The
 * size of the dictionary is given by length.
 * Eg. If the values are "ABCD" and the size is
 * @param <E>
 */
public class DictionaryIterator <E> implements Iterator<List<E>> {
	List<E> objList = new ArrayList<>();
	List<E> nextList = null;
	private int length;

	private int iVal;
	private int [] cVal;
	
	public DictionaryIterator(List<E> inList, int length) {
		this.objList = inList;
		this.cVal = new int[length];
		for (int i = 0; i < length; i++) cVal[i] = 0;
		this.iVal = 0;
		this.length = length;
	}
	

	
	private List<E> clonedList() {
		List<E> newList = new ArrayList<>();
		for (int i = 0; i < length; i++) {
			newList.add(objList.get(cVal[i]));
		}
		return newList;
	}

	public List<E> next() {
		if (iVal == 0) {
			iVal ++;
			return clonedList();
		}
		
		for (int i = length - 1; i >= 0 ; i--) {
			if (cVal[i] == objList.size()-1) {
				cVal[i] = 0;
			}
			else {
				cVal[i] ++;
				break;
			}
		}
		return clonedList();
	}
	
	public boolean hasNext() {
		for (int i = 0; i < length; i++) {
			if (cVal[i] < objList.size() - 1) return true;
		}
		return false;
	}
	
	public static void main(String [] args) {
		List<String> strList = new ArrayList<>();
		strList.add("A");
		strList.add("B");
		strList.add("C");
		strList.add("D");
		
		Iterator<List<String>> iter = new DictionaryIterator<String>(strList, 3);
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
	}
}
