CREATE OR REPLACE PACKAGE "CCC_PKG_REPOR_PROCE" is

   gv_log_cod_modu                 fin_modul.cod_modu%TYPE;
   gv_log_cod_proc                  fin_proce_modul.cod_proc%TYPE;

   gv_cod_proc_ejec                NUMBER(12);
   gv_log_cod_pais                   own_comun.seg_pais.cod_pais%TYPE;
   gv_log_cod_soci                   seg_socie.cod_soci%TYPE;
   gv_des_log                            VARCHAR2(2500);
   gv_log_user                           fin_proce_ejecu.usu_proc%TYPE;

  /* Declaracion de variables */
  ln_sqlcode           NUMBER(10);
  ls_sqlerrm           VARCHAR2(1500);
  w_filas                NUMBER := 5000;

 PROCEDURE CCC_PR_DETAL_PROV_INCOBR_MOV(
    psTitulo                     IN  VARCHAR2,
    psCodigoPais                 IN    VARCHAR2,
    psNombreArchivo              IN    VARCHAR2,
    psDirectorio                   OUT  VARCHAR2,
    psFechaInicio                IN     VARCHAR2,
    psFechaFin                   IN       VARCHAR2);

  PROCEDURE CCC_PR_DETAL_CONSUL_INCOBR(
    psTitulo                     IN      VARCHAR2,
    psCodigoPais                 IN      VARCHAR2,
    psNombreArchivo              IN      VARCHAR2,
    psDirectorio                 OUT      VARCHAR2,
    psCampanaHasta               IN      VARCHAR2,
    psFechaHasta                 IN      VARCHAR2,
    psRegion                     IN      VARCHAR2,
    psZona                       IN      VARCHAR2,
    psSeccions                   IN      VARCHAR2);

  PROCEDURE CCC_PR_DET_PROV_INCOBR_CONSLT(
    psTitulo                     IN  VARCHAR2,
    psCodigoPais                 IN    VARCHAR2,
    psNombreArchivo              IN    VARCHAR2,
    psDirectorio                   OUT  VARCHAR2,
    psFechaInicio                  IN   VARCHAR2,
    psFechaFin                     IN     VARCHAR2);

 PROCEDURE CCC_PR_DETAL_PREVI_CONSU_INCOB(
  p_fec_hast                     IN    VARCHAR2);

 PROCEDURE CCC_PR_DETAL_PREVI_MOVIM_INCOB(
  p_fec_hast                     IN    VARCHAR2,
  p_ind_proc_inco                IN    NUMBER DEFAULT 0);

 PROCEDURE CCC_PR_REPOR_MEDIO_MAGNE_BIMEN(
  p_fec_inic                     IN   VARCHAR2,
  p_fec_fina                     IN   VARCHAR2);

 PROCEDURE CCC_PR_REPOR_ANTIG_SALDO(
  p_fec_cort                     IN   VARCHAR2,
  p_cod_usua                     IN   VARCHAR2);

 PROCEDURE CCC_PR_REPOR_ANTIG_SALDO_CSV(
    pscodigopais          VARCHAR2,
    psnombrearchivo       VARCHAR2,
    pstitulo              VARCHAR2,
    psdirectorio          OUT VARCHAR2);

 PROCEDURE CCC_PR_GENER_CUADR_SAPFI(
  p_fec_inic                       IN   VARCHAR2,
  p_fec_fina                       IN   VARCHAR2);

 PROCEDURE CCC_PR_REPOR_SALDO_PENDI_CSV;

 PROCEDURE CCC_PR_GENER_CORRI_CONSU(
  p_cod_pais                   IN       seg_pais.cod_pais%TYPE,
  p_cod_soci                   IN       seg_socie.cod_soci%TYPE,
  p_cod_regi                   IN       zon_regio.cod_regi%TYPE);

   /**************************************************************************
     Descripcion       : Genera la data para el reporte de Buro de Credito
     Fecha Creacion    : 14/03/2012
     Autor             : Jose Luis Rodriguez
   ***************************************************************************/
  PROCEDURE CCC_PR_GENER_BURO_CREDI(
      p_cod_pais  IN  seg_pais.cod_pais%TYPE);

   /**************************************************************************
     Descripcion       : Genera la data para el reporte de Ventas Detalles SII
     Fecha Creacion    : 03/07/2012
     Autor             : Giovanni Ascarza
   ***************************************************************************/
  PROCEDURE CCC_PR_VENTAS_DETALLE_SII(psCodigoPais       IN  VARCHAR2,
                                      psFechaDesde       IN  VARCHAR2,
                                      psFechaHasta       IN  VARCHAR2,
                                      psAccion           IN  VARCHAR2,
                                      psDirectorio       OUT VARCHAR2,
                                      psNota1            OUT VARCHAR2,
                                      psNota2            OUT VARCHAR2,
                                      psBoleta1          OUT VARCHAR2,
                                      psBoleta2          OUT VARCHAR2);

 /**************************************************************************
     Descripcion       : Genera data para reporte de Primeros Pedidos Deudores
     Fecha Creacion    : 23/04/2012
     Autor             : EL
   ***************************************************************************/
 PROCEDURE CCC_PR_GENER_PRIME_PEDID_DEUDO(
  p_cod_peri_inic                IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_peri_fina                IN   seg_perio_corpo.cod_peri%TYPE,
  p_oid_ejec_repo                OUT  NUMBER);


 /**************************************************************************
     Descripcion       : Genera data para reporte de Primeros Y Segundos
                           Pedidos Deudores
     Fecha Creacion    : 23/04/2012
     Autor             : EL
   ***************************************************************************/
 PROCEDURE CCC_PR_GENER_SEGUN_PEDID_DEUDO (
  p_cod_peri_inic                  IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_peri_fina                  IN   seg_perio_corpo.cod_peri%TYPE,
  p_oid_ejec_repo                  OUT  NUMBER);



 /**************************************************************************
     Descripcion       : Genera data para reporte de Saldos 6 Campa?as
     Fecha Creacion    : 25/04/2012
     Autor             : EL
   ***************************************************************************/
  PROCEDURE CCC_PR_GENER_SALDO_CAMPA_5ANTE (
     psCodigoPais             IN seg_pais.cod_pais%TYPE,
     psCodigoSoci             IN seg_socie.cod_soci%TYPE,
     psCodigoPeriodoFinal     IN seg_perio_corpo.cod_peri%TYPE,
     psOidEjecuRepor          OUT NUMBER,
     psCodigoPeriodo01        OUT seg_perio_corpo.cod_peri%TYPE,
     psCodigoPeriodo02        OUT seg_perio_corpo.cod_peri%TYPE,
     psCodigoPeriodo03        OUT seg_perio_corpo.cod_peri%TYPE,
     psCodigoPeriodo04        OUT seg_perio_corpo.cod_peri%TYPE,
     psCodigoPeriodo05        OUT seg_perio_corpo.cod_peri%TYPE,
     psCodigoPeriodo06        OUT seg_perio_corpo.cod_peri%TYPE
           );




  PROCEDURE CCC_PR_GENER_DIAS_CARTE(
      p_cod_pais          seg_pais.cod_pais%TYPE,
      p_cod_socie         seg_socie.cod_soci%TYPE,
      p_fec_inicial       VARCHAR2,
      p_fec_final         VARCHAR2,
      p_oid_ejecu_repor   OUT NUMBER
 );

  /**************************************************************************
     Descripcion       : Genera data para reporte de resumen de abono
     Fecha Creacion    : 20/09/2013
     Autor             : Gonzalo Javier Huertas Agurto
   ***************************************************************************/
 PROCEDURE CCC_PR_GENER_RESUM_ABONO (
     psFechaDesde             varchar2,
     psFechaHasta             varchar2,
     psOidTipoDocumento       varchar2,
     psTipoOidTipoDocumento01 varchar2,
     psTipoOidTipoDocumento02 varchar2,
     psTipoOidTipoDocumento03 varchar2
     );


   /**************************************************************************
     Descripcion       :
     Fecha Creacion    : 03/12/2013
     Autor             : Rosalvina Ramirez Guardia
   ***************************************************************************/
 PROCEDURE CCC_PR_GENER_SALDO_DIARI;

/*************************************************************************************
Descripcion       : Procedimiento que carga el Reporte Liquidacion Cobranza para formato CSV
Fecha Creacion    : 27/01/2014
Autor             : Carlos Bazalar
************************************************************************************/
PROCEDURE ccc_pr_liqui_cobra_csv(
    pscodigopais          VARCHAR2,
    psfechaPagoDesde      VARCHAR2,
    psfechaPagoHasta      VARCHAR2,
    psfechaProcDesde      VARCHAR2,
    psfechaProcHasta      VARCHAR2,
    pscodigoProceso       VARCHAR2,
    pscodigoSubproceso    VARCHAR2,
    pscodigoBanco         VARCHAR2,
    pstipoNombreReporte   VARCHAR2,
    psnombrearchivo       VARCHAR2,
    pstitulo              VARCHAR2,
    psdirectorio          OUT VARCHAR2
);

 /************************************************************************************
  Descripcion           : Procedimiento que genera los archivos de Medios Magneticos
                                 Facturas
                                 Notas de Credito (Postventas)
                                 Cargos por Boletas no Exitosas
                                 Reversion de PostVentas
                                 Cargos y Abonos Directos

  Fecha Creacion    : 20/02/2014
  Autor                    : Sebastian Guerra
 ************************************************************************************/
  PROCEDURE CCC_PR_GENER_REPOR_MEDIO_MAGNE(
      pscodigopais            VARCHAR2,
      pscodigousuario       VARCHAR2,
      psdirectorio      OUT VARCHAR2,
      psArchivos       OUT VARCHAR2
  );

 /************************************************************************************
  Descripcion           : Procedimiento que limpia las tablas temporales de Medios Magneticos
                                 Facturas
                                 Notas de Credito (Postventas)
                                 Cargos por Boletas no Exitosas
                                 Reversion de PostVentas
                                 Cargos y Abonos Directos

  Fecha Creacion    : 21/02/2014
  Autor                    : Sebastian Guerra
 ************************************************************************************/
  PROCEDURE CCC_PR_CLEAR_TABLA_MEDIO_MAGNE(
      pscodigousuario       VARCHAR2
  );

/************************************************************************************
  Descripcion           : Inserta en tabla temporal la generacion del Reporte de Medios Magneticos
                                 Facturas
                                 Notas de Credito (Postventas)
                                 Cargos por Boletas no Exitosas
                                 Reversion de PostVentas
                                 Cargos y Abonos Directos

  Fecha Creacion    : 20/02/2014
  Autor                    : Sebastian Guerra
 ************************************************************************************/
  PROCEDURE CCC_PR_CARGA_REPOR_MEDIO_MAGNE(
      pscodigousuario       VARCHAR2,
      psfechadesde          VARCHAR2,
      psfechahasta           VARCHAR2
  );

/*************************************************************************************
Descripcion       : Procedimiento que carga el Reporte Detalle Cuenta Corriente para formato CSV
Fecha Creacion    : 26/02/2014
Autor             : Carlos Bazalar
************************************************************************************/
PROCEDURE CCC_PR_GENER_REPOR_DETAL_CTACT(
    pscodigopais          VARCHAR2,
    psFechaHasta          VARCHAR2,
    psCodigoSociedad      VARCHAR2,
    pstipoNombreReporte   VARCHAR2,
    psnombrearchivo       VARCHAR2,
    pstitulo              VARCHAR2,
    psdirectorio          OUT VARCHAR2
);

/*************************************************************************************
Descripcion       : Procedimiento que carga el Reporte Detallado Pagos x Regularizar
                    para formato CSV
Fecha Creacion    : 04/03/2014
Autor             : Carlos Bazalar
************************************************************************************/
PROCEDURE CCC_PR_REPOR_DETAL_PAGO_REGUL(
    pscodigopais          VARCHAR2,
    psCodigoBanco         VARCHAR2,
    psfechaPagoDesde      VARCHAR2,
    psfechaPagoHasta      VARCHAR2,
    psfechaProcDesde      VARCHAR2,
    psfechaProcHasta      VARCHAR2,
    psnombrearchivo       VARCHAR2,
    pstitulo              VARCHAR2,
    psdirectorio          OUT VARCHAR2
);

/*************************************************************************************
Descripcion       : Procedimiento que carga el Reporte Auditoria Saldo Cuentas x Cobrar
                    para formato CSV
Fecha Creacion    : 14/04/2014
Autor             : Carlos Bazalar
************************************************************************************/
PROCEDURE CCC_PR_REPOR_AUDI_SALDO_CXCOB(
    pscodigopais          VARCHAR2,
    psNombreReporte       VARCHAR2,
    psnombrearchivo       VARCHAR2,
    pstitulo              VARCHAR2,
    psdirectorio          OUT VARCHAR2
);

 PROCEDURE CCC_PR_REPOR_CONTA_SALDO_CAMPA(
  p_fec_cort                     IN   VARCHAR2);

 PROCEDURE CCC_PR_GENER_REPOR_CIERR_CONTA(
  p_fec_cort                     IN   DATE);

  /*************************************************************************************
  Descripcion       : Procedimiento que carga el Reporte Detallado IFC
                    para formato CSV
  Fecha Creacion    : 16/04/2014
  Autor             : Frank J Gonzales
  ************************************************************************************/
  PROCEDURE CCC_PR_GENER_REPOR_DETAL_IFC(
      psfechaCorte          VARCHAR2,
      pscodigopais          VARCHAR2,
      psnombrearchivo       VARCHAR2,
      pstitulo              VARCHAR2,
      psdirectorio          OUT VARCHAR2
    );

/*************************************************************************************
Descripcion       : Procedimiento que carga el Reporte Gasto Cupon para formato CSV
Fecha Creacion    : 15/05/2014
Autor             : Aurelio Oviedo
************************************************************************************/
PROCEDURE CCC_PR_REPOR_GASTO_CUPON(
    pscodigopais          VARCHAR2,
    psTipoReporte         VARCHAR2,
    psfechaDesde      VARCHAR2,
    psfechaHasta      VARCHAR2,
    psnombrearchivo       VARCHAR2,
    pstitulo              VARCHAR2,
    psdirectorio          OUT VARCHAR2
);

 /************************************************************************************
  Descripcion           : Procedimiento que limpia las tablas temporales de Medios Magneticos
                          Bimensual
  Fecha Creacion        : 24/10/2014
  Autor                    : Diego Torres
 ************************************************************************************/
PROCEDURE COB_PR_REPOR_MEDIO_MAGNE_BIMEP(
     psfechaCorte      VARCHAR2
);

  /*************************************************************************************
  Descripcion       : Procedimiento que carga el Reporte Medios Magneticos Bimensual para formato CSV
  Fecha Creacion    : 24/10/2014
  Autor             : Diego Torres
  ************************************************************************************/
PROCEDURE CCC_PR_GEN_REPOR_MED_MAG_BIMEN(
    pscodigopais          VARCHAR2,
    psnombrearchivo       VARCHAR2,
    pstitulo              VARCHAR2,
    psdirectorio          OUT VARCHAR2
);

 PROCEDURE CCC_PR_GENER_RECAU_CAMPA(
  p_fec_desd                     IN   VARCHAR2,
  p_fec_hast                     IN   VARCHAR2);

 PROCEDURE CCC_PR_REPOR_ANTIG_NOTAS_CREDI(
  p_fec_desd                     IN   VARCHAR2,
  p_fec_hast                     IN   VARCHAR2);

 PROCEDURE CCC_PR_DETAL_CUENT_CORRI_CONTA(
  p_fec_hast                     IN   VARCHAR2);

  /*************************************************************************************
  Descripcion       : Procedimiento que carga el Reporte Registro Ventas Bolivia para formato CSV
  Fecha Creacion    : 02/03/2015
  Autor             : Aurelio Oviedo
  ************************************************************************************/
PROCEDURE CCC_PR_GEN_REPOR_REG_VENT_BOL(
    pscodigopais          VARCHAR2,
    psfechainicio         VARCHAR2,
    psfechafin            VARCHAR2,
    psnombrearchivo       VARCHAR2,
    pstitulo              VARCHAR2,
    psdirectorio          OUT VARCHAR2
);

  /*************************************************************************************
  Descripcion       : Procedimiento que carga el Reporte Registro Abonos Bolivia para formato CSV
  Fecha Creacion    : 03/03/2015
  Autor             : Aurelio Oviedo
  ************************************************************************************/
PROCEDURE CCC_PR_GEN_REPOR_REG_ABON_BOL(
    pscodigopais          VARCHAR2,
    psfechainicio         VARCHAR2,
    psfechafin            VARCHAR2,
    psnombrearchivo       VARCHAR2,
    pstitulo              VARCHAR2,
    psdirectorio          OUT VARCHAR2
);

  /*************************************************************************************
  Descripcion       : Procedimiento que carga el Reporte Buro Credito en formato CSV
  Fecha Creacion    : 12/03/2015
  Autor             : Diego Torres Loyola
  ************************************************************************************/
PROCEDURE CCC_PR_BURO_CREDITO(
    psTitulo                     IN      VARCHAR2,
    psCodigoPais                 IN      VARCHAR2,
    psNombreArchivo              IN      VARCHAR2,
    psDirectorio                 OUT      VARCHAR2);
    
  /*************************************************************************************
  Descripcion       : Procedimiento que carga lso tres tipos de Reporte Interes CSV 
  Fecha Creacion    : 02/12/2015
  Autor             : Karina Valencia
  ************************************************************************************/
PROCEDURE CCC_PR_INTER_FACTU_TOTAL(
    psTitulo                     IN      VARCHAR2,
    psCodigoPais                 IN      VARCHAR2,
    psNombreArchivo              IN      VARCHAR2,
    psTipoReporte                IN      VARCHAR2,    
    psDirectorio                 OUT      VARCHAR2);
    
/*************************************************************************************
  Descripcion       : Procedimiento que crear los tres tipos de Reporte Interes 
  Fecha Creacion    : 03/12/2015
  Autor             : Karina Valencia
  ************************************************************************************/
PROCEDURE CCC_PR_GENER_INTER_TOTAL(   
    psTipoReporte                IN      VARCHAR2,
    psCondRegion                 IN      VARCHAR2,
    psCondZona                   IN      VARCHAR2,
    psCondCliente                IN      VARCHAR2,
    psfechaInicio                IN      VARCHAR2,
    psFechaFin                   IN      VARCHAR2
    );

END CCC_PKG_REPOR_PROCE;
/
CREATE OR REPLACE PACKAGE BODY "CCC_PKG_REPOR_PROCE" IS

 gc_cod_modu                       CONSTANT CHAR(6):= 'CCCREP';

 PROCEDURE CCC_PR_DETAL_PREVI_CONSU_INCOB(
  p_fec_hast                     IN    VARCHAR2)
 IS

  lv_ind_pago_banc               ccc_param_gener.val_para%TYPE;

 BEGIN

  lv_ind_pago_banc := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('IndicadorPagoBancarioCastigoIncobrable');

  DELETE FROM ccc_repor_previ_consu_incob;

  INSERT INTO ccc_repor_previ_consu_incob
   WITH
    temp1 AS
     (SELECT
       mcc.clie_oid_clie,
       MIN(spc.cod_peri) cod_peri_mini,
       MAX(spc.cod_peri) cod_peri_maxi,
       MIN(mcc.fec_docu) fec_deud_mini,
       MAX(mcc.fec_docu) fec_deud_maxi,
       SUM(mcc.imp_pend) imp_deud_pend
      FROM
       ccc_movim_cuent_corri mcc,
       cra_perio cp,
       seg_perio_corpo spc
      WHERE mcc.perd_oid_peri = cp.oid_peri
        AND cp.peri_oid_peri = spc.oid_peri
        AND mcc.imp_pend <> 0
        AND mcc.fec_docu <= TO_DATE(p_fec_hast,'DD/MM/YYYY')
        AND NOT EXISTS
        (SELECT NULL
         FROM ccc_clien_casti c
         WHERE c.oid_clie= mcc.clie_oid_clie)
        AND NOT EXISTS
        (SELECT NULL
         FROM ccc_consu_casti_cabec cc
         WHERE cc.oid_clie= mcc.clie_oid_clie)
      HAVING SUM(mcc.imp_pend) > 0
      GROUP BY mcc.clie_oid_clie),
    temp2 AS
       (SELECT
         mc.oid_clie,
         mc.cod_clie,
         mc.val_nom1 || ' ' || mc.val_nom2 || ' ' || mc.val_ape1 || ' ' || mc.val_ape2 nom_clie,
         NULL,
         t1.cod_peri_mini,
         t1.cod_peri_maxi,
         t1.fec_deud_mini,
         t1.fec_deud_maxi,
         t1.imp_deud_pend
        FROM
         temp1 t1,
         mae_clien mc
        WHERE mc.oid_clie = t1.clie_oid_clie
          AND mc.sal_deud_ante = t1.imp_deud_pend)
   SELECT
    h.cod_regi,
    h.cod_zona,
    h.cod_secc,
    t2.oid_clie,
    t2.cod_clie,
    t2.nom_clie,
    h.num_docu_iden,
    t2.cod_peri_mini,
    t2.cod_peri_maxi,
    t2.fec_deud_mini,
    t2.fec_deud_maxi,
    t2.imp_deud_pend,
    NULL
   FROM
    temp2 t2,
    int_reu_consu_histo h
   WHERE t2.cod_clie = h.cod_clie(+)
   order BY imp_deud_pend DESC;

  IF lv_ind_pago_banc = 'S' THEN

   UPDATE ccc_repor_previ_consu_incob pi
   SET pi.ind_tipo_excl = 2
   WHERE EXISTS (
    SELECT NULL
    FROM ccc_movim_banca mb
    WHERE mb.clie_oid_clie = pi.oid_clie
    AND mb.cod_iden_proc = 'P'
    AND mb.fec_proc >= TRUNC(SYSDATE) - 365);

  END IF;

 END CCC_PR_DETAL_PREVI_CONSU_INCOB;

 PROCEDURE CCC_PR_DETAL_PREVI_MOVIM_INCOB(
  p_fec_hast                     IN    VARCHAR2,
  p_ind_proc_inco                IN    NUMBER DEFAULT 0)
 IS

  lv_ind_pago_banc               ccc_param_gener.val_para%TYPE;
  lv_oid_tipo_soli               ped_tipo_solic_pais.oid_tipo_soli_pais%TYPE;

 BEGIN

  lv_ind_pago_banc := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('IndicadorPagoBancarioCastigoIncobrable');

  DELETE FROM ccc_repor_previ_movim_incob;

  INSERT INTO ccc_repor_previ_movim_incob
   WITH
    base_inic AS
     (SELECT
       mcc.clie_oid_clie,
       SUM(mcc.imp_pend) imp_pend
      FROM
       ccc_movim_cuent_corri mcc,
       cra_perio cp,
       seg_perio_corpo spc
      WHERE mcc.perd_oid_peri = cp.oid_peri
        AND cp.peri_oid_peri = spc.oid_peri
        AND mcc.imp_pend <> 0
        AND mcc.fec_docu <= TO_DATE(p_fec_hast,'DD/MM/YYYY')
        AND NOT EXISTS
        (SELECT NULL
         FROM ccc_clien_casti c
         WHERE c.oid_clie= mcc.clie_oid_clie)
         AND NOT EXISTS
        (SELECT NULL
         FROM ccc_consu_casti_cabec cc
         WHERE cc.oid_clie= mcc.clie_oid_clie)
      HAVING SUM(mcc.imp_pend) > 0
      GROUP BY mcc.clie_oid_clie),
    base_fina AS
       (SELECT
         mc.oid_clie,
         mc.cod_clie,
         mc.val_nom1 || ' ' || mc.val_nom2 || ' ' || mc.val_ape1 || ' ' || mc.val_ape2 nom_clie
        FROM
         base_inic b1,
         mae_clien mc
        WHERE mc.oid_clie = b1.clie_oid_clie
          AND mc.sal_deud_ante = b1.imp_pend),
    temp1 AS
     (SELECT
       bf.oid_clie,
       bf.cod_clie,
       bf.nom_clie,
       spc.cod_peri,
       su.des_subp val_tipo_carg,
       mcc.fec_docu fec_carg,
       mcc.oid_movi_cc num_iden_carg,
       mcc.imp_movi,
       mcc.imp_pend,
       TRUNC(SYSDATE) - mcc.fec_docu num_dias_atra,
       NULL
      FROM
       ccc_movim_cuent_corri mcc,
       ccc_subpr su,
       cra_perio cp,
       seg_perio_corpo spc,
       base_fina bf
      WHERE mcc.clie_oid_clie = bf.oid_clie
        AND mcc.subp_oid_subp_crea = su.oid_subp
        AND mcc.perd_oid_peri = cp.oid_peri
        AND cp.peri_oid_peri = spc.oid_peri
        AND mcc.imp_pend > 0
        AND mcc.fec_docu <= TO_DATE(p_fec_hast,'DD/MM/YYYY'))
   SELECT
    h.cod_regi,
    h.cod_zona,
    h.cod_secc,
    t1.oid_clie,
    t1.cod_clie,
    t1.nom_clie,
    h.num_docu_iden,
    NULL,
    NULL,
    NULL,
    t1.cod_peri,
    t1.val_tipo_carg,
    t1.fec_carg,
    t1.num_iden_carg,
    t1.imp_movi,
    t1.imp_pend,
    t1.num_dias_atra,
    NULL,
    NULL,
    NULL
   FROM
    temp1 t1,
    int_reu_consu_histo h
   WHERE t1.cod_clie = h.cod_clie(+)
   ORDER BY fec_carg DESC;

  IF lv_ind_pago_banc = 'S' THEN

   UPDATE ccc_repor_previ_movim_incob c
   SET c.ind_tipo_excl = 2
   WHERE EXISTS (
    SELECT NULL
    FROM ccc_movim_banca mb
    WHERE mb.clie_oid_clie = c.oid_clie
    AND mb.cod_iden_proc = 'P'
    AND mb.fec_proc >= TRUNC(SYSDATE) - 365);

  END IF;

  IF p_ind_proc_inco = 0 THEN

   lv_oid_tipo_soli := FIN_PKG_GENER.FIN_FN_OBTIE_OID_SOLIC_PAIS('SOC');

   UPDATE ccc_repor_previ_movim_incob a
   SET a.cod_camp_prim_pedi =
        (SELECT MIN(spc.cod_peri)
         FROM
          ped_solic_cabec psc,
          cra_perio cp,
          seg_perio_corpo spc
         WHERE psc.perd_oid_peri = cp.oid_peri
           AND cp.peri_oid_peri = spc.oid_peri
           AND clie_oid_clie= a.oid_clie
           AND tspa_oid_tipo_soli_pais = lv_oid_tipo_soli),
      a.cod_camp_ulti_pedi =
         (SELECT MAX(spc.cod_peri)
         FROM
          ped_solic_cabec psc,
          cra_perio cp,
          seg_perio_corpo spc
         WHERE psc.perd_oid_peri = cp.oid_peri
           AND cp.peri_oid_peri = spc.oid_peri
           AND clie_oid_clie= a.oid_clie
           AND tspa_oid_tipo_soli_pais = lv_oid_tipo_soli),
       a.val_desc_esta_cons =
        ( SELECT gen.val_i18n
          FROM mae_clien_datos_adici mcda,
               gen_i18n_sicc_comun gen
          WHERE mcda.clie_oid_clie = a.oid_clie
          AND mcda.esta_oid_esta_clie = gen.val_oid
          AND gen.attr_enti = 'MAE_ESTAT_CLIEN');

  END IF;

 END CCC_PR_DETAL_PREVI_MOVIM_INCOB;

  PROCEDURE CCC_PR_REPOR_MEDIO_MAGNE_BIMEN(
  p_fec_inic                     IN   VARCHAR2,
  p_fec_fina                     IN   VARCHAR2)
 IS

  lv_fec_inic                    DATE;
  lv_fec_fina                    DATE;

 BEGIN

  DELETE FROM ccc_repor_medio_magne_clien;
  DELETE FROM ccc_repor_medio_magne_saldo;
  DELETE FROM ccc_repor_medio_magne_bimen;

  lv_fec_inic := TO_DATE(p_fec_inic,'DD/MM/YYYY');
  lv_fec_fina := TO_DATE(p_fec_fina,'DD/MM/YYYY');

  /*
  -- Buscar el ultimo dia del a¿o anterior
  SELECT ADD_MONTHS (TRUNC (SYSDATE, 'YEAR'), -1 ) + 30
  INTO lv_fec_inic
  FROM dual;
  */

  INSERT INTO ccc_repor_medio_magne_clien
   WITH temp1
    AS
     ((SELECT mcc.clie_oid_clie, mcc.imp_movi
      FROM ccc_movim_cuent_corri mcc
      WHERE mcc.fec_docu <= lv_fec_inic)
     UNION ALL
     (SELECT mb.clie_oid_clie, mb.imp_pago*-1 imp_movi
      FROM ccc_movim_banca mb
      WHERE mb.cod_iden_proc = 'P'
        AND mb.fec_proc <= lv_fec_inic)),
    temp2 AS
     (SELECT t1.clie_oid_clie , SUM(t1.imp_movi) imp_movi
      FROM temp1 t1
      GROUP BY t1.clie_oid_clie)
      SELECT
        mc.oid_clie,
        mc.cod_clie,
        mc.val_nom1 || ' ' || mc.val_nom2 || ' ' || mc.val_ape1 || ' ' || mc.val_ape2 nom_clie,
        NVL(t2.imp_movi,0) imp_sald
       FROM temp2 t2,
            mae_clien mc
       WHERE mc.oid_clie = t2.clie_oid_clie(+);

   INSERT INTO ccc_repor_medio_magne_saldo
    SELECT
     a.oid_clie,
     a.cod_clie,
     a.nom_clie,
     a.imp_sald,
     NVL(b.imp_carg,0),
     NVL(c.imp_abon,0)
    FROM
     ccc_repor_medio_magne_clien a,
     (SELECT mcc.clie_oid_clie, SUM(mcc.imp_movi) imp_carg
     FROM
      ccc_movim_cuent_corri mcc,
      ccc_repor_medio_magne_clien mc
      WHERE mc.oid_clie = mcc.clie_oid_clie
        AND mcc.fec_docu > lv_fec_inic
        AND mcc.fec_docu <= lv_fec_fina
        AND mcc.imp_movi > 0
      GROUP BY mcc.clie_oid_clie) b,
      (SELECT clie_oid_clie, SUM(imp_movi) imp_abon
      FROM
      ((SELECT mcc.clie_oid_clie, mcc.imp_movi*-1 imp_movi
      FROM ccc_movim_cuent_corri mcc,
         ccc_repor_medio_magne_clien mc
      WHERE mc.oid_clie = mcc.clie_oid_clie
        AND mcc.imp_movi < 0
        AND mcc.fec_docu > lv_fec_inic
        AND mcc.fec_docu <= lv_fec_fina)
     UNION ALL
     (SELECT mb.clie_oid_clie, mb.imp_pago imp_movi
      FROM ccc_movim_banca mb,
           ccc_repor_medio_magne_clien mc
      WHERE mc.oid_clie = mb.clie_oid_clie
        AND mb.cod_iden_proc = 'P'
        AND mb.fec_proc > lv_fec_inic
        AND mb.fec_proc <= lv_fec_fina))
      GROUP BY clie_oid_clie) c
     WHERE a.oid_clie = b.clie_oid_clie(+)
       AND a.oid_clie = c.clie_oid_clie(+);

  DELETE FROM ccc_repor_medio_magne_saldo rb
  WHERE ABS(rb.imp_sald) + ABS(rb.imp_carg) + ABS(rb.imp_abon) = 0;

  INSERT INTO ccc_repor_medio_magne_bimen
  SELECT
   s.cod_clie,        -- cod_clie	varchar2(15)
   mci.num_docu_iden, -- num_docu_iden	varchar2(20)
   s.nom_clie,        -- nom_clie	varchar2(250)
   h.nom_depa,              --val_dept  varchar2(250)
   h.nom_prov,              --   val_prov  varchar2(250)
   h.nom_prov,              --val_dist  varchar2(250)
   h.val_nomb_via || ' ' ||
   h.num_ppal  || ' ' ||
   h.val_obse ,              --val_dire  varchar2(1000)
   s.imp_sald,        -- imp_sald_inic	number(15,2)
   s.imp_carg,        -- imp_carg	number(15,2)
   s.imp_abon,        -- imp_abon	number(15,2)
   s.imp_sald + s.imp_carg - s.imp_abon                 --imp_sald_fina	number(15,2)
  FROM
   ccc_repor_medio_magne_saldo s,
   mae_clien_ident mci,
   int_reu_consu_histo h
  WHERE s.oid_clie = mci.clie_oid_clie
    AND s.cod_clie = h.cod_clie(+)
    AND mci.val_iden_docu_prin = 1;

 END CCC_PR_REPOR_MEDIO_MAGNE_BIMEN;

 PROCEDURE CCC_PR_REPOR_ANTIG_SALDO(
  p_fec_cort                     IN   VARCHAR2,
  p_cod_usua                     IN   VARCHAR2)
 IS

  lv_fec_cort                    DATE;
  lv_oid_movi_cc                 NUMBER(12);
  lv_imp_deud_pend               NUMBER(12,2);


  CURSOR c_dife
  IS
   SELECT
    d.oid_clie,
    d.imp_pend_repor,
    d.imp_pend_clie
   FROM ccc_repor_difer_antig_saldo d;

  CURSOR c_repo_movi(
   p_oid_clie NUMBER)
  IS
   SELECT
    s.oid_clie,
    s.oid_movi_cc,
    s.imp_movi
   FROM ccc_repor_detal_antig_saldo s
   WHERE s.oid_clie = p_oid_clie
   ORDER BY s.num_dias_atra DESC;

 BEGIN

  lv_fec_cort := TO_DATE(p_fec_cort,'DD/MM/YYYY');

  DELETE FROM ccc_repor_detal_antig_clien;

  INSERT INTO ccc_repor_detal_antig_clien
   WITH temp1
    AS
     ((SELECT mcc.clie_oid_clie, mcc.imp_movi
      FROM ccc_movim_cuent_corri mcc
      WHERE mcc.fec_docu <= lv_fec_cort)
     UNION ALL
     (SELECT mb.clie_oid_clie, mb.imp_pago*-1 imp_movi
      FROM ccc_movim_banca mb
      WHERE mb.cod_iden_proc = 'P'
        AND mb.fec_proc <= lv_fec_cort))
     SELECT t1.clie_oid_clie , SUM(t1.imp_movi) imp_movi
      FROM temp1 t1
      HAVING SUM(t1.imp_movi) <> 0
      GROUP BY t1.clie_oid_clie;

  DELETE FROM ccc_repor_inici_antig_saldo;

  INSERT INTO ccc_repor_inici_antig_saldo
   SELECT
    mcc.clie_oid_clie,
    mcc.oid_movi_cc ,
    mcc.imp_movi,
    0,
    0,
    lv_fec_cort - mcc.fec_docu
   FROM
    ccc_movim_cuent_corri mcc,
    ccc_repor_detal_antig_clien i
   WHERE mcc.clie_oid_clie = i.oid_clie
     AND mcc.fec_docu <=  lv_fec_cort
     AND mcc.imp_movi > 0
     AND i.imp_pend > 0
     AND NOT EXISTS (
       SELECT NULL
       FROM ccc_movim_cuent_corri a,
            ccc_repor_detal_antig_clien b
       WHERE a.clie_oid_clie  = b.oid_clie
         AND a.oid_movi_cc = mcc.oid_movi_cc
         AND a.imp_pend = 0
         AND a.fec_ulti_movi < lv_fec_cort);

  DELETE FROM ccc_repor_detal_antig_pagos;

  INSERT INTO ccc_repor_detal_antig_pagos
   WITH temp1 AS
    ((SELECT
      mcc.clie_oid_clie,
      mcc.oid_movi_cc,
      SUM(mcc.imp_pago) imp_pago
     FROM
      ccc_movim_cuent_corri mcc,
      ccc_repor_inici_antig_saldo sal
     WHERE mcc.oid_movi_cc = sal.oid_movi_cc
       AND mcc.fec_ulti_movi <=  lv_fec_cort
     GROUP BY mcc.clie_oid_clie,mcc.oid_movi_cc)
    UNION ALL
    (SELECT
      his.clie_oid_clie,
      his.mvcc_oid_movi_cc oid_movi_cc,
      SUM(his.imp_pago) imp_pago
     FROM
      ccc_histo_movim_cc his,
      ccc_repor_inici_antig_saldo sal
     WHERE his.mvcc_oid_movi_cc = sal.oid_movi_cc
       AND his.num_hist > 0
       AND his.fec_movi <=  lv_fec_cort
     GROUP BY his.clie_oid_clie,his.mvcc_oid_movi_cc)),
    temp2 AS
     (SELECT t1.clie_oid_clie,oid_movi_cc, SUM(imp_pago) imp_pago
      FROM temp1 t1
      GROUP BY t1.clie_oid_clie,t1.oid_movi_cc)
     SELECT
      s.oid_clie,
      s.oid_movi_cc,
      NVL(t2.imp_pago,0) imp_pago
     FROM
      temp2 t2,
      ccc_repor_inici_antig_saldo s
     WHERE s.oid_movi_cc = t2.oid_movi_cc(+);

  DELETE FROM ccc_repor_detal_antig_saldo;

  INSERT INTO ccc_repor_detal_antig_saldo
   SELECT
    a.oid_clie,
    a.oid_movi_cc,
    a.imp_movi,
    NVL(SUM(b.imp_pago),0),
    a.imp_movi - NVL(SUM(b.imp_pago),0),
    a.num_dias_atra
   FROM
    ccc_repor_inici_antig_saldo a,
    ccc_repor_detal_antig_pagos b
   WHERE a.oid_movi_cc = b.oid_movi_cc(+)
   GROUP BY
    a.oid_clie,
    a.oid_movi_cc,
    a.imp_movi,
    a.num_dias_atra;

  INSERT INTO ccc_repor_detal_antig_saldo a
   SELECT
    y.oid_clie,
    (SELECT MAX(mcc.oid_movi_cc)
     FROM ccc_movim_cuent_corri mcc
     WHERE mcc.clie_oid_clie = y.oid_clie
     AND mcc.imp_movi > 0
     AND mcc.fec_docu <= lv_fec_cort),
     y.imp_pend_clie,
     0,
     y.imp_pend_clie,
     0
   FROM
    (SELECT a.oid_clie, sum(a.imp_pend) imp_pend_repor
     FROM ccc_repor_detal_antig_saldo a
     GROUP BY a.oid_clie) x,
    (SELECT b.oid_clie, sum(b.imp_pend) imp_pend_clie
     FROM ccc_repor_detal_antig_clien b
     WHERE b.imp_pend > 0
     GROUP BY b.oid_clie) y
   WHERE y.oid_clie = x.oid_clie(+)
      AND x.oid_clie IS NULL;

  DELETE FROM ccc_repor_difer_antig_saldo;

  INSERT INTO ccc_repor_difer_antig_saldo
   SELECT
    x.oid_clie,
    x.imp_pend_repor,
    y.imp_pend_clie ,
    x.imp_pend_repor - y.imp_pend_clie dif
   FROM
    (SELECT a.oid_clie, sum(a.imp_pend) imp_pend_repor
     from ccc_repor_detal_antig_saldo a
     WHERE 1=1
     GROUP BY a.oid_clie) x,
    (SELECT b.oid_clie, sum(b.imp_pend) imp_pend_clie
     FROM ccc_repor_detal_antig_clien b
     WHERE b.imp_pend > 0
     GROUP BY b.oid_clie) y
   WHERE x.oid_clie = y.oid_clie
      AND x.imp_pend_repor <> y.imp_pend_clie;


  FOR v_dife IN c_dife LOOP

   UPDATE ccc_repor_detal_antig_saldo a
   SET a.imp_pend = 0,
       a.imp_abon = a.imp_movi
   WHERE a.oid_clie = v_dife.oid_clie;

   lv_imp_deud_pend := v_dife.imp_pend_clie;

   FOR v_repo_movi IN c_repo_movi(v_dife.oid_clie) LOOP

    IF lv_imp_deud_pend > v_repo_movi.imp_movi THEN

     UPDATE ccc_repor_detal_antig_saldo a
     SET
      a.imp_pend = a.imp_movi,
      a.imp_abon = 0
     WHERE a.oid_movi_cc = v_repo_movi.oid_movi_cc;

     lv_imp_deud_pend := lv_imp_deud_pend - v_repo_movi.imp_movi;

    ELSE

     UPDATE ccc_repor_detal_antig_saldo a
     SET
      a.imp_pend = lv_imp_deud_pend,
      a.imp_abon = a.imp_movi - lv_imp_deud_pend
     WHERE a.oid_movi_cc = v_repo_movi.oid_movi_cc;

     lv_imp_deud_pend := 0;

    END IF;

    IF lv_imp_deud_pend = 0 THEN
     EXIT;
    END IF;

   END LOOP;

   IF lv_imp_deud_pend > 0 THEN

    SELECT MIN(a.oid_movi_cc)
    INTO lv_oid_movi_cc
    FROM ccc_repor_detal_antig_saldo a
    WHERE a.oid_clie = v_dife.oid_clie;

    UPDATE ccc_repor_detal_antig_saldo a
    SET a.imp_movi = a.imp_movi + lv_imp_deud_pend,
        a.imp_pend = a.imp_pend + lv_imp_deud_pend
    WHERE a.oid_movi_cc = lv_oid_movi_cc;

   END IF;

  END LOOP;

 END CCC_PR_REPOR_ANTIG_SALDO;

 PROCEDURE CCC_PR_REPOR_ANTIG_SALDO_CSV(
  pscodigopais                   IN   VARCHAR2,
  psnombrearchivo                IN   VARCHAR2,
  pstitulo                       IN   VARCHAR2,
  psdirectorio                   OUT  VARCHAR2)
 IS

  lsdirtempo                     bas_inter.dir_temp%TYPE;
  w_filas                        NUMBER := 5000;
  v_handle                       utl_file.file_type;
  lslinea                        VARCHAR2(4000);

  CURSOR c_repor
  IS
  SELECT
   r.cod_regi,
   r.cod_zona,
   r.cod_clie,
   r.nom_clie,
   r.num_docu_iden,
   r.val_dept,
   r.val_prov,
   r.val_dist,
   r.val_urba,
   r.val_dire,
   r.val_tipo_carg,
   r.val_camp_carg,
   r.val_nume_carg,
   r.val_fech_carg,
   r.val_dias_atra,
   r.val_dias_venc,
   r.imp_movi,
   r.imp_pend
  FROM ccc_gener_antig_saldo_csv r;

  TYPE lv_tipo_repor_rec IS RECORD(
   cod_regi                      ccc_gener_antig_saldo_csv.cod_regi%TYPE,
   cod_zona                      ccc_gener_antig_saldo_csv.cod_zona%TYPE,
   cod_clie                      ccc_gener_antig_saldo_csv.cod_clie%TYPE,
   nom_clie                      ccc_gener_antig_saldo_csv.nom_clie%TYPE,
   num_docu_iden                 ccc_gener_antig_saldo_csv.num_docu_iden%TYPE,
   val_dept                      ccc_gener_antig_saldo_csv.val_dept%TYPE,
   val_prov                      ccc_gener_antig_saldo_csv.val_prov%TYPE,
   val_dist                      ccc_gener_antig_saldo_csv.val_dist%TYPE,
   val_urba                      ccc_gener_antig_saldo_csv.val_urba%TYPE,
   val_dire                      ccc_gener_antig_saldo_csv.val_dire%TYPE,
   val_tipo_carg                 ccc_gener_antig_saldo_csv.val_tipo_carg%TYPE,
   val_camp_carg                 ccc_gener_antig_saldo_csv.val_camp_carg%TYPE,
   val_nume_carg                 ccc_gener_antig_saldo_csv.val_nume_carg%TYPE,
   val_fech_carg                 ccc_gener_antig_saldo_csv.val_fech_carg%TYPE,
   val_dias_atra                 ccc_gener_antig_saldo_csv.val_dias_atra%TYPE,
   val_dias_venc                 ccc_gener_antig_saldo_csv.val_dias_venc%TYPE,
   imp_movi                      ccc_gener_antig_saldo_csv.imp_movi%TYPE,
   imp_pend                      ccc_gener_antig_saldo_csv.imp_pend%TYPE);

  TYPE lv_tipo_repor_tab         IS TABLE OF lv_tipo_repor_rec;
  lv_repor_tab                   lv_tipo_repor_tab;
  lv_titu                        VARCHAR(500):= 'REGION,ZONA,CODIGO,NOMBRES Y APELLIDOS,CEDULA,DEPARTAMENTO, PROVINCIA,DISTRITO,URBANIZACION,DIRECCION,TIPO CARGO,CAMPAÑA,NUMERO CARGO,FECHA CARGO,DIAS ATRASO,DIAS VENCIMIENTO, IMPORTE, SALDO';
  lbAbrirUtlFile  BOOLEAN;

 BEGIN

  lbAbrirUtlFile := true;
  EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';

  DELETE FROM ccc_gener_antig_saldo_csv;

  INSERT INTO ccc_gener_antig_saldo_csv
   (SELECT
     GEN_PKG_GENER.gen_fn_clien_datos_oid(mc.oid_clie,'COD_REGI'),
     GEN_PKG_GENER.gen_fn_clien_datos_oid(mc.oid_clie,'COD_ZONA'),
     mc.cod_clie,
     mc.val_nom1 || ' ' || mc.val_nom2 || ' ' || mc.val_Ape1 || ' ' || mc.val_Ape2,
     mci.num_docu_iden,
     GEN_PKG_GENER.gen_fn_clien_datos_oid(mc.oid_clie,'DES_DPTO'),
     GEN_PKG_GENER.gen_fn_clien_datos_oid(mc.oid_clie,'DES_PROV'),
     GEN_PKG_GENER.gen_fn_clien_datos_oid(mc.oid_clie,'DES_DIST'),
     GEN_PKG_GENER.gen_fn_clien_datos_oid(mc.oid_clie,'DES_URBA'),
     FIN_PKG_GENER.FIN_FN_OBTIE_DIREC_COMPL_CLIEN(mc.oid_clie),
     su.des_subp,
     spc.cod_peri,
     TO_CHAR(mcc.num_iden_cuot),
     TO_CHAR(mcc.fec_docu,'DD/MM/YYYY'),
     TO_CHAR(am.num_dias_atra),
     am.num_dias_atra,
     am.imp_movi,
     am.imp_pend
    FROM
     ccc_repor_detal_antig_saldo am,
     mae_clien mc,
     mae_clien_ident mci,
     ccc_movim_cuent_corri mcc,
     ccc_subpr su,
     cra_perio cp,
     seg_perio_corpo spc
    WHERE am.oid_clie = mc.oid_clie
      AND mc.oid_clie = mci.clie_oid_clie
      AND mcc.subp_oid_subp_crea = su.oid_subp
      AND mci.val_iden_docu_prin = 1
      AND am.oid_movi_cc = mcc.oid_movi_cc
      AND mcc.perd_oid_peri = cp.oid_peri
      AND cp.peri_oid_peri = spc.oid_peri
      AND am.imp_pend > 0 );


  INSERT INTO ccc_gener_antig_saldo_csv
 (SELECT
     GEN_PKG_GENER.gen_fn_clien_datos_oid(mc.oid_clie,'COD_REGI') AS "REGIONAL",
     GEN_PKG_GENER.gen_fn_clien_datos_oid(mc.oid_clie,'COD_ZONA') AS "ZONA",
     mc.cod_clie AS "CODIGO",
     mc.val_nom1 || ' ' || mc.val_nom2 || ' ' || mc.val_Ape1 || ' ' || mc.val_Ape2 AS "NOMBRES",
     mci.num_docu_iden AS "CEDULA",
     GEN_PKG_GENER.gen_fn_clien_datos_oid(mc.oid_clie,'DES_DPTO') AS "DEPARTAMENTO",
     GEN_PKG_GENER.gen_fn_clien_datos_oid(mc.oid_clie,'DES_PROV') AS "PROVINCIA",
     GEN_PKG_GENER.gen_fn_clien_datos_oid(mc.oid_clie,'DES_DIST') AS "DISTRITO",
     GEN_PKG_GENER.gen_fn_clien_datos_oid(mc.oid_clie,'DES_URBA') AS "URBANIZACION",
     FIN_PKG_GENER.FIN_FN_OBTIE_DIREC_COMPL_CLIEN(mc.oid_clie) AS "DIRECCION",
     'SALDO A FAVOR' AS "PROCESO",
     ' ' AS "CAMPAÑA",
     ' ' AS "DOCUMENTO",
     ' ' AS "FECHA FACTURA",
     ' ' AS "DIAS FACT",
     ' ' AS "DIAS VENC",
     a.imp_pend AS "VALOR FACTURA",
     a.imp_pend AS "VALOR"
FROM
 ccc_repor_detal_antig_clien a,
 mae_clien mc,
 mae_clien_ident mci
WHERE a.oid_clie = mc.oid_clie
AND mc.oid_clie = mci.clie_oid_clie
AND mci.val_iden_docu_prin = 1
AND a.imp_pend < 0);

  OPEN c_repor;
  LOOP
   FETCH c_repor BULK COLLECT INTO lv_repor_tab LIMIT W_FILAS;
    IF lbAbrirUtlFile THEN
     GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', lv_titu, lsdirtempo, v_handle);
     psdirectorio := lsdirtempo;
     lbAbrirUtlFile := FALSE;
    END IF ;

    IF lv_repor_tab.COUNT > 0 THEN
     FOR x IN lv_repor_tab.FIRST .. lv_repor_tab.LAST LOOP
      lslinea :=
                                 '"'|| lv_repor_tab(x).cod_regi || '"' || ',' ||
                                 '"'|| lv_repor_tab(x).cod_zona || '"' || ',' ||
                                 '"'|| lv_repor_tab(x).cod_clie || '"' || ',' ||
                                 '"'|| lv_repor_tab(x).nom_clie || '"' || ',' ||
                                 '"'|| lv_repor_tab(x).num_docu_iden || '"' || ',' ||
                                 '"'|| lv_repor_tab(x).val_dept || '"' || ',' ||
                                 '"'|| lv_repor_tab(x).val_prov || '"' || ',' ||
                                 '"'|| lv_repor_tab(x).val_dist || '"' || ',' ||
                                 '"'|| lv_repor_tab(x).val_urba || '"' || ',' ||
                                 '"'|| lv_repor_tab(x).val_dire || '"' || ',' ||
                                 '"'|| lv_repor_tab(x).val_tipo_carg || '"' || ',' ||
                                 '"'|| lv_repor_tab(x).val_camp_carg || '"' || ',' ||
                                 '"'|| lv_repor_tab(x).val_nume_carg || '"' || ',' ||
                                 '"'|| lv_repor_tab(x).val_fech_carg || '"' || ',' ||
                                 '"'|| lv_repor_tab(x).val_dias_atra || '"' || ',' ||
                                 '"'|| lv_repor_tab(x).val_dias_venc || '"' || ',' ||
                                 '"'|| lv_repor_tab(x).imp_movi || '"' || ',' ||
                                 '"'|| lv_repor_tab(x).imp_pend || '"' ;

                    UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
                END LOOP;
            END IF;
            EXIT WHEN c_repor%NOTFOUND;
        END LOOP;
     CLOSE c_repor;

  IF NOT lbAbrirUtlFile THEN
   utl_file.fclose(V_HANDLE);
  END IF;

 EXCEPTION
  WHEN OTHERS THEN
   ln_sqlcode := SQLCODE;
   ls_sqlerrm := substr(sqlerrm,1,250);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR CCC_PR_REPOR_ANTIG_SALDO_CSV: '||ls_sqlerrm);

 END CCC_PR_REPOR_ANTIG_SALDO_CSV ;

 PROCEDURE CCC_PR_GENER_CUADR_SAPFI(
  p_fec_inic                       IN   VARCHAR2,
  p_fec_fina                       IN   VARCHAR2)
 IS

  lv_reg_ccc_repor_cuadr_sapfi     ccc_repor_cuadr_sapfi%ROWTYPE;
  lv_carg_ante                     NUMBER(14,2);
  lv_abon_ante                     NUMBER(14,2);
  lv_fec_proc                      DATE;
  lv_fec_inic                      DATE;
  lv_fec_fina                      DATE;
  lv_dif_dias                      NUMBER(12);

 BEGIN

  DELETE from ccc_repor_cuadr_sapfi;

  lv_fec_inic := TO_DATE(p_fec_inic,'DD/MM/YYYY');
  lv_fec_fina := TO_DATE(p_fec_fina,'DD/MM/YYYY');

  lv_dif_dias := lv_fec_fina - lv_fec_inic + 1 ;

  lv_fec_proc := lv_fec_inic;

  FOR i IN 1 .. lv_dif_dias  LOOP

   lv_reg_ccc_repor_cuadr_sapfi.Fec_Proc := lv_fec_proc;

   SELECT  NVL(SUM(MCC.IMP_MOVI),0)
   INTO lv_carg_ante
   FROM ccc_movim_cuent_corri mcc
   WHERE mcc.fec_docu <= lv_reg_ccc_repor_cuadr_sapfi.Fec_Proc -1 ;

    SELECT  NVL(SUM(MB.IMP_PAGO) ,0)
    INTO lv_abon_ante
    FROM ccc_movim_banca mb
    WHERE mb.cod_iden_proc ='P'
    AND mb.fec_proc <= lv_reg_ccc_repor_cuadr_sapfi.Fec_Proc -1;

    lv_reg_ccc_repor_cuadr_sapfi.Imp_Sald_Ante :=  lv_carg_ante - ABS(lv_abon_ante);


    SELECT NVL(SUM(imp_movi),0)
    INTO lv_reg_ccc_repor_cuadr_sapfi.imp_fact
    FROM
     (SELECT
       ccc_movim_cuent_corri.soca_oid_soli_cabe,
       SUM(ccc_movim_cuent_corri.imp_movi) imp_movi
      FROM
            ccc_movim_cuent_corri,
            ccc_subpr,
            ccc_proce,
            per_repor_param_cuent_corri param
        WHERE ccc_subpr.ccpr_oid_proc = ccc_proce.oid_proc
        AND ccc_movim_cuent_corri.subp_oid_subp_crea=ccc_subpr.oid_subp
        AND param.cod_proc=ccc_proce.cod_proc
        AND param.cod_subp=ccc_subpr.cod_subp
        AND param.cod_parm='01'
        AND ccc_movim_cuent_corri.fec_docu = lv_reg_ccc_repor_cuadr_sapfi.Fec_Proc
        HAVING SUM(ccc_movim_cuent_corri.imp_movi) > 0
        GROUP BY
           ccc_movim_cuent_corri.soca_oid_soli_cabe);


    SELECT NVL(SUM(ccc_movim_cuent_corri.imp_movi),0)
    INTO lv_reg_ccc_repor_cuadr_sapfi.Imp_Carg_Dire
    FROM
     ccc_detal_cargo_abono_direc,
     ccc_movim_cuent_corri,
     ccc_tipo_abono_subpr,
     ccc_subpr,
     ccc_proce
    WHERE ccc_detal_cargo_abono_direc.mvcc_oid_movi_Cc=ccc_movim_cuent_corri.oid_movi_cc
      AND ccc_detal_cargo_abono_direc.tasp_oid_tipo_abon_subp = ccc_tipo_abono_subpr.oid_tipo_abon_subp
      AND ccc_tipo_abono_subpr.subp_oid_subp = ccc_subpr.oid_subp
      AND ccc_subpr.ccpr_oid_proc = ccc_proce.oid_proc
      AND ccc_movim_cuent_corri.subp_oid_subp_crea=ccc_subpr.oid_subp
      AND ccc_proce.cod_proc = 'CCC007'
      AND ccc_subpr.cod_subp <> 7
      AND ccc_movim_cuent_corri.fec_docu = lv_reg_ccc_repor_cuadr_sapfi.Fec_Proc;

    SELECT NVL(SUM(ccc_movim_cuent_corri.imp_movi),0)
    INTO lv_reg_ccc_repor_cuadr_sapfi.imp_carg_fami_prot
    FROM
     ccc_detal_cargo_abono_direc,
     ccc_movim_cuent_corri,
     ccc_tipo_abono_subpr,
     ccc_subpr,
     ccc_proce
    WHERE ccc_detal_cargo_abono_direc.mvcc_oid_movi_Cc=ccc_movim_cuent_corri.oid_movi_cc
      AND ccc_detal_cargo_abono_direc.tasp_oid_tipo_abon_subp = ccc_tipo_abono_subpr.oid_tipo_abon_subp
      AND ccc_tipo_abono_subpr.subp_oid_subp = ccc_subpr.oid_subp
      AND ccc_subpr.ccpr_oid_proc = ccc_proce.oid_proc
      AND ccc_movim_cuent_corri.subp_oid_subp_crea=ccc_subpr.oid_subp
      AND ccc_proce.cod_proc = 'CCC007'
      AND ccc_subpr.cod_subp = 7
      AND ccc_movim_cuent_corri.fec_docu = lv_reg_ccc_repor_cuadr_sapfi.Fec_Proc;

     SELECT NVL(SUM(ccc_movim_cuent_corri.imp_movi),0)
     INTO lv_reg_ccc_repor_cuadr_sapfi.Imp_Carg_Sald_Meno
     FROM
            ccc_detal_cargo_abono_direc,
            ccc_movim_cuent_corri,
            ccc_tipo_abono_subpr,
            ccc_subpr,
            ccc_proce,
            per_repor_param_cuent_corri param
        WHERE ccc_detal_cargo_abono_direc.mvcc_oid_movi_Cc=ccc_movim_cuent_corri.oid_movi_cc
        AND ccc_detal_cargo_abono_direc.tasp_oid_tipo_abon_subp = ccc_tipo_abono_subpr.oid_tipo_abon_subp
        AND ccc_tipo_abono_subpr.subp_oid_subp = ccc_subpr.oid_subp
        AND ccc_subpr.ccpr_oid_proc = ccc_proce.oid_proc
        AND ccc_movim_cuent_corri.subp_oid_subp_crea=ccc_subpr.oid_subp
        AND param.cod_parm='10'
        AND param.cod_proc=ccc_proce.cod_proc
        AND param.cod_subp=ccc_subpr.cod_subp
        AND ccc_movim_cuent_corri.fec_docu = lv_reg_ccc_repor_cuadr_sapfi.Fec_Proc;


        SELECT ABS(NVL(SUM(ccc_movim_cuent_corri.imp_movi),0) )
        INTO  lv_reg_ccc_repor_cuadr_sapfi.imp_abon_sald_meno
     FROM
            ccc_detal_cargo_abono_direc,
            ccc_movim_cuent_corri,
            ccc_tipo_abono_subpr,
            ccc_subpr,
            ccc_proce,
            per_repor_param_cuent_corri param
        WHERE ccc_detal_cargo_abono_direc.mvcc_oid_movi_Cc=ccc_movim_cuent_corri.oid_movi_cc
        AND ccc_detal_cargo_abono_direc.tasp_oid_tipo_abon_subp = ccc_tipo_abono_subpr.oid_tipo_abon_subp
        AND ccc_tipo_abono_subpr.subp_oid_subp = ccc_subpr.oid_subp
        AND ccc_subpr.ccpr_oid_proc = ccc_proce.oid_proc
        AND ccc_movim_cuent_corri.subp_oid_subp_crea=ccc_subpr.oid_subp
        AND param.cod_parm='08'
        AND param.cod_proc=ccc_proce.cod_proc
        AND param.cod_subp=ccc_subpr.cod_subp
        AND ccc_movim_cuent_corri.fec_docu = lv_reg_ccc_repor_cuadr_sapfi.Fec_Proc;

       SELECT ABS(NVL(SUM(ccc_movim_cuent_corri.imp_movi),0))
       INTO lv_reg_ccc_repor_cuadr_sapfi.imp_abon_casti_inco
     FROM
            ccc_detal_cargo_abono_direc,
            ccc_movim_cuent_corri,
            ccc_tipo_abono_subpr,
            ccc_subpr,
            ccc_proce,
            per_repor_param_cuent_corri param
        WHERE ccc_detal_cargo_abono_direc.mvcc_oid_movi_Cc=ccc_movim_cuent_corri.oid_movi_cc
        AND ccc_detal_cargo_abono_direc.tasp_oid_tipo_abon_subp = ccc_tipo_abono_subpr.oid_tipo_abon_subp
        AND ccc_tipo_abono_subpr.subp_oid_subp = ccc_subpr.oid_subp
        AND ccc_subpr.ccpr_oid_proc = ccc_proce.oid_proc
        AND ccc_movim_cuent_corri.subp_oid_subp_crea=ccc_subpr.oid_subp
        AND param.cod_parm='09'
        AND param.cod_proc=ccc_proce.cod_proc
        AND param.cod_subp=ccc_subpr.cod_subp
        AND ccc_movim_cuent_corri.fec_docu = lv_reg_ccc_repor_cuadr_sapfi.Fec_Proc;


    SELECT ABS(NVL(SUM(ccc_movim_cuent_corri.imp_movi),0))
    INTO lv_reg_ccc_repor_cuadr_sapfi.imp_abon_dire
    FROM
     ccc_detal_cargo_abono_direc,
     ccc_movim_cuent_corri,
     ccc_tipo_abono_subpr,
     ccc_subpr,
     ccc_proce
    WHERE ccc_detal_cargo_abono_direc.mvcc_oid_movi_Cc=ccc_movim_cuent_corri.oid_movi_cc
      AND ccc_detal_cargo_abono_direc.tasp_oid_tipo_abon_subp = ccc_tipo_abono_subpr.oid_tipo_abon_subp
      AND ccc_tipo_abono_subpr.subp_oid_subp = ccc_subpr.oid_subp
      AND ccc_subpr.ccpr_oid_proc = ccc_proce.oid_proc
      AND ccc_movim_cuent_corri.subp_oid_subp_crea=ccc_subpr.oid_subp
      AND ccc_proce.cod_proc = 'CCC008'
      AND ccc_movim_cuent_corri.fec_docu = lv_reg_ccc_repor_cuadr_sapfi.Fec_Proc;

        SELECT ABS(NVL(SUM(imp_movi),0))
        INTO lv_reg_ccc_repor_cuadr_sapfi.imp_nota_cred
   FROM
    (SELECT
  ccc_movim_cuent_corri.soca_oid_soli_cabe,
        SUM(ccc_movim_cuent_corri.imp_movi) imp_movi
     FROM
            ccc_movim_cuent_corri,
            ccc_subpr,
            ccc_proce,
            per_repor_param_cuent_corri param
        WHERE ccc_subpr.ccpr_oid_proc = ccc_proce.oid_proc
        AND ccc_movim_cuent_corri.subp_oid_subp_crea=ccc_subpr.oid_subp
        AND param.cod_proc=ccc_proce.cod_proc
        AND param.cod_subp=ccc_subpr.cod_subp
        AND param.cod_parm='03'
        AND ccc_movim_cuent_corri.fec_docu = lv_reg_ccc_repor_cuadr_sapfi.Fec_Proc
        HAVING SUM(ccc_movim_cuent_corri.imp_movi) < 0
        GROUP BY
           ccc_movim_cuent_corri.soca_oid_soli_cabe);


   SELECT NVL(SUM(MB.IMP_PAGO),0)
   INTO lv_reg_ccc_repor_cuadr_sapfi.Imp_Cobr_Auto
    FROM ccc_movim_banca mb,
       ccc_proce cp,
       ccc_subpr su,
       ccc_cuent_corri_banca ccb
  WHERE mb.subp_oid_marc_crea = su.oid_subp
  AND su.ccpr_oid_proc = cp.oid_proc
  AND mb.ccba_oid_cc_banc = ccb.oid_cuen_corr_banc
  AND cp.cod_proc = 'TES001'
  AND su.cod_subp = 1
  AND ccb.ind_bole_depo = 0
  AND ccb.ind_banc_cheq = 0
  AND mb.cod_iden_proc ='P'
  AND mb.fec_proc = lv_reg_ccc_repor_cuadr_sapfi.Fec_Proc;


   SELECT NVL(SUM(MB.IMP_PAGO),0)
   INTO lv_reg_ccc_repor_cuadr_sapfi.Imp_Cobr_Manu
  FROM CCC_MOVIM_BANCA MB,
       ccc_proce cp,
       ccc_subpr su,
       ccc_cuent_corri_banca ccb
  WHERE mb.subp_oid_marc_crea = su.oid_subp
  AND su.ccpr_oid_proc = cp.oid_proc
  AND mb.ccba_oid_cc_banc = ccb.oid_cuen_corr_banc
  AND cp.cod_proc = 'TES001'
  AND su.cod_subp = 2
  AND ccb.ind_bole_depo = 0
  AND ccb.ind_banc_cheq = 0
  AND mb.cod_iden_proc ='P'
  AND MB.FEC_proc = lv_reg_ccc_repor_cuadr_sapfi.Fec_Proc;

   SELECT NVL(SUM(MB.IMP_PAGO),0)
   INTO lv_reg_ccc_repor_cuadr_sapfi.Imp_Cobr_Regu
  FROM CCC_MOVIM_BANCA MB,
       ccc_proce cp,
       ccc_subpr su,
       ccc_cuent_corri_banca ccb
  WHERE mb.subp_oid_marc_crea = su.oid_subp
  AND su.ccpr_oid_proc = cp.oid_proc
  AND mb.ccba_oid_cc_banc = ccb.oid_cuen_corr_banc
  AND cp.cod_proc = 'TES001'
  AND su.cod_subp = 3
  AND ccb.ind_bole_depo = 0
  AND ccb.ind_banc_cheq = 0
  AND mb.cod_iden_proc ='P'
  AND MB.FEC_proc =lv_reg_ccc_repor_cuadr_sapfi.Fec_Proc;

   SELECT NVL(SUM(MB.IMP_PAGO),0)
   INTO lv_reg_ccc_repor_cuadr_sapfi.Imp_Cheq_Banc
  FROM CCC_MOVIM_BANCA MB,
       ccc_proce cp,
       ccc_subpr cs
  WHERE mb.subp_oid_marc_crea = cs.oid_subp
  AND cs.ccpr_oid_proc = cp.oid_proc
  AND cp.cod_proc = 'TES001'
  AND cs.cod_subp = '4'
  AND MB.FEC_proc =lv_reg_ccc_repor_cuadr_sapfi.Fec_Proc;


   SELECT NVL(SUM(MB.IMP_PAGO),0)
   INTO lv_reg_ccc_repor_cuadr_sapfi.Imp_Bole_Depo
  FROM CCC_MOVIM_BANCA MB,
       ccc_proce cp,
       ccc_subpr cs,
       ccc_cuent_corri_banca ccb
  WHERE mb.subp_oid_marc_crea = cs.oid_subp
  AND ccb.oid_cuen_corr_banc = mb.ccba_oid_cc_banc
  AND cs.ccpr_oid_proc = cp.oid_proc
  AND cp.cod_proc = 'TES001'
  AND cs.cod_subp = '2'
  AND mb.cod_iden_proc = 'P'
  AND ccb.ind_bole_depo = 1
  AND MB.FEC_proc = lv_reg_ccc_repor_cuadr_sapfi.Fec_Proc;

   SELECT NVL(SUM(MB.IMP_PAGO),0)
   INTO lv_reg_ccc_repor_cuadr_sapfi.IMP_TARJ_BANC
  FROM CCC_MOVIM_BANCA MB,
       ccc_proce cp,
       ccc_subpr cs
  WHERE mb.subp_oid_marc_crea = cs.oid_subp
  AND cs.ccpr_oid_proc = cp.oid_proc
  AND cp.cod_proc = 'TES001'
  AND cs.cod_subp = '6'
  AND MB.FEC_proc = lv_reg_ccc_repor_cuadr_sapfi.Fec_Proc;

  lv_reg_ccc_repor_cuadr_sapfi.imp_sald_actu := lv_reg_ccc_repor_cuadr_sapfi.imp_sald_ante +
                                               lv_reg_ccc_repor_cuadr_sapfi.imp_fact +
                                               lv_reg_ccc_repor_cuadr_sapfi.imp_carg_dire +
                                               lv_reg_ccc_repor_cuadr_sapfi.imp_carg_fami_prot +
                                               lv_reg_ccc_repor_cuadr_sapfi.imp_carg_sald_meno -
                                               lv_reg_ccc_repor_cuadr_sapfi.imp_nota_cred -
                                               lv_reg_ccc_repor_cuadr_sapfi.imp_abon_dire -
                                               lv_reg_ccc_repor_cuadr_sapfi.imp_abon_sald_meno -
                                               lv_reg_ccc_repor_cuadr_sapfi.imp_abon_casti_inco -
                                               lv_reg_ccc_repor_cuadr_sapfi.imp_cobr_auto -
                                               lv_reg_ccc_repor_cuadr_sapfi.imp_cobr_manu -
                                               lv_reg_ccc_repor_cuadr_sapfi.imp_cobr_regu -
                                               lv_reg_ccc_repor_cuadr_sapfi.imp_cheq_banc -
                                               lv_reg_ccc_repor_cuadr_sapfi.imp_bole_depo -
                                               lv_reg_ccc_repor_cuadr_sapfi.imp_tarj_banc;

  INSERT INTO ccc_repor_cuadr_sapfi VALUES lv_reg_ccc_repor_cuadr_sapfi;

  lv_fec_proc := lv_fec_proc + 1 ;

  END LOOP;

 END CCC_PR_GENER_CUADR_SAPFI;

 PROCEDURE CCC_PR_REPOR_SALDO_PENDI_CSV
 IS

 CURSOR c_interfaz
 IS

  SELECT
   zr.cod_regi AS "Region",
   zz.cod_zona AS "Zona",
   zs.cod_secc AS "Seccion",
   zt.cod_terr AS "Terr",
   mc.cod_clie AS "Codigo",
   mc.cod_digi_ctrl AS "Dig Ctrl",
   mc.val_nom1 || ' ' || mc.val_nom2 || ' ' || mc.val_ape1 || ' ' || mc.val_ape2 AS "Nombres y Apellidos",
   mci.num_docu_iden AS "Cedula",
   mce.camp_prim_pedi AS "Primer Pedido",
   mce.camp_ulti_pedi AS "Ultimo Pedido",
   spc.cod_peri AS "Campaña Cargo",
   su.des_subp AS "Tipo Cargo",
   TO_CHAR(mcc.fec_docu,'DD/MM/YYYY') AS "Fecha Cargo",
   TO_CHAR(mcc.imp_movi,'99999999999.99') AS "Importe Cargo",
   TO_CHAR(mcc.imp_pend,'99999999999.99') AS "Saldo Actual"
  FROM
   ccc_movim_cuent_corri mcc,
   ccc_subpr su,
   mae_clien mc,
   mae_clien_unida_admin mcua,
   zon_terri_admin zta,
   zon_secci zs,
   zon_terri zt,
   zon_zona zz,
   zon_regio zr,
   mae_clien_ident mci,
   cra_perio cp,
   seg_perio_corpo spc,
   mae_clien_estat mce
  WHERE mcc.clie_oid_clie = mc.oid_clie
    AND mcc.subp_oid_subp_crea = su.oid_subp
    AND mc.oid_clie = mci.clie_oid_clie
    AND mci.val_iden_docu_prin = 1
    AND mc.oid_clie = mcua.clie_oid_clie
    AND mcua.ind_acti = 1
    AND mcua.ztad_oid_terr_admi = zta.oid_terr_admi
    AND zta.terr_oid_terr = zt.oid_terr
    AND zta.zscc_oid_secc = zs.oid_secc
    AND zs.zzon_oid_zona = zz.oid_zona
    AND zz.zorg_oid_regi = zr.oid_regi
    AND mcc.perd_oid_peri = cp.oid_peri
    AND cp.peri_oid_peri = spc.oid_peri
    AND mcc.imp_pend > 0
    AND mc.oid_clie = mce.oid_clie(+)
  ORDER BY 1,2,3,4 ASC;

 TYPE interfazTipo IS RECORD (
  cod_regi                      zon_regio.cod_regi%TYPE,
  cod_zona                      zon_zona.cod_zona%TYPE,
  cod_secc                      zon_secci.cod_secc%TYPE,
  cod_terr                      zon_terri.cod_terr%TYPE,
  cod_clie                      mae_clien.cod_clie%TYPE,
  cod_digi_ctrl                 mae_clien.cod_digi_ctrl%TYPE,
  nom_clie                      ccc_consu_casti_cabec.nom_clie%TYPE,
  num_docu_iden                 mae_clien_ident.num_docu_iden%TYPE,
  cam_prim_pedi                 mae_clien_estat.camp_prim_pedi%TYPE,
  cam_ulti_pedi                 mae_clien_estat.camp_ulti_pedi%TYPE,
  cam_carg                      seg_perio_corpo.cod_peri%TYPE,
  tip_carg                      ccc_subpr.des_subp%TYPE,
  fec_carg                      VARCHAR2(10),
  imp_carg                      VARCHAR2(15),
  imp_sald                      VARCHAR2(15));


 TYPE interfazTab  IS TABLE OF interfazTipo;
 interfazRecord                  interfazTab;

 lv_titu                         VARCHAR2(4000);
 lv_nom_arch                     ccc_param_repor_query.nom_arch%TYPE;
 lv_des_dire                     ccc_param_repor_query.des_dire%TYPE;

 W_FILAS             NUMBER := 8000 ;
 lv_handle            UTL_FILE.FILE_TYPE;
 lv_line             VARCHAR2(8000);

 BEGIN

  SELECT r.nom_arch || TO_CHAR(TRUNC(SYSDATE),'DDMMYYYY') || '.csv', r.des_dire
  INTO lv_nom_arch, lv_des_dire
  FROM ccc_param_repor_query r
  WHERE r.cod_repo = 'SALD';

  lv_titu := 'Region, Zona, Seccion, Terri, Código , Dig Ctrl, Nombres y Apellidos, Cedula, Primer Pedido, Ultimo Pedido, Campaña Cargo, Tipo cargo, Fecha Cargo, Importe Cargo, Saldo Pendiente';

  lv_handle := utl_file.fopen(lv_des_dire, lv_nom_arch, 'W', 5000);

  utl_file.put_line(lv_handle, lv_titu);

  OPEN c_interfaz;
  LOOP
   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;

   IF interfazRecord.COUNT > 0 THEN

    FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP

     lv_line :='=T("'||interfazRecord(x).cod_regi||'")' ||','||
                    '=T("'||interfazRecord(x).cod_zona||'")' ||','||
                    '=T("'||interfazRecord(x).cod_secc ||'")' ||','||
                    '=T("'||interfazRecord(x).cod_terr ||'")' ||','||
                    '=T("'||interfazRecord(x).cod_clie ||'")' ||','||
                    '=T("'||interfazRecord(x).cod_digi_ctrl ||'")' ||','||
                    '"'|| interfazRecord(x).nom_clie || '"' || ',' ||
                    '=T("'||interfazRecord(x).num_docu_iden ||'")' ||','||
                    '"'|| interfazRecord(x).cam_prim_pedi || '"' || ',' ||
                    '"'|| interfazRecord(x).cam_ulti_pedi || '"' || ',' ||
                    '"'|| interfazRecord(x).cam_carg || '"' || ',' ||
                    '"'|| interfazRecord(x).tip_carg || '"' || ',' ||
                    '"'|| interfazRecord(x).fec_carg || '"' || ',' ||
                    '"'|| interfazRecord(x).imp_carg || '"' || ',' ||
                    '"'|| interfazRecord(x).imp_sald || '"' ;

            utl_file.put_line(lv_handle, lv_line );
      END LOOP;
    END IF;
    EXIT WHEN c_interfaz%NOTFOUND;
 END LOOP;
 CLOSE c_interfaz;

    utl_file.fclose(lv_handle);

 RETURN;

 EXCEPTION
  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR CCC_PR_ARCHI_SALDO_PENDI_CSV: '||ls_sqlerrm);

 END CCC_PR_REPOR_SALDO_PENDI_CSV;

 PROCEDURE CCC_PR_GENER_CORRI_CONSU(
  p_cod_pais                   IN       seg_pais.cod_pais%TYPE,
  p_cod_soci                   IN       seg_socie.cod_soci%TYPE,
  p_cod_regi                   IN       zon_regio.cod_regi%TYPE)
 IS

      lv_oid_pais                 seg_pais.oid_pais%TYPE;
      lv_oid_soci                 seg_socie.oid_soci%TYPE;
      lv_sald_clie                NUMBER(12, 2);
      lv_imp_mcc                  NUMBER(12, 2);
      lv_imp_ban                  NUMBER(12, 2);

      CURSOR c_base IS
      SELECT
          zr.cod_regi,
          zz.cod_zona,
          mc.cod_clie,
          mc.cod_digi_ctrl,
          mc.oid_clie,
          trim(mc.val_nom1) || ' ' || trim(mc.val_nom2) || ' ' ||
          trim(mc.val_ape1) || ' ' || trim(mc.val_ape2) nom_clie
       FROM
          mae_clien                       mc,
          mae_clien_unida_admin           mcua,
          zon_terri_admin                 zta,
          zon_secci                       zs,
          zon_zona                        zz,
          zon_regio                       zr
       WHERE mc.oid_clie = mcua.clie_oid_clie
         AND mcua.ztad_oid_terr_admi = zta.oid_terr_admi
         AND mcua.ind_acti = 1
         AND zta.zscc_oid_secc = zs.oid_secc
         AND zs.zzon_oid_zona = zz.oid_zona
         AND zz.zorg_oid_regi = zr.oid_regi
         AND zr.cod_regi = p_cod_regi
         AND NOT EXISTS
               (SELECT NULL
                 FROM ccc_clien_casti c
                 WHERE  c.oid_clie = mc.oid_clie);

     TYPE cBaseTipo IS RECORD (
       cod_regi            zon_regio.cod_regi%TYPE,
       cod_zona            zon_zona.cod_zona%TYPE,
       cod_clie            mae_clien.cod_clie%TYPE,
       cod_digi_ctrl       mae_clien.cod_digi_ctrl%TYPE,
       oid_clie            mae_clien.oid_clie%TYPE,
       nom_clie            VARCHAR2(400)
      );

     TYPE cBaseTab  IS TABLE OF cBaseTipo ;
     cBaseRec       cBaseTab;
     lnFilas        INTEGER := 5000;

   BEGIN

      lv_oid_pais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(p_cod_pais);
      lv_oid_soci := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_SOCIE(p_cod_soci);

      EXECUTE IMMEDIATE 'TRUNCATE TABLE CCC_REPOR_CUENT_CORRI_CONSU';

      OPEN C_BASE;
      LOOP
       FETCH c_base BULK COLLECT INTO cBaseRec LIMIT lnFilas;
       IF cBaseRec.COUNT > 0 THEN
          FOR x IN cBaseRec.FIRST .. cBaseRec.LAST LOOP

              SELECT SUM(mcc.imp_pend)
              INTO lv_imp_mcc
              FROM ccc_movim_cuent_corri  mcc
              WHERE mcc.clie_oid_clie = cBaseRec(x).oid_clie
                AND mcc.imp_pend <> 0;

              SELECT SUM(mb.imp_sald_pend)
              INTO lv_imp_ban
              FROM ccc_movim_banca mb
              WHERE mb.clie_oid_clie = cBaseRec(x).oid_clie
                AND mb.cod_iden_proc = 'P'
                AND mb.imp_sald_pend <> 0;

              lv_sald_clie := NVL(lv_imp_mcc, 0) - NVL(lv_imp_ban, 0);

              INSERT INTO ccc_repor_cuent_corri_consu
                SELECT
                       rownum,
                       cBaseRec(x).cod_regi,
                       cBaseRec(x).cod_zona,
                       cBaseRec(x).cod_clie,
                       cBaseRec(x).cod_digi_ctrl,
                       cBaseRec(x).nom_clie,
                       fec_emis_docu,
                       cam_refe,
                       tip_movi,
                       num_docu,
                       fec_venc,
                       fec_pago,
                       imp_carg,
                       imp_abon,
                       lv_sald_clie,
                       NULL
                  FROM (SELECT TO_CHAR(FEC_EMIS, 'dd/MM/yyyy') FEC_EMIS_DOCU,
                               NVL(TO_CHAR(CAM_REFE), ' ') CAM_REFE,
                               TIP_MOVI,
                               SOCA_OID_SOLI_CABE,
                               NUM_DOCU,
                               NVL(TO_CHAR(FEC_VENC, 'dd/MM/yyyy'), ' ') FEC_VENC,
                               NVL(TO_CHAR(FEC_PAGO, 'dd/MM/yyyy'), ' ') FEC_PAGO,
                               IMP_CARG,
                               IMP_ABON
                          FROM (SELECT FEC_EMIS,
                                       CAM_REFE,
                                       TIP_MOVI,
                                       SOCA_OID_SOLI_CABE,
                                       NUM_DOCU,
                                       FEC_VENC,
                                       FEC_PAGO,
                                       SUM(IMP_CARG) IMP_CARG,
                                       SUM(IMP_ABON) IMP_ABON
                                  FROM ((SELECT MCC.FEC_DOCU FEC_EMIS,
                                                SPC.COD_PERI CAM_REFE,
                                                CASE
                                                  WHEN MCC.TCAB_OID_TCAB_CREA = 2001 THEN
                                                    CASE
                                                    WHEN EXISTS
                                                      (SELECT OID_SOLI_CABE
                                                       FROM PED_SOLIC_CABEC P,
                                                            PED_TIPO_SOLIC_PAIS TP,
                                                            PED_TIPO_SOLIC      T
                                                       WHERE P.SOCA_OID_SOLI_CABE =
                                                               MCC.SOCA_OID_SOLI_CABE
                                                       AND P.TSPA_OID_TIPO_SOLI_PAIS =
                                                               TP.OID_TIPO_SOLI_PAIS
                                                       AND TP.TSOL_OID_TIPO_SOLI =
                                                               T.OID_TIPO_SOLI
                                                       AND P.IND_OC = 1
                                                       AND T.IND_SOLI_NEGA = 0) THEN
                                                      'Pedido'
                                                    ELSE
                                                      CASE
                                                      WHEN MCC.IMP_MOVI > 0 THEN
                                                        'Atencion de Servicio'
                                                      ELSE
                                                         NVL( ( SELECT 'C' || PERIOCORPO.COD_PERI || ' ' ||
                                                                 OPERA.VAL_DESC_LARG || ' NRO.' ||
                                                                 CABECRECLA.NUM_RECL AS DESCRIP
                                                                 FROM PED_SOLIC_CABEC CONS,
                                                                      PED_SOLIC_CABEC SOLICRECLA,
                                                                      PED_SOLIC_CABEC SOLICORIGEN,
                                                                      REC_SOLIC_OPERA SOLICOPERA,
                                                                      REC_OPERA_RECLA OPERARECLA,
                                                                      REC_CABEC_RECLA CABECRECLA,
                                                                      REC_TIPOS_OPERA TIPOSOPERA,
                                                                      REC_OPERA       OPERA,
                                                                      CRA_PERIO       PERIO,
                                                                      SEG_PERIO_CORPO PERIOCORPO
                                                                WHERE CONS.OID_SOLI_CABE =
                                                                      MCC.SOCA_OID_SOLI_CABE
                                                                  AND CONS.OID_SOLI_CABE =
                                                                      SOLICRECLA.SOCA_OID_SOLI_CABE
                                                                  AND SOLICRECLA.SOCA_OID_DOCU_REFE IS NOT NULL
                                                                  AND SOLICRECLA.OID_SOLI_CABE =
                                                                      SOLICOPERA.SOCA_OID_SOLI_CABE
                                                                  AND SOLICOPERA.OPRE_OID_OPER_RECL =
                                                                      OPERARECLA.OID_OPER_RECL
                                                                  AND OPERARECLA.CARE_OID_CABE_RECL =
                                                                      CABECRECLA.OID_CABE_RECL
                                                                  AND OPERARECLA.TIOP_OID_TIPO_OPER =
                                                                      TIPOSOPERA.OID_TIPO_OPER
                                                                  AND TIPOSOPERA.ROPE_OID_OPER =
                                                                      OPERA.OID_OPER
                                                                  AND SOLICRECLA.SOCA_OID_DOCU_REFE =
                                                                      SOLICORIGEN.OID_SOLI_CABE
                                                                  AND SOLICORIGEN.PERD_OID_PERI =
                                                                      PERIO.OID_PERI
                                                                  AND PERIO.PERI_OID_PERI =
                                                                      PERIOCORPO.OID_PERI
                                                                  AND ROWNUM = 1 ), 'CDR' )
                                                      END
                                                    END
                                                ELSE
                                                    GEN.VAL_I18N
                                                END
                                                TIP_MOVI,
                                                MCC.SOCA_OID_SOLI_CABE SOCA_OID_SOLI_CABE,
                                                MCC.NUM_IDEN_CUOT NUM_DOCU,
                                                MCC.FEC_VENC FEC_VENC,
                                                NULL FEC_PAGO,
                                                CASE
                                                  WHEN (MCC.IMP_MOVI > 0) THEN
                                                   MCC.IMP_MOVI
                                                  ELSE
                                                   NULL
                                                END IMP_CARG,
                                                CASE
                                                  WHEN (MCC.IMP_MOVI > 0) THEN
                                                   NULL
                                                  ELSE
                                                   ABS(MCC.IMP_MOVI)
                                                END IMP_ABON
                                           FROM CCC_MOVIM_CUENT_CORRI MCC,
                                                CCC_SUBPR             CS,
                                                CCC_TIPO_ABONO_SUBPR  TAS,
                                                CCC_TIPO_CARGO_ABONO  TCA,
                                                GEN_I18N_SICC_PAIS    GEN,
                                                CRA_PERIO             CP,
                                                SEG_PERIO_CORPO       SPC
                                          WHERE MCC.SUBP_OID_SUBP_CREA = CS.OID_SUBP
                                            AND CS.OID_SUBP = TAS.SUBP_OID_SUBP
                                            AND TAS.TCAB_OID_TCAB =
                                                TCA.OID_TIPO_CARG_ABON
                                            AND GEN.ATTR_ENTI LIKE
                                                'CCC_TIPO_CARGO_ABONO'
                                            AND GEN.VAL_OID = TCA.OID_TIPO_CARG_ABON
                                            AND MCC.PERD_OID_PERI = CP.OID_PERI
                                            AND CP.PERI_OID_PERI = SPC.OID_PERI
                                            AND MCC.CLIE_OID_CLIE = cBaseRec(x).OID_CLIE)
                                        UNION ALL
                                        (SELECT MB.FEC_PROC      FEC_EMIS,
                                                NULL             OID_PERI,
                                                CCB.DES_CC       TIP_MOVI,
                                                MB.OID_MOVI_BANC SOCA_OID_SOLI_CABE,
                                                MB.NUM_LOTE      NUM_DOCU,
                                                NULL             FEC_VENC,
                                                MB.FEC_PAGO      FEC_PAGO,
                                                NULL             IMP_CARG,
                                                MB.IMP_PAGO      IMP_ABON
                                           FROM
                                                ccc_movim_banca       mb,
                                                ccc_cuent_corri_banca ccb
                                          WHERE mb.ccba_oid_cc_banc =
                                                ccb.oid_cuen_corr_banc
                                              AND mb.pais_oid_pais = lv_oid_pais
                                              AND mb.clie_oid_clie = cBaseRec(x).oid_clie
                                              AND mb.soci_oid_soci = lv_oid_soci
                                            AND mb.cod_iden_proc = 'P' ) )
                                 GROUP BY fec_emis,
                                          cam_refe,
                                          tip_movi,
                                          soca_oid_soli_cabe,
                                          num_docu,
                                          fec_venc,
                                          fec_pago )
                         ORDER BY fec_emis DESC )
                 WHERE ROWNUM <= 20;


          END LOOP;
          COMMIT;
       END IF;
       EXIT WHEN c_base%NOTFOUND;
     END LOOP;
    CLOSE c_base;

    COMMIT;

    UPDATE ccc_repor_cuent_corri_consu c
    SET c.imp_sald = (SELECT NVL(x.imp_carg, 0) * -1 + NVL(x.imp_abon, 0) + NVL(x.imp_sald, 0)
                      FROM ccc_repor_cuent_corri_consu x
                      WHERE x.cod_clie = c.cod_clie
                        AND x.num_oper = c.num_oper - 1)
    WHERE c.num_oper <> 1;

 END CCC_PR_GENER_CORRI_CONSU  ;

   /**************************************************************************
     Descripcion       : Genera la data para el reporte de Buro de Credito
     Fecha Creacion    : 14/03/2012
     Autor             : Jose Luis Rodriguez
   ***************************************************************************/
 PROCEDURE CCC_PR_GENER_BURO_CREDI (
  p_cod_pais                       IN   seg_pais.cod_pais%TYPE )
 IS

 CURSOR cur
 IS
  SELECT *
  FROM ccc_buro_credi_detal_deuda;

  TYPE buro_credi_tab_t IS TABLE OF ccc_buro_credi_detal_deuda%ROWTYPE INDEX BY BINARY_INTEGER;
    buro_credi_tab buro_credi_tab_t;

    rows NATURAL := 1000; -- Number of rows to process at a time
    i    BINARY_INTEGER := 0;

    vnNumeroDiasDeuda NUMBER;
    vnEdadDeuda       NUMBER;
    vnEdadDeudaFin    NUMBER;
    valAcreedor       VARCHAR2(50);
    valIndPaisMarc    bas_pais.ind_pais_marc%TYPE;
    vsCodTipoDocu     mae_tipo_docum.cod_tipo_docu%TYPE;
    vsNumDocu         VARCHAR2(30);
    vnOidPais         NUMBER(12);
    vsIndTipoDocu     VARCHAR2(1);
    vnCodDNI          VARCHAR2(2);
    vnCodRUC          VARCHAR2(2);
    vnOidRegion       NUMBER(12);
    vnImporteOriginal NUMBER;
    vnIndicadorVOC    NUMBER;
    vnValorVOC        NUMBER;
    vnIndicadorVV     NUMBER;
    vnValorVV         NUMBER;
    vnIndicadorVXV    NUMBER;
    vnValorVXV        NUMBER;

  BEGIN

   vnOidPais := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(p_cod_pais);

   -- Obteniendo el numero de dias maximos para considerra una deuda
   SELECT to_number(a.val_para)
   INTO vnNumeroDiasDeuda
   FROM ccc_param_gener a
   WHERE a.cod_para = 'BURO_01';

   -- Obteniendo el numero de dias para la edad de la deuda
   SELECT to_number(a.val_para)
   INTO vnEdadDeuda
   FROM ccc_param_gener a
   WHERE a.cod_para = 'BURO_02';

   -- Obteniendo el numero de dias para la edad de la deuda
   SELECT to_number(a.val_para)
   INTO vnEdadDeudaFin
   FROM ccc_param_gener a
   WHERE a.cod_para = 'BURO_03';

   -- Obteniendo el numero de importe original
   SELECT to_number(a.val_para)
   INTO vnImporteOriginal
   FROM ccc_param_gener a
   WHERE a.cod_para = 'BURO_04';

   -- Obteniendo el Indicador filtro por Valor Original Concedido
   SELECT to_number(a.val_para)
   INTO vnIndicadorVOC
   FROM ccc_param_gener a
   WHERE a.cod_para = 'BURO_05';

   -- Obteniendo el Indicador filtro por Valor Vencido
   SELECT TO_NUMBER(A.VAL_PARA)
   INTO vnIndicadorVV
   FROM CCC_PARAM_GENER A
   WHERE A.COD_PARA = 'BURO_06';

   -- Obteniendo el Indicador filtro por Valor por Vencer"
   SELECT TO_NUMBER(A.VAL_PARA)
   INTO vnIndicadorVXV
   FROM CCC_PARAM_GENER A
   WHERE A.COD_PARA = 'BURO_07';

   -- Obteniendo el numero de dias para la edad de la deuda
   SELECT a.val_para
   INTO vnCodDNI
   FROM ccc_param_gener a
   WHERE a.cod_para = 'DNI_BURO';

   -- Obteniendo el codigo de documento para el RUC
   SELECT a.val_para
   INTO vnCodRUC
   FROM ccc_param_gener a
   WHERE a.cod_para = 'RUC_BURO';

   -- Obteniendo el oid de la Region Oficina
   SELECT to_number(a.val_para)
   INTO vnOidRegion
   FROM ccc_param_gener a
   WHERE a.cod_para = 'REGION_BURO';

   --Obteniendo la marca del pais
   SELECT ind_pais_marc
   INTO valIndPaisMarc
   FROM bas_pais
   WHERE cod_pais = p_cod_pais;

   IF ( valIndPaisMarc = 'ES') THEN
    valAcreedor := 'Grupo Transbel - Esika';
   ELSIF ( valIndPaisMarc = 'LB') THEN
    valAcreedor := 'Grupo Transbel - Lbel';
   END IF;

   EXECUTE IMMEDIATE 'truncate table ccc_buro_credi_detal_deuda';

   INSERT INTO ccc_gtt_buro_credi_detal_deuda(
      perd_oid_peri,
      cod_peri,
      cod_peri_ulti,
      clie_oid_clie,
      fec_docu,
      fec_docu_ulti,
      imp_movi,
      imp_paga,
      imp_pend,
      num_iden_cuot,
      imp_pago,
      soca_oid_soli_cabe,
      tspa_oid_tipo_soli_pais,
      ind_sele_info,
      val_edad_deuda,
      ind_tipo_deud,
      ind_tipo_docu,
      num_docu_iden,
      val_nomb,
      val_dire,
      val_dist,
      val_tele,
      val_acre)
    SELECT
     ped.perd_oid_peri,    -----ojo
     spc.cod_peri,
     NULL  COD_PERI_ULTI,
     mcc.clie_oid_clie,
     mcc.fec_docu,
     NULL  FEC_DOCU_ULTI,
     0,    ---  ojo  SUM(mcc.imp_movi),
     0,    ---  ojo SUM(mcc.imp_paga),
     mcc.imp_pend,
     mcc.num_iden_cuot,
     0,
     mcc.soca_oid_soli_cabe,
     mcc.tspa_oid_tipo_soli_pais,
     NULL  IND_SELE_INFO,
     NULL  VAL_EDAD_DEUDA,
     NULL  IND_TIPO_DEUD,
     NULL  IND_TIPO_DOCU,
     NULL NUM_DOCUMENTO,
           (SELECT TRIM(mc.val_ape1) || ' ' || trim(mc.val_ape2) || ' ' || trim(mc.val_nom1) || ' ' || trim(mc.val_nom2)
              FROM mae_clien mc
             WHERE mc.oid_clie = mcc.clie_oid_clie) NOMBRE,
     gen_pkg_gener.GEN_FN_CLIEN_DATOS_OID(mcc.clie_oid_clie, 'DIR_CLIE') DIRECCION,
     gen_pkg_gener.GEN_FN_CLIEN_DATOS_OID(mcc.clie_oid_clie, 'DES_DIST') DISTRITO,
     substr(gen_pkg_gener.GEN_FN_CLIEN_DATOS_OID(mcc.clie_oid_clie, 'NUM_TELE'),0,50) TELEFONO,
     valAcreedor  ACREEDOR
    FROM
     ccc_movim_cuent_corri mcc,
     cra_perio cp,
     seg_perio_corpo spc,
     ped_solic_cabec  ped
    WHERE mcc.perd_oid_peri = cp.oid_peri
      AND cp.peri_oid_peri = spc.oid_peri
      AND mcc.imp_pend <> 0
      ---  ojo   AND mcc.imp_movi > vnImporteOriginal
      AND TRUNC(SYSDATE) - mcc.fec_docu  > vnNumeroDiasDeuda
      AND TRUNC(SYSDATE) - mcc.fec_docu < vnEdadDeudaFin
      AND mcc.zorg_oid_regi <> vnOidRegion
      AND ped.clie_oid_clie =  mcc.clie_oid_clie
      AND ped.soca_oid_soli_cabe = mcc.soca_oid_soli_cabe
      AND ped.ind_oc = 1
      AND mcc.tspa_oid_tipo_soli_pais NOT IN (SELECT BPP.VAL_PARA
                                                FROM BAS_PARAM_PAIS BPP
                                               WHERE BPP.COD_PAIS = p_cod_pais
                                                 AND BPP.COD_SIST = 'CCC_BURO'
                                                 AND BPP.COD_PARA LIKE 'S%'
                                                 AND BPP.Ind_Acti = '1');

   COMMIT;

   /* obteniendo totales */

    INSERT INTO ccc_buro_credi_detal_deuda
    SELECT
      perd_oid_peri,
      cod_peri,
      NULL,
      clie_oid_clie,
      fec_docu,
      NULL,
      0,
      0,
      SUM(imp_pend),
      num_iden_cuot,
      0,
      soca_oid_soli_cabe,
      tspa_oid_tipo_soli_pais,
      NULL,
      NULL,
      NULL,
      NULL,
      NULL,
      val_nomb,
      val_dire,
      val_dist,
      val_tele,
      val_acre
   FROM ccc_gtt_buro_credi_detal_deuda
   GROUP BY
      perd_oid_peri,
      cod_peri,
      clie_oid_clie,
      fec_docu,
      num_iden_cuot,
      soca_oid_soli_cabe,
      tspa_oid_tipo_soli_pais,
      val_nomb,
      val_dire,
      val_dist,
      val_tele,
      val_acre;

   /*
   INSERT INTO ccc_buro_credi_detal_deuda
    SELECT
     ped.perd_oid_peri,    -----ojo
     spc.cod_peri,
     NULL  COD_PERI_ULTI,
     mcc.clie_oid_clie,
     mcc.fec_docu,
     NULL  FEC_DOCU_ULTI,
     0,    ---  ojo  SUM(mcc.imp_movi),
     0,    ---  ojo SUM(mcc.imp_paga),
     SUM(mcc.imp_pend),
     mcc.num_iden_cuot,
     0,
     mcc.soca_oid_soli_cabe,
     mcc.tspa_oid_tipo_soli_pais,
     NULL  IND_SELE_INFO,
     NULL  VAL_EDAD_DEUDA,
     NULL  IND_TIPO_DEUD,
     NULL  IND_TIPO_DOCU,
     NULL NUM_DOCUMENTO,
           (SELECT TRIM(mc.val_ape1) || ' ' || trim(mc.val_ape2) || ' ' || trim(mc.val_nom1) || ' ' || trim(mc.val_nom2)
              FROM mae_clien mc
             WHERE mc.oid_clie = mcc.clie_oid_clie) NOMBRE,
     gen_pkg_gener.GEN_FN_CLIEN_DATOS_OID(mcc.clie_oid_clie, 'DIR_CLIE') DIRECCION,
     gen_pkg_gener.GEN_FN_CLIEN_DATOS_OID(mcc.clie_oid_clie, 'DES_DIST') DISTRITO,
     substr(gen_pkg_gener.GEN_FN_CLIEN_DATOS_OID(mcc.clie_oid_clie, 'NUM_TELE'),0,50) TELEFONO,
     valAcreedor  ACREEDOR
    FROM
     ccc_movim_cuent_corri mcc,
     cra_perio cp,
     seg_perio_corpo spc,
     ped_solic_cabec  ped
    WHERE mcc.perd_oid_peri = cp.oid_peri
      AND cp.peri_oid_peri = spc.oid_peri
      AND mcc.imp_pend <> 0
      ---  ojo   AND mcc.imp_movi > vnImporteOriginal
      AND TRUNC(SYSDATE) - mcc.fec_docu  > vnNumeroDiasDeuda
      AND TRUNC(SYSDATE) - mcc.fec_docu < vnEdadDeudaFin
      AND mcc.zorg_oid_regi <> vnOidRegion
      AND ped.clie_oid_clie =  mcc.clie_oid_clie
      AND ped.soca_oid_soli_cabe = mcc.soca_oid_soli_cabe
      AND ped.ind_oc = 1
      AND mcc.tspa_oid_tipo_soli_pais NOT IN (SELECT BPP.VAL_PARA
                                                FROM BAS_PARAM_PAIS BPP
                                               WHERE BPP.COD_PAIS = p_cod_pais
                                                 AND BPP.COD_SIST = 'CCC_BURO'
                                                 AND BPP.COD_PARA LIKE 'S%'
                                                 AND BPP.Ind_Acti = '1')
  GROUP BY ped.perd_oid_peri,   ---  ojo
           spc.cod_peri,
           mcc.clie_oid_clie,
           mcc.fec_docu,
           mcc.num_iden_cuot,
           mcc.soca_oid_soli_cabe,
           mcc.tspa_oid_tipo_soli_pais;
    */

----  ojo :
  -- Actualizar  Total  Pedido y  Total Pagado
  UPDATE  CCC_BURO_CREDI_DETAL_DEUDA wr
      SET  ( WR.IMP_MOVI , WR.IMP_PAGA )  =
       (  select   sum(imp_movi), sum(imp_paga)
       from   ccc_movim_cuent_corri cc
       where  CC.CLIE_OID_CLIe  =  WR.CLIE_OID_CLIE
       and CC.SOCA_OID_SOLI_CABE  =  WR.SOCA_OID_SOLI_CABE
        )   ;

---  OJO:
  --  Eliminar casos con Importe Original menor a  parametro "BURO_04"
    DELETE FROM ccc_buro_credi_detal_deuda wr
     WHERE wr.imp_movi < vnImporteOriginal ;

  -- Actualizando los pedidos con el maximo periodo, la maxima fecha
  UPDATE ccc_buro_credi_detal_deuda  wr
     SET wr.cod_peri_ulti = (SELECT MAX(wx.cod_peri)
                               FROM ccc_buro_credi_detal_deuda wx
                              WHERE wx.clie_oid_clie = wr.clie_oid_clie),
         wr.fec_docu_ulti = (SELECT MAX(wx.fec_docu)
                               FROM ccc_buro_credi_detal_deuda wx
                              WHERE wx.clie_oid_clie = wr.clie_oid_clie),
         wr.ind_tipo_deud =  CASE
                               WHEN wr.val_edad_deuda < vnEdadDeuda  THEN 1
                               ELSE 2
                             END;

  DELETE FROM ccc_buro_credi_detal_deuda wr
   WHERE wr.cod_peri <> wr.cod_peri_ulti;

  DELETE FROM ccc_buro_credi_detal_deuda wr
   WHERE wr.fec_docu <> wr.fec_docu_ulti;

  -- Actualizando la edad  de deuda a  fecha  actual con desplazamiento de vnEdadDeuda dias.
  UPDATE ccc_buro_credi_detal_deuda  wr
     SET wr.val_edad_deuda = TRUNC(SYSDATE) - wr.fec_docu - vnEdadDeuda,
         wr.ind_tipo_deud  = 2;

  -- Actualizando  la edad  deuda a cero  para  casos con edad  original menores que vnEdadDeuda
  UPDATE ccc_buro_credi_detal_deuda  wr
     SET wr.val_edad_deuda = 0,
         wr.ind_tipo_deud  = 1
  WHERE  wr.val_edad_deuda  < 1;

  IF ( vnIndicadorVOC = 1) THEN

    -- Obteniendo el valor del filtro por Valor Original Concedido
    SELECT to_number(a.val_para)
      INTO vnValorVOC
      FROM ccc_param_gener a
     WHERE a.cod_para = 'BURO_05_V01';

    DELETE FROM ccc_buro_credi_detal_deuda wr
     WHERE wr.imp_movi < vnValorVOC;

  END IF;

  IF ( vnIndicadorVV = 1) THEN

    -- Obteniendo el valor del filtro por Valor Vencido
    SELECT TO_NUMBER(A.VAL_PARA)
      INTO vnValorVV
      FROM CCC_PARAM_GENER A
     WHERE A.COD_PARA = 'BURO_06_V01';

    DELETE FROM ccc_buro_credi_detal_deuda wr
     WHERE wr.imp_pend < vnValorVV
       AND wr.ind_tipo_deud = 2;

  END IF;

  IF ( vnIndicadorVXV = 1) THEN

    -- Obteniendo el Indicador filtro por Valor por Vencer"
    SELECT TO_NUMBER(A.VAL_PARA)
      INTO vnValorVXV
      FROM CCC_PARAM_GENER A
     WHERE A.COD_PARA = 'BURO_07_V01';

    DELETE FROM ccc_buro_credi_detal_deuda wr
     WHERE wr.imp_paga < vnValorVXV
       AND wr.ind_tipo_deud = 1;

  END IF;

  OPEN cur;
    LOOP

      -- Bulk collect data into memory table - X rows at a time
      FETCH cur BULK COLLECT
        INTO buro_credi_tab LIMIT rows;
      EXIT WHEN buro_credi_tab.count = 0;

      FOR i IN buro_credi_tab.first .. buro_credi_tab.last
      LOOP

        vsIndTipoDocu := '';
        vsNumDocu := '';
        vsCodTipoDocu := FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_DOCUM_IDENT( vnOidPais, buro_credi_tab(i).clie_oid_clie );

        IF( vsCodTipoDocu = vnCodDNI ) THEN
          vsIndTipoDocu  := 'C';
          vsNumDocu  := lpad(FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_DOCUM_IDENT (buro_credi_tab(i).clie_oid_clie), 10, 0);
        END IF;

        IF (vsCodTipoDocu = vnCodRUC) THEN
          vsIndTipoDocu  := 'R';
          vsNumDocu := lpad(FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_DOCUM_IDENT (buro_credi_tab(i).clie_oid_clie), 13, 0);
        END IF;

        UPDATE ccc_buro_credi_detal_deuda
           SET ind_tipo_docu = vsIndTipoDocu,
               num_docu_iden = vsNumDocu
         WHERE clie_oid_clie = buro_credi_tab(i).clie_oid_clie ;

      END LOOP;

    END LOOP;
  CLOSE cur;

 EXCEPTION

  WHEN OTHERS THEN
   ln_sqlcode := SQLCODE;
   ls_sqlerrm := substr(sqlerrm,1,250);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR CCC_PR_GENER_BURO_CREDI: '||ls_sqlerrm);

 END CCC_PR_GENER_BURO_CREDI;

 /**************************************************************************
     Descripcion       : Genera la data para el reporte de Ventas Detalles SII
     Fecha Creacion    : 03/07/2012
     Autor             : Giovanni Ascarza
       Parametros        :
              p_fecha_desde : fecha comienzo
              p_fecha_hasta : fecha fin
              p_accion : 0 boleta 1 nota

   ***************************************************************************/
      PROCEDURE CCC_PR_VENTAS_DETALLE_SII(psCodigoPais       IN  VARCHAR2,
                                      psFechaDesde       IN  VARCHAR2,
                                      psFechaHasta       IN  VARCHAR2,
                                      psAccion           IN  VARCHAR2,
                                      psDirectorio       OUT VARCHAR2,
                                      psNota1            OUT VARCHAR2,
                                      psNota2            OUT VARCHAR2,
                                      psBoleta1          OUT VARCHAR2,
                                      psBoleta2          OUT VARCHAR2) AS

  v_handle            UTL_FILE.FILE_TYPE;
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  lbabrirutlfileb1 BOOLEAN;
  lbabrirutlfileb2 BOOLEAN;
  lbabrirutlfilen1 BOOLEAN;
  lbabrirutlfilen2 BOOLEAN;
  v_nombre_archivo VARCHAR2(30);
  v_titulo VARCHAR2(30);
  v_archivo varchar(50);


    TYPE rec_nota1 IS RECORD
   (
     cod_peri                PED_NOTA_CREDI_ELECT_HISTO.cod_peri%TYPE,
     cod_clie                PED_NOTA_CREDI_ELECT_HISTO.cod_clie%TYPE,
     cod_terr                PED_NOTA_CREDI_ELECT_HISTO.cod_terr%TYPE,
     val_nomb                PED_NOTA_CREDI_ELECT_HISTO.val_nomb%TYPE,
     num_docu_iden           PED_NOTA_CREDI_ELECT_HISTO.num_docu_iden%TYPE,
     val_dire                PED_NOTA_CREDI_ELECT_HISTO.val_dire%TYPE,
     val_comu                PED_NOTA_CREDI_ELECT_HISTO.val_comu%TYPE,
     val_vill                PED_NOTA_CREDI_ELECT_HISTO.val_vill%TYPE,
     fec_proc                PED_NOTA_CREDI_ELECT_HISTO.fec_proc%TYPE,
     val_nume_soli           PED_NOTA_CREDI_ELECT_HISTO.val_nume_soli%TYPE,
     num_recl                PED_NOTA_CREDI_ELECT_HISTO.num_recl%TYPE,
     val_codi_vent           PED_NOTA_CREDI_ELECT_HISTO.val_codi_vent%TYPE,
     num_unid              	 PED_NOTA_CREDI_ELECT_HISTO.num_unid%TYPE,
     cod_prod                PED_NOTA_CREDI_ELECT_HISTO.cod_prod%TYPE,
     des_prod                PED_NOTA_CREDI_ELECT_HISTO.des_prod%TYPE,
     val_prec_sin_impu       PED_NOTA_CREDI_ELECT_HISTO.val_prec_sin_impu%TYPE,
     val_impo_impu           PED_NOTA_CREDI_ELECT_HISTO.val_impo_impu%TYPE,
     val_prec_tota           PED_NOTA_CREDI_ELECT_HISTO.val_prec_tota%TYPE,
     val_foli_refe           PED_NOTA_CREDI_ELECT_HISTO.val_foli_refe%TYPE,
     val_fech_refe           PED_NOTA_CREDI_ELECT_HISTO.val_fech_refe%TYPE,
     val_tipo_refe			 PED_NOTA_CREDI_ELECT_HISTO.val_tipo_refe%TYPE,
     val_foli                PED_NOTA_CREDI_ELECT_HISTO.val_foli%TYPE
     );

  TYPE r_nota1  IS TABLE OF rec_nota1 ;
   record_nota1 r_nota1;


  TYPE rec_nota2 IS RECORD
   (
    fecha         varchar2(10),
    hora          varchar2(10),
    minimo        varchar2(9),
    maximo        varchar2(9),
    suma          number(12,2)

    );

   TYPE r_nota2  IS TABLE OF rec_nota2 ;
   record_nota2 r_nota2;

      TYPE rec_boleta1 IS RECORD
   (
       contador         NUMBER(10),
       cod_peri   		PED_BOLET_ELECT_HISTO.cod_peri%type,
       cod_clie   		PED_BOLET_ELECT_HISTO.cod_clie%type,
       cod_terr   		PED_BOLET_ELECT_HISTO.cod_terr%type,
       fec_proc  		VARCHAR2(8),
       val_nume_soli    PED_BOLET_ELECT_HISTO.val_nume_soli%type,
       val_codi_vent    PED_BOLET_ELECT_HISTO.val_codi_vent%type,
       val_desc_prod	PED_BOLET_ELECT_HISTO.val_desc_prod%type,
       num_unid			PED_BOLET_ELECT_HISTO.num_unid%type,
       total 			NUMBER(12,2),
       val_foli			PED_BOLET_ELECT_HISTO.val_foli%type
     );

   TYPE r_boleta1  IS TABLE OF rec_boleta1 ;
   record_boleta1 r_boleta1;


  TYPE rec_boleta2 IS RECORD
   (
    fecha         varchar2(10),
    hora          varchar2(10),
    minimo        varchar2(9),
    maximo        varchar2(9),
    suma          number(12,2)

    );

   TYPE r_boleta2  IS TABLE OF rec_boleta2 ;
   record_boleta2 r_boleta2;

 /* Variables de parametros */
  lsLinea          VARCHAR2(32767);

 --nota
 cursor cur_nota1 is
   select
       nc.cod_peri,
       nc.cod_clie,
       nc.cod_terr,
       nc.val_nomb,
       nc.num_docu_iden,
       nc.val_dire,
       nc.val_comu,
       nc.val_vill,
       nc.fec_proc,
       nc.val_nume_soli,
       nc.num_recl,
       nc.val_codi_vent,
       nc.num_unid,
       nc.cod_prod,
       nc.des_prod,
       nc.val_prec_sin_impu,
       nc.val_impo_impu,
       nc.val_prec_tota,
       nc.val_foli_refe,
       nc.val_fech_refe,
       nc.val_tipo_refe,
       nc.val_foli
from  PED_NOTA_CREDI_ELECT_HISTO nc
where to_char(nc.fec_proc,'yyyymmdd') >= psFechaDesde
and to_char(nc.fec_proc,'yyyymmdd') <= psFechaHasta;


   cursor cur_nota2 is
   select to_char(be.fec_proc,'dd/mm/yyyy'),
          to_char(be.fec_proc,'hh:mi:ss'),
          min(be.val_foli) ,
          max(be.val_foli) ,
          sum(be.num_unid * be.val_prec_cata)
     from PED_NOTA_CREDI_ELECT_HISTO be
    where to_char(be.fec_proc,'yyyymmdd') >= psFechaDesde
      and to_char(be.fec_proc,'yyyymmdd') <= psFechaHasta
 group by be.fec_proc;

--boleta
 cursor cur_boleta1 is
  select rownum contador,
       be.cod_peri,
       be.cod_clie,
       be.cod_terr,
       TO_CHAR(be.fec_proc, 'YYYYMMDD'),
       be.val_nume_soli,
       be.val_codi_vent,
       be.val_desc_prod,
       be.num_unid,
       be.num_unid * be.val_prec_cata total,
       be.val_foli
from PED_BOLET_ELECT_HISTO be
where to_char(be.fec_proc,'yyyymmdd') >= psFechaDesde
  and to_char(be.fec_proc,'yyyymmdd') <= psFechaHasta;



  cursor cur_boleta2 is
  select to_char(be.fec_proc,'YYYYMMDD'),
         to_char(be.fec_proc,'HH24MISS'),
         min(be.val_foli) ,
         max(be.val_foli) ,
         sum(be.num_unid * be.val_prec_cata)
from PED_BOLET_ELECT_HISTO be
where to_char(be.fec_proc,'yyyymmdd') >= psFechaDesde
  and to_char(be.fec_proc,'yyyymmdd') <= psFechaHasta
group by be.fec_proc;


begin
  lbabrirutlfileb1 := TRUE;
  lbabrirutlfileb2 := TRUE;
  lbabrirutlfilen1 := TRUE;
  lbabrirutlfilen2 := TRUE;

    if psAccion = '0' then

    v_archivo := '';

    --Creando el archivo nota1
     IF lbabrirutlfilen1 THEN
       --nombre de archivo
      select to_char(sysdate, 'yyyymmddhh24miss')
        into v_nombre_archivo
        from dual;

     v_archivo := 'nota1_' || v_nombre_archivo;
     v_titulo := '';
     gen_pkg_inter_archi.gen_pr_inici_repor_oracl(pscodigopais,
                                                     v_archivo,
                                                     '.TXT',
                                                     v_titulo,
                                                     lsdirtempo,
                                                     v_handle);
        psdirectorio   := lsdirtempo;
        lbabrirutlfilen1 := FALSE;

      END IF;


    --nota 1
    OPEN cur_nota1;
    LOOP
       FETCH cur_nota1 bulk collect INTO record_nota1;
       IF record_nota1.COUNT > 0 THEN
          FOR x IN record_nota1.FIRST .. record_nota1.LAST LOOP

          lsLinea :=   record_nota1(x).cod_peri ||';'||
                       record_nota1(x).cod_clie ||';'||
                       record_nota1(x).cod_terr ||';'||
                       record_nota1(x).val_nomb ||';'||
                       record_nota1(x).num_docu_iden ||';'||
                       record_nota1(x).val_dire ||';'||
                       record_nota1(x).val_comu ||';'||
                       record_nota1(x).val_vill ||';'||
                       record_nota1(x).fec_proc ||';'||
                       record_nota1(x).val_nume_soli ||';'||
                       record_nota1(x).num_recl ||';'||
                       record_nota1(x).val_codi_vent ||';'||
                       record_nota1(x).num_unid ||';'||
                       record_nota1(x).val_prec_tota ||';'||
                       record_nota1(x).cod_prod ||';'||
                       record_nota1(x).des_prod ||';'||
                       record_nota1(x).val_prec_sin_impu ||';'||
                       record_nota1(x).val_impo_impu ||';'||
                       record_nota1(x).val_prec_tota ||';'||
                       record_nota1(x).val_foli_refe ||';'||
                       record_nota1(x).val_fech_refe ||';'||
                       record_nota1(x).val_tipo_refe ||';'||
                       '0'||';'||
                       record_nota1(x).val_foli;
                UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
      END IF;
       EXIT WHEN cur_nota1%NOTFOUND;
    END LOOP;
    CLOSE cur_nota1;


    IF NOT lbabrirutlfilen1 THEN
        psNota1 := v_archivo||'.TXT';
      utl_file.fclose(v_handle);
    END IF;




    --Creando el archivo nota2
     IF lbabrirutlfilen2 THEN
        v_archivo := '';
        v_archivo := 'nota2_' || v_nombre_archivo;
        v_titulo := '';

        gen_pkg_inter_archi.gen_pr_inici_repor_oracl(pscodigopais,
                                                     v_archivo,
                                                     '.TXT',
                                                     v_titulo,
                                                     lsdirtempo,
                                                     v_handle);
        psdirectorio   := lsdirtempo;
        lbabrirutlfilen2 := FALSE;
      END IF;


 OPEN cur_nota2;
    LOOP

       FETCH cur_nota2 bulk collect INTO record_nota2;
        IF record_nota2.COUNT > 0 THEN
          FOR x IN record_nota2.FIRST .. record_nota2.LAST LOOP

          lsLinea :=   record_nota2(x).fecha||';'||
                       record_nota2(x).hora ||';'||
                       record_nota2(x).minimo||';'||
                       record_nota2(x).maximo||';'||
                       record_nota2(x).suma;

                UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
          END IF;
       EXIT WHEN cur_nota2%NOTFOUND;
    END LOOP;
    CLOSE cur_nota2;

    IF NOT lbabrirutlfilen2 THEN
      psNota2 := v_archivo||'.TXT';
      utl_file.fclose(v_handle);
    END IF;

 end if;


  if psAccion = '1' then
     v_archivo := '';
     --nombre de archivo
      select to_char(sysdate, 'yyyymmddhh24miss')
        into v_nombre_archivo
        from dual;

      --Creando el archivo boleta1
     IF lbabrirutlfileb1 THEN
        v_archivo := 'boleta1_'||v_nombre_archivo;

        v_titulo := '';
        gen_pkg_inter_archi.gen_pr_inici_repor_oracl(pscodigopais,
                                                     v_archivo,
                                                     '.TXT',
                                                     v_titulo,
                                                     lsdirtempo,
                                                     v_handle);
        psdirectorio   := lsdirtempo;
        lbabrirutlfileb1 := FALSE;
      END IF;


   --boleta1
    OPEN cur_boleta1;
    LOOP
       FETCH cur_boleta1 bulk collect INTO record_boleta1;
        IF record_boleta1.COUNT > 0 THEN
          FOR x IN record_boleta1.FIRST .. record_boleta1.LAST LOOP

          lsLinea :=   record_boleta1(x).contador ||';'||
                       record_boleta1(x).cod_peri ||';'||
                       record_boleta1(x).cod_clie ||';'||
                       record_boleta1(x).cod_terr ||';'||
                       record_boleta1(x).fec_proc ||';'||
                       record_boleta1(x).val_nume_soli ||';'||
                       record_boleta1(x).val_codi_vent ||';'||
                       record_boleta1(x).val_desc_prod ||';'||
                       record_boleta1(x).num_unid ||';'||
                       record_boleta1(x).total ||';'||
                       record_boleta1(x).val_foli;

                UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
          END IF;
       EXIT WHEN cur_boleta1%NOTFOUND;
    END LOOP;
    CLOSE cur_boleta1;

   IF NOT lbabrirutlfileb1 THEN
      utl_file.fclose(v_handle);
      psBoleta1 := v_archivo||'.TXT';
    END IF;



      --Creando el archivo boleta2
     IF lbabrirutlfileb2 THEN
        v_archivo := '';

        v_archivo := 'boleta2_'||v_nombre_archivo;

        v_titulo := '';

        gen_pkg_inter_archi.gen_pr_inici_repor_oracl(pscodigopais,
                                                     v_archivo,
                                                     '.TXT',
                                                     v_titulo,
                                                     lsdirtempo,
                                                     v_handle);
        psdirectorio   := lsdirtempo;
        lbabrirutlfileb2 := FALSE;
      END IF;



 OPEN cur_boleta2;
    LOOP

       FETCH cur_boleta2 bulk collect INTO record_boleta2;
        IF record_boleta2.COUNT > 0 THEN
          FOR x IN record_boleta2.FIRST .. record_boleta2.LAST LOOP

          lsLinea :=   record_boleta2(x).fecha||';'||
                       record_boleta2(x).hora ||';'||
                       record_boleta2(x).minimo||';'||
                       record_boleta2(x).maximo||';'||
                       record_boleta2(x).suma;

                UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
          END IF;
       EXIT WHEN cur_boleta2%NOTFOUND;
    END LOOP;
    CLOSE cur_boleta2;

   IF NOT lbabrirutlfileb2 THEN
      psBoleta2 := v_archivo||'.TXT';
      utl_file.fclose(v_handle);
    END IF;

  end if;



 EXCEPTION
  WHEN OTHERS THEN
   ln_sqlcode := SQLCODE;
   ls_sqlerrm := substr(sqlerrm, 1, 1000);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR CCC_PR_VENTAS_DETALLE_SII' || ls_sqlerrm);

 end CCC_PR_VENTAS_DETALLE_SII ;


 /**************************************************************************
     Descripcion       : Genera data para reporte de Primeros Pedidos Deudores
     Fecha Creacion    : 23/04/2012
     Autor             : EL
 ***************************************************************************/
 PROCEDURE CCC_PR_GENER_PRIME_PEDID_DEUDO(
  p_cod_peri_inic                  IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_peri_fina                  IN   seg_perio_corpo.cod_peri%TYPE,
  p_oid_ejec_repo                  OUT  NUMBER)
 IS

  lv_oid_ejecu_repor               NUMBER;
  lv_oid_subp                      NUMBER;
  lv_oid_tipo_soli_pais            NUMBER;
  lv_oid_peri_ini                  NUMBER;
  lv_oid_peri_fin                  NUMBER;

  lv_cod_erro                      VARCHAR2(4000);
  lv_des_erro                      VARCHAR2(4000);

  lv_id_proc_ejec                  fin_proce_ejecu.cod_proc_ejec%TYPE;
  lv_log_cod_proc                  fin_proce_ejecu.cod_proc%TYPE;
  lv_log_user                      fin_proce_ejecu.usu_proc%TYPE;

 BEGIN

  gv_log_user     := USER;
  gv_log_cod_modu := gc_cod_modu;
  lv_log_cod_proc := '25';
  FIN_PKG_GENER.FIN_PR_REGIS_PROCE(gc_cod_modu,lv_log_cod_proc,lv_log_user,lv_id_proc_ejec,lv_cod_erro);

  --Asignar  id  al proceso
  lv_oid_ejecu_repor := CCC_REPO_SEQ.NEXTVAL;

  p_oid_ejec_repo := lv_oid_ejecu_repor;

  gv_des_log:= 'Inicia CCC_PR_GENER_PRIME_PEDID_DEUDO: Codigo Periodo Inicial ' ||  p_cod_peri_inic  || ' Codigo Periodo Final : ' ||  p_cod_peri_inic || ' oid Ejecu:  ' || lv_oid_ejecu_repor;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  SELECT sp.oid_subp
  INTO lv_oid_subp
  FROM
   ccc_proce pr,
   ccc_subpr sp
  WHERE sp.ccpr_oid_proc = pr.oid_proc
    AND pr.cod_proc = 'CCC001'
    AND sp.cod_subp = 1;

  --- Obtiene  oid   de tipo solicitud  pais
  lv_oid_tipo_soli_pais := FIN_PKG_GENER.FIN_FN_OBTIE_OID_SOLIC_PAIS('C1');
  lv_oid_peri_ini := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(p_cod_peri_inic);
  lv_oid_peri_fin := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(p_cod_peri_fina);

  DELETE FROM ccc_tempo_prime_pedid_refer
  WHERE oid_ejecu_repor = lv_oid_ejecu_repor;

  DELETE FROM ccc_tempo_saldo_prime_pedid
  WHERE oid_ejecu_repor = lv_oid_ejecu_repor;

  gv_des_log:= 'Inicia carga ccc_tempo_prime_pedid_refer';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  INSERT INTO ccc_tempo_prime_pedid_refer

  SELECT
   lv_oid_ejecu_repor,
   mcc.clie_oid_clie,
   MIN(mcc.perd_oid_peri)  oid_prim_pedi
  FROM ccc_movim_cuent_corri  mcc
  WHERE mcc.subp_oid_subp_crea = lv_oid_subp
    AND mcc.tspa_oid_tipo_soli_pais = lv_oid_tipo_soli_pais
    AND mcc.imp_movi > 0
  GROUP BY mcc.clie_oid_clie
  HAVING MIN(mcc.perd_oid_peri) BETWEEN lv_oid_peri_ini AND lv_oid_peri_fin;


  gv_des_log:= 'Inicia carga ccc_tempo_saldo_prime_pedid';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);


  INSERT INTO  ccc_tempo_saldo_prime_pedid
  SELECT
   lv_oid_ejecu_repor,
   pc.oid_prim_pedi,
   cc.clie_oid_clie,
   SUM(imp_movi) tot_movi,
   SUM(imp_pend) tot_pend,
   0     ---count(distinct cc.perd_oid_peri) num_pedi
  FROM
   ccc_tempo_prime_pedid_refer  pc,
   ccc_movim_cuent_corri  cc
  WHERE pc.oid_ejecu_repor =  lv_oid_ejecu_repor
    AND cc.clie_oid_clie  = pc.clie_oid_clie
    AND cc.perd_oid_peri  = pc.oid_prim_pedi
    AND cc.subp_oid_subp_crea   =  lv_oid_subp
    AND cc.tspa_oid_tipo_soli_pais   = lv_oid_tipo_soli_pais
  GROUP BY  pc.oid_prim_pedi, cc.clie_oid_clie;

  gv_des_log:= 'Inicia update ccc_tempo_saldo_prime_pedid';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE ccc_tempo_saldo_prime_pedid  spp
  SET  spp.ind_multi_pedid  =  1
  WHERE  spp.oid_ejecu_repor =  lv_oid_ejecu_repor
    AND EXISTS ( SELECT 1
                 FROM   ccc_movim_cuent_corri  cc
                 WHERE  cc.clie_oid_clie  =  spp.clie_oid_clie
                   AND    cc.perd_oid_peri  <>  spp.perd_oid_peri
                   AND    cc.subp_oid_subp_crea      = lv_oid_subp
                   AND    cc.tspa_oid_tipo_soli_pais = lv_oid_tipo_soli_pais);


  gv_des_log:= 'Fin   CCC_PR_GENER_PRIME_PEDID_DEUDO';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,'2');

  EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR CCC_PR_GENER_PRIME_PEDID_DEUDO: '||ls_sqlerrm);

  END CCC_PR_GENER_PRIME_PEDID_DEUDO;




 /**************************************************************************
     Descripcion       : Genera data para reporte de Primeros Y Segundos
                           Pedidos Deudores
     Fecha Creacion    : 23/04/2012
     Autor             : EL
   ***************************************************************************/
 PROCEDURE CCC_PR_GENER_SEGUN_PEDID_DEUDO (
  p_cod_peri_inic                  IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_peri_fina                  IN   seg_perio_corpo.cod_peri%TYPE,
  p_oid_ejec_repo                  OUT  NUMBER)
 IS

  lv_oid_ejecu_repor           NUMBER;
  lv_oid_pais                  NUMBER;
  lv_oid_subp                  NUMBER;
  lv_oid_tipo_soli_pais        NUMBER;
  lv_oid_peri_ini              NUMBER;
  lv_oid_peri_fin              NUMBER;

  lv_cod_erro                      VARCHAR2(4000);
  lv_des_erro                      VARCHAR2(4000);

  lv_id_proc_ejec                  fin_proce_ejecu.cod_proc_ejec%TYPE;
  lv_log_cod_proc                  fin_proce_ejecu.cod_proc%TYPE;
  lv_log_user                      fin_proce_ejecu.usu_proc%TYPE;

 BEGIN

  gv_log_user     := USER;
  gv_log_cod_modu := 'CCCREP';  -- CCC Reportes ...
  gv_log_cod_proc := '20';

  FIN_PKG_GENER.FIN_PR_REGIS_PROCE(gc_cod_modu,lv_log_cod_proc,lv_log_user,lv_id_proc_ejec,lv_cod_erro);

  --Asignar  id  al proceso
  SELECT CCC_REPO_SEQ.NEXTVAL
  INTO lv_oid_ejecu_repor
  FROM dual;

  p_oid_ejec_repo := lv_oid_ejecu_repor;

  gv_des_log:= 'Inicia CCC_PR_GENER_SEGUN_PEDID_DEUDO: ' || ' '
                   ||  p_cod_peri_inic  || ' ' ||  p_cod_peri_fina || ' oid Ejecu:  ' || p_oid_ejec_repo ;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);


  SELECT  SP.OID_SUBP
  INTO    lv_oid_subp
  FROM    ccc_proce pr,
              ccc_subpr sp
      WHERE   pr.pais_oid_pais   =  lv_oid_pais
      AND     pr.cod_proc  =  'CCC001'
      AND     sp.ccpr_oid_proc  = pr.oid_proc
      AND     sp.cod_subp   =  1    ;


  lv_oid_tipo_soli_pais := FIN_PKG_GENER.FIN_FN_OBTIE_OID_SOLIC_PAIS('C1');
  lv_oid_peri_ini := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(p_cod_peri_inic);
  lv_oid_peri_fin := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(p_cod_peri_fina);

  DELETE  FROM ccc_tempo_prime_pedid_refer  purge
  WHERE  oid_ejecu_repor =  lv_oid_ejecu_repor  ;

  DELETE FROM ccc_tempo_saldo_segun_pedid  purge
  WHERE  oid_ejecu_repor =  lv_oid_ejecu_repor   ;

  gv_des_log:= 'Inicia carga ccc_tempo_prime_pedid_refer ';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  INSERT INTO  ccc_tempo_prime_pedid_refer
   SELECT
    lv_oid_ejecu_repor,
    cc.clie_oid_clie,
    MIN(cc.perd_oid_peri)  oid_prim_pedi
   FROM
    ccc_movim_cuent_corri  cc
   WHERE cc.subp_oid_subp_crea = lv_oid_subp
     AND cc.tspa_oid_tipo_soli_pais = lv_oid_tipo_soli_pais
   GROUP BY cc.clie_oid_clie
   HAVING MIN(cc.perd_oid_peri)  BETWEEN lv_oid_peri_ini  AND lv_oid_peri_fin ;

  gv_des_log:= 'Inicia carga ccc_tempo_saldo_segun_pedid ' ;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  INSERT INTO  ccc_tempo_saldo_segun_pedid
   SELECT
    lv_oid_ejecu_repor,
    pc.oid_prim_pedi,
    cc.clie_oid_clie,
    SUM(imp_movi) tot_movi,
    SUM(imp_pend) tot_pend,
    NULL,
    NULL,
    NULL,
    NULL,
    NULL,
    NULL
   FROM
    ccc_tempo_prime_pedid_refer  pc,
    ccc_movim_cuent_corri  cc
   WHERE pc.oid_ejecu_repor = lv_oid_ejecu_repor
     AND cc.clie_oid_clie = pc.clie_oid_clie
     AND cc.subp_oid_subp_crea  = lv_oid_subp
     AND cc.tspa_oid_tipo_soli_pais  = lv_oid_tipo_soli_pais
     AND pc.oid_prim_pedi BETWEEN  lv_oid_peri_ini  AND lv_oid_peri_fin
    GROUP BY  pc.oid_prim_pedi, cc.clie_oid_clie
    HAVING SUM(imp_pend) > 0
    AND COUNT(DISTINCT cc.perd_oid_peri) = 2;

  gv_des_log:= 'Inicia update ccc_tempo_saldo_segun_pedid ' ;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  UPDATE   CCC_TEMPO_SALDO_SEGUN_PEDID  sp
  SET
   oid_peri_prime  =  perd_oid_peri,
   ( imp_movi_prime,  imp_pend_prime )   =
             (  SELECT SUM(imp_movi),  SUM(imp_pend)
                FROM  ccc_movim_cuent_corri  cc
                WHERE cc.clie_oid_clie  =  sp.clie_oid_clie
                  AND   cc.perd_oid_peri  =  sp.perd_oid_peri
                  AND   cc.subp_oid_subp_crea   = lv_oid_subp
                  AND   cc.tspa_oid_tipo_soli_pais   = lv_oid_tipo_soli_pais) ,
   ( oid_peri_segun,  imp_movi_segun,  imp_pend_segun )   =
             (  SELECT MIN(perd_oid_peri) , SUM(imp_movi),  SUM(imp_pend)
                FROM  ccc_movim_cuent_corri  cc
                WHERE cc.clie_oid_clie  =  sp.clie_oid_clie
                 AND   cc.perd_oid_peri  >  sp.perd_oid_peri
                 AND   cc.subp_oid_subp_crea   = lv_oid_subp
                 AND   cc.tspa_oid_tipo_soli_pais   = lv_oid_tipo_soli_pais)
  WHERE oid_ejecu_repor = lv_oid_ejecu_repor ;

   gv_des_log:= 'Fin   CCC_PR_GENER_SEGUN_PEDID_DEUDO';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,'2');

  EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR CCC_PR_GENER_SEGUN_PEDID_DEUDO: '||ls_sqlerrm);

  END CCC_PR_GENER_SEGUN_PEDID_DEUDO;



 /**************************************************************************
     Descripcion       : Genera data para reporte de Saldos 6 Campa?as
     Fecha Creacion    : 25/04/2012
     Autor             : EL
   ***************************************************************************/
  PROCEDURE CCC_PR_GENER_SALDO_CAMPA_5ANTE (
     psCodigoPais             IN seg_pais.cod_pais%TYPE,
     psCodigoSoci             IN seg_socie.cod_soci%TYPE,
     psCodigoPeriodoFinal     IN seg_perio_corpo.cod_peri%TYPE,
     psOidEjecuRepor          OUT NUMBER,
     psCodigoPeriodo01        OUT seg_perio_corpo.cod_peri%TYPE,
     psCodigoPeriodo02        OUT seg_perio_corpo.cod_peri%TYPE,
     psCodigoPeriodo03        OUT seg_perio_corpo.cod_peri%TYPE,
     psCodigoPeriodo04        OUT seg_perio_corpo.cod_peri%TYPE,
     psCodigoPeriodo05        OUT seg_perio_corpo.cod_peri%TYPE,
     psCodigoPeriodo06        OUT seg_perio_corpo.cod_peri%TYPE
           )
  IS


       TYPE t_tab_oid_peri    IS TABLE OF cra_perio.oid_peri%TYPE;
       v_tab_oid_peri                     t_tab_oid_peri;

       TYPE t_tab_cod_peri    IS TABLE OF seg_perio_corpo.cod_peri%TYPE;
       v_tab_cod_peri                     t_tab_cod_peri;

       lv_cod_peri_ini         varchar2(6);
       lv_cod_peri_fin         varchar2(6);
       lv_oid_peri_ini         number(12);
       lv_oid_peri_fin         number(12);
       lv_oid_ejecu_repor      number(12);


  BEGIN

   --   dbms_output.put_line('inicio ejecucion' );

      gv_log_cod_pais := psCodigoPais;
      gv_log_cod_soci := psCodigoSoci;
      gv_log_user     := USER;
      gv_log_cod_modu := 'CCCREP';  -- CCC Reportes ...
      gv_log_cod_proc := '15';


      FIN_PKG_GENER.FIN_PR_REGIS_PROCE_EJEC(gv_log_cod_pais, gv_log_cod_soci, gv_log_cod_modu, gv_log_cod_proc, gv_log_user, gv_cod_proc_ejec);


          --Asignar  id  al proceso
      SELECT CCC_REPO_SEQ.NEXTVAL
      INTO lv_oid_ejecu_repor
      FROM dual;

      psOidEjecuRepor := lv_oid_ejecu_repor;


      gv_des_log:= 'Inicia CCC_PR_GENER_SALDO_CAMPA_5ANTE: ' || psCodigoPais  || ' ' ||  psCodigoSoci  || ' ' ||  psCodigoPeriodoFinal || ' oid Ejecu:  ' || psOidEjecuRepor ;
      FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC( gv_log_cod_pais, gv_log_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                 gv_cod_proc_ejec, gv_des_log);


      lv_cod_peri_fin  :=  psCodigoPeriodoFinal;
---  Calcula la 5ta campa?a anterior  a  la  de referencia
      lv_cod_peri_ini := FIN_PKG_GENER.FIN_FN_CALCU_PERIO_NANTE(lv_cod_peri_fin, 5);


      select cam.oid_peri
      into  lv_oid_peri_ini
      from cra_perio cam,
          seg_perio_corpo cor
      where cam.PERI_OID_PERI =  cor.OID_PERI
      and  cor.COD_PERI = lv_cod_peri_ini ;


      select cam.oid_peri
      into  lv_oid_peri_fin
      from cra_perio cam,
          seg_perio_corpo cor
      where cam.PERI_OID_PERI =  cor.OID_PERI
      and  cor.COD_PERI = lv_cod_peri_fin ;

--      gv_des_log:=  'carga array de periodos '  ;
--      FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC( gv_log_cod_pais, gv_log_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
--                                                   gv_cod_proc_ejec, gv_des_log);
--      dbms_output.put_line(gv_des_log);

      select cam.oid_peri,  cor.cod_peri
      BULK COLLECT INTO v_tab_oid_peri,  v_tab_cod_peri
      from cra_perio cam,
      seg_perio_corpo cor
      where cam.PERI_OID_PERI =  cor.OID_PERI
      and     cam.OID_PERI between lv_oid_peri_ini  and   lv_oid_peri_fin
      order  by  cor.cod_peri  ;

  --    gv_des_log:=  'array cargado:  '  ||   v_tab_oid_peri(1) || ' ' ||   v_tab_oid_peri(2) || ' ' ||   v_tab_oid_peri(3) || ' ' ||   v_tab_oid_peri(4) || ' ' ||   v_tab_oid_peri(5) || ' ' ||   v_tab_oid_peri(6) ;
  --    FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC( gv_log_cod_pais, gv_log_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
  --                                                   gv_cod_proc_ejec, gv_des_log);
  --    dbms_output.put_line(gv_des_log);

      ---   inicializa tablas de trabajo
      delete  from  ccc_tempo_saldo_campa  purge
      where  oid_ejecu_repor = lv_oid_ejecu_repor ;

      delete  from  ccc_tempo_array_saldo_campa  purge
      where  oid_ejecu_repor = lv_oid_ejecu_repor   ;

      gv_des_log:=  'carga  datos  ccc_tempo_saldo_campa '  ;
      FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC( gv_log_cod_pais, gv_log_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                 gv_cod_proc_ejec, gv_des_log);
--      dbms_output.put_line(gv_des_log);

      insert into  ccc_tempo_saldo_campa
      select  lv_oid_ejecu_repor, clie_oid_clie, null, perd_oid_peri, null,  sum(imp_pend)IMP_PEND,  sum(imp_movi) IMP_MOVI, null, 1
       from   ccc_movim_cuent_corri cc
      where   cc.perd_oid_peri  between   lv_oid_peri_ini    and  lv_oid_peri_fin
      and   imp_pend <> 0
      --and   subp_oid_subp_crea  =  lv_oid_subp
      --and  tspa_oid_tipo_soli_pais  =  lv_oid_tipo_soli_pais
      having  sum(imp_pend)  >  0
      group  by  clie_oid_clie,  perd_oid_peri
        ;


      gv_des_log:=  'elimina casos  1  campa?a '  ;
      FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC( gv_log_cod_pais, gv_log_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                   gv_cod_proc_ejec, gv_des_log);
--      dbms_output.put_line(gv_des_log);


      delete    from   ccc_tempo_saldo_campa
      where  oid_ejecu_repor = lv_oid_ejecu_repor
      and    clie_oid_clie  in  (
              select  clie_oid_clie     from  (
                      SELECT  clie_oid_clie ,  count(  *)  nreg
                      FROM   ccc_tempo_saldo_campa
                      where  oid_ejecu_repor = lv_oid_ejecu_repor
                      group by  clie_oid_clie
              )
              where   nreg  =  1
      )  ;


      gv_des_log:=  'actualiza  codigo  consul  campa?a '  ;
      FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC( gv_log_cod_pais, gv_log_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                   gv_cod_proc_ejec, gv_des_log);
--      dbms_output.put_line(gv_des_log);
--   se  obtienen  codigos  de consultora  y  campa?a
      update  ccc_tempo_saldo_campa WR
      set   cod_clie  =  ( select  cod_clie
                          from  mae_clien mae
                          where mae.oid_clie =  wr.clie_oid_clie),
            cod_peri =  ( select  cor.cod_peri
                          from    cra_perio cam,
                                  seg_perio_corpo cor
                          where   cam.PERI_OID_PERI =  cor.OID_PERI
                          and     CAM.OID_PERI  =  WR.PERD_OID_PERI )
      where  oid_ejecu_repor = lv_oid_ejecu_repor   ;



      gv_des_log:=  'act   ind  levan camp  actual '  ;
      FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC( gv_log_cod_pais, gv_log_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                   gv_cod_proc_ejec, gv_des_log);
--      dbms_output.put_line(gv_des_log);
---   actualiza  indicador  de levantamiento   para  campa?a actual
      UPDATE  ccc_tempo_saldo_campa WR
      set   wr.ind_leva =  (
          select count(*)
          from int_solic_conso_cabec a
          where A.COD_PAIS = psCodigoPais
          and A.COD_PERI =  wr.COD_PERI
          and A.IND_PROC_GP2 = 1
          and A.IND_ERRO_DEUD = 2
          and A.IND_ADMI_CART = 1
          and A.COD_CLIE = wr.COD_CLIE
          )
      where  oid_ejecu_repor = lv_oid_ejecu_repor ;



      gv_des_log:=  'act   ind  levan histo '  ;
      FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC( gv_log_cod_pais, gv_log_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                 gv_cod_proc_ejec, gv_des_log);
--      dbms_output.put_line(gv_des_log);

---   actualiza  indicador  de levantamiento   para  campa?as historicas
      UPDATE  ccc_tempo_saldo_campa WR
      set   wr.ind_leva =  (
          select count(*)
          from ped_histo_solic_conso_cabec a
          where A.COD_PAIS = psCodigoPais
          and A.COD_PERI =  wr.COD_PERI
          and A.IND_PROC_GP2 = 1
          and A.IND_ERRO_DEUD = 2
          and A.IND_ADMI_CART = 1
          and A.COD_CLIE = wr.COD_CLIE
          )
      where  oid_ejecu_repor = lv_oid_ejecu_repor ;

      DELETE ccc_tempo_saldo_campa WR where wr.ind_leva = 0;

      gv_des_log:=  'insert en matriz de saldos '  ;
      FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC( gv_log_cod_pais, gv_log_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                 gv_cod_proc_ejec, gv_des_log);
--      dbms_output.put_line(gv_des_log);


      INSERT  INTO  ccc_tempo_array_saldo_campa
      with   cmp_01 as
      (  select  cod_clie,  imp_movi, imp_pend, ind_leva, ind_deud   from ccc_tempo_saldo_campa
         where   perd_oid_peri  =  v_tab_oid_peri(1) and oid_ejecu_repor = lv_oid_ejecu_repor   )  ,
         cmp_02  as
      (  select  cod_clie,  imp_movi, imp_pend, ind_leva, ind_deud   from ccc_tempo_saldo_campa
         where   perd_oid_peri  =  v_tab_oid_peri(2) and oid_ejecu_repor = lv_oid_ejecu_repor  )  ,
         cmp_03  as
      (  select  cod_clie,  imp_movi, imp_pend, ind_leva, ind_deud   from ccc_tempo_saldo_campa
         where   perd_oid_peri  =  v_tab_oid_peri(3) and oid_ejecu_repor = lv_oid_ejecu_repor  )  ,
         cmp_04  as
      (  select  cod_clie,  imp_movi, imp_pend, ind_leva, ind_deud   from ccc_tempo_saldo_campa
         where   perd_oid_peri  =  v_tab_oid_peri(4) and oid_ejecu_repor = lv_oid_ejecu_repor  )  ,

         cmp_05  as
      (  select  cod_clie,  imp_movi, imp_pend, ind_leva, ind_deud   from ccc_tempo_saldo_campa
         where   perd_oid_peri  =  v_tab_oid_peri(5) and oid_ejecu_repor = lv_oid_ejecu_repor  )  ,
         cmp_06  as
      (  select  cod_clie,  imp_movi, imp_pend, ind_leva, ind_deud   from ccc_tempo_saldo_campa
         where   perd_oid_peri  =  v_tab_oid_peri(6) and oid_ejecu_repor = lv_oid_ejecu_repor  ),
         cons   as
         ( select  distinct    cod_clie, clie_oid_clie  from   ccc_tempo_saldo_campa  where oid_ejecu_repor = lv_oid_ejecu_repor )

         select lv_oid_ejecu_repor, cons.cod_clie, cons.clie_oid_clie,
                cmp_01.imp_movi movi1,  cmp_01.imp_pend pend1,  cmp_01.ind_leva ind_leva_1,  cmp_01.ind_deud ind_deud_1,
                cmp_02.imp_movi movi2,  cmp_02.imp_pend pend2,  cmp_02.ind_leva ind_leva_2,  cmp_02.ind_deud ind_deud_2,
                cmp_03.imp_movi movi3,  cmp_03.imp_pend pend3,  cmp_03.ind_leva ind_leva_3,  cmp_03.ind_deud ind_deud_3,
                cmp_04.imp_movi movi4,  cmp_04.imp_pend pend4,  cmp_04.ind_leva ind_leva_4,  cmp_04.ind_deud ind_deud_4,
                cmp_05.imp_movi movi5,  cmp_05.imp_pend pend5,  cmp_05.ind_leva ind_leva_5,  cmp_05.ind_deud ind_deud_5,
                cmp_06.imp_movi movi6,  cmp_06.imp_pend pend6,  cmp_06.ind_leva ind_leva_6,  cmp_06.ind_deud ind_deud_6,
                NULL, NULL, NULL
         from  cons,
               cmp_01,  cmp_02,  cmp_03,  cmp_04,  cmp_05,  cmp_06
         where cons.cod_clie  =  cmp_01.cod_clie(+)
         and   cons.cod_clie  =  cmp_02.cod_clie(+)
         and   cons.cod_clie  =  cmp_03.cod_clie(+)
         and   cons.cod_clie  =  cmp_04.cod_clie(+)
         and   cons.cod_clie  =  cmp_05.cod_clie(+)
         and   cons.cod_clie  =  cmp_06.cod_clie(+)

      ;


      gv_des_log:=  'Actualiza totales '  ;
      FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC( gv_log_cod_pais, gv_log_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                 gv_cod_proc_ejec, gv_des_log);
--      dbms_output.put_line(gv_des_log);


      UPDATE ccc_tempo_array_saldo_campa
      SET    TOT_PEND  = NVL(IMP_PEND_01, 0 ) + NVL(IMP_PEND_02, 0 ) + NVL(IMP_PEND_03, 0 ) +
                         NVL(IMP_PEND_04, 0 ) + NVL(IMP_PEND_05, 0 ) + NVL(IMP_PEND_06, 0 ),
             CTA_CASOS = NVL(IND_DEUD_01, 0 ) + NVL(IND_DEUD_02, 0 ) + NVL(IND_DEUD_03, 0 ) +
                         NVL(IND_DEUD_04, 0 ) + NVL(IND_DEUD_05, 0 ) + NVL(IND_DEUD_06, 0 )
      where  oid_ejecu_repor = lv_oid_ejecu_repor ;


      gv_des_log:=  'Actualiza bloqueos '  ;
      FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC( gv_log_cod_pais, gv_log_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                 gv_cod_proc_ejec, gv_des_log);
--      dbms_output.put_line(gv_des_log);

      update  ccc_tempo_array_saldo_campa   wrk
      set     ind_bloqu  = 1
      where  exists ( select 1
                       from mae_clien_bloqu  blq
                      where blq.clie_oid_clie   =   wrk.clie_oid_clie
                        and fec_desb  is null  )
      and oid_ejecu_repor = lv_oid_ejecu_repor ;

     psCodigoPeriodo01 := v_tab_cod_peri(1);
     psCodigoPeriodo02 := v_tab_cod_peri(2);
     psCodigoPeriodo03 := v_tab_cod_peri(3);
     psCodigoPeriodo04 := v_tab_cod_peri(4);
     psCodigoPeriodo05 := v_tab_cod_peri(5);
     psCodigoPeriodo06 := v_tab_cod_peri(6);


      gv_des_log:= 'Fin   CCC_PR_GENER_SALDO_CAMPA_5ANTE ';
 --     dbms_output.put_line( gv_des_log);
      FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC( gv_log_cod_pais, gv_log_cod_soci, gv_log_cod_modu, gv_log_cod_proc, gv_cod_proc_ejec, gv_des_log);
      FIN_PKG_GENER.FIN_PR_FINAL_PROCE_EJEC(gv_log_cod_pais, gv_log_cod_soci, gv_log_cod_modu, gv_log_cod_proc, gv_cod_proc_ejec, 2);

-------------------------------------------------------------------


  EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR CCC_PR_GENER_SALDO_CAMPA_5ANTE: '||ls_sqlerrm);

  END CCC_PR_GENER_SALDO_CAMPA_5ANTE;


  PROCEDURE CCC_PR_GENER_DIAS_CARTE(
      p_cod_pais          seg_pais.cod_pais%TYPE,
      p_cod_socie         seg_socie.cod_soci%TYPE,
      p_fec_inicial        VARCHAR2,
      p_fec_final          VARCHAR2,
      p_oid_ejecu_repor   OUT NUMBER
 )
  IS


       lv_oid_ejecu_repor      number(12);
       p_fec_ini DATE;
       p_fec_fin DATE;

  BEGIN

      p_fec_ini := to_date(p_fec_inicial, 'dd/mm/yyyy');
      p_fec_fin := to_date(p_fec_final, 'dd/mm/yyyy');

      gv_log_cod_pais := p_cod_pais;
      gv_log_cod_soci := p_cod_socie;
      gv_log_user     := USER;
      gv_log_cod_modu := 'CCCREP';  -- CCC Reportes ...
      gv_log_cod_proc := '10';
          --Asignar  id  al proceso

      SELECT CCC_REPO_SEQ.NEXTVAL
      INTO lv_oid_ejecu_repor
      FROM dual;

      p_oid_ejecu_repor := lv_oid_ejecu_repor;



      FIN_PKG_GENER.FIN_PR_REGIS_PROCE_EJEC(gv_log_cod_pais, gv_log_cod_soci, gv_log_cod_modu, gv_log_cod_proc, gv_log_user, gv_cod_proc_ejec);

      gv_des_log:= 'Inicia CCC_PR_GENER_DIAS_CARTE PARM: ' || P_COD_PAIS  || ' ' ||  P_COD_SOCIE  || ' ' ||  p_fec_inicial  || ' '
                    || p_fec_final || ' Oid proce: ' || p_oid_ejecu_repor ;
      FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC( gv_log_cod_pais, gv_log_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                 gv_cod_proc_ejec, gv_des_log);
--      dbms_output.put_line(gv_des_log);


      delete   from   ccc_tempo_dias_carte   PURGE
      WHERE  oid_ejecu_repor = lv_oid_ejecu_repor ;


      gv_des_log:=  'carga ccc_tempo_dias_carte '  ;
      FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC( gv_log_cod_pais, gv_log_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                 gv_cod_proc_ejec, gv_des_log);
--      dbms_output.put_line(gv_des_log);


      insert into  ccc_tempo_dias_carte
      select   lv_oid_ejecu_repor, cbz.oid_movi_cc,  cbz.imp_fact_neto, cc.fec_docu,
               cc.val_ulti_nume_hist, cc.imp_pago,
               TRUNC(cc.fec_ulti_movi), DECODE( TRUNC(cc.fec_ulti_movi) - TRUNC(cc.fec_docu), 0, 1, TRUNC(cc.fec_ulti_movi) - TRUNC(cc.fec_docu) ) dif,
               cc.subp_oid_subp_ulti, 0, 0, 0,
               EXTRACT( YEAR FROM cc.fec_DOCU ) || TRIM(to_char(EXTRACT(MONTH FROM cc.fec_DOCU ), '00') )
      from   ccc_movim_cuent_corri cc,
             cob_repor_estad_recup_cobra   cbz
      where   cc.oid_movi_cc  =  cbz.oid_movi_cc
      and  cc.val_ulti_nume_hist  >  0
      and  ( cc.val_ulti_nume_hist > 1
              or ( cc.val_ulti_nume_hist  = 1  and  exists ( select 1  from  cob_subpr_pago_banca spg
                                                              where  spg.cod_pais  =  p_cod_pais
                                                                and  spg.ind_acti = 1
                                                                and  spg.cb_oid_subp  = cc.subp_oid_subp_ulti  ) ) )
      and  cc.fec_docu  between p_fec_ini and p_fec_fin ;



      gv_des_log:=  'actualiza ccc_abon_pond '  ;
      FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC( gv_log_cod_pais, gv_log_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                 gv_cod_proc_ejec, gv_des_log);
--      dbms_output.put_line(gv_des_log);

      update ccc_tempo_dias_carte  tdc
      set   ccc_abon_pond  =  ccc_imp_pago *  ccc_dias_pago
      where  exists (  select  null
                        from cob_subpr_pago_banca spg
                       where spg.cod_pais =  p_cod_pais
                         and spg.ind_acti = 1
                         and spg.cb_oid_subp  = tdc.ccc_oid_subp  )
      and  oid_ejecu_repor =  lv_oid_ejecu_repor ;


      gv_des_log:=  'actualiza his_abon_pond '  ;
      FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC( gv_log_cod_pais, gv_log_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                 gv_cod_proc_ejec, gv_des_log);
--      dbms_output.put_line(gv_des_log);

      update   ccc_tempo_dias_carte  tdc
      set   his_abon_pond  =  (
          select nvl( sum( decode ( TRUNC(his.fec_movi) - trunc(his.fec_docu), 0,  1, TRUNC(his.fec_movi) - trunc(his.fec_docu) ) * his.imp_pago ), 0 )
          from   ccc_histo_movim_cc  his,
                 cob_subpr_pago_banca spg
          where his.subp_oid_subp  =   spg.cb_oid_subp
          and   spg.cod_pais  =  p_cod_pais
          and   ind_acti = 1
          and   his.num_hist  >  0
          and   his.mvcc_oid_movi_cc   =  tdc.oid_movi_cc
      )
      where tdc.val_ulti_nume_hist > 1
      and  oid_ejecu_repor =  lv_oid_ejecu_repor ;



      gv_des_log:=  'omitir casos  sin pago'  ;
      FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC( gv_log_cod_pais, gv_log_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                 gv_cod_proc_ejec, gv_des_log);
--      dbms_output.put_line(gv_des_log);

---   omitir  casos  que  no tienen ningun pago "monetario"
      delete    from   ccc_tempo_dias_carte   tdc
      where   tdc.ccc_abon_pond   = 0
      and       tdc.his_abon_pond   = 0
      and  oid_ejecu_repor =lv_oid_ejecu_repor ;



      gv_des_log:= 'Fin CCC_PR_GENER_DIAS_CARTE';
 --     dbms_output.put_line( gv_des_log);
      FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC( gv_log_cod_pais, gv_log_cod_soci, gv_log_cod_modu, gv_log_cod_proc, gv_cod_proc_ejec, gv_des_log);
      FIN_PKG_GENER.FIN_PR_FINAL_PROCE_EJEC(gv_log_cod_pais, gv_log_cod_soci, gv_log_cod_modu, gv_log_cod_proc, gv_cod_proc_ejec, 2);

  END CCC_PR_GENER_DIAS_CARTE  ;
  /**************************************************************************
     Descripcion       : Genera data para reporte de resumen de abono
     Fecha Creacion    : 20/09/2013
     Autor             : Gonzalo Javier Huertas Agurto
   ***************************************************************************/
 PROCEDURE CCC_PR_GENER_RESUM_ABONO (
     psFechaDesde             varchar2,
     psFechaHasta             varchar2,
     psOidTipoDocumento       varchar2,
     psTipoOidTipoDocumento01 varchar2,
     psTipoOidTipoDocumento02 varchar2,
     psTipoOidTipoDocumento03 varchar2
     )
     IS
   BEGIN
   EXECUTE IMMEDIATE 'TRUNCATE TABLE CCC_REPOR_RESUM_ABONO_TAB1';
   INSERT INTO CCC_REPOR_RESUM_ABONO_TAB1(FEC_FACT, DES_TIPO_DOCU, VAL_SERI_DOCU_LEGA, NUM_DOCU_LEGA, BPCS, IMPORTE_BI, IMPORTE_ICE, clie_oid_clie)

    SELECT FAC_DOCUM_CONTA_CABEC.FEC_FACT,
                               FAC_TIPO_DOCUM.DES_TIPO_DOCU,
                               FAC_DOCUM_CONTA_CABEC.VAL_SERI_DOCU_LEGA,
                               FAC_DOCUM_CONTA_CABEC.NUM_DOCU_LEGA,
                               PROD.VAL_GRUP_ARTI BPCS,
                               CASE
                                 WHEN INC_CONCU_TIPO_PROG.COD_TIPO_PROG IN ('B', 'C', 'R') THEN 0
                                 ELSE SUM(FAC_DOCUM_CONTA_LINEA.VAL_PREC_SIN_IMPU_TOTA_LOCA)
                               END IMPORTE_BI,
                               SUM(NVL(FAC_DOCUM_CONTA_LINEA.IMP_IMPU_TOTA_PROD_NACI, 0)) IMPORTE_ICE,
                               ped.clie_oid_clie
                          FROM FAC_DOCUM_CONTA_CABEC,
                               FAC_DOCUM_CONTA_LINEA,
                               PED_SOLIC_POSIC,
                               PED_SOLIC_CABEC PED,
                               INC_CONCU_TIPO_PROG,
                               FAC_TIPO_DOCUM,
                               MAE_PRODU PROD
                         WHERE FAC_DOCUM_CONTA_CABEC.OID_CABE = FAC_DOCUM_CONTA_LINEA.DCCA_OID_CABE
                           AND FAC_DOCUM_CONTA_LINEA.SOPO_OID_SOLI_POSI = PED_SOLIC_POSIC.OID_SOLI_POSI
                           AND PED_SOLIC_POSIC.SOCA_OID_SOLI_CABE = PED.OID_SOLI_CABE
                           AND PED_SOLIC_POSIC.PROD_OID_PROD = PROD.OID_PROD
                           AND FAC_DOCUM_CONTA_CABEC.ICTP_OID_TIPO_PROG = INC_CONCU_TIPO_PROG.OID_TIPO_PROG(+)
                           AND FAC_DOCUM_CONTA_CABEC.TIDO_OID_TIPO_DOCU = FAC_TIPO_DOCUM.OID_TIPO_DOCU
                           AND FAC_DOCUM_CONTA_CABEC.FEC_FACT >= TO_DATE(psFechaDesde, 'DD/MM/YYYY')
                           AND FAC_DOCUM_CONTA_CABEC.FEC_FACT <= TO_DATE(psFechaHasta, 'DD/MM/YYYY')
                           AND FAC_TIPO_DOCUM.OID_TIPO_DOCU NOT IN (psOidTipoDocumento)
                           AND FAC_DOCUM_CONTA_CABEC.TIDO_OID_TIPO_DOCU IN (psTipoOidTipoDocumento01, psTipoOidTipoDocumento02, psTipoOidTipoDocumento03)
                           AND nvl(FAC_DOCUM_CONTA_CABEC.val_tota_paga_loca,0) < 0
                         GROUP BY FAC_DOCUM_CONTA_CABEC.FEC_FACT,
                                  FAC_TIPO_DOCUM.DES_TIPO_DOCU,
                                  FAC_DOCUM_CONTA_CABEC.VAL_SERI_DOCU_LEGA,
                                  FAC_DOCUM_CONTA_CABEC.NUM_DOCU_LEGA,
                                  PROD.VAL_GRUP_ARTI,
                                  INC_CONCU_TIPO_PROG.COD_TIPO_PROG,
                                  PED.CLIE_OID_CLIE;

 END CCC_PR_GENER_RESUM_ABONO;

   /**************************************************************************
     Descripcion       :
     Fecha Creacion    : 03/12/2013
     Autor             : Rosalvina Ramirez Guardia
   ***************************************************************************/
PROCEDURE CCC_PR_GENER_SALDO_DIARI
    IS

    lv_fec_fact                      bas_ctrl_fact.fec_proc%TYPE;
    x                                number;

 cursor c_c1 is
         select null,
           gen_pkg_gener.GEN_FN_CLIEN_DATOS(mc.COD_CLIE, 'COD_REGI') cod_regi,
           gen_pkg_gener.GEN_FN_CLIEN_DATOS(mc.COD_CLIE, 'COD_ZONA') cod_zona,
           mc.cod_clie,
           mc.val_nom1,
           mc.val_nom2,
           mc.val_ape1,
           mc.val_ape2,
           (select num_docu_iden from mae_clien_ident iden where iden.CLIE_OID_CLIE=mc.oid_clie
           and tdoc_oid_tipo_docu=2001) num_docu_iden,
           mc.oid_clie,
           mce.soca_oid_soca oid_soli_cabe,
           ( select max(f.num_docu_cont_inte) from fac_docum_conta_cabec f
           where f.soca_oid_soli_cabe=mce.soca_oid_soca) num_docu_cont_inte, 
           mce.val_nume_soli val_nume_soli,
           mce.fec_fact_ulti fec_fact,
           mce.val_mont_fact val_tota_paga_loca,
           (select oid_peri from cra_perio where val_nomb_peri = mce.camp_ulti_pedi) perd_oid_peri,
           (select min(fec_venc) from ccc_movim_cuent_corri mcc where mcc.soca_oid_soli_cabe = mce.soca_oid_soca) fec_venc,
           null,
           null,
           nvl(mc.sal_deud_ante,0),
           sysdate,
           null ,
           'NO' castigada,
           null fec_cast,
           null abo_desp_cast,
           null imp_deud_cast,
           null fec_deud
    FROM mae_clien             mc,  
         mae_clien_estat mce
    where    mc.oid_clie = mce.oid_clie(+)
    and not exists (select * from ccc_clien_casti ccc where ccc.oid_clie= mc.oid_clie)
union
         select null,
           gen_pkg_gener.GEN_FN_CLIEN_DATOS(mc.COD_CLIE, 'COD_REGI') cod_regi,
           gen_pkg_gener.GEN_FN_CLIEN_DATOS(mc.COD_CLIE, 'COD_ZONA') cod_zona,
           mc.cod_clie,
           mc.val_nom1,
           mc.val_nom2,
           mc.val_ape1,
           mc.val_ape2,
           (select num_docu_iden from mae_clien_ident iden where iden.CLIE_OID_CLIE=mc.oid_clie
           and tdoc_oid_tipo_docu=2001) num_docu_iden,
           mc.oid_clie,
           mce.soca_oid_soca oid_soli_cabe,
           ( select max(f.num_docu_cont_inte) from fac_docum_conta_cabec f
           where f.soca_oid_soli_cabe=mce.soca_oid_soca) num_docu_cont_inte, 
           mce.val_nume_soli val_nume_soli,
           mce.fec_fact_ulti fec_fact,
           mce.val_mont_fact val_tota_paga_loca,
           (select oid_peri from cra_perio where val_nomb_peri = mce.camp_ulti_pedi) perd_oid_peri,
           (select min(fec_venc) from ccc_movim_cuent_corri mcc where mcc.soca_oid_soli_cabe = mce.soca_oid_soca) fec_venc,
           null,
           null,
           nvl(mc.sal_deud_ante,0),
           sysdate,
           null,
          'SI' castigada,
           ccc.fec_cast,
           (select nvl(sum(imp_pago),0) imp_pago from ccc_movim_banca where clie_oid_clie= mc.oid_clie
              and cod_iden_proc = 'P'
              and trunc(fec_pago) >=  trunc(ccc.fec_cast)) -
            (select nvl(sum(imp_movi),0) imp_pago from ccc_movim_cuent_corri mcc
              where mcc.clie_oid_clie = mc.oid_clie
              and mcc.imp_movi <0
              and trunc(fec_docu) >=  trunc(ccc.fec_cast)
              ) abo_desp_cast,
           ccc.imp_deud_cast,
           ccc.fec_deud
    FROM mae_clien             mc,  
         ccc_clien_casti       ccc, 
         mae_clien_estat mce
    where mc.oid_clie = ccc.oid_clie
       and mc.oid_clie = mce.oid_clie(+);


   TYPE t_tab_ccc_repor  IS TABLE OF CCC_REPOR_SALDO_DIARIO%ROWTYPE;
  lv_tab_ccc_repor           t_tab_ccc_repor;

 BEGIN

    select fec_proc into lv_fec_fact
   from bas_ctrl_fact where sta_camp='0'
   and ind_camp_act='1';

  OPEN c_c1;
  LOOP
   FETCH c_c1 BULK COLLECT INTO lv_tab_ccc_repor LIMIT 5000;
   IF lv_tab_ccc_repor.COUNT > 0 THEN

   FOR x IN lv_tab_ccc_repor.FIRST .. lv_tab_ccc_repor.LAST LOOP

  begin
  INSERT INTO CCC_REPOR_SALDO_DIARIO
  values(
          'Facturacion',
           lv_tab_ccc_repor(x).cod_regi,
           lv_tab_ccc_repor(x).cod_zona,
           lv_tab_ccc_repor(x).cod_clie,
           lv_tab_ccc_repor(x).val_nom1,
           lv_tab_ccc_repor(x).val_nom2,
           lv_tab_ccc_repor(x).val_ape1,
           lv_tab_ccc_repor(x).val_ape2,
           lv_tab_ccc_repor(x).num_docu_iden,
           lv_tab_ccc_repor(x).oid_clie,
           lv_tab_ccc_repor(x).oid_solic_cabe,
           lv_tab_ccc_repor(x).num_docu_cont_inte,
           lv_tab_ccc_repor(x).val_nume_soli,
           lv_tab_ccc_repor(x).fec_fact,
           lv_tab_ccc_repor(x).val_tota_paga_loca,
           lv_tab_ccc_repor(x).oid_peri,
           lv_tab_ccc_repor(x).fec_venc,
           null,
           null,
           lv_tab_ccc_repor(x).saldo,
           sysdate,
           null,
           lv_tab_ccc_repor(x).ind_cast,
           lv_tab_ccc_repor(x).fec_cast,
           lv_tab_ccc_repor(x).abo_desp_cast,
           lv_tab_ccc_repor(x).imp_deud_cast,
           lv_tab_ccc_repor(x).fec_deud

  );
 exception
   WHEN dup_val_on_index THEN
        update CCC_REPOR_SALDO_DIARIO tmp
        set tmp.oid_solic_cabe  = lv_tab_ccc_repor(x).oid_solic_cabe,
        tmp.val_nume_soli = lv_tab_ccc_repor(x).val_nume_soli,
        tmp.fec_fact = lv_tab_ccc_repor(x).fec_fact,
        tmp.val_tota_paga_loca= lv_tab_ccc_repor(x).val_tota_paga_loca,
        tmp.oid_peri = lv_tab_ccc_repor(x).oid_peri,
        tmp.saldo = lv_tab_ccc_repor(x).saldo,
        tmp.num_docu_iden = lv_tab_ccc_repor(x).num_docu_iden,
        tmp.fec_venc = lv_tab_ccc_repor(x).fec_venc,
        tmp.fec_modi = sysdate,
        tmp.ind_cast = lv_tab_ccc_repor(x).ind_cast,
        tmp.fec_cast = lv_tab_ccc_repor(x).fec_cast,
        tmp.abo_desp_cast = lv_tab_ccc_repor(x).abo_desp_cast,
        tmp.imp_deud_cast = lv_tab_ccc_repor(x).imp_deud_cast,
        tmp.fec_deud = lv_tab_ccc_repor(x).fec_deud
        where tmp.oid_clie = lv_tab_ccc_repor(x).oid_clie;
   end;

   end loop;

       END IF;

      EXIT WHEN c_c1%NOTFOUND;
         END LOOP;
      CLOSE c_c1;


   update CCC_REPOR_SALDO_DIARIO tmp
      set tmp.saldo =
           (SELECT NVL(SUM(ccc.IMP_PEND), 0)
               FROM CCC_MOVIM_CUENT_CORRI ccc, MAE_CLIEN cli
             WHERE cli.OID_CLIE = tmp.oid_clie
                AND ccc.CLIE_OID_CLIE = cli.OID_CLIE
                AND ccc.IMP_PEND > 0);

  update CCC_REPOR_SALDO_DIARIO tmp
     set tmp.dias_venc = tmp.fec_venc - lv_fec_fact,
         tmp.dias_fact = lv_fec_fact - tmp.fec_fact;


   EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR CCC_PR_GENER_SALDO_DIARI: '||ls_sqlerrm);
 END CCC_PR_GENER_SALDO_DIARI;


/*************************************************************************************
Descripcion       : Procedimiento que carga el Reporte Liquidacion Cobranza para formato CSV
Fecha Creacion    : 27/01/2014
Autor             : Carlos Bazalar
************************************************************************************/
PROCEDURE ccc_pr_liqui_cobra_csv(
    pscodigopais          VARCHAR2,
    psfechaPagoDesde      VARCHAR2,
    psfechaPagoHasta      VARCHAR2,
    psfechaProcDesde      VARCHAR2,
    psfechaProcHasta      VARCHAR2,
    pscodigoProceso       VARCHAR2,
    pscodigoSubproceso    VARCHAR2,
    pscodigoBanco         VARCHAR2,
    pstipoNombreReporte   VARCHAR2,
    psnombrearchivo       VARCHAR2,
    pstitulo              VARCHAR2,
    psdirectorio          OUT VARCHAR2
)
IS
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         number := 5000;
    v_handle        utl_file.file_type;
    lslinea         varchar2(4000);

    CURSOR C_REPOR_01 IS
        SELECT
          ccb.cod_cc cod_banc,
          ccb.des_cc des_banc,
          CASE
             WHEN   cp.cod_proc='TES001' AND su.cod_subp=1 THEN
                'Automatico'
             WHEN   cp.cod_proc='TES001' AND su.cod_subp=2 THEN
                'Manual'
           END tipo,
           to_char(mb.fec_pago, 'dd/mm/yyyy') fec_pago,
           to_char(MIN(mb.fec_proc), 'dd/mm/yyyy') fec_proc ,
           COUNT(*) tot_reg_lote,
           SUM(mb.imp_pago) imp_tota
        FROM ccc_movim_banca mb,
             ccc_cuent_corri_banca ccb,
             ccc_proce cp,
             ccc_subpr su
        WHERE mb.ccba_oid_cc_banc = ccb.oid_cuen_corr_banc
        AND mb.subp_oid_marc_crea = su.oid_subp
        AND cp.oid_proc = su.ccpr_oid_proc
        AND mb.fec_pago >= DECODE(TO_DATE(psfechaPagoDesde,'DD/MM/YYYY'),null,mb.fec_pago,'',mb.fec_pago,TO_DATE(psfechaPagoDesde,'DD/MM/YYYY'))
        AND mb.fec_pago <= DECODE(TO_DATE(psfechaPagoHasta,'DD/MM/YYYY'),null,mb.fec_pago,'',mb.fec_pago,TO_DATE(psfechaPagoHasta,'DD/MM/YYYY'))
        AND mb.fec_proc >= DECODE(TO_DATE(psfechaProcDesde,'DD/MM/YYYY'),null,mb.fec_proc,'',mb.fec_proc,TO_DATE(psfechaProcDesde,'DD/MM/YYYY'))
        AND mb.fec_proc <= DECODE(TO_DATE(psfechaProcHasta,'DD/MM/YYYY'),null,mb.fec_proc,'',mb.fec_proc,TO_DATE(psfechaProcHasta,'DD/MM/YYYY'))
        AND cp.cod_proc = DECODE(pscodigoProceso,null,cp.cod_proc,'',cp.cod_proc,pscodigoProceso)
        AND su.cod_subp = DECODE(pscodigoSubproceso,null,su.cod_subp,'',su.cod_subp,pscodigoSubproceso)
        AND ccb.cod_cc = DECODE(pscodigoBanco,null,ccb.cod_cc,'Todos',ccb.cod_cc,'',ccb.cod_cc,pscodigoBanco)
        AND mb.cod_iden_proc='P'
        GROUP BY  ccb.cod_cc,ccb.des_cc, cp.cod_proc, su.cod_subp, mb.fec_pago
        ORDER BY 4 , 1 , 2 ;

    TYPE detalleUnidadesReg01 IS RECORD(
      cod_cc                  ccc_cuent_corri_banca.cod_cc%TYPE,
      des_cc                  ccc_cuent_corri_banca.des_cc%TYPE,
      tipo                    VARCHAR2(20),
      fec_pago                VARCHAR2(10),
      fec_proc                VARCHAR2(10),
      tot_reg_lote            NUMBER(12),
      imp_pago                NUMBER(14,2)
    );
    TYPE detalleUnidadesRegTab01 IS TABLE OF detalleUnidadesReg01;
    detalleUnidadesRegRecord01 detalleUnidadesRegTab01;

    CURSOR C_REPOR_02 IS
        SELECT
        ccb.cod_cc cod_banc,
        ccb.des_cc des_banc,
        mb.num_lote,
        CASE
          WHEN    cp.cod_proc='TES001' AND su.cod_subp=1 THEN
                'Automatico'
          ELSE
             'Manual'
        END tipo,
        to_char(mb.fec_pago, 'dd/mm/yyyy') fec_pago,
        to_char(MIN(mb.fec_proc), 'dd/mm/yyyy') fec_proc ,
        COUNT(*) tot_reg_lote,
        SUM(mb.imp_pago) imp_tota
      FROM ccc_movim_banca mb,
           ccc_cuent_corri_banca ccb,
           ccc_proce cp,
           ccc_subpr su
      WHERE mb.ccba_oid_cc_banc = ccb.oid_cuen_corr_banc
      AND mb.subp_oid_marc_crea = su.oid_subp
      AND cp.oid_proc = su.ccpr_oid_proc
      AND mb.fec_pago >= DECODE(TO_DATE(psfechaPagoDesde,'DD/MM/YYYY'),null,mb.fec_pago,'',mb.fec_pago,TO_DATE(psfechaPagoDesde,'DD/MM/YYYY'))
      AND mb.fec_pago <= DECODE(TO_DATE(psfechaPagoHasta,'DD/MM/YYYY'),null,mb.fec_pago,'',mb.fec_pago,TO_DATE(psfechaPagoHasta,'DD/MM/YYYY'))
      AND mb.fec_proc >= DECODE(TO_DATE(psfechaProcDesde,'DD/MM/YYYY'),null,mb.fec_proc,'',mb.fec_proc,TO_DATE(psfechaProcDesde,'DD/MM/YYYY'))
      AND mb.fec_proc <= DECODE(TO_DATE(psfechaProcHasta,'DD/MM/YYYY'),null,mb.fec_proc,'',mb.fec_proc,TO_DATE(psfechaProcHasta,'DD/MM/YYYY'))
      AND cp.cod_proc = DECODE(pscodigoProceso,null,cp.cod_proc,'',cp.cod_proc,pscodigoProceso)
      AND su.cod_subp = DECODE(pscodigoSubproceso,null,su.cod_subp,'',su.cod_subp,pscodigoSubproceso)
      AND ccb.cod_cc = DECODE(pscodigoBanco,null,ccb.cod_cc,'',ccb.cod_cc,pscodigoBanco)
      AND mb.cod_iden_proc = 'P'
      GROUP BY  ccb.cod_cc,ccb.des_cc,mb.num_lote,cp.cod_proc, su.cod_subp, mb.fec_pago
      order by 4 ASC , 1 ASC , 2 ASC;

    TYPE detalleUnidadesReg02 IS RECORD(
      cod_cc                  ccc_cuent_corri_banca.cod_cc%TYPE,
      des_cc                  ccc_cuent_corri_banca.des_cc%TYPE,
      num_lote                ccc_movim_banca.num_lote%TYPE,
      tipo                    VARCHAR2(20),
      fec_pago                VARCHAR2(10),
      fec_proc                VARCHAR2(10),
      tot_reg_lote            NUMBER(12),
      imp_pago                NUMBER(14,2)
    );
    TYPE detalleUnidadesRegTab02 IS TABLE OF detalleUnidadesReg02;
    detalleUnidadesRegRecord02 detalleUnidadesRegTab02;

    CURSOR C_REPOR_03 IS
        SELECT
           ccb.des_cc des_banc,
           mb.cod_cons cod_clie,
           (SELECT TRIM(mc.val_ape1) || ' ' || TRIM(mc.val_ape2) || ' '  || TRIM(mc.val_nom1) || ' ' || TRIM(mc.val_nom2)
            FROM mae_clien mc
            WHERE mc.oid_clie = mb.clie_oid_clie) nom_clie,
           CASE
              WHEN cp.cod_proc='TES001' AND su.cod_subp=1 THEN
                'Automatico'
              ELSE
                'Manual'
           END tipo,
           to_char(mb.fec_pago, 'dd/mm/yyyy') fec_pago,
           to_char(mb.fec_proc, 'dd/mm/yyyy') fec_proc,
           mb.imp_pago
        FROM
             ccc_movim_banca mb,
             ccc_cuent_corri_banca ccb,
             ccc_proce cp,
             ccc_subpr su
        WHERE mb.ccba_oid_cc_banc = ccb.oid_cuen_corr_banc
        AND mb.subp_oid_marc_crea = su.oid_subp
        AND cp.oid_proc = su.ccpr_oid_proc
        AND mb.fec_pago >= DECODE(TO_DATE(psfechaPagoDesde,'DD/MM/YYYY'),null,mb.fec_pago,'',mb.fec_pago,TO_DATE(psfechaPagoDesde,'DD/MM/YYYY'))
        AND mb.fec_pago <= DECODE(TO_DATE(psfechaPagoHasta,'DD/MM/YYYY'),null,mb.fec_pago,'',mb.fec_pago,TO_DATE(psfechaPagoHasta,'DD/MM/YYYY'))
        AND mb.fec_proc >= DECODE(TO_DATE(psfechaProcDesde,'DD/MM/YYYY'),null,mb.fec_proc,'',mb.fec_proc,TO_DATE(psfechaProcDesde,'DD/MM/YYYY'))
        AND mb.fec_proc <= DECODE(TO_DATE(psfechaProcHasta,'DD/MM/YYYY'),null,mb.fec_proc,'',mb.fec_proc,TO_DATE(psfechaProcHasta,'DD/MM/YYYY'))
        AND cp.cod_proc = DECODE(pscodigoProceso,null,cp.cod_proc,'',cp.cod_proc,pscodigoProceso)
        AND su.cod_subp = DECODE(pscodigoSubproceso,null,su.cod_subp,'',su.cod_subp,pscodigoSubproceso)
        AND ccb.cod_cc = DECODE(pscodigoBanco,null,ccb.cod_cc,'Todos',ccb.cod_cc,'',ccb.cod_cc,pscodigoBanco)
        and mb.cod_iden_proc='P'
        ORDER BY 5,1,2,3;

    TYPE detalleUnidadesReg03 IS RECORD(
      des_cc                  ccc_cuent_corri_banca.des_cc%TYPE,
      cod_cons                ccc_movim_banca.cod_cons%TYPE,
      nom_clie                VARCHAR2(200),
      tipo                    VARCHAR2(20),
      fec_pago                VARCHAR2(10),
      fec_proc                VARCHAR2(10),
      imp_pago                ccc_movim_banca.imp_pago%TYPE
    );
    TYPE detalleUnidadesRegTab03 IS TABLE OF detalleUnidadesReg03;
    detalleUnidadesRegRecord03 detalleUnidadesRegTab03;

    CURSOR C_REPOR_04 IS
        SELECT
         ccb.des_cc des_banc,
         mb.num_lote,
         mb.cod_cons cod_clie,
         (SELECT TRIM(mc.val_ape1) || ' ' || TRIM(mc.val_ape2) || ' '  || TRIM(mc.val_nom1) || ' ' || TRIM(mc.val_nom2)
          FROM mae_clien mc
          WHERE mc.oid_clie = mb.clie_oid_clie) nom_clie,
          CASE
            WHEN    cp.cod_proc='TES001' AND su.cod_subp=1 THEN
              'Automatico'
            ELSE
              'Manual'
         END tipo,
         to_char(mb.fec_pago, 'dd/mm/yyyy') fec_pago,
         to_char(mb.fec_proc, 'dd/mm/yyyy') fec_proc,
         mb.imp_pago
      FROM
           ccc_movim_banca mb,
           ccc_cuent_corri_banca ccb,
           ccc_proce cp,
           ccc_subpr su
      WHERE mb.ccba_oid_cc_banc = ccb.oid_cuen_corr_banc
      AND mb.subp_oid_marc_crea = su.oid_subp
      AND cp.oid_proc = su.ccpr_oid_proc
      AND mb.fec_pago >= DECODE(TO_DATE(psfechaPagoDesde,'DD/MM/YYYY'),null,mb.fec_pago,'',mb.fec_pago,TO_DATE(psfechaPagoDesde,'DD/MM/YYYY'))
      AND mb.fec_pago <= DECODE(TO_DATE(psfechaPagoHasta,'DD/MM/YYYY'),null,mb.fec_pago,'',mb.fec_pago,TO_DATE(psfechaPagoHasta,'DD/MM/YYYY'))
      AND mb.fec_proc >= DECODE(TO_DATE(psfechaProcDesde,'DD/MM/YYYY'),null,mb.fec_proc,'',mb.fec_proc,TO_DATE(psfechaProcDesde,'DD/MM/YYYY'))
      AND mb.fec_proc <= DECODE(TO_DATE(psfechaProcHasta,'DD/MM/YYYY'),null,mb.fec_proc,'',mb.fec_proc,TO_DATE(psfechaProcHasta,'DD/MM/YYYY'))
      AND cp.cod_proc = DECODE(pscodigoProceso,null,cp.cod_proc,'',cp.cod_proc,pscodigoProceso)
      AND su.cod_subp = DECODE(pscodigoSubproceso,null,su.cod_subp,'',su.cod_subp,pscodigoSubproceso)
      AND ccb.cod_cc = DECODE(pscodigoBanco,null,ccb.cod_cc,'',ccb.cod_cc,pscodigoBanco)
      AND mb.cod_iden_proc='P'
      ORDER BY 6,1,2,3,4;

    TYPE detalleUnidadesReg04 IS RECORD(
      des_cc                  ccc_cuent_corri_banca.des_cc%TYPE,
      num_lote                ccc_movim_banca.num_lote%TYPE,
      cod_cons                ccc_movim_banca.cod_cons%TYPE,
      nom_clie                VARCHAR2(200),
      tipo                    VARCHAR2(20),
      fec_pago                VARCHAR2(10),
      fec_proc                VARCHAR2(10),
      imp_pago                ccc_movim_banca.imp_pago%TYPE
    );
    TYPE detalleUnidadesRegTab04 IS TABLE OF detalleUnidadesReg04;
    detalleUnidadesRegRecord04 detalleUnidadesRegTab04;

    CURSOR C_REPOR_05 IS
        SELECT
          cod_cc,
          des_cc,
          cod_regi,
          cod_zona,
          tipo_cc,
          fec_pago,
          fec_proc,
          tot_reg_lote,
          imp_pago
        FROM CCC_REPOR_LIQUI_COBRA
      ORDER BY cod_zona, des_cc, tipo_cc ;

    TYPE detalleUnidadesReg05 IS RECORD(
      cod_cc                  ccc_cuent_corri_banca.cod_cc%TYPE,
      des_cc                  ccc_cuent_corri_banca.des_cc%TYPE,
      cod_regi                zon_regio.cod_regi%TYPE,
      cod_zona                zon_zona.cod_zona%TYPE,
      tipo                    VARCHAR2(20),
      fec_pago                VARCHAR2(10),
      fec_proc                VARCHAR2(10),
      tot_reg_lote            NUMBER(12),
      imp_pago                NUMBER(14,2)
    );
    TYPE detalleUnidadesRegTab05 IS TABLE OF detalleUnidadesReg05;
    detalleUnidadesRegRecord05 detalleUnidadesRegTab05;

    CURSOR C_REPOR_06 IS
     SELECT
      des_cc,
			cod_regi,
			cod_zona,
			cod_cons,
			nom_clie,
			tipo_cc,
			fec_pago,
			fec_proc,
			imp_pago
     FROM CCC_REPOR_LIQUI_COBRA
     ORDER BY cod_zona, des_cc, tipo_cc, cod_cons ;

    TYPE detalleUnidadesReg06 IS RECORD(
      des_cc                  ccc_cuent_corri_banca.des_cc%TYPE,
      cod_regi                zon_regio.cod_regi%TYPE,
      cod_zona                zon_zona.cod_zona%TYPE,
      cod_cons                ccc_movim_banca.cod_cons%TYPE,
      nom_clie                VARCHAR2(200),
      tipo                    VARCHAR2(20),
      fec_pago                VARCHAR2(10),
      fec_proc                VARCHAR2(10),
      imp_pago                ccc_movim_banca.imp_pago%TYPE
    );
    TYPE detalleUnidadesRegTab06 IS TABLE OF detalleUnidadesReg06;
    detalleUnidadesRegRecord06 detalleUnidadesRegTab06;

    lbAbrirUtlFile  BOOLEAN;
BEGIN

    lbAbrirUtlFile := true;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
    IF pstipoNombreReporte = 'CB' THEN
        OPEN C_REPOR_01;
          LOOP
           FETCH C_REPOR_01 BULK COLLECT INTO detalleUnidadesRegRecord01 LIMIT W_FILAS;
           IF lbAbrirUtlFile THEN
              GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
              psdirectorio := lsdirtempo;
              lbAbrirUtlFile := FALSE;
           END IF ;
           IF detalleUnidadesRegRecord01.COUNT > 0 THEN
              FOR x IN detalleUnidadesRegRecord01.FIRST .. detalleUnidadesRegRecord01.LAST LOOP
                    lslinea :=
                                 '"'|| detalleUnidadesRegRecord01(x).des_cc || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord01(x).tipo || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord01(x).fec_pago || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord01(x).fec_proc || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord01(x).tot_reg_lote || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord01(x).imp_pago || '"' ;
                     UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

              END LOOP;
            END IF;
            EXIT WHEN C_REPOR_01%NOTFOUND;
        END LOOP;
        CLOSE C_REPOR_01;
    ELSIF pstipoNombreReporte = 'CL' THEN
        OPEN C_REPOR_02;
          LOOP
           FETCH C_REPOR_02 BULK COLLECT INTO detalleUnidadesRegRecord02 LIMIT W_FILAS;
           IF lbAbrirUtlFile THEN
              GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
              psdirectorio := lsdirtempo;
              lbAbrirUtlFile := FALSE;
           END IF ;
           IF detalleUnidadesRegRecord02.COUNT > 0 THEN
              FOR x IN detalleUnidadesRegRecord02.FIRST .. detalleUnidadesRegRecord02.LAST LOOP
                    lslinea :=
                                 '"'|| detalleUnidadesRegRecord02(x).des_cc || '"' || ',' ||
                                 '=T("'|| detalleUnidadesRegRecord02(x).num_lote || '")' || ',' ||
                                 '"'|| detalleUnidadesRegRecord02(x).tipo || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord02(x).fec_pago || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord02(x).fec_proc || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord02(x).tot_reg_lote || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord02(x).imp_pago || '"' ;
                     UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

              END LOOP;
            END IF;
            EXIT WHEN C_REPOR_02%NOTFOUND;
        END LOOP;
        CLOSE C_REPOR_02;

    ELSIF pstipoNombreReporte = 'DB' THEN
       OPEN C_REPOR_03;
          LOOP
           FETCH C_REPOR_03 BULK COLLECT INTO detalleUnidadesRegRecord03 LIMIT W_FILAS;
           IF lbAbrirUtlFile THEN
              GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
              psdirectorio := lsdirtempo;
              lbAbrirUtlFile := FALSE;
           END IF ;
           IF detalleUnidadesRegRecord03.COUNT > 0 THEN
              FOR x IN detalleUnidadesRegRecord03.FIRST .. detalleUnidadesRegRecord03.LAST LOOP
                    lslinea :=   '"'|| detalleUnidadesRegRecord03(x).des_cc || '"' || ',' ||
                                 '=T("'|| detalleUnidadesRegRecord03(x).cod_cons || '")' || ',' ||
                                 '"'|| detalleUnidadesRegRecord03(x).nom_clie || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord03(x).tipo || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord03(x).fec_pago || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord03(x).fec_proc || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord03(x).imp_pago || '"' ;
                     UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

              END LOOP;
            END IF;
            EXIT WHEN C_REPOR_03%NOTFOUND;
        END LOOP;
        CLOSE C_REPOR_03;
    ELSIF pstipoNombreReporte = 'DL' THEN
        OPEN C_REPOR_04;
          LOOP
           FETCH C_REPOR_04 BULK COLLECT INTO detalleUnidadesRegRecord04 LIMIT W_FILAS;
           IF lbAbrirUtlFile THEN
              GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
              psdirectorio := lsdirtempo;
              lbAbrirUtlFile := FALSE;
           END IF ;
           IF detalleUnidadesRegRecord04.COUNT > 0 THEN
              FOR x IN detalleUnidadesRegRecord04.FIRST .. detalleUnidadesRegRecord04.LAST LOOP
                    lslinea :=   '"'|| detalleUnidadesRegRecord04(x).des_cc || '"' || ',' ||
                                 '=T("'|| detalleUnidadesRegRecord04(x).num_lote || '")' || ',' ||
                                 '=T("'|| detalleUnidadesRegRecord04(x).cod_cons || '")' || ',' ||
                                 '"'|| detalleUnidadesRegRecord04(x).nom_clie || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord04(x).tipo || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord04(x).fec_pago || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord04(x).fec_proc || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord04(x).imp_pago || '"' ;
                     UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

              END LOOP;
            END IF;
            EXIT WHEN C_REPOR_04%NOTFOUND;
        END LOOP;
        CLOSE C_REPOR_04;
    ELSIF pstipoNombreReporte = 'CZ' THEN
        OPEN C_REPOR_05;
          LOOP
           FETCH C_REPOR_05 BULK COLLECT INTO detalleUnidadesRegRecord05 LIMIT W_FILAS;
           IF lbAbrirUtlFile THEN
              GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
              psdirectorio := lsdirtempo;
              lbAbrirUtlFile := FALSE;
           END IF ;
           IF detalleUnidadesRegRecord05.COUNT > 0 THEN
              FOR x IN detalleUnidadesRegRecord05.FIRST .. detalleUnidadesRegRecord05.LAST LOOP
                    lslinea :=
                                 '"'|| detalleUnidadesRegRecord05(x).des_cc || '"' || ',' ||
                                 '=T("'|| detalleUnidadesRegRecord05(x).cod_regi || '")' || ',' ||
                                 '=T("'|| detalleUnidadesRegRecord05(x).cod_zona || '")' || ',' ||
                                 '"'|| detalleUnidadesRegRecord05(x).tipo || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord05(x).fec_pago || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord05(x).fec_proc || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord05(x).tot_reg_lote || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord05(x).imp_pago || '"' ;
                     UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

              END LOOP;
            END IF;
            EXIT WHEN C_REPOR_05%NOTFOUND;
        END LOOP;
        CLOSE C_REPOR_05;
    ELSIF pstipoNombreReporte = 'DZ' THEN
        OPEN C_REPOR_06;
          LOOP
           FETCH C_REPOR_06 BULK COLLECT INTO detalleUnidadesRegRecord06 LIMIT W_FILAS;
           IF lbAbrirUtlFile THEN
              GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
              psdirectorio := lsdirtempo;
              lbAbrirUtlFile := FALSE;
           END IF ;
           IF detalleUnidadesRegRecord06.COUNT > 0 THEN
              FOR x IN detalleUnidadesRegRecord06.FIRST .. detalleUnidadesRegRecord06.LAST LOOP
                    lslinea :=   '"'|| detalleUnidadesRegRecord06(x).des_cc || '"' || ',' ||
                                 '=T("'|| detalleUnidadesRegRecord06(x).cod_regi || '")' || ',' ||
                                 '=T("'|| detalleUnidadesRegRecord06(x).cod_zona || '")' || ',' ||
                                 '=T("'|| detalleUnidadesRegRecord06(x).cod_cons || '")' || ',' ||
                                 '"'|| detalleUnidadesRegRecord06(x).nom_clie || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord06(x).tipo || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord06(x).fec_pago || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord06(x).fec_proc || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord06(x).imp_pago || '"' ;
                     UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

              END LOOP;
            END IF;
            EXIT WHEN C_REPOR_06%NOTFOUND;
        END LOOP;
        CLOSE C_REPOR_06;

    END IF;
    IF NOT lbAbrirUtlFile THEN
        utl_file.fclose(V_HANDLE);
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm,1,250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR ccc_pr_liqui_cobra_csv: '||ls_sqlerrm);

END ccc_pr_liqui_cobra_csv ;

  /************************************************************************************
  Descripcion           : Procedimiento que genera los archivos de Medios Magneticos
                                 Facturas
                                 Notas de Credito (Postventas)
                                 Cargos por Boletas no Exitosas
                                 Reversion de PostVentas
                                 Cargos y Abonos Directos

  Fecha Creacion    : 20/02/2014
  Autor                    : Sebastian Guerra
  ************************************************************************************/
  PROCEDURE CCC_PR_GENER_REPOR_MEDIO_MAGNE(
      pscodigopais            VARCHAR2,
      pscodigousuario       VARCHAR2,
      psdirectorio      OUT VARCHAR2,
      psArchivos       OUT VARCHAR2
  ) IS

    lsdirtempo      bas_inter.dir_temp%TYPE;
    lslinea            varchar2(4000);

    v_handle       utl_file.file_type;
    v_nombre_archivo varchar2(30);
    v_titulo varchar2(500);
    v_archivo varchar(50);
    v_archivos varchar(500);

    lbabrirutlfile1 BOOLEAN;
    lbabrirutlfile2 BOOLEAN;
    lbabrirutlfile3 BOOLEAN;
    lbabrirutlfile4 BOOLEAN;
    lbabrirutlfile5 BOOLEAN;

   TYPE detalleMedioFacturas IS RECORD(
        campania                      CCC_TMP_MMAGT_FACTU.COD_PERI%TYPE,
        tipoDocumento                 CCC_TMP_MMAGT_FACTU.VAL_SIGL%TYPE,
        cedula                        CCC_TMP_MMAGT_FACTU.VAL_NUME_IDEN_NNAL%TYPE,
        factura                       CCC_TMP_MMAGT_FACTU.NUM_DOCU_LEGA%TYPE,
        codigoCliente                       CCC_TMP_MMAGT_FACTU.COD_CLIE%TYPE,
        fecFacturacion                VARCHAR2(10),
        primerNombre                  CCC_TMP_MMAGT_FACTU.VAL_NOM1%TYPE,
        segundoNombre                 CCC_TMP_MMAGT_FACTU.VAL_NOM2%TYPE,
        paterno                       CCC_TMP_MMAGT_FACTU.VAL_APE1%TYPE,
        materno                       CCC_TMP_MMAGT_FACTU.VAL_APE2%TYPE,
        direccion                     CCC_TMP_MMAGT_FACTU.VAL_DIRE_COMP%TYPE,
        telefonoFijo                 CCC_TMP_MMAGT_FACTU.NUM_TELE_FIJO%TYPE,
        telefonoMovil                CCC_TMP_MMAGT_FACTU.NUM_TELE_MOVIL%TYPE,
        correo                        CCC_TMP_MMAGT_FACTU.VAL_MAIL%TYPE,
        pais                          CCC_TMP_MMAGT_FACTU.NOM_PAIS%TYPE,
        departamento                  CCC_TMP_MMAGT_FACTU.VAL_DEPA%TYPE,
        municipio                     CCC_TMP_MMAGT_FACTU.VAL_MUNI%TYPE,
        indicadorSanAndres            CCC_TMP_MMAGT_FACTU.IND_SAN_ANDRE%TYPE,
        neto                          CCC_TMP_MMAGT_FACTU.VAL_PREC_NETO_TOTA_LOCA%TYPE,
        fleteNeto                     CCC_TMP_MMAGT_FACTU.IMP_FLET_IMPU_TOTA_LOCA%TYPE,
        intereses                     CCC_TMP_MMAGT_FACTU.VAL_TOTA_GAST_ADMI_SIN_IMPU%TYPE,
        iva                           CCC_TMP_MMAGT_FACTU.IMP_IMPU_TOTA_LOCA%TYPE,
        total                         CCC_TMP_MMAGT_FACTU.VAL_TOTA_PAGA_LOCA%TYPE
    );

   TYPE detalleMedioFacturasTab IS TABLE OF detalleMedioFacturas;
   detalleMedioFacturasRecord detalleMedioFacturasTab;

   TYPE detaMedioBoleNoExito IS RECORD(
        campania                      CCC_TMP_MMAGT_BOLET_NOEXI.COD_PERI%TYPE,
        tipoDocumento                 CCC_TMP_MMAGT_BOLET_NOEXI.VAL_SIGL%TYPE,
        cedula                        CCC_TMP_MMAGT_BOLET_NOEXI.VAL_NUME_IDEN_NNAL%TYPE,
        oidCabe                       CCC_TMP_MMAGT_BOLET_NOEXI.OID_CABE%TYPE,
        codigoCliente                       CCC_TMP_MMAGT_BOLET_NOEXI.COD_CLIE%TYPE,
        fecFacturacion                VARCHAR2(10),
        primerNombre                  CCC_TMP_MMAGT_BOLET_NOEXI.VAL_NOM1%TYPE,
        segundoNombre                 CCC_TMP_MMAGT_BOLET_NOEXI.VAL_NOM2%TYPE,
        paterno                       CCC_TMP_MMAGT_BOLET_NOEXI.VAL_APE1%TYPE,
        materno                       CCC_TMP_MMAGT_BOLET_NOEXI.VAL_APE2%TYPE,
        direccion                     CCC_TMP_MMAGT_BOLET_NOEXI.VAL_DIRE_COMP%TYPE,
        telefonoFijo                 CCC_TMP_MMAGT_BOLET_NOEXI.NUM_TELE_FIJO%TYPE,
        telefonoMovil                CCC_TMP_MMAGT_BOLET_NOEXI.NUM_TELE_MOVIL%TYPE,
        correo                        CCC_TMP_MMAGT_BOLET_NOEXI.VAL_MAIL%TYPE,
        pais                          CCC_TMP_MMAGT_BOLET_NOEXI.NOM_PAIS%TYPE,
        departamento                  CCC_TMP_MMAGT_BOLET_NOEXI.VAL_DEPA%TYPE,
        municipio                     CCC_TMP_MMAGT_BOLET_NOEXI.VAL_MUNI%TYPE,
        indicadorSanAndres            CCC_TMP_MMAGT_BOLET_NOEXI.IND_SAN_ANDRE%TYPE,
        neto                          CCC_TMP_MMAGT_BOLET_NOEXI.VAL_PREC_NETO_TOTA_LOCA%TYPE,
        fleteNeto                     CCC_TMP_MMAGT_BOLET_NOEXI.IMP_FLET_IMPU_TOTA_LOCA%TYPE,
        intereses                     CCC_TMP_MMAGT_BOLET_NOEXI.VAL_TOTA_GAST_ADMI_SIN_IMPU%TYPE,
        iva                           CCC_TMP_MMAGT_BOLET_NOEXI.IMP_IMPU_TOTA_LOCA%TYPE,
        total                         CCC_TMP_MMAGT_BOLET_NOEXI.VAL_TOTA_PAGA_LOCA%TYPE
    );

   TYPE detaMedioBoleNoExitoTab IS TABLE OF detaMedioBoleNoExito;
   detaMedioBoleNoExitoRecord detaMedioBoleNoExitoTab;

   TYPE detaMedioReverPostVent IS RECORD(
        campania                      CCC_TMP_MEDIO_REVER_POST_VENTA.COD_PERI%TYPE,
        tipoDocumento                 CCC_TMP_MEDIO_REVER_POST_VENTA.VAL_SIGL%TYPE,
        cedula                        CCC_TMP_MEDIO_REVER_POST_VENTA.VAL_NUME_IDEN_NNAL%TYPE,
        oidCabe                       CCC_TMP_MEDIO_REVER_POST_VENTA.OID_CABE%TYPE,
        codigoCliente                       CCC_TMP_MEDIO_REVER_POST_VENTA.COD_CLIE%TYPE,
        fecFacturacion                VARCHAR2(10),
        primerNombre                  CCC_TMP_MEDIO_REVER_POST_VENTA.VAL_NOM1%TYPE,
        segundoNombre                 CCC_TMP_MEDIO_REVER_POST_VENTA.VAL_NOM2%TYPE,
        paterno                       CCC_TMP_MEDIO_REVER_POST_VENTA.VAL_APE1%TYPE,
        materno                       CCC_TMP_MEDIO_REVER_POST_VENTA.VAL_APE2%TYPE,
        direccion                     CCC_TMP_MEDIO_REVER_POST_VENTA.VAL_DIRE_COMP%TYPE,
        telefonoFijo                 CCC_TMP_MEDIO_REVER_POST_VENTA.NUM_TELE_FIJO%TYPE,
        telefonoMovil                CCC_TMP_MEDIO_REVER_POST_VENTA.NUM_TELE_MOVIL%TYPE,
        correo                        CCC_TMP_MEDIO_REVER_POST_VENTA.VAL_MAIL%TYPE,
        pais                          CCC_TMP_MEDIO_REVER_POST_VENTA.NOM_PAIS%TYPE,
        departamento                  CCC_TMP_MEDIO_REVER_POST_VENTA.VAL_DEPA%TYPE,
        municipio                     CCC_TMP_MEDIO_REVER_POST_VENTA.VAL_MUNI%TYPE,
        indicadorSanAndres            CCC_TMP_MEDIO_REVER_POST_VENTA.IND_SAN_ANDRE%TYPE,
        neto                          CCC_TMP_MEDIO_REVER_POST_VENTA.VAL_PREC_NETO_TOTA_LOCA%TYPE,
        fleteNeto                     CCC_TMP_MEDIO_REVER_POST_VENTA.IMP_FLET_IMPU_TOTA_LOCA%TYPE,
        intereses                     CCC_TMP_MEDIO_REVER_POST_VENTA.VAL_TOTA_GAST_ADMI_SIN_IMPU%TYPE,
        iva                           CCC_TMP_MEDIO_REVER_POST_VENTA.IMP_IMPU_TOTA_LOCA%TYPE,
        total                         CCC_TMP_MEDIO_REVER_POST_VENTA.VAL_TOTA_PAGA_LOCA%TYPE
    );

   TYPE detaMedioReverPostVentTab IS TABLE OF detaMedioReverPostVent;
   detaMedioReverPostVentRecord detaMedioReverPostVentTab;

   TYPE detaMedioNotaCred IS RECORD(
        campania                      CCC_TMP_MMAGT_NOTAS_CREDI.COD_PERI%TYPE,
        tipoDocumento                 CCC_TMP_MMAGT_NOTAS_CREDI.VAL_SIGL%TYPE,
        cedula                        CCC_TMP_MMAGT_NOTAS_CREDI.VAL_NUME_IDEN_NNAL%TYPE,
        oidCabe                       CCC_TMP_MMAGT_NOTAS_CREDI.OID_CABE%TYPE,
        codigoCliente                       CCC_TMP_MMAGT_NOTAS_CREDI.COD_CLIE%TYPE,
        valAnulacion                       CCC_TMP_MMAGT_NOTAS_CREDI.VAL_ANUL%TYPE,
        fecFacturacion                VARCHAR2(10),
        primerNombre                  CCC_TMP_MMAGT_NOTAS_CREDI.VAL_NOM1%TYPE,
        segundoNombre                 CCC_TMP_MMAGT_NOTAS_CREDI.VAL_NOM2%TYPE,
        paterno                       CCC_TMP_MMAGT_NOTAS_CREDI.VAL_APE1%TYPE,
        materno                       CCC_TMP_MMAGT_NOTAS_CREDI.VAL_APE2%TYPE,
        direccion                     CCC_TMP_MMAGT_NOTAS_CREDI.VAL_DIRE_COMP%TYPE,
        telefonoFijo                 CCC_TMP_MMAGT_NOTAS_CREDI.NUM_TELE_FIJO%TYPE,
        telefonoMovil                CCC_TMP_MMAGT_NOTAS_CREDI.NUM_TELE_MOVIL%TYPE,
        correo                        CCC_TMP_MMAGT_NOTAS_CREDI.VAL_MAIL%TYPE,
        pais                          CCC_TMP_MMAGT_NOTAS_CREDI.NOM_PAIS%TYPE,
        departamento                  CCC_TMP_MMAGT_NOTAS_CREDI.VAL_DEPA%TYPE,
        municipio                     CCC_TMP_MMAGT_NOTAS_CREDI.VAL_MUNI%TYPE,
        indicadorSanAndres            CCC_TMP_MMAGT_NOTAS_CREDI.IND_SAN_ANDRE%TYPE,
        neto                          CCC_TMP_MMAGT_NOTAS_CREDI.VAL_PREC_NETO_TOTA_LOCA%TYPE,
        fleteNeto                     CCC_TMP_MMAGT_NOTAS_CREDI.IMP_FLET_IMPU_TOTA_LOCA%TYPE,
        intereses                     CCC_TMP_MMAGT_NOTAS_CREDI.VAL_TOTA_GAST_ADMI_SIN_IMPU%TYPE,
        iva                           CCC_TMP_MMAGT_NOTAS_CREDI.IMP_IMPU_TOTA_LOCA%TYPE,
        total                         CCC_TMP_MMAGT_NOTAS_CREDI.VAL_TOTA_PAGA_LOCA%TYPE,
        campaniaReferencia                CCC_TMP_MMAGT_NOTAS_CREDI.VAL_CAMP_REFE%TYPE,
        facturaReferencia                   CCC_TMP_MMAGT_NOTAS_CREDI.NUM_DOCU_LEGA%TYPE
    );

   TYPE detaMedioNotaCredTab IS TABLE OF detaMedioNotaCred;
   detaMedioNotaCredRecord detaMedioNotaCredTab;

   TYPE detaMedioCargoAbono IS RECORD(
        campania                        CCC_TMP_MMAGT_CARGO_ABONO.COD_PERI%TYPE,
        oidMovimiento                        CCC_TMP_MMAGT_CARGO_ABONO.OID_MOVI_CC%TYPE,
        codigoCliente                        CCC_TMP_MMAGT_CARGO_ABONO.COD_CLIE%TYPE,
        tipoDocumento                        CCC_TMP_MMAGT_CARGO_ABONO.VAL_SIGL%TYPE,
        cedula                        CCC_TMP_MMAGT_CARGO_ABONO.NUM_DOCU_IDEN%TYPE,
        telefonoFijo                        CCC_TMP_MMAGT_CARGO_ABONO.NUM_TELE_FIJO%TYPE,
        telefonoMovil                        CCC_TMP_MMAGT_CARGO_ABONO.NUM_TELE_MOVIL%TYPE,
        correo                        CCC_TMP_MMAGT_CARGO_ABONO.VAL_MAIL%TYPE,
        pais                        CCC_TMP_MMAGT_CARGO_ABONO.NOM_PAIS%TYPE,
        departamento                        CCC_TMP_MMAGT_CARGO_ABONO.VAL_DEPA%TYPE,
        municipio                        CCC_TMP_MMAGT_CARGO_ABONO.VAL_MUNI%TYPE,
        tipoCargoAbono                CCC_TMP_MMAGT_CARGO_ABONO.DES_SUBP%TYPE,
        fechaDocumento                VARCHAR2(10),
        nombresApellidos              CCC_TMP_MMAGT_CARGO_ABONO.VAL_NOMB%TYPE,
        direccion                     CCC_TMP_MMAGT_CARGO_ABONO.VAL_DIRE%TYPE,
        telefono                      CCC_TMP_MMAGT_CARGO_ABONO.VAL_TELE%TYPE,
        indExcentoImpuesto            CCC_TMP_MMAGT_CARGO_ABONO.IND_EXCE_IMPU%TYPE,
        importeNeto                   CCC_TMP_MMAGT_CARGO_ABONO.VAL_IMPO_NETO%TYPE,
        flete                         CCC_TMP_MMAGT_CARGO_ABONO.VAL_FLETE%TYPE,
        intereses                     CCC_TMP_MMAGT_CARGO_ABONO.VAL_INTE%TYPE,
        impuesto                      CCC_TMP_MMAGT_CARGO_ABONO.VAL_IMPU%TYPE,
        importeTotal                  CCC_TMP_MMAGT_CARGO_ABONO.IMP_MOVI%TYPE
    );

   TYPE detaMedioCargoAbonoTab IS TABLE OF detaMedioCargoAbono;
   detaMedioCargoAbonoRecord detaMedioCargoAbonoTab;

    CURSOR C_REPOR_MMAGT_FACTU IS
    SELECT
        COD_PERI,
        VAL_SIGL,
        VAL_NUME_IDEN_NNAL,
        NUM_DOCU_LEGA,
        COD_CLIE,
        TO_CHAR(FEC_FACT, 'DD/MM/YYYY') FEC_FACT,
        VAL_NOM1,
        VAL_NOM2,
        VAL_APE1,
        VAL_APE2,
        VAL_DIRE_COMP,
        NUM_TELE_FIJO,
        NUM_TELE_MOVIL,
        VAL_MAIL,
        NOM_PAIS,
        VAL_DEPA,
        VAL_MUNI,
        IND_SAN_ANDRE,
        VAL_PREC_NETO_TOTA_LOCA,
        IMP_FLET_IMPU_TOTA_LOCA,
        VAL_TOTA_GAST_ADMI_SIN_IMPU,
        IMP_IMPU_TOTA_LOCA,
        VAL_TOTA_PAGA_LOCA
    FROM CCC_TMP_MMAGT_FACTU
    WHERE USU_DIGI = pscodigousuario
    ORDER BY NUM_DOCU_LEGA;

    CURSOR C_REPOR_MMAGT_BOLET_NOEXI IS
    SELECT
        COD_PERI,
        VAL_SIGL,
        VAL_NUME_IDEN_NNAL,
        OID_CABE,
        COD_CLIE,
        TO_CHAR(FEC_FACT, 'DD/MM/YYYY') FEC_FACT,
        VAL_NOM1,
        VAL_NOM2,
        VAL_APE1,
        VAL_APE2,
        VAL_DIRE_COMP,
        NUM_TELE_FIJO,
        NUM_TELE_MOVIL,
        VAL_MAIL,
        NOM_PAIS,
        VAL_DEPA,
        VAL_MUNI,
        IND_SAN_ANDRE,
        VAL_PREC_NETO_TOTA_LOCA,
        IMP_FLET_IMPU_TOTA_LOCA,
        VAL_TOTA_GAST_ADMI_SIN_IMPU,
        IMP_IMPU_TOTA_LOCA,
        VAL_TOTA_PAGA_LOCA
    FROM CCC_TMP_MMAGT_BOLET_NOEXI
    WHERE USU_DIGI = pscodigousuario
    ORDER BY OID_CABE;

    CURSOR C_REPOR_MMAGT_REVER_POST_VENTA IS
    SELECT
        COD_PERI,
        VAL_SIGL,
        VAL_NUME_IDEN_NNAL,
        OID_CABE,
        COD_CLIE,
        TO_CHAR(FEC_FACT, 'DD/MM/YYYY') FEC_FACT,
        VAL_NOM1,
        VAL_NOM2,
        VAL_APE1,
        VAL_APE2,
        VAL_DIRE_COMP,
        NUM_TELE_FIJO,
        NUM_TELE_MOVIL,
        VAL_MAIL,
        NOM_PAIS,
        VAL_DEPA,
        VAL_MUNI,
        IND_SAN_ANDRE,
        VAL_PREC_NETO_TOTA_LOCA,
        IMP_FLET_IMPU_TOTA_LOCA,
        VAL_TOTA_GAST_ADMI_SIN_IMPU,
        IMP_IMPU_TOTA_LOCA,
        VAL_TOTA_PAGA_LOCA
    FROM CCC_TMP_MEDIO_REVER_POST_VENTA
    WHERE USU_DIGI = pscodigousuario
    ORDER BY OID_CABE;

    CURSOR C_REPOR_MMAGT_NOTAS_CREDI IS
    SELECT
        COD_PERI,
        VAL_SIGL,
        VAL_NUME_IDEN_NNAL,
        OID_CABE,
        COD_CLIE,
        VAL_ANUL,
        TO_CHAR(FEC_FACT, 'DD/MM/YYYY') FEC_FACT,
        VAL_NOM1,
        VAL_NOM2,
        VAL_APE1,
        VAL_APE2,
        VAL_DIRE_COMP,
        NUM_TELE_FIJO,
        NUM_TELE_MOVIL,
        VAL_MAIL,
        NOM_PAIS,
        VAL_DEPA,
        VAL_MUNI,
        IND_SAN_ANDRE,
        VAL_PREC_NETO_TOTA_LOCA,
        IMP_FLET_IMPU_TOTA_LOCA,
        VAL_TOTA_GAST_ADMI_SIN_IMPU,
        IMP_IMPU_TOTA_LOCA,
        VAL_TOTA_PAGA_LOCA,
        VAL_CAMP_REFE,
        NUM_DOCU_LEGA
    FROM CCC_TMP_MMAGT_NOTAS_CREDI
    WHERE USU_DIGI = pscodigousuario
    ORDER BY OID_CABE;

    CURSOR C_REPOR_MMAGT_CARGO_ABONO IS
    SELECT
        COD_PERI,
        OID_MOVI_CC,
        COD_CLIE,
        VAL_SIGL,
        NUM_DOCU_IDEN,
        NUM_TELE_FIJO,
        NUM_TELE_MOVIL,
        VAL_MAIL,
        NOM_PAIS,
        VAL_DEPA,
        VAL_MUNI,
        DES_SUBP,
        TO_CHAR(FEC_DOCU, 'DD/MM/YYYY') FEC_DOCU,
        VAL_NOMB,
        VAL_DIRE,
        VAL_TELE,
        IND_EXCE_IMPU,
        VAL_IMPO_NETO,
        VAL_FLETE,
        VAL_INTE,
        VAL_IMPU,
        IMP_MOVI
    FROM CCC_TMP_MMAGT_CARGO_ABONO
    WHERE USU_DIGI = pscodigousuario
    ORDER BY FEC_DOCU;

  BEGIN

        EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
        lbabrirutlfile1 := true;
        lbabrirutlfile2 := true;
        lbabrirutlfile3 := true;
        lbabrirutlfile4 := true;
        lbabrirutlfile5 := true;
        v_archivos := '';

        SELECT TO_CHAR (SYSDATE, 'yyyymmddhh24miss')
          INTO v_nombre_archivo
          FROM DUAL;

        IF lbabrirutlfile1 THEN
            v_archivo := 'Facturas_' || v_nombre_archivo;
            v_titulo := 'Campania,Tipo Documento,Cedula,Factura,Codigo cliente,Fecha Facturacion,Primer Nombre,Segundo Nombre,Apellido paterno,Apellido materno,Direccion,Telefono Fijo,Telefono Movil,Correo,Pais,Departamento,Municipio,Indicador San Andres,Neto,Flete Neto,Intereses,Iva,Total';
            GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, v_archivo, '.csv', v_titulo, lsdirtempo, v_handle);
            psdirectorio   := lsdirtempo;
            lbabrirutlfile1 := FALSE;
        END IF;

        OPEN C_REPOR_MMAGT_FACTU;
        LOOP
         FETCH C_REPOR_MMAGT_FACTU BULK COLLECT INTO detalleMedioFacturasRecord LIMIT w_filas;
         IF detalleMedioFacturasRecord.COUNT > 0 THEN
            FOR x IN detalleMedioFacturasRecord.FIRST .. detalleMedioFacturasRecord.LAST LOOP
                  lslinea :=
                                '"'|| detalleMedioFacturasRecord(x).campania || '"' || ',' ||
                                '"'|| detalleMedioFacturasRecord(x).tipoDocumento || '"' || ',' ||
                                '=T("'|| detalleMedioFacturasRecord(x).cedula || '")' || ',' ||
                                '=T("'|| detalleMedioFacturasRecord(x).factura || '")' || ',' ||
                                '=T("'|| detalleMedioFacturasRecord(x).codigoCliente || '")' || ',' ||
                                '"'|| detalleMedioFacturasRecord(x).fecFacturacion || '"' || ',' ||
                                '"'|| detalleMedioFacturasRecord(x).primerNombre || '"' || ',' ||
                                '"'|| detalleMedioFacturasRecord(x).segundoNombre || '"' || ',' ||
                                '"'|| detalleMedioFacturasRecord(x).paterno || '"' || ',' ||
                                '"'|| detalleMedioFacturasRecord(x).materno || '"' || ',' ||
                                '"'|| detalleMedioFacturasRecord(x).direccion || '"' || ',' ||
                                '"'|| detalleMedioFacturasRecord(x).telefonoFijo || '"' || ',' ||
                                '"'|| detalleMedioFacturasRecord(x).telefonoMovil || '"' || ',' ||
                                '"'|| detalleMedioFacturasRecord(x).correo || '"' || ',' ||
                                '"'|| detalleMedioFacturasRecord(x).pais || '"' || ',' ||
                                '"'|| detalleMedioFacturasRecord(x).departamento || '"' || ',' ||
                                '"'|| detalleMedioFacturasRecord(x).municipio || '"' || ',' ||
                                '"'|| detalleMedioFacturasRecord(x).indicadorSanAndres || '"' || ',' ||
                                '"'|| detalleMedioFacturasRecord(x).neto || '"' || ',' ||
                                '"'|| detalleMedioFacturasRecord(x).fleteNeto || '"' || ',' ||
                                '"'|| detalleMedioFacturasRecord(x).intereses || '"' || ',' ||
                                '"'|| detalleMedioFacturasRecord(x).iva || '"' || ',' ||
                                '"'|| detalleMedioFacturasRecord(x).total || '"' ;

                   UTL_FILE.PUT_LINE (v_handle, lslinea);

            END LOOP;
          END IF;
          EXIT WHEN C_REPOR_MMAGT_FACTU%NOTFOUND;
        END LOOP;
        CLOSE C_REPOR_MMAGT_FACTU;

        IF NOT lbabrirutlfile1 THEN
            v_archivos := v_archivos || ',' || v_archivo||'.csv';
            utl_file.fclose(v_handle);
        END IF;

        IF lbabrirutlfile2 THEN
            v_archivo := 'BoletasNoExitosas_' || v_nombre_archivo;
            v_titulo := 'Campa?a,Tipo documento,Cedula,Numero Factura,Codigo cliente,Fecha facturacion,Primer nombre,Segundo nombre,Apellido paterno,Apellido materno,Direccion,Telefono fijo,Telefono movil,Correo,Pais,Departamento,Municipio,Indicador San Andres,Neto,Flete neto,Intereses,Iva,Total';
            GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, v_archivo, '.csv', v_titulo, lsdirtempo, v_handle);
            psdirectorio   := lsdirtempo;
            lbabrirutlfile2 := FALSE;
        END IF;

        OPEN C_REPOR_MMAGT_BOLET_NOEXI;
        LOOP
         FETCH C_REPOR_MMAGT_BOLET_NOEXI BULK COLLECT INTO detaMedioBoleNoExitoRecord LIMIT w_filas;
         IF detaMedioBoleNoExitoRecord.COUNT > 0 THEN
            FOR x IN detaMedioBoleNoExitoRecord.FIRST .. detaMedioBoleNoExitoRecord.LAST LOOP
                  lslinea :=
                                '"'|| detaMedioBoleNoExitoRecord(x).campania || '"' || ',' ||
                                '"'|| detaMedioBoleNoExitoRecord(x).tipoDocumento || '"' || ',' ||
                                '=T("'|| detaMedioBoleNoExitoRecord(x).cedula || '")' || ',' ||
                                '=T("'|| detaMedioBoleNoExitoRecord(x).oidCabe || '")' || ',' ||
                                '=T("'|| detaMedioBoleNoExitoRecord(x).codigoCliente || '")' || ',' ||
                                '"'|| detaMedioBoleNoExitoRecord(x).fecFacturacion || '"' || ',' ||
                                '"'|| detaMedioBoleNoExitoRecord(x).primerNombre || '"' || ',' ||
                                '"'|| detaMedioBoleNoExitoRecord(x).segundoNombre || '"' || ',' ||
                                '"'|| detaMedioBoleNoExitoRecord(x).paterno || '"' || ',' ||
                                '"'|| detaMedioBoleNoExitoRecord(x).materno || '"' || ',' ||
                                '"'|| detaMedioBoleNoExitoRecord(x).direccion || '"' || ',' ||
                                '"'|| detaMedioBoleNoExitoRecord(x).telefonoFijo || '"' || ',' ||
                                '"'|| detaMedioBoleNoExitoRecord(x).telefonoMovil || '"' || ',' ||
                                '"'|| detaMedioBoleNoExitoRecord(x).correo || '"' || ',' ||
                                '"'|| detaMedioBoleNoExitoRecord(x).pais || '"' || ',' ||
                                '"'|| detaMedioBoleNoExitoRecord(x).departamento || '"' || ',' ||
                                '"'|| detaMedioBoleNoExitoRecord(x).municipio || '"' || ',' ||
                                '"'|| detaMedioBoleNoExitoRecord(x).indicadorSanAndres || '"' || ',' ||
                                '"'|| detaMedioBoleNoExitoRecord(x).neto || '"' || ',' ||
                                '"'|| detaMedioBoleNoExitoRecord(x).fleteNeto || '"' || ',' ||
                                '"'|| detaMedioBoleNoExitoRecord(x).intereses || '"' || ',' ||
                                '"'|| detaMedioBoleNoExitoRecord(x).iva || '"' || ',' ||
                                '"'|| detaMedioBoleNoExitoRecord(x).total || '"' ;

                   UTL_FILE.PUT_LINE (v_handle, lslinea);

            END LOOP;
          END IF;
          EXIT WHEN C_REPOR_MMAGT_BOLET_NOEXI%NOTFOUND;
        END LOOP;
        CLOSE C_REPOR_MMAGT_BOLET_NOEXI;

        IF NOT lbabrirutlfile2 THEN
            v_archivos := v_archivos || ',' || v_archivo||'.csv';
            utl_file.fclose(v_handle);
        END IF;

        IF lbabrirutlfile3 THEN
            v_archivo := 'ReversionPostVenta_' || v_nombre_archivo;
            v_titulo := 'Campa?a,Tipo documento,Cedula,Numero Factura,Codigo cliente,Fecha facturacion,Primer nombre,Segundo nombre,Apellido paterno,Apellido materno,Direccion,Telefono fijo,Telefono movil,Correo,Pais,Departamento,Municipio,Indicador San Andres,Neto,Flete neto,Intereses,Iva,Total';
            GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, v_archivo, '.csv', v_titulo, lsdirtempo, v_handle);
            psdirectorio   := lsdirtempo;
            lbabrirutlfile3 := FALSE;
        END IF;

        OPEN C_REPOR_MMAGT_REVER_POST_VENTA;
        LOOP
         FETCH C_REPOR_MMAGT_REVER_POST_VENTA BULK COLLECT INTO detaMedioReverPostVentRecord LIMIT w_filas;
         IF detaMedioReverPostVentRecord.COUNT > 0 THEN
            FOR x IN detaMedioReverPostVentRecord.FIRST .. detaMedioReverPostVentRecord.LAST LOOP
                  lslinea :=
                                '"'|| detaMedioReverPostVentRecord(x).campania || '"' || ',' ||
                                '"'|| detaMedioReverPostVentRecord(x).tipoDocumento || '"' || ',' ||
                                '=T("'|| detaMedioReverPostVentRecord(x).cedula || '")' || ',' ||
                                '=T("'|| detaMedioReverPostVentRecord(x).oidCabe || '")' || ',' ||
                                '=T("'|| detaMedioReverPostVentRecord(x).codigoCliente || '")' || ',' ||
                                '"'|| detaMedioReverPostVentRecord(x).fecFacturacion || '"' || ',' ||
                                '"'|| detaMedioReverPostVentRecord(x).primerNombre || '"' || ',' ||
                                '"'|| detaMedioReverPostVentRecord(x).segundoNombre || '"' || ',' ||
                                '"'|| detaMedioReverPostVentRecord(x).paterno || '"' || ',' ||
                                '"'|| detaMedioReverPostVentRecord(x).materno || '"' || ',' ||
                                '"'|| detaMedioReverPostVentRecord(x).direccion || '"' || ',' ||
                                '"'|| detaMedioReverPostVentRecord(x).telefonoFijo || '"' || ',' ||
                                '"'|| detaMedioReverPostVentRecord(x).telefonoMovil || '"' || ',' ||
                                '"'|| detaMedioReverPostVentRecord(x).correo || '"' || ',' ||
                                '"'|| detaMedioReverPostVentRecord(x).pais || '"' || ',' ||
                                '"'|| detaMedioReverPostVentRecord(x).departamento || '"' || ',' ||
                                '"'|| detaMedioReverPostVentRecord(x).municipio || '"' || ',' ||
                                '"'|| detaMedioReverPostVentRecord(x).indicadorSanAndres || '"' || ',' ||
                                '"'|| detaMedioReverPostVentRecord(x).neto || '"' || ',' ||
                                '"'|| detaMedioReverPostVentRecord(x).fleteNeto || '"' || ',' ||
                                '"'|| detaMedioReverPostVentRecord(x).intereses || '"' || ',' ||
                                '"'|| detaMedioReverPostVentRecord(x).iva || '"' || ',' ||
                                '"'|| detaMedioReverPostVentRecord(x).total || '"' ;

                   UTL_FILE.PUT_LINE (v_handle, lslinea);

            END LOOP;
          END IF;
          EXIT WHEN C_REPOR_MMAGT_REVER_POST_VENTA%NOTFOUND;
        END LOOP;
        CLOSE C_REPOR_MMAGT_REVER_POST_VENTA;

        IF NOT lbabrirutlfile3 THEN
            v_archivos := v_archivos || ',' || v_archivo||'.csv';
            utl_file.fclose(v_handle);
        END IF;

        IF lbabrirutlfile4 THEN
            v_archivo := 'NotasCredito_' || v_nombre_archivo;
            v_titulo := 'Campa?a,Tipo documento,Cedula,Id,Codigo cliente,Tipo,Fecha facturacion,Primer nombre,Segundo nombre,Apellido paterno,Apellido materno,Direccion,Telefono fijo,Telefono movil,Correo,Pais,Departamento,Municipio,Indicador San Andres,Neto,Flete neto,Intereses,Iva,Total,Campa?a referencia,Factura referencia';
            GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, v_archivo, '.csv', v_titulo, lsdirtempo, v_handle);
            psdirectorio   := lsdirtempo;
            lbabrirutlfile4 := FALSE;
        END IF;

        OPEN C_REPOR_MMAGT_NOTAS_CREDI;
        LOOP
         FETCH C_REPOR_MMAGT_NOTAS_CREDI BULK COLLECT INTO detaMedioNotaCredRecord LIMIT w_filas;
         IF detaMedioNotaCredRecord.COUNT > 0 THEN
            FOR x IN detaMedioNotaCredRecord.FIRST .. detaMedioNotaCredRecord.LAST LOOP
                  lslinea :=
                                '"'|| detaMedioNotaCredRecord(x).campania || '"' || ',' ||
                                '"'|| detaMedioNotaCredRecord(x).tipoDocumento || '"' || ',' ||
                                '=T("'|| detaMedioNotaCredRecord(x).cedula || '")' || ',' ||
                                '=T("'|| detaMedioNotaCredRecord(x).oidCabe || '")' || ',' ||
                                '=T("'|| detaMedioNotaCredRecord(x).codigoCliente || '")' || ',' ||
                                '"'|| detaMedioNotaCredRecord(x).valAnulacion || '"' || ',' ||
                                '"'|| detaMedioNotaCredRecord(x).fecFacturacion || '"' || ',' ||
                                '"'|| detaMedioNotaCredRecord(x).primerNombre || '"' || ',' ||
                                '"'|| detaMedioNotaCredRecord(x).segundoNombre || '"' || ',' ||
                                '"'|| detaMedioNotaCredRecord(x).paterno || '"' || ',' ||
                                '"'|| detaMedioNotaCredRecord(x).materno || '"' || ',' ||
                                '"'|| detaMedioNotaCredRecord(x).direccion || '"' || ',' ||
                                '"'|| detaMedioNotaCredRecord(x).telefonoFijo || '"' || ',' ||
                                '"'|| detaMedioNotaCredRecord(x).telefonoMovil || '"' || ',' ||
                                '"'|| detaMedioNotaCredRecord(x).correo || '"' || ',' ||
                                '"'|| detaMedioNotaCredRecord(x).pais || '"' || ',' ||
                                '"'|| detaMedioNotaCredRecord(x).departamento || '"' || ',' ||
                                '"'|| detaMedioNotaCredRecord(x).municipio || '"' || ',' ||
                                '"'|| detaMedioNotaCredRecord(x).indicadorSanAndres || '"' || ',' ||
                                '"'|| detaMedioNotaCredRecord(x).neto || '"' || ',' ||
                                '"'|| detaMedioNotaCredRecord(x).fleteNeto || '"' || ',' ||
                                '"'|| detaMedioNotaCredRecord(x).intereses || '"' || ',' ||
                                '"'|| detaMedioNotaCredRecord(x).iva || '"' || ',' ||
                                '"'|| detaMedioNotaCredRecord(x).total || '"' || ',' ||
                                '"'|| detaMedioNotaCredRecord(x).campaniaReferencia || '"' || ',' ||
                                '"'|| detaMedioNotaCredRecord(x).facturaReferencia || '"' ;

                   UTL_FILE.PUT_LINE (v_handle, lslinea);

            END LOOP;
          END IF;
          EXIT WHEN C_REPOR_MMAGT_NOTAS_CREDI%NOTFOUND;
        END LOOP;
        CLOSE C_REPOR_MMAGT_NOTAS_CREDI;

        IF NOT lbabrirutlfile4 THEN
            v_archivos := v_archivos || ',' || v_archivo||'.csv';
            utl_file.fclose(v_handle);
        END IF;

        IF lbabrirutlfile5 THEN
            v_archivo := 'CargosAbonosDirectos_' || v_nombre_archivo;
            v_titulo := 'Campa?a,Id,Codigo cliente,Tipo Documento,Cedula,Telefono fijo,Telefono movil,Correo,Pais,Departamento,Municipio,Tipo cargo abono,Fecha documento,Nombres y apellidos,Direccion,Telefono,Indicador Excento Impuesto,Importe Neto,Flete,Intereses,Impuesto,Importe Total';
            GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, v_archivo, '.csv', v_titulo, lsdirtempo, v_handle);
            psdirectorio   := lsdirtempo;
            lbabrirutlfile5 := FALSE;
        END IF;

        OPEN C_REPOR_MMAGT_CARGO_ABONO;
        LOOP
         FETCH C_REPOR_MMAGT_CARGO_ABONO BULK COLLECT INTO detaMedioCargoAbonoRecord LIMIT w_filas;
         IF detaMedioCargoAbonoRecord.COUNT > 0 THEN
            FOR x IN detaMedioCargoAbonoRecord.FIRST .. detaMedioCargoAbonoRecord.LAST LOOP
                  lslinea :=
                                '"'|| detaMedioCargoAbonoRecord(x).campania || '"' || ',' ||
                                '=T("'|| detaMedioCargoAbonoRecord(x).oidMovimiento || '")' || ',' ||
                                '=T("'|| detaMedioCargoAbonoRecord(x).codigoCliente || '")' || ',' ||
                                '"'|| detaMedioCargoAbonoRecord(x).tipoDocumento || '"' || ',' ||
                                '=T("'|| detaMedioCargoAbonoRecord(x).cedula || '")' || ',' ||
                                '"'|| detaMedioCargoAbonoRecord(x).telefonoFijo || '"' || ',' ||
                                '"'|| detaMedioCargoAbonoRecord(x).telefonoMovil || '"' || ',' ||
                                '"'|| detaMedioCargoAbonoRecord(x).correo || '"' || ',' ||
                                '"'|| detaMedioCargoAbonoRecord(x).pais || '"' || ',' ||
                                '"'|| detaMedioCargoAbonoRecord(x).departamento || '"' || ',' ||
                                '"'|| detaMedioCargoAbonoRecord(x).municipio || '"' || ',' ||
                                '"'|| detaMedioCargoAbonoRecord(x).tipoCargoAbono || '"' || ',' ||
                                '"'|| detaMedioCargoAbonoRecord(x).fechaDocumento || '"' || ',' ||
                                '"'|| detaMedioCargoAbonoRecord(x).nombresApellidos || '"' || ',' ||
                                '"'|| detaMedioCargoAbonoRecord(x).direccion || '"' || ',' ||
                                '"'|| detaMedioCargoAbonoRecord(x).telefono || '"' || ',' ||
                                '"'|| detaMedioCargoAbonoRecord(x).indExcentoImpuesto || '"' || ',' ||
                                '"'|| detaMedioCargoAbonoRecord(x).importeNeto || '"' || ',' ||
                                '"'|| detaMedioCargoAbonoRecord(x).flete || '"' || ',' ||
                                '"'|| detaMedioCargoAbonoRecord(x).intereses || '"' || ',' ||
                                '"'|| detaMedioCargoAbonoRecord(x).impuesto || '"' || ',' ||
                                '"'|| detaMedioCargoAbonoRecord(x).importeTotal || '"' ;

                   UTL_FILE.PUT_LINE (v_handle, lslinea);

            END LOOP;
          END IF;
          EXIT WHEN C_REPOR_MMAGT_CARGO_ABONO%NOTFOUND;
        END LOOP;
        CLOSE C_REPOR_MMAGT_CARGO_ABONO;

        IF NOT lbabrirutlfile5 THEN
            v_archivos := v_archivos || ',' || v_archivo||'.csv';
            utl_file.fclose(v_handle);
        END IF;

        psArchivos := substr(v_archivos, 2);

  EXCEPTION
      WHEN OTHERS THEN
          ln_sqlcode := sqlcode;
          ls_sqlerrm := substr(sqlerrm,1,250);
          RAISE_APPLICATION_ERROR(-20123, 'ERROR CCC_PR_GENER_REPOR_MEDIO_MAGNE: '||ls_sqlerrm);

  END CCC_PR_GENER_REPOR_MEDIO_MAGNE;

  /************************************************************************************
  Descripcion           : Procedimiento que limpia las tablas temporales de Medios Magneticos
                                 Facturas
                                 Notas de Credito (Postventas)
                                 Cargos por Boletas no Exitosas
                                 Reversion de PostVentas
                                 Cargos y Abonos Directos

  Fecha Creacion    : 21/02/2014
  Autor                    : Sebastian Guerra
  ************************************************************************************/
  PROCEDURE CCC_PR_CLEAR_TABLA_MEDIO_MAGNE(
      pscodigousuario       VARCHAR2
  ) IS

  BEGIN

    DELETE FROM ccc_tmp_mmagt_factu
          WHERE usu_digi = pscodigousuario;

    DELETE FROM ccc_tmp_mmagt_bolet_noexi
          WHERE usu_digi = pscodigousuario;

    DELETE FROM ccc_tmp_medio_rever_post_venta
          WHERE usu_digi = pscodigousuario;

    DELETE FROM ccc_tmp_mmagt_notas_credi
          WHERE usu_digi = pscodigousuario;

    DELETE FROM ccc_tmp_mmagt_cargo_abono
          WHERE usu_digi = pscodigousuario;

  EXCEPTION
      WHEN OTHERS THEN
          ln_sqlcode := sqlcode;
          ls_sqlerrm := substr(sqlerrm,1,250);
          RAISE_APPLICATION_ERROR(-20123, 'ERROR CCC_PR_CLEAR_TABLA_MEDIO_MAGNE: '||ls_sqlerrm);

  END CCC_PR_CLEAR_TABLA_MEDIO_MAGNE;

  /************************************************************************************
  Descripcion           : Inserta en tabla temporal la generacion del Reporte de Medios Magneticos
                                 Facturas
                                 Notas de Credito (Postventas)
                                 Cargos por Boletas no Exitosas
                                 Reversion de PostVentas
                                 Cargos y Abonos Directos

  Fecha Creacion    : 21/02/2014
  Autor                    : Sebastian Guerra
  ************************************************************************************/
  PROCEDURE CCC_PR_CARGA_REPOR_MEDIO_MAGNE(
      pscodigousuario       VARCHAR2,
      psfechadesde          VARCHAR2,
      psfechahasta           VARCHAR2
  ) IS

  BEGIN

    INSERT INTO ccc_tmp_mmagt_factu (
        cod_peri,
        val_sigl,
        val_nume_iden_nnal,
        num_docu_lega,
        cod_clie,
        fec_fact,
        val_nom1,
        val_nom2,
        val_ape1,
        val_ape2,
        val_dire_comp,
        num_tele_fijo,
        num_tele_movil,
        val_mail,
        nom_pais,
        val_depa,
        val_muni,
        ind_san_andre,
        val_prec_neto_tota_loca,
        imp_flet_impu_tota_loca,
        val_tota_gast_admi_sin_impu,
        imp_impu_tota_loca,
        val_tota_paga_loca,
        usu_digi
    )
    SELECT
         d.cod_peri,
         e.val_sigl,
         a.val_nume_iden_nnal,
         a.num_docu_lega,
         g.cod_clie,
         a.fec_fact,
         a.val_nom1,
         a.val_nom2,
         a.val_ape1,
         a.val_ape2,
         replace(a.val_dire_comp,',') val_dire_comp,
         (SELECT val_text_comu
            FROM mae_clien_comun
           WHERE clie_oid_clie = b.clie_oid_clie
             AND ticm_oid_tipo_comu = 1
             AND ROWNUM = 1) num_tele_fijo,
         (SELECT val_text_comu
            FROM mae_clien_comun
           WHERE clie_oid_clie = b.clie_oid_clie
             AND ticm_oid_tipo_comu = 6
             AND ROWNUM = 1) num_tele_movil,
         (SELECT val_text_comu
            FROM mae_clien_comun
           WHERE clie_oid_clie = b.clie_oid_clie
             AND ticm_oid_tipo_comu = 3
             AND ROWNUM = 1) val_mail,
         'COLOMBIA' nom_pais,
         (SELECT des_geog
            FROM zon_valor_estru_geopo
           WHERE orde_1 = SUBSTR (f.cod_unid_geog, 1, 6)
             AND orde_2 IS NULL
             AND orde_3 IS NULL
             AND ROWNUM = 1) val_depa,
         (SELECT des_geog
            FROM zon_valor_estru_geopo
           WHERE orde_1 = SUBSTR (f.cod_unid_geog, 1, 6)
             AND orde_2 = SUBSTR (f.cod_unid_geog, 7, 6)
             AND orde_3 IS NULL
             AND ROWNUM = 1) val_muni,
         DECODE (b.val_tasa_impu, 0, 'SI', 'NO') ind_san_andre,
         (SELECT SUM( val_prec_sin_impu_tota_loca -
                                 imp_desc_sin_impu_tota_loca-
                                 decode(val_prec_cont_tota_loca, 0, 0, val_prec_sin_impu_tota_loca) )
          FROM fac_docum_conta_linea
          WHERE dcca_oid_cabe=a.oid_cabe) val_prec_neto_tota_loca,
         a.imp_flet_impu_tota_loca,
         a.val_tota_gast_admi_sin_impu,
         a.imp_impu_tota_loca,
         a.val_tota_paga_loca,
         pscodigousuario usu_digi
    FROM fac_docum_conta_cabec a,
         ped_solic_cabec b,
         cra_perio c,
         seg_perio_corpo d,
         mae_tipo_docum e,
         mae_clien_direc f,
         mae_clien g,
         ped_tipo_solic_pais h,
         ped_tipo_solic i
    WHERE a.soca_oid_soli_cabe = b.oid_soli_cabe
         AND f.clie_oid_clie=g.oid_clie
         AND a.perd_oid_peri = c.oid_peri
         AND c.peri_oid_peri = d.oid_peri
         AND b.cldi_oid_clie_dire = f.oid_clie_dire
         AND b.tdoc_oid_tipo_docu = e.oid_tipo_docu
         AND a.tido_oid_tipo_docu = 1
         AND nvl(a.val_tota_paga_loca,0) >= 0
         AND b.tspa_oid_tipo_soli_pais=h.oid_tipo_soli_pais
         and h.tsol_oid_tipo_soli=i.oid_tipo_soli
         and i.cod_tipo_soli not in ('CBR','C78')
         AND a.fec_fact>=NVL(TO_DATE(psfechadesde,'DD/MM/YYYY'), a.fec_fact)
         AND a.fec_fact<=NVL(TO_DATE(psfechahasta,'DD/MM/YYYY'), a.fec_fact);

    INSERT INTO ccc_tmp_mmagt_bolet_noexi (
        cod_peri,
        val_sigl,
        val_nume_iden_nnal,
        cod_clie,
        fec_fact,
        val_nom1,
        val_nom2,
        val_ape1,
        val_ape2,
        val_dire_comp,
        num_tele_fijo,
        num_tele_movil,
        val_mail,
        nom_pais,
        val_depa,
        val_muni,
        ind_san_andre,
        val_prec_neto_tota_loca,
        imp_flet_impu_tota_loca,
        val_tota_gast_admi_sin_impu,
        imp_impu_tota_loca,
        val_tota_paga_loca,
        oid_cabe,
        usu_digi
    )
    SELECT
         d.cod_peri,
         e.val_sigl,
         a.val_nume_iden_nnal,
         g.cod_clie,
         a.fec_fact,
         a.val_nom1,
         a.val_nom2,
         a.val_ape1,
         a.val_ape2,
         replace(a.val_dire_comp,',') val_dire_comp,
         (SELECT val_text_comu
            FROM mae_clien_comun
           WHERE clie_oid_clie = b.clie_oid_clie
             AND ticm_oid_tipo_comu = 1
             AND ROWNUM = 1) num_tele_fijo,
         (SELECT val_text_comu
            FROM mae_clien_comun
           WHERE clie_oid_clie = b.clie_oid_clie
             AND ticm_oid_tipo_comu = 6
             AND ROWNUM = 1) num_tele_movil,
         (SELECT val_text_comu
            FROM mae_clien_comun
           WHERE clie_oid_clie = b.clie_oid_clie
             AND ticm_oid_tipo_comu = 3
             AND ROWNUM = 1) val_mail,
         'COLOMBIA' nom_pais,
         (SELECT des_geog
            FROM zon_valor_estru_geopo
           WHERE orde_1 = SUBSTR (f.cod_unid_geog, 1, 6)
             AND orde_2 IS NULL
             AND orde_3 IS NULL
             AND ROWNUM = 1) val_depa,
         (SELECT des_geog
            FROM zon_valor_estru_geopo
           WHERE orde_1 = SUBSTR (f.cod_unid_geog, 1, 6)
             AND orde_2 = SUBSTR (f.cod_unid_geog, 7, 6)
             AND orde_3 IS NULL
             AND ROWNUM = 1) val_muni,
         DECODE (b.val_tasa_impu, 0, 'SI', 'NO') ind_san_andre,
         (SELECT SUM( val_prec_sin_impu_tota_loca -
                                 imp_desc_sin_impu_tota_loca -
                                 DECODE(val_prec_cont_tota_loca, 0, 0, val_prec_sin_impu_tota_loca) )
            FROM fac_docum_conta_linea
           WHERE dcca_oid_cabe=a.oid_cabe) val_prec_neto_tota_loca,
         a.imp_flet_impu_tota_loca,
         a.val_tota_gast_admi_sin_impu,
         a.imp_impu_tota_loca,
         a.val_tota_paga_loca,
         a.num_docu_cont_inte,
         pscodigousuario usu_digi
    FROM fac_docum_conta_cabec a,
         ped_solic_cabec b,
         cra_perio c,
         seg_perio_corpo d,
         mae_tipo_docum e,
         mae_clien_direc f,
         mae_clien g,
         ped_tipo_solic_pais h,
         ped_tipo_solic i
   WHERE a.soca_oid_soli_cabe = b.oid_soli_cabe
     AND f.clie_oid_clie=g.oid_clie
     AND a.perd_oid_peri = c.oid_peri
     AND c.peri_oid_peri = d.oid_peri
     AND b.cldi_oid_clie_dire = f.oid_clie_dire
     AND b.tdoc_oid_tipo_docu = e.oid_tipo_docu
     --AND a.tido_oid_tipo_docu = 34
     and b.tspa_oid_tipo_soli_pais=h.oid_tipo_soli_pais
     and h.tsol_oid_tipo_soli=i.oid_tipo_soli
     and i.cod_tipo_soli='CBR'
     AND a.val_tota_paga_loca >= 0
     AND a.fec_fact>=NVL(TO_DATE(psfechadesde,'DD/MM/YYYY'), a.fec_fact)
     AND a.fec_fact<=NVL(TO_DATE(psfechahasta,'DD/MM/YYYY'), a.fec_fact);

    INSERT INTO ccc_tmp_medio_rever_post_venta (
        cod_peri,
        val_sigl,
        val_nume_iden_nnal,
        cod_clie,
        fec_fact,
        val_nom1,
        val_nom2,
        val_ape1,
        val_ape2,
        val_dire_comp,
        num_tele_fijo,
        num_tele_movil,
        val_mail,
        nom_pais,
        val_depa,
        val_muni,
        ind_san_andre,
        val_prec_neto_tota_loca,
        imp_flet_impu_tota_loca,
        val_tota_gast_admi_sin_impu,
        imp_impu_tota_loca,
        val_tota_paga_loca,
        oid_cabe,
        usu_digi
    )
    SELECT
         d.cod_peri,
         e.val_sigl,
         a.val_nume_iden_nnal,
         g.cod_clie,
         a.fec_fact,
         a.val_nom1,
         a.val_nom2,
         a.val_ape1,
         a.val_ape2,
         replace(a.val_dire_comp,',') val_dire_comp,
         (SELECT val_text_comu
            FROM mae_clien_comun
           WHERE clie_oid_clie = b.clie_oid_clie
             AND ticm_oid_tipo_comu = 1
             AND ROWNUM = 1) num_tele_fijo,
         (SELECT val_text_comu
            FROM mae_clien_comun
           WHERE clie_oid_clie = b.clie_oid_clie
             AND ticm_oid_tipo_comu = 6
             AND ROWNUM = 1) num_tele_movil,
         (SELECT val_text_comu
            FROM mae_clien_comun
           WHERE clie_oid_clie = b.clie_oid_clie
             AND ticm_oid_tipo_comu = 3
             AND ROWNUM = 1) val_mail,
         'COLOMBIA' nom_pais,
         (SELECT des_geog
            FROM zon_valor_estru_geopo
           WHERE orde_1 = SUBSTR (f.cod_unid_geog, 1, 6)
             AND orde_2 IS NULL
             AND orde_3 IS NULL
             AND ROWNUM = 1) val_depa,
         (SELECT des_geog
            FROM zon_valor_estru_geopo
           WHERE orde_1 = SUBSTR (f.cod_unid_geog, 1, 6)
             AND orde_2 = SUBSTR (f.cod_unid_geog, 7, 6)
             AND orde_3 IS NULL
             AND ROWNUM = 1) val_muni,
         DECODE (b.val_tasa_impu, 0, 'SI', 'NO') ind_san_andre,
         (SELECT SUM( val_prec_sin_impu_tota_loca -
                                 imp_desc_sin_impu_tota_loca -
                                 DECODE(val_prec_cont_tota_loca, 0, 0, val_prec_sin_impu_tota_loca) )
            FROM fac_docum_conta_linea
           WHERE dcca_oid_cabe=a.oid_cabe) val_prec_neto_tota_loca,
         a.imp_flet_impu_tota_loca,
         a.val_tota_gast_admi_sin_impu,
         a.imp_impu_tota_loca,
         a.val_tota_paga_loca,
         a.num_docu_cont_inte,
         pscodigousuario usu_digi
    FROM fac_docum_conta_cabec a,
         ped_solic_cabec b,
         cra_perio c,
         seg_perio_corpo d,
         mae_tipo_docum e,
         mae_clien_direc f,
         mae_clien g,
         ped_tipo_solic_pais h,
         ped_tipo_solic i
   WHERE a.soca_oid_soli_cabe = b.oid_soli_cabe
     AND a.perd_oid_peri = c.oid_peri
     AND c.peri_oid_peri = d.oid_peri
     AND b.cldi_oid_clie_dire = f.oid_clie_dire
     AND f.clie_oid_clie=g.oid_clie
     AND b.tdoc_oid_tipo_docu = e.oid_tipo_docu
     AND a.tido_oid_tipo_docu = 1
     AND a.val_tota_paga_loca >= 0
     AND b.tspa_oid_tipo_soli_pais = h.oid_tipo_soli_pais
     AND h.tsol_oid_tipo_soli = i.oid_tipo_soli
     and i.cod_tipo_soli = 'C78'
     AND a.fec_fact>=NVL(TO_DATE(psfechadesde,'DD/MM/YYYY'), a.fec_fact)
     AND a.fec_fact<=NVL(TO_DATE(psfechahasta,'DD/MM/YYYY'), a.fec_fact);

    INSERT INTO ccc_tmp_mmagt_notas_credi (
        cod_peri,
        val_sigl,
        val_nume_iden_nnal,
        cod_clie,
        val_anul,
        fec_fact,
        val_nom1,
        val_nom2,
        val_ape1,
        val_ape2,
        val_dire_comp,
        num_tele_fijo,
        num_tele_movil,
        val_mail,
        nom_pais,
        val_depa,
        val_muni,
        ind_san_andre,
        val_prec_neto_tota_loca,
        imp_flet_impu_tota_loca,
        val_tota_gast_admi_sin_impu,
        imp_impu_tota_loca,
        val_tota_paga_loca,
        val_camp_refe,
        num_docu_lega,
        oid_cabe,
        usu_digi
    )
    SELECT
         d.cod_peri,
         e.val_sigl,
         a.val_nume_iden_nnal,
         g.cod_clie,
         decode(i.ind_anul,'1','ANU','DEV') val_anul,
         a.fec_fact,
         a.val_nom1,
         a.val_nom2,
         a.val_ape1,
         a.val_ape2,
         replace(a.val_dire_comp,',') val_dire_comp,
         (SELECT val_text_comu
            FROM mae_clien_comun
           WHERE clie_oid_clie = b.clie_oid_clie
             AND ticm_oid_tipo_comu = 1
             AND ROWNUM = 1) num_tele_fijo,
         (SELECT val_text_comu
            FROM mae_clien_comun
           WHERE clie_oid_clie = b.clie_oid_clie
             AND ticm_oid_tipo_comu = 6
             AND ROWNUM = 1) num_tele_movil,
         (SELECT val_text_comu
            FROM mae_clien_comun
           WHERE clie_oid_clie = b.clie_oid_clie
             AND ticm_oid_tipo_comu = 3
             AND ROWNUM = 1) val_mail,
         'COLOMBIA' nom_pais,
         (SELECT des_geog
            FROM zon_valor_estru_geopo
           WHERE orde_1 = SUBSTR (f.cod_unid_geog, 1, 6)
             AND orde_2 IS NULL
             AND orde_3 IS NULL
             AND ROWNUM = 1) val_depa,
         (SELECT des_geog
            FROM zon_valor_estru_geopo
           WHERE orde_1 = SUBSTR (f.cod_unid_geog, 1, 6)
             AND orde_2 = SUBSTR (f.cod_unid_geog, 7, 6)
             AND orde_3 IS NULL
             AND ROWNUM = 1) val_muni,
         DECODE (b.val_tasa_impu, 0, 'SI', 'NO') ind_san_andre,
         (SELECT SUM( val_prec_sin_impu_tota_loca -
                                 imp_desc_sin_impu_tota_loca -
                                 DECODE(val_prec_cont_tota_loca, 0, 0, val_prec_sin_impu_tota_loca) )
            FROM fac_docum_conta_linea
           WHERE dcca_oid_cabe=a.oid_cabe) val_prec_neto_tota_loca,
         a.imp_flet_impu_tota_loca,
         a.val_tota_gast_admi_sin_impu,
         a.imp_impu_tota_loca,
         a.val_tota_paga_loca,
         csref.cod_peri val_camp_refe,
         (SELECT num_docu_lega
            FROM fac_docum_conta_cabec
           WHERE tido_oid_tipo_docu = 1
             AND soca_oid_soli_cabe = REF.oid_soli_cabe
             AND ROWNUM = 1) num_docu_lega,
         a.oid_cabe,
         pscodigousuario usu_digi
    FROM fac_docum_conta_cabec a,
         ped_solic_cabec b,
         cra_perio c,
         seg_perio_corpo d,
         mae_tipo_docum e,
         mae_clien_direc f,
         ped_solic_cabec REF,
         cra_perio cref,
         seg_perio_corpo csref,
         mae_clien g,
         ped_tipo_solic_pais h,
         ped_tipo_solic i
    WHERE a.soca_oid_soli_cabe = b.oid_soli_cabe
     AND a.perd_oid_peri = c.oid_peri
     AND c.peri_oid_peri = d.oid_peri
     AND b.tspa_oid_tipo_soli_pais=h.oid_tipo_soli_pais
     AND h.tsol_oid_tipo_soli=i.oid_tipo_soli
     AND f.clie_oid_clie=g.oid_clie
     AND b.cldi_oid_clie_dire = f.oid_clie_dire
     AND b.tdoc_oid_tipo_docu = e.oid_tipo_docu
     AND b.soca_oid_docu_refe = REF.oid_soli_cabe
     AND REF.perd_oid_peri = cref.oid_peri
     AND cref.peri_oid_peri = csref.oid_peri
     AND a.tido_oid_tipo_docu = 32
     AND a.val_tota_paga_loca <> 0
     AND a.fec_fact>=NVL(TO_DATE(psfechadesde,'DD/MM/YYYY'), a.fec_fact)
     AND a.fec_fact<=NVL(TO_DATE(psfechahasta,'DD/MM/YYYY'), a.fec_fact);

    INSERT INTO ccc_tmp_mmagt_cargo_abono (
        cod_peri,
        oid_movi_cc,
        cod_clie,
        val_sigl,
        num_docu_iden,
        num_tele_fijo,
        num_tele_movil,
        val_mail,
        nom_pais,
        val_depa,
        val_muni,
        des_subp,
        fec_docu,
        val_nomb,
        val_dire,
        val_tele,
        ind_exce_impu,
        val_impo_neto,
        val_flete,
        val_inte,
        val_impu,
        imp_movi,
        usu_digi
    )
    SELECT
         spc.cod_peri,
         mcc.oid_movi_cc,
         mc.cod_clie,
         mtd.val_sigl,
         mci.num_docu_iden,
         (SELECT val_text_comu
            FROM mae_clien_comun
           WHERE clie_oid_clie = mcd.clie_oid_clie
             AND ticm_oid_tipo_comu = 1
             AND ROWNUM = 1) num_tele_fijo,
         (SELECT val_text_comu
            FROM mae_clien_comun
           WHERE clie_oid_clie = mcd.clie_oid_clie
             AND ticm_oid_tipo_comu = 6
             AND ROWNUM = 1) num_tele_movil,
         (SELECT val_text_comu
            FROM mae_clien_comun
           WHERE clie_oid_clie = mcd.clie_oid_clie
             AND ticm_oid_tipo_comu = 3
             AND ROWNUM = 1) val_mail,
         'COLOMBIA' nom_pais,
         (SELECT des_geog
            FROM zon_valor_estru_geopo
           WHERE orde_1 = SUBSTR (mcd.cod_unid_geog, 1, 6)
             AND orde_2 IS NULL
             AND orde_3 IS NULL
             AND ROWNUM = 1) val_depa,
         (SELECT des_geog
            FROM zon_valor_estru_geopo
           WHERE orde_1 = SUBSTR (mcd.cod_unid_geog, 1, 6)
             AND orde_2 = SUBSTR (mcd.cod_unid_geog, 7, 6)
             AND orde_3 IS NULL
             AND ROWNUM = 1) val_muni,
         su.des_subp,
         mcc.fec_docu,
         mc.val_nom1 || ' ' || mc.val_nom2 || ' ' || mc.val_ape1 || ' ' || mc.val_ape2 val_nomb,
         replace(fin_pkg_gener.fin_fn_obtie_direc_compl_clien(mc.oid_clie),',') val_dire,
         fin_pkg_gener.fin_fn_obtie_numer_telef_clien (mc.oid_clie, 'TF') val_tele,
         CASE
            WHEN zz.oid_zona IN (SELECT vepo_oid_valo_estr_geop FROM fac_tipos_impue_ubige)
               THEN 1
            ELSE 0
         END ind_exce_impu,
         ROUND (mcc.imp_movi / 1.16) val_impo_neto,
         0 val_flete,
         0 val_inte,
         mcc.imp_movi - ROUND (mcc.imp_movi / 1.16) val_impu,
         mcc.imp_movi,
         pscodigousuario usu_digi
    FROM ccc_movim_cuent_corri mcc,
         mae_clien mc,
         mae_clien_ident mci,
         mae_tipo_docum mtd,
         mae_clien_unida_admin mcua,
         mae_clien_direc mcd,
         zon_terri_admin zta,
         zon_secci zs,
         zon_zona zz,
         zon_regio zr,
         ccc_subpr su,
         cra_perio cp,
         seg_perio_corpo spc
    WHERE mcc.clie_oid_clie = mc.oid_clie
     AND mcc.perd_oid_peri=cp.oid_peri
     AND cp.peri_oid_peri=spc.oid_peri
     AND mcc.subp_oid_subp_crea = su.oid_subp
     AND mc.oid_clie = mci.clie_oid_clie
     AND mc.oid_clie = mcd.clie_oid_clie
     AND mci.tdoc_oid_tipo_docu=mtd.oid_tipo_docu
     AND mc.oid_clie = mcua.clie_oid_clie
     AND mcua.ztad_oid_terr_admi = zta.oid_terr_admi
     AND zta.zscc_oid_secc = zs.oid_secc
     AND zs.zzon_oid_zona = zz.oid_zona
     AND zz.zorg_oid_regi = zr.oid_regi
     AND mcc.subp_oid_subp_crea IN (
            SELECT su.oid_subp
              FROM ccc_param_conta_sapfi x, ccc_proce cp, ccc_subpr su
             WHERE x.ind_acti = 2
               AND x.cod_proc = cp.cod_proc
               AND x.cod_subp = su.cod_subp
               AND cp.oid_proc = su.ccpr_oid_proc)
     AND mci.val_iden_docu_prin = 1
     AND mcua.ind_acti = 1
     AND mcd.ind_elim=0
     AND mcd.ind_dire_ppal=1
     AND mcc.fec_docu>=NVL(TO_DATE(psfechadesde,'DD/MM/YYYY'), mcc.fec_docu)
     AND mcc.fec_docu<=NVL(TO_DATE(psfechahasta,'DD/MM/YYYY'), mcc.fec_docu);

  EXCEPTION
      WHEN OTHERS THEN
          ln_sqlcode := sqlcode;
          ls_sqlerrm := substr(sqlerrm,1,250);
          RAISE_APPLICATION_ERROR(-20123, 'ERROR CCC_PR_CARGA_REPOR_MEDIO_MAGNE: '||ls_sqlerrm);

  END CCC_PR_CARGA_REPOR_MEDIO_MAGNE;


/*************************************************************************************
Descripcion       : Procedimiento que carga el Reporte Detalle Cuenta Corriente para formato CSV
Fecha Creacion    : 26/02/2014
Autor             : Carlos Bazalar
************************************************************************************/
PROCEDURE CCC_PR_GENER_REPOR_DETAL_CTACT(
    pscodigopais          VARCHAR2,
    psFechaHasta          VARCHAR2,
    psCodigoSociedad      VARCHAR2,
    pstipoNombreReporte   VARCHAR2,
    psnombrearchivo       VARCHAR2,
    pstitulo              VARCHAR2,
    psdirectorio          OUT VARCHAR2
)
IS
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         number := 5000;
    v_handle        utl_file.file_type;
    lslinea         varchar2(4000);

    CURSOR C_REPOR_01 IS
       WITH temp1 AS
       (SELECT clie_oid_clie, sum(imp_movi) imp_sald
        FROM
         ((SELECT mcc.clie_oid_clie , SUM(mcc.imp_movi) imp_movi
           FROM ccc_movim_cuent_corri mcc
           WHERE mcc.fec_docu <= TO_DATE(psFechaHasta,'DD/MM/YYYY')
           GROUP BY mcc.clie_oid_clie)
         UNION ALL
          (SELECT mb.clie_oid_clie, SUM(mb.imp_pago*-1) imp_movi
           FROM ccc_movim_banca mb
           WHERE mb.cod_iden_proc = 'P'
             AND mb.fec_proc <= TO_DATE(psFechaHasta,'DD/MM/YYYY')
           GROUP BY mb.clie_oid_clie))
        GROUP BY clie_oid_clie),
      temp2 AS
       ( SELECT
          mc.cod_clie,
          mc.oid_clie,
          TRIM(mc.val_ape1) || ' ' || TRIM(mc.val_ape2) || ' '  || TRIM(mc.val_nom1) || ' ' || TRIM(mc.val_nom2) nom_clie,
          t1.imp_sald
         FROM mae_clien mc,
              temp1 t1
         WHERE mc.oid_clie = t1.clie_oid_clie
          AND t1.imp_sald <> 0),
      temp3 AS
       ( SELECT mcua.clie_oid_clie, zr.des_regi, zz.cod_zona
         FROM mae_clien_unida_admin mcua,
              zon_terri_admin zta,
              zon_secci zs,
              zon_zona zz,
              zon_regio zr,
              temp2 t2
         WHERE t2.oid_clie = mcua.clie_oid_clie
          AND mcua.ztad_oid_terr_admi = zta.oid_terr_admi
          AND mcua.ind_acti = 1
          AND zta.zscc_oid_secc = zs.oid_secc
          AND zs.zzon_oid_zona = zz.oid_zona
          AND zz.zorg_oid_regi = zr.oid_regi),
      temp4 AS
        (SELECT
          t3.des_regi,
          t3.cod_zona,
          t2.cod_clie,
          t2.oid_clie,
          t2.nom_clie,
          t2.imp_sald
         FROM temp2 t2,
              temp3 t3
         WHERE t2.oid_clie = t3.clie_oid_clie(+)),
      temp5 AS(
         SELECT mci.clie_oid_clie, mci.num_docu_iden
         FROM mae_clien_ident mci,
              temp4 t4
         WHERE t4.oid_clie = mci.clie_oid_clie
          AND mci.val_iden_docu_prin = 1)
       SELECT
          t4.des_regi region,
          t4.cod_zona zona,
          t4.cod_clie codigo,
          t5.num_docu_iden cedula,
          t4.nom_clie nombre,
          t4.imp_sald saldo
       FROM temp4 t4,
            temp5 t5
       WHERE t4.oid_clie = t5.clie_oid_clie(+)
       ORDER BY 1,2,3 ASC;

    TYPE detalleUnidadesReg01 IS RECORD(
      des_regi                  zon_regio.des_regi%TYPE,
      cod_zona                  zon_zona.cod_zona%TYPE,
      cod_clie                  mae_clien.cod_clie%TYPE,
      num_docu_iden             mae_clien_ident.num_docu_iden%TYPE,
      nom_clie                  VARCHAR2(200),
      imp_sald                  NUMBER(12,2)
    );
    TYPE detalleUnidadesRegTab01 IS TABLE OF detalleUnidadesReg01;
    detalleUnidadesRegRecord01 detalleUnidadesRegTab01;

    CURSOR C_REPOR_02 IS
       WITH temp1 AS
       (SELECT
          mc.oid_clie,
          mc.cod_clie,
          TRIM(mc.val_nom1) || ' ' || TRIM(mc.val_nom2) || ' ' || TRIM(mc.val_ape1) || ' ' || TRIM(mc.val_ape2) nom_clie,
          CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_CONTA(mc.oid_clie, TO_DATE(psFechaHasta,'DD/MM/YYYY')) saldo
       FROM mae_clien mc)
       SELECT
        zr.des_regi region,
        zz.cod_zona zona,
        t1.cod_clie codigo,
        t1.nom_clie nombre,
        t1.saldo saldo
       FROM temp1 t1,
      mae_clien_unida_admin mcua,
      zon_terri_admin zta,
      zon_secci zs,
      zon_zona zz,
      zon_regio zr
       WHERE t1.oid_clie=mcua.clie_oid_clie
       AND mcua.ztad_oid_terr_admi=zta.oid_terr_admi
       AND mcua.ind_acti=1
       AND zta.zscc_oid_secc=zs.oid_secc
       AND zs.zzon_oid_zona=zz.oid_zona
       AND zz.zorg_oid_regi=zr.oid_regi
       AND t1.saldo<>0 ;

    TYPE detalleUnidadesReg02 IS RECORD(
      des_regi                  zon_regio.des_regi%TYPE,
      cod_zona                  zon_zona.cod_zona%TYPE,
      cod_clie                  mae_clien.cod_clie%TYPE,
      nom_clie                  VARCHAR2(200),
      imp_sald                  NUMBER(12,2)
    );
    TYPE detalleUnidadesRegTab02 IS TABLE OF detalleUnidadesReg02;
    detalleUnidadesRegRecord02 detalleUnidadesRegTab02;

    CURSOR C_REPOR_03 IS
     WITH temp1 AS
       (SELECT
          mc.oid_clie,
          mc.cod_clie,
          TRIM(mc.val_nom1) || ' ' || TRIM(mc.val_nom2) || ' ' || TRIM(mc.val_ape1) || ' ' || TRIM(mc.val_ape2) nom_clie,
          CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_CONTA(mc.oid_clie, TO_DATE(psFechaHasta,'DD/MM/YYYY')) saldo
       FROM mae_clien mc)
       SELECT
          zr.des_regi region,
          zz.cod_zona zona,
          mc.cod_clie cod_clie,
          mc.val_nom1 || ' ' ||
          mc.val_nom2 || ' ' ||
          mc.val_ape1 || ' ' ||
          mc.val_ape2 nom_clie,
          TRIM(stv.des_abrv_tipo_via) || ' ' ||
          TRIM(mcd.val_nomb_via) || ' ' ||
          TRIM(mcd.num_ppal) || ' ' ||
          TRIM(mcd.val_inte)  || ' ' ||
          TRIM(mcd.val_manz) || ' ' ||
          TRIM(mcd.val_lote) || ' ' ||
          TRIM(mcd.val_km) || ' ' ||
          TRIM(mcd.val_obse) val_dire,
          tf.val_text_comu num_tele_fijo,
          tt.val_text_comu num_tele_trab,
          tm.val_text_comu num_tele_movi,
          CCC_PKG_GENER.CCC_FN_OBTIE_CAMPA_ULTIM_CARGO(t1.oid_clie,TO_DATE(psFechaHasta,'DD/MM/YYYY')) cam_ulti_pedi,
          CCC_PKG_GENER.CCC_FN_OBTIE_FECHA_ULTIM_CARGO(t1.oid_clie,TO_DATE(psFechaHasta,'DD/MM/YYYY')) fec_ulti_pedi,
          t1.saldo saldo
       FROM
          temp1 t1,
          mae_clien mc,
          mae_clien_unida_admin mcua,
          zon_terri_admin zta,
          zon_secci  zs,
          zon_zona zz,
          zon_regio zr,
          mae_clien_direc mcd,
          seg_tipo_via stv,
         (SELECT mco.clie_oid_clie ,mco.val_text_comu
          FROM mae_clien_comun mco,
           mae_tipo_comun mtc
          WHERE mco.ticm_oid_tipo_comu=mtc.oid_tipo_comu
          AND mtc.cod_tipo_comu='TF') tf,
          (SELECT mco.clie_oid_clie ,mco.val_text_comu
           FROM mae_clien_comun mco,
            mae_tipo_comun mtc
           WHERE mco.ticm_oid_tipo_comu=mtc.oid_tipo_comu
           AND mtc.cod_tipo_comu='TT') tt,
          (SELECT mco.clie_oid_clie ,mco.val_text_comu
           FROM mae_clien_comun mco,
            mae_tipo_comun mtc
           WHERE mco.ticm_oid_tipo_comu=mtc.oid_tipo_comu
           AND mtc.cod_tipo_comu='TM') tm
        WHERE t1.oid_clie = mc.oid_clie
          AND mc.oid_clie=mcua.clie_oid_clie
          AND mcua.ind_acti=1
          AND mcua.ztad_oid_terr_admi=zta.oid_terr_admi
          AND zta.zscc_oid_secc=zs.oid_secc
          AND zs.zzon_oid_zona= zz.oid_zona
          AND zz.zorg_oid_regi=zr.oid_regi
          AND mc.oid_clie=mcd.clie_oid_clie
          AND stv.oid_tipo_via=mcd.tivi_oid_tipo_via
          AND mcd.ind_dire_ppal=1
          AND mcd.ind_elim=0
          AND mc.oid_clie=tt.clie_oid_clie(+)
          AND mc.oid_clie=tf.clie_oid_clie(+)
          AND mc.oid_clie=tm.clie_oid_clie(+)
          AND saldo <> 0;

    TYPE detalleUnidadesReg03 IS RECORD(
      des_regi                  zon_regio.des_regi%TYPE,
      cod_zona                  zon_zona.cod_zona%TYPE,
      cod_clie                  mae_clien.cod_clie%TYPE,
      nom_clie                  VARCHAR2(200),
      val_dire                  VARCHAR2(1000),
      num_tele_fijo             VARCHAR2(200),
      num_tele_trab             VARCHAR2(200),
      num_tele_movi             VARCHAR2(200),
      cam_ulti_pedi             VARCHAR2(6),
      fec_ulti_pedi             VARCHAR2(10),
      imp_sald                  NUMBER(12,2)
    );
    TYPE detalleUnidadesRegTab03 IS TABLE OF detalleUnidadesReg03;
    detalleUnidadesRegRecord03 detalleUnidadesRegTab03;

    CURSOR C_REPOR_04 IS
     WITH temp1 AS
       (SELECT
          mc.oid_clie,
          mc.cod_clie,
          TRIM(mc.val_nom1) || ' ' || TRIM(mc.val_nom2) || ' ' || TRIM(mc.val_ape1) || ' ' || TRIM(mc.val_ape2) nom_clie,
          CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_COMER(mc.oid_clie, TO_DATE(psFechaHasta,'DD/MM/YYYY')) saldo
       FROM mae_clien mc)
       SELECT
         zr.des_regi region,
         zz.cod_zona zona,
         t1.cod_clie codigo,
         t1.nom_clie nombre,
         CCC_PKG_GENER.CCC_FN_OBTIE_CAMPA_ULTIM_CARGO(t1.oid_clie,TO_DATE(psFechaHasta,'DD/MM/YYYY')) cam_ulti_pedi,
         CCC_PKG_GENER.CCC_FN_OBTIE_FECHA_ULTIM_CARGO(t1.oid_clie,TO_DATE(psFechaHasta,'DD/MM/YYYY')) fec_ulti_pedi,
         t1.saldo saldo
       FROM temp1 t1,
            mae_clien_unida_admin mcua,
            zon_terri_admin zta,
            zon_secci zs,
            zon_zona zz,
            zon_regio zr
       WHERE t1.oid_clie=mcua.clie_oid_clie
       AND mcua.ztad_oid_terr_admi=zta.oid_terr_admi
       AND mcua.ind_acti=1
       AND zta.zscc_oid_secc=zs.oid_secc
       AND zs.zzon_oid_zona=zz.oid_zona
       AND zz.zorg_oid_regi=zr.oid_regi
       AND t1.saldo<>0;

    TYPE detalleUnidadesReg04 IS RECORD(
      des_regi                  zon_regio.des_regi%TYPE,
      cod_zona                  zon_zona.cod_zona%TYPE,
      cod_clie                  mae_clien.cod_clie%TYPE,
      nom_clie                  VARCHAR2(200),
      cam_ulti_pedi             VARCHAR2(6),
      fec_ulti_pedi             VARCHAR2(10),
      imp_sald                  NUMBER(12,2)
    );
    TYPE detalleUnidadesRegTab04 IS TABLE OF detalleUnidadesReg04;
    detalleUnidadesRegRecord04 detalleUnidadesRegTab04;

    CURSOR C_REPOR_05 IS
     WITH temp1 AS
       (SELECT
          mc.oid_clie,
          mc.cod_clie,
          TRIM(mc.val_nom1) || ' ' || TRIM(mc.val_nom2) || ' ' || TRIM(mc.val_ape1) || ' ' || TRIM(mc.val_ape2) nom_clie,
          CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_CONTA(mc.oid_clie, TO_DATE(psFechaHasta,'DD/MM/YYYY')) saldo
       FROM mae_clien mc)
       SELECT
        zr.des_regi region,
        zz.cod_zona zona,
        t1.cod_clie codigo,
        t1.nom_clie nombre,
        CCC_PKG_GENER.CCC_FN_OBTIE_CAMPA_ULTIM_CARGO(t1.oid_clie,TO_DATE(psFechaHasta,'DD/MM/YYYY')) cam_ulti_pedi,
        CCC_PKG_GENER.CCC_FN_OBTIE_FECHA_ULTIM_CARGO(t1.oid_clie,TO_DATE(psFechaHasta,'DD/MM/YYYY')) fec_ulti_pedi,
        t1.saldo saldo
       FROM temp1 t1,
            mae_clien_unida_admin mcua,
            zon_terri_admin zta,
            zon_secci zs,
            zon_zona zz,
            zon_regio zr
       WHERE t1.oid_clie=mcua.clie_oid_clie
       AND mcua.ztad_oid_terr_admi=zta.oid_terr_admi
       AND mcua.ind_acti=1
       AND zta.zscc_oid_secc=zs.oid_secc
       AND zs.zzon_oid_zona=zz.oid_zona
       AND zz.zorg_oid_regi=zr.oid_regi
       AND t1.saldo<>0;

    TYPE detalleUnidadesReg05 IS RECORD(
      des_regi                  zon_regio.des_regi%TYPE,
      cod_zona                  zon_zona.cod_zona%TYPE,
      cod_clie                  mae_clien.cod_clie%TYPE,
      nom_clie                  VARCHAR2(200),
      cam_ulti_pedi             VARCHAR2(6),
      fec_ulti_pedi             VARCHAR2(10),
      imp_sald                  NUMBER(12,2)
    );
    TYPE detalleUnidadesRegTab05 IS TABLE OF detalleUnidadesReg05;
    detalleUnidadesRegRecord05 detalleUnidadesRegTab05;

    CURSOR C_REPOR_06 IS
      WITH temp1 AS
       (SELECT
          mc.oid_clie,
          mc.cod_clie,
          TRIM(mc.val_nom1) || ' ' || TRIM(mc.val_nom2) || ' ' || TRIM(mc.val_ape1) || ' ' || TRIM(mc.val_ape2) nom_clie,
          CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_CONTA(mc.oid_clie, TO_DATE(psFechaHasta,'DD/MM/YYYY')) saldo
       FROM mae_clien mc)
       SELECT
        zr.des_regi region,
        zz.cod_zona zona,
        t1.cod_clie codigo,
        t1.nom_clie nombre,
        CCC_PKG_GENER.CCC_FN_OBTIE_CAMPA_ULTIM_CARGO(t1.oid_clie,TO_DATE(psFechaHasta,'DD/MM/YYYY')) cam_ulti_pedi,
        CCC_PKG_GENER.CCC_FN_OBTIE_FECHA_ULTIM_CARGO(t1.oid_clie,TO_DATE(psFechaHasta,'DD/MM/YYYY')) fec_ulti_pedi,
        (SELECT MAX(mb.fec_pago)
         FROM ccc_movim_banca mb
         WHERE mb.clie_oid_clie = t1.oid_clie
         AND mb.cod_iden_proc = 'P'
         AND mb.fec_pago <= TO_DATE(psFechaHasta,'DD/MM/YYYY')) fec_ulti_pago,
        t1.saldo saldo
       FROM temp1 t1,
      mae_clien_unida_admin mcua,
      zon_terri_admin zta,
      zon_secci zs,
      zon_zona zz,
      zon_regio zr
       WHERE t1.oid_clie=mcua.clie_oid_clie
       AND mcua.ztad_oid_terr_admi=zta.oid_terr_admi
       AND mcua.ind_acti=1
       AND zta.zscc_oid_secc=zs.oid_secc
       AND zs.zzon_oid_zona=zz.oid_zona
       AND zz.zorg_oid_regi=zr.oid_regi
       AND t1.saldo <> 0;

    TYPE detalleUnidadesReg06 IS RECORD(
      des_regi                  zon_regio.des_regi%TYPE,
      cod_zona                  zon_zona.cod_zona%TYPE,
      cod_clie                  mae_clien.cod_clie%TYPE,
      nom_clie                  VARCHAR2(200),
      cam_ulti_pedi             VARCHAR2(6),
      fec_ulti_pedi             VARCHAR2(10),
      fec_ulti_pago             DATE,
      imp_sald                  NUMBER(12,2)
    );
    TYPE detalleUnidadesRegTab06 IS TABLE OF detalleUnidadesReg06;
    detalleUnidadesRegRecord06 detalleUnidadesRegTab06;

    CURSOR C_REPOR_07 IS
      SELECT c.cod_regi           COD_REGION,
             c.cod_zona           COD_ZONA,
             c.cod_secc           COD_SECCION,
             c.cod_clie           COD_CODIGO,
             c.nom_clie           NOM_CLIE,
             c.sald_cont          SALDO,
             c.val_seri_docu_lega SERIE_DOCU,
             c.val_nume_docu_lega NUM_DOCU,
             to_char(c.fec_fact,'DD/MM/YYYY') FECHA_FACT,
             c.imp_fact           IMPO_FACT
        FROM ccc_repor_detal_cuent_conta c
       ORDER BY c.sald_cont DESC;
    TYPE detalleUnidadesReg07 IS RECORD(
      cod_regi             ccc_repor_detal_cuent_conta.cod_regi%TYPE,
      cod_zona             ccc_repor_detal_cuent_conta.cod_zona%TYPE,
      cod_secc             ccc_repor_detal_cuent_conta.cod_secc%TYPE,
      cod_clie             ccc_repor_detal_cuent_conta.cod_clie%TYPE,
      nom_clie             ccc_repor_detal_cuent_conta.nom_clie%TYPE,
      sald_cont            ccc_repor_detal_cuent_conta.sald_cont%TYPE,
      val_seri_docu_lega   ccc_repor_detal_cuent_conta.val_seri_docu_lega%TYPE,
      val_nume_docu_lega   ccc_repor_detal_cuent_conta.val_nume_docu_lega%TYPE,
      fec_fact             VARCHAR2(10),
      imp_fact             ccc_repor_detal_cuent_conta.imp_fact%TYPE
    );
    TYPE detalleUnidadesRegTab07 IS TABLE OF detalleUnidadesReg07;
    detalleUnidadesRegRecord07 detalleUnidadesRegTab07;

    lbAbrirUtlFile  BOOLEAN;
BEGIN

    lbAbrirUtlFile := true;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
    IF pstipoNombreReporte = 'A' THEN
        OPEN C_REPOR_01;
          LOOP
           FETCH C_REPOR_01 BULK COLLECT INTO detalleUnidadesRegRecord01 LIMIT W_FILAS;
           IF lbAbrirUtlFile THEN
              GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
              psdirectorio := lsdirtempo;
              lbAbrirUtlFile := FALSE;
           END IF ;
           IF detalleUnidadesRegRecord01.COUNT > 0 THEN
              FOR x IN detalleUnidadesRegRecord01.FIRST .. detalleUnidadesRegRecord01.LAST LOOP
                    lslinea :=
                                 '"'|| detalleUnidadesRegRecord01(x).des_regi || '"' || ',' ||
                                 '=T("'|| detalleUnidadesRegRecord01(x).cod_zona || '")' || ',' ||
                                 '=T("'|| detalleUnidadesRegRecord01(x).cod_clie || '")' || ',' ||
                                 '=T("'|| detalleUnidadesRegRecord01(x).num_docu_iden || '")' || ',' ||
                                 '"'|| detalleUnidadesRegRecord01(x).nom_clie || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord01(x).imp_sald || '"' ;
                     UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

              END LOOP;
            END IF;
            EXIT WHEN C_REPOR_01%NOTFOUND;
        END LOOP;
        CLOSE C_REPOR_01;
    ELSIF pstipoNombreReporte = 'P' THEN
        OPEN C_REPOR_02;
          LOOP
           FETCH C_REPOR_02 BULK COLLECT INTO detalleUnidadesRegRecord02 LIMIT W_FILAS;
           IF lbAbrirUtlFile THEN
              GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
              psdirectorio := lsdirtempo;
              lbAbrirUtlFile := FALSE;
           END IF ;
           IF detalleUnidadesRegRecord02.COUNT > 0 THEN
              FOR x IN detalleUnidadesRegRecord02.FIRST .. detalleUnidadesRegRecord02.LAST LOOP
                     lslinea :=
                                 '"'|| detalleUnidadesRegRecord02(x).des_regi || '"' || ',' ||
                                 '=T("'|| detalleUnidadesRegRecord02(x).cod_zona || '")' || ',' ||
                                 '=T("'|| detalleUnidadesRegRecord02(x).cod_clie || '")' || ',' ||
                                 '"'|| detalleUnidadesRegRecord02(x).nom_clie || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord02(x).imp_sald || '"' ;
                     UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

              END LOOP;
            END IF;
            EXIT WHEN C_REPOR_02%NOTFOUND;
        END LOOP;
        CLOSE C_REPOR_02;

    ELSIF pstipoNombreReporte = 'B' THEN
       OPEN C_REPOR_03;
          LOOP
           FETCH C_REPOR_03 BULK COLLECT INTO detalleUnidadesRegRecord03 LIMIT W_FILAS;
           IF lbAbrirUtlFile THEN
              GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
              psdirectorio := lsdirtempo;
              lbAbrirUtlFile := FALSE;
           END IF ;
           IF detalleUnidadesRegRecord03.COUNT > 0 THEN
              FOR x IN detalleUnidadesRegRecord03.FIRST .. detalleUnidadesRegRecord03.LAST LOOP
                    lslinea :=   '"'|| detalleUnidadesRegRecord03(x).des_regi || '"' || ',' ||
                                 '=T("'|| detalleUnidadesRegRecord03(x).cod_zona || '")' || ',' ||
                                 '=T("'|| detalleUnidadesRegRecord03(x).cod_clie || '")' || ',' ||
                                 '"'|| detalleUnidadesRegRecord03(x).nom_clie || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord03(x).val_dire || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord03(x).num_tele_fijo || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord03(x).num_tele_trab || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord03(x).num_tele_movi || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord03(x).cam_ulti_pedi || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord03(x).fec_ulti_pedi || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord03(x).imp_sald || '"' ;
                     UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

              END LOOP;
            END IF;
            EXIT WHEN C_REPOR_03%NOTFOUND;
        END LOOP;
        CLOSE C_REPOR_03;
    ELSIF pstipoNombreReporte = 'E' THEN
        OPEN C_REPOR_04;
          LOOP
           FETCH C_REPOR_04 BULK COLLECT INTO detalleUnidadesRegRecord04 LIMIT W_FILAS;
           IF lbAbrirUtlFile THEN
              GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
              psdirectorio := lsdirtempo;
              lbAbrirUtlFile := FALSE;
           END IF ;
           IF detalleUnidadesRegRecord04.COUNT > 0 THEN
              FOR x IN detalleUnidadesRegRecord04.FIRST .. detalleUnidadesRegRecord04.LAST LOOP
                 lslinea :=      '"'|| detalleUnidadesRegRecord04(x).des_regi || '"' || ',' ||
                                 '=T("'|| detalleUnidadesRegRecord04(x).cod_zona || '")' || ',' ||
                                 '=T("'|| detalleUnidadesRegRecord04(x).cod_clie || '")' || ',' ||
                                 '"'|| detalleUnidadesRegRecord04(x).nom_clie || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord04(x).cam_ulti_pedi || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord04(x).fec_ulti_pedi || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord04(x).imp_sald || '"' ;
                      UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

              END LOOP;
            END IF;
            EXIT WHEN C_REPOR_04%NOTFOUND;
        END LOOP;
        CLOSE C_REPOR_04;
    ELSIF pstipoNombreReporte = 'PB' THEN
        OPEN C_REPOR_05;
          LOOP
           FETCH C_REPOR_05 BULK COLLECT INTO detalleUnidadesRegRecord05 LIMIT W_FILAS;
           IF lbAbrirUtlFile THEN
              GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
              psdirectorio := lsdirtempo;
              lbAbrirUtlFile := FALSE;
           END IF ;
           IF detalleUnidadesRegRecord05.COUNT > 0 THEN
              FOR x IN detalleUnidadesRegRecord05.FIRST .. detalleUnidadesRegRecord05.LAST LOOP
                    lslinea :=   '"'|| detalleUnidadesRegRecord05(x).des_regi || '"' || ',' ||
                                 '=T("'|| detalleUnidadesRegRecord05(x).cod_zona || '")' || ',' ||
                                 '=T("'|| detalleUnidadesRegRecord05(x).cod_clie || '")' || ',' ||
                                 '"'|| detalleUnidadesRegRecord05(x).nom_clie || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord05(x).cam_ulti_pedi || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord05(x).fec_ulti_pedi || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord05(x).imp_sald || '"' ;
                     UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

              END LOOP;
            END IF;
            EXIT WHEN C_REPOR_05%NOTFOUND;
        END LOOP;
        CLOSE C_REPOR_05;
    ELSIF pstipoNombreReporte = 'UP' THEN
        OPEN C_REPOR_06;
          LOOP
           FETCH C_REPOR_06 BULK COLLECT INTO detalleUnidadesRegRecord06 LIMIT W_FILAS;
           IF lbAbrirUtlFile THEN
              GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
              psdirectorio := lsdirtempo;
              lbAbrirUtlFile := FALSE;
           END IF ;
           IF detalleUnidadesRegRecord06.COUNT > 0 THEN
              FOR x IN detalleUnidadesRegRecord06.FIRST .. detalleUnidadesRegRecord06.LAST LOOP
                    lslinea :=   '"'|| detalleUnidadesRegRecord06(x).des_regi || '"' || ',' ||
                                 '=T("'|| detalleUnidadesRegRecord06(x).cod_zona || '")' || ',' ||
                                 '=T("'|| detalleUnidadesRegRecord06(x).cod_clie || '")' || ',' ||
                                 '"'|| detalleUnidadesRegRecord06(x).nom_clie || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord06(x).cam_ulti_pedi || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord06(x).fec_ulti_pedi || '"' || ',' ||
                                 '"'|| to_char(detalleUnidadesRegRecord06(x).fec_ulti_pago,'DD/MM/YYYY') || '"' || ',' ||
                                 '"'|| psFechaHasta || '"' || ',' ||
                                 '"'|| detalleUnidadesRegRecord06(x).imp_sald || '"' ;
                     UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
              END LOOP;
            END IF;
            EXIT WHEN C_REPOR_06%NOTFOUND;
        END LOOP;
        CLOSE C_REPOR_06;
    ELSIF pstipoNombreReporte = 'L' THEN
        OPEN C_REPOR_07;
          LOOP
           FETCH C_REPOR_07 BULK COLLECT INTO detalleUnidadesRegRecord07 LIMIT W_FILAS;
           IF lbAbrirUtlFile THEN
              GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
              psdirectorio := lsdirtempo;
              lbAbrirUtlFile := FALSE;
           END IF ;
           IF detalleUnidadesRegRecord07.COUNT > 0 THEN
              FOR x IN detalleUnidadesRegRecord07.FIRST .. detalleUnidadesRegRecord07.LAST LOOP

                 lslinea :=   '"'|| detalleUnidadesRegRecord07(x).cod_regi || '"' || ',' ||
                                   '=T("'|| detalleUnidadesRegRecord07(x).cod_zona || '")' || ',' ||
                                   '=T("'|| detalleUnidadesRegRecord07(x).cod_secc || '")' || ',' ||
                                   '=T("'|| detalleUnidadesRegRecord07(x).cod_clie || '")' || ',' ||
                                   '"'|| detalleUnidadesRegRecord07(x).nom_clie || '"' || ',' ||
                                   '"'|| detalleUnidadesRegRecord07(x).sald_cont || '"' || ',' ||
                                   '"'|| detalleUnidadesRegRecord07(x).val_seri_docu_lega || '"' || ',' ||
                                   '"'|| detalleUnidadesRegRecord07(x).val_nume_docu_lega || '"' || ',' ||
                                   '"'|| detalleUnidadesRegRecord07(x).fec_fact || '"' || ',' ||
                                   '"'|| detalleUnidadesRegRecord07(x).imp_fact || '"' ;
                       UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

              END LOOP;
            END IF;
            EXIT WHEN C_REPOR_07%NOTFOUND;
        END LOOP;
        CLOSE C_REPOR_07;

    END IF;
    IF NOT lbAbrirUtlFile THEN
        utl_file.fclose(V_HANDLE);
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm,1,250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR CCC_PR_GENER_REPOR_DETAL_CTACT: '||ls_sqlerrm);

END CCC_PR_GENER_REPOR_DETAL_CTACT ;

/*************************************************************************************
Descripcion       : Procedimiento que carga el Reporte Detallado Pagos x Regularizar
                    para formato CSV
Fecha Creacion    : 04/03/2014
Autor             : Carlos Bazalar
************************************************************************************/
PROCEDURE CCC_PR_REPOR_DETAL_PAGO_REGUL(
    pscodigopais          VARCHAR2,
    psCodigoBanco         VARCHAR2,
    psfechaPagoDesde      VARCHAR2,
    psfechaPagoHasta      VARCHAR2,
    psfechaProcDesde      VARCHAR2,
    psfechaProcHasta      VARCHAR2,
    psnombrearchivo       VARCHAR2,
    pstitulo              VARCHAR2,
    psdirectorio          OUT VARCHAR2
)
IS
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         number := 5000;
    v_handle        utl_file.file_type;
    lslinea         varchar2(4000);

    CURSOR C_REPOR_01 IS
    SELECT
         ccb.des_cc des_banc,
         eb.val_desc tip_erro,
         mb.cod_cons cod_clie,
         mb.num_docu_iden num_docu_iden,
         mb.fec_pago,
         mb.fec_proc,
         mb.imp_pago
      FROM
         ccc_movim_banca mb,
         ccc_cuent_corri_banca ccb,
         ccc_proce cp,
         ccc_subpr su,
         ccc_tipos_error_banca eb
      WHERE mb.ccba_oid_cc_banc=ccb.oid_cuen_corr_banc
      AND mb.subp_oid_marc_crea = su.oid_subp
      AND cp.oid_proc = su.ccpr_oid_proc
      AND mb.cod_erro = eb.cod_erro
      AND mb.cod_iden_proc = 'I'
      AND mb.ind_pago_regu = 0
      and mb.ind_elim = 0
      AND mb.fec_pago >= DECODE(TO_DATE(psfechaPagoDesde,'DD/MM/YYYY'),null,mb.fec_pago,'',mb.fec_pago,TO_DATE(psfechaPagoDesde,'DD/MM/YYYY'))
      AND mb.fec_pago <= DECODE(TO_DATE(psfechaPagoHasta,'DD/MM/YYYY'),null,mb.fec_pago,'',mb.fec_pago,TO_DATE(psfechaPagoHasta,'DD/MM/YYYY'))
      AND mb.fec_proc >= DECODE(TO_DATE(psfechaProcDesde,'DD/MM/YYYY'),null,mb.fec_proc,'',mb.fec_proc,TO_DATE(psfechaProcDesde,'DD/MM/YYYY'))
      AND mb.fec_proc <= DECODE(TO_DATE(psfechaProcHasta,'DD/MM/YYYY'),null,mb.fec_proc,'',mb.fec_proc,TO_DATE(psfechaProcHasta,'DD/MM/YYYY'))
      AND ccb.cod_cc = DECODE(psCodigoBanco,null,ccb.cod_cc,'T',ccb.cod_cc,'',ccb.cod_cc,psCodigoBanco)
      ORDER BY 1,4,5,6 ASC  ;

    TYPE detalleUnidadesReg01 IS RECORD(
      des_banc                  zon_regio.des_regi%TYPE,
      tip_erro                  zon_zona.cod_zona%TYPE,
      cod_clie                  mae_clien.cod_clie%TYPE,
      num_docu_iden             mae_clien_ident.num_docu_iden%TYPE,
      fec_pago                  DATE,
      fec_proc                  DATE,
      imp_pago                  ccc_movim_banca.Imp_Pago%TYPE
    );
    TYPE detalleUnidadesRegTab01 IS TABLE OF detalleUnidadesReg01;
    detalleUnidadesRegRecord01 detalleUnidadesRegTab01;
    lbAbrirUtlFile  BOOLEAN;
BEGIN

    lbAbrirUtlFile := true;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
    OPEN C_REPOR_01;
      LOOP
       FETCH C_REPOR_01 BULK COLLECT INTO detalleUnidadesRegRecord01 LIMIT W_FILAS;
       IF lbAbrirUtlFile THEN
          GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
          psdirectorio := lsdirtempo;
          lbAbrirUtlFile := FALSE;
       END IF ;
       IF detalleUnidadesRegRecord01.COUNT > 0 THEN
          FOR x IN detalleUnidadesRegRecord01.FIRST .. detalleUnidadesRegRecord01.LAST LOOP
                lslinea :=
                             '"'|| detalleUnidadesRegRecord01(x).des_banc || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord01(x).tip_erro || '"' || ',' ||
                             '=T("'|| detalleUnidadesRegRecord01(x).cod_clie || '")' || ',' ||
                             '=T("'|| detalleUnidadesRegRecord01(x).num_docu_iden || '")' || ',' ||
                             '"'|| TO_CHAR(detalleUnidadesRegRecord01(x).fec_pago,'DD/MM/YYYY') || '"' || ',' ||
                             '"'|| TO_CHAR(detalleUnidadesRegRecord01(x).fec_proc,'DD/MM/YYYY') || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord01(x).imp_pago || '"' ;
                 UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

          END LOOP;
        END IF;
        EXIT WHEN C_REPOR_01%NOTFOUND;
    END LOOP;
    CLOSE C_REPOR_01;

    IF NOT lbAbrirUtlFile THEN
        utl_file.fclose(V_HANDLE);
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm,1,250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR CCC_PR_REPOR_DETAL_PAGO_REGUL: '||ls_sqlerrm);

END CCC_PR_REPOR_DETAL_PAGO_REGUL ;

/*************************************************************************************
Descripcion       : Procedimiento que carga el Reporte Auditoria Saldo Cuentas x Cobrar
                    para formato CSV
Fecha Creacion    : 14/04/2014
Autor             : Carlos Bazalar
************************************************************************************/
PROCEDURE CCC_PR_REPOR_AUDI_SALDO_CXCOB(
    pscodigopais          VARCHAR2,
    psNombreReporte       VARCHAR2,
    psnombrearchivo       VARCHAR2,
    pstitulo              VARCHAR2,
    psdirectorio          OUT VARCHAR2
)
IS
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         number := 5000;
    v_handle        utl_file.file_type;
    lslinea         varchar2(4000);

    CURSOR C_REPOR_01 IS
         SELECT VAL_PROCE,
							  COD_REGI,
							  COD_ZONA,
							  COD_CLIE,
							  VAL_NOM1,
							  VAL_NOM2,
							  VAL_APE1,
							  VAL_APE2,
							  NUM_DOCU_IDEN,
							  OID_SOLIC_CABE,
							  NUM_DOCU_CONT_INTE,
							  VAL_NUME_SOLI,
							  VAL_TOTA_PAGA_LOCA,
							  decode(oid_peri,  null, '' , GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio3(OID_PERI)) OID_PERI,
							  to_char(FEC_FACT,'dd/MM/yyyy') FEC_FACT,
							  to_char(FEC_VENC,'dd/MM/yyyy') FEC_VENC,
							  SALDO,
							  DIAS_VENC,
							  DIAS_FACT,
							  ind_cast,
							  fec_cast,
							  abo_desp_cast,
							  imp_deud_cast,
							  fec_deud
		  FROM CCC_REPOR_SALDO_DIARIO where SALDO <> 0 ;

    TYPE detalleUnidadesReg01 IS RECORD(
      valorProceso        CCC_REPOR_SALDO_DIARIO.VAL_PROCE%TYPE,
      codigoRegion        CCC_REPOR_SALDO_DIARIO.COD_REGI%TYPE,
      codigoZona          CCC_REPOR_SALDO_DIARIO.COD_ZONA%TYPE,
      codigoCliente       CCC_REPOR_SALDO_DIARIO.COD_CLIE%TYPE,
      nombre1             CCC_REPOR_SALDO_DIARIO.VAL_NOM1%TYPE,
      nombre2             CCC_REPOR_SALDO_DIARIO.VAL_NOM2%TYPE,
      apellidoPaterno     CCC_REPOR_SALDO_DIARIO.VAL_APE1%TYPE,
      apellidoMaterno     CCC_REPOR_SALDO_DIARIO.VAL_APE2%TYPE,
      numeroIdentidad     CCC_REPOR_SALDO_DIARIO.NUM_DOCU_IDEN%TYPE,
      oidSolicitud        CCC_REPOR_SALDO_DIARIO.OID_SOLIC_CABE%TYPE,
      numeroDocuContable  CCC_REPOR_SALDO_DIARIO.NUM_DOCU_CONT_INTE%TYPE,
      numeroSolicitud     CCC_REPOR_SALDO_DIARIO.VAL_NUME_SOLI%TYPE,
      valorTotal          CCC_REPOR_SALDO_DIARIO.VAL_TOTA_PAGA_LOCA%TYPE,
      oidPeri							number(12),
      fechaFactu					varchar2(10),
      fechaVenci					varchar2(10),
      saldo								CCC_REPOR_SALDO_DIARIO.saldo%TYPE,
      DIAS_VENC						CCC_REPOR_SALDO_DIARIO.DIAS_VENC%TYPE,
      DIAS_FACT						CCC_REPOR_SALDO_DIARIO.DIAS_FACT%TYPE,
			ind_cast            CCC_REPOR_SALDO_DIARIO.IND_CAST%TYPE,
			fec_cast            CCC_REPOR_SALDO_DIARIO.FEC_CAST%TYPE,
			abo_desp_cast       CCC_REPOR_SALDO_DIARIO.ABO_DESP_CAST%TYPE,
			imp_deud_cast       CCC_REPOR_SALDO_DIARIO.IMP_DEUD_CAST%TYPE,
			fec_deud            CCC_REPOR_SALDO_DIARIO.FEC_DEUD%TYPE
    );
    TYPE detalleUnidadesRegTab01 IS TABLE OF detalleUnidadesReg01;
    detalleUnidadesRegRecord01 detalleUnidadesRegTab01;
    lbAbrirUtlFile  BOOLEAN;
BEGIN

    lbAbrirUtlFile := true;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
    OPEN C_REPOR_01;
      LOOP
       FETCH C_REPOR_01 BULK COLLECT INTO detalleUnidadesRegRecord01 LIMIT W_FILAS;
       IF lbAbrirUtlFile THEN
          GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psNombreReporte, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
          psdirectorio := lsdirtempo;
          lbAbrirUtlFile := FALSE;
       END IF ;
       IF detalleUnidadesRegRecord01.COUNT > 0 THEN
          FOR x IN detalleUnidadesRegRecord01.FIRST .. detalleUnidadesRegRecord01.LAST LOOP
                lslinea :=
                             '"'|| detalleUnidadesRegRecord01(x).valorProceso || '"' || ',' ||
                             '=T("'|| detalleUnidadesRegRecord01(x).codigoRegion || '")' || ',' ||
                             '=T("'|| detalleUnidadesRegRecord01(x).codigoZona || '")' || ',' ||
                             '=T("'|| detalleUnidadesRegRecord01(x).codigoCliente || '")' || ',' ||
                             '"'|| detalleUnidadesRegRecord01(x).nombre1 || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord01(x).nombre2 || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord01(x).apellidoPaterno || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord01(x).apellidoMaterno || '"' || ',' ||
                             '=T("'|| detalleUnidadesRegRecord01(x).numeroIdentidad || '")' || ',' ||
                             '"'|| detalleUnidadesRegRecord01(x).numeroDocuContable || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord01(x).numeroSolicitud || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord01(x).oidPeri || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord01(x).fechaFactu || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord01(x).fechaVenci || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord01(x).saldo || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord01(x).DIAS_VENC || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord01(x).DIAS_FACT || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord01(x).ind_cast || '"'|| ',' ||
                             '"'|| detalleUnidadesRegRecord01(x).fec_cast || '"'|| ',' ||
                             '"'|| detalleUnidadesRegRecord01(x).abo_desp_cast || '"'|| ',' ||
                             '"'|| detalleUnidadesRegRecord01(x).imp_deud_cast || '"'|| ',' ||
                             '"'|| detalleUnidadesRegRecord01(x).fec_deud || '"' ;
                 UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

          END LOOP;
        END IF;
        EXIT WHEN C_REPOR_01%NOTFOUND;
    END LOOP;
    CLOSE C_REPOR_01;

    IF NOT lbAbrirUtlFile THEN
        utl_file.fclose(V_HANDLE);
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm,1,250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR CCC_PR_REPOR_AUDI_SALDO_CXCOB: '||ls_sqlerrm);

 END CCC_PR_REPOR_AUDI_SALDO_CXCOB ;

 PROCEDURE CCC_PR_REPOR_CONTA_SALDO_CAMPA(
  p_fec_cort                     IN   VARCHAR2)
 IS

  lv_imp_dife                    NUMBER(18,2);
  lv_cod_peri                    seg_perio_corpo.cod_peri%TYPE;

 BEGIN

  CCC_PR_REPOR_ANTIG_SALDO(p_fec_cort,USER);

  DELETE FROM ccc_repor_saldo_conta_campa;

  INSERT INTO ccc_repor_saldo_conta_campa
   SELECT
    spc.cod_peri,
    SUM(a.imp_pend)imp_pend
   FROM
    ccc_repor_detal_antig_saldo a,
    ccc_movim_cuent_corri mcc,
    cra_perio cp,
    seg_perio_corpo spc
   WHERE a.oid_clie = mcc.clie_oid_clie
     AND a.oid_movi_cc = mcc.oid_movi_cc
     AND mcc.perd_oid_peri = cp.oid_peri
     AND cp.peri_oid_peri = spc.oid_peri
     AND a.imp_pend > 0
   HAVING SUM(a.imp_pend) > 0
   GROUP BY spc.cod_peri
   ORDER BY 1 ASC;

  SELECT
   (SELECT SUM(c.imp_pend)
    FROM ccc_repor_detal_antig_clien c
    WHERE c.imp_pend > 0)-
   (SELECT SUM(cm.imp_sald)
    FROM ccc_repor_saldo_conta_campa cm)
   INTO lv_imp_dife
   FROM dual;

  SELECT bcf.cod_peri
  INTO lv_cod_peri
  FROM bas_ctrl_fact bcf
  WHERE bcf.ind_camp_act = 1
    AND bcf.sta_camp = 0;

  UPDATE ccc_repor_saldo_conta_campa c
  SET c.imp_sald = c.imp_sald + lv_imp_dife
  WHERE c.cod_peri = lv_cod_peri;

 END CCC_PR_REPOR_CONTA_SALDO_CAMPA;

 PROCEDURE CCC_PR_GENER_REPOR_CIERR_CONTA(
  p_fec_cort                     IN   DATE)
 IS
 BEGIN

  DELETE FROM ccc_repor_detal_conta_clien;

  INSERT INTO ccc_repor_detal_conta_clien
   SELECT
    mc.oid_clie,
    ccc_pkg_gener.CCC_FN_OBTIE_SALDO_CONTA(mc.oid_clie,p_fec_cort) saldo
   FROM mae_clien mc;

  DELETE FROM ccc_repor_detal_conta_movim;

  INSERT INTO ccc_repor_detal_conta_movim
   WITH
    temp1 AS
      (SELECT mcc.oid_movi_cc , mcc.imp_pago
       FROM
        ccc_movim_cuent_corri mcc,
        ccc_repor_detal_conta_clien i
       WHERE mcc.clie_oid_clie = i.oid_clie
         AND mcc.fec_proc <=  p_fec_cort
         AND mcc.imp_movi > 0
       UNION ALL
       SELECT mcc.oid_movi_cc, SUM(his.imp_pago) imp_pago
       FROM
        ccc_histo_movim_cc his,
        ccc_movim_cuent_corri mcc,
        ccc_repor_detal_conta_clien i
       WHERE mcc.clie_oid_clie = i.oid_clie
         AND mcc.oid_movi_cc = his.mvcc_oid_movi_cc
         AND mcc.imp_movi > 0
         AND his.num_hist > 0
         AND his.fec_proc <= p_fec_cort
       GROUP BY mcc.oid_movi_cc),
    temp2 AS
      (SELECT
        t1.oid_movi_cc,
        SUM(t1.imp_pago) imp_pago
       FROM
        temp1 t1
       GROUP BY t1.oid_movi_cc),
    temp3 AS
      (SELECT
        mcc.clie_oid_clie,
        mcc.oid_movi_cc,
        mcc.imp_movi - t2.imp_pago imp_pend
       FROM
        temp2 t2,
        ccc_movim_cuent_corri mcc
       WHERE mcc.oid_movi_cc = t2.oid_movi_cc)
    SELECT * FROM temp3;

 END CCC_PR_GENER_REPOR_CIERR_CONTA;

/*************************************************************************************
Descripcion       : Procedimiento que carga el Reporte Detallado IFC
                    para formato CSV
Fecha Creacion    : 16/04/2014
Autor             : Frank J Gonzales
************************************************************************************/
 PROCEDURE CCC_PR_GENER_REPOR_DETAL_IFC(
      psfechaCorte          VARCHAR2,
      pscodigopais          VARCHAR2,
      psnombrearchivo       VARCHAR2,
      pstitulo              VARCHAR2,
      psdirectorio          OUT VARCHAR2
    )
    is
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         number := 5000;
    v_handle        utl_file.file_type;
    lslinea         varchar2(4000);


    CURSOR C_REPOR_01 IS
      (SELECT
         mci.num_docu_iden AS "CEDULA",
         su.des_subp AS "PROCESO",
         mc.val_nom1 || ' ' || mc.val_nom2 || ' ' || mc.val_Ape1 || ' ' || mc.val_Ape2 AS "NOMBRES",
         FIN_PKG_GENER.FIN_FN_OBTIE_DIREC_COMPL_CLIEN(mc.oid_clie) AS "DIRECCION",
         GEN_PKG_GENER.gen_fn_clien_datos_oid(mc.oid_clie,'DES_DPTO') AS "DEPARTAMENTO",
         GEN_PKG_GENER.gen_fn_clien_datos_oid(mc.oid_clie,'DES_PROV') AS "MUNICIPIO",
         am.imp_pend AS "VALOR",
         CASE
           WHEN (TO_DATE(psfechaCorte,'DD/MM/YYYY') - mcc.fec_venc) > 0 THEN
            TO_CHAR(TO_DATE(psfechaCorte,'DD/MM/YYYY') - mcc.fec_venc)
           ELSE '0'
         END AS "DIAS VENC",
         TO_CHAR(TO_DATE(psfechaCorte,'DD/MM/YYYY') - mcc.fec_docu) AS "DIAS FACT",
         TO_CHAR(mcc.num_iden_cuot) AS "DOCUMENTO",
         GEN_PKG_GENER.gen_fn_clien_datos_oid(mc.oid_clie,'DES_REGI') AS "REGIONAL",
         GEN_PKG_GENER.gen_fn_clien_datos_oid(mc.oid_clie,'DES_ZONA') AS "ZONA",
         spc.cod_peri AS "CAMPA?A",
         TO_CHAR(mcc.fec_docu,'DD/MM/YYYY') AS "FECHA FACTURA",
         TO_CHAR(mcc.imp_movi) AS "VALOR FACTURA"
        FROM
         ccc_repor_detal_conta_movim am,
         mae_clien mc,
         mae_clien_ident mci,
         ccc_movim_cuent_corri mcc,
         ccc_subpr su,
         cra_perio cp,
         seg_perio_corpo spc
        WHERE am.oid_clie = mc.oid_clie
        AND mc.oid_clie = mci.clie_oid_clie
        AND mcc.subp_oid_subp_crea = su.oid_subp
        AND mci.val_iden_docu_prin = 1
        AND am.oid_movi_cc = mcc.oid_movi_cc
        and mcc.perd_oid_peri = cp.oid_peri
        AND cp.peri_oid_peri = spc.oid_peri
        AND am.imp_pend > 0)
        UNION ALL
        (SELECT
         mci.num_docu_iden AS "CEDULA",
         'SALDO A FAVOR' AS "PROCESO",
         mc.val_nom1 || ' ' || mc.val_nom2 || ' ' || mc.val_Ape1 || ' ' || mc.val_Ape2 AS "NOMBRES",
         FIN_PKG_GENER.FIN_FN_OBTIE_DIREC_COMPL_CLIEN(mc.oid_clie) AS "DIRECCION",
         GEN_PKG_GENER.gen_fn_clien_datos_oid(mc.oid_clie,'DES_DPTO') AS "DEPARTAMENTO",
         GEN_PKG_GENER.gen_fn_clien_datos_oid(mc.oid_clie,'DES_PROV') AS "MUNICIPIO",
         A.IMP_PEND AS "VALOR",
         ' ' AS "DIAS VENC",
         ' ' AS "DIAS FACT",
         ' ' AS "DOCUMENTO",
         GEN_PKG_GENER.gen_fn_clien_datos_oid(mc.oid_clie,'DES_REGI') AS "REGIONAL",
         GEN_PKG_GENER.gen_fn_clien_datos_oid(mc.oid_clie,'DES_ZONA') AS "ZONA",
         ' ' AS "CAMPA?A",
         ' ' AS "FECHA FACTURA",
         ' ' AS "VALOR FACTURA"
        FROM
         ccc_repor_detal_conta_clien a,
         mae_clien mc,
         mae_clien_ident mci
        WHERE a.oid_clie = mc.oid_clie
        AND mc.oid_clie = mci.clie_oid_clie
        AND mci.val_iden_docu_prin = 1
        AND a.imp_pend < 0);

    TYPE detalleUnidadesReg01 IS RECORD(
      cedula                  VARCHAR2(30),
      proceso                 VARCHAR2(40),
      nombres                 VARCHAR2(120),
      direccion               VARCHAR2(100),
      departamento            VARCHAR2(100),
      municipio               VARCHAR2(100),
      valor                   NUMBER(15,2),
      dias_venc               VARCHAR2(10),
      dias_fact               VARCHAR2(10),
      documento               VARCHAR2(20),
      regional                VARCHAR2(20),
      zona                    VARCHAR2(20),
      campania                seg_perio_corpo.cod_peri%TYPE,
      fecha_fact              VARCHAR2(50),
      valor_fact              VARCHAR2(50)
    );
    TYPE detalleUnidadesRegTab01 IS TABLE OF detalleUnidadesReg01;
    detalleUnidadesRegRecord01 detalleUnidadesRegTab01;
    lbAbrirUtlFile  BOOLEAN;
    BEGIN
      lbAbrirUtlFile := true;
      EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
       OPEN C_REPOR_01;
          LOOP
           FETCH C_REPOR_01 BULK COLLECT INTO detalleUnidadesRegRecord01 LIMIT W_FILAS;
           IF lbAbrirUtlFile THEN
              GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
              psdirectorio := lsdirtempo;
              lbAbrirUtlFile := FALSE;
           END IF ;
           IF detalleUnidadesRegRecord01.COUNT > 0 THEN
              FOR x IN detalleUnidadesRegRecord01.FIRST .. detalleUnidadesRegRecord01.LAST LOOP
                lslinea :=
                          '"'|| detalleUnidadesRegRecord01(x).cedula || '"' || ',' ||
                          '"'|| detalleUnidadesRegRecord01(x).proceso || '"' || ',' ||
                          '"'|| detalleUnidadesRegRecord01(x).nombres || '"' || ',' ||
                          '"'|| detalleUnidadesRegRecord01(x).direccion || '"' || ',' ||
                          '"'|| detalleUnidadesRegRecord01(x).departamento || '"' || ',' ||
                          '"'|| detalleUnidadesRegRecord01(x).municipio || '"' || ',' ||
                          '"'|| detalleUnidadesRegRecord01(x).valor || '"' || ',' ||
                          '"'|| detalleUnidadesRegRecord01(x).dias_venc || '"' || ',' ||
                          '"'|| detalleUnidadesRegRecord01(x).dias_fact || '"' || ',' ||
                          '"'|| detalleUnidadesRegRecord01(x).documento || '"' || ',' ||
                          '"'|| detalleUnidadesRegRecord01(x).fecha_fact || '"' || ',' ||
                          '"'|| detalleUnidadesRegRecord01(x).valor_fact || '"' ;
                UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
              END LOOP;
           END IF;
           EXIT WHEN C_REPOR_01%NOTFOUND;
       END LOOP;
       CLOSE C_REPOR_01;
       IF NOT lbAbrirUtlFile THEN
          utl_file.fclose(V_HANDLE);
       END IF;
EXCEPTION
    WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm,1,250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR CCC_PR_GENER_REPOR_DETAL_IFC: '||ls_sqlerrm);
    END CCC_PR_GENER_REPOR_DETAL_IFC;

/*************************************************************************************
Descripcion       : Procedimiento que carga el Reporte Gasto Cupon para formato CSV
Fecha Creacion    : 15/05/2014
Autor             : Aurelio Oviedo
************************************************************************************/
PROCEDURE CCC_PR_REPOR_GASTO_CUPON(
    pscodigopais          VARCHAR2,
    psTipoReporte         VARCHAR2,
    psfechaDesde      VARCHAR2,
    psfechaHasta      VARCHAR2,
    psnombrearchivo       VARCHAR2,
    pstitulo              VARCHAR2,
    psdirectorio          OUT VARCHAR2
)
IS
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         number := 5000;
    v_handle        utl_file.file_type;
    lslinea         varchar2(4000);

    CURSOR C_REPOR_BANCO IS
        SELECT c.des_cc banco, COUNT(*) cant, sum(b.val_pago) impo,a.tior_tipo_orig_dato codigo,per.des_tipo_orig_dato Descipcion_Dato
        FROM per_movim_banca_cabec a,
             per_movim_banca_detal b,
             ccc_cuent_corri_banca c,
             per_tipo_orige_datos per
        WHERE a.num_lote_inte = b.moca_num_lote_inte
        AND a.cod_ccba = c.cod_cc
        AND a.tior_tipo_orig_dato = b.tior_tipo_orig_dato
        AND b.fec_proc >= to_date(psfechaDesde, 'DD/MM/YYYY')
        AND b.fec_proc <= to_date(psfechaHasta, 'DD/MM/YYYY')
        and  per.cod_tipo_orig_dato=a.tior_tipo_orig_dato
        GROUP BY c.des_cc,a.tior_tipo_orig_dato,per.des_tipo_orig_dato;


    CURSOR C_REPOR_ZONA IS
        SELECT c.des_cc banco, zr.cod_regi,zz.cod_zona,COUNT(*) cant, sum(b.val_pago) impo,a.tior_tipo_orig_dato codigo,per.des_tipo_orig_dato Descipcion_Dato
        FROM per_movim_banca_cabec a,
             per_movim_banca_detal b,
             ccc_cuent_corri_banca c,
             mae_clien mc,
             mae_clien_unida_admin mcua,
             zon_terri_admin zta,
             zon_secci zs,
             zon_zona zz,
             zon_regio zr,
             per_tipo_orige_datos per
        WHERE a.num_lote_inte = b.moca_num_lote_inte
        AND a.cod_ccba = c.cod_cc
        AND b.cod_cons = mc.cod_clie
        AND mc.oid_clie = mcua.clie_oid_clie
        AND mcua.ztad_oid_terr_admi = zta.oid_terr_admi
        AND mcua.ind_acti = 1
        AND zta.zscc_oid_secc = zs.oid_secc
        AND zs.zzon_oid_zona = zz.oid_zona
        AND zz.zorg_oid_regi = zr.oid_regi
        AND a.tior_tipo_orig_dato = b.tior_tipo_orig_dato
        AND b.fec_proc >= to_date(psfechaDesde, 'DD/MM/YYYY')
        AND b.fec_proc <= to_date(psfechaHasta, 'DD/MM/YYYY')
        and  per.cod_tipo_orig_dato=a.tior_tipo_orig_dato
        GROUP BY c.des_cc, zr.cod_regi,zz.cod_zona,a.tior_tipo_orig_dato,per.des_tipo_orig_dato
        ORDER BY 1,2,3 ASC;


    TYPE detalleBanco IS RECORD(
      des_banc                  CCC_CUENT_CORRI_BANCA.DES_CC%TYPE,
      cant                          NUMBER,
      imp_pago                  PER_MOVIM_BANCA_DETAL.VAL_PAGO%TYPE,
      tipo_dato_codigo          PER_MOVIM_BANCA_CABEC.TIOR_TIPO_ORIG_DATO%TYPE,
      tipo_dato_desc            PER_TIPO_ORIGE_DATOS.DES_TIPO_ORIG_DATO%TYPE
    );

    TYPE detalleZona IS RECORD(
      des_banc                  CCC_CUENT_CORRI_BANCA.DES_CC%TYPE,
      cod_regi                    ZON_REGIO.COD_REGI%TYPE,
      cod_zona                  ZON_ZONA.COD_ZONA%TYPE,
      cant                          NUMBER,
      imp_pago                  PER_MOVIM_BANCA_DETAL.VAL_PAGO%TYPE,
      tipo_dato_codigo          PER_MOVIM_BANCA_CABEC.TIOR_TIPO_ORIG_DATO%TYPE,
      tipo_dato_desc            PER_TIPO_ORIGE_DATOS.DES_TIPO_ORIG_DATO%TYPE
    );

    TYPE detalleBancoTab IS TABLE OF detalleBanco;
    detalleBancoRecord detalleBancoTab;

    TYPE detalleZonaTab IS TABLE OF detalleZona;
    detalleZonaRecord detalleZonaTab;

    lbAbrirUtlFile  BOOLEAN;

BEGIN

    lbAbrirUtlFile := true;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';

    IF psTipoReporte = 'Banco' THEN
        OPEN C_REPOR_BANCO;
        LOOP
            FETCH C_REPOR_BANCO BULK COLLECT INTO detalleBancoRecord LIMIT W_FILAS;
            IF lbAbrirUtlFile THEN
                GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
                psdirectorio := lsdirtempo;
                lbAbrirUtlFile := FALSE;
            END IF ;

            IF detalleBancoRecord.COUNT > 0 THEN
                FOR x IN detalleBancoRecord.FIRST .. detalleBancoRecord.LAST LOOP
                    lslinea :=
                                 '"'|| detalleBancoRecord(x).des_banc || '"' || ',' ||
                                 '"'|| detalleBancoRecord(x).cant || '"' || ',' ||
                                 '"'|| detalleBancoRecord(x).imp_pago || '"' || ',' ||
                                 '"'|| detalleBancoRecord(x).tipo_dato_codigo || '"' || ',' ||
                                 '"'|| detalleBancoRecord(x).tipo_dato_desc || '"' ;

                    UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
                END LOOP;
            END IF;
            EXIT WHEN C_REPOR_BANCO%NOTFOUND;
        END LOOP;
        CLOSE C_REPOR_BANCO;
    END IF;

    IF psTipoReporte = 'Zona' THEN
        OPEN C_REPOR_ZONA;
        LOOP
            FETCH C_REPOR_ZONA BULK COLLECT INTO detalleZonaRecord LIMIT W_FILAS;
            IF lbAbrirUtlFile THEN
                GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
                psdirectorio := lsdirtempo;
                lbAbrirUtlFile := FALSE;
            END IF ;

            IF detalleZonaRecord.COUNT > 0 THEN
                FOR x IN detalleZonaRecord.FIRST .. detalleZonaRecord.LAST LOOP
                    lslinea :=
                                 '"'|| detalleZonaRecord(x).des_banc || '"' || ',' ||
                                 '"'|| detalleZonaRecord(x).cod_regi || '"' || ',' ||
                                 '"'|| detalleZonaRecord(x).cod_zona || '"' || ',' ||
                                 '"'|| detalleZonaRecord(x).cant || '"' || ',' ||
                                 '"'|| detalleZonaRecord(x).imp_pago || '"' || ',' ||
                                 '"'|| detalleZonaRecord(x).tipo_dato_codigo || '"' || ',' ||
                                 '"'|| detalleZonaRecord(x).tipo_dato_desc || '"' ;

                    UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
                END LOOP;
            END IF;
            EXIT WHEN C_REPOR_ZONA%NOTFOUND;
        END LOOP;
        CLOSE C_REPOR_ZONA;
    END IF;

    IF NOT lbAbrirUtlFile THEN
        utl_file.fclose(V_HANDLE);
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm,1,250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR CCC_PR_REPOR_GASTO_CUPON: '||ls_sqlerrm);

END CCC_PR_REPOR_GASTO_CUPON ;

 /************************************************************************************
  Descripcion           : Procedimiento que limpia las tablas temporales de Medios Magneticos
                          Bimensual
  Fecha Creacion        : 21/02/2014
  Autor                 : Diego Torres
  ************************************************************************************/
PROCEDURE COB_PR_REPOR_MEDIO_MAGNE_BIMEP(
    psfechaCorte      VARCHAR2
)
     IS

  BEGIN

    DELETE FROM ccc_repor_medio_magne_bimen;

    INSERT INTO ccc_repor_medio_magne_bimen (COD_CLIE, NUM_DOCU_IDEN, VAL_DEPT, VAL_PROV, VAL_DIST, VAL_DIRE, IMP_SALD_INIC, IMP_CARG, IMP_ABON, IMP_SALD_FINA)
    VALUES ('COD_CLIE','NUM_DOCU','VAL_DEPT','VAL_PROV','VAL_DIST','VAL_DIRE',1,2,3,4);

  EXCEPTION
      WHEN OTHERS THEN
          ln_sqlcode := sqlcode;
          ls_sqlerrm := substr(sqlerrm,1,250);
          RAISE_APPLICATION_ERROR(-20123, 'ERROR COB_PR_REPOR_MEDIO_MAGNE_BIMEP: '||ls_sqlerrm);

  END COB_PR_REPOR_MEDIO_MAGNE_BIMEP;

 /*************************************************************************************
  Descripcion       : Procedimiento que carga el Reporte Medios Magneticos Bimensual para formato CSV
  Fecha Creacion    : 24/10/2014
  Autor             : Diego Torres
  ************************************************************************************/
PROCEDURE CCC_PR_GEN_REPOR_MED_MAG_BIMEN(
    pscodigopais          VARCHAR2,
    psnombrearchivo       VARCHAR2,
    pstitulo              VARCHAR2,
    psdirectorio          OUT VARCHAR2
)
IS
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         number := 5000;
    v_handle        utl_file.file_type;
    lslinea         varchar2(4000);

    CURSOR C_REPOR_MEDIO_MAGNE_BIMEN IS
        SELECT
          b.cod_clie AS "Codigo Consultora",
          b.num_docu_iden AS "Cedula",
          b.val_dept AS "Departamento",
          b.val_prov AS "Provincia",
          b.val_dist AS "Distrito",
          b.val_dire AS "Direccion",
          b.imp_sald_inic AS "Saldo Inicial",
          b.imp_carg AS "Cargo",
          b.imp_abon AS "Abono",
          b.imp_sald_fina AS "Saldo Final"
         FROM ccc_repor_medio_magne_bimen b
        ORDER BY 1 ASC;


    TYPE medioMagneticoBimen IS RECORD(
      codigo_consultora         CCC_REPOR_MEDIO_MAGNE_BIMEN.COD_CLIE%TYPE,
      cedula                    CCC_REPOR_MEDIO_MAGNE_BIMEN.NUM_DOCU_IDEN%TYPE,
      departamento              CCC_REPOR_MEDIO_MAGNE_BIMEN.VAL_DEPT%TYPE,
      provincia                 CCC_REPOR_MEDIO_MAGNE_BIMEN.VAL_PROV%TYPE,
      distrito                  CCC_REPOR_MEDIO_MAGNE_BIMEN.VAL_DIST%TYPE,
      direccion                 CCC_REPOR_MEDIO_MAGNE_BIMEN.VAL_DIRE%TYPE,
      saldo_inicial             CCC_REPOR_MEDIO_MAGNE_BIMEN.IMP_SALD_INIC%TYPE,
      cargo                     CCC_REPOR_MEDIO_MAGNE_BIMEN.IMP_CARG%TYPE,
      abono                     CCC_REPOR_MEDIO_MAGNE_BIMEN.IMP_ABON%TYPE,
      saldo_final               CCC_REPOR_MEDIO_MAGNE_BIMEN.IMP_SALD_FINA %TYPE
    );

    TYPE medioMagneticoBimenTab IS TABLE OF medioMagneticoBimen;
    medioMagneticoBimenRecord medioMagneticoBimenTab;


    lbAbrirUtlFile  BOOLEAN;

BEGIN

    lbAbrirUtlFile := true;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';


    OPEN C_REPOR_MEDIO_MAGNE_BIMEN;
        LOOP
            FETCH C_REPOR_MEDIO_MAGNE_BIMEN BULK COLLECT INTO medioMagneticoBimenRecord LIMIT W_FILAS;
            IF lbAbrirUtlFile THEN
                GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
                psdirectorio := lsdirtempo;
                lbAbrirUtlFile := FALSE;
            END IF ;

            IF medioMagneticoBimenRecord.COUNT > 0 THEN
                FOR x IN medioMagneticoBimenRecord.FIRST .. medioMagneticoBimenRecord.LAST LOOP
                    lslinea :=
                                 '"'|| medioMagneticoBimenRecord(x).codigo_consultora || '"' || ',' ||
                                 '"'|| medioMagneticoBimenRecord(x).cedula || '"' || ',' ||
                                 '"'|| medioMagneticoBimenRecord(x).departamento || '"' || ',' ||
                                 '"'|| medioMagneticoBimenRecord(x).provincia || '"' || ',' ||
                                 '"'|| medioMagneticoBimenRecord(x).distrito || '"' || ',' ||
                                 '"'|| medioMagneticoBimenRecord(x).direccion || '"' || ',' ||
                                 '"'|| medioMagneticoBimenRecord(x).saldo_inicial || '"' || ',' ||
                                 '"'|| medioMagneticoBimenRecord(x).cargo || '"' || ',' ||
                                 '"'|| medioMagneticoBimenRecord(x).abono || '"' || ',' ||
                                 '"'|| medioMagneticoBimenRecord(x).saldo_final || '"' ;

                    UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
                END LOOP;
            END IF;
            EXIT WHEN C_REPOR_MEDIO_MAGNE_BIMEN%NOTFOUND;
        END LOOP;
     CLOSE C_REPOR_MEDIO_MAGNE_BIMEN;




    IF NOT lbAbrirUtlFile THEN
        utl_file.fclose(V_HANDLE);
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm,1,250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR CCC_PR_GEN_REPOR_MED_MAG_BIMEN: '||ls_sqlerrm);

END CCC_PR_GEN_REPOR_MED_MAG_BIMEN ;



PROCEDURE CCC_PR_DETAL_CONSUL_INCOBR(
    psTitulo                       VARCHAR2,
    psCodigoPais                     VARCHAR2,
    psNombreArchivo                  VARCHAR2,
    psDirectorio                   OUT  VARCHAR2,
    psCampanaHasta                     VARCHAR2,
    psFechaHasta                          VARCHAR2,
    psRegion                        VARCHAR2,
    psZona                     VARCHAR2,
    psSeccions                            VARCHAR2
    )
IS

  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 5000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(4000);
  lsFlagRegionesZonas VARCHAR2(1);
  lnOidCodigoPeriodo NUMBER;

  CURSOR c_interfaz IS
    SELECT
           c.cod_regi AS "Region",
           c.cod_zona AS "Zona",
           c.cod_secc AS "Seccion",
           c.cod_clie AS "Codigo",
           c.nom_clie AS "Nombres y Apellidos",
           c.num_docu_iden AS "Cedula",
           c.cod_camp_mini AS "Campaña Deuda Minima",
           c.cod_camp_maxi AS "Campaña Deuda Maxima",
           c.fec_deud_mini AS "Fecha Deuda Minima",
           c.fec_deud_maxi AS "Fecha Deuda Maxima",
           c.imp_deud_pend AS "Importe Deuda Pendiente"
    FROM ccc_repor_previ_consu_incob c
    WHERE  (cod_regi = psRegion or psRegion is null) AND
           (cod_zona = psZona or psZona is null) AND
           (cod_secc = psSeccions or psSeccions is null);

TYPE interfazTipo IS RECORD (

  v_COD_REGI           ccc_repor_previ_consu_incob.cod_regi%TYPE,
  v_COD_ZONA           ccc_repor_previ_consu_incob.cod_ZONa%TYPE,
  v_COD_SECC           ccc_repor_previ_consu_incob.cod_secc%TYPE,
  v_COD_CLIE           ccc_repor_previ_consu_incob.cod_clie%TYPE,
  v_nom_clie           ccc_repor_previ_consu_incob.nom_clie%TYPE,
  v_NUM_DOCU_IDEN      ccc_repor_previ_consu_incob.num_docu_iden%TYPE,
  v_COD_CAMP_MINI      ccc_repor_previ_consu_incob.cod_camp_mini%TYPE,
  v_COD_CAMP_MAXI      ccc_repor_previ_consu_incob.cod_camp_maxi%TYPE,
  v_FEC_DEUD_MINI      ccc_repor_previ_consu_incob.fec_deud_mini%TYPE,
  v_FEC_DEUD_MAXI      ccc_repor_previ_consu_incob.fec_deud_maxi%TYPE,
  v_IMP_DEUD_PEND      ccc_repor_previ_consu_incob.imp_deud_pend%TYPE
);

   TYPE interfazTab  IS TABLE OF interfazTipo;
   interfazRecord interfazTab;
   lbAbrirUtlFile  BOOLEAN;

BEGIN

  --lnOidCodigoPeriodo := GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(psCodigoPeriodo);

  lbAbrirUtlFile := TRUE;

  OPEN c_interfaz;
  LOOP
   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
   IF lbAbrirUtlFile THEN
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.csv', psTitulo, lsDirTempo, V_HANDLE);
      psDirectorio   := lsdirtempo;
      lbAbrirUtlFile := FALSE;

   END IF ;
   IF interfazRecord.COUNT > 0 THEN
      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP

          lsLinea :='=T("'||interfazRecord(x).v_COD_REGI||'")' ||','||
                    '=T("'||interfazRecord(x).v_COD_ZONA||'")' ||','||
                    '=T("'||interfazRecord(x).v_COD_SECC ||'")' ||','||
                    '=T("'||interfazRecord(x).v_COD_CLIE||'")' ||','||
                    '=T("'||interfazRecord(x).v_NOM_CLIE||'")' ||','||
                    '=T("'||interfazRecord(x).v_NUM_DOCU_IDEN||'")' ||','||
                    '=T("'||interfazRecord(x).v_COD_CAMP_MINI||'")' ||','||
                    '=T("'||interfazRecord(x).v_COD_CAMP_MAXI||'")' ||','||
                    '=T("'||interfazRecord(x).v_FEC_DEUD_MINI||'")' ||','||
                    '=T("'||interfazRecord(x).v_FEC_DEUD_MAXI||'")' ||','||
                    '"'|| interfazRecord(x).v_IMP_DEUD_PEND || '"' ;

            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
      END LOOP;
    END IF;
    EXIT WHEN c_interfaz%NOTFOUND;
 END LOOP;
 CLOSE c_interfaz;
 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
 END IF;
 RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR CCC_PR_DETAL_CONSUL_INCOBR: '||ls_sqlerrm);
END CCC_PR_DETAL_CONSUL_INCOBR;


PROCEDURE CCC_PR_DET_PROV_INCOBR_CONSLT(
    psTitulo                       VARCHAR2,
    psCodigoPais                     VARCHAR2,
    psNombreArchivo                  VARCHAR2,
    psDirectorio                   OUT  VARCHAR2,
    psFechaInicio                     VARCHAR2,
    psFechaFin                          VARCHAR2
    )
IS

  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 8000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(8000);
  lsFlagRegionesZonas VARCHAR2(1);
  lnOidCodigoPeriodo NUMBER;

  CURSOR c_interfaz IS
    SELECT
           c.cod_regi AS "Region",
           c.cod_zona AS "Zona",
           c.cod_secc AS "Seccion",
           c.cod_terr AS "Territorio",
           c.cod_clie AS "Codigo",
           c.cod_digi_ctrl AS "Digito Control",
           c.nom_clie AS "Nombres y Apellidos",
           c.num_docu_iden AS "Cedula",
           c.cod_camp_ulti_pedi AS "Campaña Ultimo Pedido",
           c.val_Tele_fijo AS "Telefono Fijo",
           c.val_tele_trab AS "Telefono Trabajo",
           c.val_tele_movi AS "Telefono Movil",
           c.val_emai AS "Correo Electrónico",
           c.val_dept AS "Departamento",
           c.val_prov AS "Provincia",
           c.val_dist AS "Distrito",
           c.val_urba AS "Urbanizacion",
           c.val_dire AS "Direccion",
           c.fec_cast AS "Fecha Castigo",
           c.fec_deud AS "Fecha Deuda Castigada",
           c.imp_deud_cast AS "Importe Deudas Castigada"
    FROM ccc_consu_casti_cabec c
    WHERE c.fec_cast >= TO_DATE(psFechaInicio,'DD/MM/YYYY')
                            AND c.fec_cast <= TO_DATE(psFechaFin,'DD/MM/YYYY')
          ORDER BY c.cod_regi,
      c.cod_zona,
      c.cod_secc,
      c.cod_terr,
      c.cod_clie ASC;

  TYPE interfazTipo IS RECORD (
    v_COD_REGI           ccc_consu_casti_cabec.COD_REGI%TYPE,
    v_COD_ZONA           ccc_consu_casti_cabec.COD_ZONA%TYPE,
    v_COD_SECC           ccc_consu_casti_cabec.COD_SECC%TYPE,
    v_COD_TERR           ccc_consu_casti_cabec.COD_TERR%TYPE,
    v_COD_CLIE           ccc_consu_casti_cabec.COD_CLIE%TYPE,
    v_COD_DIGI_CTRL      ccc_consu_casti_cabec.COD_DIGI_CTRL%TYPE,
    v_NOM_CLIE      ccc_consu_casti_cabec.NOM_CLIE%TYPE,
    v_NUM_DOCU_IDEN      ccc_consu_casti_cabec.NUM_DOCU_IDEN%TYPE,
    v_COD_CAMP_UTIL_PEDI      ccc_consu_casti_cabec.COD_CAMP_ULTI_PEDI%TYPE,
    v_VAL_TELE_FIJO      ccc_consu_casti_cabec.VAL_TELE_FIJO%TYPE,
    v_VAL_TELE_TRAB      ccc_consu_casti_cabec.VAL_TELE_TRAB%TYPE,
    v_VAL_TELE_MOVI      ccc_consu_casti_cabec.VAL_TELE_MOVI%TYPE,
    v_VAL_EMAI           ccc_consu_casti_cabec.VAL_EMAI%TYPE,
    v_VAL_DEPT           ccc_consu_casti_cabec.VAL_DEPT%TYPE,
    v_VAL_PROV           ccc_consu_casti_cabec.VAL_PROV%TYPE,
    v_VAL_DIST           ccc_consu_casti_cabec.VAL_DIST%TYPE,
    v_VAL_URBA           ccc_consu_casti_cabec.VAL_URBA%TYPE,
    v_VAL_DIRE           ccc_consu_casti_cabec.VAL_DIRE%TYPE,
    v_FEC_CAST           ccc_consu_casti_cabec.FEC_CAST%TYPE,
    v_FEC_DEUD           ccc_consu_casti_cabec.FEC_DEUD%TYPE,
    v_IMP_DEUD_CAST      ccc_consu_casti_cabec.IMP_DEUD_CAST%TYPE
    );

   TYPE interfazTab  IS TABLE OF interfazTipo;
   interfazRecord interfazTab;
   lbAbrirUtlFile  BOOLEAN;

BEGIN

  lbAbrirUtlFile := TRUE;

  OPEN c_interfaz;
  LOOP
   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
   IF lbAbrirUtlFile THEN
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.csv', psTitulo, lsDirTempo, V_HANDLE);
      psDirectorio   := lsdirtempo;
      lbAbrirUtlFile := FALSE;

   END IF ;
   IF interfazRecord.COUNT > 0 THEN
      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP


          lsLinea :='=T("'||interfazRecord(x).v_COD_REGI||'")' ||','||
                    '=T("'||interfazRecord(x).v_COD_ZONA||'")' ||','||
                    '=T("'||interfazRecord(x).v_COD_SECC ||'")' ||','||
                    '=T("'||interfazRecord(x).v_COD_TERR ||'")' ||','||
                    '=T("'||interfazRecord(x).v_COD_CLIE ||'")' ||','||
                    '=T("'||interfazRecord(x).v_NOM_CLIE ||'")' ||','||
                    '=T("'||interfazRecord(x).v_NUM_DOCU_IDEN ||'")' ||','||
                    '=T("'||interfazRecord(x).v_COD_CAMP_UTIL_PEDI ||'")' ||','||
                    '=T("'||interfazRecord(x).v_VAL_TELE_FIJO ||'")' ||','||
                    '=T("'||interfazRecord(x).v_VAL_TELE_TRAB ||'")' ||','||
                    '=T("'||interfazRecord(x).v_VAL_TELE_MOVI ||'")' ||','||
                    '=T("'||interfazRecord(x).v_VAL_EMAI ||'")' ||','||
                    '=T("'||interfazRecord(x).v_VAL_DEPT ||'")' ||','||
                    '=T("'||interfazRecord(x).v_VAL_PROV ||'")' ||','||
                    '=T("'||interfazRecord(x).v_VAL_DIST ||'")' ||','||
                    '=T("'||interfazRecord(x).v_VAL_URBA ||'")' ||','||
                    '=T("'||interfazRecord(x).v_VAL_DIRE ||'")' ||','||
                    '=T("'||interfazRecord(x).v_FEC_CAST ||'")' ||','||
                    '=T("'||interfazRecord(x).v_FEC_DEUD ||'")' ||','||
                    '=T("'||interfazRecord(x).v_IMP_DEUD_CAST ||'")';

            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
      END LOOP;
    END IF;
    EXIT WHEN c_interfaz%NOTFOUND;
 END LOOP;
 CLOSE c_interfaz;
 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
 END IF;
 RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR CCC_PR_DET_PROV_INCOBR_CONSLT: '||ls_sqlerrm);

END CCC_PR_DET_PROV_INCOBR_CONSLT;


PROCEDURE CCC_PR_DETAL_PROV_INCOBR_MOV(
    psTitulo                       VARCHAR2,
    psCodigoPais                     VARCHAR2,
    psNombreArchivo                  VARCHAR2,
    psDirectorio                   OUT  VARCHAR2,
    psFechaInicio                     VARCHAR2,
    psFechaFin                          VARCHAR2
    )
IS

  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 5000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(4000);
  lsFlagRegionesZonas VARCHAR2(1);
  lnOidCodigoPeriodo NUMBER;

  CURSOR c_interfaz IS
    SELECT
           c.cod_regi AS "Region",
           c.cod_zona AS "Zona",
           c.cod_secc AS "Seccion",
           c.cod_terr AS "Territorio",
           c.cod_clie AS "Codigo",
           c.cod_digi_ctrl AS "Digito Control",
           c.nom_clie AS "Nombres y Apellidos",
           c.num_docu_iden AS "Cedula",
           c.cod_camp_ulti_pedi AS "Campaña Ultimo Pedido",
           c.val_Tele_fijo AS "Telefono Fijo",
           c.val_tele_trab AS "Telefono Trabajo",
           c.val_tele_movi AS "Telefono Movil",
           c.val_emai AS "Correo Electrónico",
           c.val_dept AS "Departamento",
           c.val_prov AS "Provincia",
           c.val_dist AS "Distrito",
           c.val_urba AS "Urbanizacion",
           c.val_dire AS "Direccion",
           c.fec_cast AS "Fecha Castigo",
           d.val_tipo_carg AS "Tipo Cargo",
           d.cod_camp AS "Campaña Cargo",
           d.fec_docu AS "Fecha Documento",
           d.oid_movi_cc AS "Numero Cargo",
           d.imp_docu AS "Importe Documento",
           d.imp_deud_cast AS "Importe Castigado"
          FROM
           ccc_consu_casti_cabec c,
           ccc_consu_casti_detal d
          WHERE c.oid_cons_cast_cabe = d.ccca_oid_cons_cast_cabe
                AND c.fec_cast >= TO_DATE(psFechaInicio,'DD/MM/YYYY')
                AND c.fec_cast <= TO_DATE(psFechaFin,'DD/MM/YYYY')
          ORDER BY c.cod_regi,
                   c.cod_zona,
                   c.cod_secc,
                   c.cod_terr,
                   c.cod_clie ASC;

TYPE interfazTipo IS RECORD (

  v_COD_REGI           ccc_consu_casti_cabec.COD_REGI%TYPE,
  v_COD_ZONA           ccc_consu_casti_cabec.COD_ZONA%TYPE,
  v_COD_SECC           ccc_consu_casti_cabec.COD_SECC%TYPE,
  v_COD_TERR           ccc_consu_casti_cabec.COD_TERR%TYPE,
  v_COD_CLIE           ccc_consu_casti_cabec.COD_CLIE%TYPE,
  v_COD_DIGI_CTRL      ccc_consu_casti_cabec.COD_DIGI_CTRL%TYPE,
  v_NOM_CLIE      ccc_consu_casti_cabec.NOM_CLIE%TYPE,
  v_NUM_DOCU_IDEN      ccc_consu_casti_cabec.NUM_DOCU_IDEN%TYPE,
  v_COD_CAMP_ULTI_PEDI      ccc_consu_casti_cabec.COD_CAMP_ULTI_PEDI%TYPE,
  v_VAL_TELE_FIJO      ccc_consu_casti_cabec.VAL_TELE_FIJO%TYPE,
  v_VAL_TELE_TRAB      ccc_consu_casti_cabec.VAL_TELE_TRAB%TYPE,
  v_VAL_TELE_MOVI      ccc_consu_casti_cabec.VAL_TELE_MOVI%TYPE,
  v_VAL_EMAI           ccc_consu_casti_cabec.VAL_EMAI%TYPE,
  v_VAL_DEPT           ccc_consu_casti_cabec.VAL_DEPT%TYPE,
  v_VAL_PROV           ccc_consu_casti_cabec.VAL_PROV%TYPE,
  v_VAL_DIST           ccc_consu_casti_cabec.VAL_DIST%TYPE,
  v_VAL_URBA           ccc_consu_casti_cabec.VAL_URBA%TYPE,
  v_VAL_DIRE           ccc_consu_casti_cabec.VAL_DIRE%TYPE,
  v_FEC_CAST           ccc_consu_casti_cabec.FEC_CAST%TYPE,
  v_VAL_TIPO_CARG      ccc_consu_casti_detal.VAL_TIPO_CARG%TYPE,
  v_COD_CAMP           ccc_consu_casti_detal.COD_CAMP%TYPE,
  v_FEC_DOCU           ccc_consu_casti_detal.FEC_DOCU%TYPE,
  v_OID_MOVI_CC           ccc_consu_casti_detal.OID_MOVI_CC%TYPE,
  v_IMP_DOCU           ccc_consu_casti_detal.IMP_DOCU%TYPE,
  v_IMP_DEUD_CAST           ccc_consu_casti_detal.IMP_DEUD_CAST%TYPE
);

   TYPE interfazTab  IS TABLE OF interfazTipo;
   interfazRecord interfazTab;
   lbAbrirUtlFile  BOOLEAN;

BEGIN

  --lnOidCodigoPeriodo := GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(psCodigoPeriodo);

  lbAbrirUtlFile := TRUE;

  OPEN c_interfaz;
  LOOP
   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
   IF lbAbrirUtlFile THEN
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.csv', psTitulo, lsDirTempo, V_HANDLE);
      psDirectorio   := lsdirtempo;
      lbAbrirUtlFile := FALSE;

   END IF ;
   IF interfazRecord.COUNT > 0 THEN
      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP


          lsLinea :='=T("'||interfazRecord(x).v_COD_REGI||'")' ||','||
                    '=T("'||interfazRecord(x).v_COD_ZONA||'")' ||','||
                    '=T("'||interfazRecord(x).v_COD_SECC ||'")' ||','||
                    '=T("'||interfazRecord(x).v_COD_TERR ||'")' ||','||
                    '=T("'||interfazRecord(x).v_COD_CLIE ||'")' ||','||
                    '=T("'||interfazRecord(x).v_COD_DIGI_CTRL ||'")' ||','||
                    '=T("'||interfazRecord(x).v_NOM_CLIE ||'")' ||','||
                    '=T("'||interfazRecord(x).v_NUM_DOCU_IDEN ||'")' ||','||
                    '=T("'||interfazRecord(x).v_COD_CAMP_ULTI_PEDI ||'")' ||','||
                    '=T("'||interfazRecord(x).v_VAL_TELE_FIJO ||'")' ||','||
                    '=T("'||interfazRecord(x).v_VAL_TELE_TRAB ||'")' ||','||
                    '=T("'||interfazRecord(x).v_VAL_TELE_MOVI ||'")' ||','||
                    '=T("'||interfazRecord(x).v_VAL_EMAI ||'")' ||','||
                    '=T("'||interfazRecord(x).v_VAL_DEPT ||'")' ||','||
                    '=T("'||interfazRecord(x).v_VAL_PROV ||'")' ||','||
                    '=T("'||interfazRecord(x).v_VAL_DIST ||'")' ||','||
                    '=T("'||interfazRecord(x).v_VAL_URBA ||'")' ||','||
                    '=T("'||interfazRecord(x).v_VAL_DIRE ||'")' ||','||
                    '=T("'||interfazRecord(x).v_FEC_CAST ||'")' ||','||
                    '=T("'||interfazRecord(x).v_VAL_TIPO_CARG ||'")' ||','||
                    '=T("'||interfazRecord(x).v_COD_CAMP ||'")' ||','||
                    '=T("'||interfazRecord(x).v_FEC_DOCU ||'")' ||','||
                    '=T("'||interfazRecord(x).v_OID_MOVI_CC ||'")' ||','||
                    '=T("'||interfazRecord(x).v_IMP_DOCU ||'")' ||','||
                    '=T("'||interfazRecord(x).v_IMP_DEUD_CAST ||'")';

            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
      END LOOP;
    END IF;
    EXIT WHEN c_interfaz%NOTFOUND;
 END LOOP;
 CLOSE c_interfaz;
 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
 END IF;
 RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR CCC_PR_DETAL_PROV_INCOBR_MOV: '||ls_sqlerrm);
END CCC_PR_DETAL_PROV_INCOBR_MOV;

 PROCEDURE CCC_PR_GENER_RECAU_CAMPA(
  p_fec_desd                     IN   VARCHAR2,
  p_fec_hast                     IN   VARCHAR2)
 IS

 BEGIN

  DELETE FROM ccc_repor_recau_campa;

  INSERT INTO ccc_repor_recau_campa
   SELECT
    cad.cmba_oid_movi_banc,
    cad.mvcc_oid_movi_carg,
    cad.clie_oid_clie,
    spc.cod_peri,
    mb.fec_pago,
    TRUNC(cad.fec_proc),
    TRUNC(cad.fec_apli),
    cad.imp_abon
   FROM ccc_aplic_abono_cargo cad,
        ccc_movim_banca mb,
        ccc_movim_cuent_corri mcc,
        cra_perio cp,
        seg_perio_corpo spc
   WHERE cad.cmba_oid_movi_banc = mb.oid_movi_banc
     AND cad.mvcc_oid_movi_carg = mcc.oid_movi_cc
     AND mcc.perd_oid_peri = cp.oid_peri
     AND cp.peri_oid_peri = spc.oid_peri
     AND cad.cmba_oid_movi_banc IS NOT NULL
     AND cad.fec_proc >= TO_DATE(p_fec_desd,'DD/MM/YYYY')
     AND cad.fec_proc <= TO_DATE(p_fec_hast,'DD/MM/YYYY');

 EXCEPTION

  WHEN OTHERS THEN
   ln_sqlcode := SQLCODE;
   ls_sqlerrm := substr(sqlerrm,1,1000);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR CCC_PR_GENER_RECAU_CAMPA: '||ls_sqlerrm);

  END CCC_PR_GENER_RECAU_CAMPA;

 PROCEDURE CCC_PR_REPOR_ANTIG_NOTAS_CREDI(
  p_fec_desd                     IN   VARCHAR2,
  p_fec_hast                     IN   VARCHAR2)
 IS

 BEGIN

  DELETE FROM ccc_repor_detal_notas_credi;

  INSERT INTO ccc_repor_detal_notas_credi
   SELECT
    fac.tido_oid_tipo_docu,              -- oid_tipo_docu  number(12)
    mc.oid_clie,                         -- oid_clie
    mc.cod_clie,                         --cod_clie  varchar2(15)
    mc.val_nom1 || ' ' || mc.val_nom2 || ' ' || mc.val_ape1 || ' ' || mc.val_ape2 nom_clie, -- nom_clie  varchar2(103)
    NULL,                                -- num_docu_iden  varchar2(4000),
    NULL,                                -- oid_cabe_fact  number(12)
    psc.soca_oid_docu_refe,              -- oid_soli_cabe_fact  number(12)
    NULL,                                -- fec_fact  date
    NULL,                                -- num_docu_lega_fact  number(10)
    NULL,                                --imp_fact  number(12,2)
    fac.oid_cabe,                        -- oid_cabe_nocr  number(12)
    fac.soca_oid_soli_cabe,              -- oid_soli_cabe_nocr  number(12)
    fac.fec_fact,                        -- fec_nocr	date
    fac.num_docu_lega,                   -- num_docu_lega_nocr	number(10)
    fac.val_tota_paga_loca,              -- imp_nocr	number(12,2)
    NULL                                 -- val_num_dias	number
   FROM
    fac_docum_conta_cabec fac,
    ped_solic_cabec psc,
    mae_clien mc
   WHERE fac.soca_oid_soli_cabe = psc.oid_soli_cabe
     AND psc.clie_oid_clie = mc.oid_clie
     AND fac.fec_fact >= TO_DATE (p_fec_desd,'DD/MM/YYYY')
     AND fac.fec_fact <= TO_DATE (p_fec_hast,'DD/MM/YYYY')
     AND fac.tido_oid_tipo_docu in (31,32,33);

  UPDATE ccc_repor_detal_notas_credi nc
  SET
   nc.num_docu_iden = FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_DOCUM_IDENT(nc.oid_clie),
   nc.oid_cabe_fact = (
      SELECT f.oid_cabe
      FROM fac_docum_conta_cabec f
      WHERE f.soca_oid_soli_cabe = nc.oid_soli_cabe_fact),
   nc.fec_fact = (
      SELECT f.fec_fact
      FROM fac_docum_conta_cabec f
      WHERE f.soca_oid_soli_cabe = nc.oid_soli_cabe_fact),
   nc.imp_fact = (
      SELECT f.val_tota_paga_loca
      FROM fac_docum_conta_cabec f
      WHERE f.soca_oid_soli_cabe = nc.oid_soli_cabe_fact),
   nc.num_docu_lega_fact = (
      SELECT f.num_docu_lega
      FROM fac_docum_conta_cabec f
      WHERE f.soca_oid_soli_cabe = nc.oid_soli_cabe_fact);

  UPDATE ccc_repor_detal_notas_credi nc
  SET nc.val_num_dias = nc.fec_nocr - nc.fec_fact;

 END CCC_PR_REPOR_ANTIG_NOTAS_CREDI;

 PROCEDURE CCC_PR_DETAL_CUENT_CORRI_CONTA(
  p_fec_hast                     IN   VARCHAR2)
 IS

 BEGIN

  DELETE FROM ccc_repor_detal_cuent_conta;

  INSERT INTO ccc_repor_detal_cuent_conta
   WITH
    temp1 AS
     (SELECT
      oid_clie,
      SUM(imp_movi) sald_cont
     FROM
     ((SELECT
        mcc.clie_oid_clie oid_clie,
        SUM(mcc.imp_movi) imp_movi
       FROM ccc_movim_cuent_corri mcc
       WHERE mcc.fec_docu <= TO_DATE(p_fec_hast,'DD/MM/YYYY')
       GROUP BY mcc.clie_oid_clie)
     UNION ALL
      (SELECT mb.clie_oid_clie oid_clie, SUM(mb.imp_pago*-1) imp_movi
       FROM ccc_movim_banca mb
       WHERE mb.cod_iden_proc = 'P'
         AND mb.fec_proc <= TO_DATE(p_fec_hast,'DD/MM/YYYY')
      GROUP BY mb.clie_oid_clie))
     HAVING SUM(imp_movi) <> 0
      GROUP BY oid_clie),
    temp2 AS
     ( SELECT
        zr.cod_regi,
        zz.cod_zona,
        zs.cod_secc,
        t1.oid_clie,
        mc.cod_clie,
        mc.val_nom1 || ' ' || mc.val_nom2 || ' ' || mc.val_ape1 || ' ' || mc.val_ape2 nom_clie
       FROM
        mae_clien mc,
        mae_clien_unida_admin mcua,
        zon_terri_admin zta,
        zon_secci zs,
        zon_zona zz,
        zon_regio zr,
        temp1 t1
       WHERE mcua.clie_oid_clie = t1.oid_clie
         AND mcua.clie_oid_clie = mc.oid_clie
         AND mcua.ind_acti = 1
       AND mcua.ztad_oid_terr_admi = zta.oid_terr_admi
       AND zta.zscc_oid_secc = zs.oid_secc
       AND zs.zzon_oid_zona = zz.oid_zona
       AND zz.zorg_oid_regi = zr.oid_regi)
   SELECT
    t2.cod_regi,
    t2.cod_zona,
    t2.cod_secc,
    t1.oid_clie,
    t2.cod_clie,
    t2.nom_clie,
    t1.sald_cont,
    NULL,
    NULL,
    NULL,
    NULL,
    NULL
   FROM
    temp1 t1,
    temp2 t2
   WHERE t1.oid_clie = t2.oid_clie(+);

  UPDATE ccc_repor_detal_cuent_conta r
  SET r.oid_cabe =
                 (SELECT MAX(fac.oid_cabe)
                  FROM
                   fac_docum_conta_cabec fac,
                   ped_solic_cabec psc
                  WHERE fac.soca_oid_soli_cabe = psc.oid_soli_cabe
                    AND psc.clie_oid_clie = r.oid_clie
                    AND fac.tido_oid_tipo_docu IN (1,9)
                    AND fac.val_tota_paga_loca > 0
                    AND fac.fec_fact <= TO_DATE(p_fec_hast,'DD/MM/YYYY'));

  UPDATE ccc_repor_detal_cuent_conta r
  SET
   r.val_seri_docu_lega =
     (SELECT fac.val_seri_docu_lega
      FROM fac_docum_conta_cabec fac
      WHERE fac.oid_cabe = r.oid_cabe),
   r.val_nume_docu_lega =
     (SELECT fac.num_docu_lega
      FROM fac_docum_conta_cabec fac
      WHERE fac.oid_cabe = r.oid_cabe),
   r.fec_fact =
     (SELECT fac.fec_fact
      FROM fac_docum_conta_cabec fac
      WHERE fac.oid_cabe = r.oid_cabe),
   r.imp_fact =
     (SELECT fac.val_tota_paga_loca
      FROM fac_docum_conta_cabec fac
      WHERE fac.oid_cabe = r.oid_cabe);

 END CCC_PR_DETAL_CUENT_CORRI_CONTA;

 /*************************************************************************************
  Descripcion       : Procedimiento que carga el Reporte Registro Ventas Bolivia para formato CSV
  Fecha Creacion    : 02/03/2015
  Autor             : Aurelio Oviedo
  ************************************************************************************/
PROCEDURE CCC_PR_GEN_REPOR_REG_VENT_BOL(
    pscodigopais          VARCHAR2,
    psfechainicio         VARCHAR2,
    psfechafin            VARCHAR2,
    psnombrearchivo       VARCHAR2,
    pstitulo              VARCHAR2,
    psdirectorio          OUT VARCHAR2
)
IS
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         number := 5000;
    v_handle        utl_file.file_type;
    lslinea         varchar2(4000);

    CURSOR C_REPOR_REGIS_VENTA_BOLIV IS
        SELECT TO_CHAR(A.FEC_FACT, 'DD/MM/YYYY') FEC_FACT,
               A.NRO_DESP,
               A.VAL_ALFA,
               A.NRO_ORDE,
               A.NRO_FACT,
               A.VAL_NIT,
               A.COD_CLIE,
               A.NOM_CLIE,
               A.COD_CAMP,
               A.VAL_MONT,
               A.VAL_INTE,
               A.VAL_TIPO,
               A.VAL_DESC
          FROM CCC_REPOR_REGIS_VENTA_BOLIV A
         ORDER BY A.FEC_FACT, A.NRO_FACT;

    TYPE regisVentasBolivia IS RECORD(
      fecha_factura             VARCHAR2(10),
      nro_despacho              CCC_REPOR_REGIS_VENTA_BOLIV.NRO_DESP%TYPE,
      alfanumerico              CCC_REPOR_REGIS_VENTA_BOLIV.VAL_ALFA%TYPE,
      nro_orden                 CCC_REPOR_REGIS_VENTA_BOLIV.NRO_ORDE%TYPE,
      nro_factura               CCC_REPOR_REGIS_VENTA_BOLIV.NRO_FACT%TYPE,
      valor_nit                 CCC_REPOR_REGIS_VENTA_BOLIV.VAL_NIT%TYPE,
      cod_cliente               CCC_REPOR_REGIS_VENTA_BOLIV.COD_CLIE%TYPE,
      nom_cliente               CCC_REPOR_REGIS_VENTA_BOLIV.NOM_CLIE%TYPE,
      cod_campana               CCC_REPOR_REGIS_VENTA_BOLIV.COD_CAMP%TYPE,
      monto                     CCC_REPOR_REGIS_VENTA_BOLIV.VAL_MONT%TYPE,
      intereses                 CCC_REPOR_REGIS_VENTA_BOLIV.VAL_INTE%TYPE,
      tipo                      CCC_REPOR_REGIS_VENTA_BOLIV.VAL_TIPO%TYPE,
      descuento                 CCC_REPOR_REGIS_VENTA_BOLIV.VAL_DESC%TYPE
    );

    TYPE regisVentasBoliviaTab IS TABLE OF regisVentasBolivia;
    regisVentasBoliviaRecord regisVentasBoliviaTab;

    lbAbrirUtlFile  BOOLEAN;

BEGIN

    lbAbrirUtlFile := true;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';

    DELETE FROM CCC_REPOR_REGIS_VENTA_BOLIV;

    INSERT INTO CCC_REPOR_REGIS_VENTA_BOLIV
    SELECT A.FEC_FACT,
       B.VAL_NUME_SOLI BOLETA_DESPACHO,
       A.VAL_NUME_CONT ALFANUMERICO,
       A.VAL_NUME_AUTO ORDEN,
       A.NUM_DOCU_CONT_INTE FACTURA,
       TRIM(TRANSLATE(NVL(A.VAL_NUME_IDEN_FISC, A.VAL_NUME_IDEN_NNAL),
                      'abcdefghijklmnopqrstuvwxyzñABCDEFGHIJKLMNOPQRSTUVWXYZÑ',
                      ' ')) NIT,
       C.COD_CLIE,
       A.VAL_NOM1 || ' ' || A.VAL_NOM2 || ' ' || A.VAL_APE1 || ' ' ||
       A.VAL_APE2 NOMBRE,
       E.COD_PERI CAMPANA,
       --A.VAL_TOTA_PAGA_LOCA  TOTAL,
       case when exists (        select null
      from fac_docum_conta_cabec x,
           ped_solic_cabec       y,
           fac_regis_unico_venta z,
           ped_tipo_solic_pais   z1,
           ped_tipo_solic        z2
     where trunc(x.fec_fact) >= to_date(psfechainicio,'dd/mm/yyyy')
       and trunc(x.fec_fact) <= to_date(psfechafin,'dd/mm/yyyy')
       and trunc(z.fec_emis_refe) >= to_date(psfechainicio,'dd/mm/yyyy')
       and trunc(z.fec_emis_refe)<= to_date(psfechafin,'dd/mm/yyyy')
       and x.tido_oid_tipo_docu = 32  --Nota Crédito Fact 1
       and x.oid_cabe = z.dcca_oid_cabe
       and x.soca_oid_soli_cabe = y.oid_soli_cabe
       and y.tspa_oid_tipo_soli_pais = z1.oid_tipo_soli_pais
       and z1.tsol_oid_tipo_soli = z2.oid_tipo_soli
       and z2.ind_anul = 1
       and x.pais_oid_pais = gen_pkg_gener.gen_fn_devuelve_id_pais('BOE')
         and y.soca_oid_docu_refe = b.oid_soli_cabe
       )
                    then 0
                    else a.val_tota_paga_loca
                    end TOTAL,
       NVL(A.VAL_TOTA_GAST_ADMI, 0) INTERESES,
       'SISTEMA COMERCIAL' TIPO,
       a.imp_desc_tota_loca DESCUENTO
       FROM FAC_DOCUM_CONTA_CABEC A,
             PED_SOLIC_CABEC       B,
             MAE_CLIEN             C,
             CRA_PERIO             D,
             SEG_PERIO_CORPO       E
       WHERE A.FEC_FACT >= TO_DATE(psfechainicio, 'DD/MM/YYYY')
         AND A.FEC_FACT <= TO_DATE(psfechafin, 'DD/MM/YYYY')
         AND A.TIDO_OID_TIPO_DOCU = 1
         AND A.SOCA_OID_SOLI_CABE = B.OID_SOLI_CABE
         AND B.CLIE_OID_CLIE = C.OID_CLIE
         AND A.PERD_OID_PERI = D.OID_PERI
         AND D.PERI_OID_PERI = E.OID_PERI
       ORDER BY A.FEC_FACT, A.NUM_DOCU_CONT_INTE;

    OPEN C_REPOR_REGIS_VENTA_BOLIV;
        LOOP
            FETCH C_REPOR_REGIS_VENTA_BOLIV BULK COLLECT INTO regisVentasBoliviaRecord LIMIT W_FILAS;
            IF lbAbrirUtlFile THEN
                GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
                psdirectorio := lsdirtempo;
                lbAbrirUtlFile := FALSE;
            END IF ;

            IF regisVentasBoliviaRecord.COUNT > 0 THEN
                FOR x IN regisVentasBoliviaRecord.FIRST .. regisVentasBoliviaRecord.LAST LOOP
                    lslinea :=
                                 '"'|| regisVentasBoliviaRecord(x).fecha_factura || '"' || ',' ||
                                 '"'|| regisVentasBoliviaRecord(x).nro_despacho || '"' || ',' ||
                                 '"'|| regisVentasBoliviaRecord(x).alfanumerico || '"' || ',' ||
                                 '=T("'||regisVentasBoliviaRecord(x).nro_orden||'")' ||','||
                                 '"'|| regisVentasBoliviaRecord(x).nro_factura || '"' || ',' ||
                                 '=T("'||regisVentasBoliviaRecord(x).valor_nit||'")' ||','||
                                 '=T("'||regisVentasBoliviaRecord(x).cod_cliente||'")' ||','||
                                 '"'|| regisVentasBoliviaRecord(x).nom_cliente || '"' || ',' ||
                                 '"'|| regisVentasBoliviaRecord(x).monto || '"' || ',' ||
                                 '"'|| regisVentasBoliviaRecord(x).intereses || '"' || ',' ||
                                 '"'|| regisVentasBoliviaRecord(x).cod_campana || '"' || ',' ||
                                 '"'|| regisVentasBoliviaRecord(x).tipo || '"' || ',' ||
                                 '"'|| regisVentasBoliviaRecord(x).descuento || '"' ;

                    UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
                END LOOP;
            END IF;
            EXIT WHEN C_REPOR_REGIS_VENTA_BOLIV%NOTFOUND;
        END LOOP;
     CLOSE C_REPOR_REGIS_VENTA_BOLIV;

    IF NOT lbAbrirUtlFile THEN
        utl_file.fclose(V_HANDLE);
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm,1,250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR CCC_PR_GEN_REPOR_REG_VENT_BOL: '||ls_sqlerrm);

END CCC_PR_GEN_REPOR_REG_VENT_BOL ;

 /*************************************************************************************
  Descripcion       : Procedimiento que carga el Reporte Registro Abonos Bolivia para formato CSV
  Fecha Creacion    : 03/03/2015
  Autor             : Aurelio Oviedo
  ************************************************************************************/
PROCEDURE CCC_PR_GEN_REPOR_REG_ABON_BOL(
    pscodigopais          VARCHAR2,
    psfechainicio         VARCHAR2,
    psfechafin            VARCHAR2,
    psnombrearchivo       VARCHAR2,
    pstitulo              VARCHAR2,
    psdirectorio          OUT VARCHAR2
)
IS
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         number := 5000;
    v_handle        utl_file.file_type;
    lslinea         varchar2(4000);

    CURSOR C_REPOR_REGIS_ABONO_BOLIV IS
        SELECT TO_CHAR(A.FEC_FACT, 'DD/MM/YYYY') FEC_FACT,
               A.NRO_RUC,
               A.NRO_CI,
               A.NOM_PROV,
               A.VAL_ALFA,
               A.NRO_NC,
               A.NRO_ORDE,
               A.VAL_TOTA_PROD,
               A.VAL_FLET,
               A.VAL_INTE,
               A.VAL_TOTA_NC,
               TO_CHAR(A.VAL_FEC_FACTU, 'DD/MM/YYYY') VAL_FEC_FACTU,
               A.VAL_MNT_FACTU,
               A.VAL_AUT_FACTU,
               A.VAL_NUM_FACTU,
               A.VAL_CTRL_FACTU,
               A.VAL_NIT_FACTU,
               A.VAL_DESC
          FROM CCC_REPOR_REGIS_ABONO_BOLIV A
         ORDER BY A.FEC_FACT, A.NRO_NC;

    TYPE regisAbonosBolivia IS RECORD(
      fecha_factura             VARCHAR2(10),
      nro_ruc                   CCC_REPOR_REGIS_ABONO_BOLIV.NRO_RUC%TYPE,
      nro_ci                    CCC_REPOR_REGIS_ABONO_BOLIV.NRO_CI%TYPE,
      nom_proveedor             CCC_REPOR_REGIS_ABONO_BOLIV.NOM_PROV%TYPE,
      alfanumerico              CCC_REPOR_REGIS_ABONO_BOLIV.VAL_ALFA%TYPE,
      nro_nc                    CCC_REPOR_REGIS_ABONO_BOLIV.NRO_NC%TYPE,
      nro_orden                 CCC_REPOR_REGIS_ABONO_BOLIV.NRO_ORDE%TYPE,
      val_total_prod            CCC_REPOR_REGIS_ABONO_BOLIV.VAL_TOTA_PROD%TYPE,
      flete                     CCC_REPOR_REGIS_ABONO_BOLIV.VAL_FLET%TYPE,
      intereses                 CCC_REPOR_REGIS_ABONO_BOLIV.VAL_INTE%TYPE,
      val_total_nc              CCC_REPOR_REGIS_ABONO_BOLIV.VAL_TOTA_NC%TYPE,
      val_fec_factu             VARCHAR2(10),
      val_mnt_factu             CCC_REPOR_REGIS_ABONO_BOLIV.VAL_MNT_FACTU%TYPE,
      val_aut_factu             CCC_REPOR_REGIS_ABONO_BOLIV.VAL_AUT_FACTU%TYPE,
      val_num_factu             CCC_REPOR_REGIS_ABONO_BOLIV.VAL_NUM_FACTU%TYPE,
      val_ctrl_factu            CCC_REPOR_REGIS_ABONO_BOLIV.VAL_CTRL_FACTU%TYPE,
      val_nit_factu             CCC_REPOR_REGIS_ABONO_BOLIV.VAL_NIT_FACTU%TYPE,
      descuento                 CCC_REPOR_REGIS_ABONO_BOLIV.VAL_DESC%TYPE
      );

    TYPE regisAbonosBoliviaTab IS TABLE OF regisAbonosBolivia;
    regisAbonosBoliviaRecord regisAbonosBoliviaTab;

    lbAbrirUtlFile  BOOLEAN;

BEGIN

    lbAbrirUtlFile := true;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';

    DELETE FROM CCC_REPOR_REGIS_ABONO_BOLIV;

    INSERT INTO CCC_REPOR_REGIS_ABONO_BOLIV
    SELECT A.FEC_FACT,
       /*TRIM(TRANSLATE(A.VAL_NUME_IDEN_FISC,
                      'abcdefghijklmnopqrstuvwxyzñABCDEFGHIJKLMNOPQRSTUVWXYZÑ',
                      ' ')) RUC,
       TRIM(TRANSLATE(A.VAL_NUME_IDEN_NNAL,
                      'abcdefghijklmnopqrstuvwxyzñABCDEFGHIJKLMNOPQRSTUVWXYZÑ',
                      ' ')) CI,*/
      CASE
          WHEN EXISTS (SELECT TDOC_OID_TIPO_DOCU FROM MAE_CLIEN_IDENT WHERE CLIE_OID_CLIE=C.OID_CLIE
               AND VAL_IDEN_DOCU_PRIN = 1
               AND TDOC_OID_TIPO_DOCU = (SELECT OID_TIPO_DOCU FROM MAE_TIPO_DOCUM WHERE COD_TIPO_DOCU='01')  ) THEN
                  (SELECT VAL_IDEN_FISC FROM SEG_SOCIE)
          ELSE
               TRIM(TRANSLATE(A.VAL_NUME_IDEN_FISC,
                          'abcdefghijklmnopqrstuvwxyzñABCDEFGHIJKLMNOPQRSTUVWXYZÑ',
                          ' '))
        END RUC,
        CASE
          WHEN EXISTS (SELECT TDOC_OID_TIPO_DOCU FROM MAE_CLIEN_IDENT WHERE CLIE_OID_CLIE=C.OID_CLIE
               AND VAL_IDEN_DOCU_PRIN = 1
               AND TDOC_OID_TIPO_DOCU = (SELECT OID_TIPO_DOCU FROM MAE_TIPO_DOCUM WHERE COD_TIPO_DOCU='01')  ) THEN
                   A.VAL_NUME_IDEN_NNAL
          ELSE
              TRIM(TRANSLATE(A.VAL_NUME_IDEN_NNAL,
                          'abcdefghijklmnopqrstuvwxyzñABCDEFGHIJKLMNOPQRSTUVWXYZÑ',
                          ' '))
        END CI,
       A.VAL_NOM1 || ' ' || A.VAL_NOM2 || ' ' || A.VAL_APE1 || ' ' ||
       A.VAL_APE2 PROVEEDOR,
       A.VAL_NUME_CONT ALFANUMERICO,
       A.NUM_DOCU_CONT_INTE NC,
       A.VAL_NUME_AUTO ORDEN,
       ABS(A.VAL_TOTA_PAGA_LOCA - NVL(A.VAL_TOTA_GAST_ADMI, 0) -
           NVL(A.IMP_FLET_TOTA_LOCA, 0)) TOTAL_PROD,
       ABS(A.IMP_FLET_TOTA_LOCA) FLETE,
       ABS(NVL(A.VAL_TOTA_GAST_ADMI, 0)) INTERESES,
       ABS(A.VAL_TOTA_PAGA_LOCA) TOTAL_NC,
       trunc(f.fec_emis_refe) FECHA_FACTURA,
       f.val_mont_refe MONTO_FACTURA,
       f.VAL_NUME_AUTO_REFE AUTORIZACION_FACTURA,
       f.val_nume_docu_refe NUMERO_FACTURA,
       f.VAL_NUME_CONT_REFE CONTROL_FACTURA,
       trim(TRANSLATE(f.NUM_NIT_REFE,
                      'abcdefghijklmnopqrstuvwxyzñABCDEFGHIJKLMNOPQRSTUVWXYZÑ',
                      ' ')) NIT_FACTURA,
       f.val_desc  DESCUENTO
    FROM FAC_DOCUM_CONTA_CABEC A,
         PED_SOLIC_CABEC       B,
         MAE_CLIEN             C,
         CRA_PERIO             D,
         SEG_PERIO_CORPO       E,
         fac_regis_unico_venta f,
          ped_tipo_solic_pais  o,
          ped_tipo_solic p
   WHERE A.Num_Unid_Aten_Tota<>0
     and b.tspa_oid_tipo_soli_pais=o.oid_tipo_soli_pais
     and o.tsol_oid_tipo_soli=p.oid_tipo_soli
     and p.ind_anul=1
     and to_char(a.fec_fact,'YYYYMM')>to_char(f.fec_emis_refe,'YYYYMM')
     AND A.FEC_FACT >= TO_DATE(psfechainicio, 'DD/MM/YYYY')
     AND A.FEC_FACT <= TO_DATE(psfechafin, 'DD/MM/YYYY')
          --AND a.OID_CABE > (SELECT icdc.val_ulti_oid_cabe
          --            FROM IMP_CONTR_DOCUM_CONTA icdc
          --            WHERE icdc.Cod_Tipo_Docu = 'NC')
     AND A.TIDO_OID_TIPO_DOCU = 32
     AND A.SOCA_OID_SOLI_CABE = B.OID_SOLI_CABE
     AND B.CLIE_OID_CLIE = C.OID_CLIE
     AND A.PERD_OID_PERI = D.OID_PERI
     AND D.PERI_OID_PERI = E.OID_PERI
     and a.oid_cabe = f.dcca_oid_cabe
     and a.num_docu_cont_inte is not null
   UNION ALL
          SELECT A.FEC_FACT,
       /*TRIM(TRANSLATE(A.VAL_NUME_IDEN_FISC,
                      'abcdefghijklmnopqrstuvwxyzñABCDEFGHIJKLMNOPQRSTUVWXYZÑ',
                      ' ')) RUC,
       TRIM(TRANSLATE(A.VAL_NUME_IDEN_NNAL,
                      'abcdefghijklmnopqrstuvwxyzñABCDEFGHIJKLMNOPQRSTUVWXYZÑ',
                      ' ')) CI,*/
      CASE
          WHEN EXISTS (SELECT TDOC_OID_TIPO_DOCU FROM MAE_CLIEN_IDENT WHERE CLIE_OID_CLIE=C.OID_CLIE
               AND VAL_IDEN_DOCU_PRIN = 1
               AND TDOC_OID_TIPO_DOCU = (SELECT OID_TIPO_DOCU FROM MAE_TIPO_DOCUM WHERE COD_TIPO_DOCU='01')  ) THEN
                  (SELECT VAL_IDEN_FISC FROM SEG_SOCIE)
          ELSE
               TRIM(TRANSLATE(A.VAL_NUME_IDEN_FISC,
                          'abcdefghijklmnopqrstuvwxyzñABCDEFGHIJKLMNOPQRSTUVWXYZÑ',
                          ' '))
        END RUC,
        CASE
          WHEN EXISTS (SELECT TDOC_OID_TIPO_DOCU FROM MAE_CLIEN_IDENT WHERE CLIE_OID_CLIE=C.OID_CLIE
               AND VAL_IDEN_DOCU_PRIN = 1
               AND TDOC_OID_TIPO_DOCU = (SELECT OID_TIPO_DOCU FROM MAE_TIPO_DOCUM WHERE COD_TIPO_DOCU='01')  ) THEN
                   A.VAL_NUME_IDEN_NNAL
          ELSE
              TRIM(TRANSLATE(A.VAL_NUME_IDEN_NNAL,
                          'abcdefghijklmnopqrstuvwxyzñABCDEFGHIJKLMNOPQRSTUVWXYZÑ',
                          ' '))
        END CI,
       A.VAL_NOM1 || ' ' || A.VAL_NOM2 || ' ' || A.VAL_APE1 || ' ' ||
       A.VAL_APE2 PROVEEDOR,
       A.VAL_NUME_CONT ALFANUMERICO,
       A.NUM_DOCU_CONT_INTE NC,
       A.VAL_NUME_AUTO ORDEN,
       ABS(A.VAL_TOTA_PAGA_LOCA - NVL(A.VAL_TOTA_GAST_ADMI, 0) -
           NVL(A.IMP_FLET_TOTA_LOCA, 0)) TOTAL_PROD,
       ABS(A.IMP_FLET_TOTA_LOCA) FLETE,
       ABS(NVL(A.VAL_TOTA_GAST_ADMI, 0)) INTERESES,
       ABS(A.VAL_TOTA_PAGA_LOCA) TOTAL_NC,
       trunc(f.fec_emis_refe) FECHA_FACTURA,
       f.val_mont_refe MONTO_FACTURA,
       f.VAL_NUME_AUTO_REFE AUTORIZACION_FACTURA,
       f.val_nume_docu_refe NUMERO_FACTURA,
       f.VAL_NUME_CONT_REFE CONTROL_FACTURA,
       trim(TRANSLATE(f.NUM_NIT_REFE,
                      'abcdefghijklmnopqrstuvwxyzñABCDEFGHIJKLMNOPQRSTUVWXYZÑ',
                      ' ')) NIT_FACTURA,
       f.val_desc  DESCUENTO
    FROM FAC_DOCUM_CONTA_CABEC A,
         PED_SOLIC_CABEC       B,
         MAE_CLIEN             C,
         CRA_PERIO             D,
         SEG_PERIO_CORPO       E,
         fac_regis_unico_venta f,
          ped_tipo_solic_pais  o,
          ped_tipo_solic       p,
          mae_tipo_docum       q
   WHERE A.Num_Unid_Aten_Tota<>0
     and b.tdoc_oid_tipo_docu=q.oid_tipo_docu
     and q.ind_doc_iden_fisc='1'
     and b.tspa_oid_tipo_soli_pais=o.oid_tipo_soli_pais
     and o.tsol_oid_tipo_soli=p.oid_tipo_soli
     and p.ind_anul=0
     and to_char(a.fec_fact,'YYYYMM')>to_char(f.fec_emis_refe,'YYYYMM')
     AND A.FEC_FACT >= TO_DATE(psfechainicio, 'DD/MM/YYYY')
     AND A.FEC_FACT <= TO_DATE(psfechafin, 'DD/MM/YYYY')
          --AND a.OID_CABE > (SELECT icdc.val_ulti_oid_cabe
          --            FROM IMP_CONTR_DOCUM_CONTA icdc
          --            WHERE icdc.Cod_Tipo_Docu = 'NC')
     AND A.TIDO_OID_TIPO_DOCU = 32
     AND A.SOCA_OID_SOLI_CABE = B.OID_SOLI_CABE
     AND B.CLIE_OID_CLIE = C.OID_CLIE
     AND A.PERD_OID_PERI = D.OID_PERI
     AND D.PERI_OID_PERI = E.OID_PERI
     and a.oid_cabe = f.dcca_oid_cabe
      and a.num_docu_cont_inte is not null
   ORDER BY A.FEC_FACT, A.NUM_DOCU_CONT_INTE;

    OPEN C_REPOR_REGIS_ABONO_BOLIV;
        LOOP
            FETCH C_REPOR_REGIS_ABONO_BOLIV BULK COLLECT INTO regisAbonosBoliviaRecord LIMIT W_FILAS;
            IF lbAbrirUtlFile THEN
                GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
                psdirectorio := lsdirtempo;
                lbAbrirUtlFile := FALSE;
            END IF ;

            IF regisAbonosBoliviaRecord.COUNT > 0 THEN
                FOR x IN regisAbonosBoliviaRecord.FIRST .. regisAbonosBoliviaRecord.LAST LOOP
                    lslinea :=
                                 '"'|| regisAbonosBoliviaRecord(x).fecha_factura || '"' || ',' ||
                                 '=T("'||regisAbonosBoliviaRecord(x).nro_ruc||'")' ||','||
                                 '=T("'||regisAbonosBoliviaRecord(x).nro_ci||'")' ||','||
                                 '"'|| regisAbonosBoliviaRecord(x).nom_proveedor || '"' || ',' ||
                                 '"'|| regisAbonosBoliviaRecord(x).alfanumerico || '"' || ',' ||
                                 '"'|| regisAbonosBoliviaRecord(x).nro_nc || '"' || ',' ||
                                 '=T("'||regisAbonosBoliviaRecord(x).nro_orden||'")' ||','||
                                 '"'|| regisAbonosBoliviaRecord(x).val_total_prod || '"' || ',' ||
                                 '"'|| regisAbonosBoliviaRecord(x).flete || '"' || ',' ||
                                 '"'|| regisAbonosBoliviaRecord(x).intereses || '"' || ',' ||
                                 '"'|| regisAbonosBoliviaRecord(x).val_total_nc || '"' || ',' ||
                                 '"'|| regisAbonosBoliviaRecord(x).val_fec_factu || '"' || ',' ||
                                 '"'|| regisAbonosBoliviaRecord(x).val_mnt_factu || '"' || ',' ||
                                 '"'|| regisAbonosBoliviaRecord(x).val_aut_factu || '"' || ',' ||
                                 '"'|| regisAbonosBoliviaRecord(x).val_num_factu || '"' || ',' ||
                                 '"'|| regisAbonosBoliviaRecord(x).val_ctrl_factu || '"' || ',' ||
                                 '"'|| regisAbonosBoliviaRecord(x).val_nit_factu || '"' || ',' ||
                                 '"'|| regisAbonosBoliviaRecord(x).descuento || '"' ;

                    UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
                END LOOP;
            END IF;
            EXIT WHEN C_REPOR_REGIS_ABONO_BOLIV%NOTFOUND;
        END LOOP;
     CLOSE C_REPOR_REGIS_ABONO_BOLIV;

    IF NOT lbAbrirUtlFile THEN
        utl_file.fclose(V_HANDLE);
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm,1,250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR CCC_PR_GEN_REPOR_REG_ABON_BOL: '||ls_sqlerrm);

END CCC_PR_GEN_REPOR_REG_ABON_BOL ;

 /*************************************************************************************
  Descripcion       : Procedimiento que carga el Reporte Buro Credito en formato CSV
  Fecha Creacion    : 12/03/2015
  Autor             : Diego Torres Loyola
  ************************************************************************************/

  PROCEDURE CCC_PR_BURO_CREDITO(
    psTitulo                       VARCHAR2,
    psCodigoPais                     VARCHAR2,
    psNombreArchivo                  VARCHAR2,
    psDirectorio                   OUT  VARCHAR2
    )
IS

  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 5000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(4000);

  CURSOR c_interfaz IS
    SELECT a.ind_tipo_docu                     TIP_DOCUMENTO,
       a.num_docu_iden                     NUM_DOCUMENTO,
       a.val_nomb                          VAL_NOMBRE,
       a.val_dire                          VAL_DIRECCION,
       a.val_dist                          VAL_CIUDAD,
       a.val_tele                          VAL_TELEFONO,
       a.val_acre                          VAL_ACREEDOR,
       trunc(SYSDATE)                      FEC_CORTE,
       'TITULAR'                           TIP_RIESGO,
       a.num_iden_cuot                     NUM_OPERACION,
       trunc(a.fec_docu)                   FEC_CONSECION,
       a.imp_movi                          VAL_ORIGINAL,
       a.imp_pend                          VAL_TOTAL,
       CASE WHEN
         val_edad_deuda <= 0 THEN imp_pend
         ELSE 0
       END              VAL_XVENCER,
       CASE WHEN
         val_edad_deuda > 0 THEN imp_pend
         ELSE 0
       END                                 VAL_VENCIDO,
       '0'                                 DEM_JUDICIAL,
       '0'                                 CAR_CASTIGADA,
       val_edad_deuda                      VAL_DIAS
  FROM ccc_buro_credi_detal_deuda a;

TYPE interfazTipo IS RECORD (

  v_TIP_DOCUMENTO           ccc_buro_credi_detal_deuda.ind_tipo_docu%TYPE,
  v_NUM_DOCUMENTO           ccc_buro_credi_detal_deuda.num_docu_iden%TYPE,
  v_VAL_NOMBRE           ccc_buro_credi_detal_deuda.val_nomb%TYPE,
  v_VAL_DIRECCION           ccc_buro_credi_detal_deuda.val_dire%TYPE,
  v_VAL_CIUDAD           ccc_buro_credi_detal_deuda.val_dist%TYPE,
  v_VAL_TELEFONO      ccc_buro_credi_detal_deuda.val_tele%TYPE,
  v_VAL_ACREEDOR      ccc_buro_credi_detal_deuda.val_acre%TYPE,
  v_FEC_CORTE          date,
  v_TIP_RIESGO      varchar2(7),
  v_NUM_OPERACION      ccc_buro_credi_detal_deuda.num_iden_cuot%TYPE,
  v_FEC_CONSECION     date,
  v_VAL_ORIGINAL     ccc_buro_credi_detal_deuda.imp_movi%TYPE,
  v_VAL_TOTAL     ccc_buro_credi_detal_deuda.imp_pend%TYPE,
  v_VAL_XVENCER     ccc_buro_credi_detal_deuda.imp_pend%TYPE,
  v_VAL_VENCIDO    ccc_buro_credi_detal_deuda.imp_pend%TYPE,
  v_DEM_JUDICIAL      varchar2(1),
  v_CAR_CASTIGADA      varchar2(1),
  v_VAL_DIAS      ccc_buro_credi_detal_deuda.val_edad_deuda%TYPE

);

   TYPE interfazTab  IS TABLE OF interfazTipo;
   interfazRecord interfazTab;
   lbAbrirUtlFile  BOOLEAN;

BEGIN


  lbAbrirUtlFile := TRUE;

  OPEN c_interfaz;
  LOOP
   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
   IF lbAbrirUtlFile THEN
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.csv', psTitulo, lsDirTempo, V_HANDLE);
      psDirectorio   := lsdirtempo;
      lbAbrirUtlFile := FALSE;

   END IF ;
   IF interfazRecord.COUNT > 0 THEN
      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP

          lsLinea :='=T("'||trim(interfazRecord(x).v_TIP_DOCUMENTO)||'")' ||','||
                    '=T("'||trim(interfazRecord(x).v_NUM_DOCUMENTO)||'")' ||','||
                    '=T("'||trim(interfazRecord(x).v_VAL_NOMBRE) ||'")' ||','||
                    '=T("'||trim(interfazRecord(x).v_VAL_DIRECCION)||'")' ||','||
                    '=T("'||trim(interfazRecord(x).v_VAL_CIUDAD)||'")' ||','||
                    '=T("'||trim(interfazRecord(x).v_VAL_TELEFONO)||'")' ||','||
                    '=T("'||trim(interfazRecord(x).v_VAL_ACREEDOR)||'")' ||','||
                    '=T("'||trim(interfazRecord(x).v_FEC_CORTE)||'")' ||','||
                    '=T("'||trim(interfazRecord(x).v_TIP_RIESGO)||'")' ||','||
                    '=T("'||trim(interfazRecord(x).v_NUM_OPERACION)||'")' ||','||
                    '=T("'||trim(interfazRecord(x).v_FEC_CONSECION)||'")' ||','||

                    '=T("'||trim(to_char(interfazRecord(x).v_VAL_ORIGINAL , '999999999990.99')) ||'")' ||','||
                     '=T("'||trim(to_char(interfazRecord(x).v_VAL_TOTAL, '999999999990.99')) ||'")' ||','||
                      '=T("'||trim(to_char(interfazRecord(x).v_VAL_XVENCER , '999999999990.99')) ||'")' ||','||
                       '=T("'||trim(to_char(interfazRecord(x).v_VAL_VENCIDO, '999999999990.99')) ||'")' ||','||

                    '=T("'||trim(interfazRecord(x).v_DEM_JUDICIAL)||'")' ||','||
                    '=T("'||trim(interfazRecord(x).v_CAR_CASTIGADA)||'")' ||','||
                    '=T("'||trim(interfazRecord(x).v_VAL_DIAS) ||'")';

            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
      END LOOP;
    END IF;
    EXIT WHEN c_interfaz%NOTFOUND;
 END LOOP;
 CLOSE c_interfaz;
 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
 END IF;
 RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR CCC_PR_BURO_CREDITO: '||ls_sqlerrm);
END CCC_PR_BURO_CREDITO;


/*************************************************************************************
  Descripcion       : Procedimiento que carga lso tres tipos de Reporte Interes CSV 
  Fecha Creacion    : 02/12/2015
  Autor             : Karina Valencia
  ************************************************************************************/

  PROCEDURE CCC_PR_INTER_FACTU_TOTAL(
    psTitulo                       VARCHAR2,
    psCodigoPais                   VARCHAR2,
    psNombreArchivo                VARCHAR2,
    psTipoReporte                  VARCHAR2,   
    psDirectorio                   OUT  VARCHAR2
    )
IS

  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 5000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(4000);
  lbAbrirUtlFile      BOOLEAN;

--Reporte Interes Pedido

CURSOR c_reporte_interes IS
    SELECT * FROM CCC_TEMPO_INTER_TOTAL;

	TYPE pedidointerestab IS TABLE OF CCC_TEMPO_INTER_TOTAL%ROWTYPE;
	pedidoRecord pedidointerestab; 
   

BEGIN
  lbAbrirUtlFile := TRUE;

 --Reporte Interes-Pedido
IF psTipoReporte = 'CIP' THEN 
    OPEN c_reporte_interes;
    LOOP
     FETCH c_reporte_interes BULK COLLECT INTO pedidoRecord LIMIT W_FILAS;
     IF lbAbrirUtlFile THEN
        GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.csv', psTitulo, lsDirTempo, V_HANDLE);
        psDirectorio   := lsdirtempo;
        lbAbrirUtlFile := FALSE;
     END IF ;
     
     IF pedidoRecord.COUNT > 0 THEN
        FOR x IN pedidoRecord.FIRST .. pedidoRecord.LAST LOOP

            lsLinea :='=T("'||pedidoRecord(x).DES_REGI ||'")' ||','||
                      '=T("'||pedidoRecord(x).DES_ZONA ||'")' ||','||
                      '=T("'||pedidoRecord(x).COD_CLIE ||'")' ||','||
                      '=T("'||pedidoRecord(x).NOMBRE ||'")' ||','||
                      '=T("'||trim(to_char(pedidoRecord(x).INTE_VARI, '999999999990.99')) ||'")' ||','||                      
                      '=T("'||trim(to_char(pedidoRecord(x).NUM_HIST, '999999999990.99')) ||'")' ||','||                    
                      '=T("'||pedidoRecord(x).FEC_VENC ||'")' ||','||
                      '=T("'||pedidoRecord(x).FEC_MOVI ||'")' ||','||
                      '=T("'||trim(to_char(pedidoRecord(x).DEUDA_PEND, '999999999990.99')) ||'")' ||','||
                      '=T("'||trim(to_char(pedidoRecord(x).IMP_PAGO, '999999999990.99')) ||'")' ||','||
                      '=T("'||trim(to_char(pedidoRecord(x).INTER_VAR, '999999999990.99')) ||'")' ||','||                    
                      '"'||pedidoRecord(x).DIAS_DIFE ||'"' ||','||                   
                      '=T("'||trim(to_char(pedidoRecord(x).TASA_INTE, '999999999990.99')) ||'")' ||','||
                      '=T("'||pedidoRecord(x).FEC_GENE ||'")';
                      
              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
        END LOOP;
      END IF;
      EXIT WHEN c_reporte_interes%NOTFOUND;
   END LOOP;
   CLOSE c_reporte_interes;
 END IF;
 
 --Reporte Interes-Bloqueo
 IF psTipoReporte = 'CIB' THEN
   OPEN c_reporte_interes;
    LOOP
     FETCH c_reporte_interes BULK COLLECT INTO pedidoRecord LIMIT W_FILAS;
     IF lbAbrirUtlFile THEN
        GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.csv', psTitulo, lsDirTempo, V_HANDLE);
        psDirectorio   := lsdirtempo;
        lbAbrirUtlFile := FALSE;
     END IF ;
     
     IF pedidoRecord.COUNT > 0 THEN
        FOR x IN pedidoRecord.FIRST .. pedidoRecord.LAST LOOP

            lsLinea :='=T("'||pedidoRecord(x).DES_REGI ||'")' ||','||
                      '=T("'||pedidoRecord(x).DES_ZONA ||'")' ||','||
                      '=T("'||pedidoRecord(x).COD_CLIE ||'")' ||','||
                      '=T("'||pedidoRecord(x).NOMBRE ||'")' ||','||
                      '=T("'||trim(to_char(pedidoRecord(x).GASTO_ADMIN, '999999999990.99')) ||'")' ||','||
                      '=T("'||trim(to_char(pedidoRecord(x).INTE_CALCU, '999999999990.99')) ||'")' ||','||
                      '=T("'||trim(to_char(pedidoRecord(x).INTE_TOTAL, '999999999990.99')) ||'")' ||','||
                      '=T("'||trim(to_char(pedidoRecord(x).NUM_HIST, '999999999990.99')) ||'")' ||','||                    
                      '=T("'||pedidoRecord(x).FEC_VENC ||'")' ||','||
                      '=T("'||pedidoRecord(x).FEC_MOVI ||'")' ||','||
                      '=T("'||trim(to_char(pedidoRecord(x).IMP_PEND, '999999999990.99')) ||'")' ||','||
                      '=T("'||trim(to_char(pedidoRecord(x).IMP_PAGO, '999999999990.99')) ||'")' ||','|| 
                      '=T("'||trim(to_char(pedidoRecord(x).INTE_VARI, '999999999990.99')) ||'")' ||','||                                   
                      '"'||pedidoRecord(x).DIAS_DIFE ||'"' ||','||                   
                      '=T("'||trim(to_char(pedidoRecord(x).TASA_INTE, '999999999990.99')) ||'")' ||','||
                      '=T("'||pedidoRecord(x).FEC_GENE ||'")';
                      
              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
        END LOOP;
      END IF;
      EXIT WHEN c_reporte_interes%NOTFOUND;
   END LOOP;
   CLOSE c_reporte_interes;   
   END IF;
   
   --Reporte Interes-Facturado
IF psTipoReporte = 'CIF' THEN
  OPEN c_reporte_interes;
    LOOP
     FETCH c_reporte_interes BULK COLLECT INTO pedidoRecord LIMIT W_FILAS;
     IF lbAbrirUtlFile THEN
        GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.csv', psTitulo, lsDirTempo, V_HANDLE);
        psDirectorio   := lsdirtempo;
        lbAbrirUtlFile := FALSE;
     END IF ;
     
     IF pedidoRecord.COUNT > 0 THEN
        FOR x IN pedidoRecord.FIRST .. pedidoRecord.LAST LOOP

            lsLinea :='=T("'||pedidoRecord(x).DES_REGI ||'")' ||','||
                      '=T("'||pedidoRecord(x).DES_ZONA ||'")' ||','||
                      '=T("'||pedidoRecord(x).COD_CLIE ||'")' ||','||
                      '=T("'||pedidoRecord(x).NOMBRE ||'")' ||','||
                      '=T("'||trim(to_char(pedidoRecord(x).GASTO_ADMIN, '999999999990.99')) ||'")' ||','||
                      '=T("'||trim(to_char(pedidoRecord(x).INTE_CALCU, '999999999990.99')) ||'")' ||','||
                      '=T("'||trim(to_char(pedidoRecord(x).INTE_TOTAL, '999999999990.99')) ||'")' ||','||
                      '=T("'||trim(to_char(pedidoRecord(x).MONTO_FACT, '999999999990.99')) ||'")' ||','||
                      '=T("'||trim(to_char(pedidoRecord(x).NUM_HIST, '999999999990.99')) ||'")' ||','||                    
                      '=T("'||pedidoRecord(x).FEC_VENC ||'")' ||','||
                      '=T("'||pedidoRecord(x).FEC_MOVI ||'")' ||','||
                      '=T("'||trim(to_char(pedidoRecord(x).IMP_PEND, '999999999990.99')) ||'")' ||','||
                      '=T("'||trim(to_char(pedidoRecord(x).IMP_PAGO, '999999999990.99')) ||'")' ||','||
                      '=T("'||trim(to_char(pedidoRecord(x).INTE_VARI, '999999999990.99')) ||'")' ||','||                    
                      '"'||pedidoRecord(x).DIAS_DIFE ||'"' ||','||                   
                      '=T("'||trim(to_char(pedidoRecord(x).TASA_INTE, '999999999990.99')) ||'")' ||','||
                      '=T("'||pedidoRecord(x).FEC_GENE ||'")';
                      
              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
        END LOOP;
      END IF;
      EXIT WHEN c_reporte_interes%NOTFOUND;
   END LOOP;
   CLOSE c_reporte_interes; 
  END IF;   
 
 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
 END IF;
 RETURN;
 
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR CCC_PR_INTER_FACTU_TOTAL: '||ls_sqlerrm);
END CCC_PR_INTER_FACTU_TOTAL;



/*************************************************************************************
  Descripcion       : Procedimiento que crear los tres tipos de Reporte Interes 
  Fecha Creacion    : 03/12/2015
  Autor             : Karina Valencia
  ************************************************************************************/
  PROCEDURE CCC_PR_GENER_INTER_TOTAL(    
    psTipoReporte                  VARCHAR2,
    psCondRegion                   VARCHAR2,
    psCondZona                     VARCHAR2,
    psCondCliente                  VARCHAR2,
    psfechaInicio                  VARCHAR2,
    psFechaFin                     VARCHAR2
   
    )
IS

 TYPE ingresopedidosRec IS RECORD (
  DES_REGI   CCC_TEMPO_INTER_TOTAL.DES_REGI%type,  
  DES_ZONA   CCC_TEMPO_INTER_TOTAL.DES_ZONA%type,  
  COD_CLIE   CCC_TEMPO_INTER_TOTAL.COD_CLIE%type,  
  NOMBRE     CCC_TEMPO_INTER_TOTAL.NOMBRE%type,  
  INTE_VARI  CCC_TEMPO_INTER_TOTAL.INTE_VARI%type,  
  NUM_HIST   CCC_TEMPO_INTER_TOTAL.NUM_HIST%type,  
  FEC_VENC   CCC_TEMPO_INTER_TOTAL.FEC_VENC%type,  
  FEC_MOVI   CCC_TEMPO_INTER_TOTAL.FEC_MOVI%type,  
  DEUDA_PEND CCC_TEMPO_INTER_TOTAL.DEUDA_PEND%type,  
  IMP_PAGO   CCC_TEMPO_INTER_TOTAL.IMP_PAGO%type,  
  INTER_VAR  CCC_TEMPO_INTER_TOTAL.INTER_VAR%type,  
  DIAS_DIFE  CCC_TEMPO_INTER_TOTAL.DIAS_DIFE%type,   
  TASA_INTE  CCC_TEMPO_INTER_TOTAL.TASA_INTE%type,  
  FEC_GENE   CCC_TEMPO_INTER_TOTAL.FEC_GENE%type,  
  GASTO_ADMIN  CCC_TEMPO_INTER_TOTAL.GASTO_ADMIN%type,  
  INTE_CALCU   CCC_TEMPO_INTER_TOTAL.INTE_CALCU%type,
  INTE_TOTAL   CCC_TEMPO_INTER_TOTAL.INTE_TOTAL%type,       
  IMP_PEND     CCC_TEMPO_INTER_TOTAL.IMP_PEND%type,  
  MONTO_FACT   CCC_TEMPO_INTER_TOTAL.MONTO_FACT%type 
  );
  
  TYPE ingresopedidostab IS TABLE OF ingresopedidosRec;
    ingresopedidos ingresopedidostab;

    TYPE CUR_TYP  IS REF CURSOR;
    c_interesPedido CUR_TYP;
    c_interesBloqueo CUR_TYP;
    c_interesFacturado CUR_TYP;
  
    v_query varchar2(16000) ;
    rows NATURAL := 1000;
    
     BEGIN
       
    --Se elimina la data para el continuo uso del reporte
    DELETE FROM CCC_TEMPO_INTER_TOTAL;

  IF psTipoReporte = 'CIP' THEN 
   v_query :=
    'select gen_pkg_gener.GEN_FN_CLIEN_DATOS(mc.COD_CLIE, ''DES_REGI'') DES_REGI,
          gen_pkg_gener.GEN_FN_CLIEN_DATOS(mc.COD_CLIE, ''DES_ZONA'') DES_ZONA,
          mc.cod_clie COD_CLIE,
          mc.val_ape1 || '' '' || mc.val_ape2 || '' '' || mc.val_nom1 || '' '' ||
          mc.val_nom2 NOMBRE,
          cons.imp_inte_vari INTE_VARI,
          det.num_hist NUM_HIST,
          TO_CHAR(det.fec_venc, ''DD/MM/YYYY'') FEC_VENC,
          TO_CHAR(det.fec_movi, ''DD/MM/YYYY'') FEC_MOVI,
          det.imp_deud_pend DEUDA_PEND,
          det.imp_pago IMP_PAGO,
          det.imp_inte_vari INTER_VAR,
          det.num_dias_dife DIAS_DIFE,
          det.imp_tasa_inte TASA_INTE,
          TO_CHAR(cons.fec_gene, ''DD/MM/YYYY'') FEC_GENE,
          NULL GASTO_ADMIN,
          NULL INTE_CALCU,
          NULL INTE_TOTAL,
          NULL IMP_PEND,
          NULL MONTO_FACT
     from ccc_conso_gener_inter_mora cons,
          mae_clien mc,
          (select *
             from ccc_detal_gener_inter_mora
           union
           select * from ccc_detal_gener_inter_saldo) det
    where mc.oid_clie = cons.oid_clie
      and cons.oid_movi_cc = det.oid_mvcc_oid_cc
      and cons.fec_gene >= to_date('''||psfechaInicio||''',''DD/MM/YYYY'')
      and cons.fec_gene <  to_date('''||psFechaFin||''',''DD/MM/YYYY'') + 1
      '||psCondRegion||'
      '||psCondZona||'
      '||psCondCliente||'     
    order by 1, 2, 3, 6';
    
    OPEN c_interesPedido for v_query;
           LOOP
               FETCH c_interesPedido BULK COLLECT
                   INTO ingresopedidos LIMIT rows;

               FORALL i IN 1 .. ingresopedidos.count               
                   INSERT INTO CCC_TEMPO_INTER_TOTAL(DES_REGI,DES_ZONA,COD_CLIE,NOMBRE,
                          INTE_VARI,NUM_HIST,FEC_VENC,
                          FEC_MOVI,DEUDA_PEND,IMP_PAGO,INTER_VAR,DIAS_DIFE,TASA_INTE,FEC_GENE) 
                    VALUES(ingresopedidos(i).DES_REGI, 
                    ingresopedidos(i).DES_ZONA, 
                    ingresopedidos(i).COD_CLIE, 
                    ingresopedidos(i).NOMBRE, 
                    ingresopedidos(i).INTE_VARI, 
                    ingresopedidos(i).NUM_HIST, 
                    ingresopedidos(i).FEC_VENC,
                    ingresopedidos(i).FEC_MOVI, 
                    ingresopedidos(i).DEUDA_PEND, 
                    ingresopedidos(i).IMP_PAGO, 
                    ingresopedidos(i).INTER_VAR, 
                    ingresopedidos(i).DIAS_DIFE, 
                    ingresopedidos(i).TASA_INTE, 
                    ingresopedidos(i).FEC_GENE
                    );                 
                    
               EXIT WHEN c_interesPedido%NOTFOUND;
           END LOOP;
           CLOSE c_interesPedido;   
  END IF;
  
  -- Reporte de Interes -Bloqueo
  IF psTipoReporte = 'CIB' THEN 
   v_query :=    
    'select gen_pkg_gener.GEN_FN_CLIEN_DATOS(mc.COD_CLIE, ''DES_REGI'') DES_REGI,
         gen_pkg_gener.GEN_FN_CLIEN_DATOS(mc.COD_CLIE, ''DES_ZONA'') DES_ZONA,
         mc.cod_clie COD_CLIE,
         mc.val_ape1 || '' '' || mc.val_ape2 || '' '' || mc.val_nom1 || '' '' ||
         mc.val_nom2 NOMBRE,
         det.imp_inte_vari INTE_VARI,       
         det.num_hist NUM_HIST,
         TO_CHAR(det.fec_venc, ''DD/MM/YYYY'') FEC_VENCI,
         TO_CHAR(det.fec_movi, ''DD/MM/YYYY'') FEC_MOVI,
         NULL DEUDA_PEND,         
         det.imp_pago IMP_PAGO,
         NULL INTER_VAR,         
         det.num_dias_dife DIAS_DIFE,
         det.imp_tasa_inte TASA_INTE,
         TO_CHAR(cons.fec_bloq, ''DD/MM/YYYY'') FEC_GENE,
         cons.imp_inte_fijo GASTO_ADMIN,
         cons.imp_inte_vari INTE_CALCU,
         nvl(cons.imp_inte_fijo, 0) + nvl(cons.imp_inte_vari, 0) INTE_TOTAL,
         det.imp_pend IMP_PEND,
         NULL MONTO_FACT         
    from ccc_conso_calcu_inter_mora cons,
         mae_clien mc,
         (select *
            from ccc_detal_calcu_inter_mora
          union
          select * from ccc_detal_calcu_inter_saldo) det
   where mc.oid_clie = cons.clie_oid_clie
     and cons.oid_movi_cc = det.oid_mvcc_oid_cc
     '||psCondRegion||'
     '||psCondZona||'
     '||psCondCliente||'
 order by 1, 2, 3, 8';
    
    OPEN c_interesBloqueo for v_query;
           LOOP
               FETCH c_interesBloqueo BULK COLLECT
                   INTO ingresopedidos LIMIT rows;

               FORALL i IN 1 .. ingresopedidos.count               
                   INSERT INTO CCC_TEMPO_INTER_TOTAL(DES_REGI,DES_ZONA,COD_CLIE,NOMBRE,
                          GASTO_ADMIN,INTE_CALCU,INTE_TOTAL,NUM_HIST,FEC_VENC,
                          FEC_MOVI,IMP_PEND,IMP_PAGO,INTE_VARI,DIAS_DIFE,TASA_INTE,FEC_GENE) 
                    VALUES(ingresopedidos(i).DES_REGI, 
                    ingresopedidos(i).DES_ZONA, 
                    ingresopedidos(i).COD_CLIE, 
                    ingresopedidos(i).NOMBRE, 
                    ingresopedidos(i).GASTO_ADMIN,
                    ingresopedidos(i).INTE_CALCU, 
                    ingresopedidos(i).INTE_TOTAL,  
                    ingresopedidos(i).NUM_HIST, 
                    ingresopedidos(i).FEC_VENC,
                    ingresopedidos(i).FEC_MOVI, 
                    ingresopedidos(i).IMP_PEND, 
                    ingresopedidos(i).IMP_PAGO, 
                    ingresopedidos(i).INTE_VARI, 
                    ingresopedidos(i).DIAS_DIFE, 
                    ingresopedidos(i).TASA_INTE, 
                    ingresopedidos(i).FEC_GENE
                    );                 
                    
               EXIT WHEN c_interesBloqueo%NOTFOUND;
           END LOOP;
           CLOSE c_interesBloqueo;   
  END IF;
  
   -- Reporte de Interes -Facturado
  IF psTipoReporte = 'CIF' THEN 
   v_query :=  
 'select gen_pkg_gener.GEN_FN_CLIEN_DATOS(mc.COD_CLIE, ''DES_REGI'') DES_REGI,
         gen_pkg_gener.GEN_FN_CLIEN_DATOS(mc.COD_CLIE, ''DES_ZONA'') DES_ZONA,
         mc.cod_clie COD_CLIE,
         mc.val_ape1 || '' '' || mc.val_ape2 || '' '' || mc.val_nom1 || '' '' ||
         mc.val_nom2 NOMBRE,
         det.imp_inte_vari INTE_VARI,         
         det.num_hist NUM_HIST,
         TO_CHAR(det.fec_venc, ''DD/MM/YYYY'') FEC_VENCI,
         TO_CHAR(det.fec_movi, ''DD/MM/YYYY'') FEC_MOVI,         
         NULL DEUDA_PEND,
         det.imp_pago IMP_PAGO,
         NULL INTER_VAR,
         det.num_dias_dife DIAS_DIFE,
         det.imp_tasa_inte TASA_INTE,
         TO_CHAR(cons.fec_fact, ''DD/MM/YYYY'') FEC_GENE,
         cons.imp_inte_fijo GASTO_ADMIN,
         cons.imp_inte_vari INTE_CALCU,
         nvl(cons.imp_inte_fijo, 0) + nvl(cons.imp_inte_vari, 0)INTE_TOTAL,         
         det.imp_pend IMP_PEND,
         cons.mon_fact MONTO_FACT
    from ccc_conso_calcu_inter_histo cons,
         mae_clien                   mc,
         ccc_detal_calcu_inter_histo det
   where mc.oid_clie = cons.clie_oid_clie
     and cons.oid_movi_cc = det.oid_mvcc_oid_cc  
     and cons.fec_fact >= to_date('''||psfechaInicio||''',''DD/MM/YYYY'')
     AND cons.fec_fact < to_date('''||psFechaFin||''',''DD/MM/YYYY'') + 1
     '||psCondRegion||'
     '||psCondZona||'
     '||psCondCliente||' 
   order by 1, 2, 3, 9';
    
    OPEN c_interesFacturado for v_query;
           LOOP
               FETCH c_interesFacturado BULK COLLECT
                   INTO ingresopedidos LIMIT rows;

               FORALL i IN 1 .. ingresopedidos.count               
                   INSERT INTO CCC_TEMPO_INTER_TOTAL(DES_REGI,DES_ZONA,COD_CLIE,NOMBRE,
                          GASTO_ADMIN,INTE_CALCU,INTE_TOTAL,MONTO_FACT,NUM_HIST,FEC_VENC,
                          FEC_MOVI,IMP_PEND,IMP_PAGO,INTE_VARI,DIAS_DIFE,TASA_INTE,FEC_GENE) 
                    VALUES(ingresopedidos(i).DES_REGI, 
                    ingresopedidos(i).DES_ZONA, 
                    ingresopedidos(i).COD_CLIE, 
                    ingresopedidos(i).NOMBRE, 
                    ingresopedidos(i).GASTO_ADMIN,
                    ingresopedidos(i).INTE_CALCU, 
                    ingresopedidos(i).INTE_TOTAL, 
                    ingresopedidos(i).MONTO_FACT, 
                    ingresopedidos(i).NUM_HIST, 
                    ingresopedidos(i).FEC_VENC,
                    ingresopedidos(i).FEC_MOVI, 
                    ingresopedidos(i).IMP_PEND, 
                    ingresopedidos(i).IMP_PAGO, 
                    ingresopedidos(i).INTE_VARI, 
                    ingresopedidos(i).DIAS_DIFE, 
                    ingresopedidos(i).TASA_INTE, 
                    ingresopedidos(i).FEC_GENE
                    );                 
                    
               EXIT WHEN c_interesFacturado%NOTFOUND;
           END LOOP;
           CLOSE c_interesFacturado;   
  END IF;
 EXCEPTION
       WHEN OTHERS THEN
         ln_sqlcode := SQLCODE;
         ls_sqlerrm := substr(sqlerrm,1,250);
         RAISE_APPLICATION_ERROR(-20123, 'ERROR CCC_PR_GENER_INTER_TOTAL: '||ls_sqlerrm);
 END CCC_PR_GENER_INTER_TOTAL;

END CCC_PKG_REPOR_PROCE;
/
