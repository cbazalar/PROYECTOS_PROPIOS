CREATE OR REPLACE PACKAGE "IMP_PKG_PROCE_ELECT" AS

-- Constantes
CODIGO_CANAL VARCHAR2(10) := 'VD';
CODIGO_MARCA VARCHAR2(10) := 'T';
UTL_FILE_MAXI_CARA NUMBER := 32767;

/**************************************************************************
Descripcion         : Proceso que genera la informacion de facturas para
                      Ecuador
Fecha Creación      : 02/07/2012
Autor               : Dennys Oliva Iriarte
***************************************************************************/
PROCEDURE IMP_PR_PROCE_FACTU_ELECT_EC(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      pscodigoPeriodo     VARCHAR2,
                                      psfechaFacturacion  VARCHAR2,
                                      psNombreArchivo     VARCHAR2
                                      );

/**************************************************************************
Descripcion         : Proceso que genera la informacion de detalle de
                      facturas para Ecuador
Fecha Creación      : 02/07/2012
Autor               : Dennys Oliva Iriarte
***************************************************************************/
PROCEDURE IMP_PR_PROCE_FACTU_ELECT_ECDET(psCodigoPais        VARCHAR2,
                                         psCodigoSistema     VARCHAR2,
                                         psCodigoInterfaz    VARCHAR2,
                                         pscodigoPeriodo     VARCHAR2,
                                         psfechaFacturacion  VARCHAR2,
                                         psNombreArchivo     VARCHAR2);

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Notas de Credito
                      para Ecuador
Fecha Creación      : 02/07/2012
Autor               : Dennys Oliva Iriarte
***************************************************************************/
PROCEDURE IMP_PR_PROCE_NOCRE_ELECT_EC(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      pscodigoPeriodo     VARCHAR2,
                                      psfechaFacturacion  VARCHAR2,
                                      psNombreArchivo     VARCHAR2) ;

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Notas de Credito
                      para Ecuador
Fecha Creación      : 02/07/2012
Autor               : Dennys Oliva Iriarte
***************************************************************************/
PROCEDURE IMP_PR_PROCE_NOCRE_ELECT_ECDET(psCodigoPais        VARCHAR2,
                                         psCodigoSistema     VARCHAR2,
                                         psCodigoInterfaz    VARCHAR2,
                                         pscodigoPeriodo     VARCHAR2,
                                         psfechaFacturacion  VARCHAR2,
                                         psNombreArchivo     VARCHAR2);

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Detalles
                      para Ecuador
Fecha Creación      : 02/07/2012
Autor               : Dennys Oliva Iriarte
***************************************************************************/
PROCEDURE IMP_PR_PROCE_DETAL_ELECT_EC(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      pscodigoPeriodo     VARCHAR2,
                                      psfechaFacturacion  VARCHAR2,
                                      psNombreArchivo     VARCHAR2);

  /***************************************************************************
  Descripcion       : Secuencia la los Pedidos Que generan la Boleta Electronica
  Fecha Creacion    : 21/05/2013
  Autor             : Jose Cairampoma
  ***************************************************************************/
PROCEDURE imp_pr_secue_bolet_elect
(
  pscodigopais       VARCHAR2,
  pscodigoperiodo    VARCHAR2,
  psfechafacturacion VARCHAR2,
  psindicadornovedad VARCHAR2
);
/**************************************************************************
Descripcion         : Proceso que genera la informacion de Facturas
                      Cabeceras para Peru
Fecha Creación      : 01/04/2013
Autor               : Carlos Bazalar
***************************************************************************/
PROCEDURE INT_PR_IMP_FACTU_CABEC_PERU(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      pscodigoPeriodo     VARCHAR2,
                                      psfechaFacturacion  VARCHAR2,
                                      psindicadorNovedad  VARCHAR2,
                                      psNombreArchivo     VARCHAR2,
                                      psNumeroLote        VARCHAR2);

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Facturas
                      Detalle para Peru
Fecha Creación      : 01/04/2013
Autor               : Carlos Bazalar
***************************************************************************/
PROCEDURE INT_PR_IMP_FACTU_DETAL_PERU(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      pscodigoPeriodo     VARCHAR2,
                                      psfechaFacturacion  VARCHAR2,
                                      psindicadorNovedad  VARCHAR2,
                                      psNombreArchivo     VARCHAR2,
                                      psNumeroLote        VARCHAR2);

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Boletas 065
                      Cabecera para Peru
Fecha Creación      : 01/04/2013
Autor               : Carlos Bazalar
***************************************************************************/
PROCEDURE INT_PR_IMP_BOL65_CABEC_PERU(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      pscodigoPeriodo     VARCHAR2,
                                      psfechaFacturacion  VARCHAR2,
                                      psindicadorNovedad  VARCHAR2,
                                      psNombreArchivo     VARCHAR2,
                                      psNumeroLote        VARCHAR2);

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Boletas 065
                      Detalle para Peru
Fecha Creación      : 01/04/2013
Autor               : Carlos Bazalar
***************************************************************************/
PROCEDURE INT_PR_IMP_BOL65_DETAL_PERU(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      pscodigoPeriodo     VARCHAR2,
                                      psfechaFacturacion  VARCHAR2,
                                      psindicadorNovedad  VARCHAR2,
                                      psNombreArchivo     VARCHAR2,
                                      psNumeroLote        VARCHAR2);

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Boletas 067
                      Cabecera para Peru
Fecha Creación      : 01/04/2013
Autor               : Carlos Bazalar
***************************************************************************/
PROCEDURE INT_PR_IMP_BOL67_CABEC_PERU(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      pscodigoPeriodo     VARCHAR2,
                                      psfechaFacturacion  VARCHAR2,
                                      psindicadorNovedad  VARCHAR2,
                                      psNombreArchivo     VARCHAR2,
                                      psNumeroLote        VARCHAR2);

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Boletas 067
                      Detalle para Peru
Fecha Creación      : 01/04/2013
Autor               : Carlos Bazalar
***************************************************************************/
PROCEDURE INT_PR_IMP_BOL67_DETAL_PERU(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      pscodigoPeriodo     VARCHAR2,
                                      psfechaFacturacion  VARCHAR2,
                                      psindicadorNovedad  VARCHAR2,
                                      psNombreArchivo     VARCHAR2,
                                      psNumeroLote        VARCHAR2);
 /***************************************************************************
  Descripcion       : Guarda e historico del
  Fecha Creacion    : 21/05/2013
  Autor             : Jose Cairampoma
  ***************************************************************************/
PROCEDURE imp_pr_histo_bolet_elect(psindicadornovedad VARCHAR2);
/**************************************************************************
Descripcion         : Proceso que genera la informacion de Notas Debito
                      Cabecera para Peru
Fecha Creación      : 01/04/2013
Autor               : Carlos Bazalar
***************************************************************************/
PROCEDURE INT_PR_IMP_NOTDE_CABEC_PERU(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      pscodigoPeriodo     VARCHAR2,
                                      psfechaFacturacion  VARCHAR2,
                                      psindicadorNovedad  VARCHAR2,
                                      psNombreArchivo     VARCHAR2);

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Notas Debito
                      Detalle para Peru
Fecha Creación      : 01/04/2013
Autor               : Carlos Bazalar
***************************************************************************/
PROCEDURE INT_PR_IMP_NOTDE_DETAL_PERU(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      pscodigoPeriodo     VARCHAR2,
                                      psfechaFacturacion  VARCHAR2,
                                      psindicadorNovedad  VARCHAR2,
                                      psNombreArchivo     VARCHAR2);

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Notas Credito Facturas
                      Cabecera para Peru
Fecha Creación      : 01/04/2013
Autor               : Carlos Bazalar
***************************************************************************/
PROCEDURE INT_PR_IMP_NOCRF_CABEC_PERU(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      pscodigoPeriodo     VARCHAR2,
                                      psfechaFacturacion  VARCHAR2,
                                      psindicadorNovedad  VARCHAR2,
                                      psNombreArchivo     VARCHAR2);

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Notas Credito Facturas
                      Detalle para Peru
Fecha Creación      : 01/04/2013
Autor               : Carlos Bazalar
***************************************************************************/
PROCEDURE INT_PR_IMP_NOCRF_DETAL_PERU(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      pscodigoPeriodo     VARCHAR2,
                                      psfechaFacturacion  VARCHAR2,
                                      psindicadorNovedad  VARCHAR2,
                                      psNombreArchivo     VARCHAR2);

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Notas Credito Boletas
                      Cabecera para Peru
Fecha Creación      : 01/04/2013
Autor               : Carlos Bazalar
***************************************************************************/
PROCEDURE INT_PR_IMP_NOCRB_CABEC_PERU(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      pscodigoPeriodo     VARCHAR2,
                                      psfechaFacturacion  VARCHAR2,
                                      psindicadorNovedad  VARCHAR2,
                                      psNombreArchivo     VARCHAR2);

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Notas Credito Boletas
                      Detalle para Peru
Fecha Creación      : 01/04/2013
Autor               : Carlos Bazalar
***************************************************************************/
PROCEDURE INT_PR_IMP_NOCRB_DETAL_PERU(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      pscodigoPeriodo     VARCHAR2,
                                      psfechaFacturacion  VARCHAR2,
                                      psindicadorNovedad  VARCHAR2,
                                      psNombreArchivo     VARCHAR2);

/**************************************************************************
Descripcion         : Proceso que devuelve el codigo de periodo y la fecha de facturacion
                      de un pedido. Lo devuelve el valor en concatenado por una -
Fecha Creación      : 01/04/2013
Autor               : Carlos Bazalar
***************************************************************************/
FUNCTION IMP_FN_DEVUE_PERIO_FECHA_PEDID(psnumeropedido    VARCHAR2,
                                      pscodigoPeriodo     VARCHAR2,
                                      psfechaFacturacion  VARCHAR2)
RETURN VARCHAR2;

/**************************************************************************
Descripcion         : Proceso que devuelve el codigo de periodo y la fecha de facturacion
                      de un pedido. Lo devuelve el valor en concatenado por una -
Fecha Creación      : 01/04/2013
Autor               : Carlos Bazalar
***************************************************************************/
FUNCTION IMP_FN_DEVUE_LOTE_PROD_PERU(psnumeropedido    VARCHAR2,
                                      pscodigoProducto     VARCHAR2)
RETURN VARCHAR2;
/**************************************************************************
Descripcion         : NTERFAZ PARA SOFTWARE DE ORDENAMIENTO E IMPRESIÓN EN XEROX (PERU)
Fecha Creación      : 29/05/2013
Autor               : Jose Cairampoma
***************************************************************************/
PROCEDURE imp_pr_orden_impre_xerox
(
  pscodigopais       VARCHAR2,
  pscodigosistema    VARCHAR2,
  pscodigointerfaz   VARCHAR2,
  psnombrearchivo    VARCHAR2,
  pscodigoperiodo    VARCHAR2,
  psfechafacturacion VARCHAR2
);

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Facturas
                      Cabeceras para Peru
Fecha Creación      : 24/07/2013
Autor               : Sebastian Guerra
***************************************************************************/
PROCEDURE INT_PR_IMP_FACTU_CABEC_DOCUM(psCodigoPais        VARCHAR2,
                    psCodigoSistema     VARCHAR2,
                    psCodigoInterfaz    VARCHAR2,
                    psFechaFacturacion  VARCHAR2,
                    psNombreArchivo     VARCHAR2,
                    psNumeroLote        VARCHAR2,
                    psTipoDocumento     VARCHAR2,
                    psSerieDocumento    VARCHAR2);

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Facturas
                      Detalle para Peru
Fecha Creación      : 24/07/2013
Autor               : Sebastian Guerra
***************************************************************************/
PROCEDURE INT_PR_IMP_FACTU_DETAL_DOCUM(psCodigoPais        VARCHAR2,
                    psCodigoSistema     VARCHAR2,
                    psCodigoInterfaz    VARCHAR2,
                    psFechaFacturacion  VARCHAR2,
                    psNombreArchivo     VARCHAR2,
                    psNumeroLote        VARCHAR2,
                    psTipoDocumento     VARCHAR2,
                    psSerieDocumento    VARCHAR2);

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Boletas 065
                      Cabecera para Peru
Fecha Creación      : 24/07/2013
Autor               : Sebastian Guerra
***************************************************************************/
PROCEDURE INT_PR_IMP_BOL65_CABEC_DOCUM(psCodigoPais        VARCHAR2,
                    psCodigoSistema     VARCHAR2,
                    psCodigoInterfaz    VARCHAR2,
                    psFechaFacturacion  VARCHAR2,
                    psNombreArchivo     VARCHAR2,
                    psNumeroLote        VARCHAR2,
                    psTipoDocumento     VARCHAR2,
                    psSerieDocumento    VARCHAR2);

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Boletas 065
                      Detalle para Peru
Fecha Creación      : 24/07/2013
Autor               : Sebastian Guerra
***************************************************************************/
PROCEDURE INT_PR_IMP_BOL65_DETAL_DOCUM(psCodigoPais        VARCHAR2,
                    psCodigoSistema     VARCHAR2,
                    psCodigoInterfaz    VARCHAR2,
                    psFechaFacturacion  VARCHAR2,
                    psNombreArchivo     VARCHAR2,
                    psNumeroLote        VARCHAR2,
                    psTipoDocumento     VARCHAR2,
                    psSerieDocumento    VARCHAR2);

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Boletas 067
                      Cabecera para Peru
Fecha Creación      : 24/07/2013
Autor               : Sebastian Guerra
***************************************************************************/
PROCEDURE INT_PR_IMP_BOL67_CABEC_DOCUM(psCodigoPais        VARCHAR2,
                    psCodigoSistema     VARCHAR2,
                    psCodigoInterfaz    VARCHAR2,
                    psFechaFacturacion  VARCHAR2,
                    psNombreArchivo     VARCHAR2,
                    psNumeroLote        VARCHAR2,
                    psTipoDocumento     VARCHAR2,
                    psSerieDocumento    VARCHAR2);

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Boletas 067
                      Detalle para Peru
Fecha Creación      : 24/07/2013
Autor               : Sebastian Guerra
***************************************************************************/
PROCEDURE INT_PR_IMP_BOL67_DETAL_DOCUM(psCodigoPais        VARCHAR2,
                    psCodigoSistema     VARCHAR2,
                    psCodigoInterfaz    VARCHAR2,
                    psFechaFacturacion  VARCHAR2,
                    psNombreArchivo     VARCHAR2,
                    psNumeroLote        VARCHAR2,
                    psTipoDocumento     VARCHAR2,
                    psSerieDocumento    VARCHAR2);

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Notas Debito
                      Cabecera para Peru
Fecha Creación      : 24/07/2013
Autor               : Sebastian Guerra
***************************************************************************/
PROCEDURE INT_PR_IMP_NOTDE_CABEC_DOCUM(psCodigoPais        VARCHAR2,
                    psCodigoSistema     VARCHAR2,
                    psCodigoInterfaz    VARCHAR2,
                    psFechaFacturacion  VARCHAR2,
                    psNombreArchivo     VARCHAR2,
                    psTipoDocumento     VARCHAR2,
                    psSerieDocumento    VARCHAR2);

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Notas Debito
                      Detalle para Peru
Fecha Creación      : 24/07/2013
Autor               : Sebastian Guerra
***************************************************************************/
PROCEDURE INT_PR_IMP_NOTDE_DETAL_DOCUM(psCodigoPais        VARCHAR2,
                    psCodigoSistema     VARCHAR2,
                    psCodigoInterfaz    VARCHAR2,
                    psFechaFacturacion  VARCHAR2,
                    psNombreArchivo     VARCHAR2,
                    psTipoDocumento     VARCHAR2,
                    psSerieDocumento    VARCHAR2);

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Notas Credito Facturas
                      Cabecera para Peru
Fecha Creación      : 24/07/2013
Autor               : Sebastian Guerra
***************************************************************************/
PROCEDURE INT_PR_IMP_NOCRF_CABEC_DOCUM(psCodigoPais        VARCHAR2,
                    psCodigoSistema     VARCHAR2,
                    psCodigoInterfaz    VARCHAR2,
                    psFechaFacturacion  VARCHAR2,
                    psNombreArchivo     VARCHAR2,
                    psTipoDocumento     VARCHAR2,
                    psSerieDocumento    VARCHAR2);

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Notas Credito Facturas
                      Detalle para Peru
Fecha Creación      : 24/07/2013
Autor               : Sebastian Guerra
***************************************************************************/
PROCEDURE INT_PR_IMP_NOCRF_DETAL_DOCUM(psCodigoPais        VARCHAR2,
                    psCodigoSistema     VARCHAR2,
                    psCodigoInterfaz    VARCHAR2,
                    psFechaFacturacion  VARCHAR2,
                    psNombreArchivo     VARCHAR2,
                    psTipoDocumento     VARCHAR2,
                    psSerieDocumento    VARCHAR2);

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Notas Credito Boletas
                      Cabecera para Peru
Fecha Creación      : 24/07/2013
Autor               : Sebastian Guerra
***************************************************************************/
PROCEDURE INT_PR_IMP_NOCRB_CABEC_DOCUM(psCodigoPais        VARCHAR2,
                    psCodigoSistema     VARCHAR2,
                    psCodigoInterfaz    VARCHAR2,
                    psFechaFacturacion  VARCHAR2,
                    psNombreArchivo     VARCHAR2,
                    psTipoDocumento     VARCHAR2,
                    psSerieDocumento    VARCHAR2);

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Notas Credito Boletas
                      Detalle para Peru
Fecha Creación      : 24/07/2013
Autor               : Sebastian Guerra
***************************************************************************/
PROCEDURE INT_PR_IMP_NOCRB_DETAL_DOCUM(psCodigoPais        VARCHAR2,
                    psCodigoSistema     VARCHAR2,
                    psCodigoInterfaz    VARCHAR2,
                    psFechaFacturacion  VARCHAR2,
                    psNombreArchivo     VARCHAR2,
                    psTipoDocumento     VARCHAR2,
                    psSerieDocumento    VARCHAR2);

/**************************************************************************
Descripcion         : Proceso que genera la informacion de BOLETA ELECTRONICA
                      DE HONORARIOS PARA CHILE
Fecha Creación      : 30/09/2013
Autor               : Sergio Apaza
***************************************************************************/
PROCEDURE IMP_PR_PROCE_BOLET_HONOR(psCodigoPais        VARCHAR2,
                                   psCodigoSistema     VARCHAR2,
                                   psCodigoInterfaz    VARCHAR2,
                                   psCodigoPeriodo     VARCHAR2,
                                   psFechaFacturacion  VARCHAR2,
                                   psNombreArchivo     VARCHAR2,
                                   psDirectorio        VARCHAR2,
                                   psCodigoUsuario     VARCHAR2);

/**************************************************************************
Descripcion         : Genera el archivo de de Boletas Honorarios
Fecha Creación      : 30/09/2003
Autor               : Sergio Apaza
***************************************************************************/
PROCEDURE IMP_PR_GENER_ARCHI_BOLET(psCodigoPais    VARCHAR2,
                                   psNombreArchivo VARCHAR2,
                                   psDirectorio    VARCHAR2);
                                   
/**************************************************************************
Descripcion         : NTERFAZ PARA SOFTWARE DE ORDENAMIENTO E IMPRESIÓN EN XEROX (PERU)
Fecha Creación      : 21/10/2013
Autor               : Ivan Tocto
***************************************************************************/
PROCEDURE imp_pr_orden_impre_xerox_alter
(
  pscodigopais       VARCHAR2,
  pscodigosistema    VARCHAR2,
  pscodigointerfaz   VARCHAR2,
  psnombrearchivo    VARCHAR2,
  pscodigoperiodo    VARCHAR2,
  psfechafacturacion VARCHAR2
);
                                   
/**************************************************************************************
Descripcion           : Genera Interfaz de Recepcion de cabecera de Notas de Credito Retail
Fecha Creacion    : 12/05/2014
Autor                   : Sebastian Guerra
**************************************************************************************/
PROCEDURE  INT_PR_RECEP_NOCRE_CABEC_DOCUM
(
  pscodigopais          VARCHAR2,
  pscodigosistema    VARCHAR2,
  pscodigointerfaz    VARCHAR2,
  psnombrearchivo   VARCHAR2,
  psusuario               VARCHAR2
);

/**************************************************************************************
Descripcion           : Genera Interfaz de Recepcion del detalle de Notas de Credito Retail
Fecha Creacion    : 12/05/2014
Autor                   : Sebastian Guerra
**************************************************************************************/
PROCEDURE  INT_PR_RECEP_NOCRE_DETAL_DOCUM
(
  pscodigopais          VARCHAR2,
  pscodigosistema    VARCHAR2,
  pscodigointerfaz    VARCHAR2,
  psnombrearchivo   VARCHAR2,
  psusuario               VARCHAR2
);

/*****************************************************************************
  Descripcion         : Proceso que genera la informacion de Notas Credito Boletas Cabecera Retail
  Fecha Creación  : 23/05/2014
  Autor                  : Sebastian Guerra
*****************************************************************************/
PROCEDURE INT_PR_IMP_NOCRB_CABEC_RETAI(psCodigoPais        VARCHAR2,
                    psCodigoSistema     VARCHAR2,
                    psCodigoInterfaz    VARCHAR2,
                    psFechaFacturacion  VARCHAR2,
                    psNombreArchivo     VARCHAR2,
                    psTipoDocumento     VARCHAR2,
                    psSerieDocumento    VARCHAR2);

/*****************************************************************************
  Descripcion         : Proceso que genera la informacion de Notas Credito Boletas Detalle Retail
  Fecha Creación  : 23/05/2014
  Autor                  : Sebastian Guerra
*****************************************************************************/
PROCEDURE INT_PR_IMP_NOCRB_DETAL_RETAI(psCodigoPais        VARCHAR2,
                    psCodigoSistema     VARCHAR2,
                    psCodigoInterfaz    VARCHAR2,
                    psFechaFacturacion  VARCHAR2,
                    psNombreArchivo     VARCHAR2,
                    psTipoDocumento     VARCHAR2,
                    psSerieDocumento    VARCHAR2);
					
/*****************************************************************************
  Descripcion         : Proceso que genera la informacion de Notas Credito Boletas Cabecera Retail
  Fecha Creación  : 23/05/2014
  Autor                  : Sebastian Guerra
*****************************************************************************/
PROCEDURE INT_PR_IMP_NOCRF_CABEC_RETAI(psCodigoPais        VARCHAR2,
                    psCodigoSistema     VARCHAR2,
                    psCodigoInterfaz    VARCHAR2,
                    psFechaFacturacion  VARCHAR2,
                    psNombreArchivo     VARCHAR2,
                    psTipoDocumento     VARCHAR2,
                    psSerieDocumento    VARCHAR2);

/*****************************************************************************
  Descripcion         : Proceso que genera la informacion de Notas Credito Boletas Detalle Retail
  Fecha Creación  : 23/05/2014
  Autor                  : Sebastian Guerra
*****************************************************************************/
PROCEDURE INT_PR_IMP_NOCRF_DETAL_RETAI(psCodigoPais        VARCHAR2,
                    psCodigoSistema     VARCHAR2,
                    psCodigoInterfaz    VARCHAR2,
                    psFechaFacturacion  VARCHAR2,
                    psNombreArchivo     VARCHAR2,
                    psTipoDocumento     VARCHAR2,
                    psSerieDocumento    VARCHAR2);
                    
  /***************************************************************************
  Descripcion       : Inserta los Documentos Internos por grupo
  Fecha Creacion    : 24/07/2014
  Autor             : Aurelio Oviedo
  ***************************************************************************/
PROCEDURE IMP_PR_INSER_DOCUM_INTER
(
  pscodigopais VARCHAR2,
  psfechafacturacion VARCHAR2,
  psTipoDocumento VARCHAR2
);

END;
/
CREATE OR REPLACE PACKAGE BODY "IMP_PKG_PROCE_ELECT" AS

/* Declaracion de variables */
ln_sqlcode   NUMBER(10);
ls_sqlerrm   VARCHAR2(1500);
W_FILAS      NUMBER:=5000;

/**************************************************************************
Descripcion         : Proceso que genera la informacion de facturas para
                      Ecuador
Fecha Creación      : 02/07/2012
Autor               : Dennys Oliva Iriarte
***************************************************************************/
PROCEDURE IMP_PR_PROCE_FACTU_ELECT_EC(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      pscodigoPeriodo     VARCHAR2,
                                      psfechaFacturacion  VARCHAR2,
                                      psNombreArchivo     VARCHAR2
                                      ) IS

    CURSOR c_facturas(oidPeriodo NUMBER,
                      oidPeriodoSgte NUMBER,
                      oidActividad NUMBER,
                      numeroLoteFacturacion NUMBER) IS
        select substr(sp.cod_pais,1,2) codigo_pais,
               substr(sp.cod_pais,3,1) marca,
               m.cod_clie  codigo_cliente,
               m.val_nom1  nombre1,
               m.val_nom2  nombre2,
               m.val_ape1  apellido1,
               m.val_ape2  apellido2,
               dc.val_dire_comp direccion,
               pc.cod_peri codigo_periodo,
               sc.val_nume_soli numero_pedido,
               to_char(dc.fec_fact,'ddmmyyyy') fecha_facturacion,
               ti.val_tasa_impu tasa_iva,
               dc.oid_cabe id_cabecera,
               dc.num_docu_cont_inte numero_interno,
               zz.cod_zona codigo_zona,
               zt.cod_terr codigo_territorio,
               null num_peri_Refe,
               null cod_peri_Refe,
               null cod_inte_Refe,
               td.cod_tipo_docu codigo_tipo_documento,
               ROW_NUMBER() OVER (PARTITION BY sc.VAL_NUME_SOLI ORDER BY dc.NUM_DOCU_CONT_INTE) pagina_actual,
               (SELECT count(1) FROM fac_docum_conta_cabec z, ped_solic_cabec Y
                 WHERE Y.VAL_NUME_SOLI =  sc.val_nume_soli
                  and y.oid_soli_cabe = z.soca_oid_soli_cabe
                  and z.fec_fact = dc.fec_fact
                  and z.tido_oid_tipo_docu = dc.tido_oid_tipo_docu) numero_total_paginas,
               null des_moti,
                round((select sum(decode(cl.val_prec_cata_unit_loca,0,cl.val_prec_cont_tota_loca, cl.val_prec_cata_tota_loca))
                   from fac_docum_conta_linea cl where cl.dcca_oid_cabe = dc.oid_cabe),2)+nvl(dc.imp_flet_tota_loca,0) total_productos,
                round((select sum(decode(cl.val_prec_cata_unit_loca,0,cl.val_prec_cont_tota_loca, cl.imp_desc_tota_loca))
                   from fac_docum_conta_linea cl where cl.dcca_oid_cabe = dc.oid_cabe),2) total_descuentos,
                round((select sum(imp_impu_tota_prod_naci)
                   from fac_docum_conta_linea cl
                  where cl.dcca_oid_cabe = dc.oid_cabe),2)  ICE,
             (
             select sum(
               case when cl.val_prec_cata_unit_loca=0 and cl.imp_impu_tota_prod_naci>0 then cl.imp_impu_tota_prod_naci
                    when cl.imp_impu_tota_prod_naci=0 then
                       decode(decode(cl.val_prec_cata_unit_loca,0,100,cl.VAL_PORC_DESC),100,0,
                       (cl.val_prec_sin_impu_tota_loca-nvl(cl.imp_impu_tota_prod_naci,0))-
                       round(case when cl.val_prec_cata_unit_loca=0 then cl.val_prec_sin_impu_unit-(nvl(cl.imp_impu_tota_prod_naci,0)/cl.num_unid_aten)
                            when nvl(cl.VAL_PORC_DESC,0)=0 then 0
                            when ((cl.val_prec_sin_impu_unit-(nvl(cl.imp_impu_tota_prod_naci,0)/cl.num_unid_aten))-((cl.imp_desc_unit_loca+cl.val_prec_cont_unit_loca)/1.12))*cl.num_unid_aten<0 then  ((cl.imp_desc_unit_loca+cl.val_prec_cont_unit_loca)/1.12)-(nvl(cl.imp_impu_tota_prod_naci,0)/cl.num_unid_aten)
                            else (cl.imp_desc_unit_loca+cl.val_prec_cont_unit_loca)/1.12
                       end,2)
                       )
                    else decode(cl.val_prec_cata_unit_loca,0,0,(
                         (cl.val_prec_sin_impu_unit-(nvl(cl.imp_impu_tota_prod_naci,0)/cl.num_unid_aten))*cl.num_unid_aten-round((nvl(cl.imp_desc_tota_loca,0)/1.12),2)+nvl(cl.imp_impu_tota_prod_naci,0)))
               end
               )
             + decode(dc.imp_flet_tota_loca,0,0,(dc.imp_flet_sin_impu_tota_docu))
             from fac_docum_conta_linea cl where num_unid_aten<>0 and cl.dcca_oid_cabe = dc.oid_cabe
             )
             base_imponible,
             (
             select sum(decode(cl.val_prec_cata_unit_loca,0,0,cl.IMP_IMPU_TOTA_LOCA)+round(case when decode(cl.val_prec_cata_unit_loca,0,100,cl.VAL_PORC_DESC)=100 and nvl(cl.imp_impu_tota_prod_naci,0)>0 then cl.imp_impu_tota_prod_naci*0.12 else 0 end,2))
             + decode(dc.imp_flet_tota_loca,0,0,(dc.imp_flet_tota_loca-dc.imp_flet_sin_impu_tota_docu))
             from fac_docum_conta_linea cl where num_unid_aten<>0 and cl.dcca_oid_cabe = dc.oid_cabe)
             IVA,
             rv.imp_tota+(
             select sum(round(case when decode(cl.val_prec_cata_unit_loca,0,100,cl.VAL_PORC_DESC)=100 and nvl(cl.imp_impu_tota_prod_naci,0)>0 then cl.imp_impu_tota_prod_naci*0.12 else 0 end,2))
             from fac_docum_conta_linea cl where num_unid_aten<>0 and cl.dcca_oid_cabe = dc.oid_cabe)
             importe_total,
             ci.num_docu_iden identificacion,
             mtd.val_sigl
       ,                 round((select sum(decode(cl.val_prec_cata_unit_loca,0,0,cl.IMP_IMPU_TOTA_LOCA))
                   from fac_docum_conta_linea cl where cl.dcca_oid_cabe = dc.oid_cabe),2) + nvl(( imp_flet_tota_loca-imp_flet_sin_impu_tota_docu),0) +
                round((select sum(
               (cl.val_prec_sin_impu_tota_loca-nvl(cl.imp_impu_tota_prod_naci,0))-
               round(case when cl.val_prec_cata_unit_loca=0 then cl.val_prec_sin_impu_unit-(nvl(cl.imp_impu_tota_prod_naci,0)/cl.num_unid_aten)
                    when nvl(cl.VAL_PORC_DESC,0)=0 then 0
                    when ((cl.val_prec_sin_impu_unit-(nvl(cl.imp_impu_tota_prod_naci,0)/cl.num_unid_aten))-((cl.imp_desc_unit_loca+cl.val_prec_cont_unit_loca)/1.12))*cl.num_unid_aten<0 then  ((cl.imp_desc_unit_loca+cl.val_prec_cont_unit_loca)/1.12)-(nvl(cl.imp_impu_tota_prod_naci,0)/cl.num_unid_aten)
                    else (cl.imp_desc_unit_loca+cl.val_prec_cont_unit_loca)/1.12
               end,2) *abs(cl.num_unid_aten)
                    )
                   from fac_docum_conta_linea cl where num_unid_aten<>0 and cl.dcca_oid_cabe = dc.oid_cabe),2) + nvl(imp_flet_sin_impu_tota_docu,0) +
                round((select sum(nvl(imp_impu_tota_prod_naci,0))
                   from fac_docum_conta_linea cl
                  where cl.dcca_oid_cabe = dc.oid_cabe),2)+(
             select sum(round(case when decode(cl.val_prec_cata_unit_loca,0,100,cl.VAL_PORC_DESC)=100 and nvl(cl.imp_impu_tota_prod_naci,0)>0 then cl.imp_impu_tota_prod_naci*0.12 else 0 end,2))
             from fac_docum_conta_linea cl where num_unid_aten<>0 and cl.dcca_oid_cabe = dc.oid_cabe)
              SRI,
              (select c.val_text_comu from mae_clien_comun c where clie_oid_clie=m.oid_clie and c.ticm_oid_tipo_comu=3) mail
        from fac_docum_conta_cabec dc,
             ped_solic_cabec sc,
             --cra_crono cc,
             seg_pais sp,
             zon_zona zz,
             zon_terri zt,
             zon_terri_admin zta,
             mae_clien m,
             mae_clien_direc md,
             mae_clien_ident ci,
             mae_tipo_docum mtd,
             cra_perio cp,
             seg_perio_corpo pc,
             ped_tasa_impue ti,
             fac_tipo_docum td,
             fac_regis_unico_venta rv
        where dc.perd_oid_peri = oidPeriodo  -- periodo actual
        and td.cod_tipo_docu = '001' -- FACTURAS
        and dc.fec_fact =  TO_DATE(psfechaFacturacion, 'DD/MM/YYYY')
        and rv.num_docu_cont_inte = dc.num_docu_cont_inte
        and rv.val_ejer_docu_inte = dc.val_ejer_docu_inte
        and rv.tido_oid_tipo_docu = dc.tido_oid_tipo_docu
        and rv.dcca_oid_cabe = dc.oid_cabe
        and cp.oid_peri = dc.perd_oid_peri
        and cp.peri_oid_peri = pc.oid_peri
        and sp.oid_pais = dc.pais_oid_pais
        and zz.oid_zona = dc.zzon_oid_zona
        and ti.oid_tasa_impu = sc.taim_oid_tasa_impu
        and zt.oid_terr = sc.terr_oid_terr
        and zta.terr_oid_terr = zt.oid_terr
        and zta.oid_terr_admi = sc.ztad_oid_terr_admi
        and td.oid_tipo_docu = dc.tido_oid_tipo_docu
        and m.oid_clie = sc.clie_oid_clie
        and md.clie_oid_clie = m.oid_clie
        and ci.clie_oid_clie = m.oid_clie
        and mtd.oid_tipo_docu = ci.tdoc_oid_tipo_docu
        and ci.val_iden_docu_prin = 1
        and md.oid_clie_dire = dc.cldi_oid_clie_dire
        and sc.oid_soli_cabe = dc.soca_oid_soli_cabe
        --and cc.zzon_oid_zona = sc.zzon_oid_zona -- join cronograma zona
        --and cc.cact_oid_acti = oidActividad    -- Existe vencimiento para la zona
        --and cc.perd_oid_peri = oidPeriodoSgte -- en la campaña siguiente
        --and sc.ind_inte_lari_gene = '1'
        --and (numeroLoteFacturacion IS NULL OR sc.NUM_LOTE_FACT = numeroLoteFacturacion)
        and exists(select null
                    from FAC_DOCUM_CONTA_LINEA DET,
                         PED_SOLIC_POSIC PSP,
                         PRE_OFERT_DETAL POD
                   where DET.SOPO_OID_SOLI_POSI = PSP.OID_SOLI_POSI
                     AND PSP.OFDE_OID_DETA_OFER = POD.OID_DETA_OFER (+)
                     AND DET.NUM_UNID_ATEN > 0
                     AND DET.DCCA_OID_CABE = dc.OID_CABE
                     AND NOT EXISTS (select NULL
                                       from FAC_TIPO_OFERT_EXCLU TOE
                                      where TOE.TOFE_OID_TIPO_OFER = POD.TOFE_OID_TIPO_OFER)
                  )
         order by 1;

    -- Variables locales
    l_oidPais               NUMBER;
    l_oidPeriodo            NUMBER;
    l_oidCanal              NUMBER;
    l_oidMarca              NUMBER;
    l_oidPeriodoSgte        NUMBER;
    l_codPeriodoSgte        VARCHAR2(6);
    l_numeroLoteFacturacion NUMBER;
    l_oidActividad          NUMBER;

    TYPE facturarecord IS RECORD (
        codigo_pais       varchar2(2),
        codigo_marca      varchar2(1),
        codigo_cliente    mae_clien.cod_clie%type,
        nombre1           mae_clien.val_nom1%type,
        nombre2           mae_clien.val_nom2%type,
        apellido1         mae_clien.val_ape1%type,
        apellido2         mae_clien.val_ape2%type,
        direccion         fac_docum_conta_cabec.val_dire_comp%type,
        codigo_periodo    seg_perio_corpo.cod_peri%type,
        numero_pedido     ped_solic_cabec.val_nume_soli%type,
        fecha_facturacion varchar2(8),
        tasa_iva          ped_tasa_impue.val_tasa_impu%type,
        oid_cabecera      fac_docum_conta_cabec.oid_cabe%type,
        numero_interno    fac_regis_unico_venta.num_docu_cont_inte%type,
        codigo_zona       zon_zona.cod_zona%type,
        codigo_territorio zon_terri.cod_terr%type,
        num_peri_refe     varchar2(10),
        cod_peri_refe     varchar2(6),
        cod_inte_refe     varchar2(10),
        codigo_tipo_doc   fac_tipo_docum.cod_tipo_docu%type,
        pagina_actual     number,
        num_total_paginas number,
        des_moti          varchar2(25),
        total_productos   number,
        total_descuentos  number,
        total_ICE         number,
        base_imponible    fac_regis_unico_venta.val_base_impo_neto%type,
        total_IVA         fac_regis_unico_venta.imp_impu%type,
        importe_total     fac_regis_unico_venta.imp_tota%type,
        cedula            mae_clien_ident.num_docu_iden%type,
        siglaTipoDoc      mae_tipo_docum.val_sigl%type,
        SRI               number,
        mail              mae_clien_comun.val_text_comu%type
        );

    TYPE facturatype IS TABLE OF facturarecord;
    r_factura        facturatype;

    /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;

   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

   lbAbrirUtlFile      BOOLEAN;

BEGIN




    -- Obtenemos el OID del periodo
    l_oidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    l_oidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(CODIGO_CANAL);
    l_oidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(CODIGO_MARCA);
    l_oidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(pscodigoPeriodo, l_oidMarca, l_oidCanal);

    -- Obtenemos el OID del periodo siguiente
    l_codPeriodoSgte := GEN_FN_CALCU_PERIO(pscodigoPeriodo, 1);
    l_oidPeriodoSgte := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(l_codPeriodoSgte, l_oidMarca, l_oidCanal);


update fac_docum_conta_linea
set IMP_IMPU_TOTA_PROD_NACI=num_unid_aten*(select VAL_IMPU_PROD_NACI from INT_IMPUE_PRODU_NACIO where prod_oid_prod=fac_docum_conta_linea.prod_oid_prod)
where oid in
(
select b.oid
 from fac_docum_conta_cabec a, fac_docum_conta_linea b
where a.oid_cabe=b.dcca_oid_cabe
and a.perd_oid_peri=l_oidPeriodo and a.fec_fact=TO_DATE(psfechaFacturacion, 'DD/MM/YYYY')
and nvl(B.IMP_IMPU_TOTA_PROD_NACI,0)=0
and a.tido_oid_tipo_docu=1
and exists (select 1 from INT_IMPUE_PRODU_NACIO where prod_oid_prod=b.prod_oid_prod)
);






    -- Obtenemos el OID de la actividad
    SELECT ACT.OID_ACTI
    INTO l_oidActividad
    FROM CRA_ACTIV ACT
    WHERE ACT.PAIS_OID_PAIS = l_oidPais
    AND ACT.MARC_OID_MARC = l_oidMarca
    AND ACT.CANA_OID_CANA = l_oidCanal
    AND ACT.COD_ACTI = 'CV'; -- Cupon de Vencimiento

    -- Obtenemos el valor del ultimo numero de lote de facturacion
    BEGIN
      SELECT MAX(con.num_lote_fact)
      INTO l_numeroLoteFacturacion
      FROM ped_solic_cabec con,
           int_lar_tipo_solici_pedido_dis tspd
     WHERE con.perd_oid_peri = l_oidPeriodo
       AND con.fec_fact = to_date(psfechaFacturacion, 'dd/mm/yyyy')
       AND con.ind_inte_lari_gene = '1'--p_indicadorEnvioLarissa
       AND con.ind_ts_no_conso = 0
       AND (con.ind_pedi_prue = 0 OR con.ind_pedi_prue IS NULL)
       AND con.tspa_oid_tipo_soli_pais = tspd.tspa_oid_tipo_soli_pais
       AND con.pais_oid_pais = l_oidPais;
    EXCEPTION
    WHEN OTHERS THEN
        l_numeroLoteFacturacion := NULL;
    END;

    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;

    -- Abrimos el cursor
    OPEN c_facturas(l_oidPeriodo, l_oidPeriodoSgte, l_oidActividad, l_numeroLoteFacturacion);
    LOOP
        FETCH c_facturas BULK COLLECT INTO r_factura LIMIT w_filas;

        /* Procedimiento inicial para generar interfaz */
        IF lbAbrirUtlFile THEN
              GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                     psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
           lbAbrirUtlFile := FALSE;
        END IF;

        IF  r_factura.COUNT > 0 THEN
            FOR i IN r_factura.FIRST..r_factura.LAST LOOP

                lsLinea :=  r_factura(i).codigo_pais      ||';'||
                            r_factura(i).codigo_marca     ||';'||
                            r_factura(i).codigo_cliente   ||';'||
                            r_factura(i).nombre1          ||';'||
                            r_factura(i).nombre2          ||';'||
                            r_factura(i).apellido1        ||';'||
                            r_factura(i).apellido2        ||';'||
                            r_factura(i).direccion        ||';'||
                            r_factura(i).codigo_periodo   ||';'||
                            r_factura(i).numero_pedido    ||';'||
                            r_factura(i).fecha_facturacion||';'||
                            r_factura(i).tasa_iva         ||';'||
                            r_factura(i).oid_cabecera     ||';'||
                            r_factura(i).numero_interno   ||';'||
                            r_factura(i).codigo_zona      ||';'||
                            r_factura(i).codigo_territorio||';'||
                            null                          ||';'||
                            null                          ||';'||
                            null                          ||';'||
                            r_factura(i).codigo_tipo_doc  ||';'||
                            r_factura(i).pagina_actual    ||';'||
                            r_factura(i).num_total_paginas||';'||
                            null                          ||';'||
                            r_factura(i).total_productos  ||';'||
                            r_factura(i).total_descuentos ||';'||
                            r_factura(i).total_ICE        ||';'||
                            r_factura(i).base_imponible   ||';'||
                            r_factura(i).total_IVA        ||';'||
                            r_factura(i).importe_total    ||';'||
                            r_factura(i).cedula           ||';'||
                            substr(r_factura(i).siglaTipoDoc,0,3) ||';'||
                            r_factura(i).SRI ||';'||
                            r_factura(i).mail
                             ;

                UTL_FILE.PUT_LINE (V_HANDLE, lslinea);

            END LOOP;

        END IF;

        EXIT WHEN c_facturas%NOTFOUND;
    END LOOP;
    -- Cerramos el cursor
    CLOSE c_facturas;

    IF NOT lbAbrirUtlFile THEN
          utl_file.fclose(V_HANDLE);
         /* Procedimiento final para generar interfaz */
         GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;

  EXCEPTION
   WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_PROCE_FACTU_ELECT_EC: '||ls_sqlerrm);

END IMP_PR_PROCE_FACTU_ELECT_EC;

/**************************************************************************
Descripcion         : Proceso que genera la informacion de detalle de
                      facturas para Ecuador
Fecha Creación      : 02/07/2012
Autor               : Dennys Oliva Iriarte
***************************************************************************/
PROCEDURE IMP_PR_PROCE_FACTU_ELECT_ECDET(psCodigoPais        VARCHAR2,
                                         psCodigoSistema     VARCHAR2,
                                         psCodigoInterfaz    VARCHAR2,
                                         pscodigoPeriodo     VARCHAR2,
                                         psfechaFacturacion  VARCHAR2,
                                         psNombreArchivo     VARCHAR2) IS

    CURSOR c_facturas(oidPeriodo NUMBER,
                      oidPeriodoSgte NUMBER,
                      oidActividad NUMBER,
                      numeroLoteFacturacion NUMBER) IS
        select cl.dcca_oid_cabe id_cabecera,
               abs(cl.num_unid_aten) unidades_atendidas,
               mp.cod_sap codigo_sap,
               cl.val_prec_sin_impu_unit-(nvl(cl.imp_impu_tota_prod_naci,0)/cl.num_unid_aten) precio_sin_impuesto,
               decode(cl.val_prec_cata_unit_loca,0,cl.val_prec_cont_tota_loca, cl.val_prec_cata_tota_loca) precio_total,
               cl.imp_desc_unit_loca+cl.val_prec_cont_unit_loca descuento,
               decode(cl.val_prec_cata_unit_loca,0,0,cl.IMP_IMPU_TOTA_LOCA)+round(case when decode(cl.val_prec_cata_unit_loca,0,100,cl.VAL_PORC_DESC)=100 and nvl(cl.imp_impu_tota_prod_naci,0)>0 then cl.imp_impu_tota_prod_naci*0.12 else 0 end,2) IVA,
               cl.imp_impu_tota_prod_naci ICE ,
               ti.val_tasa_impu tasa_iva,
               decode(nvl(cl.imp_impu_tota_prod_naci,0),0,0,20) tasa_ice,
               nvl(sp.VAL_CODI_VENT, VAL_CODI_VENT_FICT) codigo_venta,
               decode(cl.val_prec_cata_unit_loca,0,100,cl.VAL_PORC_DESC) porcentaje_descuento,
               null redondeo,
               cl.val_prec_cata_unit_loca+cl.val_prec_cont_unit_loca  precio_catalogo,
               TRIM((SELECT VAL_I18N FROM GEN_I18N_SICC_PAIS WHERE ATTR_ENTI = 'MAE_PRODU' AND IDIO_OID_IDIO = 1 AND VAL_OID = cl.PROD_OID_PROD)) DES_PROD,
               case when cl.val_prec_cata_unit_loca=0 and cl.imp_impu_tota_prod_naci>0 then cl.imp_impu_tota_prod_naci
                    when cl.imp_impu_tota_prod_naci=0 then
                       decode(decode(cl.val_prec_cata_unit_loca,0,100,cl.VAL_PORC_DESC),100,0,
                       (cl.val_prec_sin_impu_tota_loca-nvl(cl.imp_impu_tota_prod_naci,0))-
                       round(case when cl.val_prec_cata_unit_loca=0 then cl.val_prec_sin_impu_unit-(nvl(cl.imp_impu_tota_prod_naci,0)/cl.num_unid_aten)
                            when nvl(cl.VAL_PORC_DESC,0)=0 then 0
                            when ((cl.val_prec_sin_impu_unit-(nvl(cl.imp_impu_tota_prod_naci,0)/cl.num_unid_aten))-((cl.imp_desc_unit_loca+cl.val_prec_cont_unit_loca)/1.12))*cl.num_unid_aten<0 then  ((cl.imp_desc_unit_loca+cl.val_prec_cont_unit_loca)/1.12)-(nvl(cl.imp_impu_tota_prod_naci,0)/cl.num_unid_aten)
                            else (cl.imp_desc_unit_loca+cl.val_prec_cont_unit_loca)/1.12
                       end,2)
                       )
                    else decode(cl.val_prec_cata_unit_loca,0,0,(
                         (cl.val_prec_sin_impu_unit-(nvl(cl.imp_impu_tota_prod_naci,0)/cl.num_unid_aten))*cl.num_unid_aten-round((nvl(cl.imp_desc_tota_loca,0)/1.12),2)+nvl(cl.imp_impu_tota_prod_naci,0)))
               end base_imponible,
               nvl(cl.imp_impu_tota_prod_naci,0)*100/20 base_imponible_ice,
               decode(cl.val_prec_cata_unit_loca,0,cl.val_prec_cont_unit_loca, cl.val_prec_cata_unit_loca) precio_unitario,
               --decode(cl.val_prec_cata_unit_loca,0,cl.val_prec_sin_impu_unit,cl.imp_desc_sin_impu_unit)-decode(decode(cl.val_prec_cata_unit_loca,0,100,cl.VAL_PORC_DESC),null,0,(nvl(cl.imp_impu_tota_prod_naci,0)/cl.num_unid_aten)) descuento_sin_impuesto,
               --(decode(cl.val_prec_cata_unit_loca,0,cl.val_prec_sin_impu_tota_loca,cl.imp_desc_sin_impu_tota_loca)-decode(decode(cl.val_prec_cata_unit_loca,0,100,cl.VAL_PORC_DESC),null,0,(nvl(cl.imp_impu_tota_prod_naci,0)))))
               round(case when cl.val_prec_cata_unit_loca=0 then cl.val_prec_sin_impu_unit-(nvl(cl.imp_impu_tota_prod_naci,0)/cl.num_unid_aten)
                    when nvl(cl.VAL_PORC_DESC,0)=0 then 0
                    when ((cl.val_prec_sin_impu_unit-(nvl(cl.imp_impu_tota_prod_naci,0)/cl.num_unid_aten))-((cl.imp_desc_unit_loca+cl.val_prec_cont_unit_loca)/1.12))*cl.num_unid_aten<0 then  ((cl.imp_desc_unit_loca+cl.val_prec_cont_unit_loca)/1.12)-(nvl(cl.imp_impu_tota_prod_naci,0)/cl.num_unid_aten)
                    else (cl.imp_desc_unit_loca+cl.val_prec_cont_unit_loca)/1.12
               end,2) descuento_sin_impuesto,
               --(cl.val_prec_sin_impu_tota_loca-nvl(cl.imp_impu_tota_prod_naci,0))-(decode(cl.val_prec_cata_unit_loca,0,cl.val_prec_sin_impu_tota_loca,cl.imp_desc_sin_impu_tota_loca)-decode(decode(cl.val_prec_cata_unit_loca,0,100,cl.VAL_PORC_DESC),null,0,(nvl(cl.imp_impu_tota_prod_naci,0)))) precio_sin_impuesto_total
               decode(decode(cl.val_prec_cata_unit_loca,0,100,cl.VAL_PORC_DESC),100,0,
               (cl.val_prec_sin_impu_tota_loca-nvl(cl.imp_impu_tota_prod_naci,0))-
               round(case when cl.val_prec_cata_unit_loca=0 then cl.val_prec_sin_impu_unit-(nvl(cl.imp_impu_tota_prod_naci,0)/cl.num_unid_aten)
                    when nvl(cl.VAL_PORC_DESC,0)=0 then 0
                    when ((cl.val_prec_sin_impu_unit-(nvl(cl.imp_impu_tota_prod_naci,0)/cl.num_unid_aten))-((cl.imp_desc_unit_loca+cl.val_prec_cont_unit_loca)/1.12))*cl.num_unid_aten<0 then  ((cl.imp_desc_unit_loca+cl.val_prec_cont_unit_loca)/1.12)-(nvl(cl.imp_impu_tota_prod_naci,0)/cl.num_unid_aten)
                    else (cl.imp_desc_unit_loca+cl.val_prec_cont_unit_loca)/1.12
               end,2)*cl.num_unid_aten
               )
               precio_sin_impuesto_total
               --cl.val_prec_sin_impu_tota_loca*abs(cl.num_unid_aten) precio_sin_impuesto_total
               from fac_docum_conta_cabec dc,
             ped_solic_cabec sc,
             fac_tipo_docum td,
             --cra_crono cc,
             zon_zona zz,
             ped_tasa_impue ti,
             fac_docum_conta_linea cl,
             ped_solic_posic sp,
             mae_produ mp--,
             --INT_IMPUE_PRODU_NACIO im
        where dc.perd_oid_peri = oidPeriodo  -- periodo actual
        and ti.oid_tasa_impu = sc.taim_oid_tasa_impu
        and cl.dcca_oid_cabe = dc.oid_cabe
        and sc.oid_soli_cabe = dc.soca_oid_soli_cabe
        and sp.oid_soli_posi = cl.sopo_oid_soli_posi
        and mp.oid_prod = cl.prod_oid_prod
        --and im.prod_oid_prod(+) = mp.oid_prod
        and zz.oid_zona = sc.zzon_oid_zona
        --and cc.zzon_oid_zona = sc.zzon_oid_zona -- join cronograma zona
        --and cc.cact_oid_acti = oidActividad    -- Existe vencimiento para la zona
        --and cc.perd_oid_peri = oidPeriodoSgte -- en la campaña siguiente
        and dc.tido_oid_tipo_docu = td.oid_tipo_docu
        and td.cod_tipo_docu = '001' -- FACTURA
        and dc.fec_fact = TO_DATE(psfechaFacturacion, 'DD/MM/YYYY')
        --and sc.ind_inte_lari_gene = '1'
        and cl.num_unid_aten != 0
        --and (numeroLoteFacturacion IS NULL OR sc.NUM_LOTE_FACT = numeroLoteFacturacion) --l_numeroLoteFacturacion
        and exists(select null
                    from FAC_DOCUM_CONTA_LINEA DET,
                         PED_SOLIC_POSIC PSP,
                         PRE_OFERT_DETAL POD
                   where DET.SOPO_OID_SOLI_POSI = PSP.OID_SOLI_POSI
                     AND PSP.OFDE_OID_DETA_OFER = POD.OID_DETA_OFER (+)
                     AND DET.NUM_UNID_ATEN > 0
                     AND DET.DCCA_OID_CABE = dc.OID_CABE
                     AND NOT EXISTS (select NULL
                                       from FAC_TIPO_OFERT_EXCLU TOE
                                      where TOE.TOFE_OID_TIPO_OFER = POD.TOFE_OID_TIPO_OFER)
                  )
        union all
        select dc.oid_cabe,
               1,
               '999999999',
               dc.imp_flet_sin_impu_tota_docu ,
               dc.imp_flet_tota_loca ,
               0,
               dc.imp_flet_tota_loca-dc.imp_flet_sin_impu_tota_docu,
               null,
               ti.val_tasa_impu tasa_iva,
               null,
               '00000',
               null,
               null,
               dc.imp_flet_tota_loca,
               'ADMINISTRACION MANEJO DE PRODUCTO',
               dc.imp_flet_sin_impu_tota_docu,
               0,
               dc.imp_flet_tota_loca,
               0,
               dc.imp_flet_sin_impu_tota_docu
        from fac_docum_conta_cabec dc,
             ped_solic_cabec sc ,
             fac_tipo_docum td,
             mae_clien m,
             ped_tasa_impue ti
        where sc.oid_soli_cabe = dc.soca_oid_soli_cabe
        and td.oid_tipo_docu = dc.tido_oid_tipo_docu
        and dc.perd_oid_peri = oidPeriodo  -- periodo actual
        and td.cod_tipo_docu = '001'
        and ti.oid_tasa_impu = sc.taim_oid_tasa_impu
        and m.oid_clie = sc.clie_oid_clie
        and dc.fec_fact = TO_DATE(psfechaFacturacion, 'DD/MM/YYYY')
        --and sc.ind_inte_lari_gene = '1'
        and IMP_FLET_TOTA_LOCA != 0 -- solo los q tengan monto
        --and (numeroLoteFacturacion IS NULL OR sc.NUM_LOTE_FACT = numeroLoteFacturacion) --l_numeroLoteFacturacion
        and exists(select null
                    from FAC_DOCUM_CONTA_LINEA DET,
                         PED_SOLIC_POSIC PSP,
                         PRE_OFERT_DETAL POD
                   where DET.SOPO_OID_SOLI_POSI = PSP.OID_SOLI_POSI
                     AND PSP.OFDE_OID_DETA_OFER = POD.OID_DETA_OFER (+)
                     AND DET.NUM_UNID_ATEN > 0
                     AND DET.DCCA_OID_CABE = dc.OID_CABE
                     AND NOT EXISTS (select NULL
                                       from FAC_TIPO_OFERT_EXCLU TOE
                                      where TOE.TOFE_OID_TIPO_OFER = POD.TOFE_OID_TIPO_OFER)
                  )
         order by 1;

    -- Variables locales
    l_oidPais               NUMBER;
    l_oidPeriodo            NUMBER;
    l_oidCanal              NUMBER;
    l_oidMarca              NUMBER;
    l_oidPeriodoSgte        NUMBER;
    l_codPeriodoSgte        VARCHAR2(6);
    l_numeroLoteFacturacion NUMBER;
    l_oidActividad          NUMBER;



    TYPE facturarecord IS RECORD (
        oid_cabecera             fac_docum_conta_cabec.oid_cabe%type,
        unidades_atendidas       number,
        codigo_sap               mae_produ.cod_sap%type,
        precio_sin_impuesto      number,
        precio_total             number,
        descuento_x_detalle      number,
        valor_IVA                fac_docum_conta_linea.imp_impu_tota_prod_naci%type,
        valor_ICE                number,
        tasa_iva                 number,
        tasa_ice                 number ,
        codigo_venta             ped_solic_posic.VAL_CODI_VENT%type,
        porcentaje_dscto         number,
        redondeo                 number,
        precio_catalogo          fac_docum_conta_linea.val_prec_cata_unit_loca%type  ,
        descripcion_prod         varchar2(1000),
        base_imponible_iva       number,
        base_imponible_ice       number,
        precio_unitario          number,
        desc_unit_sin_impu       number,
        prec_total_sin_impu      number
    );

    TYPE facturatype IS TABLE OF facturarecord;
    r_factura        facturatype;

    /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;

   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

   lbAbrirUtlFile      BOOLEAN;

BEGIN

    -- Obtenemos el OID del periodo
    l_oidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    l_oidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(CODIGO_CANAL);
    l_oidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(CODIGO_MARCA);
    l_oidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(pscodigoPeriodo, l_oidMarca, l_oidCanal);

    -- Obtenemos el OID del periodo siguiente
    l_codPeriodoSgte := GEN_FN_CALCU_PERIO(pscodigoPeriodo, 1);
    l_oidPeriodoSgte := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(l_codPeriodoSgte, l_oidMarca, l_oidCanal);

    -- Obtenemos el OID de la actividad
    SELECT ACT.OID_ACTI
    INTO l_oidActividad
    FROM CRA_ACTIV ACT
    WHERE ACT.PAIS_OID_PAIS = l_oidPais
    AND ACT.MARC_OID_MARC = l_oidMarca
    AND ACT.CANA_OID_CANA = l_oidCanal
    AND ACT.COD_ACTI = 'CV'; -- Cupon de Vencimiento

    -- Obtenemos el valor del ultimo numero de lote de facturacion
    BEGIN
      SELECT MAX(con.num_lote_fact)
      INTO l_numeroLoteFacturacion
      FROM ped_solic_cabec con,
           int_lar_tipo_solici_pedido_dis tspd
     WHERE con.perd_oid_peri = l_oidPeriodo
       AND con.fec_fact = to_date(psfechaFacturacion, 'dd/mm/yyyy')
       AND con.ind_inte_lari_gene = '1'--p_indicadorEnvioLarissa
       AND con.ind_ts_no_conso = 0
       AND (con.ind_pedi_prue = 0 OR con.ind_pedi_prue IS NULL)
       AND con.tspa_oid_tipo_soli_pais = tspd.tspa_oid_tipo_soli_pais
       AND con.pais_oid_pais = l_oidPais;
    EXCEPTION
    WHEN OTHERS THEN
        l_numeroLoteFacturacion := NULL;
    END;

    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;

    -- Abrimos el cursor
    OPEN c_facturas(l_oidPeriodo, l_oidPeriodoSgte, l_oidActividad, l_numeroLoteFacturacion);
    LOOP
        FETCH c_facturas BULK COLLECT INTO r_factura LIMIT w_filas;

        /* Procedimiento inicial para generar interfaz */
        IF lbAbrirUtlFile THEN
              GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                     psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
           lbAbrirUtlFile := FALSE;
        END IF;

        IF  r_factura.COUNT > 0 THEN
            FOR i IN r_factura.FIRST..r_factura.LAST LOOP

                lsLinea := r_factura(i).oid_cabecera         ||';'||
                           r_factura(i).unidades_atendidas   ||';'||
                           r_factura(i).codigo_sap           ||';'||
                           r_factura(i).precio_sin_impuesto  ||';'||
                           r_factura(i).precio_total         ||';'||
                           r_factura(i).descuento_x_detalle  ||';'||
                           r_factura(i).valor_IVA            ||';'||
                           r_factura(i).valor_ICE            ||';'||
                           r_factura(i).tasa_iva             ||';'||
                           r_factura(i).tasa_ice             ||';'||
                           r_factura(i).codigo_venta         ||';'||
                           r_factura(i).porcentaje_dscto     ||';'||
                           r_factura(i).redondeo             ||';'||
                           r_factura(i).precio_catalogo      ||';'||
                           r_factura(i).descripcion_prod     ||';'||
                           r_factura(i).base_imponible_iva   ||';'||
                           r_factura(i).base_imponible_ice   ||';'||
                           r_factura(i).precio_unitario      ||';'||
                           r_factura(i).desc_unit_sin_impu   ||';'||
                           r_factura(i).prec_total_sin_impu;

                UTL_FILE.PUT_LINE (V_HANDLE, lslinea);

            END LOOP;

        END IF;

        EXIT WHEN c_facturas%NOTFOUND;
    END LOOP;
    -- Cerramos el cursor
    CLOSE c_facturas;

    IF NOT lbAbrirUtlFile THEN
          utl_file.fclose(V_HANDLE);
         /* Procedimiento final para generar interfaz */
         GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;

  EXCEPTION
   WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_PROCE_FACTU_ELECT_ECDET: '||ls_sqlerrm);

END IMP_PR_PROCE_FACTU_ELECT_ECDET;

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Notas de Credito
                      para Ecuador
Fecha Creación      : 02/07/2012
Autor               : Dennys Oliva Iriarte
***************************************************************************/
PROCEDURE IMP_PR_PROCE_NOCRE_ELECT_EC(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      pscodigoPeriodo     VARCHAR2,
                                      psfechaFacturacion  VARCHAR2,
                                      psNombreArchivo     VARCHAR2) IS

    CURSOR c_notas_credito(oidMarca NUMBER,
                           oidCanal NUMBER) IS
       select  substr(sp.cod_pais,0,2) codigo_pais,
               substr(sp.cod_pais,3,1) codigo_marca,
               mc.cod_clie codigo_cliente,
               MC.VAL_NOM1 nombre_1,
               MC.VAL_NOM2 nombre_2,
               MC.VAL_APE1 apellido_1,
               MC.VAL_APE2 apellido_2,
               cab.val_dire_comp direccion,
               spc.cod_peri codigo_periodo,
               con.val_nume_soli numero_pedido,
               to_char(cab.fec_fact,'ddmmyyyy') fecha_facturacion,
               pti.val_tasa_impu tasa_iva,
               cab.oid_cabe oid_cabecera,
               cab.num_docu_cont_inte numero_interno,
               zon.cod_zona codigo_zona,
               ter.cod_terr codigo_territorio,
               conref.val_nume_soli numero_pedido_referencia,
               spcr.cod_peri periodo_referencia,
               nvl((select sc.num_docu_cont_inte
                  from fac_docum_conta_cabec sc
                 where sc.soca_oid_soli_cabe = conref.oid_soli_cabe
                   and tido_oid_tipo_docu = 1
                   and exists (select 1 from fac_docum_conta_linea m, fac_docum_conta_linea n where m.prod_oid_prod=n.prod_oid_prod and m.dcca_oid_cabe in (select oid_cabe from fac_docum_conta_cabec where soca_oid_soli_cabe=conref.oid_soli_cabe) and n.dcca_oid_cabe=cab.oid_cabe)
                   --and exists (select 1 from fac_docum_conta_linea m, fac_docum_conta_linea n where m.prod_oid_prod=n.prod_oid_prod and m.dcca_oid_cabe=sc.oid_cabe and n.dcca_oid_cabe=cab.oid_cabe)
                   and rownum=1
                   ),
               (select sc.num_docu_cont_inte
                  from fac_docum_conta_cabec sc
                 where sc.soca_oid_soli_cabe = conref.oid_soli_cabe
                   and tido_oid_tipo_docu = 1
                   --and exists (select 1 from fac_docum_conta_linea m, fac_docum_conta_linea n where m.prod_oid_prod=n.prod_oid_prod and m.dcca_oid_cabe in (select oid_cabe from fac_docum_conta_cabec where soca_oid_soli_cabe=conref.oid_soli_cabe) and n.dcca_oid_cabe=cab.oid_cabe)
                   --and exists (select 1 from fac_docum_conta_linea m, fac_docum_conta_linea n where m.prod_oid_prod=n.prod_oid_prod and m.dcca_oid_cabe=sc.oid_cabe and n.dcca_oid_cabe=cab.oid_cabe)
                   and rownum=1
                   )) cod_inte_refe,
               ftd.cod_tipo_docu tipo_documento,
               ROW_NUMBER() OVER (PARTITION BY con.VAL_NUME_SOLI ORDER BY cab.NUM_DOCU_CONT_INTE) pagina_actual,
               (SELECT count(1) FROM fac_docum_conta_cabec z, ped_solic_cabec Y
                 WHERE Y.VAL_NUME_SOLI =  con.val_nume_soli
                  and y.oid_soli_cabe = z.soca_oid_soli_cabe
                  and z.fec_fact = cab.fec_fact
                  and z.tido_oid_tipo_docu = cab.tido_oid_tipo_docu) numero_total_paginas,
               replace(gen_pkg_gener.GEN_FN_DEVUE_DESCR_TIPOS_SOLIC(con.tspa_oid_tipo_soli_pais),'Consolidado ','') motivo,
               round((select sum(decode(abs(cl.val_prec_cata_unit_loca),0,abs(cl.val_prec_cont_tota_loca), abs(cl.val_prec_cata_tota_loca)))
                   from fac_docum_conta_linea cl where cl.dcca_oid_cabe = cab.oid_cabe),2)+abs(nvl(cab.imp_flet_tota_loca,0)) total_productos,
               round((select sum(decode(abs(cl.val_prec_cata_unit_loca),0,abs(cl.val_prec_cont_unit_loca)*abs(cl.num_unid_aten), abs(nvl(cl.imp_desc_unit_loca,0)*abs(cl.num_unid_aten))))
                   from fac_docum_conta_linea cl where cl.dcca_oid_cabe = cab.oid_cabe),2) total_descuentos,
                round((select sum(abs(nvl(imp_impu_tota_prod_naci,0)))
                   from fac_docum_conta_linea cl
                  where cl.dcca_oid_cabe = cab.oid_cabe),2)  ICE,
             (
             select sum(
               case when cl.val_prec_cata_unit_loca=0 and abs(cl.imp_impu_tota_prod_naci)>0 then abs(cl.imp_impu_tota_prod_naci)
                    when cl.imp_impu_tota_prod_naci=0 then
                       decode(decode(cl.val_prec_cata_unit_loca,0,100,cl.VAL_PORC_DESC),100,0,
                       (abs(cl.val_prec_sin_impu_tota_loca)-nvl(abs(cl.imp_impu_tota_prod_naci),0))-
                       round(case when cl.val_prec_cata_unit_loca=0 then abs(cl.val_prec_sin_impu_unit)-(nvl(abs(cl.imp_impu_tota_prod_naci),0)/abs(cl.num_unid_aten))
                            when nvl(cl.VAL_PORC_DESC,0)=0 then 0
                            when ((abs(cl.val_prec_sin_impu_unit)-(nvl(abs(cl.imp_impu_tota_prod_naci),0)/abs(cl.num_unid_aten)))-((abs(cl.imp_desc_unit_loca)+abs(cl.val_prec_cont_unit_loca))/1.12))*abs(cl.num_unid_aten)<0 then  ((abs(cl.imp_desc_unit_loca)+abs(cl.val_prec_cont_unit_loca))/1.12)-(nvl(abs(cl.imp_impu_tota_prod_naci),0)/abs(cl.num_unid_aten))
                            else (abs(cl.imp_desc_unit_loca)+abs(cl.val_prec_cont_unit_loca))/1.12
                       end,2)
                       )
                    else decode(abs(cl.val_prec_cata_unit_loca),0,0,(
                         (abs(cl.val_prec_sin_impu_unit)-(nvl(abs(cl.imp_impu_tota_prod_naci),0)/abs(cl.num_unid_aten)))*abs(cl.num_unid_aten)-round((nvl(abs(cl.imp_desc_tota_loca),0)/1.12),2)+nvl(abs(cl.imp_impu_tota_prod_naci),0)))
               end
               )
             + decode(abs(cab.imp_flet_tota_loca),0,0,(abs(cab.imp_flet_sin_impu_tota_docu)))
             from fac_docum_conta_linea cl where num_unid_aten<>0 and cl.dcca_oid_cabe = cab.oid_cabe
             )
             base_imponible,
             (
             select sum(decode(cl.val_prec_cata_unit_loca,0,0,abs(cl.IMP_IMPU_TOTA_LOCA))+round(case when decode(abs(cl.val_prec_cata_unit_loca),0,100,cl.VAL_PORC_DESC)=100 and nvl(abs(cl.imp_impu_tota_prod_naci),0)>0 then abs(cl.imp_impu_tota_prod_naci)*0.12 else 0 end,2))
             + decode(abs(cab.imp_flet_tota_loca),0,0,(abs(cab.imp_flet_tota_loca)-abs(cab.imp_flet_sin_impu_tota_docu)))
             from fac_docum_conta_linea cl where num_unid_aten<>0 and cl.dcca_oid_cabe = cab.oid_cabe)
             IVA,
             abs(rv.imp_tota)+(
             select sum(round(case when decode(cl.val_prec_cata_unit_loca,0,100,cl.VAL_PORC_DESC)=100 and nvl(abs(cl.imp_impu_tota_prod_naci),0)>0 then abs(cl.imp_impu_tota_prod_naci)*0.12 else 0 end,2))
             from fac_docum_conta_linea cl where num_unid_aten<>0 and cl.dcca_oid_cabe = cab.oid_cabe)
             importe_total,
             ci.num_docu_iden  identificacion,
             mtd.val_sigl
       ,                 round((select sum(decode(cl.val_prec_cata_unit_loca,0,0,abs(cl.IMP_IMPU_TOTA_LOCA)))
                   from fac_docum_conta_linea cl where cl.dcca_oid_cabe = cab.oid_cabe),2) + nvl(( abs(imp_flet_tota_loca)-abs(imp_flet_sin_impu_tota_docu)),0) +
                round((select sum(
               (abs(cl.val_prec_sin_impu_tota_loca)-nvl(abs(cl.imp_impu_tota_prod_naci),0))-
               round(case when abs(cl.val_prec_cata_unit_loca)=0 then abs(cl.val_prec_sin_impu_unit)-(nvl(abs(cl.imp_impu_tota_prod_naci),0)/abs(cl.num_unid_aten))
                    when nvl(cl.VAL_PORC_DESC,0)=0 then 0
                    when ((abs(cl.val_prec_sin_impu_unit)-(nvl(abs(cl.imp_impu_tota_prod_naci),0)/abs(cl.num_unid_aten)))-((abs(cl.imp_desc_unit_loca)+abs(cl.val_prec_cont_unit_loca))/1.12))*abs(cl.num_unid_aten)<0 then  ((abs(cl.imp_desc_unit_loca)+abs(cl.val_prec_cont_unit_loca))/1.12)-(nvl(abs(cl.imp_impu_tota_prod_naci),0)/abs(cl.num_unid_aten))
                    else (abs(cl.imp_desc_unit_loca)+abs(cl.val_prec_cont_unit_loca))/1.12
               end,2) *abs(cl.num_unid_aten)
                    )
                   from fac_docum_conta_linea cl where num_unid_aten<>0 and cl.dcca_oid_cabe = cab.oid_cabe),2) + abs(nvl(imp_flet_sin_impu_tota_docu,0)) +
                round((select sum(nvl(abs(imp_impu_tota_prod_naci),0))
                   from fac_docum_conta_linea cl
                  where cl.dcca_oid_cabe = cab.oid_cabe),2)+(
             select sum(round(case when decode(abs(cl.val_prec_cata_unit_loca),0,100,cl.VAL_PORC_DESC)=100 and nvl(abs(cl.imp_impu_tota_prod_naci),0)>0 then abs(cl.imp_impu_tota_prod_naci)*0.12 else 0 end,2))
             from fac_docum_conta_linea cl where num_unid_aten<>0 and cl.dcca_oid_cabe = cab.oid_cabe)
              SRI,
              (select c.val_text_comu from mae_clien_comun c where clie_oid_clie=mc.oid_clie and c.ticm_oid_tipo_comu=3) mail
        FROM FAC_DOCUM_CONTA_CABEC CAB,
             FAC_TIPO_DOCUM FTD,
             MAE_CLIEN MC,
             PED_SOLIC_CABEC CON,
             PED_SOLIC_CABEC CONREF,
             PED_TASA_IMPUE PTI,
             ZON_REGIO REG,
             ZON_ZONA ZON,
             ZON_SECCI SEC,
             ZON_TERRI TER,
             SEG_PAIS SP,
             CRA_PERIO CP,
             SEG_PERIO_CORPO SPC,
             CRA_PERIO CPR,
             SEG_PERIO_CORPO SPCR,
             fac_regis_unico_venta rv,
             mae_clien_ident ci,
             mae_tipo_docum mtd
        WHERE SP.OID_PAIS = MC.PAIS_OID_PAIS
        and rv.dcca_oid_cabe = cab.oid_cabe
        AND MC.OID_CLIE = CON.CLIE_OID_CLIE
        and ci.clie_oid_clie = mc.oid_clie
        and ci.val_iden_docu_prin = 1
        and mtd.oid_tipo_docu = ci.tdoc_oid_tipo_docu
        AND CON.OID_SOLI_CABE = CAB.SOCA_OID_SOLI_CABE
        AND CON.TAIM_OID_TASA_IMPU = PTI.OID_TASA_IMPU
        AND CON.SOCA_OID_DOCU_REFE = CONREF.OID_SOLI_CABE (+)
        AND CONREF.PERD_OID_PERI = CPR.OID_PERI (+)
        AND CPR.PERI_OID_PERI = SPCR.OID_PERI (+)
        AND CAB.TIDO_OID_TIPO_DOCU = FTD.OID_TIPO_DOCU
        AND CAB.ZORG_OID_REGI = REG.OID_REGI
        AND CAB.ZZON_OID_ZONA = ZON.OID_ZONA
        AND CAB.ZSCC_OID_SECC = SEC.OID_SECC
        AND CAB.TERR_OID_TERR = TER.OID_TERR
        AND CAB.PERD_OID_PERI = CP.OID_PERI
        AND CP.PERI_OID_PERI = SPC.OID_PERI
        --AND CP.CANA_OID_CANA = oidCanal--2001
        --AND CP.MARC_OID_MARC = oidMarca--2003
        --AND CPR.CANA_OID_CANA (+) = oidCanal--2001
        --AND CPR.MARC_OID_MARC (+) = oidMarca--2003
        AND SP.COD_PAIS = psCodigoPais
        AND FTD.COD_TIPO_DOCU = '021' -- NOTAS DE CREDITO
        and to_char(cab.fec_fact,'dd/mm/yyyy') = psfechaFacturacion
        AND EXISTS (SELECT NULL
                    FROM FAC_DOCUM_CONTA_LINEA LIN
                    WHERE LIN.DCCA_OID_CABE = CAB.OID_CABE
                    AND LIN.NUM_UNID_ATEN != 0)
        order by 1;

    -- Variables locales
    l_oidCanal              NUMBER;
    l_oidMarca              NUMBER;

    TYPE facturarecord IS RECORD (
        codigo_pais       varchar2(2),
        codigo_marca      varchar2(1),
        codigo_cliente    mae_clien.cod_clie%type,
        nombre1           mae_clien.val_nom1%type,
        nombre2           mae_clien.val_nom2%type,
        apellido1         mae_clien.val_ape1%type,
        apellido2         mae_clien.val_ape2%type,
        direccion         fac_docum_conta_cabec.val_dire_comp%type,
        codigo_periodo    seg_perio_corpo.cod_peri%type,
        numero_pedido     ped_solic_cabec.val_nume_soli%type,
        fecha_facturacion varchar2(8),
        tasa_iva          ped_tasa_impue.val_tasa_impu%type,
        oid_cabecera      fac_docum_conta_cabec.oid_cabe%type,
        numero_interno    fac_regis_unico_venta.num_docu_cont_inte%type,
        codigo_zona              zon_zona.cod_zona%type,
        codigo_territorio        zon_terri.cod_terr%type,
        numero_pedido_referencia ped_solic_cabec.val_nume_soli%type,
        periodo_referencia seg_perio_corpo.cod_peri%type,
        tipo_documento_referencia fac_regis_unico_venta.num_docu_cont_inte%type,
        codigo_tipo_doc   fac_tipo_docum.cod_tipo_docu%type,
        pagina_actual     number,
        num_total_paginas number,
        motivo            varchar2(200),
        total_productos   number,
        total_descuentos  number,
        total_ICE         number,
        base_imponible    fac_regis_unico_venta.val_base_impo_neto%type,
        total_IVA         fac_regis_unico_venta.imp_impu%type,
        importe_total     fac_regis_unico_venta.imp_tota%type,
        cedula            mae_clien_ident.num_docu_iden%type,
        siglaTipoDoc      mae_tipo_docum.val_sigl%type,
        SRI               number,
        mail              mae_clien_comun.val_text_comu%type
    );

    TYPE facturatype IS TABLE OF facturarecord;
    r_notas_credito        facturatype;

    /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;

   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

   lbAbrirUtlFile      BOOLEAN;

BEGIN

    -- Obtenemos el OIDs
    l_oidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(CODIGO_CANAL);
    l_oidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(CODIGO_MARCA);

    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;

    -- Abrimos el cursor
    OPEN c_notas_credito(l_oidMarca,l_oidCanal);
    LOOP
        FETCH c_notas_credito BULK COLLECT INTO r_notas_credito LIMIT w_filas;

        /* Procedimiento inicial para generar interfaz */
        IF lbAbrirUtlFile THEN
              GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                     psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
           lbAbrirUtlFile := FALSE;
        END IF;

        IF  r_notas_credito.COUNT > 0 THEN
            FOR i IN r_notas_credito.FIRST..r_notas_credito.LAST LOOP

                lsLinea :=  r_notas_credito(i).codigo_pais      ||';'||
                            r_notas_credito(i).codigo_marca     ||';'||
                            r_notas_credito(i).codigo_cliente   ||';'||
                            r_notas_credito(i).nombre1          ||';'||
                            r_notas_credito(i).nombre2          ||';'||
                            r_notas_credito(i).apellido1        ||';'||
                            r_notas_credito(i).apellido2        ||';'||
                            r_notas_credito(i).direccion        ||';'||
                            r_notas_credito(i).codigo_periodo   ||';'||
                            r_notas_credito(i).numero_pedido    ||';'||
                            r_notas_credito(i).fecha_facturacion||';'||
                            r_notas_credito(i).tasa_iva         ||';'||
                            r_notas_credito(i).oid_cabecera     ||';'||
                            r_notas_credito(i).numero_interno   ||';'||
                            r_notas_credito(i).codigo_zona      ||';'||
                            r_notas_credito(i).codigo_territorio||';'||
                            r_notas_credito(i).numero_pedido_referencia  ||';'||
                            r_notas_credito(i).periodo_referencia        ||';'||
                            r_notas_credito(i).tipo_documento_referencia ||';'||
                            r_notas_credito(i).codigo_tipo_doc  ||';'||
                            r_notas_credito(i).pagina_actual    ||';'||
                            r_notas_credito(i).num_total_paginas||';'||
                            r_notas_credito(i).motivo           ||';'||
                            r_notas_credito(i).total_productos  ||';'||
                            r_notas_credito(i).total_descuentos ||';'||
                            r_notas_credito(i).total_ICE        ||';'||
                            r_notas_credito(i).base_imponible   ||';'||
                            r_notas_credito(i).total_IVA        ||';'||
                            r_notas_credito(i).importe_total    ||';'||
                            r_notas_credito(i).cedula           ||';'||
                            substr(r_notas_credito(i).siglaTipoDoc,0,3) ||';'||
                            r_notas_credito(i).sri ||';'||
                            r_notas_credito(i).mail ;

                UTL_FILE.PUT_LINE (V_HANDLE, lslinea);

            END LOOP;

        END IF;

        EXIT WHEN c_notas_credito%NOTFOUND;
    END LOOP;
    -- Cerramos el cursor
    CLOSE c_notas_credito;

    IF NOT lbAbrirUtlFile THEN
          utl_file.fclose(V_HANDLE);
         /* Procedimiento final para generar interfaz */
         GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;

  EXCEPTION
   WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_PROCE_NOCRE_ELECT_EC: '||ls_sqlerrm);

END IMP_PR_PROCE_NOCRE_ELECT_EC;

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Notas de Credito
                      para Ecuador
Fecha Creación      : 02/07/2012
Autor               : Dennys Oliva Iriarte
***************************************************************************/
PROCEDURE IMP_PR_PROCE_NOCRE_ELECT_ECDET(psCodigoPais        VARCHAR2,
                                         psCodigoSistema     VARCHAR2,
                                         psCodigoInterfaz    VARCHAR2,
                                         pscodigoPeriodo     VARCHAR2,
                                         psfechaFacturacion  VARCHAR2,
                                         psNombreArchivo     VARCHAR2) IS

    CURSOR c_notas_credito(oidMarca NUMBER,
                           oidCanal NUMBER) IS
          select cl.dcca_oid_cabe id_cabecera,
                 abs(cl.num_unid_aten) unidades_atendidas,
                 mp.cod_sap codigo_sap,
               abs(cl.val_prec_sin_impu_unit)-(nvl(abs(cl.imp_impu_tota_prod_naci),0)/abs(cl.num_unid_aten)) precio_sin_impuesto,
               decode(abs(cl.val_prec_cata_unit_loca),0,abs(cl.val_prec_cont_tota_loca), abs(cl.val_prec_cata_tota_loca)) precio_total,
               abs(cl.imp_desc_unit_loca)+abs(cl.val_prec_cont_unit_loca) descuento,
               decode(abs(cl.val_prec_cata_unit_loca),0,0,abs(cl.IMP_IMPU_TOTA_LOCA))+round(case when decode(cl.val_prec_cata_unit_loca,0,100,cl.VAL_PORC_DESC)=100 and nvl(abs(cl.imp_impu_tota_prod_naci),0)>0 then abs(cl.imp_impu_tota_prod_naci)*0.12 else 0 end,2) IVA,
               abs(cl.imp_impu_tota_prod_naci) ICE ,
               pti.val_tasa_impu tasa_iva,
               decode(abs(cl.imp_impu_tota_prod_naci),null,0,20) tasa_ice,
               nvl(nvl(spp.VAL_CODI_VENT, VAL_CODI_VENT_FICT),substr(mp.cod_sap,1,5)) codigo_venta,
               decode(abs(cl.val_prec_cata_unit_loca),0,100,nvl(cl.VAL_PORC_DESC,0)) porcentaje_descuento,
               null redondeo,
               abs(cl.val_prec_cata_unit_loca)+abs(cl.val_prec_cont_unit_loca)  precio_catalogo,
               TRIM((SELECT VAL_I18N FROM GEN_I18N_SICC_PAIS WHERE ATTR_ENTI = 'MAE_PRODU' AND IDIO_OID_IDIO = 1 AND VAL_OID = cl.PROD_OID_PROD)) DES_PROD,
               --((decode(cl.val_prec_cata_unit_loca,0,cl.val_prec_cont_unit_loca, cl.val_prec_cata_unit_loca)-IMP_DESC_UNIT_LOCA)*100/112) base_imponible
               --decode(cl.val_prec_cata_unit_loca,0,0,cl.val_prec_sin_impu_unit-cl.imp_desc_sin_impu_tota_loca)+nvl(cl.imp_impu_tota_prod_naci,0) base_imponible,
               case when cl.val_prec_cata_unit_loca=0 and abs(cl.imp_impu_tota_prod_naci)>0 then abs(cl.imp_impu_tota_prod_naci)
                    when cl.imp_impu_tota_prod_naci=0 then
                       decode(decode(abs(cl.val_prec_cata_unit_loca),0,100,cl.VAL_PORC_DESC),100,0,
                       (abs(cl.val_prec_sin_impu_tota_loca)-nvl(abs(cl.imp_impu_tota_prod_naci),0))-
                       round(case when abs(cl.val_prec_cata_unit_loca)=0 then abs(cl.val_prec_sin_impu_unit)-(nvl(abs(cl.imp_impu_tota_prod_naci),0)/abs(cl.num_unid_aten))
                            when nvl(cl.VAL_PORC_DESC,0)=0 then 0
                            when ((abs(cl.val_prec_sin_impu_unit)-(nvl(abs(cl.imp_impu_tota_prod_naci),0)/abs(cl.num_unid_aten)))-((abs(cl.imp_desc_unit_loca)+abs(cl.val_prec_cont_unit_loca))/1.12))*abs(cl.num_unid_aten)<0 then  ((abs(cl.imp_desc_unit_loca)+abs(cl.val_prec_cont_unit_loca))/1.12)-(nvl(abs(cl.imp_impu_tota_prod_naci),0)/abs(cl.num_unid_aten))
                            else (abs(cl.imp_desc_unit_loca)+abs(cl.val_prec_cont_unit_loca))/1.12
                       end,2)
                       )
                    else decode(cl.val_prec_cata_unit_loca,0,0,(
                         (abs(cl.val_prec_sin_impu_unit)-(nvl(abs(cl.imp_impu_tota_prod_naci),0)/abs(cl.num_unid_aten)))*abs(cl.num_unid_aten)-round((nvl(abs(cl.imp_desc_tota_loca),0)/1.12),2)+nvl(abs(cl.imp_impu_tota_prod_naci),0)))
               end base_imponible,
               nvl(abs(cl.imp_impu_tota_prod_naci),0)*100/20 base_imponible_ice,
               decode(abs(cl.val_prec_cata_unit_loca),0,abs(cl.val_prec_cont_unit_loca), abs(cl.val_prec_cata_unit_loca)) precio_unitario,
               round(case when cl.val_prec_cata_unit_loca=0 then abs(cl.val_prec_sin_impu_unit)-(nvl(abs(cl.imp_impu_tota_prod_naci),0)/abs(cl.num_unid_aten))
                    when nvl(cl.VAL_PORC_DESC,0)=0 then 0
                    when ((abs(cl.val_prec_sin_impu_unit)-(nvl(abs(cl.imp_impu_tota_prod_naci),0)/abs(cl.num_unid_aten)))-((abs(cl.imp_desc_unit_loca)+abs(cl.val_prec_cont_unit_loca))/1.12))*abs(cl.num_unid_aten)<0 then  ((abs(cl.imp_desc_unit_loca)+abs(cl.val_prec_cont_unit_loca))/1.12)-(nvl(abs(cl.imp_impu_tota_prod_naci),0)/abs(cl.num_unid_aten))
                    else (abs(cl.imp_desc_unit_loca)+abs(cl.val_prec_cont_unit_loca))/1.12
               end,2) descuento_sin_impuesto,
               decode(decode(cl.val_prec_cata_unit_loca,0,100,cl.VAL_PORC_DESC),100,0,
               (abs(cl.val_prec_sin_impu_tota_loca)-nvl(abs(cl.imp_impu_tota_prod_naci),0))-
               round(case when cl.val_prec_cata_unit_loca=0 then abs(cl.val_prec_sin_impu_unit)-(nvl(abs(cl.imp_impu_tota_prod_naci),0)/abs(cl.num_unid_aten))
                    when nvl(cl.VAL_PORC_DESC,0)=0 then 0
                    when ((abs(cl.val_prec_sin_impu_unit)-(nvl(abs(cl.imp_impu_tota_prod_naci),0)/abs(cl.num_unid_aten)))-((abs(cl.imp_desc_unit_loca)+abs(cl.val_prec_cont_unit_loca))/1.12))*abs(cl.num_unid_aten)<0 then  ((abs(cl.imp_desc_unit_loca)+abs(cl.val_prec_cont_unit_loca))/1.12)-(nvl(abs(cl.imp_impu_tota_prod_naci),0)/abs(cl.num_unid_aten))
                    else (abs(cl.imp_desc_unit_loca)+abs(cl.val_prec_cont_unit_loca))/1.12
               end,2) *abs(cl.num_unid_aten)
               )
               precio_sin_impuesto_total
               --(cl.val_prec_sin_impu_tota_loca*abs(cl.num_unid_aten)) precio_sin_impuesto_total
          FROM FAC_DOCUM_CONTA_CABEC CAB,
               FAC_DOCUM_CONTA_LINEA CL,
               FAC_TIPO_DOCUM FTD,
               MAE_CLIEN MC,
               PED_SOLIC_CABEC CON,
               PED_SOLIC_CABEC CONREF,
               PED_TASA_IMPUE PTI,
               ZON_REGIO REG,
               ZON_ZONA ZON,
               ZON_SECCI SEC,
               ZON_TERRI TER,
               SEG_PAIS SP,
               CRA_PERIO CP,
               SEG_PERIO_CORPO SPC,
               CRA_PERIO CPR,
               SEG_PERIO_CORPO SPCR,
               fac_regis_unico_venta rv,
               mae_produ mp,
               --INT_IMPUE_PRODU_NACIO im,
               ped_solic_posic spp
          WHERE SP.OID_PAIS = MC.PAIS_OID_PAIS
          and cl.dcca_oid_cabe = cab.oid_cabe
          and rv.dcca_oid_cabe = cab.oid_cabe
          and cl.prod_oid_prod = mp.oid_prod
          --and im.prod_oid_prod(+) = mp.oid_prod
          AND MC.OID_CLIE = CON.CLIE_OID_CLIE
          AND CON.OID_SOLI_CABE = CAB.SOCA_OID_SOLI_CABE
          and spp.oid_soli_posi = cl.sopo_oid_soli_posi
          AND CON.TAIM_OID_TASA_IMPU = PTI.OID_TASA_IMPU
          AND CON.SOCA_OID_DOCU_REFE = CONREF.OID_SOLI_CABE (+)
          AND CONREF.PERD_OID_PERI = CPR.OID_PERI (+)
          AND CPR.PERI_OID_PERI = SPCR.OID_PERI (+)
          AND CAB.TIDO_OID_TIPO_DOCU = FTD.OID_TIPO_DOCU
          AND CAB.ZORG_OID_REGI = REG.OID_REGI
          AND CAB.ZZON_OID_ZONA = ZON.OID_ZONA
          AND CAB.ZSCC_OID_SECC = SEC.OID_SECC
          AND CAB.TERR_OID_TERR = TER.OID_TERR
          AND CAB.PERD_OID_PERI = CP.OID_PERI
          AND CP.PERI_OID_PERI = SPC.OID_PERI
          --AND CP.CANA_OID_CANA = oidCanal
          --AND CP.MARC_OID_MARC = oidMarca
          --AND CPR.CANA_OID_CANA (+) = oidCanal
          --AND CPR.MARC_OID_MARC (+) = oidMarca
          AND SP.COD_PAIS = psCodigoPais
          AND FTD.COD_TIPO_DOCU = '021' -- NOTAS DE CREDITO
          and to_char(cab.fec_fact,'dd/mm/yyyy') = psfechaFacturacion
          AND cl.NUM_UNID_ATEN != 0
          union all
        select dc.oid_cabe,
               1,
               '999999999',
               abs(dc.imp_flet_sin_impu_tota_docu) ,
               abs(dc.imp_flet_tota_loca) ,
               0,
               abs(dc.imp_flet_tota_loca-dc.imp_flet_sin_impu_tota_docu),
               null,
               ti.val_tasa_impu tasa_iva,
               null,
               '00000',
               null,
               null,
               abs(dc.imp_flet_tota_loca),
               'ADMINISTRACION MANEJO DE PRODUCTO',
               abs(dc.imp_flet_sin_impu_tota_docu),
               0,
               abs(dc.imp_flet_tota_loca),
               0,
               abs(dc.imp_flet_sin_impu_tota_docu)
        from fac_docum_conta_cabec dc,
             ped_solic_cabec sc ,
             fac_tipo_docum td,
             mae_clien m,
             ped_tasa_impue ti
        where sc.oid_soli_cabe = dc.soca_oid_soli_cabe
        and td.oid_tipo_docu = dc.tido_oid_tipo_docu
        --and dc.perd_oid_peri = oidPeriodo  -- periodo actual
        and td.cod_tipo_docu = '021'
        and ti.oid_tasa_impu = sc.taim_oid_tasa_impu
        and m.oid_clie = sc.clie_oid_clie
        and dc.fec_fact = TO_DATE(psfechaFacturacion, 'DD/MM/YYYY')
        --and sc.ind_inte_lari_gene = '1'
        and IMP_FLET_TOTA_LOCA != 0 -- solo los q tengan monto
        --and (numeroLoteFacturacion IS NULL OR sc.NUM_LOTE_FACT = numeroLoteFacturacion) --l_numeroLoteFacturacion
        /*and exists(select null
                    from FAC_DOCUM_CONTA_LINEA DET,
                         PED_SOLIC_POSIC PSP,
                         PRE_OFERT_DETAL POD
                   where DET.SOPO_OID_SOLI_POSI = PSP.OID_SOLI_POSI
                     AND PSP.OFDE_OID_DETA_OFER = POD.OID_DETA_OFER (+)
                     AND DET.NUM_UNID_ATEN > 0
                     AND DET.DCCA_OID_CABE = dc.OID_CABE
                     AND NOT EXISTS (select NULL
                                       from FAC_TIPO_OFERT_EXCLU TOE
                                      where TOE.TOFE_OID_TIPO_OFER = POD.TOFE_OID_TIPO_OFER)
                  )*/
         order by 1;
    -- Variables locales
    l_oidCanal              NUMBER;
    l_oidMarca              NUMBER;

    TYPE facturarecord IS RECORD (
        oid_cabecera             fac_docum_conta_cabec.oid_cabe%type,
        unidades_atendidas       number,
        codigo_sap               mae_produ.cod_sap%type,
        precio_sin_impuesto      number,
        precio_total             number,
        descuento_x_detalle      number,
        valor_IVA                fac_docum_conta_linea.imp_impu_tota_prod_naci%type,
        valor_ICE                number,
        tasa_iva                 number,
        tasa_ice                 number ,
        codigo_venta             ped_solic_posic.VAL_CODI_VENT%type,
        porcentaje_dscto         number,
        redondeo                 number,
        precio_catalogo          fac_docum_conta_linea.val_prec_cata_unit_loca%type  ,
        descripcion_prod         varchar2(1000),
        base_imponible_iva       number,
        base_imponible_ice       number,
        precio_unitario          number,
        desc_unit_sin_impu       number,
        prec_total_sin_impu      number
    );

    TYPE facturatype IS TABLE OF facturarecord;
    r_notas_credito        facturatype;

    /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;

   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

   lbAbrirUtlFile      BOOLEAN;

BEGIN

    -- Obtenemos el OIDs
    l_oidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(CODIGO_CANAL);
    l_oidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(CODIGO_MARCA);

    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;

    -- Abrimos el cursor
    OPEN c_notas_credito(l_oidMarca,l_oidCanal);
    LOOP
        FETCH c_notas_credito BULK COLLECT INTO r_notas_credito LIMIT w_filas;

        /* Procedimiento inicial para generar interfaz */
        IF lbAbrirUtlFile THEN
              GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                     psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
           lbAbrirUtlFile := FALSE;
        END IF;

        IF  r_notas_credito.COUNT > 0 THEN
            FOR i IN r_notas_credito.FIRST..r_notas_credito.LAST LOOP

                lsLinea := r_notas_credito(i).oid_cabecera        ||';'||
                           r_notas_credito(i).unidades_atendidas   ||';'||
                           r_notas_credito(i).codigo_sap           ||';'||
                           r_notas_credito(i).precio_sin_impuesto  ||';'||
                           r_notas_credito(i).precio_total         ||';'||
                           r_notas_credito(i).descuento_x_detalle  ||';'||
                           r_notas_credito(i).valor_IVA            ||';'||
                           r_notas_credito(i).valor_ICE            ||';'||
                           r_notas_credito(i).tasa_iva             ||';'||
                           r_notas_credito(i).tasa_ice             ||';'||
                           r_notas_credito(i).codigo_venta         ||';'||
                           r_notas_credito(i).porcentaje_dscto     ||';'||
                           r_notas_credito(i).redondeo             ||';'||
                           r_notas_credito(i).precio_catalogo      ||';'||
                           r_notas_credito(i).descripcion_prod     ||';'||
                           r_notas_credito(i).base_imponible_iva   ||';'||
                           r_notas_credito(i).base_imponible_ice   ||';'||
                           r_notas_credito(i).precio_unitario      ||';'||
                           r_notas_credito(i).desc_unit_sin_impu   ||';'||
                           r_notas_credito(i).prec_total_sin_impu;

                UTL_FILE.PUT_LINE (V_HANDLE, lslinea);

            END LOOP;

        END IF;

        EXIT WHEN c_notas_credito%NOTFOUND;
    END LOOP;
    -- Cerramos el cursor
    CLOSE c_notas_credito;

    IF NOT lbAbrirUtlFile THEN
          utl_file.fclose(V_HANDLE);
         /* Procedimiento final para generar interfaz */
         GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;

  EXCEPTION
   WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_PROCE_NOCRE_ELECT_ECDET: '||ls_sqlerrm);

END IMP_PR_PROCE_NOCRE_ELECT_ECDET;

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Detalles
                      para Ecuador
Fecha Creación      : 02/07/2012
Autor               : Dennys Oliva Iriarte
***************************************************************************/
PROCEDURE IMP_PR_PROCE_DETAL_ELECT_EC(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      pscodigoPeriodo     VARCHAR2,
                                      psfechaFacturacion  VARCHAR2,
                                      psNombreArchivo     VARCHAR2) IS

    CURSOR c_facturas(oidPeriodo NUMBER,
                      oidPeriodoSgte NUMBER,
                      oidActividad NUMBER,
                      numeroLoteFacturacion NUMBER) IS
          select distinct
                 mp.cod_sap codigo_sap,
                 TRIM((SELECT VAL_I18N FROM GEN_I18N_SICC_PAIS WHERE ATTR_ENTI = 'MAE_PRODU' AND IDIO_OID_IDIO = 1 AND VAL_OID = cl.PROD_OID_PROD)) descripcion,
                 mp.val_grup_arti grupo_articulo,
                 g.val_i18n familia,
                 null importado,
                 sm.des_marc_prod marca,
                 ti.val_tasa_impu tasa_iva,
                 decode(cl.imp_impu_tota_prod_naci,null,0,20) porcentaje_ICE
                 ---round((im.val_impu_prod_naci*100)/(decode(cl.val_prec_cata_unit_loca,0,cl.val_prec_cont_unit_loca, cl.val_prec_cata_unit_loca)),2) porcentaje_ICE
          from fac_docum_conta_cabec dc,
               ped_solic_cabec sc,
               fac_tipo_docum td,
               cra_crono cc,
               zon_zona zz,
               ped_tasa_impue ti,
               fac_docum_conta_linea cl,
               ped_solic_posic sp,
               seg_pais pa,
               mae_produ mp,
               seg_marca_produ sm,
               MAE_NEGOC mn,
               gen_i18n_sicc_pais g,
               INT_IMPUE_PRODU_NACIO im
          where dc.perd_oid_peri = oidPeriodo  -- periodo actual
          and ti.oid_tasa_impu = sc.taim_oid_tasa_impu
          and cl.dcca_oid_cabe = dc.oid_cabe
          and mp.mapr_oid_marc_prod(+) = sm.oid_marc_prod
          and pa.oid_pais = sc.pais_oid_pais
          and sc.oid_soli_cabe = dc.soca_oid_soli_cabe
          and sp.oid_soli_posi = cl.sopo_oid_soli_posi
          and mp.oid_prod = cl.prod_oid_prod
          and im.prod_oid_prod(+) = mp.oid_prod
         and mp.nego_oid_nego = mn.oid_nego
         and g.attr_enti = 'MAE_NEGOC'
         and g.val_oid = mn.oid_nego
          and zz.oid_zona = sc.zzon_oid_zona
          and cc.zzon_oid_zona = sc.zzon_oid_zona -- join cronograma zona
          and cc.cact_oid_acti = oidActividad    -- Existe vencimiento para la zona
          and cc.perd_oid_peri = oidPeriodoSgte -- en la campaña siguiente
          and dc.tido_oid_tipo_docu = td.oid_tipo_docu
          and td.cod_tipo_docu = '001'
          and dc.fec_fact = TO_DATE(psfechaFacturacion, 'DD/MM/YYYY')
          --and sc.ind_inte_lari_gene = '1'
          and (numeroLoteFacturacion IS NULL OR sc.NUM_LOTE_FACT = numeroLoteFacturacion) --l_numeroLoteFacturacion
          and exists(select null
                      from FAC_DOCUM_CONTA_LINEA DET,
                           PED_SOLIC_POSIC PSP,
                           PRE_OFERT_DETAL POD
                     where DET.SOPO_OID_SOLI_POSI = PSP.OID_SOLI_POSI
                       AND PSP.OFDE_OID_DETA_OFER = POD.OID_DETA_OFER (+)
                       AND DET.NUM_UNID_ATEN > 0
                       AND DET.DCCA_OID_CABE = dc.OID_CABE
                       AND NOT EXISTS (select NULL
                                         from FAC_TIPO_OFERT_EXCLU TOE
                                        where TOE.TOFE_OID_TIPO_OFER = POD.TOFE_OID_TIPO_OFER)
                    );


    -- Variables locales
    l_oidPais               NUMBER;
    l_oidPeriodo            NUMBER;
    l_oidCanal              NUMBER;
    l_oidMarca              NUMBER;
    l_oidPeriodoSgte        NUMBER;
    l_codPeriodoSgte        VARCHAR2(6);
    l_numeroLoteFacturacion NUMBER;
    l_oidActividad          NUMBER;

    TYPE facturarecord IS RECORD (
        codigo_sap        mae_produ.cod_sap%type,
        descripcion       GEN_I18N_SICC_PAIS.Val_I18n%type,
        grupo_articulo    mae_produ.val_grup_arti%type,
        familia           gen_i18n_sicc_pais.val_i18n%type,
        importado         varchar2(2),
        marca             seg_marca.des_marc%type,
        tasa_iva          ped_tasa_impue.val_tasa_impu%type,
        porcentaje_ICE    ped_tasa_impue.val_tasa_impu%type
    );

    TYPE facturatype IS TABLE OF facturarecord;
    r_factura        facturatype;

    /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;

   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

   lbAbrirUtlFile      BOOLEAN;

BEGIN

    -- Obtenemos el OID del periodo
    l_oidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    l_oidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(CODIGO_CANAL);
    l_oidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(CODIGO_MARCA);
    l_oidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(pscodigoPeriodo, l_oidMarca, l_oidCanal);

    -- Obtenemos el OID del periodo siguiente
    l_codPeriodoSgte := GEN_FN_CALCU_PERIO(pscodigoPeriodo, 1);
    l_oidPeriodoSgte := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(l_codPeriodoSgte, l_oidMarca, l_oidCanal);

    -- Obtenemos el OID de la actividad
    SELECT ACT.OID_ACTI
    INTO l_oidActividad
    FROM CRA_ACTIV ACT
    WHERE ACT.PAIS_OID_PAIS = l_oidPais
    AND ACT.MARC_OID_MARC = l_oidMarca
    AND ACT.CANA_OID_CANA = l_oidCanal
    AND ACT.COD_ACTI = 'CV'; -- Cupon de Vencimiento

    -- Obtenemos el valor del ultimo numero de lote de facturacion
    BEGIN
      SELECT MAX(con.num_lote_fact)
      INTO l_numeroLoteFacturacion
      FROM ped_solic_cabec con,
           int_lar_tipo_solici_pedido_dis tspd
     WHERE con.perd_oid_peri = l_oidPeriodo
       AND con.fec_fact = to_date(psfechaFacturacion, 'dd/mm/yyyy')
       AND con.ind_inte_lari_gene = '1'--p_indicadorEnvioLarissa
       AND con.ind_ts_no_conso = 0
       AND (con.ind_pedi_prue = 0 OR con.ind_pedi_prue IS NULL)
       AND con.tspa_oid_tipo_soli_pais = tspd.tspa_oid_tipo_soli_pais
       AND con.pais_oid_pais = l_oidPais;
    EXCEPTION
    WHEN OTHERS THEN
        l_numeroLoteFacturacion := NULL;
    END;

    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;

    -- Abrimos el cursor
    OPEN c_facturas(l_oidPeriodo, l_oidPeriodoSgte, l_oidActividad, l_numeroLoteFacturacion);
    LOOP
        FETCH c_facturas BULK COLLECT INTO r_factura LIMIT w_filas;

        /* Procedimiento inicial para generar interfaz */
        IF lbAbrirUtlFile THEN
              GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                     psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
           lbAbrirUtlFile := FALSE;
        END IF;

        IF  r_factura.COUNT > 0 THEN
            FOR i IN r_factura.FIRST..r_factura.LAST LOOP

                lsLinea :=  r_factura(i).codigo_sap           ||';'||
                            r_factura(i).descripcion          ||';'||
                            r_factura(i).grupo_articulo       ||';'||
                            substr(r_factura(i).familia,0,15) ||';'||
                            r_factura(i).importado            ||';'||
                            r_factura(i).marca                ||';'||
                            r_factura(i).tasa_iva             ||';'||
                            r_factura(i).porcentaje_ICE ;

                UTL_FILE.PUT_LINE (V_HANDLE, lslinea);

            END LOOP;

        END IF;

        EXIT WHEN c_facturas%NOTFOUND;
    END LOOP;
    -- Cerramos el cursor
    CLOSE c_facturas;

    IF NOT lbAbrirUtlFile THEN
          utl_file.fclose(V_HANDLE);
         /* Procedimiento final para generar interfaz */
         GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;

  EXCEPTION
   WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_PROCE_DETAL_ELECT_EC: '||ls_sqlerrm);

END IMP_PR_PROCE_DETAL_ELECT_EC;

  /***************************************************************************
  Descripcion       : Secuencia la los Pedidos Que generan la Boleta Electronica
  Fecha Creacion    : 21/05/2013
  Autor             : Jose Cairampoma
  ***************************************************************************/
PROCEDURE imp_pr_secue_bolet_elect
(
  pscodigopais       VARCHAR2,
  pscodigoperiodo    VARCHAR2,
  psfechafacturacion VARCHAR2,
  psindicadornovedad VARCHAR2
) IS

  l_oidperiodo NUMBER;
  lnsegmento   NUMBER := 1000;
  CURSOR cursecue IS
    SELECT s.val_secu_ruta_terr,
           s.soca_oid_soli_cabe,
           0
      FROM ped_solic_cabec_secue s,
           ped_solic_cabec       c
     WHERE s.soca_oid_soli_cabe = c.oid_soli_cabe
       AND c.perd_oid_peri = l_oidperiodo
       AND c.fec_fact = to_date(psfechafacturacion, 'DD/MM/YYYY')
     ORDER BY s.val_secu_ruta_terr;

  TYPE yob_secue_pedid_tab_t IS TABLE OF yob_secue_pedid%ROWTYPE INDEX BY BINARY_INTEGER;
  yob_secue_pedid_tab yob_secue_pedid_tab_t;

  i BINARY_INTEGER := 0;

  v_ind_actu_indi_prim_pedi bas_pais.ind_actu_indi_prim_pedi%TYPE;

  n NUMBER := 0;

BEGIN

  lnsegmento   := gen_pkg_gener.gen_fn_param_pais(pscodigopais,
                                                  'IMP',
                                                  '005');
  l_oidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoperiodo);

  DELETE yob_secue_pedid;

  IF psindicadornovedad = '1' THEN
    INSERT INTO yob_secue_pedid
      SELECT MIN(lpad(tra.oid_catr, 12, '0')),
             con.oid_soli_cabe,
             0
        FROM fac_docum_conta_cabec cab,
             fac_tipo_docum        ftd,
             ped_solic_cabec       con,
             yob_carga_lotes_traza tra
       WHERE con.oid_soli_cabe = cab.soca_oid_soli_cabe
         AND cab.tido_oid_tipo_docu = ftd.oid_tipo_docu
         AND tra.num_pedi = con.val_nume_soli
         AND tra.ind_envi_fe IS NULL
         AND cab.perd_oid_peri = l_oidperiodo
         AND cab.fec_fact = to_date(psfechafacturacion, 'DD/MM/YYYY')
         AND ftd.cod_tipo_docu IN
             ('001', '002', '003', '011', '012', '020', '021', '022', '023')
       GROUP BY con.oid_soli_cabe;
  ELSE
  
    --Limpiamos la tabla que contiene la cabecera de documentos electronicos
    EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_TMP_FELEC_CABEC';
  
    --Limpiamos la tabla que contiene el detalle de documentos electronicos
    EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_TMP_FELEC_DETAL';
    OPEN cursecue;
    LOOP

      FETCH cursecue BULK COLLECT
        INTO yob_secue_pedid_tab LIMIT lnsegmento;
      EXIT WHEN yob_secue_pedid_tab.count = 0;

      FOR i IN yob_secue_pedid_tab.first .. yob_secue_pedid_tab.last
      LOOP
        BEGIN
          yob_secue_pedid_tab(i).num_grup := n;
          INSERT INTO yob_secue_pedid VALUES yob_secue_pedid_tab (i);

        END;
      END LOOP;
      n := n + 1;
    END LOOP;
    CLOSE cursecue;
  END IF;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 1000);
    raise_application_error(-20123,
                            'ERROR IMP_PR_SECUE_BOLET_ELECT: ' ||
                            ls_sqlerrm);
END imp_pr_secue_bolet_elect;
/**************************************************************************
Descripcion         : Proceso que genera la informacion de Facturas
                      Cabeceras para Peru
Fecha Creación      : 01/04/2013
Autor               : Carlos Bazalar
***************************************************************************/
PROCEDURE INT_PR_IMP_FACTU_CABEC_PERU(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      pscodigoPeriodo     VARCHAR2,
                                      psfechaFacturacion  VARCHAR2,
                                      psindicadorNovedad  VARCHAR2,
                                      psNombreArchivo     VARCHAR2,
                                      psNumeroLote        VARCHAR2)
IS
 l_oidPeriodo NUMBER;
 CURSOR c_documento(lnoidPeriodo NUMBER, lnNroGpo NUMBER) IS
 select rpad(decode(instr(cab.val_seri_docu_lega,'F'),0,'F','')||cab.val_seri_docu_lega||'-'||cab.num_docu_cont_inte,14,' ') || -- PK
       'NGAA' ||
       rpad('2.0',10,' ') || -- version_UBL
       rpad('1.0',10,' ') || -- version_estructura_documento
       rpad('332',3,' ') || -- tipo_documento
       to_char(CON.FEC_FACT,'yyyy-mm-dd') || -- fecha_emision
       rpad('20100123763',11,' ') || -- RUC_belcorp
       rpad('6',1,' ') || -- tipo_documento
       rpad(decode(instr(cab.val_seri_docu_lega,'F'),0,'F','')||cab.val_seri_docu_lega||'-'||cab.num_docu_cont_inte,13,' ') || -- Numeracion
       lpad(mc.cod_clie,20,'0') || -- codigo  _cliente
       rpad(con.val_nume_soli,30,' ') || -- numero_pedido
      'NGAB' ||
       --rpad(cab.val_ape1||' '||cab.val_ape2||' '||cab.val_nom1||' '||cab.val_nom2,100,' ') || --apellidos_y_nombres,
       rpad('CETCO S.A.',100,' ') || --  razon_social
       'NGAC' ||
       rpad('CETCO S.A.',100,' ') || --  razon_social
      'NGAD' ||
       rpad('150131',6,' ')||rpad('AV. FELIPE PARDO Y ALIAGA 652-1201',100,' ')||rpad('URB CHACARILLA - SANTA CRUZ',25,' ')||
            rpad('LIMA',30,' ')||rpad('LIMA',30,' ')||rpad('SAN ISIDRO',30,' ')||'PE'|| -- domicilio fiscal
       -- NGBA --
       'NGBA' ||
       rpad(cab.val_nume_iden_fisc,15,' ')|| --DNI,
       '6' || -- tipo DNI
       rpad(GEN_PKG_GENER.gen_fn_reemp_carac_extra(cab.val_ape1||' '||cab.val_ape2||' '||cab.val_nom1||' '||cab.val_nom2),100,' ') || -- apellidos_y_nombres
       rpad(GEN_PKG_GENER.gen_fn_reemp_carac_extra(cab.val_dire_comp),100,' ') || --  direccion,
       -- NGBB --
       'NGBB' ||
       rpad(  GEN_PKG_GENER.gen_fn_reemp_carac_extra(GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(cab.pais_OID_PAIS, mc.OID_CLIE, 4) || '/' ||
           GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(cab.pais_OID_PAIS, mc.OID_CLIE, 3) || '/' ||
           GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(cab.pais_OID_PAIS, mc.OID_CLIE, 2) || '/' ||
           GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(cab.pais_OID_PAIS, mc.OID_CLIE, 1)) ,240,' ') || -- descripcion ubigeo
      -- NGBC --
       'NGBC' ||
       rpad(GEN_PKG_GENER.gen_fn_reemp_carac_extra(nvl(
            (select mcc.val_text_comu
            from MAE_CLIEN_COMUN mcc
            where mcc.CLIE_OID_CLIE(+) = con.clie_oid_clie
            AND mcc.TICM_OID_TIPO_COMU(+) = 3),' '))
            ,250,' '
            ) || -- email
       -- NGBC --
       'NGDA' ||
       'PEN'  || --nuevo_sol_peruano
        rpad('1001',4,' ') || rpad(ltrim(TO_CHAR(nvl(cab.VAL_PREC_NETO_TOTA_LOCA,0)+nvl(cab.VAL_TOTA_GAST_ADMI_SIN_IMPU,0)+nvl(cab.imp_flet_impu_tota_loca,0), '9999999990.00')),15,' ') || -- total valor ventas operaciones grabadas
       rpad('1002',4,' ') || rpad('0.00',15,' ') || -- total valor ventas operaciones inafectas
       rpad('1003',4,' ') || rpad('0.00',15,' ') || -- total valor ventas operaciones exonerdas
      rpad(ltrim(TO_CHAR(cab.val_tota_paga_loca, '9999999990.00')),15,' ') || -- immporte total de la venta
       rpad('1004',4,' ') ||
       --rpad(ltrim(TO_CHAR(cab.val_prec_cont_tota_loca,'9999999990.00')),15,' ')
       rpad(ltrim(TO_CHAR((select sum(decode(dcl.val_prec_cata_unit_loca,'0',
       DECODE(dcl.val_prec_cont_unit_loca,0,0.01,dcl.val_prec_cont_unit_loca)
       *dcl.num_unid_aten,'')) from fac_docum_conta_linea dcl where dcl.dcca_oid_cabe=cab.oid_cabe ), '9999999990.00')),15,' ')
       || rpad('0.00',15,' ') || -- total operaciones grabadas y descuentos
       'NGDB' ||
       rpad(ltrim(TO_CHAR(cab.imp_impu_tota_loca, '9999999990.00')),15,' ') ||  rpad(ltrim(TO_CHAR(cab.imp_impu_tota_loca, '9999999990.00')),15,' ') || rpad('1000',4,' ') || rpad('IGV',6,' ') || rpad('VAT',3,' ') || -- sumatoria IGV
       rpad(' ',15,' ') ||  rpad(' ',15,' ') || rpad('    ',4,' ') || rpad('   ',6,' ') || rpad('   ',3,' ') || -- sumatoria ISC
       rpad(' ',15,' ') ||  rpad(' ',15,' ') || rpad('    ',4,' ') || rpad('     ',6,' ') || rpad('   ',3,' ') || -- sumatoria otros tributos
       rpad(' ', 15,' ') || -- sumatoria otros cargos
       -- NGDC --
       'NGDC' ||
       rpad('2005',4,' ') || rpad(ltrim(to_char(cab.imp_des1_sin_impu_tota, '9999999990.00')),15,' ') || -- descuentos
       -- NGEA --
       'NGEA' ||
       rpad('    ',30,' ') || rpad(' ',2,' ') ||
       -- NGEB --
       'NGEB' ||
       rpad('    ',30,' ') || rpad(' ',2,' ') || rpad(' ',15,' ') ||
       -- NGFA --
       'NGFA' ||
       '1000' ||
       rpad(GEN_FN_NUME_TO_TEXT(TRUNC(cab.val_tota_paga_loca)) || ' y ' || TO_CHAR((cab.val_tota_paga_loca - TRUNC(cab.val_tota_paga_loca)) * 100)  || '/100 NUEVOS SOLES',100,' ') || -- letras
       -- NGFB --
       'NGFB' ||
       'NG00' ||
       rpad('Representacion impresa de la factura electronica',100,' ') || -- letras
       -- NGFC --
       'NGFC' ||
       'NG99' ||
       rpad('Autorizado mediante Resolucion N 0180050000840/SUNAT',100,' ') || -- letras
      -- NGGA --
       'NGGA' ||
       --LPAD(' ',34,'') ||
       '2001' ||
       rpad(ltrim(to_char(round((cab.val_tota_paga_loca - cab.imp_flet_tota_loca) * 0.02,2),'9999999990.00')),15,' ')|| --percepcion
       rpad(ltrim(to_char(round(cab.val_tota_paga_loca + ((cab.val_tota_paga_loca - cab.imp_flet_tota_loca) * 0.02),2), '9999999990.00')) ,15, ' ' ) || --total
       -- NGHA --
       'NGHA' ||
       rpad('ZONA-TERRITORIO',60,' ') ||
       rpad(ZON.COD_ZONA || '-' || TER.COD_TERR,50,' ') ||
       'NGHA' ||
       rpad('CAMPANA',60,' ') ||
       rpad(spc.cod_peri,50,' ') ||
       'NGHA' ||
       rpad('NOTA_1',60,' ') ||
       rpad('NOTA: OPERACION SUJETA A PERCEPCION DEL IGV',50,' ') ||
       'NGHA' ||
       rpad('TOTAL PRODUCTOS',60,' ') ||
       rpad((SELECT SUM(SP.NUM_UNID_ATEN) FROM FAC_DOCUM_CONTA_LINEA SP WHERE SP.DCCA_OID_CABE = CAB.OID_CABE AND SP.IND_NO_IMPR = '0'),50,' ') ||
       'NGHA' ||
       rpad('TOTAL VALOR VENTA CAT',60,' ') ||
       rpad(ltrim(TO_CHAR(cab.val_tota_paga_loca - cab.imp_impu_tota_loca - cab.imp_flet_impu_tota_loca - nvl(cab.val_tota_gast_admi_sin_impu,0)+(cab.imp_des1_sin_impu_tota + cab.imp_des3_sin_impu_tota+cab.val_prec_cont_sin_impu_tota),'9999999990.00')),50,' ') ||
       'NGHA' ||
       rpad('DESCUENTO',60,' ') ||
       rpad(ltrim(TO_CHAR(cab.imp_des1_sin_impu_tota + cab.imp_des3_sin_impu_tota,'9999999990.00')),50,' ') ||
       'NGHA' ||
       rpad('BONIFICACION',60,' ') ||
       rpad(ltrim(TO_CHAR(cab.val_prec_cont_sin_impu_tota,'9999999990.00')),50,' ') ||
       'NGHA' ||
       rpad('TOTAL VALOR VENTA CAT. MENOS DSCTO.',60,' ') ||
       rpad(ltrim(TO_CHAR((cab.val_tota_paga_loca - cab.imp_impu_tota_loca - cab.imp_flet_impu_tota_loca - nvl(cab.val_tota_gast_admi_sin_impu,0)),'9999999990.00')),50,' ') ||
      'NGHA' ||
       rpad('TOT OPE GRA',60,' ') ||
       rpad(ltrim(TO_CHAR(cab.val_prec_cont_tota_loca,'9999999990.00')),50,' ')   ||
       'NGHA' ||
       rpad('OFI PRI DIR',60,' ') ||
       rpad('AV. FELIPE PARDO Y ALIAGA 652-1201',50,' ') ||
       'NGHA' ||
       rpad('OFI PRI URB DIS PRO',60,' ') ||
       rpad('URB CHACARILLA - SANTA CRUZ, SAN ISIDRO, LIMA',50,' ') ||
       'NGHA' ||
       rpad('OFI PRI TLF',60,' ') ||
       rpad('TELF.: 211 3400',50,' ') ||
			 'NGHA' ||
       rpad('PTO EMI DIR',60,' ') ||
       rpad('AV. SAN GENARO 150',50,' ') ||
       'NGHA' ||
       rpad('PTO EMI URB DIS PRO',60,' ') ||
       rpad('URB. MOLITALIA - LOS OLIVOS, LIMA',50,' ') ||
			 'NGHA' ||
       rpad('COD CLI',60,' ') ||
       rpad(mc.cod_clie,50,' ') ||
			 'NGHA' ||
       rpad('IMPRIME',60,' ') ||
       rpad(mcda.ind_impr_docu,50,' ') ||
       'NGHA' ||
       rpad('ORIGEN',60,' ') ||
       rpad('VENTA DIRECTA',50,' ') 
       valorColumna1,
       cab.val_seri_docu_lega,
       cab.num_docu_cont_inte,
       MCDA.ind_impr_docu
  FROM FAC_DOCUM_CONTA_CABEC CAB,
       FAC_TIPO_DOCUM        FTD,
       MAE_CLIEN             MC,
       MAE_CLIEN_DATOS_ADICI MCDA,
       PED_SOLIC_CABEC       CON,
       PED_TASA_IMPUE        PTI,
       ZON_REGIO             REG,
       ZON_ZONA              ZON,
       ZON_SECCI             SEC,
       ZON_TERRI             TER,
       SEG_PAIS              SP,
       CRA_PERIO CP,
     SEG_PERIO_CORPO SPC,
     yob_secue_pedid y
 WHERE y.soca_oid_soli_cabe = con.oid_soli_cabe
   AND SP.OID_PAIS = MC.PAIS_OID_PAIS
   AND MC.OID_CLIE = CON.CLIE_OID_CLIE
   AND MC.OID_CLIE = MCDA.CLIE_OID_CLIE
   AND CON.OID_SOLI_CABE = CAB.SOCA_OID_SOLI_CABE
   AND CON.TAIM_OID_TASA_IMPU = PTI.OID_TASA_IMPU
   AND CAB.TIDO_OID_TIPO_DOCU = FTD.OID_TIPO_DOCU
   AND CAB.ZORG_OID_REGI = REG.OID_REGI
   AND CAB.ZZON_OID_ZONA = ZON.OID_ZONA
   AND CAB.ZSCC_OID_SECC = SEC.OID_SECC
   AND CAB.TERR_OID_TERR = TER.OID_TERR

   AND CP.PERI_OID_PERI = SPC.OID_PERI
   and cp.oid_peri = cab.perd_oid_peri
   AND CAB.PERD_OID_PERI = lnoidPeriodo
   AND CAB.FEC_FACT = TO_DATE(psfechaFacturacion, 'DD/MM/YYYY') -- fecha_facturacion
   AND FTD.COD_TIPO_DOCU = '001'
   AND EXISTS
       (SELECT NULL
          FROM FAC_DOCUM_CONTA_LINEA DET,
               PED_SOLIC_POSIC       PSP,
               PRE_OFERT_DETAL       POD
         WHERE DET.SOPO_OID_SOLI_POSI = PSP.OID_SOLI_POSI
           AND PSP.OFDE_OID_DETA_OFER = POD.OID_DETA_OFER(+)
           AND DET.NUM_UNID_ATEN > 0
           AND DET.DCCA_OID_CABE = CAB.OID_CABE
           AND NOT EXISTS
         (SELECT NULL
                  FROM FAC_TIPO_OFERT_EXCLU TOE
                 WHERE TOE.TOFE_OID_TIPO_OFER = POD.TOFE_OID_TIPO_OFER))
    AND y.num_grup = lnNroGpo
 order by y.num_secu_pedi,valorColumna1;


 TYPE documentorecord IS RECORD (
        valorColumna1 varchar2(4000),
        val_seri_docu_lega FAC_DOCUM_CONTA_CABEC.VAL_SERI_DOCU_LEGA%TYPE,
        num_docu_cont_inte FAC_DOCUM_CONTA_CABEC.NUM_DOCU_CONT_INTE%TYPE ,
        ind_impr_docu MAE_CLIEN_DATOS_ADICI.ind_impr_docu%TYPE
 );

 TYPE documentotype IS TABLE OF documentorecord;
 r_documento        documentotype;

 /* Variables usadas para la generacion del archivo de texto */
 lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
 v_handle            UTL_FILE.FILE_TYPE;

 lsLinea             VARCHAR2(10000);
 lsNombreArchivo     VARCHAR2(50);

 lbAbrirUtlFile      BOOLEAN;

 lnNumeroGrupo       NUMBER(3);
 lsNombreArchivoGrupo VARCHAR2(50);

 cursor c_grupo is
 select distinct num_grup from yob_secue_pedid order by num_grup;

 lsNUmeroEnvios BAS_PARAM_PAIS.Val_Para%TYPE;
 lbIngreso      BOOLEAN;

BEGIN

  SELECT nvl(MIN(val_para), 1)
    INTO lsnumeroenvios
    FROM bas_param_pais
   WHERE cod_sist = 'IMP'
     AND cod_para = '007'
     AND cod_pais = pscodigopais;

  l_oidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoperiodo);

  -- Abrimos el cursor de grupos
  OPEN c_grupo;

  LOOP
    FETCH c_grupo
      INTO lnnumerogrupo;
    EXIT WHEN c_grupo%NOTFOUND;
  
    -- Generamos los archivos de cada uno de los grupos
  
    /* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;
  
    -- Abrimos el cursor
    OPEN c_documento(l_oidperiodo, lnnumerogrupo);
    LOOP
      FETCH c_documento BULK COLLECT
        INTO r_documento LIMIT w_filas;
    
      IF r_documento.count > 0 THEN
      
        /* Procedimiento inicial para generar interfaz */
        IF lbabrirutlfile THEN
          -- Armamos el nombre del archivo
          lsnombrearchivogrupo := psnombrearchivo || psnumerolote ||
                                  lpad(lnnumerogrupo, 3, '0');
          -- --
          gen_pkg_inter_archi.gen_pr_inici_inter_maxca(pscodigopais,
                                                       pscodigosistema,
                                                       pscodigointerfaz,
                                                       lsnombrearchivogrupo,
                                                       utl_file_maxi_cara,
                                                       FALSE,
                                                       lsdirtempo,
                                                       lsnombrearchivo,
                                                       v_handle);
          lbabrirutlfile := FALSE;
        END IF;
        FOR j IN 1 .. lsnumeroenvios
        LOOP
          FOR i IN r_documento.first .. r_documento.last
          LOOP
            
            IF j = 1 THEN
                BEGIN
                    lbIngreso := true;
              INSERT INTO imp_tmp_felec_cabec
                (tip_doc,
                 num_seri,
                 num_docu_inte,
                 fec_fact,
                 txt_cabe1,
                 txt_cabe2)
              VALUES
                ('03',
                 r_documento(i).val_seri_docu_lega,
                 r_documento(i).num_docu_cont_inte,
                 psfechafacturacion,
                 r_documento(i).valorcolumna1,
                 NULL);
                EXCEPTION
                WHEN DUP_VAL_ON_INDEX THEN 
                     lbIngreso := false;
                END;
            END IF;
            lslinea := j || substr(r_documento(i).valorcolumna1, 2);
           -- IF r_documento(i).ind_impr_docu='1' then
               utl_file.put_line(v_handle, lslinea);
           -- end if;
          END LOOP;
        END LOOP;
      END IF;

      EXIT WHEN c_documento%NOTFOUND;
    END LOOP;
    -- Cerramos el cursor
    CLOSE c_documento;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             lsnombrearchivogrupo,
                                             lsnombrearchivo);
    END IF;

    -- --

  END LOOP;

  CLOSE c_grupo;
  -- --

  /*Archivo dummy para que el proceso no se caiga */
  gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                         pscodigosistema,
                                         pscodigointerfaz,
                                         psnombrearchivo,
                                         lsdirtempo,
                                         lsnombrearchivo,
                                         v_handle);
  utl_file.put_line(v_handle, '0');
  utl_file.fclose(v_handle);

  gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                         lsdirtempo,
                                         psnombrearchivo,
                                         lsnombrearchivo);
  /**/

  RETURN;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 1000);
    raise_application_error(-20123,
                            'ERROR INT_PR_IMP_FACTU_CABEC_PERU: ' ||
                            ls_sqlerrm);
END int_pr_imp_factu_cabec_peru;

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Facturas
                      Detalle para Peru
Fecha Creación      : 01/04/2013
Autor               : Carlos Bazalar
***************************************************************************/
PROCEDURE INT_PR_IMP_FACTU_DETAL_PERU(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      pscodigoPeriodo     VARCHAR2,
                                      psfechaFacturacion  VARCHAR2,
                                      psindicadorNovedad  VARCHAR2,
                                      psNombreArchivo     VARCHAR2,
                                      psNumeroLote        VARCHAR2)
IS
 l_oidPeriodo NUMBER;
 CURSOR c_documento(lnoidPeriodo NUMBER, lnNroGpo NUMBER) IS
 select valorColumna1,
        temp.val_seri_docu_lega,
        temp.num_docu_cont_inte
  from (select rpad(decode(instr(cab.val_seri_docu_lega,'F'),0,'F','')||cab.val_seri_docu_lega||'-'||cab.num_docu_cont_inte,14,' ') || -- PK
       'NGCA' ||
       lpad(ROW_NUMBER() OVER (PARTITION BY cab.num_docu_cont_inte ORDER BY dcl.sopo_oid_soli_posi),3,'0') || -- item_detalle,
       rpad(SUBSTR(SPC.COD_PERI, -2, 2) || sp.VAL_CODI_VENT || DECODE(SP.VAL_PREC_CATA_UNIT_LOCA, 0, '0', '8'),30,' ') || -- codigo_producto
       rpad(decode(mp.cod_sap,'9999999996','ZZ','NIU'),3,' ') ||
       -- NGCB --
       'NGCB' ||
       --rpad(GEN_PKG_GENER.gen_fn_reemp_carac_extra(TRIM((SELECT VAL_I18N FROM GEN_I18N_SICC_PAIS WHERE ATTR_ENTI = 'MAE_PRODU' AND IDIO_OID_IDIO = 1 AND VAL_OID = dcl.PROD_OID_PROD))),250,' ') ||
       rpad(GEN_PKG_GENER.gen_fn_reemp_carac_extra(TRIM(imp_pkg_proce_laser.imp_fn_desc_produ(psCodigoPais,dcl.prod_oid_prod))),250,' ') ||
       -- NGCC --
       'NGCC' ||
       rpad(nvl(IMP_FN_DEVUE_LOTE_PROD_PERU(con.val_nume_soli, mp.cod_sap),' '),250,' ') || -- lote
       -- NGCD --
       'NGCD' ||
       case when dcl.val_prec_cata_unit_loca<>0 then rpad(' ',15,' ')
       else
       rpad(ltrim(to_char(DECODE(dcl.val_prec_cont_unit_loca,0,0.01,dcl.val_prec_cont_unit_loca), '9999999990.00')),15,' ')
       end
       ||decode(dcl.val_prec_cata_unit_loca, 0, '02','  ') || --prec_catal_unit_con_descuento
       rpad(dcl.num_unid_aten || '.000',16,' ') ||-- cant_unidades_x_item
       rpad(ltrim(to_char(decode(dcl.val_prec_cata_unit_loca, 0, 0,dcl.val_prec_sin_impu_unit), '9999999990.00')),15, ' ')||-- valor unitario sin IGV
       rpad(ltrim(to_char(decode(dcl.val_prec_cata_unit_loca, 0, 0,dcl.val_prec_cata_unit_loca), '9999999990.00')),15,' ')||'01' || --precio venta unitario
       rpad(ltrim(to_char(decode(dcl.val_prec_cata_unit_loca,0,0,nvl(dcl.VAL_PREC_SIN_IMPU_TOTA_LOCA,0)-nvl(dcl.IMP_DESC_SIN_IMPU_TOTA_LOCA,0)), '9999999990.00')),15,' ') || -- valor venta x item
        -- NGCE --
       'NGCE' ||
       rpad(ltrim(to_char(decode(dcl.val_prec_cata_unit_loca,0,0,dcl.imp_impu_tota_loca), '9999999990.00')),15,' ') ||
       rpad(ltrim(to_char(decode(dcl.val_prec_cata_unit_loca,0,0,dcl.imp_impu_tota_loca), '9999999990.00')),15,' ') ||
       rpad(decode(dcl.val_prec_cata_unit_loca,0,'31','10'),2,' ') ||
       rpad('1000',4,' ') ||
       rpad('IGV',6,' ') ||
       rpad('VAT',3,' ') ||
       -- NGCF --
       'NGCF' ||
       rpad(' ',15,' ') ||
       rpad(' ',15,' ') ||
       rpad('  ',2,' ') ||
       rpad('    ',4,' ') ||
       rpad('   ',6,' ') ||
       rpad('   ',3,' ') ||
       -- NGCG --
       'NGCG' ||
       rpad('PRECIO CAT. UNIT.',60,' ') ||
       rpad(ltrim(to_char(dcl.val_prec_sin_impu_unit, '9999999990.00')),100,' ') ||
       'NGCG' ||
       rpad('PRECIO CAT. UNIT. CON DSCTO',60,' ') ||
       rpad(ltrim(to_char(dcl.val_prec_neto_unit_loca, '9999999990.00')),100,' ') ||
       'NGCG' ||
       rpad('PRECIO CAT. CON DSCTO',60,' ') ||
       rpad(ltrim(to_char(dcl.val_prec_neto_tota_loca, '9999999990.00')),100,' ') ||
       -- NGCH --
       'NGCH' ||
       rpad(nvl(sp.val_porc_desc,0),10,' ') ||
       rpad(ltrim(TO_CHAR(nvl(sp.val_impo_desc_unit_loca,0), '9999999990.00')),15,' ')
       valorColumna1,
       y.num_secu_pedi,
       cab.val_seri_docu_lega,
       cab.num_docu_cont_inte
  FROM FAC_DOCUM_CONTA_CABEC CAB,
       FAC_TIPO_DOCUM        FTD,
       MAE_CLIEN             MC,
       PED_SOLIC_CABEC       CON,
       PED_TASA_IMPUE        PTI,
       ZON_REGIO             REG,
       ZON_ZONA              ZON,
       ZON_SECCI             SEC,
       ZON_TERRI             TER,
       SEG_PAIS              SP,
        fac_docum_conta_linea dcl,
       ped_solic_posic sp,
       CRA_PERIO CP,
     SEG_PERIO_CORPO SPC,
      mae_produ mp,
     yob_secue_pedid y
 WHERE y.soca_oid_soli_cabe = con.oid_soli_cabe
   AND SP.OID_PAIS = MC.PAIS_OID_PAIS
   AND MC.OID_CLIE = CON.CLIE_OID_CLIE
   AND CON.OID_SOLI_CABE = CAB.SOCA_OID_SOLI_CABE
   AND CON.TAIM_OID_TASA_IMPU = PTI.OID_TASA_IMPU
   AND CAB.TIDO_OID_TIPO_DOCU = FTD.OID_TIPO_DOCU
   AND CAB.ZORG_OID_REGI = REG.OID_REGI
   AND CAB.ZZON_OID_ZONA = ZON.OID_ZONA
   AND CAB.ZSCC_OID_SECC = SEC.OID_SECC
   AND CAB.TERR_OID_TERR = TER.OID_TERR
   and dcl.dcca_oid_cabe = cab.oid_cabe
   and dcl.num_unid_aten <> 0
   and dcl.ind_no_impr = '0'
   and sp.oid_soli_posi = dcl.sopo_oid_soli_posi
   AND CP.PERI_OID_PERI = SPC.OID_PERI
   and cp.oid_peri = cab.perd_oid_peri
   AND CAB.PERD_OID_PERI = lnoidperiodo
   and dcl.prod_oid_prod=mp.oid_prod
   AND CAB.FEC_FACT = TO_DATE(psfechaFacturacion, 'DD/MM/YYYY') -- fecha_facturacion
   AND FTD.COD_TIPO_DOCU = '001'
   AND EXISTS
       (SELECT NULL
          FROM FAC_DOCUM_CONTA_LINEA DET,
               PED_SOLIC_POSIC       PSP,
               PRE_OFERT_DETAL       POD
         WHERE DET.SOPO_OID_SOLI_POSI = PSP.OID_SOLI_POSI
           AND PSP.OFDE_OID_DETA_OFER = POD.OID_DETA_OFER(+)
           AND DET.NUM_UNID_ATEN > 0
           AND DET.DCCA_OID_CABE = CAB.OID_CABE
           AND NOT EXISTS
         (SELECT NULL
                  FROM FAC_TIPO_OFERT_EXCLU TOE
                 WHERE TOE.TOFE_OID_TIPO_OFER = POD.TOFE_OID_TIPO_OFER))
    AND y.NUM_GRUP = lnNroGpo
union all
select rpad(decode(instr(cab.val_seri_docu_lega,'F'),0,'F','')||cab.val_seri_docu_lega||'-'||cab.num_docu_cont_inte,14,' ') ||-- PK
       -- NGCA --
       'NGCA' ||
       lpad(998,3,'0') || -- item_detalle,
       rpad(' ',30,' ') || -- codigo_producto
       rpad('ZZ',3,' ') ||
       -- NGCB --
       'NGCB' ||
       rpad('FLETE/EMBALAJE',250,' ') ||
       -- NGCC --
       'NGCC' ||
       rpad(' ',250,' ') || -- lote
       -- NGCD --
       'NGCD' ||
       rpad(' ',15,' ') || --prec_catal_unit_con_descuento
       rpad(' ',2,' ') || --prec_catal_unit_con_descuento
       rpad('1' || '.000',16,' ') ||-- cant_unidades_x_item
       rpad(ltrim(to_char(nvl(cab.IMP_FLET_IMPU_TOTA_LOCA,0), '9999999990.00')),15, ' ')||-- valor unitario sin IGV
       rpad(ltrim(to_char(nvl(cab.IMP_FLET_TOTA_LOCA,0), '9999999990.00')),15,' ')||'01' || --precio venta unitario
       rpad(ltrim(to_char(nvl(cab.IMP_FLET_IMPU_TOTA_LOCA,0), '9999999990.00')),15,' ') || -- valor venta x item
        -- NGCE --
       'NGCE' ||
       rpad(ltrim(to_char(nvl(cab.IMP_FLET_TOTA_LOCA,0)-nvl(cab.IMP_FLET_IMPU_TOTA_LOCA,0), '9999999990.00')),15,' ') ||
       rpad(ltrim(to_char(nvl(cab.IMP_FLET_TOTA_LOCA,0)-nvl(cab.IMP_FLET_IMPU_TOTA_LOCA,0), '9999999990.00')),15,' ') ||
       rpad('10',2,' ') ||
       rpad('1000',4,' ') ||
       rpad('IGV',6,' ') ||
       rpad('VAT',3,' ') ||
       -- NGCF --
       'NGCF' ||
       rpad(' ',15,' ') ||
       rpad(' ',15,' ') ||
       rpad('  ',2,' ') ||
       rpad('    ',4,' ') ||
       rpad('   ',6,' ') ||
       rpad('   ',3,' ') ||
       -- NGCG --
       'NGCG' ||
       rpad('PRECIO CAT. UNIT.',60,' ') ||
       rpad(ltrim(to_char(nvl(cab.IMP_FLET_IMPU_TOTA_LOCA,0), '9999999990.00')),100,' ') ||
       'NGCG' ||
       rpad('PRECIO CAT. UNIT. CON DSCTO',60,' ') ||
       rpad(ltrim(to_char(nvl(cab.IMP_FLET_IMPU_TOTA_LOCA,0), '9999999990.00')),100,' ') ||
       'NGCG' ||
       rpad('PRECIO CAT. CON DSCTO',60,' ') ||
       rpad(ltrim(to_char(nvl(cab.IMP_FLET_IMPU_TOTA_LOCA,0), '9999999990.00')),100,' ') ||
         -- NGCH --
       'NGCH' ||
       rpad(' ',10,' ') ||
       rpad(' ',15,' '),
       y.num_secu_pedi,
       cab.val_seri_docu_lega,
       cab.num_docu_cont_inte
  FROM FAC_DOCUM_CONTA_CABEC CAB,
       yob_secue_pedid y
 WHERE y.soca_oid_soli_cabe = cab.soca_oid_soli_cabe
   AND CAB.PERD_OID_PERI = lnoidperiodo
   AND CAB.FEC_FACT = TO_DATE(psfechaFacturacion, 'DD/MM/YYYY') -- fecha_facturacion
   AND cab.tido_OiD_TIPO_DOCU = 1
    and cab.IMP_FLET_TOTA_LOCA<>0
    AND y.NUM_GRUP = lnNroGpo
union all
select rpad(decode(instr(cab.val_seri_docu_lega,'F'),0,'F','')||cab.val_seri_docu_lega||'-'||cab.num_docu_cont_inte,14,' ') || -- PK
       -- NGCA --
       'NGCA' ||
       lpad(999,3,'0') || -- item_detalle,
       rpad(' ',30,' ') || -- codigo_producto
       rpad('ZZ',3,' ') ||
       -- NGCB --
       'NGCB' ||
       rpad('GASTOS ADMINISTRATIVOS Y DE COBRANZA',250,' ') ||
       -- NGCC --
       'NGCC' ||
       rpad(' ',250,' ') || -- lote
       -- NGCD --
       'NGCD' ||
       rpad(' ',15,' ') || --prec_catal_unit_con_descuento
       rpad(' ',2,' ') || --prec_catal_unit_con_descuento
       rpad('1' || '.000',16,' ') ||-- cant_unidades_x_item
       rpad(ltrim(to_char(nvl(cab.VAL_TOTA_GAST_ADMI_SIN_IMPU,0), '9999999990.00')),15, ' ')||-- valor unitario sin IGV
       rpad(ltrim(to_char(nvl(cab.VAL_TOTA_GAST_ADMI,0), '9999999990.00')),15,' ')||'01' || --precio venta unitario
       rpad(ltrim(to_char(nvl(cab.VAL_TOTA_GAST_ADMI_SIN_IMPU,0), '9999999990.00')),15,' ') || -- valor venta x item
        -- NGCE --
       'NGCE' ||
       rpad(ltrim(to_char(nvl(cab.VAL_TOTA_IMPU_GAST_ADMI,0), '9999999990.00')),15,' ') ||
       rpad(ltrim(to_char(nvl(cab.VAL_TOTA_IMPU_GAST_ADMI,0), '9999999990.00')),15,' ') ||
       rpad('10',2,' ') ||
       rpad('1000',4,' ') ||
       rpad('IGV',6,' ') ||
       rpad('VAT',3,' ') ||
       -- NGCF --
       'NGCF' ||
       rpad(' ',15,' ') ||
       rpad(' ',15,' ') ||
       rpad('  ',2,' ') ||
       rpad('    ',4,' ') ||
       rpad('   ',6,' ') ||
       rpad('   ',3,' ') ||
       -- NGCG --
       'NGCG' ||
       rpad('PRECIO CAT. UNIT.',60,' ') ||
       rpad(ltrim(to_char(nvl(cab.VAL_TOTA_GAST_ADMI_SIN_IMPU,0), '9999999990.00')),100,' ') ||
        'NGCG' ||
       rpad('PRECIO CAT. UNIT. CON DSCTO',60,' ') ||
       rpad(ltrim(to_char(nvl(cab.VAL_TOTA_GAST_ADMI_SIN_IMPU,0), '9999999990.00')),100,' ') ||
       'NGCG' ||
       rpad('PRECIO CAT. CON DSCTO',60,' ') ||
       rpad(ltrim(to_char(nvl(cab.VAL_TOTA_GAST_ADMI_SIN_IMPU,0), '9999999990.00')),100,' ') ||
       -- NGCH --
       'NGCH' ||
       rpad(' ',10,' ') ||
       rpad(' ',15,' '),
       y.num_secu_pedi,
       cab.val_seri_docu_lega,
       cab.num_docu_cont_inte
  FROM FAC_DOCUM_CONTA_CABEC CAB,
     yob_secue_pedid y
 WHERE y.soca_oid_soli_cabe = cab.soca_oid_soli_cabe
   AND CAB.PERD_OID_PERI = lnoidperiodo
   AND CAB.FEC_FACT = TO_DATE(psfechaFacturacion, 'DD/MM/YYYY') -- fecha_facturacion
   AND cab.tido_OiD_TIPO_DOCU = 1
   and cab.VAL_TOTA_GAST_ADMI<>0
   AND y.NUM_GRUP = lnNroGpo) temp
   order by temp.num_secu_pedi,valorColumna1;

 TYPE documentorecord IS RECORD (
        valorColumna1 VARCHAR2(4000),
        val_seri_docu_lega FAC_DOCUM_CONTA_CABEC.VAL_SERI_DOCU_LEGA%TYPE,
        num_docu_cont_inte FAC_DOCUM_CONTA_CABEC.NUM_DOCU_CONT_INTE%TYPE
 );

 TYPE documentotype IS TABLE OF documentorecord;
 r_documento        documentotype;

 /* Variables usadas para la generacion del archivo de texto */
 lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
 v_handle            UTL_FILE.FILE_TYPE;

 lsLinea             VARCHAR2(10000);
 lsNombreArchivo     VARCHAR2(50);

 lbAbrirUtlFile      BOOLEAN;

 lnNumeroGrupo       NUMBER(3);
 lsNombreArchivoGrupo VARCHAR2(50);

 cursor c_grupo is
 select distinct num_grup from yob_secue_pedid order by num_grup;

lsNUmeroEnvios BAS_PARAM_PAIS.Val_Para%TYPE;
lbIngreso      BOOLEAN;

BEGIN

  SELECT nvl(MIN(val_para), 1)
    INTO lsnumeroenvios
    FROM bas_param_pais
   WHERE cod_sist = 'IMP'
     AND cod_para = '007'
     AND cod_pais = pscodigopais;

  l_oidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoperiodo);

  -- Abrimos el cursor de grupos
  OPEN c_grupo;

  LOOP
    FETCH c_grupo
      INTO lnnumerogrupo;
    EXIT WHEN c_grupo%NOTFOUND;
  
    -- Generamos los archivos de cada uno de los grupos
  
    /* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;
  
    -- Abrimos el cursor
    OPEN c_documento(l_oidperiodo, lnnumerogrupo);
    LOOP
      FETCH c_documento BULK COLLECT
        INTO r_documento LIMIT w_filas;
    
      IF r_documento.count > 0 THEN
      
        /* Procedimiento inicial para generar interfaz */
        IF lbabrirutlfile THEN
          -- Armamos el nombre del archivo
          lsnombrearchivogrupo := psnombrearchivo || psnumerolote ||
                                  lpad(lnnumerogrupo, 3, '0');
          -- --
          gen_pkg_inter_archi.gen_pr_inici_inter_maxca(pscodigopais,
                                                       pscodigosistema,
                                                       pscodigointerfaz,
                                                       lsnombrearchivogrupo,
                                                       utl_file_maxi_cara,
                                                       FALSE,
                                                       lsdirtempo,
                                                       lsnombrearchivo,
                                                       v_handle);
          lbabrirutlfile := FALSE;
        END IF;
        FOR j IN 1 .. lsnumeroenvios
        LOOP
          FOR i IN r_documento.first .. r_documento.last
          LOOP
            
            IF j = 1 THEN
                BEGIN
                    lbIngreso := true;
              INSERT INTO imp_tmp_felec_detal
                (tip_doc,
                 num_seri,
                 num_docu_inte,
                 num_secu,
                 txt_deta1,
                 txt_deta2)
              VALUES
                ('03',
                 r_documento(i).val_seri_docu_lega,
                 r_documento(i).num_docu_cont_inte,
                      to_number(substr(r_documento(i).valorcolumna1, 19,3)),
                 r_documento(i).valorcolumna1,
                 NULL);
                EXCEPTION
                WHEN DUP_VAL_ON_INDEX THEN 
                     lbIngreso := false;
                END;
            END IF;
            
            lslinea := j || substr(r_documento(i).valorcolumna1, 2);
            utl_file.put_line(v_handle, lslinea);
            
          END LOOP;
        END LOOP;
      END IF;
    
      EXIT WHEN c_documento%NOTFOUND;
    END LOOP;
    -- Cerramos el cursor
    CLOSE c_documento;
  
    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             lsnombrearchivogrupo,
                                             lsnombrearchivo);
    END IF;
  
    -- --
  
  END LOOP;

  CLOSE c_grupo;
  -- --

  /*Archivo dummy para que el proceso no se caiga */
  gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                         pscodigosistema,
                                         pscodigointerfaz,
                                         psnombrearchivo,
                                         lsdirtempo,
                                         lsnombrearchivo,
                                         v_handle);
  utl_file.put_line(v_handle, '0');
  utl_file.fclose(v_handle);

  gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                         lsdirtempo,
                                         psnombrearchivo,
                                         lsnombrearchivo);
  /**/

  RETURN;
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 1000);
    raise_application_error(-20123,
                            'ERROR INT_PR_IMP_FACTU_DETAL_PERU: ' ||
                            ls_sqlerrm);
  
END int_pr_imp_factu_detal_peru;

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Boletas 065
                      Cabecera para Peru
Fecha Creación      : 01/04/2013
Autor               : Carlos Bazalar
***************************************************************************/
PROCEDURE INT_PR_IMP_BOL65_CABEC_PERU(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      pscodigoPeriodo     VARCHAR2,
                                      psfechaFacturacion  VARCHAR2,
                                      psindicadorNovedad  VARCHAR2,
                                      psNombreArchivo     VARCHAR2,
                                      psNumeroLote        VARCHAR2)
IS
 l_oidPeriodo NUMBER;
 CURSOR c_documento(lnoidPeriodo NUMBER, lnNroGpo NUMBER) IS
 select
       rpad(decode(instr(cab.val_seri_docu_lega,'B'),0,'B','') || cab.val_seri_docu_lega||'-'||cab.num_docu_cont_inte,14,' ') || -- PK
       -- NGAA --
       'NGAA'  ||
       rpad('2.0',10,' ') || -- version_UBL
       rpad('1.0',10,' ') || -- version_estructura_documento
       rpad('333',3,' ') || -- tipo_documento
       to_char(CON.FEC_FACT,'yyyy-mm-dd') || -- fecha_emision
       rpad('20100123763',11,' ') || -- RUC_belcorp
       rpad('6',1,' ') || -- tipo_documento
       rpad(decode(instr(cab.val_seri_docu_lega,'B'),0,'B','') || cab.val_seri_docu_lega||'-'||cab.num_docu_cont_inte,13,' ') || -- Numeracion
       lpad(mc.cod_clie,20,'0') || -- codigo  _cliente
       rpad(con.val_nume_soli,30,' ') || -- numero_pedido
       -- NGAB --
       'NGAB' ||
       --rpad(cab.val_ape1||' '||cab.val_ape2||' '||cab.val_nom1||' '||cab.val_nom2,100,' ') || --apellidos_y_nombres,
       rpad('CETCO S.A.',100,' ') || --  razon_social
       -- NGAC --
       'NGAC' ||
       rpad('CETCO S.A.',100,' ') || --  razon_social
       -- NGAD --
       'NGAD' ||
       rpad('150131',6,' ')||rpad('AV. FELIPE PARDO Y ALIAGA 652-1201',100,' ')||rpad('URB CHACARILLA - SANTA CRUZ',25,' ')||
            rpad('LIMA',30,' ')||rpad('LIMA',30,' ')||rpad('SAN ISIDRO',30,' ')||'PE'|| -- domicilio fiscal
       -- NGBA --
       'NGBA' ||
       rpad(cab.val_nume_iden_fisc,15,' ')|| --DNI,
       '1' || -- tipo DNI
       rpad(GEN_PKG_GENER.gen_fn_reemp_carac_extra(cab.val_ape1||' '||cab.val_ape2||' '||cab.val_nom1||' '||cab.val_nom2),100,' ') || -- apellidos_y_nombres
       rpad(GEN_PKG_GENER.gen_fn_reemp_carac_extra(cab.val_dire_comp),100,' ') || --  direccion,
       -- NGBB --
       'NGBB'  ||
       rpad( GEN_PKG_GENER.gen_fn_reemp_carac_extra(GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(cab.pais_OID_PAIS, mc.OID_CLIE, 4) || '/' ||
           GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(cab.pais_OID_PAIS, mc.OID_CLIE, 3) || '/' ||
           GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(cab.pais_OID_PAIS, mc.OID_CLIE, 2) || '/' ||
           GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(cab.pais_OID_PAIS, mc.OID_CLIE, 1)) ,240,' ') || -- descripcion ubigeo
       -- NGBC --
       'NGBC'  ||
       rpad(GEN_PKG_GENER.gen_fn_reemp_carac_extra(nvl(
            (select mcc.val_text_comu
            from MAE_CLIEN_COMUN mcc
            where mcc.CLIE_OID_CLIE(+) = con.clie_oid_clie
            AND mcc.TICM_OID_TIPO_COMU(+) = 3),' '))
            ,250,' '
            ) || -- email
       -- NGBC --
       'NGDA' ||
       'PEN'  || --nuevo_sol_peruano
       rpad('1001',4,' ') || rpad(ltrim(TO_CHAR(nvl(cab.VAL_PREC_NETO_TOTA_LOCA,0)+nvl(cab.VAL_TOTA_GAST_ADMI_SIN_IMPU,0)+nvl(cab.imp_flet_impu_tota_loca,0), '9999999990.00')),15,' ') || -- total valor ventas operaciones grabadas
       rpad('1002',4,' ') || rpad('0.00',15,' ') || -- total valor ventas operaciones inafectas
       rpad('1003',4,' ') || rpad('0.00',15,' ') || -- total valor ventas operaciones exonerdas
       rpad(ltrim(TO_CHAR(cab.val_tota_paga_loca, '9999999990.00')),15,' ') || -- immporte total de la venta
       rpad('1004',4,' ') || rpad(ltrim(TO_CHAR(cab.val_prec_cont_tota_loca, '9999999990.00')),15,' ') || rpad('0.00',15,' ') || -- total operaciones grabadas y descuentos
       -- NGDB --
       'NGDB' ||
       rpad(ltrim(TO_CHAR(cab.imp_impu_tota_loca, '9999999990.00')),15,' ') ||  rpad(ltrim(TO_CHAR(cab.imp_impu_tota_loca, '9999999990.00')),15,' ') || rpad('1000',4,' ') || rpad('IGV',6,' ') || rpad('VAT',3,' ') || -- sumatoria IGV
       rpad(' ',15,' ') ||  rpad(' ',15,' ') || rpad('    ',4,' ') || rpad('   ',6,' ') || rpad('   ',3,' ') || -- sumatoria ISC
       rpad(' ',15,' ') ||  rpad(' ',15,' ') || rpad('    ',4,' ') || rpad('   ',6,' ') || rpad('   ',3,' ') || -- sumatoria otros tributos
       rpad(' ', 15,' ') || -- sumatoria otros cargos
       -- NGDC --
       'NGDC' ||
       rpad('2005',4,' ') || rpad(ltrim(TO_CHAR(cab.imp_desc_tota_loca-cab.val_prec_cont_tota_loca, '9999999990.00')),15,' ')||
       --rpad('2005',4,' ') || rpad(ltrim(to_char(cab.imp_des1_sin_impu_tota, '9999999990.00')),15,' ') || -- descuentos
       -- NGEA --
       'NGEA' ||
       rpad(' ',30,' ') || rpad(' ',2,' ') ||--rpad(con.val_nume_soli,15,' ') || -- boleta despacho
       'NGEB' ||
       rpad(' ',30,' ') || rpad(' ',2,' ') || rpad(' ',15,' ') ||-- otro documento
       -- NGFA --
       'NGFA' ||
       '1000' ||
       rpad(GEN_FN_NUME_TO_TEXT(TRUNC(cab.val_tota_paga_loca)) || ' y ' || TO_CHAR((cab.val_tota_paga_loca - TRUNC(cab.val_tota_paga_loca)) * 100)  || '/100 NUEVOS SOLES',100,' ') || -- letras
       -- NGFB --
       'NGFB' ||
       'NG00' ||
       rpad('Representacion impresa de la boleta de venta electronica',100,' ')  || -- letras
       -- NGFC --
       'NGFC' ||
       'NG99' ||
       rpad('Autorizado mediante Resolucion N 0180050000840/SUNAT',100,' ') || -- letras
       -- NGGA --
       'NGGA' ||
       '2001' ||
       rpad(ltrim(to_char(round((cab.val_tota_paga_loca - cab.imp_flet_tota_loca) * 0.02,2),'9999999990.00')),15,' ')|| --percepcion
       rpad(ltrim(to_char(round(cab.val_tota_paga_loca + ((cab.val_tota_paga_loca - cab.imp_flet_tota_loca) * 0.02),2), '9999999990.00')) ,15, ' ' ) || --total
       -- NGHA --
       'NGHA' ||
       rpad('ZONA-TERRITORIO',60,' ') ||
       rpad(ZON.COD_ZONA || '-' || TER.COD_TERR,50,' ') ||
       'NGHA' ||
       rpad('CAMPANA',60,' ') ||
       rpad(spc.cod_peri,50,' ') ||
       'NGHA' ||
       rpad('NOTA_1',60,' ') ||
       rpad('NOTA: OPERACION SUJETA A PERCEPCION DEL IGV',50,' ') ||
       'NGHA' ||
       rpad('NOTA_2',60,' ') ||
       rpad(' ',50,' ') ||
       'NGHA' ||
       rpad('TOTAL PRODUCTOS',60,' ') ||
       rpad((SELECT SUM(SP.NUM_UNID_ATEN) FROM FAC_DOCUM_CONTA_LINEA SP WHERE SP.DCCA_OID_CABE = CAB.OID_CABE AND SP.IND_NO_IMPR = '0'),50,' ') ||
       'NGHA' ||
       rpad('TOTAL PRECIO CAT',60,' ') ||
       rpad(ltrim(TO_CHAR(cab.val_tota_paga_loca-cab.imp_flet_tota_loca+cab.imp_desc_tota_loca- nvl(cab.val_tota_gast_admi,0), '9999999990.00')),50,' ') ||
       'NGHA' ||
       rpad('DESCUENTO',60,' ') ||
       rpad(ltrim(TO_CHAR(cab.imp_desc_tota_loca-cab.val_prec_cont_tota_loca, '9999999990.00')),50,' ')
       ||
       'NGHA' ||
       rpad('BONIFICACION',60,' ') ||
       rpad(ltrim(TO_CHAR(cab.val_prec_cont_tota_loca, '9999999990.00')),50,' ') ||
       'NGHA' ||
       rpad('TOTAL PRECIO CAT MENOS DCT',60,' ') ||
       rpad(ltrim(TO_CHAR(cab.val_tota_paga_loca-cab.imp_flet_tota_loca - nvl(cab.val_tota_gast_admi,0), '9999999990.00')),50,' ') ||
       'NGHA' ||
       rpad('TOT OPE GRA',60,' ') ||
       rpad(ltrim(TO_CHAR(cab.val_prec_cont_tota_loca, '9999999990.00')),50,' ') ||
       'NGHA' ||
       rpad('OFI PRI DIR',60,' ') ||
       rpad('AV. FELIPE PARDO Y ALIAGA 652-1201',50,' ') ||
       'NGHA' ||
       rpad('OFI PRI URB DIS PRO',60,' ') ||
       rpad('URB CHACARILLA - SANTA CRUZ, SAN ISIDRO, LIMA',50,' ') ||
       'NGHA' ||
       rpad('OFI PRI TLF',60,' ') ||
       rpad('TELF.: 211 3400',50,' ') ||
			 'NGHA' ||
       rpad('PTO EMI DIR',60,' ') ||
       rpad('AV. SAN GENARO 150',50,' ') ||
       'NGHA' ||
       rpad('PTO EMI URB DIS PRO',60,' ') ||
       rpad('URB. MOLITALIA - LOS OLIVOS, LIMA',50,' ') ||
			 'NGHA' ||
       rpad('COD CLI',60,' ') ||
       rpad(mc.cod_clie ||' / ' || NUM_SECU_FACT_DIAR,50,' ') --||
       valorColumna1,
			 'NGHA' ||
       rpad('IMPRIME',60,' ') ||
       rpad(mcda.ind_impr_docu,50,' ') ||
       'NGHA' ||
       rpad('ORIGEN',60,' ') ||
       rpad('VENTA DIRECTA',50,' ') 
       valorColumna2,
       cab.val_seri_docu_lega,
       cab.num_docu_cont_inte,
       MCDA.ind_impr_docu
  FROM FAC_DOCUM_CONTA_CABEC CAB,
       FAC_TIPO_DOCUM        FTD,
       MAE_CLIEN             MC,
       MAE_CLIEN_DATOS_ADICI MCDA,
       PED_SOLIC_CABEC       CON,
       PED_TASA_IMPUE        PTI,
       ZON_REGIO             REG,
       ZON_ZONA              ZON,
       ZON_SECCI             SEC,
       ZON_TERRI             TER,
       SEG_PAIS              SP,
       CRA_PERIO CP,
       SEG_PERIO_CORPO SPC,
     yob_secue_pedid y,
       Ped_Solic_Cabec_Secue pscs
 WHERE y.soca_oid_soli_cabe = con.oid_soli_cabe
   AND  SP.OID_PAIS = MC.PAIS_OID_PAIS
   AND MC.OID_CLIE = CON.CLIE_OID_CLIE
   AND MC.OID_CLIE = MCDA.CLIE_OID_CLIE
   AND CON.OID_SOLI_CABE = CAB.SOCA_OID_SOLI_CABE
   AND CON.TAIM_OID_TASA_IMPU = PTI.OID_TASA_IMPU
   AND CAB.TIDO_OID_TIPO_DOCU = FTD.OID_TIPO_DOCU
   AND CAB.ZORG_OID_REGI = REG.OID_REGI
   AND CAB.ZZON_OID_ZONA = ZON.OID_ZONA
   AND CAB.ZSCC_OID_SECC = SEC.OID_SECC
   AND CAB.TERR_OID_TERR = TER.OID_TERR

   AND CP.PERI_OID_PERI = SPC.OID_PERI
   and cp.oid_peri = cab.perd_oid_peri
   AND CAB.PERD_OID_PERI = lnoidperiodo
   AND CAB.FEC_FACT = TO_DATE(psfechaFacturacion, 'DD/MM/YYYY') -- fecha_facturacion
   AND FTD.COD_TIPO_DOCU = '011'
   AND EXISTS
       (SELECT NULL
          FROM FAC_DOCUM_CONTA_LINEA DET,
               PED_SOLIC_POSIC       PSP,
               PRE_OFERT_DETAL       POD
         WHERE DET.SOPO_OID_SOLI_POSI = PSP.OID_SOLI_POSI
           AND PSP.OFDE_OID_DETA_OFER = POD.OID_DETA_OFER(+)
           AND DET.NUM_UNID_ATEN > 0
           AND DET.DCCA_OID_CABE = CAB.OID_CABE
           AND NOT EXISTS
         (SELECT NULL
                  FROM FAC_TIPO_OFERT_EXCLU TOE
                 WHERE TOE.TOFE_OID_TIPO_OFER = POD.TOFE_OID_TIPO_OFER))
   AND pscs.soca_oid_soli_cabe=CON.Oid_Soli_Cabe
   and y.NUM_GRUP = lnNroGpo
   order by y.num_secu_pedi,valorColumna1;

 TYPE documentorecord IS RECORD (
        valorColumna1 varchar2(4000),
        valorColumna2 varchar2(4000),
        val_seri_docu_lega FAC_DOCUM_CONTA_CABEC.VAL_SERI_DOCU_LEGA%TYPE,
        num_docu_cont_inte FAC_DOCUM_CONTA_CABEC.NUM_DOCU_CONT_INTE%TYPE,
        ind_impr_docu MAE_CLIEN_DATOS_ADICI.ind_impr_docu%TYPE
 );

 TYPE documentotype IS TABLE OF documentorecord;
 r_documento        documentotype;

 /* Variables usadas para la generacion del archivo de texto */
 lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
 v_handle            UTL_FILE.FILE_TYPE;

 lsLinea             VARCHAR2(10000);
 lsNombreArchivo     VARCHAR2(50);

 lbAbrirUtlFile      BOOLEAN;

 lnNumeroGrupo       NUMBER(3);
 lsNombreArchivoGrupo VARCHAR2(50);

 cursor c_grupo is
 select distinct num_grup from yob_secue_pedid order by num_grup;

lbIngreso      BOOLEAN;


lsindProcNoImpr BAS_PARAM_PAIS.Val_Para%TYPE;

BEGIN
    l_oidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(pscodigoPeriodo);

 lsindProcNoImpr :=  GEN_PKG_GENER.gen_fn_param_pais
  (
    pscodigopais      ,
    'IMP'   ,
    '016' 
  );
    -- Abrimos el cursor de grupos
    OPEN c_grupo;

     LOOP
        FETCH c_grupo INTO lnNumeroGrupo;
        EXIT WHEN c_grupo%NOTFOUND;

        -- Generamos los archivos de cada uno de los grupos

        /* Generando Archivo de Texto (Detalle) */
        lbAbrirUtlFile := TRUE;

        -- Abrimos el cursor
        OPEN c_documento(l_oidPeriodo, lnNumeroGrupo);
        LOOP
            FETCH c_documento BULK COLLECT INTO r_documento LIMIT w_filas;

            IF  r_documento.COUNT > 0 THEN

                /* Procedimiento inicial para generar interfaz */
                IF lbAbrirUtlFile THEN
                    -- Armamos el nombre del archivo
                    lsNombreArchivoGrupo := psNombreArchivo || psNumeroLote || lpad(lnNumeroGrupo, 3, '0');
                    -- --
                    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER_MAXCA(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                             lsNombreArchivoGrupo, UTL_FILE_MAXI_CARA, FALSE, lsDirTempo, lsNombreArchivo, V_HANDLE);
                    lbAbrirUtlFile := FALSE;
                END IF;

                FOR i IN r_documento.FIRST..r_documento.LAST LOOP
                    lsLinea :=  r_documento(i).valorColumna1 || r_documento(i).valorColumna2;

                BEGIN
                    lbIngreso := true;
                    INSERT INTO IMP_TMP_FELEC_CABEC(TIP_DOC,
                                                    NUM_SERI,
                                                    NUM_DOCU_INTE,
                                                    FEC_FACT,
                                                    TXT_CABE1,
                                                    TXT_CABE2)
                                             VALUES('01',
                                                    r_documento(i).val_seri_docu_lega,
                                                    r_documento(i).num_docu_cont_inte,
                                                    psfechaFacturacion,
                                                    r_documento(i).valorColumna1,
                                                    r_documento(i).valorColumna2);
                EXCEPTION
                WHEN DUP_VAL_ON_INDEX THEN
                     lbIngreso := false;
                END;
                 IF lsindProcNoImpr='1' then
                    UTL_FILE.PUT_LINE (V_HANDLE, lslinea);
                 ELSE 
                IF r_documento(i).ind_impr_docu='1' then
                    UTL_FILE.PUT_LINE (V_HANDLE, lslinea);
                 end if;
                 end if;
            
                END LOOP;
            END IF;

            EXIT WHEN c_documento%NOTFOUND;
        END LOOP;
        -- Cerramos el cursor
        CLOSE c_documento;

        IF NOT lbAbrirUtlFile THEN
              utl_file.fclose(V_HANDLE);
             /* Procedimiento final para generar interfaz */
             GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, lsNombreArchivoGrupo, lsNombreArchivo);
        END IF;

        -- --

     END LOOP;

    CLOSE c_grupo;
    -- --

   /*Archivo dummy para que el proceso no se caiga */
   gen_pkg_inter_archi.gen_pr_inici_inter(psCodigoPais, pscodigosistema, pscodigointerfaz, psnombrearchivo,
                                                  lsdirtempo, lsnombrearchivo, v_handle);
   utl_file.put_line(v_handle, '0');
   utl_file.fclose(v_handle);

   gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',lsdirtempo,psnombrearchivo,lsnombrearchivo);
   /**/


    RETURN;
EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_IMP_BOL65_CABEC_PERU: '||ls_sqlerrm);
END INT_PR_IMP_BOL65_CABEC_PERU;

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Boletas 065
                      Detalle para Peru
Fecha Creación      : 01/04/2013
Autor               : Carlos Bazalar
***************************************************************************/
PROCEDURE INT_PR_IMP_BOL65_DETAL_PERU(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      pscodigoPeriodo     VARCHAR2,
                                      psfechaFacturacion  VARCHAR2,
                                      psindicadorNovedad  VARCHAR2,
                                      psNombreArchivo     VARCHAR2,
                                      psNumeroLote        VARCHAR2)
IS
 l_oidPeriodo NUMBER;
 CURSOR c_documento(lnoidPeriodo NUMBER, lnNroGpo NUMBER) IS
 select temp.valorColumna1,
        temp.val_seri_docu_lega,
        temp.num_docu_cont_inte
 from
 (select rpad(decode(instr(cab.val_seri_docu_lega,'B'),0,'B','') || cab.val_seri_docu_lega||'-'||cab.num_docu_cont_inte,14,' ') || -- PK
       -- NGCA --
       'NGCA' ||
       lpad(ROW_NUMBER() OVER (PARTITION BY cab.num_docu_cont_inte ORDER BY dcl.sopo_oid_soli_posi),3,'0') || -- item_detalle,
       rpad(SUBSTR(SPC.COD_PERI, -2, 2) || sp.VAL_CODI_VENT || DECODE(SP.VAL_PREC_CATA_UNIT_LOCA, 0, '0', '8'),30,' ') || -- codigo_producto
       rpad(decode(mp.cod_sap,'9999999996','ZZ','NIU'),3,' ') ||
       -- NGCB --
       'NGCB' ||
       --rpad(GEN_PKG_GENER.gen_fn_reemp_carac_extra(TRIM((SELECT VAL_I18N FROM GEN_I18N_SICC_PAIS WHERE ATTR_ENTI = 'MAE_PRODU' AND IDIO_OID_IDIO = 1 AND VAL_OID = dcl.PROD_OID_PROD))),250,' ') ||
       rpad(GEN_PKG_GENER.gen_fn_reemp_carac_extra(TRIM(imp_pkg_proce_laser.imp_fn_desc_produ(psCodigoPais,dcl.prod_oid_prod))),250,' ') ||
       -- NGCC --
       'NGCC' ||
       rpad(nvl(IMP_FN_DEVUE_LOTE_PROD_PERU(con.val_nume_soli, mp.cod_sap),' '),250,' ') || -- lote
       -- NGCD --
       'NGCD' ||
       case when dcl.val_prec_cata_unit_loca<>0 then rpad(' ',15,' ')
       else
       rpad(ltrim(to_char(dcl.val_prec_cont_unit_loca, '9999999990.00')),15,' ')
       end
       ||decode(dcl.val_prec_cata_unit_loca, 0, '02','  ') || --prec_catal_unit_con_descuento
       rpad(dcl.num_unid_aten || '.000',16,' ') ||-- cant_unidades_x_item
       rpad(ltrim(to_char(decode(dcl.val_prec_cata_unit_loca, 0, 0, dcl.val_prec_sin_impu_unit), '9999999990.00')),15, ' ')||-- valor unitario sin IGV
       rpad(ltrim(to_char(decode(dcl.val_prec_cata_unit_loca, 0, 0,dcl.val_prec_cata_unit_loca), '9999999990.00')),15,' ')||'01' || --precio venta unitario
       rpad(ltrim(to_char(decode(dcl.val_prec_cata_unit_loca,0,0,nvl(dcl.VAL_PREC_SIN_IMPU_TOTA_LOCA,0)-nvl(dcl.IMP_DESC_SIN_IMPU_TOTA_LOCA,0)), '9999999990.00')),15,' ') || -- valor venta x item
       -- NGCE --
       'NGCE' ||
       rpad(ltrim(to_char(decode(dcl.val_prec_cata_unit_loca,0,0,dcl.imp_impu_tota_loca), '9999999990.00')),15,' ') ||
       rpad(ltrim(to_char(decode(dcl.val_prec_cata_unit_loca,0,0,dcl.imp_impu_tota_loca), '9999999990.00')),15,' ') ||
       rpad(decode(dcl.val_prec_cata_unit_loca,0,'31','10'),2,' ') ||
       rpad('1000',4,' ') ||
       rpad('IGV',6,' ') ||
       rpad('VAT',3,' ') ||
       -- NGCF --
       'NGCF' ||
       rpad(' ',15,' ') ||
       rpad(' ',15,' ') ||
       rpad('  ',2,' ') ||
       rpad(' ',4,' ') ||
       rpad(' ',6,' ') ||
       rpad(' ',3,' ') ||
       -- NGCG --
       'NGCG' ||
       rpad('PRECIO CAT. UNIT.',60,' ') ||
       rpad(ltrim(to_char(dcl.val_prec_cata_unit_loca+dcl.val_prec_cont_unit_loca, '9999999990.00')),100,' ') ||
       'NGCG' ||
       rpad('PRECIO CAT. UNIT. CON DSCTO',60,' ') ||
       rpad(ltrim(to_char(dcl.val_prec_fact_unit_loca, '9999999990.00')),100,' ') ||
       'NGCG' ||
       rpad('PRECIO CAT. CON DSCTO',60,' ') ||
       rpad(ltrim(to_char(dcl.val_prec_fact_tota_loca, '9999999990.00')),100,' ') ||
       -- NGCH --
       'NGCH' ||
       rpad(' ',100,' ') ||
       rpad(' ',15,' ') ||
       rpad(' ',10,' ') ||
       rpad(' ',15,' ') valorColumna1,
       y.num_secu_pedi,
       cab.val_seri_docu_lega,
       cab.num_docu_cont_inte
  FROM FAC_DOCUM_CONTA_CABEC CAB,
       FAC_TIPO_DOCUM        FTD,
       MAE_CLIEN             MC,
       PED_SOLIC_CABEC       CON,
       PED_TASA_IMPUE        PTI,
       ZON_REGIO             REG,
       ZON_ZONA              ZON,
       ZON_SECCI             SEC,
       ZON_TERRI             TER,
       SEG_PAIS              SP,
       fac_docum_conta_linea dcl,
       ped_solic_posic sp,
       CRA_PERIO CP,
       SEG_PERIO_CORPO SPC,
       mae_produ mp,
     yob_secue_pedid y
 WHERE y.soca_oid_soli_cabe = con.oid_soli_cabe
   AND  SP.OID_PAIS = MC.PAIS_OID_PAIS
   AND MC.OID_CLIE = CON.CLIE_OID_CLIE
   AND CON.OID_SOLI_CABE = CAB.SOCA_OID_SOLI_CABE
   AND CON.TAIM_OID_TASA_IMPU = PTI.OID_TASA_IMPU
   AND CAB.TIDO_OID_TIPO_DOCU = FTD.OID_TIPO_DOCU
   AND CAB.ZORG_OID_REGI = REG.OID_REGI
   AND CAB.ZZON_OID_ZONA = ZON.OID_ZONA
   AND CAB.ZSCC_OID_SECC = SEC.OID_SECC
   AND CAB.TERR_OID_TERR = TER.OID_TERR
   and dcl.dcca_oid_cabe = cab.oid_cabe
   and dcl.prod_oid_prod = mp.oid_prod
   and dcl.num_unid_aten <> 0
   and dcl.ind_no_impr = '0'
   and sp.oid_soli_posi = dcl.sopo_oid_soli_posi
   AND CP.PERI_OID_PERI = SPC.OID_PERI
   and cp.oid_peri = cab.perd_oid_peri
   AND CAB.PERD_OID_PERI = lnoidperiodo
   AND CAB.FEC_FACT = TO_DATE(psfechaFacturacion, 'DD/MM/YYYY') -- fecha_facturacion
   AND FTD.COD_TIPO_DOCU = '011'
   AND EXISTS
       (SELECT NULL
          FROM FAC_DOCUM_CONTA_LINEA DET,
               PED_SOLIC_POSIC       PSP,
               PRE_OFERT_DETAL       POD
         WHERE DET.SOPO_OID_SOLI_POSI = PSP.OID_SOLI_POSI
           AND PSP.OFDE_OID_DETA_OFER = POD.OID_DETA_OFER(+)
           AND DET.NUM_UNID_ATEN > 0
           AND DET.DCCA_OID_CABE = CAB.OID_CABE
           AND NOT EXISTS
         (SELECT NULL
                  FROM FAC_TIPO_OFERT_EXCLU TOE
                 WHERE TOE.TOFE_OID_TIPO_OFER = POD.TOFE_OID_TIPO_OFER))
    and y.NUM_GRUP = lnNroGpo
union
select rpad(decode(instr(cab.val_seri_docu_lega,'B'),0,'B','') || cab.val_seri_docu_lega||'-'||cab.num_docu_cont_inte,14,' ') || -- PK
       -- NGCA --
       'NGCA' ||
       lpad(998,3,'0') || -- item_detalle,
       rpad(' ',30,' ') || -- codigo_producto
       rpad('ZZ',3,' ') ||
       -- NGCB --
       'NGCB' ||
       rpad('FLETE/EMBALAJE',250,' ') ||
       -- NGCC --
       'NGCC' ||
       rpad(' ',250,' ') || -- lote
       -- NGCD --
       'NGCD' ||
       rpad(' ',15,' ') || --prec_catal_unit_con_descuento
       rpad(' ',2,' ') ||
       rpad('1' || '.000',16,' ') ||-- cant_unidades_x_item
       rpad(ltrim(to_char(nvl(cab.IMP_FLET_IMPU_TOTA_LOCA,0), '9999999990.00')),15, ' ')||-- valor unitario sin IGV
       rpad(ltrim(to_char(nvl(cab.IMP_FLET_TOTA_LOCA,0), '9999999990.00')),15,' ')||'01' || --precio venta unitario
       rpad(ltrim(to_char(nvl(cab.IMP_FLET_IMPU_TOTA_LOCA,0), '9999999990.00')),15,' ') || -- valor venta x item
       -- NGCE --
       'NGCE' ||
       rpad(ltrim(to_char(nvl(cab.IMP_FLET_TOTA_LOCA,0)-nvl(cab.IMP_FLET_IMPU_TOTA_LOCA,0), '9999999990.00')),15,' ') ||
       rpad(ltrim(to_char(nvl(cab.IMP_FLET_TOTA_LOCA,0)-nvl(cab.IMP_FLET_IMPU_TOTA_LOCA,0), '9999999990.00')),15,' ') ||
       rpad('10',2,' ') ||
       rpad('1000',4,' ') ||
       rpad('IGV',6,' ') ||
       rpad('VAT',3,' ') ||
       -- NGCF --
       'NGCF' ||
       rpad(' ',15,' ') ||
       rpad(' ',15,' ') ||
       rpad('  ',2,' ') ||
       rpad(' ',4,' ') ||
       rpad(' ',6,' ') ||
       rpad(' ',3,' ') ||
       -- NGCG --
       'NGCG' ||
       rpad('PRECIO CAT. UNIT.',60,' ') ||
       rpad(ltrim(to_char(nvl(cab.IMP_FLET_TOTA_LOCA,0), '9999999990.00')),100,' ') ||
       'NGCG' ||
       rpad('PRECIO CAT. UNIT. CON DSCTO',60,' ') ||
       rpad(ltrim(to_char(nvl(cab.IMP_FLET_TOTA_LOCA,0), '9999999990.00')),100,' ') ||
       'NGCG' ||
       rpad('PRECIO CAT. CON DSCTO',60,' ') ||
       rpad(ltrim(to_char(nvl(cab.IMP_FLET_TOTA_LOCA,0), '9999999990.00')),100,' ') ||
       -- NGCH --
       'NGCH' ||
       rpad(' ',100,' ') ||
       rpad(' ',15,' ') ||
       rpad(' ',10,' ') ||
       rpad(' ',15,' '),
       y.num_secu_pedi,
       cab.val_seri_docu_lega,
       cab.num_docu_cont_inte
  FROM FAC_DOCUM_CONTA_CABEC CAB,
     yob_secue_pedid y
 WHERE y.soca_oid_soli_cabe = cab.soca_oid_soli_cabe
   AND CAB.PERD_OID_PERI = lnoidperiodo
   AND CAB.FEC_FACT = TO_DATE(psfechaFacturacion, 'DD/MM/YYYY') -- fecha_facturacion
   AND cab.tido_OiD_TIPO_DOCU = 29
   and cab.IMP_FLET_TOTA_LOCA <> 0
   and y.NUM_GRUP = lnNroGpo
union all
select rpad(decode(instr(cab.val_seri_docu_lega,'B'),0,'B','') || cab.val_seri_docu_lega||'-'||cab.num_docu_cont_inte,14,' ') || -- PK
      -- NGCA --
       'NGCA' ||
       lpad(999,3,'0') || -- item_detalle,
       rpad(' ',30,' ') || -- codigo_producto
       rpad('ZZ',3,' ') ||
       -- NGCB --
       'NGCB' ||
       rpad('GASTOS ADMINISTRATIVOS Y DE COBRANZA',250,' ') ||
       -- NGCC --
       'NGCC' ||
       rpad(' ',250,' ') || -- lote
       -- NGCD --
       'NGCD' ||
       rpad(' ',15,' ') || --prec_catal_unit_con_descuento
       rpad(' ',2,' ') ||
       rpad('1' || '.000',16,' ') ||-- cant_unidades_x_item
       rpad(ltrim(to_char(nvl(cab.VAL_TOTA_GAST_ADMI_SIN_IMPU,0), '9999999990.00')),15, ' ')||-- valor unitario sin IGV
       rpad(ltrim(to_char(nvl(cab.VAL_TOTA_GAST_ADMI,0), '9999999990.00')),15,' ')||'01' || --precio venta unitario
       rpad(ltrim(to_char(nvl(cab.VAL_TOTA_GAST_ADMI_SIN_IMPU,0), '9999999990.00')),15,' ') || -- valor venta x item
       -- NGCE --
       'NGCE' ||
       rpad(ltrim(to_char(nvl(cab.VAL_TOTA_IMPU_GAST_ADMI,0), '9999999990.00')),15,' ') ||
       rpad(ltrim(to_char(nvl(cab.VAL_TOTA_IMPU_GAST_ADMI,0), '9999999990.00')),15,' ') ||
       rpad('10',2,' ') ||
       rpad('1000',4,' ') ||
       rpad('IGV',6,' ') ||
       rpad('VAT',3,' ') ||
       -- NGCF --
       'NGCF' ||
       rpad(' ',15,' ') ||
       rpad(' ',15,' ') ||
       rpad('  ',2,' ') ||
       rpad(' ',4,' ') ||
       rpad(' ',6,' ') ||
       rpad(' ',3,' ') ||
       -- NGCG --
       'NGCG' ||
       rpad('PRECIO CAT. UNIT.',60,' ') ||
       rpad(ltrim(to_char(nvl(cab.VAL_TOTA_GAST_ADMI,0), '9999999990.00')),100,' ') ||
       'NGCG' ||
       rpad('PRECIO CAT. UNIT. CON DSCTO',60,' ') ||
       rpad(ltrim(to_char(nvl(cab.VAL_TOTA_GAST_ADMI,0), '9999999990.00')),100,' ') ||
       'NGCG' ||
       rpad('PRECIO CAT. CON DSCTO',60,' ') ||
       rpad(ltrim(to_char(nvl(cab.VAL_TOTA_GAST_ADMI,0), '9999999990.00')),100,' ') ||
       -- NGCH --
       'NGCH' ||
       rpad(' ',100,' ') ||
       rpad(' ',15,' ') ||
       rpad(' ',10,' ') ||
       rpad(' ',15,' '),
       y.num_secu_pedi,
       cab.val_seri_docu_lega,
       cab.num_docu_cont_inte
  FROM FAC_DOCUM_CONTA_CABEC CAB,
     yob_secue_pedid y
 WHERE y.soca_oid_soli_cabe = cab.soca_oid_soli_cabe
 AND CAB.PERD_OID_PERI = lnoidperiodo
   AND CAB.FEC_FACT = TO_DATE(psfechaFacturacion, 'DD/MM/YYYY') -- fecha_facturacion
   AND cab.tido_OiD_TIPO_DOCU = 29
   and cab.VAL_TOTA_GAST_ADMI <> 0
   and y.NUM_GRUP = lnNroGpo ) temp
order by
       temp.num_secu_pedi,valorColumna1 ;

 TYPE documentorecord IS RECORD (
        valorColumna1 varchar2(4000),
        val_seri_docu_lega FAC_DOCUM_CONTA_CABEC.VAL_SERI_DOCU_LEGA%TYPE,
        num_docu_cont_inte FAC_DOCUM_CONTA_CABEC.NUM_DOCU_CONT_INTE%TYPE
 );

 TYPE documentotype IS TABLE OF documentorecord;
 r_documento        documentotype;

 /* Variables usadas para la generacion del archivo de texto */
 lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
 v_handle            UTL_FILE.FILE_TYPE;

 lsLinea             VARCHAR2(10000);
 lsNombreArchivo     VARCHAR2(50);

 lbAbrirUtlFile      BOOLEAN;

 lnNumeroGrupo       NUMBER(3);
 lsNombreArchivoGrupo VARCHAR2(50);

 cursor c_grupo is
 select distinct num_grup from yob_secue_pedid order by num_grup;

lbIngreso      BOOLEAN;

BEGIN
    l_oidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(pscodigoPeriodo);

    -- Abrimos el cursor de grupos
    OPEN c_grupo;

     LOOP
        FETCH c_grupo INTO lnNumeroGrupo;
        EXIT WHEN c_grupo%NOTFOUND;

        -- Generamos los archivos de cada uno de los grupos

        /* Generando Archivo de Texto (Detalle) */
        lbAbrirUtlFile := TRUE;

        -- Abrimos el cursor
        OPEN c_documento(l_oidPeriodo, lnNumeroGrupo);
        LOOP
            FETCH c_documento BULK COLLECT INTO r_documento LIMIT w_filas;

            IF  r_documento.COUNT > 0 THEN

                /* Procedimiento inicial para generar interfaz */
                IF lbAbrirUtlFile THEN
                    -- Armamos el nombre del archivo
                    lsNombreArchivoGrupo := psNombreArchivo || psNumeroLote || lpad(lnNumeroGrupo, 3, '0');
                    -- --
                    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER_MAXCA(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                             lsNombreArchivoGrupo, UTL_FILE_MAXI_CARA, FALSE, lsDirTempo, lsNombreArchivo, V_HANDLE);
                    lbAbrirUtlFile := FALSE;
                END IF;

                FOR i IN r_documento.FIRST..r_documento.LAST LOOP
                    lsLinea :=  r_documento(i).valorColumna1 ;

                BEGIN
                    lbIngreso := true;
                    INSERT INTO IMP_TMP_FELEC_DETAL(TIP_DOC,
                                                    NUM_SERI,
                                                    NUM_DOCU_INTE,
                                                    NUM_SECU,
                                                    TXT_DETA1,
                                                    TXT_DETA2)
                                             VALUES('01',
                                                    r_documento(i).val_seri_docu_lega,
                                                    r_documento(i).num_docu_cont_inte,
                            to_number(substr(r_documento(i).valorcolumna1, 19,3)),
                                                    r_documento(i).valorColumna1,
                                                    null);
                EXCEPTION
                WHEN DUP_VAL_ON_INDEX THEN
                     lbIngreso := false;
                END;

                    UTL_FILE.PUT_LINE (V_HANDLE, lslinea);
                END LOOP;
            END IF;

            EXIT WHEN c_documento%NOTFOUND;
        END LOOP;
        -- Cerramos el cursor
        CLOSE c_documento;

        IF NOT lbAbrirUtlFile THEN
              utl_file.fclose(V_HANDLE);
             /* Procedimiento final para generar interfaz */
             GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, lsNombreArchivoGrupo, lsNombreArchivo);
        END IF;

        -- --

     END LOOP;

    CLOSE c_grupo;
    -- --

   /*Archivo dummy para que el proceso no se caiga */
   gen_pkg_inter_archi.gen_pr_inici_inter(psCodigoPais, pscodigosistema, pscodigointerfaz, psnombrearchivo,
                                                  lsdirtempo, lsnombrearchivo, v_handle);
   utl_file.put_line(v_handle, '0');
   utl_file.fclose(v_handle);

   gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',lsdirtempo,psnombrearchivo,lsnombrearchivo);
   /**/

    RETURN;
EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_IMP_BOL65_DETAL_PERU: '||ls_sqlerrm);
END INT_PR_IMP_BOL65_DETAL_PERU;

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Boletas 067
                      Cabecera para Peru
Fecha Creación      : 01/04/2013
Autor               : Carlos Bazalar
***************************************************************************/
PROCEDURE INT_PR_IMP_BOL67_CABEC_PERU(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      pscodigoPeriodo     VARCHAR2,
                                      psfechaFacturacion  VARCHAR2,
                                      psindicadorNovedad  VARCHAR2,
                                      psNombreArchivo     VARCHAR2,
                                      psNumeroLote        VARCHAR2)
IS
 l_oidPeriodo NUMBER;
 CURSOR c_documento(lnoidPeriodo NUMBER, lnNroGpo NUMBER) IS
 select rpad(decode(instr(cab.val_seri_docu_lega,'B'),0,'B','')||cab.val_seri_docu_lega||'-'||cab.num_docu_cont_inte,14,' ') || -- PK
       -- NGAA --
       'NGAA' ||
       rpad('2.0',10,' ') || -- version_UBL
       rpad('1.0',10,' ') || -- version_estructura_documento
       rpad('333',3,' ') || -- tipo_documento
       to_char(CON.FEC_FACT,'yyyy-mm-dd') || -- fecha_emision
       rpad('20100123763',11,' ') || -- RUC_belcorp
       rpad('6',1,' ') || -- tipo_documento
       rpad(decode(instr(cab.val_seri_docu_lega,'B'),0,'B','')||cab.val_seri_docu_lega||'-'||cab.num_docu_cont_inte,13,' ') || -- Numeracion
       lpad(mc.cod_clie,20,'0') || -- codigo  _cliente
       rpad(con.val_nume_soli,30,' ') || -- numero_pedido
       -- NGAB --
       'NGAB' ||
       --rpad(cab.val_ape1||' '||cab.val_ape2||' '||cab.val_nom1||' '||cab.val_nom2,100,' ') || --apellidos_y_nombres,
       rpad('CETCO S.A.',100,' ') || --  razon_social
       -- NGAC --
       'NGAC' ||
       rpad('CETCO S.A.',100,' ') || --  razon_social
       -- NGAD --
       'NGAD' ||
       rpad('150131',6,' ')||rpad('AV. FELIPE PARDO Y ALIAGA 652-1201',100,' ')||rpad('URB CHACARILLA - SANTA CRUZ',25,' ')||
            rpad('LIMA',30,' ')||rpad('LIMA',30,' ')||rpad('SAN ISIDRO',30,' ')||'PE'|| -- domicilio fiscal
       -- NGBA --
       'NGBA' ||
       rpad(cab.val_nume_iden_fisc,15,' ')|| --DNI,
       '1' || -- tipo DNI
       rpad(GEN_PKG_GENER.gen_fn_reemp_carac_extra(cab.val_ape1||' '||cab.val_ape2||' '||cab.val_nom1||' '||cab.val_nom2),100,' ') || -- apellidos_y_nombres
       rpad(GEN_PKG_GENER.gen_fn_reemp_carac_extra(cab.val_dire_comp),100,' ') || --  direccion,
       -- NGBB --
       'NGBB' ||
       rpad(GEN_PKG_GENER.gen_fn_reemp_carac_extra(
       GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(cab.pais_OID_PAIS, mc.OID_CLIE, 4) || '/' ||
           GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(cab.pais_OID_PAIS, mC.OID_CLIE, 3) || '/' ||
           GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(cab.pais_OID_PAIS, mC.OID_CLIE, 2) || '/' ||
           GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(cab.pais_OID_PAIS, mC.OID_CLIE, 1)) ,240,' ') || -- descripcion ubigeo
       -- NGBC --
       'NGBC' ||
       rpad(GEN_PKG_GENER.gen_fn_reemp_carac_extra(nvl(
            (select mcc.val_text_comu
            from MAE_CLIEN_COMUN mcc
            where mcc.CLIE_OID_CLIE(+) = con.clie_oid_clie
            AND mcc.TICM_OID_TIPO_COMU(+) = 3),' '))
            ,250,' '
            ) || -- email
       -- NGBC --
       'NGDA' ||
       'PEN'  || --nuevo_sol_peruano
       rpad('1001',4,' ') || rpad(ltrim(TO_CHAR(0, '9999999990.00')),15,' ') || -- total valor ventas operaciones grabadas
       rpad('1002',4,' ') || rpad('0.00',15,' ') || -- total valor ventas operaciones inafectas
       rpad('1003',4,' ') || rpad('0.00',15,' ') || -- total valor ventas operaciones exonerdas
       rpad(ltrim(TO_CHAR(0, '9999999990.00')),15,' ') || -- immporte total de la venta

       rpad('1004',4,' ') || rpad(ltrim(TO_CHAR((select sum(decode(dcl.val_prec_cata_unit_loca,'0',
       DECODE(dcl.val_prec_cont_unit_loca,0,0.01,dcl.val_prec_cont_unit_loca)
       *dcl.num_unid_aten,'')) from fac_docum_conta_linea dcl where dcl.dcca_oid_cabe=cab.oid_cabe ), '9999999990.00')),15,' ') || rpad('0.00',15,' ') || -- total operaciones grabadas y descuentos

       -- NGDB --
       'NGDB' ||
       rpad(ltrim(TO_CHAR(0, '9999999990.00')),15,' ') ||  rpad(ltrim(TO_CHAR(0, '9999999990.00')),15,' ') || rpad('1000',4,' ') || rpad('IGV',6,' ') || rpad('VAT',3,' ') || -- sumatoria IGV
       rpad(' ',15,' ') ||  rpad(' ',15,' ') || rpad('    ',4,' ') || rpad('   ',6,' ') || rpad('   ',3,' ') || -- sumatoria ISC
       rpad(' ',15,' ') ||  rpad(' ',15,' ') || rpad('    ',4,' ') || rpad('   ',6,' ') || rpad('   ',3,' ') || -- sumatoria otros tributos
       rpad(' ', 15,' ') || -- sumatoria otros cargos
       -- NGDC --
       'NGDC' ||
       rpad('2005',4,' ') || rpad('0.00',15,' ')||-- descuentos
       --rpad(' ',4,' ') || rpad(' ',15,' ') || -- descuentos
       -- NGEA --
       'NGEA' ||
       rpad(' ',30,' ') || rpad(' ',2,' ') ||--rpad(con.val_nume_soli,15,' ') || -- boleta despacho
       'NGEB' ||
       rpad(' ',30,' ') || rpad(' ',2,' ') || rpad(' ',15,' ') ||-- otro documento
       -- NGFA --
       'NGFA' ||
       '1000' ||
       rpad('CERO NUEVOS SOLES',100,' ') || -- letras
       --'1002' ||
       --rpad('TRANSFERENCIA GRATUITA DE UN BIEN Y/O SERVICIO PRESTADO GRATUITAMENTE',100,' ') || -- letras
       -- NGFB --
       'NGFB' ||
       'NG00' ||
       rpad('Representacion impresa de la boleta de venta electronica',100,' ')  || -- letras
       -- NGFC --
       'NGFC' ||
       'NG99' ||
       rpad('Autorizado mediante Resolucion N 0180050000840/SUNAT',100,' ') || -- letras
       -- NGFA --
       'NGFA' ||
       '1002' ||
       rpad('TRANSFERENCIA GRATUITA DE UN BIEN Y/O SERVICIO PRESTADO GRATUITAMENTE',100,' ') || -- letras
       -- NGFB --
       'NGFB' ||
       '    ' ||
       rpad(' ',100,' ')  || -- letras
       -- NGFC --
       'NGFC' ||
       ' ' ||
       rpad(' ',100,' ') || -- letras
       -- NGGA --
       'NGGA' ||
       '2001' ||
       rpad(ltrim(to_char(0,'9999999990.00')),15,' ')|| --percepcion
       rpad(ltrim(to_char(0,'9999999990.00')),15, ' ' ) || --total
        -- NGHA --
       'NGHA' ||
       rpad('ZONA-TERRITORIO',60,' ') ||
       rpad(ZON.COD_ZONA || '-' || TER.COD_TERR,50,' ') ||
       'NGHA' ||
       rpad('CAMPANA',60,' ') ||
       rpad(spc.cod_peri,50,' ') ||
       'NGHA' ||
       rpad('NOTA_1',60,' ') ||
       rpad(' ',50,' ') ||
       'NGHA' ||
       rpad('NOTA_2',60,' ') ||
       rpad('NOTA: BONIF X HABER ALCANZ. VOL. DE COMP',50,' ') ||
       'NGHA' ||
       rpad('TOTAL PRODUCTOS',60,' ') ||
       rpad((SELECT SUM(SP.NUM_UNID_ATEN) FROM FAC_DOCUM_CONTA_LINEA SP WHERE SP.DCCA_OID_CABE = CAB.OID_CABE AND SP.IND_NO_IMPR = '0'),50,' ') ||
       'NGHA' ||
       rpad('TOTAL PRECIO CAT',60,' ') ||
       rpad('0.00',50,' ') ||
       'NGHA' ||
       rpad('DESCUENTO',60,' ') ||
       --rpad(0,50,' ') ||
       rpad('0.00',50,' ') ||
       'NGHA' ||
       rpad('BONIFICACION',60,' ') ||
       rpad('0.00',50,' ') ||
       'NGHA' ||
       rpad('TOTAL PRECIO CAT MENOS DCT',60,' ') ||
       rpad('0.00',50,' ') ||

       'NGHA' ||
       rpad('TOT OPE GRA',60,' ') ||
       rpad(ltrim(TO_CHAR((select sum(decode(dcl.val_prec_cata_unit_loca,'0',

       DECODE(dcl.val_prec_cont_unit_loca,0,0.1,dcl.val_prec_cont_unit_loca)
       *dcl.num_unid_aten,'')) from fac_docum_conta_linea dcl where dcl.dcca_oid_cabe=cab.oid_cabe ), '9999999990.00')),50,' ')  --||
       as valorColumna1,
       'NGHA' ||
       rpad('OFI PRI DIR',60,' ') ||
       rpad('AV. FELIPE PARDO Y ALIAGA 652-1201',50,' ') ||       
       'NGHA' ||
       rpad('OFI PRI URB DIS PRO',60,' ') ||
       rpad('URB CHACARILLA - SANTA CRUZ, SAN ISIDRO, LIMA',50,' ') ||
       'NGHA' ||
       rpad('OFI PRI TLF',60,' ') ||
       rpad('TELF.: 211 3400',50,' ') ||
			 'NGHA' ||
       rpad('PTO EMI DIR',60,' ') ||
       rpad('AV. SAN GENARO 150',50,' ') ||
       'NGHA' ||
       rpad('PTO EMI URB DIS PRO',60,' ') ||
       rpad('URB. MOLITALIA - LOS OLIVOS, LIMA',50,' ') ||
			 'NGHA' ||
       rpad('COD CLI',60,' ') ||
       rpad(mc.cod_clie ||' / ' || NUM_SECU_FACT_DIAR,50,' ') ||
			 'NGHA' ||
       rpad('IMPRIME',60,' ') ||
       --rpad(mcda.ind_impr_docu,50,' ')
       rpad(case when
              (select count(1) from FAC_DESHA_ZONAS_ENVIO_BOLET FDZEB WHERE FDZEB.OID_ZONA = ZON.OID_ZONA ) > 0
              then
                 '0'
              else
                 mcda.ind_impr_docu
              end
              ,50,' ') ||
       'NGHA' ||
       rpad('ORIGEN',60,' ') ||
       rpad('VENTA DIRECTA',50,' ') 
       valorColumna2,
       cab.val_seri_docu_lega,
       cab.num_docu_cont_inte,
       case when
              (select count(1) from FAC_DESHA_ZONAS_ENVIO_BOLET FDZEB WHERE FDZEB.OID_ZONA = ZON.OID_ZONA ) > 0
              then
                 '0'
              else
                 mcda.ind_impr_docu
              end ind_impr_docu
  FROM FAC_DOCUM_CONTA_CABEC CAB,
       FAC_TIPO_DOCUM        FTD,
       MAE_CLIEN             MC,
       MAE_CLIEN_DATOS_ADICI MCDA,
       PED_SOLIC_CABEC       CON,
       PED_TASA_IMPUE        PTI,
       ZON_REGIO             REG,
       ZON_ZONA              ZON,
       ZON_SECCI             SEC,
       ZON_TERRI             TER,
       SEG_PAIS              SP,
       CRA_PERIO CP,
     SEG_PERIO_CORPO SPC,
     yob_secue_pedid y,
       Ped_Solic_Cabec_Secue pscs
 WHERE y.soca_oid_soli_cabe = con.oid_soli_cabe
   AND SP.OID_PAIS = MC.PAIS_OID_PAIS
   AND MC.OID_CLIE = CON.CLIE_OID_CLIE
   AND MC.OID_CLIE = MCDA.CLIE_OID_CLIE
   AND CON.OID_SOLI_CABE = CAB.SOCA_OID_SOLI_CABE
   AND CON.TAIM_OID_TASA_IMPU = PTI.OID_TASA_IMPU
   AND CAB.TIDO_OID_TIPO_DOCU = FTD.OID_TIPO_DOCU
   AND CAB.ZORG_OID_REGI = REG.OID_REGI
   AND CAB.ZZON_OID_ZONA = ZON.OID_ZONA
   AND CAB.ZSCC_OID_SECC = SEC.OID_SECC
   AND CAB.TERR_OID_TERR = TER.OID_TERR

   AND CP.PERI_OID_PERI = SPC.OID_PERI
   and cp.oid_peri = cab.perd_oid_peri
   AND CAB.PERD_OID_PERI = lnoidperiodo
   AND CAB.FEC_FACT = TO_DATE(psfechaFacturacion, 'DD/MM/YYYY') -- fecha_facturacion
   AND FTD.COD_TIPO_DOCU = '012'
   AND EXISTS
       (SELECT NULL
          FROM FAC_DOCUM_CONTA_LINEA DET,
               PED_SOLIC_POSIC       PSP,
               PRE_OFERT_DETAL       POD
         WHERE DET.SOPO_OID_SOLI_POSI = PSP.OID_SOLI_POSI
           AND PSP.OFDE_OID_DETA_OFER = POD.OID_DETA_OFER(+)
           AND DET.NUM_UNID_ATEN > 0
           AND DET.DCCA_OID_CABE = CAB.OID_CABE
           AND NOT EXISTS
         (SELECT NULL
                  FROM FAC_TIPO_OFERT_EXCLU TOE
                 WHERE TOE.TOFE_OID_TIPO_OFER = POD.TOFE_OID_TIPO_OFER))
AND pscs.soca_oid_soli_cabe=CON.Oid_Soli_Cabe
AND y.NUM_GRUP = lnNroGpo
order by y.num_secu_pedi,valorColumna1;

 TYPE documentorecord IS RECORD (
        valorColumna1 varchar2(4000),
        valorColumna2 varchar2(4000),
        val_seri_docu_lega FAC_DOCUM_CONTA_CABEC.VAL_SERI_DOCU_LEGA%TYPE,
        num_docu_cont_inte FAC_DOCUM_CONTA_CABEC.NUM_DOCU_CONT_INTE%TYPE,
        ind_impr_docu MAE_CLIEN_DATOS_ADICI.ind_impr_docu%TYPE
 );

 TYPE documentotype IS TABLE OF documentorecord;
 r_documento        documentotype;

 /* Variables usadas para la generacion del archivo de texto */
 lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
 v_handle            UTL_FILE.FILE_TYPE;

 lsLinea             VARCHAR2(10000);
 lsNombreArchivo     VARCHAR2(50);

 lbAbrirUtlFile      BOOLEAN;

 lnNumeroGrupo       NUMBER(3);
 lsNombreArchivoGrupo VARCHAR2(50);

 cursor c_grupo is
 select distinct num_grup from yob_secue_pedid order by num_grup;

lbIngreso      BOOLEAN;

lsindProcNoImpr BAS_PARAM_PAIS.Val_Para%TYPE;

BEGIN
    l_oidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(pscodigoPeriodo);

  lsindProcNoImpr :=  GEN_PKG_GENER.gen_fn_param_pais
  (
    pscodigopais      ,
    'IMP'   ,
    '016' 
  );

    -- Abrimos el cursor de grupos
    OPEN c_grupo;

     LOOP
        FETCH c_grupo INTO lnNumeroGrupo;
        EXIT WHEN c_grupo%NOTFOUND;

        -- Generamos los archivos de cada uno de los grupos

        /* Generando Archivo de Texto (Detalle) */
        lbAbrirUtlFile := TRUE;

        -- Abrimos el cursor
        OPEN c_documento(l_oidPeriodo, lnNumeroGrupo);
        LOOP
            FETCH c_documento BULK COLLECT INTO r_documento LIMIT w_filas;

            IF  r_documento.COUNT > 0 THEN

                /* Procedimiento inicial para generar interfaz */
                IF lbAbrirUtlFile THEN
                    -- Armamos el nombre del archivo
                    lsNombreArchivoGrupo := psNombreArchivo || psNumeroLote || lpad(lnNumeroGrupo, 3, '0');
                    -- --
                    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER_MAXCA(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                             lsNombreArchivoGrupo, UTL_FILE_MAXI_CARA, FALSE, lsDirTempo, lsNombreArchivo, V_HANDLE);
                    lbAbrirUtlFile := FALSE;
                END IF;

                FOR i IN r_documento.FIRST..r_documento.LAST LOOP
                    lsLinea :=  r_documento(i).valorColumna1;
                    lsLinea :=  lsLinea || r_documento(i).valorColumna2;

                    BEGIN
                        lbIngreso := true;
                    INSERT INTO IMP_TMP_FELEC_CABEC(TIP_DOC,
                                                    NUM_SERI,
                                                    NUM_DOCU_INTE,
                                                    FEC_FACT,
                                                    TXT_CABE1,
                                                    TXT_CABE2)
                                             VALUES('02',
                                                    r_documento(i).val_seri_docu_lega,
                                                    r_documento(i).num_docu_cont_inte,
                                                    psfechaFacturacion,
                                                    r_documento(i).valorColumna1,
                                                    r_documento(i).valorColumna2);
                    EXCEPTION
                    WHEN DUP_VAL_ON_INDEX THEN
                         lbIngreso := false;
                    END;
                 IF lsindProcNoImpr='1' then
                    UTL_FILE.PUT_LINE (V_HANDLE, lslinea);
                 ELSE 
                    IF r_documento(i).ind_impr_docu='1' then
                       UTL_FILE.PUT_LINE (V_HANDLE, lslinea);
                    end if;
                 end if;
                END LOOP;
            END IF;

            EXIT WHEN c_documento%NOTFOUND;
        END LOOP;
        -- Cerramos el cursor
        CLOSE c_documento;

        IF NOT lbAbrirUtlFile THEN
              utl_file.fclose(V_HANDLE);
             /* Procedimiento final para generar interfaz */
             GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, lsNombreArchivoGrupo, lsNombreArchivo);
        END IF;

        -- --

     END LOOP;

    CLOSE c_grupo;
    -- --

   /*Archivo dummy para que el proceso no se caiga */
   gen_pkg_inter_archi.gen_pr_inici_inter(psCodigoPais, pscodigosistema, pscodigointerfaz, psnombrearchivo,
                                                  lsdirtempo, lsnombrearchivo, v_handle);
   utl_file.put_line(v_handle, '0');
   utl_file.fclose(v_handle);

   gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',lsdirtempo,psnombrearchivo,lsnombrearchivo);
   /**/

    RETURN;
EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_IMP_BOL67_CABEC_PERU: '||ls_sqlerrm);
END INT_PR_IMP_BOL67_CABEC_PERU;

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Boletas 067
                      Detalle para Peru
Fecha Creación      : 01/04/2013
Autor               : Carlos Bazalar
***************************************************************************/
PROCEDURE INT_PR_IMP_BOL67_DETAL_PERU(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      pscodigoPeriodo     VARCHAR2,
                                      psfechaFacturacion  VARCHAR2,
                                      psindicadorNovedad  VARCHAR2,
                                      psNombreArchivo     VARCHAR2,
                                      psNumeroLote        VARCHAR2)
IS
 l_oidPeriodo NUMBER;
 CURSOR c_documento(lnoidPeriodo NUMBER, lnNroGpo NUMBER) IS
 select rpad(decode(instr(cab.val_seri_docu_lega,'B'),0,'B','')||cab.val_seri_docu_lega||'-'||cab.num_docu_cont_inte,14,' ') || -- PK
       -- NGCA --
       'NGCA' ||
       lpad(ROW_NUMBER() OVER (PARTITION BY cab.num_docu_cont_inte ORDER BY dcl.sopo_oid_soli_posi),3,'0') || -- item_detalle,
       rpad(SUBSTR(SPC.COD_PERI, -2, 2) || sp.VAL_CODI_VENT_FICT || DECODE(SP.VAL_PREC_CATA_UNIT_LOCA, 0, '0', '8')
       ,30,' ') || -- codigo_producto
       rpad('NIU',3,' ') ||
       -- NGCB --
       'NGCB' ||
      rpad( GEN_PKG_GENER.gen_fn_reemp_carac_extra(TRIM(imp_pkg_proce_laser.imp_fn_desc_produ(psCodigoPais,dcl.prod_oid_prod))
      --rpad( GEN_PKG_GENER.gen_fn_reemp_carac_extra(TRIM((SELECT VAL_I18N FROM GEN_I18N_SICC_PAIS WHERE ATTR_ENTI = 'MAE_PRODU' AND IDIO_OID_IDIO = 1 AND VAL_OID = dcl.PROD_OID_PROD))
       || decode(ictp.cod_tipo_prog,'B',' Bonificacion(es) otorgada(s) por haber alcanzado el volumen de compra requerido en el periodo. C' || SUBSTR(spc1.cod_peri, 5, 2) || '/' || SUBSTR(spc1.cod_peri, 1, 4) || ' al C' || SUBSTR(spc2.cod_peri, 5, 2) || '/' || SUBSTR(spc2.cod_peri, 1, 4) || ' del programa ' || cpg.num_conc,''))
       ,250,' ') ||
       -- NGCC --
       'NGCC' ||
       rpad(nvl(IMP_FN_DEVUE_LOTE_PROD_PERU(con.val_nume_soli, mp.cod_sap),' '),250,' ') || -- lote
       -- NGCD --
       'NGCD' ||
       rpad(ltrim(to_char(decode(dcl.val_prec_cata_unit_loca,'0',DECODE(dcl.val_prec_cont_unit_loca,0,0.01,dcl.val_prec_cont_unit_loca),''), '9999999990.00')),15,' ')||decode(dcl.val_prec_cata_unit_loca, 0, '02','  ') || --prec_catal_unit_con_descuento
       rpad(dcl.num_unid_aten || '.000',16,' ') ||-- cant_unidades_x_item
       rpad(ltrim(to_char(0, '9999999990.00')),15, ' ')||-- valor unitario sin IGV
       rpad(ltrim(to_char(0, '9999999990.00')),15,' ')||'01' || --precio venta unitario
       rpad(ltrim(to_char(0, '9999999990.00')),15,' ') || -- valor venta x item
       -- NGCE --
       'NGCE' ||
       rpad(ltrim(to_char(0, '9999999990.00')),15,' ') ||
       rpad(ltrim(to_char(0, '9999999990.00')),15,' ') ||
       rpad(decode(ictp.cod_tipo_prog,'B','31','13'),2,' ') ||
       rpad('1000',4,' ') ||
       rpad('IGV',6,' ') ||
       rpad('VAT',3,' ') ||
       -- NGCF --
       'NGCF' ||
       rpad(' ',15,' ') ||
       rpad(' ',15,' ') ||
       rpad('  ',2,' ') ||
       rpad(' ',4,' ') ||
       rpad(' ',6,' ') ||
       rpad(' ',3,' ') ||
       -- NGCG --
       'NGCG' ||
       rpad('PRECIO CAT. UNIT.',60,' ') ||
       rpad(ltrim(to_char(0, '9999999990.00')),100,' ') ||
       'NGCG' ||
       rpad('PRECIO CAT. UNIT. CON DSCTO',60,' ') ||
       rpad(ltrim(to_char(0, '9999999990.00')),100,' ') ||
       'NGCG' ||
       rpad('PRECIO CAT. CON DSCTO',60,' ') ||
       rpad(ltrim(to_char(0, '9999999990.00')),100,' ')||
       -- NGCH --
       'NGCH' ||
       rpad(' ',100,' ') ||
       rpad(' ',15,' ') ||
       rpad(' ',10,' ') ||
       rpad(' ',15,' ') valorColumna1,
       cab.val_seri_docu_lega,
       cab.num_docu_cont_inte
  FROM FAC_DOCUM_CONTA_CABEC CAB,
       FAC_TIPO_DOCUM        FTD,
       MAE_CLIEN             MC,
       PED_SOLIC_CABEC       CON,
       PED_SOLIC_CABEC       PSC,
       PED_TASA_IMPUE        PTI,
       ZON_REGIO             REG,
       ZON_ZONA              ZON,
       ZON_SECCI             SEC,
       ZON_TERRI             TER,
       SEG_PAIS              SP,
       INC_CONCU_PARAM_GENER  CPG,
       CRA_PERIO CP1,
       SEG_PERIO_CORPO SPC1,
       CRA_PERIO CP2,
       SEG_PERIO_CORPO SPC2,
       fac_docum_conta_linea dcl,
       ped_solic_posic sp,
       CRA_PERIO CP,
       SEG_PERIO_CORPO SPC,
       INC_CONCU_TIPO_PROG    ICTP,
       mae_produ mp,
     yob_secue_pedid y
 WHERE y.soca_oid_soli_cabe = con.oid_soli_cabe
   AND  SP.OID_PAIS = MC.PAIS_OID_PAIS
   and psc.ictp_oid_tipo_prog=ictp.oid_tipo_prog(+)
   AND MC.OID_CLIE = CON.CLIE_OID_CLIE
   and CAB.SOCA_OID_SOLI_CABE = PSC.SOCA_OID_SOLI_CABE
   and psc.oid_soli_cabe=sp.soca_oid_soli_cabe
   AND PSC.COPA_OID_PARA_GENE = CPG.OID_PARA_GRAL(+)
   AND CPG.PERD_OID_PERI_DESD = CP1.OID_PERI(+)
   AND CP1.PERI_OID_PERI = SPC1.OID_PERI(+)
   AND CPG.PERD_OID_PERI_HAST = CP2.OID_PERI(+)
   AND CP2.PERI_OID_PERI = SPC2.OID_PERI(+)
   AND CON.OID_SOLI_CABE = CAB.SOCA_OID_SOLI_CABE
   AND CON.TAIM_OID_TASA_IMPU = PTI.OID_TASA_IMPU
   AND CAB.TIDO_OID_TIPO_DOCU = FTD.OID_TIPO_DOCU
   AND CAB.ZORG_OID_REGI = REG.OID_REGI
   AND CAB.ZZON_OID_ZONA = ZON.OID_ZONA
   AND CAB.ZSCC_OID_SECC = SEC.OID_SECC
   AND CAB.TERR_OID_TERR = TER.OID_TERR
   and dcl.dcca_oid_cabe = cab.oid_cabe
   and dcl.prod_oid_prod = mp.oid_prod
   and dcl.num_unid_aten <> 0
   and dcl.ind_no_impr = '0'
   and sp.oid_soli_posi = dcl.sopo_oid_soli_posi
   AND CP.PERI_OID_PERI = SPC.OID_PERI
   and cp.oid_peri = cab.perd_oid_peri
   AND CAB.PERD_OID_PERI = lnoidperiodo
   AND CAB.FEC_FACT = TO_DATE(psfechaFacturacion, 'DD/MM/YYYY') -- fecha_facturacion
   AND FTD.COD_TIPO_DOCU = '012'
   AND EXISTS
       (SELECT NULL
          FROM FAC_DOCUM_CONTA_LINEA DET,
               PED_SOLIC_POSIC       PSP,
               PRE_OFERT_DETAL       POD
         WHERE DET.SOPO_OID_SOLI_POSI = PSP.OID_SOLI_POSI
           AND PSP.OFDE_OID_DETA_OFER = POD.OID_DETA_OFER(+)
           AND DET.NUM_UNID_ATEN > 0
           AND DET.DCCA_OID_CABE = CAB.OID_CABE
           AND NOT EXISTS
         (SELECT NULL
                  FROM FAC_TIPO_OFERT_EXCLU TOE
                 WHERE TOE.TOFE_OID_TIPO_OFER = POD.TOFE_OID_TIPO_OFER))
    AND y.NUM_GRUP = lnNroGpo
  order by y.num_secu_pedi,valorColumna1;


 TYPE documentorecord IS RECORD (
        valorColumna1 varchar2(4000),
        val_seri_docu_lega FAC_DOCUM_CONTA_CABEC.VAL_SERI_DOCU_LEGA%TYPE,
        num_docu_cont_inte FAC_DOCUM_CONTA_CABEC.NUM_DOCU_CONT_INTE%TYPE
 );

 TYPE documentotype IS TABLE OF documentorecord;
 r_documento        documentotype;

 /* Variables usadas para la generacion del archivo de texto */
 lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
 v_handle            UTL_FILE.FILE_TYPE;

 lsLinea             VARCHAR2(10000);
 lsNombreArchivo     VARCHAR2(50);

 lbAbrirUtlFile      BOOLEAN;

 lnNumeroGrupo       NUMBER(3);
 lsNombreArchivoGrupo VARCHAR2(50);

 cursor c_grupo is
 select distinct num_grup from yob_secue_pedid order by num_grup;

lbIngreso      BOOLEAN;

BEGIN
    l_oidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(pscodigoPeriodo);

    -- Abrimos el cursor de grupos
    OPEN c_grupo;

     LOOP
        FETCH c_grupo INTO lnNumeroGrupo;
        EXIT WHEN c_grupo%NOTFOUND;

        -- Generamos los archivos de cada uno de los grupos

        /* Generando Archivo de Texto (Detalle) */
        lbAbrirUtlFile := TRUE;

        -- Abrimos el cursor
        OPEN c_documento(l_oidPeriodo, lnNumeroGrupo);
        LOOP
            FETCH c_documento BULK COLLECT INTO r_documento LIMIT w_filas;

            IF  r_documento.COUNT > 0 THEN

                /* Procedimiento inicial para generar interfaz */
                IF lbAbrirUtlFile THEN
                    -- Armamos el nombre del archivo
                    lsNombreArchivoGrupo := psNombreArchivo || psNumeroLote || lpad(lnNumeroGrupo, 3, '0');
                    -- --
                    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER_MAXCA(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                             lsNombreArchivoGrupo, UTL_FILE_MAXI_CARA, FALSE, lsDirTempo, lsNombreArchivo, V_HANDLE);
                    lbAbrirUtlFile := FALSE;
                END IF;

                FOR i IN r_documento.FIRST..r_documento.LAST LOOP
                    lsLinea :=  r_documento(i).valorColumna1 ;
                                         
                    BEGIN
                        lbIngreso := true;
                    INSERT INTO IMP_TMP_FELEC_DETAL(TIP_DOC,
                                                    NUM_SERI,
                                                    NUM_DOCU_INTE,
                                                    NUM_SECU,
                                                    TXT_DETA1,
                                                    TXT_DETA2)
                                             VALUES('02',
                                                    r_documento(i).val_seri_docu_lega,
                                                    r_documento(i).num_docu_cont_inte,
                                to_number(substr(r_documento(i).valorColumna1, 19,3)),
                                                    r_documento(i).valorColumna1,
                                                    null);
                    EXCEPTION
                    WHEN DUP_VAL_ON_INDEX THEN 
                         lbIngreso := false;
                    END;
                                                    
                    UTL_FILE.PUT_LINE (V_HANDLE, lslinea);
                END LOOP;
            END IF;

            EXIT WHEN c_documento%NOTFOUND;
        END LOOP;
        -- Cerramos el cursor
        CLOSE c_documento;

        IF NOT lbAbrirUtlFile THEN
              utl_file.fclose(V_HANDLE);
             /* Procedimiento final para generar interfaz */
             GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, lsNombreArchivoGrupo, lsNombreArchivo);
        END IF;

        -- --

     END LOOP;

    CLOSE c_grupo;
    -- --

   /*Archivo dummy para que el proceso no se caiga */
   gen_pkg_inter_archi.gen_pr_inici_inter(psCodigoPais, pscodigosistema, pscodigointerfaz, psnombrearchivo,
                                                  lsdirtempo, lsnombrearchivo, v_handle);
   utl_file.put_line(v_handle, '0');
   utl_file.fclose(v_handle);

   gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',lsdirtempo,psnombrearchivo,lsnombrearchivo);
   /**/

    RETURN;
EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_IMP_BOL67_DETAL_PERU: '||ls_sqlerrm);
END INT_PR_IMP_BOL67_DETAL_PERU;
 /***************************************************************************
  Descripcion       : Guarda e historico del
  Fecha Creacion    : 21/05/2013
  Autor             : Jose Cairampoma
  ***************************************************************************/
PROCEDURE imp_pr_histo_bolet_elect(psindicadornovedad VARCHAR2) IS

BEGIN

  IF psindicadornovedad = '1' THEN

    INSERT INTO yob_carga_lotes_traza_histo
      (cod_peri,
       num_pedi,
       cod_sap,
       uni_aten,
       num_lote,
       fec_carg,
       num_lote_envio,
       ind_envi_fe,
       oid_catr,
       nom_archi_origi)
      SELECT cod_peri,
             num_pedi,
             cod_sap,
             uni_aten,
             num_lote,
             fec_carg,
             num_lote_envio,
             '1',
             oid_catr,
             nom_archi_origi
        FROM yob_carga_lotes_traza x
       WHERE nvl(x.ind_envi_fe,
                 0) = 0;

    DELETE yob_carga_lotes_traza x
     WHERE nvl(x.ind_envi_fe,
               0) = 0;
  END IF;
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM,
                         1,
                         1000);
    raise_application_error(-20123,
                            'ERROR IMP_PR_HISTO_BOLET_ELECT: ' || ls_sqlerrm);
END imp_pr_histo_bolet_elect;

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Notas Debito
                      Cabecera para Peru
Fecha Creación      : 01/04/2013
Autor               : Carlos Bazalar
***************************************************************************/
PROCEDURE INT_PR_IMP_NOTDE_CABEC_PERU(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      pscodigoPeriodo     VARCHAR2,
                                      psfechaFacturacion  VARCHAR2,
                                      psindicadorNovedad  VARCHAR2,
                                      psNombreArchivo     VARCHAR2)
IS
 l_oidPeriodo NUMBER;
 CURSOR c_documento(lnoidPeriodo NUMBER) IS
 select rpad(cab.val_seri_docu_lega||'-'||cab.num_docu_cont_inte,14,' ' )|| -- PK
       -- NGAA --
       'NGAA' ||
       rpad('2.0',10,' ') || -- version_UBL
       rpad('1.0',10,' ') || -- version_estructura_documento
       rpad(decode(cabref.tido_oid_tipo_docu,1,'331','331'),3,' ') || -- tipo_documento
       to_char(CON.FEC_FACT,'yyyy-mm-dd') || -- fecha_emision
       rpad('20100123763',11,' ') || -- RUC_belcorp
       rpad('6',1,' ') || -- tipo_documento
       rpad(cab.val_seri_docu_lega||'-'||cab.num_docu_cont_inte,13,' ') || -- Numeracion
       rpad(cabref.val_seri_docu_lega||'-'||cabref.num_docu_cont_inte,13,' ') || -- Numero Doc Ref
       rpad('02',2,' ') || -- tipo_nd
       -- NGAB --
       'NGAB' ||
       rpad('CETCO S.A.',100,' ') || --  razon_social
       -- NGAC --
       'NGAC' ||
       rpad('CETCO S.A.',100,' ') || --  razon_social
       -- NGAD --
       'NGAD' ||
       rpad('150131',6,' ')||rpad('AV. FELIPE PARDO Y ALIAGA 652-1201',100,' ')||rpad('URB CHACARILLA - SANTA CRUZ',25,' ')||
            rpad('LIMA',30,' ')||rpad('LIMA',30,' ')||rpad('SAN ISIDRO',30,' ')||'PE'|| -- domicilio fiscal
       -- NGBA --
       'NGBA' ||
       rpad(cab.val_nume_iden_fisc,15,' ')|| --Documento Identidad,
       decode(cabref.tido_oid_tipo_docu,1,'6','1') || -- tipo Documento Identidad
       rpad(GEN_PKG_GENER.gen_fn_reemp_carac_extra(cab.val_ape1||' '||cab.val_ape2||' '||cab.val_nom1||' '||cab.val_nom2),100,' ') || -- apellidos_y_nombres
       -- NGBB --
       'NGBB' ||
       rpad('AUMENTO DE  PRECIO',250,' ')  || --nuevo_sol_peruano*/
       -- NGBC --
       'NGBC' ||
       'PEN'  || --nuevo_sol_peruano*/
       rpad(/*decode(cabref.tido_oid_tipo_docu,1,'F','B') || */cabref.val_seri_docu_lega||'-'||cabref.num_docu_cont_inte,13,' ') ||
       decode(cabref.tido_oid_tipo_docu,1,'01','03') ||
       rpad(' ',30,' ') ||
       rpad(' ',2,' ') ||
       rpad(' ',30,' ') ||
       rpad(' ',2,' ') ||
       'NGBD' ||
       rpad(GEN_PKG_GENER.gen_fn_reemp_carac_extra(nvl(
            (select mcc.val_text_comu
            from MAE_CLIEN_COMUN mcc
            where mcc.CLIE_OID_CLIE(+) = con.clie_oid_clie
            AND mcc.TICM_OID_TIPO_COMU(+) = 3),' '))
            ,250,' '
            ) || -- email
       'NGDA' ||
       --rpad('1001',4,' ') || rpad(TO_CHAR(nvl((cab.val_tota_paga_loca - cab.imp_flet_tota_loca + cab.imp_desc_tota_loca),0), '9999999990.00'),15,' ') || -- total valor ventas operaciones grabadas
       rpad('1001',4,' ') || rpad(ltrim(TO_CHAR(nvl(cab.VAL_PREC_NETO_TOTA_LOCA,0)+nvl(cab.VAL_TOTA_GAST_ADMI_SIN_IMPU,0), '9999999990.00')),15,' ') || -- total valor ventas operaciones grabadas
       rpad('1002',4,' ') || rpad('0.00',15,' ') || -- total valor ventas operaciones inafectas
       rpad('1003',4,' ') || rpad('0.00',15,' ') || -- total valor ventas operaciones exonerdas
       rpad(ltrim(TO_CHAR(cab.val_tota_paga_loca, '9999999990.00')),15,' ') || -- immporte total de la venta
       rpad(ltrim(TO_CHAR(cab.imp_impu_tota_loca, '9999999990.00')),15,' ') ||  rpad(ltrim(TO_CHAR(cab.imp_impu_tota_loca, '9999999990.00')),15,' ') || rpad('1000',4,' ') || rpad('IGV',6,' ') || rpad('VAT',3,' ') || -- sumatoria IGV
       rpad(' ',15,' ') ||  rpad(' ',15,' ') || rpad('    ',4,' ') || rpad('   ',6,' ') || rpad('   ',3,' ') || -- sumatoria ISC
       rpad(' ',15,' ') ||  rpad(' ',15,' ') || rpad('    ',4,' ') || rpad('   ',6,' ') || rpad('   ',3,' ') || -- sumatoria otros tributos
       rpad(' ', 15,' ') || -- sumatoria otros cargos
       rpad('2005',4,' ') || rpad(ltrim(to_char(
        cab.imp_desc_tota_loca-cab.val_prec_cont_tota_loca, '9999999990.00')),15,' ') || -- descuentos
       -- NGEA --
       'NGEA' ||
       rpad('ZONA-TERRITORIO',60,' ') ||
       rpad(ZON.COD_ZONA || '-' || TER.COD_TERR,194,' ') ||
       'NGEA' ||
       rpad('CAMPANA',60,' ') ||
       rpad(spc.cod_peri,194,' ') ||
       'NGEA' ||
       rpad('TOTAL PRODUCTOS',60,' ') ||
       rpad((SELECT SUM(SP.NUM_UNID_ATEN) FROM FAC_DOCUM_CONTA_LINEA SP WHERE SP.DCCA_OID_CABE = CAB.OID_CABE AND SP.IND_NO_IMPR = '0'),194,' ') ||
       'NGEA' ||
       rpad('TOTAL PRECIO CAT',60,' ') ||
       rpad(ltrim(TO_CHAR(cab.val_tota_paga_loca-cab.imp_flet_tota_loca+cab.imp_desc_tota_loca- nvl(cab.val_tota_gast_admi,0), '9999999990.00')),194,' ') ||
       'NGEA' ||
       rpad('DESCUENTO',60,' ') ||

       rpad(ltrim(TO_CHAR(cab.imp_desc_tota_loca-cab.val_prec_cont_tota_loca, '9999999990.00')),194,' ') ||

--       rpad(ltrim(TO_CHAR(decode(cabref.tido_oid_tipo_docu,1,cab.imp_des1_sin_impu_tota,cab.imp_desc_tota_loca-cab.val_prec_cont_tota_loca), '9999999990.00')),194,' ') ||
       'NGEA' ||
       rpad('BONIFICACION',60,' ') ||
       rpad(ltrim(TO_CHAR(cab.val_prec_cont_tota_loca, '9999999990.00')),194,' ') ||
       'NGEA' ||
       rpad('TOTAL PRECIO CAT MENOS DCT',60,' ') ||
       rpad(ltrim(TO_CHAR(cab.val_tota_paga_loca-cab.imp_flet_tota_loca - nvl(cab.val_tota_gast_admi,0), '9999999990.00')),194,' ') ||
       'NGEA' ||
       rpad('TOT OPE GRA',60,' ') ||
       rpad(ltrim(TO_CHAR(cab.val_prec_cont_tota_loca, '9999999990.00')),194,' ') ||
       'NGEA' ||
       rpad('OFI PRI DIR',60,' ') ||
       rpad('AV. FELIPE PARDO Y ALIAGA 652-1201',194,' ') ||
       'NGEA' ||
       rpad('OFI PRI URB DIS PRO',60,' ') ||
       rpad('URB CHACARILLA - SANTA CRUZ, SAN ISIDRO, LIMA',114,' ') 
       as valorColumna1,
       rpad(' ',80,' ') ||
       'NGEA' ||
       rpad('OFI PRI TLF',60,' ') ||
       rpad('TELF.: 211 3400',194,' ') ||
       'NGEA' ||
       rpad('PTO EMI DIR',60,' ') ||
       rpad('AV. SAN GENARO 150',194,' ') ||
       'NGEA' ||
       rpad('PTO EMI URB DIS PRO',60,' ') ||
       rpad('URB. MOLITALIA - LOS OLIVOS, LIMA',194,' ') ||
        'NGEA' ||
       rpad('TI NO DE EL',60,' ') ||
       rpad('AUMENTO EN VALOR',194,' ')  ||
        'NGEA' ||
       rpad('BOL DES',60,' ') ||
       rpad(con.val_nume_soli,194,' ')  ||
        'NGEA' ||
       rpad('BOL DES REF',60,' ') ||
       rpad(conref.val_nume_soli,194,' ')  ||
        'NGEA' ||
       rpad('CAMP REF',60,' ') ||
       rpad(spcr.cod_peri,194,' ')  ||
        'NGEA' ||
       rpad('DOC LEG REF',60,' ') ||
       rpad(cabref.val_seri_docu_lega||'-'||cabref.num_docu_cont_inte,194,' ')  ||
        'NGEA' ||
       rpad('DIR CLI',60,' ') ||
       rpad(GEN_PKG_GENER.gen_fn_reemp_carac_extra(cab.val_dire_comp),194,' ')  ||
        'NGEA' ||
       rpad('UBIGEO',60,' ') ||
       rpad( GEN_PKG_GENER.gen_fn_reemp_carac_extra(GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(cab.pais_OID_PAIS, mc.OID_CLIE, 4) || '/' ||
           GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(cab.pais_OID_PAIS, mc.OID_CLIE, 3) || '/' ||
           GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(cab.pais_OID_PAIS, mc.OID_CLIE, 2) || '/' ||
           GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(cab.pais_OID_PAIS, mc.OID_CLIE, 1)),194,' ') ||
        'NGEA' ||
       rpad('COD CLI',60,' ') ||
       rpad(mc.cod_clie,194,' ')  ||
        'NGEA' ||
       rpad('MONTO LETRAS',60,' ') ||
       rpad(GEN_FN_NUME_TO_TEXT(TRUNC(cab.val_tota_paga_loca)) || ' y ' || TO_CHAR((cab.val_tota_paga_loca - TRUNC(cab.val_tota_paga_loca)) * 100)  || '/100 NUEVOS SOLES',194,' ')  ||
        'NGEA' ||
       rpad('IMPRIME',60,' ') ||
       rpad(mcda.ind_impr_docu,194,' ') || 
       'NGEA' ||
       rpad('ORIGEN',60,' ') ||
       rpad('VENTA DIRECTA',194,' ')  ||
       -- NGFA --
       'NGFA' ||
       'NG00' ||
       rpad('Representacion impresa de la nota de debito electronica',100,' ')  || -- letras
       -- NGFB --
       'NGFB' ||
       'NG99' ||
       rpad('Autorizado mediante Resolucion N 0180050000840/SUNAT',100,' ') 
       as valorColumna2,
       cab.val_seri_docu_lega,
       cab.num_docu_cont_inte
  FROM FAC_DOCUM_CONTA_CABEC CAB,
       FAC_TIPO_DOCUM        FTD,
       MAE_CLIEN             MC,
       MAE_CLIEN_DATOS_ADICI MCDA,
       PED_SOLIC_CABEC       CON,
       PED_TASA_IMPUE        PTI,
       ZON_REGIO             REG,
       ZON_ZONA              ZON,
       ZON_SECCI             SEC,
       ZON_TERRI             TER,
       SEG_PAIS              SP,
       CRA_PERIO CP,
       SEG_PERIO_CORPO SPC,
       ped_solic_cabec conref,
       cra_perio  cpr,
       seg_perio_corpo spcr,
       fac_docum_conta_cabec cabref
 WHERE SP.OID_PAIS = MC.PAIS_OID_PAIS
   AND MC.OID_CLIE = CON.CLIE_OID_CLIE
   AND MC.OID_CLIE = MCDA.CLIE_OID_CLIE
   AND CON.OID_SOLI_CABE = CAB.SOCA_OID_SOLI_CABE
   and con.soca_oid_docu_refe=conref.oid_soli_cabe
   and conref.perd_oid_peri=cpr.oid_peri
   and cpr.peri_oid_peri=spcr.oid_peri
   and conref.oid_soli_cabe=cabref.soca_oid_soli_cabe
   and exists (select 1 from fac_docum_conta_linea x, fac_docum_conta_linea y where x.prod_oid_prod=y.prod_oid_prod and x.dcca_oid_cabe=cab.oid_cabe and y.dcca_oid_cabe=cabref.oid_cabe)
   AND CON.TAIM_OID_TASA_IMPU = PTI.OID_TASA_IMPU
   AND CAB.TIDO_OID_TIPO_DOCU = FTD.OID_TIPO_DOCU
   AND CAB.ZORG_OID_REGI = REG.OID_REGI
   AND CAB.ZZON_OID_ZONA = ZON.OID_ZONA
   AND CAB.ZSCC_OID_SECC = SEC.OID_SECC
   AND CAB.TERR_OID_TERR = TER.OID_TERR
   AND CP.PERI_OID_PERI = SPC.OID_PERI
   and cp.oid_peri = cab.perd_oid_peri
   AND CAB.PERD_OID_PERI >= lnoidperiodo
   AND CAB.FEC_FACT = TO_DATE(psfechaFacturacion, 'DD/MM/YYYY') -- fecha_facturacion
   AND CON.IND_INTE_LARI_GENE = psindicadorNovedad -- en peru es 1
   AND FTD.OiD_TIPO_DOCU in (34,40)
   AND EXISTS
       (SELECT NULL
          FROM FAC_DOCUM_CONTA_LINEA DET,
               PED_SOLIC_POSIC       PSP,
               PRE_OFERT_DETAL       POD
         WHERE DET.SOPO_OID_SOLI_POSI = PSP.OID_SOLI_POSI
           AND PSP.OFDE_OID_DETA_OFER = POD.OID_DETA_OFER(+)
           AND DET.NUM_UNID_ATEN > 0
           AND DET.DCCA_OID_CABE = CAB.OID_CABE
           AND NOT EXISTS
         (SELECT NULL
                  FROM FAC_TIPO_OFERT_EXCLU TOE
                 WHERE TOE.TOFE_OID_TIPO_OFER = POD.TOFE_OID_TIPO_OFER))

order by 1;

 TYPE documentorecord IS RECORD (
        valorColumna1 varchar2(4000),
        valorColumna2 varchar2(4000),
        val_seri_docu_lega FAC_DOCUM_CONTA_CABEC.VAL_SERI_DOCU_LEGA%TYPE,
        num_docu_cont_inte FAC_DOCUM_CONTA_CABEC.NUM_DOCU_CONT_INTE%TYPE
 );

 TYPE documentotype IS TABLE OF documentorecord;
 r_documento        documentotype;

 /* Variables usadas para la generacion del archivo de texto */
 lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
 v_handle            UTL_FILE.FILE_TYPE;

 lsLinea             VARCHAR2(10000);
 lsNombreArchivo     VARCHAR2(50);

 lbAbrirUtlFile      BOOLEAN;
 lbIngreso      BOOLEAN;
BEGIN
    l_oidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(pscodigoPeriodo);

    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;

    -- Abrimos el cursor
    OPEN c_documento(l_oidPeriodo);
    LOOP
        FETCH c_documento BULK COLLECT INTO r_documento LIMIT w_filas;

        /* Procedimiento inicial para generar interfaz */
        IF lbAbrirUtlFile THEN
              GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER_MAXCA(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                     psNombreArchivo, UTL_FILE_MAXI_CARA, lsDirTempo, lsNombreArchivo, V_HANDLE);
           lbAbrirUtlFile := FALSE;
        END IF;

        IF  r_documento.COUNT > 0 THEN
            FOR i IN r_documento.FIRST..r_documento.LAST LOOP
                lsLinea :=  r_documento(i).valorColumna1 ||';'||
                            r_documento(i).valorColumna2 ;
                   
                BEGIN
                    lbIngreso := true;
                INSERT INTO IMP_TMP_FELEC_CABEC(TIP_DOC,
                                                NUM_SERI,
                                                NUM_DOCU_INTE,
                                                FEC_FACT,
                                                TXT_CABE1,
                                                TXT_CABE2)
                                         VALUES('06',
                                                r_documento(i).val_seri_docu_lega,
                                                r_documento(i).num_docu_cont_inte,
                                                psfechaFacturacion,
                                                r_documento(i).valorColumna1,
                                                r_documento(i).valorColumna2);
                EXCEPTION
                WHEN DUP_VAL_ON_INDEX THEN 
                     lbIngreso := false;
                END;
                                   
                UTL_FILE.PUT_LINE (V_HANDLE, lslinea);
            END LOOP;
        END IF;

        EXIT WHEN c_documento%NOTFOUND;
    END LOOP;
    -- Cerramos el cursor
    CLOSE c_documento;

    IF NOT lbAbrirUtlFile THEN
          utl_file.fclose(V_HANDLE);
         /* Procedimiento final para generar interfaz */
         GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;
EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_IMP_NOTDE_CABEC_PERU: '||ls_sqlerrm);
END INT_PR_IMP_NOTDE_CABEC_PERU;

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Notas Debito
                      Detalle para Peru
Fecha Creación      : 01/04/2013
Autor               : Carlos Bazalar
***************************************************************************/
PROCEDURE INT_PR_IMP_NOTDE_DETAL_PERU(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      pscodigoPeriodo     VARCHAR2,
                                      psfechaFacturacion  VARCHAR2,
                                      psindicadorNovedad  VARCHAR2,
                                      psNombreArchivo     VARCHAR2)
IS
 l_oidPeriodo NUMBER;
 CURSOR c_documento(lnoidPeriodo NUMBER) IS
 select rpad(cab.val_seri_docu_lega||'-'||cab.num_docu_cont_inte,14,' ' )|| -- PK
       -- NGCA --
       'NGCA' ||
       rpad('NIU',3,' ') ||
       rpad(SUBSTR(SPC.COD_PERI, -2, 2) || sp.VAL_CODI_VENT || DECODE(SP.VAL_PREC_CATA_UNIT_LOCA, 0, '0', '8'),30,' ') || -- codigo_producto
       rpad(dcl.num_unid_aten || '.000',16,' ') ||-- cant_unidades_x_item
       -- NGCB --
       'NGCB' ||
       --rpad(GEN_PKG_GENER.gen_fn_reemp_carac_extra(TRIM((SELECT VAL_I18N FROM GEN_I18N_SICC_PAIS WHERE ATTR_ENTI = 'MAE_PRODU' AND IDIO_OID_IDIO = 1 AND VAL_OID = dcl.PROD_OID_PROD))),250,' ')||
       rpad(GEN_PKG_GENER.gen_fn_reemp_carac_extra(TRIM(imp_pkg_proce_laser.imp_fn_desc_produ(psCodigoPais,dcl.prod_oid_prod))),250,' ')||
       -- NGCC --
       'NGCC' ||
       lpad(ROW_NUMBER() OVER (PARTITION BY cab.num_docu_cont_inte ORDER BY dcl.sopo_oid_soli_posi),3,'0') || -- item_detalle,
       case when dcl.val_prec_cata_unit_loca<>0 then rpad(ltrim(to_char(dcl.val_prec_sin_impu_unit, '9999999990.00')),15,' ')
       else
       rpad(ltrim(to_char(0, '9999999990.00')),15,' ')
       end ||
       case when dcl.val_prec_cata_unit_loca<>0 then rpad(ltrim(to_char(dcl.val_prec_cata_unit_loca, '9999999990.00')),15,' ')
       else
       rpad(ltrim(to_char(0, '9999999990.00')),15,' ')
       end
       || '01' || --prec_catal_unit_con_descuento
       case when dcl.val_prec_cata_unit_loca<>0 then rpad(ltrim(to_char(dcl.val_prec_neto_tota_loca, '9999999990.00')),15,' ')
       else
       rpad(ltrim(to_char(0, '9999999990.00')),15,' ')
       end ||
       -- NGCD --
       'NGCD' ||
       rpad(ltrim(to_char(decode(dcl.val_prec_cata_unit_loca,0,0,dcl.imp_impu_tota_loca), '9999999990.00')),15,' ') ||
       rpad(ltrim(to_char(decode(dcl.val_prec_cata_unit_loca,0,0,dcl.imp_impu_tota_loca), '9999999990.00')),15,' ') ||
       rpad(decode(dcl.val_prec_cata_unit_loca,0,'31','10'),2,' ') ||
       rpad('1000',4,' ') ||
       rpad('IGV',6,' ') ||
       rpad('VAT',3,' ') ||
       -- NGCE --
       'NGCE' ||
       rpad(' ',15,' ') ||
       rpad(' ',15,' ') ||
       rpad('  ',2,' ') ||
       rpad(' ',4,' ') ||
       rpad(' ',6,' ') ||
       rpad(' ',3,' ') ||
       -- NGCF --
       'NGCF' ||
       rpad('PRECIO CAT. UNIT.',60,' ') ||
       rpad(ltrim(to_char(dcl.val_prec_cata_unit_loca+dcl.val_prec_cont_unit_loca, '9999999990.00')),100,' ') ||
       'NGCF' ||
       rpad('PRECIO CAT. UNIT. CON DSCTO',60,' ') ||
       rpad(ltrim(to_char(dcl.val_prec_fact_unit_loca, '9999999990.00')),100,' ') ||
       'NGCF' ||
       rpad('PRECIO CAT. CON DSCTO',60,' ') ||
       rpad(ltrim(to_char(dcl.val_prec_fact_tota_loca, '9999999990.00')),100,' ') valorColumna1,
       cab.val_seri_docu_lega,
       cab.num_docu_cont_inte
  FROM FAC_DOCUM_CONTA_CABEC CAB,
       FAC_TIPO_DOCUM        FTD,
       MAE_CLIEN             MC,
       PED_SOLIC_CABEC       CON,
       PED_TASA_IMPUE        PTI,
       ZON_REGIO             REG,
       ZON_ZONA              ZON,
       ZON_SECCI             SEC,
       ZON_TERRI             TER,
       SEG_PAIS              SP,
       fac_docum_conta_linea dcl,
       ped_solic_posic sp,
       CRA_PERIO CP,
     SEG_PERIO_CORPO SPC
 WHERE SP.OID_PAIS = MC.PAIS_OID_PAIS
   AND MC.OID_CLIE = CON.CLIE_OID_CLIE
   AND CON.OID_SOLI_CABE = CAB.SOCA_OID_SOLI_CABE
   AND CON.TAIM_OID_TASA_IMPU = PTI.OID_TASA_IMPU
   AND CAB.TIDO_OID_TIPO_DOCU = FTD.OID_TIPO_DOCU
   AND CAB.ZORG_OID_REGI = REG.OID_REGI
   AND CAB.ZZON_OID_ZONA = ZON.OID_ZONA
   AND CAB.ZSCC_OID_SECC = SEC.OID_SECC
   AND CAB.TERR_OID_TERR = TER.OID_TERR
   and dcl.dcca_oid_cabe = cab.oid_cabe
   and dcl.num_unid_aten <> 0
   and dcl.ind_no_impr = '0'
   and sp.oid_soli_posi = dcl.sopo_oid_soli_posi
   AND CP.PERI_OID_PERI = SPC.OID_PERI
   and cp.oid_peri = cab.perd_oid_peri
   AND CAB.PERD_OID_PERI >=lnoidperiodo
   AND CAB.FEC_FACT = TO_DATE(psfechaFacturacion, 'DD/MM/YYYY') -- fecha_facturacion
   -- AND CON.IND_INTE_LARI_GENE = '1' -- en peru es 1
   AND FTD.OiD_TIPO_DOCU in (34,40)
   AND EXISTS
       (SELECT NULL
          FROM FAC_DOCUM_CONTA_LINEA DET,
               PED_SOLIC_POSIC       PSP,
               PRE_OFERT_DETAL       POD
         WHERE DET.SOPO_OID_SOLI_POSI = PSP.OID_SOLI_POSI
           AND PSP.OFDE_OID_DETA_OFER = POD.OID_DETA_OFER(+)
           AND DET.NUM_UNID_ATEN > 0
           AND DET.DCCA_OID_CABE = CAB.OID_CABE
           AND NOT EXISTS
         (SELECT NULL
                  FROM FAC_TIPO_OFERT_EXCLU TOE
                 WHERE TOE.TOFE_OID_TIPO_OFER = POD.TOFE_OID_TIPO_OFER));


 TYPE documentorecord IS RECORD (
        valorColumna1 varchar2(4000),
        val_seri_docu_lega FAC_DOCUM_CONTA_CABEC.VAL_SERI_DOCU_LEGA%TYPE,
        num_docu_cont_inte FAC_DOCUM_CONTA_CABEC.NUM_DOCU_CONT_INTE%TYPE
 );

 TYPE documentotype IS TABLE OF documentorecord;
 r_documento        documentotype;

 /* Variables usadas para la generacion del archivo de texto */
 lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
 v_handle            UTL_FILE.FILE_TYPE;

 lsLinea             VARCHAR2(10000);
 lsNombreArchivo     VARCHAR2(50);

 lbAbrirUtlFile      BOOLEAN;
 lbIngreso      BOOLEAN;
BEGIN
    l_oidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(pscodigoPeriodo);

    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;

    -- Abrimos el cursor
    OPEN c_documento(l_oidPeriodo);
    LOOP
        FETCH c_documento BULK COLLECT INTO r_documento LIMIT w_filas;

        /* Procedimiento inicial para generar interfaz */
        IF lbAbrirUtlFile THEN
              GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER_MAXCA(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                     psNombreArchivo, UTL_FILE_MAXI_CARA, lsDirTempo, lsNombreArchivo, V_HANDLE);
           lbAbrirUtlFile := FALSE;
        END IF;

        IF  r_documento.COUNT > 0 THEN
            FOR i IN r_documento.FIRST..r_documento.LAST LOOP
                lsLinea :=  r_documento(i).valorColumna1 ;
                BEGIN
                    lbIngreso := true;
                INSERT INTO IMP_TMP_FELEC_DETAL(TIP_DOC,
                                                NUM_SERI,
                                                NUM_DOCU_INTE,
                                                NUM_SECU,
                                                TXT_DETA1,
                                                TXT_DETA2)
                                         VALUES('06',
                                                r_documento(i).val_seri_docu_lega,
                                                r_documento(i).num_docu_cont_inte,
                            to_number(substr(r_documento(i).valorColumna1,326,3)),
                                                r_documento(i).valorColumna1,
                                                null);
                EXCEPTION
                WHEN DUP_VAL_ON_INDEX THEN 
                     lbIngreso := false;
                END;

                UTL_FILE.PUT_LINE (V_HANDLE, lslinea);
            END LOOP;
        END IF;

        EXIT WHEN c_documento%NOTFOUND;
    END LOOP;
    -- Cerramos el cursor
    CLOSE c_documento;

    IF NOT lbAbrirUtlFile THEN
          utl_file.fclose(V_HANDLE);
         /* Procedimiento final para generar interfaz */
         GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;
EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_IMP_NOTDE_DETAL_PERU: '||ls_sqlerrm);
END INT_PR_IMP_NOTDE_DETAL_PERU;

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Notas Credito Facturas
                      Cabecera para Peru
Fecha Creación      : 01/04/2013
Autor               : Carlos Bazalar
***************************************************************************/
PROCEDURE INT_PR_IMP_NOCRF_CABEC_PERU(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      pscodigoPeriodo     VARCHAR2,
                                      psfechaFacturacion  VARCHAR2,
                                      psindicadorNovedad  VARCHAR2,
                                      psNombreArchivo     VARCHAR2)
IS
 l_oidPeriodo NUMBER;
 CURSOR c_documento(lnoidPeriodo NUMBER) IS
 select rpad(cab.val_seri_docu_lega||'-'||cab.num_docu_cont_inte,14,' ' )|| -- PK
       -- NGAA --
       'NGAA' ||
       rpad('2.0',10,' ') || -- version_UBL
       rpad('1.0',10,' ') || -- version_estructura_documento
       rpad('334',3,' ') || -- tipo_documento
       to_char(CON.FEC_FACT,'yyyy-mm-dd') || -- fecha_emision
       rpad('20100123763',11,' ') || -- RUC_belcorp
       rpad('6',1,' ') || -- tipo_documento
       rpad(cab.val_seri_docu_lega||'-'||cab.num_docu_cont_inte,13,' ') || -- Numeracion
       rpad(cabref.val_seri_docu_lega||'-'||cabref.num_docu_cont_inte,13,' ') || -- Numero Doc Ref
       rpad('07',2,' ') || -- tipo_nd
       -- NGAB --
       'NGAB' ||
       rpad('CETCO S.A.',100,' ') || --  razon_social
       -- NGAC --
       'NGAC' ||
       rpad('CETCO S.A.',100,' ') || --  razon_social
       -- NGAD --
       'NGAD' ||
       rpad('150131',6,' ')||rpad('AV. FELIPE PARDO Y ALIAGA 652-1201',100,' ')||rpad('URB CHACARILLA - SANTA CRUZ',25,' ')||
            rpad('LIMA',30,' ')||rpad('LIMA',30,' ')||rpad('SAN ISIDRO',30,' ')||'PE'|| -- domicilio fiscal
       -- NGBA --
       'NGBA' ||
       rpad(cab.val_nume_iden_fisc,15,' ')|| --Documento Identidad,
       '6' || -- tipo Documento Identidad
       rpad(GEN_PKG_GENER.gen_fn_reemp_carac_extra(cab.val_ape1||' '||cab.val_ape2||' '||cab.val_nom1||' '||cab.val_nom2),100,' ') || -- apellidos_y_nombres
       -- NGBB --
       'NGBB' ||
       rpad('DEVOLUCION',250,' ')  || --nuevo_sol_peruano*/
       -- NGBC --
       'NGBC' ||
       rpad(GEN_PKG_GENER.gen_fn_reemp_carac_extra(nvl(
            (select mcc.val_text_comu
            from MAE_CLIEN_COMUN mcc
            where mcc.CLIE_OID_CLIE(+) = con.clie_oid_clie
            AND mcc.TICM_OID_TIPO_COMU(+) = 3),' '))
            ,250,' '
            ) || -- email
       'NGBD' ||
       'PEN'  || --nuevo_sol_peruano*/
       rpad(cabref.val_seri_docu_lega||'-'||cabref.num_docu_cont_inte,13,' ') ||
       '01' ||
       rpad(' ',30,' ') ||
       rpad(' ',2,' ') ||
       rpad(' ',30,' ') ||
       rpad(' ',2,' ') ||
       'NGDA' ||
       rpad('1001',4,' ') || rpad(ltrim(TO_CHAR(nvl(abs(cab.VAL_PREC_NETO_TOTA_LOCA),0)+nvl(abs(cab.VAL_TOTA_GAST_ADMI_SIN_IMPU),0)+nvl(abs(cab.imp_flet_impu_tota_loca),0), '9999999990.00')),15,' ') || -- total valor ventas operaciones grabadas
       rpad('1002',4,' ') || rpad('0.00',15,' ') || -- total valor ventas operaciones inafectas
       rpad('1003',4,' ') || rpad('0.00',15,' ') || -- total valor ventas operaciones exonerdas
       rpad(ltrim(TO_CHAR(abs(cab.val_tota_paga_loca), '9999999990.00')),15,' ') || -- immporte total de la venta
       -- NGDB --
       'NGDB' ||
       rpad(ltrim(TO_CHAR(abs(cab.imp_impu_tota_loca), '9999999990.00')),15,' ') ||  rpad(ltrim(TO_CHAR(abs(cab.imp_impu_tota_loca), '9999999990.00')),15,' ') || rpad('1000',4,' ') || rpad('IGV',6,' ') || rpad('VAT',3,' ') || -- sumatoria IGV
       -- NGDC --
       'NGDC' ||
       rpad(' ',15,' ') ||  rpad(' ',15,' ') || rpad('    ',4,' ') || rpad('   ',6,' ') || rpad('   ',3,' ') || -- sumatoria ISC
       -- NGDD --
       'NGDD' ||
       rpad(' ',15,' ') ||  rpad(' ',15,' ') || rpad('    ',4,' ') || rpad('   ',6,' ') || rpad('   ',3,' ') || -- sumatoria otros tributos
       -- NGDE --
       'NGDE' ||
       rpad(' ', 15,' ') || -- sumatoria otros cargos
       -- NGDF --
       'NGDF' ||
       rpad('2005',4,' ') || rpad(ltrim(to_char(abs(cab.imp_des1_sin_impu_tota), '9999999990.00')),15,' ') || -- descuentos
       -- NGEA --
       'NGEA' ||
       rpad('ZONA-TERRITORIO',60,' ') ||
       rpad(ZON.COD_ZONA || '-' || TER.COD_TERR,194,' ') ||
       'NGEA' ||
       rpad('CAMPANA',60,' ') ||
       rpad(spc.cod_peri,194,' ') ||
       'NGEA' ||
       rpad('TOTAL PRODUCTOS',60,' ') ||
       rpad((SELECT SUM(abs(SP.NUM_UNID_ATEN)) FROM FAC_DOCUM_CONTA_LINEA SP WHERE SP.DCCA_OID_CABE = CAB.OID_CABE AND SP.IND_NO_IMPR = '0'),194,' ') ||
       'NGEA' ||
       rpad('TOTAL PRECIO CAT',60,' ') ||
       rpad(ltrim(TO_CHAR(abs(cab.val_tota_paga_loca) - abs(cab.imp_impu_tota_loca) - abs(cab.imp_flet_impu_tota_loca) - nvl(abs(cab.val_tota_gast_admi_sin_impu),0)+(abs(cab.imp_des1_sin_impu_tota) + abs(cab.imp_des3_sin_impu_tota)+abs(cab.val_prec_cont_sin_impu_tota)),'9999999990.00')),194,' ') ||
       'NGEA' ||
       rpad('DESCUENTO',60,' ') ||
       rpad(ltrim(TO_CHAR(abs(cab.imp_des1_sin_impu_tota) + abs(cab.imp_des3_sin_impu_tota),'9999999990.00')),194,' ') ||
       'NGEA' ||
       rpad('BONIFICACION',60,' ') ||
       rpad(ltrim(TO_CHAR(abs(cab.val_prec_cont_sin_impu_tota),'9999999990.00')),194,' ') ||
       'NGEA' ||
       rpad('TOTAL PRECIO CAT MENOS DCT',60,' ') ||
       rpad(ltrim(TO_CHAR((abs(cab.val_tota_paga_loca) - abs(cab.imp_impu_tota_loca) - abs(cab.imp_flet_impu_tota_loca) - nvl(abs(cab.val_tota_gast_admi_sin_impu),0)),'9999999990.00')),194,' ') ||
       'NGEA' ||
       rpad('TOT OPE GRA',60,' ') ||
       --rpad(' ',50,' ')   ||
       rpad(ltrim(TO_CHAR(abs(cab.val_prec_cont_tota_loca),'9999999990.00')),194,' ')   ||
       'NGEA' ||
       rpad('OFI PRI DIR',60,' ') ||
       rpad('AV. FELIPE PARDO Y ALIAGA 652-1201',194,' ') ||      
       'NGEA' ||
       rpad('OFI PRI URB DIS PRO',60,' ') ||
       rpad('URB CHACARILLA - SANTA CRUZ, SAN ISIDRO, LIMA',114,' ')
        as valorColumna1,
       rpad(' ',80,' ') ||
       'NGEA' ||
       rpad('OFI PRI TLF',60,' ') ||
       rpad('TELF.: 211 3400',194,' ') ||
			 'NGEA' ||
       rpad('PTO EMI DIR',60,' ') ||
       rpad('AV. SAN GENARO 150',194,' ') ||
       'NGEA' ||
       rpad('PTO EMI URB DIS PRO',60,' ') ||
       rpad('URB. MOLITALIA - LOS OLIVOS, LIMA',194,' ') ||
        'NGEA' ||
       rpad('TI NO CR EL',60,' ') ||
       rpad('DEVOLUCION POR ITEM',194,' ')  ||
        'NGEA' ||
       rpad('BOL DES',60,' ') ||
       rpad(con.val_nume_soli,194,' ')  ||
        'NGEA' ||
       rpad('BOL DES REF',60,' ') ||
       rpad(conref.val_nume_soli,194,' ')  ||
        'NGEA' ||
       rpad('CAMP REF',60,' ') ||
       rpad(spcr.cod_peri,194,' ')  ||
        'NGEA' ||
       rpad('DOC LEG REF',60,' ') ||
       rpad(cabref.val_seri_docu_lega||'-'||cabref.num_docu_cont_inte,194,' ')  ||
        'NGEA' ||
       rpad('DIR CLI',60,' ') ||
       rpad(GEN_PKG_GENER.gen_fn_reemp_carac_extra(cab.val_dire_comp),194,' ') ||
        'NGEA' ||
       rpad('UBIGEO',60,' ') ||
       rpad(            GEN_PKG_GENER.gen_fn_reemp_carac_extra(GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(cab.pais_OID_PAIS, mc.OID_CLIE, 4) || '/' ||
           GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(cab.pais_OID_PAIS, mc.OID_CLIE, 3) || '/' ||
           GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(cab.pais_OID_PAIS, mc.OID_CLIE, 2) || '/' ||
           GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(cab.pais_OID_PAIS, mc.OID_CLIE, 1)),194,' ') ||
        'NGEA' ||
       rpad('COD CLI',60,' ') ||
       rpad(mc.cod_clie,194,' ')  ||
        'NGEA' ||
       rpad('MONTO LETRAS',60,' ') ||
       rpad(GEN_FN_NUME_TO_TEXT(TRUNC(abs(cab.val_tota_paga_loca))) || ' y ' || TO_CHAR((abs(cab.val_tota_paga_loca) - TRUNC(abs(cab.val_tota_paga_loca))) * 100)  || '/100 NUEVOS SOLES',194,' ')  ||
        'NGEA' ||
       rpad('IMPRIME',60,' ') ||
       rpad(mcda.ind_impr_docu,194,' ')  ||
       'NGEA' ||
       rpad('ORIGEN',60,' ') ||
       rpad('VENTA DIRECTA',194,' ') ||
       -- NGFA --
       'NGFA' ||
       'NG00' ||
       rpad('Representacion impresa de la nota de credito electronica',100,' ')  || -- letras
       -- NGFB --
       'NGFB' ||
       'NG99' ||
       rpad('Autorizado mediante Resolucion N 0180050000840/SUNAT',100,' ') 
       as valorColumna2,
       cab.val_seri_docu_lega,
       cab.num_docu_cont_inte
  FROM FAC_DOCUM_CONTA_CABEC CAB,
       FAC_TIPO_DOCUM        FTD,
       MAE_CLIEN             MC,
       MAE_CLIEN_DATOS_ADICI MCDA,
       PED_SOLIC_CABEC       CON,
       PED_TASA_IMPUE        PTI,
       ZON_REGIO             REG,
       ZON_ZONA              ZON,
       ZON_SECCI             SEC,
       ZON_TERRI             TER,
       SEG_PAIS              SP,
       CRA_PERIO CP,
     SEG_PERIO_CORPO SPC,
     ped_solic_cabec conref,
     cra_perio  cpr,
     seg_perio_corpo spcr,
     fac_docum_conta_cabec cabref
 WHERE SP.OID_PAIS = MC.PAIS_OID_PAIS
   AND MC.OID_CLIE = CON.CLIE_OID_CLIE
   AND MC.OID_CLIE = MCDA.CLIE_OID_CLIE
   AND CON.OID_SOLI_CABE = CAB.SOCA_OID_SOLI_CABE
   and con.soca_oid_docu_refe=conref.oid_soli_cabe
   and conref.perd_oid_peri=cpr.oid_peri
   and cpr.peri_oid_peri=spcr.oid_peri
   and conref.oid_soli_cabe=cabref.soca_oid_soli_cabe
   and exists (select 1 from fac_docum_conta_linea x, fac_docum_conta_linea y where x.prod_oid_prod=y.prod_oid_prod and x.dcca_oid_cabe=cab.oid_cabe and y.dcca_oid_cabe=cabref.oid_cabe)
   AND CON.TAIM_OID_TASA_IMPU = PTI.OID_TASA_IMPU
   AND CAB.TIDO_OID_TIPO_DOCU = FTD.OID_TIPO_DOCU
   AND CAB.ZORG_OID_REGI = REG.OID_REGI
   AND CAB.ZZON_OID_ZONA = ZON.OID_ZONA
   AND CAB.ZSCC_OID_SECC = SEC.OID_SECC
   AND CAB.TERR_OID_TERR = TER.OID_TERR
   AND CP.PERI_OID_PERI = SPC.OID_PERI
   and cp.oid_peri = cab.perd_oid_peri
   AND CAB.PERD_OID_PERI >= lnoidperiodo
   AND CAB.FEC_FACT = TO_DATE(psfechaFacturacion, 'DD/MM/YYYY') -- fecha_facturacion
   --AND CON.IND_INTE_LARI_GENE = '1' -- en peru es 1
   AND FTD.OiD_TIPO_DOCU = 32
   AND EXISTS
       (SELECT NULL
          FROM FAC_DOCUM_CONTA_LINEA DET,
               PED_SOLIC_POSIC       PSP,
               PRE_OFERT_DETAL       POD
         WHERE DET.SOPO_OID_SOLI_POSI = PSP.OID_SOLI_POSI
           AND PSP.OFDE_OID_DETA_OFER = POD.OID_DETA_OFER(+)
           AND DET.NUM_UNID_ATEN <> 0
           AND DET.DCCA_OID_CABE = CAB.OID_CABE
           AND NOT EXISTS
         (SELECT NULL
                  FROM FAC_TIPO_OFERT_EXCLU TOE
                 WHERE TOE.TOFE_OID_TIPO_OFER = POD.TOFE_OID_TIPO_OFER))
order by 1;

TYPE documentorecord IS RECORD (
        valorColumna1 varchar2(4000),
        valorColumna2 varchar2(4000),
        val_seri_docu_lega FAC_DOCUM_CONTA_CABEC.VAL_SERI_DOCU_LEGA%TYPE,
        num_docu_cont_inte FAC_DOCUM_CONTA_CABEC.NUM_DOCU_CONT_INTE%TYPE
 );

 TYPE documentotype IS TABLE OF documentorecord;
 r_documento        documentotype;

 /* Variables usadas para la generacion del archivo de texto */
 lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
 v_handle            UTL_FILE.FILE_TYPE;

 lsLinea             VARCHAR2(10000);
 lsNombreArchivo     VARCHAR2(50);

 lbAbrirUtlFile      BOOLEAN;
lsNUmeroEnvios BAS_PARAM_PAIS.Val_Para%TYPE;
 lbIngreso           BOOLEAN;

BEGIN

  SELECT nvl(MIN(val_para), 1)
    INTO lsnumeroenvios
    FROM bas_param_pais
   WHERE cod_sist = 'IMP'
     AND cod_para = '007'
     AND cod_pais = pscodigopais;
  l_oidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoperiodo);

  /* Generando Archivo de Texto (Detalle) */
  lbabrirutlfile := TRUE;

  -- Abrimos el cursor
  OPEN c_documento(l_oidperiodo);
  LOOP
    FETCH c_documento BULK COLLECT
      INTO r_documento LIMIT w_filas;
  
    /* Procedimiento inicial para generar interfaz */
    IF lbabrirutlfile THEN
      gen_pkg_inter_archi.gen_pr_inici_inter_maxca(pscodigopais,
                                                   pscodigosistema,
                                                   pscodigointerfaz,
                                                   psnombrearchivo,
                                                   utl_file_maxi_cara,
                                                   lsdirtempo,
                                                   lsnombrearchivo,
                                                   v_handle);
      lbabrirutlfile := FALSE;
    END IF;
  
    IF r_documento.count > 0 THEN
      FOR j IN 1 .. lsnumeroenvios
      LOOP
        FOR i IN r_documento.first .. r_documento.last
        LOOP
          
          IF j = 1 THEN
              BEGIN
                  lbIngreso := true;
            INSERT INTO imp_tmp_felec_cabec
              (tip_doc,
               num_seri,
               num_docu_inte,
               fec_fact,
               txt_cabe1,
               txt_cabe2)
            VALUES
              ('05',
               r_documento(i).val_seri_docu_lega,
               r_documento(i).num_docu_cont_inte,
               psfechafacturacion,
               r_documento(i).valorcolumna1,
               r_documento(i).valorcolumna2);
              EXCEPTION
              WHEN DUP_VAL_ON_INDEX THEN 
                   lbIngreso := false;
              END;

          END IF;
          lslinea := j || substr(r_documento(i).valorcolumna1, 2) || ';' || 
                    r_documento(i).valorcolumna2;
          utl_file.put_line(v_handle, lslinea);
        END LOOP;
      END LOOP;
    END IF;
  
    EXIT WHEN c_documento%NOTFOUND;
  END LOOP;
  -- Cerramos el cursor
  CLOSE c_documento;

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
    raise_application_error(-20123,
                            'ERROR INT_PR_IMP_NOCRF_CABEC_PERU: ' ||
                            ls_sqlerrm);
END int_pr_imp_nocrf_cabec_peru;

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Notas Credito Facturas
                      Detalle para Peru
Fecha Creación      : 01/04/2013
Autor               : Carlos Bazalar
***************************************************************************/
PROCEDURE INT_PR_IMP_NOCRF_DETAL_PERU(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      pscodigoPeriodo     VARCHAR2,
                                      psfechaFacturacion  VARCHAR2,
                                      psindicadorNovedad  VARCHAR2,
                                      psNombreArchivo     VARCHAR2)
IS
 l_oidPeriodo NUMBER;
 CURSOR c_documento(lnoidPeriodo NUMBER) IS
 select rpad(cab.val_seri_docu_lega||'-'||cab.num_docu_cont_inte,14,' ' )|| -- PK
       -- NGCA --
       'NGCA' ||
       lpad(ROW_NUMBER() OVER (PARTITION BY cab.num_docu_cont_inte ORDER BY dcl.sopo_oid_soli_posi),3,'0') || -- item_detalle,
       rpad(SUBSTR(SPC.COD_PERI, -2, 2) || sp.VAL_CODI_VENT || DECODE(SP.VAL_PREC_CATA_UNIT_LOCA, 0, '0', '8'),30,' ') || -- codigo_producto
       rpad(abs(dcl.num_unid_aten) || '.000',16,' ') ||-- cant_unidades_x_item
       -- NGCB --
       'NGCB' ||
--      rpad( GEN_PKG_GENER.gen_fn_reemp_carac_extra(TRIM((SELECT VAL_I18N FROM GEN_I18N_SICC_PAIS WHERE ATTR_ENTI = 'MAE_PRODU' AND IDIO_OID_IDIO = 1 AND VAL_OID = dcl.PROD_OID_PROD))),250,' ') ||
      rpad( GEN_PKG_GENER.gen_fn_reemp_carac_extra(TRIM(imp_pkg_proce_laser.imp_fn_desc_produ(psCodigoPais,dcl.prod_oid_prod))),250,' ') ||
       -- NGCC --
       'NGCC' ||
       rpad('NIU',3,' ') ||
       case when dcl.val_prec_cata_unit_loca<>0 then rpad(ltrim(to_char(abs(dcl.val_prec_cata_unit_loca), '9999999990.00')),15,' ')
       else
       rpad(ltrim(to_char(0, '9999999990.00')),15,' ')
       end
       || '01' || --prec_catal_unit_con_descuento
       case when dcl.val_prec_cata_unit_loca<>0 then rpad(ltrim(to_char(abs(dcl.val_prec_neto_tota_loca), '9999999990.00')),15,' ')
       else
       rpad(ltrim(to_char(0, '9999999990.00')),15,' ')
       end ||
       case when dcl.val_prec_cata_unit_loca<>0 then rpad(' ',15,' ')
       else
       rpad(ltrim(to_char(dcl.val_prec_cont_unit_loca, '9999999990.00')),15,' ')
       end
       || decode(dcl.val_prec_cata_unit_loca, 0, '02','  ') || --prec_catal_unit_con_descuento
       rpad(ltrim(to_char(abs(decode(dcl.val_prec_cata_unit_loca, 0, 0, dcl.val_prec_sin_impu_unit)), '9999999990.00')),15, ' ')||-- valor unitario sin IGV
       -- NGCD --
       'NGCD' ||
       rpad(ltrim(to_char(decode(dcl.val_prec_cata_unit_loca,0,0,abs(dcl.imp_impu_tota_loca)), '9999999990.00')),15,' ') ||
       rpad(ltrim(to_char(decode(dcl.val_prec_cata_unit_loca,0,0,abs(dcl.imp_impu_tota_loca)), '9999999990.00')),15,' ') ||
       rpad(decode(abs(dcl.val_prec_cata_unit_loca),0,'31','10'),2,' ') ||
       rpad('1000',4,' ') ||
       rpad('IGV',6,' ') ||
       rpad('VAT',3,' ') ||
       -- NGCE --
       'NGCE' ||
       rpad(' ',15,' ') ||
       rpad(' ',15,' ') ||
       rpad('  ',2,' ') ||
       rpad(' ',4,' ') ||
       rpad(' ',6,' ') ||
       rpad(' ',3,' ') ||
       -- NGCF --
       'NGCF' ||
       rpad('PRECIO CAT. UNIT.',60,' ') ||
       rpad(ltrim(to_char(abs(dcl.val_prec_sin_impu_unit), '9999999990.00')),100,' ') ||
       'NGCF' ||
       rpad('PRECIO CAT. UNIT. CON DSCTO',60,' ') ||
       rpad(ltrim(to_char(abs(dcl.val_prec_neto_unit_loca), '9999999990.00')),100,' ') ||
       'NGCF' ||
       rpad('PRECIO CAT. CON DSCTO',60,' ') ||
       rpad(ltrim(to_char(abs(dcl.val_prec_neto_tota_loca), '9999999990.00')),100,' '),
       cab.val_seri_docu_lega,
       cab.num_docu_cont_inte
  FROM FAC_DOCUM_CONTA_CABEC CAB,
       FAC_TIPO_DOCUM        FTD,
       MAE_CLIEN             MC,
       PED_SOLIC_CABEC       CON,
       PED_TASA_IMPUE        PTI,
       ZON_REGIO             REG,
       ZON_ZONA              ZON,
       ZON_SECCI             SEC,
       ZON_TERRI             TER,
       SEG_PAIS              SP,
       fac_docum_conta_linea dcl,
       ped_solic_posic sp,
       CRA_PERIO CP,
     SEG_PERIO_CORPO SPC
 WHERE SP.OID_PAIS = MC.PAIS_OID_PAIS
   AND MC.OID_CLIE = CON.CLIE_OID_CLIE
   AND CON.OID_SOLI_CABE = CAB.SOCA_OID_SOLI_CABE
   AND CON.TAIM_OID_TASA_IMPU = PTI.OID_TASA_IMPU
   AND CAB.TIDO_OID_TIPO_DOCU = FTD.OID_TIPO_DOCU
   AND CAB.ZORG_OID_REGI = REG.OID_REGI
   AND CAB.ZZON_OID_ZONA = ZON.OID_ZONA
   AND CAB.ZSCC_OID_SECC = SEC.OID_SECC
   AND CAB.TERR_OID_TERR = TER.OID_TERR
   and dcl.dcca_oid_cabe = cab.oid_cabe
   and dcl.num_unid_aten <> 0
   and dcl.ind_no_impr = '0'
   and sp.oid_soli_posi = dcl.sopo_oid_soli_posi
   AND CP.PERI_OID_PERI = SPC.OID_PERI
   and cp.oid_peri = cab.perd_oid_peri

   AND CAB.PERD_OID_PERI >= lnoidperiodo
   AND CAB.FEC_FACT = TO_DATE(psfechaFacturacion, 'DD/MM/YYYY') -- fecha_facturacion
   --AND CON.IND_INTE_LARI_GENE = '1' -- en peru es 1
   AND FTD.OiD_TIPO_DOCU = 32
   AND EXISTS
       (SELECT NULL
          FROM FAC_DOCUM_CONTA_LINEA DET,
               PED_SOLIC_POSIC       PSP,
               PRE_OFERT_DETAL       POD
         WHERE DET.SOPO_OID_SOLI_POSI = PSP.OID_SOLI_POSI
           AND PSP.OFDE_OID_DETA_OFER = POD.OID_DETA_OFER(+)
           AND DET.NUM_UNID_ATEN <> 0
           AND DET.DCCA_OID_CABE = CAB.OID_CABE
           AND NOT EXISTS
         (SELECT NULL
                  FROM FAC_TIPO_OFERT_EXCLU TOE
                 WHERE TOE.TOFE_OID_TIPO_OFER = POD.TOFE_OID_TIPO_OFER))
union
select rpad(cab.val_seri_docu_lega||'-'||cab.num_docu_cont_inte,14,' ' )|| -- PK
       -- NGCA --
       'NGCA' ||
       lpad(998,3,'0') || -- item_detalle,
       rpad(' ',30,' ') || -- codigo_producto
       rpad('1' || '.000',16,' ') ||-- cant_unidades_x_item
       -- NGCB --
       'NGCB' ||
       rpad('FLETE/EMBALAJE',250,' ') ||
       -- NGCC --
       'NGCC' ||
       rpad('ZZ',3,' ') ||
       -- NGCD --
       --'NGCD' ||
       rpad(ltrim(to_char(nvl(abs(cab.IMP_FLET_TOTA_LOCA),0), '9999999990.00')),15,' ')||'01' || --precio venta unitario
       rpad(ltrim(to_char(nvl(abs(cab.IMP_FLET_IMPU_TOTA_LOCA),0), '9999999990.00')),15,' ') || -- valor venta x item
       rpad(' ',15,' ') ||
       rpad(' ',2,' ') ||
       rpad(ltrim(to_char(nvl(abs(cab.IMP_FLET_IMPU_TOTA_LOCA),0), '9999999990.00')),15,' ') || -- valor venta x item
       -- NGCD --
       'NGCD' ||
       rpad(ltrim(to_char(nvl(abs(cab.IMP_FLET_TOTA_LOCA),0)-nvl(abs(cab.IMP_FLET_IMPU_TOTA_LOCA),0), '9999999990.00')),15,' ') ||
       rpad(ltrim(to_char(nvl(abs(cab.IMP_FLET_TOTA_LOCA),0)-nvl(abs(cab.IMP_FLET_IMPU_TOTA_LOCA),0), '9999999990.00')),15,' ') ||
       rpad('10',2,' ') ||
       rpad('1000',4,' ') ||
       rpad('IGV',6,' ') ||
       rpad('VAT',3,' ') ||
       -- NGCE --
       'NGCE' ||
       rpad(' ',15,' ') ||
       rpad(' ',15,' ') ||
       rpad('  ',2,' ') ||
       rpad(' ',4,' ') ||
       rpad(' ',6,' ') ||
       rpad(' ',3,' ') ||
       -- NGCF --
       'NGCF' ||
       rpad('PRECIO CAT. UNIT.',60,' ') ||
       rpad(ltrim(to_char(nvl(abs(cab.IMP_FLET_IMPU_TOTA_LOCA),0), '9999999990.00')),100,' ') ||
       'NGCF' ||
       rpad('PRECIO CAT. UNIT. CON DSCTO',60,' ') ||
       rpad(ltrim(to_char(nvl(abs(cab.IMP_FLET_IMPU_TOTA_LOCA),0), '9999999990.00')),100,' ') ||
       'NGCF' ||
       rpad('PRECIO CAT. CON DSCTO',60,' ') ||
       rpad(ltrim(to_char(nvl(abs(cab.IMP_FLET_IMPU_TOTA_LOCA),0), '9999999990.00')),100,' '),
       cab.val_seri_docu_lega,
       cab.num_docu_cont_inte
  FROM FAC_DOCUM_CONTA_CABEC CAB
 WHERE CAB.PERD_OID_PERI >= lnoidperiodo
   AND CAB.FEC_FACT = TO_DATE(psfechaFacturacion, 'DD/MM/YYYY') -- fecha_facturacion
   AND cab.tido_OiD_TIPO_DOCU = 32
   and cab.IMP_FLET_TOTA_LOCA<>0
union
select rpad(cab.val_seri_docu_lega||'-'||cab.num_docu_cont_inte,14,' ' )|| -- PK
       -- NGCA --
       'NGCA' ||
       lpad(999,3,'0') || -- item_detalle,
       rpad(' ',30,' ') || -- codigo_producto
       rpad('1' || '.000',16,' ') ||-- cant_unidades_x_item
       -- NGCB --
       'NGCB' ||
       rpad('GASTOS ADMINISTRATIVOS Y DE COBRANZA',250,' ') ||
       -- NGCC --
       'NGCC' ||
       rpad('ZZ',3,' ') ||
       -- NGCD --
       --'NGCD' ||
       rpad(ltrim(to_char(nvl(abs(cab.VAL_TOTA_GAST_ADMI),0), '9999999990.00')),15,' ')||'01' || --precio venta unitario
       rpad(ltrim(to_char(nvl(abs(cab.VAL_TOTA_GAST_ADMI_SIN_IMPU),0), '9999999990.00')),15,' ') || -- valor venta x item
       rpad(' ',15,' ') ||
       rpad(' ',2,' ') ||
       rpad(ltrim(to_char(nvl(abs(cab.VAL_TOTA_GAST_ADMI_SIN_IMPU),0), '9999999990.00')),15,' ') || -- valor venta x item
       -- NGCD --
       'NGCD' ||
       rpad(ltrim(to_char(nvl(abs(cab.VAL_TOTA_GAST_ADMI),0)-nvl(abs(cab.VAL_TOTA_GAST_ADMI_SIN_IMPU),0), '9999999990.00')),15,' ') ||
       rpad(ltrim(to_char(nvl(abs(cab.VAL_TOTA_GAST_ADMI),0)-nvl(abs(cab.VAL_TOTA_GAST_ADMI_SIN_IMPU),0), '9999999990.00')),15,' ') ||
       rpad('10',2,' ') ||
       rpad('1000',4,' ') ||
       rpad('IGV',6,' ') ||
       rpad('VAT',3,' ') ||
       -- NGCE --
       'NGCE' ||
       rpad(' ',15,' ') ||
       rpad(' ',15,' ') ||
       rpad('  ',2,' ') ||
       rpad(' ',4,' ') ||
       rpad(' ',6,' ') ||
       rpad(' ',3,' ') ||
       -- NGCF --
       'NGCF' ||
       rpad('PRECIO CAT. UNIT.',60,' ') ||
       rpad(ltrim(to_char(nvl(abs(cab.VAL_TOTA_GAST_ADMI_SIN_IMPU),0), '9999999990.00')),100,' ') ||
       'NGCF' ||
       rpad('PRECIO CAT. UNIT. CON DSCTO',60,' ') ||
       rpad(ltrim(to_char(nvl(abs(cab.VAL_TOTA_GAST_ADMI_SIN_IMPU),0), '9999999990.00')),100,' ') ||
       'NGCF' ||
       rpad('PRECIO CAT. CON DSCTO',60,' ') ||
       rpad(ltrim(to_char(nvl(abs(cab.VAL_TOTA_GAST_ADMI_SIN_IMPU),0), '9999999990.00')),100,' '),
       cab.val_seri_docu_lega,
       cab.num_docu_cont_inte
  FROM FAC_DOCUM_CONTA_CABEC CAB
 WHERE CAB.PERD_OID_PERI >= lnoidperiodo
   AND CAB.FEC_FACT = TO_DATE(psfechaFacturacion, 'DD/MM/YYYY') -- fecha_facturacion
   --AND CON.IND_INTE_LARI_GENE = '1' -- en peru es 1
   AND cab.tido_OiD_TIPO_DOCU = 32
   and cab.VAL_TOTA_GAST_ADMI <> 0;

 TYPE documentorecord IS RECORD (
        valorColumna1 varchar2(4000),
        val_seri_docu_lega FAC_DOCUM_CONTA_CABEC.VAL_SERI_DOCU_LEGA%TYPE,
        num_docu_cont_inte FAC_DOCUM_CONTA_CABEC.NUM_DOCU_CONT_INTE%TYPE
 );

 TYPE documentotype IS TABLE OF documentorecord;
 r_documento        documentotype;

 /* Variables usadas para la generacion del archivo de texto */
 lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
 v_handle            UTL_FILE.FILE_TYPE;

 lsLinea             VARCHAR2(10000);
 lsNombreArchivo     VARCHAR2(50);

 lbAbrirUtlFile      BOOLEAN;
lsNUmeroEnvios BAS_PARAM_PAIS.Val_Para%TYPE;
 lbIngreso           BOOLEAN;

BEGIN

  SELECT nvl(MIN(val_para), 1)
    INTO lsnumeroenvios
    FROM bas_param_pais
   WHERE cod_sist = 'IMP'
     AND cod_para = '007'
     AND cod_pais = pscodigopais;

  l_oidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoperiodo);

  /* Generando Archivo de Texto (Detalle) */
  lbabrirutlfile := TRUE;

  -- Abrimos el cursor
  OPEN c_documento(l_oidperiodo);
  LOOP
    FETCH c_documento BULK COLLECT
      INTO r_documento LIMIT w_filas;
  
    /* Procedimiento inicial para generar interfaz */
    IF lbabrirutlfile THEN
      gen_pkg_inter_archi.gen_pr_inici_inter_maxca(pscodigopais,
                                                   pscodigosistema,
                                                   pscodigointerfaz,
                                                   psnombrearchivo,
                                                   utl_file_maxi_cara,
                                                   lsdirtempo,
                                                   lsnombrearchivo,
                                                   v_handle);
      lbabrirutlfile := FALSE;
    END IF;
  
    IF r_documento.count > 0 THEN
      FOR j IN 1 .. lsnumeroenvios
      LOOP
        FOR i IN r_documento.first .. r_documento.last
        LOOP
          
          IF j = 1 THEN
            BEGIN
                lbIngreso := true;
            INSERT INTO imp_tmp_felec_detal
              (tip_doc,
               num_seri,
               num_docu_inte,
               num_secu,
               txt_deta1,
               txt_deta2)
            VALUES
              ('05',
               r_documento(i).val_seri_docu_lega,
               r_documento(i).num_docu_cont_inte,
                   to_number(substr(r_documento(i).valorcolumna1,19,3)),
               r_documento(i).valorcolumna1,
               NULL);
            EXCEPTION
            WHEN DUP_VAL_ON_INDEX THEN 
                 lbIngreso := false;
            END;
          END IF;
          
          lslinea := j || substr(r_documento(i).valorcolumna1, 2);
          utl_file.put_line(v_handle, lslinea);
          
        END LOOP;
      END LOOP;
    
    END IF;
  
    EXIT WHEN c_documento%NOTFOUND;
  END LOOP;
  -- Cerramos el cursor
  CLOSE c_documento;

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
    raise_application_error(-20123,
                            'ERROR INT_PR_IMP_NOCRF_DETAL_PERU: ' ||
                            ls_sqlerrm);
END int_pr_imp_nocrf_detal_peru;

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Notas Credito Boletas
                      Cabecera para Peru
Fecha Creación      : 01/04/2013
Autor               : Carlos Bazalar
***************************************************************************/
PROCEDURE INT_PR_IMP_NOCRB_CABEC_PERU(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      pscodigoPeriodo     VARCHAR2,
                                      psfechaFacturacion  VARCHAR2,
                                      psindicadorNovedad  VARCHAR2,
                                      psNombreArchivo     VARCHAR2)
IS
 l_oidPeriodo NUMBER;
 CURSOR c_documento(lnoidPeriodo NUMBER) IS
 select rpad(cab.val_seri_docu_lega||'-'||cab.num_docu_cont_inte,14,' ' )||--, CAB.NUM_DOCU_lega ,-- PK
       -- NGAA --
       'NGAA' ||
       rpad('2.0',10,' ') || -- version_UBL
       rpad('1.0',10,' ') || -- version_estructura_documento
       rpad('334',3,' ') || -- tipo_documento
       to_char(CON.FEC_FACT,'yyyy-mm-dd') || -- fecha_emision
       rpad('20100123763',11,' ') || -- RUC_belcorp
       rpad('6',1,' ') || -- tipo_documento
       rpad(cab.val_seri_docu_lega||'-'||cab.num_docu_cont_inte,13,' ') || -- Numeracion
       rpad(cabref.val_seri_docu_lega||'-'||cabref.num_docu_cont_inte,13,' ') || -- Numero Doc Ref
       rpad('07',2,' ') || -- tipo_nc
       -- NGAB --
       'NGAB' ||
       rpad('CETCO S.A.',100,' ') || --  razon_social
       -- NGAC --
       'NGAC' ||
       rpad('CETCO S.A.',100,' ') || --  razon_social
       -- NGAD --
       'NGAD' ||
       rpad('150131',6,' ')||rpad('AV. FELIPE PARDO Y ALIAGA 652-1201',100,' ')||rpad('URB CHACARILLA - SANTA CRUZ',25,' ')||
            rpad('LIMA',30,' ')||rpad('LIMA',30,' ')||rpad('SAN ISIDRO',30,' ')||'PE'|| -- domicilio fiscal
       -- NGBA --
       'NGBA' ||
       rpad(cab.val_nume_iden_fisc,15,' ')|| --Documento Identidad,
       '1' || -- tipo Documento Identidad
       rpad(GEN_PKG_GENER.gen_fn_reemp_carac_extra(cab.val_ape1||' '||cab.val_ape2||' '||cab.val_nom1||' '||cab.val_nom2),100,' ') || -- apellidos_y_nombres
       -- NGBB --
       'NGBB' ||
       rpad('DEVOLUCION',250,' ')  || --nuevo_sol_peruano*/
       -- NGBC --
       'NGBC' ||
       rpad(GEN_PKG_GENER.gen_fn_reemp_carac_extra(nvl(
            (select mcc.val_text_comu
            from MAE_CLIEN_COMUN mcc
            where mcc.CLIE_OID_CLIE(+) = con.clie_oid_clie
            AND mcc.TICM_OID_TIPO_COMU(+) = 3),' '))
            ,250,' '
            ) || -- email
       'NGBD' ||
       'PEN'  || --nuevo_sol_peruano*/
       rpad(cabref.val_seri_docu_lega||'-'||cabref.num_docu_cont_inte,13,' ') ||
       '03' ||
       rpad(' ',30,' ') ||
       rpad(' ',2,' ') ||
       rpad(' ',30,' ') ||
       rpad(' ',2,' ') ||
        'NGDA' ||
       rpad('1001',4,' ') || rpad(ltrim(TO_CHAR(nvl(abs(cab.VAL_PREC_NETO_TOTA_LOCA),0)+nvl(abs(cab.VAL_TOTA_GAST_ADMI_SIN_IMPU),0)+nvl(abs(cab.imp_flet_impu_tota_loca),0), '9999999990.00')),15,' ') || -- total valor ventas operaciones grabadas
       rpad('1002',4,' ') || rpad('0.00',15,' ') || -- total valor ventas operaciones inafectas
       rpad('1003',4,' ') || rpad('0.00',15,' ') || -- total valor ventas operaciones exonerdas
       rpad(ltrim(TO_CHAR(abs(cab.val_tota_paga_loca), '9999999990.00')),15,' ') || -- immporte total de la venta
       -- NGDB --
       'NGDB' ||
       rpad(ltrim(TO_CHAR(abs(cab.imp_impu_tota_loca), '9999999990.00')),15,' ') ||  rpad(ltrim(TO_CHAR(abs(cab.imp_impu_tota_loca), '9999999990.00')),15,' ') || rpad('1000',4,' ') || rpad('IGV',6,' ') || rpad('VAT',3,' ') || -- sumatoria IGV
       -- NGDC --
       'NGDC' ||
       rpad(' ',15,' ') ||  rpad(' ',15,' ') || rpad('    ',4,' ') || rpad('   ',6,' ') || rpad('   ',3,' ') || -- sumatoria ISC
       -- NGDD --
       'NGDD' ||
       rpad(' ',15,' ') ||  rpad(' ',15,' ') || rpad('    ',4,' ') || rpad('   ',6,' ') || rpad('   ',3,' ') || -- sumatoria otros tributos
       -- NGDE --
       'NGDE' ||
       rpad(' ', 15,' ') || -- sumatoria otros cargos
       -- NGDF --
       'NGDF' ||
       --rpad('2005',4,' ') || rpad(ltrim(to_char(abs(cab.imp_des1_sin_impu_tota), '9999999990.00')),15,' ')|| -- descuentos
       rpad('2005',4,' ') || rpad(ltrim(TO_CHAR(abs(cab.imp_desc_tota_loca)-abs(cab.val_prec_cont_tota_loca), '9999999990.00')),15,' ')||
       -- NGEA --
       'NGEA' ||
       rpad('ZONA-TERRITORIO',60,' ') ||
       rpad(ZON.COD_ZONA || '-' || TER.COD_TERR,194,' ') ||
       'NGEA' ||
       rpad('CAMPANA',60,' ') ||
       rpad(spc.cod_peri,194,' ') ||
       'NGEA' ||
       rpad('TOTAL PRODUCTOS',60,' ') ||
       rpad((SELECT SUM(abs(SP.NUM_UNID_ATEN)) FROM FAC_DOCUM_CONTA_LINEA SP WHERE SP.DCCA_OID_CABE = CAB.OID_CABE AND SP.IND_NO_IMPR = '0'),194,' ') ||
       'NGEA' ||
       rpad('TOTAL PRECIO CAT',60,' ') ||
       rpad(ltrim(TO_CHAR(abs(cab.val_tota_paga_loca)-abs(cab.imp_flet_tota_loca)+abs(cab.imp_desc_tota_loca)- nvl(abs(cab.val_tota_gast_admi),0), '9999999990.00')),194,' ') ||
       'NGEA' ||
       rpad('DESCUENTO',60,' ') ||
       rpad(ltrim(TO_CHAR(abs(cab.imp_desc_tota_loca)-abs(cab.val_prec_cont_tota_loca), '9999999990.00')),194,' ')||
       'NGEA' ||
       rpad('BONIFICACION',60,' ') ||
       rpad(ltrim(TO_CHAR(abs(cab.val_prec_cont_tota_loca), '9999999990.00')),194,' ') ||
       'NGEA' ||
       rpad('TOTAL PRECIO CAT MENOS DCT',60,' ') ||
       rpad(ltrim(TO_CHAR(abs(cab.val_tota_paga_loca)-abs(cab.imp_flet_tota_loca) - nvl(abs(cab.val_tota_gast_admi),0), '9999999990.00')),194,' ') ||
       'NGEA' ||
       rpad('TOT OPE GRA',60,' ') ||
       rpad(ltrim(TO_CHAR(abs(cab.val_prec_cont_tota_loca), '9999999990.00')),194,' ') ||
       'NGEA' ||
       rpad('OFI PRI DIR',60,' ') ||
       rpad('AV. FELIPE PARDO Y ALIAGA 652-1201',194,' ') ||
       'NGEA' ||
       rpad('OFI PRI URB DIS PRO',60,' ') ||
       rpad('URB CHACARILLA - SANTA CRUZ, SAN ISIDRO, LIMA',114,' ') --||
         as valorColumna1, --||
       rpad(' ',80,' ') || 
       'NGEA' ||
       rpad('OFI PRI TLF',60,' ') ||
       rpad('TELF.: 211 3400',194,' ') ||
             'NGEA' ||
       rpad('PTO EMI DIR',60,' ') ||
       rpad('AV. SAN GENARO 150',194,' ') ||
       'NGEA' ||
       rpad('PTO EMI URB DIS PRO',60,' ') ||
       rpad('URB. MOLITALIA - LOS OLIVOS, LIMA',194,' ') ||
        'NGEA' ||
       rpad('TI NO CR EL',60,' ') ||
       rpad('DEVOLUCION POR ITEM',194,' ')  ||
        'NGEA' ||
       rpad('BOL DES',60,' ') ||
       rpad(con.val_nume_soli,194,' ')  ||
        'NGEA' ||
       rpad('BOL DES REF',60,' ') ||
       rpad(conref.val_nume_soli,194,' ')  ||
        'NGEA' ||
       rpad('CAMP REF',60,' ') ||
       rpad(spcr.cod_peri,194,' ')  ||
        'NGEA' ||
       rpad('DOC LEG REF',60,' ') ||
       rpad(cabref.val_seri_docu_lega||'-'||cabref.num_docu_cont_inte,194,' ')  ||
        'NGEA' ||
       rpad('DIR CLI',60,' ') ||
       rpad(GEN_PKG_GENER.gen_fn_reemp_carac_extra(cab.val_dire_comp),194,' ')||
        'NGEA' ||
       rpad('UBIGEO',60,' ') ||
       rpad(GEN_PKG_GENER.gen_fn_reemp_carac_extra(
           GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(cab.pais_OID_PAIS, mc.OID_CLIE, 4) || '/' ||
           GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(cab.pais_OID_PAIS, mc.OID_CLIE, 3) || '/' ||
           GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(cab.pais_OID_PAIS, mc.OID_CLIE, 2) || '/' ||
           GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(cab.pais_OID_PAIS, mc.OID_CLIE, 1)),194,' ') ||
        'NGEA' ||
       rpad('COD CLI',60,' ') ||
       rpad(mc.cod_clie,194,' ')  ||
        'NGEA' ||
       rpad('MONTO LETRAS',60,' ') ||
       rpad(GEN_FN_NUME_TO_TEXT(TRUNC(abs(cab.val_tota_paga_loca))) || ' y ' || TO_CHAR((abs(cab.val_tota_paga_loca) - TRUNC(abs(cab.val_tota_paga_loca))) * 100)  || '/100 NUEVOS SOLES',194,' ')  ||
        'NGEA' ||
       rpad('IMPRIME',60,' ') ||
       rpad(mcda.ind_impr_docu,194,' ')  ||
        'NGEA' ||
       rpad('ORIGEN',60,' ') ||
       rpad('VENTA DIRECTA',194,' ') ||
       -- NGFA --
       'NGFA' ||
       'NG00' ||
       rpad('Representacion impresa de la nota de credito electronica',100,' ')  ||
       -- NGFB --
       'NGFB' ||
       'NG99' ||
       rpad('Autorizado mediante Resolucion N 0180050000840/SUNAT',100,' ')
       as valorColumna2,
       cab.val_seri_docu_lega,
       cab.num_docu_cont_inte,
       rpad(cab.val_seri_docu_lega||'-'||cab.num_docu_cont_inte,14,' ' )||--, CAB.NUM_DOCU_lega ,-- PK
       -- NGAA --
       'NGAA' ||
       rpad('2.0',10,' ') || -- version_UBL
       rpad('1.0',10,' ') || -- version_estructura_documento
       rpad('334',3,' ') || -- tipo_documento
       to_char(CON.FEC_FACT,'yyyy-mm-dd') || -- fecha_emision
       rpad('20100123763',11,' ') || -- RUC_belcorp
       rpad('6',1,' ') || -- tipo_documento
       rpad(cab.val_seri_docu_lega||'-'||cab.num_docu_cont_inte,13,' ') || -- Numeracion
       rpad(substr (cabref.val_seri_docu_lega,2)||'-'||cabref.num_docu_cont_inte,13,' ') || -- Numero Doc Ref
       rpad('07',2,' ') || -- tipo_nc
       -- NGAB --
       'NGAB' ||
       rpad('CETCO S.A.',100,' ') || --  razon_social
       -- NGAC --
       'NGAC' ||
       rpad('CETCO S.A.',100,' ') || --  razon_social
       -- NGAD --
       'NGAD' ||
       rpad('150131',6,' ')||rpad('AV. FELIPE PARDO Y ALIAGA 652-1201',100,' ')||rpad('URB CHACARILLA - SANTA CRUZ',25,' ')||
            rpad('LIMA',30,' ')||rpad('LIMA',30,' ')||rpad('SAN ISIDRO',30,' ')||'PE'|| -- domicilio fiscal
       -- NGBA --
       'NGBA' ||
       rpad(cab.val_nume_iden_fisc,15,' ')|| --Documento Identidad,
       '1' || -- tipo Documento Identidad
       rpad(GEN_PKG_GENER.gen_fn_reemp_carac_extra(cab.val_ape1||' '||cab.val_ape2||' '||cab.val_nom1||' '||cab.val_nom2),100,' ') || -- apellidos_y_nombres
       -- NGBB --
       'NGBB' ||
       rpad('DEVOLUCION',250,' ')  || --nuevo_sol_peruano*/
       -- NGBC --
       'NGBC' ||
       rpad(GEN_PKG_GENER.gen_fn_reemp_carac_extra(nvl(
            (select mcc.val_text_comu
            from MAE_CLIEN_COMUN mcc
            where mcc.CLIE_OID_CLIE(+) = con.clie_oid_clie
            AND mcc.TICM_OID_TIPO_COMU(+) = 3),' '))
            ,250,' '
            ) || -- email
       'NGBD' ||
       'PEN'  || --nuevo_sol_peruano*/
       rpad(substr (cabref.val_seri_docu_lega,2)||'-'||cabref.num_docu_cont_inte,13,' ') ||
       '03' ||
       rpad(' ',30,' ') ||
       rpad(' ',2,' ') ||
       rpad(' ',30,' ') ||
       rpad(' ',2,' ') ||
        'NGDA' ||
       rpad('1001',4,' ') || rpad(ltrim(TO_CHAR(nvl(abs(cab.VAL_PREC_NETO_TOTA_LOCA),0)+nvl(abs(cab.VAL_TOTA_GAST_ADMI_SIN_IMPU),0), '9999999990.00')),15,' ') || -- total valor ventas operaciones grabadas
       rpad('1002',4,' ') || rpad('0.00',15,' ') || -- total valor ventas operaciones inafectas
       rpad('1003',4,' ') || rpad('0.00',15,' ') || -- total valor ventas operaciones exonerdas
       rpad(ltrim(TO_CHAR(abs(cab.val_tota_paga_loca), '9999999990.00')),15,' ') || -- immporte total de la venta
       -- NGDB --
       'NGDB' ||
       rpad(ltrim(TO_CHAR(abs(cab.imp_impu_tota_loca), '9999999990.00')),15,' ') ||  rpad(ltrim(TO_CHAR(abs(cab.imp_impu_tota_loca), '9999999990.00')),15,' ') || rpad('1000',4,' ') || rpad('IGV',6,' ') || rpad('VAT',3,' ') || -- sumatoria IGV
       -- NGDC --
       'NGDC' ||
       rpad(' ',15,' ') ||  rpad(' ',15,' ') || rpad('    ',4,' ') || rpad('   ',6,' ') || rpad('   ',3,' ') || -- sumatoria ISC
       -- NGDD --
       'NGDD' ||
       rpad(' ',15,' ') ||  rpad(' ',15,' ') || rpad('    ',4,' ') || rpad('   ',6,' ') || rpad('   ',3,' ') || -- sumatoria otros tributos
       -- NGDE --
       'NGDE' ||
       rpad(' ', 15,' ') || -- sumatoria otros cargos
       -- NGDF --
       'NGDF' ||
       --rpad('2005',4,' ') || rpad(ltrim(to_char(abs(cab.imp_des1_sin_impu_tota), '9999999990.00')),15,' ')|| -- descuentos
       rpad('2005',4,' ') || rpad(ltrim(TO_CHAR(abs(cab.imp_desc_tota_loca)-abs(cab.val_prec_cont_tota_loca), '9999999990.00')),15,' ')||
       -- NGEA --
       'NGEA' ||
       rpad('ZONA-TERRITORIO',60,' ') ||
       rpad(ZON.COD_ZONA || '-' || TER.COD_TERR,194,' ') ||
       'NGEA' ||
       rpad('CAMPANA',60,' ') ||
       rpad(spc.cod_peri,194,' ') ||
       'NGEA' ||
       rpad('TOTAL PRODUCTOS',60,' ') ||
       rpad((SELECT SUM(abs(SP.NUM_UNID_ATEN)) FROM FAC_DOCUM_CONTA_LINEA SP WHERE SP.DCCA_OID_CABE = CAB.OID_CABE AND SP.IND_NO_IMPR = '0'),194,' ') ||
       'NGEA' ||
       rpad('TOTAL PRECIO CAT',60,' ') ||
       rpad(ltrim(TO_CHAR(abs(cab.val_tota_paga_loca)-abs(cab.imp_flet_tota_loca)+abs(cab.imp_desc_tota_loca)- nvl(abs(cab.val_tota_gast_admi),0), '9999999990.00')),194,' ') ||
       'NGEA' ||
       rpad('DESCUENTO',60,' ') ||
       rpad(ltrim(TO_CHAR(abs(cab.imp_desc_tota_loca)-abs(cab.val_prec_cont_tota_loca), '9999999990.00')),194,' ')||
       'NGEA' ||
       rpad('BONIFICACION',60,' ') ||
       rpad(ltrim(TO_CHAR(abs(cab.val_prec_cont_tota_loca), '9999999990.00')),194,' ') ||
       'NGEA' ||
       rpad('TOTAL PRECIO CAT MENOS DCT',60,' ') ||
       rpad(ltrim(TO_CHAR(abs(cab.val_tota_paga_loca)-abs(cab.imp_flet_tota_loca) - nvl(abs(cab.val_tota_gast_admi),0), '9999999990.00')),194,' ') ||
       'NGEA' ||
       rpad('TOT OPE GRA',60,' ') ||
       rpad(ltrim(TO_CHAR(abs(cab.val_prec_cont_tota_loca), '9999999990.00')),194,' ') ||
       'NGEA' ||
       rpad('OFI PRI DIR',60,' ') ||
       rpad('AV. FELIPE PARDO Y ALIAGA 652-1201',194,' ') ||        
       'NGEA' ||
       rpad('OFI PRI URB DIS PRO',60,' ') ||
       rpad('URB CHACARILLA - SANTA CRUZ, SAN ISIDRO, LIMA',114,' ') --||
        as valorColumna3, --||       
       rpad(' ',80,' ') || 
       'NGEA' ||
       rpad('OFI PRI TLF',60,' ') ||
       rpad('TELF.: 211 3400',194,' ') ||
             'NGEA' ||
       rpad('PTO EMI DIR',60,' ') ||
       rpad('AV. SAN GENARO 150',194,' ') ||
       'NGEA' ||
       rpad('PTO EMI URB DIS PRO',60,' ') ||
       rpad('URB. MOLITALIA - LOS OLIVOS, LIMA',194,' ') ||
        'NGEA' ||
       rpad('TI NO CR EL',60,' ') ||
       rpad('DEVOLUCION POR ITEM',194,' ')  ||
        'NGEA' ||
       rpad('BOL DES',60,' ') ||
       rpad(con.val_nume_soli,194,' ')  ||
        'NGEA' ||
       rpad('BOL DES REF',60,' ') ||
       rpad(conref.val_nume_soli,194,' ')  ||
        'NGEA' ||
       rpad('CAMP REF',60,' ') ||
       rpad(spcr.cod_peri,194,' ')  ||
        'NGEA' ||
       rpad('DOC LEG REF',60,' ') ||
       rpad(substr (cabref.val_seri_docu_lega,2)||'-'||cabref.num_docu_cont_inte,194,' ')  ||
        'NGEA' ||
       rpad('DIR CLI',60,' ') ||
       rpad(GEN_PKG_GENER.gen_fn_reemp_carac_extra(cab.val_dire_comp),194,' ')||
        'NGEA' ||
       rpad('UBIGEO',60,' ') ||
       rpad(GEN_PKG_GENER.gen_fn_reemp_carac_extra(
           GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(cab.pais_OID_PAIS, mc.OID_CLIE, 4) || '/' ||
           GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(cab.pais_OID_PAIS, mc.OID_CLIE, 3) || '/' ||
           GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(cab.pais_OID_PAIS, mc.OID_CLIE, 2) || '/' ||
           GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(cab.pais_OID_PAIS, mc.OID_CLIE, 1)),194,' ') ||
        'NGEA' ||
       rpad('COD CLI',60,' ') ||
       rpad(mc.cod_clie,194,' ')  ||
        'NGEA' ||
       rpad('MONTO LETRAS',60,' ') ||
       rpad(GEN_FN_NUME_TO_TEXT(TRUNC(abs(cab.val_tota_paga_loca))) || ' y ' || TO_CHAR((abs(cab.val_tota_paga_loca) - TRUNC(abs(cab.val_tota_paga_loca))) * 100)  || '/100 NUEVOS SOLES',194,' ')  ||
        'NGEA' ||
       rpad('IMPRIME',60,' ') ||
       rpad(mcda.ind_impr_docu,194,' ')  ||
       'NGEA' ||
       rpad('ORIGEN',60,' ') ||
       rpad('VENTA DIRECTA',194,' ') ||
       -- NGFA --
       'NGFA' ||
       'NG00' ||
       rpad('Representacion impresa de la nota de credito electronica',100,' ')  ||
       -- NGFB --
       'NGFB' ||
       'NG99' ||
       rpad('Autorizado mediante Resolucion N 0180050000840/SUNAT',100,' ')
       as valorColumna4
  FROM FAC_DOCUM_CONTA_CABEC CAB,
       FAC_TIPO_DOCUM        FTD,
       MAE_CLIEN             MC,
       MAE_CLIEN_DATOS_ADICI MCDA,
       PED_SOLIC_CABEC       CON,
       PED_TASA_IMPUE        PTI,
       ZON_REGIO             REG,
       ZON_ZONA              ZON,
       ZON_SECCI             SEC,
       ZON_TERRI             TER,
       SEG_PAIS              SP,
       CRA_PERIO CP,
       SEG_PERIO_CORPO SPC,
       ped_solic_cabec conref,
       cra_perio  cpr,
       seg_perio_corpo spcr,
       fac_docum_conta_cabec cabref
WHERE SP.OID_PAIS = MC.PAIS_OID_PAIS
   AND MC.OID_CLIE = CON.CLIE_OID_CLIE
   AND MC.OID_CLIE = MCDA.CLIE_OID_CLIE
   AND CON.OID_SOLI_CABE = CAB.SOCA_OID_SOLI_CABE
   and con.soca_oid_docu_refe=conref.oid_soli_cabe
   and conref.perd_oid_peri=cpr.oid_peri
   and cpr.peri_oid_peri=spcr.oid_peri
   and conref.oid_soli_cabe=cabref.soca_oid_soli_cabe
   and exists (select 1 from fac_docum_conta_linea x, fac_docum_conta_linea y where x.prod_oid_prod=y.prod_oid_prod and x.dcca_oid_cabe=cab.oid_cabe and y.dcca_oid_cabe=cabref.oid_cabe)
   AND CON.TAIM_OID_TASA_IMPU = PTI.OID_TASA_IMPU
   AND CAB.TIDO_OID_TIPO_DOCU = FTD.OID_TIPO_DOCU
   AND CAB.ZORG_OID_REGI = REG.OID_REGI
   AND CAB.ZZON_OID_ZONA = ZON.OID_ZONA
   AND CAB.ZSCC_OID_SECC = SEC.OID_SECC
   AND CAB.TERR_OID_TERR = TER.OID_TERR
   AND CP.PERI_OID_PERI = SPC.OID_PERI
   and cp.oid_peri = cab.perd_oid_peri
   AND CAB.PERD_OID_PERI >= lnoidperiodo
   AND CAB.FEC_FACT = TO_DATE(psfechaFacturacion, 'DD/MM/YYYY') -- fecha_facturacion
   --AND CON.IND_INTE_LARI_GENE = '1' -- en peru es 1
    AND FTD.OiD_TIPO_DOCU = 31
   AND EXISTS
       (SELECT NULL
          FROM FAC_DOCUM_CONTA_LINEA DET,
               PED_SOLIC_POSIC       PSP,
               PRE_OFERT_DETAL       POD
         WHERE DET.SOPO_OID_SOLI_POSI = PSP.OID_SOLI_POSI
           AND PSP.OFDE_OID_DETA_OFER = POD.OID_DETA_OFER(+)
           AND DET.NUM_UNID_ATEN <> 0
           AND DET.DCCA_OID_CABE = CAB.OID_CABE
           AND NOT EXISTS
         (SELECT NULL
                  FROM FAC_TIPO_OFERT_EXCLU TOE
                 WHERE TOE.TOFE_OID_TIPO_OFER = POD.TOFE_OID_TIPO_OFER))

order by 1;

 TYPE documentorecord IS RECORD (
        valorColumna1 varchar2(4000),
        valorColumna2 varchar2(4000),
        val_seri_docu_lega FAC_DOCUM_CONTA_CABEC.VAL_SERI_DOCU_LEGA%TYPE,
        num_docu_cont_inte FAC_DOCUM_CONTA_CABEC.NUM_DOCU_CONT_INTE%TYPE,
        valorColumna3 varchar2(4000),
        valorColumna4 varchar2(4000)
 );

 TYPE documentotype IS TABLE OF documentorecord;
 r_documento        documentotype;

 /* Variables usadas para la generacion del archivo de texto */
 lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
 v_handle            UTL_FILE.FILE_TYPE;

 lsLinea             VARCHAR2(10000);
 lsNombreArchivo     VARCHAR2(50);

 lbAbrirUtlFile      BOOLEAN;
 lbIngreso           BOOLEAN;
BEGIN
    l_oidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(pscodigoPeriodo);

    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;

    -- Abrimos el cursor
    OPEN c_documento(l_oidPeriodo);
    LOOP
        FETCH c_documento BULK COLLECT INTO r_documento LIMIT w_filas;

        /* Procedimiento inicial para generar interfaz */
        IF lbAbrirUtlFile THEN
              GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER_MAXCA(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                     psNombreArchivo, UTL_FILE_MAXI_CARA, lsDirTempo, lsNombreArchivo, V_HANDLE);
           lbAbrirUtlFile := FALSE;
        END IF;

        IF  r_documento.COUNT > 0 THEN
            FOR i IN r_documento.FIRST..r_documento.LAST LOOP
                lsLinea :=  r_documento(i).valorColumna1 ||';'||
                            r_documento(i).valorColumna2;
                BEGIN
                    lbIngreso := true;
                INSERT INTO IMP_TMP_FELEC_CABEC(TIP_DOC,
                                                NUM_SERI,
                                                NUM_DOCU_INTE,
                                                FEC_FACT,
                                                TXT_CABE1,
                                                TXT_CABE2)
                                         VALUES('04',
                                                r_documento(i).val_seri_docu_lega,
                                                r_documento(i).num_docu_cont_inte,
                                                psfechaFacturacion,
                                                r_documento(i).valorColumna3,
                                                r_documento(i).valorColumna4);
                EXCEPTION
                WHEN DUP_VAL_ON_INDEX THEN 
                     lbIngreso := false;
                END;
                
                UTL_FILE.PUT_LINE (V_HANDLE, lslinea);
            END LOOP;
        END IF;

        EXIT WHEN c_documento%NOTFOUND;
    END LOOP;
    -- Cerramos el cursor
    CLOSE c_documento;

    IF NOT lbAbrirUtlFile THEN
          utl_file.fclose(V_HANDLE);
         /* Procedimiento final para generar interfaz */
         GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;
EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_IMP_NOCRB_CABEC_PERU: '||ls_sqlerrm);
END INT_PR_IMP_NOCRB_CABEC_PERU;

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Notas Credito Boletas
                      Detalle para Peru
Fecha Creación      : 01/04/2013
Autor               : Carlos Bazalar
***************************************************************************/
PROCEDURE INT_PR_IMP_NOCRB_DETAL_PERU(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      pscodigoPeriodo     VARCHAR2,
                                      psfechaFacturacion  VARCHAR2,
                                      psindicadorNovedad  VARCHAR2,
                                      psNombreArchivo     VARCHAR2)
IS
 l_oidPeriodo NUMBER;
 CURSOR c_documento(lnoidPeriodo NUMBER) IS
select rpad(cab.val_seri_docu_lega||'-'||cab.num_docu_cont_inte,14,' ' )|| -- PK
       -- NGCA --
       'NGCA' ||
       lpad(ROW_NUMBER() OVER (PARTITION BY cab.num_docu_cont_inte ORDER BY dcl.sopo_oid_soli_posi),3,'0') || -- item_detalle,
       rpad(SUBSTR(SPC.COD_PERI, -2, 2) || sp.VAL_CODI_VENT || DECODE(SP.VAL_PREC_CATA_UNIT_LOCA, 0, '0', '8'),30,' ') || -- codigo_producto
       rpad(abs(dcl.num_unid_aten) || '.000',16,' ') ||-- cant_unidades_x_item
       -- NGCB --
       'NGCB' ||
      rpad( GEN_PKG_GENER.gen_fn_reemp_carac_extra(TRIM(imp_pkg_proce_laser.imp_fn_desc_produ(psCodigoPais,dcl.prod_oid_prod))),250,' ') ||
      --rpad( GEN_PKG_GENER.gen_fn_reemp_carac_extra(TRIM((SELECT VAL_I18N FROM GEN_I18N_SICC_PAIS WHERE ATTR_ENTI = 'MAE_PRODU' AND IDIO_OID_IDIO = 1 AND VAL_OID = dcl.PROD_OID_PROD))),250,' ') ||
       -- NGCC --
       'NGCC' ||
       rpad('NIU',3,' ') ||
       case when dcl.val_prec_cata_unit_loca<>0 then rpad(ltrim(to_char(abs(dcl.val_prec_cata_unit_loca), '9999999990.00')),15,' ')
       else
       rpad(ltrim(to_char(0, '9999999990.00')),15,' ')
       end
       || '01' || --prec_catal_unit_con_descuento
       case when dcl.val_prec_cata_unit_loca<>0 then rpad(ltrim(to_char(abs(dcl.val_prec_neto_tota_loca), '9999999990.00')),15,' ')
       else
       rpad(ltrim(to_char(0, '9999999990.00')),15,' ')
       end ||
       case when dcl.val_prec_cata_unit_loca<>0 then rpad(' ',15,' ')
       else
       rpad(ltrim(to_char(dcl.val_prec_cont_unit_loca, '9999999990.00')),15,' ')
       end
       || decode(dcl.val_prec_cata_unit_loca, 0, '02','  ') || --prec_catal_unit_con_descuento
       rpad(ltrim(to_char(abs(decode(dcl.val_prec_cata_unit_loca, 0, 0, dcl.val_prec_sin_impu_unit)), '9999999990.00')),15, ' ')||-- valor unitario sin IGV
       -- NGCD --
       'NGCD' ||
       rpad(ltrim(to_char(decode(dcl.val_prec_cata_unit_loca,0,0,abs(dcl.imp_impu_tota_loca)), '9999999990.00')),15,' ') ||
       rpad(ltrim(to_char(decode(dcl.val_prec_cata_unit_loca,0,0,abs(dcl.imp_impu_tota_loca)), '9999999990.00')),15,' ') ||
       rpad(decode(abs(dcl.val_prec_cata_unit_loca),0,decode(ictp.cod_tipo_prog,'B','31','13'),'10'),2,' ') ||
       rpad('1000',4,' ') ||
       rpad('IGV',6,' ') ||
       rpad('VAT',3,' ') ||
       -- NGCE --
       'NGCE' ||
       rpad(' ',15,' ') ||
       rpad(' ',15,' ') ||
       rpad('  ',2,' ') ||
       rpad(' ',4,' ') ||
       rpad(' ',6,' ') ||
       rpad(' ',3,' ') ||
       -- NGCF --
       'NGCF' ||
       rpad('PRECIO CAT. UNIT.',60,' ') ||
       rpad(ltrim(to_char(abs(dcl.val_prec_cata_unit_loca)+abs(dcl.val_prec_cont_unit_loca), '9999999990.00')),100,' ') ||
       'NGCF' ||
       rpad('PRECIO CAT. UNIT. CON DSCTO',60,' ') ||
       rpad(ltrim(to_char(abs(dcl.val_prec_fact_unit_loca), '9999999990.00')),100,' ') ||
       'NGCF' ||
       rpad('PRECIO CAT. CON DSCTO',60,' ') ||
       rpad(ltrim(to_char(abs(dcl.val_prec_fact_tota_loca), '9999999990.00')),100,' '),
       cab.val_seri_docu_lega,
       cab.num_docu_cont_inte
  FROM FAC_DOCUM_CONTA_CABEC CAB,
       FAC_TIPO_DOCUM        FTD,
       MAE_CLIEN             MC,
       PED_SOLIC_CABEC       CON,
       PED_SOLIC_CABEC       PSC,
       PED_TASA_IMPUE        PTI,
       ZON_REGIO             REG,
       ZON_ZONA              ZON,
       ZON_SECCI             SEC,
       ZON_TERRI             TER,
       SEG_PAIS              SP,
       fac_docum_conta_linea dcl,
       ped_solic_posic sp,
       CRA_PERIO CP,
       SEG_PERIO_CORPO SPC,
       INC_CONCU_TIPO_PROG    ICTP
 WHERE SP.OID_PAIS = MC.PAIS_OID_PAIS
   and CAB.SOCA_OID_SOLI_CABE = PSC.SOCA_OID_SOLI_CABE
   and psc.ictp_oid_tipo_prog=ictp.oid_tipo_prog(+)
   AND MC.OID_CLIE = CON.CLIE_OID_CLIE
   AND CON.OID_SOLI_CABE = CAB.SOCA_OID_SOLI_CABE
   AND CON.TAIM_OID_TASA_IMPU = PTI.OID_TASA_IMPU
   AND CAB.TIDO_OID_TIPO_DOCU = FTD.OID_TIPO_DOCU
   AND CAB.ZORG_OID_REGI = REG.OID_REGI
   AND CAB.ZZON_OID_ZONA = ZON.OID_ZONA
   AND CAB.ZSCC_OID_SECC = SEC.OID_SECC
   AND CAB.TERR_OID_TERR = TER.OID_TERR
   and dcl.dcca_oid_cabe = cab.oid_cabe
   and dcl.num_unid_aten <> 0
   and dcl.ind_no_impr = '0'
   and sp.oid_soli_posi = dcl.sopo_oid_soli_posi
   AND CP.PERI_OID_PERI = SPC.OID_PERI
   and cp.oid_peri = cab.perd_oid_peri
   AND CAB.PERD_OID_PERI >= lnoidperiodo
   AND CAB.FEC_FACT = TO_DATE(psfechaFacturacion, 'DD/MM/YYYY') -- fecha_facturacion
   --AND CON.IND_INTE_LARI_GENE = '1' -- en peru es 1
   AND FTD.OiD_TIPO_DOCU = 31
   AND EXISTS
       (SELECT NULL
          FROM FAC_DOCUM_CONTA_LINEA DET,
               PED_SOLIC_POSIC       PSP,
               PRE_OFERT_DETAL       POD
         WHERE DET.SOPO_OID_SOLI_POSI = PSP.OID_SOLI_POSI
           AND PSP.OFDE_OID_DETA_OFER = POD.OID_DETA_OFER(+)
           AND DET.NUM_UNID_ATEN <> 0
           AND DET.DCCA_OID_CABE = CAB.OID_CABE
           AND NOT EXISTS
         (SELECT NULL
                  FROM FAC_TIPO_OFERT_EXCLU TOE
                 WHERE TOE.TOFE_OID_TIPO_OFER = POD.TOFE_OID_TIPO_OFER))

union
select rpad(cab.val_seri_docu_lega||'-'||cab.num_docu_cont_inte,14,' ' )|| -- PK
       -- NGCA --
       'NGCA' ||
       lpad(998,3,'0') || -- item_detalle,
       rpad(' ',30,' ') || -- codigo_producto
       rpad('1' || '.000',16,' ') ||-- cant_unidades_x_item
       -- NGCB --
       'NGCB' ||
       rpad('FLETE/EMBALAJE',250,' ') ||
       -- NGCC --
       'NGCC' ||
       rpad('ZZ',3,' ') ||
       rpad(ltrim(to_char(nvl(abs(cab.IMP_FLET_TOTA_LOCA),0), '9999999990.00')),15,' ')||'01' || --precio venta unitario
       rpad(ltrim(to_char(nvl(abs(cab.IMP_FLET_IMPU_TOTA_LOCA),0), '9999999990.00')),15,' ') || -- valor venta x item
       rpad(' ',15,' ') ||
       rpad(' ',2,' ') ||
       rpad(ltrim(to_char(nvl(abs(cab.IMP_FLET_IMPU_TOTA_LOCA),0), '9999999990.00')),15,' ') || -- valor venta x item
       -- NGCD --
       'NGCD' ||
       rpad(ltrim(to_char(nvl(abs(cab.IMP_FLET_TOTA_LOCA),0)-nvl(abs(cab.IMP_FLET_IMPU_TOTA_LOCA),0), '9999999990.00')),15,' ') ||
       rpad(ltrim(to_char(nvl(abs(cab.IMP_FLET_TOTA_LOCA),0)-nvl(abs(cab.IMP_FLET_IMPU_TOTA_LOCA),0), '9999999990.00')),15,' ') ||
       rpad('10',2,' ') ||
       rpad('1000',4,' ') ||
       rpad('IGV',6,' ') ||
       rpad('VAT',3,' ') ||
       -- NGCE --
       'NGCE' ||
       rpad(' ',15,' ') ||
       rpad(' ',15,' ') ||
       rpad('  ',2,' ') ||
       rpad(' ',4,' ') ||
       rpad(' ',6,' ') ||
       rpad(' ',3,' ') ||
       -- NGCF --
       'NGCF' ||
       rpad('PRECIO CAT. UNIT.',60,' ') ||
       rpad(ltrim(to_char(nvl(abs(cab.IMP_FLET_IMPU_TOTA_LOCA),0), '9999999990.00')),100,' ') ||
       'NGCF' ||
       rpad('PRECIO CAT. UNIT. CON DSCTO',60,' ') ||
       rpad(ltrim(to_char(nvl(abs(cab.IMP_FLET_IMPU_TOTA_LOCA),0), '9999999990.00')),100,' ') ||
       'NGCF' ||
       rpad('PRECIO CAT. CON DSCTO',60,' ') ||
       rpad(ltrim(to_char(nvl(abs(cab.IMP_FLET_IMPU_TOTA_LOCA),0), '9999999990.00')),100,' '),
       cab.val_seri_docu_lega,
       cab.num_docu_cont_inte
  FROM FAC_DOCUM_CONTA_CABEC CAB
 WHERE CAB.PERD_OID_PERI >= lnoidperiodo
   AND CAB.FEC_FACT = TO_DATE(psfechaFacturacion, 'DD/MM/YYYY') -- fecha_facturacion
   --AND CON.IND_INTE_LARI_GENE = '1' -- en peru es 1
   AND cab.tido_OiD_TIPO_DOCU = 31
   and cab.IMP_FLET_TOTA_LOCA<>0
union
select rpad(cab.val_seri_docu_lega||'-'||cab.num_docu_cont_inte,14,' ' )|| -- PK
       -- NGCA --
       'NGCA' ||
       lpad(999,3,'0') || -- item_detalle,
       rpad(' ',30,' ') || -- codigo_producto
       rpad('1' || '.000',16,' ') ||-- cant_unidades_x_item
       -- NGCB --
       'NGCB' ||
       rpad('GASTOS ADMINISTRATIVOS Y DE COBRANZA',250,' ') ||
       -- NGCC --
       'NGCC' ||
       rpad('ZZ',3,' ') ||
       rpad(ltrim(to_char(nvl(abs(cab.VAL_TOTA_GAST_ADMI),0), '9999999990.00')),15,' ')||'01' || --precio venta unitario
       rpad(ltrim(to_char(nvl(abs(cab.VAL_TOTA_GAST_ADMI_SIN_IMPU),0), '9999999990.00')),15,' ') || -- valor venta x item
       rpad(' ',15,' ') ||
       rpad(' ',2,' ') ||
       rpad(ltrim(to_char(nvl(abs(cab.VAL_TOTA_GAST_ADMI_SIN_IMPU),0), '9999999990.00')),15,' ') || -- valor venta x item
       -- NGCD --
       'NGCD' ||
       rpad(ltrim(to_char(nvl(abs(cab.VAL_TOTA_GAST_ADMI),0)-nvl(abs(cab.VAL_TOTA_GAST_ADMI_SIN_IMPU),0), '9999999990.00')),15,' ') ||
       rpad(ltrim(to_char(nvl(abs(cab.VAL_TOTA_GAST_ADMI),0)-nvl(abs(cab.VAL_TOTA_GAST_ADMI_SIN_IMPU),0), '9999999990.00')),15,' ') ||
       rpad('10',2,' ') ||
       rpad('1000',4,' ') ||
       rpad('IGV',6,' ') ||
       rpad('VAT',3,' ') ||
       -- NGCE --
       'NGCE' ||
       rpad(' ',15,' ') ||
       rpad(' ',15,' ') ||
       rpad('  ',2,' ') ||
       rpad(' ',4,' ') ||
       rpad(' ',6,' ') ||
       rpad(' ',3,' ') ||
       -- NGCF --
       'NGCF' ||
       rpad('PRECIO CAT. UNIT.',60,' ') ||
       rpad(ltrim(to_char(nvl(abs(cab.VAL_TOTA_GAST_ADMI_SIN_IMPU),0), '9999999990.00')),100,' ') ||
       'NGCF' ||
       rpad('PRECIO CAT. UNIT. CON DSCTO',60,' ') ||
       rpad(ltrim(to_char(nvl(abs(cab.VAL_TOTA_GAST_ADMI_SIN_IMPU),0), '9999999990.00')),100,' ') ||
       'NGCF' ||
       rpad('PRECIO CAT. CON DSCTO',60,' ') ||
       rpad(ltrim(to_char(nvl(abs(cab.VAL_TOTA_GAST_ADMI_SIN_IMPU),0), '9999999990.00')),100,' '),
       cab.val_seri_docu_lega,
       cab.num_docu_cont_inte
  FROM FAC_DOCUM_CONTA_CABEC CAB
 WHERE CAB.PERD_OID_PERI >= lnoidperiodo
   AND CAB.FEC_FACT = TO_DATE(psfechaFacturacion, 'DD/MM/YYYY') -- fecha_facturacion
   --AND CON.IND_INTE_LARI_GENE = '1' -- en peru es 1
   AND cab.tido_OiD_TIPO_DOCU = 31
   and cab.VAL_TOTA_GAST_ADMI<>0;

 TYPE documentorecord IS RECORD (
        valorColumna1 varchar2(4000),
        val_seri_docu_lega FAC_DOCUM_CONTA_CABEC.VAL_SERI_DOCU_LEGA%TYPE,
        num_docu_cont_inte FAC_DOCUM_CONTA_CABEC.NUM_DOCU_CONT_INTE%TYPE
 );

 TYPE documentotype IS TABLE OF documentorecord;
 r_documento        documentotype;

 /* Variables usadas para la generacion del archivo de texto */
 lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
 v_handle            UTL_FILE.FILE_TYPE;

 lsLinea             VARCHAR2(10000);
 lsNombreArchivo     VARCHAR2(50);

 lbAbrirUtlFile      BOOLEAN;
 lbIngreso           BOOLEAN;
BEGIN
    l_oidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(pscodigoPeriodo);

    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;

    -- Abrimos el cursor
    OPEN c_documento(l_oidPeriodo);
    LOOP
        FETCH c_documento BULK COLLECT INTO r_documento LIMIT w_filas;

        /* Procedimiento inicial para generar interfaz */
        IF lbAbrirUtlFile THEN
              GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER_MAXCA(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                     psNombreArchivo, UTL_FILE_MAXI_CARA, lsDirTempo, lsNombreArchivo, V_HANDLE);
           lbAbrirUtlFile := FALSE;
        END IF;

        IF  r_documento.COUNT > 0 THEN
            FOR i IN r_documento.FIRST..r_documento.LAST LOOP
                lsLinea :=  r_documento(i).valorColumna1 ;
                BEGIN
                    lbIngreso := true;
                INSERT INTO IMP_TMP_FELEC_DETAL(TIP_DOC,
                                                NUM_SERI,
                                                NUM_DOCU_INTE,
                                                NUM_SECU,
                                                TXT_DETA1,
                                                TXT_DETA2)
                                         VALUES('04',
                                                r_documento(i).val_seri_docu_lega,
                                                r_documento(i).num_docu_cont_inte,
                            to_number(substr(r_documento(i).valorColumna1,19,3)),
                                                r_documento(i).valorColumna1,
                                                null);
                EXCEPTION
                WHEN DUP_VAL_ON_INDEX THEN 
                     lbIngreso := false;
                END;

                UTL_FILE.PUT_LINE (V_HANDLE, lslinea);
            END LOOP;
        END IF;

        EXIT WHEN c_documento%NOTFOUND;
    END LOOP;
    -- Cerramos el cursor
    CLOSE c_documento;

    IF NOT lbAbrirUtlFile THEN
          utl_file.fclose(V_HANDLE);
         /* Procedimiento final para generar interfaz */
         GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;
EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_IMP_NOCRB_DETAL_PERU: '||ls_sqlerrm);
END INT_PR_IMP_NOCRB_DETAL_PERU;


/**************************************************************************
Descripcion         : Proceso que devuelve el codigo de periodo y la fecha de facturacion
                      de un pedido. Lo devuelve el valor en concatenado por una -
Fecha Creación      : 01/04/2013
Autor               : Carlos Bazalar
***************************************************************************/
FUNCTION IMP_FN_DEVUE_PERIO_FECHA_PEDID(psnumeropedido    VARCHAR2,
                                      pscodigoPeriodo     VARCHAR2,
                                      psfechaFacturacion  VARCHAR2)
RETURN VARCHAR2
is
  lsRetorno varchar2(50):= '';
  lnoidperiodo  PED_SOLIC_CABEC.Perd_Oid_Peri%type;
  ldfechafactu  PED_SOLIC_CABEC.Fec_Fact%type;
  lscodperi     varchar2(6);
  lsfechaperi   varchar2(10);
  lnNumeroPedido NUMBER;
  BEGIN
  lnNumeroPedido := to_number(trim(psnumeropedido));
  BEGIN
    SELECT X.PERD_OID_PERI, X.FEC_FACT
    INTO
           lnoidperiodo, ldfechafactu
    FROM PED_SOLIC_CABEC X
    WHERE X.VAL_NUME_SOLI = lnNumeroPedido;
  EXCEPTION
  WHEN NO_DATA_FOUND THEN
     RETURN lsRetorno;
  END;

  BEGIN
     SELECT a.cod_peri
      INTO lscodperi
      FROM seg_perio_corpo a,
           cra_perio       b
     WHERE b.oid_peri = lnoidperiodo
       AND a.oid_peri = b.peri_oid_peri;
  EXCEPTION
  WHEN NO_DATA_FOUND THEN
     RETURN lsRetorno;
  END;
  lsfechaperi := to_char(ldfechafactu, 'DD/MM/YYYY');
  lsRetorno := lscodperi || '-' || lsfechaperi;

  return lsRetorno;
END IMP_FN_DEVUE_PERIO_FECHA_PEDID;


/**************************************************************************
Descripcion         : Proceso que devuelve el codigo de periodo y la fecha de facturacion
                      de un pedido. Lo devuelve el valor en concatenado por una -
Fecha Creación      : 01/04/2013
Autor               : Carlos Bazalar
***************************************************************************/
FUNCTION IMP_FN_DEVUE_LOTE_PROD_PERU(psnumeropedido    VARCHAR2,
                                      pscodigoProducto     VARCHAR2)
RETURN VARCHAR2
is

CURSOR c_lote IS
select x.num_lote from yob_carga_lotes_traza x
where x.num_pedi=psnumeropedido and x.cod_sap=pscodigoProducto
 AND X.IND_ENVI_FE IS NULL;

r_lote c_lote%ROWTYPE;

  lsRetorno varchar2(4000):= '';

  BEGIN


    OPEN c_lote;
    LOOP
    FETCH c_lote INTO r_lote;
    EXIT WHEN c_lote%NOTFOUND;
         lsRetorno:=lsRetorno || r_lote.num_lote || '-';
    END LOOP;

    begin
      if lsRetorno IS NULL then

         select a.lot_sap
         into lsRetorno
         from sap_traza_lote a
         where a.cod_sap=pscodigoProducto and rownum=1;
      else
        lsRetorno:=substr(lsRetorno,1,length(lsRetorno)-1);
      end if;


    exception when no_data_found then
      lsRetorno:='';
    end;
  IF length(lsRetorno) > 250 THEN
     lsRetorno:=substr(lsRetorno,1, 250);
  END IF;
  return lsRetorno;
END IMP_FN_DEVUE_LOTE_PROD_PERU;

/**************************************************************************
Descripcion         : NTERFAZ PARA SOFTWARE DE ORDENAMIENTO E IMPRESIÓN EN XEROX (PERU)
Fecha Creación      : 29/05/2013
Autor               : Jose Cairampoma
***************************************************************************/
PROCEDURE imp_pr_orden_impre_xerox
(
  pscodigopais       VARCHAR2,
  pscodigosistema    VARCHAR2,
  pscodigointerfaz   VARCHAR2,
  psnombrearchivo    VARCHAR2,
  pscodigoperiodo    VARCHAR2,
  psfechafacturacion VARCHAR2
) IS
  CURSOR c_interfaz(vnoidperiodo NUMBER) IS
    select * from (
    SELECT f.cod_zona,
           c.cod_clie,
           a. num_secu_fact_diar,
           decode(instr(g.val_seri_docu_lega, 'B'), 0, 'B', '') ||
           g.val_seri_docu_lega serie,
           g.num_docu_cont_inte,
           c.val_ape1 || ' ' || c.val_ape2 || ' ' || c.val_nom1 || ' ' ||
           c.val_nom2 nombre,
            a.val_secu_ruta_terr
      FROM ped_solic_cabec_secue a,
           ped_solic_cabec       b,
           mae_clien             c,
           mae_clien_datos_adici d,
           mae_clien_ident       e,
           zon_zona              f,
           fac_docum_conta_cabec g,
           fac_tipo_docum        ftd
     WHERE a.soca_oid_soli_cabe = b.oid_soli_cabe
       AND b.fec_fact = to_date(psfechafacturacion, 'DD/MM/YYYY')
       AND b.perd_oid_peri = vnoidperiodo
       AND b.clie_oid_clie = c.oid_clie
       AND c.oid_clie = d.clie_oid_clie
       AND c.oid_clie = e.clie_oid_clie
       AND d.ind_impr_docu = 1
       AND b.zzon_oid_zona = f.oid_zona
       AND g.tido_oid_tipo_docu = ftd.oid_tipo_docu
       AND ftd.cod_tipo_docu ='011'
       AND g.soca_oid_soli_cabe = b.oid_soli_cabe
       AND EXISTS
     (SELECT NULL
              FROM fac_docum_conta_linea det,
                   ped_solic_posic       psp,
                   pre_ofert_detal       pod
             WHERE det.sopo_oid_soli_posi = psp.oid_soli_posi
               AND psp.ofde_oid_deta_ofer = pod.oid_deta_ofer(+)
               AND det.num_unid_aten > 0
               AND det.dcca_oid_cabe = g.oid_cabe
               AND NOT EXISTS
             (SELECT NULL
                      FROM fac_tipo_ofert_exclu toe
                     WHERE toe.tofe_oid_tipo_ofer = pod.tofe_oid_tipo_ofer))
       AND e.val_iden_docu_prin = 1 
       UNION ALL
       SELECT f.cod_zona,
           c.cod_clie,
           a. num_secu_fact_diar,
           decode(instr(g.val_seri_docu_lega, 'B'), 0, 'B', '') ||
           g.val_seri_docu_lega serie,
           g.num_docu_cont_inte,
           c.val_ape1 || ' ' || c.val_ape2 || ' ' || c.val_nom1 || ' ' ||
           c.val_nom2 nombre,
            a.val_secu_ruta_terr
      FROM ped_solic_cabec_secue a,
           ped_solic_cabec       b,
           mae_clien             c,
           mae_clien_datos_adici d,
           mae_clien_ident       e,
           zon_zona              f,
           fac_docum_conta_cabec g,
           fac_tipo_docum        ftd
     WHERE a.soca_oid_soli_cabe = b.oid_soli_cabe
       AND b.fec_fact = to_date(psfechafacturacion, 'DD/MM/YYYY')
       AND b.perd_oid_peri = vnoidperiodo
       AND b.clie_oid_clie = c.oid_clie
       AND c.oid_clie = d.clie_oid_clie
       AND c.oid_clie = e.clie_oid_clie
       AND d.ind_impr_docu = 1
       AND b.zzon_oid_zona = f.oid_zona
       AND g.tido_oid_tipo_docu = ftd.oid_tipo_docu
       AND ftd.cod_tipo_docu = '012'
       AND g.soca_oid_soli_cabe = b.oid_soli_cabe
       AND f.oid_zona not in (select oid_zona from FAC_DESHA_ZONAS_ENVIO_BOLET )
       AND EXISTS
     (SELECT NULL
              FROM fac_docum_conta_linea det,
                   ped_solic_posic       psp,
                   pre_ofert_detal       pod
             WHERE det.sopo_oid_soli_posi = psp.oid_soli_posi
               AND psp.ofde_oid_deta_ofer = pod.oid_deta_ofer(+)
               AND det.num_unid_aten > 0
               AND det.dcca_oid_cabe = g.oid_cabe
               AND NOT EXISTS
             (SELECT NULL
                      FROM fac_tipo_ofert_exclu toe
                     WHERE toe.tofe_oid_tipo_ofer = pod.tofe_oid_tipo_ofer))
       AND e.val_iden_docu_prin = 1) tempo 
        ORDER BY tempo.serie,
              tempo.val_secu_ruta_terr,
         tempo.num_docu_cont_inte;

  TYPE interfazrec IS RECORD(
    codigozona      zon_zona.cod_zona%TYPE,
    codigocliente   mae_clien.cod_clie%TYPE,
    numerosecuencia ped_solic_cabec_secue.num_secu_fact_diar%TYPE,
    serie           VARCHAR2(100),
    numerodocumento fac_docum_conta_cabec.num_docu_cont_inte%TYPE,
    nombrecliente   VARCHAR2(400),
    val_secu_ruta_terr ped_solic_cabec_secue.val_secu_ruta_terr%TYPE);
  TYPE interfazrectab IS TABLE OF interfazrec;
  interfazrecord interfazrectab;
  /* Variables usadas para la generacion del archivo de texto */
  lsdirtempo bas_inter.dir_temp%TYPE;
  w_filas    NUMBER := 1000;
  v_handle   utl_file.file_type;

  lslinea VARCHAR2(1000) := '';

  lsnombrearchivo VARCHAR2(50);
  lnoidperiodo    cra_perio.oid_peri%TYPE;
  lbabrirutlfile  BOOLEAN;
BEGIN

  lnoidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoperiodo);
  /* Procedimiento inicial para generar interfaz */
  lbabrirutlfile := TRUE;

  /* Generar Archivo de Texto (Detalle) */
  OPEN c_interfaz(lnoidperiodo);
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

        lslinea := interfazrecord(x).codigozona || ';';
        lslinea := lslinea || interfazrecord(x).codigocliente || ';';
        lslinea := lslinea || interfazrecord(x).numerosecuencia || ';';
        lslinea := lslinea || interfazrecord(x).serie || ';';
        lslinea := lslinea || interfazrecord(x).numerodocumento || ';';
        lslinea := lslinea || interfazrecord(x).nombrecliente;
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
  /* Generando Archivo de Texto (Detalle) */

  RETURN;
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 1000);
    raise_application_error(-20123,
                            'ERROR IMP_PR_ORDEN_IMPRE_XEROX: ' ||
                            ls_sqlerrm);
END imp_pr_orden_impre_xerox;

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Facturas
                      Cabeceras para Peru
Fecha Creación      : 24/07/2013
Autor               : Sebastian Guerra
***************************************************************************/
PROCEDURE INT_PR_IMP_FACTU_CABEC_DOCUM(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      psFechaFacturacion  VARCHAR2,
                                      psNombreArchivo     VARCHAR2,
                                      psNumeroLote        VARCHAR2,
                                      psTipoDocumento     VARCHAR2,
                                      psSerieDocumento    VARCHAR2)
IS
  CURSOR c_documento(lnDiasDif NUMBER, lnNroGpo NUMBER) IS
  SELECT   a.txt_cabe1 AS valorColumna1
      FROM imp_tmp_felec_cabec a, imp_tmp_felec_archi b
     WHERE a.tip_doc = psTipoDocumento
       AND (psSerieDocumento IS NULL OR a.num_seri = psSerieDocumento)
       AND a.num_docu_inte = b.num_docu_elec
       AND TO_DATE(a.fec_fact,'dd/MM/yyyy') >=TRUNC(SYSDATE) - lnDiasDif
       AND b.num_grup = lnNroGpo;

 TYPE documentorecord IS RECORD (
        valorColumna1 varchar2(4000)
 );

 TYPE documentotype IS TABLE OF documentorecord;
 r_documento        documentotype;

 /* Variables usadas para la generacion del archivo de texto */
 lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
 v_handle            UTL_FILE.FILE_TYPE;

 lsLinea             VARCHAR2(10000);
 lsNombreArchivo     VARCHAR2(50);

 lbAbrirUtlFile      BOOLEAN;
 lnDiasDif           number;
 lnNumeroGrupo       NUMBER(3);
 lsNombreArchivoGrupo VARCHAR2(50);

 cursor c_grupo is
    select distinct num_grup
    from imp_tmp_felec_archi
    order by num_grup;

BEGIN
   begin
  select VAL_PARA INTO lnDiasDif
      from bas_param_pais
      where cod_pais= psCodigoPais
       and cod_sist='FAC'
       and cod_para='002';
    exception
       when others then
          lnDiasDif:=3;--default
    end;

    -- Abrimos el cursor de grupos
  OPEN c_grupo;

  LOOP
    FETCH c_grupo
      INTO lnnumerogrupo;
    EXIT WHEN c_grupo%NOTFOUND;

    -- Generamos los archivos de cada uno de los grupos
    
    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;

    -- Abrimos el cursor
    OPEN c_documento(lnDiasDif, lnnumerogrupo);
    LOOP
        FETCH c_documento BULK COLLECT INTO r_documento LIMIT w_filas;

        IF  r_documento.COUNT > 0 THEN

            /* Procedimiento inicial para generar interfaz */
            IF lbAbrirUtlFile THEN
              -- Armamos el nombre del archivo
                lsnombrearchivogrupo := psnombrearchivo || lpad(lnnumerogrupo, 3, '0');
              
              GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER_MAXCA01(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                       lsnombrearchivogrupo, UTL_FILE_MAXI_CARA, lsDirTempo, lsNombreArchivo, V_HANDLE);
                lbAbrirUtlFile := FALSE;
            END IF;

            FOR i IN r_documento.FIRST..r_documento.LAST LOOP
                lsLinea :=  r_documento(i).valorColumna1 ;
                UTL_FILE.PUT_LINE (V_HANDLE, lslinea);
            END LOOP;
        END IF;

        EXIT WHEN c_documento%NOTFOUND;
    END LOOP;
    -- Cerramos el cursor
    CLOSE c_documento;

    IF NOT lbAbrirUtlFile THEN
          utl_file.fclose(V_HANDLE);
         /* Procedimiento final para generar interfaz */
         GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, lsnombrearchivogrupo, lsNombreArchivo);
    END IF;

  END LOOP;
  CLOSE c_grupo;
  
  /*Archivo dummy para que el proceso no se caiga */
  gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                         pscodigosistema,
                                         pscodigointerfaz,
                                         psnombrearchivo,
                                         lsdirtempo,
                                         lsnombrearchivo,
                                         v_handle);
  utl_file.put_line(v_handle, '0');
  utl_file.fclose(v_handle);

  gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                         lsdirtempo,
                                         psnombrearchivo,
                                         lsnombrearchivo);
  /**/

    RETURN;

EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_IMP_FACTU_CABEC_DOCUM: '||ls_sqlerrm);
END INT_PR_IMP_FACTU_CABEC_DOCUM;

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Facturas
                      Detalle para Peru
Fecha Creación      : 24/07/2013
Autor               : Sebastian Guerra
***************************************************************************/
PROCEDURE INT_PR_IMP_FACTU_DETAL_DOCUM(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      psFechaFacturacion  VARCHAR2,
                                      psNombreArchivo     VARCHAR2,
                                      psNumeroLote        VARCHAR2,
                                      psTipoDocumento     VARCHAR2,
                                      psSerieDocumento    VARCHAR2)
IS
 CURSOR c_documento(lnDiasDif NUMBER, lnNroGpo NUMBER) IS
  select a.txt_deta1 as valorColumna1
    from imp_tmp_felec_detal a,
         imp_tmp_felec_cabec b,
         imp_tmp_felec_archi c
   where a.tip_doc = b.tip_doc
     and a.num_seri = b.num_seri
     and a.num_docu_inte = b.num_docu_inte
     and a.tip_doc = psTipoDocumento
     and (psSerieDocumento IS NULL OR a.num_seri = psSerieDocumento)
     and a.num_docu_inte = c.num_docu_elec
     --and b.fec_fact = psFechaFacturacion
     AND TO_DATE(b.fec_fact,'dd/MM/yyyy') >=TRUNC(SYSDATE) - lnDiasDif
     AND c.num_grup = lnNroGpo
     order by a.num_seri,a.num_docu_inte,a.num_secu;

 TYPE documentorecord IS RECORD (
        valorColumna1 VARCHAR2(4000)
 );

 TYPE documentotype IS TABLE OF documentorecord;
 r_documento        documentotype;

 /* Variables usadas para la generacion del archivo de texto */
 lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
 v_handle            UTL_FILE.FILE_TYPE;

 lsLinea             VARCHAR2(10000);
 lsNombreArchivo     VARCHAR2(50);

 lbAbrirUtlFile      BOOLEAN;

lnDiasDif           number;
 lnNumeroGrupo       NUMBER(3);
 lsNombreArchivoGrupo VARCHAR2(50);

 cursor c_grupo is
    select distinct num_grup
    from imp_tmp_felec_archi
    order by num_grup;

BEGIN
   begin
      select VAL_PARA INTO lnDiasDif
      from bas_param_pais
      where cod_pais= psCodigoPais
       and cod_sist='FAC'
       and cod_para='002';
    exception
       when others then
          lnDiasDif:=3;--default
    end;

    -- Abrimos el cursor de grupos
  OPEN c_grupo;

  LOOP
    FETCH c_grupo
      INTO lnnumerogrupo;
    EXIT WHEN c_grupo%NOTFOUND;

    -- Generamos los archivos de cada uno de los grupos
    
    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;

    -- Abrimos el cursor
    OPEN c_documento(lnDiasDif, lnnumerogrupo);
    LOOP
        FETCH c_documento BULK COLLECT INTO r_documento LIMIT w_filas;

        IF  r_documento.COUNT > 0 THEN

            /* Procedimiento inicial para generar interfaz */
            IF lbAbrirUtlFile THEN
                -- Armamos el nombre del archivo
                lsnombrearchivogrupo := psnombrearchivo || lpad(lnnumerogrupo, 3, '0');
                
                -- --
                GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER_MAXCA01(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                         lsnombrearchivogrupo, UTL_FILE_MAXI_CARA, lsDirTempo, lsNombreArchivo, V_HANDLE);
                lbAbrirUtlFile := FALSE;
            END IF;

            FOR i IN r_documento.FIRST..r_documento.LAST LOOP
                lsLinea :=  r_documento(i).valorColumna1 ;
                UTL_FILE.PUT_LINE (V_HANDLE, lslinea);
            END LOOP;
        END IF;

        EXIT WHEN c_documento%NOTFOUND;
    END LOOP;
    -- Cerramos el cursor
    CLOSE c_documento;

    IF NOT lbAbrirUtlFile THEN
          utl_file.fclose(V_HANDLE);
         /* Procedimiento final para generar interfaz */
         GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, lsnombrearchivogrupo, lsNombreArchivo);
    END IF;

  END LOOP;
  CLOSE c_grupo;
  
  /*Archivo dummy para que el proceso no se caiga */
  gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                                           pscodigosistema,
                                                           pscodigointerfaz,
                                                           psnombrearchivo,
                                                           lsdirtempo,
                                                           lsnombrearchivo,
                                                           v_handle);
  utl_file.put_line(v_handle, '0');
  utl_file.fclose(v_handle);

  gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                                            lsdirtempo,
                                                            psnombrearchivo,
                                                            lsnombrearchivo);
  /**/

    RETURN;

EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_IMP_FACTU_DETAL_DOCUM: '||ls_sqlerrm);


END INT_PR_IMP_FACTU_DETAL_DOCUM;

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Boletas 065
                      Cabecera para Peru
Fecha Creación      : 24/07/2013
Autor               : Sebastian Guerra
***************************************************************************/
PROCEDURE INT_PR_IMP_BOL65_CABEC_DOCUM(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      psFechaFacturacion  VARCHAR2,
                                      psNombreArchivo     VARCHAR2,
                                      psNumeroLote        VARCHAR2,
                                      psTipoDocumento     VARCHAR2,
                                      psSerieDocumento    VARCHAR2)
IS
 CURSOR c_documento(lnDiasDif NUMBER, lnNroGpo NUMBER) IS
  SELECT   a.txt_cabe1 AS valorColumna1,
           a.txt_cabe2 AS valorColumna2 
      FROM imp_tmp_felec_cabec a, imp_tmp_felec_archi b
     WHERE a.tip_doc = psTipoDocumento
       AND (psSerieDocumento IS NULL OR a.num_seri = psSerieDocumento)
       AND a.num_docu_inte = b.num_docu_elec
       AND TO_DATE(a.fec_fact,'dd/MM/yyyy') >=TRUNC(SYSDATE) - lnDiasDif
       AND b.num_grup = lnNroGpo;

 TYPE documentorecord IS RECORD (
        valorColumna1 varchar2(4000),
        valorColumna2 varchar2(4000)
 );

 TYPE documentotype IS TABLE OF documentorecord;
 r_documento        documentotype;

 /* Variables usadas para la generacion del archivo de texto */
 lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
 v_handle            UTL_FILE.FILE_TYPE;

 lsLinea             VARCHAR2(10000);
 lsNombreArchivo     VARCHAR2(50);

 lbAbrirUtlFile      BOOLEAN;

 lnDiasDif           number;
 lnNumeroGrupo       NUMBER(3);
 lsNombreArchivoGrupo VARCHAR2(50);
 
 cursor c_grupo is
    select distinct num_grup
      from imp_tmp_felec_archi
     order by num_grup;

BEGIN
   begin
      select VAL_PARA INTO lnDiasDif
      from bas_param_pais
      where cod_pais= psCodigoPais
       and cod_sist='FAC'
       and cod_para='002';
    exception
       when others then
          lnDiasDif:=3;--default
    end;

   -- Abrimos el cursor de grupos
  OPEN c_grupo;

  LOOP
    FETCH c_grupo
      INTO lnnumerogrupo;
    EXIT WHEN c_grupo%NOTFOUND;

    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;

    -- Abrimos el cursor
    OPEN c_documento(lnDiasDif, lnnumerogrupo);
        LOOP
            FETCH c_documento BULK COLLECT INTO r_documento LIMIT w_filas;

            IF  r_documento.COUNT > 0 THEN

                /* Procedimiento inicial para generar interfaz */
                IF lbAbrirUtlFile THEN
                    -- Armamos el nombre del archivo
                    lsnombrearchivogrupo := psnombrearchivo || lpad(lnnumerogrupo, 3, '0');
                
                    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER_MAXCA01(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                             lsnombrearchivogrupo, UTL_FILE_MAXI_CARA, lsDirTempo, lsNombreArchivo, V_HANDLE);
                    lbAbrirUtlFile := FALSE;
                END IF;

                FOR i IN r_documento.FIRST..r_documento.LAST LOOP
                    lsLinea :=  r_documento(i).valorColumna1 || r_documento(i).valorColumna2;
                    UTL_FILE.PUT_LINE (V_HANDLE, lslinea);
                END LOOP;
            END IF;

            EXIT WHEN c_documento%NOTFOUND;
        END LOOP;
        -- Cerramos el cursor
        CLOSE c_documento;

        IF NOT lbAbrirUtlFile THEN
              utl_file.fclose(V_HANDLE);
             /* Procedimiento final para generar interfaz */
             GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, lsnombrearchivogrupo, lsNombreArchivo);
        END IF;

  END LOOP;

  CLOSE c_grupo;
  
  /*Archivo dummy para que el proceso no se caiga */
  gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                                           pscodigosistema,
                                                           pscodigointerfaz,
                                                           psnombrearchivo,
                                                           lsdirtempo,
                                                           lsnombrearchivo,
                                                           v_handle);
  utl_file.put_line(v_handle, '0');
  utl_file.fclose(v_handle);

  gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                                           lsdirtempo,
                                                           psnombrearchivo,
                                                           lsnombrearchivo);
  /**/

    RETURN;

EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_IMP_BOL65_CABEC_DOCUM: '||ls_sqlerrm);
END INT_PR_IMP_BOL65_CABEC_DOCUM;

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Boletas 065
                      Detalle para Peru
Fecha Creación      : 24/07/2013
Autor               : Sebastian Guerra
***************************************************************************/
PROCEDURE INT_PR_IMP_BOL65_DETAL_DOCUM(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      psFechaFacturacion  VARCHAR2,
                                      psNombreArchivo     VARCHAR2,
                                      psNumeroLote        VARCHAR2,
                                      psTipoDocumento     VARCHAR2,
                                      psSerieDocumento    VARCHAR2)
IS
 CURSOR c_documento(lnDiasDif NUMBER, lnNroGpo NUMBER) IS
  select a.txt_deta1 as valorColumna1
    from imp_tmp_felec_detal a,
         imp_tmp_felec_cabec b,
         imp_tmp_felec_archi c
   where a.tip_doc = b.tip_doc
     and a.num_seri = b.num_seri
     and a.num_docu_inte = b.num_docu_inte
     and a.tip_doc = psTipoDocumento
     and (psSerieDocumento IS NULL OR a.num_seri = psSerieDocumento)
     and a.num_docu_inte = c.num_docu_elec
     --and b.fec_fact = psFechaFacturacion
     AND TO_DATE(b.fec_fact,'dd/MM/yyyy') >=TRUNC(SYSDATE) - lnDiasDif
     AND c.num_grup = lnNroGpo
     order by a.num_seri,a.num_docu_inte,a.num_secu;

 TYPE documentorecord IS RECORD (
        valorColumna1 varchar2(4000)
 );

 TYPE documentotype IS TABLE OF documentorecord;
 r_documento        documentotype;

 /* Variables usadas para la generacion del archivo de texto */
 lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
 v_handle            UTL_FILE.FILE_TYPE;

 lsLinea             VARCHAR2(10000);
 lsNombreArchivo     VARCHAR2(50);

 lbAbrirUtlFile      BOOLEAN;

 lnDiasDif           number;
 lnNumeroGrupo       NUMBER(3);
 lsNombreArchivoGrupo VARCHAR2(50);
 
 cursor c_grupo is
    select distinct num_grup
    from imp_tmp_felec_archi
    order by num_grup;

BEGIN
   begin
  select VAL_PARA INTO lnDiasDif
      from bas_param_pais
      where cod_pais= psCodigoPais
       and cod_sist='FAC'
       and cod_para='002';
    exception
       when others then
          lnDiasDif:=3;--default
    end;

    -- Abrimos el cursor de grupos
  OPEN c_grupo;

  LOOP
    FETCH c_grupo
      INTO lnnumerogrupo;
    EXIT WHEN c_grupo%NOTFOUND;

    -- Generamos los archivos de cada uno de los grupos
    
    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;

    -- Abrimos el cursor
    OPEN c_documento(lnDiasDif, lnnumerogrupo);
    LOOP
        FETCH c_documento BULK COLLECT INTO r_documento LIMIT w_filas;

        IF  r_documento.COUNT > 0 THEN

            /* Procedimiento inicial para generar interfaz */
            IF lbAbrirUtlFile THEN
                -- Armamos el nombre del archivo
                lsnombrearchivogrupo := psnombrearchivo || lpad(lnnumerogrupo, 3, '0');
                
                GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER_MAXCA01(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                         lsnombrearchivogrupo, UTL_FILE_MAXI_CARA, lsDirTempo, lsNombreArchivo, V_HANDLE);
                lbAbrirUtlFile := FALSE;
            END IF;

            FOR i IN r_documento.FIRST..r_documento.LAST LOOP
                lsLinea :=  r_documento(i).valorColumna1 ;
                UTL_FILE.PUT_LINE (V_HANDLE, lslinea);
            END LOOP;
        END IF;

        EXIT WHEN c_documento%NOTFOUND;
    END LOOP;
    -- Cerramos el cursor
    CLOSE c_documento;

    IF NOT lbAbrirUtlFile THEN
          utl_file.fclose(V_HANDLE);
          /* Procedimiento final para generar interfaz */
          GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, lsnombrearchivogrupo, lsNombreArchivo);
    END IF;

  END LOOP;
  CLOSE c_grupo;
  
  /*Archivo dummy para que el proceso no se caiga */
  gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                                           pscodigosistema,
                                                           pscodigointerfaz,
                                                           psnombrearchivo,
                                                           lsdirtempo,
                                                           lsnombrearchivo,
                                                           v_handle);
  utl_file.put_line(v_handle, '0');
  utl_file.fclose(v_handle);

  gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                                           lsdirtempo,
                                                           psnombrearchivo,
                                                           lsnombrearchivo);
  /**/

    RETURN;

EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_IMP_BOL65_DETAL_DOCUM: '||ls_sqlerrm);
END INT_PR_IMP_BOL65_DETAL_DOCUM;

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Boletas 067
                      Cabecera para Peru
Fecha Creación      : 24/07/2013
Autor               : Sebastian Guerra
***************************************************************************/
PROCEDURE INT_PR_IMP_BOL67_CABEC_DOCUM(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      psFechaFacturacion  VARCHAR2,
                                      psNombreArchivo     VARCHAR2,
                                      psNumeroLote        VARCHAR2,
                                      psTipoDocumento     VARCHAR2,
                                      psSerieDocumento    VARCHAR2)
IS
  CURSOR c_documento(lnDiasDif NUMBER, lnNroGpo NUMBER) IS
  SELECT   substr(a.txt_cabe1,1,3587) AS valorColumna1, 
           a.txt_cabe2 AS valorColumna2
      FROM imp_tmp_felec_cabec a, imp_tmp_felec_archi b
     WHERE a.tip_doc = psTipoDocumento
       AND (psSerieDocumento IS NULL OR a.num_seri = psSerieDocumento)
       AND a.num_docu_inte = b.num_docu_elec
       AND TO_DATE(a.fec_fact,'dd/MM/yyyy') >=TRUNC(SYSDATE) - lnDiasDif
       AND b.num_grup = lnNroGpo;

 TYPE documentorecord IS RECORD (
        valorColumna1 varchar2(4000),
        valorColumna2 varchar2(4000)
 );

 TYPE documentotype IS TABLE OF documentorecord;
 r_documento        documentotype;

 /* Variables usadas para la generacion del archivo de texto */
 lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
 v_handle            UTL_FILE.FILE_TYPE;

 lsLinea             VARCHAR2(10000);
 lsNombreArchivo     VARCHAR2(50);

 lbAbrirUtlFile      BOOLEAN;

 lnDiasDif           number;
 lnNumeroGrupo       NUMBER(3);
 lsNombreArchivoGrupo VARCHAR2(50);
 
 cursor c_grupo is
    select distinct num_grup
    from imp_tmp_felec_archi
    order by num_grup;

BEGIN
   begin
  select VAL_PARA INTO lnDiasDif
      from bas_param_pais
      where cod_pais= psCodigoPais
       and cod_sist='FAC'
       and cod_para='002';
    exception
       when others then
          lnDiasDif:=3;--default
    end;

    -- Abrimos el cursor de grupos
  OPEN c_grupo;

  LOOP
    FETCH c_grupo
      INTO lnnumerogrupo;
    EXIT WHEN c_grupo%NOTFOUND;

    -- Generamos los archivos de cada uno de los grupos
    
    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;

    -- Abrimos el cursor
    OPEN c_documento(lnDiasDif, lnnumerogrupo);
        LOOP
            FETCH c_documento BULK COLLECT INTO r_documento LIMIT w_filas;

            IF  r_documento.COUNT > 0 THEN

                /* Procedimiento inicial para generar interfaz */
                IF lbAbrirUtlFile THEN
                    lsnombrearchivogrupo := psnombrearchivo || lpad(lnnumerogrupo, 3, '0');
                    
                    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER_MAXCA01(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                             lsnombrearchivogrupo, UTL_FILE_MAXI_CARA, lsDirTempo, lsNombreArchivo, V_HANDLE);
                    lbAbrirUtlFile := FALSE;
                END IF;

                FOR i IN r_documento.FIRST..r_documento.LAST LOOP
                    lsLinea :=  r_documento(i).valorColumna1 ||''||
                                r_documento(i).valorColumna2 ;
                    UTL_FILE.PUT_LINE (V_HANDLE, lslinea);
                END LOOP;
            END IF;

            EXIT WHEN c_documento%NOTFOUND;
        END LOOP;
        -- Cerramos el cursor
        CLOSE c_documento;

        IF NOT lbAbrirUtlFile THEN
              utl_file.fclose(V_HANDLE);
             /* Procedimiento final para generar interfaz */
             GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, lsnombrearchivogrupo, lsNombreArchivo);
        END IF;

  END LOOP;
  CLOSE c_grupo;
  
  /*Archivo dummy para que el proceso no se caiga */
  gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                                           pscodigosistema,
                                                           pscodigointerfaz,
                                                           psnombrearchivo,
                                                           lsdirtempo,
                                                           lsnombrearchivo,
                                                           v_handle);
  utl_file.put_line(v_handle, '0');
  utl_file.fclose(v_handle);

  gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                                           lsdirtempo,
                                                           psnombrearchivo,
                                                           lsnombrearchivo);
  /**/

    RETURN;

EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_IMP_BOL67_CABEC_DOCUM: '||ls_sqlerrm);
END INT_PR_IMP_BOL67_CABEC_DOCUM;

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Boletas 067
                      Detalle para Peru
Fecha Creación      : 24/07/2013
Autor               : Sebastian Guerra
***************************************************************************/
PROCEDURE INT_PR_IMP_BOL67_DETAL_DOCUM(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      psFechaFacturacion  VARCHAR2,
                                      psNombreArchivo     VARCHAR2,
                                      psNumeroLote        VARCHAR2,
                                      psTipoDocumento     VARCHAR2,
                                      psSerieDocumento    VARCHAR2)
IS
 CURSOR c_documento(lnDiasDif NUMBER, lnNroGpo NUMBER) IS
  select a.txt_deta1 as valorColumna1
    from imp_tmp_felec_detal a,
         imp_tmp_felec_cabec b,
         imp_tmp_felec_archi c
   where a.tip_doc = b.tip_doc
     and a.num_seri = b.num_seri
     and a.num_docu_inte = b.num_docu_inte
     and a.tip_doc = psTipoDocumento
     and (psSerieDocumento IS NULL OR a.num_seri = psSerieDocumento)
     and a.num_docu_inte = c.num_docu_elec
     --and b.fec_fact = psFechaFacturacion
    AND TO_DATE(b.fec_fact,'dd/MM/yyyy') >=TRUNC(SYSDATE) - lnDiasDif
    AND c.num_grup = lnNroGpo
     order by a.num_seri,a.num_docu_inte,a.num_secu;


 TYPE documentorecord IS RECORD (
        valorColumna1 varchar2(4000)
 );

 TYPE documentotype IS TABLE OF documentorecord;
 r_documento        documentotype;

 /* Variables usadas para la generacion del archivo de texto */
 lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
 v_handle            UTL_FILE.FILE_TYPE;

 lsLinea             VARCHAR2(10000);
 lsNombreArchivo     VARCHAR2(50);

 lbAbrirUtlFile      BOOLEAN;
 lnDiasDif           number;
 lnNumeroGrupo       NUMBER(3);
 lsNombreArchivoGrupo VARCHAR2(50);
 
 cursor c_grupo is
    select distinct num_grup
    from imp_tmp_felec_archi
    order by num_grup;

BEGIN
   begin
   select VAL_PARA INTO lnDiasDif
      from bas_param_pais
      where cod_pais= psCodigoPais
       and cod_sist='FAC'
       and cod_para='002';
    exception
       when others then
          lnDiasDif:=3;--default
    end;

    -- Abrimos el cursor de grupos
  OPEN c_grupo;

  LOOP
    FETCH c_grupo
      INTO lnnumerogrupo;
    EXIT WHEN c_grupo%NOTFOUND;

    -- Generamos los archivos de cada uno de los grupos
    
    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;

    -- Abrimos el cursor
    OPEN c_documento(lnDiasDif, lnnumerogrupo);
        LOOP
            FETCH c_documento BULK COLLECT INTO r_documento LIMIT w_filas;

            IF  r_documento.COUNT > 0 THEN

                /* Procedimiento inicial para generar interfaz */
                IF lbAbrirUtlFile THEN
                    -- Armamos el nombre del archivo
                    lsnombrearchivogrupo := psnombrearchivo || lpad(lnnumerogrupo, 3, '0');
                    
                    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER_MAXCA01(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                             lsnombrearchivogrupo, UTL_FILE_MAXI_CARA, lsDirTempo, lsNombreArchivo, V_HANDLE);
                    lbAbrirUtlFile := FALSE;
                END IF;

                FOR i IN r_documento.FIRST..r_documento.LAST LOOP
                    lsLinea :=  r_documento(i).valorColumna1 ;
                    UTL_FILE.PUT_LINE (V_HANDLE, lslinea);
                END LOOP;
            END IF;

            EXIT WHEN c_documento%NOTFOUND;
        END LOOP;
        -- Cerramos el cursor
        CLOSE c_documento;

        IF NOT lbAbrirUtlFile THEN
              utl_file.fclose(V_HANDLE);
             /* Procedimiento final para generar interfaz */
             GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, lsnombrearchivogrupo, lsNombreArchivo);
        END IF;

  END LOOP;
  CLOSE c_grupo;
  
  /*Archivo dummy para que el proceso no se caiga */
  gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                                           pscodigosistema,
                                                           pscodigointerfaz,
                                                           psnombrearchivo,
                                                           lsdirtempo,
                                                           lsnombrearchivo,
                                                           v_handle);
  utl_file.put_line(v_handle, '0');
  utl_file.fclose(v_handle);

  gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                                           lsdirtempo,
                                                           psnombrearchivo,
                                                           lsnombrearchivo);
  /**/

    RETURN;

EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_IMP_BOL67_DETAL_DOCUM: '||ls_sqlerrm);
END INT_PR_IMP_BOL67_DETAL_DOCUM;

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Notas Debito
                      Cabecera para Peru
Fecha Creación      : 24/07/2013
Autor               : Sebastian Guerra
***************************************************************************/
PROCEDURE INT_PR_IMP_NOTDE_CABEC_DOCUM(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      psFechaFacturacion  VARCHAR2,
                                      psNombreArchivo     VARCHAR2,
                                      psTipoDocumento     VARCHAR2,
                                      psSerieDocumento    VARCHAR2)
IS
 CURSOR c_documento(lnDiasDif NUMBER) IS
  SELECT   a.txt_cabe1 AS valorColumna1, a.txt_cabe2 AS valorColumna2
      FROM imp_tmp_felec_cabec a, imp_tmp_felec_archi b
     WHERE a.tip_doc = psTipoDocumento
       AND (psSerieDocumento IS NULL OR a.num_seri = psSerieDocumento)
       AND a.num_docu_inte = b.num_docu_elec
       AND TO_DATE(a.fec_fact,'dd/MM/yyyy') >=TRUNC(SYSDATE) - lnDiasDif;

 TYPE documentorecord IS RECORD (
        valorColumna1 varchar2(4000),
        valorColumna2 varchar2(4000)
 );

 TYPE documentotype IS TABLE OF documentorecord;
 r_documento        documentotype;

 /* Variables usadas para la generacion del archivo de texto */
 lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
 v_handle            UTL_FILE.FILE_TYPE;

 lsLinea             VARCHAR2(10000);
 lsNombreArchivo     VARCHAR2(50);

 lbAbrirUtlFile      BOOLEAN;
 lnDiasDif           number;

BEGIN
   begin
  select VAL_PARA INTO lnDiasDif
      from bas_param_pais
      where cod_pais= psCodigoPais
       and cod_sist='FAC'
       and cod_para='002';
    exception
       when others then
          lnDiasDif:=3;--default
    end;

    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;

    -- Abrimos el cursor
    OPEN c_documento(lnDiasDif);
    LOOP
        FETCH c_documento BULK COLLECT INTO r_documento LIMIT w_filas;

        /* Procedimiento inicial para generar interfaz */
        IF lbAbrirUtlFile THEN
          GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER_MAXCA(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                   psNombreArchivo, UTL_FILE_MAXI_CARA, lsDirTempo, lsNombreArchivo, V_HANDLE);
           lbAbrirUtlFile := FALSE;
        END IF;

        IF  r_documento.COUNT > 0 THEN
            FOR i IN r_documento.FIRST..r_documento.LAST LOOP
                lsLinea :=  r_documento(i).valorColumna1 ||';'||
                            r_documento(i).valorColumna2 ;
                UTL_FILE.PUT_LINE (V_HANDLE, lslinea);
            END LOOP;
        END IF;

        EXIT WHEN c_documento%NOTFOUND;
    END LOOP;
    -- Cerramos el cursor
    CLOSE c_documento;

    IF NOT lbAbrirUtlFile THEN
          utl_file.fclose(V_HANDLE);
         /* Procedimiento final para generar interfaz */
         GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;
EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_IMP_NOTDE_CABEC_DOCUM: '||ls_sqlerrm);
END INT_PR_IMP_NOTDE_CABEC_DOCUM;

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Notas Debito
                      Detalle para Peru
Fecha Creación      : 24/07/2013
Autor               : Sebastian Guerra
***************************************************************************/
PROCEDURE INT_PR_IMP_NOTDE_DETAL_DOCUM(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      psFechaFacturacion  VARCHAR2,
                                      psNombreArchivo     VARCHAR2,
                                      psTipoDocumento     VARCHAR2,
                                      psSerieDocumento    VARCHAR2)
IS
 CURSOR c_documento(lnDiasDif NUMBER) IS
  select a.txt_deta1 as valorColumna1
    from imp_tmp_felec_detal a,
         imp_tmp_felec_cabec b,
         imp_tmp_felec_archi c
   where a.tip_doc = b.tip_doc
     and a.num_seri = b.num_seri
     and a.num_docu_inte = b.num_docu_inte
     and a.tip_doc = psTipoDocumento
     and (psSerieDocumento IS NULL OR a.num_seri = psSerieDocumento)
     and a.num_docu_inte = c.num_docu_elec
    -- and b.fec_fact = psFechaFacturacion
     AND TO_DATE(b.fec_fact,'dd/MM/yyyy') >=TRUNC(SYSDATE) - lnDiasDif
     order by a.num_seri,a.num_docu_inte,a.num_secu;

 TYPE documentorecord IS RECORD (
        valorColumna1 varchar2(4000)
 );

 TYPE documentotype IS TABLE OF documentorecord;
 r_documento        documentotype;

 /* Variables usadas para la generacion del archivo de texto */
 lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
 v_handle            UTL_FILE.FILE_TYPE;

 lsLinea             VARCHAR2(10000);
 lsNombreArchivo     VARCHAR2(50);

 lbAbrirUtlFile      BOOLEAN;
 lnDiasDif           number;

BEGIN
   begin
  select VAL_PARA INTO lnDiasDif
      from bas_param_pais
      where cod_pais= psCodigoPais
       and cod_sist='FAC'
       and cod_para='002';
    exception
       when others then
          lnDiasDif:=3;--default
    end;

    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;

    -- Abrimos el cursor
    OPEN c_documento(lnDiasDif);
    LOOP
        FETCH c_documento BULK COLLECT INTO r_documento LIMIT w_filas;

        /* Procedimiento inicial para generar interfaz */
        IF lbAbrirUtlFile THEN
          GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER_MAXCA(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                   psNombreArchivo, UTL_FILE_MAXI_CARA, lsDirTempo, lsNombreArchivo, V_HANDLE);
           lbAbrirUtlFile := FALSE;
        END IF;

        IF  r_documento.COUNT > 0 THEN
            FOR i IN r_documento.FIRST..r_documento.LAST LOOP
                lsLinea :=  r_documento(i).valorColumna1 ;
                UTL_FILE.PUT_LINE (V_HANDLE, lslinea);
            END LOOP;
        END IF;

        EXIT WHEN c_documento%NOTFOUND;
    END LOOP;
    -- Cerramos el cursor
    CLOSE c_documento;

    IF NOT lbAbrirUtlFile THEN
          utl_file.fclose(V_HANDLE);
         /* Procedimiento final para generar interfaz */
         GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;
EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_IMP_NOTDE_DETAL_DOCUM: '||ls_sqlerrm);
END INT_PR_IMP_NOTDE_DETAL_DOCUM;

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Notas Credito Facturas
                      Cabecera para Peru
Fecha Creación      : 24/07/2013
Autor               : Sebastian Guerra
***************************************************************************/
PROCEDURE INT_PR_IMP_NOCRF_CABEC_DOCUM(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      psFechaFacturacion  VARCHAR2,
                                      psNombreArchivo     VARCHAR2,
                                      psTipoDocumento     VARCHAR2,
                                      psSerieDocumento    VARCHAR2)
IS
 CURSOR c_documento(lnDiasDif NUMBER) IS
 SELECT   a.txt_cabe1 AS valorColumna1, a.txt_cabe2 AS valorColumna2
    FROM imp_tmp_felec_cabec a, imp_tmp_felec_archi b
   WHERE a.tip_doc = psTipoDocumento
     AND (psSerieDocumento IS NULL OR a.num_seri = psSerieDocumento)
     AND a.num_docu_inte = b.num_docu_elec
     AND TO_DATE(a.fec_fact,'dd/MM/yyyy') >=TRUNC(SYSDATE) - lnDiasDif;

TYPE documentorecord IS RECORD (
        valorColumna1 varchar2(4000),
        valorColumna2 varchar2(4000)
 );

 TYPE documentotype IS TABLE OF documentorecord;
 r_documento        documentotype;

 /* Variables usadas para la generacion del archivo de texto */
 lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
 v_handle            UTL_FILE.FILE_TYPE;

 lsLinea             VARCHAR2(10000);
 lsNombreArchivo     VARCHAR2(50);

 lbAbrirUtlFile      BOOLEAN;
 lnDiasDif           number;

BEGIN
   begin
  select VAL_PARA INTO lnDiasDif
      from bas_param_pais
      where cod_pais= psCodigoPais
       and cod_sist='FAC'
       and cod_para='002';
    exception
       when others then
          lnDiasDif:=3;--default
    end;

    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;

    -- Abrimos el cursor
    OPEN c_documento(lnDiasDif);
    LOOP
        FETCH c_documento BULK COLLECT INTO r_documento LIMIT w_filas;

        /* Procedimiento inicial para generar interfaz */
        IF lbAbrirUtlFile THEN
          GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER_MAXCA(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                   psNombreArchivo, UTL_FILE_MAXI_CARA, lsDirTempo, lsNombreArchivo, V_HANDLE);
           lbAbrirUtlFile := FALSE;
        END IF;

        IF  r_documento.COUNT > 0 THEN
            FOR i IN r_documento.FIRST..r_documento.LAST LOOP
                lsLinea :=  r_documento(i).valorColumna1 ||';'||
                            r_documento(i).valorColumna2;
                UTL_FILE.PUT_LINE (V_HANDLE, lslinea);
            END LOOP;
        END IF;

        EXIT WHEN c_documento%NOTFOUND;
    END LOOP;
    -- Cerramos el cursor
    CLOSE c_documento;

    IF NOT lbAbrirUtlFile THEN
          utl_file.fclose(V_HANDLE);
         /* Procedimiento final para generar interfaz */
          GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;
EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_IMP_NOCRF_CABEC_DOCUM: '||ls_sqlerrm);
END INT_PR_IMP_NOCRF_CABEC_DOCUM;

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Notas Credito Facturas
                      Detalle para Peru
Fecha Creación      : 24/07/2013
Autor               : Sebastian Guerra
***************************************************************************/
PROCEDURE INT_PR_IMP_NOCRF_DETAL_DOCUM(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      psFechaFacturacion  VARCHAR2,
                                      psNombreArchivo     VARCHAR2,
                                      psTipoDocumento     VARCHAR2,
                                      psSerieDocumento    VARCHAR2)
IS
 CURSOR c_documento(lnDiasDif NUMBER) IS
  select a.txt_deta1 as valorColumna1
    from imp_tmp_felec_detal a,
         imp_tmp_felec_cabec b,
         imp_tmp_felec_archi c
   where a.tip_doc = b.tip_doc
     and a.num_seri = b.num_seri
     and a.num_docu_inte = b.num_docu_inte
     and a.tip_doc = psTipoDocumento
     and (psSerieDocumento IS NULL OR a.num_seri = psSerieDocumento)
     and a.num_docu_inte = c.num_docu_elec
     --and b.fec_fact = psFechaFacturacion
     AND TO_DATE(b.fec_fact,'dd/MM/yyyy') >=TRUNC(SYSDATE) - lnDiasDif
     order by a.num_seri,a.num_docu_inte,a.num_secu;

 TYPE documentorecord IS RECORD (
        valorColumna1 varchar2(4000)
 );

 TYPE documentotype IS TABLE OF documentorecord;
 r_documento        documentotype;

 /* Variables usadas para la generacion del archivo de texto */
 lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
 v_handle            UTL_FILE.FILE_TYPE;

 lsLinea             VARCHAR2(10000);
 lsNombreArchivo     VARCHAR2(50);
 lbAbrirUtlFile      BOOLEAN;
 lnDiasDif           number;

BEGIN
   begin
  select VAL_PARA INTO lnDiasDif
      from bas_param_pais
      where cod_pais= psCodigoPais
       and cod_sist='FAC'
       and cod_para='002';
    exception
       when others then
          lnDiasDif:=3;--default
    end;

    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;

    -- Abrimos el cursor
    OPEN c_documento(lnDiasDif);
    LOOP
        FETCH c_documento BULK COLLECT INTO r_documento LIMIT w_filas;

        /* Procedimiento inicial para generar interfaz */
        IF lbAbrirUtlFile THEN
          GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER_MAXCA(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                   psNombreArchivo, UTL_FILE_MAXI_CARA, lsDirTempo, lsNombreArchivo, V_HANDLE);
           lbAbrirUtlFile := FALSE;
        END IF;

        IF  r_documento.COUNT > 0 THEN
            FOR i IN r_documento.FIRST..r_documento.LAST LOOP
                lsLinea :=  r_documento(i).valorColumna1 ;
                UTL_FILE.PUT_LINE (V_HANDLE, lslinea);
            END LOOP;
        END IF;

        EXIT WHEN c_documento%NOTFOUND;
    END LOOP;
    -- Cerramos el cursor
    CLOSE c_documento;

    IF NOT lbAbrirUtlFile THEN
          utl_file.fclose(V_HANDLE);
         /* Procedimiento final para generar interfaz */
          GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;
EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_IMP_NOCRF_DETAL_DOCUM: '||ls_sqlerrm);
END INT_PR_IMP_NOCRF_DETAL_DOCUM;

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Notas Credito Boletas
                      Cabecera para Peru
Fecha Creación      : 24/07/2013
Autor               : Sebastian Guerra
***************************************************************************/
PROCEDURE INT_PR_IMP_NOCRB_CABEC_DOCUM(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      psFechaFacturacion  VARCHAR2,
                                      psNombreArchivo     VARCHAR2,
                                      psTipoDocumento     VARCHAR2,
                                      psSerieDocumento    VARCHAR2)
IS
 CURSOR c_documento(lnDiasDif NUMBER) IS
 SELECT   a.txt_cabe1 AS valorColumna1, a.txt_cabe2 AS valorColumna2
    FROM imp_tmp_felec_cabec a, imp_tmp_felec_archi b
   WHERE a.tip_doc = psTipoDocumento
     AND (psSerieDocumento IS NULL OR a.num_seri = psSerieDocumento)
     AND a.num_docu_inte = b.num_docu_elec
     AND TO_DATE(a.fec_fact,'dd/MM/yyyy') >=TRUNC(SYSDATE) - lnDiasDif;

 TYPE documentorecord IS RECORD (
        valorColumna1 varchar2(4000),
        valorColumna2 varchar2(4000)
 );

 TYPE documentotype IS TABLE OF documentorecord;
 r_documento        documentotype;

 /* Variables usadas para la generacion del archivo de texto */
 lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
 v_handle            UTL_FILE.FILE_TYPE;

 lsLinea             VARCHAR2(10000);
 lsNombreArchivo     VARCHAR2(50);
 lbAbrirUtlFile      BOOLEAN;
  lnDiasDif           number;

BEGIN
   begin
  select VAL_PARA INTO lnDiasDif
      from bas_param_pais
      where cod_pais= psCodigoPais
       and cod_sist='FAC'
       and cod_para='002';
    exception
       when others then
          lnDiasDif:=3;--default
    end;

    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;

    -- Abrimos el cursor
    OPEN c_documento(lnDiasDif);
    LOOP
        FETCH c_documento BULK COLLECT INTO r_documento LIMIT w_filas;

        /* Procedimiento inicial para generar interfaz */
        IF lbAbrirUtlFile THEN
          GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER_MAXCA(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                   psNombreArchivo, UTL_FILE_MAXI_CARA, lsDirTempo, lsNombreArchivo, V_HANDLE);
           lbAbrirUtlFile := FALSE;
        END IF;

        IF  r_documento.COUNT > 0 THEN
            FOR i IN r_documento.FIRST..r_documento.LAST LOOP
                lsLinea :=  r_documento(i).valorColumna1 ||';'||
                            r_documento(i).valorColumna2;
                UTL_FILE.PUT_LINE (V_HANDLE, lslinea);
            END LOOP;
        END IF;

        EXIT WHEN c_documento%NOTFOUND;
    END LOOP;
    -- Cerramos el cursor
    CLOSE c_documento;

    IF NOT lbAbrirUtlFile THEN
          utl_file.fclose(V_HANDLE);
         /* Procedimiento final para generar interfaz */
          GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;
EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_IMP_NOCRB_CABEC_DOCUM: '||ls_sqlerrm);
END INT_PR_IMP_NOCRB_CABEC_DOCUM;

/**************************************************************************
Descripcion         : Proceso que genera la informacion de Notas Credito Boletas
                      Detalle para Peru
Fecha Creación      : 24/07/2013
Autor               : Sebastian Guerra
***************************************************************************/
PROCEDURE INT_PR_IMP_NOCRB_DETAL_DOCUM(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      psFechaFacturacion  VARCHAR2,
                                      psNombreArchivo     VARCHAR2,
                                      psTipoDocumento     VARCHAR2,
                                      psSerieDocumento    VARCHAR2)
IS
 CURSOR c_documento(lnDiasDif NUMBER) IS
  select a.txt_deta1 as valorColumna1
    from imp_tmp_felec_detal a,
         imp_tmp_felec_cabec b,
         imp_tmp_felec_archi c
   where a.tip_doc = b.tip_doc
     and a.num_seri = b.num_seri
     and a.num_docu_inte = b.num_docu_inte
     and a.tip_doc = psTipoDocumento
     and (psSerieDocumento IS NULL OR a.num_seri = psSerieDocumento)
     and a.num_docu_inte = c.num_docu_elec
     --and b.fec_fact = psFechaFacturacion
     AND TO_DATE(b.fec_fact,'dd/MM/yyyy') >=TRUNC(SYSDATE) - lnDiasDif
     order by a.num_seri,a.num_docu_inte,a.num_secu;

 TYPE documentorecord IS RECORD (
        valorColumna1 varchar2(4000)
 );

 TYPE documentotype IS TABLE OF documentorecord;
 r_documento        documentotype;

 /* Variables usadas para la generacion del archivo de texto */
 lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
 v_handle            UTL_FILE.FILE_TYPE;

 lsLinea             VARCHAR2(10000);
 lsNombreArchivo     VARCHAR2(50);

 lbAbrirUtlFile      BOOLEAN;
 lnDiasDif           number;

BEGIN
   begin
  select VAL_PARA INTO lnDiasDif
      from bas_param_pais
      where cod_pais= psCodigoPais
       and cod_sist='FAC'
       and cod_para='002';
    exception
       when others then
          lnDiasDif:=3;--default
    end;

    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;

    -- Abrimos el cursor
    OPEN c_documento(lnDiasDif);
    LOOP
        FETCH c_documento BULK COLLECT INTO r_documento LIMIT w_filas;

        /* Procedimiento inicial para generar interfaz */
        IF lbAbrirUtlFile THEN
          GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER_MAXCA(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                   psNombreArchivo, UTL_FILE_MAXI_CARA, lsDirTempo, lsNombreArchivo, V_HANDLE);
           lbAbrirUtlFile := FALSE;
        END IF;

        IF  r_documento.COUNT > 0 THEN
            FOR i IN r_documento.FIRST..r_documento.LAST LOOP
                lsLinea :=  r_documento(i).valorColumna1 ;
                UTL_FILE.PUT_LINE (V_HANDLE, lslinea);
            END LOOP;
        END IF;

        EXIT WHEN c_documento%NOTFOUND;
    END LOOP;
    -- Cerramos el cursor
    CLOSE c_documento;

    IF NOT lbAbrirUtlFile THEN
          utl_file.fclose(V_HANDLE);
         /* Procedimiento final para generar interfaz */
          GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;
EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_IMP_NOCRB_DETAL_DOCUM: '||ls_sqlerrm);
END INT_PR_IMP_NOCRB_DETAL_DOCUM;

/**************************************************************************
Descripcion         : Proceso que genera la informacion de BOLETA ELECTRONICA 
                      DE HONORARIOS PARA CHILE 
Fecha Creación      : 30/09/2013
Autor               : Sergio Apaza
***************************************************************************/
PROCEDURE IMP_PR_PROCE_BOLET_HONOR(psCodigoPais        VARCHAR2,
                                   psCodigoSistema     VARCHAR2,
                                   psCodigoInterfaz    VARCHAR2,
                                   psCodigoPeriodo     VARCHAR2,
                                   psFechaFacturacion  VARCHAR2,
                                   psNombreArchivo     VARCHAR2,
                                   psDirectorio        VARCHAR2,
                                   psCodigoUsuario     VARCHAR2) 
IS

 TYPE boletaRecord IS RECORD (
    lsTipoDTE              VARCHAR2(20),
    lsFolio                VARCHAR2(20),
    lsFechaEmision         VARCHAR2(20),
    lsFechaDeLaBoleta      VARCHAR2(20),
    lsNombreReceptor       VARCHAR2(1000),
    lsDomReceptorParte1    VARCHAR2(1000),
    lsDomReceptorComun     VARCHAR2(1000),
    lsEReceptorRutReceptorRut   VARCHAR2(20),
    lsEReceptorRutReceptorDv    VARCHAR2(20),
    lsEERutEmisorRut            VARCHAR2(20),
    lsEERutEmisorDv             VARCHAR2(20),
    lsRznSocEmisor         VARCHAR2(1000),
    lsDomEmisorParte1      VARCHAR2(1000),
    lsDomEmisorComuna      VARCHAR2(1000),
    lsGlosaActividadEconomica   VARCHAR2(1000),
    lsTextoLibreEmisor          VARCHAR2(1000),
    lsTotalBolMontoBrutoServicio    VARCHAR2(20),
    lsTotalBolRetencion             VARCHAR2(20),
    lsTotalBolNumeroDetails         VARCHAR2(20),
    lsTotalBolMontoLiquidoServicio  VARCHAR2(20),
    lsTotalBolTasa          VARCHAR2(1000),
    lsTextoLibre1           VARCHAR2(1000),
    lsTextoLibre2           VARCHAR2(1000),
    lsTextoLibre3           VARCHAR2(1000),
    lsNroLinDet             VARCHAR2(20),
    lsDetalleServicio       VARCHAR2(100),
    lsMontoBrutoDetalleServicio     VARCHAR2(20),
    lsCodigoBarra                   VARCHAR2(100),
    lnOidCliente                    NUMBER,
    lnOidCabe                       NUMBER
  );

  TYPE boletaType IS TABLE OF boletaRecord;
  r_boleta    boletaType;

  CURSOR c_Boletas(oidPeriodo NUMBER) IS
  SELECT '96' as TipoDTE,
         '0010678380' as Folio,
         TO_CHAR(a.fec_emis, 'YYYYMMDDHH24MMSS') as FechaEmision,
         TO_CHAR(a.fec_fact, 'YYYYMMDD') as FechaDeLaBoleta,
         GEN_PKG_GENER.gen_fn_reemp_carac_extra(substr(rtrim(a.val_nom1)||' '||rtrim(a.val_nom2)||' '||rtrim(a.val_ape1)||' '||rtrim(a.val_ape2),1,30)) as NombreReceptor,
         GEN_PKG_GENER.gen_fn_reemp_carac_extra(ltrim(a.val_dire_comp)) as DomReceptorParte1,
         '15128' as DomReceptorComun, 
         SUBSTR(a.val_nume_iden_fisc,1,8) as EReceptorRutReceptorRut,
         substr(a.val_nume_iden_fisc,9,1) as EReceptorRutReceptorDv,
         '96524830' as EERutEmisorRut,
         '7' as EERutEmisorDv,
         'PROMOTORA DE BELLEZA S.A.' as RznSocEmisor,
         'AV.AEROPUERTO #860, PARQUE INDUSTRIAL AEROPUERTO' as DomEmisorParte1,
         '14114' as DomEmisorComuna,
         'IMPORTADORA Y DISTRIBUIDORA DE PRODUCTOS DE BELLEZA' as GlosaActividadEconomica,
         '' as TextoLibreEmisor,
         TRIM(TO_CHAR(a.imp_desc_tota_loca,'999999990')) as TotalBolMontoBrutoServicio,
         TRIM(TO_CHAR(a.val_impo_rete_desc,'999999990')) as TotalBolRetencion,
         '1' as TotalBolNumeroDetails,
         TRIM(TO_CHAR(a.imp_desc_tota_loca-a.val_impo_rete_desc,'999999990')) as TotalBolMontoLiquidoServicio,
         '10' as TotalBolTasa,
         b.val_nume_soli as TextoLibre1,
         c.cod_clie as TextoLibre2,
         '' as TextoLibre3,
         '1' as NroLinDet,
         'HONORARIOS POR VENTA DE COSMETICOS' as DetalleServicio,
         TRIM(TO_CHAR(a.imp_desc_tota_loca,'999999990')) as MontoBrutoDetalleServicio,
         '8507790078380AE09B83' as CodigoBarra,
         c.Oid_Clie oidCliente,
         a.oid_cabe
    FROM FAC_DOCUM_CONTA_CABEC a, 
         PED_SOLIC_CABEC b, 
         MAE_CLIEN c
   WHERE a.perd_oid_peri = oidPeriodo
     AND a.fec_fact = to_date(psFechaFacturacion, 'dd/mm/yyyy') 
     AND a.SOCA_OID_SOLI_CABE = b.oid_soli_cabe 
     AND a.tido_oid_tipo_docu = 1
     --AND a.val_tota_paga_loca <> 0
     AND b.clie_oid_Clie = c.oid_Clie 
     --AND a.imp_desc_tota_loca > 0
     and a.val_impo_rete_desc>0
     and exists
          (
          select 1 
            from fac_docum_conta_linea xx, ped_solic_posic yy, pre_ofert_detal zz
           where xx.dcca_oid_cabe=a.oid_cabe 
             and xx.num_unid_aten>0
             and xx.sopo_oid_soli_posi=yy.oid_soli_posi
             and yy.ofde_oid_deta_ofer=zz.oid_deta_ofer
             and zz.tofe_oid_tipo_ofer not in (select tofe_oid_tipo_ofer from fac_tipo_ofert_exclu)
          );
        

  lsTextoActual           VARCHAR2(1000) := '';
  
  lnOidPais               SEG_PAIS.OID_PAIS%TYPE;
  lnOidMarca              SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal              SEG_CANAL.OID_CANA%TYPE;
  lnOidPeriodo            CRA_PERIO.OID_PERI%TYPE;
  
  lsCRutEmisorRut         VARCHAR2(20);    
  lsCRutEmisorDv          VARCHAR2(20);
  lsCRutEnviaRut          VARCHAR2(20);
  lsCRutEnviaDv           VARCHAR2(20);
  lsCRutReceptorRut       VARCHAR2(20);
  lscRutReceptorDv        VARCHAR2(20);
  lsFchResol              VARCHAR2(20);    
  lsNroResol              VARCHAR2(20);                 
  lsFechaSet              VARCHAR2(20);
  lsTotalGralBoleta       VARCHAR2(20);
  lsTotalGralBruto        VARCHAR2(20);
  lsTotalGralRetencion    VARCHAR2(20);
  lsTotalGralLiquido      VARCHAR2(20);
  lsTmstFirmaEnv          VARCHAR2(20);
  
  lcXML                   CLOB:=EMPTY_CLOB();
  l_formatoNumerico           VARCHAR2(100) := '9999999G990D00';

  lnCodigoHomo            ZON_VALOR_ESTRU_GEOPO.COD_HOMO%TYPE;
  lnFolioActual           IMP_FOLIO.VAL_ACTU%TYPE;
  lnFolioInicio           IMP_FOLIO.VAL_INIC%TYPE;
  lnFolioFin              IMP_FOLIO.VAL_FIN%TYPE;
  lsFolio                 VARCHAR2(20);
  lsCodigoFirmar          VARCHAR2(1000);
  lsCodigoFirmarMD5       VARCHAR2(50);  
  lsCodigoBarra           VARCHAR2(50);  

BEGIN

  --Recuperamos el oid Pais,Marca,Canal,Periodo
  lnOidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T');
  lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');
  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                             lnOidMarca,
                                                             lnOidCanal);

  --Obtenemos el Folio Actual
  SELECT VAL_ACTU, VAL_INIC, VAL_FIN
    INTO lnFolioActual, lnFolioInicio, lnFolioFin
    FROM IMP_FOLIO;
  
  IF(lnFolioActual IS NOT NULL) THEN
    IF(lnFolioActual = lnFolioFin) THEN
      RAISE_APPLICATION_ERROR(-20123, 'EL FOLIO ACTUAL HA IGUALADO AL FOLIO FIN');
    END IF;  
  END IF;    
  
  -- Elimina todos las filas de la Tabla IMP_TMP_BOLET_HONOR
  EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_TMP_BOLET_HONOR';
    
  --CARATULA
  SELECT '96524830' as CRutEmisorRut,
         '7' as CRutEmisorDv,
         '96524830' as CRutEnviaRut,
         '7' as CRutEnviaDv,
         '60803000' as CRutReceptorRut,
         'K' as cRutReceptorDv,
         '20041201' as FchResol,
         '999999' as NroResol,        
         MAX(TO_CHAR(a.fec_emis, 'YYYYMMDDHH24MM')) as FechaSet, 
         count(a.val_nume_iden_fisc) as TotalGralBoleta,
         TRIM(TO_CHAR(sum(a.imp_desc_tota_loca),'999999990')) as TotalGralBruto,
         TRIM(TO_CHAR(sum(a.val_impo_rete_desc),'999999990')) as TotalGralRetencion,
         TRIM(TO_CHAR(sum(a.imp_desc_tota_loca) - sum(a.val_impo_rete_desc),'999999990')) as TotalGralLiquido,
         MAX(TO_CHAR(a.fec_emis, 'YYYYMMDDHH24MMSS')) as TmstFirmaEnv
    INTO lsCRutEmisorRut,    
         lsCRutEmisorDv,
         lsCRutEnviaRut,
         lsCRutEnviaDv,
         lsCRutReceptorRut,
         lscRutReceptorDv,
         lsFchResol,
         lsNroResol,
         lsFechaSet,
         lsTotalGralBoleta,
         lsTotalGralBruto,
         lsTotalGralRetencion,
         lsTotalGralLiquido,
         lsTmstFirmaEnv
    FROM fac_docum_conta_cabec a, 
         ped_solic_cabec b, 
         mae_clien c
   WHERE a.PERD_OID_PERI = lnOidPeriodo
     AND a.FEC_FACT = to_date(psfechaFacturacion, 'dd/mm/yyyy') 
     AND a.SOCA_OID_SOLI_CABE = b.OID_SOLI_CABE  
     AND a.TIDO_OID_TIPO_DOCU = 1 
     --AND a.VAL_TOTA_PAGA_LOCA <> 0 
     AND b.CLIE_OID_CLIE = c.OID_CLIE 
     --AND a.IMP_DESC_TOTA_LOCA > 0;
     AND a.VAL_IMPO_RETE_DESC>0
     AND EXISTS
         (
          SELECT 1 
            FROM FAC_DOCUM_CONTA_LINEA xx, 
                 PED_SOLIC_POSIC yy, 
                 PRE_OFERT_DETAL zz
           WHERE xx.dcca_oid_cabe = a.oid_cabe 
             AND xx.num_unid_aten > 0
             AND xx.sopo_oid_soli_posi = yy.oid_soli_posi
             AND yy.ofde_oid_deta_ofer = zz.oid_deta_ofer
             AND zz.tofe_oid_tipo_ofer not in (select tofe_oid_tipo_ofer from fac_tipo_ofert_exclu)
         );
  
  INSERT INTO IMP_TMP_BOLET_HONOR (XML_CONS)
   VALUES(EMPTY_CLOB())
  RETURNING XML_CONS INTO lcXML;
                
  lsTextoActual := '<EnvioBTE><SetBoletas><Caratula><CRutEmisor><CRutEmisorRut>' || lsCRutEmisorRut || '</CRutEmisorRut>';
  DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);
    
  lsTextoActual := '<CRutEmisorDv>' || lsCRutEmisorDv || '</CRutEmisorDv></CRutEmisor>';  
  DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  

  lsTextoActual := '<CRutEnvia><CRutEnviaRut>' || lsCRutEnviaRut || '</CRutEnviaRut>';  
  DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  
            
  lsTextoActual := '<CRutEnviaDv>' || lsCRutEnviaDv || '</CRutEnviaDv></CRutEnvia>';  
  DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  

  lsTextoActual := '<CRutReceptor><CRutReceptorRut>' || lsCRutReceptorRut || '</CRutReceptorRut>';  
  DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  

  lsTextoActual := '<CRutReceptorDv>' || lscRutReceptorDv || '</CRutReceptorDv></CRutReceptor>';  
  DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  

  lsTextoActual := '<FchResol>' || lsFchResol || '</FchResol>';  
  DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  

  lsTextoActual := '<NroResol>' || lsNroResol || '</NroResol>';  
  DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  

  lsTextoActual := '<FechaSet>' || lsFechaSet || '</FechaSet>';  
  DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  

  lsTextoActual := '<TotalGral><TotalGralBoletas>' || lsTotalGralBoleta || '</TotalGralBoletas>';  
  DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  

  lsTextoActual := '<TotalGralBruto>' || lsTotalGralBruto || '</TotalGralBruto>';  
  DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  

  lsTextoActual := '<TotalGralRetencion>' || lsTotalGralRetencion || '</TotalGralRetencion>';  
  DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  

  lsTextoActual := '<TotalGralLiquido>' || lsTotalGralLiquido || '</TotalGralLiquido></TotalGral>';  
  DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  

  lsTextoActual := '<TmstFirmaEnv>' || lsTmstFirmaEnv || '</TmstFirmaEnv></Caratula>';  
  DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  

  --RECORREMOS LAS BOLETAS
  OPEN c_Boletas(lnOidPeriodo);
    LOOP
    FETCH c_Boletas BULK COLLECT INTO r_boleta LIMIT W_FILAS; 
      IF  r_boleta.COUNT > 0 THEN
        FOR i IN r_boleta.FIRST..r_boleta.LAST
        LOOP
        
          --Obtenemos el dato de la Comuna
          BEGIN
            SELECT C.COD_HOMO
              INTO lnCodigoHomo
              FROM ZON_VALOR_ESTRU_GEOPO A, 
                   MAE_CLIEN_DIREC B,   
                   ZON_VALOR_ESTRU_GEOPO C
             WHERE b.clie_oid_clie = r_boleta(i).lnOidCliente
               AND a.orde_1 = TRIM(substr(b.cod_unid_geog, 1, 6))
               AND a.orde_2 = TRIM(substr(b.cod_unid_geog, 7, 6))
               AND a.orde_3 = TRIM(substr(b.cod_unid_geog, 13,6))
               AND a.orde_4 IS NULL  
               AND c.ORDE_1 = A.ORDE_1
               AND c.ORDE_2 = A.ORDE_2
               AND c.ORDE_3 = SUBSTR(a.ORDE_3,1,4) || '00' 
               AND b.ind_dire_ppal = 1
               AND b.ind_elim = 0
               AND ROWNUM = 1;
          EXCEPTION
            WHEN NO_DATA_FOUND THEN
              lnCodigoHomo := NULL;
          END;     
   
          --Obtenemos el Folio Actual
          IF(lnFolioActual IS NULL) THEN
            lnFolioActual := lnFolioInicio;
          ELSE  
            lnFolioActual := lnFolioActual + 1;
          END IF;  
   
          --Obtenemos el texto a Firmar
          lsCodigoFirmar := '<BOLETA>' ||
                          '<RUT_CTR>' || lsCRutEmisorRut || '</RUT_CTR>' || 
                          '<FECHA_ATN>' || psFechaFacturacion || '</FECHA_ATN>' ||
                          '<FECHA_EMI>00-00-00:00:</FECHA_EMI>' ||
                          '<RECEPTOR>' || 
                          '<NOMBRES>' || r_boleta(i).lsNombreReceptor || '</NOMBRES>' ||
                          '<RUT>' || r_boleta(i).lsEReceptorRutReceptorRut || '</RUT>' ||
                          '<DV>' || r_boleta(i).lsEReceptorRutReceptorDv || '</DV>' ||
                          '<DOMICILIO>' || r_boleta(i).lsDomReceptorParte1 || '</DOMICILIO>' ||
                          '</RECEPTOR>' ||
                          '<VALORES_BOLETA>' ||
                          '<TOTAL>' || r_boleta(i).lsTotalBolMontoBrutoServicio || '</TOTAL>' ||
                          '<RETENCION>' || r_boleta(i).lsTotalBolRetencion || '</RETENCION>' ||
                          '<LIQUIDO>' || r_boleta(i).lsTotalBolMontoLiquidoServicio || '</LIQUIDO>' ||                          
                          '</VALORES_BOLETA>' ||                          
                          '</BOLETA>';
         
         --Aplicamos la funcion hash de MD5                 
         lsCodigoFirmarMD5 := RAWTOHEX(UTL_RAW.CAST_TO_RAW(
                                DBMS_OBFUSCATION_TOOLKIT.md5 (input_string => lsCodigoFirmar)));                          
                                
         lsFolio := SUBSTR(TRIM(TO_CHAR(lnFolioActual,'0000000000')),6);
                          
         --Obtenemos el codigo de Barra                 
         lsCodigoBarra := SUBSTR(lsCRutEmisorRut || lsFolio || lsCodigoFirmarMD5, 1, 20);                 
        
        
          lsTextoActual := '<BoletaBTE><Documento><Encabezado><IdDoc><TipoDTE>' || r_boleta(i).lsTipoDTE || '</TipoDTE>';
          DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);
          
         lsTextoActual := '<Folio>' || lnFolioActual || '</Folio>';  
         DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  

         lsTextoActual := '<FechaEmision>' || r_boleta(i).lsFechaEmision || '</FechaEmision>';  
         DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  

         lsTextoActual := '<FechaDeLaBoleta>' || r_boleta(i).lsFechaDeLaBoleta || '</FechaDeLaBoleta></IdDoc>';  
         DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  

         lsTextoActual := '<EReceptor><NombreReceptor>' || r_boleta(i).lsNombreReceptor || '</NombreReceptor>';  
         DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  

         lsTextoActual := '<DomicilioReceptor><DomReceptorParte1>' || r_boleta(i).lsDomReceptorParte1 || '</DomReceptorParte1>';  
         DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  

         lsTextoActual := '<DomReceptorComuna>' || lnCodigoHomo || '</DomReceptorComuna></DomicilioReceptor>';  
         DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  

         lsTextoActual := '<EReceptorRutReceptor><EReceptorRutReceptorRut>' || r_boleta(i).lsEReceptorRutReceptorRut || '</EReceptorRutReceptorRut>';  
         DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  

         lsTextoActual := '<EReceptorRutReceptorDv>' || r_boleta(i).lsEReceptorRutReceptorDv || '</EReceptorRutReceptorDv></EReceptorRutReceptor></EReceptor>';  
         DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  

         lsTextoActual := '<Emisor><EERutEmisor><EERutEmisorRut>' || r_boleta(i).lsEERutEmisorRut || '</EERutEmisorRut>';  
         DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  

         lsTextoActual := '<EERutEmisorDv>' || r_boleta(i).lsEERutEmisorDv || '</EERutEmisorDv></EERutEmisor>';  
         DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  

         lsTextoActual := '<RznSocEmisor>' || r_boleta(i).lsRznSocEmisor || '</RznSocEmisor>';  
         DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  

         lsTextoActual := '<DomicilioEmisor><DomEmisorParte1>' || r_boleta(i).lsDomEmisorParte1 || '</DomEmisorParte1>';  
         DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  

         lsTextoActual := '<DomEmisorComuna>' || r_boleta(i).lsDomEmisorComuna || '</DomEmisorComuna></DomicilioEmisor>';  
         DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  

         lsTextoActual := '<GlosaActividadesEconomicas>' || r_boleta(i).lsGlosaActividadEconomica || '</GlosaActividadesEconomicas>';  
         DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  

         lsTextoActual := '<TextoLibreEmisor>' || r_boleta(i).lsTextoLibreEmisor || '</TextoLibreEmisor></Emisor>';  
         DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  

         lsTextoActual := '<TotalBol><TotalBolMontoBrutoServicio>' || r_boleta(i).lsTotalBolMontoBrutoServicio || '</TotalBolMontoBrutoServicio>';  
         DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  

         lsTextoActual := '<TotalBolRetencion>' || r_boleta(i).lsTotalBolRetencion || '</TotalBolRetencion>';  
         DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  

         lsTextoActual := '<TotalBolNumeroDetails>' || r_boleta(i).lsTotalBolNumeroDetails || '</TotalBolNumeroDetails>';  
         DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  

         lsTextoActual := '<TotalBolMontoLiquidoServicio>' || r_boleta(i).lsTotalBolMontoLiquidoServicio || '</TotalBolMontoLiquidoServicio>';  
         DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  

         lsTextoActual := '<TotalBolTasa>' || r_boleta(i).lsTotalBolTasa || '</TotalBolTasa></TotalBol>';  
         DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  

         lsTextoActual := '<TextosLibres><TextoLibre1>' || r_boleta(i).lsTextoLibre1 || '</TextoLibre1>';  
         DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  

         lsTextoActual := '<TextoLibre2>' || r_boleta(i).lsTextoLibre2 || '</TextoLibre2>';  
         DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  

         lsTextoActual := '<TextoLibre3>' || r_boleta(i).lsTextoLibre3 || '</TextoLibre3></TextosLibres></Encabezado>';  
         DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  

         lsTextoActual := '<Detalle><NroLinDet>' || r_boleta(i).lsNroLinDet || '</NroLinDet>';  
         DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  

         lsTextoActual := '<DetalleServicio>' || r_boleta(i).lsDetalleServicio || '</DetalleServicio>';  
         DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  

         lsTextoActual := '<MontoBrutoDetalleServicio>' || r_boleta(i).lsMontoBrutoDetalleServicio || '</MontoBrutoDetalleServicio></Detalle>';  
         DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  

         lsTextoActual := '<CodigoBarras>' || lsCodigoBarra || '</CodigoBarras>';  
         DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  

         lsTextoActual := '</Documento><Signature /></BoletaBTE>';  
         DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  

         update fac_docum_conta_cabec x set x.val_foli=lsFolio
         where x.oid_cabe=r_boleta(i).lnOidCabe;

        END LOOP;

       END IF;
    EXIT WHEN c_Boletas%NOTFOUND;
  END LOOP;
  CLOSE c_Boletas;

  lsTextoActual := '</SetBoletas><Signature /></EnvioBTE>';  
  DBMS_LOB.writeappend(lcXML, LENGTH(lsTextoActual), lsTextoActual);  

  --Actualizamos el Folio
  UPDATE IMP_FOLIO
     SET VAL_ACTU = lnFolioActual;

  --Generamos el Archivo XML
  IMP_PR_GENER_ARCHI_BOLET(psCodigoPais, psNombreArchivo, psDirectorio);
  
 EXCEPTION
  WHEN OTHERS THEN
     RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_PROCE_BOLET_HONOR: '||substr(sqlerrm,1,250));
END IMP_PR_PROCE_BOLET_HONOR;

/**************************************************************************
Descripcion         : Genera el archivo de de Boletas Honorarios
Fecha Creación      : 30/09/2003
Autor               : Sergio Apaza
***************************************************************************/
PROCEDURE IMP_PR_GENER_ARCHI_BOLET(psCodigoPais    VARCHAR2,
                                   psNombreArchivo VARCHAR2,
                                   psDirectorio    VARCHAR2)
IS

  l_output         UTL_FILE.file_type;
  l_amt            NUMBER DEFAULT 4000;
  l_offset         NUMBER DEFAULT 1;
  position         INTEGER := 1;
  l_length         NUMBER := 0;
  x                VARCHAR2(32000);
  t_Clob           CLOB;
  
  -- Variable a contener el mensaje de la excepcion a lanzar
  l_mensajeError VARCHAR2(500);
  
  l_inicioArchivo VARCHAR2(100);
  l_finArchivo    VARCHAR2(100);
  l_contador      NUMBER := 0;
  l_total         NUMBER := 0;
  
  CURSOR c_documentos IS
  SELECT XML_CONS
  FROM IMP_TMP_BOLET_HONOR;

BEGIN

  --Abrimos el archivo XML
  l_output := UTL_FILE.fopen (psDirectorio, psNombreArchivo || '.xml', 'wb', 32760);
  
  -- Escribimos la cabecera y el tag inicial de la raiz del XML
  UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw('<?xml version="1.0" encoding="iso-8859-1"?>'), TRUE);

  -- Iteramos sobre el cursor
  OPEN c_documentos;
  LOOP
      FETCH c_documentos INTO t_clob;
      EXIT WHEN c_documentos%NOTFOUND;

      l_length := DBMS_LOB.GETLENGTH(T_CLOB);
      position := 1;
      l_offset := 1;
      l_amt := 4000;

      -- Escribimos los bloques en el archivo
      WHILE (l_offset < l_length) LOOP
          IF (l_amt > (l_length - l_offset)) THEN l_amt := l_length - l_offset + 1; END IF;
          dbms_lob.read (t_clob, l_amt, l_offset, x);
          UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(x), TRUE);
          l_offset := l_offset + l_amt;
          position := position + 4000;
          x := NULL;
      END LOOP;

      l_contador := l_contador + 1;

  END LOOP;

  -- Cerramos el cursor
  CLOSE c_documentos;
  
  -- Cerramos el archivo
  UTL_FILE.fclose (l_output);

EXCEPTION
  WHEN UTL_FILE.INTERNAL_ERROR THEN
      l_mensajeError:='ERROR INTERNO DEL MANEJADOR DE ARCHIVOS';
      RAISE_APPLICATION_ERROR(-20123, l_mensajeError);
  WHEN UTL_FILE.INVALID_FILEHANDLE THEN
      l_mensajeError:='EL ARCHIVO NO ESTA ABIERTO O NO ES VALIDO';
      RAISE_APPLICATION_ERROR(-20123, l_mensajeError);
  WHEN UTL_FILE.INVALID_MODE THEN
      l_mensajeError:='MODO INVALIDO AL ABRIR EL ARCHIVO';
      RAISE_APPLICATION_ERROR(-20123, l_mensajeError);
  WHEN UTL_FILE.WRITE_ERROR THEN
         l_mensajeError:='ERROR AL ESCRIBIR EN EL ARCHIVO O NO HAY ESPACIO EN DISCO';
         RAISE_APPLICATION_ERROR(-20123, l_mensajeError);
  WHEN UTL_FILE.INVALID_OPERATION THEN
      l_mensajeError:='MODO INVALIDO AL ABRIR EL ARCHIVO';
      RAISE_APPLICATION_ERROR(-20123, l_mensajeError);
  WHEN UTL_FILE.INVALID_PATH THEN
      l_mensajeError:='ERROR EN LA RUTA DEL ARCHIVO, ARCHIVO NO ES ACCESIBLE';
      RAISE_APPLICATION_ERROR(-20123, l_mensajeError);
  WHEN OTHERS THEN
       RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_GENER_ARCHI_BOLET: '||substr(SQLERRM,1,250));

END IMP_PR_GENER_ARCHI_BOLET;

/**************************************************************************
Descripcion         : NTERFAZ PARA SOFTWARE DE ORDENAMIENTO E IMPRESIÓN EN XEROX (PERU)
Fecha Creación      : 21/10/2013
Autor               : Ivan Tocto
***************************************************************************/
PROCEDURE IMP_PR_ORDEN_IMPRE_XEROX_ALTER
(
  pscodigopais       VARCHAR2,
  pscodigosistema    VARCHAR2,
  pscodigointerfaz   VARCHAR2,
  psnombrearchivo    VARCHAR2,
  pscodigoperiodo    VARCHAR2,
  psfechafacturacion VARCHAR2
) IS
  CURSOR c_interfaz(vnoidperiodo NUMBER) IS
    select * from (SELECT
           TO_CHAR(g.fec_fact, 'DD/MM/YYYY') fec_fact,
           b.val_nume_soli,
           f.cod_zona,
           c.cod_clie,
           a. num_secu_fact_diar,
           decode(instr(g.val_seri_docu_lega, 'B'), 0, 'B', '') ||
           g.val_seri_docu_lega serie,
           g.num_docu_cont_inte,
           c.val_ape1 || ' ' || c.val_ape2 || ' ' || c.val_nom1 || ' ' ||
           c.val_nom2 nombre,
            a.val_secu_ruta_terr
      FROM ped_solic_cabec_secue a,
           ped_solic_cabec       b,
           mae_clien             c,
           mae_clien_datos_adici d,
           mae_clien_ident       e,
           zon_zona              f,
           fac_docum_conta_cabec g,
           fac_tipo_docum        ftd
     WHERE a.soca_oid_soli_cabe = b.oid_soli_cabe
       AND b.fec_fact = to_date(psfechafacturacion, 'DD/MM/YYYY')
       AND b.perd_oid_peri = vnoidperiodo
       AND b.clie_oid_clie = c.oid_clie
       AND c.oid_clie = d.clie_oid_clie
       AND c.oid_clie = e.clie_oid_clie
       AND d.ind_impr_docu = 1
       AND b.zzon_oid_zona = f.oid_zona
       AND g.tido_oid_tipo_docu = ftd.oid_tipo_docu
       AND ftd.cod_tipo_docu ='011'
       AND g.soca_oid_soli_cabe = b.oid_soli_cabe
       AND EXISTS
     (SELECT NULL
              FROM fac_docum_conta_linea det,
                   ped_solic_posic       psp,
                   pre_ofert_detal       pod
             WHERE det.sopo_oid_soli_posi = psp.oid_soli_posi
               AND psp.ofde_oid_deta_ofer = pod.oid_deta_ofer(+)
               AND det.num_unid_aten > 0
               AND det.dcca_oid_cabe = g.oid_cabe
               AND NOT EXISTS
             (SELECT NULL
                      FROM fac_tipo_ofert_exclu toe
                     WHERE toe.tofe_oid_tipo_ofer = pod.tofe_oid_tipo_ofer))
       AND e.val_iden_docu_prin = 1 
              UNION ALL
    SELECT 
           TO_CHAR(g.fec_fact, 'DD/MM/YYYY') fec_fact,
           b.val_nume_soli,
           f.cod_zona,
           c.cod_clie,
           a. num_secu_fact_diar,
           decode(instr(g.val_seri_docu_lega, 'B'), 0, 'B', '') ||
           g.val_seri_docu_lega serie,
           g.num_docu_cont_inte,
           c.val_ape1 || ' ' || c.val_ape2 || ' ' || c.val_nom1 || ' ' ||
           c.val_nom2 nombre ,
            a.val_secu_ruta_terr
      FROM ped_solic_cabec_secue a,
           ped_solic_cabec       b,
           mae_clien             c,
           mae_clien_datos_adici d,
           mae_clien_ident       e,
           zon_zona              f,
           fac_docum_conta_cabec g,
           fac_tipo_docum        ftd
     WHERE a.soca_oid_soli_cabe = b.oid_soli_cabe
       AND b.fec_fact = to_date(psfechafacturacion, 'DD/MM/YYYY')
       AND b.perd_oid_peri = vnoidperiodo
       AND b.clie_oid_clie = c.oid_clie
       AND c.oid_clie = d.clie_oid_clie
       AND c.oid_clie = e.clie_oid_clie
       AND d.ind_impr_docu = 1
       AND b.zzon_oid_zona = f.oid_zona
       AND g.tido_oid_tipo_docu = ftd.oid_tipo_docu
       AND ftd.cod_tipo_docu = '012'
       AND g.soca_oid_soli_cabe = b.oid_soli_cabe
       AND f.oid_zona not in (select oid_zona from FAC_DESHA_ZONAS_ENVIO_BOLET )
       AND EXISTS
     (SELECT NULL
              FROM fac_docum_conta_linea det,
                   ped_solic_posic       psp,
                   pre_ofert_detal       pod
             WHERE det.sopo_oid_soli_posi = psp.oid_soli_posi
               AND psp.ofde_oid_deta_ofer = pod.oid_deta_ofer(+)
               AND det.num_unid_aten > 0
               AND det.dcca_oid_cabe = g.oid_cabe
               AND NOT EXISTS
             (SELECT NULL
                      FROM fac_tipo_ofert_exclu toe
                     WHERE toe.tofe_oid_tipo_ofer = pod.tofe_oid_tipo_ofer))
       AND e.val_iden_docu_prin = 1)tempo
       ORDER BY tempo.serie,
              tempo.val_secu_ruta_terr,
         tempo.num_docu_cont_inte; -- ajuste

  TYPE interfazrec IS RECORD(
    fechafacturacion VARCHAR2(10),
    numerosolicitud ped_solic_cabec.val_nume_soli%TYPE,
    codigozona      zon_zona.cod_zona%TYPE,
    codigocliente   mae_clien.cod_clie%TYPE,
    numerosecuencia ped_solic_cabec_secue.num_secu_fact_diar%TYPE,
    serie           VARCHAR2(100),
    numerodocumento fac_docum_conta_cabec.num_docu_cont_inte%TYPE,
    nombrecliente   VARCHAR2(400),
    val_secu_ruta_terr ped_solic_cabec_secue.val_secu_ruta_terr%TYPE);
  TYPE interfazrectab IS TABLE OF interfazrec;
  interfazrecord interfazrectab;
  /* Variables usadas para la generacion del archivo de texto */
  lsdirtempo bas_inter.dir_temp%TYPE;
  w_filas    NUMBER := 1000;
  v_handle   utl_file.file_type;

  lslinea VARCHAR2(1000) := '';

  lsnombrearchivo VARCHAR2(50);
  lnoidperiodo    cra_perio.oid_peri%TYPE;
  lbabrirutlfile  BOOLEAN;
BEGIN

  lnoidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoperiodo);
  /* Procedimiento inicial para generar interfaz */
  lbabrirutlfile := TRUE;

  /* Generar Archivo de Texto (Detalle) */
  OPEN c_interfaz(lnoidperiodo);
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
        lslinea := interfazrecord(x).fechafacturacion || ';';
        lslinea := lslinea || interfazrecord(x).numerosolicitud || ';';
        lslinea := lslinea || interfazrecord(x).codigozona || ';';
        lslinea := lslinea || interfazrecord(x).codigocliente || ';';
        lslinea := lslinea || interfazrecord(x).numerosecuencia || ';';
        lslinea := lslinea || interfazrecord(x).serie || ';';
        lslinea := lslinea || interfazrecord(x).numerodocumento || ';';
        lslinea := lslinea || interfazrecord(x).nombrecliente;
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
  /* Generando Archivo de Texto (Detalle) */

  RETURN;
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 1000);
    raise_application_error(-20123,
                            'ERROR IMP_PR_ORDEN_IMPRE_XEROX_ALTER: ' ||
                            ls_sqlerrm);
END IMP_PR_ORDEN_IMPRE_XEROX_ALTER;

/**************************************************************************************
Descripcion           : Genera Interfaz de Recepcion de cabecera de Notas de Credito Retail
Fecha Creacion    : 12/05/2014
Autor                   : Sebastian Guerra
**************************************************************************************/
PROCEDURE  INT_PR_RECEP_NOCRE_CABEC_DOCUM
(
  pscodigopais          VARCHAR2,
  pscodigosistema    VARCHAR2,
  pscodigointerfaz    VARCHAR2,
  psnombrearchivo   VARCHAR2,
  psusuario               VARCHAR2
) IS
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
  posiccampo    bas_estru_archi.pos_camp%TYPE,
  longcampo     bas_estru_archi.lon_camp%TYPE,
  cantdecimal   bas_estru_archi.can_deci%TYPE,
  idcampo         bas_estru_archi.ide_camp%TYPE,
  sigla               bas_tipo_dato.sig_tdat%TYPE);

TYPE interfazcabtab IS TABLE OF interfazcab;
interfazrecord interfazcabtab;

TYPE t_num_seri IS TABLE OF imp_tmp_felec_nc_retail_cabec.num_seri%TYPE;
TYPE t_num_docu_inte IS TABLE OF varchar2(10);
TYPE t_fec_fact IS TABLE OF varchar2(10);
TYPE t_txt_cabe1 IS TABLE OF imp_tmp_felec_nc_retail_cabec.txt_cabe1%TYPE;
TYPE t_txt_cabe2 IS TABLE OF imp_tmp_felec_nc_retail_cabec.txt_cabe2%TYPE;

v_num_seri t_num_seri := t_num_seri();
v_num_docu_inte t_num_docu_inte := t_num_docu_inte();
v_fec_fact t_fec_fact := t_fec_fact();
v_txt_cabe1 t_txt_cabe1 := t_txt_cabe1();
v_txt_cabe2 t_txt_cabe2 := t_txt_cabe2();

/* Variables usadas para la generacion del archivo de texto */
lsdirtempo bas_inter.dir_temp%TYPE;
v_handle   utl_file.file_type;

lslinea                  VARCHAR2(10000);
lsnombrearchivo  VARCHAR2(50);

/* Variables de parametros */
i        BINARY_INTEGER := 0;
posicion NUMBER := 0;
longitud NUMBER := 0;
    
BEGIN
/* Procedimiento inicial para generar interfaz */
gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                                         pscodigosistema,
                                                                         pscodigointerfaz,
                                                                         psnombrearchivo,
                                                                         'TXT',
                                                                         lsdirtempo,
                                                                         lsnombrearchivo,
                                                                         v_handle,
                                                                         10000);

IF utl_file.is_open(v_handle) THEN
  LOOP
    BEGIN
      utl_file.get_line(v_handle, lslinea);
      i      := i + 1;
      IF lslinea IS NULL THEN
        EXIT;
      END IF;

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
              v_num_seri.extend;
              v_num_seri(i) := TRIM(substr(lslinea, 1, longitud));
            ELSIF (posicion = 2) THEN
              v_num_docu_inte.extend;
              v_num_docu_inte(i) := TRIM(substr(lslinea, 6, longitud));
            ELSIF (posicion = 3) THEN
              v_fec_fact.extend;
              v_fec_fact(i) := TRIM(substr(lslinea, 42, longitud));
            ELSIF (posicion = 4) THEN
              v_txt_cabe1.extend;
              v_txt_cabe1(i) := TRIM(substr(lslinea, 1, longitud));
            ELSIF (posicion = 5) THEN
              v_txt_cabe2.extend;
              v_txt_cabe2(i) := TRIM(substr(lslinea, 3822, longitud));
            END IF;

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
FOR i IN 1 .. v_num_seri.count
LOOP
  IF v_num_seri(i) IS NOT NULL THEN
    BEGIN
      INSERT INTO imp_tmp_felec_nc_retail_cabec(
        num_seri,
        num_docu_inte,
        fec_fact,
        txt_cabe1,
        txt_cabe2,
        txt_nom_arch
      ) VALUES (
         v_num_seri(i),
         to_number(v_num_docu_inte(i)),
         to_date((substr(v_fec_fact(i),9,2)||'/'||substr(v_fec_fact(i),6,2)||'/'||substr(v_fec_fact(i),1,4)),'dd/MM/yyyy'),
         v_txt_cabe1(i),
         v_txt_cabe2(i),
         psnombrearchivo
      );
    EXCEPTION
      WHEN dup_val_on_index THEN
        NULL;
    END;
  END IF;
END LOOP;
RETURN;

EXCEPTION
WHEN OTHERS THEN
  ln_sqlcode := SQLCODE;
  ls_sqlerrm := SUBSTR(SQLERRM, 1, 1000);
  raise_application_error(-20123, 'ERROR  INT_PR_RECEP_NOCRE_CABEC_DOCUM: ' || psnombrearchivo || '**' || ls_sqlerrm);

END  INT_PR_RECEP_NOCRE_CABEC_DOCUM;

/**************************************************************************************
Descripcion           : Genera Interfaz de Recepcion del detalle de Notas de Credito Retail
Fecha Creacion    : 12/05/2014
Autor                   : Sebastian Guerra
**************************************************************************************/
PROCEDURE  INT_PR_RECEP_NOCRE_DETAL_DOCUM
(
  pscodigopais          VARCHAR2,
  pscodigosistema    VARCHAR2,
  pscodigointerfaz    VARCHAR2,
  psnombrearchivo   VARCHAR2,
  psusuario               VARCHAR2
) IS
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
  posiccampo    bas_estru_archi.pos_camp%TYPE,
  longcampo     bas_estru_archi.lon_camp%TYPE,
  cantdecimal   bas_estru_archi.can_deci%TYPE,
  idcampo         bas_estru_archi.ide_camp%TYPE,
  sigla               bas_tipo_dato.sig_tdat%TYPE);

TYPE interfazcabtab IS TABLE OF interfazcab;
interfazrecord interfazcabtab;

TYPE t_num_seri IS TABLE OF imp_tmp_felec_nc_retail_detal.num_seri%TYPE;
TYPE t_num_docu_inte IS TABLE OF imp_tmp_felec_nc_retail_detal.num_docu_inte%TYPE;
TYPE t_num_secu IS TABLE OF imp_tmp_felec_nc_retail_detal.num_secu%TYPE;
TYPE t_txt_deta1 IS TABLE OF imp_tmp_felec_nc_retail_detal.txt_deta1%TYPE;

v_num_seri t_num_seri := t_num_seri();
v_num_docu_inte t_num_docu_inte := t_num_docu_inte();
v_num_secu t_num_secu := t_num_secu();
v_txt_deta1 t_txt_deta1 := t_txt_deta1();

/* Variables usadas para la generacion del archivo de texto */
lsdirtempo bas_inter.dir_temp%TYPE;
v_handle   utl_file.file_type;

lslinea                  VARCHAR2(10000);
lsnombrearchivo  VARCHAR2(50);

/* Variables de parametros */
i        BINARY_INTEGER := 0;
posicion NUMBER := 0;
longitud NUMBER := 0;
    
BEGIN
/* Procedimiento inicial para generar interfaz */
gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                                         pscodigosistema,
                                                                         pscodigointerfaz,
                                                                         psnombrearchivo,
                                                                         'TXT',
                                                                         lsdirtempo,
                                                                         lsnombrearchivo,
                                                                         v_handle,
                                                                         10000);

IF utl_file.is_open(v_handle) THEN
  LOOP
    BEGIN
      utl_file.get_line(v_handle, lslinea);
      i      := i + 1;
      IF lslinea IS NULL THEN
        EXIT;
      END IF;

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
              v_num_seri.extend;
              v_num_seri(i) := TRIM(substr(lslinea, 1, longitud));
            ELSIF (posicion = 2) THEN
              v_num_docu_inte.extend;
              v_num_docu_inte(i) := TRIM(substr(lslinea, 6, longitud));
            ELSIF (posicion = 3) THEN
              v_num_secu.extend;
              v_num_secu(i) := TRIM(substr(lslinea, 19, longitud));
            ELSIF (posicion = 4) THEN
              v_txt_deta1.extend;
              v_txt_deta1(i) := TRIM(substr(lslinea, 1, longitud));
            END IF;

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
FOR i IN 1 .. v_num_seri.count
LOOP
  IF v_num_seri(i) IS NOT NULL THEN
    BEGIN
      INSERT INTO imp_tmp_felec_nc_retail_detal(
        num_seri,
        num_docu_inte,
        num_secu,
        txt_deta1,
        txt_nom_arch
      ) VALUES (
         v_num_seri(i),
         to_number(v_num_docu_inte(i)),
         to_number(v_num_secu(i)),
         v_txt_deta1(i),
         psnombrearchivo
      );
    EXCEPTION
      WHEN dup_val_on_index THEN
        NULL;
    END;
  END IF;
END LOOP;
RETURN;

EXCEPTION
WHEN OTHERS THEN
  ln_sqlcode := SQLCODE;
  ls_sqlerrm := SUBSTR(SQLERRM, 1, 1000);
  raise_application_error(-20123, 'ERROR INT_PR_RECEP_NOCRE_DETAL_DOCUM: ' || psnombrearchivo || '**' || ls_sqlerrm);

END  INT_PR_RECEP_NOCRE_DETAL_DOCUM;

/*****************************************************************************
  Descripcion         : Proceso que genera la informacion de Notas Credito Boletas Cabecera Retail
  Fecha Creación  : 23/05/2014
  Autor                  : Sebastian Guerra
*****************************************************************************/
PROCEDURE INT_PR_IMP_NOCRB_CABEC_RETAI(psCodigoPais        VARCHAR2,
                                      psCodigoSistema        VARCHAR2,
                                      psCodigoInterfaz       VARCHAR2,
                                      psFechaFacturacion   VARCHAR2,
                                      psNombreArchivo       VARCHAR2,
                                      psTipoDocumento      VARCHAR2,
                                      psSerieDocumento     VARCHAR2)
IS
 CURSOR c_documento(lnDiasDif NUMBER) IS
 SELECT   a.txt_cabe1 AS valorColumna1, a.txt_cabe2 AS valorColumna2
    FROM imp_tmp_felec_nc_retail_cabec a-- imp_tmp_felec_archi b
   WHERE --a.num_docu_inte = b.num_docu_elec
      (psSerieDocumento IS NULL OR a.num_seri = psSerieDocumento)
     AND a.ind_proc_fe = '0'
     AND substr(a.num_seri,0,1) ='B'
     AND TRUNC(a.fec_fact) >=TRUNC(SYSDATE) - lnDiasDif
   order by a.num_seri,a.num_docu_inte;

 TYPE documentorecord IS RECORD (
        valorColumna1 varchar2(4000),
        valorColumna2 varchar2(4000)
 );

 TYPE documentotype IS TABLE OF documentorecord;
 r_documento        documentotype;

 /* Variables usadas para la generacion del archivo de texto */
 lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
 v_handle            UTL_FILE.FILE_TYPE;

 lsLinea                  VARCHAR2(10000);
 lsNombreArchivo  VARCHAR2(50);
 lbAbrirUtlFile         BOOLEAN;
 lnDiasDif               NUMBER;

BEGIN
  begin
  select VAL_PARA INTO lnDiasDif
      from bas_param_pais
      where cod_pais= psCodigoPais
       and cod_sist='FAC'
       and cod_para='002';
    exception
       when others then
          lnDiasDif:=3;--default
    end;

    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;

    -- Abrimos el cursor
    OPEN c_documento(lnDiasDif);
    LOOP
        FETCH c_documento BULK COLLECT INTO r_documento LIMIT w_filas;

        /* Procedimiento inicial para generar interfaz */
        IF lbAbrirUtlFile THEN
          GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER_MAXCA(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                   psNombreArchivo, UTL_FILE_MAXI_CARA, lsDirTempo, lsNombreArchivo, V_HANDLE);
           lbAbrirUtlFile := FALSE;
        END IF;

        IF  r_documento.COUNT > 0 THEN
            FOR i IN r_documento.FIRST..r_documento.LAST LOOP
                lsLinea :=  r_documento(i).valorColumna1 ||';'||
                                 r_documento(i).valorColumna2;
                UTL_FILE.PUT_LINE (V_HANDLE, lslinea);
            END LOOP;
        END IF;

        EXIT WHEN c_documento%NOTFOUND;
    END LOOP;
    -- Cerramos el cursor
    CLOSE c_documento;

    IF NOT lbAbrirUtlFile THEN
          utl_file.fclose(V_HANDLE);
         /* Procedimiento final para generar interfaz */
          GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;
EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_IMP_NOCRB_CABEC_RETAI: '||ls_sqlerrm);
END INT_PR_IMP_NOCRB_CABEC_RETAI;

/*****************************************************************************
  Descripcion         : Proceso que genera la informacion de Notas Credito Boletas Detalle Retail
  Fecha Creación  : 23/05/2014
  Autor                  : Sebastian Guerra
*****************************************************************************/
PROCEDURE INT_PR_IMP_NOCRB_DETAL_RETAI(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      psFechaFacturacion  VARCHAR2,
                                      psNombreArchivo     VARCHAR2,
                                      psTipoDocumento     VARCHAR2,
                                      psSerieDocumento    VARCHAR2)
IS
 CURSOR c_documento(lnDiasDif NUMBER) IS
  select a.txt_deta1 as valorColumna1
    from imp_tmp_felec_nc_retail_detal a,
            imp_tmp_felec_nc_retail_cabec b--,
            --imp_tmp_felec_archi c
   where a.num_seri = b.num_seri
     and a.num_docu_inte = b.num_docu_inte
     --and a.num_docu_inte = c.num_docu_elec
     and (psSerieDocumento IS NULL OR a.num_seri = psSerieDocumento)
     and b.ind_proc_fe = '0'
     and substr(a.num_seri,0,1) ='B'
     and TRUNC(b.fec_fact) >=TRUNC(SYSDATE) - lnDiasDif
     order by a.num_seri,a.num_docu_inte,a.num_secu;

 TYPE documentorecord IS RECORD (
        valorColumna1 varchar2(4000)
 );

 TYPE documentotype IS TABLE OF documentorecord;
 r_documento        documentotype;

 /* Variables usadas para la generacion del archivo de texto */
 lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
 v_handle            UTL_FILE.FILE_TYPE;

 lsLinea             VARCHAR2(10000);
 lsNombreArchivo     VARCHAR2(50);

 lbAbrirUtlFile      BOOLEAN;
 lnDiasDif           number;

BEGIN
   begin
  select VAL_PARA INTO lnDiasDif
      from bas_param_pais
      where cod_pais= psCodigoPais
       and cod_sist='FAC'
       and cod_para='002';
    exception
       when others then
          lnDiasDif:=3;--default
    end;

    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;

    -- Abrimos el cursor
    OPEN c_documento(lnDiasDif);
    LOOP
        FETCH c_documento BULK COLLECT INTO r_documento LIMIT w_filas;

        /* Procedimiento inicial para generar interfaz */
        IF lbAbrirUtlFile THEN
          GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER_MAXCA(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                   psNombreArchivo, UTL_FILE_MAXI_CARA, lsDirTempo, lsNombreArchivo, V_HANDLE);
           lbAbrirUtlFile := FALSE;
        END IF;

        IF  r_documento.COUNT > 0 THEN
            FOR i IN r_documento.FIRST..r_documento.LAST LOOP
                lsLinea :=  r_documento(i).valorColumna1 ;
                UTL_FILE.PUT_LINE (V_HANDLE, lslinea);
            END LOOP;
        END IF;

        EXIT WHEN c_documento%NOTFOUND;
    END LOOP;
    -- Cerramos el cursor
    CLOSE c_documento;

    IF NOT lbAbrirUtlFile THEN
          utl_file.fclose(V_HANDLE);
         /* Procedimiento final para generar interfaz */
          GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;
EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_IMP_NOCRB_DETAL_RETAI: '||ls_sqlerrm);
END INT_PR_IMP_NOCRB_DETAL_RETAI;

/*****************************************************************************
  Descripcion         : Proceso que genera la informacion de Notas Credito Boletas Cabecera Retail
  Fecha Creación  : 23/05/2014
  Autor                  : Sebastian Guerra
*****************************************************************************/
PROCEDURE INT_PR_IMP_NOCRF_CABEC_RETAI(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      psFechaFacturacion  VARCHAR2,
                                      psNombreArchivo     VARCHAR2,
                                      psTipoDocumento     VARCHAR2,
                                      psSerieDocumento    VARCHAR2)
IS
 CURSOR c_documento(lnDiasDif NUMBER) IS
 SELECT   a.txt_cabe1 AS valorColumna1, a.txt_cabe2 AS valorColumna2
    FROM imp_tmp_felec_nc_retail_cabec a--, imp_tmp_felec_archi b
   WHERE --a.num_docu_inte = b.num_docu_elec
      (psSerieDocumento IS NULL OR a.num_seri = psSerieDocumento)
     AND a.ind_proc_fe = '0'
     AND substr(a.num_seri,0,1) ='F'
     AND TRUNC(a.fec_fact) >=TRUNC(SYSDATE) - lnDiasDif
  order by a.num_seri,a.num_docu_inte;

TYPE documentorecord IS RECORD (
        valorColumna1 varchar2(4000),
        valorColumna2 varchar2(4000)
 );

 TYPE documentotype IS TABLE OF documentorecord;
 r_documento        documentotype;

 /* Variables usadas para la generacion del archivo de texto */
 lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
 v_handle            UTL_FILE.FILE_TYPE;

 lsLinea                     VARCHAR2(10000);
 lsNombreArchivo     VARCHAR2(50);

 lbAbrirUtlFile      BOOLEAN;
 lnDiasDif           number;

BEGIN
   begin
  select VAL_PARA INTO lnDiasDif
      from bas_param_pais
      where cod_pais= psCodigoPais
       and cod_sist='FAC'
       and cod_para='002';
    exception
       when others then
          lnDiasDif:=3;--default
    end;

    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;

    -- Abrimos el cursor
    OPEN c_documento(lnDiasDif);
    LOOP
        FETCH c_documento BULK COLLECT INTO r_documento LIMIT w_filas;

        /* Procedimiento inicial para generar interfaz */
        IF lbAbrirUtlFile THEN
          GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER_MAXCA(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                   psNombreArchivo, UTL_FILE_MAXI_CARA, lsDirTempo, lsNombreArchivo, V_HANDLE);
           lbAbrirUtlFile := FALSE;
        END IF;

        IF  r_documento.COUNT > 0 THEN
            FOR i IN r_documento.FIRST..r_documento.LAST LOOP
                lsLinea :=  r_documento(i).valorColumna1 ||';'||
                            r_documento(i).valorColumna2;
                UTL_FILE.PUT_LINE (V_HANDLE, lslinea);
            END LOOP;
        END IF;

        EXIT WHEN c_documento%NOTFOUND;
    END LOOP;
    -- Cerramos el cursor
    CLOSE c_documento;

    IF NOT lbAbrirUtlFile THEN
          utl_file.fclose(V_HANDLE);
         /* Procedimiento final para generar interfaz */
          GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;
EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_IMP_NOCRF_CABEC_RETAI: '||ls_sqlerrm);
END INT_PR_IMP_NOCRF_CABEC_RETAI;

/*****************************************************************************
  Descripcion         : Proceso que genera la informacion de Notas Credito Boletas Detalle Retail
  Fecha Creación  : 23/05/2014
  Autor                  : Sebastian Guerra
*****************************************************************************/
PROCEDURE INT_PR_IMP_NOCRF_DETAL_RETAI(psCodigoPais        VARCHAR2,
                                      psCodigoSistema     VARCHAR2,
                                      psCodigoInterfaz    VARCHAR2,
                                      psFechaFacturacion  VARCHAR2,
                                      psNombreArchivo     VARCHAR2,
                                      psTipoDocumento     VARCHAR2,
                                      psSerieDocumento    VARCHAR2)
IS
 CURSOR c_documento(lnDiasDif NUMBER) IS
  select a.txt_deta1 as valorColumna1
    from imp_tmp_felec_nc_retail_detal a,
            imp_tmp_felec_nc_retail_cabec b--,
          --  imp_tmp_felec_archi c
   where a.num_seri = b.num_seri
     and a.num_docu_inte = b.num_docu_inte
     --and a.num_docu_inte = c.num_docu_elec
     and (psSerieDocumento IS NULL OR a.num_seri = psSerieDocumento)
     and b.ind_proc_fe = '0'
     and substr(a.num_seri,0,1) ='F'
     and TRUNC(b.fec_fact) >=TRUNC(SYSDATE) - lnDiasDif
     order by a.num_seri,a.num_docu_inte,a.num_secu;

 TYPE documentorecord IS RECORD (
        valorColumna1 varchar2(4000)
 );

 TYPE documentotype IS TABLE OF documentorecord;
 r_documento        documentotype;

 /* Variables usadas para la generacion del archivo de texto */
 lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
 v_handle            UTL_FILE.FILE_TYPE;

 lsLinea             VARCHAR2(10000);
 lsNombreArchivo     VARCHAR2(50);
 lbAbrirUtlFile      BOOLEAN;
 lnDiasDif           number;

BEGIN
   begin
  select VAL_PARA INTO lnDiasDif
      from bas_param_pais
      where cod_pais= psCodigoPais
       and cod_sist='FAC'
       and cod_para='002';
    exception
       when others then
          lnDiasDif:=3;--default
    end;

    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;

    -- Abrimos el cursor
    OPEN c_documento(lnDiasDif);
    LOOP
        FETCH c_documento BULK COLLECT INTO r_documento LIMIT w_filas;

        /* Procedimiento inicial para generar interfaz */
        IF lbAbrirUtlFile THEN
          GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER_MAXCA(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                   psNombreArchivo, UTL_FILE_MAXI_CARA, lsDirTempo, lsNombreArchivo, V_HANDLE);
           lbAbrirUtlFile := FALSE;
        END IF;

        IF  r_documento.COUNT > 0 THEN
            FOR i IN r_documento.FIRST..r_documento.LAST LOOP
                lsLinea :=  r_documento(i).valorColumna1 ;
                UTL_FILE.PUT_LINE (V_HANDLE, lslinea);
            END LOOP;
        END IF;

        EXIT WHEN c_documento%NOTFOUND;
    END LOOP;
    -- Cerramos el cursor
    CLOSE c_documento;

    IF NOT lbAbrirUtlFile THEN
          utl_file.fclose(V_HANDLE);
         /* Procedimiento final para generar interfaz */
          GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;
EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_IMP_NOCRF_DETAL_RETAI: '||ls_sqlerrm);
END INT_PR_IMP_NOCRF_DETAL_RETAI;

  /***************************************************************************
  Descripcion       : Inserta los Documentos Internos por grupo
  Fecha Creacion    : 24/07/2014
  Autor             : Aurelio Oviedo
  ***************************************************************************/
PROCEDURE IMP_PR_INSER_DOCUM_INTER
(
  pscodigopais VARCHAR2,
  psfechafacturacion VARCHAR2,
  psTipoDocumento VARCHAR2
) IS

  lnsegmento   NUMBER := 1000;
  
  CURSOR cursecue IS
    SELECT cab.num_docu_cont_inte, 0
      FROM fac_docum_conta_cabec cab,
                fac_tipo_docum ftd,
                imp_felec_tipo_proce_cabec ifc
     WHERE cab.tido_oid_tipo_docu = ftd.oid_tipo_docu
       AND ftd.cod_tipo_docu = ifc.cod_homo
       AND cab.fec_fact >= TO_DATE (psfechafacturacion, 'DD/MM/YYYY')
       AND cab.ind_proc_fact_elec = 0
       AND ifc.cod_proc = psTipoDocumento;

  TYPE imp_tmp_felec_archi_tab_t IS TABLE OF imp_tmp_felec_archi%ROWTYPE INDEX BY BINARY_INTEGER;
  imp_tmp_felec_archi_tab imp_tmp_felec_archi_tab_t;

  i BINARY_INTEGER := 0;
  n NUMBER := 0;

BEGIN

  lnsegmento   := gen_pkg_gener.gen_fn_param_pais(pscodigopais, 'IMP', '005');

  DELETE imp_tmp_felec_archi;

    /*--Limpiamos la tabla que contiene la cabecera de documentos electronicos
    EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_TMP_FELEC_CABEC';

    --Limpiamos la tabla que contiene el detalle de documentos electronicos
    EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_TMP_FELEC_DETAL';*/
    
    OPEN cursecue;
    LOOP
      FETCH cursecue BULK COLLECT INTO imp_tmp_felec_archi_tab LIMIT lnsegmento;
      EXIT WHEN imp_tmp_felec_archi_tab.count = 0;

      FOR i IN imp_tmp_felec_archi_tab.first .. imp_tmp_felec_archi_tab.last
      LOOP
        BEGIN
          imp_tmp_felec_archi_tab(i).num_grup := n;
          
          INSERT INTO imp_tmp_felec_archi VALUES imp_tmp_felec_archi_tab (i);
        END;
      END LOOP;
      n := n + 1;
    END LOOP;
    CLOSE cursecue;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 1000);
    raise_application_error(-20123, 'ERROR IMP_PR_INSER_DOCUM_INTER: ' || ls_sqlerrm);
END IMP_PR_INSER_DOCUM_INTER;

END;
/
