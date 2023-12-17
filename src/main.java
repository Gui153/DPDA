import java.util.*;
import java.util.regex.*;

/**
* Author: Guilherme Vilatoro Taglianeti
* Date created: 4/14/23
*/

public class main {
	
	
	public static boolean checkAlpha(String buf, char[] alphabet) {
		
		Pattern pat = Pattern.compile("[a-zA-Z0-9\\p{Punct}&&[^,]]{2,}");
		Matcher match = pat.matcher(buf);
		
		if(match.find() || buf.length() >1) {
			System.out.println("Input single characters");
			return false;
			
		}
		if(buf.contains("-")) {
			return true;
		}
		
		for(int i = 0; i < alphabet.length; i++) {
			
			if(alphabet[i] == buf.charAt(0)) {
				return true;
			}
			
			
		}
		
		System.out.println("Input  \""+buf+"\" is not part of the alphabet, please try again");
		return false;
	}
	
	public static boolean checkAlpha2(String buf, char[] alphabet) {
		
		Pattern pat = Pattern.compile("[a-zA-Z0-9\\p{Punct}&&[^,]]{2,}");
		Matcher match = pat.matcher(buf);
		
		if(match.find()) {
			System.out.println("Input single characters");
			return false;
			
		}
		buf = buf.replace(",","");
		boolean temp = false;
		for(int j = 0; j < buf.length(); j++) {
			for(int i = 0; i < alphabet.length; i++) {
				
				if(alphabet[i] == buf.charAt(j)) {
					temp = true;
				}
			}
			if (temp == false) {
				System.out.println("Input  \""+buf.charAt(j)+"\" is not part of the alphabet, please try again");
				return false;
			}
			temp = false;
		}
		
		return true;
	}
	
	
	
	public static void main(String[] args) {
		LinkedList <State> table = new LinkedList<State>();
		// TODO Auto-generated method stub
		int num;
		String buf;
		char[] alphabet= {};
		int[] accept = {};
		char[] stackAlpha = {};
		Scanner scan = new Scanner(System.in);
		
		
		
		while(true) {
			System.out.println("Enter number of states :");
			//getting the number of states
			try {
				num = scan.nextInt();
				num--;
				break;
			}
			catch(InputMismatchException e){
				System.out.println("Input is not recognized as a number, try again.");
				scan.nextLine();
			}
			
		}
				
		
		while(true) {
			System.out.println("Enter input alphabet as a comma-separated list of symbols :");
			//regex for valid input
				buf = scan.next();
				Pattern pat = Pattern.compile("[a-zA-Z0-9\\p{Punct}&&[^,]]{2,}");
				Matcher match = pat.matcher(buf);
				
				if(match.find()) {
					System.out.println("Input single characters");
					continue;
				}
				
				try {
					buf = buf.replace(",","");
					alphabet = buf.toCharArray();
					break;
				}
				catch(Error e){
					System.out.println("Input is not recognized as a comma-separated list, try again.");
					scan.nextLine();
				}
				
		}
		
		// getting the accept states
		boolean acc= true;
		while(acc) {
				System.out.println("Enter accepting states as a comma-separated list of integers :");
				buf = scan.next();
				//regex for valid input
				Pattern pat = Pattern.compile("[a-zA-Z\\p{Punct}&&[^,]]");
				Matcher match = pat.matcher(buf);
				
				if(match.find()) {
					System.out.println("Input numbers followed by commas");
					continue;
				}
				//finding the size of the array
				buf = buf.strip();
				int count=1;
				for( int i = 0; i < buf.length(); i++) {
					if(buf.charAt(i) == ',') {
						count++;
					}
				}
				
				if(buf.lastIndexOf(",") == buf.length()-1) {
					count--;
				}
				
				
				//start to populate the accept array
				accept = new int[count];
				for(int i = 0; i < accept.length; i++) {
					
					try {
						if(buf.indexOf(",")>0) {
							accept[i] =  Integer.parseInt(buf.substring(0,buf.indexOf(",")));
							buf = buf.substring(buf.indexOf(",")+1);
						}
						else {
							accept[i] =  Integer.parseInt(buf);
							buf = "";
						}
					}
					catch(Error e) {
						System.out.println("One of the intger inputs was not recognized, please try again");
						acc = true;
						break;
					}
					
					
					if(accept[i] > num) {
						System.out.println("invalid state "+ accept[i] +"; enter a value between 0 and "+num);
						acc = true;
						break;
					}
					
					
					if( i == accept.length-1) {
						acc = false;
					}
					
				}
				
				
		}
		
		// creating the stack alphabet
		stackAlpha = new char[alphabet.length+2];
		for(int i = 0; i < alphabet.length;i++) {
			stackAlpha[i]= alphabet[i];
		}
		stackAlpha[stackAlpha.length-1] = '-';
		stackAlpha[stackAlpha.length-2] = '$';
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		// all values were acquired
	/*	System.out.println("state values goes from 0-"+num);
		System.out.println("alphabet:");	
		for(int i = 0; i < alphabet.length; i++) {
			System.out.print(""+alphabet[i] + ", ");		
		}
		System.out.println("\nstack alphabet:");	
		for(int i = 0; i < stackAlpha.length; i++) {
			System.out.print(""+stackAlpha[i] + ", ");		
		}
		System.out.println("\naccept:");	
		for(int i = 0; i < accept.length; i++) {
			System.out.print(""+accept[i]+", ");		
		}
		System.out.println("");	
		
		*/
		
		
		//get the transition table now
		for(int i = 0; i <= num; i++) {
			boolean ac = false;
			for(int b = 0; b < accept.length; b++) {
				if(accept[b] == i) {
					ac = true;
					break;
				}
			}
			
			

			
			while(true) {
				System.out.println("transitions for state "+i+":");
				for(int j = 0; j < table.size(); j++) {
					if(table.get(j).getVal() == i) {
					System.out.println(table.get(j));
					}
				}
				System.out.println("Need a transition rule for state "+i+" ? (y or n)");		
				buf = scan.next();
				if(buf.equals("n")) break;
				else if(buf.equals("y")) {}
				else { 
					System.out.println("Please input y or n");
					continue;
					}
				
				State news = new State(i,ac);
				//get Input Symbol
				while(true) {
					System.out.println("Input Symbol to read (enter - for epsilon):");
					buf = scan.next();
					buf = buf.strip();
					if(checkAlpha(buf,stackAlpha)) {
						news.setRead(buf.charAt(0));
						break;	
					}
				}
				//get stack symbol to read/pop
				while(true) {
					System.out.println("Stack symbol to match and pop (enter - for epsilon):");
					buf = scan.next();
					buf = buf.strip();
					if(checkAlpha(buf,stackAlpha)) {
						news.setPop(buf.charAt(0));
						break;
					}
				}
				// get state to for transition
				while(true) {
					int temp = 0;
					System.out.println("State to transition to :");
					try {
						temp = scan.nextInt();
						if(temp > num) {
							System.out.println("invalid state "+ temp +"; enter a value between 0 and "+num);
							continue;
						}
						news.setTransit(temp);
						break;
					}
					catch(InputMismatchException e){
						System.out.println("Input is not recognized as a number, try again.");
						scan.nextLine();
					}
				}
				
				//symbols to push
				while(true) {
					System.out.println("Stack symbols to push as comma separated list, first symbol to top of stack (enter - for epsilon): ");
					buf = scan.next();
					Pattern pat = Pattern.compile("[a-zA-Z0-9\\p{Punct}&&[^,]]{2,}");
					Matcher match = pat.matcher(buf);
					
					if(match.find()) {
						System.out.println("Input single characters between the commas.");
						continue;
					}
					
					if(buf.contains("-") && buf.length()>1) {
						System.out.println("Do not include an epsilon(-) in the comma separated list.");
					}
					
					
					try {
						if(checkAlpha2(buf,stackAlpha)) {
							buf = buf.strip();
							buf = buf.replace(",","");
							news.setPush(buf);
							break;
						}
					}
					catch(Error e){
						System.out.println("Input is not recognized as a comma-separated list, try again.");
						scan.nextLine();
					}
				}
				
				//adding state to a list of states and checking if it complies with the rules.
				boolean test = true;
				for (int j = 0; j < table.size(); j++) {
					State onT = table.get(j);
					
					if(onT.getVal() ==  news.getVal()) {
						//checking for eps, eps
						if((onT.getRead() == '-' && onT.getPop() == '-')|| (news.getRead() == '-' && news.getPop() == '-')){
							
							System.out.println("Violation of DPDA due to epsilon input/epsilon stack transition from state "+onT.getVal()+":"+onT+"");
							test = false;
							break;
						}
						//checking for eps, char
						if(onT.getRead() == '-' && onT.getPop()!='-' && news.getRead() == '-' && news.getPop() !='-' && onT.getPop() == news.getPop()){
							System.out.println("Violation of DPDA due to epsilon input/epsilon stack transition from state "+onT.getVal()+":"+onT+"");
							test = false;
							break;
						}
						//checking for char,eps 
						if(onT.getRead() != '-' && news.getRead() != '-' && news.getRead() == onT.getRead() && ((news.getPop() =='-' || onT.getPop() == '-'))){
							System.out.println("Violation of DPDA due to epsilon input/epsilon stack transition from state "+onT.getVal()+":"+onT+"");
							test = false;
							break;
						}
						//checking for char, char
						if(onT.getRead() != '-' && onT.getPop()!='-' && news.getRead() != '-' && news.getPop() !='-' && onT.getRead() == news.getRead()&& onT.getPop() == news.getPop()){
							System.out.println("Violation of DPDA ue to multiple transitions for the same input and stack top from state "+onT.getVal()+":"+onT+"");
							test = false;
							break;
						}
					}
				}
				if(test == false) {
					continue;
				}
				table.add(news);
				
			}
		}	
		//printing tansitions
		System.out.println("Printing all transitions");
		for(int j = 0; j <= num; j++) {
			System.out.println("state "+j+":");
			for(int i = 0; i < table.size(); i++) {
				if(j == table.get(i).getVal()) {
					System.out.println(table.get(i));
				}
				
			}
		}
		
		//running the DPA simulator
		scan.nextLine(); 

		while (true) {
			//get input
			System.out.println("Enter an input string to be processed by the PDA:");
			buf = scan.nextLine();
			//check if all chars in the string are valid
			boolean test = true;
			for(int z = 0; z < buf.length(); z++) {
				test = false;
				for (int j = 0; j < alphabet.length; j++ ) {
					if(alphabet[j] == buf.charAt(z)) {
						test = true;
					}
					
				}
				if(!test) {
					break;
				}
			}
			if(test == false) {
				System.out.println("Invalid string alphabet, please try again");
				continue;
			}
			
			//DPDA simulator:
			int currState = 0;
			String input = buf;
			LinkedList<Character> stack = new LinkedList<Character>();
			String path = "";
			State tra = table.get(0);
			//path will keep track of the transitions
			path +="(q"+currState+";"+input+";";
			path += "eps)";	
			
			boolean done = false;
			while(!done) {
				//if any changes does not change to true, after going
				//over all transitions, it means that it is stuck
				//so check if input is empty, and if it ended in a accept/reject.
				boolean anyChanges = false;
				
				
				
				for(int i = 0; i < table.size(); i++) {
					tra = table.get(i);
					if(currState == tra.getVal()) {
						
						//if eps, eps push value
						if(tra.getRead() == '-' && tra.getPop() == '-') {
							if(!tra.getPush().equals("-")) {
								for(int j = tra.getPush().length()-1; j >= 0 ; j--) {
									if(tra.getPush().charAt(j) != '-') {
										stack.addFirst(tra.getPush().charAt(j));
									}
								}
							}
							//just push
							currState = tra.getTransit();
							anyChanges = true;
							break;
						}
						
						//if eps, val push val
						if(tra.getRead() == '-' && tra.getPop() != '-') {
							if(!stack.isEmpty()) {
								if(tra.getPop() == stack.get(0)) {
									//remove 1 from stack
									stack.pop();
									if(!tra.getPush().equals("-")) {
										for(int j = tra.getPush().length()-1; j >= 0 ; j--) {
											if(tra.getPush().charAt(j) != '-') {
											stack.addFirst(tra.getPush().charAt(j));
											}
										}
									}
									anyChanges = true;
									currState = tra.getTransit();
									break;
								}
							}
						}
						
						// if val,eps push val
						if(tra.getRead() != '-' && tra.getPop() == '-') {
							if(!input.equals("")) {
								if(tra.getRead() == input.charAt(0)) {
									if(!tra.getPush().equals("-")) {
										for(int j = tra.getPush().length()-1; j >= 0 ; j--) {
											if(tra.getPush().charAt(j) != '-') {
												stack.addFirst(tra.getPush().charAt(j));
											}
										}
									}
									//remove 1 from input
									input = input.substring(1,input.length());
									currState = tra.getTransit();
									anyChanges = true;
									break;
								}
							}
						}
						
						// if val,val push val
						//fix here for last input when input is empty.
						if(tra.getRead() != '-' && tra.getPop() != '-') {
							if(!stack.isEmpty() && !input.equals("")) {
								if(tra.getRead() == input.charAt(0) && tra.getPop() == stack.getFirst()) {
									stack.pop();
									if(!tra.getPush().equals("-")) {
										for(int j = tra.getPush().length()-1; j >= 0 ; j--) {
											if(tra.getPush().charAt(j) != '-') {
												stack.addFirst(tra.getPush().charAt(j));
											}
										}
									}
									//remove 1 from input and 1 from stack
									
									input = input.substring(1,input.length());
									currState = tra.getTransit();
									anyChanges = true;
									break;
								}
							}
						}
					}
				}
				
				
				
				if(anyChanges == false) {
					for(int z = 0; z < accept.length; z++) {
						if(accept[z] == currState && input.equals("") && stack.isEmpty()) {
							System.out.println("Accept String "+buf +"?True");
							System.out.println(path);
							done = true;
							break;
						}
						else if(z == accept.length-1) {
							System.out.println("Accept String "+buf+"?False");
							System.out.println(path);
							done = true;
							break;
						}
					}
					
					
					
				}
				
				

				//path will keep track of the transitions
				path+="--"+tra+"-->";
				path +="(q"+currState+";";
				if(input.equals("")) {
					path += "eps;";	
				}
				else {
					path += ""+input+";";
				}
				
				if(stack.isEmpty()) {
				path += "eps)";	
				}
				else {
					for(int z = 0; z < stack.size(); z++) {
						path += ""+stack.get(z);
					}
					path+=")";
				}
				
				
			}
		}				
	}
}
