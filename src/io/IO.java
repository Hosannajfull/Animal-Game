package io;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * The {@link IO} class is a class that provides all the basic input and output
 * methods required for Data Structures in Java (COMS W3134).
 * 
 */
public final class IO {
	public static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
	public static PrintStream stdout = System.out;
	public static PrintStream stderr = System.err;
	public static BufferedWriter output;

	/**
	 * Reads the contents of a file.
	 * 
	 * @param fileName
	 *            name of the file to read
	 * @return contents of the file if the contents of the file were read
	 *         successfully or an empty string if otherwise
	 */
	public static String readFile(String fileName) {
		String fileContents = "";
		BufferedReader bufferedReader;
		try {
			bufferedReader = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				fileContents += line + " ";
			}
			bufferedReader.close();
		} catch (IOException e) {
			stderr.println("Cannnot open file: " + fileName);
		}
		return fileContents;
	}
	public static void printFile(String contents) {
		try {	 		
			File file = new File("knowledge.txt");
			// if file doesnt exists, then create it

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(contents);
			bw.close();
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Prompts the user for a {@link String} with a prompt and returns the
	 * user's response.
	 * 
	 * @param prompt
	 *            prompt to the user
	 * @return user's response to the prompt or an empty string if there was an
	 *         {@link IOException}
	 */
	public static String prompt(String prompt) {
		try {
			stdout.print(prompt);
			stdout.flush();
			return stdin.readLine();
		} catch (IOException e) {
			stderr.println(e);
			return "";
		}
	}

	/**
	 * Prompts the user for an integer and returns the integer that the user
	 * enters. Specifies a range for the integer to be in. The min and max are
	 * inclusive.
	 * 
	 * @param prompt
	 *            prompt to the user
	 * @param min
	 *            minimum integer
	 * @param max
	 *            maximum integer
	 * @return user's response to the prompt or (min - 1) if an exception
	 *         occurred.
	 */
	public static int promptInt(String prompt, int min, int max) {
		try {
			stdout.print(prompt + " [" + min + " to " + max + "] ");
			stdout.flush();
			int result = Integer.parseInt(stdin.readLine());
			if (result < min || result > max) {
				throw new IOException("Out of range.");
			}
			return result;
		} catch (Exception e) {
			stderr.println(e);
			return min - 1;
		}
	}

	/**
	 * Checks if the user's response was "Yes" or "No".
	 * 
	 * @param yesOrNo user's yes-or-no response
	 * @return true if yesOrNo is "Yes" or false if yesOrNo is "No"
	 */
	public static boolean affirmative(String yesOrNo) {
		return yesOrNo.charAt(0) == 'y' || yesOrNo.charAt(0) == 'Y';
	}
}


