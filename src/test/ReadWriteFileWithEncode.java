package test;

import java.io.BufferedReader;  
import java.io.BufferedWriter;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.OutputStreamWriter;  
  
public class ReadWriteFileWithEncode {  
  
    public static void write(String path, String content, String encoding)  
            throws IOException {  
        File file = new File(path);  
        file.delete();  
        file.createNewFile();  
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(  
                new FileOutputStream(file), encoding));  
        writer.write(content);  
        writer.close();  
    }  
  
    public static String read(String path, String encoding) throws IOException {  
        String content = "";  
        File file = new File(path);  
        BufferedReader reader = new BufferedReader(new InputStreamReader(  
                new FileInputStream(file), encoding));  
        String line = null;  
        while ((line = reader.readLine()) != null) {  
            content += line + "\n";  
        }  
        reader.close();  
        return content;  
    }  
  
    public static void main(String[] args) throws IOException {  
        String content = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
        		"<people>\r\n" + 
        		"    <student name=\"ÕÅÈý\" salary=\"8000\">ºÇºÇ</student>\r\n" + 
        		"</people>\r\n" + 
        		"";  
        String path = "testxml01.xml";  
        String encoding = "utf-8";  
        ReadWriteFileWithEncode.write(path, content, encoding);  
        System.out.println(ReadWriteFileWithEncode.read(path, encoding));  
    }  
}  