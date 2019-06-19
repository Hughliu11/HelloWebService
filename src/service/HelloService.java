
package service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import beans.BookBean;
import dao.CURDdata;
import impl.insertDataImpl;

public class HelloService {

	public final String Receice_SOXmlName = "E:\\EclipseWorkSpace\\HelloWorldWS\\SalesOrderXml.xml";
	public final String Receice_SOXmlUnicode = "UTF-8";
	 
	public String createSalesOrder(String xml) throws InterruptedException {

			
			try {
				// 保存接收到的xml
				write(Receice_SOXmlName, xml, Receice_SOXmlUnicode);
				
				//解析xml
				//parsXml();
																
				CURDdata dao=new insertDataImpl();
																			
				dao.insertBookData(parseXML(Receice_SOXmlName));
				 				
				
			} catch (IOException e) {
				e.printStackTrace();
			}

		return  xml;
	}

	/**
	 * 保存接收到内容
	 * 
	 * @param path
	 * @param content
	 * @param encoding
	 * @throws IOException
	 */
	private void write(String path, String content, String encoding) throws IOException {

		try {
			File file = new File(path);
			file.delete();
			file.createNewFile();

			BufferedWriter writer;
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), encoding));
			writer.write(content);
			writer.close();
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	private List<BookBean> parseXML(String path) {
		
		SAXReader reader=new SAXReader();
		List<BookBean> bookBeanList=new ArrayList<BookBean>();
		try {
			// 通过reader对象的read方法加载books.xml文件,获取docuemnt对象。
            org.dom4j.Document document = reader.read(new File(path));
            // 通过document对象获取根节点bookstore
            org.dom4j.Element bookStore = document.getRootElement();
            // 通过element对象的elementIterator方法获取迭代器
            Iterator it = bookStore.elementIterator();           
                        
            // 遍历迭代器，获取根节点中的信息（书籍）
            while (it.hasNext()) {
                System.out.println("=====开始遍历某一本书=====");
                Element book = (Element) it.next();
                
                // 获取book的属性名以及 属性值
                List<Attribute> bookAttrs = ((org.dom4j.Element) book).attributes();
                for (Attribute attr : bookAttrs) {
                    System.out.println("属性名：" + attr.getName() + "--属性值："
                            + attr.getValue());
                }
                Iterator itt = ((org.dom4j.Element) book).elementIterator();
                
                BookBean bookBean=new BookBean();
                
                while (itt.hasNext()) {
                    Element bookChild = (Element) itt.next();
                    //System.out.println("节点名：" + bookChild.getName() + "--节点值：" + bookChild.getStringValue());
                    if (bookChild.getName().toString().equals(bookBean.NAME)) {
                    	bookBean.setName((bookChild).getStringValue());
					}
                    if (bookChild.getName().equals(bookBean.AUTHOR)) {
                    	bookBean.setAuthor(( bookChild).getStringValue());
					}
                    if (bookChild.getName().equals(bookBean.YEAR)) {
                    	bookBean.setYear((bookChild).getStringValue());
					}
                    if (bookChild.getName().equals(bookBean.PRICE)) {
                    	bookBean.setPrice((bookChild).getStringValue());
					}                                   
                }
                bookBeanList.add(bookBean);
                System.out.println(bookBean.toString());
                System.out.println("=====结束遍历某一本书=====");
                
            }
            System.out.println(bookBeanList.toString());
            
            
		} catch (DocumentException  e) {
			// TODO: handle exception
		}
		return bookBeanList;		
		
	}
	
	/**
	 * 解析XML文件
	 */
//	private void parsXml() {
//		 SAXBuilder sb = new SAXBuilder();
//	        //构造文档对象
//	        Document doc;
//			try {
//				doc = sb.build(new FileInputStream(new File(Receice_SOXmlName)));
//			
//	       // Document doc = sb.build(Test.class.getClassLoader().getResourceAsStream("nation.xml"));
//	        //获取根元素
//	        Element root = doc.getRootElement();
//	        //定位到<Configuration> -> <Key>
//	        List<Element> list = root.getChildren("Key");
//	        List<Element> children = new ArrayList<Element>();
//	        List<Element> childrens = new ArrayList<Element>();
//	        for (int i = 0; i < list.size(); i++) {
//	            Element element = (Element) list.get(i);
//	            System.out.print(element.getAttributeValue("Name"));
//	            //定位到<Configuration> -> <Key>  -> <Value>
//	            children = element.getChildren("Value"); 
//	            for(int j=0; j<children.size(); j++){
//	                Element elementChildren = (Element) children.get(j);
//	                //定位到<Configuration> -> <Key>  -> <Value Name="PhotoIDWidth">
//	                if(elementChildren.getAttributeValue("Name").equals("PhotoIDWidth")){
//	                    //获取<Configuration> -> <Key>  -> <Value Name="PhotoIDWidth"> 属性值
//	                    System.out.print("<--------->"+elementChildren.getAttributeValue("Name"));
//	                    //获取<Configuration> -> <Key>  -> <Value Name="PhotoIDWidth"> 标签里内容
//	                    System.out.print(","+elementChildren.getText());
//	                }
//	            }
//	            children.clear();
//	            //定位到<Configuration> -> <Key>  -> <Key>
//	            children = element.getChildren("Key");
//	            for(int j=0; j<children.size(); j++){
//	                Element elementChildren = (Element)children.get(j);
//	                //定位到<Configuration> -> <Key>  -> <Key Name="Child">
//	                if(elementChildren.getAttributeValue("Name").equals("Child")){
//	                    //定位到<Configuration> -> <Key>  -> <Key Name="Child"> -> <Value>
//	                    childrens = elementChildren.getChildren("Value");
//	                    for(int k=0; k<childrens.size(); k++){
//	                        Element elementChildrens = (Element)childrens.get(k);
//	                        //定位到<Configuration> -> <Key>  -> <Key Name="Child"> -> <Value Name="HeadPercent">
//	                        if(elementChildrens.getAttributeValue("Name").equals("HeadPercent")){
//	                            System.out.println("<--------->"+elementChildrens.getText());
//	                        }
//	                    }
//	                }
//	            }
//	        }
//	        } catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (JDOMException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}	   		
//	}

	/**
	 * helloWorld (方法名必须小写)
	 * 
	 * @param name
	 * @return
	 * @throws InterruptedException
	 */
	public String helloWorld(String name) throws InterruptedException {
		return "hello " + name;
	}
}