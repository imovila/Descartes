package bus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class DataCollection<T> implements Serializable{
	
	private static final long serialVersionUID = 2330762566328802692L;

	private ArrayList<T> myList;

	public ArrayList<T> getMyList() {
		return myList;
	}

	public void setMyList(ArrayList<T> myList) {
		this.myList = myList;
	}
	
	public DataCollection() {
		this.myList = new ArrayList<>();
	}

	public DataCollection(ArrayList<T> myList) {
		super();
		this.myList = myList;
	}
	
	public void add(T obj)
	{
		this.myList.add(obj);
	}
	
	public boolean Remove(String value) {
		Object obj = SearchByNAS(value);
		if (obj != null){ 
			this.myList.remove(obj);
			return true;
		}
		else
			return false;
	}
	
	public void add(ArrayList<T> myList) {
		this.myList = myList;
	}
	
	public void remove(T obj)
	{
		this.myList.remove(obj);
	}
	
	public int getSize()
	{
		return this.myList.size();
	}
		
	public void SortByNas() {
		Collections.sort(this.myList, new SortByNAS<T>());
	}
	
	public void erase(){
		myList.clear();
	}
	
	public int GetNumberOfList() {
		return this.myList.size();
	}
	
	public ArrayList<T>  GetList(){
		return this.myList;
	}
	
	@SuppressWarnings("unchecked")
	public T SearchByNAS(String value) {
		for (Object obj : myList) {
			if(((Member) obj).getNas().trim().equals(value.trim()))
				return (T) obj;
		}
		return null;
	}
	
	public int GetIndexByNAS(String value) {
		for (int i = 0; i < myList.size(); i++) {
			if(((Member) myList.get(i)).getNas().equals(value.trim())) {
				return i;
			}
		}
		return -1;
	}
	
	public Object GetObjectByIndex(int idx) {
		return myList.get(idx);
	}
	
	public boolean UpdateByNAS(int idx, T obj) {
		try{
			myList.set(idx, obj);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("-------View List-------\n");
		for (T obj : myList) {
			sb.append("\n" + obj);
		}
		return sb.toString();
	}
}