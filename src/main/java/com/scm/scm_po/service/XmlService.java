package com.scm.scm_po.service;

import com.scm.scm_po.model.Detail;
import com.scm.scm_po.model.Header;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

@Service
public class XmlService {

    public void generateXml(List<Header> headers) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        Element root = doc.createElement("HEADERS");
        doc.appendChild(root);

        for (Header h : headers) {
            Element header = doc.createElement("HEADER");
            root.appendChild(header);

            addElement(doc, header, "PO_NUMBER", h.getPoNumber());
            addElement(doc, header, "PO_DATE", h.getPoDate().toString());
            addElement(doc, header, "BUYER_NAME", h.getBuyerName());
            addElement(doc, header, "BUYER_ADDR", h.getBuyerAddr());

            Element details = doc.createElement("DETAILS");
            header.appendChild(details);

            for (Detail d : h.getDetails()) {
                Element detail = doc.createElement("DETAIL");
                details.appendChild(detail);

                addElement(doc, detail, "PART_NO", d.getPartNo());
                addElement(doc, detail, "PART_NAME", d.getPartName());
                addElement(doc, detail, "QTY", d.getQty().toString());
                addElement(doc, detail, "UNIT", d.getUnit());
                addElement(doc, detail, "PRICE", d.getPrice().toString());
            }
        }

        Transformer tf = TransformerFactory.newInstance().newTransformer();
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        tf.transform(new DOMSource(doc), new StreamResult(new File("D:/SCM/OUTBOX/po_data.xml")));
    }

    private void addElement(Document doc, Element parent, String name, String value) {
        Element el = doc.createElement(name);
        el.appendChild(doc.createTextNode(value));
        parent.appendChild(el);
    }
}

