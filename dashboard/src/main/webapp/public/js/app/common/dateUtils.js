/**
 * Created by Administrator on 2016/6/6.
 */

/**
 *
 * @author linguozhi@52tt.com
 * @desc 时间工具类
 * @date 2016-06-06
 */
var dateUtils = {
    /**
     * 判断两个时间月份是否相同
     * @param m1 moment类型
     * @param m2 moment类型
     */
    equalMonth : function(m1, m2) {
        if(m1.month() == m2.month()) {
            return true;
        }

        return false;
    }
}
