package replacer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;


public class Logic
{
	 String find;
	 String replace;
	 String checkedMark = "\u2713";
	 
	 Logic(String find, String replace)
	 {
		 this.find = find;
		 this.replace = replace;
	 }
	 
	/* private int filesDone()
	 {
		 String temp = doneCount.getText().toString();
		 try
		 {
			 int nooflines = Integer.parseInt(temp.substring(0,temp.indexOf(' ')));
			 return nooflines;
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 return 0;
	 }*/
    /*public void run() 
    {
        try {
        	String data = "";
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) 
            {
            	line.replaceAll(find, replace);
            	data += line + System.getProperty("line.separator");
            }
            fr.close();
            br.close();
            data = data.replaceAll(find, replace);
            FileWriter fw = new FileWriter(file);
            BufferedWriter out = new BufferedWriter(fw);
            out.write(data);
            out.flush();
            out.close();
            result.append(checkedMark + "  " + file.getName() + "\r\n"); 
            doneCount.setText(String.valueOf(filesDone()) + " files done");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }*/
    public void replacer(File file1)
    {
    	try {
        	String data = "";
            FileReader fr = new FileReader(file1);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) 
            {
            	line.replaceAll(find, replace);
            	data += line + System.getProperty("line.separator");
            }
            fr.close();
            br.close();
            data = data.replaceAll(find, replace);
            FileWriter fw = new FileWriter(file1);
            BufferedWriter out = new BufferedWriter(fw);
            out.write(data);
            out.flush();
            out.close();
//            doneCount.setText(String.valueOf(filesDone()) + " files done");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
