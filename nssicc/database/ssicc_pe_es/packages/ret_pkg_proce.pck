CREATE OR REPLACE PACKAGE RET_PKG_PROCE IS

  /* Declaracion de Variables */
  ln_sqlcode   NUMBER(10);
  ls_sqlerrm   VARCHAR2(150);
  W_FILAS      NUMBER:=1000;

/***************************************************************************
Descripcion       : Actualiza en la Tabla RET_VENTA_CABEC Campo GZON
Fecha Creacion    : 22/01/2010
Parametros Entrada :
                 psCodigoPais : codigo Pais
                 psCodigoSBAC : codigo SBAC
                 psCodigoTipoDocum :  codigo Tipo documento
                 psNumDocuReta: numero Documento Retail
                 psIndAsigGzon: Valor del Indicador IND_ASIG_GZON
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE RET_PR_UPDAT_VENTA_CABEC_GZONA(
   psCodigoPais      VARCHAR2,
   psCodigoSBAC      VARCHAR2,
   psCodigoTipoDocum VARCHAR2,
   psNumDocuReta     VARCHAR2,
   psIndAsigGzon     VARCHAR2
);

  /***************************************************************************
  Descripcion       : Proceso de Asignacion de Ventas Retail a Gerentes de Zona
  Fecha Creacion    : 06/07/2009
  Parametros Entrada :
                   psCodigoPais :        codigo Pais
                   psFechaInicio :       fecha Inicio
                   psFechaFin :          fecha Fin
                   psIndicadorReproceso: indicador Reproceso

  Autor             : Sergio Apaza
  Fecha Actualiza   : 15/04/2011
  Autor Actualiza   : Carlos Diaz Valverde
  ***************************************************************************/
  PROCEDURE RET_PR_ASIG_VENTA_RETAI_GZONA(psCodigoPais              VARCHAR2,
                                          psFechaInicio             VARCHAR2,
                                          psFechaFin                VARCHAR2);

  /***************************************************************************
  Descripcion       : Proceso de Reasignacion de Gerentes de Zona
  Fecha Creacion    : 06/07/2009
  Parametros Entrada :
                 psCodigoPais :        codigo Pais
                 psCodigoRegiones :    codigo de Regiones
                 psCodigoZonas :       codigo de Zonas
                 psFechaInicio:        fecha de Inicio
                 psFechaFin:           fecha Fin

  Autor             : Sergio Apaza
  ***************************************************************************/
  PROCEDURE RET_PR_REASI_GZONA(psCodigoPais              VARCHAR2,
                               psCodigoRegiones          VARCHAR2,
                               psCodigoZonas             VARCHAR2,
                               psFechaInicio             VARCHAR2,
                               psFechaFin                VARCHAR2);

  /***************************************************************************
  Descripcion       : Proceso de Calculo Retail a Gerentes de Zona
  Fecha Creacion    : 08/07/2009
  Parametros Entrada :
                   psCodigoPais :        codigo Pais
                   psCodigoRegiones :    codigo de Regiones
                   psCodigoZonas :       codigo de Zonas
                   psFechaInicio:        fecha de Inicio
                   psFechaFin:           fecha Fin

  Autor             : Sergio Apaza
  ***************************************************************************/
  PROCEDURE RET_PR_CALCU_COMIS_RETAI_GZONA(psCodigoPais              VARCHAR2,
                                           psCodigoRegiones          VARCHAR2,
                                           psCodigoZonas             VARCHAR2,
                                           psFechaInicio             VARCHAR2,
                                           psFechaFin                VARCHAR2);


  /**************************************************************************
  Descripcion        : Obtiene el Gerente de Zona Historico de acuerdo a la fecha
                       y zon pasada como parametro
  Fecha Creacion     : 06/07/2009
  Parametros Entrada :
             psCodigoPais : codigo Pais
             psCodigoZona : codigo de Zona
             pdFecha      : fecha
             devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
                                    Si es true : devuelve -1
                                    Si es false: devuelve Excepcion (Por defecto)
  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION RET_FN_OBTIE_GZONA_HISTO(psCodigoPais        VARCHAR2,
                                    psCodigoZona        VARCHAR2,
                                    pdFecha             DATE,
                                    devuelveValorNoData BOOLEAN := FALSE)
  RETURN VARCHAR2;

  /**************************************************************************
  Descripcion        : Obtiene Codigo de Empleado del cliente
  Fecha Creacion     : 06/07/2009
  Parametros Entrada :
             psCodigoPais : codigo Pais
             psCodigoZona : codigo de Zona

  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION RET_FN_OBTIE_CODIG_EMPLE(psCodigoPais       VARCHAR2,
                                    psCodigoCliente    VARCHAR2)
  RETURN VARCHAR2;

  /**************************************************************************
  Descripción        : Devuelve Lista de Escalas de Descuento
  Fecha Creación     : 17/12/2015
  Autor              : Richar Cruzado
  Parámetros         :
  ***************************************************************************/
  FUNCTION RET_FN_OBTIE_ESCAL_DESCU

  RETURN  TTAB_RET_INFOR_ESCAL_DESCU;

  /**************************************************************************
  Descripción        : Devuelve Datos Consultora
  Fecha Creación     : 02/02/2016
  Autor              : Sandro Quintana
  Parámetros         :
  ***************************************************************************/
  FUNCTION RET_FN_OBTIE_DATOS_CONSU(psCodigoConsultora    VARCHAR2,
                                    psNumeroDocumento    	 VARCHAR2,
									                  psPrimerApellido		   VARCHAR2,
									                  psSegundoApellido		   VARCHAR2,
									                  psNombres				       VARCHAR2                                   
                                    )

  RETURN  ttab_ret_datos_consu;

  /**************************************************************************
  Descripción        : Obtiene Datos de Consultora
  Fecha Creación     : 22/12/2015
  Parametros Entrada :
             psCodigoConsultora : código Consultora
             psNumeroDocumento  : número de Documento
			 psPrimerApellido	: primer Apellido
			 psSegundoApellido	: segundo Apellido
			 psNombres			: nombres

  Autor              : Richar Cruzado
  ***************************************************************************/
  PROCEDURE RET_PR_OBTIE_DATOS_CONSU(psCodigoConsultora    VARCHAR2,
                                    psNumeroDocumento    	 VARCHAR2,
									                  psPrimerApellido		   VARCHAR2,
									                  psSegundoApellido		   VARCHAR2,
									                  psNombres				       VARCHAR2                                   
                                    );
  --RETURN TTAB_RET_DATOS_CONSU;

  /**************************************************************************
  Descripción        : Obtiene Información de Precios de un Producto
  Fecha Creación     : 22/12/2015
  Parametros Entrada :
             psCodigoPeriodo	: código Período
             psCodigoSap  		: código SAP

  Autor              : Richar Cruzado
  ***************************************************************************/
  FUNCTION RET_FN_OBTIE_PRECI_PRODU(psCodigoPeriodo       VARCHAR2,
                                    psCodigoSap    	      VARCHAR2)
  RETURN TTAB_RET_PRECI_PRODU;

  /**************************************************************************
  Descripción        : Obtiene Información de Consulta de Factura
  Fecha Creación     : 28/12/2015
  Parametros Entrada :
             psNumeroFactura	: número de Factura
             psNumeroPedido  	: número de Pedido
             psCodigoPeriodo  : código de Campaña

  Autor              : Richar Cruzado
  ***************************************************************************/
  FUNCTION RET_FN_OBTIE_CONSU_FACTU(psNumeroFactura       VARCHAR2,
                                    psNumeroPedido    	  VARCHAR2,
                                    psCodigoPeriodo       VARCHAR2
                                    )
    RETURN TTAB_RET_CONSU_FACTU;
  /**************************************************************************
  Descripción        : Inserta Venta de Devolución
  Fecha Creación     : 29/12/2015
  Parametros Entrada :
            psCodPais                : código País
            psSociedad 				       : sociedad
            psCanal					         : canal
            psAcceso				         : acceso
            psSubAcceso				       : subAcceso
            psLocal					         : local
            psTipoDocumentoLegal	   : tipo documento legal
            psSerieDocumentoLegal	   : serie documento legal
            psNroDocumentoLegal	     : número documento legal
            psCajaEmisora			       : caja emisora
            psNroDocInterno			     : número documento interno
            psTipoCliente			       : tipo cliente
            psNombreCliente			     : nombre cliente
            psCodigoConsultCliente   : código consultor cliente
            psFecEmisionComproban	   : fecha emisión comprobante
            psTipoImpuesto			     : tipo impuesto
            psTasaImpuesto			     : tasa impuesto
            psCampana				         : campania
            psTipoDocReferencial	   : tipo documento referencial
            psSerieDocReferencial	   : serie documento referencial
            psNroDocReferencial		   : número documento referencial
            psFechaDocReferencia	   : fecha documento referencial
            psAnulado				         : anulado
            psRUC					           : ruc
            psTipoDocIdentClient	   : tipo documento identificación cliente
            psNroDocIdentClient		   : número documento identificación cliente
            psInteresMora			       : interés mora
            psBaseImponible			     : base imponible
            psDescuento				       : descuento
            psComisiones			       : comisiones
            psFlete					         : flete
            psBaseImponibleNeto		   : base imponible neto
            psImpuesto				       : impuesto
            psImporteTotal			     : importe total
            psFacturaGratuita		     : factura gratuita
            psSerieComprobantPercep	 : serie comprobante percepción
            psNroComprobantPercep	   : número comprobante percepción
            psSecuenComprobantPercep : secuencia comprobante percepción
            psMontoTotalDocumentLegl : monto total documento legal
            psMontoPagoAplicado		   : monto pago aplicado
            psMontoOperacion		     : monto operación
            psPorentajePercepcion	   : porcentaje percepción
            psFlagProceso			       : flag proceso
            psFlagCancelacion		     : flag cancelación
            psHoraProceso			       : hora proceso
            psFechaProceso			     : fecha proceso
            psFlagCierreRVC			     : flag cierre RVC
            psFacturaAnulada		     : factura anulada
            psLote					         : lote

  Autor              : Richar Cruzado
  ***************************************************************************/
  PROCEDURE RET_PR_INSER_CONSU_VENTA_DEVOL(psCodPais       		 	    VARCHAR2,
                                          psSociedad    		 	      VARCHAR2,
                                          psCanal    			 	        VARCHAR2,
                                          psAcceso    			 	      VARCHAR2,
                                          psSubAcceso    		 	      VARCHAR2,
                                          psLocal    			 	        VARCHAR2,
                                          psTipoDocumentoLegal      VARCHAR2,
                                          psSerieDocumentoLegal     VARCHAR2,
                                          psNroDocumentoLegal       VARCHAR2,
                                          psCajaEmisora    	        VARCHAR2,
                                          psNroDocInterno    	 	    VARCHAR2,
                                          psTipoCliente    		 	    VARCHAR2,
                                          psNombreCliente    	 	    VARCHAR2,
                                          psCodigoConsultCliente    VARCHAR2,
                                          psFecEmisionComproban     VARCHAR2,
                                          psTipoImpuesto    	 	    VARCHAR2,
                                          psTasaImpuesto    	 	    VARCHAR2,
                                          psCampana    			 	      VARCHAR2,
                                          psTipoDocReferencial      VARCHAR2,
                                          psSerieDocReferencial  	  VARCHAR2,
                                          psNroDocReferencial    	  VARCHAR2,
                                          psFechaDocReferencia   	  VARCHAR2,
                                          psAnulado   			 	      VARCHAR2,
                                          psRUC   				 	        VARCHAR2,
                                          psTipoDocIdentClient   	  VARCHAR2,
                                          psNroDocIdentClient   	  VARCHAR2,
                                          psInteresMora   			    VARCHAR2,
                                          psBaseImponible   		    VARCHAR2,
                                          psDescuento   			      VARCHAR2,
                                          psComisiones   			      VARCHAR2,
                                          psFlete   				        VARCHAR2,
                                          psBaseImponibleNeto   	  VARCHAR2,
                                          psImpuesto   				      VARCHAR2,
                                          psImporteTotal   			    VARCHAR2,
                                          psFacturaGratuita   		  VARCHAR2,
                                          psSerieComprobantPercep   VARCHAR2,
                                          psNroComprobantPercep     VARCHAR2,
                                          psSecuenComprobantPercep  VARCHAR2,
                                          psMontoTotalDocumentLegl  VARCHAR2,
                                          psMontoPagoAplicado   	  VARCHAR2,
                                          psMontoOperacion   		    VARCHAR2,
                                          psPorentajePercepcion   	VARCHAR2,
                                          psFlagProceso   			    VARCHAR2,
                                          psFlagCancelacion   		  VARCHAR2,
                                          psHoraProceso   			    VARCHAR2,
                                          psFechaProceso   			    VARCHAR2,
                                          psFlagCierreRVC   		    VARCHAR2,
                                          psFacturaAnulada   		    VARCHAR2,
                                          psLote   					        VARCHAR2,
                                          psStatus          OUT VARCHAR2,
                                          psMensaje         OUT VARCHAR2);

/**************************************************************************
  Descripcion        : Obtiene Informacion Consulta de Catalogos
  Fecha Creacion     : 21/01/2016
  Autor              : Richar Cruzado
  Parametros         :
  ***************************************************************************/
  FUNCTION RET_FN_OBTIE_CONSU_CATAG

  RETURN  TTAB_RET_CONSU_CATAG;

END RET_PKG_PROCE;
/
CREATE OR REPLACE PACKAGE BODY RET_PKG_PROCE IS

/***************************************************************************
Descripcion       : Actualiza en la Tabla RET_VENTA_CABEC Campo GZON
Fecha Creacion    : 22/01/2010
Parametros Entrada :
                 psCodigoPais : codigo Pais
                 psCodigoSBAC : codigo SBAC
                 psCodigoTipoDocum :  codigo Tipo documento
                 psNumDocuReta: numero Documento Retail
                 psIndAsigGzon: Valor del Indicador IND_ASIG_GZON
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE RET_PR_UPDAT_VENTA_CABEC_GZONA(
   psCodigoPais      VARCHAR2,
   psCodigoSBAC      VARCHAR2,
   psCodigoTipoDocum VARCHAR2,
   psNumDocuReta     VARCHAR2,
   psIndAsigGzon     VARCHAR2
)
IS
BEGIN
 UPDATE RET_VENTA_CABEC
 SET IND_ASIG_GZON = psIndAsigGzon
 WHERE COD_PAIS = psCodigoPais
   AND COD_SBAC = psCodigoSBAC
   AND COD_TIPO_DOCU = psCodigoTipoDocum
   AND NUM_DOCU_RETA = psNumDocuReta;
END RET_PR_UPDAT_VENTA_CABEC_GZONA;


/***************************************************************************
Descripcion       : Proceso de Asignacion de Ventas Retail a Gerentes de Zona
Fecha Creacion    : 06/07/2009
Parametros Entrada :
                 psCodigoPais :        codigo Pais
                 psFechaInicio :       fecha Inicio
                 psFechaFin :          fecha Fin
                 psIndicadorReproceso: indicador Reproceso

Autor             : Sergio Apaza
Fecha Actualiza   : 15/04/2011
Autor Actualiza   : Carlos Diaz Valverde
***************************************************************************/
PROCEDURE RET_PR_ASIG_VENTA_RETAI_GZONA(psCodigoPais              VARCHAR2,
                                        psFechaInicio             VARCHAR2,
                                        psFechaFin                VARCHAR2)
IS
CURSOR c_ConsultorasRetail IS
  SELECT cab.*
    FROM    RET_VENTA_CABEC cab,
            RET_PARAM_DOCUM_COMIS par
   WHERE cab.COD_PAIS = par.COD_PAIS
     AND cab.COD_TIPO_DOCU = par.COD_TIPO_DOCU
     AND cab.COD_PAIS = psCodigoPais
     AND NVL(cab.IND_PROC, '0') <> '1'
     AND (PAR.COD_TIPO_TRAN = 'V' OR PAR.COD_TIPO_TRAN = 'D')
     AND    cab.VAL_CUEN_CONSU IS NOT NULL
     AND    TRUNC(FEC_ENVI) >= TO_DATE(psFechaInicio, 'DD/MM/YYYY')
     AND    TRUNC(FEC_ENVI) <= TO_DATE(psFechaFin, 'DD/MM/YYYY');


  TYPE interfazConsultorassTab  IS TABLE OF RET_VENTA_CABEC%ROWTYPE;
  interfazConsultoras     interfazConsultorassTab;

  lnOidCliente           MAE_CLIEN.OID_CLIE%TYPE;

  lsCodigoSubGerencia    ZON_SUB_GEREN_VENTA.COD_SUBG_VENT%TYPE;
  lsCodigoRegion         ZON_REGIO.COD_REGI%TYPE;
  lsCodigoZona           ZON_ZONA.COD_ZONA%TYPE;
  lsCodigoSeccion        ZON_SECCI.COD_SECC%TYPE;

  lsCodigoGerenteZona    MAE_CLIEN.COD_CLIE%TYPE;
  lsCodigoEmpleado       MAE_CLIEN_DATOS_ADICI.COD_EMPL%TYPE;
  lnImporteVenta         RET_VENTA_GZONA.IMP_VENT%TYPE;
  lsTipoTransaccion      RET_PARAM_DOCUM_COMIS.Cod_Tipo_Tran%TYPE;
  lsErrorIndicador       BOOLEAN :=FALSE;
  lnImporteDevolucion    RET_VENTA_GZONA.IMP_DEVO%TYPE;
  lsCodPeriodo           seg_perio_corpo.cod_peri%TYPE;
  lbContinuar            BOOLEAN :=TRUE;
  lnIdPais               NUMBER;
  lnIdUnidadAdm          NUMBER;
  lsNomLider             VARCHAR2(500);
  lnContarFilas          NUMBER;

BEGIN

  lnIdPais       := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodigoPais);

  --ELIMINAMOS LOS REGISTROS CUYA VENTA ESTE EN EL RANGO DE FECHA INICIO Y FIN
  DELETE
  FROM      RET_VENTA_GZONA
    WHERE  COD_PAIS = psCodigoPais
      AND TRUNC(FEC_VENT) >= TO_DATE(psFechaInicio, 'DD/MM/YYYY')
      AND TRUNC(FEC_VENT) <= TO_DATE(psFechaFin, 'DD/MM/YYYY');

    UPDATE RET_VENTA_CABEC
      SET IND_PROC = NULL,
          IND_ASIG_GZON = NULL
    WHERE COD_PAIS = psCodigoPais
      AND TRUNC(FEC_ENVI) >= TO_DATE(psFechaInicio, 'DD/MM/YYYY')
      AND TRUNC(FEC_ENVI) <= TO_DATE(psFechaFin, 'DD/MM/YYYY');

  --PROCESAMOS LAS CONSULTORAS QUE HAN REALIZADO SU COMPRA x RETAIL
  OPEN c_ConsultorasRetail;
  LOOP
    FETCH c_ConsultorasRetail BULK COLLECT INTO interfazConsultoras LIMIT W_FILAS;
    IF interfazConsultoras.COUNT > 0 THEN

      FOR x IN interfazConsultoras.FIRST .. interfazConsultoras.LAST LOOP

        -- Validar si se exluye la venta de la consultora según local
        SELECT    COUNT(1)
          INTO    lnContarFilas
        FROM      RET_LOCAL_SINCO
        WHERE     COD_PAIS = interfazConsultoras(x).COD_PAIS
          AND     COD_CANA = interfazConsultoras(x).COD_CANA
          AND     COD_ACCE = interfazConsultoras(x).COD_ACCE
          AND     COD_SBAC = interfazConsultoras(x).COD_SBAC;

        IF lnContarFilas = 0 THEN

        lsErrorIndicador := FALSE;
        lbContinuar := TRUE;
        lsCodPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_PERIO_FECHA(
                     interfazConsultoras(x).COD_PAIS,
                     'T',
                     'VD',
                     TRUNC(interfazConsultoras(x).FEC_ENVI));
        lnOidCliente := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(interfazConsultoras(x).VAL_CUEN_CONSU, true);

        IF(lnOidCliente = -1) THEN
          RET_PR_UPDAT_VENTA_CABEC_GZONA(interfazConsultoras(x).COD_PAIS,
             interfazConsultoras(x).COD_SBAC,
             interfazConsultoras(x).COD_TIPO_DOCU,
             interfazConsultoras(x).NUM_DOCU_RETA,
             '0'
             );
          lbContinuar := FALSE;
        END IF;

        --RECUPERAMOS LA REGION, ZONA, SECCION DE LA UNIDAD ADMINISTRATIVA DE LA CONSULTORA
        IF lbContinuar THEN
          BEGIN
            -- Devuelve Unidad Administrativa de la Consultora para el Periodo
            lnIdUnidadAdm := COM_PKG_REPOR.COM_FN_DEVUE_OID_UNADM_HISTO(
                                      lnOidCliente,
                                      lsCodPeriodo);
            IF lnIdUnidadAdm = -1 THEN
               lsCodigoGerenteZona := NULL;
               lsErrorIndicador := TRUE;
               lbContinuar := FALSE;
               RET_PR_UPDAT_VENTA_CABEC_GZONA(interfazConsultoras(x).COD_PAIS,
                   interfazConsultoras(x).COD_SBAC,
                   interfazConsultoras(x).COD_TIPO_DOCU,
                   interfazConsultoras(x).NUM_DOCU_RETA,
                   '2'
                   );
            END IF;
            SELECT L.COD_SUBG_VENT, re.COD_REGI, zo.COD_ZONA, se.COD_SECC
              INTO lsCodigoSubGerencia, lsCodigoRegion, lsCodigoZona, lsCodigoSeccion
              FROM ZON_REGIO re, ZON_ZONA zo, ZON_SECCI se, ZON_TERRI_ADMIN ta, MAE_CLIEN_UNIDA_ADMIN ua,
                   ZON_SUB_GEREN_VENTA L
             WHERE ua.oid_clie_unid_admi = lnIdUnidadAdm
               AND ua.ZTAD_OID_TERR_ADMI = ta.OID_TERR_ADMI
               AND ta.ZSCC_OID_SECC = se.OID_SECC
               AND se.ZZON_OID_ZONA = zo.OID_ZONA
               AND zo.ZORG_OID_REGI = re.OID_REGI
               AND L.OID_SUBG_VENT = RE.ZSGV_OID_SUBG_VENT;
          EXCEPTION
          WHEN OTHERS THEN
               lsCodigoGerenteZona := NULL;
               lsErrorIndicador := TRUE;
               lbContinuar := FALSE;
               RET_PR_UPDAT_VENTA_CABEC_GZONA(interfazConsultoras(x).COD_PAIS,
                   interfazConsultoras(x).COD_SBAC,
                   interfazConsultoras(x).COD_TIPO_DOCU,
                   interfazConsultoras(x).NUM_DOCU_RETA,
                   '2'
                   );
          END;
        END IF;

        --RECUPERAMOS EL CODIGO DE LA GERENTE DE ZONA
        IF lbContinuar THEN
          BEGIN
            --lsCodigoGerenteZona := RET_FN_OBTIE_GZONA_HISTO(psCodigoPais, lsCodigoZona, TRUNC(interfazConsultoras(x).FEC_ENVI), TRUE);
            lsNomLider := COM_PKG_REPOR.COM_FN_DEVUE_RESPO_UNIAD_HISTO(
                                          lsCodigoGerenteZona,
                                          TRUNC(interfazConsultoras(x).FEC_ENVI),
                                          TRUNC(interfazConsultoras(x).FEC_ENVI),
                                          lnIdPais,
                                          lsCodigoSubGerencia,
                                          lsCodigoRegion,
                                          lsCodigoZona,
                                          NULL);
          EXCEPTION
          WHEN OTHERS THEN
               lsCodigoGerenteZona := NULL;
               lsErrorIndicador := TRUE;
               lbContinuar := FALSE;
               RET_PR_UPDAT_VENTA_CABEC_GZONA(interfazConsultoras(x).COD_PAIS,
                 interfazConsultoras(x).COD_SBAC,
                 interfazConsultoras(x).COD_TIPO_DOCU,
                 interfazConsultoras(x).NUM_DOCU_RETA,
                 '2'
                 );
          END;
        END IF;

        IF lbContinuar THEN
          IF(lsCodigoGerenteZona IS NOT NULL) THEN --NO ENCONTRO GERENTE DE ZONA o NO TIENE UNIDAD ADMINISTRATIVA EL CLIENTE
            --RECUPERAMOS EL CODIGO DE PLANILLA DEL GERENTE
            BEGIN
               lsCodigoEmpleado := RET_FN_OBTIE_CODIG_EMPLE(psCodigoPais, lsCodigoGerenteZona);
               IF lsCodigoEmpleado IS NULL THEN
                  lbContinuar := FALSE;
                  RET_PR_UPDAT_VENTA_CABEC_GZONA(interfazConsultoras(x).COD_PAIS,
                     interfazConsultoras(x).COD_SBAC,
                     interfazConsultoras(x).COD_TIPO_DOCU,
                     interfazConsultoras(x).NUM_DOCU_RETA,
                     '2'
                     );
               END IF;
            EXCEPTION
            WHEN OTHERS THEN
               lbContinuar := FALSE;
               RET_PR_UPDAT_VENTA_CABEC_GZONA(interfazConsultoras(x).COD_PAIS,
                 interfazConsultoras(x).COD_SBAC,
                 interfazConsultoras(x).COD_TIPO_DOCU,
                 interfazConsultoras(x).NUM_DOCU_RETA,
                 '2'
                 );
            END;

            IF lbContinuar THEN
                --OBTENEMOS TIPO DE TRANSACCION
                SELECT A.COD_TIPO_TRAN
                INTO lsTipoTransaccion
                FROM RET_PARAM_DOCUM_COMIS A
                WHERE A.COD_PAIS = interfazConsultoras(x).COD_PAIS
                AND A.COD_TIPO_DOCU = interfazConsultoras(x).COD_TIPO_DOCU;

                --RECUPERAMOS EL IMPORTE DE VENTA = Total Monto Con Descuento  - Total Monto IMPUESTO
                IF lsTipoTransaccion = 'V' THEN
                    SELECT ROUND(NVL(SUM(det.VAL_MONT_DSCT) - SUM(det.MON_IMPU),0), 2)
                      INTO lnImporteVenta
                      FROM RET_VENTA_DETAL det
                     WHERE COD_PAIS = interfazConsultoras(x).COD_PAIS
                       AND COD_SBAC = interfazConsultoras(x).COD_SBAC
                       AND COD_TIPO_DOCU = interfazConsultoras(x).COD_TIPO_DOCU
                         AND NUM_DOCU_RETA = interfazConsultoras(x).NUM_DOCU_RETA
                         AND VAL_SERI_DOCU = interfazConsultoras(x).VAL_SERI_DOCU;
                ELSE
                   lnImporteVenta := 0;
                END IF;

                --RECUPERAMOS EL IMPORTE DE Devolucion = Total Monto Devuelto  - Total Monto IMPUESTO
                IF lsTipoTransaccion = 'D' THEN
                    SELECT ROUND(NVL(SUM(det.mon_devu) - SUM(det.MON_IMPU),0), 2)
                      INTO lnImporteDevolucion
                      FROM RET_VENTA_DETAL det
                     WHERE COD_PAIS = interfazConsultoras(x).COD_PAIS
                       AND COD_SBAC = interfazConsultoras(x).COD_SBAC
                       AND COD_TIPO_DOCU = interfazConsultoras(x).COD_TIPO_DOCU
                         AND NUM_DOCU_RETA = interfazConsultoras(x).NUM_DOCU_RETA
                         AND VAL_SERI_DOCU = interfazConsultoras(x).VAL_SERI_DOCU;
                ELSE
                   lnImporteDevolucion := 0;
                END IF;

                --INSERTAMOS UN REGISTRO EN LA TABLA RET_VENTA_GZONA
                INSERT INTO RET_VENTA_GZONA
                  (COD_PAIS, COD_CANA, COD_ACCE,
                   COD_SBAC, COD_TIPO_DOCU, VAL_SERI_DOCU,
                   NUM_DOCU_RETA, COD_CONS, FEC_VENT,
                   COD_PERI, COD_REGI, COD_ZONA,
                   COD_SECC, COD_GERE_ZONA, COD_PLAN_GERE,
                   IMP_VENT, IND_ANUL, FEC_PROC,
                   COD_TIPO_TRAN, IMP_DEVO)
                VALUES
                  (interfazConsultoras(x).COD_PAIS,  interfazConsultoras(x).COD_CANA, interfazConsultoras(x).COD_ACCE,
                   interfazConsultoras(x).COD_SBAC, interfazConsultoras(x).COD_TIPO_DOCU, interfazConsultoras(x).VAL_SERI_DOCU,
                   interfazConsultoras(x).NUM_DOCU_RETA, interfazConsultoras(x).VAL_CUEN_CONSU, interfazConsultoras(x).FEC_ENVI,
                   interfazConsultoras(x).CAM_PROC, lsCodigoRegion, lsCodigoZona,
                   lsCodigoSeccion, lsCodigoGerenteZona, lsCodigoEmpleado,
                   lnImporteVenta, interfazConsultoras(x).IND_ANUL, SYSDATE,
                   lsTipoTransaccion, lnImporteDevolucion );


                RET_PR_UPDAT_VENTA_CABEC_GZONA(interfazConsultoras(x).COD_PAIS,
                   interfazConsultoras(x).COD_SBAC,
                   interfazConsultoras(x).COD_TIPO_DOCU,
                   interfazConsultoras(x).NUM_DOCU_RETA,
                   '1'
                   );
            END IF;

          END IF;
        END IF;

        END IF; -- Validar si se exluye la venta de la consultora según local

      END LOOP; -- Fin del FOR interfazConsultoras

    END IF;
    EXIT WHEN c_ConsultorasRetail%NOTFOUND;
  END LOOP;
  CLOSE c_ConsultorasRetail;

  --POR ULTIMO SE DEBERÁ ACTUALIZAR LA TABLA RET_VENTAS_CABEC, CON EL IND_PROC = 1
  UPDATE RET_VENTA_CABEC
     SET IND_PROC = '1';

EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR RET_PR_ASIG_VENTA_RETAI_GZONA: (' || ls_sqlerrm);
END RET_PR_ASIG_VENTA_RETAI_GZONA;


/***************************************************************************
Descripcion       : Proceso de Reasignacion de Gerentes de Zona
Fecha Creacion    : 06/07/2009
Parametros Entrada :
                 psCodigoPais :        codigo Pais
                 psCodigoRegiones :    codigo de Regiones
                 psCodigoZonas :       codigo de Zonas
                 psFechaInicio:        fecha de Inicio
                 psFechaFin:           fecha Fin

Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE RET_PR_REASI_GZONA(psCodigoPais              VARCHAR2,
                             psCodigoRegiones          VARCHAR2,
                             psCodigoZonas             VARCHAR2,
                             psFechaInicio             VARCHAR2,
                             psFechaFin                VARCHAR2)
IS
CURSOR c_ConsultorasRetail IS
  SELECT ven.*
    FROM RET_VENTA_GZONA ven
   WHERE ven.COD_PAIS = psCodigoPais
     AND TRUNC(FEC_VENT) >= TO_DATE(psFechaInicio, 'DD/MM/YYYY')
     AND TRUNC(FEC_VENT) <= TO_DATE(psFechaFin, 'DD/MM/YYYY')
     AND (psCodigoRegiones IS NULL OR (INSTR(psCodigoRegiones, COD_REGI) > 0))
     AND (psCodigoZonas IS NULL OR (INSTR(psCodigoZonas, COD_ZONA) > 0));


  TYPE interfazConsultorassTab  IS TABLE OF RET_VENTA_GZONA%ROWTYPE;
  interfazConsultoras     interfazConsultorassTab;

  lsCodigoGerenteZona    MAE_CLIEN.COD_CLIE%TYPE;
  lsCodigoEmpleado       MAE_CLIEN_DATOS_ADICI.COD_EMPL%TYPE;

BEGIN

  --PROCESAMOS LAS CONSULTORAS QUE HAN REALIZADO SU COMPRA x RETAIL
  OPEN c_ConsultorasRetail;
  LOOP
    FETCH c_ConsultorasRetail BULK COLLECT INTO interfazConsultoras LIMIT W_FILAS;
    IF interfazConsultoras.COUNT > 0 THEN

      FOR x IN interfazConsultoras.FIRST .. interfazConsultoras.LAST LOOP

        --RECUPERAMOS EL CODIGO DE LA GERENTE DE ZONA
        lsCodigoGerenteZona := RET_FN_OBTIE_GZONA_HISTO(psCodigoPais, interfazConsultoras(x).COD_ZONA,
                                                        TRUNC(interfazConsultoras(x).FEC_VENT));

        --RECUPERAMOS EL CODIGO DE PLANILLA DEL GERENTE
        lsCodigoEmpleado := RET_FN_OBTIE_CODIG_EMPLE(psCodigoPais, lsCodigoGerenteZona);

        --ACTUALIZAMOS EL CODIGO DE GERENTE DE ZONA Y SU CODIGO DE PLANILLA EN LA TABLA RET_VENTA_GZONA
        UPDATE RET_VENTA_GZONA
           SET COD_GERE_ZONA = lsCodigoGerenteZona,
               COD_PLAN_GERE = lsCodigoEmpleado
         WHERE COD_PAIS = interfazConsultoras(x).COD_PAIS
           AND COD_SBAC = interfazConsultoras(x).COD_SBAC
           AND VAL_SERI_DOCU = interfazConsultoras(x).VAL_SERI_DOCU
           AND NUM_DOCU_RETA = interfazConsultoras(x).NUM_DOCU_RETA
           AND COD_CONS = interfazConsultoras(x).COD_CONS;

      END LOOP;

    END IF;
    EXIT WHEN c_ConsultorasRetail%NOTFOUND;
  END LOOP;
  CLOSE c_ConsultorasRetail;

EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR RET_PR_REASI_GZONA: (' || ls_sqlerrm);
END RET_PR_REASI_GZONA;


/***************************************************************************
Descripcion       : Proceso de Calculo Retail a Gerentes de Zona
Fecha Creacion    : 08/07/2009
Parametros Entrada :
                 psCodigoPais :        codigo Pais
                 psCodigoRegiones :    codigo de Regiones
                 psCodigoZonas :       codigo de Zonas
                 psFechaInicio:        fecha de Inicio
                 psFechaFin:           fecha Fin

Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE RET_PR_CALCU_COMIS_RETAI_GZONA(psCodigoPais              VARCHAR2,
                                         psCodigoRegiones          VARCHAR2,
                                         psCodigoZonas             VARCHAR2,
                                         psFechaInicio             VARCHAR2,
                                         psFechaFin                VARCHAR2)
IS

  lnPorcentajeComision   RET_PARAM_PORCE_COMIS.POR_COMI%TYPE;
  ldFechaInicio          DATE;
  ldFechaFin             DATE;
BEGIN

  ldFechaInicio := TO_DATE(psFechaInicio, 'DD/MM/YYYY');
  ldFechaFin := TO_DATE(psFechaFin, 'DD/MM/YYYY');

  --ELIMINAMOS LOS REGISTROS DE LA TABLA RET_REPOR_COMIS_GZONA por el Rango de Fecha Inicio y Fecha Fin
  DELETE FROM RET_REPOR_COMIS_GZONA
   WHERE COD_PAIS = psCodigoPais
     AND FEC_INIC >= ldFechaInicio
     AND FEC_FINA <= ldFechaFin
     AND (psCodigoRegiones IS NULL OR (INSTR(psCodigoRegiones, COD_REGI) > 0))
     AND (psCodigoZonas IS NULL OR (INSTR(psCodigoZonas, COD_ZONA) > 0));

  --ELIMINAMOS LOS REGISTROS DE LA TABLA RET_COMIS_GZONA por el Rango de Fecha Inicio y Fecha Fin
  DELETE FROM RET_COMIS_GZONA
   WHERE COD_PAIS = psCodigoPais
     AND FEC_INIC >= ldFechaInicio
     AND FEC_FINA <= ldFechaFin
     AND (psCodigoRegiones IS NULL OR (INSTR(psCodigoRegiones, COD_REGI) > 0))
     AND (psCodigoZonas IS NULL OR (INSTR(psCodigoZonas, COD_ZONA) > 0));

--ELIMINAMOS LOS REGISTROS DE LA TABLA RET_COMIS_GZONA por el Rango de Fecha Inicio y Fecha Fin
  DELETE FROM RET_REPOR_VNETA_GZONA
   WHERE COD_PAIS = psCodigoPais
     AND FEC_INIC >= ldFechaInicio
     AND FEC_FINA <= ldFechaFin
     AND (psCodigoRegiones IS NULL OR (INSTR(psCodigoRegiones, COD_REGI) > 0))
     AND (psCodigoZonas IS NULL OR (INSTR(psCodigoZonas, COD_ZONA) > 0));


  --RECUPERAMOS LA COMISION POR PAIS
  BEGIN
    SELECT POR_COMI
      INTO lnPorcentajeComision
      FROM RET_PARAM_PORCE_COMIS
     WHERE COD_PAIS = psCodigoPais;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RAISE_APPLICATION_ERROR(-20123, 'No se encontro parametro de Comision para el Pais');
  END;

  --POR CADA REGISTRO DE RET_VENTA_GZONA, GRABAR EN LA TABLA RET_REPOR_COMIS_GZONA
  INSERT INTO RET_REPOR_COMIS_GZONA
    (COD_PAIS, COD_CANA, COD_ACCE,
     COD_SBAC, COD_TIPO_DOCU, VAL_SERI_DOCU,
     NUM_DOCU_RETA, COD_CONS, COD_PERI,
     COD_REGI, COD_ZONA, COD_SECC,
     COD_GERE_ZONA, COD_PLAN_GERE, IMP_VENT,
     FEC_VENT, FEC_PROC, POR_COMI,
     IMP_COMI, FEC_INIC, FEC_FINA,
     COD_TIPO_TRAN, IMP_DEVO)
  SELECT ven.COD_PAIS, ven.COD_CANA, ven.COD_ACCE,
         ven.COD_SBAC, ven.COD_TIPO_DOCU, ven.VAL_SERI_DOCU,
         ven.NUM_DOCU_RETA, ven.COD_CONS, ven.COD_PERI,
         ven.COD_REGI, ven.COD_ZONA, ven.COD_SECC,
         ven.COD_GERE_ZONA, ven.COD_PLAN_GERE, ven.IMP_VENT,
         ven.FEC_VENT, SYSDATE, lnPorcentajeComision,
         0.00, ldFechaInicio, ldFechaFin,
         COD_TIPO_TRAN, IMP_DEVO
    FROM RET_VENTA_GZONA ven
   WHERE ven.COD_PAIS = psCodigoPais
     AND (ven.IND_ANUL IS NULL OR ven.IND_ANUL <> 'A')
     AND TRUNC(FEC_VENT) >= ldFechaInicio
     AND TRUNC(FEC_VENT) <= ldFechaFin
     AND (psCodigoRegiones IS NULL OR (INSTR(psCodigoRegiones, COD_REGI) > 0))
     AND (psCodigoZonas IS NULL OR (INSTR(psCodigoZonas, COD_ZONA) > 0));


  --CONSOLIDAR LAS VENTA NETA X CONSULTORA
  INSERT INTO RET_REPOR_VNETA_GZONA
    (COD_PAIS, COD_CONS, COD_GERE_ZONA, COD_PLAN_GERE,
     NOM_GERE_ZONA, COD_REGI, COD_ZONA,
     TOT_IMPO_VENT, POR_COMI, TOT_IMPO_COMI,
     FEC_INIC, FEC_FINA, FEC_PROC, COD_SECC)
  SELECT com.COD_PAIS, com.COD_CONS, com.COD_GERE_ZONA, com.COD_PLAN_GERE,
         (SELECT TRIM(VAL_APE1)||' '||TRIM(VAL_APE2)||' '||TRIM(VAL_NOM1)||' '||TRIM(VAL_NOM2)
           FROM MAE_CLIEN WHERE COD_CLIE = com.COD_GERE_ZONA),
          com.COD_REGI,
          com.COD_ZONA,
          case
           when SUM(com.IMP_VENT) > SUM(com.IMP_DEVO) then
             SUM(com.IMP_VENT) - SUM(com.IMP_DEVO)
           else
             0.00
          end,
          lnPorcentajeComision,
          case
           when SUM(com.IMP_VENT) > SUM(com.IMP_DEVO) then
                (SUM(com.IMP_VENT) - SUM(com.IMP_DEVO)) * lnPorcentajeComision / 100
           else
                0.00
          end,
          MIN(com.FEC_VENT), MAX(com.FEC_VENT), SYSDATE,
          com.COD_SECC
    FROM RET_REPOR_COMIS_GZONA com
   WHERE com.COD_PAIS = psCodigoPais
     AND com.FEC_INIC >= ldFechaInicio
     AND com.FEC_FINA <= ldFechaFin
   GROUP BY com.COD_PAIS,
         com.COD_CONS,
         com.COD_GERE_ZONA,
         com.COD_PLAN_GERE,
         com.COD_REGI,
         com.COD_ZONA,
         com.COD_SECC;

  --CONSOLIDAR LAS COMISIONES POR GERENTE DE ZONA Y ZONA
  INSERT INTO RET_COMIS_GZONA
    (COD_PAIS, COD_GERE_ZONA, COD_PLAN_GERE,
     NOM_GERE_ZONA, COD_REGI, COD_ZONA,
     TOT_IMPO_VENT, POR_COMI, TOT_IMPO_COMI,
     FEC_INIC, FEC_FINA, FEC_PROC)
  SELECT com.COD_PAIS, com.COD_GERE_ZONA, com.COD_PLAN_GERE,
         (SELECT TRIM(VAL_APE1)||' '||TRIM(VAL_APE2)||' '||TRIM(VAL_NOM1)||' '||TRIM(VAL_NOM2)
           FROM MAE_CLIEN WHERE COD_CLIE = com.COD_GERE_ZONA), com.COD_REGI, com.COD_ZONA,
         SUM(com.TOT_IMPO_VENT), lnPorcentajeComision, SUM(com.TOT_IMPO_COMI),
         MIN(com.FEC_INIC), MAX(com.FEC_FINA), SYSDATE
    FROM RET_REPOR_VNETA_GZONA com
   WHERE com.COD_PAIS = psCodigoPais
     AND com.FEC_INIC >= ldFechaInicio
     AND com.FEC_FINA <= ldFechaFin
   GROUP BY com.COD_PAIS,
         com.COD_GERE_ZONA,
         COD_PLAN_GERE,
         com.COD_REGI,
         com.COD_ZONA;

EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR RET_PR_CALCU_COMIS_RETAI_GZONA: (' || ls_sqlerrm);
END RET_PR_CALCU_COMIS_RETAI_GZONA;


/**************************************************************************
Descripcion        : Obtiene el Gerente de Zona Historico de acuerdo a la fecha
                     y zon pasada como parametro
Fecha Creacion     : 06/07/2009
Parametros Entrada :
           psCodigoPais : codigo Pais
           psCodigoZona : codigo de Zona
           pdFecha      : fecha
           devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
                                  Si es true : devuelve -1
                                  Si es false: devuelve Excepcion (Por defecto)
Autor              : Sergio Apaza
***************************************************************************/
FUNCTION RET_FN_OBTIE_GZONA_HISTO(psCodigoPais        VARCHAR2,
                                  psCodigoZona        VARCHAR2,
                                  pdFecha             DATE,
                                  devuelveValorNoData BOOLEAN := FALSE)
RETURN VARCHAR2 IS
  lsCodigoCliente       MAE_CLIEN.COD_CLIE%TYPE;
  lnIdPais              SEG_PAIS.OID_PAIS%TYPE;
BEGIN
  /* obteniendo id Pais */
  lnIdPais := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodigoPais);

  /* Obteniendo codigo de Gerente de Zona */
  SELECT TRIM(GERE)
    INTO lsCodigoCliente
    FROM (SELECT GERE
            FROM ZON_HISTO_GEREN
           WHERE PAIS_OID_PAIS = lnIdPais
             AND UA LIKE '____' || psCodigoZona
             AND TRUNC(FEC_DESD) <= pdFecha
             AND TRUNC(FEC_HAST) >= pdFecha
           ORDER BY OID_HIST_GERE DESC)
   WHERE ROWNUM=1;

  RETURN lsCodigoCliente;

EXCEPTION
  WHEN no_data_found THEN
    BEGIN
      /* Obteniendo codigo de Gerente de Zona */
      SELECT TRIM(GERE)
        INTO lsCodigoCliente
        FROM ZON_HISTO_GEREN
       WHERE PAIS_OID_PAIS = lnIdPais
         AND UA LIKE '____' || psCodigoZona
         AND TRUNC(FEC_DESD) <= pdFecha
         AND FEC_HAST IS NULL;

      RETURN lsCodigoCliente;
    EXCEPTION
      WHEN no_data_found THEN
        IF (NOT devuelveValorNoData) THEN
          ls_sqlerrm := 'No se encontro codigo de Gerente de Zona: ' ||  psCodigoZona || ' y fecha : ' || TO_CHAR(pdFecha,'DD/MM/YYYY');
          RAISE_APPLICATION_ERROR(-20123,
                                  'ERROR RET_FN_OBTIE_GZONA_HISTO: ' ||
                                  ls_sqlerrm);
        ELSE
          RETURN NULL;
        END IF;
      WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(SQLERRM, 1, 250);
        IF ln_sqlcode < 0 THEN
          RAISE_APPLICATION_ERROR(-20123,
                                  'ERROR RET_FN_OBTIE_GZONA_HISTO: ' ||
                                  ls_sqlerrm);
        END IF;
    END;
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    IF ln_sqlcode < 0 THEN
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR RET_FN_OBTIE_GZONA_HISTO: ' ||
                              ls_sqlerrm);
    END IF;

END RET_FN_OBTIE_GZONA_HISTO;

/**************************************************************************
Descripcion        : Obtiene Codigo de Empleado del cliente
Fecha Creacion     : 06/07/2009
Parametros Entrada :
           psCodigoPais : codigo Pais
           psCodigoZona : codigo de Zona

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION RET_FN_OBTIE_CODIG_EMPLE(psCodigoPais       VARCHAR2,
                                  psCodigoCliente    VARCHAR2)
RETURN VARCHAR2
IS
  lsCodigoEmpleado      MAE_CLIEN_DATOS_ADICI.COD_EMPL%TYPE;
  lnIdPais              SEG_PAIS.OID_PAIS%TYPE;
BEGIN
  /* obteniendo id Pais */
  lnIdPais := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodigoPais);

  /* Obteniendo codigo de Empleado */
  SELECT ad.COD_EMPL
  INTO   lsCodigoEmpleado
  FROM   MAE_CLIEN_DATOS_ADICI ad, MAE_CLIEN cl
  WHERE  cl.pais_oid_pais = lnIdPais
    AND  cl.cod_clie = psCodigoCliente
    AND  cl.oid_clie = ad.clie_oid_clie;

  RETURN lsCodigoEmpleado;

EXCEPTION
  WHEN no_data_found THEN
    RETURN '';
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    IF ln_sqlcode < 0 THEN
      RAISE_APPLICATION_ERROR(-20123,  'ERROR RET_FN_OBTIE_CODIG_EMPLE: ' ||  ls_sqlerrm);
    END IF;
END RET_FN_OBTIE_CODIG_EMPLE;

/**************************************************************************
Descripción        : Obtiene Información de Escalas de Descuento
Fecha Creación     : 17/12/2015
Autor              : Richar Cruzado
Parámetros Entrada :
***************************************************************************/
FUNCTION RET_FN_OBTIE_ESCAL_DESCU

RETURN  TTAB_RET_INFOR_ESCAL_DESCU
IS
  ltDetalleEscalasDescuento TTAB_RET_INFOR_ESCAL_DESCU ;
BEGIN

    select TOBJ_RET_INFOR_ESCAL_DESCU
    ( a.cod_grup_desc,
      a.des_grup_desc,
      a.cod_rang_desc,
      a.val_impo_hasta,
      a.por_desc
    )
    bulk collect
    into ltDetalleEscalasDescuento
    from (
		-- Consulta Escala de Descuentos
		select ddg.COD_GRUP_DESC, ddg.DES_GRUP_DESC, ddgr.COD_RANG_DESC, ddgr.VAL_IMPO_HASTA, ddgr.POR_DESC
		from DTO_DESCU_GRUPO ddg, DTO_DESCU_GRUPO_RANGO ddgr
		where ddg.COD_GRUP_DESC = ddgr.cod_grup_desc
		and ddg.EST_REGI = 1
		and ddg.IND_ESCL_PRAL = 1
		and ddg.EST_REGI = 1
		order by cod_rang_desc
     )a;

    return ltDetalleEscalasDescuento;

	EXCEPTION
	WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     ltDetalleEscalasDescuento.DELETE;
     RAISE_APPLICATION_ERROR(-20123, 'ERROR RET_FN_OBTIE_ESCAL_DESCU: '||ls_sqlerrm);

	return ltDetalleEscalasDescuento;

END RET_FN_OBTIE_ESCAL_DESCU;


/**************************************************************************
Descripcion        : Obtiene Informacion de Consulta de Factura
Fecha Creacion     : 28/12/2015
Parametros Entrada :
			             psNumeroFactura	: numero de Factura
                   psNumeroPedido  	: numero de Pedido
                   psCodigoPeriodo  : codigo de Campaña

Autor              : Richar Cruzado
***************************************************************************/
FUNCTION RET_FN_OBTIE_DATOS_CONSU(psCodigoConsultora       VARCHAR2,
                                  psNumeroDocumento    	    VARCHAR2,
								                  psPrimerApellido			    VARCHAR2,
								                  psSegundoApellido		      VARCHAR2,
								                  psNombres		              VARCHAR2
                                  )
RETURN ttab_ret_datos_consu
IS
  ltDatosConsultora  ttab_ret_datos_consu ;

  lsCodigoPais  bas_ctrl_fact.cod_pais%type;
  lsCodigoConsultora          mae_clien.cod_clie%TYPE;
   lsOidClie                   mae_clien.oid_clie%TYPE;

BEGIN

  -- Se obtiene codigoPais
  select cod_pais into lsCodigoPais from bas_ctrl_fact where rownum=1;


    if psCodigoConsultora is null then
        BEGIN
            SELECT mae.cod_clie , mae.oid_clie  --- clid.num_docu_iden
              into lsCodigoConsultora, lsOidClie
              FROM mae_clien_ident clid,
                   mae_clien mae
             WHERE mae.oid_clie = clid.clie_oid_clie
               ---AND clid.val_iden_docu_prin = 1          
               and clid.num_docu_iden = psNumeroDocumento 
            and rownum = 1;
            
        EXCEPTION
          WHEN no_data_found THEN
               lsOidClie := 0;
        end;
    else
      
     lsCodigoConsultora := psCodigoConsultora;
      BEGIN
        select mae.oid_clie
          into lsOidClie
          from mae_clien mae
          where mae.cod_clie = psCodigoConsultora
          and rownum = 1;
      EXCEPTION
        WHEN no_data_found THEN
             lsOidClie := 0;
      end;
        
    end if;


  select TOBJ_RET_DATOS_CONSU
    (
        a.PAIS_COD_PAIS  ,
        a.COD_CLIE       ,
        a.NUM_DOCU_IDEN  ,
        a.APE_PATE_CLIE  ,
        a.APE_MATE_CLIE  ,
        a.NOM_CLIE       ,
        a.VAL_TIPO       ,
        a.VAL_DIRE       ,
        a.VAL_BARR       ,
        a.UBI_GEOG       ,
        a.COD_POST       ,
        a.COD_SEXO       ,
        a.FEC_NACI       ,
        a.TIP_CUTI       ,
        a.EST_CIVI       ,
        a.TEL_FIJO       ,
        a.TIP_CLIE       ,
        a.EST_CLIE       ,
        a.POR_DESC       ,
        a.COD_REGI       ,
        a.COD_ZONA       ,
        a.COD_SECC       ,
        a.COD_TERR       ,
        a.NIV_SOCI_ECON  ,
        a.CAM_ULTI_PEDI  ,
        a.PRO_FIDE       ,
        a.VAL_PUNT       ,
        a.COR_ELEC       ,
        a.FEC_FACT       ,
        a.COD_ESTA_CLIE  ,
        a.IND_TOP        ,
        a.TOT_VTA_DIR    ,
        a.TOT_VTA_RET    ,
        a.FEC_PROC       
      )
    bulk collect
    into ltDatosConsultora
    from (
      -- Devuelve Información de datos de consultora
     select 
          PAIS_COD_PAIS  ,
          COD_CLIE       ,
          NUM_DOCU_IDEN  ,
          APE_PATE_CLIE  ,
          APE_MATE_CLIE  ,
          NOM_CLIE       ,
          VAL_TIPO       ,
          VAL_DIRE       ,
          VAL_BARR       ,
          UBI_GEOG       ,
          COD_POST       ,
          COD_SEXO       ,
          FEC_NACI       ,
          TIP_CUTI       ,
          EST_CIVI       ,
          TEL_FIJO       ,
          TIP_CLIE       ,
          EST_CLIE       ,
          POR_DESC       ,
          COD_REGI       ,
          COD_ZONA       ,
          COD_SECC       ,
          COD_TERR       ,
          NIV_SOCI_ECON  ,
          CAM_ULTI_PEDI  ,
          PRO_FIDE       ,
          VAL_PUNT       ,
          COR_ELEC       ,
          FEC_FACT       ,
          COD_ESTA_CLIE  ,
          IND_TOP        ,
          TOT_VTA_DIR    ,
          TOT_VTA_RET    ,
          sysdate as fec_proc
      from int_ret_clien_noved
     where ( PAIS_COD_PAIS = lsCodigoPais and cod_clie = lsCodigoConsultora )            
    )a;             
    
    return ltDatosConsultora;

EXCEPTION
  WHEN OTHERS THEN
  ln_sqlcode := SQLCODE;
  ls_sqlerrm := substr(sqlerrm,1,250);
  ltDatosConsultora.DELETE;
  RAISE_APPLICATION_ERROR(-20123, 'ERROR RET_FN_OBTIE_DATOS_CONSU: '||ls_sqlerrm);
  return ltDatosConsultora;

END RET_FN_OBTIE_DATOS_CONSU;

/**************************************************************************
Descripcion        : Obtiene Datos de Consultora
Fecha Creacion     : 22/12/2015
Parametros Entrada :
			 psCodigoConsultora : codigo Consultora
       psNumeroDocumento  : numero de Documento
			 psPrimerApellido	  : primer Apellido
			 psSegundoApellido	: segundo Apellido
			 psNombres			    : nombres

Autor              : Richar Cruzado
***************************************************************************/
PROCEDURE RET_PR_OBTIE_DATOS_CONSU(psCodigoConsultora       VARCHAR2,
                                  psNumeroDocumento    	    VARCHAR2,
								                  psPrimerApellido			    VARCHAR2,
								                  psSegundoApellido		      VARCHAR2,
								                  psNombres				          VARCHAR2                                  
                                  )
IS 


  -- Variables locales
  lsCodigoPais  bas_ctrl_fact.cod_pais%type;  
  lsFechaFacturacion      VARCHAR2(50);
 
  lsCodigoConsultora          VARCHAR2(50);
  lsNumeroDocumento         VARCHAR2(50);
  
BEGIN
   
  lsCodigoConsultora := psCodigoConsultora;
  lsNumeroDocumento := psNumeroDocumento;
  -- Se obtiene codigoPais
  select cod_pais into lsCodigoPais from bas_ctrl_fact where rownum=1;  
  -- Fecha de facturacion
  lsFechaFacturacion := TO_CHAR(SYSDATE, 'DD/MM/YYYY');    

  -- RC: lamda al SP INT_PR_RET_ENVIO_CLIEN
  INT_PKG_RETAI.INT_PR_RET_ENVIO_CLIEN(lsCodigoPais, 'RET', 'RET-16', '', 'T', 'VD', '', lsFechaFacturacion, 'RETONLINE', 'W', lsCodigoConsultora, lsNumeroDocumento);
        

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);     
     RAISE_APPLICATION_ERROR(-20123, 'ERROR RET_PR_OBTIE_DATOS_CONSU: '||ls_sqlerrm);


END RET_PR_OBTIE_DATOS_CONSU;

/**************************************************************************
Descripcion        : Obtiene Datos Información de Precios de un Producto
Fecha Creacion     : 22/12/2015
Parametros Entrada :
			 psCodigoPeriodo	: código Período
             psCodigoSap  		: código SAP

Autor              : Richar Cruzado
***************************************************************************/
FUNCTION RET_FN_OBTIE_PRECI_PRODU(psCodigoPeriodo       VARCHAR2,
                                  psCodigoSap    	      VARCHAR2)
RETURN TTAB_RET_PRECI_PRODU
IS
  ltDatosProducto  TTAB_RET_PRECI_PRODU ;
BEGIN

  select TOBJ_RET_PRECI_PRODU
    ( a.estrategia,
      a.oferta,
      a.codi_vent,
      a.cod_sap,
	    a.campania,
	    a.desc_prod,
	    a.imp_prec_cata,
      a.cod_cata,
	    a.des_cata,
	    a.num_pagi_cata,
	    a.Cuadre,
      a.val_tasa_impu,
      a.val_desc_espe,
      a.val_libr_en1,
      a.val_libr_en2,
      a.val_libr_en3
    )
    bulk collect
    into ltDatosProducto
    from (
		-- Consulta Información de Precios de un Producto
		SELECT
			 i18est.val_i18n estrategia,
			 tofe.cod_tipo_ofer AS oferta,
			 ofedet.val_codi_vent as codi_vent,
			 prod.cod_sap,
			 spc.cod_peri  AS campania,
			 NVL(IMP_PKG_PROCE_LASER.imp_fn_desc_produ_br((select distinct cod_pais from bas_ctrl_fact),prod.oid_prod),'-') AS desc_prod,
			 ROUND(ofedet.imp_prec_cata / ofedet.VAL_FACT_REPE, 2) as imp_prec_cata,
       cat.cod_cata,
			 cat.DES_CATA,
			 ofedet.num_pagi_cata ,
			 decode(ofe.coes_oid_estr,2001,'','Cuadre') Cuadre,
       (select val_tasa_impu 
        from PED_IMPUE_GENER pig, PED_TASA_IMPUE pti
        where pti.OID_TASA_IMPU = pig.TAIM_OID_TASA_IMPU
        and pig.PAIS_OID_PAIS = peri.PAIS_OID_PAIS
        and pig.SBAC_OID_SBAC = 888) as   val_tasa_impu,                          
        GEN_PKG_GENER.GEN_FN_DEVUE_DSCTO_GENER(peri.OID_PERI,
                  ofedet.VAL_CODI_VENT)as val_desc_espe,
       '' val_libr_en1,
       '' val_libr_en2,
       '' val_libr_en3
		FROM cra_perio peri,
			 seg_perio_corpo spc,
			 pre_ofert ofe,
			 pre_ofert_detal ofedet,
			 pre_matri_factu mf,
			 pre_matri_factu_cabec mfc,
			 mae_produ prod,
			 pre_estra es,
			 pre_catal cat,
			 seg_marca marca,
			 pre_tipo_ofert tofe,
			 seg_acces acc,
			 (SELECT v.val_oid, v.val_i18n
				FROM v_gen_i18n_sicc v
			   WHERE v.attr_enti = 'MAE_PRODU' AND v.idio_oid_idio = 1) i18prod,
			 (SELECT v.val_oid, v.val_i18n
				FROM v_gen_i18n_sicc v
			   WHERE v.attr_enti = 'PRE_ESTRA' AND v.idio_oid_idio = 1) i18est,
			 (SELECT v.val_oid, v.val_i18n
				FROM v_gen_i18n_sicc v
			   WHERE v.attr_enti = 'SEG_PAIS' AND v.idio_oid_idio = 1) i18pais
	    WHERE peri.oid_peri = mfc.perd_oid_peri
			 and peri.peri_oid_peri  = spc.oid_peri
			 AND ofe.mfca_oid_cabe = mfc.oid_cabe
			 AND mf.mfca_oid_cabe = mfc.oid_cabe
			 AND ofe.oid_ofer = ofedet.ofer_oid_ofer
			 AND ofedet.oid_deta_ofer = mf.ofde_oid_deta_ofer
			 AND ofedet.prod_oid_prod = prod.oid_prod
			 AND ofedet.ocat_oid_catal = cat.oid_cata(+)
			 AND ofe.coes_oid_estr = es.oid_estr(+)
			 AND ofedet.tofe_oid_tipo_ofer = tofe.oid_tipo_ofer(+)
			 AND ofe.acce_oid_acce = acc.oid_acce(+)
			 AND i18est.val_oid(+) = ofe.coes_oid_estr
			 AND i18pais.val_oid(+) = peri.pais_oid_pais
			 AND i18prod.val_oid(+) = ofedet.prod_oid_prod
			 AND peri.marc_oid_marc = marca.oid_marc(+)
			 AND peri.pais_oid_pais = (select oid_pais from seg_pais where cod_pais = (select distinct cod_pais from bas_ctrl_fact))
			 and spc.cod_peri = psCodigoPeriodo
			 and prod.cod_sap = psCodigoSap
     )a;

    return ltDatosProducto;

EXCEPTION
  WHEN OTHERS THEN
  ln_sqlcode := SQLCODE;
  ls_sqlerrm := substr(sqlerrm,1,250);
  ltDatosProducto.DELETE;
  RAISE_APPLICATION_ERROR(-20123, 'ERROR RET_FN_OBTIE_PRECI_PRODU: '||ls_sqlerrm);

  return ltDatosProducto;
END RET_FN_OBTIE_PRECI_PRODU;

/**************************************************************************
Descripcion        : Obtiene Informacion de Consulta de Factura
Fecha Creacion     : 28/12/2015
Parametros Entrada :
			             psNumeroFactura	: numero de Factura
                   psNumeroPedido  	: numero de Pedido
                   psCodigoPeriodo  : codigo de Campaña

Autor              : Richar Cruzado
***************************************************************************/
FUNCTION RET_FN_OBTIE_CONSU_FACTU(psNumeroFactura       VARCHAR2,
                                  psNumeroPedido    	  VARCHAR2,
                                  psCodigoPeriodo      VARCHAR2
                                  )
RETURN TTAB_RET_CONSU_FACTU
IS
  ltDatosFactura  TTAB_RET_CONSU_FACTU ;

  lsCodigoPais  bas_ctrl_fact.cod_pais%type;
  lsindretfe              VARCHAR2(2);
  lsindretfe2             VARCHAR2(2);
  lsNumeroPedido          VARCHAR2(50);
  lsNumeroFactura         VARCHAR2(50);

BEGIN

  -- Se obtiene codigoPais
  select cod_pais into lsCodigoPais from bas_ctrl_fact where rownum=1;
  -- Se obtiene los codigos de parametros
  lsindretfe2 := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(lsCodigoPais,'IND_RET_FE2'),'N');
  lsindretfe := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(lsCodigoPais,'IND_RET_FE'),'N');

  lsNumeroPedido := psNumeroPedido;
  lsNumeroFactura := psNumeroFactura;

  -- Valida si numero pedido no es vacio
  IF (lsNumeroPedido IS NOT NULL) THEN
     lsNumeroFactura:=null;
  END IF;


  select TOBJ_RET_CONSU_FACTU
    (
	    a.cod_pais,
		  a.num_docu_cont_inte,
		  a.cod_clie,
		  a.fec_fact,
	    a.cod_sap,
	    a.val_codi_vent,
	    a.cod_marc_prod,
	    a.num_unid_aten,
	    a.val_tota,
	    a.ind_tipo_docu,
	    a.cod_ubig,
	    a.cod_peri,
	    a.num_unid_recla,
	    a.num_unid_otros,
	    a.nropedido,
	    a.monto_pedido,
	    a.monto_devuelto,
	    a.porcentaje_devol
    )
    bulk collect
    into ltDatosFactura
    from (
      -- Devuelve Información de Consulta de Factura
      select sp.cod_pais,
             decode(lsindretfe,'S',cab.val_seri_docu_lega || '-','') || cab.num_docu_cont_inte as num_docu_cont_inte,
             decode(lsindretfe2, 'S', (select num_docu_iden from mae_clien_ident x where x.VAL_IDEN_DOCU_PRIN=1 and x.clie_oid_clie=mc.oid_clie and rownum=1), mc.cod_clie) cod_clie,
             to_char(cab.fec_fact, 'yyyymmdd') fec_fact,
             mp.cod_sap,
             psp.val_codi_vent,
             decode(lsindretfe,'S',mcdi.ind_impr_docu,smp.cod_marc_prod) cod_marc_prod,
             det.num_unid_aten,
             decode(nvl(det.val_prec_cata_unit_loca,0), 0,0,det.val_prec_fact_unit_loca * det.num_unid_aten*100) as val_tota,
             decode(tdl.ind_tipo_docu, 'B', 0, 1) ind_tipo_docu,
             decode(lsindretfe,'S',con.val_nume_soli,smp.cod_marc_prod) as cod_ubig,
             spc.cod_peri,
       (  SELECT SUM(nvl(lin.num_unid_recl,0))
          FROM rec_linea_opera_recla       lin
         WHERE lin.timo_oid_tipo_movi = 2
           AND lin.sopo_oid_soli_posi = psp.oid_soli_posi) num_unid_recla ,
        (  SELECT nvl(SUM(nvl(det2.can_vent_devu,0)),0)
                  FROM int_solic_conso_poven_detal det2,
                       sto_docum_digit             dig
                 WHERE det2.cod_pais = lsCodigoPais
                   AND det2.cod_clie = mc.cod_clie
                   AND det2.oid_soli_posi_devu = psp.oid_soli_posi
                   AND dig.sec_nume_docu = det2.sec_nume_docu
                   AND dig.ind_envi = 0
                   AND dig.ind_rech = 0       ) num_unid_otros,
             con.val_nume_soli as nropedido,
             con.val_tota_paga_loca as monto_pedido,
      ( select nvl(sum(f.VAL_TOTA_PAGA_LOCA),0) MontoDevolucion
         from ped_solic_cabec a,
              rec_cabec_recla b,
              rec_opera_recla c,
              rec_solic_opera d,
              ped_solic_cabec e,
              ped_solic_cabec f,
              ped_tipo_solic_pais tsp,
              ped_tipo_solic ts
        where a.VAL_NUME_SOLI = con.val_nume_soli
        and a.OID_SOLI_CABE = b.SOCA_OID_SOLI_CABE
        and b.oid_cabe_recl = c.CARE_OID_CABE_RECL
        and c.OID_OPER_RECL  = d.opre_oid_oper_recl
        and tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
        and oid_tipo_soli_pais = d.TSPA_OID_TIPO_SOLI_PAIS
        and cod_tipo_soli in ('SDDN')
        and d.SOCA_OID_SOLI_CABE = e.OID_SOLI_CABE
        and e.SOCA_OID_SOLI_CABE = f.OID_SOLI_CABE  ) monto_devuelto,
        ( REC_PKG_PROCE.REC_FN_PORCE_MONTO_DEVOL(mc.oid_clie, lsCodigoPais, psCodigoPeriodo) ) porcentaje_devol
        from fac_docum_conta_cabec cab,
             fac_docum_conta_linea det,
             fac_tipo_docum        ftd,
             mae_produ             mp,
             ped_solic_cabec       con,
             ped_solic_posic       psp,
             mae_clien             mc,
             mae_clien_datos_adici mcdi,
             seg_pais              sp,
             seg_marca_produ       smp,
             fac_tipo_docum_legal  tdl,
             cra_perio             cp,
             seg_perio_corpo       spc,
             zon_valor_estru_geopo veg
       where cab.soca_oid_soli_cabe = con.oid_soli_cabe
         and con.clie_oid_clie = mc.oid_clie
         and mcdi.clie_oid_clie = mc.oid_clie
         and con.pais_oid_pais = sp.oid_pais
         and cab.oid_cabe = det.dcca_oid_cabe
         and det.prod_oid_prod = mp.oid_prod
         and mp.mapr_oid_marc_prod = smp.oid_marc_prod
         and det.sopo_oid_soli_posi = psp.oid_soli_posi
         and con.perd_oid_peri = cp.oid_peri
         and cp.peri_oid_peri = spc.oid_peri
         and det.num_unid_aten > 0
         and psp.val_codi_vent is not null -- no se consideran los premios
         and ( (psNumeroPedido is not null and con.val_nume_soli = psNumeroPedido) -- si pedido no es nulo
				        or ( psNumeroPedido is null -- si nro. pedido es nulo
				        and decode(lsindretfe,'S',cab.val_seri_docu_lega,'B065') = 'B065' -- consulta por factura
				        and cab.num_docu_cont_inte = SUBSTR(psNumeroFactura, 6)) )
         and exists ( select null
                      from int_lar_tipo_solici_pedido_dis l
                      where l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais)
         and cab.tido_oid_tipo_docu = ftd.oid_tipo_docu
         and ftd.cod_tipo_docu = tdl.cod_tipo_dole
         and tdl.ind_acti_docu = 1
         and con.vepo_oid_valo_estr_geop = veg.oid_valo_estr_geop
    )a;             
    
    return ltDatosFactura;

EXCEPTION
  WHEN OTHERS THEN
  ln_sqlcode := SQLCODE;
  ls_sqlerrm := substr(sqlerrm,1,250);
  ltDatosFactura.DELETE;
  RAISE_APPLICATION_ERROR(-20123, 'ERROR RET_FN_OBTIE_CONSU_FACTU: '||ls_sqlerrm);
  return ltDatosFactura;

END RET_FN_OBTIE_CONSU_FACTU;

/**************************************************************************
Descripción        : Inserta Venta de Devolución
Fecha Creación     : 28/12/2015
Parametros Entrada :
          psCodPais                : código País
          psSociedad 				       : sociedad
          psCanal					         : canal
          psAcceso				         : acceso
          psSubAcceso				       : subAcceso
          psLocal					         : local
          psTipoDocumentoLegal	   : tipo documento legal
          psSerieDocumentoLegal	   : serie documento legal
          psNroDocumentoLegal		   : número documento legal
          psCajaEmisora			       : caja emisora
          psNroDocInterno			     : número documento interno
          psTipoCliente			       : tipo cliente
          psNombreCliente			     : nombre cliente
          psCodigoConsultCliente	 : código consultor cliente
          psFecEmisionComproban	   : fecha emisión comprobante
          psTipoImpuesto			     : tipo impuesto
          psTasaImpuesto			     : tasa impuesto
          psCampana				         : campania
          psTipoDocReferencial	   : tipo documento referencial
          psSerieDocReferencial	   : serie documento referencial
          psNroDocReferencial		   : número documento referencial
          psFechaDocReferencia	   : fecha documento referencial
          psAnulado				         : anulado
          psRUC					           : ruc
          psTipoDocIdentClient	   : tipo documento identificación cliente
          psNroDocIdentClient		   : número documento identificación cliente
          psInteresMora			       : interés mora
          psBaseImponible			     : base imponible
          psDescuento				       : descuento
          psComisiones			       : comisiones
          psFlete					         : flete
          psBaseImponibleNeto		   : base imponible neto
          psImpuesto				       : impuesto
          psImporteTotal			     : importe total
          psFacturaGratuita		     : factura gratuita
          psSerieComprobantPercep	 : serie comprobante percepción
          psNroComprobantPercep	   : número comprobante percepción
          psSecuenComprobantPercep : secuencia comprobante percepción
          psMontoTotalDocumentLegl : monto total documento legal
          psMontoPagoAplicado		   : monto pago aplicado
          psMontoOperacion		     : monto operación
          psPorentajePercepcion	   : porcentaje percepción
          psFlagProceso			       : flag proceso
          psFlagCancelacion		     : flag cancelación
          psHoraProceso			       : hora proceso
          psFechaProceso			     : fecha proceso
          psFlagCierreRVC			     : flag cierre RVC
          psFacturaAnulada		     : factura anulada
          psLote					         : lote
Autor              : Richar Cruzado
***************************************************************************/
PROCEDURE RET_PR_INSER_CONSU_VENTA_DEVOL(psCodPais       		 	   VARCHAR2,
                                       psSociedad    		 	       VARCHAR2,
                                       psCanal    			 	       VARCHAR2,
                                       psAcceso    			 	       VARCHAR2,
                                       psSubAcceso    		 	     VARCHAR2,
                                       psLocal    			 	       VARCHAR2,
                                       psTipoDocumentoLegal   	 VARCHAR2,
                                       psSerieDocumentoLegal  	 VARCHAR2,
                                       psNroDocumentoLegal       VARCHAR2,
                                       psCajaEmisora    	     	 VARCHAR2,
                                       psNroDocInterno    	 	   VARCHAR2,
                                       psTipoCliente    		 	   VARCHAR2,
                                       psNombreCliente    	 	   VARCHAR2,
                                       psCodigoConsultCliente 	 VARCHAR2,
                                       psFecEmisionComproban  	 VARCHAR2,
                                       psTipoImpuesto    	 	     VARCHAR2,
                                       psTasaImpuesto    	 	     VARCHAR2,
                                       psCampana    			 	     VARCHAR2,
                                       psTipoDocReferencial   	 VARCHAR2,
                                       psSerieDocReferencial  	 VARCHAR2,
                                       psNroDocReferencial    	 VARCHAR2,
                                       psFechaDocReferencia   	 VARCHAR2,
                                       psAnulado   			 	       VARCHAR2,
                                       psRUC   				 	         VARCHAR2,
                                       psTipoDocIdentClient   	 VARCHAR2,
                                       psNroDocIdentClient   	   VARCHAR2,
                                       psInteresMora   			     VARCHAR2,
                                       psBaseImponible   		     VARCHAR2,
                                       psDescuento   			       VARCHAR2,
                                       psComisiones   			     VARCHAR2,
                                       psFlete   				         VARCHAR2,
                                       psBaseImponibleNeto   	   VARCHAR2,
                                       psImpuesto   				     VARCHAR2,
                                       psImporteTotal   			   VARCHAR2,
                                       psFacturaGratuita   		   VARCHAR2,
                                       psSerieComprobantPercep   VARCHAR2,
                                       psNroComprobantPercep     VARCHAR2,
                                       psSecuenComprobantPercep  VARCHAR2,
                                       psMontoTotalDocumentLegl  VARCHAR2,
                                       psMontoPagoAplicado   	   VARCHAR2,
                                       psMontoOperacion   		   VARCHAR2,
                                       psPorentajePercepcion   	 VARCHAR2,
                                       psFlagProceso   			     VARCHAR2,
                                       psFlagCancelacion   		   VARCHAR2,
                                       psHoraProceso   			     VARCHAR2,
                                       psFechaProceso   			   VARCHAR2,
                                       psFlagCierreRVC   		     VARCHAR2,
                                       psFacturaAnulada   		   VARCHAR2,
                                       psLote   					       VARCHAR2,
                                       psStatus              OUT VARCHAR2,
                                       psMensaje             OUT VARCHAR2)

IS

BEGIN

  psStatus:='OK';
	psMensaje:='';

EXCEPTION
  WHEN OTHERS THEN
  ln_sqlcode := SQLCODE;
  ls_sqlerrm := substr(sqlerrm,1,250);
  RAISE_APPLICATION_ERROR(-20123, 'ERROR RET_PR_INSER_CONSU_VENTA_DEVOL: '||ls_sqlerrm);

END RET_PR_INSER_CONSU_VENTA_DEVOL;

/**************************************************************************
Descripcion        : Obtiene Informacion Consulta de Catalogos
Fecha Creacion     : 21/01/2016
Autor              : Richar Cruzado
Parametros Entrada :
***************************************************************************/
FUNCTION RET_FN_OBTIE_CONSU_CATAG

RETURN  TTAB_RET_CONSU_CATAG
IS
  ltDetalleCatalogos TTAB_RET_CONSU_CATAG ;
BEGIN

    select TOBJ_RET_CONSU_CATAG
    ( a.cod_cata,
      a.des_cata
    )
    bulk collect
    into ltDetalleCatalogos
    from (
		-- Consulta Informacion de Catalogos
		select COD_CATA, DES_CATA 
		from PRE_CATAL
		order by COD_CATA
     )a;

    return ltDetalleCatalogos;

	EXCEPTION
	WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     ltDetalleCatalogos.DELETE;
     RAISE_APPLICATION_ERROR(-20123, 'ERROR RET_FN_OBTIE_CONSU_CATAG: '||ls_sqlerrm);

	return ltDetalleCatalogos;

END RET_FN_OBTIE_CONSU_CATAG;

END RET_PKG_PROCE;
/
