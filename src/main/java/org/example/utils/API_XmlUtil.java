package org.example.utils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class API_XmlUtil {
// Load XML file as String (existing method)

    public static String loadXmlAsString (String path) throws IOException {
    try (InputStream is = API_XmlUtil.class
            .getClassLoader()
            .getResourceAsStream(path)) {
    BufferedReader reader = new BufferedReader(new InputStreamReader(is));

    StringBuilder sb = new StringBuilder();
    String line;

    while ((line = reader.readLine()) != null) {
        sb.append(line).append("\n");
    }
        return sb.toString();
} catch (Exception e) {
        throw new RuntimeException("Unable to load XML file", e);
    }
}


//Update XML tag value dynamically
    public static String updateXmlTagValue(String xml,String tagName,String newValue)
{
    try {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(xml)));
        Node node = document.getElementsByTagName(tagName).item(0);
        if (node!=null)
        {
            node.setTextContent(newValue);
        }
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(document), new StreamResult(writer));
        return writer.toString();
    } catch (Exception e) {
        throw new RuntimeException("Unable to update XML tag value: "+tagName, e);
    }
    }
}