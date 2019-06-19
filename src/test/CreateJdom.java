package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

//����xml�ļ�
public class CreateJdom {

    public static void main(String[] args) {
        //����Ԫ��
        Element people,student;
        people = new Element("people");
        student = new Element("student");
        //��������
        student.setAttribute("name", "����");
        student.setAttribute("salary","8000");
        //�����ı�
        student.setText("�Ǻ�");
        //������ӵ���Ŀ¼��
        people.addContent(student);
        
        String str="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
        		"<people>\r\n" + 
        		"    <student name=\"����\" salary=\"8000\">�Ǻ�</student>\r\n" + 
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
        
        //�½�һ���ĵ���
        Document doc = new Document(people);
       
        //��ȡ��ʽ����ֵ����ǰ��Format
        Format format = Format.getCompactFormat();
        //�Ե�ǰ��ʽ���г�ʼ��
        format.setEncoding("UTF-8");
        //����xml�ļ�����4���ո�
        format.setIndent("    ");
        //��һ��xml�������������ʽ������
        XMLOutputter xmlout = new XMLOutputter(format);
        try {
            //����д�õ��ı������������ҽ�һ���ļ�����������������
            xmlout.output(doc, new FileOutputStream("people.xml"));
            System.out.println("�ɹ���");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block	
            e.printStackTrace();
        }
    }
}
