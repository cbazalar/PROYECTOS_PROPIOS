package biz.belcorp.ssicc.web.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Rol;

public final class CommonUtilValue {

	public static Map armandoDisplaytag(List lista) {
		Map retorno = new HashMap();
		
		java.util.ArrayList displayitems = new java.util.ArrayList();
		String[] onombre = new String[0];
		String cod = null;
		String nom = null;
		for (int i = 0; i < lista.size(); i++) {
			java.util.HashMap hx = new java.util.HashMap();
			hx = (java.util.HashMap) lista.get(i);
			String indice = hx.get("rnum") != null ? String.valueOf(hx.get("rnum")) : null;
			String[] ocodigo = hx.get("ocodigo") != null ? String.valueOf(hx.get("ocodigo")).split(",") : null;
			onombre = new String[ocodigo.length];
			onombre = hx.get("onombre") != null ? String.valueOf(hx.get("onombre")).split(",") : null;
			String[] disponible = hx.get("disponibles") != null ? String.valueOf(hx.get("disponibles")).split(",") : null;
			String[] activos = hx.get("activos") != null ? String.valueOf(hx.get("activos")).split(",") : null;

			Rol [] accesosDisponibles = new Rol[onombre.length + 1];
			accesosDisponibles[0] = new Rol();
			accesosDisponibles[0].setEstado(Constants.NO);
			accesosDisponibles[0].setCodigo("");
			accesosDisponibles[0].setDescripcion(MapUtils.getString(hx, "descripcionMenu", ""));
			
			String codigoMenu = MapUtils.getString(hx, "codigoMenu", "");
			
			for (int h = 0; h < onombre.length; h++) {
				accesosDisponibles[h + 1] = new Rol();
				accesosDisponibles[h + 1].setEstado(Constants.NO);
				accesosDisponibles[h + 1].setCodigo("");
				accesosDisponibles[h + 1].setDescripcion("");
				
				cod = String.valueOf(onombre[h]).substring(0, ocodigo[0].length());
				nom = String.valueOf(onombre[h]).substring(5, String.valueOf(onombre[h]).length());
				if (disponible != null) {
					for (int n = 0; n < disponible.length; n++) {
						if (cod.equalsIgnoreCase(String.valueOf(disponible[n]))) {
							accesosDisponibles[h + 1].setEstado(verificarEstadoCheck(activos, disponible[n], indice));
							accesosDisponibles[h + 1].setCodigo(String.format("%s_%s", codigoMenu, disponible[n]));
							accesosDisponibles[h + 1].setDescripcion(nom);
						}
					}
				}
			}

			displayitems.add(accesosDisponibles);
		}
		
		java.util.Map id1 = new java.util.HashMap();
		id1.put("property", "descripcionMenu");
		id1.put("title", "DESCRIPCION");
		java.util.List set1 = new java.util.ArrayList();
		set1.add(id1);

		for (int j = 0; j < onombre.length; j++) {
			java.util.Map mx = new java.util.HashMap();
			String nombre = String.valueOf(onombre[j]).substring(5, String.valueOf(onombre[j]).length());
			mx.put("property", nombre);

			mx.put("title", nombre);
			set1.add(mx);
		}
		
		retorno.put("displayitems", displayitems);
		retorno.put("collist", set1);
		
		return retorno;
	}

	public static String verificarEstadoCheck(String[] activos, String disponibles, String indice) {
		String checked = null;
		if (activos != null) {
			for (int x = 0; x < activos.length; x++) {
				if (disponibles.equalsIgnoreCase(String.valueOf(activos[x]))) {
					checked = Constants.SI;
					break;
				} else {
					checked = Constants.NO;
				}
			}
		} else {
			checked = Constants.NO;
		}
		
		return checked;
	}
}
