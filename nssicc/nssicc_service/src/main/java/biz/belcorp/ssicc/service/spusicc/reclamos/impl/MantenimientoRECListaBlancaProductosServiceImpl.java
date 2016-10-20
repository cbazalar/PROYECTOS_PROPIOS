
package biz.belcorp.ssicc.service.spusicc.reclamos.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECListaBlancaProductosDAO;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.ListaBlancaProductos;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECListaBlancaProductosService;

@Service("spusicc.mantenimientoRECListaBlancaProductosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoRECListaBlancaProductosServiceImpl extends BaseService implements	MantenimientoRECListaBlancaProductosService {
	
	@Resource(name="spusicc.mantenimientoRECListaBlancaProductosDAO")
	MantenimientoRECListaBlancaProductosDAO mantenimientoRECListaBlancaProductosDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECListaBlancaProductosService#getListMotivoDevolucion()
	 */
	public List getListMotivoDevolucion() {
		return mantenimientoRECListaBlancaProductosDAO.getListMotivoDevolucion();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECListaBlancaProductosService#getListaBlancaProductosList(java.util.Map)
	 */
	public List getListaBlancaProductosList(Map criteria) {
		return mantenimientoRECListaBlancaProductosDAO.getListaBlancaProductosList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECListaBlancaProductosService#insertListaBlancaProductos(java.util.Map)
	 */
	public String insertListaBlancaProductos(Map criteria) {
		log.debug("Entro a MantenimientoRECListaBlancaProductosServiceImpl - insertListaBlancaProductos(java.util.Map)");
		StringBuilder lineaError = new StringBuilder();
		List listaCodigosVentaArchivo = (List) criteria.get("listaCodigoVentaArchivo");
		List listaCodigoClienteArchivo =  (List) criteria.get("listaCodigoClienteArchivo");
		String[] listaTipoOperacion = (String[])criteria.get("codigoTipoOperacion");
		String[] listaRegion = (String[]) criteria.get("codigoRegionList");
		String[] listaZona = (String[]) criteria.get("codigoZonaList");
		String[] codigosOperacion = null;
		
		//Lista de Tipos de Operacion
		for (int i = 0; i < listaTipoOperacion.length; i++) {
			codigosOperacion = listaTipoOperacion[i].split("-");
			criteria.put("codigoOperacion", codigosOperacion[0].toString());
			criteria.put("codigoTipoOperacion", codigosOperacion[1].toString());
			if(listaCodigosVentaArchivo != null && !listaCodigosVentaArchivo.isEmpty()){
				for(int j=0;j<listaCodigosVentaArchivo.size();j++){
					criteria.put("codigoVenta", listaCodigosVentaArchivo.get(j).toString());
					if(listaCodigoClienteArchivo != null && !listaCodigoClienteArchivo.isEmpty()){
						for(int l=0;l<listaCodigoClienteArchivo.size();l++){
							criteria.put("codigoCliente", listaCodigoClienteArchivo.get(l).toString());
							if (listaRegion != null && listaRegion.length > 0 ) {
								//recorrido de las zonas
								for (int p = 0; p < listaRegion.length; p++) {
									for (int k = 0; k < listaZona.length; k++) {
										criteria.put("codigoRegion", listaRegion[p].toString());
										criteria.put("codigoZona", listaZona[k].toString());
										//1 = Region Pertenece a Periodo, 0 = Region no pertenece a Region
										if (mantenimientoRECListaBlancaProductosDAO.getValidaRegionxZona(criteria) == 0)
											continue;
										//Validar e insertar a la tabla
										if (mantenimientoRECListaBlancaProductosDAO.getValidaListaBlanca(criteria) > 0) {
											lineaError.append("El registro con codigo de Venta " + listaCodigosVentaArchivo.get(j).toString() + " y codigo de Cliente " + listaCodigoClienteArchivo.get(l).toString() + " ya se encuentra registrado "+"<br/>");
										}else 
											mantenimientoRECListaBlancaProductosDAO.insertListaBlancaProductos(criteria);
									}	
								}
							}else {
								if (mantenimientoRECListaBlancaProductosDAO.getValidaListaBlanca(criteria) > 0) {
									lineaError.append("El registro con codigo de Venta " + listaCodigosVentaArchivo.get(j).toString() + " y codigo de Cliente " + listaCodigoClienteArchivo.get(l).toString() + " ya se encuentra registrado "+"<br/>");
								}else 
									mantenimientoRECListaBlancaProductosDAO.insertListaBlancaProductos(criteria);
							}
						}
					}else{
						String codigoCliente = (String)criteria.get("codigoCliente");
						if (listaRegion != null && listaRegion.length > 0 ) {
							//recorrido de las zonas
							for (int p = 0; p < listaRegion.length; p++) {
								for (int k = 0; k < listaZona.length; k++) {
									criteria.put("codigoRegion", listaRegion[p].toString());
									criteria.put("codigoZona", listaZona[k].toString());
									//1 = Region Pertenece a Periodo, 0 = Region no pertenece a Region
									if (mantenimientoRECListaBlancaProductosDAO.getValidaRegionxZona(criteria) == 0)
										continue;
									if (mantenimientoRECListaBlancaProductosDAO.getValidaListaBlanca(criteria) > 0) {
										lineaError.append("El registro con codigo de Venta " + listaCodigosVentaArchivo.get(j).toString() + ((StringUtils.isEmpty(codigoCliente)) ? "ya se encuentra registrado" : " y codigo de Cliente " + codigoCliente + " ya se encuentra registrado "+"<br/>"));
									}else 
										mantenimientoRECListaBlancaProductosDAO.insertListaBlancaProductos(criteria);
								}
							}
						}else {
							if (mantenimientoRECListaBlancaProductosDAO.getValidaListaBlanca(criteria) > 0) {
								lineaError.append("El registro con codigo de Venta " + listaCodigosVentaArchivo.get(j).toString() + ((StringUtils.isEmpty(codigoCliente)) ? "ya se encuentra registrado" : " y codigo de Cliente " + codigoCliente + " ya se encuentra registrado "+"<br/>"));
							}else 
								mantenimientoRECListaBlancaProductosDAO.insertListaBlancaProductos(criteria);
						}
					}
				}
			}else{
				String codigoVenta = (String)criteria.get("codigoVenta");
				if(listaCodigoClienteArchivo != null && !listaCodigoClienteArchivo.isEmpty()){
					for(int l=0;l<listaCodigoClienteArchivo.size();l++){
						criteria.put("codigoCliente", listaCodigoClienteArchivo.get(l).toString());
						if (listaRegion != null && listaRegion.length > 0 ) {
							//recorrido de las zonas
							for (int p = 0; p < listaRegion.length; p++) {
								for (int k = 0; k < listaZona.length; k++) {
									criteria.put("codigoRegion", listaRegion[p].toString());
									criteria.put("codigoZona", listaZona[k].toString());
									//1 = Region Pertenece a Periodo, 0 = Region no pertenece a Region
									if (mantenimientoRECListaBlancaProductosDAO.getValidaRegionxZona(criteria) == 0)
										continue;
									//Validar e insertar a la tabla
									if (mantenimientoRECListaBlancaProductosDAO.getValidaListaBlanca(criteria) > 0) {
										lineaError.append("El registro con codigo de Venta " + codigoVenta + " y codigo de Cliente " + listaCodigoClienteArchivo.get(l).toString() + " ya se encuentra registrado "+"<br/>");
									}else 
										mantenimientoRECListaBlancaProductosDAO.insertListaBlancaProductos(criteria);
								}
							}
						}else {
							if (mantenimientoRECListaBlancaProductosDAO.getValidaListaBlanca(criteria) > 0) {
								lineaError.append("El registro con codigo de Venta " + codigoVenta + " y codigo de Cliente " + listaCodigoClienteArchivo.get(l).toString() + " ya se encuentra registrado "+"<br/>");
							}else 
								mantenimientoRECListaBlancaProductosDAO.insertListaBlancaProductos(criteria);
						}
					}
				}else{
					String codigoCliente = (String)criteria.get("codigoCliente");
					if (listaRegion != null && listaRegion.length > 0 ) {
						//recorrido de las zonas
						for (int p = 0; p < listaRegion.length; p++) {
							for (int k = 0; k < listaZona.length; k++) {
								criteria.put("codigoRegion", listaRegion[p].toString());
								criteria.put("codigoZona", listaZona[k].toString());
								//1 = Region Pertenece a Periodo, 0 = Region no pertenece a Region
								if (mantenimientoRECListaBlancaProductosDAO.getValidaRegionxZona(criteria) == 0)
									continue;
								if (mantenimientoRECListaBlancaProductosDAO.getValidaListaBlanca(criteria) > 0) {
									lineaError.append("El registro con codigo de Venta " + codigoVenta + ((StringUtils.isEmpty(codigoCliente)) ? " ya se encuentra registrado <br/>" : " y cdigo de Cliente " + codigoCliente + " ya se encuentra registrado "+"<br/>"));
								}else 
									mantenimientoRECListaBlancaProductosDAO.insertListaBlancaProductos(criteria);
							}
						}
					}else {
						if (mantenimientoRECListaBlancaProductosDAO.getValidaListaBlanca(criteria) > 0) {
							lineaError.append("El registro con codigo de Venta " + codigoVenta + ((StringUtils.isEmpty(codigoCliente)) ? " ya se encuentra registrado <br/>" : " y cdigo de Cliente " + codigoCliente + " ya se encuentra registrado "+"<br/>"));
						}else 
							mantenimientoRECListaBlancaProductosDAO.insertListaBlancaProductos(criteria);
					}
				}
			}
		}
		
		return lineaError.toString() ;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECListaBlancaProductosService#updateListaBlancaProductos(java.util.Map)
	 */
	public String updateListaBlancaProductos(Map criteria) {
		StringBuilder lineaError = new StringBuilder();
		String codigoVenta = (String)criteria.get("codigoVenta");
		String codigoCliente = (String)criteria.get("codigoCliente");
		
		if (mantenimientoRECListaBlancaProductosDAO.getValidaListaBlanca(criteria) > 0) {
			lineaError.append("El registro con cdigo de Venta " + codigoVenta + ((StringUtils.isEmpty(codigoCliente)) ? "<br/>" : " y cdigo de Cliente " + codigoCliente + " ya se encuentra registrado "+"<br/>"));
		}else {
			mantenimientoRECListaBlancaProductosDAO.updateListaBlancaProductos(criteria);
		}
		return lineaError.toString();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECListaBlancaProductosService#consultarListaBlancaProductos(java.util.Map)
	 */
	public ListaBlancaProductos consultarListaBlancaProductos(Map criteria) {
		return mantenimientoRECListaBlancaProductosDAO.consultarListaBlancaProductos(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECListaBlancaProductosService#deleteListaBlancaProductos(java.util.Map)
	 */
	public void deleteListaBlancaProductos(Map criteria) {
		mantenimientoRECListaBlancaProductosDAO.deleteListaBlancaProductos(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECListaBlancaProductosService#getValidaCodigoCliente(java.lang.String)
	 */
	public Integer getValidaCodigoCliente(String value) {
		return mantenimientoRECListaBlancaProductosDAO.getValidaCodigoCliente(value);
	}

}
