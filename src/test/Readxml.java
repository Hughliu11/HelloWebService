package test;

import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

//��ȡpeople.xml�ĵ�
public class Readxml {

    public static void main(String[] args) {
        //�½�����������xml
        SAXBuilder sax = new SAXBuilder();
        //��һ���ĵ�ȥ��������
        Document doc;
        try {
            //��ȡpeople.xml�ĵ�
            doc = sax.build("people.xml");
            //��ø��ڵ�
            Element people = doc.getRootElement();
            //��ø��ڵ��µĽڵ�����
            List<Element> list = people.getChildren();
            for(int i = 0;i<list.size();i++){
                Element e = list.get(i);
                //�������ֵ
                System.out.println("name��"+e.getAttributeValue("name")+"   salary��"+e.getAttributeValue("salary"));
                //����ı�ֵ
                System.out.println(e.getText());
            }
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}