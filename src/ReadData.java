
import java.io.*;
import java.util.ArrayList;


public class ReadData 
{
    public ReadData(ArrayList<Acquaintance> contact_book,ArrayList<Relative> relative_book,ArrayList<Personal> personal_book,ArrayList<Professional> professional_book,ArrayList<Casual> casual_book,String file)
    {
        try {
        	FileOutputStream fileOut = new FileOutputStream(file); 
        	ObjectOutputStream Output = new ObjectOutputStream(fileOut);
            
        	Output.writeObject(contact_book);
        	Output.writeInt(contact_book.size());
        	Output.writeObject(relative_book);
        	Output.writeInt(relative_book.size());
        	Output.writeObject(personal_book);
        	Output.writeInt(personal_book.size());
        	Output.writeObject(professional_book);
        	Output.writeInt(professional_book.size());
        	Output.writeObject(casual_book);
        	Output.writeInt(casual_book.size());

           // System.out.println("Stored in database"); 
            fileOut.close(); 
            Output.close();
        }
        catch (FileNotFoundException e) 
        { 
            //System.err.println("File not found");
        }
        catch (IOException e)
        {
        	//System.err.println("Read or write failed");
        }
    }
}

