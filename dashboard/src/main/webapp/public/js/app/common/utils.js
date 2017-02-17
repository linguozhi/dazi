
/**
 * 工具类
 *
 * @author linguzhi@52tt.com
 * @since 2016-05-31
 * @version 1.0.0
 */
var utils = {
	/**
	 * 判断是否为空
	 */
	empty : function(e) {
		if (e == null || e == "") {
			return true;
		}
		return false;
	},
	/**
	 * 判断是否为空数组
	 */
	emptys : function(e) {
		if (e == null || e.length < 1) {
			return true;
		}
		return false;
	},
	/**
	 * 未定义
	 */
	undefine : function(e) {
		if (utils.empty(e)) {
			return true;
		}
		return typeof (e) == 'undefined';
	},
	/**
	 * 元素长度
	 */
	length : function(e) {
		if (utils.undefine(e)) {
			return 0;
		}
		return e.length;
	},
	/**
	 * 分割
	 */
	split : function(e, s) {
		if (utils.undefine(e)) {
			return "";
		}
		return ("" + e).split(s);
	}
}

/**
 * 协议工具类
 */
var protocols = {
	/**
	 * 判断是否成功
	 */
	isSuccess : function(obj) {
		if (utils.empty(obj) || utils.undefine(obj.state)
				|| utils.undefine(obj.state.code)) {
			return false;
		}
		return obj.state.code == 2000000;
	},
	/**
	 * 获取状态信息
	 */
	getMessage : function(obj) {
		if (utils.empty(obj) || utils.undefine(obj.state)
				|| utils.undefine(obj.state.message)) {
			return "";
		}
		return obj.state.message;
	},
	/**
	 * 获取data
	 */
	getDate : function(obj) {
		if (utils.empty(obj) || utils.undefine(obj.data)) {
			return "";
		}
		return obj.data;
	}
}

var dates = {
	/**
	 * 获取当前往前N个月数组
	 */
	preMonths : function(n) {
		if (n && n > 0) {
			var result = new Array();
			for (var i = 0; i < n; i++) {
				result[i] = dates.preMonth(i);
			}
			return result;
		}
	},
	/**
	 * 获取前n月的年月
	 */
	preMonth : function(pre) {
		var datePre = new Date();
		var nowYearPre = datePre.getFullYear();
		var nowMonthPre = datePre.getMonth() + 1;
		var preMonth = 0;
		var result = "";
		var nyear = Math.floor(pre / 12); // 需要向前nyear个年
		var nmon = pre % 12; // 需要向前nmon个月
		if (nmon >= nowMonthPre) { // 向前月数大于等于当前月时，向前年数加一。
			nyear++;
		}
		if (nmon <= nowMonthPre - 1) { // 向前月数小于等于今年已过月数
			preMonth = nowMonthPre - nmon;
		} else {
			preMonth = 12 - (nmon - (nowMonthPre - 1)) + 1; // 今天已过m个月，需再向前去n-m个月。
		}
		result = (nowYearPre - nyear) + "-" + dates.getMonInMM(preMonth);
		return result;
	},
	/**
	 * 返回MM格式的月
	 */
    getMonInMM : function(mon){
        if(mon < 10){
            return "0"+mon;
        }else{
            return mon;
        }
    },
	
	getCurrentDateTime : function() { 
		//获取当前时间
		var now= new Date();
		var _month = ( 10 > (now.getMonth()+1) ) ? '0' + (now.getMonth()+1) : now.getMonth()+1;
		var _day = ( 10 > now.getDate() ) ? '0' + now.getDate() : now.getDate();
		var _hour = ( 10 > now.getHours() ) ? '0' + now.getHours() : now.getHours();
		var _minute = ( 10 > now.getMinutes() ) ? '0' + now.getMinutes() : now.getMinutes();
		var _second = ( 10 > now.getSeconds() ) ? '0' + now.getSeconds() : now.getSeconds();
		return now.getFullYear() + '-' + _month + '-' + _day + ' ' + _hour + ':' + _minute + ':' + _second;
		}
}

