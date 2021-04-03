package sample;
import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.awt.event.*;
import java.awt.*;

public class fileServerClient extends Frame {
	private Socket socket = null;
	private BufferedReader in = null;
	private PrintWriter networkOut = null;
	private BufferedReader networkIn = null;
	public static String ClientFolder;
	public static String CompName;

	public  static String SERVER_ADDRESS = "localhost";
	public  static int    SERVER_PORT = 16790;

	public fileServerClient(String s) {

		ClientFolder = s;

		try {
			socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
			in = new BufferedReader(new InputStreamReader(System.in));
		} catch (UnknownHostException e) {
			System.err.println("Unknown host: "+SERVER_ADDRESS);
		} catch (IOException e) {
			System.err.println("IOEXception while connecting to server: "+SERVER_ADDRESS);
		}
		if (socket == null) {
			System.err.println("socket is null");
		}
		try {
			networkOut = new PrintWriter(socket.getOutputStream(), true);
			networkIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			System.err.println("IOEXception while opening a read/write connection");
		}

	}

//	protected boolean processUserInput() {
//		String input = null;
//
//		// print the menu
//		System.out.println("Commands: ");
//		System.out.println("DIR -  To display contents of Server Directory");
//		System.out.println("UPLOAD - to upload a file to the server");
//		System.out.println("DOWNLOAD - to download a file from server");
//		System.out.println("QUIT");
//		System.out.print("Command> ");
//
//		try {
//			input = in.readLine();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		if (input.equals("DIR")) {
//			listAllFiles();
//		} else if (input.equals("UPLOAD")) {
//			uploadNewFile();
//		} else if(input.equals("DOWNLOAD")){
//			downloadFileFromServer();
//		}
//		else if (input.equals("QUIT")) {
//			return false;
//		} else{
//			System.out.println("Invalid Command. Please Try Again! ");
//		}
//		return true;
//	}

	//menu option 3
	public void downloadFileFromServer(String fileName) {
		String data = null;

		networkOut.println("DOWNLOAD " + fileName);
		try{
			data = networkIn.readLine();
			String data2w[] = data.split("/");
			String path = ClientFolder + "\\" +fileName;
			File output = new File(path);
			FileWriter fin = new FileWriter(output);
			for(int i=0; i<data2w.length; i++){
				fin.append(data2w[i] + "\n");
			}
			fin.flush();
			fin.close();

		}
		catch (IOException e){
			e.printStackTrace();
		}
	}

	// menu option 2
	public void uploadNewFile(String fileName) {
		String message = null;
		String path = ClientFolder + "\\" +fileName;
		try {

			File myFile = new File(path);
			FileReader fr = new FileReader(myFile);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine())!= null){
				System.out.println(line);
			}
		} catch (IOException e) {
			System.err.println("Error reading from socket");
		}

		networkOut.println("UPLOAD " + path);
		try{
			message = networkIn.readLine();
		}
		catch (IOException e){
			System.err.println("Error reading from socket.");
		}
		System.out.println(message);
	}

	// menu option 1
	public String[] listAllFiles(){
		String File = null;
		networkOut.println("DIR");
		try {
			File = networkIn.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String files[] = File.split(",");

		return files;
	}

	public void openLocalFile(String fileName){
		String path = ClientFolder + "\\" +fileName;
		try{
			File file = new File(path);
			if(!Desktop.isDesktopSupported())//check if Desktop is supported by Platform or not
			{
				System.out.println("not supported");
				return;
			}
			Desktop desktop = Desktop.getDesktop();
			if(file.exists())         //checks file exists or not
				desktop.open(file);              //opens the specified file
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
	public void openServerFile(String file){

		networkOut.println("OPEN "+ file);
	}

	public static void main(String[] args) {

	}
}