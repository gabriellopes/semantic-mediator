package br.arida.ufc.core.util;

import jdk.nashorn.internal.runtime.options.LoggingOption;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.logging.Logger;

/**
 * Created by gabriellopes9102 on 29/11/2016.
 */

public class XMLHelper {


    public static Node readXML(String str) throws IOException, SAXException, ParserConfigurationException {
        return fromString(str);
    }

    public static Node readXML(File file) throws IOException, SAXException, ParserConfigurationException {
        return fromString(file.getAbsolutePath());
    }


    private static Node fromString(String str) throws ParserConfigurationException, IOException, SAXException {
        Document document = null;
        DocumentBuilder builder = null;
        DocumentBuilderFactory factory = null;


        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        document = (Document) builder.parse(str);

        return (Node) document.getDocumentElement();
    }

    public static File toXML(String str, String output_path) throws TransformerException, IOException {
        File new_file = new File(output_path);
        FileWriter f = new FileWriter(new_file);
        f.write(str);

        f.close();
        /*Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource();
        new StreamSource(new FileReader(new_file));
        StreamResult result = new StreamResult(new_file);
        transformer.transform(source, result);
*/
        return new_file;
    }

}

