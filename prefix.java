
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main{
	public static void main(String[] args) throws IOException {
        // To read from file
		File file =  new File(args[0]);
		BufferedReader br = new BufferedReader(new FileReader(file));
		try {
			String sentence = br.readLine();
			while (sentence != null && sentence.length() > 0) {
				String prefixExpression = sentence.toString();
				// Breaking String into Token
				StringTokenizer strngtok = new StringTokenizer(prefixExpression);
                
				//Initiakising Stacks for operators and numbers
				Stack<String> operatorList = new Stack<String>();
				Stack<Float> numberList = new Stack<Float>();
				boolean lastoperator = false;
                
				//Iterating through the token
				while (strngtok.hasMoreElements()) {
					String strng = strngtok.nextToken();

					//If token is operator add to operator stack
					if (strng.equals("*") || strng.equals("+") || strng.equals("/")) {
						operatorList.add(strng);
						lastoperator = true;
					//If token is number add to number Stack	
					} else if (lastoperator || numberList.isEmpty()) {
						numberList.add(Float.valueOf(strng));
						lastoperator = false;
					} else {
						numberList.add(doOperation(operatorList.pop(), numberList.pop(),
								Float.valueOf(strng)));
					}
				}

				// Printing result of each Line
				System.out.println(Math.round(numberList.pop()));
				sentence = br.readLine();
			}
		} finally {
			br.close();
		}
	}

	//Function to check if the token is the operator or not
	public static Float doOperation(String op, Float a, Float b) {
		if (op.equals("+")) {
			return a + b;
		} else if (op.equals("*")) {
			return a * b;
		} else if (op.equals("/")) {
			return a / b;
		}
		return (float) 0;
	}

}
