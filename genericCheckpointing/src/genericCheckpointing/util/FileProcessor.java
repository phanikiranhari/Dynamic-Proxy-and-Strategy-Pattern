package src.genericCheckpointing.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Hari
 *
 */

public class FileProcessor {
	
	private BufferedReader bufferReader;
	private BufferedWriter bufferWriter;

	/**
	 * This method takes the required parameters viz. filename and buffer reader and
	 * returns the current line from the file.
	 * @param fileName
	 * @param bufferReader
	 * @return the current line from the file
	 */
	public String readLineFromFile() {
		String currentLine = null;
		try {
			currentLine = bufferReader.readLine();
		} catch (IOException e) {
			Logger.writeMessage("Exception: Some exception has occured while reading the file");
			System.exit(0);
		}
		return currentLine;
	}
	
	/**
	 * This method takes the required parameters viz. current line and
	 * writes the current line into the file.
	 * @param line to be written into a file
	 * @return void
	 */
	public void writeLineToFile(String currentLine) {
		try {
			bufferWriter.write(currentLine);
			bufferWriter.newLine();
		} catch (IOException e) {
			e.printStackTrace();
			Logger.writeMessage("Exception: Some exception has occured while writing into the file");
			System.exit(0);
		}
	}
	/** This method closes the buffer writer
	 * @param void
	 * @return void
	 * 
	 */
	public void closeBufferWriter() {
		try {
			bufferWriter.close();
		} catch (IOException e) {
			Logger.writeMessage("Exception: Problem closing the buffer writer");
			System.exit(0);
		}
	}
	/**
	 * Constructor that instantiates a buffer reader and makes it ready to read file content
	 * @param fileName
	 * @return No Value
	 */
	public FileProcessor(String fileName) {
		try {
			bufferReader = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			Logger.writeMessage("Error File not found");
			System.exit(0);
		}
	}
	/**
	 * Constructor that instantiates a buffer writer and makes it ready to write file content
	 * @param fileName
	 * @param boolean value to mention that is output
	 * @return No Value
	 */
	public FileProcessor(String fileName, boolean output) {
		try {
			if (bufferWriter== null)
				bufferWriter = new BufferedWriter(new FileWriter(fileName));
			
		} catch (FileNotFoundException e) {
			Logger.writeMessage("Error: File not found");
			System.exit(0);
		} catch (IOException e) {
			Logger.writeMessage("Error with file handling");
			System.exit(0);
		}
	}
	/**
	 * Default constructor
	 */
	public FileProcessor(){
		
	}
}
