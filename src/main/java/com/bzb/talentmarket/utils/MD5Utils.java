package com.bzb.talentmarket.utils;

import java.security.MessageDigest;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * 
 * ClassName：MD5Util 
 * @Description: MD5加密工具
 * @author：BJC
 * @date 2016年7月13日
 *
 */
public class MD5Utils {
	
	private static final Logger log = LoggerFactory.getLogger(MD5Utils.class);
	
	private static String SY_ACCESSKEY;
	
	/**
	 * 
	 * @Description: Md5加密
	 * @param @param s
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author BJC
	 * @date 2016年7月13日
	 *
	 */
	public final static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = s.getBytes("utf-8");
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
       }
	}
	
	/**
	 * 
	 * @Title: generateSignature 
	 * @Description: 生成签名
	 * @param params 参数列表， 必须存在timestamp和nonstr
	 * @return  
	 * @return String 
	 * @author bzb  
	 * @date 2018年5月23日 下午5:15:43
	 * @throws
	 */
	public static String generateSignature(Map<String, Object> params) {
		System.out.println("生成签名");
		log.info("待签名参数, params={}", params);
		
		if (params == null || params.isEmpty()) {
			throw new RuntimeException("待签名参数为空");
		}
		
		// 校验参数中是否存在时间戳和随机字符串
		if (!params.containsKey("timestamp")) {
			throw new RuntimeException("时间戳不存在");
		}
		if (!params.containsKey("nonstr")) {
			throw new RuntimeException("随机字符串不存在");
		}
		
		
		// 将参数params进行升序排列
		params = new TreeMap<>(params);
		log.info("排序后参数={}", params);
		System.out.println(params);
		
		// 将排序后的key按照key1value1...的形式拼接
		StringBuilder sb = new StringBuilder();
		for (Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();
			Object valueObj= entry.getValue();
			String value = valueObj == null ? "" : String.valueOf(valueObj);
			sb.append(key).append(value);
		}
		
		// 密钥，暂定所有客户端使用同一个密钥 
		String secret = SY_ACCESSKEY; 
		
		// 将密钥拼接到头部和尾部进行md5签名，并转换为大写
		String signature = new StringBuffer().append(secret).append(sb).append(secret).toString();
		log.info("代签名字符串={}", signature);
		System.out.println(signature);
		
		signature = MD5(signature).toUpperCase();
		log.info("signature={}", signature);
		System.out.println(signature);
		
		return signature;
	}
	
	public static final void main(String[] args){
		//System.out.println(MD5("123456"));
		
		// 组装参数http://cqys.tunnel.qydev.com/SUBSCRIBE/indexAction!index?qydm=BZBKHD&dealerid=b4dad91d93884468a74687e248814dce&proid=&timestamp=1527069376597&nonstr=8b3aa928240b4f0da92aab794fb522b6&signature=B61A049E34EDC846262E32998AFC161E
		/*Map<String, Object> params = new HashMap<>();
		params.put("qydm", "BZBKHD");
		params.put("dealerid", "b4dad91d93884468a74687e248814dce");
		params.put("proid", "");
		params.put("timestamp", 1527069376597L);
		params.put("nonstr", "8b3aa928240b4f0da92aab794fb522b6");
		generateSignature(params);*/
		
		System.out.println(MD5(""));
		
 	}

	@Value("${SY_ACCESSKEY}")
	public void setSY_ACCESSKEY(String sY_ACCESSKEY) {
		SY_ACCESSKEY = sY_ACCESSKEY;
	}
}