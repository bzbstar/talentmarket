package com.bzb.talentmarket.common;

/**
 * @author bzb
 * @Description:
 * @date 2018/9/25 17:01
 */
public class FinalData {

    public static class Choreboy {

        // 收支类型
        public static int MONEY_CLASS_IN = 0; // 收入
        public static int MONEY_CLASS_OUT = 1; // 支出

        // 状态
        public static int STATUS_ON = 1; // 启用
        public static int STATUS_OFF = 0; // 禁用
    }
    
    public static class Wx {
    	// 消息类型
    	public static String MSGTYPE_EVENT = "event"; // 事件类型
    	public static String MSGTYPE_TEXT = "text"; // 文本消息
    	public static String MSGTYPE_IMAGE = "image"; // 图片消息
    	public static String MSGTYPE_VOICE = "voice"; // 语音消息
    	public static String MSGTYPE_VIDEO = "video"; // 视频消息
    	public static String MSGTYPE_SHORTVIDEO = "shortvideo"; // 小视频消息
    	public static String MSGTYPE_LINK = "link"; // 链接消息
    	public static String MSGTYPE_TRANSFER_CUSTOMER_SERVICE = "transfer_customer_service"; // 客服消息

    	// 事件类型
		public static String EVENT_SUBSCIBE = "subscribe"; // 订阅
		public static String EVENT_UNSUBSCRIBE = "unsubscribe";// 取消订阅
        public static String EVENT_LOCATION = "LOCATION"; // 地理位置上报

        // 扫码事件， 用户未关注时的事件推送为subscribe
        public static String EVENT_SCAN = "SCAN"; // 用户已关注时的事件推送为SCAN
        
        // 微信转账到零钱，校验用户姓名选项
        public static String CHECK_NAME_NO_CHECK = "NO_CHECK"; // 不校验真实姓名 
        public static String CHECK_NAME_FORCE_CHECK = "FORCE_CHECK"; // 强校验真实姓名
    }

    public static class Member {
        // 订阅状态
        public static int SUBSCIBE_YES = 1; // 已订阅
        public static int SUBSCIBE_NO = 0; // 未订阅

        // 随机红包领取状态
        public static int REDSTATUS_DRAWED = 1; // 已领取
        public static int REDSTATUS_UNCLAINMED = 0; // 未领取

        // 红包来源
        public static int REDMONEY_SOURCE_SCAN_RECOMMEND = 0; // 扫码推荐

        // 是否总部经纪人
        public static int HEADER_AGENT = 2; // 总部经纪人
        public static int COMMON_AGENT = 1; // 普通经济人
        public static int NO_AGENT = 0; // 普通粉丝
    }
}
