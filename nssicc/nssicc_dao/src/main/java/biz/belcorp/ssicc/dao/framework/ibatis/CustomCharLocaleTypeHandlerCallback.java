/*
 * Created on 14/04/2005 03:13:17 PM
 *
 * biz.belcorp.ssicc.dao.ibatis.CustomCharLocaleTypeHandlerCallback
 */
package biz.belcorp.ssicc.dao.framework.ibatis;

import java.sql.SQLException;
import java.util.Locale;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;

/**
 * TODO Include class description here.
 * <p>
 * <a href="CustomCharLocaleTypeHandlerCallback.java.html"> <i>View Source </i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public class CustomCharLocaleTypeHandlerCallback implements TypeHandlerCallback {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibatis.sqlmap.client.extensions.TypeHandlerCallback#setParameter(com.ibatis.sqlmap.client.extensions.ParameterSetter,
	 *      java.lang.Object)
	 */
	public void setParameter(ParameterSetter setter, Object parameter)
			throws SQLException {
		String language = ((Locale) parameter).getLanguage();
		setter.setString(language);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibatis.sqlmap.client.extensions.TypeHandlerCallback#getResult(com.ibatis.sqlmap.client.extensions.ResultGetter)
	 */
	public Object getResult(ResultGetter getter) throws SQLException {
		String s = getter.getString();
		return new Locale(s);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibatis.sqlmap.client.extensions.TypeHandlerCallback#valueOf(java.lang.String)
	 */
	public Object valueOf(String s) {
		return new Locale(s);
	}

}



