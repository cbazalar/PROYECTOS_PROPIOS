create or replace package INT_PKG_SAPFI is

/* Declaracion de variables */
ln_sqlcode   NUMBER(10);
ls_sqlerrm   VARCHAR2(150);
W_FILAS      NUMBER:=1000;

/* Declaracion de procedures */
/***************************************************************************
Descripcion       : Genera Informacion para la Interfaz de Envia SAPFI Facturacion
Fecha Creacion    : 04/09/2009
Autor             : Jose Cairampoma
***************************************************************************/
PROCEDURE INT_PR_SAF_GENER_SAPFI_FACTU
  (psNumeroLote       VARCHAR2,
   psFecha            VARCHAR2);

/***************************************************************************
Descripcion       : Genera Interfaz de Envia SAPFI Facturacion
Fecha Creacion    : 04/09/2009
Autor             : Jose Cairampoma
***************************************************************************/
PROCEDURE INT_PR_SAF_SAPFI_FACTU
  (psCodigoPais       VARCHAR2,
   psCodigoSistema    VARCHAR2,
   psCodigoInterfaz   VARCHAR2,
   psNombreArchivo    VARCHAR2,
   psNumeroLote       VARCHAR2,
   psFecha            VARCHAR2);
  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Cabecera para Colombia
  Fecha Creacion    : 23/05/2013
  Autor             : Jorge Yépez
  ***************************************************************************/
  PROCEDURE int_pr_saf_cabec_colom
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psperiodo        VARCHAR2,
    psfecha          VARCHAR2
  ) ;
  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Detalle para Colombia
  Fecha Creacion    : 23/05/2013
  Autor             : Jorge Yépez
  ***************************************************************************/
  PROCEDURE int_pr_saf_detal_colom
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psperiodo        VARCHAR2,
    psfecha          VARCHAR2
  ) ;
  /***************************************************************************
  Descripcion       : Genera Interfaz de NUEVA SAPFI
  Fecha Creacion    : 23/05/2013
  Autor             : Jorge Yépez
  ***************************************************************************/
  PROCEDURE int_pr_saf_corpo
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscentro         VARCHAR2,
    psperiodo        VARCHAR2,
    psfecha          VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Llena las tablas para SAPFI
  Fecha Creacion    : 23/05/2013
  Autor             : Jorge Yépez
  ***************************************************************************/
  PROCEDURE int_pr_carga_saf_corpo
  (
    pscodigopais     VARCHAR2,
    psperiodo        VARCHAR2,
    psfecha          VARCHAR2,
    psnumlote        VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Comision Ejecutiva para SAP-FI
  Fecha Creacion    : 09/07/2013
  Autor             : Carlos Bazalar
  ***************************************************************************/
  PROCEDURE INT_PR_SAF_ENVIO_COMIS_EJECU
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psCodigoPeriodo  VARCHAR2
  );

  /***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte SAPFI Cabecera
Fecha Creacion    : 07/02/2014
Autor             : Gonzalo Javier Huertas Agurto
Parametros        :
            psCodigoPeriodo : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE INT_PR_SAF_REPOR_SAPF_CABE(

    psCodigoPais                        VARCHAR2,
    psFechaProceso                      VARCHAR2,
    pscodigosistema                     VARCHAR2,
    pscodigointerfaz                    VARCHAR2,
    psnombrearchivo                     VARCHAR2
    );

  /***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte SAPFI Detalle
Fecha Creacion    : 07/02/2014
Autor             : Gonzalo Javier Huertas Agurto
Parametros        :
            psCodigoPeriodo : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE INT_PR_SAF_REPOR_SAPF_DETA(

    psCodigoPais                        VARCHAR2,
    psFechaProceso                      VARCHAR2,
    pscodigosistema                     VARCHAR2,
    pscodigointerfaz                    VARCHAR2,
    psnombrearchivo                     VARCHAR2
    );

  /***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte SAPFI Resumen
Fecha Creacion    : 10/02/2014
Autor             : Gonzalo Javier Huertas Agurto
Parametros        :
            psCodigoPeriodo : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE INT_PR_SAF_REPOR_SAPF_RESU(

    psCodigoPais                        VARCHAR2,
    pscodigosistema                     VARCHAR2,
    pscodigointerfaz                    VARCHAR2,
    psnombrearchivo                     VARCHAR2
    );

  /***************************************************************************
  Descripcion       : Generar interfaz de pagos para SAP-FI
  Fecha Creacion    : 13/03/2014
  Autor             : henry paredes
  ***************************************************************************/
  PROCEDURE INT_PR_SAF_PAGOS_LIDER
  (
    pscodigopais     VARCHAR2,
    psCodigoPeriodo  VARCHAR2,
    pscodigoPrograma  VARCHAR2,
    psfecha          VARCHAR2,
    pscodigoTipoPago VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pstipoenvio      VARCHAR2
  );

 /***************************************************************************
  Descripcion       : Generar interfaz de pagos para SAP-FI
  Fecha Creacion    : 13/06/2014
  Autor             : Juan Gutierrez
  ***************************************************************************/

  PROCEDURE int_pr_saf_envio_socia_empre(
     pscodigopais     VARCHAR,
     pscodigoperiodo  VARCHAR,
     psfechafact      VARCHAR,
     pscodigosistema  VARCHAR,
     pscodigointerfaz VARCHAR,
     psnombrearchivo  VARCHAR,
     psUsuario        VARCHAR
   );
  /***************************************************************************
  Descripcion       : Llena las tablas para SAPFI
  Fecha Creacion    : 23/05/2013
  Autor             : Jorge Yépez
  ***************************************************************************/
  PROCEDURE int_pr_carga_saf_corpo2
  (
    pscodigopais     VARCHAR2,
    psperiodo        VARCHAR2,
    psfecha          VARCHAR2,
    psnumlote        VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz de NUEVA SAPFI
  Fecha Creacion    : 23/05/2013
  Autor             : Jorge Yépez
  ***************************************************************************/
  PROCEDURE int_pr_saf_corpo2
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscentro         VARCHAR2,
    psperiodo        VARCHAR2,
    psfecha          VARCHAR2
  );

  /**********************************************************************************
  Descripcion       : Genera Interfaz de Pagos de Concurso de Ventas Consultoras Top
  Fecha Creacion    : 20/02/2015
  Autor             : Carlos Mori
  ***********************************************************************************/
  PROCEDURE INT_PR_SAF_PAGOS_TOP
  (
    psCodigoPais     VARCHAR2,
    psCodigoCampanna VARCHAR2,
    pdFechaPago      DATE,
    psTipoEnvio      VARCHAR2,
    psCodigoSistema  VARCHAR2,
    psCodigoInterfaz VARCHAR2,
    psNombreArchivo  VARCHAR2,
    psCodigoUsuario  VARCHAR2
  );

END INT_PKG_SAPFI;
/
CREATE OR REPLACE PACKAGE BODY int_pkg_sapfi IS
  /***************************************************************************
  Descripcion       : Genera Informacion para la Interfaz de Envia SAPFI Facturacion
  Fecha Creacion    : 04/09/2009
  Autor             : Jose Cairampoma
  ***************************************************************************/
 PROCEDURE int_pr_saf_gener_sapfi_factu
 (
   psnumerolote VARCHAR2,
   psfecha      VARCHAR2
 ) IS


 BEGIN


   INSERT INTO int_saf_sapfi_factu
     (num_lote,
      cod_pais,
      fec_cont,
      cod_soci,
      cod_cana,
      cod_acce,
      cod_suba,
      tip_peri_come,
      cod_peri_come,
      val_ejer_come,
      tip_asie,
      tip_movi_safi,
      num_docu_iden,
      des_glos,
      ind_debe_habe,
      cod_marc_prod,
      cod_grup_arti,
      cod_nego_prod,
      tip_ofer,
      val_cicl_vida,
      val_impo,
      cod_mone,
      cod_peri_cont,
      val_ejer_cont,
      fec_docu,
      cod_banc,
      fec_pago_banc,
      fec_valo,
      num_comp,
      val_reca_sucu,
      cod_zona)

    WITH periodos as (

SELECT DISTINCT cab_p.perd_oid_peri oid_peri
  FROM fac_docum_conta_cabec cab_p
 WHERE cab_p.num_lote_cont IS NULL
   AND trunc(cab_p.fec_fact) <= to_date(psfecha,
                                        'DD/MM/YYYY')
   AND cab_p.tido_oid_tipo_docu in (1,9,28,29,30,34,31,32,33))
     SELECT psnumerolote numerolote,
            codigopais,
            fecha fechacontable,
            codigoempresa sociedad,
            codigocanal canal,
            codigoacceso acceso,
            codigosubacceso subacceso,
            codigotipoperiodo tipoperiodo,
            substr(codigoperiodo,
                   5,
                   2) periodocomercial,
            ejerciciocomercial,
            tipo_asiento tipoasiento,
            cod_cuen_cont tipomovimientosapfi, --
            NULL documentoidentidad,
            decode(tipo_asiento,
                   'AB',
                   'Nota de Crédito  ' || to_char(fecha,
                                                  'YYYYMMDD') || ' VD',
                   'Venta ' || to_char(fecha,
                                       'YYYYMMDD') || ' VD') glosa,
            val_indi_debe_habe inddebehaber,
            codigomarcaproducto marcaproducto,
            substr(grupoproductos,
                   1,
                   9) grupoarticulo,
            codigonegocio negocio,
            codigotipooferta tipooferta,
            decode(substr(grupoproductos,
                          1,
                          9),
                   NULL,
                   NULL,
                   '00') ciclovida,
            importe importe,
            (SELECT m.cod_mone
               FROM seg_pais  p,
                    seg_moned m
              WHERE p.mone_oid_mone = m.oid_mone
                AND oid_pais = pais) moneda,
            to_char(fecha,
                    'MM') /**/ periodocontable,
            to_char(fecha,
                    'YYYY') /**/ ejerciciocontable,
            fecha fechadocumento,
            NULL codigobanco,
            NULL fechapagobanco,
            fecha fechavalor,
            NULL numcomprobante,
            NULL recaudadorasucursal,
            NULL zona
       FROM ((SELECT pais,
                     codigopais,
                     fecha,
                     sociedad,
                     codigoempresa,
                     codigocanal,
                     codigoacceso,
                     subacceso,
                     codigosubacceso,
                     codigotipoperiodo,
                     periodo,
                     codigoperiodo,
                     ejerciciocomercial,
                     CASE
                       WHEN importe < 0 THEN
                        decode(val_indi_debe_habe,
                               'D',
                               'H',
                               'D')
                       ELSE
                        val_indi_debe_habe
                     END val_indi_debe_habe,
                     'AB' tipo_asiento,
                     cod_cuen_cont,
                     codigomarcaproducto,
                     grupoproductos,
                     codigonegocio,
                     codigotipooferta,
                     tipo_soli,
                     tipo_impu,
                     cod,
                     abs(importe) importe
                FROM (SELECT pais,
                             codigopais,
                             fecha,
                             sociedad,
                             codigoempresa,
                             codigocanal,
                             codigoacceso,
                             subacceso,
                             codigosubacceso,
                             codigotipoperiodo,
                             periodo,
                             codigoperiodo,
                             ejerciciocomercial,
                             val_indi_debe_habe,
                             cod_cuen_cont,
                             codigomarcaproducto,
                             grupoproductos,
                             codigonegocio,
                             codigotipooferta,
                             tipo_soli,
                             tipo_impu,
                             cod,
                             SUM(importe50) importe
                        FROM (SELECT cab.pais_oid_pais pais,
                                     pai.cod_pais codigopais,
                                     cab.fec_fact fecha,
                                     cab.soci_oid_soci sociedad,
                                     soc.cod_soci codigoempresa,
                                     cana.cod_cana codigocanal,
                                     acce.cod_acce codigoacceso,
                                     cab.sbac_oid_sbac subacceso,
                                     sbac.cod_sbac codigosubacceso,
                                     tpe.cod_tipo_peri codigotipoperiodo,
                                     peri.peri_oid_peri periodo,
                                     pcor.cod_peri codigoperiodo,
                                     pcor.val_anio ejerciciocomercial,
                                     ipc.val_indi_debe_habe,
                                     cc.cod_cuen_cont,
                                     NULL codigomarcaproducto,
                                     NULL grupoproductos,
                                     NULL codigonegocio,
                                     NULL codigotipooferta,
                                     ipc.tspa_oid_tipo_soli_pais tipo_soli,
                                     ipc.TAIM_OID_TASA_IMPU tipo_impu,
                                     '50' cod,
                                     CASE
                                       WHEN con.cod_tipo_prog IN ('B', 'C', 'R') THEN
                                        0
                                       ELSE
                                        cab.val_tota_paga_loca
                                     END importe50
                                FROM fac_docum_conta_cabec cab,
                                     ped_solic_cabec       psc,
                                     seg_pais              pai,
                                     seg_socie             soc,
                                     seg_subac             sbac,
                                     seg_acces             acce,
                                     seg_canal             cana,
                                     cra_perio             peri,
                                     seg_perio_corpo       pcor,
                                     seg_tipo_perio        tpe,
                                     int_param_conta_factu ipc,
                                     int_impor_conta_factu icf,
                                     ccc_cuent_conta       cc,
                                     inc_concu_tipo_prog   con
                               WHERE nvl(cab.val_tota_paga_loca,
                                         0) < 0
                                 AND cab.tido_oid_tipo_docu in (31,32,33)
                                 and cab.soca_oid_soli_cabe=psc.oid_soli_cabe
                                 AND cab.pais_oid_pais = pai.oid_pais
                                 AND cab.soci_oid_soci = soc.oid_soci
                                 AND cab.sbac_oid_sbac = sbac.oid_sbac
                                 AND cab.perd_oid_peri = peri.oid_peri
                                 AND peri.peri_oid_peri = pcor.oid_peri
                                 AND sbac.acce_oid_acce = acce.oid_acce
                                 AND acce.cana_oid_cana = cana.oid_cana
                                 AND pcor.tipe_oid_tipo_peri = tpe.oid_tipo_peri
                                 AND ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                 AND ipc.val_tipo_asie = 'AB'
                                 AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                 AND cod_impo_cont = '50'
                                 AND cab.ictp_oid_tipo_prog = con.oid_tipo_prog(+)
                                 AND cab.num_lote_cont IS NULL
                                 AND trunc(cab.fec_fact) <=
                                     to_date(psfecha,
                                             'DD/MM/YYYY')
                                 AND psc.TAIM_OID_TASA_IMPU =
                                     nvl(ipc.TAIM_OID_TASA_IMPU,
                                         decode((SELECT COUNT(*)
                                                  FROM int_param_conta_factu ipc,
                                                       int_impor_conta_factu icf,
                                                       ccc_cuent_conta       cc
                                                 WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                   AND ipc.val_tipo_asie = 'AB'
                                                   AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                   AND cod_impo_cont = '50'
                                                   AND TAIM_OID_TASA_IMPU = psc.TAIM_OID_TASA_IMPU),
                                                0,
                                                psc.TAIM_OID_TASA_IMPU,
                                                psc.TAIM_OID_TASA_IMPU))
                                 AND psc.TSPA_OID_TIPO_SOLI_PAIS =
                                     nvl(ipc.TSPA_OID_TIPO_SOLI_PAIS,
                                         decode((SELECT COUNT(*)
                                                  FROM int_param_conta_factu ipc,
                                                       int_impor_conta_factu icf,
                                                       ccc_cuent_conta       cc
                                                 WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                   AND ipc.val_tipo_asie = 'AB'
                                                   AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                   AND cod_impo_cont = '50'
                                                   AND TSPA_OID_TIPO_SOLI_PAIS = psc.TSPA_OID_TIPO_SOLI_PAIS),
                                                0,
                                                psc.TSPA_OID_TIPO_SOLI_PAIS,
                                                psc.TSPA_OID_TIPO_SOLI_PAIS))
                                 AND cab.perd_oid_peri in  (select oid_peri  from periodos))
                       GROUP BY pais,
                                codigopais,
                                fecha,
                                sociedad,
                                codigoempresa,
                                codigocanal,
                                codigoacceso,
                                subacceso,
                                codigosubacceso,
                                codigotipoperiodo,
                                periodo,
                                codigoperiodo,
                                ejerciciocomercial,
                                val_indi_debe_habe,
                                cod_cuen_cont,
                                codigomarcaproducto,
                                codigonegocio,
                                codigotipooferta,
                                tipo_soli,
                                tipo_impu,
                                cod)) UNION

             (SELECT pais,
                     codigopais,
                     fecha,
                     sociedad,
                     codigoempresa,
                     codigocanal,
                     codigoacceso,
                     subacceso,
                     codigosubacceso,
                     codigotipoperiodo,
                     periodo,
                     codigoperiodo,
                     ejerciciocomercial,
                     CASE
                       WHEN importe < 0 THEN
                        decode(val_indi_debe_habe,
                               'D',
                               'H',
                               'D')
                       ELSE
                        val_indi_debe_habe
                     END val_indi_debe_habe,
                     'AB' tipo_asiento,
                     cod_cuen_cont,
                     codigomarcaproducto,
                     grupoproductos,
                     codigonegocio,
                     codigotipooferta,
                     tipo_soli,
                     tipo_impu,
                     cod,
                     abs(importe)
                FROM (SELECT pais,
                             codigopais,
                             fecha,
                             sociedad,
                             codigoempresa,
                             codigocanal,
                             codigoacceso,
                             subacceso,
                             codigosubacceso,
                             codigotipoperiodo,
                             periodo,
                             codigoperiodo,
                             ejerciciocomercial,
                             val_indi_debe_habe,
                             cod_cuen_cont,
                             codigomarcaproducto,
                             grupoproductos,
                             codigonegocio,
                             codigotipooferta,
                             tipo_soli,
                             tipo_impu,
                             cod,
                             SUM(importe55) importe
                        FROM (SELECT cab.pais_oid_pais pais,
                                     pai.cod_pais codigopais,
                                     cab.fec_fact fecha,
                                     cab.soci_oid_soci sociedad,
                                     soc.cod_soci codigoempresa,
                                     cana.cod_cana codigocanal,
                                     acce.cod_acce codigoacceso,
                                     cab.sbac_oid_sbac subacceso,
                                     sbac.cod_sbac codigosubacceso,
                                     tpe.cod_tipo_peri codigotipoperiodo,
                                     peri.peri_oid_peri periodo,
                                     pcor.cod_peri codigoperiodo,
                                     pcor.val_anio ejerciciocomercial,
                                     ipc.val_indi_debe_habe,
                                     cc.cod_cuen_cont,
                                     NULL codigomarcaproducto,
                                     NULL grupoproductos,
                                     NULL codigonegocio,
                                     NULL codigotipooferta,
                                     ipc.tspa_oid_tipo_soli_pais tipo_soli,
                                     ipc.TAIM_OID_TASA_IMPU tipo_impu,
                                     '55' cod,
                                     CASE
                                       WHEN con.cod_tipo_prog IN ('B', 'C', 'R') THEN
                                        0
                                       ELSE
                                        cab.imp_redo_loca
                                     END importe55
                                FROM fac_docum_conta_cabec cab,
                                     ped_solic_cabec       psc,
                                     seg_pais              pai,
                                     seg_socie             soc,
                                     seg_subac             sbac,
                                     seg_acces             acce,
                                     seg_canal             cana,
                                     cra_perio             peri,
                                     seg_perio_corpo       pcor,
                                     seg_tipo_perio        tpe,
                                     int_param_conta_factu ipc,
                                     int_impor_conta_factu icf,
                                     ccc_cuent_conta       cc,
                                     inc_concu_tipo_prog   con
                               WHERE nvl(cab.val_tota_paga_loca,
                                         0) < 0
                                 AND cab.tido_oid_tipo_docu in (31,32,33)
                                 and cab.soca_oid_soli_cabe=psc.oid_soli_cabe
                                 AND cab.pais_oid_pais = pai.oid_pais
                                 AND cab.soci_oid_soci = soc.oid_soci
                                 AND cab.sbac_oid_sbac = sbac.oid_sbac
                                 AND cab.perd_oid_peri = peri.oid_peri
                                 AND peri.peri_oid_peri = pcor.oid_peri
                                 AND sbac.acce_oid_acce = acce.oid_acce
                                 AND acce.cana_oid_cana = cana.oid_cana
                                 AND pcor.tipe_oid_tipo_peri = tpe.oid_tipo_peri
                                 AND ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                 AND ipc.val_tipo_asie = 'AB'
                                 AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                 AND cod_impo_cont = '55'
                                 AND cab.ictp_oid_tipo_prog = con.oid_tipo_prog(+)
                                 AND cab.num_lote_cont IS NULL
                                 AND trunc(cab.fec_fact) <=
                                     to_date(psfecha,
                                             'DD/MM/YYYY')
                                 AND psc.TAIM_OID_TASA_IMPU =
                                     nvl(ipc.TAIM_OID_TASA_IMPU,
                                         decode((SELECT COUNT(*)
                                                  FROM int_param_conta_factu ipc,
                                                       int_impor_conta_factu icf,
                                                       ccc_cuent_conta       cc
                                                 WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                   AND ipc.val_tipo_asie = 'AB'
                                                   AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                   AND cod_impo_cont = '55'
                                                   AND TAIM_OID_TASA_IMPU = psc.TAIM_OID_TASA_IMPU),
                                                0,
                                                psc.TAIM_OID_TASA_IMPU,
                                                psc.TAIM_OID_TASA_IMPU))
                                 AND psc.TSPA_OID_TIPO_SOLI_PAIS =
                                     nvl(ipc.TSPA_OID_TIPO_SOLI_PAIS,
                                         decode((SELECT COUNT(*)
                                                  FROM int_param_conta_factu ipc,
                                                       int_impor_conta_factu icf,
                                                       ccc_cuent_conta       cc
                                                 WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                   AND ipc.val_tipo_asie = 'AB'
                                                   AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                   AND cod_impo_cont = '55'
                                                   AND TSPA_OID_TIPO_SOLI_PAIS = psc.TSPA_OID_TIPO_SOLI_PAIS),
                                                0,
                                                psc.TSPA_OID_TIPO_SOLI_PAIS,
                                                psc.TSPA_OID_TIPO_SOLI_PAIS))
                                 AND cab.perd_oid_peri in  (select oid_peri  from periodos))
                       GROUP BY pais,
                                codigopais,
                                fecha,
                                sociedad,
                                codigoempresa,
                                codigocanal,
                                codigoacceso,
                                subacceso,
                                codigosubacceso,
                                codigotipoperiodo,
                                periodo,
                                codigoperiodo,
                                ejerciciocomercial,
                                val_indi_debe_habe,
                                cod_cuen_cont,
                                codigomarcaproducto,
                                grupoproductos,
                                codigonegocio,
                                codigotipooferta,
                                tipo_soli,
                                tipo_impu,
                                cod)) UNION

             (SELECT pais,
                     codigopais,
                     fecha,
                     sociedad,
                     codigoempresa,
                     codigocanal,
                     codigoacceso,
                     subacceso,
                     codigosubacceso,
                     codigotipoperiodo,
                     periodo,
                     codigoperiodo,
                     ejerciciocomercial,
                     CASE
                       WHEN importe < 0 THEN
                        decode(val_indi_debe_habe,
                               'D',
                               'H',
                               'D')
                       ELSE
                        val_indi_debe_habe
                     END val_indi_debe_habe,
                     'AB' tipo_asiento,
                     cod_cuen_cont,
                     codigomarcaproducto,
                     grupoproductos,
                     codigonegocio,
                     codigotipooferta,
                     tipo_soli,
                     tipo_impu,
                     cod,
                     abs(importe)
                FROM (SELECT pais,
                             codigopais,
                             fecha,
                             sociedad,
                             codigoempresa,
                             codigocanal,
                             codigoacceso,
                             subacceso,
                             codigosubacceso,
                             codigotipoperiodo,
                             periodo,
                             codigoperiodo,
                             ejerciciocomercial,
                             val_indi_debe_habe,
                             cod_cuen_cont,
                             codigomarcaproducto,
                             grupoproductos,
                             codigonegocio,
                             codigotipooferta,
                             tipo_soli,
                             tipo_impu,
                             cod,
                             SUM(importe60) importe
                        FROM (SELECT cab.pais_oid_pais pais,
                                     pai.cod_pais codigopais,
                                     cab.fec_fact fecha,
                                     cab.soci_oid_soci sociedad,
                                     soc.cod_soci codigoempresa,
                                     cana.cod_cana codigocanal,
                                     acce.cod_acce codigoacceso,
                                     cab.sbac_oid_sbac subacceso,
                                     sbac.cod_sbac codigosubacceso,
                                     tpe.cod_tipo_peri codigotipoperiodo,
                                     peri.peri_oid_peri periodo,
                                     pcor.cod_peri codigoperiodo,
                                     pcor.val_anio ejerciciocomercial,
                                     ipc.val_indi_debe_habe,
                                     cc.cod_cuen_cont,
                                     NULL codigomarcaproducto,
                                     NULL grupoproductos,
                                     NULL codigonegocio,
                                     NULL codigotipooferta,
                                     ipc.tspa_oid_tipo_soli_pais tipo_soli,
                                     ipc.TAIM_OID_TASA_IMPU tipo_impu,
                                     '60' cod,
                                     CASE
                                       WHEN con.cod_tipo_prog IN ('B', 'C', 'R') THEN
                                        0
                                       ELSE
                                        cab.imp_impu_tota_loca
                                     END importe60
                                FROM fac_docum_conta_cabec cab,
                                     ped_solic_cabec       psc,
                                     seg_pais              pai,
                                     seg_socie             soc,
                                     seg_subac             sbac,
                                     seg_acces             acce,
                                     seg_canal             cana,
                                     cra_perio             peri,
                                     seg_perio_corpo       pcor,
                                     seg_tipo_perio        tpe,
                                     int_param_conta_factu ipc,
                                     int_impor_conta_factu icf,
                                     ccc_cuent_conta       cc,
                                     inc_concu_tipo_prog   con
                               WHERE nvl(cab.val_tota_paga_loca,
                                         0) < 0
                                 AND cab.tido_oid_tipo_docu in (31,32,33)
                                 and cab.soca_oid_soli_cabe=psc.oid_soli_cabe
                                 AND cab.pais_oid_pais = pai.oid_pais
                                 AND cab.soci_oid_soci = soc.oid_soci
                                 AND cab.sbac_oid_sbac = sbac.oid_sbac
                                 AND cab.perd_oid_peri = peri.oid_peri
                                 AND peri.peri_oid_peri = pcor.oid_peri
                                 AND sbac.acce_oid_acce = acce.oid_acce
                                 AND acce.cana_oid_cana = cana.oid_cana
                                 AND pcor.tipe_oid_tipo_peri = tpe.oid_tipo_peri
                                 AND ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                 AND ipc.val_tipo_asie = 'AB'
                                 AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                 AND cod_impo_cont = '60'
                                 AND cab.ictp_oid_tipo_prog = con.oid_tipo_prog(+)
                                 AND cab.num_lote_cont IS NULL
                                 AND trunc(cab.fec_fact) <=
                                     to_date(psfecha,
                                             'DD/MM/YYYY')
                                 AND psc.TAIM_OID_TASA_IMPU =
                                     nvl(ipc.TAIM_OID_TASA_IMPU,
                                         decode((SELECT COUNT(*)
                                                  FROM int_param_conta_factu ipc,
                                                       int_impor_conta_factu icf,
                                                       ccc_cuent_conta       cc
                                                 WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                   AND ipc.val_tipo_asie = 'AB'
                                                   AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                   AND cod_impo_cont = '60'
                                                   AND TAIM_OID_TASA_IMPU = psc.TAIM_OID_TASA_IMPU),
                                                0,
                                                psc.TAIM_OID_TASA_IMPU,
                                                psc.TAIM_OID_TASA_IMPU))
                                 AND psc.TSPA_OID_TIPO_SOLI_PAIS =
                                     nvl(ipc.TSPA_OID_TIPO_SOLI_PAIS,
                                         decode((SELECT COUNT(*)
                                                  FROM int_param_conta_factu ipc,
                                                       int_impor_conta_factu icf,
                                                       ccc_cuent_conta       cc
                                                 WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                   AND ipc.val_tipo_asie = 'AB'
                                                   AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                   AND cod_impo_cont = '60'
                                                   AND TSPA_OID_TIPO_SOLI_PAIS = psc.TSPA_OID_TIPO_SOLI_PAIS),
                                                0,
                                                psc.TSPA_OID_TIPO_SOLI_PAIS,
                                                psc.TSPA_OID_TIPO_SOLI_PAIS))
                                 AND cab.perd_oid_peri in  (select oid_peri  from periodos))
                       GROUP BY pais,
                                codigopais,
                                fecha,
                                sociedad,
                                codigoempresa,
                                codigocanal,
                                codigoacceso,
                                subacceso,
                                codigosubacceso,
                                codigotipoperiodo,
                                periodo,
                                codigoperiodo,
                                ejerciciocomercial,
                                val_indi_debe_habe,
                                cod_cuen_cont,
                                codigomarcaproducto,
                                grupoproductos,
                                codigonegocio,
                                codigotipooferta,
                                tipo_soli,
                                tipo_impu,
                                cod)) UNION

             (SELECT pais,
                     codigopais,
                     fecha,
                     sociedad,
                     codigoempresa,
                     codigocanal,
                     codigoacceso,
                     subacceso,
                     codigosubacceso,
                     codigotipoperiodo,
                     periodo,
                     codigoperiodo,
                     ejerciciocomercial,
                     CASE
                       WHEN importe < 0 THEN
                        decode(val_indi_debe_habe,
                               'D',
                               'H',
                               'D')
                       ELSE
                        val_indi_debe_habe
                     END val_indi_debe_habe,
                     'AB' tipo_asiento,
                     cod_cuen_cont,
                     codigomarcaproducto,
                     grupoproductos,
                     codigonegocio,
                     codigotipooferta,
                     tipo_soli,
                     tipo_impu,
                     cod,
                     abs(importe)
                FROM (SELECT pais,
                             codigopais,
                             fecha,
                             sociedad,
                             codigoempresa,
                             codigocanal,
                             codigoacceso,
                             subacceso,
                             codigosubacceso,
                             codigotipoperiodo,
                             periodo,
                             codigoperiodo,
                             ejerciciocomercial,
                             val_indi_debe_habe,
                             cod_cuen_cont,
                             codigomarcaproducto,
                             grupoproductos,
                             codigonegocio,
                             codigotipooferta,
                             tipo_soli,
                             tipo_impu,
                             cod,
                             SUM(importe70) importe
                        FROM (SELECT cab.pais_oid_pais pais,
                                     pai.cod_pais codigopais,
                                     cab.fec_fact fecha,
                                     cab.soci_oid_soci sociedad,
                                     soc.cod_soci codigoempresa,
                                     cana.cod_cana codigocanal,
                                     acce.cod_acce codigoacceso,
                                     cab.sbac_oid_sbac subacceso,
                                     sbac.cod_sbac codigosubacceso,
                                     tpe.cod_tipo_peri codigotipoperiodo,
                                     peri.peri_oid_peri periodo,
                                     pcor.cod_peri codigoperiodo,
                                     pcor.val_anio ejerciciocomercial,
                                     ipc.val_indi_debe_habe,
                                     cc.cod_cuen_cont,
                                     NULL codigomarcaproducto,
                                     NULL grupoproductos,
                                     NULL codigonegocio,
                                     NULL codigotipooferta,
                                     ipc.tspa_oid_tipo_soli_pais tipo_soli,
                                     ipc.TAIM_OID_TASA_IMPU tipo_impu,
                                     '70' cod,
                                     CASE
                                       WHEN con.cod_tipo_prog IN ('B', 'C', 'R') THEN
                                        0
                                       ELSE
                                        cab.imp_flet_impu_tota_loca
                                     END importe70
                                FROM fac_docum_conta_cabec cab,
                                     ped_solic_cabec       psc,
                                     seg_pais              pai,
                                     seg_socie             soc,
                                     seg_subac             sbac,
                                     seg_acces             acce,
                                     seg_canal             cana,
                                     cra_perio             peri,
                                     seg_perio_corpo       pcor,
                                     seg_tipo_perio        tpe,
                                     int_param_conta_factu ipc,
                                     int_impor_conta_factu icf,
                                     ccc_cuent_conta       cc,
                                     inc_concu_tipo_prog   con
                               WHERE nvl(cab.val_tota_paga_loca,
                                         0) < 0
                                 AND cab.tido_oid_tipo_docu in (31,32,33)
                                 and cab.soca_oid_soli_cabe=psc.oid_soli_cabe
                                 AND cab.pais_oid_pais = pai.oid_pais
                                 AND cab.soci_oid_soci = soc.oid_soci
                                 AND cab.sbac_oid_sbac = sbac.oid_sbac
                                 AND cab.perd_oid_peri = peri.oid_peri
                                 AND peri.peri_oid_peri = pcor.oid_peri
                                 AND sbac.acce_oid_acce = acce.oid_acce
                                 AND acce.cana_oid_cana = cana.oid_cana
                                 AND pcor.tipe_oid_tipo_peri = tpe.oid_tipo_peri
                                 AND ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                 AND ipc.val_tipo_asie = 'AB'
                                 AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                 AND cod_impo_cont = '70'
                                 AND cab.ictp_oid_tipo_prog = con.oid_tipo_prog(+)
                                 AND cab.num_lote_cont IS NULL
                                 AND trunc(cab.fec_fact) <=
                                     to_date(psfecha,
                                             'DD/MM/YYYY')
                                 AND psc.TAIM_OID_TASA_IMPU =
                                     nvl(ipc.TAIM_OID_TASA_IMPU,
                                         decode((SELECT COUNT(*)
                                                  FROM int_param_conta_factu ipc,
                                                       int_impor_conta_factu icf,
                                                       ccc_cuent_conta       cc
                                                 WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                   AND ipc.val_tipo_asie = 'AB'
                                                   AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                   AND cod_impo_cont = '70'
                                                   AND TAIM_OID_TASA_IMPU = psc.TAIM_OID_TASA_IMPU),
                                                0,
                                                psc.TAIM_OID_TASA_IMPU,
                                                psc.TAIM_OID_TASA_IMPU))
                                 AND psc.TSPA_OID_TIPO_SOLI_PAIS =
                                     nvl(ipc.TSPA_OID_TIPO_SOLI_PAIS,
                                         decode((SELECT COUNT(*)
                                                  FROM int_param_conta_factu ipc,
                                                       int_impor_conta_factu icf,
                                                       ccc_cuent_conta       cc
                                                 WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                   AND ipc.val_tipo_asie = 'AB'
                                                   AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                   AND cod_impo_cont = '70'
                                                   AND TSPA_OID_TIPO_SOLI_PAIS = psc.TSPA_OID_TIPO_SOLI_PAIS),
                                                0,
                                                psc.TSPA_OID_TIPO_SOLI_PAIS,
                                                psc.TSPA_OID_TIPO_SOLI_PAIS))
                                 AND cab.perd_oid_peri in  (select oid_peri  from periodos))
                       GROUP BY pais,
                                codigopais,
                                fecha,
                                sociedad,
                                codigoempresa,
                                codigocanal,
                                codigoacceso,
                                subacceso,
                                codigosubacceso,
                                codigotipoperiodo,
                                periodo,
                                codigoperiodo,
                                ejerciciocomercial,
                                val_indi_debe_habe,
                                cod_cuen_cont,
                                codigomarcaproducto,
                                grupoproductos,
                                codigonegocio,
                                codigotipooferta,
                                tipo_soli,
                                tipo_impu,
                                cod)) UNION

             (SELECT pais,
                     codigopais,
                     fecha,
                     sociedad,
                     codigoempresa,
                     codigocanal,
                     codigoacceso,
                     subacceso,
                     codigosubacceso,
                     codigotipoperiodo,
                     periodo,
                     codigoperiodo,
                     ejerciciocomercial,
                     CASE
                       WHEN importe < 0 THEN
                        decode(val_indi_debe_habe,
                               'D',
                               'H',
                               'D')
                       ELSE
                        val_indi_debe_habe
                     END val_indi_debe_habe,
                     'AB' tipo_asiento,
                     cod_cuen_cont,
                     codigomarcaproducto,
                     grupoproductos,
                     codigonegocio,
                     codigotipooferta,
                     tipo_soli,
                     tipo_impu,
                     cod,
                     abs(importe)
                FROM (SELECT pais,
                             codigopais,
                             fecha,
                             sociedad,
                             codigoempresa,
                             codigocanal,
                             codigoacceso,
                             subacceso,
                             codigosubacceso,
                             codigotipoperiodo,
                             periodo,
                             codigoperiodo,
                             ejerciciocomercial,
                             val_indi_debe_habe,
                             cod_cuen_cont,
                             codigomarcaproducto,
                             grupoproductos,
                             codigonegocio,
                             codigotipooferta,
                             tipo_soli,
                             tipo_impu,
                             cod,
                             SUM(importe80) importe
                        FROM (SELECT cab.pais_oid_pais pais,
                                     pai.cod_pais codigopais,
                                     cab.fec_fact fecha,
                                     cab.soci_oid_soci sociedad,
                                     soc.cod_soci codigoempresa,
                                     cana.cod_cana codigocanal,
                                     acce.cod_acce codigoacceso,
                                     cab.sbac_oid_sbac subacceso,
                                     sbac.cod_sbac codigosubacceso,
                                     tpe.cod_tipo_peri codigotipoperiodo,
                                     peri.peri_oid_peri periodo,
                                     pcor.cod_peri codigoperiodo,
                                     pcor.val_anio ejerciciocomercial,
                                     ipc.val_indi_debe_habe,
                                     cc.cod_cuen_cont,
                                     NULL codigomarcaproducto,
                                     NULL grupoproductos,
                                     NULL codigonegocio,
                                     NULL codigotipooferta,
                                     ipc.tspa_oid_tipo_soli_pais tipo_soli,
                                     ipc.TAIM_OID_TASA_IMPU tipo_impu,
                                     '80' cod,
                                     CASE
                                       WHEN con.cod_tipo_prog IN ('B', 'C', 'R') THEN
                                        0
                                       ELSE
                                        cab.imp_des1_sin_impu_tota
                                     END importe80
                                FROM fac_docum_conta_cabec cab,
                                     ped_solic_cabec       psc,
                                     seg_pais              pai,
                                     seg_socie             soc,
                                     seg_subac             sbac,
                                     seg_acces             acce,
                                     seg_canal             cana,
                                     cra_perio             peri,
                                     seg_perio_corpo       pcor,
                                     seg_tipo_perio        tpe,
                                     int_param_conta_factu ipc,
                                     int_impor_conta_factu icf,
                                     ccc_cuent_conta       cc,
                                     inc_concu_tipo_prog   con
                               WHERE nvl(cab.val_tota_paga_loca,
                                         0) < 0
                                 AND cab.tido_oid_tipo_docu in (31,32,33)
                                 and cab.soca_oid_soli_cabe=psc.oid_soli_cabe
                                 AND cab.pais_oid_pais = pai.oid_pais
                                 AND cab.soci_oid_soci = soc.oid_soci
                                 AND cab.sbac_oid_sbac = sbac.oid_sbac
                                 AND cab.perd_oid_peri = peri.oid_peri
                                 AND peri.peri_oid_peri = pcor.oid_peri
                                 AND sbac.acce_oid_acce = acce.oid_acce
                                 AND acce.cana_oid_cana = cana.oid_cana
                                 AND pcor.tipe_oid_tipo_peri = tpe.oid_tipo_peri
                                 AND ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                 AND ipc.val_tipo_asie = 'AB'
                                 AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                 AND cod_impo_cont = '80'
                                 AND cab.ictp_oid_tipo_prog = con.oid_tipo_prog(+)
                                 AND cab.num_lote_cont IS NULL
                                 AND trunc(cab.fec_fact) <=
                                     to_date(psfecha,
                                             'DD/MM/YYYY')
                                 AND psc.TAIM_OID_TASA_IMPU =
                                     nvl(ipc.TAIM_OID_TASA_IMPU,
                                         decode((SELECT COUNT(*)
                                                  FROM int_param_conta_factu ipc,
                                                       int_impor_conta_factu icf,
                                                       ccc_cuent_conta       cc
                                                 WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                   AND ipc.val_tipo_asie = 'AB'
                                                   AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                   AND cod_impo_cont = '80'
                                                   AND TAIM_OID_TASA_IMPU = psc.TAIM_OID_TASA_IMPU),
                                                0,
                                                psc.TAIM_OID_TASA_IMPU,
                                                psc.TAIM_OID_TASA_IMPU))
                                 AND psc.TSPA_OID_TIPO_SOLI_PAIS =
                                     nvl(ipc.TSPA_OID_TIPO_SOLI_PAIS,
                                         decode((SELECT COUNT(*)
                                                  FROM int_param_conta_factu ipc,
                                                       int_impor_conta_factu icf,
                                                       ccc_cuent_conta       cc
                                                 WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                   AND ipc.val_tipo_asie = 'AB'
                                                   AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                   AND cod_impo_cont = '80'
                                                   AND TSPA_OID_TIPO_SOLI_PAIS = psc.TSPA_OID_TIPO_SOLI_PAIS),
                                                0,
                                                psc.TSPA_OID_TIPO_SOLI_PAIS,
                                                psc.TSPA_OID_TIPO_SOLI_PAIS))
                                 AND cab.perd_oid_peri in  (select oid_peri  from periodos))
                       GROUP BY pais,
                                codigopais,
                                fecha,
                                sociedad,
                                codigoempresa,
                                codigocanal,
                                codigoacceso,
                                subacceso,
                                codigosubacceso,
                                codigotipoperiodo,
                                periodo,
                                codigoperiodo,
                                ejerciciocomercial,
                                val_indi_debe_habe,
                                cod_cuen_cont,
                                codigomarcaproducto,
                                grupoproductos,
                                codigonegocio,
                                codigotipooferta,
                                tipo_soli,
                                tipo_impu,
                                cod))

            UNION

             (SELECT pais,
                     codigopais,
                     fecha,
                     sociedad,
                     codigoempresa,
                     codigocanal,
                     codigoacceso,
                     subacceso,
                     codigosubacceso,
                     codigotipoperiodo,
                     periodo,
                     codigoperiodo,
                     ejerciciocomercial,
                     CASE
                       WHEN importe < 0 THEN
                        decode(val_indi_debe_habe,
                               'D',
                               'H',
                               'D')
                       ELSE
                        val_indi_debe_habe
                     END val_indi_debe_habe,
                     'AB' tipo_asiento,
                     cod_cuen_cont,
                     codigomarcaproducto,
                     grupoproductos,
                     codigonegocio,
                     codigotipooferta,
                     tipo_soli,
                     tipo_impu,
                     cod,
                     abs(importe)
                FROM (SELECT pais,
                             codigopais,
                             fecha,
                             sociedad,
                             codigoempresa,
                             codigocanal,
                             codigoacceso,
                             subacceso,
                             codigosubacceso,
                             codigotipoperiodo,
                             periodo,
                             codigoperiodo,
                             ejerciciocomercial,
                             val_indi_debe_habe,
                             cod_cuen_cont,
                             codigomarcaproducto,
                             grupoproductos,
                             codigonegocio,
                             codigotipooferta,
                             tipo_soli,
                             tipo_impu,
                             cod,
                             SUM(importe84) importe
                        FROM (SELECT cab.pais_oid_pais pais,
                                     pai.cod_pais codigopais,
                                     cab.fec_fact fecha,
                                     cab.soci_oid_soci sociedad,
                                     soc.cod_soci codigoempresa,
                                     cana.cod_cana codigocanal,
                                     acce.cod_acce codigoacceso,
                                     cab.sbac_oid_sbac subacceso,
                                     sbac.cod_sbac codigosubacceso,
                                     tpe.cod_tipo_peri codigotipoperiodo,
                                     peri.peri_oid_peri periodo,
                                     pcor.cod_peri codigoperiodo,
                                     pcor.val_anio ejerciciocomercial,
                                     ipc.val_indi_debe_habe,
                                     cc.cod_cuen_cont,
                                     NULL codigomarcaproducto,
                                     NULL grupoproductos,
                                     NULL codigonegocio,
                                     NULL codigotipooferta,
                                     ipc.tspa_oid_tipo_soli_pais tipo_soli,
                                     ipc.TAIM_OID_TASA_IMPU tipo_impu,
                                     '84' cod,
                                     CASE
                                       WHEN con.cod_tipo_prog IN ('B', 'C', 'R') THEN
                                        0
                                       ELSE
                                        cab.val_prec_cont_sin_impu_tota
                                     END importe84
                                FROM fac_docum_conta_cabec cab,
                                     ped_solic_cabec       psc,
                                     seg_pais              pai,
                                     seg_socie             soc,
                                     seg_subac             sbac,
                                     seg_acces             acce,
                                     seg_canal             cana,
                                     cra_perio             peri,
                                     seg_perio_corpo       pcor,
                                     seg_tipo_perio        tpe,
                                     int_param_conta_factu ipc,
                                     int_impor_conta_factu icf,
                                     ccc_cuent_conta       cc,
                                     inc_concu_tipo_prog   con
                               WHERE nvl(cab.val_tota_paga_loca,
                                         0) < 0
                                 AND cab.tido_oid_tipo_docu in (31,32,33)
                                 and cab.soca_oid_soli_cabe=psc.oid_soli_cabe
                                 AND cab.pais_oid_pais = pai.oid_pais
                                 AND cab.soci_oid_soci = soc.oid_soci
                                 AND ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                 AND ipc.val_tipo_asie = 'AB'
                                 AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                 AND cod_impo_cont = '84'
                                 AND cab.sbac_oid_sbac = sbac.oid_sbac
                                 AND cab.perd_oid_peri = peri.oid_peri
                                 AND peri.peri_oid_peri = pcor.oid_peri
                                 AND sbac.acce_oid_acce = acce.oid_acce
                                 AND acce.cana_oid_cana = cana.oid_cana
                                 AND pcor.tipe_oid_tipo_peri = tpe.oid_tipo_peri
                                 AND cab.ictp_oid_tipo_prog = con.oid_tipo_prog(+)
                                 AND cab.num_lote_cont IS NULL
                                 AND trunc(cab.fec_fact) <=
                                     to_date(psfecha,
                                             'DD/MM/YYYY')
                                 AND psc.TAIM_OID_TASA_IMPU =
                                     nvl(ipc.TAIM_OID_TASA_IMPU,
                                         decode((SELECT COUNT(*)
                                                  FROM int_param_conta_factu ipc,
                                                       int_impor_conta_factu icf,
                                                       ccc_cuent_conta       cc
                                                 WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                   AND ipc.val_tipo_asie = 'AB'
                                                   AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                   AND cod_impo_cont = '84'
                                                   AND TAIM_OID_TASA_IMPU = psc.TAIM_OID_TASA_IMPU),
                                                0,
                                                psc.TAIM_OID_TASA_IMPU,
                                                psc.TAIM_OID_TASA_IMPU))
                                 AND psc.TSPA_OID_TIPO_SOLI_PAIS =
                                     nvl(ipc.TSPA_OID_TIPO_SOLI_PAIS,
                                         decode((SELECT COUNT(*)
                                                  FROM int_param_conta_factu ipc,
                                                       int_impor_conta_factu icf,
                                                       ccc_cuent_conta       cc
                                                 WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                   AND ipc.val_tipo_asie = 'AB'
                                                   AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                   AND cod_impo_cont = '84'
                                                   AND TSPA_OID_TIPO_SOLI_PAIS = psc.TSPA_OID_TIPO_SOLI_PAIS),
                                                0,
                                                psc.TSPA_OID_TIPO_SOLI_PAIS,
                                                psc.TSPA_OID_TIPO_SOLI_PAIS))
                                 AND cab.perd_oid_peri in  (select oid_peri  from periodos))
                       GROUP BY pais,
                                codigopais,
                                fecha,
                                sociedad,
                                codigoempresa,
                                codigocanal,
                                codigoacceso,
                                subacceso,
                                codigosubacceso,
                                codigotipoperiodo,
                                periodo,
                                codigoperiodo,
                                ejerciciocomercial,
                                val_indi_debe_habe,
                                cod_cuen_cont,
                                codigomarcaproducto,
                                grupoproductos,
                                codigonegocio,
                                codigotipooferta,
                                tipo_soli,
                                tipo_impu,
                                cod))

            UNION

             (SELECT pais,
                     codigopais,
                     fecha,
                     sociedad,
                     codigoempresa,
                     codigocanal,
                     codigoacceso,
                     subacceso,
                     codigosubacceso,
                     codigotipoperiodo,
                     periodo,
                     codigoperiodo,
                     ejerciciocomercial,
                     CASE
                       WHEN importe < 0 THEN
                        decode(val_indi_debe_habe,
                               'D',
                               'H',
                               'D')
                       ELSE
                        val_indi_debe_habe
                     END val_indi_debe_habe,
                     'AB' tipo_asiento,
                     cod_cuen_cont,
                     codigomarcaproducto,
                     grupoproductos,
                     codigonegocio,
                     codigotipooferta,
                     tipo_soli,
                     tipo_impu,
                     cod,
                     abs(importe)
                FROM (SELECT pais,
                             codigopais,
                             fecha,
                             sociedad,
                             codigoempresa,
                             codigocanal,
                             codigoacceso,
                             subacceso,
                             codigosubacceso,
                             codigotipoperiodo,
                             periodo,
                             codigoperiodo,
                             ejerciciocomercial,
                             val_indi_debe_habe,
                             cod_cuen_cont,
                             codigomarcaproducto,
                             grupoproductos,
                             codigonegocio,
                             codigotipooferta,
                             tipo_soli,
                             tipo_impu,
                             cod,
                             SUM(importe88) importe
                        FROM (SELECT cab.pais_oid_pais pais,
                                     pai.cod_pais codigopais,
                                     cab.fec_fact fecha,
                                     cab.soci_oid_soci sociedad,
                                     soc.cod_soci codigoempresa,
                                     cana.cod_cana codigocanal,
                                     acce.cod_acce codigoacceso,
                                     cab.sbac_oid_sbac subacceso,
                                     sbac.cod_sbac codigosubacceso,
                                     tpe.cod_tipo_peri codigotipoperiodo,
                                     peri.peri_oid_peri periodo,
                                     pcor.cod_peri codigoperiodo,
                                     pcor.val_anio ejerciciocomercial,
                                     ipc.val_indi_debe_habe,
                                     cc.cod_cuen_cont,
                                     NULL codigomarcaproducto,
                                     NULL grupoproductos,
                                     NULL codigonegocio,
                                     NULL codigotipooferta,
                                     ipc.tspa_oid_tipo_soli_pais tipo_soli,
                                     ipc.TAIM_OID_TASA_IMPU tipo_impu,
                                     '88' cod,
                                     CASE
                                       WHEN con.cod_tipo_prog IN ('B', 'C', 'R') THEN
                                        0
                                       ELSE
                                        cab.imp_des3_sin_impu_tota
                                     END importe88
                                FROM fac_docum_conta_cabec cab,
                                     ped_solic_cabec       psc,
                                     seg_pais              pai,
                                     seg_socie             soc,
                                     seg_subac             sbac,
                                     seg_acces             acce,
                                     seg_canal             cana,
                                     cra_perio             peri,
                                     seg_perio_corpo       pcor,
                                     seg_tipo_perio        tpe,
                                     int_param_conta_factu ipc,
                                     int_impor_conta_factu icf,
                                     ccc_cuent_conta       cc,
                                     inc_concu_tipo_prog   con
                               WHERE nvl(cab.val_tota_paga_loca,
                                         0) < 0
                                 AND cab.tido_oid_tipo_docu in (31,32,33)
                                 and cab.soca_oid_soli_cabe=psc.oid_soli_cabe
                                 AND cab.pais_oid_pais = pai.oid_pais
                                 AND cab.soci_oid_soci = soc.oid_soci
                                 AND cab.sbac_oid_sbac = sbac.oid_sbac
                                 AND cab.perd_oid_peri = peri.oid_peri
                                 AND peri.peri_oid_peri = pcor.oid_peri
                                 AND sbac.acce_oid_acce = acce.oid_acce
                                 AND acce.cana_oid_cana = cana.oid_cana
                                 AND pcor.tipe_oid_tipo_peri = tpe.oid_tipo_peri
                                 AND ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                 AND ipc.val_tipo_asie = 'AB'
                                 AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                 AND cod_impo_cont = '88'
                                 AND cab.ictp_oid_tipo_prog = con.oid_tipo_prog(+)
                                 AND cab.num_lote_cont IS NULL
                                 AND trunc(cab.fec_fact) <=
                                     to_date(psfecha,
                                             'DD/MM/YYYY')
                                 AND psc.TAIM_OID_TASA_IMPU =
                                     nvl(ipc.TAIM_OID_TASA_IMPU,
                                         decode((SELECT COUNT(*)
                                                  FROM int_param_conta_factu ipc,
                                                       int_impor_conta_factu icf,
                                                       ccc_cuent_conta       cc
                                                 WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                   AND ipc.val_tipo_asie = 'AB'
                                                   AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                   AND cod_impo_cont = '88'
                                                   AND TAIM_OID_TASA_IMPU = psc.TAIM_OID_TASA_IMPU),
                                                0,
                                                psc.TAIM_OID_TASA_IMPU,
                                                psc.TAIM_OID_TASA_IMPU))
                                 AND psc.TSPA_OID_TIPO_SOLI_PAIS =
                                     nvl(ipc.TSPA_OID_TIPO_SOLI_PAIS,
                                         decode((SELECT COUNT(*)
                                                  FROM int_param_conta_factu ipc,
                                                       int_impor_conta_factu icf,
                                                       ccc_cuent_conta       cc
                                                 WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                   AND ipc.val_tipo_asie = 'AB'
                                                   AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                   AND cod_impo_cont = '88'
                                                   AND TSPA_OID_TIPO_SOLI_PAIS = psc.TSPA_OID_TIPO_SOLI_PAIS),
                                                0,
                                                psc.TSPA_OID_TIPO_SOLI_PAIS,
                                                psc.TSPA_OID_TIPO_SOLI_PAIS))
                                 AND cab.perd_oid_peri in  (select oid_peri  from periodos))
                       GROUP BY pais,
                                codigopais,
                                fecha,
                                sociedad,
                                codigoempresa,
                                codigocanal,
                                codigoacceso,
                                subacceso,
                                codigosubacceso,
                                codigotipoperiodo,
                                periodo,
                                codigoperiodo,
                                ejerciciocomercial,
                                val_indi_debe_habe,
                                cod_cuen_cont,
                                codigomarcaproducto,
                                grupoproductos,
                                codigonegocio,
                                codigotipooferta,
                                tipo_soli,
                                tipo_impu,
                                cod)) UNION

             (SELECT pais,
                     codigopais,
                     fecha,
                     sociedad,
                     codigoempresa,
                     codigocanal,
                     codigoacceso,
                     subacceso,
                     codigosubacceso,
                     codigotipoperiodo,
                     periodo,
                     codigoperiodo,
                     ejerciciocomercial,
                     CASE
                       WHEN importe < 0 THEN
                        decode(val_indi_debe_habe,
                               'D',
                               'H',
                               'D')
                       ELSE
                        val_indi_debe_habe
                     END val_indi_debe_habe,
                     'AB' tipo_asiento,
                     cod_cuen_cont,
                     codigomarcaproducto,
                     grupoproductos,
                     codigonegocio,
                     codigotipooferta,
                     tipo_soli,
                     tipo_impu,
                     cod,
                     abs(importe)
                FROM (SELECT pais,
                             codigopais,
                             fecha,
                             sociedad,
                             codigoempresa,
                             codigocanal,
                             codigoacceso,
                             subacceso,
                             codigosubacceso,
                             codigotipoperiodo,
                             periodo,
                             codigoperiodo,
                             ejerciciocomercial,
                             val_indi_debe_habe,
                             cod_cuen_cont,
                             codigomarcaproducto,
                             grupoproductos,
                             codigonegocio,
                             codigotipooferta,
                             tipo_soli,
                             tipo_impu,
                             cod,
                             SUM(importe90) importe
                        FROM (SELECT cab.pais_oid_pais pais,
                                     pai.cod_pais codigopais,
                                     cab.fec_fact fecha,
                                     cab.soci_oid_soci sociedad,
                                     soc.cod_soci codigoempresa,
                                     cana.cod_cana codigocanal,
                                     acce.cod_acce codigoacceso,
                                     cab.sbac_oid_sbac subacceso,
                                     sbac.cod_sbac codigosubacceso,
                                     tpe.cod_tipo_peri codigotipoperiodo,
                                     peri.peri_oid_peri periodo,
                                     pcor.cod_peri codigoperiodo,
                                     pcor.val_anio ejerciciocomercial,
                                     ipc.val_indi_debe_habe,
                                     cc.cod_cuen_cont,
                                     NULL codigomarcaproducto,
                                     NULL grupoproductos,
                                     NULL codigonegocio,
                                     NULL codigotipooferta,
                                     ipc.tspa_oid_tipo_soli_pais tipo_soli,
                                     ipc.TAIM_OID_TASA_IMPU tipo_impu,
                                     '90' cod,
                                     CASE
                                       WHEN con.cod_tipo_prog IN ('B', 'C', 'R') THEN
                                        0
                                       ELSE
                                        abs(cab.val_impo_iva_asum_empr)
                                     END importe90
                                FROM fac_docum_conta_cabec cab,
                                     ped_solic_cabec       psc,
                                     seg_pais              pai,
                                     seg_socie             soc,
                                     seg_subac             sbac,
                                     seg_acces             acce,
                                     seg_canal             cana,
                                     cra_perio             peri,
                                     seg_perio_corpo       pcor,
                                     seg_tipo_perio        tpe,
                                     int_param_conta_factu ipc,
                                     int_impor_conta_factu icf,
                                     ccc_cuent_conta       cc,
                                     inc_concu_tipo_prog   con
                               WHERE nvl(cab.val_tota_paga_loca,
                                         0) < 0
                                 AND cab.tido_oid_tipo_docu in (31,32,33)
                                 and cab.soca_oid_soli_cabe=psc.oid_soli_cabe
                                 AND cab.pais_oid_pais = pai.oid_pais
                                 AND cab.soci_oid_soci = soc.oid_soci
                                 AND cab.sbac_oid_sbac = sbac.oid_sbac
                                 AND cab.perd_oid_peri = peri.oid_peri
                                 AND peri.peri_oid_peri = pcor.oid_peri
                                 AND sbac.acce_oid_acce = acce.oid_acce
                                 AND acce.cana_oid_cana = cana.oid_cana
                                 AND pcor.tipe_oid_tipo_peri = tpe.oid_tipo_peri
                                 AND ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                 AND ipc.val_tipo_asie = 'AB'
                                 AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                 AND cod_impo_cont = '90'
                                 AND cab.ictp_oid_tipo_prog = con.oid_tipo_prog(+)
                                 AND cab.num_lote_cont IS NULL
                                 AND trunc(cab.fec_fact) <=
                                     to_date(psfecha,
                                             'DD/MM/YYYY')
                                 AND psc.TAIM_OID_TASA_IMPU =
                                     nvl(ipc.TAIM_OID_TASA_IMPU,
                                         decode((SELECT COUNT(*)
                                                  FROM int_param_conta_factu ipc,
                                                       int_impor_conta_factu icf,
                                                       ccc_cuent_conta       cc
                                                 WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                   AND ipc.val_tipo_asie = 'AB'
                                                   AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                   AND cod_impo_cont = '90'
                                                   AND TAIM_OID_TASA_IMPU = psc.TAIM_OID_TASA_IMPU),
                                                0,
                                                psc.TAIM_OID_TASA_IMPU,
                                                psc.TAIM_OID_TASA_IMPU))
                                 AND psc.TSPA_OID_TIPO_SOLI_PAIS =
                                     nvl(ipc.TSPA_OID_TIPO_SOLI_PAIS,
                                         decode((SELECT COUNT(*)
                                                  FROM int_param_conta_factu ipc,
                                                       int_impor_conta_factu icf,
                                                       ccc_cuent_conta       cc
                                                 WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                   AND ipc.val_tipo_asie = 'AB'
                                                   AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                   AND cod_impo_cont = '90'
                                                   AND TSPA_OID_TIPO_SOLI_PAIS = psc.TSPA_OID_TIPO_SOLI_PAIS),
                                                0,
                                                psc.TSPA_OID_TIPO_SOLI_PAIS,
                                                psc.TSPA_OID_TIPO_SOLI_PAIS))
                                 AND cab.perd_oid_peri in  (select oid_peri  from periodos))
                       GROUP BY pais,
                                codigopais,
                                fecha,
                                sociedad,
                                codigoempresa,
                                codigocanal,
                                codigoacceso,
                                subacceso,
                                codigosubacceso,
                                codigotipoperiodo,
                                periodo,
                                codigoperiodo,
                                ejerciciocomercial,
                                val_indi_debe_habe,
                                cod_cuen_cont,
                                codigomarcaproducto,
                                grupoproductos,
                                codigonegocio,
                                codigotipooferta,
                                tipo_soli,
                                tipo_impu,
                                cod)) UNION

             (SELECT pais,
                     codigopais,
                     fecha,
                     sociedad,
                     codigoempresa,
                     codigocanal,
                     codigoacceso,
                     subacceso,
                     codigosubacceso,
                     codigotipoperiodo,
                     periodo,
                     codigoperiodo,
                     ejerciciocomercial,
                     CASE
                       WHEN importe < 0 THEN
                        decode(val_indi_debe_habe,
                               'D',
                               'H',
                               'D')
                       ELSE
                        val_indi_debe_habe
                     END val_indi_debe_habe,
                     'AB' tipo_asiento,
                     cod_cuen_cont,
                     codigomarcaproducto,
                     grupoproductos,
                     codigonegocio,
                     codigotipooferta,
                     tipo_soli,
                     tipo_impu,
                     cod,
                     abs(importe)
                FROM (SELECT pais,
                             codigopais,
                             fecha,
                             sociedad,
                             codigoempresa,
                             codigocanal,
                             codigoacceso,
                             subacceso,
                             codigosubacceso,
                             codigotipoperiodo,
                             periodo,
                             codigoperiodo,
                             ejerciciocomercial,
                             val_indi_debe_habe,
                             cod_cuen_cont,
                             codigomarcaproducto,
                             grupoproductos,
                             codigonegocio,
                             codigotipooferta,
                             tipo_soli,
                             tipo_impu,
                             cod,
                             SUM(importe95) importe
                        FROM (SELECT cab.pais_oid_pais pais,
                                     pai.cod_pais codigopais,
                                     cab.fec_fact fecha,
                                     cab.soci_oid_soci sociedad,
                                     soc.cod_soci codigoempresa,
                                     cana.cod_cana codigocanal,
                                     acce.cod_acce codigoacceso,
                                     cab.sbac_oid_sbac subacceso,
                                     sbac.cod_sbac codigosubacceso,
                                     tpe.cod_tipo_peri codigotipoperiodo,
                                     peri.peri_oid_peri periodo,
                                     pcor.cod_peri codigoperiodo,
                                     pcor.val_anio ejerciciocomercial,
                                     ipc.val_indi_debe_habe,
                                     cc.cod_cuen_cont,
                                     NULL codigomarcaproducto,
                                     NULL grupoproductos,
                                     NULL codigonegocio,
                                     NULL codigotipooferta,
                                     ipc.tspa_oid_tipo_soli_pais tipo_soli,
                                     ipc.TAIM_OID_TASA_IMPU tipo_impu,
                                     '95' cod,
                                     CASE
                                       WHEN con.cod_tipo_prog IN ('B', 'C', 'R') THEN
                                        0
                                       ELSE
                                        nvl(cab.val_tota_gast_admi_sin_impu,0)
                                     END importe95
                                FROM fac_docum_conta_cabec cab,
                                     ped_solic_cabec       psc,
                                     seg_pais              pai,
                                     seg_socie             soc,
                                     seg_subac             sbac,
                                     seg_acces             acce,
                                     seg_canal             cana,
                                     cra_perio             peri,
                                     seg_perio_corpo       pcor,
                                     seg_tipo_perio        tpe,
                                     int_param_conta_factu ipc,
                                     int_impor_conta_factu icf,
                                     ccc_cuent_conta       cc,
                                     inc_concu_tipo_prog   con
                               WHERE nvl(cab.val_tota_paga_loca,
                                         0) < 0
                                 AND cab.tido_oid_tipo_docu in (31,32,33)
                                 and cab.soca_oid_soli_cabe=psc.oid_soli_cabe
                                 AND cab.pais_oid_pais = pai.oid_pais
                                 AND cab.soci_oid_soci = soc.oid_soci
                                 AND cab.sbac_oid_sbac = sbac.oid_sbac
                                 AND cab.perd_oid_peri = peri.oid_peri
                                 AND peri.peri_oid_peri = pcor.oid_peri
                                 AND sbac.acce_oid_acce = acce.oid_acce
                                 AND acce.cana_oid_cana = cana.oid_cana
                                 AND pcor.tipe_oid_tipo_peri = tpe.oid_tipo_peri
                                 AND ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                 AND ipc.val_tipo_asie = 'AB'
                                 AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                 AND cod_impo_cont = '95'
                                 AND cab.ictp_oid_tipo_prog = con.oid_tipo_prog(+)
                                 AND cab.num_lote_cont IS NULL
                                 AND trunc(cab.fec_fact) <=
                                     to_date(psfecha,
                                             'DD/MM/YYYY')
                                 AND psc.TAIM_OID_TASA_IMPU =
                                     nvl(ipc.TAIM_OID_TASA_IMPU,
                                         decode((SELECT COUNT(*)
                                                  FROM int_param_conta_factu ipc,
                                                       int_impor_conta_factu icf,
                                                       ccc_cuent_conta       cc
                                                 WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                   AND ipc.val_tipo_asie = 'AB'
                                                   AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                   AND cod_impo_cont = '95'
                                                   AND TAIM_OID_TASA_IMPU = psc.TAIM_OID_TASA_IMPU),
                                                0,
                                                psc.TAIM_OID_TASA_IMPU,
                                                psc.TAIM_OID_TASA_IMPU))
                                 AND psc.TSPA_OID_TIPO_SOLI_PAIS =
                                     nvl(ipc.TSPA_OID_TIPO_SOLI_PAIS,
                                         decode((SELECT COUNT(*)
                                                  FROM int_param_conta_factu ipc,
                                                       int_impor_conta_factu icf,
                                                       ccc_cuent_conta       cc
                                                 WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                   AND ipc.val_tipo_asie = 'AB'
                                                   AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                   AND cod_impo_cont = '95'
                                                   AND TSPA_OID_TIPO_SOLI_PAIS = psc.TSPA_OID_TIPO_SOLI_PAIS),
                                                0,
                                                psc.TSPA_OID_TIPO_SOLI_PAIS,
                                                psc.TSPA_OID_TIPO_SOLI_PAIS))
                                 AND cab.perd_oid_peri in  (select oid_peri  from periodos))
                       GROUP BY pais,
                                codigopais,
                                fecha,
                                sociedad,
                                codigoempresa,
                                codigocanal,
                                codigoacceso,
                                subacceso,
                                codigosubacceso,
                                codigotipoperiodo,
                                periodo,
                                codigoperiodo,
                                ejerciciocomercial,
                                val_indi_debe_habe,
                                cod_cuen_cont,
                                codigomarcaproducto,
                                grupoproductos,
                                codigonegocio,
                                codigotipooferta,
                                tipo_soli,
                                tipo_impu,
                                cod)) UNION

             (SELECT pais,
                     codigopais,
                     fecha,
                     sociedad,
                     codigoempresa,
                     codigocanal,
                     codigoacceso,
                     subacceso,
                     codigosubacceso,
                     codigotipoperiodo,
                     periodo,
                     codigoperiodo,
                     ejerciciocomercial,
                     CASE
                       WHEN importe < 0 THEN
                        decode(val_indi_debe_habe,
                               'D',
                               'H',
                               'D')
                       ELSE
                        val_indi_debe_habe
                     END val_indi_debe_habe,
                     'AB' tipo_asiento,
                     cod_cuen_cont,
                     codigomarcaproducto,
                     grupoproductos,
                     codigonegocio,
                     codigotipooferta,
                     tipo_soli,
                     tipo_impu,
                     cod,
                     abs(importe)
                FROM (SELECT pais,
                             codigopais,
                             fecha,
                             sociedad,
                             codigoempresa,
                             codigocanal,
                             codigoacceso,
                             subacceso,
                             codigosubacceso,
                             codigotipoperiodo,
                             periodo,
                             codigoperiodo,
                             ejerciciocomercial,
                             val_indi_debe_habe,
                             cod_cuen_cont,
                             codigomarcaproducto,
                             grupoproductos,
                             codigonegocio,
                             codigotipooferta,
                             tipo_soli,
                             tipo_impu,
                             cod,
                             SUM(importe96) importe
                        FROM (SELECT cab.pais_oid_pais pais,
                                     pai.cod_pais codigopais,
                                     cab.fec_fact fecha,
                                     cab.soci_oid_soci sociedad,
                                     soc.cod_soci codigoempresa,
                                     cana.cod_cana codigocanal,
                                     acce.cod_acce codigoacceso,
                                     cab.sbac_oid_sbac subacceso,
                                     sbac.cod_sbac codigosubacceso,
                                     tpe.cod_tipo_peri codigotipoperiodo,
                                     peri.peri_oid_peri periodo,
                                     pcor.cod_peri codigoperiodo,
                                     pcor.val_anio ejerciciocomercial,
                                     ipc.val_indi_debe_habe,
                                     cc.cod_cuen_cont,
                                     NULL codigomarcaproducto,
                                     NULL grupoproductos,
                                     NULL codigonegocio,
                                     NULL codigotipooferta,
                                     ipc.tspa_oid_tipo_soli_pais tipo_soli,
                                     ipc.TAIM_OID_TASA_IMPU tipo_impu,
                                     '96' cod,
                                     CASE
                                       WHEN con.cod_tipo_prog IN ('B', 'C', 'R') THEN
                                        0
                                       ELSE
                                        nvl(cab.val_impo_rete_desc,0)
                                     END importe96
                                FROM fac_docum_conta_cabec cab,
                                     ped_solic_cabec       psc,
                                     seg_pais              pai,
                                     seg_socie             soc,
                                     seg_subac             sbac,
                                     seg_acces             acce,
                                     seg_canal             cana,
                                     cra_perio             peri,
                                     seg_perio_corpo       pcor,
                                     seg_tipo_perio        tpe,
                                     int_param_conta_factu ipc,
                                     int_impor_conta_factu icf,
                                     ccc_cuent_conta       cc,
                                     inc_concu_tipo_prog   con
                               WHERE nvl(cab.val_tota_paga_loca,
                                         0) < 0
                                 AND cab.tido_oid_tipo_docu in (31,32,33)
                                 and cab.soca_oid_soli_cabe=psc.oid_soli_cabe
                                 AND cab.pais_oid_pais = pai.oid_pais
                                 AND cab.soci_oid_soci = soc.oid_soci
                                 AND cab.sbac_oid_sbac = sbac.oid_sbac
                                 AND cab.perd_oid_peri = peri.oid_peri
                                 AND peri.peri_oid_peri = pcor.oid_peri
                                 AND sbac.acce_oid_acce = acce.oid_acce
                                 AND acce.cana_oid_cana = cana.oid_cana
                                 AND pcor.tipe_oid_tipo_peri = tpe.oid_tipo_peri
                                 AND ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                 AND ipc.val_tipo_asie = 'AB'
                                 AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                 AND cod_impo_cont = '96'
                                 AND cab.ictp_oid_tipo_prog = con.oid_tipo_prog(+)
                                 AND cab.num_lote_cont IS NULL
                                 AND trunc(cab.fec_fact) <=
                                     to_date(psfecha,
                                             'DD/MM/YYYY')
                                 AND psc.TAIM_OID_TASA_IMPU =
                                     nvl(ipc.TAIM_OID_TASA_IMPU,
                                         decode((SELECT COUNT(*)
                                                  FROM int_param_conta_factu ipc,
                                                       int_impor_conta_factu icf,
                                                       ccc_cuent_conta       cc
                                                 WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                   AND ipc.val_tipo_asie = 'AB'
                                                   AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                   AND cod_impo_cont = '96'
                                                   AND TAIM_OID_TASA_IMPU = psc.TAIM_OID_TASA_IMPU),
                                                0,
                                                psc.TAIM_OID_TASA_IMPU,
                                                psc.TAIM_OID_TASA_IMPU))
                                 AND psc.TSPA_OID_TIPO_SOLI_PAIS =
                                     nvl(ipc.TSPA_OID_TIPO_SOLI_PAIS,
                                         decode((SELECT COUNT(*)
                                                  FROM int_param_conta_factu ipc,
                                                       int_impor_conta_factu icf,
                                                       ccc_cuent_conta       cc
                                                 WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                   AND ipc.val_tipo_asie = 'AB'
                                                   AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                   AND cod_impo_cont = '96'
                                                   AND TSPA_OID_TIPO_SOLI_PAIS = psc.TSPA_OID_TIPO_SOLI_PAIS),
                                                0,
                                                psc.TSPA_OID_TIPO_SOLI_PAIS,
                                                psc.TSPA_OID_TIPO_SOLI_PAIS))
                                 AND cab.perd_oid_peri in  (select oid_peri  from periodos))
                       GROUP BY pais,
                                codigopais,
                                fecha,
                                sociedad,
                                codigoempresa,
                                codigocanal,
                                codigoacceso,
                                subacceso,
                                codigosubacceso,
                                codigotipoperiodo,
                                periodo,
                                codigoperiodo,
                                ejerciciocomercial,
                                val_indi_debe_habe,
                                cod_cuen_cont,
                                codigomarcaproducto,
                                grupoproductos,
                                codigonegocio,
                                codigotipooferta,
                                tipo_soli,
                                tipo_impu,
                                cod))


            UNION

            ---- DETALLE

              SELECT pais,
                     codigopais,
                     fecha,
                     sociedad,
                     codigoempresa,
                     codigocanal,
                     codigoacceso,
                     subacceso,
                     codigosubacceso,
                     codigotipoperiodo,
                     periodo,
                     codigoperiodo,
                     ejerciciocomercial,
                     val_indi_debe_habe,
                     'AB' tipo_asiento,
                     cod_cuen_cont,
                     codigomarcaproducto,
                     grupoproductos,
                     codigonegocio,
                     codigotipooferta,
                     tipo_soli,
                     tipo_impu,
                     '01',
                     SUM(abs(importe))
                FROM (SELECT cab.pais_oid_pais pais,
                             pai.cod_pais codigopais,
                             cab.fec_fact fecha,
                             cab.soci_oid_soci sociedad,
                             soc.cod_soci codigoempresa,
                             cana.cod_cana codigocanal,
                             acce.cod_acce codigoacceso,
                             cab.sbac_oid_sbac subacceso,
                             sbac.cod_sbac codigosubacceso,
                             tpe.cod_tipo_peri codigotipoperiodo,
                             peri.peri_oid_peri periodo,
                             pcor.cod_peri codigoperiodo,
                             pcor.val_anio ejerciciocomercial,
                             CASE
                               WHEN lin.val_prec_sin_impu_tota_loca -
                                    decode((SELECT nvl(par.ind_impu_prod_naci,
                                                      0)
                                             FROM seg_param_inter_pais par),
                                           1,
                                           nvl(decode(lin.val_prec_cata_unit_loca,0,0,lin.imp_impu_tota_prod_naci),
                                               0),
                                           0) < 0 THEN
                                decode(xx.val_indi_debe_habe,
                                       'D',
                                       'H',
                                       'D')
                               ELSE
                                xx.val_indi_debe_habe
                             END val_indi_debe_habe,
                             xx.cod_cuen_cont,
                             mpro.cod_marc_prod codigomarcaproducto,
                             pro.val_grup_arti grupoproductos,
                             nego.cod_nego codigonegocio,
                             tof.cod_tipo_ofer codigotipooferta,
                             xx.tspa_oid_tipo_soli_pais tipo_soli,
                             xx.TAIM_OID_TASA_IMPU tipo_impu,
                             CASE
                               WHEN con.cod_tipo_prog IN ('B', 'C', 'R') THEN
                                0
                               ELSE
                                (lin.val_prec_sin_impu_tota_loca -
                                decode((SELECT nvl(par.ind_impu_prod_naci,
                                                   0)
                                          FROM seg_param_inter_pais par),
                                        1,
                                        nvl(decode(lin.val_prec_cata_unit_loca,0,0,lin.imp_impu_tota_prod_naci),
                                            0),
                                        0))
                             END importe
                        FROM fac_docum_conta_cabec cab,
                             fac_docum_conta_linea lin,
                             seg_pais pai,
                             seg_socie soc,
                             mae_produ pro,
                             ped_solic_cabec psc,
                             ped_solic_posic sop,
                             pre_ofert_detal dof,
                             seg_subac sbac,
                             seg_acces acce,
                             seg_canal cana,
                             cra_perio peri,
                             seg_perio_corpo pcor,
                             seg_tipo_perio tpe,
                             seg_marca_produ mpro,
                             mae_negoc nego,
                             pre_tipo_ofert tof,
                             inc_concu_tipo_prog con,
                             (SELECT cod_cuen_cont,
                                     val_indi_debe_habe,
                                     val_grup_arti,
                                     tspa_oid_tipo_soli_pais,
                                     taim_oid_tasa_impu
                                FROM int_param_conta_factu ipc,
                                     int_impor_conta_factu icf,
                                     ccc_cuent_conta       cc
                               WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                 AND ipc.val_tipo_asie = 'AB'
                                 AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                 AND cod_impo_cont = '01') xx
                       WHERE nvl(cab.val_tota_paga_loca,
                                 0) < 0
                         AND cab.tido_oid_tipo_docu in (31,32,33)
                         AND lin.dcca_oid_cabe = cab.oid_cabe
                         AND cab.pais_oid_pais = pai.oid_pais
                         AND cab.soci_oid_soci = soc.oid_soci
                         AND lin.prod_oid_prod = pro.oid_prod
                         AND sop.soca_oid_soli_cabe = psc.oid_soli_cabe
                         AND lin.sopo_oid_soli_posi = sop.oid_soli_posi
                         AND sop.ofde_oid_deta_ofer = dof.oid_deta_ofer(+)
                         AND cab.sbac_oid_sbac = sbac.oid_sbac
                         AND cab.perd_oid_peri = peri.oid_peri
                         AND peri.peri_oid_peri = pcor.oid_peri
                         AND sbac.acce_oid_acce = acce.oid_acce
                         AND acce.cana_oid_cana = cana.oid_cana
                         AND pcor.tipe_oid_tipo_peri = tpe.oid_tipo_peri
                         AND pro.mapr_oid_marc_prod = mpro.oid_marc_prod(+)
                         AND pro.nego_oid_nego = nego.oid_nego(+)
                         AND dof.tofe_oid_tipo_ofer = tof.oid_tipo_ofer(+)
                         AND pro.val_grup_arti =
                             nvl(xx.val_grup_arti,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'AB'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '01'
                                           AND val_grup_arti = pro.val_grup_arti
                                           --------
                                           and ((SELECT COUNT(*)
                                                  FROM int_param_conta_factu ipc,
                                                       int_impor_conta_factu icf,
                                                       ccc_cuent_conta       cc
                                                 WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                   AND ipc.val_tipo_asie = 'AB'
                                                   AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                   AND cod_impo_cont = '01'
                                                   AND TSPA_OID_TIPO_SOLI_PAIS = psc.TSPA_OID_TIPO_SOLI_PAIS) = 0)
                                           ),
                                        0,
                                        pro.val_grup_arti,
                                        'XXXX'))
                         AND sop.TAIM_OID_TASA_IMPU =
                             nvl(xx.TAIM_OID_TASA_IMPU,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'AB'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '01'
                                           AND TAIM_OID_TASA_IMPU = sop.TAIM_OID_TASA_IMPU),
                                        0,
                                        sop.TAIM_OID_TASA_IMPU,
                                        0/*sop.TAIM_OID_TASA_IMPU*/))
                         AND psc.TSPA_OID_TIPO_SOLI_PAIS =
                             nvl(xx.TSPA_OID_TIPO_SOLI_PAIS,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'AB'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '01'
                                           AND TSPA_OID_TIPO_SOLI_PAIS = psc.TSPA_OID_TIPO_SOLI_PAIS),
                                        0,
                                        psc.TSPA_OID_TIPO_SOLI_PAIS,
                                        0/*psc.TSPA_OID_TIPO_SOLI_PAIS*/))
                         AND cab.ictp_oid_tipo_prog = con.oid_tipo_prog(+)
                         AND cab.num_lote_cont IS NULL
                         AND trunc(cab.fec_fact) <= to_date(psfecha,
                                                            'DD/MM/YYYY')
                         AND cab.perd_oid_peri in  (select oid_peri  from periodos))
               WHERE importe <> 0
                 AND cod_cuen_cont IS NOT NULL
               GROUP BY pais,
                        codigopais,
                        fecha,
                        sociedad,
                        codigoempresa,
                        codigocanal,
                        codigoacceso,
                        subacceso,
                        codigosubacceso,
                        codigotipoperiodo,
                        periodo,
                        codigoperiodo,
                        ejerciciocomercial,
                        val_indi_debe_habe,
                        cod_cuen_cont,
                        codigomarcaproducto,
                        grupoproductos,
                        codigonegocio,
                        codigotipooferta,
                        tipo_soli,
                        tipo_impu,
                        '01'

              UNION

              SELECT pais,
                     codigopais,
                     fecha,
                     sociedad,
                     codigoempresa,
                     codigocanal,
                     codigoacceso,
                     subacceso,
                     codigosubacceso,
                     codigotipoperiodo,
                     periodo,
                     codigoperiodo,
                     ejerciciocomercial,
                     val_indi_debe_habe,
                     'AB' tipo_asiento,
                     cod_cuen_cont,
                     codigomarcaproducto,
                     grupoproductos,
                     codigonegocio,
                     codigotipooferta,
                     tipo_soli,
                     tipo_impu,
                     '03',
                     SUM(abs(importe))
                FROM (SELECT cab.pais_oid_pais pais,
                             pai.cod_pais codigopais,
                             cab.fec_fact fecha,
                             cab.soci_oid_soci sociedad,
                             soc.cod_soci codigoempresa,
                             cana.cod_cana codigocanal,
                             acce.cod_acce codigoacceso,
                             cab.sbac_oid_sbac subacceso,
                             sbac.cod_sbac codigosubacceso,
                             tpe.cod_tipo_peri codigotipoperiodo,
                             peri.peri_oid_peri periodo,
                             pcor.cod_peri codigoperiodo,
                             pcor.val_anio ejerciciocomercial,
                             CASE
                               WHEN decode((SELECT nvl(par.ind_impu_prod_grat,
                                                      0)
                                             FROM seg_param_inter_pais par),
                                           1,
                                           (lin.imp_impu_tota_loca *
                                           decode(lin.val_prec_cata_unit_loca,
                                                   0,
                                                   1,
                                                   0)),
                                           lin.imp_impu_tota_loca) < 0 THEN
                                decode(xx.val_indi_debe_habe,
                                       'D',
                                       'H',
                                       'D')
                               ELSE
                                xx.val_indi_debe_habe
                             END val_indi_debe_habe,
                             xx.cod_cuen_cont,
                             mpro.cod_marc_prod codigomarcaproducto,
                             pro.val_grup_arti grupoproductos,
                             nego.cod_nego codigonegocio,
                             tof.cod_tipo_ofer codigotipooferta,
                             xx.tspa_oid_tipo_soli_pais tipo_soli,
                             xx.TAIM_OID_TASA_IMPU tipo_impu,
                             decode((SELECT nvl(par.ind_impu_prod_grat,
                                               0)
                                      FROM seg_param_inter_pais par),
                                    1,
                                    (lin.imp_impu_tota_loca * decode(lin.val_prec_cata_unit_loca,
                                                                     0,
                                                                     1,
                                                                     0)),
                                    lin.imp_impu_tota_loca) importe
                        FROM fac_docum_conta_cabec cab,
                             fac_docum_conta_linea lin,
                             seg_pais pai,
                             seg_socie soc,
                             mae_produ pro,
                             ped_solic_cabec psc,
                             ped_solic_posic sop,
                             pre_ofert_detal dof,
                             seg_subac sbac,
                             seg_acces acce,
                             seg_canal cana,
                             cra_perio peri,
                             seg_perio_corpo pcor,
                             seg_tipo_perio tpe,
                             seg_marca_produ mpro,
                             mae_negoc nego,
                             pre_tipo_ofert tof,
                             inc_concu_tipo_prog con,
                             (SELECT cod_cuen_cont,
                                     val_indi_debe_habe,
                                     val_grup_arti,
                                     tspa_oid_tipo_soli_pais,
                                     taim_oid_tasa_impu
                                FROM int_param_conta_factu ipc,
                                     int_impor_conta_factu icf,
                                     ccc_cuent_conta       cc
                               WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                 AND ipc.val_tipo_asie = 'AB'
                                 AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                 AND cod_impo_cont = '03') xx
                       WHERE nvl(cab.val_tota_paga_loca,
                                 0) < 0
                         AND cab.tido_oid_tipo_docu in (31,32,33)
                         AND lin.dcca_oid_cabe = cab.oid_cabe
                         AND cab.pais_oid_pais = pai.oid_pais
                         AND cab.soci_oid_soci = soc.oid_soci
                         AND lin.prod_oid_prod = pro.oid_prod
                         AND sop.soca_oid_soli_cabe = psc.oid_soli_cabe
                         AND lin.sopo_oid_soli_posi = sop.oid_soli_posi
                         AND sop.ofde_oid_deta_ofer = dof.oid_deta_ofer(+)
                         AND cab.sbac_oid_sbac = sbac.oid_sbac
                         AND cab.perd_oid_peri = peri.oid_peri
                         AND peri.peri_oid_peri = pcor.oid_peri
                         AND sbac.acce_oid_acce = acce.oid_acce
                         AND acce.cana_oid_cana = cana.oid_cana
                         AND pcor.tipe_oid_tipo_peri = tpe.oid_tipo_peri
                         AND pro.mapr_oid_marc_prod = mpro.oid_marc_prod(+)
                         AND pro.nego_oid_nego = nego.oid_nego(+)
                         AND dof.tofe_oid_tipo_ofer = tof.oid_tipo_ofer(+)
                         AND cab.ictp_oid_tipo_prog = con.oid_tipo_prog(+)
                         AND pro.val_grup_arti =
                             nvl(xx.val_grup_arti,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'AB'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '03'
                                           AND val_grup_arti = pro.val_grup_arti),
                                        0,
                                        pro.val_grup_arti,
                                        'XXXX'))
                         AND sop.TAIM_OID_TASA_IMPU =
                             nvl(xx.TAIM_OID_TASA_IMPU,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'AB'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '03'
                                           AND TAIM_OID_TASA_IMPU = sop.TAIM_OID_TASA_IMPU),
                                        0,
                                        sop.TAIM_OID_TASA_IMPU,
                                        0/*sop.TAIM_OID_TASA_IMPU*/))
                         AND psc.TSPA_OID_TIPO_SOLI_PAIS =
                             nvl(xx.TSPA_OID_TIPO_SOLI_PAIS,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'AB'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '03'
                                           AND TSPA_OID_TIPO_SOLI_PAIS = psc.TSPA_OID_TIPO_SOLI_PAIS),
                                        0,
                                        psc.TSPA_OID_TIPO_SOLI_PAIS,
                                        0/*psc.TSPA_OID_TIPO_SOLI_PAIS*/))
                         AND cab.num_lote_cont IS NULL
                         AND trunc(cab.fec_fact) <= to_date(psfecha,
                                                            'DD/MM/YYYY')
                         AND cab.perd_oid_peri in  (select oid_peri  from periodos))
               WHERE importe <> 0
                 AND cod_cuen_cont IS NOT NULL
               GROUP BY pais,
                        codigopais,
                        fecha,
                        sociedad,
                        codigoempresa,
                        codigocanal,
                        codigoacceso,
                        subacceso,
                        codigosubacceso,
                        codigotipoperiodo,
                        periodo,
                        codigoperiodo,
                        ejerciciocomercial,
                        val_indi_debe_habe,
                        cod_cuen_cont,
                        codigomarcaproducto,
                        grupoproductos,
                        codigonegocio,
                        codigotipooferta,
                        tipo_soli,
                        tipo_impu,
                        '03'

              UNION

              SELECT pais,
                     codigopais,
                     fecha,
                     sociedad,
                     codigoempresa,
                     codigocanal,
                     codigoacceso,
                     subacceso,
                     codigosubacceso,
                     codigotipoperiodo,
                     periodo,
                     codigoperiodo,
                     ejerciciocomercial,
                     val_indi_debe_habe,
                     'AB' tipo_asiento,
                     cod_cuen_cont,
                     codigomarcaproducto,
                     grupoproductos,
                     codigonegocio,
                     codigotipooferta,
                     tipo_soli,
                     tipo_impu,
                     '04',
                     SUM(abs(importe))
                FROM (SELECT cab.pais_oid_pais pais,
                             pai.cod_pais codigopais,
                             cab.fec_fact fecha,
                             cab.soci_oid_soci sociedad,
                             soc.cod_soci codigoempresa,
                             cana.cod_cana codigocanal,
                             acce.cod_acce codigoacceso,
                             cab.sbac_oid_sbac subacceso,
                             sbac.cod_sbac codigosubacceso,
                             tpe.cod_tipo_peri codigotipoperiodo,
                             peri.peri_oid_peri periodo,
                             pcor.cod_peri codigoperiodo,
                             pcor.val_anio ejerciciocomercial,
                             CASE
                               WHEN --decode((SELECT nvl(par.ind_impu_prod_naci,
                                    --                  0)
                                    --         FROM seg_param_inter_pais par),
                                    --       1,
                                           nvl(lin.imp_impu_tota_prod_naci,0)--,
                                          -- 0)
                                           < 0 THEN
                                decode(xx.val_indi_debe_habe,
                                       'D',
                                       'H',
                                       'D')
                               ELSE
                                xx.val_indi_debe_habe
                             END val_indi_debe_habe,
                             xx.cod_cuen_cont,
                             mpro.cod_marc_prod codigomarcaproducto,
                             pro.val_grup_arti grupoproductos,
                             nego.cod_nego codigonegocio,
                             tof.cod_tipo_ofer codigotipooferta,
                             xx.tspa_oid_tipo_soli_pais tipo_soli,
                             xx.TAIM_OID_TASA_IMPU tipo_impu,
                             --decode((SELECT nvl(par.ind_impu_prod_naci,
                             --                  0)
                             --         FROM seg_param_inter_pais par),
                             --       1,
                                    nvl(decode(lin.val_prec_cata_unit_loca,0,0,lin.imp_impu_tota_prod_naci),
                                        0)--,
                                    --0)
                                    importe
                        FROM fac_docum_conta_cabec cab,
                             fac_docum_conta_linea lin,
                             seg_pais pai,
                             seg_socie soc,
                             mae_produ pro,
                             ped_solic_cabec psc,
                             ped_solic_posic sop,
                             pre_ofert_detal dof,
                             seg_subac sbac,
                             seg_acces acce,
                             seg_canal cana,
                             cra_perio peri,
                             seg_perio_corpo pcor,
                             seg_tipo_perio tpe,
                             seg_marca_produ mpro,
                             mae_negoc nego,
                             pre_tipo_ofert tof,
                             inc_concu_tipo_prog con,
                             (SELECT cod_cuen_cont,
                                     val_indi_debe_habe,
                                     val_grup_arti,
                                     tspa_oid_tipo_soli_pais,
                                     taim_oid_tasa_impu
                                FROM int_param_conta_factu ipc,
                                     int_impor_conta_factu icf,
                                     ccc_cuent_conta       cc
                               WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                 AND ipc.val_tipo_asie = 'AB'
                                 AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                 AND cod_impo_cont = '04') xx
                       WHERE nvl(cab.val_tota_paga_loca,
                                 0) < 0
                         AND cab.tido_oid_tipo_docu in (31,32,33)
                         AND lin.dcca_oid_cabe = cab.oid_cabe
                         AND cab.pais_oid_pais = pai.oid_pais
                         AND cab.soci_oid_soci = soc.oid_soci
                         AND lin.prod_oid_prod = pro.oid_prod
                         AND sop.soca_oid_soli_cabe = psc.oid_soli_cabe
                         AND lin.sopo_oid_soli_posi = sop.oid_soli_posi
                         AND sop.ofde_oid_deta_ofer = dof.oid_deta_ofer(+)
                         AND cab.sbac_oid_sbac = sbac.oid_sbac
                         AND cab.perd_oid_peri = peri.oid_peri
                         AND peri.peri_oid_peri = pcor.oid_peri
                         AND sbac.acce_oid_acce = acce.oid_acce
                         AND acce.cana_oid_cana = cana.oid_cana
                         AND pcor.tipe_oid_tipo_peri = tpe.oid_tipo_peri
                         AND pro.mapr_oid_marc_prod = mpro.oid_marc_prod(+)
                         AND pro.nego_oid_nego = nego.oid_nego(+)
                         AND dof.tofe_oid_tipo_ofer = tof.oid_tipo_ofer(+)
                         AND pro.val_grup_arti =
                             nvl(xx.val_grup_arti,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'AB'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '04'
                                           AND val_grup_arti = pro.val_grup_arti),
                                        0,
                                        pro.val_grup_arti,
                                        'XXXX'))
                         AND sop.TAIM_OID_TASA_IMPU =
                             nvl(xx.TAIM_OID_TASA_IMPU,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'AB'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '04'
                                           AND TAIM_OID_TASA_IMPU = sop.TAIM_OID_TASA_IMPU),
                                        0,
                                        sop.TAIM_OID_TASA_IMPU,
                                        0/*sop.TAIM_OID_TASA_IMPU*/))
                         AND psc.TSPA_OID_TIPO_SOLI_PAIS =
                             nvl(xx.TSPA_OID_TIPO_SOLI_PAIS,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'AB'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '04'
                                           AND TSPA_OID_TIPO_SOLI_PAIS = psc.TSPA_OID_TIPO_SOLI_PAIS),
                                        0,
                                        psc.TSPA_OID_TIPO_SOLI_PAIS,
                                        0/*psc.TSPA_OID_TIPO_SOLI_PAIS*/))
                         AND cab.ictp_oid_tipo_prog = con.oid_tipo_prog(+)
                         AND cab.num_lote_cont IS NULL
                         AND trunc(cab.fec_fact) <= to_date(psfecha,
                                                            'DD/MM/YYYY')
                         AND cab.perd_oid_peri in  (select oid_peri  from periodos))
               WHERE importe <> 0
                 AND cod_cuen_cont IS NOT NULL
               GROUP BY pais,
                        codigopais,
                        fecha,
                        sociedad,
                        codigoempresa,
                        codigocanal,
                        codigoacceso,
                        subacceso,
                        codigosubacceso,
                        codigotipoperiodo,
                        periodo,
                        codigoperiodo,
                        ejerciciocomercial,
                        val_indi_debe_habe,
                        cod_cuen_cont,
                        codigomarcaproducto,
                        grupoproductos,
                        codigonegocio,
                        codigotipooferta,
                        tipo_soli,
                        tipo_impu,
                        '04'
              UNION

              SELECT pais,
                     codigopais,
                     fecha,
                     sociedad,
                     codigoempresa,
                     codigocanal,
                     codigoacceso,
                     subacceso,
                     codigosubacceso,
                     codigotipoperiodo,
                     periodo,
                     codigoperiodo,
                     ejerciciocomercial,
                     val_indi_debe_habe,
                     'AB' tipo_asiento,
                     cod_cuen_cont,
                     codigomarcaproducto,
                     grupoproductos,
                     codigonegocio,
                     codigotipooferta,
                     tipo_soli,
                     tipo_impu,
                     '06',
                     SUM(abs(importe))
                FROM (SELECT cab.pais_oid_pais pais,
                             pai.cod_pais codigopais,
                             cab.fec_fact fecha,
                             cab.soci_oid_soci sociedad,
                             soc.cod_soci codigoempresa,
                             cana.cod_cana codigocanal,
                             acce.cod_acce codigoacceso,
                             cab.sbac_oid_sbac subacceso,
                             sbac.cod_sbac codigosubacceso,
                             tpe.cod_tipo_peri codigotipoperiodo,
                             peri.peri_oid_peri periodo,
                             pcor.cod_peri codigoperiodo,
                             pcor.val_anio ejerciciocomercial,
                             CASE
                               WHEN --decode((SELECT nvl(par.ind_impu_prod_naci,
                                    --                  0)
                                    --         FROM seg_param_inter_pais par),
                                    --       1,
                                           nvl(decode(lin.val_prec_cata_unit_loca,0,lin.imp_impu_tota_prod_naci*0.12,0),
                                               0)--,
                                          -- 0)
                                           < 0 THEN
                                decode(xx.val_indi_debe_habe,
                                       'D',
                                       'H',
                                       'D')
                               ELSE
                                xx.val_indi_debe_habe
                             END val_indi_debe_habe,
                             xx.cod_cuen_cont,
                             mpro.cod_marc_prod codigomarcaproducto,
                             pro.val_grup_arti grupoproductos,
                             nego.cod_nego codigonegocio,
                             tof.cod_tipo_ofer codigotipooferta,
                             xx.tspa_oid_tipo_soli_pais tipo_soli,
                             xx.TAIM_OID_TASA_IMPU tipo_impu,
                             --decode((SELECT nvl(par.ind_impu_prod_naci,
                             --                  0)
                             --         FROM seg_param_inter_pais par),
                             --       1,
                                           nvl(decode(lin.val_prec_cata_unit_loca,0,lin.imp_impu_tota_prod_naci*0.12,0),
                                               0)--,
                                    --0)
                                    importe
                        FROM fac_docum_conta_cabec cab,
                             fac_docum_conta_linea lin,
                             seg_pais pai,
                             seg_socie soc,
                             mae_produ pro,
                             ped_solic_cabec psc,
                             ped_solic_posic sop,
                             pre_ofert_detal dof,
                             seg_subac sbac,
                             seg_acces acce,
                             seg_canal cana,
                             cra_perio peri,
                             seg_perio_corpo pcor,
                             seg_tipo_perio tpe,
                             seg_marca_produ mpro,
                             mae_negoc nego,
                             pre_tipo_ofert tof,
                             inc_concu_tipo_prog con,
                             (SELECT cod_cuen_cont,
                                     val_indi_debe_habe,
                                     val_grup_arti,
                                     tspa_oid_tipo_soli_pais,
                                     taim_oid_tasa_impu
                                FROM int_param_conta_factu ipc,
                                     int_impor_conta_factu icf,
                                     ccc_cuent_conta       cc
                               WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                 AND ipc.val_tipo_asie = 'AB'
                                 AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                 AND cod_impo_cont = '06') xx
                       WHERE nvl(cab.val_tota_paga_loca,
                                 0) < 0
                         AND cab.tido_oid_tipo_docu in (31,32,33)
                         AND lin.dcca_oid_cabe = cab.oid_cabe
                         AND cab.pais_oid_pais = pai.oid_pais
                         AND cab.soci_oid_soci = soc.oid_soci
                         AND lin.prod_oid_prod = pro.oid_prod
                         AND sop.soca_oid_soli_cabe = psc.oid_soli_cabe
                         AND lin.sopo_oid_soli_posi = sop.oid_soli_posi
                         AND sop.ofde_oid_deta_ofer = dof.oid_deta_ofer(+)
                         AND cab.sbac_oid_sbac = sbac.oid_sbac
                         AND cab.perd_oid_peri = peri.oid_peri
                         AND peri.peri_oid_peri = pcor.oid_peri
                         AND sbac.acce_oid_acce = acce.oid_acce
                         AND acce.cana_oid_cana = cana.oid_cana
                         AND pcor.tipe_oid_tipo_peri = tpe.oid_tipo_peri
                         AND pro.mapr_oid_marc_prod = mpro.oid_marc_prod(+)
                         AND pro.nego_oid_nego = nego.oid_nego(+)
                         AND dof.tofe_oid_tipo_ofer = tof.oid_tipo_ofer(+)
                         AND pro.val_grup_arti =
                             nvl(xx.val_grup_arti,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'AB'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '06'
                                           AND val_grup_arti = pro.val_grup_arti),
                                        0,
                                        pro.val_grup_arti,
                                        'XXXX'))
                         AND sop.TAIM_OID_TASA_IMPU =
                             nvl(xx.TAIM_OID_TASA_IMPU,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'AB'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '06'
                                           AND TAIM_OID_TASA_IMPU = sop.TAIM_OID_TASA_IMPU),
                                        0,
                                        sop.TAIM_OID_TASA_IMPU,
                                        0/*sop.TAIM_OID_TASA_IMPU*/))
                         AND psc.TSPA_OID_TIPO_SOLI_PAIS =
                             nvl(xx.TSPA_OID_TIPO_SOLI_PAIS,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'AB'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '06'
                                           AND TSPA_OID_TIPO_SOLI_PAIS = psc.TSPA_OID_TIPO_SOLI_PAIS),
                                        0,
                                        psc.TSPA_OID_TIPO_SOLI_PAIS,
                                        0/*psc.TSPA_OID_TIPO_SOLI_PAIS*/))
                         AND cab.ictp_oid_tipo_prog = con.oid_tipo_prog(+)
                         AND cab.num_lote_cont IS NULL
                         AND trunc(cab.fec_fact) <= to_date(psfecha,
                                                            'DD/MM/YYYY')
                         AND cab.perd_oid_peri in  (select oid_peri  from periodos))
               WHERE importe <> 0
                 AND cod_cuen_cont IS NOT NULL
               GROUP BY pais,
                        codigopais,
                        fecha,
                        sociedad,
                        codigoempresa,
                        codigocanal,
                        codigoacceso,
                        subacceso,
                        codigosubacceso,
                        codigotipoperiodo,
                        periodo,
                        codigoperiodo,
                        ejerciciocomercial,
                        val_indi_debe_habe,
                        cod_cuen_cont,
                        codigomarcaproducto,
                        grupoproductos,
                        codigonegocio,
                        codigotipooferta,
                        tipo_soli,
                        tipo_impu,
                        '06'

       UNION
              SELECT pais,
                     codigopais,
                     fecha,
                     sociedad,
                     codigoempresa,
                     codigocanal,
                     codigoacceso,
                     subacceso,
                     codigosubacceso,
                     codigotipoperiodo,
                     periodo,
                     codigoperiodo,
                     ejerciciocomercial,
                     val_indi_debe_habe,
                     'AB' tipo_asiento,
                     cod_cuen_cont,
                     codigomarcaproducto,
                     grupoproductos,
                     codigonegocio,
                     codigotipooferta,
                     tipo_soli,
                     tipo_impu,
                     '08',
                     SUM(abs(importe))
                FROM (SELECT cab.pais_oid_pais pais,
                             pai.cod_pais codigopais,
                             cab.fec_fact fecha,
                             cab.soci_oid_soci sociedad,
                             soc.cod_soci codigoempresa,
                             cana.cod_cana codigocanal,
                             acce.cod_acce codigoacceso,
                             cab.sbac_oid_sbac subacceso,
                             sbac.cod_sbac codigosubacceso,
                             tpe.cod_tipo_peri codigotipoperiodo,
                             peri.peri_oid_peri periodo,
                             pcor.cod_peri codigoperiodo,
                             pcor.val_anio ejerciciocomercial,
                             CASE
                               WHEN --decode((SELECT nvl(par.ind_impu_prod_naci,
                                    --                  0)
                                    --         FROM seg_param_inter_pais par),
                                    --       1,
                                           nvl(decode(lin.val_prec_cata_unit_loca,0,lin.imp_impu_tota_prod_naci,0),
                                               0)--,
                                          -- 0)
                                           < 0 THEN
                                decode(xx.val_indi_debe_habe,
                                       'D',
                                       'H',
                                       'D')
                               ELSE
                                xx.val_indi_debe_habe
                             END val_indi_debe_habe,
                             xx.cod_cuen_cont,
                             mpro.cod_marc_prod codigomarcaproducto,
                             pro.val_grup_arti grupoproductos,
                             nego.cod_nego codigonegocio,
                             tof.cod_tipo_ofer codigotipooferta,
                             xx.tspa_oid_tipo_soli_pais tipo_soli,
                             xx.TAIM_OID_TASA_IMPU tipo_impu,
                             --decode((SELECT nvl(par.ind_impu_prod_naci,
                             --                  0)
                             --         FROM seg_param_inter_pais par),
                             --       1,
                                           nvl(decode(lin.val_prec_cata_unit_loca,0,lin.imp_impu_tota_prod_naci,0),
                                               0)--,
                                    --0)
                                    importe
                        FROM fac_docum_conta_cabec cab,
                             fac_docum_conta_linea lin,
                             seg_pais pai,
                             seg_socie soc,
                             mae_produ pro,
                             ped_solic_cabec psc,
                             ped_solic_posic sop,
                             pre_ofert_detal dof,
                             seg_subac sbac,
                             seg_acces acce,
                             seg_canal cana,
                             cra_perio peri,
                             seg_perio_corpo pcor,
                             seg_tipo_perio tpe,
                             seg_marca_produ mpro,
                             mae_negoc nego,
                             pre_tipo_ofert tof,
                             inc_concu_tipo_prog con,
                             (SELECT cod_cuen_cont,
                                     val_indi_debe_habe,
                                     val_grup_arti,
                                     tspa_oid_tipo_soli_pais,
                                     taim_oid_tasa_impu
                                FROM int_param_conta_factu ipc,
                                     int_impor_conta_factu icf,
                                     ccc_cuent_conta       cc
                               WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                 AND ipc.val_tipo_asie = 'AB'
                                 AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                 AND cod_impo_cont = '08') xx
                       WHERE nvl(cab.val_tota_paga_loca,
                                 0) < 0
                         AND cab.tido_oid_tipo_docu in (31,32,33)
                         AND lin.dcca_oid_cabe = cab.oid_cabe
                         AND cab.pais_oid_pais = pai.oid_pais
                         AND cab.soci_oid_soci = soc.oid_soci
                         AND lin.prod_oid_prod = pro.oid_prod
                         AND sop.soca_oid_soli_cabe = psc.oid_soli_cabe
                         AND lin.sopo_oid_soli_posi = sop.oid_soli_posi
                         AND sop.ofde_oid_deta_ofer = dof.oid_deta_ofer(+)
                         AND cab.sbac_oid_sbac = sbac.oid_sbac
                         AND cab.perd_oid_peri = peri.oid_peri
                         AND peri.peri_oid_peri = pcor.oid_peri
                         AND sbac.acce_oid_acce = acce.oid_acce
                         AND acce.cana_oid_cana = cana.oid_cana
                         AND pcor.tipe_oid_tipo_peri = tpe.oid_tipo_peri
                         AND pro.mapr_oid_marc_prod = mpro.oid_marc_prod(+)
                         AND pro.nego_oid_nego = nego.oid_nego(+)
                         AND dof.tofe_oid_tipo_ofer = tof.oid_tipo_ofer(+)
                         AND pro.val_grup_arti =
                             nvl(xx.val_grup_arti,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'AB'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '08'
                                           AND val_grup_arti = pro.val_grup_arti),
                                        0,
                                        pro.val_grup_arti,
                                        'XXXX'))
                         AND sop.TAIM_OID_TASA_IMPU =
                             nvl(xx.TAIM_OID_TASA_IMPU,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'AB'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '08'
                                           AND TAIM_OID_TASA_IMPU = sop.TAIM_OID_TASA_IMPU),
                                        0,
                                        sop.TAIM_OID_TASA_IMPU,
                                        0/*sop.TAIM_OID_TASA_IMPU*/))
                         AND psc.TSPA_OID_TIPO_SOLI_PAIS =
                             nvl(xx.TSPA_OID_TIPO_SOLI_PAIS,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'AB'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '08'
                                           AND TSPA_OID_TIPO_SOLI_PAIS = psc.TSPA_OID_TIPO_SOLI_PAIS),
                                        0,
                                        psc.TSPA_OID_TIPO_SOLI_PAIS,
                                        0/*psc.TSPA_OID_TIPO_SOLI_PAIS*/))
                         AND cab.ictp_oid_tipo_prog = con.oid_tipo_prog(+)
                         AND cab.num_lote_cont IS NULL
                         AND trunc(cab.fec_fact) <= to_date(psfecha,
                                                            'DD/MM/YYYY')
                         AND cab.perd_oid_peri in  (select oid_peri  from periodos))
               WHERE importe <> 0
                 AND cod_cuen_cont IS NOT NULL
               GROUP BY pais,
                        codigopais,
                        fecha,
                        sociedad,
                        codigoempresa,
                        codigocanal,
                        codigoacceso,
                        subacceso,
                        codigosubacceso,
                        codigotipoperiodo,
                        periodo,
                        codigoperiodo,
                        ejerciciocomercial,
                        val_indi_debe_habe,
                        cod_cuen_cont,
                        codigomarcaproducto,
                        grupoproductos,
                        codigonegocio,
                        codigotipooferta,
                        tipo_soli,
                        tipo_impu,
                        '08'

              UNION

              SELECT pais,
                     codigopais,
                     fecha,
                     sociedad,
                     codigoempresa,
                     codigocanal,
                     codigoacceso,
                     subacceso,
                     codigosubacceso,
                     codigotipoperiodo,
                     periodo,
                     codigoperiodo,
                     ejerciciocomercial,
                     val_indi_debe_habe,
                     'AB' tipo_asiento,
                     cod_cuen_cont,
                     codigomarcaproducto,
                     grupoproductos,
                     codigonegocio,
                     codigotipooferta,
                     tipo_soli,
                     tipo_impu,
                     '05',
                     SUM(abs(importe))
                FROM (SELECT cab.pais_oid_pais pais,
                             pai.cod_pais codigopais,
                             cab.fec_fact fecha,
                             cab.soci_oid_soci sociedad,
                             soc.cod_soci codigoempresa,
                             cana.cod_cana codigocanal,
                             acce.cod_acce codigoacceso,
                             cab.sbac_oid_sbac subacceso,
                             sbac.cod_sbac codigosubacceso,
                             tpe.cod_tipo_peri codigotipoperiodo,
                             peri.peri_oid_peri periodo,
                             pcor.cod_peri codigoperiodo,
                             pcor.val_anio ejerciciocomercial,
                             CASE
                               WHEN CASE WHEN con.cod_tipo_prog IN ('B', 'C', 'R') THEN
                                0
                               ELSE
                                lin.imp_desc_sin_impu_tota_loca
                             END < 0 THEN decode(xx.val_indi_debe_habe, 'D', 'H', 'D') ELSE xx.val_indi_debe_habe END val_indi_debe_habe,
                             xx.cod_cuen_cont,
                             mpro.cod_marc_prod codigomarcaproducto,
                             pro.val_grup_arti grupoproductos,
                             nego.cod_nego codigonegocio,
                             tof.cod_tipo_ofer codigotipooferta,
                             xx.tspa_oid_tipo_soli_pais tipo_soli,
                             xx.TAIM_OID_TASA_IMPU tipo_impu,
                             CASE
                               WHEN con.cod_tipo_prog IN ('B', 'C', 'R') THEN
                                0
                               ELSE
                                lin.imp_desc_sin_impu_tota_loca
                             END importe
                        FROM fac_docum_conta_cabec cab,
                             fac_docum_conta_linea lin,
                             seg_pais pai,
                             seg_socie soc,
                             mae_produ pro,
                             ped_solic_cabec psc,
                             ped_solic_posic sop,
                             pre_ofert_detal dof,
                             seg_subac sbac,
                             seg_acces acce,
                             seg_canal cana,
                             cra_perio peri,
                             seg_perio_corpo pcor,
                             seg_tipo_perio tpe,
                             seg_marca_produ mpro,
                             mae_negoc nego,
                             pre_tipo_ofert tof,
                             inc_concu_tipo_prog con,
                             (SELECT cod_cuen_cont,
                                     val_indi_debe_habe,
                                     val_grup_arti,
                                     tspa_oid_tipo_soli_pais,
                                     taim_oid_tasa_impu
                                FROM int_param_conta_factu ipc,
                                     int_impor_conta_factu icf,
                                     ccc_cuent_conta       cc
                               WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                 AND ipc.val_tipo_asie = 'AB'
                                 AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                 AND cod_impo_cont = '05') xx
                       WHERE nvl(cab.val_tota_paga_loca,
                                 0) < 0
                         AND cab.tido_oid_tipo_docu in (31,32,33)
                         AND lin.dcca_oid_cabe = cab.oid_cabe
                         AND cab.pais_oid_pais = pai.oid_pais
                         AND cab.soci_oid_soci = soc.oid_soci
                         AND lin.prod_oid_prod = pro.oid_prod
                         AND sop.soca_oid_soli_cabe = psc.oid_soli_cabe
                         AND lin.sopo_oid_soli_posi = sop.oid_soli_posi
                         AND sop.ofde_oid_deta_ofer = dof.oid_deta_ofer(+)
                         AND cab.sbac_oid_sbac = sbac.oid_sbac
                         AND cab.perd_oid_peri = peri.oid_peri
                         AND peri.peri_oid_peri = pcor.oid_peri
                         AND sbac.acce_oid_acce = acce.oid_acce
                         AND acce.cana_oid_cana = cana.oid_cana
                         AND pcor.tipe_oid_tipo_peri = tpe.oid_tipo_peri
                         AND pro.mapr_oid_marc_prod = mpro.oid_marc_prod(+)
                         AND pro.nego_oid_nego = nego.oid_nego(+)
                         AND dof.tofe_oid_tipo_ofer = tof.oid_tipo_ofer(+)
                         AND pro.val_grup_arti =
                             nvl(xx.val_grup_arti,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'AB'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '05'
                                           AND val_grup_arti = pro.val_grup_arti),
                                        0,
                                        pro.val_grup_arti,
                                        'XXXX'))
                         AND sop.TAIM_OID_TASA_IMPU =
                             nvl(xx.TAIM_OID_TASA_IMPU,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'AB'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '05'
                                           AND TAIM_OID_TASA_IMPU = sop.TAIM_OID_TASA_IMPU),
                                        0,
                                        sop.TAIM_OID_TASA_IMPU,
                                        0/*sop.TAIM_OID_TASA_IMPU*/))
                         AND psc.TSPA_OID_TIPO_SOLI_PAIS =
                             nvl(xx.TSPA_OID_TIPO_SOLI_PAIS,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'AB'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '05'
                                           AND TSPA_OID_TIPO_SOLI_PAIS = psc.TSPA_OID_TIPO_SOLI_PAIS),
                                        0,
                                        psc.TSPA_OID_TIPO_SOLI_PAIS,
                                        0/*psc.TSPA_OID_TIPO_SOLI_PAIS*/))
                         AND cab.ictp_oid_tipo_prog = con.oid_tipo_prog(+)
                         AND cab.num_lote_cont IS NULL
                         AND trunc(cab.fec_fact) <= to_date(psfecha,
                                                            'DD/MM/YYYY')
                         AND cab.perd_oid_peri in  (select oid_peri  from periodos))
               WHERE importe <> 0
                 AND cod_cuen_cont IS NOT NULL
               GROUP BY pais,
                        codigopais,
                        fecha,
                        sociedad,
                        codigoempresa,
                        codigocanal,
                        codigoacceso,
                        subacceso,
                        codigosubacceso,
                        codigotipoperiodo,
                        periodo,
                        codigoperiodo,
                        ejerciciocomercial,
                        val_indi_debe_habe,
                        cod_cuen_cont,
                        codigomarcaproducto,
                        grupoproductos,
                        codigonegocio,
                        codigotipooferta,
                        tipo_soli,
                        tipo_impu,
                        '05'

              UNION

              SELECT pais,
                     codigopais,
                     fecha,
                     sociedad,
                     codigoempresa,
                     codigocanal,
                     codigoacceso,
                     subacceso,
                     codigosubacceso,
                     codigotipoperiodo,
                     periodo,
                     codigoperiodo,
                     ejerciciocomercial,
                     val_indi_debe_habe,
                     'AB' tipo_asiento,
                     cod_cuen_cont,
                     codigomarcaproducto,
                     grupoproductos,
                     codigonegocio,
                     codigotipooferta,
                     tipo_soli,
                     tipo_impu,
                     '07',
                     SUM(abs(importe))
                FROM (SELECT cab.pais_oid_pais pais,
                             pai.cod_pais codigopais,
                             cab.fec_fact fecha,
                             cab.soci_oid_soci sociedad,
                             soc.cod_soci codigoempresa,
                             cana.cod_cana codigocanal,
                             acce.cod_acce codigoacceso,
                             cab.sbac_oid_sbac subacceso,
                             sbac.cod_sbac codigosubacceso,
                             tpe.cod_tipo_peri codigotipoperiodo,
                             peri.peri_oid_peri periodo,
                             pcor.cod_peri codigoperiodo,
                             pcor.val_anio ejerciciocomercial,
                             CASE
                               WHEN CASE WHEN con.cod_tipo_prog IN ('B','C', 'R') THEN
                                0
                               ELSE
                                decode(lin.val_prec_cont_tota_loca,
                                       0,
                                       0,
                                       lin.val_prec_sin_impu_tota_loca)
                             END < 0 THEN decode(xx.val_indi_debe_habe, 'D', 'H', 'D') ELSE xx.val_indi_debe_habe END val_indi_debe_habe,
                             xx.cod_cuen_cont,
                             mpro.cod_marc_prod codigomarcaproducto,
                             pro.val_grup_arti grupoproductos,
                             nego.cod_nego codigonegocio,
                             tof.cod_tipo_ofer codigotipooferta,
                             xx.tspa_oid_tipo_soli_pais tipo_soli,
                             xx.TAIM_OID_TASA_IMPU tipo_impu,
                             CASE
                               WHEN con.cod_tipo_prog IN ('B','C', 'R') THEN
                                0
                               ELSE
                                decode(lin.val_prec_cont_tota_loca,
                                       0,
                                       0,
                                       lin.val_prec_sin_impu_tota_loca)
                             END importe
                        FROM fac_docum_conta_cabec cab,
                             fac_docum_conta_linea lin,
                             seg_pais pai,
                             seg_socie soc,
                             mae_produ pro,
                             ped_solic_cabec psc,
                             ped_solic_posic sop,
                             pre_ofert_detal dof,
                             seg_subac sbac,
                             seg_acces acce,
                             seg_canal cana,
                             cra_perio peri,
                             seg_perio_corpo pcor,
                             seg_tipo_perio tpe,
                             seg_marca_produ mpro,
                             mae_negoc nego,
                             pre_tipo_ofert tof,
                             inc_concu_tipo_prog con,
                             (SELECT cod_cuen_cont,
                                     val_indi_debe_habe,
                                     val_grup_arti,
                                     tspa_oid_tipo_soli_pais,
                                     taim_oid_tasa_impu
                                FROM int_param_conta_factu ipc,
                                     int_impor_conta_factu icf,
                                     ccc_cuent_conta       cc
                               WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                 AND ipc.val_tipo_asie = 'AB'
                                 AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                 AND cod_impo_cont = '07') xx
                       WHERE nvl(cab.val_tota_paga_loca,
                                 0) < 0
                         AND cab.tido_oid_tipo_docu in (31,32,33)
                         AND lin.dcca_oid_cabe = cab.oid_cabe
                         AND cab.pais_oid_pais = pai.oid_pais
                         AND cab.soci_oid_soci = soc.oid_soci
                         AND lin.prod_oid_prod = pro.oid_prod
                         AND sop.soca_oid_soli_cabe = psc.oid_soli_cabe
                         AND lin.sopo_oid_soli_posi = sop.oid_soli_posi
                         AND sop.ofde_oid_deta_ofer = dof.oid_deta_ofer(+)
                         AND cab.sbac_oid_sbac = sbac.oid_sbac
                         AND cab.perd_oid_peri = peri.oid_peri
                         AND peri.peri_oid_peri = pcor.oid_peri
                         AND sbac.acce_oid_acce = acce.oid_acce
                         AND acce.cana_oid_cana = cana.oid_cana
                         AND pcor.tipe_oid_tipo_peri = tpe.oid_tipo_peri
                         AND pro.mapr_oid_marc_prod = mpro.oid_marc_prod(+)
                         AND pro.nego_oid_nego = nego.oid_nego(+)
                         AND dof.tofe_oid_tipo_ofer = tof.oid_tipo_ofer(+)
                         AND pro.val_grup_arti =
                             nvl(xx.val_grup_arti,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'AB'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '07'
                                           AND val_grup_arti = pro.val_grup_arti),
                                        0,
                                        pro.val_grup_arti,
                                        'XXXX'))
                         AND sop.TAIM_OID_TASA_IMPU =
                             nvl(xx.TAIM_OID_TASA_IMPU,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'AB'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '07'
                                           AND TAIM_OID_TASA_IMPU = sop.TAIM_OID_TASA_IMPU),
                                        0,
                                        sop.TAIM_OID_TASA_IMPU,
                                        0/*sop.TAIM_OID_TASA_IMPU*/))
                         AND psc.TSPA_OID_TIPO_SOLI_PAIS =
                             nvl(xx.TSPA_OID_TIPO_SOLI_PAIS,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'AB'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '07'
                                           AND TSPA_OID_TIPO_SOLI_PAIS = psc.TSPA_OID_TIPO_SOLI_PAIS),
                                        0,
                                        psc.TSPA_OID_TIPO_SOLI_PAIS,
                                        0/*psc.TSPA_OID_TIPO_SOLI_PAIS*/))
                         AND cab.ictp_oid_tipo_prog = con.oid_tipo_prog(+)
                         AND cab.num_lote_cont IS NULL
                         AND trunc(cab.fec_fact) <= to_date(psfecha,
                                                            'DD/MM/YYYY')
                         AND cab.perd_oid_peri in  (select oid_peri  from periodos))
               WHERE importe <> 0
                 AND cod_cuen_cont IS NOT NULL
               GROUP BY pais,
                        codigopais,
                        fecha,
                        sociedad,
                        codigoempresa,
                        codigocanal,
                        codigoacceso,
                        subacceso,
                        codigosubacceso,
                        codigotipoperiodo,
                        periodo,
                        codigoperiodo,
                        ejerciciocomercial,
                        val_indi_debe_habe,
                        cod_cuen_cont,
                        codigomarcaproducto,
                        grupoproductos,
                        codigonegocio,
                        codigotipooferta,
                        tipo_soli,
                        tipo_impu,
                        '07'

              UNION

               (SELECT pais,
                       codigopais,
                       fecha,
                       sociedad,
                       codigoempresa,
                       codigocanal,
                       codigoacceso,
                       subacceso,
                       codigosubacceso,
                       codigotipoperiodo,
                       periodo,
                       codigoperiodo,
                       ejerciciocomercial,
                       CASE
                         WHEN importe < 0 THEN
                          decode(val_indi_debe_habe,
                                 'D',
                                 'H',
                                 'D')
                         ELSE
                          val_indi_debe_habe
                       END val_indi_debe_habe,
                       'VN' tipo_asiento,
                       cod_cuen_cont,
                       codigomarcaproducto,
                       grupoproductos,
                       codigonegocio,
                       codigotipooferta,
                       tipo_soli,
                       tipo_impu,
                       cod,
                       abs(importe)
                  FROM (SELECT pais,
                               codigopais,
                               fecha,
                               sociedad,
                               codigoempresa,
                               codigocanal,
                               codigoacceso,
                               subacceso,
                               codigosubacceso,
                               codigotipoperiodo,
                               periodo,
                               codigoperiodo,
                               ejerciciocomercial,
                               val_indi_debe_habe,
                               cod_cuen_cont,
                               codigomarcaproducto,
                               grupoproductos,
                               codigonegocio,
                               codigotipooferta,
                               tipo_soli,
                               tipo_impu,
                               cod,
                               SUM(importe50) importe
                          FROM (SELECT cab.pais_oid_pais pais,
                                       pai.cod_pais codigopais,
                                       cab.fec_fact fecha,
                                       cab.soci_oid_soci sociedad,
                                       soc.cod_soci codigoempresa,
                                       cana.cod_cana codigocanal,
                                       acce.cod_acce codigoacceso,
                                       cab.sbac_oid_sbac subacceso,
                                       sbac.cod_sbac codigosubacceso,
                                       tpe.cod_tipo_peri codigotipoperiodo,
                                       peri.peri_oid_peri periodo,
                                       pcor.cod_peri codigoperiodo,
                                       pcor.val_anio ejerciciocomercial,
                                       ipc.val_indi_debe_habe,
                                       cc.cod_cuen_cont,
                                       NULL codigomarcaproducto,
                                       NULL grupoproductos,
                                       NULL codigonegocio,
                                       NULL codigotipooferta,
                                       ipc.tspa_oid_tipo_soli_pais tipo_soli,
                                       ipc.TAIM_OID_TASA_IMPU tipo_impu,
                                       '50' cod,
                                       CASE
                                         WHEN con.cod_tipo_prog IN ('B', 'C', 'R') THEN
                                          0
                                         ELSE
                                          cab.val_tota_paga_loca
                                       END importe50
                                  FROM fac_docum_conta_cabec cab,
                                       ped_solic_cabec       psc,
                                       seg_pais              pai,
                                       seg_socie             soc,
                                       seg_subac             sbac,
                                       seg_acces             acce,
                                       seg_canal             cana,
                                       cra_perio             peri,
                                       seg_perio_corpo       pcor,
                                       seg_tipo_perio        tpe,
                                       int_param_conta_factu ipc,
                                       int_impor_conta_factu icf,
                                       ccc_cuent_conta       cc,
                                       inc_concu_tipo_prog   con
                                 WHERE nvl(cab.val_tota_paga_loca,
                                           0) >= 0
                                   AND cab.tido_oid_tipo_docu in (1,9,28,29,30,34)
                                   and cab.soca_oid_soli_cabe=psc.oid_soli_cabe
                                   AND cab.pais_oid_pais = pai.oid_pais
                                   AND cab.soci_oid_soci = soc.oid_soci
                                   AND cab.sbac_oid_sbac = sbac.oid_sbac
                                   AND cab.perd_oid_peri = peri.oid_peri
                                   AND peri.peri_oid_peri = pcor.oid_peri
                                   AND sbac.acce_oid_acce = acce.oid_acce
                                   AND acce.cana_oid_cana = cana.oid_cana
                                   AND pcor.tipe_oid_tipo_peri = tpe.oid_tipo_peri
                                   AND ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                   AND ipc.val_tipo_asie = 'VN'
                                   AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                   AND cod_impo_cont = '50'
                                   AND cab.ictp_oid_tipo_prog = con.oid_tipo_prog(+)
                                   AND cab.num_lote_cont IS NULL
                                   AND trunc(cab.fec_fact) <=
                                       to_date(psfecha,
                                               'DD/MM/YYYY')
                                     AND psc.TAIM_OID_TASA_IMPU =
                                         nvl(ipc.TAIM_OID_TASA_IMPU,
                                             decode((SELECT COUNT(*)
                                                      FROM int_param_conta_factu ipc,
                                                           int_impor_conta_factu icf,
                                                           ccc_cuent_conta       cc
                                                     WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                       AND ipc.val_tipo_asie = 'VN'
                                                       AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                       AND cod_impo_cont = '50'
                                                       AND TAIM_OID_TASA_IMPU = psc.TAIM_OID_TASA_IMPU),
                                                    0,
                                                    psc.TAIM_OID_TASA_IMPU,
                                                    0/*psc.TAIM_OID_TASA_IMPU*/))
                                     AND psc.TSPA_OID_TIPO_SOLI_PAIS =
                                         nvl(ipc.TSPA_OID_TIPO_SOLI_PAIS,
                                             decode((SELECT COUNT(*)
                                                      FROM int_param_conta_factu ipc,
                                                           int_impor_conta_factu icf,
                                                           ccc_cuent_conta       cc
                                                     WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                       AND ipc.val_tipo_asie = 'VN'
                                                       AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                       AND cod_impo_cont = '50'
                                                       AND TSPA_OID_TIPO_SOLI_PAIS = psc.TSPA_OID_TIPO_SOLI_PAIS),
                                                    0,
                                                    psc.TSPA_OID_TIPO_SOLI_PAIS,
                                                0/*psc.TSPA_OID_TIPO_SOLI_PAIS*/))
                                   AND cab.perd_oid_peri in  (select oid_peri  from periodos))
                         GROUP BY pais,
                                  codigopais,
                                  fecha,
                                  sociedad,
                                  codigoempresa,
                                  codigocanal,
                                  codigoacceso,
                                  subacceso,
                                  codigosubacceso,
                                  codigotipoperiodo,
                                  periodo,
                                  codigoperiodo,
                                  ejerciciocomercial,
                                  val_indi_debe_habe,
                                  cod_cuen_cont,
                                  codigomarcaproducto,
                                  codigonegocio,
                                  codigotipooferta,
                                  tipo_soli,
                                  tipo_impu,
                                  cod))
              UNION

               (SELECT pais,
                       codigopais,
                       fecha,
                       sociedad,
                       codigoempresa,
                       codigocanal,
                       codigoacceso,
                       subacceso,
                       codigosubacceso,
                       codigotipoperiodo,
                       periodo,
                       codigoperiodo,
                       ejerciciocomercial,
                       CASE
                         WHEN importe < 0 THEN
                          decode(val_indi_debe_habe,
                                 'D',
                                 'H',
                                 'D')
                         ELSE
                          val_indi_debe_habe
                       END val_indi_debe_habe,
                       'VN' tipo_asiento,
                       cod_cuen_cont,
                       codigomarcaproducto,
                       grupoproductos,
                       codigonegocio,
                       codigotipooferta,
                       tipo_soli,
                       tipo_impu,
                       cod,
                       abs(importe)
                  FROM (SELECT pais,
                               codigopais,
                               fecha,
                               sociedad,
                               codigoempresa,
                               codigocanal,
                               codigoacceso,
                               subacceso,
                               codigosubacceso,
                               codigotipoperiodo,
                               periodo,
                               codigoperiodo,
                               ejerciciocomercial,
                               val_indi_debe_habe,
                               cod_cuen_cont,
                               codigomarcaproducto,
                               grupoproductos,
                               codigonegocio,
                               codigotipooferta,
                               tipo_soli,
                               tipo_impu,
                               cod,
                               SUM(importe55) importe
                          FROM (SELECT cab.pais_oid_pais pais,
                                       pai.cod_pais codigopais,
                                       cab.fec_fact fecha,
                                       cab.soci_oid_soci sociedad,
                                       soc.cod_soci codigoempresa,
                                       cana.cod_cana codigocanal,
                                       acce.cod_acce codigoacceso,
                                       cab.sbac_oid_sbac subacceso,
                                       sbac.cod_sbac codigosubacceso,
                                       tpe.cod_tipo_peri codigotipoperiodo,
                                       peri.peri_oid_peri periodo,
                                       pcor.cod_peri codigoperiodo,
                                       pcor.val_anio ejerciciocomercial,
                                       ipc.val_indi_debe_habe,
                                       cc.cod_cuen_cont,
                                       NULL codigomarcaproducto,
                                       NULL grupoproductos,
                                       NULL codigonegocio,
                                       NULL codigotipooferta,
                                       ipc.tspa_oid_tipo_soli_pais tipo_soli,
                                       ipc.TAIM_OID_TASA_IMPU tipo_impu,
                                       '55' cod,
                                       CASE
                                         WHEN con.cod_tipo_prog IN ('B', 'C', 'R') THEN
                                          0
                                         ELSE
                                          cab.imp_redo_loca
                                       END importe55
                                  FROM fac_docum_conta_cabec cab,
                                       ped_solic_cabec       psc,
                                       seg_pais              pai,
                                       seg_socie             soc,
                                       seg_subac             sbac,
                                       seg_acces             acce,
                                       seg_canal             cana,
                                       cra_perio             peri,
                                       seg_perio_corpo       pcor,
                                       seg_tipo_perio        tpe,
                                       int_param_conta_factu ipc,
                                       int_impor_conta_factu icf,
                                       ccc_cuent_conta       cc,
                                       inc_concu_tipo_prog   con
                                 WHERE nvl(cab.val_tota_paga_loca,
                                           0) >= 0
                                   AND cab.tido_oid_tipo_docu in (1,9,28,29,30,34)
                                   and cab.soca_oid_soli_cabe=psc.oid_soli_cabe
                                   AND cab.pais_oid_pais = pai.oid_pais
                                   AND cab.soci_oid_soci = soc.oid_soci
                                   AND cab.sbac_oid_sbac = sbac.oid_sbac
                                   AND cab.perd_oid_peri = peri.oid_peri
                                   AND peri.peri_oid_peri = pcor.oid_peri
                                   AND sbac.acce_oid_acce = acce.oid_acce
                                   AND acce.cana_oid_cana = cana.oid_cana
                                   AND pcor.tipe_oid_tipo_peri = tpe.oid_tipo_peri
                                   AND ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                   AND ipc.val_tipo_asie = 'VN'
                                   AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                   AND cod_impo_cont = '55'
                                   AND cab.ictp_oid_tipo_prog = con.oid_tipo_prog(+)
                                   AND cab.num_lote_cont IS NULL
                                   AND trunc(cab.fec_fact) <=
                                       to_date(psfecha,
                                               'DD/MM/YYYY')
                                     AND psc.TAIM_OID_TASA_IMPU =
                                         nvl(ipc.TAIM_OID_TASA_IMPU,
                                             decode((SELECT COUNT(*)
                                                      FROM int_param_conta_factu ipc,
                                                           int_impor_conta_factu icf,
                                                           ccc_cuent_conta       cc
                                                     WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                       AND ipc.val_tipo_asie = 'VN'
                                                       AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                       AND cod_impo_cont = '55'
                                                       AND TAIM_OID_TASA_IMPU = psc.TAIM_OID_TASA_IMPU),
                                                    0,
                                                    psc.TAIM_OID_TASA_IMPU,
                                                    0/*psc.TAIM_OID_TASA_IMPU*/))
                                     AND psc.TSPA_OID_TIPO_SOLI_PAIS =
                                         nvl(ipc.TSPA_OID_TIPO_SOLI_PAIS,
                                             decode((SELECT COUNT(*)
                                                      FROM int_param_conta_factu ipc,
                                                           int_impor_conta_factu icf,
                                                           ccc_cuent_conta       cc
                                                     WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                       AND ipc.val_tipo_asie = 'VN'
                                                       AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                       AND cod_impo_cont = '55'
                                                       AND TSPA_OID_TIPO_SOLI_PAIS = psc.TSPA_OID_TIPO_SOLI_PAIS),
                                                    0,
                                                    psc.TSPA_OID_TIPO_SOLI_PAIS,
                                                0/*psc.TSPA_OID_TIPO_SOLI_PAIS*/))
                                   AND cab.perd_oid_peri in  (select oid_peri  from periodos))
                         GROUP BY pais,
                                  codigopais,
                                  fecha,
                                  sociedad,
                                  codigoempresa,
                                  codigocanal,
                                  codigoacceso,
                                  subacceso,
                                  codigosubacceso,
                                  codigotipoperiodo,
                                  periodo,
                                  codigoperiodo,
                                  ejerciciocomercial,
                                  val_indi_debe_habe,
                                  cod_cuen_cont,
                                  codigomarcaproducto,
                                  grupoproductos,
                                  codigonegocio,
                                  codigotipooferta,
                                  tipo_soli,
                                  tipo_impu,
                                  cod))
              UNION

              /* Formatted on 2009/07/06 10:09 (Formatter Plus v4.8.8) */
               (SELECT pais,
                       codigopais,
                       fecha,
                       sociedad,
                       codigoempresa,
                       codigocanal,
                       codigoacceso,
                       subacceso,
                       codigosubacceso,
                       codigotipoperiodo,
                       periodo,
                       codigoperiodo,
                       ejerciciocomercial,
                       CASE
                         WHEN importe < 0 THEN
                          decode(val_indi_debe_habe,
                                 'D',
                                 'H',
                                 'D')
                         ELSE
                          val_indi_debe_habe
                       END val_indi_debe_habe,
                       'VN' tipo_asiento,
                       cod_cuen_cont,
                       codigomarcaproducto,
                       grupoproductos,
                       codigonegocio,
                       codigotipooferta,
                       tipo_soli,
                       tipo_impu,
                       cod,
                       abs(importe)
                  FROM (SELECT pais,
                               codigopais,
                               fecha,
                               sociedad,
                               codigoempresa,
                               codigocanal,
                               codigoacceso,
                               subacceso,
                               codigosubacceso,
                               codigotipoperiodo,
                               periodo,
                               codigoperiodo,
                               ejerciciocomercial,
                               val_indi_debe_habe,
                               cod_cuen_cont,
                               codigomarcaproducto,
                               grupoproductos,
                               codigonegocio,
                               codigotipooferta,
                               tipo_soli,
                               tipo_impu,
                               cod,
                               SUM(importe60) importe
                          FROM (SELECT cab.pais_oid_pais pais,
                                       pai.cod_pais codigopais,
                                       cab.fec_fact fecha,
                                       cab.soci_oid_soci sociedad,
                                       soc.cod_soci codigoempresa,
                                       cana.cod_cana codigocanal,
                                       acce.cod_acce codigoacceso,
                                       cab.sbac_oid_sbac subacceso,
                                       sbac.cod_sbac codigosubacceso,
                                       tpe.cod_tipo_peri codigotipoperiodo,
                                       peri.peri_oid_peri periodo,
                                       pcor.cod_peri codigoperiodo,
                                       pcor.val_anio ejerciciocomercial,
                                       ipc.val_indi_debe_habe,
                                       cc.cod_cuen_cont,
                                       NULL codigomarcaproducto,
                                       NULL grupoproductos,
                                       NULL codigonegocio,
                                       NULL codigotipooferta,
                                       ipc.tspa_oid_tipo_soli_pais tipo_soli,
                                       ipc.TAIM_OID_TASA_IMPU tipo_impu,
                                       '60' cod,
                                       CASE
                                         WHEN con.cod_tipo_prog IN ('B', 'C', 'R') THEN
                                          0
                                         ELSE
                                          cab.imp_impu_tota_loca
                                       END importe60
                                  FROM fac_docum_conta_cabec cab,
                                       ped_solic_cabec       psc,
                                       seg_pais              pai,
                                       seg_socie             soc,
                                       seg_subac             sbac,
                                       seg_acces             acce,
                                       seg_canal             cana,
                                       cra_perio             peri,
                                       seg_perio_corpo       pcor,
                                       seg_tipo_perio        tpe,
                                       int_param_conta_factu ipc,
                                       int_impor_conta_factu icf,
                                       ccc_cuent_conta       cc,
                                       inc_concu_tipo_prog   con
                                 WHERE nvl(cab.val_tota_paga_loca,
                                           0) >= 0
                                   AND cab.tido_oid_tipo_docu in (1,9,28,29,30,34)
                                   and cab.soca_oid_soli_cabe=psc.oid_soli_cabe
                                   AND cab.pais_oid_pais = pai.oid_pais
                                   AND cab.soci_oid_soci = soc.oid_soci
                                   AND cab.sbac_oid_sbac = sbac.oid_sbac
                                   AND cab.perd_oid_peri = peri.oid_peri
                                   AND peri.peri_oid_peri = pcor.oid_peri
                                   AND sbac.acce_oid_acce = acce.oid_acce
                                   AND acce.cana_oid_cana = cana.oid_cana
                                   AND pcor.tipe_oid_tipo_peri = tpe.oid_tipo_peri
                                   AND ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                   AND ipc.val_tipo_asie = 'VN'
                                   AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                   AND cod_impo_cont = '60'
                                   AND cab.ictp_oid_tipo_prog = con.oid_tipo_prog(+)
                                   AND cab.num_lote_cont IS NULL
                                   AND trunc(cab.fec_fact) <=
                                       to_date(psfecha,
                                               'DD/MM/YYYY')
                                     AND psc.TAIM_OID_TASA_IMPU =
                                         nvl(ipc.TAIM_OID_TASA_IMPU,
                                             decode((SELECT COUNT(*)
                                                      FROM int_param_conta_factu ipc,
                                                           int_impor_conta_factu icf,
                                                           ccc_cuent_conta       cc
                                                     WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                       AND ipc.val_tipo_asie = 'VN'
                                                       AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                       AND cod_impo_cont = '60'
                                                       AND TAIM_OID_TASA_IMPU = psc.TAIM_OID_TASA_IMPU),
                                                    0,
                                                    psc.TAIM_OID_TASA_IMPU,
                                                    0/*psc.TAIM_OID_TASA_IMPU*/))
                                     AND psc.TSPA_OID_TIPO_SOLI_PAIS =
                                         nvl(ipc.TSPA_OID_TIPO_SOLI_PAIS,
                                             decode((SELECT COUNT(*)
                                                      FROM int_param_conta_factu ipc,
                                                           int_impor_conta_factu icf,
                                                           ccc_cuent_conta       cc
                                                     WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                       AND ipc.val_tipo_asie = 'VN'
                                                       AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                       AND cod_impo_cont = '60'
                                                       AND TSPA_OID_TIPO_SOLI_PAIS = psc.TSPA_OID_TIPO_SOLI_PAIS),
                                                    0,
                                                    psc.TSPA_OID_TIPO_SOLI_PAIS,
                                                0/*psc.TSPA_OID_TIPO_SOLI_PAIS*/))
                                   AND cab.perd_oid_peri in  (select oid_peri  from periodos))
                         GROUP BY pais,
                                  codigopais,
                                  fecha,
                                  sociedad,
                                  codigoempresa,
                                  codigocanal,
                                  codigoacceso,
                                  subacceso,
                                  codigosubacceso,
                                  codigotipoperiodo,
                                  periodo,
                                  codigoperiodo,
                                  ejerciciocomercial,
                                  val_indi_debe_habe,
                                  cod_cuen_cont,
                                  codigomarcaproducto,
                                  grupoproductos,
                                  codigonegocio,
                                  codigotipooferta,
                                  tipo_soli,
                                  tipo_impu,
                                  cod))
              UNION

              /* Formatted on 2009/07/06 10:09 (Formatter Plus v4.8.8) */
               (SELECT pais,
                       codigopais,
                       fecha,
                       sociedad,
                       codigoempresa,
                       codigocanal,
                       codigoacceso,
                       subacceso,
                       codigosubacceso,
                       codigotipoperiodo,
                       periodo,
                       codigoperiodo,
                       ejerciciocomercial,
                       CASE
                         WHEN importe < 0 THEN
                          decode(val_indi_debe_habe,
                                 'D',
                                 'H',
                                 'D')
                         ELSE
                          val_indi_debe_habe
                       END val_indi_debe_habe,
                       'VN' tipo_asiento,
                       cod_cuen_cont,
                       codigomarcaproducto,
                       grupoproductos,
                       codigonegocio,
                       codigotipooferta,
                       tipo_soli,
                       tipo_impu,
                       cod,
                       abs(importe)
                  FROM (SELECT pais,
                               codigopais,
                               fecha,
                               sociedad,
                               codigoempresa,
                               codigocanal,
                               codigoacceso,
                               subacceso,
                               codigosubacceso,
                               codigotipoperiodo,
                               periodo,
                               codigoperiodo,
                               ejerciciocomercial,
                               val_indi_debe_habe,
                               cod_cuen_cont,
                               codigomarcaproducto,
                               grupoproductos,
                               codigonegocio,
                               codigotipooferta,
                               tipo_soli,
                               tipo_impu,
                               cod,
                               SUM(importe70) importe
                          FROM (SELECT cab.pais_oid_pais pais,
                                       pai.cod_pais codigopais,
                                       cab.fec_fact fecha,
                                       cab.soci_oid_soci sociedad,
                                       soc.cod_soci codigoempresa,
                                       cana.cod_cana codigocanal,
                                       acce.cod_acce codigoacceso,
                                       cab.sbac_oid_sbac subacceso,
                                       sbac.cod_sbac codigosubacceso,
                                       tpe.cod_tipo_peri codigotipoperiodo,
                                       peri.peri_oid_peri periodo,
                                       pcor.cod_peri codigoperiodo,
                                       pcor.val_anio ejerciciocomercial,
                                       ipc.val_indi_debe_habe,
                                       cc.cod_cuen_cont,
                                       NULL codigomarcaproducto,
                                       NULL grupoproductos,
                                       NULL codigonegocio,
                                       NULL codigotipooferta,
                                       ipc.tspa_oid_tipo_soli_pais tipo_soli,
                                       ipc.TAIM_OID_TASA_IMPU tipo_impu,
                                       '70' cod,
                                       CASE
                                         WHEN con.cod_tipo_prog IN ('B', 'C', 'R') THEN
                                          0
                                         ELSE
                                          cab.imp_flet_impu_tota_loca
                                       END importe70
                                  FROM fac_docum_conta_cabec cab,
                                       ped_solic_cabec       psc,
                                       seg_pais              pai,
                                       seg_socie             soc,
                                       seg_subac             sbac,
                                       seg_acces             acce,
                                       seg_canal             cana,
                                       cra_perio             peri,
                                       seg_perio_corpo       pcor,
                                       seg_tipo_perio        tpe,
                                       int_param_conta_factu ipc,
                                       int_impor_conta_factu icf,
                                       ccc_cuent_conta       cc,
                                       inc_concu_tipo_prog   con
                                 WHERE nvl(cab.val_tota_paga_loca,
                                           0) >= 0
                                   AND cab.tido_oid_tipo_docu in (1,9,28,29,30,34)
                                   and cab.soca_oid_soli_cabe=psc.oid_soli_cabe
                                   AND cab.pais_oid_pais = pai.oid_pais
                                   AND cab.soci_oid_soci = soc.oid_soci
                                   AND cab.sbac_oid_sbac = sbac.oid_sbac
                                   AND cab.perd_oid_peri = peri.oid_peri
                                   AND peri.peri_oid_peri = pcor.oid_peri
                                   AND sbac.acce_oid_acce = acce.oid_acce
                                   AND acce.cana_oid_cana = cana.oid_cana
                                   AND pcor.tipe_oid_tipo_peri = tpe.oid_tipo_peri
                                   AND ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                   AND ipc.val_tipo_asie = 'VN'
                                   AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                   AND cod_impo_cont = '70'
                                   AND cab.ictp_oid_tipo_prog = con.oid_tipo_prog(+)
                                   AND cab.num_lote_cont IS NULL
                                   AND trunc(cab.fec_fact) <=
                                       to_date(psfecha,
                                               'DD/MM/YYYY')
                                     AND psc.TAIM_OID_TASA_IMPU =
                                         nvl(ipc.TAIM_OID_TASA_IMPU,
                                             decode((SELECT COUNT(*)
                                                      FROM int_param_conta_factu ipc,
                                                           int_impor_conta_factu icf,
                                                           ccc_cuent_conta       cc
                                                     WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                       AND ipc.val_tipo_asie = 'VN'
                                                       AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                       AND cod_impo_cont = '70'
                                                       AND TAIM_OID_TASA_IMPU = psc.TAIM_OID_TASA_IMPU),
                                                    0,
                                                    psc.TAIM_OID_TASA_IMPU,
                                                    0/*psc.TAIM_OID_TASA_IMPU*/))
                                     AND psc.TSPA_OID_TIPO_SOLI_PAIS =
                                         nvl(ipc.TSPA_OID_TIPO_SOLI_PAIS,
                                             decode((SELECT COUNT(*)
                                                      FROM int_param_conta_factu ipc,
                                                           int_impor_conta_factu icf,
                                                           ccc_cuent_conta       cc
                                                     WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                       AND ipc.val_tipo_asie = 'VN'
                                                       AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                       AND cod_impo_cont = '70'
                                                       AND TSPA_OID_TIPO_SOLI_PAIS = psc.TSPA_OID_TIPO_SOLI_PAIS),
                                                    0,
                                                    psc.TSPA_OID_TIPO_SOLI_PAIS,
                                                0/*psc.TSPA_OID_TIPO_SOLI_PAIS*/))
                                   AND cab.perd_oid_peri in  (select oid_peri  from periodos))
                         GROUP BY pais,
                                  codigopais,
                                  fecha,
                                  sociedad,
                                  codigoempresa,
                                  codigocanal,
                                  codigoacceso,
                                  subacceso,
                                  codigosubacceso,
                                  codigotipoperiodo,
                                  periodo,
                                  codigoperiodo,
                                  ejerciciocomercial,
                                  val_indi_debe_habe,
                                  cod_cuen_cont,
                                  codigomarcaproducto,
                                  grupoproductos,
                                  codigonegocio,
                                  codigotipooferta,
                                  tipo_soli,
                                  tipo_impu,
                                  cod))
              UNION

              /* Formatted on 2009/07/06 10:09 (Formatter Plus v4.8.8) */
               (SELECT pais,
                       codigopais,
                       fecha,
                       sociedad,
                       codigoempresa,
                       codigocanal,
                       codigoacceso,
                       subacceso,
                       codigosubacceso,
                       codigotipoperiodo,
                       periodo,
                       codigoperiodo,
                       ejerciciocomercial,
                       CASE
                         WHEN importe < 0 THEN
                          decode(val_indi_debe_habe,
                                 'D',
                                 'H',
                                 'D')
                         ELSE
                          val_indi_debe_habe
                       END val_indi_debe_habe,
                       'VN' tipo_asiento,
                       cod_cuen_cont,
                       codigomarcaproducto,
                       grupoproductos,
                       codigonegocio,
                       codigotipooferta,
                       tipo_soli,
                       tipo_impu,
                       cod,
                       abs(importe)
                  FROM (SELECT pais,
                               codigopais,
                               fecha,
                               sociedad,
                               codigoempresa,
                               codigocanal,
                               codigoacceso,
                               subacceso,
                               codigosubacceso,
                               codigotipoperiodo,
                               periodo,
                               codigoperiodo,
                               ejerciciocomercial,
                               val_indi_debe_habe,
                               cod_cuen_cont,
                               codigomarcaproducto,
                               grupoproductos,
                               codigonegocio,
                               codigotipooferta,
                               tipo_soli,
                               tipo_impu,
                               cod,
                               SUM(importe80) importe
                          FROM (SELECT cab.pais_oid_pais pais,
                                       pai.cod_pais codigopais,
                                       cab.fec_fact fecha,
                                       cab.soci_oid_soci sociedad,
                                       soc.cod_soci codigoempresa,
                                       cana.cod_cana codigocanal,
                                       acce.cod_acce codigoacceso,
                                       cab.sbac_oid_sbac subacceso,
                                       sbac.cod_sbac codigosubacceso,
                                       tpe.cod_tipo_peri codigotipoperiodo,
                                       peri.peri_oid_peri periodo,
                                       pcor.cod_peri codigoperiodo,
                                       pcor.val_anio ejerciciocomercial,
                                       ipc.val_indi_debe_habe,
                                       cc.cod_cuen_cont,
                                       NULL codigomarcaproducto,
                                       NULL grupoproductos,
                                       NULL codigonegocio,
                                       NULL codigotipooferta,
                                       ipc.tspa_oid_tipo_soli_pais tipo_soli,
                                       ipc.TAIM_OID_TASA_IMPU tipo_impu,
                                       '80' cod,
                                       CASE
                                         WHEN con.cod_tipo_prog IN ('B', 'C', 'R') THEN
                                          0
                                         ELSE
                                          cab.imp_des1_sin_impu_tota
                                       END importe80
                                  FROM fac_docum_conta_cabec cab,
                                       ped_solic_cabec       psc,
                                       seg_pais              pai,
                                       seg_socie             soc,
                                       seg_subac             sbac,
                                       seg_acces             acce,
                                       seg_canal             cana,
                                       cra_perio             peri,
                                       seg_perio_corpo       pcor,
                                       seg_tipo_perio        tpe,
                                       int_param_conta_factu ipc,
                                       int_impor_conta_factu icf,
                                       ccc_cuent_conta       cc,
                                       inc_concu_tipo_prog   con
                                 WHERE nvl(cab.val_tota_paga_loca,
                                           0) >= 0
                                   AND cab.tido_oid_tipo_docu in (1,9,28,29,30,34)
                                   and cab.soca_oid_soli_cabe=psc.oid_soli_cabe
                                   AND cab.pais_oid_pais = pai.oid_pais
                                   AND cab.soci_oid_soci = soc.oid_soci
                                   AND cab.sbac_oid_sbac = sbac.oid_sbac
                                   AND cab.perd_oid_peri = peri.oid_peri
                                   AND peri.peri_oid_peri = pcor.oid_peri
                                   AND sbac.acce_oid_acce = acce.oid_acce
                                   AND acce.cana_oid_cana = cana.oid_cana
                                   AND pcor.tipe_oid_tipo_peri = tpe.oid_tipo_peri
                                   AND ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                   AND ipc.val_tipo_asie = 'VN'
                                   AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                   AND cod_impo_cont = '80'
                                   AND cab.ictp_oid_tipo_prog = con.oid_tipo_prog(+)
                                   AND cab.num_lote_cont IS NULL
                                   AND trunc(cab.fec_fact) <=
                                       to_date(psfecha,
                                               'DD/MM/YYYY')
                                     AND psc.TAIM_OID_TASA_IMPU =
                                         nvl(ipc.TAIM_OID_TASA_IMPU,
                                             decode((SELECT COUNT(*)
                                                      FROM int_param_conta_factu ipc,
                                                           int_impor_conta_factu icf,
                                                           ccc_cuent_conta       cc
                                                     WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                       AND ipc.val_tipo_asie = 'VN'
                                                       AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                       AND cod_impo_cont = '80'
                                                       AND TAIM_OID_TASA_IMPU = psc.TAIM_OID_TASA_IMPU),
                                                    0,
                                                    psc.TAIM_OID_TASA_IMPU,
                                                    0/*psc.TAIM_OID_TASA_IMPU*/))
                                     AND psc.TSPA_OID_TIPO_SOLI_PAIS =
                                         nvl(ipc.TSPA_OID_TIPO_SOLI_PAIS,
                                             decode((SELECT COUNT(*)
                                                      FROM int_param_conta_factu ipc,
                                                           int_impor_conta_factu icf,
                                                           ccc_cuent_conta       cc
                                                     WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                       AND ipc.val_tipo_asie = 'VN'
                                                       AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                       AND cod_impo_cont = '80'
                                                       AND TSPA_OID_TIPO_SOLI_PAIS = psc.TSPA_OID_TIPO_SOLI_PAIS),
                                                    0,
                                                    psc.TSPA_OID_TIPO_SOLI_PAIS,
                                                0/*psc.TSPA_OID_TIPO_SOLI_PAIS*/))
                                   AND cab.perd_oid_peri in  (select oid_peri  from periodos)

                                )
                         GROUP BY pais,
                                  codigopais,
                                  fecha,
                                  sociedad,
                                  codigoempresa,
                                  codigocanal,
                                  codigoacceso,
                                  subacceso,
                                  codigosubacceso,
                                  codigotipoperiodo,
                                  periodo,
                                  codigoperiodo,
                                  ejerciciocomercial,
                                  val_indi_debe_habe,
                                  cod_cuen_cont,
                                  codigomarcaproducto,
                                  grupoproductos,
                                  codigonegocio,
                                  codigotipooferta,
                                  tipo_soli,
                                  tipo_impu,
                                  cod))

              UNION

              /* Formatted on 2009/07/06 10:09 (Formatter Plus v4.8.8) */
               (SELECT pais,
                       codigopais,
                       fecha,
                       sociedad,
                       codigoempresa,
                       codigocanal,
                       codigoacceso,
                       subacceso,
                       codigosubacceso,
                       codigotipoperiodo,
                       periodo,
                       codigoperiodo,
                       ejerciciocomercial,
                       CASE
                         WHEN importe < 0 THEN
                          decode(val_indi_debe_habe,
                                 'D',
                                 'H',
                                 'D')
                         ELSE
                          val_indi_debe_habe
                       END val_indi_debe_habe,
                       'VN' tipo_asiento,
                       cod_cuen_cont,
                       codigomarcaproducto,
                       grupoproductos,
                       codigonegocio,
                       codigotipooferta,
                       tipo_soli,
                       tipo_impu,
                       cod,
                       abs(importe)
                  FROM (SELECT pais,
                               codigopais,
                               fecha,
                               sociedad,
                               codigoempresa,
                               codigocanal,
                               codigoacceso,
                               subacceso,
                               codigosubacceso,
                               codigotipoperiodo,
                               periodo,
                               codigoperiodo,
                               ejerciciocomercial,
                               val_indi_debe_habe,
                               cod_cuen_cont,
                               codigomarcaproducto,
                               grupoproductos,
                               codigonegocio,
                               codigotipooferta,
                               tipo_soli,
                               tipo_impu,
                               cod,
                               SUM(importe84) importe
                          FROM (SELECT cab.pais_oid_pais pais,
                                       pai.cod_pais codigopais,
                                       cab.fec_fact fecha,
                                       cab.soci_oid_soci sociedad,
                                       soc.cod_soci codigoempresa,
                                       cana.cod_cana codigocanal,
                                       acce.cod_acce codigoacceso,
                                       cab.sbac_oid_sbac subacceso,
                                       sbac.cod_sbac codigosubacceso,
                                       tpe.cod_tipo_peri codigotipoperiodo,
                                       peri.peri_oid_peri periodo,
                                       pcor.cod_peri codigoperiodo,
                                       pcor.val_anio ejerciciocomercial,
                                       ipc.val_indi_debe_habe,
                                       cc.cod_cuen_cont,
                                       NULL codigomarcaproducto,
                                       NULL grupoproductos,
                                       NULL codigonegocio,
                                       NULL codigotipooferta,
                                       ipc.tspa_oid_tipo_soli_pais tipo_soli,
                                       ipc.TAIM_OID_TASA_IMPU tipo_impu,
                                       '84' cod,
                                       CASE
                                         WHEN con.cod_tipo_prog IN ('B', 'C', 'R') THEN
                                          0
                                         ELSE
                                          cab.val_prec_cont_sin_impu_tota
                                       END importe84
                                  FROM fac_docum_conta_cabec cab,
                                       ped_solic_cabec       psc,
                                       seg_pais              pai,
                                       seg_socie             soc,
                                       seg_subac             sbac,
                                       seg_acces             acce,
                                       seg_canal             cana,
                                       cra_perio             peri,
                                       seg_perio_corpo       pcor,
                                       seg_tipo_perio        tpe,
                                       int_param_conta_factu ipc,
                                       int_impor_conta_factu icf,
                                       ccc_cuent_conta       cc,
                                       inc_concu_tipo_prog   con
                                 WHERE nvl(cab.val_tota_paga_loca,
                                           0) >= 0
                                   AND cab.tido_oid_tipo_docu in (1,9,28,29,30,34)
                                   and cab.soca_oid_soli_cabe=psc.oid_soli_cabe
                                   AND cab.pais_oid_pais = pai.oid_pais
                                   AND cab.soci_oid_soci = soc.oid_soci
                                   AND ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                   AND ipc.val_tipo_asie = 'VN'
                                   AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                   AND cod_impo_cont = '84'
                                   AND cab.sbac_oid_sbac = sbac.oid_sbac
                                   AND cab.perd_oid_peri = peri.oid_peri
                                   AND peri.peri_oid_peri = pcor.oid_peri
                                   AND sbac.acce_oid_acce = acce.oid_acce
                                   AND acce.cana_oid_cana = cana.oid_cana
                                   AND pcor.tipe_oid_tipo_peri = tpe.oid_tipo_peri
                                   AND cab.ictp_oid_tipo_prog = con.oid_tipo_prog(+)
                                     AND psc.TAIM_OID_TASA_IMPU =
                                         nvl(ipc.TAIM_OID_TASA_IMPU,
                                             decode((SELECT COUNT(*)
                                                      FROM int_param_conta_factu ipc,
                                                           int_impor_conta_factu icf,
                                                           ccc_cuent_conta       cc
                                                     WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                       AND ipc.val_tipo_asie = 'VN'
                                                       AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                       AND cod_impo_cont = '84'
                                                       AND TAIM_OID_TASA_IMPU = psc.TAIM_OID_TASA_IMPU),
                                                    0,
                                                    psc.TAIM_OID_TASA_IMPU,
                                                    0/*psc.TAIM_OID_TASA_IMPU*/))
                                     AND psc.TSPA_OID_TIPO_SOLI_PAIS =
                                         nvl(ipc.TSPA_OID_TIPO_SOLI_PAIS,
                                             decode((SELECT COUNT(*)
                                                      FROM int_param_conta_factu ipc,
                                                           int_impor_conta_factu icf,
                                                           ccc_cuent_conta       cc
                                                     WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                       AND ipc.val_tipo_asie = 'VN'
                                                       AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                       AND cod_impo_cont = '84'
                                                       AND TSPA_OID_TIPO_SOLI_PAIS = psc.TSPA_OID_TIPO_SOLI_PAIS),
                                                    0,
                                                    psc.TSPA_OID_TIPO_SOLI_PAIS,
                                                0/*psc.TSPA_OID_TIPO_SOLI_PAIS*/))
                                   AND cab.perd_oid_peri in  (select oid_peri  from periodos)
                                   AND cab.num_lote_cont IS NULL
                                   AND trunc(cab.fec_fact) <=
                                       to_date(psfecha,
                                               'DD/MM/YYYY'))
                         GROUP BY pais,
                                  codigopais,
                                  fecha,
                                  sociedad,
                                  codigoempresa,
                                  codigocanal,
                                  codigoacceso,
                                  subacceso,
                                  codigosubacceso,
                                  codigotipoperiodo,
                                  periodo,
                                  codigoperiodo,
                                  ejerciciocomercial,
                                  val_indi_debe_habe,
                                  cod_cuen_cont,
                                  codigomarcaproducto,
                                  grupoproductos,
                                  codigonegocio,
                                  codigotipooferta,
                                  tipo_soli,
                                  tipo_impu,
                                  cod))

              UNION

              /* Formatted on 2009/07/06 10:09 (Formatter Plus v4.8.8) */
               (SELECT pais,
                       codigopais,
                       fecha,
                       sociedad,
                       codigoempresa,
                       codigocanal,
                       codigoacceso,
                       subacceso,
                       codigosubacceso,
                       codigotipoperiodo,
                       periodo,
                       codigoperiodo,
                       ejerciciocomercial,
                       CASE
                         WHEN importe < 0 THEN
                          decode(val_indi_debe_habe,
                                 'D',
                                 'H',
                                 'D')
                         ELSE
                          val_indi_debe_habe
                       END val_indi_debe_habe,
                       'VN' tipo_asiento,
                       cod_cuen_cont,
                       codigomarcaproducto,
                       grupoproductos,
                       codigonegocio,
                       codigotipooferta,
                       tipo_soli,
                       tipo_impu,
                       cod,
                       abs(importe)
                  FROM (SELECT pais,
                               codigopais,
                               fecha,
                               sociedad,
                               codigoempresa,
                               codigocanal,
                               codigoacceso,
                               subacceso,
                               codigosubacceso,
                               codigotipoperiodo,
                               periodo,
                               codigoperiodo,
                               ejerciciocomercial,
                               val_indi_debe_habe,
                               cod_cuen_cont,
                               codigomarcaproducto,
                               grupoproductos,
                               codigonegocio,
                               codigotipooferta,
                               tipo_soli,
                               tipo_impu,
                               cod,
                               SUM(importe88) importe
                          FROM (SELECT cab.pais_oid_pais pais,
                                       pai.cod_pais codigopais,
                                       cab.fec_fact fecha,
                                       cab.soci_oid_soci sociedad,
                                       soc.cod_soci codigoempresa,
                                       cana.cod_cana codigocanal,
                                       acce.cod_acce codigoacceso,
                                       cab.sbac_oid_sbac subacceso,
                                       sbac.cod_sbac codigosubacceso,
                                       tpe.cod_tipo_peri codigotipoperiodo,
                                       peri.peri_oid_peri periodo,
                                       pcor.cod_peri codigoperiodo,
                                       pcor.val_anio ejerciciocomercial,
                                       ipc.val_indi_debe_habe,
                                       cc.cod_cuen_cont,
                                       NULL codigomarcaproducto,
                                       NULL grupoproductos,
                                       NULL codigonegocio,
                                       NULL codigotipooferta,
                                       ipc.tspa_oid_tipo_soli_pais tipo_soli,
                                       ipc.TAIM_OID_TASA_IMPU tipo_impu,
                                       '88' cod,
                                       CASE
                                         WHEN con.cod_tipo_prog IN ('B', 'C', 'R') THEN
                                          0
                                         ELSE
                                          cab.imp_des3_sin_impu_tota
                                       END importe88
                                  FROM fac_docum_conta_cabec cab,
                                       ped_solic_cabec       psc,
                                       seg_pais              pai,
                                       seg_socie             soc,
                                       seg_subac             sbac,
                                       seg_acces             acce,
                                       seg_canal             cana,
                                       cra_perio             peri,
                                       seg_perio_corpo       pcor,
                                       seg_tipo_perio        tpe,
                                       int_param_conta_factu ipc,
                                       int_impor_conta_factu icf,
                                       ccc_cuent_conta       cc,
                                       inc_concu_tipo_prog   con
                                 WHERE nvl(cab.val_tota_paga_loca,
                                           0) >= 0
                                   AND cab.tido_oid_tipo_docu in (1,9,28,29,30,34)
                                   and cab.soca_oid_soli_cabe=psc.oid_soli_cabe
                                   AND cab.pais_oid_pais = pai.oid_pais
                                   AND cab.soci_oid_soci = soc.oid_soci
                                   AND cab.sbac_oid_sbac = sbac.oid_sbac
                                   AND cab.perd_oid_peri = peri.oid_peri
                                   AND peri.peri_oid_peri = pcor.oid_peri
                                   AND sbac.acce_oid_acce = acce.oid_acce
                                   AND acce.cana_oid_cana = cana.oid_cana
                                   AND pcor.tipe_oid_tipo_peri = tpe.oid_tipo_peri
                                   AND ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                   AND ipc.val_tipo_asie = 'VN'
                                   AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                   AND cod_impo_cont = '88'
                                   AND cab.ictp_oid_tipo_prog = con.oid_tipo_prog(+)
                                   AND cab.num_lote_cont IS NULL
                                   AND trunc(cab.fec_fact) <=
                                       to_date(psfecha,
                                               'DD/MM/YYYY')
                                     AND psc.TAIM_OID_TASA_IMPU =
                                         nvl(ipc.TAIM_OID_TASA_IMPU,
                                             decode((SELECT COUNT(*)
                                                      FROM int_param_conta_factu ipc,
                                                           int_impor_conta_factu icf,
                                                           ccc_cuent_conta       cc
                                                     WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                       AND ipc.val_tipo_asie = 'VN'
                                                       AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                       AND cod_impo_cont = '88'
                                                       AND TAIM_OID_TASA_IMPU = psc.TAIM_OID_TASA_IMPU),
                                                    0,
                                                    psc.TAIM_OID_TASA_IMPU,
                                                    0/*psc.TAIM_OID_TASA_IMPU*/))
                                     AND psc.TSPA_OID_TIPO_SOLI_PAIS =
                                         nvl(ipc.TSPA_OID_TIPO_SOLI_PAIS,
                                             decode((SELECT COUNT(*)
                                                      FROM int_param_conta_factu ipc,
                                                           int_impor_conta_factu icf,
                                                           ccc_cuent_conta       cc
                                                     WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                       AND ipc.val_tipo_asie = 'VN'
                                                       AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                       AND cod_impo_cont = '88'
                                                       AND TSPA_OID_TIPO_SOLI_PAIS = psc.TSPA_OID_TIPO_SOLI_PAIS),
                                                    0,
                                                    psc.TSPA_OID_TIPO_SOLI_PAIS,
                                               0 /*psc.TSPA_OID_TIPO_SOLI_PAIS*/))
                                   AND cab.perd_oid_peri in  (select oid_peri  from periodos))
                         GROUP BY pais,
                                  codigopais,
                                  fecha,
                                  sociedad,
                                  codigoempresa,
                                  codigocanal,
                                  codigoacceso,
                                  subacceso,
                                  codigosubacceso,
                                  codigotipoperiodo,
                                  periodo,
                                  codigoperiodo,
                                  ejerciciocomercial,
                                  val_indi_debe_habe,
                                  cod_cuen_cont,
                                  codigomarcaproducto,
                                  grupoproductos,
                                  codigonegocio,
                                  codigotipooferta,
                                  tipo_soli,
                                  tipo_impu,
                                  cod))
              UNION

               (SELECT pais,
                       codigopais,
                       fecha,
                       sociedad,
                       codigoempresa,
                       codigocanal,
                       codigoacceso,
                       subacceso,
                       codigosubacceso,
                       codigotipoperiodo,
                       periodo,
                       codigoperiodo,
                       ejerciciocomercial,
                       CASE
                         WHEN importe < 0 THEN
                          decode(val_indi_debe_habe,
                                 'D',
                                 'H',
                                 'D')
                         ELSE
                          val_indi_debe_habe
                       END val_indi_debe_habe,
                       'VN' tipo_asiento,
                       cod_cuen_cont,
                       codigomarcaproducto,
                       grupoproductos,
                       codigonegocio,
                       codigotipooferta,
                       tipo_soli,
                       tipo_impu,
                       cod,
                       abs(importe)
                  FROM (SELECT pais,
                               codigopais,
                               fecha,
                               sociedad,
                               codigoempresa,
                               codigocanal,
                               codigoacceso,
                               subacceso,
                               codigosubacceso,
                               codigotipoperiodo,
                               periodo,
                               codigoperiodo,
                               ejerciciocomercial,
                               val_indi_debe_habe,
                               cod_cuen_cont,
                               codigomarcaproducto,
                               grupoproductos,
                               codigonegocio,
                               codigotipooferta,
                               tipo_soli,
                               tipo_impu,
                               cod,
                               SUM(importe90) importe
                          FROM (SELECT cab.pais_oid_pais pais,
                                       pai.cod_pais codigopais,
                                       cab.fec_fact fecha,
                                       cab.soci_oid_soci sociedad,
                                       soc.cod_soci codigoempresa,
                                       cana.cod_cana codigocanal,
                                       acce.cod_acce codigoacceso,
                                       cab.sbac_oid_sbac subacceso,
                                       sbac.cod_sbac codigosubacceso,
                                       tpe.cod_tipo_peri codigotipoperiodo,
                                       peri.peri_oid_peri periodo,
                                       pcor.cod_peri codigoperiodo,
                                       pcor.val_anio ejerciciocomercial,
                                       ipc.val_indi_debe_habe,
                                       cc.cod_cuen_cont,
                                       NULL codigomarcaproducto,
                                       NULL grupoproductos,
                                       NULL codigonegocio,
                                       NULL codigotipooferta,
                                       ipc.tspa_oid_tipo_soli_pais tipo_soli,
                                       ipc.TAIM_OID_TASA_IMPU tipo_impu,
                                       '90' cod,
                                       CASE
                                         WHEN con.cod_tipo_prog IN ('B', 'C', 'R') THEN
                                          0
                                         ELSE
                                          abs(cab.val_impo_iva_asum_empr)
                                       END importe90
                                  FROM fac_docum_conta_cabec cab,
                                       ped_solic_cabec       psc,
                                       seg_pais              pai,
                                       seg_socie             soc,
                                       seg_subac             sbac,
                                       seg_acces             acce,
                                       seg_canal             cana,
                                       cra_perio             peri,
                                       seg_perio_corpo       pcor,
                                       seg_tipo_perio        tpe,
                                       int_param_conta_factu ipc,
                                       int_impor_conta_factu icf,
                                       ccc_cuent_conta       cc,
                                       inc_concu_tipo_prog   con
                                 WHERE nvl(cab.val_tota_paga_loca,
                                           0) >= 0
                                   AND cab.tido_oid_tipo_docu in (1,9,28,29,30,34)
                                   and cab.soca_oid_soli_cabe=psc.oid_soli_cabe
                                   AND cab.pais_oid_pais = pai.oid_pais
                                   AND cab.soci_oid_soci = soc.oid_soci
                                   AND cab.sbac_oid_sbac = sbac.oid_sbac
                                   AND cab.perd_oid_peri = peri.oid_peri
                                   AND peri.peri_oid_peri = pcor.oid_peri
                                   AND sbac.acce_oid_acce = acce.oid_acce
                                   AND acce.cana_oid_cana = cana.oid_cana
                                   AND pcor.tipe_oid_tipo_peri = tpe.oid_tipo_peri
                                   AND ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                   AND ipc.val_tipo_asie = 'VN'
                                   AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                   AND cod_impo_cont = '90'
                                   AND cab.ictp_oid_tipo_prog = con.oid_tipo_prog(+)
                                   AND cab.num_lote_cont IS NULL
                                   AND trunc(cab.fec_fact) <=
                                       to_date(psfecha,
                                               'DD/MM/YYYY')
                                     AND psc.TAIM_OID_TASA_IMPU =
                                         nvl(ipc.TAIM_OID_TASA_IMPU,
                                             decode((SELECT COUNT(*)
                                                      FROM int_param_conta_factu ipc,
                                                           int_impor_conta_factu icf,
                                                           ccc_cuent_conta       cc
                                                     WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                       AND ipc.val_tipo_asie = 'VN'
                                                       AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                       AND cod_impo_cont = '90'
                                                       AND TAIM_OID_TASA_IMPU = psc.TAIM_OID_TASA_IMPU),
                                                    0,
                                                    psc.TAIM_OID_TASA_IMPU,
                                                    0/*psc.TAIM_OID_TASA_IMPU*/))
                                     AND psc.TSPA_OID_TIPO_SOLI_PAIS =
                                         nvl(ipc.TSPA_OID_TIPO_SOLI_PAIS,
                                             decode((SELECT COUNT(*)
                                                      FROM int_param_conta_factu ipc,
                                                           int_impor_conta_factu icf,
                                                           ccc_cuent_conta       cc
                                                     WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                       AND ipc.val_tipo_asie = 'VN'
                                                       AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                       AND cod_impo_cont = '90'
                                                       AND TSPA_OID_TIPO_SOLI_PAIS = psc.TSPA_OID_TIPO_SOLI_PAIS),
                                                    0,
                                                    psc.TSPA_OID_TIPO_SOLI_PAIS,
                                                0/*psc.TSPA_OID_TIPO_SOLI_PAIS*/))
                                   AND cab.perd_oid_peri in  (select oid_peri  from periodos))
                         GROUP BY pais,
                                  codigopais,
                                  fecha,
                                  sociedad,
                                  codigoempresa,
                                  codigocanal,
                                  codigoacceso,
                                  subacceso,
                                  codigosubacceso,
                                  codigotipoperiodo,
                                  periodo,
                                  codigoperiodo,
                                  ejerciciocomercial,
                                  val_indi_debe_habe,
                                  cod_cuen_cont,
                                  codigomarcaproducto,
                                  grupoproductos,
                                  codigonegocio,
                                  codigotipooferta,
                                  tipo_soli,
                                  tipo_impu,
                                  cod))

              UNION

               (SELECT pais,
                       codigopais,
                       fecha,
                       sociedad,
                       codigoempresa,
                       codigocanal,
                       codigoacceso,
                       subacceso,
                       codigosubacceso,
                       codigotipoperiodo,
                       periodo,
                       codigoperiodo,
                       ejerciciocomercial,
                       CASE
                         WHEN importe < 0 THEN
                          decode(val_indi_debe_habe,
                                 'D',
                                 'H',
                                 'D')
                         ELSE
                          val_indi_debe_habe
                       END val_indi_debe_habe,
                       'VN' tipo_asiento,
                       cod_cuen_cont,
                       codigomarcaproducto,
                       grupoproductos,
                       codigonegocio,
                       codigotipooferta,
                       tipo_soli,
                       tipo_impu,
                       cod,
                       abs(importe)
                  FROM (SELECT pais,
                               codigopais,
                               fecha,
                               sociedad,
                               codigoempresa,
                               codigocanal,
                               codigoacceso,
                               subacceso,
                               codigosubacceso,
                               codigotipoperiodo,
                               periodo,
                               codigoperiodo,
                               ejerciciocomercial,
                               val_indi_debe_habe,
                               cod_cuen_cont,
                               codigomarcaproducto,
                               grupoproductos,
                               codigonegocio,
                               codigotipooferta,
                               tipo_soli,
                               tipo_impu,
                               cod,
                               SUM(importe95) importe
                          FROM (SELECT cab.pais_oid_pais pais,
                                       pai.cod_pais codigopais,
                                       cab.fec_fact fecha,
                                       cab.soci_oid_soci sociedad,
                                       soc.cod_soci codigoempresa,
                                       cana.cod_cana codigocanal,
                                       acce.cod_acce codigoacceso,
                                       cab.sbac_oid_sbac subacceso,
                                       sbac.cod_sbac codigosubacceso,
                                       tpe.cod_tipo_peri codigotipoperiodo,
                                       peri.peri_oid_peri periodo,
                                       pcor.cod_peri codigoperiodo,
                                       pcor.val_anio ejerciciocomercial,
                                       ipc.val_indi_debe_habe,
                                       cc.cod_cuen_cont,
                                       NULL codigomarcaproducto,
                                       NULL grupoproductos,
                                       NULL codigonegocio,
                                       NULL codigotipooferta,
                                       ipc.tspa_oid_tipo_soli_pais tipo_soli,
                                       ipc.TAIM_OID_TASA_IMPU tipo_impu,
                                       '95' cod,
                                       CASE
                                         WHEN con.cod_tipo_prog IN ('B', 'C', 'R') THEN
                                          0
                                         ELSE
                                          abs(nvl(cab.val_tota_gast_admi_sin_impu,0))
                                       END importe95
                                  FROM fac_docum_conta_cabec cab,
                                       ped_solic_cabec       psc,
                                       seg_pais              pai,
                                       seg_socie             soc,
                                       seg_subac             sbac,
                                       seg_acces             acce,
                                       seg_canal             cana,
                                       cra_perio             peri,
                                       seg_perio_corpo       pcor,
                                       seg_tipo_perio        tpe,
                                       int_param_conta_factu ipc,
                                       int_impor_conta_factu icf,
                                       ccc_cuent_conta       cc,
                                       inc_concu_tipo_prog   con
                                 WHERE nvl(cab.val_tota_paga_loca,
                                           0) >= 0
                                   AND cab.tido_oid_tipo_docu in (1,9,28,29,30,34)
                                   and cab.soca_oid_soli_cabe=psc.oid_soli_cabe
                                   AND cab.pais_oid_pais = pai.oid_pais
                                   AND cab.soci_oid_soci = soc.oid_soci
                                   AND cab.sbac_oid_sbac = sbac.oid_sbac
                                   AND cab.perd_oid_peri = peri.oid_peri
                                   AND peri.peri_oid_peri = pcor.oid_peri
                                   AND sbac.acce_oid_acce = acce.oid_acce
                                   AND acce.cana_oid_cana = cana.oid_cana
                                   AND pcor.tipe_oid_tipo_peri = tpe.oid_tipo_peri
                                   AND ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                   AND ipc.val_tipo_asie = 'VN'
                                   AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                   AND cod_impo_cont = '95'
                                   AND cab.ictp_oid_tipo_prog = con.oid_tipo_prog(+)
                                   AND cab.num_lote_cont IS NULL
                                   AND trunc(cab.fec_fact) <=
                                       to_date(psfecha,
                                               'DD/MM/YYYY')
                                     AND psc.TAIM_OID_TASA_IMPU =
                                         nvl(ipc.TAIM_OID_TASA_IMPU,
                                             decode((SELECT COUNT(*)
                                                      FROM int_param_conta_factu ipc,
                                                           int_impor_conta_factu icf,
                                                           ccc_cuent_conta       cc
                                                     WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                       AND ipc.val_tipo_asie = 'VN'
                                                       AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                       AND cod_impo_cont = '95'
                                                       AND TAIM_OID_TASA_IMPU = psc.TAIM_OID_TASA_IMPU),
                                                    0,
                                                    psc.TAIM_OID_TASA_IMPU,
                                                    0/*psc.TAIM_OID_TASA_IMPU*/))
                                     AND psc.TSPA_OID_TIPO_SOLI_PAIS =
                                         nvl(ipc.TSPA_OID_TIPO_SOLI_PAIS,
                                             decode((SELECT COUNT(*)
                                                      FROM int_param_conta_factu ipc,
                                                           int_impor_conta_factu icf,
                                                           ccc_cuent_conta       cc
                                                     WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                       AND ipc.val_tipo_asie = 'VN'
                                                       AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                       AND cod_impo_cont = '95'
                                                       AND TSPA_OID_TIPO_SOLI_PAIS = psc.TSPA_OID_TIPO_SOLI_PAIS),
                                                    0,
                                                    psc.TSPA_OID_TIPO_SOLI_PAIS,
                                                0/*psc.TSPA_OID_TIPO_SOLI_PAIS*/))
                                   AND cab.perd_oid_peri in  (select oid_peri  from periodos))
                         GROUP BY pais,
                                  codigopais,
                                  fecha,
                                  sociedad,
                                  codigoempresa,
                                  codigocanal,
                                  codigoacceso,
                                  subacceso,
                                  codigosubacceso,
                                  codigotipoperiodo,
                                  periodo,
                                  codigoperiodo,
                                  ejerciciocomercial,
                                  val_indi_debe_habe,
                                  cod_cuen_cont,
                                  codigomarcaproducto,
                                  grupoproductos,
                                  codigonegocio,
                                  codigotipooferta,
                                  tipo_soli,
                                  tipo_impu,
                                  cod))
              UNION

               (SELECT pais,
                       codigopais,
                       fecha,
                       sociedad,
                       codigoempresa,
                       codigocanal,
                       codigoacceso,
                       subacceso,
                       codigosubacceso,
                       codigotipoperiodo,
                       periodo,
                       codigoperiodo,
                       ejerciciocomercial,
                       CASE
                         WHEN importe < 0 THEN
                          decode(val_indi_debe_habe,
                                 'D',
                                 'H',
                                 'D')
                         ELSE
                          val_indi_debe_habe
                       END val_indi_debe_habe,
                       'VN' tipo_asiento,
                       cod_cuen_cont,
                       codigomarcaproducto,
                       grupoproductos,
                       codigonegocio,
                       codigotipooferta,
                       tipo_soli,
                       tipo_impu,
                       cod,
                       abs(importe)
                  FROM (SELECT pais,
                               codigopais,
                               fecha,
                               sociedad,
                               codigoempresa,
                               codigocanal,
                               codigoacceso,
                               subacceso,
                               codigosubacceso,
                               codigotipoperiodo,
                               periodo,
                               codigoperiodo,
                               ejerciciocomercial,
                               val_indi_debe_habe,
                               cod_cuen_cont,
                               codigomarcaproducto,
                               grupoproductos,
                               codigonegocio,
                               codigotipooferta,
                               tipo_soli,
                               tipo_impu,
                               cod,
                               SUM(importe96) importe
                          FROM (SELECT cab.pais_oid_pais pais,
                                       pai.cod_pais codigopais,
                                       cab.fec_fact fecha,
                                       cab.soci_oid_soci sociedad,
                                       soc.cod_soci codigoempresa,
                                       cana.cod_cana codigocanal,
                                       acce.cod_acce codigoacceso,
                                       cab.sbac_oid_sbac subacceso,
                                       sbac.cod_sbac codigosubacceso,
                                       tpe.cod_tipo_peri codigotipoperiodo,
                                       peri.peri_oid_peri periodo,
                                       pcor.cod_peri codigoperiodo,
                                       pcor.val_anio ejerciciocomercial,
                                       ipc.val_indi_debe_habe,
                                       cc.cod_cuen_cont,
                                       NULL codigomarcaproducto,
                                       NULL grupoproductos,
                                       NULL codigonegocio,
                                       NULL codigotipooferta,
                                       ipc.tspa_oid_tipo_soli_pais tipo_soli,
                                       ipc.TAIM_OID_TASA_IMPU tipo_impu,
                                       '96' cod,
                                       CASE
                                         WHEN con.cod_tipo_prog IN ('B', 'C', 'R') THEN
                                          0
                                         ELSE
                                          abs(nvl(cab.val_impo_rete_desc,0))
                                       END importe96
                                  FROM fac_docum_conta_cabec cab,
                                       ped_solic_cabec       psc,
                                       seg_pais              pai,
                                       seg_socie             soc,
                                       seg_subac             sbac,
                                       seg_acces             acce,
                                       seg_canal             cana,
                                       cra_perio             peri,
                                       seg_perio_corpo       pcor,
                                       seg_tipo_perio        tpe,
                                       int_param_conta_factu ipc,
                                       int_impor_conta_factu icf,
                                       ccc_cuent_conta       cc,
                                       inc_concu_tipo_prog   con
                                 WHERE nvl(cab.val_tota_paga_loca,
                                           0) >= 0
                                   AND cab.tido_oid_tipo_docu in (1,9,28,29,30,34)
                                   and cab.soca_oid_soli_cabe=psc.oid_soli_cabe
                                   AND cab.pais_oid_pais = pai.oid_pais
                                   AND cab.soci_oid_soci = soc.oid_soci
                                   AND cab.sbac_oid_sbac = sbac.oid_sbac
                                   AND cab.perd_oid_peri = peri.oid_peri
                                   AND peri.peri_oid_peri = pcor.oid_peri
                                   AND sbac.acce_oid_acce = acce.oid_acce
                                   AND acce.cana_oid_cana = cana.oid_cana
                                   AND pcor.tipe_oid_tipo_peri = tpe.oid_tipo_peri
                                   AND ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                   AND ipc.val_tipo_asie = 'VN'
                                   AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                   AND cod_impo_cont = '96'
                                   AND cab.ictp_oid_tipo_prog = con.oid_tipo_prog(+)
                                   AND cab.num_lote_cont IS NULL
                                   AND trunc(cab.fec_fact) <=
                                       to_date(psfecha,
                                               'DD/MM/YYYY')
                                     AND psc.TAIM_OID_TASA_IMPU =
                                         nvl(ipc.TAIM_OID_TASA_IMPU,
                                             decode((SELECT COUNT(*)
                                                      FROM int_param_conta_factu ipc,
                                                           int_impor_conta_factu icf,
                                                           ccc_cuent_conta       cc
                                                     WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                       AND ipc.val_tipo_asie = 'VN'
                                                       AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                       AND cod_impo_cont = '96'
                                                       AND TAIM_OID_TASA_IMPU = psc.TAIM_OID_TASA_IMPU),
                                                    0,
                                                    psc.TAIM_OID_TASA_IMPU,
                                                    0/*psc.TAIM_OID_TASA_IMPU*/))
                                     AND psc.TSPA_OID_TIPO_SOLI_PAIS =
                                         nvl(ipc.TSPA_OID_TIPO_SOLI_PAIS,
                                             decode((SELECT COUNT(*)
                                                      FROM int_param_conta_factu ipc,
                                                           int_impor_conta_factu icf,
                                                           ccc_cuent_conta       cc
                                                     WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                       AND ipc.val_tipo_asie = 'VN'
                                                       AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                       AND cod_impo_cont = '96'
                                                       AND TSPA_OID_TIPO_SOLI_PAIS = psc.TSPA_OID_TIPO_SOLI_PAIS),
                                                    0,
                                                    psc.TSPA_OID_TIPO_SOLI_PAIS,
                                                0/*psc.TSPA_OID_TIPO_SOLI_PAIS*/))
                                   AND cab.perd_oid_peri in  (select oid_peri  from periodos))
                         GROUP BY pais,
                                  codigopais,
                                  fecha,
                                  sociedad,
                                  codigoempresa,
                                  codigocanal,
                                  codigoacceso,
                                  subacceso,
                                  codigosubacceso,
                                  codigotipoperiodo,
                                  periodo,
                                  codigoperiodo,
                                  ejerciciocomercial,
                                  val_indi_debe_habe,
                                  cod_cuen_cont,
                                  codigomarcaproducto,
                                  grupoproductos,
                                  codigonegocio,
                                  codigotipooferta,
                                  tipo_soli,
                                  tipo_impu,
                                  cod))
              UNION

              ---- DETALLE

              /* Formatted on 2009/07/06 10:09 (Formatter Plus v4.8.8) */
              SELECT pais,
                     codigopais,
                     fecha,
                     sociedad,
                     codigoempresa,
                     codigocanal,
                     codigoacceso,
                     subacceso,
                     codigosubacceso,
                     codigotipoperiodo,
                     periodo,
                     codigoperiodo,
                     ejerciciocomercial,
                     val_indi_debe_habe,
                     'VN' tipo_asiento,
                     cod_cuen_cont,
                     codigomarcaproducto,
                     grupoproductos,
                     codigonegocio,
                     codigotipooferta,
                     tipo_soli,
                     tipo_impu,
                     '01',
                     SUM(abs(importe))
                FROM (SELECT cab.pais_oid_pais pais,
                             pai.cod_pais codigopais,
                             cab.fec_fact fecha,
                             cab.soci_oid_soci sociedad,
                             soc.cod_soci codigoempresa,
                             cana.cod_cana codigocanal,
                             acce.cod_acce codigoacceso,
                             cab.sbac_oid_sbac subacceso,
                             sbac.cod_sbac codigosubacceso,
                             tpe.cod_tipo_peri codigotipoperiodo,
                             peri.peri_oid_peri periodo,
                             pcor.cod_peri codigoperiodo,
                             pcor.val_anio ejerciciocomercial,
                             CASE
                               WHEN lin.val_prec_sin_impu_tota_loca -
                                    decode((SELECT nvl(par.ind_impu_prod_naci,
                                                      0)
                                             FROM seg_param_inter_pais par),
                                           1,
                                           nvl(decode(lin.val_prec_cata_unit_loca,0,0,lin.imp_impu_tota_prod_naci),
                                               0),
                                           0) < 0 THEN
                                decode(xx.val_indi_debe_habe,
                                       'D',
                                       'H',
                                       'D')
                               ELSE
                                xx.val_indi_debe_habe
                             END val_indi_debe_habe,
                             xx.cod_cuen_cont,
                             mpro.cod_marc_prod codigomarcaproducto,
                             pro.val_grup_arti grupoproductos,
                             nego.cod_nego codigonegocio,
                             tof.cod_tipo_ofer codigotipooferta,
                             xx.tspa_oid_tipo_soli_pais tipo_soli,
                             xx.TAIM_OID_TASA_IMPU tipo_impu,
                             CASE
                               WHEN con.cod_tipo_prog IN ('B', 'C', 'R') THEN
                                0
                               ELSE
                                (lin.val_prec_sin_impu_tota_loca -
                                decode((SELECT nvl(par.ind_impu_prod_naci,
                                                   0)
                                          FROM seg_param_inter_pais par),
                                        1,
                                        nvl(decode(lin.val_prec_cata_unit_loca,0,0,lin.imp_impu_tota_prod_naci),
                                            0),
                                        0))
                             END importe
                        FROM fac_docum_conta_cabec cab,
                             fac_docum_conta_linea lin,
                             seg_pais pai,
                             seg_socie soc,
                             mae_produ pro,
                             ped_solic_cabec psc,
                             ped_solic_posic sop,
                             pre_ofert_detal dof,
                             seg_subac sbac,
                             seg_acces acce,
                             seg_canal cana,
                             cra_perio peri,
                             seg_perio_corpo pcor,
                             seg_tipo_perio tpe,
                             seg_marca_produ mpro,
                             mae_negoc nego,
                             pre_tipo_ofert tof,
                             inc_concu_tipo_prog con,
                             (SELECT cod_cuen_cont,
                                     val_indi_debe_habe,
                                     val_grup_arti,
                                     tspa_oid_tipo_soli_pais,
                                     taim_oid_tasa_impu
                                FROM int_param_conta_factu ipc,
                                     int_impor_conta_factu icf,
                                     ccc_cuent_conta       cc
                               WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                 AND ipc.val_tipo_asie = 'VN'
                                 AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                 AND cod_impo_cont = '01') xx
                       WHERE nvl(cab.val_tota_paga_loca,
                                 0) >= 0
                         AND lin.num_unid_aten > 0
                         AND cab.tido_oid_tipo_docu in (1,9,28,29,30,34)
                         AND lin.dcca_oid_cabe = cab.oid_cabe
                         AND cab.pais_oid_pais = pai.oid_pais
                         AND cab.soci_oid_soci = soc.oid_soci
                         AND lin.prod_oid_prod = pro.oid_prod
                         AND sop.soca_oid_soli_cabe = psc.oid_soli_cabe
                         AND lin.sopo_oid_soli_posi = sop.oid_soli_posi
                         AND sop.ofde_oid_deta_ofer = dof.oid_deta_ofer(+)
                         AND cab.sbac_oid_sbac = sbac.oid_sbac
                         AND cab.perd_oid_peri = peri.oid_peri
                         AND peri.peri_oid_peri = pcor.oid_peri
                         AND sbac.acce_oid_acce = acce.oid_acce
                         AND acce.cana_oid_cana = cana.oid_cana
                         AND pcor.tipe_oid_tipo_peri = tpe.oid_tipo_peri
                         AND pro.mapr_oid_marc_prod = mpro.oid_marc_prod(+)
                         AND pro.nego_oid_nego = nego.oid_nego(+)
                         AND dof.tofe_oid_tipo_ofer = tof.oid_tipo_ofer(+)
                         AND pro.val_grup_arti =
                             nvl(xx.val_grup_arti,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'VN'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '01'
                                           AND val_grup_arti = pro.val_grup_arti
                                           ----*----
                                           and ((SELECT COUNT(*)
                                                  FROM int_param_conta_factu ipc,
                                                       int_impor_conta_factu icf,
                                                       ccc_cuent_conta       cc
                                                 WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                                   AND ipc.val_tipo_asie = 'VN'
                                                   AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                                   AND cod_impo_cont = '01'
                                                   AND TSPA_OID_TIPO_SOLI_PAIS = psc.TSPA_OID_TIPO_SOLI_PAIS) = 0)
                                           ),
                                        0,
                                        pro.val_grup_arti,
                                        'XXXX'))
                         AND sop.TAIM_OID_TASA_IMPU =
                             nvl(xx.TAIM_OID_TASA_IMPU,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'VN'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '01'
                                           AND TAIM_OID_TASA_IMPU = sop.TAIM_OID_TASA_IMPU),
                                        0,
                                        sop.TAIM_OID_TASA_IMPU,
                                        0/*sop.TAIM_OID_TASA_IMPU*/))
                         AND psc.TSPA_OID_TIPO_SOLI_PAIS =
                             nvl(xx.TSPA_OID_TIPO_SOLI_PAIS,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'VN'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '01'
                                           AND TSPA_OID_TIPO_SOLI_PAIS = psc.TSPA_OID_TIPO_SOLI_PAIS),
                                        0,
                                        psc.TSPA_OID_TIPO_SOLI_PAIS,
                                        0/*psc.TSPA_OID_TIPO_SOLI_PAIS*/))
                         AND cab.ictp_oid_tipo_prog = con.oid_tipo_prog(+)
                         AND cab.num_lote_cont IS NULL
                         AND trunc(cab.fec_fact) <= to_date(psfecha,
                                                            'DD/MM/YYYY')
                         AND cab.perd_oid_peri in  (select oid_peri  from periodos))
               WHERE importe <> 0
                 AND cod_cuen_cont IS NOT NULL
               GROUP BY pais,
                        codigopais,
                        fecha,
                        sociedad,
                        codigoempresa,
                        codigocanal,
                        codigoacceso,
                        subacceso,
                        codigosubacceso,
                        codigotipoperiodo,
                        periodo,
                        codigoperiodo,
                        ejerciciocomercial,
                        val_indi_debe_habe,
                        cod_cuen_cont,
                        codigomarcaproducto,
                        grupoproductos,
                        codigonegocio,
                        codigotipooferta,
                        tipo_soli,
                        tipo_impu,
                        '01'

              UNION

              /* Formatted on 2009/07/06 10:09 (Formatter Plus v4.8.8) */
              SELECT pais,
                     codigopais,
                     fecha,
                     sociedad,
                     codigoempresa,
                     codigocanal,
                     codigoacceso,
                     subacceso,
                     codigosubacceso,
                     codigotipoperiodo,
                     periodo,
                     codigoperiodo,
                     ejerciciocomercial,
                     val_indi_debe_habe,
                     'VN' tipo_asiento,
                     cod_cuen_cont,
                     codigomarcaproducto,
                     grupoproductos,
                     codigonegocio,
                     codigotipooferta,
                     tipo_soli,
                     tipo_impu,
                     '03',
                     SUM(abs(importe))
                FROM (SELECT cab.pais_oid_pais pais,
                             pai.cod_pais codigopais,
                             cab.fec_fact fecha,
                             cab.soci_oid_soci sociedad,
                             soc.cod_soci codigoempresa,
                             cana.cod_cana codigocanal,
                             acce.cod_acce codigoacceso,
                             cab.sbac_oid_sbac subacceso,
                             sbac.cod_sbac codigosubacceso,
                             tpe.cod_tipo_peri codigotipoperiodo,
                             peri.peri_oid_peri periodo,
                             pcor.cod_peri codigoperiodo,
                             pcor.val_anio ejerciciocomercial,
                             CASE
                               WHEN decode((SELECT nvl(par.ind_impu_prod_grat,
                                                      0)
                                             FROM seg_param_inter_pais par),
                                           1,
                                           (lin.imp_impu_tota_loca *
                                           decode(lin.val_prec_cata_unit_loca,
                                                   0,
                                                   1,
                                                   0)),
                                           lin.imp_impu_tota_loca) < 0 THEN
                                decode(xx.val_indi_debe_habe,
                                       'D',
                                       'H',
                                       'D')
                               ELSE
                                xx.val_indi_debe_habe
                             END val_indi_debe_habe,
                             xx.cod_cuen_cont,
                             mpro.cod_marc_prod codigomarcaproducto,
                             pro.val_grup_arti grupoproductos,
                             nego.cod_nego codigonegocio,
                             tof.cod_tipo_ofer codigotipooferta,
                             xx.tspa_oid_tipo_soli_pais tipo_soli,
                             xx.TAIM_OID_TASA_IMPU tipo_impu,
                             decode((SELECT nvl(par.ind_impu_prod_grat,
                                               0)
                                      FROM seg_param_inter_pais par),
                                    1,
                                    (lin.imp_impu_tota_loca * decode(lin.val_prec_cata_unit_loca,
                                                                     0,
                                                                     1,
                                                                     0)),
                                    lin.imp_impu_tota_loca) importe
                        FROM fac_docum_conta_cabec cab,
                             fac_docum_conta_linea lin,
                             seg_pais pai,
                             seg_socie soc,
                             mae_produ pro,
                             ped_solic_cabec psc,
                             ped_solic_posic sop,
                             pre_ofert_detal dof,
                             seg_subac sbac,
                             seg_acces acce,
                             seg_canal cana,
                             cra_perio peri,
                             seg_perio_corpo pcor,
                             seg_tipo_perio tpe,
                             seg_marca_produ mpro,
                             mae_negoc nego,
                             pre_tipo_ofert tof,
                             inc_concu_tipo_prog con,
                             (SELECT cod_cuen_cont,
                                     val_indi_debe_habe,
                                     val_grup_arti,
                                     tspa_oid_tipo_soli_pais,
                                     taim_oid_tasa_impu
                                FROM int_param_conta_factu ipc,
                                     int_impor_conta_factu icf,
                                     ccc_cuent_conta       cc
                               WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                 AND ipc.val_tipo_asie = 'VN'
                                 AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                 AND cod_impo_cont = '03') xx
                       WHERE nvl(cab.val_tota_paga_loca,
                                 0) >= 0
                         AND lin.num_unid_aten > 0
                         AND cab.tido_oid_tipo_docu in (1,9,28,29,30,34)
                         AND lin.dcca_oid_cabe = cab.oid_cabe
                         AND cab.pais_oid_pais = pai.oid_pais
                         AND cab.soci_oid_soci = soc.oid_soci
                         AND lin.prod_oid_prod = pro.oid_prod
                         AND sop.soca_oid_soli_cabe = psc.oid_soli_cabe
                         AND lin.sopo_oid_soli_posi = sop.oid_soli_posi
                         AND sop.ofde_oid_deta_ofer = dof.oid_deta_ofer(+)
                         AND cab.sbac_oid_sbac = sbac.oid_sbac
                         AND cab.perd_oid_peri = peri.oid_peri
                         AND peri.peri_oid_peri = pcor.oid_peri
                         AND sbac.acce_oid_acce = acce.oid_acce
                         AND acce.cana_oid_cana = cana.oid_cana
                         AND pcor.tipe_oid_tipo_peri = tpe.oid_tipo_peri
                         AND pro.mapr_oid_marc_prod = mpro.oid_marc_prod(+)
                         AND pro.nego_oid_nego = nego.oid_nego(+)
                         AND dof.tofe_oid_tipo_ofer = tof.oid_tipo_ofer(+)
                         AND cab.ictp_oid_tipo_prog = con.oid_tipo_prog(+)
                         AND pro.val_grup_arti =
                             nvl(xx.val_grup_arti,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'VN'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '03'
                                           AND val_grup_arti = pro.val_grup_arti),
                                        0,
                                        pro.val_grup_arti,
                                        'XXXX'))
                         AND sop.TAIM_OID_TASA_IMPU =
                             nvl(xx.TAIM_OID_TASA_IMPU,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'VN'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '03'
                                           AND TAIM_OID_TASA_IMPU = sop.TAIM_OID_TASA_IMPU),
                                        0,
                                        sop.TAIM_OID_TASA_IMPU,
                                        0/*sop.TAIM_OID_TASA_IMPU*/))
                         AND psc.TSPA_OID_TIPO_SOLI_PAIS =
                             nvl(xx.TSPA_OID_TIPO_SOLI_PAIS,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'VN'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '03'
                                           AND TSPA_OID_TIPO_SOLI_PAIS = psc.TSPA_OID_TIPO_SOLI_PAIS),
                                        0,
                                        psc.TSPA_OID_TIPO_SOLI_PAIS,
                                        0/*psc.TSPA_OID_TIPO_SOLI_PAIS*/))
                         AND cab.num_lote_cont IS NULL
                         AND trunc(cab.fec_fact) <= to_date(psfecha,
                                                            'DD/MM/YYYY')
                         AND cab.perd_oid_peri in  (select oid_peri  from periodos))
               WHERE importe <> 0
                 AND cod_cuen_cont IS NOT NULL
               GROUP BY pais,
                        codigopais,
                        fecha,
                        sociedad,
                        codigoempresa,
                        codigocanal,
                        codigoacceso,
                        subacceso,
                        codigosubacceso,
                        codigotipoperiodo,
                        periodo,
                        codigoperiodo,
                        ejerciciocomercial,
                        val_indi_debe_habe,
                        cod_cuen_cont,
                        codigomarcaproducto,
                        grupoproductos,
                        codigonegocio,
                        codigotipooferta,
                        tipo_soli,
                        tipo_impu,
                        '03'

              UNION

              SELECT pais,
                     codigopais,
                     fecha,
                     sociedad,
                     codigoempresa,
                     codigocanal,
                     codigoacceso,
                     subacceso,
                     codigosubacceso,
                     codigotipoperiodo,
                     periodo,
                     codigoperiodo,
                     ejerciciocomercial,
                     val_indi_debe_habe,
                     'VN' tipo_asiento,
                     cod_cuen_cont,
                     codigomarcaproducto,
                     grupoproductos,
                     codigonegocio,
                     codigotipooferta,
                     tipo_soli,
                     tipo_impu,
                     '04',
                     SUM(abs(importe))
                FROM (SELECT cab.pais_oid_pais pais,
                             pai.cod_pais codigopais,
                             cab.fec_fact fecha,
                             cab.soci_oid_soci sociedad,
                             soc.cod_soci codigoempresa,
                             cana.cod_cana codigocanal,
                             acce.cod_acce codigoacceso,
                             cab.sbac_oid_sbac subacceso,
                             sbac.cod_sbac codigosubacceso,
                             tpe.cod_tipo_peri codigotipoperiodo,
                             peri.peri_oid_peri periodo,
                             pcor.cod_peri codigoperiodo,
                             pcor.val_anio ejerciciocomercial,
                             CASE
                               WHEN
                                 --decode((SELECT nvl(par.ind_impu_prod_naci,
                                 --                     0)
                                 --            FROM seg_param_inter_pais par),
                                 --          1,
                                           nvl(lin.imp_impu_tota_prod_naci,
                                               0)--,
                                 --          0)
                                 < 0 THEN
                                decode(xx.val_indi_debe_habe,
                                       'D',
                                       'H',
                                       'D')
                               ELSE
                                xx.val_indi_debe_habe
                             END val_indi_debe_habe,
                             xx.cod_cuen_cont,
                             mpro.cod_marc_prod codigomarcaproducto,
                             pro.val_grup_arti grupoproductos,
                             nego.cod_nego codigonegocio,
                             tof.cod_tipo_ofer codigotipooferta,
                             xx.tspa_oid_tipo_soli_pais tipo_soli,
                             xx.TAIM_OID_TASA_IMPU tipo_impu,
                             --decode((SELECT nvl(par.ind_impu_prod_naci,
                             --                  0)
                             --         FROM seg_param_inter_pais par),
                             --       1,
                                    nvl(decode(lin.val_prec_cata_unit_loca,0,0,lin.imp_impu_tota_prod_naci),0)--,
                             --       0)
                             importe
                        FROM fac_docum_conta_cabec cab,
                             fac_docum_conta_linea lin,
                             seg_pais pai,
                             seg_socie soc,
                             mae_produ pro,
                             ped_solic_cabec psc,
                             ped_solic_posic sop,
                             pre_ofert_detal dof,
                             seg_subac sbac,
                             seg_acces acce,
                             seg_canal cana,
                             cra_perio peri,
                             seg_perio_corpo pcor,
                             seg_tipo_perio tpe,
                             seg_marca_produ mpro,
                             mae_negoc nego,
                             pre_tipo_ofert tof,
                             inc_concu_tipo_prog con,
                             (SELECT cod_cuen_cont,
                                     val_indi_debe_habe,
                                     val_grup_arti,
                                     tspa_oid_tipo_soli_pais,
                                     taim_oid_tasa_impu
                                FROM int_param_conta_factu ipc,
                                     int_impor_conta_factu icf,
                                     ccc_cuent_conta       cc
                               WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                 AND ipc.val_tipo_asie = 'VN'
                                 AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                 AND cod_impo_cont = '04') xx
                       WHERE nvl(cab.val_tota_paga_loca,
                                 0) >= 0
                         AND lin.num_unid_aten > 0
                         AND cab.tido_oid_tipo_docu in (1,9,28,29,30,34)
                         AND lin.dcca_oid_cabe = cab.oid_cabe
                         AND cab.pais_oid_pais = pai.oid_pais
                         AND cab.soci_oid_soci = soc.oid_soci
                         AND lin.prod_oid_prod = pro.oid_prod
                         AND sop.soca_oid_soli_cabe = psc.oid_soli_cabe
                         AND lin.sopo_oid_soli_posi = sop.oid_soli_posi
                         AND sop.ofde_oid_deta_ofer = dof.oid_deta_ofer(+)
                         AND cab.sbac_oid_sbac = sbac.oid_sbac
                         AND cab.perd_oid_peri = peri.oid_peri
                         AND peri.peri_oid_peri = pcor.oid_peri
                         AND sbac.acce_oid_acce = acce.oid_acce
                         AND acce.cana_oid_cana = cana.oid_cana
                         AND pcor.tipe_oid_tipo_peri = tpe.oid_tipo_peri
                         AND pro.mapr_oid_marc_prod = mpro.oid_marc_prod(+)
                         AND pro.nego_oid_nego = nego.oid_nego(+)
                         AND dof.tofe_oid_tipo_ofer = tof.oid_tipo_ofer(+)
                         AND pro.val_grup_arti =
                             nvl(xx.val_grup_arti,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'VN'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '04'
                                           AND val_grup_arti = pro.val_grup_arti),
                                        0,
                                        pro.val_grup_arti,
                                        'XXXX'))
                         AND sop.TAIM_OID_TASA_IMPU =
                             nvl(xx.TAIM_OID_TASA_IMPU,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'VN'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '04'
                                           AND TAIM_OID_TASA_IMPU = sop.TAIM_OID_TASA_IMPU),
                                        0,
                                        sop.TAIM_OID_TASA_IMPU,
                                        0/*sop.TAIM_OID_TASA_IMPU*/))
                         AND psc.TSPA_OID_TIPO_SOLI_PAIS =
                             nvl(xx.TSPA_OID_TIPO_SOLI_PAIS,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'VN'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '04'
                                           AND TSPA_OID_TIPO_SOLI_PAIS = psc.TSPA_OID_TIPO_SOLI_PAIS),
                                        0,
                                        psc.TSPA_OID_TIPO_SOLI_PAIS,
                                        0/*psc.TSPA_OID_TIPO_SOLI_PAIS*/))
                         AND cab.ictp_oid_tipo_prog = con.oid_tipo_prog(+)
                         AND cab.num_lote_cont IS NULL
                         AND trunc(cab.fec_fact) <= to_date(psfecha,
                                                            'DD/MM/YYYY')
                         AND cab.perd_oid_peri in  (select oid_peri  from periodos))
               WHERE importe <> 0
                 AND cod_cuen_cont IS NOT NULL
               GROUP BY pais,
                        codigopais,
                        fecha,
                        sociedad,
                        codigoempresa,
                        codigocanal,
                        codigoacceso,
                        subacceso,
                        codigosubacceso,
                        codigotipoperiodo,
                        periodo,
                        codigoperiodo,
                        ejerciciocomercial,
                        val_indi_debe_habe,
                        cod_cuen_cont,
                        codigomarcaproducto,
                        grupoproductos,
                        codigonegocio,
                        codigotipooferta,
                        tipo_soli,
                        tipo_impu,
                        '04'

              UNION

              SELECT pais,
                     codigopais,
                     fecha,
                     sociedad,
                     codigoempresa,
                     codigocanal,
                     codigoacceso,
                     subacceso,
                     codigosubacceso,
                     codigotipoperiodo,
                     periodo,
                     codigoperiodo,
                     ejerciciocomercial,
                     val_indi_debe_habe,
                     'VN' tipo_asiento,
                     cod_cuen_cont,
                     codigomarcaproducto,
                     grupoproductos,
                     codigonegocio,
                     codigotipooferta,
                     tipo_soli,
                     tipo_impu,
                     '06',
                     SUM(abs(importe))
                FROM (SELECT cab.pais_oid_pais pais,
                             pai.cod_pais codigopais,
                             cab.fec_fact fecha,
                             cab.soci_oid_soci sociedad,
                             soc.cod_soci codigoempresa,
                             cana.cod_cana codigocanal,
                             acce.cod_acce codigoacceso,
                             cab.sbac_oid_sbac subacceso,
                             sbac.cod_sbac codigosubacceso,
                             tpe.cod_tipo_peri codigotipoperiodo,
                             peri.peri_oid_peri periodo,
                             pcor.cod_peri codigoperiodo,
                             pcor.val_anio ejerciciocomercial,
                             CASE
                               WHEN
                                       nvl(decode(lin.val_prec_cata_unit_loca,0,lin.imp_impu_tota_prod_naci*0.12,0),0)
                                 < 0 THEN
                                decode(xx.val_indi_debe_habe,
                                       'D',
                                       'H',
                                       'D')
                               ELSE
                                xx.val_indi_debe_habe
                             END val_indi_debe_habe,
                             xx.cod_cuen_cont,
                             mpro.cod_marc_prod codigomarcaproducto,
                             pro.val_grup_arti grupoproductos,
                             nego.cod_nego codigonegocio,
                             tof.cod_tipo_ofer codigotipooferta,
                             xx.tspa_oid_tipo_soli_pais tipo_soli,
                             xx.TAIM_OID_TASA_IMPU tipo_impu,
                             nvl(decode(lin.val_prec_cata_unit_loca,0,lin.imp_impu_tota_prod_naci*0.12,0),0) importe
                        FROM fac_docum_conta_cabec cab,
                             fac_docum_conta_linea lin,
                             seg_pais pai,
                             seg_socie soc,
                             mae_produ pro,
                             ped_solic_cabec psc,
                             ped_solic_posic sop,
                             pre_ofert_detal dof,
                             seg_subac sbac,
                             seg_acces acce,
                             seg_canal cana,
                             cra_perio peri,
                             seg_perio_corpo pcor,
                             seg_tipo_perio tpe,
                             seg_marca_produ mpro,
                             mae_negoc nego,
                             pre_tipo_ofert tof,
                             inc_concu_tipo_prog con,
                             (SELECT cod_cuen_cont,
                                     val_indi_debe_habe,
                                     val_grup_arti,
                                     tspa_oid_tipo_soli_pais,
                                     taim_oid_tasa_impu
                                FROM int_param_conta_factu ipc,
                                     int_impor_conta_factu icf,
                                     ccc_cuent_conta       cc
                               WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                 AND ipc.val_tipo_asie = 'VN'
                                 AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                 AND cod_impo_cont = '06') xx
                       WHERE nvl(cab.val_tota_paga_loca,
                                 0) >= 0
                         AND lin.num_unid_aten > 0
                         AND cab.tido_oid_tipo_docu in (1,9,28,29,30,34)
                         AND lin.dcca_oid_cabe = cab.oid_cabe
                         AND cab.pais_oid_pais = pai.oid_pais
                         AND cab.soci_oid_soci = soc.oid_soci
                         AND lin.prod_oid_prod = pro.oid_prod
                         AND sop.soca_oid_soli_cabe = psc.oid_soli_cabe
                         AND lin.sopo_oid_soli_posi = sop.oid_soli_posi
                         AND sop.ofde_oid_deta_ofer = dof.oid_deta_ofer(+)
                         AND cab.sbac_oid_sbac = sbac.oid_sbac
                         AND cab.perd_oid_peri = peri.oid_peri
                         AND peri.peri_oid_peri = pcor.oid_peri
                         AND sbac.acce_oid_acce = acce.oid_acce
                         AND acce.cana_oid_cana = cana.oid_cana
                         AND pcor.tipe_oid_tipo_peri = tpe.oid_tipo_peri
                         AND pro.mapr_oid_marc_prod = mpro.oid_marc_prod(+)
                         AND pro.nego_oid_nego = nego.oid_nego(+)
                         AND dof.tofe_oid_tipo_ofer = tof.oid_tipo_ofer(+)
                         AND pro.val_grup_arti =
                             nvl(xx.val_grup_arti,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'VN'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '06'
                                           AND val_grup_arti = pro.val_grup_arti),
                                        0,
                                        pro.val_grup_arti,
                                        'XXXX'))
                         AND sop.TAIM_OID_TASA_IMPU =
                             nvl(xx.TAIM_OID_TASA_IMPU,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'VN'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '06'
                                           AND TAIM_OID_TASA_IMPU = sop.TAIM_OID_TASA_IMPU),
                                        0,
                                        sop.TAIM_OID_TASA_IMPU,
                                        0/*sop.TAIM_OID_TASA_IMPU*/))
                         AND psc.TSPA_OID_TIPO_SOLI_PAIS =
                             nvl(xx.TSPA_OID_TIPO_SOLI_PAIS,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'VN'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '06'
                                           AND TSPA_OID_TIPO_SOLI_PAIS = psc.TSPA_OID_TIPO_SOLI_PAIS),
                                        0,
                                        psc.TSPA_OID_TIPO_SOLI_PAIS,
                                        0/*psc.TSPA_OID_TIPO_SOLI_PAIS*/))
                         AND cab.ictp_oid_tipo_prog = con.oid_tipo_prog(+)
                         AND cab.num_lote_cont IS NULL
                         AND trunc(cab.fec_fact) <= to_date(psfecha,
                                                            'DD/MM/YYYY')
                         AND cab.perd_oid_peri in  (select oid_peri  from periodos))
               WHERE importe <> 0
                 AND cod_cuen_cont IS NOT NULL
               GROUP BY pais,
                        codigopais,
                        fecha,
                        sociedad,
                        codigoempresa,
                        codigocanal,
                        codigoacceso,
                        subacceso,
                        codigosubacceso,
                        codigotipoperiodo,
                        periodo,
                        codigoperiodo,
                        ejerciciocomercial,
                        val_indi_debe_habe,
                        cod_cuen_cont,
                        codigomarcaproducto,
                        grupoproductos,
                        codigonegocio,
                        codigotipooferta,
                        tipo_soli,
                        tipo_impu,
                        '06'

              UNION

              SELECT pais,
                     codigopais,
                     fecha,
                     sociedad,
                     codigoempresa,
                     codigocanal,
                     codigoacceso,
                     subacceso,
                     codigosubacceso,
                     codigotipoperiodo,
                     periodo,
                     codigoperiodo,
                     ejerciciocomercial,
                     val_indi_debe_habe,
                     'VN' tipo_asiento,
                     cod_cuen_cont,
                     codigomarcaproducto,
                     grupoproductos,
                     codigonegocio,
                     codigotipooferta,
                     tipo_soli,
                     tipo_impu,
                     '08',
                     SUM(abs(importe))
                FROM (SELECT cab.pais_oid_pais pais,
                             pai.cod_pais codigopais,
                             cab.fec_fact fecha,
                             cab.soci_oid_soci sociedad,
                             soc.cod_soci codigoempresa,
                             cana.cod_cana codigocanal,
                             acce.cod_acce codigoacceso,
                             cab.sbac_oid_sbac subacceso,
                             sbac.cod_sbac codigosubacceso,
                             tpe.cod_tipo_peri codigotipoperiodo,
                             peri.peri_oid_peri periodo,
                             pcor.cod_peri codigoperiodo,
                             pcor.val_anio ejerciciocomercial,
                             CASE
                               WHEN
                                       nvl(decode(lin.val_prec_cata_unit_loca,0,lin.imp_impu_tota_prod_naci,0),0)
                                 < 0 THEN
                                decode(xx.val_indi_debe_habe,
                                       'D',
                                       'H',
                                       'D')
                               ELSE
                                xx.val_indi_debe_habe
                             END val_indi_debe_habe,
                             xx.cod_cuen_cont,
                             mpro.cod_marc_prod codigomarcaproducto,
                             pro.val_grup_arti grupoproductos,
                             nego.cod_nego codigonegocio,
                             tof.cod_tipo_ofer codigotipooferta,
                             xx.tspa_oid_tipo_soli_pais tipo_soli,
                             xx.TAIM_OID_TASA_IMPU tipo_impu,
                             nvl(decode(lin.val_prec_cata_unit_loca,0,lin.imp_impu_tota_prod_naci,0),0) importe
                        FROM fac_docum_conta_cabec cab,
                             fac_docum_conta_linea lin,
                             seg_pais pai,
                             seg_socie soc,
                             mae_produ pro,
                             ped_solic_cabec psc,
                             ped_solic_posic sop,
                             pre_ofert_detal dof,
                             seg_subac sbac,
                             seg_acces acce,
                             seg_canal cana,
                             cra_perio peri,
                             seg_perio_corpo pcor,
                             seg_tipo_perio tpe,
                             seg_marca_produ mpro,
                             mae_negoc nego,
                             pre_tipo_ofert tof,
                             inc_concu_tipo_prog con,
                             (SELECT cod_cuen_cont,
                                     val_indi_debe_habe,
                                     val_grup_arti,
                                     tspa_oid_tipo_soli_pais,
                                     taim_oid_tasa_impu
                                FROM int_param_conta_factu ipc,
                                     int_impor_conta_factu icf,
                                     ccc_cuent_conta       cc
                               WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                 AND ipc.val_tipo_asie = 'VN'
                                 AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                 AND cod_impo_cont = '08') xx
                       WHERE nvl(cab.val_tota_paga_loca,
                                 0) >= 0
                         AND lin.num_unid_aten > 0
                         AND cab.tido_oid_tipo_docu in (1,9,28,29,30,34)
                         AND lin.dcca_oid_cabe = cab.oid_cabe
                         AND cab.pais_oid_pais = pai.oid_pais
                         AND cab.soci_oid_soci = soc.oid_soci
                         AND lin.prod_oid_prod = pro.oid_prod
                         AND sop.soca_oid_soli_cabe = psc.oid_soli_cabe
                         AND lin.sopo_oid_soli_posi = sop.oid_soli_posi
                         AND sop.ofde_oid_deta_ofer = dof.oid_deta_ofer(+)
                         AND cab.sbac_oid_sbac = sbac.oid_sbac
                         AND cab.perd_oid_peri = peri.oid_peri
                         AND peri.peri_oid_peri = pcor.oid_peri
                         AND sbac.acce_oid_acce = acce.oid_acce
                         AND acce.cana_oid_cana = cana.oid_cana
                         AND pcor.tipe_oid_tipo_peri = tpe.oid_tipo_peri
                         AND pro.mapr_oid_marc_prod = mpro.oid_marc_prod(+)
                         AND pro.nego_oid_nego = nego.oid_nego(+)
                         AND dof.tofe_oid_tipo_ofer = tof.oid_tipo_ofer(+)
                         AND pro.val_grup_arti =
                             nvl(xx.val_grup_arti,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'VN'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '08'
                                           AND val_grup_arti = pro.val_grup_arti),
                                        0,
                                        pro.val_grup_arti,
                                        'XXXX'))
                         AND sop.TAIM_OID_TASA_IMPU =
                             nvl(xx.TAIM_OID_TASA_IMPU,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'VN'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '08'
                                           AND TAIM_OID_TASA_IMPU = sop.TAIM_OID_TASA_IMPU),
                                        0,
                                        sop.TAIM_OID_TASA_IMPU,
                                        0/*sop.TAIM_OID_TASA_IMPU*/))
                         AND psc.TSPA_OID_TIPO_SOLI_PAIS =
                             nvl(xx.TSPA_OID_TIPO_SOLI_PAIS,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'VN'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '08'
                                           AND TSPA_OID_TIPO_SOLI_PAIS = psc.TSPA_OID_TIPO_SOLI_PAIS),
                                        0,
                                        psc.TSPA_OID_TIPO_SOLI_PAIS,
                                        0/*psc.TSPA_OID_TIPO_SOLI_PAIS*/))
                         AND cab.ictp_oid_tipo_prog = con.oid_tipo_prog(+)
                         AND cab.num_lote_cont IS NULL
                         AND trunc(cab.fec_fact) <= to_date(psfecha,
                                                            'DD/MM/YYYY')
                         AND cab.perd_oid_peri in  (select oid_peri  from periodos))
               WHERE importe <> 0
                 AND cod_cuen_cont IS NOT NULL
               GROUP BY pais,
                        codigopais,
                        fecha,
                        sociedad,
                        codigoempresa,
                        codigocanal,
                        codigoacceso,
                        subacceso,
                        codigosubacceso,
                        codigotipoperiodo,
                        periodo,
                        codigoperiodo,
                        ejerciciocomercial,
                        val_indi_debe_habe,
                        cod_cuen_cont,
                        codigomarcaproducto,
                        grupoproductos,
                        codigonegocio,
                        codigotipooferta,
                        tipo_soli,
                        tipo_impu,
                        '08'

              UNION

              SELECT pais,
                     codigopais,
                     fecha,
                     sociedad,
                     codigoempresa,
                     codigocanal,
                     codigoacceso,
                     subacceso,
                     codigosubacceso,
                     codigotipoperiodo,
                     periodo,
                     codigoperiodo,
                     ejerciciocomercial,
                     val_indi_debe_habe,
                     'VN' tipo_asiento,
                     cod_cuen_cont,
                     codigomarcaproducto,
                     grupoproductos,
                     codigonegocio,
                     codigotipooferta,
                     tipo_soli,
                     tipo_impu,
                     '05',
                     SUM(abs(importe))
                FROM (SELECT cab.pais_oid_pais pais,
                             pai.cod_pais codigopais,
                             cab.fec_fact fecha,
                             cab.soci_oid_soci sociedad,
                             soc.cod_soci codigoempresa,
                             cana.cod_cana codigocanal,
                             acce.cod_acce codigoacceso,
                             cab.sbac_oid_sbac subacceso,
                             sbac.cod_sbac codigosubacceso,
                             tpe.cod_tipo_peri codigotipoperiodo,
                             peri.peri_oid_peri periodo,
                             pcor.cod_peri codigoperiodo,
                             pcor.val_anio ejerciciocomercial,
                             CASE
                               WHEN CASE WHEN con.cod_tipo_prog IN ('B', 'C', 'R') THEN
                                0
                               ELSE
                                lin.imp_desc_sin_impu_tota_loca
                             END < 0 THEN decode(xx.val_indi_debe_habe, 'D', 'H', 'D') ELSE xx.val_indi_debe_habe END val_indi_debe_habe,
                             xx.cod_cuen_cont,
                             mpro.cod_marc_prod codigomarcaproducto,
                             pro.val_grup_arti grupoproductos,
                             nego.cod_nego codigonegocio,
                             tof.cod_tipo_ofer codigotipooferta,
                             xx.tspa_oid_tipo_soli_pais tipo_soli,
                             xx.TAIM_OID_TASA_IMPU tipo_impu,
                             CASE
                               WHEN con.cod_tipo_prog IN ('B', 'C', 'R') THEN
                                0
                               ELSE
                                lin.imp_desc_sin_impu_tota_loca
                             END importe
                        FROM fac_docum_conta_cabec cab,
                             fac_docum_conta_linea lin,
                             seg_pais pai,
                             seg_socie soc,
                             mae_produ pro,
                             ped_solic_cabec psc,
                             ped_solic_posic sop,
                             pre_ofert_detal dof,
                             seg_subac sbac,
                             seg_acces acce,
                             seg_canal cana,
                             cra_perio peri,
                             seg_perio_corpo pcor,
                             seg_tipo_perio tpe,
                             seg_marca_produ mpro,
                             mae_negoc nego,
                             pre_tipo_ofert tof,
                             inc_concu_tipo_prog con,
                             (SELECT cod_cuen_cont,
                                     val_indi_debe_habe,
                                     val_grup_arti,
                                     tspa_oid_tipo_soli_pais,
                                     taim_oid_tasa_impu
                                FROM int_param_conta_factu ipc,
                                     int_impor_conta_factu icf,
                                     ccc_cuent_conta       cc
                               WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                 AND ipc.val_tipo_asie = 'VN'
                                 AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                 AND cod_impo_cont = '05') xx
                       WHERE nvl(cab.val_tota_paga_loca,
                                 0) >= 0
                         AND lin.num_unid_aten > 0
                         AND cab.tido_oid_tipo_docu in (1,9,28,29,30,34)
                         AND lin.dcca_oid_cabe = cab.oid_cabe
                         AND cab.pais_oid_pais = pai.oid_pais
                         AND cab.soci_oid_soci = soc.oid_soci
                         AND lin.prod_oid_prod = pro.oid_prod
                         AND sop.soca_oid_soli_cabe = psc.oid_soli_cabe
                         AND lin.sopo_oid_soli_posi = sop.oid_soli_posi
                         AND sop.ofde_oid_deta_ofer = dof.oid_deta_ofer(+)
                         AND cab.sbac_oid_sbac = sbac.oid_sbac
                         AND cab.perd_oid_peri = peri.oid_peri
                         AND peri.peri_oid_peri = pcor.oid_peri
                         AND sbac.acce_oid_acce = acce.oid_acce
                         AND acce.cana_oid_cana = cana.oid_cana
                         AND pcor.tipe_oid_tipo_peri = tpe.oid_tipo_peri
                         AND pro.mapr_oid_marc_prod = mpro.oid_marc_prod(+)
                         AND pro.nego_oid_nego = nego.oid_nego(+)
                         AND dof.tofe_oid_tipo_ofer = tof.oid_tipo_ofer(+)
                         AND pro.val_grup_arti =
                             nvl(xx.val_grup_arti,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'VN'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '05'
                                           AND val_grup_arti = pro.val_grup_arti),
                                        0,
                                        pro.val_grup_arti,
                                        'XXXX'))
                         AND sop.TAIM_OID_TASA_IMPU =
                             nvl(xx.TAIM_OID_TASA_IMPU,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'VN'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '05'
                                           AND TAIM_OID_TASA_IMPU = sop.TAIM_OID_TASA_IMPU),
                                        0,
                                        sop.TAIM_OID_TASA_IMPU,
                                        0/*sop.TAIM_OID_TASA_IMPU*/))
                         AND psc.TSPA_OID_TIPO_SOLI_PAIS =
                             nvl(xx.TSPA_OID_TIPO_SOLI_PAIS,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'VN'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '05'
                                           AND TSPA_OID_TIPO_SOLI_PAIS = psc.TSPA_OID_TIPO_SOLI_PAIS),
                                        0,
                                        psc.TSPA_OID_TIPO_SOLI_PAIS,
                                        0/*psc.TSPA_OID_TIPO_SOLI_PAIS*/))
                         AND cab.ictp_oid_tipo_prog = con.oid_tipo_prog(+)
                         AND cab.num_lote_cont IS NULL
                         AND trunc(cab.fec_fact) <= to_date(psfecha,
                                                            'DD/MM/YYYY')
                         AND cab.perd_oid_peri in  (select oid_peri  from periodos))
               WHERE importe <> 0
                 AND cod_cuen_cont IS NOT NULL
               GROUP BY pais,
                        codigopais,
                        fecha,
                        sociedad,
                        codigoempresa,
                        codigocanal,
                        codigoacceso,
                        subacceso,
                        codigosubacceso,
                        codigotipoperiodo,
                        periodo,
                        codigoperiodo,
                        ejerciciocomercial,
                        val_indi_debe_habe,
                        cod_cuen_cont,
                        codigomarcaproducto,
                        grupoproductos,
                        codigonegocio,
                        codigotipooferta,
                        tipo_soli,
                        tipo_impu,
                        '05'

              UNION

              SELECT pais,
                     codigopais,
                     fecha,
                     sociedad,
                     codigoempresa,
                     codigocanal,
                     codigoacceso,
                     subacceso,
                     codigosubacceso,
                     codigotipoperiodo,
                     periodo,
                     codigoperiodo,
                     ejerciciocomercial,
                     val_indi_debe_habe,
                     'VN' tipo_asiento,
                     cod_cuen_cont,
                     codigomarcaproducto,
                     grupoproductos,
                     codigonegocio,
                     codigotipooferta,
                     tipo_soli,
                     tipo_impu,
                     '07',
                     SUM(abs(importe))
                FROM (SELECT cab.pais_oid_pais pais,
                             pai.cod_pais codigopais,
                             cab.fec_fact fecha,
                             cab.soci_oid_soci sociedad,
                             soc.cod_soci codigoempresa,
                             cana.cod_cana codigocanal,
                             acce.cod_acce codigoacceso,
                             cab.sbac_oid_sbac subacceso,
                             sbac.cod_sbac codigosubacceso,
                             tpe.cod_tipo_peri codigotipoperiodo,
                             peri.peri_oid_peri periodo,
                             pcor.cod_peri codigoperiodo,
                             pcor.val_anio ejerciciocomercial,
                             CASE
                               WHEN CASE WHEN con.cod_tipo_prog IN ('B','C', 'R') THEN
                                0
                               ELSE
                                decode(lin.val_prec_cont_tota_loca,
                                       0,
                                       0,
                                       lin.val_prec_sin_impu_tota_loca)
                             END < 0 THEN decode(xx.val_indi_debe_habe, 'D', 'H', 'D') ELSE xx.val_indi_debe_habe END val_indi_debe_habe,
                             xx.cod_cuen_cont,
                             mpro.cod_marc_prod codigomarcaproducto,
                             pro.val_grup_arti grupoproductos,
                             nego.cod_nego codigonegocio,
                             tof.cod_tipo_ofer codigotipooferta,
                             xx.tspa_oid_tipo_soli_pais tipo_soli,
                             xx.TAIM_OID_TASA_IMPU tipo_impu,
                             CASE
                               WHEN con.cod_tipo_prog IN ('B','C', 'R') THEN
                                0
                               ELSE
                                decode(lin.val_prec_cont_tota_loca,
                                       0,
                                       0,
                                       lin.val_prec_sin_impu_tota_loca)
                             END importe
                        FROM fac_docum_conta_cabec cab,
                             fac_docum_conta_linea lin,
                             seg_pais pai,
                             seg_socie soc,
                             mae_produ pro,
                             ped_solic_cabec psc,
                             ped_solic_posic sop,
                             pre_ofert_detal dof,
                             seg_subac sbac,
                             seg_acces acce,
                             seg_canal cana,
                             cra_perio peri,
                             seg_perio_corpo pcor,
                             seg_tipo_perio tpe,
                             seg_marca_produ mpro,
                             mae_negoc nego,
                             pre_tipo_ofert tof,
                             inc_concu_tipo_prog con,
                             (SELECT cod_cuen_cont,
                                     val_indi_debe_habe,
                                     val_grup_arti,
                                     tspa_oid_tipo_soli_pais,
                                     taim_oid_tasa_impu
                                FROM int_param_conta_factu ipc,
                                     int_impor_conta_factu icf,
                                     ccc_cuent_conta       cc
                               WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                 AND ipc.val_tipo_asie = 'VN'
                                 AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                 AND cod_impo_cont = '07') xx
                       WHERE nvl(cab.val_tota_paga_loca,
                                 0) >= 0
                         AND lin.num_unid_aten > 0
                         AND cab.tido_oid_tipo_docu in (1,9,28,29,30,34)
                         AND lin.dcca_oid_cabe = cab.oid_cabe
                         AND cab.pais_oid_pais = pai.oid_pais
                         AND cab.soci_oid_soci = soc.oid_soci
                         AND lin.prod_oid_prod = pro.oid_prod
                         AND sop.soca_oid_soli_cabe = psc.oid_soli_cabe
                         AND lin.sopo_oid_soli_posi = sop.oid_soli_posi
                         AND sop.ofde_oid_deta_ofer = dof.oid_deta_ofer(+)
                         AND cab.sbac_oid_sbac = sbac.oid_sbac
                         AND cab.perd_oid_peri = peri.oid_peri
                         AND peri.peri_oid_peri = pcor.oid_peri
                         AND sbac.acce_oid_acce = acce.oid_acce
                         AND acce.cana_oid_cana = cana.oid_cana
                         AND pcor.tipe_oid_tipo_peri = tpe.oid_tipo_peri
                         AND pro.mapr_oid_marc_prod = mpro.oid_marc_prod(+)
                         AND pro.nego_oid_nego = nego.oid_nego(+)
                         AND dof.tofe_oid_tipo_ofer = tof.oid_tipo_ofer(+)
                         AND pro.val_grup_arti =
                             nvl(xx.val_grup_arti,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'VN'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '07'
                                           AND val_grup_arti = pro.val_grup_arti),
                                        0,
                                        pro.val_grup_arti,
                                        'XXXX'))
                         AND sop.TAIM_OID_TASA_IMPU =
                             nvl(xx.TAIM_OID_TASA_IMPU,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'VN'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '07'
                                           AND TAIM_OID_TASA_IMPU = sop.TAIM_OID_TASA_IMPU),
                                        0,
                                        sop.TAIM_OID_TASA_IMPU,
                                        0/*sop.TAIM_OID_TASA_IMPU*/))
                         AND psc.TSPA_OID_TIPO_SOLI_PAIS =
                             nvl(xx.TSPA_OID_TIPO_SOLI_PAIS,
                                 decode((SELECT COUNT(*)
                                          FROM int_param_conta_factu ipc,
                                               int_impor_conta_factu icf,
                                               ccc_cuent_conta       cc
                                         WHERE ipc.cuco_oid_cuen_cont = cc.oid_cuen_cont
                                           AND ipc.val_tipo_asie = 'VN'
                                           AND ipc.imcf_oid_impo_cont_fact = icf.oid_impo_cont_fact
                                           AND cod_impo_cont = '07'
                                           AND TSPA_OID_TIPO_SOLI_PAIS = psc.TSPA_OID_TIPO_SOLI_PAIS),
                                        0,
                                        psc.TSPA_OID_TIPO_SOLI_PAIS,
                                        0/*psc.TSPA_OID_TIPO_SOLI_PAIS*/))
                         AND cab.ictp_oid_tipo_prog = con.oid_tipo_prog(+)
                         AND cab.num_lote_cont IS NULL
                         AND trunc(cab.fec_fact) <= to_date(psfecha,
                                                            'DD/MM/YYYY')
                         AND cab.perd_oid_peri in  (select oid_peri  from periodos))
               WHERE importe <> 0
                 AND cod_cuen_cont IS NOT NULL
               GROUP BY pais,
                        codigopais,
                        fecha,
                        sociedad,
                        codigoempresa,
                        codigocanal,
                        codigoacceso,
                        subacceso,
                        codigosubacceso,
                        codigotipoperiodo,
                        periodo,
                        codigoperiodo,
                        ejerciciocomercial,
                        val_indi_debe_habe,
                        cod_cuen_cont,
                        codigomarcaproducto,
                        grupoproductos,
                        codigonegocio,
                        codigotipooferta,
                        tipo_soli,
                        tipo_impu,
                        '07'
                        );


 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(SQLERRM,
                          1,
                          250);
     raise_application_error(-20123,
                             'INT_PR_SAF_GENER_SAPFI_FACTU: ' || ls_sqlerrm);
 END int_pr_saf_gener_sapfi_factu;

/***************************************************************************
Descripcion       : Genera Interfaz de Envia SAPFI Facturacion
Fecha Creacion    : 04/09/2009
Autor             : Jose Cairampoma
***************************************************************************/
PROCEDURE int_pr_saf_sapfi_factu
(
  pscodigopais     VARCHAR2,
  pscodigosistema  VARCHAR2,
  pscodigointerfaz VARCHAR2,
  psnombrearchivo  VARCHAR2,
  psnumerolote     VARCHAR2,
  psfecha          VARCHAR2
) IS

 CURSOR c_descuadre IS
    -- obtiene la diferencia entre Debe y Haber, por LOTE, TIPO ASIENTO y DIA
    -- de los asientos descuadrados
    select (sum(decode(f.ind_debe_habe,'H',f.val_impo,0)) -
                   sum(decode(f.ind_debe_habe,'D',f.val_impo,0))) descuadre,
           f.tip_asie,
           f.fec_cont,
           f.cod_peri_come
      from int_saf_sapfi_factu f
     where f.num_lote = psnumerolote
  group by f.tip_asie, f.fec_cont, f.cod_peri_come
    having (sum(decode(f.ind_debe_habe,'H',f.val_impo,0)) -
                   sum(decode(f.ind_debe_habe,'D',f.val_impo,0))) != 0;

    TYPE t_descuadre   IS TABLE OF int_saf_sapfi_factu.val_impo%TYPE;
    TYPE t_tipoasiento IS TABLE OF int_saf_sapfi_factu.tip_asie%TYPE;
    TYPE t_fechacont   IS TABLE OF int_saf_sapfi_factu.fec_cont%TYPE;
    TYPE t_pericome    IS TABLE OF int_saf_sapfi_factu.cod_peri_come%TYPE;

    v_descuadre     t_descuadre;
    v_tipoasiento   t_tipoasiento;
    v_fechacont     t_fechacont;
    v_pericome      t_pericome;

  CURSOR c_interfaz IS
    SELECT num_lote,
           cod_pais,
           TO_CHAR(fec_cont,'YYYYMMDD'),
           cod_soci,
           cod_cana,
           cod_acce,
           cod_suba,
           tip_peri_come,
           cod_peri_come,
           val_ejer_come,
           tip_asie,
           tip_movi_safi,
           num_docu_iden,
           des_glos,
           ind_debe_habe,
           cod_marc_prod,
           cod_grup_arti,
           cod_nego_prod,
           tip_ofer,
           val_cicl_vida,
           val_impo,
           cod_mone,
           cod_peri_cont,
           val_ejer_cont,
           TO_CHAR(fec_docu,'YYYYMMDD'),
           cod_banc,
           TO_CHAR(fec_pago_banc,'YYYYMMDD'),
           TO_CHAR(fec_valo,'YYYYMMDD'),
           num_comp,
           val_reca_sucu,
           cod_zona
      FROM int_saf_sapfi_factu
     WHERE num_lote = psnumerolote
       AND VAL_impo <> 0;

  TYPE interfazrec IS RECORD(
    numerolote          int_saf_sapfi_factu.num_lote%TYPE,
    codigopais          int_saf_sapfi_factu.cod_pais%TYPE,
    fechacontable       VARCHAR2(8),
    sociedad            int_saf_sapfi_factu.cod_soci%TYPE,
    canal               int_saf_sapfi_factu.cod_cana%TYPE,
    acceso              int_saf_sapfi_factu.cod_acce%TYPE,
    subacceso           int_saf_sapfi_factu.cod_suba%TYPE,
    tipoperiodo         int_saf_sapfi_factu.tip_peri_come%TYPE,
    periodocomercial    int_saf_sapfi_factu.cod_peri_come%TYPE,
    ejerciciocomercial  int_saf_sapfi_factu.val_ejer_come%TYPE,
    tipoasiento         int_saf_sapfi_factu.tip_asie%TYPE,
    tipomovimientosapfi int_saf_sapfi_factu.tip_movi_safi%TYPE,
    documentoidentidad  int_saf_sapfi_factu.num_docu_iden%TYPE,
    glosa               int_saf_sapfi_factu.des_glos%TYPE,
    inddebehaber        int_saf_sapfi_factu.ind_debe_habe%TYPE,
    marcaproducto       int_saf_sapfi_factu.cod_marc_prod%TYPE,
    grupoarticulo       int_saf_sapfi_factu.cod_grup_arti%TYPE,
    negocio             int_saf_sapfi_factu.cod_nego_prod%TYPE,
    tipooferta          int_saf_sapfi_factu.tip_ofer%TYPE,
    ciclovida           int_saf_sapfi_factu.val_cicl_vida%TYPE,
    importe             int_saf_sapfi_factu.val_impo%TYPE,
    moneda              int_saf_sapfi_factu.cod_mone%TYPE,
    periodocontable     int_saf_sapfi_factu.cod_peri_cont%TYPE,
    ejerciciocontable   int_saf_sapfi_factu.val_ejer_cont%TYPE,
    fechadocumento      VARCHAR2(8),
    codigobanco         int_saf_sapfi_factu.cod_banc%TYPE,
    fechapagobanco      VARCHAR2(8),
    fechavalor          VARCHAR2(8),
    numcomprobante      int_saf_sapfi_factu.num_comp%TYPE,
    recaudadorasucursal int_saf_sapfi_factu.val_reca_sucu%TYPE,
    zona                int_saf_sapfi_factu.cod_zona%TYPE);

  TYPE interfazrectab IS TABLE OF interfazrec;
  interfazrecord interfazrectab;
  /* Variables usadas para la generacion del archivo de texto */
  lsdirtempo      bas_inter.dir_temp%TYPE;
  w_filas         NUMBER := 1000;
  v_handle        utl_file.file_type;
  lslinea         VARCHAR2(1000);
  lsnombrearchivo VARCHAR2(50);

  lbabrirutlfile  BOOLEAN;
  i               BINARY_INTEGER := 0;
  rows            NUMBER := 5000;
  --
  lssociedadSV    ccc_param_gener.val_para%type;

  lsajustedescuadre number;

BEGIN

  lbabrirutlfile := TRUE;

  -- Obtiene parametro de ajuste de descuadre
  begin
     lsajustedescuadre := nvl(gen_pkg_gener.gen_fn_param_pais(pscodigopais,pscodigosistema,'001'),1.2);
  exception
    when others then
      lsajustedescuadre := 1.2;
  end;

  -- genera la informacion
  int_pr_saf_gener_sapfi_factu(psnumerolote , psfecha);

  --Cambia la sociedad en el caso de El Salvador Esika
  begin
     select val_para
       into lssociedadSV
       from ccc_param_gener pg
      where cod_para = 'COD_SOCI_SAFI';

     update int_saf_sapfi_factu sf
        set sf.cod_soci = lssociedadSV
      where sf.num_lote = psnumerolote;

  exception
    when no_data_found then
      null;
  end;

  -- corrige el descuadre
  OPEN c_descuadre;
    LOOP
      FETCH c_descuadre BULK COLLECT INTO v_descuadre,
                                          v_tipoasiento,
                                          v_fechacont,
                                          v_pericome
                                          LIMIT rows;

      IF v_tipoasiento.count > 0 THEN
        FOR i IN v_tipoasiento.first .. v_tipoasiento.last
        LOOP
            -- Que lo haga solo si el descuadre es solo x 1.2 o
            --el valor del parametro del BAS_PARAM_PAIS [CUADRE_SAPFI]
            IF abs(v_descuadre(i)) <= lsajustedescuadre THEN
            --
              IF v_descuadre(i) > 0 THEN
                 -- H > D   =>  Le suma el descuadre al Debe
                 update int_saf_sapfi_factu f
                    set f.val_impo = (f.val_impo + abs(v_descuadre(i)))
                  where f.num_lote = psnumerolote
                    and f.tip_asie = v_tipoasiento(i)
                    and f.fec_cont = v_fechacont(i)
                    and f.cod_peri_come = v_pericome(i)
                    and f.ind_debe_habe = 'D'
                    and rownum = 1;
              ELSE
                 -- D > H   =>  Le suma el descuadre al Haber
                 update int_saf_sapfi_factu f
                    set f.val_impo = (f.val_impo + abs(v_descuadre(i)))
                  where f.num_lote = psnumerolote
                    and f.tip_asie = v_tipoasiento(i)
                    and f.fec_cont = v_fechacont(i)
                    and f.cod_peri_come = v_pericome(i)
                    and f.ind_debe_habe = 'H'
                    and rownum = 1;
              END IF;
            --
            END IF;
        END LOOP;
      END IF;

      EXIT WHEN c_descuadre %NOTFOUND;
    END LOOP;
  CLOSE c_descuadre;

  -- genera la interfaz
  OPEN c_interfaz;
  LOOP
    FETCH c_interfaz BULK COLLECT
      INTO interfazrecord LIMIT w_filas;

    /* Procedimiento inicial para generar interfaz */
    IF lbabrirutlfile THEN
      gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                             pscodigosistema,
                                             pscodigointerfaz,
                                             psnombrearchivo,
                                             lsdirtempo,
                                             lsnombrearchivo,
                                             v_handle);
      lbabrirutlfile := FALSE;
    END IF;

    IF interfazrecord.COUNT > 0 THEN
      FOR x IN interfazrecord.FIRST .. interfazrecord.LAST LOOP
         lslinea := interfazrecord(x).numeroLote                        || ';' ||
                    interfazrecord(x).codigoPais                        || ';' ||
                    interfazrecord(x).fechaContable                     || ';' ||
                    interfazrecord(x).sociedad                          || ';' ||
                    interfazrecord(x).canal                             || ';' ||
                    interfazrecord(x).acceso                            || ';' ||
                    interfazrecord(x).subacceso                         || ';' ||
                    interfazrecord(x).tipoPeriodo                       || ';' ||
                    interfazrecord(x).periodoComercial                  || ';' ||
                    interfazrecord(x).ejercicioComercial                || ';' ||
                    interfazrecord(x).tipoAsiento                       || ';' ||
                    interfazrecord(x).tipoMovimientoSAPFI               || ';' ||
                    interfazrecord(x).documentoIdentidad                || ';' ||
                    interfazrecord(x).glosa                             || ';' ||
                    interfazrecord(x).indDebeHaber                      || ';' ||
                    interfazrecord(x).marcaProducto                     || ';' ||
                    interfazrecord(x).grupoArticulo                     || ';' ||
                    interfazrecord(x).negocio                           || ';' ||
                    interfazrecord(x).tipoOferta                        || ';' ||
                    interfazrecord(x).cicloVida                         || ';' ||
                    interfazrecord(x).importe                           || ';' ||
                    interfazrecord(x).moneda                            || ';' ||
                    interfazrecord(x).periodoContable                   || ';' ||
                    interfazrecord(x).ejercicioContable                 || ';' ||
                    interfazrecord(x).fechaDocumento                    || ';' ||
                    interfazrecord(x).codigoBanco                       || ';' ||
                    interfazrecord(x).fechaPagoBanco                    || ';' ||
                    interfazrecord(x).fechaValor                        || ';' ||
                    interfazrecord(x).numComprobante                    || ';' ||
                    interfazrecord(x).recaudadoraSucursal               || ';' ||
                    interfazrecord(x).zona;

        utl_file.put_line(v_handle, lslinea);

      END LOOP;
    END IF;

    EXIT WHEN c_interfaz%NOTFOUND;
  END LOOP;
  CLOSE c_interfaz;

  IF NOT lbabrirutlfile THEN
    utl_file.fclose(v_handle);
    /* Procedimiento final para generar interfaz */
    gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                           lsdirtempo,
                                           psnombrearchivo,
                                           lsnombrearchivo);
  END IF;

  UPDATE fac_docum_conta_cabec cab
     SET cab.num_lote_cont = psnumerolote,
         cab.fec_cont = trunc(SYSDATE)
   WHERE cab.tido_oid_tipo_docu in (1,9,28,29,30,34,31,32,33)
     AND cab.num_lote_cont IS NULL
     AND trunc(cab.fec_fact) <= to_date(psfecha, 'DD/MM/YYYY')
     AND cab.perd_oid_peri IN (SELECT DISTINCT cab_p.perd_oid_peri oid_peri
                                 FROM fac_docum_conta_cabec cab_p
                                WHERE cab_p.num_lote_cont IS NULL
                                  AND trunc(cab_p.fec_fact) <= to_date(psfecha, 'DD/MM/YYYY')
                                  AND cab_p.tido_oid_tipo_docu in (1,9,28,29,30,34,31,32,33));

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    raise_application_error(-20123,'INT_PR_SAF_FACTU: ' || ls_sqlerrm);
END int_pr_saf_sapfi_factu;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Cabecera para Colombia
  Fecha Creacion    : 23/05/2013
  Autor             : Jorge Yépez
  ***************************************************************************/
  PROCEDURE int_pr_saf_cabec_colom
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psperiodo        VARCHAR2,
    psfecha          VARCHAR2
  ) IS

    lsFecProc      bas_Ctrl_Fact.Fec_Proc%TYPE;

    CURSOR c_interfaz IS

      select x.COMP , psperiodo , to_char(x.fec_fact,'YYYYMMDD') , lpad(y.tip_comp,2,'0') , to_char(x.fec_fact,'mm')
      , lpad(y.sec_comp,6,'0')
      , lpad(abs(sum(x.IMP_FLET_IMPU_TOTA_LOCA)),11,'0')
      , lpad(abs(sum(x.imp_impu_tota_loca)),11,'0')
      , lpad(abs(sum(x.pie)),11,'0')
      , lpad(abs(sum(x.otros)),11,'0')
      , lpad(abs(sum(x.fp)),11,'0')
      , lpad(abs(sum(x.intereses)),11,'0')
      , lpad(abs(sum(x.val_tota_paga_loca+x.iva_asum+x.cree)
        -(decode( x.COMP, 'VTA', (select sum(val_prec_cata_sin_impu_tota) from ped_solic_cabec where tspa_oid_tipo_soli_pais=1507 and fec_fact=to_date(psfecha,'dd/mm/yyyy')), 0))
      ),11,'0')
      , lpad(abs(detalle.monto),11,'0')
      , lpad(abs(sum(x.iva_asum)),11,'0')
      , lpad(abs(sum(x.cree)),11,'0')
      from
      (
      select case
      when h.cod_tipo_soli='CBR' then 'NRT'
      when h.cod_tipo_soli='C78' then 'REV'
      when h.cod_tipo_soli in ('CCDF','CADF') then 'DFP'
      when h.ind_anul=1 then 'ANU'
      when h.ind_soli_nega=1 then 'SAC'
      when h.ind_soli_nega=0 then 'VTA'
      else 'XXX' end as COMP,
      psperiodo cod_peri,
      a.fec_fact,
      nvl(a.IMP_FLET_IMPU_TOTA_LOCA,0) IMP_FLET_IMPU_TOTA_LOCA,
      nvl(a.imp_impu_tota_loca,0)-nvl(a.imp_redo_loca,0) imp_impu_tota_loca,
      0 pie,
      0 otros,
      0 fp,
      nvl(a.val_tota_gast_admi_sin_impu,0) intereses,
      nvl(a.val_tota_paga_loca,0) val_tota_paga_loca,
      nvl(a.val_impo_iva_asum_empr,0) iva_asum,
      nvl(a.val_impo_impu_cree,0) cree
      from fac_docum_conta_cabec a,
           ped_solic_cabec b,
           --pre_ofert_detal c,
           ped_tipo_solic_pais e,
           cra_perio f,
           seg_perio_corpo g,
           ped_tipo_solic h
      where a.soca_oid_soli_cabe=b.oid_soli_cabe
      --and b.oid_soli_cabe=c.soca_oid_soli_cabe
      and b.tspa_oid_tipo_soli_pais=e.oid_tipo_soli_pais
      and e.tsol_oid_tipo_soli=h.oid_tipo_soli
      and a.fec_fact=to_date(psfecha,'dd/mm/yyyy')
      and a.perd_oid_peri=f.oid_peri
      and f.peri_oid_peri=g.oid_peri
      and g.cod_peri in (psperiodo,GEN_FN_CALCU_PERIO(psperiodo, 1))
      ) x, SAF_INFOR_COMPR y,
           (
      select x.COMP, psperiodo cod_peri, sum(x.monto) monto
      from
      (
      select case
      when h.cod_tipo_soli='CBR' then 'NRT'
      when h.cod_tipo_soli='C78' then 'REV'
      when h.cod_tipo_soli in ('CCDF','CADF') then 'DFP'
      when h.ind_anul=1 then 'ANU'
      when h.ind_soli_nega=1 then 'SAC'
      when h.ind_soli_nega=0 then 'VTA'
      else 'XXX' end as COMP,
      psperiodo cod_peri,
      a.fec_fact,
      d.val_prec_sin_impu_tota_loca-d.imp_desc_sin_impu_tota_loca-decode(d.val_prec_cont_tota_loca,0,0,d.val_prec_sin_impu_tota_loca) monto
      from fac_docum_conta_cabec a,
           ped_solic_cabec b,
           pre_ofert_detal c,
           fac_docum_conta_linea d,
           ped_solic_posic d1,
           ped_tipo_solic_pais e,
           cra_perio f,
           seg_perio_corpo g,
           ped_tipo_solic h
      where a.soca_oid_soli_cabe=b.oid_soli_cabe
      --and b.oid_soli_cabe=c.soca_oid_soli_cabe
      and a.oid_cabe=d.dcca_oid_cabe
      and d.sopo_oid_soli_posi=d1.oid_soli_posi
      and d1.ofde_oid_deta_ofer=c.oid_deta_ofer
      and b.tspa_oid_tipo_soli_pais=e.oid_tipo_soli_pais
      and e.tsol_oid_tipo_soli=h.oid_tipo_soli
      and a.fec_fact=to_date(psfecha,'dd/mm/yyyy')
      and a.perd_oid_peri=f.oid_peri
      and f.peri_oid_peri=g.oid_peri
      and g.cod_peri in (psperiodo,GEN_FN_CALCU_PERIO(psperiodo, 1))
      ) x
      group by x.COMP, psperiodo
           ) detalle
      where x.comp=y.cod_comp(+)
      and x.comp=detalle.comp
      and x.cod_peri=detalle.cod_peri
      group by x.COMP, psperiodo, x.fec_fact, y.tip_comp, to_char(x.fec_fact,'mm')
      , y.sec_comp, detalle.monto      ;

    TYPE interfazrec IS RECORD(
      codigocomprobante          VARCHAR2(3),
      codigoperiodo              VARCHAR2(6),
      fecha                      VARCHAR2(10),
      tipocomprobante            VARCHAR2(2),
      mescomprobante             VARCHAR2(2),
      numerocomprobante          VARCHAR2(6),
      montoflete                 VARCHAR2(11),
      montoiva                   VARCHAR2(11),
      montopie                   VARCHAR2(11),
      montootros                 VARCHAR2(11),
      montofp                    VARCHAR2(11),
      montointereses             VARCHAR2(11),
      montototal                 VARCHAR2(11),
      montodetalle               VARCHAR2(11),
      ivaasumido                 VARCHAR2(11),
      cree                       VARCHAR2(11)
      );

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         NUMBER := 1000;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);
    lbabrirutlfile  BOOLEAN;
  BEGIN

    lsFecProc := to_Date(psfecha,'DD/MM/YYYY');


    /* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x).codigocomprobante || ';' ||
                     interfazrecord(x).codigoperiodo || ';' ||
                     interfazrecord(x).fecha || ';' ||
                     interfazrecord(x).tipocomprobante || ';' ||
                     interfazrecord(x).mescomprobante || ';' ||
                     interfazrecord(x).numerocomprobante || ';' ||
                     interfazrecord(x).montoflete || ';' ||
                     interfazrecord(x).montoiva || ';' ||
                     interfazrecord(x).montopie || ';' ||
                     interfazrecord(x).montootros || ';' ||
                     interfazrecord(x).montofp || ';' ||
                     interfazrecord(x).montointereses || ';' ||
                     interfazrecord(x).montototal || ';' ||
                     interfazrecord(x).montodetalle || ';' ||
                     interfazrecord(x).ivaasumido || ';' ||
                     interfazrecord(x).cree;
          utl_file.put_line(v_handle,
                            lslinea);

                     update SAF_INFOR_COMPR y set y.sec_comp=y.sec_comp+1 where y.cod_comp=interfazrecord(x).codigocomprobante;

        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR int_pr_saf_cabec_colom: ' || ls_sqlerrm);
  END int_pr_saf_cabec_colom;
  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Detalle para Colombia
  Fecha Creacion    : 23/05/2013
  Autor             : Jorge Yépez
  ***************************************************************************/
  PROCEDURE int_pr_saf_detal_colom
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psperiodo        VARCHAR2,
    psfecha          VARCHAR2
  ) IS

    lsFecProc      bas_Ctrl_Fact.Fec_Proc%TYPE;

    CURSOR c_interfaz IS

      select x.COMP , psperiodo , to_char(x.fec_fact,'YYYYMMDD')
      , lpad(x.cod_marc_prod,2,'0')
      , x.val_grup_arti
      , lpad(x.cod_tipo_ofer,3,'0')
      , lpad(x.val_tasa_impu,2,'0')
      , lpad(abs(sum(x.monto)),11,'0') monto
      from
      (
      select case
      when h.cod_tipo_soli='CBR' then 'NRT'
      when h.cod_tipo_soli='C78' then 'REV'
      when h.cod_tipo_soli in ('CCDF','CADF') then 'DFP'
      when h.ind_anul=1 then 'ANU'
      when h.ind_soli_nega=1 then 'SAC'
      when h.ind_soli_nega=0 then 'VTA'
      else 'XXX' end as COMP,
      psperiodo cod_peri,
      a.fec_fact,
      k.cod_marc_prod,
      --j.cod_nego,
      i.val_grup_arti,
      m.cod_tipo_ofer,
      b.val_tasa_impu,
      d.val_prec_sin_impu_tota_loca-d.imp_desc_sin_impu_tota_loca-decode(d.val_prec_cont_tota_loca,0,0,d.val_prec_sin_impu_tota_loca) monto
      from fac_docum_conta_cabec a,
           ped_solic_cabec b,
           --ped_solic_cabec c,
           fac_docum_conta_linea d,
           ped_solic_posic d1,
           ped_tipo_solic_pais e,
           cra_perio f,
           seg_perio_corpo g,
           ped_tipo_solic h,
           mae_produ i,
--           mae_negoc j,
           seg_marca_produ k,
           pre_ofert_detal l,
           pre_tipo_ofert m
      where a.soca_oid_soli_cabe=b.oid_soli_cabe
      --and b.oid_soli_cabe=c.soca_oid_soli_cabe
      and a.oid_cabe=d.dcca_oid_cabe
      and d.prod_oid_prod=i.oid_prod
      and d.sopo_oid_soli_posi=d1.oid_soli_posi
      and d1.ofde_oid_deta_ofer=l.oid_deta_ofer
      and l.tofe_oid_tipo_ofer=m.oid_tipo_ofer
      and i.mapr_oid_marc_prod=k.oid_marc_prod
--      and i.nego_oid_nego=j.oid_nego
      and b.tspa_oid_tipo_soli_pais=e.oid_tipo_soli_pais
      and e.tsol_oid_tipo_soli=h.oid_tipo_soli
      and a.fec_fact=to_date(psfecha,'dd/mm/yyyy')
      and a.perd_oid_peri=f.oid_peri
      and f.peri_oid_peri=g.oid_peri
      and g.cod_peri in (psperiodo,GEN_FN_CALCU_PERIO(psperiodo, 1))
      ) x
      group by x.COMP, psperiodo, x.fec_fact
      , x.cod_marc_prod
      , x.val_grup_arti
      , x.cod_tipo_ofer
      , x.val_tasa_impu
       ;

    TYPE interfazrec IS RECORD(
      codigocomprobante          VARCHAR2(3),
      codigoperiodo              VARCHAR2(6),
      fecha                      VARCHAR2(10),
      codigomarca                VARCHAR2(2),
      codigoclase                VARCHAR2(4),
      codigotipopferta           VARCHAR2(3),
      porceiva                   VARCHAR2(2),
      montototal                 VARCHAR2(11)
      );

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         NUMBER := 1000;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);
    lbabrirutlfile  BOOLEAN;
  BEGIN

    lsFecProc := to_Date(psfecha,'DD/MM/YYYY');


    /* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x).codigocomprobante ||';' ||
                     interfazrecord(x).codigoperiodo || ';' ||
                     interfazrecord(x).fecha || ';' ||
                     interfazrecord(x).codigomarca || ';' ||
                     interfazrecord(x).codigoclase || ';' ||
                     interfazrecord(x).codigotipopferta || ';' ||
                     interfazrecord(x).porceiva || ';' ||
                     interfazrecord(x).montototal;
          utl_file.put_line(v_handle,lslinea);


        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR int_pr_saf_detal_colom: ' || ls_sqlerrm);
  END int_pr_saf_detal_colom;

  /***************************************************************************
  Descripcion       : Genera Interfaz de NUEVA SAPFI
  Fecha Creacion    : 23/05/2013
  Autor             : Jorge Yépez
  ***************************************************************************/
  PROCEDURE int_pr_saf_corpo
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscentro         VARCHAR2,
    psperiodo        VARCHAR2,
    psfecha          VARCHAR2
  ) IS

    lsFecProc      bas_Ctrl_Fact.Fec_Proc%TYPE;

    CURSOR c_interfaz IS

select
rpad(NUM_LOTE,20,' ')
|| rpad(COD_PAIS,3,' ')
|| to_char(FECCONT,'yyyymmdd')
|| to_char(FECDOC,'yyyymmdd')
|| COD_SOCI
|| pscentro
|| rpad(COD_ASIE,3,' ')
|| rpad(TIP_MOV,3,' ')
|| rpad(nvl(TIP_OFER,' '),4,' ')
|| rpad(nvl(COD_MARC,' '),2,' ')
|| rpad(nvl(VAL_GRUP_ARTI,' '),4,' ')
|| lpad(NRO_COMP, 16, '0')
|| IND_DEBE_HABE
|| rpad(VAL_GLOS || ' ' || to_char(FECCONT,'yyyymmdd'), 50, ' ')
|| rpad(substr(COD_PERI, 5),4,' ')
|| NUM_EJER
|| VAL_TASA
|| lpad(VAL_MONT*100, 16, '0') registro
from INT_SAF_RESUL where --cod_peri=psperiodo and to_char(feccont,'dd/mm/yyyy')=psfecha and
val_mont>0;


    TYPE interfazrec IS RECORD(
      registro          VARCHAR2(500)
      );

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         NUMBER := 1000;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);
    lbabrirutlfile  BOOLEAN;
  BEGIN


    /* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x).registro;
          utl_file.put_line(v_handle,lslinea);


        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR int_pr_saf_corpo: ' || ls_sqlerrm);
  END int_pr_saf_corpo;
  /***************************************************************************
  Descripcion       : Llena las tablas para SAPFI
  Fecha Creacion    : 23/05/2013
  Autor             : Jorge Yépez
  ***************************************************************************/
  PROCEDURE int_pr_carga_saf_corpo
  (
    pscodigopais     VARCHAR2,
    psperiodo        VARCHAR2,
    psfecha          VARCHAR2,
    psnumlote        VARCHAR2
  ) IS

    CURSOR c_config IS
    select tip_movi,
           des_movi,
           ind_debe_habe,
           cod_impo,
           ind_orig,
           tipo_asie,
           cod_asie
    from int_sapfi_conf
        ;

    r_config c_config%ROWTYPE;


    CURSOR c_ajuste IS
    select distinct cod_peri,
           cod_asie
    from INT_SAF_RESUL
        ;

    r_ajuste c_ajuste%ROWTYPE;

    ln_diferencia NUMBER(12,2):=0;

    ls_ajusteSFPeru VARCHAR2(100):=IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','ajusteSFPeru');

    ls_nomasieBR VARCHAR2(100):=nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','nomasieBR'),'VENTA');
    ls_codasieBR VARCHAR2(100):=nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','codasieBR'),'VN');

    ls_nomasieRP VARCHAR2(100):=nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','nomasieRP'),'VENTA');
    ls_codasieRP VARCHAR2(100):=nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','codasieRP'),'VN');

    ls_nomasieCD VARCHAR2(100):=nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','nomasieCD'),'VENTA');
    ls_codasieCD VARCHAR2(100):=nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','codasieCD'),'VN');


  BEGIN

update fac_docum_conta_linea
set IMP_IMPU_TOTA_PROD_NACI=0--num_unid_aten*(select VAL_IMPU_PROD_NACI from INT_IMPUE_PRODU_NACIO where prod_oid_prod=fac_docum_conta_linea.prod_oid_prod)
where oid in
(
select b.oid
 from fac_docum_conta_cabec a, fac_docum_conta_linea b
where a.oid_cabe=b.dcca_oid_cabe
and a.perd_oid_peri=gen_pkg_gener.gen_fn_devuelve_id_cra_perio(psperiodo,2003,2001) and a.fec_fact=TO_DATE(psfecha, 'DD/MM/YYYY')
and b.val_prec_sin_impu_unit-(nvl(b.imp_impu_tota_prod_naci,0)/b.num_unid_aten)<0
and a.tido_oid_tipo_docu=1
and exists (select 1 from INT_IMPUE_PRODU_NACIO where prod_oid_prod=b.prod_oid_prod)
);


if ls_ajusteSFPeru='S' then
        update fac_docum_conta_cabec
         set val_tota_paga_loca=val_tota_paga_loca-IMP_FLET_TOTA_LOCA
         , imp_impu_tota_loca=imp_impu_tota_loca-(IMP_FLET_TOTA_LOCA-IMP_FLET_IMPU_TOTA_LOCA)
         where tido_oid_tipo_docu=29 and soca_oid_soli_cabe in
         (
 select oid_soli_cabe from ped_solic_cabec where fec_fact=TO_DATE(psfecha, 'DD/MM/YYYY')
and exists (select 1 from fac_docum_conta_cabec where soca_oid_soli_cabe=oid_soli_cabe and tido_oid_tipo_docu=1 and IMP_FLET_TOTA_LOCA>0)
and exists (select 1 from fac_docum_conta_cabec where soca_oid_soli_cabe=oid_soli_cabe and tido_oid_tipo_docu=29 and IMP_FLET_TOTA_LOCA>0)
         );

         update fac_docum_conta_cabec set IMP_FLET_TOTA_LOCA=0
         , IMP_FLET_IMPU_TOTA_LOCA=0
         where soca_oid_soli_cabe in
         (
 select oid_soli_cabe from ped_solic_cabec where fec_fact=TO_DATE(psfecha, 'DD/MM/YYYY')
and exists (select 1 from fac_docum_conta_cabec where soca_oid_soli_cabe=oid_soli_cabe and tido_oid_tipo_docu=1 and IMP_FLET_TOTA_LOCA>0)
and exists (select 1 from fac_docum_conta_cabec where soca_oid_soli_cabe=oid_soli_cabe and tido_oid_tipo_docu=29 and IMP_FLET_TOTA_LOCA>0)
         );

end if;

delete from int_sapfi_cabec;

delete from int_sapfi_detal;

delete from INT_SAF_RESUL;

insert into int_sapfi_cabec
select
psnumlote NUM_LOTE
, g.cod_pais COD_PAIS
,to_char(a.fec_fact,'dd/mm/yyyy') FEC_PROC
, f.cod_soci COD_SOCI
, case when e.cod_tipo_soli in ('C52','C53') then 'NM'
       when e.cod_tipo_soli='C77' then 'FF'
       when e.cod_tipo_soli='CBR' then ls_codasieBR
       when e.cod_tipo_soli='C78' then ls_codasieRP
       when e.cod_tipo_soli='C10' then ls_codasieCD
       when e.ind_anul=1 then 'AN'
       when e.ind_soli_nega=1 then 'AB'
       else 'VN'
  end COD_ASIE
, i.cod_peri COD_PERI
, c.taim_oid_tasa_impu VAL_TASA
, case when e.cod_tipo_soli in ('C52','C53') then 'NMP'
       when e.cod_tipo_soli='C77' then 'FALTANTE'
       when e.cod_tipo_soli='CBR' then ls_nomasieBR
       when e.cod_tipo_soli='C78' then ls_nomasieRP
       when e.cod_tipo_soli='C10' then ls_nomasieCD
       when e.ind_anul=1 then 'ANULACIONES'
       when e.ind_soli_nega=1 then 'ABONOS'
       else 'VENTA'
  end  VAL_GLOS
, to_char(a.fec_fact,'yyyy') VAL_EJER
, sum(case when a.ICTP_OID_TIPO_PROG is not null then 0 else a.val_tota_paga_loca end) VAL_TOTA_PAGA_LOCA
, sum(case when a.ICTP_OID_TIPO_PROG is not null then 0 else a.imp_redo_loca end)   IMP_REDO_LOCA
, sum(case when a.ICTP_OID_TIPO_PROG is not null then 0 else a.imp_impu_tota_loca end)  IMP_IMPU_TOTA_LOCA
, sum(case when a.ICTP_OID_TIPO_PROG is not null then 0 else nvl(a.VAL_TOTA_GAST_ADMI_SIN_IMPU,0) end) VAL_TOTA_GAST_ADMI_SIN_IMPU
, sum(case when a.ICTP_OID_TIPO_PROG is not null then 0 else nvl(a.val_impo_rete_desc,0) end) VAL_IMPO_RETE_DESC
, sum(case when a.ICTP_OID_TIPO_PROG is not null then 0 else nvl(a.IMP_IVA_IMPU_TOTA_PROD_NACI,0) end) IMP_IVA_IMPU_TOTA_PROD_NACI
, sum(case when a.ICTP_OID_TIPO_PROG is not null then 0 else a.IMP_FLET_IMPU_TOTA_LOCA end) VAL_IMP_FLET_IMPU_TOTA_LOCA
, sum(case when a.ICTP_OID_TIPO_PROG is not null then 0 else nvl(A.VAL_IMPO_IMPU_CREE,0) end) VAL_IMPO_IMPU_CREE
, sum(case when a.ICTP_OID_TIPO_PROG is not null then 0 else nvl(A.VAL_IMPO_IVA_ASUM_EMPR,0) end) VAL_IMPO_IVA_ASUM_EMPR
, sum(case when a.ICTP_OID_TIPO_PROG is not null then 0 else nvl(A.Imp_Des3_Sin_Impu_Tota,0)+nvl(A.Imp_Des4_Sin_Impu_Tota,0) end) VAL_IMPO_DESC_TOTA
, NULL
, sum(case when a.ICTP_OID_TIPO_PROG is not null then 0 else nvl(a.VAL_TOTA_GAST_ADMI2_SIN_IMPU,0) end) VAL_TOTA_GAST_ADMI_SIN_IMPU
from fac_docum_conta_cabec a, ped_solic_cabec c, ped_tipo_solic_pais d, ped_tipo_solic e, seg_socie f, seg_pais g, cra_perio h, seg_perio_corpo i
where a.fec_fact>=to_date(psfecha,'dd/mm/yyyy')-3
and a.fec_fact<=to_date(psfecha,'dd/mm/yyyy')
and a.soca_oid_soli_cabe=c.oid_soli_cabe
and c.tspa_oid_tipo_soli_pais=d.oid_tipo_soli_pais and d.tsol_oid_tipo_soli=e.oid_tipo_soli
and c.pais_oid_pais=g.oid_pais and c.soci_oid_soci=f.oid_soci
and c.perd_oid_peri=h.oid_peri and h.peri_oid_peri=i.oid_peri
and a.tido_oid_tipo_docu not in (8)
and a.num_lote_cont is null
--and a.ICTP_OID_TIPO_PROG is null
group by
psnumlote
, g.cod_pais
, to_char(a.fec_fact,'dd/mm/yyyy')
, f.cod_soci
, case when e.cod_tipo_soli in ('C52','C53') then 'NM'
       when e.cod_tipo_soli='C77' then 'FF'
       when e.cod_tipo_soli='CBR' then ls_codasieBR
       when e.cod_tipo_soli='C78' then ls_codasieRP
       when e.cod_tipo_soli='C10' then ls_codasieCD
       when e.ind_anul=1 then 'AN'
       when e.ind_soli_nega=1 then 'AB'
       else 'VN' end
, i.cod_peri
, c.taim_oid_tasa_impu
, case when e.cod_tipo_soli in ('C52','C53') then 'NMP'
       when e.cod_tipo_soli='C77' then 'FALTANTE'
       when e.cod_tipo_soli='CBR' then ls_nomasieBR
       when e.cod_tipo_soli='C78' then ls_nomasieRP
       when e.cod_tipo_soli='C10' then ls_nomasieCD
       when e.ind_anul=1 then 'ANULACIONES'
       when e.ind_soli_nega=1 then 'ABONOS'
       else 'VENTA' end
, to_char(a.fec_fact,'yyyy');

insert into int_sapfi_detal
select
psnumlote NUM_LOTE
, g.cod_pais COD_PAIS
,to_char(a.fec_fact,'dd/mm/yyyy') FEC_PROC
, f.cod_soci COD_SOCI
, case when e.cod_tipo_soli in ('C52','C53') then 'NM'
       when e.cod_tipo_soli='C77' then 'FF'
       when e.cod_tipo_soli='CBR' then ls_codasieBR
       when e.cod_tipo_soli='C78' then ls_codasieRP
       when e.cod_tipo_soli='C10' then ls_codasieCD
       when e.ind_anul=1 then 'AN'
       when e.ind_soli_nega=1 then 'AB'
       else 'VN'
       end COD_ASIE
, i.cod_peri COD_PERI
, c.taim_oid_tasa_impu VAL_TASA
, case when e.cod_tipo_soli in ('C52','C53') then 'NMP'
       when e.cod_tipo_soli='C77' then 'FALTANTE'
       when e.cod_tipo_soli='CBR' then ls_nomasieBR
       when e.cod_tipo_soli='C78' then ls_nomasieRP
       when e.cod_tipo_soli='C10' then ls_nomasieCD
       when e.ind_anul=1 then 'ANULACIONES'
       when e.ind_soli_nega=1 then 'ABONOS'
       else 'VENTA'
       end  VAL_GLOS
, to_char(a.fec_fact,'yyyy') VAL_EJER
, sum(case when a.ICTP_OID_TIPO_PROG is not null then 0 else j.val_prec_sin_impu_tota_loca end) VAL_PREC_SIN_IMPU_TOTA_LOCA
, sum(case when a.ICTP_OID_TIPO_PROG is not null then 0 else j.imp_desc_sin_impu_tota_loca end)  IMP_DESC_SIN_IMPU_TOTA_LOCA
, sum(case when a.ICTP_OID_TIPO_PROG is not null then 0 else decode(j.val_prec_cont_tota_loca,0,0,j.val_prec_sin_impu_tota_loca) end) VAL_PREC_CONT_TOTA_LOCA
, o.cod_tipo_ofer COD_TIPO_OFER
, n.cod_marc_prod COD_MARC_PRODU
, m.val_grup_arti VAL_GRUP_ARTI
, sum(case when a.ICTP_OID_TIPO_PROG is not null then 0 else nvl(decode(j.val_prec_cata_unit_loca,0,0,J.IMP_IMPU_TOTA_PROD_NACI),0) end)  IMP_IMPU_TOTA_PROD_NACI
, sum(case when a.ICTP_OID_TIPO_PROG in (2001,2002) then ((decode(j.val_prec_cont_tota_loca,0,m.VAL_COST_ESTD*j.num_unid_aten,j.val_prec_cont_tota_loca)*c.val_tasa_impu)/100) else 0 end) VAL_IMP_GRAT_TOTA_LOCA
, sum(round(case when a.ICTP_OID_TIPO_PROG is not null then 0 else nvl(decode(j.val_prec_cata_unit_loca,0,J.IMP_IMPU_TOTA_PROD_NACI,0),0) end,2))  IMP_IMPU_TOTA_PROD_NACI_GRAT
,NULL
from fac_docum_conta_cabec a, ped_solic_cabec c, ped_tipo_solic_pais d, ped_tipo_solic e, seg_socie f, seg_pais g, cra_perio h, seg_perio_corpo i
, fac_docum_conta_linea j, ped_solic_posic k, pre_ofert_detal l, mae_produ m, seg_marca_produ n, pre_tipo_ofert o
where a.fec_fact>=to_date(psfecha,'dd/mm/yyyy')-3
and a.fec_fact<=to_date(psfecha,'dd/mm/yyyy')
and a.soca_oid_soli_cabe=c.oid_soli_cabe
and c.tspa_oid_tipo_soli_pais=d.oid_tipo_soli_pais and d.tsol_oid_tipo_soli=e.oid_tipo_soli
and c.pais_oid_pais=g.oid_pais and c.soci_oid_soci=f.oid_soci
and c.perd_oid_peri=h.oid_peri and h.peri_oid_peri=i.oid_peri
and a.tido_oid_tipo_docu not in (8)
and a.num_lote_cont is null
--and a.ICTP_OID_TIPO_PROG is null
and a.oid_cabe=j.dcca_oid_cabe and j.sopo_oid_soli_posi=k.oid_soli_posi and k.ofde_oid_deta_ofer=l.oid_deta_ofer(+)
and l.tofe_oid_tipo_ofer=o.oid_tipo_ofer(+)
and j.prod_oid_prod=m.oid_prod and m.mapr_oid_marc_prod=n.oid_marc_prod
group by
psnumlote
, g.cod_pais
, to_char(a.fec_fact,'dd/mm/yyyy')
, f.cod_soci
, case when e.cod_tipo_soli in ('C52','C53') then 'NM'
       when e.cod_tipo_soli='C77' then 'FF'
       when e.cod_tipo_soli='CBR' then ls_codasieBR
       when e.cod_tipo_soli='C78' then ls_codasieRP
       when e.cod_tipo_soli='C10' then ls_codasieCD
       when e.ind_anul=1 then 'AN'
       when e.ind_soli_nega=1 then 'AB'
       else 'VN' end
, i.cod_peri
, c.taim_oid_tasa_impu
, o.cod_tipo_ofer
, n.cod_marc_prod
, m.val_grup_arti
, case when e.cod_tipo_soli in ('C52','C53') then 'NMP'
       when e.cod_tipo_soli='C77' then 'FALTANTE'
       when e.cod_tipo_soli='CBR' then ls_nomasieBR
       when e.cod_tipo_soli='C78' then ls_nomasieRP
       when e.cod_tipo_soli='C10' then ls_nomasieCD
       when e.ind_anul=1 then 'ANULACIONES'
       when e.ind_soli_nega=1 then 'ABONOS'
       else 'VENTA' end
, to_char(a.fec_fact,'yyyy');

          OPEN c_config;
          LOOP
              FETCH c_config INTO r_config;
              EXIT WHEN c_config%NOTFOUND;

              if r_config.ind_orig='C' then

                 insert into INT_SAF_RESUL
                  select
                  num_lote
                  , cod_pais
                  , to_date(fec_proc,'dd/mm/yyyy')
                  , to_date(fec_proc,'dd/mm/yyyy')
                  , cod_soci
                  , 'XXXX'
                  , cod_asie
                  , r_config.tip_movi
                  , ' '
                  , ' '
                  , ' '
                  , '1'
                  , case when r_config.tipo_asie='1' then
                         case when case
                                    when r_config.cod_impo='01' then val_tota_paga_loca
                                    when r_config.cod_impo='02' then imp_redo_loca
                                    when r_config.cod_impo='04' then VAL_TOTA_GAST_ADMI_SIN_IMPU
                                    when r_config.cod_impo='03' then VAL_IMP_FLET_IMPU_TOTA_LOCA
                                    when r_config.cod_impo='05' then IMP_IMPU_TOTA_LOCA
                                    when r_config.cod_impo='09' then VAL_IMPO_RETE_DESC
                                    when r_config.cod_impo='14' then VAL_IMPO_DESC_TOTA
                                    when r_config.cod_impo='15' then VAL_IMPO_IMPU_CREE
                                    when r_config.cod_impo='16' then VAL_IMPO_IVA_ASUM_EMPR
                                    when r_config.cod_impo='17' then VAL_TOTA_GAST_ADMI2_SIN_IMPU
                               end >=0
                               then r_config.ind_debe_habe else decode(r_config.ind_debe_habe,'D','H','D')
                          end
                    else
                         case when case
                                    when r_config.cod_impo='01' then val_tota_paga_loca
                                    when r_config.cod_impo='02' then imp_redo_loca
                                    when r_config.cod_impo='04' then VAL_TOTA_GAST_ADMI_SIN_IMPU
                                    when r_config.cod_impo='03' then VAL_IMP_FLET_IMPU_TOTA_LOCA
                                    when r_config.cod_impo='05' then IMP_IMPU_TOTA_LOCA
                                    when r_config.cod_impo='09' then VAL_IMPO_RETE_DESC
                                    when r_config.cod_impo='14' then VAL_IMPO_DESC_TOTA
                                    when r_config.cod_impo='15' then VAL_IMPO_IMPU_CREE
                                    when r_config.cod_impo='16' then VAL_IMPO_IVA_ASUM_EMPR
                                    when r_config.cod_impo='17' then VAL_TOTA_GAST_ADMI2_SIN_IMPU
                                    end <=0
                               then r_config.ind_debe_habe else decode(r_config.ind_debe_habe,'D','H','D')
                          end
                    end
                  , val_glos
                  , cod_peri
                  , val_ejer
                  , val_tasa
                  , abs(
                  case
                    when r_config.cod_impo='01' then val_tota_paga_loca
                    when r_config.cod_impo='02' then imp_redo_loca
                    when r_config.cod_impo='04' then VAL_TOTA_GAST_ADMI_SIN_IMPU
                    when r_config.cod_impo='03' then VAL_IMP_FLET_IMPU_TOTA_LOCA
                    when r_config.cod_impo='05' then IMP_IMPU_TOTA_LOCA
                    when r_config.cod_impo='09' then VAL_IMPO_RETE_DESC
                    when r_config.cod_impo='14' then VAL_IMPO_DESC_TOTA
                    when r_config.cod_impo='15' then VAL_IMPO_IMPU_CREE
                    when r_config.cod_impo='16' then VAL_IMPO_IVA_ASUM_EMPR
                    when r_config.cod_impo='17' then VAL_TOTA_GAST_ADMI2_SIN_IMPU
                  end
                  )
                   from int_sapfi_cabec x where --x.fec_proc=psfecha and
                   x.cod_asie=r_config.cod_asie;

              else
                 insert into INT_SAF_RESUL
                  select
                  num_lote
                  , cod_pais
                  , to_date(fec_proc,'dd/mm/yyyy')
                  , to_date(fec_proc,'dd/mm/yyyy')
                  , cod_soci
                  , 'XXXX'
                  , cod_asie
                  , r_config.tip_movi
                  , x.cod_tipo_ofert
                  , cod_marc_produ
                  , val_grup_arti
                  , '1'
                  , case when r_config.tipo_asie='1' then
                         case when case
                                      when r_config.cod_impo='08' then VAL_PREC_CONT_TOTA_LOCA
                                      when r_config.cod_impo='07' then IMP_DESC_SIN_IMPU_TOTA_LOCA
                                      when r_config.cod_impo='06' then VAL_PREC_SIN_IMPU_TOTA_LOCA
                                      when r_config.cod_impo='10' then IMP_IMPU_TOTA_PROD_NACI
                                      when r_config.cod_impo='11' then IMP_IMPU_TOTA_PROD_NACI_GRAT
                                      when r_config.cod_impo='13' then IMP_IMPU_TOTA_PROD_NACI_GRAT*0.12
                                      when r_config.cod_impo='12' then VAL_IMP_GRAT_TOTA_LOCA
                                    end >=0
                               then r_config.ind_debe_habe else decode(r_config.ind_debe_habe,'D','H','D')
                          end
                    else
                         case when case
                                      when r_config.cod_impo='08' then VAL_PREC_CONT_TOTA_LOCA
                                      when r_config.cod_impo='07' then IMP_DESC_SIN_IMPU_TOTA_LOCA
                                      when r_config.cod_impo='06' then VAL_PREC_SIN_IMPU_TOTA_LOCA
                                      when r_config.cod_impo='10' then IMP_IMPU_TOTA_PROD_NACI
                                      when r_config.cod_impo='11' then IMP_IMPU_TOTA_PROD_NACI_GRAT
                                      when r_config.cod_impo='13' then IMP_IMPU_TOTA_PROD_NACI_GRAT*0.12
                                      when r_config.cod_impo='12' then VAL_IMP_GRAT_TOTA_LOCA
                                    end <=0
                               then r_config.ind_debe_habe else decode(r_config.ind_debe_habe,'D','H','D')
                          end
                    end
                  , val_glos
                  , cod_peri
                  , val_ejer
                  , val_tasa
                  , abs(
                  case
                    when r_config.cod_impo='08' then VAL_PREC_CONT_TOTA_LOCA
                    when r_config.cod_impo='07' then IMP_DESC_SIN_IMPU_TOTA_LOCA
                    when r_config.cod_impo='06' then VAL_PREC_SIN_IMPU_TOTA_LOCA
                    when r_config.cod_impo='10' then IMP_IMPU_TOTA_PROD_NACI
                    when r_config.cod_impo='11' then IMP_IMPU_TOTA_PROD_NACI_GRAT
                    when r_config.cod_impo='13' then IMP_IMPU_TOTA_PROD_NACI_GRAT*0.12
                    when r_config.cod_impo='12' then VAL_IMP_GRAT_TOTA_LOCA
                  end
                  )
                   from int_sapfi_detal x where --x.fec_proc=psfecha and
                   x.cod_asie=r_config.cod_asie;

                end if;





          END LOOP;
          CLOSE c_config;


          update int_saf_resul set ind_debe_habe=decode(ind_debe_habe,'D','H','D')
          where cod_asie not in (select distinct z.cod_asie from int_sapfi_conf z where z.tipo_asie='1');





          OPEN c_ajuste;
          LOOP
              FETCH c_ajuste INTO r_ajuste;
              EXIT WHEN c_ajuste%NOTFOUND;

              select
              (select sum(x.val_mont) from int_saf_resul x
              where x.cod_asie=r_ajuste.cod_asie
              and x.cod_peri=r_ajuste.cod_peri
              and x.ind_debe_habe='D')
              -
              (select sum(x.val_mont) from int_saf_resul x
              where x.cod_asie=r_ajuste.cod_asie
              and x.cod_peri=r_ajuste.cod_peri
              and x.ind_debe_habe='H')
              into ln_diferencia
              from dual;

              if abs(ln_diferencia)>0 and abs(ln_diferencia)<0.6 then
                update int_saf_resul x set x.val_mont=x.val_mont+ln_diferencia
                where x.cod_asie=r_ajuste.cod_asie and x.cod_peri=r_ajuste.cod_peri
                and x.tip_mov='005';
              end if;

          END LOOP;
          CLOSE c_ajuste;


          update fac_docum_conta_cabec a
          set a.num_lote_cont=psnumlote
          where a.fec_fact>=to_date(psfecha,'dd/mm/yyyy')-3
          and a.fec_fact<=to_date(psfecha,'dd/mm/yyyy')
          and a.tido_oid_tipo_docu not in (8)
          and a.num_lote_cont is null
          ;


  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR int_pr_carga_saf_corpo: ' || ls_sqlerrm);
  END int_pr_carga_saf_corpo;
  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Comision Ejecutiva para SAP-FI
  Fecha Creacion    : 09/07/2013
  Autor             : Carlos Bazalar
  ***************************************************************************/
  PROCEDURE INT_PR_SAF_ENVIO_COMIS_EJECU
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psCodigoPeriodo  VARCHAR2
  ) IS

    lsFecProc      bas_Ctrl_Fact.Fec_Proc%TYPE;

    CURSOR c_interfaz IS
    SELECT NULL FechaDocumento,
           (SELECT pain.val_pain
              FROM bas_param_inter pain
             WHERE pain.pais_cod_pais = pscodigopais
               AND pain.sist_cod_sist = pscodigosistema
               AND pain.inte_cod_inte = pscodigointerfaz
               AND UPPER(pain.nom_pain) = 'CLASEDOCUMENTO'
           ) ClaseDocumento,
           NULL CodigoSociedad,
           NULL FechaContable,
           NULL CampannaProceso,
           mone.cod_mone CodigoMoneda,
           NULL NumDocEquivalente,
           'Comisión Ejec C' || SUBSTR(psCodigoPeriodo,5,2) || SUBSTR(psCodigoPeriodo,1,4) TextoCabecera,
           (SELECT pain.val_pain
              FROM bas_param_inter pain
             WHERE pain.pais_cod_pais = pscodigopais
               AND pain.sist_cod_sist = pscodigosistema
               AND pain.inte_cod_inte =  pscodigointerfaz
               AND UPPER(pain.nom_pain) = 'CLAVEPROVEEDOR'
           ) ClaveProveedor,
           NULL CodProvEjecutiva,
           (SELECT pain.val_pain
              FROM bas_param_inter pain
             WHERE pain.pais_cod_pais = pscodigopais
               AND pain.sist_cod_sist = pscodigosistema
               AND pain.inte_cod_inte = pscodigointerfaz
               AND UPPER(pain.nom_pain) = 'INDCALCULOIMPUESTO'
           ) IndCalculoImpuesto,
           NULL ImporteComision,
           (SELECT pain.val_pain
              FROM bas_param_inter pain
            WHERE pain.pais_cod_pais = pscodigopais
               AND pain.sist_cod_sist = pscodigosistema
               AND pain.inte_cod_inte =  pscodigointerfaz
               AND UPPER(pain.nom_pain) = 'CLAVECUENTAGASTO'
           ) ClaveCuentaGasto,
           (SELECT pain.val_pain
              FROM bas_param_inter pain
            WHERE pain.pais_cod_pais = pscodigopais
               AND pain.sist_cod_sist = pscodigosistema
               AND pain.inte_cod_inte =  pscodigointerfaz
               AND UPPER(pain.nom_pain) = 'CUENTACONTABLE'
           ) CuentaContable,
           (SELECT pain.val_pain
              FROM bas_param_inter pain
            WHERE pain.pais_cod_pais = pscodigopais
               AND pain.sist_cod_sist = pscodigosistema
               AND pain.inte_cod_inte =  pscodigointerfaz
               AND UPPER(pain.nom_pain) = 'INDICADORTASAIMP'
           ) IndicadorTasaImp,
           NULL ImporteComision2,
           (SELECT pain.val_pain
              FROM bas_param_inter pain
            WHERE pain.pais_cod_pais = pscodigopais
               AND pain.sist_cod_sist = pscodigosistema
               AND pain.inte_cod_inte =  pscodigointerfaz
               AND UPPER(pain.nom_pain) = 'INDICADORTASAIMP'
           ) CentroCosto,
           (SELECT pain.val_pain
              FROM bas_param_inter pain
            WHERE pain.pais_cod_pais = pscodigopais
               AND pain.sist_cod_sist = pscodigosistema
               AND pain.inte_cod_inte =  pscodigointerfaz
               AND UPPER(pain.nom_pain) = 'MARCA'
           ) Marca,
           NULL CampannaComision,
           NULL AnnoCampanna,
           NULL NumDocEjecutiva,
           (SELECT pain.val_pain
              FROM bas_param_inter pain
          WHERE pain.pais_cod_pais = pscodigopais
               AND pain.sist_cod_sist = pscodigosistema
               AND pain.inte_cod_inte =  pscodigointerfaz
               AND UPPER(pain.nom_pain) = 'INDFINDOCU'
           ) IndFinDocu
      FROM eje_liqui_campa lica,
           eje_progr prog,
           seg_pais pais,
           seg_moned mone
    WHERE lica.pais_cod_pais = prog.pais_cod_pais
       AND lica.cod_prog_ejec = prog.cod_prog_ejec
       AND lica.pais_cod_pais = pais.cod_pais
       AND pais.mone_oid_mone = mone.oid_mone
       AND lica.cam_proc = psCodigoPeriodo ;

    TYPE interfazrec IS RECORD(
      fechaDocumento							VARCHAR2(10),
      claseDocumento              VARCHAR2(2),
      codigoSociedad              VARCHAR2(4),
      fechaContable               VARCHAR2(10),
      campannaProceso             VARCHAR2(2),
      codigoMoneda                VARCHAR2(3),
      numDocEquivalente           VARCHAR2(16),
      textoCabecera               VARCHAR2(25),
      claveProveedor              VARCHAR2(2),
      codProvEjecutiva            VARCHAR2(10),
      indCalculoImpuesto          VARCHAR2(1),
      importeComision             VARCHAR2(15),
      claveCuentaGasto            VARCHAR2(2),
      cuentaContable              VARCHAR2(10),
      indicadorTasaImp            VARCHAR2(2),
      importeComision2            VARCHAR2(15),
      centroCosto                 VARCHAR2(6),
      marca                       VARCHAR2(2),
      campannaComision            VARCHAR2(4),
      annoCampanna                VARCHAR2(4),
      numDocEjecutiva             VARCHAR2(12),
      indFinDocu                  VARCHAR2(1)
    );

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         NUMBER := 1000;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);
    lbabrirutlfile  BOOLEAN;
  BEGIN

    /* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x).fechaDocumento			     || ';' ||
                     interfazrecord(x).claseDocumento          || ';' ||
                     interfazrecord(x).codigoSociedad          || ';' ||
                     interfazrecord(x).fechaContable           || ';' ||
                     interfazrecord(x).campannaProceso         || ';' ||
                     interfazrecord(x).codigoMoneda            || ';' ||
                     interfazrecord(x).numDocEquivalente       || ';' ||
                     interfazrecord(x).textoCabecera           || ';' ||
                     interfazrecord(x).claveProveedor          || ';' ||
                     interfazrecord(x).codProvEjecutiva        || ';' ||
                     interfazrecord(x).indCalculoImpuesto      || ';' ||
                     interfazrecord(x).importeComision         || ';' ||
                     interfazrecord(x).claveCuentaGasto        || ';' ||
                     interfazrecord(x).cuentaContable          || ';' ||
                     interfazrecord(x).indicadorTasaImp        || ';' ||
                     interfazrecord(x).importeComision2        || ';' ||
                     interfazrecord(x).centroCosto             || ';' ||
                     interfazrecord(x).marca                   || ';' ||
                     interfazrecord(x).campannaComision        || ';' ||
                     interfazrecord(x).annoCampanna            || ';' ||
                     interfazrecord(x).numDocEjecutiva         || ';' ||
                     interfazrecord(x).indFinDocu   ;
          utl_file.put_line(v_handle,lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123, 'ERROR INT_PR_SAF_ENVIO_COMIS_EJECU: ' || ls_sqlerrm);
  END INT_PR_SAF_ENVIO_COMIS_EJECU;

/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte SAPFI Cabecera
Fecha Creacion    : 07/02/2014
Autor             : Gonzalo Javier Huertas Agurto
Parametros        :
            psCodigoPeriodo : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE INT_PR_SAF_REPOR_SAPF_CABE(

    psCodigoPais                        VARCHAR2,
    psFechaProceso                      VARCHAR2,
    pscodigosistema                     VARCHAR2,
    pscodigointerfaz                    VARCHAR2,
    psnombrearchivo                     VARCHAR2
    )
IS

  W_FILAS             NUMBER := 5000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(4000);
  lnValTasa           VARCHAR2(20);
  lnTotalAPagar       VARCHAR2(20);
  lnRedondeo          VARCHAR2(20);
  lnImpuesto          VARCHAR2(20);
  lnGastosAadministrativos   VARCHAR2(20);
  lnValImpoReteDesc          VARCHAR2(20);
  lnIce                      VARCHAR2(20);
  lnFlete                    VARCHAR2(20);

  CURSOR c_interfaz IS
      select a.oid_cabe NUMERO_DOCUMENTO,
           g.cod_pais COD_PAIS,
           to_char(a.fec_fact,'dd/MM/yyyy') FEC_PROC,
           f.cod_soci COD_SOCI,
           case
             when e.cod_tipo_soli in ('C52', 'C53') then
              'NM'
             when e.cod_tipo_soli = 'C77' then
              'FF'
             when e.ind_anul = 1 then
              'AN'
             when e.ind_soli_nega = 1 then
              'AB'
             else
              'VN'
           end COD_ASIE,
           i.cod_peri COD_PERI,
           c.val_tasa_impu VAL_TASA,
           to_char(a.fec_fact, 'yyyy') VAL_EJER,
           case
             when a.ICTP_OID_TIPO_PROG is not null then
              0
             else
              a.val_tota_paga_loca
           end TOTAL_A_PAGAR,
           case
             when a.ICTP_OID_TIPO_PROG is not null then
              0
             else
              a.imp_redo_loca
           end REDONDEO,
           case
             when a.ICTP_OID_TIPO_PROG is not null then
              0
             else
              a.imp_impu_tota_loca
           end IMPUESTO,
           case
             when a.ICTP_OID_TIPO_PROG is not null then
              0
             else
              nvl(a.VAL_TOTA_GAST_ADMI_SIN_IMPU, 0)
           end GASTOS_ADMINISTRATIVOS,
           case
             when a.ICTP_OID_TIPO_PROG is not null then
              0
             else
              nvl(a.val_impo_rete_desc, 0)
           end VAL_IMPO_RETE_DESC,
           case
             when a.ICTP_OID_TIPO_PROG is not null then
              0
             else
              nvl(a.IMP_IVA_IMPU_TOTA_PROD_NACI, 0)
           end ICE,
           case
             when a.ICTP_OID_TIPO_PROG is not null then
              0
             else
              a.IMP_FLET_IMPU_TOTA_LOCA
           end FLETE
      from fac_docum_conta_cabec a,
           ped_solic_cabec       c,
           ped_tipo_solic_pais   d,
           ped_tipo_solic        e,
           seg_socie             f,
           seg_pais              g,
           cra_perio             h,
           seg_perio_corpo       i
     where a.fec_fact = psFechaProceso
       and a.soca_oid_soli_cabe = c.oid_soli_cabe
       and c.tspa_oid_tipo_soli_pais = d.oid_tipo_soli_pais
       and d.tsol_oid_tipo_soli = e.oid_tipo_soli
       and c.pais_oid_pais = g.oid_pais
       and c.soci_oid_soci = f.oid_soci
       and c.perd_oid_peri = h.oid_peri
       and h.peri_oid_peri = i.oid_peri
       and a.tido_oid_tipo_docu not in (8);

TYPE interfazTipo IS RECORD (

    v_NUMERO_DOCUMENTO  NUMBER(12),
    v_COD_PAIS	        VARCHAR2(3),
    v_FEC_PROC	        VARCHAR2(10),
    v_COD_SOCI	        VARCHAR2(4),
    v_COD_ASIE	        VARCHAR2(2),
    v_COD_PERI	        VARCHAR2(6),
    v_VAL_TASA	        NUMBER(6,2),
    v_VAL_EJER	        VARCHAR2(4),
    v_TOTAL_A_PAGAR     NUMBER(12,2),
    v_REDONDEO          NUMBER(12,2),
    v_IMPUESTO	        NUMBER(12,2),
    v_GASTOS_ADMINISTRATIVOS  NUMBER(12,2),
    v_VAL_IMPO_RETE_DESC NUMBER(12,2),
    v_ICE		             NUMBER(12,2),
    v_FLETE		           NUMBER(12,2)
);

   TYPE interfazTab  IS TABLE OF interfazTipo ;
   interfazRecord interfazTab;
   lbAbrirUtlFile  BOOLEAN;
     /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    lsnombrearchivo VARCHAR2(50);

BEGIN

  lbAbrirUtlFile := TRUE;
  DELETE FROM INT_SAF_REPOR_SAPF_CABE;

  OPEN c_interfaz;
  LOOP
   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
   IF lbAbrirUtlFile THEN
      gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
      lbAbrirUtlFile := FALSE;

   END IF ;
   IF interfazRecord.COUNT > 0 THEN

      lsLinea :='Numero Documento; Codigo Pais; Fecha Proceso; Codigo Sociedad; Codigo Asiento; Campaña; Valor Tasa; Val. Ejer; Total a Pagar; Redondeo; Impuesto; Gastos Administrativos; Valor Importe Retencion; Ice; Flete';
      UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          select decode(ROUND(interfazRecord(x).v_VAL_TASA, 0),0,to_char(interfazRecord(x).v_VAL_TASA,'0.99'),TO_CHAR(interfazRecord(x).v_VAL_TASA,'999999999.99'))
          into lnValTasa
          from dual;

          select decode(ROUND(interfazRecord(x).v_TOTAL_A_PAGAR, 0),0,to_char(interfazRecord(x).v_TOTAL_A_PAGAR,'0.99'),TO_CHAR(interfazRecord(x).v_TOTAL_A_PAGAR,'999999999.99'))
          into lnTotalAPagar
          from dual;

          select decode(ROUND(interfazRecord(x).v_REDONDEO, 0),0,to_char(interfazRecord(x).v_REDONDEO,'0.99'),TO_CHAR(interfazRecord(x).v_REDONDEO,'999999999.99'))
          into lnRedondeo
          from dual;

          select decode(ROUND(interfazRecord(x).v_IMPUESTO, 0),0,to_char(interfazRecord(x).v_IMPUESTO,'0.99'),TO_CHAR(interfazRecord(x).v_IMPUESTO,'999999999.99'))
          into lnImpuesto
          from dual;

          select decode(ROUND(interfazRecord(x).v_GASTOS_ADMINISTRATIVOS, 0),0,to_char(interfazRecord(x).v_GASTOS_ADMINISTRATIVOS,'0.99'),TO_CHAR(interfazRecord(x).v_GASTOS_ADMINISTRATIVOS,'999999999.99'))
          into lnGastosAadministrativos
          from dual;

          select decode(ROUND(interfazRecord(x).v_VAL_IMPO_RETE_DESC, 0),0,to_char(interfazRecord(x).v_VAL_IMPO_RETE_DESC,'0.99'),TO_CHAR(interfazRecord(x).v_VAL_IMPO_RETE_DESC,'999999999.99'))
          into lnValImpoReteDesc
          from dual;

          select decode(ROUND(interfazRecord(x).v_ICE, 0),0,to_char(interfazRecord(x).v_ICE,'0.99'),TO_CHAR(interfazRecord(x).v_ICE,'999999999.99'))
          into lnIce
          from dual;

          select decode(ROUND(interfazRecord(x).v_FLETE, 0),0,to_char(interfazRecord(x).v_FLETE,'0.99'),TO_CHAR(interfazRecord(x).v_FLETE,'999999999.99'))
          into lnFlete
          from dual;

          lsLinea := '=T("'||interfazRecord(x).v_NUMERO_DOCUMENTO||'")' ||';'||
                     interfazRecord(x).v_COD_PAIS||';'||
                     '=T("'||interfazRecord(x).v_FEC_PROC||'")' ||';'||
                     interfazRecord(x).v_COD_SOCI||';'||
                     interfazRecord(x).v_COD_ASIE||';'||
                     '=T("'||interfazRecord(x).v_COD_PERI||'")' ||';'||
                     '=T("'||lnValTasa||'")'||';'||
                     interfazRecord(x).v_VAL_EJER||';'||
                     '=T("'||lnTotalAPagar||'")'||';'||
                     '=T("'||lnRedondeo||'")'||';'||
                     '=T("'||lnImpuesto||'")'||';'||
                     '=T("'||lnGastosAadministrativos||'")'||';'||
                     '=T("'||lnValImpoReteDesc||'")'||';'||
                     '=T("'||lnIce||'")'||';'||
                     '=T("'||lnFlete||'")';
            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

            insert into INT_SAF_REPOR_SAPF_CABE (NUM_DOCU,COD_PAIS, FEC_PROC, COD_SOCI, COD_ASIE,COD_PERI,VAL_TASA,VAL_EJER,TOT_A_PAGAR, VAL_REDO,VAL_IMPU, GAS_ADMI, VAL_IMPO_RETE_DESC, VAL_ICE, VAL_FLET)
            values(interfazRecord(x).v_NUMERO_DOCUMENTO,interfazRecord(x).v_COD_PAIS,interfazRecord(x).v_FEC_PROC,interfazRecord(x).v_COD_SOCI,interfazRecord(x).v_COD_ASIE,interfazRecord(x).v_COD_PERI,interfazRecord(x).v_VAL_TASA,interfazRecord(x).v_VAL_EJER,interfazRecord(x).v_TOTAL_A_PAGAR,interfazRecord(x).v_REDONDEO,interfazRecord(x).v_IMPUESTO,interfazRecord(x).v_GASTOS_ADMINISTRATIVOS,interfazRecord(x).v_VAL_IMPO_RETE_DESC,interfazRecord(x).v_ICE,interfazRecord(x).v_FLETE);
      END LOOP;
    END IF;
    EXIT WHEN c_interfaz%NOTFOUND;
 END LOOP;
 CLOSE c_interfaz;
 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
 /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
 END IF;
 RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_SAF_REPOR_SAPF_CABE: '||ls_sqlerrm);
END INT_PR_SAF_REPOR_SAPF_CABE;

/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte SAPFI Detalle
Fecha Creacion    : 07/02/2014
Autor             : Gonzalo Javier Huertas Agurto
Parametros        :
            psCodigoPeriodo : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE INT_PR_SAF_REPOR_SAPF_DETA(

    psCodigoPais                        VARCHAR2,
    psFechaProceso                      VARCHAR2,
    pscodigosistema                     VARCHAR2,
    pscodigointerfaz                    VARCHAR2,
    psnombrearchivo                     VARCHAR2
    )
IS

  W_FILAS             NUMBER := 5000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(4000);
  lnValTasa           VARCHAR2(20);
  lnVentaDetalle      VARCHAR2(20);
  lnDescuentoVolumen  VARCHAR2(20);
  lnDescuentoGratis   VARCHAR2(20);
  lnIce               VARCHAR2(20);

  CURSOR c_interfaz IS
  select a.oid_cabe NUMERO_DOCUMENTO,
         g.cod_pais COD_PAIS,
         to_char(a.fec_fact,'dd/MM/yyyy') FEC_PROC,
         f.cod_soci COD_SOCI,
         case
           when e.cod_tipo_soli in ('C52', 'C53') then
            'NM'
           when e.cod_tipo_soli = 'C77' then
            'FF'
           when e.ind_anul = 1 then
            'AN'
           when e.ind_soli_nega = 1 then
            'AB'
           else
            'VN'
         end COD_ASIE,
         i.cod_peri COD_PERI,
         c.val_tasa_impu VAL_TASA,
         to_char(a.fec_fact, 'yyyy') VAL_EJER,
         case
           when a.ICTP_OID_TIPO_PROG is not null then
            0
           else
            j.val_prec_sin_impu_tota_loca
         end VENTA_DETALLE,
         case
           when a.ICTP_OID_TIPO_PROG is not null then
            0
           else
            j.imp_desc_sin_impu_tota_loca
         end DESCUENTO_VOLUMEN,
         case
           when a.ICTP_OID_TIPO_PROG is not null then
            0
           else
            decode(j.val_prec_cont_tota_loca,
                   0,
                   0,
                   j.val_prec_sin_impu_tota_loca)
         end DESCUENTO_GRATIS,
         o.cod_tipo_ofer COD_TIPO_OFER,
         n.cod_marc_prod COD_MARC_PRODU,
         m.val_grup_arti VAL_GRUP_ARTI,
         case
           when a.ICTP_OID_TIPO_PROG is not null then
            0
           else
            J.IMP_IMPU_TOTA_PROD_NACI
         end ICE
    from fac_docum_conta_cabec a,
         ped_solic_cabec       c,
         ped_tipo_solic_pais   d,
         ped_tipo_solic        e,
         seg_socie             f,
         seg_pais              g,
         cra_perio             h,
         seg_perio_corpo       i,
         fac_docum_conta_linea j,
         ped_solic_posic       k,
         pre_ofert_detal       l,
         mae_produ             m,
         seg_marca_produ       n,
         pre_tipo_ofert        o
   where a.fec_fact = psFechaProceso
     and a.soca_oid_soli_cabe = c.oid_soli_cabe
     and c.tspa_oid_tipo_soli_pais = d.oid_tipo_soli_pais
     and d.tsol_oid_tipo_soli = e.oid_tipo_soli
     and c.pais_oid_pais = g.oid_pais
     and c.soci_oid_soci = f.oid_soci
     and c.perd_oid_peri = h.oid_peri
     and h.peri_oid_peri = i.oid_peri
     and a.tido_oid_tipo_docu not in (8)
     and a.oid_cabe = j.dcca_oid_cabe
     and j.sopo_oid_soli_posi = k.oid_soli_posi
     and k.ofde_oid_deta_ofer = l.oid_deta_ofer(+)
     and l.tofe_oid_tipo_ofer = o.oid_tipo_ofer(+)
     and j.prod_oid_prod = m.oid_prod
     and m.mapr_oid_marc_prod = n.oid_marc_prod;

TYPE interfazTipo IS RECORD (

    v_NUMERO_DOCUMENTO  NUMBER(12),
    v_COD_PAIS	        VARCHAR2(3),
    v_FEC_PROC	        VARCHAR2(10),
    v_COD_SOCI	        VARCHAR2(4),
    v_COD_ASIE	        VARCHAR2(2),
    v_COD_PERI	        VARCHAR2(6),
    v_VAL_TASA	        NUMBER(6,2),
    v_VAL_EJER	        VARCHAR2(4),
    v_VENTA_DETALLE		  NUMBER(12,2),
    v_DESCUENTO_VOLUMEN	NUMBER(12,2),
    v_DESCUENTO_GRATIS	NUMBER(12,2),
    v_COD_TIPO_OFER		  VARCHAR2(4),
    v_COD_MARC_PRODU		VARCHAR2(3),
    v_VAL_GRUP_ARTI		  VARCHAR2(9),
    v_ICE			          NUMBER(12,2)
);

   TYPE interfazTab  IS TABLE OF interfazTipo ;
   interfazRecord interfazTab;
   lbAbrirUtlFile  BOOLEAN;
     /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    lsnombrearchivo VARCHAR2(50);

BEGIN

  lbAbrirUtlFile := TRUE;
  DELETE FROM INT_SAF_REPOR_SAPF_DETA;

  OPEN c_interfaz;
  LOOP
   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
   IF lbAbrirUtlFile THEN
      gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
      lbAbrirUtlFile := FALSE;

   END IF ;
   IF interfazRecord.COUNT > 0 THEN
      lsLinea :='Numero Documento; Codigo Pais; Fecha Proceso; Codigo Sociedad; Codigo Asiento; Campaña; Valor Tasa; Val. Ejer; Venta Detalle; Descuento Volumen; Descuento Gratis; Codigo Tipo Oferta; Codigo Marca Producto; Grupo Articulos; Ice';
      UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          select decode(ROUND(interfazRecord(x).v_VAL_TASA, 0),0,to_char(interfazRecord(x).v_VAL_TASA,'0.99'),TO_CHAR(interfazRecord(x).v_VAL_TASA,'999999999.99'))
          into lnValTasa
          from dual;

          select decode(ROUND(interfazRecord(x).v_VENTA_DETALLE, 0),0,to_char(interfazRecord(x).v_VENTA_DETALLE,'0.99'),TO_CHAR(interfazRecord(x).v_VENTA_DETALLE,'999999999.99'))
          into lnVentaDetalle
          from dual;

          select decode(ROUND(interfazRecord(x).v_DESCUENTO_VOLUMEN, 0),0,to_char(interfazRecord(x).v_DESCUENTO_VOLUMEN,'0.99'),TO_CHAR(interfazRecord(x).v_DESCUENTO_VOLUMEN,'999999999.99'))
          into lnDescuentoVolumen
          from dual;

          select decode(ROUND(interfazRecord(x).v_DESCUENTO_GRATIS, 0),0,to_char(interfazRecord(x).v_DESCUENTO_GRATIS,'0.99'),TO_CHAR(interfazRecord(x).v_DESCUENTO_GRATIS,'999999999.99'))
          into lnDescuentoGratis
          from dual;

          select decode(ROUND(interfazRecord(x).v_ICE, 0),0,to_char(interfazRecord(x).v_ICE,'0.99'),TO_CHAR(interfazRecord(x).v_ICE,'999999999.99'))
          into lnIce
          from dual;

          lsLinea := '=T("'||interfazRecord(x).v_NUMERO_DOCUMENTO||'")' ||';'||
                     interfazRecord(x).v_COD_PAIS||';'||
                     '=T("'||interfazRecord(x).v_FEC_PROC||'")' ||';'||
                     interfazRecord(x).v_COD_SOCI||';'||
                     interfazRecord(x).v_COD_ASIE||';'||
                     '=T("'||interfazRecord(x).v_COD_PERI||'")' ||';'||
                     '=T("'||lnValTasa||'")'||';'||
                     interfazRecord(x).v_VAL_EJER||';'||
                     '=T("'||lnVentaDetalle||'")'||';'||
                     '=T("'||lnDescuentoVolumen||'")'||';'||
                     '=T("'||lnDescuentoGratis||'")'||';'||
                     '=T("'||interfazRecord(x).v_COD_TIPO_OFER||'")'||';'||
                     '=T("'||interfazRecord(x).v_COD_MARC_PRODU||'")'||';'||
                     '=T("'||interfazRecord(x).v_VAL_GRUP_ARTI||'")'||';'||
                     '=T("'||lnIce||'")';
            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

            insert into INT_SAF_REPOR_SAPF_DETA (NUM_DOCU,COD_PAIS,FEC_PROC,COD_SOCI,COD_ASIE,COD_PERI,VAL_TASA,VAL_EJER,VEN_DETA,DES_VOLU,DES_GRAT,COD_TIPO_OFER,COD_MARC_PRODU,VAL_GRUP_ARTI,VAL_ICE)
            values(interfazRecord(x).v_NUMERO_DOCUMENTO,interfazRecord(x).v_COD_PAIS,interfazRecord(x).v_FEC_PROC,interfazRecord(x).v_COD_SOCI,interfazRecord(x).v_COD_ASIE,interfazRecord(x).v_COD_PERI,interfazRecord(x).v_VAL_TASA,interfazRecord(x).v_VAL_EJER,interfazRecord(x).v_VENTA_DETALLE,interfazRecord(x).v_DESCUENTO_VOLUMEN,interfazRecord(x).v_DESCUENTO_GRATIS,interfazRecord(x).v_COD_TIPO_OFER,interfazRecord(x).v_COD_MARC_PRODU,interfazRecord(x).v_VAL_GRUP_ARTI,interfazRecord(x).v_ICE);
      END LOOP;
    END IF;
    EXIT WHEN c_interfaz%NOTFOUND;
 END LOOP;
 CLOSE c_interfaz;
 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
     /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
 END IF;
 RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_SAF_REPOR_SAPF_DETA: '||ls_sqlerrm);
END INT_PR_SAF_REPOR_SAPF_DETA;

/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte SAPFI Resumen
Fecha Creacion    : 07/02/2014
Autor             : Gonzalo Javier Huertas Agurto
Parametros        :
            psCodigoPeriodo : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE INT_PR_SAF_REPOR_SAPF_RESU(

    psCodigoPais                        VARCHAR2,
    pscodigosistema                     VARCHAR2,
    pscodigointerfaz                    VARCHAR2,
    psnombrearchivo                     VARCHAR2
    )
IS
  W_FILAS             NUMBER := 5000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(4000);
  lnSumTotalPagar     VARCHAR2(20);
  lnSumRedondeo       VARCHAR2(20);
  lnSumImpuestos      VARCHAR2(20);
  lnSumGastosAdministrativos   VARCHAR2(20);
  lnSumRetencion      VARCHAR2(20);
  lnSumIce            VARCHAR2(20);
  lnSumFlete          VARCHAR2(20);
  lnSumVenta          VARCHAR2(20);
  lnSumDescuentoGratis  VARCHAR2(20);
  lnSumDescuentoVolumen VARCHAR2(20);

  CURSOR c_interfaz IS
  SELECT CAB.COD_ASIE,
       CAB.FEC_PROC,
       SUM(CAB.TOT_A_PAGAR) Sum_tot_a_pagar,
       SUM(CAB.VAL_REDO) sum_redo,
       SUM(CAB.VAL_IMPU) sum_impu,
       SUM(CAB.GAS_ADMI) sum_gastos_administrativos,
       SUM(CAB.VAL_IMPO_RETE_DESC) sum_retencion,
       SUM(CAB.VAL_ICE) sum_ice,
       SUM(CAB.VAL_FLET) sum_flete,
       SUM(DET.VEN_DETA) sum_venta,
       SUM(DET.DES_GRAT) sum_dscto_gratis,
       SUM(DET.DES_VOLU) sum_dscto_volumen
  FROM INT_SAF_REPOR_SAPF_CABE CAB, INT_SAF_REPOR_SAPF_DETA DET
  where cab.num_docu= det.num_docu
 GROUP BY CAB.COD_ASIE, CAB.FEC_PROC;

TYPE interfazTipo IS RECORD (

    v_COD_ASIE	                  VARCHAR2(2),
    v_FEC_PROC	                  VARCHAR2(10),
    v_Sum_tot_a_pagar             NUMBER(12,2),
    v_sum_redo                    NUMBER(12,2),
    v_sum_impu	                  NUMBER(12,2),
    v_sum_gastos_administrativos  NUMBER(12,2),
    v_sum_retencion               NUMBER(12,2),
    v_sum_ice		                  NUMBER(12,2),
    v_sum_flete		                NUMBER(12,2),
    v_sum_venta		                NUMBER(12,2),
    v_sum_dscto_gratis	          NUMBER(12,2),
    v_sum_dscto_volumen	          NUMBER(12,2)
);

   TYPE interfazTab  IS TABLE OF interfazTipo ;
   interfazRecord interfazTab;
   lbAbrirUtlFile  BOOLEAN;
     /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    lsnombrearchivo VARCHAR2(50);

BEGIN

  lbAbrirUtlFile := TRUE;

  OPEN c_interfaz;
  LOOP
   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
   IF lbAbrirUtlFile THEN
      gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
      lbAbrirUtlFile := FALSE;

   END IF ;
   IF interfazRecord.COUNT > 0 THEN
      lsLinea :='Codigo Asiento; Fecha Proceso; Suma Total a Pagar; Total Redondeo; Total Impuestos; Total Gastos Administrativos; Total Retencion; Total Ice; Total Flete; Total Venta; Total Descuento Volumen; Total Descuento Gratis';
      UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP

          select decode(ROUND(interfazRecord(x).v_Sum_tot_a_pagar, 0),0,to_char(interfazRecord(x).v_Sum_tot_a_pagar,'0.99'),TO_CHAR(interfazRecord(x).v_Sum_tot_a_pagar,'999999999.99'))
          into lnSumTotalPagar
          from dual;

          select decode(ROUND(interfazRecord(x).v_sum_redo, 0),0,to_char(interfazRecord(x).v_sum_redo,'0.99'),TO_CHAR(interfazRecord(x).v_sum_redo,'999999999.99'))
          into lnSumRedondeo
          from dual;

          select decode(ROUND(interfazRecord(x).v_sum_impu, 0),0,to_char(interfazRecord(x).v_sum_impu,'0.99'),TO_CHAR(interfazRecord(x).v_sum_impu,'999999999.99'))
          into lnSumImpuestos
          from dual;

          select decode(ROUND(interfazRecord(x).v_sum_gastos_administrativos, 0),0,to_char(interfazRecord(x).v_sum_gastos_administrativos,'0.99'),TO_CHAR(interfazRecord(x).v_sum_gastos_administrativos,'999999999.99'))
          into lnSumGastosAdministrativos
          from dual;

          select decode(ROUND(interfazRecord(x).v_sum_retencion, 0),0,to_char(interfazRecord(x).v_sum_retencion,'0.99'),TO_CHAR(interfazRecord(x).v_sum_retencion,'999999999.99'))
          into lnSumRetencion
          from dual;

          select decode(ROUND(interfazRecord(x).v_sum_ice, 0),0,to_char(interfazRecord(x).v_sum_ice,'0.99'),TO_CHAR(interfazRecord(x).v_sum_ice,'999999999.99'))
          into lnSumIce
          from dual;

          select decode(ROUND(interfazRecord(x).v_sum_flete, 0),0,to_char(interfazRecord(x).v_sum_flete,'0.99'),TO_CHAR(interfazRecord(x).v_sum_flete,'999999999.99'))
          into lnSumFlete
          from dual;

          select decode(ROUND(interfazRecord(x).v_sum_venta, 0),0,to_char(interfazRecord(x).v_sum_venta,'0.99'),TO_CHAR(interfazRecord(x).v_sum_venta,'999999999.99'))
          into lnSumVenta
          from dual;

          select decode(ROUND(interfazRecord(x).v_sum_dscto_gratis, 0),0,to_char(interfazRecord(x).v_sum_dscto_gratis,'0.99'),TO_CHAR(interfazRecord(x).v_sum_dscto_gratis,'999999999.99'))
          into lnSumDescuentoGratis
          from dual;

          select decode(ROUND(interfazRecord(x).v_sum_dscto_volumen, 0),0,to_char(interfazRecord(x).v_sum_dscto_volumen,'0.99'),TO_CHAR(interfazRecord(x).v_sum_dscto_volumen,'999999999.99'))
          into lnSumDescuentoVolumen
          from dual;

          lsLinea := interfazRecord(x).v_COD_ASIE||';'||
                     '=T("'||interfazRecord(x).v_FEC_PROC||'")' ||';'||
                     '=T("'||lnSumTotalPagar||'")'||';'||
                     '=T("'||lnSumRedondeo||'")'||';'||
                     '=T("'||lnSumImpuestos||'")'||';'||
                     '=T("'||lnSumGastosAdministrativos||'")'||';'||
                     '=T("'||lnSumRetencion||'")'||';'||
                     '=T("'||lnSumIce||'")'||';'||
                     '=T("'||lnSumFlete||'")'||';'||
                     '=T("'||lnSumVenta||'")'||';'||
                     '=T("'||lnSumDescuentoGratis||'")'||';'||
                     '=T("'||lnSumDescuentoVolumen||'")';
            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

      END LOOP;
    END IF;
    EXIT WHEN c_interfaz%NOTFOUND;
 END LOOP;
 CLOSE c_interfaz;
 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
     /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
 END IF;
 RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_SAF_REPOR_SAPF_RESU: '||ls_sqlerrm);
END INT_PR_SAF_REPOR_SAPF_RESU;



 PROCEDURE INT_PR_SAF_PAGOS_LIDER
  (
    pscodigopais     VARCHAR2,
    psCodigoPeriodo  VARCHAR2,
    pscodigoPrograma  VARCHAR2,
    psfecha          VARCHAR2,
    pscodigoTipoPago  VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pstipoenvio      VARCHAR2
  ) is

   pscodigoMoneda     seg_moned.cod_mone%TYPE;
   pscodigoSociedad   seg_socie.cod_soci%TYPE;
   lsClaCtal1         VARCHAR2(50);
   lsClaCtal2         VARCHAR2(50);
  lsClaDocu           VARCHAR2(10);
  lsIndTImp           VARCHAR2(50);
  lsValAsig           VARCHAR2(50);
  lnImpu              NUMBER;
  lnPend              NUMBER;
  lnFolio             NUMBER;
  lsCodNeg            VARCHAR2(10);
  lsCodMarca          VARCHAR2(10);
  lsViaPago           VARCHAR2(10);
  lsFolio16Dig        VARCHAR2(20);
  lsFolio3Dig         VARCHAR2(10);
  lsFolio19Dig        VARCHAR2(20):=null;


CURSOR C_EJE_SECCI ( vsCodigoPrograma VARCHAR2,
                     pscodigoMoneda seg_moned.cod_mone%TYPE,
                     pscodigoSociedad seg_socie.cod_soci%TYPE,
                     lnImpu NUMBER,
                     lsClaCtal1 VARCHAR2,
                     lsClaCtal2 VARCHAR2,
                     lsViaPago VARCHAR2,
                     lsIndTImp VARCHAR2,
                     lsCodMarca VARCHAR2,
                     lsCodNeg VARCHAR2,
                     lnFolio NUMBER                   
                   ) is
SELECT to_char(eval.fec_proc_pago,'DD.MM.YYYY') C001,
       lsClaDocu C002,
       pscodigoSociedad  C003,
       to_char(eval.fec_proc_pago,'DD.MM.YYYY') C004,
       SUBSTR(to_char(eval.fec_proc_pago,'DD.MM.YYYY'),4,2) C005, -- Mes
       pscodigoMoneda C006,
       CASE pscodigopais
         when 'DOL' THEN ''
         ELSE  TO_CHAR(eval.num_secu) END C007, -- SI PAIS = DOL SE COLOCA FOLIO , SINO LA REFERENCIA DEL DOCUMENTO
     
 
       SUBSTR(eval.tex_pago,1,length(eval.tex_pago)) || ' C'  || SUBSTR(eval.cam_refe,5,2) || '-' || SUBSTR(eval.cam_refe,1,4) C008,
       lsClaCtal1 C009,  --  CLAVE CUENTA 1
       eval.cod_sap_cons C010,
       NULL C011,
       NULL C012,
       NULL C013,
       NULL C014,
       NULL C015,
       'X' C016,  -- IBD CALCULO DE IMPUESTO
       NULL C017,
       NULL C018,
       TO_CHAR(ROUND(eval.val_mont_comi * lnImpu,0)) C019,
       NULL C020,
       NULL C021,
       NULL C022,
       NULL C023,
       NULL C024,
       NULL C025,
       NULL C026,
       NULL C027,
       NULL C028,
       NULL C029,
       NULL C030,
       NULL C031,
       NULL C032,
       --lsValAsig C032,
       TRIM(SUBSTR(eval.tex_pago,1,length(eval.tex_pago)) || ' C' || SUBSTR(eval.cam_refe,5,2) || '-' || SUBSTR(eval.cam_refe,1,4)) C033,
       NULL C034,
       eval.num_secu C035,
       NULL C036,
       NULL C037,
       NULL C038,
       lsViaPago C039,
       NULL C040,
       NULL C041,
       NULL C042,
       NULL C043,
       NULL C044,
       NULL C045,
       NULL C046,
       NULL C047,
       NULL C048,
       NULL C049,
       NULL C050,
       NULL C051,
       NULL C052,
       NULL C053,
       NULL C054,
       NULL C055,
       NULL C056,
       NULL C057,
       NULL C058,
       NULL C201,
       NULL C202,
       NULL C203,
       NULL C204,
       NULL C205,
       NULL C206,
       NULL C207,
       NULL C208,
       lsClaCtal2 C209,
       eval.num_cuen  C210, -- para LEC-COM
       NULL C211,
       NULL C212,
       NULL C213,
       NULL C214,
       NULL C215,
       NULL C216,
       lsIndTImp C217,
       NULL C218,
       TO_CHAR(ROUND(eval.val_mont_comi * lnImpu,0)) C219,
       NULL C220,
       NULL C221,
       NULL C222,
       eval.cen_cost C223,
       NULL C224,
       lsCodNeg C225,
       lsCodMarca C226,
       'C-' || SUBSTR(eval.cam_refe,5,2) C227,
       SUBSTR(eval.cam_refe,1,4) C228,
       NULL C229,
       NULL C230,
       NULL C231,
       NULL C232,
       TRIM(SUBSTR(eval.tex_pago,1,length(eval.tex_pago)) || ' C' || SUBSTR(eval.cam_refe,5,2) || '-' || SUBSTR(eval.cam_refe,1,4)) C233,
       NULL C234,
        eval.num_secu C235,
       NULL C236,
       NULL C237,
       NULL C238,
       lsViaPago C239,
       NULL C240,
       NULL C241,
       NULL C242,
       NULL C243,
       NULL C244,
       NULL C245,
       NULL C246,
       NULL C247,
       NULL C248,
       NULL C249,
       NULL C250,
       NULL C251,
       NULL C252,
       NULL C253,
       NULL C254,
       NULL C255,
       NULL C256,
       NULL C257,
       'F'  C258
  FROM (
        SELECT temp.cod_lide clie_cod_clie, temp.val_mont_brut val_mont_comi, temp.cod_sap_cons , temp.num_secu as num_secu, temp.fec_proc_pago,
        temp.tex_pago , temp.num_cuen , temp.cen_cost, temp.cam_refe
          FROM lec_lider_pago_comis temp
         WHERE 1=1
           AND temp.cam_proc = psCodigoPeriodo
           AND ((pstipoenvio = 0  AND temp.ind_proc_pago IN ( '1' ,'4'))
            OR (pstipoenvio = 1  AND temp.ind_proc_pago IN ( '2' ,'4')))
      ORDER BY temp.num_secu
       ) eval ;


TYPE EjeSecciRecTab IS TABLE OF c_eje_secci%ROWTYPE INDEX BY BINARY_INTEGER;
EjeSecciRecord EjeSecciRecTab;

-- Variables de control
lnFilas    NUMBER(12):= 5000;
ln_sqlcode NUMBER(10);
ls_sqlerrm varchar2(1500);


-- Variables de trabajo
lnOidPeriodo CRA_PERIO.OID_PERI%TYPE;

lsCodigoPrograma  EJE_PROGR.COD_PROG_EJEC%TYPE;
lsCodigoFase      eje_fases_progr.cod_fase%TYPE;
lnOk NUMBER(1):=1;

    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         NUMBER := 1000;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(2000);
    lslinea2         VARCHAR2(2000);
    lsnombrearchivo VARCHAR2(50);
    lbabrirutlfile  BOOLEAN;

BEGIN
    /* Se obtiene los Oids de la campaña actual y campaña anterior */

    BEGIN
      SELECT perd.oid_peri
        INTO lnOidPeriodo
        FROM cra_perio perd,
             seg_perio_corpo peri
       WHERE perd.peri_oid_peri = peri.oid_peri
         AND peri.cod_peri = psCodigoPeriodo;
    EXCEPTION WHEN OTHERS THEN
       lnOidPeriodo := 0;
       lnOk := 0;
    END;

  BEGIN
         SELECT   mone.cod_mone,soci.cod_soci
         INTO pscodigoMoneda,pscodigoSociedad
          FROM int_param_pais para,
               gen_pais_activ pact,
               seg_pais pais,
               seg_socie soci,
               seg_moned mone
        WHERE para.pais_oid_pais = pact.pais_oid_pais
           AND para.pais_oid_pais = pais.oid_pais
           AND para.soci_oid_soci = soci.oid_soci
           AND pais.mone_oid_mone = mone.oid_mone ;
            EXCEPTION WHEN OTHERS THEN
               lnOk := 0;
            END;

    BEGIN
      SELECT substr(bpi.val_pain,1,length(bpi.val_pain))
        INTO lsClaCtal1
        FROM bas_param_inter bpi
       WHERE bpi.sist_cod_sist =  pscodigosistema
         AND bpi.inte_cod_inte =  pscodigointerfaz
         AND bpi.nom_pain      =  'ClaCta1'  ;
    EXCEPTION WHEN OTHERS THEN
       lsClaCtal1 := '';
     --  lnOk := 0;
    END;

     BEGIN
      SELECT substr(bpi.val_pain,1,length(bpi.val_pain))
        INTO lsClaCtal2
        FROM bas_param_inter bpi
       WHERE bpi.sist_cod_sist =  pscodigosistema
         AND bpi.inte_cod_inte =  pscodigointerfaz
         AND bpi.nom_pain      =  'ClaCta2'     ;
    EXCEPTION WHEN OTHERS THEN
       lsClaCtal2 := '';
    --   lnOk := 0;
    END;


    BEGIN
      SELECT TO_NUMBER(bpi.val_pain,'9999.999')
        INTO lnImpu
        FROM bas_param_inter bpi
       WHERE bpi.sist_cod_sist =  pscodigosistema
         AND bpi.inte_cod_inte =  pscodigointerfaz
         AND bpi.nom_pain      =  'ValImpu' ;
    EXCEPTION WHEN OTHERS THEN
       lnImpu := 0;
      -- lnOk := 0;
    END;

    BEGIN
      SELECT  TO_NUMBER( SUBSTR(bpi.val_pain,2,LENGTH(bpi.val_pain)-1))
        INTO lnFolio
        FROM bas_param_inter bpi
       WHERE bpi.sist_cod_sist =  pscodigosistema
         AND bpi.inte_cod_inte =  pscodigointerfaz
         AND bpi.nom_pain      =  'nroFolio' ;
    EXCEPTION WHEN OTHERS THEN
       lnFolio := 0;
    END;

  BEGIN
      SELECT substr(bpi.val_pain,1,length(bpi.val_pain))
        INTO lsClaDocu
        FROM bas_param_inter bpi
       WHERE bpi.sist_cod_sist =  pscodigosistema
         AND bpi.inte_cod_inte =  pscodigointerfaz
         AND bpi.nom_pain      =  'ClaDocu'  ;
    EXCEPTION WHEN OTHERS THEN
       lsClaCtal1 := '';
     --  lnOk := 0;
    END;

  BEGIN
      SELECT substr(bpi.val_pain,1,length(bpi.val_pain))
        INTO lsIndTImp
        FROM bas_param_inter bpi
       WHERE bpi.sist_cod_sist =  pscodigosistema
         AND bpi.inte_cod_inte =  pscodigointerfaz
         AND bpi.nom_pain      =  'IndTImp'  ;
    EXCEPTION WHEN OTHERS THEN
       lsIndTImp:= '';
      -- lnOk := 0;
    END;

    BEGIN
      SELECT substr(bpi.val_pain,1,length(bpi.val_pain))
        INTO lsValAsig
        FROM bas_param_inter bpi
       WHERE bpi.sist_cod_sist =  pscodigosistema
         AND bpi.inte_cod_inte =  pscodigointerfaz
         AND bpi.nom_pain      =  'ValAsig'  ;
    EXCEPTION WHEN OTHERS THEN
       lsValAsig := '';
      -- lnOk := 0;
    END;

    BEGIN
      SELECT substr(bpi.val_pain,1,length(bpi.val_pain))
        INTO lsCodNeg
        FROM bas_param_inter bpi
       WHERE bpi.sist_cod_sist =  pscodigosistema
         AND bpi.inte_cod_inte =  pscodigointerfaz
         AND bpi.nom_pain      =  'CodNeg'  ;
    EXCEPTION WHEN OTHERS THEN
       lsCodNeg := '';
     --  lnOk := 0;
    END;

   BEGIN
      SELECT substr(bpi.val_pain,1,length(bpi.val_pain))
        INTO lsCodMarca
        FROM bas_param_inter bpi
       WHERE bpi.sist_cod_sist =  pscodigosistema
         AND bpi.inte_cod_inte =  pscodigointerfaz
         AND bpi.nom_pain      =  'CodMarca'  ;
    EXCEPTION WHEN OTHERS THEN
       lsCodMarca := '';
    --   lnOk := 0;
    END;
    
   BEGIN
      SELECT substr(bpi.val_pain,1,length(bpi.val_pain))
        INTO lsViaPago
        FROM bas_param_inter bpi
       WHERE bpi.sist_cod_sist =  pscodigosistema
         AND bpi.inte_cod_inte =  pscodigointerfaz
         AND bpi.nom_pain      =  'ViaPago'  ;
    EXCEPTION WHEN OTHERS THEN
       lsViaPago := '';
      -- lnOk := 0;
    END;

    /* Se obtiene el código del programa y fase activas en la campaña  */

 /*   BEGIN
       SELECT prog.cod_prog_ejec,
              (
                SELECT fapr.cod_fase
                  FROM eje_fases_progr fapr
                 WHERE fapr.pais_cod_pais = psCodigoPais
                   AND fapr.cod_prog_ejec = prog.cod_prog_ejec
                   AND psCodigoPeriodo BETWEEN fapr.cod_camp_inic AND fapr.cod_camp_fina
              ) cod_fase
         INTO lsCodigoPrograma,
              lsCodigoFase
          FROM  eje_progr prog
         WHERE prog.ind_acti = 1;
    EXCEPTION WHEN OTHERS THEN
       lnOk := 0;
    END;*/

 --   IF lnOk = 1 THEN

    lbabrirutlfile := TRUE;
        /* Se leen todas las ejecutivas activas al cierre de región o e campaña */
        OPEN C_EJE_SECCI( lsCodigoPrograma , pscodigoMoneda,pscodigoSociedad ,
                             lnImpu , lsClaCtal1 ,lsClaCtal2 ,
                         lsViaPago ,
                     lsIndTImp ,
                     lsCodMarca ,
                     lsCodNeg ,
                     lnFolio  );

      LOOP
            FETCH C_EJE_SECCI BULK COLLECT INTO EjeSecciRecord LIMIT lnFilas;
      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
      
            IF EjeSecciRecord.COUNT > 0 THEN
               FOR a IN EjeSecciRecord.FIRST .. EjeSecciRecord.LAST LOOP

                  IF lnFolio =0 THEN
                     EjeSecciRecord(a).C007 :=  EjeSecciRecord(a).C035;
                     lsFolio3Dig:='';
                     lsFolio16Dig :=  EjeSecciRecord(a).C007 ;
                     
                   ELSE
                    lnFolio:= lnFolio + 1;
                        
                    EjeSecciRecord(a).C007 := 'A' ||  LPAD(TO_CHAR(lnFolio),18,'0');
                    SELECT SUBSTR( EjeSecciRecord(a).C007,17,3) || ' ' INTO lsFolio3Dig FROM DUAL;
                    SELECT SUBSTR( EjeSecciRecord(a).C007,1,16) INTO lsFolio16Dig FROM DUAL;
                    
                    lsFolio19Dig := EjeSecciRecord(a).C007;
                  END IF ;

                   /* Crea Primera linea de archivo */
                  lslinea :=  EjeSecciRecord(a).C001 || ';' ||
                                EjeSecciRecord(a).C002 || ';' ||
                                EjeSecciRecord(a).C003 || ';' ||
                                EjeSecciRecord(a).C004 || ';' ||
                                EjeSecciRecord(a).C005 || ';' ||
                                EjeSecciRecord(a).C006 || ';' ||
                                lsFolio16Dig || ';' ||  
                                lsFolio3Dig  ||EjeSecciRecord(a).C008 || ';' ||
                                EjeSecciRecord(a).C009 || ';' ||
                                EjeSecciRecord(a).C010 || ';' ||
                                EjeSecciRecord(a).C011 || ';' ||
                                EjeSecciRecord(a).C012 || ';' ||
                                EjeSecciRecord(a).C013 || ';' ||
                                EjeSecciRecord(a).C014 || ';' ||
                                EjeSecciRecord(a).C015 || ';' ||
                                EjeSecciRecord(a).C016 || ';' ||
                                EjeSecciRecord(a).C017 || ';' ||
                                EjeSecciRecord(a).C018 || ';' ||
                                EjeSecciRecord(a).C019 || ';' ||
                                EjeSecciRecord(a).C020 || ';' ||
                                EjeSecciRecord(a).C021 || ';' ||
                                EjeSecciRecord(a).C022 || ';' ||
                                EjeSecciRecord(a).C023 || ';' ||
                                EjeSecciRecord(a).C024 || ';' ||
                                EjeSecciRecord(a).C025 || ';' ||
                                EjeSecciRecord(a).C026 || ';' ||
                                EjeSecciRecord(a).C027 || ';' ||
                                EjeSecciRecord(a).C028 || ';' ||
                                EjeSecciRecord(a).C029 || ';' ||
                                EjeSecciRecord(a).C030 || ';' ||
                                EjeSecciRecord(a).C031 || ';' ||
                                EjeSecciRecord(a).C032 || ';' ||
                                EjeSecciRecord(a).C033 || ';' ||
                                EjeSecciRecord(a).C034 || ';' ||
                                EjeSecciRecord(a).C035 || ';' ||
                                EjeSecciRecord(a).C036 || ';' ||
                                EjeSecciRecord(a).C037 || ';' ||
                                EjeSecciRecord(a).C038 || ';' ||
                                EjeSecciRecord(a).C039 || ';' ||
                                EjeSecciRecord(a).C040 || ';' ||
                                EjeSecciRecord(a).C041 || ';' ||
                                EjeSecciRecord(a).C042 || ';' ||
                                EjeSecciRecord(a).C043 || ';' ||
                                EjeSecciRecord(a).C044 || ';' ||
                                EjeSecciRecord(a).C045 || ';' ||
                                EjeSecciRecord(a).C046 || ';' ||
                                EjeSecciRecord(a).C047 || ';' ||
                                EjeSecciRecord(a).C048 || ';' ||
                                EjeSecciRecord(a).C049 || ';' ||
                                EjeSecciRecord(a).C050 || ';' ||
                                EjeSecciRecord(a).C051 || ';' ||
                                EjeSecciRecord(a).C052 || ';' ||
                                EjeSecciRecord(a).C053 || ';' ||
                                EjeSecciRecord(a).C054 || ';' ||
                                EjeSecciRecord(a).C055 || ';' ||
                                EjeSecciRecord(a).C056 || ';' ||
                                EjeSecciRecord(a).C057 || ';' ||
                                EjeSecciRecord(a).C058 ;
          utl_file.put_line(v_handle,lslinea);
                   /* Crea Segunda linea de archivo */

                  lslinea2 :=   EjeSecciRecord(a).C201 || ';' ||
                                EjeSecciRecord(a).C202 || ';' ||
                                EjeSecciRecord(a).C203 || ';' ||
                                EjeSecciRecord(a).C204 || ';' ||
                                EjeSecciRecord(a).C205 || ';' ||
                                EjeSecciRecord(a).C206 || ';' ||
                                EjeSecciRecord(a).C207 || ';' ||
                                EjeSecciRecord(a).C208 || ';' ||
                                EjeSecciRecord(a).C209 || ';' ||
                                EjeSecciRecord(a).C210 || ';' ||
                                EjeSecciRecord(a).C211 || ';' ||
                                EjeSecciRecord(a).C212 || ';' ||
                                EjeSecciRecord(a).C213 || ';' ||
                                EjeSecciRecord(a).C214 || ';' ||
                                EjeSecciRecord(a).C215 || ';' ||
                                EjeSecciRecord(a).C216 || ';' ||
                                EjeSecciRecord(a).C217 || ';' ||
                                EjeSecciRecord(a).C218 || ';' ||
                                EjeSecciRecord(a).C219 || ';' ||
                                EjeSecciRecord(a).C220 || ';' ||
                                EjeSecciRecord(a).C221 || ';' ||
                                EjeSecciRecord(a).C222 || ';' ||
                                EjeSecciRecord(a).C223 || ';' ||
                                EjeSecciRecord(a).C224 || ';' ||
                                EjeSecciRecord(a).C225 || ';' ||
                                EjeSecciRecord(a).C226 || ';' ||
                                EjeSecciRecord(a).C227 || ';' ||
                                EjeSecciRecord(a).C228 || ';' ||
                                EjeSecciRecord(a).C229 || ';' ||
                                EjeSecciRecord(a).C230 || ';' ||
                                EjeSecciRecord(a).C231 || ';' ||
                                EjeSecciRecord(a).C232 || ';' ||
                                EjeSecciRecord(a).C233 || ';' ||
                                EjeSecciRecord(a).C234 || ';' ||
                                EjeSecciRecord(a).C235 || ';' ||
                                EjeSecciRecord(a).C236 || ';' ||
                                EjeSecciRecord(a).C237 || ';' ||
                                EjeSecciRecord(a).C238 || ';' ||
                                EjeSecciRecord(a).C239 || ';' ||
                                EjeSecciRecord(a).C240 || ';' ||
                                EjeSecciRecord(a).C241 || ';' ||
                                EjeSecciRecord(a).C242 || ';' ||
                                EjeSecciRecord(a).C243 || ';' ||
                                EjeSecciRecord(a).C244 || ';' ||
                                EjeSecciRecord(a).C245 || ';' ||
                                EjeSecciRecord(a).C246 || ';' ||
                                EjeSecciRecord(a).C247 || ';' ||
                                EjeSecciRecord(a).C248 || ';' ||
                                EjeSecciRecord(a).C249 || ';' ||
                                EjeSecciRecord(a).C250 || ';' ||
                                EjeSecciRecord(a).C251 || ';' ||
                                EjeSecciRecord(a).C252 || ';' ||
                                EjeSecciRecord(a).C253 || ';' ||
                                EjeSecciRecord(a).C254 || ';' ||
                                EjeSecciRecord(a).C255 || ';' ||
                                EjeSecciRecord(a).C256 || ';' ||
                                EjeSecciRecord(a).C257 || ';' ||
                                EjeSecciRecord(a).C258 ;
                                utl_file.put_line(v_handle,lslinea2);

                     UPDATE LEC_LIDER_PAGO_COMIS llpc
                        SET llpc.IND_PROC_PAGO = '2',
                            llpc.val_nume_folio = CASE lnFolio
                                                  WHEN 0 THEN NULL
                                                  ELSE lsFolio19Dig END,
                            llpc.fec_modi = sysdate,
                            llpc.usu_modi = 'OPERADOR LET'                      
                      WHERE llpc.cam_proc = psCodigoPeriodo
                   --     AND cod_sap_cons = EjeSecciRecord(a).C010
                        AND llpc.num_secu = EjeSecciRecord(a).C035;

             END LOOP;
           END IF;
            EXIT WHEN C_EJE_SECCI%NOTFOUND;
    END LOOP;
        CLOSE C_EJE_SECCI;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;

    --Actualizamos Nro Folio

    IF lnFolio >0 AND lsFolio19Dig is not null THEN
    UPDATE bas_param_inter bpi
       SET bpi.val_pain = lsFolio19Dig
       WHERE bpi.sist_cod_sist =  pscodigosistema
         AND bpi.inte_cod_inte =  pscodigointerfaz
         AND bpi.nom_pain      =  'nroFolio' ;
    END IF;

    RETURN;

EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_SAF_PAGOS_LIDER: '||ls_sqlerrm);
  END INT_PR_SAF_PAGOS_LIDER;

  /***************************************************************************
  Descripcion       : Generar interfaz de pagos para SAP-FI
  Fecha Creacion    : 13/06/2014
  Autor             : Juan Gutierrez
  ***************************************************************************/

  PROCEDURE int_pr_saf_envio_socia_empre(
     pscodigopais     VARCHAR,
     pscodigoperiodo  VARCHAR,
     psfechafact      VARCHAR,
     pscodigosistema  VARCHAR,
     pscodigointerfaz VARCHAR,
     psnombrearchivo  VARCHAR,
     psUsuario        VARCHAR
   ) IS


   CURSOR c_interfaz(vnidperiodo NUMBER) IS

   SELECT  '1' CO001,
        'XK01' CO002,
        '/' CO003,
        --'CO01' CO004,
        '/' CO005,
        '15' CO006,
        'Señora' CO007,
        DECODE(clie.val_ape1,NULL,'',clie.val_ape1) || DECODE(clie.val_ape2,NULL,'',' ') ||
        DECODE(clie.val_ape2,NULL,'',clie.val_ape2) || DECODE(clie.val_nom1,NULL,'',' ') ||
        DECODE(clie.val_nom1,NULL,'',clie.val_nom1) || DECODE(clie.val_nom2,NULL,'',' ') ||
        DECODE(clie.val_nom2,NULL,'',clie.val_nom2) CO008,
        '/' CO009,
        DECODE(clie.val_nom1,NULL,'',clie.val_nom1) || DECODE(clie.val_nom2,NULL,'',',') ||
        DECODE(clie.val_nom2,NULL,'',clie.val_nom2) CO010,
        DECODE(clie.val_ape1,NULL,'',clie.val_ape1) || DECODE(clie.val_ape2,NULL,'',',') ||
        DECODE(clie.val_ape2,NULL,'',clie.val_ape2) CO011,
        DECODE(clie.val_ape1,NULL,'',clie.val_ape1) || DECODE(clie.val_nom1,NULL,'',',') ||
        DECODE(clie.val_nom1,NULL,'',clie.val_nom1) CO012,
        substr( TRIM( dire.des_abrv_tipo_via || ' ' || dire.val_nomb_via || ' ' || dire.num_ppal || ' ' || dire.val_obse || ' ' || dire.val_barr ),1,35) CO013,
        '/' CO014,
        dire.nivel_2 CO015,
        '/' CO016,
        dire.nivel_1 CO017,
        '/' CO018,
        substr(pscodigopais,1,2)  CO019,
        (
         SELECT regi.cod_regi
           FROM LEC_REGIO_TRIBU regi
          WHERE SUBSTR(regi.des_regi,1,6) = SUBSTR(dire.nivel_1,1,6)
        ) CO020,
        'ES' CO021,
        (
         SELECT val_text_comu
           FROM mae_clien_comun com
          WHERE com.clie_oid_clie = clie.oid_clie
            AND ticm_oid_tipo_comu = 6
        ) CO022,
        '/' CO023,
        '/' CO024,
        '/' CO025,
        '/' CO026,
        '/' CO027,
        '/' CO028,
        temp.docFiscal  CO029, -- temp.num_docu_iden || gen_pkg_gener.gen_fn_devue_digit_verif_NIT(TO_NUMBER(temp.num_docu_iden))  CO029,
        '/' CO030,
        temp.num_docu_iden CO031,
        'X' CO032,
        '/' CO033,
        '/' CO034,
        '/' CO035,
        '/' CO036,
        '/' CO037,
        '/' CO038,
        '/' CO039,
        '/' CO040,
        '/' CO041,
        '/' CO042,
       -- '' CO043,
        --'' CO044,
        '/' CO045,
        '/' CO046,
        '/' CO047,
       -- '230101032' CO048,
        '/' CO049,
        '/' CO050,
        'EGR19' CO051,
        '/' CO052,
        '/' CO053,
        '/' CO054,
        '0001' CO055,  --CONDICION DE PAGO
        '/' CO056,
        '/' CO057,
        'X' CO058,
        --'0' CO059,
        '/' CO060,
        '/' CO061,
        --'CRCO1' CO062,
        '/' CO063,
        '/' CO064,
        '/' CO065,
        '/' CO066,
        '/' CO067,
        '/' CO068,
        '/' CO069,
        --'00001' CO070 , -- NOTA INTERIOR,
        '/' CO071,
        --  '480' CO072, 
        '/' CO073,
        '/' CO074,
        --'J5' CO075,
        --'J5' CO076,
        --'X' CO077,
        '/' CO078,
        '/' CO079,
        '/' CO080,
        '/' CO081,
        '/' CO082,
      --  '85' CO083,
      --  '85' CO084,
      --  'X' CO085,
        '/' CO086,
        '/' CO087,
        '/' CO088,
        '/' CO089,
        '/' CO090,
        dire.cod_nivel_2,
/*        CASE
           WHEN dire.nivel_2 IN ('BOGOTA','BOGOTÁ') THEN '41'
           WHEN dire.nivel_2 ='CALI' THEN '56'
           WHEN dire.nivel_2 IN ('TOCANCIPA','TOCANCIPÁ') THEN '91'
        END */
        --'' CO091,

/*        CASE
           WHEN dire.nivel_2 IN ('BOGOTA','BOGOTÁ') THEN '41'
           WHEN dire.nivel_2 = 'CALI' THEN '56'
           WHEN dire.nivel_2 IN ('TOCANCIPA','TOCANCIPÁ') THEN '91'
        END */
       -- '  ' CO092,
/*        CASE
           WHEN dire.nivel_2 IN ('BOGOTA','BOGOTÁ') THEN 'X'
           WHEN dire.nivel_2 = 'CALI' THEN 'X'
           WHEN dire.nivel_2 IN ('TOCANCIPA','TOCANCIPÁ') THEN 'X'
        END */
       -- ' '  CO093,
        '/' CO094,
        '/' CO095,
        '/' CO096,
        '/' CO097,
        '/' CO098,
        '/' CO099,
        '/' CO100,
        '/' CO101,
        '/' CO102,
        '/' CO103,
        '/' CO104,
        '/' CO105,
        '/' CO106,
        '/' CO107,
        '/' CO108,
        '/' CO109,
        '/' CO110,
        '/' CO111,
        '/' CO112,
        '/' CO113,
        '/' CO114,
        '/' CO115,
        '/' CO116,
        '/' CO117,
        '/' CO118,
        '/' CO119,
        '/' CO120,
        '/' CO121,
        '/' CO122,
        '/' CO123,
        '/' CO124,
        '/' CO125,
        '/' CO126,
        '/' CO127,
        '/' CO128,
        '/' CO129,
        
        substr(pscodigopais,1,2)  CO130,  -- CLAVE PAIS DE BANCO
       -- '9999' CO131,
       DECODE (pscodigopais , 'COE' ,'9999', temp.bbpa_cod_banc_paga ) CO131,  -- COD BANCO
       -- '9999' CO132,
       DECODE (pscodigopais , 'COE' ,'9999',temp.num_cuen_banc)   CO132,       -- NUM CUENTA
       -- '01' CO133,
       DECODE (pscodigopais , 'COE' ,'01',temp.btcu_cod_tipo_cuen) CO133,      -- TIPO CUENTA
       -- 'COP' CO134,
        '/' CO135,
        '/' CO136,
        DECODE(clie.val_ape1,NULL,'',clie.val_ape1) || DECODE(clie.val_ape2,NULL,'',' ') ||
        DECODE(clie.val_ape2,NULL,'',clie.val_ape2) || DECODE(clie.val_nom1,NULL,'',' ') ||
        DECODE(clie.val_nom1,NULL,'',clie.val_nom1) || DECODE(clie.val_nom2,NULL,'',' ') ||
        DECODE(clie.val_nom2,NULL,'',clie.val_nom2) CO137,
        '/' CO138,
        '/' CO139,
        '/' CO140,
        '/' CO141,
        '/' CO142,
        '/' CO143,
        '/' CO144,
        '/' CO145,
        '/' CO146,
        '/' CO147,
        '/' CO148,
        '/' CO149,
        '/' CO150,
        '/' CO151,
        '/' CO152,
        '/' CO153,
        '/' CO154,
        '/' CO155,
        '/' CO156,
        '/' CO157,
        '/' CO158,
        '/' CO159,
        'Estandar' CO160,
        'AV-Ejecutivas' CO161,
        '0005' CO162,
        '/' CO163,
        '/' CO164,
        '/' CO165
  FROM mae_clien clie,
       (
        select clie.cod_clie clie_cod_clie, clid.num_docu_iden, clif.num_docu_iden as docFiscal,
               macd.bbpa_cod_banc_paga , macd.btcu_cod_tipo_cuen ,macd.num_cuen_banc
          from zon_histo_geren gere,
               mae_clien_datos_adici macd,
               mae_clien clie,
               mae_clien_ident clid,
               mae_clien_ident clif,
               cra_perio perd,
               seg_perio_corpo peri,
               cra_perio perd2,
               seg_perio_corpo peri2
         where 1=1
           and clie.oid_clie = macd.clie_oid_clie(+)
           and gere.gere = clie.cod_clie
           and clie.oid_clie = clid.clie_oid_clie
           and gere.perd_oid_peri_desd = perd.oid_peri
           and perd.peri_oid_peri = peri.oid_peri
           and gere.perd_oid_peri_desd = vnidperiodo
           and nvl(gere.perd_oid_peri_hast,vnidperiodo) = perd2.oid_peri
           and perd2.peri_oid_peri = peri2.oid_peri
           and clid.val_iden_docu_prin = 1
           and clif.clie_oid_clie = clie.oid_clie
           and clif.tdoc_oid_tipo_docu in (SELECT mtd.oid_tipo_docu
                                            FROM BAS_PARAM_INTER bpi,
                                                 MAE_TIPO_DOCUM mtd
                                           WHERE bpi.sist_cod_sist = pscodigosistema
                                             AND bpi.inte_cod_inte = pscodigointerfaz
                                             AND bpi.val_pain = mtd.cod_tipo_docu
                                             AND UPPER(bpi.nom_pain) = 'CODTDOCFIS')

           and macd.cod_prov IS NULL
           and gere.cod_regi is not null
           and gere.cod_zona is not null
           and gere.cod_secc is not null
           and pscodigoperiodo between peri.cod_peri and nvl(peri2.cod_peri,pscodigoperiodo)
       ) temp,
       (
         SELECT a.clie_oid_clie,
                c.des_abrv_tipo_via,
                a.val_nomb_via,
                a.num_ppal,
                a.val_obse,
                a.val_barr,
                a.cod_unid_geog,
                a.des_villa_pobl,
               (SELECT des_geog
                  FROM ZON_VALOR_ESTRU_GEOPO
                 WHERE pais_oid_pais = d.pais_oid_pais
                   AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
                   AND orde_2 IS NULL) AS NIVEL_1,
               (SELECT des_geog
                  FROM ZON_VALOR_ESTRU_GEOPO
                 WHERE pais_oid_pais = d.pais_oid_pais
                   AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
                   AND orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
                   AND orde_3 IS NULL) AS NIVEL_2,
               (SELECT cod_unid_geog
                  FROM ZON_VALOR_ESTRU_GEOPO
                 WHERE pais_oid_pais = d.pais_oid_pais
                   AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
                   AND orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
                   AND orde_3 IS NULL) AS COD_NIVEL_2,
               (SELECT des_geog
                  FROM ZON_VALOR_ESTRU_GEOPO
                 WHERE pais_oid_pais = d.pais_oid_pais
                   AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
                   AND orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
                   AND orde_3 = SUBSTR (a.cod_unid_geog, 13, 6)
                   AND orde_4 IS NULL) AS NIVEL_3
          FROM mae_clien_direc a,
               mae_tipo_direc b,
               seg_tipo_via c,
               mae_clien d,
               zon_terri t
         WHERE d.oid_clie = a.clie_oid_clie
           AND a.ind_elim = 0
           AND b.oid_tipo_dire = a.tidc_oid_tipo_dire
           AND c.oid_tipo_via = a.tivi_oid_tipo_via
           AND a.ind_dire_ppal  = 1
           AND a.terr_oid_terr = t.oid_terr (+)
       ) dire
WHERE 1=1
  AND temp.clie_cod_clie = clie.cod_clie
  AND clie.oid_clie = dire.clie_oid_clie(+);

   TYPE interfazrectab IS TABLE OF c_interfaz%ROWTYPE INDEX BY BINARY_INTEGER;
   interfazrecord interfazrectab;

   t_array dbms_sql.varchar2_table;
   t_tipret dbms_sql.varchar2_table;
   t_catret dbms_sql.varchar2_table;

   /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         NUMBER := 1000;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(5000);

    lsnombrearchivo VARCHAR2(50);
    lbabrirutlfile  BOOLEAN;
    vnidperiodo    number;
    lsIndTipoRet    VARCHAR2(50);
    lsCatRet        VARCHAR2(50);
    lsIndNivRet     VARCHAR2(50);
    lsDesDistritoAnt  VARCHAR2(50);
    lsDesDistritoNue  VARCHAR2(50);
    lsDesPoblacion    VARCHAR2(50);
    lsTDocFis       VARCHAR2(50);

    lnTotRet        NUMBER:=0;
    lsNivel2        VARCHAR2(10);
    lsTRet       VARCHAR2(6);
    lsCRet        VARCHAR2(6);
    lsCO091         varchar(2):='';
    lsCO092         varchar(2):='';
    lsCO093         varchar(2):='';
    lsCO004         varchar(6):='';
    lsCO043         varchar(2):='';
    lsCO044         varchar(2):='';
    lsCO048         varchar(10):='';
    lsCO062         varchar(6):='';
    lsCO072         varchar(6):='';
    lsCO075         varchar(6):='';
    lsCO076         varchar(6):='';
    lsCO077         varchar(6):='';
    lsCO083         varchar(6):='';
    lsCO084         varchar(6):='';
    lsCO085         varchar(6):='';
    lsCO134         varchar(6):='';
    lsCO059         varchar(6):='';
    lsCO070         varchar(10):='';

   BEGIN

    /* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;

    SELECT cra.oid_peri INTO vnidperiodo FROM cra_perio cra, seg_perio_corpo seg
     WHERE seg.oid_peri = cra.peri_oid_peri
       AND seg.cod_peri = pscodigoperiodo;

    -- Sociedad P04
    BEGIN
       SELECT bpi.val_pain INTO lsCO004 FROM BAS_PARAM_INTER bpi WHERE bpi.sist_cod_sist = pscodigosistema AND bpi.inte_cod_inte = pscodigointerfaz AND bpi.nom_pain = 'codSoc';
    EXCEPTION WHEN no_data_found THEN lsCO004 := '';
    END;
    
    -- Tipo Fiscal P43
    BEGIN
       SELECT bpi.val_pain INTO lsCO043 FROM BAS_PARAM_INTER bpi WHERE bpi.sist_cod_sist = pscodigosistema AND bpi.inte_cod_inte = pscodigointerfaz AND bpi.nom_pain = 'tipFiscal';
    EXCEPTION WHEN no_data_found THEN lsCO043 := '';
    END;
    
    -- Tipo Identificacion P44
    BEGIN
       SELECT bpi.val_pain INTO lsCO044 FROM BAS_PARAM_INTER bpi WHERE bpi.sist_cod_sist = pscodigosistema AND bpi.inte_cod_inte = pscodigointerfaz AND bpi.nom_pain = 'tipIden';
    EXCEPTION WHEN no_data_found THEN lsCO044 := ''; 
    END;
    
    -- Cuenta Asociada P48
    BEGIN
       SELECT bpi.val_pain INTO lsCO048 FROM BAS_PARAM_INTER bpi WHERE bpi.sist_cod_sist = pscodigosistema AND bpi.inte_cod_inte = pscodigointerfaz AND bpi.nom_pain = 'ctaAsoc';
    EXCEPTION WHEN no_data_found THEN lsCO048 := ''; 
    END;
    
       -- Via de Pago P59
    BEGIN
       SELECT bpi.val_pain INTO lsCO059 FROM BAS_PARAM_INTER bpi WHERE bpi.sist_cod_sist = pscodigosistema AND bpi.inte_cod_inte = pscodigointerfaz AND bpi.nom_pain = 'viaPago';
    EXCEPTION WHEN no_data_found THEN lsCO059 := ''; 
    END;
        
    -- Banco Propio P62
    BEGIN
       SELECT bpi.val_pain INTO lsCO062 FROM BAS_PARAM_INTER bpi WHERE bpi.sist_cod_sist = pscodigosistema AND bpi.inte_cod_inte = pscodigointerfaz AND bpi.nom_pain = 'bcoPropio';
    EXCEPTION WHEN no_data_found THEN lsCO062 := ''; 
    END;
    
    -- País de retención P72
    BEGIN
       SELECT bpi.val_pain INTO lsCO072 FROM BAS_PARAM_INTER bpi WHERE bpi.sist_cod_sist = pscodigosistema AND bpi.inte_cod_inte = pscodigointerfaz AND bpi.nom_pain = 'paisReten';
    EXCEPTION WHEN no_data_found THEN lsCO072 := ''; 
    END;
    
        -- Nota Interior P70
    BEGIN
       SELECT bpi.val_pain INTO lsCO070 FROM BAS_PARAM_INTER bpi WHERE bpi.sist_cod_sist = pscodigosistema AND bpi.inte_cod_inte = pscodigointerfaz AND bpi.nom_pain = 'notaInte';
    EXCEPTION WHEN no_data_found THEN lsCO070 := ''; 
    END;
    
    -- Ind. Tipo Retencion P75
    BEGIN
       SELECT bpi.val_pain INTO lsCO075 FROM BAS_PARAM_INTER bpi WHERE bpi.sist_cod_sist = pscodigosistema AND bpi.inte_cod_inte = pscodigointerfaz AND bpi.nom_pain = 'tipoReten';
    EXCEPTION WHEN no_data_found THEN lsCO075 := ''; 
    END;
    
    -- Ind. Retencion P76
    BEGIN
       SELECT bpi.val_pain INTO lsCO076 FROM BAS_PARAM_INTER bpi WHERE bpi.sist_cod_sist = pscodigosistema AND bpi.inte_cod_inte = pscodigointerfaz AND bpi.nom_pain = 'indReten';
    EXCEPTION WHEN no_data_found THEN lsCO076 := ''; 
    END;
    
    -- Ind. Sujeto Retencion P77
    BEGIN
       SELECT bpi.val_pain INTO lsCO077 FROM BAS_PARAM_INTER bpi WHERE bpi.sist_cod_sist = pscodigosistema AND bpi.inte_cod_inte = pscodigointerfaz AND bpi.nom_pain = 'sujReten';
    EXCEPTION WHEN no_data_found THEN lsCO077 := ''; 
    END;
    
    -- Ind. Tipo Retencion 2 P83
    BEGIN
       SELECT bpi.val_pain INTO lsCO083 FROM BAS_PARAM_INTER bpi WHERE bpi.sist_cod_sist = pscodigosistema AND bpi.inte_cod_inte = pscodigointerfaz AND bpi.nom_pain = 'tipoReten2';
    EXCEPTION WHEN no_data_found THEN lsCO083 := '';
    END;
    
    -- Ind. Categoria Retencion  P84
    BEGIN
       SELECT bpi.val_pain INTO lsCO084 FROM BAS_PARAM_INTER bpi WHERE bpi.sist_cod_sist = pscodigosistema AND bpi.inte_cod_inte = pscodigointerfaz AND bpi.nom_pain = 'catReten2';
    EXCEPTION WHEN no_data_found THEN lsCO084 := '';
    END;
    
    -- Ind. Sujeto Retencion 2 P85
    BEGIN
       SELECT bpi.val_pain INTO lsCO085 FROM BAS_PARAM_INTER bpi WHERE bpi.sist_cod_sist = pscodigosistema AND bpi.inte_cod_inte = pscodigointerfaz AND bpi.nom_pain = 'sujReten2';
    EXCEPTION WHEN no_data_found THEN lsCO085 := '';
    END;
    
    -- Tipo Bco Interlocutor P134
    BEGIN
       SELECT bpi.val_pain INTO lsCO134 FROM BAS_PARAM_INTER bpi WHERE bpi.sist_cod_sist = pscodigosistema AND bpi.inte_cod_inte = pscodigointerfaz AND bpi.nom_pain = 'tipoBcoInt';
    EXCEPTION WHEN no_data_found THEN lsCO134 := '';
    END;
    
    BEGIN
      SELECT bpi.val_pain
        INTO lsIndNivRet
        FROM BAS_PARAM_INTER bpi
       WHERE bpi.sist_cod_sist = pscodigosistema
         AND bpi.inte_cod_inte = pscodigointerfaz
         AND bpi.nom_pain = 'NivRetencion';
    EXCEPTION
      WHEN no_data_found THEN
        lsIndNivRet := NULL;
    END;

    BEGIN
    SELECT bpi.val_pain
      INTO lsIndTipoRet
      FROM BAS_PARAM_INTER bpi
     WHERE bpi.sist_cod_sist = pscodigosistema
       AND bpi.inte_cod_inte = pscodigointerfaz
       AND bpi.nom_pain = 'indTipoRetencion';
    EXCEPTION
      WHEN no_data_found THEN
        lsIndTipoRet := NULL;
    END;

    BEGIN
    SELECT bpi.val_pain
      INTO lsCatRet
      FROM BAS_PARAM_INTER bpi
     WHERE bpi.sist_cod_sist = pscodigosistema
       AND bpi.inte_cod_inte = pscodigointerfaz
       AND bpi.nom_pain = 'catRetenciones';
    EXCEPTION
      WHEN no_data_found THEN
        lsCatRet := NULL;
    END;

    BEGIN
    SELECT bpi.val_pain
      INTO lsDesDistritoAnt
      FROM BAS_PARAM_INTER bpi
     WHERE bpi.sist_cod_sist = pscodigosistema
       AND bpi.inte_cod_inte = pscodigointerfaz
       AND bpi.nom_pain = 'desDistritoAnt';
    EXCEPTION
      WHEN no_data_found THEN
        lsDesDistritoAnt := NULL;
    END;

    IF lsDesDistritoAnt IS NOT NULL THEN
    SELECT bpi.val_pain
      INTO lsDesDistritoNue
      FROM BAS_PARAM_INTER bpi
     WHERE bpi.sist_cod_sist = pscodigosistema
       AND bpi.inte_cod_inte = pscodigointerfaz
       AND bpi.nom_pain = 'desDistritoNue';

    SELECT bpi.val_pain
      INTO lsDesPoblacion
      FROM BAS_PARAM_INTER bpi
     WHERE bpi.sist_cod_sist = pscodigosistema
       AND bpi.inte_cod_inte = pscodigointerfaz
       AND bpi.nom_pain = 'desPoblacion';
    ELSE
      lsDesDistritoNue:=NULL;
      lsDesPoblacion:=NULL;
    END IF;


    WHILE ( INSTR(lsIndNivRet,',',1,1) > 0 OR lsIndNivRet IS NOT NULL )
    LOOP
           lnTotRet := lnTotRet +1;

           IF INSTR(lsIndNivRet,',',1,1) > 0 THEN
             SELECT SUBSTR(lsIndNivRet,1,INSTR(lsIndNivRet,',',1,1)-1)
             INTO   lsNivel2
             FROM   DUAL;
           ELSE
             SELECT SUBSTR(lsIndNivRet,1 ,LENGTH(lsIndNivRet))
             INTO  lsNivel2
             FROM  DUAL;
           END IF;

           t_array(lnTotRet):=lsNivel2;

           SELECT SUBSTR(lsIndTipoRet,1,2)
           INTO   lsTRet
           FROM   DUAL;

           t_tipret(lnTotRet):= lsTRet;

           SELECT SUBSTR(lsCatRet,1,2)
           INTO   lsCRet
           FROM   DUAL;

           t_catret(lnTotRet):= lsCRet;

           IF INSTR(lsIndNivRet,',',1,1) > 0 THEN
             SELECT SUBSTR(lsIndNivRet,INSTR(lsIndNivRet,',',1,1) + 1 ,LENGTH(lsIndNivRet)-INSTR(lsIndNivRet,',',1,1))
             INTO lsIndNivRet
             FROM DUAL;
           ELSE
             lsIndNivRet:='';
           END IF;

           SELECT SUBSTR(lsIndTipoRet,4,LENGTH(lsIndTipoRet)-3)
           INTO lsIndTipoRet
           FROM DUAL;

           SELECT SUBSTR(lsCatRet,4,LENGTH(lsCatRet)-3)
           INTO lsCatRet
           FROM DUAL;

    END LOOP;

     OPEN c_interfaz(vnidperiodo);
      LOOP
        FETCH c_interfaz BULK COLLECT
          INTO interfazrecord LIMIT w_filas;
        /* Procedimiento inicial para generar interfaz */
        IF lbabrirutlfile THEN
          gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle);
          lbabrirutlfile := FALSE;
        END IF;

        IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP

        IF lsIndNivRet is not null then
            FOR a IN t_array.first .. t_array.last
              LOOP
                IF interfazrecord(x).COD_NIVEL_2 = t_array(a) THEN
                  lsCO091 := t_tipret(a);
                  lsCO092 := t_catret(a);
                  lsCO093 := 'X';
                END IF;
              END LOOP  ;
        END IF;

        IF (lsDesDistritoAnt IS NOT NULL AND interfazrecord(x).CO015 = lsDesPoblacion
           AND interfazrecord(x).CO017 = lsDesDistritoAnt) THEN
               interfazrecord(x).CO017 := lsDesDistritoNue;
        END IF;


          lslinea := interfazrecord(x).CO001			   || ';' ||
                     interfazrecord(x).CO002         || ';' ||
                     interfazrecord(x).CO003         || ';' ||
                     lsCO004                         || ';' ||
                     interfazrecord(x).CO005         || ';' ||
                     interfazrecord(x).CO006         || ';' ||
                     interfazrecord(x).CO007         || ';' ||
                     interfazrecord(x).CO008         || ';' ||
                     interfazrecord(x).CO009         || ';' ||
                     interfazrecord(x).CO010         || ';' ||
                     interfazrecord(x).CO011         || ';' ||
                     interfazrecord(x).CO012         || ';' ||
                     interfazrecord(x).CO013         || ';' ||
                     interfazrecord(x).CO014         || ';' ||
                     interfazrecord(x).CO015         || ';' ||
                     interfazrecord(x).CO016         || ';' ||
                     interfazrecord(x).CO017         || ';' ||
                     interfazrecord(x).CO018         || ';' ||
                     interfazrecord(x).CO019         || ';' ||
                     interfazrecord(x).CO020         || ';' ||
                     interfazrecord(x).CO021         || ';' ||
                     interfazrecord(x).CO022         || ';' ||
                     interfazrecord(x).CO023         || ';' ||
                     interfazrecord(x).CO024         || ';' ||
                     interfazrecord(x).CO025         || ';' ||
                     interfazrecord(x).CO026         || ';' ||
                     interfazrecord(x).CO027         || ';' ||
                     interfazrecord(x).CO028         || ';' ||
                     interfazrecord(x).CO029         || ';' ||
                     interfazrecord(x).CO030         || ';' ||
                     interfazrecord(x).CO031         || ';' ||
                     interfazrecord(x).CO032         || ';' ||
                     interfazrecord(x).CO033         || ';' ||
                     interfazrecord(x).CO034         || ';' ||
                     interfazrecord(x).CO035         || ';' ||
                     interfazrecord(x).CO036         || ';' ||
                     interfazrecord(x).CO037         || ';' ||
                     interfazrecord(x).CO038         || ';' ||
                     interfazrecord(x).CO039         || ';' ||
                     interfazrecord(x).CO040         || ';' ||
                     interfazrecord(x).CO041         || ';' ||
                     interfazrecord(x).CO042         || ';' ||
                   --  interfazrecord(x).CO043         || ';' ||
                     lsCO043                         || ';' ||
                   --  interfazrecord(x).CO044         || ';' ||
                     lsCO044                         || ';' ||
                     interfazrecord(x).CO045         || ';' ||
                     interfazrecord(x).CO046         || ';' ||
                     interfazrecord(x).CO047         || ';' ||
                    -- interfazrecord(x).CO048         || ';' ||
                     lsCO048                         || ';' ||
                     interfazrecord(x).CO049         || ';' ||
                     interfazrecord(x).CO050         || ';' ||
                     interfazrecord(x).CO051         || ';' ||
                     interfazrecord(x).CO052         || ';' ||
                     interfazrecord(x).CO053         || ';' ||
                     interfazrecord(x).CO054         || ';' ||
                     interfazrecord(x).CO055         || ';' ||
                     interfazrecord(x).CO056         || ';' ||
                     interfazrecord(x).CO057         || ';' ||
                     interfazrecord(x).CO058         || ';' ||
                     lsCO059                         || ';' ||
                    -- interfazrecord(x).CO059         || ';' ||
                     interfazrecord(x).CO060         || ';' ||
                     interfazrecord(x).CO061         || ';' ||
                   --  interfazrecord(x).CO062         || ';' ||
                     lsCO062                         || ';' ||
                     interfazrecord(x).CO063         || ';' ||
                     interfazrecord(x).CO064         || ';' ||
                     interfazrecord(x).CO065         || ';' ||
                     interfazrecord(x).CO066         || ';' ||
                     interfazrecord(x).CO067         || ';' ||
                     interfazrecord(x).CO068         || ';' ||
                     interfazrecord(x).CO069         || ';' ||
                     lsCO070                         || ';' ||
                     interfazrecord(x).CO071         || ';' ||
                 --    interfazrecord(x).CO072         || ';' ||
                     lsCO072                         || ';' ||
                     interfazrecord(x).CO073         || ';' ||
                     interfazrecord(x).CO074         || ';' ||
                    -- interfazrecord(x).CO075         || ';' ||
                     lsCO075                         || ';' ||
                    -- interfazrecord(x).CO076         || ';' ||
                     lsCO076                         || ';' ||
                    --  interfazrecord(x).CO077         || ';' ||
                     lsCO077                         || ';' ||
                     interfazrecord(x).CO078         || ';' ||
                     interfazrecord(x).CO079         || ';' ||
                     interfazrecord(x).CO080         || ';' ||
                     interfazrecord(x).CO081         || ';' ||
                     interfazrecord(x).CO082         || ';' ||
                     --interfazrecord(x).CO083         || ';' ||
                     lsCO083                         || ';' ||
                    -- interfazrecord(x).CO084         || ';' ||
                     lsCO084                          || ';' ||
                     --interfazrecord(x).CO085         || ';' ||
                     lsCO085                         || ';' ||
                     interfazrecord(x).CO086         || ';' ||
                     interfazrecord(x).CO087         || ';' ||
                     interfazrecord(x).CO088         || ';' ||
                     interfazrecord(x).CO089         || ';' ||
                     interfazrecord(x).CO090         || ';' ||
                     lsCO091                         || ';' ||
                     lsCO092                         || ';' ||
                     lsCO093                         || ';' ||
                     interfazrecord(x).CO094         || ';' ||
                     interfazrecord(x).CO095         || ';' ||
                     interfazrecord(x).CO096         || ';' ||
                     interfazrecord(x).CO097         || ';' ||
                     interfazrecord(x).CO098         || ';' ||
                     interfazrecord(x).CO099         || ';' ||
                     interfazrecord(x).CO100         || ';' ||
                     interfazrecord(x).CO101         || ';' ||
                     interfazrecord(x).CO102         || ';' ||
                     interfazrecord(x).CO103         || ';' ||
                     interfazrecord(x).CO104         || ';' ||
                     interfazrecord(x).CO105         || ';' ||
                     interfazrecord(x).CO106         || ';' ||
                     interfazrecord(x).CO107         || ';' ||
                     interfazrecord(x).CO108         || ';' ||
                     interfazrecord(x).CO109         || ';' ||
                     interfazrecord(x).CO110         || ';' ||
                     interfazrecord(x).CO111         || ';' ||
                     interfazrecord(x).CO112         || ';' ||
                     interfazrecord(x).CO113         || ';' ||
                     interfazrecord(x).CO114         || ';' ||
                     interfazrecord(x).CO115         || ';' ||
                     interfazrecord(x).CO116         || ';' ||
                     interfazrecord(x).CO117         || ';' ||
                     interfazrecord(x).CO118         || ';' ||
                     interfazrecord(x).CO119         || ';' ||
                     interfazrecord(x).CO120         || ';' ||
                     interfazrecord(x).CO121         || ';' ||
                    interfazrecord(x).CO122          || ';' ||
                    interfazrecord(x).CO123          || ';' ||
                    interfazrecord(x).CO124          || ';' ||
                    interfazrecord(x).CO125          || ';' ||
                    interfazrecord(x).CO126          || ';' ||
                    interfazrecord(x).CO127          || ';' ||
                    interfazrecord(x).CO128          || ';' ||
                    interfazrecord(x).CO129          || ';' ||
                    interfazrecord(x).CO130          || ';' ||
                    interfazrecord(x).CO131          || ';' ||
                    interfazrecord(x).CO132          || ';' ||
                    interfazrecord(x).CO133          || ';' ||
                    lsCO134                          || ';' ||
                    interfazrecord(x).CO135          || ';' ||
                    interfazrecord(x).CO136          || ';' ||
                    interfazrecord(x).CO137          || ';' ||
                    interfazrecord(x).CO138          || ';' ||
                    interfazrecord(x).CO139          || ';' ||
                    interfazrecord(x).CO140          || ';' ||
                    interfazrecord(x).CO141          || ';' ||
                    interfazrecord(x).CO142          || ';' ||
                    interfazrecord(x).CO143          || ';' ||
                    interfazrecord(x).CO144          || ';' ||
                    interfazrecord(x).CO145          || ';' ||
                    interfazrecord(x).CO146          || ';' ||
                    interfazrecord(x).CO147          || ';' ||
                    interfazrecord(x).CO148          || ';' ||
                    interfazrecord(x).CO149          || ';' ||
                    interfazrecord(x).CO150          || ';' ||
                    interfazrecord(x).CO151          || ';' ||
                    interfazrecord(x).CO152          || ';' ||
                    interfazrecord(x).CO153          || ';' ||
                    interfazrecord(x).CO154          || ';' ||
                    interfazrecord(x).CO155          || ';' ||
                    interfazrecord(x).CO156          || ';' ||
                    interfazrecord(x).CO157          || ';' ||
                    interfazrecord(x).CO158          || ';' ||
                    interfazrecord(x).CO159          || ';' ||
                    interfazrecord(x).CO160          || ';' ||
                    interfazrecord(x).CO161          || ';' ||
                    interfazrecord(x).CO162          || ';' ||
                    interfazrecord(x).CO163          || ';' ||
                    interfazrecord(x).CO164          || ';' ||
                    interfazrecord(x).CO165 ;

          utl_file.put_line(v_handle,lslinea);

          END LOOP;
        END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;


 EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 1000);
    raise_application_error(-20123, 'ERROR INT_PR_SAF_ENVIO_SOCIA_EMPRE: ' || ls_sqlerrm);

END int_pr_saf_envio_socia_empre;

  /***************************************************************************
  Descripcion       : Llena las tablas para SAPFI
  Fecha Creacion    : 23/05/2013
  Autor             : Jorge Yépez
  ***************************************************************************/
  PROCEDURE int_pr_carga_saf_corpo2
  (
    pscodigopais     VARCHAR2,
    psperiodo        VARCHAR2,
    psfecha          VARCHAR2,
    psnumlote        VARCHAR2
  ) IS

    CURSOR c_config IS
    select tip_movi,
           des_movi,
           ind_debe_habe,
           cod_impo,
           ind_orig,
           tipo_asie,
           cod_asie
    from int_sapfi_conf
        ;

    r_config c_config%ROWTYPE;


    CURSOR c_ajuste IS
    select distinct cod_peri,
           cod_asie
    from INT_SAF_RESUL
        ;

    r_ajuste c_ajuste%ROWTYPE;

    ln_diferencia NUMBER(12,2):=0;

    ls_ajusteSFPeru VARCHAR2(100):=IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','ajusteSFPeru');

  BEGIN

update fac_docum_conta_linea
set IMP_IMPU_TOTA_PROD_NACI=0--num_unid_aten*(select VAL_IMPU_PROD_NACI from INT_IMPUE_PRODU_NACIO where prod_oid_prod=fac_docum_conta_linea.prod_oid_prod)
where oid in
(
select b.oid
 from fac_docum_conta_cabec a, fac_docum_conta_linea b
where a.oid_cabe=b.dcca_oid_cabe
and a.perd_oid_peri=gen_pkg_gener.gen_fn_devuelve_id_cra_perio(psperiodo,2003,2001) and a.fec_fact=TO_DATE(psfecha, 'DD/MM/YYYY')
and b.val_prec_sin_impu_unit-(nvl(b.imp_impu_tota_prod_naci,0)/b.num_unid_aten)<0
and a.tido_oid_tipo_docu=1
and exists (select 1 from INT_IMPUE_PRODU_NACIO where prod_oid_prod=b.prod_oid_prod)
);


if ls_ajusteSFPeru='S' then
        update fac_docum_conta_cabec
         set val_tota_paga_loca=val_tota_paga_loca-IMP_FLET_TOTA_LOCA
         , imp_impu_tota_loca=imp_impu_tota_loca-(IMP_FLET_TOTA_LOCA-IMP_FLET_IMPU_TOTA_LOCA)
         where tido_oid_tipo_docu=29 and soca_oid_soli_cabe in
         (
 select oid_soli_cabe from ped_solic_cabec where fec_fact=TO_DATE(psfecha, 'DD/MM/YYYY')
and exists (select 1 from fac_docum_conta_cabec where soca_oid_soli_cabe=oid_soli_cabe and tido_oid_tipo_docu=1 and IMP_FLET_TOTA_LOCA>0)
and exists (select 1 from fac_docum_conta_cabec where soca_oid_soli_cabe=oid_soli_cabe and tido_oid_tipo_docu=29 and IMP_FLET_TOTA_LOCA>0)
         );

         update fac_docum_conta_cabec set IMP_FLET_TOTA_LOCA=0
         , IMP_FLET_IMPU_TOTA_LOCA=0
         where soca_oid_soli_cabe in
         (
 select oid_soli_cabe from ped_solic_cabec where fec_fact=TO_DATE(psfecha, 'DD/MM/YYYY')
and exists (select 1 from fac_docum_conta_cabec where soca_oid_soli_cabe=oid_soli_cabe and tido_oid_tipo_docu=1 and IMP_FLET_TOTA_LOCA>0)
and exists (select 1 from fac_docum_conta_cabec where soca_oid_soli_cabe=oid_soli_cabe and tido_oid_tipo_docu=29 and IMP_FLET_TOTA_LOCA>0)
         );

end if;

delete from int_sapfi_cabec;

delete from int_sapfi_detal;

delete from INT_SAF_RESUL;

insert into int_sapfi_cabec
select
psnumlote NUM_LOTE
, g.cod_pais COD_PAIS
,to_char(a.fec_fact,'dd/mm/yyyy') FEC_PROC
, f.cod_soci COD_SOCI
, case when e.cod_tipo_soli in ('C52','C53') then 'NM'  when e.cod_tipo_soli='C77' then 'FF' when e.ind_anul=1 then 'AN' when e.ind_soli_nega=1 then 'AB' else 'VN' end COD_ASIE
, i.cod_peri COD_PERI
, c.val_tasa_impu VAL_TASA
, case when e.cod_tipo_soli in ('C52','C53') then 'NMP' when e.cod_tipo_soli='C77' then 'FALTANTE' when e.ind_anul=1 then 'ANULACIONES' when e.ind_soli_nega=1 then 'ABONOS' else 'VENTA' end  VAL_GLOS
, to_char(a.fec_fact,'yyyy') VAL_EJER
, sum(case when a.ICTP_OID_TIPO_PROG is not null then 0 else a.val_tota_paga_loca end) VAL_TOTA_PAGA_LOCA
, sum(case when a.ICTP_OID_TIPO_PROG is not null then 0 else a.imp_redo_loca end)   IMP_REDO_LOCA
, sum(case when a.ICTP_OID_TIPO_PROG is not null then 0 else a.imp_impu_tota_loca end)  IMP_IMPU_TOTA_LOCA
, sum(case when a.ICTP_OID_TIPO_PROG is not null then 0 else nvl(a.VAL_TOTA_GAST_ADMI_SIN_IMPU,0) end) VAL_TOTA_GAST_ADMI_SIN_IMPU
, sum(case when a.ICTP_OID_TIPO_PROG is not null then 0 else nvl(a.val_impo_rete_desc,0) end) VAL_IMPO_RETE_DESC
, sum(case when a.ICTP_OID_TIPO_PROG is not null then 0 else nvl(a.IMP_IVA_IMPU_TOTA_PROD_NACI,0) end) IMP_IVA_IMPU_TOTA_PROD_NACI
, sum(case when a.ICTP_OID_TIPO_PROG is not null then 0 else a.IMP_FLET_IMPU_TOTA_LOCA end) VAL_IMP_FLET_IMPU_TOTA_LOCA
, sum(case when a.ICTP_OID_TIPO_PROG is not null then 0 else nvl(A.VAL_IMPO_IMPU_CREE,0) end) VAL_IMPO_IMPU_CREE
, sum(case when a.ICTP_OID_TIPO_PROG is not null then 0 else nvl(A.VAL_IMPO_IVA_ASUM_EMPR,0) end) VAL_IMPO_IVA_ASUM_EMPR
, sum(case when a.ICTP_OID_TIPO_PROG is not null then 0 else nvl(A.Imp_Des3_Total_Loca,0) end) VAL_IMPO_DESC_TOTA
, c.almc_oid_alma
, NULL
from fac_docum_conta_cabec a, ped_solic_cabec c, ped_tipo_solic_pais d, ped_tipo_solic e, seg_socie f, seg_pais g, cra_perio h, seg_perio_corpo i
where a.fec_fact>=to_date(psfecha,'dd/mm/yyyy')-3
and a.fec_fact<=to_date(psfecha,'dd/mm/yyyy')
and a.soca_oid_soli_cabe=c.oid_soli_cabe
and c.tspa_oid_tipo_soli_pais=d.oid_tipo_soli_pais and d.tsol_oid_tipo_soli=e.oid_tipo_soli
and c.pais_oid_pais=g.oid_pais and c.soci_oid_soci=f.oid_soci
and c.perd_oid_peri=h.oid_peri and h.peri_oid_peri=i.oid_peri
and a.tido_oid_tipo_docu not in (8)
and a.num_lote_cont is null
--and a.ICTP_OID_TIPO_PROG is null
group by
psnumlote
, g.cod_pais
, to_char(a.fec_fact,'dd/mm/yyyy')
, f.cod_soci
, case when e.cod_tipo_soli in ('C52','C53') then 'NM'  when e.cod_tipo_soli='C77' then 'FF' when e.ind_anul=1 then 'AN' when e.ind_soli_nega=1 then 'AB' else 'VN' end
, i.cod_peri
, c.val_tasa_impu
, case when e.cod_tipo_soli in ('C52','C53') then 'NMP' when e.cod_tipo_soli='C77' then 'FALTANTE' when e.ind_anul=1 then 'ANULACIONES' when e.ind_soli_nega=1 then 'ABONOS' else 'VENTA' end
, to_char(a.fec_fact,'yyyy')
, c.almc_oid_alma
;

insert into int_sapfi_detal
select
psnumlote NUM_LOTE
, g.cod_pais COD_PAIS
,to_char(a.fec_fact,'dd/mm/yyyy') FEC_PROC
, f.cod_soci COD_SOCI
, case when e.cod_tipo_soli in ('C52','C53') then 'NM' when e.cod_tipo_soli='C77' then 'FF' when e.ind_anul=1 then 'AN' when e.ind_soli_nega=1 then 'AB' else 'VN' end COD_ASIE
, i.cod_peri COD_PERI
, c.val_tasa_impu VAL_TASA
, case when e.cod_tipo_soli in ('C52','C53') then 'NMP' when e.cod_tipo_soli='C77' then 'FALTANTE' when e.ind_anul=1 then 'ANULACIONES' when e.ind_soli_nega=1 then 'ABONOS' else 'VENTA' end  VAL_GLOS
, to_char(a.fec_fact,'yyyy') VAL_EJER
, sum(case when a.ICTP_OID_TIPO_PROG is not null then 0 else j.val_prec_sin_impu_tota_loca end) VAL_PREC_SIN_IMPU_TOTA_LOCA
, sum(case when a.ICTP_OID_TIPO_PROG is not null then 0 else j.imp_desc_sin_impu_tota_loca end)  IMP_DESC_SIN_IMPU_TOTA_LOCA
, sum(case when a.ICTP_OID_TIPO_PROG is not null then 0 else decode(j.val_prec_cont_tota_loca,0,0,j.val_prec_sin_impu_tota_loca) end) VAL_PREC_CONT_TOTA_LOCA
, o.cod_tipo_ofer COD_TIPO_OFER
, n.cod_marc_prod COD_MARC_PRODU
, m.val_grup_arti VAL_GRUP_ARTI
, sum(case when a.ICTP_OID_TIPO_PROG is not null then 0 else nvl(decode(j.val_prec_cata_unit_loca,0,0,J.IMP_IMPU_TOTA_PROD_NACI),0) end)  IMP_IMPU_TOTA_PROD_NACI
, sum(case when a.ICTP_OID_TIPO_PROG in (2001,2002) then ((decode(j.val_prec_cont_tota_loca,0,m.VAL_COST_ESTD*j.num_unid_aten,j.val_prec_cont_tota_loca)*c.val_tasa_impu)/100) else 0 end) VAL_IMP_GRAT_TOTA_LOCA
, sum(case when a.ICTP_OID_TIPO_PROG is not null then 0 else nvl(decode(j.val_prec_cata_unit_loca,0,J.IMP_IMPU_TOTA_PROD_NACI,0),0) end)  IMP_IMPU_TOTA_PROD_NACI_GRAT
, c.almc_oid_alma
from fac_docum_conta_cabec a, ped_solic_cabec c, ped_tipo_solic_pais d, ped_tipo_solic e, seg_socie f, seg_pais g, cra_perio h, seg_perio_corpo i
, fac_docum_conta_linea j, ped_solic_posic k, pre_ofert_detal l, mae_produ m, seg_marca_produ n, pre_tipo_ofert o
where a.fec_fact>=to_date(psfecha,'dd/mm/yyyy')-3
and a.fec_fact<=to_date(psfecha,'dd/mm/yyyy')
and a.soca_oid_soli_cabe=c.oid_soli_cabe
and c.tspa_oid_tipo_soli_pais=d.oid_tipo_soli_pais and d.tsol_oid_tipo_soli=e.oid_tipo_soli
and c.pais_oid_pais=g.oid_pais and c.soci_oid_soci=f.oid_soci
and c.perd_oid_peri=h.oid_peri and h.peri_oid_peri=i.oid_peri
and a.tido_oid_tipo_docu not in (8)
and a.num_lote_cont is null
--and a.ICTP_OID_TIPO_PROG is null
and a.oid_cabe=j.dcca_oid_cabe and j.sopo_oid_soli_posi=k.oid_soli_posi and k.ofde_oid_deta_ofer=l.oid_deta_ofer(+)
and l.tofe_oid_tipo_ofer=o.oid_tipo_ofer(+)
and j.prod_oid_prod=m.oid_prod and m.mapr_oid_marc_prod=n.oid_marc_prod
group by
psnumlote
, g.cod_pais
, to_char(a.fec_fact,'dd/mm/yyyy')
, f.cod_soci
, case when e.cod_tipo_soli in ('C52','C53') then 'NM' when e.cod_tipo_soli='C77' then 'FF' when e.ind_anul=1 then 'AN' when e.ind_soli_nega=1 then 'AB' else 'VN' end
, i.cod_peri
, c.val_tasa_impu
, o.cod_tipo_ofer
, n.cod_marc_prod
, m.val_grup_arti
, case when e.cod_tipo_soli in ('C52','C53') then 'NMP' when e.cod_tipo_soli='C77' then 'FALTANTE' when e.ind_anul=1 then 'ANULACIONES' when e.ind_soli_nega=1 then 'ABONOS' else 'VENTA' end
, to_char(a.fec_fact,'yyyy')
, c.almc_oid_alma
;

          OPEN c_config;
          LOOP
              FETCH c_config INTO r_config;
              EXIT WHEN c_config%NOTFOUND;

              if r_config.ind_orig='C' then

                 insert into INT_SAF_RESUL
                  select
                  num_lote
                  , cod_pais
                  , to_date(fec_proc,'dd/mm/yyyy')
                  , to_date(fec_proc,'dd/mm/yyyy')
                  , cod_soci
                  , cod_cent
                  , cod_asie
                  , r_config.tip_movi
                  , ' '
                  , ' '
                  , ' '
                  , '1'
                  , case when r_config.tipo_asie='1' then
                         case when case
                                    when r_config.cod_impo='01' then val_tota_paga_loca
                                    when r_config.cod_impo='02' then imp_redo_loca
                                    when r_config.cod_impo='04' then VAL_TOTA_GAST_ADMI_SIN_IMPU
                                    when r_config.cod_impo='03' then VAL_IMP_FLET_IMPU_TOTA_LOCA
                                    when r_config.cod_impo='05' then IMP_IMPU_TOTA_LOCA
                                    when r_config.cod_impo='09' then VAL_IMPO_RETE_DESC
                                    when r_config.cod_impo='14' then VAL_IMPO_DESC_TOTA
                               end >=0
                               then r_config.ind_debe_habe else decode(r_config.ind_debe_habe,'D','H','D')
                          end
                    else
                         case when case
                                    when r_config.cod_impo='01' then val_tota_paga_loca
                                    when r_config.cod_impo='02' then imp_redo_loca
                                    when r_config.cod_impo='04' then VAL_TOTA_GAST_ADMI_SIN_IMPU
                                    when r_config.cod_impo='03' then VAL_IMP_FLET_IMPU_TOTA_LOCA
                                    when r_config.cod_impo='05' then IMP_IMPU_TOTA_LOCA
                                    when r_config.cod_impo='09' then VAL_IMPO_RETE_DESC
                                    when r_config.cod_impo='14' then VAL_IMPO_DESC_TOTA
                                    end <=0
                               then r_config.ind_debe_habe else decode(r_config.ind_debe_habe,'D','H','D')
                          end
                    end
                  , val_glos
                  , cod_peri
                  , val_ejer
                  , 2001
                  , abs(
                  case
                    when r_config.cod_impo='01' then val_tota_paga_loca
                    when r_config.cod_impo='02' then imp_redo_loca
                    when r_config.cod_impo='04' then VAL_TOTA_GAST_ADMI_SIN_IMPU
                    when r_config.cod_impo='03' then VAL_IMP_FLET_IMPU_TOTA_LOCA
                    when r_config.cod_impo='05' then IMP_IMPU_TOTA_LOCA
                    when r_config.cod_impo='09' then VAL_IMPO_RETE_DESC
                  end
                  )
                   from int_sapfi_cabec x where --x.fec_proc=psfecha and
                   x.cod_asie=r_config.cod_asie;

              else
                 insert into INT_SAF_RESUL
                  select
                  num_lote
                  , cod_pais
                  , to_date(fec_proc,'dd/mm/yyyy')
                  , to_date(fec_proc,'dd/mm/yyyy')
                  , cod_soci
                  , cod_cent
                  , cod_asie
                  , r_config.tip_movi
                  , x.cod_tipo_ofert
                  , cod_marc_produ
                  , val_grup_arti
                  , '1'
                  , case when r_config.tipo_asie='1' then
                         case when case
                                      when r_config.cod_impo='08' then VAL_PREC_CONT_TOTA_LOCA
                                      when r_config.cod_impo='07' then IMP_DESC_SIN_IMPU_TOTA_LOCA
                                      when r_config.cod_impo='06' then VAL_PREC_SIN_IMPU_TOTA_LOCA
                                      when r_config.cod_impo='10' then IMP_IMPU_TOTA_PROD_NACI
                                      when r_config.cod_impo='11' then IMP_IMPU_TOTA_PROD_NACI_GRAT
                                      when r_config.cod_impo='13' then IMP_IMPU_TOTA_PROD_NACI_GRAT*0.12
                                      when r_config.cod_impo='12' then VAL_IMP_GRAT_TOTA_LOCA
                                    end >=0
                               then r_config.ind_debe_habe else decode(r_config.ind_debe_habe,'D','H','D')
                          end
                    else
                         case when case
                                      when r_config.cod_impo='08' then VAL_PREC_CONT_TOTA_LOCA
                                      when r_config.cod_impo='07' then IMP_DESC_SIN_IMPU_TOTA_LOCA
                                      when r_config.cod_impo='06' then VAL_PREC_SIN_IMPU_TOTA_LOCA
                                      when r_config.cod_impo='10' then IMP_IMPU_TOTA_PROD_NACI
                                      when r_config.cod_impo='11' then IMP_IMPU_TOTA_PROD_NACI_GRAT
                                      when r_config.cod_impo='13' then IMP_IMPU_TOTA_PROD_NACI_GRAT*0.12
                                      when r_config.cod_impo='12' then VAL_IMP_GRAT_TOTA_LOCA
                                    end <=0
                               then r_config.ind_debe_habe else decode(r_config.ind_debe_habe,'D','H','D')
                          end
                    end
                  , val_glos
                  , cod_peri
                  , val_ejer
                  , 2001
                  , abs(
                  case
                    when r_config.cod_impo='08' then VAL_PREC_CONT_TOTA_LOCA
                    when r_config.cod_impo='07' then IMP_DESC_SIN_IMPU_TOTA_LOCA
                    when r_config.cod_impo='06' then VAL_PREC_SIN_IMPU_TOTA_LOCA
                    when r_config.cod_impo='10' then IMP_IMPU_TOTA_PROD_NACI
                    when r_config.cod_impo='11' then IMP_IMPU_TOTA_PROD_NACI_GRAT
                    when r_config.cod_impo='13' then IMP_IMPU_TOTA_PROD_NACI_GRAT*0.12
                    when r_config.cod_impo='12' then VAL_IMP_GRAT_TOTA_LOCA
                  end
                  )
                   from int_sapfi_detal x where --x.fec_proc=psfecha and
                   x.cod_asie=r_config.cod_asie;

                end if;





          END LOOP;
          CLOSE c_config;


          update int_saf_resul set ind_debe_habe=decode(ind_debe_habe,'D','H','D')
          where cod_asie not in (select distinct z.cod_asie from int_sapfi_conf z where z.tipo_asie='1');





          OPEN c_ajuste;
          LOOP
              FETCH c_ajuste INTO r_ajuste;
              EXIT WHEN c_ajuste%NOTFOUND;

              select
              (select sum(x.val_mont) from int_saf_resul x
              where x.cod_asie=r_ajuste.cod_asie
              and x.cod_peri=r_ajuste.cod_peri
              and x.ind_debe_habe='D')
              -
              (select sum(x.val_mont) from int_saf_resul x
              where x.cod_asie=r_ajuste.cod_asie
              and x.cod_peri=r_ajuste.cod_peri
              and x.ind_debe_habe='H')
              into ln_diferencia
              from dual;

              if abs(ln_diferencia)>0 and abs(ln_diferencia)<0.6 then
                update int_saf_resul x set x.val_mont=x.val_mont+ln_diferencia
                where x.cod_asie=r_ajuste.cod_asie and x.cod_peri=r_ajuste.cod_peri
                and x.tip_mov='005';
              end if;

          END LOOP;
          CLOSE c_ajuste;


          update fac_docum_conta_cabec a
          set a.num_lote_cont=psnumlote
          where a.fec_fact>=to_date(psfecha,'dd/mm/yyyy')-3
          and a.fec_fact<=to_date(psfecha,'dd/mm/yyyy')
          and a.tido_oid_tipo_docu not in (8)
          and a.num_lote_cont is null
          ;


  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR int_pr_carga_saf_corpo2: ' || ls_sqlerrm);
  END int_pr_carga_saf_corpo2;

  /***************************************************************************
  Descripcion       : Genera Interfaz de NUEVA SAPFI
  Fecha Creacion    : 23/05/2013
  Autor             : Jorge Yépez
  ***************************************************************************/
  PROCEDURE int_pr_saf_corpo2
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscentro         VARCHAR2,
    psperiodo        VARCHAR2,
    psfecha          VARCHAR2
  ) IS

    lsFecProc      bas_Ctrl_Fact.Fec_Proc%TYPE;

    CURSOR c_interfaz IS

select
rpad(NUM_LOTE,20,' ')
|| rpad(COD_PAIS,3,' ')
|| to_char(FECCONT,'yyyymmdd')
|| to_char(FECDOC,'yyyymmdd')
|| COD_SOCI
|| decode(cod_cent,2001,pscentro,2002,pscentro,'VE23')
|| rpad(COD_ASIE,3,' ')
|| rpad(TIP_MOV,3,' ')
|| rpad(nvl(TIP_OFER,' '),4,' ')
|| rpad(nvl(COD_MARC,' '),2,' ')
|| rpad(nvl(VAL_GRUP_ARTI,' '),4,' ')
|| lpad(NRO_COMP, 16, '0')
|| IND_DEBE_HABE
|| rpad(VAL_GLOS || ' ' || to_char(FECCONT,'yyyymmdd'), 50, ' ')
|| rpad(substr(COD_PERI, 5),4,' ')
|| NUM_EJER
|| VAL_TASA
|| lpad(VAL_MONT*100, 16, '0') registro
from INT_SAF_RESUL where --cod_peri=psperiodo and to_char(feccont,'dd/mm/yyyy')=psfecha and
val_mont>0;


    TYPE interfazrec IS RECORD(
      registro          VARCHAR2(500)
      );

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         NUMBER := 1000;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);
    lbabrirutlfile  BOOLEAN;
  BEGIN


    /* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x).registro;
          utl_file.put_line(v_handle,lslinea);

        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR int_pr_saf_corpo: ' || ls_sqlerrm);
  END int_pr_saf_corpo2;

/**********************************************************************************
Descripcion       : Genera Interfaz de Pagos de Concurso de Ventas Consultoras Top
Fecha Creacion    : 20/02/2015
Autor             : Carlos Mori
***********************************************************************************/
PROCEDURE INT_PR_SAF_PAGOS_TOP ( psCodigoPais     VARCHAR2,
                                 psCodigoCampanna VARCHAR2,
                                 pdFechaPago      DATE,
                                 psTipoEnvio VARCHAR2,
                                 psCodigoSistema  VARCHAR2,
                                 psCodigoInterfaz VARCHAR2,
                                 psNombreArchivo  VARCHAR2,
                                 psCodigoUsuario  VARCHAR2 )
IS

CURSOR c_interfaz( vsNumeroLote VARCHAR2 ) IS
  SELECT 1 val_nume_fila,
         CASE WHEN psTipoEnvio = 'N' THEN vsNumeroLote ELSE pgpd.val_nume_lote END numeroLote,
         pgpd.pais_cod_pais codigoPais,
         TO_CHAR( CASE WHEN psTipoEnvio = 'N' THEN pgpd.fec_crea ELSE pgpd.fec_crea END,'YYYYMMDD' ) fechaContable,
         TO_CHAR( CASE WHEN psTipoEnvio = 'N' THEN pgpd.fec_crea ELSE pgpd.fec_crea END,'YYYYMMDD' ) fechaDocumento,
         soci.cod_soci codigoSociedad,
         'CO03' codigoCentroLogistico,
         'PGC' tipoAsiento,
         '030' tipoMovimiento,
         NULL tipoOferta, -- Nulo
         NULL codigoMarca, -- Nulo
         NULL grupoArticulo, -- Nulo
         NULL numeroComprobante, -- Nulo
         'H' debeHaber,
         'BANCO DE CREDITO' descripcion,
         NULL periodoComercial, -- Nulo
         NULL ejercicioComercial, -- Nulo
         NULL tasaImpuesto, -- Nulo
         REPLACE(REPLACE(TRIM(TO_CHAR(SUM(pgpd.val_mont_proc),'00000000000000.00')),'.',''),',','') importePago
    FROM inc_concu_pagos_detal pgpd,
         inc_concu_pagos_param pgpa,
         inc_concu_pagos_tipo_abono pgta,
         seg_pais pais,
         int_param_pais ptpa,
         seg_socie soci
   WHERE pgpd.pais_cod_pais = pgpa.pais_cod_pais
     AND pgpd.pgpa_cod_pago = pgpa.cod_pago
     AND pgpd.pais_cod_pais = pgta.pais_cod_pais
     AND pgpd.pgpa_cod_pago = pgta.cod_tipo_abon
     AND pgpd.pais_cod_pais = pais.cod_pais
     AND pais.oid_pais = ptpa.pais_oid_pais
     AND ptpa.soci_oid_soci = soci.oid_soci
     AND pgpd.ind_esta_pago = CASE WHEN psTipoEnvio = 'N' THEN '1' ELSE '3' END
     AND ( psTipoEnvio = 'N' OR ( psTipoEnvio = 'R' AND TRUNC(pgpd.fec_envi) = TRUNC(pdFechaPago) ))
   GROUP BY pgpd.pais_cod_pais,
            soci.cod_soci,
            TO_CHAR( CASE WHEN psTipoEnvio = 'N' THEN pgpd.fec_crea ELSE pgpd.fec_crea END,'YYYYMMDD' ),
            CASE WHEN psTipoEnvio = 'N' THEN vsNumeroLote ELSE pgpd.val_nume_lote END
   UNION
  SELECT 2 val_nume_fila,
         CASE WHEN psTipoEnvio = 'N' THEN vsNumeroLote ELSE pgpd.val_nume_lote END numeroLote,
         pgpd.pais_cod_pais codigoPais,
         TO_CHAR( CASE WHEN psTipoEnvio = 'N' THEN pgpd.fec_crea ELSE pgpd.fec_crea END,'YYYYMMDD' ) fechaContable,
         TO_CHAR( CASE WHEN psTipoEnvio = 'N' THEN pgpd.fec_crea ELSE pgpd.fec_crea END,'YYYYMMDD' ) fechaDocumento,
         soci.cod_soci codigoSociedad,
         'CO03' codigoCentroLogistico,
         'PGC' tipoAsiento,
         '031' tipoMovimiento,
         NULL tipoOferta, -- Nulo
         NULL codigoMarca, -- Nulo
         NULL grupoArticulo, -- Nulo
         NULL numeroComprobante, -- Nulo
         'D' debeHaber,
         'CONCURSO DE VENTAS' descripcion,
         NULL periodoComercial, -- Nulo
         NULL ejercicioComercial, -- Nulo
         NULL tasaImpuesto, -- Nulo
         REPLACE(REPLACE(TRIM(TO_CHAR(SUM(pgpd.val_mont_proc),'00000000000000.00')),'.',''),',','') importePago
    FROM inc_concu_pagos_detal pgpd,
         inc_concu_pagos_param pgpa,
         inc_concu_pagos_tipo_abono pgta,
         seg_pais pais,
         int_param_pais ptpa,
         seg_socie soci
   WHERE pgpd.pais_cod_pais = pgpa.pais_cod_pais
     AND pgpd.pgpa_cod_pago = pgpa.cod_pago
     AND pgpd.pais_cod_pais = pgta.pais_cod_pais
     AND pgpd.pgpa_cod_pago = pgta.cod_tipo_abon
     AND pgpd.pais_cod_pais = pais.cod_pais
     AND pais.oid_pais = ptpa.pais_oid_pais
     AND ptpa.soci_oid_soci = soci.oid_soci
     AND pgpd.ind_esta_pago = CASE WHEN psTipoEnvio = 'N' THEN '1' ELSE '3' END
     AND ( psTipoEnvio = 'N' OR ( psTipoEnvio = 'R' AND TRUNC(pgpd.fec_envi) = TRUNC(pdFechaPago) ))
   GROUP BY pgpd.pais_cod_pais,
            soci.cod_soci,
            TO_CHAR( CASE WHEN psTipoEnvio = 'N' THEN pgpd.fec_crea ELSE pgpd.fec_crea END,'YYYYMMDD' ),
            CASE WHEN psTipoEnvio = 'N' THEN vsNumeroLote ELSE pgpd.val_nume_lote END
       ;

TYPE interfazrectab IS TABLE OF c_interfaz%ROWTYPE INDEX BY BINARY_INTEGER;
interfazrecord interfazrectab;

-- Variables para la generacion del archivo de texto

lsDirTempo      bas_inter.dir_temp%TYPE;
w_Filas         NUMBER := 1000;
v_Handle        utl_file.file_type;
lsLinea         VARCHAR2(2000);
lsLinea2        VARCHAR2(2000);
lsNombreArchivo VARCHAR2(50);
lbAbrirUtlFile  BOOLEAN;
lsNumeroLote    VARCHAR2(12);

BEGIN

-- Obtener Número de Lote

   BEGIN
    SELECT MAX(lote.num_lote)
      INTO lsNumeroLote
      FROM bas_histo_lotes lote
     WHERE lote.PAIS_COD_PAIS = psCodigoPais
       AND lote.SIST_COD_SIST= psCodigosistema
       AND lote.INTE_COD_INTE = psCodigoInterfaz
       AND lote.FEC_FPRO IS NULL;
   EXCEPTION WHEN NO_DATA_FOUND THEN
     lsNumeroLote := TO_CHAR(pdFechaPago,'YYYYMMDD') || '0001';
   END;

-- Generar Archivo de Texto (Detalle)

    lbAbrirUtlFile := TRUE;

    OPEN c_interfaz(lsNumeroLote);
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazRecord LIMIT w_filas;

-- Procedimiento inicial para generar interfaz */

      IF lbAbrirUtlFile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbAbrirUtlFile := FALSE;
      END IF;

      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lsLinea := interfazRecord(x).numeroLote ||';'||
                     interfazRecord(x).codigoPais ||';'||
                     interfazRecord(x).fechaContable ||';'||
                     interfazRecord(x).fechaDocumento ||';'||
                     interfazRecord(x).codigoSociedad ||';'||
                     interfazRecord(x).codigoCentroLogistico ||';'||
                     interfazRecord(x).tipoAsiento ||';'||
                     interfazRecord(x).tipoMovimiento ||';'||
                     interfazRecord(x).tipoOferta ||';'||
                     interfazRecord(x).codigoMarca ||';'||
                     interfazRecord(x).grupoArticulo ||';'||
                     interfazRecord(x).numeroComprobante ||';'||
                     interfazRecord(x).debeHaber ||';'||
                     interfazRecord(x).descripcion ||';'||
                     interfazRecord(x).periodoComercial ||';'||
                     interfazRecord(x).ejercicioComercial ||';'||
                     interfazRecord(x).tasaImpuesto ||';'||
                     interfazRecord(x).importePago;
          utl_file.put_line(v_handle,lslinea);
        END LOOP;
      END IF;

      EXIT WHEN c_interfaz%NOTFOUND;

    END LOOP;

    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;

-- Actualizar auditoría en archivo de pagos

   BEGIN
      UPDATE inc_concu_pagos_detal pgpd
         SET pgpd.val_nume_lote = lsNumeroLote,
             pgpd.ind_esta_pago = '3',
             pgpd.fec_envi = SYSDATE,
             pgpd.usu_modi = psCodigoUsuario,
             pgpd.fec_modi = SYSDATE
       WHERE pgpd.ind_esta_pago = CASE WHEN psTipoEnvio = 'N' THEN '1' ELSE '3' END
           AND ( psTipoEnvio = 'N' OR ( psTipoEnvio = 'R' AND TRUNC(pgpd.fec_envi) = pdFechaPago ))
           ;
   EXCEPTION WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123, 'ERROR AL ACTUALIZAR ARCHIVO DE PAGOS: ' || ls_sqlerrm);
   END;
    RETURN;
EXCEPTION WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 1000);
    raise_application_error(-20123, 'ERROR INT_PR_SAF_PAGOS_TOP: ' || ls_sqlerrm);
END INT_PR_SAF_PAGOS_TOP;

END int_pkg_sapfi;
/
