package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner ui = new Scanner(System.in);
		FileManager fm = new FileManager();
		
		//Available set of operations
		String[] options = {"1. Create a new text file"
						+ "\n2. Open an existing text file"
						+ "\n3. Edit an already open file"
						+ "\n4. Exit",
						
						"1. Write some text"
						+ "\n2. Undo text"
						+ "\n3. Redo text"
						+ "\n4. Save file"
						+ "\n5. Close file"
						+ "\n6. Find and/or replace"
						+ "\n7. Navigate to another file",};
		
		//At start of program
		System.out.println("Available options:");
		System.out.println(options[0]);
		System.out.print("Enter your choice: ");
		int option = ui.nextInt();
		while(!"1234".contains(Integer.toString(option))) { //Checking for valid input
			System.out.print("Invalid choice! Enter your choice again: ");
			option = ui.nextInt();
		}
		System.out.println("-------------------------------------------------------------------------");
		while(option != 4) {
			switch(option) {
			case 1:{ //If create a new file is selected
				TextEditor te = new TextEditor();
				fm.createNewFile(te);
				textEditorHandler(options, fm, te, ui);
				break;
			}
			case 2:{ //If open an existing text file is selected
				System.out.print("Enter file name without the \".txt\" extension: ");
				String name = ui.next();
				TextEditor te = new TextEditor();
				fm.createNewFile(te);
				while(true) {
					try {
						File testExists = new File(name+".txt");
						if(!testExists.exists()) {
							throw new FileNotFoundException(name + ".txt does not exist!");
						}
						te.setSaveFile(name);
						te.readFromFile();
						textEditorHandler(options, fm, te, ui);
						break;
					}
					catch(FileNotFoundException e) {
						System.out.println(e.getMessage());
						System.out.print("Enter correct file name: ");
						name = ui.next();
					}
				}
				break;
			}
			case 3:{ //If open an already open file is selected
				String openFiles = fm.displayOpenFiles();
				if(!openFiles.equals("")) {
					System.out.println(openFiles);
					System.out.print("Enter your choice(type only the complete file name exactly as written): ");
					String fileName = ui.next();
					while(!openFiles.contains(openFiles)) {
						System.out.print("Invalid choice! Enter your choice again: ");
						fileName = ui.next();
					}
					TextEditor te = fm.navigateToFile(fileName);
					textEditorHandler(options, fm, te, ui);
				}
				else {
					System.out.println("No files are already open!");
				}
				break;
			}
			}
			System.out.println("Choose another operation:");
			System.out.println(options[0]);
			System.out.print("Enter your choice: ");
			option = ui.nextInt();
			while(!"1234".contains(Integer.toString(option))) { //Checking for valid input
				System.out.print("Invalid choice! Enter your choice again: ");
				option = ui.nextInt();
			}
			System.out.println("-------------------------------------------------------------------------");
		}
		fm.closeAll();
		ui.close();
		System.out.println("You existed the program! Have a nice day!");
	}
	
	public static void textEditorHandler(String[] options, FileManager fm, TextEditor te, Scanner ui) throws FileNotFoundException {
		System.out.println("Available options:");
		System.out.println(options[1]);
		System.out.print("Enter your choice: ");
		int option = ui.nextInt();
		while(!"1234567".contains(Integer.toString(option))) { //Checking for valid input
			System.out.print("Invalid choice! Enter a correct option: ");
			option = ui.nextInt();
		}
		System.out.println("-------------------------------------------------------------------------");
		while(option != 7) {
			switch(option) {
			case 1:{ //If user wants to type more text
				System.out.println("Enter some text (to stop, type \"\\\"):");
				ui.reset();
				String word = ui.next();
				System.out.println(word.equals("\\"));
				while(!word.equals("\\")) {
					te.appendText(word);
					word = ui.next();
				}
				System.out.println("Your updated text:");
				System.out.println(te.getText());
				System.out.println("-------------------------------------------------------------------------");
				break;
			}
			case 2:{ //If users want to undo
				te.undo();
				System.out.println("Your updated text:");
				System.out.println(te.getText());
				System.out.println("-------------------------------------------------------------------------");
				break;
			}
			case 3:{ //If user wants to redo
				te.redo();
				System.out.println("Your updated text:");
				System.out.println(te.getText());
				System.out.println("-------------------------------------------------------------------------");
				break;
			}
			case 4:{ //If user wants to save
				if(te.getSaveFileName().equals("")) {
					System.out.print("Enter the name of the text file, without the \".txt\" extension, to be created: ");
					te.setSaveFile(ui.next());
				}
				te.saveToFile();
				System.out.println("File saved successfully!");
				System.out.println("-------------------------------------------------------------------------");
				break;
			}
			case 5:{ //If user wants to close
				System.out.print("Do you want to save before closing the file? (Y/N) ");
				if(ui.next().equals("Y")) {
					te.saveToFile();
				}
				fm.closeFile(te);
				System.out.println("File closed successfully!");
				System.out.println("-------------------------------------------------------------------------");
				break;
			}
			case 6:{ //If user wants to find and/or replace
				System.out.print("Enter the word you want to search for: ");
				String word = ui.next();
				int count = te.searchWord(word);
				System.out.println("The word \"" + word + "\" occurred " + count + " times in this file.");
				if(count > 0) {
					System.out.print("Do you want to replace all occurrences of this word with another one? (Y/N)");
					if(ui.next().equals("Y")) {
						System.out.print("Enter replacement word: ");
						te.replaceAll(word, ui.next());
					}
				}
				System.out.println("Your updated text:");
				System.out.println(te.getText());
				System.out.println("-------------------------------------------------------------------------");
			}
			}
			if(option == 5) {
				break;
			}
			System.out.println("Choose another operation:");
			System.out.println(options[1]);
			System.out.print("Enter your choice: ");
			option = ui.nextInt();
			while(!"123456".contains(Integer.toString(option))) { //Checking for valid input
				System.out.print("Invalid choice! Enter your choice again: ");
				option = ui.nextInt();
			}
			System.out.println("-------------------------------------------------------------------------");
		}
	}
}
