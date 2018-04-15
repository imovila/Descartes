package bus;

import java.util.Comparator;

public class SortByNAS<T> implements Comparator<T> {

	@Override
	public int compare(T o1, T o2) {
		if (((Member)o1).getNas().compareTo(((Member)o2).getNas()) < 0)
			return -1;
		else if (((Member)o1).getNas().compareTo(((Member)o1).getNas()) > 0)
			return 1;
		return 0;
	}

}
