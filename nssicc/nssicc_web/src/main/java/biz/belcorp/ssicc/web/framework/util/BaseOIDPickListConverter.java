package biz.belcorp.ssicc.web.framework.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.sisicc.model.BaseOID;


@FacesConverter(value="baseOIDPickListConverter")
public class BaseOIDPickListConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String object) {
		 	
			Object ret = null;
	       if (component instanceof PickList) {
	           Object dualList = ((PickList) component).getValue();
	           DualListModel<BaseOID> dl = (DualListModel<BaseOID>) dualList;
	           for (Object o : dl.getSource()) {
	        	   Integer mcod=((BaseOID) o).getOid();
	               String cod = Integer.toString(mcod);
	               if (object.equals(cod)) {
	                   ret = o;
	                   break;
	               }
	           }
	           if (ret == null)
	               for (Object o : dl.getTarget()) {
	            	   Integer mcod=((BaseOID) o).getOid();
		               String cod = Integer.toString(mcod);
	                   if (object.equals(cod)) {
	                       ret = o;
	                       break;
	                   }
	               }
	       }
	       return ret;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object object) {
		 	String str = "";
	       if (object instanceof BaseOID) {
	    	   Integer mcod=((BaseOID) object).getOid();
	           str = Integer.toString(mcod);
	       }
	       return str;
	}

}
