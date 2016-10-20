CREATE OR REPLACE PACKAGE INT_PKG_SAPMM AS
/* Declaracion de variables */
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(1500);
  w_filas    NUMBER := 1000;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de movimientos de almacen
  Fecha Creacion    : 08/09/2009
  Fecha Modificacion : 25/09/2009
  Autor             : Cristhian Roman
  Modificado por     : Sergio Buchelli
  Parametros:
          psCodigoPais   :  Codigo de Pais
          pscodigosistema : Codigo Sistena
          pscodigointerfaz : Codigo Interfaz
          psnombrearchivo     : Nombre Archivo
          pscodigoperiodo  : Codigo de Periodo
          psnumerolote  :     Numero Lote
          psfechaFacturacion : Fecha Facturacion
          psindicadorenvioultimolote   : Indicador envio ultimo lote
          psindicadorenviolar   : Indicador envio Lar
  ***************************************************************************/
  PROCEDURE int_pr_sap_envi_movim_almac
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
	pscodigoperiodo	 VARCHAR2,
    psfechaFacturacion         VARCHAR2,
	psnumerolote	 VARCHAR2,
    psindicadorenvioultimolote VARCHAR2,
    psindicadorenviolar        VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de movimientos de almacen
  con el formato de SiCC
  Fecha Creacion    : 23/11/2010
  Fecha Modificacion : 23/11/2010
  Autor             : Jorge Yepez
  Modificado por     : Jorge Yepez
  Parametros:
          psCodigoPais   :  Codigo de Pais
          pscodigosistema : Codigo Sistena
          pscodigointerfaz : Codigo Interfaz
          psnombrearchivo     : Nombre Archivo
          pscodigoperiodo  : Codigo de Periodo
          psnumerolote  :     Numero Lote
          psfechaFacturacion : Fecha Facturacion
          psindicadorenvioultimolote   : Indicador envio ultimo lote
          psindicadorenviolar   : Indicador envio Lar
  ***************************************************************************/
  PROCEDURE int_pr_sap_env_mov_almac_sicc
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
	pscodigoperiodo	 VARCHAR2,
    psfechaFacturacion         VARCHAR2,
	psnumerolote	 VARCHAR2,
    psindicadorenvioultimolote VARCHAR2,
    psindicadorenviolar        VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de movimientos de almacen
  con el formato de SiCC
  Fecha Creacion    : 23/11/2010
  Fecha Modificacion : 23/11/2010
  Autor             : Jorge Yepez
  Modificado por     : Jorge Yepez
  Parametros:
          psCodigoPais   :  Codigo de Pais
          pscodigosistema : Codigo Sistena
          pscodigointerfaz : Codigo Interfaz
          psnombrearchivo     : Nombre Archivo
          pscodigoperiodo  : Codigo de Periodo
          psnumerolote  :     Numero Lote
          psfechaFacturacion : Fecha Facturacion
          psindicadorenvioultimolote   : Indicador envio ultimo lote
          psindicadorenviolar   : Indicador envio Lar
  ***************************************************************************/
  PROCEDURE int_pr_sap_env_mov_almac_col
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
	pscodigoperiodo	 VARCHAR2,
    psfechaFacturacion         VARCHAR2,
	psnumerolote	 VARCHAR2,
    psindicadorenvioultimolote VARCHAR2,
    psindicadorenviolar        VARCHAR2
  );

/***************************************************************************
 Descripcion       : Genera Interfaz de Recepcion de productos nacionales
                     e importados
 Fecha Creacion    : 09/11/2010
 Autor             : Dennys Oliva Iriarte
 ***************************************************************************/
  PROCEDURE INT_PR_SAP_RECEP_PRODU_NAIMP(pscodigopais     VARCHAR2,
                    										 pscodigosistema  VARCHAR2,
                    										 pscodigointerfaz VARCHAR2,
                    										 psnombrearchivo  VARCHAR2);

/**************************************************************************
Descripcion        : recupera el ultimo Numero Movimiento para un determinado
                     Pais y Operacion
Fecha Creacion     :  07/11/2011
Parametros Entrada :
           psCodigoPais : codigo Pais
           psCodigoOperacion : codigo Operacion
Autor              : Sergio Apaza
***************************************************************************/
PROCEDURE INT_PR_OBTIE_NUMER_MOVIM(psCodigoPais       VARCHAR2,
                                   psCodigoOperacion  VARCHAR2,
                                   pnNumeroMovimiento OUT NUMBER);
RETURN NUMBER;

/***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Reserva PROL
  Fecha Creacion    : 28/12/2011
  Autor             : Jose Luis Rodriguez
  Parametros:
          psCodigoPais                 : Codigo de Pais
          pscodigosistema              : Codigo Sistena
          pscodigointerfaz             : Codigo Interfaz
          psnombrearchivo              : Nombre Archivo
          pscodigoperiodo              : Codigo de Periodo
          psnumerolote                 : Numero Lote
          psfechaFacturacion           : Fecha Facturacion
          psindicadorenvioultimolote   : Indicador envio ultimo lote
          psindicadorenviolar          : Indicador envio Lar
  ***************************************************************************/
  PROCEDURE int_pr_sap_envi_reser_prol (
    pscodigopais                VARCHAR2,
    pscodigosistema             VARCHAR2,
    pscodigointerfaz            VARCHAR2,
    psnombrearchivo             VARCHAR2,
	  pscodigoperiodo	            VARCHAR2,
    psfechaFacturacion          VARCHAR2,
  	psnumerolote	              VARCHAR2,
    psindicadorenvioultimolote  VARCHAR2,
    psindicadorenviolar         VARCHAR2
  );


/***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Cantidad Productos
  Fecha Creacion    : 05/06/2012
  Autor             : Sergio Buchelli
  Parametros:
          psCodigoPais                 : Codigo de Pais
          pscodigosistema              : Codigo Sistena
          pscodigointerfaz             : Codigo Interfaz
          psnombrearchivo              : Nombre Archivo
          pscodigoperiodo              : Codigo de Periodo
          psnumerolote                 : Numero Lote
          psfechaFacturacion           : Fecha Facturacion
  ***************************************************************************/
  PROCEDURE int_pr_sap_envio_canti_produ (
    pscodigopais                VARCHAR2,
    pscodigosistema             VARCHAR2,
    pscodigointerfaz            VARCHAR2,
    psnombrearchivo             VARCHAR2,
	pscodigoperiodo	            VARCHAR2,
    psfechaFacturacion          VARCHAR2,
  	psnumerolote	            VARCHAR2
  );

/***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Archivo de Lote Producto
  Fecha Creacion    : 11/06/2012
  Autor             : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE INT_PR_SAP_RECEP_LOTE_PRODU (pscodigopais     VARCHAR2,
                                         pscodigosistema  VARCHAR2,
                                         pscodigointerfaz VARCHAR2,
                                         psnombrearchivo  VARCHAR2,
                                         psusuario        VARCHAR2,
                                         psCantidadRegistros OUT VARCHAR2);

/***************************************************************************
  Descripcion       : Proceso de Recepcion de Negocio
  Fecha Creacion    : 26/03/2013
  Autor             : Danny Amaro
  ***************************************************************************/
  PROCEDURE INT_PR_SAP_RECEP_NEGO (pscodigopais     VARCHAR2,
                                   pscodigonegocio  VARCHAR2,
                                   pscodigoidioma   VARCHAR2,
                                   psdescripcion    VARCHAR2);

/***************************************************************************
  Descripcion       : Proceso de Recepcion Super Genérico
  Fecha Creacion    : 26/03/2013
  Autor             : Danny Amaro
  ***************************************************************************/
  PROCEDURE INT_PR_SAP_RECEP_SUPER_GENER(pscodigopais           VARCHAR2,
                                         pscodigosupergenerico  VARCHAR2,
                                         pscodigoidioma         VARCHAR2,
                                         psdescripcion          VARCHAR2);

/***************************************************************************
  Descripcion       : Proceso de Recepcion Genérico
  Fecha Creacion    : 26/03/2013
  Autor             : Danny Amaro
  ***************************************************************************/
  PROCEDURE INT_PR_SAP_RECEP_GENER(pscodigopais           VARCHAR2,
                                   pscodigogenerico       VARCHAR2,
                                   pscodigoidioma         VARCHAR2,
                                   psdescripcion          VARCHAR2);

/***************************************************************************
  Descripcion       : Proceso de Recepcion Descripcion de Producto por Idioma
  Fecha Creacion    : 26/03/2013
  Autor             : Danny Amaro
  ***************************************************************************/
  PROCEDURE INT_PR_SAP_RECEP_DESC_PRODU(pscodigopais           VARCHAR2,
                                        pscodigoproducto       VARCHAR2,
                                        pscodigoidioma         VARCHAR2,
                                        psdescripcion          VARCHAR2);

/***************************************************************************
  Descripcion       : Proceso de Recepcion de Producto
  Fecha Creacion    : 27/03/2013
  Autor             : Danny Amaro
  ***************************************************************************/
  PROCEDURE INT_PR_SAP_RECEP_PRODU(pscodigopais           VARCHAR2,
                                   pscodigoproducto       VARCHAR2,
                                   psdescripcion          VARCHAR2,
                                   psindicadorProServ     VARCHAR2,
                                   pscodigomarca          VARCHAR2,
                                   pscodigounidadnegocio  VARCHAR2,
                                   pscodigonegocio        VARCHAR2,
                                   psgrupoarticulo        VARCHAR2,
                                   pscodigogenerico       VARCHAR2,
                                   pscodigosupergenerico  VARCHAR2,
                                   psjerarquia1           VARCHAR2,
                                   psjerarquia2           VARCHAR2,
                                   psjerarquia3           VARCHAR2,
                                   psalto                 VARCHAR2,
                                   pslargo                VARCHAR2,
                                   psancho                VARCHAR2,
                                   psindicadorFueraCaja   VARCHAR2,
                                   psvolumen              VARCHAR2,
                                   pspesobruto            VARCHAR2,
                                   pscodigoestatus        VARCHAR2,
                                   pscosteestandar        VARCHAR2,
                                   pscodigounimedvolumen  VARCHAR2,
                                   pscodigounimedpeso     VARCHAR2,
                                   pscodigoantiguo        VARCHAR2,
                                   pscuv                  VARCHAR2);

  /**************************************************************************
  Descripcion        : Devuelve el Tipo de Movimiento para la SAM7
  Fecha Creacion     : 18/09/2013
  Autor              : Jorge Yepez
  ***************************************************************************/
  FUNCTION ped_fn_devue_tip_movi_sam
  (
    pnoidmodu NUMBER,
    pnindoc   NUMBER,
    pscodigpais VARCHAR2
  ) RETURN VARCHAR;

END INT_PKG_SAPMM;
/
CREATE OR REPLACE PACKAGE BODY INT_PKG_SAPMM AS


   /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de movimientos de almacen
  Fecha Creacion    : 09/09/2009
  Fecha Modificacion : 25/09/2009
  Autor             : Cristhian Roman
  Modificado por     : Sergio Buchelli
  Parametros:
          psCodigoPais   :  Codigo de Pais
          pscodigosistema : Codigo Sistena
          pscodigointerfaz : Codigo Interfaz
          psnombrearchivo     : Nombre Archivo
          pscodigoperiodo  : Codigo de Periodo
          psnumerolote  :     Numero Lote
          psfechaFacturacion : Fecha Facturacion
          psindicadorenvioultimolote   : Indicador envio ultimo lote
          psindicadorenviolar   : Indicador envio Lar
  ***************************************************************************/
  PROCEDURE int_pr_sap_envi_movim_almac
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
	pscodigoperiodo	 VARCHAR2,
    psfechaFacturacion         VARCHAR2,
	psnumerolote	 VARCHAR2,
    psindicadorenvioultimolote VARCHAR2,
    psindicadorenviolar        VARCHAR2
  ) IS
    CURSOR c_interfaz(lsoidperiodo NUMBER, lsoidpais NUMBER,lnnumeroLoteFacturacion NUMBER) IS
     SELECT *
  FROM (SELECT   pais.cod_pais,
                 pto.cod_tipo_ofer,
                 to_char(cons.fec_fact, 'yyyymmdd') fec_proc,
                 pro.cod_sap codigosap,
                 SUM (pos.num_unid_aten) totalunidades,
                 decode(cab.ind_rece_onli, 1, 'P01', nvl(alm2.cod_alma,alm.cod_alma)) cod_alma
            FROM ped_solic_cabec cab,
                 ped_solic_cabec cons,
                 ped_solic_posic pos,
                 mae_produ pro,
                 ped_tipo_solic_pais tsp,
                 ped_tipo_solic ts,
                 int_lar_tipo_solici_pedido_dis tspd,
                 seg_pais pais,
                 pre_ofert_detal pod,
                 bel_almac alm,
                 bel_almac alm2,
                 pre_tipo_ofert pto
           WHERE pos.soca_oid_soli_cabe = cab.oid_soli_cabe
             AND cab.soca_oid_soli_cabe = cons.oid_soli_cabe
             AND pos.prod_oid_prod = pro.oid_prod
             AND cab.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
             AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
             AND cons.perd_oid_peri = lsoidperiodo
             AND cons.ind_inte_lari_gene = psindicadorenviolar
             AND cons.fec_fact = to_date(psfechaFacturacion, 'dd/mm/yyyy')
             AND cons.ind_ts_no_conso = 0
             AND (cons.ind_pedi_prue = 0 OR cons.ind_pedi_prue IS NULL)
             AND cons.tspa_oid_tipo_soli_pais = tspd.tspa_oid_tipo_soli_pais
             AND cons.pais_oid_pais = lsoidpais
             AND pos.espo_oid_esta_posi <> 2
             --and nvl(cab.ind_rece_onli,0)=0
             AND cab.pais_oid_pais=pais.oid_pais
             AND pos.ofde_oid_deta_ofer=pod.oid_deta_ofer(+)
             and cab.almc_oid_alma=alm.oid_alma
             and pos.almc_oid_almc=alm2.oid_alma(+)
             and pod.tofe_oid_tipo_ofer=pto.oid_tipo_ofer(+)
             and nvl(pro.ind_prod_serv,0)<>2
             and nvl(pro.val_atri_3,0)<>1
             AND (lnnumeroLoteFacturacion IS NULL OR cons.num_lote_fact = lnnumeroLoteFacturacion)
        GROUP BY pais.cod_pais,
                 pto.cod_tipo_ofer,
                 decode(cab.ind_rece_onli, 1, 'P01', nvl(alm2.cod_alma,alm.cod_alma)),
                 to_char(cons.fec_fact, 'yyyymmdd'),
                 pro.cod_sap)
     WHERE totalunidades > 0
    ORDER BY 4, 2;


    TYPE interfazrec IS RECORD(
	  pais                          seg_pais.COD_PAIS%type,
	  tipoOferta  				  	pre_tipo_ofert.cod_tipo_ofer%type,
	  fechaProceso					VARCHAR(8),
	  codigoProductoSAP             mae_produ.cod_sap%TYPE,
	  unidadesAtendidas      		ped_solic_posic.NUM_UNID_ATEN%TYPE,
	  almacen      					bel_almac.COD_ALMA%type
     );

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo   VARCHAR2(50);
    lbabrirutlfile    BOOLEAN;

    lscadena  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20);
    lsreplace VARCHAR2(100) := 'a        ';

	lsoidperiodo seg_perio_corpo.OID_PERI%type;
	lsoidpais	 seg_pais.OID_PAIS%type;
    lnnumeroLoteFacturacion NUMBER;

  BEGIN

	lsoidperiodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(pscodigoperiodo);
	lsoidpais	 := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(pscodigopais);
    lbabrirutlfile := TRUE;

    /* En caso el indicador de envio de ultimo lote de facturacion tenga el valor 1
       SE Obtendra el numero delote de facturacion
    */

    IF(psindicadorenvioultimolote='1')THEN

      BEGIN
          SELECT MAX (cons.num_lote_fact) INTO lnnumeroLoteFacturacion
          FROM ped_solic_cabec cons,
               int_lar_tipo_solici_pedido_dis tspd
         WHERE cons.perd_oid_peri = lsoidperiodo
           AND cons.fec_fact = to_date(psfechaFacturacion, 'dd/mm/yyyy')
           AND cons.ind_ts_no_conso = 0
           AND (cons.ind_pedi_prue = 0 OR cons.ind_pedi_prue IS NULL)
           AND cons.tspa_oid_tipo_soli_pais = tspd.tspa_oid_tipo_soli_pais
           AND cons.pais_oid_pais = lsoidpais;
      EXCEPTION
        when others then
            lnnumeroLoteFacturacion:=null;
      END;

    END IF;


    OPEN c_interfaz(lsoidperiodo,lsoidpais,lnnumeroLoteFacturacion);
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais, pscodigosistema, pscodigointerfaz,
                                               psnombrearchivo, lsdirtempo, lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;

      IF interfazrecord.COUNT > 0 THEN
        FOR x IN interfazrecord.FIRST .. interfazrecord.LAST LOOP
          lslinea := translate(psnumerolote, lscadena, lsreplace) 						 || ';' ||
		   		     translate(interfazrecord(x).pais, lscadena, lsreplace) 			 || ';' ||
					 translate(pscodigoperiodo, lscadena, lsreplace) 		   	   	 	 || ';' ||
					 translate(interfazrecord(x).fechaProceso, lscadena, lsreplace)  	 || ';' ||
					 interfazrecord(x).tipoOferta || ';' ||
                     translate(interfazrecord(x).codigoProductoSAP, lscadena, lsreplace) || ';' ||
                     translate(interfazrecord(x).unidadesAtendidas, lscadena, lsreplace) || ';' ||
                     translate(interfazrecord(x).almacen, lscadena, lsreplace);

          utl_file.put_line(v_handle, lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);

      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR', lsdirtempo, psnombrearchivo,
                                             lsnombrearchivo);
    END IF;
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123, 'INT_PR_SAP_ENVI_MOVIM_ALMAC: ' || ls_sqlerrm);
  END int_pr_sap_envi_movim_almac;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de movimientos de almacen
  con el formato de SiCC
  Fecha Creacion    : 23/11/2010
  Fecha Modificacion : 23/11/2010
  Autor             : Jorge Yepez
  Modificado por     : Jorge Yepez
  Parametros:
          psCodigoPais   :  Codigo de Pais
          pscodigosistema : Codigo Sistena
          pscodigointerfaz : Codigo Interfaz
          psnombrearchivo     : Nombre Archivo
          pscodigoperiodo  : Codigo de Periodo
          psnumerolote  :     Numero Lote
          psfechaFacturacion : Fecha Facturacion
          psindicadorenvioultimolote   : Indicador envio ultimo lote
          psindicadorenviolar   : Indicador envio Lar
  ***************************************************************************/
  PROCEDURE int_pr_sap_env_mov_almac_sicc
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
	pscodigoperiodo	 VARCHAR2,
    psfechaFacturacion         VARCHAR2,
	psnumerolote	 VARCHAR2,
    psindicadorenvioultimolote VARCHAR2,
    psindicadorenviolar        VARCHAR2
  ) IS
    CURSOR c_interfaz(lsoidperiodo NUMBER, lsoidpais NUMBER,lnnumeroLoteFacturacion NUMBER) IS
 /*    SELECT *
  FROM (SELECT   pais.cod_pais,
                 pto.cod_tipo_ofer,
                 to_char(cons.fec_fact, 'yyyymmdd') fec_proc,
                 pro.cod_sap codigosap,
                 SUM (pos.num_unid_aten) totalunidades,
                 alm.cod_alma
            FROM ped_solic_cabec cab,
                 ped_solic_cabec cons,
                 ped_solic_posic pos,
                 mae_produ pro,
                 ped_tipo_solic_pais tsp,
                 ped_tipo_solic ts,
                 int_lar_tipo_solici_pedido_dis tspd,
                 seg_pais pais,
                 pre_ofert_detal pod,
                 bel_almac alm,
                 pre_tipo_ofert pto
           WHERE pos.soca_oid_soli_cabe = cab.oid_soli_cabe
             AND cab.soca_oid_soli_cabe = cons.oid_soli_cabe
             AND pos.prod_oid_prod = pro.oid_prod
             AND cab.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
             AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
             AND cons.perd_oid_peri = lsoidperiodo
             AND cons.ind_inte_lari_gene = psindicadorenviolar
             AND cons.fec_fact = to_date(psfechaFacturacion, 'dd/mm/yyyy')
             AND cons.ind_ts_no_conso = 0
             AND (cons.ind_pedi_prue = 0 OR cons.ind_pedi_prue IS NULL)
             AND cons.tspa_oid_tipo_soli_pais = tspd.tspa_oid_tipo_soli_pais
             AND cons.pais_oid_pais = lsoidpais
             AND pos.espo_oid_esta_posi <> 2
             AND cab.pais_oid_pais=pais.oid_pais
             AND pos.ofde_oid_deta_ofer=pod.oid_deta_ofer(+)
             and cab.almc_oid_alma=alm.oid_alma
             and pod.tofe_oid_tipo_ofer=pto.oid_tipo_ofer(+)
             AND (lnnumeroLoteFacturacion IS NULL OR cons.num_lote_fact = lnnumeroLoteFacturacion)
        GROUP BY pais.cod_pais,
                 pto.cod_tipo_ofer,
                 alm.cod_alma,
                 to_char(cons.fec_fact, 'yyyymmdd'),
                 pro.cod_sap)
     WHERE totalunidades > 0
    ORDER BY 4, 2;*/
select * from (
     SELECT cod_pais,'VD','GZ','000','CM',tip_mov,'S',cod_tipo_ofer, COD_NEGO, MAPR_OID_MARC_PROD, fec_proc, codigosap, SUM (num_unid_aten) totalunidades,
                 round(SUM (num_unid_aten*VAL_PREC_NETO_UNIT_LOCA)/SUM (num_unid_aten),2)*100 totalneto,
                 round(SUM (num_unid_aten*VAL_PREC_CONT_UNIT_LOCA)/SUM (num_unid_aten),2)*100 totalcont,
                 cod_alma
  FROM (SELECT   pais.cod_pais,'VD','GZ','000','CM'
                 ,ped_fn_devue_tip_movi_sam(cab.modu_oid_modu, cab.ind_oc, pscodigopais) tip_mov
                 ,'S',
                 nvl(pto.cod_tipo_ofer,(select ito.COD_TIPO_OFER_CONC from inc_concu_param_gener ic, INC_TIPO_OFERT_CONCU ito where ic.tioc_oid_tipo_ofer_conc=ito.oid_tipo_ofer_conc and ic.oid_para_gral=cab.copa_oid_para_gene)) cod_tipo_ofer,
                 neg.COD_NEGO,
                 --to_char(to_number(smp.COD_MARC_PROD)) COD_MARC_PROD,
                 pro.MAPR_OID_MARC_PROD,
                 to_char(cons.fec_fact, 'yyyymmdd') fec_proc,
                 pro.cod_sap codigosap,
                 pos.num_unid_aten num_unid_aten,
                 pos.VAL_PREC_NETO_UNIT_LOCA VAL_PREC_NETO_UNIT_LOCA,
                 pos.VAL_PREC_CONT_UNIT_LOCA VAL_PREC_CONT_UNIT_LOCA,
                 nvl(alm2.cod_alma,alm.cod_alma) cod_alma
            FROM ped_solic_cabec cab,
                 ped_solic_cabec cons,
                 ped_solic_posic pos,
                 mae_produ pro,
                 ped_tipo_solic_pais tsp,
                 ped_tipo_solic ts,
                 int_lar_tipo_solici_pedido_dis tspd,
                 seg_pais pais,
                 pre_ofert_detal pod,
                 bel_almac alm,
                 bel_almac alm2,
                 pre_tipo_ofert pto,
                 mae_negoc neg--,
                 --seg_marca_produ smp
           WHERE pos.soca_oid_soli_cabe = cab.oid_soli_cabe
             AND cab.soca_oid_soli_cabe = cons.oid_soli_cabe
             AND pos.prod_oid_prod = pro.oid_prod
             AND cab.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
             AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
             AND cons.perd_oid_peri = lsoidperiodo
             AND cons.ind_inte_lari_gene = psindicadorenviolar
             AND cons.fec_fact = to_date(psfechaFacturacion, 'dd/mm/yyyy')
             AND cons.ind_ts_no_conso = 0
             AND (cons.ind_pedi_prue = 0 OR cons.ind_pedi_prue IS NULL)
             AND cons.tspa_oid_tipo_soli_pais = tspd.tspa_oid_tipo_soli_pais
             AND cons.pais_oid_pais = lsoidpais
             AND pos.espo_oid_esta_posi <> 2
             AND cab.pais_oid_pais=pais.oid_pais
             AND pos.ofde_oid_deta_ofer=pod.oid_deta_ofer(+)
             and cab.almc_oid_alma=alm.oid_alma
             and pos.almc_oid_almc=alm2.oid_alma(+)
             and pod.tofe_oid_tipo_ofer=pto.oid_tipo_ofer(+)
             and pro.NEGO_OID_NEGO=neg.OID_NEGO(+)
             and nvl(cab.ind_rece_onli,0)=0
             and nvl(pro.ind_prod_serv,0)<>2
             --and pro.MAPR_OID_MARC_PROD=smp.OID_MARC_PROD(+)
             AND (lnnumeroLoteFacturacion IS NULL OR cons.num_lote_fact = lnnumeroLoteFacturacion)
             and nvl(pro.val_atri_3,0)<>1
             and pos.num_unid_aten>0
             )
        GROUP BY cod_pais,
                 tip_mov,
                 cod_tipo_ofer,
                 COD_NEGO,
                 MAPR_OID_MARC_PROD,
                 cod_alma,
                 fec_proc,
                 codigosap
        )
     WHERE totalunidades > 0
        ORDER BY codigosap
    ;

    TYPE interfazrec IS RECORD(
	  pais                          seg_pais.COD_PAIS%type,
	  canal                          seg_canal.COD_CANA%type,
	  acceso                          seg_acces.COD_ACCE%type,
	  subacceso                          seg_subac.COD_sbac%type,
	  tipoper                          seg_tipo_perio.COD_tipo_peri%type,
	  tipoMovim                          bel_tipo_movim_almac.COD_tipo_movi%type,
	  indSal                          bel_tipo_movim_almac.COD_tipo_movi%type,
	  tipoOferta  				  	pre_tipo_ofert.cod_tipo_ofer%type,
	  negocio  				  	mae_negoc.cod_nego%type,
	  marca  				  	  seg_marca_produ.cod_marc_prod%type,
	  fechaProceso					VARCHAR(8),
	  codigoProductoSAP             mae_produ.cod_sap%TYPE,
	  unidadesAtendidas      		ped_solic_posic.NUM_UNID_ATEN%TYPE,
	  ventaneta      		ped_solic_posic.VAL_PREC_NETO_UNIT_LOCA%TYPE,
	  preccont      		ped_solic_posic.VAL_PREC_CONT_UNIT_LOCA%TYPE,
	  almacen      					bel_almac.COD_ALMA%type
     );

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo   VARCHAR2(50);
    lbabrirutlfile    BOOLEAN;

    lscadena  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20);
    lsreplace VARCHAR2(100) := 'a        ';

	lsoidperiodo seg_perio_corpo.OID_PERI%type;
	lsoidpais	 seg_pais.OID_PAIS%type;
    lnnumeroLoteFacturacion NUMBER;

  BEGIN

	lsoidperiodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(pscodigoperiodo);
	lsoidpais	 := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(pscodigopais);
    lbabrirutlfile := TRUE;

    /* En caso el indicador de envio de ultimo lote de facturacion tenga el valor 1
       SE Obtendra el numero delote de facturacion
    */

    IF(psindicadorenvioultimolote='1')THEN

      BEGIN
          SELECT MAX (cons.num_lote_fact) INTO lnnumeroLoteFacturacion
          FROM ped_solic_cabec cons,
               int_lar_tipo_solici_pedido_dis tspd
         WHERE cons.perd_oid_peri = lsoidperiodo
           AND cons.fec_fact = to_date(psfechaFacturacion, 'dd/mm/yyyy')
           AND cons.ind_ts_no_conso = 0
           AND (cons.ind_pedi_prue = 0 OR cons.ind_pedi_prue IS NULL)
           AND cons.tspa_oid_tipo_soli_pais = tspd.tspa_oid_tipo_soli_pais
           AND cons.pais_oid_pais = lsoidpais;
      EXCEPTION
        when others then
            lnnumeroLoteFacturacion:=null;
      END;

    END IF;


    OPEN c_interfaz(lsoidperiodo,lsoidpais,lnnumeroLoteFacturacion);
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais, pscodigosistema, pscodigointerfaz,
                                               psnombrearchivo, lsdirtempo, lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;



      IF interfazrecord.COUNT > 0 THEN
        FOR x IN interfazrecord.FIRST .. interfazrecord.LAST LOOP
          lslinea := translate(psnumerolote, lscadena, lsreplace) 						 || ';' ||
		   		     translate(interfazrecord(x).pais, lscadena, lsreplace) 			 || ';' ||
		   		     translate(interfazrecord(x).canal, lscadena, lsreplace) 			 || ';' ||
		   		     translate(interfazrecord(x).acceso, lscadena, lsreplace) 			 || ';' ||
		   		     translate(interfazrecord(x).subacceso, lscadena, lsreplace) 			 || ';' ||
		   		     translate(interfazrecord(x).tipoper, lscadena, lsreplace) 			 || ';' ||
					 translate(pscodigoperiodo, lscadena, lsreplace) 		   	   	 	 || ';' ||
					 translate(interfazrecord(x).fechaProceso, lscadena, lsreplace)  	 || ';' ||
					 interfazrecord(x).tipoOferta 	   	 || ';' ||
		   		     translate(interfazrecord(x).tipoMovim, lscadena, lsreplace) 			 || ';' ||
		   		     translate(interfazrecord(x).negocio, lscadena, lsreplace) 			 || ';' ||
		   		     translate(interfazrecord(x).marca, lscadena, lsreplace) 			 || ';' ||
		   		     translate('S', lscadena, lsreplace) 			 || ';' ||
                     translate(interfazrecord(x).codigoProductoSAP, lscadena, lsreplace) || ';' ||
                     translate(interfazrecord(x).unidadesAtendidas, lscadena, lsreplace) || ';' ||
                     translate(interfazrecord(x).ventaneta, lscadena, lsreplace) || ';' ||
                     translate(interfazrecord(x).preccont, lscadena, lsreplace) || ';' ||
                     translate(interfazrecord(x).almacen, lscadena, lsreplace) || ';' ||
                     translate('', lscadena, lsreplace) || ';' ||
                     translate('', lscadena, lsreplace) || ';' ||
                     translate('', lscadena, lsreplace) || ';' ||
                     translate('', lscadena, lsreplace);

          utl_file.put_line(v_handle, lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);

      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR', lsdirtempo, psnombrearchivo,
                                             lsnombrearchivo);
    END IF;
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123, 'INT_PR_SAP_ENVI_MOVIM_ALMAC: ' || ls_sqlerrm);
  END int_pr_sap_env_mov_almac_sicc;
  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de movimientos de almacen
  con el formato de SiCC
  Fecha Creacion    : 23/11/2010
  Fecha Modificacion : 23/11/2010
  Autor             : Jorge Yepez
  Modificado por     : Jorge Yepez
  Parametros:
          psCodigoPais   :  Codigo de Pais
          pscodigosistema : Codigo Sistena
          pscodigointerfaz : Codigo Interfaz
          psnombrearchivo     : Nombre Archivo
          pscodigoperiodo  : Codigo de Periodo
          psnumerolote  :     Numero Lote
          psfechaFacturacion : Fecha Facturacion
          psindicadorenvioultimolote   : Indicador envio ultimo lote
          psindicadorenviolar   : Indicador envio Lar
  ***************************************************************************/
  PROCEDURE int_pr_sap_env_mov_almac_col
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
	pscodigoperiodo	 VARCHAR2,
    psfechaFacturacion         VARCHAR2,
	psnumerolote	 VARCHAR2,
    psindicadorenvioultimolote VARCHAR2,
    psindicadorenviolar        VARCHAR2
  ) IS
    CURSOR c_interfaz(lsoidperiodo NUMBER, lsoidpais NUMBER,lnnumeroLoteFacturacion NUMBER) IS

select * from (
     SELECT cod_pais,'VD','GZ','000','CM',tipo_mov,'S',cod_tipo_ofer, COD_NEGO, MAPR_OID_MARC_PROD, fec_proc, codigosap, SUM (num_unid_aten) totalunidades,
                 round(SUM (num_unid_aten*VAL_PREC_NETO_UNIT_LOCA)/SUM (num_unid_aten),2)*100 totalneto,
                 round(SUM (num_unid_aten*VAL_PREC_CONT_UNIT_LOCA)/SUM (num_unid_aten),2)*100 totalcont,
                 cod_alma
  FROM (SELECT   pais.cod_pais,'VD','GZ','000','CM'
                 ,ped_fn_devue_tip_movi_sam(cab.modu_oid_modu, cab.ind_oc, pscodigopais) tipo_mov
                 ,'S',
                 nvl(lpad(pto.cod_tipo_ofer,3,'0'),lpad((select ito.COD_TIPO_OFER_CONC from inc_concu_param_gener ic, INC_TIPO_OFERT_CONCU ito where ic.tioc_oid_tipo_ofer_conc=ito.oid_tipo_ofer_conc and ic.oid_para_gral=cab.copa_oid_para_gene),3,'0')) cod_tipo_ofer,
                 neg.COD_NEGO,
                 --to_char(to_number(smp.COD_MARC_PROD)) COD_MARC_PROD,
                 pro.MAPR_OID_MARC_PROD,
                 to_char(cons.fec_fact, 'yyyymmdd') fec_proc,
                 pro.cod_sap codigosap,
                 pos.num_unid_aten num_unid_aten,
                 pos.VAL_PREC_NETO_UNIT_LOCA VAL_PREC_NETO_UNIT_LOCA,
                 pos.VAL_PREC_CONT_UNIT_LOCA VAL_PREC_CONT_UNIT_LOCA,
                 case when cab.modu_oid_modu=9 then 'A01'
                 else 'A03'--nvl((select decode(d1.liac_oid_conf_lafp_cabe,1,'A01','A03') from ape_confi_liafp_detal d1 where d1.zzon_oid_zona=cab.zzon_oid_zona),'A03')
                 end
                 cod_alma
            FROM ped_solic_cabec cab,
                 ped_solic_cabec cons,
                 ped_solic_posic pos,
                 mae_produ pro,
                 ped_tipo_solic_pais tsp,
                 ped_tipo_solic ts,
                 --int_lar_tipo_solici_pedido_dis tspd,
                 seg_pais pais,
                 pre_ofert_detal pod,
                 --bel_almac alm,
                 gen_i18n_sicc_comun g1,
                 pre_tipo_ofert pto,
                 mae_negoc neg--,
                 --seg_marca_produ smp
           WHERE pos.soca_oid_soli_cabe = cab.oid_soli_cabe
             AND cab.soca_oid_soli_cabe = cons.oid_soli_cabe
             AND pos.prod_oid_prod = pro.oid_prod
             AND cab.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
             AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
             AND cons.perd_oid_peri = lsoidperiodo
             AND cons.ind_inte_lari_gene = psindicadorenviolar
             AND cons.fec_fact = to_date(psfechaFacturacion, 'dd/mm/yyyy')
             and ts.oid_tipo_soli=g1.val_oid and g1.attr_enti='PED_TIPO_SOLIC'
             AND cons.ind_ts_no_conso = 0
             AND (cons.ind_pedi_prue = 0 OR cons.ind_pedi_prue IS NULL)
             --AND cons.tspa_oid_tipo_soli_pais = tspd.tspa_oid_tipo_soli_pais
             AND (cons.tspa_oid_tipo_soli_pais in (select tspa_oid_tipo_soli_pais from int_lar_tipo_solici_pedido_dis) or g1.val_i18n like '%MAV%')
             AND cons.pais_oid_pais = lsoidpais
             AND pos.espo_oid_esta_posi <> 2
             AND cab.pais_oid_pais=pais.oid_pais
             AND pos.ofde_oid_deta_ofer=pod.oid_deta_ofer(+)
             --and cab.almc_oid_alma=alm.oid_alma
             and pod.tofe_oid_tipo_ofer=pto.oid_tipo_ofer(+)
             and pro.NEGO_OID_NEGO=neg.OID_NEGO(+)
             and nvl(cab.ind_rece_onli,0)=0
             and nvl(pro.ind_prod_serv,0)<>2
             and nvl(pro.val_atri_3,0)<>1
             --and pro.MAPR_OID_MARC_PROD=smp.OID_MARC_PROD(+)
             AND (lnnumeroLoteFacturacion IS NULL OR cons.num_lote_fact = lnnumeroLoteFacturacion)
             and pos.num_unid_aten>0
             )
        GROUP BY cod_pais,
                 tipo_mov,
                 cod_tipo_ofer,
                 COD_NEGO,
                 MAPR_OID_MARC_PROD,
                 cod_alma,
                 fec_proc,
                 codigosap
        )
     WHERE totalunidades > 0
        ORDER BY codigosap
    ;

    TYPE interfazrec IS RECORD(
	  pais                          seg_pais.COD_PAIS%type,
	  canal                          seg_canal.COD_CANA%type,
	  acceso                          seg_acces.COD_ACCE%type,
	  subacceso                          seg_subac.COD_sbac%type,
	  tipoper                          seg_tipo_perio.COD_tipo_peri%type,
	  tipoMovim                          bel_tipo_movim_almac.COD_tipo_movi%type,
	  indSal                          bel_tipo_movim_almac.COD_tipo_movi%type,
	  tipoOferta  				  	pre_tipo_ofert.cod_tipo_ofer%type,
	  negocio  				  	mae_negoc.cod_nego%type,
	  marca  				  	  seg_marca_produ.cod_marc_prod%type,
	  fechaProceso					VARCHAR(8),
	  codigoProductoSAP             mae_produ.cod_sap%TYPE,
	  unidadesAtendidas      		ped_solic_posic.NUM_UNID_ATEN%TYPE,
	  ventaneta      		ped_solic_posic.VAL_PREC_NETO_UNIT_LOCA%TYPE,
	  preccont      		ped_solic_posic.VAL_PREC_CONT_UNIT_LOCA%TYPE,
	  almacen      					bel_almac.COD_ALMA%type
     );

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo   VARCHAR2(50);
    lbabrirutlfile    BOOLEAN;

    lscadena  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20);
    lsreplace VARCHAR2(100) := 'a        ';

	lsoidperiodo seg_perio_corpo.OID_PERI%type;
	lsoidpais	 seg_pais.OID_PAIS%type;
    lnnumeroLoteFacturacion NUMBER;

  BEGIN

	lsoidperiodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(pscodigoperiodo);
	lsoidpais	 := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(pscodigopais);
    lbabrirutlfile := TRUE;

    /* En caso el indicador de envio de ultimo lote de facturacion tenga el valor 1
       SE Obtendra el numero delote de facturacion
    */

    IF(psindicadorenvioultimolote='1')THEN

      BEGIN
          SELECT MAX (cons.num_lote_fact) INTO lnnumeroLoteFacturacion
          FROM ped_solic_cabec cons,
               int_lar_tipo_solici_pedido_dis tspd
         WHERE cons.perd_oid_peri = lsoidperiodo
           AND cons.fec_fact = to_date(psfechaFacturacion, 'dd/mm/yyyy')
           AND cons.ind_ts_no_conso = 0
           AND (cons.ind_pedi_prue = 0 OR cons.ind_pedi_prue IS NULL)
           AND cons.tspa_oid_tipo_soli_pais = tspd.tspa_oid_tipo_soli_pais
           AND cons.pais_oid_pais = lsoidpais;
      EXCEPTION
        when others then
            lnnumeroLoteFacturacion:=null;
      END;

    END IF;


    OPEN c_interfaz(lsoidperiodo,lsoidpais,lnnumeroLoteFacturacion);
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais, pscodigosistema, pscodigointerfaz,
                                               psnombrearchivo, lsdirtempo, lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;



      IF interfazrecord.COUNT > 0 THEN
        FOR x IN interfazrecord.FIRST .. interfazrecord.LAST LOOP
          lslinea := translate(psnumerolote, lscadena, lsreplace) 						 || ';' ||
		   		     translate(interfazrecord(x).pais, lscadena, lsreplace) 			 || ';' ||
		   		     translate(interfazrecord(x).canal, lscadena, lsreplace) 			 || ';' ||
		   		     translate(interfazrecord(x).acceso, lscadena, lsreplace) 			 || ';' ||
		   		     translate(interfazrecord(x).subacceso, lscadena, lsreplace) 			 || ';' ||
		   		     translate(interfazrecord(x).tipoper, lscadena, lsreplace) 			 || ';' ||
					 translate(pscodigoperiodo, lscadena, lsreplace) 		   	   	 	 || ';' ||
					 translate(interfazrecord(x).fechaProceso, lscadena, lsreplace)  	 || ';' ||
					 interfazrecord(x).tipoOferta 	   	 || ';' ||
		   		     translate(interfazrecord(x).tipoMovim, lscadena, lsreplace) 			 || ';' ||
		   		     translate(interfazrecord(x).negocio, lscadena, lsreplace) 			 || ';' ||
		   		     translate(interfazrecord(x).marca, lscadena, lsreplace) 			 || ';' ||
		   		     translate('S', lscadena, lsreplace) 			 || ';' ||
                     translate(interfazrecord(x).codigoProductoSAP, lscadena, lsreplace) || ';' ||
                     translate(interfazrecord(x).unidadesAtendidas, lscadena, lsreplace) || ';' ||
                     translate(interfazrecord(x).ventaneta, lscadena, lsreplace) || ';' ||
                     translate(interfazrecord(x).preccont, lscadena, lsreplace) || ';' ||
                     translate(interfazrecord(x).almacen, lscadena, lsreplace) || ';' ||
                     translate('', lscadena, lsreplace) || ';' ||
                     translate('', lscadena, lsreplace) || ';' ||
                     translate('', lscadena, lsreplace) || ';' ||
                     translate('', lscadena, lsreplace);

          utl_file.put_line(v_handle, lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);

      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR', lsdirtempo, psnombrearchivo,
                                             lsnombrearchivo);
    END IF;
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123, 'INT_PR_SAP_ENVI_MOVIM_ALMAC_COL: ' || ls_sqlerrm);
  END int_pr_sap_env_mov_almac_col;
/***************************************************************************
 Descripcion       : Genera Interfaz de Recepcion de productos nacionales
                     e importados
 Fecha Creacion    : 09/11/2010
 Autor             : Dennys Oliva Iriarte
 ***************************************************************************/
  PROCEDURE INT_PR_SAP_RECEP_PRODU_NAIMP(pscodigopais     VARCHAR2,
                    										 pscodigosistema  VARCHAR2,
                    										 pscodigointerfaz VARCHAR2,
                    										 psnombrearchivo  VARCHAR2) IS
    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
             bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;

    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);

    TYPE interfazcabtab IS TABLE OF interfazcab;

    interfazrecord interfazcabtab;

	TYPE t_cod_cent          IS TABLE OF varchar2(4);
	TYPE t_cod_sap_ppal      IS TABLE OF PRE_PROD_ALTER_ICE.COD_SAP_PPAL%TYPE;
	TYPE t_cod_sap_alte      IS TABLE OF PRE_PROD_ALTER_ICE.COD_SAP_ALTE%TYPE;

	v_cod_cent           t_cod_cent         :=     t_cod_cent();
	v_cod_sap_ppal       t_cod_sap_ppal     :=     t_cod_sap_ppal();
	v_cod_sap_alte       t_cod_sap_alte     :=     t_cod_sap_alte();


    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo VARCHAR2(50);

    /* Variables de parametros */

    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;
    inicio   NUMBER := 0;

  BEGIN

    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 '',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle);

    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
          utl_file.get_line(v_handle, lslinea);
          i      := i + 1;
          inicio := 1;
          IF lslinea IS NULL THEN
            EXIT;
          END IF;

          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
      				IF interfazrecord.COUNT > 0 THEN
      				  FOR x IN interfazrecord.FIRST .. interfazrecord.LAST
      				  LOOP
      					posicion := interfazrecord(x).posiccampo;
      					longitud := interfazrecord(x).longcampo;

      					IF (posicion = 1) THEN
      					  v_cod_cent.EXTEND;
      					  v_cod_cent(i) := TRIM(substr(lslinea,inicio,longitud));
      					ELSIF (posicion = 2) THEN
      					  v_cod_sap_ppal.EXTEND;
      					  v_cod_sap_ppal(i) := TRIM(substr(lslinea,inicio,longitud));
      					ELSIF (posicion = 3) THEN
      					  v_cod_sap_alte.EXTEND;
      					  v_cod_sap_alte(i) := TRIM(substr(lslinea,inicio,longitud));
      					END IF;

      					inicio := inicio + longitud;
      				  END LOOP;
      				END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;

        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;

    utl_file.fclose(v_handle);

    -- Bulk bind of data in memory table...
    FOR i IN 1 .. v_cod_sap_ppal.COUNT loop
      begin
		 insert into PRE_PROD_ALTER_ICE(COD_SAP_PPAL,
                                    COD_SAP_ALTE )
			values (v_cod_sap_ppal(i),
			        v_cod_sap_alte(i));
	  exception
		when dup_val_on_index then
		  null;
	  end;

	end loop;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_SAP_RECEP_PRODU_NAIMP: ' ||
                               psnombrearchivo || '**' ||
                               ls_sqlerrm);

  END INT_PR_SAP_RECEP_PRODU_NAIMP;

/**************************************************************************
Descripcion        : recupera el ultimo Numero Movimiento para un determinado
                     Pais y Operacion
Fecha Creacion     :  07/11/2011
Parametros Entrada :
           psCodigoPais : codigo Pais
           psCodigoOperacion : codigo Operacion
Autor              : Sergio Apaza
***************************************************************************/
PROCEDURE INT_PR_OBTIE_NUMER_MOVIM(psCodigoPais       VARCHAR2,
                                   psCodigoOperacion  VARCHAR2,
                                   pnNumeroMovimiento OUT NUMBER)
IS
  lnNumeroMovimiento     NUMBER;
  lsAnio                 VARCHAR2(2);
  lbEncontrado           BOOLEAN;
BEGIN
  lsAnio := TO_CHAR(sysdate, 'yy');
  lbEncontrado := FALSE;

  BEGIN
    SELECT VAL_ULTI_NUME_SOLI + 1
      INTO lnNumeroMovimiento
      FROM PED_NUMER_SOLIC
     WHERE COD_PAIS = psCodigoPais
       AND VAL_OPER = psCodigoOperacion
       AND VAL_ANIO = lsAnio;

    lbEncontrado := TRUE;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      lnNumeroMovimiento := 1;
  END;

  IF(lbEncontrado) THEN
    UPDATE PED_NUMER_SOLIC
  	   SET VAL_ULTI_NUME_SOLI = lnNumeroMovimiento
  	 WHERE COD_PAIS = psCodigoPais
  	   AND VAL_OPER = psCodigoOperacion
       AND VAL_ANIO = lsAnio;
  ELSE
    INSERT INTO PED_NUMER_SOLIC
     (OID_NUME_SOLI, COD_CANA, COD_ACCE,
      COD_SUBA, COD_PAIS, VAL_OPER,
      VAL_ANIO, VAL_ULTI_NUME_SOLI)
    VALUES
     (PED_NUSO_SEQ.NEXTVAL, 'VD', 'GZ',
      '000', psCodigoPais, psCodigoOperacion,
      lsAnio, lnNumeroMovimiento);
  END IF;

  pnNumeroMovimiento := lnNumeroMovimiento;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_OBTIE_NUMER_MOVIM: ' || ls_sqlerrm);

END INT_PR_OBTIE_NUMER_MOVIM;

/***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Reserva PROL
  Fecha Creacion    : 28/12/2011
  Autor             : Jose Luis Rodriguez
  Parametros:
          psCodigoPais                 : Codigo de Pais
          pscodigosistema              : Codigo Sistena
          pscodigointerfaz             : Codigo Interfaz
          psnombrearchivo              : Nombre Archivo
          pscodigoperiodo              : Codigo de Periodo
          psnumerolote                 : Numero Lote
          psfechaFacturacion           : Fecha Facturacion
          psindicadorenvioultimolote   : Indicador envio ultimo lote
          psindicadorenviolar          : Indicador envio Lar
  ***************************************************************************/
  PROCEDURE int_pr_sap_envi_reser_prol (
    pscodigopais                VARCHAR2,
    pscodigosistema             VARCHAR2,
    pscodigointerfaz            VARCHAR2,
    psnombrearchivo             VARCHAR2,
	  pscodigoperiodo	            VARCHAR2,
    psfechaFacturacion          VARCHAR2,
  	psnumerolote	              VARCHAR2,
    psindicadorenvioultimolote  VARCHAR2,
    psindicadorenviolar         VARCHAR2
  )
  IS

     CURSOR c_interfaz(lsoidperiodo NUMBER, lsoidpais NUMBER,lnnumeroLoteFacturacion NUMBER) IS
       SELECT *
         FROM (SELECT pais.cod_pais pais,
                      'P01'  almacen,
                      pro.cod_sap codigosap,
                      SUM (pos.NUM_UNID_COMPR) totalunidades,
                      to_char(sysdate, 'yyyymmdd') fec_proc
                 FROM ped_solic_cabec cab,
                      --ped_solic_cabec cons,
                      ped_solic_posic pos,
                      mae_produ pro,
                      ped_tipo_solic_pais tsp,
                      ped_tipo_solic ts,
                      --int_lar_tipo_solici_pedido_dis tspd,
                      seg_pais pais--,
                      --pre_ofert_detal pod,
                      --bel_almac alm,
                      --pre_tipo_ofert pto
                WHERE pos.soca_oid_soli_cabe = cab.oid_soli_cabe
                  --AND cab.soca_oid_soli_cabe = cons.oid_soli_cabe
                  AND pos.prod_oid_prod = pro.oid_prod
                  AND cab.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
                  AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                  AND cab.perd_oid_peri = lsoidperiodo
                  --AND cons.ind_inte_lari_gene = psindicadorenviolar
                  --AND cons.fec_fact = to_date(psfechaFacturacion, 'dd/mm/yyyy')
                  --AND cons.ind_ts_no_conso = 0
                  --AND (cons.ind_pedi_prue = 0 OR cons.ind_pedi_prue IS NULL)
                  --AND cab.tspa_oid_tipo_soli_pais = tspd.tspa_oid_tipo_soli_pais
                  AND cab.pais_oid_pais = lsoidpais
                  AND pos.espo_oid_esta_posi <> 2
                  AND cab.pais_oid_pais = pais.oid_pais
                  and cab.grpr_oid_grup_proc=3
                  and cab.esso_oid_esta_soli in (1,5,6)
                  and cab.ind_rece_onli=1
                  --AND pos.ofde_oid_deta_ofer = pod.oid_deta_ofer(+)
                  --AND cab.almc_oid_alma = alm.oid_alma
                  --AND pod.tofe_oid_tipo_ofer = pto.oid_tipo_ofer(+)
                  --AND (lnnumeroLoteFacturacion IS NULL OR cons.num_lote_fact = lnnumeroLoteFacturacion)
             GROUP BY pais.cod_pais,
                      --pto.cod_tipo_ofer,
                      'P01',
                      pro.cod_sap,
                      to_char(sysdate, 'yyyymmdd')--,
                      )  tabla
        WHERE totalunidades > 0
     ORDER BY 4, 2;

    TYPE interfazrec IS RECORD(
  	  pais               seg_pais.COD_PAIS%type,
	    almacen      			 bel_almac.COD_ALMA%TYPE,
      codigoProductoSAP  mae_produ.cod_sap%TYPE,
	    unidadesAtendidas  ped_solic_posic.NUM_UNID_ATEN%TYPE,
      fechaProceso			 VARCHAR(8)
    );

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord  interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo  bas_inter.dir_temp%TYPE;
    w_filas     NUMBER := 1000;
    v_handle    utl_file.file_type;

    lslinea  VARCHAR2(1000);

    lsnombrearchivo  VARCHAR2(50);
    lbabrirutlfile   BOOLEAN;

    lscadena   VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20);
    lsreplace  VARCHAR2(100) := 'a        ';

  	lsoidperiodo             seg_perio_corpo.OID_PERI%type;
	  lsoidpais	               seg_pais.OID_PAIS%type;
    lnnumeroLoteFacturacion  NUMBER;

  BEGIN

    lsoidperiodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(pscodigoperiodo);
	  lsoidpais	 := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(pscodigopais);
    lbabrirutlfile := TRUE;

    /* En caso el indicador de envío de último lote de facturación tenga el valor 1
       SE Obtendra el numero delote de facturacion
    */
    IF(psindicadorenvioultimolote='1')THEN

      BEGIN
        SELECT MAX (cons.num_lote_fact) INTO lnnumeroLoteFacturacion
          FROM ped_solic_cabec cons,
               int_lar_tipo_solici_pedido_dis tspd
         WHERE cons.perd_oid_peri = lsoidperiodo
           AND cons.fec_fact = to_date(psfechaFacturacion, 'dd/mm/yyyy')
           AND cons.ind_ts_no_conso = 0
           AND (cons.ind_pedi_prue = 0 OR cons.ind_pedi_prue IS NULL)
           AND cons.tspa_oid_tipo_soli_pais = tspd.tspa_oid_tipo_soli_pais
           AND cons.pais_oid_pais = lsoidpais;
      EXCEPTION
        WHEN OTHERS THEN
            lnnumeroLoteFacturacion:=null;
      END;

    END IF;

    OPEN c_interfaz(lsoidperiodo,lsoidpais,lnnumeroLoteFacturacion);
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;

      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais, pscodigosistema, pscodigointerfaz,
                                               psnombrearchivo, lsdirtempo, lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;

      IF interfazrecord.COUNT > 0 THEN
        FOR x IN interfazrecord.FIRST .. interfazrecord.LAST LOOP
          lslinea := translate(interfazrecord(x).pais, lscadena, lsreplace)        			  || ';' ||
                     translate(interfazrecord(x).almacen, lscadena, lsreplace)		        || ';' ||
                     translate(interfazrecord(x).codigoProductoSAP, lscadena, lsreplace)  || ';' ||
                     translate(interfazrecord(x).unidadesAtendidas, lscadena, lsreplace)  || ';' ||
                     translate(interfazrecord(x).fechaProceso, lscadena, lsreplace);

          utl_file.put_line(v_handle, lslinea);

        END LOOP;
      END IF;

      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);

      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR', lsdirtempo, psnombrearchivo, lsnombrearchivo);
    END IF;

    RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm,1,250);
      RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_SAP_ENVI_RESER_PROL: ' || ls_sqlerrm);

  END int_pr_sap_envi_reser_prol;

/***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Cantidad Productos
  Fecha Creacion    : 05/06/2012
  Autor             : Sergio Buchelli
  Parametros:
          psCodigoPais                 : Codigo de Pais
          pscodigosistema              : Codigo Sistena
          pscodigointerfaz             : Codigo Interfaz
          psnombrearchivo              : Nombre Archivo
          pscodigoperiodo              : Codigo de Periodo
          psnumerolote                 : Numero Lote
          psfechaFacturacion           : Fecha Facturacion
  ***************************************************************************/
  PROCEDURE int_pr_sap_envio_canti_produ (
    pscodigopais                VARCHAR2,
    pscodigosistema             VARCHAR2,
    pscodigointerfaz            VARCHAR2,
    psnombrearchivo             VARCHAR2,
	pscodigoperiodo	            VARCHAR2,
    psfechaFacturacion          VARCHAR2,
  	psnumerolote	            VARCHAR2
  )
    IS

     CURSOR c_interfaz(lsoidperiodo NUMBER) IS
        SELECT   pais.cod_pais,
                 pto.cod_tipo_ofer,
                 pro.cod_sap codigosap,
                 SUM (pos.num_unid_compr) totalunidades,
                 decode(cab.ind_rece_onli, 1, 'P01', alm.cod_alma) cod_alma
            FROM ped_solic_cabec cab,
                 ped_solic_posic pos,
                 mae_produ pro,
                 ped_tipo_solic_pais tsp,
                 ped_tipo_solic ts,
                 seg_pais pais,
                 pre_ofert_detal pod,
                 bel_almac alm,
                 pre_tipo_ofert pto
           WHERE pos.soca_oid_soli_cabe = cab.oid_soli_cabe
             AND pos.prod_oid_prod = pro.oid_prod
             AND cab.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
             AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
             AND cab.perd_oid_peri = lsoidperiodo
             AND cab.fec_prog_fact = decode(cab.ind_oc,1,to_date(psfechaFacturacion, 'dd/mm/yyyy'), cab.fec_prog_fact)
             and ts.ind_soli_nega=0
             AND pos.espo_oid_esta_posi <> 2
             AND cab.pais_oid_pais=pais.oid_pais
             AND pos.ofde_oid_deta_ofer=pod.oid_deta_ofer(+)
             and cab.almc_oid_alma=alm.oid_alma
             and cab.grpr_oid_grup_proc=4
             and pos.num_unid_compr>0
             and pod.tofe_oid_tipo_ofer=pto.oid_tipo_ofer(+)
        GROUP BY pais.cod_pais,
                 pto.cod_tipo_ofer,
                 decode(cab.ind_rece_onli, 1, 'P01', alm.cod_alma),
                 pro.cod_sap;


    TYPE interfazrec IS RECORD(
  	    cod_pais              seg_pais.COD_PAIS%type,
        cod_tipo_ofer         pre_tipo_ofert.cod_tipo_ofer%type,
        codigosap             mae_produ.cod_sap%TYPE,
        totalunidades         NUMBER ,
	    cod_alma      		  bel_almac.COD_ALMA%TYPE
    );

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord  interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo  bas_inter.dir_temp%TYPE;
    w_filas     NUMBER := 1000;
    v_handle    utl_file.file_type;

    lslinea  VARCHAR2(1000);

    lsnombrearchivo  VARCHAR2(50);
    lbabrirutlfile   BOOLEAN;

    lscadena   VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20);
    lsreplace  VARCHAR2(100) := 'a        ';

  	lsoidperiodo               seg_perio_corpo.OID_PERI%type;
	lsoidpais	               seg_pais.OID_PAIS%type;


  BEGIN

    lsoidperiodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(pscodigoperiodo);
	 lsoidpais	 := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(pscodigopais);
    lbabrirutlfile := TRUE;


    OPEN c_interfaz(lsoidperiodo);
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;

      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais, pscodigosistema, pscodigointerfaz,
                                               psnombrearchivo, lsdirtempo, lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;

      IF interfazrecord.COUNT > 0 THEN
        FOR x IN interfazrecord.FIRST .. interfazrecord.LAST LOOP
          lslinea := translate(psnumerolote, lscadena, lsreplace)                          || ';' ||
                     translate(interfazrecord(x).cod_alma, lscadena, lsreplace)		       || ';' ||
                     translate(interfazrecord(x).codigosap, lscadena, lsreplace)           || ';' ||
                     translate(interfazrecord(x).cod_tipo_ofer, lscadena, lsreplace)       || ';' ||
                     translate(interfazrecord(x).totalunidades, lscadena, lsreplace);

          utl_file.put_line(v_handle, lslinea);

        END LOOP;
      END IF;

      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR', lsdirtempo, psnombrearchivo, lsnombrearchivo);
    END IF;

    RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm,1,250);
      RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_SAP_ENVIO_CANTI_PRODU: ' || ls_sqlerrm);

  END int_pr_sap_envio_canti_produ;


/***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Archivo de Lote Producto
  Fecha Creacion    : 11/06/2012
  Autor             : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE INT_PR_SAP_RECEP_LOTE_PRODU (pscodigopais     VARCHAR2,
                                         pscodigosistema  VARCHAR2,
                                         pscodigointerfaz VARCHAR2,
                                         psnombrearchivo  VARCHAR2,
                                         psusuario        VARCHAR2,
                                         psCantidadRegistros OUT VARCHAR2)
IS
    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
             bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;

    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);

    TYPE interfazcabtab IS TABLE OF interfazcab;

    interfazrecord interfazcabtab;

    TYPE t_numlote IS TABLE OF bas_histo_lotes.NUM_LOTE%TYPE;
    TYPE t_codsap  IS TABLE OF SAP_TRAZA_LOTE.cod_sap%TYPE;
    TYPE t_lotesap IS TABLE OF SAP_TRAZA_LOTE.LOT_SAP%TYPE;


    v_numlote t_numlote := t_numlote();
    v_codsap  t_codsap  := t_codsap();
    v_lotesap t_lotesap := t_lotesap();


    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);
    i               BINARY_INTEGER := 0;
    posicion        NUMBER := 0;
    longitud        NUMBER := 0;
    inicio          NUMBER := 0;

    v_error_carga  NUMBER := 0;
    v_error_status NUMBER := 0;

    v_camactiva bas_ctrl_fact.cod_peri%TYPE;
    v_fechafactu bas_ctrl_fact.fec_proc%TYPE;

  BEGIN

    EXECUTE IMMEDIATE 'TRUNCATE TABLE SAP_TRAZA_LOTE';
    psCantidadRegistros:=0;
   /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle);

    --Obteniendo la campaña activa
    SELECT c.cod_peri,c.FEC_PROC
      INTO v_camactiva,v_fechafactu
      FROM bas_ctrl_fact c
     WHERE c.cod_pais = pscodigopais
       AND c.ind_camp_act = 1
       AND c.sta_camp = '0';

    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
          utl_file.get_line(v_handle,
                            lslinea);
          i      := i + 1;
          inicio := 1;
          IF lslinea IS NULL THEN
            EXIT;
          END IF;

          v_error_carga  := 0;
          v_error_status := 0;

          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP

                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;

                IF (posicion = 1) THEN
                  v_numlote.extend;
                  v_numlote(i) := TRIM(substr(lslinea,
                                                  inicio,
                                                  longitud));


                ELSIF (posicion = 2) THEN
                  v_codsap.extend;
                  v_codsap(i) := TRIM(substr(lslinea,
                                             inicio,
                                             longitud));


                ELSIF (posicion = 3) THEN
                  v_lotesap.extend;
                  v_lotesap(i) := TRIM(substr(lslinea,
                                              inicio,
                                              longitud));

                END IF;
                inicio := inicio + longitud;

              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;

        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;

    utl_file.fclose(v_handle);

    psCantidadRegistros:=v_numlote.count;
    -- Bulk bind of data in memory table...
    FORALL i IN 1 .. v_numlote.count
      INSERT INTO SAP_TRAZA_LOTE
        (NUM_LOTE,
         COD_SAP,
         LOT_SAP,
         USU_DIGI,
         FEC_DIGI
        )
      VALUES
        (v_numlote(i),
         v_codsap(i),
         v_lotesap(i),
         psusuario,
         SYSDATE
        );

    --borramos los lotes del historico
    FORALL i IN 1 .. v_numlote.count
        delete from SAP_TRAZA_LOTE_HISTO
           where NUM_LOTE =  v_numlote(i);

    -- cargamos al historico
    insert INTO SAP_TRAZA_LOTE_HISTO(OID_LOTE_HIST,
        NUM_LOTE,
        COD_SAP,
        LOT_SAP,
        FEC_FACT,
        USU_DIGI,
        FEC_DIGI)
    SELECT
        SAP_SEQ_TRLO.NEXTVAL,
        NUM_LOTE,
        COD_SAP,
        LOT_SAP,
        v_fechafactu,
        psusuario,
        SYSDATE
    FROM SAP_TRAZA_LOTE;

    RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_SAP_RECEP_LOTE_PRODU: ' || psnombrearchivo || '**' ||
                              ls_sqlerrm);

  END INT_PR_SAP_RECEP_LOTE_PRODU;

/***************************************************************************
  Descripcion       : Proceso de Recepcion de Negocio
  Fecha Creacion    : 26/03/2013
  Autor             : Danny Amaro
  ***************************************************************************/
  PROCEDURE INT_PR_SAP_RECEP_NEGO (pscodigopais     VARCHAR2,
                                   pscodigonegocio  VARCHAR2,
                                   pscodigoidioma   VARCHAR2,
                                   psdescripcion    VARCHAR2)
  IS
    lnOidPais              NUMBER;
    lnNumeroNegocio        NUMBER;
    lnNumeroNegocioDes     NUMBER;
    lnNumeroSeq            NUMBER;

  BEGIN

    SELECT OID_PAIS
      INTO lnOidPais
      FROM SEG_PAIS A
     WHERE A.COD_PAIS = pscodigopais;

    SELECT COUNT(M.OID_NEGO) INTO lnNumeroNegocio
      FROM MAE_NEGOC M
     WHERE M.COD_NEGO = pscodigonegocio;

    IF(lnNumeroNegocio > 0)THEN

            SELECT COUNT(G.OID_I18N) INTO lnNumeroNegocioDes
              FROM GEN_I18N_SICC_PAIS G
             WHERE G.Attr_Enti = 'MAE_NEGOC'
             and g.val_oid=(SELECT OID_NEGO FROM MAE_NEGOC WHERE COD_NEGO = pscodigonegocio)
             and g.idio_oid_idio=(select oid_idio from seg_idiom where upper(COD_ISO_IDIO)=upper(pscodigoidioma));

          IF(lnNumeroNegocioDes > 0)THEN


      UPDATE GEN_I18N_SICC_PAIS
         SET VAL_I18N = psdescripcion
       WHERE ATTR_ENTI='MAE_NEGOC' and VAL_OID in
                   (SELECT OID_NEGO FROM MAE_NEGOC WHERE COD_NEGO = pscodigonegocio)
                   and idio_oid_idio in
                   (select oid_idio from seg_idiom where upper(COD_ISO_IDIO)=upper(pscodigoidioma));
          else

            INSERT INTO GEN_I18N_SICC_PAIS
              (OID_I18N, ATTR_ENTI, ATTR_NUM_ATRI, IDIO_OID_IDIO, VAL_I18N, VAL_OID)
            VALUES
              (GEN_I18N_SEQ.NEXTVAL,
               'MAE_NEGOC',
               1,
               (select oid_idio from seg_idiom where upper(COD_ISO_IDIO)=upper(pscodigoidioma)),
               psdescripcion,
               (SELECT OID_NEGO FROM MAE_NEGOC WHERE COD_NEGO = pscodigonegocio));

          end if;

    ELSE

      INSERT INTO MAE_NEGOC
          (OID_NEGO, PAIS_OID_PAIS, COD_NEGO)
        VALUES
          (MAE_NEGO_SEQ.NEXTVAL, lnOidPais, pscodigonegocio);

      SELECT MAX(MN.OID_NEGO) INTO lnNumeroSeq
      FROM MAE_NEGOC MN;

      INSERT INTO GEN_I18N_SICC_PAIS
			  (OID_I18N, ATTR_ENTI, ATTR_NUM_ATRI, IDIO_OID_IDIO, VAL_I18N, VAL_OID)
			VALUES
			  (GEN_I18N_SEQ.NEXTVAL,
			   'MAE_NEGOC',
			   1,
			   (select oid_idio from seg_idiom where upper(COD_ISO_IDIO)=upper(pscodigoidioma)),
			   psdescripcion,
			   lnNumeroSeq);

    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm,1,250);
      RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_SAP_RECEP_NEGO: ' || ls_sqlerrm);

  END INT_PR_SAP_RECEP_NEGO;


/***************************************************************************
  Descripcion       : Proceso de Recepcion Super Gen¿rico
  Fecha Creacion    : 26/03/2013
  Autor             : Danny Amaro
  ***************************************************************************/
  PROCEDURE INT_PR_SAP_RECEP_SUPER_GENER(pscodigopais           VARCHAR2,
                                         pscodigosupergenerico  VARCHAR2,
                                         pscodigoidioma         VARCHAR2,
                                         psdescripcion          VARCHAR2)
 IS
    lnOidPais              NUMBER;
    lnNumeroSuperGenerico  NUMBER;
    lnNumeroSuperGenericoDes  NUMBER;
    lnNumeroSeq            NUMBER;

  BEGIN

if pscodigosupergenerico is not null then

    SELECT OID_PAIS
      INTO lnOidPais
      FROM SEG_PAIS A
     WHERE A.COD_PAIS = pscodigopais;

    SELECT COUNT(M.OID_SUPE_GENE) INTO lnNumeroSuperGenerico
      FROM MAE_SUPER_GENER M
     WHERE M.COD_SUPE_GENE = pscodigosupergenerico;

    IF(lnNumeroSuperGenerico > 0)THEN

            SELECT COUNT(G.OID_I18N) INTO lnNumeroSuperGenericoDes
              FROM GEN_I18N_SICC_PAIS G
             WHERE G.Attr_Enti = 'MAE_SUPER_GENER'
             and g.val_oid=(SELECT OID_SUPE_GENE FROM MAE_SUPER_GENER WHERE COD_SUPE_GENE = pscodigosupergenerico)
             and g.idio_oid_idio=(select oid_idio from seg_idiom where upper(COD_ISO_IDIO)=upper(pscodigoidioma));

          IF(lnNumeroSuperGenericoDes > 0)THEN


      UPDATE GEN_I18N_SICC_PAIS
         SET VAL_I18N = nvl(psdescripcion,'XXXXXX')
       WHERE ATTR_ENTI='MAE_SUPER_GENER' and VAL_OID in
                       (SELECT OID_SUPE_GENE FROM MAE_SUPER_GENER WHERE COD_SUPE_GENE = pscodigosupergenerico)
                   and idio_oid_idio in
                   (select oid_idio from seg_idiom where upper(COD_ISO_IDIO)=upper(pscodigoidioma));
          else

            INSERT INTO GEN_I18N_SICC_PAIS
              (OID_I18N, ATTR_ENTI, ATTR_NUM_ATRI, IDIO_OID_IDIO, VAL_I18N, VAL_OID)
            VALUES
              (GEN_I18N_SEQ.NEXTVAL,
               'MAE_SUPER_GENER',
               1,
               (select oid_idio from seg_idiom where upper(COD_ISO_IDIO)=upper(pscodigoidioma)),
               nvl(psdescripcion,'XXXXXX'),
               (SELECT OID_SUPE_GENE FROM MAE_SUPER_GENER WHERE COD_SUPE_GENE = pscodigosupergenerico));

          end if;




    ELSE

      INSERT INTO MAE_SUPER_GENER
          (OID_SUPE_GENE, PAIS_OID_PAIS, COD_SUPE_GENE)
        VALUES
          (MAE_SGEN_SEQ.NEXTVAL, lnOidPais, pscodigosupergenerico);

      SELECT MAX(MN.OID_SUPE_GENE) INTO lnNumeroSeq
      FROM MAE_SUPER_GENER MN;

      INSERT INTO GEN_I18N_SICC_PAIS
			  (OID_I18N, ATTR_ENTI, ATTR_NUM_ATRI, IDIO_OID_IDIO, VAL_I18N, VAL_OID)
			VALUES
			  (GEN_I18N_SEQ.NEXTVAL,
			   'MAE_SUPER_GENER',
			   1,
			   (select oid_idio from seg_idiom where upper(COD_ISO_IDIO)=upper(pscodigoidioma)),
			   nvl(psdescripcion,'XXXXXX'),
			   lnNumeroSeq);

    END IF;
    
end if;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm,1,250);
      RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_SAP_RECEP_SUPER_GENER: ' || ls_sqlerrm);

  END INT_PR_SAP_RECEP_SUPER_GENER;

  /***************************************************************************
  Descripcion       : Proceso de Recepcion Gen¿rico
  Fecha Creacion    : 26/03/2013
  Autor             : Danny Amaro
  ***************************************************************************/
  PROCEDURE INT_PR_SAP_RECEP_GENER(pscodigopais           VARCHAR2,
                                   pscodigogenerico       VARCHAR2,
                                   pscodigoidioma         VARCHAR2,
                                   psdescripcion          VARCHAR2)
 IS
    lnOidPais              NUMBER;
    lnNumeroGenerico       NUMBER;
    lnNumeroGenericoDes       NUMBER;
    lnNumeroSeq            NUMBER;

  BEGIN

if pscodigogenerico is not null then

    SELECT OID_PAIS
      INTO lnOidPais
      FROM SEG_PAIS A
     WHERE A.COD_PAIS = pscodigopais;

    SELECT COUNT(M.OID_GENE) INTO lnNumeroGenerico
      FROM MAE_GENER M
     WHERE M.COD_GENE = pscodigogenerico;

    IF(lnNumeroGenerico > 0)THEN

            SELECT COUNT(G.OID_I18N) INTO lnNumeroGenericoDes
              FROM GEN_I18N_SICC_PAIS G
             WHERE G.Attr_Enti = 'MAE_GENER'
             and g.val_oid=(SELECT OID_GENE FROM MAE_GENER WHERE COD_GENE = pscodigogenerico)
             and g.idio_oid_idio=(select oid_idio from seg_idiom where upper(COD_ISO_IDIO)=upper(pscodigoidioma));

          IF(lnNumeroGenericoDes > 0)THEN


      UPDATE GEN_I18N_SICC_PAIS
         SET VAL_I18N = nvl(psdescripcion,'XXXXXX')
       WHERE ATTR_ENTI='MAE_GENER' and VAL_OID IN
                       (SELECT OID_GENE FROM MAE_GENER WHERE COD_GENE = pscodigogenerico)
                   and idio_oid_idio in
                   (select oid_idio from seg_idiom where upper(COD_ISO_IDIO)=upper(pscodigoidioma));
          else

                INSERT INTO GEN_I18N_SICC_PAIS
                  (OID_I18N, ATTR_ENTI, ATTR_NUM_ATRI, IDIO_OID_IDIO, VAL_I18N, VAL_OID)
                VALUES
                  (GEN_I18N_SEQ.NEXTVAL,
                   'MAE_GENER',
                   1,
                   (select oid_idio from seg_idiom where upper(COD_ISO_IDIO)=upper(pscodigoidioma)),
                   nvl(psdescripcion,'XXXXXX'),
                   (SELECT OID_GENE FROM MAE_GENER WHERE COD_GENE = pscodigogenerico));

          end if;


    ELSE

      INSERT INTO MAE_GENER
          (OID_GENE, PAIS_OID_PAIS, COD_GENE)
        VALUES
          (MAE_GENE_SEQ.NEXTVAL, lnOidPais, pscodigogenerico);

      SELECT MAX(MN.OID_GENE) INTO lnNumeroSeq
      FROM MAE_GENER MN;

      INSERT INTO GEN_I18N_SICC_PAIS
			  (OID_I18N, ATTR_ENTI, ATTR_NUM_ATRI, IDIO_OID_IDIO, VAL_I18N, VAL_OID)
			VALUES
			  (GEN_I18N_SEQ.NEXTVAL,
			   'MAE_GENER',
			   1,
         (select oid_idio from seg_idiom where upper(COD_ISO_IDIO)=upper(pscodigoidioma)),
			   nvl(psdescripcion,'XXXXXX'),
			   lnNumeroSeq);

    END IF;

end if;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm,1,250);
      RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_SAP_RECEP_GENER: ' || ls_sqlerrm);

  END INT_PR_SAP_RECEP_GENER;

/***************************************************************************
  Descripcion       : Proceso de Recepcion Descripcion de Producto por Idioma
  Fecha Creacion    : 26/03/2013
  Autor             : Danny Amaro
  ***************************************************************************/
  PROCEDURE INT_PR_SAP_RECEP_DESC_PRODU(pscodigopais           VARCHAR2,
                                        pscodigoproducto       VARCHAR2,
                                        pscodigoidioma         VARCHAR2,
                                        psdescripcion          VARCHAR2)
 IS
    lnOidPais              NUMBER;
    lnNumeroProducto       NUMBER;
    lnNumeroDesc           NUMBER;
    lnNumeroSeq            NUMBER;
    lnCodigoIdioma         NUMBER;
    lsCodigoIdioma         varchar2(2);

  BEGIN

    SELECT OID_PAIS
      INTO lnOidPais
      FROM SEG_PAIS A
     WHERE A.COD_PAIS = pscodigopais;

    SELECT COUNT(P.COD_SAP) INTO lnNumeroProducto
      FROM MAE_PRODU P
     WHERE P.COD_SAP = pscodigoproducto;

     select count(*) into lnCodigoIdioma 
     from seg_idiom 
     where upper(COD_ISO_IDIO)=upper(pscodigoidioma);

     lsCodigoIdioma := pscodigoidioma;
     if lnCodigoIdioma = 0 then
        lsCodigoIdioma := 'ES';
     end if;

    IF(lnNumeroProducto > 0)THEN

            SELECT COUNT(G.OID_I18N) INTO lnNumeroDesc
              FROM GEN_I18N_SICC_PAIS G
             WHERE G.Attr_Enti = 'MAE_PRODU'
             and g.val_oid=(SELECT OID_PROD FROM MAE_PRODU WHERE COD_SAP = pscodigoproducto)
             and g.idio_oid_idio=(select oid_idio from seg_idiom where upper(COD_ISO_IDIO)=upper(lsCodigoIdioma));


          IF(lnNumeroDesc > 0)THEN


            UPDATE GEN_I18N_SICC_PAIS
               SET VAL_I18N = psdescripcion
             WHERE ATTR_ENTI='MAE_PRODU' and VAL_OID in
                   (SELECT OID_PROD FROM MAE_PRODU WHERE COD_SAP = pscodigoproducto)
                   and idio_oid_idio in
                   (select oid_idio from seg_idiom where upper(COD_ISO_IDIO)=upper(lsCodigoIdioma));
          else

        INSERT INTO GEN_I18N_SICC_PAIS
          (OID_I18N, ATTR_ENTI, ATTR_NUM_ATRI, IDIO_OID_IDIO, VAL_I18N, VAL_OID)
        VALUES
          (GEN_I18N_SEQ.NEXTVAL,
           'MAE_PRODU',
           1,
                 (select oid_idio from seg_idiom where upper(COD_ISO_IDIO)=upper(lsCodigoIdioma)),
                 psdescripcion,
                 (SELECT OID_PROD FROM MAE_PRODU WHERE COD_SAP = pscodigoproducto));

          end if;
      /*else

        INSERT INTO GEN_I18N_SICC_PAIS
          (OID_I18N, ATTR_ENTI, ATTR_NUM_ATRI, IDIO_OID_IDIO, VAL_I18N, VAL_OID)
        VALUES
          (GEN_I18N_SEQ.NEXTVAL,
           'MAE_PRODU',
           1,
           (select oid_idio from seg_idiom where upper(COD_ISO_IDIO)=upper(pscodigoidioma)),
           psdescripcion,
           (SELECT OID_PROD FROM MAE_PRODU WHERE COD_SAP = pscodigoproducto));
       */
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm,1,250);
      RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_SAP_RECEP_DESC_PRODU: ' || ls_sqlerrm);

  END INT_PR_SAP_RECEP_DESC_PRODU;

  /***************************************************************************
  Descripcion       : Proceso de Recepcion de Producto
  Fecha Creacion    : 27/03/2013
  Autor             : Danny Amaro
  ***************************************************************************/
  PROCEDURE INT_PR_SAP_RECEP_PRODU(pscodigopais           VARCHAR2,
                                   pscodigoproducto       VARCHAR2,
                                   psdescripcion          VARCHAR2,
                                   psindicadorProServ     VARCHAR2,
                                   pscodigomarca          VARCHAR2,
                                   pscodigounidadnegocio  VARCHAR2,
                                   pscodigonegocio        VARCHAR2,
                                   psgrupoarticulo        VARCHAR2,
                                   pscodigogenerico       VARCHAR2,
                                   pscodigosupergenerico  VARCHAR2,
                                   psjerarquia1           VARCHAR2,
                                   psjerarquia2           VARCHAR2,
                                   psjerarquia3           VARCHAR2,
                                   psalto                 VARCHAR2,
                                   pslargo                VARCHAR2,
                                   psancho                VARCHAR2,
                                   psindicadorFueraCaja   VARCHAR2,
                                   psvolumen              VARCHAR2,
                                   pspesobruto            VARCHAR2,
                                   pscodigoestatus        VARCHAR2,
                                   pscosteestandar        VARCHAR2,
                                   pscodigounimedvolumen  VARCHAR2,
                                   pscodigounimedpeso     VARCHAR2,
                                   pscodigoantiguo        VARCHAR2,
                                   pscuv                  VARCHAR2)
 IS
    lnOidPais              NUMBER;
    lnNumeroRegistros      NUMBER;
    lnNumeroSeq            NUMBER;

  BEGIN

    SELECT OID_PAIS
      INTO lnOidPais
      FROM SEG_PAIS A
     WHERE A.COD_PAIS = pscodigopais;

    SELECT COUNT(M.OID_UNID_NEGO) INTO lnNumeroRegistros
      FROM MAE_UNIDA_NEGOC M
     WHERE M.COD_UNID_NEGO = nvl(pscodigounidadnegocio,'XXXX');

    IF(lnNumeroRegistros = 0 and pscodigounidadnegocio is not null)THEN

        INSERT INTO MAE_UNIDA_NEGOC
          (OID_UNID_NEGO, PAIS_OID_PAIS, COD_UNID_NEGO)
        VALUES
          (MAE_UNEG_SEQ.NEXTVAL, lnOidPais, pscodigounidadnegocio);

        SELECT MAX(MN.OID_UNID_NEGO) INTO lnNumeroSeq
        FROM MAE_UNIDA_NEGOC MN;

        INSERT INTO GEN_I18N_SICC_PAIS
          (OID_I18N, ATTR_ENTI, ATTR_NUM_ATRI, IDIO_OID_IDIO, VAL_I18N, VAL_OID)
        VALUES
          (GEN_I18N_SEQ.NEXTVAL,
           'MAE_UNIDA_NEGOC',
           1,
           1,
           'DESCRIPCION PENDIENTE',
           lnNumeroSeq);

    END IF;

    SELECT COUNT(M.OID_NEGO) INTO lnNumeroRegistros
      FROM MAE_NEGOC M
     WHERE M.COD_NEGO = nvl(pscodigonegocio,'XXXX');

    IF(lnNumeroRegistros = 0 and pscodigonegocio is not null)THEN

      INSERT INTO MAE_NEGOC
        (OID_NEGO, PAIS_OID_PAIS, COD_NEGO)
      VALUES
        (MAE_NEGO_SEQ.NEXTVAL, lnOidPais, pscodigonegocio);

      SELECT MAX(MN.OID_NEGO) INTO lnNumeroSeq
      FROM MAE_NEGOC MN;

      INSERT INTO GEN_I18N_SICC_PAIS
        (OID_I18N, ATTR_ENTI, ATTR_NUM_ATRI, IDIO_OID_IDIO, VAL_I18N, VAL_OID)
      VALUES
        (GEN_I18N_SEQ.NEXTVAL,
         'MAE_NEGOC',
         1,
         1,
         'DESCRIPCION PENDIENTE',
         lnNumeroSeq);

    END IF;

    SELECT COUNT(M.OID_GENE) INTO lnNumeroRegistros
      FROM MAE_GENER M
     WHERE M.COD_GENE = nvl(pscodigogenerico,'XXXX');

    IF(lnNumeroRegistros = 0 and pscodigogenerico is not null)THEN

      INSERT INTO MAE_GENER
          (OID_GENE, PAIS_OID_PAIS, COD_GENE)
        VALUES
          (MAE_GENE_SEQ.NEXTVAL, lnOidPais, pscodigogenerico);

      SELECT MAX(MN.OID_GENE) INTO lnNumeroSeq
      FROM MAE_GENER MN;

      INSERT INTO GEN_I18N_SICC_PAIS
			  (OID_I18N, ATTR_ENTI, ATTR_NUM_ATRI, IDIO_OID_IDIO, VAL_I18N, VAL_OID)
			VALUES
			  (GEN_I18N_SEQ.NEXTVAL,
			   'MAE_GENER',
			   1,
			   1,
			   'DESCRIPCION PENDIENTE',
			   lnNumeroSeq);

    END IF;

    SELECT COUNT(M.OID_SUPE_GENE) INTO lnNumeroRegistros
      FROM MAE_SUPER_GENER M
     WHERE M.COD_SUPE_GENE = nvl(pscodigosupergenerico,'XXXX');

    IF(lnNumeroRegistros = 0 and pscodigosupergenerico is not null)THEN

      INSERT INTO MAE_SUPER_GENER
          (OID_SUPE_GENE, PAIS_OID_PAIS, COD_SUPE_GENE)
        VALUES
          (MAE_SGEN_SEQ.NEXTVAL, lnOidPais, pscodigosupergenerico);

      SELECT MAX(MN.OID_SUPE_GENE) INTO lnNumeroSeq
      FROM MAE_SUPER_GENER MN;

      INSERT INTO GEN_I18N_SICC_PAIS
			  (OID_I18N, ATTR_ENTI, ATTR_NUM_ATRI, IDIO_OID_IDIO, VAL_I18N, VAL_OID)
			VALUES
			  (GEN_I18N_SEQ.NEXTVAL,
			   'MAE_SUPER_GENER',
			   1,
			   1,
			   'DESCRIPCION PENDIENTE',
			   lnNumeroSeq);

    END IF;

    SELECT COUNT(M.OID_ESTA_PROD) INTO lnNumeroRegistros
      FROM MAE_ESTAT_PRODU M
     WHERE M.COD_ESTA_PROD = nvl(pscodigoestatus,'XXXX');

    IF(lnNumeroRegistros = 0 and pscodigoestatus is not null)THEN

      INSERT INTO MAE_ESTAT_PRODU
        (OID_ESTA_PROD, PAIS_OID_PAIS, COD_ESTA_PROD)
      VALUES
        (MAE_ESTA_SEQ.NEXTVAL, lnOidPais, pscodigoestatus);

      SELECT MAX(MN.OID_ESTA_PROD) INTO lnNumeroSeq
      FROM MAE_ESTAT_PRODU MN;

      INSERT INTO GEN_I18N_SICC_PAIS
			  (OID_I18N, ATTR_ENTI, ATTR_NUM_ATRI, IDIO_OID_IDIO, VAL_I18N, VAL_OID)
			VALUES
			  (GEN_I18N_SEQ.NEXTVAL,
			   'MAE_ESTAT_PRODU',
			   1,
			   1,
			   'DESCRIPCION PENDIENTE',
			   lnNumeroSeq);

    END IF;

    SELECT COUNT(M.OID_UNID_MEDI) INTO lnNumeroRegistros
      FROM MAE_UNIDA_MEDID M
     WHERE M.COD_UNID_MEDI = nvl(pscodigounimedvolumen,'XXXX');

    IF(lnNumeroRegistros = 0 and pscodigounimedvolumen is not null)THEN

      INSERT INTO MAE_UNIDA_MEDID
        (OID_UNID_MEDI,
         PAIS_OID_PAIS,
         COD_UNID_MEDI,
         MAGN_OID_MAGN,
         IND_UM_STND)
      VALUES
        (MAE_UNMD_SEQ.NEXTVAL, lnOidPais, pscodigounimedvolumen, 2002, 0);

      SELECT MAX(MN.OID_UNID_MEDI) INTO lnNumeroSeq
      FROM MAE_UNIDA_MEDID MN;

      INSERT INTO GEN_I18N_SICC_PAIS
			  (OID_I18N, ATTR_ENTI, ATTR_NUM_ATRI, IDIO_OID_IDIO, VAL_I18N, VAL_OID)
			VALUES
			  (GEN_I18N_SEQ.NEXTVAL,
			   'MAE_UNIDA_MEDID',
			   1,
			   1,
			   'DESCRIPCION PENDIENTE',
			   lnNumeroSeq);

    END IF;

    SELECT COUNT(M.OID_UNID_MEDI) INTO lnNumeroRegistros
      FROM MAE_UNIDA_MEDID M
     WHERE M.COD_UNID_MEDI = nvl(pscodigounimedpeso,'XXXX');

    IF(lnNumeroRegistros = 0 and pscodigounimedpeso is not null)THEN

      INSERT INTO MAE_UNIDA_MEDID
        (OID_UNID_MEDI,
         PAIS_OID_PAIS,
         COD_UNID_MEDI,
         MAGN_OID_MAGN,
         IND_UM_STND)
      VALUES
        (MAE_UNMD_SEQ.NEXTVAL, lnOidPais, pscodigounimedpeso, 2003, 0);

      SELECT MAX(MN.OID_UNID_MEDI) INTO lnNumeroSeq
      FROM MAE_UNIDA_MEDID MN;

      INSERT INTO GEN_I18N_SICC_PAIS
			  (OID_I18N, ATTR_ENTI, ATTR_NUM_ATRI, IDIO_OID_IDIO, VAL_I18N, VAL_OID)
			VALUES
			  (GEN_I18N_SEQ.NEXTVAL,
			   'MAE_UNIDA_MEDID',
			   1,
			   1,
			   'DESCRIPCION PENDIENTE',
			   lnNumeroSeq);

    END IF;

    SELECT COUNT(M.OID_MARC_PROD) INTO lnNumeroRegistros
      FROM SEG_MARCA_PRODU M
     WHERE M.COD_MARC_PROD = nvl(pscodigomarca,'XXXX');

    IF(lnNumeroRegistros = 0 and pscodigomarca is not null)THEN

      INSERT INTO SEG_MARCA_PRODU
        (OID_MARC_PROD, COD_MARC_PROD, DES_MARC_PROD)
      VALUES
        (SEG_MAPR_SEQ.NEXTVAL, pscodigomarca, 'DESCRIPCION PENDIENTE');

    END IF;

    SELECT COUNT(M.Oid_Prod) INTO lnNumeroRegistros
      FROM MAE_PRODU M
     WHERE M.Cod_Sap = pscodigoproducto;

    IF(lnNumeroRegistros > 0)THEN

      UPDATE MAE_PRODU
         SET CODI_ANTI               = pscodigoantiguo,
             VAL_DIME_ALTO           = TO_NUMBER(psalto,'9999999.99'),
             VAL_DIME_LARG           = TO_NUMBER(pslargo,'9999999.99'),
             VAL_DIME_ANCH           = TO_NUMBER(psancho,'9999999.99'),
             VAL_PESO                = TO_NUMBER(pspesobruto,'9999999.99'),
             VAL_VOLU                = TO_NUMBER(psvolumen,'9999999.99'),
             VAL_COST_ESTD           = TO_NUMBER(pscosteestandar,'9999999.99'),
             GENE_OID_GENE           = decode(pscodigogenerico,NULL,NULL,
                                           (SELECT OID_GENE
                                              FROM MAE_GENER
                                             WHERE COD_GENE = pscodigogenerico)),
             SGEN_OID_SUPE_GENE      = decode(pscodigosupergenerico,NULL, NULL,
                                           (SELECT OID_SUPE_GENE
                                              FROM MAE_SUPER_GENER
                                             WHERE COD_SUPE_GENE = pscodigosupergenerico)),
             UNMD_OID_UNID_MEDI      = decode(pscodigounimedvolumen,NULL,NULL,
                                           (SELECT OID_UNID_MEDI
                                              FROM MAE_UNIDA_MEDID
                                             WHERE COD_UNID_MEDI = pscodigounimedvolumen)),
             UNMD_OID_UNID_MEDI_PESO = decode(pscodigounimedpeso,NULL, NULL,
                                           (SELECT OID_UNID_MEDI
                                              FROM MAE_UNIDA_MEDID
                                             WHERE COD_UNID_MEDI = pscodigounimedpeso)),
             NEGO_OID_NEGO           = decode(pscodigonegocio,NULL, NULL,
                                           (SELECT OID_NEGO
                                              FROM MAE_NEGOC
                                             WHERE COD_NEGO = pscodigonegocio)),
             MEUD_OID_ESTA_PROD      = decode(pscodigoestatus,NULL, NULL,
                                           (SELECT OID_ESTA_PROD
                                              FROM MAE_ESTAT_PRODU
                                             WHERE COD_ESTA_PROD = pscodigoestatus)),
             UNEG_OID_UNID_NEGO      = decode(pscodigounidadnegocio,NULL, NULL,
                                           (SELECT OID_UNID_NEGO
                                              FROM MAE_UNIDA_NEGOC
                                             WHERE COD_UNID_NEGO = pscodigounidadnegocio)),
             IND_PROD_SERV           = decode(psindicadorProServ,'P',0,1),
             COD_IND_DENT_CAJA       = NVL(psindicadorFueraCaja, COD_IND_DENT_CAJA),
             MAPR_OID_MARC_PROD      = decode(pscodigomarca,NULL, NULL,
                                           (SELECT OID_MARC_PROD
                                              FROM SEG_MARCA_PRODU
                                             WHERE COD_MARC_PROD = pscodigomarca)),
             VAL_GRUP_ARTI           = psgrupoarticulo,
             COD_UNIC_VENT           = pscuv
       WHERE COD_SAP = pscodigoproducto;

       update gen_i18n_sicc_pais set val_i18n=psdescripcion
       where attr_enti='MAE_PRODU' and val_oid=(select oid_prod from mae_produ where cod_sap=pscodigoproducto);

    ELSE

      INSERT INTO MAE_PRODU
        (OID_PROD,
         PAIS_OID_PAIS,
         COD_SAP,
         CODI_ANTI,
         DES_CORT,
         VAL_JERA_1,
         VAL_JERA_2,
         VAL_JERA_3,
         VAL_ATRI_1,
         VAL_ATRI_2,
         VAL_ATRI_3,
         VAL_DIME_ALTO,
         VAL_DIME_LARG,
         VAL_DIME_ANCH,
         VAL_PESO,
         VAL_VOLU,
         VAL_COST_ESTD,
         VAL_PREC_POSI,
         VAL_PREC_CATA,
         VAL_PREC_CONT,
         NUM_UNID_DENT_PEDI,
         NUM_UNID_CAJA,
         COD_IMPU,
         PCT_UNID,
         GENE_OID_GENE,
         SGEN_OID_SUPE_GENE,
         UNMD_OID_UNID_MEDI,
         UNMD_OID_UNID_MEDI_PESO,
         UNMD_OID_UNID_MEDI_DIME,
         NEGO_OID_NEGO,
         MEUD_OID_ESTA_PROD,
         LIPR_OID_LINE_PROD,
         UNEG_OID_UNID_NEGO,
         PERD_OID_PERI_INIC,
         PERD_OID_PERI_FIN,
         IND_LOTE,
         IND_PROD_SERV,
         COD_IND_SITU,
         COD_IND_DENT_CAJA,
         IND_KIT,
         FOPA_OID_FORM_PAGO,
         MAPR_OID_MARC_PROD,
         VAL_GRUP_ARTI,
         FEC_ULTI_ACTU,
         COD_UNIC_VENT,
         PRFI_OID_PROG_FIDE,
         TICP_OID_TIPO_CAJA_PROD,
         NUM_UNID_CAJA_PROD,
         NUM_CAPA_OPER)
      VALUES
        (MAE_PROD_SEQ.NEXTVAL,
         lnOidPais,
         pscodigoproducto,
         pscodigoantiguo,
         NULL,
         NULL,
         NULL,
         NULL,
         NULL,
         NULL,
         NULL,
         TO_NUMBER(psalto,'9999999.99'),
         TO_NUMBER(pslargo,'9999999.99'),
         TO_NUMBER(psancho,'9999999.99'),
         TO_NUMBER(pspesobruto,'9999999.99'),
         TO_NUMBER(psvolumen,'9999999.99'),
         TO_NUMBER(pscosteestandar,'9999999.99'),
         NULL,
         NULL,
         NULL,
         NULL,
         NULL,
         NULL,
         NULL,
         (SELECT OID_GENE FROM MAE_GENER WHERE COD_GENE = pscodigogenerico),
         (SELECT OID_SUPE_GENE
            FROM MAE_SUPER_GENER
           WHERE COD_SUPE_GENE = pscodigosupergenerico),
         (SELECT OID_UNID_MEDI
            FROM MAE_UNIDA_MEDID
           WHERE COD_UNID_MEDI = pscodigounimedpeso),
         (SELECT OID_UNID_MEDI
            FROM MAE_UNIDA_MEDID
           WHERE COD_UNID_MEDI = pscodigounimedvolumen),
         NULL,
         (SELECT OID_NEGO FROM MAE_NEGOC WHERE COD_NEGO = pscodigonegocio),
         (SELECT OID_ESTA_PROD
            FROM MAE_ESTAT_PRODU
           WHERE COD_ESTA_PROD = pscodigoestatus),
         NULL,
         (SELECT OID_UNID_NEGO
            FROM MAE_UNIDA_NEGOC
           WHERE COD_UNID_NEGO = pscodigounidadnegocio),
         NULL,
         NULL,
         NULL,
         decode(psindicadorProServ,'P',0,1),
         NULL,
         psindicadorFueraCaja,
         NULL,
         NULL,
         (SELECT OID_MARC_PROD
            FROM SEG_MARCA_PRODU
           WHERE COD_MARC_PROD = pscodigomarca),
         psgrupoarticulo,
         SYSDATE,
         pscuv,
         NULL,
         NULL,
         NULL,
         NULL);

insert into gen_i18n_sicc_pais values (gen_i18n_seq.nextval,'MAE_PRODU',1,1,psdescripcion,mae_prod_seq.currval);

    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm,1,250);
      RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_SAP_RECEP_PRODU: ' || pscodigoproducto || ls_sqlerrm);

  END INT_PR_SAP_RECEP_PRODU;

  /**************************************************************************
  Descripcion        : Devuelve el Tipo de Movimiento para la SAM7
  Fecha Creacion     : 18/09/2013
  Autor              : Jorge Yepez
  ***************************************************************************/
  FUNCTION ped_fn_devue_tip_movi_sam
  (
    pnoidmodu NUMBER,
    pnindoc   NUMBER,
    pscodigpais VARCHAR2
  ) RETURN VARCHAR IS

  ls_temp VARCHAR2(3):='03';
  lsnuevoindicador VARCHAR2(5):=nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigpais,'STO_MOVI_SAM'),'N');

  BEGIN

   if lsnuevoindicador='N' then
      return ls_temp;
   else
      if pnindoc=1 then
         ls_temp:='01';
      elsif (pnoidmodu=15 or (pnoidmodu=1 and pnindoc=0))then ls_temp:='02';
      elsif pnoidmodu=13 then ls_temp:='03';
      elsif pnoidmodu=9 then ls_temp:='04';
      else ls_temp:='01';
      end if;
      return ls_temp;
   end if;


  EXCEPTION

    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR ped_fn_devue_tip_movi_sam: ' || ls_sqlerrm);

  END ped_fn_devue_tip_movi_sam;

END INT_PKG_SAPMM;
/
