package tw.com.tendebank.utils;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * 物件工具
 * @author Edison
 *
 */
public class ObjectUtils {

	public static boolean isObjNotBlank(Object obj) {
		return !isObjBlank(obj);
	}
	
	public static boolean isObjBlank(Object obj) {
		if(obj==null) {
			return true;
		}else if(obj instanceof String){
		    return ((String) obj).isEmpty();
		}else if(obj instanceof CharSequence) {
			return StringUtils.isBlank((CharSequence)obj);
		} else if(obj instanceof Collection) {
			return ((Collection<?>)obj).isEmpty();
		} else if(obj instanceof Map) {
			return ((Map<?, ?>)obj).isEmpty();
		} else if(obj instanceof Object[]) {
			return ((Object[])obj).length==0;
		}
		return false;
	}
	/**
	 * 全部為空值就為true,只要一個為非空就false
	 * @param Object[]
	 * @return
	 */
	public static boolean isBlankByAnd(Object... objs) {
		for(Object obj:objs) {
			if(!isObjBlank(obj)) {
				return false;
			}
		}
		return true;
	}
	/**
	 * 只要有一個為空就為true,全部為非空就為false
	 * @param Object[]
	 * @return
	 */
	public static boolean isBlankByOr(Object... objs) {
		for(Object obj:objs) {
			if(isObjBlank(obj)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 全部為非空就為true,只要一個為空就false
	 * @param Object[]
	 * @return
	 */
	public static boolean notBlankByAnd(Object... objs) {
		return !isBlankByOr(objs);
	}
	/**
	 * 只要一個為非空就為true,全部為空就為false
	 * @param Object[]
	 * @return
	 */
	public static boolean notBlankByOr(Object... objs) {
		return !isBlankByAnd(objs);
	}
}
