CREATE OR REPLACE PACKAGE ped_pkg_proce IS

  /* Declaracion de Variables */
  ln_sqlcode  NUMBER(10);
  ls_sqlerrm  VARCHAR2(150);
  v_codorigen ped_orige_chequ.cod_orig_cheq%TYPE;

  /*******************************************************************************
    Descripcion         : Proceso padre que llama a los demas procesos hijos
    Fecha Creacion      : 21/12/2009
    Parametros Entrada:
        ps_Cod_Pais      : Codigo de Pais
    Autor               : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE ped_pr_proce_princ(ps_cod_pais VARCHAR2);

  /*******************************************************************************
    Descripcion         : Inserta en la tabla Pedidos a Chequear los pedidos con
                          origen de chequeo MA
    Fecha Creacion      : 21/12/2009
    Parametros Entrada:
        ps_Cod_Pais      : Codigo de Pais
    Autor               : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE ped_pr_proce_ingre_pedid_manu(ps_cod_pais VARCHAR2);

  /*******************************************************************************
    Descripcion         : Inserta en la tabla Pedidos a Chequear los pedidos con
                          origen de chequeo UN
    Fecha Creacion      : 21/12/2009
    Parametros Entrada:
        ps_Cod_Pais      : Codigo de Pais
    Autor               : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE ped_pr_proce_ingre_pedid_nuev(ps_cod_pais VARCHAR2);

  /*******************************************************************************
    Descripcion         : Inserta en la tabla Pedidos a Chequear los pedidos con
                          origen de chequeo RE
    Fecha Creacion      : 21/12/2009
    Parametros Entrada:
        ps_Cod_Pais      : Codigo de Pais
    Autor               : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE ped_pr_proce_ingre_pedid_rein(ps_cod_pais VARCHAR2);

  /*******************************************************************************
    Descripcion         : Inserta en la tabla Pedidos a Chequear los pedidos con
                          origen de chequeo C1
    Fecha Creacion      : 21/12/2009
    Parametros Entrada:
        ps_Cod_Pais      : Codigo de Pais
    Autor               : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE ped_pr_proce_ingre_pedid_top(ps_cod_pais VARCHAR2);

  /*******************************************************************************
    Descripcion         : Inserta en la tabla Pedidos a Chequear los pedidos con
                          origen de chequeo UA
    Fecha Creacion      : 21/12/2009
    Parametros Entrada:
        ps_Cod_Pais      : Codigo de Pais
    Autor               : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE ped_pr_proce_ingre_pedid_unad(ps_cod_pais VARCHAR2);

  /*******************************************************************************
    Descripcion         : Funcion que valida si un pedido es reincidente
    Fecha Creacion      : 21/12/2009
    Parametros Entrada:
        pd_fec_proceso      : fecha proceso
        pn_Oid_Cliente      : oid Cliente
        pn_Dias_Ante        : numero de Dias de antelacion
        pn_Cant_Oper        : cantidad de Operaciones
    Devuelve:
        1 Si es reincidente
        0 Si no es reincidente
    Autor               : Jose Luis Rodriguez
  ***************************************************************************/
  FUNCTION ped_fn_valid_reinc
  (
    pd_fec_proceso DATE,
    pn_oid_cliente NUMBER,
    pn_dias_ante   NUMBER,
    pn_cant_oper   NUMBER
  ) RETURN NUMBER;

  /*******************************************************************************
    Descripcion         : Actualiza el indicador de envio a Yobel cuando finaliza
                          el envio de las interfaces de Pedidos a Chequear
    Fecha Creacion      : 28/12/2009
    Parametros Entrada:
        ps_Cod_Pais      : Codigo de Pais
        ps_num_lote      : Numero de Lote
    Autor               : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE ped_pr_proce_updat_pedid_cheq
  (
    ps_cod_pais VARCHAR2,
    ps_num_lote VARCHAR2
  );

  /*******************************************************************************
    Descripcion         : ReasignaR los documentos legales que esten dentro del rango
    Fecha Creacion      : 13/04/2010
    Parametros Entrada:
        ps_tipoDocumentoContable      : Tipo de Documento
        ps_codigoSubacceso            : Codigo Subacceso
        pn_ejercicio                  : Ejercicio
        pn_rangoHastaDocLegal         : Rango Desde Documento Legal
        pn_rangoDesdeNrControl        : Rango Desde Nro Control
        ps_ind_NumControlDocuLegal    : Indicador Numero de Control de Documento Legal por Pais
        pn_rangoDesdeDocInterno       : Rango Desde Documento Interno
        pn_rangoHastaDocInterno       : Rango Hasta Documento Interno
    Autor               : Jesse James Rios Franco
  ***************************************************************************/
  PROCEDURE ped_pr_proce_reasi_docum_legal
  (
    pn_tipodocumentocontable   NUMBER,
    ps_codigosubacceso         VARCHAR2,
    ps_ejercicio               VARCHAR2,
    pn_rangodesdedoclegal      NUMBER,
    pn_rangodesdenrcontrol     NUMBER,
    ps_ind_numcontroldoculegal VARCHAR2,
    pn_rangodesdedocinterno    NUMBER,
    pn_rangohastadocinterno    NUMBER,
    ps_seriedoclegal           VARCHAR2
  );

  /*******************************************************************************
    Descripcion         : Recalcula la secuencia de los pedidos que aun no han sido
                          enviados a despacho de tal manera que permita la ejecucion
                          de mas de un lote de GP4 a GP5, esta actualizacion incluye
                          ademas la regeneracion de los documentos internos y la
                          actualizacion del numero de lote de facturacion al valor
                          maximo.
    Fecha Creacion      : 16/04/2010
    Parametros Entrada:
        p_codigoPais       : Codigo del pais a procesar
        p_codigoPeriodo    : Codigo de periodo
        p_fechaFacturacion : Fecha de Facturacion
    Autor               : Carlos Hurtado Ramirez
  ***************************************************************************/
  PROCEDURE ped_pr_actua_secue_pedid
  (
    p_codigopais       IN VARCHAR2,
    p_codigoperiodo    IN VARCHAR2,
    p_fechafacturacion IN VARCHAR2
  );
  /*******************************************************************************
    Descripcion         : Recalcula la secuencia de los pedidos que aun no han sido
                          enviados a despacho de tal manera que permita la ejecucion
                          de mas de un lote de GP4 a GP5, esta actualizacion incluye
                          ademas la regeneracion de los documentos internos y la
                          actualizacion del numero de lote de facturacion al valor
                          maximo.
    Fecha Creacion      : 16/04/2010
    Parametros Entrada:
        p_codigoPais       : Codigo del pais a procesar
        p_codigoPeriodo    : Codigo de periodo
        p_fechaFacturacion : Fecha de Facturacion
    Autor               : Carlos Hurtado Ramirez
  ***************************************************************************/
  PROCEDURE ped_pr_actua_secue_pedid2
  (
    p_codigopais       IN VARCHAR2,
    p_codigoperiodo    IN VARCHAR2,
    p_fechafacturacion IN VARCHAR2
  );
  /*******************************************************************************
    Descripcion         : Recalcula la secuencia de los pedidos que aun no han sido
                          enviados a despacho de tal manera que permita la ejecucion
                          de mas de un lote de GP4 a GP5, esta actualizacion incluye
                          ademas la regeneracion de los documentos internos y la
                          actualizacion del numero de lote de facturacion al valor
                          maximo.
    Fecha Creacion      : 16/04/2010
    Parametros Entrada:
        p_codigoPais       : Codigo del pais a procesar
        p_codigoPeriodo    : Codigo de periodo
        p_fechaFacturacion : Fecha de Facturacion
    Autor               : Carlos Hurtado Ramirez
  ***************************************************************************/
  PROCEDURE ped_pr_actua_secue_pedid3
  (
    p_codigopais       IN VARCHAR2,
    p_codigoperiodo    IN VARCHAR2,
    p_fechafacturacion IN VARCHAR2
  );
  /*******************************************************************************
    Descripcion         : Recalcula la secuencia de los pedidos que aun no han sido
                          enviados a despacho de tal manera que permita la ejecucion
                          de mas de un lote de GP4 a GP5, esta actualizacion incluye
                          ademas la regeneracion de los documentos internos y la
                          actualizacion del numero de lote de facturacion al valor
                          maximo.
    Fecha Creacion      : 16/04/2010
    Parametros Entrada:
        p_codigoPais       : Codigo del pais a procesar
        p_codigoPeriodo    : Codigo de periodo
        p_fechaFacturacion : Fecha de Facturacion
    Autor               : Carlos Hurtado Ramirez
  ***************************************************************************/
  PROCEDURE ped_pr_actua_secue_col
  (
    p_codigopais       IN VARCHAR2,
    p_codigoperiodo    IN VARCHAR2,
    p_fechafacturacion IN VARCHAR2
  );

  /*******************************************************************************
    Descripcion         : Reemplazo de productos importados por nacionales
    Fecha Creacion      : 01/06/2010
    Parametros Entrada:
        p_codigoPeriodo    : Codigo de periodo
    Autor               : Jesse James Rios Franco
  ***************************************************************************/

  PROCEDURE ped_pr_proce_reemp_produ_impor(p_codigoperiodo VARCHAR2);

  /*******************************************************************************
    Descripcion         : Proceso de Historico de Pedidos
    Fecha Creacion      : 13/08/2010
    Autor               : Jesse James Rios Franco
  ***************************************************************************/
  PROCEDURE ped_pr_proce_histo_pedid;

  /*******************************************************************************
    Descripcion       : Inserta en la tabla Pedidos a Chequear los pedidos con
                        origen de chequeo NR
    Fecha Creacion    : 15/09/2010
    Autor             : Carlos Diaz Valverde
    Parametros        :
                ps_Cod_Pais      : Codigo de Pais
  ***************************************************************************/
  PROCEDURE ped_pr_proce_nivel_riesg(ps_cod_pais VARCHAR2);
  /*******************************************************************************
    Descripcion       : Realiza el cierre del Periodo
    Fecha Creacion    : 24/03/2011
    Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE ped_pr_cierr_perio
  (
    pscodigopais          VARCHAR2,
    pscodigoperiodo       VARCHAR2,
    psusuario             VARCHAR2,
    pscodigotipodocumento VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera archivo CSV correspondiente al Reporte de Pedidos Bonificaciones
  Fecha Creacion    : 21/07/2011
  Autor             : Carlos Bazalar
  Parametros        :
              pnoidPais : Oid Pais
              psfechaFactura : Fecha de Factura
              pnoidTipoDocumento: Oid Documento
              psTitulo: Titulo
              psDirectorio: Directorio en donde se encuentra el archivo
  ***************************************************************************/
  PROCEDURE ped_pr_gener_repor_bonif_csv
  (
    pscodigopais       VARCHAR2,
    pnoidpais          NUMBER,
    psfechafactura     VARCHAR2,
    pnoidtipodocumento NUMBER,
    psnombrearchivo    VARCHAR2,
    pstitulo           VARCHAR2,
    psdirectorio       OUT VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Funcio para activar de control de la tabla BAS_CTRL_FACT
  Fecha Creacion    : 15/12/2011
  Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE ped_pr_envio_portal_findi;

  /***************************************************************************
  Descripcion       : Inserta un archivo xml a una tabla
  Fecha Creacion    : 17/01/2012
  Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE ped_pr_inser_archi_prol
  (
    psarchvio         IN VARCHAR2,
    psindicadororigen IN VARCHAR2,
    psoidtemporal     OUT VARCHAR2
  );

  /**************************************************************************
    Descripcion       : Devuelve el contenido de una etiqueta XML
    Fecha Creación    : 18/01/2012
    Autor             : Jose Luis Rodriguez
  /**************************************************************************/
  FUNCTION ped_fn_obtie_conte_etiqu
  (
    pscadenaclob     IN CLOB,
    psetiquetainicio IN VARCHAR2,
    psetiquetacierre IN VARCHAR2
  ) RETURN VARCHAR2;
  /**************************************************************************
    Descripcion       : Devuelve la secuenciacion para Colombia
    Fecha Creación    : 12/02/2013
    Autor             : Jorge Yepez
  /**************************************************************************/
  FUNCTION ped_fn_obtie_secue_colom(pnoidcabe IN NUMBER) RETURN VARCHAR2;

  /**************************************************************************
    Descripcion       : Genera el archivo de respuesta web service
    Fecha Creación    :  23/01/2012
    Autor             : Sergio Buchelli
    Parametros        :  pnOidArchivo oid con elc ual ha sido guardado en la tabla de entrade del archivo xml
                         pnOidPedido Oid pedido
  /**************************************************************************/
  PROCEDURE ped_fn_gener_archi_prol(pnoidarchivo NUMBER);

  /***************************************************************************
  Descripcion       : Cargar a la tabla temporal datos para el Reporte de Consultoras a Chequear
  Fecha Creacion    : 27/05/2013
  Autor             : Eduardo Sanchez
  Parametros        :
              pscodigoperiodo      : Codigo de Periodo
              psfechafacturainicio : Fecha de Factura Inicial
              psfechafacturafin    : Fecha de Factura Final
              pscondicion          : Cadena condicional para regiones y zonas
              pnoidproc            : Oid de Proceso incremental
  ***************************************************************************/
  PROCEDURE ped_pr_repor_consu_chequ
  (
    pscodigoperiodo      VARCHAR2,
    psfechafacturainicio VARCHAR2,
    psfechafacturafin    VARCHAR2,
    pscondicion          VARCHAR2,
    pnoidproc            OUT NUMBER
  );

  /***************************************************************************
  Descripcion       : Procesos de ejecucion de Facturacion adicional
  Fecha Creacion    : 01/07/2013
  Autor             : Carlos Chata
  ***************************************************************************/
  PROCEDURE ped_pr_proce_consu_fad
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    psdirectorio    OUT VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Procesos de ejecucion de Facturacion adicional
  Fecha Creacion    : 01/07/2013
  Autor             : Carlos Chata
  ***************************************************************************/
  PROCEDURE ped_pr_actua_consu_fad
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Procesos de ejecucion de Facturacion adicional
                       Genera un archivo en excel
  Fecha Creacion    : 01/07/2013
  Autor             : Carlos Chata
  ***************************************************************************/
  PROCEDURE ped_pr_repor_consu_fad
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    psdirectorio    OUT VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Proceso de Carga a tablas para los reportes de Demanda
                        Anticipada
                       Carga las tablas para generar los reportes
  Fecha Creacion    : 02/07/2013
  Autor             : Ivan Tocto
  ***************************************************************************/
  PROCEDURE ped_pr_repor_deman_antic
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    psfecha         VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Inserta Facturacion adicional Detalle
  Fecha Creacion    : 04/09/2013
  Autor             : Aurelio Oviedo
  ***************************************************************************/
  PROCEDURE ped_pr_inser_consu_fad_detal
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    psoidcabecera   VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Actualiza los datos de ultimo pedido
  Fecha Creacion    : 13/02/2014
  Autor             : Rosalvina Ramirez Guardia
  ***************************************************************************/

  PROCEDURE ped_pr_actua_ultim_pedid(psoidcliente VARCHAR2);

  PROCEDURE imp_pr_obtie_infor_docle_refe1(p_oiddocumento IN NUMBER);

  /***************************************************************************
   Descripcion           : Valida la carga masiva de fletes
   Fecha Creacion    : 27/08/2014
   Autor                   : Sebastian Guerra
  ****************************************************************************/
  PROCEDURE ped_pr_valid_carga_masiv_flete(pncodigousuario VARCHAR2);

  /***************************************************************************
      Descripcion           : Actualizar carga masiva de fletes
      Fecha Creacion    : 27/08/2014
      Autor                   : Sebastian Guerra
  ***************************************************************************/
  PROCEDURE ped_pr_actua_carga_masiv_flete(pncodigousuario VARCHAR2);

  /***************************************************************************
      Descripcion           : Retorna el numero pedido sobre la campaña para el
                            ciclo de nuevas
      Fecha Creacion    : 03/10/2014
      Autor                   : Rosalvina Ramirez
  ***************************************************************************/
  FUNCTION ped_fn_obtie_nries
  (
    psoidclie       NUMBER,
    psoidniripedi   NUMBER,
    pscodigoperiodo VARCHAR2,
    ps_cod_pais     VARCHAR2
  ) RETURN VARCHAR2;
  
  /*************************************************************************************
  Descripcion       : Procedimiento que carga el Reporte Detallado
                      Seguimiento Consultora para formato CSV
  Fecha Creacion    : 13/03/2015
  Autor             : Carlos Bazalar
  ************************************************************************************/
  PROCEDURE ped_pr_detal_segui_consu_csv(pscodigopais    VARCHAR2,
                                         psnombrearchivo VARCHAR2,
                                         pstitulo        VARCHAR2,
                                         psdirectorio    OUT VARCHAR2);
                                         
/*************************************************************************************
Descripcion       : Procedimiento que carga el Reporte de Avance Facturado de Programa 
                    de Reconocimiento VZ
Fecha Creacion    : 02/11/2015
Autor             : Gonzalo Huertas
************************************************************************************/
PROCEDURE ped_pr_afprv_repor_pedid
  (
    pscodigopais                IN VARCHAR2,
    pscodigoPeriodoInicio       IN VARCHAR2,
    pscodigoPeriodoFin          IN VARCHAR2
  );   
  
  /***************************************************************************
   Descripcion           : Valida la carga masiva Monto Minimo
   Fecha Creacion        : 04/01/2016
   Autor                 : Karina Valencia
  ****************************************************************************/
  PROCEDURE ped_pr_valid_carga_masiv_mtmin(pncodigousuario VARCHAR2);
 
   /***************************************************************************
      Descripcion           : Actualizar carga masiva Monto Minimo
      Fecha Creacion        : 04/01/2016
      Autor                 : Karina Valencia
  ***************************************************************************/
  PROCEDURE ped_pr_actua_carga_masiv_mtmin(pncodigousuario VARCHAR2);                                      

END ped_pkg_proce;
/
CREATE OR REPLACE PACKAGE BODY ped_pkg_proce IS

  /*******************************************************************************
    Descripcion         : Proceso padre que llama a los demas procesos hijos
    Fecha Creacion      : 21/12/2009
    Parametros Entrada:
        ps_Cod_Pais      : Codigo de Pais
    Autor               : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE ped_pr_proce_princ(ps_cod_pais VARCHAR2) AS
  
    CURSOR c_nomb_proceso IS
      SELECT poc.sec_eval,
             poc.nom_proc_ejec
        FROM ped_orige_chequ poc
       WHERE poc.cod_pais = ps_cod_pais
         AND poc.sec_eval != 0
       ORDER BY poc.sec_eval;
  
  BEGIN
  
    FOR curproceso IN c_nomb_proceso
    LOOP
      CASE curproceso.nom_proc_ejec
      -- Caso cuando se llama al procedure de Manual
        WHEN 'PED_PR_PROCE_INGRE_PEDID_MANU' THEN
          ped_pr_proce_ingre_pedid_manu(ps_cod_pais);
          -- Caso cuando se llama al procedure de Nuevas
        WHEN 'PED_PR_PROCE_INGRE_PEDID_NUEV' THEN
          ped_pr_proce_ingre_pedid_nuev(ps_cod_pais);
          -- Caso cuando se llama al procedure de Reincidentes
        WHEN 'PED_PR_PROCE_INGRE_PEDID_REIN' THEN
          ped_pr_proce_ingre_pedid_rein(ps_cod_pais);
          -- Caso cuando se llama al procedure de Clasificacion
        WHEN 'PED_PR_PROCE_INGRE_PEDID_TOP' THEN
          ped_pr_proce_ingre_pedid_top(ps_cod_pais);
          -- Caso cuando se llama al procedure de Unidad Administrativa
        WHEN 'PED_PR_PROCE_INGRE_PEDID_UNAD' THEN
          ped_pr_proce_ingre_pedid_unad(ps_cod_pais);
        WHEN 'PED_PR_PROCE_NIVEL_RIESG' THEN
          ped_pr_proce_nivel_riesg(ps_cod_pais);
      END CASE;
    
    END LOOP;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123, 'PED_PR_PROCE_PRINC: ' || ls_sqlerrm);
  END ped_pr_proce_princ;

  /*******************************************************************************
    Descripcion         : Inserta en la tabla Pedidos a Chequear los pedidos con
                          origen de chequeo MA
    Fecha Creacion      : 21/12/2009
    Parametros Entrada:
        ps_Cod_Pais      : Codigo de Pais
    Autor               : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE ped_pr_proce_ingre_pedid_manu(ps_cod_pais VARCHAR2) AS
  
  BEGIN
  
    -- Codigo de Origen Manual
    v_codorigen := 'MA';
  
    INSERT INTO ped_pedid_chequ
      (cod_pais,
       cod_tipo_cheq,
       oid_pedi_cheq,
       cod_orig_cheq,
       ind_envi_yobe,
       cod_peri,
       fec_fact,
       cod_regi,
       cod_zona,
       cod_secc,
       cod_terr,
       cod_clie,
       val_nume_soli)
      SELECT ps_cod_pais,
             cach.cod_tipo_cheq,
             con.oid_soli_cabe,
             v_codorigen,
             0,
             spc.cod_peri,
             con.fec_fact,
             (SELECT zr.cod_regi
                FROM zon_zona  zz,
                     zon_regio zr
               WHERE zz.oid_zona = con.zzon_oid_zona
                 AND zr.oid_regi = zz.zorg_oid_regi),
             (SELECT zz.cod_zona
                FROM zon_zona zz
               WHERE zz.oid_zona = con.zzon_oid_zona),
             (SELECT nvl(zs.cod_secc, '')
                FROM zon_terri_admin zta,
                     zon_terri       zt,
                     zon_secci       zs,
                     zon_zona        zz,
                     zon_regio       zr
               WHERE zta.oid_terr_admi = con.ztad_oid_terr_admi
                 AND zs.oid_secc = zta.zscc_oid_secc
                 AND zz.oid_zona = zs.zzon_oid_zona
                 AND zr.oid_regi = zz.zorg_oid_regi
                 AND zta.terr_oid_terr = zt.oid_terr),
             (SELECT nvl(zt.cod_terr, '')
                FROM zon_terri_admin zta,
                     zon_terri       zt
               WHERE zta.oid_terr_admi = con.ztad_oid_terr_admi
                 AND zta.terr_oid_terr = zt.oid_terr),
             cli.cod_clie,
             con.val_nume_soli
        FROM ped_solic_cabec con,
             mae_clien       cli,
             ped_consu_chequ cach,
             cra_perio       cp,
             seg_perio_corpo spc
       WHERE con.perd_oid_peri = cp.oid_peri
         AND cp.peri_oid_peri = spc.oid_peri
         AND spc.cod_peri = (SELECT cod_peri
                               FROM bas_ctrl_fact b
                              WHERE b.sta_camp = 0
                                AND b.ind_camp_act = 1)
         AND con.tspa_oid_tipo_soli_pais IN
             (SELECT tspa_oid_tipo_soli_pais
                FROM int_lar_tipo_solici_pedido_dis)
         AND con.fec_fact = (SELECT fec_proc
                               FROM bas_ctrl_fact b
                              WHERE b.sta_camp = 0
                                AND b.ind_camp_act = 1)
         AND con.ind_inte_lari_gene = 0
         AND con.num_unid_aten_tota > 0
         AND con.clie_oid_clie = cli.oid_clie
         --AND gen_pkg_gener.gen_fn_devuelve_id_cliente(cach.cod_clie) = con.clie_oid_clie
         and cach.cod_clie=cli.cod_clie
         AND cach.cod_peri = spc.cod_peri
         AND con.oid_soli_cabe NOT IN
             (SELECT oid_pedi_cheq
                FROM ped_pedid_chequ
               WHERE ind_envi_yobe = 0);
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'PED_PR_PROCE_INGRE_PEDID_MANU: ' ||
                              ls_sqlerrm);
  END ped_pr_proce_ingre_pedid_manu;

  /*******************************************************************************
    Descripcion         : Inserta en la tabla Pedidos a Chequear los pedidos con
                          origen de chequeo UN
    Fecha Creacion      : 21/12/2009
    Parametros Entrada:
        ps_Cod_Pais      : Codigo de Pais
    Autor               : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE ped_pr_proce_ingre_pedid_nuev(ps_cod_pais VARCHAR2) AS
  BEGIN
  
    -- Codigo de Origen Nuevas
    v_codorigen := 'UN';
  
    INSERT INTO ped_pedid_chequ
      (cod_pais,
       cod_tipo_cheq,
       oid_pedi_cheq,
       cod_orig_cheq,
       ind_envi_yobe,
       cod_peri,
       fec_fact,
       cod_regi,
       cod_zona,
       cod_secc,
       cod_terr,
       cod_clie,
       val_nume_soli)
      SELECT ps_cod_pais,
             tch.cod_tipo_cheq,
             con.oid_soli_cabe,
             v_codorigen,
             0,
             gen_pkg_gener.gen_fn_devuelve_des_perio(con.perd_oid_peri),
             con.fec_fact,
             (SELECT zr.cod_regi
                FROM zon_zona  zz,
                     zon_regio zr
               WHERE zz.oid_zona = con.zzon_oid_zona
                 AND zr.oid_regi = zz.zorg_oid_regi),
             (SELECT zz.cod_zona
                FROM zon_zona zz
               WHERE zz.oid_zona = con.zzon_oid_zona),
             (SELECT nvl(zs.cod_secc, '')
                FROM zon_terri_admin zta,
                     zon_terri       zt,
                     zon_secci       zs,
                     zon_zona        zz,
                     zon_regio       zr
               WHERE zta.oid_terr_admi = con.ztad_oid_terr_admi
                 AND zs.oid_secc = zta.zscc_oid_secc
                 AND zz.oid_zona = zs.zzon_oid_zona
                 AND zr.oid_regi = zz.zorg_oid_regi
                 AND zta.terr_oid_terr = zt.oid_terr),
             (SELECT nvl(zt.cod_terr, '')
                FROM zon_terri_admin zta,
                     zon_terri       zt
               WHERE zta.oid_terr_admi = con.ztad_oid_terr_admi
                 AND zta.terr_oid_terr = zt.oid_terr),
             cli.cod_clie,
             con.val_nume_soli
        FROM ped_solic_cabec       con,
             mae_clien             cli,
             mae_clien_datos_adici da,
             ped_tipo_chequ        tch
       WHERE con.perd_oid_peri =
             (SELECT c.oid_peri
                FROM cra_perio       c,
                     seg_perio_corpo d
               WHERE c.peri_oid_peri = d.oid_peri
                 AND d.cod_peri = (SELECT cod_peri
                                     FROM bas_ctrl_fact b
                                    WHERE b.sta_camp = 0
                                      AND b.ind_camp_act = 1))
         AND con.tspa_oid_tipo_soli_pais IN
             (SELECT tspa_oid_tipo_soli_pais
                FROM int_lar_tipo_solici_pedido_dis)
         AND con.fec_fact = (SELECT fec_proc
                               FROM bas_ctrl_fact b
                              WHERE b.sta_camp = 0
                                AND b.ind_camp_act = 1)
         AND con.ind_inte_lari_gene = 0
         AND con.num_unid_aten_tota > 0
         AND con.clie_oid_clie = cli.oid_clie
         AND cli.oid_clie = da.clie_oid_clie
         AND da.esta_oid_esta_clie = 1
         AND tch.ind_nuev = 1
         AND con.oid_soli_cabe NOT IN
             (SELECT oid_pedi_cheq
                FROM ped_pedid_chequ
               WHERE ind_envi_yobe = 0);
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'PED_PR_PROCE_INGRE_PEDID_NUEV: ' ||
                              ls_sqlerrm);
  END ped_pr_proce_ingre_pedid_nuev;

  /*******************************************************************************
    Descripcion         : Inserta en la tabla Pedidos a Chequear los pedidos con
                          origen de chequeo RE
    Fecha Creacion      : 21/12/2009
    Parametros Entrada:
        ps_Cod_Pais      : Codigo de Pais
    Autor               : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE ped_pr_proce_ingre_pedid_rein(ps_cod_pais VARCHAR2) AS
  BEGIN
  
    -- Codigo de Origen Reincidentes
    v_codorigen := 'RE';
  
    INSERT INTO ped_pedid_chequ
      (cod_pais,
       cod_tipo_cheq,
       oid_pedi_cheq,
       cod_orig_cheq,
       ind_envi_yobe,
       cod_peri,
       fec_fact,
       cod_regi,
       cod_zona,
       cod_secc,
       cod_terr,
       cod_clie,
       val_nume_soli)
      SELECT ps_cod_pais,
             tipo_chequeo,
             oid_soli_cabe,
             origen,
             yobel,
             cod_campana,
             fec_facturacion,
             region,
             zona,
             seccion,
             territorio,
             cliente,
             num_solicitud
        FROM (SELECT tch.cod_tipo_cheq tipo_chequeo,
                     con.oid_soli_cabe oid_soli_cabe,
                     v_codorigen AS origen,
                     0 yobel,
                     ped_fn_valid_reinc(SYSDATE,
                                        con.clie_oid_clie,
                                        tch.num_dias_atra_eval,
                                        tch.min_recl_falt) AS reincidente,
                     gen_pkg_gener.gen_fn_devuelve_des_perio(con.perd_oid_peri) cod_campana,
                     con.fec_fact fec_facturacion,
                     (SELECT zr.cod_regi
                        FROM zon_zona  zz,
                             zon_regio zr
                       WHERE zz.oid_zona = con.zzon_oid_zona
                         AND zr.oid_regi = zz.zorg_oid_regi) region,
                     (SELECT zz.cod_zona
                        FROM zon_zona zz
                       WHERE zz.oid_zona = con.zzon_oid_zona) zona,
                     (SELECT nvl(zs.cod_secc, '')
                        FROM zon_terri_admin zta,
                             zon_terri       zt,
                             zon_secci       zs,
                             zon_zona        zz,
                             zon_regio       zr
                       WHERE zta.oid_terr_admi = con.ztad_oid_terr_admi
                         AND zs.oid_secc = zta.zscc_oid_secc
                         AND zz.oid_zona = zs.zzon_oid_zona
                         AND zr.oid_regi = zz.zorg_oid_regi
                         AND zta.terr_oid_terr = zt.oid_terr) seccion,
                     (SELECT nvl(zt.cod_terr, '')
                        FROM zon_terri_admin zta,
                             zon_terri       zt
                       WHERE zta.oid_terr_admi = con.ztad_oid_terr_admi
                         AND zta.terr_oid_terr = zt.oid_terr) territorio,
                     cli.cod_clie cliente,
                     con.val_nume_soli num_solicitud
                FROM ped_solic_cabec con,
                     mae_clien       cli,
                     ped_tipo_chequ  tch
               WHERE con.perd_oid_peri =
                     (SELECT c.oid_peri
                        FROM cra_perio       c,
                             seg_perio_corpo d
                       WHERE c.peri_oid_peri = d.oid_peri
                         AND d.cod_peri =
                             (SELECT cod_peri
                                FROM bas_ctrl_fact b
                               WHERE b.sta_camp = 0
                                 AND b.ind_camp_act = 1))
                 AND con.tspa_oid_tipo_soli_pais IN
                     (SELECT tspa_oid_tipo_soli_pais
                        FROM int_lar_tipo_solici_pedido_dis)
                 AND con.fec_fact =
                     (SELECT fec_proc
                        FROM bas_ctrl_fact b
                       WHERE b.sta_camp = 0
                         AND b.ind_camp_act = 1)
                 AND con.ind_inte_lari_gene = 0
                 AND con.num_unid_aten_tota > 0
                 AND con.clie_oid_clie = cli.oid_clie
                 AND tch.ind_cons_rein = 1
                 AND con.oid_soli_cabe NOT IN
                     (SELECT oid_pedi_cheq
                        FROM ped_pedid_chequ
                       WHERE ind_envi_yobe = 0))
       WHERE reincidente = 1;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'PED_PR_PROCE_INGRE_PEDID_REIN: ' ||
                              ls_sqlerrm);
  END ped_pr_proce_ingre_pedid_rein;

  /*******************************************************************************
    Descripcion         : Inserta en la tabla Pedidos a Chequear los pedidos con
                          origen de chequeo C1
    Fecha Creacion      : 21/12/2009
    Parametros Entrada:
        ps_Cod_Pais      : Codigo de Pais
    Autor               : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE ped_pr_proce_ingre_pedid_top(ps_cod_pais VARCHAR2) AS
  BEGIN
  
    -- Codigo de Origen TOP
    v_codorigen := 'C1';
  
    INSERT INTO ped_pedid_chequ
      (cod_pais,
       cod_tipo_cheq,
       oid_pedi_cheq,
       cod_orig_cheq,
       ind_envi_yobe,
       cod_peri,
       fec_fact,
       cod_regi,
       cod_zona,
       cod_secc,
       cod_terr,
       cod_clie,
       val_nume_soli,
       val_text)
      SELECT DISTINCT ps_cod_pais,
                      cach.cod_tipo_cheq,
                      con.oid_soli_cabe,
                      v_codorigen,
                      0,
                      gen_pkg_gener.gen_fn_devuelve_des_perio(con.perd_oid_peri),
                      con.fec_fact,
                      (SELECT zr.cod_regi
                         FROM zon_zona  zz,
                              zon_regio zr
                        WHERE zz.oid_zona = con.zzon_oid_zona
                          AND zr.oid_regi = zz.zorg_oid_regi),
                      (SELECT zz.cod_zona
                         FROM zon_zona zz
                        WHERE zz.oid_zona = con.zzon_oid_zona),
                      (SELECT nvl(zs.cod_secc, '')
                         FROM zon_terri_admin zta,
                              zon_terri       zt,
                              zon_secci       zs,
                              zon_zona        zz,
                              zon_regio       zr
                        WHERE zta.oid_terr_admi = con.ztad_oid_terr_admi
                          AND zs.oid_secc = zta.zscc_oid_secc
                          AND zz.oid_zona = zs.zzon_oid_zona
                          AND zr.oid_regi = zz.zorg_oid_regi
                          AND zta.terr_oid_terr = zt.oid_terr),
                      (SELECT nvl(zt.cod_terr, '')
                         FROM zon_terri_admin zta,
                              zon_terri       zt
                        WHERE zta.oid_terr_admi = con.ztad_oid_terr_admi
                          AND zta.terr_oid_terr = zt.oid_terr),
                      cli.cod_clie,
                      con.val_nume_soli,
                      cach.val_text
        FROM ped_solic_cabec      con,
             mae_clien            cli,
             mae_clien_tipo_subti mcts,
             mae_clien_clasi      mcc,
             ped_clasi_chequ      cach
       WHERE con.perd_oid_peri =
             (SELECT c.oid_peri
                FROM cra_perio       c,
                     seg_perio_corpo d
               WHERE c.peri_oid_peri = d.oid_peri
                 AND d.cod_peri = (SELECT cod_peri
                                     FROM bas_ctrl_fact b
                                    WHERE b.sta_camp = 0
                                      AND b.ind_camp_act = 1))
         AND con.tspa_oid_tipo_soli_pais IN
             (SELECT tspa_oid_tipo_soli_pais
                FROM int_lar_tipo_solici_pedido_dis)
         AND con.fec_fact = (SELECT fec_proc
                               FROM bas_ctrl_fact b
                              WHERE b.sta_camp = 0
                                AND b.ind_camp_act = 1)
         AND con.ind_inte_lari_gene = 0
         AND con.num_unid_aten_tota > 0
         AND con.clie_oid_clie = cli.oid_clie
         AND cli.oid_clie = mcts.clie_oid_clie
         AND mcts.oid_clie_tipo_subt = mcc.ctsu_oid_clie_tipo_subt
         AND mcts.ticl_oid_tipo_clie = cach.oid_tipo_clie
         AND mcts.sbti_oid_subt_clie =
             decode(cach.oid_subt_clie,
                    NULL,
                    mcts.sbti_oid_subt_clie,
                    cach.oid_subt_clie)
         AND mcc.tccl_oid_tipo_clasi =
             decode(cach.oid_tipo_clas,
                    NULL,
                    mcc.tccl_oid_tipo_clasi,
                    cach.oid_tipo_clas)
         AND mcc.clas_oid_clas =
             decode(cach.oid_clas, NULL, mcc.clas_oid_clas, cach.oid_clas)
         AND con.oid_soli_cabe NOT IN
             (SELECT oid_pedi_cheq
                FROM ped_pedid_chequ
               WHERE ind_envi_yobe = 0);
  
  EXCEPTION
    WHEN OTHERS THEN
      /*ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'PED_PR_PROCE_INGRE_PEDID_TOP: ' || ls_sqlerrm);*/
      NULL;
  END ped_pr_proce_ingre_pedid_top;

  /*******************************************************************************
    Descripcion         : Inserta en la tabla Pedidos a Chequear los pedidos con
                          origen de chequeo UA
    Fecha Creacion      : 21/12/2009
    Parametros Entrada:
        ps_Cod_Pais      : Codigo de Pais
    Autor               : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE ped_pr_proce_ingre_pedid_unad(ps_cod_pais VARCHAR2) AS
  BEGIN
  
    -- Codigo de Origen Unidad Administrativa
    v_codorigen := 'UA';
  
    INSERT INTO ped_pedid_chequ
      (cod_pais,
       cod_tipo_cheq,
       oid_pedi_cheq,
       cod_orig_cheq,
       ind_envi_yobe,
       cod_peri,
       fec_fact,
       cod_regi,
       cod_zona,
       cod_secc,
       cod_terr,
       cod_clie,
       val_nume_soli)
      SELECT DISTINCT ps_cod_pais,
                      uach.cod_tipo_cheq,
                      con.oid_soli_cabe,
                      v_codorigen,
                      0,
                      gen_pkg_gener.gen_fn_devuelve_des_perio(con.perd_oid_peri),
                      con.fec_fact,
                      zr.cod_regi,
                      zz.cod_zona,
                      zs.cod_secc,
                      zt.cod_terr,
                      cli.cod_clie,
                      con.val_nume_soli
        FROM ped_solic_cabec       con,
             mae_clien             cli,
             mae_clien_unida_admin mcua,
             zon_terri_admin       zta,
             zon_terri             zt,
             zon_secci             zs,
             zon_zona              zz,
             zon_regio             zr,
             ped_unida_admin_chequ uach,
             cra_perio             cp,
             seg_perio_corpo       spc
       WHERE con.perd_oid_peri = cp.oid_peri
         and cp.peri_oid_peri=spc.oid_peri
         and spc.cod_peri=
             (SELECT cod_peri
                                     FROM bas_ctrl_fact b
                                    WHERE b.sta_camp = 0
                                      AND b.ind_camp_act = 1)
         AND con.tspa_oid_tipo_soli_pais IN
             (SELECT tspa_oid_tipo_soli_pais
                FROM int_lar_tipo_solici_pedido_dis)
         AND con.fec_fact = (SELECT fec_proc
                               FROM bas_ctrl_fact b
                              WHERE b.sta_camp = 0
                                AND b.ind_camp_act = 1)
         AND con.ind_inte_lari_gene = 0
         AND con.num_unid_aten_tota > 0
         AND con.clie_oid_clie = cli.oid_clie
         AND cli.oid_clie = mcua.clie_oid_clie
         AND mcua.ind_acti = 1
         AND mcua.ztad_oid_terr_admi = zta.oid_terr_admi
         AND zta.terr_oid_terr = zt.oid_terr
         AND zta.zscc_oid_secc = zs.oid_secc
         AND zs.zzon_oid_zona = zz.oid_zona
         AND zz.zorg_oid_regi = zr.oid_regi
         AND zr.cod_regi = uach.cod_regi
         AND (zz.cod_zona = ltrim(uach.cod_zona) OR
             ltrim(uach.cod_zona) IS NULL)
         AND (zs.cod_secc = ltrim(uach.cod_secc) OR
             ltrim(uach.cod_secc) IS NULL)
         AND (zt.cod_terr = uach.cod_terr OR uach.cod_terr = 0)
         AND ( spc.cod_peri=uach.cod_peri OR uach.cod_peri is null)
         AND con.oid_soli_cabe NOT IN
             (SELECT oid_pedi_cheq
                FROM ped_pedid_chequ
               WHERE ind_envi_yobe = 0);
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'PED_PR_PROCE_INGRE_PEDID_UNAD: ' ||
                              ls_sqlerrm);
  END ped_pr_proce_ingre_pedid_unad;

  /*******************************************************************************
    Descripcion         : Funcion que valida si un pedido es reincidente
    Fecha Creacion      : 21/12/2009
    Parametros Entrada:
        pd_fec_proceso      : fecha proceso
        pn_Oid_Cliente      : oid Cliente
        pn_Dias_Ante        : numero de Dias de antelacion
        pn_Cant_Oper        : cantidad de Operaciones
    Devuelve:
        1 Si es reincidente
        0 Si no es reincidente
    Autor               : Jose Luis Rodriguez
  ***************************************************************************/
  FUNCTION ped_fn_valid_reinc
  (
    pd_fec_proceso DATE,
    pn_oid_cliente NUMBER,
    pn_dias_ante   NUMBER,
    pn_cant_oper   NUMBER
  ) RETURN NUMBER IS
    lnvalor       NUMBER;
    lnreincidente NUMBER;
  
    lscampanainicio VARCHAR2(10);
    lscampanafin    VARCHAR2(10);
  
  BEGIN
  
    SELECT cod_peri
      INTO lscampanainicio
      FROM bas_ctrl_fact a
     WHERE a.sta_camp = 0
       AND a.ind_camp_act = 1;
  
    lscampanafin := gen_fn_calcu_perio(lscampanainicio, (pn_dias_ante * -1));
  
    SELECT COUNT(1)
      INTO lnvalor
      FROM rec_cabec_recla a,
           rec_opera_recla b,
           rec_tipos_opera e,
           rec_opera       f,
           --ped_solic_cabec g,
           cra_perio       h,
           seg_perio_corpo i
     WHERE a.oid_cabe_recl = b.care_oid_cabe_recl
          --AND a.soca_oid_soli_cabe = g.oid_soli_cabe
       AND a.perd_oid_peri_recl = h.oid_peri
       AND h.peri_oid_peri = i.oid_peri
       AND b.tiop_oid_tipo_oper = e.oid_tipo_oper
       AND e.rope_oid_oper = f.oid_oper
       AND f.cod_oper IN ('FM', 'FA')
       AND a.clie_oid_clie = pn_oid_cliente
          --AND TRUNC(pd_fec_proceso) - a.fec_ingr <= pn_Dias_Ante
          -- AND i.cod_peri BETWEEN lscampañainicio AND lscampañafin;
       AND i.cod_peri >= lscampanafin
       AND i.cod_peri <= lscampanainicio;
  
    IF lnvalor >= pn_cant_oper THEN
      lnreincidente := 1;
    ELSE
      lnreincidente := 0;
    END IF;
  
    RETURN lnreincidente;
  
  EXCEPTION
    WHEN no_data_found THEN
      RETURN NULL;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR PED_FN_VALID_REINC: ' || ls_sqlerrm);
  END ped_fn_valid_reinc;

  /*******************************************************************************
    Descripcion         : Actualiza el indicador de envio a Yobel cuando finaliza
                          el envio de las interfaces de Pedidos a Chequear
    Fecha Creacion      : 28/12/2009
    Parametros Entrada:
        ps_Cod_Pais      : Codigo de Pais
        ps_num_lote      : Numero de Lote
    Autor               : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE ped_pr_proce_updat_pedid_cheq
  (
    ps_cod_pais VARCHAR2,
    ps_num_lote VARCHAR2
  ) AS
  BEGIN
  
    UPDATE ped_pedid_chequ
       SET ind_envi_yobe = 1
     WHERE cod_pais = ps_cod_pais
       AND num_lote = ps_num_lote
       AND ind_envi_yobe = 0;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'PED_PR_PROCE_UPDAT_PEDID_CHEQ: ' ||
                              ls_sqlerrm);
    
  END ped_pr_proce_updat_pedid_cheq;

  /*******************************************************************************
    Descripcion         : ReasignaR los documentos legales que esten dentro del rango
    Fecha Creacion      : 13/04/2010
    Parametros Entrada:
        ps_tipoDocumentoContable      : Tipo de Documento
        ps_codigoSubacceso            : Codigo Subacceso
        pn_ejercicio                  : Ejercicio
        pn_rangoDesdeDocLegal         : Rango Desde Documento Legal
        pn_rangoDesdeNrControl        : Rango Desde Nro Control
        ps_ind_NumControlDocuLegal    : Indicador Numero de Control de Documento Legal por Pais
        pn_rangoDesdeDocInterno       : Rango Desde Documento Interno
        pn_rangoHastaDocInterno       : Rango Hasta Documento Interno
    Autor               : Jesse James Rios Franco
  ***************************************************************************/
  PROCEDURE ped_pr_proce_reasi_docum_legal
  (
    pn_tipodocumentocontable   NUMBER,
    ps_codigosubacceso         VARCHAR2,
    ps_ejercicio               VARCHAR2,
    pn_rangodesdedoclegal      NUMBER,
    pn_rangodesdenrcontrol     NUMBER,
    ps_ind_numcontroldoculegal VARCHAR2,
    pn_rangodesdedocinterno    NUMBER,
    pn_rangohastadocinterno    NUMBER,
    ps_seriedoclegal           VARCHAR2
  ) AS
    CURSOR c_cursor IS
      SELECT dcc.oid_cabe
        FROM fac_docum_conta_cabec dcc,
             seg_subac             s
       WHERE dcc.sbac_oid_sbac = s.oid_sbac
         AND s.cod_sbac = ps_codigosubacceso
         AND dcc.tido_oid_tipo_docu = pn_tipodocumentocontable
         AND dcc.val_ejer_docu_inte = ps_ejercicio
         AND dcc.num_docu_cont_inte >= pn_rangodesdedocinterno
         AND dcc.num_docu_cont_inte <= pn_rangohastadocinterno
         AND dcc.num_unid_aten_tota <> 0
       ORDER BY dcc.num_docu_cont_inte ASC;
  
    contador NUMBER;
  
  BEGIN
  
    contador := 0;
  
    FOR fila IN c_cursor
    LOOP
    
      UPDATE fac_docum_conta_cabec
         SET num_docu_lega = pn_rangodesdedoclegal + contador
       WHERE oid_cabe = fila.oid_cabe;
    
      UPDATE fac_regis_unico_venta
         SET val_nume_docu_lega = pn_rangodesdedoclegal + contador,
             val_seri_docu_lega = upper(ps_seriedoclegal)
       WHERE dcca_oid_cabe = fila.oid_cabe;
    
      IF ps_ind_numcontroldoculegal = '1' THEN
        UPDATE fac_docum_conta_cabec
           SET num_cont_docu_lega = pn_rangodesdenrcontrol + contador
         WHERE oid_cabe = fila.oid_cabe;
      
        UPDATE fac_regis_unico_venta
           SET num_cont_docu_lega = pn_rangodesdenrcontrol + contador
         WHERE dcca_oid_cabe = fila.oid_cabe;
      END IF;
    
      contador := contador + 1;
    
    END LOOP;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'PED_PR_PROCE_UPDAT_PEDID_CHEQ: ' ||
                              ls_sqlerrm);
    
  END ped_pr_proce_reasi_docum_legal;

  /*******************************************************************************
    Descripcion         : Recalcula la secuencia de los pedidos que aun no han sido
                          enviados a despacho de tal manera que permita la ejecucion
                          de mas de un lote de GP4 a GP5, esta actualizacion incluye
                          ademas la regeneracion de los documentos internos y la
                          actualizacion del numero de lote de facturacion al valor
                          maximo.
    Fecha Creacion      : 16/04/2010
    Parametros Entrada:
        p_codigoPais       : Codigo del pais a procesar
        p_codigoPeriodo    : Codigo de periodo
        p_fechaFacturacion : Fecha de Facturacion
    Autor               : Carlos Hurtado Ramirez
  ***************************************************************************/
  PROCEDURE ped_pr_actua_secue_pedid
  (
    p_codigopais       IN VARCHAR2,
    p_codigoperiodo    IN VARCHAR2,
    p_fechafacturacion IN VARCHAR2
  ) IS
  
    CURSOR c_pedidostransporte(p_oidperiodo NUMBER) IS
      SELECT consolidados.oid_clie,
             consolidados.cod_clie,
             consolidados.cod_zona,
             consolidados.oid_soli_cabe,
             consolidados.num_secu_ruta_tran,
             consolidados.num_secu_ruta_terr,
             consolidados.val_secu_ruta_terr,
             (nvl(rutas.num_secu_inic, 0) + consolidados.num_secu) num_secu
        FROM (
              -- Subquery que obtiene los numeros de secuencia iniciales de
              -- lotes anteriores pero para la misma fecha y periodo, para estos
              -- no se toma en cuenta el valor del tipo de solicitud pais
              SELECT pscs.rutr_oid_ruta_tran,
                      MAX(pscs.num_secu_fact_diar) num_secu_inic
                FROM ped_solic_cabec       psc,
                      ped_solic_cabec_secue pscs
               WHERE psc.oid_soli_cabe = pscs.soca_oid_soli_cabe
                 AND psc.perd_oid_peri = p_oidperiodo
                 AND psc.fec_fact = to_date(p_fechafacturacion, 'dd/mm/yyyy')
                 AND nvl(psc.ind_inte_lari_gene, 0) = 1 -- Pedidos que ya fueron enviados
               GROUP BY pscs.rutr_oid_ruta_tran) rutas,
             (
              -- Subquery que obtiene los consolidados no enviados a despacho y calcula
              -- el valor de su secuencia en base a la tabla APP_RUTAS_TERRI
              SELECT mc.oid_clie,
                      mc.cod_clie,
                      zon.cod_zona,
                      con.oid_soli_cabe,
                      tra.oid_ruta_tran,
                      tra.num_secu num_secu_ruta_tran,
                      ter.num_secu num_secu_ruta_terr,
                      TRIM(to_char(tra.num_secu, '000000')) ||
                      TRIM(to_char(nvl(ter.num_secu, con.terr_oid_terr),
                                   '000000')) val_secu_ruta_terr,
                      row_number() over(PARTITION BY tra.num_secu ORDER BY tra.num_secu, nvl(ter.num_secu, con.terr_oid_terr), mc.cod_clie) num_secu
                FROM ped_solic_cabec con,
                      mae_clien       mc,
                      zon_zona        zon,
                      app_rutas_trans tra,
                      app_rutas_terri ter
               WHERE con.terr_oid_terr = ter.terr_oid_terr(+)
                 AND con.clie_oid_clie = mc.oid_clie
                 AND con.zzon_oid_zona = zon.oid_zona
                 AND zon.cod_zona = tra.cod_ruta
                    --      and ter.rutr_oid_ruta_tran = tra.oid_ruta_tran -- Comentado para permitir el outer join de los territorios no secuenciados (CHR - 10/09/2010)
                 AND con.perd_oid_peri = p_oidperiodo
                 AND con.fec_fact = to_date(p_fechafacturacion, 'dd/mm/yyyy')
                 AND con.num_unid_aten_tota > 0 -- Pedidos con unidades atendidas
                 AND nvl(con.ind_inte_lari_gene, 0) = 0 -- Pedidos que no han sido enviados a despacho
                 AND EXISTS (SELECT NULL
                        FROM int_lar_tipo_solici_pedido_dis l
                       WHERE l.tspa_oid_tipo_soli_pais =
                             con.tspa_oid_tipo_soli_pais)
               ORDER BY tra.num_secu,
                         ter.num_secu,
                         mc.cod_clie) consolidados
       WHERE rutas.rutr_oid_ruta_tran(+) = consolidados.oid_ruta_tran
       ORDER BY consolidados.num_secu_ruta_tran,
                consolidados.num_secu_ruta_terr,
                consolidados.cod_clie;
  
    CURSOR c_pedidoscliente(p_oidperiodo NUMBER) IS
      SELECT consolidados.oid_clie,
             consolidados.cod_clie,
             consolidados.cod_zona,
             consolidados.oid_soli_cabe,
             consolidados.num_secu_ruta_tran,
             consolidados.num_secu_ruta_clie,
             consolidados.val_secu_ruta_terr,
             (nvl(rutas.num_secu_inic, 0) + consolidados.num_secu) num_secu
        FROM (
              -- Subquery que obtiene los numeros de secuencia iniciales de
              -- lotes anteriores pero para la misma fecha y periodo, para estos
              -- no se toma en cuenta el valor del tipo de solicitud pais
              SELECT pscs.rutr_oid_ruta_tran,
                      MAX(pscs.num_secu_fact_diar) num_secu_inic
                FROM ped_solic_cabec       psc,
                      ped_solic_cabec_secue pscs
               WHERE psc.oid_soli_cabe = pscs.soca_oid_soli_cabe
                 AND psc.perd_oid_peri = p_oidperiodo
                 AND psc.fec_fact = to_date(p_fechafacturacion, 'dd/mm/yyyy')
                 AND nvl(psc.ind_inte_lari_gene, 0) = 1 -- Pedidos que ya fueron enviados
               GROUP BY pscs.rutr_oid_ruta_tran) rutas,
             (
              -- Subquery que obtiene los consolidados no enviados a despacho y calcula
              -- el valor de su secuencia en base a la tabla APP_RUTAS_CLIEN
              SELECT mc.oid_clie,
                      mc.cod_clie,
                      zon.cod_zona,
                      con.oid_soli_cabe,
                      tra.oid_ruta_tran,
                      tra.num_secu num_secu_ruta_tran,
                      cli.val_nume_secu num_secu_ruta_clie,
                      TRIM(to_char(tra.num_secu, '000000')) ||
                      TRIM(to_char(cli.val_nume_secu, '000000')) val_secu_ruta_terr,
                      row_number() over(PARTITION BY tra.num_secu ORDER BY tra.num_secu, cli.val_nume_secu) num_secu
                FROM ped_solic_cabec con,
                      mae_clien       mc,
                      zon_zona        zon,
                      app_rutas_trans tra,
                      app_rutas_clien cli
               WHERE con.clie_oid_clie = mc.oid_clie
                 AND con.zzon_oid_zona = zon.oid_zona
                 AND cli.rutr_oid_ruta_tran = tra.oid_ruta_tran
                 AND con.clie_oid_clie = cli.clie_oid_clie
                 AND con.perd_oid_peri = p_oidperiodo
                 AND con.fec_fact = to_date(p_fechafacturacion, 'dd/mm/yyyy')
                 AND nvl(con.ind_inte_lari_gene, 0) = 0 -- Pedidos que no han sido enviados a despacho
                 AND con.num_unid_aten_tota > 0
                 AND EXISTS (SELECT NULL
                        FROM int_lar_tipo_solici_pedido_dis l
                       WHERE l.tspa_oid_tipo_soli_pais =
                             con.tspa_oid_tipo_soli_pais)
               ORDER BY tra.num_secu,
                         cli.val_nume_secu) consolidados
       WHERE rutas.rutr_oid_ruta_tran(+) = consolidados.oid_ruta_tran
       ORDER BY consolidados.num_secu_ruta_tran,
                consolidados.num_secu_ruta_clie;
  
    TYPE t_oid_clie IS TABLE OF mae_clien.oid_clie%TYPE;
    TYPE t_cod_clie IS TABLE OF mae_clien.cod_clie%TYPE;
    TYPE t_cod_zona IS TABLE OF zon_zona.cod_zona%TYPE;
    TYPE t_oid_soli_cabe IS TABLE OF ped_solic_cabec.oid_soli_cabe%TYPE;
    TYPE t_num_secu_ruta_tran IS TABLE OF app_rutas_trans.num_secu%TYPE;
    TYPE t_num_secu_ruta_terr IS TABLE OF app_rutas_terri.num_secu%TYPE;
    TYPE t_num_secu_ruta_clie IS TABLE OF app_rutas_clien.val_nume_secu%TYPE;
    TYPE t_val_secu_ruta_terr IS TABLE OF ped_solic_cabec_secue.val_secu_ruta_terr%TYPE;
    TYPE t_num_secu IS TABLE OF ped_solic_cabec_secue.num_secu_fact_diar%TYPE;
  
    v_oid_clie           t_oid_clie;
    v_cod_clie           t_cod_clie;
    v_cod_zona           t_cod_zona;
    v_oid_soli_cabe      t_oid_soli_cabe;
    v_num_secu_ruta_tran t_num_secu_ruta_tran;
    v_num_secu_ruta_terr t_num_secu_ruta_terr;
    v_num_secu_ruta_clie t_num_secu_ruta_clie;
    v_val_secu_ruta_terr t_val_secu_ruta_terr;
    v_num_secu           t_num_secu;
  
    -- A diferencia de los cursores anteriores, no consideramos la condicion
    -- NUM_UNID_ATEN_TOTA > 0 ya que a nivel de documentos contables, sí se
    -- llegan a generar registros en FAC_DOCUM_CONTA_CABEC a pesar que el
    -- consolidado no tenga unidades atendidas
    CURSOR c_documentos(p_oidperiodo NUMBER) IS
      SELECT documentos.oid_cabe,
             documentos.oid_regi,
             documentos.val_ejer_docu_inte,
             (tipos.num_docu_cont_inte_inic + documentos.num_secu - 1) num_docu_cont_inte_calc
        FROM (
              -- select que obtiene los numeros de documento iniciales
              SELECT doc.tido_oid_tipo_docu,
                      MIN(doc.num_docu_cont_inte) num_docu_cont_inte_inic
                FROM ped_solic_cabec       psc,
                      fac_docum_conta_cabec doc
               WHERE psc.oid_soli_cabe = doc.soca_oid_soli_cabe
                 AND psc.perd_oid_peri = p_oidperiodo
                 AND psc.fec_fact = to_date(p_fechafacturacion, 'dd/mm/yyyy')
                 AND nvl(psc.ind_inte_lari_gene, 0) = 0 -- Pedidos que no han sido enviados a despacho
                 AND EXISTS (SELECT NULL
                        FROM int_lar_tipo_solici_pedido_dis lar
                       WHERE lar.tspa_oid_tipo_soli_pais =
                             psc.tspa_oid_tipo_soli_pais)
               GROUP BY doc.tido_oid_tipo_docu) tipos,
             (
              -- select que obtiene los datos de los documentos matriciales y su secuencia
              SELECT con.oid_soli_cabe,
                      con.clie_oid_clie,
                      con.fec_fact,
                      con.num_lote_fact,
                      row_number() over(PARTITION BY cab.tido_oid_tipo_docu ORDER BY sec.val_secu_ruta_terr, mc.cod_clie, cab.oid_cabe) num_secu,
                      cab.oid_cabe,
                      cab.tido_oid_tipo_docu,
                      cab.num_docu_cont_inte,
                      nvl(cab.val_ejer_docu_inte, to_char(cab.fec_fact, 'yy')) val_ejer_docu_inte,
                      ven.oid_regi
                FROM ped_solic_cabec       con,
                      mae_clien             mc,
                      ped_solic_cabec_secue sec,
                      fac_docum_conta_cabec cab,
                      fac_regis_unico_venta ven
               WHERE con.oid_soli_cabe = sec.soca_oid_soli_cabe
                 AND con.clie_oid_clie = mc.oid_clie
                 AND con.perd_oid_peri = p_oidperiodo
                 AND con.fec_fact = to_date(p_fechafacturacion, 'dd/mm/yyyy')
                 AND nvl(con.ind_inte_lari_gene, 0) = 0 -- Pedidos que no han sido enviados a despacho
                 AND EXISTS (SELECT NULL
                        FROM int_lar_tipo_solici_pedido_dis l
                       WHERE l.tspa_oid_tipo_soli_pais =
                             con.tspa_oid_tipo_soli_pais)
                 AND cab.soca_oid_soli_cabe = con.oid_soli_cabe
                 AND cab.oid_cabe = ven.dcca_oid_cabe
               ORDER BY cab.tido_oid_tipo_docu,
                         sec.val_secu_ruta_terr,
                         mc.cod_clie,
                         cab.oid_cabe) documentos
       WHERE documentos.tido_oid_tipo_docu = tipos.tido_oid_tipo_docu;
  
    CURSOR c_numerosinternos(p_oidperiodo NUMBER) IS
      SELECT cab.tido_oid_tipo_docu,
             cab.sbac_oid_sbac,
             MAX(cab.num_docu_cont_inte) val_ulti_nume_docu_cont_inte
        FROM ped_solic_cabec       con,
             ped_solic_cabec_secue sec,
             fac_docum_conta_cabec cab
       WHERE con.oid_soli_cabe = sec.soca_oid_soli_cabe
         AND con.perd_oid_peri = p_oidperiodo
         AND con.fec_fact = to_date(p_fechafacturacion, 'dd/mm/yyyy')
         AND nvl(con.ind_inte_lari_gene, 0) = 0 -- Pedidos que no han sido enviados a despacho
         AND EXISTS
       (SELECT NULL
                FROM int_lar_tipo_solici_pedido_dis l
               WHERE l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais)
         AND cab.soca_oid_soli_cabe = con.oid_soli_cabe
       GROUP BY cab.tido_oid_tipo_docu,
                cab.sbac_oid_sbac
       ORDER BY cab.tido_oid_tipo_docu,
                cab.sbac_oid_sbac;
  
    TYPE t_oid_cabe IS TABLE OF fac_docum_conta_cabec.oid_cabe%TYPE;
    TYPE t_oid_regi IS TABLE OF fac_regis_unico_venta.oid_regi%TYPE;
    TYPE t_val_ejer_docu_inte IS TABLE OF fac_docum_conta_cabec.val_ejer_docu_inte%TYPE;
    TYPE t_num_docu_cont_inte IS TABLE OF fac_docum_conta_cabec.num_docu_cont_inte%TYPE;
    TYPE t_tido_oid_tipo_docu IS TABLE OF fac_docum_conta_cabec.tido_oid_tipo_docu%TYPE;
    TYPE t_sbac_oid_sbac IS TABLE OF fac_docum_conta_cabec.sbac_oid_sbac%TYPE;
  
    v_oid_cabe           t_oid_cabe;
    v_oid_regi           t_oid_regi;
    v_val_ejer_docu_inte t_val_ejer_docu_inte;
    v_num_docu_cont_inte t_num_docu_cont_inte;
    v_tido_oid_tipo_docu t_tido_oid_tipo_docu;
    v_sbac_oid_sbac      t_sbac_oid_sbac;
  
    rows NATURAL := 500; -- Numero de filas a procesar a la vez
    i    BINARY_INTEGER := 0;
  
    l_oidpais               NUMBER;
    l_oidperiodo            NUMBER;
    l_tiposecuenciacion     seg_pais.ind_secu%TYPE;
    l_numerolotefacturacion ped_solic_cabec.num_lote_fact%TYPE;
  
    l_oid_docu_suba           fac_docum_subac.oid_docu_suba%TYPE;
    l_val_seri_docu_lega      fac_docum_subac.val_seri_docu_lega%TYPE;
    l_val_ulti_nume_docu_inte fac_docum_subac.val_ulti_nume_docu_inte%TYPE;
  
  BEGIN
  
    -- Obtenemos el OID del pais y del periodo
    l_oidpais    := gen_pkg_gener.gen_fn_devuelve_id_pais(p_codigopais);
    l_oidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(p_codigoperiodo);
  
    -- Obtenemos el tipo de secuenciacion
    SELECT nvl(ind_secu, 'T')
      INTO l_tiposecuenciacion
      FROM seg_pais
     WHERE cod_pais = p_codigopais;
  
    -- 1 - ACTUALIZACION DE LA SECUENCIA DE LOS CONSOLIDADOS
    -- Actualizamos la secuencia de los pedidos en PED_SOLIC_CABEC_SECUE
    -- dependiento del tipo de secuenciacion parametrizado a nivel de pais YA NO SE USA
  
    -- Se CAMBIO X el proceso de secuenciacion dinamico
    app_pkg_proce.app_pr_proce_secue(p_codigopais,
                                     p_codigoperiodo,
                                     p_fechafacturacion,
                                     '');
  
    -- Tipo Transporte ya no se usa
    /*IF l_tipoSecuenciacion = 'T' THEN
        OPEN c_pedidosTransporte(l_oidPeriodo);
        LOOP
            FETCH c_pedidosTransporte
            BULK COLLECT INTO v_oid_clie,
                              v_cod_clie,
                              v_cod_zona,
                              v_oid_soli_cabe,
                              v_num_secu_ruta_tran,
                              v_num_secu_ruta_terr,
                              v_val_secu_ruta_terr,
                              v_num_secu  LIMIT rows;
            EXIT WHEN v_oid_soli_cabe.count = 0;
    
            FORALL i IN 1..v_oid_soli_cabe.count
            UPDATE ped_solic_cabec_secue sec
               SET sec.val_secu_ruta_terr = v_val_secu_ruta_terr(i),
                   sec.num_secu_fact_diar = v_num_secu(i),
                   sec.num_secu_zona_ruta = v_num_secu_ruta_tran(i)
             WHERE sec.soca_oid_soli_cabe = v_oid_soli_cabe(i);
    
        END LOOP;
        CLOSE c_pedidosTransporte;
    ELSE
        OPEN c_pedidosCliente(l_oidPeriodo);
        LOOP
            FETCH c_pedidosCliente
            BULK COLLECT INTO v_oid_clie,
                              v_cod_clie,
                              v_cod_zona,
                              v_oid_soli_cabe,
                              v_num_secu_ruta_tran,
                              v_num_secu_ruta_clie,
                              v_val_secu_ruta_terr,
                              v_num_secu  LIMIT rows;
            EXIT WHEN v_oid_soli_cabe.count = 0;
    
            FORALL i IN 1..v_oid_soli_cabe.count
            UPDATE ped_solic_cabec_secue sec
               SET sec.val_secu_ruta_terr = v_val_secu_ruta_terr(i),
                   sec.num_secu_fact_diar = v_num_secu(i),
                   sec.num_secu_zona_ruta = v_num_secu_ruta_tran(i)
             WHERE sec.soca_oid_soli_cabe = v_oid_soli_cabe(i);
    
        END LOOP;
        CLOSE c_pedidosCliente;
    
    END IF;*/
  
    -- Para los consolidados con numero de unidades atendidas en cero
    -- actualizamos su secuencia a 0 para evitar saltos en la secuencia
    -- de los paquetes documentarios YA NO S EUSA
    /*UPDATE ped_solic_cabec_secue sec
    SET sec.num_secu_fact_diar = 0
    WHERE EXISTS (
        select con.oid_soli_cabe
        from ped_solic_cabec con
        where con.perd_oid_peri = l_oidPeriodo
          and con.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
          and nvl(con.ind_inte_lari_gene, 0) = 0 -- Pedidos que no han sido enviados a despacho
          and con.num_unid_aten_tota = 0 -- Sin unidades atendidas
          and exists (
            select null
            from int_lar_tipo_solici_pedido_dis l
            where l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais
          )
          and sec.soca_oid_soli_cabe = con.oid_soli_cabe
    );*/
  
    -- 2 - ACTUALIZACION DEL NUMERO DE DOCUMENTO INTERNO DE LOS DOCUMENTOS CONTABLES
    -- Actualizamos los numeros de codigo interno de los documentos
    -- matriciales en base a la secuencia actualizada
    OPEN c_documentos(l_oidperiodo);
    LOOP
      FETCH c_documentos BULK COLLECT
        INTO v_oid_cabe,
             v_oid_regi,
             v_val_ejer_docu_inte,
             v_num_docu_cont_inte LIMIT rows;
      EXIT WHEN v_oid_cabe.count = 0;
    
      -- Actualizamos el documento interno en FAC_DOCUM_CONTA_CABEC
      FORALL j IN 1 .. v_oid_cabe.count
        UPDATE fac_docum_conta_cabec cab
           SET cab.num_docu_cont_inte = v_num_docu_cont_inte(j),
               cab.val_ejer_docu_inte = v_val_ejer_docu_inte(j)
         WHERE cab.oid_cabe = v_oid_cabe(j);
    
      -- Actualizamos el documento interno en FAC_REGIS_UNICO_VENTA
      FORALL k IN 1 .. v_oid_regi.count
        UPDATE fac_regis_unico_venta ven
           SET ven.num_docu_cont_inte = v_num_docu_cont_inte(k),
               ven.val_ejer_docu_inte = v_val_ejer_docu_inte(k)
         WHERE ven.oid_regi = v_oid_regi(k);
    
    END LOOP;
    CLOSE c_documentos;
  
    -- 3 - ACTUALIZACION DEL LOTE DE FACTURACION
    -- Primero obtenemos el numero de lote mayor de aquellos consolidados
    -- que aun no hayan sido enviados a ser despachados (indicador LAR en 0)
    BEGIN
    
      SELECT MAX(con.num_lote_fact)
        INTO l_numerolotefacturacion
        FROM ped_solic_cabec con
       WHERE con.perd_oid_peri = l_oidperiodo
         AND con.fec_fact = to_date(p_fechafacturacion, 'dd/mm/yyyy')
         AND nvl(con.ind_inte_lari_gene, 0) = 0 -- Pedidos que no han sido enviados a despacho
         AND con.num_lote_fact IS NOT NULL
         AND EXISTS
       (SELECT NULL
                FROM int_lar_tipo_solici_pedido_dis l
               WHERE l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais);
    
    EXCEPTION
      WHEN no_data_found THEN
        l_numerolotefacturacion := NULL;
    END;
  
    -- Hacemos la actualizacion correspondiente
    IF l_numerolotefacturacion IS NOT NULL THEN
      UPDATE ped_solic_cabec con
         SET con.num_lote_fact = l_numerolotefacturacion
       WHERE con.perd_oid_peri = l_oidperiodo
         AND con.fec_fact = to_date(p_fechafacturacion, 'dd/mm/yyyy')
         AND nvl(con.ind_inte_lari_gene, 0) = 0 -- Pedidos que no han sido enviados a despacho
         AND con.num_lote_fact IS NOT NULL
         AND EXISTS
       (SELECT NULL
                FROM int_lar_tipo_solici_pedido_dis l
               WHERE l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais);
    END IF;
  
    -- 4 - ACTUALIZACION DE LAS SEMILLAS DE LOS DOCUMENTOS CONTABLES
    OPEN c_numerosinternos(l_oidperiodo);
    LOOP
      FETCH c_numerosinternos BULK COLLECT
        INTO v_tido_oid_tipo_docu,
             v_sbac_oid_sbac,
             v_num_docu_cont_inte LIMIT rows;
      EXIT WHEN v_tido_oid_tipo_docu.count = 0;
    
      FOR i IN 1 .. v_tido_oid_tipo_docu.count
      LOOP
      
        -- Obtenemos el valor actual de la semilla en base al tipo de documento
        SELECT fds.oid_docu_suba,
               fds.val_seri_docu_lega,
               fds.val_ulti_nume_docu_inte
          INTO l_oid_docu_suba,
               l_val_seri_docu_lega,
               l_val_ulti_nume_docu_inte
          FROM fac_docum_subac fds
         WHERE fds.sbac_oid_sbac = v_sbac_oid_sbac(i)
           AND fds.tido_oid_tipo_docu = v_tido_oid_tipo_docu(i)
           AND fds.pais_oid_pais = l_oidpais
           AND fds.val_ulti_ejer_docu_inte =
               (SELECT MAX(x.val_ulti_ejer_docu_inte)
                  FROM fac_docum_subac x
                 WHERE x.tido_oid_tipo_docu = v_tido_oid_tipo_docu(i)
                   AND x.sbac_oid_sbac = fds.sbac_oid_sbac);
      
        -- Si el valor de la semilla es diferente al ultimo numero
        -- de documento interno hacemos la actualizacion correspondiente
        IF l_val_ulti_nume_docu_inte != v_num_docu_cont_inte(i) THEN
        
          UPDATE fac_docum_subac fds
             SET fds.val_ulti_nume_docu_inte = v_num_docu_cont_inte(i)
           WHERE fds.oid_docu_suba = l_oid_docu_suba;
        
        END IF;
      
        -- Finalmente actualizamos aquellos registros que tengan
        -- el valor de la serie en null con el valor correcto
        -- primero en el registro de ventas ...
        UPDATE fac_regis_unico_venta x
           SET x.val_seri_docu_lega = l_val_seri_docu_lega
         WHERE EXISTS
         (SELECT ven.oid_regi
                  FROM fac_docum_conta_cabec cab,
                       fac_regis_unico_venta ven
                 WHERE cab.oid_cabe = ven.dcca_oid_cabe
                   AND cab.perd_oid_peri = l_oidperiodo
                   AND cab.pais_oid_pais = l_oidpais
                   AND cab.fec_fact =
                       to_date(p_fechafacturacion, 'dd/mm/yyyy')
                   AND cab.tido_oid_tipo_docu = v_tido_oid_tipo_docu(i)
                   AND cab.val_seri_docu_lega IS NULL
                   AND ven.oid_regi = x.oid_regi);
      
        -- y luego en los documentos contables
        UPDATE fac_docum_conta_cabec y
           SET y.val_seri_docu_lega = l_val_seri_docu_lega
         WHERE EXISTS
         (SELECT cab.oid_cabe
                  FROM fac_docum_conta_cabec cab
                 WHERE cab.perd_oid_peri = l_oidperiodo
                   AND cab.pais_oid_pais = l_oidpais
                   AND cab.fec_fact =
                       to_date(p_fechafacturacion, 'dd/mm/yyyy')
                   AND cab.tido_oid_tipo_docu = v_tido_oid_tipo_docu(i)
                   AND cab.val_seri_docu_lega IS NULL
                   AND cab.oid_cabe = y.oid_cabe);
      
      END LOOP;
    END LOOP;
    CLOSE c_numerosinternos;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'PED_PR_ACTUA_SECUE_PEDID: ' || ls_sqlerrm);
    
  END ped_pr_actua_secue_pedid;

  /*******************************************************************************
    Descripcion         : Recalcula la secuencia de los pedidos que aun no han sido
                          enviados a despacho de tal manera que permita la ejecucion
                          de mas de un lote de GP4 a GP5, esta actualizacion incluye
                          ademas la regeneracion de los documentos internos y la
                          actualizacion del numero de lote de facturacion al valor
                          maximo.
    Fecha Creacion      : 16/04/2010
    Parametros Entrada:
        p_codigoPais       : Codigo del pais a procesar
        p_codigoPeriodo    : Codigo de periodo
        p_fechaFacturacion : Fecha de Facturacion
    Autor               : Carlos Hurtado Ramirez
  ***************************************************************************/
  PROCEDURE ped_pr_actua_secue_pedid2
  (
    p_codigopais       IN VARCHAR2,
    p_codigoperiodo    IN VARCHAR2,
    p_fechafacturacion IN VARCHAR2
  ) IS
  
    -- A diferencia de los cursores anteriores, no consideramos la condicion
    -- NUM_UNID_ATEN_TOTA > 0 ya que a nivel de documentos contables, si se
    -- llegan a generar registros en FAC_DOCUM_CONTA_CABEC a pesar que el
    -- consolidado no tenga unidades atendidas
    CURSOR c_documentos(p_oidperiodo NUMBER) IS
      SELECT documentos.oid_cabe,
             documentos.oid_regi,
             documentos.val_ejer_docu_inte,
             (tipos.num_docu_cont_inte_inic + documentos.num_secu) num_docu_cont_inte_calc
        FROM (
              -- select que obtiene los numeros de documento iniciales
              SELECT DISTINCT doc.tido_oid_tipo_docu,
                               fds.val_ulti_nume_docu_inte num_docu_cont_inte_inic
                FROM ped_solic_cabec       psc,
                      fac_docum_conta_cabec doc,
                      fac_docum_subac       fds
               WHERE psc.oid_soli_cabe = doc.soca_oid_soli_cabe
                 AND psc.perd_oid_peri = p_oidperiodo
                 AND doc.tido_oid_tipo_docu = fds.tido_oid_tipo_docu
                 AND fds.sbac_oid_sbac = 888
                 AND psc.fec_fact = to_date(p_fechafacturacion, 'dd/mm/yyyy')
                 AND nvl(psc.ind_inte_lari_gene, 0) = 0 -- Pedidos que no han sido enviados a despacho
                 AND doc.tido_oid_tipo_docu IN (1, 29, 30)
              /*AND EXISTS
              (SELECT NULL
                       FROM int_lar_tipo_solici_pedido_dis lar
                      WHERE lar.tspa_oid_tipo_soli_pais = psc.tspa_oid_tipo_soli_pais)*/
              --GROUP BY doc.tido_oid_tipo_docu
              ) tipos,
             (
              -- select que obtiene los datos de los documentos matriciales y su secuencia
              SELECT con.oid_soli_cabe,
                      con.clie_oid_clie,
                      con.fec_fact,
                      con.num_lote_fact,
                      row_number() over(PARTITION BY cab.tido_oid_tipo_docu ORDER BY sec.val_secu_ruta_terr, mc.cod_clie, cab.oid_cabe) num_secu,
                      cab.oid_cabe,
                      cab.tido_oid_tipo_docu,
                      cab.num_docu_cont_inte,
                      nvl(cab.val_ejer_docu_inte, to_char(cab.fec_fact, 'yy')) val_ejer_docu_inte,
                      ven.oid_regi
                FROM ped_solic_cabec       con,
                      mae_clien             mc,
                      ped_solic_cabec_secue sec,
                      fac_docum_conta_cabec cab,
                      fac_regis_unico_venta ven
               WHERE con.oid_soli_cabe = sec.soca_oid_soli_cabe
                 AND con.clie_oid_clie = mc.oid_clie
                 AND con.perd_oid_peri = p_oidperiodo
                 AND con.fec_fact = to_date(p_fechafacturacion, 'dd/mm/yyyy')
                 AND nvl(con.ind_inte_lari_gene, 0) = 0 -- Pedidos que no han sido enviados a despacho
                 AND cab.tido_oid_tipo_docu IN (1, 29, 30)
                    /*AND EXISTS (SELECT NULL
                     FROM int_lar_tipo_solici_pedido_dis l
                    WHERE l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais)*/
                 AND cab.soca_oid_soli_cabe = con.oid_soli_cabe
                 AND cab.oid_cabe = ven.dcca_oid_cabe
               ORDER BY cab.tido_oid_tipo_docu,
                         sec.val_secu_ruta_terr,
                         mc.cod_clie,
                         cab.oid_cabe) documentos
       WHERE documentos.tido_oid_tipo_docu = tipos.tido_oid_tipo_docu;
  
    CURSOR c_numerosinternos(p_oidperiodo NUMBER) IS
      SELECT cab.tido_oid_tipo_docu,
             cab.sbac_oid_sbac,
             MAX(cab.num_docu_cont_inte) val_ulti_nume_docu_cont_inte
        FROM ped_solic_cabec       con,
             ped_solic_cabec_secue sec,
             fac_docum_conta_cabec cab
       WHERE con.oid_soli_cabe = sec.soca_oid_soli_cabe
         AND con.perd_oid_peri = p_oidperiodo
         AND con.fec_fact = to_date(p_fechafacturacion, 'dd/mm/yyyy')
         AND nvl(con.ind_inte_lari_gene, 0) = 0 -- Pedidos que no han sido enviados a despacho
            /*AND EXISTS (SELECT NULL
             FROM int_lar_tipo_solici_pedido_dis l
            WHERE l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais)*/
         AND cab.tido_oid_tipo_docu IN (1, 29, 30)
         AND cab.soca_oid_soli_cabe = con.oid_soli_cabe
       GROUP BY cab.tido_oid_tipo_docu,
                cab.sbac_oid_sbac;
  
    TYPE t_oid_cabe IS TABLE OF fac_docum_conta_cabec.oid_cabe%TYPE;
    TYPE t_oid_regi IS TABLE OF fac_regis_unico_venta.oid_regi%TYPE;
    TYPE t_val_ejer_docu_inte IS TABLE OF fac_docum_conta_cabec.val_ejer_docu_inte%TYPE;
    TYPE t_num_docu_cont_inte IS TABLE OF fac_docum_conta_cabec.num_docu_cont_inte%TYPE;
    TYPE t_tido_oid_tipo_docu IS TABLE OF fac_docum_conta_cabec.tido_oid_tipo_docu%TYPE;
    TYPE t_sbac_oid_sbac IS TABLE OF fac_docum_conta_cabec.sbac_oid_sbac%TYPE;
  
    v_oid_cabe           t_oid_cabe;
    v_oid_regi           t_oid_regi;
    v_val_ejer_docu_inte t_val_ejer_docu_inte;
    v_num_docu_cont_inte t_num_docu_cont_inte;
    v_tido_oid_tipo_docu t_tido_oid_tipo_docu;
    v_sbac_oid_sbac      t_sbac_oid_sbac;
  
    rows NATURAL := 500; -- Numero de filas a procesar a la vez
    i    BINARY_INTEGER := 0;
  
    l_oidpais               NUMBER;
    l_oidperiodo            NUMBER;
    l_numerolotefacturacion ped_solic_cabec.num_lote_fact%TYPE;
  
    l_oid_docu_suba           fac_docum_subac.oid_docu_suba%TYPE;
    l_val_seri_docu_lega      fac_docum_subac.val_seri_docu_lega%TYPE;
    l_val_ulti_nume_docu_inte fac_docum_subac.val_ulti_nume_docu_inte%TYPE;
  
    lv_indactdoculega VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                    'indActDocuLega');
  
    l_actualiza VARCHAR2(100) := nvl(imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                  'actualizaInternoNC2'),
                                     'N');
  
    l_actualizand VARCHAR2(100) := nvl(imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                    'actualizaInternoND'),
                                       'N');
  
    l_actualizand3 VARCHAR2(100) := nvl(imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                     'actualizaInternoND3'),
                                        'N');
  
    l_actualizaseriend VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                     'actualizaSerieND');
  
    l_nuevosecuepedid VARCHAR2(100) := nvl(imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                        'nuevoSecuePedid'),
                                           'N');
  
    l_docinte NUMBER;
  
    l_oidcabe NUMBER;
  
    CURSOR c_ncboleta IS
      SELECT a.oid_cabe
        FROM fac_docum_conta_cabec a
       WHERE a.tido_oid_tipo_docu = 31
         AND a.fec_fact = to_date(p_fechafacturacion, 'dd/mm/yyyy')
      --and a.oid_cabe>(select VAL_ULTI_OID_CABE from imp_contr_docum_conta where cod_tipo_docu_cont='020')
       ORDER BY a.oid_cabe;
  
    r_ncboleta c_ncboleta%ROWTYPE;
  
    CURSOR c_ncfactura IS
      SELECT a.oid_cabe
        FROM fac_docum_conta_cabec a
       WHERE a.tido_oid_tipo_docu = 32
         AND a.fec_fact = to_date(p_fechafacturacion, 'dd/mm/yyyy')
      --and a.oid_cabe>(select VAL_ULTI_OID_CABE from imp_contr_docum_conta where cod_tipo_docu_cont='021')
       ORDER BY a.oid_cabe;
  
    r_ncfactura c_ncfactura%ROWTYPE;
  
    CURSOR c_ndebito IS
      SELECT a.oid_cabe
        FROM fac_docum_conta_cabec a
       WHERE a.tido_oid_tipo_docu = 34
         AND a.fec_fact = to_date(p_fechafacturacion, 'dd/mm/yyyy')
      --and a.oid_cabe>(select VAL_ULTI_OID_CABE from imp_contr_docum_conta where cod_tipo_docu_cont='023')
       ORDER BY a.oid_cabe;
  
    r_ndebito c_ndebito%ROWTYPE;
  
    CURSOR c_ndebitofac IS
      SELECT a.oid_cabe
        FROM fac_docum_conta_cabec a
       WHERE a.tido_oid_tipo_docu = 40
         AND a.fec_fact = to_date(p_fechafacturacion, 'dd/mm/yyyy')
      --and a.oid_cabe>(select VAL_ULTI_OID_CABE from imp_contr_docum_conta where cod_tipo_docu_cont='023')
       ORDER BY a.oid_cabe;
  
    r_ndebitofac c_ndebitofac%ROWTYPE;
  
  BEGIN
  
    IF l_nuevosecuepedid = 'S' THEN
      ped_pr_actua_secue_pedid3(p_codigopais,
                                p_codigoperiodo,
                                p_fechafacturacion);
      RETURN;
    END IF;
  
    -- Obtenemos el OID del pais y del periodo
    l_oidpais    := gen_pkg_gener.gen_fn_devuelve_id_pais(p_codigopais);
    l_oidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(p_codigoperiodo);
  
    IF l_actualizaseriend IS NOT NULL THEN
    
      UPDATE fac_docum_conta_cabec a
         SET val_seri_docu_lega = l_actualizaseriend,
             tido_oid_tipo_docu = 40
       WHERE fec_fact = to_date(p_fechafacturacion, 'dd/mm/yyyy')
         AND tido_oid_tipo_docu = 34
         AND EXISTS (SELECT y.fec_fact
                FROM ped_solic_cabec       x,
                     ped_solic_cabec       y,
                     fac_docum_conta_cabec z
               WHERE x.oid_soli_cabe = a.soca_oid_soli_cabe
                 AND x.soca_oid_docu_refe = y.oid_soli_cabe
                 AND y.oid_soli_cabe = z.soca_oid_soli_cabe
                 AND z.tido_oid_tipo_docu = 1);
    
      UPDATE fac_regis_unico_venta
         SET val_seri_docu_lega = l_actualizaseriend,
             tido_oid_tipo_docu = 40
       WHERE dcca_oid_cabe IN
             (SELECT a.oid_cabe
                FROM fac_docum_conta_cabec a
               WHERE fec_fact = to_date(p_fechafacturacion, 'dd/mm/yyyy')
                 AND tido_oid_tipo_docu = 34
                 AND EXISTS
               (SELECT y.fec_fact
                        FROM ped_solic_cabec       x,
                             ped_solic_cabec       y,
                             fac_docum_conta_cabec z
                       WHERE x.oid_soli_cabe = a.soca_oid_soli_cabe
                         AND x.soca_oid_docu_refe = y.oid_soli_cabe
                         AND y.oid_soli_cabe = z.soca_oid_soli_cabe
                         AND z.tido_oid_tipo_docu = 1));
    
    END IF;
  
    IF l_actualiza = 'S' THEN
    
      UPDATE fac_regis_unico_venta a
         SET a.tido_oid_tipo_docu = 29
       WHERE dcca_oid_cabe IN
             (SELECT oid_cabe
                FROM fac_docum_conta_cabec a
               WHERE a.perd_oid_peri = l_oidperiodo
                    --AND a.fec_fact = lv_fecfact
                 AND a.fec_fact = to_date(p_fechafacturacion, 'dd/mm/yyyy')
                 AND a.tido_oid_tipo_docu = 9);
    
      UPDATE fac_docum_conta_cabec a
         SET a.tido_oid_tipo_docu = 29
       WHERE a.perd_oid_peri = l_oidperiodo
         AND a.fec_fact = to_date(p_fechafacturacion, 'dd/mm/yyyy')
         AND a.tido_oid_tipo_docu = 9;
    
      UPDATE ped_solic_cabec a
         SET a.tido_oid_tipo_docu = 29
       WHERE a.perd_oid_peri = l_oidperiodo
         AND a.fec_fact = to_date(p_fechafacturacion, 'dd/mm/yyyy')
         AND a.tido_oid_tipo_docu = 9;
    
    END IF;
  
    OPEN c_documentos(l_oidperiodo);
    LOOP
      FETCH c_documentos BULK COLLECT
        INTO v_oid_cabe,
             v_oid_regi,
             v_val_ejer_docu_inte,
             v_num_docu_cont_inte LIMIT rows;
      EXIT WHEN v_oid_cabe.count = 0;
    
      -- Actualizamos el documento interno en FAC_DOCUM_CONTA_CABEC
      FORALL j IN 1 .. v_oid_cabe.count
        UPDATE fac_docum_conta_cabec cab
           SET cab.num_docu_cont_inte = v_num_docu_cont_inte(j),
               cab.val_ejer_docu_inte = v_val_ejer_docu_inte(j),
               cab.num_docu_lega      = decode(lv_indactdoculega,
                                               '1',
                                               v_num_docu_cont_inte(j),
                                               cab.num_docu_lega)
         WHERE cab.oid_cabe = v_oid_cabe(j);
    
      -- Actualizamos el documento interno en FAC_REGIS_UNICO_VENTA
      FORALL k IN 1 .. v_oid_regi.count
        UPDATE fac_regis_unico_venta ven
           SET ven.num_docu_cont_inte = v_num_docu_cont_inte(k),
               ven.val_ejer_docu_inte = v_val_ejer_docu_inte(k),
               ven.num_cont_docu_lega = decode(lv_indactdoculega,
                                               '1',
                                               v_num_docu_cont_inte(k),
                                               ven.num_cont_docu_lega),
               ven.val_nume_docu_lega = decode(lv_indactdoculega,
                                               '1',
                                               v_num_docu_cont_inte(k),
                                               ven.num_cont_docu_lega)
         WHERE ven.oid_regi = v_oid_regi(k);
    
    END LOOP;
    CLOSE c_documentos;
  
    -- 4 - ACTUALIZACION DE LAS SEMILLAS DE LOS DOCUMENTOS CONTABLES
    OPEN c_numerosinternos(l_oidperiodo);
    LOOP
      FETCH c_numerosinternos BULK COLLECT
        INTO v_tido_oid_tipo_docu,
             v_sbac_oid_sbac,
             v_num_docu_cont_inte LIMIT rows;
      EXIT WHEN v_tido_oid_tipo_docu.count = 0;
    
      FOR i IN 1 .. v_tido_oid_tipo_docu.count
      LOOP
      
        -- Obtenemos el valor actual de la semilla en base al tipo de documento
        SELECT fds.oid_docu_suba,
               fds.val_seri_docu_lega,
               fds.val_ulti_nume_docu_inte
          INTO l_oid_docu_suba,
               l_val_seri_docu_lega,
               l_val_ulti_nume_docu_inte
          FROM fac_docum_subac fds
         WHERE fds.sbac_oid_sbac = v_sbac_oid_sbac(i)
           AND fds.tido_oid_tipo_docu = v_tido_oid_tipo_docu(i)
           AND fds.pais_oid_pais = l_oidpais
           AND fds.val_ulti_ejer_docu_inte =
               (SELECT MAX(x.val_ulti_ejer_docu_inte)
                  FROM fac_docum_subac x
                 WHERE x.tido_oid_tipo_docu = v_tido_oid_tipo_docu(i)
                   AND x.sbac_oid_sbac = fds.sbac_oid_sbac);
      
        -- Si el valor de la semilla es diferente al ultimo numero
        -- de documento interno hacemos la actualizacion correspondiente
        IF l_val_ulti_nume_docu_inte != v_num_docu_cont_inte(i) THEN
        
          UPDATE fac_docum_subac fds
             SET fds.val_ulti_nume_docu_inte = v_num_docu_cont_inte(i)
           WHERE fds.oid_docu_suba = l_oid_docu_suba;
        
        END IF;
      
        -- Finalmente actualizamos aquellos registros que tengan
        -- el valor de la serie en null con el valor correcto
        -- primero en el registro de ventas ...
        UPDATE fac_regis_unico_venta x
           SET x.val_seri_docu_lega = l_val_seri_docu_lega
         WHERE EXISTS
         (SELECT ven.oid_regi
                  FROM fac_docum_conta_cabec cab,
                       fac_regis_unico_venta ven
                 WHERE cab.oid_cabe = ven.dcca_oid_cabe
                   AND cab.perd_oid_peri = l_oidperiodo
                   AND cab.pais_oid_pais = l_oidpais
                   AND cab.fec_fact =
                       to_date(p_fechafacturacion, 'dd/mm/yyyy')
                   AND cab.tido_oid_tipo_docu = v_tido_oid_tipo_docu(i)
                   AND cab.val_seri_docu_lega IS NULL
                   AND ven.oid_regi = x.oid_regi);
      
        -- y luego en los documentos contables
        UPDATE fac_docum_conta_cabec y
           SET y.val_seri_docu_lega = l_val_seri_docu_lega
         WHERE EXISTS
         (SELECT cab.oid_cabe
                  FROM fac_docum_conta_cabec cab
                 WHERE cab.perd_oid_peri = l_oidperiodo
                   AND cab.pais_oid_pais = l_oidpais
                   AND cab.fec_fact =
                       to_date(p_fechafacturacion, 'dd/mm/yyyy')
                   AND cab.tido_oid_tipo_docu = v_tido_oid_tipo_docu(i)
                   AND cab.val_seri_docu_lega IS NULL
                   AND cab.oid_cabe = y.oid_cabe);
      
      END LOOP;
    END LOOP;
    CLOSE c_numerosinternos;
  
    IF l_actualiza = 'S' THEN
    
      -- Notas de Crédito Boleta
      SELECT MAX(val_ulti_nume_docu_inte)
        INTO l_docinte
        FROM fac_docum_subac a
       WHERE a.tido_oid_tipo_docu = 31
         AND a.sbac_oid_sbac = 888;
    
      OPEN c_ncboleta;
      LOOP
        FETCH c_ncboleta
          INTO r_ncboleta;
        EXIT WHEN c_ncboleta%NOTFOUND;
      
        l_docinte := l_docinte + 1;
      
        UPDATE fac_docum_conta_cabec a
           SET a.num_docu_cont_inte = l_docinte,
               a.num_docu_lega      = l_docinte
         WHERE a.oid_cabe = r_ncboleta.oid_cabe;
      
        UPDATE fac_regis_unico_venta a
           SET a.num_docu_cont_inte = l_docinte,
               a.val_nume_docu_lega = l_docinte
         WHERE a.dcca_oid_cabe = r_ncboleta.oid_cabe;
      
        l_oidcabe := r_ncboleta.oid_cabe;
      
        imp_pr_obtie_infor_docle_refe1(l_oidcabe);
      
      END LOOP;
      CLOSE c_ncboleta;
    
      UPDATE fac_docum_subac a
         SET a.val_ulti_nume_docu_inte = l_docinte
       WHERE tido_oid_tipo_docu = 31;
    
      --update imp_contr_docum_conta a set a.val_ante_oid_cabe=a.val_ulti_oid_cabe where a.cod_tipo_docu_cont='020';
    
      --update imp_contr_docum_conta a set a.val_ulti_oid_cabe = r_ncBoleta.Oid_Cabe
      --where a.cod_tipo_docu_cont='020';
    
      -- Notas de Crédito Factura
      SELECT MAX(val_ulti_nume_docu_inte)
        INTO l_docinte
        FROM fac_docum_subac a
       WHERE a.tido_oid_tipo_docu = 32
         AND a.sbac_oid_sbac = 888;
    
      OPEN c_ncfactura;
      LOOP
        FETCH c_ncfactura
          INTO r_ncfactura;
        EXIT WHEN c_ncfactura%NOTFOUND;
      
        l_docinte := l_docinte + 1;
      
        UPDATE fac_docum_conta_cabec a
           SET a.num_docu_cont_inte = l_docinte,
               a.num_docu_lega      = l_docinte
         WHERE a.oid_cabe = r_ncfactura.oid_cabe;
      
        UPDATE fac_regis_unico_venta a
           SET a.num_docu_cont_inte = l_docinte,
               a.val_nume_docu_lega = l_docinte
         WHERE a.dcca_oid_cabe = r_ncfactura.oid_cabe;
      
        l_oidcabe := r_ncfactura.oid_cabe;
      
        imp_pr_obtie_infor_docle_refe1(l_oidcabe);
      
      END LOOP;
      CLOSE c_ncfactura;
    
      UPDATE fac_docum_subac a
         SET a.val_ulti_nume_docu_inte = l_docinte
       WHERE tido_oid_tipo_docu = 32;
    
      --update imp_contr_docum_conta a set a.val_ante_oid_cabe=a.val_ulti_oid_cabe where a.cod_tipo_docu_cont='021';
    
      --update imp_contr_docum_conta a set a.val_ulti_oid_cabe = l_oidcabe
      --where a.cod_tipo_docu_cont='021';
    
    END IF;
  
    IF l_actualizand = 'S' THEN
    
      -- Notas de Débito
      SELECT MAX(val_ulti_nume_docu_inte)
        INTO l_docinte
        FROM fac_docum_subac a
       WHERE a.tido_oid_tipo_docu = 34
         AND a.sbac_oid_sbac = 888;
    
      OPEN c_ndebito;
      LOOP
        FETCH c_ndebito
          INTO r_ndebito;
        EXIT WHEN c_ndebito%NOTFOUND;
      
        l_docinte := l_docinte + 1;
      
        UPDATE fac_docum_conta_cabec a
           SET a.num_docu_cont_inte = l_docinte,
               a.num_docu_lega      = l_docinte
         WHERE a.oid_cabe = r_ndebito.oid_cabe;
      
        UPDATE fac_regis_unico_venta a
           SET a.num_docu_cont_inte = l_docinte,
               a.val_nume_docu_lega = l_docinte
         WHERE a.dcca_oid_cabe = r_ndebito.oid_cabe;
      
        l_oidcabe := r_ndebito.oid_cabe;
      
        imp_pr_obtie_infor_docle_refe1(l_oidcabe);
      
      END LOOP;
      CLOSE c_ndebito;
    
      UPDATE fac_docum_subac a
         SET a.val_ulti_nume_docu_inte = l_docinte
       WHERE tido_oid_tipo_docu = 34;
    
      -- Notas de Débito Factura
      SELECT MAX(val_ulti_nume_docu_inte)
        INTO l_docinte
        FROM fac_docum_subac a
       WHERE a.tido_oid_tipo_docu = 40
         AND a.sbac_oid_sbac = 888;
    
      OPEN c_ndebitofac;
      LOOP
        FETCH c_ndebitofac
          INTO r_ndebitofac;
        EXIT WHEN c_ndebitofac%NOTFOUND;
      
        l_docinte := l_docinte + 1;
      
        UPDATE fac_docum_conta_cabec a
           SET a.num_docu_cont_inte = l_docinte,
               a.num_docu_lega      = l_docinte
         WHERE a.oid_cabe = r_ndebitofac.oid_cabe;
      
        UPDATE fac_regis_unico_venta a
           SET a.num_docu_cont_inte = l_docinte,
               a.val_nume_docu_lega = l_docinte
         WHERE a.dcca_oid_cabe = r_ndebitofac.oid_cabe;
      
        l_oidcabe := r_ndebitofac.oid_cabe;
      
        imp_pr_obtie_infor_docle_refe1(l_oidcabe);
      
      END LOOP;
      CLOSE c_ndebitofac;
    
      UPDATE fac_docum_subac a
         SET a.val_ulti_nume_docu_inte = l_docinte
       WHERE tido_oid_tipo_docu = 40;
    
    END IF;
  
    IF l_actualizand3 = 'S' THEN
    
      -- Notas de Débito
      SELECT MAX(val_ulti_nume_docu_inte)
        INTO l_docinte
        FROM fac_docum_subac a
       WHERE a.tido_oid_tipo_docu = 1
         AND a.sbac_oid_sbac = 888;
    
      OPEN c_ndebito;
      LOOP
        FETCH c_ndebito
          INTO r_ndebito;
        EXIT WHEN c_ndebito%NOTFOUND;
      
        l_docinte := l_docinte + 1;
      
        UPDATE fac_docum_conta_cabec a
           SET a.num_docu_cont_inte = l_docinte,
               a.num_docu_lega      = l_docinte
         WHERE a.oid_cabe = r_ndebito.oid_cabe;
      
        UPDATE fac_regis_unico_venta a
           SET a.num_docu_cont_inte = l_docinte,
               a.val_nume_docu_lega = l_docinte
         WHERE a.dcca_oid_cabe = r_ndebito.oid_cabe;
      
        l_oidcabe := r_ndebito.oid_cabe;
      
        imp_pr_obtie_infor_docle_refe1(l_oidcabe);
      
      END LOOP;
      CLOSE c_ndebito;
    
      UPDATE fac_docum_subac a
         SET a.val_ulti_nume_docu_inte = l_docinte
       WHERE tido_oid_tipo_docu = 1;
    
    END IF;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ped_pr_actua_secue_pedid2: ' || ls_sqlerrm);
    
  END ped_pr_actua_secue_pedid2;
  /*******************************************************************************
    Descripcion         : Recalcula la secuencia de los pedidos que aun no han sido
                          enviados a despacho de tal manera que permita la ejecucion
                          de mas de un lote de GP4 a GP5, esta actualizacion incluye
                          ademas la regeneracion de los documentos internos y la
                          actualizacion del numero de lote de facturacion al valor
                          maximo.
    Fecha Creacion      : 16/04/2010
    Parametros Entrada:
        p_codigoPais       : Codigo del pais a procesar
        p_codigoPeriodo    : Codigo de periodo
        p_fechaFacturacion : Fecha de Facturacion
    Autor               : Carlos Hurtado Ramirez
  ***************************************************************************/
  PROCEDURE ped_pr_actua_secue_pedid3
  (
    p_codigopais       IN VARCHAR2,
    p_codigoperiodo    IN VARCHAR2,
    p_fechafacturacion IN VARCHAR2
  ) IS
  
    l_oidpais       NUMBER;
    l_oidperiodo    NUMBER;
    l_oidperiodosig NUMBER;
    l_oidperiodoant NUMBER;
    l_oidperiodosig2 NUMBER;
    l_oidperiodoant2 NUMBER;
    l_oidperiodoant3 NUMBER;
  
    l_codperiodosig VARCHAR2(10);
    l_codperiodoant VARCHAR2(10);

    l_codperiodosig2 VARCHAR2(10);
    l_codperiodoant2 VARCHAR2(10);
    l_codperiodoant3 VARCHAR2(10);
  
    l_numerolotefacturacion ped_solic_cabec.num_lote_fact%TYPE;
  
    l_oid_docu_suba           fac_docum_subac.oid_docu_suba%TYPE;
    l_val_seri_docu_lega      fac_docum_subac.val_seri_docu_lega%TYPE;
    l_val_ulti_nume_docu_inte fac_docum_subac.val_ulti_nume_docu_inte%TYPE;
    l_val_llave               fac_docum_subac.val_llav%TYPE;
    l_val_nume_auto           fac_docum_subac.val_nume_auto%TYPE;
    l_fecha_limite            fac_docum_subac.fec_limi%TYPE;
  
    l_actualizaseriend VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                     'actualizaSerieND');
    l_actualizaNCF VARCHAR2(100) := nvl(imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS','actualizaNCF'),'S');
  
    l_oidcabe NUMBER;
  
  
    CURSOR c_boleta
    (
      sig NUMBER,
      ant NUMBER,
      act NUMBER
    ) IS
      SELECT a.oid_cabe
        FROM fac_docum_conta_cabec a,
             ped_solic_cabec_secue b
       WHERE a.tido_oid_tipo_docu = 29
         AND a.soca_oid_soli_cabe = b.soca_oid_soli_cabe(+)
         AND a.fec_fact <= to_date(p_fechafacturacion, 'dd/mm/yyyy')
         AND a.fec_fact >= to_date(p_fechafacturacion, 'dd/mm/yyyy') - 7
         AND a.num_docu_cont_inte IS NULL
         AND a.perd_oid_peri IN (sig, ant, act)
      --and a.oid_cabe>(select VAL_ULTI_OID_CABE from imp_contr_docum_conta where cod_tipo_docu_cont='020')
       ORDER BY a.fec_fact,
                nvl(b.val_secu_ruta_terr, '0'),
                a.oid_cabe;
  
    r_boleta c_boleta%ROWTYPE;
  
    CURSOR c_boletapremio
    (
      sig NUMBER,
      ant NUMBER,
      act NUMBER
    ) IS
      SELECT a.oid_cabe
        FROM fac_docum_conta_cabec a,
             ped_solic_cabec_secue b
       WHERE a.tido_oid_tipo_docu = 30
         AND a.soca_oid_soli_cabe = b.soca_oid_soli_cabe(+)
         AND a.fec_fact <= to_date(p_fechafacturacion, 'dd/mm/yyyy')
         AND a.fec_fact >= to_date(p_fechafacturacion, 'dd/mm/yyyy') - 7
         AND a.num_docu_cont_inte IS NULL
         AND a.perd_oid_peri IN (sig, ant, act)
      --and a.oid_cabe>(select VAL_ULTI_OID_CABE from imp_contr_docum_conta where cod_tipo_docu_cont='020')
       ORDER BY a.fec_fact,
                nvl(b.val_secu_ruta_terr, '0'),
                a.oid_cabe;
  
    r_boletapremio c_boletapremio%ROWTYPE;
  
    CURSOR c_factura
    (
      sig NUMBER,
      ant NUMBER,
      act NUMBER
    ) IS
      SELECT a.oid_cabe
        FROM fac_docum_conta_cabec a,
             ped_solic_cabec_secue b
       WHERE a.tido_oid_tipo_docu = 1
         AND a.soca_oid_soli_cabe = b.soca_oid_soli_cabe(+)
         AND a.fec_fact <= to_date(p_fechafacturacion, 'dd/mm/yyyy')
         AND a.fec_fact >= to_date(p_fechafacturacion, 'dd/mm/yyyy') - 7
         AND a.num_docu_cont_inte IS NULL
         AND a.perd_oid_peri IN (sig, ant, act)
      --and a.oid_cabe>(select VAL_ULTI_OID_CABE from imp_contr_docum_conta where cod_tipo_docu_cont='020')
       ORDER BY a.fec_fact,
                nvl(b.val_secu_ruta_terr, '0'),
                a.oid_cabe;
  
    r_factura c_factura%ROWTYPE;
  
    CURSOR c_ncboleta
    (
      sig NUMBER,
      ant NUMBER,
      act NUMBER,
      sig2 NUMBER,
      ant2 NUMBER,
      ant3 NUMBER
    ) IS
      SELECT a.oid_cabe
        FROM fac_docum_conta_cabec a
       WHERE a.tido_oid_tipo_docu = 31
         AND a.fec_fact <= to_date(p_fechafacturacion, 'dd/mm/yyyy')
         AND a.fec_fact >= to_date(p_fechafacturacion, 'dd/mm/yyyy') - 7
         AND a.num_docu_cont_inte IS NULL
         AND a.perd_oid_peri IN (sig, ant, act, sig2, ant2, ant3)
      --and a.oid_cabe>(select VAL_ULTI_OID_CABE from imp_contr_docum_conta where cod_tipo_docu_cont='020')
       ORDER BY a.oid_cabe;
  
    r_ncboleta c_ncboleta%ROWTYPE;
  
    CURSOR c_ncfactura
     IS
      SELECT a.oid_cabe
        FROM fac_docum_conta_cabec a, ped_solic_cabec b
       WHERE a.tido_oid_tipo_docu = 32
         and a.soca_oid_soli_cabe=b.oid_soli_cabe
         AND a.fec_fact <= to_date(p_fechafacturacion, 'dd/mm/yyyy')
         AND a.fec_fact >= to_date(p_fechafacturacion, 'dd/mm/yyyy') - 7
         AND a.num_docu_cont_inte IS NULL
         --AND a.perd_oid_peri IN (sig, ant, act, sig2, ant2, ant3)
      --and a.oid_cabe>(select VAL_ULTI_OID_CABE from imp_contr_docum_conta where cod_tipo_docu_cont='021')
       ORDER BY b.almc_oid_alma, a.oid_cabe;
  
    r_ncfactura c_ncfactura%ROWTYPE;
  
    CURSOR c_ndebito
    (
      sig NUMBER,
      ant NUMBER,
      act NUMBER,
      sig2 NUMBER,
      ant2 NUMBER,
      ant3 NUMBER
    ) IS
      SELECT a.oid_cabe
        FROM fac_docum_conta_cabec a
       WHERE a.tido_oid_tipo_docu = 34
         AND a.fec_fact <= to_date(p_fechafacturacion, 'dd/mm/yyyy')
         AND a.fec_fact >= to_date(p_fechafacturacion, 'dd/mm/yyyy') - 7
         AND a.num_docu_cont_inte IS NULL
         AND a.perd_oid_peri IN (sig, ant, act,sig2, ant2, ant3)
      --and a.oid_cabe>(select VAL_ULTI_OID_CABE from imp_contr_docum_conta where cod_tipo_docu_cont='023')
       ORDER BY a.oid_cabe;
  
    r_ndebito c_ndebito%ROWTYPE;
  
    CURSOR c_ndebitofac
    (
      sig NUMBER,
      ant NUMBER,
      act NUMBER
    ) IS
      SELECT a.oid_cabe
        FROM fac_docum_conta_cabec a
       WHERE a.tido_oid_tipo_docu = 40
         AND a.fec_fact <= to_date(p_fechafacturacion, 'dd/mm/yyyy')
         AND a.fec_fact >= to_date(p_fechafacturacion, 'dd/mm/yyyy') - 7
         AND a.num_docu_cont_inte IS NULL
         AND a.perd_oid_peri IN (sig, ant, act)
      --and a.oid_cabe>(select VAL_ULTI_OID_CABE from imp_contr_docum_conta where cod_tipo_docu_cont='023')
       ORDER BY a.oid_cabe;
  
    r_ndebitofac c_ndebitofac%ROWTYPE;
  
  BEGIN
  
    -- Obtenemos el OID del pais y del periodo
    l_oidpais    := gen_pkg_gener.gen_fn_devuelve_id_pais(p_codigopais);
    l_oidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(p_codigoperiodo);
  
    l_codperiodosig := gen_fn_calcu_perio(p_codigoperiodo, 1);
    l_codperiodoant := gen_fn_calcu_perio(p_codigoperiodo, -1);
  
    l_codperiodosig2 := gen_fn_calcu_perio(p_codigoperiodo, 2);
    l_codperiodoant2 := gen_fn_calcu_perio(p_codigoperiodo, -2);

    l_codperiodoant3 := gen_fn_calcu_perio(p_codigoperiodo, -3);
  
    l_oidperiodoant := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(l_codperiodoant);
    l_oidperiodosig := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(l_codperiodosig);

    l_oidperiodoant2 := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(l_codperiodoant2);
    l_oidperiodosig2 := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(l_codperiodosig2);

    l_oidperiodoant3 := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(l_codperiodoant3);
  
    IF l_actualizaseriend IS NOT NULL THEN
    
      UPDATE fac_regis_unico_venta
         SET val_seri_docu_lega = l_actualizaseriend,
             tido_oid_tipo_docu = 40
       WHERE dcca_oid_cabe IN
             (SELECT a.oid_cabe
                FROM fac_docum_conta_cabec a
               WHERE fec_fact = to_date(p_fechafacturacion, 'dd/mm/yyyy')
                 AND tido_oid_tipo_docu = 34
                 AND EXISTS
               (SELECT y.fec_fact
                        FROM ped_solic_cabec       x,
                             ped_solic_cabec       y,
                             fac_docum_conta_cabec z
                       WHERE x.oid_soli_cabe = a.soca_oid_soli_cabe
                         AND x.soca_oid_docu_refe = y.oid_soli_cabe
                         AND y.oid_soli_cabe = z.soca_oid_soli_cabe
                         AND z.tido_oid_tipo_docu = 1));

      UPDATE fac_docum_conta_cabec a
         SET val_seri_docu_lega = l_actualizaseriend,
             tido_oid_tipo_docu = 40
       WHERE fec_fact = to_date(p_fechafacturacion, 'dd/mm/yyyy')
         AND tido_oid_tipo_docu = 34
         AND EXISTS (SELECT y.fec_fact
                FROM ped_solic_cabec       x,
                     ped_solic_cabec       y,
                     fac_docum_conta_cabec z
               WHERE x.oid_soli_cabe = a.soca_oid_soli_cabe
                 AND x.soca_oid_docu_refe = y.oid_soli_cabe
                 AND y.oid_soli_cabe = z.soca_oid_soli_cabe
                 AND z.tido_oid_tipo_docu = 1);
    

    
    END IF;
  
    UPDATE fac_regis_unico_venta a
       SET a.tido_oid_tipo_docu = 29
     WHERE dcca_oid_cabe IN
           (SELECT oid_cabe
              FROM fac_docum_conta_cabec a
             WHERE a.perd_oid_peri = l_oidperiodo
                  --AND a.fec_fact = lv_fecfact
               AND a.fec_fact = to_date(p_fechafacturacion, 'dd/mm/yyyy')
               AND a.tido_oid_tipo_docu = 9);
  
    UPDATE fac_docum_conta_cabec a
       SET a.tido_oid_tipo_docu = 29
     WHERE a.perd_oid_peri = l_oidperiodo
       AND a.fec_fact = to_date(p_fechafacturacion, 'dd/mm/yyyy')
       AND a.tido_oid_tipo_docu = 9;
  
    UPDATE ped_solic_cabec a
       SET a.tido_oid_tipo_docu = 29
     WHERE a.perd_oid_peri = l_oidperiodo
       AND a.fec_fact = to_date(p_fechafacturacion, 'dd/mm/yyyy')
       AND a.tido_oid_tipo_docu = 9;
  
    -- Boletas de Venta

    SELECT MAX(val_ulti_nume_docu_inte), max(a.val_seri_docu_lega), max(a.val_llav), max(a.val_nume_auto), max(a.fec_limi)
      INTO l_val_ulti_nume_docu_inte, l_val_seri_docu_lega, l_val_llave, l_val_nume_auto, l_fecha_limite
      FROM fac_docum_subac a
     WHERE a.tido_oid_tipo_docu = 29
       AND a.sbac_oid_sbac = 888;
  
    OPEN c_boleta(l_oidperiodosig, l_oidperiodoant, l_oidperiodo);
    LOOP
      FETCH c_boleta
        INTO r_boleta;
      EXIT WHEN c_boleta%NOTFOUND;
    
      l_val_ulti_nume_docu_inte := l_val_ulti_nume_docu_inte + 1;
    
      UPDATE fac_docum_conta_cabec a
         SET a.num_docu_cont_inte = l_val_ulti_nume_docu_inte,
             a.num_docu_lega      = l_val_ulti_nume_docu_inte,
             a.val_seri_docu_lega = l_val_seri_docu_lega,
             a.val_llav           = l_val_llave,
             a.val_nume_auto      = l_val_nume_auto,
             a.fec_limi           = l_fecha_limite
       WHERE a.oid_cabe = r_boleta.oid_cabe;
    
      UPDATE fac_regis_unico_venta a
         SET a.num_docu_cont_inte = l_val_ulti_nume_docu_inte,
             a.val_nume_docu_lega = l_val_ulti_nume_docu_inte,
             a.val_seri_docu_lega = l_val_seri_docu_lega,
             a.val_llav           = l_val_llave,
             a.val_nume_auto      = l_val_nume_auto,
             a.fec_limi           = l_fecha_limite
       WHERE a.dcca_oid_cabe = r_boleta.oid_cabe;
    
    END LOOP;
    CLOSE c_boleta;
  
    UPDATE fac_docum_subac a
       SET a.val_ulti_nume_docu_inte = l_val_ulti_nume_docu_inte
     WHERE tido_oid_tipo_docu = 29;
  
    -- Boletas de Premio
    SELECT MAX(val_ulti_nume_docu_inte), max(a.val_seri_docu_lega), max(a.val_llav), max(a.val_nume_auto), max(a.fec_limi)
      INTO l_val_ulti_nume_docu_inte, l_val_seri_docu_lega, l_val_llave, l_val_nume_auto, l_fecha_limite
      FROM fac_docum_subac a
     WHERE a.tido_oid_tipo_docu = 30
       AND a.sbac_oid_sbac = 888;
  
    OPEN c_boletapremio(l_oidperiodosig, l_oidperiodoant, l_oidperiodo);
    LOOP
      FETCH c_boletapremio
        INTO r_boletapremio;
      EXIT WHEN c_boletapremio%NOTFOUND;
    
      l_val_ulti_nume_docu_inte := l_val_ulti_nume_docu_inte + 1;
    
      UPDATE fac_docum_conta_cabec a
         SET a.num_docu_cont_inte = l_val_ulti_nume_docu_inte,
             a.num_docu_lega      = l_val_ulti_nume_docu_inte,
             a.val_seri_docu_lega = l_val_seri_docu_lega,
             a.val_llav           = l_val_llave,
             a.val_nume_auto      = l_val_nume_auto,
             a.fec_limi           = l_fecha_limite
       WHERE a.oid_cabe = r_boletapremio.oid_cabe;
    
      UPDATE fac_regis_unico_venta a
         SET a.num_docu_cont_inte = l_val_ulti_nume_docu_inte,
             a.val_nume_docu_lega = l_val_ulti_nume_docu_inte,
             a.val_seri_docu_lega = l_val_seri_docu_lega,
             a.val_llav           = l_val_llave,
             a.val_nume_auto      = l_val_nume_auto,
             a.fec_limi           = l_fecha_limite
       WHERE a.dcca_oid_cabe = r_boletapremio.oid_cabe;
    
    END LOOP;
    CLOSE c_boletapremio;
  
    UPDATE fac_docum_subac a
       SET a.val_ulti_nume_docu_inte = l_val_ulti_nume_docu_inte
     WHERE tido_oid_tipo_docu = 30;
  
    -- Facturas
    SELECT MAX(val_ulti_nume_docu_inte), max(a.val_seri_docu_lega), max(a.val_llav), max(a.val_nume_auto), max(a.fec_limi)
      INTO l_val_ulti_nume_docu_inte, l_val_seri_docu_lega, l_val_llave, l_val_nume_auto, l_fecha_limite
      FROM fac_docum_subac a
     WHERE a.tido_oid_tipo_docu = 1
       AND a.sbac_oid_sbac = 888;
  
    OPEN c_factura(l_oidperiodosig, l_oidperiodoant, l_oidperiodo);
    LOOP
      FETCH c_factura
        INTO r_factura;
      EXIT WHEN c_factura%NOTFOUND;
    
      l_val_ulti_nume_docu_inte := l_val_ulti_nume_docu_inte + 1;
    
      UPDATE fac_docum_conta_cabec a
         SET a.num_docu_cont_inte = l_val_ulti_nume_docu_inte,
             a.num_docu_lega      = l_val_ulti_nume_docu_inte,
             a.val_seri_docu_lega = l_val_seri_docu_lega,
             a.val_llav           = l_val_llave,
             a.val_nume_auto      = l_val_nume_auto,
             a.fec_limi           = l_fecha_limite
       WHERE a.oid_cabe = r_factura.oid_cabe;
    
      UPDATE fac_regis_unico_venta a
         SET a.num_docu_cont_inte = l_val_ulti_nume_docu_inte,
             a.val_nume_docu_lega = l_val_ulti_nume_docu_inte,
             a.val_seri_docu_lega = l_val_seri_docu_lega,
             a.val_llav           = l_val_llave,
             a.val_nume_auto      = l_val_nume_auto,
             a.fec_limi           = l_fecha_limite
      WHERE a.dcca_oid_cabe = r_factura.oid_cabe;
    
    END LOOP;
    CLOSE c_factura;
  
    UPDATE fac_docum_subac a
       SET a.val_ulti_nume_docu_inte = l_val_ulti_nume_docu_inte
     WHERE tido_oid_tipo_docu = 1;
  
    -- Notas de Crédito Boleta
    SELECT MAX(val_ulti_nume_docu_inte), max(a.val_seri_docu_lega), max(a.val_llav), max(a.val_nume_auto), max(a.fec_limi)
      INTO l_val_ulti_nume_docu_inte, l_val_seri_docu_lega, l_val_llave, l_val_nume_auto, l_fecha_limite
      FROM fac_docum_subac a
     WHERE a.tido_oid_tipo_docu = 31
       AND a.sbac_oid_sbac = 888;
  
    OPEN c_ncboleta(l_oidperiodosig, l_oidperiodoant, l_oidperiodo, l_oidperiodosig2, l_oidperiodoant2, l_oidperiodoant3);
    LOOP
      FETCH c_ncboleta
        INTO r_ncboleta;
      EXIT WHEN c_ncboleta%NOTFOUND;
    
      l_val_ulti_nume_docu_inte := l_val_ulti_nume_docu_inte + 1;
    
      UPDATE fac_docum_conta_cabec a
         SET a.num_docu_cont_inte = l_val_ulti_nume_docu_inte,
             a.num_docu_lega      = l_val_ulti_nume_docu_inte,
             a.val_seri_docu_lega = l_val_seri_docu_lega,
             a.val_llav           = l_val_llave,
             a.val_nume_auto      = l_val_nume_auto,
             a.fec_limi           = l_fecha_limite
       WHERE a.oid_cabe = r_ncboleta.oid_cabe;
    
      UPDATE fac_regis_unico_venta a
         SET a.num_docu_cont_inte = l_val_ulti_nume_docu_inte,
             a.val_nume_docu_lega = l_val_ulti_nume_docu_inte,
             a.val_seri_docu_lega = l_val_seri_docu_lega,
             a.val_llav           = l_val_llave,
             a.val_nume_auto      = l_val_nume_auto,
             a.fec_limi           = l_fecha_limite
       WHERE a.dcca_oid_cabe = r_ncboleta.oid_cabe;
    
      l_oidcabe := r_ncboleta.oid_cabe;
    
      imp_pr_obtie_infor_docle_refe1(l_oidcabe);
    
    END LOOP;
    CLOSE c_ncboleta;
  
    UPDATE fac_docum_subac a
       SET a.val_ulti_nume_docu_inte = l_val_ulti_nume_docu_inte
     WHERE tido_oid_tipo_docu = 31;
  
    --update imp_contr_docum_conta a set a.val_ante_oid_cabe=a.val_ulti_oid_cabe where a.cod_tipo_docu_cont='020';
  
    --update imp_contr_docum_conta a set a.val_ulti_oid_cabe = r_ncBoleta.Oid_Cabe
    --where a.cod_tipo_docu_cont='020';
  
    -- Notas de Crédito Factura
    SELECT MAX(val_ulti_nume_docu_inte), max(a.val_seri_docu_lega), max(a.val_llav), max(a.val_nume_auto), max(a.fec_limi)
      INTO l_val_ulti_nume_docu_inte, l_val_seri_docu_lega, l_val_llave, l_val_nume_auto, l_fecha_limite
      FROM fac_docum_subac a
     WHERE a.tido_oid_tipo_docu = 32
       AND a.sbac_oid_sbac = 888;
  
    OPEN c_ncfactura;
    LOOP
      FETCH c_ncfactura
        INTO r_ncfactura;
      EXIT WHEN c_ncfactura%NOTFOUND;
    
      l_val_ulti_nume_docu_inte := l_val_ulti_nume_docu_inte + 1;
    
      if l_actualizaNCF='S' then
      
      UPDATE fac_docum_conta_cabec a
         SET a.num_docu_cont_inte = l_val_ulti_nume_docu_inte,
             a.num_docu_lega      = l_val_ulti_nume_docu_inte,
             a.val_seri_docu_lega = l_val_seri_docu_lega,
             a.val_llav           = l_val_llave,
             a.val_nume_auto      = l_val_nume_auto,
             a.fec_limi           = l_fecha_limite
       WHERE a.oid_cabe = r_ncfactura.oid_cabe;
    
      UPDATE fac_regis_unico_venta a
         SET a.num_docu_cont_inte = l_val_ulti_nume_docu_inte,
             a.val_nume_docu_lega = l_val_ulti_nume_docu_inte,
             a.val_seri_docu_lega = l_val_seri_docu_lega,
             a.val_llav           = l_val_llave,
             a.val_nume_auto      = l_val_nume_auto,
             a.fec_limi           = l_fecha_limite
       WHERE a.dcca_oid_cabe = r_ncfactura.oid_cabe;
       
       end if;
    
      l_oidcabe := r_ncfactura.oid_cabe;
    
      imp_pr_obtie_infor_docle_refe1(l_oidcabe);
    
    END LOOP;
    CLOSE c_ncfactura;
  
      if l_actualizaNCF='S' then
          UPDATE fac_docum_subac a
             SET a.val_ulti_nume_docu_inte = l_val_ulti_nume_docu_inte
           WHERE tido_oid_tipo_docu = 32;
      end if;     
  
    --update imp_contr_docum_conta a set a.val_ante_oid_cabe=a.val_ulti_oid_cabe where a.cod_tipo_docu_cont='021';
  
    --update imp_contr_docum_conta a set a.val_ulti_oid_cabe = l_oidcabe
    --where a.cod_tipo_docu_cont='021';
  
    -- Notas de Débito
    SELECT MAX(val_ulti_nume_docu_inte), max(a.val_seri_docu_lega), max(a.val_llav), max(a.val_nume_auto), max(a.fec_limi)
      INTO l_val_ulti_nume_docu_inte, l_val_seri_docu_lega, l_val_llave, l_val_nume_auto, l_fecha_limite
      FROM fac_docum_subac a
     WHERE a.tido_oid_tipo_docu = 34
       AND a.sbac_oid_sbac = 888;
  
    OPEN c_ndebito(l_oidperiodosig, l_oidperiodoant, l_oidperiodo, l_oidperiodosig2, l_oidperiodoant2, l_oidperiodoant3);
    LOOP
      FETCH c_ndebito
        INTO r_ndebito;
      EXIT WHEN c_ndebito%NOTFOUND;
    
      l_val_ulti_nume_docu_inte := l_val_ulti_nume_docu_inte + 1;
    
      UPDATE fac_docum_conta_cabec a
         SET a.num_docu_cont_inte = l_val_ulti_nume_docu_inte,
             a.num_docu_lega      = l_val_ulti_nume_docu_inte,
             a.val_seri_docu_lega = l_val_seri_docu_lega,
             a.val_llav           = l_val_llave,
             a.val_nume_auto      = l_val_nume_auto,
             a.fec_limi           = l_fecha_limite
       WHERE a.oid_cabe = r_ndebito.oid_cabe;
    
      UPDATE fac_regis_unico_venta a
         SET a.num_docu_cont_inte = l_val_ulti_nume_docu_inte,
             a.val_nume_docu_lega = l_val_ulti_nume_docu_inte,
             a.val_seri_docu_lega = l_val_seri_docu_lega,
             a.val_llav           = l_val_llave,
             a.val_nume_auto      = l_val_nume_auto,
             a.fec_limi           = l_fecha_limite
       WHERE a.dcca_oid_cabe = r_ndebito.oid_cabe;
    
      l_oidcabe := r_ndebito.oid_cabe;
    
      imp_pr_obtie_infor_docle_refe1(l_oidcabe);
    
    END LOOP;
    CLOSE c_ndebito;
  
    UPDATE fac_docum_subac a
       SET a.val_ulti_nume_docu_inte = l_val_ulti_nume_docu_inte
     WHERE tido_oid_tipo_docu = 34;
  
    -- Notas de Débito Factura
    SELECT MAX(val_ulti_nume_docu_inte), max(a.val_seri_docu_lega), max(a.val_llav), max(a.val_nume_auto), max(a.fec_limi)
      INTO l_val_ulti_nume_docu_inte, l_val_seri_docu_lega, l_val_llave, l_val_nume_auto, l_fecha_limite
      FROM fac_docum_subac a
     WHERE a.tido_oid_tipo_docu = 40
       AND a.sbac_oid_sbac = 888;
  
    OPEN c_ndebitofac(l_oidperiodosig, l_oidperiodoant, l_oidperiodo);
    LOOP
      FETCH c_ndebitofac
        INTO r_ndebitofac;
      EXIT WHEN c_ndebitofac%NOTFOUND;
    
      l_val_ulti_nume_docu_inte := l_val_ulti_nume_docu_inte + 1;
    
      UPDATE fac_docum_conta_cabec a
         SET a.num_docu_cont_inte = l_val_ulti_nume_docu_inte,
             a.num_docu_lega      = l_val_ulti_nume_docu_inte,
             a.val_seri_docu_lega = l_val_seri_docu_lega,
             a.val_llav           = l_val_llave,
             a.val_nume_auto      = l_val_nume_auto,
             a.fec_limi           = l_fecha_limite
       WHERE a.oid_cabe = r_ndebitofac.oid_cabe;
    
      UPDATE fac_regis_unico_venta a
         SET a.num_docu_cont_inte = l_val_ulti_nume_docu_inte,
             a.val_nume_docu_lega = l_val_ulti_nume_docu_inte,
             a.val_seri_docu_lega = l_val_seri_docu_lega,
             a.val_llav           = l_val_llave,
             a.val_nume_auto      = l_val_nume_auto,
             a.fec_limi           = l_fecha_limite
       WHERE a.dcca_oid_cabe = r_ndebitofac.oid_cabe;
    
      l_oidcabe := r_ndebitofac.oid_cabe;
    
      imp_pr_obtie_infor_docle_refe1(l_oidcabe);
    
    END LOOP;
    CLOSE c_ndebitofac;
  
    UPDATE fac_docum_subac a
       SET a.val_ulti_nume_docu_inte = l_val_ulti_nume_docu_inte
     WHERE tido_oid_tipo_docu = 40;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ped_pr_actua_secue_pedid3: ' || ls_sqlerrm);
    
  END ped_pr_actua_secue_pedid3;
  /*******************************************************************************
    Descripcion         : Recalcula la secuencia de los pedidos que aun no han sido
                          enviados a despacho de tal manera que permita la ejecucion
                          de mas de un lote de GP4 a GP5, esta actualizacion incluye
                          ademas la regeneracion de los documentos internos y la
                          actualizacion del numero de lote de facturacion al valor
                          maximo.
    Fecha Creacion      : 16/04/2010
    Parametros Entrada:
        p_codigoPais       : Codigo del pais a procesar
        p_codigoPeriodo    : Codigo de periodo
        p_fechaFacturacion : Fecha de Facturacion
    Autor               : Carlos Hurtado Ramirez
  ***************************************************************************/
  PROCEDURE ped_pr_actua_secue_col
  (
    p_codigopais       IN VARCHAR2,
    p_codigoperiodo    IN VARCHAR2,
    p_fechafacturacion IN VARCHAR2
  ) IS
  
    rows NATURAL := 500; -- Numero de filas a procesar a la vez
    i    BINARY_INTEGER := 0;
  
    l_oidpais               NUMBER;
    l_oidperiodo            NUMBER;
    l_numerolotefacturacion ped_solic_cabec.num_lote_fact%TYPE;
  
    l_oid_docu_suba           fac_docum_subac.oid_docu_suba%TYPE;
    l_val_seri_docu_lega      fac_docum_subac.val_seri_docu_lega%TYPE;
    l_val_ulti_nume_docu_inte fac_docum_subac.val_ulti_nume_docu_inte%TYPE;
  
  BEGIN
  
    -- Obtenemos el OID del pais y del periodo
    l_oidpais    := gen_pkg_gener.gen_fn_devuelve_id_pais(p_codigopais);
    l_oidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(p_codigoperiodo);
  
    UPDATE ped_solic_cabec_secue a
       SET a.val_secu_ruta_terr = ped_fn_obtie_secue_colom(a.soca_oid_soli_cabe)
     WHERE a.soca_oid_soli_cabe IN
           (SELECT oid_soli_cabe
              FROM ped_solic_cabec con
             WHERE con.perd_oid_peri = l_oidperiodo
               AND con.fec_fact = to_date(p_fechafacturacion, 'dd/mm/yyyy')
               AND nvl(con.ind_inte_lari_gene, 0) = 0 -- Pedidos que no han sido enviados a despacho
               AND EXISTS (SELECT NULL
                      FROM int_lar_tipo_solici_pedido_dis l
                     WHERE l.tspa_oid_tipo_soli_pais =
                           con.tspa_oid_tipo_soli_pais));
  
    UPDATE ped_solic_cabec_secue a
       SET a.val_secu_ruta_terr = 'AAAAAAAA'
     WHERE a.soca_oid_soli_cabe IN
           (SELECT oid_soli_cabe
              FROM ped_solic_cabec      con,
                   mae_clien_tipo_subti c,
                   mae_clien_clasi      d,
                   lar_tipo_clien_vip   e
             WHERE con.perd_oid_peri = l_oidperiodo
               AND con.fec_fact = to_date(p_fechafacturacion, 'dd/mm/yyyy')
               AND nvl(con.ind_inte_lari_gene, 0) = 0 -- Pedidos que no han sido enviados a despacho
               AND EXISTS
             (SELECT NULL
                      FROM int_lar_tipo_solici_pedido_dis l
                     WHERE l.tspa_oid_tipo_soli_pais =
                           con.tspa_oid_tipo_soli_pais)
               AND con.clie_oid_clie = c.clie_oid_clie
               AND c.oid_clie_tipo_subt = d.ctsu_oid_clie_tipo_subt
               AND d.tccl_oid_tipo_clasi = e.oid_tipo_clas
               AND d.clas_oid_clas = e.oid_clas);
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ped_pr_actua_secue_col: ' || ls_sqlerrm);
    
  END ped_pr_actua_secue_col;
  /*******************************************************************************
    Descripcion         : Reemplazo de productos importados por nacionales
    Fecha Creacion      : 01/06/2010
    Parametros Entrada:
        p_codigoPeriodo    : Codigo de periodo
    Autor               : Jesse James Rios Franco
  ***************************************************************************/

  PROCEDURE ped_pr_proce_reemp_produ_impor(p_codigoperiodo VARCHAR2) AS
  
    CURSOR c_cursor IS
      SELECT cod_sap_ppal,
             cod_sap_alte
        FROM pre_prod_alter_ice;
  
    TYPE interfazrec IS RECORD(
      cod_sap_ppal pre_prod_alter_ice.cod_sap_ppal%TYPE,
      cod_sap_alte pre_prod_alter_ice.cod_sap_alte%TYPE);
  
    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
  
    w_filas NUMBER := 1000;
  
  BEGIN
  
    OPEN c_cursor;
  
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          UPDATE pre_ofert_detal a
             SET a.prod_oid_prod =
                 (SELECT oid_prod
                    FROM mae_produ
                   WHERE cod_sap = interfazrecord(x).cod_sap_alte)
           WHERE a.ofer_oid_ofer IN
                 (SELECT oid_ofer
                    FROM pre_ofert
                   WHERE mfca_oid_cabe =
                         (SELECT oid_cabe
                            FROM pre_matri_factu_cabec x,
                                 cra_perio             y,
                                 seg_perio_corpo       z
                           WHERE x.perd_oid_peri = y.oid_peri
                             AND y.peri_oid_peri = z.oid_peri
                             AND z.cod_peri = p_codigoperiodo))
             AND a.prod_oid_prod =
                 (SELECT oid_prod
                    FROM mae_produ
                   WHERE cod_sap = interfazrecord(x).cod_sap_ppal);
        END LOOP;
      END IF;
      EXIT WHEN c_cursor%NOTFOUND;
    END LOOP;
  
    CLOSE c_cursor;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'PED_PR_PROCE_REEMP_PRODU_IMPOR: ' ||
                              ls_sqlerrm);
  END ped_pr_proce_reemp_produ_impor;

  /*******************************************************************************
    Descripcion         : Proceso de Historico de Pedidos
    Fecha Creacion      : 13/08/2010
    Autor               : Jesse James Rios Franco
  ***************************************************************************/
  PROCEDURE ped_pr_proce_histo_pedid IS
  
    CURSOR c_cursor IS
      SELECT xx.cod_peri,
             xx.cod_regi,
             xx.cod_zona,
             xx.ped_puro,
             nvl(yy.pedidos_con_eoferta, 0) ped_eoferta,
             nvl(yy.unidades_eoferta, 0) uni_eoferta
        FROM (SELECT a.cod_peri,
                     a.cod_regi,
                     a.cod_zona,
                     COUNT(a.cod_clie) ped_puro
                FROM int_solic_conso_cabec a
               WHERE a.ind_rece_web = 1
                 AND a.ind_rece_dd = 0
                 AND a.ind_rece_digi = 0
                 AND a.ind_ocs_proc = 1
                 AND a.ind_proc_gp2 = 1
               GROUP BY a.cod_peri,
                        a.cod_regi,
                        a.cod_zona) xx,
             (SELECT a.cod_peri,
                     a.cod_regi,
                     a.cod_zona,
                     COUNT(DISTINCT a.cod_clie) pedidos_con_eoferta,
                     SUM(b.val_unid_dem) unidades_eoferta
                FROM int_solic_conso_cabec a,
                     int_solic_conso_detal b
               WHERE a.ind_rece_web = 1
                 AND a.ind_ocs_proc = 1
                 AND a.ind_proc_gp2 = 1
                 AND a.cod_clie = b.cod_clie
                 AND a.num_lote = b.num_lote
                 AND a.cod_peri = b.cod_peri
                 AND b.acce_oid_acce =
                     (SELECT val_param
                        FROM sto_param_gener_occrr
                       WHERE cod_para = 'STO_ACCE_WEB')
               GROUP BY a.cod_peri,
                        a.cod_regi,
                        a.cod_zona) yy
       WHERE xx.cod_peri = yy.cod_peri(+)
         AND xx.cod_regi = yy.cod_regi(+)
         AND xx.cod_zona = yy.cod_zona(+)
       ORDER BY 1,
                2,
                3;
  
    TYPE interfazrec IS RECORD(
      cod_peri    ped_estad_pedid.cod_peri%TYPE,
      cod_regi    ped_estad_pedid.cod_regi%TYPE,
      cod_zona    ped_estad_pedid.cod_zona%TYPE,
      ped_puro    ped_estad_pedid.ped_puro%TYPE,
      ped_eoferta ped_estad_pedid.ped_entr_ofer%TYPE,
      uni_eoferta ped_estad_pedid.uni_entr_ofer%TYPE);
  
    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
  
    w_filas NUMBER := 5000;
    cont    NUMBER;
  BEGIN
  
    OPEN c_cursor;
  
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          SELECT COUNT(1)
            INTO cont
            FROM ped_estad_pedid ep
           WHERE ep.cod_peri = interfazrecord(x).cod_peri
             AND ep.cod_regi = interfazrecord(x).cod_regi
             AND ep.cod_zona = interfazrecord(x).cod_zona;
        
          IF cont >= 0 THEN
            DELETE FROM ped_estad_pedid ep
             WHERE ep.cod_peri = interfazrecord(x).cod_peri
               AND ep.cod_regi = interfazrecord(x).cod_regi
               AND ep.cod_zona = interfazrecord(x).cod_zona;
          END IF;
        
          INSERT INTO ped_estad_pedid
            (cod_peri,
             cod_regi,
             cod_zona,
             ped_puro,
             ped_entr_ofer,
             uni_entr_ofer)
          VALUES
            (interfazrecord(x).cod_peri,
             interfazrecord(x).cod_regi,
             interfazrecord(x).cod_zona,
             interfazrecord(x).ped_puro,
             interfazrecord(x).ped_eoferta,
             interfazrecord(x).uni_eoferta);
        
        END LOOP;
      END IF;
      EXIT WHEN c_cursor%NOTFOUND;
    END LOOP;
  
    CLOSE c_cursor;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'PED_PR_PROCE_HISTO_PEDID: ' || ls_sqlerrm);
  END ped_pr_proce_histo_pedid;

  /*******************************************************************************
    Descripcion       : Inserta en la tabla Pedidos a Chequear los pedidos con
                        origen de chequeo NR
    Fecha Creacion    : 15/09/2010
    Autor             : Carlos Diaz Valverde
    Parametros        :
                ps_Cod_Pais      : Codigo de Pais
  ***************************************************************************/
  PROCEDURE ped_pr_proce_nivel_riesg(ps_cod_pais VARCHAR2) AS
  BEGIN
  
    -- Codigo de Origen Unidad Administrativa
    v_codorigen := 'NR';
  
    INSERT INTO ped_pedid_chequ
      (cod_pais,
       cod_tipo_cheq,
       oid_pedi_cheq,
       cod_orig_cheq,
       ind_envi_yobe,
       cod_peri,
       fec_fact,
       cod_regi,
       cod_zona,
       cod_secc,
       cod_terr,
       cod_clie,
       val_nume_soli,
       val_text)
      SELECT DISTINCT ps_cod_pais,
                      nach.cod_tipo_cheq,
                      con.oid_soli_cabe,
                      v_codorigen,
                      0,
                      gen_pkg_gener.gen_fn_devuelve_des_perio(con.perd_oid_peri),
                      con.fec_fact,
                      (SELECT zr.cod_regi
                         FROM zon_zona  zz,
                              zon_regio zr
                        WHERE zz.oid_zona = con.zzon_oid_zona
                          AND zr.oid_regi = zz.zorg_oid_regi),
                      (SELECT zz.cod_zona
                         FROM zon_zona zz
                        WHERE zz.oid_zona = con.zzon_oid_zona),
                      (SELECT nvl(zs.cod_secc, '')
                         FROM zon_terri_admin zta,
                              zon_terri       zt,
                              zon_secci       zs,
                              zon_zona        zz,
                              zon_regio       zr
                        WHERE zta.oid_terr_admi = con.ztad_oid_terr_admi
                          AND zs.oid_secc = zta.zscc_oid_secc
                          AND zz.oid_zona = zs.zzon_oid_zona
                          AND zr.oid_regi = zz.zorg_oid_regi
                          AND zta.terr_oid_terr = zt.oid_terr),
                      (SELECT nvl(zt.cod_terr, '')
                         FROM zon_terri_admin zta,
                              zon_terri       zt
                        WHERE zta.oid_terr_admi = con.ztad_oid_terr_admi
                          AND zta.terr_oid_terr = zt.oid_terr),
                      cli.cod_clie,
                      con.val_nume_soli,
                      --nach.val_text
                      decode(gen_pkg_gener.gen_fn_param_pais(ps_cod_pais,
                                                             'PED',
                                                             '019'),
                             1,
                             ped_pkg_proce.ped_fn_obtie_nries(cli.oid_clie,
                                                              nach.cod_nive_ries,
                                                              gen_pkg_gener.gen_fn_devuelve_des_perio(con.perd_oid_peri),
                                                              ps_cod_pais),
                             '')
        FROM ped_solic_cabec       con,
             mae_clien             cli,
             mae_clien_datos_adici mcda,
             ped_nivel_riesg_chequ nach
       WHERE con.perd_oid_peri IN
             (SELECT c.oid_peri
                FROM cra_perio       c,
                     seg_perio_corpo d
               WHERE c.peri_oid_peri = d.oid_peri
                 AND d.cod_peri IN (SELECT cod_peri
                                      FROM bas_ctrl_fact b
                                     WHERE b.sta_camp = 0
                                       AND b.ind_camp_act = 1))
         AND con.tspa_oid_tipo_soli_pais IN
             (SELECT tspa_oid_tipo_soli_pais
                FROM int_lar_tipo_solici_pedido_dis)
         AND con.fec_fact = (SELECT fec_proc
                               FROM bas_ctrl_fact b
                              WHERE b.sta_camp = 0
                                AND b.ind_camp_act = 1)
         AND con.ind_inte_lari_gene = 0
         AND con.num_unid_aten_tota > 0
         AND con.clie_oid_clie = cli.oid_clie
         AND cli.oid_clie = mcda.clie_oid_clie
         AND mcda.niri_oid_nive_ries = nach.cod_nive_ries
         AND con.oid_soli_cabe NOT IN
             (SELECT oid_pedi_cheq
                FROM ped_pedid_chequ
               WHERE ind_envi_yobe = 0);
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'PED_PR_PROCE_NIVEL_RIESG: ' || ls_sqlerrm);
  END ped_pr_proce_nivel_riesg;

  /*******************************************************************************
    Descripcion       : Realiza el cierre del Periodo
    Fecha Creacion    : 24/03/2011
    Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE ped_pr_cierr_perio
  (
    pscodigopais          VARCHAR2,
    pscodigoperiodo       VARCHAR2,
    psusuario             VARCHAR2,
    pscodigotipodocumento VARCHAR2
  ) AS
  
    CURSOR curinsconsolcab IS
      SELECT cab.*
        FROM int_solic_conso_cabec cab
       WHERE cab.cod_pais = pscodigopais
         AND cab.cod_peri = pscodigoperiodo;
  
    TYPE solic_cab_tab_t IS TABLE OF int_solic_conso_cabec%ROWTYPE INDEX BY BINARY_INTEGER;
    sol_cab_tab solic_cab_tab_t;
  
    CURSOR curinsconsoldet IS
      SELECT det.*
        FROM int_solic_conso_detal det
       WHERE det.cod_pais = pscodigopais
         AND det.cod_peri = pscodigoperiodo;
  
    TYPE solic_det_tab_t IS TABLE OF int_solic_conso_detal%ROWTYPE INDEX BY BINARY_INTEGER;
    sol_det_tab solic_det_tab_t; -- In-memory table
  
    TYPE t_rowid IS TABLE OF ROWID INDEX BY BINARY_INTEGER;
    v_rowid t_rowid;
  
    CURSOR cursto
    (
      psdocumentocabecera VARCHAR2,
      psdocumentodetalle  VARCHAR2
    ) IS
      SELECT sto.*
        FROM sto_docum_digit sto
       WHERE sto.cod_pais = pscodigopais
         AND sto.cod_peri = pscodigoperiodo
         AND sto.cod_tipo_docu IN (psdocumentocabecera, psdocumentodetalle);
  
    TYPE solic_sto_t IS TABLE OF sto_docum_digit%ROWTYPE INDEX BY BINARY_INTEGER;
    sol_sto_tab solic_sto_t; -- In-memory table
  
    rows           NATURAL := 1000; -- Number of rows to process at a time
    i              BINARY_INTEGER := 0;
    totaldetalles  NUMBER := 0;
    totalcabeceras NUMBER := 0;
  
    pstipoclasi              sto_param_gener_occrr.val_param%TYPE;
    lscodigodocumentodetalle sto_tipo_docum_digit.cod_tipo_docu%TYPE;
  
    inddeshabilitarindices bas_param_pais.val_para%TYPE;
  
    /*BLOQUEO DE REGIONES*/
  
    lsperiodosiguiente seg_perio_corpo.cod_peri%TYPE;
    lnperiodosiguiente cra_perio.oid_peri%TYPE;
  
    indbloqueoregioncierre bas_param_pais.val_para%TYPE;
  
    CURSOR curbloqregion IS
      SELECT sbco_seq.nextval cod_bloq,
             oid_regi
        FROM zon_regio;
  
    TYPE t_cod_bloq IS TABLE OF sto_bloqu_contr.cod_bloq%TYPE;
    TYPE t_oid_regi IS TABLE OF sto_bloqu_contr.oid_regi%TYPE;
  
    v_cod_bloq t_cod_bloq;
    v_oid_regi t_oid_regi;
  
  BEGIN
  
    inddeshabilitarindices := gen_pkg_gener.gen_fn_param_pais(pscodigopais,
                                                              'OCR',
                                                              '000');
  
    indbloqueoregioncierre := gen_pkg_gener.gen_fn_param_pais(pscodigopais,
                                                              'PED',
                                                              '002');
  
    lscodigodocumentodetalle := sto_pkg_gener.sto_fn_devue_codig_docum_detal(pscodigopais,
                                                                             pscodigotipodocumento);
  
    pstipoclasi := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                        'STO_TIPO_CLASI_PEDID');
  
    IF indbloqueoregioncierre = 'S' THEN
    
      lsperiodosiguiente := gen_pkg_gener.gen_fn_perio_nsigu(pscodigopais,
                                                             pscodigoperiodo,
                                                             1);
    
      lnperiodosiguiente := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(lsperiodosiguiente);
    
      OPEN curbloqregion;
      LOOP
        FETCH curbloqregion BULK COLLECT
          INTO v_cod_bloq,
               v_oid_regi LIMIT rows;
      
        IF v_cod_bloq.count > 0 THEN
        
          FORALL j IN 1 .. v_cod_bloq.count
            UPDATE sto_bloqu_contr b
               SET b.ind_acti = '0'
             WHERE b.oid_regi = v_oid_regi(j)
               AND b.oid_peri = lnperiodosiguiente
               AND b.cod_tipo_docu = 'OCC'
               AND b.val_moti_bloq = 'C';
          FORALL j IN 1 .. v_cod_bloq.count
            INSERT INTO sto_bloqu_contr
              (cod_bloq,
               oid_regi,
               oid_peri,
               usu_crea,
               fec_crea,
               des_obse,
               ind_acti,
               ind_tipo_bloq,
               cod_tipo_docu,
               val_moti_bloq)
            VALUES
              (v_cod_bloq(j),
               v_oid_regi(j),
               lnperiodosiguiente,
               psusuario,
               SYSDATE,
               'Bloqueo automatico en cierre de campaña',
               '1',
               '0',
               'OCC',
               'C');
        
        END IF;
        EXIT WHEN curbloqregion%NOTFOUND;
      END LOOP;
      CLOSE curbloqregion;
    
    END IF;
  
    ped_pkg_proce.ped_pr_proce_histo_pedid;
  
    SELECT COUNT(ca.cod_pais)
      INTO totalcabeceras
      FROM int_solic_conso_cabec ca
     WHERE ca.cod_pais = pscodigopais
       AND ca.cod_peri <> pscodigoperiodo;
  
    OPEN curinsconsolcab;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinsconsolcab BULK COLLECT
        INTO sol_cab_tab LIMIT rows;
      EXIT WHEN sol_cab_tab.count = 0;
    
      -- Bulk bind of data in memory table...
      FORALL i IN sol_cab_tab.first .. sol_cab_tab.last
        INSERT INTO ped_histo_solic_conso_cabec VALUES sol_cab_tab (i);
    
    END LOOP;
    CLOSE curinsconsolcab;
  
    IF (totalcabeceras > 0) THEN
      BEGIN
      
        SELECT ROWID BULK COLLECT
          INTO v_rowid
          FROM int_solic_conso_cabec ca
         WHERE ca.cod_pais = pscodigopais
           AND ca.cod_peri = pscodigoperiodo
           FOR UPDATE NOWAIT;
      
        IF v_rowid.count > 0 THEN
          FORALL i IN v_rowid.first .. v_rowid.last
            DELETE FROM int_solic_conso_cabec cab
             WHERE cab.rowid = v_rowid(i);
        END IF;
      
      END;
    ELSE
    
      EXECUTE IMMEDIATE 'truncate table int_solic_conso_cabec';
    
    END IF;
  
    SELECT COUNT(ca.cod_pais)
      INTO totaldetalles
      FROM int_solic_conso_detal ca
     WHERE ca.cod_pais = pscodigopais
       AND ca.cod_peri <> pscodigoperiodo;
  
    OPEN curinsconsoldet;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinsconsoldet BULK COLLECT
        INTO sol_det_tab LIMIT rows;
      EXIT WHEN sol_det_tab.count = 0;
    
      -- Bulk bind of data in memory table...
      FORALL i IN sol_det_tab.first .. sol_det_tab.last
        INSERT INTO ped_histo_solic_conso_detal VALUES sol_det_tab (i);
    
    END LOOP;
    CLOSE curinsconsoldet;
  
    IF (totaldetalles > 0) THEN
      SELECT ROWID BULK COLLECT
        INTO v_rowid
        FROM int_solic_conso_detal de
       WHERE de.cod_pais = pscodigopais
         AND de.cod_peri = pscodigoperiodo
         FOR UPDATE NOWAIT;
      IF v_rowid.count > 0 THEN
        FORALL i IN v_rowid.first .. v_rowid.last
          DELETE FROM int_solic_conso_detal cab
           WHERE cab.rowid = v_rowid(i);
      END IF;
    ELSE
      EXECUTE IMMEDIATE 'truncate table int_solic_conso_detal';
    END IF;
  
    /*ELIMINANDO CLASIFICACIONES*/
    DELETE FROM mae_clien_clasi a
     WHERE a.tccl_oid_tipo_clasi IN
           (SELECT oid_tipo_clas
              FROM mae_tipo_clasi_clien
             WHERE cod_tipo_clas = pstipoclasi);
  
    /*CERARNDO CAMPAÑA*/
    UPDATE bas_ctrl_fact t
       SET t.sta_camp     = '1',
           t.ind_camp_act = '0'
     WHERE t.cod_pais = pscodigopais
       AND t.cod_peri = pscodigoperiodo;
  
    UPDATE bas_pais b
       SET b.cod_ulti_peri_proc = pscodigoperiodo
     WHERE cod_pais = pscodigopais;
  
    INSERT INTO bas_ctrl_fact_audi
      (cod_pais,
       cod_peri,
       num_secu,
       fec_proc,
       val_mnt_min_fact,
       val_mnt_min_acept,
       val_mnt_max,
       val_unid_max,
       sta_camp,
       usu_digi,
       fec_digi,
       usu_modi,
       fec_modi,
       cod_marc,
       des_marc,
       cod_cana,
       des_cana,
       val_mnt_min_deud,
       ind_camp_act,
       num_lote)
      (SELECT cod_pais,
              cod_peri,
              bas_seq_ctrl.nextval,
              fec_proc,
              val_mnt_min_fact,
              val_mnt_min_acept,
              val_mnt_max,
              val_unid_max,
              sta_camp,
              nvl(usu_digi, psusuario),
              nvl(fec_digi, SYSDATE),
              psusuario,
              SYSDATE,
              cod_marc,
              des_marc,
              cod_cana,
              des_cana,
              val_mnt_min_deud,
              ind_camp_act,
              num_lote
         FROM bas_ctrl_fact
        WHERE cod_pais = pscodigopais
          AND cod_peri = pscodigoperiodo);
  
    /*PASANDO A HISTORICOS INFORMACION DE STO*/
    IF (inddeshabilitarindices = 'S') THEN
      EXECUTE IMMEDIATE 'ALTER TABLE STO_DETAL_DOCUM_EXCEP DISABLE CONSTRAINT STO_DDEX_DODI_FK';
    
      EXECUTE IMMEDIATE ' ALTER TABLE STO_AUDIT_EXCEP DISABLE CONSTRAINT STO_AUEX_DODI_FK';
    
      EXECUTE IMMEDIATE 'ALTER SESSION SET skip_unusable_indexes=true';
      EXECUTE IMMEDIATE 'ALTER INDEX STO_CABE_DODI_IDX UNUSABLE';
      EXECUTE IMMEDIATE 'ALTER INDEX STO_DODI_CLIE_IDX UNUSABLE';
      EXECUTE IMMEDIATE 'ALTER INDEX STO_DODI_FEDI_IDX UNUSABLE';
      EXECUTE IMMEDIATE 'ALTER INDEX STO_DODI_FEMO_IDX UNUSABLE';
      EXECUTE IMMEDIATE 'ALTER INDEX STO_DODI_PROC_IDX UNUSABLE';
    END IF;
  
    OPEN cursto(pscodigotipodocumento, lscodigodocumentodetalle);
    LOOP
      FETCH cursto BULK COLLECT
        INTO sol_sto_tab LIMIT rows;
    
      EXIT WHEN sol_sto_tab.count = 0;
    
      FORALL i IN sol_sto_tab.first .. sol_sto_tab.last
        INSERT INTO sto_histo_docum_digit VALUES sol_sto_tab (i);
    
      FOR i IN sol_sto_tab.first .. sol_sto_tab.last
      LOOP
        INSERT INTO sto_histo_detal_docum_excep
          SELECT e.*
            FROM sto_detal_docum_excep e
           WHERE e.num_lote = sol_sto_tab(i).num_lote
             AND e.sec_nume_docu = sol_sto_tab(i).sec_nume_docu
             AND e.cod_tipo_docu IN
                 (pscodigotipodocumento, lscodigodocumentodetalle);
      
        INSERT INTO sto_histo_audit_excep
          SELECT a.*
            FROM sto_audit_excep a
           WHERE a.num_lote = sol_sto_tab(i).num_lote
             AND a.sec_nume_docu = sol_sto_tab(i).sec_nume_docu
             AND a.cod_tipo_docu IN
                 (pscodigotipodocumento, lscodigodocumentodetalle);
      
        DELETE FROM sto_audit_excep a
         WHERE a.num_lote = sol_sto_tab(i).num_lote
           AND a.sec_nume_docu = sol_sto_tab(i).sec_nume_docu
           AND a.cod_tipo_docu IN
               (pscodigotipodocumento, lscodigodocumentodetalle);
      
        DELETE FROM sto_detal_docum_excep e
         WHERE e.num_lote = sol_sto_tab(i).num_lote
           AND e.sec_nume_docu = sol_sto_tab(i).sec_nume_docu
           AND e.cod_tipo_docu IN
               (pscodigotipodocumento, lscodigodocumentodetalle);
      
        DELETE FROM sto_docum_digit d
         WHERE d.num_lote = sol_sto_tab(i).num_lote
           AND d.sec_nume_docu = sol_sto_tab(i).sec_nume_docu
           AND d.cod_tipo_docu IN
               (pscodigotipodocumento, lscodigodocumentodetalle);
      END LOOP;
    
    END LOOP;
    CLOSE cursto;
  
    IF (inddeshabilitarindices = 'S') THEN
      --HABILTANDO CONSTRAINTS
      EXECUTE IMMEDIATE 'ALTER TABLE STO_DETAL_DOCUM_EXCEP  ENABLE CONSTRAINT STO_DDEX_DODI_FK';
      EXECUTE IMMEDIATE 'ALTER TABLE STO_AUDIT_EXCEP  ENABLE CONSTRAINT STO_AUEX_DODI_FK';
    
      --HABILITANDO INDICES
      EXECUTE IMMEDIATE 'ALTER INDEX STO_CABE_DODI_IDX REBUILD';
      EXECUTE IMMEDIATE 'ALTER INDEX STO_DODI_CLIE_IDX REBUILD';
      EXECUTE IMMEDIATE 'ALTER INDEX STO_DODI_FEDI_IDX REBUILD';
      EXECUTE IMMEDIATE 'ALTER INDEX STO_DODI_FEMO_IDX REBUILD';
      EXECUTE IMMEDIATE 'ALTER INDEX STO_DODI_PROC_IDX REBUILD';
    
      EXECUTE IMMEDIATE 'TRUNCATE TABLE STO_PROCE_DOCUM_DIGIT';
    END IF;
  
    INSERT INTO sto_histo_proce_histo
      SELECT * FROM sto_histo_proce WHERE fec_ipro < SYSDATE - 21;
  
    INSERT INTO sto_histo_valid_histo
      SELECT * FROM sto_histo_valid WHERE fec_ipro < SYSDATE - 21;
  
    /* Registro de Carga de Historico de lotes YOBEL */
    int_pkg_laris.int_pr_lar_regis_histo_yobel(pscodigopais);
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123, 'PED_PR_CIERR_PERIO: ' || ls_sqlerrm);
  END ped_pr_cierr_perio;

  /***************************************************************************
  Descripcion       : Genera archivo CSV correspondiente al Reporte de Pedidos Bonificaciones
  Fecha Creacion    : 21/07/2011
  Autor             : Carlos Bazalar
  Parametros        :
              pnoidPais : Oid Pais
              psfechaFactura : Fecha de Factura
              pnoidTipoDocumento: Oid Documento
              psTitulo: Titulo
              psDirectorio: Directorio en donde se encuentra el archivo
  ***************************************************************************/
  PROCEDURE ped_pr_gener_repor_bonif_csv
  (
    pscodigopais       VARCHAR2,
    pnoidpais          NUMBER,
    psfechafactura     VARCHAR2,
    pnoidtipodocumento NUMBER,
    psnombrearchivo    VARCHAR2,
    pstitulo           VARCHAR2,
    psdirectorio       OUT VARCHAR2
  ) IS
  
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         NUMBER := 5000;
    v_handle        utl_file.file_type;
    w_desc          VARCHAR2(200);
    lslinea         VARCHAR2(4000);
    lsnombrearchivo VARCHAR2(50);
    lscodigopais    VARCHAR2(3);
  
    CURSOR c_interfaz IS
      SELECT b.des_tipo_docu           tipo_documento,
             a.val_seri_docu_lega      serie,
             a.num_docu_lega,
             a.fec_fact,
             d.cod_clie,
             d.val_ape1,
             d.val_ape2,
             d.val_nom1,
             d.val_nom2,
             a.imp_des1_tota_loca      descuento,
             a.val_prec_cont_tota_loca bonificacion
        FROM fac_docum_conta_cabec a,
             fac_tipo_docum        b,
             mae_clien_direc       c,
             mae_clien             d
       WHERE a.pais_oid_pais = pnoidpais
         AND to_char(a.fec_fact, 'YYYYMM') = psfechafactura
         AND a.perd_oid_peri IN
             (SELECT DISTINCT perd_oid_peri
                FROM fac_docum_conta_cabec dt
               WHERE dt.pais_oid_pais = pnoidpais
                 AND to_char(dt.fec_fact, 'YYYYMM') = psfechafactura)
         AND a.tido_oid_tipo_docu = pnoidtipodocumento
         AND a.tido_oid_tipo_docu = b.oid_tipo_docu
         AND a.cldi_oid_clie_dire = c.oid_clie_dire
         AND c.clie_oid_clie = d.oid_clie
       ORDER BY 4,
                1,
                3;
  
    TYPE interfaztipo IS RECORD(
      tipo_documento fac_tipo_docum.des_tipo_docu%TYPE,
      serie          fac_docum_conta_cabec.val_seri_docu_lega%TYPE,
      num_docu_lega  fac_docum_conta_cabec.num_docu_lega%TYPE,
      fec_fact       fac_docum_conta_cabec.fec_fact%TYPE,
      cod_clie       mae_clien.cod_clie%TYPE,
      val_ape1       mae_clien.val_ape1%TYPE,
      val_ape2       mae_clien.val_ape2%TYPE,
      val_nom1       mae_clien.val_nom1%TYPE,
      val_nom2       mae_clien.val_nom1%TYPE,
      descuento      fac_docum_conta_cabec.imp_des1_tota_loca%TYPE,
      bonificacion   fac_docum_conta_cabec.val_prec_cont_tota_loca%TYPE);
  
    TYPE interfaztab IS TABLE OF interfaztipo;
    interfazrecord interfaztab;
    lbabrirutlfile BOOLEAN;
  
  BEGIN
    lbabrirutlfile := TRUE;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
  
    OPEN c_interfaz;
    LOOP
    
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_repor_oracl(pscodigopais,
                                                     psnombrearchivo,
                                                     '.csv',
                                                     pstitulo,
                                                     lsdirtempo,
                                                     v_handle);
        psdirectorio   := lsdirtempo;
        lbabrirutlfile := FALSE;
      END IF;
    
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x)
                     .tipo_documento || ',' || '=T("' || interfazrecord(x)
                     .serie || '")' || ',' || '=T("' || interfazrecord(x)
                     .num_docu_lega || '")' || ',' || interfazrecord(x)
                     .fec_fact || ',' || '=T("' || interfazrecord(x)
                     .cod_clie || '")' || ',' || interfazrecord(x).val_ape1 || ',' || interfazrecord(x)
                     .val_ape2 || ',' || interfazrecord(x).val_nom1 || ',' || interfazrecord(x)
                     .val_nom2 || ',' || interfazrecord(x).descuento || ',' || interfazrecord(x)
                     .bonificacion;
        
          utl_file.put_line(v_handle, lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
  
    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
    END IF;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR PED_PR_GENER_REPOR_BONIF_CSV' ||
                              ls_sqlerrm);
  END ped_pr_gener_repor_bonif_csv;

  /***************************************************************************
  Descripcion       : Desactiva el proceso de Envio PROL
  Fecha Creacion    : 15/12/2011
  Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE ped_pr_envio_portal_findi IS
  
  BEGIN
  
    UPDATE bas_ctrl_fact c
       SET c.ind_acti_prol = '2'
     WHERE c.ind_acti_prol = '1'
       AND c.ind_camp_act = '1'
       AND c.sta_camp = '0';
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR PED_PR_ENVIO_PORTAL_FINDI: ' ||
                              ls_sqlerrm);
    
  END ped_pr_envio_portal_findi;

  /***************************************************************************
  Descripcion       : Inserta un archivo xml a una tabla
  Fecha Creacion    : 17/01/2012
  Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE ped_pr_inser_archi_prol
  (
    psarchvio         IN VARCHAR2,
    psindicadororigen IN VARCHAR2,
    psoidtemporal     OUT VARCHAR2
  ) IS
  
    vnoid     NUMBER;
    vnoiddet  NUMBER;
    vclob     CLOB;
    vclobtemp CLOB;
  
    vsetiquetainicio  VARCHAR2(100);
    vsetiquetafin     VARCHAR2(100);
    vsaccion          VARCHAR2(1);
    vsoidpedido       VARCHAR2(100);
    vsconsultora      VARCHAR2(100);
    vscampanha        VARCHAR2(100);
    vscuv             VARCHAR2(100);
    vsunidadescuv     VARCHAR2(100);
    vscuvoriginal     VARCHAR2(100);
    vscuvalternativo  VARCHAR2(100);
    vsunidadescuvorig VARCHAR2(100);
    vsunidadescuvalte VARCHAR2(100);
  
    vnoffset                 NUMBER := 1;
    vnoffset2                NUMBER := 1;
    vnposiciondetalleini     NUMBER := 0;
    vnposiciondetallefin     NUMBER := 0;
    vnposicionalternativoini NUMBER := 0;
    vnposicionalternativofin NUMBER := 0;
  
    vbencontrodetalle     BOOLEAN := TRUE;
    vbencontroalternativo BOOLEAN := TRUE;
  
    vnoidcampanha   NUMBER;
    vnoidconsultora NUMBER;
  
    vscodpais   bas_ctrl_fact.cod_pais%TYPE;
    vdfechafac  bas_ctrl_fact.fec_proc%TYPE;
    vsnumlote   bas_ctrl_fact.num_lote%TYPE;
    vscodregion zon_regio.cod_regi%TYPE;
    vscodzona   zon_zona.cod_zona%TYPE;
  
    vsnumerolotesto sto_tipo_docum_digit.num_lote%TYPE;
  
  BEGIN
  
    vnoid := seq_solic_cab.nextval;
  
    INSERT INTO int_solic_cabec
      (oid_cab,
       num_clie,
       tip_soli,
       cod_suba,
       cod_acce,
       tip_desp,
       sta_proc,
       ind_proc,
       xml_pedi_entr,
       xml_pedi_sali)
    VALUES
      (vnoid,
       0,
       'SOC',
       '000',
       '01',
       'N',
       'A',
       psindicadororigen,
       psarchvio,
       empty_clob());
  
    -- Obteniendo el contenido de las etiquetas
    SELECT xml_pedi_entr
      INTO vclob
      FROM int_solic_cabec
     WHERE oid_cab = vnoid;
  
    -- Obteniendo la accion
    vsetiquetainicio := '<accion>';
    vsetiquetafin    := '</accion>';
    vsaccion         := ped_fn_obtie_conte_etiqu(vclob,
                                                 vsetiquetainicio,
                                                 vsetiquetafin);
  
    -- Si la accion es 2 o 3 se obtiene el oid del pedido
    vsetiquetainicio := '<oidsicc>';
    vsetiquetafin    := '</oidsicc>';
    vsoidpedido      := ped_fn_obtie_conte_etiqu(vclob,
                                                 vsetiquetainicio,
                                                 vsetiquetafin);
  
    --Obteniendo la consultora
    vsetiquetainicio := '<consultora>';
    vsetiquetafin    := '</consultora>';
    vsconsultora     := ped_fn_obtie_conte_etiqu(vclob,
                                                 vsetiquetainicio,
                                                 vsetiquetafin);
  
    --Obteniendo la campaña
    vsetiquetainicio := '<campana>';
    vsetiquetafin    := '</campana>';
    vscampanha       := ped_fn_obtie_conte_etiqu(vclob,
                                                 vsetiquetainicio,
                                                 vsetiquetafin);
  
    --Obteniendo el pais, fecha de facturacion y numero de lote
    SELECT a.cod_pais,
           a.fec_proc,
           a.num_lote
      INTO vscodpais,
           vdfechafac,
           vsnumlote
      FROM bas_ctrl_fact a
     WHERE a.cod_peri = vscampanha;
  
    -- Si la accion es 3 ó 2 se Elimina el Pedido
    IF (vsaccion != '1') THEN
    
      vnoidconsultora := gen_pkg_gener.gen_fn_devuelve_id_cliente(vsconsultora);
      vnoidcampanha   := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(vscampanha);
    
      --Se elimina ademas la tabla de alternativos
      DELETE FROM ped_produ_alter
       WHERE perd_oid_peri = vnoidcampanha
         AND clie_oid_clie = vnoidconsultora;
    
    END IF;
  
    -- Actualizando la cabecera del pedido si la accion es 1 ó 2
    IF (vsaccion != '3') THEN
    
      vscodregion := gen_pkg_gener.gen_fn_clien_datos_codig(vsconsultora,
                                                            'COD_REGI');
      vscodzona   := gen_pkg_gener.gen_fn_clien_datos_codig(vsconsultora,
                                                            'COD_ZONA');
      sto_pkg_gener.sto_pr_devue_lote(vscodpais, 'OCC', vsnumerolotesto);
    
      -- Obteniendo los detalles
      WHILE (vbencontrodetalle)
      LOOP
      
        vnposiciondetalleini := dbms_lob.instr(vclob, '<detalle>', vnoffset);
      
        vnposiciondetallefin := dbms_lob.instr(vclob,
                                               '</detalle>',
                                               vnoffset);
      
        IF (vnposiciondetalleini = 0) THEN
        
          vbencontrodetalle := FALSE;
        
        ELSE
        
          vclobtemp := dbms_lob.substr(vclob,
                                       vnposiciondetallefin -
                                       vnposiciondetalleini + 10,
                                       vnposiciondetalleini);
        
          --Obteniendo el CUV
          vsetiquetainicio := '<cuv>';
          vsetiquetafin    := '</cuv>';
          vscuv            := ped_fn_obtie_conte_etiqu(vclobtemp,
                                                       vsetiquetainicio,
                                                       vsetiquetafin);
        
          --Obteniendo las unidades
          vsetiquetainicio := '<unidadessol>';
          vsetiquetafin    := '</unidadessol>';
          vsunidadescuv    := ped_fn_obtie_conte_etiqu(vclobtemp,
                                                       vsetiquetainicio,
                                                       vsetiquetafin);
        
          vnoffset := vnposiciondetallefin + 1;
        
          --insertamos en la tabla detalle
          vnoiddet := seq_solic_pos.nextval;
        
          INSERT INTO int_solic_posic
            (cod_pais,
             cam_soli,
             cod_clie,
             tip_posi,
             cod_prod,
             uni_dema,
             sta_proc,
             oid_posic,
             num_lote,
             ind_proc,
             num_lote_sto)
          VALUES
            (vscodpais,
             vscampanha,
             vsconsultora,
             'OC',
             vscuv,
             to_number(vsunidadescuv),
             'A',
             vnoiddet,
             vsnumlote,
             psindicadororigen,
             vsnumerolotesto);
        
        END IF;
      
      END LOOP;
    
    END IF;
  
    --Si la accion es 2 se obtenien los alternativos
    IF (vsaccion = '2') THEN
    
      WHILE (vbencontroalternativo)
      LOOP
      
        vnposicionalternativoini := dbms_lob.instr(vclob,
                                                   '<alternativos>',
                                                   vnoffset2);
        vnposicionalternativofin := dbms_lob.instr(vclob,
                                                   '</alternativos>',
                                                   vnoffset2);
      
        IF (vnposicionalternativoini = 0) THEN
        
          vbencontroalternativo := FALSE;
        
        ELSE
        
          vclobtemp := dbms_lob.substr(vclob,
                                       vnposicionalternativofin -
                                       vnposicionalternativoini + 15,
                                       vnposicionalternativoini);
        
          --Obteniendo el CUV Original
          vsetiquetainicio := '<cuvoriginal>';
          vsetiquetafin    := '</cuvoriginal>';
          vscuvoriginal    := ped_fn_obtie_conte_etiqu(vclobtemp,
                                                       vsetiquetainicio,
                                                       vsetiquetafin);
        
          --Obteniendo el CUV alternativo
          vsetiquetainicio := '<cuvalternativo>';
          vsetiquetafin    := '</cuvalternativo>';
          vscuvalternativo := ped_fn_obtie_conte_etiqu(vclobtemp,
                                                       vsetiquetainicio,
                                                       vsetiquetafin);
        
          --Obteniendo las unidades original
          vsetiquetainicio  := '<unidadessolorig>';
          vsetiquetafin     := '</unidadessolorig>';
          vsunidadescuvorig := ped_fn_obtie_conte_etiqu(vclobtemp,
                                                        vsetiquetainicio,
                                                        vsetiquetafin);
        
          --Obteniendo las unidades alternativo
          vsetiquetainicio  := '<unidadessolalte>';
          vsetiquetafin     := '</unidadessolalte>';
          vsunidadescuvalte := ped_fn_obtie_conte_etiqu(vclobtemp,
                                                        vsetiquetainicio,
                                                        vsetiquetafin);
        
          vnoffset2 := vnposicionalternativofin + 1;
        
          -- Si la accion es 2 Los alternativos se guardan en una tabla
          INSERT INTO ped_produ_alter
            (oid_prod_alte,
             perd_oid_peri,
             clie_oid_clie,
             cod_vent_orig,
             cod_vent_alte,
             num_unid_orig,
             num_unid_alte)
          VALUES
            (ped_pral_seq.nextval,
             vnoidcampanha,
             vnoidconsultora,
             vscuvoriginal,
             vscuvalternativo,
             vsunidadescuvorig,
             vsunidadescuvalte);
        
        END IF;
      
      END LOOP;
    
    END IF;
  
    UPDATE int_solic_cabec a
       SET a.cod_pais           = vscodpais,
           a.cam_soli           = vscampanha,
           a.cod_clie           = vsconsultora,
           a.fec_soli           = vdfechafac,
           a.num_lote           = vsnumlote,
           a.cod_regi_arri      = vscodregion,
           a.cod_zona_arri      = vscodzona,
           a.num_lote_sto       = vsnumerolotesto,
           a.soca_oid_soli_cabe = vsoidpedido,
           a.val_acci           = vsaccion
     WHERE a.oid_cab = vnoid;
  
    psoidtemporal := vnoid;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR PED_PR_INSER_ARCHI_PROL: ' ||
                              ls_sqlerrm);
    
  END ped_pr_inser_archi_prol;

  /**************************************************************************
    Descripcion       : Devuelve el contenido de una etiqueta XML
    Fecha Creación    : 18/01/2012
    Autor             : Jose Luis Rodriguez
  /**************************************************************************/
  FUNCTION ped_fn_obtie_conte_etiqu
  (
    pscadenaclob     IN CLOB,
    psetiquetainicio IN VARCHAR2,
    psetiquetacierre IN VARCHAR2
  ) RETURN VARCHAR2 IS
  
    l_clob             CLOB;
    l_etiquetaapertura VARCHAR2(100);
    l_etiquetacierre   VARCHAR2(100);
    l_longitudapertura NUMBER;
  
    l_subcontenido VARCHAR2(100);
  
  BEGIN
  
    l_clob := pscadenaclob;
  
    IF (instr(l_clob, psetiquetainicio) != 0) THEN
    
      l_etiquetaapertura := psetiquetainicio;
      l_etiquetacierre   := psetiquetacierre;
      l_longitudapertura := length(l_etiquetaapertura);
    
      l_subcontenido := dbms_lob.substr(l_clob,
                                        instr(l_clob, l_etiquetacierre) -
                                        (instr(l_clob, l_etiquetaapertura) +
                                         l_longitudapertura),
                                        instr(l_clob, l_etiquetaapertura) +
                                        l_longitudapertura);
    
    END IF;
  
    RETURN TRIM(l_subcontenido);
  
  END ped_fn_obtie_conte_etiqu;
  /**************************************************************************
    Descripcion       : Devuelve la secuenciacion para Colombia
    Fecha Creación    : 12/02/2013
    Autor             : Jorge Yepez
  /**************************************************************************/
  FUNCTION ped_fn_obtie_secue_colom(pnoidcabe IN NUMBER) RETURN VARCHAR2 IS
  
    lsprio VARCHAR2(15);
  
  BEGIN
  
    BEGIN
      SELECT val_prio
        INTO lsprio
        FROM (SELECT ord.val_prio
                FROM sat_orden_impre_apsat ord,
                     ped_solic_cabec       con,
                     zon_terri_admin       zta,
                     zon_secci             sec,
                     zon_zona              zon,
                     zon_regio             reg,
                     zon_terri             ter,
                     mae_clien             cli
               WHERE con.oid_soli_cabe = pnoidcabe
                 AND con.clie_oid_clie = cli.oid_clie
                 AND con.ztad_oid_terr_admi = zta.oid_terr_admi
                 AND zta.zscc_oid_secc = sec.oid_secc
                 AND zta.terr_oid_terr = ter.oid_terr
                 AND sec.zzon_oid_zona = zon.oid_zona
                 AND zon.zorg_oid_regi = reg.oid_regi
                 AND ord.cod_regi = reg.cod_regi
                 AND ord.cod_zona = zon.cod_zona
                 AND (ord.cod_secc = sec.cod_secc OR
                     ltrim(ord.cod_secc, 'X') IS NULL)
                 AND (ord.cod_terr = to_char(ter.cod_terr) OR
                     ltrim(ord.cod_terr, 'X') IS NULL)
                 AND (ord.cod_clie = cli.cod_clie OR
                     ltrim(ord.cod_clie, 'X') IS NULL)
               ORDER BY CASE
                          WHEN ord.tip_regi = 'Z' THEN
                           1
                          WHEN ord.tip_regi = 'S' THEN
                           2
                          WHEN ord.tip_regi = 'T' THEN
                           3
                          WHEN ord.tip_regi = 'C' THEN
                           4
                        END)
       WHERE rownum = 1;
    EXCEPTION
      WHEN no_data_found THEN
        lsprio := 'AAAAAAAAAAAA';
    END;
  
    RETURN TRIM(lsprio);
  
  END ped_fn_obtie_secue_colom;
  /**************************************************************************
    Descripcion       : Devuelve el archivo de respuesta web service
    Fecha Creación    :  23/01/2012
    Autor             : Sergio Buchelli
    Parametros        :  pnOidArchivo oid con elc ual ha sido guardado en la tabla de entrade del archivo xml
                         pnOidPedido Oid pedido
  /**************************************************************************/
  PROCEDURE ped_fn_gener_archi_prol(pnoidarchivo NUMBER) IS
  
    l_clob        CLOB;
    l_textoactual VARCHAR2(1000) := '';
  
    --cursor de errores
  
    CURSOR c_errores
    (
      vscampanha   VARCHAR2,
      vsnumerolote VARCHAR2,
      vscodcliente VARCHAR2
    ) IS
      SELECT e.cod_vali cod_vali,
             (SELECT m.des_web_mens
                FROM sto_mensa_valid m
               WHERE m.cod_mens = e.cod_mens
                 AND e.cod_tipo_docu = m.cod_tipo_docu
                 AND e.cod_vali = m.cod_vali) des_cort_mens
        FROM int_solic_conso_cabec c,
             sto_detal_docum_excep e
       WHERE e.sec_nume_docu = c.sec_nume_docu
         AND e.num_lote = c.num_lote
         AND c.cod_peri = vscampanha
         AND c.num_lote = vsnumerolote
         AND c.cod_clie = vscodcliente;
  
    --cursor de detalle
    CURSOR c_detalles(vnoidpedido NUMBER) IS
      SELECT p.oid_deta_prol,
             p.val_codi_vent,
             p.num_unid_dema,
             p.num_unid_dema_real,
             p.num_unid_comp,
             p.val_prec_cata_unit_loca,
             p.val_porc_desc,
             p.val_impo_desc_unit_loca,
             p.val_prec_fact_unit_loca,
             p.val_obse,
             p.cod_vent_orig,
             (SELECT ofer_oid_ofer
                FROM pre_ofert_detal a,
                     ped_solic_posic b
               WHERE a.oid_deta_ofer = b.ofde_oid_deta_ofer
                 AND b.soca_oid_soli_cabe = vnoidpedido
                 AND b.val_codi_vent = p.val_codi_vent
                 AND rownum = 1) ofer_oid_ofer,
             (SELECT c.coes_oid_estr
                FROM pre_ofert_detal a,
                     ped_solic_posic b,
                     pre_ofert       c
               WHERE a.oid_deta_ofer = b.ofde_oid_deta_ofer
                 AND b.soca_oid_soli_cabe = vnoidpedido
                 AND b.val_codi_vent = p.val_codi_vent
                 AND a.ofer_oid_ofer = c.oid_ofer
                 AND rownum = 1) oid_estr
        FROM ped_detal_prol p
       WHERE soca_oid_soli_cabe = vnoidpedido;
  
    --cursors de alternativos
    CURSOR c_alternativos(vnoidpedido NUMBER) IS
      SELECT oid_prod_alte_falt,
             cod_vent_orig,
             cod_vent_alte,
             des_alte,
             imp_prec_cata,
             des_cata
        FROM ped_produ_alter_falta
       WHERE soca_oid_soli_cabe = vnoidpedido;
  
    r_errores      c_errores%ROWTYPE;
    r_alternativos c_alternativos%ROWTYPE;
    r_detalles     c_detalles%ROWTYPE;
  
    lnvaldeuda int_solic_conso_cabec.val_sald_deud%TYPE;
  
    lscodcliente   mae_clien.cod_clie%TYPE;
    lscampanha     seg_perio_corpo.cod_peri%TYPE;
    lsnumerolote   int_solic_cabec.num_lote%TYPE;
    lsaccion       int_solic_cabec.val_acci%TYPE;
    lnoidsolicitud int_solic_cabec.soca_oid_soli_cabe%TYPE;
  BEGIN
  
    SELECT xml_pedi_sali,
           cod_clie,
           cam_soli,
           num_lote,
           val_acci,
           soca_oid_soli_cabe
      INTO l_clob,
           lscodcliente,
           lscampanha,
           lsnumerolote,
           lsaccion,
           lnoidsolicitud
      FROM int_solic_cabec
     WHERE oid_cab = pnoidarchivo
       FOR UPDATE; --vnOid;
  
    IF lsaccion != 3 THEN
      SELECT val_sald_deud,
             x.soca_oid_soli_cabe_refe
        INTO lnvaldeuda,
             lnoidsolicitud
        FROM int_solic_conso_cabec x,
             int_solic_cabec       t
       WHERE t.oid_cab = pnoidarchivo
         AND t.cam_soli = x.cod_peri
         AND t.cod_clie = x.cod_clie
         AND t.num_lote = x.num_lote;
    END IF;
  
    -- INICIO PEDIDO
    l_textoactual := '<pedido>';
  
    dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
  
    -- pedido
    l_textoactual := '<oidsicc>' || lnoidsolicitud || '</oidsicc>';
    dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
  
    -- deuda
    l_textoactual := '<deuda>' || lnvaldeuda || '</deuda>';
    dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
  
    -- cons
    l_textoactual := '<consultora>' || lscodcliente || '</consultora>';
    dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
  
    -- campanha
    l_textoactual := '<campana>' || lscampanha || '</campana>';
    dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
  
    -- INSERTAMOS LOS ERRORES STO
  
    OPEN c_errores(lscampanha, lsnumerolote, lscodcliente);
    LOOP
      FETCH c_errores
        INTO r_errores;
      EXIT WHEN c_errores%NOTFOUND;
      BEGIN
        -- Inicio de errores
        l_textoactual := '<errores>';
        dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
      
        -- codigo
        l_textoactual := '<codigo>' || r_errores.cod_vali || '</codigo>';
        dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
      
        -- desc
        l_textoactual := '<desc>' || r_errores.des_cort_mens || '</desc>';
        dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
      
        -- Fin errores
        l_textoactual := '</errores>';
        dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
      
      END;
    END LOOP;
    CLOSE c_errores;
  
    -- INSERTAMOS LOS DETALLES
  
    OPEN c_detalles(lnoidsolicitud);
    LOOP
      FETCH c_detalles
        INTO r_detalles;
      EXIT WHEN c_detalles%NOTFOUND;
      BEGIN
      
        -- Inicio de detalle
        l_textoactual := '<detalle>';
        dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
      
        -- cuv
        l_textoactual := '<cuv>' || r_detalles.val_codi_vent || '</cuv>';
        dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
      
        -- unidades
        l_textoactual := '<unidades>' || r_detalles.num_unid_dema ||
                         '</unidades>';
        dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
      
        -- unidadesreal
        l_textoactual := '<unidadesreal>' || r_detalles.num_unid_dema_real ||
                         '</unidadesreal>';
        dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
      
        -- unidadesatender
        l_textoactual := '<unidadesatender>' || r_detalles.num_unid_comp ||
                         '</unidadesatender>';
        dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
      
        -- preciocatalogo
        l_textoactual := '<preciocatalogo>' ||
                         r_detalles.val_prec_cata_unit_loca ||
                         '</preciocatalogo>';
        dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
      
        -- porcentajedesc
        l_textoactual := '<porcentajedesc>' || r_detalles.val_porc_desc ||
                         '</porcentajedesc>';
        dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
      
        -- montodesc
        l_textoactual := '<montodesc>' ||
                         r_detalles.val_impo_desc_unit_loca ||
                         '</montodesc>';
        dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
      
        -- montoapagar
        l_textoactual := '<montoapagar>' ||
                         r_detalles.val_prec_fact_unit_loca ||
                         '</montoapagar>';
        dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
      
        -- observaciones
        l_textoactual := '<observaciones>' || r_detalles.val_obse ||
                         '</observaciones>';
        dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
      
        -- Cuv Original
        l_textoactual := '<cuvoriginal>' || r_detalles.cod_vent_orig ||
                         '</cuvoriginal>';
        dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
      
        -- Oid Oferta
        l_textoactual := '<oidoferta>' || r_detalles.ofer_oid_ofer ||
                         '</oidoferta>';
        dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
      
        -- Oid Estrategia
        l_textoactual := '<oidestrategia>' || r_detalles.oid_estr ||
                         '</oidestrategia>';
        dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
      
        -- Fin detalle
        l_textoactual := '</detalle>';
        dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
      
      END;
    END LOOP;
    CLOSE c_detalles;
  
    -- INSERTAMOS LOS ALTERNATIMOS
  
    OPEN c_alternativos(lnoidsolicitud);
    LOOP
      FETCH c_alternativos
        INTO r_alternativos;
      EXIT WHEN c_alternativos%NOTFOUND;
      BEGIN
        -- Inicio de alternativos
        l_textoactual := '<alternativos>';
        dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
      
        -- cuvoriginal
        l_textoactual := '<cuvoriginal>' || r_alternativos.cod_vent_orig ||
                         '</cuvoriginal>';
        dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
      
        -- cuvalternativo
        l_textoactual := '<cuvalternativo>' || r_alternativos.cod_vent_alte ||
                         '</cuvalternativo>';
        dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
      
        -- descalternativo
        l_textoactual := '<descalternativo>' || r_alternativos.des_alte ||
                         '</descalternativo>';
        dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
      
        -- preciocatalogo
        l_textoactual := '<preciocatalogo>' || r_alternativos.imp_prec_cata ||
                         '</preciocatalogo>';
        dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
      
        -- cuvalternativo
        l_textoactual := '<catalogo>' || r_alternativos.des_cata ||
                         '</catalogo>';
        dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
      
        -- Fin Alternativos
        l_textoactual := '</alternativos>';
        dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
      
      END;
    END LOOP;
    CLOSE c_alternativos;
  
    --MENSAJES
    -- Inicio de mesnajes
    l_textoactual := '<mensajes>';
    dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
  
    -- codigo
    l_textoactual := '<codigo>' || '</codigo>';
    dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
  
    -- texto
    l_textoactual := '<texto>' || '</texto>';
    dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
  
    -- Fin mensaje
    l_textoactual := '</mensajes>';
    dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
  
    -- FIN PEDIDO
    l_textoactual := '</pedido>';
    dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
  
  END ped_fn_gener_archi_prol;

  /***************************************************************************
  Descripcion       : Cargar a la tabla temporal datos para el Reporte de Consultoras a Chequear
  Fecha Creacion    : 27/05/2013
  Autor             : Eduardo Sanchez
  Parametros        :
              pscodigoperiodo      : Codigo de Periodo
              psfechafacturainicio : Fecha de Factura Inicial
              psfechafacturafin    : Fecha de Factura Final
              pscondicion          : Cadena condicional para regiones y zonas
              pnoidproc            : Oid de Proceso incremental
  ***************************************************************************/
  PROCEDURE ped_pr_repor_consu_chequ
  (
    pscodigoperiodo      VARCHAR2,
    psfechafacturainicio VARCHAR2,
    psfechafacturafin    VARCHAR2,
    pscondicion          VARCHAR2,
    pnoidproc            OUT NUMBER
  ) IS
    lnsecuencia NUMBER;
    var_sql_str VARCHAR2(2000);
  
  BEGIN
  
    SELECT repor_consu_chequ_seq.nextval INTO lnsecuencia FROM dual;
  
    var_sql_str := 'INSERT INTO PED_REPOR_CONSU_CHEQU_TEMPO' ||
                   '(OID_PROC, COD_REGI, COD_ZONA, COD_SECC, COD_TERR, COD_CLIE, FEC_FACT, VAL_NUME_SOLI, DES_ORIG_CHEQ) ' ||
                   'SELECT ' || lnsecuencia || ', ' || 'A.COD_REGI, ' ||
                   'A.COD_ZONA, ' || 'A.COD_SECC, ' || 'A.COD_TERR, ' ||
                   'A.COD_CLIE, ' || 'A.FEC_FACT, ' || 'A.VAL_NUME_SOLI, ' ||
                   'B.DES_ORIG_CHEQ ' || 'FROM PED_PEDID_CHEQU A, ' ||
                   'PED_ORIGE_CHEQU B ' ||
                   'WHERE A.COD_ORIG_CHEQ = B.COD_ORIG_CHEQ ' ||
                   ' AND A.COD_PERI = ' || pscodigoperiodo ||
                   ' AND A.FEC_FACT >= TO_DATE(''' || psfechafacturainicio ||
                   ''',''dd/mm/yyyy'')' || ' AND A.FEC_FACT <= TO_DATE(''' ||
                   psfechafacturafin || ''',''dd/mm/yyyy'') ';
  
    IF pscondicion IS NOT NULL THEN
      var_sql_str := var_sql_str || ' ' || pscondicion;
    END IF;
  
    EXECUTE IMMEDIATE var_sql_str;
  
    pnoidproc := lnsecuencia;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
    
      raise_application_error(-20123,
                              'ERROR PED_PR_REPOR_CONSU_CHEQU: ' ||
                              ls_sqlerrm);
    
  END ped_pr_repor_consu_chequ;

  /***************************************************************************
  Descripcion       : Procesos de ejecucion de Facturacion adicional
  Fecha Creacion    : 01/07/2013
  Autor             : Carlos Chata
  ***************************************************************************/
  PROCEDURE ped_pr_proce_consu_fad
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    psdirectorio    OUT VARCHAR2
  ) IS
  BEGIN
  
    ped_pr_actua_consu_fad(pscodigopais, pscodigoperiodo);
    ped_pr_repor_consu_fad(pscodigopais, pscodigoperiodo, psdirectorio);
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
    
      raise_application_error(-20123,
                              'ERROR PED_PR_PROCE_CONSU_FAD: ' ||
                              ls_sqlerrm);
  END ped_pr_proce_consu_fad;

  /***************************************************************************
  Descripcion       : Procesos de ejecucion de Facturacion adicional
  Fecha Creacion    : 01/07/2013
  Autor             : Carlos Chata
  ***************************************************************************/
  PROCEDURE ped_pr_actua_consu_fad
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2
  ) IS
  
    lsperiodosfad       NUMBER;
    t_perd_oid_peri_ini NUMBER;
    t_perd_oid_peri_fin NUMBER;
  
  BEGIN
  
    lsperiodosfad := to_number(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                    'STO_FAD_PERIODOS'));
  
    t_perd_oid_peri_ini := fin_pkg_gener.fin_fn_obtie_oid_perio(fin_pkg_gener.fin_fn_calcu_perio_nante(fin_pkg_gener.fin_fn_obtie_codig_perio_actua,
                                                                                                       lsperiodosfad - 1));
    t_perd_oid_peri_fin := fin_pkg_gener.fin_fn_obtie_oid_perio(pscodigoperiodo);
  
    DELETE FROM sto_factu_adici_clien;
  
    INSERT INTO sto_factu_adici_clien
      (oid_fact_adic,
       oid_clie,
       cod_peri,
       cod_clie,
       nom_clie,
       cod_regi,
       des_regi,
       cod_zona,
       des_zona,
       cod_terr,
       val_mont_prom_calc,
       val_mont_prom_real,
       val_mtmi_calc,
       val_mtmi_real,
       ind_vali_prom,
       ind_vali_mtmi)
      SELECT DISTINCT conso.clie_oid_clie,
                      conso.clie_oid_clie AS oid_clie,
                      fin_pkg_gener.fin_fn_obtie_nsgte_campa(pscodigoperiodo,
                                                             1) AS cod_peri,
                      mae.cod_clie,
                      gen_pkg_gener.gen_fn_clien_datos(mae.cod_clie,
                                                       'NOM_CLIE') AS nom_clie,
                      gen_pkg_gener.gen_fn_clien_datos(cod_clie, 'COD_REGI') AS cod_regi,
                      gen_pkg_gener.gen_fn_clien_datos(cod_clie, 'DES_REGI') AS des_regi,
                      gen_pkg_gener.gen_fn_clien_datos(cod_clie, 'COD_ZONA') AS cod_zona,
                      gen_pkg_gener.gen_fn_clien_datos(cod_clie, 'DES_ZONA') AS des_zona,
                      gen_pkg_gener.gen_fn_clien_datos(cod_clie, 'COD_TERR') AS cod_terr,
                      sto_pkg_proce_valid_occ.sto_fn_devue_prom_venta(conso.clie_oid_clie,
                                                                      t_perd_oid_peri_ini,
                                                                      t_perd_oid_peri_fin),
                      0,
                      0,
                      0,
                      conso.ind_vali_mtmi,
                      conso.ind_vali_prom
        FROM mae_clien mae,
             (SELECT DISTINCT c.clie_oid_clie,
                              a.ind_vali_mtmi,
                              a.ind_vali_prom,
                              a.oid_fact_adic
                FROM mae_clien_unida_admin c,
                     zon_terri_admin       d,
                     zon_terri             e,
                     zon_secci             f,
                     zon_zona              g,
                     zon_regio             h,
                     mae_clien_tipo_subti  i,
                     mae_clien_clasi       j,
                     sto_factu_adici       a
               WHERE c.ztad_oid_terr_admi = d.oid_terr_admi
                 AND d.zscc_oid_secc = f.oid_secc
                 AND d.terr_oid_terr = e.oid_terr
                 AND f.zzon_oid_zona = g.oid_zona
                 AND g.zorg_oid_regi = h.oid_regi
                 AND c.clie_oid_clie = i.clie_oid_clie
                 AND c.ind_acti = 1
                 AND d.ind_borr = 0
                 AND i.oid_clie_tipo_subt = j.ctsu_oid_clie_tipo_subt
                 AND (c.clie_oid_clie = a.oid_clie OR a.oid_clie IS NULL) --cliente
                 AND i.ticl_oid_tipo_clie =
                     nvl(a.oid_tipo_clie, i.ticl_oid_tipo_clie) --tipo
                 AND i.sbti_oid_subt_clie =
                     nvl(a.oid_subt_clie, i.sbti_oid_subt_clie) --subtipo
                 AND j.tccl_oid_tipo_clasi =
                     nvl(a.oid_tipo_clas, j.tccl_oid_tipo_clasi) --tipo clasif
                 AND j.clas_oid_clas = nvl(a.oid_clas, j.clas_oid_clas) --clasif
                 AND h.oid_regi = nvl(a.oid_regi, h.oid_regi) --region
                 AND g.oid_zona = nvl(a.oid_zona, g.oid_zona) --zon
                 AND a.ind_acti = '1') conso
       WHERE conso.clie_oid_clie = mae.oid_clie
         AND mae.oid_clie NOT IN
             (SELECT b.oid_clie
                FROM sto_factu_adici_clien b
               WHERE b.oid_clie = mae.oid_clie);
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
    
      raise_application_error(-20123,
                              'ERROR PED_PR_ACTUA_CONSU_FAD: ' ||
                              ls_sqlerrm);
  END ped_pr_actua_consu_fad;

  /***************************************************************************
  Descripcion       : Procesos de ejecucion de Facturacion adicional
                       Genera un archivo en excel
  Fecha Creacion    : 01/07/2013
  Autor             : Carlos Chata
  ***************************************************************************/
  PROCEDURE ped_pr_repor_consu_fad
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    psdirectorio    OUT VARCHAR2
  ) IS
  
    lsdirtempo     bas_inter.dir_temp%TYPE;
    w_filas        NUMBER := 5000;
    v_handle       utl_file.file_type;
    lslinea        VARCHAR2(4000);
    lsmtominimofad NUMBER;
  
    CURSOR c_facturaadicional IS
      SELECT * FROM sto_factu_adici_clien;
  
    TYPE facturaadicionaltab IS TABLE OF sto_factu_adici_clien%ROWTYPE;
    facturaadicionalrecord facturaadicionaltab;
  
    lbcabecera BOOLEAN;
  
    lsnombrearchivo VARCHAR2(13);
  BEGIN
  
    lbcabecera      := TRUE;
    lsnombrearchivo := 'FAD_' || pscodigoperiodo;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
  
    gen_pkg_inter_archi.gen_pr_inici_repor_oracl(pscodigopais,
                                                 lsnombrearchivo,
                                                 '.csv',
                                                 'FAD',
                                                 lsdirtempo,
                                                 v_handle);
    psdirectorio := lsdirtempo;
  
    lsmtominimofad := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                           'STO_FAD_MTOMIN');
  
    OPEN c_facturaadicional;
    LOOP
      FETCH c_facturaadicional BULK COLLECT
        INTO facturaadicionalrecord LIMIT w_filas;
      IF facturaadicionalrecord.count > 0 THEN
        FOR x IN facturaadicionalrecord.first .. facturaadicionalrecord.last
        LOOP
          IF lbcabecera THEN
            lslinea := '"Campaña",' || '"Código Cliente",' ||
                       '"Nombre Cliente",' || '"Código Región",' ||
                       '"Descripción Región",' || '"Código Zona",' ||
                       '"Descripción Zona",' || '"Código Territorio",' ||
                       '"Promedio de Venta",' || '"Monto Mínimo"';
          
            lbcabecera := FALSE;
            utl_file.put_line(v_handle, lslinea);
          END IF;
        
          lslinea := '=T("' || facturaadicionalrecord(x).cod_peri || '")' || ',' ||
                     '=T("' || facturaadicionalrecord(x).cod_clie || '")' || ',' ||
                     '=T("' || facturaadicionalrecord(x).nom_clie || '")' || ',' ||
                     '=T("' || facturaadicionalrecord(x).cod_regi || '")' || ',' ||
                     '=T("' || facturaadicionalrecord(x).des_regi || '")' || ',' ||
                     '=T("' || facturaadicionalrecord(x).cod_zona || '")' || ',' ||
                     '=T("' || facturaadicionalrecord(x).des_zona || '")' || ',' ||
                     '=T("' || facturaadicionalrecord(x).cod_terr || '")' || ',' ||
                     '=T("' || facturaadicionalrecord(x).val_mont_prom_calc || '")' || ',' ||
                     '=T("' || lsmtominimofad || '")';
        
          utl_file.put_line(v_handle, lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_facturaadicional%NOTFOUND;
    END LOOP;
  
    CLOSE c_facturaadicional;
  
    utl_file.fclose(v_handle);
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
    
      raise_application_error(-20123,
                              'ERROR PED_PR_REPOR_CONSU_FAD: ' ||
                              ls_sqlerrm);
  END ped_pr_repor_consu_fad;

  /***************************************************************************
  Descripcion       : Proceso de Carga a tablas para los reportes de Demanda
                        Anticipada
                       Carga las tablas para generar los reportes
  Fecha Creacion    : 02/07/2013
  Autor             : Ivan Tocto
  ***************************************************************************/
  PROCEDURE ped_pr_repor_deman_antic
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    psfecha         VARCHAR2
  ) IS
  BEGIN
  
    DELETE FROM ped_deman_antic WHERE cod_peri = pscodigoperiodo;
  
    INSERT INTO ped_deman_antic
      (cod_pais,
       cod_peri,
       cod_zona,
       cod_clie,
       cod_vent,
       cod_sap,
       des_sap,
       cod_tipo_ofer,
       cod_estr,
       des_estr,
       num_unid,
       val_vent_neta)
      SELECT pscodigopais    cod_pais,
             m.cod_peri      cod_peri,
             g.cod_zona,
             e.cod_clie      cod_clie,
             b.val_codi_vent cod_vent,
             c.cod_sap,
             d.val_i18n      des_sap,
             i.cod_tipo_ofer,
             k.cod_estr,
             l.val_i18n      des_estr,
             --j.num_ofer,
             SUM(b.num_unid_por_aten) num_unid,
             SUM(round(nvl(((nvl(b.val_prec_cata_unit_loca, 0) -
                           nvl(b.val_impo_desc_unit_loca, 0)) / 1.12) *
                           b.num_unid_por_aten,
                           0),
                       2)) val_vent_neta
        FROM ped_solic_cabec    a,
             ped_solic_posic    b,
             mae_produ          c,
             gen_i18n_sicc_pais d,
             mae_clien          e,
             cra_perio          f,
             zon_zona           g,
             pre_ofert_detal    h,
             pre_tipo_ofert     i,
             pre_ofert          j,
             pre_estra          k,
             gen_i18n_sicc_pais l,
             seg_perio_corpo    m
       WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
         AND a.fec_prog_fact = to_date(psfecha, 'dd/mm/yyyy') ---->  VARIANTE 1
         AND a.perd_oid_peri =
             gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoperiodo) ----->  VARIANTE 2
         AND b.prod_oid_prod = c.oid_prod
         AND d.attr_enti = 'MAE_PRODU'
         AND d.val_oid = c.oid_prod
         AND l.attr_enti = 'PRE_ESTRA'
         AND l.val_oid = k.oid_estr
         AND a.clie_oid_clie = e.oid_clie
         AND f.peri_oid_peri = m.oid_peri
         AND a.perd_oid_peri = f.oid_peri
         AND a.esso_oid_esta_soli = 1
         AND a.zzon_oid_zona = g.oid_zona
         AND b.ofde_oid_deta_ofer = h.oid_deta_ofer
         AND h.tofe_oid_tipo_ofer = i.oid_tipo_ofer
         AND h.ofer_oid_ofer = j.oid_ofer
         AND j.coes_oid_estr = k.oid_estr
         AND a.grpr_oid_grup_proc = 3
       GROUP BY pscodigopais,
                m.cod_peri,
                g.cod_zona,
                e.cod_clie,
                b.val_codi_vent,
                c.cod_sap,
                d.val_i18n,
                i.cod_tipo_ofer,
                k.cod_estr,
                l.val_i18n;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
    
      raise_application_error(-20123,
                              'ERROR PED_PR_REPOR_DEMAN_ANTIC: ' ||
                              ls_sqlerrm);
  END ped_pr_repor_deman_antic;

  /***************************************************************************
  Descripcion       : Inserta Facturacion adicional Detalle
  Fecha Creacion    : 04/09/2013
  Autor             : Aurelio Oviedo
  ***************************************************************************/
  PROCEDURE ped_pr_inser_consu_fad_detal
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    psoidcabecera   VARCHAR2
  ) IS
  
    lsperiodosfad       NUMBER;
    t_perd_oid_peri_ini NUMBER;
    t_perd_oid_peri_fin NUMBER;
    v_param_cliente     VARCHAR2(100);
  
    v_codigoperiodo VARCHAR2(10);
  
  BEGIN
  
    IF pscodigoperiodo IS NULL THEN
      SELECT cod_peri
        INTO v_codigoperiodo
        FROM bas_ctrl_fact
       WHERE sta_camp = '0'
         AND ind_camp_act = '1';
    ELSE
      v_codigoperiodo := pscodigoperiodo;
    END IF;
  
    lsperiodosfad := to_number(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                    'STO_FAD_PERIODOS'));
  
    t_perd_oid_peri_ini := fin_pkg_gener.fin_fn_obtie_oid_perio(fin_pkg_gener.fin_fn_calcu_perio_nante(fin_pkg_gener.fin_fn_obtie_codig_perio_actua,
                                                                                                       lsperiodosfad - 1));
    t_perd_oid_peri_fin := fin_pkg_gener.fin_fn_obtie_oid_perio(v_codigoperiodo);
  
    INSERT INTO sto_factu_adici_clien
      (oid_fact_adic,
       oid_clie,
       cod_peri,
       cod_clie,
       nom_clie,
       cod_regi,
       des_regi,
       cod_zona,
       des_zona,
       cod_terr,
       val_mont_prom_calc,
       val_mont_prom_real,
       val_mtmi_calc,
       val_mtmi_real,
       ind_vali_prom,
       ind_vali_mtmi)
      SELECT conso.oid_fact_adic,
             conso.clie_oid_clie AS oid_clie,
             fin_pkg_gener.fin_fn_obtie_nsgte_campa(v_codigoperiodo, 1) AS cod_peri,
             mae.cod_clie,
             gen_pkg_gener.gen_fn_clien_datos(mae.cod_clie, 'NOM_CLIE') AS nom_clie,
             gen_pkg_gener.gen_fn_clien_datos(cod_clie, 'COD_REGI') AS cod_regi,
             gen_pkg_gener.gen_fn_clien_datos(cod_clie, 'DES_REGI') AS des_regi,
             gen_pkg_gener.gen_fn_clien_datos(cod_clie, 'COD_ZONA') AS cod_zona,
             gen_pkg_gener.gen_fn_clien_datos(cod_clie, 'DES_ZONA') AS des_zona,
             gen_pkg_gener.gen_fn_clien_datos(cod_clie, 'COD_TERR') AS cod_terr,
             sto_pkg_proce_valid_occ.sto_fn_devue_prom_venta(conso.clie_oid_clie,
                                                             t_perd_oid_peri_ini,
                                                             t_perd_oid_peri_fin),
             0,
             0,
             0,
             conso.ind_vali_prom,
             conso.ind_vali_mtmi
        FROM mae_clien mae,
             (SELECT DISTINCT c.clie_oid_clie,
                              a.ind_vali_mtmi,
                              a.ind_vali_prom,
                              a.oid_fact_adic
                FROM mae_clien_unida_admin c,
                     zon_terri_admin       d,
                     zon_terri             e,
                     zon_secci             f,
                     zon_zona              g,
                     zon_regio             h,
                     mae_clien_tipo_subti  i,
                     mae_clien_clasi       j,
                     sto_factu_adici       a
               WHERE c.ztad_oid_terr_admi = d.oid_terr_admi
                 AND d.zscc_oid_secc = f.oid_secc
                 AND d.terr_oid_terr = e.oid_terr
                 AND f.zzon_oid_zona = g.oid_zona
                 AND g.zorg_oid_regi = h.oid_regi
                 AND c.clie_oid_clie = i.clie_oid_clie
                 AND c.ind_acti = 1
                 AND d.ind_borr = 0
                 AND i.oid_clie_tipo_subt = j.ctsu_oid_clie_tipo_subt
                 AND c.clie_oid_clie = nvl(a.oid_clie, c.clie_oid_clie) --cliente
                 AND i.ticl_oid_tipo_clie =
                     nvl(a.oid_tipo_clie, i.ticl_oid_tipo_clie) --tipo
                 AND i.sbti_oid_subt_clie =
                     nvl(a.oid_subt_clie, i.sbti_oid_subt_clie) --subtipo
                 AND j.tccl_oid_tipo_clasi =
                     nvl(a.oid_tipo_clas, j.tccl_oid_tipo_clasi) --tipo clasif
                 AND j.clas_oid_clas = nvl(a.oid_clas, j.clas_oid_clas) --clasif
                 AND h.oid_regi = nvl(a.oid_regi, h.oid_regi) --region
                 AND g.oid_zona = nvl(a.oid_zona, g.oid_zona) --zon
                 AND a.ind_acti = '1'
                 AND a.oid_fact_adic = psoidcabecera) conso
       WHERE conso.clie_oid_clie = mae.oid_clie
         AND mae.oid_clie NOT IN
             (SELECT b.oid_clie
                FROM sto_factu_adici_clien b
               WHERE b.oid_clie = mae.oid_clie);
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
    
      raise_application_error(-20123,
                              'ERROR PED_PR_INSER_CONSU_FAD_DETAL: ' ||
                              ls_sqlerrm);
  END ped_pr_inser_consu_fad_detal;

  /***************************************************************************
  Descripcion       : Actualiza los datos de ultimo pedido
  Fecha Creacion    : 13/02/2014
  Autor             : Rosalvina Ramirez Guardia
  ***************************************************************************/

  PROCEDURE ped_pr_actua_ultim_pedid(psoidcliente VARCHAR2) IS
  
    lv_camp_ult_pedid VARCHAR2(6);
    ln_monto_fact     NUMBER(12, 2);
    lnSoca  NUMBER(12);
    lnFecFact DATE;
    lnValNumeSoli NUMBER(10);
    
  BEGIN
  
    BEGIN
      SELECT cod_peri,
             val_tota_paga_loca,
             oid_soli_cabe,
             fec_fact,
             val_nume_soli
        INTO lv_camp_ult_pedid,
             ln_monto_fact,
             lnSoca, lnFecFact, lnValNumeSoli
        FROM (SELECT per.cod_peri,
                     psc.val_tota_paga_loca,
                     psc.oid_soli_cabe,
                     psc.fec_fact,
                     psc.val_nume_soli
                FROM ped_solic_cabec     psc,
                     ped_tipo_solic_pais tsp,
                     ped_tipo_solic      ts,
                     cra_perio           cra,
                     seg_perio_corpo     per
               WHERE cra.oid_peri = psc.perd_oid_peri
                 AND per.oid_peri = cra.peri_oid_peri
                 AND psc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
                 AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                 AND psc.clie_oid_clie = psoidcliente
                 AND ts.cod_tipo_soli = 'C1'
                 AND psc.esso_oid_esta_soli <> 4
               ORDER BY cra.fec_inic      DESC,
                        psc.val_nume_soli DESC)
       WHERE rownum = 1;
    EXCEPTION
      WHEN OTHERS THEN
        lv_camp_ult_pedid := fin_pkg_gener.fin_fn_obtie_codig_perio(fin_pkg_gener.fin_fn_obtie_perio_actu);
        ln_monto_fact     := 0;
    END;
  
    UPDATE mae_clien_estat me
       SET me.camp_ulti_pedi = lv_camp_ult_pedid,
           me.fec_ulti_actu  = SYSDATE,
           me.val_mont_fact  = ln_monto_fact,
           me.soca_oid_soca = lnSoca, me.fec_fact_ulti = lnFecFact, me.val_nume_soli = lnValNumeSoli
     WHERE me.oid_clie = psoidcliente;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
    
      raise_application_error(-20123,
                              'ERROR PED_PR_INSER_CONSU_FAD_DETAL: ' ||
                              ls_sqlerrm);
  END ped_pr_actua_ultim_pedid;

  PROCEDURE imp_pr_obtie_infor_docle_refe1(p_oiddocumento IN NUMBER) IS
  
    p_seriedocumentoreferencia   VARCHAR2(15);
    p_numerodocumentoreferencia  NUMBER;
    p_codigointernoreferencia    NUMBER;
    p_fechareferencia            DATE;
    p_montoreferencia            NUMBER;
    p_oidtipodocumentoreferencia NUMBER;
    p_tipodocumentoreferencia    VARCHAR2(15);
    p_fecha_limite_refe          fac_regis_unico_venta.fec_limi_refe%type;
    p_llave_refe                 fac_regis_unico_venta.val_llav_refe%type;
    p_nume_auto_refe             fac_regis_unico_venta.val_nume_auto_refe%type;
    p_nume_cont_refe             fac_regis_unico_venta.val_nume_cont_refe%type;
    p_val_nume_soli_refe         ped_solic_cabec.val_nume_soli%type;
    p_val_nume_iden_fisc         fac_regis_unico_venta.val_nume_iden_fisc%type;
    p_cod_peri_refe              seg_perio_corpo.cod_peri%type;
  
    CURSOR c_referencia IS
      SELECT cab.oid_cabe,
             con.oid_soli_cabe,
             --con.val_nume_soli,
             con.fec_fact,
             fac.num_docu_cont_inte,
             fac.num_docu_lega,
             fac.val_seri_docu_lega,
             fac.fec_fact fec_fact_refe,
             fac.val_tota_paga_loca,
             ftd.oid_tipo_docu,
             ftd.cod_tipo_docu,
             fac.fec_limi,
             fac.val_llav,
             fac.val_nume_auto,
             fac.val_nume_cont,
             refe.val_nume_soli pedi_refe,
             nvl(fac.val_nume_iden_fisc, fac.val_nume_iden_nnal) nit,
             spc.cod_peri peri_refe
        FROM fac_docum_conta_cabec cab,
             ped_solic_cabec       con,
             fac_docum_conta_cabec fac,
             fac_tipo_docum        ftd,
             ped_solic_cabec       refe,
             cra_perio             cp, 
             seg_perio_corpo       spc
       WHERE cab.soca_oid_soli_cabe = con.oid_soli_cabe
         AND con.soca_oid_docu_refe = fac.soca_oid_soli_cabe
         AND fac.tido_oid_tipo_docu = ftd.oid_tipo_docu
         AND cab.oid_cabe = p_oiddocumento
         and fac.soca_oid_soli_cabe=refe.oid_soli_cabe
         and refe.perd_oid_peri=cp.oid_peri
         and cp.peri_oid_peri=spc.oid_peri
         AND EXISTS
       (SELECT NULL
                FROM fac_docum_conta_linea det,
                     fac_docum_conta_linea det_refe,
                     ped_solic_posic       pos,
                     ped_solic_posic       pos_refe
               WHERE cab.oid_cabe = det.dcca_oid_cabe
                 AND det.sopo_oid_soli_posi = pos.oid_soli_posi
                 AND fac.oid_cabe = det_refe.dcca_oid_cabe
                 AND det_refe.sopo_oid_soli_posi = pos_refe.oid_soli_posi
                 AND pos.prod_oid_prod = pos_refe.prod_oid_prod
                 AND pos.ofde_oid_deta_ofer = pos_refe.ofde_oid_deta_ofer);
  
    CURSOR c_referencia1 IS
      SELECT cab.oid_cabe,
             con.oid_soli_cabe,
             con.val_nume_soli,
             con.fec_fact,
             fac.num_docu_cont_inte,
             fac.num_docu_lega,
             fac.val_seri_docu_lega,
             fac.fec_fact fec_fact_refe,
             fac.val_tota_paga_loca,
             ftd.oid_tipo_docu,
             ftd.cod_tipo_docu,
             fac.fec_limi,
             fac.val_llav,
             fac.val_nume_auto,
             fac.val_nume_cont,
             refe.val_nume_soli pedi_refe,
             nvl(fac.val_nume_iden_fisc, fac.val_nume_iden_nnal) nit,
             spc.cod_peri peri_refe
        FROM fac_docum_conta_cabec cab,
             ped_solic_cabec       con,
             fac_docum_conta_cabec fac,
             fac_tipo_docum        ftd,
             ped_solic_cabec       refe,
             cra_perio             cp, 
             seg_perio_corpo       spc
       WHERE cab.soca_oid_soli_cabe = con.oid_soli_cabe
         AND con.soca_oid_docu_refe = fac.soca_oid_soli_cabe
         AND fac.tido_oid_tipo_docu = ftd.oid_tipo_docu
         AND cab.oid_cabe = p_oiddocumento
         and fac.soca_oid_soli_cabe=refe.oid_soli_cabe
         and refe.perd_oid_peri=cp.oid_peri
         and cp.peri_oid_peri=spc.oid_peri
         AND EXISTS
       (SELECT NULL
                FROM fac_docum_conta_linea det,
                     fac_docum_conta_linea det_refe,
                     ped_solic_posic       pos,
                     ped_solic_posic       pos_refe
               WHERE cab.oid_cabe = det.dcca_oid_cabe
                 AND det.sopo_oid_soli_posi = pos.oid_soli_posi
                 AND fac.oid_cabe = det_refe.dcca_oid_cabe
                 AND det_refe.sopo_oid_soli_posi = pos_refe.oid_soli_posi
                 AND pos.prod_oid_prod = pos_refe.prod_oid_prod
              --AND POS.OFDE_OID_DETA_OFER = POS_REFE.OFDE_OID_DETA_OFER
              );
  
    CURSOR c_referencia2 IS
      SELECT cab.oid_cabe,
             con.oid_soli_cabe,
             con.val_nume_soli,
             con.fec_fact,
             fac.num_docu_cont_inte,
             fac.num_docu_lega,
             fac.val_seri_docu_lega,
             fac.fec_fact fec_fact_refe,
             fac.val_tota_paga_loca,
             ftd.oid_tipo_docu,
             ftd.cod_tipo_docu,
             fac.fec_limi,
             fac.val_llav,
             fac.val_nume_auto,
             fac.val_nume_cont,
             refe.val_nume_soli pedi_refe,
             nvl(fac.val_nume_iden_fisc, fac.val_nume_iden_nnal) nit,
             spc.cod_peri peri_refe
        FROM fac_docum_conta_cabec cab,
             ped_solic_cabec       con,
             fac_docum_conta_cabec fac,
             fac_tipo_docum        ftd,
             ped_solic_cabec       refe,
             cra_perio             cp, 
             seg_perio_corpo       spc
       WHERE cab.soca_oid_soli_cabe = con.oid_soli_cabe
         AND con.soca_oid_docu_refe = fac.soca_oid_soli_cabe
         AND fac.tido_oid_tipo_docu = ftd.oid_tipo_docu
         AND cab.oid_cabe = p_oiddocumento
         and fac.soca_oid_soli_cabe=refe.oid_soli_cabe
         and refe.perd_oid_peri=cp.oid_peri
         and cp.peri_oid_peri=spc.oid_peri
         AND rownum = 1;
  
  BEGIN
  
    -- Abrimos el cursor y obtenemos los datos
    FOR r_referencia IN c_referencia
    LOOP
      p_seriedocumentoreferencia   := r_referencia.val_seri_docu_lega;
      p_numerodocumentoreferencia  := r_referencia.num_docu_lega;
      p_codigointernoreferencia    := r_referencia.num_docu_cont_inte;
      p_fechareferencia            := r_referencia.fec_fact_refe;
      p_montoreferencia            := r_referencia.val_tota_paga_loca;
      p_oidtipodocumentoreferencia := r_referencia.oid_tipo_docu;
      p_tipodocumentoreferencia    := r_referencia.cod_tipo_docu;
      p_fecha_limite_refe          := r_referencia.fec_limi;
      p_llave_refe                 := r_referencia.val_llav;
      p_nume_auto_refe             := r_referencia.val_nume_auto;
      p_nume_cont_refe             := r_referencia.val_nume_cont;   
      p_val_nume_soli_refe         := r_referencia.pedi_refe;
      p_val_nume_iden_fisc         := r_referencia.nit;
      p_cod_peri_refe              := r_referencia.peri_refe;
        
    END LOOP;
  
    IF p_seriedocumentoreferencia IS NULL AND p_fechareferencia IS NULL THEN
    
      FOR r_referencia1 IN c_referencia1
      LOOP
        p_seriedocumentoreferencia   := r_referencia1.val_seri_docu_lega;
        p_numerodocumentoreferencia  := r_referencia1.num_docu_lega;
        p_codigointernoreferencia    := r_referencia1.num_docu_cont_inte;
        p_fechareferencia            := r_referencia1.fec_fact_refe;
        p_montoreferencia            := r_referencia1.val_tota_paga_loca;
        p_oidtipodocumentoreferencia := r_referencia1.oid_tipo_docu;
        p_tipodocumentoreferencia    := r_referencia1.cod_tipo_docu;
        p_fecha_limite_refe          := r_referencia1.fec_limi;
        p_llave_refe                 := r_referencia1.val_llav;
        p_nume_auto_refe             := r_referencia1.val_nume_auto;
        p_nume_cont_refe             := r_referencia1.val_nume_cont;      
        p_val_nume_soli_refe         := r_referencia1.pedi_refe;
        p_val_nume_iden_fisc         := r_referencia1.nit;
        p_cod_peri_refe              := r_referencia1.peri_refe;
      END LOOP;
    
    END IF;
  
    IF p_seriedocumentoreferencia IS NULL AND p_fechareferencia IS NULL THEN
    
      FOR r_referencia2 IN c_referencia2
      LOOP
        p_seriedocumentoreferencia   := r_referencia2.val_seri_docu_lega;
        p_numerodocumentoreferencia  := r_referencia2.num_docu_lega;
        p_codigointernoreferencia    := r_referencia2.num_docu_cont_inte;
        p_fechareferencia            := r_referencia2.fec_fact_refe;
        p_montoreferencia            := r_referencia2.val_tota_paga_loca;
        p_oidtipodocumentoreferencia := r_referencia2.oid_tipo_docu;
        p_tipodocumentoreferencia    := r_referencia2.cod_tipo_docu;
        p_fecha_limite_refe          := r_referencia2.fec_limi;
        p_llave_refe                 := r_referencia2.val_llav;
        p_nume_auto_refe             := r_referencia2.val_nume_auto;
        p_nume_cont_refe             := r_referencia2.val_nume_cont;      
        p_val_nume_soli_refe         := r_referencia2.pedi_refe;
        p_val_nume_iden_fisc         := r_referencia2.nit;
        p_cod_peri_refe              := r_referencia2.peri_refe;
      END LOOP;
    
    END IF;
  
    UPDATE fac_regis_unico_venta
       SET val_seri_docu_refe  = p_seriedocumentoreferencia,
           val_nume_docu_refe  = p_numerodocumentoreferencia,
           tido_tipo_docu_refe = p_oidtipodocumentoreferencia,
           fec_emis_refe       = p_fechareferencia,
           fec_limi_refe       = p_fecha_limite_refe,
           val_llav_refe       = p_llave_refe,
           val_nume_auto_refe  = p_nume_auto_refe,
           val_nume_cont_refe  = p_nume_cont_refe,
           val_mont_refe       = p_montoreferencia,
           num_nit_refe        = p_val_nume_iden_fisc,
           camp_refe           = p_cod_peri_refe,
           val_nume_soli_refe  = p_val_nume_soli_refe
     WHERE dcca_oid_cabe = p_oiddocumento;
  
  END;

  /***************************************************************************
   Descripcion           : Valida la carga masiva de fletes
   Fecha Creacion    : 27/08/2014
   Autor                   : Sebastian Guerra
  ****************************************************************************/
  PROCEDURE ped_pr_valid_carga_masiv_flete(pncodigousuario VARCHAR2) IS
  
    CURSOR c_masiva_flete IS
      SELECT num_fila,
             cod_zona,
             val_mont_fijo,
             val_reca,
             val_mont_nfle,
             men_erro
        FROM ped_tempo_carg_masi_flet
       WHERE cod_usua = pncodigousuario;
  
    TYPE procesomasivaflete IS RECORD(
      numerofila   ped_tempo_carg_masi_flet.num_fila%TYPE,
      codigozona   ped_tempo_carg_masi_flet.cod_zona%TYPE,
      montofijo    ped_tempo_carg_masi_flet.val_mont_fijo%TYPE,
      recargo      ped_tempo_carg_masi_flet.val_reca%TYPE,
      montonoflete ped_tempo_carg_masi_flet.val_mont_nfle%TYPE,
      mensajeerror ped_tempo_carg_masi_flet.men_erro%TYPE);
  
    TYPE procesomasivafletetab IS TABLE OF procesomasivaflete;
    procesorecordn procesomasivafletetab;
  
    lnnumerofila   ped_tempo_carg_masi_flet.num_fila%TYPE;
    lsmensajeerror ped_tempo_carg_masi_flet.men_erro%TYPE;
    lnocurrencias  NUMBER(12);
    lnvalidacion   NUMBER(12);
    w_filas        NUMBER := 5000;
  
  BEGIN
    OPEN c_masiva_flete;
    LOOP
      FETCH c_masiva_flete BULK COLLECT
        INTO procesorecordn LIMIT w_filas;
      IF procesorecordn.count > 0 THEN
        FOR x IN procesorecordn.first .. procesorecordn.last
        LOOP
        
          lnnumerofila   := procesorecordn(x).numerofila;
          lsmensajeerror := '';
        
          --(1), Validamos si existe la zona
          IF (procesorecordn(x).codigozona IS NULL) THEN
            lsmensajeerror := lsmensajeerror ||
                              'Zona no Existe o no esta Activa,';
          ELSE
            SELECT COUNT(1)
              INTO lnocurrencias
              FROM zon_zona
             WHERE ind_acti = 1
               AND cod_zona = procesorecordn(x).codigozona;
            IF (lnocurrencias = 0) THEN
              lsmensajeerror := lsmensajeerror ||
                                'Zona no Existe o no esta Activa,';
            END IF;
          END IF;
        
          --(2), Validamos que el monto fijo contega valores numericos
          IF (procesorecordn(x).montofijo IS NULL) THEN
            lsmensajeerror := lsmensajeerror ||
                              'Monto Fijo solo puede contener valores numericos,';
          ELSE
            BEGIN
              lnvalidacion := to_number(procesorecordn(x).montofijo,
                                        '999999999.99');
            EXCEPTION
              WHEN OTHERS THEN
                lsmensajeerror := lsmensajeerror ||
                                  'Monto Fijo solo puede contener valores numericos,';
            END;
          END IF;
        
          --(3), Validamos que el recargo contega valores numericos
          IF (procesorecordn(x).recargo IS NULL) THEN
            lsmensajeerror := lsmensajeerror ||
                              'Recargo solo puede contener valores numericos,';
          ELSE
            BEGIN
              lnvalidacion := to_number(procesorecordn(x).recargo,
                                        '999999999.99');
            EXCEPTION
              WHEN OTHERS THEN
                lsmensajeerror := lsmensajeerror ||
                                  'Recargo solo puede contener valores numericos,';
            END;
          END IF;
        
          --(4), Validamos que el monto para no cobrar flete contega valores numericos
          IF (procesorecordn(x).montonoflete IS NULL) THEN
            lsmensajeerror := lsmensajeerror ||
                              'Monto para no cobrar flete solo puede contener valores numericos,';
          ELSE
            BEGIN
              lnvalidacion := to_number(procesorecordn(x).montonoflete,
                                        '999999999.99');
            EXCEPTION
              WHEN OTHERS THEN
                lsmensajeerror := lsmensajeerror ||
                                  'Monto para no cobrar flete solo puede contener valores numericos,';
            END;
          END IF;
        
          IF (length(lsmensajeerror) > 0) THEN
            lsmensajeerror := substr(lsmensajeerror,
                                     1,
                                     length(lsmensajeerror) - 1);
            UPDATE ped_tempo_carg_masi_flet
               SET est_regi = 0,
                   men_erro = lsmensajeerror
             WHERE cod_usua = pncodigousuario
               AND num_fila = procesorecordn(x).numerofila;
          END IF;
        
        END LOOP;
      END IF;
      EXIT WHEN c_masiva_flete%NOTFOUND;
    END LOOP;
    CLOSE c_masiva_flete;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'PED_PR_VALID_CARGA_MASIV_FLETE: (' ||
                              lnnumerofila || ' - ' || ln_sqlcode || ')' ||
                              ls_sqlerrm);
  END ped_pr_valid_carga_masiv_flete;

  /***************************************************************************
      Descripcion           : Actualizar carga masiva de fletes
      Fecha Creacion    : 27/08/2014
      Autor                   : Sebastian Guerra
  ***************************************************************************/
  PROCEDURE ped_pr_actua_carga_masiv_flete(pncodigousuario VARCHAR2) IS
  
    CURSOR c_masiva_flete IS
      SELECT num_fila,
             cod_zona,
             val_mont_fijo,
             val_reca,
             val_mont_nfle,
             men_erro
        FROM ped_tempo_carg_masi_flet
       WHERE est_regi = 1
         AND cod_usua = pncodigousuario;
  
    TYPE procesomasivaflete IS RECORD(
      numerofila   ped_tempo_carg_masi_flet.num_fila%TYPE,
      codigozona   ped_tempo_carg_masi_flet.cod_zona%TYPE,
      montofijo    ped_tempo_carg_masi_flet.val_mont_fijo%TYPE,
      recargo      ped_tempo_carg_masi_flet.val_reca%TYPE,
      montonoflete ped_tempo_carg_masi_flet.val_mont_nfle%TYPE,
      mensajeerror ped_tempo_carg_masi_flet.men_erro%TYPE);
  
    TYPE procesomasivafletetab IS TABLE OF procesomasivaflete;
    procesorecordn procesomasivafletetab;
  
    lnnumerofila ped_tempo_carg_masi_flet.num_fila%TYPE;
    w_filas      NUMBER := 5000;
  
  BEGIN
    OPEN c_masiva_flete;
    LOOP
      FETCH c_masiva_flete BULK COLLECT
        INTO procesorecordn LIMIT w_filas;
      IF procesorecordn.count > 0 THEN
        FOR x IN procesorecordn.first .. procesorecordn.last
        LOOP
        
          lnnumerofila := procesorecordn(x).numerofila;
        
          --(1), Si el campo monto para no cobrar flete es igual a cero
          IF (procesorecordn(x).montonoflete = 0) THEN
            INSERT INTO ped_flete
              (oid_flete,
               val_mont_fijo,
               val_tasa,
               val_flet_mini,
               val_flet_maxi,
               val_cont_entr,
               cana_oid_cana,
               tids_oid_tipo_desp,
               marc_oid_marc,
               clas_oid_clas,
               sbti_oid_subt_clie,
               ticl_oid_tipo_clie,
               tccl_oid_tipo_clas,
               zzon_zona,
               vepo_ubig)
            VALUES
              (ped_flet_seq.nextval,
               procesorecordn(x).montofijo,
               0,
               0,
               procesorecordn(x).montofijo,
               procesorecordn(x).recargo,
               2001,
               3,
               2003,
               NULL,
               NULL,
               2,
               NULL,
               (SELECT oid_zona
                  FROM zon_zona
                 WHERE cod_zona = procesorecordn(x).codigozona),
               NULL);
          END IF;
        
          --(2), Si el campo monto para no cobrar flete es mayor a cero
          IF (procesorecordn(x).montonoflete > 0) THEN
            INSERT INTO ped_flete
              (oid_flete,
               val_mont_fijo,
               val_tasa,
               val_flet_mini,
               val_flet_maxi,
               val_cont_entr,
               cana_oid_cana,
               tids_oid_tipo_desp,
               marc_oid_marc,
               clas_oid_clas,
               sbti_oid_subt_clie,
               ticl_oid_tipo_clie,
               tccl_oid_tipo_clas,
               zzon_zona,
               vepo_ubig)
            VALUES
              (ped_flet_seq.nextval,
               0,
               0,
               0,
               procesorecordn(x).montofijo,
               procesorecordn(x).recargo,
               2001,
               3,
               2003,
               NULL,
               NULL,
               2,
               NULL,
               (SELECT oid_zona
                  FROM zon_zona
                 WHERE cod_zona = procesorecordn(x).codigozona),
               NULL);
          
            INSERT INTO ped_detal_flete
              (oid_deta_flet,
               flet_oid_flet,
               rango_infe,
               rang_supe,
               valo_mont_fijo)
            VALUES
              (ped_defl_seq.nextval,
               ped_flet_seq.currval,
               0,
               procesorecordn(x).montonoflete,
               0);
          
            INSERT INTO ped_detal_flete
              (oid_deta_flet,
               flet_oid_flet,
               rango_infe,
               rang_supe,
               valo_mont_fijo)
            VALUES
              (ped_defl_seq.nextval,
               ped_flet_seq.currval,
               procesorecordn(x).montonoflete + 1,
               999999999,
               procesorecordn(x).montofijo);
          END IF;
        
          COMMIT;
        END LOOP;
      END IF;
      EXIT WHEN c_masiva_flete%NOTFOUND;
    END LOOP;
    CLOSE c_masiva_flete;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'PED_PR_ACTUA_CARGA_MASIV_FLETE: (' ||
                              lnnumerofila || ' - ' || ln_sqlcode || ')' ||
                              ls_sqlerrm);
  END ped_pr_actua_carga_masiv_flete;

  /***************************************************************************
      Descripcion           : Retorna el numero pedido sobre la campaña para el
                            ciclo de nuevas
      Fecha Creacion    : 03/10/2014
      Autor                   : Rosalvina Ramirez
  ***************************************************************************/
  FUNCTION ped_fn_obtie_nries
  (
    psoidclie       NUMBER,
    psoidniripedi   NUMBER,
    pscodigoperiodo VARCHAR2,
    ps_cod_pais     VARCHAR2
  ) RETURN VARCHAR2 IS
  
    lv_resul        VARCHAR2(2);
    ln_oidperiprime NUMBER;
    ln_rie          NUMBER;
    lv_codperiprime VARCHAR2(6);
    ln_oidperi      NUMBER;
  
  BEGIN
  
    ln_rie := psoidniripedi - 3;
  
    SELECT MIN(psc.perd_oid_peri)
      INTO ln_oidperiprime
      FROM ped_solic_cabec psc
     WHERE psc.tspa_oid_tipo_soli_pais =
           fin_pkg_gener.fin_fn_obtie_oid_solic_pais('C1')
       AND psc.grpr_oid_grup_proc = 5
       AND psc.val_tota_paga_loca > 0
       AND psc.esso_oid_esta_soli <> 4
       AND psc.ind_oc = 0
       AND psc.clie_oid_clie = psoidclie
       AND psc.perd_oid_peri >=
           fin_pkg_gener.fin_fn_obtie_oid_perio(ps_cod_pais,
                                                fin_pkg_gener.fin_fn_obtie_nsgte_campa(pscodigoperiodo,
                                                                                       -4));
  
    IF (fin_pkg_gener.fin_fn_obtie_oid_perio(ps_cod_pais,
                                             fin_pkg_gener.fin_fn_obtie_nsgte_campa(pscodigoperiodo,
                                                                                    - (ln_rie - 1))) =
       ln_oidperiprime) THEN
    
      lv_resul := 'N' || ln_rie;
      RETURN lv_resul;
    
    END IF;
  
    lv_codperiprime := fin_pkg_gener.fin_fn_obtie_codig_perio(ln_oidperiprime);
    ln_oidperi      := fin_pkg_gener.fin_fn_obtie_oid_perio(ps_cod_pais,
                                                            pscodigoperiodo);
  
    --Si es por campaña
    IF (nvl(gen_pkg_gener.gen_fn_param_pais(ps_cod_pais, 'PED', '018'), '0') = '1') THEN
    
      IF ((ln_oidperi >= ln_oidperiprime) AND
         (ln_oidperi <=
         fin_pkg_gener.fin_fn_obtie_oid_perio(ps_cod_pais,
                                                fin_pkg_gener.fin_fn_obtie_nsgte_campa(lv_codperiprime,
                                                                                       3)))) THEN
      
        lv_resul := 'NV';
      ELSE
      
        lv_resul := '';
      
      END IF;
    ELSE
      lv_resul := 'NV';
    
    END IF;
  
    RETURN lv_resul;
  
  EXCEPTION
  
    WHEN OTHERS THEN
      RETURN '';
    
  END ped_fn_obtie_nries;
  
/*************************************************************************************
Descripcion       : Procedimiento que carga el Reporte Detallado
                    Cobranza 31 dias para formato CSV
Fecha Creacion    : 27/01/2014
Autor             : Carlos Bazalar
************************************************************************************/
PROCEDURE ped_pr_detal_segui_consu_csv(
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

    CURSOR C_REPOR IS
    SELECT
      COD_REGI,
		  COD_ZONA,
		  COD_SECC,
		  COD_TERR,
		  COD_CLIE,
		  COD_DIGI_CTRL,
		  NOM_CLIE,
		  CAMP_INGR,          
		  CAMP_PRIM_PEDI,
		  CAMP_ULTI_PEDI,
		  SAL_DEUD_ANTE,
		  ESTADO,
		  VAL_NUME_PEDI,
		  DIRECCION,
		  COLONIA,
		  LOCALIDAD,
		  DEL_MUN,
		  DESC_ESTA,
		  val_cod_post,
		  PRIM_FONO,
		  SEGU_FONO,
		  COD_RECOM
    FROM PED_TMP_DETAL_SEGUI_CONSU
    ORDER BY
      COD_REGI,
      COD_ZONA,
      COD_SECC,
      COD_TERR,
      COD_CLIE;

    TYPE detalleUnidadesReg IS RECORD(
      COD_REGI              PED_TMP_DETAL_SEGUI_CONSU.COD_REGI%type,
		  COD_ZONA              PED_TMP_DETAL_SEGUI_CONSU.COD_ZONA%type,
		  COD_SECC              PED_TMP_DETAL_SEGUI_CONSU.COD_SECC%type,
		  COD_TERR              PED_TMP_DETAL_SEGUI_CONSU.COD_TERR%type,
		  COD_CLIE              PED_TMP_DETAL_SEGUI_CONSU.COD_CLIE%type,
		  COD_DIGI_CTRL         PED_TMP_DETAL_SEGUI_CONSU.COD_DIGI_CTRL%type,
		  NOM_CLIE              PED_TMP_DETAL_SEGUI_CONSU.NOM_CLIE%type,
		  CAMP_INGR             PED_TMP_DETAL_SEGUI_CONSU.CAMP_INGR%type,          
		  CAMP_PRIM_PEDI        PED_TMP_DETAL_SEGUI_CONSU.CAMP_PRIM_PEDI%type,
		  CAMP_ULTI_PEDI        PED_TMP_DETAL_SEGUI_CONSU.CAMP_ULTI_PEDI%type,
		  SAL_DEUD_ANTE         PED_TMP_DETAL_SEGUI_CONSU.SAL_DEUD_ANTE%type,
		  ESTADO                PED_TMP_DETAL_SEGUI_CONSU.ESTADO%type,
		  VAL_NUME_PEDI         PED_TMP_DETAL_SEGUI_CONSU.VAL_NUME_PEDI%type,
		  DIRECCION             PED_TMP_DETAL_SEGUI_CONSU.DIRECCION%type,
		  COLONIA               PED_TMP_DETAL_SEGUI_CONSU.COLONIA%type,
		  LOCALIDAD             PED_TMP_DETAL_SEGUI_CONSU.LOCALIDAD%type,
		  DEL_MUN               PED_TMP_DETAL_SEGUI_CONSU.DEL_MUN%type,
		  DESC_ESTA             PED_TMP_DETAL_SEGUI_CONSU.DESC_ESTA%type,
		  val_cod_post          PED_TMP_DETAL_SEGUI_CONSU.val_cod_post%type,
		  PRIM_FONO             PED_TMP_DETAL_SEGUI_CONSU.PRIM_FONO%type,
		  SEGU_FONO             PED_TMP_DETAL_SEGUI_CONSU.SEGU_FONO%type,
		  COD_RECOM             PED_TMP_DETAL_SEGUI_CONSU.COD_RECOM%type
    );

     TYPE detalleUnidadesRegTab IS TABLE OF detalleUnidadesReg;
     detalleUnidadesRegRecord detalleUnidadesRegTab;

     lbAbrirUtlFile  BOOLEAN;
BEGIN

lbAbrirUtlFile := true;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
    OPEN C_REPOR;
      LOOP
       FETCH C_REPOR BULK COLLECT INTO detalleUnidadesRegRecord LIMIT W_FILAS;
       IF lbAbrirUtlFile THEN
          GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
          psdirectorio := lsdirtempo;
          lbAbrirUtlFile := FALSE;
       END IF ;
       IF detalleUnidadesRegRecord.COUNT > 0 THEN
          FOR x IN detalleUnidadesRegRecord.FIRST .. detalleUnidadesRegRecord.LAST LOOP
                lslinea :=   '=T("'|| detalleUnidadesRegRecord(x).COD_REGI || '")' || ',' ||
                             '=T("'|| detalleUnidadesRegRecord(x).COD_ZONA || '")' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).cod_secc || '"' || ',' ||
                             '=T("'|| detalleUnidadesRegRecord(x).COD_TERR || '")' || ',' ||
                             '=T("'|| detalleUnidadesRegRecord(x).cod_clie || '")' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).cod_digi_ctrl || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).nom_clie || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).CAMP_INGR || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).CAMP_PRIM_PEDI || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).CAMP_ULTI_PEDI || '"' || ',' ||
                             '=T("'|| detalleUnidadesRegRecord(x).SAL_DEUD_ANTE || '")' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).ESTADO || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).VAL_NUME_PEDI || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).DIRECCION || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).COLONIA || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).LOCALIDAD || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).DEL_MUN || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).DESC_ESTA || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).val_cod_post || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).PRIM_FONO || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).SEGU_FONO || '"' || ',' ||                             
                             '=T("'|| detalleUnidadesRegRecord(x).COD_RECOM || '")'  ;
                 UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

          END LOOP;
        END IF;
        EXIT WHEN C_REPOR%NOTFOUND;
    END LOOP;
    CLOSE C_REPOR;

    IF NOT lbAbrirUtlFile THEN
        utl_file.fclose(V_HANDLE);
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm,1,250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR ped_pr_detal_segui_consu_csv: '||ls_sqlerrm);

END ped_pr_detal_segui_consu_csv ;

/*************************************************************************************
Descripcion       : Procedimiento que carga el Reporte de Avance Facturado de Programa 
                    de Reconocimiento VZ
Fecha Creacion    : 02/11/2015
Autor             : Gonzalo Huertas
************************************************************************************/
PROCEDURE ped_pr_afprv_repor_pedid
  (
    pscodigopais                IN VARCHAR2,
    pscodigoPeriodoInicio       IN VARCHAR2,
    pscodigoPeriodoFin          IN VARCHAR2
  ) IS
  
    lsFlagCodigoConsultoras VARCHAR2(1);
  
    CURSOR c_data(codigoPeriodo number) IS
  SELECT mc.cod_clie,
         gen_pkg_gener.gen_fn_clien_datos_oid(mc.oid_clie, 'COD_REGI') REGION,
         gen_pkg_gener.gen_fn_clien_datos_oid(mc.oid_clie, 'COD_ZONA') ZONA,
         codigoPeriodo codigo_periodo,
         nvl((select max(case
                          when PSC.almc_oid_alma = 2001 then
                           'GUA'
                          when PSC.almc_oid_alma = 2003 then
                           'VAL'
                          else
                           'SIN CDP'
                        end)
               from ped_solic_cabec PSC, SEG_PERIO_CORPo SPC, CRA_PERIO CP
              where PSC.CLIE_OID_CLIE = MC.OID_CLIE
                and PSC.PERD_OID_PERI = CP.OID_PERI
                and SPC.OID_PERI = CP.PERI_OID_PERI
                and SPC.COD_PERI = codigoPeriodo
                and PSC.TSPA_OID_TIPO_SOLI_PAIS in
                    (select tspa_oid_tipo_soli_pais
                       from INT_LAR_TIPO_SOLICI_PEDIDO_DIS)
                and decode((select nvl(count(oid_soli_cabe), 0)
                             from ped_solic_cabec
                            where soca_oid_soli_cabe = PSC.oid_soli_cabe
                              and ind_oc = 1),
                           1,
                           'NO',
                           'SI') = 'NO'),
             '') as CDP,
         nvl((select to_char(max(psc.fec_fact),'dd/mm/yyyy') fec_fact
               from ped_solic_cabec PSC, SEG_PERIO_CORPo SPC, CRA_PERIO CP
              where PSC.CLIE_OID_CLIE = MC.OID_CLIE
                and PSC.PERD_OID_PERI = CP.OID_PERI
                and SPC.OID_PERI = CP.PERI_OID_PERI
                and SPC.COD_PERI = codigoPeriodo
                and PSC.TSPA_OID_TIPO_SOLI_PAIS in
                    (select tspa_oid_tipo_soli_pais
                       from INT_LAR_TIPO_SOLICI_PEDIDO_DIS)
                and decode((select nvl(count(oid_soli_cabe), 0)
                             from ped_solic_cabec
                            where soca_oid_soli_cabe = PSC.oid_soli_cabe
                              and ind_oc = 1),
                           1,
                           'NO',
                           'SI') = 'NO'),
             '') as FECHA,
         nvl((select sum(psc.val_tota_paga_loca) / 1.12
               from ped_solic_cabec PSC, SEG_PERIO_CORPo SPC, CRA_PERIO CP
              where PSC.CLIE_OID_CLIE = MC.OID_CLIE
                and PSC.PERD_OID_PERI = CP.OID_PERI
                and SPC.OID_PERI = CP.PERI_OID_PERI
                and SPC.COD_PERI = codigoPeriodo
                and PSC.TSPA_OID_TIPO_SOLI_PAIS in
                    (select tspa_oid_tipo_soli_pais
                       from INT_LAR_TIPO_SOLICI_PEDIDO_DIS)
                and decode((select nvl(count(oid_soli_cabe), 0)
                             from ped_solic_cabec
                            where soca_oid_soli_cabe = PSC.oid_soli_cabe
                              and ind_oc = 1),
                           1,
                           'NO',
                           'SI') = 'NO'),
             '') as VENTA,
         nvl((select SUM(a2.val_tota_paga_loca)
               from ped_solic_cabec a2, cra_perio x, seg_perio_corpo y
              where a2.CLIE_OID_CLIE = mc.oid_clie
                and a2.PERD_OID_PERI = x.oid_peri
                and x.peri_oid_peri = y.oid_peri
                and y.cod_peri in (codigoPeriodo)
                and a2.TSPA_OID_TIPO_SOLI_PAIS in (2075)),
             '') as DEMANDADO,
         nvl((select sum(case
                          when PED_SOLIC_POSIC.val_prec_cata_unit_loca = 0 then
                           0
                          else
                           sum(PED_SOLIC_POSIC.VAL_PREC_TOTA_TOTA_LOCA) / 1.12
                        end) Monto
               from PED_SOLIC_CABEC,
                    PED_SOLIC_POSIC,
                    cra_perio,
                    SEG_PERIO_CORPO
              Where SEG_PERIO_CORPO.cod_peri = codigoPeriodo
                and PED_SOLIC_CABEC.clie_oid_clie = mc.oid_clie
                and PED_SOLIC_POSIC.espo_oid_esta_posi <> 2
                and PED_SOLIC_CABEC.tido_oid_tipo_docu = 1
                and PED_SOLIC_CABEC.TSPA_OID_TIPO_SOLI_PAIS in
                    (2099,
                     2101,
                     2107,
                     2109,
                     2117,
                     2119,
                     2121,
                     2122,
                     2124,
                     2131,
                     2160)
                and PED_SOLIC_POSIC.soca_oid_soli_cabe =
                    PED_SOLIC_CABEC.oid_soli_cabe
                and PED_SOLIC_CABEC.perd_oid_peri = CRA_PERIO.oid_peri
                and CRA_PERIO.peri_oid_peri = SEG_PERIO_CORPO.oid_peri
              group by PED_SOLIC_POSIC.val_prec_cata_unit_loca),
             '') as ATEN,
         nvl((select sum((PED_SOLIC_POSIC.NUM_UNID_DEMA_REAL -
                        PED_SOLIC_POSIC.NUM_UNID_ATEN) *
                        PED_SOLIC_POSIC.VAL_PREC_fact_UNIT_LOCA)
               from PED_SOLIC_CABEC,
                    PED_SOLIC_POSIC,
                    cra_perio,
                    SEG_PERIO_CORPO
              Where SEG_PERIO_CORPO.cod_peri = codigoPeriodo
                and PED_SOLIC_CABEC.clie_oid_clie = mc.oid_clie
                and PED_SOLIC_POSIC.espo_oid_esta_posi <> 2
                and PED_SOLIC_CABEC.tido_oid_tipo_docu = 1
                and PED_SOLIC_CABEC.TSPA_OID_TIPO_SOLI_PAIS in (2075)
                and PED_SOLIC_POSIC.soca_oid_soli_cabe =
                    PED_SOLIC_CABEC.oid_soli_cabe
                and PED_SOLIC_CABEC.perd_oid_peri = CRA_PERIO.oid_peri
                and CRA_PERIO.peri_oid_peri = SEG_PERIO_CORPO.oid_peri
                and PED_SOLIC_POSIC.NUM_UNID_DEMA_REAL -
                    PED_SOLIC_POSIC.NUM_UNID_ATEN <> 0),
             '') as FNA,
         nvl((select sum(case
                          when PED_SOLIC_POSIC.val_prec_cata_unit_loca = 0 then
                           0
                          else
                           sum(PED_SOLIC_POSIC.VAL_PREC_TOTA_TOTA_LOCA) / 1.12
                        end) Monto
               from PED_SOLIC_CABEC,
                    PED_SOLIC_POSIC,
                    PED_SOLIC_CABEC PED_SOLIC_CABEC_AUX,
                    cra_perio,
                    SEG_PERIO_CORPO,
                    cra_perio       cra_perio_aux,
                    SEG_PERIO_CORPO SEG_PERIO_CORPO_AUX
              Where SEG_PERIO_CORPO_AUX.cod_peri = codigoPeriodo
                and PED_SOLIC_CABEC.clie_oid_clie = mc.oid_clie
                and PED_SOLIC_CABEC.tspa_oid_tipo_soli_pais in (2116, 2120) -- FFNE
                and PED_SOLIC_POSIC.espo_oid_esta_posi <> 2
                and PED_SOLIC_CABEC.tido_oid_tipo_docu = 32 -- Nota de Crédito
                and PED_SOLIC_CABEC_AUX.TSPA_OID_TIPO_SOLI_PAIS = 2043
                and PED_SOLIC_POSIC.soca_oid_soli_cabe =
                    PED_SOLIC_CABEC.oid_soli_cabe
                and PED_SOLIC_CABEC.perd_oid_peri = CRA_PERIO.oid_peri
                and CRA_PERIO.peri_oid_peri = SEG_PERIO_CORPO.oid_peri
                and PED_SOLIC_CABEC.soca_oid_docu_refe =
                    PED_SOLIC_CABEC_AUX.oid_soli_cabe
                and PED_SOLIC_CABEC_AUX.perd_oid_peri =
                    Cra_Perio_Aux.oid_peri
                and Cra_Perio_Aux.peri_oid_peri =
                    Seg_Perio_Corpo_Aux.oid_peri
              group by PED_SOLIC_POSIC.val_prec_cata_unit_loca),
             '') as FFNE,
         nvl((select sum(case
                          when PED_SOLIC_POSIC.val_prec_cata_unit_loca = 0 then
                           0
                          else
                           sum(PED_SOLIC_POSIC.VAL_PREC_TOTA_TOTA_LOCA) / 1.12
                        end) Monto
               from PED_SOLIC_CABEC,
                    PED_SOLIC_POSIC,
                    PED_SOLIC_CABEC PED_SOLIC_CABEC_AUX,
                    cra_perio,
                    SEG_PERIO_CORPO,
                    cra_perio       cra_perio_aux,
                    SEG_PERIO_CORPO SEG_PERIO_CORPO_AUX
              Where SEG_PERIO_CORPO_AUX.cod_peri = codigoPeriodo
                and PED_SOLIC_CABEC.clie_oid_clie = mc.oid_clie
                and PED_SOLIC_CABEC.tspa_oid_tipo_soli_pais in (2102, 2103) -- DEV
                and PED_SOLIC_POSIC.espo_oid_esta_posi <> 2
                and PED_SOLIC_CABEC.tido_oid_tipo_docu = 32 -- Nota de Crédito
                and PED_SOLIC_CABEC_AUX.TSPA_OID_TIPO_SOLI_PAIS = 2043
                and PED_SOLIC_POSIC.soca_oid_soli_cabe =
                    PED_SOLIC_CABEC.oid_soli_cabe
                and PED_SOLIC_CABEC.perd_oid_peri = CRA_PERIO.oid_peri
                and CRA_PERIO.peri_oid_peri = SEG_PERIO_CORPO.oid_peri
                and PED_SOLIC_CABEC.soca_oid_docu_refe =
                    PED_SOLIC_CABEC_AUX.oid_soli_cabe
                and PED_SOLIC_CABEC_AUX.perd_oid_peri =
                    Cra_Perio_Aux.oid_peri
                and Cra_Perio_Aux.peri_oid_peri =
                    Seg_Perio_Corpo_Aux.oid_peri
              group by PED_SOLIC_POSIC.val_prec_cata_unit_loca),
             '') as DEV,
         nvl((select sum(case
                          when PED_SOLIC_POSIC.val_prec_cata_unit_loca = 0 then
                           0
                          else
                           sum(PED_SOLIC_POSIC.VAL_PREC_TOTA_TOTA_LOCA) / 1.12
                        end) Monto
               from PED_SOLIC_CABEC,
                    PED_SOLIC_POSIC,
                    PED_SOLIC_CABEC PED_SOLIC_CABEC_AUX,
                    cra_perio,
                    SEG_PERIO_CORPO,
                    cra_perio       cra_perio_aux,
                    SEG_PERIO_CORPO SEG_PERIO_CORPO_AUX
              Where SEG_PERIO_CORPO_AUX.cod_peri = codigoPeriodo
                and PED_SOLIC_CABEC.clie_oid_clie = mc.oid_clie
                and PED_SOLIC_CABEC.tspa_oid_tipo_soli_pais in (2125, 2156) -- ANU
                and PED_SOLIC_POSIC.espo_oid_esta_posi <> 2
                and PED_SOLIC_CABEC.tido_oid_tipo_docu = 32 -- Nota de Crédito
                and PED_SOLIC_CABEC_AUX.TSPA_OID_TIPO_SOLI_PAIS = 2043
                and PED_SOLIC_POSIC.soca_oid_soli_cabe =
                    PED_SOLIC_CABEC.oid_soli_cabe
                and PED_SOLIC_CABEC.perd_oid_peri = CRA_PERIO.oid_peri
                and CRA_PERIO.peri_oid_peri = SEG_PERIO_CORPO.oid_peri
                and PED_SOLIC_CABEC.soca_oid_docu_refe =
                    PED_SOLIC_CABEC_AUX.oid_soli_cabe
                and PED_SOLIC_CABEC_AUX.perd_oid_peri =
                    Cra_Perio_Aux.oid_peri
                and Cra_Perio_Aux.peri_oid_peri =
                    Seg_Perio_Corpo_Aux.oid_peri
              group by PED_SOLIC_POSIC.val_prec_cata_unit_loca),
             '') as ANU
    from mae_clien mc
   where GEN_PKG_GENER.GEN_FN_CLIEN_PERIO_ULTIM_PEDID(mc.OID_CLIE) >=pscodigoPeriodoInicio
     and GEN_PKG_GENER.gen_fn_clien_perio_ingre(mc.OID_CLIE) <= pscodigoPeriodoFin
     AND mc.cod_clie IN(SELECT COD_CLIE FROM PED_AFPRV_CONSU_TEMPO)
     order by cod_clie;

  TYPE interfazrec IS RECORD(
    v_cod_clie          PED_AFPRV_REPOR_TEMPO.cod_clie%TYPE,
    v_cod_regi          PED_AFPRV_REPOR_TEMPO.cod_regi%TYPE,
    v_cod_zona          PED_AFPRV_REPOR_TEMPO.cod_zona%TYPE,
    v_cod_peri          PED_AFPRV_REPOR_TEMPO.cod_peri%TYPE,
    v_cod_cdp           PED_AFPRV_REPOR_TEMPO.cod_cdp%TYPE,
    v_fec_peri          PED_AFPRV_REPOR_TEMPO.fec_peri%TYPE,
    v_ven_peri          PED_AFPRV_REPOR_TEMPO.ven_peri%TYPE,
    v_dem_peri          PED_AFPRV_REPOR_TEMPO.dem_peri%TYPE,
    v_ate_peri          PED_AFPRV_REPOR_TEMPO.ate_peri%TYPE,
    v_fna_peri          PED_AFPRV_REPOR_TEMPO.fna_peri%TYPE,
    v_ffn_peri          PED_AFPRV_REPOR_TEMPO.ffn_peri%TYPE,
    v_dev_peri          PED_AFPRV_REPOR_TEMPO.dev_peri%TYPE,
    v_anu_peri          PED_AFPRV_REPOR_TEMPO.anu_peri%TYPE);
  
    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
  
    w_filas NUMBER := 1000;   
    lnCantidadPeriodos NUMBER := 0;
    lsCampanya number;
    
  BEGIN
    
    delete from PED_AFPRV_REPOR_TEMPO;
    
    select gen_pkg_gener.gen_fn_devue_difer_perio_pais1(pscodigopais,pscodigoPeriodoInicio,pscodigoPeriodoFin) 
      into lnCantidadPeriodos 
      from dual;
      
  SELECT DECODE(COUNT(*), 0, '0', '1')
    INTO lsFlagCodigoConsultoras
    FROM PED_AFPRV_CONSU_TEMPO;   
      
      lsCampanya:= pscodigoPeriodoInicio;
  
  for peri in 0..lnCantidadPeriodos loop
      
    lsCampanya := gen_pkg_gener.gen_fn_perio_nsigu (psCodigoPais, pscodigoPeriodoInicio, peri);  
  
    OPEN c_data(lsCampanya);
    LOOP
         FETCH c_data BULK COLLECT
          INTO interfazrecord LIMIT w_filas;
          IF interfazrecord.count > 0 THEN
            FOR x IN interfazrecord.first .. interfazrecord.last
            LOOP  
              insert into PED_AFPRV_REPOR_TEMPO
                (cod_clie,
                 cod_regi,
                 cod_zona,
                 cod_peri,
                 cod_cdp,
                 fec_peri,
                 ven_peri,
                 dem_peri,
                 ate_peri,
                 fna_peri,
                 ffn_peri,
                 dev_peri,
                 anu_peri)
                values
                 (interfazrecord(x).v_cod_clie,
                  interfazrecord(x).v_cod_regi,
                  interfazrecord(x).v_cod_zona,
                  interfazrecord(x).v_cod_peri,
                  interfazrecord(x).v_cod_cdp,
                  interfazrecord(x).v_fec_peri,
                  interfazrecord(x).v_ven_peri,
                  interfazrecord(x).v_dem_peri,
                  interfazrecord(x).v_ate_peri,
                  interfazrecord(x).v_fna_peri,
                  interfazrecord(x).v_ffn_peri,
                  interfazrecord(x).v_dev_peri,
                  interfazrecord(x).v_anu_peri);  
            END LOOP;
          END IF;
      EXIT WHEN c_data%NOTFOUND;
     END LOOP;
     CLOSE c_data;
    end loop;
    
    --llenamos la siguiente tabla temporal, para el crosstab
    BEGIN 
      
    delete from PED_AFPRV_ACUM_TEMPO;
      
    --llenamos CDP 
    insert into PED_AFPRV_ACUM_TEMPO x
      (x.cod_clie, x.cod_regi, x.cod_zona, X.COD_ACUM, x.cod_peri, x.des_valo)
      select y.cod_clie, y.cod_regi, y.cod_zona, 'A_CDP', y.cod_peri, y.cod_cdp
        from PED_AFPRV_REPOR_TEMPO y
       order by cod_clie, cod_peri;
       
    --llenamos FECHA FACTURACION 
    insert into PED_AFPRV_ACUM_TEMPO x
      (x.cod_clie, x.cod_regi, x.cod_zona, X.COD_ACUM, x.cod_peri, x.des_valo)
      select y.cod_clie, y.cod_regi, y.cod_zona, 'B_FECHA FACTURACION', y.cod_peri, y.fec_peri
        from PED_AFPRV_REPOR_TEMPO y
       order by cod_clie, cod_peri;   
       
    --llenamos VENTA 
    insert into PED_AFPRV_ACUM_TEMPO x
      (x.cod_clie, x.cod_regi, x.cod_zona, X.COD_ACUM, x.cod_peri, x.des_valo)
      select y.cod_clie, y.cod_regi, y.cod_zona, 'C_VENTA', y.cod_peri, y.ven_peri
        from PED_AFPRV_REPOR_TEMPO y
       order by cod_clie, cod_peri; 
       
    --llenamos DEMANDADO 
    insert into PED_AFPRV_ACUM_TEMPO x
      (x.cod_clie, x.cod_regi, x.cod_zona, X.COD_ACUM, x.cod_peri, x.des_valo)
      select y.cod_clie, y.cod_regi, y.cod_zona, 'D_DEMANDADO', y.cod_peri, y.dem_peri
        from PED_AFPRV_REPOR_TEMPO y
       order by cod_clie, cod_peri;    
            
    --llenamos ATENDIDO 
    insert into PED_AFPRV_ACUM_TEMPO x
      (x.cod_clie, x.cod_regi, x.cod_zona, X.COD_ACUM, x.cod_peri, x.des_valo)
      select y.cod_clie, y.cod_regi, y.cod_zona, 'E_ATENDIDO', y.cod_peri, y.ate_peri
        from PED_AFPRV_REPOR_TEMPO y
       order by cod_clie, cod_peri;    
       
    --llenamos FNA
    insert into PED_AFPRV_ACUM_TEMPO x
      (x.cod_clie, x.cod_regi, x.cod_zona, X.COD_ACUM, x.cod_peri, x.des_valo)
      select y.cod_clie, y.cod_regi, y.cod_zona, 'F_FNA', y.cod_peri, y.fna_peri
        from PED_AFPRV_REPOR_TEMPO y
       order by cod_clie, cod_peri;           
       
    --llenamos FFNE
    insert into PED_AFPRV_ACUM_TEMPO x
      (x.cod_clie, x.cod_regi, x.cod_zona, X.COD_ACUM, x.cod_peri, x.des_valo)
      select y.cod_clie, y.cod_regi, y.cod_zona, 'G_FFNE', y.cod_peri, y.ffn_peri
        from PED_AFPRV_REPOR_TEMPO y
       order by cod_clie, cod_peri;   

    --llenamos DEV
    insert into PED_AFPRV_ACUM_TEMPO x
      (x.cod_clie, x.cod_regi, x.cod_zona, X.COD_ACUM, x.cod_peri, x.des_valo)
      select y.cod_clie, y.cod_regi, y.cod_zona, 'H_DEV', y.cod_peri, y.dev_peri
        from PED_AFPRV_REPOR_TEMPO y
       order by cod_clie, cod_peri;  
       
    --llenamos ANU
    insert into PED_AFPRV_ACUM_TEMPO x
      (x.cod_clie, x.cod_regi, x.cod_zona, X.COD_ACUM, x.cod_peri, x.des_valo)
      select y.cod_clie, y.cod_regi, y.cod_zona, 'I_ANU', y.cod_peri, y.anu_peri
        from PED_AFPRV_REPOR_TEMPO y
       order by cod_clie, cod_peri;                                               
           
     END;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ped_pr_afprv_repor_pedid: ' || ls_sqlerrm);
    
  END ped_pr_afprv_repor_pedid;
  
  /***************************************************************************
   Descripcion           : Valida la carga masiva Monto Minimo
   Fecha Creacion        : 04/01/2016
   Autor                 : Karina Valencia
  ****************************************************************************/
  PROCEDURE ped_pr_valid_carga_masiv_mtmin(pncodigousuario VARCHAR2) IS
  
    CURSOR c_masiva_mtmin IS
      SELECT NUM_FILA,
             COD_ZONA,
             VAL_NIV1,
             VAL_NIV2,
             VAL_NIV3,
             MONT_NOMI,
             TIP_CLIE,
             MEN_ERRO
        FROM PED_TEMPO_CARG_MONT_MIN
       WHERE cod_usua = pncodigousuario;
  
    TYPE procesomasivamtmin IS RECORD(
      numerofila   PED_TEMPO_CARG_MONT_MIN.NUM_FILA%TYPE,
      codigozona   PED_TEMPO_CARG_MONT_MIN.COD_ZONA%TYPE,
      valorn1      PED_TEMPO_CARG_MONT_MIN.VAL_NIV1%TYPE,
      valorn2      PED_TEMPO_CARG_MONT_MIN.VAL_NIV2%TYPE,
      valorn3      PED_TEMPO_CARG_MONT_MIN.VAL_NIV3%TYPE,
      montonomimal PED_TEMPO_CARG_MONT_MIN.MONT_NOMI%TYPE,
      tipocliente  PED_TEMPO_CARG_MONT_MIN.TIP_CLIE%TYPE,
      mensajeerror PED_TEMPO_CARG_MONT_MIN.MEN_ERRO%TYPE);
  
    TYPE procesomasivamtmintab IS TABLE OF procesomasivamtmin;
    procesorecordn procesomasivamtmintab;
  
    lnnumerofila   PED_TEMPO_CARG_MONT_MIN.num_fila%TYPE;
    lsmensajeerror PED_TEMPO_CARG_MONT_MIN.men_erro%TYPE;
    lnocurrencias  NUMBER(12);
    lnvalidacion   NUMBER(12);
    w_filas        NUMBER := 5000;
  
  BEGIN
    OPEN c_masiva_mtmin;
    LOOP
      FETCH c_masiva_mtmin BULK COLLECT
        INTO procesorecordn LIMIT w_filas;
      IF procesorecordn.count > 0 THEN
        FOR x IN procesorecordn.first .. procesorecordn.last
        LOOP
        
          lnnumerofila   := procesorecordn(x).numerofila;
          lsmensajeerror := '';
        
          --(1), Validamos si existe la zona
          IF (procesorecordn(x).codigozona IS NULL) THEN
            lsmensajeerror := lsmensajeerror ||
                              'Zona no Existe o no esta Activa,';
          ELSE
            SELECT COUNT(1)
              INTO lnocurrencias
              FROM zon_zona
             WHERE ind_acti = 1
               AND cod_zona = procesorecordn(x).codigozona;
            IF (lnocurrencias = 0) THEN
              lsmensajeerror := lsmensajeerror ||
                                'Zona no Existe o no esta Activa,';
            END IF;
          END IF;
        
          --(2), Validamos que el Valor N1 contega valores numericos
          IF (procesorecordn(x).valorn1 IS NULL) THEN
            lsmensajeerror := lsmensajeerror ||
                              'Nivel 1 solo puede contener valores numericos,';
          ELSE
            BEGIN
              lnvalidacion := to_number(procesorecordn(x).valorn1, '999999999.99');
            EXCEPTION
              WHEN OTHERS THEN
                lsmensajeerror := lsmensajeerror ||
                                  'Nivel 1 solo puede contener valores numericos,';
            END;
          END IF;
          
          --(3), Validamos que el Valor N2 contega valores numericos
          IF (procesorecordn(x).valorn2 IS NULL) THEN
            lsmensajeerror := lsmensajeerror ||
                              'Nivel 2 solo puede contener valores numericos,';
          ELSE
            BEGIN
              lnvalidacion := to_number(procesorecordn(x).valorn2, '999999999.99');
            EXCEPTION
              WHEN OTHERS THEN
                lsmensajeerror := lsmensajeerror ||
                                  'Nivel 2 solo puede contener valores numericos,';
            END;
          END IF;
          
           --(3), Validamos que el Valor N3 contega valores numericos
          IF (procesorecordn(x).valorn3 IS NULL) THEN
            lsmensajeerror := lsmensajeerror ||
                              'Nivel 3 solo puede contener valores numericos,';
          ELSE
            BEGIN
              lnvalidacion := to_number(procesorecordn(x).valorn3, '999999999.99');
            EXCEPTION
              WHEN OTHERS THEN
                lsmensajeerror := lsmensajeerror ||
                                  'Nivel 3 solo puede contener valores numericos,';
            END;
          END IF;
          
        
          --(3), Validamos que el Monto Nominal contega valores numericos
          IF (procesorecordn(x).montonomimal IS NULL) THEN
            lsmensajeerror := lsmensajeerror ||
                              'Monto Nomimal solo puede contener valores numericos,';
          ELSE
            BEGIN
              lnvalidacion := to_number(procesorecordn(x).montonomimal, '999999999.99');
            EXCEPTION
              WHEN OTHERS THEN
                lsmensajeerror := lsmensajeerror ||
                                  'Monto Nomimal solo puede contener valores numericos,';
            END;
          END IF;
        
          --(4), Validamos que el Tipo Cliente contega valores oficina, Normal
          IF (procesorecordn(x).tipocliente IS NULL) THEN
            lsmensajeerror := lsmensajeerror ||
                              'Tipo Cliente solo puede ser NORMAL u OFICINA,';
          ELSE
            IF (procesorecordn(x).tipocliente !='NORMAL' 
              AND procesorecordn(x).tipocliente !='OFICINA' )THEN
                  lsmensajeerror := lsmensajeerror ||
                              'Tipo Cliente solo puede ser NORMAL u OFICINA,';
              END IF;
          END IF;
        
          IF (length(lsmensajeerror) > 0) THEN
            lsmensajeerror := substr(lsmensajeerror,
                                     1,
                                     length(lsmensajeerror) - 1);
            UPDATE PED_TEMPO_CARG_MONT_MIN
               SET est_regi = 0,
                   men_erro = lsmensajeerror
             WHERE cod_usua = pncodigousuario
               AND num_fila = procesorecordn(x).numerofila;
          END IF;
        
        END LOOP;
      END IF;
      EXIT WHEN c_masiva_mtmin%NOTFOUND;
    END LOOP;
    CLOSE c_masiva_mtmin;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123, 'PED_PR_VALID_CARGA_MASIV_MTMIN: (' ||
                              lnnumerofila || ' - ' || ln_sqlcode || ')' ||
                              ls_sqlerrm);
  END ped_pr_valid_carga_masiv_mtmin;
    
  /***************************************************************************
      Descripcion           : Actualizar carga masiva Monto Minimo
      Fecha Creacion        : 04/01/2016
      Autor                 : Karina Valencia
  ***************************************************************************/
  PROCEDURE ped_pr_actua_carga_masiv_mtmin(pncodigousuario VARCHAR2) IS
  
    CURSOR c_masiva_mtmin IS
      SELECT NUM_FILA,
               COD_ZONA,
               VAL_NIV1,
               VAL_NIV2,
               VAL_NIV3,
               MONT_NOMI,
               decode(TIP_CLIE,'NORMAL','1','OFICINA','21')AS TIPO,
               MEN_ERRO
          FROM PED_TEMPO_CARG_MONT_MIN
         WHERE est_regi = 1
         AND cod_usua = pncodigousuario;
  
    TYPE procesomasivamontomin IS RECORD(
      numerofila   PED_TEMPO_CARG_MONT_MIN.NUM_FILA%TYPE,
      codigozona   PED_TEMPO_CARG_MONT_MIN.COD_ZONA%TYPE,
      valorn1      PED_TEMPO_CARG_MONT_MIN.VAL_NIV1%TYPE,
      valorn2      PED_TEMPO_CARG_MONT_MIN.VAL_NIV2%TYPE,
      valorn3      PED_TEMPO_CARG_MONT_MIN.VAL_NIV3%TYPE,
      montonomimal PED_TEMPO_CARG_MONT_MIN.MONT_NOMI%TYPE,
      tipocliente  PED_TEMPO_CARG_MONT_MIN.TIP_CLIE%TYPE,
      mensajeerror PED_TEMPO_CARG_MONT_MIN.MEN_ERRO%TYPE);
  
    TYPE procesomasivamtmintab IS TABLE OF procesomasivamontomin;
    procesorecordn procesomasivamtmintab;
  
    lnnumerofila PED_TEMPO_CARG_MONT_MIN.num_fila%TYPE;
    w_filas      NUMBER := 5000;
  
  BEGIN
    --Eliminar datos de la tabla ped_monto_minim 
    Delete from PED_MONTO_MINIM where TCCL_OID_TIPO_CLAS is null;
    
    OPEN c_masiva_mtmin;
    LOOP
      FETCH c_masiva_mtmin BULK COLLECT
        INTO procesorecordn LIMIT w_filas;
      IF procesorecordn.count > 0 THEN
        FOR x IN procesorecordn.first .. procesorecordn.last
        LOOP        
                  
         insert into PED_MONTO_MINIM
            (OID_MONT_MINI,
             VAL_NIV1,
             VAL_NIV2,
             VAL_NIV3,
             VAL_RECA,
             VAL_MONT_MINI_NOMI,
             CLAS_OID_CLAS,
             TSPA_OID_TIPO_SOLI_PAIS,
             SBTI_OID_SUBT_CLIE,
             TICL_OID_TIPO_CLIE,
             TCCL_OID_TIPO_CLAS,
             ZZON_OID_ZONA,
             ZORG_OID_REGI)
          values
            (ped_momi_seq.nextval,
             to_number(procesorecordn(x).valorn1, '999999999.99'),
             to_number(procesorecordn(x).valorn2, '999999999.99'),
             to_number(procesorecordn(x).valorn3, '999999999.99'),            
             0.00,
             to_number(procesorecordn(x).montonomimal, '999999999.99'),           
             NULL,
             2075,
             to_number(procesorecordn(x).tipocliente),
             2,
             NULL,
             (SELECT oid_zona
                  FROM zon_zona
                 WHERE cod_zona = procesorecordn(x).codigozona),   
             (select zorg_oid_regi
               from ZON_ZONA
              where cod_zona = procesorecordn(x).codigozona)  );
        commit;
        END LOOP;
      END IF;
      EXIT WHEN c_masiva_mtmin%NOTFOUND;
    END LOOP;
    CLOSE c_masiva_mtmin;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123, 'ped_pr_actua_carga_masiv_mtmin: (' ||
                              lnnumerofila || ' - ' || ln_sqlcode || ')' || ls_sqlerrm);
  END ped_pr_actua_carga_masiv_mtmin;


END ped_pkg_proce;
/
