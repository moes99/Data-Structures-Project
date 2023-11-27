package project;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner ui = new Scanner(System.in);
		System.out.print("Input some text (to stop, type \"\\\"): ");
		TextEditor t = new TextEditor();
		String input = "";
		while(!input.equals("\\")) {
			input = ui.next();
			t.appendText(input);
		}
		System.out.println("Your text: ");
		System.out.println(t.getText());
		System.out.println("----------------------------------------------------");
		
		System.out.println("Choose an operation:\n1) Undo\n2) Redo\n3) Input Text\n4) Exit");
		System.out.print("Your option: ");
		int option = ui.nextInt();
		System.out.println("----------------------------------------------------");
		while(option != 4) {
			switch(option) {
			case 1:{
				t.undo();
				System.out.println("Your updated text:\n" + t.getText());
				System.out.println("----------------------------------------------------");
				break;
			}
			case 2:{
				t.redo();
				System.out.println("Your updated text:\n" + t.getText());
				System.out.println("----------------------------------------------------");
				break;
			}
			case 3:{
				System.out.print("Input some text (to stop, type \"\\\"): ");
				input = "";
				while(!input.equals("\\")) {
					input = ui.next();
					t.appendText(input);
				}
				System.out.println("Your updated text:\n" + t.getText());
				System.out.println("----------------------------------------------------");
				break;
			}
			}
			System.out.println("Choose another operation:\n1) Undo\n2) Redo\n3) Input Text\n4) Exit");
			System.out.print("Your option: ");
			option = ui.nextInt();
			System.out.println("----------------------------------------------------");
		}
		System.out.println("You have exited the program!\nYour last updated text is:\n" + t.getText());
	}

}
