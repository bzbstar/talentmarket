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
    	
    	// 事件类型
		public static String EVENT_SUBSCIBE = "subscribe"; // 订阅
		public static String EVENT_UNSUBSCRIBE = "unsubscribe";// 取消订阅
        public static String EVENT_LOCATION = "LOCATION"; // 地理位置上报

        // 扫码事件， 用户未关注时的事件推送为subscribe
        public static String EVENT_SCAN = "SCAN"; // 用户已关注时的事件推送为SCAN
    }

    public static class Member {
        // 订阅状态
        public static int SUBSCIBE_YES = 1; // 已订阅
        public static int SUBSCIBE_NO = 0; // 未订阅

        // 随机红包领取状态
        public static int REDSTATUS_DRAWED = 1; // 已领取
        public static int REDSTATUS_UNCLAINMED = 0; // 未领取
    }
}
