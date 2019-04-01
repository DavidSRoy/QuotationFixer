import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class QuotationFixer {

	public static final String QUOTE_LEFT = "“";
	public static final String QUOTE_RIGHT = "”";
	public static final String DUMB_QUOTE = "\"";
	public static final String DUMB_APOSTROPHE = "\'";
	public static final String SMART_APOSTROPHE = "’";
	public static  double progress = 0;
	
	public static boolean fileChanged = false;
	public static MainWindow window = new MainWindow();
	
	public static final boolean IS_LOGGING = false;
	
	public static void main(String[] args) throws IOException {
		
		
		window.start();
		
//		Scanner sc = new Scanner(System.in);
//		
//		System.out.println("Enter URL of Database");
//		String url = sc.nextLine();
//		
//		//System.out.println(fixQuotes(text));
//		fixSongs(url);
		
		
		
	}
	
	public QuotationFixer() {
		
	}
	
	//“correct quotes” 
	public static String fixQuotes(String text, FileWriter oldSongs, FileWriter newSongs) throws IOException {

		
		//String[] quotes = {"\"", QUOTE_LEFT, QUOTE_RIGHT};
		String quotes = DUMB_QUOTE + QUOTE_LEFT + QUOTE_RIGHT + "" + DUMB_APOSTROPHE + SMART_APOSTROPHE; //’
		
		text = " " + text + " ";
		String[] chars = text.split("");
		if (IS_LOGGING) {
			oldSongs.write(text);
		}
		
		text = "";
		boolean isBeginningQuote = false;
		fileChanged = false;
		for (int i = 1; i < chars.length - 1; i++) {
			
			
			String ch = chars[i];
			boolean isSpaceBefore = chars[i - 1].equals(" ");
			boolean isSpaceAfter = chars[i + 1].equals(" ");
			boolean containsQuotes = quotes.contains(ch);
			
			
			if (containsQuotes) {
				if(ch.equals(DUMB_APOSTROPHE) || ch.equals(SMART_APOSTROPHE)) {
					chars[i] = "’";
				} else if (!isBeginningQuote && isSpaceBefore && !isSpaceAfter) {
					chars[i] = QUOTE_LEFT;
					isBeginningQuote = true;
				} else if (!isSpaceBefore && isSpaceAfter) { //is end quotes, has letters before quote
					chars[i] = QUOTE_RIGHT;
					isBeginningQuote = false;
				} else if (isBeginningQuote) {  // && !(chars[i - 1].equals(" "))
					chars[i] = QUOTE_RIGHT;
					isBeginningQuote = false;
				} else if (((!isSpaceBefore && !isSpaceAfter) || (isSpaceBefore && isSpaceAfter))) {
					if (isBeginningQuote) {
						chars[i] = QUOTE_RIGHT;
					} else {
						chars[i] = QUOTE_LEFT;
					}
					isBeginningQuote = !isBeginningQuote;
				}
				fileChanged = true;
			}	
		}
		
		
		for (int i = 1; i < chars.length - 1; i++) {
			text += chars[i];
		}
		
		if (IS_LOGGING) {
			newSongs.write(text);
		}
		
		
		PrintStream console = System.out;
		System.setOut(console); 
		return text;	
	}
	
	
	 
    /**
     * Connect to the test.db database
     * @return the Connection object
     */
    private static Connection connect(String url) {
        // SQLite connection string
    	//String temp = "C:\\Home\\David\\Java\\QuotationFixer\\test databases\\Backup.s3db";
		int end = url.lastIndexOf(".");
//	    String[] chars = url.split("");
//	    chars[end - 1] = "_FixedQuotes"; 
//	    String fileName = chars.toString();
         
    	url = "jdbc:sqlite:" + url;
        
       try {
		Class.forName("org.sqlite.JDBC").newInstance();
	} catch (ClassNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (InstantiationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
        
        
        
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            println(e.getMessage());
        }
        return conn;
    }
 

    public static void fixSongs(String url) throws IOException{
    	
    	FileWriter oldSongs = null;
		FileWriter newSongs = null;
		
		if (IS_LOGGING) {
			oldSongs = new FileWriter("C:\\Home\\David\\Java\\QuotationFixer\\src\\OldSongs.txt");
			newSongs = new FileWriter("C:\\Home\\David\\Java\\QuotationFixer\\src\\NewSongs.txt");
		}
		/*
        String condition = " where songtext like '%\"%' "
        						+ "or songtext like '%" + QUOTE_LEFT + "%' "
								+ "or songtext like '%" + QUOTE_RIGHT + "%' "
								+ "or songtext like '%" + "''" + "%'";
								*/
		String condition = "";
        String songsSQL = "SELECT * from songs" + condition;
        String countSQL = "select count(SongID) as total from Songs" + condition;
        //System.out.println(sql);
        //size = countRs.getInt(1);
        int size = 0;
        try (Connection conn = connect(url);
                Statement stmt  = conn.createStatement();
        		ResultSet countRs = stmt.executeQuery(countSQL)) {
        	size = countRs.getInt(1);
        }  catch (SQLException e) {
            println(e.getMessage());
        }
        		
        try (Connection conn = connect(url);
             Statement stmt  = conn.createStatement();             
             ResultSet rs    = stmt.executeQuery(songsSQL)) {
         	      	
        	ResultSetMetaData data = rs.getMetaData();
        	//int size = data.getColumnCount();
        	
        	int index = 0;
            // loop through the result set
        	
            while (rs.next()) {
//                System.out.println(rs.getInt("songText") +  "\t" + 
//                                   rs.getString("name") + "\t" +
//                                   rs.getDouble("capacity"));
               	int id = rs.getInt("SongId");
               	String songText = rs.getString("SongText");
               	
               	
               	

               	
               	String updateSQL = "update songs set songtext = '" + fixQuotes(songText, oldSongs, newSongs) + "' where SongID = " + id;
               	//System.out.println(updateSQL);
               	
               	
               	if (fileChanged) {
               		Statement updateStmt  = conn.createStatement();
               		println(id + " " + rs.getString("SongTitle"));
               		updateStmt.executeUpdate(updateSQL);
               	}
               	
               	index++;
               	//int size = 1;
               	progress = ((double) index / size) * 100;
               	System.out.println(index);
               	System.out.println(size);
               	System.out.println(progress);
           		window.updateProgress(progress);

            }
        } catch (SQLException e) {
            println(e.getMessage());
        } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        if(IS_LOGGING) {
        	oldSongs.close();
            newSongs.close();
        }
        
        
    }
    
    private static void print(String text) {
    	window.addToStatusDisplayText(text);
    	//System.out.print(text);
		
    }
    
    private static void println(String text) {
    	print("\n" +text);
    }
    
    private static double getProgress() {
    	return progress;
    }
    

	
}
