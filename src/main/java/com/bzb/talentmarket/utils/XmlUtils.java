package com.bzb.talentmarket.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.bzb.talentmarket.bean.wx.TextMessage;
import com.thoughtworks.xstream.XStream;

public class XmlUtils {
	
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
	public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException{
		HashMap<String, String> map = new HashMap<String,String>();
		SAXReader reader = new SAXReader();
 
		InputStream ins = request.getInputStream();
		Document doc = reader.read(ins);
 
		Element root = doc.getRootElement();
		@SuppressWarnings("unchecked")
		List<Element> list = (List<Element>)root.elements();
 
		for(Element e:list){
			map.put(e.getName(), e.getText());
		}
		ins.close();
		return map;
	}
	
	/*
	 * 文本消息对象转xml
	 */
	public static String textMsgToxml(TextMessage textMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}
}
