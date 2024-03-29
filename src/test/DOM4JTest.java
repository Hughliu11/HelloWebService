package test;

import java.awt.print.Book;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import org.jdom2.input.SAXBuilder;

import beans.BookBean;;

public class DOM4JTest {
    private static ArrayList<Book> bookList = new ArrayList<Book>();
    /**
     * @param args
     */
    public static void main(String[] args) {
        // 解析books.xml文件
        // 创建SAXReader的对象reader
    	
        SAXReader reader = new SAXReader();
        try {
            // 通过reader对象的read方法加载books.xml文件,获取docuemnt对象。
            Document document = reader.read(new File("book.xml"));
            // 通过document对象获取根节点bookstore
            Element bookStore = document.getRootElement();
            // 通过element对象的elementIterator方法获取迭代器
            Iterator it = bookStore.elementIterator();
            
            
            List<BookBean> bookBeanList=new ArrayList<BookBean>();
            // 遍历迭代器，获取根节点中的信息（书籍）
            while (it.hasNext()) {
                System.out.println("=====开始遍历某一本书=====");
                Element book = (Element) it.next();
                
                // 获取book的属性名以及 属性值
                List<Attribute> bookAttrs = book.attributes();
                for (Attribute attr : bookAttrs) {
                    System.out.println("属性名：" + attr.getName() + "--属性值："
                            + attr.getValue());
                }
                Iterator itt = book.elementIterator();
                
                BookBean bookBean=new BookBean();
                
                while (itt.hasNext()) {
                    Element bookChild = (Element) itt.next();
                    //System.out.println("节点名：" + bookChild.getName() + "--节点值：" + bookChild.getStringValue());
                    if (bookChild.getName().toString().equals(bookBean.NAME)) {
                    	bookBean.setName(bookChild.getStringValue());
					}
                    if (bookChild.getName().equals(bookBean.AUTHOR)) {
                    	bookBean.setAuthor(bookChild.getStringValue());
					}
                    if (bookChild.getName().equals(bookBean.YEAR)) {
                    	bookBean.setYear(bookChild.getStringValue());
					}
                    if (bookChild.getName().equals(bookBean.PRICE)) {
                    	bookBean.setPrice(bookChild.getStringValue());
					}                                   
                }
                bookBeanList.add(bookBean);
                System.out.println(bookBean.toString());
                System.out.println("=====结束遍历某一本书=====");
                
            }
            System.out.println(bookBeanList.toString());
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
