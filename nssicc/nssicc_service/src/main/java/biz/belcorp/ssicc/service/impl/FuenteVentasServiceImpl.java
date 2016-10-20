/*
 * Created on 08-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package biz.belcorp.ssicc.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.FuenteVentasDAO;
import biz.belcorp.ssicc.dao.sisicc.model.ConsultaFuenteVentas;
import biz.belcorp.ssicc.dao.sisicc.model.EstructuraFuenteVentaPrevista;
import biz.belcorp.ssicc.dao.sisicc.model.FuenteVentas;
import biz.belcorp.ssicc.dao.sisicc.model.FuenteVentasPais;
import biz.belcorp.ssicc.dao.sisicc.model.FuenteVentasRegion;
import biz.belcorp.ssicc.dao.sisicc.model.FuenteVentasZona;
import biz.belcorp.ssicc.service.FuenteVentasService;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.util.ExcelUtil;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="FuenteVentasServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
@Service("sisicc.fuenteVentasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class FuenteVentasServiceImpl extends BaseService
		implements
			FuenteVentasService {

	@Resource(name="sisicc.fuenteVentasDAO")	
	private FuenteVentasDAO fuenteVentasDAO;


	/**
	 * Setea el DAO para la comunicacin con la capa de persistencia.
	 * 
	 * @param fuenteVentas
	 * 
	 * @see biz.belcorp.ssicc.service.FuenteVentasService#setFuenteVentasDAO(biz.belcorp.ssicc.dao.FuenteVentasDAO)
	 */
	public void setFuenteVentasDAO(FuenteVentasDAO fuenteVentasDAO) {
		this.fuenteVentasDAO = fuenteVentasDAO;
	}

	public void insertNuevaConfiguracion(Map criteria, Usuario usuario) {
		long nuevoCodigo = 0;
		FuenteVentas nuevaFuenteVentas;
		String periodoInicio = (String) criteria.get("periodoInicio");
		String periodo;
		int periodoInicioAux = Integer.parseInt(periodoInicio);

		for (int i = 1; i <= 6; i++) {
			nuevaFuenteVentas = new FuenteVentas();
			nuevoCodigo = this.getNextPK();
			log.debug("El nuevo codigo generado es:" + nuevoCodigo);
			nuevaFuenteVentas.setCodigoFuenteVentas(nuevoCodigo);
			nuevaFuenteVentas
					.setCodigoPais((String) criteria.get("codigoPais"));
			nuevaFuenteVentas.setCodigoSociedad((String) criteria
					.get("codigoSociedad"));
			nuevaFuenteVentas.setCodigoAlmacen((String) criteria
					.get("codigoAlmacen"));
			nuevaFuenteVentas.setCodigoRegion((String) criteria
					.get("codigoRegion"));
			nuevaFuenteVentas.setCodigoMarca((String) criteria
					.get("codigoMarca"));
			nuevaFuenteVentas.setCodigoCanal((String) criteria
					.get("codigoCanal"));
			nuevaFuenteVentas
					.setCodigoZona((String) criteria.get("codigoZona"));

			if (periodoInicioAux < 10)
				periodo = (String) criteria.get("codigoAnio") + "0"
						+ String.valueOf(periodoInicioAux);
			else
				periodo = (String) criteria.get("codigoAnio")
						+ String.valueOf(periodoInicioAux);

			nuevaFuenteVentas.setCodigoPeriodo(periodo);
			nuevaFuenteVentas.setActividadesIniciales(Long
					.parseLong((String) criteria.get("c" + i + "f1")));
			nuevaFuenteVentas.setIngresos(Long.parseLong((String) criteria
					.get("c" + i + "f2")));
			nuevaFuenteVentas.setReingresos(Long.parseLong((String) criteria
					.get("c" + i + "f3")));
			nuevaFuenteVentas.setEgresos(Long.parseLong((String) criteria
					.get("c" + i + "f4")));
			nuevaFuenteVentas.setPorcentajeActividad(Double
					.parseDouble((String) criteria.get("c" + i + "f5")));
			nuevaFuenteVentas.setPpu(Double.parseDouble((String) criteria
					.get("c" + i + "f6")));
			nuevaFuenteVentas.setPup(Double.parseDouble((String) criteria
					.get("c" + i + "f7")));
			nuevaFuenteVentas.setNumeroClientes(Long
					.parseLong((String) criteria.get("c" + i + "f8")));
			nuevaFuenteVentas.setEstado(Constants.ESTADO_ACTIVO);

			/*Ini efernandezo*/
			nuevaFuenteVentas.setActividadLider(Double
					.parseDouble((String) criteria.get("c" + i + "f9")));
			
			nuevaFuenteVentas.setNumeroConsultoraCliPrivilege(Long.parseLong((String) criteria
					.get("c" + i + "f10")));
			
			nuevaFuenteVentas.setNumeroClientesInsPrivilege(Long.parseLong((String) criteria
					.get("c" + i + "f11")));
			
			nuevaFuenteVentas.setNumeroClientesTrsPrivelege(Long.parseLong((String) criteria
					.get("c" + i + "f12")));
			
			nuevaFuenteVentas.setRetencionSdoPeriodo(Long.parseLong((String) criteria
					.get("c" + i + "f13")));
			
			nuevaFuenteVentas.setRetencionTerPeriodo(Long.parseLong((String) criteria
					.get("c" + i + "f14")));
			
			nuevaFuenteVentas.setRetencionCuaPeriodo(Long.parseLong((String) criteria
					.get("c" + i + "f15")));
			
			nuevaFuenteVentas.setRetencionActivas(Long.parseLong((String) criteria
					.get("c" + i + "f16")));
			
			nuevaFuenteVentas.setPorcentajeRotacion(Double
					.parseDouble((String) criteria.get("c" + i + "f17")));
			
			nuevaFuenteVentas.setPosiblesEgresos(Long.parseLong((String) criteria
					.get("c" + i + "f18")));
			
			nuevaFuenteVentas.setRetencionPosEgresos(Long.parseLong((String) criteria
					.get("c" + i + "f19")));
/*Fin efernandezo*/			
			
			this.insertFuenteVenta(nuevaFuenteVentas, usuario);

			periodoInicioAux++;
		}
	}

	public String getPeriodoInicio(String codigoRangoPeriodo) {
		return this.fuenteVentasDAO.getPeriodoInicio(codigoRangoPeriodo);
	}

	public String getPeriodoFin(String codigoRangoPeriodo) {
		return this.fuenteVentasDAO.getPeriodoFin(codigoRangoPeriodo);
	}

	public long getNextPK() {
		return this.fuenteVentasDAO.getNextPK();
	}

	public List getFuenteVentasByCriteria(Map criteria) {

		log.debug("codigoPais:" + criteria.get("codigoPais"));
		log.debug("codigoSociedad:" + criteria.get("codigoSociedad"));
		log.debug("codigoAlmacen:" + criteria.get("codigoAlmacen"));
		log.debug("codigoRegion:" + criteria.get("codigoRegion"));
		log.debug("codigoMarca:" + criteria.get("codigoMarca"));
		log.debug("codigoCanal:" + criteria.get("codigoCanal"));		

		log.debug("codigoZona:" + criteria.get("codigoZona"));
		log.debug("periodoMayor:" + criteria.get("periodoMayor"));		
		log.debug("periodoMenor:" + criteria.get("periodoMenor"));		
		
		return this.fuenteVentasDAO.getFuenteVentas(criteria);
	}

	public FuenteVentas getFuenteVenta(String primaryKey) {
		return this.fuenteVentasDAO.getFuenteVenta(primaryKey);
	}

	public void updateFuenteVentasLista(ArrayList listaFuentesVenta,
			Usuario usuario) {
		FuenteVentas fuenteVentas;
		for (int i = 0; i < listaFuentesVenta.size(); i++) {
			fuenteVentas = new FuenteVentas();
			fuenteVentas = (FuenteVentas) listaFuentesVenta.get(i);
			this.updateFuenteVenta(fuenteVentas, usuario);
		}
	}

	public ArrayList getConsultaFuenteVentasPrevistaByCriteria(Map criteria) {

		log
				.debug("====== Verificacion de datos ** getConsultaFuenteVentasPrevistaByCriteria ** ============");
		log.debug("tipoSeleccion:" + criteria.get("tipoSeleccion"));

		log.debug("codigoPais:" + criteria.get("codigoPais"));
		log.debug("codigoSociedad:" + criteria.get("codigoSociedad"));
		log.debug("codigoAlmacen:" + criteria.get("codigoAlmacen"));
		log.debug("codigoMarca:" + criteria.get("codigoMarca"));
		log.debug("codigoCanal:" + criteria.get("codigoCanal"));

		log.debug("periodoMenor:" + criteria.get("periodoMenor"));
		log.debug("periodoMayor:" + criteria.get("periodoMayor"));

		log.debug("codigoRegion:" + criteria.get("codigoRegion"));
		log.debug("codigoZona:" + criteria.get("codigoZona"));

		String tipoSeleccion = (String) criteria.get("tipoSeleccion");
		List listaFuentes = new LinkedList();
		/*if (tipoSeleccion.equals("pais"))
			listaFuentes = this.fuenteVentasDAO
					.getConsultaFuenteVentasPais(criteria);
		if (tipoSeleccion.equals("region"))
			listaFuentes = this.fuenteVentasDAO
					.getConsultaFuenteVentasRegion(criteria);
		if (tipoSeleccion.equals("zona"))*/
			listaFuentes = this.fuenteVentasDAO
					.getConsultaFuenteVentasZona(criteria);
		ArrayList listaAux = new ArrayList();

		log.debug("tamao de la lista original:" + listaFuentes.size());

		if (listaFuentes.size() == 0 || listaFuentes == null) {
		} else {
			if (listaFuentes.size() < 6) {
				ArrayList listaFuenteVentasAux = new ArrayList();
				listaFuenteVentasAux.addAll(listaFuentes);
				listaFuentes.clear();
				ConsultaFuenteVentas consultaFuenteVentas;

				for (int k = 0; k < (6 - listaFuenteVentasAux.size()); k++) {
					consultaFuenteVentas = new ConsultaFuenteVentas();
					consultaFuenteVentas.setPeriodo("--");
					listaFuentes.add(consultaFuenteVentas);
				}
				listaFuentes.addAll(listaFuenteVentasAux);

			}
		}
		log
				.debug("tamao de la lista luego del proceso de agregar beanes vacios:"
						+ listaFuentes.size());

		if (listaFuentes.size() == 6) {
			// Hallamos los valores de la columna total
			listaAux = new ArrayList(listaFuentes);
			ConsultaFuenteVentas consultaFuenteVentas = new ConsultaFuenteVentas();
			ConsultaFuenteVentas consultaFuenteVentasAux = new ConsultaFuenteVentas();
			double tempo = 0;
			for (int i = 0; i < listaAux.size(); i++) {
				consultaFuenteVentasAux = (ConsultaFuenteVentas) listaAux
						.get(i);
				/**
				 * ESTE CAMBIO ES POR SOLICITUD DE Doris Martinich 11/07/2006
				 * 
				 * consultaFuenteVentas.setActivasIniciales(consultaFuenteVentas
				 * .getActivasIniciales() +
				 * consultaFuenteVentasAux.getActivasIniciales());
				 */
				if (i == 0) {
					consultaFuenteVentas
							.setActivasIniciales(consultaFuenteVentasAux
									.getActivasIniciales());
				}
				consultaFuenteVentas.setIngresos(consultaFuenteVentas
						.getIngresos()
						+ consultaFuenteVentasAux.getIngresos());
				consultaFuenteVentas.setReingresos(consultaFuenteVentas
						.getReingresos()
						+ consultaFuenteVentasAux.getReingresos());
				consultaFuenteVentas.setEgresosNetos(consultaFuenteVentas
						.getEgresosNetos()
						+ consultaFuenteVentasAux.getEgresosNetos());
				consultaFuenteVentas.setEgresosPuros(consultaFuenteVentas
						.getEgresosPuros()
						+ consultaFuenteVentasAux.getEgresosPuros());
				consultaFuenteVentas.setVenta(consultaFuenteVentas.getVenta()
						+ consultaFuenteVentasAux.getVenta());
				consultaFuenteVentas.setUnidades(consultaFuenteVentas
						.getUnidades()
						+ consultaFuenteVentasAux.getUnidades());
				consultaFuenteVentas.setPedidos(consultaFuenteVentas
						.getPedidos()
						+ consultaFuenteVentasAux.getPedidos());
				consultaFuenteVentas.setClientes(consultaFuenteVentas.getClientes()+ consultaFuenteVentasAux.getClientes());
				
				consultaFuenteVentas.setActivasFinales(consultaFuenteVentasAux.getActivasFinales());
				
				/* Ini efernandezo */
				//if (tipoSeleccion.equals("zona")){
					consultaFuenteVentas.setActividadLider(consultaFuenteVentasAux.getActividadLider());
					consultaFuenteVentas.setNumeroConsultoraCliPrivilege(consultaFuenteVentasAux.getNumeroConsultoraCliPrivilege());
					consultaFuenteVentas.setNumeroClientesInsPrivilege(consultaFuenteVentasAux.getNumeroClientesInsPrivilege());
					consultaFuenteVentas.setNumeroClientesTrsPrivelege(consultaFuenteVentasAux.getNumeroClientesTrsPrivelege());
					consultaFuenteVentas.setRetencionSdoPeriodo(consultaFuenteVentasAux.getRetencionSdoPeriodo());
					consultaFuenteVentas.setRetencionTerPeriodo(consultaFuenteVentasAux.getRetencionTerPeriodo());
					consultaFuenteVentas.setRetencionCuaPeriodo(consultaFuenteVentasAux.getRetencionCuaPeriodo());
					consultaFuenteVentas.setRetencionActivas(consultaFuenteVentasAux.getRetencionActivas());
					consultaFuenteVentas.setPorcentajeRotacion(consultaFuenteVentasAux.getPorcentajeRotacion());
					consultaFuenteVentas.setPosiblesEgresos(consultaFuenteVentasAux.getPosiblesEgresos());
					consultaFuenteVentas.setRetencionPosEgresos(consultaFuenteVentasAux.getRetencionPosEgresos());
				//}
				/* Fin efernandezo */
				
				tempo = consultaFuenteVentasAux.getActivasFinales() + tempo;
			}
			// Hallamos los porcentajes de la columna total

			if (consultaFuenteVentas.getActivasIniciales()
					+ consultaFuenteVentas.getIngresos() != 0) {
				consultaFuenteVentas
						.setPorcentajeEgresosNetos((consultaFuenteVentas
								.getEgresosNetos() * 100)
								/ (consultaFuenteVentas.getActivasIniciales() + consultaFuenteVentas
										.getIngresos()));
				consultaFuenteVentas
						.setPorcentajeEgresosNetos(((int) (consultaFuenteVentas
								.getPorcentajeEgresosNetos() * 100)) / 100);
			} else
				consultaFuenteVentas.setPorcentajeEgresosNetos(0);

			if (consultaFuenteVentas.getActivasIniciales() != 0) {
				consultaFuenteVentas
						.setPorcentajeEgresosPuros((consultaFuenteVentas
								.getEgresosPuros() * 100)
								/ (consultaFuenteVentas.getActivasIniciales()));
				consultaFuenteVentas.setPorcentajeEgresosPuros(redondear(
						new Double(consultaFuenteVentas
								.getPorcentajeEgresosPuros()), 1));
			} else
				consultaFuenteVentas.setPorcentajeEgresosPuros(0);

			consultaFuenteVentas.setCapitalizacion(consultaFuenteVentas
					.getIngresos()
					+ consultaFuenteVentas.getReingresos()
					- consultaFuenteVentas.getEgresosPuros());

			/**
			 * ESTE CAMBIO ES POR SOLICITUD DE Doris Martinich 11/07/2006
			 * 
			 * if (consultaFuenteVentas.getActivasFinales() != 0) {
			 * consultaFuenteVentas
			 * .setPorcentajeActividad((consultaFuenteVentas .getPedidos() *
			 * 100) / consultaFuenteVentas.getActivasFinales());
			 * consultaFuenteVentas .setPorcentajeActividad(((int)
			 * (consultaFuenteVentas .getPorcentajeActividad() * 100)) / 100); }
			 * else consultaFuenteVentas.setPorcentajeActividad(0);
			 */
			/*
			 * consultaFuenteVentas.setPorcentajeActividad(consultaFuenteVentasAux
			 * .getPorcentajeActividad());
			 */

			if (tempo > 0)
				consultaFuenteVentas.setPorcentajeActividad(redondear(
						new Double((consultaFuenteVentas.getPedidos() / tempo)*100),
						2));
			else
				consultaFuenteVentas.setPorcentajeActividad(0);

			if (consultaFuenteVentas.getPedidos() != 0) {
				consultaFuenteVentas.setPup(consultaFuenteVentas.getUnidades()
						/ consultaFuenteVentas.getPedidos());
				consultaFuenteVentas.setPup(redondear(new Double(
						consultaFuenteVentas.getPup()), 2));
			} else
				consultaFuenteVentas.setPup(0);

			if (consultaFuenteVentas.getUnidades() != 0) {
				consultaFuenteVentas.setPpu(consultaFuenteVentas.getVenta()
						/ consultaFuenteVentas.getUnidades());
				consultaFuenteVentas.setPpu(redondear(new Double(
						consultaFuenteVentas.getPpu()), 2));
			} else
				consultaFuenteVentas.setPpu(0);

			consultaFuenteVentas.setPromedioVenta(consultaFuenteVentas.getPpu()
					* consultaFuenteVentas.getPup());
			consultaFuenteVentas.setPromedioVenta(truncar(new Double(
					consultaFuenteVentas.getPromedioVenta()), 2));

			listaAux.add(6, consultaFuenteVentas);
		}

		return listaAux;
	}

	public void executeCalculoFuenteVentasPrevista(Map criteria, Usuario usuario) {
		// int result;

		log
				.debug("====== Verificacion de datos  executeCalculoFuenteVentasPrevista ============");
		log.debug("codigoPais:" + criteria.get("codigoPais"));
		log.debug("codigoSociedad:" + criteria.get("codigoSociedad"));
		log.debug("codigoAlmacen:" + criteria.get("codigoAlmacen"));
		log.debug("codigoMarca:" + criteria.get("codigoMarca"));
		log.debug("codigoCanal:" + criteria.get("codigoCanal"));
		log.debug("codigoAnio:" + criteria.get("codigoAnio"));
		log.debug("periodoInicialRango:" + criteria.get("periodoInicialRango"));
		log.debug("periodoFinalRango:" + criteria.get("periodoFinalRango"));
		log.debug("codigoPeriodoInicial:"
				+ criteria.get("codigoPeriodoInicial"));

	}

	// Zonas

	public int executeCalculoFuenteZona(Map criteria, Usuario usuario) {
		int result;

		List listaFuenteVentas = this.fuenteVentasDAO
				.getListaFuenteVentasForCalculo(criteria);
		if (listaFuenteVentas.size() > 0)
			result = 1;
		else
			result = 0;

		FuenteVentas fuenteVentas;
		// FuenteVentas fuenteVentasAux;
		FuenteVentasZona fuenteVentasZona;
		// FuenteVentasZona fuenteVentasZonaAux;

		fuenteVentasDAO.removeFuenteVentaZonas(criteria);

		for (int i = 0; i < listaFuenteVentas.size(); i++) {
			fuenteVentas = new FuenteVentas();
			fuenteVentasZona = new FuenteVentasZona();

			// Obtenemos la entidad origen
			fuenteVentas = (FuenteVentas) listaFuenteVentas.get(i);

			// Copiamos los datos comunes
			fuenteVentasZona.setCodigoPais(fuenteVentas.getCodigoPais());
			fuenteVentasZona
					.setCodigoSociedad(fuenteVentas.getCodigoSociedad());
			fuenteVentasZona.setCodigoAlmacen(fuenteVentas.getCodigoAlmacen());
			fuenteVentasZona.setCodigoMarca(fuenteVentas.getCodigoMarca());
			fuenteVentasZona.setCodigoCanal(fuenteVentas.getCodigoCanal());
			fuenteVentasZona.setCodigoPeriodo(fuenteVentas.getCodigoPeriodo());
			fuenteVentasZona.setCodigoRegion(fuenteVentas.getCodigoRegion());
			fuenteVentasZona.setCodigoZona(fuenteVentas.getCodigoZona());
			fuenteVentasZona.setEstado(Constants.ESTADO_ACTIVO);
			
			/* Ini efernandezo */ 
			fuenteVentasZona.setActividadLider(fuenteVentas.getActividadLider());
			fuenteVentasZona.setNumeroConsultoraCliPrivilege(fuenteVentas.getNumeroConsultoraCliPrivilege());
			fuenteVentasZona.setNumeroClientesInsPrivilege(fuenteVentas.getNumeroClientesInsPrivilege());
			fuenteVentasZona.setNumeroClientesTrsPrivelege(fuenteVentas.getNumeroClientesTrsPrivelege()); 
			fuenteVentasZona.setRetencionSdoPeriodo(fuenteVentas.getRetencionSdoPeriodo()); 
			fuenteVentasZona.setRetencionTerPeriodo(fuenteVentas.getRetencionTerPeriodo()); 
			fuenteVentasZona.setRetencionCuaPeriodo(fuenteVentas.getRetencionCuaPeriodo()); 
			fuenteVentasZona.setRetencionActivas(fuenteVentas.getRetencionActivas()); 
			fuenteVentasZona.setPorcentajeRotacion(fuenteVentas.getPorcentajeRotacion());
			fuenteVentasZona.setPosiblesEgresos(fuenteVentas.getPosiblesEgresos()); 
			fuenteVentasZona.setRetencionPosEgresos(fuenteVentas.getRetencionPosEgresos());
			/* Fin efernandezo */

			if ((fuenteVentas.getActividadesIniciales() == 0)) // 2a
			{
				// RCDLRP 19/06/2006
				// Obtener la activa final del periodo anterior
				// fuenteVentasZona.setActividadesIniciales(activaFinalPeriodoAnterior);
				String periodoAnterior = ""
						+ (Integer
								.parseInt(fuenteVentasZona.getCodigoPeriodo()) - 1);
				Map criteria2 = null;
				try {
					criteria2 = BeanUtils.describe(fuenteVentas);
					criteria2.put("periodoMenor", periodoAnterior);
					criteria2.put("periodoMayor", periodoAnterior);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				List fuente = fuenteVentasDAO.getFuenteVentasZona(criteria2);
				if (fuente.size() == 1)
					fuenteVentasZona
							.setActividadesIniciales(((FuenteVentasZona) fuente
									.get(0)).getActividadesFinales());
				else
					log.debug("Esta devolviendo mal la data " + fuente);
				System.out
						.println("######################### SETEANDO ACTIVA INICIAL A FINAL"
								+ fuenteVentasZona.getActividadesIniciales());

			} else {
				// Caso Contrario.
				fuenteVentasZona.setActividadesIniciales(fuenteVentas
						.getActividadesIniciales());
				System.out
						.println("######################### PARA EL CASO QUE LA FUENTE DE VENTAS YA CONTENGA VALORES "
								+ fuenteVentasZona.getActividadesIniciales());
			}

			// Nuevos
			// Los ingresos son igual a su maestro.
			fuenteVentasZona.setIngresos(fuenteVentas.getIngresos());
			// Los reingresos son igual a su maestro.
			fuenteVentasZona.setReingresos(fuenteVentas.getReingresos());

			// Nuevos
			// Los egresos puros son igual a su maestro. - Renombrar Egresos por
			// egresosPuros
			fuenteVentasZona.setEgresos(fuenteVentas.getEgresos());

			// PPU es igual a su maestro
			fuenteVentasZona.setPpu(fuenteVentas.getPpu());

			// PUP es igual a su maestro
			fuenteVentasZona.setPup(fuenteVentas.getPup());

			// Los egresos netos son igual a los Egresos Puros menos los
			// Reingresos
			fuenteVentasZona.setEgresosNetos(fuenteVentasZona.getEgresos()
					- fuenteVentasZona.getReingresos()); // 2b

			// Porcentaje Egresos Netos - Renombrar de actividades a activas.
			if ((fuenteVentasZona.getActividadesIniciales() + fuenteVentasZona
					.getIngresos()) != 0)
				fuenteVentasZona
						.setPorcentajeEgresosNetos(fuenteVentasZona
								.getEgresosNetos()
								* 100
								/ (fuenteVentasZona.getActividadesIniciales() + fuenteVentasZona
										.getIngresos())); // 2c
			else
				fuenteVentasZona.setPorcentajeEgresosNetos(0);

			// Renombrar de PorcentajeEgresos a PorcentajeEgresosPuros
			// los PorcentajesEgresos Puros son iguales a egresosPuros * 100 /
			// activasIniciales
			if (fuenteVentasZona.getActividadesIniciales() != 0)
				fuenteVentasZona.setPorcentajeEgresos(fuenteVentasZona
						.getEgresos()
						* 100 / fuenteVentasZona.getActividadesIniciales()); // 2d
			else
				fuenteVentasZona.setPorcentajeEgresos(0);

			// La capitalizacion es igual a ingresos + reingresos - egresosPuros
			fuenteVentasZona.setCapitalizacion(fuenteVentas.getIngresos()
					+ fuenteVentas.getReingresos() - fuenteVentas.getEgresos());// 2e

			// Renombrar actividades a activas Finales
			// Las activas finales es igual a ActivasIniciales + Ingresos +
			// Reingresos - EgresosPuros
			fuenteVentasZona.setActividadesFinales(fuenteVentasZona
					.getActividadesIniciales()
					+ fuenteVentasZona.getIngresos()
					+ fuenteVentasZona.getReingresos()
					- fuenteVentasZona.getEgresos()); // 2f

			// El Porcentaje de Actividad es igual a su maestro
			fuenteVentasZona.setPorcentajeActividad(fuenteVentas
					.getPorcentajeActividad());

			/*
			 * if (fuenteVentasZona.getActividadesFinales() != 0)
			 * fuenteVentasZona.setPorcentajeActividad(fuenteVentasZona
			 * .getNumeroPedidos() 100 /
			 * fuenteVentasZona.getActividadesFinales()); // 2g else
			 * fuenteVentasZona.setPorcentajeActividad(0);
			 */

			// El numero de pedidos es igual a activas finales *
			// porcentajeActividad / 100
			fuenteVentasZona.setNumeroPedidos(fuenteVentasZona
					.getActividadesFinales()
					* fuenteVentasZona.getPorcentajeActividad() / 100); // 2h

			// El numero de unidades es igual a NumeroPedidos * PUP
			fuenteVentasZona.setNumeroUnidades(fuenteVentasZona
					.getNumeroPedidos()
					* fuenteVentasZona.getPup()); // 2i

			// El numero de clientes es igual a su maestro
			fuenteVentasZona
					.setNumeroClientes(fuenteVentas.getNumeroClientes());

			// El promedioSolesPorPedido es igual a PPU * PUP
			fuenteVentasZona.setPromedioSope(fuenteVentasZona.getPpu()
					* fuenteVentasZona.getPup()); // 2j

			// La Venta Neta es igual a PromedioSolesPorPedido * numeroPedidos
			fuenteVentasZona.setVene(fuenteVentasZona.getPromedioSope()
					* fuenteVentasZona.getNumeroPedidos()); // 2k

			// existe = this.fuenteVentasDAO.verificaZona(fuenteVentasZona);

			/*
			 * if (existe == true) {
			 * fuenteVentasZona.setCodigoFuenteVentasZona(this.fuenteVentasDAO
			 * .getPKZona(fuenteVentasZona));
			 * this.updateFuenteVentaZona(fuenteVentasZona, usuario); } else {
			 * fuenteVentasZona
			 * .setCodigoFuenteVentasZona(this.getNextPKZona());
			 * this.insertFuenteVentaZona(fuenteVentasZona, usuario); }
			 */
			fuenteVentasZona.setCodigoFuenteVentasZona(this.getNextPKZona());
			this.insertFuenteVentaZona(fuenteVentasZona, usuario);

		} // Fin del for
		return result;
	}

	// Regiones

	public int executeCalculoFuenteRegion(Map criteria, Usuario usuario) {
		int result;

		List listaFuenteVentasRegion = this.fuenteVentasDAO
				.getListaFuenteVentasRegionForCalculo(criteria);

		if (listaFuenteVentasRegion.size() > 0)
			result = 1;
		else
			result = 0;

		// boolean existe;
		FuenteVentasRegion fuenteVentasRegion;

		fuenteVentasDAO.removeFuenteVentaRegiones(criteria);

		for (int i = 0; i < listaFuenteVentasRegion.size(); i++) {
			fuenteVentasRegion = new FuenteVentasRegion();
			fuenteVentasRegion.setEstado(Constants.ESTADO_ACTIVO);
			fuenteVentasRegion = (FuenteVentasRegion) listaFuenteVentasRegion
					.get(i);

			if ((fuenteVentasRegion.getActividadesIniciales() + fuenteVentasRegion
					.getIngresos()) != 0)
				fuenteVentasRegion
						.setPorcentajeEgresosNetos(fuenteVentasRegion
								.getEgresosNetos()
								* 100
								/ (fuenteVentasRegion.getActividadesIniciales() + fuenteVentasRegion
										.getIngresos()));
			else
				fuenteVentasRegion.setPorcentajeEgresosNetos(0);

			if (fuenteVentasRegion.getActividadesIniciales() != 0)
				fuenteVentasRegion.setPorcentajeEgresos(fuenteVentasRegion
						.getEgresos()
						* 100 / fuenteVentasRegion.getActividadesIniciales());
			else
				fuenteVentasRegion.setPorcentajeEgresos(0);

			fuenteVentasRegion.setCapitalizacion(fuenteVentasRegion
					.getIngresos()
					+ fuenteVentasRegion.getReingresos()
					- fuenteVentasRegion.getEgresos());

			if (fuenteVentasRegion.getActividadesFinales() != 0)
				fuenteVentasRegion.setPorcentajeActividad(fuenteVentasRegion
						.getNumeroPedidos()
						* 100 / fuenteVentasRegion.getActividadesFinales());
			else
				fuenteVentasRegion.setPorcentajeActividad(0);

			if (fuenteVentasRegion.getNumeroPedidos() != 0)
				fuenteVentasRegion.setPup(fuenteVentasRegion
						.getNumeroUnidades()
						/ fuenteVentasRegion.getNumeroPedidos());
			else
				fuenteVentasRegion.setPup(0);

			if (fuenteVentasRegion.getNumeroUnidades() != 0)
				fuenteVentasRegion.setPpu(fuenteVentasRegion.getVene()
						/ fuenteVentasRegion.getNumeroUnidades());
			else
				fuenteVentasRegion.setPpu(0);

			fuenteVentasRegion.setPromedioSope(fuenteVentasRegion.getPpu()
					* fuenteVentasRegion.getPup());

			/*
			 * existe = this.fuenteVentasDAO.verificaRegion(fuenteVentasRegion);
			 * 
			 * if (existe == true) { fuenteVentasRegion
			 * .setCodigoFuenteVentasRegion(this.fuenteVentasDAO
			 * .getPKRegion(fuenteVentasRegion));
			 * this.updateFuenteVentaRegion(fuenteVentasRegion, usuario); } else {
			 * fuenteVentasRegion.setCodigoFuenteVentasRegion(this
			 * .getNextPKRegion());
			 * this.insertFuenteVentaRegion(fuenteVentasRegion, usuario); }
			 */

			fuenteVentasRegion.setCodigoFuenteVentasRegion(this
					.getNextPKRegion());
			insertFuenteVentaRegion(fuenteVentasRegion, usuario);

		} // Fin del for
		return result;
	}

	// Paises

	public int executeCalculoFuentePais(Map criteria, Usuario usuario) {
		int result;

		List listaFuenteVentasPais = this.fuenteVentasDAO
				.getListaFuenteVentasPaisForCalculo(criteria);

		if (listaFuenteVentasPais.size() > 0)
			result = 1;
		else
			result = 0;
		// boolean existe;
		FuenteVentasPais fuenteVentasPais;

		fuenteVentasDAO.removeFuenteVentaPaises(criteria);

		for (int i = 0; i < listaFuenteVentasPais.size(); i++) {
			fuenteVentasPais = new FuenteVentasPais();
			fuenteVentasPais = (FuenteVentasPais) listaFuenteVentasPais.get(i);
			fuenteVentasPais.setEstado(Constants.ESTADO_ACTIVO);

			if ((fuenteVentasPais.getActividadesIniciales()
					+ fuenteVentasPais.getIngresos() != 0))
				fuenteVentasPais
						.setPorcentajeEgresosNetos(fuenteVentasPais
								.getEgresosNetos()
								* 100
								/ (fuenteVentasPais.getActividadesIniciales() + fuenteVentasPais
										.getIngresos()));
			else
				fuenteVentasPais.setPorcentajeEgresosNetos(0);
			if (fuenteVentasPais.getActividadesIniciales() != 0)
				fuenteVentasPais.setPorcentajeEgresos(fuenteVentasPais
						.getEgresos()
						* 100 / fuenteVentasPais.getActividadesIniciales());
			else
				fuenteVentasPais.setPorcentajeEgresos(0);

			fuenteVentasPais.setCapitalizacion(fuenteVentasPais.getIngresos()
					+ fuenteVentasPais.getReingresos()
					- fuenteVentasPais.getEgresos());
			if (fuenteVentasPais.getActividadesFinales() != 0)
				fuenteVentasPais.setPorcentajeActividad(fuenteVentasPais
						.getNumeroPedidos()
						* 100 / fuenteVentasPais.getActividadesFinales());
			else
				fuenteVentasPais.setPorcentajeActividad(0);

			if (fuenteVentasPais.getNumeroPedidos() != 0)
				fuenteVentasPais.setPup(fuenteVentasPais.getNumeroUnidades()
						/ fuenteVentasPais.getNumeroPedidos());
			else
				fuenteVentasPais.setPup(0);

			if (fuenteVentasPais.getNumeroUnidades() != 0)
				fuenteVentasPais.setPpu(fuenteVentasPais.getVene()
						/ fuenteVentasPais.getNumeroUnidades());
			else
				fuenteVentasPais.setPpu(0);

			fuenteVentasPais.setPromedioSope(fuenteVentasPais.getPpu()
					* fuenteVentasPais.getPup());

			/*
			 * existe = this.fuenteVentasDAO.verificaPais(fuenteVentasPais);
			 * 
			 * log.debug("Control::" + i + " --> Valor de existe:" +
			 * existe);
			 * 
			 * if (existe == true) {
			 * fuenteVentasPais.setCodigoFuenteVentasPais(this.fuenteVentasDAO
			 * .getPKPais(fuenteVentasPais));
			 * this.updateFuenteVentaPais(fuenteVentasPais, usuario); } else {
			 * fuenteVentasPais
			 * .setCodigoFuenteVentasPais(this.getNextPKPais());
			 * this.insertFuenteVentaPais(fuenteVentasPais, usuario); }
			 */

			fuenteVentasPais.setCodigoFuenteVentasPais(this.getNextPKPais());
			this.insertFuenteVentaPais(fuenteVentasPais, usuario);
		} // Fin del for
		return result;
	}

	public void insertFuenteVenta(FuenteVentas fuenteVentas, Usuario usuario) {
		try {

			// Verificamos que no exista un fuenteVentas con el mismo
			// identificador
			// En caso no exista el objeto se lanzar una excepcion
			// ObjectRetrievalFailureException
			this.fuenteVentasDAO.getFuenteVenta(String.valueOf(fuenteVentas
					.getCodigoFuenteVentas()));

			// Si ninguna excepcion es lanzada entonces el objeto ya existe
			// Por consiguiente lanzamos la excepcion correspondiente
			throw new InvalidIdentifierException(FuenteVentas.class, String
					.valueOf(fuenteVentas.getCodigoFuenteVentas()));
		} catch (ObjectRetrievalFailureException orfe) {
			fuenteVentas.setEstado(Constants.ESTADO_ACTIVO);
			// Insertamos el nuevo fuenteVentas
			this.fuenteVentasDAO.insertFuenteVenta(fuenteVentas, usuario);
		}
	}

	public String getPeriodoAbierto(String periodo) {
		return fuenteVentasDAO.getPeriodoAbierto(periodo);
	}

	public void updateFuenteVenta(FuenteVentas fuenteVentas, Usuario usuario) {
		// Verificamos que no exista un fuenteVentas con la misma descripcin,
		// para el mismo pais
		// Creamos el bean que nos servir como criterio de busqueda

		FuenteVentas fuenteVentasAux = fuenteVentasDAO.getFuenteVenta(String
				.valueOf(fuenteVentas.getCodigoFuenteVentas()));

		if (fuenteVentasAux != null) {
			this.fuenteVentasDAO.updateFuenteVenta(fuenteVentas, usuario);
		}
	}

	public void removeFuenteVenta(String primaryKey, Usuario usuario) {
		// Actualizamos el estado del FuenteVentas
		try {
			FuenteVentas fuenteVentas = this.fuenteVentasDAO
					.getFuenteVenta(primaryKey);
			fuenteVentas.setEstado(Constants.ESTADO_INACTIVO);
			// Actualizamos el fuenteVentas
			this.fuenteVentasDAO.updateFuenteVenta(fuenteVentas, usuario);
		} catch (ObjectRetrievalFailureException orfe) {
			log.warn(orfe.getMessage());
		}
	}

	// Zona
	// ======================================================================================

	public long getNextPKZona() {
		return this.fuenteVentasDAO.getNextPKZona();
	}

	public List getFuenteVentasZonaByCriteria(Map criteria) {
		return this.fuenteVentasDAO.getFuenteVentasZona(criteria);
	}

	public FuenteVentasZona getFuenteVentaZona(String primaryKey) {
		return this.fuenteVentasDAO.getFuenteVentaZona(primaryKey);
	}

	public void updateFuenteVentasZonaLista(ArrayList listaFuentesVentaZona,
			Usuario usuario) {
		FuenteVentasZona fuenteVentasZona;
		for (int i = 0; i < listaFuentesVentaZona.size(); i++) {
			fuenteVentasZona = new FuenteVentasZona();
			fuenteVentasZona = (FuenteVentasZona) listaFuentesVentaZona.get(i);
			this.updateFuenteVentaZona(fuenteVentasZona, usuario);
		}
	}

	public void insertFuenteVentaZona(FuenteVentasZona fuenteVentasZona,
			Usuario usuario) {
		try {
			// Verificamos que no exista un fuenteVentas con el mismo
			// identificador
			// En caso no exista el objeto se lanzar una excepcion
			// ObjectRetrievalFailureException
			this.fuenteVentasDAO.getFuenteVentaZona(String
					.valueOf(fuenteVentasZona.getCodigoFuenteVentasZona()));

			// Si ninguna excepcion es lanzada entonces el objeto ya existe
			// Por consiguiente lanzamos la excepcion correspondiente
			throw new InvalidIdentifierException(FuenteVentasZona.class, String
					.valueOf(fuenteVentasZona.getCodigoFuenteVentasZona()));
		} catch (ObjectRetrievalFailureException orfe) {
			// Insertamos el nuevo fuenteVentasZona
			this.fuenteVentasDAO.insertFuenteVentaZona(fuenteVentasZona,
					usuario);
		}
	}

	public void updateFuenteVentaZona(FuenteVentasZona fuenteVentasZona,
			Usuario usuario) {
		// Verificamos que no exista un fuenteVentas con la misma descripcin,
		// para el mismo pais
		// Creamos el bean que nos servir como criterio de busqueda

		FuenteVentasZona fuenteVentasZonaAux = fuenteVentasDAO
				.getFuenteVentaZona(String.valueOf(fuenteVentasZona
						.getCodigoFuenteVentasZona()));

		if (fuenteVentasZonaAux != null) {
			this.fuenteVentasDAO.updateFuenteVentaZona(fuenteVentasZona,
					usuario);
		}
	}

	public void removeFuenteVentaZona(String primaryKey, Usuario usuario) {
		// Actualizamos el estado del FuenteVentas
		try {
			FuenteVentasZona fuenteVentasZona = this.fuenteVentasDAO
					.getFuenteVentaZona(primaryKey);
			// Actualizamos el fuenteVentas
			this.fuenteVentasDAO.removeFuenteVentaZona(String
					.valueOf(fuenteVentasZona.getCodigoFuenteVentasZona()));
		} catch (ObjectRetrievalFailureException orfe) {
			log.warn(orfe.getMessage());
		}
	}

	// Region
	// ======================================================================================

	public long getNextPKRegion() {
		return this.fuenteVentasDAO.getNextPKRegion();
	}

	public List getFuenteVentasRegionByCriteria(Map criteria) {
		return this.fuenteVentasDAO.getFuenteVentasRegion(criteria);
	}

	public FuenteVentasRegion getFuenteVentaRegion(String primaryKey) {
		return this.fuenteVentasDAO.getFuenteVentaRegion(primaryKey);
	}

	public void updateFuenteVentasRegionLista(
			ArrayList listaFuentesVentaRegion, Usuario usuario) {
		FuenteVentasRegion fuenteVentasRegion;
		for (int i = 0; i < listaFuentesVentaRegion.size(); i++) {
			fuenteVentasRegion = new FuenteVentasRegion();
			fuenteVentasRegion = (FuenteVentasRegion) listaFuentesVentaRegion
					.get(i);
			this.updateFuenteVentaRegion(fuenteVentasRegion, usuario);
		}
	}

	public void insertFuenteVentaRegion(FuenteVentasRegion fuenteVentasRegion,
			Usuario usuario) {
		try {
			// Verificamos que no exista un fuenteVentas con el mismo
			// identificador
			// En caso no exista el objeto se lanzar una excepcion
			// ObjectRetrievalFailureException
			this.fuenteVentasDAO.getFuenteVentaRegion(String
					.valueOf(fuenteVentasRegion.getCodigoFuenteVentasRegion()));

			// Si ninguna excepcion es lanzada entonces el objeto ya existe
			// Por consiguiente lanzamos la excepcion correspondiente
			throw new InvalidIdentifierException(FuenteVentasRegion.class,
					String.valueOf(fuenteVentasRegion
							.getCodigoFuenteVentasRegion()));
		} catch (ObjectRetrievalFailureException orfe) {
			// Insertamos el nuevo fuenteVentasRegion
			this.fuenteVentasDAO.insertFuenteVentaRegion(fuenteVentasRegion,
					usuario);
		}
	}

	public void updateFuenteVentaRegion(FuenteVentasRegion fuenteVentasRegion,
			Usuario usuario) {
		// Verificamos que no exista un fuenteVentas con la misma descripcin,
		// para el mismo pais
		// Creamos el bean que nos servir como criterio de busqueda

		FuenteVentasRegion fuenteVentasRegionAux = fuenteVentasDAO
				.getFuenteVentaRegion(String.valueOf(fuenteVentasRegion
						.getCodigoFuenteVentasRegion()));

		if (fuenteVentasRegionAux != null) {
			this.fuenteVentasDAO.updateFuenteVentaRegion(fuenteVentasRegion,
					usuario);
		}
	}

	public void removeFuenteVentaRegion(String primaryKey, Usuario usuario) {
		// Actualizamos el estado del FuenteVentas
		try {
			FuenteVentasRegion fuenteVentasRegion = this.fuenteVentasDAO
					.getFuenteVentaRegion(primaryKey);
			// Actualizamos el fuenteVentas
			this.fuenteVentasDAO.removeFuenteVentaRegion(String
					.valueOf(fuenteVentasRegion.getCodigoFuenteVentasRegion()));
		} catch (ObjectRetrievalFailureException orfe) {
			log.warn(orfe.getMessage());
		}
	}

	// Pais
	// ======================================================================================

	public long getNextPKPais() {
		return this.fuenteVentasDAO.getNextPKPais();
	}

	public List getFuenteVentasPaisByCriteria(Map criteria) {
		return this.fuenteVentasDAO.getFuenteVentasPais(criteria);
	}

	public FuenteVentasPais getFuenteVentaPais(String primaryKey) {
		return this.fuenteVentasDAO.getFuenteVentaPais(primaryKey);
	}

	public void updateFuenteVentasPaisLista(ArrayList listaFuentesVentaPais,
			Usuario usuario) {
		FuenteVentasPais fuenteVentasPais;
		for (int i = 0; i < listaFuentesVentaPais.size(); i++) {
			fuenteVentasPais = new FuenteVentasPais();
			fuenteVentasPais = (FuenteVentasPais) listaFuentesVentaPais.get(i);
			this.updateFuenteVentaPais(fuenteVentasPais, usuario);
		}
	}

	public void insertFuenteVentaPais(FuenteVentasPais fuenteVentasPais,
			Usuario usuario) {
		/*
		 * try{ // Verificamos que no exista un fuenteVentas con el mismo
		 * identificador // En caso no exista el objeto se lanzar una excepcion
		 * ObjectRetrievalFailureException
		 * //this.fuenteVentasDAO.getFuenteVentaPais(String.valueOf(fuenteVentasPais.getCodigoFuenteVentasPais()));
		 * FuenteVentasPais fuenteVentasPaisAux =
		 * fuenteVentasDAO.getFuenteVentaPais(String.valueOf(fuenteVentasPais.getCodigoFuenteVentasPais())); //
		 * Si ninguna excepcion es lanzada entonces el objeto ya existe // Por
		 * consiguiente lanzamos la excepcion correspondiente throw new
		 * InvalidIdentifierException(FuenteVentasPais.class,
		 * String.valueOf(fuenteVentasPais.getCodigoFuenteVentasPais())); }
		 * catch(ObjectRetrievalFailureException orfe){
		 * log.debug("============= no existe en la BD, se procede a
		 * insertar:"+fuenteVentasPais.getCodigoFuenteVentasPais()); }
		 */
		this.fuenteVentasDAO.insertFuenteVentaPais(fuenteVentasPais, usuario);
	}

	public void updateFuenteVentaPais(FuenteVentasPais fuenteVentasPais,
			Usuario usuario) {
		// Verificamos que no exista un fuenteVentas con la misma descripcin,
		// para el mismo pais
		// Creamos el bean que nos servir como criterio de busqueda
		/*
		 * FuenteVentasPais fuenteVentasPaisAux =
		 * fuenteVentasDAO.getFuenteVentaPais(String.valueOf(fuenteVentasPais.getCodigoFuenteVentasPais()));
		 * 
		 * if (fuenteVentasPaisAux != null) {
		 * this.fuenteVentasDAO.updateFuenteVentaPais(fuenteVentasPais,
		 * usuario); }
		 */
		this.fuenteVentasDAO.updateFuenteVentaPais(fuenteVentasPais, usuario);
	}

	public void removeFuenteVentaPais(String primaryKey, Usuario usuario) {
		// Actualizamos el estado del FuenteVentas
		try {
			FuenteVentasPais fuenteVentasPais = this.fuenteVentasDAO
					.getFuenteVentaPais(primaryKey);
			// Actualizamos el fuenteVentas
			this.fuenteVentasDAO.removeFuenteVentaPais(String
					.valueOf(fuenteVentasPais.getCodigoFuenteVentasPais()));
		} catch (ObjectRetrievalFailureException orfe) {
			log.warn(orfe.getMessage());
		}
	}

	public double redondear(Double doubleNumber, int decimales) {
		double result;
		BigDecimal bd = new BigDecimal(doubleNumber.doubleValue());
		bd = bd.setScale(decimales, BigDecimal.ROUND_HALF_UP);
		result = bd.doubleValue();
		return result;
	}

	public double truncar(Double doubleNumber, int decimales) {
		double result;
		BigDecimal bd = new BigDecimal(doubleNumber.doubleValue());
		bd = bd.setScale(decimales, BigDecimal.ROUND_DOWN);
		result = bd.doubleValue();
		return result;
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.FuenteVentasService#deleteTablasCargaFuenteVentaPrevista()
	 */
	public void deleteTablasCargaFuenteVentaPrevista() {
		this.fuenteVentasDAO.deleteTablasCargaFuenteVentaPrevista();		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.FuenteVentasService#obtenerPathUpload(java.lang.String)
	 */
	public String obtenerPathUpload(String codigoPais) {
		return fuenteVentasDAO.obtenerPathUpload(codigoPais);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.FuenteVentasService#executeValidarCargaFuenteVentaPrevista(java.util.Map)
	 */
	public void executeValidarCargaFuenteVentaPrevista(Map criteria) throws Exception {
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		String extensionArchivo = (String)criteria.get("extensionArchivo");
		String indTipoFvp = (String)criteria.get("indTipoFvp");
		
		//Eliminamos la data de las tablas de las unidades Geograficas, ya que seran
		//cargadas con los datos del archivo excel
		deleteTablasCargaFuenteVentaPrevista();

		if (extensionArchivo.equalsIgnoreCase(Constants.EXTENSION_ARCHIVO_EXCEL))
			procesarArchivoExcel(directorioTemporal, nombreArchivo, indTipoFvp);
		
		//Realizamos el proceso de Validacion de los datos ingresados
		fuenteVentasDAO.executeValidarCargaFuenteVentaPrevista(criteria);			

	}
	
	/**
	 *Metodo que procesa el archivo excel
	 * @param directorioTemporal
	 * @param nombreArchivo
	 * @throws Exception
	 */
	private void procesarArchivoExcel(String directorioTemporal, String nombreArchivo, String indTipoFvp) throws Exception {
	//Abrimos el archivo Excel para su procesamiento		
	ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);
	
	//nos colocamos en la primera hoja del documento Excel
	excelUtil.initSheet(0);
	
	//nos pasamos a la segunda fila, ya que el primero se encuentra la cabecera
	excelUtil.next();
	int fila = 0;
		
		while(excelUtil.hasNext()) {
			Map mapRow = excelUtil.next();
			EstructuraFuenteVentaPrevista estructura = new EstructuraFuenteVentaPrevista();
			fila = fila + 1;
			
			log.debug("mapRow--- "+fila+" - "+mapRow.toString());

			if (StringUtils.equalsIgnoreCase(indTipoFvp, Constants.NUMERO_UNO)) {
				if (StringUtils.isNotEmpty((String)mapRow.get("0")) )
					if (((String)mapRow.get("0")).length() > 3 )
						estructura.setCodigoPais(((String)mapRow.get("0")).substring(0,3));
					else estructura.setCodigoPais((String)mapRow.get("0"));
				else estructura.setCodigoPais(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("1")) )
					if (((String)mapRow.get("1")).length() > 6 )
						estructura.setPeriodo(((String)mapRow.get("1")).substring(0,6));
					else estructura.setPeriodo((String)mapRow.get("1"));
				else estructura.setPeriodo(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("2")) )
					if (((String)mapRow.get("2")).length() > 2 )
						estructura.setCodigoRegion(((String)mapRow.get("2")).substring(0,2));
					else estructura.setCodigoRegion((String)mapRow.get("2"));
				else estructura.setCodigoRegion(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("3")) )
					if (((String)mapRow.get("3")).length() > 4 )
						estructura.setCodigoZona(((String)mapRow.get("3")).substring(0,4));
					else estructura.setCodigoZona((String)mapRow.get("3"));
				else estructura.setCodigoZona(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("4")) )
					if (((String)mapRow.get("4")).length() > 1 )
						estructura.setCodigoSeccion(((String)mapRow.get("4")).substring(0,1));
					else estructura.setCodigoSeccion((String)mapRow.get("4"));
				else estructura.setCodigoSeccion(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("5")) )
					if (((String)mapRow.get("5")).length() > 13 )
						if (((String) mapRow.get("5")).indexOf("E")>0 )
							estructura.setVentaNetaCatalogo(((String)mapRow.get("5")).substring(0,((String)mapRow.get("5")).length()));
						else
							estructura.setVentaNetaCatalogo(((String)mapRow.get("5")).substring(0,13));
					else estructura.setVentaNetaCatalogo((String)mapRow.get("5"));
				else estructura.setVentaNetaCatalogo(null);
								
				if (StringUtils.isNotEmpty((String)mapRow.get("6")) )
					if (((String)mapRow.get("6")).length() > 12 )
						if (((String) mapRow.get("6")).indexOf("E")>0 )
							estructura.setPedidos(((String)mapRow.get("6")).substring(0,((String)mapRow.get("6")).length()));
						else
							estructura.setPedidos(((String)mapRow.get("6")).substring(0,12));
					else estructura.setPedidos((String)mapRow.get("6"));
				else estructura.setPedidos(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("7")) )
					if (((String)mapRow.get("7")).length() > 13 )
						if (((String) mapRow.get("7")).indexOf("E")>0 )
							estructura.setActivasIniciales(((String)mapRow.get("7")).substring(0,((String)mapRow.get("7")).length()));
						else
							estructura.setActivasIniciales(((String)mapRow.get("7")).substring(0,13));
					else estructura.setActivasIniciales((String)mapRow.get("7"));
				else estructura.setActivasIniciales(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("8")) )
					if (((String)mapRow.get("8")).length() > 13 )
						if (((String) mapRow.get("8")).indexOf("E")>0 )
							estructura.setIngresos(((String)mapRow.get("8")).substring(0,((String)mapRow.get("8")).length()));
						else
							estructura.setIngresos(((String)mapRow.get("8")).substring(0,13));
					else estructura.setIngresos((String)mapRow.get("8"));
				else estructura.setIngresos(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("9")) )
					if (((String)mapRow.get("9")).length() > 13 )
						if (((String) mapRow.get("9")).indexOf("E")>0 )
							estructura.setReingresos(((String)mapRow.get("9")).substring(0,((String)mapRow.get("9")).length()));
						else
							estructura.setReingresos(((String)mapRow.get("9")).substring(0,13));
					else estructura.setReingresos((String)mapRow.get("9"));
				else estructura.setReingresos(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("10")) )
					if (((String)mapRow.get("10")).length() > 13 )
						if (((String) mapRow.get("10")).indexOf("E")>0 )
							estructura.setEgresos(((String)mapRow.get("10")).substring(0,((String)mapRow.get("10")).length()));
						else
						estructura.setEgresos(((String)mapRow.get("10")).substring(0,13));
					else estructura.setEgresos((String)mapRow.get("10"));
				else estructura.setEgresos(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("11")) )
					if (((String)mapRow.get("11")).length() > 6 )
						estructura.setRetencionSegPedido(((String)mapRow.get("11")).substring(0,6));
					else estructura.setRetencionSegPedido((String)mapRow.get("11"));
				else estructura.setRetencionSegPedido(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("12")) )
					if (((String)mapRow.get("12")).length() > 6 )
						estructura.setRetencionTerPedido(((String)mapRow.get("12")).substring(0,6));
					else estructura.setRetencionTerPedido((String)mapRow.get("12"));
				else estructura.setRetencionTerPedido(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("13")) )
					if (((String)mapRow.get("13")).length() > 6 )
						estructura.setRetencionCuarPedido(((String)mapRow.get("13")).substring(0,6));
					else estructura.setRetencionCuarPedido((String)mapRow.get("13"));
				else estructura.setRetencionCuarPedido(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("14")) )
					if (((String)mapRow.get("14")).length() > 12 )
						if (((String) mapRow.get("14")).indexOf("E")>0 )
							estructura.setUnidades(((String)mapRow.get("14")).substring(0,((String)mapRow.get("14")).length()));
						else
							estructura.setUnidades(((String)mapRow.get("14")).substring(0,12));
					else estructura.setUnidades((String)mapRow.get("14"));
				else estructura.setUnidades(null);
					
				if (StringUtils.isNotEmpty((String)mapRow.get("15")) )
					if (((String)mapRow.get("15")).length() > 3 )
						estructura.setPosibleEgresoCampaniaAnterior(((String)mapRow.get("15")).substring(0,3));
					else estructura.setPosibleEgresoCampaniaAnterior((String)mapRow.get("15"));
				else estructura.setPosibleEgresoCampaniaAnterior(null);
				
			} else {
				if (StringUtils.isNotEmpty((String)mapRow.get("0")) )
					if (((String)mapRow.get("0")).length() > 3 )
						estructura.setCodigoPais(((String)mapRow.get("0")).substring(0,3));
					else estructura.setCodigoPais((String)mapRow.get("0"));
				else estructura.setCodigoPais(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("1")) )
					if (((String)mapRow.get("1")).length() > 4 )
						estructura.setCodigoSociedad(((String)mapRow.get("1")).substring(0,4));
					else estructura.setCodigoSociedad((String)mapRow.get("1"));
				else estructura.setCodigoSociedad(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("2")) )
					if (((String)mapRow.get("2")).length() > 3 )
						estructura.setCodgioMarca(((String)mapRow.get("2")).substring(0,3));
					else estructura.setCodgioMarca((String)mapRow.get("2"));
				else estructura.setCodgioMarca(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("3")) )
					if (((String)mapRow.get("3")).length() > 4 )
						estructura.setCodigoAlmacen(((String)mapRow.get("3")).substring(0,4));
					else estructura.setCodigoAlmacen((String)mapRow.get("3"));
				else estructura.setCodigoAlmacen(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("4")) )
					if (((String)mapRow.get("4")).length() > 2 )
						estructura.setCanal(((String)mapRow.get("4")).substring(0,2));
					else estructura.setCanal((String)mapRow.get("4"));
				else estructura.setCanal(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("5")) )
					if (((String)mapRow.get("5")).length() > 6 )
						estructura.setPeriodo(((String)mapRow.get("5")).substring(0,6));
					else estructura.setPeriodo((String)mapRow.get("5"));
				else estructura.setPeriodo(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("6")) )
					if (((String)mapRow.get("6")).length() > 2 )
						estructura.setCodigoRegion(((String)mapRow.get("6")).substring(0,2));
					else estructura.setCodigoRegion((String)mapRow.get("6"));
				else estructura.setCodigoRegion(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("7")) )
					if (((String)mapRow.get("7")).length() > 4 )
						estructura.setCodigoZona(((String)mapRow.get("7")).substring(0,4));
					else estructura.setCodigoZona((String)mapRow.get("7"));
				else estructura.setCodigoZona(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("8")) )										   
					if (((String)mapRow.get("8")).length() > 13 )
						if (((String) mapRow.get("8")).indexOf("E")>0 )
							estructura.setVentaNetaCatalogo(((String)mapRow.get("8")).substring(0,((String)mapRow.get("8")).length()));
						else
							estructura.setVentaNetaCatalogo(((String)mapRow.get("8")).substring(0,13));
					else estructura.setVentaNetaCatalogo((String)mapRow.get("8"));
				else estructura.setVentaNetaCatalogo(null);
								
				if (StringUtils.isNotEmpty((String)mapRow.get("9")) )
					if (((String)mapRow.get("9")).length() > 12 )
						if (((String) mapRow.get("9")).indexOf("E")>0 )
							estructura.setPedidos(((String)mapRow.get("9")).substring(0,((String)mapRow.get("9")).length()));
						else
							estructura.setPedidos(((String)mapRow.get("9")).substring(0,12));
					else estructura.setPedidos((String)mapRow.get("9"));
				else estructura.setPedidos(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("10")) )
					if (((String)mapRow.get("10")).length() > 13 )
						if (((String) mapRow.get("10")).indexOf("E")>0 )
							estructura.setActivasIniciales(((String)mapRow.get("10")).substring(0,((String)mapRow.get("10")).length()));
						else
							estructura.setActivasIniciales(((String)mapRow.get("10")).substring(0,13));
					else estructura.setActivasIniciales((String)mapRow.get("10"));
				else estructura.setActivasIniciales(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("11")) )
					if (((String)mapRow.get("11")).length() > 13 )
						if (((String) mapRow.get("11")).indexOf("E")>0 )
							estructura.setIngresos(((String)mapRow.get("11")).substring(0,((String)mapRow.get("11")).length()));
						else							
							estructura.setIngresos(((String)mapRow.get("11")).substring(0,13));
					else estructura.setIngresos((String)mapRow.get("11"));
				else estructura.setIngresos(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("12")) )
					if (((String)mapRow.get("12")).length() > 13 )
						if (((String) mapRow.get("12")).indexOf("E")>0 )
							estructura.setReingresos(((String)mapRow.get("12")).substring(0,((String)mapRow.get("12")).length()));
						else														
							estructura.setReingresos(((String)mapRow.get("12")).substring(0,13));
					else estructura.setReingresos((String)mapRow.get("12"));
				else estructura.setReingresos(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("13")) )
					if (((String)mapRow.get("13")).length() > 13 )
						if (((String) mapRow.get("13")).indexOf("E")>0 )
							estructura.setEgresos(((String)mapRow.get("13")).substring(0,((String)mapRow.get("13")).length()));
						else																					
						estructura.setEgresos(((String)mapRow.get("13")).substring(0,13));
					else estructura.setEgresos((String)mapRow.get("13"));
				else estructura.setEgresos(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("14")) )
					if (((String)mapRow.get("14")).length() > 10 )
						if (((String) mapRow.get("14")).indexOf("E")>0 )
							estructura.setConsultorasPrivilege(((String)mapRow.get("14")).substring(0,((String)mapRow.get("14")).length()));
						else
							estructura.setConsultorasPrivilege(((String)mapRow.get("14")).substring(0,10));
					else estructura.setConsultorasPrivilege((String)mapRow.get("14"));
				else estructura.setConsultorasPrivilege(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("15")) )
					if (((String)mapRow.get("15")).length() > 10 )
						if (((String) mapRow.get("15")).indexOf("E")>0 )
							estructura.setClientePrivilege(((String)mapRow.get("15")).substring(0,((String)mapRow.get("15")).length()));
						else							
							estructura.setClientePrivilege(((String)mapRow.get("15")).substring(0,10));
					else estructura.setClientePrivilege((String)mapRow.get("15"));
				else estructura.setClientePrivilege(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("16")) )
					if (((String)mapRow.get("16")).length() > 10 )
						if (((String) mapRow.get("16")).indexOf("E")>0 )
							estructura.setTransaccionesPrivilege(((String)mapRow.get("16")).substring(0,((String)mapRow.get("16")).length()));
						else														
							estructura.setTransaccionesPrivilege(((String)mapRow.get("16")).substring(0,10));
					else estructura.setTransaccionesPrivilege((String)mapRow.get("16"));
				else estructura.setTransaccionesPrivilege(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("17")) )
					if (((String)mapRow.get("17")).length() > 6 )
						estructura.setRetencionSegPedido(((String)mapRow.get("17")).substring(0,6));
					else estructura.setRetencionSegPedido((String)mapRow.get("17"));
				else estructura.setRetencionSegPedido(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("18")) )
					if (((String)mapRow.get("18")).length() > 6 )
						estructura.setRetencionTerPedido(((String)mapRow.get("18")).substring(0,6));
					else estructura.setRetencionTerPedido((String)mapRow.get("18"));
				else estructura.setRetencionTerPedido(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("19")) )
					if (((String)mapRow.get("19")).length() > 6 )
						estructura.setRetencionCuarPedido(((String)mapRow.get("19")).substring(0,6));
					else estructura.setRetencionCuarPedido((String)mapRow.get("19"));
				else estructura.setRetencionCuarPedido(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("20")) )
					if (((String)mapRow.get("20")).length() > 12 )
						if (((String) mapRow.get("20")).indexOf("E")>0 )
							estructura.setUnidades(((String)mapRow.get("20")).substring(0,((String)mapRow.get("20")).length()));
						else																					
							estructura.setUnidades(((String)mapRow.get("20")).substring(0,12));
					else estructura.setUnidades((String)mapRow.get("20"));
				else estructura.setUnidades(null);
					
				if (StringUtils.isNotEmpty((String)mapRow.get("21")) )
					if (((String)mapRow.get("21")).length() > 6 )
						estructura.setPorcentajeRetencion(((String)mapRow.get("21")).substring(0,6));
					else estructura.setPorcentajeRetencion((String)mapRow.get("21"));
				else estructura.setPorcentajeRetencion(null);			
			}
				
				estructura.setFila(fila);
		
				//insertamos en BD la estructura Geografica recuperada del Excel
				fuenteVentasDAO.insertEstructuraCargaFuenteVentaPrevista(estructura);
			
		}
	
	excelUtil.cerrar();
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.FuenteVentasService#getErroresCargaFuenteVentaPrevista()
	 */
	public List getErroresCargaFuenteVentaPrevista(){
		return fuenteVentasDAO.getErroresCargaFuenteVentaPrevista();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.FuenteVentasService#getCalcularCargaFuenteVentaPrevista(java.util.Map)
	 */
	public List getCalcularCargaFuenteVentaPrevista(Map datos) {
		return fuenteVentasDAO.getCalcularCargaFuenteVentaPrevista(datos);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.FuenteVentasService#actualizarCargaFuenteVentaPrevista()
	 */
	public void actualizarCargaFuenteVentaPrevista(Map datos){		
		fuenteVentasDAO.actualizarCargaFuenteVentaPrevista(datos);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.FuenteVentasService#getFechaCalculo()
	 */
	public List getFechaCalculo(){
		return fuenteVentasDAO.getFechaCalculo();		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.FuenteVentasService#getPeriodosYaCargados(java.util.Map)
	 */
	public List getPeriodosYaCargados(Map datos) throws Exception{
		String directorioTemporal = (String)datos.get("directorioTemporal");
		String nombreArchivo = (String)datos.get("nombreArchivo");
		String indTipoFvp = (String)datos.get("indTipoFvp");
		
		String celda = new String("");
					
		if( StringUtils.equalsIgnoreCase(indTipoFvp, Constants.NUMERO_UNO) )
			celda = Constants.NUMERO_TRES;
		else
			celda = Constants.NUMERO_CINCO;
			
			//Abrimos el archivo Excel para su procesamiento		
			ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);
			
			//nos colocamos en la primera hoja del documento Excel
			excelUtil.initSheet(0);
		
			//nos pasamos a la segunda fila, ya que el primero se encuentra la cabecera
			excelUtil.next();
		
			List lista1= new ArrayList();
			String codigoPeriodo = null;			
				
			//recorrer archivo y obtener lista de periodos no repetidos
				while(excelUtil.hasNext()) {
					Map mapRow = excelUtil.next();										
					
					if (StringUtils.isNotEmpty((String)mapRow.get(celda)) ){
						if (((String)mapRow.get(celda)).length() > 6 ){
							codigoPeriodo = ((String)mapRow.get(celda)).substring(0,6);
							}
							else{ 
							codigoPeriodo = (String)mapRow.get(celda);
							}
							log.debug("codigoPer"+codigoPeriodo);
							if (!lista1.contains(codigoPeriodo)){
								lista1.add(codigoPeriodo);		
							}
						}
				}			
			excelUtil.cerrar();	
			
			String [] lista= new String[lista1.size()];
			List periodos = new ArrayList();
			
			for (int i=0; i<lista1.size();i++){
				lista[i] = (String)lista1.get(i);
			}
			
			Map criteria = new HashMap(); 
			criteria.put("lista",lista);
			
		if( StringUtils.equalsIgnoreCase(indTipoFvp, Constants.NUMERO_UNO) )
			periodos = fuenteVentasDAO.getPeriodosYaCargadosSeccion(criteria);
		else
			periodos = fuenteVentasDAO.getPeriodosYaCargados(criteria);			
			
			return periodos;					
	}
}

