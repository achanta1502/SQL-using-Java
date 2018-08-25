import java.io.RandomAccessFile;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.SortedMap;

public class Main {
	static String prompt = "achantasql> ";
	static String version = "v2016";
	static String copyright = "©2016 Achanta pavan kumar";
	static boolean isExit = false;
		
	public static int pageSize = 512;
	
	static Scanner scanner = new Scanner(System.in).useDelimiter(";");	
    public static void main(String[] args) {
    	Initialization init=new Initialization();
    	init.init();		
		splashScreen();	
		String userCommand = ""; 

		while(!isExit) {
			System.out.print(prompt);
			userCommand = scanner.next().replace("\n", " ").replace("\r", "").trim().toLowerCase();
			Parsing pr=new Parsing();
				
				ArrayList<String> commandTokens = new ArrayList<String>(Arrays.asList(userCommand.split(" ")));

				switch (commandTokens.get(0)) {

				    case "show":
					    pr.showTables();
					    break;
					
				    case "create":
						pr.parseCreateString(userCommand);
					    break;

					case "insert":
						pr.parseInsertString(userCommand);
						break;
						
					case "delete":
						pr.parseDeleteString(userCommand);
						break;	

					case "update":
						pr.parseUpdateString(userCommand);
						break;
						
					case "select":
						pr.parseQueryString(userCommand);
						break;

					case "drop":
						pr.dropTable(userCommand);
						break;	

					case "help":
						help();
						break;

					case "version":
						System.out.println("DavisBase Version " + version);
						System.out.println(copyright);
						break;

					case "exit":
						isExit=true;
						break;
						
					case "quit":
						isExit=true;
						break;
			
					default:
						System.out.println("I didn't understand the command: \"" + userCommand + "\"");
						System.out.println();
						break;
				}
			} 
		
		System.out.println("Exiting...");
	}
	
    public static void splashScreen() {
		System.out.println(line("-",80));
        System.out.println("Welcome to My own Sql");
		System.out.println("AchantaSql Version " + version);
		System.out.println(copyright);
		System.out.println("\nType \"help;\" to display supported commands.");
		System.out.println(line("-",80));
	}
	public static String line(String s,int num) {
		String a = "";
		for(int i=0;i<num;i++) {
			a += s;
		}
		return a;
	}
	
	public static void help() {
		System.out.println(line("*",80));
		System.out.println("SUPPORTED COMMANDS");
		System.out.println("All commands below are case insensitive");
		System.out.println("Indentation is very important and use the indentation as shown");
		System.out.println();
		System.out.println("\tSHOW TABLES;                                               Display all the tables in the database.");
		System.out.println("\tCREATE TABLE table_name (<column_name datatype>);          Create a new table in the database.");
		System.out.println("\tINSERT INTO table_name VALUES (value1,value2,..);          Insert a new record into the table.");
		System.out.println("\tDELETE FROM TABLE table_name WHERE row_id = key_value;     Delete a record from the table whose rowid is <key_value>.");
		System.out.println("\tUPDATE table_name SET column_name = value WHERE condition; Modifies the records in the table.");
		System.out.println("\tSELECT * FROM table_name;                                  Display all records in the table.");
		System.out.println("\tSELECT * FROM table_name WHERE column_name operator value; Display records in the table where the given condition is satisfied.");
		System.out.println("\tDROP TABLE table_name;                                     Remove table data and its schema.");
		System.out.println("\tVERSION;                                                   Show the program version.");
		System.out.println("\tHELP;                                                      Show this help information.");
		System.out.println("\tEXIT;                                                      Exit the program.");
		System.out.println();
		System.out.println();
		System.out.println(line("*",80));
	}

	}