package cecs277momentohw;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * @author Naveene Raya Carlos Alvarenga
 *
 */
public class PersonCaretaker {

	ObjectOutputStream outputStream;
	ObjectInputStream inputStream;
	
	/**
	 * default constructor for PersonCaretaker
	 * @throws IOException 
	 */
	public PersonCaretaker(String filePath) throws IOException {
		
		this.outputStream = new ObjectOutputStream( new FileOutputStream(filePath));
		this.inputStream = new ObjectInputStream(new FileInputStream(filePath));
	}


	/**
	 * method to write out a Memento instance
	 * @throws IOException 
	 */
    public void addMemento(PersonMemento pm) throws IOException {
    	outputStream.writeObject(pm);   //writes in binary, unreadable by human eyes
    }

    /**
     * 
     * @return an instance of PersonMementothat has the lowest weight
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    public PersonMemento getMemento() throws ClassNotFoundException, IOException {
		boolean eof = false;

		PersonMemento skinniest = (PersonMemento)inputStream.readObject();
		while(!eof) {
			try {
			PersonMemento tempP = (PersonMemento)inputStream.readObject();
			if(skinniest.getSavedPerson().getWeightPounds() > tempP.getSavedPerson().getWeightPounds())
				skinniest = tempP;
			} catch(EOFException e) {
				eof = true;
			}
		}
		
		return skinniest;
	}
 
    
    /**
     * 
     * @param i
     * @return first instance of PersonMemento that it finds in the file that has a 
     * weight that matches the input weight
     */
    public PersonMemento getMemento(int i) {
		// TODO Auto-generated method stub
		return null;
	}
    
    
    /**
     * closes the output stream objects
     * @throws IOException
     */
    public void closeWriter() throws IOException {
    	this.outputStream.close();
    	this.inputStream.close();
    }

}
