CREATE OR REPLACE PACKAGE imp_pkg_proce_impre IS

  nota_credito CONSTANT VARCHAR(3) := 'NC';
  nota_debito  CONSTANT VARCHAR(3) := 'ND';

  codigo_canal VARCHAR2(10) := 'VD';
  codigo_marca VARCHAR2(10) := 'T';

  -- Nros de Paquetes Documentarios usados segun configuracion
  nro_paquete_normal          CONSTANT NUMBER := 1;
  nro_paquete_boleta_despacho CONSTANT NUMBER := 2;
  nro_paquete_boleta_recojo   CONSTANT NUMBER := 3;
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(1500);
  w_filas    NUMBER := 5000;

  /**************************************************************************
  Descripcion         : Proceso que prepara genera el XML de ultimas noticias.
  Fecha Creacion      : 11/05/2010
  Fecha Modificacion  : 11/05/2010
  Autor               : Jorge Yepez
  Version             : Final (Alfa|Final)
  ***************************************************************************/
  PROCEDURE imp_pr_proce_spool
  (
    pscodigopais       VARCHAR2,
    pscodigoperiodo    VARCHAR2,
    psfechafacturacion VARCHAR2,
    pscodigoproceso    VARCHAR2,
    pscodigopaquete    VARCHAR2,
    psusuario          VARCHAR2
  );

  /**************************************************************************
  Descripcion         : Proceso que prepara genera el XML de ultimas noticias.
  Fecha Creacion      : 11/05/2010
  Fecha Modificacion  : 11/05/2010
  Autor               : Jorge Yepez
  Version             : Final (Alfa|Final)
  ***************************************************************************/
  PROCEDURE imp_pr_proce_notic
  (
    p_codigopais            IN VARCHAR2,
    p_codigoperiodo         IN VARCHAR2,
    p_fechafacturacion      IN VARCHAR2,
    p_numerolotefacturacion NUMBER
  );

  /**************************************************************************
  Descripcion         : Proceso que prepara genera el XML de ultimas noticias. Coorperativo
  Fecha Creacion      : 06/06/2013
  Fecha Modificacion  : 06/06/2013
  Autor               : Sergio Buchelli
  Version             : Final (Alfa|Final)
  ***************************************************************************/
  PROCEDURE imp_pr_proce_notic_corpo
  (
    p_codperi     IN VARCHAR2,
    p_oidperi     IN NUMBER,
    p_oidclie     IN NUMBER,
    p_oidsoli     IN NUMBER,
    p_numlotefact IN VARCHAR2
  );

  /**************************************************************************
  Descripcion : Devuelve 1 si el mensaje aplica para el cliente o 0 si no
  Fecha Creacion : 13/01/2016
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION imp_fn_mens_ua_clie
  (
    p_oidclie NUMBER,
    p_codperi VARCHAR2,
    p_codmens VARCHAR2
  ) RETURN NUMBER;

  /**************************************************************************
  Descripcion : Devuelve 1 si el mensaje aplica para el cliente o 0 si no
  Fecha Creacion : 13/01/2016
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION imp_fn_mens_tipo_clie
  (
    p_oidclie NUMBER,
    p_codperi VARCHAR2,
    p_codmens VARCHAR2
  ) RETURN NUMBER;

  /**************************************************************************
  Descripcion : Devuelve 1 si el mensaje aplica para el cliente o 0 si no
  la Hora en formato dd/mm/yyyy
  Fecha Creacion : 07/02/2012
  Autor : Jorge Yepez
  ***************************************************************************/
  FUNCTION imp_fn_mens_pedid_clie
  (
    p_oidsoli NUMBER,
    p_codperi VARCHAR2,
    p_codmens VARCHAR2
  ) RETURN NUMBER;

  /**************************************************************************
  Descripcion : Devuelve 1 si el mensaje aplica para el cliente o 0 si no
  la Hora en formato dd/mm/yyyy
  Fecha Creacion : 07/02/2012
  Autor : Jorge Yepez
  ***************************************************************************/
  FUNCTION imp_fn_mens_lista_clie
  (
    p_oidclie NUMBER,
    p_oidsoli NUMBER,
    p_codperi VARCHAR2,
    p_codmens VARCHAR2
  ) RETURN NUMBER;

  /***************************************************************************
  Descripcion       : Procedimiemto que implementa la logica en IMP_PR_PROCE_DETAL_FACTU4_REGI
                      para su insercion en tablas cabecera y detalle: IMP_PRINT_PEDID_RESUM y
                      IMP_PRINT_PEDID_DETAL - PAIS COLOMBIA
  
  Parametros        : Codigo de pais, codigo de periodo, codigo de marca, codigo de canal
  Fecha Creacion    : 09/12/2015
  Autor             : Gonzalo Javier Huertas Agurto
  ***************************************************************************/
  PROCEDURE imp_pr_proce_pedid_co
  (
    p_codigopais          IN VARCHAR2,
    p_codigoperiodo       IN VARCHAR2,
    p_fechafacturacion    IN VARCHAR2,
    p_oidzona             NUMBER,
    pscodigotransaccional VARCHAR2,
    pscodigopaquete       VARCHAR2,
    pscodigousuario       VARCHAR2
  );

  /**************************************************************************
  Descripcion         : Proceso que obtiene la informacion para generar la
                        seccion de detalle de factura modificada por Proyecto UNO y
                        optimizada para lanzarse en paralelo por region.
  Fecha Creacion      : 13/09/2011
  Fecha Modificacion  : 13/09/2011
  Autor               : Jorge Yepez Reyes - cahurtado@belcorp.biz
  Parametros Entrada  :
      p_codigoPais            : Codigo del pais
      p_codigoPeriodo         : Periodo del cupon
      p_fechaFacturacion      : Fecha de Facturacion
  Version             : Final (Alfa|Final)
  ***************************************************************************/
  PROCEDURE imp_pr_proce_detal_factu
  (
    p_codigopais       IN VARCHAR2,
    p_codigoperiodo    IN VARCHAR2,
    p_oidzona          NUMBER,
    p_fechafacturacion IN VARCHAR2
  );

  /**************************************************************************
  Descripcion         : Proceso que obtiene la informacion para generar la
                        seccion de detalle de factura modificada por Proyecto UNO y
                        optimizada para lanzarse en paralelo por region.
  Fecha Creacion      : 13/09/2011
  Fecha Modificacion  : 13/09/2011
  Autor               : Jorge Yepez Reyes - cahurtado@belcorp.biz
  Parametros Entrada  :
      p_codigoPais            : Codigo del pais
      p_codigoPeriodo         : Periodo del cupon
      p_fechaFacturacion      : Fecha de Facturacion
  Version             : Final (Alfa|Final)
  ***************************************************************************/
  PROCEDURE imp_pr_proce_detal_factu_cl
  (
    p_codigopais       IN VARCHAR2,
    p_codigoperiodo    IN VARCHAR2,
    p_fechafacturacion IN VARCHAR2,
    p_oidzona          NUMBER
  );

  /**************************************************************************
  Descripcion         : Proceso que obtiene la informacion para generar la
                        seccion de detalle de factura modificada por Proyecto UNO y
                        optimizada para lanzarse en paralelo por region.
  Fecha Creacion      : 13/09/2011
  Fecha Modificacion  : 13/09/2011
  Autor               : Jorge Yepez Reyes - cahurtado@belcorp.biz
  Parametros Entrada  :
      p_codigoPais            : Codigo del pais
      p_codigoPeriodo         : Periodo del cupon
      p_fechaFacturacion      : Fecha de Facturacion
  Version             : Final (Alfa|Final)
  ***************************************************************************/
  PROCEDURE imp_pr_proce_detal_factu_co
  (
    p_codigopais       IN VARCHAR2,
    p_codigoperiodo    IN VARCHAR2,
    p_fechafacturacion IN VARCHAR2,
    p_oidzona          NUMBER
  );

  /***************************************************************************
  Descripcion       : Proceso que genera los cupones de las consultoras que
                      han pasado pedido en un periodo y fecha particular.
  Fecha Creacion    : 31/03/2010
  Autor             : Carlos Hurtado Ramirez
  ***************************************************************************/
  PROCEDURE imp_pr_proce_cupon
  (
    p_codigopais       VARCHAR2,
    p_codigoperiodo    VARCHAR2,
    p_oidzona          NUMBER,
    p_fechafacturacion VARCHAR2
  );

  /**************************************************************************
  Descripcion         : Genera de orden de compra simplificada
  Fecha Creacion      : 04/10/2010
  Autor               : Sergio Buchelli
  Parametros Entrada  :
      p_codigoPais            : Codigo del pais.
      p_codigoPeriodo         : Codigo periodo
      p_fechaFacturacion     : Fecha Facturacion
  /**************************************************************************/
  PROCEDURE imp_pr_proce_orden_compr
  (
    p_codigopais       VARCHAR2,
    p_codigoperiodo    VARCHAR2,
    p_fechafacturacion VARCHAR2,
    p_oidzona          NUMBER
  );

  /**************************************************************************
  Descripcion         : Realiza el envio del archivo de documento factura
                        IMP-14
  Fecha Creacion      : 10/11/2011
  Autor               : Sergio Buchelli
  Parametros Entrada  :
      p_codigoPais            : Codigo del pais.
      p_codigoPeriodo         : Codigo periodo
      p_fechaFacturacion     : Fecha Facturacion
  /**************************************************************************/
  PROCEDURE imp_pr_proce_factu_laser
  (
    p_codigopais       VARCHAR2,
    p_codigomarca      VARCHAR2,
    p_codigocanal      VARCHAR2,
    p_codigoperiodo    VARCHAR2,
    p_fechafacturacion VARCHAR2,
    p_nombrearchivo    VARCHAR2,
    p_directorio       VARCHAR2
  );

  /**************************************************************************
  Descripcion         : Genera la FACTURA
  Fecha Creacion      : 21/07/2011
  Autor               : Sergio Buchelli
  Parametros Entrada  :
      p_codigoPais            : Codigo del pais.
      p_codigoPeriodo         : Codigo periodo
      p_fechaFacturacion     : Fecha Facturacion
  /**************************************************************************/
  PROCEDURE imp_pr_proce_paque_docum_fac_v
  (
    p_codigopais       VARCHAR2,
    p_codigomarca      VARCHAR2,
    p_codigocanal      VARCHAR2,
    p_codigoperiodo    VARCHAR2,
    p_fechafacturacion VARCHAR2
  );

  /**************************************************************************
  Descripcion         : Genera la FACTURA
  Fecha Creacion      : 21/07/2011
  Autor               : Sergio Buchelli
  Parametros Entrada  :
      p_codigoPais            : Codigo del pais.
      p_codigoPeriodo         : Codigo periodo
      p_fechaFacturacion     : Fecha Facturacion
  /**************************************************************************/
  PROCEDURE imp_pr_proce_paque_docum_fac_p
  (
    p_codigopais       VARCHAR2,
    p_codigomarca      VARCHAR2,
    p_codigocanal      VARCHAR2,
    p_codigoperiodo    VARCHAR2,
    p_fechafacturacion VARCHAR2
  );

  /**************************************************************************
  Descripcion         : Genera la FACTURA
  Fecha Creacion      : 21/07/2011
  Autor               : Sergio Buchelli
  Parametros Entrada  :
      p_codigoPais            : Codigo del pais.
      p_codigoPeriodo         : Codigo periodo
      p_fechaFacturacion     : Fecha Facturacion
  /**************************************************************************/
  PROCEDURE imp_pr_proce_paque_docum_factu
  (
    p_codigopais       VARCHAR2,
    p_codigomarca      VARCHAR2,
    p_codigocanal      VARCHAR2,
    p_codigoperiodo    VARCHAR2,
    p_fechafacturacion VARCHAR2
  );

  /**************************************************************************
  Descripcion         : Realiza EL REEMPAZO DE CARACTERES ESPECIALES
                        IMP-14
  Fecha Creacion      : 10/11/2011
  Autor               : Sergio Buchelli
  /***************************************************************************/
  PROCEDURE imp_pr_reemp_carac_docum_factu;

  /**************************************************************************
  Descripcion         : Genera el archivo de documento factura laser
  Fecha Creacion      : 10/11/2011
  Autor               : Sergio Buchelli
  Parametros Entrada  :
      p_codigoPais            : Codigo del pais.
      p_nombreArchivo         : Nombre del archivo de texto.
      p_directorio            : Ruta de generacion del archivo.
  ***************************************************************************/
  PROCEDURE imp_pr_gener_archi_factu_laser
  (
    p_codigopais    VARCHAR2,
    p_nombrearchivo VARCHAR2,
    p_directorio    VARCHAR2
  );

  /**************************************************************************
  Descripcion         : Realiza el 'merge' entre el archivo XML del SiCC, el archivo
                        XML de Cupon de Pago y los bloques XML de las Ultimas Noticias
         Privilege, el resultado final va a la tabla IMP_PAQUE_DOCUM.
  Fecha Creacion      : 20/02/2006
  Fecha Modificacion  : 06/09/2006
  Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
  Version             : Final (Alfa|Final)
  Cambios Importantes : 05/11/2008 Se modifico el procedimiento para que invoque
                        a la compaginacion dinamica o estatica (forma antigua, sin
                        uso de tabla de configuracion para el orden de los formatos).
  ***************************************************************************/
  PROCEDURE imp_pr_proce_compa_final
  (
    p_valnumesoli NUMBER,
    p_codclie     VARCHAR2
  );

  PROCEDURE imp_pr_proce_bolet_honor
  (
    pscodigopais       VARCHAR2,
    pscodigoperiodo    VARCHAR2,
    psfechafacturacion VARCHAR2,
    pscodigousuario    VARCHAR2
  );

  /**************************************************************************
  Descripcion : Devuelve la descripcion del producto de planit
  la Hora en formato dd/mm/yyyy
  Fecha Creacion : 12/12/2012
  Autor : Jorge Yepez
  ***************************************************************************/
  FUNCTION imp_fn_desc_produ
  (
    p_codigopais VARCHAR,
    p_oidprod    NUMBER
  ) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion : Devuelve el xml con la oportunidad de ahorro de las ultimas
  6 campa?as para inclrustarlo en el detalle de factura
  Fecha Creacion : 29/04/2013
  Autor : Jorge Yepez
  ***************************************************************************/
  FUNCTION imp_fn_xml_opor_ahor
  (
    p_codigoperiodo VARCHAR,
    p_oidperi       NUMBER,
    p_oidclie       NUMBER
  ) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion : Devuelve el numero de OCS a enviar a las consultoras
  la Hora en formato dd/mm/yyyy
  Fecha Creacion : 02/07/2012
  Autor : Jorge Yepez
  ***************************************************************************/
  FUNCTION imp_fn_devue_num_ocs
  (
    p_codigopais VARCHAR,
    p_oidclie    NUMBER
  ) RETURN NUMBER;

  /***************************************************************************
  Descripcion       : Funcion que retorna el parametro de impresion de un
                      determinado proceso de impresion en base al nombre del
                      parametro, retorna NULL en caso no encuentre el valor.
  
  Fecha Creacion    : 07/03/2008
  Autor             : Carlos Hurtado
  ***************************************************************************/
  FUNCTION imp_fn_obtie_param_impre
  (
    pscodigoproceso   VARCHAR2,
    psnombreparametro VARCHAR2
  ) RETURN VARCHAR2;

  PROCEDURE imp_pr_print_compr
  (
    p_codigoperiodo       IN VARCHAR2,
    p_fechafacturacion    IN VARCHAR2,
    pscodigotransaccional VARCHAR2,
    pscodigopaquete       VARCHAR2,
    pscodigousuario       VARCHAR2
  );
  PROCEDURE imp_pr_proce_print_execu
  (
    pscodigopais       IN VARCHAR2,
    pscodigoperiodo    IN VARCHAR2,
    psfechafacturacion IN VARCHAR2,
    psnumerolote       IN VARCHAR2, --codigo de proceso,
    psusuario          IN VARCHAR2
  );
  PROCEDURE imp_pr_print_spool_orige_secci;

  PROCEDURE imp_pr_print_spool
  (
    pscodigopais       VARCHAR2,
    pscodigoperiodo    VARCHAR2,
    psfechafacturacion VARCHAR2,
    pnoidzona          NUMBER,
    pscodigoproceso    VARCHAR2,
    psusuario          VARCHAR2
  );
  PROCEDURE imp_pr_print_spool_final
  (
    pscodigopais       VARCHAR2,
    pscodigoperiodo    VARCHAR2,
    psfechafacturacion VARCHAR2,
    pscodigoproceso    VARCHAR2,
    psusuario          VARCHAR2,
    psvalnumesoli      NUMBER,
    pnoidclie          NUMBER
  );
  FUNCTION imp_fn_print_devue_paque(pscodigoformulario VARCHAR)
    RETURN VARCHAR2;
  /**************************************************************************
  Descripcion         : Genera la boleta de despacho
  Fecha Creacion      : 04/10/2010
  Autor               : Sergio Buchelli
  Parametros Entrada  :
      p_codigoPais            : Codigo del pais.
      p_codigoPeriodo         : Codigo periodo
      p_fechaFacturacion     : Fecha Facturacion
  /**************************************************************************/
  PROCEDURE imp_pr_print_bolet_despa
  (
    pscodigopais          VARCHAR2,
    pscodigoperiodo       VARCHAR2,
    psfechafacturacion    VARCHAR2,
    pnoidzona             NUMBER,
    pscodigotransaccional VARCHAR2,
    pscodigousuario       VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Procedimiemto que implementa la logica en JAVA
  Parametros        : Codigo de pais, codigo de periodo, codigo de marca, codigo de canal
  Fecha Creacion    : 17/09/2014
  Autor             : Gonzalo Javier Huertas Agurto
  ***************************************************************************/
  PROCEDURE imp_pr_print_bolet_recoj
  (
    pscodigopais          IN VARCHAR2,
    pscodigoperiodo       IN VARCHAR2,
    pscodigomarca         IN VARCHAR2,
    pscodigocanal         IN VARCHAR2,
    pscodigotransaccional VARCHAR2,
    pscodigousuario       VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Procedimiemto que implementa la logica en IMP_PR_PROCE_DETAL_FACTU2_REGI
                      para su insercion en tablas cabecera y detalle: IMP_PRINT_PEDID_RESUM y
                      IMP_PRINT_PEDID_DETAL - PAIS PERU CON Y SIN PERCEPCION
  
  Parametros        : Codigo de pais, codigo de periodo, codigo de marca, codigo de canal
  Fecha Creacion    : 20/11/2015
  Autor             : Gonzalo Javier Huertas Agurto
  ***************************************************************************/
  PROCEDURE imp_pr_print_pedid
  (
    p_codigopais          IN VARCHAR2,
    p_codigoperiodo       IN VARCHAR2,
    p_fechafacturacion    IN VARCHAR2,
    p_oidzona             NUMBER,
    pscodigotransaccional VARCHAR2,
    pscodigopaquete       VARCHAR2,
    pscodigousuario       VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Procedimiemto que implementa la logica en IMP_PR_PROCE_DETAL_FACTU3_REGI
                      para su insercion en tablas cabecera y detalle: IMP_PRINT_PEDID_RESUM y
                      IMP_PRINT_PEDID_DETAL - PAIS CHILE
  
  Parametros        : Codigo de pais, codigo de periodo, codigo de marca, codigo de canal
  Fecha Creacion    : 07/12/2015
  Autor             : Gonzalo Javier Huertas Agurto
  ***************************************************************************/
  PROCEDURE imp_pr_print_pedid_cl
  (
    p_codigopais          IN VARCHAR2,
    p_codigoperiodo       IN VARCHAR2,
    p_fechafacturacion    IN VARCHAR2,
    p_oidzona             NUMBER,
    pscodigotransaccional VARCHAR2,
    pscodigopaquete       VARCHAR2,
    pscodigousuario       VARCHAR2
  );
  PROCEDURE imp_pr_print_flexi
  (
    p_codigopais          IN VARCHAR2,
    p_codigoperiodo       IN VARCHAR2,
    p_fechafacturacion    IN VARCHAR2,
    pscodigotransaccional VARCHAR2,
    pscodigopaquete       VARCHAR2,
    pscodigousuario       VARCHAR2
  );

  /**************************************************************************
  Descripcion         : Proceso que obtiene la informacion para generar la
                        seccion de detalle de cuenta corriente.
  Fecha Creacion      : 11/05/2010
  Fecha Modificacion  : 11/05/2010
  Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
  Parametros Entrada  :
      p_codigoPais            : Codigo del pais
      p_codigoPeriodo         : Periodo del cupon
      p_fechaFacturacion      : Fecha de Facturacion
  Version             : Final (Alfa|Final)
  ***************************************************************************/

  PROCEDURE imp_pr_print_ccc
  (
    p_codigopais          IN VARCHAR2,
    p_codigoperiodo       IN VARCHAR2,
    p_fechafacturacion    IN VARCHAR2,
    pnoidzona             IN NUMBER,
    pscodigotransaccional VARCHAR2,
    pscodigopaquete       VARCHAR2,
    pscodigousuario       VARCHAR2
  );

  PROCEDURE imp_pr_print_picad
  (
    pscodigoperiodo    VARCHAR2,
    psfechafacturacion VARCHAR2,
    pscodigoproceso    VARCHAR2,
    pscodigopaquete    VARCHAR2,
    psusuario          VARCHAR2
  );
  
  PROCEDURE imp_pr_print_homol_picad;
END imp_pkg_proce_impre;
/
create or replace package body IMP_PKG_PROCE_IMPRE is

PROCEDURE IMP_PR_PROCE_SPOOL( psCodigoPais       VARCHAR2,
                              psCodigoPeriodo    VARCHAR2,
                              psFechaFacturacion VARCHAR2,
                              pscodigoproceso VARCHAR2,
                              pscodigoPaquete  VARCHAR2,
                              psusuario varchar2) IS

CURSOR c_base(
               vnOidPeriodo       NUMBER--,
               --vsIndEnvioLarissa  VARCHAR2,
               --vnNumLoteFactu     NUMBER
             )
IS
--insert into IMP_PRINT_SPOOL
SELECT pscodigoproceso cod_proc, -- Temporal rownum
       clie.cod_clie,
       pscodigopaquete cod_paqu,
       rownum oid_spoo, -- Esto debe ser un Sequence de la tabla
       nvl(clie.cod_digi_ctrl, '0') val_digi_veri,
       pscodigoperiodo cod_camp,
       zorg.cod_regi,
       zzon.cod_zona,
       zscc.cod_secc,
       terr.cod_terr,
       secu.num_secu_fact_diar ord_prio, -- Validar
       NULL ind_tipo_clie, -- Campo ya no se usa. Enviar en blanco
       clie.val_ape1 val_apel_pate,
       clie.val_ape2 val_apel_mate,
       substr(clie.val_nom1 || ' ' || clie.val_nom2, 1, 30) val_nomb,
       cons.val_nume_soli num_pedi, -- Validar
       NULL cod_cupo, -- Validar
       clid.num_docu_iden,
       (SELECT decode(y.val_sigl, 'RUC', y.val_sigl, 'DNI')
          FROM mae_tipo_docum y
         WHERE y.oid_tipo_docu = clid.tdoc_oid_tipo_docu
           AND rownum = 1) cod_trib, -- Validar
       cons.fec_fact,
       cons.fec_fact fec_emis, -- Validar
       NULL fec_reun_01, -- ??
       NULL fec_reun_02, -- ??
       NULL fec_reun_03, -- ??
       NULL fec_repa_01, -- cabecera
       NULL fec_repa_02, -- cabecera
       NULL fec_repa_03, -- cabecera
       NULL fec_vcto, --IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'mensajeOportunidadAhorro') FEC_VCTO, -- Cabecera
       NULL fec_prox_fact_01, -- cabecera
       NULL fec_prox_fact_02, -- cabecera
       NULL fec_prox_fact_03, -- cabecera
       NULL mon_sald_actu, -- Esto lo llena Pedidos
       NULL mon_sald_ante, -- Esto lo llena Pedidos
       NULL val_dire_01, -- Validar
       NULL val_dire_02, -- Validar
       NULL val_dire_03, -- Validar
       NULL val_ubig_01, -- Validar
       NULL val_ubig_02, -- Validar
       NULL val_ubig_03, -- Validar
       NULL val_refe_01, -- Validar
       NULL val_refe_02, -- Validar
       NULL val_refe_03, -- Validar
       substr((SELECT TRIM(x.val_text_comu)
                FROM mae_clien_comun x
               WHERE clie_oid_clie = cons.clie_oid_clie
                 AND x.ticm_oid_tipo_comu = 6
                 AND rownum = 1),
              1,
              20) val_telf_01, -- Validar
       substr((SELECT TRIM(x.val_text_comu)
                FROM mae_clien_comun x
               WHERE clie_oid_clie = cons.clie_oid_clie
                 AND x.ticm_oid_tipo_comu = 7
                 AND rownum = 1),
              1,
              20) val_telf_02, -- Validar
       NULL flg_orig, -- Validar
       NULL flg_secc, -- Validar
       CASE
         WHEN clda.ind_impr_pdoc = 'N' THEN
          0
         WHEN clda.ind_impr_pdoc = 'S' THEN
          1
         WHEN clda.ind_impr_pdoc IS NULL THEN
          1
         ELSE
          0
       END ind_impr_pdoc,
       NULL ind_cons_estr, -- Validar
       NULL ind_cons_circ, -- Validar
       NULL ord_pedi, -- Validar
       NULL des_nive_01, -- Validar
       NULL des_nive_02, -- Validar
       NULL des_nive_03, -- Validar
       NULL can_comp, -- Validar (pedidos)
       NULL can_pica, -- Validar (Ape)
       NULL can_orde_comp, -- Validar (pedidos)
       NULL num_bole_desp, -- Validar (pedidos)
       NULL uni_bole_desp, -- Validar (pedidos)
       NULL imp_bole_desp, -- Validar (pedidos)
       NULL cod_bole_desp, -- Validar (pedidos)
       NULL cod_tipo_pedi, -- Validar (pedidos)
       NULL des_tipo_pedi, -- Validar ???
       NULL mon_pedm_flex, -- Esto lo llena flexipago
       NULL mon_disp_flex, -- Esto lo llena flexipago
       (SELECT val_ape1 || ' ' || val_ape2
          FROM mae_clien
         WHERE oid_clie = zzon.clie_oid_clie) val_gezo_apel, -- Cabecera
       (SELECT val_nom1 || ' ' || val_nom2
          FROM mae_clien
         WHERE oid_clie = zzon.clie_oid_clie) val_gezo_nomb, -- Cabecera
       (SELECT x.val_text_comu
          FROM mae_clien_comun x
         WHERE clie_oid_clie = zzon.clie_oid_clie
           AND x.ticm_oid_tipo_comu = 6
           AND rownum = 1) val_gezo_movi, -- Cabecera
       (SELECT x.val_text_comu
          FROM mae_clien_comun x
         WHERE clie_oid_clie = zzon.clie_oid_clie
           AND x.ticm_oid_tipo_comu = 2006
           AND rownum = 1) val_gezo_mail, -- Cabecera
       (SELECT val_ape1 || ' ' || val_ape2
          FROM mae_clien
         WHERE oid_clie = zscc.clie_oid_clie) val_ejec_apel, -- Cabecera
       (SELECT val_nom1 || ' ' || val_nom2
          FROM mae_clien
         WHERE oid_clie = zscc.clie_oid_clie) val_ejec_nomb, -- Cabecera
       (SELECT x.val_text_comu
          FROM mae_clien_comun x
         WHERE clie_oid_clie = zscc.clie_oid_clie
           AND x.ticm_oid_tipo_comu = 6
           AND rownum = 1) val_ejec_movi, -- Cabecera
       (SELECT x.val_text_comu
          FROM mae_clien_comun x
         WHERE clie_oid_clie = zscc.clie_oid_clie
           AND x.ticm_oid_tipo_comu = 3
           AND rownum = 1) val_ejec_mail, -- Cabecera
       NULL cod_alma_cdp, -- Validar ( pedidos )
       --
       NULL val_auxi_rv1, -- SOLO RV
       NULL val_auxi_rv2, -- SOLO RV
       NULL val_auxi_rv3, -- SOLO RV
       NULL val_auxi_rv4, -- SOLO RV
       NULL val_auxi_rv5, -- SOLO RV
       --
       psusuario usu_crea,
       SYSDATE fec_crea,
       NULL usu_modi,
       NULL fec_modi,
       '1' ind_acti
  FROM ped_solic_cabec       cons,
       ped_solic_cabec_secue secu,
       mae_clien_datos_adici clda,
       mae_clien             clie,
       mae_clien_ident       clid,
       zon_terri_admin       ztad,
       zon_terri             terr,
       zon_secci             zscc,
       zon_zona              zzon,
       zon_regio             zorg
 WHERE cons.oid_soli_cabe = secu.soca_oid_soli_cabe
   AND cons.clie_oid_clie = clie.oid_clie
   AND cons.clie_oid_clie = clda.clie_oid_clie
   AND cons.clie_oid_clie = clid.clie_oid_clie
   AND cons.ztad_oid_terr_admi = ztad.oid_terr_admi
   AND ztad.terr_oid_terr = terr.oid_terr
   AND ztad.zscc_oid_secc = zscc.oid_secc
   AND zscc.zzon_oid_zona = zzon.oid_zona
   AND zzon.zorg_oid_regi = zorg.oid_regi
      --
      /*AND zscc.clie_oid_clie = lide.oid_clie(+)*/
      --
   AND cons.fec_fact = to_date(psfechafacturacion, 'dd/mm/yyyy')
   AND cons.perd_oid_peri = vnoidperiodo
      --AND cons.IND_INTE_LARI_GENE = vsIndEnvioLarissa
      --AND cons.NUM_LOTE_FACT = NVL( vnNumLoteFactu, cons.NUM_LOTE_FACT )
   AND cons.num_unid_aten_tota > 0
   AND EXISTS
 (SELECT NULL
          FROM int_lar_tipo_solici_pedido_dis ltsp
         WHERE ltsp.tspa_oid_tipo_soli_pais = cons.tspa_oid_tipo_soli_pais)
   AND clid.val_iden_docu_prin = 1 -- 1=Documento Principal
;

TYPE c_Base_t IS TABLE OF c_Base%ROWTYPE INDEX BY BINARY_INTEGER;
curSpool c_Base_t;

-- Cursor para obtener valores de fechas de facturación, reparto y reuniones próximas

-- Variables ( BORRAR DESPUES )
CODIGO_CANAL VARCHAR2(10) := 'VD';
CODIGO_MARCA VARCHAR2(10) := 'T';
W_FILAS      NUMBER:=5000;

-- Variables
lnOidPais          SEG_PAIS.OID_PAIS%TYPE;
lnOidMarca         SEG_MARCA.OID_MARC%TYPE;
lnOidCanal         SEG_CANAL.OID_CANA%TYPE;
lnOidPeriodo       CRA_PERIO.OID_PERI%TYPE;
--lsCampaActuaMas1   SEG_PERIO_CORPO.COD_PERI%TYPE:= GEN_FN_CALCU_PERIO( psCodigoPeriodo, 1);
--lsCampaActuaMas2   SEG_PERIO_CORPO.COD_PERI%TYPE:= GEN_FN_CALCU_PERIO( psCodigoPeriodo, 2);

--lsIndEnvioLarissa    IMP_PARAM_PROCE_IMPRE.VAL_PARA_PRIM%TYPE := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioLarissa');
lsIndEnvioUltimoLote IMP_PARAM_PROCE_IMPRE.VAL_PARA_PRIM%TYPE := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioUltimoLote');
lnNumLoteFactu       PED_SOLIC_CABEC.NUM_LOTE_FACT%TYPE := NULL;
--lsActividadConf      VARCHAR2(100) := NVL(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','actividadConf'),'CV');
--lsActividadDesp      VARCHAR2(100) := NVL(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','actividadDesp'),'DP');

lnOk                 NUMBER := 1;
lnpagina             NUMBER := 0;
lnColumna            NUMBER := 0;
BEGIN
    -- Iniciar Variables
    lnOidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS( psCodigoPais );
    lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL( CODIGO_CANAL );
    lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA( CODIGO_MARCA );
    lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO( psCodigoPeriodo, lnOidMarca, lnOidCanal );

    IF( lsIndEnvioUltimoLote = '1' OR lsIndEnvioUltimoLote = 'S' ) THEN
        BEGIN
            SELECT MAX( cons.NUM_LOTE_FACT )
              INTO lnNumLoteFactu
              FROM PED_SOLIC_CABEC cons,
                   INT_LAR_TIPO_SOLICI_PEDIDO_DIS tspd
             WHERE cons.PERD_OID_PERI = lnOidPeriodo
               AND cons.FEC_FACT = TO_DATE( psFechaFacturacion, 'DD/MM/YYYY' )
               AND cons.IND_TS_NO_CONSO = 0
               AND ( cons.IND_PEDI_PRUE = 0 OR cons.IND_PEDI_PRUE IS NULL )
               AND cons.TSPA_OID_TIPO_SOLI_PAIS = tspd.TSPA_OID_TIPO_SOLI_PAIS
               AND cons.PAIS_OID_PAIS = lnOidPais;
        EXCEPTION WHEN OTHERS THEN
              lnNumLoteFactu := NULL;
        END;
    END IF;

    -- Limpiar tabla

    BEGIN
        DELETE FROM IMP_PRINT_SPOOL;
    EXCEPTION WHEN OTHERS THEN
        lnOk := 0;
    END;

    OPEN c_Base( lnOidPeriodo/*, lsIndEnvioLarissa, lnNumLoteFactu */);
    LOOP
      lnpagina := lnpagina + 1;
      FETCH c_base BULK COLLECT INTO curSpool LIMIT w_Filas;
      IF curSpool.COUNT > 0 THEN
         FOR i IN curSpool.FIRST .. curSpool.LAST LOOP
             BEGIN
                 lnColumna := i;
                 INSERT INTO IMP_PRINT_SPOOL(COD_PROC, -- PK
                        COD_CLIE, -- PK
                        COD_PAQU, -- PK
                        OID_SPOO,
                        VAL_DIGI_VERI,
                        COD_CAMP,
                        COD_REGI,
                        COD_ZONA,
                        COD_SECC,
                        COD_TERR,
                        ORD_PRIO,
                        IND_TIPO_CLIE,
                        VAL_APEL_PATE,
                        VAL_APEL_MATE,
                        VAL_NOMB,
                        NUM_PEDI,
                        COD_CUPO,
                        NUM_DOCU_IDEN,
                        COD_TRIB,
                        FEC_FACT,
                        FEC_EMIS,
                        FEC_REUN_01,
                        FEC_REUN_02,
                        FEC_REUN_03,
                        FEC_REPA_01,
                        FEC_REPA_02,
                        FEC_REPA_03,
                        FEC_VCTO,
                        FEC_PROX_FACT_01,
                        FEC_PROX_FACT_02,
                        FEC_PROX_FACT_03,
                        MON_SALD_ACTU,
                        MON_SALD_ANTE,
                        VAL_DIRE_01,
                        VAL_DIRE_02,
                        VAL_DIRE_03,
                        VAL_UBIG_01,
                        VAL_UBIG_02,
                        VAL_UBIG_03,
                        VAL_REFE_01,
                        VAL_REFE_02,
                        VAL_REFE_03,
                        VAL_TELF_01,
                        VAL_TELF_02,
                        FLG_ORIG,
                        FLG_SECC,
                        IND_IMPR_PDOC,
                        IND_CONS_ESTR,
                        IND_CONS_CIRC,
                        ORD_PEDI,
                        DES_NIVE_01,
                        DES_NIVE_02,
                        DES_NIVE_03,
                        CAN_COMP,
                        CAN_PICA,
                        CAN_ORDE_COMP,
                        NUM_BOLE_DESP,
                        UNI_BOLE_DESP,
                        IMP_BOLE_DESP,
                        COD_BOLE_DESP,
                        COD_TIPO_PEDI,
                        DES_TIPO_PEDI,
                        MON_PEDM_FLEX,
                        MON_DISP_FLEX,
                        VAL_GEZO_APEL,
                        VAL_GEZO_NOMB,
                        VAL_GEZO_MOVI,
                        VAL_GEZO_MAIL,
                        VAL_EJEC_APEL,
                        VAL_EJEC_NOMB,
                        VAL_EJEC_MOVI,
                        VAL_EJEC_MAIL,
                        COD_ALMA,
                        VAL_AUXI_RV1,
                        VAL_AUXI_RV2,
                        VAL_AUXI_RV3,
                        VAL_AUXI_RV4,
                        VAL_AUXI_RV5,
                        USU_CREA,
                        FEC_CREA,
                        USU_MODI,
                        FEC_MODI,
                        IND_ACTI)
                 SELECT curSpool(i).COD_PROC, -- PK
                        curSpool(i).COD_CLIE, -- PK
                        curSpool(i).COD_PAQU, -- PK
                        curSpool(i).OID_SPOO,
                        curSpool(i).VAL_DIGI_VERI,
                        curSpool(i).COD_CAMP,
                        curSpool(i).COD_REGI,
                        curSpool(i).COD_ZONA,
                        curSpool(i).COD_SECC,
                        curSpool(i).COD_TERR,
                        curSpool(i).ORD_PRIO,
                        curSpool(i).IND_TIPO_CLIE,
                        curSpool(i).VAL_APEL_PATE,
                        curSpool(i).VAL_APEL_MATE,
                        curSpool(i).VAL_NOMB,
                        curSpool(i).NUM_PEDI,
                        curSpool(i).COD_CUPO,
                        curSpool(i).NUM_DOCU_IDEN,
                        curSpool(i).COD_TRIB,
                        curSpool(i).FEC_FACT,
                        curSpool(i).FEC_EMIS,
                        curSpool(i).FEC_REUN_01,
                        curSpool(i).FEC_REUN_02,
                        curSpool(i).FEC_REUN_03,
                        curSpool(i).FEC_REPA_01,
                        curSpool(i).FEC_REPA_02,
                        curSpool(i).FEC_REPA_03,
                        curSpool(i).FEC_VCTO,
                        curSpool(i).FEC_PROX_FACT_01,
                        curSpool(i).FEC_PROX_FACT_02,
                        curSpool(i).FEC_PROX_FACT_03,
                        curSpool(i).MON_SALD_ACTU,
                        curSpool(i).MON_SALD_ANTE,
                        curSpool(i).VAL_DIRE_01,
                        curSpool(i).VAL_DIRE_02,
                        curSpool(i).VAL_DIRE_03,
                        curSpool(i).VAL_UBIG_01,
                        curSpool(i).VAL_UBIG_02,
                        curSpool(i).VAL_UBIG_03,
                        curSpool(i).VAL_REFE_01,
                        curSpool(i).VAL_REFE_02,
                        curSpool(i).VAL_REFE_03,
                        curSpool(i).VAL_TELF_01,
                        curSpool(i).VAL_TELF_02,
                        curSpool(i).FLG_ORIG,
                        curSpool(i).FLG_SECC,
                        curSpool(i).IND_IMPR_PDOC,
                        curSpool(i).IND_CONS_ESTR,
                        curSpool(i).IND_CONS_CIRC,
                        curSpool(i).ORD_PEDI,
                        curSpool(i).DES_NIVE_01,
                        curSpool(i).DES_NIVE_02,
                        curSpool(i).DES_NIVE_03,
                        curSpool(i).CAN_COMP,
                        curSpool(i).CAN_PICA,
                        curSpool(i).CAN_ORDE_COMP,
                        curSpool(i).NUM_BOLE_DESP,
                        curSpool(i).UNI_BOLE_DESP,
                        curSpool(i).IMP_BOLE_DESP,
                        curSpool(i).COD_BOLE_DESP,
                        curSpool(i).COD_TIPO_PEDI,
                        curSpool(i).DES_TIPO_PEDI,
                        curSpool(i).MON_PEDM_FLEX,
                        curSpool(i).MON_DISP_FLEX,
                        curSpool(i).VAL_GEZO_APEL,
                        curSpool(i).VAL_GEZO_NOMB,
                        curSpool(i).VAL_GEZO_MOVI,
                        curSpool(i).VAL_GEZO_MAIL,
                        curSpool(i).VAL_EJEC_APEL,
                        curSpool(i).VAL_EJEC_NOMB,
                        curSpool(i).VAL_EJEC_MOVI,
                        curSpool(i).VAL_EJEC_MAIL,
                        curSpool(i).COD_ALMA_CDP,
                        curSpool(i).VAL_AUXI_RV1,
                        curSpool(i).VAL_AUXI_RV2,
                        curSpool(i).VAL_AUXI_RV3,
                        curSpool(i).VAL_AUXI_RV4,
                        curSpool(i).VAL_AUXI_RV5,
                        curSpool(i).USU_CREA,
                        curSpool(i).FEC_CREA,
                        curSpool(i).USU_MODI,
                        curSpool(i).FEC_MODI,
                        curSpool(i).IND_ACTI
                   FROM DUAL;
             EXCEPTION WHEN DUP_VAL_ON_INDEX THEN
                    lnOk := 0;
             END;
         END LOOP;
      END IF;
      EXIT WHEN c_Base%NOTFOUND;
    END LOOP;
    CLOSE c_Base;
EXCEPTION WHEN OTHERS THEN
    ln_sqlcode := sqlcode;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123,
     'ERROR IMP_PR_PROCE_SPOOL: pagina: '|| lnpagina||
            ' columna: '||lnColumna||' - '||ls_sqlerrm);
END IMP_PR_PROCE_SPOOL;

/**************************************************************************
Descripcion         : Proceso que prepara genera el XML de ultimas noticias.
Fecha Creacion      : 11/05/2010
Fecha Modificacion  : 11/05/2010
Autor               : Jorge Yepez
Version             : Final (Alfa|Final)

IMP-104
***************************************************************************/
PROCEDURE IMP_PR_PROCE_NOTIC(
  p_codigoPais IN VARCHAR2,
  p_codigoPeriodo IN VARCHAR2,
  p_fechaFacturacion IN VARCHAR2,
  p_numeroLoteFacturacion  NUMBER)
IS

  W_FILAS       NUMBER := 1000 ;
  l_oidPeriodo  NUMBER;
  l_oidPais     NUMBER;
  l_oidCanal    NUMBER;
  l_oidMarca    NUMBER;
  --lsCodClie     mae_clien.cod_clie%TYPE;

  CURSOR c_consolidados(
     oidPeriodo NUMBER) IS
  SELECT SP.OID_PAIS,
         SP.COD_PAIS,
         MC.OID_CLIE,
         MC.COD_CLIE,
         MC.COD_DIGI_CTRL,
         MC.VAL_NOM1,
         MC.VAL_NOM2,
         MC.VAL_APE1,
         MC.VAL_APE2,
         MCI.NUM_DOCU_IDEN,
         CON.OID_SOLI_CABE,
         CON.VAL_NUME_SOLI,
         CON.FEC_FACT,
         CON.VAL_IMPO_FLET_TOTA_LOCA,
         CON.VAL_IMPO_REDO_LOCA,
         substr(des_pais, 1,length(des_pais)- decode(instr(des_pais,'ESIKA'),0,0,5)- decode(instr(des_pais,'LBEL'),0,0,4)) DES_PAIS,
         ZON.COD_ZONA,
         SEC.COD_SECC,
         TER.COD_TERR,
         SEC.NUM_SECU_FACT_DIAR,
         con.num_lote_fact
  FROM MAE_CLIEN MC,
       MAE_CLIEN_IDENT MCI,
       PED_SOLIC_CABEC CON,
       ZON_ZONA ZON,
       ZON_TERRI TER,
       ZON_TERRI_ADMIN ZTA,
       ZON_SECCI SEC,
       PED_SOLIC_CABEC_SECUE SEC,
       SEG_PAIS SP,
       BAS_PAIS BP
  WHERE MC.OID_CLIE = CON.CLIE_OID_CLIE
    AND MC.OID_CLIE = MCI.CLIE_OID_CLIE
    AND MCI.VAL_IDEN_DOCU_PRIN = 1
    AND SP.OID_PAIS = CON.PAIS_OID_PAIS
    AND SP.COD_PAIS = BP.COD_PAIS
    AND CON.ZZON_OID_ZONA = ZON.OID_ZONA
    AND CON.TERR_OID_TERR = TER.OID_TERR
    AND CON.ZTAD_OID_TERR_ADMI = ZTA.OID_TERR_ADMI
    AND ZTA.ZSCC_OID_SECC = SEC.OID_SECC
    AND CON.OID_SOLI_CABE = SEC.SOCA_OID_SOLI_CABE
    AND CON.FEC_FACT = TO_DATE(p_fechaFacturacion, 'dd/mm/yyyy')
    and (p_numeroLoteFacturacion is null or con.num_lote_fact = p_numeroLoteFacturacion)
    AND CON.PERD_OID_PERI = oidPeriodo
   -- AND ZON.Oid_Zona = oidZona
    AND CON.NUM_UNID_ATEN_TOTA > 0
    AND EXISTS (
        SELECT NULL
        FROM INT_LAR_TIPO_SOLICI_PEDIDO_DIS L
        WHERE L.TSPA_OID_TIPO_SOLI_PAIS = CON.TSPA_OID_TIPO_SOLI_PAIS
    )
  ORDER BY MC.COD_CLIE,  CON.VAL_NUME_SOLI;

  TYPE consolidadotype IS TABLE OF c_consolidados%ROWTYPE;
  r_consolidado    consolidadotype;

  lsValor BAS_PARAM_PAIS.VAL_PARA%TYPE;

BEGIN

    l_oidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(p_codigoPais);
    l_oidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(CODIGO_CANAL);
    l_oidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(CODIGO_MARCA);
    l_oidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(p_codigoPeriodo, l_oidMarca, l_oidCanal);

    -- Abrimos el cursor principal
    OPEN c_consolidados(l_oidPeriodo);
    LOOP
        FETCH c_consolidados BULK COLLECT  INTO r_consolidado LIMIT w_filas;
        IF  r_consolidado.COUNT > 0 THEN
            FOR i IN r_consolidado.FIRST..r_consolidado.LAST
            LOOP
                IMP_PR_PROCE_NOTIC_CORPO(
                    p_codigoPeriodo,
                    l_oidPeriodo,
                    r_consolidado(i).oid_clie,
                    r_consolidado(i).oid_soli_cabe,
                    r_consolidado(i).num_lote_fact);
            END LOOP;
        END IF;
        EXIT WHEN c_consolidados%NOTFOUND;
    END LOOP;

    -- Cerramos el cursor
    CLOSE c_consolidados;
    COMMIT;
END IMP_PR_PROCE_NOTIC;

/**************************************************************************
Descripcion         : Proceso que prepara genera el XML de ultimas noticias. Cooperativo
Fecha Creacion      : 06/06/2013
Fecha Modificacion  : 06/06/2013
Autor               : Sergio Buchelli
Version             : Final (Alfa|Final)
***************************************************************************/
PROCEDURE IMP_PR_PROCE_NOTIC_CORPO(
  p_codPeri IN VARCHAR2,
  p_oidPeri IN NUMBER,
  p_oidclie IN NUMBER,
  p_oidsoli IN NUMBER,
  p_numlotefact IN VARCHAR2
  )
IS

  W_FILAS                   NUMBER := 1000 ;
  ls_patron                 NUMBER(12);
  ls_codpatr_antg           VARCHAR(100):=' ';
  ls_codsecc_antg           VARCHAR(100):=' ';
  ls_vallite_antg           VARCHAR(100):=' ';
  ls_mensaje                VARCHAR(10000):=' ';
  ls_mensbloq               VARCHAR(10000):=' ';
  ls_mensfijo               VARCHAR(10000):=' ';
  ls_mensvar                VARCHAR(10000):=' ';
  ls_menstemp               VARCHAR(10000):=' ';

  l_clob                    CLOB;
  l_indexinibloque          NUMBER;
  l_indexfinbloque          NUMBER;
  l_textoActual             VARCHAR2(20000) := '';

  l_textoOriginal           VARCHAR2(200);
  l_textoReemplazo          VARCHAR2(200);
  lc_xml_mens_punt          CLOB;

  CURSOR c_patron IS
     select a.patr_oid_patr
     from msg_patro_perio a,
          cra_perio x
     WHERE x.oid_peri = p_oidPeri
       AND a.peri_oid_peri = x.peri_oid_peri ;

  TYPE patrontype IS TABLE OF c_patron%ROWTYPE;
  r_patron    patrontype;

  CURSOR c_mensaje(oidPatron NUMBER) IS
        SELECT
           CAMPA.Cod_Mens,
           campa.NOM_IMAG_FOND,
           A.OID_PATR,
           substr(A.COD_PATR,1,4) COD_PATR,
           A.DES_PATR,
           A.FORS_OID_FORM,
           B.COD_FORM,
           A.MEEP_OID_MEDI_ENVI_PAIS,
           D.DES_MEDI_ENVI,
           A.IND_ACTI,
           A.IND_PATR_PERI,
           DECODE(A.IND_PATR_PERI,
                  1,
                  (SELECT E.PATR_OID_PATR_ORIG
                     FROM MSG_PATRO_PERIO E
                    WHERE E.PATR_OID_PATR = A.OID_PATR),
                  NULL) PATR_OID_PATR_ORIG,
           DECODE(A.IND_PATR_PERI,
                  1,
                  (SELECT E.PERI_OID_PERI
                     FROM MSG_PATRO_PERIO E
                    WHERE E.PATR_OID_PATR = A.OID_PATR),
                  NULL) PERI_OID_PERI,
           DECODE(A.IND_PATR_PERI,
                  1,
                  (SELECT E.OID_PATR_PERI
                     FROM MSG_PATRO_PERIO E
                    WHERE E.PATR_OID_PATR = A.OID_PATR),
                  NULL) OID_PATR_PERI,
           A.PAIS_OID_PAIS,
           F.PATR_OID_PATR,
           F.OID_PATR_SECC,
           F.COD_SECC,
           F.NUM_ORDE_SECC,
           F.METC_OID_META,
           G.VAL_LITE_IDEN,
           H.VAL_I18N,
           I.MENS_OID_MENS,
           I.NUM_ORDE_IMPR,
           CAMPA.VAL_TEXT,
           campa.tmen_oid_tipo_mens,
           (select IDE_SECC from MSG_SECCI_DOCUM x where  x.mdoc_cod_mens_docu = f.mdoc_cod_mens_docu and x.COD_SECC_DOCU  = F.MSEC_COD_SECC_DOCU AND X.IND_ACTI= 1 AND X.EST_REGI='1' ) IDE_SECC,
           (select NIV_SECC from MSG_SECCI_DOCUM x where x.mdoc_cod_mens_docu = f.mdoc_cod_mens_docu and x.COD_SECC_DOCU  = F.MSEC_COD_SECC_DOCU AND X.IND_ACTI= 1 AND X.EST_REGI='1' ) NIV_SECC,--SI ES PADRE
           (select IND_GRUP_SECC from MSG_SECCI_DOCUM x where x.mdoc_cod_mens_docu = f.mdoc_cod_mens_docu and x.COD_SECC_DOCU  = F.MSEC_COD_SECC_DOCU AND X.IND_ACTI= 1 AND X.EST_REGI='1' ) IND_GRUP_SECC, --MISMO GRUPO
           NULL NOM_BAND_FLYE
      FROM MSG_PATRO            A,
           FAC_FORMU            B,
           MSG_MEDIO_ENVIO_PAIS C,
           MSG_MEDIO_ENVIO      D,
           MSG_PATRO_SECCI      F,
           MSG_METAC            G,
           V_GEN_I18N_SICC      H,
           MSG_PATRO_MENSA      I,
           MSG_MENSA_CAMPA      CAMPA
     WHERE A.OID_PATR = oidPatron
       AND CAMPA.CAM_PROC = p_codPeri

       AND A.IND_ACTI = 1
       AND B.OID_FORM = A.FORS_OID_FORM
       AND C.OID_MEDI_ENVI_PAIS = A.MEEP_OID_MEDI_ENVI_PAIS
       AND D.OID_MEDI_ENVI = C.MEEN_OID_MEDI_ENVI
       AND F.PATR_OID_PATR = A.OID_PATR
       AND F.METC_OID_META = G.OID_META(+)
       AND G.OID_META = H.VAL_OID(+)
       AND H.ATTR_ENTI(+) = 'MSG_METAC'
       AND H.IDIO_OID_IDIO(+) = 1
       AND I.PASE_OID_PATR_SECC = F.OID_PATR_SECC
       AND i.mens_oid_mens = campa.mens_oid_mens
       AND IMP_PKG_PROCE_IMPRE.imp_fn_mens_ua_clie(p_oidclie, p_codPeri, campa.cod_mens)=1
       and IMP_PKG_PROCE_IMPRE.imp_fn_mens_tipo_clie(p_oidclie, p_codPeri, campa.cod_mens)=1
       and IMP_PKG_PROCE_IMPRE.imp_fn_mens_pedid_clie(p_oidsoli, p_codPeri, campa.cod_mens)=1
       and IMP_PKG_PROCE_IMPRE.imp_fn_mens_lista_clie(p_oidclie, p_oidsoli, p_codPeri, campa.cod_mens)=1
    --  and IMP_PKG_PROCE_LASER.imp_fn_mens_marca_clie(p_oidclie, campa.mens_oid_mens)=1
    --  and IMP_PKG_PROCE_LASER.imp_fn_mens_cuv_clie(p_oidsoli, campa.mens_oid_mens)=1

    UNION

    SELECT
           NULL,
           NULL,
           X.OID_PATR,
           substr(x.COD_PATR,1,4) COD_PATR,
           x.DES_PATR,
           x.FORS_OID_FORM,
           B.COD_FORM,
           X.MEEP_OID_MEDI_ENVI_PAIS,
           NULL DES_MEDI_ENVI,
           X.IND_ACTI,
           X.IND_PATR_PERI,
           DECODE(X.IND_PATR_PERI,
                  1,
                  (SELECT E.PATR_OID_PATR_ORIG
                     FROM MSG_PATRO_PERIO E
                    WHERE E.PATR_OID_PATR = X.OID_PATR),
                  NULL) PATR_OID_PATR_ORIG,
           DECODE(X.IND_PATR_PERI,
                  1,
                  (SELECT E.PERI_OID_PERI
                     FROM MSG_PATRO_PERIO E
                    WHERE E.PATR_OID_PATR = X.OID_PATR),
                  NULL) PERI_OID_PERI,
           DECODE(X.IND_PATR_PERI,
                  1,
                  (SELECT E.OID_PATR_PERI
                     FROM MSG_PATRO_PERIO E
                    WHERE E.PATR_OID_PATR = X.OID_PATR),
                  NULL) OID_PATR_PERI,
           X.PAIS_OID_PAIS,
           NULL,
           NULL ,
           NULL,
           NULL NUM_ORDE_SECC,
           NULL,
           NULL,
           NULL,
           NULL,
           NULL NUM_ORDE_IMPR,
           NULL,
           NULL,
           NULL IDE_SECC,
           NULL NIV_SECC,--SI ES PADRE
           NULL IND_GRUP_SECC, --MISMO GRUPO
           x.NOM_BAND_FLYE
    FROM MSG_PATRO x,
         MSG_PATRO_PERIO y,
         FAC_FORMU B
    WHERE x.OID_PATR = oidPatron
      AND x.oid_patr = y.PATR_OID_PATR
      AND x.IND_ACTI = 1
      AND B.OID_FORM = x.FORS_OID_FORM
      AND y.PERI_OID_PERI = (SELECT A.OID_PERI
                     				FROM SEG_PERIO_CORPO A
                     				WHERE A.COD_PERI = p_codPeri)
   ORDER BY NUM_ORDE_SECC, NUM_ORDE_IMPR;

  TYPE mensajetype IS TABLE OF c_mensaje%ROWTYPE;
  r_mensaje    mensajetype;

  CURSOR c_buzon(p_oidmens NUMBER,p_oidclie NUMBER,p_oidsoli NUMBER) IS
      SELECT A.Oid_Buzo_Mens,
          a.dato_vari_01,
          a.dato_vari_02,
          a.dato_vari_03,
          a.dato_vari_04,
          a.dato_vari_05,
          a.dato_vari_06,
          a.dato_vari_07,
          a.dato_vari_08,
          a.dato_vari_09,
          a.dato_vari_10,
          a.dato_vari_11,
          a.dato_vari_12,
          a.dato_vari_13,
          a.dato_vari_14,
          a.dato_vari_15,
          a.dato_vari_16,
          a.dato_vari_17,
          a.dato_vari_18,
          a.dato_vari_19,
          a.dato_vari_20
       FROM MSG_BUZON_MENSA A
       WHERE A.Mens_Oid_Mens=p_oidmens
       and (a.peri_oid_peri is null or
            a.peri_oid_peri = (select peri_oid_peri from cra_perio
                               where oid_peri=(select perd_oid_peri
                                               from ped_solic_cabec
                                               where oid_soli_cabe = p_oidsoli)))
       AND a.clie_oid_clie = p_oidclie
       and (a.ind_acti=1 or a.num_lote_impr = p_numlotefact) ;

  TYPE buzontype IS TABLE OF c_buzon%ROWTYPE;
  r_buzon    buzontype;


  lsIdetificadorSeccion      MSG_SECCI_DOCUM.IDE_SECC%type;
  lsNivelSeccion             MSG_SECCI_DOCUM.NIV_SECC%type;
  lsGruposeccion             MSG_SECCI_DOCUM.IND_GRUP_SECC%type;

  lsIdetificadorSeccionAnt   MSG_SECCI_DOCUM.IDE_SECC%type;
  lsNivelSeccionAnt          MSG_SECCI_DOCUM.NIV_SECC%type;
  lsGruposeccionAnt          MSG_SECCI_DOCUM.IND_GRUP_SECC%type;
  hayCambioGrupo             boolean:=false;
  cerrarTagMensaje           boolean:= false;
  lsCodClie                  mae_clien.cod_clie%TYPE;
  lsCodProc                  imp_print_spool.cod_proc%TYPE;
  lsCodPaq                   imp_print_spool.cod_paqu%TYPE;
  lsCodGrupo                 VARCHAR2(3);
  lnOidNoti                  IMP_PRINT_NOTIC.Oid_Noti%TYPE;
  ln_numtab                  NUMBER(3);
  lnMaxCol                   number(5) :=0;
  lnOidCartMens              NUMBER(10);
  ls_lineamsg                VARCHAR2(1000):='';
  ls_tipolinea               VARCHAR2(2):='';
  ln_nrolinea                number(2) :=1;
  ln_inicol                  number(10) :=0;
  ln_fincol                  number(10) :=0;
  ln_nrocol                  number(5) :=0;
  ls_colum                   VARCHAR2(104);
  ls_indTLin                 VARCHAR2(1):='';
  l_inifijo                  NUMBER(10);
  l_finfijo                  NUMBER(10);
  t_array                    dbms_sql.varchar2_table;
  lsNombreImagen             imp_print_carta_grafi.nom_arch_imag%TYPE;
  lsTipoCarta                VARCHAR2(1);
  lsTipoPatron               VARCHAR2(2);

BEGIN

select cod_clie
into lsCodClie
from mae_clien
where oid_clie = p_oidclie;

BEGIN
    select MAX(COD_PROC) , MAX(COD_PAQU)
    INTO lsCodProc , lsCodPaq
    from imp_print_spool
    where IND_ACTI = 1
      AND COD_CLIE = lsCodClie;
EXCEPTION
  WHEN no_data_found THEN
       lsCodProc := NULL;
END;

IF lsCodProc IS NULL THEN
  RETURN;
END IF;


--Se inicializa la variabla CLOB en Memoria
DBMS_LOB.CREATETEMPORARY(l_clob, TRUE);

-- Abrimos el cursor de patrones del periodo
OPEN c_patron;
LOOP
    FETCH c_patron BULK COLLECT INTO r_patron LIMIT w_filas;
    IF  r_patron.COUNT > 0 THEN
        FOR y IN r_patron.FIRST..r_patron.LAST
        LOOP
          -- Abrimos el cursor principal
          OPEN c_mensaje(r_patron(y).patr_oid_patr);
          LOOP
              FETCH c_mensaje BULK COLLECT INTO r_mensaje LIMIT w_filas;
              IF  r_mensaje.COUNT > 0 THEN
                  FOR i IN r_mensaje.FIRST..r_mensaje.LAST
                  LOOP
                      l_textoActual:='';
                      lsIdetificadorSeccion :=  r_mensaje(i).IDE_SECC;
                      lsNivelSeccion:= r_mensaje(i).NIV_SECC;
                      lsGruposeccion:= r_mensaje(i).IND_GRUP_SECC;
                      lsTipoPatron := substr(r_mensaje(i).cod_patr,0,2);

                      IF ( lsGruposeccion = lsGruposeccionAnt) then
                         hayCambioGrupo:=false;
                      else
                          hayCambioGrupo:= true;
                      end if;

                      --  DBMS_OUTPUT.put_line(lsIdetificadorSeccion);
                      SELECT CASE lsIdetificadorSeccion
                            WHEN 'belnoticias' THEN 'BN'
                            WHEN 'belteorienta' THEN 'BO'
                            WHEN 'beltepremia' THEN 'BP'
                              ELSE 'X'
                             END
                      INTO lsCodGrupo
                      FROM dual;

                      if (lsNivelSeccion is null or length(lsNivelSeccion) =0) then
                          cerrarTagMensaje:= true;
                      else
                          cerrarTagMensaje:= false;
                      end if;
                      ls_mensaje:=r_mensaje(i).VAL_TEXT;
                      l_indexinibloque := instr(ls_mensaje,'<fijo>');
                      l_indexfinbloque := instr(ls_mensaje,'<fijo>', l_indexinibloque + 1);

                      BEGIN
                        SELECT max(OID_NOTI)
                        INTO lnOidNoti
                        FROM IMP_PRINT_NOTIC
                        WHERE PRSP_COD_PROC  = lsCodProc
                        AND   PRSP_COD_CLIE  = lsCodClie
                        AND   PRSP_COD_PAQU  = lsCodPaq
                        AND   COD_GRUP       = lsCodGrupo ;
                      EXCEPTION
                          WHEN no_data_found THEN
                             lnOidNoti := 0;
                      END;

                      if lnOidNoti is null or lnOidNoti = 0 then
                         lnOidNoti := 1;
                      else
                         lnOidNoti := lnOidNoti +1;
                      end if;

                      IF (r_mensaje(i).VAL_LITE_IDEN <>'libre') THEN
                          ln_numtab:=   TO_NUMBER(substr(r_mensaje(i).VAL_LITE_IDEN, 6,2));  --25;
                   --     DBMS_OUTPUT.put_line(TO_NUMBER(substr(r_mensaje(i).VAL_LITE_IDEN, 6,2)));
                      END IF;

                      IF r_mensaje(i).tmen_oid_tipo_mens = 2 THEN
                           ls_mensaje := replace( ls_mensaje , '<b>');
                           ls_mensaje := replace( ls_mensaje , '</b>');

                           IF  instr(ls_mensaje,'<t/>') <  1 THEN    --- MENSAJE sin ESTRUCTURA TABLA Y CON DETALLE
                               ls_mensaje := replace( ls_mensaje , '<txt>');
                               ls_mensaje := replace( ls_mensaje , '</txt>');
                               ls_mensaje := replace( ls_mensaje , '<txt/>');

                               IF lsTipoPatron = 'ps' OR
                                  lsTipoPatron = 'fl' OR
                                  lsTipoPatron = 'dp' THEN
                                  BEGIN
                                    SELECT MAX( nvl(X.OID_ORDE,0) ) +1
                                      INTO lnOidCartMens
                                      FROM IMP_PRINT_CARTA_GRAFI X
                                      WHERE X.PRSP_COD_PROC = lsCodProc
                                        AND X.PRSP_COD_CLIE = lsCodClie
                                        AND X.PRSP_COD_PAQU = lsCodPaq;
                                  EXCEPTION
                                  WHEN no_data_found THEN
                                       lnOidCartMens := 1;
                                  END;

                                  IF lnOidCartMens is null then
                                     lnOidCartMens := 1;
                                  end if;

                                  lsNombreImagen := ' ';
                                  IF lsTipoPatron = 'ps' THEN
                                      lsTipoCarta := '2';
                                      IF r_mensaje(i).NOM_IMAG_FOND IS NOT NULL THEN
                                         lsTipoCarta := '1';
                                         IF ls_mensaje IS NOT NULL THEN
                                            lsTipoCarta := '3';
                                         END IF;
                                      END IF;
                                  END IF;
                                  IF lsTipoPatron = 'fl' THEN
                                     lsTipoCarta := '5';
                                  END IF;
                                  IF lsTipoPatron = 'dp' THEN
                                     lsTipoCarta := '4';
                                  END IF;

                                  INSERT INTO IMP_PRINT_CARTA_GRAFI(
                                      prsp_cod_proc,
                                      prsp_cod_clie,
                                      prsp_cod_paqu,
                                      oid_orde,
                                      nom_arch_imag,
                                      cod_tipo_cart,
                                      val_titu,
                                      val_text,
                                      val_codi_barr,
                                      nom_band_flye,
                                      usu_crea,
                                      fec_crea,
                                      ind_acti
                                  )
                                  VALUES (
                                      lsCodProc,
                                      lsCodClie,
                                      lsCodPaq,
                                      lnOidCartMens,
                                      lsNombreImagen,
                                      lsTipoCarta,
                                      NULL,
                                      ls_mensaje,
                                      NULL,
                                      r_mensaje(i).nom_band_flye,
                                      'OPERADOR RV',
                                      SYSDATE,
                                      '1');

                               ELSE

                                  IF ls_mensaje IS NOT NULL THEN
                                     INSERT INTO imp_print_notic
                                       (prsp_cod_proc,
                                        prsp_cod_clie,
                                        prsp_cod_paqu,
                                        cod_grup,
                                        oid_noti,
                                        tip_cont,
                                        tex_noti,
                                        nom_imag,
                                        usu_crea,
                                        fec_crea,
                                        ind_acti,
                                        cod_tabl)
                                     VALUES
                                       (lscodproc,
                                        lscodclie,
                                        lscodpaq,
                                        lscodgrupo,
                                        lnoidnoti,
                                        'T',
                                        ls_mensaje,
                                        ' ',
                                        'OPERADOR RV',
                                        SYSDATE,
                                        '1',
                                        ln_numtab);
                                  END IF;

                               END IF;

                           ELSE    -- ESTRUCTURA DE TABLA

                              INSERT INTO imp_print_notic
                                (prsp_cod_proc,
                                 prsp_cod_clie,
                                 prsp_cod_paqu,
                                 cod_grup,
                                 oid_noti,
                                 tip_cont,
                                 tex_noti,
                                 nom_imag,
                                 usu_crea,
                                 fec_crea,
                                 ind_acti,
                                 cod_tabl)
                              VALUES
                                (lscodproc,
                                 lscodclie,
                                 lscodpaq,
                                 lscodgrupo,
                                 lnoidnoti,
                                 'D',
                                 ' ',
                                 ' ',
                                 'OPERADOR RV',
                                 SYSDATE,
                                 1,
                                 ln_numtab);

                              ln_nrolinea := 0;
                              l_inifijo := 1;
                              WHILE  instr(ls_mensaje,'<txt>', l_inifijo ) > 0 -- MIENTRAS HAYA LINEAS EN EL MENSAJE
                              LOOP
                                   l_inifijo := instr(ls_mensaje,'<txt>',l_inifijo);
                                   l_finfijo := instr(ls_mensaje,'</txt>',l_inifijo);
                                   ls_lineamsg := substr(ls_mensaje, l_inifijo+length('<txt>'), l_finfijo - (l_inifijo+length('<txt>')) );

                                   l_inifijo :=  l_finfijo + length('</txt>') ;
                                   ln_nrolinea := ln_nrolinea + 1;
                                     -- DBMS_OUTPUT.put_line(ls_lineamsg);

                                   IF  INSTR (ls_lineamsg, '<t/>',1) = 0 OR INSTR (ls_lineamsg, '<t/>',1) = NULL THEN  -- TITULO
                                       ls_indTLin :='T';
                                       INSERT INTO imp_print_notic_detal ndet
                                         (prsp_cod_proc,
                                          prsp_cod_clie,
                                          prsp_cod_paqu,
                                          prno_cod_grup,
                                          prno_oid_noti,
                                          oid_noti_deta,
                                        ---  cod_tabl,
                                          tip_fila,
                                          val_colu_01,
                                          val_colu_02,
                                          val_colu_03,
                                          val_colu_04,
                                          val_colu_05,
                                          val_colu_06,
                                          val_colu_07,
                                          val_colu_08,
                                          val_colu_09,
                                          val_colu_10,
                                          val_colu_11,
                                          val_colu_12,
                                          usu_crea,
                                          fec_crea,
                                          ind_acti)
                                       VALUES
                                         (lscodproc,
                                          lscodclie,
                                          lscodpaq,
                                          lscodgrupo,
                                          lnoidnoti,
                                          ln_nrolinea,
                                        --  ln_numtab,
                                          ls_indtlin,
                                          ls_lineamsg,
                                          ' ',
                                          ' ',
                                          ' ',
                                          ' ',
                                          ' ',
                                          ' ',
                                          ' ',
                                          ' ',
                                          ' ',
                                          ' ',
                                          ' ',
                                          'OPERADOR RV',
                                          SYSDATE,
                                          1);


                                   ELSE -- RESTO DE DETALLE

                                      IF  ln_nrolinea = 2 THEN
                                         ls_indTLin:= 'S';
                                      ELSE
                                         ls_indTLin:= 'D';
                                      END IF;
                                      ln_inicol:=1;
                                      ln_fincol:=1;
                                      ln_nrocol:=0;

                                      ls_lineamsg:= replace(ls_lineamsg,'<fhe08/>');

                                      WHILE instr(ls_lineamsg,'<t/>') > 1
                                      LOOP
                                          ln_fincol := instr(ls_lineamsg,'<t/>');
                                          ls_colum := substr(ls_lineamsg, 1 , ln_fincol - 1 ) ;
                                          ls_lineamsg:= SUBSTR (ls_lineamsg, ln_fincol  + length('<t/>'),length(ls_lineamsg) -(ln_fincol -1 +length('<t/>')));
                                          ln_nrocol :=  ln_nrocol + 1;
                                          t_array(ln_nrocol):=ls_colum;

                                      END LOOP    ;

                                      ln_nrocol:= ln_nrocol +1 ;

                                      IF ls_lineamsg <> '<t/><t/>' THEN
                                         t_array(ln_nrocol ):=ls_lineamsg;
                                      ELSE
                                         t_array(ln_nrocol ):= ' ';
                                      END IF;

                                      FOR  Lcntr IN ln_nrocol +1 ..12
                                      LOOP
                                         t_array(Lcntr):= ' ';
                                      END LOOP;

                                      IF t_array(1)<> '<t/><t/><t/><t/><t/><t/>' THEN

                                          INSERT INTO imp_print_notic_detal ndet
                                            (prsp_cod_proc,
                                             prsp_cod_clie,
                                             prsp_cod_paqu,
                                             prno_cod_grup,
                                             prno_oid_noti,
                                             oid_noti_deta,
                                         --    cod_tabl,
                                             tip_fila,
                                             val_colu_01,
                                             val_colu_02,
                                             val_colu_03,
                                             val_colu_04,
                                             val_colu_05,
                                             val_colu_06,
                                             val_colu_07,
                                             val_colu_08,
                                             val_colu_09,
                                             val_colu_10,
                                             val_colu_11,
                                             val_colu_12,
                                             usu_crea,
                                             fec_crea,
                                             ind_acti)
                                          VALUES
                                            (lscodproc,
                                             lscodclie,
                                             lscodpaq,
                                             lscodgrupo,
                                             lnoidnoti,
                                             ln_nrolinea,
                                            -- ln_numtab,
                                             ls_indtlin,
                                             t_array(1),
                                             t_array(2),
                                             t_array(3),
                                             t_array(4),
                                             t_array(5),
                                             t_array(6),
                                             t_array(7),
                                             t_array(8),
                                             t_array(9),
                                             t_array(10),
                                             t_array(11),
                                             t_array(12),
                                             'OPERADOR RV',
                                             SYSDATE,
                                             1);
                                      END IF;   --t_array(ln_nrocol ) =1



                                   END IF;
                              END LOOP; -- FIN DE LINEA DEL WHILE

                           END IF;  -- FIN PARTE ESTRUCTURA DE TABLA


                      ELSE

                          BEGIN
                            SELECT MAX( NVL(OID_NOTI,0) )
                                INTO lnOidNoti
                                FROM IMP_PRINT_NOTIC
                                WHERE PRSP_COD_PROC  = lsCodProc
                                AND   PRSP_COD_CLIE  = lsCodClie
                                AND   PRSP_COD_PAQU  = lsCodPaq
                                AND   COD_GRUP       = lsCodGrupo ;
                          EXCEPTION
                          WHEN no_data_found THEN
                               lnOidNoti := 0;
                          END;

                         --   FOR x in 0..l_indexfinbloque LOOP
                         -- DBMS_OUTPUT.put_line('mensaje '|| ls_mensaje);
                          IF l_indexinibloque=0 then
                             ls_mensbloq:=ls_mensaje;
                             ls_mensfijo:='';
                             ls_mensvar:=ls_mensaje;

                          ELSE
                             ls_mensbloq := substr(ls_mensaje,instr(ls_mensaje,'<fijo>'), instr(ls_mensaje,'<fijo>',2)-1);
                             ls_mensfijo := substr(ls_mensbloq,instr(ls_mensbloq,'<fijo>')+length('<fijo>'), instr(ls_mensbloq,'</fijo>')-length('<fijo>')-1);
                             ls_mensvar := substr(ls_mensbloq,instr(ls_mensbloq,'</fijo>')+length('</fijo>'));

                          END IF;

                          --     -- DBMS_OUTPUT.put_line('b ' || ls_mensbloq);
                          -- DBMS_OUTPUT.put_line('f ' || ls_mensfijo);
                          --   -- DBMS_OUTPUT.put_line('v ' || ls_mensvar);

                          IF ls_mensvar IS NOT NULL THEN

                            OPEN c_buzon(r_mensaje(i).mens_oid_mens,p_oidclie,p_oidsoli);
                            LOOP
                                FETCH c_buzon BULK COLLECT  INTO r_buzon LIMIT w_filas;
                                IF  r_buzon.COUNT > 0 THEN   --- ABRIMOS BUZON
                                    IF ls_mensfijo IS NOT NULL  THEN
                                       IF  lnOidNoti is null or lnOidNoti= 0 then
                                           lnOidNoti:=1 ;
                                       ELSE
                                           lnOidNoti:= lnOidNoti +1;
                                       END IF;
                                       INSERT INTO imp_print_notic
                                         (prsp_cod_proc,
                                          prsp_cod_clie,
                                          prsp_cod_paqu,
                                          cod_grup,
                                          oid_noti,
                                          tip_cont,
                                          tex_noti,
                                          nom_imag,
                                          usu_crea,
                                          fec_crea,
                                          ind_acti,
                                          cod_tabl)
                                       VALUES
                                         (lscodproc,
                                          lscodclie,
                                          lscodpaq,
                                          lscodgrupo,
                                          lnoidnoti,
                                          'D',
                                          ' ',
                                          ' ',
                                          'OPERADOR RV',
                                          SYSDATE,
                                          '1',
                                          ln_numtab);

                                       ln_nrolinea := 0;
                                       l_inifijo := 1;

                                       WHILE  instr(ls_mensfijo,'<txt>', l_inifijo ) >0 -- MIENTRAS HAYA LINEAS EN EL MENSAJE
                                       LOOP
                                           l_inifijo := instr(ls_mensfijo,'<txt>',l_inifijo);
                                           l_finfijo :=  instr(ls_mensfijo,'</txt>',l_inifijo);
                                           ls_lineamsg := substr(ls_mensfijo, l_inifijo+length('<txt>'), l_finfijo - (l_inifijo+length('<txt>')) );

                                           l_inifijo :=  l_finfijo + length('</txt>') ;
                                           ln_nrolinea := ln_nrolinea + 1;
                                           -- DBMS_OUTPUT.put_line('LINEA ' ||ln_nrolinea || ' ' || ls_lineamsg);

                                           IF  INSTR (ls_lineamsg, '<t/>',1) = 0 OR   INSTR (ls_lineamsg, '<t/>',1) = NULL THEN  -- TITULO
                                               ls_indTLin :='T';
                                               INSERT INTO imp_print_notic_detal ndet
                                                 (prsp_cod_proc,
                                                  prsp_cod_clie,
                                                  prsp_cod_paqu,
                                                  prno_cod_grup,
                                                  prno_oid_noti,
                                                  oid_noti_deta,
                                                 -- cod_tabl,
                                                  tip_fila,
                                                  val_colu_01,
                                                  val_colu_02,
                                                  val_colu_03,
                                                  val_colu_04,
                                                  val_colu_05,
                                                  val_colu_06,
                                                  val_colu_07,
                                                  val_colu_08,
                                                  val_colu_09,
                                                  val_colu_10,
                                                  val_colu_11,
                                                  val_colu_12,
                                                  usu_crea,
                                                  fec_crea,
                                                  ind_acti)
                                               VALUES
                                                 (lscodproc,
                                                  lscodclie,
                                                  lscodpaq,
                                                  lscodgrupo,
                                                  lnoidnoti,
                                                  ln_nrolinea,
                                                --  ln_numtab,
                                                  ls_indtlin,
                                                  ls_lineamsg,
                                                  ' ',
                                                  ' ',
                                                  ' ',
                                                  ' ',
                                                  ' ',
                                                  ' ',
                                                  ' ',
                                                  ' ',
                                                  ' ',
                                                  ' ',
                                                  ' ',
                                                  'OPERADOR RV',
                                                  SYSDATE,
                                                  1);

                                           ELSE  --SUBTITULO
                                              -- -- DBMS_OUTPUT.put_line('subtitutlo ');
                                               ln_nrocol:=0;
                                               ls_indTLin :='S';
                                               ln_fincol :=1;
                                               WHILE instr(ls_lineamsg,'<t/>') > 1  -- BUSCA SEPARADOR
                                               LOOP
                                                 select instr(ls_lineamsg,'<t/>') into  ln_fincol from dual;
                                                 select substr(ls_lineamsg, 1 , ln_fincol - 1 ) into ls_colum from dual;
                                                 ls_lineamsg:= SUBSTR (ls_lineamsg, ln_fincol  + length('<t/>'),length(ls_lineamsg) -(ln_fincol -1 +length('<t/>')));

                                                 ln_nrocol :=  ln_nrocol + 1;
                                                 t_array(ln_nrocol):=ls_colum;


                                               END LOOP    ;  -- FIN WHILE BUSCA SEPARADOR

                                               ln_nrocol :=  ln_nrocol + 1;
                                               FOR  Lcntr IN ln_nrocol..12
                                               LOOP
                                                    t_array(Lcntr):= ' ';
                                               END LOOP;

                                               -- DBMS_OUTPUT.put_line( lsCodProc || ' ' || lsCodClie || ' ' || lsCodPaq || ' ' || lsCodGrupo || ' ' || lnOidNoti || ' ' || ln_nrolinea || ' ' || ln_numtab );

                                               INSERT INTO imp_print_notic_detal ndet
                                                 (prsp_cod_proc,
                                                  prsp_cod_clie,
                                                  prsp_cod_paqu,
                                                  prno_cod_grup,
                                                  prno_oid_noti,
                                                  oid_noti_deta,
                                               --   cod_tabl,
                                                  tip_fila,
                                                  val_colu_01,
                                                  val_colu_02,
                                                  val_colu_03,
                                                  val_colu_04,
                                                  val_colu_05,
                                                  val_colu_06,
                                                  val_colu_07,
                                                  val_colu_08,
                                                  val_colu_09,
                                                  val_colu_10,
                                                  val_colu_11,
                                                  val_colu_12,
                                                  usu_crea,
                                                  fec_crea,
                                                  ind_acti)
                                               VALUES
                                                 (lscodproc,
                                                  lscodclie,
                                                  lscodpaq,
                                                  lscodgrupo,
                                                  lnoidnoti,
                                                  ln_nrolinea,
                                                --  ln_numtab,
                                                  ls_indtlin,
                                                  t_array(1),
                                                  t_array(2),
                                                  t_array(3),
                                                  t_array(4),
                                                  t_array(5),
                                                  t_array(6),
                                                  t_array(7),
                                                  t_array(8),
                                                  t_array(9),
                                                  t_array(10),
                                                  t_array(11),
                                                  t_array(12),
                                                  'OPERADOR RV',
                                                  SYSDATE,
                                                  1);

                                           END IF;  -- TITULO
                                       END LOOP; --   WHILE  instr(ls_mensfijo,'<txt>', l_inifijo ) >0

                                    END IF;  --if ls_mensfijo IS NOT NULL


                                    FOR j IN r_buzon.FIRST..r_buzon.LAST
                                    LOOP
                                        ls_menstemp:=ls_mensvar;

                                        if instr(ls_menstemp,'<DATO01>')>0 THEN
                                           ls_menstemp := replace(ls_menstemp,'<DATO01>', r_buzon(j).dato_vari_01);
                                        end if;
                                        if instr(ls_menstemp,'<DATO02>')>0 THEN
                                           ls_menstemp := replace(ls_menstemp,'<DATO02>', r_buzon(j).dato_vari_02);
                                        end if;
                                        if instr(ls_menstemp,'<DATO03>')>0 THEN
                                           ls_menstemp := replace(ls_menstemp,'<DATO03>', r_buzon(j).dato_vari_03);
                                        end if;
                                        if instr(ls_menstemp,'<DATO04>')>0 THEN
                                           ls_menstemp := replace(ls_menstemp,'<DATO04>', r_buzon(j).dato_vari_04);
                                        end if;
                                        if instr(ls_menstemp,'<DATO05>')>0 THEN
                                           ls_menstemp := replace(ls_menstemp,'<DATO05>', r_buzon(j).dato_vari_05);
                                        end if;
                                        if instr(ls_menstemp,'<DATO06>')>0 THEN
                                           ls_menstemp := replace(ls_menstemp,'<DATO06>', r_buzon(j).dato_vari_06);
                                        end if;
                                        if instr(ls_menstemp,'<DATO07>')>0 THEN
                                           ls_menstemp := replace(ls_menstemp,'<DATO07>', r_buzon(j).dato_vari_07);
                                        end if;
                                        if instr(ls_menstemp,'<DATO08>')>0 THEN
                                           ls_menstemp := replace(ls_menstemp,'<DATO08>', r_buzon(j).dato_vari_08);
                                        end if;
                                        if instr(ls_menstemp,'<DATO09>')>0 THEN
                                           ls_menstemp := replace(ls_menstemp,'<DATO09>', r_buzon(j).dato_vari_09);
                                        end if;
                                        if instr(ls_menstemp,'<DATO10>')>0 THEN
                                           ls_menstemp := replace(ls_menstemp,'<DATO10>', r_buzon(j).dato_vari_10);
                                        end if;
                                        if instr(ls_menstemp,'<DATO11>')>0 THEN
                                           ls_menstemp := replace(ls_menstemp,'<DATO11>', r_buzon(j).dato_vari_11) ;
                                        end if;
                                        if instr(ls_menstemp,'<DATO12>')>0 THEN
                                           ls_menstemp := replace(ls_menstemp,'<DATO12>', r_buzon(j).dato_vari_12);
                                        end if;
                                        if instr(ls_menstemp,'<DATO13>')>0 THEN
                                           ls_menstemp := replace(ls_menstemp,'<DATO13>', r_buzon(j).dato_vari_13);
                                        end if;
                                        if instr(ls_menstemp,'<DATO14>')>0 THEN
                                           ls_menstemp := replace(ls_menstemp,'<DATO14>', r_buzon(j).dato_vari_14);
                                        end if;
                                        if instr(ls_menstemp,'<DATO15>')>0 THEN
                                           ls_menstemp := replace(ls_menstemp,'<DATO15>', r_buzon(j).dato_vari_15);
                                        end if;
                                        if instr(ls_menstemp,'<DATO16>')>0 THEN
                                           ls_menstemp := replace(ls_menstemp,'<DATO16>', r_buzon(j).dato_vari_16);
                                        end if;
                                        if instr(ls_menstemp,'<DATO17>')>0 THEN
                                           ls_menstemp := replace(ls_menstemp,'<DATO17>', r_buzon(j).dato_vari_17);
                                        end if;
                                        if instr(ls_menstemp,'<DATO18>')>0 THEN
                                           ls_menstemp := replace(ls_menstemp,'<DATO18>', r_buzon(j).dato_vari_18);
                                        end if;
                                        if instr(ls_menstemp,'<DATO19>')>0 THEN
                                           ls_menstemp := replace(ls_menstemp,'<DATO19>', r_buzon(j).dato_vari_19);
                                        end if;
                                        if instr(ls_menstemp,'<DATO20>')>0 THEN
                                           ls_menstemp := replace(ls_menstemp,'<DATO20>', r_buzon(j).dato_vari_20);
                                        end if;

                                        /* update msg_buzon_mensa set ind_acti=0, num_lote_impr=p_numlotefact, fec_impr=sysdate
                                          where oid_buzo_mens=r_buzon(j).oid_buzo_mens;*/

                                        -- -- DBMS_OUTPUT.put_line('v ' || ls_menstemp);
                                        -- falta quitar txt y b

                                        IF ls_mensfijo IS NULL THEN
                                           ls_menstemp := replace( ls_menstemp , '<txt>');
                                           ls_menstemp := replace( ls_menstemp , '</txt>');
                                           ls_menstemp := replace( ls_menstemp , '<txt/>');
                                           ls_menstemp := replace( ls_menstemp , '<b>');
                                           ls_menstemp := replace( ls_menstemp , '</b>');

                                           ls_menstemp := substr(ls_menstemp,1,1000);
                                           IF  lnOidNoti is null or lnOidNoti= 0 then
                                               lnOidNoti:=1 ;
                                           ELSE
                                               lnOidNoti:= lnOidNoti +1;
                                           END IF;
                                           INSERT INTO imp_print_notic
                                             (prsp_cod_proc,
                                              prsp_cod_clie,
                                              prsp_cod_paqu,
                                              cod_grup,
                                              oid_noti,
                                              tip_cont,
                                              tex_noti,
                                              nom_imag,
                                              usu_crea,
                                              fec_crea,
                                              ind_acti,
                                              cod_tabl)
                                           VALUES
                                             (lscodproc,
                                              lscodclie,
                                              lscodpaq,
                                              lscodgrupo,
                                              lnoidnoti,
                                              'T',
                                              ls_menstemp,
                                              ' ',
                                              'OPERADOR RV',
                                              SYSDATE,
                                              '1',
                                              ln_numtab);

                                        ELSE   --  ls_mensfijo IS NOT  NULL
                                           l_inifijo := 1;

                                           WHILE  instr(ls_menstemp,'<txt>', l_inifijo ) >0 -- MIENTRAS HAYA LINEAS EN EL MENSAJE
                                           LOOP
                                             l_inifijo := instr(ls_menstemp,'<txt>',l_inifijo) ;
                                             l_finfijo := instr(ls_menstemp,'</txt>',l_inifijo);
                                             ls_lineamsg := substr(ls_menstemp, l_inifijo+length('<txt>'), l_finfijo - (l_inifijo+length('<txt>')) );

                                             l_inifijo :=  l_finfijo + length('</txt>') ;
                                             ln_nrolinea := ln_nrolinea + 1;
                                             -- DBMS_OUTPUT.put_line('LINEA ' ||ln_nrolinea || ' ' || ls_lineamsg);
                                             ln_nrocol:=0;
                                             ls_indTLin :='D';
                                             ln_fincol :=1;
                                             WHILE instr(ls_lineamsg,'<t/>') > 1  -- BUSCA SEPARADOR
                                             LOOP
                                               ln_fincol := instr(ls_lineamsg,'<t/>');
                                               ls_colum := substr(ls_lineamsg, 1 , ln_fincol - 1 ) ;
                                               ls_lineamsg:= SUBSTR (ls_lineamsg, ln_fincol  + length('<t/>'),length(ls_lineamsg) -(ln_fincol -1 +length('<t/>')));

                                               ln_nrocol :=  ln_nrocol + 1;
                                               t_array(ln_nrocol):=ls_colum;

                                             END LOOP    ;  -- FIN WHILE BUSCA SEPARADOR

                                             ln_nrocol :=  ln_nrocol + 1;
                                             FOR  Lcntr IN ln_nrocol..12
                                             LOOP
                                                  t_array(Lcntr):= ' ';
                                             END LOOP;

                                             INSERT INTO imp_print_notic_detal ndet
                                               (prsp_cod_proc,
                                                prsp_cod_clie,
                                                prsp_cod_paqu,
                                                prno_cod_grup,
                                                prno_oid_noti,
                                                oid_noti_deta,
                                                --cod_tabl,
                                                tip_fila,
                                                val_colu_01,
                                                val_colu_02,
                                                val_colu_03,
                                                val_colu_04,
                                                val_colu_05,
                                                val_colu_06,
                                                val_colu_07,
                                                val_colu_08,
                                                val_colu_09,
                                                val_colu_10,
                                                val_colu_11,
                                                val_colu_12,
                                                usu_crea,
                                                fec_crea,
                                                ind_acti)
                                             VALUES
                                               (lscodproc,
                                                lscodclie,
                                                lscodpaq,
                                                lscodgrupo,
                                                lnoidnoti,
                                                ln_nrolinea,
                                               -- ln_numtab,
                                                ls_indtlin,
                                                t_array(1),
                                                t_array(2),
                                                t_array(3),
                                                t_array(4),
                                                t_array(5),
                                                t_array(6),
                                                t_array(7),
                                                t_array(8),
                                                t_array(9),
                                                t_array(10),
                                                t_array(11),
                                                t_array(12),
                                                'OPERADOR RV',
                                                SYSDATE,
                                                1);

                                           END LOOP;  -- END WHILE  instr(ls_menstemp,'<txt>', l_inifijo ) >0

                                        END IF; -- ls_mensfijo IS NULL

                                    END LOOP;  -- BUZON

                                END IF;  -- r_buzon.COUNT > 0   l

                                EXIT WHEN c_buzon%NOTFOUND;
                            END LOOP;
                            -- Cerramos el cursor
                            CLOSE c_buzon;

                          END IF; --ls_mensvar IS NOT NULL


                          /*  select substr(ls_mensaje,l_indexfinbloque)into ls_mensaje from dual;
                              select instr(ls_mensaje,'<fijo>') into l_indexinibloque from dual;
                              select instr(ls_mensaje,'<fijo>',l_indexinibloque+1) into l_indexfinbloque from dual;*/

                          --    end loop; -- FOR x in 0..l_indexfinbloque

                      END IF;  --- SI r_mensaje(i).tmen_oid_tipo_mens = 2

                      ls_codpatr_antg:=substr(r_mensaje(i).cod_patr,1,4);
                      ls_codsecc_antg:=r_mensaje(i).cod_secc;
                      ls_vallite_antg:=r_mensaje(i).VAL_LITE_IDEN;
                      lsIdetificadorSeccionAnt :=  r_mensaje(i).IDE_SECC;
                      lsNivelSeccionAnt:= r_mensaje(i).NIV_SECC;
                      lsGruposeccionAnt:= r_mensaje(i).IND_GRUP_SECC;

                  END LOOP;  -- FOR  MENSAJE
              END IF;  -- if CURSOR MENSAJE
              EXIT WHEN c_mensaje%NOTFOUND;
          END LOOP; -- CURSOR MENSAJE
          -- Cerramos el cursor
          CLOSE c_mensaje;
          ls_codpatr_antg           :=' ';
          ls_codsecc_antg           :=' ';
          ls_vallite_antg           :=' ';

        END LOOP; -- for patron
     END IF; -- CURSOR PATRON
     EXIT WHEN c_patron%NOTFOUND;
END LOOP; --LOOP PATRON
-- Cerramos el cursor
CLOSE c_patron;

--Se libera la variabla CLOB de Memoria
DBMS_LOB.FREETEMPORARY(l_clob);

--COMMIT;
END IMP_PR_PROCE_NOTIC_CORPO;

/**************************************************************************
Descripcion : Devuelve 1 si el mensaje aplica para el cliente o 0 si no
Fecha Creacion : 13/01/2016
Autor : Carlos Bazalar
***************************************************************************/
FUNCTION imp_fn_mens_ua_clie(p_oidclie NUMBER, p_codPeri VARCHAR2, p_codMens VARCHAR2)
RETURN NUMBER
 IS

 lnRetorno      NUMBER(3);
 lncontador     NUMBER(10);

BEGIN
lnRetorno:= 0;

SELECT COUNT(1)
INTO lncontador
FROM
   msg_mensa_campa x,
   msg_cores_mensa_cabec y,
   msg_cores_mensa_detal z,
   msg_consi_restr w
WHERE x.cam_proc = p_codPeri
  AND x.cod_mens = p_codMens
  AND y.pais_cod_pais = x.pais_cod_pais
  AND y.meca_cam_proc = x.cam_proc
  AND y.meca_cod_mens = x.cod_mens

  AND z.pais_cod_pais = x.pais_cod_pais
  AND z.meca_cam_proc = x.cam_proc
  AND z.meca_cod_mens = x.cod_mens
  AND z.core_cod_cons_rest = y.core_cod_cons_rest

  AND w.cod_cons_rest = z.core_cod_cons_rest
  AND w.abr_cons_rest = 'UAS'
  AND z.ind_acti = 1
  AND z.est_regi = '1';

IF lncontador = 0 THEN
   RETURN 1;
END IF;

SELECT COUNT(1)
INTO lncontador
FROM
   msg_mensa_campa x,
   msg_cores_mensa_cabec y,
   msg_cores_mensa_detal z,
   msg_consi_restr w,
   (select f.cod_regi, e.cod_zona, c.cod_secc, d.cod_terr, a.clie_oid_clie, g.cod_clie
    from mae_clien_unida_admin a,
           zon_terri_admin b,
           zon_secci c,
           zon_terri d,
           zon_zona e,
           zon_regio f,
           mae_clien g,
           mae_clien_tipo_subti h

    where a.ztad_oid_terr_admi = b.oid_terr_admi
       and b.zscc_oid_secc = c.oid_secc
       and b.terr_oid_terr = d.oid_terr
       and c.zzon_oid_zona = e.oid_zona
       and e.zorg_oid_regi = f.oid_regi
       and a.ind_acti = 1
       and a.clie_oid_clie = g.oid_clie
       and g.oid_clie = h.clie_oid_clie
       and h.ticl_oid_tipo_clie = 2
       and h.ind_ppal = 1
       AND g.oid_clie = p_oidclie) ua

WHERE x.cam_proc = p_codPeri
  AND x.cod_mens = p_codMens
  AND y.pais_cod_pais = x.pais_cod_pais
  AND y.meca_cam_proc = x.cam_proc
  AND y.meca_cod_mens = x.cod_mens

  AND z.pais_cod_pais = x.pais_cod_pais
  AND z.meca_cam_proc = x.cam_proc
  AND z.meca_cod_mens = x.cod_mens
  AND z.core_cod_cons_rest = y.core_cod_cons_rest

  AND w.cod_cons_rest = z.core_cod_cons_rest
  AND w.abr_cons_rest = 'UAS'
  AND z.ind_acti = 1
  AND z.est_regi = '1'
  AND ( (ltrim(rtrim(z.VAL_CON4)) IS NULL) OR (ltrim(rtrim(z.VAL_CON4)) IS NOT NULL AND z.VAL_CON4 = ua.cod_terr) )
  AND ( (ltrim(rtrim(z.VAL_CON3)) IS NULL) OR (ltrim(rtrim(z.VAL_CON3)) IS NOT NULL AND z.VAL_CON3 = ua.cod_secc) )
  AND ( (ltrim(rtrim(z.VAL_CON2)) IS NULL) OR (ltrim(rtrim(z.VAL_CON2)) IS NOT NULL AND z.VAL_CON2 = ua.cod_zona) )
  AND ( (ltrim(rtrim(z.VAL_CON1)) IS NULL) OR (ltrim(rtrim(z.VAL_CON1)) IS NOT NULL AND z.VAL_CON1 = ua.cod_regi) );

IF lnContador > 0 THEN
  lnRetorno := 1;
END IF;

RETURN lnRetorno;
END imp_fn_mens_ua_clie;

/**************************************************************************
Descripcion : Devuelve 1 si el mensaje aplica para el cliente o 0 si no
Fecha Creacion : 13/01/2016
Autor : Carlos Bazalar
***************************************************************************/
FUNCTION imp_fn_mens_tipo_clie(p_oidclie NUMBER, p_codPeri VARCHAR2, p_codMens VARCHAR2)
RETURN NUMBER
 IS

 lnRetorno      NUMBER(3);
 lncontador     NUMBER(10);

BEGIN
lnRetorno:= 0;

SELECT COUNT(1)
INTO lncontador
FROM
   msg_mensa_campa x,
   msg_cores_mensa_cabec y,
   msg_cores_mensa_detal z,
   msg_consi_restr w
WHERE x.cam_proc = p_codPeri
  AND x.cod_mens = p_codMens
  AND y.pais_cod_pais = x.pais_cod_pais
  AND y.meca_cam_proc = x.cam_proc
  AND y.meca_cod_mens = x.cod_mens

  AND z.pais_cod_pais = x.pais_cod_pais
  AND z.meca_cam_proc = x.cam_proc
  AND z.meca_cod_mens = x.cod_mens
  AND z.core_cod_cons_rest = y.core_cod_cons_rest

  AND w.cod_cons_rest = z.core_cod_cons_rest
  AND w.abr_cons_rest = 'TCL'
  AND z.ind_acti = 1
  AND z.est_regi = '1';

IF lncontador = 0 THEN
   RETURN 1;
END IF;


SELECT COUNT(1)
INTO lncontador
FROM
   msg_mensa_campa x,
   msg_cores_mensa_cabec y,
   msg_cores_mensa_detal z,
   msg_consi_restr w,
   (select a.ticl_oid_tipo_clie, c.cod_tipo_clie,
       a.sbti_oid_subt_clie, d.cod_subt_clie,
       b.tccl_oid_tipo_clasi, e.cod_tipo_clas,
       b.clas_oid_clas, f.cod_clas
     from mae_clien_tipo_subti a,
          mae_clien_clasi b,
          mae_tipo_clien c,
          mae_subti_clien d,
          mae_tipo_clasi_clien e,
          mae_clasi f
     where a.clie_oid_clie= p_oidclie
       and a.oid_clie_tipo_subt = b.ctsu_oid_clie_tipo_subt
       AND c.oid_tipo_clie = a.ticl_oid_tipo_clie
       AND d.oid_subt_clie = a.sbti_oid_subt_clie
       AND e.oid_tipo_clas = b.tccl_oid_tipo_clasi
       AND f.oid_clas = b.clas_oid_clas ) ua

WHERE x.cam_proc = p_codPeri
  AND x.cod_mens = p_codMens
  AND y.pais_cod_pais = x.pais_cod_pais
  AND y.meca_cam_proc = x.cam_proc
  AND y.meca_cod_mens = x.cod_mens

  AND z.pais_cod_pais = x.pais_cod_pais
  AND z.meca_cam_proc = x.cam_proc
  AND z.meca_cod_mens = x.cod_mens
  AND z.core_cod_cons_rest = y.core_cod_cons_rest

  AND w.cod_cons_rest = z.core_cod_cons_rest
  AND w.abr_cons_rest = 'TCL'
  AND z.ind_acti = 1
  AND z.est_regi = '1'
  AND ( (rtrim(ltrim(z.VAL_CON4)) IS NULL) OR (rtrim(ltrim(z.VAL_CON4)) IS NOT NULL AND z.VAL_CON4 = ua.cod_clas) )
  AND ( (rtrim(ltrim(z.VAL_CON3)) IS NULL) OR (rtrim(ltrim(z.VAL_CON3)) IS NOT NULL AND z.VAL_CON3 = ua.cod_tipo_clas) )
  AND ( (rtrim(ltrim(z.VAL_CON2)) IS NULL) OR (rtrim(ltrim(z.VAL_CON2)) IS NOT NULL AND z.VAL_CON2 = ua.cod_subt_clie) )
  AND ( (rtrim(ltrim(z.VAL_CON1)) IS NULL) OR (rtrim(ltrim(z.VAL_CON1)) IS NOT NULL AND z.VAL_CON1 = ua.cod_tipo_clie) );

IF lnContador > 0 THEN
  lnRetorno := 1;
END IF;

RETURN lnRetorno;
END imp_fn_mens_tipo_clie;

/**************************************************************************
Descripcion : Devuelve 1 si el mensaje aplica para el cliente o 0 si no
la Hora en formato dd/mm/yyyy
Fecha Creacion : 07/02/2012
Autor : Jorge Yepez
***************************************************************************/
FUNCTION imp_fn_mens_pedid_clie(p_oidsoli NUMBER, p_codPeri VARCHAR2, p_codMens VARCHAR2)  RETURN NUMBER
 IS
 lncontador     NUMBER(10);
BEGIN

lncontador:= 0;
SELECT COUNT(1)
INTO lncontador
FROM
   msg_mensa_campa x,
   msg_cores_mensa_cabec y,
   msg_consi_restr w
WHERE x.cam_proc = p_codPeri
  AND x.cod_mens = p_codMens
  AND y.pais_cod_pais = x.pais_cod_pais
  AND y.meca_cam_proc = x.cam_proc
  AND y.meca_cod_mens = x.cod_mens
  AND W.COD_CONS_REST = Y.CORE_COD_CONS_REST
  AND w.abr_cons_rest = 'PED'
  AND y.ind_acti = 1
  AND y.est_regi = '1';

IF lncontador = 0 THEN
   RETURN 1;
END IF;

 select count(1)
 into lncontador
 from ped_solic_cabec a,
      ped_solic_cabec d
 where a.oid_soli_cabe = p_oidsoli
   and a.oid_soli_cabe = d.soca_oid_soli_cabe
   and d.ind_oc = 1 ;

 if lncontador>0 then
    return 1;
 else
    return 0;
 end if;

RETURN 1;
END imp_fn_mens_pedid_clie;

/**************************************************************************
Descripcion : Devuelve 1 si el mensaje aplica para el cliente o 0 si no
la Hora en formato dd/mm/yyyy
Fecha Creacion : 07/02/2012
Autor : Jorge Yepez
***************************************************************************/
FUNCTION imp_fn_mens_lista_clie(
   p_oidclie NUMBER,
   p_oidsoli NUMBER,
   p_codPeri VARCHAR2,
   p_codMens VARCHAR2)  RETURN NUMBER
IS
 lncontador     NUMBER(10);
BEGIN

lncontador:= 0;
SELECT COUNT(1)
INTO lncontador
FROM
   msg_mensa_campa x,
   msg_cores_mensa_cabec y,
   msg_consi_restr w
WHERE x.cam_proc = p_codPeri
  AND x.cod_mens = p_codMens
  AND y.pais_cod_pais = x.pais_cod_pais
  AND y.meca_cam_proc = x.cam_proc
  AND y.meca_cod_mens = x.cod_mens
  AND W.COD_CONS_REST = Y.CORE_COD_CONS_REST
  AND w.abr_cons_rest = 'LCL'
  AND y.ind_acti = 1
  AND y.est_regi = '1';

IF lncontador = 0 THEN
   RETURN 1;
END IF;

select count(1)
into lncontador
from  msg_mensa_campa b,
      msg_buzon_mensa c
 where c.clie_oid_clie = p_oidclie
 AND b.cam_proc = p_codPeri
 and b.cod_mens = p_codMens
 and b.mens_oid_mens = c.mens_oid_mens
 and (c.peri_oid_peri is null or
      c.peri_oid_peri=(select oid_peri
                       from seg_perio_corpo x, bas_ctrl_fact z
                       where x.cod_peri = z.cod_peri
                          and z.sta_camp=0
                          and z.ind_camp_act=1
     )
     and (c.ind_acti=1 or
           c.num_lote_impr=(select num_lote_fact
                            from ped_solic_cabec
                            where oid_soli_cabe = p_oidsoli))
     )
 ;
 if lncontador>0 then
    return 1;
 else
    return 0;
 end if;

RETURN 1;
END imp_fn_mens_lista_clie;




/***************************************************************************
Descripcion       : Procedimiemto que implementa la logica en IMP_PR_PROCE_DETAL_FACTU3_REGI
                    para su insercion en tablas cabecera y detalle: IMP_PRINT_PEDID_RESUM y
                    IMP_PRINT_PEDID_DETAL - PAIS CHILE

Parametros        : Codigo de pais, codigo de periodo, codigo de marca, codigo de canal
Fecha Creacion    : 07/12/2015
Autor             : Gonzalo Javier Huertas Agurto
***************************************************************************/



/***************************************************************************
Descripcion       : Procedimiemto que implementa la logica en IMP_PR_PROCE_DETAL_FACTU4_REGI
                    para su insercion en tablas cabecera y detalle: IMP_PRINT_PEDID_RESUM y
                    IMP_PRINT_PEDID_DETAL - PAIS COLOMBIA

Parametros        : Codigo de pais, codigo de periodo, codigo de marca, codigo de canal
Fecha Creacion    : 09/12/2015
Autor             : Gonzalo Javier Huertas Agurto
***************************************************************************/
PROCEDURE IMP_PR_PROCE_PEDID_CO(p_codigoPais IN VARCHAR2,
                                   p_codigoPeriodo IN VARCHAR2,
                                   p_fechaFacturacion IN VARCHAR2,
                                   p_oidZona NUMBER,
                                   psCodigoTransaccional varchar2,
                                   psCodigoPaquete varchar2,
                                   psCodigoUsuario varchar2) IS

CURSOR c_consolidados(oidPeriodo NUMBER,
                      indicadorEnvioLarissa VARCHAR2,
                      numeroLoteFacturacion NUMBER) IS
SELECT sp.oid_pais,
       sp.cod_pais,
       mc.oid_clie,
       mc.cod_clie,
       mc.cod_digi_ctrl,
       mc.val_nom1,
       mc.val_nom2,
       mc.val_ape1,
       mc.val_ape2,
       mci.num_docu_iden,
       con.oid_soli_cabe,
       con.val_nume_soli,
       con.fec_fact,
       con.val_impo_flet_tota_loca,
       con.val_impo_impu_tota_loca,
       con.val_impo_redo_loca,
       substr(des_pais,
              1,
              length(des_pais) - decode(instr(des_pais, 'ESIKA'), 0, 0, 5) -
              decode(instr(des_pais, 'LBEL'), 0, 0, 4)) des_pais,
       zon.cod_zona,
       sec.cod_secc,
       ter.cod_terr,
       sec.num_secu_fact_diar,
       0 saldo_a_favor,
       0 saldo_anterior,
       con.val_tota_gast_admi gastos,
       con.val_impo_rete_desc,
       nvl((SELECT x.val_text_comu
             FROM mae_clien_comun x
            WHERE clie_oid_clie = con.clie_oid_clie
              AND x.ticm_oid_tipo_comu = 1
              AND rownum = 1),
           (SELECT x.val_text_comu
              FROM mae_clien_comun x
             WHERE clie_oid_clie = con.clie_oid_clie
               AND x.ticm_oid_tipo_comu = 6
               AND rownum = 1)) tel_clie,
       (SELECT val_nom1 || ' ' || val_nom2 || ' ' || val_ape1 || ' ' ||
               val_ape2
          FROM mae_clien
         WHERE oid_clie = zon.clie_oid_clie) gerente,
       (SELECT x.val_text_comu
          FROM mae_clien_comun x
         WHERE clie_oid_clie = zon.clie_oid_clie
           AND x.ticm_oid_tipo_comu = 6
           AND rownum = 1) cel_gerente,
       (SELECT val_nom1 || ' ' || val_nom2 || ' ' || val_ape1 || ' ' ||
               val_ape2
          FROM mae_clien
         WHERE oid_clie = sec.clie_oid_clie) ejecutiva,
       (SELECT x.val_text_comu
          FROM mae_clien_comun x
         WHERE clie_oid_clie = sec.clie_oid_clie
           AND x.ticm_oid_tipo_comu = 6
           AND rownum = 1) cel_ejecutiva,
       (SELECT num_docu_cont_inte
          FROM fac_docum_conta_cabec
         WHERE soca_oid_soli_cabe = con.oid_soli_cabe
           AND tido_oid_tipo_docu = 1
           AND rownum = 1) factura,
       mcda.ind_impr_pdoc
  FROM mae_clien             mc,
       mae_clien_ident       mci,
       mae_clien_datos_adici mcda,
       ped_solic_cabec       con,
       zon_zona              zon,
       zon_terri             ter,
       zon_terri_admin       zta,
       zon_secci             sec,
       ped_solic_cabec_secue sec,
       seg_pais              sp,
       bas_pais              bp
 WHERE mc.oid_clie = con.clie_oid_clie
   AND mc.oid_clie = mcda.clie_oid_clie
   AND mc.oid_clie = mci.clie_oid_clie
   AND mci.val_iden_docu_prin = 1
   AND sp.oid_pais = con.pais_oid_pais
   AND sp.cod_pais = bp.cod_pais
   AND con.zzon_oid_zona = zon.oid_zona
   AND con.terr_oid_terr = ter.oid_terr
   AND con.ztad_oid_terr_admi = zta.oid_terr_admi
   AND zta.zscc_oid_secc = sec.oid_secc
   AND con.oid_soli_cabe = sec.soca_oid_soli_cabe
   AND con.fec_fact = to_date(p_fechafacturacion, 'dd/mm/yyyy')
   AND con.perd_oid_peri = oidperiodo
   AND zon.oid_zona = p_oidzona
   AND con.ind_inte_lari_gene = indicadorenviolarissa
   AND (numerolotefacturacion IS NULL OR
       con.num_lote_fact = numerolotefacturacion)
   AND con.num_unid_aten_tota > 0
   AND EXISTS
 (SELECT NULL
          FROM int_lar_tipo_solici_pedido_dis l
         WHERE l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais);


TYPE consolidadorecord IS RECORD (
    oid_pais                seg_pais.oid_pais%TYPE,
    cod_pais                seg_pais.cod_pais%TYPE,
    oid_clie                mae_clien.oid_clie%TYPE,
    cod_clie                mae_clien.cod_clie%TYPE,
    cod_digi_ctrl           mae_clien.cod_digi_ctrl%TYPE,
    val_nom1                mae_clien.val_nom1%TYPE,
    val_nom2                mae_clien.val_nom2%TYPE,
    val_ape1                mae_clien.val_ape1%TYPE,
    val_ape2                mae_clien.val_ape2%TYPE,
    num_docu_iden           mae_clien_ident.num_docu_iden%TYPE,
    oid_soli_cabe           ped_solic_cabec.oid_soli_cabe%TYPE,
    val_nume_soli           ped_solic_cabec.val_nume_soli%TYPE,
    fec_fact                ped_solic_cabec.fec_fact%TYPE,
    val_impo_flet_tota_loca ped_solic_cabec.val_impo_flet_tota_loca%TYPE,
    val_impo_impu_tota_loca ped_solic_cabec.val_impo_impu_tota_loca%TYPE,
    val_impo_redo_loca      ped_solic_cabec.val_impo_redo_loca%TYPE,
    des_pais                bas_pais.des_pais%TYPE,
    cod_zona                zon_zona.cod_zona%TYPE,
    cod_secc                zon_secci.cod_secc%TYPE,
    cod_terr                zon_terri.cod_terr%TYPE,
    num_secu_fact_diar      ped_solic_cabec_secue.num_secu_fact_diar%TYPE,
    saldoAnterior           ped_solic_posic.val_prec_cata_unit_loca%TYPE,
    saldoAFavor             ped_solic_posic.val_prec_cata_unit_loca%TYPE,
    gastos                  ped_solic_cabec.val_tota_gast_admi%TYPE,
    val_impo_rete_desc      ped_solic_cabec.val_impo_rete_desc%TYPE,
    tel_clie                 varchar2(100),
    gerente                 varchar2(100),
    cel_gerente                 varchar2(100),
    ejecutiva               varchar2(100),
    cel_ejecutiva               varchar2(100),
    numero_factura              varchar2(100),
    ind_impr_pdoc               varchar2(10)
);

TYPE consolidadotype IS TABLE OF consolidadorecord;
r_consolidado    consolidadotype;

CURSOR c_detalle(oidConsolidado NUMBER
,v_desPremioAgotado VARCHAR2
,v_desAgotado VARCHAR2
,v_desFaltLiq VARCHAR2
,v_desFaltAnun VARCHAR2
,v_desAnulMotMax VARCHAR2
,v_desPremio VARCHAR2
,v_desAtRec VARCHAR2
,v_desRecup VARCHAR2
,v_desReemp VARCHAR2
,v_desGratis VARCHAR2
,v_desPremioLET VARCHAR2
,v_desOfertNavid VARCHAR2
, v_oidestra  NUMBER
) IS
SELECT PSC.OID_SOLI_CABE,
       PSC.COPA_OID_PARA_GENE,
       PSP.OID_SOLI_POSI,
       nvl(NVL(PSP.VAL_CODI_VENT, LPAD('0', 4 - LENGTH(PSP.VAL_CODI_VENT_FICT), '0') || PSP.VAL_CODI_VENT_FICT),'00000') AS VAL_CODI_VENT,
       imp_pkg_proce_laser.imp_fn_desc_produ(p_codigoPais,psp.prod_oid_prod) DES_PROD, --modificado
       PSP.NUM_UNID_DEMA_REAL,
       PSP.NUM_UNID_ATEN,
       PSP.VAL_PREC_CATA_UNIT_LOCA,
       PSP.VAL_PREC_CATA_UNIT_LOCA * PSP.NUM_UNID_ATEN VAL_PREC_CATA_TOTA_LOCA,
       PSP.VAL_PREC_CONT_UNIT_LOCA,
       PSP.VAL_PREC_CONT_UNIT_LOCA * PSP.NUM_UNID_ATEN VAL_PREC_CONT_TOTA_LOCA,
       decode(psp.val_prec_cata_unit_loca,0,0,PSP.VAL_PREC_FACT_TOTA_LOCA) VAL_PREC_FACT_TOTA_LOCA,
       PSP.VAL_IMPO_DESC_TOTA_LOCA,
       decode(psp.num_unid_compr,0,0,decode(psp.val_prec_cata_unit_loca,0,0,NVL(PSP.VAL_PORC_DESC, 0))) VAL_PORC_DESC,
       PSP.VAL_IMPO_IMPU_TOTA_LOCA,
       case when psp.val_prec_cata_unit_loca=0 then psp.val_prec_cata_tota_loca
         when pto.num_secc_deta_fact=0 then psp.val_impo_desc_tota_loca
         when pto.num_secc_deta_fact=1 then psp.val_impo_desc_tota_loca--(nvl(pod.imp_prec_publ,0)*psp.num_unid_aten)-psp.val_prec_cata_tota_loca
         else psp.val_impo_desc_tota_loca
         end
         IMP_PREC_PUBL,
       psp.val_prec_sin_impu_tota_loca,
       psp.val_codi_orig,
       psp.num_unid_orig,
       psp.oid_nive_ofer,
       case
       when exists (select 1 from fac_tipo_ofert_exclu x
                 where x.tofe_oid_tipo_ofer=pod.tofe_oid_tipo_ofer) then (select lpad(cod_cata,2,'0') || des_cata from pre_catal where cod_cata='2')
       when nvl(mp.val_atri_3,0)=1 then '99CARGO POR USO FLEXIPAGO'
       when psp.val_prec_cata_unit_loca=0 then '97ADICIONALMENTE ESTA CAMPAÑA GANASTE'
       else  lpad(cat.cod_cata,2,'0') || cat.des_cata
       end des_cata,
       sum(PSP.NUM_UNID_DEMA_REAL) over (partition by               case
       when exists (select 1 from fac_tipo_ofert_exclu x
                 where x.tofe_oid_tipo_ofer=pod.tofe_oid_tipo_ofer) then (select lpad(cod_cata,2,'0') || des_cata from pre_catal where cod_cata='2')
       when nvl(mp.val_atri_3,0)=1 then '99CARGO POR USO FLEXIPAGO'
       when psp.val_prec_cata_unit_loca=0 then '97ADICIONALMENTE ESTA CAMPA?A GANASTE'
       else  lpad(cat.cod_cata,2,'0') || cat.des_cata
       end) UNID_SOLI_CATA,
       sum(PSP.NUM_UNID_DEMA_REAL) over () UNID_SOLI_TOTA,
       sum(PSP.NUM_UNID_ATEN) over (partition by               case
       when exists (select 1 from fac_tipo_ofert_exclu x
                 where x.tofe_oid_tipo_ofer=pod.tofe_oid_tipo_ofer) then (select lpad(cod_cata,2,'0') || des_cata from pre_catal where cod_cata='2')
       when nvl(mp.val_atri_3,0)=1 then '99CARGO POR USO FLEXIPAGO'
       when psp.val_prec_cata_unit_loca=0 then '97ADICIONALMENTE ESTA CAMPAÑA GANASTE'
       else  lpad(cat.cod_cata,2,'0') || cat.des_cata
       end) UNID_ATEN_CATA,
       sum(PSP.NUM_UNID_ATEN) over () UNID_ATEN_TOTA,
       sum(PSP.VAL_PREC_CATA_UNIT_LOCA * PSP.NUM_UNID_ATEN) over (partition by        case
       when exists (select 1 from fac_tipo_ofert_exclu x
                 where x.tofe_oid_tipo_ofer=pod.tofe_oid_tipo_ofer) then (select lpad(cod_cata,2,'0') || des_cata from pre_catal where cod_cata='2')
       when nvl(mp.val_atri_3,0)=1 then '99CARGO POR USO FLEXIPAGO'
       when psp.val_prec_cata_unit_loca=0 then '97ADICIONALMENTE ESTA CAMPAÑA GANASTE'
       else  lpad(cat.cod_cata,2,'0') || cat.des_cata
       end) PREC_CATA,
       sum(PSP.VAL_PREC_CATA_UNIT_LOCA * PSP.NUM_UNID_ATEN) over () PREC_TOTA,
       sum(decode(psp.val_prec_cata_unit_loca,0,0,PSP.VAL_PREC_FACT_TOTA_LOCA)) over (partition by        case
       when exists (select 1 from fac_tipo_ofert_exclu x
                 where x.tofe_oid_tipo_ofer=pod.tofe_oid_tipo_ofer) then (select lpad(cod_cata,2,'0') || des_cata from pre_catal where cod_cata='2')
       when nvl(mp.val_atri_3,0)=1 then '99CARGO POR USO FLEXIPAGO'
       when psp.val_prec_cata_unit_loca=0 then '97ADICIONALMENTE ESTA CAMPA?A GANASTE'
       else  lpad(cat.cod_cata,2,'0') || cat.des_cata
       end) TOTA_CATA,
       sum(decode(psp.val_prec_cata_unit_loca,0,0,PSP.VAL_PREC_FACT_TOTA_LOCA)) over () TOTA_TOTA,
       sum(       case when psp.val_prec_cata_unit_loca=0 then psp.val_prec_cata_tota_loca
         when pto.num_secc_deta_fact=0 then psp.val_impo_desc_tota_loca
         when pto.num_secc_deta_fact=1 then psp.val_impo_desc_tota_loca--(nvl(pod.imp_prec_publ,0)*psp.num_unid_aten)-psp.val_prec_cata_tota_loca
         else psp.val_impo_desc_tota_loca
         end) over (partition by        case
       when exists (select 1 from fac_tipo_ofert_exclu x
                 where x.tofe_oid_tipo_ofer=pod.tofe_oid_tipo_ofer) then (select lpad(cod_cata,2,'0') || des_cata from pre_catal where cod_cata='2')
       when nvl(mp.val_atri_3,0)=1 then '99CARGO POR USO FLEXIPAGO'
       when psp.val_prec_cata_unit_loca=0 then '97ADICIONALMENTE ESTA CAMPAÑA GANASTE'
       else  lpad(cat.cod_cata,2,'0') || cat.des_cata
       end) OPOR_CATA,
       sum(       case when psp.val_prec_cata_unit_loca=0 then psp.val_prec_cata_tota_loca
         when pto.num_secc_deta_fact=0 then psp.val_impo_desc_tota_loca
         when pto.num_secc_deta_fact=1 then psp.val_impo_desc_tota_loca--(nvl(pod.imp_prec_publ,0)*psp.num_unid_aten)-psp.val_prec_cata_tota_loca
         else psp.val_impo_desc_tota_loca
         end) over () OPOR_TOTA,
              CASE
           WHEN exists (select 1 from pre_ofert x where x.oid_ofer=pod.ofer_oid_ofer and x.coes_oid_estr=v_oidestra)
               THEN v_desOfertNavid
           WHEN (NVL(PSP.NUM_UNID_DEMA_REAL, 0) - NVL(PSP.NUM_UNID_ATEN, 0) > 0) AND (PSC.COPA_OID_PARA_GENE IS NOT NULL OR PSC.ICTP_OID_TIPO_PROG IS NOT NULL)
               THEN v_desPremioAgotado
           WHEN NVL(PSP.NUM_UNID_DEMA_REAL, 0) - NVL(PSP.NUM_UNID_ATEN, 0) > 0
               THEN v_desAgotado
           WHEN NVL(PSP.NUM_UNID_DEMA_REAL, 0) = 0 AND NVL(PSP.NUM_UNID_ATEN, 0) = 0
                AND PSP.IND_LIMI_VENT = 1 AND (PTO.COD_TIPO_OFER = '21' OR PTO.COD_TIPO_OFER = '23')
               THEN v_desFaltLiq
           WHEN NVL(PSP.NUM_UNID_DEMA_REAL, 0) = 0 AND NVL(PSP.NUM_UNID_ATEN, 0) = 0
                AND PSP.IND_LIMI_VENT = 1
               THEN v_desFaltAnun
           WHEN NVL(PSP.NUM_UNID_DEMA_REAL, 0) = 0 AND NVL(PSP.NUM_UNID_ATEN, 0) = 0
                AND PSP.STPO_OID_SUBT_POSI = 21
               THEN v_desAnulMotMax
          WHEN PST.COD_SUBT_POSI IS NOT NULL AND PST.COD_SUBT_POSI = 'RD'
               THEN v_desAgotado
           WHEN NVL(PSP.NUM_UNID_DEMA_REAL, 0) = 0 AND NVL(PSP.NUM_UNID_ATEN, 0) = 0
               THEN 'No Cumple'
           WHEN (PSC.COPA_OID_PARA_GENE IS NOT NULL OR PSC.ICTP_OID_TIPO_PROG IS NOT NULL)
               THEN v_desPremio
           WHEN PSC.MODU_OID_MODU = '15'
               THEN v_desAtRec
           WHEN PTP.COD_TIPO_POSI IS NOT NULL AND PTP.COD_TIPO_POSI = 'RE'
               THEN v_desRecup
           WHEN PTP.COD_TIPO_POSI IS NOT NULL AND PTP.COD_TIPO_POSI = 'DA'
               THEN v_desReemp  || (
                 SELECT distinct POF.Val_Codi_Vent
                 FROM PRE_MATRI_FACTU PMF,
                      PRE_MATRI_CODIG_ALTER PMR,
                      PRE_MATRI_FACTU PMF2,
                      PRE_OFERT_DETAL POF
                 WHERE PMF.OID_MATR_FACT = PMR.MAFA_OID_COD_ALTE
                 AND PMF.OFDE_OID_DETA_OFER = PSP.OFDE_OID_DETA_OFER
                 AND PMR.MAFA_OID_COD_PPAL=PMF2.Oid_Matr_Fact
                 AND PMF2.Ofde_Oid_Deta_Ofer=POF.Oid_Deta_Ofer
                 AND exists (select 1 from ped_solic_posic where soca_oid_soli_cabe=PSC.OID_SOLI_CABE
                              and ofde_oid_deta_ofer=PSP.OFDE_Oid_Deta_Ofer)
                 and rownum=1
                 )
           WHEN PST.COD_SUBT_POSI IS NOT NULL AND PST.COD_SUBT_POSI = 'RZ'
               THEN v_desReemp  || (
                 SELECT distinct POF.Val_Codi_Vent
                 FROM PRE_MATRI_FACTU PMF,
                      PRE_MATRI_REEMP PMR,
                      PRE_MATRI_FACTU PMF2,
                      PRE_OFERT_DETAL POF
                 WHERE PMF.OID_MATR_FACT = PMR.MAFA_OID_COD_REEM
                 AND PMF.OFDE_OID_DETA_OFER = PSP.OFDE_OID_DETA_OFER
                 AND PMR.MAFA_OID_COD_PPAL=PMF2.Oid_Matr_Fact
                 AND PMF2.Ofde_Oid_Deta_Ofer=POF.Oid_Deta_Ofer
                 AND exists (select 1 from ped_solic_posic where soca_oid_soli_cabe=PSC.OID_SOLI_CABE
                              and ofde_oid_deta_ofer=PSP.OFDE_Oid_Deta_Ofer)
                 and rownum=1
                 )
           WHEN PSP.VAL_PREC_CATA_UNIT_LOCA = 0
               THEN v_desGratis
           ELSE ''
        END AS VAL_OBSE,
        MP.COD_SAP,
        psp.num_unid_por_aten
FROM PED_SOLIC_CABEC PSC,
     PED_SOLIC_POSIC PSP,
     PRE_OFERT_DETAL POD,
     PED_SUBTI_POSIC PST,
     PED_TIPO_POSIC PTP,
     ped_tipo_solic_pais PTSP,
     ped_tipo_solic TS,
     PRE_TIPO_OFERT PTO,
     pre_catal CAT,
     mae_produ mp
WHERE PSC.OID_SOLI_CABE = PSP.SOCA_OID_SOLI_CABE
  AND PSP.OFDE_OID_DETA_OFER = POD.OID_DETA_OFER(+)
  and pod.tofe_oid_tipo_ofer=pto.oid_tipo_ofer(+)
  AND PSP.ESPO_OID_ESTA_POSI != 2
  and psp.prod_oid_prod=mp.oid_prod
  and psc.tspa_oid_tipo_soli_pais=ptsp.oid_tipo_soli_pais
  and ptsp.tsol_oid_tipo_soli=ts.oid_tipo_soli
  and psp.stpo_oid_subt_posi=pst.oid_subt_posi(+)
  and psp.tpos_oid_tipo_posi=ptp.oid_tipo_posi(+)
  and pod.ocat_oid_catal=cat.oid_cata(+)
  and psc.modu_oid_modu<>13
  AND PSC.SOCA_OID_SOLI_CABE = oidConsolidado
ORDER BY               case
       when exists (select 1 from fac_tipo_ofert_exclu x
                 where x.tofe_oid_tipo_ofer=pod.tofe_oid_tipo_ofer) then (select lpad(cod_cata,2,'0') || des_cata from pre_catal where cod_cata='2')
       when nvl(mp.val_atri_3,0)=1 then '99CARGO POR USO FLEXIPAGO'
       when psp.val_prec_cata_unit_loca=0 then '97ADICIONALMENTE ESTA CAMPAÑA GANASTE'
       else  lpad(cat.cod_cata,2,'0') || cat.des_cata
       end, psp.oid_nive_ofer, psp.val_codi_orig, psp.val_codi_vent;


TYPE detallerecord IS RECORD (
    oid_soli_cabe               ped_solic_cabec.oid_soli_cabe%TYPE,
    copa_oid_para_gene          ped_solic_cabec.copa_oid_para_gene%TYPE,
    oid_soli_posi               ped_solic_posic.oid_soli_posi%TYPE,
    val_codi_vent               varchar2(15),
    des_prod                    gen_i18n_sicc_pais.val_i18n%TYPE,
    num_unid_dema_real          ped_solic_posic.num_unid_dema_real%TYPE,
    num_unid_aten               ped_solic_posic.num_unid_aten%TYPE,
    val_prec_cata_unit_loca     ped_solic_posic.val_prec_cata_unit_loca%TYPE,
    val_prec_cata_tota_loca     ped_solic_posic.val_prec_cata_tota_loca%TYPE,
    val_prec_cont_unit_loca     ped_solic_posic.val_prec_cont_unit_loca%TYPE,
    val_prec_cont_tota_loca     ped_solic_posic.val_prec_cont_tota_loca%TYPE,
    val_prec_fact_tota_loca     ped_solic_posic.val_prec_fact_tota_loca%TYPE,
    val_impo_desc_tota_loca     ped_solic_posic.val_impo_desc_tota_loca%TYPE,
    val_porc_desc               ped_solic_posic.val_porc_desc%TYPE,
    val_impo_impu_tota_loca     ped_solic_posic.val_impo_impu_tota_loca%TYPE,
    imp_prec_publ               pre_ofert_detal.imp_prec_publ%TYPE,
    val_prec_sin_impu_tota_loca ped_solic_posic.val_prec_sin_impu_tota_loca%TYPE,
    val_codi_orig               ped_solic_posic.val_codi_orig%TYPE,
    num_unid_orig               ped_solic_posic.num_unid_orig%TYPE,
    oid_nive_ofer               ped_solic_posic.oid_nive_ofer%TYPE,
    des_cata                    pre_catal.des_cata%TYPE,
    unid_soli_cata              ped_solic_posic.num_unid_dema_real%TYPE,
    unid_soli_tota              ped_solic_posic.num_unid_dema_real%TYPE,
    unid_aten_cata              ped_solic_posic.num_unid_dema_real%TYPE,
    unid_aten_tota              ped_solic_posic.num_unid_dema_real%TYPE,
    prec_cata                   ped_solic_posic.val_prec_sin_impu_tota_loca%TYPE,
    prec_tota                   ped_solic_posic.val_prec_sin_impu_tota_loca%TYPE,
    tota_cata                   ped_solic_posic.val_prec_sin_impu_tota_loca%TYPE,
    tota_tota                   ped_solic_posic.val_prec_sin_impu_tota_loca%TYPE,
    opor_cata                   ped_solic_posic.val_prec_sin_impu_tota_loca%TYPE,
    opor_tota                   ped_solic_posic.val_prec_sin_impu_tota_loca%TYPE,
    val_obse                    VARCHAR2(500),
    COD_SAP                     MAE_PRODU.COD_SAP%TYPE,
    Num_Unid_Por_Aten           PED_SOLIC_POSIC.Num_Unid_Por_Aten%TYPE
);

TYPE detalletype IS TABLE OF detallerecord;
r_detalle    detalletype;


-- Variables locales
l_oidPais                   NUMBER;
l_oidPeriodo                NUMBER;
--l_oidCanal                  NUMBER;
--l_oidMarca                  NUMBER;
--l_correlativo               NUMBER := 1;
l_contadorDetalles          NUMBER := 0;


l_totalFactura              NUMBER(12, 2) := 0;
l_totalAhorro               NUMBER(12, 2) := 0;

l_cargoFamSegura            NUMBER(12, 2) := 0;
l_totalAPagar               NUMBER(12, 2) := 0;
l_saldoAnterior             NUMBER(12, 2) := 0;
cupon                       NUMBER(12, 2) := 0;
saldo_cupon                 NUMBER(12, 2) := 0;
l_totalPagoPosterior        NUMBER(12, 2) := 0;


l_catalogo_anterior         VARCHAR2(100);

l_indicadorEnvioLarissa     VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioLarissa');
l_indicadorEnvioUltimoLote  VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioUltimoLote');
l_indicadorCargoFamSegura   VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorCargoFamSegura'),'N');
l_indicadorImpuestosGratis  VARCHAR2(1) := 'N';
l_indicadorPercepcion       VARCHAR2(1) := 'N';
l_numeroLoteFacturacion     NUMBER;
--l_clob                      CLOB;
--l_textoActual               VARCHAR2(20000) := '';
l_codigoPeriodoSiguiente    VARCHAR2(6);
l_cambioLineaRetornoCarro   VARCHAR2(2) := CHR(13) || CHR(10);
l_formatoNumerico           VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'formatoNumerico'),'999,999,999');
l_tasaImpuestoPercepcion    NUMBER(5, 3);
--nuevo parametro

l_mensajeOportunidadAhorro VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'mensajeOportunidadAhorro');

l_fechaVencimiento VARCHAR2(100) := '';
l_fechaCV VARCHAR2(100) := '';
l_fechaDespacho VARCHAR2(100) := '';
l_fechaDespacho2 VARCHAR2(100) := '';


l_cantidadOC      NUMBER:=0;

lv_indiejec VARCHAR2(10):=nvl(sto_pkg_gener.sto_fn_obten_param_ocr(p_codigoPais,'STO_GASTO_ADMIN'),'N');

lv_reemplazoOCS VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorReemplazoOCS');

lv_actividadConf VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','actividadConf'),'CV');

lv_actividadVenc VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','actividadVenc'),'CP');

lv_actividadDesp VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','actividadDesp'),'FA');

lv_imprimepaqdoc VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','imprimepaqdoc'),'N');


l_desRecupSemana VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desRecupSemana');
l_desPremioAgotado VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desPremioAgotado');
l_desAgotado VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desAgotado');
l_desFaltLiq VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desFaltLiq');
l_desFaltAnun VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desFaltAnun');
l_desAnulMotMax VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desAnulMotMax');
l_desPremio VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desPremio');
l_desAtRec VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desAtRec');
l_desRecup VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desRecup');
l_desReemp VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desReemp');
l_desGratis VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desGratis');
l_desPremioLET VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desPremioLET');
l_desOfertNavid VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desOfertNavid');

ln_oidestra NUMBER(10):= sto_pkg_gener.sto_fn_obten_param_ocr(p_codigoPais,'STO_ESTRA_NAVID');

--ln_diasdesp NUMBER(10):= 0;
ln_diasdesp2 NUMBER(10):= 0;
ln_diasdesp3 NUMBER(10):= 0;


--l_fechaDespacho VARCHAR2(100) := '';
l_fechaDespacho_2 VARCHAR2(100) := '';
l_fechaDespacho_3 VARCHAR2(100) := '';

--l_fechaDespacho2 VARCHAR2(100) :=  nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indFechaDesp2'),'N');

--l_fechaSigFact VARCHAR2(100) := '';
l_fechaSigFact2 VARCHAR2(100) := '';
l_fechaSigFact3 VARCHAR2(100) := '';

orig_ante VARCHAR2(100):='';

lsCodigoVenta varchar2(15);
lsUnidadesDemandadas number(12);
lsUnidadesAtendidas number(6);
lsObservacion VARCHAR2(1000);
lsOidCabecera number(12);

lsTotalCatalogo             NUMBER(12, 2) := 0;
lsTotalDescuentos           NUMBER(12, 2) := 0;
lsTotalFacturado            NUMBER(12, 2) := 0;
lsTotalFlete                NUMBER(12, 2) := 0;
lsTotalPercepciones         NUMBER(12, 2) := 0;
lsTotalFactura              NUMBER(12, 2) := 0;
lsPagosPosteriores          NUMBER(12, 2) := 0;
lsAbonoAtencionServicios    NUMBER(12, 2) := 0;
lsSaldoAnterior             NUMBER(12, 2) := 0;
lsImporteActual             NUMBER(12, 2) := 0;
lsOportunidadCatalogo       NUMBER(12, 2) := 0;
lsOportunidadRevista        NUMBER(12, 2) := 0;
lsOportunidadTotal          NUMBER(12, 2) := 0;
lsMensaje                   VARCHAR2(60);
lsFechaVencimiento          VARCHAR2(60);
lsGastosAdministrativos     NUMBER(12, 2) := 0;
lsSaldoFlexipago            NUMBER(12, 2) := 0;
lsCuotaFlexipago            NUMBER(12, 2) := 0;
lsRecargoFlete              NUMBER(12, 2) := 0;
lsDescuentoCabecera         NUMBER(12, 2) := 0;
lsCampanaGastosAdministrativos         VARCHAR2(10);
lsDescuentoCabecera2         NUMBER(12, 2) := 0;
lsReclamosPendientes         NUMBER(12, 2) := 0;

lsImpuesto                   NUMBER(12, 2) := 0;
lsSubTotalFlete              NUMBER(12, 2) := 0;
lsSubTotalFleteImpuesto      NUMBER(12, 2) := 0;
lsDescuentoFlete             NUMBER(12, 2) := 0;
lsTotalImpuestosGratis       NUMBER(12, 2) := 0;
lsDescripcion                varchar2(70);
lsImpuestoProductosGratis    NUMBER(12, 2) := 0;
lsDescuentoTotal             NUMBER(12, 2) := 0;
lsRetencion                  NUMBER(12, 2) := 0;
lsFechaCV                    NUMBER(12, 2) := 0;
lsFechaV1                    varchar2(100);

l_precioUnitario             NUMBER(12, 2) := 0;
l_precioTotal                NUMBER(12, 2) := 0;
l_porcDscto                  NUMBER;
l_PrecioCatalogoTotal        NUMBER(12, 2) := 0;
l_oportunidadAhorro          NUMBER(12, 2) := 0;
lsSubTotalFactura            NUMBER(12, 2) := 0;
lsSaldoCupon                 NUMBER(12, 2) := 0;
lsInteresMora                NUMBER(12, 2) := 0;

lsValorCata1                 varchar2(60):='';
lsMontoCata1                 NUMBER(12, 2) := 0;

lsValorCata2                 varchar2(60):='';
lsMontoCata2                 NUMBER(12, 2) := 0;

lsValorCata3                 varchar2(60):='';
lsMontoCata3                 NUMBER(12, 2) := 0;

BEGIN



    -- Obtenemos el OID del periodo
    l_oidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(p_codigoPais);
    --l_oidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(CODIGO_CANAL);
    --l_oidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(CODIGO_MARCA);
    l_oidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(p_codigoPeriodo, 2003, 2001);
    l_codigoPeriodoSiguiente := GEN_FN_CALCU_PERIO(p_codigoPeriodo, 1);

    --SOLO SE JECUTARA EL PROCESO SI PARAMETRO ES S, EN OTRO CASO NO SE EJECUTARA
    IF(l_indicadorEnvioUltimoLote = '1' OR l_indicadorEnvioUltimoLote = 'S') THEN

        BEGIN
            select max(cons.num_lote_fact)
            into l_numeroLoteFacturacion
            from ped_solic_cabec cons,
                 int_lar_tipo_solici_pedido_dis tspd
            where cons.perd_oid_peri = l_oidPeriodo
             and  cons.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
             and  cons.ind_ts_no_conso = 0
             and  (cons.ind_pedi_prue = 0 or cons.ind_pedi_prue is null)
             and  cons.tspa_oid_tipo_soli_pais = tspd.tspa_oid_tipo_soli_pais
             and  cons.pais_oid_pais = l_oidPais;
        EXCEPTION
          WHEN OTHERS THEN
              l_numeroLoteFacturacion := null;
        END;

    END IF;



    --EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_PAQUE_DOCUM_DETAL_FACTU';
    --delete from IMP_PAQUE_DOCUM_DETAL_FACTU;

    -- Abrimos el cursor principal
    OPEN c_consolidados(l_oidPeriodo, l_indicadorEnvioLarissa, l_numeroLoteFacturacion/*, p_oidZona*/);
    LOOP
        FETCH c_consolidados BULK COLLECT
        INTO r_consolidado LIMIT 10000; --modificado

        IF  r_consolidado.COUNT > 0 THEN
            FOR i IN r_consolidado.FIRST..r_consolidado.LAST
            LOOP

                lsOidCabecera:= IMP_PRPR_SEQ.Nextval;

                -- Insertamos el registro y obtenemos la referencia al CLOB
                /*INSERT INTO IMP_PAQUE_DOCUM_DETAL_FACTU (
                COR_PADO_DEFA,
                COD_CONS,
                VAL_NUME_SOLI,
                XML_DETA_FACT)
                VALUES(
                r_consolidado(i).oid_soli_cabe,
                r_consolidado(i).cod_clie,
                r_consolidado(i).val_nume_soli,
                EMPTY_CLOB())
                RETURNING XML_DETA_FACT INTO l_clob;*/


               BEGIN

                SELECT to_char(cc.FEC_INIC,'dd/mm/yyyy')
                INTO l_fechaVencimiento
                FROM cra_crono cc, CRA_PERIO CP, seg_perio_corpo spc, CRA_ACTIV act
                WHERE cc.PERD_OID_PERI = cp.oid_peri
                  AND cp.peri_oid_peri = spc.oid_peri
                  AND spc.cod_peri = l_codigoPeriodoSiguiente
                  AND cc.ZZON_OID_ZONA = (select oid_zona from zon_zona where cod_zona=r_consolidado(i).cod_zona)

                  AND cc.CACT_OID_ACTI = act.OID_ACTI
                  AND act.COD_ACTI = lv_actividadVenc;

               EXCEPTION
                WHEN OTHERS THEN
                 l_fechaVencimiento:=to_char(sysdate,'dd/mm/yyyy');
               END;

               BEGIN

                --<DEMORA> ESTA FUNCION fac_pkg_proc.ped_fn_obt_dias_fecha_ent demora bastante EN PROD
                SELECT to_char(cc.fec_inic,'dd/mm/yyyy'), to_char(cc.FEC_INIC+fac_pkg_proc.ped_fn_obt_dias_fecha_ent(r_consolidado(i).oid_soli_cabe,1,l_codigoPeriodoSiguiente, cc.FEC_INIC),'dd/mm/yyyy')
                INTO l_fechaDespacho2, l_fechaDespacho
                FROM cra_crono cc, CRA_PERIO CP, seg_perio_corpo spc, CRA_ACTIV act
                WHERE cc.PERD_OID_PERI = cp.oid_peri
                  AND cp.peri_oid_peri = spc.oid_peri
                  AND spc.cod_peri = l_codigoPeriodoSiguiente
                  AND cc.ZZON_OID_ZONA = (select oid_zona from zon_zona where cod_zona=r_consolidado(i).cod_zona)

                  AND cc.CACT_OID_ACTI = act.OID_ACTI
                  AND act.COD_ACTI = lv_actividadDesp;

                  l_fechaSigFact2:=to_char(fac_pkg_proc.ped_fn_dev_dia_fact(l_codigoPeriodoSiguiente,r_consolidado(i).cod_zona,2),'dd/mm/yyyy');

                  l_fechaSigFact3:=to_char(fac_pkg_proc.ped_fn_dev_dia_fact(l_codigoPeriodoSiguiente,r_consolidado(i).cod_zona,3),'dd/mm/yyyy');

                  ln_diasdesp2:=fac_pkg_proc.ped_fn_obt_dias_fecha_ent(r_consolidado(i).oid_soli_cabe,2,l_codigoPeriodoSiguiente, to_date(l_fechaDespacho2,'dd/mm/yyyy'));
                  ln_diasdesp3:=fac_pkg_proc.ped_fn_obt_dias_fecha_ent(r_consolidado(i).oid_soli_cabe,3,l_codigoPeriodoSiguiente, to_date(l_fechaDespacho2,'dd/mm/yyyy'));




                  --if l_fechaDespacho2='S' and ln_diasdesp>0 then
                    l_fechaDespacho_2:=to_char(to_date(l_fechaSigFact2,'dd/mm/yyyy')+ln_diasdesp2,'dd/mm/yyyy');
                  --end if;

                  --if l_fechaDespacho2='S' and ln_diasdesp>0 then
                    l_fechaDespacho_3:=to_char(to_date(l_fechaSigFact3,'dd/mm/yyyy')+ln_diasdesp3,'dd/mm/yyyy');
                  --end if;


               update ped_segui_pedid a
               set a.fec_sigu_entr=to_date(l_fechaDespacho,'dd/mm/yyyy'),
               a.fec_sigu_fact=to_date(l_fechaDespacho2 ,'dd/mm/yyyy')
               where a.soca_oid_soli_cabe=r_consolidado(i).oid_soli_cabe;

               EXCEPTION
                WHEN OTHERS THEN
                 l_fechaDespacho:=to_char(sysdate,'dd/mm/yyyy');
                 l_fechaDespacho2:=to_char(sysdate,'dd/mm/yyyy');
                 l_fechaSigFact2:=to_char(sysdate,'dd/mm/yyyy');
                 l_fechaSigFact3:=to_char(sysdate,'dd/mm/yyyy');
                 l_fechaDespacho_2:=to_char(sysdate,'dd/mm/yyyy');
                 l_fechaDespacho_3:=to_char(sysdate,'dd/mm/yyyy');
               END;


               BEGIN

                SELECT to_char(cc.FEC_INIC,'dd/mm/yyyy')
                INTO l_fechaCV
                FROM cra_crono cc, CRA_PERIO CP, seg_perio_corpo spc, CRA_ACTIV act
                WHERE cc.PERD_OID_PERI = cp.oid_peri
                  AND cp.peri_oid_peri = spc.oid_peri
                  AND spc.cod_peri = l_codigoPeriodoSiguiente
                  AND cc.ZZON_OID_ZONA = (select oid_zona from zon_zona where cod_zona=r_consolidado(i).cod_zona)
                  AND cc.CACT_OID_ACTI = act.OID_ACTI
                  AND act.COD_ACTI = lv_actividadConf;


               EXCEPTION
                WHEN OTHERS THEN
                 l_fechaCV:=to_char(sysdate,'dd/mm/yyyy');
               END;

                -- Inicio Detalle de Factura
                --l_textoActual := '<detfac4>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                --l_textoActual := l_textoActual || IMP_PKG_PROCE_COMPA.IMP_FN_ETIQU_CLASI_BOLET_DESPA( r_consolidado(i).cod_clie) ||
                --IMP_PKG_PROCE_COMPA.IMP_FN_ETIQU_ESTAT_BOLET_DESPA( r_consolidado(i).cod_clie);

                -- Inicio Cabecera
                --l_textoActual := l_textoActual || '<blqcab>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- Numero Pedido
                --l_textoActual := l_textoActual || '<numpedido>' || r_consolidado(i).val_nume_soli || '</numpedido>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- Descripcion Pais
                --l_textoActual := l_textoActual || '<lugar>' || r_consolidado(i).des_pais || '</lugar>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- Dia
                --l_textoActual := l_textoActual || '<dia>' || to_char(r_consolidado(i).fec_fact, 'dd') || '</dia>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- Mes
                --l_textoActual := l_textoActual || '<mes>' || to_char(r_consolidado(i).fec_fact, 'mm') || '</mes>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- A?o
                --l_textoActual := l_textoActual || '<ano>' || to_char(r_consolidado(i).fec_fact, 'yyyy') || '</ano>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- Codigo Cliente
                --l_textoActual := l_textoActual || '<codconsultora>' || r_consolidado(i).cod_clie || '</codconsultora>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- Territorio Cliente
                --l_textoActual := l_textoActual || '<territorio>';
                --l_textoActual := l_textoActual || substr(r_consolidado(i).cod_zona,1,2);
                --l_textoActual := l_textoActual || r_consolidado(i).cod_zona;
                --l_textoActual := l_textoActual || r_consolidado(i).cod_secc;
                --l_textoActual := l_textoActual || r_consolidado(i).cod_terr || '-';
                --l_textoActual := l_textoActual || r_consolidado(i).num_secu_fact_diar;
                --l_textoActual := l_textoActual || '</territorio>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- Documento de Identidad
                --l_textoActual := l_textoActual || '<rifci>' || r_consolidado(i).num_docu_iden || '</rifci>';


                -- Documento de Identidad
                --l_textoActual := l_textoActual || '<tel_con>' || r_consolidado(i).tel_clie || '</tel_con>';

                -- Nombre Cliente
                --l_textoActual := l_textoActual || '<nombre>';
                --l_textoActual := l_textoActual || r_consolidado(i).val_nom1 || ' ';
                --l_textoActual := l_textoActual || r_consolidado(i).val_nom2 || ' ';
                --l_textoActual := l_textoActual || r_consolidado(i).val_ape1 || ' ';
                --l_textoActual := l_textoActual || r_consolidado(i).val_ape2;
                --l_textoActual := l_textoActual || '</nombre>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- Periodo
                --l_textoActual := l_textoActual || '<campana>' || p_codigoPeriodo || '</campana>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                -- Fecha Entrega
                --l_textoActual := l_textoActual || '<fecha_entrega>' || trim(l_fechaDespacho) || '</fecha_entrega>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                -- Fecha pago
                --l_textoActual := l_textoActual || '<fecha_pago>' || trim(l_fechaVencimiento) || '</fecha_pago>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                -- Fecha Conf
                --l_textoActual := l_textoActual || '<fecha_conf>' || trim(l_fechaCV) || '</fecha_conf>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);


                 /* l_textoActual := l_textoActual || '<fechaReparto>' || l_fechaDespacho || '</fechaReparto>';
                  l_textoActual := l_textoActual || '<fechaReparto2>' || l_fechaDespacho_2 || '</fechaReparto2>';
                  l_textoActual := l_textoActual || '<fechaReparto3>' || l_fechaDespacho_3 || '</fechaReparto3>';
                  l_textoActual := l_textoActual || '<fechaSigFact>' || l_fechaDespacho2 || '</fechaSigFact>';
                  l_textoActual := l_textoActual || '<fechaSigFact2>' || l_fechaSigFact2 || '</fechaSigFact2>';
                  l_textoActual := l_textoActual || '<fechaSigFact3>' || l_fechaSigFact3 || '</fechaSigFact3>';
                  l_textoActual := l_textoActual || '<diasDesp>' || fac_pkg_proc.ped_fn_obt_dias_fecha_ent(r_consolidado(i).oid_soli_cabe,1,l_codigoPeriodoSiguiente, to_date(l_fechaDespacho2,'dd/mm/yyyy')) || '</diasDesp>';
                  */
                -- Nombre Gerente
                --l_textoActual := l_textoActual || '<nom_geren>' || r_consolidado(i).gerente || '</nom_geren>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                -- Celular Gerente
                --l_textoActual := l_textoActual || '<cel_geren>' || r_consolidado(i).cel_gerente || '</cel_geren>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                -- Nombre Ejecutiva
                --l_textoActual := l_textoActual || '<nom_ejec>' || r_consolidado(i).ejecutiva || '</nom_ejec>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                -- Celular Ejecutiva
                --l_textoActual := l_textoActual || '<cel_ejec>' || r_consolidado(i).cel_ejecutiva || '</cel_ejec>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                -- Numero de Factura
                --l_textoActual := l_textoActual || '<num_fac>' || r_consolidado(i).numero_factura || '</num_fac>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);


                /*if lv_imprimepaqdoc='S' then
                  -- Ind. Impresion
                  l_textoActual := l_textoActual || '<imprimepaqdoc>' || r_consolidado(i).ind_impr_pdoc || '</imprimepaqdoc>';

                  --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                end if;*/


                -- Fin Cabecera
                --l_textoActual := l_textoActual || '</blqcab>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);


                -- Inicio Detalle
                --l_textoActual := l_textoActual || '<cuerpo>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);


                /*INSERTAMOS LA CABECERA*/
                INSERT INTO IMP_PRINT_PEDID_RESUM
                    (
                      PRSP_COD_PROC,
                      PRSP_COD_CLIE,
                      PRSP_COD_PAQU,
                      OID_PEDI_RESU,
                      USU_CREA,
                      FEC_CREA,
                      IND_ACTI
                    )VALUES
                    (
                      psCodigoTransaccional,
                      r_consolidado(i).cod_clie,
                      psCodigoPaquete,
                      lsOidCabecera,
                      psCodigoUsuario,
                      SYSDATE,
                      1
                    );

                -- Iteramos por las secciones del detalle
                    OPEN c_detalle(r_consolidado(i).oid_soli_cabe
                      ,l_desPremioAgotado
                      ,l_desAgotado
                      ,l_desFaltLiq
                      ,l_desFaltAnun
                      ,l_desAnulMotMax
                      ,l_desPremio
                      ,l_desAtRec
                      ,l_desRecup
                      ,l_desReemp
                      ,l_desGratis
                      ,l_desPremioLET
                      ,l_desOfertNavid
                      ,ln_oidestra
                    );
                    LOOP
                        FETCH c_detalle BULK COLLECT
                        INTO r_detalle LIMIT 1000;


                        IF  r_detalle.COUNT > 0 THEN
                            FOR j IN r_detalle.FIRST..r_detalle.LAST
                            LOOP

                               /* if nvl(l_catalogo_anterior,'X')<>r_detalle(j).des_cata and j<>r_detalle.FIRST then
                                -- Cerramos el cursor de detalles
                                   --l_textoActual := '</catalogo>';
                                   --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                end if;*/
                                /*
                                if nvl(l_catalogo_anterior,'X')<>r_detalle(j).des_cata then
                                       /*l_textoActual := '<catalogo>';
                                       --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                       l_textoActual := l_textoActual || '<nombre>' || substr(r_detalle(j).des_cata,3) || '</nombre>';
                                       --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                       l_textoActual := l_textoActual || '<total_solic>' || r_detalle(j).UNID_SOLI_CATA || '</total_solic>';
                                       --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                       l_textoActual := l_textoActual || '<total_aten>' || r_detalle(j).UNID_ATEN_CATA || '</total_aten>';
                                       --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                       l_textoActual := l_textoActual || '<total_total_cata>' || r_detalle(j).PREC_CATA || '</total_total_cata>';
                                       --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                       l_textoActual := l_textoActual || '<total_valor_pagar>' || r_detalle(j).TOTA_CATA || '</total_valor_pagar>';
                                       --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                       l_textoActual := l_textoActual || '<total_oport_ahorro>' || r_detalle(j).OPOR_CATA || '</total_oport_ahorro>';
                                       --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                       *-/
                                end if;
                                */

                               if (orig_ante is null and r_detalle(j).val_codi_orig is not null) or (orig_ante is not null and orig_ante<>r_detalle(j).val_codi_orig and r_detalle(j).val_codi_orig is not null) then

                                 --l_textoActual := '<detalle>';
                                 --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                --l_textoActual := l_textoActual || '<tiporeg>H</tiporeg>';

                                -- Codigo de Venta
                                lsCodigoVenta :=  r_detalle(j).val_codi_orig;

                                -- Descripcion
                                lsDescripcion := SUBSTR(r_detalle(j).des_prod,1,70);

                                -- Unidades demandadas
                                lsUnidadesDemandadas := r_detalle(j).num_unid_orig;

                                -- Unidades atendidas
                                lsUnidadesAtendidas := 0;

                                -- Precio unitario
                                l_precioUnitario:= 0;

                                -- Precio total
                                l_precioTotal:= 0;

                                -- % Descuento
                                l_porcDscto := 0;

                                -- Total Precio Catalogo
                                l_precioCatalogoTotal := 0;

                                -- Oportunidad de Ahorro
                                l_oportunidadAhorro := 0;

                                -- Observaciones
                                lsObservacion := '';


                                end if;

                                 --l_textoActual := '<detalle>';
                                 --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                /*if r_detalle(j).val_codi_orig is not null then
                                   l_textoActual := l_textoActual || '<tiporeg>D</tiporeg>';
                                end if;*/


                                -- Codigo de Venta
                                lsCodigoVenta := r_detalle(j).val_codi_vent;

                                -- Descripcion
                                lsDescripcion := SUBSTR(r_detalle(j).des_prod,1,70);

                                -- Unidades demandadas
                                lsUnidadesDemandadas := r_detalle(j).num_unid_dema_real;

                                -- Unidades atendidas
                                lsUnidadesAtendidas :=  r_detalle(j).num_unid_aten;

                                -- Precio unitario
                                l_precioUnitario := r_detalle(j).val_prec_cata_unit_loca;

                                -- Precio total
                                l_precioTotal:= r_detalle(j).val_prec_cata_tota_loca;

                                -- % Descuento
                                l_porcDscto := r_detalle(j).val_porc_desc;

                                -- Total Precio Catalogo
                                l_precioCatalogoTotal := r_detalle(j).val_prec_fact_tota_loca;


                                -- Oportunidad de Ahorro
                                l_oportunidadAhorro := nvl(r_detalle(j).imp_prec_publ,0);

                                -- Observaciones
                                lsObservacion := r_detalle(j).val_obse;


                                 l_catalogo_anterior:=r_detalle(j).des_cata;
                                 l_totalFactura:=r_detalle(j).prec_tota;
                                 l_totalAhorro:=r_detalle(j).opor_tota;

                                 orig_ante:=r_detalle(j).val_codi_orig;

                                 /*para oportunidad de ahorro*/
                                 if j = 1 then
                                    lsValorCata1:=r_detalle(j).des_cata;
                                    lsMontoCata1:= r_detalle(j).opor_tota;
                                 elsif j = 2 then
                                    lsValorCata2:=r_detalle(j).des_cata;
                                    lsMontoCata2:= r_detalle(j).opor_tota;
                                 elsif j = 3 then
                                    lsValorCata3:=r_detalle(j).des_cata;
                                    lsMontoCata3:= r_detalle(j).opor_tota;
                                 end if;

                                  /**/
                            INSERT INTO IMP_PRINT_PEDID_DETAL
                            (
                              PRSP_COD_PROC,
                              PRSP_COD_CLIE,
                              PRSP_COD_PAQU,
                              OID_PEDI_RESU,
                              OID_PEDI_DETAL,
                              NUM_ORDE,
                              TIP_FILA,
                              DES_FILA,
                              COD_CUV,
                              COD_SAP,
                              NOM_PROD,
                              VAL_UNID_SOLI,
                              VAL_UNID_ATEN,
                              VAL_UNID_FALT,
                              MON_PREC_UNIT,
                              MON_PREC_UNIT_SIVA,
                              MON_TOTA_ATEN,
                              MON_PORC_DCTO,
                              --MON_TOTA_DCTO,
                              --MON_PREC_CATA,
                              MON_TOTA_CATA,
                              MON_OPOR_AHOR,
                              --MON_TOTA_FACT,
                              VAL_OBSE,
                              VAL_PUNT,
                              USU_CREA,
                              FEC_CREA,
                              IND_ACTI
                            ) VALUES
                            (
                              psCodigoTransaccional,
                              r_consolidado(i).cod_clie,
                              psCodigoPaquete,
                              lsOidCabecera,
                              IMP_PRPD_SEQ.NEXTVAL,
                              NULL,
                              NULL,
                              NULL,
                              lsCodigoVenta,
                              r_detalle(j).cod_sap,
                              lsDescripcion,
                              nvl(lsUnidadesDemandadas,0),
                              nvl(lsUnidadesAtendidas,0),
                              nvl((nvl(lsUnidadesDemandadas,0) - nvl(lsUnidadesAtendidas,0)),0),
                              l_precioUnitario,
                              NULL,
                              l_precioTotal,
                              l_porcDscto,
                              --l_descuento,
                              --l_precioCatalogo,
                              l_precioCatalogoTotal,
                              l_oportunidadAhorro,
                              --l_precioFacturado,
                              SUBSTR(lsObservacion, 0, 60),
                              NULL,
                              psCodigoUsuario,
                              sysdate,
                              1);
                            /**/

                            END LOOP;
                        END IF;



                        EXIT WHEN c_detalle%NOTFOUND;
                END LOOP;
                    CLOSE c_detalle;

                    --l_textoActual := '</catalogo>';
                    --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                --l_textoActual := l_textoActual || '</cuerpo>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- Inicio Pie
                --l_textoActual := l_textoActual || '<pie>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);




                --l_totalFactura := l_totalCatalogo - l_totalDescuentos + r_consolidado(i).val_impo_flet_tota_loca + l_percepcion+nvl(r_consolidado(i).val_impo_rete_desc,0);


                -- Obtenemos los Pagos Posteriores
                /*SELECT NVL(SUM(imp_pend),0)
                INTO l_totalPagoPosterior
                FROM ccc_movim_cuent_corri
                WHERE clie_oid_clie = r_consolidado(i).oid_clie
                  AND imp_pend > 0
                  AND perd_oid_peri > l_oidPeriodo ;*/


                select count(1) into l_cantidadOC from ped_solic_cabec
                where clie_oid_clie=r_consolidado(i).oid_clie
                and perd_oid_peri=l_oidPeriodo
                and ind_oc=1
                and ind_ts_no_conso=1
                and fec_fact is not null
                and tspa_oid_tipo_soli_pais in
                (
                select oid_tipo_soli_pais from ped_tipo_solic_pais x, ped_tipo_solic y
                where x.tsol_oid_tipo_soli=y.oid_tipo_soli
                and y.ind_soli_nega=0 and y.ind_cons=0
                )
                ;



                -- Obtenemos el Cargos por Familia Segura
                IF l_indicadorCargoFamSegura='S' and l_cantidadOC<2 THEN

                 SELECT NVL(SUM(mcc.imp_movi),0)
                 INTO l_cargoFamSegura
                 FROM
                  ccc_movim_cuent_corri mcc,
                  ccc_proce cp,
                  ccc_subpr su
                 WHERE mcc.clie_oid_clie = r_consolidado(i).oid_clie
                   AND mcc.perd_oid_peri = l_oidPeriodo
                   AND mcc.subp_oid_subp_crea = su.Oid_Subp
                   AND su.ccpr_Oid_Proc = cp.oid_proc
                   AND cp.cod_proc = 'CCC007'
                   AND su.cod_subp = 7;
                else
                    l_cargoFamSegura:=0;

                END IF;


                /********************************************************************
                Total Factura - Pagos Posteriores + Cargo Familia Segura
                + Saldo Anterior = Total Monto a Pagar
                **********************************************************************/
                --<DEMORA> ESTA FUNCION CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_CAMPA_ANTER demora bastante EN PROD
                l_totalAPagar := CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_CAMPA_ANTER(r_consolidado(i).oid_clie,l_codigoPeriodoSiguiente);

                BEGIN
                  SELECT SUM(ccc_detal_cupon_trami_depur.imp_deta)
                    INTO cupon
                    FROM mae_clien,
                         ccc_detal_cupon_trami_depur,
                         ccc_situa_cupon
                   WHERE ((ccc_situa_cupon.oid_situ_cupo = ccc_detal_cupon_trami_depur.sicu_oid_situ_cupo) AND
                         (ccc_situa_cupon.cod_situ_cupo = 'T') AND
                         (mae_clien.oid_clie = ccc_detal_cupon_trami_depur.clie_oid_clie) AND
                         (mae_clien.oid_clie = r_consolidado(i).oid_clie));
                  IF cupon IS NULL THEN
                    cupon := 0;
                  END IF;
                EXCEPTION
                  WHEN no_data_found THEN
                    cupon := 0;
                END;

                l_totalAPagar:=l_totalAPagar-cupon;

                saldo_cupon:=l_totalAPagar;

                saldo_cupon:=trunc(saldo_cupon/100)*100;


                if saldo_cupon<0 then
                   saldo_cupon:=0;
                end if;

               update ped_segui_pedid a
               set a.val_mont_cupo=saldo_cupon
               where a.soca_oid_soli_cabe=r_consolidado(i).oid_soli_cabe;

                /*IF lv_indiejec='S' THEN
                 -- Gastos Administrativos
                 --l_saldoAnterior:=l_saldoAnterior-nvl(r_consolidado(i).gastos,0);
                 l_totalFactura:=l_totalFactura+nvl(r_consolidado(i).gastos,0);
                END IF;*/

                -- Obtenemos los Pagos Posteriores
                SELECT NVL(SUM(imp_pend),0)
                INTO l_totalPagoPosterior
                FROM ccc_movim_cuent_corri
                WHERE clie_oid_clie = r_consolidado(i).oid_clie
                  AND imp_pend > 0
                  AND perd_oid_peri > l_oidPeriodo ;

                l_saldoAnterior := l_totalAPagar - (l_totalFactura-l_totalAhorro+r_consolidado(i).val_impo_flet_tota_loca+l_cargoFamSegura+nvl(r_consolidado(i).gastos,0));



                    -- Total Catalogo
                    --l_textoActual := l_textoActual || '<subtotal>' || trim(to_char(l_totalFactura, l_formatoNumerico)) || '</subtotal>';
                    lsTotalCatalogo:= l_totalFactura;

                    -- Total Descuentos (Incluimos el redondeo)
                    --l_textoActual := l_textoActual || '<descuentos>' || trim(to_char(l_totalAhorro, l_formatoNumerico)) || '</descuentos>';
                    lsTotalDescuentos:= l_totalAhorro;

                    -- Total Descuentos (Incluimos el redondeo)
                    --l_textoActual := l_textoActual || '<subtotalfac>' || trim(to_char(l_totalFactura-l_totalAhorro, l_formatoNumerico)) || '</subtotalfac>';
                    lsSubTotalFactura:= l_totalFactura-l_totalAhorro;

                    -- Flete
                    --l_textoActual := l_textoActual || '<fletes>' || trim(to_char(r_consolidado(i).val_impo_flet_tota_loca, l_formatoNumerico)) || '</fletes>';
                    lsTotalFlete:= r_consolidado(i).val_impo_flet_tota_loca;

                    -- Abono Atencion de Servicios (No se visualiza)
                    --l_textoActual := l_textoActual || '<familia>' || trim(to_char(l_cargoFamSegura, l_formatoNumerico)) || '</familia>';
                    lsAbonoAtencionServicios:= l_cargoFamSegura;

                    -- Impuesto
                    --l_textoActual := l_textoActual || '<iva>' || trim(to_char(r_consolidado(i).val_impo_impu_tota_loca, l_formatoNumerico)) || '</iva>';
                    --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                    -- Total a pagar
                    --l_textoActual := l_textoActual || '<total_pagar>' || trim(to_char(l_totalFactura-l_totalAhorro+r_consolidado(i).val_impo_flet_tota_loca+l_cargoFamSegura+nvl(r_consolidado(i).gastos,0), l_formatoNumerico)) || '</total_pagar>';
                    --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                    -- Total Ahorro
                    --l_textoActual := l_textoActual || '<total_oport_ahorro>' || trim(to_char(l_totalAhorro, l_formatoNumerico)) || '</total_oport_ahorro>';
                    --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                    -- Saldo Anterior
                    --l_textoActual := l_textoActual || '<saldo_anterior>' || trim(to_char(l_saldoAnterior, l_formatoNumerico)) || '</saldo_anterior>';
                    lsSaldoAnterior:=l_saldoAnterior;

                    -- Pagos Posteriores
                    --l_textoActual := l_textoActual || '<pagos_posteriores>' || trim(to_char(l_totalPagoPosterior, l_formatoNumerico)) || '</pagos_posteriores>';
                    lsPagosPosteriores:=l_totalPagoPosterior;

                    -- Saldo Cupon
                    --l_textoActual := l_textoActual || '<saldo_cupon>' || trim(to_char(saldo_cupon, l_formatoNumerico)) || '</saldo_cupon>';
                    lsSaldoCupon:= saldo_cupon;


                    -- Intereses de Mora
                    --l_textoActual := l_textoActual || '<inter_mora>' || trim(to_char(nvl(r_consolidado(i).gastos,0), l_formatoNumerico)) || '</inter_mora>';
                    lsInteresMora:= nvl(r_consolidado(i).gastos,0);



                                 l_catalogo_anterior:=null;
                                 l_totalFactura:=null;
                                 l_totalAhorro:=null;


                /*ACTUALIZAMOS LA CABECERA*/
                    UPDATE IMP_PRINT_PEDID_RESUM
                    SET
                      VAL_MONT_PAGO = lsTotalCatalogo,
                      VAL_TABU_01B = 1,
                      VAL_TABU_02B = 1,
                      VAL_TABU_03B = 1,
                      VAL_TABU_04B = 1,
                      VAL_TABU_05B = 1,
                      VAL_TABU_06B = 0,
                      VAL_DESC_01B = 'SUBTOTAL',
                      VAL_DESC_02B = 'TOTAL DESCUENTOS',
                      VAL_DESC_03B = 'FLETES',
                      VAL_DESC_04B = 'GASTOS ADMINISTRATIVOS',
                      VAL_DESC_05B = 'TOTAL A PAGAR',
                      VAL_DESC_06B = 'TOTAL OPORTUNIDAD DE AHORRO',
                      VAL_MONT_01B = lsTotalCatalogo,
                      VAL_MONT_02B = lsTotalDescuentos,
                      VAL_MONT_03B = lsTotalFlete,
                      VAL_MONT_04B = lsInteresMora,
                      VAL_MONT_05B = l_totalFactura-l_totalAhorro+r_consolidado(i).val_impo_flet_tota_loca+l_cargoFamSegura+nvl(r_consolidado(i).gastos,0),
                      VAL_MONT_06B = l_totalAhorro,
                      VAL_DESC_01A = 'DEL '||lsValorCata1,
                      VAL_DESC_02A = 'DEL '||lsValorCata2,
                      VAL_DESC_03A = 'DEL '||lsValorCata3,
                      VAL_MONT_01A = lsMontoCata1,
                      VAL_MONT_02A = lsMontoCata2,
                      VAL_MONT_03A = lsMontoCata3
                  WHERE
                      PRSP_COD_PROC = psCodigoTransaccional
                      AND PRSP_COD_CLIE = r_consolidado(i).cod_clie
                      AND PRSP_COD_PAQU = psCodigoPaquete
                      AND OID_PEDI_RESU = lsOidCabecera;

            UPDATE imp_print_spool s
           SET mon_sald_actu = lstotalfactura,
               mon_sald_ante = l_saldoanterior,
               mon_opor_ahor =  l_oportunidadahorro
         WHERE s.cod_clie = r_consolidado(i).cod_clie;

            END LOOP;

        END IF;



        EXIT WHEN c_consolidados%NOTFOUND;
    END LOOP;
    -- Cerramos el cursor
    CLOSE c_consolidados;
END IMP_PR_PROCE_PEDID_CO;

/**************************************************************************
Descripcion         : Proceso que obtiene la informacion para generar la
                      seccion de detalle de cuenta corriente.
Fecha Creacion      : 11/05/2010
Fecha Modificacion  : 11/05/2010
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Parametros Entrada  :
    p_codigoPais            : Codigo del pais
    p_codigoPeriodo         : Periodo del cupon
    p_fechaFacturacion      : Fecha de Facturacion
Version             : Final (Alfa|Final)

IMP-108
***************************************************************************/


/**************************************************************************
Descripcion         : Proceso que obtiene la informacion para generar la
                      seccion de detalle de factura modificada por Proyecto UNO y
                      optimizada para lanzarse en paralelo por region.
Fecha Creacion      : 13/09/2011
Fecha Modificacion  : 13/09/2011
Autor               : Jorge Yepez Reyes - cahurtado@belcorp.biz
Parametros Entrada  :
    p_codigoPais            : Codigo del pais
    p_codigoPeriodo         : Periodo del cupon
    p_fechaFacturacion      : Fecha de Facturacion
Version             : Final (Alfa|Final)

IMP-101
***************************************************************************/
PROCEDURE IMP_PR_PROCE_DETAL_FACTU( p_codigoPais       IN VARCHAR2,
                              p_codigoPeriodo    IN VARCHAR2,
                              p_oidZona          IN NUMBER,
                              p_fechaFacturacion IN VARCHAR2 ) IS

CURSOR c_consolidados( oidPeriodo NUMBER,
                       indicadorEnvioLarissa VARCHAR2,
                       numeroLoteFacturacion NUMBER,
                       oidRegi NUMBER) IS
SELECT SP.OID_PAIS,
       SP.COD_PAIS,
       MC.OID_CLIE,
       MC.COD_CLIE,
       MC.COD_DIGI_CTRL,
       MC.VAL_NOM1,
       MC.VAL_NOM2,
       MC.VAL_APE1,
       MC.VAL_APE2,
       MCI.NUM_DOCU_IDEN,
       CON.OID_SOLI_CABE,
       CON.VAL_NUME_SOLI,
       CON.FEC_FACT,
       CON.VAL_IMPO_FLET_TOTA_LOCA,
       CON.Val_Reca_Flet_Loca,
       CON.VAL_IMPO_REDO_LOCA,
       substr(des_pais, 1,length(des_pais)-decode(instr(des_pais,'ESIKA'),0,0,5)-decode(instr(des_pais,'LBEL'),0,0,4)) DES_PAIS,
       ZON.COD_ZONA,
       SEC.COD_SECC,
       TER.COD_TERR,
       SEC.NUM_SECU_FACT_DIAR,
       mcda.ind_impr_docu imprimefactel,
       mcda.ind_impr_pdoc imprimepaqdoc,
       con.val_tota_gast_admi GASTOS,
       con.val_tota_gast_admi2 GASTOS2,
       (select val_nom1 || ' ' || val_nom2 from mae_clien where oid_clie=zon.clie_oid_clie)  nombreGZ,
       (select val_ape1 || ' ' || val_ape2 from mae_clien where oid_clie=zon.clie_oid_clie)  apellidosGZ,
       (select x.val_text_comu from mae_clien_comun x where clie_oid_clie=zon.clie_oid_clie and x.ticm_oid_tipo_comu=6 and rownum=1)  celular_GZ,
       (select x.val_text_comu from mae_clien_comun x where clie_oid_clie=zon.clie_oid_clie and x.ticm_oid_tipo_comu=3 and rownum=1)  correo_GZ,
       (select decode(y.val_sigl,'RUC',y.val_sigl,'DNI') from mae_tipo_docum y where y.oid_tipo_docu=mci.tdoc_oid_tipo_docu and rownum=1) tipo_docum,
       con.val_impo_impu_tota_loca,
       con.val_impo_desc_flet,
       con.val_impo_desc_3_tota_loca,
       con.val_impo_desc_4_tota_loca,
       (select x1.val_i18n from gen_i18n_sicc_comun x1 where x1.attr_enti='CAR_NIVEL_RIESG' and x1.val_oid=mcda.niri_oid_nive_ries) nr,
       nvl(mc.val_recl_pend,0) val_recl_pend
FROM MAE_CLIEN MC,
     MAE_CLIEN_IDENT MCI,
     MAE_CLIEN_DATOS_ADICI MCDA,
     PED_SOLIC_CABEC CON,
     ZON_ZONA ZON,
     ZON_TERRI TER,
     ZON_TERRI_ADMIN ZTA,
     ZON_SECCI SEC,
     PED_SOLIC_CABEC_SECUE SEC,
     SEG_PAIS SP,
     BAS_PAIS BP
WHERE MC.OID_CLIE = CON.CLIE_OID_CLIE
  AND MC.OID_CLIE = MCI.CLIE_OID_CLIE
  AND MC.OID_CLIE = MCDA.CLIE_OID_CLIE
  AND MCI.VAL_IDEN_DOCU_PRIN = 1
  AND SP.OID_PAIS = CON.PAIS_OID_PAIS
  AND SP.COD_PAIS = BP.COD_PAIS
  AND CON.ZZON_OID_ZONA = ZON.OID_ZONA
  AND CON.TERR_OID_TERR = TER.OID_TERR
  AND CON.ZTAD_OID_TERR_ADMI = ZTA.OID_TERR_ADMI
  AND ZTA.ZSCC_OID_SECC = SEC.OID_SECC
  AND CON.OID_SOLI_CABE = SEC.SOCA_OID_SOLI_CABE
  AND CON.FEC_FACT = TO_DATE(p_fechaFacturacion, 'dd/mm/yyyy')
  AND CON.PERD_OID_PERI = oidPeriodo
  AND ZON.Oid_Zona = oidRegi
  and con.ind_inte_lari_gene = indicadorEnvioLarissa
  and (numeroLoteFacturacion is null or con.num_lote_fact = numeroLoteFacturacion)
  AND CON.NUM_UNID_ATEN_TOTA > 0
  AND EXISTS (
      SELECT NULL
      FROM INT_LAR_TIPO_SOLICI_PEDIDO_DIS L
      WHERE L.TSPA_OID_TIPO_SOLI_PAIS = CON.TSPA_OID_TIPO_SOLI_PAIS
  );

TYPE consolidadorecord IS RECORD (
    oid_pais                seg_pais.oid_pais%TYPE,
    cod_pais                seg_pais.cod_pais%TYPE,
    oid_clie                mae_clien.oid_clie%TYPE,
    cod_clie                mae_clien.cod_clie%TYPE,
    cod_digi_ctrl           mae_clien.cod_digi_ctrl%TYPE,
    val_nom1                mae_clien.val_nom1%TYPE,
    val_nom2                mae_clien.val_nom2%TYPE,
    val_ape1                mae_clien.val_ape1%TYPE,
    val_ape2                mae_clien.val_ape2%TYPE,
    num_docu_iden           mae_clien_ident.num_docu_iden%TYPE,
    oid_soli_cabe           ped_solic_cabec.oid_soli_cabe%TYPE,
    val_nume_soli           ped_solic_cabec.val_nume_soli%TYPE,
    fec_fact                ped_solic_cabec.fec_fact%TYPE,
    val_impo_flet_tota_loca ped_solic_cabec.val_impo_flet_tota_loca%TYPE,
    val_reca_flet_loca      ped_solic_cabec.val_reca_flet_loca%TYPE,
    val_impo_redo_loca      ped_solic_cabec.val_impo_redo_loca%TYPE,
    des_pais                bas_pais.des_pais%TYPE,
    cod_zona                zon_zona.cod_zona%TYPE,
    cod_secc                zon_secci.cod_secc%TYPE,
    cod_terr                zon_terri.cod_terr%TYPE,
    num_secu_fact_diar      ped_solic_cabec_secue.num_secu_fact_diar%TYPE,
    imprimefactel           mae_clien_datos_adici.ind_impr_docu%TYPE,
    imprimepaqdoc           mae_clien_datos_adici.ind_impr_pdoc%TYPE,
    gastos                  ped_solic_cabec.val_tota_gast_admi%TYPE,
    gastos2                  ped_solic_cabec.val_tota_gast_admi2%TYPE,
    nombresGZ               varchar2(100),
    apellidosGZ             varchar2(100),
    celularGZ               varchar2(100),
    correoGZ                varchar2(100),
    tipo_docum               varchar2(100),
    val_impo_impu_tota_loca   ped_solic_cabec.val_impo_impu_tota_loca%TYPE,
    val_impo_desc_flet        ped_solic_cabec.val_impo_desc_flet%TYPE,
    val_impo_desc_3_tota_loca ped_solic_cabec.val_impo_desc_3_tota_loca%TYPE,
    val_impo_desc_4_tota_loca ped_solic_cabec.val_impo_desc_4_tota_loca%TYPE,
    nr                        gen_i18n_sicc_comun.val_i18n%TYPE,
    val_recl_pend             mae_clien.val_recl_pend%TYPE
);

TYPE consolidadotype IS TABLE OF consolidadorecord;
r_consolidado    consolidadotype;

CURSOR c_detalle( oidConsolidado      NUMBER,
                  v_desPremioAgotado  VARCHAR2,
                  v_desAgotado        VARCHAR2,
                  v_desFaltLiq        VARCHAR2,
                  v_desFaltAnun       VARCHAR2,
                  v_desAnulMotMax     VARCHAR2,
                  v_desPremio         VARCHAR2,
                  v_desAtRec          VARCHAR2,
                  v_desRecup          VARCHAR2,
                  v_desReemp          VARCHAR2,
                  v_desGratis         VARCHAR2,
                  v_desPremioLET      VARCHAR2,
                  v_desRecupSemana    VARCHAR2,
                  v_desOfertNavid     VARCHAR2,
                  v_oidestra          NUMBER,
                  v_desRecuPROL       VARCHAR2,
                  v_desRecuPROL2      VARCHAR2
                  ) IS

SELECT PSC.OID_SOLI_CABE,
       PSC.COPA_OID_PARA_GENE,
       PSC.ICTP_OID_TIPO_PROG,
       PSP.OID_SOLI_POSI,
       nvl(NVL(PSP.VAL_CODI_VENT, LPAD('0', 4 - LENGTH(PSP.VAL_CODI_VENT_FICT), '0') || PSP.VAL_CODI_VENT_FICT),'00000') AS VAL_CODI_VENT,
       IMP_FN_DESC_PRODU( p_codigoPais, psp.prod_oid_prod ) DES_PROD,
       PSP.NUM_UNID_DEMA_REAL,
       PSP.NUM_UNID_ATEN,
       PSP.VAL_PREC_CATA_UNIT_LOCA,
       PSP.VAL_PREC_CATA_UNIT_LOCA * PSP.NUM_UNID_ATEN VAL_PREC_CATA_TOTA_LOCA,
       PSP.VAL_PREC_CONT_UNIT_LOCA,
       PSP.VAL_PREC_CONT_UNIT_LOCA * PSP.NUM_UNID_ATEN VAL_PREC_CONT_TOTA_LOCA,
       PSP.VAL_PREC_FACT_TOTA_LOCA,
       PSP.VAL_IMPO_DESC_TOTA_LOCA,
       NVL(PSP.VAL_PORC_DESC, 0) VAL_PORC_DESC,
       PSP.VAL_IMPO_IMPU_TOTA_LOCA,
       POD.IMP_PREC_PUBL,
       PSP.FOPA_OID_FORM_PAGO,
       decode(psc.modu_oid_modu,15,3,nvl(PTO.NUM_SECC_DETA_FACT,2)) IND_GRUP,
       psp.val_codi_orig,
       psp.num_unid_orig,
       psp.oid_nive_ofer,
       decode(mp.val_atri_3,'1','C',nvl(psp.ind_dent_fuer_caja_bols,'F')) ind_dent_fuer_caja_bols,
       CASE
           WHEN exists (select 1 from pre_ofert x where x.oid_ofer=pod.ofer_oid_ofer and x.coes_oid_estr=v_oidestra)
               THEN v_desOfertNavid
           WHEN exists (select 1 from pre_recup_seman x where x.cod_peri=p_codigoPeriodo and x.cod_vent=psp.val_codi_vent) and (NVL(PSP.NUM_UNID_DEMA_REAL, 0) - NVL(PSP.NUM_UNID_ATEN, 0) > 0)
               THEN v_desRecupSemana
           WHEN (NVL(PSP.NUM_UNID_DEMA_REAL, 0) - NVL(PSP.NUM_UNID_ATEN, 0) > 0) AND (PSC.COPA_OID_PARA_GENE IS NOT NULL OR PSC.ICTP_OID_TIPO_PROG IS NOT NULL)
               THEN v_desPremioAgotado
           WHEN NVL(PSP.NUM_UNID_DEMA_REAL, 0) - NVL(PSP.NUM_UNID_ATEN, 0) > 0
               THEN v_desAgotado
           WHEN NVL(PSP.NUM_UNID_DEMA_REAL, 0) = 0 AND NVL(PSP.NUM_UNID_ATEN, 0) = 0
                AND PSP.IND_LIMI_VENT = 1 AND (PTO.COD_TIPO_OFER = '21' OR PTO.COD_TIPO_OFER = '23')
               THEN v_desFaltLiq
           WHEN NVL(PSP.NUM_UNID_DEMA_REAL, 0) = 0 AND NVL(PSP.NUM_UNID_ATEN, 0) = 0
                AND PSP.IND_LIMI_VENT = 1
               THEN v_desFaltAnun
           WHEN NVL(PSP.NUM_UNID_DEMA_REAL, 0) = 0 AND NVL(PSP.NUM_UNID_ATEN, 0) = 0
                AND PSP.ESPO_OID_ESTA_POSI = 2 AND PSP.STPO_OID_SUBT_POSI = 21
               THEN v_desAnulMotMax
          WHEN PST.COD_SUBT_POSI IS NOT NULL AND PST.COD_SUBT_POSI = 'RD'
               THEN v_desAgotado
           WHEN (PSC.COPA_OID_PARA_GENE IS NOT NULL OR PSC.ICTP_OID_TIPO_PROG IS NOT NULL)
               THEN v_desPremio
           WHEN PSC.MODU_OID_MODU = '15'
               THEN v_desAtRec
           WHEN PTP.COD_TIPO_POSI IS NOT NULL AND PTP.COD_TIPO_POSI = 'RE'
               THEN v_desRecup
           WHEN PTP.COD_TIPO_POSI IS NOT NULL AND PTP.COD_TIPO_POSI = 'DA'
               THEN ''
           WHEN PST.COD_SUBT_POSI IS NOT NULL AND PST.COD_SUBT_POSI = 'RZ'
               THEN v_desReemp  || (
                 SELECT distinct POF.Val_Codi_Vent
                 FROM PRE_MATRI_FACTU PMF,
                      PRE_MATRI_REEMP PMR,
                      PRE_MATRI_FACTU PMF2,
                      PRE_OFERT_DETAL POF
                 WHERE PMF.OID_MATR_FACT = PMR.MAFA_OID_COD_REEM
                 AND PMF.OFDE_OID_DETA_OFER = PSP.OFDE_OID_DETA_OFER
                 AND PMR.MAFA_OID_COD_PPAL=PMF2.Oid_Matr_Fact
                 AND PMF2.Ofde_Oid_Deta_Ofer=POF.Oid_Deta_Ofer
                 AND exists (select 1 from ped_solic_posic where soca_oid_soli_cabe=PSC.OID_SOLI_CABE
                              and ofde_oid_deta_ofer=PSP.OFDE_Oid_Deta_Ofer)
                 and rownum=1
                 )
           WHEN PSP.VAL_PREC_CATA_UNIT_LOCA = 0
               THEN v_desGratis
           WHEN TS.Cod_Tipo_Soli = 'IPLC'
               THEN v_desPremioLET
           WHEN psp.ind_recu_prol = '1'
               THEN v_desRecuPROL
           WHEN psp.ind_recu_prol = '2'
               THEN v_desRecuPROL2
           ELSE ''
        END AS VAL_OBSE
FROM PED_SOLIC_CABEC PSC,
     PED_SOLIC_POSIC PSP,
     PRE_OFERT_DETAL POD,
     PRE_TIPO_OFERT PTO,
     PED_TIPO_SOLIC_PAIS TSP,
     PED_TIPO_SOLIC TS,
     PED_TIPO_POSIC PTP,
     PED_SUBTI_POSIC PST,
     mae_produ mp
WHERE PSC.OID_SOLI_CABE = PSP.SOCA_OID_SOLI_CABE
  AND PSC.TSPA_OID_TIPO_SOLI_PAIS = TSP.OID_TIPO_SOLI_PAIS
  AND TSP.TSOL_OID_TIPO_SOLI = TS.OID_TIPO_SOLI
  and psp.prod_oid_prod=mp.oid_prod
  AND PSP.TPOS_OID_TIPO_POSI = PTP.OID_TIPO_POSI (+)
  AND PSP.Stpo_Oid_Subt_Posi = PST.Oid_Subt_Posi (+)
  AND PSP.OFDE_OID_DETA_OFER = POD.OID_DETA_OFER (+)
  AND POD.TOFE_OID_TIPO_OFER = PTO.OID_TIPO_OFER (+)
  AND PSP.ESPO_OID_ESTA_POSI != 2
  AND PSC.SOCA_OID_SOLI_CABE = oidConsolidado
ORDER BY OID_NIVE_OFER, VAL_CODI_ORIG, VAL_CODI_VENT;

TYPE detallerecord IS RECORD (
    oid_soli_cabe               ped_solic_cabec.oid_soli_cabe%TYPE,
    copa_oid_para_gene          ped_solic_cabec.copa_oid_para_gene%TYPE,
    ictp_oid_tipo_prog          ped_solic_cabec.ictp_oid_tipo_prog%TYPE,
    oid_soli_posi               ped_solic_posic.oid_soli_posi%TYPE,
    val_codi_vent               varchar2(15),
    des_prod                    gen_i18n_sicc_pais.val_i18n%TYPE,
    num_unid_dema_real          ped_solic_posic.num_unid_dema_real%TYPE,
    num_unid_aten               ped_solic_posic.num_unid_aten%TYPE,
    val_prec_cata_unit_loca     ped_solic_posic.val_prec_cata_unit_loca%TYPE,
    val_prec_cata_tota_loca     ped_solic_posic.val_prec_cata_tota_loca%TYPE,
    val_prec_cont_unit_loca     ped_solic_posic.val_prec_cont_unit_loca%TYPE,
    val_prec_cont_tota_loca     ped_solic_posic.val_prec_cont_tota_loca%TYPE,
    val_prec_fact_tota_loca     ped_solic_posic.val_prec_fact_tota_loca%TYPE,
    val_impo_desc_tota_loca     ped_solic_posic.val_impo_desc_tota_loca%TYPE,
    val_porc_desc               ped_solic_posic.val_porc_desc%TYPE,
    val_impo_impu_tota_loca     ped_solic_posic.val_impo_impu_tota_loca%TYPE,
    imp_prec_publ               pre_ofert_detal.imp_prec_publ%TYPE,
    fopa_oid_form_pago          ped_solic_posic.fopa_oid_form_pago%TYPE,
    ind_grupo                   pre_tipo_ofert.num_secc_deta_fact%TYPE,
    val_codi_orig               ped_solic_posic.val_codi_orig%TYPE,
    num_unid_orig               ped_solic_posic.num_unid_orig%TYPE,
    oid_nive_ofer               ped_solic_posic.oid_nive_ofer%TYPE,
    ind_dent_fuer_caja_bols     ped_solic_posic.ind_dent_fuer_caja_bols%TYPE,
    val_obse                    VARCHAR2(1000)
);

TYPE detalletype IS TABLE OF detallerecord;
r_detalle    detalletype;

-- Variables locales
l_oidPais                   NUMBER;
l_oidPeriodo                NUMBER;
l_oidCanal                  NUMBER;
l_oidMarca                  NUMBER;
l_correlativo               NUMBER := 1;
l_contadorDetalles          NUMBER := 0;
l_porcDscto                 NUMBER;
l_precioUnitario            NUMBER(12,2) := 0;
l_precioTotal               NUMBER(12,2) := 0;
l_descuento                 NUMBER(12,2) := 0;
l_descuento2                NUMBER(12,2) := 0;
l_dif_desc                  NUMBER(12,2) := 0;
l_precioFacturado           NUMBER(12,2) := 0;
l_pagoPosterior             NUMBER(12,2) := 0;
l_precioCatalogo            NUMBER(12,2) := 0;
l_PrecioCatalogoTotal       NUMBER(12,2) := 0;
l_oportunidadAhorro         NUMBER(12,2) := 0;

l_subtotalSolicitado        NUMBER := 0;
l_subtotalAtendido          NUMBER := 0;
l_subtotalCatalogo          NUMBER(12, 2) := 0;
l_subtotalDescuentos        NUMBER(12, 2) := 0;
l_subtotalFacturado         NUMBER(12, 2) := 0;
l_subtotalPrecioCatalogo    NUMBER(12, 2) := 0;
l_subtotalPrecioCatalogoTotal       NUMBER(12, 2) := 0;
l_subtotaloportunidadAhorro NUMBER(12, 2) := 0;

l_totalSolicitado           NUMBER := 0;
l_totalAtendido             NUMBER := 0;
l_totalCatalogo             NUMBER(12, 2) := 0;
l_totalDescuentos           NUMBER(12, 2) := 0;
l_totalDescuentos2           NUMBER(12, 2) := 0;
l_totalFacturado            NUMBER(12, 2) := 0;
l_totalPrecioCatalogo       NUMBER(12, 2) := 0;
l_totalPrecioCatalogoTotal  NUMBER(12, 2) := 0;
l_totalOportunidadAhorro    NUMBER(12, 2) := 0;
l_totalImpuestosGratis      NUMBER(12, 2) := 0;
l_saldoFavor                NUMBER(12, 2) := 0;
l_saldoAnterior             NUMBER(12, 2) := 0;
l_saldoFlexAnterior         NUMBER(12, 2) := 0;
l_interesFlexipago          NUMBER(12, 2) := 0;
l_percepcion                NUMBER(12, 2) := 0;
l_totalFactura              NUMBER(12, 2) := 0;
l_cargoFamSegura            NUMBER(12, 2) := 0;
l_totalAPagar               NUMBER(12, 2) := 0;

l_oportunidadCatalogo       NUMBER(12, 2) := 0;
l_oportunidadRevista        NUMBER(12, 2) := 0;

l_indicadorEnvioLarissa     VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioLarissa');
l_indicadorEnvioUltimoLote  VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioUltimoLote');
l_indicadorCargoFamSegura   VARCHAR2(100) := NVL(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorCargoFamSegura'),'N');
l_indicadorImpuestosGratis  VARCHAR2(1) := 'N';
l_totalPagoPosterior        NUMBER(12, 2) := 0;
l_indicadorPercepcion       VARCHAR2(1) := 'N';
l_numeroLoteFacturacion     NUMBER;
l_clob                      CLOB;
l_textoActual               VARCHAR2(20000) := '';
l_codigoPeriodoSiguiente    VARCHAR2(6);
l_cambioLineaRetornoCarro   VARCHAR2(2)   := CHR(13) || CHR(10);
l_formatoNumerico           VARCHAR2(100) := NVL(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'formatoNumerico'),'9999999G990D00');
l_tasaImpuestoPercepcion    NUMBER(5, 3);
--nuevo parametro
l_indicadorGenerarDetalleFact2 VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorGenerarDetalleFactura2');

l_nombreSeccion1 VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'nombreSeccion1');
l_nombreSeccion2 VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'nombreSeccion2');
l_nombreSeccion3 VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'nombreSeccion3');
l_nombreSeccion4 VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'nombreSeccion4');

l_desRecupSemana VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desRecupSemana');
l_desPremioAgotado VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desPremioAgotado');
l_desAgotado VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desAgotado');
l_desFaltLiq VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desFaltLiq');
l_desFaltAnun VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desFaltAnun');
l_desAnulMotMax VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desAnulMotMax');
l_desPremio VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desPremio');
l_desAtRec VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desAtRec');
l_desRecup VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desRecup');
l_desReemp VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desReemp');
l_desGratis VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desGratis');
l_desPremioLET VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desPremioLET');
l_desOfertNavid VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desOfertNavid');
l_desRecuPROL VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desRecuPROL');
l_desRecuPROL2 VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desRecuPROL2');



l_mensajeOportunidadAhorro VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'mensajeOportunidadAhorro');

l_fechaVencimiento VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'mensajeOportunidadAhorro');

l_flagTitulo1     NUMBER:=0;
l_flagTitulo2     NUMBER:=0;
l_flagTitulo3     NUMBER:=0;
l_flagTitulo4     NUMBER:=0;

l_cantidadOC      NUMBER:=0;

lv_indiejec VARCHAR2(10):=nvl(sto_pkg_gener.sto_fn_obten_param_ocr(p_codigoPais,'STO_GASTO_ADMIN'),'N');

lv_indiejec2 VARCHAR2(10):=IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','impuestoEstatal');

lv_reemplazoOCS VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorReemplazoOCS');

lv_actividadConf VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','actividadConf'),'CV');

lv_actividadDesp VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','actividadDesp'),'DP');

lv_imprimepaqdoc VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','imprimepaqdoc'),'N');

lv_imprimefactel VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','imprimefactel'),'N');

lv_imprimenuevos VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','imprimenuevos'),'N');

lv_redondeoDesc VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','redondeoDesc'),'S');


ln_oidestra NUMBER(10):= sto_pkg_gener.sto_fn_obten_param_ocr(p_codigoPais,'STO_ESTRA_NAVID');

ln_diasdesp NUMBER(10):= 0;
ln_diasdesp2 NUMBER(10):= 0;
ln_diasdesp3 NUMBER(10):= 0;


l_fechaCV VARCHAR2(100) := '';
l_fechaDespacho VARCHAR2(100) := '';
l_fechaDespacho_2 VARCHAR2(100) := '';
l_fechaDespacho_3 VARCHAR2(100) := '';
l_fechaDespacho2 VARCHAR2(100) :=  nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indFechaDesp2'),'N');
l_fechaSigFact VARCHAR2(100) := '';
l_fechaSigFact2 VARCHAR2(100) := '';
l_fechaSigFact3 VARCHAR2(100) := '';

lsactividadparametro VARCHAR2(100):= sto_pkg_gener.sto_fn_obten_param_ocr(p_codigoPais,
                                                                 'STO_ACTIV_RECAR_FLET');
lscodperigastos VARCHAR2(10);

ln_desc_flete VARCHAR2(2):=nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'descFlete'),'N');

ln_desc_flete2 VARCHAR2(2):=nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'descFlete2'),'N');

orig_ante VARCHAR2(100):='';

BEGIN


    -- Obtenemos el OID del periodo
    l_oidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(p_codigoPais);
    l_oidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(CODIGO_CANAL);
    l_oidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(CODIGO_MARCA);
    l_oidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(p_codigoPeriodo, l_oidMarca, l_oidCanal);
    l_codigoPeriodoSiguiente := GEN_FN_CALCU_PERIO(p_codigoPeriodo, 1);

    -- Solo se jecutara el proceso si parametro es S, en otro caso no se ejecutara

    IF( l_indicadorEnvioUltimoLote = '1' OR l_indicadorEnvioUltimoLote = 'S' ) THEN
        BEGIN
            SELECT MAX( cons.num_lote_fact )
              INTO l_numeroLoteFacturacion
              FROM ped_solic_cabec cons,
                   int_lar_tipo_solici_pedido_dis tspd
             WHERE cons.perd_oid_peri = l_oidPeriodo
               AND cons.fec_fact = to_date( p_fechaFacturacion, 'DD/MM/YYYY' )
               AND cons.ind_ts_no_conso = 0
               AND ( cons.ind_pedi_prue = 0 or cons.ind_pedi_prue IS NULL )
               AND cons.tspa_oid_tipo_soli_pais = tspd.tspa_oid_tipo_soli_pais
               AND cons.pais_oid_pais = l_oidPais;
        EXCEPTION WHEN OTHERS THEN
              l_numeroLoteFacturacion := NULL;
        END;
    END IF;

    -- Obtenemos el valor del indicador de impuestos a los productos gratis
    select decode(count(*), 0, 'N', 'S')
    into l_indicadorImpuestosGratis
    from seg_pais p,
         seg_param_inter_pais i
    where p.oid_pais = i.pais_oid_pais
    and i.ind_impu_prod_grat = 1
    and p.cod_pais = p_codigoPais;

    -- Obtenemos el valor del indicador de percepciones
    select decode(count(*), 0, 'N', 'S')
      into l_indicadorPercepcion
      from fac_formu_perce ffp
     where ffp.pais_oid_pais = l_oidPais;

    -- Obtenemos el valor de la tasa de impuesto por percepciones
    IF l_indicadorPercepcion = 'S' THEN
        BEGIN
            select pti.val_tasa_impu
            into l_tasaImpuestoPercepcion
            from ped_tasa_impue pti
            where pti.pais_oid_pais = l_oidPais
            and pti.val_indi_impu = 'PER'; -- Codigo Percepciones
        EXCEPTION
        WHEN NO_DATA_FOUND THEN
            l_tasaImpuestoPercepcion := 0;
        END;
    END IF;

-- Abrimos el cursor principal
OPEN c_consolidados(l_oidPeriodo, l_indicadorEnvioLarissa, l_numeroLoteFacturacion, p_oidZona);
    LOOP
        FETCH c_consolidados BULK COLLECT
        INTO r_consolidado LIMIT w_filas;

        IF  r_consolidado.COUNT > 0 THEN
            FOR i IN r_consolidado.FIRST..r_consolidado.LAST
            LOOP

                l_flagTitulo1:=0;
                l_flagTitulo2:=0;
                l_flagTitulo3:=0;
                l_flagTitulo4:=0;

                -- Insertamos el registro y obtenemos la referencia al CLOB

                /*INSERT INTO IMP_PAQUE_DOCUM_DETAL_FACTU
                            (
                              COR_PADO_DEFA,
                              COD_CONS,
                              VAL_NUME_SOLI,
                              XML_DETA_FACT
                            )
                       VALUES (
                                r_consolidado(i).oid_soli_cabe,
                                r_consolidado(i).cod_clie,
                                r_consolidado(i).val_nume_soli,
                                EMPTY_CLOB()
                              )
                RETURNING XML_DETA_FACT INTO l_clob;*/

                -- Inicio Detalle de Factura
                --l_textoActual := '<detfac2>';

                -- Inicio Cabecera
                --l_textoActual := l_textoActual || '<blqcab>';

                if lv_imprimefactel='S' then
                  if r_consolidado(i).tipo_docum<>'RUC' then
                     r_consolidado(i).imprimefactel:='1';
                  end if;
                end if;

                -- Ind. Impresion FE
                l_textoActual := l_textoActual || '<imprimefactel>' || r_consolidado(i).imprimefactel || '</imprimefactel>';

                if lv_imprimepaqdoc='S' then
                  -- Ind. Impresion
                  l_textoActual := l_textoActual || '<imprimepaqdoc>' || r_consolidado(i).imprimepaqdoc || '</imprimepaqdoc>';
                end if;

                if lv_imprimenuevos='S' then

                  begin
                  SELECT to_char(cc.FEC_INIC,'dd/mm/yyyy'),to_char(cc.FEC_INIC,'dd/mm/yyyy'),to_char(cc.FEC_INIC,'dd/mm/yyyy')
                  INTO l_fechaDespacho,l_fechaDespacho_2,l_fechaDespacho_3
                  FROM cra_crono cc, CRA_PERIO CP, seg_perio_corpo spc, CRA_ACTIV act
                  WHERE cc.PERD_OID_PERI = cp.oid_peri
                    AND cp.peri_oid_peri = spc.oid_peri
                    AND spc.cod_peri = l_codigoPeriodoSiguiente
                    AND cc.ZZON_OID_ZONA = (select oid_zona from zon_zona where cod_zona=r_consolidado(i).cod_zona)

                    AND cc.CACT_OID_ACTI = act.OID_ACTI
                    AND act.COD_ACTI = lv_actividadDesp;
                  exception when others then
                    l_fechaDespacho:=trunc(sysdate);
                    l_fechaDespacho_2:=trunc(sysdate);
                    l_fechaDespacho_3:=trunc(sysdate);
                  end;

                  begin
                  SELECT to_char(cc.FEC_INIC,'dd/mm/yyyy')
                  INTO l_fechaSigFact
                  FROM cra_crono cc, CRA_PERIO CP, seg_perio_corpo spc, CRA_ACTIV act
                  WHERE cc.PERD_OID_PERI = cp.oid_peri
                    AND cp.peri_oid_peri = spc.oid_peri
                    AND spc.cod_peri = l_codigoPeriodoSiguiente
                    AND cc.ZZON_OID_ZONA = (select oid_zona from zon_zona where cod_zona=r_consolidado(i).cod_zona)

                    AND cc.CACT_OID_ACTI = act.OID_ACTI
                    AND act.COD_ACTI = 'FA';
                  exception when others then
                    l_fechaSigFact:=trunc(sysdate);
                  end;

                  begin
                  SELECT to_char(cc.FEC_INIC,'dd/mm/yyyy')
                  INTO l_fechaCV
                  FROM cra_crono cc, CRA_PERIO CP, seg_perio_corpo spc, CRA_ACTIV act
                  WHERE cc.PERD_OID_PERI = cp.oid_peri
                    AND cp.peri_oid_peri = spc.oid_peri
                    AND spc.cod_peri = l_codigoPeriodoSiguiente
                    AND cc.ZZON_OID_ZONA = (select oid_zona from zon_zona where cod_zona=r_consolidado(i).cod_zona)
                    AND cc.CACT_OID_ACTI = act.OID_ACTI
                    AND act.COD_ACTI = lv_actividadConf;
                  exception when others then
                    l_fechaCV:=trunc(sysdate);
                  end;


                  l_fechaSigFact2:=to_char(fac_pkg_proc.ped_fn_dev_dia_fact(l_codigoPeriodoSiguiente,r_consolidado(i).cod_zona,2),'dd/mm/yyyy');

                  l_fechaSigFact3:=to_char(fac_pkg_proc.ped_fn_dev_dia_fact(l_codigoPeriodoSiguiente,r_consolidado(i).cod_zona,3),'dd/mm/yyyy');

                  ln_diasdesp:=fac_pkg_proc.ped_fn_obt_dias_fecha_ent(r_consolidado(i).oid_soli_cabe,1,l_codigoPeriodoSiguiente, to_date(l_fechaSigFact,'dd/mm/yyyy'));
                  ln_diasdesp2:=fac_pkg_proc.ped_fn_obt_dias_fecha_ent(r_consolidado(i).oid_soli_cabe,2,l_codigoPeriodoSiguiente, to_date(l_fechaSigFact,'dd/mm/yyyy'));
                  ln_diasdesp3:=fac_pkg_proc.ped_fn_obt_dias_fecha_ent(r_consolidado(i).oid_soli_cabe,3,l_codigoPeriodoSiguiente, to_date(l_fechaSigFact,'dd/mm/yyyy'));


                  if l_fechaDespacho2='S' and ln_diasdesp>0 then
                    l_fechaDespacho:=to_char(to_date(l_fechaSigFact,'dd/mm/yyyy')+ln_diasdesp,'dd/mm/yyyy');
                  end if;


                  if l_fechaDespacho2='S' and ln_diasdesp>0 then
                    l_fechaDespacho_2:=to_char(to_date(l_fechaSigFact2,'dd/mm/yyyy')+ln_diasdesp2,'dd/mm/yyyy');
                  end if;

                  if l_fechaDespacho2='S' and ln_diasdesp>0 then
                    l_fechaDespacho_3:=to_char(to_date(l_fechaSigFact3,'dd/mm/yyyy')+ln_diasdesp3,'dd/mm/yyyy');
                  end if;



                  -- Nombre Consultora
                  l_textoActual := l_textoActual || '<nombres>' || r_consolidado(i).val_nom1 || ' ' || r_consolidado(i).val_nom2 || '</nombres>';
                  l_textoActual := l_textoActual || '<apellidos>' || r_consolidado(i).val_ape1 || ' ' || r_consolidado(i).val_ape2 || '</apellidos>';
                  l_textoActual := l_textoActual || '<nombresGZ>' || r_consolidado(i).nombresGZ || '</nombresGZ>';
                  l_textoActual := l_textoActual || '<apellidosGZ>' || r_consolidado(i).apellidosGZ || '</apellidosGZ>';
                  l_textoActual := l_textoActual || '<celularGZ>' || r_consolidado(i).celularGZ || '</celularGZ>';
                  l_textoActual := l_textoActual || '<correoGZ>' || r_consolidado(i).correoGZ || '</correoGZ>';
                  l_textoActual := l_textoActual || '<fechaCV>' || l_fechaCV || '</fechaCV>';
                  l_textoActual := l_textoActual || '<fechaReparto>' || l_fechaDespacho || '</fechaReparto>';
                  l_textoActual := l_textoActual || '<fechaReparto2>' || l_fechaDespacho_2 || '</fechaReparto2>';
                  l_textoActual := l_textoActual || '<fechaReparto3>' || l_fechaDespacho_3 || '</fechaReparto3>';
                  l_textoActual := l_textoActual || '<fechaSigFact>' || l_fechaSigFact || '</fechaSigFact>';
                  l_textoActual := l_textoActual || '<fechaSigFact2>' || l_fechaSigFact2 || '</fechaSigFact2>';
                  l_textoActual := l_textoActual || '<fechaSigFact3>' || l_fechaSigFact3 || '</fechaSigFact3>';
                  l_textoActual := l_textoActual || '<diasDesp>' || ln_diasdesp || '</diasDesp>';
                  l_textoActual := l_textoActual || '<tipo_docum>' || r_consolidado(i).tipo_docum || '</tipo_docum>';
                  l_textoActual := l_textoActual || imp_fn_xml_opor_ahor(p_codigoPeriodo,l_oidPeriodo,r_consolidado(i).oid_clie);
                  l_textoActual := l_textoActual || '<nriesgo>' || r_consolidado(i).nr || '</nriesgo>';
                end if;

                -- Numero Pedido
                l_textoActual := l_textoActual || '<numpedido>' || r_consolidado(i).val_nume_soli || '</numpedido>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- Descripcion Pais
                l_textoActual := l_textoActual || '<lugar>' || r_consolidado(i).des_pais || '</lugar>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- Dia
                l_textoActual := l_textoActual || '<dia>' || to_char(r_consolidado(i).fec_fact, 'dd') || '</dia>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- Mes
                l_textoActual := l_textoActual || '<mes>' || to_char(r_consolidado(i).fec_fact, 'mm') || '</mes>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- A?o
                l_textoActual := l_textoActual || '<ano>' || to_char(r_consolidado(i).fec_fact, 'yyyy') || '</ano>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- Codigo Cliente
                l_textoActual := l_textoActual || '<codconsultora>' || r_consolidado(i).cod_clie || '</codconsultora>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- Territorio Cliente
                l_textoActual := l_textoActual || '<territorio>';
                l_textoActual := l_textoActual || r_consolidado(i).cod_zona || '-';
                l_textoActual := l_textoActual || r_consolidado(i).cod_secc || '-';
                l_textoActual := l_textoActual || r_consolidado(i).cod_terr || '-';
                l_textoActual := l_textoActual || r_consolidado(i).num_secu_fact_diar;
                l_textoActual := l_textoActual || '</territorio>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- Documento de Identidad
                l_textoActual := l_textoActual || '<rifci>' || r_consolidado(i).num_docu_iden || '</rifci>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- Nombre Cliente
                l_textoActual := l_textoActual || '<nombre>';
                l_textoActual := l_textoActual || r_consolidado(i).val_nom1 || ' ';
                l_textoActual := l_textoActual || r_consolidado(i).val_nom2 || ' ';
                l_textoActual := l_textoActual || r_consolidado(i).val_ape1 || ' ';
                l_textoActual := l_textoActual || r_consolidado(i).val_ape2;
                l_textoActual := l_textoActual || '</nombre>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- Periodo
                l_textoActual := l_textoActual || '<campana>' || p_codigoPeriodo || '</campana>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);



                l_textoActual := l_textoActual || IMP_PKG_PROCE_COMPA.IMP_FN_ETIQU_CLASI_BOLET_DESPA( r_consolidado(i).cod_clie) ||
              IMP_PKG_PROCE_COMPA.IMP_FN_ETIQU_ESTAT_BOLET_DESPA( r_consolidado(i).cod_clie);


                -- Fin Cabecera
                l_textoActual := l_textoActual || '</blqcab>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);


                -- Inicio Detalle
                l_textoActual := '<detalle>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                /*
                l_textoActual := '<nombreColumnas><txt>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                l_textoActual := l_nombreColumna1 || '<tc/>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                l_textoActual := l_nombreColumna2 || '<tc/>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                l_textoActual := l_nombreColumna3 || '<tc/>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                l_textoActual := l_nombreColumna4 || '<tc/>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                l_textoActual := l_nombreColumna5 || '<tc/>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                l_textoActual := l_nombreColumna6 || '<tc/>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                l_textoActual := l_nombreColumna7 || '<tc/>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                l_textoActual := l_nombreColumna8 || '<tc/>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                l_textoActual := l_nombreColumna9 || '<tc/>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                l_textoActual := l_nombreColumna10 || '<tc/>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                l_textoActual := l_nombreColumna11 || '<tc/>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                l_textoActual := '</txt></nombreColumnas>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                */

                -- Iniciamos los totales
                l_totalSolicitado := 0;
                l_totalAtendido := 0;
                l_totalDescuentos := 0;
                l_totalCatalogo := 0;
                l_totalDescuentos := 0;
                l_totalImpuestosGratis := 0;
                l_totalFacturado := 0;
                l_totalPrecioCatalogo := 0;
                l_totalPrecioCatalogoTotal := 0;
                l_totalOportunidadAhorro := 0;
                l_oportunidadRevista:=0;
                l_oportunidadCatalogo:=0;

                -- Iteramos por las secciones del detalle
                    OPEN c_detalle(r_consolidado(i).oid_soli_cabe,
                                    l_desPremioAgotado,
                                    l_desAgotado,
                                    l_desFaltLiq,
                                    l_desFaltAnun,
                                    l_desAnulMotMax,
                                    l_desPremio,
                                    l_desAtRec,
                                    l_desRecup,
                                    l_desReemp,
                                    l_desGratis,
                                    l_desPremioLET,
                                    l_desRecupSemana,
                                    l_desOfertNavid,
                                    ln_oidestra,
                                    l_desRecuPROL,
                                    l_desRecuPROL2
                    );
                    LOOP
                        FETCH c_detalle BULK COLLECT
                        INTO r_detalle LIMIT w_filas;
                FOR k IN 0..3
                LOOP

                    orig_ante:=null;

                    -- Iniciamos los subtotales

                    l_contadorDetalles := 0;
                    l_subtotalSolicitado := 0;
                    l_subtotalAtendido := 0;
                    l_subtotalCatalogo := 0;
                    l_subtotalDescuentos := 0;
                    l_subtotalFacturado := 0;
                    l_subtotalPrecioCatalogo := 0;
                    l_subtotalPrecioCatalogoTotal := 0;
                    l_subtotaloportunidadAhorro := 0;
                    l_oportunidadAhorro := 0;

                    -- Mostramos el titulo


                        IF  r_detalle.COUNT > 0 THEN
                            FOR j IN r_detalle.FIRST..r_detalle.LAST
                            LOOP


                            if r_detalle(j).ind_grupo=k then

                                IF k = 0 and l_flagTitulo1=0 THEN
                                    l_textoActual := '<txt><u>' || l_nombreSeccion1 || '</u></txt><txt><t/></txt>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                    l_flagTitulo1:=1;
                                ELSIF k = 1 and l_flagTitulo2=0 THEN
                                    l_textoActual := '<txt/>';
                                    l_textoActual := l_textoActual || '<txt><u>' || l_nombreSeccion2 || '</u></txt><txt><t/></txt>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                    l_flagTitulo2:=1;
                                ELSIF k = 2 and l_flagTitulo3=0 THEN
                                    l_textoActual := '<txt/>';
                                    l_textoActual := l_textoActual || '<txt><u>' || l_nombreSeccion3 || '</u></txt><txt><t/></txt>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                    l_flagTitulo3:=1;
                                ELSIF k = 3 and l_flagTitulo4=0 THEN
                                    l_textoActual := '<txt/>';
                                    l_textoActual := l_textoActual || '<txt><u>' || l_nombreSeccion4 || '</u></txt><txt><t/></txt>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                    l_flagTitulo4:=1;
                                END IF;
                                -- Calculo de valores
                                IF r_detalle(j).copa_oid_para_gene IS NOT NULL
                                OR r_detalle(j).ictp_oid_tipo_prog IS NOT NULL THEN
                                    -- Si se trata de un premio
                                    l_precioUnitario := 0;
                                    l_precioTotal := 0;
                                    l_porcDscto := 100;
                                    l_descuento := 0;
                                    l_precioFacturado := 0;
                                    l_pagoPosterior := 0;
                                    l_oportunidadAhorro := 0;
                                    l_precioCatalogo := 0;
                                    l_PrecioCatalogoTotal := 0;
                                ELSE
                                    IF r_detalle(j).val_prec_cata_unit_loca = 0 THEN
                                        -- Si es un gratis
                                        l_precioUnitario := r_detalle(j).val_prec_cont_unit_loca;
                                        l_precioTotal := r_detalle(j).val_prec_cont_tota_loca;
                                        l_porcDscto := 100;
                                        l_descuento := r_detalle(j).val_prec_cont_tota_loca;
                                        l_precioFacturado := 0;
                                        l_totalImpuestosGratis := l_totalImpuestosGratis + r_detalle(j).val_impo_impu_tota_loca;
                                        l_pagoPosterior := 0;
                                        l_oportunidadAhorro := 0;
                                        l_precioCatalogo := 0;
                                        l_PrecioCatalogoTotal := 0;
                                        if r_detalle(j).ind_grupo=1 then
                                           l_precioCatalogo := nvl(r_detalle(j).imp_prec_publ,0);
                                           l_PrecioCatalogoTotal := nvl(r_detalle(j).imp_prec_publ,0)*r_detalle(j).num_unid_aten;
                                           l_oportunidadAhorro := l_PrecioCatalogoTotal;
                                           if l_oportunidadAhorro<0 then
                                              l_oportunidadAhorro:=0;
                                           end if;
                                        end if;
                                    ELSE
                                        -- Producto con descuento
                                        l_precioUnitario := r_detalle(j).val_prec_cata_unit_loca;
                                        l_precioTotal := r_detalle(j).val_prec_cata_tota_loca;
                                        l_porcDscto := r_detalle(j).val_porc_desc;
                                        l_descuento := r_detalle(j).val_impo_desc_tota_loca;
                                        l_descuento2 := r_detalle(j).val_prec_cata_tota_loca*(r_detalle(j).val_porc_desc/100);
                                        l_precioFacturado := r_detalle(j).val_prec_fact_tota_loca;
                                        if ln_desc_flete2='S' then
                                           l_precioFacturado:=r_detalle(j).val_prec_cata_tota_loca-l_descuento2;
                                        end if;
                                        if r_detalle(j).ind_grupo=0 then
                                           l_precioCatalogo := l_precioUnitario;
                                           l_PrecioCatalogoTotal := l_precioTotal;
                                           l_oportunidadAhorro := l_descuento;
                                            if ln_desc_flete2='S' then
                                               l_oportunidadAhorro := l_descuento2;
                                            end if;
                                        elsif r_detalle(j).ind_grupo=1 then
                                           l_precioCatalogo := nvl(r_detalle(j).imp_prec_publ,0);
                                           l_PrecioCatalogoTotal := nvl(r_detalle(j).imp_prec_publ,0)*r_detalle(j).num_unid_aten;
                                           l_oportunidadAhorro := l_PrecioCatalogoTotal-l_precioTotal;
                                           if l_oportunidadAhorro<0 then
                                              l_oportunidadAhorro:=0;
                                           end if;
                                        else
                                           l_precioCatalogo := 0;
                                           l_PrecioCatalogoTotal := 0;
                                           l_oportunidadAhorro := 0;
                                        end if;

                                        -- Pago posterior (fraccionado)
                                        --l_pagoPosterior := IMP_PKG_PROCE_COMPA.IMP_FN_CALCU_PAGO_POSTE(r_detalle(j).oid_soli_posi, l_codigoPeriodoSiguiente);
                                    END IF;
                                END IF;

                                if (orig_ante is null and r_detalle(j).val_codi_orig is not null) or (orig_ante is not null and orig_ante<>r_detalle(j).val_codi_orig and r_detalle(j).val_codi_orig is not null) then

                                 l_textoActual := '<txt>H' || '<t/>';

                                      -- Codigo de Venta
                                      l_textoActual := l_textoActual || r_detalle(j).val_codi_orig;
                                      l_textoActual := l_textoActual || '<t/>';

                                      -- Descripcion
                                      l_textoActual := l_textoActual || r_detalle(j).des_prod;
                                      l_textoActual := l_textoActual || '<tr/>';

                                      -- Unidades demandadas
                                      l_textoActual := l_textoActual || r_detalle(j).num_unid_orig;
                                      l_textoActual := l_textoActual || '<tr/>';

                                      -- Unidades atendidas
                                      l_textoActual := l_textoActual || '';
                                      l_textoActual := l_textoActual || '<tr/>';

                                      -- Precio unitario
                                      l_textoActual := l_textoActual || '';
                                      l_textoActual := l_textoActual || '<tr/>';

                                      -- Precio total
                                      l_textoActual := l_textoActual || '';
                                      l_textoActual := l_textoActual || '<tr/>';

                                      -- % Descuento
                                      l_textoActual := l_textoActual || '';
                                      l_textoActual := l_textoActual || '<tr/>';

                                      -- Importe Descuento
                                      l_textoActual := l_textoActual || '';
                                      l_textoActual := l_textoActual || '<tr/>';

                                      -- Precio Catalogo
                                      l_textoActual := l_textoActual || '';
                                      l_textoActual := l_textoActual || '<tr/>';

                                      -- Total Precio Catalogo
                                      l_textoActual := l_textoActual || '';
                                      l_textoActual := l_textoActual || '<tr/>';

                                      -- Oportunidad de Ahorro
                                      l_textoActual := l_textoActual || '';
                                      l_textoActual := l_textoActual || '<tr/>';

                                      -- Total con Descuento
                                      l_textoActual := l_textoActual || '';
                                      l_textoActual := l_textoActual || '<tr/>';

                                      -- Observaciones
                                      l_textoActual := l_textoActual || '';
                                      l_textoActual := l_textoActual || '</txt>';
                                      DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);


                                end if;

                                l_textoActual := '<txt>';

                                if r_detalle(j).val_codi_orig is not null then
                                   l_textoActual := l_textoActual || 'D<t/>';
                                end if;

                                -- Codigo de Venta
                                l_textoActual := l_textoActual || r_detalle(j).val_codi_vent;
                                l_textoActual := l_textoActual || '<t/>';

                                -- Descripcion
                                l_textoActual := l_textoActual || r_detalle(j).des_prod;
                                l_textoActual := l_textoActual || '<tr/>';

                                -- Unidades demandadas
                                l_textoActual := l_textoActual || r_detalle(j).num_unid_dema_real;
                                l_textoActual := l_textoActual || '<tr/>';

                                -- Unidades atendidas
                                l_textoActual := l_textoActual || r_detalle(j).num_unid_aten;
                                l_textoActual := l_textoActual || '<tr/>';

                                -- Precio unitario
                                l_textoActual := l_textoActual || trim(to_char(l_precioUnitario, l_formatoNumerico));
                                l_textoActual := l_textoActual || '<tr/>';

                                -- Precio total
                                l_textoActual := l_textoActual || trim(to_char(l_precioTotal, l_formatoNumerico));
                                l_textoActual := l_textoActual || '<tr/>';

                                -- % Descuento
                                l_textoActual := l_textoActual || l_porcDscto;
                                l_textoActual := l_textoActual || '<tr/>';

                                -- Importe Descuento
                                l_textoActual := l_textoActual || trim(to_char(l_descuento, l_formatoNumerico));
                                l_textoActual := l_textoActual || '<tr/>';

                                -- Precio Catalogo
                                l_textoActual := l_textoActual || trim(to_char(l_precioCatalogo, l_formatoNumerico));
                                l_textoActual := l_textoActual || '<tr/>';

                                -- Total Precio Catalogo
                                l_textoActual := l_textoActual || trim(to_char(l_precioCatalogoTotal, l_formatoNumerico));
                                l_textoActual := l_textoActual || '<tr/>';

                                -- Oportunidad de Ahorro
                                l_textoActual := l_textoActual || trim(to_char(l_oportunidadAhorro, l_formatoNumerico));
                                l_textoActual := l_textoActual || '<tr/>';

                                -- Total con Descuento
                                l_textoActual := l_textoActual || trim(to_char(l_precioFacturado, l_formatoNumerico));
                                l_textoActual := l_textoActual || '<tr/>';

                                -- Observaciones
                                l_textoActual := l_textoActual || r_detalle(j).val_obse;


                                if p_codigoPais<>'BOE' then
                                     l_textoActual := l_textoActual || '</txt>';
                                else
                                     l_textoActual := l_textoActual || '<tr/>';
                                end if;

                                if p_codigoPais='BOE' then
                                    -- Indicador Fuera de Caja
                                    l_textoActual := l_textoActual || r_detalle(j).ind_dent_fuer_caja_bols;
                                    l_textoActual := l_textoActual || '</txt>';
                                end if;

                                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                l_contadorDetalles := l_contadorDetalles + 1;
                                l_subtotalSolicitado := l_subtotalSolicitado + r_detalle(j).num_unid_dema_real;
                                l_subtotalAtendido := l_subtotalAtendido + r_detalle(j).num_unid_aten;
                                l_subtotalCatalogo := l_subtotalCatalogo + l_precioTotal;
                                l_subtotalDescuentos := l_subtotalDescuentos + l_descuento;
                                l_subtotalFacturado := l_subtotalFacturado + l_precioFacturado;
                                l_subtotalPrecioCatalogo := l_subtotalPrecioCatalogo + l_precioCatalogo;
                                l_subtotalPrecioCatalogoTotal := l_subtotalPrecioCatalogoTotal + l_PrecioCatalogoTotal;
                                l_subtotaloportunidadAhorro := l_subtotaloportunidadAhorro + l_oportunidadAhorro;

                                l_totalSolicitado := l_totalSolicitado + r_detalle(j).num_unid_dema_real;
                                l_totalAtendido := l_totalAtendido + r_detalle(j).num_unid_aten;
                                l_totalCatalogo := l_totalCatalogo + l_precioTotal;
                                l_totalDescuentos := l_totalDescuentos + l_descuento;
                                l_totalDescuentos2 := l_totalDescuentos2 + l_descuento2;
                                l_totalFacturado := l_totalFacturado + l_precioFacturado;
                                l_totalPrecioCatalogo := l_totalPrecioCatalogo + l_precioCatalogo;
                                l_totalPrecioCatalogoTotal := l_totalPrecioCatalogoTotal + l_PrecioCatalogoTotal;
                                l_totalOportunidadAhorro := l_totalOportunidadAhorro + l_oportunidadAhorro;

                                update ped_solic_posic xx set xx.val_prec_publ_unit_loca=r_detalle(j).imp_prec_publ--*r_detalle(j).num_unid_aten
                                where oid_soli_posi=r_detalle(j).oid_soli_posi;

                                update ped_solic_posic xx set xx.val_prec_publ_tota_loca=l_oportunidadAhorro
                                where oid_soli_posi=r_detalle(j).oid_soli_posi;

                                orig_ante:=r_detalle(j).val_codi_orig;

                            end if;
                            END LOOP;
                        END IF;

                    -- Cerramos el cursor de detalles

                    -- Subtotales
                    IF l_contadorDetalles > 0 THEN
                        l_textoActual := '<txt><t/><u>Sub Total:</u><tr/>';
                        l_textoActual := l_textoActual || l_subtotalSolicitado;
                        l_textoActual := l_textoActual || '<tr/>';
                        l_textoActual := l_textoActual || l_subtotalAtendido;
                        l_textoActual := l_textoActual || '<tr/><tr/>';
                        l_textoActual := l_textoActual || trim(to_char(l_subtotalCatalogo, l_formatoNumerico));
                        l_textoActual := l_textoActual || '<tr/><tr/>';
                        l_textoActual := l_textoActual || trim(to_char(l_subtotalDescuentos, l_formatoNumerico));
                        l_textoActual := l_textoActual || '<tr/>';
                        l_textoActual := l_textoActual || trim(to_char(l_subtotalPrecioCatalogo, l_formatoNumerico));
                        l_textoActual := l_textoActual || '<tr/>';
                        l_textoActual := l_textoActual || trim(to_char(l_subtotalPrecioCatalogoTotal, l_formatoNumerico));
                        l_textoActual := l_textoActual || '<tr/>';
                        l_textoActual := l_textoActual || trim(to_char(l_subtotaloportunidadAhorro, l_formatoNumerico));
                        l_textoActual := l_textoActual || '<tr/>';
                        l_textoActual := l_textoActual || trim(to_char(l_subtotalFacturado, l_formatoNumerico));
                        l_textoActual := l_textoActual || '<tr/>';
                        l_textoActual := l_textoActual || '</txt>';
                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                        if k=0 then
                           l_oportunidadCatalogo:=l_subtotaloportunidadAhorro;
                        elsif k=1 then
                           l_oportunidadRevista:=l_subtotaloportunidadAhorro;
                        end if;
                    END IF;
                    END LOOP;

                        EXIT WHEN c_detalle%NOTFOUND;
                END LOOP;
                    CLOSE c_detalle;

                -- Totales
                -- Al descuento le agregamos el redondeo

                if lv_redondeoDesc='S' then
                   l_totalDescuentos := l_totalDescuentos - r_consolidado(i).val_impo_redo_loca;
                end if;


                l_totalFacturado := l_totalCatalogo - l_totalDescuentos;

                l_textoActual := '<txt/><txt><t/><u>Total:</u><tr/>';
                l_textoActual := l_textoActual || l_totalSolicitado;
                l_textoActual := l_textoActual || '<tr/>';
                l_textoActual := l_textoActual || l_totalAtendido;
                l_textoActual := l_textoActual || '<tr/><tr/>';
                l_textoActual := l_textoActual || trim(to_char(l_totalCatalogo, l_formatoNumerico));
                l_textoActual := l_textoActual || '<tr/><tr/>';
                l_textoActual := l_textoActual || trim(to_char(l_totalDescuentos, l_formatoNumerico));
                l_textoActual := l_textoActual || '<tr/>';
                l_textoActual := l_textoActual || trim(to_char(l_totalPrecioCatalogo, l_formatoNumerico));
                l_textoActual := l_textoActual || '<tr/>';
                l_textoActual := l_textoActual || trim(to_char(l_totalPrecioCatalogoTotal, l_formatoNumerico));
                l_textoActual := l_textoActual || '<tr/>';
                l_textoActual := l_textoActual || trim(to_char(l_totalOportunidadAhorro, l_formatoNumerico));
                l_textoActual := l_textoActual || '<tr/>';
                l_textoActual := l_textoActual || trim(to_char(l_totalFacturado, l_formatoNumerico));
                l_textoActual := l_textoActual || '<tr/>';
                l_textoActual := l_textoActual || '</txt>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- Inicio Pie
                l_textoActual := l_textoActual || '<pie>';

               BEGIN
                SELECT to_char(cc.FEC_INIC,'dd/mm/yyyy')
                INTO l_fechaVencimiento
                FROM cra_crono cc, CRA_PERIO CP, seg_perio_corpo spc, CRA_ACTIV act
                WHERE cc.PERD_OID_PERI = cp.oid_peri
                  AND cp.peri_oid_peri = spc.oid_peri
                  AND spc.cod_peri = l_codigoPeriodoSiguiente
                  AND cc.ZZON_OID_ZONA = (select oid_zona from zon_zona where cod_zona=r_consolidado(i).cod_zona)
                  AND cc.CACT_OID_ACTI = act.OID_ACTI
                  AND act.COD_ACTI = 'CV';

               EXCEPTION
                WHEN OTHERS THEN
                 l_fechaVencimiento:=to_char(sysdate,'dd/mm/yyyy');
               END;

               -- Funcionalidad Flexipago
               IF lv_reemplazoOCS='S' THEN
                l_interesFlexipago  := NVL(CCC_PKG_GENER.CCC_FN_OBTIE_INTER_FLEXI_CAMPA(r_consolidado(i).oid_clie,p_codigoPeriodo),0);
                l_saldoFlexAnterior := ROUND(NVL(CCC_PKG_GENER.CCC_FN_OBTIE_MONTO_FLEXI_CAMPA(r_consolidado(i).oid_clie,p_codigoPeriodo),0),2);
               ELSE
                l_interesFlexipago :=0 ;
                l_saldoFlexAnterior := 0;
               END IF;

                -- Calculamos la percepcion
                l_percepcion := 0;
                IF l_indicadorPercepcion = 'S' THEN
                    -- No tomamos en cuenta el flete para el calculo de la percepcion
                    l_percepcion := round((l_totalCatalogo - l_totalDescuentos+nvl(r_consolidado(i).gastos,0)) * l_tasaImpuestoPercepcion / 100, 2);
                END IF;

                l_totalFactura := l_totalCatalogo - l_totalDescuentos + r_consolidado(i).val_impo_flet_tota_loca +  l_interesFlexipago + l_percepcion;


                -- En caso el pais este configurado para cobrar el impuesto por las promociones
                /*
                IF l_indicadorImpuestosGratis = 'S' THEN

                 l_totalAPagar := l_totalAPagar + l_totalImpuestosGratis;

                END IF;
                */

                -- Obtenemos los Pagos Posteriores
                SELECT NVL(SUM(imp_pend),0)
                INTO l_totalPagoPosterior
                FROM ccc_movim_cuent_corri mcc
                WHERE mcc.clie_oid_clie = r_consolidado(i).oid_clie
                  AND mcc.imp_pend > 0
                  AND mcc.perd_oid_peri > l_oidPeriodo
                  AND mcc.soca_oid_soli_cabe =r_consolidado(i).OID_SOLI_CABE ;


                select count(1) into l_cantidadOC from ped_solic_cabec
                where clie_oid_clie=r_consolidado(i).oid_clie
                and perd_oid_peri=l_oidPeriodo
                and ind_oc=1
                and ind_ts_no_conso=1
                and fec_fact is not null
                and tspa_oid_tipo_soli_pais in
                    (
                      select oid_tipo_soli_pais
                        from ped_tipo_solic_pais x,
                             ped_tipo_solic y
                       where x.tsol_oid_tipo_soli = y.oid_tipo_soli
                         and y.ind_soli_nega = 0
                         and y.ind_cons = 0
                    );

                -- Obtenemos el Cargo por Familia Segura
                IF l_indicadorCargoFamSegura='S' and l_cantidadOC<2 THEN

                 SELECT NVL(SUM(mcc.imp_movi),0)
                   INTO l_cargoFamSegura
                   FROM ccc_movim_cuent_corri mcc,
                        ccc_proce cp,
                        ccc_subpr su
                  WHERE mcc.clie_oid_clie = r_consolidado(i).oid_clie
                    AND mcc.perd_oid_peri = l_oidPeriodo
                    AND mcc.subp_oid_subp_crea = su.Oid_Subp
                    AND su.ccpr_Oid_Proc = cp.oid_proc
                    AND cp.cod_proc = 'CCC007'
                    AND su.cod_subp = 7;
                ELSE
                    l_cargoFamSegura:=0;
                END IF;

                /***************************************************************************
                 Total Factura - Pagos Posteriores + Cargo Familia Segura + Saldo Anterior +
                  Cuota Flexipago = Total Monto a Pagar
                 ***************************************************************************/

                l_totalAPagar := CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_CAMPA_ANTER(r_consolidado(i).oid_clie,l_codigoPeriodoSiguiente);

                IF lv_indiejec = 'S' THEN
                   -- Gastos Administrativos
                   l_totalFactura := l_totalFactura +
                                     NVL( r_consolidado(i).gastos,0 ) +
                                     NVL( r_consolidado(i).gastos2,0 );
                END IF;

                IF lv_indiejec2 is not null then
                   -- Gastos Administrativos
                   l_totalFactura:=l_totalFactura + nvl(r_consolidado(i).val_impo_impu_tota_loca,0);
                END IF;

                BEGIN
                  SELECT c.camp_ulti_pedi
                    INTO lscodperigastos
                    FROM ccc_detal_consu_gasto_admin c, ped_solic_cabec a
                   WHERE c.soca_oid_soli_cabe_deud = a.oid_soli_cabe
                     AND a.soca_oid_soli_cabe=r_consolidado(i).oid_soli_cabe;
                EXCEPTION WHEN OTHERS THEN
                  NULL;
                END;

                /********************************************************************
                Saldo Anterior = Total Monto a Pagar -
                                 Total Factura +
                                 Pagos Posteriores -
                                 Cargo Familia Segura -
                                 Cuota Flexipago
                **********************************************************************/

                l_saldoAnterior := l_totalAPagar +
                                   l_totalPagoPosterior -
                                   l_totalFactura -
                                   l_cargoFamSegura -
                                   l_saldoFlexAnterior;


                if ln_desc_flete = 'S' then
                   l_totalFactura := l_totalFactura -
                                     nvl(r_consolidado(i).val_impo_desc_flet,0);
                end if;

                -- En Peru el pie es diferente al resto de paises por las percepciones
                IF (l_indicadorPercepcion = 'S') THEN

                    -- Total Catalogo
                    l_textoActual := l_textoActual || '<linea1>' || trim(to_char(l_totalCatalogo, l_formatoNumerico)) || '</linea1>';
                    -- Total Descuentos (Incluimos el redondeo)
                    l_textoActual := l_textoActual || '<linea2>' || trim(to_char(l_totalDescuentos, l_formatoNumerico)) || '</linea2>';
                    -- Total Facturado
                    l_textoActual := l_textoActual || '<linea3>' || trim(to_char(l_totalCatalogo - l_totalDescuentos, l_formatoNumerico)) || '</linea3>';
                    -- Flete
                    l_textoActual := l_textoActual || '<linea4>' || trim(to_char(r_consolidado(i).val_impo_flet_tota_loca-r_consolidado(i).val_reca_flet_loca, l_formatoNumerico)) || '</linea4>';
                    -- Percepciones
                    l_textoActual := l_textoActual || '<linea5>' || trim(to_char(l_percepcion, l_formatoNumerico)) || '</linea5>';
                    -- Total Factura
                    l_textoActual := l_textoActual || '<linea6>' || trim(to_char(l_totalFactura, l_formatoNumerico)) || '</linea6>';
                    -- Pagos Posteriores
                    l_textoActual := l_textoActual || '<linea7>' || trim(to_char(l_totalPagoPosterior, l_formatoNumerico)) || '</linea7>';
                    -- Abono Atencion de Servicios
                    l_textoActual := l_textoActual || '<linea8>' || trim(to_char(l_cargoFamSegura, l_formatoNumerico)) || '</linea8>';
                    -- Saldo anterior
                    l_textoActual := l_textoActual || '<linea9>' || trim(to_char(l_saldoAnterior, l_formatoNumerico)) || '</linea9>';
                    -- Importe Total
                    l_textoActual := l_textoActual || '<linea10>' || trim(to_char(l_totalAPagar, l_formatoNumerico)) || '</linea10>';
                    -- Oportunidad Catalogo
                    l_textoActual := l_textoActual || '<linea11>' || trim(to_char(l_oportunidadCatalogo, l_formatoNumerico)) || '</linea11>';
                    -- Oportunidad Revista
                    l_textoActual := l_textoActual || '<linea12>' || trim(to_char(l_oportunidadRevista, l_formatoNumerico)) || '</linea12>';
                    -- Oportunidad Total
                    l_textoActual := l_textoActual || '<linea13>' || trim(to_char(l_oportunidadCatalogo+l_oportunidadRevista, l_formatoNumerico)) || '</linea13>';
                    -- Mensaje
                    l_textoActual := l_textoActual || '<linea14>' || trim(l_mensajeOportunidadAhorro) || '</linea14>';
                    -- Fecha Vencimiento
                    l_textoActual := l_textoActual || '<linea15>' || trim(l_fechaVencimiento) || '</linea15>';
                    -- Gastos Administrativos
                    l_textoActual := l_textoActual || '<linea16>' || trim(to_char(nvl(r_consolidado(i).gastos,0), l_formatoNumerico)) || '</linea16>';
                    -- Saldo Flexipago
                    l_textoActual := l_textoActual || '<linea17>' || trim(to_char(l_interesFlexipago, l_formatoNumerico)) || '</linea17>';
                    -- Cuota Flexipago
                    l_textoActual := l_textoActual || '<linea18>' || trim(to_char(l_saldoFlexAnterior, l_formatoNumerico)) || '</linea18>';

                    IF lsActividadParametro IS NOT NULL THEN
                        -- Recargo Flete
                        l_textoActual := l_textoActual || '<linea19>' || trim(to_char(nvl(r_consolidado(i).val_reca_flet_loca,0), l_formatoNumerico)) || '</linea19>';
                    END IF;

                    -- Descuento Cabecera
                    l_textoActual := l_textoActual || '<linea20>' || trim(to_char(nvl(r_consolidado(i).val_impo_desc_3_tota_loca,0), l_formatoNumerico)) || '</linea20>';
                    -- Campa?a de Gasto Administrativo
                    l_textoActual := l_textoActual || '<linea21>' || lscodperigastos || '</linea21>';
                    -- Descuento Cabecera2
                    l_textoActual := l_textoActual || '<linea22>' || trim(to_char(nvl(r_consolidado(i).val_impo_desc_4_tota_loca,0), l_formatoNumerico)) || '</linea22>';
                    -- Reclamos Pendientes
                    l_textoActual := l_textoActual || '<linea23>' || trim(to_char(nvl(r_consolidado(i).val_recl_pend,0), l_formatoNumerico)) || '</linea23>';
                ELSE
                    -- Total Catalogo
                    l_textoActual := l_textoActual || '<linea1>' || trim(to_char(l_totalCatalogo, l_formatoNumerico)) || '</linea1>';
                    -- Total Descuentos (Incluimos el redondeo)
                    l_textoActual := l_textoActual || '<linea2>' || trim(to_char(l_totalDescuentos, l_formatoNumerico)) || '</linea2>';
                    -- Total Facturado
                    l_textoActual := l_textoActual || '<linea3>' || trim(to_char(l_totalCatalogo - l_totalDescuentos, l_formatoNumerico)) || '</linea3>';
                    -- Flete
                    l_textoActual := l_textoActual || '<linea4>' || trim(to_char(r_consolidado(i).val_impo_flet_tota_loca, l_formatoNumerico)) || '</linea4>';
                    -- Total Factura
                    l_textoActual := l_textoActual || '<linea5>' || trim(to_char(l_totalFactura, l_formatoNumerico)) || '</linea5>';
                    -- Pagos Posteriores
                    l_textoActual := l_textoActual || '<linea6>' || trim(to_char(l_totalPagoPosterior, l_formatoNumerico)) || '</linea6>';
                    -- Abono Atencion de Servicios (No se visualiza)
                    l_textoActual := l_textoActual || '<linea7>' || trim(to_char(l_cargoFamSegura, l_formatoNumerico)) || '</linea7>';
                    -- Impuesto de productos gratis (Solo se visualiza en algunos paises)
                    l_textoActual := l_textoActual || '<linea8>' || trim(to_char(l_totalImpuestosGratis, l_formatoNumerico)) || '</linea8>';
                    -- Saldo anterior
                    l_textoActual := l_textoActual || '<linea9>' || trim(to_char(l_saldoAnterior, l_formatoNumerico)) || '</linea9>';
                    -- Importe Total
                    l_textoActual := l_textoActual || '<linea10>' || trim(to_char(l_totalAPagar, l_formatoNumerico)) || '</linea10>';
                    -- Oportunidad Catalogo
                    l_textoActual := l_textoActual || '<linea11>' || trim(to_char(l_oportunidadCatalogo, l_formatoNumerico)) || '</linea11>';
                    -- Oportunidad Revista
                    l_textoActual := l_textoActual || '<linea12>' || trim(to_char(l_oportunidadRevista, l_formatoNumerico)) || '</linea12>';
                    -- Oportunidad Total
                    l_textoActual := l_textoActual || '<linea13>' || trim(to_char(l_oportunidadCatalogo+l_oportunidadRevista, l_formatoNumerico)) || '</linea13>';
                    -- Mensaje
                    l_textoActual := l_textoActual || '<linea14>' || trim(l_mensajeOportunidadAhorro) || '</linea14>';
                    -- Fecha Vencimiento
                    l_textoActual := l_textoActual || '<linea15>' || trim(l_fechaVencimiento) || '</linea15>';
                    -- Impuesto
                    l_textoActual := l_textoActual || '<linea16>' || trim(to_char(nvl(r_consolidado(i).val_impo_impu_tota_loca,0), l_formatoNumerico)) || '</linea16>';
                    -- subtotal con flete
                    l_textoActual := l_textoActual || '<linea17>' || trim(to_char(l_totalCatalogo+nvl(r_consolidado(i).val_impo_flet_tota_loca,0), l_formatoNumerico)) || '</linea17>';
                    -- subtotal con flete e impuesto
                    l_textoActual := l_textoActual || '<linea18>' || trim(to_char(l_totalCatalogo+nvl(r_consolidado(i).val_impo_flet_tota_loca,0)+nvl(r_consolidado(i).val_impo_impu_tota_loca,0), l_formatoNumerico)) || '</linea18>';
                    -- descuento flete
                    l_textoActual := l_textoActual || '<linea19>' || trim(to_char(r_consolidado(i).val_impo_desc_flet, l_formatoNumerico)) || '</linea19>';
                    -- Descuento Cabecera
                    l_textoActual := l_textoActual || '<linea20>' || trim(to_char(nvl(r_consolidado(i).val_impo_desc_3_tota_loca,0), l_formatoNumerico)) || '</linea20>';
                      -- Gastos Administrativos
                      l_textoActual := l_textoActual || '<linea21>' || trim(to_char(nvl(r_consolidado(i).gastos,0)+nvl(r_consolidado(i).gastos2,0), l_formatoNumerico)) || '</linea21>';
                    -- Descuento Cabecera2
                    l_textoActual := l_textoActual || '<linea22>' || trim(to_char(nvl(r_consolidado(i).val_impo_desc_4_tota_loca,0), l_formatoNumerico)) || '</linea22>';
                    -- Reclamos Pendientes
                    l_textoActual := l_textoActual || '<linea23>' || trim(to_char(nvl(r_consolidado(i).val_recl_pend,0), l_formatoNumerico)) || '</linea23>';
                END IF;

                -- Fin Pie
                l_textoActual := l_textoActual || '</pie>';

                -- Fin Detalle de Factura
                l_textoActual := l_textoActual || '</detfac2>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                l_correlativo := l_correlativo + 1;

                update ped_solic_cabec xx set xx.val_gana_tota_loca=l_oportunidadCatalogo+l_oportunidadRevista
                where oid_soli_cabe=r_consolidado(i).oid_soli_cabe;

                commit;
            END LOOP;
        END IF;
        EXIT WHEN c_consolidados%NOTFOUND;
    END LOOP;
    -- Cerramos el cursor
    CLOSE c_consolidados;
 COMMIT;
END;

/**************************************************************************
Descripcion         : Proceso que obtiene la informacion para generar la
                      seccion de detalle de factura modificada por Proyecto UNO y
                      optimizada para lanzarse en paralelo por region.
Fecha Creacion      : 13/09/2011
Fecha Modificacion  : 13/09/2011
Autor               : Jorge Yepez Reyes - cahurtado@belcorp.biz
Parametros Entrada  :
    p_codigoPais            : Codigo del pais
    p_codigoPeriodo         : Periodo del cupon
    p_fechaFacturacion      : Fecha de Facturacion
Version             : Final (Alfa|Final)

IMP-145
***************************************************************************/
PROCEDURE IMP_PR_PROCE_DETAL_FACTU_CL(p_codigoPais IN VARCHAR2,
                                   p_codigoPeriodo IN VARCHAR2
                                   , p_fechaFacturacion IN VARCHAR2
                                   , p_oidZona NUMBER) IS

CURSOR c_consolidados(oidPeriodo NUMBER,
                      indicadorEnvioLarissa VARCHAR2,
                      numeroLoteFacturacion NUMBER,
                      oidRegi NUMBER) IS
SELECT SP.OID_PAIS,
       SP.COD_PAIS,
       MC.OID_CLIE,
       MC.COD_CLIE,
       MC.COD_DIGI_CTRL,
       MC.VAL_NOM1,
       MC.VAL_NOM2,
       MC.VAL_APE1,
       MC.VAL_APE2,
       MCI.NUM_DOCU_IDEN,
       CON.OID_SOLI_CABE,
       CON.VAL_NUME_SOLI,
       CON.FEC_FACT,
       CON.VAL_IMPO_FLET_TOTA_LOCA,
       CON.VAL_IMPO_REDO_LOCA,
       substr(des_pais, 1,length(des_pais)-decode(instr(des_pais,'ESIKA'),0,0,5)-decode(instr(des_pais,'LBEL'),0,0,4)) DES_PAIS,
       ZON.COD_ZONA,
       SEC.COD_SECC,
       TER.COD_TERR,
       SEC.NUM_SECU_FACT_DIAR,
       0 SALDO_A_FAVOR,
       --IMP_PKG_PROCE_COMPA.IMP_FN_CALCU_SALDO_FAVOR(CON.OID_SOLI_CABE) SALDO_A_FAVOR,
       --IMP_PKG_PROCE_COMPA.IMP_FN_CALCU_TOTAL_PAGO_POSTE(CON.OID_SOLI_CABE, '200902', 'A') SALDO_ANTERIOR
       0 SALDO_ANTERIOR,
       --con.val_tota_gast_admi GASTOS,
       con.val_impo_rete_desc,
       mcda.ind_impr_docu imprimefactel,
       mcda.ind_impr_pdoc imprimepaqdoc,
       con.val_tota_gast_admi GASTOS,
       (select val_nom1 || ' ' || val_nom2 from mae_clien where oid_clie=zon.clie_oid_clie)  nombreGZ,
       (select val_ape1 || ' ' || val_ape2 from mae_clien where oid_clie=zon.clie_oid_clie)  apellidosGZ,
       (select x.val_text_comu from mae_clien_comun x where clie_oid_clie=zon.clie_oid_clie and x.ticm_oid_tipo_comu=6 and rownum=1)  celular_GZ,
       (select x.val_text_comu from mae_clien_comun x where clie_oid_clie=zon.clie_oid_clie and x.ticm_oid_tipo_comu=3 and rownum=1)  correo_GZ,
       (select decode(y.val_sigl,'RUC',y.val_sigl,'DNI') from mae_tipo_docum y where y.oid_tipo_docu=mci.tdoc_oid_tipo_docu and rownum=1) tipo_docum
FROM MAE_CLIEN MC,
     MAE_CLIEN_IDENT MCI,
     mae_clien_datos_adici mcda,
     PED_SOLIC_CABEC CON,
     ZON_ZONA ZON,
     ZON_TERRI TER,
     ZON_TERRI_ADMIN ZTA,
     ZON_SECCI SEC,
     PED_SOLIC_CABEC_SECUE SEC,
     SEG_PAIS SP,
     BAS_PAIS BP
WHERE MC.OID_CLIE = CON.CLIE_OID_CLIE
  AND MC.OID_CLIE = MCI.CLIE_OID_CLIE
  AND MC.OID_CLIE = MCDA.CLIE_OID_CLIE
  AND MCI.VAL_IDEN_DOCU_PRIN = 1
  AND SP.OID_PAIS = CON.PAIS_OID_PAIS
  AND SP.COD_PAIS = BP.COD_PAIS
  AND CON.ZZON_OID_ZONA = ZON.OID_ZONA
  AND CON.TERR_OID_TERR = TER.OID_TERR
  AND CON.ZTAD_OID_TERR_ADMI = ZTA.OID_TERR_ADMI
  AND ZTA.ZSCC_OID_SECC = SEC.OID_SECC
  AND CON.OID_SOLI_CABE = SEC.SOCA_OID_SOLI_CABE
  AND CON.FEC_FACT = TO_DATE(p_fechaFacturacion, 'dd/mm/yyyy')
  AND CON.PERD_OID_PERI = oidPeriodo
  AND ZON.Oid_Zona = oidRegi
  and con.ind_inte_lari_gene = indicadorEnvioLarissa
  and (numeroLoteFacturacion is null or con.num_lote_fact = numeroLoteFacturacion)
  AND CON.NUM_UNID_ATEN_TOTA > 0
  AND EXISTS (
      SELECT NULL
      FROM INT_LAR_TIPO_SOLICI_PEDIDO_DIS L
      WHERE L.TSPA_OID_TIPO_SOLI_PAIS = CON.TSPA_OID_TIPO_SOLI_PAIS
  );
--ORDER BY MC.COD_CLIE,
--         CON.VAL_NUME_SOLI;

TYPE consolidadorecord IS RECORD (
    oid_pais                seg_pais.oid_pais%TYPE,
    cod_pais                seg_pais.cod_pais%TYPE,
    oid_clie                mae_clien.oid_clie%TYPE,
    cod_clie                mae_clien.cod_clie%TYPE,
    cod_digi_ctrl           mae_clien.cod_digi_ctrl%TYPE,
    val_nom1                mae_clien.val_nom1%TYPE,
    val_nom2                mae_clien.val_nom2%TYPE,
    val_ape1                mae_clien.val_ape1%TYPE,
    val_ape2                mae_clien.val_ape2%TYPE,
    num_docu_iden           mae_clien_ident.num_docu_iden%TYPE,
    oid_soli_cabe           ped_solic_cabec.oid_soli_cabe%TYPE,
    val_nume_soli           ped_solic_cabec.val_nume_soli%TYPE,
    fec_fact                ped_solic_cabec.fec_fact%TYPE,
    val_impo_flet_tota_loca ped_solic_cabec.val_impo_flet_tota_loca%TYPE,
    val_impo_redo_loca      ped_solic_cabec.val_impo_redo_loca%TYPE,
    des_pais                bas_pais.des_pais%TYPE,
    cod_zona                zon_zona.cod_zona%TYPE,
    cod_secc                zon_secci.cod_secc%TYPE,
    cod_terr                zon_terri.cod_terr%TYPE,
    num_secu_fact_diar      ped_solic_cabec_secue.num_secu_fact_diar%TYPE,
    saldoAnterior           ped_solic_posic.val_prec_cata_unit_loca%TYPE,
    saldoAFavor             ped_solic_cabec_secue.num_secu_fact_diar%TYPE,
--    gastos                  ped_solic_cabec.val_tota_gast_admi%TYPE,
    val_impo_rete_desc      ped_solic_cabec.val_impo_rete_desc%TYPE,
    imprimefactel           mae_clien_datos_adici.ind_impr_docu%TYPE,
    imprimepaqdoc           mae_clien_datos_adici.ind_impr_pdoc%TYPE,
    gastos                  ped_solic_cabec.val_tota_gast_admi%TYPE,
    nombresGZ               varchar2(100),
    apellidosGZ             varchar2(100),
    celularGZ               varchar2(100),
    correoGZ               varchar2(100),
    tipo_docum               varchar2(100)

);

TYPE consolidadotype IS TABLE OF consolidadorecord;
r_consolidado    consolidadotype;

CURSOR c_detalle(oidConsolidado NUMBER
,v_desPremioAgotado VARCHAR2
,v_desAgotado VARCHAR2
,v_desFaltLiq VARCHAR2
,v_desFaltAnun VARCHAR2
,v_desAnulMotMax VARCHAR2
,v_desPremio VARCHAR2
,v_desAtRec VARCHAR2
,v_desRecup VARCHAR2
,v_desReemp VARCHAR2
,v_desGratis VARCHAR2
,v_desPremioLET VARCHAR2
,v_desOfertNavid VARCHAR2
, v_oidestra  NUMBER
) IS
SELECT PSC.OID_SOLI_CABE,
       PSC.COPA_OID_PARA_GENE,
       PSC.ICTP_OID_TIPO_PROG,
       PSP.OID_SOLI_POSI,
       nvl(NVL(PSP.VAL_CODI_VENT, LPAD('0', 4 - LENGTH(PSP.VAL_CODI_VENT_FICT), '0') || PSP.VAL_CODI_VENT_FICT),'00000') AS VAL_CODI_VENT,
       --(SELECT VAL_I18N FROM GEN_I18N_SICC_PAIS WHERE ATTR_ENTI = 'MAE_PRODU' AND IDIO_OID_IDIO = 1 AND VAL_OID = PSP.PROD_OID_PROD) DES_PROD,
       imp_fn_desc_produ(p_codigoPais,psp.prod_oid_prod) DES_PROD,
       PSP.NUM_UNID_DEMA_REAL,
       PSP.NUM_UNID_ATEN,
       PSP.VAL_PREC_CATA_UNIT_LOCA,
       PSP.VAL_PREC_CATA_UNIT_LOCA * PSP.NUM_UNID_ATEN VAL_PREC_CATA_TOTA_LOCA,
       PSP.VAL_PREC_CONT_UNIT_LOCA,
       PSP.VAL_PREC_CONT_UNIT_LOCA * PSP.NUM_UNID_ATEN VAL_PREC_CONT_TOTA_LOCA,
       PSP.VAL_PREC_FACT_TOTA_LOCA,
       PSP.VAL_IMPO_DESC_TOTA_LOCA,
       NVL(PSP.VAL_PORC_DESC, 0) VAL_PORC_DESC,
       PSP.VAL_IMPO_IMPU_TOTA_LOCA,
       POD.IMP_PREC_PUBL,
       PSP.FOPA_OID_FORM_PAGO,
       psp.val_prec_sin_impu_tota_loca,
       decode(psc.modu_oid_modu,15,3,nvl(PTO.NUM_SECC_DETA_FACT,2)) IND_GRUP,
       psp.val_codi_orig,
       psp.num_unid_orig,
       psp.oid_nive_ofer,
       CASE
           WHEN exists (select 1 from pre_ofert x where x.oid_ofer=pod.ofer_oid_ofer and x.coes_oid_estr=v_oidestra)
               THEN v_desOfertNavid
           WHEN (NVL(PSP.NUM_UNID_DEMA_REAL, 0) - NVL(PSP.NUM_UNID_ATEN, 0) > 0) AND (PSC.COPA_OID_PARA_GENE IS NOT NULL OR PSC.ICTP_OID_TIPO_PROG IS NOT NULL)
               THEN v_desPremioAgotado
           WHEN NVL(PSP.NUM_UNID_DEMA_REAL, 0) - NVL(PSP.NUM_UNID_ATEN, 0) > 0
               THEN v_desAgotado
           WHEN NVL(PSP.NUM_UNID_DEMA_REAL, 0) = 0 AND NVL(PSP.NUM_UNID_ATEN, 0) = 0
                AND PSP.IND_LIMI_VENT = 1 AND (PTO.COD_TIPO_OFER = '21' OR PTO.COD_TIPO_OFER = '23')
               THEN v_desFaltLiq
           WHEN NVL(PSP.NUM_UNID_DEMA_REAL, 0) = 0 AND NVL(PSP.NUM_UNID_ATEN, 0) = 0
                AND PSP.IND_LIMI_VENT = 1
               THEN v_desFaltAnun
           WHEN NVL(PSP.NUM_UNID_DEMA_REAL, 0) = 0 AND NVL(PSP.NUM_UNID_ATEN, 0) = 0
                AND PSP.ESPO_OID_ESTA_POSI = 2 AND PSP.STPO_OID_SUBT_POSI = 21
               THEN v_desAnulMotMax
           /*WHEN NVL(PSP.NUM_UNID_DEMA_REAL, 0) = 0 AND NVL(PSP.NUM_UNID_ATEN, 0) = 0
                AND PSP.ESPO_OID_ESTA_POSI = 2
               THEN 'Vta.Exc'*/
          WHEN PST.COD_SUBT_POSI IS NOT NULL AND PST.COD_SUBT_POSI = 'RD'
               THEN v_desAgotado
           /*WHEN NVL(PSP.NUM_UNID_DEMA_REAL, 0) = 0 AND NVL(PSP.NUM_UNID_ATEN, 0) = 0
               THEN 'No.Aplica'*/
           WHEN (PSC.COPA_OID_PARA_GENE IS NOT NULL OR PSC.ICTP_OID_TIPO_PROG IS NOT NULL)
               THEN v_desPremio
           WHEN PSC.MODU_OID_MODU = '15'
               THEN v_desAtRec
           WHEN PTP.COD_TIPO_POSI IS NOT NULL AND PTP.COD_TIPO_POSI = 'RE'
               THEN v_desRecup
           WHEN PTP.COD_TIPO_POSI IS NOT NULL AND PTP.COD_TIPO_POSI = 'DA'
               THEN ''
           WHEN PST.COD_SUBT_POSI IS NOT NULL AND PST.COD_SUBT_POSI = 'RZ'
               THEN v_desReemp  || (
                 SELECT distinct POF.Val_Codi_Vent
                 FROM PRE_MATRI_FACTU PMF,
                      PRE_MATRI_REEMP PMR,
                      PRE_MATRI_FACTU PMF2,
                      PRE_OFERT_DETAL POF
                 WHERE PMF.OID_MATR_FACT = PMR.MAFA_OID_COD_REEM
                 AND PMF.OFDE_OID_DETA_OFER = PSP.OFDE_OID_DETA_OFER
                 AND PMR.MAFA_OID_COD_PPAL=PMF2.Oid_Matr_Fact
                 AND PMF2.Ofde_Oid_Deta_Ofer=POF.Oid_Deta_Ofer
                 AND exists (select 1 from ped_solic_posic where soca_oid_soli_cabe=PSC.OID_SOLI_CABE
                              and ofde_oid_deta_ofer=PSP.OFDE_Oid_Deta_Ofer)
                 and rownum=1
                 )
           WHEN PSP.VAL_PREC_CATA_UNIT_LOCA = 0
               THEN v_desGratis
           WHEN TS.Cod_Tipo_Soli = 'IPLC'
               THEN v_desPremioLET
           ELSE ''
        END AS VAL_OBSE
FROM PED_SOLIC_CABEC PSC,
     PED_SOLIC_POSIC PSP,
     PRE_OFERT_DETAL POD,
     PRE_TIPO_OFERT PTO,
     PED_TIPO_SOLIC_PAIS TSP,
     PED_TIPO_SOLIC TS,
     PED_TIPO_POSIC PTP,
     PED_SUBTI_POSIC PST--,
     --PED_CLASE_SOLIC PCS
WHERE PSC.OID_SOLI_CABE = PSP.SOCA_OID_SOLI_CABE
  AND PSC.TSPA_OID_TIPO_SOLI_PAIS = TSP.OID_TIPO_SOLI_PAIS
  AND TSP.TSOL_OID_TIPO_SOLI = TS.OID_TIPO_SOLI
  --AND TS.CLSO_OID_CLAS_SOLI = PCS.OID_CLAS_SOLI
  AND PSP.TPOS_OID_TIPO_POSI = PTP.OID_TIPO_POSI (+)
  AND PSP.Stpo_Oid_Subt_Posi = PST.Oid_Subt_Posi (+)
  AND PSP.OFDE_OID_DETA_OFER = POD.OID_DETA_OFER (+)
  AND POD.TOFE_OID_TIPO_OFER = PTO.OID_TIPO_OFER (+)
  AND PSP.ESPO_OID_ESTA_POSI != 2
  AND PSC.SOCA_OID_SOLI_CABE = oidConsolidado
--)
--WHERE IND_GRUP = indicadorGrupo
ORDER BY OID_NIVE_OFER, VAL_CODI_ORIG, VAL_CODI_VENT;

TYPE detallerecord IS RECORD (
    oid_soli_cabe               ped_solic_cabec.oid_soli_cabe%TYPE,
    copa_oid_para_gene          ped_solic_cabec.copa_oid_para_gene%TYPE,
    ictp_oid_tipo_prog          ped_solic_cabec.ictp_oid_tipo_prog%TYPE,
    oid_soli_posi               ped_solic_posic.oid_soli_posi%TYPE,
    val_codi_vent               varchar2(15),
    des_prod                    gen_i18n_sicc_pais.val_i18n%TYPE,
    num_unid_dema_real          ped_solic_posic.num_unid_dema_real%TYPE,
    num_unid_aten               ped_solic_posic.num_unid_aten%TYPE,
    val_prec_cata_unit_loca     ped_solic_posic.val_prec_cata_unit_loca%TYPE,
    val_prec_cata_tota_loca     ped_solic_posic.val_prec_cata_tota_loca%TYPE,
    val_prec_cont_unit_loca     ped_solic_posic.val_prec_cont_unit_loca%TYPE,
    val_prec_cont_tota_loca     ped_solic_posic.val_prec_cont_tota_loca%TYPE,
    val_prec_fact_tota_loca     ped_solic_posic.val_prec_fact_tota_loca%TYPE,
    val_impo_desc_tota_loca     ped_solic_posic.val_impo_desc_tota_loca%TYPE,
    val_porc_desc               ped_solic_posic.val_porc_desc%TYPE,
    val_impo_impu_tota_loca     ped_solic_posic.val_impo_impu_tota_loca%TYPE,
    imp_prec_publ               pre_ofert_detal.imp_prec_publ%TYPE,
    fopa_oid_form_pago          ped_solic_posic.fopa_oid_form_pago%TYPE,
    val_prec_sin_impu_tota_loca ped_solic_posic.val_prec_sin_impu_tota_loca%TYPE,
    ind_grupo                   pre_tipo_ofert.num_secc_deta_fact%TYPE,
    val_codi_orig               ped_solic_posic.val_codi_orig%TYPE,
    num_unid_orig               ped_solic_posic.num_unid_orig%TYPE,
    oid_nive_ofer               ped_solic_posic.oid_nive_ofer%TYPE,
    val_obse                    VARCHAR2(1000)
);

TYPE detalletype IS TABLE OF detallerecord;
r_detalle    detalletype;



CURSOR c_detalle1(oidConsolidado NUMBER) IS
SELECT NVL(PSP.VAL_PORC_DESC, 0) VAL_PORC_DESC,
       SUM(PSP.val_prec_sin_impu_tota_loca) VAL_PREC_CATA_TOTA_LOCA,
       SUM(PSP.VAL_IMPO_DESC_TOTA_LOCA)
FROM PED_SOLIC_CABEC PSC,
     PED_SOLIC_POSIC PSP
WHERE PSC.OID_SOLI_CABE = PSP.SOCA_OID_SOLI_CABE
  AND PSP.ESPO_OID_ESTA_POSI != 2
  AND PSC.SOCA_OID_SOLI_CABE = oidConsolidado
GROUP BY NVL(PSP.VAL_PORC_DESC, 0)
ORDER BY NVL(PSP.VAL_PORC_DESC, 0);

TYPE detallerecord1 IS RECORD (
    val_porc_desc               ped_solic_posic.val_porc_desc%TYPE,
    val_prec_cata_tota_loca     ped_solic_posic.val_prec_cata_tota_loca%TYPE,
    val_impo_desc_tota_loca     ped_solic_posic.val_impo_desc_tota_loca%TYPE
);

TYPE detalletype1 IS TABLE OF detallerecord1;
r_detalle1    detalletype1;


-- Variables locales
l_oidPais                   NUMBER;
l_oidPeriodo                NUMBER;
l_oidCanal                  NUMBER;
l_oidMarca                  NUMBER;
l_correlativo               NUMBER := 1;
l_contadorDetalles          NUMBER := 0;
l_porcDscto                 NUMBER;
l_precioUnitario            NUMBER(12, 2) := 0;
l_precioTotal               NUMBER(12, 2) := 0;
l_descuento                 NUMBER(12, 2) := 0;
l_precioFacturado           NUMBER(12, 2) := 0;
l_pagoPosterior             NUMBER(12, 2) := 0;
l_precioCatalogo            NUMBER(12, 2) := 0;
l_PrecioCatalogoTotal       NUMBER(12, 2) := 0;
l_oportunidadAhorro         NUMBER(12, 2) := 0;

l_subtotalSolicitado        NUMBER := 0;
l_subtotalAtendido          NUMBER := 0;
l_subtotalCatalogo          NUMBER(12, 2) := 0;
l_subtotalDescuentos        NUMBER(12, 2) := 0;
l_subtotalFacturado         NUMBER(12, 2) := 0;
l_subtotalPrecioCatalogo    NUMBER(12, 2) := 0;
l_subtotalPrecioCatalogoTotal       NUMBER(12, 2) := 0;
l_subtotaloportunidadAhorro NUMBER(12, 2) := 0;

l_totalSolicitado           NUMBER := 0;
l_totalAtendido             NUMBER := 0;
l_totalCatalogo             NUMBER(12, 2) := 0;
l_totalDescuentos           NUMBER(12, 2) := 0;
l_totalFacturado            NUMBER(12, 2) := 0;
l_totalPrecioCatalogo       NUMBER(12, 2) := 0;
l_totalPrecioCatalogoTotal  NUMBER(12, 2) := 0;
l_totalOportunidadAhorro    NUMBER(12, 2) := 0;
l_totalImpuestosGratis      NUMBER(12, 2) := 0;
l_saldoFavor                NUMBER(12, 2) := 0;
l_saldoAnterior             NUMBER(12, 2) := 0;
l_percepcion                NUMBER(12, 2) := 0;
l_totalFactura              NUMBER(12, 2) := 0;
l_cargoFamSegura            NUMBER(12, 2) := 0;
l_totalAPagar               NUMBER(12, 2) := 0;

l_oportunidadCatalogo       NUMBER(12, 2) := 0;
l_oportunidadRevista        NUMBER(12, 2) := 0;

l_indicadorEnvioLarissa     VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioLarissa');
l_indicadorEnvioUltimoLote  VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioUltimoLote');
l_indicadorCargoFamSegura   VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorCargoFamSegura'),'N');
l_indicadorImpuestosGratis  VARCHAR2(1) := 'N';
l_totalPagoPosterior        NUMBER(12, 2) := 0;
l_indicadorPercepcion       VARCHAR2(1) := 'N';
l_numeroLoteFacturacion     NUMBER;
l_clob                      CLOB;
l_textoActual               VARCHAR2(20000) := '';
l_codigoPeriodoSiguiente    VARCHAR2(6);
l_cambioLineaRetornoCarro   VARCHAR2(2) := CHR(13) || CHR(10);
l_formatoNumerico           VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'formatoNumerico'),'9999999G990D00');
l_tasaImpuestoPercepcion    NUMBER(5, 3);
--nuevo parametro

l_nombreSeccion1 VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'nombreSeccion1');
l_nombreSeccion2 VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'nombreSeccion2');
l_nombreSeccion3 VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'nombreSeccion3');
l_nombreSeccion4 VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'nombreSeccion4');

l_desPremioAgotado VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desPremioAgotado');
l_desAgotado VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desAgotado');
l_desFaltLiq VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desFaltLiq');
l_desFaltAnun VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desFaltAnun');
l_desAnulMotMax VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desAnulMotMax');
l_desPremio VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desPremio');
l_desAtRec VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desAtRec');
l_desRecup VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desRecup');
l_desReemp VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desReemp');
l_desGratis VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desGratis');
l_desPremioLET VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desPremioLET');
l_desOfertNavid VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desOfertNavid');


l_mensajeOportunidadAhorro VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'mensajeOportunidadAhorro');

l_fechaVencimiento VARCHAR2(100) := '';
l_fechaCV VARCHAR2(100) := '';

l_flagTitulo1     NUMBER:=0;
l_flagTitulo2     NUMBER:=0;
l_flagTitulo3     NUMBER:=0;
l_flagTitulo4     NUMBER:=0;

l_cantidadOC      NUMBER:=0;

lv_indiejec VARCHAR2(10):=nvl(sto_pkg_gener.sto_fn_obten_param_ocr(p_codigoPais,'STO_GASTO_ADMIN'),'N');

lv_reemplazoOCS VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorReemplazoOCS');

ln_oidestra NUMBER(10):= sto_pkg_gener.sto_fn_obten_param_ocr(p_codigoPais,'STO_ESTRA_NAVID');



lv_actividadConf VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','actividadConf'),'CV');

lv_actividadDesp VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','actividadDesp'),'DP');

lv_imprimepaqdoc VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','imprimepaqdoc'),'N');

lv_imprimefactel VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','imprimefactel'),'N');

lv_imprimenuevos VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','imprimenuevos'),'N');


ln_diasdesp NUMBER(10):= 0;
ln_diasdesp2 NUMBER(10):= 0;
ln_diasdesp3 NUMBER(10):= 0;


l_fechaDespacho VARCHAR2(100) := '';
l_fechaDespacho_2 VARCHAR2(100) := '';
l_fechaDespacho_3 VARCHAR2(100) := '';
l_fechaDespacho2 VARCHAR2(100) :=  nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indFechaDesp2'),'N');
l_fechaSigFact VARCHAR2(100) := '';
l_fechaSigFact2 VARCHAR2(100) := '';
l_fechaSigFact3 VARCHAR2(100) := '';

lsactividadparametro VARCHAR2(100):= sto_pkg_gener.sto_fn_obten_param_ocr(p_codigoPais,
                                                                 'STO_ACTIV_RECAR_FLET');


l_saldoFlexAnterior         NUMBER(12, 2) := 0;
l_interesFlexipago          NUMBER(12, 2) := 0;

orig_ante VARCHAR2(100):='';

BEGIN


    -- Obtenemos el OID del periodo
    l_oidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(p_codigoPais);
    l_oidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(CODIGO_CANAL);
    l_oidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(CODIGO_MARCA);
    l_oidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(p_codigoPeriodo, l_oidMarca, l_oidCanal);
    l_codigoPeriodoSiguiente := GEN_FN_CALCU_PERIO(p_codigoPeriodo, 1);

    --SOLO SE JECUTARA EL PROCESO SI PARAMETRO ES S, EN OTRO CASO NO SE EJECUTARA
    IF(l_indicadorEnvioUltimoLote = '1' OR l_indicadorEnvioUltimoLote = 'S') THEN

        BEGIN
            select max(cons.num_lote_fact)
            into l_numeroLoteFacturacion
            from ped_solic_cabec cons,
                 int_lar_tipo_solici_pedido_dis tspd
            where cons.perd_oid_peri = l_oidPeriodo
             and  cons.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
             and  cons.ind_ts_no_conso = 0
             and  (cons.ind_pedi_prue = 0 or cons.ind_pedi_prue is null)
             and  cons.tspa_oid_tipo_soli_pais = tspd.tspa_oid_tipo_soli_pais
             and  cons.pais_oid_pais = l_oidPais;
        EXCEPTION
          WHEN OTHERS THEN
              l_numeroLoteFacturacion := null;
        END;

    END IF;

    -- Obtenemos el valor del indicador de impuestos a los productos gratis
    select decode(count(*), 0, 'N', 'S')
    into l_indicadorImpuestosGratis
    from seg_pais p,
         seg_param_inter_pais i
    where p.oid_pais = i.pais_oid_pais
    and i.ind_impu_prod_grat = 1
    and p.cod_pais = p_codigoPais;

    -- Obtenemos el valor del indicador de percepciones
    select decode(count(*), 0, 'N', 'S')
    into l_indicadorPercepcion
    from fac_formu_perce ffp
    where ffp.pais_oid_pais = l_oidPais;

    -- Obtenemos el valor de la tasa de impuesto por percepciones
    IF l_indicadorPercepcion = 'S' THEN
        BEGIN
            select pti.val_tasa_impu
            into l_tasaImpuestoPercepcion
            from ped_tasa_impue pti
            where pti.pais_oid_pais = l_oidPais
            and pti.val_indi_impu = 'PER'; -- Codigo Percepciones

        EXCEPTION
        WHEN NO_DATA_FOUND THEN

            l_tasaImpuestoPercepcion := 0;

        END;
    END IF;

    -- Abrimos el cursor principal
    OPEN c_consolidados(l_oidPeriodo, l_indicadorEnvioLarissa, l_numeroLoteFacturacion, p_oidZona);
    LOOP
        FETCH c_consolidados BULK COLLECT
        INTO r_consolidado LIMIT w_filas;

        IF  r_consolidado.COUNT > 0 THEN
            FOR i IN r_consolidado.FIRST..r_consolidado.LAST
            LOOP

                l_flagTitulo1:=0;
                l_flagTitulo2:=0;
                l_flagTitulo3:=0;
                l_flagTitulo4:=0;
                -- Insertamos el registro y obtenemos la referencia al CLOB
                INSERT INTO IMP_PAQUE_DOCUM_DETAL_FACTU (
                COR_PADO_DEFA,
                COD_CONS,
                VAL_NUME_SOLI,
                XML_DETA_FACT)
                VALUES(
                r_consolidado(i).oid_soli_cabe,
                r_consolidado(i).cod_clie,
                r_consolidado(i).val_nume_soli,
                EMPTY_CLOB())
                RETURNING XML_DETA_FACT INTO l_clob;

                -- Inicio Detalle de Factura
                l_textoActual := '<detfac3>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- Inicio Cabecera
                l_textoActual := l_textoActual || '<blqcab>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                if lv_imprimefactel='S' then
                  -- Ind. Impresion
                  l_textoActual := l_textoActual || '<imprimefactel>' || r_consolidado(i).imprimefactel || '</imprimefactel>';

                  --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                end if;

                if lv_imprimepaqdoc='S' then
                  -- Ind. Impresion
                  l_textoActual := l_textoActual || '<imprimepaqdoc>' || r_consolidado(i).imprimepaqdoc || '</imprimepaqdoc>';

                  --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                end if;

                if lv_imprimenuevos='S' then

                  begin
                  SELECT to_char(cc.FEC_INIC,'dd/mm/yyyy'),to_char(cc.FEC_INIC,'dd/mm/yyyy'),to_char(cc.FEC_INIC,'dd/mm/yyyy')
                  INTO l_fechaDespacho,l_fechaDespacho_2,l_fechaDespacho_3
                  FROM cra_crono cc, CRA_PERIO CP, seg_perio_corpo spc, CRA_ACTIV act
                  WHERE cc.PERD_OID_PERI = cp.oid_peri
                    AND cp.peri_oid_peri = spc.oid_peri
                    AND spc.cod_peri = l_codigoPeriodoSiguiente
                    AND cc.ZZON_OID_ZONA = (select oid_zona from zon_zona where cod_zona=r_consolidado(i).cod_zona)

                    AND cc.CACT_OID_ACTI = act.OID_ACTI
                    AND act.COD_ACTI = lv_actividadDesp;
                  exception when others then
                    l_fechaDespacho:=trunc(sysdate);
                    l_fechaDespacho_2:=trunc(sysdate);
                    l_fechaDespacho_3:=trunc(sysdate);
                  end;

                  begin
                  SELECT to_char(cc.FEC_INIC,'dd/mm/yyyy')
                  INTO l_fechaSigFact
                  FROM cra_crono cc, CRA_PERIO CP, seg_perio_corpo spc, CRA_ACTIV act
                  WHERE cc.PERD_OID_PERI = cp.oid_peri
                    AND cp.peri_oid_peri = spc.oid_peri
                    AND spc.cod_peri = l_codigoPeriodoSiguiente
                    AND cc.ZZON_OID_ZONA = (select oid_zona from zon_zona where cod_zona=r_consolidado(i).cod_zona)

                    AND cc.CACT_OID_ACTI = act.OID_ACTI
                    AND act.COD_ACTI = 'FA';
                  exception when others then
                    l_fechaSigFact:=trunc(sysdate);
                  end;

                  begin
                  SELECT to_char(cc.FEC_INIC,'dd/mm/yyyy')
                  INTO l_fechaCV
                  FROM cra_crono cc, CRA_PERIO CP, seg_perio_corpo spc, CRA_ACTIV act
                  WHERE cc.PERD_OID_PERI = cp.oid_peri
                    AND cp.peri_oid_peri = spc.oid_peri
                    AND spc.cod_peri = l_codigoPeriodoSiguiente
                    AND cc.ZZON_OID_ZONA = (select oid_zona from zon_zona where cod_zona=r_consolidado(i).cod_zona)
                    AND cc.CACT_OID_ACTI = act.OID_ACTI
                    AND act.COD_ACTI = lv_actividadConf;
                  exception when others then
                    l_fechaCV:=trunc(sysdate);
                  end;

                  l_fechaSigFact2:=to_char(fac_pkg_proc.ped_fn_dev_dia_fact(l_codigoPeriodoSiguiente,r_consolidado(i).cod_zona,2),'dd/mm/yyyy');

                  l_fechaSigFact3:=to_char(fac_pkg_proc.ped_fn_dev_dia_fact(l_codigoPeriodoSiguiente,r_consolidado(i).cod_zona,3),'dd/mm/yyyy');

                  ln_diasdesp:=fac_pkg_proc.ped_fn_obt_dias_fecha_ent(r_consolidado(i).oid_soli_cabe,1,l_codigoPeriodoSiguiente, to_date(l_fechaSigFact,'dd/mm/yyyy'));
                  ln_diasdesp2:=fac_pkg_proc.ped_fn_obt_dias_fecha_ent(r_consolidado(i).oid_soli_cabe,2,l_codigoPeriodoSiguiente, to_date(l_fechaSigFact,'dd/mm/yyyy'));
                  ln_diasdesp3:=fac_pkg_proc.ped_fn_obt_dias_fecha_ent(r_consolidado(i).oid_soli_cabe,3,l_codigoPeriodoSiguiente, to_date(l_fechaSigFact,'dd/mm/yyyy'));


                  if l_fechaDespacho2='S' and ln_diasdesp>0 then
                    l_fechaDespacho:=to_char(to_date(l_fechaSigFact,'dd/mm/yyyy')+ln_diasdesp,'dd/mm/yyyy');
                  end if;

                  if l_fechaDespacho2='S' and ln_diasdesp>0 then
                    l_fechaDespacho_2:=to_char(to_date(l_fechaSigFact2,'dd/mm/yyyy')+ln_diasdesp2,'dd/mm/yyyy');
                  end if;

                  if l_fechaDespacho2='S' and ln_diasdesp>0 then
                    l_fechaDespacho_3:=to_char(to_date(l_fechaSigFact3,'dd/mm/yyyy')+ln_diasdesp3,'dd/mm/yyyy');
                  end if;

                  -- Nombre Consultora
                  l_textoActual := l_textoActual || '<nombres>' || r_consolidado(i).val_nom1 || ' ' || r_consolidado(i).val_nom2 || '</nombres>';
                  l_textoActual := l_textoActual || '<apellidos>' || r_consolidado(i).val_ape1 || ' ' || r_consolidado(i).val_ape2 || '</apellidos>';
                  l_textoActual := l_textoActual || '<nombresGZ>' || r_consolidado(i).nombresGZ || '</nombresGZ>';
                  l_textoActual := l_textoActual || '<apellidosGZ>' || r_consolidado(i).apellidosGZ || '</apellidosGZ>';
                  l_textoActual := l_textoActual || '<celularGZ>' || r_consolidado(i).celularGZ || '</celularGZ>';
                  l_textoActual := l_textoActual || '<correoGZ>' || r_consolidado(i).correoGZ || '</correoGZ>';
                  l_textoActual := l_textoActual || '<fechaCV>' || l_fechaCV || '</fechaCV>';
                  l_textoActual := l_textoActual || '<fechaReparto>' || l_fechaDespacho || '</fechaReparto>';
                  l_textoActual := l_textoActual || '<fechaReparto2>' || l_fechaDespacho_2 || '</fechaReparto2>';
                  l_textoActual := l_textoActual || '<fechaReparto3>' || l_fechaDespacho_3 || '</fechaReparto3>';
                  l_textoActual := l_textoActual || '<fechaSigFact>' || l_fechaSigFact || '</fechaSigFact>';
                  l_textoActual := l_textoActual || '<fechaSigFact2>' || l_fechaSigFact2 || '</fechaSigFact2>';
                  l_textoActual := l_textoActual || '<fechaSigFact3>' || l_fechaSigFact3 || '</fechaSigFact3>';
                  l_textoActual := l_textoActual || '<diasDesp>' || ln_diasdesp || '</diasDesp>';
                  l_textoActual := l_textoActual || '<tipo_docum>' || r_consolidado(i).tipo_docum || '</tipo_docum>';
                  l_textoActual := l_textoActual || imp_fn_xml_opor_ahor(p_codigoPeriodo,l_oidPeriodo,r_consolidado(i).oid_clie);
                end if;

                -- Numero Pedido
                l_textoActual := l_textoActual || '<numpedido>' || r_consolidado(i).val_nume_soli || '</numpedido>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- Descripcion Pais
                l_textoActual := l_textoActual || '<lugar>' || r_consolidado(i).des_pais || '</lugar>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- Dia
                l_textoActual := l_textoActual || '<dia>' || to_char(r_consolidado(i).fec_fact, 'dd') || '</dia>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- Mes
                l_textoActual := l_textoActual || '<mes>' || to_char(r_consolidado(i).fec_fact, 'mm') || '</mes>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- A?o
                l_textoActual := l_textoActual || '<ano>' || to_char(r_consolidado(i).fec_fact, 'yyyy') || '</ano>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- Codigo Cliente
                l_textoActual := l_textoActual || '<codconsultora>' || r_consolidado(i).cod_clie || '</codconsultora>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- Territorio Cliente
                l_textoActual := l_textoActual || '<territorio>';
                l_textoActual := l_textoActual || r_consolidado(i).cod_zona || '-';
                l_textoActual := l_textoActual || r_consolidado(i).cod_secc || '-';
                l_textoActual := l_textoActual || r_consolidado(i).cod_terr || '-';
                l_textoActual := l_textoActual || r_consolidado(i).num_secu_fact_diar;
                l_textoActual := l_textoActual || '</territorio>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- Documento de Identidad
                l_textoActual := l_textoActual || '<rifci>' || r_consolidado(i).num_docu_iden || '</rifci>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- Nombre Cliente
                l_textoActual := l_textoActual || '<nombre>';
                l_textoActual := l_textoActual || r_consolidado(i).val_nom1 || ' ';
                l_textoActual := l_textoActual || r_consolidado(i).val_nom2 || ' ';
                l_textoActual := l_textoActual || r_consolidado(i).val_ape1 || ' ';
                l_textoActual := l_textoActual || r_consolidado(i).val_ape2;
                l_textoActual := l_textoActual || '</nombre>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- Periodo
                l_textoActual := l_textoActual || '<campana>' || p_codigoPeriodo || '</campana>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- Fin Cabecera
                l_textoActual := l_textoActual || '</blqcab>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);


                -- Inicio Detalle
                l_textoActual := '<detalle>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                /*
                l_textoActual := '<nombreColumnas><txt>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                l_textoActual := l_nombreColumna1 || '<tc/>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                l_textoActual := l_nombreColumna2 || '<tc/>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                l_textoActual := l_nombreColumna3 || '<tc/>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                l_textoActual := l_nombreColumna4 || '<tc/>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                l_textoActual := l_nombreColumna5 || '<tc/>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                l_textoActual := l_nombreColumna6 || '<tc/>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                l_textoActual := l_nombreColumna7 || '<tc/>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                l_textoActual := l_nombreColumna8 || '<tc/>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                l_textoActual := l_nombreColumna9 || '<tc/>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                l_textoActual := l_nombreColumna10 || '<tc/>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                l_textoActual := l_nombreColumna11 || '<tc/>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                l_textoActual := '</txt></nombreColumnas>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                */
                -- Iniciamos los totales
                l_totalSolicitado := 0;
                l_totalAtendido := 0;
                l_totalDescuentos := 0;
                l_totalCatalogo := 0;
                l_totalDescuentos := 0;
                l_totalImpuestosGratis := 0;
                l_totalFacturado := 0;
                l_totalPrecioCatalogo := 0;
                l_totalPrecioCatalogoTotal := 0;
                l_totalOportunidadAhorro := 0;
                l_oportunidadRevista:=0;
                l_oportunidadCatalogo:=0;

                -- Iteramos por las secciones del detalle
                    OPEN c_detalle(r_consolidado(i).oid_soli_cabe
                      ,l_desPremioAgotado
                      ,l_desAgotado
                      ,l_desFaltLiq
                      ,l_desFaltAnun
                      ,l_desAnulMotMax
                      ,l_desPremio
                      ,l_desAtRec
                      ,l_desRecup
                      ,l_desReemp
                      ,l_desGratis
                      ,l_desPremioLET
                      ,l_desOfertNavid
                      ,ln_oidestra
                    );
                    LOOP
                        FETCH c_detalle BULK COLLECT
                        INTO r_detalle LIMIT w_filas;
                FOR k IN 0..3
                LOOP

                    -- Iniciamos los subtotales

                    l_contadorDetalles := 0;
                    l_subtotalSolicitado := 0;
                    l_subtotalAtendido := 0;
                    l_subtotalCatalogo := 0;
                    l_subtotalDescuentos := 0;
                    l_subtotalFacturado := 0;
                    l_subtotalPrecioCatalogo := 0;
                    l_subtotalPrecioCatalogoTotal := 0;
                    l_subtotaloportunidadAhorro := 0;
                    l_oportunidadAhorro := 0;

                    -- Mostramos el titulo


                        IF  r_detalle.COUNT > 0 THEN
                            FOR j IN r_detalle.FIRST..r_detalle.LAST
                            LOOP
                            if r_detalle(j).ind_grupo=k then

                                IF k = 0 and l_flagTitulo1=0 THEN
                                    l_textoActual := '<txt><u>' || l_nombreSeccion1 || '</u></txt><txt><t/></txt>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                    l_flagTitulo1:=1;
                                ELSIF k = 1 and l_flagTitulo2=0 THEN
                                    l_textoActual := '<txt/>';
                                    l_textoActual := l_textoActual || '<txt><u>' || l_nombreSeccion2 || '</u></txt><txt><t/></txt>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                    l_flagTitulo2:=1;
                                ELSIF k = 2 and l_flagTitulo3=0 THEN
                                    l_textoActual := '<txt/>';
                                    l_textoActual := l_textoActual || '<txt><u>' || l_nombreSeccion3 || '</u></txt><txt><t/></txt>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                    l_flagTitulo3:=1;
                                ELSIF k = 3 and l_flagTitulo4=0 THEN
                                    l_textoActual := '<txt/>';
                                    l_textoActual := l_textoActual || '<txt><u>' || l_nombreSeccion4 || '</u></txt><txt><t/></txt>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                    l_flagTitulo4:=1;
                                END IF;
                                -- Calculo de valores
                                IF r_detalle(j).copa_oid_para_gene IS NOT NULL
                                OR r_detalle(j).ictp_oid_tipo_prog IS NOT NULL THEN
                                    -- Si se trata de un premio
                                    l_precioUnitario := 0;
                                    l_precioTotal := 0;
                                    l_porcDscto := 100;
                                    l_descuento := 0;
                                    l_precioFacturado := 0;
                                    l_pagoPosterior := 0;
                                    l_oportunidadAhorro := 0;
                                    l_precioCatalogo := 0;
                                    l_PrecioCatalogoTotal := 0;
                                ELSE
                                    IF r_detalle(j).val_prec_cata_unit_loca = 0 THEN
                                        -- Si es un gratis
                                        l_precioUnitario := r_detalle(j).val_prec_cont_unit_loca;
                                        l_precioTotal := r_detalle(j).val_prec_cont_tota_loca;
                                        l_porcDscto := 100;
                                        l_descuento := r_detalle(j).val_prec_cont_tota_loca;
                                        l_precioFacturado := 0;
                                        l_totalImpuestosGratis := l_totalImpuestosGratis + r_detalle(j).val_impo_impu_tota_loca;
                                        l_pagoPosterior := 0;
                                        l_oportunidadAhorro := 0;
                                        l_precioCatalogo := 0;
                                        l_PrecioCatalogoTotal := 0;
                                        if r_detalle(j).ind_grupo=1 then
                                           l_precioCatalogo := nvl(r_detalle(j).imp_prec_publ,0);
                                           l_PrecioCatalogoTotal := nvl(r_detalle(j).imp_prec_publ,0)*r_detalle(j).num_unid_aten;
                                           l_oportunidadAhorro := l_PrecioCatalogoTotal;
                                           if l_oportunidadAhorro<0 then
                                              l_oportunidadAhorro:=0;
                                           end if;
                                        end if;
                                    ELSE
                                        -- Producto con descuento
                                        l_precioUnitario := r_detalle(j).val_prec_cata_unit_loca;
                                        l_precioTotal := r_detalle(j).val_prec_cata_tota_loca;
                                        l_porcDscto := r_detalle(j).val_porc_desc;
                                        l_descuento := r_detalle(j).val_impo_desc_tota_loca;
                                        l_precioFacturado := r_detalle(j).val_prec_fact_tota_loca;
                                        if r_detalle(j).ind_grupo=0 then
                                           l_precioCatalogo := l_precioUnitario;
                                           l_PrecioCatalogoTotal := l_precioTotal;
                                           l_oportunidadAhorro := l_descuento;
                                        elsif r_detalle(j).ind_grupo=1 then
                                           l_precioCatalogo := nvl(r_detalle(j).imp_prec_publ,0);
                                           l_PrecioCatalogoTotal := nvl(r_detalle(j).imp_prec_publ,0)*r_detalle(j).num_unid_aten;
                                           l_oportunidadAhorro := l_PrecioCatalogoTotal-l_precioTotal;
                                           if l_oportunidadAhorro<0 then
                                              l_oportunidadAhorro:=0;
                                           end if;
                                        else
                                           l_precioCatalogo := 0;
                                           l_PrecioCatalogoTotal := 0;
                                           l_oportunidadAhorro := 0;
                                        end if;

                                        -- Pago posterior (fraccionado)
                                        --l_pagoPosterior := IMP_PKG_PROCE_COMPA.IMP_FN_CALCU_PAGO_POSTE(r_detalle(j).oid_soli_posi, l_codigoPeriodoSiguiente);
                                    END IF;
                                END IF;

                                if (orig_ante is null and r_detalle(j).val_codi_orig is not null) or (orig_ante is not null and orig_ante<>r_detalle(j).val_codi_orig and r_detalle(j).val_codi_orig is not null) then

                                 l_textoActual := '<txt>H' || '<t/>';

                                      -- Codigo de Venta
                                      l_textoActual := l_textoActual || r_detalle(j).val_codi_orig;
                                      l_textoActual := l_textoActual || '<t/>';

                                      -- Descripcion
                                      l_textoActual := l_textoActual || r_detalle(j).des_prod;
                                      l_textoActual := l_textoActual || '<tr/>';

                                      -- Unidades demandadas
                                      l_textoActual := l_textoActual || r_detalle(j).num_unid_orig;
                                      l_textoActual := l_textoActual || '<tr/>';

                                      -- Unidades atendidas
                                      l_textoActual := l_textoActual || '';
                                      l_textoActual := l_textoActual || '<tr/>';

                                      -- Precio unitario
                                      l_textoActual := l_textoActual || '';
                                      l_textoActual := l_textoActual || '<tr/>';

                                      -- Precio total
                                      l_textoActual := l_textoActual || '';
                                      l_textoActual := l_textoActual || '<tr/>';

                                      -- % Descuento
                                      l_textoActual := l_textoActual || '';
                                      l_textoActual := l_textoActual || '<tr/>';

                                      -- Importe Descuento
                                      l_textoActual := l_textoActual || '';
                                      l_textoActual := l_textoActual || '<tr/>';

                                      -- Precio Catalogo
                                      l_textoActual := l_textoActual || '';
                                      l_textoActual := l_textoActual || '<tr/>';

                                      -- Total Precio Catalogo
                                      l_textoActual := l_textoActual || '';
                                      l_textoActual := l_textoActual || '<tr/>';

                                      -- Oportunidad de Ahorro
                                      l_textoActual := l_textoActual || '';
                                      l_textoActual := l_textoActual || '<tr/>';

                                      -- Total con Descuento
                                      l_textoActual := l_textoActual || '';
                                      l_textoActual := l_textoActual || '<tr/>';

                                      -- Observaciones
                                      l_textoActual := l_textoActual || '';
                                      l_textoActual := l_textoActual || '</txt>';
                                      DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);


                                end if;

                                l_textoActual := '<txt>';

                                if r_detalle(j).val_codi_orig is not null then
                                   l_textoActual := l_textoActual || 'D<t/>';
                                end if;

                                -- Codigo de Venta
                                l_textoActual := l_textoActual || r_detalle(j).val_codi_vent;
                                l_textoActual := l_textoActual || '<t/>';

                                -- Descripcion
                                l_textoActual := l_textoActual || r_detalle(j).des_prod;
                                l_textoActual := l_textoActual || '<tr/>';

                                -- Unidades demandadas
                                l_textoActual := l_textoActual || r_detalle(j).num_unid_dema_real;
                                l_textoActual := l_textoActual || '<tr/>';

                                -- Unidades atendidas
                                l_textoActual := l_textoActual || r_detalle(j).num_unid_aten;
                                l_textoActual := l_textoActual || '<tr/>';

                                -- Precio unitario
                                l_textoActual := l_textoActual || trim(to_char(l_precioUnitario, l_formatoNumerico));
                                l_textoActual := l_textoActual || '<tr/>';

                                -- Precio total
                                l_textoActual := l_textoActual || trim(to_char(l_precioTotal, l_formatoNumerico));
                                l_textoActual := l_textoActual || '<tr/>';

                                -- % Descuento
                                l_textoActual := l_textoActual || l_porcDscto;
                                l_textoActual := l_textoActual || '<tr/>';

                                -- Importe Descuento
                                l_textoActual := l_textoActual || trim(to_char(r_detalle(j).val_prec_sin_impu_tota_loca, l_formatoNumerico));
                                l_textoActual := l_textoActual || '<tr/>';

                                -- Precio Catalogo
                                l_textoActual := l_textoActual || trim(to_char(l_precioCatalogo, l_formatoNumerico));
                                l_textoActual := l_textoActual || '<tr/>';

                                -- Total Precio Catalogo
                                l_textoActual := l_textoActual || trim(to_char(l_precioCatalogoTotal, l_formatoNumerico));
                                l_textoActual := l_textoActual || '<tr/>';

                                -- Oportunidad de Ahorro
                                l_textoActual := l_textoActual || trim(to_char(l_oportunidadAhorro, l_formatoNumerico));
                                l_textoActual := l_textoActual || '<tr/>';

                                -- Total con Descuento
                                l_textoActual := l_textoActual || trim(to_char(l_precioFacturado, l_formatoNumerico));
                                l_textoActual := l_textoActual || '<tr/>';

                                -- Observaciones
                                l_textoActual := l_textoActual || r_detalle(j).val_obse;
                                l_textoActual := l_textoActual || '</txt>';
                                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                l_contadorDetalles := l_contadorDetalles + 1;
                                l_subtotalSolicitado := l_subtotalSolicitado + r_detalle(j).num_unid_dema_real;
                                l_subtotalAtendido := l_subtotalAtendido + r_detalle(j).num_unid_aten;
                                l_subtotalCatalogo := l_subtotalCatalogo + l_precioTotal;
                                l_subtotalDescuentos := l_subtotalDescuentos + l_descuento;
                                l_subtotalFacturado := l_subtotalFacturado + l_precioFacturado;
                                l_subtotalPrecioCatalogo := l_subtotalPrecioCatalogo + l_precioCatalogo;
                                l_subtotalPrecioCatalogoTotal := l_subtotalPrecioCatalogoTotal + l_PrecioCatalogoTotal;
                                l_subtotaloportunidadAhorro := l_subtotaloportunidadAhorro + l_oportunidadAhorro;

                                l_totalSolicitado := l_totalSolicitado + r_detalle(j).num_unid_dema_real;
                                l_totalAtendido := l_totalAtendido + r_detalle(j).num_unid_aten;
                                l_totalCatalogo := l_totalCatalogo + l_precioTotal;
                                l_totalDescuentos := l_totalDescuentos + l_descuento;
                                l_totalFacturado := l_totalFacturado + l_precioFacturado;
                                l_totalPrecioCatalogo := l_totalPrecioCatalogo + l_precioCatalogo;
                                l_totalPrecioCatalogoTotal := l_totalPrecioCatalogoTotal + l_PrecioCatalogoTotal;
                                l_totalOportunidadAhorro := l_totalOportunidadAhorro + l_oportunidadAhorro;

                                /*update ped_solic_posic xx set xx.val_prec_publ_unit_loca=r_detalle(j).imp_prec_publ--*r_detalle(j).num_unid_aten
                                where oid_soli_posi=r_detalle(j).oid_soli_posi;

                                update ped_solic_posic xx set xx.val_prec_publ_tota_loca=l_oportunidadAhorro
                                where oid_soli_posi=r_detalle(j).oid_soli_posi;*/
                            end if;

                                orig_ante:=r_detalle(j).val_codi_orig;

                            END LOOP;
                        END IF;

                    -- Cerramos el cursor de detalles

                    -- Subtotales
                    IF l_contadorDetalles > 0 THEN
                        l_textoActual := '<txt><t/><u>Sub Total:</u><tr/>';
                        l_textoActual := l_textoActual || l_subtotalSolicitado;
                        l_textoActual := l_textoActual || '<tr/>';
                        l_textoActual := l_textoActual || l_subtotalAtendido;
                        l_textoActual := l_textoActual || '<tr/><tr/>';
                        l_textoActual := l_textoActual || trim(to_char(l_subtotalCatalogo, l_formatoNumerico));
                        l_textoActual := l_textoActual || '<tr/><tr/>';
                        l_textoActual := l_textoActual || trim(to_char(l_subtotalDescuentos, l_formatoNumerico));
                        l_textoActual := l_textoActual || '<tr/>';
                        l_textoActual := l_textoActual || trim(to_char(l_subtotalPrecioCatalogo, l_formatoNumerico));
                        l_textoActual := l_textoActual || '<tr/>';
                        l_textoActual := l_textoActual || trim(to_char(l_subtotalPrecioCatalogoTotal, l_formatoNumerico));
                        l_textoActual := l_textoActual || '<tr/>';
                        l_textoActual := l_textoActual || trim(to_char(l_subtotaloportunidadAhorro, l_formatoNumerico));
                        l_textoActual := l_textoActual || '<tr/>';
                        l_textoActual := l_textoActual || trim(to_char(l_subtotalFacturado, l_formatoNumerico));
                        l_textoActual := l_textoActual || '<tr/>';
                        l_textoActual := l_textoActual || '</txt>';
                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                        if k=0 then
                           l_oportunidadCatalogo:=l_subtotaloportunidadAhorro;
                        elsif k=1 then
                           l_oportunidadRevista:=l_subtotaloportunidadAhorro;
                        end if;
                    END IF;
                    END LOOP;

                        EXIT WHEN c_detalle%NOTFOUND;
                END LOOP;
                    CLOSE c_detalle;



                -- Totales

                -- Al descuento le agregamos el redondeo
                --l_totalDescuentos := l_totalDescuentos - r_consolidado(i).val_impo_redo_loca;
                l_totalFacturado := l_totalCatalogo - l_totalDescuentos;

                l_textoActual := '<txt/><txt><t/><u>Total:</u><tr/>';
                l_textoActual := l_textoActual || l_totalSolicitado;
                l_textoActual := l_textoActual || '<tr/>';
                l_textoActual := l_textoActual || l_totalAtendido;
                l_textoActual := l_textoActual || '<tr/><tr/>';
                l_textoActual := l_textoActual || trim(to_char(l_totalCatalogo, l_formatoNumerico));
                l_textoActual := l_textoActual || '<tr/><tr/>';
                l_textoActual := l_textoActual || trim(to_char(l_totalDescuentos, l_formatoNumerico));
                l_textoActual := l_textoActual || '<tr/>';
                l_textoActual := l_textoActual || trim(to_char(l_totalPrecioCatalogo, l_formatoNumerico));
                l_textoActual := l_textoActual || '<tr/>';
                l_textoActual := l_textoActual || trim(to_char(l_totalPrecioCatalogoTotal, l_formatoNumerico));
                l_textoActual := l_textoActual || '<tr/>';
                l_textoActual := l_textoActual || trim(to_char(l_totalOportunidadAhorro, l_formatoNumerico));
                l_textoActual := l_textoActual || '<tr/>';
                l_textoActual := l_textoActual || trim(to_char(l_totalFacturado, l_formatoNumerico));
                l_textoActual := l_textoActual || '<tr/>';
                l_textoActual := l_textoActual || '</txt>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- Fin Detalle
                l_textoActual := '</detalle>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                -- Inicio Detalle1
                l_textoActual := l_textoActual || '<detalle1>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);


                -- Iteramos por las secciones del detalle
                    OPEN c_detalle1(r_consolidado(i).oid_soli_cabe);
                    LOOP
                        FETCH c_detalle1 BULK COLLECT
                        INTO r_detalle1 LIMIT w_filas;

                        IF  r_detalle1.COUNT > 0 THEN
                            FOR j IN r_detalle1.FIRST..r_detalle1.LAST
                            LOOP
                                l_textoActual := l_textoActual || '<txt>';

                                -- Porcentaje
                                l_textoActual := l_textoActual || r_detalle1(j).val_porc_desc;
                                l_textoActual := l_textoActual || '<t/>';

                                -- Total Catalogo
                                l_textoActual := l_textoActual || trim(to_char(r_detalle1(j).val_prec_cata_tota_loca, l_formatoNumerico));
                                l_textoActual := l_textoActual || '<tr/>';

                                -- Total Descuento
                                l_textoActual := l_textoActual || trim(to_char(r_detalle1(j).val_impo_desc_tota_loca, l_formatoNumerico));
                                l_textoActual := l_textoActual || '</txt>';
                                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                            end loop;
                         end if;

                        EXIT WHEN c_detalle1%NOTFOUND;
                END LOOP;
                    CLOSE c_detalle1;

                -- Fin Detalle1
                l_textoActual := l_textoActual || '</detalle1>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);


                -- Inicio Pie
                l_textoActual := l_textoActual || '<pie>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);


                -- Obtenemos los valores faltantes
                -- Saldo a favor al momento de pasar y fue aplicado
                --l_saldoFavor := IMP_PKG_PROCE_COMPA.IMP_FN_CALCU_SALDO_FAVOR(r_consolidado(i).oid_soli_cabe);
                --l_saldoFavor := r_consolidado(i).saldoAFavor;

                -- Saldo anterior
                --l_saldoAnterior := IMP_PKG_PROCE_COMPA.IMP_FN_CALCU_TOTAL_PAGO_POSTE(r_consolidado(i).oid_soli_cabe, l_codigoPeriodoSiguiente, 'A');
                --l_saldoAnterior := r_consolidado(i).saldoAnterior;

                -- Restamos al saldo anterior el saldo a favor
                --l_saldoAnterior := l_saldoAnterior - l_saldoFavor;

               BEGIN

                SELECT to_char(cc.FEC_INIC,'dd/mm/yyyy')
                INTO l_fechaVencimiento
                FROM cra_crono cc, CRA_PERIO CP, seg_perio_corpo spc, CRA_ACTIV act
                WHERE cc.PERD_OID_PERI = cp.oid_peri
                  AND cp.peri_oid_peri = spc.oid_peri
                  AND spc.cod_peri = p_codigoPeriodo
                  AND cc.ZZON_OID_ZONA = (select oid_zona from zon_zona where cod_zona=r_consolidado(i).cod_zona)
                  AND cc.CACT_OID_ACTI = act.OID_ACTI
                  AND act.COD_ACTI = 'V1';

                SELECT to_char(cc.FEC_INIC,'dd/mm/yyyy')
                INTO l_fechaCV
                FROM cra_crono cc, CRA_PERIO CP, seg_perio_corpo spc, CRA_ACTIV act
                WHERE cc.PERD_OID_PERI = cp.oid_peri
                  AND cp.peri_oid_peri = spc.oid_peri
                  AND spc.cod_peri = l_codigoPeriodoSiguiente
                  AND cc.ZZON_OID_ZONA = (select oid_zona from zon_zona where cod_zona=r_consolidado(i).cod_zona)
                  AND cc.CACT_OID_ACTI = act.OID_ACTI
                  AND act.COD_ACTI = 'RE';

               EXCEPTION
                WHEN OTHERS THEN
                 l_fechaVencimiento:=to_char(sysdate,'dd/mm/yyyy');
                 l_fechaCV:=to_char(sysdate,'dd/mm/yyyy');
               END;

                -- Calculamos la percepcion
                l_percepcion := 0;
                IF l_indicadorPercepcion = 'S' THEN
                    -- No tomamos en cuenta el flete para el calculo de la percepcion
                    l_percepcion := round((l_totalCatalogo - l_totalDescuentos+nvl(r_consolidado(i).gastos,0)) * l_tasaImpuestoPercepcion / 100, 2);
                END IF;

                l_totalFactura := l_totalCatalogo - l_totalDescuentos + r_consolidado(i).val_impo_flet_tota_loca + l_percepcion+nvl(r_consolidado(i).val_impo_rete_desc,0);


                -- En caso el pais este configurado para cobrar el impuesto por las promociones
                /*
                IF l_indicadorImpuestosGratis = 'S' THEN

                 l_totalAPagar := l_totalAPagar + l_totalImpuestosGratis;

                END IF;
                */

                -- Obtenemos los Pagos Posteriores
                SELECT NVL(SUM(imp_pend),0)
                INTO l_totalPagoPosterior
                FROM ccc_movim_cuent_corri
                WHERE clie_oid_clie = r_consolidado(i).oid_clie
                  AND imp_pend > 0
                  AND perd_oid_peri > l_oidPeriodo ;


                select count(1) into l_cantidadOC from ped_solic_cabec
                where clie_oid_clie=r_consolidado(i).oid_clie
                and perd_oid_peri=l_oidPeriodo
                and ind_oc=1
                and ind_ts_no_conso=1
                and fec_fact is not null
                and tspa_oid_tipo_soli_pais in
                (
                select oid_tipo_soli_pais from ped_tipo_solic_pais x, ped_tipo_solic y
                where x.tsol_oid_tipo_soli=y.oid_tipo_soli
                and y.ind_soli_nega=0 and y.ind_cons=0
                )
                ;



                -- Obtenemos el Cargos por Familia Segura
                IF l_indicadorCargoFamSegura='S' and l_cantidadOC<2 THEN

                 SELECT NVL(SUM(mcc.imp_movi),0)
                 INTO l_cargoFamSegura
                 FROM
                  ccc_movim_cuent_corri mcc,
                  ccc_proce cp,
                  ccc_subpr su
                 WHERE mcc.clie_oid_clie = r_consolidado(i).oid_clie
                   AND mcc.perd_oid_peri = l_oidPeriodo
                   AND mcc.subp_oid_subp_crea = su.Oid_Subp
                   AND su.ccpr_Oid_Proc = cp.oid_proc
                   AND cp.cod_proc = 'CCC007'
                   AND su.cod_subp = 7;
                 else
                     l_cargoFamSegura:=0;

                END IF;


                /********************************************************************
                Total Factura - Pagos Posteriores + Cargo Familia Segura
                + Saldo Anterior = Total Monto a Pagar
                **********************************************************************/
                l_totalAPagar := CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_CAMPA_ANTER(r_consolidado(i).oid_clie,l_codigoPeriodoSiguiente);

                IF lv_indiejec='S' THEN
                 -- Gastos Administrativos
                 --l_saldoAnterior:=l_saldoAnterior-nvl(r_consolidado(i).gastos,0);
                 l_totalFactura:=l_totalFactura+nvl(r_consolidado(i).gastos,0);
                END IF;

                l_saldoAnterior := l_totalAPagar - l_totalFactura - nvl(r_consolidado(i).gastos,0) - l_cargoFamSegura + l_totalPagoPosterior;



                -- En Peru el pie es diferente al resto de paises por las percepciones

                    -- Total Catalogo
                    l_textoActual := l_textoActual || '<linea1>' || trim(to_char(l_totalCatalogo, l_formatoNumerico)) || '</linea1>';
                    --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                    -- Total Descuentos (Incluimos el redondeo)
                    l_textoActual := l_textoActual || '<linea2>' || trim(to_char(l_totalDescuentos, l_formatoNumerico)) || '</linea2>';
                    --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                    -- Total Facturado
                    l_textoActual := l_textoActual || '<linea3>' || trim(to_char(l_totalCatalogo - l_totalDescuentos+nvl(r_consolidado(i).val_impo_rete_desc,0), l_formatoNumerico)) || '</linea3>';
                    --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                    -- Flete
                    l_textoActual := l_textoActual || '<linea4>' || trim(to_char(r_consolidado(i).val_impo_flet_tota_loca, l_formatoNumerico)) || '</linea4>';
                    --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                    -- Total Factura
                    l_textoActual := l_textoActual || '<linea5>' || trim(to_char(l_totalFactura, l_formatoNumerico)) || '</linea5>';
                    --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                    -- Pagos Posteriores
                    l_textoActual := l_textoActual || '<linea6>' || trim(to_char(l_totalPagoPosterior, l_formatoNumerico)) || '</linea6>';
                    --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                    -- Abono Atencion de Servicios (No se visualiza)
                    l_textoActual := l_textoActual || '<linea7>' || trim(to_char(l_cargoFamSegura, l_formatoNumerico)) || '</linea7>';
                    --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                    -- Impuesto de productos gratis (Solo se visualiza en algunos paises)
                    l_textoActual := l_textoActual || '<linea8>' || trim(to_char(nvl(r_consolidado(i).gastos,0), l_formatoNumerico)) || '</linea8>';
                    --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                    -- Saldo anterior
                    l_textoActual := l_textoActual || '<linea9>' || trim(to_char(l_saldoAnterior, l_formatoNumerico)) || '</linea9>';
                    --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                    -- Importe Total
                    l_textoActual := l_textoActual || '<linea10>' || trim(to_char(l_totalAPagar, l_formatoNumerico)) || '</linea10>';
                    --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                    -- Oportunidad Catalogo
                    l_textoActual := l_textoActual || '<linea11>' || trim(to_char(l_oportunidadCatalogo, l_formatoNumerico)) || '</linea11>';
                    --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                    -- Oportunidad Revista
                    l_textoActual := l_textoActual || '<linea12>' || trim(to_char(l_oportunidadRevista, l_formatoNumerico)) || '</linea12>';
                    --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                    -- Oportunidad Total
                    l_textoActual := l_textoActual || '<linea13>' || trim(to_char(l_oportunidadCatalogo+l_oportunidadRevista, l_formatoNumerico)) || '</linea13>';
                    --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                    -- Mensaje
                    l_textoActual := l_textoActual || '<linea14>' || trim(l_mensajeOportunidadAhorro) || '</linea14>';
                    --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                    -- Fecha Vencimiento
                    l_textoActual := l_textoActual || '<linea15>' || trim(l_fechaVencimiento) || '</linea15>';
                    --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                    -- Descuento Total
                    l_textoActual := l_textoActual || '<linea16>' || trim(to_char(l_totalDescuentos-nvl(r_consolidado(i).val_impo_rete_desc,0), l_formatoNumerico)) || '</linea16>';
                    --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                    -- Retencion
                    l_textoActual := l_textoActual || '<linea17>' || trim(to_char(nvl(r_consolidado(i).val_impo_rete_desc,0), l_formatoNumerico)) || '</linea17>';
                    --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                    -- Fecha CV
                    l_textoActual := l_textoActual || '<linea18>' || trim(to_char(nvl(r_consolidado(i).val_impo_rete_desc,0), l_formatoNumerico)) || '</linea18>';
                    --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                    -- Fecha V1
                    l_textoActual := l_textoActual || '<linea19>' || trim(l_fechaCV) || '</linea19>';
                    --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);


                l_interesFlexipago := NVL(CCC_PKG_GENER.CCC_FN_OBTIE_INTER_FLEXI_CAMPA(r_consolidado(i).oid_clie,p_codigoPeriodo),0);
                l_saldoFlexAnterior := ROUND(NVL(CCC_PKG_GENER.CCC_FN_OBTIE_MONTO_FLEXI_CAMPA(r_consolidado(i).oid_clie,p_codigoPeriodo),0),2);

                      -- Saldo Flexipago
                      l_textoActual := l_textoActual || '<linea20>' || trim(to_char(l_interesFlexipago, l_formatoNumerico)) || '</linea20>';
                      --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                      --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                      -- Cuota Flexipago
                      l_textoActual := l_textoActual || '<linea21>' || trim(to_char(l_saldoFlexAnterior, l_formatoNumerico)) || '</linea21>';
                      --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                      --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);


                -- Fin Pie
                l_textoActual := l_textoActual || '</pie>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                -- Fin Detalle de Factura
                l_textoActual := l_textoActual || '</detfac3>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                l_correlativo := l_correlativo + 1;

                update ped_solic_cabec xx set xx.val_gana_tota_loca=l_oportunidadCatalogo+l_oportunidadRevista
                where oid_soli_cabe=r_consolidado(i).oid_soli_cabe;


                commit;

            END LOOP;

        END IF;

        EXIT WHEN c_consolidados%NOTFOUND;
    END LOOP;
    -- Cerramos el cursor
    CLOSE c_consolidados;
 COMMIT;
END;

/**************************************************************************
Descripcion         : Proceso que obtiene la informacion para generar la
                      seccion de detalle de factura modificada por Proyecto UNO y
                      optimizada para lanzarse en paralelo por region.
Fecha Creacion      : 13/09/2011
Fecha Modificacion  : 13/09/2011
Autor               : Jorge Yepez Reyes - cahurtado@belcorp.biz
Parametros Entrada  :
    p_codigoPais            : Codigo del pais
    p_codigoPeriodo         : Periodo del cupon
    p_fechaFacturacion      : Fecha de Facturacion
Version             : Final (Alfa|Final)

IMP-167
***************************************************************************/
PROCEDURE IMP_PR_PROCE_DETAL_FACTU_CO(p_codigoPais IN VARCHAR2,
                                   p_codigoPeriodo IN VARCHAR2
                                   , p_fechaFacturacion IN VARCHAR2
                                   , p_oidZona NUMBER) IS

CURSOR c_consolidados(oidPeriodo NUMBER,
                      indicadorEnvioLarissa VARCHAR2,
                      numeroLoteFacturacion NUMBER,
                      oidRegi NUMBER) IS
SELECT SP.OID_PAIS,
       SP.COD_PAIS,
       MC.OID_CLIE,
       MC.COD_CLIE,
       MC.COD_DIGI_CTRL,
       MC.VAL_NOM1,
       MC.VAL_NOM2,
       MC.VAL_APE1,
       MC.VAL_APE2,
       MCI.NUM_DOCU_IDEN,
       CON.OID_SOLI_CABE,
       CON.VAL_NUME_SOLI,
       CON.FEC_FACT,
       CON.VAL_IMPO_FLET_TOTA_LOCA,
       CON.VAL_IMPO_IMPU_TOTA_LOCA,
       CON.VAL_IMPO_REDO_LOCA,
       substr(des_pais, 1,length(des_pais)-decode(instr(des_pais,'ESIKA'),0,0,5)-decode(instr(des_pais,'LBEL'),0,0,4)) DES_PAIS,
       ZON.COD_ZONA,
       SEC.COD_SECC,
       TER.COD_TERR,
       SEC.NUM_SECU_FACT_DIAR,
       0 SALDO_A_FAVOR,
       --IMP_PKG_PROCE_COMPA.IMP_FN_CALCU_SALDO_FAVOR(CON.OID_SOLI_CABE) SALDO_A_FAVOR,
       --IMP_PKG_PROCE_COMPA.IMP_FN_CALCU_TOTAL_PAGO_POSTE(CON.OID_SOLI_CABE, '200902', 'A') SALDO_ANTERIOR
       0 SALDO_ANTERIOR,
       con.val_tota_gast_admi GASTOS,
       con.val_impo_rete_desc,
       nvl(
       (select x.val_text_comu from mae_clien_comun x where clie_oid_clie=con.clie_oid_clie and x.ticm_oid_tipo_comu=1 and rownum=1),
       (select x.val_text_comu from mae_clien_comun x where clie_oid_clie=con.clie_oid_clie and x.ticm_oid_tipo_comu=6 and rownum=1)
       )  TEL_CLIE,
       (select val_nom1 || ' ' || val_nom2 || ' ' || val_ape1 || ' ' || val_ape2 from mae_clien where oid_clie=zon.clie_oid_clie)  GERENTE,
       (select x.val_text_comu from mae_clien_comun x where clie_oid_clie=zon.clie_oid_clie and x.ticm_oid_tipo_comu=6 and rownum=1)  CEL_GERENTE,
       (select val_nom1 || ' ' || val_nom2 || ' ' || val_ape1 || ' ' || val_ape2 from mae_clien where oid_clie=sec.clie_oid_clie)  EJECUTIVA,
       (select x.val_text_comu from mae_clien_comun x where clie_oid_clie=sec.clie_oid_clie and x.ticm_oid_tipo_comu=6 and rownum=1)  CEL_EJECUTIVA,
       (select num_docu_cont_inte from fac_docum_conta_cabec where soca_oid_soli_cabe=con.oid_soli_cabe and tido_oid_tipo_docu=1 and rownum=1) factura,
       mcda.ind_impr_pdoc
FROM MAE_CLIEN MC,
     MAE_CLIEN_IDENT MCI,
     mae_clien_datos_adici mcda,
     PED_SOLIC_CABEC CON,
     --fac_docum_conta_cabec fdc,
     ZON_ZONA ZON,
     ZON_TERRI TER,
     ZON_TERRI_ADMIN ZTA,
     ZON_SECCI SEC,
     PED_SOLIC_CABEC_SECUE SEC,
     SEG_PAIS SP,
     BAS_PAIS BP
WHERE MC.OID_CLIE = CON.CLIE_OID_CLIE
  and MC.OID_CLIE = mcda.CLIE_OID_CLIE
  --and con.oid_soli_cabe=fdc.soca_oid_soli_cabe
  AND MC.OID_CLIE = MCI.CLIE_OID_CLIE
  AND MCI.VAL_IDEN_DOCU_PRIN = 1
  AND SP.OID_PAIS = CON.PAIS_OID_PAIS
  AND SP.COD_PAIS = BP.COD_PAIS
  AND CON.ZZON_OID_ZONA = ZON.OID_ZONA
  AND CON.TERR_OID_TERR = TER.OID_TERR
  AND CON.ZTAD_OID_TERR_ADMI = ZTA.OID_TERR_ADMI
  AND ZTA.ZSCC_OID_SECC = SEC.OID_SECC
  AND CON.OID_SOLI_CABE = SEC.SOCA_OID_SOLI_CABE
  AND CON.FEC_FACT = TO_DATE(p_fechaFacturacion, 'dd/mm/yyyy')
  --and fdc.tido_oid_tipo_docu=1
  AND CON.PERD_OID_PERI = oidPeriodo
  AND ZON.Oid_Zona = oidRegi
  and con.ind_inte_lari_gene = indicadorEnvioLarissa
  and (numeroLoteFacturacion is null or con.num_lote_fact = numeroLoteFacturacion)
  AND CON.NUM_UNID_ATEN_TOTA > 0
  AND EXISTS (
      SELECT NULL
      FROM INT_LAR_TIPO_SOLICI_PEDIDO_DIS L
      WHERE L.TSPA_OID_TIPO_SOLI_PAIS = CON.TSPA_OID_TIPO_SOLI_PAIS
  );
--ORDER BY MC.COD_CLIE,
--         CON.VAL_NUME_SOLI;

TYPE consolidadorecord IS RECORD (
    oid_pais                seg_pais.oid_pais%TYPE,
    cod_pais                seg_pais.cod_pais%TYPE,
    oid_clie                mae_clien.oid_clie%TYPE,
    cod_clie                mae_clien.cod_clie%TYPE,
    cod_digi_ctrl           mae_clien.cod_digi_ctrl%TYPE,
    val_nom1                mae_clien.val_nom1%TYPE,
    val_nom2                mae_clien.val_nom2%TYPE,
    val_ape1                mae_clien.val_ape1%TYPE,
    val_ape2                mae_clien.val_ape2%TYPE,
    num_docu_iden           mae_clien_ident.num_docu_iden%TYPE,
    oid_soli_cabe           ped_solic_cabec.oid_soli_cabe%TYPE,
    val_nume_soli           ped_solic_cabec.val_nume_soli%TYPE,
    fec_fact                ped_solic_cabec.fec_fact%TYPE,
    val_impo_flet_tota_loca ped_solic_cabec.val_impo_flet_tota_loca%TYPE,
    val_impo_impu_tota_loca ped_solic_cabec.val_impo_impu_tota_loca%TYPE,
    val_impo_redo_loca      ped_solic_cabec.val_impo_redo_loca%TYPE,
    des_pais                bas_pais.des_pais%TYPE,
    cod_zona                zon_zona.cod_zona%TYPE,
    cod_secc                zon_secci.cod_secc%TYPE,
    cod_terr                zon_terri.cod_terr%TYPE,
    num_secu_fact_diar      ped_solic_cabec_secue.num_secu_fact_diar%TYPE,
    saldoAnterior           ped_solic_posic.val_prec_cata_unit_loca%TYPE,
    saldoAFavor             ped_solic_posic.val_prec_cata_unit_loca%TYPE,
    gastos                  ped_solic_cabec.val_tota_gast_admi%TYPE,
    val_impo_rete_desc      ped_solic_cabec.val_impo_rete_desc%TYPE,
    tel_clie                 varchar2(100),
    gerente                 varchar2(100),
    cel_gerente                 varchar2(100),
    ejecutiva               varchar2(100),
    cel_ejecutiva               varchar2(100),
    numero_factura              varchar2(100),
    ind_impr_pdoc               varchar2(10)
);

TYPE consolidadotype IS TABLE OF consolidadorecord;
r_consolidado    consolidadotype;

CURSOR c_detalle(oidConsolidado NUMBER
,v_desPremioAgotado VARCHAR2
,v_desAgotado VARCHAR2
,v_desFaltLiq VARCHAR2
,v_desFaltAnun VARCHAR2
,v_desAnulMotMax VARCHAR2
,v_desPremio VARCHAR2
,v_desAtRec VARCHAR2
,v_desRecup VARCHAR2
,v_desReemp VARCHAR2
,v_desGratis VARCHAR2
,v_desPremioLET VARCHAR2
,v_desOfertNavid VARCHAR2
, v_oidestra  NUMBER
) IS
SELECT PSC.OID_SOLI_CABE,
       PSC.COPA_OID_PARA_GENE,
       PSP.OID_SOLI_POSI,
       nvl(NVL(PSP.VAL_CODI_VENT, LPAD('0', 4 - LENGTH(PSP.VAL_CODI_VENT_FICT), '0') || PSP.VAL_CODI_VENT_FICT),'00000') AS VAL_CODI_VENT,
       --(SELECT VAL_I18N FROM GEN_I18N_SICC_PAIS WHERE ATTR_ENTI = 'MAE_PRODU' AND IDIO_OID_IDIO = 1 AND VAL_OID = PSP.PROD_OID_PROD) DES_PROD,
       imp_fn_desc_produ(p_codigoPais,psp.prod_oid_prod) DES_PROD,
       PSP.NUM_UNID_DEMA_REAL,
       PSP.NUM_UNID_ATEN,
       PSP.VAL_PREC_CATA_UNIT_LOCA,
       PSP.VAL_PREC_CATA_UNIT_LOCA * PSP.NUM_UNID_ATEN VAL_PREC_CATA_TOTA_LOCA,
       PSP.VAL_PREC_CONT_UNIT_LOCA,
       PSP.VAL_PREC_CONT_UNIT_LOCA * PSP.NUM_UNID_ATEN VAL_PREC_CONT_TOTA_LOCA,
       decode(psp.val_prec_cata_unit_loca,0,0,PSP.VAL_PREC_FACT_TOTA_LOCA) VAL_PREC_FACT_TOTA_LOCA,
       PSP.VAL_IMPO_DESC_TOTA_LOCA,
       decode(psp.num_unid_compr,0,0,decode(psp.val_prec_cata_unit_loca,0,0,NVL(PSP.VAL_PORC_DESC, 0))) VAL_PORC_DESC,
       PSP.VAL_IMPO_IMPU_TOTA_LOCA,
       case when psp.val_prec_cata_unit_loca=0 then psp.val_prec_cata_tota_loca
         when pto.num_secc_deta_fact=0 then psp.val_impo_desc_tota_loca
         when pto.num_secc_deta_fact=1 then psp.val_impo_desc_tota_loca--(nvl(pod.imp_prec_publ,0)*psp.num_unid_aten)-psp.val_prec_cata_tota_loca
         else psp.val_impo_desc_tota_loca
         end
         IMP_PREC_PUBL,
       psp.val_prec_sin_impu_tota_loca,
       psp.val_codi_orig,
       psp.num_unid_orig,
       psp.oid_nive_ofer,
       case
       when exists (select 1 from fac_tipo_ofert_exclu x
                 where x.tofe_oid_tipo_ofer=pod.tofe_oid_tipo_ofer) then (select lpad(cod_cata,2,'0') || des_cata from pre_catal where cod_cata='2')
       when nvl(mp.val_atri_3,0)=1 then '99CARGO POR USO FLEXIPAGO'
       when psp.val_prec_cata_unit_loca=0 then '97ADICIONALMENTE ESTA CAMPA?A GANASTE'
       else  lpad(cat.cod_cata,2,'0') || cat.des_cata
       end des_cata,
       sum(PSP.NUM_UNID_DEMA_REAL) over (partition by               case
       when exists (select 1 from fac_tipo_ofert_exclu x
                 where x.tofe_oid_tipo_ofer=pod.tofe_oid_tipo_ofer) then (select lpad(cod_cata,2,'0') || des_cata from pre_catal where cod_cata='2')
       when nvl(mp.val_atri_3,0)=1 then '99CARGO POR USO FLEXIPAGO'
       when psp.val_prec_cata_unit_loca=0 then '97ADICIONALMENTE ESTA CAMPA?A GANASTE'
       else  lpad(cat.cod_cata,2,'0') || cat.des_cata
       end) UNID_SOLI_CATA,
       sum(PSP.NUM_UNID_DEMA_REAL) over () UNID_SOLI_TOTA,
       sum(PSP.NUM_UNID_ATEN) over (partition by               case
       when exists (select 1 from fac_tipo_ofert_exclu x
                 where x.tofe_oid_tipo_ofer=pod.tofe_oid_tipo_ofer) then (select lpad(cod_cata,2,'0') || des_cata from pre_catal where cod_cata='2')
       when nvl(mp.val_atri_3,0)=1 then '99CARGO POR USO FLEXIPAGO'
       when psp.val_prec_cata_unit_loca=0 then '97ADICIONALMENTE ESTA CAMPA?A GANASTE'
       else  lpad(cat.cod_cata,2,'0') || cat.des_cata
       end) UNID_ATEN_CATA,
       sum(PSP.NUM_UNID_ATEN) over () UNID_ATEN_TOTA,
       sum(PSP.VAL_PREC_CATA_UNIT_LOCA * PSP.NUM_UNID_ATEN) over (partition by        case
       when exists (select 1 from fac_tipo_ofert_exclu x
                 where x.tofe_oid_tipo_ofer=pod.tofe_oid_tipo_ofer) then (select lpad(cod_cata,2,'0') || des_cata from pre_catal where cod_cata='2')
       when nvl(mp.val_atri_3,0)=1 then '99CARGO POR USO FLEXIPAGO'
       when psp.val_prec_cata_unit_loca=0 then '97ADICIONALMENTE ESTA CAMPA?A GANASTE'
       else  lpad(cat.cod_cata,2,'0') || cat.des_cata
       end) PREC_CATA,
       sum(PSP.VAL_PREC_CATA_UNIT_LOCA * PSP.NUM_UNID_ATEN) over () PREC_TOTA,
       sum(decode(psp.val_prec_cata_unit_loca,0,0,PSP.VAL_PREC_FACT_TOTA_LOCA)) over (partition by        case
       when exists (select 1 from fac_tipo_ofert_exclu x
                 where x.tofe_oid_tipo_ofer=pod.tofe_oid_tipo_ofer) then (select lpad(cod_cata,2,'0') || des_cata from pre_catal where cod_cata='2')
       when nvl(mp.val_atri_3,0)=1 then '99CARGO POR USO FLEXIPAGO'
       when psp.val_prec_cata_unit_loca=0 then '97ADICIONALMENTE ESTA CAMPA?A GANASTE'
       else  lpad(cat.cod_cata,2,'0') || cat.des_cata
       end) TOTA_CATA,
       sum(decode(psp.val_prec_cata_unit_loca,0,0,PSP.VAL_PREC_FACT_TOTA_LOCA)) over () TOTA_TOTA,
       sum(       case when psp.val_prec_cata_unit_loca=0 then psp.val_prec_cata_tota_loca
         when pto.num_secc_deta_fact=0 then psp.val_impo_desc_tota_loca
         when pto.num_secc_deta_fact=1 then psp.val_impo_desc_tota_loca--(nvl(pod.imp_prec_publ,0)*psp.num_unid_aten)-psp.val_prec_cata_tota_loca
         else psp.val_impo_desc_tota_loca
         end) over (partition by        case
       when exists (select 1 from fac_tipo_ofert_exclu x
                 where x.tofe_oid_tipo_ofer=pod.tofe_oid_tipo_ofer) then (select lpad(cod_cata,2,'0') || des_cata from pre_catal where cod_cata='2')
       when nvl(mp.val_atri_3,0)=1 then '99CARGO POR USO FLEXIPAGO'
       when psp.val_prec_cata_unit_loca=0 then '97ADICIONALMENTE ESTA CAMPA?A GANASTE'
       else  lpad(cat.cod_cata,2,'0') || cat.des_cata
       end) OPOR_CATA,
       sum(       case when psp.val_prec_cata_unit_loca=0 then psp.val_prec_cata_tota_loca
         when pto.num_secc_deta_fact=0 then psp.val_impo_desc_tota_loca
         when pto.num_secc_deta_fact=1 then psp.val_impo_desc_tota_loca--(nvl(pod.imp_prec_publ,0)*psp.num_unid_aten)-psp.val_prec_cata_tota_loca
         else psp.val_impo_desc_tota_loca
         end) over () OPOR_TOTA,
              CASE
           WHEN exists (select 1 from pre_ofert x where x.oid_ofer=pod.ofer_oid_ofer and x.coes_oid_estr=v_oidestra)
               THEN v_desOfertNavid
           WHEN (NVL(PSP.NUM_UNID_DEMA_REAL, 0) - NVL(PSP.NUM_UNID_ATEN, 0) > 0) AND (PSC.COPA_OID_PARA_GENE IS NOT NULL OR PSC.ICTP_OID_TIPO_PROG IS NOT NULL)
               THEN v_desPremioAgotado
           WHEN NVL(PSP.NUM_UNID_DEMA_REAL, 0) - NVL(PSP.NUM_UNID_ATEN, 0) > 0
               THEN v_desAgotado
           WHEN NVL(PSP.NUM_UNID_DEMA_REAL, 0) = 0 AND NVL(PSP.NUM_UNID_ATEN, 0) = 0
                AND PSP.IND_LIMI_VENT = 1 AND (PTO.COD_TIPO_OFER = '21' OR PTO.COD_TIPO_OFER = '23')
               THEN v_desFaltLiq
           WHEN NVL(PSP.NUM_UNID_DEMA_REAL, 0) = 0 AND NVL(PSP.NUM_UNID_ATEN, 0) = 0
                AND PSP.IND_LIMI_VENT = 1
               THEN v_desFaltAnun
           WHEN NVL(PSP.NUM_UNID_DEMA_REAL, 0) = 0 AND NVL(PSP.NUM_UNID_ATEN, 0) = 0
                AND PSP.STPO_OID_SUBT_POSI = 21
               THEN v_desAnulMotMax
           /*WHEN NVL(PSP.NUM_UNID_DEMA_REAL, 0) = 0 AND NVL(PSP.NUM_UNID_ATEN, 0) = 0
                AND PSP.ESPO_OID_ESTA_POSI = 2
               THEN 'Vta.Exc'*/
          WHEN PST.COD_SUBT_POSI IS NOT NULL AND PST.COD_SUBT_POSI = 'RD'
               THEN v_desAgotado
           WHEN NVL(PSP.NUM_UNID_DEMA_REAL, 0) = 0 AND NVL(PSP.NUM_UNID_ATEN, 0) = 0
               THEN 'No Cumple'
           WHEN (PSC.COPA_OID_PARA_GENE IS NOT NULL OR PSC.ICTP_OID_TIPO_PROG IS NOT NULL)
               THEN v_desPremio
           WHEN PSC.MODU_OID_MODU = '15'
               THEN v_desAtRec
           WHEN PTP.COD_TIPO_POSI IS NOT NULL AND PTP.COD_TIPO_POSI = 'RE'
               THEN v_desRecup
           WHEN PTP.COD_TIPO_POSI IS NOT NULL AND PTP.COD_TIPO_POSI = 'DA'
               THEN v_desReemp  || (
                 SELECT distinct POF.Val_Codi_Vent
                 FROM PRE_MATRI_FACTU PMF,
                      PRE_MATRI_CODIG_ALTER PMR,
                      PRE_MATRI_FACTU PMF2,
                      PRE_OFERT_DETAL POF
                 WHERE PMF.OID_MATR_FACT = PMR.MAFA_OID_COD_ALTE
                 AND PMF.OFDE_OID_DETA_OFER = PSP.OFDE_OID_DETA_OFER
                 AND PMR.MAFA_OID_COD_PPAL=PMF2.Oid_Matr_Fact
                 AND PMF2.Ofde_Oid_Deta_Ofer=POF.Oid_Deta_Ofer
                 AND exists (select 1 from ped_solic_posic where soca_oid_soli_cabe=PSC.OID_SOLI_CABE
                              and ofde_oid_deta_ofer=PSP.OFDE_Oid_Deta_Ofer)
                 and rownum=1
                 )
           WHEN PST.COD_SUBT_POSI IS NOT NULL AND PST.COD_SUBT_POSI = 'RZ'
               THEN v_desReemp  || (
                 SELECT distinct POF.Val_Codi_Vent
                 FROM PRE_MATRI_FACTU PMF,
                      PRE_MATRI_REEMP PMR,
                      PRE_MATRI_FACTU PMF2,
                      PRE_OFERT_DETAL POF
                 WHERE PMF.OID_MATR_FACT = PMR.MAFA_OID_COD_REEM
                 AND PMF.OFDE_OID_DETA_OFER = PSP.OFDE_OID_DETA_OFER
                 AND PMR.MAFA_OID_COD_PPAL=PMF2.Oid_Matr_Fact
                 AND PMF2.Ofde_Oid_Deta_Ofer=POF.Oid_Deta_Ofer
                 AND exists (select 1 from ped_solic_posic where soca_oid_soli_cabe=PSC.OID_SOLI_CABE
                              and ofde_oid_deta_ofer=PSP.OFDE_Oid_Deta_Ofer)
                 and rownum=1
                 )
           WHEN PSP.VAL_PREC_CATA_UNIT_LOCA = 0
               THEN v_desGratis
           /*WHEN TS.Cod_Tipo_Soli = 'IPLC'
               THEN v_desPremioLET*/
           ELSE ''
        END AS VAL_OBSE
FROM PED_SOLIC_CABEC PSC,
     PED_SOLIC_POSIC PSP,
     PRE_OFERT_DETAL POD,
     PED_SUBTI_POSIC PST,
     PED_TIPO_POSIC PTP,
     ped_tipo_solic_pais PTSP,
     ped_tipo_solic TS,
     PRE_TIPO_OFERT PTO,
     pre_catal CAT,
     mae_produ mp
     --PED_CLASE_SOLIC PCS
WHERE PSC.OID_SOLI_CABE = PSP.SOCA_OID_SOLI_CABE
  AND PSP.OFDE_OID_DETA_OFER = POD.OID_DETA_OFER(+)
  and pod.tofe_oid_tipo_ofer=pto.oid_tipo_ofer(+)
  AND PSP.ESPO_OID_ESTA_POSI != 2
  and psp.prod_oid_prod=mp.oid_prod
  and psc.tspa_oid_tipo_soli_pais=ptsp.oid_tipo_soli_pais
  and ptsp.tsol_oid_tipo_soli=ts.oid_tipo_soli
  and psp.stpo_oid_subt_posi=pst.oid_subt_posi(+)
  and psp.tpos_oid_tipo_posi=ptp.oid_tipo_posi(+)
  and pod.ocat_oid_catal=cat.oid_cata(+)
  and psc.modu_oid_modu<>13
  AND PSC.SOCA_OID_SOLI_CABE = oidConsolidado

--)
--WHERE IND_GRUP = indicadorGrupo
ORDER BY               case
       when exists (select 1 from fac_tipo_ofert_exclu x
                 where x.tofe_oid_tipo_ofer=pod.tofe_oid_tipo_ofer) then (select lpad(cod_cata,2,'0') || des_cata from pre_catal where cod_cata='2')
       when nvl(mp.val_atri_3,0)=1 then '99CARGO POR USO FLEXIPAGO'
       when psp.val_prec_cata_unit_loca=0 then '97ADICIONALMENTE ESTA CAMPA?A GANASTE'
       else  lpad(cat.cod_cata,2,'0') || cat.des_cata
       end, psp.oid_nive_ofer, psp.val_codi_orig, psp.val_codi_vent;


TYPE detallerecord IS RECORD (
    oid_soli_cabe               ped_solic_cabec.oid_soli_cabe%TYPE,
    copa_oid_para_gene          ped_solic_cabec.copa_oid_para_gene%TYPE,
    oid_soli_posi               ped_solic_posic.oid_soli_posi%TYPE,
    val_codi_vent               varchar2(15),
    des_prod                    gen_i18n_sicc_pais.val_i18n%TYPE,
    num_unid_dema_real          ped_solic_posic.num_unid_dema_real%TYPE,
    num_unid_aten               ped_solic_posic.num_unid_aten%TYPE,
    val_prec_cata_unit_loca     ped_solic_posic.val_prec_cata_unit_loca%TYPE,
    val_prec_cata_tota_loca     ped_solic_posic.val_prec_cata_tota_loca%TYPE,
    val_prec_cont_unit_loca     ped_solic_posic.val_prec_cont_unit_loca%TYPE,
    val_prec_cont_tota_loca     ped_solic_posic.val_prec_cont_tota_loca%TYPE,
    val_prec_fact_tota_loca     ped_solic_posic.val_prec_fact_tota_loca%TYPE,
    val_impo_desc_tota_loca     ped_solic_posic.val_impo_desc_tota_loca%TYPE,
    val_porc_desc               ped_solic_posic.val_porc_desc%TYPE,
    val_impo_impu_tota_loca     ped_solic_posic.val_impo_impu_tota_loca%TYPE,
    imp_prec_publ               pre_ofert_detal.imp_prec_publ%TYPE,
    val_prec_sin_impu_tota_loca ped_solic_posic.val_prec_sin_impu_tota_loca%TYPE,
    val_codi_orig               ped_solic_posic.val_codi_orig%TYPE,
    num_unid_orig               ped_solic_posic.num_unid_orig%TYPE,
    oid_nive_ofer               ped_solic_posic.oid_nive_ofer%TYPE,
    des_cata                    pre_catal.des_cata%TYPE,
    unid_soli_cata              ped_solic_posic.num_unid_dema_real%TYPE,
    unid_soli_tota              ped_solic_posic.num_unid_dema_real%TYPE,
    unid_aten_cata              ped_solic_posic.num_unid_dema_real%TYPE,
    unid_aten_tota              ped_solic_posic.num_unid_dema_real%TYPE,
    prec_cata                   ped_solic_posic.val_prec_sin_impu_tota_loca%TYPE,
    prec_tota                   ped_solic_posic.val_prec_sin_impu_tota_loca%TYPE,
    tota_cata                   ped_solic_posic.val_prec_sin_impu_tota_loca%TYPE,
    tota_tota                   ped_solic_posic.val_prec_sin_impu_tota_loca%TYPE,
    opor_cata                   ped_solic_posic.val_prec_sin_impu_tota_loca%TYPE,
    opor_tota                   ped_solic_posic.val_prec_sin_impu_tota_loca%TYPE,
    val_obse                    VARCHAR2(500)
);

TYPE detalletype IS TABLE OF detallerecord;
r_detalle    detalletype;


-- Variables locales
l_oidPais                   NUMBER;
l_oidPeriodo                NUMBER;
--l_oidCanal                  NUMBER;
--l_oidMarca                  NUMBER;
l_correlativo               NUMBER := 1;
l_contadorDetalles          NUMBER := 0;


l_totalFactura              NUMBER(12, 2) := 0;
l_totalAhorro               NUMBER(12, 2) := 0;

l_cargoFamSegura            NUMBER(12, 2) := 0;
l_totalAPagar               NUMBER(12, 2) := 0;
l_saldoAnterior             NUMBER(12, 2) := 0;
cupon                       NUMBER(12, 2) := 0;
saldo_cupon                 NUMBER(12, 2) := 0;
l_totalPagoPosterior        NUMBER(12, 2) := 0;


l_catalogo_anterior         VARCHAR2(100);

l_indicadorEnvioLarissa     VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioLarissa');
l_indicadorEnvioUltimoLote  VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioUltimoLote');
l_indicadorCargoFamSegura   VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorCargoFamSegura'),'N');
l_indicadorImpuestosGratis  VARCHAR2(1) := 'N';
l_indicadorPercepcion       VARCHAR2(1) := 'N';
l_numeroLoteFacturacion     NUMBER;
l_clob                      CLOB;
l_textoActual               VARCHAR2(20000) := '';
l_codigoPeriodoSiguiente    VARCHAR2(6);
l_cambioLineaRetornoCarro   VARCHAR2(2) := CHR(13) || CHR(10);
l_formatoNumerico           VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'formatoNumerico'),'999,999,999');
l_tasaImpuestoPercepcion    NUMBER(5, 3);
--nuevo parametro

l_mensajeOportunidadAhorro VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'mensajeOportunidadAhorro');

l_fechaVencimiento VARCHAR2(100) := '';
l_fechaCV VARCHAR2(100) := '';
l_fechaDespacho VARCHAR2(100) := '';
l_fechaDespacho2 VARCHAR2(100) := '';


l_cantidadOC      NUMBER:=0;

lv_indiejec VARCHAR2(10):=nvl(sto_pkg_gener.sto_fn_obten_param_ocr(p_codigoPais,'STO_GASTO_ADMIN'),'N');

lv_reemplazoOCS VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorReemplazoOCS');

lv_actividadConf VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','actividadConf'),'CV');

lv_actividadVenc VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','actividadVenc'),'CP');

lv_actividadDesp VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','actividadDesp'),'FA');

lv_imprimepaqdoc VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','imprimepaqdoc'),'N');


l_desRecupSemana VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desRecupSemana');
l_desPremioAgotado VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desPremioAgotado');
l_desAgotado VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desAgotado');
l_desFaltLiq VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desFaltLiq');
l_desFaltAnun VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desFaltAnun');
l_desAnulMotMax VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desAnulMotMax');
l_desPremio VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desPremio');
l_desAtRec VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desAtRec');
l_desRecup VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desRecup');
l_desReemp VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desReemp');
l_desGratis VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desGratis');
l_desPremioLET VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desPremioLET');
l_desOfertNavid VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desOfertNavid');

ln_oidestra NUMBER(10):= sto_pkg_gener.sto_fn_obten_param_ocr(p_codigoPais,'STO_ESTRA_NAVID');

--ln_diasdesp NUMBER(10):= 0;
ln_diasdesp2 NUMBER(10):= 0;
ln_diasdesp3 NUMBER(10):= 0;


--l_fechaDespacho VARCHAR2(100) := '';
l_fechaDespacho_2 VARCHAR2(100) := '';
l_fechaDespacho_3 VARCHAR2(100) := '';

--l_fechaDespacho2 VARCHAR2(100) :=  nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indFechaDesp2'),'N');

--l_fechaSigFact VARCHAR2(100) := '';
l_fechaSigFact2 VARCHAR2(100) := '';
l_fechaSigFact3 VARCHAR2(100) := '';

orig_ante VARCHAR2(100):='';


BEGIN



    -- Obtenemos el OID del periodo
    l_oidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(p_codigoPais);
    --l_oidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(CODIGO_CANAL);
    --l_oidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(CODIGO_MARCA);
    l_oidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(p_codigoPeriodo, 2003, 2001);
    l_codigoPeriodoSiguiente := GEN_FN_CALCU_PERIO(p_codigoPeriodo, 1);

    --SOLO SE JECUTARA EL PROCESO SI PARAMETRO ES S, EN OTRO CASO NO SE EJECUTARA
    IF(l_indicadorEnvioUltimoLote = '1' OR l_indicadorEnvioUltimoLote = 'S') THEN

        BEGIN
            select max(cons.num_lote_fact)
            into l_numeroLoteFacturacion
            from ped_solic_cabec cons,
                 int_lar_tipo_solici_pedido_dis tspd
            where cons.perd_oid_peri = l_oidPeriodo
             and  cons.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
             and  cons.ind_ts_no_conso = 0
             and  (cons.ind_pedi_prue = 0 or cons.ind_pedi_prue is null)
             and  cons.tspa_oid_tipo_soli_pais = tspd.tspa_oid_tipo_soli_pais
             and  cons.pais_oid_pais = l_oidPais;
        EXCEPTION
          WHEN OTHERS THEN
              l_numeroLoteFacturacion := null;
        END;

    END IF;



    --EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_PAQUE_DOCUM_DETAL_FACTU';
    --delete from IMP_PAQUE_DOCUM_DETAL_FACTU;

    -- Abrimos el cursor principal
    OPEN c_consolidados(l_oidPeriodo, l_indicadorEnvioLarissa, l_numeroLoteFacturacion, p_oidZona);
    LOOP
        FETCH c_consolidados BULK COLLECT
        INTO r_consolidado LIMIT w_filas;

        IF  r_consolidado.COUNT > 0 THEN
            FOR i IN r_consolidado.FIRST..r_consolidado.LAST
            LOOP

                --Se inicializa la variabla CLOB en Memoria
                DBMS_LOB.CREATETEMPORARY(l_clob, TRUE);

                -- Insertamos el registro y obtenemos la referencia al CLOB
                /*INSERT INTO IMP_PAQUE_DOCUM_DETAL_FACTU (
                COR_PADO_DEFA,
                COD_CONS,
                VAL_NUME_SOLI,
                XML_DETA_FACT)
                VALUES(
                r_consolidado(i).oid_soli_cabe,
                r_consolidado(i).cod_clie,
                r_consolidado(i).val_nume_soli,
                EMPTY_CLOB())
                RETURNING XML_DETA_FACT INTO l_clob;*/


               BEGIN

                SELECT to_char(cc.FEC_INIC,'dd/mm/yyyy')
                INTO l_fechaVencimiento
                FROM cra_crono cc, CRA_PERIO CP, seg_perio_corpo spc, CRA_ACTIV act
                WHERE cc.PERD_OID_PERI = cp.oid_peri
                  AND cp.peri_oid_peri = spc.oid_peri
                  AND spc.cod_peri = l_codigoPeriodoSiguiente
                  AND cc.ZZON_OID_ZONA = (select oid_zona from zon_zona where cod_zona=r_consolidado(i).cod_zona)

                  AND cc.CACT_OID_ACTI = act.OID_ACTI
                  AND act.COD_ACTI = lv_actividadVenc;

               EXCEPTION
                WHEN OTHERS THEN
                 l_fechaVencimiento:=to_char(sysdate,'dd/mm/yyyy');
               END;

               BEGIN

                --<DEMORA> ESTA FUNCION fac_pkg_proc.ped_fn_obt_dias_fecha_ent demora bastante EN PROD
                SELECT to_char(cc.fec_inic,'dd/mm/yyyy'), to_char(cc.FEC_INIC+fac_pkg_proc.ped_fn_obt_dias_fecha_ent(r_consolidado(i).oid_soli_cabe,1,l_codigoPeriodoSiguiente, cc.FEC_INIC),'dd/mm/yyyy')
                INTO l_fechaDespacho2, l_fechaDespacho
                FROM cra_crono cc, CRA_PERIO CP, seg_perio_corpo spc, CRA_ACTIV act
                WHERE cc.PERD_OID_PERI = cp.oid_peri
                  AND cp.peri_oid_peri = spc.oid_peri
                  AND spc.cod_peri = l_codigoPeriodoSiguiente
                  AND cc.ZZON_OID_ZONA = (select oid_zona from zon_zona where cod_zona=r_consolidado(i).cod_zona)

                  AND cc.CACT_OID_ACTI = act.OID_ACTI
                  AND act.COD_ACTI = lv_actividadDesp;

		              l_fechaSigFact2:=to_char(fac_pkg_proc.ped_fn_dev_dia_fact(l_codigoPeriodoSiguiente,r_consolidado(i).cod_zona,2),'dd/mm/yyyy');

                  l_fechaSigFact3:=to_char(fac_pkg_proc.ped_fn_dev_dia_fact(l_codigoPeriodoSiguiente,r_consolidado(i).cod_zona,3),'dd/mm/yyyy');

                  ln_diasdesp2:=fac_pkg_proc.ped_fn_obt_dias_fecha_ent(r_consolidado(i).oid_soli_cabe,2,l_codigoPeriodoSiguiente, to_date(l_fechaDespacho2,'dd/mm/yyyy'));
                  ln_diasdesp3:=fac_pkg_proc.ped_fn_obt_dias_fecha_ent(r_consolidado(i).oid_soli_cabe,3,l_codigoPeriodoSiguiente, to_date(l_fechaDespacho2,'dd/mm/yyyy'));




                  --if l_fechaDespacho2='S' and ln_diasdesp>0 then
                    l_fechaDespacho_2:=to_char(to_date(l_fechaSigFact2,'dd/mm/yyyy')+ln_diasdesp2,'dd/mm/yyyy');
                  --end if;

                  --if l_fechaDespacho2='S' and ln_diasdesp>0 then
                    l_fechaDespacho_3:=to_char(to_date(l_fechaSigFact3,'dd/mm/yyyy')+ln_diasdesp3,'dd/mm/yyyy');
                  --end if;


               update ped_segui_pedid a
               set a.fec_sigu_entr=to_date(l_fechaDespacho,'dd/mm/yyyy'),
               a.fec_sigu_fact=to_date(l_fechaDespacho2 ,'dd/mm/yyyy')
               where a.soca_oid_soli_cabe=r_consolidado(i).oid_soli_cabe;

               EXCEPTION
                WHEN OTHERS THEN
                 l_fechaDespacho:=to_char(sysdate,'dd/mm/yyyy');
                 l_fechaDespacho2:=to_char(sysdate,'dd/mm/yyyy');
                 l_fechaSigFact2:=to_char(sysdate,'dd/mm/yyyy');
                 l_fechaSigFact3:=to_char(sysdate,'dd/mm/yyyy');
                 l_fechaDespacho_2:=to_char(sysdate,'dd/mm/yyyy');
                 l_fechaDespacho_3:=to_char(sysdate,'dd/mm/yyyy');
               END;


               BEGIN

                SELECT to_char(cc.FEC_INIC,'dd/mm/yyyy')
                INTO l_fechaCV
                FROM cra_crono cc, CRA_PERIO CP, seg_perio_corpo spc, CRA_ACTIV act
                WHERE cc.PERD_OID_PERI = cp.oid_peri
                  AND cp.peri_oid_peri = spc.oid_peri
                  AND spc.cod_peri = l_codigoPeriodoSiguiente
                  AND cc.ZZON_OID_ZONA = (select oid_zona from zon_zona where cod_zona=r_consolidado(i).cod_zona)
                  AND cc.CACT_OID_ACTI = act.OID_ACTI
                  AND act.COD_ACTI = lv_actividadConf;


               EXCEPTION
                WHEN OTHERS THEN
                 l_fechaCV:=to_char(sysdate,'dd/mm/yyyy');
               END;

                -- Inicio Detalle de Factura
                l_textoActual := '<detfac4>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                l_textoActual := l_textoActual || IMP_PKG_PROCE_COMPA.IMP_FN_ETIQU_CLASI_BOLET_DESPA( r_consolidado(i).cod_clie) ||
                IMP_PKG_PROCE_COMPA.IMP_FN_ETIQU_ESTAT_BOLET_DESPA( r_consolidado(i).cod_clie);

                -- Inicio Cabecera
                l_textoActual := l_textoActual || '<blqcab>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- Numero Pedido
                l_textoActual := l_textoActual || '<numpedido>' || r_consolidado(i).val_nume_soli || '</numpedido>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- Descripcion Pais
                l_textoActual := l_textoActual || '<lugar>' || r_consolidado(i).des_pais || '</lugar>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- Dia
                l_textoActual := l_textoActual || '<dia>' || to_char(r_consolidado(i).fec_fact, 'dd') || '</dia>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- Mes
                l_textoActual := l_textoActual || '<mes>' || to_char(r_consolidado(i).fec_fact, 'mm') || '</mes>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- A?o
                l_textoActual := l_textoActual || '<ano>' || to_char(r_consolidado(i).fec_fact, 'yyyy') || '</ano>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- Codigo Cliente
                l_textoActual := l_textoActual || '<codconsultora>' || r_consolidado(i).cod_clie || '</codconsultora>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- Territorio Cliente
                l_textoActual := l_textoActual || '<territorio>';
                l_textoActual := l_textoActual || substr(r_consolidado(i).cod_zona,1,2);
                l_textoActual := l_textoActual || r_consolidado(i).cod_zona;
                l_textoActual := l_textoActual || r_consolidado(i).cod_secc;
                --l_textoActual := l_textoActual || r_consolidado(i).cod_terr || '-';
                --l_textoActual := l_textoActual || r_consolidado(i).num_secu_fact_diar;
                l_textoActual := l_textoActual || '</territorio>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- Documento de Identidad
                l_textoActual := l_textoActual || '<rifci>' || r_consolidado(i).num_docu_iden || '</rifci>';


                -- Documento de Identidad
                l_textoActual := l_textoActual || '<tel_con>' || r_consolidado(i).tel_clie || '</tel_con>';

                -- Nombre Cliente
                l_textoActual := l_textoActual || '<nombre>';
                l_textoActual := l_textoActual || r_consolidado(i).val_nom1 || ' ';
                l_textoActual := l_textoActual || r_consolidado(i).val_nom2 || ' ';
                l_textoActual := l_textoActual || r_consolidado(i).val_ape1 || ' ';
                l_textoActual := l_textoActual || r_consolidado(i).val_ape2;
                l_textoActual := l_textoActual || '</nombre>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- Periodo
                l_textoActual := l_textoActual || '<campana>' || p_codigoPeriodo || '</campana>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                -- Fecha Entrega
                l_textoActual := l_textoActual || '<fecha_entrega>' || trim(l_fechaDespacho) || '</fecha_entrega>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                -- Fecha pago
                l_textoActual := l_textoActual || '<fecha_pago>' || trim(l_fechaVencimiento) || '</fecha_pago>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                -- Fecha Conf
                l_textoActual := l_textoActual || '<fecha_conf>' || trim(l_fechaCV) || '</fecha_conf>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);


                  l_textoActual := l_textoActual || '<fechaReparto>' || l_fechaDespacho || '</fechaReparto>';
                  l_textoActual := l_textoActual || '<fechaReparto2>' || l_fechaDespacho_2 || '</fechaReparto2>';
                  l_textoActual := l_textoActual || '<fechaReparto3>' || l_fechaDespacho_3 || '</fechaReparto3>';
                  l_textoActual := l_textoActual || '<fechaSigFact>' || l_fechaDespacho2 || '</fechaSigFact>';
                  l_textoActual := l_textoActual || '<fechaSigFact2>' || l_fechaSigFact2 || '</fechaSigFact2>';
                  l_textoActual := l_textoActual || '<fechaSigFact3>' || l_fechaSigFact3 || '</fechaSigFact3>';
                  l_textoActual := l_textoActual || '<diasDesp>' || fac_pkg_proc.ped_fn_obt_dias_fecha_ent(r_consolidado(i).oid_soli_cabe,1,l_codigoPeriodoSiguiente, to_date(l_fechaDespacho2,'dd/mm/yyyy')) || '</diasDesp>';

                -- Nombre Gerente
                l_textoActual := l_textoActual || '<nom_geren>' || r_consolidado(i).gerente || '</nom_geren>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                -- Celular Gerente
                l_textoActual := l_textoActual || '<cel_geren>' || r_consolidado(i).cel_gerente || '</cel_geren>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                -- Nombre Ejecutiva
                l_textoActual := l_textoActual || '<nom_ejec>' || r_consolidado(i).ejecutiva || '</nom_ejec>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                -- Celular Ejecutiva
                l_textoActual := l_textoActual || '<cel_ejec>' || r_consolidado(i).cel_ejecutiva || '</cel_ejec>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                -- Numero de Factura
                l_textoActual := l_textoActual || '<num_fac>' || r_consolidado(i).numero_factura || '</num_fac>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);


                if lv_imprimepaqdoc='S' then
                  -- Ind. Impresion
                  l_textoActual := l_textoActual || '<imprimepaqdoc>' || r_consolidado(i).ind_impr_pdoc || '</imprimepaqdoc>';

                  --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                end if;


                -- Fin Cabecera
                l_textoActual := l_textoActual || '</blqcab>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);


                -- Inicio Detalle
                l_textoActual := l_textoActual || '<cuerpo>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);



                -- Iteramos por las secciones del detalle
                    OPEN c_detalle(r_consolidado(i).oid_soli_cabe
                      ,l_desPremioAgotado
                      ,l_desAgotado
                      ,l_desFaltLiq
                      ,l_desFaltAnun
                      ,l_desAnulMotMax
                      ,l_desPremio
                      ,l_desAtRec
                      ,l_desRecup
                      ,l_desReemp
                      ,l_desGratis
                      ,l_desPremioLET
                      ,l_desOfertNavid
                      ,ln_oidestra
                    );
                    LOOP
                        FETCH c_detalle BULK COLLECT
                        INTO r_detalle LIMIT 1000;


                        IF  r_detalle.COUNT > 0 THEN
                            FOR j IN r_detalle.FIRST..r_detalle.LAST
                            LOOP

                                if nvl(l_catalogo_anterior,'X')<>r_detalle(j).des_cata and j<>r_detalle.FIRST then
                                -- Cerramos el cursor de detalles
                                   l_textoActual := '</catalogo>';
                                   DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                end if;

                                if nvl(l_catalogo_anterior,'X')<>r_detalle(j).des_cata then
                                       l_textoActual := '<catalogo>';
                                       --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                       l_textoActual := l_textoActual || '<nombre>' || substr(r_detalle(j).des_cata,3) || '</nombre>';
                                       --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                       l_textoActual := l_textoActual || '<total_solic>' || r_detalle(j).UNID_SOLI_CATA || '</total_solic>';
                                       --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                       l_textoActual := l_textoActual || '<total_aten>' || r_detalle(j).UNID_ATEN_CATA || '</total_aten>';
                                       --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                       l_textoActual := l_textoActual || '<total_total_cata>' || r_detalle(j).PREC_CATA || '</total_total_cata>';
                                       --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                       l_textoActual := l_textoActual || '<total_valor_pagar>' || r_detalle(j).TOTA_CATA || '</total_valor_pagar>';
                                       --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                       l_textoActual := l_textoActual || '<total_oport_ahorro>' || r_detalle(j).OPOR_CATA || '</total_oport_ahorro>';
                                       DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                end if;


                               if (orig_ante is null and r_detalle(j).val_codi_orig is not null) or (orig_ante is not null and orig_ante<>r_detalle(j).val_codi_orig and r_detalle(j).val_codi_orig is not null) then

                                 l_textoActual := '<detalle>';
                                 --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                l_textoActual := l_textoActual || '<tiporeg>H</tiporeg>';

                                -- Codigo de Venta
                                l_textoActual := l_textoActual || '<CUV>' || r_detalle(j).val_codi_orig || '</CUV>';
                                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                -- Descripcion
                                l_textoActual := l_textoActual || '<Descripcion>' || r_detalle(j).des_prod || '</Descripcion>';
                                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                -- Unidades demandadas
                                l_textoActual := l_textoActual || '<solicitado>' || r_detalle(j).num_unid_orig || '</solicitado>';
                                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                -- Unidades atendidas
                                l_textoActual := l_textoActual || '<atendido></atendido>';
                                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                -- Precio unitario
                                l_textoActual := l_textoActual || '<unit_cata></unit_cata>';
                                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                -- Precio total
                                l_textoActual := l_textoActual || '<tota_cata></tota_cata>';
                                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                -- % Descuento
                                l_textoActual := l_textoActual || '<porc_desc></porc_desc>';
                                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                -- Total Precio Catalogo
                                l_textoActual := l_textoActual || '<valor_pagar></valor_pagar>';
                                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                -- Oportunidad de Ahorro
                                l_textoActual := l_textoActual || '<oport_ahorro></oport_ahorro>';
                                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                -- Observaciones
                                l_textoActual := l_textoActual || '<val_obse></val_obse>';
                                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                 l_textoActual := l_textoActual || '</detalle>';
                                 DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);


                                end if;



                                 l_textoActual := '<detalle>';
                                 --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                if r_detalle(j).val_codi_orig is not null then
                                   l_textoActual := l_textoActual || '<tiporeg>D</tiporeg>';
                                end if;


                                -- Codigo de Venta
                                l_textoActual := l_textoActual || '<CUV>' || r_detalle(j).val_codi_vent || '</CUV>';
                                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                -- Descripcion
                                l_textoActual := l_textoActual || '<Descripcion>' || r_detalle(j).des_prod || '</Descripcion>';
                                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                -- Unidades demandadas
                                l_textoActual := l_textoActual || '<solicitado>' || r_detalle(j).num_unid_dema_real || '</solicitado>';
                                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                -- Unidades atendidas
                                l_textoActual := l_textoActual || '<atendido>' || r_detalle(j).num_unid_aten || '</atendido>';
                                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                -- Precio unitario
                                l_textoActual := l_textoActual || '<unit_cata>' || trim(to_char(r_detalle(j).val_prec_cata_unit_loca , l_formatoNumerico)) || '</unit_cata>';
                                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                -- Precio total
                                l_textoActual := l_textoActual || '<tota_cata>' || trim(to_char(r_detalle(j).val_prec_cata_tota_loca, l_formatoNumerico)) || '</tota_cata>';
                                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                -- % Descuento
                                l_textoActual := l_textoActual || '<porc_desc>' || r_detalle(j).val_porc_desc || '</porc_desc>';
                                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                -- Total Precio Catalogo
                                l_textoActual := l_textoActual || '<valor_pagar>' || trim(to_char(r_detalle(j).val_prec_fact_tota_loca, l_formatoNumerico)) || '</valor_pagar>';
                                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                -- Oportunidad de Ahorro
                                l_textoActual := l_textoActual || '<oport_ahorro>' || trim(to_char(nvl(r_detalle(j).imp_prec_publ,0), l_formatoNumerico)) || '</oport_ahorro>';
                                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                -- Observaciones
                                l_textoActual := l_textoActual || '<val_obse>' || r_detalle(j).val_obse || '</val_obse>';
                                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                 l_textoActual := l_textoActual || '</detalle>';
                                 DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);


                                 l_catalogo_anterior:=r_detalle(j).des_cata;
                                 l_totalFactura:=r_detalle(j).prec_tota;
                                 l_totalAhorro:=r_detalle(j).opor_tota;

                                 orig_ante:=r_detalle(j).val_codi_orig;

                            END LOOP;
                        END IF;



                        EXIT WHEN c_detalle%NOTFOUND;
                END LOOP;
                    CLOSE c_detalle;

                    l_textoActual := '</catalogo>';
                    --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                l_textoActual := l_textoActual || '</cuerpo>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- Inicio Pie
                l_textoActual := l_textoActual || '<pie>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);




                --l_totalFactura := l_totalCatalogo - l_totalDescuentos + r_consolidado(i).val_impo_flet_tota_loca + l_percepcion+nvl(r_consolidado(i).val_impo_rete_desc,0);


                -- Obtenemos los Pagos Posteriores
                /*SELECT NVL(SUM(imp_pend),0)
                INTO l_totalPagoPosterior
                FROM ccc_movim_cuent_corri
                WHERE clie_oid_clie = r_consolidado(i).oid_clie
                  AND imp_pend > 0
                  AND perd_oid_peri > l_oidPeriodo ;*/


                select count(1) into l_cantidadOC from ped_solic_cabec
                where clie_oid_clie=r_consolidado(i).oid_clie
                and perd_oid_peri=l_oidPeriodo
                and ind_oc=1
                and ind_ts_no_conso=1
                and fec_fact is not null
                and tspa_oid_tipo_soli_pais in
                (
                select oid_tipo_soli_pais from ped_tipo_solic_pais x, ped_tipo_solic y
                where x.tsol_oid_tipo_soli=y.oid_tipo_soli
                and y.ind_soli_nega=0 and y.ind_cons=0
                )
                ;



                -- Obtenemos el Cargos por Familia Segura
                IF l_indicadorCargoFamSegura='S' and l_cantidadOC<2 THEN

                 SELECT NVL(SUM(mcc.imp_movi),0)
                 INTO l_cargoFamSegura
                 FROM
                  ccc_movim_cuent_corri mcc,
                  ccc_proce cp,
                  ccc_subpr su
                 WHERE mcc.clie_oid_clie = r_consolidado(i).oid_clie
                   AND mcc.perd_oid_peri = l_oidPeriodo
                   AND mcc.subp_oid_subp_crea = su.Oid_Subp
                   AND su.ccpr_Oid_Proc = cp.oid_proc
                   AND cp.cod_proc = 'CCC007'
                   AND su.cod_subp = 7;
                else
                    l_cargoFamSegura:=0;

                END IF;


                /********************************************************************
                Total Factura - Pagos Posteriores + Cargo Familia Segura
                + Saldo Anterior = Total Monto a Pagar
                **********************************************************************/
                --<DEMORA> ESTA FUNCION CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_CAMPA_ANTER demora bastante EN PROD
                l_totalAPagar := CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_CAMPA_ANTER(r_consolidado(i).oid_clie,l_codigoPeriodoSiguiente);

                BEGIN
                  SELECT SUM(ccc_detal_cupon_trami_depur.imp_deta)
                    INTO cupon
                    FROM mae_clien,
                         ccc_detal_cupon_trami_depur,
                         ccc_situa_cupon
                   WHERE ((ccc_situa_cupon.oid_situ_cupo = ccc_detal_cupon_trami_depur.sicu_oid_situ_cupo) AND
                         (ccc_situa_cupon.cod_situ_cupo = 'T') AND
                         (mae_clien.oid_clie = ccc_detal_cupon_trami_depur.clie_oid_clie) AND
                         (mae_clien.oid_clie = r_consolidado(i).oid_clie));
                  IF cupon IS NULL THEN
                    cupon := 0;
                  END IF;
                EXCEPTION
                  WHEN no_data_found THEN
                    cupon := 0;
                END;

                l_totalAPagar:=l_totalAPagar-cupon;

                saldo_cupon:=l_totalAPagar;

                saldo_cupon:=trunc(saldo_cupon/100)*100;


                if saldo_cupon<0 then
                   saldo_cupon:=0;
                end if;

               update ped_segui_pedid a
               set a.val_mont_cupo=saldo_cupon
               where a.soca_oid_soli_cabe=r_consolidado(i).oid_soli_cabe;

                /*IF lv_indiejec='S' THEN
                 -- Gastos Administrativos
                 --l_saldoAnterior:=l_saldoAnterior-nvl(r_consolidado(i).gastos,0);
                 l_totalFactura:=l_totalFactura+nvl(r_consolidado(i).gastos,0);
                END IF;*/

                -- Obtenemos los Pagos Posteriores
                SELECT NVL(SUM(imp_pend),0)
                INTO l_totalPagoPosterior
                FROM ccc_movim_cuent_corri
                WHERE clie_oid_clie = r_consolidado(i).oid_clie
                  AND imp_pend > 0
                  AND perd_oid_peri > l_oidPeriodo ;

                l_saldoAnterior := l_totalAPagar - (l_totalFactura-l_totalAhorro+r_consolidado(i).val_impo_flet_tota_loca+l_cargoFamSegura+nvl(r_consolidado(i).gastos,0));



                    -- Total Catalogo
                    l_textoActual := l_textoActual || '<subtotal>' || trim(to_char(l_totalFactura, l_formatoNumerico)) || '</subtotal>';
                    --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                    -- Total Descuentos (Incluimos el redondeo)
                    l_textoActual := l_textoActual || '<descuentos>' || trim(to_char(l_totalAhorro, l_formatoNumerico)) || '</descuentos>';
                    --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                    -- Total Descuentos (Incluimos el redondeo)
                    l_textoActual := l_textoActual || '<subtotalfac>' || trim(to_char(l_totalFactura-l_totalAhorro, l_formatoNumerico)) || '</subtotalfac>';
                    --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                    -- Flete
                    l_textoActual := l_textoActual || '<fletes>' || trim(to_char(r_consolidado(i).val_impo_flet_tota_loca, l_formatoNumerico)) || '</fletes>';
                    --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                    -- Abono Atencion de Servicios (No se visualiza)
                    l_textoActual := l_textoActual || '<familia>' || trim(to_char(l_cargoFamSegura, l_formatoNumerico)) || '</familia>';
                    --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                    -- Impuesto
                    l_textoActual := l_textoActual || '<iva>' || trim(to_char(r_consolidado(i).val_impo_impu_tota_loca, l_formatoNumerico)) || '</iva>';
                    --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                    -- Total a pagar
                    l_textoActual := l_textoActual || '<total_pagar>' || trim(to_char(l_totalFactura-l_totalAhorro+r_consolidado(i).val_impo_flet_tota_loca+l_cargoFamSegura+nvl(r_consolidado(i).gastos,0), l_formatoNumerico)) || '</total_pagar>';
                    --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                    -- Total Ahorro
                    l_textoActual := l_textoActual || '<total_oport_ahorro>' || trim(to_char(l_totalAhorro, l_formatoNumerico)) || '</total_oport_ahorro>';
                    --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                    -- Saldo Anterior
                    l_textoActual := l_textoActual || '<saldo_anterior>' || trim(to_char(l_saldoAnterior, l_formatoNumerico)) || '</saldo_anterior>';
                    --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                    -- Pagos Posteriores
                    l_textoActual := l_textoActual || '<pagos_posteriores>' || trim(to_char(l_totalPagoPosterior, l_formatoNumerico)) || '</pagos_posteriores>';
                    --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                    -- Saldo Cupon
                    l_textoActual := l_textoActual || '<saldo_cupon>' || trim(to_char(saldo_cupon, l_formatoNumerico)) || '</saldo_cupon>';
                    --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);


                    -- Intereses de Mora
                    l_textoActual := l_textoActual || '<inter_mora>' || trim(to_char(nvl(r_consolidado(i).gastos,0), l_formatoNumerico)) || '</inter_mora>';
                    --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                -- Fin Pie
                l_textoActual := l_textoActual || '</pie>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                -- Fin Detalle de Factura
                l_textoActual := l_textoActual || '</detfac4>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                                 l_catalogo_anterior:=null;
                                 l_totalFactura:=null;
                                 l_totalAhorro:=null;

                --Se inserta el CLOB en memoria a BD
                INSERT INTO IMP_PAQUE_DOCUM_DETAL_FACTU (
                COR_PADO_DEFA,
                COD_CONS,
                VAL_NUME_SOLI,
                XML_DETA_FACT)
                VALUES(
                r_consolidado(i).oid_soli_cabe,
                r_consolidado(i).cod_clie,
                r_consolidado(i).val_nume_soli,
                l_CLOB);

                --Se libera la variabla CLOB de Memoria
                DBMS_LOB.FREETEMPORARY(l_CLOB);

                commit;

            END LOOP;

        END IF;

        EXIT WHEN c_consolidados%NOTFOUND;
    END LOOP;
    -- Cerramos el cursor
    CLOSE c_consolidados;
 COMMIT;
END;

/***************************************************************************
Descripcion       : Proceso que genera los cupones de las consultoras que
                    han pasado pedido en un periodo y fecha particular.
Fecha Creacion    : 31/03/2010
Autor             : Carlos Hurtado Ramirez

IMP-102
***************************************************************************/

PROCEDURE IMP_PR_PROCE_CUPON(p_codigoPais VARCHAR2,
                                         p_codigoPeriodo VARCHAR2,
                                         p_oidzona NUMBER,
                                         p_fechaFacturacion VARCHAR2) IS

CURSOR c_cupon(oidPais NUMBER,
               oidPeriodo NUMBER,
               indicadorEnvioLarissa VARCHAR2,
               numeroLoteFacturacion NUMBER) IS
select mc.oid_clie,
       mc.cod_digi_ctrl,
       mc.cod_clie,
       mc.val_ape1 || ' ' || mc.val_ape2 || ', ' || mc.val_nom1 || ' ' || mc.val_nom2 nom_clie,
       stv.des_abrv_tipo_via  || ' ' || mcd.val_nomb_via || ' ' || mcd.num_ppal val_dir1,
       trim('/' from
       GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(oidPais, mc.oid_clie, 4) || '/' ||
       GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(oidPais, mc.oid_clie, 3) || '/' ||
       GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(oidPais, mc.oid_clie, 2) || '/' ||
       GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(oidPais, mc.oid_clie, 1)) val_dir2,
       zon.oid_zona,
       zon.cod_zona,
       ter.cod_terr,
       con.oid_soli_cabe,
       nvl(mc.val_recl_pend,0) val_recl_pend
from mae_clien mc,
     mae_clien_direc mcd,
     seg_tipo_via stv,
     ped_solic_cabec con,
     zon_zona zon,
     zon_terri ter
where mc.oid_clie = mcd.clie_oid_clie
  and mcd.tivi_oid_tipo_via = stv.oid_tipo_via (+)
  and mcd.ind_elim = 0
  and mcd.ind_dire_ppal = 1
  and mc.oid_clie = con.clie_oid_clie
  and con.zzon_oid_zona = zon.oid_zona
  and con.terr_oid_terr = ter.oid_terr
  and con.perd_oid_peri = oidPeriodo
  and zon.oid_zona=p_oidzona
  and con.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
  and con.num_unid_aten_tota>0
  and con.ind_inte_lari_gene = indicadorEnvioLarissa
  and (numeroLoteFacturacion is null or con.num_lote_fact = numeroLoteFacturacion)
  and exists (
      select null
      from int_lar_tipo_solici_pedido_dis l
      where l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais
  );


r_cupon c_cupon%ROWTYPE;

-- Variables locales
l_oidPais                       NUMBER;
l_oidPeriodo                    NUMBER;
l_oidPeriodoSgte                NUMBER;
l_codPeriodoSgte                VARCHAR2(6);
l_oidCanal                      NUMBER;
l_oidMarca                      NUMBER;
l_oidActividad                  NUMBER;
l_fechaVencimiento              DATE;
l_codigoPeriodo                 VARCHAR2(25);
l_saldoCupon                    NUMBER(12,2) := 0.00;
l_correlativo                   NUMBER := 1;
l_indicadorEnvioLarissa         VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioLarissa');
l_indicadorEnvioUltimoLote      VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioUltimoLote    ');
l_codigoActividadVencimiento    VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'codigoActividadVencimiento');
l_numeroLoteFacturacion         NUMBER;
l_clob                          CLOB;
l_textoActual                   VARCHAR2(1000) := '';
l_digiver                       VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'digiVer');
l_redondeo                      VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'IndRedondeo'),'N');
l_recPend                       VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'IndRecPendCupon'),'N');


BEGIN

    -- Obtenemos los OIDs necesarios
    l_oidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(p_codigoPais);
    l_oidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(CODIGO_CANAL);
    l_oidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(CODIGO_MARCA);
    l_oidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(p_codigoPeriodo, l_oidMarca, l_oidCanal);
    l_codigoPeriodo := SUBSTR(p_codigoPeriodo, 5, 2) || '/' || SUBSTR(p_codigoPeriodo, 1, 4);

    IF(l_indicadorEnvioUltimoLote = '1' OR l_indicadorEnvioUltimoLote = 'S') THEN

        BEGIN
            select max(cons.num_lote_fact)
            into l_numeroLoteFacturacion
            from ped_solic_cabec cons,
                 int_lar_tipo_solici_pedido_dis tspd
            where cons.perd_oid_peri = l_oidPeriodo
             and  cons.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
             and  cons.ind_ts_no_conso = 0
             and  (cons.ind_pedi_prue = 0 or cons.ind_pedi_prue is null)
             and  cons.tspa_oid_tipo_soli_pais = tspd.tspa_oid_tipo_soli_pais
             and  cons.pais_oid_pais = l_oidPais;

        EXCEPTION
          WHEN OTHERS THEN
              l_numeroLoteFacturacion := null;

        END;


    END IF;


    -- Obtenemos el OID del periodo siguiente
    l_codPeriodoSgte := GEN_FN_CALCU_PERIO(p_codigoPeriodo, 1);
    l_oidPeriodoSgte := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(l_codPeriodoSgte, l_oidMarca, l_oidCanal);

    -- Obtenemos el OID de la actividad
    SELECT ACT.OID_ACTI
    INTO l_oidActividad
    FROM CRA_ACTIV ACT
    WHERE ACT.PAIS_OID_PAIS = l_oidPais
    AND ACT.MARC_OID_MARC = l_oidMarca
    AND ACT.CANA_OID_CANA = l_oidCanal
    AND ACT.COD_ACTI = l_codigoActividadVencimiento; -- Cupon de Vencimiento

    -- Elimina todos las filas de la Tabla IMP_PAQUE_DOCUM_CUPON
    --EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_PAQUE_DOCUM_CUPON';

    OPEN c_cupon(l_oidPais, l_oidPeriodo, l_indicadorEnvioLarissa, l_numeroLoteFacturacion);
    LOOP
    FETCH c_cupon INTO r_cupon;
    EXIT WHEN c_cupon%NOTFOUND;
        BEGIN

            INSERT INTO IMP_PAQUE_DOCUM_CUPON
            VALUES (
            r_cupon.oid_soli_cabe,
            EMPTY_CLOB(),
            r_cupon.cod_clie)
            RETURNING XML_CONS INTO l_clob;


            -- Obtenemos la fecha de vencimiento de la zona del cliente
            BEGIN
                SELECT CRO.FEC_INIC
                INTO l_fechaVencimiento
                FROM CRA_CRONO CRO
                WHERE CRO.ZZON_OID_ZONA = r_cupon.oid_zona
                AND CRO.PERD_OID_PERI = l_oidPeriodoSgte
                AND CRO.CACT_OID_ACTI = l_oidActividad;

            EXCEPTION
            WHEN NO_DATA_FOUND THEN
                l_fechaVencimiento := TRUNC(SYSDATE);

            END;


            -- Obtenemos el valor del saldo para el cliente
            --l_saldoCupon := CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_VENCI(r_cupon.oid_clie, l_fechaVencimiento);
            l_saldoCupon := CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_CAMPA_ANTER(r_cupon.oid_clie,l_codPeriodoSgte);


            if l_saldoCupon<0 then
              l_saldoCupon:=0;
            end if;


            if l_redondeo='S' then
              l_saldoCupon:=round(l_saldoCupon/100,0)*100;
            end if;



            if l_recPend='S' then
              l_saldoCupon:=l_saldoCupon-r_cupon.val_recl_pend;
            end if;




/*
            -- Inicio Cupon
            l_textoActual := '<frmecc>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
*/

            -- Inicio cabecera
            l_textoActual := '<cab>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Codigo Cliente
            l_textoActual := '<ccon>' || r_cupon.cod_clie || '</ccon>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Codigo Verificador
            if nvl(l_digiver,'N')='S' then
            l_textoActual := '<dver>' || r_cupon.cod_digi_ctrl || '</dver>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
            end if;


            -- Nombre Cliente
            l_textoActual := '<ncon>' || r_cupon.nom_clie || '</ncon>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Zona
            l_textoActual := '<czon>' || r_cupon.cod_zona || '</czon>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Periodo
            l_textoActual := '<fcam>' || l_codigoPeriodo || '</fcam>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Territorio
            l_textoActual := '<cter>' || r_cupon.cod_terr || '</cter>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Direccion 1
            l_textoActual := '<dir1>' || r_cupon.val_dir1 || '</dir1>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Direccion 2
            l_textoActual := '<dir2>' || r_cupon.val_dir2 || '</dir2>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Fecha
            l_textoActual := '<fec>' || p_fechaFacturacion || '</fec>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Fin Cabecera
            l_textoActual := '</cab>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Inicio Bloque Cupon
            l_textoActual := '<blqcp>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Codigo Cliente
            l_textoActual := '<ccon>' || r_cupon.cod_clie || '</ccon>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Nombre Cliente
            l_textoActual := '<ncon>' || r_cupon.nom_clie || '</ncon>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Zona
            l_textoActual := '<czon>' || r_cupon.cod_zona || '</czon>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Territorio
            l_textoActual := '<cter>' || r_cupon.cod_terr || '</cter>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Saldo
            l_textoActual := '<saldo>' || l_saldoCupon || '</saldo>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Fecha Facturacion
            l_textoActual := '<fec>' || p_fechaFacturacion || '</fec>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Fecha Vencimiento
            l_textoActual := '<fecven>' || to_char(l_fechaVencimiento, 'dd/mm/yyyy') || '</fecven>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Fin Bloque Cupon
            l_textoActual := '</blqcp>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

/*            -- Detalle Vacio
            l_textoActual := '<detalle><txt><t/><tr/><tr/><tr/></txt><txt><t/><tr/><tr/><tr/></txt></detalle>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Fin Cupon
            l_textoActual := '</frmecc>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
*/

            l_correlativo := l_correlativo + 1;

        END;

    END LOOP;

    CLOSE c_cupon;

END;




/**************************************************************************
Descripcion         : Genera la orden compra simplificada
Fecha Creacion      : 04/10/2010
Autor               : Sergio Buchelli
Parametros Entrada  :
    p_codigoPais            : Codigo del pais.
    p_codigoPeriodo         : Codigo periodo
    p_fechaFacturacion     : Fecha Facturacion

IMP-106
/**************************************************************************/
PROCEDURE IMP_PR_PROCE_ORDEN_COMPR(p_codigoPais VARCHAR2,
                                         p_codigoPeriodo VARCHAR2,
                                         p_fechaFacturacion VARCHAR2,
                                         p_oidzona NUMBER)
IS
w_Filas NUMBER := 1000;
ls_sqlerrm   VARCHAR2(150);


CURSOR c_ordenCompra(oidPais NUMBER,
                        oidPeriodo NUMBER,
                        indicadorEnvioLarissa VARCHAR2,
                        numeroLoteFacturacion NUMBER) IS
select mc.cod_clie,
       mc.oid_clie,
       mc.cod_digi_ctrl,
       mc.val_ape1 || ' ' || mc.val_ape2 || ' ' || mc.val_nom1 || ' ' || mc.val_nom2 nom_clie,
       stv.des_abrv_tipo_via  || ' ' || mcd.val_nomb_via || ' ' || mcd.num_ppal || mcd.val_obse val_dir1,
       mcd.val_barr,
       trim('/' from
       GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(con.pais_oid_pais, mc.oid_clie, 4) || '/' ||
       GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(con.pais_oid_pais, mc.oid_clie, 3) || '/' ||
       GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(con.pais_oid_pais, mc.oid_clie, 2) || '/' ||
       GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(con.pais_oid_pais, mc.oid_clie, 1)) val_dir2,
       trim('/' from
       GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(mc.oid_clie, 'TF')  || '/' ||
       GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(mc.oid_clie, 'TM')) val_tele,
       con.val_nume_soli,
       reg.cod_regi,
       zon.cod_zona,
       zse.cod_secc,
       ter.cod_terr
from mae_clien mc,
     mae_clien_direc mcd,
     seg_tipo_via stv,
     ped_solic_cabec con,
     ped_solic_cabec_secue sec,
     zon_regio reg,
     zon_zona zon,
     zon_terri_admin zta,
     zon_secci zse,
     zon_terri ter
where mc.oid_clie = mcd.clie_oid_clie
  and mcd.tivi_oid_tipo_via = stv.oid_tipo_via (+)
  and mcd.ind_elim = 0
  and mcd.ind_dire_ppal = 1
  and mc.oid_clie = con.clie_oid_clie
  and con.zzon_oid_zona = zon.oid_zona
  and con.zzon_oid_zona = p_oidzona
  and reg.oid_regi = zon.zorg_oid_regi
  and con.terr_oid_terr = ter.oid_terr
  and con.ztad_oid_terr_admi = zta.oid_terr_admi
  and zta.zscc_oid_secc = zse.oid_secc
  and con.pais_oid_pais = oidPais
  and con.perd_oid_peri = oidPeriodo
  and con.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
  and con.ind_inte_lari_gene = indicadorEnvioLarissa
  and con.oid_soli_cabe = sec.soca_oid_soli_cabe
  and con.num_unid_aten_tota > 0
  and (numeroLoteFacturacion is null or con.num_lote_fact = numeroLoteFacturacion)
  and exists (
      select null
      from int_lar_tipo_solici_pedido_dis l
      where l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais
  )
order by sec.num_secu_zona_ruta,
         sec.num_secu_fact_diar;

r_ordenCompra c_ordenCompra%ROWTYPE;

-- Variables locales
l_oidPais                       NUMBER;
l_oidPeriodo                    NUMBER;
l_oidCanal                      NUMBER;
l_oidMarca                      NUMBER;
l_codigoPeriodo                 VARCHAR2(25);
l_correlativo                   NUMBER := 1;
l_indicadorEnvioLarissa         VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioLarissa');
l_indicadorEnvioUltimoLote      VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioUltimoLote');
l_indicadorDigitoCtrlOCS        VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorDigitoCtrlOCS');
l_numeroLoteFacturacion         NUMBER;
l_clob                          CLOB;
l_textoActual                   VARCHAR2(1000) := '';
l_contadorDetalles              NUMBER := 0;

-- Variables usadas para la inclusion del telefono en la boleta de despacho
l_indicadorTelefono         VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorTelefono');
l_textoTelefono             VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','textoTelefono');

-- Variables usadas para la inclusion del tag de saludo por cumplea?os
l_indicadorCumpleanos       VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorCumpleanos');
l_tagCumpleanosApertura     VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagCumpleanosApertura');
l_tagCumpleanosCierre       VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagCumpleanosCierre');
--NUEVO PARAMETRO
l_indicadorGenerarOCS       VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorGenerarOCS');

l_indtelenOCS       VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'l_indtelenOCS'),'S');

l_indcopiaOCS       VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indcopiaOCS'),'N');

BEGIN


    -- Obtenemos los OIDs necesarios
    l_oidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(p_codigoPais);
    l_oidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(CODIGO_CANAL);
    l_oidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(CODIGO_MARCA);
    l_oidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(p_codigoPeriodo, l_oidMarca, l_oidCanal);
    l_codigoPeriodo := SUBSTR(p_codigoPeriodo, 5, 2) || '/' || SUBSTR(p_codigoPeriodo, 1, 4);

    IF(l_indicadorEnvioUltimoLote = '1' OR l_indicadorEnvioUltimoLote = 'S') THEN

        BEGIN
            select max(cons.num_lote_fact)
            into l_numeroLoteFacturacion
            from ped_solic_cabec cons,
                 int_lar_tipo_solici_pedido_dis tspd
            where cons.perd_oid_peri = l_oidPeriodo
             and  cons.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
             and  cons.ind_ts_no_conso = 0
             and  (cons.ind_pedi_prue = 0 or cons.ind_pedi_prue is null)
             and  cons.tspa_oid_tipo_soli_pais = tspd.tspa_oid_tipo_soli_pais
             and  cons.pais_oid_pais = l_oidPais;
        EXCEPTION
          WHEN OTHERS THEN
              l_numeroLoteFacturacion := null;
        END;

    END IF;


    OPEN c_ordenCompra(l_oidPais, l_oidPeriodo, l_indicadorEnvioLarissa, l_numeroLoteFacturacion);
    LOOP
    FETCH c_ordenCompra INTO r_ordenCompra;
    EXIT WHEN c_ordenCompra%NOTFOUND;
        BEGIN

            INSERT INTO IMP_PAQUE_DOCUM_OCS(
            COR_OCOS,
            COD_CLIE,
            VAL_NUME_SOLI,
            XML_CONS
            )
            VALUES (
            r_ordenCompra.val_nume_soli,
            r_ordenCompra.cod_clie,
            r_ordenCompra.val_nume_soli,
            EMPTY_CLOB())
            RETURNING XML_CONS INTO l_clob;

            -- Inicio de orden compra simplificada
            l_textoActual := '<frmocs>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Inicio cabecera
            l_textoActual := '<blqcon>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            if l_indcopiaOCS='S' then

              -- numero de ocs
              l_textoActual := '<barrio>' || r_ordenCompra.Val_Barr || '</barrio>';
              DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);


              -- barrio
              l_textoActual := '<copias>' || imp_fn_devue_num_ocs(p_codigoPais,r_ordenCompra.Oid_Clie) || '</copias>';
              DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            end if;

            -- codigo region
            l_textoActual := '<creg>' || r_ordenCompra.cod_regi || '</creg>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Zona
            l_textoActual := '<czon>' || r_ordenCompra.cod_zona || '</czon>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Seccion
            l_textoActual := '<csec>' || r_ordenCompra.cod_secc || '</csec>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
            -- Territorio
            l_textoActual := '<cter>' || r_ordenCompra.cod_terr || '</cter>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Codigo Cliente
            IF(l_indicadorDigitoCtrlOCS = 'S' ) THEN
               l_textoActual := '<ccon>' || r_ordenCompra.cod_clie || r_ordenCompra.cod_digi_ctrl  ||'</ccon>';
               DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
            ELSE
              l_textoActual := '<ccon>' || r_ordenCompra.cod_clie || '</ccon>';
               DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
            END IF;

              -- Nombre Cliente
             l_textoActual := '<ncon>' || r_ordenCompra.nom_clie || '</ncon>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);


            -- Direccion 1
            l_textoActual := '<dir1>' || r_ordenCompra.val_dir1 || '</dir1>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Direccion 2
             l_textoActual := '<dir2>' || r_ordenCompra.val_dir2 || '</dir2>';
             DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

           -- tcon
           if l_indtelenOCS='S' then
             l_textoActual := '<tcon>' || r_ordenCompra.val_tele || '</tcon>';
             DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
           else
             l_textoActual := '<tcon></tcon>';
             DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
           end if;

            -- Fin Cabecera
            l_textoActual := '</blqcon>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);


            -- Fin orden compra simplificada
            l_textoActual := '</frmocs>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            --l_correlativo := l_correlativo + 1;

        END;
    END LOOP;
    CLOSE c_ordenCompra;
 EXCEPTION
  WHEN OTHERS THEN
     RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_PROCE_PAQUE_DOCUM_OCS_Z: '||substr(sqlerrm,1,250));
END;

/**************************************************************************
Descripcion         : Realiza el envio del archivo de documento factura
                      IMP-14
Fecha Creacion      : 10/11/2011
Autor               : Sergio Buchelli
Parametros Entrada  :
    p_codigoPais            : Codigo del pais.
    p_codigoPeriodo         : Codigo periodo
    p_fechaFacturacion     : Fecha Facturacion

IMP-136
/**************************************************************************/
PROCEDURE IMP_PR_PROCE_FACTU_LASER(p_codigoPais       VARCHAR2,
                                         p_codigoMarca      VARCHAR2,
                                         p_codigoCanal      VARCHAR2,
                                         p_codigoPeriodo    VARCHAR2,
                                         p_fechaFacturacion VARCHAR2,
                                         p_nombreArchivo    VARCHAR2,
                                         p_directorio VARCHAR2)
IS
w_Filas NUMBER := 1000;
ls_sqlerrm   VARCHAR2(150);

BEGIN
    --EXECUTAMOS  EL PROCESO QUE GENERA LA INFORMACION
        IF p_codigoPais='VEL' or p_codigoPais='VEE' then
           IMP_PR_PROCE_PAQUE_DOCUM_FAC_V(p_codigoPais,p_codigoMarca,p_codigoCanal,p_codigoPeriodo,p_fechaFacturacion);
        ELSE
           IF p_codigoPais='PAL' then
              IMP_PR_PROCE_PAQUE_DOCUM_FAC_P(p_codigoPais,p_codigoMarca,p_codigoCanal,p_codigoPeriodo,p_fechaFacturacion);
           ELSE
              IMP_PR_PROCE_PAQUE_DOCUM_FACTU(p_codigoPais,p_codigoMarca,p_codigoCanal,p_codigoPeriodo,p_fechaFacturacion);
           END IF;
        END IF;
    --REEMPLAZAMOS CARACTERES ESPECIALES
        IMP_PR_REEMP_CARAC_DOCUM_FACTU;
    --GENERAMOS EL ARCHIVO
       if p_nombreArchivo is not null then
           IMP_PR_GENER_ARCHI_FACTU_LASER(p_codigoPais,p_nombreArchivo,p_directorio);
       end if;
EXCEPTION
  WHEN OTHERS THEN
     RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_ENVIO_PAQUE_DOCUM_FACTU: '||substr(sqlerrm,1,250));
END;

/**************************************************************************
Descripcion         : Genera la FACTURA
Fecha Creacion      : 21/07/2011
Autor               : Sergio Buchelli
Parametros Entrada  :
    p_codigoPais            : Codigo del pais.
    p_codigoPeriodo         : Codigo periodo
    p_fechaFacturacion     : Fecha Facturacion
/**************************************************************************/
PROCEDURE IMP_PR_PROCE_PAQUE_DOCUM_FAC_V(p_codigoPais VARCHAR2,
                                         p_codigoMarca      VARCHAR2,
                                         p_codigoCanal      VARCHAR2,
                                         p_codigoPeriodo VARCHAR2,
                                         p_fechaFacturacion VARCHAR2)
IS
w_Filas NUMBER := 1000;
ls_sqlerrm   VARCHAR2(150);


CURSOR c_facturaCabecera IS
  SELECT VAL_NUME_SOLI,
             IMP_PAQUE_GENER_DOCUM_LASER.OID_CABE,
             IMP_PAQUE_GENER_DOCUM_LASER.NUM_DOCU_LEGA,
             COD_PAIS,
             DES_PAIS,
             IMP_PAQUE_GENER_DOCUM_LASER.FEC_EMIS,
             IMP_PAQUE_GENER_DOCUM_LASER.NUM_DOCU_CONT_INTE,
             OID_CLIE,
             COD_CLIE,
             COD_PERI,
             COD_REGI,
             COD_ZONA,
             COD_TERR,
             NOM_COMP,
             IMP_PAQUE_GENER_DOCUM_LASER.VAL_DIRE_COMP,
             IMP_PAQUE_GENER_DOCUM_LASER.VAL_NUME_IDEN_FISC,
             --IMP_PAQUE_GENER_DOCUM_LASER.IMP_DESC_TOTA_LOCA,
             IMP_PAQUE_GENER_DOCUM_LASER.IMP_IMPU_TOTA_LOCA,
             IMP_PAQUE_GENER_DOCUM_LASER.IMP_FLET_TOTA_LOCA,
             IMP_PAQUE_GENER_DOCUM_LASER.VAL_TOTA_PAGA_LOCA,
             SUM_PCON_TOTA_LOCA,
             SUM_PNET_TOTA_FACT,
             SUM_SIMP_TOTA_FACT,
             SUM_NUM_UNID_ATEN,
             SUM_TOTA_LETR,
             VAL_TASA_IMPU,
             IND_TASA_IMPU,
             VAL_DESC,
            -- VAL_PROM,
             VAL_TOTA,
             VAL_IMPO_VENT,
             VAL_BASE_IMPO,
             NVL(COD_PERI_REFE, ' ') COD_PERI_REFE,
             NVL(VAL_NUME_SOLI_REFE, 0) VAL_NUME_SOLI_REFE,
             COD_TIPO_DOCU,
             IMP_PAQUE_GENER_DOCUM_LASER.VAL_SERI_DOCU_LEGA,
             FAC_DOCUM_CONTA_CABEC.val_prec_cata_tota_loca+FAC_DOCUM_CONTA_CABEC.val_prec_cont_tota_loca TOTPROD,
             FAC_DOCUM_CONTA_CABEC.IMP_DESC_TOTA_LOCA-(FAC_DOCUM_CONTA_CABEC.VAL_TOTA_PAGA_LOCA-((FAC_DOCUM_CONTA_CABEC.val_prec_cata_tota_loca+FAC_DOCUM_CONTA_CABEC.val_prec_cont_tota_loca)-FAC_DOCUM_CONTA_CABEC.IMP_DESC_TOTA_LOCA+FAC_DOCUM_CONTA_CABEC.IMP_FLET_TOTA_LOCA)) IMP_DESC_TOTA_LOCA
          FROM IMP_PAQUE_GENER_DOCUM_LASER, FAC_DOCUM_CONTA_CABEC
          WHERE IMP_PAQUE_GENER_DOCUM_LASER.OID_CABE=FAC_DOCUM_CONTA_CABEC.Oid_Cabe
          and FAC_DOCUM_CONTA_CABEC.Num_Unid_Aten_Tota>0
          ORDER BY COD_CLIE, OID_CABE;

r_facturaCabecera c_facturaCabecera%ROWTYPE;

CURSOR c_detalleFactura(oidConsolidado NUMBER,filaInicio NUMBER, filaFin NUMBER) IS
  SELECT * FROM
     (SELECT AX.*,
           ROWNUM  RNUM
     FROM
      (SELECT   X.DCCA_OID_CABE,
             X.OID OID_LINE,
             X.NUM_LINEA,
             X.NUM_UNID_ATEN,
             X.PROD_OID_PROD,
             Y.VAL_I18N DES_PROD,
             X.VAL_PREC_CATA_UNIT_LOCA,
             X.VAL_PREC_CATA_TOTA_LOCA,
             X.VAL_PREC_CONT_UNIT_LOCA,
             X.VAL_PREC_CONT_TOTA_LOCA,
             X.IMP_DESC_UNIT_LOCA,
             X.IMP_DESC_TOTA_LOCA,
             X.VAL_PREC_FACT_UNIT_LOCA,
             X.VAL_PREC_FACT_TOTA_LOCA,
             X.IMP_IMPU_UNIT_LOCA,
             X.IMP_IMPU_TOTA_LOCA,
             X.VAL_PREC_NETO_UNIT_LOCA,
             X.VAL_PREC_NETO_TOTA_LOCA,
             NVL(X.VAL_PORC_DESC, 0) VAL_PORC_DESC,
             DECODE(X.VAL_PREC_CATA_UNIT_LOCA, 0, X.VAL_PREC_SIN_IMPU_UNIT,  X.VAL_PREC_CATA_UNIT_LOCA) VAL_PREC_CATA_UNIT_FACT,
             DECODE(X.VAL_PREC_CATA_TOTA_LOCA, 0, X.VAL_PREC_SIN_IMPU_TOTA_LOCA, X.VAL_PREC_SIN_IMPU_TOTA_LOCA) VAL_PREC_SIN_IMPU_TOTA_FACT,
             DECODE(X.VAL_PREC_CATA_UNIT_LOCA, 0, X.VAL_PREC_CONT_TOTA_LOCA, X.IMP_DESC_SIN_IMPU_TOTA_LOCA) IMP_DESC_TOTA_FACT,
             DECODE(X.VAL_PREC_CATA_UNIT_LOCA, 0, 0, X.VAL_PREC_NETO_TOTA_LOCA) VAL_PREC_NETO_TOTA_FACT,
             DECODE(X.VAL_PREC_CATA_TOTA_LOCA, 0, 0, X.IMP_IMPU_TOTA_LOCA) IMP_IMPU_TOTA_FACT,
             Z.Val_Codi_Vent
        FROM FAC_DOCUM_CONTA_LINEA X,
             (SELECT VAL_I18N, VAL_OID FROM GEN_I18N_SICC WHERE ATTR_ENTI = 'MAE_PRODU') Y,
             PED_SOLIC_POSIC Z,
             PRE_OFERT_DETAL P
       WHERE X.PROD_OID_PROD = Y.VAL_OID
        AND  X.SOPO_OID_SOLI_POSI = Z.OID_SOLI_POSI
        AND  Z.OFDE_OID_DETA_OFER = P.OID_DETA_OFER
        AND  NOT EXISTS (SELECT NULL
                         FROM FAC_TIPO_OFERT_EXCLU O
                         WHERE O.TOFE_OID_TIPO_OFER = P.TOFE_OID_TIPO_OFER)
        AND  X.DCCA_OID_CABE = oidConsolidado
        AND  X.NUM_UNID_ATEN > 0
       ORDER BY X.NUM_LINEA) AX)
     --WHERE ROWNUM <= filaFin)
  --WHERE ROWNUM >= filaInicio
  ;

r_detalleFactura c_detalleFactura%ROWTYPE;


CURSOR c_detalleFactura1(oidConsolidado NUMBER) IS
       SELECT   X.DCCA_OID_CABE,
             neg.cod_nego COD,
             gen.VAL_I18N DES_PROD,
             sum(X.NUM_UNID_ATEN) NUM_UNID_ATEN,
             sum(decode(x.val_prec_cata_tota_loca,0,x.val_prec_cont_tota_loca,x.val_prec_sin_impu_tota_loca)) VAL_PREC_CATA_UNIT_FACT,
             sum(decode(x.val_prec_cata_tota_loca,0,x.val_prec_cont_tota_loca,x.val_prec_sin_impu_tota_loca)) VAL_PREC_SIN_IMPU_TOTA_FACT,
             sum(decode(nvl(x.imp_desc_sin_impu_tota_loca,0),0,x.val_prec_cont_tota_loca,nvl(x.imp_desc_sin_impu_tota_loca,0))) IMP_DESC_TOTA_FACT
        FROM FAC_DOCUM_CONTA_LINEA X,
             (SELECT VAL_I18N, VAL_OID FROM GEN_I18N_SICC WHERE ATTR_ENTI = 'MAE_PRODU') Y,
             PED_SOLIC_POSIC Z,
             PRE_OFERT_DETAL P,
             mae_produ mp,
             mae_negoc neg,
             gen_i18n_sicc_pais gen
       WHERE X.PROD_OID_PROD = Y.VAL_OID
        and x.prod_oid_prod=mp.oid_prod
        and mp.nego_oid_nego=neg.oid_nego(+)
        and neg.oid_nego=gen.val_oid and gen.attr_enti='MAE_NEGOC'
        AND  X.SOPO_OID_SOLI_POSI = Z.OID_SOLI_POSI
        AND  Z.OFDE_OID_DETA_OFER = P.OID_DETA_OFER
        AND  NOT EXISTS (SELECT NULL
                         FROM FAC_TIPO_OFERT_EXCLU O
                         WHERE O.TOFE_OID_TIPO_OFER = P.TOFE_OID_TIPO_OFER)
        AND  X.DCCA_OID_CABE = oidConsolidado
        AND  X.NUM_UNID_ATEN > 0
       group by x.dcca_oid_cabe,neg.cod_nego, gen.val_i18n
  ;



r_detalleFactura1 c_detalleFactura1%ROWTYPE;

-- Variables locales
l_oidPais                       NUMBER;
l_oidPeriodo                    NUMBER;
l_oidCanal                      NUMBER;
l_oidMarca                      NUMBER;
l_codigoPeriodo                 VARCHAR2(25);
l_correlativo                   NUMBER := 1;
l_indicadorEnvioLarissa         VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioLarissa');
l_indicadorEnvioUltimoLote      VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioUltimoLote');
l_numeroLoteFacturacion         NUMBER;
l_clob                          CLOB;
l_textoActual                   VARCHAR2(1000) := '';

--NUEVO PARAMETRO
l_numFilas   VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'numeroDetallesFactura');

l_modelo   VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'modeloFacturaVE'),'1');

l_totalPaginas              NUMBER;
l_sizeDetalle               NUMBER;

auxCodClie              VARCHAR2(15);
l_codClie               VARCHAR2(15);

l_filaInicio            NUMBER;
l_filaFin               NUMBER;

BEGIN
    --limpiamos temporal de factura
    EXECUTE IMMEDIATE(' TRUNCATE TABLE IMP_PAQUE_DOCUM_LASER_FACTU');
    auxCodClie:=NULL;

    --inicamos con el caculo de la cabecera
    IMP_PKG_PROCE_COMPA.IMP_PR_CALCU_DOCUM_LASER_FACTU(p_codigoPais,p_codigoMarca,p_codigoCanal,p_codigoPeriodo,p_fechaFacturacion);


    -- Obtenemos los OIDs necesarios
    l_oidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(p_codigoPais);
    l_oidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(CODIGO_CANAL);
    l_oidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(CODIGO_MARCA);
    l_oidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(p_codigoPeriodo, l_oidMarca, l_oidCanal);
    l_codigoPeriodo := SUBSTR(p_codigoPeriodo, 5, 2) || '/' || SUBSTR(p_codigoPeriodo, 1, 4);


    OPEN c_facturaCabecera;
    LOOP
    FETCH c_facturaCabecera INTO r_facturaCabecera;
    EXIT WHEN c_facturaCabecera%NOTFOUND;
        BEGIN


                                  INSERT INTO IMP_PAQUE_DOCUM_LASER_FACTU (
                                        COD_CLIE,
                                        XML_LASE_FACT)
                                  VALUES ( r_facturaCabecera.oid_cabe,
                                         EMPTY_CLOB())
                                  RETURNING XML_LASE_FACT INTO l_clob;


                                -- Inicio del paquete
                                l_textoActual := '<factura>';
                                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                --Inicio Bloque cabecera
                                l_textoActual := '<blqcab>';
                                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<numfact>'|| r_facturaCabecera.NUM_DOCU_LEGA  ||'</numfact>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<numint>'|| r_facturaCabecera.NUM_DOCU_CONT_INTE  ||'</numint>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<numped>'|| r_facturaCabecera.VAL_NUME_SOLI  ||'</numped>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<numcedula>'|| r_facturaCabecera.VAL_NUME_IDEN_FISC  ||'</numcedula>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<lugar>' || r_facturaCabecera.DES_PAIS || '</lugar>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<dia>'|| to_char(r_facturaCabecera.fec_emis,'dd') || '</dia>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<mes>' || to_char(r_facturaCabecera.fec_emis,'mm') || '</mes>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<ano>' || to_char(r_facturaCabecera.fec_emis,'yyyy') || '</ano>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<pag>Pag. '|| ' / ' || l_totalPaginas || '</pag>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<codconsultora>' || r_facturaCabecera.COD_CLIE || '</codconsultora>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<territorio>' || r_facturaCabecera.COD_ZONA || ' - ' || r_facturaCabecera.COD_TERR ||'</territorio>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<documento>' || r_facturaCabecera.VAL_NUME_IDEN_FISC ||'</documento>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<nombre>' || r_facturaCabecera.NOM_COMP ||'</nombre>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<campana>' || r_facturaCabecera.COD_PERI ||'</campana>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<direccion>' || r_facturaCabecera.VAL_DIRE_COMP ||'</direccion>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                l_textoActual := '</blqcab>';
                                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                               --FIN Bloque cabecera

                               --INICIO BLOQUE DETALLE
                                l_textoActual := '<detalle>';
                                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                if l_modelo='1' then

                                OPEN c_detalleFactura( r_facturaCabecera.OID_CABE,l_filaInicio,l_filaFin);
                                LOOP
                                FETCH c_detalleFactura INTO r_detalleFactura;
                                EXIT WHEN c_detalleFactura%NOTFOUND;
                                BEGIN

                                   l_textoActual := '<txt>' || r_detalleFactura.NUM_UNID_ATEN || '<t/>'|| r_detalleFactura.VAL_CODI_VENT || '     ' || r_detalleFactura.DES_PROD||'<tr/> ';
                                   l_textoActual := l_textoActual || TRIM(TO_CHAR(r_detalleFactura.VAL_PREC_CATA_UNIT_FACT,'999999999990.99')) || '<tr/> ';
                                   l_textoActual := l_textoActual || TRIM(TO_CHAR(r_detalleFactura.VAL_PREC_SIN_IMPU_TOTA_FACT,'999999999990.99')) || '(D-' || r_detalleFactura.VAL_PORC_DESC || ')';

                                   l_textoActual := l_textoActual ||  '</txt>';
                                       /* IF(c_detalleFactura.IND_PROD_GRAT='S') THEN
                                            l_textoActual := l_textoActual ||  'Prom.</txt>';
                                        ELSE
                                            l_textoActual := l_textoActual ||  '</txt>';
                                        END IF;*/

                                   DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                END;
                                END LOOP;
                                CLOSE c_detalleFactura;

                                else

                                OPEN c_detalleFactura1( r_facturaCabecera.OID_CABE);
                                LOOP
                                FETCH c_detalleFactura1 INTO r_detalleFactura1;
                                EXIT WHEN c_detalleFactura1%NOTFOUND;
                                BEGIN

                                   l_textoActual := '<txt>' || r_detalleFactura1.NUM_UNID_ATEN || '<t/>'|| r_detalleFactura1.COD || '     ' || r_detalleFactura1.DES_PROD||'<tr/> ';
                                   l_textoActual := l_textoActual || TRIM(TO_CHAR(r_detalleFactura1.VAL_PREC_CATA_UNIT_FACT,'999999999990.99')) || '<tr/> ';
                                   l_textoActual := l_textoActual || TRIM(TO_CHAR(r_detalleFactura1.VAL_PREC_SIN_IMPU_TOTA_FACT,'999999999990.99')) || '(D-' || TRIM(TO_CHAR(r_detalleFactura1.IMP_DESC_TOTA_FACT,'999999999990.99')) || ')';

                                   l_textoActual := l_textoActual ||  '</txt>';
                                       /* IF(c_detalleFactura.IND_PROD_GRAT='S') THEN
                                            l_textoActual := l_textoActual ||  'Prom.</txt>';
                                        ELSE
                                            l_textoActual := l_textoActual ||  '</txt>';
                                        END IF;*/

                                   DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                END;
                                END LOOP;
                                CLOSE c_detalleFactura1;

                                end if;
                                -- SI NOS ENCONTRAMOS EN LA ULTIMA PAGINA PONEMOS TODAS LAS UNIDADES ATENDIDAS
/*
                                        IF( pag = l_totalPaginas ) THEN
                                            l_textoActual := '<txt>-----</txt>';
                                            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                            l_textoActual := '<txt>'|| r_facturaCabecera.SUM_NUM_UNID_ATEN ||'</txt> ';
                                            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                        END IF;
*/
                                -- Fin de detalle Factura
                                l_textoActual := '</detalle>';
                                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                --
                                        l_textoActual := '<pie>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                        l_textoActual := '<totprod>' || TRIM(TO_CHAR(r_facturaCabecera.Totprod,'999999999990.99')) || '</totprod>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                        l_textoActual := '<totdesc>' || TRIM(TO_CHAR(r_facturaCabecera.IMP_DESC_TOTA_LOCA,'999999999990.99')) || '</totdesc>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                        l_textoActual := '<totgast>' || TRIM(TO_CHAR(r_facturaCabecera.IMP_FLET_TOTA_LOCA,'999999999990.99')) || '</totgast>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                        l_textoActual := '<totfact>' || TRIM(TO_CHAR(r_facturaCabecera.VAL_TOTA_PAGA_LOCA,'999999999990.99')) || '</totfact>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                        l_textoActual := '<totimp>' || TRIM(TO_CHAR(r_facturaCabecera.IMP_IMPU_TOTA_LOCA,'999999999990.99')) || '</totimp>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                        l_textoActual := '</pie>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                 --FIN FACTURA
                                l_textoActual := '</factura>';
                                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

        END;
    END LOOP;
    CLOSE c_facturaCabecera;
 EXCEPTION
  WHEN OTHERS THEN
     RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_PROCE_PAQUE_DOCUM_FAC_V: '||substr(sqlerrm,1,250));
END IMP_PR_PROCE_PAQUE_DOCUM_FAC_V;

/**************************************************************************
Descripcion         : Genera la FACTURA
Fecha Creacion      : 21/07/2011
Autor               : Sergio Buchelli
Parametros Entrada  :
    p_codigoPais            : Codigo del pais.
    p_codigoPeriodo         : Codigo periodo
    p_fechaFacturacion     : Fecha Facturacion
/**************************************************************************/
PROCEDURE IMP_PR_PROCE_PAQUE_DOCUM_FAC_P(p_codigoPais VARCHAR2,
                                         p_codigoMarca      VARCHAR2,
                                         p_codigoCanal      VARCHAR2,
                                         p_codigoPeriodo VARCHAR2,
                                         p_fechaFacturacion VARCHAR2)
IS
w_Filas NUMBER := 1000;
ls_sqlerrm   VARCHAR2(150);


CURSOR c_facturaCabecera IS
  SELECT VAL_NUME_SOLI,
             IMP_PAQUE_GENER_DOCUM_LASER.OID_CABE,
             IMP_PAQUE_GENER_DOCUM_LASER.NUM_DOCU_LEGA,
             IMP_PAQUE_GENER_DOCUM_LASER.COD_PAIS,
             IMP_PAQUE_GENER_DOCUM_LASER.DES_PAIS,
             IMP_PAQUE_GENER_DOCUM_LASER.FEC_EMIS,
             IMP_PAQUE_GENER_DOCUM_LASER.NUM_DOCU_CONT_INTE,
             IMP_PAQUE_GENER_DOCUM_LASER.OID_CLIE,
             IMP_PAQUE_GENER_DOCUM_LASER.COD_CLIE,
             IMP_PAQUE_GENER_DOCUM_LASER.COD_PERI,
             IMP_PAQUE_GENER_DOCUM_LASER.COD_REGI,
             IMP_PAQUE_GENER_DOCUM_LASER.COD_ZONA,
             IMP_PAQUE_GENER_DOCUM_LASER.COD_TERR,
             IMP_PAQUE_GENER_DOCUM_LASER.NOM_COMP,
             IMP_PAQUE_GENER_DOCUM_LASER.VAL_DIRE_COMP,
             IMP_PAQUE_GENER_DOCUM_LASER.VAL_NUME_IDEN_FISC,
             IMP_PAQUE_GENER_DOCUM_LASER.IMP_DESC_TOTA_LOCA,
             IMP_PAQUE_GENER_DOCUM_LASER.IMP_IMPU_TOTA_LOCA,
             IMP_PAQUE_GENER_DOCUM_LASER.IMP_FLET_TOTA_LOCA,
             IMP_PAQUE_GENER_DOCUM_LASER.VAL_TOTA_PAGA_LOCA,
             IMP_PAQUE_GENER_DOCUM_LASER.SUM_PCON_TOTA_LOCA,
             IMP_PAQUE_GENER_DOCUM_LASER.SUM_PNET_TOTA_FACT,
             fac_docum_conta_cabec.val_prec_cata_tota_loca+fac_docum_conta_cabec.val_prec_cont_tota_loca SUM_SIMP_TOTA_FACT,
             IMP_PAQUE_GENER_DOCUM_LASER.SUM_NUM_UNID_ATEN,
             IMP_PAQUE_GENER_DOCUM_LASER.SUM_TOTA_LETR,
             IMP_PAQUE_GENER_DOCUM_LASER.VAL_TASA_IMPU,
             IMP_PAQUE_GENER_DOCUM_LASER.IND_TASA_IMPU,
             IMP_PAQUE_GENER_DOCUM_LASER.VAL_DESC,
            -- VAL_PROM,
             IMP_PAQUE_GENER_DOCUM_LASER.VAL_TOTA,
             IMP_PAQUE_GENER_DOCUM_LASER.VAL_IMPO_VENT,
             IMP_PAQUE_GENER_DOCUM_LASER.VAL_BASE_IMPO,
             NVL(IMP_PAQUE_GENER_DOCUM_LASER.COD_PERI_REFE, ' ') COD_PERI_REFE,
             NVL(IMP_PAQUE_GENER_DOCUM_LASER.VAL_NUME_SOLI_REFE, 0) VAL_NUME_SOLI_REFE,
             IMP_PAQUE_GENER_DOCUM_LASER.COD_TIPO_DOCU,
             IMP_PAQUE_GENER_DOCUM_LASER.VAL_SERI_DOCU_LEGA

          FROM IMP_PAQUE_GENER_DOCUM_LASER, fac_docum_conta_cabec
          where IMP_PAQUE_GENER_DOCUM_LASER.oid_cabe=fac_docum_conta_cabec.oid_cabe
          ORDER BY COD_CLIE, OID_CABE;





r_facturaCabecera c_facturaCabecera%ROWTYPE;

CURSOR c_detalleFactura(oidConsolidado NUMBER,filaInicio NUMBER, filaFin NUMBER) IS
  SELECT * FROM
     (SELECT AX.*,
           ROWNUM  RNUM
     FROM
      (SELECT   X.DCCA_OID_CABE,
             X.OID OID_LINE,
             X.NUM_LINEA,
             X.NUM_UNID_ATEN,
             X.PROD_OID_PROD,
             Y.VAL_I18N DES_PROD,
             X.VAL_PREC_CATA_UNIT_LOCA,
             X.VAL_PREC_CATA_TOTA_LOCA,
             X.VAL_PREC_CONT_UNIT_LOCA,
             X.VAL_PREC_CONT_TOTA_LOCA,
             X.IMP_DESC_UNIT_LOCA,
             X.IMP_DESC_TOTA_LOCA,
             X.VAL_PREC_FACT_UNIT_LOCA,
             X.VAL_PREC_FACT_TOTA_LOCA,
             X.IMP_IMPU_UNIT_LOCA,
             X.IMP_IMPU_TOTA_LOCA,
             X.VAL_PREC_NETO_UNIT_LOCA,
             X.VAL_PREC_NETO_TOTA_LOCA,
             NVL(X.VAL_PORC_DESC, 0) VAL_PORC_DESC,
             DECODE(X.VAL_PREC_CATA_UNIT_LOCA, 0, X.VAL_PREC_SIN_IMPU_UNIT,  X.VAL_PREC_CATA_UNIT_LOCA) VAL_PREC_CATA_UNIT_FACT,
             DECODE(X.VAL_PREC_CATA_TOTA_LOCA, 0, X.VAL_PREC_SIN_IMPU_TOTA_LOCA, X.VAL_PREC_SIN_IMPU_TOTA_LOCA) VAL_PREC_SIN_IMPU_TOTA_FACT,
             DECODE(X.VAL_PREC_CATA_UNIT_LOCA, 0, X.VAL_PREC_CONT_TOTA_LOCA, X.IMP_DESC_SIN_IMPU_TOTA_LOCA) IMP_DESC_TOTA_FACT,
             DECODE(X.VAL_PREC_CATA_UNIT_LOCA, 0, 0, X.VAL_PREC_NETO_TOTA_LOCA) VAL_PREC_NETO_TOTA_FACT,
             DECODE(X.VAL_PREC_CATA_TOTA_LOCA, 0, 0, X.IMP_IMPU_TOTA_LOCA) IMP_IMPU_TOTA_FACT--,
           --  DECODE(X.VAL_PREC_CATA_TOTA_LOCA, 0, 'S', 'N') IND_PROD_GRAT
        FROM FAC_DOCUM_CONTA_LINEA X,
             (SELECT VAL_I18N, VAL_OID FROM GEN_I18N_SICC WHERE ATTR_ENTI = 'MAE_PRODU') Y,
             PED_SOLIC_POSIC Z,
             PRE_OFERT_DETAL P
       WHERE X.PROD_OID_PROD = Y.VAL_OID
        AND  X.SOPO_OID_SOLI_POSI = Z.OID_SOLI_POSI
        AND  Z.OFDE_OID_DETA_OFER = P.OID_DETA_OFER
        AND  NOT EXISTS (SELECT NULL
                         FROM FAC_TIPO_OFERT_EXCLU O
                         WHERE O.TOFE_OID_TIPO_OFER = P.TOFE_OID_TIPO_OFER)
        AND  X.DCCA_OID_CABE = oidConsolidado
        AND  X.NUM_UNID_ATEN > 0
       ORDER BY X.NUM_LINEA) AX
     WHERE ROWNUM <= filaFin)
  WHERE ROWNUM >= filaInicio    ;

r_detalleFactura c_detalleFactura%ROWTYPE;

-- Variables locales
l_oidPais                       NUMBER;
l_oidPeriodo                    NUMBER;
l_oidCanal                      NUMBER;
l_oidMarca                      NUMBER;
l_codigoPeriodo                 VARCHAR2(25);
l_correlativo                   NUMBER := 1;
l_indicadorEnvioLarissa         VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioLarissa');
l_indicadorEnvioUltimoLote      VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioUltimoLote');
l_numeroLoteFacturacion         NUMBER;
l_clob                          CLOB;
l_textoActual                   VARCHAR2(1000) := '';

--NUEVO PARAMETRO
l_numFilas   VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'numeroDetallesFactura');
l_totalPaginas              NUMBER;
l_sizeDetalle               NUMBER;

auxCodClie              VARCHAR2(15);
l_codClie               VARCHAR2(15);

l_filaInicio            NUMBER;
l_filaFin               NUMBER;

BEGIN
    --limpiamos temporal de factura
    EXECUTE IMMEDIATE(' TRUNCATE TABLE IMP_PAQUE_DOCUM_LASER_FACTU');
    auxCodClie:=NULL;

    --inicamos con el caculo de la cabecera
    IMP_PKG_PROCE_COMPA.IMP_PR_CALCU_DOCUM_LASER_FACTU(p_codigoPais,p_codigoMarca,p_codigoCanal,p_codigoPeriodo,p_fechaFacturacion);


    -- Obtenemos los OIDs necesarios
    l_oidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(p_codigoPais);
    l_oidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(CODIGO_CANAL);
    l_oidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(CODIGO_MARCA);
    l_oidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(p_codigoPeriodo, l_oidMarca, l_oidCanal);
    l_codigoPeriodo := SUBSTR(p_codigoPeriodo, 5, 2) || '/' || SUBSTR(p_codigoPeriodo, 1, 4);


    OPEN c_facturaCabecera;
    LOOP
    FETCH c_facturaCabecera INTO r_facturaCabecera;
    EXIT WHEN c_facturaCabecera%NOTFOUND;
        BEGIN

            --OBTENEMOS EL TOTAL DE PAGUNAS POR OID CABECERA
            SELECT COUNT(1)   into l_sizeDetalle
            FROM FAC_DOCUM_CONTA_LINEA X,
                 (SELECT VAL_I18N, VAL_OID FROM GEN_I18N_SICC WHERE ATTR_ENTI = 'MAE_PRODU') Y,
                 PED_SOLIC_POSIC Z,
                 PRE_OFERT_DETAL P
           WHERE X.PROD_OID_PROD = Y.VAL_OID
            AND  X.SOPO_OID_SOLI_POSI = Z.OID_SOLI_POSI
            AND  Z.OFDE_OID_DETA_OFER = P.OID_DETA_OFER
            AND  NOT EXISTS (SELECT NULL
                             FROM FAC_TIPO_OFERT_EXCLU O
                             WHERE O.TOFE_OID_TIPO_OFER = P.TOFE_OID_TIPO_OFER)
            AND  X.DCCA_OID_CABE = r_facturaCabecera.OID_CABE
            AND  X.NUM_UNID_ATEN > 0;

          IF(l_sizeDetalle > 0) THEN
                    l_totalPaginas := ROUND(l_sizeDetalle/ TO_NUMBER(l_numFilas));

                    if(l_totalPaginas = 0) then
                        l_totalPaginas:=1;
                    end if;

                    l_filaInicio := 1;
                    l_filaFin := TO_NUMBER(l_numFilas);

                    FOR pag IN 1 .. l_totalPaginas LOOP

                            l_codClie:= r_facturaCabecera.COD_CLIE;
                            IF (l_codClie <> auxCodClie OR auxCodClie IS NULL) THEN

                                  INSERT INTO IMP_PAQUE_DOCUM_LASER_FACTU (
                                        COD_CLIE,
                                        XML_LASE_FACT)
                                  VALUES ( r_facturaCabecera.COD_CLIE,
                                         EMPTY_CLOB())
                                  RETURNING XML_LASE_FACT INTO l_clob;

                                -- Inicio del paquete
                                l_textoActual := '<factura>';
                                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                --Inicio Bloque cabecera
                                l_textoActual := '<blqcab>';
                                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<numfact>'|| r_facturaCabecera.NUM_DOCU_LEGA  ||'</numfact>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);


                                    l_textoActual := '<lugar>' || r_facturaCabecera.DES_PAIS || '</lugar>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<dia>'|| to_char(r_facturaCabecera.fec_emis,'dd') || '</dia>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<mes>' || to_char(r_facturaCabecera.fec_emis,'mm') || '</mes>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<ano>' || to_char(r_facturaCabecera.fec_emis,'yyyy') || '</ano>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<pag>Pag. '|| pag || ' / ' || l_totalPaginas || '</pag>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<codconsultora>' || r_facturaCabecera.COD_CLIE || '</codconsultora>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<territorio>' || r_facturaCabecera.COD_ZONA || ' - ' || r_facturaCabecera.COD_TERR ||'</territorio>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<documento>' || r_facturaCabecera.VAL_NUME_IDEN_FISC ||'</documento>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<nombre>' || r_facturaCabecera.NOM_COMP ||'</nombre>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<campana>' || r_facturaCabecera.COD_PERI ||'</campana>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<direccion>' || r_facturaCabecera.VAL_DIRE_COMP ||'</direccion>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                l_textoActual := '</blqcab>';
                                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                               --FIN Bloque cabecera

                               --INICIO BLOQUE DETALLE
                                l_textoActual := '<detalle>';
                                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                OPEN c_detalleFactura( r_facturaCabecera.OID_CABE,l_filaInicio,l_filaFin);
                                LOOP
                                FETCH c_detalleFactura INTO r_detalleFactura;
                                EXIT WHEN c_detalleFactura%NOTFOUND;
                                BEGIN

                                   l_textoActual := '<txt>' || r_detalleFactura.NUM_UNID_ATEN || '<t/>'|| r_detalleFactura.DES_PROD||'<tr/> ';
                                   l_textoActual := l_textoActual || TRIM(TO_CHAR(r_detalleFactura.Val_Prec_Cata_Unit_Loca+r_detalleFactura.Val_Prec_Cont_Unit_Loca,'999999999990.99')) || '<tr/> ';
                                   l_textoActual := l_textoActual || TRIM(TO_CHAR(r_detalleFactura.Val_Prec_Cata_Tota_Loca+r_detalleFactura.Val_Prec_Cont_Tota_Loca,'999999999990.99'));--|| '<tr/> ';

                                   l_textoActual := l_textoActual ||  '</txt>';
                                       /* IF(c_detalleFactura.IND_PROD_GRAT='S') THEN
                                            l_textoActual := l_textoActual ||  'Prom.</txt>';
                                        ELSE
                                            l_textoActual := l_textoActual ||  '</txt>';
                                        END IF;*/

                                   DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                END;
                                END LOOP;
                                CLOSE c_detalleFactura;
                                -- SI NOS ENCONTRAMOS EN LA ULTIMA PAGINA PONEMOS TODAS LAS UNIDADES ATENDIDAS

                                        IF( pag = l_totalPaginas ) THEN
                                            l_textoActual := '<txt>-----</txt>';
                                            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                            l_textoActual := '<txt>'|| r_facturaCabecera.SUM_NUM_UNID_ATEN ||'</txt> ';
                                            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                        END IF;

                                -- Fin de detalle Factura
                                l_textoActual := '</detalle>';
                                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                --
                                    IF( pag = l_totalPaginas ) THEN
                                        l_textoActual := '<pie>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                       /* l_textoActual := '<txt>SubTotal<t/>'||TRIM(TO_CHAR(r_facturaCabecera.SUM_SIMP_TOTA_FACT,'999999999990.99'))||'</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                        l_textoActual := '<txt>(-) Descuentos<t/>'||TRIM(TO_CHAR(r_facturaCabecera.VAL_DESC,'999999999990.99'))||'</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                      --  l_textoActual := '<txt>(+) Promociones<t/>'||TRIM(TO_CHAR(r_facturaCabecera.VAL_PROM,'999999999990.99'))||'</txt>';
                                       -- DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                        l_textoActual := '<txt><t/>-------------</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                        l_textoActual := '<txt>Monto Afecto '||r_facturaCabecera.IND_TASA_IMPU||'<t/>'||TRIM(TO_CHAR(r_facturaCabecera.VAL_BASE_IMPO,'999999999990.99'))||'</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                        l_textoActual := '<txt>(+) '||r_facturaCabecera.IND_TASA_IMPU ||' '||r_facturaCabecera.VAL_TASA_IMPU
                                                                    ||'%<t/>'|| TRIM(TO_CHAR(r_facturaCabecera.VAL_IMPO_VENT,'999999999990.99')) ||'</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                        l_textoActual := '<txt>(+) Flete <t/>'||TRIM(TO_CHAR(r_facturaCabecera.IMP_FLET_TOTA_LOCA,'999999999990.99'))||'</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                        --l_textoActual := '<txt>(-) Promociones <t/>'||TRIM(TO_CHAR(r_facturaCabecera.VAL_PROMO,'999999999990.99'))||'</txt>';
                                        --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                        l_textoActual := '<txt><t/>-------------</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                        l_textoActual := '<txt>Total Factura<t/>'|| TRIM(TO_CHAR(r_facturaCabecera.VAL_TOTA,'999999999990.99')) ||'</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);*/

                                        l_textoActual := '<txt>SUBTOTAL PRODUCTO LINEA<t/>'||TRIM(TO_CHAR(r_facturaCabecera.SUM_SIMP_TOTA_FACT,'999999999990.99'))||'</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                        l_textoActual := '<txt>(-) DESCUENTOS<t/>'||TRIM(TO_CHAR(r_facturaCabecera.Imp_Desc_Tota_Loca,'999999999990.99'))||'</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                        l_textoActual := '<txt><t/>-------------</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                        l_textoActual := '<txt>SUBTOTAL FACTURA<t/>'|| TRIM(TO_CHAR(r_facturaCabecera.SUM_SIMP_TOTA_FACT-r_facturaCabecera.Imp_Desc_Tota_Loca,'999999999990.99')) ||'</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                        l_textoActual := '<txt>(+) IMP VENTAS '|| r_facturaCabecera.VAL_TASA_IMPU
                                                        ||'%<t/>'
                                                        ||TRIM(TO_CHAR(r_facturaCabecera.Imp_Impu_Tota_Loca,'999999999990.99'))||'</txt>';

                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                        l_textoActual := '<txt>(+) FLETES Y SERV. <t/>'||TRIM(TO_CHAR(r_facturaCabecera.IMP_FLET_TOTA_LOCA,'999999999990.99'))||'</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                        l_textoActual := '<txt>Total Factura<t/>'|| TRIM(TO_CHAR(r_facturaCabecera.Val_Tota_Paga_Loca,'999999999990.99')) ||'</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                        l_textoActual := '</pie>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                        l_textoActual := '<total>'|| r_facturaCabecera.SUM_TOTA_LETR ||'</total> ';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                    END IF;

                                 --FIN FACTURA
                                l_textoActual := '</factura>';
                                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                            else
                                -- si se trata de la misma consultora
                                SELECT XML_LASE_FACT INTO l_clob
                                FROM  IMP_PAQUE_DOCUM_LASER_FACTU
                                WHERE   COD_CLIE= r_facturaCabecera.COD_CLIE FOR UPDATE;


                                -- Inicio del paquete
                                l_textoActual := '<factura>';
                                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                --Inicio Bloque cabecera
                                l_textoActual := '<blqcab>';
                                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<numfact>'|| r_facturaCabecera.NUM_DOCU_LEGA  ||'</numfact>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);


                                    l_textoActual := '<lugar>' || r_facturaCabecera.DES_PAIS || '</lugar>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<dia>'|| to_char(r_facturaCabecera.fec_emis,'dd') || '</dia>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<mes>' || to_char(r_facturaCabecera.fec_emis,'mm') || '</mes>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<ano>' || to_char(r_facturaCabecera.fec_emis,'yyyy') || '</ano>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<pag>Pag. '|| pag || ' / ' || l_totalPaginas || '</pag>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<codconsultora>' || r_facturaCabecera.COD_CLIE || '</codconsultora>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<territorio>' || r_facturaCabecera.COD_ZONA || ' - ' || r_facturaCabecera.COD_TERR ||'</territorio>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<documento>' || r_facturaCabecera.VAL_NUME_IDEN_FISC ||'</documento>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<nombre>' || r_facturaCabecera.NOM_COMP ||'</nombre>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<campana>' || r_facturaCabecera.COD_PERI ||'</campana>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<direccion>' || r_facturaCabecera.VAL_DIRE_COMP ||'</direccion>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                l_textoActual := '</blqcab>';
                                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                               --FIN Bloque cabecera

                               --INICIO BLOQUE DETALLE
                                l_textoActual := '<detalle>';
                                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                OPEN c_detalleFactura( r_facturaCabecera.OID_CABE,l_filaInicio,l_filaFin);
                                LOOP
                                FETCH c_detalleFactura INTO r_detalleFactura;
                                EXIT WHEN c_detalleFactura%NOTFOUND;
                                BEGIN

                                   l_textoActual := '<txt>' || r_detalleFactura.NUM_UNID_ATEN || '<t/>'|| r_detalleFactura.DES_PROD||'<tr/> ';
                                   l_textoActual := l_textoActual || TRIM(TO_CHAR(r_detalleFactura.Val_Prec_Cata_Unit_Loca+r_detalleFactura.Val_Prec_Cont_Unit_Loca,'999999999990.99')) || '<tr/> ';
                                   l_textoActual := l_textoActual || TRIM(TO_CHAR(r_detalleFactura.Val_Prec_Cata_Tota_Loca+r_detalleFactura.Val_Prec_Cont_Tota_Loca,'999999999990.99'));--|| '<tr/> ';

                                   l_textoActual := l_textoActual ||  '</txt>';
                                       /* IF(c_detalleFactura.IND_PROD_GRAT='S') THEN
                                            l_textoActual := l_textoActual ||  'Prom.</txt>';
                                        ELSE
                                            l_textoActual := l_textoActual ||  '</txt>';
                                        END IF;*/


                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                END;
                                END LOOP;
                                CLOSE c_detalleFactura;
                                -- SI NOS ENCONTRAMOS EN LA ULTIMA PAGINA PONEMOS TODAS LAS UNIDADES ATENDIDAS

                                        IF( pag = l_totalPaginas ) THEN
                                            l_textoActual := '<txt>-----</txt>';
                                            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                            l_textoActual := '<txt>'|| r_facturaCabecera.SUM_NUM_UNID_ATEN ||'</txt> ';
                                            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                        END IF;

                                -- Fin de detalle Factura
                                l_textoActual := '</detalle>';
                                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                --
                                    IF( pag = l_totalPaginas ) THEN
                                        l_textoActual := '<pie>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                       /* l_textoActual := '<txt>SubTotal<t/>'||TRIM(TO_CHAR(r_facturaCabecera.SUM_SIMP_TOTA_FACT,'999999999990.99'))||'</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                        l_textoActual := '<txt>(-) Descuentos<t/>'||TRIM(TO_CHAR(r_facturaCabecera.VAL_DESC,'999999999990.99'))||'</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                      --  l_textoActual := '<txt>(+) Promociones<t/>'||TRIM(TO_CHAR(r_facturaCabecera.VAL_PROM,'999999999990.99'))||'</txt>';
                                       -- DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                        l_textoActual := '<txt><t/>-------------</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                        l_textoActual := '<txt>Monto Afecto '||r_facturaCabecera.IND_TASA_IMPU||'<t/>'||TRIM(TO_CHAR(r_facturaCabecera.VAL_BASE_IMPO,'999999999990.99'))||'</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                        l_textoActual := '<txt>(+) '||r_facturaCabecera.IND_TASA_IMPU ||' '||r_facturaCabecera.VAL_TASA_IMPU
                                                                    ||'%<t/>'|| TRIM(TO_CHAR(r_facturaCabecera.VAL_IMPO_VENT,'999999999990.99')) ||'</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                        l_textoActual := '<txt>(+) Flete <t/>'||TRIM(TO_CHAR(r_facturaCabecera.IMP_FLET_TOTA_LOCA,'999999999990.99'))||'</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                        --l_textoActual := '<txt>(-) Promociones <t/>'||TRIM(TO_CHAR(r_facturaCabecera.VAL_PROMO,'999999999990.99'))||'</txt>';
                                        --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                        l_textoActual := '<txt><t/>-------------</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                        l_textoActual := '<txt>Total Factura<t/>'|| TRIM(TO_CHAR(r_facturaCabecera.VAL_TOTA,'999999999990.99')) ||'</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);*/


                                        l_textoActual := '<txt>SUBTOTAL PRODUCTO LINEA<t/>'||TRIM(TO_CHAR(r_facturaCabecera.SUM_SIMP_TOTA_FACT,'999999999990.99'))||'</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                        l_textoActual := '<txt>(-) DESCUENTOS<t/>'||TRIM(TO_CHAR(r_facturaCabecera.Imp_Desc_Tota_Loca,'999999999990.99'))||'</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                        l_textoActual := '<txt><t/>-------------</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                        l_textoActual := '<txt>SUBTOTAL FACTURA<t/>'|| TRIM(TO_CHAR(r_facturaCabecera.SUM_SIMP_TOTA_FACT-r_facturaCabecera.Imp_Desc_Tota_Loca,'999999999990.99')) ||'</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                        l_textoActual := '<txt>(+) IMP VENTAS '|| r_facturaCabecera.VAL_TASA_IMPU
                                                        ||'%<t/>'
                                                        ||TRIM(TO_CHAR(r_facturaCabecera.VAL_IMPO_VENT,'999999999990.99'))||'</txt>';

                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                        l_textoActual := '<txt>(+) FLETES Y SERV. <t/>'||TRIM(TO_CHAR(r_facturaCabecera.IMP_FLET_TOTA_LOCA,'999999999990.99'))||'</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                        l_textoActual := '<txt>Total Factura<t/>'|| TRIM(TO_CHAR(r_facturaCabecera.Val_Tota_Paga_Loca,'999999999990.99')) ||'</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                        l_textoActual := '</pie>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                        l_textoActual := '<total>'|| r_facturaCabecera.SUM_TOTA_LETR ||'</total> ';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                    END IF;

                                 --FIN FACTURA
                                l_textoActual := '</factura>';
                                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                            end if;

                            auxCodClie:= l_codClie;
                            l_filaInicio := l_filaInicio + TO_NUMBER(l_numFilas);
                            l_filaFin :=  l_filaFin +TO_NUMBER(l_numFilas);

                   END LOOP;
           END IF;
        END;
    END LOOP;
    CLOSE c_facturaCabecera;
 EXCEPTION
  WHEN OTHERS THEN
     RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_PROCE_PAQUE_DOCUM_FAC_P: '||substr(sqlerrm,1,250));
END IMP_PR_PROCE_PAQUE_DOCUM_FAC_P;

/**************************************************************************
Descripcion         : Genera la FACTURA
Fecha Creacion      : 21/07/2011
Autor               : Sergio Buchelli
Parametros Entrada  :
    p_codigoPais            : Codigo del pais.
    p_codigoPeriodo         : Codigo periodo
    p_fechaFacturacion     : Fecha Facturacion
/**************************************************************************/
PROCEDURE IMP_PR_PROCE_PAQUE_DOCUM_FACTU(p_codigoPais VARCHAR2,
                                         p_codigoMarca      VARCHAR2,
                                         p_codigoCanal      VARCHAR2,
                                         p_codigoPeriodo VARCHAR2,
                                         p_fechaFacturacion VARCHAR2)
IS
w_Filas NUMBER := 1000;
ls_sqlerrm   VARCHAR2(150);

    ln_desc_flete VARCHAR2(2):=nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'descFlete'),'N');

    ln_tipo_fact VARCHAR2(2):=nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'tipoFactCR'),'1');

    l_formatoNumerico           VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'formatoNumerico'),'9999999G990D00');

CURSOR c_facturaCabecera IS
  SELECT x.VAL_NUME_SOLI,
             x.OID_CABE,
             x.NUM_DOCU_LEGA,
             x.COD_PAIS,
             x.DES_PAIS,
             x.FEC_EMIS,
             x.NUM_DOCU_CONT_INTE,
             x.OID_CLIE,
             x.COD_CLIE,
             x.COD_PERI,
             x.COD_REGI,
             x.COD_ZONA,
             x.COD_TERR,
             x.NOM_COMP,
             x.VAL_DIRE_COMP,
             x.VAL_NUME_IDEN_FISC,
             x.IMP_DESC_TOTA_LOCA,
             x.IMP_IMPU_TOTA_LOCA,
             x.IMP_FLET_TOTA_LOCA,
             x.VAL_TOTA_PAGA_LOCA,
             x.SUM_PCON_TOTA_LOCA,
             x.SUM_PNET_TOTA_FACT,
             x.SUM_SIMP_TOTA_FACT,
             x.SUM_NUM_UNID_ATEN,
             x.SUM_TOTA_LETR,
             x.VAL_TASA_IMPU,
             x.IND_TASA_IMPU,
             x.VAL_DESC,
            -- VAL_PROM,
             x.VAL_TOTA,
             x.VAL_IMPO_VENT,
             x.VAL_BASE_IMPO,
             NVL(x.COD_PERI_REFE, ' ') COD_PERI_REFE,
             NVL(x.VAL_NUME_SOLI_REFE, 0) VAL_NUME_SOLI_REFE,
             x.COD_TIPO_DOCU,
             x.VAL_SERI_DOCU_LEGA,
             a.val_impo_desc_flet,
             (select sum(imp_impu_tota_loca) from fac_docum_conta_linea x where x.dcca_oid_cabe=a.oid_cabe) IMPU,
             a.val_tota_paga_loca TOTA
          FROM IMP_PAQUE_GENER_DOCUM_LASER x , fac_docum_conta_cabec a
          where x.oid_cabe=a.oid_cabe
          ORDER BY COD_CLIE, OID_CABE;

r_facturaCabecera c_facturaCabecera%ROWTYPE;

CURSOR c_detalleFactura(oidConsolidado NUMBER,filaInicio NUMBER, filaFin NUMBER, indicador VARCHAR) IS
  SELECT * FROM
     (SELECT AX.*,
           ROWNUM  RNUM
     FROM
      (SELECT   X.DCCA_OID_CABE,
             X.OID OID_LINE,
             X.NUM_LINEA,
             X.NUM_UNID_ATEN,
             X.PROD_OID_PROD,
             Y.VAL_I18N DES_PROD,
             X.VAL_PREC_CATA_UNIT_LOCA,
             X.VAL_PREC_CATA_TOTA_LOCA,
             X.VAL_PREC_CONT_UNIT_LOCA,
             X.VAL_PREC_CONT_TOTA_LOCA,
             X.IMP_DESC_UNIT_LOCA,
             X.IMP_DESC_TOTA_LOCA,
             X.VAL_PREC_FACT_UNIT_LOCA,
             X.VAL_PREC_FACT_TOTA_LOCA,
             X.IMP_IMPU_UNIT_LOCA,
             X.IMP_IMPU_TOTA_LOCA,
             X.VAL_PREC_NETO_UNIT_LOCA,
             X.VAL_PREC_NETO_TOTA_LOCA,
             NVL(X.VAL_PORC_DESC, 0) VAL_PORC_DESC,
             DECODE(X.VAL_PREC_CATA_UNIT_LOCA, 0, X.VAL_PREC_SIN_IMPU_UNIT,  X.VAL_PREC_CATA_UNIT_LOCA) VAL_PREC_CATA_UNIT_FACT,
             DECODE(X.VAL_PREC_CATA_TOTA_LOCA, 0, X.VAL_PREC_SIN_IMPU_TOTA_LOCA, X.VAL_PREC_SIN_IMPU_TOTA_LOCA) VAL_PREC_SIN_IMPU_TOTA_FACT,
             DECODE(X.VAL_PREC_CATA_UNIT_LOCA, 0, X.VAL_PREC_CONT_TOTA_LOCA, X.IMP_DESC_SIN_IMPU_TOTA_LOCA) IMP_DESC_TOTA_FACT,
             DECODE(X.VAL_PREC_CATA_UNIT_LOCA, 0, 0, X.VAL_PREC_NETO_TOTA_LOCA) VAL_PREC_NETO_TOTA_FACT,
             DECODE(X.VAL_PREC_CATA_TOTA_LOCA, 0, 0, X.IMP_IMPU_TOTA_LOCA) IMP_IMPU_TOTA_FACT--,
           --  DECODE(X.VAL_PREC_CATA_TOTA_LOCA, 0, 'S', 'N') IND_PROD_GRAT
        FROM FAC_DOCUM_CONTA_LINEA X,
             (SELECT VAL_I18N, VAL_OID FROM GEN_I18N_SICC WHERE ATTR_ENTI = 'MAE_PRODU') Y,
             PED_SOLIC_POSIC Z,
             PRE_OFERT_DETAL P
       WHERE X.PROD_OID_PROD = Y.VAL_OID
        AND  X.SOPO_OID_SOLI_POSI = Z.OID_SOLI_POSI
        AND  Z.OFDE_OID_DETA_OFER = P.OID_DETA_OFER
        AND  NOT EXISTS (SELECT NULL
                         FROM FAC_TIPO_OFERT_EXCLU O
                         WHERE O.TOFE_OID_TIPO_OFER = P.TOFE_OID_TIPO_OFER)
        AND  X.DCCA_OID_CABE = oidConsolidado
        and  (x.val_prec_cata_tota_loca>0 or indicador<>'S')
        AND  X.NUM_UNID_ATEN > 0
       ORDER BY X.NUM_LINEA) AX
     WHERE ROWNUM <= filaFin)
  WHERE ROWNUM >= filaInicio    ;

r_detalleFactura c_detalleFactura%ROWTYPE;

-- Variables locales
l_oidPais                       NUMBER;
l_oidPeriodo                    NUMBER;
l_oidCanal                      NUMBER;
l_oidMarca                      NUMBER;
l_codigoPeriodo                 VARCHAR2(25);
l_correlativo                   NUMBER := 1;
l_indicadorEnvioLarissa         VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioLarissa');
l_indicadorEnvioUltimoLote      VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioUltimoLote');
l_numeroLoteFacturacion         NUMBER;
l_clob                          CLOB;
l_textoActual                   VARCHAR2(1000) := '';

--NUEVO PARAMETRO
l_numFilas   VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'numeroDetallesFactura');
l_totalPaginas              NUMBER;
l_sizeDetalle               NUMBER;

auxCodClie              VARCHAR2(15);
l_codClie               VARCHAR2(15);

l_filaInicio            NUMBER;
l_filaFin               NUMBER;

BEGIN
    auxCodClie:=NULL;

    --inicamos con el caculo de la cabecera
    IMP_PKG_PROCE_COMPA.IMP_PR_CALCU_DOCUM_LASER_FACTU(p_codigoPais,p_codigoMarca,p_codigoCanal,p_codigoPeriodo,p_fechaFacturacion);


    -- Obtenemos los OIDs necesarios
    l_oidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(p_codigoPais);
    l_oidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(CODIGO_CANAL);
    l_oidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(CODIGO_MARCA);
    l_oidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(p_codigoPeriodo, l_oidMarca, l_oidCanal);
    l_codigoPeriodo := SUBSTR(p_codigoPeriodo, 5, 2) || '/' || SUBSTR(p_codigoPeriodo, 1, 4);


    OPEN c_facturaCabecera;
    LOOP
    FETCH c_facturaCabecera INTO r_facturaCabecera;
    EXIT WHEN c_facturaCabecera%NOTFOUND;
        BEGIN

            --OBTENEMOS EL TOTAL DE PAGUNAS POR OID CABECERA
            SELECT COUNT(1)   into l_sizeDetalle
            FROM FAC_DOCUM_CONTA_LINEA X,
                 (SELECT VAL_I18N, VAL_OID FROM GEN_I18N_SICC WHERE ATTR_ENTI = 'MAE_PRODU') Y,
                 PED_SOLIC_POSIC Z,
                 PRE_OFERT_DETAL P
           WHERE X.PROD_OID_PROD = Y.VAL_OID
            AND  X.SOPO_OID_SOLI_POSI = Z.OID_SOLI_POSI
            AND  Z.OFDE_OID_DETA_OFER = P.OID_DETA_OFER
            AND  NOT EXISTS (SELECT NULL
                             FROM FAC_TIPO_OFERT_EXCLU O
                             WHERE O.TOFE_OID_TIPO_OFER = P.TOFE_OID_TIPO_OFER)
            AND  X.DCCA_OID_CABE = r_facturaCabecera.OID_CABE
            AND  X.NUM_UNID_ATEN > 0;

          IF(l_sizeDetalle > 0) THEN
                    l_totalPaginas := ROUND(l_sizeDetalle/ TO_NUMBER(l_numFilas));

                    if(l_totalPaginas = 0) then
                        l_totalPaginas:=1;
                    end if;

                    l_filaInicio := 1;
                    l_filaFin := TO_NUMBER(l_numFilas);

                    FOR pag IN 1 .. l_totalPaginas LOOP

                            l_codClie:= r_facturaCabecera.COD_CLIE;
                            IF (l_codClie <> auxCodClie OR auxCodClie IS NULL) THEN

                                  INSERT INTO IMP_PAQUE_DOCUM_LASER_FACTU (
                                        COD_CLIE,
                                        XML_LASE_FACT)
                                  VALUES ( r_facturaCabecera.COD_CLIE,
                                         EMPTY_CLOB())
                                  RETURNING XML_LASE_FACT INTO l_clob;

                                -- Inicio del paquete
                                l_textoActual := '<factura>';
                                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                --Inicio Bloque cabecera
                                l_textoActual := '<blqcab>';
                                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<numfact>'|| r_facturaCabecera.NUM_DOCU_LEGA  ||'</numfact>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);


                                    l_textoActual := '<lugar>' || r_facturaCabecera.DES_PAIS || '</lugar>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<dia>'|| to_char(r_facturaCabecera.fec_emis,'dd') || '</dia>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<mes>' || to_char(r_facturaCabecera.fec_emis,'mm') || '</mes>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<ano>' || to_char(r_facturaCabecera.fec_emis,'yyyy') || '</ano>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<pag>Pag. '|| pag || ' / ' || l_totalPaginas || '</pag>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<codconsultora>' || r_facturaCabecera.COD_CLIE || '</codconsultora>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<territorio>' || r_facturaCabecera.COD_ZONA || ' - ' || r_facturaCabecera.COD_TERR ||'</territorio>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<documento>' || r_facturaCabecera.VAL_NUME_IDEN_FISC ||'</documento>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<nombre>' || r_facturaCabecera.NOM_COMP ||'</nombre>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<campana>' || r_facturaCabecera.COD_PERI ||'</campana>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<direccion>' || r_facturaCabecera.VAL_DIRE_COMP ||'</direccion>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                l_textoActual := '</blqcab>';
                                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                               --FIN Bloque cabecera

                               --INICIO BLOQUE DETALLE
                                l_textoActual := '<detalle>';
                                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                OPEN c_detalleFactura( r_facturaCabecera.OID_CABE,l_filaInicio,l_filaFin,ln_desc_flete);
                                LOOP
                                FETCH c_detalleFactura INTO r_detalleFactura;
                                EXIT WHEN c_detalleFactura%NOTFOUND;
                                BEGIN

                                   l_textoActual := '<txt>' || r_detalleFactura.NUM_UNID_ATEN || '<t/>'|| r_detalleFactura.DES_PROD||'<tr/> ';
                                   l_textoActual := l_textoActual || TRIM(TO_CHAR(r_detalleFactura.VAL_PREC_CATA_UNIT_FACT,'999999999990.99')) || '<tr/> ';
                                   l_textoActual := l_textoActual || TRIM(TO_CHAR(r_detalleFactura.VAL_PREC_SIN_IMPU_TOTA_FACT,'999999999990.99'));--|| '<tr/> ';

                                   l_textoActual := l_textoActual ||  '</txt>';
                                       /* IF(c_detalleFactura.IND_PROD_GRAT='S') THEN
                                            l_textoActual := l_textoActual ||  'Prom.</txt>';
                                        ELSE
                                            l_textoActual := l_textoActual ||  '</txt>';
                                        END IF;*/

                                   DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                END;
                                END LOOP;
                                CLOSE c_detalleFactura;
                                -- SI NOS ENCONTRAMOS EN LA ULTIMA PAGINA PONEMOS TODAS LAS UNIDADES ATENDIDAS

                                        IF( pag = l_totalPaginas ) THEN
                                            l_textoActual := '<txt>-----</txt>';
                                            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                            l_textoActual := '<txt>'|| r_facturaCabecera.SUM_NUM_UNID_ATEN ||'</txt> ';
                                            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                        END IF;

                                -- Fin de detalle Factura
                                l_textoActual := '</detalle>';
                                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                --
                                    IF( pag = l_totalPaginas ) THEN
                                        l_textoActual := '<pie>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                       /* l_textoActual := '<txt>SubTotal<t/>'||TRIM(TO_CHAR(r_facturaCabecera.SUM_SIMP_TOTA_FACT,'999999999990.99'))||'</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                        l_textoActual := '<txt>(-) Descuentos<t/>'||TRIM(TO_CHAR(r_facturaCabecera.VAL_DESC,'999999999990.99'))||'</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                      --  l_textoActual := '<txt>(+) Promociones<t/>'||TRIM(TO_CHAR(r_facturaCabecera.VAL_PROM,'999999999990.99'))||'</txt>';
                                       -- DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                        l_textoActual := '<txt><t/>-------------</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                        l_textoActual := '<txt>Monto Afecto '||r_facturaCabecera.IND_TASA_IMPU||'<t/>'||TRIM(TO_CHAR(r_facturaCabecera.VAL_BASE_IMPO,'999999999990.99'))||'</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                        l_textoActual := '<txt>(+) '||r_facturaCabecera.IND_TASA_IMPU ||' '||r_facturaCabecera.VAL_TASA_IMPU
                                                                    ||'%<t/>'|| TRIM(TO_CHAR(r_facturaCabecera.VAL_IMPO_VENT,'999999999990.99')) ||'</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                        l_textoActual := '<txt>(+) Flete <t/>'||TRIM(TO_CHAR(r_facturaCabecera.IMP_FLET_TOTA_LOCA,'999999999990.99'))||'</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                        --l_textoActual := '<txt>(-) Promociones <t/>'||TRIM(TO_CHAR(r_facturaCabecera.VAL_PROMO,'999999999990.99'))||'</txt>';
                                        --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                        l_textoActual := '<txt><t/>-------------</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                        l_textoActual := '<txt>Total Factura<t/>'|| TRIM(TO_CHAR(r_facturaCabecera.VAL_TOTA,'999999999990.99')) ||'</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);*/
                                        if ln_tipo_fact='1' then

                                              l_textoActual := '<txt>SUBTOTAL PRODUCTO LINEA<t/>'||TRIM(TO_CHAR(r_facturaCabecera.SUM_SIMP_TOTA_FACT,'999999999990.99'))||'</txt>';
                                              DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                              l_textoActual := '<txt>(-) DESCUENTOS<t/>'||TRIM(TO_CHAR(r_facturaCabecera.VAL_DESC,'999999999990.99'))||'</txt>';
                                              DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                              l_textoActual := '<txt><t/>-------------</txt>';
                                              DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                              l_textoActual := '<txt>SUBTOTAL FACTURA<t/>'|| TRIM(TO_CHAR(r_facturaCabecera.SUM_PNET_TOTA_FACT,'999999999990.99')) ||'</txt>';
                                              DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                              l_textoActual := '<txt>(+) IMP VENTAS '|| r_facturaCabecera.VAL_TASA_IMPU
                                                              ||'%<t/>'
                                                              ||TRIM(TO_CHAR(r_facturaCabecera.VAL_IMPO_VENT,'999999999990.99'))||'</txt>';

                                              DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                              l_textoActual := '<txt>(+) FLETES Y SERV. <t/>'||TRIM(TO_CHAR(r_facturaCabecera.IMP_FLET_TOTA_LOCA,'999999999990.99'))||'</txt>';
                                              DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                              if ln_desc_flete='S' then
                                                   l_textoActual := '<txt>(-) CREDITO IVA FLETE <t/>'||TRIM(TO_CHAR(r_facturaCabecera.val_impo_desc_flet,'999999999990.99'))||'</txt>';
                                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                                    r_facturaCabecera.VAL_TOTA:=r_facturaCabecera.VAL_TOTA-nvl(r_facturaCabecera.val_impo_desc_flet,0);
                                              end if;

                                              l_textoActual := '<txt>Total Factura<t/>'|| TRIM(TO_CHAR(r_facturaCabecera.VAL_TOTA,'999999999990.99')) ||'</txt>';
                                              DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                              l_textoActual := '</pie>';
                                              DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                              l_textoActual := '<total>'|| r_facturaCabecera.SUM_TOTA_LETR ||'</total> ';
                                              DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                       else
                                              l_textoActual := '<txt>SUBTOTAL PRODUCTO LINEA<t/>'||TRIM(TO_CHAR(r_facturaCabecera.SUM_SIMP_TOTA_FACT,l_formatoNumerico))||'</txt>';
                                              DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                              l_textoActual := '<txt>(+) IMP VENTAS '|| r_facturaCabecera.VAL_TASA_IMPU
                                                              ||'%<t/>'
                                                              ||TRIM(TO_CHAR(r_facturaCabecera.IMPU,l_formatoNumerico))||'</txt>';

                                              DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                              l_textoActual := '<txt><t/>-------------</txt>';
                                              DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                              l_textoActual := '<txt>SUBTOTAL FACTURA<t/>'|| TRIM(TO_CHAR(r_facturaCabecera.SUM_SIMP_TOTA_FACT+r_facturaCabecera.IMPU,l_formatoNumerico)) ||'</txt>';
                                              DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                              l_textoActual := '<txt>(-) DESCUENTOS<t/>'||TRIM(TO_CHAR(r_facturaCabecera.VAL_DESC,l_formatoNumerico))||'</txt>';
                                              DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                              l_textoActual := '<txt>(+) FLETES Y SERV. <t/>'||TRIM(TO_CHAR(r_facturaCabecera.IMP_FLET_TOTA_LOCA,l_formatoNumerico))||'</txt>';
                                              DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                              if ln_desc_flete='S' then
                                                   l_textoActual := '<txt>(-) CREDITO IVA FLETE <t/>'||TRIM(TO_CHAR(r_facturaCabecera.val_impo_desc_flet,l_formatoNumerico))||'</txt>';
                                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                                    r_facturaCabecera.VAL_TOTA:=r_facturaCabecera.VAL_TOTA-nvl(r_facturaCabecera.val_impo_desc_flet,0);
                                              end if;

                                              l_textoActual := '<txt>Total Factura<t/>'|| TRIM(TO_CHAR(r_facturaCabecera.TOTA,l_formatoNumerico)) ||'</txt>';
                                              DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                              l_textoActual := '</pie>';
                                              DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                              l_textoActual := '<total>'|| UPPER(GEN_FN_NUME_TO_TEXT(TRUNC(ABS(r_facturaCabecera.TOTA)))) || ' y ' || TO_CHAR(ABS((r_facturaCabecera.TOTA - TRUNC(r_facturaCabecera.TOTA))) * 100) || '/100 COLONES' ||'</total> ';
                                              DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);


                                       end if;
                                    END IF;

                                 --FIN FACTURA
                                l_textoActual := '</factura>';
                                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                            else
                                -- si se trata de la misma consultora
                                SELECT XML_LASE_FACT INTO l_clob
                                FROM  IMP_PAQUE_DOCUM_LASER_FACTU
                                WHERE   COD_CLIE= r_facturaCabecera.COD_CLIE FOR UPDATE;


                                -- Inicio del paquete
                                l_textoActual := '<factura>';
                                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                --Inicio Bloque cabecera
                                l_textoActual := '<blqcab>';
                                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<numfact>'|| r_facturaCabecera.NUM_DOCU_LEGA  ||'</numfact>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);


                                    l_textoActual := '<lugar>' || r_facturaCabecera.DES_PAIS || '</lugar>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<dia>'|| to_char(r_facturaCabecera.fec_emis,'dd') || '</dia>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<mes>' || to_char(r_facturaCabecera.fec_emis,'mm') || '</mes>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<ano>' || to_char(r_facturaCabecera.fec_emis,'yyyy') || '</ano>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<pag>Pag. '|| pag || ' / ' || l_totalPaginas || '</pag>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<codconsultora>' || r_facturaCabecera.COD_CLIE || '</codconsultora>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<territorio>' || r_facturaCabecera.COD_ZONA || ' - ' || r_facturaCabecera.COD_TERR ||'</territorio>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<documento>' || r_facturaCabecera.VAL_NUME_IDEN_FISC ||'</documento>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<nombre>' || r_facturaCabecera.NOM_COMP ||'</nombre>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<campana>' || r_facturaCabecera.COD_PERI ||'</campana>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                    l_textoActual := '<direccion>' || r_facturaCabecera.VAL_DIRE_COMP ||'</direccion>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                l_textoActual := '</blqcab>';
                                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                               --FIN Bloque cabecera

                               --INICIO BLOQUE DETALLE
                                l_textoActual := '<detalle>';
                                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                OPEN c_detalleFactura( r_facturaCabecera.OID_CABE,l_filaInicio,l_filaFin, ln_desc_flete);
                                LOOP
                                FETCH c_detalleFactura INTO r_detalleFactura;
                                EXIT WHEN c_detalleFactura%NOTFOUND;
                                BEGIN

                                   l_textoActual := '<txt>' || r_detalleFactura.NUM_UNID_ATEN || '<t/>'|| r_detalleFactura.DES_PROD||'<tr/> ';
                                   l_textoActual := l_textoActual || TRIM(TO_CHAR(r_detalleFactura.VAL_PREC_CATA_UNIT_FACT,'999999999990.99')) || '<tr/> ';
                                   l_textoActual := l_textoActual || TRIM(TO_CHAR(r_detalleFactura.VAL_PREC_SIN_IMPU_TOTA_FACT,'999999999990.99'));--|| '<tr/> ';

                                   l_textoActual := l_textoActual ||  '</txt>';
                                       /* IF(c_detalleFactura.IND_PROD_GRAT='S') THEN
                                            l_textoActual := l_textoActual ||  'Prom.</txt>';
                                        ELSE
                                            l_textoActual := l_textoActual ||  '</txt>';
                                        END IF;*/


                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                END;
                                END LOOP;
                                CLOSE c_detalleFactura;
                                -- SI NOS ENCONTRAMOS EN LA ULTIMA PAGINA PONEMOS TODAS LAS UNIDADES ATENDIDAS

                                        IF( pag = l_totalPaginas ) THEN
                                            l_textoActual := '<txt>-----</txt>';
                                            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                            l_textoActual := '<txt>'|| r_facturaCabecera.SUM_NUM_UNID_ATEN ||'</txt> ';
                                            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                        END IF;

                                -- Fin de detalle Factura
                                l_textoActual := '</detalle>';
                                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                --
                                    IF( pag = l_totalPaginas ) THEN
                                        l_textoActual := '<pie>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                       /* l_textoActual := '<txt>SubTotal<t/>'||TRIM(TO_CHAR(r_facturaCabecera.SUM_SIMP_TOTA_FACT,'999999999990.99'))||'</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                        l_textoActual := '<txt>(-) Descuentos<t/>'||TRIM(TO_CHAR(r_facturaCabecera.VAL_DESC,'999999999990.99'))||'</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                      --  l_textoActual := '<txt>(+) Promociones<t/>'||TRIM(TO_CHAR(r_facturaCabecera.VAL_PROM,'999999999990.99'))||'</txt>';
                                       -- DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                        l_textoActual := '<txt><t/>-------------</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                        l_textoActual := '<txt>Monto Afecto '||r_facturaCabecera.IND_TASA_IMPU||'<t/>'||TRIM(TO_CHAR(r_facturaCabecera.VAL_BASE_IMPO,'999999999990.99'))||'</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                        l_textoActual := '<txt>(+) '||r_facturaCabecera.IND_TASA_IMPU ||' '||r_facturaCabecera.VAL_TASA_IMPU
                                                                    ||'%<t/>'|| TRIM(TO_CHAR(r_facturaCabecera.VAL_IMPO_VENT,'999999999990.99')) ||'</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                        l_textoActual := '<txt>(+) Flete <t/>'||TRIM(TO_CHAR(r_facturaCabecera.IMP_FLET_TOTA_LOCA,'999999999990.99'))||'</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                        --l_textoActual := '<txt>(-) Promociones <t/>'||TRIM(TO_CHAR(r_facturaCabecera.VAL_PROMO,'999999999990.99'))||'</txt>';
                                        --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                        l_textoActual := '<txt><t/>-------------</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                        l_textoActual := '<txt>Total Factura<t/>'|| TRIM(TO_CHAR(r_facturaCabecera.VAL_TOTA,'999999999990.99')) ||'</txt>';
                                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);*/

                                        if ln_tipo_fact='1' then

                                              l_textoActual := '<txt>SUBTOTAL PRODUCTO LINEA<t/>'||TRIM(TO_CHAR(r_facturaCabecera.SUM_SIMP_TOTA_FACT,'999999999990.99'))||'</txt>';
                                              DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                              l_textoActual := '<txt>(-) DESCUENTOS<t/>'||TRIM(TO_CHAR(r_facturaCabecera.VAL_DESC,'999999999990.99'))||'</txt>';
                                              DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                              l_textoActual := '<txt><t/>-------------</txt>';
                                              DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                              l_textoActual := '<txt>SUBTOTAL FACTURA<t/>'|| TRIM(TO_CHAR(r_facturaCabecera.SUM_PNET_TOTA_FACT,'999999999990.99')) ||'</txt>';
                                              DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                              l_textoActual := '<txt>(+) IMP VENTAS '|| r_facturaCabecera.VAL_TASA_IMPU
                                                              ||'%<t/>'
                                                              ||TRIM(TO_CHAR(r_facturaCabecera.VAL_IMPO_VENT,'999999999990.99'))||'</txt>';

                                              DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                              l_textoActual := '<txt>(+) FLETES Y SERV. <t/>'||TRIM(TO_CHAR(r_facturaCabecera.IMP_FLET_TOTA_LOCA,'999999999990.99'))||'</txt>';
                                              DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                              if ln_desc_flete='S' then
                                                   l_textoActual := '<txt>(-) CREDITO IVA FLETE <t/>'||TRIM(TO_CHAR(r_facturaCabecera.val_impo_desc_flet,'999999999990.99'))||'</txt>';
                                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                                    r_facturaCabecera.VAL_TOTA:=r_facturaCabecera.VAL_TOTA-nvl(r_facturaCabecera.val_impo_desc_flet,0);
                                              end if;

                                              l_textoActual := '<txt>Total Factura<t/>'|| TRIM(TO_CHAR(r_facturaCabecera.VAL_TOTA,'999999999990.99')) ||'</txt>';
                                              DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                              l_textoActual := '</pie>';
                                              DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                              l_textoActual := '<total>'|| r_facturaCabecera.SUM_TOTA_LETR ||'</total> ';
                                              DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                        else
                                              l_textoActual := '<txt>SUBTOTAL PRODUCTO LINEA<t/>'||TRIM(TO_CHAR(r_facturaCabecera.SUM_SIMP_TOTA_FACT,l_formatoNumerico))||'</txt>';
                                              DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                              l_textoActual := '<txt>(+) IMP VENTAS '|| r_facturaCabecera.VAL_TASA_IMPU
                                                              ||'%<t/>'
                                                              ||TRIM(TO_CHAR(r_facturaCabecera.IMPU,l_formatoNumerico))||'</txt>';

                                              DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                              l_textoActual := '<txt><t/>-------------</txt>';
                                              DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                              l_textoActual := '<txt>SUBTOTAL FACTURA<t/>'|| TRIM(TO_CHAR(r_facturaCabecera.SUM_SIMP_TOTA_FACT+r_facturaCabecera.IMPU,l_formatoNumerico)) ||'</txt>';
                                              DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                              l_textoActual := '<txt>(-) DESCUENTOS<t/>'||TRIM(TO_CHAR(r_facturaCabecera.VAL_DESC,l_formatoNumerico))||'</txt>';
                                              DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                              l_textoActual := '<txt>(+) FLETES Y SERV. <t/>'||TRIM(TO_CHAR(r_facturaCabecera.IMP_FLET_TOTA_LOCA,l_formatoNumerico))||'</txt>';
                                              DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                              if ln_desc_flete='S' then
                                                   l_textoActual := '<txt>(-) CREDITO IVA FLETE <t/>'||TRIM(TO_CHAR(r_facturaCabecera.val_impo_desc_flet,l_formatoNumerico))||'</txt>';
                                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                                    r_facturaCabecera.VAL_TOTA:=r_facturaCabecera.VAL_TOTA-nvl(r_facturaCabecera.val_impo_desc_flet,0);
                                              end if;

                                              l_textoActual := '<txt>Total Factura<t/>'|| TRIM(TO_CHAR(r_facturaCabecera.TOTA,l_formatoNumerico)) ||'</txt>';
                                              DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                              l_textoActual := '</pie>';
                                              DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                              l_textoActual := '<total>'|| UPPER(GEN_FN_NUME_TO_TEXT(TRUNC(ABS(r_facturaCabecera.TOTA)))) || ' y ' || TO_CHAR(ABS((r_facturaCabecera.TOTA - TRUNC(r_facturaCabecera.TOTA))) * 100) || '/100 COLONES'  ||'</total> ';
                                              DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                                        end if;
                                    END IF;

                                 --FIN FACTURA
                                l_textoActual := '</factura>';
                                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                            end if;

                            auxCodClie:= l_codClie;
                            l_filaInicio := l_filaInicio + TO_NUMBER(l_numFilas);
                            l_filaFin :=  l_filaFin +TO_NUMBER(l_numFilas);

                   END LOOP;
           END IF;
        END;
    END LOOP;
    CLOSE c_facturaCabecera;
 EXCEPTION
  WHEN OTHERS THEN
     RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_PROCE_PAQUE_DOCUM_FACTU: '||substr(sqlerrm,1,250));
END IMP_PR_PROCE_PAQUE_DOCUM_FACTU;

/**************************************************************************
Descripcion         : Proceso que reemplaza los caracteres especiales
Fecha Creacion      : 10/11/2011
Autor               : Sergio Buchelli
***************************************************************************/

PROCEDURE IMP_PR_REEMP_CARAC_DOCUM_FACTU IS

-- Variables a utilizar
l_clob              CLOB;

CURSOR c_paquetes IS
SELECT COD_CLIE,
       XML_LASE_FACT
FROM IMP_PAQUE_DOCUM_LASER_FACTU;


r_paquetes c_paquetes%ROWTYPE;

CURSOR c_caracteres IS
SELECT VAL_CARA, VAL_REEM
FROM IMP_CARAC_REEMP
WHERE EST_CARE = '1'
ORDER BY ORD_REEM;


r_caracteres c_caracteres%ROWTYPE;

l_pos    NUMBER := 1;
l_startPos   NUMBER := 1;
l_longitud   NUMBER;

BEGIN

    /* Iteramos sobre cada una de las lineas del paquete documentario */

    OPEN c_paquetes;
    LOOP
    FETCH c_paquetes INTO r_paquetes;
    EXIT WHEN c_paquetes%NOTFOUND;

        /* Iteramos sobre cada uno de los caracteres especiales a reemplazar */

        OPEN c_caracteres;
        LOOP
        FETCH c_caracteres INTO r_caracteres;
        EXIT WHEN c_caracteres%NOTFOUND;

            l_startPos := 1;
            l_longitud := LENGTH(r_caracteres.VAL_REEM);
            l_pos := INSTR(r_paquetes.XML_LASE_FACT, r_caracteres.VAL_CARA, l_startPos);
            WHILE(l_pos != 0) LOOP

                SELECT XML_LASE_FACT
                INTO l_clob
                FROM IMP_PAQUE_DOCUM_LASER_FACTU
                WHERE COD_CLIE = r_paquetes.COD_CLIE
                FOR UPDATE;


                -- Reemplazamos el texto en el clob
                IMP_PKG_PROCE_GENER.IMP_PR_LOB_REPLACE(l_clob, r_caracteres.VAL_CARA, r_caracteres.VAL_REEM, l_startPos);

                l_startPos := l_pos + l_longitud;
                l_pos := INSTR(l_clob, r_caracteres.VAL_CARA, l_startPos);
            END LOOP;

        END LOOP;

        CLOSE c_caracteres;
        COMMIT;

    END LOOP;

    CLOSE c_paquetes;

END IMP_PR_REEMP_CARAC_DOCUM_FACTU;



/**************************************************************************
Descripcion         : Genera el archivo de nota de credito
Fecha Creacion      : 10/11/2011
Autor               : Sergio Buchelli
Parametros Entrada  :
    p_codigoPais            : Codigo del pais.
    p_nombreArchivo         : Nombre del archivo de texto.
    p_directorio            : Ruta de generacion del archivo.
***************************************************************************/

PROCEDURE IMP_PR_GENER_ARCHI_FACTU_LASER(p_codigoPais VARCHAR2,
                                         p_nombreArchivo VARCHAR2,
                                         p_directorio VARCHAR2) IS

CURSOR c_paquete(p_inicio VARCHAR, p_fin VARCHAR) IS
select p_inicio || x.XML_LASE_FACT || p_fin xml_paqu_docu
from imp_paque_docum_laser_factu x
order by x.COD_CLIE;


r_paquete c_paquete%ROWTYPE;

-- Variables para la escritura del archivo
l_output         UTL_FILE.file_type;
l_amt            NUMBER DEFAULT 4000;
l_offset         NUMBER DEFAULT 1;
l_length         NUMBER := 0 ;
x                VARCHAR2(32000);

-- Variable a contener el mensaje de la excepcion a lanzar
l_mensajeError              VARCHAR2(500);
l_inicioArchivo             VARCHAR2(500);
l_finArchivo                VARCHAR2(500);
l_cambioLineaRetornoCarro   VARCHAR2(2) := CHR(13) || CHR(10);

l_iniciodocu                VARCHAR2(100);
l_findocu                VARCHAR2(100);

l_auto                   VARCHAR2(100);
l_leyenda                   VARCHAR2(255);
l_fecha                  DATE;

BEGIN

     l_iniciodocu:=nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'tagInicio'),'<pd>');
     l_findocu:=nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'tagFin'),'</pd>');

        IMP_PR_REEMP_CARAC_DOCUM_FACTU;

    -- Creamos la referencia al archivo
    l_output := UTL_FILE.fopen (p_directorio, p_nombreArchivo, 'wb', 32760);

    -- Creamos las cadenas de inicio y fin del archivo
    l_inicioArchivo := '<?xml version="1.0" encoding="iso-8859-1"?>' || l_cambioLineaRetornoCarro;
    l_inicioArchivo := l_inicioArchivo || '<spoolpd>' || l_cambioLineaRetornoCarro;
    l_finArchivo    := '</spoolpd>';

    if p_codigoPais='BOE' then

       SELECT a.val_nume_auto, a.fec_limi, a.val_obse
      INTO l_auto, l_fecha, l_leyenda
      FROM fac_docum_subac a
     WHERE a.tido_oid_tipo_docu = 1
       AND a.sbac_oid_sbac = 888;


       l_inicioArchivo:='<?xml version="1.0" encoding="iso-8859-1"?>' || l_cambioLineaRetornoCarro;
       l_inicioArchivo := l_inicioArchivo || '<EnvioFAC>' || l_cambioLineaRetornoCarro;
       l_inicioArchivo := l_inicioArchivo || '<SetFacturas>' || l_cambioLineaRetornoCarro;
       l_inicioArchivo := l_inicioArchivo || '<DosificacionFacturas>' || l_cambioLineaRetornoCarro;
       l_inicioArchivo := l_inicioArchivo || '<NumeroAutorizacion>' || l_auto || '</NumeroAutorizacion>' || l_cambioLineaRetornoCarro;
       l_inicioArchivo := l_inicioArchivo || '<FechaLimiteEmision>' || to_char(l_fecha,'yyyymmdd') || '</FechaLimiteEmision>' || l_cambioLineaRetornoCarro;
       l_inicioArchivo := l_inicioArchivo || '<LeyendaDeFactura>' || l_leyenda || '</LeyendaDeFactura>' || l_cambioLineaRetornoCarro;
       l_inicioArchivo := l_inicioArchivo || '</DosificacionFacturas>';

       l_finArchivo    := '</SetFacturas></EnvioFAC>';

       l_iniciodocu := '';
       l_findocu := '';

    end if;


    -- Escribimos los caracteres de inicio de archivo
    UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(l_inicioArchivo), TRUE);

    -- Iteramos sobre el cursor
    OPEN c_paquete(l_iniciodocu,l_findocu);
    LOOP
        FETCH c_paquete INTO r_paquete;
        EXIT WHEN c_paquete%NOTFOUND;
        l_length := DBMS_LOB.GETLENGTH(r_paquete.xml_paqu_docu);
        l_offset := 1;
        l_amt := 4000;

        -- Escribimos los bloques en el archivo
        WHILE (l_offset <= l_length) LOOP
            IF (l_amt > (l_length - l_offset)) THEN l_amt := l_length - l_offset + 1; END IF;

            dbms_lob.read (r_paquete.xml_paqu_docu, l_amt, l_offset, x);
            UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(x), TRUE);
            l_offset := l_offset + l_amt;
            x := null;
        END LOOP;


        -- Escribimos el salto de linea
        UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(l_cambioLineaRetornoCarro), TRUE);

    END LOOP;


    -- Cerramos el cursor
    CLOSE c_paquete;

    -- Escribimos los caracteres de fin de archivo
    UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(l_finArchivo), TRUE);

    -- Cerramos el archivo
    UTL_FILE.fclose (l_output);

EXCEPTION
WHEN UTL_FILE.INTERNAL_ERROR THEN
    l_mensajeError:= 'ERROR INTERNO DEL MANEJADOR DE ARCHIVOS';
    RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

WHEN UTL_FILE.INVALID_FILEHANDLE THEN
    l_mensajeError:= 'EL ARCHIVO NO ESTA ABIERTO O NO ES VALIDO';
    RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

WHEN UTL_FILE.INVALID_MODE THEN
    l_mensajeError:= 'MODO INVALIDO AL ABRIR EL ARCHIVO';
    RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

WHEN UTL_FILE.WRITE_ERROR THEN
       l_mensajeError:= 'ERROR AL ESCRIBIR EN EL ARCHIVO O NO HAY ESPACIO EN DISCO';
       RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

WHEN UTL_FILE.INVALID_OPERATION THEN
    l_mensajeError:= 'MODO INVALIDO AL ABRIR EL ARCHIVO';
    RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

WHEN UTL_FILE.INVALID_PATH THEN
    l_mensajeError:= 'ERROR EN LA RUTA DEL ARCHIVO, ARCHIVO NO ES ACCESIBLE';
    RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

WHEN OTHERS THEN
     RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_GENER_ARCHI_FACTU_LASER: '||substr(sqlerrm,1,250));

END IMP_PR_GENER_ARCHI_FACTU_LASER;



/**************************************************************************
Descripcion         : Realiza el 'merge' entre el archivo XML del SiCC, el archivo
                      XML de Cupon de Pago y los bloques XML de las Ultimas Noticias
                      Privilege, el resultado final va a la tabla IMP_PAQUE_DOCUM.
Fecha Creacion      : 05/11/2008
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Version             : Final (Alfa|Final)
Cambios Importantes : Ninguno

IMP-172
***************************************************************************/
PROCEDURE IMP_PR_PROCE_COMPA_FINAL(p_valnumesoli NUMBER, p_codclie varchar2) IS

-- Variable usada para eliminar los detalles erroneos
lv_eliminacionDetalles VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorEliminacionDetallesErroneos');
l_clob                 CLOB;
l_clob_temp            CLOB;
l_clob_temp2           CLOB;


CURSOR c_pado IS
select cod_pado from IMP_COMPA_PAQUE_DOCUM
;

r_pado c_pado%ROWTYPE;


CURSOR c_form(p_codpado varchar2) IS
select a.cod_form from IMP_FORMU_PAQUE_DOCU2 a
where a.cod_pado=p_codpado and a.est_form=1
order by a.num_orde_secu
;

r_form c_form%ROWTYPE;

/*CURSOR c_borec IS
select a.xml_cons from IMP_PAQUE_DOCUM_BOREC a where a.cod_cons
not in (select b.cod_clie from imp_paque_docum_final b)
;

r_borec c_borec%ROWTYPE;

*/

    ln_oidperi    NUMBER(10);
--    ln_borec      NUMBER(1):=0;
    lv_fecfact   varchar2(20);

    lv_codpais   varchar2(20);
    --w_filas        NUMBER(12);

ped_serv NUMBER := 0;
ped_expr NUMBER := 0;
ped_flex NUMBER := 0;
lv_indicadorPedidoServicio   VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorPedidoServicio');
lv_indicadorNMP              VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorNMP');
lv_tagInicioPedidoServicio   VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagInicioPedidoServicio');
lv_tagInicioPedidoServicio2   VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagInicioPedidoServicio2');
lv_tagInicioPedidoServicio3   VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagInicioPedidoServicio3');
lv_tagInicio VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagInicio');
lv_tagFin VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagFin');


begin



    OPEN c_pado;
    LOOP
    FETCH c_pado INTO r_pado;
    EXIT WHEN c_pado%NOTFOUND;


            INSERT INTO IMP_PAQUE_DOCUM_FINAL(
            COR_PADO,
            COD_CLIE,
            NUM_PADO,
            VAL_NUME_SOLI,
            XML_CONS
            )
            VALUES (
            p_valnumesoli,
            p_codclie,
            r_pado.cod_pado,
            p_valnumesoli,
            EMPTY_CLOB())
            RETURNING XML_CONS INTO l_clob;

            if lv_indicadorPedidoServicio='1' then

            select count(1)
            into ped_serv
            from ped_solic_cabec a
            where a.val_nume_soli=p_valnumesoli
            and a.clie_oid_clie=(select oid_clie from mae_clien where cod_clie=p_codclie)
            and
            (
            exists (select 1 from ped_solic_cabec where soca_oid_soli_cabe=a.oid_soli_cabe and ind_oc=1)
            or (
                   exists (select 1
                          from ped_solic_cabec x, ped_tipo_solic_pais y, ped_tipo_solic z, gen_i18n_sicc_comun gen
                          where x.tspa_oid_tipo_soli_pais=y.oid_tipo_soli_pais
                          and y.tsol_oid_tipo_soli=z.oid_tipo_soli
                          and z.oid_tipo_soli=gen.val_oid
                          and gen.attr_enti='PED_TIPO_SOLIC'
                          and gen.val_i18n like '%NMP%'
                          and x.soca_oid_soli_cabe=a.oid_soli_cabe
                          )
                   and lv_indicadorNMP='1'
               )
            )
            ;

            select count(1)
            into ped_expr
            from ped_solic_cabec a
            where a.val_nume_soli=p_valnumesoli
            and a.clie_oid_clie=(select oid_clie from mae_clien where cod_clie=p_codclie)
            and exists (select 1 from ped_solic_cabec, ped_tipo_solic_pais, ped_tipo_solic, gen_i18n_sicc_comun
            where soca_oid_soli_cabe=a.oid_soli_cabe and tspa_oid_tipo_soli_pais=oid_tipo_soli_pais
            and tsol_oid_tipo_soli=oid_tipo_soli and oid_tipo_soli=val_oid and attr_enti='PED_TIPO_SOLIC'
            and upper(val_i18n) like '%XPRES%'
            );


            select count(1)
            into ped_flex
            from ped_solic_cabec a
            where a.val_nume_soli=p_valnumesoli
            and a.clie_oid_clie=(select oid_clie from mae_clien where cod_clie=p_codclie)
            and exists (select 1 from ped_solic_cabec, ped_tipo_solic_pais, ped_tipo_solic, gen_i18n_sicc_comun
            where soca_oid_soli_cabe=a.oid_soli_cabe and tspa_oid_tipo_soli_pais=oid_tipo_soli_pais
            and tsol_oid_tipo_soli=oid_tipo_soli and oid_tipo_soli=val_oid and attr_enti='PED_TIPO_SOLIC'
            and upper(val_i18n) like '%CARGO POR USO%'
            and (select count(1) from ped_solic_cabec where soca_oid_soli_cabe=a.oid_soli_cabe)=1
            );

            end if;

            if ped_serv=0 and lv_indicadorPedidoServicio='1' then
              if ped_expr=0 and ped_flex=0 then
                l_clob_temp:=l_clob_temp || lv_tagInicioPedidoServicio;
                 else
                  if ped_expr>0 then
                          l_clob_temp:=l_clob_temp || lv_tagInicioPedidoServicio2;
                  else
                         l_clob_temp:=l_clob_temp || lv_tagInicioPedidoServicio3;
                 end if;
              end if;
            else
                l_clob_temp:=l_clob_temp || lv_tagInicio;
            end if;



            OPEN c_form(r_pado.cod_pado);
            LOOP
            FETCH c_form INTO r_form;
            EXIT WHEN c_form%NOTFOUND;

begin
                  if r_form.cod_form='BD' then
                     select x.xml_cons into l_clob_temp2 from imp_paque_docum_bolet_despa x
                     where x.val_nume_soli=p_valnumesoli;

                     l_clob_temp:=l_clob_temp || l_clob_temp2;
                     --DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);


                  end if;
                  if r_form.cod_form='OC' then
                     select x.xml_cons into l_clob_temp2 from imp_paque_docum_ocs x
                     where x.val_nume_soli=p_valnumesoli;

                     l_clob_temp:=l_clob_temp || l_clob_temp2;
                     --DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);

                  end if;
                  if r_form.cod_form='UN' then
                     select x.xml_unot into l_clob_temp2 from imp_paque_docum_unot x
                     where x.val_nume_soli=p_valnumesoli;

                     l_clob_temp:=l_clob_temp || l_clob_temp2;
                     --DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);

                  end if;
                  if r_form.cod_form='DF' then
                     select x.xml_deta_fact into l_clob_temp2 from imp_paque_docum_detal_factu x
                     where x.val_nume_soli=p_valnumesoli;
                    /*if LENGTH(l_clob_temp)>30000 then
                     DBMS_LOB.writeappend(l_clob, 30000, substr(l_clob_temp,1,30000));
                     DBMS_LOB.writeappend(l_clob, length(substr(l_clob_temp,30001)), substr(l_clob_temp,30001));
                    else
                     DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);
                     end if;*/

                     l_clob_temp:=l_clob_temp || l_clob_temp2;


                  end if;
                  if r_form.cod_form='CP' then
                     select x.xml_cons into l_clob_temp2 from imp_paque_docum_cupon x
                     where x.cod_clie=p_codclie and rownum=1;

                     l_clob_temp:=l_clob_temp || '<frmecc>' || l_clob_temp2 || '</frmecc>';

                     --DBMS_LOB.writeappend(l_clob, LENGTH('<frmecc>'), '<frmecc>');
                     --DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);
                     --DBMS_LOB.writeappend(l_clob, LENGTH('</frmecc>'), '</frmecc>');
                  end if;
                  if r_form.cod_form='CT' then
                     select x.xml_cons into l_clob_temp2 from imp_paque_docum_cupon x
                     where x.cod_clie=p_codclie and rownum=1;

                     l_clob_temp:=l_clob_temp || '<frmecc>' || l_clob_temp2 ;

                     --DBMS_LOB.writeappend(l_clob, LENGTH('<frmecc>'), '<frmecc>');
                     --DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);

                     select x.xml_cuen_corr into l_clob_temp2 from imp_paque_docum_laser_ctact x
                     where x.cod_clie=p_codclie;

                     l_clob_temp:=l_clob_temp || l_clob_temp2 || '</frmecc>' ;

                     --DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);
                     --DBMS_LOB.writeappend(l_clob, LENGTH('</frmecc>'), '</frmecc>');
                  end if;
                  if r_form.cod_form='BR' then
                    -- ln_borec:=1;
                     select x.xml_cons into l_clob_temp2 from imp_paque_docum_borec x
                     where x.cod_cons=p_codclie and rownum=1;

                     l_clob_temp:=l_clob_temp || l_clob_temp2;
                     --DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);
                  end if;
                  if r_form.cod_form='FA' then
                    -- ln_borec:=1;
                     select x.xml_lase_fact into l_clob_temp2 from IMP_PAQUE_DOCUM_LASER_FACTU x
                     where x.cod_clie=p_codclie and rownum=1;

                     l_clob_temp:=l_clob_temp || l_clob_temp2;
                     --DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);
                  end if;
                  if r_form.cod_form='FX' then
                    -- ln_borec:=1;
                     begin
                     select x.xml_cons into l_clob_temp2 from flx_paque_docum x
                     where x.cod_clie=p_codclie and rownum=1;

                     l_clob_temp:=l_clob_temp || l_clob_temp2;
                     exception when others then
                       null;
                     end;
                     --DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);
                  end if;




                  exception when others then
                     continue;
                  end;

            END LOOP;
            CLOSE c_form;
            --DBMS_LOB.writeappend(l_clob, LENGTH('</pd>'), '</pd>');

                  if LENGTH(l_clob_temp)>30000 then
                      while LENGTH(l_clob_temp)>30000
                      loop
                       DBMS_LOB.writeappend(l_clob, 30000, substr(l_clob_temp,1,30000));
                       l_clob_temp:=substr(l_clob_temp,30001);
                       end loop;
                       l_clob_temp:=l_clob_temp || '</pd>';
                       DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);
                  else
                       l_clob_temp:=l_clob_temp || '</pd>';
                       DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);
                   end if;

            l_clob_temp:='';


    END LOOP;
    CLOSE c_pado;
    commit;
END;





PROCEDURE IMP_PR_PROCE_BOLET_HONOR(psCodigoPais        VARCHAR2,
                                   psCodigoPeriodo     VARCHAR2,
                                   psFechaFacturacion  VARCHAR2,
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
  --IMP_PR_GENER_ARCHI_BOLET(psCodigoPais, psNombreArchivo, psDirectorio);

 EXCEPTION
  WHEN OTHERS THEN
     RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_PROCE_BOLET_HONOR: '||substr(sqlerrm,1,250));
END IMP_PR_PROCE_BOLET_HONOR;


/**************************************************************************
Descripcion : Devuelve la descripcion del producto de planit
la Hora en formato dd/mm/yyyy
Fecha Creacion : 12/12/2012
Autor : Jorge Yepez
***************************************************************************/
FUNCTION IMP_FN_DESC_PRODU( p_codigoPais VARCHAR, p_oidprod NUMBER) RETURN VARCHAR2
  IS

l_indicadorEnvioLarissa     VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioLarissa');

lstipoDescProd   VARCHAR(10):=nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'tipoDescProd'),'0');

lsDescProd   VARCHAR(1000):='';

BEGIN
     if lstipoDescProd='0' then
        select val_i18n into lsDescProd from gen_i18n_sicc_pais x
        where x.attr_enti='MAE_PRODU'
        and x.val_oid=p_oidprod;
      else
        begin

        select x.des_com into lsDescProd from ped_descr_produ x, mae_produ y
        where x.cod_sap=y.cod_sap
        and y.oid_prod=p_oidprod;

        exception when no_data_found then

        select val_i18n into lsDescProd from gen_i18n_sicc_pais x
        where x.attr_enti='MAE_PRODU'
        and x.val_oid=p_oidprod;
      end;

      end if;

    RETURN lsDescProd;
END IMP_FN_DESC_PRODU;

/**************************************************************************
Descripcion : Devuelve el oid de la direccion del cliente a mostrar en los
documentos de despacho
la Hora en formato dd/mm/yyyy
Fecha Creacion : 02/07/2012
Autor : Jorge Yepez
***************************************************************************/
FUNCTION IMP_FN_XML_OPOR_AHOR(p_codigoPeriodo VARCHAR,p_oidperi NUMBER,p_oidclie NUMBER) RETURN VARCHAR2
  IS

 lnoidperi6     NUMBER(12);

 lscodperi6   VARCHAR(10):='';

 lsxml   VARCHAR(1000):='';

CURSOR c_ahorro IS
select b.cod_peri
, (select nvl(sum(nvl(val_gana_tota_loca,0)),0) from ped_solic_cabec x, ped_tipo_solic_pais y, ped_tipo_solic z where x.perd_oid_peri=a.oid_peri and x.clie_oid_clie=p_oidclie
and x.tspa_oid_tipo_soli_pais=y.oid_tipo_soli_pais and y.tsol_oid_tipo_soli=z.oid_tipo_soli and z.cod_tipo_soli='SOC') monto
from cra_perio a, seg_perio_corpo b
where a.peri_oid_peri=b.oid_peri and a.oid_peri>=lnoidperi6 and a.oid_peri<=p_oidperi;

r_ahorro c_ahorro%ROWTYPE;


 BEGIN

    lscodperi6 := GEN_FN_CALCU_PERIO(p_codigoPeriodo, -6);
    lnoidperi6 := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(lscodperi6, 2003, 2001);

    lsxml:='<graficobar><barra>';

    -- Iteramos sobre el cursor
    OPEN c_ahorro;
    LOOP
        FETCH c_ahorro INTO r_ahorro;
        EXIT WHEN c_ahorro%NOTFOUND;
             lsxml:=lsxml || '<cmp>' || r_ahorro.cod_peri || '</cmp>';
             lsxml:=lsxml || '<monto>' || r_ahorro.monto || '</monto>';

        -- Escribimos el salto de linea

    END LOOP;
        lsxml:=lsxml || '</barra></graficobar>';

    -- Cerramos el cursor
    CLOSE c_ahorro;


    RETURN lsxml;
 END imp_fn_xml_opor_ahor;

/**************************************************************************
Descripcion : Devuelve el numero de OCS a enviar a las consultoras
la Hora en formato dd/mm/yyyy
Fecha Creacion : 02/07/2012
Autor : Jorge Yepez
***************************************************************************/
FUNCTION IMP_FN_DEVUE_NUM_OCS(p_codigoPais VARCHAR,p_oidclie NUMBER) RETURN NUMBER
  IS

 ln_numocs     NUMBER(12):=1;
 ln_tmp     NUMBER(12):=0;


  lsnumpediweb VARCHAR2(15) := sto_pkg_gener.sto_fn_obten_param_ocr(p_codigoPais,
                                                                         'NUM_WEB_NO_OCS');


 BEGIN

 select count(1) into ln_tmp
 from mae_clien_datos_adici x
 where nvl(x.num_pedi_web,0)>=nvl(lsnumpediweb,'0')
 and x.clie_oid_clie=p_oidclie;

 if ln_tmp>0 and lsnumpediweb is not null then ln_numocs:=0;
 else

     begin
     select d.num_ocs into ln_numocs
     from mae_clien_tipo_subti a
     , mae_clien_clasi b
     , lar_tipo_clien_vip d
     where a.oid_clie_tipo_subt=b.ctsu_oid_clie_tipo_subt
     and a.clie_oid_clie=p_oidclie
     and b.clas_oid_clas=d.oid_clas
     and b.tccl_oid_tipo_clasi=d.oid_tipo_clas
     ;
     exception when no_data_found then
       ln_numocs:=1;
     end;

 end if;
 RETURN ln_numocs;
END imp_fn_devue_num_ocs;

/***************************************************************************
Descripcion       : Funcion que retorna el parametro de impresion de un
                    determinado proceso de impresion en base al nombre del
                    parametro, retorna NULL en caso no encuentre el valor.

Fecha Creacion    : 07/03/2008
Autor             : Carlos Hurtado
***************************************************************************/
FUNCTION IMP_FN_OBTIE_PARAM_IMPRE( psCodigoProceso VARCHAR2,
                                   psNombreParametro VARCHAR2)
RETURN VARCHAR2 IS

lsValorParametro VARCHAR2(100);

BEGIN
    SELECT A.VAL_PARA_PRIM
    INTO lsValorParametro
    FROM IMP_PARAM_PROCE_IMPRE A
    WHERE A.PRIM_COD_PROC = psCodigoProceso
    AND A.NOM_PARA_PRIM = psNombreParametro
    AND A.EST_PARA_PRIM = '1';

    RETURN lsValorParametro;

    EXCEPTION
    WHEN NO_DATA_FOUND THEN
    RETURN NULL;

END;




PROCEDURE imp_pr_print_compr
(
  p_codigoperiodo       IN VARCHAR2,
  p_fechafacturacion    IN VARCHAR2,
  pscodigotransaccional VARCHAR2,
  pscodigopaquete       VARCHAR2,
  pscodigousuario       VARCHAR2
) IS
BEGIN

  INSERT INTO imp_print_compr
    (prsp_cod_proc,
     prsp_cod_clie,
     prsp_cod_paqu,
     num_comp,
     fec_emis,
     val_mont_01,
     cod_barr,
     nom_prod,
     cod_cuv,
     val_cant,
     nro_pedi,
     cod_camp_orig,
     cod_ctrl,
     usu_crea,
     fec_crea)
    SELECT pscodigotransaccional,
           cod_clie,
           pscodigopaquete,
           num_corr_regi,
           fec_proc,
           val_prec_cata,
           val_estr,
           val_desc_prod,
           val_codi_vent,
           num_unid,
           val_nume_soli,
           cod_peri,
           val_foli,
           pscodigousuario,
           SYSDATE
      FROM ped_bolet_elect_histo p
     WHERE p.fec_proc = to_date(p_fechafacturacion, 'DD/MM/YYYY')
       AND p.cod_peri = p_codigoperiodo
       AND p.val_foli IS NOT NULL;

END imp_pr_print_compr;
PROCEDURE imp_pr_proce_print_execu
(
  pscodigopais       IN VARCHAR2,
  pscodigoperiodo    IN VARCHAR2,
  psfechafacturacion IN VARCHAR2,
  psnumerolote       IN VARCHAR2, --codigo de proceso,
  psusuario          IN VARCHAR2
) IS

  lscodigoproceso imp_print_spool.cod_proc%TYPE;
  lscodigopaquete imp_print_spool.cod_paqu%TYPE;
  lscodigomarca   seg_marca.cod_marc%TYPE := 'T';
  lscodigocanal   seg_canal.cod_cana%TYPE := 'VD';

BEGIN

  DELETE imp_print_ape;

  DELETE imp_print_bolet_despa_detal;
  DELETE imp_print_bolet_despa;

  DELETE imp_print_bolet_recoj_detal;
  DELETE imp_print_bolet_recoj;

  DELETE imp_print_carta_detal;
  DELETE imp_print_carta;

  DELETE imp_print_compr_detal;
  DELETE imp_print_compr;

  DELETE imp_print_estad_cuent_detal;
  DELETE imp_print_estad_cuent;

  DELETE imp_print_notic_detal;
  DELETE imp_print_notic;

  DELETE imp_print_pedid_detal;
  DELETE imp_print_pedid_resum;

  DELETE imp_print_picad_detal;
  DELETE imp_print_picad;

  DELETE imp_print_carta_grafi;

  DELETE imp_print_flexi;

  DELETE imp_print_grafi;

  DELETE imp_print_spool_orige;
  DELETE imp_print_spool_secci;

  DELETE imp_print_spool;

  lscodigoproceso := lpad(psnumerolote, 12, '0');
  lscodigopaquete := '01';

  FOR y IN (SELECT oid_zona FROM zon_zona)
  LOOP
  
    imp_pr_print_spool(pscodigopais,
                       pscodigoperiodo,
                       psfechafacturacion,
                       y.oid_zona,
                       lscodigoproceso,
                       psusuario);
  
    imp_pr_print_bolet_despa(pscodigopais,
                             pscodigoperiodo,
                             psfechafacturacion,
                             y.oid_zona,
                             lscodigoproceso,
                             psusuario);
  
    IF pscodigopais = 'CLE' THEN
      imp_pr_print_pedid_cl(pscodigopais,
                            pscodigoperiodo,
                            psfechafacturacion,
                            y.oid_zona,
                            lscodigoproceso,
                            lscodigopaquete,
                            psusuario);
    
    ELSIF pscodigopais = 'COE' THEN
      imp_pr_proce_pedid_co(pscodigopais,
                            pscodigoperiodo,
                            psfechafacturacion,
                            y.oid_zona,
                            lscodigoproceso,
                            lscodigopaquete,
                            psusuario);
    ELSE
    
      imp_pr_print_pedid(pscodigopais,
                         pscodigoperiodo,
                         psfechafacturacion,
                         y.oid_zona,
                         lscodigoproceso,
                         lscodigopaquete,
                         psusuario);
    
    END IF;
    COMMIT;
  
  END LOOP;

  IF pscodigopais = 'CLE' THEN
  
    imp_pr_print_compr( --pscodigopais,
                       pscodigoperiodo,
                       psfechafacturacion,
                       lscodigoproceso,
                       lscodigopaquete,
                       psusuario);
  END IF;

  imp_pr_print_bolet_recoj(pscodigopais,
                           pscodigoperiodo,
                           lscodigomarca,
                           lscodigocanal,
                           lscodigoproceso,
                           psusuario);
  COMMIT;

  /*if r_form.cod_form='OC' then                     imp_paque_docum_ocs x
  if r_form.cod_form='UN' then                     imp_paque_docum_unot x
  if r_form.cod_form='DF' then                     imp_paque_docum_detal_factu x
  if r_form.cod_form='CP' then                     imp_paque_docum_cupon x
  if r_form.cod_form='CT' then                     imp_paque_docum_cupon x imp_paque_docum_laser_ctact x
  if r_form.cod_form='BR' then                     imp_paque_docum_borec x
  if r_form.cod_form='FA' then                     IMP_PAQUE_DOCUM_LASER_FACTU x
  if r_form.cod_form='FX' then                     flx_paque_docum x
  if r_form.cod_form='PP' then                     IMP_PAQUE_DOCUM_PROGR_PUNTO x*/

  imp_pr_print_flexi(pscodigopais,
                     pscodigoperiodo,
                     psfechafacturacion,
                     lscodigoproceso,
                     lscodigopaquete,
                     psusuario);
  COMMIT;

  /* imp_pr_proce_bolet_honor(pscodigopais,
                           pscodigoperiodo,
                           psfechafacturacion,
                           psusuario);
  COMMIT;*/
  /*imp_pkg_proce_impre.imp_pr_proce_cupon(p_codigopais,
  p_codigoperiodo,
  p_oidzona,
  p_fechafacturacion);*/

  /*imp_pkg_proce_impre.imp_pr_proce_ccc(pscodigopais,
    pscodigoperiodo,
    psfechafacturacion,
  --  p_oidzona,
    lscodigoproceso,
    lscodigopaquete,
    psusuario);*/
  COMMIT;

  COMMIT;

  /* imp_pkg_proce_impre.imp_pr_proce_orden_compr(p_codigopais,
  p_codigoperiodo,
  p_fechafacturacion,
  p_oidzona);*/
  imp_pr_proce_notic(pscodigopais,
                     pscodigoperiodo,
                     psfechafacturacion,
                     NULL);

  imp_pr_print_spool_orige_secci;

END imp_pr_proce_print_execu;

PROCEDURE imp_pr_print_spool_orige_secci IS
BEGIN

  DELETE imp_print_spool_orige;
  DELETE imp_print_spool_secci;
  /*CUPONES*/
  FOR z IN (SELECT s.*
              FROM imp_formu_paque_docu2 f,
                   imp_print_spool       s
             WHERE f.cod_form IN ('CP', 'CT')
               AND ltrim(s.cod_paqu, '0') = f.cod_pado)
  LOOP
  
    -- imp_print_spool_orige 19 Cupones U POR DEFINIR
    INSERT INTO imp_print_spool_orige
      (prsp_cod_proc,
       prsp_cod_clie,
       prsp_cod_paqu,
       fla_orig)
    VALUES
      (z.cod_proc,
       z.cod_clie,
       z.cod_paqu,
       'U');
  
    -- imp_print_spool_orige 20  Cupones  al cierre  G
    /*INSERT INTO imp_print_spool_orige
      (prsp_cod_proc,
       prsp_cod_clie,
       prsp_cod_paqu,
       fla_orig)
    VALUES
      (pscodigotransaccional,
       r_cupon.cod_clie,
       pscodigopaquete,
       'G');*/
  
    --  imp_print_spool_secci 7 Cupón C
    INSERT INTO imp_print_spool_secci
      (prsp_cod_proc,
       prsp_cod_clie,
       prsp_cod_paqu,
       fla_secc)
    VALUES
      (z.cod_proc,
       z.cod_clie,
       z.cod_paqu,
       'C');
  END LOOP;

  --1 Boleta de despacho  D IMP_PRINT_BOLET_DESPA
  INSERT INTO imp_print_spool_orige
    (prsp_cod_proc,
     prsp_cod_clie,
     prsp_cod_paqu,
     fla_orig)
    SELECT prsp_cod_proc,
           prsp_cod_clie,
           prsp_cod_paqu,
           'D'
      FROM imp_print_bolet_despa;

  --2  Boleta de recojo  R IMP_PRINT_BOLET_RECOJ
  INSERT INTO imp_print_spool_orige
    (prsp_cod_proc,
     prsp_cod_clie,
     prsp_cod_paqu,
     fla_orig)
    SELECT prsp_cod_proc,
           prsp_cod_clie,
           prsp_cod_paqu,
           'R'
      FROM imp_print_bolet_recoj;
  --3  Detalle de factura  P IMP_PRINT_COMPR   FA=Factura, FR=Factura Referenciada, BV=Boleta de Venta, NC=Nota de credito, BT=Boleta de terceros
  INSERT INTO imp_print_spool_orige
    (prsp_cod_proc,
     prsp_cod_clie,
     prsp_cod_paqu,
     fla_orig)
    SELECT DISTINCT prsp_cod_proc,
                    prsp_cod_clie,
                    prsp_cod_paqu,
                    'P'
      FROM imp_print_pedid_resum;

  --4 Noticias #1 1 IMP_PRINT_NOTIC OID_NOTI
  INSERT INTO imp_print_spool_orige
    (prsp_cod_proc,
     prsp_cod_clie,
     prsp_cod_paqu,
     fla_orig)
    SELECT DISTINCT prsp_cod_proc,
                    prsp_cod_clie,
                    prsp_cod_paqu,
                    '1'
      FROM imp_print_notic;

  --5 Noticias #2 2 IMP_PRINT_NOTIC OID_NOTI -- PENDIENTE

  --6 Cartas RvDocument C IMP_PRINT_CARTA_GRAFI
  INSERT INTO imp_print_spool_orige
    (prsp_cod_proc,
     prsp_cod_clie,
     prsp_cod_paqu,
     fla_orig)
    SELECT DISTINCT prsp_cod_proc,
                    prsp_cod_clie,
                    prsp_cod_paqu,
                    'C'
      FROM imp_print_carta_grafi;
  -- 7  Noticias FlexiPago  X
  INSERT INTO imp_print_spool_orige
    (prsp_cod_proc,
     prsp_cod_clie,
     prsp_cod_paqu,
     fla_orig)
    SELECT prsp_cod_proc,
           prsp_cod_clie,
           prsp_cod_paqu,
           'X'
      FROM imp_print_flexi;
  --8 Orden de Compra O IMP_PRINT_PEDID_RESUM
  INSERT INTO imp_print_spool_orige
    (prsp_cod_proc,
     prsp_cod_clie,
     prsp_cod_paqu,
     fla_orig)
    SELECT prsp_cod_proc,
           prsp_cod_clie,
           prsp_cod_paqu,
           'O'
      FROM imp_print_pedid_resum;

  --9 Orden de Compra FlexiPago Q IMP_PRINT_FLEXI
  INSERT INTO imp_print_spool_orige
    (prsp_cod_proc,
     prsp_cod_clie,
     prsp_cod_paqu,
     fla_orig)
    SELECT prsp_cod_proc,
           prsp_cod_clie,
           prsp_cod_paqu,
           'Q'
      FROM imp_print_flexi;
  --10  Boletas de Venta  V IMP_PRINT_COMPR
  INSERT INTO imp_print_spool_orige
    (prsp_cod_proc,
     prsp_cod_clie,
     prsp_cod_paqu,
     fla_orig)
    SELECT prsp_cod_proc,
           prsp_cod_clie,
           prsp_cod_paqu,
           'V'
      FROM imp_print_compr
     WHERE cod_tipo_comp = 'BV';
  --11  Boleta de Servicio de Terceros  T IMP_PRINT_COMPR   FA=Factura, FR=Factura Referenciada, BV=Boleta de Venta, NC=Nota de credito, BT=Boleta de terceros
  INSERT INTO imp_print_spool_orige
    (prsp_cod_proc,
     prsp_cod_clie,
     prsp_cod_paqu,
     fla_orig)
    SELECT prsp_cod_proc,
           prsp_cod_clie,
           prsp_cod_paqu,
           'T'
      FROM imp_print_compr
     WHERE cod_tipo_comp = 'BT';
  --12  Cartas SiCC S IMP_PRINT_CARTA
  --13  Control de Despacho H
  --14  Notas de Credito  N IMP_PRINT_COMPR   FA=Factura, FR=Factura Referenciada, BV=Boleta de Venta, NC=Nota de credito, BT=Boleta de terceros
  INSERT INTO imp_print_spool_orige
    (prsp_cod_proc,
     prsp_cod_clie,
     prsp_cod_paqu,
     fla_orig)
    SELECT prsp_cod_proc,
           prsp_cod_clie,
           prsp_cod_paqu,
           'N'
      FROM imp_print_compr
     WHERE cod_tipo_comp = 'NC';
  --15  Notas de Debito E IMP_PRINT_COMPR   FA=Factura, FR=Factura Referenciada, BV=Boleta de Venta, NC=Nota de credito, BT=Boleta de terceros
  --PENDIENTE
  --16  Factura (Electronica, Consumidor, etc)  F IMP_PRINT_COMPR   FA=Factura, FR=Factura Referenciada, BV=Boleta de Venta, NC=Nota de credito, BT=Boleta de terceros
  INSERT INTO imp_print_spool_orige
    (prsp_cod_proc,
     prsp_cod_clie,
     prsp_cod_paqu,
     fla_orig)
    SELECT prsp_cod_proc,
           prsp_cod_clie,
           prsp_cod_paqu,
           'F'
      FROM imp_print_compr
     WHERE cod_tipo_comp = 'FA';
  --17  Estado de Cuenta  Z IMP_PRINT_ESTAD_CUENT
  INSERT INTO imp_print_spool_orige
    (prsp_cod_proc,
     prsp_cod_clie,
     prsp_cod_paqu,
     fla_orig)
    SELECT prsp_cod_proc,
           prsp_cod_clie,
           prsp_cod_paqu,
           'Z'
      FROM imp_print_estad_cuent;
  --18  Hoja de Picado  K IMP_PRINT_PICAD
  INSERT INTO imp_print_spool_orige
    (prsp_cod_proc,
     prsp_cod_clie,
     prsp_cod_paqu,
     fla_orig)
    SELECT prsp_cod_proc,
           prsp_cod_clie,
           prsp_cod_paqu,
           'K'
      FROM imp_print_picad;

  --PENDIENTE
  --20  Cupones  al cierre  G
  --PEMDIENTE

  INSERT INTO imp_print_spool_secci
    (prsp_cod_proc,
     prsp_cod_clie,
     prsp_cod_paqu,
     fla_secc)
    SELECT DISTINCT prsp_cod_proc,
                    prsp_cod_clie,
                    prsp_cod_paqu,
                    CASE
                    --Belcorp te premia P - BP=Belcorp te premia
                      WHEN cod_grup = 'BP' THEN
                       'P'
                    --Noticias (Belcorp te hace crecer, Belcorp te cuenta)  N - BN=Belcorp Noticias
                      WHEN cod_grup = 'BN' THEN
                       'N'
                    --BO=Belcorp te orienta
                    -- when COD_GRUP = 'BO'  then 'P'
                    END
      FROM imp_print_notic
     WHERE cod_grup IN ('BP', 'BN' /*,'BO'*/);

  -- Oportunidad de Ahorro H -- PENDIENTE
  --Productos agotados  A -- PENDIENTE

  --Detalle de factura  D
  INSERT INTO imp_print_spool_secci
    (prsp_cod_proc,
     prsp_cod_clie,
     prsp_cod_paqu,
     fla_secc)
    SELECT distinct prsp_cod_proc,
           prsp_cod_clie,
           prsp_cod_paqu,
           'D'
      FROM imp_print_pedid_resum;

  --Facturas (Consumidor, Electrónica, etc) F
  --Boleta (de venta, terceros) B

  /*IMP_PRINT_COMPR
  FA=Factura,
  FR=Factura Referenciada,
  BV=Boleta de Venta,
  NC=Nota de credito,
  BT=Boleta de terceros*/
  INSERT INTO imp_print_spool_secci
    (prsp_cod_proc,
     prsp_cod_clie,
     prsp_cod_paqu,
     fla_secc)
    SELECT prsp_cod_proc,
           prsp_cod_clie,
           prsp_cod_paqu,
           CASE
             WHEN cod_tipo_comp = 'FA' THEN
              'F'
             WHEN cod_tipo_comp = 'BV' THEN
              'B'
             WHEN cod_tipo_comp = 'BT' THEN
              'B'
           END
      FROM imp_print_compr
     WHERE cod_tipo_comp IN ('FA', 'BV', 'BT');
  --Flexipago X
  INSERT INTO imp_print_spool_secci
    (prsp_cod_proc,
     prsp_cod_clie,
     prsp_cod_paqu,
     fla_secc)
    SELECT prsp_cod_proc,
           prsp_cod_clie,
           prsp_cod_paqu,
           'X'
      FROM imp_print_flexi;

  --Cupón C --PENDIENTE

END imp_pr_print_spool_orige_secci;

PROCEDURE imp_pr_print_spool
(
  pscodigopais       VARCHAR2,
  pscodigoperiodo    VARCHAR2,
  psfechafacturacion VARCHAR2,
  pnoidzona          NUMBER,
  pscodigoproceso    VARCHAR2,
  psusuario          VARCHAR2
) IS

  -- Variable usada para eliminar los detalles erroneos
  lv_eliminaciondetalles VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                       'indicadorEliminacionDetallesErroneos');

  CURSOR c_cons
  (
    vnoidperi             NUMBER,
    numerolotefacturacion NUMBER,
    indicadorenviolarissa NUMBER
  ) IS
    SELECT a.val_nume_soli,
           a.clie_oid_clie
      FROM ped_solic_cabec a
     WHERE a.perd_oid_peri = vnoidperi
       AND a.zzon_oid_zona = pnoidzona
       AND a.fec_fact = to_date(psfechafacturacion, 'dd/mm/yyyy')
       AND a.num_unid_aten_tota > 0
       AND a.ind_ts_no_conso = 0
          -- AND a.ind_inte_lari_gene = indicadorenviolarissa
       AND (numerolotefacturacion IS NULL OR
           a.num_lote_fact = numerolotefacturacion)
       AND EXISTS
     (SELECT NULL
              FROM int_lar_tipo_solici_pedido_dis l
             WHERE l.tspa_oid_tipo_soli_pais = a.tspa_oid_tipo_soli_pais);

  r_cons c_cons%ROWTYPE;

  --w_filas        NUMBER(12);
  ln_oidperi NUMBER;

  l_indicadorenviolarissa    VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                           'indicadorEnvioLarissa');
  l_indicadorenvioultimolote VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                           'indicadorEnvioUltimoLote');
  l_numerolotefacturacion    NUMBER;

BEGIN

  ln_oidperi := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoperiodo);

  IF (l_indicadorenvioultimolote = '1' OR l_indicadorenvioultimolote = 'S') THEN
    BEGIN
      SELECT MAX(con.num_lote_fact)
        INTO l_numerolotefacturacion
        FROM ped_solic_cabec                con,
             int_lar_tipo_solici_pedido_dis tspd
       WHERE con.perd_oid_peri = ln_oidperi
         AND con.fec_fact = to_date(psfechafacturacion, 'dd/mm/yyyy')
         AND con.ind_inte_lari_gene = l_indicadorenviolarissa
         AND con.ind_ts_no_conso = 0
         AND (con.ind_pedi_prue = 0 OR con.ind_pedi_prue IS NULL)
         AND con.tspa_oid_tipo_soli_pais = tspd.tspa_oid_tipo_soli_pais;
    
    EXCEPTION
      WHEN OTHERS THEN
        l_numerolotefacturacion := NULL;
    END;
  END IF;

  OPEN c_cons(ln_oidperi, l_numerolotefacturacion, l_indicadorenviolarissa);
  LOOP
    FETCH c_cons
      INTO r_cons;
    EXIT WHEN c_cons%NOTFOUND;
  
    imp_pr_print_spool_final(pscodigopais,
                             pscodigoperiodo,
                             psfechafacturacion,
                             pscodigoproceso,
                             psusuario,
                             r_cons.val_nume_soli,
                             r_cons.clie_oid_clie);
  
  END LOOP;
  CLOSE c_cons;

  /* Eliminacion de detalles con error */
  IF lv_eliminaciondetalles = 'S' THEN
    DELETE FROM imp_paque_docum_final
     WHERE instr(xml_cons, '$consultora.VAL_IMPO_TOTA_PAGA') != 0
       AND num_pado = nro_paquete_normal;
  END IF;
  COMMIT;
END imp_pr_print_spool;
PROCEDURE imp_pr_print_spool_final
(
  pscodigopais       VARCHAR2,
  pscodigoperiodo    VARCHAR2,
  psfechafacturacion VARCHAR2,
  pscodigoproceso    VARCHAR2,
  psusuario          VARCHAR2,
  psvalnumesoli      NUMBER,
  pnoidclie          NUMBER
) IS

  CURSOR c_pado IS
    SELECT lpad(cod_pado, 2, '0') cod_pado FROM imp_compa_paque_docum;

  r_pado   c_pado%ROWTYPE;
  ped_serv NUMBER := 0;
  ped_expr NUMBER := 0;
  ped_flex NUMBER := 0;

  lv_indicadornmp            VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                           'indicadorNMP');
  lv_indicadorpedidoservicio VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                           'indicadorPedidoServicio');
  TYPE imp_print_spool_tab_t IS TABLE OF imp_print_spool%ROWTYPE INDEX BY BINARY_INTEGER;
  imp_print_spool_tab imp_print_spool_tab_t;

  CURSOR cur_spool IS
    SELECT pscodigoproceso cod_proc,
           NULL cod_clie, --OK
           NULL cod_paqu, --OK
           NULL oid_spoo, --OK
           NULL val_digi_veri, --OK
           pscodigoperiodo cod_camp,
           NULL cod_regi, --OK
           NULL cod_zona, --OK
           NULL cod_secc, --OK
           NULL cod_terr, --OK
           NULL ord_prio, --OK
           NULL ind_tipo_clie, -- Campo ya no se usa. Enviar en blanco
           NULL val_apel_pate, --OK
           NULL val_apel_mate, --OK
           NULL val_nomb, --OK
           psvalnumesoli num_pedi,
           NULL cod_cupo,
           NULL num_docu_iden, --OK
           NULL cod_trib, --OK
           psfechafacturacion fec_fact,
           psfechafacturacion fec_emis,
           NULL fec_reun_01, --PENDIENTE
           NULL fec_reun_02, --PENDIENTE
           NULL fec_reun_03, --PENDIENTE
           NULL fec_repa_01, --OK
           NULL fec_repa_02, --OK
           NULL fec_repa_03, --OK
           NULL fec_vcto, --OK
           NULL fec_prox_fact_01, --OK
           NULL fec_prox_fact_02, --OK
           NULL fec_prox_fact_03, --OK
           NULL mon_sald_actu,
           NULL mon_sald_ante,
           NULL val_dire_01, --OK
           NULL val_dire_02, --OK
           NULL val_dire_03, --OK
           NULL val_ubig_01, --OK
           NULL val_ubig_02, --OK
           NULL val_ubig_03, --OK
           NULL val_refe_01, --OK
           NULL val_refe_02, --OK
           NULL val_refe_03, --OK
           NULL val_telf_01, --OK
           NULL val_telf_02, --OK
           NULL flg_orig, --SE CREO NUEVA TABLA
           NULL flg_secc, --SE CREO NUEVA TABLA
           NULL ind_impr_pdoc, --OK
           NULL ind_cons_estr, --OK
           NULL ind_cons_circ, --OK
           NULL ord_pedi, --OK
           NULL des_nive_01,
           NULL des_nive_02,
           NULL des_nive_03,
           0 can_comp,
           0 can_pica,
           NULL can_orde_comp,
           psvalnumesoli num_bole_desp, --OK
           NULL uni_bole_desp, --OK
           NULL imp_bole_desp, --OK
           NULL cod_bole_desp,
           NULL cod_tipo_pedi, --OK
           NULL des_tipo_pedi, --OK
           NULL mon_pedm_flex,
           NULL mon_disp_flex,
           NULL val_gezo_apel, --OK
           NULL val_gezo_nomb, --OK
           NULL val_gezo_movi, --OK
           NULL val_gezo_mail, --OK
           NULL val_ejec_apel, --OK
           NULL val_ejec_nomb, --OK
           NULL val_ejec_movi, --OK
           NULL val_ejec_mail, --OK
           NULL cod_alma, --OK
           NULL val_auxi_rv1, --USO RV
           NULL val_auxi_rv2, --USO RV
           NULL val_auxi_rv3, --USO RV
           NULL val_auxi_rv4, --USO RV
           NULL val_auxi_rv5, --USO RV
           NULL val_info_caja,
           NULL val_clas,
           NULL val_esta,
           psvalnumesoli num_fact,
           NULL num_secu,
           NULL num_secu_comp,
           NULL fec_entr,
           NULL num_dias_entr, --OK
           NULL val_tipo_01,
           NULL val_tipo_02,
           NULL val_tipo_03,
           NULL val_tipo_04,
           NULL val_tipo_05,
           NULL val_tipo_06,
           NULL val_secu_2459,
           NULL val_mont_opor_ahor,
           psusuario usu_crea,
           SYSDATE fec_crea,
           NULL usu_modi,
           NULL fec_modi,
           '1' ind_acti
      FROM dual;

  rows NATURAL := 10000; -- Number of rows to process at a time

  ln_diasdesp  NUMBER(10) := 0;
  ln_diasdesp2 NUMBER(10) := 0;
  ln_diasdesp3 NUMBER(10) := 0;

  l_codigoperiodosiguiente VARCHAR2(6);
  lv_actividadconf         VARCHAR2(100) := nvl(imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                             'actividadConf'),
                                                'CV');

  lv_actividaddesp VARCHAR2(100) := nvl(imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                     'actividadDesp'),
                                        'DP');

  l_fechacv                    DATE;
  l_fechadespacho              DATE;
  l_fechadespacho_2            DATE;
  l_fechadespacho_3            DATE;
  l_fechadespacho2             VARCHAR2(100) := nvl(imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                                 'indFechaDesp2'),
                                                    'N');
  l_fechasigfact               DATE;
  l_fechasigfact2              DATE;
  l_fechasigfact3              DATE;
  l_fechavencimiento           DATE;
  l_codigoactividadvencimiento VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                             'codigoActividadVencimiento');

  lnidzona      zon_zona.oid_zona%TYPE;
  lnoidcliezona zon_zona.clie_oid_clie%TYPE;
  lnoidcliesecc zon_secci.clie_oid_clie%TYPE;

  lnoidsolicabe ped_solic_cabec.oid_soli_cabe%TYPE;

BEGIN

  l_codigoperiodosiguiente := gen_fn_calcu_perio(pscodigoperiodo, 1);

  OPEN cur_spool;
  LOOP
  
    FETCH cur_spool BULK COLLECT
      INTO imp_print_spool_tab LIMIT rows;
    EXIT WHEN imp_print_spool_tab.count = 0;
  
    FOR i IN imp_print_spool_tab.first .. imp_print_spool_tab.last
    LOOP
    
      SELECT c.cod_clie,
             nvl(c.cod_digi_ctrl, '0'),
             c.val_ape1,
             c.val_ape2,
             substr(c.val_nom1 || ' ' || c.val_nom2, 1, 30),
             zr.cod_regi,
             zz.cod_zona,
             zs.cod_secc,
             zt.cod_terr,
             decode(da.ind_impr_pdoc, 'N', 0, 1),
             zz.oid_zona,
             zz.clie_oid_clie,
             zs.clie_oid_clie
        INTO imp_print_spool_tab(i).cod_clie,
             imp_print_spool_tab(i).val_digi_veri,
             imp_print_spool_tab(i).val_apel_pate,
             imp_print_spool_tab(i).val_apel_mate,
             imp_print_spool_tab(i).val_nomb,
             imp_print_spool_tab(i).cod_regi,
             imp_print_spool_tab(i).cod_zona,
             imp_print_spool_tab(i).cod_secc,
             imp_print_spool_tab(i).cod_terr,
             imp_print_spool_tab(i).ind_impr_pdoc,
             lnidzona,
             lnoidcliezona,
             lnoidcliesecc
        FROM mae_clien             c,
             mae_clien_unida_admin cua,
             mae_clien_datos_adici da,
             zon_terri_admin       zta,
             zon_terri             zt,
             zon_secci             zs,
             zon_zona              zz,
             zon_regio             zr
       WHERE zta.oid_terr_admi = cua.ztad_oid_terr_admi
         AND zs.oid_secc = zta.zscc_oid_secc
         AND zz.oid_zona = zs.zzon_oid_zona
         AND zr.oid_regi = zz.zorg_oid_regi
         AND da.clie_oid_clie = c.oid_clie
         AND c.oid_clie = cua.clie_oid_clie
         AND zta.terr_oid_terr = zt.oid_terr
         AND cua.ind_acti = '1'
         AND c.oid_clie = pnoidclie;
    
      SELECT (SELECT sec.num_secu_fact_diar
                FROM ped_solic_cabec_secue sec
               WHERE sec.soca_oid_soli_cabe = con.oid_soli_cabe),
             (SELECT cod_alma
                FROM bel_almac
               WHERE oid_alma = con.almc_oid_alma),
             con.num_unid_aten_tota,
             con.val_tota_paga_loca,
             oid_soli_cabe
        INTO imp_print_spool_tab(i).ord_prio,
             imp_print_spool_tab(i).cod_alma,
             imp_print_spool_tab(i).uni_bole_desp,
             imp_print_spool_tab(i).imp_bole_desp,
             lnoidsolicabe
        FROM ped_solic_cabec con
       WHERE val_nume_soli = psvalnumesoli
         AND clie_oid_clie = pnoidclie;
    
      SELECT MIN(mci.num_docu_iden),
             decode(MIN(y.val_sigl), 'RUC', MIN(y.val_sigl), 'DNI')
        INTO imp_print_spool_tab(i).num_docu_iden,
             imp_print_spool_tab(i) . cod_trib
        FROM mae_clien_ident mci,
             mae_tipo_docum  y
       WHERE mci.val_iden_docu_prin = 1
         AND y.oid_tipo_docu = mci.tdoc_oid_tipo_docu
         AND clie_oid_clie = pnoidclie;
    
      BEGIN
        SELECT trunc(cc.fec_inic),
               trunc(cc.fec_inic),
               trunc(cc.fec_inic)
          INTO l_fechadespacho,
               l_fechadespacho_2,
               l_fechadespacho_3
          FROM cra_crono       cc,
               cra_perio       cp,
               seg_perio_corpo spc,
               cra_activ       act
         WHERE cc.perd_oid_peri = cp.oid_peri
           AND cp.peri_oid_peri = spc.oid_peri
           AND spc.cod_peri = l_codigoperiodosiguiente
           AND cc.zzon_oid_zona = lnidzona
           AND cc.cact_oid_acti = act.oid_acti
           AND act.cod_acti = lv_actividaddesp;
      EXCEPTION
        WHEN OTHERS THEN
          l_fechadespacho   := trunc(SYSDATE);
          l_fechadespacho_2 := trunc(SYSDATE);
          l_fechadespacho_3 := trunc(SYSDATE);
      END;
    
      BEGIN
        SELECT trunc(cc.fec_inic)
          INTO l_fechasigfact
          FROM cra_crono       cc,
               cra_perio       cp,
               seg_perio_corpo spc,
               cra_activ       act
         WHERE cc.perd_oid_peri = cp.oid_peri
           AND cp.peri_oid_peri = spc.oid_peri
           AND spc.cod_peri = l_codigoperiodosiguiente
           AND cc.zzon_oid_zona = lnidzona
           AND cc.cact_oid_acti = act.oid_acti
           AND act.cod_acti = 'FA';
      EXCEPTION
        WHEN OTHERS THEN
          l_fechasigfact := trunc(SYSDATE);
      END;
    
      BEGIN
        SELECT trunc(cc.fec_inic)
          INTO l_fechacv
          FROM cra_crono       cc,
               cra_perio       cp,
               seg_perio_corpo spc,
               cra_activ       act
         WHERE cc.perd_oid_peri = cp.oid_peri
           AND cp.peri_oid_peri = spc.oid_peri
           AND spc.cod_peri = l_codigoperiodosiguiente
           AND cc.zzon_oid_zona = lnidzona
           AND cc.cact_oid_acti = act.oid_acti
           AND act.cod_acti = lv_actividadconf;
      EXCEPTION
        WHEN OTHERS THEN
          l_fechacv := trunc(SYSDATE);
      END;
    
      l_fechasigfact2 := fac_pkg_proc.ped_fn_dev_dia_fact(l_codigoperiodosiguiente,
                                                          imp_print_spool_tab(i)
                                                          .cod_zona,
                                                          2);
    
      l_fechasigfact3 := fac_pkg_proc.ped_fn_dev_dia_fact(l_codigoperiodosiguiente,
                                                          imp_print_spool_tab(i)
                                                          .cod_zona,
                                                          3);
    
      ln_diasdesp  := fac_pkg_proc.ped_fn_obt_dias_fecha_ent(lnoidsolicabe,
                                                             1,
                                                             l_codigoperiodosiguiente,
                                                             l_fechasigfact);
      ln_diasdesp2 := fac_pkg_proc.ped_fn_obt_dias_fecha_ent(lnoidsolicabe,
                                                             2,
                                                             l_codigoperiodosiguiente,
                                                             l_fechasigfact);
      ln_diasdesp3 := fac_pkg_proc.ped_fn_obt_dias_fecha_ent(lnoidsolicabe,
                                                             3,
                                                             l_codigoperiodosiguiente,
                                                             l_fechasigfact);
    
      IF l_fechadespacho2 = 'S' AND ln_diasdesp > 0 THEN
        l_fechadespacho := l_fechasigfact + ln_diasdesp;
      END IF;
    
      IF l_fechadespacho2 = 'S' AND ln_diasdesp > 0 THEN
        l_fechadespacho_2 := l_fechasigfact2 + ln_diasdesp2;
      END IF;
    
      IF l_fechadespacho2 = 'S' AND ln_diasdesp > 0 THEN
        l_fechadespacho_3 := l_fechasigfact3 + ln_diasdesp3;
      END IF;
    
      imp_print_spool_tab(i).fec_reun_01 := l_fechacv;
      imp_print_spool_tab(i).fec_reun_02 := l_fechacv;
      imp_print_spool_tab(i).fec_reun_03 := l_fechacv;
    
      imp_print_spool_tab(i).fec_repa_01 := l_fechadespacho;
      imp_print_spool_tab(i).fec_repa_02 := l_fechadespacho_2;
      imp_print_spool_tab(i).fec_repa_03 := l_fechadespacho_3;
    
      BEGIN
      
        SELECT trunc(cc.fec_inic)
          INTO l_fechavencimiento
          FROM cra_crono       cc,
               cra_perio       cp,
               seg_perio_corpo spc,
               cra_activ       act
         WHERE cc.perd_oid_peri = cp.oid_peri
           AND cp.peri_oid_peri = spc.oid_peri
           AND spc.cod_peri = l_codigoperiodosiguiente
           AND cc.zzon_oid_zona = lnidzona
           AND cc.cact_oid_acti = act.oid_acti
           AND act.cod_acti = l_codigoactividadvencimiento;
      EXCEPTION
        WHEN no_data_found THEN
          l_fechavencimiento := trunc(SYSDATE);
      END;
    
      imp_print_spool_tab(i).fec_vcto := l_fechacv;
    
      imp_print_spool_tab(i).fec_prox_fact_01 := l_fechasigfact;
      imp_print_spool_tab(i).fec_prox_fact_02 := l_fechasigfact;
      imp_print_spool_tab(i).fec_prox_fact_03 := l_fechasigfact;
    
      --FACTURACION --definir
      imp_print_spool_tab(i).val_dire_01 := TRIM(substr(imp_pkg_proce_laser.imp_fn_obtiene_text_direc(imp_pkg_proce_laser.imp_fn_obtiene_direc_clie(pscodigopais,
                                                                                                                                                    pnoidclie),
                                                                                                      ''),
                                                        1,
                                                        140));
      imp_print_spool_tab(i).val_ubig_01 := TRIM(substr(imp_pkg_proce_laser.imp_fn_obtiene_text2_direc(imp_pkg_proce_laser.imp_fn_obtiene_direc_clie(pscodigopais,
                                                                                                                                                     pnoidclie),
                                                                                                       ''),
                                                        1,
                                                        120));
      imp_print_spool_tab(i).val_refe_01 := TRIM(substr(imp_pkg_proce_laser.imp_fn_obtiene_text2_direc(imp_pkg_proce_laser.imp_fn_obtiene_direc_clie(pscodigopais,
                                                                                                                                                     pnoidclie),
                                                                                                       ''),
                                                        1,
                                                        120));
    
      --DESPACHO --definir
      imp_print_spool_tab(i).val_dire_02 := TRIM(substr(imp_pkg_proce_laser.imp_fn_obtiene_text_direc(imp_pkg_proce_laser.imp_fn_obtiene_direc_clie(pscodigopais,
                                                                                                                                                    pnoidclie),
                                                                                                      ''),
                                                        1,
                                                        140));
      imp_print_spool_tab(i).val_ubig_02 := TRIM(substr(imp_pkg_proce_laser.imp_fn_obtiene_text2_direc(imp_pkg_proce_laser.imp_fn_obtiene_direc_clie(pscodigopais,
                                                                                                                                                     pnoidclie),
                                                                                                       ''),
                                                        1,
                                                        120));
      imp_print_spool_tab(i).val_refe_02 := TRIM(substr(imp_pkg_proce_laser.imp_fn_obtiene_text2_direc(imp_pkg_proce_laser.imp_fn_obtiene_direc_clie(pscodigopais,
                                                                                                                                                     pnoidclie),
                                                                                                       ''),
                                                        1,
                                                        120));
      --RECOJO --definir
      imp_print_spool_tab(i).val_dire_03 := TRIM(substr(imp_pkg_proce_laser.imp_fn_obtiene_text_direc(imp_pkg_proce_laser.imp_fn_obtiene_direc_clie(pscodigopais,
                                                                                                                                                    pnoidclie),
                                                                                                      ''),
                                                        1,
                                                        140));
      imp_print_spool_tab(i).val_ubig_03 := TRIM(substr(imp_pkg_proce_laser.imp_fn_obtiene_text2_direc(imp_pkg_proce_laser.imp_fn_obtiene_direc_clie(pscodigopais,
                                                                                                                                                     pnoidclie),
                                                                                                       ''),
                                                        1,
                                                        120));
      imp_print_spool_tab(i).val_refe_03 := TRIM(substr(imp_pkg_proce_laser.imp_fn_obtiene_text2_direc(imp_pkg_proce_laser.imp_fn_obtiene_direc_clie(pscodigopais,
                                                                                                                                                     pnoidclie),
                                                                                                       ''),
                                                        1,
                                                        120));
    
      imp_print_spool_tab(i).val_telf_01 := substr(gen_pkg_gener.gen_fn_clien_texto_comun(pnoidclie,
                                                                                          'TF'),
                                                   1,
                                                   20);
      imp_print_spool_tab(i).val_telf_02 := substr(gen_pkg_gener.gen_fn_clien_texto_comun(pnoidclie,
                                                                                          'TM'),
                                                   1,
                                                   20);
    
      SELECT COUNT(1)
        INTO imp_print_spool_tab(i).ind_cons_estr
        FROM mae_clien_tipo_subti a,
             mae_clien_clasi      b
       WHERE a.clie_oid_clie = pnoidclie
         AND a.oid_clie_tipo_subt = b.ctsu_oid_clie_tipo_subt
         AND b.tccl_oid_tipo_clasi =
             to_number(ape_pkg_gener.ape_fn_obten_param(pscodigopais,
                                                        'APE_CLAS_ESTR'))
         AND rownum = 1;
    
      SELECT COUNT(1)
        INTO imp_print_spool_tab(i).ind_cons_circ
        FROM mae_clien_tipo_subti a,
             mae_clien_clasi      b
       WHERE a.clie_oid_clie = pnoidclie
         AND a.oid_clie_tipo_subt = b.ctsu_oid_clie_tipo_subt
         AND b.tccl_oid_tipo_clasi =
             to_number(ape_pkg_gener.ape_fn_obten_param(pscodigopais,
                                                        'APE_CLAS_CIRC_HONO'))
         AND rownum = '1';
    
      imp_print_spool_tab(i).ord_pedi := ape_pkg_gener.ape_fn_obten_tipo_consu(pscodigopais,
                                                                               psfechafacturacion,
                                                                               pnoidclie);
    
      -- NULL des_nive_01,
      -- NULL des_nive_02,
      --  NULL des_nive_03,
      --  NULL can_comp,
      --  NULL can_pica,
      --  NULL can_orde_comp,
    
      -- NULL cod_bole_desp,
      --POR DEFAULT ES PEDIDO REGULAR
      imp_print_spool_tab(i).cod_tipo_pedi := '01';
      imp_print_spool_tab(i).des_tipo_pedi := 'Regular';
    
      IF lv_indicadorpedidoservicio = '1' THEN
      
        SELECT COUNT(1)
          INTO ped_serv
          FROM ped_solic_cabec a
         WHERE a.val_nume_soli = psvalnumesoli
           AND a.clie_oid_clie = pnoidclie
           AND (EXISTS (SELECT 1
                          FROM ped_solic_cabec
                         WHERE soca_oid_soli_cabe = a.oid_soli_cabe
                           AND ind_oc = 1) OR
                (EXISTS (SELECT 1
                                  FROM ped_solic_cabec     x,
                                       ped_tipo_solic_pais y,
                                       ped_tipo_solic      z,
                                       gen_i18n_sicc_comun gen
                                 WHERE x.tspa_oid_tipo_soli_pais =
                                       y.oid_tipo_soli_pais
                                   AND y.tsol_oid_tipo_soli = z.oid_tipo_soli
                                   AND z.oid_tipo_soli = gen.val_oid
                                   AND gen.attr_enti = 'PED_TIPO_SOLIC'
                                   AND gen.val_i18n LIKE '%NMP%'
                                   AND x.soca_oid_soli_cabe = a.oid_soli_cabe) AND
                 lv_indicadornmp = '1'));
      
        SELECT COUNT(1)
          INTO ped_expr
          FROM ped_solic_cabec a
         WHERE a.val_nume_soli = psvalnumesoli
           AND a.clie_oid_clie = pnoidclie
           AND EXISTS
         (SELECT 1
                  FROM ped_solic_cabec,
                       ped_tipo_solic_pais,
                       ped_tipo_solic,
                       gen_i18n_sicc_comun
                 WHERE soca_oid_soli_cabe = a.oid_soli_cabe
                   AND tspa_oid_tipo_soli_pais = oid_tipo_soli_pais
                   AND tsol_oid_tipo_soli = oid_tipo_soli
                   AND oid_tipo_soli = val_oid
                   AND attr_enti = 'PED_TIPO_SOLIC'
                   AND upper(val_i18n) LIKE '%XPRES%');
      
        SELECT COUNT(1)
          INTO ped_flex
          FROM ped_solic_cabec a
         WHERE a.val_nume_soli = psvalnumesoli
           AND a.clie_oid_clie = pnoidclie
           AND EXISTS
         (SELECT 1
                  FROM ped_solic_cabec,
                       ped_tipo_solic_pais,
                       ped_tipo_solic,
                       gen_i18n_sicc_comun
                 WHERE soca_oid_soli_cabe = a.oid_soli_cabe
                   AND tspa_oid_tipo_soli_pais = oid_tipo_soli_pais
                   AND tsol_oid_tipo_soli = oid_tipo_soli
                   AND oid_tipo_soli = val_oid
                   AND attr_enti = 'PED_TIPO_SOLIC'
                   AND upper(val_i18n) LIKE '%CARGO POR USO%'
                   AND (SELECT COUNT(1)
                          FROM ped_solic_cabec
                         WHERE soca_oid_soli_cabe = a.oid_soli_cabe) = 1);
      
        IF ped_serv = 0 THEN
          IF ped_expr = 0 AND ped_flex = 0 THEN
            imp_print_spool_tab(i).cod_tipo_pedi := '02';
            imp_print_spool_tab(i).des_tipo_pedi := 'Pedido de Servicio';
          
          ELSE
            IF ped_expr > 0 THEN
              imp_print_spool_tab(i).cod_tipo_pedi := '03';
              imp_print_spool_tab(i).des_tipo_pedi := 'Pedido Express';
            
            ELSE
              imp_print_spool_tab(i).cod_tipo_pedi := '04';
              imp_print_spool_tab(i).des_tipo_pedi := 'Pedido Flexipago';
            
            END IF;
          END IF;
        
        END IF;
      
      END IF;
    
      --  NULL mon_pedm_flex,
      --  NULL mon_disp_flex,
    
      SELECT MIN(val_ape1 || ' ' || val_ape2),
             MIN(val_nom1 || ' ' || val_nom2)
        INTO imp_print_spool_tab(i).val_gezo_apel,
             imp_print_spool_tab(i).val_gezo_nomb
        FROM mae_clien
       WHERE oid_clie = lnoidcliezona;
    
      imp_print_spool_tab(i).val_gezo_movi := gen_pkg_gener.gen_fn_clien_texto_comun(lnoidcliezona,
                                                                                     'TM');
      imp_print_spool_tab(i).val_gezo_mail := gen_pkg_gener.gen_fn_clien_texto_comun(lnoidcliezona,
                                                                                     'ML');
    
      SELECT MIN(val_ape1 || ' ' || val_ape2),
             MIN(val_nom1 || ' ' || val_nom2)
        INTO imp_print_spool_tab(i).val_ejec_apel,
             imp_print_spool_tab(i).val_ejec_nomb
        FROM mae_clien
       WHERE oid_clie = lnoidcliesecc;
    
      imp_print_spool_tab(i).val_ejec_movi := gen_pkg_gener.gen_fn_clien_texto_comun(lnoidcliesecc,
                                                                                     'TM');
      imp_print_spool_tab(i).val_ejec_mail := gen_pkg_gener.gen_fn_clien_texto_comun(lnoidcliesecc,
                                                                                     'ML');
    
      /*NULL val_info_caja,
      NULL val_clas,
      NULL val_esta,
      NULL num_fact,
      NULL num_secu,
      NULL num_secu_comp,*/
    
      SELECT MIN(fec)
        INTO imp_print_spool_tab(i).fec_entr
        FROM ped_segui_pedid psep
       WHERE psep.soca_oid_soli_cabe = lnoidsolicabe;
    
      imp_print_spool_tab(i).num_dias_entr := ln_diasdesp;
    
      /*NULL val_tipo_01,
      NULL val_tipo_02,
      NULL val_tipo_03,
      NULL val_tipo_04,
      NULL val_tipo_05,
      NULL val_tipo_06,*/
      OPEN c_pado;
      LOOP
        FETCH c_pado
          INTO r_pado;
        EXIT WHEN c_pado%NOTFOUND;
        imp_print_spool_tab(i).oid_spoo := imp_prsp_seq.nextval;
        imp_print_spool_tab(i).cod_paqu := r_pado.cod_pado;
        INSERT INTO imp_print_spool VALUES imp_print_spool_tab (i);
      END LOOP;
    
      CLOSE c_pado;
    END LOOP;
  
  END LOOP;
  CLOSE cur_spool;

END imp_pr_print_spool_final;

FUNCTION imp_fn_print_devue_paque(pscodigoformulario VARCHAR)
  RETURN VARCHAR2 IS

  lspaquete imp_print_spool.cod_paqu%TYPE;

BEGIN
  /*
  if r_form.cod_form='BD' then                     imp_paque_docum_bolet_despa x
  if r_form.cod_form='OC' then                     imp_paque_docum_ocs x
  if r_form.cod_form='UN' then                     imp_paque_docum_unot x
  if r_form.cod_form='DF' then                     imp_paque_docum_detal_factu x
  if r_form.cod_form='CP' then                     imp_paque_docum_cupon x
  if r_form.cod_form='CT' then                     imp_paque_docum_cupon x imp_paque_docum_laser_ctact x
  if r_form.cod_form='BR' then                     imp_paque_docum_borec x
  if r_form.cod_form='FA' then                     IMP_PAQUE_DOCUM_LASER_FACTU x
  if r_form.cod_form='FX' then                     flx_paque_docum x
  if r_form.cod_form='PP' then                     IMP_PAQUE_DOCUM_PROGR_PUNTO x*/
  SELECT lpad(MIN(a.cod_pado), 2, '0')
    INTO lspaquete
    FROM imp_formu_paque_docu2 a
   WHERE a.cod_form = pscodigoformulario
     AND a.est_form = '1';

  RETURN lspaquete;
END imp_fn_print_devue_paque;
/**************************************************************************
Descripcion         : Genera la boleta de despacho
Fecha Creacion      : 04/10/2010
Autor               : Sergio Buchelli
Parametros Entrada  :
    p_codigoPais            : Codigo del pais.
    p_codigoPeriodo         : Codigo periodo
    p_fechaFacturacion     : Fecha Facturacion
IMP-105
**************************************************************************/
PROCEDURE imp_pr_print_bolet_despa
(
  pscodigopais          VARCHAR2,
  pscodigoperiodo       VARCHAR2,
  psfechafacturacion    VARCHAR2,
  pnoidzona             NUMBER,
  pscodigotransaccional VARCHAR2,
  pscodigousuario       VARCHAR2
) IS
  w_filas    NUMBER := 1000;
  ls_sqlerrm VARCHAR2(150);

  CURSOR c_boletadespacho
  (
    oidpais               NUMBER,
    oidperiodo            NUMBER,
    indicadorenviolarissa VARCHAR2,
    numerolotefacturacion NUMBER,
    indicadordir2         VARCHAR2
  ) IS
    SELECT con.oid_soli_cabe,
           mc.oid_clie,
           mc.cod_clie,
           mc.val_ape1 || ' ' || mc.val_ape2 || ', ' || mc.val_nom1 || ' ' ||
           mc.val_nom2 nom_clie,
           sec.num_secu_fact_diar,
           con.val_nume_soli,
           --trim(stv.des_abrv_tipo_via)  || ' ' || trim(mcd.val_nomb_via) || ' ' || trim(mcd.num_ppal) || trim(mcd.val_obse) val_dir1,
           imp_pkg_proce_laser.imp_fn_obtiene_text_direc(imp_pkg_proce_laser.imp_fn_obtiene_direc_clie(pscodigopais,
                                                                                                       mc.oid_clie),
                                                         '') AS val_dir1,
           decode(indicadordir2,
                  'S',
                  imp_pkg_proce_laser.imp_fn_obtiene_text2_direc(imp_pkg_proce_laser.imp_fn_obtiene_direc_clie(pscodigopais,
                                                                                                               mc.oid_clie),
                                                                 ''),
                  '') AS val_dir2,
           /*trim('/' from
           GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(con.pais_oid_pais, mc.oid_clie, 4) || '/' ||
           GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(con.pais_oid_pais, mc.oid_clie, 3) || '/' ||
           GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(con.pais_oid_pais, mc.oid_clie, 2) || '/' ||
           GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(con.pais_oid_pais, mc.oid_clie, 1)) val_dir2,*/
           TRIM('/' FROM
                gen_pkg_gener.gen_fn_clien_texto_comun(mc.oid_clie, 'TF') || '/' ||
                gen_pkg_gener.gen_fn_clien_texto_comun(mc.oid_clie, 'TM')) val_tele,
           zon.oid_zona,
           zse.cod_secc,
           zon.cod_zona,
           ter.cod_terr,
           to_char(con.fec_fact, 'dd/mm/yyyy') fec_emis,
           con.num_unid_aten_tota,
           con.val_tota_paga_loca,
           (SELECT nvl(SUM(num_unid_aten), 0)
              FROM ped_solic_cabec psc,
                   ped_solic_posic psp
             WHERE psc.oid_soli_cabe = psp.soca_oid_soli_cabe
               AND psp.ind_dent_fuer_caja_bols != 'C'
               AND psp.num_unid_aten > 0
               AND psc.soca_oid_soli_cabe = con.oid_soli_cabe) num_unid_fuer_caja_tota,
           (SELECT z.dir_buzo
              FROM sto_acopi_cober z
             WHERE ltrim(z.cod_terr) = ter.cod_terr
               AND rownum = 1) dirbuz,
           (SELECT z.num_docu_cont_inte
              FROM fac_docum_conta_cabec z
             WHERE z.soca_oid_soli_cabe = con.oid_soli_cabe
               AND tido_oid_tipo_docu IN (1, 29)
               AND rownum = 1) doclega,
           to_char(psep.fec, 'dd/mm/yyyy') fecent,
           (SELECT cod_alma
              FROM bel_almac
             WHERE oid_alma = con.almc_oid_alma) almacen
      FROM mae_clien             mc,
           ped_solic_cabec       con,
           ped_solic_cabec_secue sec,
           ped_segui_pedid       psep,
           zon_zona              zon,
           zon_terri_admin       zta,
           zon_secci             zse,
           zon_terri             ter
     WHERE mc.oid_clie = con.clie_oid_clie
       AND con.zzon_oid_zona = zon.oid_zona
       AND con.oid_soli_cabe = psep.soca_oid_soli_cabe(+)
       AND con.terr_oid_terr = ter.oid_terr
       AND con.ztad_oid_terr_admi = zta.oid_terr_admi
       AND zta.zscc_oid_secc = zse.oid_secc
       AND con.pais_oid_pais = oidpais
       AND con.perd_oid_peri = oidperiodo
       AND con.zzon_oid_zona = pnoidzona
       AND con.fec_fact = to_date(psfechafacturacion, 'dd/mm/yyyy')
          --AND con.ind_inte_lari_gene = indicadorenviolarissa
       AND con.oid_soli_cabe = sec.soca_oid_soli_cabe
       AND con.num_unid_aten_tota > 0
       AND (numerolotefacturacion IS NULL OR
           con.num_lote_fact = numerolotefacturacion)
       AND EXISTS
     (SELECT NULL
              FROM int_lar_tipo_solici_pedido_dis l
             WHERE l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais)
     ORDER BY sec.num_secu_zona_ruta,
              sec.num_secu_fact_diar;

  r_boletadespacho c_boletadespacho%ROWTYPE;

  CURSOR c_detalleboleta(oidconsolidado NUMBER) IS
    SELECT psp.cod_posi,
           psp.num_unid_aten,
           decode(psc.ictp_oid_tipo_prog,
                  NULL,
                  psp.ind_dent_fuer_caja_bols,
                  'P') ind_dent_fuer_caja_bols,
           mp.cod_sap,
           nvl(psp.val_codi_vent, psp.val_codi_vent_fict) val_codi_vent,
           TRIM((SELECT val_i18n
                  FROM gen_i18n_sicc_pais i18n
                 WHERE i18n.attr_enti = 'MAE_PRODU'
                   AND i18n.val_oid = mp.oid_prod
                   AND i18n.idio_oid_idio = 1)) des_prod
      FROM ped_solic_cabec con,
           ped_solic_cabec psc,
           ped_solic_posic psp,
           mae_produ       mp
     WHERE con.oid_soli_cabe = psc.soca_oid_soli_cabe
       AND psc.oid_soli_cabe = psp.soca_oid_soli_cabe
       AND psp.prod_oid_prod = mp.oid_prod
       AND con.oid_soli_cabe = oidconsolidado
       AND psp.ind_dent_fuer_caja_bols != 'C'
       AND psp.num_unid_aten > 0
       AND nvl(mp.val_atri_3, '0') <> '1'
     ORDER BY psp.cod_posi;

  r_detalleboleta c_detalleboleta%ROWTYPE;

  -- Variables locales
  l_oidpais                  NUMBER;
  l_oidperiodo               NUMBER;
  l_oidcanal                 NUMBER;
  l_oidmarca                 NUMBER;
  l_codigoperiodo            VARCHAR2(25);
  l_correlativo              NUMBER := 1;
  l_indicadorenviolarissa    VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                           'indicadorEnvioLarissa');
  l_indicadorenvioultimolote VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                           'indicadorEnvioUltimoLote');
  l_numerolotefacturacion    NUMBER;
  l_clob                     CLOB;
  l_textoactual              VARCHAR2(1000) := '';
  l_contadordetalles         NUMBER := 0;
  lsoidboleta                NUMBER := 0;
  lscontador                 NUMBER := 0;

  -- Variables usadas para la inclusion del telefono en la boleta de despacho
  l_indicadortelefono VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                    'indicadorTelefono');
  l_textotelefono     VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                    'textoTelefono');

  -- Variables usadas para la inclusion del tag de saludo por cumplea?os
  l_indicadorcumpleanos   VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                        'indicadorCumpleanos');
  l_tagcumpleanosapertura VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                        'tagCumpleanosApertura');
  l_tagcumpleanoscierre   VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                        'tagCumpleanosCierre');

  --NUEVO PARAMETRO
  indicadordir2boletadespacho VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                            'indicadorDir2BoletaDespacho');

  --NUEVO PARAMETRO
  indicadorfechaentrega VARCHAR2(100) := nvl(imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                          'indicadorFechaEntrega'),
                                             'N');

  lscodigopaquete imp_print_spool.cod_paqu%TYPE := imp_fn_print_devue_paque('BD');
BEGIN
  IF lscodigopaquete IS NULL THEN
    RETURN;
  END IF;
  -- Obtenemos los OIDs necesarios
  l_oidpais       := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);
  l_oidcanal      := gen_pkg_gener.gen_fn_devuelve_id_canal(codigo_canal);
  l_oidmarca      := gen_pkg_gener.gen_fn_devuelve_id_marca(codigo_marca);
  l_oidperiodo    := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo,
                                                                l_oidmarca,
                                                                l_oidcanal);
  l_codigoperiodo := substr(pscodigoperiodo, 5, 2) || '/' ||
                     substr(pscodigoperiodo, 1, 4);

  IF (l_indicadorenvioultimolote = '1' OR l_indicadorenvioultimolote = 'S') THEN
  
    BEGIN
      SELECT MAX(cons.num_lote_fact)
        INTO l_numerolotefacturacion
        FROM ped_solic_cabec                cons,
             int_lar_tipo_solici_pedido_dis tspd
       WHERE cons.perd_oid_peri = l_oidperiodo
         AND cons.fec_fact = to_date(psfechafacturacion, 'dd/mm/yyyy')
         AND cons.ind_ts_no_conso = 0
         AND (cons.ind_pedi_prue = 0 OR cons.ind_pedi_prue IS NULL)
         AND cons.tspa_oid_tipo_soli_pais = tspd.tspa_oid_tipo_soli_pais
         AND cons.pais_oid_pais = l_oidpais;
    EXCEPTION
      WHEN OTHERS THEN
        l_numerolotefacturacion := NULL;
    END;
  
  END IF;

  OPEN c_boletadespacho(l_oidpais,
                        l_oidperiodo,
                        l_indicadorenviolarissa,
                        l_numerolotefacturacion,
                        indicadordir2boletadespacho);
  LOOP
    FETCH c_boletadespacho
      INTO r_boletadespacho;
    EXIT WHEN c_boletadespacho%NOTFOUND;
    BEGIN
    
      INSERT INTO imp_paque_docum_bolet_despa
        (cor_pdbd,
         cod_clie,
         val_nume_soli,
         xml_cons)
      VALUES
        (r_boletadespacho.val_nume_soli,
         r_boletadespacho.cod_clie,
         r_boletadespacho.val_nume_soli,
         empty_clob())
      RETURNING xml_cons INTO l_clob;
    
      -- Inicio del paquete
      l_textoactual := '<frmbd>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      l_textoactual := imp_pkg_proce_compa.imp_fn_etiqu_clasi_bolet_despa(r_boletadespacho.cod_clie) ||
                       imp_pkg_proce_compa.imp_fn_etiqu_estat_bolet_despa(r_boletadespacho.cod_clie);
    
      IF (l_textoactual IS NOT NULL AND length(l_textoactual) > 0) THEN
        dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
      END IF;
    
      -- Inicio boleta Despacho
      l_textoactual := '<pbd1>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      -- Inicio cabecera
      l_textoactual := '<blqcab>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      -- Saludo de cumplea?os
      IF l_indicadorcumpleanos = 'S' AND
         imp_pkg_proce_compa.imp_fn_valid_cumpl(r_boletadespacho.cod_clie) != 0 THEN
        l_textoactual := l_tagcumpleanosapertura || l_tagcumpleanoscierre;
        dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
      END IF;
    
      -- Numero de Secuencia
      l_textoactual := '<nsec>' || r_boletadespacho.num_secu_fact_diar ||
                       '</nsec>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      -- Chequeo de Pedido
      l_textoactual := '<chq></chq>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      -- Codigo Cliente
      l_textoactual := '<ccon>' || r_boletadespacho.cod_clie || '</ccon>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      -- Nombre Cliente
      l_textoactual := '<ncon>' || r_boletadespacho.nom_clie || '</ncon>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      -- Zona
      l_textoactual := '<czon>' || r_boletadespacho.cod_zona || '</czon>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      -- Territorio
      l_textoactual := '<cter>' || r_boletadespacho.cod_secc || ' - ' ||
                       r_boletadespacho.cod_terr || '</cter>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      -- Direccion 1
      l_textoactual := '<dir1>' ||
                       nvl(r_boletadespacho.dirbuz,
                           r_boletadespacho.val_dir1) || '</dir1>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      -- Direccion 2
      IF l_indicadortelefono = 'S' AND
         (r_boletadespacho.val_tele IS NOT NULL) THEN
        IF r_boletadespacho.dirbuz IS NULL THEN
          l_textoactual := '<dir2>' || r_boletadespacho.val_dir2 ||
                           l_textotelefono || r_boletadespacho.val_tele ||
                           '</dir2>';
        ELSE
          l_textoactual := '<dir2>' || l_textotelefono ||
                           r_boletadespacho.val_tele || '</dir2>';
        END IF;
        dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
      ELSE
        IF r_boletadespacho.dirbuz IS NULL THEN
          l_textoactual := '<dir2>' || r_boletadespacho.val_dir2 ||
                           '</dir2>';
        ELSE
          l_textoactual := '<dir2>' || '</dir2>';
        END IF;
        dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
      END IF;
    
      -- Nunero Boleta Despacho
      l_textoactual := '<nbd>' || r_boletadespacho.val_nume_soli ||
                       '</nbd>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      -- Periodo
      l_textoactual := '<fcam>' || l_codigoperiodo || '</fcam>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      -- Fecha
      l_textoactual := '<femi>' || r_boletadespacho.fec_emis || '</femi>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      IF indicadorfechaentrega = 'S' THEN
      
        -- Fecha Entrega
        l_textoactual := '<feen>' || r_boletadespacho.fecent || '</feen>';
        dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
      
        -- Fecha Entrega
        l_textoactual := '<doclega>' || r_boletadespacho.doclega ||
                         '</doclega>';
        dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
      
        -- Almacén
        l_textoactual := '<almacen>' || r_boletadespacho.almacen ||
                         '</almacen>';
        dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
      
      END IF;
    
      -- Fin Cabecera
      l_textoactual := '</blqcab>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      -- Inicio Bloque Numero de Paquetes
      l_textoactual := '<blqimp>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      -- Numero total de unidades atendidas
      l_textoactual := '<txt>' || r_boletadespacho.num_unid_aten_tota ||
                       '</txt>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      -- Separador
      l_textoactual := '<txt/>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      -- Monto del Pedido
      l_textoactual := '<txt>' || r_boletadespacho.val_tota_paga_loca ||
                       '</txt>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      -- Separador
      l_textoactual := '<txt/><txt></txt><txt></txt><txt/><txt></txt>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      -- Fin Bloque Numero de Paquetes
      l_textoactual := '</blqimp>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      -- Inicio de detalle de Boleta de Despacho
      l_textoactual := '<detalle>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      lscontador         := 2;
      l_contadordetalles := 0;
    
      lsoidboleta := imp_prin_bole_desp_seq.nextval;
    
      /*INSERTAMOS LA CABECERA*/
      INSERT INTO imp_print_bolet_despa
        (prsp_cod_proc,
         prsp_cod_clie,
         prsp_cod_paqu,
         oid_bole_desp,
         num_bole_desp,
         val_unid,
         val_unid_fcaj,
         fec_desp,
         est_bole_desp,
         num_pagi,
         val_tota_pagi,
         val_impo_tota,
         usu_crea,
         fec_crea,
         ind_acti,
         cod_tabl)
      VALUES
        (pscodigotransaccional,
         r_boletadespacho.cod_clie,
         lscodigopaquete,
         lsoidboleta,
         r_boletadespacho.val_nume_soli,
         r_boletadespacho.num_unid_aten_tota,
         r_boletadespacho.num_unid_fuer_caja_tota,
         r_boletadespacho.fec_emis,
         NULL,
         0,
         0,
         r_boletadespacho.val_tota_paga_loca,
         pscodigousuario,
         SYSDATE,
         1,
         'BDA');
      /*INSERTAMOS EL PRIMER DETALLE*/
      INSERT INTO imp_print_bolet_despa_detal
        (prsp_cod_clie,
         prsp_cod_paqu,
         prsp_cod_proc,
         prbl_oid_bole_desp,
         oid_bode_deta,
         --  cod_tabl,
         cod_tipo_fila,
         val_colu_01,
         val_colu_02,
         val_colu_03,
         val_colu_04,
         val_colu_05,
         val_colu_06,
         val_colu_07,
         val_colu_08,
         val_colu_09,
         val_colu_10,
         val_colu_11,
         val_colu_12,
         usu_crea,
         fec_crea,
         ind_acti)
      VALUES
        (r_boletadespacho.cod_clie,
         lscodigopaquete,
         pscodigotransaccional,
         lsoidboleta,
         1,
         -- ' ',
         'S',
         'UNID',
         'IND',
         'DESCRIPCION DEL PRODUCTO',
         'UNID',
         'IND',
         'DESCRIPCION DEL PRODUCTO',
         NULL,
         NULL,
         NULL,
         NULL,
         NULL,
         NULL,
         pscodigousuario,
         SYSDATE,
         1);
      /**/
    
      OPEN c_detalleboleta(r_boletadespacho.oid_soli_cabe);
      LOOP
        FETCH c_detalleboleta
          INTO r_detalleboleta;
        EXIT WHEN c_detalleboleta%NOTFOUND;
        BEGIN
        
          IF l_contadordetalles = 0 THEN
            l_textoactual := '<txt>SE ADJUNTAN ' ||
                             r_boletadespacho.num_unid_fuer_caja_tota ||
                             ' UNIDADES FUERA DE LA CAJA Y/O PREMIOS</txt>';
            l_textoactual := l_textoactual ||
                             '<txt>SIRVASE VERIFICAR: </txt>';
            l_textoactual := l_textoactual ||
                             '<txt>UNID<t/>IND<t/>DESCRIPCION DE PRODUCTO<t/><t/><t/><t/><t/><t/><t/>UNID<t/>IND<t/>DESCRIPCION DE PRODUCTO</txt>';
            dbms_lob.writeappend(l_clob,
                                 length(l_textoactual),
                                 l_textoactual);
          END IF;
        
          IF MOD(l_contadordetalles, 2) = 0 THEN
            l_textoactual := '<txt>' || r_detalleboleta.num_unid_aten ||
                             '<t/>' ||
                             r_detalleboleta.ind_dent_fuer_caja_bols ||
                             '<t/>' || r_detalleboleta.des_prod;
            l_textoactual := l_textoactual ||
                             '<t/><t/><t/><t/><t/><t/><t/>';
            dbms_lob.writeappend(l_clob,
                                 length(l_textoactual),
                                 l_textoactual);
            /*INSERTAMOS DETALLE SI EL CONTADOR ES PAR*/
            INSERT INTO imp_print_bolet_despa_detal
              (prsp_cod_clie,
               prsp_cod_paqu,
               prsp_cod_proc,
               prbl_oid_bole_desp,
               oid_bode_deta,
               --  cod_tabl,
               cod_tipo_fila,
               val_colu_01,
               val_colu_02,
               val_colu_03,
               val_colu_04,
               val_colu_05,
               val_colu_06,
               val_colu_07,
               val_colu_08,
               val_colu_09,
               val_colu_10,
               val_colu_11,
               val_colu_12,
               usu_crea,
               fec_crea,
               ind_acti)
            VALUES
              (r_boletadespacho.cod_clie,
               lscodigopaquete,
               pscodigotransaccional,
               lsoidboleta,
               lscontador,
               -- ' ',
               'D',
               r_detalleboleta.num_unid_aten,
               r_detalleboleta.ind_dent_fuer_caja_bols,
               r_detalleboleta.des_prod,
               ' ',
               ' ',
               ' ',
               NULL,
               NULL,
               NULL,
               NULL,
               NULL,
               NULL,
               pscodigousuario,
               SYSDATE,
               1);
            lscontador := lscontador + 1;
            /**/
          END IF;
        
          IF MOD(l_contadordetalles, 2) != 0 THEN
            l_textoactual := r_detalleboleta.num_unid_aten || '<t/>' ||
                             r_detalleboleta.ind_dent_fuer_caja_bols ||
                             '<t/>' || r_detalleboleta.des_prod || '</txt>';
            dbms_lob.writeappend(l_clob,
                                 length(l_textoactual),
                                 l_textoactual);
            /*ACTUALIZAMOS DETALLE FILA ANTERIOR SI EL CONTADOR ES IMPAR*/
            UPDATE imp_print_bolet_despa_detal
               SET val_colu_04 = r_detalleboleta.num_unid_aten,
                   val_colu_05 = r_detalleboleta.ind_dent_fuer_caja_bols,
                   val_colu_06 = r_detalleboleta.des_prod,
                   usu_modi    = pscodigousuario,
                   fec_modi    = SYSDATE
             WHERE prsp_cod_clie = r_boletadespacho.cod_clie
               AND prsp_cod_paqu = lscodigopaquete
               AND prsp_cod_proc = pscodigotransaccional
               AND prbl_oid_bole_desp = lsoidboleta
               AND oid_bode_deta = lscontador - 1;
            /**/
          
          END IF;
        
          l_contadordetalles := l_contadordetalles + 1;
        
        END;
      END LOOP;
      CLOSE c_detalleboleta;
    
      -- En caso el numero de detalles haya sido impar agregamos los ultimos campos vacios
      IF MOD(l_contadordetalles, 2) != 0 THEN
        l_textoactual := '<t/><t/></txt>';
        dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
      END IF;
    
      -- Fin de detalle de Boleta de Despacho
      l_textoactual := '</detalle>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      -- Fin boletaDespacho
      l_textoactual := '</pbd1></frmbd>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      -- l_correlativo := l_correlativo + 1;
    
    END;
  END LOOP;
  CLOSE c_boletadespacho;
  COMMIT;
EXCEPTION
  WHEN OTHERS THEN
    raise_application_error(-20123,
                            'ERROR imp_pr_print_bolet_despa: ' ||
                            substr(SQLERRM, 1, 250));
END imp_pr_print_bolet_despa;
/***************************************************************************
Descripcion       : Procedimiemto que implementa la logica en JAVA
Parametros        : Codigo de pais, codigo de periodo, codigo de marca, codigo de canal
Fecha Creacion    : 17/09/2014
Autor             : Gonzalo Javier Huertas Agurto
       06/01/205    Se agrega logica para Chile

IMP-162
***************************************************************************/
PROCEDURE imp_pr_print_bolet_recoj
(
  pscodigopais          IN VARCHAR2,
  pscodigoperiodo       IN VARCHAR2,
  pscodigomarca         IN VARCHAR2,
  pscodigocanal         IN VARCHAR2,
  pscodigotransaccional VARCHAR2,
  pscodigousuario       VARCHAR2
) IS
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(2500);

  CURSOR c_boleta_recojo_cabecera IS
    SELECT DISTINCT cb.cod_regi,
                    cb.cod_zona,
                    cb.cod_cabe_bore,
                    cb.cod_clie,
                    cb.num_reco,
                    '1' flag_imp
      FROM int_rec_linea_borec lb,
           int_rec_cabec_borec cb,
           rec_cabec_recla     rc,
           cra_perio           cra,
           seg_perio_corpo     sp,
           int_rec_cierr_borec cie
     WHERE cb.cod_pais = pscodigopais
       AND lb.cod_pais = pscodigopais
       AND cb.esbo_oid_esta_bor1 =
           int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais, 'I')
       AND cb.esbo_oid_esta_bor2 IS NULL
       AND cb.num_reco = 1
       AND cb.ind_regr_yobe = 0
       AND lb.ind_regr_yobe = 0
       AND cb.cod_cabe_bore = lb.cod_cabe_bore
       AND rc.oid_cabe_recl = lb.care_oid_cabe_recl
       AND cra.peri_oid_peri = sp.oid_peri
       AND cra.oid_peri = rc.perd_oid_peri_docu_refe
       AND cb.ind_envi_yobe = 1
       AND lb.ind_envi_yobe = 1
          -- AND cb.ind_envi_xero = 0
          -- AND lb.ind_envi_xero = 0
       AND cb.num_tota_unid_recl > 0
       AND cie.cod_regi =
           gen_pkg_gener.gen_fn_clien_datos(cb.cod_clie, 'COD_REGI')
       AND cie.cod_zona =
           gen_pkg_gener.gen_fn_clien_datos(cb.cod_clie, 'COD_ZONA')
       AND cie.cod_pais = cb.cod_pais
          /*AND (SELECT fec_proc
                 FROM bas_ctrl_fact
                WHERE sta_camp = 0
                  AND ind_camp_act = 1) >= trunc(cie.fec_cierr)
          AND (SELECT fec_proc
                 FROM bas_ctrl_fact
                WHERE sta_camp = 0
                  AND ind_camp_act = 1) <= trunc(cie.fec_cie2)*/
       AND cie.cod_peri = pscodigoperiodo
       AND cb.cod_clie IN (SELECT cod_clie FROM imp_print_spool)
    UNION
    SELECT DISTINCT cb.cod_regi,
                    cb.cod_zona,
                    cb.cod_cabe_bore,
                    cb.cod_clie,
                    cb.num_reco,
                    '1' flag_imp
      FROM int_rec_linea_borec lb,
           int_rec_cabec_borec cb,
           rec_cabec_recla     rc,
           cra_perio           cra,
           seg_perio_corpo     sp,
           ped_solic_cabec     con,
           int_rec_cierr_borec cie
     WHERE cb.cod_pais = pscodigopais
       AND lb.cod_pais = pscodigopais
       AND cb.cod_cabe_bore = lb.cod_cabe_bore
       AND cb.esbo_oid_esta_bor2 =
           int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais, 'NX')
       AND cb.num_reco = 2
       AND cb.esbo_oid_esta_bor1 =
           int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais, 'GE')
       AND cb.ind_envi_yobe = 1
       AND cb.ind_regr_yobe = 1
       AND lb.ind_envi_yobe = 1
       AND lb.ind_regr_yobe = 1
       AND rc.oid_cabe_recl = lb.care_oid_cabe_recl
       AND cra.peri_oid_peri = sp.oid_peri
       AND cra.oid_peri = rc.perd_oid_peri_docu_refe
       AND con.clie_oid_clie = cb.clie_oid_clie
       AND con.perd_oid_peri =
           gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo,
                                                      gen_pkg_gener.gen_fn_devuelve_id_marca(pscodigomarca),
                                                      gen_pkg_gener.gen_fn_devuelve_id_canal(pscodigocanal))
       AND con.grpr_oid_grup_proc =
           int_pkg_recla.gen_fn_devue_oid_grupo_proce('GP5')
       AND con.soca_oid_soli_cabe IS NULL
       AND con.tspa_oid_tipo_soli_pais IN
           (int_pkg_recla.gen_fn_devue_oid_tipo_solpa('C1'))
       AND (SELECT COUNT(*)
              FROM ped_solic_cabec x
             WHERE x.soca_oid_soli_cabe = con.oid_soli_cabe
               AND x.tspa_oid_tipo_soli_pais <>
                   (SELECT tsp.oid_tipo_soli_pais
                      FROM ped_tipo_solic_pais tsp,
                           ped_tipo_solic      ts
                     WHERE tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                       AND ts.cod_tipo_soli = 'SCUF')) <> 0 --- que no sea cargo de uso flexipago
       AND cie.cod_regi =
           gen_pkg_gener.gen_fn_clien_datos(cb.cod_clie, 'COD_REGI')
       AND cie.cod_zona =
           gen_pkg_gener.gen_fn_clien_datos(cb.cod_clie, 'COD_ZONA')
       AND cie.cod_pais = cb.cod_pais
          /*    AND (SELECT fec_proc
                 FROM bas_ctrl_fact
                WHERE sta_camp = 0
                  AND ind_camp_act = 1) >= trunc(cie.fec_cierr)
          AND (SELECT fec_proc
                 FROM bas_ctrl_fact
                WHERE sta_camp = 0
                  AND ind_camp_act = 1) <= trunc(cie.fec_cie2)*/
       AND cie.cod_peri = pscodigoperiodo
       AND cb.ind_envi_yob2 = 1
       AND lb.ind_envi_yob2 = 1
       AND cb.ind_envi_xer2 IS NULL
       AND lb.ind_envi_xer2 IS NULL
       AND cb.num_tota_unid_recl > 0
       AND cb.cod_clie IN (SELECT cod_clie FROM imp_print_spool)
    UNION
    SELECT DISTINCT cb.cod_regi,
                    cb.cod_zona,
                    cb.cod_cabe_bore,
                    cb.cod_clie,
                    cb.num_reco,
                    '1' flag_imp
      FROM int_rec_linea_borec lb,
           int_rec_cabec_borec cb,
           mae_clien           mc,
           rec_cabec_recla     rc,
           cra_perio           cra,
           seg_perio_corpo     sp,
           int_rec_cierr_borec cie
     WHERE cb.cod_pais = pscodigopais
       AND lb.cod_pais = pscodigopais
       AND cb.esbo_oid_esta_bor2 =
           int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais, 'NX')
       AND cb.num_reco = 2
       AND cb.esbo_oid_esta_bor1 =
           int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais, 'GE')
       AND cb.ind_envi_yobe = 1
       AND cb.ind_regr_yobe = 1
       AND lb.ind_envi_yobe = 1
       AND lb.ind_regr_yobe = 1
       AND cb.cod_cabe_bore = lb.cod_cabe_bore
       AND mc.oid_clie = cb.clie_oid_clie
       AND rc.oid_cabe_recl = lb.care_oid_cabe_recl
       AND cra.peri_oid_peri = sp.oid_peri
       AND cra.oid_peri = rc.perd_oid_peri_docu_refe
       AND cb.clie_oid_clie NOT IN
           (SELECT clie_oid_clie
              FROM ped_solic_cabec con1
             WHERE con1.grpr_oid_grup_proc =
                   int_pkg_recla.gen_fn_devue_oid_grupo_proce('GP5')
               AND con1.perd_oid_peri =
                   gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo,
                                                              gen_pkg_gener.gen_fn_devuelve_id_marca(pscodigomarca),
                                                              gen_pkg_gener.gen_fn_devuelve_id_canal(pscodigocanal))
               AND con1.soca_oid_soli_cabe IS NULL
               AND con1.tspa_oid_tipo_soli_pais IN
                   (int_pkg_recla.gen_fn_devue_oid_tipo_solpa('C1'))
               AND (SELECT COUNT(*)
                      FROM ped_solic_cabec x
                     WHERE x.soca_oid_soli_cabe = con1.oid_soli_cabe
                       AND x.tspa_oid_tipo_soli_pais <>
                           (SELECT tsp.oid_tipo_soli_pais
                              FROM ped_tipo_solic_pais tsp,
                                   ped_tipo_solic      ts
                             WHERE tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                               AND ts.cod_tipo_soli = 'SCUF')) <> 0 --- que no sea cargo de uso flexipago
            )
       AND (gen_pkg_gener.gen_fn_clien_datos(cb.cod_clie, 'OID_ZONA') IN
           (SELECT zzon_oid_zona
               FROM fac_contr_cierr ci
              WHERE ci.perd_oid_peri =
                    gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo,
                                                               gen_pkg_gener.gen_fn_devuelve_id_marca(pscodigomarca),
                                                               gen_pkg_gener.gen_fn_devuelve_id_canal(pscodigocanal))
                AND ci.tcie_oid_tipo_cier = 2) OR
           gen_pkg_gener.gen_fn_clien_datos(cb.cod_clie, 'OID_REGI') IN
           (SELECT zorg_oid_regi
               FROM fac_contr_cierr ci
              WHERE ci.perd_oid_peri =
                    gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo,
                                                               gen_pkg_gener.gen_fn_devuelve_id_marca(pscodigomarca),
                                                               gen_pkg_gener.gen_fn_devuelve_id_canal(pscodigocanal))
                AND ci.tcie_oid_tipo_cier = 1))
       AND cb.ind_envi_yob2 = 1
       AND lb.ind_envi_yob2 = 1
       AND cb.ind_envi_xer2 IS NULL
       AND lb.ind_envi_xer2 IS NULL
       AND cie.cod_regi =
           gen_pkg_gener.gen_fn_clien_datos(cb.cod_clie, 'COD_REGI')
       AND cie.cod_zona =
           gen_pkg_gener.gen_fn_clien_datos(cb.cod_clie, 'COD_ZONA')
       AND cie.cod_pais = cb.cod_pais
          /*AND (SELECT fec_proc
                 FROM bas_ctrl_fact
                WHERE sta_camp = 0
                  AND ind_camp_act = 1) >= trunc(cie.fec_cierr)
          AND (SELECT fec_proc
                 FROM bas_ctrl_fact
                WHERE sta_camp = 0
                  AND ind_camp_act = 1) <= trunc(cie.fec_cie2)*/
       AND cie.cod_peri = pscodigoperiodo
          /*in (
               select cod_peri
               from bas_ctrl_fact
               where sta_camp = 0
               and ind_camp_act = 1
          )*/
       AND cb.num_tota_unid_recl > 0
       AND cb.cod_clie IN (SELECT cod_clie FROM imp_print_spool)
     ORDER BY cod_clie,
              num_reco;

  TYPE boletarecojocabecerarec IS RECORD(
    cod_regi      VARCHAR2(2),
    cod_zona      VARCHAR2(4),
    cod_cabe_bore NUMBER(12),
    cod_clie      VARCHAR2(15),
    num_reco      NUMBER(4),
    flag_imp      VARCHAR2(11));

  TYPE boletarecojocabecerareccab IS TABLE OF boletarecojocabecerarec;
  boletarecojocabecerarecord boletarecojocabecerareccab;

  sizepaginacion    NUMBER;
  actualdireccionbr NUMBER;
  totalpaginas      NUMBER;

  datoenviobr VARCHAR2(10);

  rowhasta                   NUMBER;
  rowdesde                   NUMBER;
  codigocabeceraboletarecojo NUMBER(12);
  existenpaginas             BOOLEAN;
  existecabecera             BOOLEAN;
  pagina                     NUMBER;

  boletarecojolineatamanio NUMBER;

  vl_val_nume_bore      NUMBER(10);
  vl_num_reco           VARCHAR2(10);
  vl_cod_zona           VARCHAR2(4);
  vl_cod_secc           VARCHAR2(1);
  vl_cod_terr           NUMBER(8);
  vl_cod_peri_proc      VARCHAR2(6);
  vl_val_nume_bole_desp NUMBER(10);
  vl_fec_ingr           DATE;
  vl_nombre             VARCHAR2(200);
  vl_cod_clie           VARCHAR2(30);
  vl_telefono           VARCHAR2(100);
  vl_dir_br1            VARCHAR2(200);
  vl_des_urba           VARCHAR2(200);
  vl_des_dist           VARCHAR2(200);
  vl_des_prov           VARCHAR2(200);
  vl_des_dpto           VARCHAR2(200);
  vl_dir_br2            VARCHAR2(200);
  vl_num_tota_unid_recl NUMBER(4);

  TYPE boletarecojolinearec IS RECORD(
    val_nume_bore      NUMBER(10),
    cod_clie           VARCHAR2(15),
    fec_ingr           DATE,
    num_reco           VARCHAR2(10),
    num_tota_unid_recl NUMBER(4),
    cod_regi           VARCHAR2(2),
    cod_zona           VARCHAR2(4),
    cod_terr           NUMBER(8),
    cod_secc           VARCHAR2(1),
    val_nume_bole_desp NUMBER(10),
    cod_peri_proc      VARCHAR2(6),
    cod_prod           VARCHAR2(20),
    cod_anti           VARCHAR2(20),
    num_unid_recl      NUMBER(4),
    des_oper           VARCHAR2(24),
    num_secu           NUMBER(4),
    cod_peri           VARCHAR2(6),
    nombre             VARCHAR2(200),
    direccion          VARCHAR2(400),
    telefono           VARCHAR2(100),
    des_urba           VARCHAR2(200),
    des_dist           VARCHAR2(200),
    des_prov           VARCHAR2(200),
    des_dpto           VARCHAR2(200),
    codigounicoventa   NUMBER(10),
    des_cort           VARCHAR2(500),
    dir_br1            VARCHAR2(200),
    dir_br2            VARCHAR2(200),
    rnum               NUMBER(10));

  TYPE boletarecojolineareccab IS TABLE OF boletarecojolinearec;
  boletarecojolinearecord boletarecojolineareccab;

  TYPE boletarecojolinealongrec IS RECORD(
    tamanio_cursor NUMBER(8));

  TYPE boletarecojolinealongreccab IS TABLE OF boletarecojolinealongrec;
  boletarecojolinealongrecord boletarecojolinealongreccab;

  archivoxml  VARCHAR2(4000);
  archivoxml1 VARCHAR2(1000);
  archivoxml2 VARCHAR2(3000);

  CURSOR c_boleta_recojo_linea_long IS
    SELECT COUNT(1) AS tamanio_cursor
      FROM (SELECT a.*,
                   rownum rnum
              FROM (SELECT cb.val_nume_bore,
                           mc.cod_clie,
                           decode(cb.num_reco,
                                  1,
                                  cb.fec_ingr,
                                  2,
                                  cb.fec_ing2,
                                  NULL) fec_ingr,
                           decode(cb.num_reco,
                                  1,
                                  'PRIMER ',
                                  2,
                                  'SEGUNDO ',
                                  '-') num_reco,
                           cb.num_tota_unid_recl,
                           gen_pkg_gener.gen_fn_clien_datos(mc.cod_clie,
                                                            'COD_REGI') AS cod_regi,
                           gen_pkg_gener.gen_fn_clien_datos(mc.cod_clie,
                                                            'COD_ZONA') AS cod_zona,
                           gen_pkg_gener.gen_fn_clien_datos(mc.cod_clie,
                                                            'COD_TERR') AS cod_terr,
                           gen_pkg_gener.gen_fn_clien_datos(mc.cod_clie,
                                                            'COD_SECC') AS cod_secc,
                           nvl(cb.val_nume_bole_desp, '0') val_nume_bole_desp,
                           cb.cod_peri_proc,
                           lb.cod_prod,
                           lb.cod_anti,
                           lb.num_unid_recl - lb.num_unid_elim num_unid_recl,
                           (SELECT (CASE val_param
                                     WHEN 'S' THEN
                                      lb.des_oper || ' (' || lb.cod_moti_devo || ')'
                                     ELSE
                                      lb.des_oper
                                   END)
                              FROM sto_param_gener_occrr
                             WHERE cod_para = 'STO_IMP_MOT_BR') des_oper,
                           lb.num_secu,
                           sp.cod_peri,
                           TRIM(mc.val_nom1) || ' ' || TRIM(mc.val_ape1) || ' ' ||
                           TRIM(mc.val_ape2) AS nombre,
                           nvl(gen_pkg_gener.gen_fn_clien_datos_oid(cb.clie_oid_clie,
                                                                    'DIR_CLIE'),
                               '-') direccion,
                           nvl(gen_pkg_gener.gen_fn_clien_texto_comun(cb.clie_oid_clie,
                                                                      'TF'),
                               '-') AS telefono,
                           nvl(gen_pkg_gener.gen_fn_clien_datos_oid(cb.clie_oid_clie,
                                                                    'DES_URBA'),
                               '-') AS des_urba,
                           nvl(gen_pkg_gener.gen_fn_clien_datos_oid(cb.clie_oid_clie,
                                                                    'DES_DIST'),
                               '-') AS des_dist,
                           nvl(gen_pkg_gener.gen_fn_clien_datos_oid(cb.clie_oid_clie,
                                                                    'DES_PROV'),
                               '-') AS des_prov,
                           nvl(gen_pkg_gener.gen_fn_clien_datos_oid(cb.clie_oid_clie,
                                                                    'DES_DPTO'),
                               '-') AS des_dpto,
                           decode(datoenviobr,
                                  'C',
                                  nvl(decode(lb.copa_oid_para_gral,
                                             NULL,
                                             int_pkg_recla.gen_fn_devue_cod_venta_mfact(lb.tofe_oid_tipo_ofer,
                                                                                        lb.mafa_oid_matr_fact,
                                                                                        lb.prod_oid_prod),
                                             int_pkg_recla.gen_fn_devue_cod_venta_ficti(lb.lopa_oid_lote_prem_arti,
                                                                                        lb.panp_oid_para_nive_prem,
                                                                                        lb.copa_oid_para_gral,
                                                                                        lb.prod_oid_prod)),
                                      '-'),
                                  rc.num_recl) AS codigounicoventa,
                           /*NVL(GEN_PKG_GENER.GEN_FN_DEVUELVE_DESCRIPCION(LB.PROD_OID_PROD,
                                                                     'MAE_PRODU',
                                                                     'es'),
                           '-') DES_CORT,*/
                           nvl(imp_pkg_proce_laser.imp_fn_desc_produ_br(pscodigopais,
                                                                        lb.prod_oid_prod),
                               '-') des_cort,
                           imp_pkg_proce_laser.imp_fn_obtiene_text_direc(imp_pkg_proce_laser.imp_fn_obtiene_direc_clie(pscodigopais,
                                                                                                                       mc.oid_clie),
                                                                         '') AS dir_br1,
                           imp_pkg_proce_laser.imp_fn_obtiene_text2_direc(imp_pkg_proce_laser.imp_fn_obtiene_direc_clie(pscodigopais,
                                                                                                                        mc.oid_clie),
                                                                          '') AS dir_br2
                      FROM int_rec_linea_borec lb,
                           int_rec_cabec_borec cb,
                           mae_clien           mc,
                           rec_cabec_recla     rc,
                           cra_perio           cra,
                           seg_perio_corpo     sp,
                           int_rec_cierr_borec cie
                     WHERE cb.cod_pais = pscodigopais
                       AND lb.cod_pais = cb.cod_pais
                       AND cb.esbo_oid_esta_bor1 =
                           int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,
                                                                      'I')
                       AND cb.esbo_oid_esta_bor2 IS NULL
                       AND cb.num_reco = 1
                       AND cb.ind_regr_yobe = 0
                       AND lb.ind_regr_yobe = 0
                       AND cb.cod_cabe_bore = lb.cod_cabe_bore
                       AND mc.oid_clie = cb.clie_oid_clie
                       AND rc.oid_cabe_recl = lb.care_oid_cabe_recl
                       AND cra.peri_oid_peri = sp.oid_peri
                       AND cra.oid_peri = rc.perd_oid_peri_docu_refe
                       AND cb.ind_envi_yobe = 1
                       AND lb.ind_envi_yobe = 1
                          -- AND cb.ind_envi_xero = 0
                          -- AND lb.ind_envi_xero = 0
                       AND cb.cod_cabe_bore = codigocabeceraboletarecojo
                       AND cie.cod_regi =
                           gen_pkg_gener.gen_fn_clien_datos(cb.cod_clie,
                                                            'COD_REGI')
                       AND cie.cod_zona =
                           gen_pkg_gener.gen_fn_clien_datos(cb.cod_clie,
                                                            'COD_ZONA')
                       AND cie.cod_pais = cb.cod_pais
                          /*AND (SELECT fec_proc
                                 FROM bas_ctrl_fact
                                WHERE sta_camp = 0
                                  AND ind_camp_act = 1) >= trunc(cie.fec_cierr)
                          AND (SELECT fec_proc
                                 FROM bas_ctrl_fact
                                WHERE sta_camp = 0
                                  AND ind_camp_act = 1) <= trunc(cie.fec_cie2)*/
                       AND cie.cod_peri = pscodigoperiodo
                       AND cb.cod_clie IN
                           (SELECT cod_clie FROM imp_print_spool)
                    UNION
                    SELECT cb.val_nume_bore,
                           mc.cod_clie,
                           decode(cb.num_reco,
                                  1,
                                  cb.fec_ingr,
                                  2,
                                  cb.fec_ing2,
                                  NULL) fec_ingr,
                           decode(cb.num_reco,
                                  1,
                                  'PRIMER ',
                                  2,
                                  'SEGUNDO ',
                                  '-') num_reco,
                           (cb.num_tota_unid_recl -
                           (SELECT SUM(ll.num_unid_elim)
                               FROM int_rec_linea_borec ll
                              WHERE ll.cod_cabe_bore = cb.cod_cabe_bore)) num_tota_unid_recl,
                           gen_pkg_gener.gen_fn_clien_datos(mc.cod_clie,
                                                            'COD_REGI') AS cod_regi,
                           gen_pkg_gener.gen_fn_clien_datos(mc.cod_clie,
                                                            'COD_ZONA') AS cod_zona,
                           gen_pkg_gener.gen_fn_clien_datos(mc.cod_clie,
                                                            'COD_TERR') AS cod_terr,
                           gen_pkg_gener.gen_fn_clien_datos(mc.cod_clie,
                                                            'COD_SECC') AS cod_secc,
                           nvl(cb.val_nume_bole_desp, '0') val_nume_bole_desp,
                           cb.cod_peri_proc,
                           lb.cod_prod,
                           lb.cod_anti,
                           lb.num_unid_recl - lb.num_unid_elim num_unid_recl,
                           (SELECT (CASE val_param
                                     WHEN 'S' THEN
                                      lb.des_oper || ' (' || lb.cod_moti_devo || ')'
                                     ELSE
                                      lb.des_oper
                                   END)
                              FROM sto_param_gener_occrr
                             WHERE cod_para = 'STO_IMP_MOT_BR') des_oper,
                           lb.num_secu,
                           sp.cod_peri,
                           TRIM(mc.val_nom1) || ' ' || TRIM(mc.val_ape1) || ' ' ||
                           TRIM(mc.val_ape2) AS nombre,
                           nvl(gen_pkg_gener.gen_fn_clien_datos_oid(cb.clie_oid_clie,
                                                                    'DIR_CLIE'),
                               '-') direccion,
                           nvl(gen_pkg_gener.gen_fn_clien_texto_comun(cb.clie_oid_clie,
                                                                      'TF'),
                               '-') AS telefono,
                           nvl(gen_pkg_gener.gen_fn_clien_datos_oid(cb.clie_oid_clie,
                                                                    'DES_URBA'),
                               '-') AS des_urba,
                           nvl(gen_pkg_gener.gen_fn_clien_datos_oid(cb.clie_oid_clie,
                                                                    'DES_DIST'),
                               '-') AS des_dist,
                           nvl(gen_pkg_gener.gen_fn_clien_datos_oid(cb.clie_oid_clie,
                                                                    'DES_PROV'),
                               '-') AS des_prov,
                           nvl(gen_pkg_gener.gen_fn_clien_datos_oid(cb.clie_oid_clie,
                                                                    'DES_DPTO'),
                               '-') AS des_dpto,
                           decode(datoenviobr,
                                  'C',
                                  nvl(decode(lb.copa_oid_para_gral,
                                             NULL,
                                             int_pkg_recla.gen_fn_devue_cod_venta_mfact(lb.tofe_oid_tipo_ofer,
                                                                                        lb.mafa_oid_matr_fact,
                                                                                        lb.prod_oid_prod),
                                             int_pkg_recla.gen_fn_devue_cod_venta_ficti(lb.lopa_oid_lote_prem_arti,
                                                                                        lb.panp_oid_para_nive_prem,
                                                                                        lb.copa_oid_para_gral,
                                                                                        lb.prod_oid_prod)),
                                      '-'),
                                  rc.num_recl) AS codigounicoventa,
                           /* NVL(GEN_PKG_GENER.GEN_FN_DEVUELVE_DESCRIPCION(LB.PROD_OID_PROD,
                                                                     'MAE_PRODU',
                                                                     'es'),
                           '-') DES_CORT, */
                           nvl(imp_pkg_proce_laser.imp_fn_desc_produ_br(pscodigopais,
                                                                        lb.prod_oid_prod),
                               '-') des_cort,
                           imp_pkg_proce_laser.imp_fn_obtiene_text_direc(imp_pkg_proce_laser.imp_fn_obtiene_direc_clie(pscodigopais,
                                                                                                                       mc.oid_clie),
                                                                         '') AS dir_br1,
                           imp_pkg_proce_laser.imp_fn_obtiene_text2_direc(imp_pkg_proce_laser.imp_fn_obtiene_direc_clie(pscodigopais,
                                                                                                                        mc.oid_clie),
                                                                          '') AS dir_br2
                      FROM int_rec_linea_borec lb,
                           int_rec_cabec_borec cb,
                           mae_clien           mc,
                           rec_cabec_recla     rc,
                           cra_perio           cra,
                           seg_perio_corpo     sp,
                           ped_solic_cabec     con,
                           int_rec_cierr_borec cie
                     WHERE cb.cod_pais = pscodigopais
                       AND lb.cod_pais = cb.cod_pais
                       AND cb.cod_cabe_bore = lb.cod_cabe_bore
                       AND cb.esbo_oid_esta_bor2 =
                           int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,
                                                                      'NX')
                       AND cb.num_reco = 2
                       AND cb.esbo_oid_esta_bor1 =
                           int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,
                                                                      'GE')
                       AND cb.ind_envi_yobe = 1
                       AND cb.ind_regr_yobe = 1
                       AND lb.ind_envi_yobe = 1
                       AND lb.ind_regr_yobe = 1
                       AND mc.oid_clie = cb.clie_oid_clie
                       AND rc.oid_cabe_recl = lb.care_oid_cabe_recl
                       AND cra.peri_oid_peri = sp.oid_peri
                       AND cra.oid_peri = rc.perd_oid_peri_docu_refe
                       AND con.clie_oid_clie = cb.clie_oid_clie
                       AND con.perd_oid_peri =
                           gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo,
                                                                      gen_pkg_gener.gen_fn_devuelve_id_marca(pscodigomarca),
                                                                      gen_pkg_gener.gen_fn_devuelve_id_canal(pscodigocanal))
                       AND con.grpr_oid_grup_proc =
                           int_pkg_recla.gen_fn_devue_oid_grupo_proce('GP5')
                       AND con.soca_oid_soli_cabe IS NULL
                       AND con.tspa_oid_tipo_soli_pais IN
                           (int_pkg_recla.gen_fn_devue_oid_tipo_solpa('C1'))
                       AND (SELECT COUNT(*)
                              FROM ped_solic_cabec x
                             WHERE x.soca_oid_soli_cabe = con.oid_soli_cabe
                               AND x.tspa_oid_tipo_soli_pais <>
                                   (SELECT tsp.oid_tipo_soli_pais
                                      FROM ped_tipo_solic_pais tsp,
                                           ped_tipo_solic      ts
                                     WHERE tsp.tsol_oid_tipo_soli =
                                           ts.oid_tipo_soli
                                       AND ts.cod_tipo_soli = 'SCUF')) <> 0 --- que no sea cargo de uso flexipago
                       AND cie.cod_regi =
                           gen_pkg_gener.gen_fn_clien_datos(cb.cod_clie,
                                                            'COD_REGI')
                       AND cie.cod_zona =
                           gen_pkg_gener.gen_fn_clien_datos(cb.cod_clie,
                                                            'COD_ZONA')
                       AND cie.cod_pais = cb.cod_pais
                          /* AND (SELECT fec_proc
                                 FROM bas_ctrl_fact
                                WHERE sta_camp = 0
                                  AND ind_camp_act = 1) >= trunc(cie.fec_cierr)
                          AND (SELECT fec_proc
                                 FROM bas_ctrl_fact
                                WHERE sta_camp = 0
                                  AND ind_camp_act = 1) <= trunc(cie.fec_cie2)*/
                       AND cie.cod_peri = pscodigoperiodo
                       AND cb.ind_envi_yob2 = 1
                       AND lb.ind_envi_yob2 = 1
                       AND cb.ind_envi_xer2 IS NULL
                       AND lb.ind_envi_xer2 IS NULL
                       AND cb.cod_cabe_bore = codigocabeceraboletarecojo
                       AND cb.cod_clie IN
                           (SELECT cod_clie FROM imp_print_spool)
                    UNION
                    SELECT cb.val_nume_bore,
                           mc.cod_clie,
                           decode(cb.num_reco,
                                  1,
                                  cb.fec_ingr,
                                  2,
                                  cb.fec_ing2,
                                  NULL) fec_ingr,
                           decode(cb.num_reco,
                                  1,
                                  'PRIMER ',
                                  2,
                                  'SEGUNDO ',
                                  '-') num_reco,
                           (cb.num_tota_unid_recl -
                           (SELECT SUM(ll.num_unid_elim)
                               FROM int_rec_linea_borec ll
                              WHERE ll.cod_cabe_bore = cb.cod_cabe_bore)) num_tota_unid_recl,
                           gen_pkg_gener.gen_fn_clien_datos(mc.cod_clie,
                                                            'COD_REGI') AS cod_regi,
                           gen_pkg_gener.gen_fn_clien_datos(mc.cod_clie,
                                                            'COD_ZONA') AS cod_zona,
                           gen_pkg_gener.gen_fn_clien_datos(mc.cod_clie,
                                                            'COD_TERR') AS cod_terr,
                           gen_pkg_gener.gen_fn_clien_datos(mc.cod_clie,
                                                            'COD_SECC') AS cod_secc,
                           nvl(cb.val_nume_bole_desp, '0') val_nume_bole_desp,
                           cb.cod_peri_proc,
                           lb.cod_prod,
                           lb.cod_anti,
                           lb.num_unid_recl - lb.num_unid_elim num_unid_recl,
                           (SELECT (CASE val_param
                                     WHEN 'S' THEN
                                      lb.des_oper || ' (' || lb.cod_moti_devo || ')'
                                     ELSE
                                      lb.des_oper
                                   END)
                              FROM sto_param_gener_occrr
                             WHERE cod_para = 'STO_IMP_MOT_BR') des_oper,
                           lb.num_secu,
                           sp.cod_peri,
                           TRIM(mc.val_nom1) || ' ' || TRIM(mc.val_ape1) || ' ' ||
                           TRIM(mc.val_ape2) AS nombre,
                           nvl(gen_pkg_gener.gen_fn_clien_datos_oid(cb.clie_oid_clie,
                                                                    'DIR_CLIE'),
                               '-') direccion,
                           nvl(gen_pkg_gener.gen_fn_clien_texto_comun(cb.clie_oid_clie,
                                                                      'TF'),
                               '-') AS telefono,
                           nvl(gen_pkg_gener.gen_fn_clien_datos_oid(cb.clie_oid_clie,
                                                                    'DES_URBA'),
                               '-') AS des_urba,
                           nvl(gen_pkg_gener.gen_fn_clien_datos_oid(cb.clie_oid_clie,
                                                                    'DES_DIST'),
                               '-') AS des_dist,
                           nvl(gen_pkg_gener.gen_fn_clien_datos_oid(cb.clie_oid_clie,
                                                                    'DES_PROV'),
                               '-') AS des_prov,
                           nvl(gen_pkg_gener.gen_fn_clien_datos_oid(cb.clie_oid_clie,
                                                                    'DES_DPTO'),
                               '-') AS des_dpto,
                           decode(datoenviobr,
                                  'C',
                                  nvl(decode(lb.copa_oid_para_gral,
                                             NULL,
                                             int_pkg_recla.gen_fn_devue_cod_venta_mfact(lb.tofe_oid_tipo_ofer,
                                                                                        lb.mafa_oid_matr_fact,
                                                                                        lb.prod_oid_prod),
                                             int_pkg_recla.gen_fn_devue_cod_venta_ficti(lb.lopa_oid_lote_prem_arti,
                                                                                        lb.panp_oid_para_nive_prem,
                                                                                        lb.copa_oid_para_gral,
                                                                                        lb.prod_oid_prod)),
                                      '-'),
                                  rc.num_recl) AS codigounicoventa,
                           /*NVL(GEN_PKG_GENER.GEN_FN_DEVUELVE_DESCRIPCION(LB.PROD_OID_PROD,
                                                                     'MAE_PRODU',
                                                                     'es'),
                           '-') DES_CORT,*/
                           nvl(imp_pkg_proce_laser.imp_fn_desc_produ_br(pscodigopais,
                                                                        lb.prod_oid_prod),
                               '-') des_cort,
                           imp_pkg_proce_laser.imp_fn_obtiene_text_direc(imp_pkg_proce_laser.imp_fn_obtiene_direc_clie(pscodigopais,
                                                                                                                       mc.oid_clie),
                                                                         '') AS dir_br1,
                           imp_pkg_proce_laser.imp_fn_obtiene_text2_direc(imp_pkg_proce_laser.imp_fn_obtiene_direc_clie(pscodigopais,
                                                                                                                        mc.oid_clie),
                                                                          '') AS dir_br2
                      FROM int_rec_linea_borec lb,
                           int_rec_cabec_borec cb,
                           mae_clien           mc,
                           rec_cabec_recla     rc,
                           cra_perio           cra,
                           seg_perio_corpo     sp,
                           int_rec_cierr_borec cie
                     WHERE cb.cod_pais = pscodigopais
                       AND lb.cod_pais = cb.cod_pais
                       AND cb.esbo_oid_esta_bor2 =
                           int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,
                                                                      'NX')
                       AND cb.num_reco = 2
                       AND cb.esbo_oid_esta_bor1 =
                           int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,
                                                                      'GE')
                       AND cb.ind_envi_yobe = 1
                       AND cb.ind_regr_yobe = 1
                       AND lb.ind_envi_yobe = 1
                       AND lb.ind_regr_yobe = 1
                       AND cb.cod_cabe_bore = lb.cod_cabe_bore
                       AND mc.oid_clie = cb.clie_oid_clie
                       AND rc.oid_cabe_recl = lb.care_oid_cabe_recl
                       AND cra.peri_oid_peri = sp.oid_peri
                       AND cra.oid_peri = rc.perd_oid_peri_docu_refe
                       AND cb.clie_oid_clie NOT IN
                           (SELECT clie_oid_clie
                              FROM ped_solic_cabec con1
                             WHERE con1.grpr_oid_grup_proc =
                                   int_pkg_recla.gen_fn_devue_oid_grupo_proce('GP5')
                               AND con1.perd_oid_peri =
                                   gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo,
                                                                              gen_pkg_gener.gen_fn_devuelve_id_marca(pscodigomarca),
                                                                              gen_pkg_gener.gen_fn_devuelve_id_canal(pscodigocanal))
                               AND con1.soca_oid_soli_cabe IS NULL
                               AND con1.tspa_oid_tipo_soli_pais IN
                                   (int_pkg_recla.gen_fn_devue_oid_tipo_solpa('C1'))
                               AND (SELECT COUNT(*)
                                      FROM ped_solic_cabec x
                                     WHERE x.soca_oid_soli_cabe =
                                           con1.oid_soli_cabe
                                       AND x.tspa_oid_tipo_soli_pais <>
                                           (SELECT tsp.oid_tipo_soli_pais
                                              FROM ped_tipo_solic_pais tsp,
                                                   ped_tipo_solic      ts
                                             WHERE tsp.tsol_oid_tipo_soli =
                                                   ts.oid_tipo_soli
                                               AND ts.cod_tipo_soli = 'SCUF')) <> 0 --- que no sea cargo de uso flexipago
                            )
                       AND (gen_pkg_gener.gen_fn_clien_datos(mc.cod_clie,
                                                             'OID_ZONA') IN
                           (SELECT zzon_oid_zona
                               FROM fac_contr_cierr ci
                              WHERE ci.perd_oid_peri =
                                    gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo,
                                                                               gen_pkg_gener.gen_fn_devuelve_id_marca(pscodigomarca),
                                                                               gen_pkg_gener.gen_fn_devuelve_id_canal(pscodigocanal))
                                AND ci.tcie_oid_tipo_cier = 2) OR
                           gen_pkg_gener.gen_fn_clien_datos(mc.cod_clie,
                                                             'OID_REGI') IN
                           (SELECT zorg_oid_regi
                               FROM fac_contr_cierr ci
                              WHERE ci.perd_oid_peri =
                                    gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo,
                                                                               gen_pkg_gener.gen_fn_devuelve_id_marca(pscodigomarca),
                                                                               gen_pkg_gener.gen_fn_devuelve_id_canal(pscodigocanal))
                                AND ci.tcie_oid_tipo_cier = 1))
                       AND cb.ind_envi_yob2 = 1
                       AND lb.ind_envi_yob2 = 1
                          --  AND cb.ind_envi_xer2 IS NULL
                          --  AND lb.ind_envi_xer2 IS NULL
                       AND cie.cod_regi =
                           gen_pkg_gener.gen_fn_clien_datos(cb.cod_clie,
                                                            'COD_REGI')
                       AND cie.cod_zona =
                           gen_pkg_gener.gen_fn_clien_datos(cb.cod_clie,
                                                            'COD_ZONA')
                       AND cie.cod_pais = cb.cod_pais
                          /*AND (SELECT fec_proc
                                 FROM bas_ctrl_fact
                                WHERE sta_camp = 0
                                  AND ind_camp_act = 1) >= trunc(cie.fec_cierr)
                          AND (SELECT fec_proc
                                 FROM bas_ctrl_fact
                                WHERE sta_camp = 0
                                  AND ind_camp_act = 1) <= trunc(cie.fec_cie2)*/
                       AND cie.cod_peri = pscodigoperiodo
                       AND cb.cod_cabe_bore = codigocabeceraboletarecojo
                       AND cb.cod_clie IN
                           (SELECT cod_clie FROM imp_print_spool)) a
             WHERE a.num_unid_recl > 0
               AND a.num_tota_unid_recl > 0
               AND (rowhasta IS NULL OR rownum <= rowhasta)
            
            )
     WHERE (rowdesde IS NULL OR rnum >= rowdesde);

  CURSOR c_boleta_recojo_linea IS
    SELECT *
      FROM (SELECT a.*,
                   rownum rnum
              FROM (SELECT cb.val_nume_bore,
                           mc.cod_clie,
                           decode(cb.num_reco,
                                  1,
                                  cb.fec_ingr,
                                  2,
                                  cb.fec_ing2,
                                  NULL) fec_ingr,
                           decode(cb.num_reco,
                                  1,
                                  'PRIMER ',
                                  2,
                                  'SEGUNDO ',
                                  '-') num_reco,
                           cb.num_tota_unid_recl,
                           gen_pkg_gener.gen_fn_clien_datos(mc.cod_clie,
                                                            'COD_REGI') AS cod_regi,
                           gen_pkg_gener.gen_fn_clien_datos(mc.cod_clie,
                                                            'COD_ZONA') AS cod_zona,
                           gen_pkg_gener.gen_fn_clien_datos(mc.cod_clie,
                                                            'COD_TERR') AS cod_terr,
                           gen_pkg_gener.gen_fn_clien_datos(mc.cod_clie,
                                                            'COD_SECC') AS cod_secc,
                           nvl(cb.val_nume_bole_desp, '0') val_nume_bole_desp,
                           cb.cod_peri_proc,
                           lb.cod_prod,
                           lb.cod_anti,
                           lb.num_unid_recl - lb.num_unid_elim num_unid_recl,
                           (SELECT (CASE val_param
                                     WHEN 'S' THEN
                                      lb.des_oper || ' (' || lb.cod_moti_devo || ')'
                                     ELSE
                                      lb.des_oper
                                   END)
                              FROM sto_param_gener_occrr
                             WHERE cod_para = 'STO_IMP_MOT_BR') des_oper,
                           lb.num_secu,
                           sp.cod_peri,
                           TRIM(mc.val_nom1) || ' ' || TRIM(mc.val_ape1) || ' ' ||
                           TRIM(mc.val_ape2) AS nombre,
                           nvl(gen_pkg_gener.gen_fn_clien_datos_oid(cb.clie_oid_clie,
                                                                    'DIR_CLIE'),
                               '-') direccion,
                           nvl(gen_pkg_gener.gen_fn_clien_texto_comun(cb.clie_oid_clie,
                                                                      'TF'),
                               '-') AS telefono,
                           nvl(gen_pkg_gener.gen_fn_clien_datos_oid(cb.clie_oid_clie,
                                                                    'DES_URBA'),
                               '-') AS des_urba,
                           nvl(gen_pkg_gener.gen_fn_clien_datos_oid(cb.clie_oid_clie,
                                                                    'DES_DIST'),
                               '-') AS des_dist,
                           nvl(gen_pkg_gener.gen_fn_clien_datos_oid(cb.clie_oid_clie,
                                                                    'DES_PROV'),
                               '-') AS des_prov,
                           nvl(gen_pkg_gener.gen_fn_clien_datos_oid(cb.clie_oid_clie,
                                                                    'DES_DPTO'),
                               '-') AS des_dpto,
                           decode(datoenviobr,
                                  'C',
                                  nvl(decode(lb.copa_oid_para_gral,
                                             NULL,
                                             int_pkg_recla.gen_fn_devue_cod_venta_mfact(lb.tofe_oid_tipo_ofer,
                                                                                        lb.mafa_oid_matr_fact,
                                                                                        lb.prod_oid_prod),
                                             int_pkg_recla.gen_fn_devue_cod_venta_ficti(lb.lopa_oid_lote_prem_arti,
                                                                                        lb.panp_oid_para_nive_prem,
                                                                                        lb.copa_oid_para_gral,
                                                                                        lb.prod_oid_prod)),
                                      '-'),
                                  rc.num_recl) AS codigounicoventa,
                           /*NVL(GEN_PKG_GENER.GEN_FN_DEVUELVE_DESCRIPCION(LB.PROD_OID_PROD,
                                                                     'MAE_PRODU',
                                                                     'es'),
                           '-') DES_CORT, */
                           nvl(imp_pkg_proce_laser.imp_fn_desc_produ_br(pscodigopais,
                                                                        lb.prod_oid_prod),
                               '-') des_cort,
                           imp_pkg_proce_laser.imp_fn_obtiene_text_direc(imp_pkg_proce_laser.imp_fn_obtiene_direc_clie(pscodigopais,
                                                                                                                       mc.oid_clie),
                                                                         '') AS dir_br1,
                           imp_pkg_proce_laser.imp_fn_obtiene_text2_direc(imp_pkg_proce_laser.imp_fn_obtiene_direc_clie(pscodigopais,
                                                                                                                        mc.oid_clie),
                                                                          '') AS dir_br2
                      FROM int_rec_linea_borec lb,
                           int_rec_cabec_borec cb,
                           mae_clien           mc,
                           rec_cabec_recla     rc,
                           cra_perio           cra,
                           seg_perio_corpo     sp,
                           int_rec_cierr_borec cie
                     WHERE cb.cod_pais = pscodigopais
                       AND lb.cod_pais = cb.cod_pais
                       AND cb.esbo_oid_esta_bor1 =
                           int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,
                                                                      'I')
                       AND cb.esbo_oid_esta_bor2 IS NULL
                       AND cb.num_reco = 1
                       AND cb.ind_regr_yobe = 0
                       AND lb.ind_regr_yobe = 0
                       AND cb.cod_cabe_bore = lb.cod_cabe_bore
                       AND mc.oid_clie = cb.clie_oid_clie
                       AND rc.oid_cabe_recl = lb.care_oid_cabe_recl
                       AND cra.peri_oid_peri = sp.oid_peri
                       AND cra.oid_peri = rc.perd_oid_peri_docu_refe
                       AND cb.ind_envi_yobe = 1
                       AND lb.ind_envi_yobe = 1
                          -- AND cb.ind_envi_xero = 0
                          --  AND lb.ind_envi_xero = 0
                       AND cb.cod_cabe_bore = codigocabeceraboletarecojo
                       AND cie.cod_regi =
                           gen_pkg_gener.gen_fn_clien_datos(cb.cod_clie,
                                                            'COD_REGI')
                       AND cie.cod_zona =
                           gen_pkg_gener.gen_fn_clien_datos(cb.cod_clie,
                                                            'COD_ZONA')
                       AND cie.cod_pais = cb.cod_pais
                          /* AND (SELECT fec_proc
                                 FROM bas_ctrl_fact
                                WHERE sta_camp = 0
                                  AND ind_camp_act = 1) >= trunc(cie.fec_cierr)
                          AND (SELECT fec_proc
                                 FROM bas_ctrl_fact
                                WHERE sta_camp = 0
                                  AND ind_camp_act = 1) <= trunc(cie.fec_cie2)*/
                       AND cie.cod_peri = pscodigoperiodo
                       AND cb.cod_clie IN
                           (SELECT cod_clie FROM imp_print_spool)
                    UNION
                    SELECT cb.val_nume_bore,
                           mc.cod_clie,
                           decode(cb.num_reco,
                                  1,
                                  cb.fec_ingr,
                                  2,
                                  cb.fec_ing2,
                                  NULL) fec_ingr,
                           decode(cb.num_reco,
                                  1,
                                  'PRIMER ',
                                  2,
                                  'SEGUNDO ',
                                  '-') num_reco,
                           (cb.num_tota_unid_recl -
                           (SELECT SUM(ll.num_unid_elim)
                               FROM int_rec_linea_borec ll
                              WHERE ll.cod_cabe_bore = cb.cod_cabe_bore)) num_tota_unid_recl,
                           gen_pkg_gener.gen_fn_clien_datos(mc.cod_clie,
                                                            'COD_REGI') AS cod_regi,
                           gen_pkg_gener.gen_fn_clien_datos(mc.cod_clie,
                                                            'COD_ZONA') AS cod_zona,
                           gen_pkg_gener.gen_fn_clien_datos(mc.cod_clie,
                                                            'COD_TERR') AS cod_terr,
                           gen_pkg_gener.gen_fn_clien_datos(mc.cod_clie,
                                                            'COD_SECC') AS cod_secc,
                           nvl(cb.val_nume_bole_desp, '0') val_nume_bole_desp,
                           cb.cod_peri_proc,
                           lb.cod_prod,
                           lb.cod_anti,
                           lb.num_unid_recl - lb.num_unid_elim num_unid_recl,
                           (SELECT (CASE val_param
                                     WHEN 'S' THEN
                                      lb.des_oper || ' (' || lb.cod_moti_devo || ')'
                                     ELSE
                                      lb.des_oper
                                   END)
                              FROM sto_param_gener_occrr
                             WHERE cod_para = 'STO_IMP_MOT_BR') des_oper,
                           lb.num_secu,
                           sp.cod_peri,
                           TRIM(mc.val_nom1) || ' ' || TRIM(mc.val_ape1) || ' ' ||
                           TRIM(mc.val_ape2) AS nombre,
                           nvl(gen_pkg_gener.gen_fn_clien_datos_oid(cb.clie_oid_clie,
                                                                    'DIR_CLIE'),
                               '-') direccion,
                           nvl(gen_pkg_gener.gen_fn_clien_texto_comun(cb.clie_oid_clie,
                                                                      'TF'),
                               '-') AS telefono,
                           nvl(gen_pkg_gener.gen_fn_clien_datos_oid(cb.clie_oid_clie,
                                                                    'DES_URBA'),
                               '-') AS des_urba,
                           nvl(gen_pkg_gener.gen_fn_clien_datos_oid(cb.clie_oid_clie,
                                                                    'DES_DIST'),
                               '-') AS des_dist,
                           nvl(gen_pkg_gener.gen_fn_clien_datos_oid(cb.clie_oid_clie,
                                                                    'DES_PROV'),
                               '-') AS des_prov,
                           nvl(gen_pkg_gener.gen_fn_clien_datos_oid(cb.clie_oid_clie,
                                                                    'DES_DPTO'),
                               '-') AS des_dpto,
                           decode(datoenviobr,
                                  'C',
                                  nvl(decode(lb.copa_oid_para_gral,
                                             NULL,
                                             int_pkg_recla.gen_fn_devue_cod_venta_mfact(lb.tofe_oid_tipo_ofer,
                                                                                        lb.mafa_oid_matr_fact,
                                                                                        lb.prod_oid_prod),
                                             int_pkg_recla.gen_fn_devue_cod_venta_ficti(lb.lopa_oid_lote_prem_arti,
                                                                                        lb.panp_oid_para_nive_prem,
                                                                                        lb.copa_oid_para_gral,
                                                                                        lb.prod_oid_prod)),
                                      '-'),
                                  rc.num_recl) AS codigounicoventa,
                           /*NVL(GEN_PKG_GENER.GEN_FN_DEVUELVE_DESCRIPCION(LB.PROD_OID_PROD,
                                                                     'MAE_PRODU',
                                                                     'es'),
                           '-') DES_CORT,*/
                           nvl(imp_pkg_proce_laser.imp_fn_desc_produ_br(pscodigopais,
                                                                        lb.prod_oid_prod),
                               '-') des_cort,
                           imp_pkg_proce_laser.imp_fn_obtiene_text_direc(imp_pkg_proce_laser.imp_fn_obtiene_direc_clie(pscodigopais,
                                                                                                                       mc.oid_clie),
                                                                         '') AS dir_br1,
                           imp_pkg_proce_laser.imp_fn_obtiene_text2_direc(imp_pkg_proce_laser.imp_fn_obtiene_direc_clie(pscodigopais,
                                                                                                                        mc.oid_clie),
                                                                          '') AS dir_br2
                      FROM int_rec_linea_borec lb,
                           int_rec_cabec_borec cb,
                           mae_clien           mc,
                           rec_cabec_recla     rc,
                           cra_perio           cra,
                           seg_perio_corpo     sp,
                           ped_solic_cabec     con,
                           int_rec_cierr_borec cie
                     WHERE cb.cod_pais = pscodigopais
                       AND lb.cod_pais = cb.cod_pais
                       AND cb.cod_cabe_bore = lb.cod_cabe_bore
                       AND cb.esbo_oid_esta_bor2 =
                           int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,
                                                                      'NX')
                       AND cb.num_reco = 2
                       AND cb.esbo_oid_esta_bor1 =
                           int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,
                                                                      'GE')
                       AND cb.ind_envi_yobe = 1
                       AND cb.ind_regr_yobe = 1
                       AND lb.ind_envi_yobe = 1
                       AND lb.ind_regr_yobe = 1
                       AND mc.oid_clie = cb.clie_oid_clie
                       AND rc.oid_cabe_recl = lb.care_oid_cabe_recl
                       AND cra.peri_oid_peri = sp.oid_peri
                       AND cra.oid_peri = rc.perd_oid_peri_docu_refe
                       AND con.clie_oid_clie = cb.clie_oid_clie
                       AND con.perd_oid_peri =
                           gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo,
                                                                      gen_pkg_gener.gen_fn_devuelve_id_marca(pscodigomarca),
                                                                      gen_pkg_gener.gen_fn_devuelve_id_canal(pscodigocanal))
                       AND con.grpr_oid_grup_proc =
                           int_pkg_recla.gen_fn_devue_oid_grupo_proce('GP5')
                       AND con.soca_oid_soli_cabe IS NULL
                       AND con.tspa_oid_tipo_soli_pais IN
                           (int_pkg_recla.gen_fn_devue_oid_tipo_solpa('C1'))
                       AND (SELECT COUNT(*)
                              FROM ped_solic_cabec x
                             WHERE x.soca_oid_soli_cabe = con.oid_soli_cabe
                               AND x.tspa_oid_tipo_soli_pais <>
                                   (SELECT tsp.oid_tipo_soli_pais
                                      FROM ped_tipo_solic_pais tsp,
                                           ped_tipo_solic      ts
                                     WHERE tsp.tsol_oid_tipo_soli =
                                           ts.oid_tipo_soli
                                       AND ts.cod_tipo_soli = 'SCUF')) <> 0 --- que no sea cargo de uso flexipago
                       AND cie.cod_regi =
                           gen_pkg_gener.gen_fn_clien_datos(cb.cod_clie,
                                                            'COD_REGI')
                       AND cie.cod_zona =
                           gen_pkg_gener.gen_fn_clien_datos(cb.cod_clie,
                                                            'COD_ZONA')
                       AND cie.cod_pais = cb.cod_pais
                          /* AND (SELECT fec_proc
                                 FROM bas_ctrl_fact
                                WHERE sta_camp = 0
                                  AND ind_camp_act = 1) >= trunc(cie.fec_cierr)
                          AND (SELECT fec_proc
                                 FROM bas_ctrl_fact
                                WHERE sta_camp = 0
                                  AND ind_camp_act = 1) <= trunc(cie.fec_cie2)*/
                       AND cie.cod_peri = pscodigoperiodo
                       AND cb.ind_envi_yob2 = 1
                       AND lb.ind_envi_yob2 = 1
                       AND cb.ind_envi_xer2 IS NULL
                       AND lb.ind_envi_xer2 IS NULL
                       AND cb.cod_cabe_bore = codigocabeceraboletarecojo
                       AND cb.cod_clie IN
                           (SELECT cod_clie FROM imp_print_spool)
                    UNION
                    SELECT cb.val_nume_bore,
                           mc.cod_clie,
                           decode(cb.num_reco,
                                  1,
                                  cb.fec_ingr,
                                  2,
                                  cb.fec_ing2,
                                  NULL) fec_ingr,
                           decode(cb.num_reco,
                                  1,
                                  'PRIMER ',
                                  2,
                                  'SEGUNDO ',
                                  '-') num_reco,
                           (cb.num_tota_unid_recl -
                           (SELECT SUM(ll.num_unid_elim)
                               FROM int_rec_linea_borec ll
                              WHERE ll.cod_cabe_bore = cb.cod_cabe_bore)) num_tota_unid_recl,
                           gen_pkg_gener.gen_fn_clien_datos(mc.cod_clie,
                                                            'COD_REGI') AS cod_regi,
                           gen_pkg_gener.gen_fn_clien_datos(mc.cod_clie,
                                                            'COD_ZONA') AS cod_zona,
                           gen_pkg_gener.gen_fn_clien_datos(mc.cod_clie,
                                                            'COD_TERR') AS cod_terr,
                           gen_pkg_gener.gen_fn_clien_datos(mc.cod_clie,
                                                            'COD_SECC') AS cod_secc,
                           nvl(cb.val_nume_bole_desp, '0') val_nume_bole_desp,
                           cb.cod_peri_proc,
                           lb.cod_prod,
                           lb.cod_anti,
                           lb.num_unid_recl - lb.num_unid_elim num_unid_recl,
                           (SELECT (CASE val_param
                                     WHEN 'S' THEN
                                      lb.des_oper || ' (' || lb.cod_moti_devo || ')'
                                     ELSE
                                      lb.des_oper
                                   END)
                              FROM sto_param_gener_occrr
                             WHERE cod_para = 'STO_IMP_MOT_BR') des_oper,
                           lb.num_secu,
                           sp.cod_peri,
                           TRIM(mc.val_nom1) || ' ' || TRIM(mc.val_ape1) || ' ' ||
                           TRIM(mc.val_ape2) AS nombre,
                           nvl(gen_pkg_gener.gen_fn_clien_datos_oid(cb.clie_oid_clie,
                                                                    'DIR_CLIE'),
                               '-') direccion,
                           nvl(gen_pkg_gener.gen_fn_clien_texto_comun(cb.clie_oid_clie,
                                                                      'TF'),
                               '-') AS telefono,
                           nvl(gen_pkg_gener.gen_fn_clien_datos_oid(cb.clie_oid_clie,
                                                                    'DES_URBA'),
                               '-') AS des_urba,
                           nvl(gen_pkg_gener.gen_fn_clien_datos_oid(cb.clie_oid_clie,
                                                                    'DES_DIST'),
                               '-') AS des_dist,
                           nvl(gen_pkg_gener.gen_fn_clien_datos_oid(cb.clie_oid_clie,
                                                                    'DES_PROV'),
                               '-') AS des_prov,
                           nvl(gen_pkg_gener.gen_fn_clien_datos_oid(cb.clie_oid_clie,
                                                                    'DES_DPTO'),
                               '-') AS des_dpto,
                           decode(datoenviobr,
                                  'C',
                                  nvl(decode(lb.copa_oid_para_gral,
                                             NULL,
                                             int_pkg_recla.gen_fn_devue_cod_venta_mfact(lb.tofe_oid_tipo_ofer,
                                                                                        lb.mafa_oid_matr_fact,
                                                                                        lb.prod_oid_prod),
                                             int_pkg_recla.gen_fn_devue_cod_venta_ficti(lb.lopa_oid_lote_prem_arti,
                                                                                        lb.panp_oid_para_nive_prem,
                                                                                        lb.copa_oid_para_gral,
                                                                                        lb.prod_oid_prod)),
                                      '-'),
                                  rc.num_recl) AS codigounicoventa,
                           /*NVL(GEN_PKG_GENER.GEN_FN_DEVUELVE_DESCRIPCION(LB.PROD_OID_PROD,
                                                                     'MAE_PRODU',
                                                                     'es'),
                           '-') DES_CORT, */
                           nvl(imp_pkg_proce_laser.imp_fn_desc_produ_br(pscodigopais,
                                                                        lb.prod_oid_prod),
                               '-') des_cort,
                           imp_pkg_proce_laser.imp_fn_obtiene_text_direc(imp_pkg_proce_laser.imp_fn_obtiene_direc_clie(pscodigopais,
                                                                                                                       mc.oid_clie),
                                                                         '') AS dir_br1,
                           imp_pkg_proce_laser.imp_fn_obtiene_text2_direc(imp_pkg_proce_laser.imp_fn_obtiene_direc_clie(pscodigopais,
                                                                                                                        mc.oid_clie),
                                                                          '') AS dir_br2
                      FROM int_rec_linea_borec lb,
                           int_rec_cabec_borec cb,
                           mae_clien           mc,
                           rec_cabec_recla     rc,
                           cra_perio           cra,
                           seg_perio_corpo     sp,
                           int_rec_cierr_borec cie
                     WHERE cb.cod_pais = pscodigopais
                       AND lb.cod_pais = cb.cod_pais
                       AND cb.esbo_oid_esta_bor2 =
                           int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,
                                                                      'NX')
                       AND cb.num_reco = 2
                       AND cb.esbo_oid_esta_bor1 =
                           int_pkg_recla.gen_fn_devue_oid_estad_borec(pscodigopais,
                                                                      'GE')
                       AND cb.ind_envi_yobe = 1
                       AND cb.ind_regr_yobe = 1
                       AND lb.ind_envi_yobe = 1
                       AND lb.ind_regr_yobe = 1
                       AND cb.cod_cabe_bore = lb.cod_cabe_bore
                       AND mc.oid_clie = cb.clie_oid_clie
                       AND rc.oid_cabe_recl = lb.care_oid_cabe_recl
                       AND cra.peri_oid_peri = sp.oid_peri
                       AND cra.oid_peri = rc.perd_oid_peri_docu_refe
                       AND cb.clie_oid_clie NOT IN
                           (SELECT clie_oid_clie
                              FROM ped_solic_cabec con1
                             WHERE con1.grpr_oid_grup_proc =
                                   int_pkg_recla.gen_fn_devue_oid_grupo_proce('GP5')
                               AND con1.perd_oid_peri =
                                   gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo,
                                                                              gen_pkg_gener.gen_fn_devuelve_id_marca(pscodigomarca),
                                                                              gen_pkg_gener.gen_fn_devuelve_id_canal(pscodigocanal))
                               AND con1.soca_oid_soli_cabe IS NULL
                               AND con1.tspa_oid_tipo_soli_pais IN
                                   (int_pkg_recla.gen_fn_devue_oid_tipo_solpa('C1'))
                               AND (SELECT COUNT(*)
                                      FROM ped_solic_cabec x
                                     WHERE x.soca_oid_soli_cabe =
                                           con1.oid_soli_cabe
                                       AND x.tspa_oid_tipo_soli_pais <>
                                           (SELECT tsp.oid_tipo_soli_pais
                                              FROM ped_tipo_solic_pais tsp,
                                                   ped_tipo_solic      ts
                                             WHERE tsp.tsol_oid_tipo_soli =
                                                   ts.oid_tipo_soli
                                               AND ts.cod_tipo_soli = 'SCUF')) <> 0 --- que no sea cargo de uso flexipago
                            )
                       AND (gen_pkg_gener.gen_fn_clien_datos(mc.cod_clie,
                                                             'OID_ZONA') IN
                           (SELECT zzon_oid_zona
                               FROM fac_contr_cierr ci
                              WHERE ci.perd_oid_peri =
                                    gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo,
                                                                               gen_pkg_gener.gen_fn_devuelve_id_marca(pscodigomarca),
                                                                               gen_pkg_gener.gen_fn_devuelve_id_canal(pscodigocanal))
                                AND ci.tcie_oid_tipo_cier = 2) OR
                           gen_pkg_gener.gen_fn_clien_datos(mc.cod_clie,
                                                             'OID_REGI') IN
                           (SELECT zorg_oid_regi
                               FROM fac_contr_cierr ci
                              WHERE ci.perd_oid_peri =
                                    gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo,
                                                                               gen_pkg_gener.gen_fn_devuelve_id_marca(pscodigomarca),
                                                                               gen_pkg_gener.gen_fn_devuelve_id_canal(pscodigocanal))
                                AND ci.tcie_oid_tipo_cier = 1))
                       AND cb.ind_envi_yob2 = 1
                       AND lb.ind_envi_yob2 = 1
                       AND cb.ind_envi_xer2 IS NULL
                       AND lb.ind_envi_xer2 IS NULL
                       AND cie.cod_regi =
                           gen_pkg_gener.gen_fn_clien_datos(cb.cod_clie,
                                                            'COD_REGI')
                       AND cie.cod_zona =
                           gen_pkg_gener.gen_fn_clien_datos(cb.cod_clie,
                                                            'COD_ZONA')
                       AND cie.cod_pais = cb.cod_pais
                          /*AND (SELECT fec_proc
                                 FROM bas_ctrl_fact
                                WHERE sta_camp = 0
                                  AND ind_camp_act = 1) >= trunc(cie.fec_cierr)
                          AND (SELECT fec_proc
                                 FROM bas_ctrl_fact
                                WHERE sta_camp = 0
                                  AND ind_camp_act = 1) <= trunc(cie.fec_cie2)*/
                       AND cie.cod_peri = pscodigoperiodo
                       AND cb.cod_cabe_bore = codigocabeceraboletarecojo
                       AND cb.cod_clie IN
                           (SELECT cod_clie FROM imp_print_spool)) a
             WHERE a.num_unid_recl > 0
               AND a.num_tota_unid_recl > 0
               AND (rowhasta IS NULL OR rownum <= rowhasta)
            
            )
     WHERE (rowdesde IS NULL OR rnum >= rowdesde);

  lsindgenebrua VARCHAR2(1);
  lsindgenebrok VARCHAR2(1);

  lncuentaua               NUMBER := 0;
  lncorrelativoboleta      NUMBER := 0;
  lncuentacierrezonaregion NUMBER := 0;
  lnoidtipocierra          NUMBER;
  lnoidtipocierrar         NUMBER;

  lsactuacronobr VARCHAR2(1);
  lsflagimpr     VARCHAR2(1);

  lb_clienteold NUMBER(12) := NULL;
  lb_numrecoold NUMBER(4);

  lncontador         NUMBER(5) := 1;
  lnflagcambiopagina BOOLEAN := TRUE;

  lscodigopaquete imp_print_spool.cod_paqu%TYPE := imp_fn_print_devue_paque('BD');
BEGIN
  IF lscodigopaquete IS NULL THEN
    RETURN;
  END IF;

  EXECUTE IMMEDIATE 'TRUNCATE TABLE INT_BOLET_RECOJ_XML';
  --obtenemos los datos necesarios
  SELECT to_number(val_para_prim)
    INTO sizepaginacion
    FROM imp_param_proce_impre
   WHERE prim_cod_proc = 'LAS'
     AND nom_para_prim = 'numeroDetallesBoletaRecojo'
     AND est_para_prim = '1';

  SELECT to_number(val_param)
    INTO actualdireccionbr
    FROM sto_param_gener_occrr
   WHERE cod_pais = pscodigopais
     AND cod_para = 'STO_ACTUA_DIREC_BR';

  SELECT val_param
    INTO datoenviobr
    FROM sto_param_gener_occrr a
   WHERE a.cod_pais = pscodigopais
     AND a.cod_para = 'STO_DATO_ENVIA_BR';

  IF datoenviobr = '' THEN
    datoenviobr := 'C';
  END IF;

  lsactuacronobr := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                         'STO_ACTUA_CRONO_BR');
  lsindgenebrua  := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                             'STO_IND_GENE_BR_UA'),
                        '0');

  --- Si no se procesa las UA, todos los registros se dan como OK
  IF lsindgenebrua = '0' THEN
    lsindgenebrok := '1';
  ELSE
    lsindgenebrok := '0';
  END IF;

  lnoidtipocierra  := 2;
  lnoidtipocierrar := 1;

  --este es el primer cursor, el de mayor jerarquia, en el codigo java linea 2006
  OPEN c_boleta_recojo_cabecera;
  LOOP
  
    FETCH c_boleta_recojo_cabecera BULK COLLECT
      INTO boletarecojocabecerarecord LIMIT w_filas;
    IF boletarecojocabecerarecord.count > 0 THEN
    
      -- si verifica la generacion de BR para Chile
      IF lsactuacronobr = '4' THEN
      
        FOR j IN boletarecojocabecerarecord.first .. boletarecojocabecerarecord.last
        LOOP
        
          IF (lb_clienteold IS NULL OR
             lb_clienteold <> boletarecojocabecerarecord(j).cod_clie) THEN
          
            ---- valida si zona o region esta en tabla de santiago
            SELECT COUNT(*)
              INTO lncuentaua
              FROM int_rec_gene_borec
             WHERE cod_pais = pscodigopais
               AND (cod_regi =
                   gen_pkg_gener.gen_fn_clien_datos(boletarecojocabecerarecord(j)
                                                     .cod_clie,
                                                     'COD_REGI') OR
                   cod_zona =
                   gen_pkg_gener.gen_fn_clien_datos(boletarecojocabecerarecord(j)
                                                     .cod_clie,
                                                     'COD_ZONA'))
               AND ind_reg = '1';
          
            SELECT COUNT(*)
              INTO lncuentacierrezonaregion
              FROM fac_progr_cierr x,
                   bas_ctrl_fact   y
             WHERE x.fec_cier = y.fec_proc
               AND y.sta_camp = 0
               AND y.ind_camp_act = 1
               AND x.tip_cier = 'R'
               AND x.est_regi = '1'
               AND x.est_cier IN ('A', 'P')
               AND x.cod_regi = boletarecojocabecerarecord(j).cod_regi
               AND x.cam_proc = pscodigoperiodo;
          
            --- si la consultora es de santiago
            IF lncuentaua > 0 THEN
              -- si tiene primer recojo se imprime todas las del cliente
              IF boletarecojocabecerarecord(j).num_reco = 1 THEN
                lsflagimpr := '1';
              ELSE
                -- si no tiene primer recojo y es cierre de region se imprime
                IF lncuentacierrezonaregion > 0 THEN
                  lsflagimpr := '1';
                ELSE
                  lsflagimpr := '0';
                END IF;
              END IF;
            ELSE
              -- si no es de santiago y es cierre de region se imprime
              IF lncuentacierrezonaregion > 0 THEN
                lsflagimpr := '1';
              ELSE
                lsflagimpr := '0';
              END IF;
            END IF;
            lb_clienteold := boletarecojocabecerarecord(j).cod_clie;
          END IF;
          boletarecojocabecerarecord(j).flag_imp := lsflagimpr;
        
        END LOOP;
      
      END IF;
    
      FOR i IN boletarecojocabecerarecord.first .. boletarecojocabecerarecord.last
      LOOP
      
        IF boletarecojocabecerarecord(i).flag_imp = '1' THEN
        
          --dentro del recorrido del cursor cada elemento es "boletaRecojoClientes"
          rowdesde := NULL;
          rowhasta := NULL;
        
          codigocabeceraboletarecojo := boletarecojocabecerarecord(i)
                                        .cod_cabe_bore;
          --segundo cursor, mas interno "boletaRecojoLineaList"
          FOR v_linea IN c_boleta_recojo_linea_long
          LOOP
            boletarecojolineatamanio := v_linea.tamanio_cursor;
          END LOOP;
        
          IF MOD(boletarecojolineatamanio, sizepaginacion) != 0 THEN
            totalpaginas := boletarecojolineatamanio / sizepaginacion + 1;
          ELSE
            totalpaginas := boletarecojolineatamanio / sizepaginacion;
          END IF;
        
          SELECT floor(totalpaginas) INTO totalpaginas FROM dual;
        
          existenpaginas := TRUE;
          existecabecera := FALSE;
        
          rowdesde := 1;
          rowhasta := sizepaginacion;
          pagina   := 1;
        
          WHILE (existenpaginas)
          LOOP
            --se vuelve a llamar al segundo cursor, el mas interno, pero con los datos
            --actualizados, se llama con cada iteracion actualizando los datos
          
            OPEN c_boleta_recojo_linea_long;
            LOOP
            
              FETCH c_boleta_recojo_linea_long BULK COLLECT
                INTO boletarecojolinealongrecord LIMIT w_filas;
              IF boletarecojolinealongrecord.count > 0 THEN
                FOR k IN boletarecojolinealongrecord.first .. boletarecojolinealongrecord.last
                LOOP
                  boletarecojolineatamanio := boletarecojolinealongrecord(k)
                                              .tamanio_cursor;
                END LOOP;
              END IF;
              EXIT WHEN c_boleta_recojo_linea_long%NOTFOUND;
            END LOOP;
            CLOSE c_boleta_recojo_linea_long;
          
            OPEN c_boleta_recojo_linea;
            LOOP
            
              FETCH c_boleta_recojo_linea BULK COLLECT
                INTO boletarecojolinearecord LIMIT w_filas;
              IF boletarecojolinearecord.count > 0 THEN
                FOR j IN boletarecojolinearecord.first .. boletarecojolinearecord.last
                LOOP
                
                  IF NOT existecabecera THEN
                    vl_val_nume_bore      := boletarecojolinearecord(j)
                                             .val_nume_bore;
                    vl_num_reco           := boletarecojolinearecord(j)
                                             .num_reco;
                    vl_cod_zona           := boletarecojolinearecord(j)
                                             .cod_zona;
                    vl_cod_secc           := boletarecojolinearecord(j)
                                             .cod_secc;
                    vl_cod_terr           := boletarecojolinearecord(j)
                                             .cod_terr;
                    vl_cod_peri_proc      := boletarecojolinearecord(j)
                                             .cod_peri_proc;
                    vl_val_nume_bole_desp := boletarecojolinearecord(j)
                                             .val_nume_bole_desp;
                    vl_fec_ingr           := boletarecojolinearecord(j)
                                             .fec_ingr;
                    vl_nombre             := boletarecojolinearecord(j)
                                             .nombre;
                    vl_cod_clie           := boletarecojolinearecord(j)
                                             .cod_clie;
                    vl_telefono           := boletarecojolinearecord(j)
                                             .telefono;
                    vl_dir_br1            := boletarecojolinearecord(j)
                                             .dir_br1;
                    vl_des_dist           := boletarecojolinearecord(j)
                                             .des_dist;
                    vl_des_prov           := boletarecojolinearecord(j)
                                             .des_prov;
                    vl_des_dpto           := boletarecojolinearecord(j)
                                             .des_dpto;
                    vl_des_urba           := boletarecojolinearecord(j)
                                             .des_urba;
                    vl_dir_br2            := boletarecojolinearecord(j)
                                             .dir_br2;
                    vl_num_tota_unid_recl := boletarecojolinearecord(j)
                                             .num_tota_unid_recl;
                    lncorrelativoboleta   := boletarecojocabecerarecord(i)
                                             .cod_cabe_bore;
                    INSERT INTO imp_print_bolet_recoj
                      (prsp_cod_proc,
                       prsp_cod_clie,
                       prsp_cod_paqu,
                       oid_bole_reco,
                       num_bole,
                       val_tota_unid,
                       fec_reco,
                       est_bole,
                       num_pagi,
                       val_tota_pagi,
                       usu_crea,
                       fec_crea,
                       ind_acti,
                       cod_tabl)
                    VALUES
                      (pscodigotransaccional,
                       boletarecojocabecerarecord(i).cod_clie,
                       lscodigopaquete,
                       boletarecojocabecerarecord(i).cod_cabe_bore,
                       boletarecojocabecerarecord(i).cod_cabe_bore,
                       vl_num_tota_unid_recl,
                       SYSDATE,
                       decode(boletarecojocabecerarecord(i).num_reco,
                              1,
                              'PRIMER ',
                              2,
                              'SEGUNDO ',
                              '-'),
                       pagina,
                       totalpaginas,
                       pscodigousuario,
                       SYSDATE,
                       '1',
                       'BRA');
                    existecabecera := TRUE;
                  END IF;
                
                  IF boletarecojolineatamanio > 0 THEN
                    IF datoenviobr = 'C' THEN
                      archivoxml2 := archivoxml2 || '<txt>' ||
                                     TRIM(to_char(boletarecojolinearecord(j)
                                                  .codigounicoventa,
                                                  '00000')) || '<t />' || boletarecojolinearecord(j)
                                    .des_cort || '<tr />' ||
                                     to_char(boletarecojolinearecord(j)
                                             .num_unid_recl) || '<t />' || boletarecojolinearecord(j)
                                    .des_oper || '<tc />' || boletarecojolinearecord(j)
                                    .cod_peri || '<t />' || boletarecojolinearecord(j)
                                    .cod_prod || '</txt>';
                    ELSE
                      archivoxml2 := archivoxml2 || '<txt>' ||
                                     TRIM(to_char(boletarecojolinearecord(j)
                                                  .codigounicoventa)) ||
                                     '<t />' || boletarecojolinearecord(j)
                                    .des_cort || '<tr />' ||
                                     to_char(boletarecojolinearecord(j)
                                             .num_unid_recl) || '<t />' || boletarecojolinearecord(j)
                                    .des_oper || '<tc />' || boletarecojolinearecord(j)
                                    .cod_peri || '<t />' || boletarecojolinearecord(j)
                                    .cod_prod || '</txt>';
                    END IF;
                    /*POR CADA PAGINA SE DEBE TENER UN TITULO*/
                    IF lnflagcambiopagina = TRUE THEN
                      --PRIMERA FILA
                      INSERT INTO imp_print_bolet_recoj_detal
                        (prsp_cod_proc,
                         prsp_cod_clie,
                         prsp_cod_paqu,
                         prbr_oid_bole_reco,
                         oid_bore_deta,
                         --cod_tabl,
                         cod_tipo_fila,
                         val_colu_01,
                         val_colu_02,
                         val_colu_03,
                         val_colu_04,
                         val_colu_05,
                         val_colu_06,
                         val_colu_07,
                         val_colu_08,
                         val_colu_09,
                         val_colu_10,
                         val_colu_11,
                         val_colu_12,
                         usu_crea,
                         fec_crea,
                         ind_acti)
                      VALUES
                        (pscodigotransaccional,
                         boletarecojolinearecord(j).cod_clie,
                         lscodigopaquete,
                         lncorrelativoboleta,
                         lncontador,
                         --'P',
                         'S',
                         decode(datoenviobr, 'C', 'CUV', 'NUM'),
                         ' ',
                         ' ',
                         'TIPO',
                         ' ',
                         ' ',
                         ' ',
                         NULL,
                         NULL,
                         NULL,
                         NULL,
                         NULL,
                         pscodigousuario,
                         SYSDATE,
                         1);
                      --SEGUNDA FILA
                      INSERT INTO imp_print_bolet_recoj_detal
                        (prsp_cod_proc,
                         prsp_cod_clie,
                         prsp_cod_paqu,
                         prbr_oid_bole_reco,
                         oid_bore_deta,
                         --COD_TABL,
                         cod_tipo_fila,
                         val_colu_01,
                         val_colu_02,
                         val_colu_03,
                         val_colu_04,
                         val_colu_05,
                         val_colu_06,
                         val_colu_07,
                         val_colu_08,
                         val_colu_09,
                         val_colu_10,
                         val_colu_11,
                         val_colu_12,
                         usu_crea,
                         fec_crea,
                         ind_acti)
                      VALUES
                        (pscodigotransaccional,
                         boletarecojolinearecord(j).cod_clie,
                         lscodigopaquete,
                         lncorrelativoboleta,
                         lncontador + 1,
                         --   'P',
                         'S',
                         decode(datoenviobr, 'C', 'PRM', 'CDR'),
                         'DESCRIPCION',
                         'CANT',
                         'OPERACION',
                         'CAMPAÑA',
                         'COD SAP',
                         decode(datoenviobr, 'C', 'OBSERVACIONES', NULL),
                         NULL,
                         NULL,
                         NULL,
                         NULL,
                         NULL,
                         pscodigousuario,
                         SYSDATE,
                         1);
                      lncontador         := lncontador + 2;
                      lnflagcambiopagina := FALSE;
                      /**/
                    END IF;
                  
                    INSERT INTO imp_print_bolet_recoj_detal
                      (prsp_cod_proc,
                       prsp_cod_clie,
                       prsp_cod_paqu,
                       prbr_oid_bole_reco,
                       oid_bore_deta,
                       --COD_TABL,
                       cod_tipo_fila,
                       val_colu_01,
                       val_colu_02,
                       val_colu_03,
                       val_colu_04,
                       val_colu_05,
                       val_colu_06,
                       val_colu_07,
                       val_colu_08,
                       val_colu_09,
                       val_colu_10,
                       val_colu_11,
                       val_colu_12,
                       usu_crea,
                       fec_crea,
                       ind_acti)
                    VALUES
                      (pscodigotransaccional,
                       boletarecojolinearecord(j).cod_clie,
                       lscodigopaquete,
                       lncorrelativoboleta,
                       lncontador,
                       -- 'P',
                       'D',
                       decode(datoenviobr,
                              'C',
                              TRIM(to_char(boletarecojolinearecord(j)
                                           .codigounicoventa,
                                           '00000')),
                              TRIM(to_char(boletarecojolinearecord(j)
                                           .codigounicoventa))),
                       boletarecojolinearecord(j).des_cort,
                       boletarecojolinearecord(j).num_unid_recl,
                       boletarecojolinearecord(j).des_oper,
                       boletarecojolinearecord(j).cod_peri,
                       boletarecojolinearecord(j).cod_prod,
                       decode(datoenviobr,
                              'C',
                              '.........................',
                              NULL),
                       NULL,
                       NULL,
                       NULL,
                       NULL,
                       NULL,
                       pscodigousuario,
                       SYSDATE,
                       1);
                    lncontador := lncontador + 1;
                  END IF;
                
                END LOOP;
              END IF;
              EXIT WHEN c_boleta_recojo_linea%NOTFOUND;
            END LOOP;
            CLOSE c_boleta_recojo_linea;
          
            IF boletarecojolineatamanio = 0 THEN
              existenpaginas := FALSE;
            ELSE
              IF actualdireccionbr = 1 THEN
                --archivoBR
                archivoxml1 := '<frmbrec><blqdata><numbrec>BR ' ||
                               to_char(vl_val_nume_bore) ||
                               '</numbrec><numrecojo>' || vl_num_reco ||
                               '</numrecojo><zona>' || vl_cod_zona ||
                               '</zona><territorio>' || vl_cod_secc ||
                               to_char(vl_cod_terr) ||
                               '</territorio><campana>' || vl_cod_peri_proc ||
                               '</campana><factura>' ||
                               to_char(vl_val_nume_bole_desp) ||
                               '</factura><femision>' ||
                               to_char(trunc(vl_fec_ingr),
                                       'YYYY-MM-DD HH24:MI:SS.') || '0' ||
                               '</femision><nombre>' || vl_nombre ||
                               '</nombre><codconsultora>' || vl_cod_clie ||
                               '</codconsultora><telefono>' || vl_telefono ||
                               '</telefono><direccion1>' || vl_dir_br1 ||
                               '</direccion1><direccion2>' || vl_des_urba ||
                               '</direccion2><direccion3>' || vl_dir_br2 ||
                               '</direccion3><totalunidades>' ||
                               to_char(vl_num_tota_unid_recl) ||
                               '</totalunidades><cpg>' || to_char(pagina) ||
                               '</cpg><tpg>' || to_char(totalpaginas) ||
                               '</tpg></blqdata><detalle>';
                archivoxml  := archivoxml1 || archivoxml2 ||
                               '</detalle></frmbrec>';
              ELSE
                archivoxml1 := '<frmbrec><blqdata><numbrec>BR ' ||
                               to_char(vl_val_nume_bore) ||
                               '</numbrec><numrecojo>' || vl_num_reco ||
                               '</numrecojo><zona>' || vl_cod_zona ||
                               '</zona><seccion>' || vl_cod_secc ||
                               '</seccion><territorio>' ||
                               to_char(vl_cod_terr) ||
                               '</territorio><campana>' || vl_cod_peri_proc ||
                               '</campana><factura>' ||
                               to_char(vl_val_nume_bole_desp) ||
                               '</factura><femision>' ||
                               to_char(trunc(vl_fec_ingr),
                                       'YYYY-MM-DD HH24:MI:SS.') || '0' ||
                               '</femision><nombre>' || vl_nombre ||
                               '</nombre><codconsultora>' || vl_cod_clie ||
                               '</codconsultora><telefono>' || vl_telefono ||
                               '</telefono><direccion1>' || vl_dir_br1 ||
                               '</direccion1><direccion2>' || vl_des_urba ||
                               '</direccion2><direccion3>' || vl_des_dist || '/' ||
                               vl_des_prov || '/' || vl_des_dpto ||
                               '</direccion3><totalunidades>' ||
                               to_char(vl_num_tota_unid_recl) ||
                               '</totalunidades><cpg>' || to_char(pagina) ||
                               '</cpg><tpg>' || to_char(totalpaginas) ||
                               '</tpg></blqdata><detalle>';
                archivoxml  := archivoxml1 || archivoxml2 ||
                               '</detalle></frmbrec>';
              END IF;
            
              INSERT INTO int_bolet_recoj_xml
                (cod_cons,
                 val_corr,
                 xml_bole_reco,
                 cod_cabe_bore)
              VALUES
                (boletarecojocabecerarecord(i).cod_clie,
                 pagina,
                 archivoxml,
                 codigocabeceraboletarecojo);
              pagina             := pagina + 1;
              rowdesde           := rowdesde + sizepaginacion;
              rowhasta           := rowhasta + sizepaginacion;
              archivoxml         := '';
              archivoxml1        := '';
              archivoxml2        := '';
              pagina             := pagina + 1;
              rowdesde           := rowdesde + sizepaginacion;
              rowhasta           := rowhasta + sizepaginacion;
              lnflagcambiopagina := TRUE;
            END IF;
          END LOOP;
        
        END IF;
      
      END LOOP;
    END IF;
  
    EXIT WHEN c_boleta_recojo_cabecera%NOTFOUND;
  END LOOP;
  CLOSE c_boleta_recojo_cabecera;

  imp_pkg_proce_compa.imp_pr_gener_xml_bolet_recoj();

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    raise_application_error(-20123,
                            'ERROR imp_pr_print_bolet_recoj: ' ||
                            ls_sqlerrm);
  
END imp_pr_print_bolet_recoj;
/***************************************************************************
Descripcion       : Procedimiemto que implementa la logica en IMP_PR_PROCE_DETAL_FACTU2_REGI
                    para su insercion en tablas cabecera y detalle: IMP_PRINT_PEDID_RESUM y
                    IMP_PRINT_PEDID_DETAL - PAIS PERU CON Y SIN PERCEPCION

Parametros        : Codigo de pais, codigo de periodo, codigo de marca, codigo de canal
Fecha Creacion    : 20/11/2015
Autor             : Gonzalo Javier Huertas Agurto
***************************************************************************/
PROCEDURE imp_pr_print_pedid
(
  p_codigopais          IN VARCHAR2,
  p_codigoperiodo       IN VARCHAR2,
  p_fechafacturacion    IN VARCHAR2,
  p_oidzona             NUMBER,
  pscodigotransaccional VARCHAR2,
  pscodigopaquete       VARCHAR2,
  pscodigousuario       VARCHAR2
) IS

  CURSOR c_consolidados
  (
    oidperiodo            NUMBER,
    indicadorenviolarissa VARCHAR2,
    numerolotefacturacion NUMBER
  ) IS
    SELECT sp.oid_pais,
           sp.cod_pais,
           mc.oid_clie,
           mc.cod_clie,
           mc.cod_digi_ctrl,
           mc.val_nom1,
           mc.val_nom2,
           mc.val_ape1,
           mc.val_ape2,
           mci.num_docu_iden,
           con.oid_soli_cabe,
           con.val_nume_soli,
           con.fec_fact,
           con.val_impo_flet_tota_loca,
           con.val_reca_flet_loca,
           con.val_impo_redo_loca,
           substr(des_pais,
                  1,
                  length(des_pais) -
                  decode(instr(des_pais, 'ESIKA'), 0, 0, 5) -
                  decode(instr(des_pais, 'LBEL'), 0, 0, 4)) des_pais,
           zon.cod_zona,
           sec.cod_secc,
           ter.cod_terr,
           sec.num_secu_fact_diar,
           mcda.ind_impr_docu imprimefactel,
           mcda.ind_impr_pdoc imprimepaqdoc,
           con.val_tota_gast_admi gastos,
           con.val_tota_gast_admi2 gastos2,
           (SELECT val_nom1 || ' ' || val_nom2
              FROM mae_clien
             WHERE oid_clie = zon.clie_oid_clie) nombregz,
           (SELECT val_ape1 || ' ' || val_ape2
              FROM mae_clien
             WHERE oid_clie = zon.clie_oid_clie) apellidosgz,
           (SELECT x.val_text_comu
              FROM mae_clien_comun x
             WHERE clie_oid_clie = zon.clie_oid_clie
               AND x.ticm_oid_tipo_comu = 6
               AND rownum = 1) celular_gz,
           (SELECT x.val_text_comu
              FROM mae_clien_comun x
             WHERE clie_oid_clie = zon.clie_oid_clie
               AND x.ticm_oid_tipo_comu = 3
               AND rownum = 1) correo_gz,
           (SELECT decode(y.val_sigl, 'RUC', y.val_sigl, 'DNI')
              FROM mae_tipo_docum y
             WHERE y.oid_tipo_docu = mci.tdoc_oid_tipo_docu
               AND rownum = 1) tipo_docum,
           con.val_impo_impu_tota_loca,
           con.val_impo_desc_flet,
           con.val_impo_desc_3_tota_loca,
           con.val_impo_desc_4_tota_loca,
           (SELECT x1.val_i18n
              FROM gen_i18n_sicc_comun x1
             WHERE x1.attr_enti = 'CAR_NIVEL_RIESG'
               AND x1.val_oid = mcda.niri_oid_nive_ries) nr,
           nvl(mc.val_recl_pend, 0) val_recl_pend
      FROM mae_clien             mc,
           mae_clien_ident       mci,
           mae_clien_datos_adici mcda,
           ped_solic_cabec       con,
           zon_zona              zon,
           zon_terri             ter,
           zon_terri_admin       zta,
           zon_secci             sec,
           ped_solic_cabec_secue sec,
           seg_pais              sp,
           bas_pais              bp
     WHERE mc.oid_clie = con.clie_oid_clie
       AND mc.oid_clie = mci.clie_oid_clie
       AND mc.oid_clie = mcda.clie_oid_clie
       AND mci.val_iden_docu_prin = 1
       AND sp.oid_pais = con.pais_oid_pais
       AND sp.cod_pais = bp.cod_pais
       AND con.zzon_oid_zona = zon.oid_zona
       AND con.terr_oid_terr = ter.oid_terr
       AND con.ztad_oid_terr_admi = zta.oid_terr_admi
       AND zta.zscc_oid_secc = sec.oid_secc
       AND con.oid_soli_cabe = sec.soca_oid_soli_cabe
       AND con.fec_fact = to_date(p_fechafacturacion, 'dd/mm/yyyy')
       AND con.perd_oid_peri = oidperiodo
       AND zon.oid_zona = p_oidzona
          --   AND con.ind_inte_lari_gene = indicadorenviolarissa
          /* AND (numerolotefacturacion IS NULL OR
          con.num_lote_fact = numerolotefacturacion)*/
       AND con.num_unid_aten_tota > 0
       AND EXISTS
     (SELECT NULL
              FROM int_lar_tipo_solici_pedido_dis l
             WHERE l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais);
  --ORDER BY MC.COD_CLIE,
  --         CON.VAL_NUME_SOLI;

  TYPE consolidadorecord IS RECORD(
    oid_pais                  seg_pais.oid_pais%TYPE,
    cod_pais                  seg_pais.cod_pais%TYPE,
    oid_clie                  mae_clien.oid_clie%TYPE,
    cod_clie                  mae_clien.cod_clie%TYPE,
    cod_digi_ctrl             mae_clien.cod_digi_ctrl%TYPE,
    val_nom1                  mae_clien.val_nom1%TYPE,
    val_nom2                  mae_clien.val_nom2%TYPE,
    val_ape1                  mae_clien.val_ape1%TYPE,
    val_ape2                  mae_clien.val_ape2%TYPE,
    num_docu_iden             mae_clien_ident.num_docu_iden%TYPE,
    oid_soli_cabe             ped_solic_cabec.oid_soli_cabe%TYPE,
    val_nume_soli             ped_solic_cabec.val_nume_soli%TYPE,
    fec_fact                  ped_solic_cabec.fec_fact%TYPE,
    val_impo_flet_tota_loca   ped_solic_cabec.val_impo_flet_tota_loca%TYPE,
    val_reca_flet_loca        ped_solic_cabec.val_reca_flet_loca%TYPE,
    val_impo_redo_loca        ped_solic_cabec.val_impo_redo_loca%TYPE,
    des_pais                  bas_pais.des_pais%TYPE,
    cod_zona                  zon_zona.cod_zona%TYPE,
    cod_secc                  zon_secci.cod_secc%TYPE,
    cod_terr                  zon_terri.cod_terr%TYPE,
    num_secu_fact_diar        ped_solic_cabec_secue.num_secu_fact_diar%TYPE,
    imprimefactel             mae_clien_datos_adici.ind_impr_docu%TYPE,
    imprimepaqdoc             mae_clien_datos_adici.ind_impr_pdoc%TYPE,
    gastos                    ped_solic_cabec.val_tota_gast_admi%TYPE,
    gastos2                   ped_solic_cabec.val_tota_gast_admi2%TYPE,
    nombresgz                 VARCHAR2(100),
    apellidosgz               VARCHAR2(100),
    celulargz                 VARCHAR2(100),
    correogz                  VARCHAR2(100),
    tipo_docum                VARCHAR2(100),
    val_impo_impu_tota_loca   ped_solic_cabec.val_impo_impu_tota_loca%TYPE,
    val_impo_desc_flet        ped_solic_cabec.val_impo_desc_flet%TYPE,
    val_impo_desc_3_tota_loca ped_solic_cabec.val_impo_desc_3_tota_loca%TYPE,
    val_impo_desc_4_tota_loca ped_solic_cabec.val_impo_desc_4_tota_loca%TYPE,
    nr                        gen_i18n_sicc_comun.val_i18n%TYPE,
    val_recl_pend             mae_clien.val_recl_pend%TYPE);

  TYPE consolidadotype IS TABLE OF consolidadorecord;
  r_consolidado consolidadotype;

  CURSOR c_detalle
  (
    oidconsolidado     NUMBER,
    v_despremioagotado VARCHAR2,
    v_desagotado       VARCHAR2,
    v_desfaltliq       VARCHAR2,
    v_desfaltanun      VARCHAR2,
    v_desanulmotmax    VARCHAR2,
    v_despremio        VARCHAR2,
    v_desatrec         VARCHAR2,
    v_desrecup         VARCHAR2,
    v_desreemp         VARCHAR2,
    v_desgratis        VARCHAR2,
    v_despremiolet     VARCHAR2,
    v_desrecupsemana   VARCHAR2,
    v_desofertnavid    VARCHAR2,
    v_oidestra         NUMBER,
    v_desrecuprol      VARCHAR2,
    v_desrecuprol2     VARCHAR2
  ) IS
    SELECT psc.oid_soli_cabe,
           psc.copa_oid_para_gene,
           psc.ictp_oid_tipo_prog,
           psp.oid_soli_posi,
           nvl(nvl(psp.val_codi_vent,
                   lpad('0', 4 - length(psp.val_codi_vent_fict), '0') ||
                   psp.val_codi_vent_fict),
               '00000') AS val_codi_vent,
           --(SELECT VAL_I18N FROM GEN_I18N_SICC_PAIS WHERE ATTR_ENTI = 'MAE_PRODU' AND IDIO_OID_IDIO = 1 AND VAL_OID = PSP.PROD_OID_PROD) DES_PROD,
           imp_pkg_proce_laser.imp_fn_desc_produ(p_codigopais,
                                                 psp.prod_oid_prod) des_prod,
           psp.num_unid_dema_real,
           psp.num_unid_aten,
           psp.val_prec_cata_unit_loca,
           psp.val_prec_cata_unit_loca * psp.num_unid_aten val_prec_cata_tota_loca,
           psp.val_prec_cont_unit_loca,
           psp.val_prec_cont_unit_loca * psp.num_unid_aten val_prec_cont_tota_loca,
           psp.val_prec_fact_tota_loca,
           psp.val_impo_desc_tota_loca,
           nvl(psp.val_porc_desc, 0) val_porc_desc,
           psp.val_impo_impu_tota_loca,
           pod.imp_prec_publ,
           psp.fopa_oid_form_pago,
           decode(psc.modu_oid_modu, 15, 3, nvl(pto.num_secc_deta_fact, 2)) ind_grup,
           psp.val_codi_orig,
           psp.num_unid_orig,
           psp.oid_nive_ofer,
           CASE
             WHEN decode(mp.val_atri_3,
                         '1',
                         'C',
                         nvl(psp.ind_dent_fuer_caja_bols, 'F')) = 'C' THEN
              '0'
             ELSE
              '1'
           END ind_dent_fuer_caja_bols,
           CASE
             WHEN EXISTS (SELECT 1
                     FROM pre_ofert x
                    WHERE x.oid_ofer = pod.ofer_oid_ofer
                      AND x.coes_oid_estr = v_oidestra) THEN
              v_desofertnavid
             WHEN EXISTS (SELECT 1
                     FROM pre_recup_seman x
                    WHERE x.cod_peri = p_codigoperiodo
                      AND x.cod_vent = psp.val_codi_vent) AND
                  (nvl(psp.num_unid_dema_real, 0) -
                  nvl(psp.num_unid_aten, 0) > 0) THEN
              v_desrecupsemana
             WHEN (nvl(psp.num_unid_dema_real, 0) -
                  nvl(psp.num_unid_aten, 0) > 0) AND
                  (psc.copa_oid_para_gene IS NOT NULL OR
                  psc.ictp_oid_tipo_prog IS NOT NULL) THEN
              v_despremioagotado
             WHEN nvl(psp.num_unid_dema_real, 0) - nvl(psp.num_unid_aten, 0) > 0 THEN
              v_desagotado
             WHEN nvl(psp.num_unid_dema_real, 0) = 0 AND
                  nvl(psp.num_unid_aten, 0) = 0 AND psp.ind_limi_vent = 1 AND
                  (pto.cod_tipo_ofer = '21' OR pto.cod_tipo_ofer = '23') THEN
              v_desfaltliq
             WHEN nvl(psp.num_unid_dema_real, 0) = 0 AND
                  nvl(psp.num_unid_aten, 0) = 0 AND psp.ind_limi_vent = 1 THEN
              v_desfaltanun
             WHEN nvl(psp.num_unid_dema_real, 0) = 0 AND
                  nvl(psp.num_unid_aten, 0) = 0 AND
                  psp.espo_oid_esta_posi = 2 AND psp.stpo_oid_subt_posi = 21 THEN
              v_desanulmotmax
           /*WHEN NVL(PSP.NUM_UNID_DEMA_REAL, 0) = 0 AND NVL(PSP.NUM_UNID_ATEN, 0) = 0
            AND PSP.ESPO_OID_ESTA_POSI = 2
           THEN 'Vta.Exc'*/
             WHEN pst.cod_subt_posi IS NOT NULL AND pst.cod_subt_posi = 'RD' THEN
              v_desagotado
           /*WHEN NVL(PSP.NUM_UNID_DEMA_REAL, 0) = 0 AND NVL(PSP.NUM_UNID_ATEN, 0) = 0
           THEN 'No.Aplica'*/
             WHEN (psc.copa_oid_para_gene IS NOT NULL OR
                  psc.ictp_oid_tipo_prog IS NOT NULL) THEN
              v_despremio
             WHEN psc.modu_oid_modu = '15' THEN
              v_desatrec
             WHEN ptp.cod_tipo_posi IS NOT NULL AND ptp.cod_tipo_posi = 'RE' THEN
              v_desrecup
             WHEN ptp.cod_tipo_posi IS NOT NULL AND ptp.cod_tipo_posi = 'DA' THEN
              ''
             WHEN pst.cod_subt_posi IS NOT NULL AND pst.cod_subt_posi = 'RZ' THEN
              v_desreemp ||
              (SELECT DISTINCT pof.val_codi_vent
                 FROM pre_matri_factu pmf,
                      pre_matri_reemp pmr,
                      pre_matri_factu pmf2,
                      pre_ofert_detal pof
                WHERE pmf.oid_matr_fact = pmr.mafa_oid_cod_reem
                  AND pmf.ofde_oid_deta_ofer = psp.ofde_oid_deta_ofer
                  AND pmr.mafa_oid_cod_ppal = pmf2.oid_matr_fact
                  AND pmf2.ofde_oid_deta_ofer = pof.oid_deta_ofer
                  AND EXISTS
                (SELECT 1
                         FROM ped_solic_posic
                        WHERE soca_oid_soli_cabe = psc.oid_soli_cabe
                          AND ofde_oid_deta_ofer = psp.ofde_oid_deta_ofer)
                  AND rownum = 1)
             WHEN psp.val_prec_cata_unit_loca = 0 THEN
              v_desgratis
             WHEN ts.cod_tipo_soli = 'IPLC' THEN
              v_despremiolet
             WHEN psp.ind_recu_prol = '1' THEN
              v_desrecuprol
             WHEN psp.ind_recu_prol = '2' THEN
              v_desrecuprol2
             ELSE
              ''
           END AS val_obse,
           mp.cod_sap,
           psp.num_unid_por_aten
      FROM ped_solic_cabec     psc,
           ped_solic_posic     psp,
           pre_ofert_detal     pod,
           pre_tipo_ofert      pto,
           ped_tipo_solic_pais tsp,
           ped_tipo_solic      ts,
           ped_tipo_posic      ptp,
           ped_subti_posic     pst,
           mae_produ           mp
     WHERE psc.oid_soli_cabe = psp.soca_oid_soli_cabe
       AND psc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
       AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
       AND psp.prod_oid_prod = mp.oid_prod
          --AND TS.CLSO_OID_CLAS_SOLI = PCS.OID_CLAS_SOLI
       AND psp.tpos_oid_tipo_posi = ptp.oid_tipo_posi(+)
       AND psp.stpo_oid_subt_posi = pst.oid_subt_posi(+)
       AND psp.ofde_oid_deta_ofer = pod.oid_deta_ofer(+)
       AND pod.tofe_oid_tipo_ofer = pto.oid_tipo_ofer(+)
       AND psp.espo_oid_esta_posi != 2
       AND psc.soca_oid_soli_cabe = oidconsolidado
    --)
    --WHERE IND_GRUP = indicadorGrupo
     ORDER BY oid_nive_ofer,
              val_codi_orig,
              val_codi_vent;

  TYPE detallerecord IS RECORD(
    oid_soli_cabe           ped_solic_cabec.oid_soli_cabe%TYPE,
    copa_oid_para_gene      ped_solic_cabec.copa_oid_para_gene%TYPE,
    ictp_oid_tipo_prog      ped_solic_cabec.ictp_oid_tipo_prog%TYPE,
    oid_soli_posi           ped_solic_posic.oid_soli_posi%TYPE,
    val_codi_vent           VARCHAR2(15),
    des_prod                gen_i18n_sicc_pais.val_i18n%TYPE,
    num_unid_dema_real      ped_solic_posic.num_unid_dema_real%TYPE,
    num_unid_aten           ped_solic_posic.num_unid_aten%TYPE,
    val_prec_cata_unit_loca ped_solic_posic.val_prec_cata_unit_loca%TYPE,
    val_prec_cata_tota_loca ped_solic_posic.val_prec_cata_tota_loca%TYPE,
    val_prec_cont_unit_loca ped_solic_posic.val_prec_cont_unit_loca%TYPE,
    val_prec_cont_tota_loca ped_solic_posic.val_prec_cont_tota_loca%TYPE,
    val_prec_fact_tota_loca ped_solic_posic.val_prec_fact_tota_loca%TYPE,
    val_impo_desc_tota_loca ped_solic_posic.val_impo_desc_tota_loca%TYPE,
    val_porc_desc           ped_solic_posic.val_porc_desc%TYPE,
    val_impo_impu_tota_loca ped_solic_posic.val_impo_impu_tota_loca%TYPE,
    imp_prec_publ           pre_ofert_detal.imp_prec_publ%TYPE,
    fopa_oid_form_pago      ped_solic_posic.fopa_oid_form_pago%TYPE,
    ind_grupo               pre_tipo_ofert.num_secc_deta_fact%TYPE,
    val_codi_orig           ped_solic_posic.val_codi_orig%TYPE,
    num_unid_orig           ped_solic_posic.num_unid_orig%TYPE,
    oid_nive_ofer           ped_solic_posic.oid_nive_ofer%TYPE,
    ind_dent_fuer_caja_bols ped_solic_posic.ind_dent_fuer_caja_bols%TYPE,
    val_obse                VARCHAR2(1000),
    cod_sap                 mae_produ.cod_sap%TYPE,
    num_unid_por_aten       ped_solic_posic.num_unid_por_aten%TYPE);

  TYPE detalletype IS TABLE OF detallerecord;
  r_detalle detalletype;

  -- Variables locales
  l_oidpais             NUMBER;
  l_oidperiodo          NUMBER;
  l_oidcanal            NUMBER;
  l_oidmarca            NUMBER;
  l_correlativo         NUMBER := 1;
  l_contadordetalles    NUMBER := 0;
  l_porcdscto           NUMBER;
  l_preciounitario      NUMBER(12, 2) := 0;
  l_preciototal         NUMBER(12, 2) := 0;
  l_descuento           NUMBER(12, 2) := 0;
  l_descuento2          NUMBER(12, 2) := 0;
  l_dif_desc            NUMBER(12, 2) := 0;
  l_preciofacturado     NUMBER(12, 2) := 0;
  l_pagoposterior       NUMBER(12, 2) := 0;
  l_preciocatalogo      NUMBER(12, 2) := 0;
  l_preciocatalogototal NUMBER(12, 2) := 0;
  l_oportunidadahorro   NUMBER(12, 2) := 0;

  l_subtotalsolicitado          NUMBER := 0;
  l_subtotalatendido            NUMBER := 0;
  l_subtotalcatalogo            NUMBER(12, 2) := 0;
  l_subtotaldescuentos          NUMBER(12, 2) := 0;
  l_subtotalfacturado           NUMBER(12, 2) := 0;
  l_subtotalpreciocatalogo      NUMBER(12, 2) := 0;
  l_subtotalpreciocatalogototal NUMBER(12, 2) := 0;
  l_subtotaloportunidadahorro   NUMBER(12, 2) := 0;

  l_totalsolicitado          NUMBER := 0;
  l_totalatendido            NUMBER := 0;
  l_totalcatalogo            NUMBER(12, 2) := 0;
  l_totaldescuentos          NUMBER(12, 2) := 0;
  l_totaldescuentos2         NUMBER(12, 2) := 0;
  l_totalfacturado           NUMBER(12, 2) := 0;
  l_totalpreciocatalogo      NUMBER(12, 2) := 0;
  l_totalpreciocatalogototal NUMBER(12, 2) := 0;
  l_totaloportunidadahorro   NUMBER(12, 2) := 0;
  l_totalimpuestosgratis     NUMBER(12, 2) := 0;
  l_saldofavor               NUMBER(12, 2) := 0;
  l_saldoanterior            NUMBER(12, 2) := 0;
  l_saldoflexanterior        NUMBER(12, 2) := 0;
  l_interesflexipago         NUMBER(12, 2) := 0;
  l_percepcion               NUMBER(12, 2) := 0;
  l_totalfactura             NUMBER(12, 2) := 0;
  l_cargofamsegura           NUMBER(12, 2) := 0;
  l_totalapagar              NUMBER(12, 2) := 0;

  l_oportunidadcatalogo NUMBER(12, 2) := 0;
  l_oportunidadrevista  NUMBER(12, 2) := 0;

  l_totalcatalogo2 NUMBER(12, 2) := 0;
  l_totalmav       NUMBER(12, 2) := 0;

  l_indicadorenviolarissa    VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                           'indicadorEnvioLarissa');
  l_indicadorenvioultimolote VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                           'indicadorEnvioUltimoLote');
  l_indicadorcargofamsegura  VARCHAR2(100) := nvl(imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                               'indicadorCargoFamSegura'),
                                                  'N');
  l_indicadorimpuestosgratis VARCHAR2(1) := 'N';
  l_totalpagoposterior       NUMBER(12, 2) := 0;
  l_indicadorpercepcion      VARCHAR2(1) := 'N';
  l_numerolotefacturacion    NUMBER;
  l_clob                     CLOB;
  l_textoactual              VARCHAR2(20000) := '';
  l_codigoperiodosiguiente   VARCHAR2(6);
  l_cambiolinearetornocarro  VARCHAR2(2) := chr(13) || chr(10);
  l_formatonumerico          VARCHAR2(100) := nvl(imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                               'formatoNumerico'),
                                                  '9999999G990D00');
  l_tasaimpuestopercepcion   NUMBER(5, 3);
  --nuevo parametro
  l_indicadorgenerardetallefact2 VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                               'indicadorGenerarDetalleFactura2');

  l_nombreseccion1 VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                 'nombreSeccion1');
  l_nombreseccion2 VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                 'nombreSeccion2');
  l_nombreseccion3 VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                 'nombreSeccion3');
  l_nombreseccion4 VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                 'nombreSeccion4');
  l_nombreseccion  VARCHAR2(100) := '';

  l_desrecupsemana   VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                   'desRecupSemana');
  l_despremioagotado VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                   'desPremioAgotado');
  l_desagotado       VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                   'desAgotado');
  l_desfaltliq       VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                   'desFaltLiq');
  l_desfaltanun      VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                   'desFaltAnun');
  l_desanulmotmax    VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                   'desAnulMotMax');
  l_despremio        VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                   'desPremio');
  l_desatrec         VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                   'desAtRec');
  l_desrecup         VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                   'desRecup');
  l_desreemp         VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                   'desReemp');
  l_desgratis        VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                   'desGratis');
  l_despremiolet     VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                   'desPremioLET');
  l_desofertnavid    VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                   'desOfertNavid');
  l_desrecuprol      VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                   'desRecuPROL');
  l_desrecuprol2     VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                   'desRecuPROL2');

  l_mensajeoportunidadahorro VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                           'mensajeOportunidadAhorro');

  l_fechavencimiento VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                   'mensajeOportunidadAhorro');

  l_flagtitulo1 NUMBER := 0;
  l_flagtitulo2 NUMBER := 0;
  l_flagtitulo3 NUMBER := 0;
  l_flagtitulo4 NUMBER := 0;

  l_cantidadoc NUMBER := 0;

  lv_indiejec VARCHAR2(10) := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(p_codigopais,
                                                                       'STO_GASTO_ADMIN'),
                                  'N');

  lv_indiejec2 VARCHAR2(10) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                            'impuestoEstatal');

  lv_reemplazoocs VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                'indicadorReemplazoOCS');

  lv_actividadconf VARCHAR2(100) := nvl(imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                     'actividadConf'),
                                        'CV');

  lv_actividaddesp VARCHAR2(100) := nvl(imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                     'actividadDesp'),
                                        'DP');

  lv_imprimepaqdoc VARCHAR2(100) := nvl(imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                     'imprimepaqdoc'),
                                        'N');

  lv_imprimefactel VARCHAR2(100) := nvl(imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                     'imprimefactel'),
                                        'N');

  lv_imprimenuevos VARCHAR2(100) := nvl(imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                     'imprimenuevos'),
                                        'N');

  lv_redondeodesc VARCHAR2(100) := nvl(imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                    'redondeoDesc'),
                                       'S');

  ln_oidestra NUMBER(10) := sto_pkg_gener.sto_fn_obten_param_ocr(p_codigopais,
                                                                 'STO_ESTRA_NAVID');

  ln_diasdesp  NUMBER(10) := 0;
  ln_diasdesp2 NUMBER(10) := 0;
  ln_diasdesp3 NUMBER(10) := 0;

  l_fechacv         VARCHAR2(100) := '';
  l_fechadespacho   VARCHAR2(100) := '';
  l_fechadespacho_2 VARCHAR2(100) := '';
  l_fechadespacho_3 VARCHAR2(100) := '';
  l_fechadespacho2  VARCHAR2(100) := nvl(imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                      'indFechaDesp2'),
                                         'N');
  l_fechasigfact    VARCHAR2(100) := '';
  l_fechasigfact2   VARCHAR2(100) := '';
  l_fechasigfact3   VARCHAR2(100) := '';

  lsactividadparametro VARCHAR2(100) := sto_pkg_gener.sto_fn_obten_param_ocr(p_codigopais,
                                                                             'STO_ACTIV_RECAR_FLET');
  lscodperigastos      VARCHAR2(10);

  ln_desc_flete VARCHAR2(2) := nvl(imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                'descFlete'),
                                   'N');

  ln_desc_flete2 VARCHAR2(2) := nvl(imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                 'descFlete2'),
                                    'N');

  orig_ante VARCHAR2(100) := '';

  lscodigoventa        VARCHAR2(15);
  lsunidadesdemandadas NUMBER(12);
  lsunidadesatendidas  NUMBER(6);
  lsobservacion        VARCHAR2(1000);
  lsoidcabecera        NUMBER(12);

  lstotalcatalogo                NUMBER(12, 2) := 0;
  lstotaldescuentos              NUMBER(12, 2) := 0;
  lstotalfacturado               NUMBER(12, 2) := 0;
  lstotalflete                   NUMBER(12, 2) := 0;
  lstotalpercepciones            NUMBER(12, 2) := 0;
  lstotalfactura                 NUMBER(12, 2) := 0;
  lspagosposteriores             NUMBER(12, 2) := 0;
  lsabonoatencionservicios       NUMBER(12, 2) := 0;
  lssaldoanterior                NUMBER(12, 2) := 0;
  lsimporteactual                NUMBER(12, 2) := 0;
  lsoportunidadcatalogo          NUMBER(12, 2) := 0;
  lsoportunidadrevista           NUMBER(12, 2) := 0;
  lsoportunidadtotal             NUMBER(12, 2) := 0;
  lsmensaje                      VARCHAR2(100);
  lsfechavencimiento             VARCHAR2(60);
  lsgastosadministrativos        NUMBER(12, 2) := 0;
  lssaldoflexipago               NUMBER(12, 2) := 0;
  lscuotaflexipago               NUMBER(12, 2) := 0;
  lsrecargoflete                 NUMBER(12, 2) := 0;
  lsdescuentocabecera            NUMBER(12, 2) := 0;
  lscampanagastosadministrativos VARCHAR2(10);
  lsdescuentocabecera2           NUMBER(12, 2) := 0;
  lsreclamospendientes           NUMBER(12, 2) := 0;

  lsimpuesto              NUMBER(12, 2) := 0;
  lssubtotalflete         NUMBER(12, 2) := 0;
  lssubtotalfleteimpuesto NUMBER(12, 2) := 0;
  lsdescuentoflete        NUMBER(12, 2) := 0;
  lstotalimpuestosgratis  NUMBER(12, 2) := 0;

  lstipofila VARCHAR2(2) := '';
  lstiporegi VARCHAR2(2);

  lnnumorden NUMBER := 0;

BEGIN

  -- Obtenemos el OID del periodo
  l_oidpais                := gen_pkg_gener.gen_fn_devuelve_id_pais(p_codigopais);
  l_oidcanal               := gen_pkg_gener.gen_fn_devuelve_id_canal(codigo_canal);
  l_oidmarca               := gen_pkg_gener.gen_fn_devuelve_id_marca(codigo_marca);
  l_oidperiodo             := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(p_codigoperiodo,
                                                                         l_oidmarca,
                                                                         l_oidcanal);
  l_codigoperiodosiguiente := gen_fn_calcu_perio(p_codigoperiodo, 1);

  --SOLO SE JECUTARA EL PROCESO SI PARAMETRO ES S, EN OTRO CASO NO SE EJECUTARA
  IF (l_indicadorenvioultimolote = '1' OR l_indicadorenvioultimolote = 'S') THEN
  
    BEGIN
      SELECT MAX(cons.num_lote_fact)
        INTO l_numerolotefacturacion
        FROM ped_solic_cabec                cons,
             int_lar_tipo_solici_pedido_dis tspd
       WHERE cons.perd_oid_peri = l_oidperiodo
         AND cons.fec_fact = to_date(p_fechafacturacion, 'dd/mm/yyyy')
         AND cons.ind_ts_no_conso = 0
         AND (cons.ind_pedi_prue = 0 OR cons.ind_pedi_prue IS NULL)
         AND cons.tspa_oid_tipo_soli_pais = tspd.tspa_oid_tipo_soli_pais
         AND cons.pais_oid_pais = l_oidpais;
    EXCEPTION
      WHEN OTHERS THEN
        l_numerolotefacturacion := NULL;
    END;
  
  END IF;

  -- Obtenemos el valor del indicador de impuestos a los productos gratis
  SELECT decode(COUNT(*), 0, 'N', 'S')
    INTO l_indicadorimpuestosgratis
    FROM seg_pais             p,
         seg_param_inter_pais i
   WHERE p.oid_pais = i.pais_oid_pais
     AND i.ind_impu_prod_grat = 1
     AND p.cod_pais = p_codigopais;

  -- Obtenemos el valor del indicador de percepciones
  SELECT decode(COUNT(*), 0, 'N', 'S')
    INTO l_indicadorpercepcion
    FROM fac_formu_perce ffp
   WHERE ffp.pais_oid_pais = l_oidpais;

  -- Obtenemos el valor de la tasa de impuesto por percepciones
  IF l_indicadorpercepcion = 'S' THEN
    BEGIN
      SELECT pti.val_tasa_impu
        INTO l_tasaimpuestopercepcion
        FROM ped_tasa_impue pti
       WHERE pti.pais_oid_pais = l_oidpais
         AND pti.val_indi_impu = 'PER'; -- Codigo Percepciones
    
    EXCEPTION
      WHEN no_data_found THEN
      
        l_tasaimpuestopercepcion := 0;
      
    END;
  END IF;

  -- Abrimos el cursor principal
  OPEN c_consolidados(l_oidperiodo,
                      l_indicadorenviolarissa,
                      l_numerolotefacturacion /*, p_oidZona*/);
  LOOP
    FETCH c_consolidados BULK COLLECT
      INTO r_consolidado LIMIT w_filas;
  
    IF r_consolidado.count > 0 THEN
      FOR i IN r_consolidado.first .. r_consolidado.last
      LOOP
      
        l_flagtitulo1 := 0;
        l_flagtitulo2 := 0;
        l_flagtitulo3 := 0;
        l_flagtitulo4 := 0;
        -- Insertamos el registro y obtenemos la referencia al CLOB
        INSERT INTO imp_paque_docum_detal_factu
          (cor_pado_defa,
           cod_cons,
           val_nume_soli,
           xml_deta_fact)
        VALUES
          (r_consolidado(i).oid_soli_cabe,
           r_consolidado(i).cod_clie,
           r_consolidado(i).val_nume_soli,
           empty_clob())
        RETURNING xml_deta_fact INTO l_clob;
      
        -- Inicio Detalle de Factura
        l_textoactual := '<detfac2>';
      
        -- Inicio Cabecera
        l_textoactual := l_textoactual || '<blqcab>';
      
        lsoidcabecera := imp_prpr_seq.nextval;
      
        IF lv_imprimefactel = 'S' THEN
          IF r_consolidado(i).tipo_docum <> 'RUC' THEN
            r_consolidado(i).imprimefactel := '1';
          END IF;
        
        END IF;
      
        -- Ind. Impresion FE
        l_textoactual := l_textoactual || '<imprimefactel>' || r_consolidado(i)
                        .imprimefactel || '</imprimefactel>';
      
        IF lv_imprimepaqdoc = 'S' THEN
          -- Ind. Impresion
          l_textoactual := l_textoactual || '<imprimepaqdoc>' || r_consolidado(i)
                          .imprimepaqdoc || '</imprimepaqdoc>';
        
        END IF;
      
        IF lv_imprimenuevos = 'S' THEN
        
          BEGIN
            SELECT to_char(cc.fec_inic, 'dd/mm/yyyy'),
                   to_char(cc.fec_inic, 'dd/mm/yyyy'),
                   to_char(cc.fec_inic, 'dd/mm/yyyy')
              INTO l_fechadespacho,
                   l_fechadespacho_2,
                   l_fechadespacho_3
              FROM cra_crono       cc,
                   cra_perio       cp,
                   seg_perio_corpo spc,
                   cra_activ       act
             WHERE cc.perd_oid_peri = cp.oid_peri
               AND cp.peri_oid_peri = spc.oid_peri
               AND spc.cod_peri = l_codigoperiodosiguiente
               AND cc.zzon_oid_zona =
                   (SELECT oid_zona
                      FROM zon_zona
                     WHERE cod_zona = r_consolidado(i).cod_zona)
                  
               AND cc.cact_oid_acti = act.oid_acti
               AND act.cod_acti = lv_actividaddesp;
          EXCEPTION
            WHEN OTHERS THEN
              l_fechadespacho   := trunc(SYSDATE);
              l_fechadespacho_2 := trunc(SYSDATE);
              l_fechadespacho_3 := trunc(SYSDATE);
          END;
        
          BEGIN
            SELECT to_char(cc.fec_inic, 'dd/mm/yyyy')
              INTO l_fechasigfact
              FROM cra_crono       cc,
                   cra_perio       cp,
                   seg_perio_corpo spc,
                   cra_activ       act
             WHERE cc.perd_oid_peri = cp.oid_peri
               AND cp.peri_oid_peri = spc.oid_peri
               AND spc.cod_peri = l_codigoperiodosiguiente
               AND cc.zzon_oid_zona =
                   (SELECT oid_zona
                      FROM zon_zona
                     WHERE cod_zona = r_consolidado(i).cod_zona)
                  
               AND cc.cact_oid_acti = act.oid_acti
               AND act.cod_acti = 'FA';
          EXCEPTION
            WHEN OTHERS THEN
              l_fechasigfact := trunc(SYSDATE);
          END;
        
          BEGIN
            SELECT to_char(cc.fec_inic, 'dd/mm/yyyy')
              INTO l_fechacv
              FROM cra_crono       cc,
                   cra_perio       cp,
                   seg_perio_corpo spc,
                   cra_activ       act
             WHERE cc.perd_oid_peri = cp.oid_peri
               AND cp.peri_oid_peri = spc.oid_peri
               AND spc.cod_peri = l_codigoperiodosiguiente
               AND cc.zzon_oid_zona =
                   (SELECT oid_zona
                      FROM zon_zona
                     WHERE cod_zona = r_consolidado(i).cod_zona)
               AND cc.cact_oid_acti = act.oid_acti
               AND act.cod_acti = lv_actividadconf;
          EXCEPTION
            WHEN OTHERS THEN
              l_fechacv := trunc(SYSDATE);
          END;
        
          l_fechasigfact2 := to_char(fac_pkg_proc.ped_fn_dev_dia_fact(l_codigoperiodosiguiente,
                                                                      r_consolidado(i)
                                                                      .cod_zona,
                                                                      2),
                                     'dd/mm/yyyy');
        
          l_fechasigfact3 := to_char(fac_pkg_proc.ped_fn_dev_dia_fact(l_codigoperiodosiguiente,
                                                                      r_consolidado(i)
                                                                      .cod_zona,
                                                                      3),
                                     'dd/mm/yyyy');
        
          ln_diasdesp  := fac_pkg_proc.ped_fn_obt_dias_fecha_ent(r_consolidado(i)
                                                                 .oid_soli_cabe,
                                                                 1,
                                                                 l_codigoperiodosiguiente,
                                                                 to_date(l_fechasigfact,
                                                                         'dd/mm/yyyy'));
          ln_diasdesp2 := fac_pkg_proc.ped_fn_obt_dias_fecha_ent(r_consolidado(i)
                                                                 .oid_soli_cabe,
                                                                 2,
                                                                 l_codigoperiodosiguiente,
                                                                 to_date(l_fechasigfact,
                                                                         'dd/mm/yyyy'));
          ln_diasdesp3 := fac_pkg_proc.ped_fn_obt_dias_fecha_ent(r_consolidado(i)
                                                                 .oid_soli_cabe,
                                                                 3,
                                                                 l_codigoperiodosiguiente,
                                                                 to_date(l_fechasigfact,
                                                                         'dd/mm/yyyy'));
        
          IF l_fechadespacho2 = 'S' AND ln_diasdesp > 0 THEN
            l_fechadespacho := to_char(to_date(l_fechasigfact, 'dd/mm/yyyy') +
                                       ln_diasdesp,
                                       'dd/mm/yyyy');
          END IF;
        
          IF l_fechadespacho2 = 'S' AND ln_diasdesp > 0 THEN
            l_fechadespacho_2 := to_char(to_date(l_fechasigfact2,
                                                 'dd/mm/yyyy') +
                                         ln_diasdesp2,
                                         'dd/mm/yyyy');
          END IF;
        
          IF l_fechadespacho2 = 'S' AND ln_diasdesp > 0 THEN
            l_fechadespacho_3 := to_char(to_date(l_fechasigfact3,
                                                 'dd/mm/yyyy') +
                                         ln_diasdesp3,
                                         'dd/mm/yyyy');
          END IF;
        
          -- Nombre Consultora
          l_textoactual := l_textoactual || '<nombres>' || r_consolidado(i)
                          .val_nom1 || ' ' || r_consolidado(i).val_nom2 ||
                           '</nombres>';
          l_textoactual := l_textoactual || '<apellidos>' || r_consolidado(i)
                          .val_ape1 || ' ' || r_consolidado(i).val_ape2 ||
                           '</apellidos>';
          l_textoactual := l_textoactual || '<nombresGZ>' || r_consolidado(i)
                          .nombresgz || '</nombresGZ>';
          l_textoactual := l_textoactual || '<apellidosGZ>' || r_consolidado(i)
                          .apellidosgz || '</apellidosGZ>';
          l_textoactual := l_textoactual || '<celularGZ>' || r_consolidado(i)
                          .celulargz || '</celularGZ>';
          l_textoactual := l_textoactual || '<correoGZ>' || r_consolidado(i)
                          .correogz || '</correoGZ>';
          l_textoactual := l_textoactual || '<fechaCV>' || l_fechacv ||
                           '</fechaCV>';
          l_textoactual := l_textoactual || '<fechaReparto>' ||
                           l_fechadespacho || '</fechaReparto>';
          l_textoactual := l_textoactual || '<fechaReparto2>' ||
                           l_fechadespacho_2 || '</fechaReparto2>';
          l_textoactual := l_textoactual || '<fechaReparto3>' ||
                           l_fechadespacho_3 || '</fechaReparto3>';
          l_textoactual := l_textoactual || '<fechaSigFact>' ||
                           l_fechasigfact || '</fechaSigFact>';
          l_textoactual := l_textoactual || '<fechaSigFact2>' ||
                           l_fechasigfact2 || '</fechaSigFact2>';
          l_textoactual := l_textoactual || '<fechaSigFact3>' ||
                           l_fechasigfact3 || '</fechaSigFact3>';
          l_textoactual := l_textoactual || '<diasDesp>' || ln_diasdesp ||
                           '</diasDesp>';
          l_textoactual := l_textoactual || '<tipo_docum>' || r_consolidado(i)
                          .tipo_docum || '</tipo_docum>';
          l_textoactual := l_textoactual ||
                           imp_fn_xml_opor_ahor(p_codigoperiodo,
                                                l_oidperiodo,
                                                r_consolidado(i).oid_clie);
          l_textoactual := l_textoactual || '<nriesgo>' || r_consolidado(i).nr ||
                           '</nriesgo>';
        END IF;
      
        -- Numero Pedido
        l_textoactual := l_textoactual || '<numpedido>' || r_consolidado(i)
                        .val_nume_soli || '</numpedido>';
        --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
      
        -- Descripcion Pais
        l_textoactual := l_textoactual || '<lugar>' || r_consolidado(i)
                        .des_pais || '</lugar>';
        --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
      
        -- Dia
        l_textoactual := l_textoactual || '<dia>' ||
                         to_char(r_consolidado(i).fec_fact, 'dd') ||
                         '</dia>';
        --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
      
        -- Mes
        l_textoactual := l_textoactual || '<mes>' ||
                         to_char(r_consolidado(i).fec_fact, 'mm') ||
                         '</mes>';
        --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
      
        -- A?o
        l_textoactual := l_textoactual || '<ano>' ||
                         to_char(r_consolidado(i).fec_fact, 'yyyy') ||
                         '</ano>';
        --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
      
        -- Codigo Cliente
        l_textoactual := l_textoactual || '<codconsultora>' || r_consolidado(i)
                        .cod_clie || '</codconsultora>';
        --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
      
        -- Territorio Cliente
        l_textoactual := l_textoactual || '<territorio>';
        l_textoactual := l_textoactual || r_consolidado(i).cod_zona || '-';
        l_textoactual := l_textoactual || r_consolidado(i).cod_secc || '-';
        l_textoactual := l_textoactual || r_consolidado(i).cod_terr || '-';
        l_textoactual := l_textoactual || r_consolidado(i)
                        .num_secu_fact_diar;
        l_textoactual := l_textoactual || '</territorio>';
        --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
      
        -- Documento de Identidad
        l_textoactual := l_textoactual || '<rifci>' || r_consolidado(i)
                        .num_docu_iden || '</rifci>';
        --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
      
        -- Nombre Cliente
        l_textoactual := l_textoactual || '<nombre>';
        l_textoactual := l_textoactual || r_consolidado(i).val_nom1 || ' ';
        l_textoactual := l_textoactual || r_consolidado(i).val_nom2 || ' ';
        l_textoactual := l_textoactual || r_consolidado(i).val_ape1 || ' ';
        l_textoactual := l_textoactual || r_consolidado(i).val_ape2;
        l_textoactual := l_textoactual || '</nombre>';
        --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
      
        -- Periodo
        l_textoactual := l_textoactual || '<campana>' || p_codigoperiodo ||
                         '</campana>';
        --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
      
        l_textoactual := l_textoactual ||
                         imp_pkg_proce_compa.imp_fn_etiqu_clasi_bolet_despa(r_consolidado(i)
                                                                            .cod_clie) ||
                         imp_pkg_proce_compa.imp_fn_etiqu_estat_bolet_despa(r_consolidado(i)
                                                                            .cod_clie);
      
        -- Fin Cabecera
        l_textoactual := l_textoactual || '</blqcab>';
        dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
      
        -- Inicio Detalle
        l_textoactual := '<detalle>';
        dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
      
        -- Iniciamos los totales
        l_totalsolicitado          := 0;
        l_totalatendido            := 0;
        l_totaldescuentos          := 0;
        l_totalcatalogo            := 0;
        l_totaldescuentos          := 0;
        l_totalimpuestosgratis     := 0;
        l_totalfacturado           := 0;
        l_totalpreciocatalogo      := 0;
        l_totalpreciocatalogototal := 0;
        l_totaloportunidadahorro   := 0;
        l_oportunidadrevista       := 0;
        l_oportunidadcatalogo      := 0;
      
        l_totalcatalogo2 := 0;
        l_totalmav       := 0;
      
        /*INSERTAMOS LA CABECERA*/
        INSERT INTO imp_print_pedid_resum
          (prsp_cod_proc,
           prsp_cod_clie,
           prsp_cod_paqu,
           oid_pedi_resu,
           usu_crea,
           fec_crea,
           ind_acti)
        VALUES
          (pscodigotransaccional,
           r_consolidado(i).cod_clie,
           pscodigopaquete,
           lsoidcabecera,
           pscodigousuario,
           SYSDATE,
           1);
        /**/
        lnnumorden := 0;
        -- Iteramos por las secciones del detalle
        OPEN c_detalle(r_consolidado(i).oid_soli_cabe,
                       l_despremioagotado,
                       l_desagotado,
                       l_desfaltliq,
                       l_desfaltanun,
                       l_desanulmotmax,
                       l_despremio,
                       l_desatrec,
                       l_desrecup,
                       l_desreemp,
                       l_desgratis,
                       l_despremiolet,
                       l_desrecupsemana,
                       l_desofertnavid,
                       ln_oidestra,
                       l_desrecuprol,
                       l_desrecuprol2);
        LOOP
          FETCH c_detalle BULK COLLECT
            INTO r_detalle LIMIT w_filas;
          FOR k IN 0 .. 3
          LOOP
          
            orig_ante := NULL;
          
            -- Iniciamos los subtotales
          
            l_contadordetalles            := 0;
            l_subtotalsolicitado          := 0;
            l_subtotalatendido            := 0;
            l_subtotalcatalogo            := 0;
            l_subtotaldescuentos          := 0;
            l_subtotalfacturado           := 0;
            l_subtotalpreciocatalogo      := 0;
            l_subtotalpreciocatalogototal := 0;
            l_subtotaloportunidadahorro   := 0;
            l_oportunidadahorro           := 0;
          
            -- Mostramos el titulo
          
            IF r_detalle.count > 0 THEN
              FOR j IN r_detalle.first .. r_detalle.last
              LOOP
              
                IF r_detalle(j).ind_grupo = k THEN
                
                  IF k = 0 AND l_flagtitulo1 = 0 THEN
                    l_textoactual := '<txt><u>' || l_nombreseccion1 ||
                                     '</u></txt><txt><t/></txt>';
                    dbms_lob.writeappend(l_clob,
                                         length(l_textoactual),
                                         l_textoactual);
                    l_flagtitulo1   := 1;
                    l_nombreseccion := l_nombreseccion1;
                    lstipofila      := '01';
                  ELSIF k = 1 AND l_flagtitulo2 = 0 THEN
                    l_textoactual := '<txt/>';
                    l_textoactual := l_textoactual || '<txt><u>' ||
                                     l_nombreseccion2 ||
                                     '</u></txt><txt><t/></txt>';
                    dbms_lob.writeappend(l_clob,
                                         length(l_textoactual),
                                         l_textoactual);
                    l_flagtitulo2   := 1;
                    l_nombreseccion := l_nombreseccion2;
                  ELSIF k = 2 AND l_flagtitulo3 = 0 THEN
                    l_textoactual := '<txt/>';
                    l_textoactual := l_textoactual || '<txt><u>' ||
                                     l_nombreseccion3 ||
                                     '</u></txt><txt><t/></txt>';
                    dbms_lob.writeappend(l_clob,
                                         length(l_textoactual),
                                         l_textoactual);
                    lstipofila      := '03';
                    l_nombreseccion := l_nombreseccion3;
                    l_flagtitulo3   := 1;
                  ELSIF k = 3 AND l_flagtitulo4 = 0 THEN
                    l_textoactual := '<txt/>';
                    l_textoactual := l_textoactual || '<txt><u>' ||
                                     l_nombreseccion4 ||
                                     '</u></txt><txt><t/></txt>';
                    dbms_lob.writeappend(l_clob,
                                         length(l_textoactual),
                                         l_textoactual);
                    l_flagtitulo4   := 1;
                    l_nombreseccion := l_nombreseccion4;
                    lstipofila      := '04';
                  END IF;
                  -- Calculo de valores
                  IF r_detalle(j).copa_oid_para_gene IS NOT NULL OR r_detalle(j)
                     .ictp_oid_tipo_prog IS NOT NULL THEN
                    -- Si se trata de un premio
                    l_preciounitario      := 0;
                    l_preciototal         := 0;
                    l_porcdscto           := 100;
                    l_descuento           := 0;
                    l_preciofacturado     := 0;
                    l_pagoposterior       := 0;
                    l_oportunidadahorro   := 0;
                    l_preciocatalogo      := 0;
                    l_preciocatalogototal := 0;
                  ELSE
                    IF r_detalle(j).val_prec_cata_unit_loca = 0 THEN
                      -- Si es un gratis
                      l_preciounitario       := r_detalle(j)
                                                .val_prec_cont_unit_loca;
                      l_preciototal          := r_detalle(j)
                                                .val_prec_cont_tota_loca;
                      l_porcdscto            := 100;
                      l_descuento            := r_detalle(j)
                                                .val_prec_cont_tota_loca;
                      l_preciofacturado      := 0;
                      l_totalimpuestosgratis := l_totalimpuestosgratis + r_detalle(j)
                                               .val_impo_impu_tota_loca;
                      l_pagoposterior        := 0;
                      l_oportunidadahorro    := 0;
                      l_preciocatalogo       := 0;
                      l_preciocatalogototal  := 0;
                      IF r_detalle(j).ind_grupo = 1 THEN
                        l_preciocatalogo      := nvl(r_detalle(j)
                                                     .imp_prec_publ,
                                                     0);
                        l_preciocatalogototal := nvl(r_detalle(j)
                                                     .imp_prec_publ,
                                                     0) * r_detalle(j)
                                                .num_unid_aten;
                        l_oportunidadahorro   := l_preciocatalogototal;
                        IF l_oportunidadahorro < 0 THEN
                          l_oportunidadahorro := 0;
                        END IF;
                      END IF;
                    ELSE
                      -- Producto con descuento
                      l_preciounitario  := r_detalle(j)
                                           .val_prec_cata_unit_loca;
                      l_preciototal     := r_detalle(j)
                                           .val_prec_cata_tota_loca;
                      l_porcdscto       := r_detalle(j).val_porc_desc;
                      l_descuento       := r_detalle(j)
                                           .val_impo_desc_tota_loca;
                      l_descuento2      := r_detalle(j)
                                           .val_prec_cata_tota_loca *
                                            (r_detalle(j).val_porc_desc / 100);
                      l_preciofacturado := r_detalle(j)
                                           .val_prec_fact_tota_loca;
                      IF ln_desc_flete2 = 'S' THEN
                        l_preciofacturado := r_detalle(j).val_prec_cata_tota_loca -
                                              l_descuento2;
                      END IF;
                      IF r_detalle(j).ind_grupo = 0 THEN
                        l_preciocatalogo      := l_preciounitario;
                        l_preciocatalogototal := l_preciototal;
                        l_oportunidadahorro   := l_descuento;
                        IF ln_desc_flete2 = 'S' THEN
                          l_oportunidadahorro := l_descuento2;
                        END IF;
                      ELSIF r_detalle(j).ind_grupo = 1 THEN
                        l_preciocatalogo      := nvl(r_detalle(j)
                                                     .imp_prec_publ,
                                                     0);
                        l_preciocatalogototal := nvl(r_detalle(j)
                                                     .imp_prec_publ,
                                                     0) * r_detalle(j)
                                                .num_unid_aten;
                        l_oportunidadahorro   := l_preciocatalogototal -
                                                 l_preciototal;
                        IF l_oportunidadahorro < 0 THEN
                          l_oportunidadahorro := 0;
                        END IF;
                      ELSE
                        l_preciocatalogo      := 0;
                        l_preciocatalogototal := 0;
                        l_oportunidadahorro   := 0;
                      END IF;
                    
                      -- Pago posterior (fraccionado)
                      --l_pagoPosterior := IMP_PKG_PROCE_COMPA.IMP_FN_CALCU_PAGO_POSTE(r_detalle(j).oid_soli_posi, l_codigoPeriodoSiguiente);
                    END IF;
                  END IF;
                
                  lstiporegi := 'PN';
                
                  IF (orig_ante IS NULL AND r_detalle(j)
                     .val_codi_orig IS NOT NULL) OR
                     (orig_ante IS NOT NULL AND
                     orig_ante <> r_detalle(j).val_codi_orig AND r_detalle(j)
                     .val_codi_orig IS NOT NULL) THEN
                  
                    l_textoactual := '<txt>H' || '<t/>';
                  
                    lstiporegi := 'PH';
                  
                    -- Codigo de Venta
                    l_textoactual := l_textoactual || r_detalle(j)
                                    .val_codi_orig;
                    l_textoactual := l_textoactual || '<t/>';
                    lscodigoventa := r_detalle(j).val_codi_orig;
                  
                    -- Descripcion
                    l_textoactual := l_textoactual || r_detalle(j).des_prod;
                    l_textoactual := l_textoactual || '<tr/>';
                  
                    -- Unidades demandadas
                    l_textoactual        := l_textoactual || r_detalle(j)
                                           .num_unid_orig;
                    l_textoactual        := l_textoactual || '<tr/>';
                    lsunidadesdemandadas := r_detalle(j).num_unid_orig;
                  
                    -- Unidades atendidas
                    l_textoactual       := l_textoactual || '';
                    l_textoactual       := l_textoactual || '<tr/>';
                    lsunidadesatendidas := 0;
                  
                    -- Precio unitario
                    l_textoactual    := l_textoactual || '';
                    l_textoactual    := l_textoactual || '<tr/>';
                    l_preciounitario := 0;
                  
                    -- Precio total
                    l_textoactual := l_textoactual || '';
                    l_textoactual := l_textoactual || '<tr/>';
                    l_preciototal := 0;
                  
                    -- % Descuento
                    l_textoactual := l_textoactual || '';
                    l_textoactual := l_textoactual || '<tr/>';
                    l_porcdscto   := 0;
                  
                    -- Importe Descuento
                    l_textoactual := l_textoactual || '';
                    l_textoactual := l_textoactual || '<tr/>';
                    l_descuento   := 0;
                  
                    -- Precio Catalogo
                    l_textoactual    := l_textoactual || '';
                    l_textoactual    := l_textoactual || '<tr/>';
                    l_preciocatalogo := 0;
                  
                    -- Total Precio Catalogo
                    l_textoactual         := l_textoactual || '';
                    l_textoactual         := l_textoactual || '<tr/>';
                    l_preciocatalogototal := 0;
                  
                    -- Oportunidad de Ahorro
                    l_textoactual       := l_textoactual || '';
                    l_textoactual       := l_textoactual || '<tr/>';
                    l_oportunidadahorro := 0;
                  
                    -- Total con Descuento
                    l_textoactual     := l_textoactual || '';
                    l_textoactual     := l_textoactual || '<tr/>';
                    l_preciofacturado := 0;
                  
                    -- Observaciones
                    l_textoactual := l_textoactual || '';
                    l_textoactual := l_textoactual || '</txt>';
                    dbms_lob.writeappend(l_clob,
                                         length(l_textoactual),
                                         l_textoactual);
                    lsobservacion := '';
                  
                    INSERT INTO imp_print_pedid_detal
                      (prsp_cod_proc,
                       prsp_cod_clie,
                       prsp_cod_paqu,
                       oid_pedi_resu,
                       oid_pedi_detal,
                       num_orde,
                       tip_fila,
                       des_fila,
                       cod_cuv,
                       cod_sap,
                       nom_prod,
                       val_unid_soli,
                       val_unid_aten,
                       val_unid_falt,
                       mon_prec_unit,
                       mon_prec_unit_siva,
                       mon_tota_aten,
                       mon_porc_dcto,
                       mon_tota_dcto,
                       mon_prec_cata,
                       mon_tota_cata,
                       mon_opor_ahor,
                       mon_tota_fact,
                       val_obse,
                       val_punt,
                       ind_fuer_caja,
                       usu_crea,
                       fec_crea,
                       ind_acti,
                       tip_regi)
                    VALUES
                      (pscodigotransaccional,
                       r_consolidado(i).cod_clie,
                       pscodigopaquete,
                       lsoidcabecera,
                       imp_prpd_seq.nextval,
                       lnnumorden,
                       lstipofila,
                       l_nombreseccion,
                       lscodigoventa,
                       r_detalle(j).cod_sap,
                       r_detalle(j).des_prod,
                       nvl(lsunidadesdemandadas, 0),
                       nvl(lsunidadesatendidas, 0),
                       nvl((nvl(lsunidadesdemandadas, 0) -
                           nvl(lsunidadesatendidas, 0)),
                           0),
                       l_preciounitario,
                       NULL,
                       l_preciototal,
                       l_porcdscto,
                       l_descuento,
                       l_preciocatalogo,
                       l_preciocatalogototal,
                       l_oportunidadahorro,
                       l_preciofacturado,
                       substr(lsobservacion, 0, 60),
                       NULL,
                       r_detalle(j).ind_dent_fuer_caja_bols,
                       pscodigousuario,
                       SYSDATE,
                       1,
                       lstiporegi);
                  
                  END IF;
                
                  l_textoactual := '<txt>';
                
                  IF r_detalle(j).val_codi_orig IS NOT NULL THEN
                    l_textoactual := l_textoactual || 'D<t/>';
                    lstiporegi    := 'PD';
                  END IF;
                
                  -- Codigo de Venta
                  l_textoactual := l_textoactual || r_detalle(j)
                                  .val_codi_vent;
                  l_textoactual := l_textoactual || '<t/>';
                  lscodigoventa := r_detalle(j).val_codi_vent;
                
                  -- Descripcion
                  l_textoactual := l_textoactual || r_detalle(j).des_prod;
                  l_textoactual := l_textoactual || '<tr/>';
                
                  -- Unidades demandadas
                  l_textoactual        := l_textoactual || r_detalle(j)
                                         .num_unid_dema_real;
                  l_textoactual        := l_textoactual || '<tr/>';
                  lsunidadesdemandadas := r_detalle(j).num_unid_dema_real;
                
                  -- Unidades atendidas
                  l_textoactual       := l_textoactual || r_detalle(j)
                                        .num_unid_aten;
                  l_textoactual       := l_textoactual || '<tr/>';
                  lsunidadesatendidas := r_detalle(j).num_unid_aten;
                
                  -- Precio unitario
                  l_textoactual := l_textoactual ||
                                   TRIM(to_char(l_preciounitario,
                                                l_formatonumerico));
                  l_textoactual := l_textoactual || '<tr/>';
                
                  -- Precio total
                  l_textoactual := l_textoactual ||
                                   TRIM(to_char(l_preciototal,
                                                l_formatonumerico));
                  l_textoactual := l_textoactual || '<tr/>';
                
                  -- % Descuento
                  l_textoactual := l_textoactual || l_porcdscto;
                  l_textoactual := l_textoactual || '<tr/>';
                
                  -- Importe Descuento
                  l_textoactual := l_textoactual ||
                                   TRIM(to_char(l_descuento,
                                                l_formatonumerico));
                  l_textoactual := l_textoactual || '<tr/>';
                
                  -- Precio Catalogo
                  l_textoactual := l_textoactual ||
                                   TRIM(to_char(l_preciocatalogo,
                                                l_formatonumerico));
                  l_textoactual := l_textoactual || '<tr/>';
                
                  -- Total Precio Catalogo
                  l_textoactual := l_textoactual ||
                                   TRIM(to_char(l_preciocatalogototal,
                                                l_formatonumerico));
                  l_textoactual := l_textoactual || '<tr/>';
                
                  -- Oportunidad de Ahorro
                  l_textoactual := l_textoactual ||
                                   TRIM(to_char(l_oportunidadahorro,
                                                l_formatonumerico));
                  l_textoactual := l_textoactual || '<tr/>';
                
                  -- Total con Descuento
                  l_textoactual := l_textoactual ||
                                   TRIM(to_char(l_preciofacturado,
                                                l_formatonumerico));
                  l_textoactual := l_textoactual || '<tr/>';
                
                  -- Observaciones
                  l_textoactual := l_textoactual || r_detalle(j).val_obse;
                  lsobservacion := r_detalle(j).val_obse;
                  IF p_codigopais <> 'BOE' THEN
                    l_textoactual := l_textoactual || '</txt>';
                  ELSE
                    l_textoactual := l_textoactual || '<tr/>';
                  END IF;
                  IF p_codigopais = 'BOE' THEN
                    -- Indicador Fuera de Caja
                    l_textoactual := l_textoactual || r_detalle(j)
                                    .ind_dent_fuer_caja_bols;
                    l_textoactual := l_textoactual || '</txt>';
                  END IF;
                
                  dbms_lob.writeappend(l_clob,
                                       length(l_textoactual),
                                       l_textoactual);
                
                  l_contadordetalles            := l_contadordetalles + 1;
                  l_subtotalsolicitado          := l_subtotalsolicitado + r_detalle(j)
                                                  .num_unid_dema_real;
                  l_subtotalatendido            := l_subtotalatendido + r_detalle(j)
                                                  .num_unid_aten;
                  l_subtotalcatalogo            := l_subtotalcatalogo +
                                                   l_preciototal;
                  l_subtotaldescuentos          := l_subtotaldescuentos +
                                                   l_descuento;
                  l_subtotalfacturado           := l_subtotalfacturado +
                                                   l_preciofacturado;
                  l_subtotalpreciocatalogo      := l_subtotalpreciocatalogo +
                                                   l_preciocatalogo;
                  l_subtotalpreciocatalogototal := l_subtotalpreciocatalogototal +
                                                   l_preciocatalogototal;
                  l_subtotaloportunidadahorro   := l_subtotaloportunidadahorro +
                                                   l_oportunidadahorro;
                
                  l_totalsolicitado          := l_totalsolicitado + r_detalle(j)
                                               .num_unid_dema_real;
                  l_totalatendido            := l_totalatendido + r_detalle(j)
                                               .num_unid_aten;
                  l_totalcatalogo            := l_totalcatalogo +
                                                l_preciototal;
                  l_totaldescuentos          := l_totaldescuentos +
                                                l_descuento;
                  l_totaldescuentos2         := l_totaldescuentos2 +
                                                l_descuento2;
                  l_totalfacturado           := l_totalfacturado +
                                                l_preciofacturado;
                  l_totalpreciocatalogo      := l_totalpreciocatalogo +
                                                l_preciocatalogo;
                  l_totalpreciocatalogototal := l_totalpreciocatalogototal +
                                                l_preciocatalogototal;
                  l_totaloportunidadahorro   := l_totaloportunidadahorro +
                                                l_oportunidadahorro;
                
                  orig_ante := r_detalle(j).val_codi_orig;
                
                  lnnumorden := lnnumorden + 1;
                  /*INSERTAMOS EL DETALLE*/
                  INSERT INTO imp_print_pedid_detal
                    (prsp_cod_proc,
                     prsp_cod_clie,
                     prsp_cod_paqu,
                     oid_pedi_resu,
                     oid_pedi_detal,
                     num_orde,
                     tip_fila,
                     des_fila,
                     cod_cuv,
                     cod_sap,
                     nom_prod,
                     val_unid_soli,
                     val_unid_aten,
                     val_unid_falt,
                     mon_prec_unit,
                     mon_prec_unit_siva,
                     mon_tota_aten,
                     mon_porc_dcto,
                     mon_tota_dcto,
                     mon_prec_cata,
                     mon_tota_cata,
                     mon_opor_ahor,
                     mon_tota_fact,
                     val_obse,
                     val_punt,
                     ind_fuer_caja,
                     usu_crea,
                     fec_crea,
                     ind_acti,
                     tip_regi)
                  VALUES
                    (pscodigotransaccional,
                     r_consolidado(i).cod_clie,
                     pscodigopaquete,
                     lsoidcabecera,
                     imp_prpd_seq.nextval,
                     lnnumorden,
                     lstipofila,
                     l_nombreseccion,
                     lscodigoventa,
                     r_detalle(j).cod_sap,
                     r_detalle(j).des_prod,
                     nvl(lsunidadesdemandadas, 0),
                     nvl(lsunidadesatendidas, 0),
                     nvl((nvl(lsunidadesdemandadas, 0) -
                         nvl(lsunidadesatendidas, 0)),
                         0),
                     l_preciounitario,
                     NULL,
                     l_preciototal,
                     l_porcdscto,
                     l_descuento,
                     l_preciocatalogo,
                     l_preciocatalogototal,
                     l_oportunidadahorro,
                     l_preciofacturado,
                     substr(lsobservacion, 0, 60),
                     NULL,
                     r_detalle(j).ind_dent_fuer_caja_bols,
                     pscodigousuario,
                     SYSDATE,
                     1,
                     lstiporegi);
                  /**/
                
                END IF;
              END LOOP;
            END IF;
          
            -- Cerramos el cursor de detalles
          
            -- Subtotales
            IF l_contadordetalles > 0 THEN
              l_textoactual := '<txt><t/><u>Sub Total:</u><tr/>';
              l_textoactual := l_textoactual || l_subtotalsolicitado;
              l_textoactual := l_textoactual || '<tr/>';
              l_textoactual := l_textoactual || l_subtotalatendido;
              l_textoactual := l_textoactual || '<tr/><tr/>';
              l_textoactual := l_textoactual ||
                               TRIM(to_char(l_subtotalcatalogo,
                                            l_formatonumerico));
              l_textoactual := l_textoactual || '<tr/><tr/>';
              l_textoactual := l_textoactual ||
                               TRIM(to_char(l_subtotaldescuentos,
                                            l_formatonumerico));
              l_textoactual := l_textoactual || '<tr/>';
              l_textoactual := l_textoactual ||
                               TRIM(to_char(l_subtotalpreciocatalogo,
                                            l_formatonumerico));
              l_textoactual := l_textoactual || '<tr/>';
              l_textoactual := l_textoactual ||
                               TRIM(to_char(l_subtotalpreciocatalogototal,
                                            l_formatonumerico));
              l_textoactual := l_textoactual || '<tr/>';
              l_textoactual := l_textoactual ||
                               TRIM(to_char(l_subtotaloportunidadahorro,
                                            l_formatonumerico));
              l_textoactual := l_textoactual || '<tr/>';
              l_textoactual := l_textoactual ||
                               TRIM(to_char(l_subtotalfacturado,
                                            l_formatonumerico));
              l_textoactual := l_textoactual || '<tr/>';
              l_textoactual := l_textoactual || '</txt>';
              dbms_lob.writeappend(l_clob,
                                   length(l_textoactual),
                                   l_textoactual);
              IF k = 0 THEN
                l_oportunidadcatalogo := l_subtotaloportunidadahorro;
              ELSIF k = 1 THEN
                l_oportunidadrevista := l_subtotaloportunidadahorro;
              END IF;
              IF k = 0 OR k = 1 THEN
                l_totalcatalogo2 := l_totalcatalogo2 + l_subtotalcatalogo;
              END IF;
              IF k = 2 THEN
                l_totalmav := l_totalmav + l_subtotalcatalogo;
              END IF;
            END IF;
          END LOOP;
        
          EXIT WHEN c_detalle%NOTFOUND;
        END LOOP;
        CLOSE c_detalle;
      
        -- Totales
      
        -- Al descuento le agregamos el redondeo
        IF lv_redondeodesc = 'S' THEN
          l_totaldescuentos := l_totaldescuentos - r_consolidado(i)
                              .val_impo_redo_loca;
        END IF;
      
        l_totalfacturado := l_totalcatalogo - l_totaldescuentos;
      
        l_textoactual := '<txt/><txt><t/><u>Total:</u><tr/>';
        l_textoactual := l_textoactual || l_totalsolicitado;
        l_textoactual := l_textoactual || '<tr/>';
        l_textoactual := l_textoactual || l_totalatendido;
        l_textoactual := l_textoactual || '<tr/><tr/>';
        l_textoactual := l_textoactual ||
                         TRIM(to_char(l_totalcatalogo, l_formatonumerico));
        l_textoactual := l_textoactual || '<tr/><tr/>';
        l_textoactual := l_textoactual ||
                         TRIM(to_char(l_totaldescuentos, l_formatonumerico));
        l_textoactual := l_textoactual || '<tr/>';
        l_textoactual := l_textoactual ||
                         TRIM(to_char(l_totalpreciocatalogo,
                                      l_formatonumerico));
        l_textoactual := l_textoactual || '<tr/>';
        l_textoactual := l_textoactual ||
                         TRIM(to_char(l_totalpreciocatalogototal,
                                      l_formatonumerico));
        l_textoactual := l_textoactual || '<tr/>';
        l_textoactual := l_textoactual ||
                         TRIM(to_char(l_totaloportunidadahorro,
                                      l_formatonumerico));
        l_textoactual := l_textoactual || '<tr/>';
        l_textoactual := l_textoactual ||
                         TRIM(to_char(l_totalfacturado, l_formatonumerico));
        l_textoactual := l_textoactual || '<tr/>';
        l_textoactual := l_textoactual || '</txt>';
        dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
      
        -- Fin Detalle
        l_textoactual := '</detalle>';
        --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
        --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);
      
        -- Inicio Pie
        l_textoactual := l_textoactual || '<pie>';
      
        BEGIN
        
          SELECT to_char(cc.fec_inic, 'dd/mm/yyyy')
            INTO l_fechavencimiento
            FROM cra_crono       cc,
                 cra_perio       cp,
                 seg_perio_corpo spc,
                 cra_activ       act
           WHERE cc.perd_oid_peri = cp.oid_peri
             AND cp.peri_oid_peri = spc.oid_peri
             AND spc.cod_peri = l_codigoperiodosiguiente
             AND cc.zzon_oid_zona =
                 (SELECT oid_zona
                    FROM zon_zona
                   WHERE cod_zona = r_consolidado(i).cod_zona)
             AND cc.cact_oid_acti = act.oid_acti
             AND act.cod_acti = 'CV';
        
        EXCEPTION
          WHEN OTHERS THEN
            l_fechavencimiento := to_char(SYSDATE, 'dd/mm/yyyy');
        END;
      
        -- Funcionalidad Flexipago
        IF lv_reemplazoocs = 'S' THEN
          l_interesflexipago  := nvl(ccc_pkg_gener.ccc_fn_obtie_inter_flexi_campa(r_consolidado(i)
                                                                                  .oid_clie,
                                                                                  p_codigoperiodo),
                                     0);
          l_saldoflexanterior := round(nvl(ccc_pkg_gener.ccc_fn_obtie_monto_flexi_campa(r_consolidado(i)
                                                                                        .oid_clie,
                                                                                        p_codigoperiodo),
                                           0),
                                       2);
        ELSE
          l_interesflexipago  := 0;
          l_saldoflexanterior := 0;
        END IF;
      
        -- Calculamos la percepcion
        l_percepcion := 0;
        IF l_indicadorpercepcion = 'S' THEN
          -- No tomamos en cuenta el flete para el calculo de la percepcion
          l_percepcion := round((l_totalcatalogo - l_totaldescuentos +
                                nvl(r_consolidado(i).gastos, 0)) *
                                l_tasaimpuestopercepcion / 100,
                                2);
        END IF;
      
        l_totalfactura := l_totalcatalogo - l_totaldescuentos + r_consolidado(i)
                         .val_impo_flet_tota_loca + l_interesflexipago +
                          l_percepcion;
      
        -- Obtenemos los Pagos Posteriores
        SELECT nvl(SUM(imp_pend), 0)
          INTO l_totalpagoposterior
          FROM ccc_movim_cuent_corri mcc
         WHERE mcc.clie_oid_clie = r_consolidado(i).oid_clie
           AND mcc.imp_pend > 0
           AND mcc.perd_oid_peri > l_oidperiodo
           AND mcc.soca_oid_soli_cabe = r_consolidado(i).oid_soli_cabe;
      
        SELECT COUNT(1)
          INTO l_cantidadoc
          FROM ped_solic_cabec
         WHERE clie_oid_clie = r_consolidado(i).oid_clie
           AND perd_oid_peri = l_oidperiodo
           AND ind_oc = 1
           AND ind_ts_no_conso = 1
           AND fec_fact IS NOT NULL
           AND tspa_oid_tipo_soli_pais IN
               (SELECT oid_tipo_soli_pais
                  FROM ped_tipo_solic_pais x,
                       ped_tipo_solic      y
                 WHERE x.tsol_oid_tipo_soli = y.oid_tipo_soli
                   AND y.ind_soli_nega = 0
                   AND y.ind_cons = 0);
      
        -- Obtenemos el Cargos por Familia Segura
        IF l_indicadorcargofamsegura = 'S' AND l_cantidadoc < 2 THEN
        
          SELECT nvl(SUM(mcc.imp_movi), 0)
            INTO l_cargofamsegura
            FROM ccc_movim_cuent_corri mcc,
                 ccc_proce             cp,
                 ccc_subpr             su
           WHERE mcc.clie_oid_clie = r_consolidado(i).oid_clie
             AND mcc.perd_oid_peri = l_oidperiodo
             AND mcc.subp_oid_subp_crea = su.oid_subp
             AND su.ccpr_oid_proc = cp.oid_proc
             AND cp.cod_proc = 'CCC007'
             AND su.cod_subp = 7;
        ELSE
          l_cargofamsegura := 0;
        
        END IF;
      
        /********************************************************************
        Total Factura - Pagos Posteriores + Cargo Familia Segura
        + Saldo Anterior + Cuota Flexipago = Total Monto a Pagar
        **********************************************************************/
        l_totalapagar := ccc_pkg_gener.ccc_fn_obtie_saldo_campa_anter(r_consolidado(i)
                                                                      .oid_clie,
                                                                      l_codigoperiodosiguiente);
      
        IF lv_indiejec = 'S' THEN
          -- Gastos Administrativos
          --l_saldoAnterior:=l_saldoAnterior-nvl(r_consolidado(i).gastos,0);
          l_totalfactura := l_totalfactura +
                            nvl(r_consolidado(i).gastos, 0) +
                            +nvl(r_consolidado(i).gastos2, 0);
        END IF;
      
        IF lv_indiejec2 IS NOT NULL THEN
          -- Gastos Administrativos
          --l_saldoAnterior:=l_saldoAnterior-nvl(r_consolidado(i).gastos,0);
          l_totalfactura := l_totalfactura +
                            nvl(r_consolidado(i).val_impo_impu_tota_loca, 0);
        END IF;
      
        BEGIN
        
          SELECT c.camp_ulti_pedi
            INTO lscodperigastos
            FROM ccc_detal_consu_gasto_admin c,
                 ped_solic_cabec             a
           WHERE c.soca_oid_soli_cabe_deud = a.oid_soli_cabe
             AND a.soca_oid_soli_cabe = r_consolidado(i).oid_soli_cabe;
        
        EXCEPTION
          WHEN OTHERS THEN
            NULL;
        END;
      
        /********************************************************************
        Saldo Anterior = Total Monto a Pagar - Total Factura + Pagos Posteriores - Cargo Familia Segura - Cuota Flexipago
        **********************************************************************/
        l_saldoanterior := l_totalapagar + l_totalpagoposterior -
                           l_totalfactura - l_cargofamsegura -
                           l_saldoflexanterior;
      
        IF ln_desc_flete = 'S' THEN
        
          l_totalfactura := l_totalfactura -
                            nvl(r_consolidado(i).val_impo_desc_flet, 0);
        
        END IF;
      
        -- En Peru el pie es diferente al resto de paises por las percepciones
        IF (l_indicadorpercepcion = 'S') THEN
        
          -- Total Catalogo
          l_textoactual := l_textoactual || '<linea1>' ||
                           TRIM(to_char(l_totalcatalogo, l_formatonumerico)) ||
                           '</linea1>';
        
          lstotalcatalogo := l_totalcatalogo;
          -- Total Descuentos (Incluimos el redondeo)
          l_textoactual := l_textoactual || '<linea2>' ||
                           TRIM(to_char(l_totaldescuentos,
                                        l_formatonumerico)) || '</linea2>';
        
          lstotaldescuentos := l_totaldescuentos;
          -- Total Facturado
          l_textoactual := l_textoactual || '<linea3>' ||
                           TRIM(to_char(l_totalcatalogo - l_totaldescuentos,
                                        l_formatonumerico)) || '</linea3>';
        
          lstotalfacturado := l_totalcatalogo - l_totaldescuentos;
          -- Flete
          l_textoactual := l_textoactual || '<linea4>' ||
                           TRIM(to_char(r_consolidado(i).val_impo_flet_tota_loca - r_consolidado(i)
                                        .val_reca_flet_loca,
                                        l_formatonumerico)) || '</linea4>';
        
          lstotalflete := r_consolidado(i).val_impo_flet_tota_loca - r_consolidado(i)
                          .val_reca_flet_loca;
          -- Percepciones
          l_textoactual       := l_textoactual || '<linea5>' ||
                                 TRIM(to_char(l_percepcion,
                                              l_formatonumerico)) ||
                                 '</linea5>';
          lstotalpercepciones := l_percepcion;
          -- Total Factura
          l_textoactual  := l_textoactual || '<linea6>' ||
                            TRIM(to_char(l_totalfactura, l_formatonumerico)) ||
                            '</linea6>';
          lstotalfactura := l_totalfactura;
          -- Pagos Posteriores
          l_textoactual      := l_textoactual || '<linea7>' ||
                                TRIM(to_char(l_totalpagoposterior,
                                             l_formatonumerico)) ||
                                '</linea7>';
          lspagosposteriores := l_totalpagoposterior;
          -- Abono Atencion de Servicios
          l_textoactual            := l_textoactual || '<linea8>' ||
                                      TRIM(to_char(l_cargofamsegura,
                                                   l_formatonumerico)) ||
                                      '</linea8>';
          lsabonoatencionservicios := l_cargofamsegura;
          -- Saldo anterior
          l_textoactual   := l_textoactual || '<linea9>' ||
                             TRIM(to_char(l_saldoanterior,
                                          l_formatonumerico)) ||
                             '</linea9>';
          lssaldoanterior := l_saldoanterior;
          -- Importe Total
          l_textoactual   := l_textoactual || '<linea10>' ||
                             TRIM(to_char(l_totalapagar, l_formatonumerico)) ||
                             '</linea10>';
          lsimporteactual := l_totalapagar;
          -- Oportunidad Catalogo
          l_textoactual         := l_textoactual || '<linea11>' ||
                                   TRIM(to_char(l_oportunidadcatalogo,
                                                l_formatonumerico)) ||
                                   '</linea11>';
          lsoportunidadcatalogo := l_oportunidadcatalogo;
          -- Oportunidad Revista
          l_textoactual        := l_textoactual || '<linea12>' ||
                                  TRIM(to_char(l_oportunidadrevista,
                                               l_formatonumerico)) ||
                                  '</linea12>';
          lsoportunidadrevista := l_oportunidadrevista;
          -- Oportunidad Total
          l_textoactual      := l_textoactual || '<linea13>' ||
                                TRIM(to_char(l_oportunidadcatalogo +
                                             l_oportunidadrevista,
                                             l_formatonumerico)) ||
                                '</linea13>';
          lsoportunidadtotal := l_oportunidadcatalogo +
                                l_oportunidadrevista;
          -- Mensaje
          l_textoactual := l_textoactual || '<linea14>' ||
                           TRIM(l_mensajeoportunidadahorro) || '</linea14>';
          lsmensaje     := l_mensajeoportunidadahorro;
          -- Fecha Vencimiento
          l_textoactual      := l_textoactual || '<linea15>' ||
                                TRIM(l_fechavencimiento) || '</linea15>';
          lsfechavencimiento := l_fechavencimiento;
        
          -- Gastos Administrativos
          l_textoactual           := l_textoactual || '<linea16>' ||
                                     TRIM(to_char(nvl(r_consolidado(i)
                                                      .gastos,
                                                      0),
                                                  l_formatonumerico)) ||
                                     '</linea16>';
          lsgastosadministrativos := nvl(r_consolidado(i).gastos, 0);
        
          -- Saldo Flexipago
          l_textoactual    := l_textoactual || '<linea17>' ||
                              TRIM(to_char(l_interesflexipago,
                                           l_formatonumerico)) ||
                              '</linea17>';
          lssaldoflexipago := l_interesflexipago;
          -- Cuota Flexipago
          l_textoactual    := l_textoactual || '<linea18>' ||
                              TRIM(to_char(l_saldoflexanterior,
                                           l_formatonumerico)) ||
                              '</linea18>';
          lscuotaflexipago := l_saldoflexanterior;
        
          IF lsactividadparametro IS NOT NULL THEN
            -- Recargo Flete
            l_textoactual  := l_textoactual || '<linea19>' ||
                              TRIM(to_char(nvl(r_consolidado(i)
                                               .val_reca_flet_loca,
                                               0),
                                           l_formatonumerico)) ||
                              '</linea19>';
            lsrecargoflete := nvl(r_consolidado(i).val_reca_flet_loca, 0);
          END IF;
        
          -- Descuento Cabecera
          l_textoactual       := l_textoactual || '<linea20>' ||
                                 TRIM(to_char(nvl(r_consolidado(i)
                                                  .val_impo_desc_3_tota_loca,
                                                  0),
                                              l_formatonumerico)) ||
                                 '</linea20>';
          lsdescuentocabecera := nvl(r_consolidado(i)
                                     .val_impo_desc_3_tota_loca,
                                     0);
        
          -- Campa?a de Gasto Administrativo
          l_textoactual                  := l_textoactual || '<linea21>' ||
                                            lscodperigastos || '</linea21>';
          lscampanagastosadministrativos := lscodperigastos;
        
          -- Descuento Cabecera2
          l_textoactual        := l_textoactual || '<linea22>' ||
                                  TRIM(to_char(nvl(r_consolidado(i)
                                                   .val_impo_desc_4_tota_loca,
                                                   0),
                                               l_formatonumerico)) ||
                                  '</linea22>';
          lsdescuentocabecera2 := nvl(r_consolidado(i)
                                      .val_impo_desc_4_tota_loca,
                                      0);
        
          -- Reclamos Pendientes
          l_textoactual        := l_textoactual || '<linea23>' ||
                                  TRIM(to_char(nvl(r_consolidado(i)
                                                   .val_recl_pend,
                                                   0),
                                               l_formatonumerico)) ||
                                  '</linea23>';
          lsreclamospendientes := nvl(r_consolidado(i).val_recl_pend, 0);
          -- Total Catalogo2
          l_textoactual := l_textoactual || '<linea24>' ||
                           TRIM(to_char(nvl(l_totalcatalogo2, 0),
                                        l_formatonumerico)) || '</linea24>';
        
          -- Total MAV
          l_textoactual := l_textoactual || '<linea25>' ||
                           TRIM(to_char(nvl(l_totalmav, 0),
                                        l_formatonumerico)) || '</linea25>';
          /*ACTUALIZAMOS LA CABECERA*/
          UPDATE imp_print_pedid_resum
             SET val_mont_pago      = lstotalcatalogo,
                 val_tabu_01b       = 0,
                 val_tabu_02b       = 1,
                 val_tabu_03b       = 1,
                 val_tabu_04b       = 0,
                 val_tabu_05b       = 1,
                 val_tabu_06b       = 1,
                 val_tabu_07b       = 1,
                 val_tabu_08b       = 1,
                 val_tabu_09b       = 1,
                 val_tabu_10b       = 0,
                 val_tabu_11b       = 1,
                 val_tabu_12b       = 1,
                 val_tabu_13b       = 1,
                 val_tabu_14b       = 1,
                 val_tabu_15b       = 0,
                 val_desc_01b       = 'TOTAL CATALOGO',
                 val_desc_02b       = '(-) Mis Descuentos',
                 val_desc_03b       = '(-) Descuentos Para Nuevas',
                 val_desc_04b       = 'TOTAL CON DESCUENTO',
                 val_desc_05b       = '(+) Gastos de Transporte',
                 val_desc_06b       = '(+) Gastos de Flete por Fecha Extemporánea',
                 val_desc_07b       = '(+) Percepción',
                 val_desc_08b       = '(+) Gastos Administrativos y de Cobranza', --falta decode campaña
                 val_desc_09b       = '(+) Intereses Flexipago de campañas anteriores',
                 val_desc_10b       = 'TOTAL FACTURA',
                 val_desc_11b       = '(-) Pagos Posteriores',
                 val_desc_12b       = '(+) Recaudo Seguro Familia Protegida',
                 val_desc_13b       = '(+) Saldo Campaña Anterior',
                 val_desc_14b       = 'Cuotas Flexipago Campañas Anteriores',
                 val_desc_15b       = 'TOTAL MONTO A PAGAR',
                 val_mont_01b       = lstotalcatalogo,
                 val_mont_02b       = lstotaldescuentos,
                 val_mont_03b       = NULL,
                 val_mont_04b       = lstotalfacturado,
                 val_mont_05b       = lstotalflete,
                 val_mont_06b       = NULL,
                 val_mont_07b       = lstotalpercepciones,
                 val_mont_08b       = NULL,
                 val_mont_09b       = NULL,
                 val_mont_10b       = lstotalfactura,
                 val_mont_11b       = lspagosposteriores,
                 val_mont_12b       = lsabonoatencionservicios,
                 val_mont_13b       = lssaldoanterior,
                 val_mont_14b       = NULL,
                 val_mont_15b       = lsimporteactual,
                 val_desc_01a       = 'Por tus compras de productos de catálogo',
                 val_desc_02a       = 'Por tus compras de productos de revista',
                 val_desc_03a       = 'Total Oportunidad de Ahorro*',
                 val_desc_opor_ahor = lsmensaje,
                 val_mont_01a       = lsoportunidadcatalogo,
                 val_mont_02a       = lsoportunidadrevista,
                 val_mont_03a       = lsoportunidadtotal
           WHERE prsp_cod_proc = pscodigotransaccional
             AND prsp_cod_clie = r_consolidado(i).cod_clie
             AND prsp_cod_paqu = pscodigopaquete
             AND oid_pedi_resu = lsoidcabecera;
        
        ELSE
        
          -- Total Catalogo
          l_textoactual   := l_textoactual || '<linea1>' ||
                             TRIM(to_char(l_totalcatalogo,
                                          l_formatonumerico)) ||
                             '</linea1>';
          lstotalcatalogo := l_totalcatalogo;
          -- Total Descuentos (Incluimos el redondeo)
          l_textoactual     := l_textoactual || '<linea2>' ||
                               TRIM(to_char(l_totaldescuentos,
                                            l_formatonumerico)) ||
                               '</linea2>';
          lstotaldescuentos := l_totaldescuentos;
          -- Total Facturado
          l_textoactual    := l_textoactual || '<linea3>' ||
                              TRIM(to_char(l_totalcatalogo -
                                           l_totaldescuentos,
                                           l_formatonumerico)) ||
                              '</linea3>';
          lstotalfacturado := l_totalcatalogo - l_totaldescuentos;
          -- Flete
          l_textoactual := l_textoactual || '<linea4>' ||
                           TRIM(to_char(r_consolidado(i)
                                        .val_impo_flet_tota_loca,
                                        l_formatonumerico)) || '</linea4>';
          lstotalflete  := r_consolidado(i).val_impo_flet_tota_loca;
          -- Total Factura
          l_textoactual  := l_textoactual || '<linea5>' ||
                            TRIM(to_char(l_totalfactura, l_formatonumerico)) ||
                            '</linea5>';
          lstotalfactura := l_totalfactura;
          -- Pagos Posteriores
          l_textoactual      := l_textoactual || '<linea6>' ||
                                TRIM(to_char(l_totalpagoposterior,
                                             l_formatonumerico)) ||
                                '</linea6>';
          lspagosposteriores := l_totalpagoposterior;
          -- Abono Atencion de Servicios (No se visualiza)
          l_textoactual            := l_textoactual || '<linea7>' ||
                                      TRIM(to_char(l_cargofamsegura,
                                                   l_formatonumerico)) ||
                                      '</linea7>';
          lsabonoatencionservicios := l_cargofamsegura;
          -- Impuesto de productos gratis (Solo se visualiza en algunos paises)
          l_textoactual          := l_textoactual || '<linea8>' ||
                                    TRIM(to_char(l_totalimpuestosgratis,
                                                 l_formatonumerico)) ||
                                    '</linea8>';
          lstotalimpuestosgratis := l_totalimpuestosgratis;
          -- Saldo anterior
          l_textoactual   := l_textoactual || '<linea9>' ||
                             TRIM(to_char(l_saldoanterior,
                                          l_formatonumerico)) ||
                             '</linea9>';
          lssaldoanterior := l_saldoanterior;
          -- Importe Total
          l_textoactual   := l_textoactual || '<linea10>' ||
                             TRIM(to_char(l_totalapagar, l_formatonumerico)) ||
                             '</linea10>';
          lsimporteactual := l_totalapagar;
          -- Oportunidad Catalogo
          l_textoactual         := l_textoactual || '<linea11>' ||
                                   TRIM(to_char(l_oportunidadcatalogo,
                                                l_formatonumerico)) ||
                                   '</linea11>';
          lsoportunidadcatalogo := l_oportunidadcatalogo;
          -- Oportunidad Revista
          l_textoactual := l_textoactual || '<linea12>' ||
                           TRIM(to_char(l_oportunidadrevista,
                                        l_formatonumerico)) || '</linea12>';
        
          lsoportunidadrevista := l_oportunidadrevista;
          -- Oportunidad Total
          l_textoactual := l_textoactual || '<linea13>' ||
                           TRIM(to_char(l_oportunidadcatalogo +
                                        l_oportunidadrevista,
                                        l_formatonumerico)) || '</linea13>';
        
          lsoportunidadtotal := l_oportunidadcatalogo +
                                l_oportunidadrevista;
          -- Mensaje
          l_textoactual := l_textoactual || '<linea14>' ||
                           TRIM(l_mensajeoportunidadahorro) || '</linea14>';
          lsmensaje     := l_mensajeoportunidadahorro;
          -- Fecha Vencimiento
          l_textoactual      := l_textoactual || '<linea15>' ||
                                TRIM(l_fechavencimiento) || '</linea15>';
          lsfechavencimiento := l_fechavencimiento;
          -- Impuesto
          l_textoactual := l_textoactual || '<linea16>' ||
                           TRIM(to_char(nvl(r_consolidado(i)
                                            .val_impo_impu_tota_loca,
                                            0),
                                        l_formatonumerico)) || '</linea16>';
          lsimpuesto    := nvl(r_consolidado(i).val_impo_impu_tota_loca, 0);
          -- subtotal con flete
          l_textoactual   := l_textoactual || '<linea17>' ||
                             TRIM(to_char(l_totalcatalogo +
                                          nvl(r_consolidado(i)
                                              .val_impo_flet_tota_loca,
                                              0),
                                          l_formatonumerico)) ||
                             '</linea17>';
          lssubtotalflete := l_totalcatalogo +
                             nvl(r_consolidado(i).val_impo_flet_tota_loca,
                                 0);
          -- subtotal con flete e impuesto
          l_textoactual           := l_textoactual || '<linea18>' ||
                                     TRIM(to_char(l_totalcatalogo +
                                                  nvl(r_consolidado(i)
                                                      .val_impo_flet_tota_loca,
                                                      0) + nvl(r_consolidado(i)
                                                               .val_impo_impu_tota_loca,
                                                               0),
                                                  l_formatonumerico)) ||
                                     '</linea18>';
          lssubtotalfleteimpuesto := l_totalcatalogo +
                                     nvl(r_consolidado(i)
                                         .val_impo_flet_tota_loca,
                                         0) + nvl(r_consolidado(i)
                                                  .val_impo_impu_tota_loca,
                                                  0);
          -- descuento flete
          l_textoactual    := l_textoactual || '<linea19>' ||
                              TRIM(to_char(r_consolidado(i)
                                           .val_impo_desc_flet,
                                           l_formatonumerico)) ||
                              '</linea19>';
          lsdescuentoflete := r_consolidado(i).val_impo_desc_flet;
        
          -- Descuento Cabecera
          l_textoactual       := l_textoactual || '<linea20>' ||
                                 TRIM(to_char(nvl(r_consolidado(i)
                                                  .val_impo_desc_3_tota_loca,
                                                  0),
                                              l_formatonumerico)) ||
                                 '</linea20>';
          lsdescuentocabecera := nvl(r_consolidado(i)
                                     .val_impo_desc_3_tota_loca,
                                     0);
          -- Gastos Administrativos
          l_textoactual           := l_textoactual || '<linea21>' ||
                                     TRIM(to_char(nvl(r_consolidado(i)
                                                      .gastos,
                                                      0) + nvl(r_consolidado(i)
                                                               .gastos2,
                                                               0),
                                                  l_formatonumerico)) ||
                                     '</linea21>';
          lsgastosadministrativos := nvl(r_consolidado(i).gastos, 0) +
                                     nvl(r_consolidado(i).gastos2, 0);
          -- Descuento Cabecera2
          l_textoactual        := l_textoactual || '<linea22>' ||
                                  TRIM(to_char(nvl(r_consolidado(i)
                                                   .val_impo_desc_4_tota_loca,
                                                   0),
                                               l_formatonumerico)) ||
                                  '</linea22>';
          lsdescuentocabecera2 := nvl(r_consolidado(i)
                                      .val_impo_desc_4_tota_loca,
                                      0);
          -- Reclamos Pendientes
          l_textoactual        := l_textoactual || '<linea23>' ||
                                  TRIM(to_char(nvl(r_consolidado(i)
                                                   .val_recl_pend,
                                                   0),
                                               l_formatonumerico)) ||
                                  '</linea23>';
          lsreclamospendientes := nvl(r_consolidado(i).val_recl_pend, 0);
        
          -- Total Catalogo2
          l_textoactual := l_textoactual || '<linea24>' ||
                           TRIM(to_char(nvl(l_totalcatalogo2, 0),
                                        l_formatonumerico)) || '</linea24>';
        
          -- Total MAV
          l_textoactual := l_textoactual || '<linea25>' ||
                           TRIM(to_char(nvl(l_totalmav, 0),
                                        l_formatonumerico)) || '</linea25>';
          /*ACTUALIZAMOS LA CABECERA*/
          UPDATE imp_print_pedid_resum
             SET val_mont_pago      = lstotalcatalogo,
                 val_tabu_01b       = 0,
                 val_tabu_02b       = 1,
                 val_tabu_03b       = 1,
                 val_tabu_04b       = 0,
                 val_tabu_05b       = 1,
                 val_tabu_06b       = 1,
                 val_tabu_07b       = 1,
                 val_tabu_08b       = 1,
                 val_tabu_09b       = 1,
                 val_tabu_10b       = 0,
                 val_tabu_11b       = NULL,
                 val_tabu_12b       = NULL,
                 val_tabu_13b       = 1,
                 val_tabu_14b       = NULL,
                 val_tabu_15b       = 0,
                 val_desc_01b       = 'TOTAL CATALOGO',
                 val_desc_02b       = '(-) Mis Descuentos',
                 val_desc_03b       = '(-) Descuentos Para Nuevas',
                 val_desc_04b       = 'TOTAL CON DESCUENTO',
                 val_desc_05b       = '(+) Gastos de Transporte',
                 val_desc_06b       = '(+) Gastos de Flete por Fecha Extemporánea',
                 val_desc_07b       = '(+) Percepción',
                 val_desc_08b       = '(+) Gastos Administrativos y de Cobranza',
                 val_desc_09b       = '(+) Interéses Flexipago de Campañas Anteriores',
                 val_desc_10b       = 'TOTAL FACTURA',
                 val_desc_11b       = NULL,
                 val_desc_12b       = NULL,
                 val_desc_13b       = '(+) Saldo Campaña Anterior',
                 val_desc_14b       = NULL,
                 val_desc_15b       = 'TOTAL MONTO A PAGAR',
                 val_mont_01b       = lstotalcatalogo,
                 val_mont_02b       = lstotaldescuentos,
                 val_mont_03b       = NULL,
                 val_mont_04b       = lstotalfacturado,
                 val_mont_05b       = lstotalflete,
                 val_mont_06b       = NULL,
                 val_mont_07b       = NULL,
                 val_mont_08b       = NULL,
                 val_mont_09b       = NULL,
                 val_mont_10b       = lstotalfactura,
                 val_mont_11b       = NULL,
                 val_mont_12b       = NULL,
                 val_mont_13b       = lssaldoanterior,
                 val_mont_14b       = NULL,
                 val_mont_15b       = lsimporteactual,
                 val_desc_01a       = 'Por tus compras de productos de catálogo',
                 val_desc_02a       = 'Por tus compras de productos de revista',
                 val_desc_03a       = 'Total Oportunidad de Ahorro*',
                 val_desc_opor_ahor = lsmensaje,
                 val_mont_01a       = lsoportunidadcatalogo,
                 val_mont_02a       = lsoportunidadrevista,
                 val_mont_03a       = lsoportunidadtotal
           WHERE prsp_cod_proc = pscodigotransaccional
             AND prsp_cod_clie = r_consolidado(i).cod_clie
             AND prsp_cod_paqu = pscodigopaquete
             AND oid_pedi_resu = lsoidcabecera;
        
        END IF;
      
        -- Fin Pie
        l_textoactual := l_textoactual || '</pie>';
      
        -- Fin Detalle de Factura
        l_textoactual := l_textoactual || '</detfac2>';
        dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
      
        l_correlativo := l_correlativo + 1;
      
        UPDATE imp_print_spool s
           SET mon_sald_actu = lstotalfactura,
               mon_sald_ante = l_saldoanterior,
               mon_opor_ahor = l_oportunidadahorro
         WHERE s.cod_clie = r_consolidado(i).cod_clie;
      
        UPDATE ped_solic_cabec xx
           SET xx.val_gana_tota_loca = l_oportunidadcatalogo +
                                       l_oportunidadrevista
         WHERE oid_soli_cabe = r_consolidado(i).oid_soli_cabe;
      
      END LOOP;
    
    END IF;
  
    EXIT WHEN c_consolidados%NOTFOUND;
  END LOOP;
  -- Cerramos el cursor
  CLOSE c_consolidados;
  COMMIT;
END imp_pr_print_pedid;
PROCEDURE imp_pr_print_pedid_cl
(
  p_codigopais          IN VARCHAR2,
  p_codigoperiodo       IN VARCHAR2,
  p_fechafacturacion    IN VARCHAR2,
  p_oidzona             NUMBER,
  pscodigotransaccional VARCHAR2,
  pscodigopaquete       VARCHAR2,
  pscodigousuario       VARCHAR2
) IS

  CURSOR c_consolidados
  (
    oidperiodo            NUMBER,
    indicadorenviolarissa VARCHAR2,
    numerolotefacturacion NUMBER
  ) IS
    SELECT sp.oid_pais,
           sp.cod_pais,
           mc.oid_clie,
           mc.cod_clie,
           mc.cod_digi_ctrl,
           mc.val_nom1,
           mc.val_nom2,
           mc.val_ape1,
           mc.val_ape2,
           mci.num_docu_iden,
           con.oid_soli_cabe,
           con.val_nume_soli,
           con.fec_fact,
           con.val_impo_flet_tota_loca,
           con.val_impo_redo_loca,
           substr(des_pais,
                  1,
                  length(des_pais) -
                  decode(instr(des_pais, 'ESIKA'), 0, 0, 5) -
                  decode(instr(des_pais, 'LBEL'), 0, 0, 4)) des_pais,
           zon.cod_zona,
           sec.cod_secc,
           ter.cod_terr,
           sec.num_secu_fact_diar,
           0 saldo_a_favor,
           --IMP_PKG_PROCE_COMPA.IMP_FN_CALCU_SALDO_FAVOR(CON.OID_SOLI_CABE) SALDO_A_FAVOR,
           --IMP_PKG_PROCE_COMPA.IMP_FN_CALCU_TOTAL_PAGO_POSTE(CON.OID_SOLI_CABE, '200902', 'A') SALDO_ANTERIOR
           0 saldo_anterior,
           --con.val_tota_gast_admi GASTOS,
           con.val_impo_rete_desc,
           mcda.ind_impr_docu imprimefactel,
           mcda.ind_impr_pdoc imprimepaqdoc,
           con.val_tota_gast_admi gastos,
           (SELECT val_nom1 || ' ' || val_nom2
              FROM mae_clien
             WHERE oid_clie = zon.clie_oid_clie) nombregz,
           (SELECT val_ape1 || ' ' || val_ape2
              FROM mae_clien
             WHERE oid_clie = zon.clie_oid_clie) apellidosgz,
           (SELECT x.val_text_comu
              FROM mae_clien_comun x
             WHERE clie_oid_clie = zon.clie_oid_clie
               AND x.ticm_oid_tipo_comu = 6
               AND rownum = 1) celular_gz,
           (SELECT x.val_text_comu
              FROM mae_clien_comun x
             WHERE clie_oid_clie = zon.clie_oid_clie
               AND x.ticm_oid_tipo_comu = 3
               AND rownum = 1) correo_gz,
           (SELECT decode(y.val_sigl, 'RUC', y.val_sigl, 'DNI')
              FROM mae_tipo_docum y
             WHERE y.oid_tipo_docu = mci.tdoc_oid_tipo_docu
               AND rownum = 1) tipo_docum
      FROM mae_clien             mc,
           mae_clien_ident       mci,
           mae_clien_datos_adici mcda,
           ped_solic_cabec       con,
           zon_zona              zon,
           zon_terri             ter,
           zon_terri_admin       zta,
           zon_secci             sec,
           ped_solic_cabec_secue sec,
           seg_pais              sp,
           bas_pais              bp
     WHERE mc.oid_clie = con.clie_oid_clie
       AND mc.oid_clie = mci.clie_oid_clie
       AND mc.oid_clie = mcda.clie_oid_clie
       AND mci.val_iden_docu_prin = 1
       AND sp.oid_pais = con.pais_oid_pais
       AND sp.cod_pais = bp.cod_pais
       AND con.zzon_oid_zona = zon.oid_zona
       AND con.terr_oid_terr = ter.oid_terr
       AND con.ztad_oid_terr_admi = zta.oid_terr_admi
       AND zta.zscc_oid_secc = sec.oid_secc
       AND con.oid_soli_cabe = sec.soca_oid_soli_cabe
       AND con.fec_fact = to_date(p_fechafacturacion, 'dd/mm/yyyy')
       AND con.perd_oid_peri = oidperiodo
       AND zon.oid_zona = p_oidzona
       AND con.ind_inte_lari_gene = indicadorenviolarissa
       AND (numerolotefacturacion IS NULL OR
           con.num_lote_fact = numerolotefacturacion)
       AND con.num_unid_aten_tota > 0
       AND EXISTS
     (SELECT NULL
              FROM int_lar_tipo_solici_pedido_dis l
             WHERE l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais);
  --ORDER BY MC.COD_CLIE,
  --         CON.VAL_NUME_SOLI;

  TYPE consolidadorecord IS RECORD(
    oid_pais                seg_pais.oid_pais%TYPE,
    cod_pais                seg_pais.cod_pais%TYPE,
    oid_clie                mae_clien.oid_clie%TYPE,
    cod_clie                mae_clien.cod_clie%TYPE,
    cod_digi_ctrl           mae_clien.cod_digi_ctrl%TYPE,
    val_nom1                mae_clien.val_nom1%TYPE,
    val_nom2                mae_clien.val_nom2%TYPE,
    val_ape1                mae_clien.val_ape1%TYPE,
    val_ape2                mae_clien.val_ape2%TYPE,
    num_docu_iden           mae_clien_ident.num_docu_iden%TYPE,
    oid_soli_cabe           ped_solic_cabec.oid_soli_cabe%TYPE,
    val_nume_soli           ped_solic_cabec.val_nume_soli%TYPE,
    fec_fact                ped_solic_cabec.fec_fact%TYPE,
    val_impo_flet_tota_loca ped_solic_cabec.val_impo_flet_tota_loca%TYPE,
    val_impo_redo_loca      ped_solic_cabec.val_impo_redo_loca%TYPE,
    des_pais                bas_pais.des_pais%TYPE,
    cod_zona                zon_zona.cod_zona%TYPE,
    cod_secc                zon_secci.cod_secc%TYPE,
    cod_terr                zon_terri.cod_terr%TYPE,
    num_secu_fact_diar      ped_solic_cabec_secue.num_secu_fact_diar%TYPE,
    saldoanterior           ped_solic_posic.val_prec_cata_unit_loca%TYPE,
    saldoafavor             ped_solic_cabec_secue.num_secu_fact_diar%TYPE,
    --    gastos                  ped_solic_cabec.val_tota_gast_admi%TYPE,
    val_impo_rete_desc ped_solic_cabec.val_impo_rete_desc%TYPE,
    imprimefactel      mae_clien_datos_adici.ind_impr_docu%TYPE,
    imprimepaqdoc      mae_clien_datos_adici.ind_impr_pdoc%TYPE,
    gastos             ped_solic_cabec.val_tota_gast_admi%TYPE,
    nombresgz          VARCHAR2(100),
    apellidosgz        VARCHAR2(100),
    celulargz          VARCHAR2(100),
    correogz           VARCHAR2(100),
    tipo_docum         VARCHAR2(100)
    
    );

  TYPE consolidadotype IS TABLE OF consolidadorecord;
  r_consolidado consolidadotype;

  CURSOR c_detalle
  (
    oidconsolidado     NUMBER,
    v_despremioagotado VARCHAR2,
    v_desagotado       VARCHAR2,
    v_desfaltliq       VARCHAR2,
    v_desfaltanun      VARCHAR2,
    v_desanulmotmax    VARCHAR2,
    v_despremio        VARCHAR2,
    v_desatrec         VARCHAR2,
    v_desrecup         VARCHAR2,
    v_desreemp         VARCHAR2,
    v_desgratis        VARCHAR2,
    v_despremiolet     VARCHAR2,
    v_desofertnavid    VARCHAR2,
    v_oidestra         NUMBER
  ) IS
    SELECT psc.oid_soli_cabe,
           psc.copa_oid_para_gene,
           psc.ictp_oid_tipo_prog,
           psp.oid_soli_posi,
           nvl(nvl(psp.val_codi_vent,
                   lpad('0', 4 - length(psp.val_codi_vent_fict), '0') ||
                   psp.val_codi_vent_fict),
               '00000') AS val_codi_vent,
           --(SELECT VAL_I18N FROM GEN_I18N_SICC_PAIS WHERE ATTR_ENTI = 'MAE_PRODU' AND IDIO_OID_IDIO = 1 AND VAL_OID = PSP.PROD_OID_PROD) DES_PROD,
           imp_pkg_proce_laser.imp_fn_desc_produ(p_codigopais,
                                                 psp.prod_oid_prod) des_prod,
           psp.num_unid_dema_real,
           psp.num_unid_aten,
           psp.val_prec_cata_unit_loca,
           psp.val_prec_cata_unit_loca * psp.num_unid_aten val_prec_cata_tota_loca,
           psp.val_prec_cont_unit_loca,
           psp.val_prec_cont_unit_loca * psp.num_unid_aten val_prec_cont_tota_loca,
           psp.val_prec_fact_tota_loca,
           psp.val_impo_desc_tota_loca,
           nvl(psp.val_porc_desc, 0) val_porc_desc,
           psp.val_impo_impu_tota_loca,
           pod.imp_prec_publ,
           psp.fopa_oid_form_pago,
           psp.val_prec_sin_impu_tota_loca,
           decode(psc.modu_oid_modu, 15, 3, nvl(pto.num_secc_deta_fact, 2)) ind_grup,
           psp.val_codi_orig,
           psp.num_unid_orig,
           psp.oid_nive_ofer,
           CASE
             WHEN EXISTS (SELECT 1
                     FROM pre_ofert x
                    WHERE x.oid_ofer = pod.ofer_oid_ofer
                      AND x.coes_oid_estr = v_oidestra) THEN
              v_desofertnavid
             WHEN (nvl(psp.num_unid_dema_real, 0) -
                  nvl(psp.num_unid_aten, 0) > 0) AND
                  (psc.copa_oid_para_gene IS NOT NULL OR
                  psc.ictp_oid_tipo_prog IS NOT NULL) THEN
              v_despremioagotado
             WHEN nvl(psp.num_unid_dema_real, 0) - nvl(psp.num_unid_aten, 0) > 0 THEN
              v_desagotado
             WHEN nvl(psp.num_unid_dema_real, 0) = 0 AND
                  nvl(psp.num_unid_aten, 0) = 0 AND psp.ind_limi_vent = 1 AND
                  (pto.cod_tipo_ofer = '21' OR pto.cod_tipo_ofer = '23') THEN
              v_desfaltliq
             WHEN nvl(psp.num_unid_dema_real, 0) = 0 AND
                  nvl(psp.num_unid_aten, 0) = 0 AND psp.ind_limi_vent = 1 THEN
              v_desfaltanun
             WHEN nvl(psp.num_unid_dema_real, 0) = 0 AND
                  nvl(psp.num_unid_aten, 0) = 0 AND
                  psp.espo_oid_esta_posi = 2 AND psp.stpo_oid_subt_posi = 21 THEN
              v_desanulmotmax
           /*WHEN NVL(PSP.NUM_UNID_DEMA_REAL, 0) = 0 AND NVL(PSP.NUM_UNID_ATEN, 0) = 0
            AND PSP.ESPO_OID_ESTA_POSI = 2
           THEN 'Vta.Exc'*/
             WHEN pst.cod_subt_posi IS NOT NULL AND pst.cod_subt_posi = 'RD' THEN
              v_desagotado
           /*WHEN NVL(PSP.NUM_UNID_DEMA_REAL, 0) = 0 AND NVL(PSP.NUM_UNID_ATEN, 0) = 0
           THEN 'No.Aplica'*/
             WHEN (psc.copa_oid_para_gene IS NOT NULL OR
                  psc.ictp_oid_tipo_prog IS NOT NULL) THEN
              v_despremio
             WHEN psc.modu_oid_modu = '15' THEN
              v_desatrec
             WHEN ptp.cod_tipo_posi IS NOT NULL AND ptp.cod_tipo_posi = 'RE' THEN
              v_desrecup
             WHEN ptp.cod_tipo_posi IS NOT NULL AND ptp.cod_tipo_posi = 'DA' THEN
              ''
             WHEN pst.cod_subt_posi IS NOT NULL AND pst.cod_subt_posi = 'RZ' THEN
              v_desreemp ||
              (SELECT DISTINCT pof.val_codi_vent
                 FROM pre_matri_factu pmf,
                      pre_matri_reemp pmr,
                      pre_matri_factu pmf2,
                      pre_ofert_detal pof
                WHERE pmf.oid_matr_fact = pmr.mafa_oid_cod_reem
                  AND pmf.ofde_oid_deta_ofer = psp.ofde_oid_deta_ofer
                  AND pmr.mafa_oid_cod_ppal = pmf2.oid_matr_fact
                  AND pmf2.ofde_oid_deta_ofer = pof.oid_deta_ofer
                  AND EXISTS
                (SELECT 1
                         FROM ped_solic_posic
                        WHERE soca_oid_soli_cabe = psc.oid_soli_cabe
                          AND ofde_oid_deta_ofer = psp.ofde_oid_deta_ofer)
                  AND rownum = 1)
             WHEN psp.val_prec_cata_unit_loca = 0 THEN
              v_desgratis
             WHEN ts.cod_tipo_soli = 'IPLC' THEN
              v_despremiolet
             ELSE
              ''
           END AS val_obse,
           mp.cod_sap,
           psp.num_unid_por_aten
      FROM ped_solic_cabec     psc,
           ped_solic_posic     psp,
           pre_ofert_detal     pod,
           pre_tipo_ofert      pto,
           ped_tipo_solic_pais tsp,
           ped_tipo_solic      ts,
           ped_tipo_posic      ptp,
           ped_subti_posic     pst,
           mae_produ           mp
     WHERE psc.oid_soli_cabe = psp.soca_oid_soli_cabe
       AND psc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
       AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
       AND psp.prod_oid_prod = mp.oid_prod
          --AND TS.CLSO_OID_CLAS_SOLI = PCS.OID_CLAS_SOLI
       AND psp.tpos_oid_tipo_posi = ptp.oid_tipo_posi(+)
       AND psp.stpo_oid_subt_posi = pst.oid_subt_posi(+)
       AND psp.ofde_oid_deta_ofer = pod.oid_deta_ofer(+)
       AND pod.tofe_oid_tipo_ofer = pto.oid_tipo_ofer(+)
       AND psp.espo_oid_esta_posi != 2
       AND psc.soca_oid_soli_cabe = oidconsolidado
    --)
    --WHERE IND_GRUP = indicadorGrupo
     ORDER BY oid_nive_ofer,
              val_codi_orig,
              val_codi_vent;

  TYPE detallerecord IS RECORD(
    oid_soli_cabe               ped_solic_cabec.oid_soli_cabe%TYPE,
    copa_oid_para_gene          ped_solic_cabec.copa_oid_para_gene%TYPE,
    ictp_oid_tipo_prog          ped_solic_cabec.ictp_oid_tipo_prog%TYPE,
    oid_soli_posi               ped_solic_posic.oid_soli_posi%TYPE,
    val_codi_vent               VARCHAR2(15),
    des_prod                    gen_i18n_sicc_pais.val_i18n%TYPE,
    num_unid_dema_real          ped_solic_posic.num_unid_dema_real%TYPE,
    num_unid_aten               ped_solic_posic.num_unid_aten%TYPE,
    val_prec_cata_unit_loca     ped_solic_posic.val_prec_cata_unit_loca%TYPE,
    val_prec_cata_tota_loca     ped_solic_posic.val_prec_cata_tota_loca%TYPE,
    val_prec_cont_unit_loca     ped_solic_posic.val_prec_cont_unit_loca%TYPE,
    val_prec_cont_tota_loca     ped_solic_posic.val_prec_cont_tota_loca%TYPE,
    val_prec_fact_tota_loca     ped_solic_posic.val_prec_fact_tota_loca%TYPE,
    val_impo_desc_tota_loca     ped_solic_posic.val_impo_desc_tota_loca%TYPE,
    val_porc_desc               ped_solic_posic.val_porc_desc%TYPE,
    val_impo_impu_tota_loca     ped_solic_posic.val_impo_impu_tota_loca%TYPE,
    imp_prec_publ               pre_ofert_detal.imp_prec_publ%TYPE,
    fopa_oid_form_pago          ped_solic_posic.fopa_oid_form_pago%TYPE,
    val_prec_sin_impu_tota_loca ped_solic_posic.val_prec_sin_impu_tota_loca%TYPE,
    ind_grupo                   pre_tipo_ofert.num_secc_deta_fact%TYPE,
    val_codi_orig               ped_solic_posic.val_codi_orig%TYPE,
    num_unid_orig               ped_solic_posic.num_unid_orig%TYPE,
    oid_nive_ofer               ped_solic_posic.oid_nive_ofer%TYPE,
    val_obse                    VARCHAR2(1000),
    cod_sap                     mae_produ.cod_sap%TYPE,
    num_unid_por_aten           ped_solic_posic.num_unid_por_aten%TYPE);

  TYPE detalletype IS TABLE OF detallerecord;
  r_detalle detalletype;

  CURSOR c_detalle1(oidconsolidado NUMBER) IS
    SELECT nvl(psp.val_porc_desc, 0) val_porc_desc,
           SUM(psp.val_prec_sin_impu_tota_loca) val_prec_cata_tota_loca,
           SUM(psp.val_impo_desc_tota_loca)
      FROM ped_solic_cabec psc,
           ped_solic_posic psp
     WHERE psc.oid_soli_cabe = psp.soca_oid_soli_cabe
       AND psp.espo_oid_esta_posi != 2
       AND psc.soca_oid_soli_cabe = oidconsolidado
     GROUP BY nvl(psp.val_porc_desc, 0)
     ORDER BY nvl(psp.val_porc_desc, 0);

  TYPE detallerecord1 IS RECORD(
    val_porc_desc           ped_solic_posic.val_porc_desc%TYPE,
    val_prec_cata_tota_loca ped_solic_posic.val_prec_cata_tota_loca%TYPE,
    val_impo_desc_tota_loca ped_solic_posic.val_impo_desc_tota_loca%TYPE);

  TYPE detalletype1 IS TABLE OF detallerecord1;
  r_detalle1 detalletype1;

  -- Variables locales
  l_oidpais    NUMBER;
  l_oidperiodo NUMBER;
  l_oidcanal   NUMBER;
  l_oidmarca   NUMBER;
  --l_correlativo               NUMBER := 1;
  l_contadordetalles    NUMBER := 0;
  l_porcdscto           NUMBER;
  l_preciounitario      NUMBER(12, 2) := 0;
  l_preciototal         NUMBER(12, 2) := 0;
  l_descuento           NUMBER(12, 2) := 0;
  l_preciofacturado     NUMBER(12, 2) := 0;
  l_pagoposterior       NUMBER(12, 2) := 0;
  l_preciocatalogo      NUMBER(12, 2) := 0;
  l_preciocatalogototal NUMBER(12, 2) := 0;
  l_oportunidadahorro   NUMBER(12, 2) := 0;

  l_subtotalsolicitado          NUMBER := 0;
  l_subtotalatendido            NUMBER := 0;
  l_subtotalcatalogo            NUMBER(12, 2) := 0;
  l_subtotaldescuentos          NUMBER(12, 2) := 0;
  l_subtotalfacturado           NUMBER(12, 2) := 0;
  l_subtotalpreciocatalogo      NUMBER(12, 2) := 0;
  l_subtotalpreciocatalogototal NUMBER(12, 2) := 0;
  l_subtotaloportunidadahorro   NUMBER(12, 2) := 0;

  l_totalsolicitado          NUMBER := 0;
  l_totalatendido            NUMBER := 0;
  l_totalcatalogo            NUMBER(12, 2) := 0;
  l_totaldescuentos          NUMBER(12, 2) := 0;
  l_totalfacturado           NUMBER(12, 2) := 0;
  l_totalpreciocatalogo      NUMBER(12, 2) := 0;
  l_totalpreciocatalogototal NUMBER(12, 2) := 0;
  l_totaloportunidadahorro   NUMBER(12, 2) := 0;
  l_totalimpuestosgratis     NUMBER(12, 2) := 0;
  l_saldofavor               NUMBER(12, 2) := 0;
  l_saldoanterior            NUMBER(12, 2) := 0;
  l_percepcion               NUMBER(12, 2) := 0;
  l_totalfactura             NUMBER(12, 2) := 0;
  l_cargofamsegura           NUMBER(12, 2) := 0;
  l_totalapagar              NUMBER(12, 2) := 0;

  l_oportunidadcatalogo NUMBER(12, 2) := 0;
  l_oportunidadrevista  NUMBER(12, 2) := 0;

  l_indicadorenviolarissa    VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                           'indicadorEnvioLarissa');
  l_indicadorenvioultimolote VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                           'indicadorEnvioUltimoLote');
  l_indicadorcargofamsegura  VARCHAR2(100) := nvl(imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                               'indicadorCargoFamSegura'),
                                                  'N');
  l_indicadorimpuestosgratis VARCHAR2(1) := 'N';
  l_totalpagoposterior       NUMBER(12, 2) := 0;
  l_indicadorpercepcion      VARCHAR2(1) := 'N';
  l_numerolotefacturacion    NUMBER;
  l_textoactual              VARCHAR2(20000) := '';
  l_codigoperiodosiguiente   VARCHAR2(6);
  l_cambiolinearetornocarro  VARCHAR2(2) := chr(13) || chr(10);
  l_formatonumerico          VARCHAR2(100) := nvl(imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                               'formatoNumerico'),
                                                  '9999999G990D00');
  l_tasaimpuestopercepcion   NUMBER(5, 3);
  --nuevo parametro

  l_nombreseccion1 VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                 'nombreSeccion1');
  l_nombreseccion2 VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                 'nombreSeccion2');
  l_nombreseccion3 VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                 'nombreSeccion3');
  l_nombreseccion4 VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                 'nombreSeccion4');
  l_nombreseccion  VARCHAR2(100) := '';

  l_despremioagotado VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                   'desPremioAgotado');
  l_desagotado       VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                   'desAgotado');
  l_desfaltliq       VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                   'desFaltLiq');
  l_desfaltanun      VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                   'desFaltAnun');
  l_desanulmotmax    VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                   'desAnulMotMax');
  l_despremio        VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                   'desPremio');
  l_desatrec         VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                   'desAtRec');
  l_desrecup         VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                   'desRecup');
  l_desreemp         VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                   'desReemp');
  l_desgratis        VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                   'desGratis');
  l_despremiolet     VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                   'desPremioLET');
  l_desofertnavid    VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                   'desOfertNavid');

  l_mensajeoportunidadahorro VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                           'mensajeOportunidadAhorro');

  l_fechavencimiento VARCHAR2(100) := '';
  l_fechacv          VARCHAR2(100) := '';

  l_flagtitulo1 NUMBER := 0;
  l_flagtitulo2 NUMBER := 0;
  l_flagtitulo3 NUMBER := 0;
  l_flagtitulo4 NUMBER := 0;

  l_cantidadoc NUMBER := 0;

  lv_indiejec VARCHAR2(10) := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(p_codigopais,
                                                                       'STO_GASTO_ADMIN'),
                                  'N');

  lv_reemplazoocs VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                'indicadorReemplazoOCS');

  ln_oidestra NUMBER(10) := sto_pkg_gener.sto_fn_obten_param_ocr(p_codigopais,
                                                                 'STO_ESTRA_NAVID');

  lv_actividadconf VARCHAR2(100) := nvl(imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                     'actividadConf'),
                                        'CV');

  lv_actividaddesp VARCHAR2(100) := nvl(imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                     'actividadDesp'),
                                        'DP');

  lv_imprimepaqdoc VARCHAR2(100) := nvl(imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                     'imprimepaqdoc'),
                                        'N');

  lv_imprimefactel VARCHAR2(100) := nvl(imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                     'imprimefactel'),
                                        'N');

  lv_imprimenuevos VARCHAR2(100) := nvl(imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                     'imprimenuevos'),
                                        'N');

  ln_diasdesp  NUMBER(10) := 0;
  ln_diasdesp2 NUMBER(10) := 0;
  ln_diasdesp3 NUMBER(10) := 0;

  l_fechadespacho   VARCHAR2(100) := '';
  l_fechadespacho_2 VARCHAR2(100) := '';
  l_fechadespacho_3 VARCHAR2(100) := '';
  l_fechadespacho2  VARCHAR2(100) := nvl(imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                      'indFechaDesp2'),
                                         'N');
  l_fechasigfact    VARCHAR2(100) := '';
  l_fechasigfact2   VARCHAR2(100) := '';
  l_fechasigfact3   VARCHAR2(100) := '';

  lsactividadparametro VARCHAR2(100) := sto_pkg_gener.sto_fn_obten_param_ocr(p_codigopais,
                                                                             'STO_ACTIV_RECAR_FLET');

  l_saldoflexanterior NUMBER(12, 2) := 0;
  l_interesflexipago  NUMBER(12, 2) := 0;

  orig_ante VARCHAR2(100) := '';

  lscodigoventa        VARCHAR2(15);
  lsunidadesdemandadas NUMBER(12);
  lsunidadesatendidas  NUMBER(6);
  lsobservacion        VARCHAR2(1000);
  lsoidcabecera        NUMBER(12);

  lstotalcatalogo                NUMBER(12, 2) := 0;
  lstotaldescuentos              NUMBER(12, 2) := 0;
  lstotalfacturado               NUMBER(12, 2) := 0;
  lstotalflete                   NUMBER(12, 2) := 0;
  lstotalpercepciones            NUMBER(12, 2) := 0;
  lstotalfactura                 NUMBER(12, 2) := 0;
  lspagosposteriores             NUMBER(12, 2) := 0;
  lsabonoatencionservicios       NUMBER(12, 2) := 0;
  lssaldoanterior                NUMBER(12, 2) := 0;
  lsimporteactual                NUMBER(12, 2) := 0;
  lsoportunidadcatalogo          NUMBER(12, 2) := 0;
  lsoportunidadrevista           NUMBER(12, 2) := 0;
  lsoportunidadtotal             NUMBER(12, 2) := 0;
  lsmensaje                      VARCHAR2(60);
  lsfechavencimiento             VARCHAR2(60);
  lsgastosadministrativos        NUMBER(12, 2) := 0;
  lssaldoflexipago               NUMBER(12, 2) := 0;
  lscuotaflexipago               NUMBER(12, 2) := 0;
  lsrecargoflete                 NUMBER(12, 2) := 0;
  lsdescuentocabecera            NUMBER(12, 2) := 0;
  lscampanagastosadministrativos VARCHAR2(10);
  lsdescuentocabecera2           NUMBER(12, 2) := 0;
  lsreclamospendientes           NUMBER(12, 2) := 0;

  lsimpuesto                NUMBER(12, 2) := 0;
  lssubtotalflete           NUMBER(12, 2) := 0;
  lssubtotalfleteimpuesto   NUMBER(12, 2) := 0;
  lsdescuentoflete          NUMBER(12, 2) := 0;
  lstotalimpuestosgratis    NUMBER(12, 2) := 0;
  lsdescripcion             VARCHAR2(70);
  lsimpuestoproductosgratis NUMBER(12, 2) := 0;
  lsdescuentototal          NUMBER(12, 2) := 0;
  lsretencion               NUMBER(12, 2) := 0;
  lsfechacv                 NUMBER(12, 2) := 0;
  lsfechav1                 VARCHAR2(100);

  lstipofila VARCHAR2(2) := '';
  lstiporegi VARCHAR2(2) := 'PN';

BEGIN

  -- Obtenemos el OID del periodo
  l_oidpais                := gen_pkg_gener.gen_fn_devuelve_id_pais(p_codigopais);
  l_oidcanal               := gen_pkg_gener.gen_fn_devuelve_id_canal('VD'); --modificado
  l_oidmarca               := gen_pkg_gener.gen_fn_devuelve_id_marca('T'); --modificado
  l_oidperiodo             := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(p_codigoperiodo,
                                                                         l_oidmarca,
                                                                         l_oidcanal);
  l_codigoperiodosiguiente := gen_fn_calcu_perio(p_codigoperiodo, 1);

  --SOLO SE JECUTARA EL PROCESO SI PARAMETRO ES S, EN OTRO CASO NO SE EJECUTARA
  IF (l_indicadorenvioultimolote = '1' OR l_indicadorenvioultimolote = 'S') THEN
  
    BEGIN
      SELECT MAX(cons.num_lote_fact)
        INTO l_numerolotefacturacion
        FROM ped_solic_cabec                cons,
             int_lar_tipo_solici_pedido_dis tspd
       WHERE cons.perd_oid_peri = l_oidperiodo
         AND cons.fec_fact = to_date(p_fechafacturacion, 'dd/mm/yyyy')
         AND cons.ind_ts_no_conso = 0
         AND (cons.ind_pedi_prue = 0 OR cons.ind_pedi_prue IS NULL)
         AND cons.tspa_oid_tipo_soli_pais = tspd.tspa_oid_tipo_soli_pais
         AND cons.pais_oid_pais = l_oidpais;
    EXCEPTION
      WHEN OTHERS THEN
        l_numerolotefacturacion := NULL;
    END;
  
  END IF;

  -- Obtenemos el valor del indicador de impuestos a los productos gratis
  SELECT decode(COUNT(*), 0, 'N', 'S')
    INTO l_indicadorimpuestosgratis
    FROM seg_pais             p,
         seg_param_inter_pais i
   WHERE p.oid_pais = i.pais_oid_pais
     AND i.ind_impu_prod_grat = 1
     AND p.cod_pais = p_codigopais;

  -- Obtenemos el valor del indicador de percepciones
  SELECT decode(COUNT(*), 0, 'N', 'S')
    INTO l_indicadorpercepcion
    FROM fac_formu_perce ffp
   WHERE ffp.pais_oid_pais = l_oidpais;

  -- Obtenemos el valor de la tasa de impuesto por percepciones
  IF l_indicadorpercepcion = 'S' THEN
    BEGIN
      SELECT pti.val_tasa_impu
        INTO l_tasaimpuestopercepcion
        FROM ped_tasa_impue pti
       WHERE pti.pais_oid_pais = l_oidpais
         AND pti.val_indi_impu = 'PER'; -- Codigo Percepciones
    
    EXCEPTION
      WHEN no_data_found THEN
      
        l_tasaimpuestopercepcion := 0;
      
    END;
  END IF;

  --EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_PAQUE_DOCUM_DETAL_FACTU';
  --delete from IMP_PAQUE_DOCUM_DETAL_FACTU;

  -- Abrimos el cursor principal
  OPEN c_consolidados(l_oidperiodo,
                      l_indicadorenviolarissa,
                      l_numerolotefacturacion /*, p_oidZona*/);
  LOOP
    FETCH c_consolidados BULK COLLECT
      INTO r_consolidado LIMIT 10000; --modificado
  
    IF r_consolidado.count > 0 THEN
      FOR i IN r_consolidado.first .. r_consolidado.last
      LOOP
      
        l_flagtitulo1 := 0;
        l_flagtitulo2 := 0;
        l_flagtitulo3 := 0;
        l_flagtitulo4 := 0;
      
        lsoidcabecera := imp_prpr_seq.nextval;
      
        -- Inicio Detalle de Factura
        --l_textoActual := '<detfac3>';
        --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
      
        -- Inicio Cabecera
        --l_textoActual := l_textoActual || '<blqcab>';
        --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
      
        /*if lv_imprimefactel='S' then
          -- Ind. Impresion
          l_textoActual := l_textoActual || '<imprimefactel>' || r_consolidado(i).imprimefactel || '</imprimefactel>';
        
          --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
        end if;
        
        if lv_imprimepaqdoc='S' then
          -- Ind. Impresion
          l_textoActual := l_textoActual || '<imprimepaqdoc>' || r_consolidado(i).imprimepaqdoc || '</imprimepaqdoc>';
        
          --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
        end if;*/
      
        IF lv_imprimenuevos = 'S' THEN
        
          BEGIN
            SELECT to_char(cc.fec_inic, 'dd/mm/yyyy'),
                   to_char(cc.fec_inic, 'dd/mm/yyyy'),
                   to_char(cc.fec_inic, 'dd/mm/yyyy')
              INTO l_fechadespacho,
                   l_fechadespacho_2,
                   l_fechadespacho_3
              FROM cra_crono       cc,
                   cra_perio       cp,
                   seg_perio_corpo spc,
                   cra_activ       act
             WHERE cc.perd_oid_peri = cp.oid_peri
               AND cp.peri_oid_peri = spc.oid_peri
               AND spc.cod_peri = l_codigoperiodosiguiente
               AND cc.zzon_oid_zona =
                   (SELECT oid_zona
                      FROM zon_zona
                     WHERE cod_zona = r_consolidado(i).cod_zona)
                  
               AND cc.cact_oid_acti = act.oid_acti
               AND act.cod_acti = lv_actividaddesp;
          EXCEPTION
            WHEN OTHERS THEN
              l_fechadespacho   := trunc(SYSDATE);
              l_fechadespacho_2 := trunc(SYSDATE);
              l_fechadespacho_3 := trunc(SYSDATE);
          END;
        
          BEGIN
            SELECT to_char(cc.fec_inic, 'dd/mm/yyyy')
              INTO l_fechasigfact
              FROM cra_crono       cc,
                   cra_perio       cp,
                   seg_perio_corpo spc,
                   cra_activ       act
             WHERE cc.perd_oid_peri = cp.oid_peri
               AND cp.peri_oid_peri = spc.oid_peri
               AND spc.cod_peri = l_codigoperiodosiguiente
               AND cc.zzon_oid_zona =
                   (SELECT oid_zona
                      FROM zon_zona
                     WHERE cod_zona = r_consolidado(i).cod_zona)
                  
               AND cc.cact_oid_acti = act.oid_acti
               AND act.cod_acti = 'FA';
          EXCEPTION
            WHEN OTHERS THEN
              l_fechasigfact := trunc(SYSDATE);
          END;
        
          BEGIN
            SELECT to_char(cc.fec_inic, 'dd/mm/yyyy')
              INTO l_fechacv
              FROM cra_crono       cc,
                   cra_perio       cp,
                   seg_perio_corpo spc,
                   cra_activ       act
             WHERE cc.perd_oid_peri = cp.oid_peri
               AND cp.peri_oid_peri = spc.oid_peri
               AND spc.cod_peri = l_codigoperiodosiguiente
               AND cc.zzon_oid_zona =
                   (SELECT oid_zona
                      FROM zon_zona
                     WHERE cod_zona = r_consolidado(i).cod_zona)
               AND cc.cact_oid_acti = act.oid_acti
               AND act.cod_acti = lv_actividadconf;
          EXCEPTION
            WHEN OTHERS THEN
              l_fechacv := trunc(SYSDATE);
          END;
        
          l_fechasigfact2 := to_char(fac_pkg_proc.ped_fn_dev_dia_fact(l_codigoperiodosiguiente,
                                                                      r_consolidado(i)
                                                                      .cod_zona,
                                                                      2),
                                     'dd/mm/yyyy');
        
          l_fechasigfact3 := to_char(fac_pkg_proc.ped_fn_dev_dia_fact(l_codigoperiodosiguiente,
                                                                      r_consolidado(i)
                                                                      .cod_zona,
                                                                      3),
                                     'dd/mm/yyyy');
        
          ln_diasdesp  := fac_pkg_proc.ped_fn_obt_dias_fecha_ent(r_consolidado(i)
                                                                 .oid_soli_cabe,
                                                                 1,
                                                                 l_codigoperiodosiguiente,
                                                                 to_date(l_fechasigfact,
                                                                         'dd/mm/yyyy'));
          ln_diasdesp2 := fac_pkg_proc.ped_fn_obt_dias_fecha_ent(r_consolidado(i)
                                                                 .oid_soli_cabe,
                                                                 2,
                                                                 l_codigoperiodosiguiente,
                                                                 to_date(l_fechasigfact,
                                                                         'dd/mm/yyyy'));
          ln_diasdesp3 := fac_pkg_proc.ped_fn_obt_dias_fecha_ent(r_consolidado(i)
                                                                 .oid_soli_cabe,
                                                                 3,
                                                                 l_codigoperiodosiguiente,
                                                                 to_date(l_fechasigfact,
                                                                         'dd/mm/yyyy'));
        
          IF l_fechadespacho2 = 'S' AND ln_diasdesp > 0 THEN
            l_fechadespacho := to_char(to_date(l_fechasigfact, 'dd/mm/yyyy') +
                                       ln_diasdesp,
                                       'dd/mm/yyyy');
          END IF;
        
          IF l_fechadespacho2 = 'S' AND ln_diasdesp > 0 THEN
            l_fechadespacho_2 := to_char(to_date(l_fechasigfact2,
                                                 'dd/mm/yyyy') +
                                         ln_diasdesp2,
                                         'dd/mm/yyyy');
          END IF;
        
          IF l_fechadespacho2 = 'S' AND ln_diasdesp > 0 THEN
            l_fechadespacho_3 := to_char(to_date(l_fechasigfact3,
                                                 'dd/mm/yyyy') +
                                         ln_diasdesp3,
                                         'dd/mm/yyyy');
          END IF;
        
          -- Nombre Consultora
          /*l_textoActual := l_textoActual || '<nombres>' || r_consolidado(i).val_nom1 || ' ' || r_consolidado(i).val_nom2 || '</nombres>';
          l_textoActual := l_textoActual || '<apellidos>' || r_consolidado(i).val_ape1 || ' ' || r_consolidado(i).val_ape2 || '</apellidos>';
          l_textoActual := l_textoActual || '<nombresGZ>' || r_consolidado(i).nombresGZ || '</nombresGZ>';
          l_textoActual := l_textoActual || '<apellidosGZ>' || r_consolidado(i).apellidosGZ || '</apellidosGZ>';
          l_textoActual := l_textoActual || '<celularGZ>' || r_consolidado(i).celularGZ || '</celularGZ>';
          l_textoActual := l_textoActual || '<correoGZ>' || r_consolidado(i).correoGZ || '</correoGZ>';
          l_textoActual := l_textoActual || '<fechaCV>' || l_fechaCV || '</fechaCV>';
          l_textoActual := l_textoActual || '<fechaReparto>' || l_fechaDespacho || '</fechaReparto>';
          l_textoActual := l_textoActual || '<fechaReparto2>' || l_fechaDespacho_2 || '</fechaReparto2>';
          l_textoActual := l_textoActual || '<fechaReparto3>' || l_fechaDespacho_3 || '</fechaReparto3>';
          l_textoActual := l_textoActual || '<fechaSigFact>' || l_fechaSigFact || '</fechaSigFact>';
          l_textoActual := l_textoActual || '<fechaSigFact2>' || l_fechaSigFact2 || '</fechaSigFact2>';
          l_textoActual := l_textoActual || '<fechaSigFact3>' || l_fechaSigFact3 || '</fechaSigFact3>';
          l_textoActual := l_textoActual || '<diasDesp>' || ln_diasdesp || '</diasDesp>';
          l_textoActual := l_textoActual || '<tipo_docum>' || r_consolidado(i).tipo_docum || '</tipo_docum>';
          l_textoActual := l_textoActual || imp_fn_xml_opor_ahor(p_codigoPeriodo,l_oidPeriodo,r_consolidado(i).oid_clie);*/
        END IF;
        /*
        -- Numero Pedido
        l_textoActual := l_textoActual || '<numpedido>' || r_consolidado(i).val_nume_soli || '</numpedido>';
        --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
        
        -- Descripcion Pais
        l_textoActual := l_textoActual || '<lugar>' || r_consolidado(i).des_pais || '</lugar>';
        --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
        
        -- Dia
        l_textoActual := l_textoActual || '<dia>' || to_char(r_consolidado(i).fec_fact, 'dd') || '</dia>';
        --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
        
        -- Mes
        l_textoActual := l_textoActual || '<mes>' || to_char(r_consolidado(i).fec_fact, 'mm') || '</mes>';
        --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
        
        -- A?o
        l_textoActual := l_textoActual || '<ano>' || to_char(r_consolidado(i).fec_fact, 'yyyy') || '</ano>';
        --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
        
        -- Codigo Cliente
        l_textoActual := l_textoActual || '<codconsultora>' || r_consolidado(i).cod_clie || '</codconsultora>';
        --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
        
        -- Territorio Cliente
        l_textoActual := l_textoActual || '<territorio>';
        l_textoActual := l_textoActual || r_consolidado(i).cod_zona || '-';
        l_textoActual := l_textoActual || r_consolidado(i).cod_secc || '-';
        l_textoActual := l_textoActual || r_consolidado(i).cod_terr || '-';
        l_textoActual := l_textoActual || r_consolidado(i).num_secu_fact_diar;
        l_textoActual := l_textoActual || '</territorio>';
        --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
        
        -- Documento de Identidad
        l_textoActual := l_textoActual || '<rifci>' || r_consolidado(i).num_docu_iden || '</rifci>';
        --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
        
        -- Nombre Cliente
        l_textoActual := l_textoActual || '<nombre>';
        l_textoActual := l_textoActual || r_consolidado(i).val_nom1 || ' ';
        l_textoActual := l_textoActual || r_consolidado(i).val_nom2 || ' ';
        l_textoActual := l_textoActual || r_consolidado(i).val_ape1 || ' ';
        l_textoActual := l_textoActual || r_consolidado(i).val_ape2;
        l_textoActual := l_textoActual || '</nombre>';
        --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
        
        -- Periodo
        l_textoActual := l_textoActual || '<campana>' || p_codigoPeriodo || '</campana>';
        --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
        
        -- Fin Cabecera
        l_textoActual := l_textoActual || '</blqcab>';
        --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
        
        
        -- Inicio Detalle
        l_textoActual := '<detalle>';
        --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);*/
        -- Iniciamos los totales
        l_totalsolicitado          := 0;
        l_totalatendido            := 0;
        l_totaldescuentos          := 0;
        l_totalcatalogo            := 0;
        l_totaldescuentos          := 0;
        l_totalimpuestosgratis     := 0;
        l_totalfacturado           := 0;
        l_totalpreciocatalogo      := 0;
        l_totalpreciocatalogototal := 0;
        l_totaloportunidadahorro   := 0;
        l_oportunidadrevista       := 0;
        l_oportunidadcatalogo      := 0;
      
        /*INSERTAMOS LA CABECERA*/
        INSERT INTO imp_print_pedid_resum
          (prsp_cod_proc,
           prsp_cod_clie,
           prsp_cod_paqu,
           oid_pedi_resu,
           usu_crea,
           fec_crea,
           ind_acti)
        VALUES
          (pscodigotransaccional,
           r_consolidado(i).cod_clie,
           pscodigopaquete,
           lsoidcabecera,
           pscodigousuario,
           SYSDATE,
           1);
      
        -- Iteramos por las secciones del detalle
        OPEN c_detalle(r_consolidado(i).oid_soli_cabe,
                       l_despremioagotado,
                       l_desagotado,
                       l_desfaltliq,
                       l_desfaltanun,
                       l_desanulmotmax,
                       l_despremio,
                       l_desatrec,
                       l_desrecup,
                       l_desreemp,
                       l_desgratis,
                       l_despremiolet,
                       l_desofertnavid,
                       ln_oidestra);
        LOOP
          FETCH c_detalle BULK COLLECT
            INTO r_detalle LIMIT 10000; --modificado
          FOR k IN 0 .. 3
          LOOP
          
            -- Iniciamos los subtotales
          
            l_contadordetalles            := 0;
            l_subtotalsolicitado          := 0;
            l_subtotalatendido            := 0;
            l_subtotalcatalogo            := 0;
            l_subtotaldescuentos          := 0;
            l_subtotalfacturado           := 0;
            l_subtotalpreciocatalogo      := 0;
            l_subtotalpreciocatalogototal := 0;
            l_subtotaloportunidadahorro   := 0;
            l_oportunidadahorro           := 0;
          
            -- Mostramos el titulo
          
            IF r_detalle.count > 0 THEN
              FOR j IN r_detalle.first .. r_detalle.last
              LOOP
                IF r_detalle(j).ind_grupo = k THEN
                
                  IF k = 0 AND l_flagtitulo1 = 0 THEN
                    l_nombreseccion := l_nombreseccion1;
                    lstipofila      := '01';
                    --l_textoActual := '<txt><u>' || l_nombreSeccion1 || '</u></txt><txt><t/></txt>';
                    --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    l_flagtitulo1 := 1;
                  ELSIF k = 1 AND l_flagtitulo2 = 0 THEN
                    l_nombreseccion := l_nombreseccion2;
                    lstipofila      := '02';
                    --l_textoActual := '<txt/>';
                    --l_textoActual := l_textoActual || '<txt><u>' || l_nombreSeccion2 || '</u></txt><txt><t/></txt>';
                    --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    l_flagtitulo2 := 1;
                  ELSIF k = 2 AND l_flagtitulo3 = 0 THEN
                    l_nombreseccion := l_nombreseccion3;
                    lstipofila      := '03';
                    --l_textoActual := '<txt/>';
                    --l_textoActual := l_textoActual || '<txt><u>' || l_nombreSeccion3 || '</u></txt><txt><t/></txt>';
                    --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    l_flagtitulo3 := 1;
                  ELSIF k = 3 AND l_flagtitulo4 = 0 THEN
                    l_nombreseccion := l_nombreseccion4;
                    lstipofila      := '04';
                    --l_textoActual := '<txt/>';
                    --l_textoActual := l_textoActual || '<txt><u>' || l_nombreSeccion4 || '</u></txt><txt><t/></txt>';
                    --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    l_flagtitulo4 := 1;
                  END IF;
                  -- Calculo de valores
                  IF r_detalle(j).copa_oid_para_gene IS NOT NULL OR r_detalle(j)
                     .ictp_oid_tipo_prog IS NOT NULL THEN
                    -- Si se trata de un premio
                    l_preciounitario      := 0;
                    l_preciototal         := 0;
                    l_porcdscto           := 100;
                    l_descuento           := 0;
                    l_preciofacturado     := 0;
                    l_pagoposterior       := 0;
                    l_oportunidadahorro   := 0;
                    l_preciocatalogo      := 0;
                    l_preciocatalogototal := 0;
                  ELSE
                    IF r_detalle(j).val_prec_cata_unit_loca = 0 THEN
                      -- Si es un gratis
                      l_preciounitario       := r_detalle(j)
                                                .val_prec_cont_unit_loca;
                      l_preciototal          := r_detalle(j)
                                                .val_prec_cont_tota_loca;
                      l_porcdscto            := 100;
                      l_descuento            := r_detalle(j)
                                                .val_prec_cont_tota_loca;
                      l_preciofacturado      := 0;
                      l_totalimpuestosgratis := l_totalimpuestosgratis + r_detalle(j)
                                               .val_impo_impu_tota_loca;
                      l_pagoposterior        := 0;
                      l_oportunidadahorro    := 0;
                      l_preciocatalogo       := 0;
                      l_preciocatalogototal  := 0;
                      IF r_detalle(j).ind_grupo = 1 THEN
                        l_preciocatalogo      := nvl(r_detalle(j)
                                                     .imp_prec_publ,
                                                     0);
                        l_preciocatalogototal := nvl(r_detalle(j)
                                                     .imp_prec_publ,
                                                     0) * r_detalle(j)
                                                .num_unid_aten;
                        l_oportunidadahorro   := l_preciocatalogototal;
                        IF l_oportunidadahorro < 0 THEN
                          l_oportunidadahorro := 0;
                        END IF;
                      END IF;
                    ELSE
                      -- Producto con descuento
                      l_preciounitario  := r_detalle(j)
                                           .val_prec_cata_unit_loca;
                      l_preciototal     := r_detalle(j)
                                           .val_prec_cata_tota_loca;
                      l_porcdscto       := r_detalle(j).val_porc_desc;
                      l_descuento       := r_detalle(j)
                                           .val_impo_desc_tota_loca;
                      l_preciofacturado := r_detalle(j)
                                           .val_prec_fact_tota_loca;
                      IF r_detalle(j).ind_grupo = 0 THEN
                        l_preciocatalogo      := l_preciounitario;
                        l_preciocatalogototal := l_preciototal;
                        l_oportunidadahorro   := l_descuento;
                      ELSIF r_detalle(j).ind_grupo = 1 THEN
                        l_preciocatalogo      := nvl(r_detalle(j)
                                                     .imp_prec_publ,
                                                     0);
                        l_preciocatalogototal := nvl(r_detalle(j)
                                                     .imp_prec_publ,
                                                     0) * r_detalle(j)
                                                .num_unid_aten;
                        l_oportunidadahorro   := l_preciocatalogototal -
                                                 l_preciototal;
                        IF l_oportunidadahorro < 0 THEN
                          l_oportunidadahorro := 0;
                        END IF;
                      ELSE
                        l_preciocatalogo      := 0;
                        l_preciocatalogototal := 0;
                        l_oportunidadahorro   := 0;
                      END IF;
                    
                      -- Pago posterior (fraccionado)
                      --l_pagoPosterior := IMP_PKG_PROCE_COMPA.IMP_FN_CALCU_PAGO_POSTE(r_detalle(j).oid_soli_posi, l_codigoPeriodoSiguiente);
                    END IF;
                  END IF;
                
                  IF (orig_ante IS NULL AND r_detalle(j)
                     .val_codi_orig IS NOT NULL) OR
                     (orig_ante IS NOT NULL AND
                     orig_ante <> r_detalle(j).val_codi_orig AND r_detalle(j)
                     .val_codi_orig IS NOT NULL) THEN
                  
                    --l_textoActual := '<txt>H' || '<t/>';
                  
                    lstiporegi := 'PE';
                  
                    -- Codigo de Venta
                    lscodigoventa := r_detalle(j).val_codi_orig;
                  
                    -- Descripcion
                    lsdescripcion := r_detalle(j).des_prod;
                  
                    -- Unidades demandadas
                    lsunidadesdemandadas := r_detalle(j).num_unid_orig;
                  
                    -- Unidades atendidas
                    lsunidadesatendidas := 0;
                  
                    -- Precio unitario
                    l_preciounitario := 0;
                  
                    -- Precio total
                    l_preciototal := 0;
                  
                    -- % Descuento
                    l_porcdscto := 0;
                  
                    -- Importe Descuento
                    l_descuento := 0;
                  
                    -- Precio Catalogo
                    l_preciocatalogo := 0;
                  
                    -- Total Precio Catalogo
                    l_preciocatalogototal := 0;
                  
                    -- Oportunidad de Ahorro
                    l_oportunidadahorro := 0;
                  
                    -- Total con Descuento
                    l_preciofacturado := 0;
                  
                    -- Observaciones
                    lsobservacion := '';
                  
                  END IF;
                
                  --l_textoActual := '<txt>';
                
                  /*if r_detalle(j).val_codi_orig is not null then
                                                     l_textoActual := l_textoActual || 'D<t/>';
                                                     lsTipoRegi:='PD';
                                                  end if;
                  */
                  -- Codigo de Venta
                  lscodigoventa := r_detalle(j).val_codi_vent;
                
                  -- Descripcion
                  lsdescripcion := r_detalle(j).des_prod;
                
                  -- Unidades demandadas
                  lsunidadesdemandadas := r_detalle(j).num_unid_dema_real;
                
                  -- Unidades atendidas
                  lsunidadesatendidas := r_detalle(j).num_unid_aten;
                
                  /*-- Precio unitario
                  l_textoActual := l_textoActual || trim(to_char(l_precioUnitario, l_formatoNumerico));
                  
                  -- Precio total
                  l_textoActual := l_textoActual || trim(to_char(l_precioTotal, l_formatoNumerico));
                  
                  -- % Descuento
                  l_textoActual := l_textoActual || l_porcDscto;
                  
                  -- Importe Descuento
                  l_textoActual := l_textoActual || trim(to_char(r_detalle(j).val_prec_sin_impu_tota_loca, l_formatoNumerico));
                  
                  -- Precio Catalogo
                  l_textoActual := l_textoActual || trim(to_char(l_precioCatalogo, l_formatoNumerico));
                  
                  -- Total Precio Catalogo
                  l_textoActual := l_textoActual || trim(to_char(l_precioCatalogoTotal, l_formatoNumerico));
                  
                  -- Oportunidad de Ahorro
                  l_textoActual := l_textoActual || trim(to_char(l_oportunidadAhorro, l_formatoNumerico));
                  
                  -- Total con Descuento
                  l_textoActual := l_textoActual || trim(to_char(l_precioFacturado, l_formatoNumerico));
                  l_textoActual := l_textoActual || '<tr/>';
                  */
                  -- Observaciones
                  lsobservacion := r_detalle(j).val_obse;
                
                  l_contadordetalles            := l_contadordetalles + 1;
                  l_subtotalsolicitado          := l_subtotalsolicitado + r_detalle(j)
                                                  .num_unid_dema_real;
                  l_subtotalatendido            := l_subtotalatendido + r_detalle(j)
                                                  .num_unid_aten;
                  l_subtotalcatalogo            := l_subtotalcatalogo +
                                                   l_preciototal;
                  l_subtotaldescuentos          := l_subtotaldescuentos +
                                                   l_descuento;
                  l_subtotalfacturado           := l_subtotalfacturado +
                                                   l_preciofacturado;
                  l_subtotalpreciocatalogo      := l_subtotalpreciocatalogo +
                                                   l_preciocatalogo;
                  l_subtotalpreciocatalogototal := l_subtotalpreciocatalogototal +
                                                   l_preciocatalogototal;
                  l_subtotaloportunidadahorro   := l_subtotaloportunidadahorro +
                                                   l_oportunidadahorro;
                
                  l_totalsolicitado          := l_totalsolicitado + r_detalle(j)
                                               .num_unid_dema_real;
                  l_totalatendido            := l_totalatendido + r_detalle(j)
                                               .num_unid_aten;
                  l_totalcatalogo            := l_totalcatalogo +
                                                l_preciototal;
                  l_totaldescuentos          := l_totaldescuentos +
                                                l_descuento;
                  l_totalfacturado           := l_totalfacturado +
                                                l_preciofacturado;
                  l_totalpreciocatalogo      := l_totalpreciocatalogo +
                                                l_preciocatalogo;
                  l_totalpreciocatalogototal := l_totalpreciocatalogototal +
                                                l_preciocatalogototal;
                  l_totaloportunidadahorro   := l_totaloportunidadahorro +
                                                l_oportunidadahorro;
                
                  /*update ped_solic_posic xx set xx.val_prec_publ_unit_loca=r_detalle(j).imp_prec_publ--*r_detalle(j).num_unid_aten
                  where oid_soli_posi=r_detalle(j).oid_soli_posi;
                  
                  update ped_solic_posic xx set xx.val_prec_publ_tota_loca=l_oportunidadAhorro
                  where oid_soli_posi=r_detalle(j).oid_soli_posi;*/
                END IF;
              
                orig_ante := r_detalle(j).val_codi_orig;
                /**/
                INSERT INTO imp_print_pedid_detal
                  (prsp_cod_proc,
                   prsp_cod_clie,
                   prsp_cod_paqu,
                   oid_pedi_resu,
                   oid_pedi_detal,
                   num_orde,
                   tip_fila,
                   des_fila,
                   cod_cuv,
                   cod_sap,
                   nom_prod,
                   val_unid_soli,
                   val_unid_aten,
                   val_unid_falt,
                   mon_prec_unit,
                   mon_prec_unit_siva,
                   mon_tota_aten,
                   mon_porc_dcto,
                   mon_tota_aten_siva,
                   mon_prec_cata,
                   mon_tota_cata,
                   mon_opor_ahor,
                   mon_tota_fact,
                   val_obse,
                   val_punt,
                   usu_crea,
                   fec_crea,
                   ind_acti)
                VALUES
                  (pscodigotransaccional,
                   r_consolidado(i).cod_clie,
                   pscodigopaquete,
                   lsoidcabecera,
                   imp_prpd_seq.nextval,
                   NULL,
                   'PD',
                   NULL,
                   lscodigoventa,
                   r_detalle(j).cod_sap,
                   r_detalle(j).des_prod,
                   nvl(lsunidadesdemandadas, 0),
                   nvl(lsunidadesatendidas, 0),
                   nvl((nvl(lsunidadesdemandadas, 0) -
                       nvl(lsunidadesatendidas, 0)),
                       0),
                   l_preciounitario,
                   NULL,
                   l_preciototal,
                   l_porcdscto,
                   r_detalle(j).val_prec_sin_impu_tota_loca,
                   l_preciocatalogo,
                   l_preciocatalogototal,
                   l_oportunidadahorro,
                   l_preciofacturado,
                   substr(lsobservacion, 0, 60),
                   NULL,
                   pscodigousuario,
                   SYSDATE,
                   1);
                /**/
              
              END LOOP;
            END IF;
          
            -- Cerramos el cursor de detalles
          
            -- Subtotales
            IF l_contadordetalles > 0 THEN
              /*
              l_textoActual := '<txt><t/><u>Sub Total:</u><tr/>';
              l_textoActual := l_textoActual || l_subtotalSolicitado;
              l_textoActual := l_textoActual || '<tr/>';
              l_textoActual := l_textoActual || l_subtotalAtendido;
              l_textoActual := l_textoActual || '<tr/><tr/>';
              l_textoActual := l_textoActual || trim(to_char(l_subtotalCatalogo, l_formatoNumerico));
              l_textoActual := l_textoActual || '<tr/><tr/>';
              l_textoActual := l_textoActual || trim(to_char(l_subtotalDescuentos, l_formatoNumerico));
              l_textoActual := l_textoActual || '<tr/>';
              l_textoActual := l_textoActual || trim(to_char(l_subtotalPrecioCatalogo, l_formatoNumerico));
              l_textoActual := l_textoActual || '<tr/>';
              l_textoActual := l_textoActual || trim(to_char(l_subtotalPrecioCatalogoTotal, l_formatoNumerico));
              l_textoActual := l_textoActual || '<tr/>';
              l_textoActual := l_textoActual || trim(to_char(l_subtotaloportunidadAhorro, l_formatoNumerico));
              l_textoActual := l_textoActual || '<tr/>';
              l_textoActual := l_textoActual || trim(to_char(l_subtotalFacturado, l_formatoNumerico));
              l_textoActual := l_textoActual || '<tr/>';
              l_textoActual := l_textoActual || '</txt>';
              --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);*/
            
              IF k = 0 THEN
                l_oportunidadcatalogo := l_subtotaloportunidadahorro;
              ELSIF k = 1 THEN
                l_oportunidadrevista := l_subtotaloportunidadahorro;
              END IF;
            END IF;
          END LOOP;
        
          EXIT WHEN c_detalle%NOTFOUND;
        END LOOP;
        CLOSE c_detalle;
      
        -- Totales
      
        -- Al descuento le agregamos el redondeo
        --l_totalDescuentos := l_totalDescuentos - r_consolidado(i).val_impo_redo_loca;
        l_totalfacturado := l_totalcatalogo - l_totaldescuentos;
        /*
        l_textoActual := '<txt/><txt><t/><u>Total:</u><tr/>';
        l_textoActual := l_textoActual || l_totalSolicitado;
        l_textoActual := l_textoActual || '<tr/>';
        l_textoActual := l_textoActual || l_totalAtendido;
        l_textoActual := l_textoActual || '<tr/><tr/>';
        l_textoActual := l_textoActual || trim(to_char(l_totalCatalogo, l_formatoNumerico));
        l_textoActual := l_textoActual || '<tr/><tr/>';
        l_textoActual := l_textoActual || trim(to_char(l_totalDescuentos, l_formatoNumerico));
        l_textoActual := l_textoActual || '<tr/>';
        l_textoActual := l_textoActual || trim(to_char(l_totalPrecioCatalogo, l_formatoNumerico));
        l_textoActual := l_textoActual || '<tr/>';
        l_textoActual := l_textoActual || trim(to_char(l_totalPrecioCatalogoTotal, l_formatoNumerico));
        l_textoActual := l_textoActual || '<tr/>';
        l_textoActual := l_textoActual || trim(to_char(l_totalOportunidadAhorro, l_formatoNumerico));
        l_textoActual := l_textoActual || '<tr/>';
        l_textoActual := l_textoActual || trim(to_char(l_totalFacturado, l_formatoNumerico));
        l_textoActual := l_textoActual || '<tr/>';
        l_textoActual := l_textoActual || '</txt>';
        --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
        
        -- Fin Detalle
        l_textoActual := '</detalle>';
        
        -- Inicio Detalle1
        l_textoActual := l_textoActual || '<detalle1>';*/
      
        -- Iteramos por las secciones del detalle
        OPEN c_detalle1(r_consolidado(i).oid_soli_cabe);
        LOOP
          FETCH c_detalle1 BULK COLLECT
            INTO r_detalle1 LIMIT 10000; --modificado
        
          IF r_detalle1.count > 0 THEN
            FOR j IN r_detalle1.first .. r_detalle1.last
            LOOP
              /*l_textoActual := l_textoActual || '<txt>';*/
            
              -- Porcentaje
              l_textoactual := l_textoactual || r_detalle1(j).val_porc_desc;
              --/* l_textoActual := l_textoActual || '<t/>';*/
            
              -- Total Catalogo
              l_textoactual := l_textoactual ||
                               TRIM(to_char(r_detalle1(j)
                                            .val_prec_cata_tota_loca,
                                            l_formatonumerico));
              --/*l_textoActual := l_textoActual || '<tr/>';*/
            
              -- Total Descuento
              l_textoactual := l_textoactual ||
                               TRIM(to_char(r_detalle1(j)
                                            .val_impo_desc_tota_loca,
                                            l_formatonumerico));
              --/*l_textoActual := l_textoActual || '</txt>';*/
            --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
            END LOOP;
          END IF;
        
          EXIT WHEN c_detalle1%NOTFOUND;
        END LOOP;
        CLOSE c_detalle1;
      
        -- Fin Detalle1
        /*l_textoActual := l_textoActual || '</detalle1>';*/
        --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
        --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);
      
        -- Inicio Pie
        /*l_textoActual := l_textoActual || '<pie>';*/
        --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
        --DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);
      
        -- Obtenemos los valores faltantes
        -- Saldo a favor al momento de pasar y fue aplicado
        --l_saldoFavor := IMP_PKG_PROCE_COMPA.IMP_FN_CALCU_SALDO_FAVOR(r_consolidado(i).oid_soli_cabe);
        --l_saldoFavor := r_consolidado(i).saldoAFavor;
      
        -- Saldo anterior
        --l_saldoAnterior := IMP_PKG_PROCE_COMPA.IMP_FN_CALCU_TOTAL_PAGO_POSTE(r_consolidado(i).oid_soli_cabe, l_codigoPeriodoSiguiente, 'A');
        --l_saldoAnterior := r_consolidado(i).saldoAnterior;
      
        -- Restamos al saldo anterior el saldo a favor
        --l_saldoAnterior := l_saldoAnterior - l_saldoFavor;
      
        BEGIN
        
          SELECT to_char(cc.fec_inic, 'dd/mm/yyyy')
            INTO l_fechavencimiento
            FROM cra_crono       cc,
                 cra_perio       cp,
                 seg_perio_corpo spc,
                 cra_activ       act
           WHERE cc.perd_oid_peri = cp.oid_peri
             AND cp.peri_oid_peri = spc.oid_peri
             AND spc.cod_peri = p_codigoperiodo
             AND cc.zzon_oid_zona =
                 (SELECT oid_zona
                    FROM zon_zona
                   WHERE cod_zona = r_consolidado(i).cod_zona)
             AND cc.cact_oid_acti = act.oid_acti
             AND act.cod_acti = 'V1';
        
          SELECT to_char(cc.fec_inic, 'dd/mm/yyyy')
            INTO l_fechacv
            FROM cra_crono       cc,
                 cra_perio       cp,
                 seg_perio_corpo spc,
                 cra_activ       act
           WHERE cc.perd_oid_peri = cp.oid_peri
             AND cp.peri_oid_peri = spc.oid_peri
             AND spc.cod_peri = l_codigoperiodosiguiente
             AND cc.zzon_oid_zona =
                 (SELECT oid_zona
                    FROM zon_zona
                   WHERE cod_zona = r_consolidado(i).cod_zona)
             AND cc.cact_oid_acti = act.oid_acti
             AND act.cod_acti = 'RE';
        
        EXCEPTION
          WHEN OTHERS THEN
            l_fechavencimiento := to_char(SYSDATE, 'dd/mm/yyyy');
            l_fechacv          := to_char(SYSDATE, 'dd/mm/yyyy');
        END;
      
        -- Calculamos la percepcion
        l_percepcion := 0;
        IF l_indicadorpercepcion = 'S' THEN
          -- No tomamos en cuenta el flete para el calculo de la percepcion
          l_percepcion := round((l_totalcatalogo - l_totaldescuentos +
                                nvl(r_consolidado(i).gastos, 0)) *
                                l_tasaimpuestopercepcion / 100,
                                2);
        END IF;
      
        l_totalfactura := l_totalcatalogo - l_totaldescuentos + r_consolidado(i)
                         .val_impo_flet_tota_loca + l_percepcion +
                          nvl(r_consolidado(i).val_impo_rete_desc, 0);
      
        -- Obtenemos los Pagos Posteriores
        SELECT nvl(SUM(imp_pend), 0)
          INTO l_totalpagoposterior
          FROM ccc_movim_cuent_corri
         WHERE clie_oid_clie = r_consolidado(i).oid_clie
           AND imp_pend > 0
           AND perd_oid_peri > l_oidperiodo;
      
        SELECT COUNT(1)
          INTO l_cantidadoc
          FROM ped_solic_cabec
         WHERE clie_oid_clie = r_consolidado(i).oid_clie
           AND perd_oid_peri = l_oidperiodo
           AND ind_oc = 1
           AND ind_ts_no_conso = 1
           AND fec_fact IS NOT NULL
           AND tspa_oid_tipo_soli_pais IN
               (SELECT oid_tipo_soli_pais
                  FROM ped_tipo_solic_pais x,
                       ped_tipo_solic      y
                 WHERE x.tsol_oid_tipo_soli = y.oid_tipo_soli
                   AND y.ind_soli_nega = 0
                   AND y.ind_cons = 0);
      
        -- Obtenemos el Cargos por Familia Segura
        IF l_indicadorcargofamsegura = 'S' AND l_cantidadoc < 2 THEN
        
          SELECT nvl(SUM(mcc.imp_movi), 0)
            INTO l_cargofamsegura
            FROM ccc_movim_cuent_corri mcc,
                 ccc_proce             cp,
                 ccc_subpr             su
           WHERE mcc.clie_oid_clie = r_consolidado(i).oid_clie
             AND mcc.perd_oid_peri = l_oidperiodo
             AND mcc.subp_oid_subp_crea = su.oid_subp
             AND su.ccpr_oid_proc = cp.oid_proc
             AND cp.cod_proc = 'CCC007'
             AND su.cod_subp = 7;
        ELSE
          l_cargofamsegura := 0;
        
        END IF;
      
        /********************************************************************
        Total Factura - Pagos Posteriores + Cargo Familia Segura
        + Saldo Anterior = Total Monto a Pagar
        **********************************************************************/
        l_totalapagar := ccc_pkg_gener.ccc_fn_obtie_saldo_campa_anter(r_consolidado(i)
                                                                      .oid_clie,
                                                                      l_codigoperiodosiguiente);
      
        IF lv_indiejec = 'S' THEN
          -- Gastos Administrativos
          --l_saldoAnterior:=l_saldoAnterior-nvl(r_consolidado(i).gastos,0);
          l_totalfactura := l_totalfactura +
                            nvl(r_consolidado(i).gastos, 0);
        END IF;
      
        l_saldoanterior := l_totalapagar - l_totalfactura -
                           nvl(r_consolidado(i).gastos, 0) -
                           l_cargofamsegura + l_totalpagoposterior;
      
        -- En Peru el pie es diferente al resto de paises por las percepciones
      
        -- Total Catalogo
        --l_textoActual := l_textoActual || '<linea1>' || trim(to_char(l_totalCatalogo, l_formatoNumerico)) || '</linea1>';
        lstotalcatalogo := l_totalcatalogo;
        -- Total Descuentos (Incluimos el redondeo)
        --l_textoActual := l_textoActual || '<linea2>' || trim(to_char(l_totalDescuentos, l_formatoNumerico)) || '</linea2>';
        lstotaldescuentos := l_totaldescuentos;
        -- Total Facturado
        --l_textoActual := l_textoActual || '<linea3>' || trim(to_char(l_totalCatalogo - l_totalDescuentos+nvl(r_consolidado(i).val_impo_rete_desc,0), l_formatoNumerico)) || '</linea3>';
        lstotalfacturado := l_totalcatalogo - l_totaldescuentos +
                            nvl(r_consolidado(i).val_impo_rete_desc, 0);
        -- Flete
        --l_textoActual := l_textoActual || '<linea4>' || trim(to_char(r_consolidado(i).val_impo_flet_tota_loca, l_formatoNumerico)) || '</linea4>';
        lstotalflete := r_consolidado(i).val_impo_flet_tota_loca;
        -- Total Factura
        --l_textoActual := l_textoActual || '<linea5>' || trim(to_char(l_totalFactura, l_formatoNumerico)) || '</linea5>';
        lstotalfactura := l_totalfactura;
        -- Pagos Posteriores
        --l_textoActual := l_textoActual || '<linea6>' || trim(to_char(l_totalPagoPosterior, l_formatoNumerico)) || '</linea6>';
        lspagosposteriores := l_totalpagoposterior;
      
        -- Abono Atencion de Servicios (No se visualiza)
        --l_textoActual := l_textoActual || '<linea7>' || trim(to_char(l_cargoFamSegura, l_formatoNumerico)) || '</linea7>';
        lsabonoatencionservicios := l_cargofamsegura;
      
        -- Impuesto de productos gratis (Solo se visualiza en algunos paises)
        --l_textoActual := l_textoActual || '<linea8>' || trim(to_char(nvl(r_consolidado(i).gastos,0), l_formatoNumerico)) || '</linea8>';
        lsimpuestoproductosgratis := nvl(r_consolidado(i).gastos, 0);
      
        -- Saldo anterior
        --l_textoActual := l_textoActual || '<linea9>' || trim(to_char(l_saldoAnterior, l_formatoNumerico)) || '</linea9>';
        lssaldoanterior := l_saldoanterior;
        -- Importe Total
        --l_textoActual := l_textoActual || '<linea10>' || trim(to_char(l_totalAPagar, l_formatoNumerico)) || '</linea10>';
        lsimporteactual := l_totalapagar;
        -- Oportunidad Catalogo
        --l_textoActual := l_textoActual || '<linea11>' || trim(to_char(l_oportunidadCatalogo, l_formatoNumerico)) || '</linea11>';
        lsoportunidadcatalogo := l_oportunidadcatalogo;
        -- Oportunidad Revista
        --l_textoActual := l_textoActual || '<linea12>' || trim(to_char(l_oportunidadRevista, l_formatoNumerico)) || '</linea12>';
        lsoportunidadrevista := l_oportunidadrevista;
        -- Oportunidad Total
        --l_textoActual := l_textoActual || '<linea13>' || trim(to_char(l_oportunidadCatalogo+l_oportunidadRevista, l_formatoNumerico)) || '</linea13>';
        lsoportunidadtotal := l_oportunidadcatalogo + l_oportunidadrevista;
        -- Mensaje
        --l_textoActual := l_textoActual || '<linea14>' || trim(l_mensajeOportunidadAhorro) || '</linea14>';
        lsmensaje := l_mensajeoportunidadahorro;
        -- Fecha Vencimiento
        --l_textoActual := l_textoActual || '<linea15>' || trim(l_fechaVencimiento) || '</linea15>';
        lsfechavencimiento := l_fechavencimiento;
      
        -- Descuento Total
        --l_textoActual := l_textoActual || '<linea16>' || trim(to_char(l_totalDescuentos-nvl(r_consolidado(i).val_impo_rete_desc,0), l_formatoNumerico)) || '</linea16>';
        lsdescuentototal := l_totaldescuentos -
                            nvl(r_consolidado(i).val_impo_rete_desc, 0);
      
        -- Retencion
        --l_textoActual := l_textoActual || '<linea17>' || trim(to_char(nvl(r_consolidado(i).val_impo_rete_desc,0), l_formatoNumerico)) || '</linea17>';
        lsretencion := nvl(r_consolidado(i).val_impo_rete_desc, 0);
      
        -- Fecha CV
        --l_textoActual := l_textoActual || '<linea18>' || trim(to_char(nvl(r_consolidado(i).val_impo_rete_desc,0), l_formatoNumerico)) || '</linea18>';
        lsfechacv := nvl(r_consolidado(i).val_impo_rete_desc, 0);
      
        -- Fecha V1
        --l_textoActual := l_textoActual || '<linea19>' || trim(l_fechaCV) || '</linea19>';
        lsfechav1 := l_fechacv;
      
        l_interesflexipago  := nvl(ccc_pkg_gener.ccc_fn_obtie_inter_flexi_campa(r_consolidado(i)
                                                                                .oid_clie,
                                                                                p_codigoperiodo),
                                   0);
        l_saldoflexanterior := round(nvl(ccc_pkg_gener.ccc_fn_obtie_monto_flexi_campa(r_consolidado(i)
                                                                                      .oid_clie,
                                                                                      p_codigoperiodo),
                                         0),
                                     2);
      
        -- Saldo Flexipago
        --l_textoActual := l_textoActual || '<linea20>' || trim(to_char(l_interesFlexipago, l_formatoNumerico)) || '</linea20>';
        lssaldoflexipago := l_interesflexipago;
      
        -- Cuota Flexipago
        --l_textoActual := l_textoActual || '<linea21>' || trim(to_char(l_saldoFlexAnterior, l_formatoNumerico)) || '</linea21>';
        lscuotaflexipago := l_saldoflexanterior;
      
        /*ACTUALIZAMOS LA CABECERA*/
        UPDATE imp_print_pedid_resum
           SET val_mont_pago      = lstotalcatalogo,
               val_tabu_01b       = 0,
               val_tabu_02b       = 1,
               val_tabu_03b       = 0,
               val_tabu_04b       = 1,
               val_tabu_05b       = 1,
               val_tabu_06b       = 1,
               val_tabu_07b       = 1,
               val_tabu_08b       = 0,
               val_tabu_09b       = 1,
               val_tabu_10b       = 1,
               val_tabu_11b       = 1,
               val_tabu_12b       = 1,
               val_tabu_13b       = 0,
               val_tabu_14b       = NULL,
               val_tabu_15b       = NULL,
               val_desc_01b       = 'TOTAL VENTA CON I.V.A.',
               val_desc_02b       = '(-) Comisión Liquida',
               val_desc_03b       = 'TOTAL CON DESCUENTO',
               val_desc_04b       = '(+) Flete Transporte',
               val_desc_05b       = '(+) Interés por mora',
               val_desc_06b       = '(+) Gastos de Cobranza',
               val_desc_07b       = '(+) Intereses Flexipago Camp. Anteriores',
               val_desc_08b       = 'TOTAL FACTURA',
               val_desc_09b       = '(-) Cuotas siguiente campaña',
               val_desc_10b       = '(+) Recaudo Consorcio - Familia Protegida Paralife',
               val_desc_11b       = '(+) Deuda Anterior',
               val_desc_12b       = '(+) Cuotas Flexipago Campañas Anteriores',
               val_desc_13b       = 'TOTAL A PAGAR',
               val_desc_14b       = NULL,
               val_desc_15b       = NULL,
               val_mont_01b       = lstotalcatalogo,
               val_mont_02b       = lsdescuentototal,
               val_mont_03b       = lstotalfacturado,
               val_mont_04b       = lstotalflete,
               val_mont_05b       = NULL,
               val_mont_06b       = NULL,
               val_mont_07b       = NULL,
               val_mont_08b       = lstotalfactura,
               val_mont_09b       = lspagosposteriores,
               val_mont_10b       = NULL,
               val_mont_11b       = lssaldoanterior,
               val_mont_12b       = NULL,
               val_mont_13b       = lsimporteactual,
               val_mont_14b       = NULL,
               val_mont_15b       = NULL,
               val_desc_01a       = 'Por tus compras de productos de catálogo',
               val_desc_02a       = 'Por tus compras de productos de revista',
               val_desc_03a       = 'Total Oportunidad de Ahorro*',
               val_desc_opor_ahor = lsmensaje,
               val_desc_05a       = '(-) 10% Retencion',
               val_desc_06a       = 'Comisión Líquida',
               val_desc_07a       = lsfechavencimiento,
               val_desc_08a       = lsfechav1,
               val_mont_01a       = lsoportunidadcatalogo,
               val_mont_02a       = lsoportunidadrevista,
               val_mont_05a       = lsretencion,
               val_mont_06a       = lsdescuentototal
         WHERE prsp_cod_proc = pscodigotransaccional
           AND prsp_cod_clie = r_consolidado(i).cod_clie
           AND prsp_cod_paqu = pscodigopaquete
           AND oid_pedi_resu = lsoidcabecera;
      
        --l_correlativo := l_correlativo + 1;
      
        UPDATE ped_solic_cabec xx
           SET xx.val_gana_tota_loca = l_oportunidadcatalogo +
                                       l_oportunidadrevista
         WHERE oid_soli_cabe = r_consolidado(i).oid_soli_cabe;
      
        UPDATE imp_print_spool s
           SET mon_sald_actu = lstotalfactura,
               mon_sald_ante = l_saldoanterior,
               mon_opor_ahor = l_oportunidadahorro
         WHERE s.cod_clie = r_consolidado(i).cod_clie;
      END LOOP;
    END IF;
    EXIT WHEN c_consolidados%NOTFOUND;
  END LOOP;
  -- Cerramos el cursor
  CLOSE c_consolidados;
END imp_pr_print_pedid_cl;
PROCEDURE imp_pr_print_flexi
(
  p_codigopais          IN VARCHAR2,
  p_codigoperiodo       IN VARCHAR2,
  p_fechafacturacion    IN VARCHAR2,
  pscodigotransaccional VARCHAR2,
  pscodigopaquete       VARCHAR2,
  pscodigousuario       VARCHAR2
) IS

  lv_cod_pais        seg_pais.cod_pais%TYPE;
  lv_oid_peri        seg_perio_corpo.oid_peri%TYPE;
  lv_cod_peri        seg_perio_corpo.cod_peri%TYPE;
  lv_fec_fact        ped_solic_cabec.fec_prog_fact%TYPE;
  lv_cod_peri_adic_1 seg_perio_corpo.cod_peri%TYPE;
  lv_oid_peri_adic_1 seg_perio_corpo.oid_peri%TYPE;
  lv_cod_peri_rest_1 seg_perio_corpo.cod_peri%TYPE;
  lv_cod_peri_rest_2 seg_perio_corpo.cod_peri%TYPE;

  lv_mont_mini_paga NUMBER(12, 2);
  lv_cuot_flex_1    NUMBER(12, 2);
  lv_cuot_flex_2    NUMBER(12, 2);
  lv_mont_paga      NUMBER(12, 2);

  lv_imp_cuot_inte_fle1 NUMBER(12, 2);
  lv_imp_cuot_inte_fle2 NUMBER(12, 2);
  lv_imp_deud_tota      NUMBER(12, 2);

  ln_cuota_pend_1 NUMBER(12, 2);
  ln_cuota_pend_2 NUMBER(12, 2);
  ln_cuota_pend_3 NUMBER(12, 2);

  ln_inte_pend_1 NUMBER(12, 2);
  ln_inte_pend_2 NUMBER(12, 2);
  ln_inte_pend_3 NUMBER(12, 2);

  ln_monto_pend_1 NUMBER(12, 2);
  ln_monto_pend_2 NUMBER(12, 2);
  ln_monto_pend_3 NUMBER(12, 2);

  ln_monto_ped_flex NUMBER(12, 2);

  ln_monto_ped_tot NUMBER(12, 2);

  lv_cuota_pend_1 NUMBER(12, 2);
  lv_cuota_pend_2 NUMBER(12, 2);
  lv_cuota_pend_3 NUMBER(12, 2);
  lv_cuota_pend_4 NUMBER(12, 2);
  lv_cuota_pend_5 NUMBER(12, 2);

  lv_inte_pend_1         NUMBER(12, 2);
  lv_inte_pend_2         NUMBER(12, 2);
  lv_inte_pend_3         NUMBER(12, 2);
  lv_inte_pend_4         NUMBER(12, 2);
  lv_inte_pend_5         NUMBER(12, 2);
  lv_monto_pend_1        NUMBER(12, 2);
  lv_monto_pend_2        NUMBER(12, 2);
  lv_monto_pend_3        NUMBER(12, 2);
  lv_monto_ped_flex      NUMBER(12, 2);
  lv_monto_finan         NUMBER(12, 2);
  lv_monto_ped_tot       NUMBER(12, 2);
  lv_comportamiento      VARCHAR2(100);
  lv_tasa_tcem           NUMBER(12, 2);
  lv_tasa_tcea           NUMBER(12, 2);
  lv_imp_tasa_tcem       NUMBER(12, 2);
  lv_imp_tasa_tcea       NUMBER(12, 2);
  lv_q                   NUMBER(12, 8);
  lv_pedi_base           NUMBER(12, 2);
  lv_linea_credi         NUMBER(12, 2);
  lv_sald_tota           NUMBER(12, 2);
  lv_oid_tipo_soli_pais  NUMBER(12);
  lv_oid_tipo_soli_cons  NUMBER(12);
  lv_mens_tota           VARCHAR2(4000);
  lv_reg_flx_paque_docum flx_paque_docum%ROWTYPE;
  lv_cod_cliente         VARCHAR2(15);

  CURSOR c_fact_flex IS
    SELECT pd.cod_clie,
           pd.oid_clie,
           pd.ind_acti,
           pd.val_cuot_21di_pedi_vige,
           pd.val_sald_flex_ante,
           pd.val_sald_otro_ante,
           pd.val_sald_maxi_camp,
           pd.val_mont_mini_paga,
           pd.val_mont_cuot_fle1_proy,
           pd.val_mont_inte_fle1_proy,
           pd.val_mont_cuot_fle2_proy,
           pd.val_mont_inte_fle2_proy,
           pd.val_mont_paga_sinf
      FROM flx_gener_consu_paque_docum pd;

  r_fact_flex c_fact_flex%ROWTYPE;

BEGIN

  lv_cod_peri := p_codigoperiodo;
  lv_fec_fact := p_fechafacturacion;

  lv_cod_pais           := p_codigopais;
  lv_oid_tipo_soli_pais := fin_pkg_gener.fin_fn_obtie_oid_solic_pais('SOC');
  lv_oid_tipo_soli_cons := fin_pkg_gener.fin_fn_obtie_oid_solic_pais('C1');

  lv_oid_peri := fin_pkg_gener.fin_fn_obtie_oid_perio(lv_cod_pais,
                                                      lv_cod_peri);

  DELETE FROM flx_paque_docum;

  DELETE FROM flx_gener_consu_paque_docum;

  INSERT INTO flx_gener_consu_paque_docum
    SELECT f.cod_clie,
           f.oid_clie,
           1 ind_acti,
           f.val_cuot_21di_pedi_vige,
           f.val_sald_flex_ante,
           f.val_sald_otro_ante,
           f.val_sald_maxi_camp,
           f.val_mont_mini_paga,
           f.val_mont_cuot_fle1_proy,
           f.val_mont_inte_fle1_proy,
           f.val_mont_cuot_fle2_proy,
           f.val_mont_inte_fle2_proy,
           f.val_mont_paga_sinf
      FROM flx_gener_finan_consu_flexi f
     WHERE f.cod_peri = lv_cod_peri
       AND f.fec_fact = lv_fec_fact
       AND f.cod_moti_rech IS NULL
       AND f.cod_clie IN (SELECT cod_clie FROM imp_print_spool);

  INSERT INTO flx_gener_consu_paque_docum
    SELECT fc.cod_clie,
           fc.oid_clie,
           1           ind_acti,
           NULL        val_cuot_21di_pedi_vige,
           NULL        val_sald_flex_ante,
           NULL        val_sald_maxi_camp,
           NULL        val_sald_otro_ante,
           NULL        val_mont_mini_paga,
           NULL        val_mont_cuot_fle1_proy,
           NULL        val_mont_inte_fle1_proy,
           NULL        val_mont_cuot_fle2_proy,
           NULL        val_mont_inte_fle2_proy,
           NULL        val_mont_paga_sinf
      FROM flx_cuota_flexi_factu_cabec fc
     WHERE NOT EXISTS (SELECT NULL
              FROM flx_gener_finan_consu_flexi f
             WHERE f.cod_peri = lv_cod_peri
               AND f.fec_fact = lv_fec_fact
               AND f.cod_moti_rech IS NULL
               AND f.oid_clie = fc.oid_clie)
       AND EXISTS
     (SELECT NULL
              FROM ped_solic_cabec psc
             WHERE psc.fec_fact = lv_fec_fact
               AND psc.tspa_oid_tipo_soli_pais = lv_oid_tipo_soli_cons
               AND psc.perd_oid_peri = lv_oid_peri
               AND psc.clie_oid_clie = fc.oid_clie)
       AND fc.cod_clie IN (SELECT cod_clie FROM imp_print_spool)
     GROUP BY fc.cod_clie,
              fc.oid_clie;

  lv_cod_peri_rest_1 := fin_pkg_gener.fin_fn_obtie_nsgte_campa(lv_cod_peri,
                                                               -1);

  lv_cod_peri_rest_2 := fin_pkg_gener.fin_fn_obtie_nsgte_campa(lv_cod_peri,
                                                               -2);

  lv_cod_peri_adic_1 := fin_pkg_gener.fin_fn_obtie_nsgte_campa(lv_cod_peri,
                                                               1);
  lv_oid_peri_adic_1 := fin_pkg_gener.fin_fn_obtie_oid_perio(lv_cod_pais,
                                                             lv_cod_peri_adic_1);

  OPEN c_fact_flex;
  LOOP
    FETCH c_fact_flex
      INTO r_fact_flex;
    EXIT WHEN c_fact_flex%NOTFOUND;
  
    BEGIN
    
      IF r_fact_flex.val_mont_paga_sinf > 0 THEN
        lv_mont_paga := r_fact_flex.val_mont_paga_sinf;
      ELSE
        lv_mont_paga := ccc_pkg_gener.ccc_fn_obtie_saldo_campa_anter(r_fact_flex.oid_clie,
                                                                     lv_cod_peri_adic_1);
      END IF;
    
      lv_cod_cliente := r_fact_flex.cod_clie;
    
      lv_mont_mini_paga     := r_fact_flex.val_mont_mini_paga;
      lv_imp_cuot_inte_fle1 := r_fact_flex.val_mont_cuot_fle1_proy +
                               r_fact_flex.val_mont_inte_fle1_proy;
      lv_imp_cuot_inte_fle2 := r_fact_flex.val_mont_cuot_fle2_proy +
                               r_fact_flex.val_mont_inte_fle2_proy;
    
      lv_cuota_pend_4 := r_fact_flex.val_mont_cuot_fle1_proy;
      lv_cuota_pend_5 := r_fact_flex.val_mont_cuot_fle2_proy;
    
      lv_inte_pend_4 := r_fact_flex.val_mont_inte_fle1_proy;
      lv_inte_pend_5 := r_fact_flex.val_mont_inte_fle2_proy;
    
      lv_monto_finan := r_fact_flex.val_sald_maxi_camp;
    
      -- Inicio Cuota Pendiente 1 --
    
      BEGIN
      
        ln_cuota_pend_1 := 0;
        lv_inte_pend_1  := 0;
      
        SELECT nvl(ccc.imp_pend, 0),
               fd.val_mont_carg_uso
          INTO ln_cuota_pend_1,
               ln_inte_pend_1
          FROM flx_cuota_flexi_factu_detal fd,
               ccc_movim_cuent_corri       ccc
         WHERE fd.cod_peri = lv_cod_peri_rest_2
           AND ccc.oid_movi_cc = fd.oid_movi_carg_flex
           AND fd.oid_clie = r_fact_flex.oid_clie
           AND fd.val_nume_orde_cuot = 3;
      
      EXCEPTION
      
        WHEN OTHERS THEN
        
          ln_cuota_pend_1 := 0;
          lv_inte_pend_1  := 0;
        
      END;
    
      lv_cuota_pend_1 := ln_cuota_pend_1;
      lv_inte_pend_1  := ln_inte_pend_1;
    
      ln_monto_pend_1 := nvl(ln_cuota_pend_1, 0) + nvl(ln_inte_pend_1, 0);
      lv_monto_pend_1 := ln_monto_pend_1;
    
      -- Fin Cuota Pendiente 1 --
    
      -- Inicio Cuota Pendiente 2 --
      BEGIN
      
        ln_cuota_pend_2 := 0;
        ln_inte_pend_2  := 0;
      
        SELECT fd.val_mont_cuot_flex,
               fd.val_mont_carg_uso
          INTO ln_cuota_pend_2,
               ln_inte_pend_2
          FROM flx_cuota_flexi_factu_detal fd
         WHERE fd.cod_peri = lv_cod_peri_rest_1
           AND fd.oid_clie = r_fact_flex.oid_clie
           AND fd.val_nume_orde_cuot = 2;
      
      EXCEPTION
      
        WHEN OTHERS THEN
          ln_cuota_pend_2 := 0;
          ln_inte_pend_2  := 0;
        
      END;
    
      lv_cuota_pend_2 := ln_cuota_pend_2;
      lv_inte_pend_2  := ln_inte_pend_2;
      ln_monto_pend_2 := nvl(ln_cuota_pend_2, 0) + nvl(ln_inte_pend_2, 0);
      lv_monto_pend_2 := ln_monto_pend_2;
    
      -- Fin Cuota Pendiente 2 --
    
      -- Inicio Cuota Pendiente 3 --
      BEGIN
      
        ln_cuota_pend_3 := 0;
        ln_inte_pend_3  := 0;
      
        SELECT fd.val_mont_cuot_flex,
               fd.val_mont_carg_uso
          INTO ln_cuota_pend_3,
               ln_inte_pend_3
          FROM flx_cuota_flexi_factu_detal fd
         WHERE fd.cod_peri = lv_cod_peri_rest_1
           AND fd.oid_clie = r_fact_flex.oid_clie
           AND fd.val_nume_orde_cuot = 3;
      
      EXCEPTION
      
        WHEN OTHERS THEN
          ln_cuota_pend_3 := 0;
          ln_inte_pend_3  := 0;
        
      END;
    
      lv_cuota_pend_3 := ln_cuota_pend_3;
      lv_inte_pend_3  := ln_inte_pend_3;
      ln_monto_pend_3 := nvl(ln_cuota_pend_3, 0) + nvl(ln_inte_pend_3, 0);
      lv_monto_pend_3 := ln_monto_pend_3;
    
      -- Fin Cuota Pendiente 3 --
    
      IF nvl(r_fact_flex.val_mont_mini_paga, 0) > 0 THEN
        ln_monto_ped_flex := nvl(r_fact_flex.val_mont_mini_paga, 0) -
                             nvl(ln_monto_pend_1, 0) -
                             nvl(ln_monto_pend_2, 0);
      ELSE
        ln_monto_ped_flex := 0;
      END IF;
    
      lv_monto_ped_flex := ln_monto_ped_flex;
    
      lv_sald_tota := nvl(ccc_pkg_gener.ccc_fn_obtie_saldo_campa_anter(r_fact_flex.oid_clie,
                                                                       lv_cod_peri_adic_1),
                          0);
    
      ln_monto_ped_tot := lv_sald_tota - nvl(ln_monto_pend_1, 0) -
                          nvl(ln_monto_pend_2, 0);
    
      lv_monto_ped_tot := ln_monto_ped_tot;
    
      BEGIN
      
        SELECT b.des_cali,
               nvl(a.val_pedi_base, 0),
               nvl(a.val_line_cred, 0)
          INTO lv_comportamiento,
               lv_pedi_base,
               lv_linea_credi
          FROM flx_consu_habil_flexi a,
               flx_calif_compo       b
         WHERE a.ind_cali_comp = b.cod_cali
           AND a.cod_clie = r_fact_flex.cod_clie
           AND a.cod_peri_fact = lv_cod_peri
           AND rownum = 1;
      
      EXCEPTION
        WHEN no_data_found THEN
          lv_comportamiento := 'Regular';
          lv_pedi_base      := 0;
          lv_linea_credi    := 0;
      END;
    
      BEGIN
      
        lv_q := flx_pkg_proce.flx_fn_obtie_q(r_fact_flex.oid_clie,
                                             lv_cod_peri_rest_1,
                                             lv_cod_peri_rest_2);
      
        IF lv_q IS NOT NULL AND lv_q < 1 THEN
        
          flx_pkg_proce.flx_pr_obtie_valor_tasas(lv_q,
                                                 lv_imp_tasa_tcea,
                                                 lv_imp_tasa_tcem);
        ELSE
          lv_imp_tasa_tcea := 0;
          lv_imp_tasa_tcem := 0;
        END IF;
      EXCEPTION
        WHEN OTHERS THEN
          lv_imp_tasa_tcea := 0;
          lv_imp_tasa_tcem := 0;
        
      END;
    
      lv_reg_flx_paque_docum.xml_cons := lv_mens_tota;
    
      lv_reg_flx_paque_docum.cod_clie := r_fact_flex.cod_clie;
    
      INSERT INTO imp_print_flexi
        (prsp_cod_proc,
         prsp_cod_clie,
         prsp_cod_paqu,
         oid_flex,
         mon_cuot_01,
         mon_cuot_02,
         cam_cuot_01,
         cam_cuot_02,
         val_tota_pago,
         val_mont_mini,
         val_tcea,
         val_tcem,
         val_cuot_pend_01,
         val_cuot_pend_02,
         val_cuot_pend_03,
         val_cuot_pend_04,
         val_cuot_pend_05,
         mon_line_cred,
         mon_inte_pend_01,
         mon_inte_pend_02,
         mon_inte_pend_03,
         mon_inte_pend_04,
         mon_inte_pend_05,
         mon_fina,
         mon_pedi_base,
         mon_pedi_tota,
         mon_pend_01,
         mon_pend_02,
         mon_pend_03,
         mon_pend_flex,
         nro_tabl_imag,
         usu_crea,
         fec_crea,
         ind_acti)
      VALUES
        (pscodigotransaccional,
         lv_cod_cliente,
         pscodigopaquete,
         imp_prfl_seq.nextval,
         nvl(lv_imp_cuot_inte_fle1, 0),
         nvl(lv_imp_cuot_inte_fle2, 0),
         substr(lv_cod_peri, 5),
         substr(lv_cod_peri_adic_1, 5),
         nvl(lv_mont_paga, 0),
         nvl(lv_mont_mini_paga, 0),
         lv_imp_tasa_tcea,
         lv_imp_tasa_tcem,
         nvl(lv_cuota_pend_1, 0),
         nvl(lv_cuota_pend_2, 0),
         nvl(lv_cuota_pend_3, 0),
         nvl(lv_cuota_pend_4, 0),
         nvl(lv_cuota_pend_5, 0),
         lv_linea_credi,
         nvl(lv_inte_pend_1, 0),
         nvl(lv_inte_pend_2, 0),
         nvl(lv_inte_pend_3, 0),
         nvl(lv_inte_pend_4, 0),
         nvl(lv_inte_pend_5, 0),
         nvl(lv_monto_finan, 0),
         lv_pedi_base,
         nvl(lv_monto_ped_tot, 0),
         nvl(lv_monto_pend_1, 0),
         nvl(lv_monto_pend_2, 0),
         nvl(lv_monto_pend_3, 0),
         nvl(lv_monto_ped_flex, 0),
         substr(lv_comportamiento, 1, 2),
         pscodigousuario,
         SYSDATE,
         1);
    
      UPDATE imp_print_spool s
         SET mon_disp_flex = lv_pedi_base,
             mon_pedm_flex = lv_linea_credi
       WHERE s.cod_clie = lv_reg_flx_paque_docum.cod_clie;
    END;
  END LOOP;
  CLOSE c_fact_flex;
END imp_pr_print_flexi;

PROCEDURE imp_pr_print_cupon_z
(
  p_codigopais       VARCHAR2,
  p_codigoperiodo    VARCHAR2,
  p_oidzona          NUMBER,
  p_fechafacturacion VARCHAR2
) IS

  CURSOR c_cupon
  (
    oidpais               NUMBER,
    oidperiodo            NUMBER,
    indicadorenviolarissa VARCHAR2,
    numerolotefacturacion NUMBER
  ) IS
    SELECT mc.oid_clie,
           mc.cod_digi_ctrl,
           mc.cod_clie,
           mc.val_ape1 || ' ' || mc.val_ape2 || ', ' || mc.val_nom1 || ' ' ||
           mc.val_nom2 nom_clie,
           stv.des_abrv_tipo_via || ' ' || mcd.val_nomb_via || ' ' ||
           mcd.num_ppal val_dir1,
           TRIM('/' FROM gen_pkg_gener.gen_fn_descr_estru_geopo(oidpais,
                                                       mc.oid_clie,
                                                       4) || '/' ||
                gen_pkg_gener.gen_fn_descr_estru_geopo(oidpais,
                                                       mc.oid_clie,
                                                       3) || '/' ||
                gen_pkg_gener.gen_fn_descr_estru_geopo(oidpais,
                                                       mc.oid_clie,
                                                       2) || '/' ||
                gen_pkg_gener.gen_fn_descr_estru_geopo(oidpais,
                                                       mc.oid_clie,
                                                       1)) val_dir2,
           zon.oid_zona,
           zon.cod_zona,
           ter.cod_terr,
           con.oid_soli_cabe,
           nvl(mc.val_recl_pend, 0) val_recl_pend
      FROM mae_clien       mc,
           mae_clien_direc mcd,
           seg_tipo_via    stv,
           ped_solic_cabec con,
           zon_zona        zon,
           zon_terri       ter
     WHERE mc.oid_clie = mcd.clie_oid_clie
       AND mcd.tivi_oid_tipo_via = stv.oid_tipo_via(+)
       AND mcd.ind_elim = 0
       AND mcd.ind_dire_ppal = 1
       AND mc.oid_clie = con.clie_oid_clie
       AND con.zzon_oid_zona = zon.oid_zona
       AND con.terr_oid_terr = ter.oid_terr
       AND con.perd_oid_peri = oidperiodo
       AND zon.oid_zona = p_oidzona
       AND con.fec_fact = to_date(p_fechafacturacion, 'dd/mm/yyyy')
       AND con.num_unid_aten_tota > 0
       AND con.ind_inte_lari_gene = indicadorenviolarissa
       AND (numerolotefacturacion IS NULL OR
           con.num_lote_fact = numerolotefacturacion)
       AND EXISTS
     (SELECT NULL
              FROM int_lar_tipo_solici_pedido_dis l
             WHERE l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais);

  r_cupon c_cupon%ROWTYPE;

  -- Variables locales
  l_oidpais                    NUMBER;
  l_oidperiodo                 NUMBER;
  l_oidperiodosgte             NUMBER;
  l_codperiodosgte             VARCHAR2(6);
  l_oidcanal                   NUMBER;
  l_oidmarca                   NUMBER;
  l_oidactividad               NUMBER;
  l_fechavencimiento           DATE;
  l_codigoperiodo              VARCHAR2(25);
  l_saldocupon                 NUMBER(12, 2) := 0.00;
  l_correlativo                NUMBER := 1;
  l_indicadorenviolarissa      VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                             'indicadorEnvioLarissa');
  l_indicadorenvioultimolote   VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                             'indicadorEnvioUltimoLote    ');
  l_codigoactividadvencimiento VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                             'codigoActividadVencimiento');
  l_numerolotefacturacion      NUMBER;
  l_clob                       CLOB;
  l_textoactual                VARCHAR2(1000) := '';
  l_digiver                    VARCHAR2(100) := imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                             'digiVer');
  l_redondeo                   VARCHAR2(100) := nvl(imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                                 'IndRedondeo'),
                                                    'N');
  l_recpend                    VARCHAR2(100) := nvl(imp_pkg_proce_gener.imp_fn_obtie_param_impre('LAS',
                                                                                                 'IndRecPendCupon'),
                                                    'N');

BEGIN

  -- Obtenemos los OIDs necesarios
  l_oidpais       := gen_pkg_gener.gen_fn_devuelve_id_pais(p_codigopais);
  l_oidcanal      := gen_pkg_gener.gen_fn_devuelve_id_canal(codigo_canal);
  l_oidmarca      := gen_pkg_gener.gen_fn_devuelve_id_marca(codigo_marca);
  l_oidperiodo    := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(p_codigoperiodo,
                                                                l_oidmarca,
                                                                l_oidcanal);
  l_codigoperiodo := substr(p_codigoperiodo, 5, 2) || '/' ||
                     substr(p_codigoperiodo, 1, 4);

  IF (l_indicadorenvioultimolote = '1' OR l_indicadorenvioultimolote = 'S') THEN
  
    BEGIN
      SELECT MAX(cons.num_lote_fact)
        INTO l_numerolotefacturacion
        FROM ped_solic_cabec                cons,
             int_lar_tipo_solici_pedido_dis tspd
       WHERE cons.perd_oid_peri = l_oidperiodo
         AND cons.fec_fact = to_date(p_fechafacturacion, 'dd/mm/yyyy')
         AND cons.ind_ts_no_conso = 0
         AND (cons.ind_pedi_prue = 0 OR cons.ind_pedi_prue IS NULL)
         AND cons.tspa_oid_tipo_soli_pais = tspd.tspa_oid_tipo_soli_pais
         AND cons.pais_oid_pais = l_oidpais;
    EXCEPTION
      WHEN OTHERS THEN
        l_numerolotefacturacion := NULL;
    END;
  
  END IF;

  -- Obtenemos el OID del periodo siguiente
  l_codperiodosgte := gen_fn_calcu_perio(p_codigoperiodo, 1);
  l_oidperiodosgte := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(l_codperiodosgte,
                                                                 l_oidmarca,
                                                                 l_oidcanal);

  -- Obtenemos el OID de la actividad
  SELECT act.oid_acti
    INTO l_oidactividad
    FROM cra_activ act
   WHERE act.pais_oid_pais = l_oidpais
     AND act.marc_oid_marc = l_oidmarca
     AND act.cana_oid_cana = l_oidcanal
     AND act.cod_acti = l_codigoactividadvencimiento; -- Cupon de Vencimiento

  -- Elimina todos las filas de la Tabla IMP_PAQUE_DOCUM_CUPON
  --EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_PAQUE_DOCUM_CUPON';

  OPEN c_cupon(l_oidpais,
               l_oidperiodo,
               l_indicadorenviolarissa,
               l_numerolotefacturacion);
  LOOP
    FETCH c_cupon
      INTO r_cupon;
    EXIT WHEN c_cupon%NOTFOUND;
    BEGIN
    
      INSERT INTO imp_paque_docum_cupon
      VALUES
        (r_cupon.oid_soli_cabe,
         empty_clob(),
         r_cupon.cod_clie)
      RETURNING xml_cons INTO l_clob;
    
      -- Obtenemos la fecha de vencimiento de la zona del cliente
      BEGIN
        SELECT cro.fec_inic
          INTO l_fechavencimiento
          FROM cra_crono cro
         WHERE cro.zzon_oid_zona = r_cupon.oid_zona
           AND cro.perd_oid_peri = l_oidperiodosgte
           AND cro.cact_oid_acti = l_oidactividad;
      EXCEPTION
        WHEN no_data_found THEN
          l_fechavencimiento := trunc(SYSDATE);
      END;
    
      -- Obtenemos el valor del saldo para el cliente
      --l_saldoCupon := CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_VENCI(r_cupon.oid_clie, l_fechaVencimiento);
      l_saldocupon := ccc_pkg_gener.ccc_fn_obtie_saldo_campa_anter(r_cupon.oid_clie,
                                                                   l_codperiodosgte);
    
      IF l_saldocupon < 0 THEN
        l_saldocupon := 0;
      END IF;
    
      IF l_redondeo = 'S' THEN
        l_saldocupon := round(l_saldocupon / 100, 0) * 100;
      END IF;
    
      IF l_recpend = 'S' THEN
        l_saldocupon := l_saldocupon - r_cupon.val_recl_pend;
      END IF;
    
      /*
                  -- Inicio Cupon
                  l_textoActual := '<frmecc>';
                  DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
      */
      -- Inicio cabecera
      l_textoactual := '<cab>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      -- Codigo Cliente
      l_textoactual := '<ccon>' || r_cupon.cod_clie || '</ccon>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      -- Codigo Verificador
      IF nvl(l_digiver, 'N') = 'S' THEN
        l_textoactual := '<dver>' || r_cupon.cod_digi_ctrl || '</dver>';
        dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
      END IF;
    
      -- Nombre Cliente
      l_textoactual := '<ncon>' || r_cupon.nom_clie || '</ncon>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      -- Zona
      l_textoactual := '<czon>' || r_cupon.cod_zona || '</czon>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      -- Periodo
      l_textoactual := '<fcam>' || l_codigoperiodo || '</fcam>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      -- Territorio
      l_textoactual := '<cter>' || r_cupon.cod_terr || '</cter>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      -- Direccion 1
      l_textoactual := '<dir1>' || r_cupon.val_dir1 || '</dir1>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      -- Direccion 2
      l_textoactual := '<dir2>' || r_cupon.val_dir2 || '</dir2>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      -- Fecha
      l_textoactual := '<fec>' || p_fechafacturacion || '</fec>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      -- Fin Cabecera
      l_textoactual := '</cab>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      -- Inicio Bloque Cupon
      l_textoactual := '<blqcp>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      -- Codigo Cliente
      l_textoactual := '<ccon>' || r_cupon.cod_clie || '</ccon>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      -- Nombre Cliente
      l_textoactual := '<ncon>' || r_cupon.nom_clie || '</ncon>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      -- Zona
      l_textoactual := '<czon>' || r_cupon.cod_zona || '</czon>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      -- Territorio
      l_textoactual := '<cter>' || r_cupon.cod_terr || '</cter>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      -- Saldo
      l_textoactual := '<saldo>' || l_saldocupon || '</saldo>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      -- Fecha Facturacion
      l_textoactual := '<fec>' || p_fechafacturacion || '</fec>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      -- Fecha Vencimiento
      l_textoactual := '<fecven>' ||
                       to_char(l_fechavencimiento, 'dd/mm/yyyy') ||
                       '</fecven>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      -- Fin Bloque Cupon
      l_textoactual := '</blqcp>';
      dbms_lob.writeappend(l_clob, length(l_textoactual), l_textoactual);
    
      /*            -- Detalle Vacio
                  l_textoActual := '<detalle><txt><t/><tr/><tr/><tr/></txt><txt><t/><tr/><tr/><tr/></txt></detalle>';
                  DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
      
                  -- Fin Cupon
                  l_textoActual := '</frmecc>';
                  DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
      */
      l_correlativo := l_correlativo + 1;
    
    END;
  END LOOP;
  CLOSE c_cupon;

END imp_pr_print_cupon_z;
PROCEDURE imp_pr_print_ccc
(
  p_codigopais       IN VARCHAR2,
  p_codigoperiodo    IN VARCHAR2,
  p_fechafacturacion IN VARCHAR2,
  pnoidzona IN NUMBER,
  pscodigotransaccional VARCHAR2,
  pscodigopaquete       VARCHAR2,
  pscodigousuario       VARCHAR2
) IS

  CURSOR c_clientes(oidperiodo NUMBER) IS
    SELECT DISTINCT mc.oid_clie,
                    mc.cod_clie
      FROM ped_solic_cabec     con,
           mae_clien           mc,
           ped_tipo_solic_pais ptsp,
           ped_tipo_solic      pts,
           seg_pais
     WHERE mc.oid_clie = con.clie_oid_clie
       AND con.fec_fact = to_date(p_fechafacturacion, 'dd/mm/yyyy')
       AND con.perd_oid_peri = oidperiodo
       AND CON.Zzon_Oid_Zona=pnoidzona
       AND seg_pais.cod_pais = p_codigopais
       AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
       AND con.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
       AND con.num_unid_aten_tota > 0
       AND pts.cod_tipo_soli = 'C1' -- Consolidado O/C Consultora
     ORDER BY mc.cod_clie;

  TYPE clienterecord IS RECORD(
    oid_clie mae_clien.oid_clie%TYPE,
    cod_clie mae_clien.cod_clie%TYPE);

  TYPE clientetype IS TABLE OF clienterecord;
  r_cliente clientetype;

  CURSOR c_ctacte
  (
    oidcliente     NUMBER,
    numerodetalles NUMBER
  ) IS
    SELECT *
      FROM (SELECT fec_emis,
                   cam_refe,
                   tip_movi,
                   num_docu,
                   fec_venc,
                   fec_pago,
                   imp_carg,
                   imp_abon,
                   imp_movi
              FROM (SELECT fec_emis,
                           cam_refe,
                           tip_movi,
                           num_docu,
                           fec_venc,
                           fec_pago,
                           imp_carg,
                           imp_abon,
                           decode(imp_carg, NULL, imp_abon, -1 * imp_carg) imp_movi
                      FROM (SELECT mcc.fec_docu fec_emis,
                                   nvl((SELECT z.cod_peri
                                         FROM ped_solic_cabec x,
                                              cra_perio       y,
                                              seg_perio_corpo z
                                        WHERE x.perd_oid_peri = y.oid_peri
                                          AND y.peri_oid_peri = z.oid_peri
                                          AND x.oid_soli_cabe =
                                              mcc.soca_oid_soli_cabe),
                                       spc.cod_peri) cam_refe,
                                   CASE
                                     WHEN mcc.tcab_oid_tcab_crea = 2001 THEN
                                      CASE
                                        WHEN EXISTS
                                         (SELECT oid_soli_cabe
                                                FROM ped_solic_cabec     p,
                                                     ped_tipo_solic_pais tp,
                                                     ped_tipo_solic      t
                                               WHERE p.soca_oid_soli_cabe =
                                                     mcc.soca_oid_soli_cabe
                                                 AND p.tspa_oid_tipo_soli_pais =
                                                     tp.oid_tipo_soli_pais
                                                 AND tp.tsol_oid_tipo_soli =
                                                     t.oid_tipo_soli
                                                 AND p.ind_oc = 1
                                                 AND t.ind_soli_nega = 0) THEN
                                         'Pedido'
                                        ELSE
                                         CASE
                                           WHEN mcc.imp_movi > 0 THEN
                                            'Atencion de Servicio'
                                           ELSE
                                            nvl((SELECT 'C' || periocorpo.cod_peri || ' ' ||
                                                       opera.val_desc_larg || ' NRO.' ||
                                                       cabecrecla.num_recl AS descrip
                                                  FROM ped_solic_cabec cons,
                                                       ped_solic_cabec solicrecla,
                                                       ped_solic_cabec solicorigen,
                                                       rec_solic_opera solicopera,
                                                       rec_opera_recla operarecla,
                                                       rec_cabec_recla cabecrecla,
                                                       rec_tipos_opera tiposopera,
                                                       rec_opera       opera,
                                                       cra_perio       perio,
                                                       seg_perio_corpo periocorpo
                                                 WHERE cons.oid_soli_cabe =
                                                       mcc.soca_oid_soli_cabe
                                                   AND cons.oid_soli_cabe =
                                                       solicrecla.soca_oid_soli_cabe
                                                   AND solicrecla.soca_oid_docu_refe IS NOT NULL
                                                   AND solicrecla.oid_soli_cabe =
                                                       solicopera.soca_oid_soli_cabe
                                                   AND solicopera.opre_oid_oper_recl =
                                                       operarecla.oid_oper_recl
                                                   AND operarecla.care_oid_cabe_recl =
                                                       cabecrecla.oid_cabe_recl
                                                   AND operarecla.tiop_oid_tipo_oper =
                                                       tiposopera.oid_tipo_oper
                                                   AND tiposopera.rope_oid_oper =
                                                       opera.oid_oper
                                                   AND solicrecla.soca_oid_docu_refe =
                                                       solicorigen.oid_soli_cabe
                                                   AND solicorigen.perd_oid_peri =
                                                       perio.oid_peri
                                                   AND perio.peri_oid_peri =
                                                       periocorpo.oid_peri
                                                   AND rownum = 1),
                                                'CDR')
                                         END
                                      END
                                     ELSE
                                      gen.val_i18n
                                   END tip_movi,
                                   mcc.soca_oid_soli_cabe soca_oid_soli_cabe,
                                   mcc.num_iden_cuot num_docu,
                                   mcc.fec_venc fec_venc,
                                   NULL fec_pago,
                                   CASE
                                     WHEN (mcc.imp_movi > 0) THEN
                                      mcc.imp_movi
                                     ELSE
                                      NULL
                                   END imp_carg,
                                   CASE
                                     WHEN (mcc.imp_movi > 0) THEN
                                      NULL
                                     ELSE
                                      abs(mcc.imp_movi)
                                   END imp_abon
                              FROM ccc_movim_cuent_corri mcc,
                                   ccc_subpr             cs,
                                   ccc_tipo_abono_subpr  tas,
                                   ccc_tipo_cargo_abono  tca,
                                   gen_i18n_sicc_pais    gen,
                                   cra_perio             cp,
                                   seg_perio_corpo       spc
                             WHERE mcc.subp_oid_subp_crea = cs.oid_subp
                               AND cs.oid_subp = tas.subp_oid_subp
                               AND tas.tcab_oid_tcab = tca.oid_tipo_carg_abon
                               AND gen.attr_enti LIKE 'CCC_TIPO_CARGO_ABONO'
                               AND gen.val_oid = tca.oid_tipo_carg_abon
                               AND mcc.perd_oid_peri = cp.oid_peri
                               AND cp.peri_oid_peri = spc.oid_peri
                               AND mcc.clie_oid_clie = oidcliente
                            /*AND ROWNUM = 1*/
                            UNION ALL
                            SELECT mb.fec_proc      fec_emis,
                                   NULL             oid_peri,
                                   ccb.des_cc       tip_movi,
                                   mb.oid_movi_banc soca_oid_soli_cabe,
                                   mb.num_lote      num_docu,
                                   NULL             fec_venc,
                                   mb.fec_pago      fec_pago,
                                   NULL             imp_carg,
                                   mb.imp_pago      imp_abon
                              FROM ccc_movim_banca       mb,
                                   ccc_cuent_corri_banca ccb
                             WHERE mb.ccba_oid_cc_banc =
                                   ccb.oid_cuen_corr_banc
                               AND mb.cod_iden_proc = 'P'
                               AND mb.clie_oid_clie = oidcliente)
                     WHERE fec_emis <=
                           to_date(p_fechafacturacion, 'dd/mm/yyyy')
                     ORDER BY 1 DESC) x
             WHERE rownum <= numerodetalles)
     ORDER BY 1 ASC;

  TYPE ctacterecord IS RECORD(
    fec_movi DATE,
    cam_refe VARCHAR2(6),
    tip_movi VARCHAR2(100),
    num_docu NUMBER,
    fec_venc DATE,
    fec_pago DATE,
    imp_carg NUMBER(12, 2),
    imp_abon NUMBER(12, 2),
    imp_movi NUMBER(12, 2));

  TYPE ctactetype IS TABLE OF ctacterecord;
  r_ctacte ctactetype;

  codigo_canal VARCHAR2(10) := 'VD';
  codigo_marca VARCHAR2(10) := 'T';

  -- Variables locales
  l_oidpais    NUMBER;
  l_oidperiodo NUMBER;
  l_oidcanal   NUMBER;
  l_oidmarca   NUMBER;

  l_clob        CLOB;
  l_textoactual VARCHAR2(20000) := '';
  --l_formatoNumerico           VARCHAR2(100) := '9999999G990D00';
  l_numerodetalles NUMBER := 5;
  l_saldoactual    NUMBER(12, 2) := 0;

BEGIN

  -- Obtenemos el OID del periodo
  l_oidpais    := gen_pkg_gener.gen_fn_devuelve_id_pais(p_codigopais);
  l_oidcanal   := gen_pkg_gener.gen_fn_devuelve_id_canal(codigo_canal);
  l_oidmarca   := gen_pkg_gener.gen_fn_devuelve_id_marca(codigo_marca);
  l_oidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(p_codigoperiodo,
                                                             l_oidmarca,
                                                             l_oidcanal);

  /*IMP_PR_GENER_PAQUE_DOCUM_CUPON(p_codigoPais,
  p_codigoPeriodo,
  p_fechaFacturacion);*/

  -- Abrimos el cursor principal
  OPEN c_clientes(l_oidperiodo);
  LOOP
    FETCH c_clientes BULK COLLECT
      INTO r_cliente LIMIT w_filas;
  
    IF r_cliente.count > 0 THEN
      FOR i IN r_cliente.first .. r_cliente.last
      LOOP
      
        -- Obtenemos el saldo actual
        l_saldoactual := imp_pkg_proce_compa.imp_fn_calcu_saldo_inici(r_cliente(i)
                                                                      .oid_clie,
                                                                      l_numerodetalles,
                                                                      p_fechafacturacion);
      
        INSERT INTO imp_print_estad_cuent
          (prsp_cod_proc,
           prsp_cod_clie,
           prsp_cod_paqu,
           val_sald_ante,
           val_sald_venc,
           usu_crea,
           fec_crea,
           ind_acti)
        VALUES
          (pscodigotransaccional,
           r_cliente(i).cod_clie,
           pscodigopaquete,
           l_saldoactual,
           l_saldoactual,
           pscodigousuario,
           SYSDATE,
           1);
      
        OPEN c_ctacte(r_cliente(i).oid_clie, l_numerodetalles);
        LOOP
          FETCH c_ctacte BULK COLLECT
            INTO r_ctacte LIMIT w_filas;
        
          IF r_ctacte.count > 0 THEN
            FOR j IN r_ctacte.first .. r_ctacte.last
            LOOP
            
              INSERT INTO imp_print_estad_cuent_detal
                (prsp_cod_proc,
                 prsp_cod_clie,
                 prsp_cod_paqu,
                 oid_esta_deta,
                 --   cod_tabl,
                 cod_tipo_fila,
                 val_colu_01,
                 val_colu_02,
                 val_colu_03,
                 val_colu_04,
                 val_colu_05,
                 val_colu_06,
                 val_colu_07,
                 val_colu_08,
                 val_colu_09,
                 val_colu_10,
                 val_colu_11,
                 val_colu_12,
                 usu_crea,
                 fec_crea,
                 ind_acti)
              VALUES
                (pscodigotransaccional,
                 r_cliente(i).cod_clie,
                 pscodigopaquete,
                 imp_pecd_seq.nextval,
                 --  NULL,
                 'D',
                 r_ctacte(j).fec_movi,
                 r_ctacte(j).cam_refe,
                 r_ctacte(j).tip_movi,
                 r_ctacte(j).fec_venc,
                 r_ctacte(j).fec_pago,
                 r_ctacte(j).imp_carg,
                 r_ctacte(j).imp_abon,
                 l_saldoactual - r_ctacte(j).imp_movi,
                 NULL,
                 NULL,
                 NULL,
                 NULL,
                 pscodigousuario,
                 SYSDATE,
                 1);
            
            END LOOP;
          END IF;
          EXIT WHEN c_ctacte%NOTFOUND;
        END LOOP;
      
        -- Cerramos el cursor de detalles
        CLOSE c_ctacte;
      
      END LOOP;
    
    END IF;
  
    EXIT WHEN c_clientes%NOTFOUND;
  END LOOP;
  -- Cerramos el cursor
  CLOSE c_clientes;

END imp_pr_print_ccc;
PROCEDURE imp_pr_print_picad(
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  pscodigoproceso VARCHAR2,
  pscodigoPaquete  VARCHAR2,
  psusuario VARCHAR2
)
IS

  CURSOR c_consultoras IS
  SELECT
    y.cod_proc, 
    y.cod_paqu,
    x.cod_clie, 
    x.cod_regi, 
    x.cod_zona, 
    x.lin_arma, 
    x.tot_caja, 
    x.tip_serv, 
    x.num_pedi, 
    x.fec_fact, 
    x.cam_fact, 
    x.ind_cheq, 
    x.cen_dist, 
    x.usu_crea, 
    x.fec_crea
  FROM imp_tmp_print_picad x,
       imp_print_spool y
  WHERE y.COD_PROC = pscodigoproceso
    AND y.COD_PAQU = pscodigoPaquete
    AND y.COD_CAMP = psCodigoPeriodo
    AND y.FEC_FACT = to_date(psFechaFacturacion, 'dd/mm/yyyy')
    AND y.COD_CLIE = x.COD_CLIE
  --WHERE x.cod_clie = '0666245'
  ;
  
  TYPE consultorasTab         IS TABLE OF c_consultoras%ROWTYPE ;
  consultoraRecord            consultorasTab;
  
  CURSOR c_detalle(vsNumPedi VARCHAR2) IS
  SELECT 
    tip_serv, 
    num_pedi, 
    val_anaq, 
    nom_prod, 
    uni_emba, 
    num_caja, 
    tip_caja, 
    ord_lipi, 
    lin_emba, 
    est_trab, 
    cod_sap, 
    usu_crea, 
    fec_crea
  FROM imp_tmp_print_picad_detal x
  WHERE num_pedi = vsNumPedi
  ORDER BY num_caja, est_trab, ord_lipi;
  
  TYPE detalleTab             IS TABLE OF c_detalle%ROWTYPE ;
  detalleRecord               detalleTab;
  
  CURSOR c_intermedio IS
  SELECT 
    cod_clie, 
    num_pedi, 
    tip_serv, 
    tip_caja, 
    lin_emba, 
    num_caja, 
    num_pagi, 
    ord_regi, 
    ind_colu, 
    est_trab, 
    val_anaq, 
    nom_prod, 
    cod_sap, 
    uni_emba, 
    ind_regi
  FROM IMP_GTT_PRINT_PICAD_DETAL x
  ORDER BY x.num_caja, x.num_pagi, x.ind_colu, x.ord_regi;
  
  TYPE intermedioTab          IS TABLE OF c_intermedio%ROWTYPE ;
  intermedioRecord               intermedioTab;
  
  W_FILAS                     NUMBER := 5000 ;
  lnTotalCajas                imp_tmp_print_picad.tot_caja%TYPE;
  lnCajaActual                imp_tmp_print_picad_detal.num_caja%TYPE;
  lnCajaRegistro              imp_tmp_print_picad_detal.num_caja%TYPE;
  lsEstacionActual            imp_tmp_print_picad_detal.est_trab%TYPE;
  lsEstacionRegistro          imp_tmp_print_picad_detal.est_trab%TYPE;
  lnMaximoRegistroxColumna    NUMBER(12) := 52; 
  lnOidRegi                   NUMBER(12);
  
  INDICADOR_PRODUCTO          VARCHAR2(1) := 'P';
  INDICADOR_BLANCO            VARCHAR2(1) := 'B';
  INDICADOR_LINEA             VARCHAR2(1) := 'L';
  INDICADOR_CONTINUA          VARCHAR2(1) := 'C';
  INDICADOR_FIN               VARCHAR2(1) := 'F';
  lsIndiccadorRegistroPar     VARCHAR2(1);
  lnPagina                    NUMBER(12);
  COLUMNA_A                   VARCHAR2(1) := 'A';
  COLUMNA_B                   VARCHAR2(1) := 'B';
  lsColumna                   VARCHAR2(1);
  lbCambiarPagina             BOOLEAN;
  lnPaginaActual              NUMBER;
  lsScript                    VARCHAR2(100);
  lnX                         NUMBER;
BEGIN
  DELETE FROM imp_print_picad;
  DELETE FROM imp_print_picad_detal;
  DELETE FROM imp_gtt_print_picad_detal;
  
  OPEN c_consultoras;
  LOOP
   FETCH c_consultoras BULK COLLECT INTO consultoraRecord LIMIT W_FILAS;
   IF consultoraRecord.COUNT > 0 THEN
      FOR x IN consultoraRecord.FIRST .. consultoraRecord.LAST LOOP
          lnX := x;
          lnTotalCajas := consultoraRecord(x).tot_caja;
          lnOidRegi := 1;
          lnPagina := 1;
          lsColumna := COLUMNA_A;
          lbCambiarPagina := FALSE;
          
          /* Obteniendo detalle de tabla temporal TMP */
          OPEN c_detalle(consultoraRecord(x).num_pedi);
          LOOP
             FETCH c_detalle BULK COLLECT INTO detalleRecord LIMIT W_FILAS;
             IF detalleRecord.COUNT > 0 THEN
                FOR y IN detalleRecord.FIRST .. detalleRecord.LAST LOOP 
                    lbCambiarPagina := FALSE;
                    IF (y = 1) THEN
                        lnCajaActual := detalleRecord(y).num_caja;
                        lsEstacionActual := detalleRecord(y).est_trab;
                        lnOidRegi := 1;
                        lnPagina := 1;
                        lsColumna := COLUMNA_A;
                        
                    END IF;   
                    
                    /* Insertando Producto */   
                    IF lnOidRegi <= lnMaximoRegistroxColumna THEN             
                        INSERT INTO IMP_GTT_PRINT_PICAD_DETAL(
                            cod_clie, 
                            num_pedi, 
                            tip_serv,
                            tip_caja, 
                            lin_emba, 
                            num_caja, 
                            NUM_PAGI,
                            ord_regi, 
                            IND_COLU,
                            est_trab, 
                            val_anaq, 
                            nom_prod, 
                            cod_sap, 
                            uni_emba, 
                            ind_regi
                            )
                        VALUES (
                            consultoraRecord(x).cod_clie,
                            consultoraRecord(x).num_pedi,
                            consultoraRecord(x).tip_serv,
                            detalleRecord(y).tip_caja,
                            detalleRecord(y).lin_emba,
                            detalleRecord(y).num_caja,
                            lnPagina,
                            lnOidRegi,
                            lsColumna,
                            detalleRecord(y).est_trab,
                            detalleRecord(y).val_anaq,
                            detalleRecord(y).nom_prod,
                            detalleRecord(y).cod_sap,
                            detalleRecord(y).uni_emba,
                            INDICADOR_PRODUCTO
                         );
                        
                        lnOidRegi := lnOidRegi + 1;
                    END IF;
                    
                    /* Verificando el Siguiente Registro */
                    IF y = detalleRecord.LAST THEN
                       INSERT INTO IMP_GTT_PRINT_PICAD_DETAL(
                                cod_clie, 
                                num_pedi, 
                                tip_serv,
                                tip_caja, 
                                lin_emba, 
                                num_caja, 
                                NUM_PAGI,
                                ord_regi, 
                                IND_COLU,
                                est_trab, 
                                val_anaq, 
                                nom_prod, 
                                cod_sap, 
                                uni_emba, 
                                ind_regi
                                )
                            VALUES (
                                consultoraRecord(x).cod_clie,
                                consultoraRecord(x).num_pedi,
                                consultoraRecord(x).tip_serv,
                                detalleRecord(y).tip_caja,
                                detalleRecord(y).lin_emba,
                                detalleRecord(y).num_caja,
                                lnPagina,
                                lnOidRegi,
                                lsColumna,
                                detalleRecord(y).est_trab,
                                NULL,
                                NULL,
                                NULL,
                                NULL,
                                INDICADOR_FIN
                             );
                    ELSE
                       
                       /* Verificando si cambio Caja */
                       IF lnCajaActual != detalleRecord(y + 1).num_caja THEN
                              
                            INSERT INTO IMP_GTT_PRINT_PICAD_DETAL(
                                cod_clie, 
                                num_pedi, 
                                tip_serv,
                                tip_caja, 
                                lin_emba, 
                                num_caja, 
                                NUM_PAGI,
                                ord_regi, 
                                IND_COLU,
                                est_trab, 
                                val_anaq, 
                                nom_prod, 
                                cod_sap, 
                                uni_emba, 
                                ind_regi
                                )
                            VALUES (
                                consultoraRecord(x).cod_clie,
                                consultoraRecord(x).num_pedi,
                                consultoraRecord(x).tip_serv,
                                detalleRecord(y).tip_caja,
                                detalleRecord(y).lin_emba,
                                detalleRecord(y).num_caja,
                                lnPagina,
                                lnOidRegi,
                                lsColumna,
                                detalleRecord(y).est_trab,
                                NULL,
                                NULL,
                                NULL,
                                NULL,
                                INDICADOR_FIN
                             );
                               
                             lnOidRegi := 1;
                             lnPagina := 1;
                             lsColumna := COLUMNA_A;
                             lnCajaActual := detalleRecord(y + 1).num_caja;
                             lsEstacionActual := detalleRecord(y + 1).est_trab;
                       ELSE
                          /* No cambio Caja */
                          lsIndiccadorRegistroPar := INDICADOR_BLANCO;
                          
                          /* Verificando si cambio Estacion de Trabajo */
                          IF lsEstacionActual != detalleRecord(y + 1).est_trab THEN
                             lsIndiccadorRegistroPar := INDICADOR_LINEA;
                             lsEstacionActual := detalleRecord(y + 1).est_trab;
                          END IF;
                          
                          IF lnOidRegi <= lnMaximoRegistroxColumna THEN        
                            
                             INSERT INTO IMP_GTT_PRINT_PICAD_DETAL(
                                cod_clie, 
                                num_pedi, 
                                tip_serv,
                                tip_caja, 
                                lin_emba, 
                                num_caja, 
                                NUM_PAGI,
                                ord_regi, 
                                IND_COLU,
                                est_trab, 
                                val_anaq, 
                                nom_prod, 
                                cod_sap, 
                                uni_emba, 
                                ind_regi
                                )
                            VALUES (
                                consultoraRecord(x).cod_clie,
                                consultoraRecord(x).num_pedi,
                                consultoraRecord(x).tip_serv,
                                detalleRecord(y).tip_caja,
                                detalleRecord(y).lin_emba,
                                detalleRecord(y).num_caja,
                                lnPagina,
                                lnOidRegi,
                                lsColumna,
                                detalleRecord(y).est_trab,
                                NULL,
                                NULL,
                                NULL,
                                NULL,
                                lsIndiccadorRegistroPar
                             );
                            lnOidRegi := lnOidRegi + 1;
                            IF lnOidRegi > lnMaximoRegistroxColumna THEN
                               lbCambiarPagina := TRUE;
                            END IF;
                            
                          ELSE
                            lbCambiarPagina := TRUE;  
                          END IF;
                          
                          /* Verificando posible cambio de pagina */
                          IF lbCambiarPagina THEN
                             lbCambiarPagina := FALSE;
                             IF lsColumna = COLUMNA_A THEN
                                lnOidRegi := 1;
                                lsColumna := COLUMNA_B;
                             ELSE
                                
                                 INSERT INTO IMP_GTT_PRINT_PICAD_DETAL(
                                    cod_clie, 
                                    num_pedi, 
                                    tip_serv,
                                    tip_caja, 
                                    lin_emba, 
                                    num_caja, 
                                    NUM_PAGI,
                                    ord_regi, 
                                    IND_COLU,
                                    est_trab, 
                                    val_anaq, 
                                    nom_prod, 
                                    cod_sap, 
                                    uni_emba, 
                                    ind_regi
                                    )
                                VALUES (
                                    consultoraRecord(x).cod_clie,
                                    consultoraRecord(x).num_pedi,
                                    consultoraRecord(x).tip_serv,
                                    detalleRecord(y).tip_caja,
                                    detalleRecord(y).lin_emba,
                                    detalleRecord(y).num_caja,
                                    lnPagina,
                                    lnOidRegi,
                                    lsColumna,
                                    detalleRecord(y).est_trab,
                                    NULL,
                                    NULL,
                                    NULL,
                                    NULL,
                                    INDICADOR_CONTINUA
                                 );
                                
                                lnOidRegi := 1;
                                lnPagina := lnPagina + 1;
                                lsColumna := COLUMNA_A;
                                
                                
                             END IF;  
                          END IF;
                      
                       END IF;
                        
                    END IF;
                 
                END LOOP;
             END IF;
             EXIT WHEN c_detalle%NOTFOUND;
          END LOOP;
          CLOSE c_detalle;
          
          /* Recorriendo Cursor Intermedio Generado previamente */
          OPEN c_intermedio;
          LOOP
             FETCH c_intermedio BULK COLLECT INTO intermedioRecord LIMIT W_FILAS;
             IF intermedioRecord.COUNT > 0 THEN
                lnCajaActual := -1;
                lnPaginaActual := -1;
                lnPagina := 0;
                FOR z IN intermedioRecord.FIRST .. intermedioRecord.LAST LOOP
                    IF lnCajaActual != intermedioRecord(z).NUM_CAJA OR lnPaginaActual != intermedioRecord(z).NUM_PAGI THEN
                       lnPagina := lnPagina + 1;
                       INSERT INTO imp_print_picad(
                          prsp_cod_proc, 
                          prsp_cod_clie, 
                          prsp_cod_paqu, 
                          num_pagi_pica, 
                          cod_zona, 
                          val_tota_pagi, 
                          val_tipo_caja, 
                          val_nume_caja, 
                          val_tota_caja, 
                          ind_cheq, 
                          val_zona_trab_01, 
                          val_zona_trab_02, 
                          val_zona_trab_03, 
                          val_line_emba, 
                          val_info_caja, 
                          usu_crea, 
                          fec_crea, 
                          usu_modi, 
                          fec_modi, 
                          ind_acti
                          )
                       VALUES (
                          consultoraRecord(x).cod_proc,
                          consultoraRecord(x).cod_clie,
                          consultoraRecord(x).cod_paqu,
                          lnPagina,
                          consultoraRecord(x).COD_ZONA,
                          NULL,
                          intermedioRecord(z).TIP_CAJA,
                          intermedioRecord(z).NUM_CAJA,
                          NULL,
                          consultoraRecord(x).IND_CHEQ,
                          NULL,
                          NULL,
                          NULL,
                          intermedioRecord(z).LIN_EMBA,
                          NULL,
                          USER,
                          SYSDATE,
                          NULL,
                          NULL,
                          '1'
                        );
                      lnCajaActual := intermedioRecord(z).NUM_CAJA;
                      lnPaginaActual := intermedioRecord(z).NUM_PAGI;
                        
                    END IF;
                    
                    lsScript := '';
                    IF intermedioRecord(z).IND_REGI = INDICADOR_LINEA THEN
                       lsScript := 'LINE';
                    END IF;
                    IF intermedioRecord(z).IND_REGI = INDICADOR_FIN THEN
                       lsScript := 'END';
                    END IF;
                    IF intermedioRecord(z).IND_REGI = INDICADOR_CONTINUA THEN
                       lsScript := 'CONTINUE';
                    END IF;
                    
                    IF intermedioRecord(z).IND_COLU = COLUMNA_A THEN
                       
                       INSERT INTO imp_print_picad_detal(
                            prsp_cod_proc, 
                            prsp_cod_clie, 
                            prsp_cod_paqu, 
                            prpi_num_pagi, 
                            oid_pica_deta, 
                            num_caja, 
                            ord_impr, 
                            val_anaq_a, 
                            val_prod_a, 
                            val_cant_a, 
                            val_esta_trab_a, 
                            val_scri_a, 
                            usu_crea, 
                            fec_crea,
                            IND_ACTI
                        )
                        VALUES (
                            consultoraRecord(x).cod_proc,
                            consultoraRecord(x).cod_clie,
                            consultoraRecord(x).cod_paqu,
                            lnPagina,
                            intermedioRecord(z).ORD_REGI,
                            intermedioRecord(z).num_caja,
                            intermedioRecord(z).NUM_PAGI,
                            intermedioRecord(z).VAL_ANAQ,
                            intermedioRecord(z).NOM_PROD,
                            intermedioRecord(z).UNI_EMBA,
                            intermedioRecord(z).EST_TRAB,
                            lsScript,
                            USER,
                            sysdate,
                            '1'
                        );
                    ELSE
                      IF intermedioRecord(z).ORD_REGI <= lnMaximoRegistroxColumna THEN
                          UPDATE imp_print_picad_detal
                          SET
                                val_anaq_b = intermedioRecord(z).VAL_ANAQ, 
                                val_prod_b = intermedioRecord(z).NOM_PROD,
                                val_cant_b = intermedioRecord(z).UNI_EMBA,
                                val_esta_trab_b = intermedioRecord(z).EST_TRAB,
                                val_scri_b = lsScript
                          WHERE prsp_cod_proc = consultoraRecord(x).cod_proc 
                            AND prsp_cod_clie = consultoraRecord(x).cod_clie
                            AND prsp_cod_paqu = consultoraRecord(x).cod_paqu
                            AND prpi_num_pagi = lnPagina
                            AND oid_pica_deta = intermedioRecord(z).ORD_REGI;
                      ELSE
                         INSERT INTO imp_print_picad_detal(
                            prsp_cod_proc, 
                            prsp_cod_clie, 
                            prsp_cod_paqu, 
                            prpi_num_pagi, 
                            oid_pica_deta, 
                            num_caja, 
                            ord_impr, 
                            val_anaq_b, 
                            val_prod_b, 
                            val_cant_b, 
                            val_esta_trab_b, 
                            val_scri_b, 
                            usu_crea, 
                            fec_crea,
                            IND_ACTI
                        )
                        VALUES (
                            consultoraRecord(x).cod_proc,
                            consultoraRecord(x).cod_clie,
                            consultoraRecord(x).cod_paqu,
                            lnPagina,
                            intermedioRecord(z).ORD_REGI,
                            intermedioRecord(z).num_caja,
                            intermedioRecord(z).NUM_PAGI,
                            intermedioRecord(z).VAL_ANAQ,
                            intermedioRecord(z).NOM_PROD,
                            intermedioRecord(z).UNI_EMBA,
                            intermedioRecord(z).EST_TRAB,
                            lsScript,
                            USER,
                            sysdate,
                            '1'
                        );
                      END IF;
                    END IF;
                    
                END LOOP;
             END IF;
             EXIT WHEN c_intermedio%NOTFOUND;
          END LOOP;
          CLOSE c_intermedio;
          
          UPDATE imp_print_picad
          SET val_tota_pagi = lnPagina,
              val_tota_caja = lnCajaActual
          WHERE prsp_cod_proc = consultoraRecord(x).cod_proc
            AND prsp_cod_clie = consultoraRecord(x).cod_clie;
          
          
          DELETE FROM IMP_GTT_PRINT_PICAD_DETAL;
          COMMIT;
      END LOOP;
    END IF;
    EXIT WHEN c_consultoras%NOTFOUND;
 END LOOP;
 CLOSE c_consultoras;
 
 RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR imp_pr_print_picad (x): '||lnX||' - '||ls_sqlerrm);
END imp_pr_print_picad;

PROCEDURE imp_pr_print_homol_picad IS

  n NUMBER := 0;
BEGIN

  DELETE imp_tmp_homol_picad;
  INSERT INTO imp_tmp_homol_picad
    SELECT s.cod_clie,
           s.num_bole_desp num_pedi,
           s.cod_clie      cod_clie_pica,
           s.num_bole_desp num_pedi_pica,
           rownum          num_line
      FROM imp_print_spool s
     WHERE s.cod_paqu = '01';
  
  FOR y IN (SELECT * FROM imp_tmp_print_picad)
  LOOP
    n := n + 1;
    UPDATE imp_tmp_homol_picad
       SET cod_clie_pica = y.cod_clie,
           num_pedi_pica = y.num_pedi
     WHERE num_line = n;
  
  END LOOP;
  
  FOR z IN (SELECT * FROM imp_tmp_homol_picad)
  LOOP
   
    UPDATE imp_tmp_print_picad
       SET cod_clie = z.cod_clie,
           num_pedi = z.num_pedi
     WHERE cod_clie = z.cod_clie_pica
     and num_pedi = z.num_pedi_pica;
    
     UPDATE imp_tmp_print_picad_detal
       SET num_pedi = z.num_pedi
       WHERE  num_pedi = z.num_pedi_pica;
  
  END LOOP;

END imp_pr_print_homol_picad;
END IMP_PKG_PROCE_IMPRE;
/
