package com.dazi.core.common.utils.redis;

public class RedisKey {
	
	/**
	 * 订单token
	 * [type=string]
	 */
	public static String getToken(String userId) {
		return "umall:token:"+userId;
	}
	
	/**
	 * 活动key
	 * [type=string]
	 */
	public static String getOnlineActiveById(Integer activeId) {
		return "umall:active:"+activeId;
	}
	
	/**
	 * 根据游戏获取活动(活动跟游戏一对一关系)
	 * [type=string]
	 */
	public static String getOnlineActiveByGameId(Integer gameId) {
		return "umall:active:online:"+gameId;
	}
	
	/**
	 * 获取模板数据
	 * [type=string]
	 */
	public static String getTemplateById(Integer templateId) {
		return "umall:template:"+templateId;
	}

	/**
     * 获得代金券模板数据
     * [type=set, member=voucherTemplate]
     * @param gameId
     * @return
     */
	public static String getVoucherTemplateByTid(Integer templateId) {
		return "umall:vtemplate:template:"+templateId;
	}
	
	/**
	 * 获取折扣信息
	 * [type=set, member=discount]
	 */
	public static String getDiscountByTid(Integer activeId) {
		return "umall:discount:active:"+activeId;
	}
	
	/**
	 * 获取代金券模板数据
	 * [type=string]
	 */
	public static String getVoucherTemplateById(Integer vtId) {
		return "umall:vourcherTemplate:"+vtId;
	}
	
	/**
	 * 根据用户id,游戏id获取对应包id
	 * [type=string]
	 */
	public static String getCpId(Long userId, Integer gameId) {
		return "umall:cpId:" + userId + ":" + gameId;
	}
}
