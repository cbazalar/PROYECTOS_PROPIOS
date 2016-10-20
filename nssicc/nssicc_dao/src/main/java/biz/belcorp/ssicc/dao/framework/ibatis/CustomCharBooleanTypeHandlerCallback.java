/*
 * Created on 21/03/2005 09:13:41 AM
 *
 * biz.belcorp.ssicc.dao.ibatis.CustomCharBooleanTypeHandlerCallback
 */
package biz.belcorp.ssicc.dao.framework.ibatis;

import java.sql.SQLException;

import biz.belcorp.ssicc.dao.Constants;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;

/**
 * TODO Include class description here.
 * <p>
 * <a href="CustomCharBooleanTypeHandlerCallback.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public class CustomCharBooleanTypeHandlerCallback implements TypeHandlerCallback {
    
    private static final String YES = Constants.YES;

    private static final String NO = Constants.NO;

    /*
     * (non-Javadoc)
     * 
     * @see com.ibatis.sqlmap.client.extensions.TypeHandlerCallback#setParameter(com.ibatis.sqlmap.client.extensions.ParameterSetter,
     *      java.lang.Object)
     */
    public void setParameter(ParameterSetter setter, Object parameter)
            throws SQLException {
        boolean b = ((Boolean) parameter).booleanValue();
        if (b) {
            setter.setString(YES);
        }
        else {
            setter.setString(NO);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ibatis.sqlmap.client.extensions.TypeHandlerCallback#getResult(com.ibatis.sqlmap.client.extensions.ResultGetter)
     */
    public Object getResult(ResultGetter getter) throws SQLException {
        String s = getter.getString();
        if (s != null && YES.equalsIgnoreCase(s)) {
            return new Boolean(true);
        }
        else {
            return new Boolean(false);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ibatis.sqlmap.client.extensions.TypeHandlerCallback#valueOf(java.lang.String)
     */
    public Object valueOf(String s) {
        if (YES.equalsIgnoreCase(s)) {
            return new Boolean(true);
        }
        else {
            return new Boolean(false);
        }
    }

}


