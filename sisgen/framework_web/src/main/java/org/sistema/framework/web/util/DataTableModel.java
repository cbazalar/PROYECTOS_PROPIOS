package org.sistema.framework.web.util;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

/**
 * Clase Base utilizada para todos aquellos datatable de seleccion (sea simple / como multiple)
 * @author pecbazalar
 *
 */
public class DataTableModel extends ListDataModel<Object> implements SelectableDataModel<Object> {    
  
    /**
     * 
     */
    public DataTableModel() {  
    }  
  
    /**
     * @param data
     */
    public DataTableModel(List<Object> data) {  
        super(data);  
    }

	/* (non-Javadoc)
	 * @see org.primefaces.model.SelectableDataModel#getRowData(java.lang.String)
	 */
	@Override
	public Object getRowData(String rowKey) {
    	List lista = (List) getWrappedData();  
         
        for(int i=0; i < lista.size(); i++) {
        	Object registro = (Object)lista.get(i);
//        	String valor = registro.toString();
        	int intValor = registro.hashCode();
        	Integer iValor = new Integer(intValor);
        	String valor = iValor.toString();
            if(valor.equals(rowKey))  
                return registro;  
        }  
		return null;
	}

	@Override
	public Object getRowKey(Object arg0) {
		// TODO Auto-generated method stub
		String devuelve = arg0.toString();
		return devuelve;
	}
}    
