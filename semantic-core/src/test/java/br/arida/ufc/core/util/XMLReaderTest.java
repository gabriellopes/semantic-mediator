package br.arida.ufc.core.util;

import junit.framework.TestCase;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by gabriellopes9102 on 29/11/2016.
 */
public class XMLReaderTest extends TestCase{

    public void testToXML() throws Exception {
        String str = "";
        str += "<html>" +
                "<body>"+
                "<p>Olá</p>"+
                "</body>" +
                "</html>";

        File new_file = new File("C:\\tmp\\file.xml");
        FileWriter f = new FileWriter(new_file);
        f.write(str);

        f.close();


//        XMLHelper.toXML(str,"C:\\tmp\\file.xml");

    }

    public void testXMLReader() throws Exception{
        Node root = null;
        try {
//            root = XMLHelper.readXML("C:\\Users\\gabriellopes9102\\Documents\\IdeaProjects\\semantic-mediator\\mediator_config.xml");
            root = XMLHelper.readXML("C:\\tmp\\file.xml");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        System.out.println(root.toString());

        NodeList node = root.getChildNodes();

        for(int i = 0; i < node.getLength(); i++ ){
            System.out.println(node.item(i).getNodeName());

            if(node.item(i).getNodeName().equals("views")){
                for(int j = 0; j < node.item(i).getChildNodes().getLength(); j++){
                    Node n = node.item(i).getChildNodes().item(j);
                    System.out.println(n.getNodeName());
                }
            }
        }

    }

    public static void main(String[] a){
        new XMLReaderTest();
    }
}
