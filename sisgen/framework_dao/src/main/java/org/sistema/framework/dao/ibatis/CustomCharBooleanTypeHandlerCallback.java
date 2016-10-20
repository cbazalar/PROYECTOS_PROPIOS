/*
 * Created on 21/03/2005 09:13:41 AM
 *
 * org.sistema.framework.dao.ibatis.CustomCharBooleanTypeHandlerCallback
 */
package org.sistema.framework.dao.ibatis;

import java.sql.SQLException;

import org.sistema.framework.dao.Constants;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;

/**
 * TODO Include class description here.
 * <p>
 * <a href="CustomCharBooleanTypeHandlerCallback.java.html"> <i>View Source </i> </a>
 * </p>
 * 
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


