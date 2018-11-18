package com.bzb.talentmarket.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.NodeList;

import com.bzb.talentmarket.bean.wx.KfMessage;
import com.bzb.talentmarket.bean.wx.TextMessage;
import com.bzb.talentmarket.bean.wx.TransInfo;
import com.thoughtworks.xstream.XStream;

public class XmlUtils {

	private static final Logger log = LoggerFactory.getLogger(XmlUtils.class);

	/**
	 * 
	 * @Description: xml转Map
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 * @exception:
	 * @author: bzb
	 * @time:2018年11月11日 下午9:43:40
	 */
	public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {
		HashMap<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();

		InputStream ins = request.getInputStream();
		Document doc = reader.read(ins);

		Element root = doc.getRootElement();
		@SuppressWarnings("unchecked")
		List<Element> list = (List<Element>) root.elements();

		for (Element e : list) {
			map.put(e.getName(), e.getText());
		}
		ins.close();
		return map;
	}

	/*
	 * 文本消息对象转xml
	 */
	public static String textMsgToxml(TextMessage textMessage) {
		XStream xstream = new XStream();
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}

	/**
	 * 将Object转化为XML字符串
	 * 
	 * @param     <T>
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static <T> String toXML(T obj) {
		XStream xstream = new XStream();
		xstream.alias("xml", obj.getClass());
		return xstream.toXML(obj);
	}

	/**
	 * 将Map转换为XML格式的字符串
	 *
	 * @param data Map类型数据
	 * @return XML格式的字符串
	 * @throws Exception
	 */
	public static String toXml(Map<String, String> data) {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = null;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		org.w3c.dom.Document document = documentBuilder.newDocument();
		org.w3c.dom.Element root = document.createElement("xml");
		document.appendChild(root);
		for (String key : data.keySet()) {
			String value = data.get(key);
			if (value == null) {
				value = "";
			}
			value = value.trim();
			org.w3c.dom.Element filed = document.createElement(key);
			filed.appendChild(document.createTextNode(value));
			root.appendChild(filed);
		}
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			transformer = tf.newTransformer();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
		DOMSource source = new DOMSource(document);
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		String output = writer.getBuffer().toString(); // .replaceAll("\n|\r", "");
		try {
			writer.close();
		} catch (Exception ex) {
		}
		return output;
	}

	/*
	 * 将SortedMap<Object,Object> 集合转化成 xml格式
	 */
	public static String toXml(SortedMap<Object, Object> parameters) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = String.valueOf(entry.getValue());
			if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {
				sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
			} else {
				sb.append("<" + k + ">" + v + "</" + k + ">");
			}
		}
		sb.append("</xml>");
		return sb.toString();
	}

	/**
	 * XML格式字符串转换为Map
	 *
	 * @param strXML XML字符串
	 * @return XML数据转换后的Map
	 * @throws Exception
	 */
	public static Map<String, String> xmlToMap(String strXML) throws Exception {
		try {
			Map<String, String> data = new HashMap<String, String>();
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			InputStream stream = new ByteArrayInputStream(strXML.getBytes("UTF-8"));
			org.w3c.dom.Document doc = documentBuilder.parse(stream);
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getDocumentElement().getChildNodes();
			for (int idx = 0; idx < nodeList.getLength(); ++idx) {
				Node node = (Node) nodeList.item(idx);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					org.w3c.dom.Element element = (org.w3c.dom.Element) node;
					data.put(element.getNodeName(), element.getTextContent());
				}
			}
			try {
				stream.close();
			} catch (Exception ex) {
				// do nothing
			}
			return data;
		} catch (Exception ex) {
			log.error("Invalid XML, can not convert to map. Error message: {}. XML content: {}", ex.getMessage(),
					strXML);
			throw ex;
		}

	}

	public static void main(String[] args) throws Exception {
		TextMessage kfMsg = new TextMessage();
		kfMsg.setToUserName("touser");
		kfMsg.setFromUserName("fromuser");
		kfMsg.setCreateTime(1399197672);
		kfMsg.setMsgType("transfer_customer_service");
		kfMsg.setContent("sdjklf");
//		System.out.println(toXML(kfMsg));
		Map<String, String> map = new HashMap<>();
		map.put("user", "1");
		map.put("hah", "123");
//		String xml = mapToXml(map);
		SortedMap<String, String> sortMap = new TreeMap<>();
		sortMap.put("user", "1");
		sortMap.put("hah", "123");
	}
}
