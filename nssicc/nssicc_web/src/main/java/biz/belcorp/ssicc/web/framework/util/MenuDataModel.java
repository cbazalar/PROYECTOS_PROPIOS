package biz.belcorp.ssicc.web.framework.util;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import biz.belcorp.ssicc.dao.model.MenuRol;

public class MenuDataModel extends ListDataModel<MenuRol> implements SelectableDataModel<MenuRol> {

	public MenuDataModel() {
	}

	public MenuDataModel(List<MenuRol> data) {
		super(data);
	}

	@Override
	public MenuRol getRowData(String rowKey) {
		List<MenuRol> menuRols = (List<MenuRol>) getWrappedData();
		for (MenuRol menuRol : menuRols) {
			if(menuRol.equals(rowKey)){
				return menuRol;
			}
		}
		return null;
	}
	
	@Override
	public Object getRowKey(MenuRol menuRol) {
		return menuRol.getCodigo();
	}	
}
