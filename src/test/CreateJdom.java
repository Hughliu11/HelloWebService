package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

//生成xml文件
public class CreateJdom {

    public static void main(String[] args) {
        //定义元素
        Element people,student;
        people = new Element("people");
        student = new Element("student");
        //设置属性
        student.setAttribute("name", "张三");
        student.setAttribute("salary","8000");
        //设置文本
        student.setText("呵呵");
        //将其添加到根目录下
        people.addContent(student);
        
        String str="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
        		"<people>\r\n" + 
        		"    <student name=\"张三\" salary=\"8000\">呵呵</student>\r\n" + 
        		"</people>";
       File file= new File("TestXml.xml");
  
        try {
        	
			FileOutputStream outputStream=new  FileOutputStream(file);			
			outputStream.write(str.getBytes());
			outputStream.close();
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        //新建一个文档。
        Document doc = new Document(people);
       
        //读取格式，赋值给当前的Format
        Format format = Format.getCompactFormat();
        //对当前格式进行初始化
        format.setEncoding("UTF-8");
        //设置xml文件缩进4个空格
        format.setIndent("    ");
        //建一个xml输出工厂，将格式给工厂
        XMLOutputter xmlout = new XMLOutputter(format);
        try {
            //将其写好的文本给工厂，并且建一个文件输出流，将数据输出
            xmlout.output(doc, new FileOutputStream("people.xml"));
            System.out.println("成功！");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block	
            e.printStackTrace();
        }
    }
}
