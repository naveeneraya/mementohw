package cecs277momentohw;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;

import cecs277momentohw.Person.HairColor;

/**
 * @author Naveene Raya Carlos Alvarenga
 * @date 10/29/2019
 */
public class MementoRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int heightFeet;
		int heightInch;
		int weightLbs;
		String lName;
		String fName;
		HairColor color = HairColor.BALD;
		PersonCaretaker pCare = null;


		Scanner in = new Scanner(System.in);     // takes in user response
		JFileChooser chooser = new JFileChooser();

		boolean foundOutFile = false;			//Flag that we don't have an output file

		do {      //standard file process
			chooser.setDialogTitle("Please choose a file to store your PersonMemento objects: ");
			if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
				try {
					pCare = new PersonCaretaker(chooser.getSelectedFile().getAbsolutePath());
					foundOutFile = true;
				}
				catch (FileNotFoundException ex){
					System.out.println("Error. Incorrect file path.");
					foundOutFile = false;
				} catch (IOException e) {
					System.out.println("Error. Incorrect file path.");
					foundOutFile = false;
				}
			}

		} while (!foundOutFile);

		System.out.println("prompting you for persons: ");   //requesting name
		System.out.print("Enter the person's last name: ");
		lName = in.nextLine();

		System.out.print("enter the person's first name: ");
		fName = in.nextLine();

		Person changePerson = new Person( lName,fName);   // creating new person, is re-used to create multiple mementos
		
		do {   //start of loop for Person characteristics
			boolean flag;
			do {
				flag = true;
				for (int i = 0; i < HairColor.values().length; i++)   //printing hair color menu
					System.out.println(i + "\t" + HairColor.getColor(i));

				System.out.print("Please enter the # corresponding to the hair color: ");

				try {
					color = HairColor.getColor(Integer.parseInt(in.nextLine()));   //attempt to parse number choice 
					flag = true;
				}
				catch(NumberFormatException ne) {    //in case its not an integer
					System.out.println("Error. Not an integer.");
					flag = false;
				}
				catch(IllegalArgumentException ie) {    //in case number isn't between 0-7
					System.out.println(ie.getMessage());
					flag = false;
				}
			} while(flag == false);
			
			changePerson.setHairColor(color);   // sets hair color

			System.out.println("You entered color:\t" + color.name());
			
			System.out.print("Person's height in feet: ");
			heightFeet = Integer.parseInt(in.nextLine());
			System.out.print("And the inches?  For instance, 5'10\" would be 10: ");
			heightInch = Integer.parseInt(in.nextLine());
			changePerson.setHeight(heightFeet, heightInch);
			
			System.out.print("Their weight in pounds: ");
			weightLbs = Integer.parseInt(in.nextLine());   //throws an illegal argument
			changePerson.setWeight(weightLbs);

			
			try {
				pCare.addMemento(changePerson.save());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}   // adds instance of PersonMemento (using Person save() function) to file
			
			System.out.println(changePerson);
			System.out.print("Are we done here (Y/N)?:  ");

		} while (!in.nextLine().equalsIgnoreCase("y"));
		
		try {
			System.out.print(pCare.getMemento());
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		/** cleanup */
		in.close();
		try {
			pCare.closeWriter();
		} catch (IOException e) {
			System.out.println("ERROR. CLOSE WRITER");
		}

	}

}
