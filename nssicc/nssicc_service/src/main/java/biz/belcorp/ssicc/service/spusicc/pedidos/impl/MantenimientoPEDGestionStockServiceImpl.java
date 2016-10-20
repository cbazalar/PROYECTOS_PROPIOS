/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoPEDGestionStockDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.DetalleCuvExistencia;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDGestionStockService;

/**
 * @author jvelasquez
 *
 */
@Service("spusicc.mantenimientoPEDGestionStockService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoPEDGestionStockServiceImpl extends BaseService implements MantenimientoPEDGestionStockService {
	
	@Resource(name="spusicc.mantenimientoPEDGestionStockDAO")
	private MantenimientoPEDGestionStockDAO mantenimientoPEDGestionStockDAO; 

	public List getGestionStockList(Map criteria) {
		return mantenimientoPEDGestionStockDAO.getGestionStockList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDGestionStockService#insertGestionStock(java.util.Map)
	 */
	public String insertGestionStock(Map criteria) {
		List listaValidacion = new ArrayList();
		String flagCargaSAP = Constants.NUMERO_CERO;
		StringBuilder lineaError = new StringBuilder();
		try {
			String[] tipoClienteList = (String[])criteria.get("tipoClienteList");
			String[] subTipoClienteList = (String[])criteria.get("subTipoCLienteList");
			String[] tipoClasificacionList = (String[])criteria.get("tipoClasificacionList");
			String[] clasificacionList = (String[])criteria.get("clasificacionList");
			
			List listaCodigos = (List) criteria.get("listaCodigos");
			
			String[] regionList = (String[])criteria.get("regionList");
			String[] zonaList = (String[]) criteria.get("zonaList");
			String codigoRegion = "";
			String codigoZona = "";

			int indicador = 0; //Indicador que servir si tengo seleccionado un campo de las Seleccin Mltiple
			int valida = 0; //Sumatoria de la validacin
			//La lista de codigos de venta solo tiene 1 CUV Vlido
			if (tipoClienteList != null && tipoClienteList.length > 0)
			{
				if(!(tipoClienteList.length == 1 && StringUtils.isBlank(tipoClienteList[0])))
					indicador = 1;
			}
				
			if (subTipoClienteList!= null && subTipoClienteList.length > 0)
			{
				if(!(subTipoClienteList.length == 1 && StringUtils.isBlank(subTipoClienteList[0])))
					indicador = 2;
			}

			if (tipoClasificacionList != null && tipoClasificacionList.length > 0)
			{
				if(!(tipoClasificacionList.length == 1 && StringUtils.isBlank(tipoClasificacionList[0])))
					indicador = 3;
			}
			
			if (clasificacionList != null && clasificacionList.length > 0) 
			{
				if(!(clasificacionList.length == 1 && StringUtils.isBlank(clasificacionList[0])))
					indicador = 4;
			}
			
			if(regionList != null && regionList.length == 1 && StringUtils.isBlank(regionList[0]))
				regionList = null;

			if(zonaList != null && zonaList.length == 1 && StringUtils.isBlank(zonaList[0]))
				zonaList = null;
			
			String tipoLista = ((String)criteria.get("tipoLista"));
			if(StringUtils.equals(tipoLista, "listaCodigosSap")){
				flagCargaSAP = Constants.NUMERO_UNO;
				String indicadorEliminacion = mantenimientoPEDGestionStockDAO.getIndicadorEliminacionCodigoSAC(criteria);
				if(StringUtils.equals(indicadorEliminacion, new String("0"))){
					mantenimientoPEDGestionStockDAO.deleteGestionStock(criteria);
				}
			}
			
			criteria.put("flagCargaSAP", flagCargaSAP);
			
			//Validar si la lista de codigo de venta tiene valores
			if (listaCodigos != null && listaCodigos.size() >0) {
				for (int z = 0; z < listaCodigos.size() ; z++) {
					DetalleCuvExistencia det = new DetalleCuvExistencia();
					det = (DetalleCuvExistencia)listaCodigos.get(z);
					
					criteria.put("oidDetalleOferta", det.getOidDetalleOferta());
					criteria.put("codigoVenta", det.getCodigoVenta());
					criteria.put("codigoSap", det.getCodigoSap());					
					
					if(StringUtils.equals(tipoLista, "listaCodigosSap")){
						listaValidacion = mantenimientoPEDGestionStockDAO.getValidarSapExistencia(criteria);
					}else{
						listaValidacion = mantenimientoPEDGestionStockDAO.getValidarCuvExistencia(criteria);
					}
					
					
					//Validar que el CUV sea Vlido para poder ser registrado.
					if (listaValidacion!= null && listaValidacion.size() > 0) {
						//Si el resultado es 0 (No se encuentra registros en la tabla PED_GESTI_STOCK)
						//if (mantenimientoPEDGestionStockDAO.getValidaGestionStockUk(criteria) == 0) {
							if (indicador == 0) {
								if (zonaList != null && zonaList.length > 0) {
									for (int i = 0; i < regionList.length; i++) {
										for (int j = 0; j < zonaList.length; j++) {
											codigoRegion = regionList[i].toString();
											codigoZona = zonaList[j].toString();
											
											criteria.put("codigoZona", codigoZona);
											criteria.put("codigoRegion", codigoRegion);
											
											//getValidaRegionxZona == 0 (La region y la Zona no pertenecen)
											if (mantenimientoPEDGestionStockDAO.getValidaRegionxZona(criteria)==0)
												continue;
											
											if (mantenimientoPEDGestionStockDAO.getValidaGestionStockUk(criteria) == 0) 
												mantenimientoPEDGestionStockDAO.insertGestionStock(criteria);
											else
												lineaError.append("El cdigo de venta "+ det.getCodigoVenta()+" ya se encuentra registrada"+"<br/>");
										}
									}
								}else if(regionList !=null && regionList.length > 0) {
									//Cuando solo seleccion REGIONES
									for (int j = 0; j < regionList.length; j++) {
										codigoRegion = regionList[j].toString();
									
										criteria.put("codigoRegion", codigoRegion);
										
										if (mantenimientoPEDGestionStockDAO.getValidaGestionStockUk(criteria) == 0) 
											mantenimientoPEDGestionStockDAO.insertGestionStock(criteria);
										else
											lineaError.append("El cdigo de venta "+ det.getCodigoVenta()+" ya se encuentra registrada"+"<br/>");
									}
								}else {
									// Cuando No he seleccionado ZONAS y REGION
									if (mantenimientoPEDGestionStockDAO.getValidaGestionStockUk(criteria) == 0) 
										mantenimientoPEDGestionStockDAO.insertGestionStock(criteria);
									else
										lineaError.append("El cdigo de venta "+ det.getCodigoVenta()+" ya se encuentra registrada"+"<br/>");
								}
							}
						
							//valida = 1 'Dato si existe en la tabla', 0 no existe
							switch (indicador) {
							case 1:
								for (int i = 0; i < tipoClienteList.length; i++) {
									criteria.put("oidTipoCliente", tipoClienteList[i].toString());
									valida = mantenimientoPEDGestionStockDAO.getOidTipoCliente(criteria);
									if (valida == indicador){
										if (zonaList != null && zonaList.length > 0) {
											for (int r = 0; r < regionList.length; r++) {
												for (int j = 0; j < zonaList.length; j++) {
													codigoRegion = regionList[r].toString();
													codigoZona = zonaList[j].toString();
													
													criteria.put("codigoZona", codigoZona);
													criteria.put("codigoRegion", codigoRegion);
													
													//getValidaRegionxZona == 0 (La region y la Zona no pertenecen)
													if (mantenimientoPEDGestionStockDAO.getValidaRegionxZona(criteria)==0)
														continue;
													
													if (mantenimientoPEDGestionStockDAO.getValidaGestionStockUk(criteria) == 0) 
														mantenimientoPEDGestionStockDAO.insertGestionStock(criteria);
													else
														lineaError.append("El cdigo de venta "+ det.getCodigoVenta()+" ya se encuentra registrada"+"<br/>");
												}
											}
										}else if(regionList !=null && regionList.length > 0) {
											//Cuando solo seleccion REGIONES
											for (int j = 0; j < regionList.length; j++) {
												codigoRegion = regionList[j].toString();
											
												criteria.put("codigoRegion", codigoRegion);
												
												if (mantenimientoPEDGestionStockDAO.getValidaGestionStockUk(criteria) == 0) 
													mantenimientoPEDGestionStockDAO.insertGestionStock(criteria);
												else
													lineaError.append("El cdigo de venta "+ det.getCodigoVenta()+" ya se encuentra registrada"+"<br/>");
											}
										}else {
											// Cuando No he seleccionado ZONAS y REGION
											if (mantenimientoPEDGestionStockDAO.getValidaGestionStockUk(criteria) == 0) 
												mantenimientoPEDGestionStockDAO.insertGestionStock(criteria);
											else
												lineaError.append("El cdigo de venta "+ det.getCodigoVenta()+" ya se encuentra registrada"+"<br/>");
										}
									}
								}
								break;
							case 2:
									for (int i = 0; i < tipoClienteList.length; i++) {
										for (int k = 0; k < subTipoClienteList.length; k++) {
											criteria.put("oidTipoCliente", tipoClienteList[i].toString());
											criteria.put("oidSubTipoCliente", subTipoClienteList[k].toString());
											valida = mantenimientoPEDGestionStockDAO.getOidTipoCliente(criteria);
											valida = valida + mantenimientoPEDGestionStockDAO.getOidSubTipoCliente(criteria);
											if (valida == indicador){
												if (zonaList != null && zonaList.length > 0) {
													for (int r = 0; r < regionList.length; r++) {
														for (int j = 0; j < zonaList.length; j++) {
															codigoRegion = regionList[r].toString();
															codigoZona = zonaList[j].toString();
															
															criteria.put("codigoZona", codigoZona);
															criteria.put("codigoRegion", codigoRegion);
															
															//getValidaRegionxZona == 0 (La region y la Zona no pertenecen)
															if (mantenimientoPEDGestionStockDAO.getValidaRegionxZona(criteria)==0)
																continue;
															
															if (mantenimientoPEDGestionStockDAO.getValidaGestionStockUk(criteria) == 0) 
																mantenimientoPEDGestionStockDAO.insertGestionStock(criteria);
															else
																lineaError.append("El cdigo de venta "+ det.getCodigoVenta()+" ya se encuentra registrada"+"<br/>");
														}
													}
												}else if(regionList !=null && regionList.length > 0) {
													//Cuando solo seleccion REGIONES
													for (int j = 0; j < regionList.length; j++) {
														codigoRegion = regionList[j].toString();
													
														criteria.put("codigoRegion", codigoRegion);
														
														if (mantenimientoPEDGestionStockDAO.getValidaGestionStockUk(criteria) == 0) 
															mantenimientoPEDGestionStockDAO.insertGestionStock(criteria);
														else
															lineaError.append("El cdigo de venta "+ det.getCodigoVenta()+" ya se encuentra registrada"+"<br/>");
													}
												}else {
													// Cuando No he seleccionado ZONAS y REGION
													if (mantenimientoPEDGestionStockDAO.getValidaGestionStockUk(criteria) == 0) 
														mantenimientoPEDGestionStockDAO.insertGestionStock(criteria);
													else
														lineaError.append("El cdigo de venta "+ det.getCodigoVenta()+" ya se encuentra registrada"+"<br/>");
												}
											}
										}
									}
									break;
							case 3:
								for (int i = 0; i < tipoClienteList.length; i++) {
									for (int k = 0; k < subTipoClienteList.length; k++) {
										for (int p = 0; p < tipoClasificacionList.length; p++) {
											criteria.put("oidTipoCliente", tipoClienteList[i].toString());
											criteria.put("oidSubTipoCliente", subTipoClienteList[k].toString());
											criteria.put("oidTipoClasificacion", tipoClasificacionList[p].toString());
											valida = mantenimientoPEDGestionStockDAO.getOidTipoCliente(criteria);
											valida = valida + mantenimientoPEDGestionStockDAO.getOidSubTipoCliente(criteria);
											valida = valida + mantenimientoPEDGestionStockDAO.getOidTipoClasificacion(criteria);
											if (valida == indicador){
												if (zonaList != null && zonaList.length > 0) {
													for (int r = 0; r < regionList.length; r++) {
														for (int j = 0; j < zonaList.length; j++) {
															codigoRegion = regionList[r].toString();
															codigoZona = zonaList[j].toString();
															
															criteria.put("codigoZona", codigoZona);
															criteria.put("codigoRegion", codigoRegion);
															
															//getValidaRegionxZona == 0 (La region y la Zona no pertenecen)
															if (mantenimientoPEDGestionStockDAO.getValidaRegionxZona(criteria)==0)
																continue;
															
															if (mantenimientoPEDGestionStockDAO.getValidaGestionStockUk(criteria) == 0) 
																mantenimientoPEDGestionStockDAO.insertGestionStock(criteria);
															else
																lineaError.append("El cdigo de venta "+ det.getCodigoVenta()+" ya se encuentra registrada"+"<br/>");
														}
													}
												}else if(regionList !=null && regionList.length > 0) {
													//Cuando solo seleccion REGIONES
													for (int j = 0; j < regionList.length; j++) {
														codigoRegion = regionList[j].toString();
													
														criteria.put("codigoRegion", codigoRegion);
														
														if (mantenimientoPEDGestionStockDAO.getValidaGestionStockUk(criteria) == 0) 
															mantenimientoPEDGestionStockDAO.insertGestionStock(criteria);
														else
															lineaError.append("El cdigo de venta "+ det.getCodigoVenta()+" ya se encuentra registrada"+"<br/>");
													}
												}else {
													// Cuando No he seleccionado ZONAS y REGION
													if (mantenimientoPEDGestionStockDAO.getValidaGestionStockUk(criteria) == 0) 
														mantenimientoPEDGestionStockDAO.insertGestionStock(criteria);
													else
														lineaError.append("El cdigo de venta "+ det.getCodigoVenta()+" ya se encuentra registrada"+"<br/>");
												}
											}
										}
									}
								}
								break;
							case 4:
								for (int i = 0; i < tipoClienteList.length; i++) {
									for (int k = 0; k < subTipoClienteList.length; k++) {
										for (int p = 0; p < tipoClasificacionList.length; p++) {
											for (int w = 0; w < clasificacionList.length; w++) {
												criteria.put("oidTipoCliente", tipoClienteList[i].toString());
												criteria.put("oidSubTipoCliente", subTipoClienteList[k].toString());
												criteria.put("oidTipoClasificacion", tipoClasificacionList[p].toString());
												criteria.put("oidClasificacion",clasificacionList[w].toString());
												valida = mantenimientoPEDGestionStockDAO.getOidTipoCliente(criteria);
												valida = valida + mantenimientoPEDGestionStockDAO.getOidSubTipoCliente(criteria);
												valida = valida + mantenimientoPEDGestionStockDAO.getOidTipoClasificacion(criteria);
												valida = valida + mantenimientoPEDGestionStockDAO.getOidClasificacion(criteria);
												if (valida == indicador){
													if (zonaList != null && zonaList.length > 0) {
														for (int r = 0; r < regionList.length; r++) {
															for (int j = 0; j < zonaList.length; j++) {
																codigoRegion = regionList[r].toString();
																codigoZona = zonaList[j].toString();
																
																criteria.put("codigoZona", codigoZona);
																criteria.put("codigoRegion", codigoRegion);
																
																//getValidaRegionxZona == 0 (La region y la Zona no pertenecen)
																if (mantenimientoPEDGestionStockDAO.getValidaRegionxZona(criteria)==0)
																	continue;
																
																if (mantenimientoPEDGestionStockDAO.getValidaGestionStockUk(criteria) == 0) 
																	mantenimientoPEDGestionStockDAO.insertGestionStock(criteria);
																else
																	lineaError.append("El cdigo de venta "+ det.getCodigoVenta()+" ya se encuentra registrada"+"<br/>");
															}
														}
													}else if(regionList !=null && regionList.length > 0) {
														//Cuando solo seleccion REGIONES
														for (int j = 0; j < regionList.length; j++) {
															codigoRegion = regionList[j].toString();
														
															criteria.put("codigoRegion", codigoRegion);
															
															if (mantenimientoPEDGestionStockDAO.getValidaGestionStockUk(criteria) == 0) 
																mantenimientoPEDGestionStockDAO.insertGestionStock(criteria);
															else
																lineaError.append("El cdigo de venta "+ det.getCodigoVenta()+" ya se encuentra registrada"+"<br/>");
														}
													}else {
														// Cuando No he seleccionado ZONAS y REGION
														
														if (mantenimientoPEDGestionStockDAO.getValidaGestionStockUk(criteria) == 0) 
															mantenimientoPEDGestionStockDAO.insertGestionStock(criteria);
														else
															lineaError.append("El cdigo de venta "+ det.getCodigoVenta()+" ya se encuentra registrada"+"<br/>");
													}
												}
											}
										}
									}
								}
								break;
							}
						//}else {
							//lineaError.append("El cdigo de venta "+ det.getCodigoVenta()+" ya se encuentra registrada"+"<br/>");
						//}//Fin Validacion UK
					}else {
						lineaError.append("El cdigo de venta "+ det.getCodigoVenta()+" no es vlido"+"<br/>");
					}//Fin de validacion Cuv
				}
			}else {
				String codigoVenta = (String)criteria.get("codigoVenta");
				
				//Si el resultado es 0 (No se encuentra registros en la tabla PED_GESTI_STOCK)
				//if (mantenimientoPEDGestionStockDAO.getValidaGestionStockUk(criteria) == 0) {
					if (indicador == 0) {
						if (zonaList != null && zonaList.length > 0) {
							for (int i = 0; i < regionList.length; i++) {
								for (int j = 0; j < zonaList.length; j++) {
									codigoRegion = regionList[i].toString();
									codigoZona = zonaList[j].toString();
									
									criteria.put("codigoZona", codigoZona);
									criteria.put("codigoRegion", codigoRegion);
									
									//getValidaRegionxZona == 0 (La region y la Zona no pertenecen)
									if (mantenimientoPEDGestionStockDAO.getValidaRegionxZona(criteria)==0)
										continue;
									
									if (mantenimientoPEDGestionStockDAO.getValidaGestionStockUk(criteria) == 0) 
										mantenimientoPEDGestionStockDAO.insertGestionStock(criteria);
									else
										lineaError.append("El cdigo de venta "+ codigoVenta+" ya se encuentra registrada"+"<br/>");
									
								}
							}
						}else if(regionList !=null && regionList.length > 0) {
							//Cuando solo seleccion REGIONES
							for (int j = 0; j < regionList.length; j++) {
								codigoRegion = regionList[j].toString();
							
								criteria.put("codigoRegion", codigoRegion);
								
								if (mantenimientoPEDGestionStockDAO.getValidaGestionStockUk(criteria) == 0) 
									mantenimientoPEDGestionStockDAO.insertGestionStock(criteria);
								else
									lineaError.append("El cdigo de venta "+ codigoVenta+" ya se encuentra registrada"+"<br/>");
							}
						}else {
							// Cuando No he seleccionado ZONAS y REGION
							if (mantenimientoPEDGestionStockDAO.getValidaGestionStockUk(criteria) == 0) 
								mantenimientoPEDGestionStockDAO.insertGestionStock(criteria);
							else
								lineaError.append("El cdigo de venta "+ codigoVenta+" ya se encuentra registrada"+"<br/>");
						}
					}
				
					//valida = 1 'Dato si existe en la tabla', 0 no existe
					switch (indicador) {
					case 1:
						for (int i = 0; i < tipoClienteList.length; i++) {
							criteria.put("oidTipoCliente", tipoClienteList[i].toString());
							valida = mantenimientoPEDGestionStockDAO.getOidTipoCliente(criteria);
							if (valida == indicador){
								if (zonaList != null && zonaList.length > 0) {
									for (int r = 0; r < regionList.length; r++) {
										for (int j = 0; j < zonaList.length; j++) {
											codigoRegion = regionList[r].toString();
											codigoZona = zonaList[j].toString();
											
											criteria.put("codigoZona", codigoZona);
											criteria.put("codigoRegion", codigoRegion);
											
											//getValidaRegionxZona == 0 (La region y la Zona no pertenecen)
											if (mantenimientoPEDGestionStockDAO.getValidaRegionxZona(criteria)==0)
												continue;
											
											if (mantenimientoPEDGestionStockDAO.getValidaGestionStockUk(criteria) == 0) 
												mantenimientoPEDGestionStockDAO.insertGestionStock(criteria);
											else
												lineaError.append("El cdigo de venta "+ codigoVenta+" ya se encuentra registrada"+"<br/>");
										}
									}
								}else if(regionList !=null && regionList.length > 0) {
									//Cuando solo seleccion REGIONES
									for (int j = 0; j < regionList.length; j++) {
										codigoRegion = regionList[j].toString();
									
										criteria.put("codigoRegion", codigoRegion);
										
										if (mantenimientoPEDGestionStockDAO.getValidaGestionStockUk(criteria) == 0) 
											mantenimientoPEDGestionStockDAO.insertGestionStock(criteria);
										else
											lineaError.append("El cdigo de venta "+ codigoVenta+" ya se encuentra registrada"+"<br/>");
									}
								}else {
									// Cuando No he seleccionado ZONAS y REGION
									if (mantenimientoPEDGestionStockDAO.getValidaGestionStockUk(criteria) == 0) 
										mantenimientoPEDGestionStockDAO.insertGestionStock(criteria);
									else
										lineaError.append("El cdigo de venta "+ codigoVenta+" ya se encuentra registrada"+"<br/>");
								}
							}
						}
						break;
					case 2:
							for (int i = 0; i < tipoClienteList.length; i++) {
								for (int k = 0; k < subTipoClienteList.length; k++) {
									criteria.put("oidTipoCliente", tipoClienteList[i].toString());
									criteria.put("oidSubTipoCliente", subTipoClienteList[k].toString());
									valida = mantenimientoPEDGestionStockDAO.getOidTipoCliente(criteria);
									valida = valida + mantenimientoPEDGestionStockDAO.getOidSubTipoCliente(criteria);
									if (valida == indicador){
										if (zonaList != null && zonaList.length > 0) {
											for (int r = 0; r < regionList.length; r++) {
												for (int j = 0; j < zonaList.length; j++) {
													codigoRegion = regionList[r].toString();
													codigoZona = zonaList[j].toString();
													
													criteria.put("codigoZona", codigoZona);
													criteria.put("codigoRegion", codigoRegion);
													
													//getValidaRegionxZona == 0 (La region y la Zona no pertenecen)
													if (mantenimientoPEDGestionStockDAO.getValidaRegionxZona(criteria)==0)
														continue;
													
													if (mantenimientoPEDGestionStockDAO.getValidaGestionStockUk(criteria) == 0) 
														mantenimientoPEDGestionStockDAO.insertGestionStock(criteria);
													else
														lineaError.append("El cdigo de venta "+ codigoVenta+" ya se encuentra registrada"+"<br/>");
												}
											}
										}else if(regionList !=null && regionList.length > 0) {
											//Cuando solo seleccion REGIONES
											for (int j = 0; j < regionList.length; j++) {
												codigoRegion = regionList[j].toString();
											
												criteria.put("codigoRegion", codigoRegion);
												
												if (mantenimientoPEDGestionStockDAO.getValidaGestionStockUk(criteria) == 0) 
													mantenimientoPEDGestionStockDAO.insertGestionStock(criteria);
												else
													lineaError.append("El cdigo de venta "+ codigoVenta+" ya se encuentra registrada"+"<br/>");
											}
										}else {
											// Cuando No he seleccionado ZONAS y REGION
											if (mantenimientoPEDGestionStockDAO.getValidaGestionStockUk(criteria) == 0) 
												mantenimientoPEDGestionStockDAO.insertGestionStock(criteria);
											else
												lineaError.append("El cdigo de venta "+ codigoVenta+" ya se encuentra registrada"+"<br/>");
										}
									}
								}
							}
							break;
					case 3:
						for (int i = 0; i < tipoClienteList.length; i++) {
							for (int k = 0; k < subTipoClienteList.length; k++) {
								for (int p = 0; p < tipoClasificacionList.length; p++) {
									criteria.put("oidTipoCliente", tipoClienteList[i].toString());
									criteria.put("oidSubTipoCliente", subTipoClienteList[k].toString());
									criteria.put("oidTipoClasificacion", tipoClasificacionList[p].toString());
									valida = mantenimientoPEDGestionStockDAO.getOidTipoCliente(criteria);
									valida = valida + mantenimientoPEDGestionStockDAO.getOidSubTipoCliente(criteria);
									valida = valida + mantenimientoPEDGestionStockDAO.getOidTipoClasificacion(criteria);
									if (valida == indicador){
										if (zonaList != null && zonaList.length > 0) {
											for (int r = 0; r < regionList.length; r++) {
												for (int j = 0; j < zonaList.length; j++) {
													codigoRegion = regionList[r].toString();
													codigoZona = zonaList[j].toString();
													
													criteria.put("codigoZona", codigoZona);
													criteria.put("codigoRegion", codigoRegion);
													
													//getValidaRegionxZona == 0 (La region y la Zona no pertenecen)
													if (mantenimientoPEDGestionStockDAO.getValidaRegionxZona(criteria)==0)
														continue;
													
													if (mantenimientoPEDGestionStockDAO.getValidaGestionStockUk(criteria) == 0) 
														mantenimientoPEDGestionStockDAO.insertGestionStock(criteria);
													else
														lineaError.append("El cdigo de venta "+ codigoVenta+" ya se encuentra registrada"+"<br/>");
												}
											}
										}else if(regionList !=null && regionList.length > 0) {
											//Cuando solo seleccion REGIONES
											for (int j = 0; j < regionList.length; j++) {
												codigoRegion = regionList[j].toString();
											
												criteria.put("codigoRegion", codigoRegion);
												
												if (mantenimientoPEDGestionStockDAO.getValidaGestionStockUk(criteria) == 0) 
													mantenimientoPEDGestionStockDAO.insertGestionStock(criteria);
												else
													lineaError.append("El cdigo de venta "+ codigoVenta+" ya se encuentra registrada"+"<br/>");
											}
										}else {
											// Cuando No he seleccionado ZONAS y REGION
											if (mantenimientoPEDGestionStockDAO.getValidaGestionStockUk(criteria) == 0) 
												mantenimientoPEDGestionStockDAO.insertGestionStock(criteria);
											else
												lineaError.append("El cdigo de venta "+ codigoVenta+" ya se encuentra registrada"+"<br/>");
										}
									}
								}
							}
						}
						break;
					case 4:
						for (int i = 0; i < tipoClienteList.length; i++) {
							for (int k = 0; k < subTipoClienteList.length; k++) {
								for (int p = 0; p < tipoClasificacionList.length; p++) {
									for (int w = 0; w < clasificacionList.length; w++) {
										criteria.put("oidTipoCliente", tipoClienteList[i].toString());
										criteria.put("oidSubTipoCliente", subTipoClienteList[k].toString());
										criteria.put("oidTipoClasificacion", tipoClasificacionList[p].toString());
										criteria.put("oidClasificacion",clasificacionList[w].toString());
										valida = mantenimientoPEDGestionStockDAO.getOidTipoCliente(criteria);
										valida = valida + mantenimientoPEDGestionStockDAO.getOidSubTipoCliente(criteria);
										valida = valida + mantenimientoPEDGestionStockDAO.getOidTipoClasificacion(criteria);
										valida = valida + mantenimientoPEDGestionStockDAO.getOidClasificacion(criteria);
										if (valida == indicador){
											if (zonaList != null && zonaList.length > 0) {
												for (int r = 0; r < regionList.length; r++) {
													for (int j = 0; j < zonaList.length; j++) {
														codigoRegion = regionList[r].toString();
														codigoZona = zonaList[j].toString();
														
														criteria.put("codigoZona", codigoZona);
														criteria.put("codigoRegion", codigoRegion);
														
														//getValidaRegionxZona == 0 (La region y la Zona no pertenecen)
														if (mantenimientoPEDGestionStockDAO.getValidaRegionxZona(criteria)==0)
															continue;
														
														if (mantenimientoPEDGestionStockDAO.getValidaGestionStockUk(criteria) == 0) 
															mantenimientoPEDGestionStockDAO.insertGestionStock(criteria);
														else
															lineaError.append("El cdigo de venta "+ codigoVenta+" ya se encuentra registrada"+"<br/>");
													}
												}
											}else if(regionList !=null && regionList.length > 0) {
												//Cuando solo seleccion REGIONES
												for (int j = 0; j < regionList.length; j++) {
													codigoRegion = regionList[j].toString();
												
													criteria.put("codigoRegion", codigoRegion);
													
													if (mantenimientoPEDGestionStockDAO.getValidaGestionStockUk(criteria) == 0) 
														mantenimientoPEDGestionStockDAO.insertGestionStock(criteria);
													else
														lineaError.append("El cdigo de venta "+ codigoVenta+" ya se encuentra registrada"+"<br/>");
												}
											}else {
												// Cuando No he seleccionado ZONAS y REGION
												if (mantenimientoPEDGestionStockDAO.getValidaGestionStockUk(criteria) == 0) 
													mantenimientoPEDGestionStockDAO.insertGestionStock(criteria);
												else
													lineaError.append("El cdigo de venta "+ codigoVenta+" ya se encuentra registrada"+"<br/>");
											}
										}
									}
								}
							}
						}
						break;
					}
				//}else {
					//lineaError.append("El cdigo de venta "+ codigoVenta+" ya se encuentra registrada" +"<br/>");
				//}//Fin Validacion UK}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lineaError.toString();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDGestionStockService#getValidarCuvExistencia(java.util.Map)
	 */
	public List getValidarCuvExistencia(Map criteria) {
		return mantenimientoPEDGestionStockDAO.getValidarCuvExistencia(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDGestionStockService#updateDesactivarGestionStock(java.util.Map)
	 */
	public void updateDesactivarGestionStock(Map criteria) {
		mantenimientoPEDGestionStockDAO.updateDesactivarGestionStock(criteria);
		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDGestionStockService#updateActivarGestionStock(java.util.Map)
	 */
	public void updateActivarGestionStock(Map criteria) {
		mantenimientoPEDGestionStockDAO.updateActivarGestionStock(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDGestionStockService#getValidarSapExistencia(java.util.Map)
	 */
	public List getValidarSapExistencia(Map criteria) {
		return mantenimientoPEDGestionStockDAO.getValidarSapExistencia(criteria);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDGestionStockService#updateEliminarGestionStock(java.util.Map)
	 */
	public void updateEliminarGestionStock(Map criteria) {
		mantenimientoPEDGestionStockDAO.deleteEliminarGestionStock(criteria);
		mantenimientoPEDGestionStockDAO.updateEliminarGestionStock(criteria);
	}
	
}
