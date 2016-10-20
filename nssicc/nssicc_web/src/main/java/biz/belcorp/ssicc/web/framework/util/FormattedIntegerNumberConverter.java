package biz.belcorp.ssicc.web.framework.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * TODO Include class description here.
 * <p>
 * <a href="IntegerNumberConverter.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:ivan.tocto@gmail.com">Ivan Tocto Jaimes</a>
 */

@FacesConverter("formattedIntegerNumberConverter")
public class FormattedIntegerNumberConverter implements Converter {

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return value;
	}

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		Locale locale = new Locale("en", "US");
		double number = 0;
		
		if(value instanceof String)
		{
			if(NumberUtils.isNumber((String)value))
			{
				number = Double.parseDouble((String)value);
			}
			else
				return "";
		}
		else
		{
			number = Double.parseDouble(String.valueOf((String)value));
		}
		
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
		DecimalFormat formatter = new DecimalFormat("###,###,###", symbols);
		formatter.setMaximumFractionDigits(0);
		
		return formatter.format(number);
		
	}

}
