package lib;

import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.logging.log4j.LogManager;

public class LogFactory {
	private Logger log;
	private String filepath=null;
	
	
	public LogFactory(String className) {
		 log = LogManager.getLogger(className);
//		 filepath=System.getProperty("user.dir")+"\\src\\test\\resources\\log4j2.xml";
//		 System.out.println(filepath);
//		 File xml= new File(filepath);
//		 DocumentBuilderFactory dbFact= DocumentBuilderFactory.newInstance();
//		 DocumentBuilder db;
//		 try{
//			 db= dbFact.newDocumentBuilder();
//			 Document doc= db.parse(xml);
//			 doc.getDocumentElement().normalize();
//			 updateProperty(doc, className);
//		 }catch(Exception e) {
//			 
//		 }
	}
	
	private void updateProperty(Document doc, String className) {
		NodeList prop = doc.getElementsByTagName("Property");
		Element emp = null;
		Node n = null;
		// looping through all Property tags
		for (int i = 0; i < prop.getLength(); i++) {
			emp = (Element) prop.item(i);
			n=prop.item(i);
			String classnm = emp.getAttribute("name");
//			System.out.println(n.getTextContent());
			if (classnm.equalsIgnoreCase("classname")) {
				// updating the node value to current class name
				System.out.println(n.getTextContent());
				n.setTextContent(className);
				System.out.println(n.getTextContent());
			}
		}
	}

	public void trace(String msg) {
		log.trace(msg);
	}

	public void debug(String msg) {
		log.debug(msg);
	}

	public void info(String msg) {
		this.log.info(msg);
	}

	public void warn(String msg) {
		log.warn(msg);
	}
	public void warn(String msg,Exception e) {
		log.warn(msg,e);
		
	}

	public void error(String msg) {
		log.error(msg);
	}
	public void error(String msg,Exception e) {
		log.error(msg,e);
	}

	public void fatal(String msg) {
		log.fatal(msg);
	}
	public void fatal(String msg,Exception e) {
		log.fatal(msg,e);
		
	}

	public  void a() {
		System.out.println(this.getClass().getName() );
		System.out.println(this.getClass().getPackageName() );
	}
//	@SuppressWarnings("static-access")
//	public static void main(String[] args) {
//		new LogFactory(className).a();
//		new LogFactory(className).trace("This is a traces message");
//		new LogFactory(className).debug("This is a debug message");
//		new LogFactory(className).info("This is a information");
//		new LogFactory(className).warn("This is a warning");
//		new LogFactory(className).error("This is a error message");
//		new LogFactory(className).fatal("This is a fatal error message");
//		
//	}
}
