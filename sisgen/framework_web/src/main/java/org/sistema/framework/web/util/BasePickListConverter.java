package org.sistema.framework.web.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.sistema.framework.dao.model.Base;

import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

@FacesConverter(value="basePickListConverter")
public class BasePickListConverter implements Converter{
	   
	   @Override
	   public Object getAsObject(FacesContext facesContext, UIComponent component, String object) {
	       Object ret = null;
	       if (component instanceof PickList) {
	           Object dualList = ((PickList) component).getValue();
	           DualListModel<Base> dl = (DualListModel<Base>) dualList;
	           for (Object o : dl.getSource()) {
	               String cod = ((Base) o).getCodigo();
	               if (object.equals(cod)) {
	                   ret = o;
	                   break;
	               }
	           }
	           if (ret == null)
	               for (Object o : dl.getTarget()) {
	                   String cod = ((Base) o).getCodigo();
	                   if (object.equals(cod)) {
	                       ret = o;
	                       break;
	                   }
	               }
	       }
	       return ret;
	   }

	   @Override
	   public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
	       String str = "";
	       if (object instanceof Base) {
	           str = ((Base) object).getCodigo();
	       }
	       return str;
	   }
}


