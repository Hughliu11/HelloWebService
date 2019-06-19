package test;

import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

//读取people.xml文档
public class Readxml {

    public static void main(String[] args) {
        //新建构造器解析xml
        SAXBuilder sax = new SAXBuilder();
        //建一个文档去接受数据
        Document doc;
        try {
            //获取people.xml文档
            doc = sax.build("people.xml");
            //获得根节点
            Element people = doc.getRootElement();
            //获得根节点下的节点数据
            List<Element> list = people.getChildren();
            for(int i = 0;i<list.size();i++){
                Element e = list.get(i);
                //获得属性值
                System.out.println("name："+e.getAttributeValue("name")+"   salary："+e.getAttributeValue("salary"));
                //获得文本值
                System.out.println(e.getText());
            }
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}