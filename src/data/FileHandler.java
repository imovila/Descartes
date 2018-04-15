package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileHandler {
	private static String path = "src/data/personal.ser";
	
	public static String getPath() {
		return path;
	}

	public static void setPath(String path) {
		setPath(path);
	}

	public static File getFile() {
		return new File(getPath());
	}


	public static<T> boolean WriteToBinFile(ArrayList<T> myList) throws IOException {
		try {
			FileOutputStream outFile = new FileOutputStream(path);
			ObjectOutputStream outStream = new ObjectOutputStream(outFile);
			outStream.writeObject(myList);
			outStream.close();
		}
		catch(Exception e) {
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public static<T> ArrayList<T> ReadFromBinFile() throws IOException, ClassNotFoundException {
		FileInputStream inFile = new FileInputStream(path);
		ObjectInputStream inStream = new ObjectInputStream(inFile);
		
		ArrayList<T> myArray =  new ArrayList<T>();
		
		for(T t : (ArrayList<T>)inStream.readObject())
		{
			myArray.add(t);
		}
		inStream.close();
		return myArray;	
	}
}
