package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class ReadNationXML {

    /**
     * @param args
     */
    public static void main(String[] args) throws JDOMException, IOException {
        SAXBuilder sb = new SAXBuilder();
        //�����ĵ�����
        Document doc = sb.build(new FileInputStream(new File("nation.xml")));
       // Document doc = sb.build(Test.class.getClassLoader().getResourceAsStream("nation.xml"));
        //��ȡ��Ԫ��
        Element root = doc.getRootElement();
        //��λ��<Configuration> -> <Key>
        List<Element> list = root.getChildren("Key");
        List<Element> children = new ArrayList<Element>();
        List<Element> childrens = new ArrayList<Element>();
        for (int i = 0; i < list.size(); i++) {
            Element element = (Element) list.get(i);
            System.out.print(element.getAttributeValue("Name"));
            //��λ��<Configuration> -> <Key>  -> <Value>
            children = element.getChildren("Value"); 
            for(int j=0; j<children.size(); j++){
                Element elementChildren = (Element) children.get(j);
                //��λ��<Configuration> -> <Key>  -> <Value Name="PhotoIDWidth">
                if(elementChildren.getAttributeValue("Name").equals("PhotoIDWidth")){
                    //��ȡ<Configuration> -> <Key>  -> <Value Name="PhotoIDWidth"> ����ֵ
                    System.out.print("<--------->"+elementChildren.getAttributeValue("Name"));
                    //��ȡ<Configuration> -> <Key>  -> <Value Name="PhotoIDWidth"> ��ǩ������
                    System.out.print(","+elementChildren.getText());
                }
            }
            children.clear();
            //��λ��<Configuration> -> <Key>  -> <Key>
            children = element.getChildren("Key");
            for(int j=0; j<children.size(); j++){
                Element elementChildren = (Element)children.get(j);
                //��λ��<Configuration> -> <Key>  -> <Key Name="Child">
                if(elementChildren.getAttributeValue("Name").equals("Child")){
                    //��λ��<Configuration> -> <Key>  -> <Key Name="Child"> -> <Value>
                    childrens = elementChildren.getChildren("Value");
                    for(int k=0; k<childrens.size(); k++){
                        Element elementChildrens = (Element)childrens.get(k);
                        //��λ��<Configuration> -> <Key>  -> <Key Name="Child"> -> <Value Name="HeadPercent">
                        if(elementChildrens.getAttributeValue("Name").equals("HeadPercent")){
                            System.out.println("<--------->"+elementChildrens.getText());
                        }
                    }
                }
            }
        }
    }
}
