package biz.belcorp.ssicc.web.framework.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

import biz.belcorp.ssicc.dao.model.MenuOpciones;

@FacesConverter(value="menuOpcionesPickListConverter")
public class MenuOpcionesPickListConverter implements Converter{
	   
	   @Override
	   public Object getAsObject(FacesContext facesContext, UIComponent component, String object) {
	       Object ret = null;
	       if (component instanceof PickList) {
	           Object dualList = ((PickList) component).getValue();
	           DualListModel<MenuOpciones> dl = (DualListModel<MenuOpciones>) dualList;
	           for (Object o : dl.getSource()) {
	               String cod = ((MenuOpciones) o).getCodigoOpciones();
	               if (object.equals(cod)) {
	                   ret = o;
	                   break;
	               }
	           }
	           if (ret == null)
	               for (Object o : dl.getTarget()) {
	                   String cod = ((MenuOpciones) o).getCodigoOpciones();
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
	       if (object instanceof MenuOpciones) {
	           str = ((MenuOpciones) object).getCodigoOpciones();
	       }
	       return str;
	   }
}


