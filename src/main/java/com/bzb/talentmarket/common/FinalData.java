package com.bzb.talentmarket.common;

/**
 * @author bzb
 * @Description:
 * @date 2018/9/25 17:01
 */
public class FinalData {

    public static class Choreboy {

        // 收支类型
        public static final int MONEY_CLASS_IN = 0; // 收入
        public static final int MONEY_CLASS_OUT = 1; // 支出

        // 状态
        public static final int STATUS_ON = 1; // 启用
        public static final int STATUS_OFF = 0; // 禁用
    }
    
    public static class Wx {
    	// 消息类型
    	public static final String MSGTYPE_EVENT = "event"; // 事件类型
    	public static final String MSGTYPE_TEXT = "text"; // 文本消息
    	public static final String MSGTYPE_IMAGE = "image"; // 图片消息
    	public static final String MSGTYPE_VOICE = "voice"; // 语音消息
    	public static final String MSGTYPE_VIDEO = "video"; // 视频消息
    	public static final String MSGTYPE_SHORTVIDEO = "shortvideo"; // 小视频消息
    	public static final String MSGTYPE_LINK = "link"; // 链接消息
    	public static final String MSGTYPE_TRANSFER_CUSTOMER_SERVICE = "transfer_customer_service"; // 客服消息

    	// 事件类型
		public static final String EVENT_SUBSCIBE = "subscribe"; // 订阅
		public static final String EVENT_UNSUBSCRIBE = "unsubscribe";// 取消订阅
        public static final String EVENT_LOCATION = "LOCATION"; // 地理位置上报

        // 扫码事件， 用户未关注时的事件推送为subscribe
        public static final String EVENT_SCAN = "SCAN"; // 用户已关注时的事件推送为SCAN
        
        // 微信转账到零钱，校验用户姓名选项
        public static final String CHECK_NAME_NO_CHECK = "NO_CHECK"; // 不校验真实姓名 
        public static final String CHECK_NAME_FORCE_CHECK = "FORCE_CHECK"; // 强校验真实姓名
    }

    public static class Member {
        // 订阅状态
        public static final int SUBSCIBE_YES = 1; // 已订阅
        public static final int SUBSCIBE_NO = 0; // 未订阅

        // 随机红包领取状态
        public static final int REDSTATUS_DRAWED = 1; // 已领取
        public static final int REDSTATUS_UNCLAINMED = 0; // 未领取

        // 红包来源
        public static final  int REDMONEY_SOURCE_SCAN_RECOMMEND = 0; // 扫码推荐

        // 是否总部经纪人
        public static final int ISAGENT_HEADER = 2; // 总部经纪人
        public static final  int ISAGENT_AGENT = 1; // 普通经济人
        public static final  int ISAGENT_FAN = 0; // 普通粉丝
    }
    
    /**
     * 
     * @Description:消息表
     * @author:bzb
     * @time:2018年11月23日 下午8:25:21
     */
    public static class Message {
    	
    	// 消息状态
    	  public static final int STATUS_UNREAD = 0; // 未读消息
          public static final int STATUS_READ = 1; // 已读消息
          public static final int STATUS_DELETE = 2; // 删除消息
          
          //消息表前缀
          public static final String TABLENAME_PRE_ACCEPT = "tb_message_accept_"; // 接受粉丝消息表
          public static final String TABLENAME_PRE_REPLY = "tb_message_reply_"; // 回复粉丝消息表
    }
}
