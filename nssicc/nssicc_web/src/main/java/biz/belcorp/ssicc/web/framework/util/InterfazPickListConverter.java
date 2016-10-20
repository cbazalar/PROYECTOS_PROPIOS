package biz.belcorp.ssicc.web.framework.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;

@FacesConverter(value="interfazPickListConverter")
public class InterfazPickListConverter implements Converter{
	   
	   @Override
	   public Object getAsObject(FacesContext facesContext, UIComponent component, String object) {
	       Object ret = null;
	       if (component instanceof PickList) {
	           Object dualList = ((PickList) component).getValue();
	           DualListModel<Interfaz> dl = (DualListModel<Interfaz>) dualList;
	           for (Object o : dl.getSource()) {
	               String cod = ((Interfaz) o).getCodigo();
	               if (object.equals(cod)) {
	                   ret = o;
	                   break;
	               }
	           }
	           if (ret == null)
	               for (Object o : dl.getTarget()) {
	                   String cod = ((Interfaz) o).getCodigo();
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
	       if (object instanceof Interfaz) {
	           str = ((Interfaz) object).getCodigo();
	       }
	       return str;
	   }
}


