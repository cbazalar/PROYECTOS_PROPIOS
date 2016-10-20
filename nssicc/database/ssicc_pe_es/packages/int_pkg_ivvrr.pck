CREATE OR REPLACE PACKAGE "INT_PKG_IVVRR" IS
  /* Declaracion de variables */
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(1500);
  w_filas    NUMBER := 1000;
  /***************************************************************************
  Descripcion       : Genera Interfase de Enviar Maestro de Clientes de IVR
  Fecha Creacion    : 21/03/2007
  Autor             : Marco Agurto
  ***************************************************************************/
  PROCEDURE int_pr_ivr_envi_maes_clie
  (
    pscodigopais        VARCHAR2,
    pscodigosistema     VARCHAR2,
    pscodigointerfaz    VARCHAR2,
    psnombrearchivo     VARCHAR2,
    psnumerolote        VARCHAR2,
    pscodigotipocliente VARCHAR2,
    pscodigotipobloqueo VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Genera Interfase de Enviar Concursos de IVR
  Fecha Creacion    : 29/03/2007
  Autor             : Marco Agurto
  ***************************************************************************/
  PROCEDURE int_pr_ivr_envi_concu
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Genera Interfase de Enviar Bases Incumplidas de IVR
  Fecha Creacion    : 29/03/2007
  Autor             : Marco Agurto
  ***************************************************************************/
  PROCEDURE int_pr_ivr_base_incu
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfase de Enviar Niveles Sin controls de Campa¿a de IVR
  Fecha Creacion    : 29/03/2007
  Autor             : Marco Agurto
  ***************************************************************************/
  PROCEDURE int_pr_ivr_nive_conc
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfase de Enviar Maestro de Clientes de IVR
  Fecha Creacion    : 21/03/2007
  Autor             : Marco Agurto
  ***************************************************************************/
  PROCEDURE int_pr_ivr_envi_prem_conc
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz Emviar Tabla de Clientes de IVR dendiendo
                      de psIndProceso:
                      Si psIndProceso='T' se borran los datos de la tabla
                      int_ivr_clien y se cargan todos los datos
                      Si psIndProceso='N' se cargan solo los datos modificados
  Fecha Creacion    : 09/02/2008
  Autor             : José A. Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_ivr_envi_tabla_clien
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigomarca    VARCHAR2,
    pscodigocanal    VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    psindproceso     VARCHAR2,
    psnumerolote     VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz Emviar Tabla de Concursos de IVR dendiendo
                      de psIndProceso:
                      Si psIndProceso='T' se borran los datos de la tabla
                      int_ivr_clien y se cargan todos los datos
                      Si psIndProceso='N' se cargan solo los datos modificados
  Fecha Creacion    : 09/02/2008
  Autor             : José A. Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_ivr_envi_tabla_concu
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    psindproceso     VARCHAR2,
    psnumerolote     VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz Emviar Tabla de Motivos de Rechazo a IVR
  Fecha Creacion    : 09/02/2008
  Autor             : José A. Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_ivr_envi_motiv_recha
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigoiso      VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz Emviar Tabla de Control de IVR
  Fecha Creacion    : 09/02/2008
  Autor             : José A. Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_ivr_envi_tabla_ctrl
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

  /**************************************************************************
  Descripcion       : Obtiene el nombre de Archivo IVR
  Fecha Creacion    : 17/01/2008
  Parametros Entrada:

  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION int_fn_devue_nomb_arch_ivr
  (
    pscodigotabla VARCHAR2,
    pscodigopais  VARCHAR2,
    psinfoadic    VARCHAR2
  ) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion       : Obtiene el indicador de marca de situación, indicador
                      de incobrable:
                      (I) Incobrable
                      (N) Normal

  Fecha Creacion    : 11/02/2008
  Parametros Entrada:

  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION int_fn_devue_marca_situa(pnoidcliente NUMBER) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion       : Obtiene el indicador de Club Privilege
                      (1) Cliente inscrito club privilege
                      (0) Cliente NO inscrito club privilege

  Fecha Creacion    : 11/02/2008
  Parametros Entrada:

  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION int_fn_devue_indic_club_privi(pnoidcliente NUMBER) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion       : Obtiene el indicador de Concurso
                      (1) Tiene Concursos
                      (0) No tiene concursos

  Fecha Creacion    : 11/02/2008
  Parametros Entrada:

  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION int_fn_devue_indic_concu(pscodigocliente VARCHAR2) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion       : Devuelve un indicador de modificiacion de los datos de
                      cliente respecto a la tabla int_ivr_clien.
                      (S) Tiene Modificaciones
                      (N) No tiene Modificaciones

  Fecha Creacion    : 11/02/2008
  Parametros Entrada:

  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION int_fn_devue_inmod_clien
  (
    pscodigocliente VARCHAR2,
    pnoidcliente    NUMBER
  ) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion       : Devuelve un indicador de modificiacion de los datos de
                      concurso respecto a la tabla int_ivr_clien.
                      (S) Tiene Modificaciones
                      (N) No tiene Modificaciones

  Fecha Creacion    : 11/02/2008
  Parametros Entrada:

  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION int_fn_devue_inmod_concu
  (
    pscodigoconcurso VARCHAR2,
    pnoidconcurso    NUMBER
  ) RETURN VARCHAR2;

  /***************************************************************************
  Descripcion       : Genera Interfaz Emviar Tabla de Post Venta
  Fecha Creacion    : 27/02/2008
  Autor             : José A. Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_ivr_envi_tabl_post_vent
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigomarca    VARCHAR2,
    pscodigocanal    VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    psperiodocorte   VARCHAR2
  );
  /**************************************************************************
  Descripcion       : Obtiene el indicador de CDR
                      (1) Tiene registros en post venta
                      (0) No tiene registros en post venta

  Fecha Creacion    : 27/02/2008
  Parametros Entrada:

  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION int_fn_devue_indic_cdr(pscodigocliente VARCHAR2) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion       : Obtiene el indicador de CDR
                      (1) Tiene registros en post venta
                      (0) No tiene registros en post venta

  Fecha Creacion    : 27/02/2008
  Parametros Entrada:

  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION int_fn_calcu_valor_saldo_venci
  (
    pscodigopais    VARCHAR2,
    pscodigomarca   VARCHAR2,
    pscodigocanal   VARCHAR2,
    pscodigoperiodo VARCHAR2,
    pnoidcliente    NUMBER
  ) RETURN NUMBER;

  /**************************************************************************
  Descripcion       : Obtiene el indicador de CDR
                      (1) Tiene registros en post venta
                      (0) No tiene registros en post venta

  Fecha Creacion    : 27/02/2008

  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION int_fn_calcu_valor_saldo_venci
  (
    pnoidcliente NUMBER,
    pdfechavenci DATE
  ) RETURN NUMBER;
  /***************************************************************************
  Descripcion       : Registra los datos de los archivos procesados en el envio
                      de IVR Corporativo.
  Fecha Creacion    : 21/07/2009
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_ivr_corpo_regis_cntrl
  (
    pscodigocia     VARCHAR2,
    psnombrearchivo VARCHAR2,
    pnnumregistros  NUMBER,
    pscodigoperiodo VARCHAR2,
    pnnumerodias    NUMBER := 0
  );
  /**************************************************************************
  Descripcion       : Obtiene el indicador de marca de situación, indicador
                      de incobrable:
                      Si tipo Bloqueo = '01' (Administrativo)  -> Marca Situación = 'B'
                      Si tipo Bloqueo = '02' (Financiero)  -> Marca Situación = 'I'
                      SiNO  -> Marca Situación = 'N'

  Fecha Creacion    : 21/07/2009
  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION int_fn_devue_marca_situa_corpo(pnoidcliente NUMBER) RETURN VARCHAR2;
  /**************************************************************************
  Descripcion       : Devuelve la situacion de del concurso
                      Si periodo menor que campaña actual = 'T'
                      Si periodo mayor o igual campaña acatula ='V'

  Fecha Creacion    : 22/07/2009
  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION int_fn_devue_situa_concu(pnoidperi NUMBER) RETURN VARCHAR2;
  /**************************************************************************
  Descripcion       : Obtiene el indicador de Concurso
                      (1) Tiene Concursos
                      (0) No tiene concursos

  Fecha Creacion    : 11/02/2008
  Parametros Entrada:

  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION int_fn_devue_indic_concu_corpo(pscodigocliente VARCHAR2) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion       : Obtiene el indicador de CDR
                      (1) Tiene registros en post venta
                      (0) No tiene registros en post venta

  Fecha Creacion    : 27/02/2008
  Parametros Entrada:

  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION int_fn_devue_indic_cdr_corpo(pscodigocliente VARCHAR2) RETURN VARCHAR2;
  /**************************************************************************
  Descripcion       : Obtiene el indicador de marca de situación, indicador
                      de incobrable:
                      Si tipo Bloqueo = '01' (Administrativo)  -> Marca Situación = 'B'
                      Si tipo Bloqueo = '02' (Financiero)  -> Marca Situación = 'I'
                      SiNO  -> Marca Situación = 'N'

  Fecha Creacion    : 21/07/2009
  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION int_fn_devue_perio_ultim_pedid(pnoidcliente NUMBER) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion       : Devuelve Telefono
                      Si tiene Caracteres no numeriocs 0
                      Caso Contrario se devuelve le mismo valor.
  Fecha Creacion    : 01/10/2009
  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION int_fn_devue_telef(pstelefono VARCHAR2) RETURN VARCHAR2;
  /***************************************************************************
  Descripcion       : Genera Interfaz Emviar Tabla de Control de IVR Corporativo
  Fecha Creacion    : 21/07/2009
  Autor             : José A. Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_ivr_corpo_cntrl
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    indnuevaversion  VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Genera Interfaz Emviar  Clientes de IVR Corporativo
                      dependiendo de psIndProceso:
                      Si psIndProceso='T' se borran los datos de la tabla
                      int_ivr_clien y se cargan todos los datos
                      Si psIndProceso='N' se cargan solo los datos modificados
  Fecha Creacion    : 21/07/2009
  Autor             : José A. Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_ivr_corpo_clien
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    pscodigomarca     VARCHAR2,
    pscodigocanal     VARCHAR2,
    pscodigoperiodo   VARCHAR2,
    psindproceso      VARCHAR2,
    psnumerolote      VARCHAR2,
    psnombrecontrol   VARCHAR2,
    psenviarhistorico VARCHAR2,
    indnuevaversion   VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Genera Interfaz Emviar Tabla de Concursos de IVR dendiendo
                      de psIndProceso:
                      Si psIndProceso='T' se borran los datos de la tabla
                      int_ivr_clien y se cargan todos los datos
                      Si psIndProceso='N' se cargan solo los datos modificados
  Fecha Creacion    : 21/07/2009
  Autor             : José A. Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_ivr_corpo_concu
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    psindproceso     VARCHAR2,
    psnumerolote     VARCHAR2,
    psnombrecontrol  VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Genera Interfaz Emviar Tabla de Motivos de Rechazo a IVR
  Fecha Creacion    : 09/02/2008
  Autor             : José A. Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_ivr_corpo_recha_poven
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoiso      VARCHAR2,
    psnombrecontrol  VARCHAR2,
    indnuevaversion   VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Genera Interfaz Emviar Tabla de Post Venta
  Fecha Creacion    : 27/02/2008
  Autor             : José A. Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_ivr_corpo_servi_poven
  (
    pscodigopais           VARCHAR2,
    pscodigosistema        VARCHAR2,
    pscodigointerfaz       VARCHAR2,
    psnombrearchivo        VARCHAR2,
    pscodigomarca          VARCHAR2,
    pscodigocanal          VARCHAR2,
    pscodigoperiodo        VARCHAR2,
    psperiodocorte         VARCHAR2,
    psporcentajepercepcion NUMBER,
    psnombrecontrol        VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz Emviar Tabla de llamadas de salida de IVR dendiendo
                      de psIndProceso:
                      Si psIndProceso='T' se borran los datos de la tabla
                      int_ivr_corpo_llama_salid y se cargan todos los datos
                      Si psIndProceso='N' se cargan solo los datos modificados
  Fecha Creacion    : 20/07/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE int_pr_ivr_corpo_llama_salid
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    psindproceso     VARCHAR2,
    psnumerolote     VARCHAR2,
    psnombrecontrol  VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Genera Interfaz Emviar Tabla de resultados de campaña de IVR dendiendo
                      de psIndProceso:
                      Si psIndProceso='T' se borran los datos de la tabla
                      int_ivr_corpo_resul_campa y se cargan todos los datos
                      Si psIndProceso='N' se cargan solo los datos modificados
  Fecha Creacion    : 21/07/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE int_pr_ivr_corpo_resul_campa
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    psindproceso     VARCHAR2,
    psnumerolote     VARCHAR2,
    psnombrecontrol  VARCHAR2,
    pscodigomarca    VARCHAR2,
    pscodigocanal    VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Genera Interfaz Emviar Tabla de programa de dupla cyzone de IVR dendiendo
                      de psIndProceso:
                      Si psIndProceso='T' se borran los datos de la tabla
                      int_ivr_corpo_resul_campa y se cargan todos los datos
                      Si psIndProceso='N' se cargan solo los datos modificados
  Fecha Creacion    : 22/07/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE int_pr_ivr_corpo_progr_ducyz
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    psindproceso     VARCHAR2,
    psnumerolote     VARCHAR2,
    psnombrecontrol  VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Genera Interfaz Emviar Tabla de estado de pedidos de IVR dendiendo
                      de psIndProceso:
                      Si psIndProceso='T' se borran los datos de la tabla
                      int_ivr_clien y se cargan todos los datos
                      Si psIndProceso='N' se cargan solo los datos modificados
  Fecha Creacion    : 23/07/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE int_pr_ivr_corpo_estad_pedid
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigomarca    VARCHAR2,
    pscodigocanal    VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    psindproceso     VARCHAR2,
    psnumerolote     VARCHAR2,
    psnombrecontrol  VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Genera Interfaz Emviar Tabla de estado de pedidos de IVR dendiendo
                      de psIndProceso:
                      Si psIndProceso='T' se borran los datos de la tabla
                      int_ivr_clien y se cargan todos los datos
                      Si psIndProceso='N' se cargan solo los datos modificados
  Fecha Creacion    : 15/12/2010
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_ivr_corpo_estad_pedi2
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigomarca    VARCHAR2,
    pscodigocanal    VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    psindproceso     VARCHAR2,
    psnumerolote     VARCHAR2,
    psnombrecontrol  VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Genera Interfaz Emviar Tabla de maestro de dupla cyzone de IVR dendiendo
                      de psIndProceso:
                      Si psIndProceso='T' se borran los datos de la tabla
                      int_ivr_corpo_resul_campa y se cargan todos los datos
                      Si psIndProceso='N' se cargan solo los datos modificados
  Fecha Creacion    : 23/07/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE int_pr_ivr_corpo_maest_ducyz
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    psindproceso     VARCHAR2,
    psnumerolote     VARCHAR2,
    psnombrecontrol  VARCHAR2
  );

  /**************************************************************************
  Descripcion : Genera la Informacion de CLientes de IVR
  Fecha Creacion : 30/10/2009
  Parametros Entrada :
  Autor : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_ivr_corpo_gener_clien
  (
    psindproceso      IN VARCHAR2,
    psnumerolote      IN VARCHAR2,
    pscodigocia       IN VARCHAR2,
    psultimaejecucion IN VARCHAR2,
    psenviarhistorico IN VARCHAR2,
    pscodigopais      IN VARCHAR2,
    pnidpais          IN NUMBER,
    pscodigomarca     IN VARCHAR2,
    pnidmarca         IN NUMBER,
    pscodigocanal     IN VARCHAR2,
    pnidcanal         IN NUMBER,
    pscodigoperiodo   IN VARCHAR2,
    pnidperiodo       IN NUMBER,
    pscodigointerfaz  VARCHAR2,
    psindCambiarCodigo IN VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz Enviar Tabla de Bonos
  Fecha Creacion    : 29/10/2009
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_pr_ivr_corpo_bonos
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    psindproceso     VARCHAR2,
    psnumerolote     VARCHAR2,
    psnombrecontrol  VARCHAR2,
    indnuevaversion  VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Genera Interfaz Enviar Tabla de Bonos
  Fecha Creacion    : 12/04/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_ivr_actua_fecha_venci
  (
    pscodpais        VARCHAR2,
    pscodmarca       VARCHAR2,
    pscodcanal       VARCHAR2,
    pnoidpais        NUMBER,
    pnoidmarca       NUMBER,
    pnoidcanal       NUMBER,
    pnoidperiodo     NUMBER,
    pscodperiodo     VARCHAR2,
    pscodperiodosgte VARCHAR2,
    psnumerolote     VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz Enviar Tabla de Cronograma de actividades
  Fecha Creacion    : 12/10/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_pr_ivr_corpo_crono
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    psindproceso     VARCHAR2,
    psnumerolote     VARCHAR2,
    psnombrecontrol  VARCHAR2,
    psmarca          VARCHAR2,
    pscanal          VARCHAR2,
    indnuevaversion  VARCHAR2
  );

    /***************************************************************************
  Descripcion       : Genera Interfaz Enviar Matriz de Facturación de IVR dependiendo
                      de psIndProceso:
                      Si psIndProceso='T' se borran los datos de la tabla
                      int_ivr_matri y se cargan todos los datos
                      Si psIndProceso='N' se cargan solo los datos modificados
  Fecha Creacion    : 10/10/2011
  Autor             : Dany Romero
 ****************************************************************************/

  PROCEDURE int_pr_ivr_corpo_matri
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigomarca     VARCHAR2,
    pscodigocanal     VARCHAR2,
    pscodigoperiodo   VARCHAR2,
    psindproceso      VARCHAR2,
    psnumerolote     VARCHAR2,
    psnombrecontrol  VARCHAR2
  );

/***************************************************************************
  Descripcion       : inserta en las tablas INT_SOLIC_CABEC e INT_SOLIC_POSIC
                      los pedidos que lleguan de la interface de IVR-60
  Fecha Creacion    : 15/11/2011
  Autor             : Jose Luis Rodriguez
****************************************************************************/
PROCEDURE int_pr_ivr_recep_pedid(
    pscodigoPais      VARCHAR2,
    pscodigoInterfaz  VARCHAR2,
    pscodigoTipoDocu  VARCHAR2,
    pscodigousuario   VARCHAR2,
    psindicadorOrigen VARCHAR2,
    psnumeroLoteSTO   VARCHAR2
);
END int_pkg_ivvrr;
/
CREATE OR REPLACE PACKAGE BODY "INT_PKG_IVVRR" IS
  /***************************************************************************
  Descripcion       : Genera Interfase de Enviar Maestro de Clientes de IVR
  Fecha Creacion    : 21/03/2007
  Autor             : Marco Agurto
  ***************************************************************************/
  PROCEDURE int_pr_ivr_envi_maes_clie
  (
    pscodigopais        VARCHAR2,
    pscodigosistema     VARCHAR2,
    pscodigointerfaz    VARCHAR2,
    psnombrearchivo     VARCHAR2,
    psnumerolote        VARCHAR2,
    pscodigotipocliente VARCHAR2,
    pscodigotipobloqueo VARCHAR2
  ) IS
    CURSOR c_interfaz
    (
      oidpais        NUMBER,
      oidtipocliente NUMBER,
      oidtipobloqueo NUMBER,
      codigoperiodo  VARCHAR2,
      codigomarca    VARCHAR2,
      codigocanal    VARCHAR2
    ) IS
      SELECT '"0' || cl.cod_clie || '",' || '"' || (cl.val_nom1) || ' ' || (cl.val_nom2) || ' ' ||
              (cl.val_ape1) || ' ' || (cl.val_ape2) || '",' || '"' || ab.cod_acce_buzo_ivrz || '",' ||
             -- GEN_PKG_GENER.GEN_FN_CALCU_VALOR_SALD_DECI(cl.oid_clie) ||','||
              TRIM(to_char(gen_pkg_gener.gen_fn_calcu_valor_saldo_venci(cl.oid_clie,
                                                                        gen_pkg_gener.gen_fn_obtie_fecha_venci(pscodigopais,
                                                                                                               codigomarca,
                                                                                                               codigocanal,
                                                                                                               codigoperiodo,
                                                                                                               gen_pkg_gener.gen_fn_clien_datos_oid(cl.oid_clie,
                                                                                                                                                    'COD_REGI'),
                                                                                                               gen_pkg_gener.gen_fn_clien_datos_oid(cl.oid_clie,
                                                                                                                                                    'COD_ZONA'),
                                                                                                               cl.oid_clie)),
                           '9999990D00',
                           'NLS_NUMERIC_CHARACTERS=''.,''')) || ',' || '"' || se.cod_secc || '",' || '"' ||
              substr(te.cod_terr,
                     1,
                     3) || '",' || '"' || to_char(SYSDATE,
                                                  'YYYYMMDD') || '",' || '"' || 'N' || '"' AS campoconcatenado
        FROM mae_clien             cl,
             mae_clien_tipo_subti  ct,
             mae_clien_unida_admin cu,
             zon_terri_admin       zta,
             zon_zona              zo,
             zon_secci             se,
             zon_terri             te,
             int_acces_buzon_ivrzn ab
       WHERE cl.oid_clie = ct.clie_oid_clie
         AND cl.oid_clie = cu.clie_oid_clie
         AND cu.ztad_oid_terr_admi = zta.oid_terr_admi
         AND zta.zscc_oid_secc = se.oid_secc
         AND se.zzon_oid_zona = zo.oid_zona
         AND ab.zzon_oid_zona(+) = zo.oid_zona
         AND te.oid_terr = zta.terr_oid_terr
         AND cl.pais_oid_pais = oidpais
         AND ct.ticl_oid_tipo_clie = oidtipocliente
         AND ct.ind_ppal = 1
         AND cu.ind_acti = 1
         AND zta.ind_borr = 0
         AND zo.ind_acti = 1
         AND zo.ind_borr = 0
         AND se.ind_acti = 1
         AND se.ind_borr = 0
         AND NOT EXISTS (SELECT bloq.clie_oid_clie
                FROM mae_clien_bloqu bloq
               WHERE bloq.clie_oid_clie = cl.oid_clie
                 AND bloq.tibq_oid_tipo_bloq = oidtipobloqueo
                 AND bloq.fec_desb IS NULL);
    TYPE interfazrec IS RECORD(
      campoconcatenado VARCHAR2(200));
    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo   VARCHAR2(50);
    ls_oidpais        seg_pais.oid_pais%TYPE;
    ls_oidtipocliente mae_tipo_clien.oid_tipo_clie%TYPE;
    ls_oidtipobloqueo mae_tipo_bloqu.oid_tipo_bloq%TYPE;
    lnidcanal         seg_canal.oid_cana%TYPE;

    lscodperiodo   seg_perio_corpo.cod_peri%TYPE;
    lscodigomarca  VARCHAR2(1) := 'T';
    lscodigocanal  VARCHAR2(2) := 'VD';
    lbabrirutlfile BOOLEAN;
  BEGIN

    SELECT oid_tipo_bloq
      INTO ls_oidtipobloqueo
      FROM mae_tipo_bloqu
     WHERE mae_tipo_bloqu.cod_tipo_bloq = pscodigotipobloqueo;

    SELECT oid_tipo_clie
      INTO ls_oidtipocliente
      FROM mae_tipo_clien
     WHERE mae_tipo_clien.cod_tipo_clie = pscodigotipocliente;

    ls_oidpais := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);
    lnidcanal  := gen_pkg_gener.gen_fn_devuelve_id_canal(lscodigocanal);

    lscodperiodo := gen_pkg_gener.gen_fn_devue_perio_actua(ls_oidpais,
                                                           lnidcanal,
                                                           NULL);

    /* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;
    OPEN c_interfaz(ls_oidpais,
                    ls_oidtipobloqueo,
                    ls_oidtipocliente,
                    lscodperiodo,
                    lscodigomarca,
                    lscodigocanal);
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
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x).campoconcatenado;
          utl_file.put_line(v_handle,
                            lslinea);
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
                              'ERROR INT_PR_IVR_ENVI_MAES_CLIE: ' || ls_sqlerrm);
  END int_pr_ivr_envi_maes_clie;

  /***************************************************************************
  Descripcion       : Genera Interfase de Enviar Concursos de IVR
  Fecha Creacion    : 29/03/2007
  Autor             : Marco Agurto
  ***************************************************************************/
  PROCEDURE int_pr_ivr_envi_concu
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT '"0' || (clien.cod_clie) || '",' || '"' || ivc.situacion || '",' || '"' ||
             substr(cpg.num_conc,
                    1,
                    2) || substr(cpg.num_conc,
                                 5,
                                 2) || '",' || '"' || cpg.tipo || '",' || SUM(ccp.num_punt) AS campoconcatenado
        FROM (SELECT ivd.num_conc,
                     ivd.copa_oid_para_gral,
                     decode(ivd.vico_oid_vige_conc,
                            1,
                            'V',
                            'T') situacion
                FROM inc_versi_concu ivd
               WHERE ivd.vico_oid_vige_conc IN (1,
                                                6)) ivc,
             (SELECT cpg1.oid_para_gral,
                     cpg1.num_conc,
                     cpg1.perd_oid_peri_hast,
                     'V' tipo
                FROM inc_concu_param_gener cpg1
               WHERE cpg1.coiv_oid_conc_ivr = 1
              UNION
              SELECT cpg1.oid_para_gral,
                     cpg1.num_conc,
                     cpg1.perd_oid_peri_hast,
                     decode(cpg1.coiv_oid_conc_ivr,
                            2,
                            'C',
                            'P') tipo
                FROM inc_concu_param_gener cpg1
               WHERE cpg1.coiv_oid_conc_ivr = 2
                  OR cpg1.coiv_oid_conc_ivr = 3) cpg,
             inc_cuent_corri_punto ccp,
             mae_clien clien,
             (SELECT gan.clie_oid_clie,
                     pgp.copa_oid_para_gral
                FROM inc_param_gener_premi pgp,
                     inc_param_nivel_premi pnp,
                     inc_ganad             gan
               WHERE gan.panp_oid_para_nive_prem = pnp.oid_para_nive_prem
                 AND pnp.pagp_oid_para_gene_prem = pgp.oid_para_gene_prem
               GROUP BY gan.clie_oid_clie,
                        pgp.copa_oid_para_gral) ivf
       WHERE ivc.copa_oid_para_gral = cpg.oid_para_gral
         AND cpg.oid_para_gral = ccp.copa_oid_para_gral
         AND ccp.copa_oid_para_gral = ivf.copa_oid_para_gral(+)
         AND ccp.clie_oid_clie = clien.oid_clie
         AND ccp.clie_oid_clie = ivf.clie_oid_clie(+)
         AND ccp.tmov_oid_tipo_movi <> 2
         AND ((ivc.situacion = 'T' AND ivf.clie_oid_clie IS NULL) OR ivc.situacion = 'V')
       GROUP BY clien.cod_clie,
                ccp.clie_oid_clie,
                cpg.perd_oid_peri_hast,
                cpg.num_conc,
                cpg.oid_para_gral,
                ivc.situacion,
                cpg.tipo;
    TYPE interfazrec IS RECORD(
      campoconcatenado VARCHAR2(200));
    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo VARCHAR2(50);
  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                           pscodigosistema,
                                           pscodigointerfaz,
                                           psnombrearchivo,
                                           lsdirtempo,
                                           lsnombrearchivo,
                                           v_handle);

    /* Generando Archivo de Texto (Detalle) */
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      IF interfazrecord.COUNT > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x).campoconcatenado;
          utl_file.put_line(v_handle,
                            lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    utl_file.fclose(v_handle);

    /* Procedimiento final para generar interfaz */
    gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                           lsdirtempo,
                                           psnombrearchivo,
                                           lsnombrearchivo);
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_IVR_ENVI_CONCU: ' || ls_sqlerrm);
  END int_pr_ivr_envi_concu;

  /***************************************************************************
  Descripcion       : Genera Interfase de Enviar Bases Incumplidas de IVR
  Fecha Creacion    : 29/03/2007
  Autor             : Marco Agurto
  ***************************************************************************/
  PROCEDURE int_pr_ivr_base_incu
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT '"0' || (clien.cod_clie) || '",' || '"' ||
             substr(cpg.num_conc,
                    1,
                    2) || substr(cpg.num_conc,
                                 5,
                                 2) || '",' || '"' || '2' || '",' || '"' || spc.cod_peri || '",' ||
             '0.00' || ',' || '""' AS campoconcatenado
        FROM (SELECT ivd.num_conc,
                     ivd.copa_oid_para_gral,
                     decode(ivd.vico_oid_vige_conc,
                            1,
                            'V',
                            'T') situacion
                FROM inc_versi_concu ivd
               WHERE ivd.vico_oid_vige_conc IN (1,
                                                6)) ivc,
             inc_concu_param_gener cpg,
             inc_cuent_corri_punto ccp,
             mae_clien clien,
             seg_perio_corpo spc,
             cra_perio cra1,
             inc_desca des,
             (SELECT gan.clie_oid_clie,
                     pgp.copa_oid_para_gral
                FROM inc_param_gener_premi pgp,
                     inc_param_nivel_premi pnp,
                     inc_ganad             gan
               WHERE gan.panp_oid_para_nive_prem = pnp.oid_para_nive_prem
                 AND pnp.pagp_oid_para_gene_prem = pgp.oid_para_gene_prem
               GROUP BY gan.clie_oid_clie,
                        pgp.copa_oid_para_gral) ivf
       WHERE ivc.copa_oid_para_gral = cpg.oid_para_gral
         AND cpg.oid_para_gral = ccp.copa_oid_para_gral
         AND des.copa_oid_para_gral = ccp.copa_oid_para_gral
         AND des.clie_oid_clie = ccp.clie_oid_clie
         AND des.cade_oid_caus_desc = 2
         AND des.perd_oid_peri = cra1.oid_peri
         AND cra1.peri_oid_peri = spc.oid_peri
         AND ccp.copa_oid_para_gral = ivf.copa_oid_para_gral(+)
         AND ccp.clie_oid_clie = clien.oid_clie
         AND ccp.clie_oid_clie = ivf.clie_oid_clie(+)
         AND cpg.coiv_oid_conc_ivr IS NOT NULL
         AND ((ivc.situacion = 'T' AND ivf.clie_oid_clie IS NULL) OR ivc.situacion = 'V')
       GROUP BY clien.cod_clie,
                ccp.clie_oid_clie,
                cpg.perd_oid_peri_hast,
                cpg.num_conc,
                cpg.oid_para_gral,
                ivc.situacion,
                des.perd_oid_peri,
                spc.cod_peri;
    TYPE interfazrec IS RECORD(
      campoconcatenado VARCHAR2(200));
    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;

    lslinea         VARCHAR2(1000);
    lbabrirutlfile  BOOLEAN;
    lsnombrearchivo VARCHAR2(50);
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

      IF interfazrecord.COUNT > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x).campoconcatenado;
          utl_file.put_line(v_handle,
                            lslinea);
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
                              'ERROR INT_PR_IVR_BASE_INCU: ' || ls_sqlerrm);
  END int_pr_ivr_base_incu;

  /***************************************************************************
  Descripcion       : Genera Interfase de Enviar Niveles Sin controls de Campa¿a de IVR
  Fecha Creacion    : 29/03/2007
  Autor             : Marco Agurto
  ***************************************************************************/
  PROCEDURE int_pr_ivr_nive_conc
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
    CURSOR c_interfaz IS

      SELECT '"' || substr(cpg.num_conc,
                           1,
                           2) || substr(cpg.num_conc,
                                        5,
                                        2) || '",' || '"0' || (to_char(pnp.num_nive)) || '",' ||
             pnp.num_cant_inic_punt || ',' || '"' || spc.cod_peri || '"'
        FROM inc_concu_param_gener cpg,
             inc_param_nivel_premi pnp,
             inc_param_gener_premi pgp,
             cra_perio             cra,
             seg_perio_corpo       spc
       WHERE cpg.oid_para_gral = pgp.copa_oid_para_gral
         AND pgp.oid_para_gene_prem = pnp.pagp_oid_para_gene_prem
         AND pgp.perd_oid_peri = cra.oid_peri(+)
         AND cra.peri_oid_peri = spc.oid_peri(+)
         AND EXISTS (SELECT NULL
                FROM seg_pais pa
               WHERE pa.cod_pais = pscodigopais
                 AND cpg.pais_oid_pais = pa.oid_pais)
         AND cpg.coiv_oid_conc_ivr IS NOT NULL;

    TYPE interfazrec IS RECORD(
      campoconcatenado VARCHAR2(200));
    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo VARCHAR2(50);
  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                           pscodigosistema,
                                           pscodigointerfaz,
                                           psnombrearchivo,
                                           lsdirtempo,
                                           lsnombrearchivo,
                                           v_handle);

    /* Generando Archivo de Texto (Detalle) */
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      IF interfazrecord.COUNT > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x).campoconcatenado;
          utl_file.put_line(v_handle,
                            lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    utl_file.fclose(v_handle);

    /* Procedimiento final para generar interfaz */
    gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                           lsdirtempo,
                                           psnombrearchivo,
                                           lsnombrearchivo);
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_IVR_NIVE_CONC: ' || ls_sqlerrm);
  END int_pr_ivr_nive_conc;

  /***************************************************************************
  Descripcion       : Genera Interfase de Enviar Maestro de Clientes de IVR
  Fecha Creacion    : 21/03/2007
  Autor             : Marco Agurto
  ***************************************************************************/
  PROCEDURE int_pr_ivr_envi_prem_conc
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
    CURSOR c_interfaz(oidpais VARCHAR2) IS
      SELECT '"' || substr(inc_concu_param_gener.num_conc,
                           1,
                           2) || substr(inc_concu_param_gener.num_conc,
                                        5,
                                        2) || '",' || '"0' ||
             (to_char(inc_param_nivel_premi.num_nive)) || '",' || '"' || mae_produ.cod_sap || '",' ||
             inc_artic_lote.num_unid AS campoconcatenado
        FROM inc_concu_param_gener,
             inc_param_gener_premi,
             inc_param_nivel_premi,
             inc_premi_artic,
             inc_artic_lote,
             inc_lote_premi_artic,
             mae_produ
       WHERE inc_concu_param_gener.coiv_oid_conc_ivr IS NOT NULL
         AND inc_concu_param_gener.pais_oid_pais = oidpais
         AND inc_concu_param_gener.oid_para_gral = inc_param_gener_premi.copa_oid_para_gral
         AND inc_param_gener_premi.oid_para_gene_prem =
             inc_param_nivel_premi.pagp_oid_para_gene_prem
         AND inc_param_nivel_premi.oid_para_nive_prem = inc_premi_artic.panp_oid_para_nive_prem
         AND inc_premi_artic.oid_prem_arti = inc_lote_premi_artic.prar_oid_prem_arti
         AND inc_lote_premi_artic.oid_lote_prem_arti = inc_artic_lote.lopa_oid_lote_prem_arti
         AND inc_artic_lote.prod_oid_prod = mae_produ.oid_prod;
    TYPE interfazrec IS RECORD(
      campoconcatenado VARCHAR2(200));
    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo VARCHAR2(50);
    ls_oidpais      seg_pais.oid_pais%TYPE;

  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                           pscodigosistema,
                                           pscodigointerfaz,
                                           psnombrearchivo,
                                           lsdirtempo,
                                           lsnombrearchivo,
                                           v_handle);

    ls_oidpais := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);

    /* Generando Archivo de Texto (Detalle) */
    OPEN c_interfaz(ls_oidpais);
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      IF interfazrecord.COUNT > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x).campoconcatenado;
          utl_file.put_line(v_handle,
                            lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    utl_file.fclose(v_handle);

    /* Procedimiento final para generar interfaz */
    gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                           lsdirtempo,
                                           psnombrearchivo,
                                           lsnombrearchivo);
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_IVR_ENVI_PREM_CONC: ' || ls_sqlerrm);
  END int_pr_ivr_envi_prem_conc;
  /***************************************************************************
  Descripcion       : Genera Interfaz Emviar Tabla de Clientes de IVR dendiendo
                      de psIndProceso:
                      Si psIndProceso='T' se borran los datos de la tabla
                      int_ivr_clien y se cargan todos los datos
                      Si psIndProceso='N' se cargan solo los datos modificados
  Fecha Creacion    : 09/02/2008
  Autor             : José A. Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_ivr_envi_tabla_clien
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigomarca    VARCHAR2,
    pscodigocanal    VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    psindproceso     VARCHAR2,
    psnumerolote     VARCHAR2
  ) IS
    searchstr  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20);
    replacestr VARCHAR2(100) := 'a        ';

    CURSOR c_interfaz(vnmaxcarcodclie NUMBER) IS
      SELECT '"' || cod_comp || '",' || '"' || cod_tipo_clie || '",' || '"' ||
             lpad(cod_clie,
                  vnmaxcarcodclie,
                  '0') || '",' || '"' || translate(val_nomb_clie,
                                                   searchstr,
                                                   replacestr) || '",' || '"' || cod_zona || '",' ||
             TRIM(to_char(val_sald_cart,
                          '999999999990.00')) || ',' || '"' ||
             to_char(fec_envi_nove - 1,
                     'YYYYMMDD') || '",' || '"' || ind_situ_inco || '",' || '"' || ind_club_priv || '",' || '"' ||
             ind_conc || '",' || '"' || ind_cdr || '"'
        FROM int_ivr_clien
       WHERE num_lote = psnumerolote;

    TYPE interfazrec IS RECORD(
      campoconcatenado VARCHAR2(200)

      );
    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo VARCHAR2(50);

    lscodigocia    int_ivr_param_compa.cod_comp%TYPE;
    lnnumregistros NUMBER := 0;

    lnoidtipoconsultora NUMBER;
    lnoidtipogerente    NUMBER;
    lnoidsubtipogzona   NUMBER;
    lnoidsubtipogregion NUMBER;
    ldfechaproceso      DATE;
    lsfechaproceso      VARCHAR2(8);
    lnmaxcarcodclie     NUMBER := 10;
    lbabrirutlfile      BOOLEAN;
    l_user              VARCHAR2(20);

  BEGIN

    /* Obtiene el codigo de Compañia */
    SELECT int_ivr_param_compa.cod_comp
      INTO lscodigocia
      FROM int_ivr_param_compa
     WHERE int_ivr_param_compa.cod_pais = pscodigopais;

    --TipoConsultora
    SELECT tipo.oid_tipo_clie
      INTO lnoidtipoconsultora
      FROM mae_tipo_clien tipo
     WHERE tipo.cod_tipo_clie = '02';

    --TipoConsultora
    SELECT tipo.oid_tipo_clie
      INTO lnoidtipogerente
      FROM mae_tipo_clien tipo
     WHERE tipo.cod_tipo_clie = '04';

    --SUBTIPO GERENTE REGION
    SELECT subtipo.oid_subt_clie
      INTO lnoidsubtipogregion
      FROM mae_subti_clien subtipo
     WHERE subtipo.ticl_oid_tipo_clie = lnoidtipogerente
       AND subtipo.cod_subt_clie = '01';

    --SUBTIPO GERENTE ZONA
    SELECT subtipo.oid_subt_clie
      INTO lnoidsubtipogzona
      FROM mae_subti_clien subtipo
     WHERE subtipo.ticl_oid_tipo_clie = lnoidtipogerente
       AND subtipo.cod_subt_clie = '02';

    --OBTENGO FECHA DE PROCESO

    SELECT SYSDATE,
           to_char(SYSDATE,
                   'YYYYMMDD')
      INTO ldfechaproceso,
           lsfechaproceso
      FROM dual;

    /* Generando Archivo de Texto (Detalle) */

    /* obteniendo id's */
    IF psindproceso = 'T' THEN
      EXECUTE IMMEDIATE 'TRUNCATE TABLE int_ivr_clien';

      INSERT INTO int_ivr_clien
        (cod_comp,
         cod_tipo_clie,
         cod_clie,
         val_nomb_clie,
         cod_zona,
         val_sald_cart,
         fec_envi_nove,
         ind_situ_inco,
         ind_club_priv,
         ind_conc,
         ind_cdr,
         num_lote)

        SELECT lscodigocia,
               temp.tipocliente,
               temp.cod_clie,
               temp.nombre,
               temp.codigozona,
               int_pkg_ivvrr.int_fn_calcu_valor_saldo_venci(pscodigopais,
                                                            pscodigomarca,
                                                            pscodigocanal,
                                                            pscodigoperiodo,
                                                            temp.oid_clie) saldocartera,
               ldfechaproceso fechaenvionovedad,
               int_pkg_ivvrr.int_fn_devue_marca_situa(temp.oid_clie) marcasituacion,
               int_pkg_ivvrr.int_fn_devue_indic_club_privi(temp.oid_clie) indicadorprivilege,
               int_pkg_ivvrr.int_fn_devue_indic_concu(temp.cod_clie) indicadorconcurso,
               int_pkg_ivvrr.int_fn_devue_indic_cdr(temp.cod_clie) indicadorcdr,
               psnumerolote
          FROM (SELECT 'CL' tipocliente,
                       c.cod_clie cod_clie,
                       c.oid_clie oid_clie,
                       TRIM(c.val_nom1) || ' ' || TRIM(c.val_nom2) || ' ' || TRIM(c.val_ape1) || ' ' ||
                       TRIM(c.val_ape2) nombre,
                       gen_pkg_gener.gen_fn_clien_datos_oid(c.oid_clie,
                                                            'COD_ZONA') codigozona

                  FROM mae_clien            c,
                       mae_clien_tipo_subti t

                 WHERE c.oid_clie = t.clie_oid_clie
                   AND t.ticl_oid_tipo_clie = lnoidtipoconsultora
                   AND NOT EXISTS (SELECT 1 FROM zon_zona z WHERE c.oid_clie = z.clie_oid_clie)
                   AND NOT EXISTS
                 (SELECT 1 FROM zon_regio r WHERE c.oid_clie = r.clie_oid_clie)
                UNION ALL
                SELECT 'GZ' tipocliente,
                       cz.cod_clie cod_clie,
                       cz.oid_clie oid_clie,
                       TRIM(cz.val_nom1) || ' ' || TRIM(cz.val_nom2) || ' ' || TRIM(cz.val_ape1) || ' ' ||
                       TRIM(cz.val_ape2) nombre,
                       z.cod_zona
                  FROM zon_zona  z,
                       mae_clien cz
                 WHERE cz.oid_clie = z.clie_oid_clie
                   AND z.ind_acti = '1'
                   AND z.ind_borr = '0'
                UNION ALL
                SELECT 'GR' tipocliente,
                       cr.cod_clie cod_clie,
                       cr.oid_clie oid_clie,
                       TRIM(cr.val_nom1) || ' ' || TRIM(cr.val_nom2) || ' ' || TRIM(cr.val_ape1) || ' ' ||
                       TRIM(cr.val_ape2) nombre,
                       r.cod_regi codigozona
                  FROM zon_regio r,
                       mae_clien cr
                 WHERE cr.oid_clie = r.clie_oid_clie
                   AND r.ind_acti = '1'
                   AND r.ind_borr = '0') temp;

    ELSE

      --ELIMINO LOS REGISTROS DE CLIENTES CON CAMBIOS
      DELETE int_ivr_clien ivr_clie
       WHERE EXISTS (SELECT DISTINCT mae_clien.cod_clie
                FROM mae_clien,
                     int_ivr_clien,
                     mae_clien_unida_admin,
                     ccc_movim_cuent_corri
               WHERE mae_clien.cod_clie = int_ivr_clien.cod_clie
                 AND mae_clien.oid_clie = mae_clien_unida_admin.clie_oid_clie
                 AND mae_clien.oid_clie = ccc_movim_cuent_corri.clie_oid_clie
                 AND (mae_clien.fec_ulti_actu > int_ivr_clien.fec_envi_nove OR
                     mae_clien_unida_admin.fec_ulti_actu > int_ivr_clien.fec_envi_nove OR
                     ccc_movim_cuent_corri.fec_ulti_actu > int_ivr_clien.fec_envi_nove)
                 AND ivr_clie.cod_clie = int_ivr_clien.cod_clie);

      INSERT INTO int_ivr_clien
        (cod_comp,
         cod_tipo_clie,
         cod_clie,
         val_nomb_clie,
         cod_zona,
         val_sald_cart,
         fec_envi_nove,
         ind_situ_inco,
         ind_club_priv,
         ind_conc,
         ind_cdr,
         num_lote)

        SELECT lscodigocia,
               temp.tipocliente,
               temp.cod_clie,
               temp.nombre,
               temp.codigozona,
               int_pkg_ivvrr.int_fn_calcu_valor_saldo_venci(pscodigopais,
                                                            pscodigomarca,
                                                            pscodigocanal,
                                                            pscodigoperiodo,
                                                            temp.oid_clie) saldocartera,
               ldfechaproceso fechaenvionovedad,
               int_pkg_ivvrr.int_fn_devue_marca_situa(temp.oid_clie) marcasituacion,
               int_pkg_ivvrr.int_fn_devue_indic_club_privi(temp.oid_clie) indicadorprivilege,
               int_pkg_ivvrr.int_fn_devue_indic_concu(temp.cod_clie) indicadorconcurso,
               int_pkg_ivvrr.int_fn_devue_indic_cdr(temp.cod_clie) indicadorcdr,
               psnumerolote
          FROM (SELECT 'CL' tipocliente,
                       c.cod_clie cod_clie,
                       c.oid_clie oid_clie,
                       TRIM(c.val_nom1) || ' ' || TRIM(c.val_nom2) || ' ' || TRIM(c.val_ape1) || ' ' ||
                       TRIM(c.val_ape2) nombre,
                       gen_pkg_gener.gen_fn_clien_datos_oid(c.oid_clie,
                                                            'COD_ZONA') codigozona

                  FROM mae_clien            c,
                       mae_clien_tipo_subti t

                 WHERE c.oid_clie = t.clie_oid_clie
                   AND t.ticl_oid_tipo_clie = lnoidtipoconsultora
                   AND NOT EXISTS (SELECT 1
                          FROM zon_zona z
                         WHERE c.oid_clie = z.clie_oid_clie
                           AND z.ind_acti = '1'
                           AND z.ind_borr = '0')
                   AND NOT EXISTS (SELECT 1
                          FROM zon_regio r
                         WHERE c.oid_clie = r.clie_oid_clie
                           AND r.ind_acti = '1'
                           AND r.ind_borr = '0')
                   AND NOT EXISTS
                 (SELECT 1 FROM int_ivr_clien ivr WHERE ivr.cod_clie = c.cod_clie)
                UNION ALL
                SELECT 'GZ' tipocliente,
                       cz.cod_clie cod_clie,
                       cz.oid_clie oid_clie,
                       TRIM(cz.val_nom1) || ' ' || TRIM(cz.val_nom2) || ' ' || TRIM(cz.val_ape1) || ' ' ||
                       TRIM(cz.val_ape2) nombre,
                       z.cod_zona

                  FROM zon_zona  z,
                       mae_clien cz
                 WHERE cz.oid_clie = z.clie_oid_clie
                   AND z.ind_acti = '1'
                   AND z.ind_borr = '0'

                UNION ALL
                SELECT 'GR' tipocliente,
                       cr.cod_clie cod_clie,
                       cr.oid_clie oid_clie,
                       TRIM(cr.val_nom1) || ' ' || TRIM(cr.val_nom2) || ' ' || TRIM(cr.val_ape1) || ' ' ||
                       TRIM(cr.val_ape2) nombre,
                       r.cod_regi codigozona

                  FROM zon_regio r,
                       mae_clien cr
                 WHERE cr.oid_clie = r.clie_oid_clie
                   AND r.ind_acti = '1'
                   AND r.ind_borr = '0') temp
         WHERE NOT EXISTS (SELECT 1 FROM int_ivr_clien ivr WHERE ivr.cod_clie = temp.cod_clie);

    END IF;

    l_user := USER;
    dbms_stats.gather_table_stats(ownname => l_user,
                                  tabname => 'int_ivr_clien',
                                  cascade => TRUE);

    lnnumregistros := 0;
    lbabrirutlfile := TRUE;

    OPEN c_interfaz(lnmaxcarcodclie);
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
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP

          lslinea := interfazrecord(x).campoconcatenado;
          utl_file.put_line(v_handle,
                            lslinea);

          lnnumregistros := lnnumregistros + 1;
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
    END IF;

    INSERT INTO int_ivr_ctrl
      (cod_comp,
       nom_arch,
       val_cant_regi,
       ind_esta_actu)
    VALUES
      (lscodigocia,
       psnombrearchivo,
       lnnumregistros,
       '0');

    IF NOT lbabrirutlfile THEN
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
                              'ERROR INT_PR_IVR_ENVI_TABLA_CLIEN: ' || ls_sqlerrm);
  END int_pr_ivr_envi_tabla_clien;

  /***************************************************************************
  Descripcion       : Genera Interfaz Emviar Tabla de Concursos de IVR dendiendo
                      de psIndProceso:
                      Si psIndProceso='T' se borran los datos de la tabla
                      int_ivr_clien y se cargan todos los datos
                      Si psIndProceso='N' se cargan solo los datos modificados
  Fecha Creacion    : 09/02/2008
  Autor             : José A. Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_ivr_envi_tabla_concu
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    psindproceso     VARCHAR2,
    psnumerolote     VARCHAR2
  ) IS
    CURSOR c_interfaz(vnmaxcarcodclie NUMBER) IS

      SELECT cod_comp,
             lpad(cod_clie,
                  vnmaxcarcodclie,
                  '0'),
             ind_vige,
             num_conc,
             tip_conc,
             cod_peri_inic,
             cod_peri_fin,
             val_punt_acum,
             to_char(fec_acum,
                     'YYYYMMDD'),
             ind_valid
        FROM int_ivr_concu
       WHERE num_lote = psnumerolote;

    TYPE interfazrec IS RECORD(
      codigocia       int_ivr_concu.cod_comp%TYPE,
      codigocliente   int_ivr_concu.cod_clie%TYPE,
      indvigencia     int_ivr_concu.ind_vige%TYPE,
      numconcurso     int_ivr_concu.num_conc%TYPE,
      tipoconcurso    int_ivr_concu.tip_conc%TYPE,
      periodoinicio   int_ivr_concu.cod_peri_inic%TYPE,
      periodofin      int_ivr_concu.cod_peri_fin%TYPE,
      puntajeconcurso int_ivr_concu.val_punt_acum%TYPE,
      fechaproceso    VARCHAR2(8),
      indvalidacion   int_ivr_concu.ind_valid%TYPE);
    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo VARCHAR2(50);

    lnidpais       NUMBER;
    lscodigocia    int_ivr_param_compa.cod_comp%TYPE;
    lnnumregistros NUMBER := 0;

    ldfechaproceso  DATE;
    lsfechaproceso  VARCHAR2(8);
    lnmaxcarcodclie NUMBER := 10;
    lbabrirutlfile  BOOLEAN;
    l_user          VARCHAR2(20);
  BEGIN

    /* Obtiene el codigo de Compañia */
    SELECT int_ivr_param_compa.cod_comp
      INTO lscodigocia
      FROM int_ivr_param_compa
     WHERE int_ivr_param_compa.cod_pais = pscodigopais;

    /* obteniendo id's */
    lnidpais := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais); -- id del pais consultante

    --OBTENGO FECHA DE PROCESO

    SELECT SYSDATE,
           to_char(SYSDATE,
                   'YYYYMMDD')
      INTO ldfechaproceso,
           lsfechaproceso
      FROM dual;

    IF psindproceso = 'T' THEN
      EXECUTE IMMEDIATE 'TRUNCATE TABLE int_ivr_concu';

      INSERT INTO int_ivr_concu
        (cod_comp,
         cod_clie,
         ind_vige,
         num_conc,
         tip_conc,
         cod_peri_inic,
         cod_peri_fin,
         val_punt_acum,
         fec_acum,
         ind_valid,
         num_lote)
        SELECT lscodigocia codigocia,
               clien.cod_clie codigocliente,
               ivc.situacion indicadorvigencia,
               REPLACE(cpg.num_conc,
                       '/',
                       '') numeroconcurso,
               cpg.tipo tipoconcurso,
               perd.cod_peri periodoinicio,
               perh.cod_peri periodofin,
               SUM(ccp.num_punt) puntacumulado,
               ldfechaproceso fechaacumulacion,
               decode(ivc.situacion,
                      'V',
                      '',
                      decode(ivf.clie_oid_clie,
                             NULL,
                             'N',
                             'G')) flagvalidacion,
               psnumerolote

          FROM (SELECT ivd.num_conc,
                       ivd.copa_oid_para_gral,
                       decode(ivd.vico_oid_vige_conc,
                              1,
                              'V',
                              'T') situacion
                  FROM inc_versi_concu ivd
                 WHERE ivd.vico_oid_vige_conc IN (1,
                                                  6)) ivc,
               (SELECT cpg1.oid_para_gral,
                       cpg1.num_conc,
                       cpg1.perd_oid_peri_hast,
                       cpg1.perd_oid_peri_desd,
                       'V' tipo
                  FROM inc_concu_param_gener cpg1
                 WHERE cpg1.coiv_oid_conc_ivr = 1
                   AND cpg1.pais_oid_pais = lnidpais
                UNION
                SELECT cpg1.oid_para_gral,
                       cpg1.num_conc,
                       cpg1.perd_oid_peri_hast,
                       cpg1.perd_oid_peri_desd,
                       decode(cpg1.coiv_oid_conc_ivr,
                              2,
                              'C',
                              'P') tipo
                  FROM inc_concu_param_gener cpg1
                 WHERE (cpg1.coiv_oid_conc_ivr = 2 OR cpg1.coiv_oid_conc_ivr = 3)
                   AND cpg1.pais_oid_pais = lnidpais) cpg,
               inc_cuent_corri_punto ccp,
               mae_clien clien,
               (SELECT gan.clie_oid_clie,
                       pgp.copa_oid_para_gral
                  FROM inc_param_gener_premi pgp,
                       inc_param_nivel_premi pnp,
                       inc_ganad             gan
                 WHERE gan.panp_oid_para_nive_prem = pnp.oid_para_nive_prem
                   AND pnp.pagp_oid_para_gene_prem = pgp.oid_para_gene_prem
                 GROUP BY gan.clie_oid_clie,
                          pgp.copa_oid_para_gral) ivf,
               (SELECT c.oid_peri,
                       a.cod_peri,
                       (SELECT COUNT(*) FROM seg_perio_corpo b WHERE b.cod_peri <= a.cod_peri) AS conta
                  FROM seg_perio_corpo a,
                       cra_perio       c
                 WHERE a.oid_peri = c.peri_oid_peri
                 ORDER BY a.cod_peri) perd,
               (SELECT c.oid_peri,
                       a.cod_peri,
                       (SELECT COUNT(*) FROM seg_perio_corpo b WHERE b.cod_peri <= a.cod_peri) AS conta
                  FROM seg_perio_corpo a,
                       cra_perio       c
                 WHERE a.oid_peri = c.peri_oid_peri
                 ORDER BY a.cod_peri) perh
         WHERE ivc.copa_oid_para_gral = cpg.oid_para_gral
           AND cpg.oid_para_gral = ccp.copa_oid_para_gral
           AND ccp.copa_oid_para_gral = ivf.copa_oid_para_gral(+)
           AND ccp.clie_oid_clie = clien.oid_clie
           AND ccp.clie_oid_clie = ivf.clie_oid_clie(+)
           AND cpg.perd_oid_peri_desd = perd.oid_peri
           AND cpg.perd_oid_peri_hast = perh.oid_peri
           AND ((ivc.situacion = 'T' AND
               ((SELECT COUNT(*) FROM seg_perio_corpo b WHERE b.cod_peri <= pscodigoperiodo) -
               perh.conta BETWEEN 1 AND 5)) OR
               (ivc.situacion = 'V' AND
               perd.conta <=
               (SELECT COUNT(*) FROM seg_perio_corpo b WHERE b.cod_peri <= pscodigoperiodo)))

         GROUP BY clien.cod_clie,
                  ccp.clie_oid_clie,
                  ivf.clie_oid_clie,
                  perh.cod_peri,
                  perd.cod_peri,
                  cpg.num_conc,
                  cpg.oid_para_gral,
                  ivc.situacion,
                  cpg.tipo;
    ELSE
      DELETE FROM int_ivr_concu ivr
       WHERE EXISTS (SELECT DISTINCT coivr.num_conc,
                              coivr.cod_clie
                FROM inc_cuent_corri_punto iccp,
                     inc_concu_param_gener cpp,
                     mae_clien             clie,
                     int_ivr_concu         coivr
               WHERE iccp.copa_oid_para_gral = cpp.oid_para_gral
                 AND iccp.clie_oid_clie = clie.oid_clie
                 AND coivr.cod_clie = clie.cod_clie
                 AND REPLACE(cpp.num_conc,
                             '/',
                             '') = coivr.num_conc
                 AND iccp.fec_ulti_actu > coivr.fec_acum
                 AND ivr.cod_clie = coivr.cod_clie
                 AND ivr.num_conc = coivr.num_conc);

      INSERT INTO int_ivr_concu
        (cod_comp,
         cod_clie,
         ind_vige,
         num_conc,
         tip_conc,
         cod_peri_inic,
         cod_peri_fin,
         val_punt_acum,
         fec_acum,
         ind_valid,
         num_lote)
        SELECT lscodigocia codigocia,
               clien.cod_clie codigocliente,
               ivc.situacion indicadorvigencia,
               REPLACE(cpg.num_conc,
                       '/',
                       '') numeroconcurso,
               cpg.tipo tipoconcurso,
               perd.cod_peri periodoinicio,
               perh.cod_peri periodofin,
               SUM(ccp.num_punt) puntacumulado,
               ldfechaproceso fechaacumulacion,
               decode(ivc.situacion,
                      'V',
                      '',
                      decode(ivf.clie_oid_clie,
                             NULL,
                             'N',
                             'G')) flagvalidacion,
               psnumerolote

          FROM (SELECT ivd.num_conc,
                       ivd.copa_oid_para_gral,
                       decode(ivd.vico_oid_vige_conc,
                              1,
                              'V',
                              'T') situacion
                  FROM inc_versi_concu ivd
                 WHERE ivd.vico_oid_vige_conc IN (1,
                                                  6)) ivc,
               (SELECT cpg1.oid_para_gral,
                       cpg1.num_conc,
                       cpg1.perd_oid_peri_hast,
                       cpg1.perd_oid_peri_desd,
                       'V' tipo
                  FROM inc_concu_param_gener cpg1
                 WHERE cpg1.coiv_oid_conc_ivr = 1
                   AND cpg1.pais_oid_pais = lnidpais
                UNION
                SELECT cpg1.oid_para_gral,
                       cpg1.num_conc,
                       cpg1.perd_oid_peri_hast,
                       cpg1.perd_oid_peri_desd,
                       decode(cpg1.coiv_oid_conc_ivr,
                              2,
                              'C',
                              'P') tipo
                  FROM inc_concu_param_gener cpg1
                 WHERE (cpg1.coiv_oid_conc_ivr = 2 OR cpg1.coiv_oid_conc_ivr = 3)
                   AND cpg1.pais_oid_pais = lnidpais) cpg,
               inc_cuent_corri_punto ccp,
               mae_clien clien,
               (SELECT gan.clie_oid_clie,
                       pgp.copa_oid_para_gral
                  FROM inc_param_gener_premi pgp,
                       inc_param_nivel_premi pnp,
                       inc_ganad             gan
                 WHERE gan.panp_oid_para_nive_prem = pnp.oid_para_nive_prem
                   AND pnp.pagp_oid_para_gene_prem = pgp.oid_para_gene_prem
                 GROUP BY gan.clie_oid_clie,
                          pgp.copa_oid_para_gral) ivf,
               (SELECT c.oid_peri,
                       a.cod_peri,
                       (SELECT COUNT(*) FROM seg_perio_corpo b WHERE b.cod_peri <= a.cod_peri) AS conta
                  FROM seg_perio_corpo a,
                       cra_perio       c
                 WHERE a.oid_peri = c.peri_oid_peri
                 ORDER BY a.cod_peri) perd,
               (SELECT c.oid_peri,
                       a.cod_peri,
                       (SELECT COUNT(*) FROM seg_perio_corpo b WHERE b.cod_peri <= a.cod_peri) AS conta
                  FROM seg_perio_corpo a,
                       cra_perio       c
                 WHERE a.oid_peri = c.peri_oid_peri
                 ORDER BY a.cod_peri) perh
         WHERE ivc.copa_oid_para_gral = cpg.oid_para_gral
           AND cpg.oid_para_gral = ccp.copa_oid_para_gral
           AND ccp.copa_oid_para_gral = ivf.copa_oid_para_gral(+)
           AND ccp.clie_oid_clie = clien.oid_clie
           AND ccp.clie_oid_clie = ivf.clie_oid_clie(+)
           AND cpg.perd_oid_peri_desd = perd.oid_peri
           AND cpg.perd_oid_peri_hast = perh.oid_peri
           AND ((ivc.situacion = 'T' AND
               ((SELECT COUNT(*) FROM seg_perio_corpo b WHERE b.cod_peri <= pscodigoperiodo) -
               perh.conta BETWEEN 1 AND 5)) OR
               (ivc.situacion = 'V' AND
               perh.conta >=
               (SELECT COUNT(*) FROM seg_perio_corpo b WHERE b.cod_peri <= pscodigoperiodo) AND
               perd.conta <=
               (SELECT COUNT(*) FROM seg_perio_corpo b WHERE b.cod_peri <= pscodigoperiodo)))
           AND NOT EXISTS (SELECT 1
                  FROM int_ivr_concu ivrc
                 WHERE ivrc.cod_clie = clien.cod_clie
                   AND ivrc.num_conc = REPLACE(cpg.num_conc,
                                               '/',
                                               ''))

         GROUP BY clien.cod_clie,
                  ccp.clie_oid_clie,
                  ivf.clie_oid_clie,
                  perh.cod_peri,
                  perd.cod_peri,
                  cpg.num_conc,
                  cpg.oid_para_gral,
                  ivc.situacion,
                  cpg.tipo;
    END IF;

    l_user := USER;
    dbms_stats.gather_table_stats(ownname => l_user,
                                  tabname => 'int_ivr_concu',
                                  cascade => TRUE);

    lnnumregistros := 0;
    lbabrirutlfile := TRUE;

    /* Generando Archivo de Texto (Detalle) */
    OPEN c_interfaz(lnmaxcarcodclie);
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
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP

          lslinea := interfazrecord(x).codigocia || ';' || interfazrecord(x).codigocliente || ';' || interfazrecord(x)
                     .indvigencia || ';' || interfazrecord(x).numconcurso || ';' || interfazrecord(x)
                     .tipoconcurso || ';' || interfazrecord(x).periodoinicio || ';' || interfazrecord(x)
                     .periodofin || ';' || interfazrecord(x).puntajeconcurso || ';' || interfazrecord(x)
                     .fechaproceso || ';' || interfazrecord(x).indvalidacion;

          utl_file.put_line(v_handle,
                            lslinea);
          lnnumregistros := lnnumregistros + 1;
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
    END IF;

    INSERT INTO int_ivr_ctrl
      (cod_comp,
       nom_arch,
       val_cant_regi,
       ind_esta_actu)
    VALUES
      (lscodigocia,
       psnombrearchivo,
       lnnumregistros,
       '0');

    IF NOT lbabrirutlfile THEN
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
                              'ERROR INT_PR_IVR_ENVI_TABLA_CONCU: ' || ls_sqlerrm);
  END int_pr_ivr_envi_tabla_concu;
  /***************************************************************************
  Descripcion       : Genera Interfaz Emviar Tabla de Motivos de Rechazo a IVR
  Fecha Creacion    : 09/02/2008
  Autor             : José A. Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_ivr_envi_motiv_recha
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigoiso      VARCHAR2
  ) IS
    CURSOR c_interfaz IS

      SELECT cod_comp      codigocia,
             cod_moti_rech codmotrechazo,
             des_moti_rech desmotrechazo
        FROM int_ivr_motiv_recha;

    TYPE interfazrec IS RECORD(
      codigocia     int_ivr_motiv_recha.cod_comp %TYPE,
      codmotrechazo int_ivr_motiv_recha.cod_moti_rech %TYPE,
      desmotrechazo int_ivr_motiv_recha.des_moti_rech %TYPE);

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo VARCHAR2(50);

    lnidpais       NUMBER;
    lscodigocia    int_ivr_param_compa.cod_comp%TYPE;
    lbabrirutlfile BOOLEAN;
    lnnumregistros NUMBER := 0;
    l_user         VARCHAR2(20);

  BEGIN
    /* Obtiene el codigo de Compañia */
    SELECT int_ivr_param_compa.cod_comp
      INTO lscodigocia
      FROM int_ivr_param_compa
     WHERE int_ivr_param_compa.cod_pais = pscodigopais;

    /* obteniendo id's */
    lnidpais := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais); -- id del pais consultante

    --Borrando la tabla
    EXECUTE IMMEDIATE 'TRUNCATE TABLE int_ivr_motiv_recha';

    --Insertamos los datos
    INSERT INTO int_ivr_motiv_recha
      (cod_comp,
       cod_moti_rech,
       des_moti_rech)
      SELECT int_ivr_param_compa.cod_comp codigocia,
             cod_rech_desb codigo,
             gen_pkg_gener.gen_fn_devuelve_descripcion(oid_moti_rech_desb,
                                                       'REC_MOTIV_RECHA_DESBL',
                                                       pscodigoiso) descripcion
        FROM rec_motiv_recha_desbl,
             int_ivr_param_compa

       WHERE pais_oid_pais = lnidpais
         AND int_ivr_param_compa.cod_pais = pscodigopais;

    lnnumregistros := 0;

    l_user := USER;
    dbms_stats.gather_table_stats(ownname => l_user,
                                  tabname => 'int_ivr_motiv_recha',
                                  cascade => TRUE);

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

      IF interfazrecord.COUNT > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP

          lslinea := interfazrecord(x).codigocia || ';' || interfazrecord(x).codmotrechazo || ';' || interfazrecord(x)
                     .desmotrechazo;

          lnnumregistros := lnnumregistros + 1;
          utl_file.put_line(v_handle,
                            lslinea);
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

    INSERT INTO int_ivr_ctrl
      (cod_comp,
       nom_arch,
       val_cant_regi,
       ind_esta_actu)
    VALUES
      (lscodigocia,
       psnombrearchivo,
       lnnumregistros,
       '0');

    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_IVR_ENVI_TABLA_MOTIV_RECHA: ' || ls_sqlerrm);
  END int_pr_ivr_envi_motiv_recha;

  /***************************************************************************
  Descripcion       : Genera Interfaz Emviar Tabla de Control de IVR
  Fecha Creacion    : 09/02/2008
  Autor             : José A. Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_ivr_envi_tabla_ctrl
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
    CURSOR c_interfaz IS

      SELECT cod_comp      codigocia,
             nom_arch      nombrearchivo,
             val_cant_regi cantidadregistros,
             ind_esta_actu estadoactualizacion
        FROM int_ivr_ctrl;

    TYPE interfazrec IS RECORD(
      codigocia           int_ivr_ctrl.cod_comp%TYPE,
      nombrearchivo       int_ivr_ctrl.nom_arch%TYPE,
      cantidadregistros   int_ivr_ctrl.val_cant_regi%TYPE,
      estadoactualizacion int_ivr_ctrl.ind_esta_actu%TYPE

      );

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo VARCHAR2(50);

  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                           pscodigosistema,
                                           pscodigointerfaz,
                                           psnombrearchivo,
                                           lsdirtempo,
                                           lsnombrearchivo,
                                           v_handle);

    /* Generando Archivo de Texto (Detalle) */
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      IF interfazrecord.COUNT > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP

          lslinea := interfazrecord(x)
                     .codigocia || ';' || interfazrecord(x).nombrearchivo || ';' || interfazrecord(x)
                     .cantidadregistros || ';' || interfazrecord(x).estadoactualizacion;

          utl_file.put_line(v_handle,
                            lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    utl_file.fclose(v_handle);

    /* Procedimiento final para generar interfaz */
    gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                           lsdirtempo,
                                           psnombrearchivo,
                                           lsnombrearchivo);
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_IVR_ENVI_TABLA_CTRL: ' || ls_sqlerrm);
  END int_pr_ivr_envi_tabla_ctrl;

  /**************************************************************************
  Descripcion       : Obtiene el nombre de Archivo IVR
  Fecha Creacion    : 17/01/2008
  Parametros Entrada:

  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION int_fn_devue_nomb_arch_ivr
  (
    pscodigotabla VARCHAR2,
    pscodigopais  VARCHAR2,
    psinfoadic    VARCHAR2
  ) RETURN VARCHAR2 IS

    lsnombrearchivo VARCHAR2(100);
    lscodigocia     int_ivr_param_compa.cod_comp%TYPE;

  BEGIN

    SELECT int_ivr_param_compa.cod_comp
      INTO lscodigocia
      FROM int_ivr_param_compa
     WHERE int_ivr_param_compa.cod_pais = pscodigopais;

    lsnombrearchivo := pscodigotabla || lscodigocia ||
                       to_char(SYSDATE,
                               'YYYYMMDD') || psinfoadic;

    RETURN lsnombrearchivo;
  EXCEPTION

    WHEN no_data_found THEN
      RETURN '';

    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR INT_FN_DEVUE_NOMB_ARCH_IVR: ' || ls_sqlerrm);

  END int_fn_devue_nomb_arch_ivr;

  /**************************************************************************
  Descripcion       : Obtiene el indicador de marca de situación, indicador
                      de incobrable:
                      (I) Incobrable
                      (N) Normal

  Fecha Creacion    : 11/02/2008
  Parametros Entrada:

  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION int_fn_devue_marca_situa(pnoidcliente NUMBER) RETURN VARCHAR2 IS

    lnindicador            NUMBER;
    lnoidtipobloqfinaciero NUMBER;

  BEGIN

    --Obtengo el Tipo de Bloqueo finaciero
    SELECT a.oid_tipo_bloq
      INTO lnoidtipobloqfinaciero
      FROM mae_tipo_bloqu a
     WHERE a.cod_tipo_bloq = '02';

    SELECT COUNT(*)
      INTO lnindicador
      FROM mae_clien_bloqu bloq
     WHERE bloq.clie_oid_clie = pnoidcliente
       AND bloq.tibq_oid_tipo_bloq = lnoidtipobloqfinaciero
       AND bloq.fec_desb IS NULL;

    IF lnindicador > 0 THEN
      RETURN 'I';
    ELSE
      RETURN 'N';
    END IF;

  EXCEPTION

    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR INT_FN_DEVUE_MARCA_SITUA: ' || ls_sqlerrm);

  END int_fn_devue_marca_situa;

  /**************************************************************************
  Descripcion       : Obtiene el indicador de Club Privilege
                      (1) Cliente inscrito club privilege
                      (0) Cliente NO inscrito club privilege

  Fecha Creacion    : 11/02/2008
  Parametros Entrada:

  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION int_fn_devue_indic_club_privi(pnoidcliente NUMBER) RETURN VARCHAR2 IS

    lnindicador NUMBER;

  BEGIN

    SELECT COUNT(*)
      INTO lnindicador
      FROM mae_clien_tipo_subti,
           mae_subti_clien,
           mae_tipo_clien,
           mae_clien_clasi,
           mae_tipo_clasi_clien
     WHERE mae_clien_tipo_subti.sbti_oid_subt_clie = mae_subti_clien.oid_subt_clie
       AND mae_tipo_clien.oid_tipo_clie = mae_subti_clien.ticl_oid_tipo_clie
       AND mae_tipo_clien.cod_tipo_clie = '02'
       AND mae_subti_clien.cod_subt_clie IN ('04',
                                             '06')
       AND mae_clien_clasi.ctsu_oid_clie_tipo_subt = mae_clien_tipo_subti.oid_clie_tipo_subt
       AND mae_clien_clasi.tccl_oid_tipo_clasi = mae_tipo_clasi_clien.oid_tipo_clas
       AND mae_tipo_clasi_clien.cod_tipo_clas = '11'
       AND mae_clien_tipo_subti.clie_oid_clie = pnoidcliente;

    IF lnindicador > 0 THEN
      RETURN '1';
    ELSE
      RETURN '0';
    END IF;

  EXCEPTION

    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR INT_FN_DEVUE_MARCA_SITUA: ' || ls_sqlerrm);

  END int_fn_devue_indic_club_privi;

  /**************************************************************************
  Descripcion       : Obtiene el indicador de Concurso
                      (1) Tiene Concursos
                      (0) No tiene concursos

  Fecha Creacion    : 11/02/2008
  Parametros Entrada:

  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION int_fn_devue_indic_concu(pscodigocliente VARCHAR2) RETURN VARCHAR2 IS
    lnindicador NUMBER;

  BEGIN
    SELECT COUNT(*)
      INTO lnindicador
      FROM int_ivr_concu conc
     WHERE conc.cod_clie = pscodigocliente
       AND rownum = 1;

    IF lnindicador > 0 THEN
      RETURN '1';
    ELSE
      RETURN '0';
    END IF;

  EXCEPTION

    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR INT_FN_DEVUE_MARCA_SITUA: ' || ls_sqlerrm);

  END int_fn_devue_indic_concu;

  /**************************************************************************
  Descripcion       : Devuelve un indicador de modificiacion de los datos de
                      cliente respecto a la tabla int_ivr_clien.
                      (S) Tiene Modificaciones
                      (N) No tiene Modificaciones

  Fecha Creacion    : 11/02/2008
  Parametros Entrada:

  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION int_fn_devue_inmod_clien
  (
    pscodigocliente VARCHAR2,
    pnoidcliente    NUMBER
  ) RETURN VARCHAR2 IS

    lnindicador   NUMBER;
    ldfechaultact DATE;

  BEGIN

    --Si encuenta el cliente determina la fecha de ultima actualizacion
    SELECT MAX(ci.fec_envi_nove)
      INTO ldfechaultact
      FROM int_ivr_clien ci
     WHERE cod_clie = pscodigocliente;

    --Verifica que no se hayan hecho modificaciones en mae_clien
    SELECT COUNT(*)
      INTO lnindicador
      FROM mae_clien cl
     WHERE cl.oid_clie = pnoidcliente
       AND cl.fec_ulti_actu > ldfechaultact;
    --Si no encontro registros
    IF lnindicador = 0 THEN
      RETURN 'N';
    END IF;

    --Verifica que no se hayan hecho modificaciones en mae_clien_unida_admin
    SELECT COUNT(*)
      INTO lnindicador
      FROM mae_clien_unida_admin clua
     WHERE clua.clie_oid_clie = pnoidcliente
       AND clua.fec_ulti_actu > ldfechaultact;
    --Si no encontro registros
    IF lnindicador = 0 THEN
      RETURN 'N';
    END IF;

    --Verifica que no se hayan hecho modificaciones en ccc_movim_cuent_corri
    SELECT COUNT(*)
      INTO lnindicador
      FROM ccc_movim_cuent_corri mcc
     WHERE mcc.clie_oid_clie = pnoidcliente
       AND mcc.fec_ulti_actu > ldfechaultact;
    --Si no encontro registros
    IF lnindicador = 0 THEN
      RETURN 'N';
    END IF;

    --Verifica que no se hayan hecho modificaciones en ccc_movim_cuent_corri
    SELECT COUNT(*)
      INTO lnindicador
      FROM int_ivr_concu conc
     WHERE conc.cod_clie = pscodigocliente
       AND conc.fec_acum > ldfechaultact;
    IF lnindicador = 0 THEN
      RETURN 'N';
    END IF;

    RETURN 'S';

  EXCEPTION
    WHEN no_data_found THEN
      RETURN 'S';

    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR INT_FN_DEVUE_INMOD_CLIEN: ' || ls_sqlerrm);

  END int_fn_devue_inmod_clien;

  /**************************************************************************
  Descripcion       : Devuelve un indicador de modificiacion de los datos de
                      concurso respecto a la tabla int_ivr_clien.
                      (S) Tiene Modificaciones
                      (N) No tiene Modificaciones

  Fecha Creacion    : 11/02/2008
  Parametros Entrada:

  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION int_fn_devue_inmod_concu
  (
    pscodigoconcurso VARCHAR2,
    pnoidconcurso    NUMBER
  ) RETURN VARCHAR2 IS

    lnindicador   NUMBER;
    ldfechaultact DATE;

  BEGIN

    --Si encuenta el cliente determina la fecha de ultima actualizacion
    SELECT MAX(ci.fec_acum)
      INTO ldfechaultact
      FROM int_ivr_concu ci
     WHERE ci.num_conc = pscodigoconcurso;

    --Verifica que no se hayan hecho modificaciones en mae_clien
    SELECT COUNT(*)
      INTO lnindicador
      FROM inc_cuent_corri_punto cl
     WHERE cl.copa_oid_para_gral = pnoidconcurso
       AND cl.fec_ulti_actu > ldfechaultact;
    --Si no encontro registros
    IF lnindicador = 0 THEN
      RETURN 'N';
    END IF;
    RETURN 'S';
  EXCEPTION
    WHEN no_data_found THEN
      RETURN 'S';
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR INT_FN_DEVUE_INMOD_CONCU: ' || ls_sqlerrm);

  END int_fn_devue_inmod_concu;

  /***************************************************************************
  Descripcion       : Genera Interfaz Emviar Tabla de Post Venta
  Fecha Creacion    : 27/02/2008
  Autor             : José A. Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_ivr_envi_tabl_post_vent
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigomarca    VARCHAR2,
    pscodigocanal    VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    psperiodocorte   VARCHAR2
  ) IS
    CURSOR c_interfaz(lnmaxcarcodclie NUMBER) IS

      SELECT cod_comp,
             lpad(cod_clie,
                  lnmaxcarcodclie,
                  '0'),
             lpad(val_nume_serv,
                  8,
                  '0'),
             cod_oper_serv,
             cod_prod,
             des_prod,
             cod_prod_aten,
             des_prod_aten,
             val_cant_aten,
             ind_aten,
             cod_moti_recha,
             to_char(val_mnto_serv,
                     '99999999990.00'),
             cod_peri_proc
        FROM int_ivr_post_venta;

    TYPE interfazrec IS RECORD(
      codigocia              int_ivr_post_venta.cod_comp%TYPE,
      codigocliente          int_ivr_post_venta.cod_clie%TYPE,
      numservicio            VARCHAR2(8),
      operacion              int_ivr_post_venta.cod_oper_serv%TYPE,
      codigoproducto         int_ivr_post_venta.cod_prod%TYPE,
      descproducto           int_ivr_post_venta.des_prod%TYPE,
      codigoproductoatendido int_ivr_post_venta.cod_prod_aten%TYPE,
      descproductoatendido   int_ivr_post_venta.des_prod_aten%TYPE,
      cantidad               int_ivr_post_venta.val_cant_aten%TYPE,
      indicadoratencion      int_ivr_post_venta.ind_aten%TYPE,
      motivorechazo          int_ivr_post_venta.cod_moti_recha%TYPE,
      valor                  VARCHAR2(20),
      periodoproceso         int_ivr_post_venta.cod_peri_proc%TYPE

      );

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo         bas_inter.dir_temp%TYPE;
    w_filas            NUMBER := 1000;
    v_handle           utl_file.file_type;
    lslinea            VARCHAR2(1000);
    lsnombrearchivo    VARCHAR2(50);
    lnidmarca          NUMBER;
    lnidcanal          NUMBER;
    lnidperiodoproceso NUMBER;

    lscodigocia int_ivr_param_compa.cod_comp%TYPE;

    lnnumregistros          NUMBER := 0;
    lnmaxcarcodclie         NUMBER := 10;
    lnidpais                NUMBER;
    lscodigoperiodoanterior seg_perio_corpo.cod_peri%TYPE;
    lbabrirutlfile          BOOLEAN;
    lnidperiodoanterior     NUMBER;
    l_user                  VARCHAR2(20);

    lnmaxdigitos          NUMBER := 10;

  BEGIN

    /* Obtiene el codigo de Compañia */
    SELECT int_ivr_param_compa.cod_comp
      INTO lscodigocia
      FROM int_ivr_param_compa
     WHERE int_ivr_param_compa.cod_pais = pscodigopais;

    /* obteniendo id's */
    lnidpais  := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais); -- id del pais consultante
    lnidmarca := gen_pkg_gener.gen_fn_devuelve_id_marca(pscodigomarca);
    lnidcanal := gen_pkg_gener.gen_fn_devuelve_id_canal(pscodigocanal);

    lnidperiodoproceso := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo,
                                                                     lnidmarca,
                                                                     lnidcanal);

    lscodigoperiodoanterior := per_pkg_repor_perce.per_fn_obtie_perio(pscodigoperiodo,
                                                                      lnidpais,
                                                                      lnidmarca,
                                                                      lnidcanal,
                                                                      -1);

    lnidperiodoanterior := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(lscodigoperiodoanterior,
                                                                      lnidmarca,
                                                                      lnidcanal);

    --Borrando la tabla
    EXECUTE IMMEDIATE 'TRUNCATE TABLE int_ivr_post_venta';

    --Insertamos los datos
    INSERT INTO int_ivr_post_venta
      (cod_comp,
       cod_clie,
       val_nume_serv,
       cod_oper_serv,
       cod_prod,
       des_prod,
       cod_prod_aten,
       des_prod_aten,
       val_cant_aten,
       ind_aten,
       cod_moti_recha,
       val_mnto_serv,
       cod_peri_proc)
      SELECT lscodigocia codigocia,
             gen_pkg_gener.gen_fn_devuelve_cod_clie(a.clie_oid_clie),

             CASE
               WHEN psperiodocorte IS NOT NULL AND
                    gen_pkg_gener.gen_fn_devuelve_des_perio(a.perd_oid_peri_recl) >= psperiodocorte THEN
                substr(lpad(a.num_recl,
                            lnmaxdigitos,
                            '0'),
                       lnmaxdigitos - 8 + 1,
                       8)
               WHEN psperiodocorte IS NOT NULL AND
                    gen_pkg_gener.gen_fn_devuelve_des_perio(a.perd_oid_peri_recl) < psperiodocorte THEN
                substr(lpad(a.num_recl,
                            lnmaxdigitos,
                            '0'),
                       lnmaxdigitos - 7 + 1,
                       7)
               ELSE
                to_char(a.num_recl)
             END numservpost,
             CASE
               WHEN e.cod_oper = 'TM' OR e.cod_oper = 'C' THEN
                'CM'
               WHEN e.cod_oper = 'TP' THEN
                'CP'
               WHEN e.cod_oper = 'DM' OR e.cod_oper = 'DE' OR e.cod_oper = 'DN' THEN
                'D'
               WHEN e.cod_oper = 'FM' OR e.cod_oper = 'FA' THEN
                'FM'
               ELSE
                e.cod_oper
             END codoper,
             z4.val_codi_vent codventa,
             pr.des_cort descprod,
             NULL codprodaten,
             NULL desprodaten,
             c.num_unid_recl cantidad,
             '2' indatencion,
             NULL motivorechazo,
             CASE
               WHEN e.cod_oper = 'DN' OR e.cod_oper = 'DE' OR e.cod_oper = 'DM' OR e.cod_oper = 'FM' THEN
                c.imp_abon
               ELSE
                0
             END valor,
             gen_pkg_gener.gen_fn_devuelve_des_perio(a.perd_oid_peri_recl)
        FROM rec_cabec_recla a,
             rec_opera_recla b,
             rec_linea_opera_recla c,
             rec_tipo_movim d,
             rec_tipos_opera f,
             rec_opera e,
             (SELECT z2.prod_oid_prod,
                     z2.val_codi_vent,
                     z1.oid_matr_fact,
                     z2.tofe_oid_tipo_ofer
                FROM pre_matri_factu       z1,
                     pre_ofert_detal       z2,
                     pre_matri_factu_cabec z3
               WHERE z3.oid_cabe = z1.mfca_oid_cabe
                 AND z1.ofde_oid_deta_ofer = z2.oid_deta_ofer) z4,
             mae_produ pr
       WHERE a.oid_cabe_recl = b.care_oid_cabe_recl
         AND b.oid_oper_recl = c.opre_oid_oper_recl
         AND c.timo_oid_tipo_movi = d.oid_tipo_movi
         AND a.perd_oid_peri_recl IN (lnidperiodoproceso,
                                      lnidperiodoanterior) --parametro --
         AND d.cod_tipo_movi = 'D'
         AND b.tiop_oid_tipo_oper = f.oid_tipo_oper
         AND f.rope_oid_oper = e.oid_oper
         AND e.cod_oper IN ('DN',
                            'DE',
                            'DM',
                            'TM',
                            'TP',
                            'FM',
                            'FA',
                            'C')
         AND c.prod_oid_prod = z4.prod_oid_prod(+)
         AND z4.tofe_oid_tipo_ofer(+) = c.tofe_oid_tipo_ofer
         AND z4.oid_matr_fact(+) = c.mafa_oid_matr_fact
         AND pr.oid_prod = c.prod_oid_prod;

    lnnumregistros := 0;

    l_user := USER;
    dbms_stats.gather_table_stats(ownname => l_user,
                                  tabname => 'int_ivr_post_venta',
                                  cascade => TRUE);

    /* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;
    OPEN c_interfaz(lnmaxcarcodclie);
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
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP

          lslinea        := interfazrecord(x)
                            .codigocia || ';' || interfazrecord(x).codigocliente || ';' || interfazrecord(x)
                            .numservicio || ';' || interfazrecord(x).operacion || ';' || interfazrecord(x)
                            .codigoproducto || ';' || interfazrecord(x).descproducto || ';' || interfazrecord(x)
                            .codigoproductoatendido || ';' || interfazrecord(x).descproductoatendido || ';' || interfazrecord(x)
                            .cantidad || ';' || interfazrecord(x).indicadoratencion || ';' || interfazrecord(x)
                            .motivorechazo || ';' || interfazrecord(x).valor || ';' || interfazrecord(x)
                            .periodoproceso;
          lnnumregistros := lnnumregistros + 1;
          utl_file.put_line(v_handle,
                            lslinea);
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

    INSERT INTO int_ivr_ctrl
      (cod_comp,
       nom_arch,
       val_cant_regi,
       ind_esta_actu)
    VALUES
      (lscodigocia,
       psnombrearchivo,
       lnnumregistros,
       '0');

    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_IVR_ENVI_TABL_POST_VENT: ' || ls_sqlerrm);
  END int_pr_ivr_envi_tabl_post_vent;

  /**************************************************************************
  Descripcion       : Obtiene el indicador de CDR
                      (1) Tiene registros en post venta
                      (0) No tiene registros en post venta

  Fecha Creacion    : 27/02/2008
  Parametros Entrada:

  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION int_fn_devue_indic_cdr(pscodigocliente VARCHAR2) RETURN VARCHAR2 IS
    lnindicador NUMBER;

  BEGIN
    SELECT COUNT(*)
      INTO lnindicador
      FROM int_ivr_post_venta cdr
     WHERE cdr.cod_clie = pscodigocliente
       AND rownum = 1;
    IF lnindicador > 0 THEN
      RETURN '1';
    ELSE
      RETURN '0';
    END IF;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR INT_FN_DEVUE_INDIC_CDR: ' || ls_sqlerrm);

  END int_fn_devue_indic_cdr;

  /**************************************************************************
  Descripcion       : Obtiene el indicador de CDR
                      (1) Tiene registros en post venta
                      (0) No tiene registros en post venta

  Fecha Creacion    : 27/02/2008
  Parametros Entrada:

  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION int_fn_calcu_valor_saldo_venci
  (
    pscodigopais    VARCHAR2,
    pscodigomarca   VARCHAR2,
    pscodigocanal   VARCHAR2,
    pscodigoperiodo VARCHAR2,
    pnoidcliente    NUMBER
  ) RETURN NUMBER IS

    ldfechavenci DATE;
    lsidregion   zon_regio.cod_regi%TYPE;
    lsidzona     zon_zona.cod_zona%TYPE;

    lnsaldo      NUMBER;
    lnsaldofavor NUMBER;

    lnbanco NUMBER;

    lncupon NUMBER;

    lnresultado NUMBER;

  BEGIN
    BEGIN
      SELECT nvl(zon_zona.cod_zona,
                 ''),
             nvl(zon_regio.cod_regi,
                 '')
        INTO lsidzona,
             lsidregion
        FROM mae_clien,
             mae_clien_unida_admin,
             zon_terri_admin,
             zon_secci,
             zon_zona,
             zon_regio
       WHERE ((zon_terri_admin.oid_terr_admi = mae_clien_unida_admin.ztad_oid_terr_admi) AND
             (zon_secci.oid_secc = zon_terri_admin.zscc_oid_secc) AND
             (zon_zona.oid_zona = zon_secci.zzon_oid_zona) AND
             (zon_regio.oid_regi = zon_zona.zorg_oid_regi) AND
             (mae_clien.oid_clie = mae_clien_unida_admin.clie_oid_clie) AND
             (mae_clien_unida_admin.ind_acti = '1') AND (mae_clien.oid_clie = pnoidcliente) AND
             (rownum = 1));
    EXCEPTION
      WHEN no_data_found THEN
        RETURN 0;
    END;

    ldfechavenci := gen_pkg_gener.gen_fn_obtie_fecha_venci(pscodigopais,
                                                           pscodigomarca,
                                                           pscodigocanal,
                                                           pscodigoperiodo,
                                                           lsidregion,
                                                           lsidzona,
                                                           pnoidcliente);

    WITH temp_marca AS
     (SELECT mta.masi_oid_marc_sali
        FROM ccc_proce            cp,
             ccc_subpr            csp,
             ccc_tipo_abono_subpr tasp,
             ccc_marca_tipo_abono mta
       WHERE ((cp.oid_proc = csp.ccpr_oid_proc) AND (csp.oid_subp = tasp.subp_oid_subp) AND
             (tasp.oid_tipo_abon_subp = mta.tasp_oid_tipo_abon_subp) AND (cp.cod_proc = 'CON001') AND
             (mta.ind_entr_sali = 'E')))
    SELECT SUM (ccc.imp_pend)
      INTO lnsaldo
      FROM ccc_movim_cuent_corri ccc, temp_marca
     WHERE ccc.masi_oid_marc_situ = temp_marca.masi_oid_marc_sali AND ccc.imp_pend > 0 -- positivos
    AND ccc.fec_venc <= ldfechavenci AND ccc.clie_oid_clie = pnoidcliente;

    IF lnsaldo IS NULL THEN
      lnsaldo := 0;
    END IF;

    WITH temp_marca AS
     (SELECT mta.masi_oid_marc_sali
        FROM ccc_proce            cp,
             ccc_subpr            csp,
             ccc_tipo_abono_subpr tasp,
             ccc_marca_tipo_abono mta
       WHERE ((cp.oid_proc = csp.ccpr_oid_proc) AND (csp.oid_subp = tasp.subp_oid_subp) AND
             (tasp.oid_tipo_abon_subp = mta.tasp_oid_tipo_abon_subp) AND (cp.cod_proc = 'CON001') AND
             (mta.ind_entr_sali = 'E')))
    SELECT SUM (mcc.imp_pend)
      INTO lnsaldofavor
      FROM ccc_movim_cuent_corri mcc, temp_marca
     WHERE mcc.masi_oid_marc_situ = temp_marca.masi_oid_marc_sali AND mcc.imp_pend < 0 -- negativos
    AND mcc.clie_oid_clie = pnoidcliente;

    IF lnsaldofavor IS NULL THEN
      lnsaldofavor := 0;
    END IF;

    SELECT SUM(imp_sald_pend)
      INTO lnbanco
      FROM ccc_movim_banca
     WHERE clie_oid_clie = pnoidcliente
       AND cod_iden_proc = 'P';
    IF lnbanco IS NULL THEN
      lnbanco := 0;
    END IF;

    SELECT SUM(dct.imp_deta)
      INTO lncupon
      FROM mae_clien                   mc,
           ccc_detal_cupon_trami_depur dct,
           ccc_situa_cupon             sc
     WHERE ((sc.oid_situ_cupo = dct.sicu_oid_situ_cupo) AND (sc.cod_situ_cupo = 'T') AND
           (mc.oid_clie = dct.clie_oid_clie) AND (mc.oid_clie = pnoidcliente));
    IF lncupon IS NULL THEN
      lncupon := 0;
    END IF;

    lnresultado := lnsaldo + lnsaldofavor - lnbanco - lncupon;

    IF lnresultado < 0 THEN
      lnresultado := 0;
    END IF;

    RETURN lnresultado;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR INT_FN_CALCU_VALOR_SALDO_VENCI: ' || ls_sqlerrm);

  END int_fn_calcu_valor_saldo_venci;

  /**************************************************************************
  Descripcion       : Obtiene el indicador de CDR
                      (1) Tiene registros en post venta
                      (0) No tiene registros en post venta

  Fecha Creacion    : 27/02/2008
  Parametros Entrada:

  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION int_fn_calcu_valor_saldo_venci
  (
    pnoidcliente NUMBER,
    pdfechavenci DATE
  ) RETURN NUMBER IS
    lnua         NUMBER;
    lnsaldo      NUMBER;
    lnsaldofavor NUMBER;
    lnbanco      NUMBER;
    lncupon      NUMBER;
    lnresultado  NUMBER;

  BEGIN

    BEGIN
      SELECT 1
        INTO lnua
        FROM mae_clien_unida_admin ua,
             zon_terri_admin       zta,
             zon_secci             zs,
             zon_zona              zz,
             zon_regio             zr
       WHERE zta.oid_terr_admi = ua.ztad_oid_terr_admi
         AND zs.oid_secc = zta.zscc_oid_secc
         AND zz.oid_zona = zs.zzon_oid_zona
         AND zr.oid_regi = zz.zorg_oid_regi
         AND ua.clie_oid_clie = pnoidcliente
         AND ua.ind_acti = '1'
         AND rownum = 1;
    EXCEPTION
      WHEN no_data_found THEN
        RETURN 0;
    END;

    WITH temp_marca AS
     (SELECT mta.masi_oid_marc_sali
        FROM ccc_proce            cp,
             ccc_subpr            csp,
             ccc_tipo_abono_subpr tasp,
             ccc_marca_tipo_abono mta
       WHERE ((cp.oid_proc = csp.ccpr_oid_proc) AND (csp.oid_subp = tasp.subp_oid_subp) AND
             (tasp.oid_tipo_abon_subp = mta.tasp_oid_tipo_abon_subp) AND (cp.cod_proc = 'CON001') AND
             (mta.ind_entr_sali = 'E')))
    SELECT SUM (ccc.imp_pend)
      INTO lnsaldo
      FROM ccc_movim_cuent_corri ccc, temp_marca
     WHERE ccc.masi_oid_marc_situ = temp_marca.masi_oid_marc_sali AND ccc.imp_pend > 0 -- positivos
    AND ccc.fec_venc <= pdfechavenci AND ccc.clie_oid_clie = pnoidcliente;

    IF lnsaldo IS NULL THEN
      lnsaldo := 0;
    END IF;

    WITH temp_marca AS
     (SELECT mta.masi_oid_marc_sali
        FROM ccc_proce            cp,
             ccc_subpr            csp,
             ccc_tipo_abono_subpr tasp,
             ccc_marca_tipo_abono mta
       WHERE ((cp.oid_proc = csp.ccpr_oid_proc) AND (csp.oid_subp = tasp.subp_oid_subp) AND
             (tasp.oid_tipo_abon_subp = mta.tasp_oid_tipo_abon_subp) AND (cp.cod_proc = 'CON001') AND
             (mta.ind_entr_sali = 'E')))
    SELECT SUM (mcc.imp_pend)
      INTO lnsaldofavor
      FROM ccc_movim_cuent_corri mcc, temp_marca
     WHERE mcc.masi_oid_marc_situ = temp_marca.masi_oid_marc_sali AND mcc.imp_pend < 0 -- negativos
    AND mcc.clie_oid_clie = pnoidcliente;

    IF lnsaldofavor IS NULL THEN
      lnsaldofavor := 0;
    END IF;

    SELECT SUM(imp_sald_pend)
      INTO lnbanco
      FROM ccc_movim_banca
     WHERE clie_oid_clie = pnoidcliente
       AND cod_iden_proc = 'P';
    IF lnbanco IS NULL THEN
      lnbanco := 0;
    END IF;

    SELECT SUM(dct.imp_deta)
      INTO lncupon
      FROM mae_clien                   mc,
           ccc_detal_cupon_trami_depur dct,
           ccc_situa_cupon             sc
     WHERE ((sc.oid_situ_cupo = dct.sicu_oid_situ_cupo) AND (sc.cod_situ_cupo = 'T') AND
           (mc.oid_clie = dct.clie_oid_clie) AND (mc.oid_clie = pnoidcliente));
    IF lncupon IS NULL THEN
      lncupon := 0;
    END IF;

    lnresultado := lnsaldo + lnsaldofavor - lnbanco - lncupon;

    IF lnresultado < 0 THEN
      lnresultado := 0;
    END IF;

    RETURN lnresultado;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR INT_FN_CALCU_VALOR_SALDO_VENCI: ' || ls_sqlerrm);

  END int_fn_calcu_valor_saldo_venci;


  /***************************************************************************
Descripcion       : Registra los datos de archivos procesados en el envio
                      de IVR Corporativo.
  Fecha Creacion    : 21/07/2009
  Autor             : Jose Cairampoma
  ***************************************************************************/
PROCEDURE INT_PR_IVR_CORPO_REGIS_CNTRL
  (
    pscodigocia     VARCHAR2,
    psnombrearchivo VARCHAR2,
    pnnumregistros  NUMBER,
    pscodigoperiodo VARCHAR2,
    pnnumerodias    NUMBER := 0
  ) IS

  BEGIN

    INSERT INTO int_ivr_corpo_cntrl
      (
       cod_comp,
       val_nomb_arch,
       num_regi,
       ind_esta_actu,
       val_anio_camp,
       num_camp,
       val_anio_proc,
       val_mes_proc,
       val_dia_proc
      )
    VALUES
      (
       pscodigocia,
       psnombrearchivo,
       pnnumregistros,
       '0',
       substr(pscodigoperiodo,
              1,
              4),
       substr(pscodigoperiodo,
              5,
              2),
       to_char(SYSDATE + pnnumerodias,
               'YYYY'),
       to_char(SYSDATE + pnnumerodias,
               'MM'),
       to_char(SYSDATE + pnnumerodias,
               'DD')
      );

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123, 'ERROR INT_PR_IVR_CORPO_REGIS_CNTRL: ' || ls_sqlerrm);

END INT_PR_IVR_CORPO_REGIS_CNTRL;


  /**************************************************************************
  Descripcion       : Obtiene el indicador de marca de situación, indicador
                      de incobrable:
                      Si tipo Bloqueo = '01' (Administrativo)  -> Marca Situación = 'B'
                      Si tipo Bloqueo = '02' (Financiero)  -> Marca Situación = 'I'
                      SiNO  -> Marca Situación = 'N'

  Fecha Creacion    : 21/07/2009
  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION int_fn_devue_marca_situa_corpo(pnoidcliente NUMBER) RETURN VARCHAR2 IS

    lsmarcasituacion VARCHAR2(1);

  BEGIN

    SELECT decode(MAX(tb.cod_tipo_bloq),
                  '01',
                  'B',
                  '02',
                  'I',
                  'N')
      INTO lsmarcasituacion
      FROM mae_clien_bloqu b,
           mae_tipo_bloqu  tb
     WHERE b.clie_oid_clie = pnoidcliente
       AND b.tibq_oid_tipo_bloq = tb.oid_tipo_bloq
       AND b.fec_desb IS NULL;

    RETURN lsmarcasituacion;

  EXCEPTION

    WHEN no_data_found THEN
      RETURN 'N';

    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR INT_FN_DEVUE_MARCA_SITUA_CORPO: ' || ls_sqlerrm);

  END int_fn_devue_marca_situa_corpo;
  /**************************************************************************
  Descripcion       : Devuelve la situacion de del concurso
                      Si periodo menor que campaña actual = 'T'
                      Si periodo mayor o igual campaña acatula ='V'

  Fecha Creacion    : 22/07/2009
  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION int_fn_devue_situa_concu(pnoidperi NUMBER) RETURN VARCHAR2 IS

    lnperiodoactual seg_perio_corpo.cod_peri%TYPE;

  BEGIN

    SELECT MIN(oid_peri)
      INTO lnperiodoactual
      FROM cra_perio c
     WHERE c.fec_inic <= trunc(SYSDATE)
       AND c.fec_fina >= trunc(SYSDATE);

    IF pnoidperi < lnperiodoactual THEN
      RETURN 'T';
    ELSE
      RETURN 'V';
    END IF;

  EXCEPTION

    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR INT_FN_DEVUE_SITUA_CONCU: ' || ls_sqlerrm);

  END int_fn_devue_situa_concu;

  /**************************************************************************
  Descripcion       : Obtiene el indicador de Concurso
                      (1) Tiene Concursos
                      (0) No tiene concursos

  Fecha Creacion    : 11/02/2008
  Parametros Entrada:

  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION int_fn_devue_indic_concu_corpo(pscodigocliente VARCHAR2) RETURN VARCHAR2 IS
    lnindicador NUMBER;

  BEGIN
    SELECT COUNT(*)
      INTO lnindicador
      FROM int_ivr_corpo_concu conc
     WHERE conc.cod_clie = pscodigocliente
       AND rownum = 1;

    IF lnindicador > 0 THEN
      RETURN '1';
    ELSE
      RETURN '0';
    END IF;

  EXCEPTION

    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR INT_FN_DEVUE_INDIC_CONCU_CORPO: ' || ls_sqlerrm);

  END int_fn_devue_indic_concu_corpo;
  /**************************************************************************
  Descripcion       : Obtiene el indicador de CDR
                      (1) Tiene registros en post venta
                      (0) No tiene registros en post venta

  Fecha Creacion    : 27/02/2008
  Parametros Entrada:

  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION int_fn_devue_indic_cdr_corpo(pscodigocliente VARCHAR2) RETURN VARCHAR2 IS
    lnindicador NUMBER;

  BEGIN
    SELECT COUNT(*)
      INTO lnindicador
      FROM int_ivr_corpo_servi_poven cdr
     WHERE cdr.cod_clie = pscodigocliente
       AND rownum = 1;
    IF lnindicador > 0 THEN
      RETURN '1';
    ELSE
      RETURN '0';
    END IF;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR INT_FN_DEVUE_INDIC_CDR_CORPO: ' || ls_sqlerrm);

  END int_fn_devue_indic_cdr_corpo;
  /**************************************************************************
  Descripcion       : Obtiene el indicador de marca de situación, indicador
                      de incobrable:
                      Si tipo Bloqueo = '01' (Administrativo)  -> Marca Situación = 'B'
                      Si tipo Bloqueo = '02' (Financiero)  -> Marca Situación = 'I'
                      SiNO  -> Marca Situación = 'N'

  Fecha Creacion    : 21/07/2009
  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION int_fn_devue_perio_ultim_pedid(pnoidcliente NUMBER) RETURN VARCHAR2 IS

    lnoidperiodo ped_solic_cabec.perd_oid_peri%TYPE;

  BEGIN

    SELECT MAX(c.perd_oid_peri)
      INTO lnoidperiodo
      FROM ped_solic_cabec c
     WHERE clie_oid_clie = pnoidcliente
       AND c.tspa_oid_tipo_soli_pais =
           (SELECT tsp.oid_tipo_soli_pais
              FROM ped_tipo_solic_pais tsp,
                   ped_tipo_solic      ts
             WHERE ts.oid_tipo_soli = tsp.tsol_oid_tipo_soli
               AND ts.cod_tipo_soli = 'SOC');

    IF lnoidperiodo IS NULL THEN
      RETURN lnoidperiodo;
    ELSE
      RETURN gen_pkg_gener.gen_fn_devuelve_des_perio(lnoidperiodo);
    END IF;
  EXCEPTION

    WHEN no_data_found THEN
      RETURN 'N';

    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR INT_FN_DEVUE_PERIO_ULTIM_PEDID: ' || ls_sqlerrm);

  END int_fn_devue_perio_ultim_pedid;
  /**************************************************************************
  Descripcion       : Devuelve Telefono
                      Si tiene Caracteres no numeriocs 0
                      Caso Contrario se devuelve le mismo valor.
  Fecha Creacion    : 01/10/2009
  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION int_fn_devue_telef(pstelefono VARCHAR2) RETURN VARCHAR2 IS

    lnmumerico NUMBER(20);
  BEGIN

    lnmumerico := to_number(TRIM(pstelefono));

    RETURN nvl(pstelefono,
               0);

  EXCEPTION

    WHEN OTHERS THEN
      RETURN 0;

  END int_fn_devue_telef;


/*************************************************************************
Descripcion       : Genera Interfaz Enviar Control - IVR Corporativo
  Fecha Creacion    : 21/07/2009
  Autor             : José A. Cairampoma
**************************************************************************/
PROCEDURE INT_PR_IVR_CORPO_CNTRL
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    indnuevaversion  VARCHAR2
  ) IS

    CURSOR c_interfaz IS

      SELECT pscodigopais,
             cod_comp,
             val_nomb_arch,
             num_regi,
             ind_esta_actu,
             val_anio_camp,
             num_camp,
             val_anio_proc,
             val_mes_proc,
             val_dia_proc
        FROM int_ivr_corpo_cntrl;

    TYPE interfazrec IS RECORD
    (
      codigopais          bas_pais.cod_pais%TYPE,
      codigocia           int_ivr_corpo_cntrl.cod_comp%TYPE,
      nombrearchivo       int_ivr_corpo_cntrl.val_nomb_arch%TYPE,
      cantidadregistros   int_ivr_corpo_cntrl.num_regi%TYPE,
      estadoactualizacion int_ivr_corpo_cntrl.ind_esta_actu%TYPE,
      anioperiodo         int_ivr_corpo_cntrl.val_anio_camp%TYPE,
      numperiodo          int_ivr_corpo_cntrl.num_camp%TYPE,
      anio                int_ivr_corpo_cntrl.val_anio_proc%TYPE,
      mes                 int_ivr_corpo_cntrl.val_mes_proc%TYPE,
      dia                 int_ivr_corpo_cntrl.val_dia_proc%TYPE
    );

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo VARCHAR2(50);

  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                           pscodigosistema,
                                           pscodigointerfaz,
                                           psnombrearchivo,
                                           lsdirtempo,
                                           lsnombrearchivo,
                                           v_handle);

    /* Generando Archivo de Texto (Detalle) */
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      IF interfazrecord.COUNT > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea :='';
          IF indnuevaversion = 'S' THEN
            lslinea := interfazrecord(x).codigopais || ';';
          END IF;
          lslinea := lslinea ||
                     interfazrecord(x).codigocia || ';' ||
                     interfazrecord(x).nombrearchivo || ';' ||
                     interfazrecord(x).cantidadregistros || ';';

          IF indnuevaversion <> 'S' THEN
            lslinea := lslinea ||
                       interfazrecord(x).estadoactualizacion || ';';
          END IF;
          lslinea := lslinea ||
                     interfazrecord(x).anioperiodo || ';' ||
                     interfazrecord(x).numperiodo || ';' ||
                     interfazrecord(x).anio || ';' ||
                     interfazrecord(x).mes || ';' ||
                     interfazrecord(x).dia;

          utl_file.put_line(v_handle,
                            lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    utl_file.fclose(v_handle);

    /* Procedimiento final para generar interfaz */
    gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR', lsdirtempo, psnombrearchivo, lsnombrearchivo);

    RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123, 'ERROR INT_PR_IVR_CORPO_CNTRL: ' || ls_sqlerrm);

END INT_PR_IVR_CORPO_CNTRL;


/********************************************************************************
Descripcion       : Genera Interfaz Enviar Clientes - IVR Corporativo
                      dependiendo de psIndProceso:
                      Si psIndProceso='T' se borran los datos de la tabla
                      int_ivr_clien y se cargan todos los datos
                      Si psIndProceso='N' se cargan solo los datos modificados
  Fecha Creacion    : 21/07/2009
  Fecha Modificacion: 18/09/2014
  Autor             : CSVD - FFVV
***************************************************************************/
PROCEDURE INT_PR_IVR_CORPO_CLIEN
 (
  pscodigopais      VARCHAR2,
  pscodigosistema   VARCHAR2,
  pscodigointerfaz  VARCHAR2,
  psnombrearchivo   VARCHAR2,
  pscodigomarca     VARCHAR2,
  pscodigocanal     VARCHAR2,
  pscodigoperiodo   VARCHAR2,
  psindproceso      VARCHAR2,
  psnumerolote      VARCHAR2,
  psnombrecontrol   VARCHAR2,
  psenviarhistorico VARCHAR2,
  indnuevaversion   VARCHAR2
 ) IS

  searchstr  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20);
  replacestr VARCHAR2(100) := 'a        ';

  TYPE t_oidclie IS TABLE OF int_ivr_corpo_clien.oid_clie%TYPE;
  TYPE t_valsaldvenc IS TABLE OF int_ivr_corpo_clien.val_sald_cart%TYPE;
    TYPE t_valsaldmora IS TABLE OF int_ivr_corpo_clien.val_sald_cart%TYPE; --cambio
    --cambio
    TYPE t_rowid IS TABLE OF ROWID;
    v_rowid     t_rowid;
   --fin

  v_oidclie     t_oidclie;
  v_valsaldvenc t_valsaldvenc;
    v_valsaldmora t_valsaldmora; --cambio

    lscodigocia    int_ivr_param_compa.cod_comp%TYPE;
    ldultimaejecucion DATE;

  CURSOR c_interfaz(vnmaxcarcodclie NUMBER) IS -- Version Actual --

      SELECT '"' || cod_comp || '",' ||
             '"' || tip_clie || '",' ||
             '"' || lpad(cod_clie,vnmaxcarcodclie,'0') || '",' ||
             '"' || translate(val_nomb_clie,searchstr,replacestr) || '",' ||
             '"' || int_fn_devue_telef(translate(val_tele_fijo,searchstr,replacestr)) || '",' ||
             '"' || int_fn_devue_telef(translate(val_tele_movi,searchstr,replacestr)) || '",' ||
             '"' || cod_regi || '",' ||
             '"' || cod_zona || '",' ||
             '"' || cod_secc || '",' ||
             '"' || TRIM(to_char(val_sald_cart,'999999999990.00')) || '",' ||
             '"' || ind_marc_situ || '",' ||
             '"' || substr(cod_peri_ulti_pedi, 1, 4) || '",' ||
             '"' || substr(cod_peri_ulti_pedi, 5, 2) || '",' ||
             '"' || ind_club_priv || '",' ||
             '"' || val_tota_clie_priv || '",' ||
             '"' || ind_conc || '",' ||
             '"' || ind_cdr || '",' ||
             '"' || ind_bono || '",' ||
             '"' || ind_esta_pedi || '",' ||
             '"' || ind_dupl || '",' ||
             '"' || des_bloq || '",' ||
             '"' || cod_secc_trab || '",' ||
             '"' || to_char(fec_entr_pedi,'YYYY') || '",' ||
             '"' || to_char(fec_entr_pedi,'MM') || '",' ||
             '"' || to_char(fec_entr_pedi,'dd') || '"'
              campoconcatenado --c1
        FROM int_ivr_corpo_clien
       WHERE num_lote = DECODE(psindproceso,'T',num_lote,psnumerolote);


  CURSOR c_NuevaInterfaz(vnmaxcarcodclie NUMBER) IS -- Version Nueva --

       SELECT pscodigopais                                                      codigoPais,
              cod_comp                                                          codigoCompania,
              tip_clie                                                          tipoCliente,
              ''                                                                nivelVenta,
              val_segm_clie                                                     segCliente,
              val_esta_clie                                                     estatusCliente,
              lpad(cod_clie,vnmaxcarcodclie,'0')                                codigoCliente,
              val_num_doc_iden                                                  numeroDocumento,
              translate(val_nomb_clie,searchstr,replacestr)                     nombreCliente,
              int_fn_devue_telef(translate(val_tele_fijo,searchstr,replacestr)) telefonoFijo,
              int_fn_devue_telef(translate(val_tele_movi,searchstr,replacestr)) telefonoCelular,
              cod_regi                                                          codigoRegion,
              cod_zona                                                          codigoZona,
              cod_secc                                                          codigoSeccion,
              TRIM(to_char(val_sald_cart,'999999999990.00'))                    saldo,
              ind_sald_mora                                                     saldoMora,
              ind_marc_situ                                                     marcaSituacion,
              substr(cod_peri_ulti_pedi, 1, 4)                                  anoUltimoPedido,
              substr(cod_peri_ulti_pedi, 5, 2)                                  campanaUltimoPedido,
              case when ind_esta_pedi='1' then 'S' else 'N' end                 estadoPedido,
              case when ind_dupl='1' then 'S' else 'N' end                      indicadorDupla,
              des_bloq                                                          descripcionBloqueo,
              cod_secc_trab                                                     seccionTrabajo,
              to_char(fec_venc,'YYYY')                                     anoEntregaPedido,
              to_char(fec_venc,'MM')                                       mesEntregaPedido,
              to_char(fec_venc,'dd')                                       diaEntregaPedido,
              substr(camp_fact,1,4)                                             anoFacturacion,
              substr(camp_fact,5,2)                                             campanaFacturacion,
              to_char(fec_naci,'YYYYMM')                                        fechaNacimiento,
              ind_acti_pedi                                                     indicadorPasaPedido
         FROM int_ivr_corpo_clien
        WHERE num_lote = decode(psindproceso, 'T', num_lote, psnumerolote)
          AND cod_clie NOT IN ( SELECT cc.cod_clie
                                  FROM ccc_consu_casti_cabec cc
                                 WHERE IND_ACTI <> 9
                                 AND   (( psindproceso = 'N' AND  -- Novedad
                                        (cc.fec_modi >= TRUNC(ldultimaejecucion))
                                        ) OR psindproceso = 'T'  -- Completo
                                       ) )

        UNION
           SELECT pscodigopais                                                  codigoPais,
                  lscodigocia                                                   codigoCompania,
                  'CL'                                                          tipoCliente,
                  NULL                                                          nivelVenta,
                  NULL                                                          segCliente,
                'R'                                                           estatusCliente,
                  lpad(cod_clie,vnmaxcarcodclie,'0')                            codigoCliente,
                  num_docu_iden                                                 numeroDocumento,
                  translate( TRIM(val_nom1) || ' ' ||
                             TRIM(val_nom2) || ' ' ||
                             TRIM(val_ape1) || ' ' ||
                             TRIM(val_ape2),searchstr,replacestr)               nombreCliente,
                  NULL                                                          telefonoFijo,
                  NULL                                                          telefonoCelular,
                  cod_regi                                                      codigoRegion,
                  cod_zona                                                      codigoZona,
                  NULL                                                          codigoSeccion,
                  TRIM(to_char(IMP_DEUD_ACTU,'999999999990.00'))                saldo,
                  CASE WHEN IMP_DEUD_CAST > 0 THEN 'S' ELSE 'N' END             saldoMora,
                CASE WHEN cc.ind_acti = 9 THEN 'N' ELSE 'I' END               marcaSituacion,
                  NULL                                                          anoUltimoPedido,
                  NULL                                                          campanaUltimoPedido,
                   'N'                                                          estadoPedido,
                   'N'                                                          indicadorDupla,
                  (SELECT ip.des_ope1
                     FROM int_ivr_corpo_param_poven ip
                    WHERE ip.cod_pais = pscodigopais  AND
                          ip.cod_acce = 'BLQ' AND
                          ip.cod_oper = 'IC')                                   descripcionBloqueo,
                  NULL                                                          seccionTrabajo,
                  NULL                                                          anoEntregaPedido,
                  NULL                                                          mesEntregaPedido,
                  NULL                                                          diaEntregaPedido,
                  NULL                                                          anoFacturacion,
                  NULL                                                          campanaFacturacion,
                  NULL                                                          fechaNacimiento,
                CASE WHEN cc.ind_acti = 9 THEN 'S' ELSE 'N' END               indicadorPasaPedido
           FROM ccc_consu_casti_cabec cc
          WHERE IND_ACTI <> 9  
            AND ((
                  psindproceso = 'N' AND  -- Novedad
                 (cc.fec_modi >= TRUNC(ldultimaejecucion))
                 ) OR psindproceso = 'T'  -- Completo
                 )  ;


    TYPE interfazrec IS RECORD
    (
      campoconcatenado VARCHAR2(1000)
    );

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;


    TYPE nuevainterfazrectab IS TABLE OF c_NuevaInterfaz%ROWTYPE;
    nuevainterfazrecord nuevainterfazrectab;

  /* Variables usadas para la generacion del archivo de texto */
  lsdirtempo            bas_inter.dir_temp%TYPE;
  w_filas               NUMBER := 20000;
  v_handle              utl_file.file_type;

  lslinea               VARCHAR2(1000);
  lsnombrearchivo       VARCHAR2(50);


  lnnumregistros        NUMBER := 0;

  lnoidtipoconsultora   NUMBER;
  lnoidtipogerente      NUMBER;
  lnoidsubtipogzona     NUMBER;
  lnoidsubtipogregion   NUMBER;
  ldfechaproceso        DATE;
  lsfechaproceso        VARCHAR2(8);
  lnmaxcarcodclie       NUMBER := 15;
  lbabrirutlfile        BOOLEAN;

  lnidpais              NUMBER;
  lnidmarca             NUMBER;
  lnidcanal             NUMBER;
  lnidperiodo           NUMBER;
  lscodperiodosiguiente VARCHAR2(6);

  lnnumdias             NUMBER;
  lsinddocugz           int_ivr_param_compa.ind_docu_iden_gezo%TYPE;


  lsultimaejecucion     VARCHAR2(14);
  lsvalpain bas_param_inter.val_pain%TYPE;

BEGIN

  -- Obtiene la ultima ejecucion de la interfaz corecta --
  SELECT MAX(fec_ipro)
    INTO ldultimaejecucion
    FROM bas_histo_lotes
   WHERE inte_cod_inte = pscodigointerfaz
     AND fec_fpro IS NOT NULL
     AND ind_loer = 'N'
     AND pais_cod_pais = pscodigopais;

     lsultimaejecucion := to_char(ldultimaejecucion,'YYYYMMDDHHMISS');

  -- Obtiene el codigo de Compañia --
  SELECT p.cod_comp,
         decode(p.ind_fech_ante,'1',-1,0),
         p.ind_docu_iden_gezo
    INTO lscodigocia,
         lnnumdias,
         lsinddocugz
    FROM int_ivr_param_compa p
   WHERE p.cod_pais = pscodigopais;

  -- Obtiene OID Tipo Consultora --
  SELECT tipo.oid_tipo_clie
    INTO lnoidtipoconsultora
    FROM mae_tipo_clien tipo
   WHERE tipo.cod_tipo_clie = '02';

  -- Obtiene OID Tipo Gerente --
  SELECT tipo.oid_tipo_clie
    INTO lnoidtipogerente
    FROM mae_tipo_clien tipo
   WHERE tipo.cod_tipo_clie = '04';

  -- Obtiene OID Subtipo Gerente Region --
  SELECT subtipo.oid_subt_clie
    INTO lnoidsubtipogregion
    FROM mae_subti_clien subtipo
   WHERE subtipo.ticl_oid_tipo_clie = lnoidtipogerente
     AND subtipo.cod_subt_clie = '01';

  -- Obtiene OID Subtipo Gerente Zona --
  SELECT subtipo.oid_subt_clie
    INTO lnoidsubtipogzona
    FROM mae_subti_clien subtipo
   WHERE subtipo.ticl_oid_tipo_clie = lnoidtipogerente
     AND subtipo.cod_subt_clie = '02';

  -- Obtiene Fecha de Sistema --
  SELECT SYSDATE,
         TO_CHAR(SYSDATE, 'YYYYMMDD')
    INTO ldfechaproceso,
         lsfechaproceso
    FROM DUAL;

  -- Obteniendo id's --
  lnidpais              := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais,TRUE);
  lnidmarca             := gen_pkg_gener.gen_fn_devuelve_id_marca(pscodigomarca,TRUE);
  lnidcanal             := gen_pkg_gener.gen_fn_devuelve_id_canal(pscodigocanal,TRUE);
  lnidperiodo           := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo, lnidmarca, lnidcanal, TRUE);
  lscodperiodosiguiente := per_pkg_repor_perce.per_fn_obtie_perio(pscodigoperiodo, lnidpais, lnidmarca, lnidcanal, 1);

  --variable para cambiar el codigo de cliente por numero de documento
  SELECT nvl((select b.val_pain
                from bas_param_inter b
               where b.inte_cod_inte = pscodigointerfaz
                 and b.cod_pain = '04'
             ), 'N') INTO lsvalpain FROM dual;

  /* Generando Archivo de Texto (Detalle) */
  INT_PR_IVR_CORPO_GENER_CLIEN(psindproceso,
                               psnumerolote,
                               lscodigocia,
                               lsultimaejecucion,
                               psenviarhistorico,
                               pscodigopais,
                               lnidpais,
                               pscodigomarca,
                               lnidmarca,
                               pscodigocanal,
                               lnidcanal,
                               pscodigoperiodo,
                               lnidperiodo,
                               pscodigointerfaz,
                               lsvalpain);

  INT_IVR_ACTUA_FECHA_VENCI(pscodigopais,
                            pscodigomarca,
                            pscodigocanal,
                            lnidpais,
                            lnidmarca,
                            lnidcanal,
                            lnidperiodo,
                            pscodigoperiodo,
                            lscodperiodosiguiente,
                            psnumerolote);

    IF lsinddocugz = '1' THEN

        UPDATE int_ivr_corpo_clien i
           SET i.cod_clie = (
                             SELECT ci.num_docu_iden
                               FROM mae_clien_ident ci
                              WHERE ci.val_iden_docu_prin = '1'
                                AND ci.clie_oid_clie = i.oid_clie
                            )
         WHERE i.num_lote = DECODE(psindproceso, 'T', i.num_lote, psnumerolote)
           AND i.tip_clie = 'GZ';

    END IF;

    lnnumregistros := 0;
    lbabrirutlfile := TRUE;

    IF indnuevaversion = 'N' then
        OPEN c_interfaz(lnmaxcarcodclie);
            LOOP
              FETCH c_interfaz BULK COLLECT INTO interfazrecord LIMIT w_filas;

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

                      lslinea := interfazrecord(x).campoconcatenado;

                      utl_file.put_line(v_handle, lslinea);

                      lnnumregistros := lnnumregistros + 1;

                    END LOOP;
                END IF;

              EXIT WHEN c_interfaz%NOTFOUND;
            END LOOP;
        CLOSE c_interfaz;
    ELSE
        OPEN c_NuevaInterfaz(lnmaxcarcodclie);
            LOOP
              FETCH c_NuevaInterfaz BULK COLLECT INTO nuevainterfazrecord LIMIT w_filas;

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

                IF nuevainterfazrecord.count > 0 THEN
                  FOR x IN nuevainterfazrecord.first .. nuevainterfazrecord.last
                    LOOP

                      lslinea := nuevainterfazrecord(x).codigoPais              || ';' ||
                                 nuevainterfazrecord(x).codigoCompania          || ';' ||
                                 nuevainterfazrecord(x).tipoCliente             || ';' ||
                                 nuevainterfazrecord(x).nivelVenta              || ';' ||
                                 nuevainterfazrecord(x).segCliente              || ';' ||
                                 nuevainterfazrecord(x).estatusCliente          || ';' ||
                                 nuevainterfazrecord(x).codigoCliente           || ';' ||
                                 nuevainterfazrecord(x).numeroDocumento         || ';' ||
                                 nuevainterfazrecord(x).nombreCliente           || ';' ||
                                 nuevainterfazrecord(x).telefonoFijo            || ';' ||
                                 nuevainterfazrecord(x).telefonoCelular         || ';' ||
                                 nuevainterfazrecord(x).codigoRegion            || ';' ||
                                 nuevainterfazrecord(x).codigoZona              || ';' ||
                                 nuevainterfazrecord(x).codigoSeccion           || ';' ||
                                 nuevainterfazrecord(x).saldo                   || ';' ||
                                 nuevainterfazrecord(x).saldoMora               || ';' ||
                                 nuevainterfazrecord(x).marcaSituacion          || ';' ||
                                 nuevainterfazrecord(x).anoUltimoPedido         || ';' ||
                                 nuevainterfazrecord(x).campanaUltimoPedido     || ';' ||
                                 nuevainterfazrecord(x).estadoPedido            || ';' ||
                                 nuevainterfazrecord(x).indicadorDupla          || ';' ||
                                 nuevainterfazrecord(x).descripcionBloqueo      || ';' ||
                                 nuevainterfazrecord(x).seccionTrabajo          || ';' ||
                                 nuevainterfazrecord(x).anoEntregaPedido        || ';' ||
                                 nuevainterfazrecord(x).mesEntregaPedido        || ';' ||
                                 nuevainterfazrecord(x).diaEntregaPedido        || ';' ||
                                 nuevainterfazrecord(x).anoFacturacion          || ';' ||
                                 nuevainterfazrecord(x).campanaFacturacion      || ';' ||
                                 nuevainterfazrecord(x).fechaNacimiento         || ';' ||
                                 nuevainterfazrecord(x).indicadorPasaPedido;

                     utl_file.put_line(v_handle, lslinea);

                      lnnumregistros := lnnumregistros + 1;

                    END LOOP;
                END IF;

              EXIT WHEN c_NuevaInterfaz%NOTFOUND;
            END LOOP;
        CLOSE c_NuevaInterfaz;


    END IF;

    IF NOT lbabrirutlfile THEN
       utl_file.fclose(v_handle);
    END IF;

    INT_PR_IVR_CORPO_REGIS_CNTRL(lscodigocia,
                                 psnombrecontrol,
                                 lnnumregistros,
                                 pscodigoperiodo,
                                 lnnumdias);

    IF NOT lbabrirutlfile THEN
       /* Procedimiento final para generar interfaz */
       gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR', lsdirtempo, psnombrearchivo, lsnombrearchivo);

    END IF;

    RETURN;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 1000);
    raise_application_error(-20123, 'ERROR INT_PR_IVR_CORPO_CLIEN: ' || ls_sqlerrm);

END INT_PR_IVR_CORPO_CLIEN;

    /***************************************************************************
  Descripcion       : Genera Interfaz Emviar Tabla de Concursos de IVR dendiendo
                      de psIndProceso:
                      Si psIndProceso='T' se borran los datos de la tabla
                      int_ivr_clien y se cargan todos los datos
                      Si psIndProceso='N' se cargan solo los datos modificados
  Fecha Creacion    : 21/07/2009
  Autor             : José A. Cairampoma
  ***************************************************************************/

  PROCEDURE int_pr_ivr_corpo_concu
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    psindproceso     VARCHAR2,
    psnumerolote     VARCHAR2,
    psnombrecontrol  VARCHAR2
  ) IS
    CURSOR c_interfaz(vnmaxcarcodclie NUMBER) IS
      SELECT cod_comp,
             lpad(cod_clie,
                  vnmaxcarcodclie,
                  '0'),
             ind_vige,
             num_conc,
             tip_conc,
             val_anio_peri_inic,
             val_nume_peri_inic,
             val_anio_peri_fina,
             val_nume_peri_fina,
             val_punt_acum,
             to_char(fec_acum,
                     'YYYYMMDD'),
             ind_vali
        FROM int_ivr_corpo_concu
       WHERE num_lote = psnumerolote;

    TYPE interfazrec IS RECORD(
      codigocia         int_ivr_corpo_concu.cod_comp%TYPE,
      codigocliente     int_ivr_corpo_concu.cod_clie%TYPE,
      indvigencia       int_ivr_corpo_concu.ind_vige%TYPE,
      numconcurso       int_ivr_corpo_concu.num_conc%TYPE,
      tipoconcurso      int_ivr_corpo_concu.tip_conc%TYPE,
      anioperiodoinicio int_ivr_corpo_concu.val_anio_peri_inic%TYPE,
      numeperiodoinicio int_ivr_corpo_concu.val_nume_peri_inic%TYPE,
      anioperiodofin    int_ivr_corpo_concu.val_anio_peri_fina%TYPE,
      numperiodofin     int_ivr_corpo_concu.val_nume_peri_fina%TYPE,
      puntajeconcurso   int_ivr_corpo_concu.val_punt_acum%TYPE,
      fechaproceso      VARCHAR2(8),
      indvalidacion     int_ivr_corpo_concu.ind_vali%TYPE);

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo VARCHAR2(50);

    lnidpais          NUMBER;
    lscodigocia       int_ivr_param_compa.cod_comp%TYPE;
    lsnumerocampanias int_ivr_param_compa.num_peri_ince%TYPE;
    lnnumregistros    NUMBER := 0;

    ldfechaproceso  DATE;
    lsfechaproceso  VARCHAR2(8);
    lnmaxcarcodclie NUMBER := 15;
    lbabrirutlfile  BOOLEAN;
    lsvalpain bas_param_inter.val_pain%TYPE;

    --parametro para eliminar posiciones de codigo cliente
    lsElimPosiCodigo bas_param_pais.val_para%TYPE;
    lnTamanio NUMBER;

  BEGIN

    /* Obtiene el codigo de Compañia */
    SELECT cod_comp,
           nvl(num_peri_ince,
               1)
      INTO lscodigocia,
           lsnumerocampanias
      FROM int_ivr_param_compa
     WHERE int_ivr_param_compa.cod_pais = pscodigopais;

    /* obteniendo id's */
    lnidpais := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais); -- id del pais consultante

    --OBTENGO FECHA DE PROCESO

    SELECT SYSDATE,
           to_char(SYSDATE,
                   'YYYYMMDD')
      INTO ldfechaproceso,
           lsfechaproceso
      FROM dual;

    --variable para cambiar el codigo de cliente por numero de documento
    SELECT nvl((select b.val_pain
                  from bas_param_inter b
                 where b.inte_cod_inte = pscodigointerfaz
                   and b.cod_pain = '04'
               ), 'N') INTO lsvalpain FROM dual;


    --parametros de interface
    --variable para eliminar ultimas posiciones de codigo Consultora
    BEGIN
        --variable para cambiar el codigo de cliente por numero de documento
        SELECT b.val_pain INTO lsElimPosiCodigo
          FROM bas_param_inter b
         WHERE b.inte_cod_inte = pscodigointerfaz AND
               b.nom_pain = 'indElimPosiCod' AND
               b.est_pain = 1;
      EXCEPTION
         WHEN NO_DATA_FOUND THEN
              lsElimPosiCodigo := 0;
    END;


    IF psindproceso = 'T' THEN

      gen_pkg_gener.gen_pr_trunc_table('int_ivr_corpo_concu');

      INSERT INTO int_ivr_corpo_concu
        (cod_comp,
         cod_clie,
         ind_vige,
         num_conc,
         tip_conc,
         val_anio_peri_inic,
         val_nume_peri_inic,
         val_anio_peri_fina,
         val_nume_peri_fina,
         val_punt_acum,
         fec_acum,
         ind_vali,
         num_lote)
        SELECT lscodigocia codigocia,
               clien.cod_clie codigocliente,
               ivc.situacion indicadorvigencia,
               REPLACE(cpg.num_conc,
                       '/',
                       '') numeroconcurso,
               (SELECT val_i18n
                  FROM gen_i18n_sicc_comun g
                 WHERE g.attr_enti = 'INC_CONCU_IVR'
                   AND val_oid = cpg.tipo) tipoconcurso,
               substr(perd.cod_peri,
                      1,
                      4) anioperiodoinicio,
               substr(perd.cod_peri,
                      5,
                      2) numeperiodoinicio,
               substr(perh.cod_peri,
                      1,
                      4) anioperiodofin,
               substr(perh.cod_peri,
                      5,
                      2) numeperiodofin,
               SUM(ccp.num_punt) puntacumulado,
               ldfechaproceso fechaacumulacion,
               decode(ivc.situacion,
                      'V',
                      '',
                      decode(ivf.clie_oid_clie,
                             NULL,
                             'N',
                             'G')) flagvalidacion,
               psnumerolote

          FROM (SELECT ipgp.copa_oid_para_gral,
                       int_fn_devue_situa_concu(ipgp.perd_oid_peri) situacion
                  FROM inc_param_gener_premi ipgp) ivc,
               (SELECT cpg1.oid_para_gral,
                       cpg1.num_conc,
                       cpg1.perd_oid_peri_hast,
                       cpg1.perd_oid_peri_desd,
                       cpg1.coiv_oid_conc_ivr tipo
                  FROM inc_concu_param_gener cpg1
                 WHERE cpg1.coiv_oid_conc_ivr IS NOT NULL
                   AND cpg1.pais_oid_pais = lnidpais) cpg,
               inc_cuent_corri_punto ccp,
               mae_clien clien,
               (SELECT gan.clie_oid_clie,
                       pgp.copa_oid_para_gral
                  FROM inc_param_gener_premi pgp,
                       inc_param_nivel_premi pnp,
                       inc_ganad             gan
                 WHERE gan.panp_oid_para_nive_prem = pnp.oid_para_nive_prem
                   AND pnp.pagp_oid_para_gene_prem = pgp.oid_para_gene_prem
                 GROUP BY gan.clie_oid_clie,
                          pgp.copa_oid_para_gral) ivf,
               (SELECT c.oid_peri,
                       a.cod_peri,
                       (SELECT COUNT(*) FROM seg_perio_corpo b WHERE b.cod_peri <= a.cod_peri) AS conta
                  FROM seg_perio_corpo a,
                       cra_perio       c
                 WHERE a.oid_peri = c.peri_oid_peri
                 ORDER BY a.cod_peri) perd,
               (SELECT c.oid_peri,
                       a.cod_peri,
                       (SELECT COUNT(*) FROM seg_perio_corpo b WHERE b.cod_peri <= a.cod_peri) AS conta
                  FROM seg_perio_corpo a,
                       cra_perio       c
                 WHERE a.oid_peri = c.peri_oid_peri
                 ORDER BY a.cod_peri) perh
         WHERE ivc.copa_oid_para_gral = cpg.oid_para_gral
           AND cpg.oid_para_gral = ccp.copa_oid_para_gral
           AND ccp.copa_oid_para_gral = ivf.copa_oid_para_gral(+)
           AND ccp.clie_oid_clie = clien.oid_clie
           AND ccp.clie_oid_clie = ivf.clie_oid_clie(+)
           AND ccp.tmov_oid_tipo_movi <> 2
           AND cpg.perd_oid_peri_desd = perd.oid_peri
           AND cpg.perd_oid_peri_hast = perh.oid_peri
           AND ((ivc.situacion = 'T' AND
               ((SELECT COUNT(*) FROM seg_perio_corpo b WHERE b.cod_peri <= pscodigoperiodo) -
               perh.conta BETWEEN 1 AND lsnumerocampanias)) OR
               (ivc.situacion = 'V' AND
               perd.conta <=
               (SELECT COUNT(*) FROM seg_perio_corpo b WHERE b.cod_peri <= pscodigoperiodo)))

         GROUP BY clien.cod_clie,
                  ccp.clie_oid_clie,
                  ivf.clie_oid_clie,
                  perh.cod_peri,
                  perd.cod_peri,
                  cpg.num_conc,
                  cpg.oid_para_gral,
                  ivc.situacion,
                  cpg.tipo;
    ELSE
      DELETE FROM int_ivr_corpo_concu ivr
       WHERE EXISTS (SELECT DISTINCT coivr.num_conc,
                              coivr.cod_clie
                FROM inc_cuent_corri_punto iccp,
                     inc_concu_param_gener cpp,
                     mae_clien             clie,
                     int_ivr_corpo_concu   coivr
               WHERE iccp.copa_oid_para_gral = cpp.oid_para_gral
                 AND iccp.clie_oid_clie = clie.oid_clie
                 AND coivr.cod_clie = clie.cod_clie
                 AND REPLACE(cpp.num_conc,
                             '/',
                             '') = coivr.num_conc
                 AND iccp.fec_ulti_actu > coivr.fec_acum
                 AND ivr.cod_clie = coivr.cod_clie
                 AND ivr.num_conc = coivr.num_conc);

      INSERT INTO int_ivr_corpo_concu
        (cod_comp,
         cod_clie,
         ind_vige,
         num_conc,
         tip_conc,
         val_anio_peri_inic,
         val_nume_peri_inic,
         val_anio_peri_fina,
         val_nume_peri_fina,
         val_punt_acum,
         fec_acum,
         ind_vali,
         num_lote)
        SELECT lscodigocia codigocia,
               clien.cod_clie codigocliente,
               ivc.situacion indicadorvigencia,
               REPLACE(cpg.num_conc,
                       '/',
                       '') numeroconcurso,
               (SELECT val_i18n
                  FROM gen_i18n_sicc_comun g
                 WHERE g.attr_enti = 'INC_CONCU_IVR'
                   AND val_oid = cpg.tipo) tipoconcurso,
               substr(perd.cod_peri,
                      1,
                      4) anioperiodoinicio,
               substr(perd.cod_peri,
                      5,
                      2) numeperiodoinicio,
               substr(perh.cod_peri,
                      1,
                      4) anioperiodofin,
               substr(perh.cod_peri,
                      5,
                      2) numeperiodofin,
               SUM(ccp.num_punt) puntacumulado,
               ldfechaproceso fechaacumulacion,
               decode(ivc.situacion,
                      'V',
                      '',
                      decode(ivf.clie_oid_clie,
                             NULL,
                             'N',
                             'G')) flagvalidacion,
               psnumerolote

          FROM (SELECT ipgp.copa_oid_para_gral,
                       int_fn_devue_situa_concu(ipgp.perd_oid_peri) situacion
                  FROM inc_param_gener_premi ipgp) ivc,
               (SELECT cpg1.oid_para_gral,
                       cpg1.num_conc,
                       cpg1.perd_oid_peri_hast,
                       cpg1.perd_oid_peri_desd,
                       cpg1.coiv_oid_conc_ivr tipo
                  FROM inc_concu_param_gener cpg1
                 WHERE cpg1.coiv_oid_conc_ivr IS NOT NULL) cpg,
               inc_cuent_corri_punto ccp,
               mae_clien clien,
               (SELECT gan.clie_oid_clie,
                       pgp.copa_oid_para_gral
                  FROM inc_param_gener_premi pgp,
                       inc_param_nivel_premi pnp,
                       inc_ganad             gan
                 WHERE gan.panp_oid_para_nive_prem = pnp.oid_para_nive_prem
                   AND pnp.pagp_oid_para_gene_prem = pgp.oid_para_gene_prem
                 GROUP BY gan.clie_oid_clie,
                          pgp.copa_oid_para_gral) ivf,
               (SELECT c.oid_peri,
                       a.cod_peri,
                       (SELECT COUNT(*) FROM seg_perio_corpo b WHERE b.cod_peri <= a.cod_peri) AS conta
                  FROM seg_perio_corpo a,
                       cra_perio       c
                 WHERE a.oid_peri = c.peri_oid_peri
                 ORDER BY a.cod_peri) perd,
               (SELECT c.oid_peri,
                       a.cod_peri,
                       (SELECT COUNT(*) FROM seg_perio_corpo b WHERE b.cod_peri <= a.cod_peri) AS conta
                  FROM seg_perio_corpo a,
                       cra_perio       c
                 WHERE a.oid_peri = c.peri_oid_peri
                 ORDER BY a.cod_peri) perh
         WHERE ivc.copa_oid_para_gral = cpg.oid_para_gral
           AND cpg.oid_para_gral = ccp.copa_oid_para_gral
           AND ccp.copa_oid_para_gral = ivf.copa_oid_para_gral(+)
           AND ccp.clie_oid_clie = clien.oid_clie
           AND ccp.clie_oid_clie = ivf.clie_oid_clie(+)
           AND ccp.tmov_oid_tipo_movi <> 2
           AND cpg.perd_oid_peri_desd = perd.oid_peri
           AND cpg.perd_oid_peri_hast = perh.oid_peri
           AND ((ivc.situacion = 'T' AND
               ((SELECT COUNT(*) FROM seg_perio_corpo b WHERE b.cod_peri <= pscodigoperiodo) -
               perh.conta BETWEEN 1 AND lsnumerocampanias)) OR
               (ivc.situacion = 'V' AND
               perd.conta <=
               (SELECT COUNT(*) FROM seg_perio_corpo b WHERE b.cod_peri <= pscodigoperiodo)))
           AND NOT EXISTS (SELECT 1
                  FROM int_ivr_corpo_concu ivrc
                 WHERE ivrc.cod_clie = clien.cod_clie
                   AND ivrc.num_conc = REPLACE(cpg.num_conc,
                                               '/',
                                               ''))

         GROUP BY clien.cod_clie,
                  ccp.clie_oid_clie,
                  ivf.clie_oid_clie,
                  perh.cod_peri,
                  perd.cod_peri,
                  cpg.num_conc,
                  cpg.oid_para_gral,
                  ivc.situacion,
                  cpg.tipo;
    END IF;

    ---SI LA INTERFAZ REQUIERE EL CAMBION DE COD CLIENTE POR DOCUMENTO IDENTIDAD
    IF lsvalpain = 'S' THEN
      UPDATE int_ivr_corpo_concu c
         SET c.cod_clie = ( SELECT mci.num_docu_iden
                              FROM mae_clien mc
                                  ,mae_clien_ident mci
                             WHERE mc.oid_clie = mci.clie_oid_clie
                               AND mci.val_iden_docu_prin = 1
                               AND mc.cod_clie = c.cod_clie
                           );
    END IF;

    --Eliminando posiciones de codigo consultora
    IF lsElimPosiCodigo > 0 THEN
      SELECT td.val_long INTO lnTamanio FROM mae_tipo_docum td WHERE td.cod_tipo_docu = '01';
      lsElimPosiCodigo := lnTamanio - lsElimPosiCodigo;
      UPDATE int_ivr_corpo_concu c
         SET c.cod_clie = SUBSTR(c.cod_clie,-lnTamanio,lsElimPosiCodigo);
    END IF;

    gen_pkg_gener.gen_pr_gener_stats('int_ivr_corpo_concu',
                                     USER);

    lnnumregistros := 0;
    lbabrirutlfile := TRUE;

    /* Generando Archivo de Texto (Detalle) */
    OPEN c_interfaz(lnmaxcarcodclie);
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
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP

          lslinea := interfazrecord(x).codigocia || ';' || interfazrecord(x).codigocliente || ';' || interfazrecord(x)
                     .indvigencia || ';' || interfazrecord(x).numconcurso || ';' || interfazrecord(x)
                     .tipoconcurso || ';' || interfazrecord(x).anioperiodoinicio || ';' || interfazrecord(x)
                     .numeperiodoinicio || ';' || interfazrecord(x).anioperiodofin || ';' || interfazrecord(x)
                     .numperiodofin || ';' || interfazrecord(x).puntajeconcurso || ';' || interfazrecord(x)
                     .fechaproceso || ';' || interfazrecord(x).indvalidacion;

          utl_file.put_line(v_handle,
                            lslinea);
          lnnumregistros := lnnumregistros + 1;
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
    END IF;

    int_pr_ivr_corpo_regis_cntrl(lscodigocia,
                                 psnombrecontrol,
                                 lnnumregistros,
                                 pscodigoperiodo);

    IF NOT lbabrirutlfile THEN
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
                              'ERROR INT_PR_IVR_CORPO_CONCU: ' || ls_sqlerrm);
  END int_pr_ivr_corpo_concu;

/*******************************************************************************
Descripcion       : Genera Interfaz Enviar Motivos de Rechazo - IVR Corporativo
  Fecha Creacion    : 09/02/2008
  Autor             : José A. Cairampoma
*******************************************************************************/
PROCEDURE INT_PR_IVR_CORPO_RECHA_POVEN
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    pscodigoiso      VARCHAR2,
    psnombrecontrol  VARCHAR2,
    indnuevaversion  VARCHAR2
  ) IS

    CURSOR c_interfaz IS
      SELECT pscodigopais,
             cod_comp,
             tip_docu,
             val_moti_rech,
             des_moti_rech
        FROM int_ivr_corpo_recha_poven;

    TYPE interfazrec IS RECORD(
      codigopais        bas_pais.cod_pais%TYPE,
      codigocia     int_ivr_corpo_recha_poven.cod_comp %TYPE,
      tipodocumento int_ivr_corpo_recha_poven.tip_docu%TYPE,
      codmotrechazo int_ivr_corpo_recha_poven.val_moti_rech %TYPE,
      desmotrechazo int_ivr_corpo_recha_poven.des_moti_rech %TYPE);

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);
    lscodigocia    int_ivr_param_compa.cod_comp%TYPE;
    lbabrirutlfile BOOLEAN;
    lnnumregistros NUMBER := 0;


  BEGIN
    /* Obtiene el codigo de Compañia */
    SELECT int_ivr_param_compa.cod_comp
      INTO lscodigocia
      FROM int_ivr_param_compa
     WHERE int_ivr_param_compa.cod_pais = pscodigopais;

    --Borrando la tabla
    GEN_PKG_GENER.GEN_PR_TRUNC_TABLE('int_ivr_corpo_recha_poven');

    --Insertamos los datos
    INSERT INTO int_ivr_corpo_recha_poven
      (cod_comp, tip_docu, val_moti_rech, des_moti_rech)
      SELECT lscodigocia,
             CASE
               WHEN p.cod_tipo_docu = 'OCC' then 'OCS'
               WHEN p.cod_tipo_docu = 'SPVD' then 'SPV'
             END,
             substr(lpad(cod_mens,4,'0'), 1, 2),
             des_larg_mens
        FROM sto_mensa_valid p
       WHERE p.cod_tipo_docu IN ('OCC', 'SPVD')
      UNION
      SELECT lscodigocia,
             'DC',
             p.cod_oper_homo,
             p.des_ope1
        FROM int_ivr_corpo_param_poven p
       WHERE p.cod_acce = 'RDC';

    lnnumregistros := 0;

    GEN_PKG_GENER.GEN_PR_GENER_STATS('int_ivr_corpo_recha_poven',USER);

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

      IF interfazrecord.COUNT > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea :='';
          IF indnuevaversion = 'S' THEN
            lslinea := interfazrecord(x).codigopais || ';';
          END IF;
          lslinea := lslinea ||
                  interfazrecord(x).codigocia || ';' ||
                  interfazrecord(x).tipodocumento || ';' ||
                  interfazrecord(x).codmotrechazo || ';' ||
                  interfazrecord(x).desmotrechazo;

          lnnumregistros := lnnumregistros + 1;
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

    INT_PR_IVR_CORPO_REGIS_CNTRL(lscodigocia,
                                 psnombrecontrol,
                                 lnnumregistros,
                                 pscodigoperiodo);

    RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123, 'ERROR INT_PR_IVR_CORPO_RECHA_POVEN: ' || ls_sqlerrm);

END INT_PR_IVR_CORPO_RECHA_POVEN;


  /***************************************************************************
  Descripcion       : Genera Interfaz Emviar Tabla de Post Venta
  Fecha Creacion    : 27/02/2008
  Autor             : José A. Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_ivr_corpo_servi_poven
  (
    pscodigopais           VARCHAR2,
    pscodigosistema        VARCHAR2,
    pscodigointerfaz       VARCHAR2,
    psnombrearchivo        VARCHAR2,
    pscodigomarca          VARCHAR2,
    pscodigocanal          VARCHAR2,
    pscodigoperiodo        VARCHAR2,
    psperiodocorte         VARCHAR2,
    psporcentajepercepcion NUMBER,
    psnombrecontrol        VARCHAR2
  ) IS
    CURSOR c_interfaz(lnmaxcarcodclie NUMBER) IS
      SELECT cod_comp,
             lpad(cod_clie,
                  lnmaxcarcodclie,
                  '0'),
             lpad(val_nume_serv,
                  8,
                  '0'),
             cod_oper_serv,
             cod_prod,
             des_prod,
             cod_prod_aten,
             des_prod_aten,
             val_cant_aten,
             ind_aten,
             cod_moti_recha,
             to_char(val_mnto_serv,
                     '99999999990.00'),
             substr(cod_peri_proc,
                    1,
                    4) anio,
             substr(cod_peri_proc,
                    5,
                    2) campania
        FROM int_ivr_corpo_servi_poven;

    TYPE interfazrec IS RECORD(
      codigocia              int_ivr_corpo_servi_poven.cod_comp%TYPE,
      codigocliente          int_ivr_corpo_servi_poven.cod_clie%TYPE,
      numservicio            VARCHAR2(8),
      operacion              int_ivr_corpo_servi_poven.cod_oper_serv%TYPE,
      codigoproducto         int_ivr_corpo_servi_poven.cod_prod%TYPE,
      descproducto           int_ivr_corpo_servi_poven.des_prod%TYPE,
      codigoproductoatendido int_ivr_corpo_servi_poven.cod_prod_aten%TYPE,
      descproductoatendido   int_ivr_corpo_servi_poven.des_prod_aten%TYPE,
      cantidad               int_ivr_corpo_servi_poven.val_cant_aten%TYPE,
      indicadoratencion      int_ivr_corpo_servi_poven.ind_aten%TYPE,
      motivorechazo          int_ivr_corpo_servi_poven.cod_moti_recha%TYPE,
      valor                  VARCHAR2(20),
      anio                   int_ivr_corpo_servi_poven.cod_peri_proc%TYPE,
      campania               int_ivr_corpo_servi_poven.cod_peri_proc%TYPE);

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo         bas_inter.dir_temp%TYPE;
    w_filas            NUMBER := 1000;
    v_handle           utl_file.file_type;
    lslinea            VARCHAR2(1000);
    lsnombrearchivo    VARCHAR2(50);
    lnidmarca          NUMBER;
    lnidcanal          NUMBER;
    lnidperiodoproceso NUMBER;

    lscodigocia int_ivr_param_compa.cod_comp%TYPE;

    lnnumregistros          NUMBER := 0;
    lnmaxcarcodclie         NUMBER := 15;
    lnidpais                NUMBER;
    lscodigoperiodoanterior seg_perio_corpo.cod_peri%TYPE;
    lbabrirutlfile          BOOLEAN;
    lnidperiodoanterior     NUMBER;

    lnmaxdigitos NUMBER := 10;
    lsvalpain bas_param_inter.val_pain%TYPE;

    --parametro para eliminar posiciones de codigo cliente
    lsElimPosiCodigo bas_param_pais.val_para%TYPE;
    lnTamanio NUMBER;


  BEGIN

    /* Obtiene el codigo de Compañia */
    SELECT int_ivr_param_compa.cod_comp
      INTO lscodigocia
      FROM int_ivr_param_compa
     WHERE int_ivr_param_compa.cod_pais = pscodigopais;

    --variable para cambiar el codigo de cliente por numero de documento
    SELECT nvl((select b.val_pain
                  from bas_param_inter b
                 where b.inte_cod_inte = pscodigointerfaz
                   and b.cod_pain = '04'
               ), 'N') INTO lsvalpain FROM dual;

    --parametros de interface
    --variable para eliminar ultimas posiciones de codigo Consultora
    BEGIN
        --variable para cambiar el codigo de cliente por numero de documento
        SELECT b.val_pain INTO lsElimPosiCodigo
          FROM bas_param_inter b
         WHERE b.inte_cod_inte = pscodigointerfaz AND
               b.nom_pain = 'indElimPosiCod' AND
               b.est_pain = 1;
      EXCEPTION
         WHEN NO_DATA_FOUND THEN
              lsElimPosiCodigo := 0;
    END;


    /* obteniendo id's */
    lnidpais  := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais); -- id del pais consultante
    lnidmarca := gen_pkg_gener.gen_fn_devuelve_id_marca(pscodigomarca);
    lnidcanal := gen_pkg_gener.gen_fn_devuelve_id_canal(pscodigocanal);

    lnidperiodoproceso := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodo,
                                                                     lnidmarca,
                                                                     lnidcanal);

    lscodigoperiodoanterior := per_pkg_repor_perce.per_fn_obtie_perio(pscodigoperiodo,
                                                                      lnidpais,
                                                                      lnidmarca,
                                                                      lnidcanal,
                                                                      -1);

    lnidperiodoanterior := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(lscodigoperiodoanterior,
                                                                      lnidmarca,
                                                                      lnidcanal);

    --Borrando la tabla

    gen_pkg_gener.gen_pr_trunc_table('INT_IVR_CORPO_SERVI_POVEN');

    INSERT INTO int_ivr_corpo_servi_poven
      (cod_comp,
       cod_clie,
       val_nume_serv,
       cod_oper_serv,
       cod_prod,
       des_prod,
       cod_prod_aten,
       des_prod_aten,
       val_cant_aten,
       ind_aten,
       cod_moti_recha,
       val_mnto_serv,
       cod_peri_proc)
      SELECT lscodigocia codigocia,
             gen_pkg_gener.gen_fn_devuelve_cod_clie(a.clie_oid_clie),

             CASE
               WHEN psperiodocorte IS NOT NULL AND
                    gen_pkg_gener.gen_fn_devuelve_des_perio(a.perd_oid_peri_recl) >= psperiodocorte THEN
                substr(lpad(a.num_recl,
                            lnmaxdigitos,
                            '0'),
                       lnmaxdigitos - 8 + 1,
                       8)
               WHEN psperiodocorte IS NOT NULL AND
                    gen_pkg_gener.gen_fn_devuelve_des_perio(a.perd_oid_peri_recl) < psperiodocorte THEN
                substr(lpad(a.num_recl,
                            lnmaxdigitos,
                            '0'),
                       lnmaxdigitos - 7 + 1,
                       7)
               ELSE
                to_char(a.num_recl)
             END numservpost,
             p.cod_oper_homo codoper,
             z4.val_codi_vent codventa,
             pr.des_cort descprod,
             NULL codprodaten,
             NULL desprodaten,
             c.num_unid_recl cantidad,
             '2' indatencion,
             NULL motivorechazo,
             c.imp_abon valor,
             gen_pkg_gener.gen_fn_devuelve_des_perio(a.perd_oid_peri_recl)
        FROM rec_cabec_recla a,
             rec_opera_recla b,
             rec_linea_opera_recla c,
             rec_tipo_movim d,
             rec_tipos_opera f,
             rec_opera e,
             (SELECT z2.prod_oid_prod,
                     z2.val_codi_vent,
                     z1.oid_matr_fact,
                     z2.tofe_oid_tipo_ofer
                FROM pre_matri_factu       z1,
                     pre_ofert_detal       z2,
                     pre_matri_factu_cabec z3
               WHERE z3.oid_cabe = z1.mfca_oid_cabe
                 AND z1.ofde_oid_deta_ofer = z2.oid_deta_ofer) z4,
             mae_produ pr,
             int_ivr_corpo_param_poven p
       WHERE a.oid_cabe_recl = b.care_oid_cabe_recl
         AND b.oid_oper_recl = c.opre_oid_oper_recl
         AND c.timo_oid_tipo_movi = d.oid_tipo_movi
         AND a.perd_oid_peri_recl IN (lnidperiodoproceso,
                                      lnidperiodoanterior) --parametro --
         AND d.cod_tipo_movi = 'D'
         AND b.tiop_oid_tipo_oper = f.oid_tipo_oper
         AND f.rope_oid_oper = e.oid_oper
         AND e.cod_oper = p.cod_oper
         AND p.cod_pais = pscodigopais
         AND c.prod_oid_prod = z4.prod_oid_prod(+)
         AND z4.tofe_oid_tipo_ofer(+) = c.tofe_oid_tipo_ofer
         AND z4.oid_matr_fact(+) = c.mafa_oid_matr_fact
         AND pr.oid_prod = c.prod_oid_prod
         AND p.ind_devu = '1'
      UNION ALL
      SELECT lscodigocia codigocia,
             gen_pkg_gener.gen_fn_devuelve_cod_clie(a.clie_oid_clie),

             CASE
               WHEN psperiodocorte IS NOT NULL AND
                    gen_pkg_gener.gen_fn_devuelve_des_perio(a.perd_oid_peri_recl) >= psperiodocorte THEN
                substr(lpad(a.num_recl,
                            lnmaxdigitos,
                            '0'),
                       lnmaxdigitos - 8 + 1,
                       8)
               WHEN psperiodocorte IS NOT NULL AND
                    gen_pkg_gener.gen_fn_devuelve_des_perio(a.perd_oid_peri_recl) < psperiodocorte THEN
                substr(lpad(a.num_recl,
                            lnmaxdigitos,
                            '0'),
                       lnmaxdigitos - 7 + 1,
                       7)
               ELSE
                to_char(a.num_recl)
             END numservpost,
             p.cod_oper_homo codoper,
             NULL codventa,
             NULL descprod,
             z4.val_codi_vent codprodaten,
             pr.des_cort desprodaten,
             c.num_unid_recl cantidad,
             '1' indatencion,
             NULL motivorechazo,
             c.imp_carg valor,
             gen_pkg_gener.gen_fn_devuelve_des_perio(a.perd_oid_peri_recl)
        FROM rec_cabec_recla a,
             rec_opera_recla b,
             rec_linea_opera_recla c,
             rec_tipo_movim d,
             rec_tipos_opera f,
             rec_opera e,
             (SELECT z2.prod_oid_prod,
                     z2.val_codi_vent,
                     z1.oid_matr_fact,
                     z2.tofe_oid_tipo_ofer
                FROM pre_matri_factu       z1,
                     pre_ofert_detal       z2,
                     pre_matri_factu_cabec z3
               WHERE z3.oid_cabe = z1.mfca_oid_cabe
                 AND z1.ofde_oid_deta_ofer = z2.oid_deta_ofer) z4,
             mae_produ pr,
             int_ivr_corpo_param_poven p
       WHERE a.oid_cabe_recl = b.care_oid_cabe_recl
         AND b.oid_oper_recl = c.opre_oid_oper_recl
         AND c.timo_oid_tipo_movi = d.oid_tipo_movi
         AND a.perd_oid_peri_recl IN (lnidperiodoproceso,
                                      lnidperiodoanterior) --parametro --
         AND d.cod_tipo_movi = 'E'
         AND b.tiop_oid_tipo_oper = f.oid_tipo_oper
         AND f.rope_oid_oper = e.oid_oper
         AND e.cod_oper = p.cod_oper
         AND p.cod_pais = pscodigopais
         AND c.prod_oid_prod = z4.prod_oid_prod(+)
         AND z4.tofe_oid_tipo_ofer(+) = c.tofe_oid_tipo_ofer
         AND z4.oid_matr_fact(+) = c.mafa_oid_matr_fact
         AND pr.oid_prod = c.prod_oid_prod
         AND p.ind_envi = '1'
      UNION ALL
      SELECT lscodigocia,
             c.cod_clie,
             c.num_docu,
             p.cod_oper_homo, /*CAM;BIO homologacion CAMPAÑA AQCTUAL Y ANTERIOR*/
             d.cod_vent_devu,
             int_pkg_recla.gen_fn_desc_prod(d.prod_oid_prod_devu),
             d.cod_vent_dese,
             int_pkg_recla.gen_fn_desc_prod(d.prod_oid_prod_envi),
             decode(d.can_prod_dese,
                    NULL,
                    d.can_vent_devu,
                    0,
                    d.can_vent_devu,
                    d.can_prod_dese) cantidad_envia,
             'R',
             substr(mv.cod_mens,
                    length(mv.cod_mens) - 1,
                    2),
             d.can_vent_devu,
             decode(c.oid_peri_recl,
                    NULL,
                    NULL,
                    gen_pkg_gener.gen_fn_devuelve_des_perio(c.oid_peri_recl)) periodo_reclamo
        FROM int_solic_conso_poven_detal d,
             int_solic_conso_poven_cabec c,
             sto_detal_docum_excep       de,
             sto_docum_digit             dg,
             sto_param_valid             sp,
             mae_clien                   m,
             sto_mensa_valid             mv,
             rec_opera                   rop,
             int_ivr_corpo_param_poven   p
       WHERE de.cod_tipo_docu IN ('SPVD')
         AND d.num_docu = c.num_docu
         AND d.num_lote = c.num_lote
         AND d.cod_clie = c.cod_clie
         AND d.cod_pais = c.cod_pais
         AND rop.cod_oper = d.cod_oper
         AND d.cod_peri = c.cod_peri
         AND m.cod_clie = c.cod_clie
         AND d.sec_nume_docu = dg.sec_nume_docu
         AND dg.sec_nume_docu = de.sec_nume_docu
         AND dg.ind_rech = 1
         AND de.ind_apro = 0
         AND de.ind_leva_erro = 0
         AND sp.cod_vali = de.cod_vali
         AND de.cod_tipo_docu = d.docu_cod_tipo_docu
         AND de.cod_tipo_docu = dg.cod_tipo_docu
         AND mv.cod_vali = sp.cod_vali
         AND rop.cod_oper = p.cod_oper
         AND p.cod_pais = pscodigopais
         AND c.cod_peri IN (pscodigoperiodo,
                            lscodigoperiodoanterior);

    ---SI LA INTERFAZ REQUIERE EL CAMBION DE COD CLIENTE POR DOCUMENTO IDENTIDAD
    IF lsvalpain = 'S' THEN
      UPDATE int_ivr_corpo_servi_poven c
         SET c.cod_clie = ( SELECT mci.num_docu_iden
                              FROM mae_clien mc
                                  ,mae_clien_ident mci
                             WHERE mc.oid_clie = mci.clie_oid_clie
                               AND mci.val_iden_docu_prin = 1
                               AND mc.cod_clie = c.cod_clie
                           );
    END IF;

    --Eliminando posiciones de codigo consultora
    IF lsElimPosiCodigo > 0 THEN
      SELECT td.val_long INTO lnTamanio FROM mae_tipo_docum td WHERE td.cod_tipo_docu = '01';
      lsElimPosiCodigo := lnTamanio - lsElimPosiCodigo;
      UPDATE int_ivr_corpo_servi_poven c
         SET c.cod_clie = SUBSTR(c.cod_clie,-lnTamanio,lsElimPosiCodigo);
    END IF;


    lnnumregistros := 0;

    gen_pkg_gener.gen_pr_gener_stats('INT_IVR_CORPO_SERVI_POVEN',
                                     USER);

    /* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;
    OPEN c_interfaz(lnmaxcarcodclie);
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
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP

          lslinea        := interfazrecord(x)
                            .codigocia || ';' || interfazrecord(x).codigocliente || ';' || interfazrecord(x)
                            .numservicio || ';' || interfazrecord(x).operacion || ';' || interfazrecord(x)
                            .codigoproducto || ';' || interfazrecord(x).descproducto || ';' || interfazrecord(x)
                            .codigoproductoatendido || ';' || interfazrecord(x).descproductoatendido || ';' || interfazrecord(x)
                            .cantidad || ';' || interfazrecord(x).indicadoratencion || ';' || interfazrecord(x)
                            .motivorechazo || ';' || interfazrecord(x).valor || ';' || interfazrecord(x).anio || ';' || interfazrecord(x)
                            .campania;
          lnnumregistros := lnnumregistros + 1;
          utl_file.put_line(v_handle,
                            lslinea);
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

    int_pr_ivr_corpo_regis_cntrl(lscodigocia,
                                 psnombrecontrol,
                                 lnnumregistros,
                                 pscodigoperiodo);

    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_IVR_CORPO_SERVI_POVEN: ' || ls_sqlerrm);
  END int_pr_ivr_corpo_servi_poven;

  /***************************************************************************
  Descripcion       : Genera Interfaz Emviar Tabla de llamadas de salida de IVR dendiendo
                      de psIndProceso:
                      Si psIndProceso='T' se borran los datos de la tabla
                      int_ivr_corpo_llama_salid y se cargan todos los datos
                      Si psIndProceso='N' se cargan solo los datos modificados
  Fecha Creacion    : 20/07/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE int_pr_ivr_corpo_llama_salid
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    psindproceso     VARCHAR2,
    psnumerolote     VARCHAR2,
    psnombrecontrol  VARCHAR2
  ) IS
    CURSOR c_interfaz(lnmaxcarcodclie NUMBER) IS

      SELECT cod_comp,
             lpad(cod_clie,
                  lnmaxcarcodclie,
                  '0'),
             tel_clie,
             tip_llam,
             men_gene,
             tex_envi,
             tip_deri
        FROM int_ivr_corpo_llama_salid
       WHERE num_lote = psnumerolote;

    TYPE interfazrec IS RECORD(
      codigocia       int_ivr_corpo_llama_salid.cod_comp%TYPE,
      codigocliente   int_ivr_corpo_llama_salid.cod_clie%TYPE,
      telefonocliente int_ivr_corpo_llama_salid.tel_clie%TYPE,
      tipollamada     int_ivr_corpo_llama_salid.tip_llam%TYPE,
      mensajegenerar  int_ivr_corpo_llama_salid.men_gene%TYPE,
      textoenviar     int_ivr_corpo_llama_salid.tex_envi%TYPE,
      tipoderivacion  int_ivr_corpo_llama_salid.tip_deri%TYPE);
    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo VARCHAR2(50);

    lscodigocia     int_ivr_param_compa.cod_comp%TYPE;
    lnnumregistros  NUMBER := 0;
    lnmaxcarcodclie NUMBER := 15;
    lbabrirutlfile  BOOLEAN;
    lsvalpain bas_param_inter.val_pain%TYPE;
    --parametro para eliminar posiciones de codigo cliente
    lsElimPosiCodigo bas_param_pais.val_para%TYPE;
    lnTamanio NUMBER;


  BEGIN

    /* Obtiene el codigo de Compañia */
    SELECT int_ivr_param_compa.cod_comp
      INTO lscodigocia
      FROM int_ivr_param_compa
     WHERE int_ivr_param_compa.cod_pais = pscodigopais;

    --variable para cambiar el codigo de cliente por numero de documento
    SELECT nvl((select b.val_pain
                  from bas_param_inter b
                 where b.inte_cod_inte = pscodigointerfaz
                   and b.cod_pain = '04'
               ), 'N') INTO lsvalpain FROM dual;

    --parametros de interface
    --variable para eliminar ultimas posiciones de codigo Consultora
    BEGIN
        --variable para cambiar el codigo de cliente por numero de documento
        SELECT b.val_pain INTO lsElimPosiCodigo
          FROM bas_param_inter b
         WHERE b.inte_cod_inte = pscodigointerfaz AND
               b.nom_pain = 'indElimPosiCod' AND
               b.est_pain = 1;
      EXCEPTION
         WHEN NO_DATA_FOUND THEN
              lsElimPosiCodigo := 0;
    END;


    gen_pkg_gener.gen_pr_trunc_table('int_ivr_corpo_llama_salid');
    INSERT INTO int_ivr_corpo_llama_salid
      (cod_comp,
       cod_clie,
       tel_clie,
       tip_llam,
       men_gene,
       tex_envi,
       tip_deri,
       num_lote)
      SELECT lscodigocia  codigocia,
             ivr.cod_clie codigocliente,
             ivr.tel_clie telefonocliente,
             ivr.tip_llam tipollamada,
             ivr.men_gene mensajegenerar,
             ivr.tex_envi textoenviar,
             ivr.tip_deri tipoderivacion,
             psnumerolote
        FROM int_ivr_llama_salid ivr
       GROUP BY ivr.cod_comp,
                ivr.cod_clie,
                ivr.tel_clie,
                ivr.tip_llam,
                ivr.men_gene,
                ivr.tex_envi,
                ivr.tip_deri;

    ---SI LA INTERFAZ REQUIERE EL CAMBION DE COD CLIENTE POR DOCUMENTO IDENTIDAD
    IF lsvalpain = 'S' THEN
      UPDATE int_ivr_corpo_llama_salid c
         SET c.cod_clie = ( SELECT mci.num_docu_iden
                              FROM mae_clien mc
                                  ,mae_clien_ident mci
                             WHERE mc.oid_clie = mci.clie_oid_clie
                               AND mci.val_iden_docu_prin = 1
                               AND mc.cod_clie = c.cod_clie
                           );
    END IF;

    --Eliminando posiciones de codigo consultora
    IF lsElimPosiCodigo > 0 THEN
      SELECT td.val_long INTO lnTamanio FROM mae_tipo_docum td WHERE td.cod_tipo_docu = '01';
      lsElimPosiCodigo := lnTamanio - lsElimPosiCodigo;
      UPDATE int_ivr_corpo_llama_salid c
         SET c.cod_clie = SUBSTR(c.cod_clie,-lnTamanio,lsElimPosiCodigo);
    END IF;


    lnnumregistros := 0;
    lbabrirutlfile := TRUE;

    /* Generando Archivo de Texto (Detalle) */
    OPEN c_interfaz(lnmaxcarcodclie);
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
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP

          lslinea := interfazrecord(x).codigocia || ';' || interfazrecord(x).codigocliente || ';' || interfazrecord(x)
                     .telefonocliente || ';' || interfazrecord(x).tipollamada || ';' || interfazrecord(x)
                     .mensajegenerar || ';' || interfazrecord(x).textoenviar || ';' || interfazrecord(x)
                     .tipoderivacion;

          utl_file.put_line(v_handle,
                            lslinea);
          lnnumregistros := lnnumregistros + 1;
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
    END IF;

    int_pr_ivr_corpo_regis_cntrl(lscodigocia,
                                 psnombrecontrol,
                                 lnnumregistros,
                                 pscodigoperiodo);

    IF NOT lbabrirutlfile THEN
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
                              'ERROR INT_PR_IVR_CORPO_LLAMA_SALID: ' || ls_sqlerrm);
  END int_pr_ivr_corpo_llama_salid;

   /***************************************************************************
  Descripcion       : Genera Interfaz Emviar Tabla de resultados de campaña de IVR dendiendo
                      de psIndProceso:
                      Si psIndProceso='T' se borran los datos de la tabla
                      int_ivr_corpo_resul_campa y se cargan todos los datos
                      Si psIndProceso='N' se cargan solo los datos modificados
  Fecha Creacion    : 21/07/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE int_pr_ivr_corpo_resul_campa
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    psindproceso     VARCHAR2,
    psnumerolote     VARCHAR2,
    psnombrecontrol  VARCHAR2,
    pscodigomarca    VARCHAR2,
    pscodigocanal    VARCHAR2
  ) IS
    CURSOR c_interfaz(lnmaxcarcodclie NUMBER) IS
      SELECT cod_comp,
             lpad(cod_clie,
                  lnmaxcarcodclie,
                  '0'),
             ano_camp,
             cod_camp,
             num_cons_acti,
             num_pedi_fact_zona,
             to_char(nvl(por_acti_zona * 10,
                         0),
                     '0000'),
             num_pedi_norm,
             num_pedi_rein,
             num_pedi_nuev,
             num_pedi_rech_deud,
             num_pedi_rech_mont
        FROM int_ivr_corpo_resul_campa
       WHERE num_lote = psnumerolote;

    TYPE interfazrec IS RECORD(
      codigocia              int_ivr_corpo_resul_campa.cod_comp%TYPE,
      codigocliente          int_ivr_corpo_resul_campa.cod_clie%TYPE,
      anocampana             int_ivr_corpo_resul_campa.ano_camp%TYPE,
      numerocampana          int_ivr_corpo_resul_campa.cod_camp%TYPE,
      consultorasactivas     int_ivr_corpo_resul_campa.num_cons_acti%TYPE,
      numeropedidos          int_ivr_corpo_resul_campa.num_pedi_fact_zona%TYPE,
      porcentajeactividad    VARCHAR2(10),
      pedidosnormales        int_ivr_corpo_resul_campa.num_pedi_norm%TYPE,
      pedidosreingresos      int_ivr_corpo_resul_campa.num_pedi_rein%TYPE,
      pedidosnuevos          int_ivr_corpo_resul_campa.num_pedi_nuev%TYPE,
      pedidosrechazadosdeuda int_ivr_corpo_resul_campa.num_pedi_rech_deud%TYPE,
      pedidosrechazadosmonto int_ivr_corpo_resul_campa.num_pedi_rech_mont%TYPE);
    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;

    CURSOR c_acti_fina(vscodigoperiodo VARCHAR2) IS
      SELECT seg_pais.cod_pais,
             seg_perio_corpo.cod_peri,
             zon_regio.cod_regi,
             zon_zona.cod_zona,
             SUM(int_fuent_ventas_real.num_acti_fina) AS num_acti_fina
        FROM seg_pais,
             seg_perio_corpo,
             zon_zona,
             zon_regio,
             cra_perio,
             int_fuent_ventas_real,
             int_fuent_venta_previ_sap
       WHERE (seg_pais.oid_pais = zon_zona.pais_oid_pais)
         AND (zon_regio.oid_regi = zon_zona.zorg_oid_regi)
         AND (seg_pais.oid_pais = cra_perio.pais_oid_pais)
         AND (seg_perio_corpo.oid_peri = cra_perio.peri_oid_peri)
         AND (zon_zona.oid_zona = int_fuent_ventas_real.zzon_oid_zona)
         AND (zon_regio.oid_regi = int_fuent_ventas_real.zorg_oid_regi)
         AND (cra_perio.oid_peri = int_fuent_ventas_real.perd_oid_peri)
         AND (zon_zona.oid_zona = int_fuent_venta_previ_sap.zzon_oid_zona)
         AND (zon_regio.oid_regi = int_fuent_venta_previ_sap.zorg_oid_regi)
         AND (cra_perio.oid_peri = int_fuent_venta_previ_sap.perd_oid_peri)
         AND (seg_pais.cod_pais = pscodigopais)
         AND (seg_perio_corpo.cod_peri = vscodigoperiodo)
       GROUP BY seg_pais.cod_pais,
                seg_perio_corpo.cod_peri,
                zon_regio.cod_regi,
                zon_zona.cod_zona,
                zon_regio.oid_regi,
                zon_zona.oid_zona;

    TYPE actifinarec IS RECORD(
      codigopais    seg_pais.cod_pais%TYPE,
      codigoperiodo seg_perio_corpo.cod_peri%TYPE,
      codigoregion  zon_regio.cod_regi%TYPE,
      codigozona    zon_zona.cod_zona%TYPE,
      numeroactivas int_fuent_ventas_real.num_acti_fina%TYPE);

    TYPE actifinarectab IS TABLE OF actifinarec;
    actifinarecord actifinarectab;

    CURSOR c_esti(vscodigoperiodo VARCHAR2) IS
      SELECT seg_pais.cod_pais,
             seg_perio_corpo.cod_peri,
             zon_regio.cod_regi,
             zon_zona.cod_zona,
             int_fuent_venta_previ_sap.num_acti_fina AS num_esti_acti_fina
        FROM seg_pais,
             seg_perio_corpo,
             zon_zona,
             zon_regio,
             cra_perio,
             int_fuent_venta_previ_sap
       WHERE (seg_pais.oid_pais = zon_zona.pais_oid_pais)
         AND (zon_regio.oid_regi = zon_zona.zorg_oid_regi)
         AND (seg_pais.oid_pais = cra_perio.pais_oid_pais)
         AND (seg_perio_corpo.oid_peri = cra_perio.peri_oid_peri)
         AND (zon_zona.oid_zona = int_fuent_venta_previ_sap.zzon_oid_zona)
         AND (zon_regio.oid_regi = int_fuent_venta_previ_sap.zorg_oid_regi)
         AND (cra_perio.oid_peri = int_fuent_venta_previ_sap.perd_oid_peri)
         AND (seg_pais.cod_pais = pscodigopais)
         AND (seg_perio_corpo.cod_peri = vscodigoperiodo);

    TYPE estirec IS RECORD(
      codigopais    seg_pais.cod_pais%TYPE,
      codigoperiodo seg_perio_corpo.cod_peri%TYPE,
      codigoregion  zon_regio.cod_regi%TYPE,
      codigozona    zon_zona.cod_zona%TYPE,
      numeroactivas int_fuent_ventas_real.num_acti_fina%TYPE);

    TYPE estirectab IS TABLE OF estirec;
    estirecord estirectab;

    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo VARCHAR2(50);

    lscodigocia    int_ivr_param_compa.cod_comp%TYPE;
    lnnumregistros NUMBER := 0;

    lnmaxcarcodclie         NUMBER := 15;
    lbabrirutlfile          BOOLEAN;
    lscodigoperiodoanterior seg_perio_corpo.cod_peri%TYPE;

    lnidmarca NUMBER;
    lnidcanal NUMBER;

    lnidpais    NUMBER;
    lsinddocugz int_ivr_param_compa.ind_docu_iden_gezo%TYPE;
    lsvalpain bas_param_inter.val_pain%TYPE;

    --parametro para eliminar posiciones de codigo cliente
    lsElimPosiCodigo bas_param_pais.val_para%TYPE;
    lnTamanio NUMBER;


  BEGIN

    /* Obtiene el codigo de Compañia */
    SELECT cod_comp,
           ind_docu_iden_gezo
      INTO lscodigocia,
           lsinddocugz
      FROM int_ivr_param_compa
     WHERE int_ivr_param_compa.cod_pais = pscodigopais;

    --variable para cambiar el codigo de cliente por numero de documento
    SELECT nvl((select b.val_pain
                  from bas_param_inter b
                 where b.inte_cod_inte = pscodigointerfaz
                   and b.cod_pain = '04'
               ), 'N') INTO lsvalpain FROM dual;


    --variable para eliminar ultimas posiciones de codigo Consultora
    BEGIN
        --variable para cambiar el codigo de cliente por numero de documento
        SELECT b.val_pain INTO lsElimPosiCodigo
          FROM bas_param_inter b
         WHERE b.inte_cod_inte = pscodigointerfaz AND
               b.nom_pain = 'indElimPosiCod' AND
               b.est_pain = 1;
      EXCEPTION
         WHEN NO_DATA_FOUND THEN
              lsElimPosiCodigo := 0;
    END;


    /* obteniendo id's */
    lnidpais  := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais); -- id del pais consultante
    lnidmarca := gen_pkg_gener.gen_fn_devuelve_id_marca(pscodigomarca);
    lnidcanal := gen_pkg_gener.gen_fn_devuelve_id_canal(pscodigocanal);

    lscodigoperiodoanterior := per_pkg_repor_perce.per_fn_obtie_perio(pscodigoperiodo,
                                                                      lnidpais,
                                                                      lnidmarca,
                                                                      lnidcanal,
                                                                      -1);

    gen_pkg_gener.gen_pr_trunc_table('int_ivr_corpo_resul_campa');
    INSERT INTO int_ivr_corpo_resul_campa
      (cod_pais,
       cod_comp,
       cod_clie,
       ano_camp,
       cod_camp,
       cod_regi,
       cod_zona,
       cod_peri,
       num_pedi_fact_zona,
       num_pedi_nuev,
       num_pedi_norm,
       num_pedi_rein,
       num_pedi_rech_deud,
       num_pedi_rech_mont,
       num_lote)
      SELECT pscodigopais,
             lscodigocia codigocia,
             mci.cod_clie,
             substr(pscodigoperiodo,
                    0,
                    4),
             substr(pscodigoperiodo,
                    5,
                    2),
             zon_regio.cod_regi,
             z.cod_zona,
             pscodigoperiodo,
             COUNT(DISTINCT psc.clie_oid_clie) AS nropedidos,
             SUM(CASE
                   WHEN ((mae_clien_datos_adici.esta_oid_esta_clie = 1) OR
                        (mae_clien_datos_adici.esta_oid_esta_clie = 2 AND (fc2.total > 0)) OR
                        (mae_clien_datos_adici.esta_oid_esta_clie = 8 AND (fc2.total > 0)) OR
                        (mae_clien_datos_adici.esta_oid_esta_clie = 7 AND
                        (mae_clien_datos_adici.ind_acti = 1))) THEN
                    1
                   ELSE
                    0
                 END) AS num_prim_pedi,
             SUM(CASE
                   WHEN ((mae_clien_datos_adici.esta_oid_esta_clie = 3 AND (fc2.total > 0) OR
                        (mae_clien_datos_adici.esta_oid_esta_clie = 2 AND (fc2.total < 0)))) THEN
                    1
                   ELSE
                    0
                 END) AS num_pedi_normales,
             SUM(CASE
                   WHEN ((mae_clien_datos_adici.esta_oid_esta_clie = 6 AND (fc2.total > 0) OR
                        (mae_clien_datos_adici.esta_oid_esta_clie = 5 AND (fc2.total < 0)))) THEN
                    1
                   ELSE
                    0
                 END) AS num_pedi_reingreso,
             (SELECT COUNT(*)
                FROM int_solic_conso_cabec b
               WHERE b.cod_peri = pscodigoperiodo
                 AND b.zzon_oid_zona = z.oid_zona
                 AND (b.ind_erro_deud = '2')
                 AND (b.ind_error_sgpe = '0')
                 AND (b.ind_erro_remp = '0')
                 AND (b.ind_admi_cart = '0')
                 AND (b.ind_ocs_proc = '0')
                 AND (b.ind_cont_act = '0')) rechazados_deuda,
             (SELECT COUNT(*)
                FROM int_solic_conso_cabec b
               WHERE b.cod_peri = pscodigoperiodo
                 AND b.zzon_oid_zona = z.oid_zona
                 AND (b.ind_erro_mtmi = '1')
                 AND (b.ind_error_sgpe = '0')
                 AND (b.ind_erro_remp = '0')
                 AND (b.ind_admi_cart = '0')
                 AND (b.ind_ocs_proc = '0')) rechazados_monto,
             psnumerolote

        FROM cra_perio,
             ped_solic_cabec psc,
             ped_tipo_solic_pais,
             seg_perio_corpo,
             seg_pais,
             ped_tipo_solic,
             zon_zona z,
             zon_regio,
             mae_clien mci,
             mae_clien_datos_adici,
             (SELECT cc.oid_peri,
                     rr.oid_regi,
                     INT_PKG_ASIST_VIRTU.avi_fn_valid_regio_cerra(cc.oid_peri,
                                                                  rr.oid_regi) total
                FROM cra_perio       cc,
                     zon_regio       rr,
                     seg_perio_corpo ss
               WHERE cc.peri_oid_peri = ss.oid_peri
                 AND (ss.cod_peri = pscodigoperiodo)) fc2
       WHERE (cra_perio.oid_peri = psc.perd_oid_peri)
         AND (ped_tipo_solic_pais.oid_tipo_soli_pais = psc.tspa_oid_tipo_soli_pais)
         AND (mci.oid_clie = z.clie_oid_clie)
            --       AND (mci.val_iden_docu_prin = '1')
         AND (mae_clien_datos_adici.clie_oid_clie = psc.clie_oid_clie)
         AND (seg_perio_corpo.oid_peri = cra_perio.peri_oid_peri)
         AND (seg_pais.oid_pais = psc.pais_oid_pais)
         AND (ped_tipo_solic.oid_tipo_soli = ped_tipo_solic_pais.tsol_oid_tipo_soli)
         AND (ped_tipo_solic.cod_tipo_soli IN (SELECT cod_tipo_soli FROM int_evi_tipo_solic_pais))
         AND ((psc.esso_oid_esta_soli = 1) OR (psc.esso_oid_esta_soli = 5))
         AND (psc.zzon_oid_zona = z.oid_zona)
         AND (z.zorg_oid_regi = zon_regio.oid_regi)
         AND (psc.grpr_oid_grup_proc = 5)
         AND (psc.ind_ts_no_conso = 1)
         AND (psc.fec_fact IS NOT NULL)
         AND (fc2.oid_regi = zon_regio.oid_regi)
         AND (fc2.oid_peri = cra_perio.oid_peri)
         AND (seg_pais.cod_pais = pscodigopais)
         AND (seg_perio_corpo.cod_peri = pscodigoperiodo)
       GROUP BY seg_pais.cod_pais,
                zon_regio.cod_regi,
                z.cod_zona,
                seg_perio_corpo.cod_peri,
                zon_regio.oid_regi,
                z.oid_zona,
                mci.cod_clie;

    INSERT INTO int_ivr_corpo_resul_campa
      (cod_pais,
       cod_comp,
       cod_clie,
       ano_camp,
       cod_camp,
       cod_regi,
       cod_zona,
       cod_peri,
       num_pedi_fact_zona,
       num_pedi_nuev,
       num_pedi_norm,
       num_pedi_rein,
       num_pedi_rech_deud,
       num_pedi_rech_mont,
       num_lote)
      SELECT pscodigopais,
             lscodigocia codigocia,
             mci.cod_clie,
             substr(lscodigoperiodoanterior,
                    0,
                    4),
             substr(lscodigoperiodoanterior,
                    5,
                    2),
             zon_regio.cod_regi,
             z.cod_zona,
             lscodigoperiodoanterior,
             COUNT(DISTINCT psc.clie_oid_clie) AS nropedidos,
             SUM(CASE
                   WHEN ((mae_clien_datos_adici.esta_oid_esta_clie = 1) OR
                        (mae_clien_datos_adici.esta_oid_esta_clie = 2 AND (fc2.total > 0)) OR
                        (mae_clien_datos_adici.esta_oid_esta_clie = 8 AND (fc2.total > 0)) OR
                        (mae_clien_datos_adici.esta_oid_esta_clie = 7 AND
                        (mae_clien_datos_adici.ind_acti = 1))) THEN
                    1
                   ELSE
                    0
                 END) AS num_prim_pedi,
             SUM(CASE
                   WHEN ((mae_clien_datos_adici.esta_oid_esta_clie = 3 AND (fc2.total > 0) OR
                        (mae_clien_datos_adici.esta_oid_esta_clie = 2 AND (fc2.total < 0)))) THEN
                    1
                   ELSE
                    0
                 END) AS num_pedi_normales,
             SUM(CASE
                   WHEN ((mae_clien_datos_adici.esta_oid_esta_clie = 6 AND (fc2.total > 0) OR
                        (mae_clien_datos_adici.esta_oid_esta_clie = 5 AND (fc2.total < 0)))) THEN
                    1
                   ELSE
                    0
                 END) AS num_pedi_reingreso,
             (SELECT COUNT(*)
                FROM ped_histo_solic_conso_cabec b
               WHERE b.cod_peri = lscodigoperiodoanterior
                 AND b.zzon_oid_zona = z.oid_zona
                 AND (b.ind_erro_deud = '2')
                 AND (b.ind_error_sgpe = '0')
                 AND (b.ind_erro_remp = '0')
                 AND (b.ind_admi_cart = '0')
                 AND (b.ind_ocs_proc = '0')
                 AND (b.ind_cont_act = '0')) rechazados_deuda,
             (SELECT COUNT(*)
                FROM ped_histo_solic_conso_cabec b
               WHERE b.cod_peri = lscodigoperiodoanterior
                 AND b.zzon_oid_zona = z.oid_zona
                 AND (b.ind_erro_mtmi = '1')
                 AND (b.ind_error_sgpe = '0')
                 AND (b.ind_erro_remp = '0')
                 AND (b.ind_admi_cart = '0')
                 AND (b.ind_ocs_proc = '0')) rechazados_monto,
             psnumerolote

        FROM cra_perio,
             ped_solic_cabec psc,
             ped_tipo_solic_pais,
             seg_perio_corpo,
             seg_pais,
             ped_tipo_solic,
             zon_zona z,
             zon_regio,
             mae_clien mci,
             mae_clien_datos_adici,
             (SELECT cc.oid_peri,
                     rr.oid_regi,
                     avi_pkg_asist_virtu.avi_fn_valid_regio_cerra(cc.oid_peri,
                                                                  rr.oid_regi) total
                FROM cra_perio       cc,
                     zon_regio       rr,
                     seg_perio_corpo ss
               WHERE cc.peri_oid_peri = ss.oid_peri
                 AND (ss.cod_peri = lscodigoperiodoanterior)) fc2
       WHERE (cra_perio.oid_peri = psc.perd_oid_peri)
         AND (ped_tipo_solic_pais.oid_tipo_soli_pais = psc.tspa_oid_tipo_soli_pais)
         AND (mci.oid_clie = z.clie_oid_clie)
            --       AND (mci.val_iden_docu_prin = '1')
         AND (mae_clien_datos_adici.clie_oid_clie = psc.clie_oid_clie)
         AND (seg_perio_corpo.oid_peri = cra_perio.peri_oid_peri)
         AND (seg_pais.oid_pais = psc.pais_oid_pais)
         AND (ped_tipo_solic.oid_tipo_soli = ped_tipo_solic_pais.tsol_oid_tipo_soli)
         AND (ped_tipo_solic.cod_tipo_soli IN (SELECT cod_tipo_soli FROM int_evi_tipo_solic_pais))
         AND ((psc.esso_oid_esta_soli = 1) OR (psc.esso_oid_esta_soli = 5))
         AND (psc.zzon_oid_zona = z.oid_zona)
         AND (z.zorg_oid_regi = zon_regio.oid_regi)
         AND (psc.grpr_oid_grup_proc = 5)
         AND (psc.ind_ts_no_conso = 1)
         AND (psc.fec_fact IS NOT NULL)
         AND (fc2.oid_regi = zon_regio.oid_regi)
         AND (fc2.oid_peri = cra_perio.oid_peri)
         AND (seg_pais.cod_pais = pscodigopais)
         AND (seg_perio_corpo.cod_peri = lscodigoperiodoanterior)
         AND NOT EXISTS
       (SELECT 1 FROM int_ivr_corpo_resul_campa ivr WHERE ivr.cod_clie = mci.cod_clie)
       GROUP BY seg_pais.cod_pais,
                zon_regio.cod_regi,
                z.cod_zona,
                seg_perio_corpo.cod_peri,
                zon_regio.oid_regi,
                z.oid_zona,
                mci.cod_clie;

    OPEN c_acti_fina(pscodigoperiodo);
    LOOP
      FETCH c_acti_fina BULK COLLECT
        INTO actifinarecord LIMIT w_filas;
      IF actifinarecord.count > 0 THEN
        FOR x IN actifinarecord.first .. actifinarecord.last
        LOOP
          UPDATE int_ivr_corpo_resul_campa
             SET num_cons_acti = actifinarecord(x).numeroactivas
           WHERE cod_pais = actifinarecord(x).codigopais
             AND cod_regi = actifinarecord(x).codigoregion
             AND cod_zona = actifinarecord(x).codigozona
             AND cod_peri = actifinarecord(x).codigoperiodo;

        END LOOP;
      END IF;
      EXIT WHEN c_acti_fina%NOTFOUND;
    END LOOP;
    CLOSE c_acti_fina;

    OPEN c_esti(pscodigoperiodo);
    LOOP
      FETCH c_esti BULK COLLECT
        INTO estirecord LIMIT w_filas;
      IF estirecord.count > 0 THEN
        FOR x IN estirecord.first .. estirecord.last
        LOOP
          UPDATE int_ivr_corpo_resul_campa
             SET num_acti_esti_fina = estirecord(x).numeroactivas
           WHERE cod_pais = estirecord(x).codigopais
             AND cod_regi = estirecord(x).codigoregion
             AND cod_zona = estirecord(x).codigozona
             AND cod_peri = estirecord(x).codigoperiodo;

        END LOOP;
      END IF;
      EXIT WHEN c_esti%NOTFOUND;
    END LOOP;
    CLOSE c_esti;

    /*PERIODO ANTERIOR*/

    OPEN c_acti_fina(lscodigoperiodoanterior);
    LOOP
      FETCH c_acti_fina BULK COLLECT
        INTO actifinarecord LIMIT w_filas;
      IF actifinarecord.count > 0 THEN
        FOR x IN actifinarecord.first .. actifinarecord.last
        LOOP
          UPDATE int_ivr_corpo_resul_campa
             SET num_cons_acti = actifinarecord(x).numeroactivas
           WHERE cod_pais = actifinarecord(x).codigopais
             AND cod_regi = actifinarecord(x).codigoregion
             AND cod_zona = actifinarecord(x).codigozona
             AND cod_peri = actifinarecord(x).codigoperiodo;

        END LOOP;
      END IF;
      EXIT WHEN c_acti_fina%NOTFOUND;
    END LOOP;
    CLOSE c_acti_fina;

    OPEN c_esti(lscodigoperiodoanterior);
    LOOP
      FETCH c_esti BULK COLLECT
        INTO estirecord LIMIT w_filas;
      IF estirecord.count > 0 THEN
        FOR x IN estirecord.first .. estirecord.last
        LOOP
          UPDATE int_ivr_corpo_resul_campa
             SET num_acti_esti_fina = estirecord(x).numeroactivas
           WHERE cod_pais = estirecord(x).codigopais
             AND cod_regi = estirecord(x).codigoregion
             AND cod_zona = estirecord(x).codigozona
             AND cod_peri = estirecord(x).codigoperiodo;

        END LOOP;
      END IF;
      EXIT WHEN c_esti%NOTFOUND;
    END LOOP;
    CLOSE c_esti;

    UPDATE int_ivr_corpo_resul_campa
       SET por_acti_zona = decode(num_cons_acti,
                                  0,
                                  0,
                                  num_pedi_fact_zona / num_cons_acti * 100);

    IF lsinddocugz = '1' THEN

      UPDATE int_ivr_corpo_resul_campa i
         SET i.cod_clie =
             (SELECT ci.num_docu_iden
                FROM mae_clien_ident ci
               WHERE ci.val_iden_docu_prin = '1'
                 AND ci.clie_oid_clie =
                     (SELECT oid_clie FROM mae_clien c WHERE c.cod_clie = i.cod_clie));
    END IF;

    ---SI LA INTERFAZ REQUIERE EL CAMBION DE COD CLIENTE POR DOCUMENTO IDENTIDAD
    IF lsvalpain = 'S' THEN
      UPDATE int_ivr_corpo_resul_campa c
         SET c.cod_clie = ( SELECT mci.num_docu_iden
                              FROM mae_clien mc
                                  ,mae_clien_ident mci
                             WHERE mc.oid_clie = mci.clie_oid_clie
                               AND mci.val_iden_docu_prin = 1
                               AND mc.cod_clie = c.cod_clie
                           );
    END IF;

    --Eliminando posiciones de codigo consultora
    IF lsElimPosiCodigo > 0 THEN
      SELECT td.val_long INTO lnTamanio FROM mae_tipo_docum td WHERE td.cod_tipo_docu = '01';
      lsElimPosiCodigo := lnTamanio - lsElimPosiCodigo;
      UPDATE int_ivr_corpo_resul_campa c
         SET c.cod_clie = SUBSTR(c.cod_clie,-lnTamanio,lsElimPosiCodigo);
    END IF;


    lnnumregistros := 0;
    lbabrirutlfile := TRUE;

    /* Generando Archivo de Texto (Detalle) */
    OPEN c_interfaz(lnmaxcarcodclie);
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

          lslinea := interfazrecord(x)
                     .codigocia || ';' || interfazrecord(x).codigocliente || ';' || interfazrecord(x)
                     .anocampana || ';' || interfazrecord(x).numerocampana || ';' || interfazrecord(x)
                     .consultorasactivas || ';' || interfazrecord(x).numeropedidos || ';' || interfazrecord(x)
                     .porcentajeactividad || ';' || interfazrecord(x).pedidosnormales || ';' || interfazrecord(x)
                     .pedidosreingresos || ';' || interfazrecord(x).pedidosnuevos || ';' || interfazrecord(x)
                     .pedidosrechazadosdeuda || ';' || interfazrecord(x).pedidosrechazadosmonto;

          utl_file.put_line(v_handle,
                            lslinea);
          lnnumregistros := lnnumregistros + 1;
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
    END IF;

    int_pr_ivr_corpo_regis_cntrl(lscodigocia,
                                 psnombrecontrol,
                                 lnnumregistros,
                                 pscodigoperiodo);

    IF NOT lbabrirutlfile THEN
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
                              'ERROR INT_IVR_CORPO_RESUL_CAMPA: ' || ls_sqlerrm);
  END int_pr_ivr_corpo_resul_campa;
  /***************************************************************************
  Descripcion       : Genera Interfaz Emviar Tabla de programa de dupla cyzone de IVR dendiendo
                      de psIndProceso:
                      Si psIndProceso='T' se borran los datos de la tabla
                      int_ivr_corpo_resul_campa y se cargan todos los datos
                      Si psIndProceso='N' se cargan solo los datos modificados
  Fecha Creacion    : 22/07/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE int_pr_ivr_corpo_progr_ducyz
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    psindproceso     VARCHAR2,
    psnumerolote     VARCHAR2,
    psnombrecontrol  VARCHAR2
  ) IS
    CURSOR c_interfaz(lnmaxcarcodclie NUMBER) IS

      SELECT cod_comp,
             lpad(cod_clie,
                  lnmaxcarcodclie,
                  '0'),
             lpad(cod_dupl,
                  lnmaxcarcodclie,
                  '0'),
             des_prog,
             ano_camp_inic,
             cod_camp_inic,
             ano_camp_fin,
             cod_camp_fin,
             pun_base,
             pun_acum,
             to_char(fec_punt_acum,
                     'YYYY'),
             to_char(fec_punt_acum,
                     'MM'),
             fec_envi_nove,
             ind_vali
        FROM int_ivr_corpo_progr_ducyz
       WHERE num_lote = psnumerolote;

    TYPE interfazrec IS RECORD(
      codigocia           int_ivr_corpo_progr_ducyz.cod_comp%TYPE,
      codigocliente       int_ivr_corpo_progr_ducyz.cod_clie%TYPE,
      codigodupla         int_ivr_corpo_progr_ducyz.cod_dupl %TYPE,
      nombreprograma      int_ivr_corpo_progr_ducyz.des_prog %TYPE,
      anocampanainicio    int_ivr_corpo_progr_ducyz.ano_camp_inic %TYPE,
      numerocampanainicio int_ivr_corpo_progr_ducyz.cod_camp_inic%TYPE,
      anocampanafin       int_ivr_corpo_progr_ducyz.ano_camp_fin %TYPE,
      numerocampanafin    int_ivr_corpo_progr_ducyz.cod_camp_fin%TYPE,
      puntosbase          int_ivr_corpo_progr_ducyz.pun_base%TYPE,
      puntosacumulados    int_ivr_corpo_progr_ducyz.pun_acum%TYPE,
      anopuntosacumulados VARCHAR2(4),
      mespuntosacumulados VARCHAR2(2),
      fechaenvionovedad   int_ivr_corpo_progr_ducyz.fec_envi_nove%TYPE,
      flagvalidacion      int_ivr_corpo_progr_ducyz.ind_vali%TYPE);
    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo VARCHAR2(50);

    lnidpais          NUMBER;
    lscodigocia       int_ivr_param_compa.cod_comp%TYPE;
    lsnumerocampanias int_ivr_param_compa.num_peri_ince%TYPE;
    lnnumregistros    NUMBER := 0;

    ldfechaproceso  DATE;
    lsfechaproceso  VARCHAR2(8);
    lnmaxcarcodclie NUMBER := 15;
    lbabrirutlfile  BOOLEAN;
    lsvalpain bas_param_inter.val_pain%TYPE;
    --parametro para eliminar posiciones de codigo cliente
    lsElimPosiCodigo bas_param_pais.val_para%TYPE;
    lnTamanio NUMBER;

  BEGIN

    /* Obtiene el codigo de Compañia */
    SELECT cod_comp,
           nvl(num_peri_ince,
               1)
      INTO lscodigocia,
           lsnumerocampanias
      FROM int_ivr_param_compa
     WHERE int_ivr_param_compa.cod_pais = pscodigopais;

    --variable para cambiar el codigo de cliente por numero de documento
    SELECT nvl((select b.val_pain
                  from bas_param_inter b
                 where b.inte_cod_inte = pscodigointerfaz
                   and b.cod_pain = '04'
               ), 'N') INTO lsvalpain FROM dual;

    --variable para eliminar ultimas posiciones de codigo Consultora
    BEGIN
        --variable para cambiar el codigo de cliente por numero de documento
        SELECT b.val_pain INTO lsElimPosiCodigo
          FROM bas_param_inter b
         WHERE b.inte_cod_inte = pscodigointerfaz AND
               b.nom_pain = 'indElimPosiCod' AND
               b.est_pain = 1;
      EXCEPTION
         WHEN NO_DATA_FOUND THEN
              lsElimPosiCodigo := 0;
    END;

    /* obteniendo id's */
    lnidpais := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais); -- id del pais consultante

    --OBTENGO FECHA DE PROCESO

    SELECT SYSDATE,
           to_char(SYSDATE,
                   'YYYYMMDD')
      INTO ldfechaproceso,
           lsfechaproceso
      FROM dual;

    gen_pkg_gener.gen_pr_trunc_table('int_ivr_corpo_progr_ducyz');

    INSERT INTO int_ivr_corpo_progr_ducyz
      (cod_comp,
       cod_clie,
       cod_dupl,
       des_prog,
       ano_camp_inic,
       cod_camp_inic,
       ano_camp_fin,
       cod_camp_fin,
       pun_base,
       pun_acum,
       fec_punt_acum,
       fec_envi_nove,
       ind_vali,
       num_lote)
      SELECT lscodigocia codigocia,
             clien.cod_clie codigocliente,
             NULL codigodupla,
             cpg.val_nomb nombreprograma,
             substr(perd.cod_peri,
                    0,
                    4) anocampanainicio,
             substr(perd.cod_peri,
                    5,
                    2) numerocampanainicio,
             substr(perh.cod_peri,
                    0,
                    4) anocampanafin,
             substr(perh.cod_peri,
                    5,
                    2) numerocampanafin,
             SUM(venta.val_meta) puntosbase,
             SUM(ccp.num_punt) puntosacumulados,
             MAX(ccp.fec_movi) fechapuntaje,
             lsfechaproceso fechaenvionovedad,
             decode(ivc.situacion,
                    'V',
                    '',
                    decode(ivf.clie_oid_clie,
                           NULL,
                           'N',
                           'G')) flagvalidacion,
             psnumerolote

        FROM (SELECT ipgp.copa_oid_para_gral,
                     int_fn_devue_situa_concu(ipgp.perd_oid_peri) situacion
                FROM inc_param_gener_premi ipgp) ivc,
             (SELECT cpg1.oid_para_gral,
                     cpg1.num_conc,
                     cpg1.perd_oid_peri_hast,
                     cpg1.perd_oid_peri_desd,
                     cpg1.val_nomb,
                     'V' tipo
                FROM inc_concu_param_gener cpg1
               WHERE cpg1.ind_dupl_cyzo = 1
                 AND cpg1.pais_oid_pais = lnidpais) cpg,
             inc_cuent_corri_punto ccp,
             inc_metas_tipo_venta venta,
             mae_clien clien,
             (SELECT gan.clie_oid_clie,
                     pgp.copa_oid_para_gral
                FROM inc_param_gener_premi pgp,
                     inc_param_nivel_premi pnp,
                     inc_ganad             gan
               WHERE gan.panp_oid_para_nive_prem = pnp.oid_para_nive_prem
                 AND pnp.pagp_oid_para_gene_prem = pgp.oid_para_gene_prem
               GROUP BY gan.clie_oid_clie,
                        pgp.copa_oid_para_gral) ivf,
             (SELECT c.oid_peri,
                     a.cod_peri,
                     (SELECT COUNT(*) FROM seg_perio_corpo b WHERE b.cod_peri <= a.cod_peri) AS conta
                FROM seg_perio_corpo a,
                     cra_perio       c
               WHERE a.oid_peri = c.peri_oid_peri
               ORDER BY a.cod_peri) perd,
             (SELECT c.oid_peri,
                     a.cod_peri,
                     (SELECT COUNT(*) FROM seg_perio_corpo b WHERE b.cod_peri <= a.cod_peri) AS conta
                FROM seg_perio_corpo a,
                     cra_perio       c
               WHERE a.oid_peri = c.peri_oid_peri
               ORDER BY a.cod_peri) perh
       WHERE ivc.copa_oid_para_gral = cpg.oid_para_gral
         AND cpg.oid_para_gral = ccp.copa_oid_para_gral
         AND ccp.copa_oid_para_gral = ivf.copa_oid_para_gral(+)
         AND ccp.clie_oid_clie = clien.oid_clie
         AND ccp.clie_oid_clie = ivf.clie_oid_clie(+)
         AND NOT (ccp.tmov_oid_tipo_movi = 2 AND ccp.val_desc = 'Entrega de Premio')
         AND ccp.tmov_oid_tipo_movi <> 3
         AND venta.copa_oid_para_gral = cpg.oid_para_gral
         AND venta.clie_oid_clie = ccp.clie_oid_clie
         AND cpg.perd_oid_peri_desd = perd.oid_peri
         AND cpg.perd_oid_peri_hast = perh.oid_peri
         AND ((ivc.situacion = 'T' AND /*CAMBIO CAMPAÑAS IGUAL QUE CONCURSOS*/
             ((SELECT COUNT(*) FROM seg_perio_corpo b WHERE b.cod_peri <= pscodigoperiodo) -
             perh.conta BETWEEN 1 AND lsnumerocampanias)) OR
             (ivc.situacion = 'V' AND
             perd.conta <=
             (SELECT COUNT(*) FROM seg_perio_corpo b WHERE b.cod_peri <= pscodigoperiodo)))
         AND EXISTS
       (SELECT 1 FROM int_ivr_corpo_maest_ducyz m WHERE clien.cod_clie = m.cod_clie)

       GROUP BY clien.cod_clie,
                ccp.clie_oid_clie,
                ivf.clie_oid_clie,
                perh.cod_peri,
                perd.cod_peri,
                cpg.num_conc,
                cpg.oid_para_gral,
                ivc.situacion,
                cpg.tipo,
                cpg.val_nomb;

    UPDATE int_ivr_corpo_progr_ducyz d
       SET d.cod_dupl =
                 (SELECT c.cod_clie
                              FROM mae_clien_vincu v,
                                   mae_clien       c
                             WHERE v.clie_oid_clie_vnte =
                   (SELECT clien.oid_clie FROM mae_clien clien WHERE clien.cod_clie = d.cod_clie)
                               AND c.oid_clie = v.clie_oid_clie_vndo
                               AND v.fec_hast IS NULL
               AND rownum = 1);

    ---SI LA INTERFAZ REQUIERE EL CAMBION DE COD CLIENTE POR DOCUMENTO IDENTIDAD
    IF lsvalpain = 'S' THEN
      UPDATE int_ivr_corpo_progr_ducyz c
         SET c.cod_clie = ( SELECT mci.num_docu_iden
                              FROM mae_clien mc
                                  ,mae_clien_ident mci
                             WHERE mc.oid_clie = mci.clie_oid_clie
                               AND mci.val_iden_docu_prin = 1
                               AND mc.cod_clie = c.cod_clie
                           );
    END IF;

    --Eliminando posiciones de codigo consultora
    IF lsElimPosiCodigo > 0 THEN
      SELECT td.val_long INTO lnTamanio FROM mae_tipo_docum td WHERE td.cod_tipo_docu = '01';
      lsElimPosiCodigo := lnTamanio - lsElimPosiCodigo;
      UPDATE int_ivr_corpo_progr_ducyz c
         SET c.cod_clie = SUBSTR(c.cod_clie,-lnTamanio,lsElimPosiCodigo);
    END IF;

    gen_pkg_gener.gen_pr_gener_stats('int_ivr_corpo_progr_ducyz',
                                     USER);

    lnnumregistros := 0;

    lbabrirutlfile := TRUE;

    /* Generando Archivo de Texto (Detalle) */
    OPEN c_interfaz(lnmaxcarcodclie);
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
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP

          lslinea := interfazrecord(x)
                     .codigocia || ';' || interfazrecord(x).codigocliente || ';' || interfazrecord(x)
                     .codigodupla || ';' || interfazrecord(x).nombreprograma || ';' || interfazrecord(x)
                     .anocampanainicio || ';' || interfazrecord(x).numerocampanainicio || ';' || interfazrecord(x)
                     .anocampanafin || ';' || interfazrecord(x).numerocampanafin || ';' || interfazrecord(x)
                     .puntosbase || ';' || interfazrecord(x).puntosacumulados || ';' || interfazrecord(x)
                     .anopuntosacumulados || ';' || interfazrecord(x).mespuntosacumulados || ';' || interfazrecord(x)
                     .fechaenvionovedad || ';' || interfazrecord(x).flagvalidacion;

          utl_file.put_line(v_handle,
                            lslinea);
          lnnumregistros := lnnumregistros + 1;
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
    END IF;

    int_pr_ivr_corpo_regis_cntrl(lscodigocia,
                                 psnombrecontrol,
                                 lnnumregistros,
                                 pscodigoperiodo);

    IF NOT lbabrirutlfile THEN
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
                              'ERROR INT_PR_IVR_CORPO_PROGR_DUCYZ: ' || ls_sqlerrm);
  END int_pr_ivr_corpo_progr_ducyz;

  /***************************************************************************
  Descripcion       : Genera Interfaz Emviar Tabla de estado de pedidos de IVR dendiendo
                      de psIndProceso:
                      Si psIndProceso='T' se borran los datos de la tabla
                      int_ivr_clien y se cargan todos los datos
                      Si psIndProceso='N' se cargan solo los datos modificados
  Fecha Creacion    : 23/07/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE int_pr_ivr_corpo_estad_pedid
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigomarca    VARCHAR2,
    pscodigocanal    VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    psindproceso     VARCHAR2,
    psnumerolote     VARCHAR2,
    psnombrecontrol  VARCHAR2
  ) IS
    CURSOR c_interfaz(lnmaxcarcodclie NUMBER) IS

      SELECT cod_comp,
             lpad(cod_clie,
                  lnmaxcarcodclie,
                  '0'),
             cod_situ,
             ano_camp,
             cod_camp,
             to_char(fec_situ,
                     'YYYY'),
             to_char(fec_situ,
                     'MM'),
             to_char(fec_situ,
                     'DD'),
             TRIM(to_char(val_mont,
                          '999999999990.99')),
             cod_moti_rech,
             fec_entr_pedi
        FROM int_ivr_corpo_estad_pedid
       WHERE num_lote = psnumerolote;

    TYPE interfazrec IS RECORD(
      codigocia     int_ivr_corpo_estad_pedid.cod_comp%TYPE,
      codigocliente int_ivr_corpo_estad_pedid.cod_clie%TYPE,
      situacion     int_ivr_corpo_estad_pedid.cod_situ %TYPE,
      anocampana    int_ivr_corpo_estad_pedid.ano_camp%TYPE,
      numerocampana int_ivr_corpo_estad_pedid.cod_camp%TYPE,
      anosituacion  VARCHAR2(4),
      messituacion  VARCHAR2(2),
      diasituacion  VARCHAR2(2),
      valor         VARCHAR2(20),
      motivorechazo int_ivr_corpo_estad_pedid.cod_moti_rech%TYPE,
      fechaentrega  int_ivr_corpo_estad_pedid.fec_entr_pedi%TYPE);


    TYPE interfazrectab IS TABLE OF interfazrec;

    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */

    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo VARCHAR2(50);

    lscodigocia    int_ivr_param_compa.cod_comp%TYPE;
    lnnumregistros NUMBER := 0;

    lnmaxcarcodclie NUMBER := 15;
    lbabrirutlfile  BOOLEAN;

    lsperiodoanterior seg_perio_corpo.cod_peri%TYPE;

    lnidmarca   NUMBER;
    lnidcanal   NUMBER;
    lnidpais    NUMBER;
    lnidperiodo NUMBER;
    lsvalpain bas_param_inter.val_pain%TYPE;
    lsvalDiasEntregaPedido bas_param_inter.val_pain%TYPE;
    --parametro para eliminar posiciones de codigo cliente
    lsElimPosiCodigo bas_param_pais.val_para%TYPE;
    lnTamanio NUMBER;


  BEGIN

    /* Obtiene el codigo de Compañia */

    SELECT int_ivr_param_compa.cod_comp
      INTO lscodigocia
      FROM int_ivr_param_compa
     WHERE int_ivr_param_compa.cod_pais = pscodigopais;

    --variable para cambiar el codigo de cliente por numero de documento
    SELECT nvl((select b.val_pain
                  from bas_param_inter b
                 where b.inte_cod_inte = pscodigointerfaz
                   and b.cod_pain = '04'
               ), 'N') INTO lsvalpain FROM dual;

   --variable para calcular la fecha de entrega de pedido
   --variable para cambiar el codigo de cliente por numero de documento
    SELECT nvl((select b.val_pain
                  from bas_param_inter b
                 where b.inte_cod_inte = pscodigointerfaz
                   and b.nom_pain = 'cantidadDiasEntregaPedido'
               ), '0') INTO lsvalDiasEntregaPedido FROM dual;

    --parametros de interface
    --variable para eliminar ultimas posiciones de codigo Consultora
    BEGIN
        --variable para cambiar el codigo de cliente por numero de documento
        SELECT b.val_pain INTO lsElimPosiCodigo
          FROM bas_param_inter b
         WHERE b.inte_cod_inte = pscodigointerfaz AND
               b.nom_pain = 'indElimPosiCod' AND
               b.est_pain = 1;
      EXCEPTION
         WHEN NO_DATA_FOUND THEN
              lsElimPosiCodigo := 0;
    END;


    --Determino los Ids de Pais, Marca y Canal y periodo
    lnidpais    := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);
    lnidmarca   := gen_pkg_gener.gen_fn_devuelve_id_marca(pscodigomarca);
    lnidcanal   := gen_pkg_gener.gen_fn_devuelve_id_canal(pscodigocanal);
    lnidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoperiodo);

    --Periodo Anterior
    lsperiodoanterior := per_pkg_repor_perce.per_fn_obtie_perio(pscodigoperiodo,
                                                                lnidpais,
                                                                lnidmarca,
                                                                lnidcanal,
                                                                -1);

    -- Se obtiene el oid de actividad
    /* SELECT ca.oid_acti
     INTO v_oid_acti
     FROM CRA_ACTIV ca
    WHERE ca.cod_acti = (SELECT p.cod_oper
                           FROM int_ivr_corpo_param_poven p
                          WHERE p.cod_acce = 'REP');*/


    gen_pkg_gener.gen_pr_trunc_table('int_ivr_corpo_estad_pedid');
    INSERT INTO int_ivr_corpo_estad_pedid
      (cod_comp,
       cod_clie,
       cod_situ,
       ano_camp,
       cod_camp,
       fec_situ,
       val_mont,
       cod_moti_rech,
       num_lote,
       fec_entr_pedi)
      SELECT lscodigocia codigocia,
             mc.cod_clie cliente,
             'P',
             substr(spc.cod_peri,
                    0,
                    4),
             substr(spc.cod_peri,
                    5,
                    2),
             psc2.fec_fact fecha,
             psc2.val_tota_paga_loca,
             NULL,
             psnumerolote,
             CASE
               WHEN lsvalDiasEntregaPedido <> 0
                    THEN psc1.fec_fact +   lsvalDiasEntregaPedido
               ELSE
                   (
                    SELECT x.fec_inic
                FROM cra_crono x
               WHERE x.zzon_oid_zona = psc1.zzon_oid_zona
                 AND x.cact_oid_acti = ca.oid_acti
                       AND x.perd_oid_peri = lnidperiodo
                   )
               END
        FROM cra_perio           cp,
             ped_solic_cabec     psc1,
             ped_solic_cabec     psc2,
             ped_tipo_solic_pais ptsp,
             seg_perio_corpo     spc,
             seg_pais            sp,
             seg_marca           sm,
             seg_canal           sc,
             ped_tipo_solic      pts,
             mae_clien           mc,
             cra_activ           ca
       WHERE psc1.soca_oid_soli_cabe = psc2.oid_soli_cabe
         AND (cp.oid_peri = psc1.perd_oid_peri)
         AND (ptsp.oid_tipo_soli_pais = psc1.tspa_oid_tipo_soli_pais)
         AND (mc.oid_clie = psc1.clie_oid_clie)
         AND (spc.oid_peri = cp.peri_oid_peri)
         AND (sp.oid_pais = psc1.pais_oid_pais)
         AND (sm.oid_marc = cp.marc_oid_marc)
         AND (sc.oid_cana = cp.cana_oid_cana)
         AND (pts.oid_tipo_soli = ptsp.tsol_oid_tipo_soli)
         AND (pts.cod_tipo_soli IN (SELECT cod_tipo_soli FROM int_evi_tipo_solic_pais))
         AND psc1.esso_oid_esta_soli IN (1,
                                         5)
         AND (psc1.grpr_oid_grup_proc = 5)
         AND (psc1.ind_ts_no_conso = 1)
         AND (psc1.ind_oc = 1)
         AND (psc1.fec_fact IS NOT NULL)
         AND (sp.cod_pais = pscodigopais)
         AND (sm.cod_marc = pscodigomarca)
         AND (sc.cod_cana = pscodigocanal)
         AND (spc.cod_peri = pscodigoperiodo)
         AND ca.cod_acti =
             (SELECT p.cod_oper FROM int_ivr_corpo_param_poven p WHERE p.cod_acce = 'REP')
         AND ca.pais_oid_pais = lnidpais;


    INSERT INTO int_ivr_corpo_estad_pedid
      (cod_comp,
       cod_clie,
       cod_situ,
       ano_camp,
       cod_camp,
       fec_situ,
       val_mont,
       cod_moti_rech,
       num_lote)
      SELECT lscodigocia codigocia,
             cod_clie,
             'R',
             substr(cod_peri,
                    0,
                    4),
             substr(cod_peri,
                    5,
                    2),
             trunc(fec_digi),
             0,
             (SELECT substr(mv.cod_mens,1,2)
                FROM sto_detal_docum_excep e,
                     sto_mensa_valid       mv
               WHERE e.cod_pais = do.cod_pais
                 AND e.cod_tipo_docu = do.cod_tipo_docu
                 AND e.num_lote = do.num_lote
                 AND e.sec_nume_docu = do.sec_nume_docu
                 AND e.fec_digi = do.fec_digi
                 AND e.cod_mens = mv.cod_mens
                 AND e.cod_tipo_docu = mv.cod_tipo_docu
                 AND rownum = 1),
             psnumerolote

        FROM (SELECT dd.cod_pais,
                     dd.cod_tipo_docu,
                     dd.num_lote,
                     dd.sec_nume_docu,
                     dd.cod_peri,
                     dd.cod_clie,
                     MAX(de.fec_digi) fec_digi,
                     zz.oid_zona
                FROM sto_detal_docum_excep de,
                     sto_docum_digit       dd,
                     zon_zona              zz
               WHERE de.cod_pais = pscodigopais
                 AND dd.cod_pais = de.cod_pais
                 AND de.cod_tipo_docu = 'OCC'
                 AND dd.cod_tipo_docu = de.cod_tipo_docu
                 AND dd.sec_nume_docu = de.sec_nume_docu
                 AND dd.num_lote = de.num_lote
                 AND dd.cod_clie IS NOT NULL
                 AND dd.ind_envi = '0'
                 AND de.ind_apro = '0'
                 AND dd.cod_peri = pscodigoperiodo

                 AND zz.cod_zona = dd.cod_zona

                 AND NOT EXISTS (SELECT 1
                        FROM sto_docum_digit d
                       WHERE d.cod_pais = dd.cod_pais
                         AND d.cod_tipo_docu = dd.cod_tipo_docu
                         AND d.num_lote = dd.num_lote
                         AND d.sec_nume_docu = dd.sec_nume_docu
                         AND d.ind_envi = '1')
               GROUP BY dd.cod_pais,
                        dd.cod_tipo_docu,
                        dd.num_lote,
                        dd.sec_nume_docu,
                        dd.cod_peri,
                        dd.cod_clie,
                        zz.oid_zona) do
                  WHERE NOT regexp_like(do.cod_clie,'^(c)','i');


    INSERT INTO int_ivr_corpo_estad_pedid
      (cod_comp,
       cod_clie,
       cod_situ,
       ano_camp,
       cod_camp,
       fec_situ,
       val_mont,
       cod_moti_rech,
       num_lote,
       fec_entr_pedi)
      SELECT lscodigocia codigocia,
             mc.cod_clie cliente,
             'P',
             substr(spc.cod_peri,
                    0,
                    4),
             substr(spc.cod_peri,
                    5,
                    2),
             psc2.fec_fact fecha,
             psc2.val_tota_paga_loca,
             NULL,
             psnumerolote,
           CASE WHEN lsvalDiasEntregaPedido <> 0
                     THEN psc1.fec_fact + lsvalDiasEntregaPedido
                ELSE
             (SELECT x.fec_inic
                FROM cra_crono x
               WHERE x.zzon_oid_zona = psc1.zzon_oid_zona
                 AND x.cact_oid_acti = ca.oid_acti
                 AND x.perd_oid_peri = lnidperiodo)
                END
        FROM cra_perio           cp,
             ped_solic_cabec     psc1,
             ped_solic_cabec     psc2,
             ped_tipo_solic_pais ptsp,
             seg_perio_corpo     spc,
             seg_pais            sp,
             seg_marca           sm,
             seg_canal           sc,
             ped_tipo_solic      pts,
             mae_clien           mc,
             cra_activ           ca
       WHERE psc1.soca_oid_soli_cabe = psc2.oid_soli_cabe
         AND (cp.oid_peri = psc1.perd_oid_peri)
         AND (ptsp.oid_tipo_soli_pais = psc1.tspa_oid_tipo_soli_pais)
         AND (mc.oid_clie = psc1.clie_oid_clie)
         AND (spc.oid_peri = cp.peri_oid_peri)
         AND (sp.oid_pais = psc1.pais_oid_pais)
         AND (sm.oid_marc = cp.marc_oid_marc)
         AND (sc.oid_cana = cp.cana_oid_cana)
         AND (pts.oid_tipo_soli = ptsp.tsol_oid_tipo_soli)
         AND (pts.cod_tipo_soli IN (SELECT cod_tipo_soli FROM int_evi_tipo_solic_pais))
         AND psc1.esso_oid_esta_soli IN (1,
                                         5)
         AND (psc1.grpr_oid_grup_proc = 5)
         AND (psc1.ind_ts_no_conso = 1)
         AND (psc1.ind_oc = 1)
         AND (psc1.fec_fact IS NOT NULL)
         AND (sp.cod_pais = pscodigopais)
         AND (sm.cod_marc = pscodigomarca)
         AND (sc.cod_cana = pscodigocanal)
         AND (spc.cod_peri = lsperiodoanterior)
         AND ca.cod_acti =
             (SELECT p.cod_oper FROM int_ivr_corpo_param_poven p WHERE p.cod_acce = 'REP')
         AND ca.pais_oid_pais = lnidpais
         AND NOT EXISTS (SELECT 1
                FROM int_ivr_corpo_estad_pedid ivr
               WHERE ivr.cod_clie = mc.cod_clie
                 AND ivr.num_lote = psnumerolote);


    gen_pkg_gener.gen_pr_gener_stats('int_ivr_corpo_estad_pedid',
                                     USER);
    ---SI LA INTERFAZ REQUIERE EL CAMBIO DE COD CLIENTE POR DOCUMENTO IDENTIDAD
    IF lsvalpain = 'S' THEN
      UPDATE int_ivr_corpo_estad_pedid c
         SET c.cod_clie = ( SELECT mci.num_docu_iden
                              FROM mae_clien mc
                                  ,mae_clien_ident mci
                             WHERE mc.oid_clie = mci.clie_oid_clie
                               AND mci.val_iden_docu_prin = 1
                               AND mc.cod_clie = c.cod_clie
                           );
    END IF;

    --Eliminando posiciones de codigo consultora
    IF lsElimPosiCodigo > 0 THEN
      SELECT td.val_long INTO lnTamanio FROM mae_tipo_docum td WHERE td.cod_tipo_docu = '01';
      lsElimPosiCodigo := lnTamanio - lsElimPosiCodigo;
      UPDATE int_ivr_corpo_estad_pedid c
         SET c.cod_clie = SUBSTR(c.cod_clie,-lnTamanio,lsElimPosiCodigo);
    END IF;

    --eliminar caracteres al inicio
    lnTamanio:=0;
    SELECT p.lon_codi_clie INTO lnTamanio FROM bas_pais p WHERE p.cod_pais = pscodigopais;
    UPDATE int_ivr_corpo_estad_pedid ep
       SET ep.cod_clie = nvl(SUBSTR(ep.cod_clie,-lnTamanio,lnTamanio),0)
     WHERE regexp_like(ep.cod_clie,'^(r|c)','i');


    lnnumregistros := 0;
    lbabrirutlfile := TRUE;

    /* Generando Archivo de Texto (Detalle) */

    OPEN c_interfaz(lnmaxcarcodclie);
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
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP

          lslinea := interfazrecord(x).codigocia || ';' ||
                     interfazrecord(x).codigocliente || ';' ||
                     interfazrecord(x).situacion || ';' ||
                     interfazrecord(x).anocampana || ';' ||
                     interfazrecord(x).numerocampana || ';' ||
                     interfazrecord(x).anosituacion || ';' ||
                     interfazrecord(x).messituacion || ';' ||
                     interfazrecord(x).diasituacion || ';' ||
                     interfazrecord(x).valor || ';' ||
                     interfazrecord(x).motivorechazo || ';' ||
                     to_char(interfazrecord(x).fechaentrega,'YYYY') || ';' ||
                     to_char(interfazrecord(x).fechaentrega,'MM') || ';' ||
                     to_char(interfazrecord(x).fechaentrega,'dd');

          utl_file.put_line(v_handle,
                            lslinea);
          lnnumregistros := lnnumregistros + 1;
        END LOOP;

      END IF;

      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;

    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
    END IF;


    int_pr_ivr_corpo_regis_cntrl(lscodigocia,
                                 psnombrecontrol,
                                 lnnumregistros,
                                 pscodigoperiodo);

    IF NOT lbabrirutlfile THEN
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
                              'ERROR INT_PR_IVR_CORPO_ESTAD_PEDID: ' || ls_sqlerrm);
  END int_pr_ivr_corpo_estad_pedid;


/*******************************************************************************
Descripcion       : Genera Interfaz Enviar Estado de Pedidos - IVR Corporativo
                    dependiendo de psIndProceso:
                      Si psIndProceso='T' se borran los datos de la tabla
                      int_ivr_clien y se cargan todos los datos
                      Si psIndProceso='N' se cargan solo los datos modificados
  Fecha Creacion    : 15/12/2010
  Fecha Modificacion: 23/05/2014
  Autor             : CSVD - FFVV
********************************************************************************/
PROCEDURE INT_PR_IVR_CORPO_ESTAD_PEDI2
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigomarca    VARCHAR2,
    pscodigocanal    VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    psindproceso     VARCHAR2,
    psnumerolote     VARCHAR2,
    psnombrecontrol  VARCHAR2
  ) IS

    CURSOR c_interfaz(lnmaxcarcodclie NUMBER) IS

      SELECT pscodigopais,
             cod_comp,
             lpad(cod_clie, lnmaxcarcodclie, '0'),
             cod_situ,
             ano_camp,
             cod_camp,
             to_char(fec_situ, 'YYYY'),
             to_char(fec_situ, 'MM'),
             to_char(fec_situ, 'DD'),
             TRIM(to_char(val_mont, '999999999990.99')),
             cod_moti_rech,
             fec_entr_pedi
        FROM int_ivr_corpo_estad_pedid
       WHERE num_lote = psnumerolote;

    TYPE interfazrec IS RECORD
    (
      codigopais    bas_pais.cod_pais%TYPE,
      codigocia     int_ivr_corpo_estad_pedid.cod_comp%TYPE,
      codigocliente int_ivr_corpo_estad_pedid.cod_clie%TYPE,
      situacion     int_ivr_corpo_estad_pedid.cod_situ %TYPE,
      anocampana    int_ivr_corpo_estad_pedid.ano_camp%TYPE,
      numerocampana int_ivr_corpo_estad_pedid.cod_camp%TYPE,
      anosituacion  VARCHAR2(4),
      messituacion  VARCHAR2(2),
      diasituacion  VARCHAR2(2),
      valor         VARCHAR2(20),
      motivorechazo int_ivr_corpo_estad_pedid.cod_moti_rech%TYPE,
      fechaentrega  int_ivr_corpo_estad_pedid.fec_entr_pedi%TYPE
    );

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo VARCHAR2(50);

    lscodigocia    int_ivr_param_compa.cod_comp%TYPE;
    lnnumregistros NUMBER := 0;

    lnmaxcarcodclie NUMBER := 15;
    lbabrirutlfile  BOOLEAN;

    lsperiodoanterior seg_perio_corpo.cod_peri%TYPE;

    lnidmarca   NUMBER;
    lnidcanal   NUMBER;
    lnidpais    NUMBER;
    lnidperiodo NUMBER;
    lsvalpain bas_param_inter.val_pain%TYPE;
    lsvalDiasEntregaPedido bas_param_inter.val_pain%TYPE;
    lsOrigenFechaEntrega    bas_param_inter.val_pain%TYPE;
    lnTamanio NUMBER;

  BEGIN

    /* Obtiene el codigo de Compañia */
    SELECT int_ivr_param_compa.cod_comp
      INTO lscodigocia
      FROM int_ivr_param_compa
     WHERE int_ivr_param_compa.cod_pais = pscodigopais;

    --variable para cambiar el codigo de cliente por numero de documento
    SELECT nvl((select b.val_pain
                  from bas_param_inter b
                 where b.inte_cod_inte = pscodigointerfaz
                   and b.cod_pain = '04'
               ), 'N') INTO lsvalpain FROM dual;

   --variable para calcular la fecha de entrega de pedido
    SELECT nvl((select b.val_pain
                  from bas_param_inter b
                 where b.inte_cod_inte = pscodigointerfaz
                   and b.nom_pain = 'cantidadDiasEntregaPedido'
               ), '0') INTO lsvalDiasEntregaPedido FROM dual;

    --variable para obtener el Origen de Fecha de Entrega a mostrar
    SELECT nvl((SELECT b.val_pain
                  FROM bas_param_inter b
                 WHERE b.inte_cod_inte = pscodigointerfaz
                   AND b.nom_pain = 'indOrigenFechaEntrega'
               ), '0') INTO lsOrigenFechaEntrega FROM dual;

    --Determino los Ids de Pais, Marca y Canal y periodo
    lnidpais    := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);
    lnidmarca   := gen_pkg_gener.gen_fn_devuelve_id_marca(pscodigomarca);
    lnidcanal   := gen_pkg_gener.gen_fn_devuelve_id_canal(pscodigocanal);
    lnidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoperiodo);

    --Periodo Anterior
    lsperiodoanterior := per_pkg_repor_perce.per_fn_obtie_perio(pscodigoperiodo,
                                                                lnidpais,
                                                                lnidmarca,
                                                                lnidcanal,
                                                                -1);

    gen_pkg_gener.gen_pr_trunc_table('int_ivr_corpo_estad_pedid');

    INSERT INTO int_ivr_corpo_estad_pedid
      (
       cod_comp,
       cod_clie,
       cod_situ,
       ano_camp,
       cod_camp,
       fec_situ,
       val_mont,
       cod_moti_rech,
       num_lote,
       fec_entr_pedi
      )
      SELECT lscodigocia codigocia,
             mc.cod_clie cliente,
             'P',
             substr(spc.cod_peri, 0, 4),
             substr(spc.cod_peri, 5, 2),
             psc2.fec_fact fecha,
             psc2.val_tota_paga_loca,
             NULL,
             psnumerolote,
             CASE
               WHEN lsOrigenFechaEntrega = 1  THEN
                    psc2.fec_fact +   lsvalDiasEntregaPedido

               WHEN lsOrigenFechaEntrega = 2  THEN
             (
              SELECT x.fec_inic
                FROM cra_crono x
               WHERE x.zzon_oid_zona = psc1.zzon_oid_zona
                 AND x.cact_oid_acti = ca.oid_acti
                 AND x.perd_oid_peri = lnidperiodo
             )

               WHEN lsOrigenFechaEntrega = 3  THEN
                   (
                    SELECT fec
                      FROM ped_segui_pedid psp
                     WHERE psp.soca_oid_soli_cabe = psc2.oid_soli_cabe
                   )
              END
        FROM cra_perio           cp,
             ped_solic_cabec     psc1,
             ped_solic_cabec     psc2,
             ped_tipo_solic_pais ptsp,
             seg_perio_corpo     spc,
             seg_pais            sp,
             seg_marca           sm,
             seg_canal           sc,
             ped_tipo_solic      pts,
             mae_clien           mc,
             cra_activ           ca
       WHERE psc1.soca_oid_soli_cabe = psc2.oid_soli_cabe
         AND (cp.oid_peri = psc1.perd_oid_peri)
         AND (ptsp.oid_tipo_soli_pais = psc1.tspa_oid_tipo_soli_pais)
         AND (mc.oid_clie = psc1.clie_oid_clie)
         AND (spc.oid_peri = cp.peri_oid_peri)
         AND (sp.oid_pais = psc1.pais_oid_pais)
         AND (sm.oid_marc = cp.marc_oid_marc)
         AND (sc.oid_cana = cp.cana_oid_cana)
         AND (pts.oid_tipo_soli = ptsp.tsol_oid_tipo_soli)
         AND (pts.cod_tipo_soli IN (SELECT cod_tipo_soli FROM int_evi_tipo_solic_pais))
         AND psc1.esso_oid_esta_soli IN (1,5)
         AND (psc1.grpr_oid_grup_proc = 5)
         AND (psc1.ind_ts_no_conso = 1)
         AND (psc1.ind_oc = 1)
         AND (psc1.fec_fact IS NOT NULL)
         AND (sp.cod_pais = pscodigopais)
         AND (sm.cod_marc = pscodigomarca)
         AND (sc.cod_cana = pscodigocanal)
         AND (spc.cod_peri = pscodigoperiodo)
         AND ca.cod_acti = (SELECT p.cod_oper FROM int_ivr_corpo_param_poven p WHERE p.cod_acce = 'REP')
         AND ca.pais_oid_pais = lnidpais;

    INSERT INTO int_ivr_corpo_estad_pedid
      (
       cod_comp,
       cod_clie,
       cod_situ,
       ano_camp,
       cod_camp,
       fec_situ,
       val_mont,
       cod_moti_rech,
       num_lote
      )
      SELECT lscodigocia codigocia,
             cod_clie,
             'R',
             substr(cod_peri, 0, 4),
             substr(cod_peri, 5, 2),
             trunc(fec_digi),
             0,
             (
              SELECT --substr(mv.cod_mens,1,2)
                     CASE length(mv.cod_vali)
                     WHEN 5 THEN
                          '0' || substr(mv.cod_vali, 5,length(mv.cod_vali)-4 )
                     ELSE
                          substr(mv.cod_vali, 5,length(mv.cod_vali)-4 )
                     END
                FROM sto_detal_docum_excep e,
                     sto_mensa_valid       mv
               WHERE e.cod_pais = do.cod_pais
                 AND e.cod_tipo_docu = do.cod_tipo_docu
                 AND e.num_lote = do.num_lote
                 AND e.sec_nume_docu = do.sec_nume_docu
                 AND e.fec_digi = do.fec_digi
                 AND e.cod_mens = mv.cod_mens
                 AND e.cod_tipo_docu = mv.cod_tipo_docu
                 AND rownum = 1
             ),
             psnumerolote
        FROM (
              SELECT dd.cod_pais,
                     dd.cod_tipo_docu,
                     dd.num_lote,
                     dd.sec_nume_docu,
                     dd.cod_peri,
                     dd.cod_clie,
                     MAX(de.fec_digi) fec_digi,
                     zz.oid_zona
                FROM sto_detal_docum_excep de,
                     sto_docum_digit       dd,
                     zon_zona              zz
               WHERE de.cod_pais = pscodigopais
                 AND dd.cod_pais = de.cod_pais
                 AND de.cod_tipo_docu = 'OCC'
                 AND dd.cod_tipo_docu = de.cod_tipo_docu
                 AND dd.sec_nume_docu = de.sec_nume_docu
                 AND dd.num_lote = de.num_lote
                 AND dd.cod_clie IS NOT NULL
                 AND dd.ind_envi = '0'
                 AND de.ind_apro = '0'
                 AND dd.cod_peri = pscodigoperiodo
                 AND zz.cod_zona = dd.cod_zona
                 AND NOT EXISTS (
                                 SELECT 1
                        FROM sto_docum_digit d
                       WHERE d.cod_pais = dd.cod_pais
                         AND d.cod_tipo_docu = dd.cod_tipo_docu
                         AND d.num_lote = dd.num_lote
                         AND d.sec_nume_docu = dd.sec_nume_docu
                                    AND d.ind_envi = '1'
                                )
               GROUP BY dd.cod_pais,
                        dd.cod_tipo_docu,
                        dd.num_lote,
                        dd.sec_nume_docu,
                        dd.cod_peri,
                        dd.cod_clie,
                        zz.oid_zona
             ) do
         WHERE NOT regexp_like(do.cod_clie,'^(c)','i');

    INSERT INTO int_ivr_corpo_estad_pedid
      (
       cod_comp,
       cod_clie,
       cod_situ,
       ano_camp,
       cod_camp,
       fec_situ,
       val_mont,
       cod_moti_rech,
       num_lote,
       fec_entr_pedi
      )
      SELECT lscodigocia codigocia,
             mc.cod_clie cliente,
             'P',
             substr(spc.cod_peri, 0, 4),
             substr(spc.cod_peri, 5, 2),
             psc2.fec_fact fecha,
             psc2.val_tota_paga_loca,
             NULL,
             psnumerolote,
             CASE
               WHEN lsOrigenFechaEntrega = 1  THEN
                    psc2.fec_fact +   lsvalDiasEntregaPedido

               WHEN lsOrigenFechaEntrega = 2  THEN
             (
              SELECT x.fec_inic
                FROM cra_crono x
               WHERE x.zzon_oid_zona = psc1.zzon_oid_zona
                 AND x.cact_oid_acti = ca.oid_acti
                 AND x.perd_oid_peri = lnidperiodo
             )

               WHEN lsOrigenFechaEntrega = 3  THEN
                   (
                    SELECT fec
                      FROM ped_segui_pedid psp
                     WHERE psp.soca_oid_soli_cabe = psc2.oid_soli_cabe
                   )
              END
        FROM cra_perio           cp,
             ped_solic_cabec     psc1,
             ped_solic_cabec     psc2,
             ped_tipo_solic_pais ptsp,
             seg_perio_corpo     spc,
             seg_pais            sp,
             seg_marca           sm,
             seg_canal           sc,
             ped_tipo_solic      pts,
             mae_clien           mc,
             cra_activ           ca
       WHERE psc1.soca_oid_soli_cabe = psc2.oid_soli_cabe
         AND (cp.oid_peri = psc1.perd_oid_peri)
         AND (ptsp.oid_tipo_soli_pais = psc1.tspa_oid_tipo_soli_pais)
         AND (mc.oid_clie = psc1.clie_oid_clie)
         AND (spc.oid_peri = cp.peri_oid_peri)
         AND (sp.oid_pais = psc1.pais_oid_pais)
         AND (sm.oid_marc = cp.marc_oid_marc)
         AND (sc.oid_cana = cp.cana_oid_cana)
         AND (pts.oid_tipo_soli = ptsp.tsol_oid_tipo_soli)
         AND (pts.cod_tipo_soli IN (SELECT cod_tipo_soli FROM int_evi_tipo_solic_pais))
         AND psc1.esso_oid_esta_soli IN (1,5)
         AND (psc1.grpr_oid_grup_proc = 5)
         AND (psc1.ind_ts_no_conso = 1)
         AND (psc1.ind_oc = 1)
         AND (psc1.fec_fact IS NOT NULL)
         AND (sp.cod_pais = pscodigopais)
         AND (sm.cod_marc = pscodigomarca)
         AND (sc.cod_cana = pscodigocanal)
         AND (spc.cod_peri = lsperiodoanterior)
         AND ca.cod_acti = (SELECT p.cod_oper FROM int_ivr_corpo_param_poven p WHERE p.cod_acce = 'REP')
         AND ca.pais_oid_pais = lnidpais;
/*         AND NOT EXISTS (SELECT 1
                FROM int_ivr_corpo_estad_pedid ivr
               WHERE ivr.cod_clie = mc.cod_clie
                 AND ivr.num_lote = psnumerolote);*/

    ---SI LA INTERFAZ REQUIERE EL CAMBIO DE COD CLIENTE POR DOCUMENTO IDENTIDAD
    IF lsvalpain = 'S' THEN
      UPDATE int_ivr_corpo_estad_pedid c
         SET c.cod_clie = ( SELECT mci.num_docu_iden
                              FROM mae_clien mc
                                  ,mae_clien_ident mci
                             WHERE mc.oid_clie = mci.clie_oid_clie
                               AND mci.val_iden_docu_prin = 1
                               AND mc.cod_clie = c.cod_clie
                           );
    END IF;

    --eliminar caracteres al inicio
    lnTamanio:=0;
    SELECT p.lon_codi_clie INTO lnTamanio FROM bas_pais p WHERE p.cod_pais = pscodigopais;
    UPDATE int_ivr_corpo_estad_pedid ep
       SET ep.cod_clie = nvl(SUBSTR(ep.cod_clie,-lnTamanio,lnTamanio),0)
     WHERE regexp_like(ep.cod_clie,'^(r|c)','i');

    gen_pkg_gener.gen_pr_gener_stats('int_ivr_corpo_estad_pedid', USER);

    lnnumregistros := 0;
    lbabrirutlfile := TRUE;

    /* Generando Archivo de Texto (Detalle) */
    OPEN c_interfaz(lnmaxcarcodclie);
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
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP

          lslinea := interfazrecord(x).codigopais || ';' ||
                     interfazrecord(x).codigocia || ';' ||
                     interfazrecord(x).codigocliente || ';' ||
                     interfazrecord(x).situacion || ';' ||
                     interfazrecord(x).anocampana || ';' ||
                     interfazrecord(x).numerocampana || ';' ||
                     interfazrecord(x).anosituacion || ';' ||
                     interfazrecord(x).messituacion || ';' ||
                     interfazrecord(x).diasituacion || ';' ||
                     interfazrecord(x).valor || ';' ||
                     interfazrecord(x).motivorechazo || ';' ||
                     to_char(interfazrecord(x).fechaentrega,'YYYY') || ';' ||
                     to_char(interfazrecord(x).fechaentrega,'MM') || ';' ||
                     to_char(interfazrecord(x).fechaentrega,'dd');

          utl_file.put_line(v_handle,
                            lslinea);
          lnnumregistros := lnnumregistros + 1;
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
    END IF;

    INT_PR_IVR_CORPO_REGIS_CNTRL(lscodigocia,
                                 psnombrecontrol,
                                 lnnumregistros,
                                 pscodigoperiodo);

    IF NOT lbabrirutlfile THEN
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR', lsdirtempo, psnombrearchivo, lsnombrearchivo);
    END IF;

    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123, 'ERROR INT_PR_IVR_CORPO_ESTAD_PEDI2: ' || ls_sqlerrm);

END INT_PR_IVR_CORPO_ESTAD_PEDI2;


  /***************************************************************************
  Descripcion       : Genera Interfaz Emviar Tabla de maestro de dupla cyzone de IVR dendiendo
                      de psIndProceso:
                      Si psIndProceso='T' se borran los datos de la tabla
                      int_ivr_corpo_resul_campa y se cargan todos los datos
                      Si psIndProceso='N' se cargan solo los datos modificados
  Fecha Creacion    : 23/07/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE int_pr_ivr_corpo_maest_ducyz
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    psindproceso     VARCHAR2,
    psnumerolote     VARCHAR2,
    psnombrecontrol  VARCHAR2
  ) IS
    CURSOR c_interfaz(lnmaxcarcodclie NUMBER) IS

      SELECT cod_comp,
             lpad(cod_clie,
                  lnmaxcarcodclie,
                  '0'),
             lpad(cod_dupl,
                  lnmaxcarcodclie,
                  '0'),
             nom_dupl,
             pun_acum,
             fec_envi,
             est_dupl,
             cod_moti_rech,
             ind_vige
        FROM int_ivr_corpo_maest_ducyz
       WHERE num_lote = psnumerolote;

    TYPE interfazrec IS RECORD(
      codigocia         int_ivr_corpo_maest_ducyz.cod_comp%TYPE,
      codigocliente     int_ivr_corpo_maest_ducyz.cod_clie%TYPE,
      codigodupla       int_ivr_corpo_maest_ducyz.cod_dupl %TYPE,
      nombredupla       int_ivr_corpo_maest_ducyz.nom_dupl %TYPE,
      puntosacumulados  int_ivr_corpo_maest_ducyz.pun_acum%TYPE,
      fechaenvionovedad int_ivr_corpo_maest_ducyz.fec_envi%TYPE,
      estado            int_ivr_corpo_maest_ducyz.est_dupl%TYPE,
      motivorechazo     int_ivr_corpo_maest_ducyz.cod_moti_rech%TYPE,
      indicadordupla    int_ivr_corpo_maest_ducyz.ind_vige%TYPE);

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo VARCHAR2(50);

    lscodigocia    int_ivr_param_compa.cod_comp%TYPE;
    lnnumregistros NUMBER := 0;

    lnmaxcarcodclie      NUMBER := 15;
    lbabrirutlfile       BOOLEAN;
    lnoidperiodo         cra_perio.oid_peri%TYPE;
    lnoidperiodoanterior cra_perio.oid_peri%TYPE;
    lsvalpain bas_param_inter.val_pain%TYPE;
    --parametro para eliminar posiciones de codigo cliente
    lsElimPosiCodigo bas_param_pais.val_para%TYPE;
    lnTamanio NUMBER;

  BEGIN

    /* Obtiene el codigo de Compañia */
    SELECT int_ivr_param_compa.cod_comp
      INTO lscodigocia
      FROM int_ivr_param_compa
     WHERE int_ivr_param_compa.cod_pais = pscodigopais;

     --variable para cambiar el codigo de cliente por numero de documento
    SELECT nvl((select b.val_pain
                  from bas_param_inter b
                 where b.inte_cod_inte = pscodigointerfaz
                   and b.cod_pain = '04'
               ), 'N') INTO lsvalpain FROM dual;

    --variable para eliminar ultimas posiciones de codigo Consultora
    BEGIN
        --variable para cambiar el codigo de cliente por numero de documento
        SELECT b.val_pain INTO lsElimPosiCodigo
          FROM bas_param_inter b
         WHERE b.inte_cod_inte = pscodigointerfaz AND
               b.nom_pain = 'indElimPosiCod' AND
               b.est_pain = 1;
      EXCEPTION
         WHEN NO_DATA_FOUND THEN
              lsElimPosiCodigo := 0;
    END;

    lnoidperiodo         := gen_pkg_gener.gen_fn_devuelve_id_perio(pscodigoperiodo);
    lnoidperiodoanterior := gen_pkg_gener.gen_fn_devuelve_id_perio(pscodigoperiodo) - 1;

    gen_pkg_gener.gen_pr_trunc_table('int_ivr_corpo_maest_ducyz');
    INSERT INTO int_ivr_corpo_maest_ducyz
      (cod_comp,
       cod_clie,
       cod_dupl,
       nom_dupl,
       pun_acum,
       fec_envi,
       est_dupl,
       cod_moti_rech,
       ind_vige,
       num_lote)
      SELECT lscodigocia codigocia,
             clientes.cod_clie codigocliente,
             gen_pkg_gener.gen_fn_devuelve_cod_clie(mae_clien_vincu.clie_oid_clie_vndo) codigodupla,
             TRIM(inscritas.val_nom1) || ' ' || TRIM(inscritas.val_nom2) || ' ' ||
             TRIM(inscritas.val_ape1) || ' ' || TRIM(inscritas.val_ape2),
             (SELECT nvl(SUM(pun_acum),
                         0)
                FROM int_ivr_corpo_progr_ducyz
               WHERE cod_clie = clientes.cod_clie) pun_acum,
             to_char(SYSDATE,
                     'YYYYMMDD'),
             'A',
             '',
             '',
             psnumerolote
        FROM mae_clien inscritas,
             mae_clien_datos_adici,
             mae_clien_vincu,
             mae_tipo_vincu,
             (SELECT c.oid_clie,
                     c.cod_clie
                FROM mae_clien            c,
                     mae_clien_tipo_subti ctst,
                     mae_clien_clasi      cc,
                     mae_tipo_clien       tc,
                     mae_subti_clien      stc,
                     mae_tipo_clasi_clien tcc,
                     mae_clasi            cl
               WHERE c.oid_clie = ctst.clie_oid_clie
                 AND ctst.oid_clie_tipo_subt = cc.ctsu_oid_clie_tipo_subt
                 AND ctst.ticl_oid_tipo_clie = tc.oid_tipo_clie
                 AND ctst.sbti_oid_subt_clie = stc.oid_subt_clie
                 AND cc.tccl_oid_tipo_clasi = tcc.oid_tipo_clas
                 AND cc.clas_oid_clas = cl.oid_clas
                 AND tc.oid_tipo_clie = stc.ticl_oid_tipo_clie
                 AND stc.oid_subt_clie = tcc.sbti_oid_subt_clie
                 AND tcc.oid_tipo_clas = cl.tccl_oid_tipo_clas
                 AND tc.cod_tipo_clie = '02'
                 AND stc.cod_subt_clie IN ('04',
                                           '06')
                 AND cl.cod_clas = '01'
                 AND tcc.cod_tipo_clas = '23') clientes
       WHERE inscritas.oid_clie = mae_clien_datos_adici.clie_oid_clie
         AND inscritas.oid_clie = mae_clien_vincu.clie_oid_clie_vndo
         AND mae_clien_vincu.tivc_oid_tipo_vinc = mae_tipo_vincu.oid_tipo_vinc
         AND mae_tipo_vincu.cod_tipo_vinc = '01'
         AND mae_clien_vincu.clie_oid_clie_vnte = clientes.oid_clie
         AND mae_clien_vincu.fec_hast IS NULL
      UNION
      SELECT lscodigocia codigocia,
             c.cod_clie,
             '',
             '',
             (SELECT nvl(SUM(pun_acum),
                         0)
                FROM int_ivr_corpo_progr_ducyz
               WHERE cod_clie = c.cod_clie),
             to_char(SYSDATE,
                     'YYYYMMDD'),
             'R',
             m.cod_mens,
             '',
             psnumerolote
        FROM msg_mensa       m,
             msg_buzon_mensa b,
             mae_clien       c
       WHERE c.oid_clie = b.clie_oid_clie
         AND b.peri_oid_peri IN (lnoidperiodo,
                                 lnoidperiodoanterior)
         AND m.cod_mens IN (SELECT des_ope1
                              FROM int_ivr_corpo_param
                             WHERE cod_pais = pscodigopais
                               AND cod_acce = 'RDC')
         AND b.mens_oid_mens = m.oid_mens;

    UPDATE int_ivr_corpo_maest_ducyz ivr
       SET ind_vige = '1'
     WHERE EXISTS
     (SELECT ivr.cod_clie FROM int_ivr_corpo_progr_ducyz ivl WHERE ivr.cod_clie = ivl.cod_clie);

    UPDATE int_ivr_corpo_maest_ducyz ivr
       SET ind_vige = '0'
     WHERE NOT EXISTS
     (SELECT ivr.cod_clie FROM int_ivr_corpo_progr_ducyz ivl WHERE ivr.cod_clie = ivl.cod_clie);

    ---SI LA INTERFAZ REQUIERE EL CAMBIO DE COD CLIENTE POR DOCUMENTO IDENTIDAD
    IF lsvalpain = 'S' THEN
      UPDATE int_ivr_corpo_maest_ducyz c
         SET c.cod_clie = ( SELECT mci.num_docu_iden
                              FROM mae_clien mc
                                  ,mae_clien_ident mci
                             WHERE mc.oid_clie = mci.clie_oid_clie
                               AND mci.val_iden_docu_prin = 1
                               AND mc.cod_clie = c.cod_clie
                           );
    END IF;

        --Eliminando posiciones de codigo consultora
    IF lsElimPosiCodigo > 0 THEN
      SELECT td.val_long INTO lnTamanio FROM mae_tipo_docum td WHERE td.cod_tipo_docu = '01';
      lsElimPosiCodigo := lnTamanio - lsElimPosiCodigo;
      UPDATE int_ivr_corpo_maest_ducyz c
         SET c.cod_clie = SUBSTR(c.cod_clie,-lnTamanio,lsElimPosiCodigo);
    END IF;

    gen_pkg_gener.gen_pr_gener_stats('int_ivr_corpo_maest_ducyz',
                                     USER);

    lnnumregistros := 0;

    lbabrirutlfile := TRUE;

    /* Generando Archivo de Texto (Detalle) */
    OPEN c_interfaz(lnmaxcarcodclie);
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
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP

          lslinea := interfazrecord(x).codigocia || ';' || interfazrecord(x).codigocliente || ';' || interfazrecord(x)
                     .codigodupla || ';' || interfazrecord(x).nombredupla || ';' || interfazrecord(x)
                     .puntosacumulados || ';' || interfazrecord(x)
                     .fechaenvionovedad || ';' || interfazrecord(x).estado || ';' || interfazrecord(x)
                     .motivorechazo || ';' || interfazrecord(x).indicadordupla;

          utl_file.put_line(v_handle,
                            lslinea);

          lnnumregistros := lnnumregistros + 1;
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
    END IF;

    IF NOT lbabrirutlfile THEN
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;

    int_pr_ivr_corpo_regis_cntrl(lscodigocia,
                                 psnombrecontrol,
                                 lnnumregistros,
                                 pscodigoperiodo);

    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_IVR_CORPO_MAEST_DUCYZ: ' || ls_sqlerrm);
  END int_pr_ivr_corpo_maest_ducyz;

  /**************************************************************************
Descripcion : Genera la Informacion de Clientes - IVR Corporativo
  Fecha Creacion : 30/10/2009
  Fecha Modificacion : 11/02/2015 
  Parametros Entrada :
  Autor : Jose Cairampoma
  Autor Mod: CSVD - FFVV
  ***************************************************************************/
PROCEDURE INT_PR_IVR_CORPO_GENER_CLIEN
  (
    psindproceso      IN VARCHAR2,
    psnumerolote      IN VARCHAR2,
    pscodigocia       IN VARCHAR2,
    psultimaejecucion IN VARCHAR2,
    psenviarhistorico IN VARCHAR2,
    pscodigopais      IN VARCHAR2,
    pnidpais          IN NUMBER,
    pscodigomarca     IN VARCHAR2,
    pnidmarca         IN NUMBER,
    pscodigocanal     IN VARCHAR2,
    pnidcanal         IN NUMBER,
    pscodigoperiodo   IN VARCHAR2,
    pnidperiodo       IN NUMBER,
    pscodigointerfaz  IN VARCHAR2,
    psindCambiarCodigo IN VARCHAR2
  ) IS

    CURSOR clie_actu_cur(vdultimaejecucion DATE) IS

      SELECT clie_oid_clie
        FROM ccc_movim_cuent_corri
       WHERE fec_ulti_actu > vdultimaejecucion
      UNION
      SELECT oid_clie
        FROM mae_clien
       WHERE fec_ulti_actu > vdultimaejecucion
      UNION
      SELECT clie_oid_clie
        FROM mae_clien_datos_adici
       WHERE fec_ulti_actu > vdultimaejecucion
      UNION
      SELECT clie_oid_clie
        FROM mae_clien_direc
       WHERE fec_ulti_actu > vdultimaejecucion
      UNION
      SELECT clie_oid_clie
        FROM mae_clien_comun
       WHERE fec_ulti_actu > vdultimaejecucion
      UNION
      SELECT clie_oid_clie
        FROM mae_clien_unida_admin
       WHERE fec_ulti_actu > vdultimaejecucion
      UNION
      SELECT clie_oid_clie
        FROM mae_clien_tipo_subti
       WHERE fec_ulti_actu > vdultimaejecucion
      UNION
      SELECT clie_oid_clie
        FROM ZON_SECCI
       WHERE clie_oid_clie IS NOT NULL
         AND fec_ulti_actu > vdultimaejecucion
     UNION
      SELECT clie_oid_clie
        FROM ZON_ZONA
       WHERE clie_oid_clie IS NOT NULL
       AND fec_ulti_actu > vdultimaejecucion
     UNION
      SELECT clie_oid_clie
        FROM ZON_REGIO
       WHERE clie_oid_clie IS NOT NULL
         AND fec_ulti_actu > vdultimaejecucion
     UNION
      SELECT x.clie_oid_clie
        FROM mae_clien_tipo_subti x,
             mae_clien_clasi      y
       WHERE x.oid_clie_tipo_subt = y.ctsu_oid_clie_tipo_subt
         AND y.fec_ulti_actu > vdultimaejecucion
      ;

    TYPE clie_actu_oid_type IS TABLE OF int_reu_oid_clien_actua.oid_clie%TYPE;
    clie_actu_oid clie_actu_oid_type;

    rowlimit NUMBER := 5000;
    i        NUMBER;

    CURSOR c_marcasituasion IS

        WITH temp AS
           (
            SELECT DISTINCT oid_clie,
                   num_nive_grav_bloq,
                   pb.cod_oper_homo,
                   pb.des_ope1
              FROM mae_clien_bloqu           b,
                   mae_tipo_bloqu            tb,
                   int_ivr_corpo_clien       ic,
                   int_ivr_corpo_param_poven pb
             WHERE b.clie_oid_clie = ic.oid_clie
               AND b.tibq_oid_tipo_bloq = tb.oid_tipo_bloq
               AND b.fec_desb IS NULL
               AND ic.num_lote = psnumerolote
               AND tb.cod_tipo_bloq = pb.cod_oper
               AND pb.cod_acce = 'BLQ'
           )
           SELECT a.oid_clie,
                  b.cod_oper_homo,
                  b.des_ope1
             FROM (
                   SELECT oid_clie,
                          MIN(num_nive_grav_bloq) min_nive_grav
                     FROM temp
                    GROUP BY oid_clie
                  ) a,
                  (
                   SELECT DISTINCT oid_clie,
                          num_nive_grav_bloq,
                          cod_oper_homo,
                          des_ope1
                     FROM temp
                  ) b
            WHERE a.min_nive_grav = b.num_nive_grav_bloq
              AND a.oid_clie = b.oid_clie;


    TYPE t_oidclie IS TABLE OF int_ivr_corpo_clien.oid_clie%TYPE;
    TYPE t_marcsitu IS TABLE OF mae_tipo_bloqu.cod_tipo_bloq%TYPE;
    TYPE t_desbloq IS TABLE OF int_ivr_corpo_param_poven.des_ope1%TYPE;

    v_oidclie  t_oidclie;
    v_marcsitu t_marcsitu;
    v_desbloq  t_desbloq;

    ldultimaejecucion DATE:= null;
    --parametro para eliminar posiciones de codigo cliente
    lsElimPosiCodigo bas_param_pais.val_para%TYPE;
    --parametro para eliminar posiciones de documento identidad
    lsElimPosiDocIde bas_param_inter.val_pain%TYPE;
    --parametro para eliminar letras de documento identidad
    lsElimLetrasDocIde bas_param_inter.val_pain%TYPE;
    
    --parametro para Tipo Clasificacion Top
    lsTipoClasificacion bas_param_pais.val_para%TYPE;
    --parametro para Lista Clasificacion Top
    lsListaClasificacion bas_param_pais.val_para%TYPE;


    lnTamanio NUMBER;
    lnTamanioDocId NUMBER;


BEGIN

    --parametros de interface
    --variable para eliminar ultimas posiciones de codigo Consultora
    BEGIN
        --variable para cambiar el codigo de cliente por numero de documento
        SELECT b.val_pain INTO lsElimPosiCodigo
          FROM bas_param_inter b
         WHERE b.inte_cod_inte = pscodigointerfaz AND
               b.nom_pain = 'indElimPosiCod' AND
               b.est_pain = 1;
      EXCEPTION
         WHEN NO_DATA_FOUND THEN
              lsElimPosiCodigo := 0;
    END;

    --variable para eliminar ultimas posiciones de Documento Identidad
    BEGIN
        SELECT b.val_pain INTO lsElimPosiDocIde
          FROM bas_param_inter b
         WHERE b.inte_cod_inte = pscodigointerfaz AND
               b.nom_pain = 'numElimPosiDocIde' AND
               b.est_pain = 1;
      EXCEPTION
         WHEN NO_DATA_FOUND THEN
              lsElimPosiDocIde := 0;
    END;
    
    --variable para eliminar las letras de Documento Identidad
    BEGIN
        SELECT b.val_pain INTO lsElimLetrasDocIde
          FROM bas_param_inter b
         WHERE b.inte_cod_inte = pscodigointerfaz AND
               b.nom_pain = 'indElimLetrasDocIde' AND
               b.est_pain = 1;
      EXCEPTION
         WHEN NO_DATA_FOUND THEN
              lsElimLetrasDocIde := 0;
    END;

    ldultimaejecucion := to_date(psultimaejecucion,'YYYYMMDDHHMISS');

    dbms_stats.gather_table_stats(ownname => USER, tabname => 'INT_IVR_CORPO_ESTAD_PEDID', cascade => TRUE);
    dbms_stats.gather_table_stats(ownname => USER, tabname => 'INT_IVR_CORPO_MAEST_DUCYZ', cascade => TRUE);
    dbms_stats.gather_table_stats(ownname => USER, tabname => 'INT_IVR_CORPO_CONCU', cascade => TRUE);
    dbms_stats.gather_table_stats(ownname => USER, tabname => 'INT_IVR_CORPO_SERVI_POVEN', cascade => TRUE);
    dbms_stats.gather_table_stats(ownname => USER, tabname => 'INT_IVR_CORPO_BONOS', cascade => TRUE);

    EXECUTE IMMEDIATE 'TRUNCATE TABLE INT_IVR_CORPO_CLIEN_ACTUA';

    IF psenviarhistorico = 'S' OR ldultimaejecucion IS NULL THEN

      EXECUTE IMMEDIATE 'TRUNCATE TABLE INT_IVR_CORPO_CLIEN';

      INSERT /*+PARALLEL(3)*/INTO int_ivr_corpo_clien_actua
        (oid_clie)
        SELECT /*+PARALLEL(A 3)*/ oid_clie FROM mae_clien A;

    ELSE
      OPEN clie_actu_cur(ldultimaejecucion);
        LOOP
          FETCH clie_actu_cur BULK COLLECT
            INTO clie_actu_oid LIMIT rowlimit;
          EXIT WHEN clie_actu_oid.COUNT = 0;
          FORALL i IN clie_actu_oid.first .. clie_actu_oid.last
            INSERT INTO int_ivr_corpo_clien_actua (oid_clie) VALUES (clie_actu_oid(i));

          FORALL i IN clie_actu_oid.first .. clie_actu_oid.last
            DELETE int_ivr_corpo_clien WHERE oid_clie = clie_actu_oid(i);

        END LOOP;
      CLOSE clie_actu_cur;

    END IF;

    -- CARGAMOS LA TABLA TEMPORAL DE CLIENTES --
    dbms_stats.gather_table_stats(ownname => USER, tabname => 'INT_IVR_CORPO_CLIEN_ACTUA', cascade => TRUE);

    EXECUTE IMMEDIATE 'alter index INT_IVR_CLIEN_INTER_IDX unusable';

    EXECUTE IMMEDIATE 'TRUNCATE table INT_IVR_CLIEN_INTER';

    INSERT /*+ APPEND */ INTO INT_IVR_CLIEN_INTER(
        TIP_CLIE,
        COD_CLIE,
        OID_CLIE,
        NOM_CLIE,
        COD_REGI,
        COD_ZONA,
        COD_SECC
    )
    SELECT 'CL' tipocliente,
                     c.cod_clie cod_clie,
                     c.oid_clie oid_clie,
                     TRIM(c.val_nom1) || ' ' || TRIM(c.val_nom2) || ' ' || TRIM(c.val_ape1) || ' ' || TRIM(c.val_ape2) nombre,
                     gen_pkg_gener.gen_fn_clien_datos_oid(c.oid_clie, 'COD_REGI') codigoregion,
                     gen_pkg_gener.gen_fn_clien_datos_oid(c.oid_clie, 'COD_ZONA') codigozona,
                     gen_pkg_gener.gen_fn_clien_datos_oid(c.oid_clie, 'COD_SECC') codigoseccion
                FROM mae_clien c, mae_clien_tipo_subti t, int_ivr_corpo_clien_actua a
               WHERE a.oid_clie = c.oid_clie
                 AND c.oid_clie = t.clie_oid_clie
                 AND t.ticl_oid_tipo_clie = 2
                 AND NOT EXISTS (
                                 SELECT 1
                        FROM zon_zona z
                       WHERE z.ind_acti = '1'
                         AND z.ind_borr = '0'
                                    AND c.oid_clie = z.clie_oid_clie
                                )
                 AND NOT EXISTS (
                                 SELECT 1
                        FROM zon_regio r
                       WHERE r.ind_acti = '1'
                         AND r.ind_borr = '0'
                                    AND c.oid_clie = r.clie_oid_clie
                                )
                 AND NOT EXISTS (
                                 SELECT clie_oid_clie
                        FROM zon_secci s
                       WHERE s.ind_acti = '1'
                         AND s.ind_borr = '0'
                                    AND c.oid_clie = s.clie_oid_clie
                                )
              UNION ALL
              SELECT 'GR' tipocliente,
                     cr.cod_clie cod_clie,
                     cr.oid_clie oid_clie,
                     TRIM(cr.val_nom1) || ' ' || TRIM(cr.val_nom2) || ' ' || TRIM(cr.val_ape1) || ' ' ||
                     TRIM(cr.val_ape2) nombre,
                     r.cod_regi codigoregion,
                     gen_pkg_gener.gen_fn_clien_datos_oid(cr.oid_clie, 'COD_ZONA') codigozona,
                     gen_pkg_gener.gen_fn_clien_datos_oid(cr.oid_clie, 'COD_SECC') codigoseccion
                FROM zon_regio r, mae_clien cr, int_ivr_corpo_clien_actua a
               WHERE a.oid_clie = cr.oid_clie
                 AND cr.oid_clie = r.clie_oid_clie
                 AND r.ind_acti = '1'
                 AND r.ind_borr = '0'
              UNION ALL
              SELECT 'GZ' tipocliente,
                     cz.cod_clie cod_clie,
                     cz.oid_clie oid_clie,
                     TRIM(cz.val_nom1) || ' ' || TRIM(cz.val_nom2) || ' ' || TRIM(cz.val_ape1) || ' ' ||
                     TRIM(cz.val_ape2) nombre,
                     (SELECT cod_regi FROM zon_regio rz WHERE rz.oid_regi = z.zorg_oid_regi) codigoregion,
                     z.cod_zona codigozona,
                     gen_pkg_gener.gen_fn_clien_datos_oid(cz.oid_clie, 'COD_SECC') codigoseccion
                FROM zon_zona z, mae_clien cz, int_ivr_corpo_clien_actua a
               WHERE a.oid_clie = cz.oid_clie
                 AND cz.oid_clie = z.clie_oid_clie
                 AND z.ind_acti = '1'
                 AND z.ind_borr = '0'
              UNION ALL
              SELECT  'LD' tipocliente,
                     cs.cod_clie cod_clie,
                     cs.oid_clie oid_clie,
                     TRIM(cs.val_nom1) || ' ' || TRIM(cs.val_nom2) || ' ' || TRIM(cs.val_ape1) || ' ' ||
                     TRIM(cs.val_ape2) nombre,
                     (
                      SELECT cod_regi
                        FROM zon_regio rz, zon_zona z
                       WHERE rz.oid_regi = z.zorg_oid_regi
                         AND z.oid_zona = s.zzon_oid_zona
                     ) codigoregion,
                     (SELECT cod_zona FROM zon_zona zs WHERE zs.oid_zona = s.zzon_oid_zona) codigozona,
                     s.cod_secc
                FROM zon_secci s, mae_clien cs, int_ivr_corpo_clien_actua a
               WHERE a.oid_clie = cs.oid_clie
                 AND cs.oid_clie = s.clie_oid_clie
                 AND s.ind_acti = '1'
                 AND s.ind_borr = '0';

    EXECUTE IMMEDIATE 'alter index INT_IVR_CLIEN_INTER_IDX rebuild online';
    dbms_stats.gather_table_stats(ownname => USER, tabname => 'INT_IVR_CLIEN_INTER', cascade => TRUE);
    -- --

    -- DESABILITAMOS LOS INDICES DE LA TABLA int_ivr_corpo_clien
    EXECUTE IMMEDIATE 'alter index INT_IVR_CLIE_CLIE_IDX unusable';
    EXECUTE IMMEDIATE 'alter index INT_IVR_LOTE_CLIE_IDX unusable';
    EXECUTE IMMEDIATE 'alter index INT_IVR_OID_CLIE_IDX unusable';
    EXECUTE IMMEDIATE 'alter index INT_IVR_TIP_CLIE_IDX unusable';
    -- --

    INSERT /*+ APPEND */ INTO int_ivr_corpo_clien
      (
       cod_comp,
       tip_clie,
       cod_clie,
       val_nomb_clie,
       val_tele_fijo,
       val_tele_movi,
       cod_regi,
       cod_zona,
       cod_secc,
       val_sald_cart,
       ind_marc_situ,
       val_anio_ulti_pedi,
       num_camp_ulti_pedi,
       ind_club_priv,
       val_tota_clie_priv,
       ind_conc,
       ind_cdr,
       ind_bono,
       ind_esta_pedi,
       ind_dupl,
       des_bloq,
       cod_secc_trab,
       num_lote,
       fec_envi_nove,
       oid_clie,
       cod_peri_ulti_pedi,
       val_segm_clie,
       val_esta_clie,
       val_num_doc_iden,
       ind_sald_mora,
       camp_fact,
       fec_naci,
       ind_acti_pedi
      )
      SELECT pscodigocia,
             temp.tip_clie,
             temp.cod_clie,
             temp.nom_clie,
             TRIM(gen_pkg_gener.gen_fn_clien_texto_comun(temp.oid_clie, 'TF')),
             TRIM(gen_pkg_gener.gen_fn_clien_texto_comun(temp.oid_clie, 'TM')),
             temp.cod_regi,
             temp.cod_zona,
             temp.cod_secc,
             (
                NVL((SELECT SUM(mcc.imp_pend)
                FROM ccc_movim_cuent_corri mcc
                WHERE mcc.clie_oid_clie=temp.oid_clie
                AND mcc.imp_pend<>0), 0)
                -
               (  NVL((SELECT SUM(mb.imp_sald_pend)
                FROM ccc_movim_banca mb
                WHERE mb.clie_oid_clie=temp.oid_clie
                AND mb.cod_iden_proc='P'
                AND mb.imp_sald_pend<>0), 0)
                )
                -
                (  NVL((SELECT (cl.val_recl_pend)
                FROM mae_clien cl
                WHERE cl.oid_clie=temp.oid_clie), 0)
                )
             )  saldocartera,
             'N' marcasituacion,
             NULL val_anio_ulti_pedi,
             NULL num_camp_ulti_pedi,
             NULL indicadorprivilege,
             NULL val_tota_clie_priv,
             '0' indicadorconcurso,
             '0' indicadorcdr,
             '0' indbono,
             '0' ind_esta_pedi,
             '0' ind_dupl,
             NULL,
             decode(temp.tip_clie, 'LD', temp.cod_secc, 'CL', temp.cod_secc, NULL),
             psnumerolote,
             SYSDATE,
             oid_clie,
             (
                SELECT a.cod_peri
                FROM seg_perio_corpo a, cra_perio b
                WHERE a.oid_peri = b.peri_oid_peri
                and b.oid_peri = (
                    SELECT MAX(c.perd_oid_peri)
                    FROM ped_solic_cabec c
                    WHERE clie_oid_clie = temp.oid_clie
                    AND c.tspa_oid_tipo_soli_pais =
                       (SELECT tsp.oid_tipo_soli_pais
                          FROM ped_tipo_solic_pais tsp,
                               ped_tipo_solic ts
                         WHERE ts.oid_tipo_soli = tsp.tsol_oid_tipo_soli
                           AND ts.cod_tipo_soli = 'SOC'))
             ),
             '' segmentacion_cliente,
             CASE
               WHEN ma.esta_oid_esta_clie in (1, 2, 8) THEN 'N'
               WHEN ma.esta_oid_esta_clie in (3, 4, 5, 6) THEN 'A'
               ELSE 'R'
             END estatus,
             regexp_replace(mi.num_docu_iden,'[^0-9]',''),--elimina y/o reemplaza cualquier caracter no númerico
             (case when (
                NVL((SELECT SUM(mcc.imp_pend)
                FROM ccc_movim_cuent_corri mcc
                WHERE mcc.clie_oid_clie=temp.oid_clie
                AND mcc.imp_pend<>0), 0)
                -
               (  NVL((SELECT SUM(mb.imp_sald_pend)
                FROM ccc_movim_banca mb
                WHERE mb.clie_oid_clie=temp.oid_clie
                AND mb.cod_iden_proc='P'
                AND mb.imp_sald_pend<>0), 0)
                  )
             )
              > 0 THEN 'S' ELSE 'N' END) ind_sald_mora,
             CASE
               WHEN ((
                      SELECT COUNT(1)
                        FROM fac_contr_cierr a, fac_tipos_cierr b, zon_regio z
                       WHERE a.tcie_oid_tipo_cier = b.oid_tipo_cier
                         AND b.cod_tipo_cier = 'R'
                         AND a.zorg_oid_regi = z.oid_regi
                         AND z.cod_regi = temp.cod_regi
                         AND a.perd_oid_peri = pnidperiodo
                     ) > 0 OR
                     (
                      SELECT COUNT(1)
                        FROM ped_solic_cabec sc, ped_tipo_solic_pais tsp, ped_tipo_solic ts
                       WHERE sc.pais_oid_pais = pnidpais
                         AND sc.perd_oid_peri = pnidperiodo
                         AND sc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
                         AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                         AND sc.fec_fact IS NOT NULL
                         AND sc.clie_oid_clie = temp.oid_clie
                         AND sc.ind_ts_no_conso = 1
                         AND sc.ind_oc = 1
                         AND sc.ind_pedi_prue = 0
                         AND ts.ind_devo = 0
                         AND ts.ind_anul = 0
                     ) > 0) THEN PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(pscodigoperiodo, pnidpais, pnidmarca, pnidcanal, 1)
               ELSE
                pscodigoperiodo
             END cam_fact,
             ma.fec_naci,
             CASE
               WHEN ma.ind_acti = 1 THEN 'S'
               ELSE 'N'
             END ind_puede_pasar_pedido
        FROM INT_IVR_CLIEN_INTER temp,
             mae_clien_datos_adici ma,
             mae_clien_ident mi
       WHERE ma.clie_oid_clie = temp.oid_clie
         AND mi.clie_oid_clie(+) = temp.oid_clie
         AND mi.val_iden_docu_prin(+) = 1;

    -- HABILITAMOS LOS INDICES DE LA TABLA int_ivr_corpo_clien
    EXECUTE IMMEDIATE 'alter index INT_IVR_CLIE_CLIE_IDX rebuild online';
    EXECUTE IMMEDIATE 'alter index INT_IVR_LOTE_CLIE_IDX rebuild online';
    EXECUTE IMMEDIATE 'alter index INT_IVR_OID_CLIE_IDX rebuild online';
    EXECUTE IMMEDIATE 'alter index INT_IVR_TIP_CLIE_IDX rebuild online';
    -- --

    dbms_stats.gather_table_stats(ownname => USER, tabname => 'INT_IVR_CORPO_CLIEN', cascade => TRUE);

    /*ACTUALIZANDO INDICADOR CONCURSOS*/
    UPDATE int_ivr_corpo_clien c
       SET ind_conc = '1'
     WHERE EXISTS (SELECT 1 FROM int_ivr_corpo_concu a WHERE c.cod_clie = a.cod_clie)
       AND c.num_lote = psnumerolote;

    /*ACTUALIZANDO INDICADOR CDR*/
    UPDATE int_ivr_corpo_clien c
       SET ind_cdr = '1'
     WHERE EXISTS (SELECT 1 FROM int_ivr_corpo_servi_poven p WHERE c.cod_clie = p.cod_clie)
       AND c.num_lote = psnumerolote;

    /*ACTUALIZANDO INDICADOR BONO*/
    UPDATE int_ivr_corpo_clien c
       SET ind_bono = '1'
     WHERE EXISTS (SELECT 1 FROM int_ivr_corpo_bonos b WHERE c.cod_clie = b.cod_clie)
       AND c.tip_clie = 'LD'
       AND c.num_lote = psnumerolote;

    /*ACTUALIZANDO INDICADOR ESTADO DE PEDIDO*/
    UPDATE /*+PARALLEL(3)*/int_ivr_corpo_clien c
       SET ind_esta_pedi = '1'
     WHERE EXISTS (SELECT 1 FROM int_ivr_corpo_estad_pedid e WHERE c.cod_clie = e.cod_clie)
       AND c.num_lote = psnumerolote;

    /*ACTUALIZANDO INDICADOR DUPLA*/
    UPDATE int_ivr_corpo_clien c
       SET ind_dupl = '1'
     WHERE EXISTS (SELECT 1 FROM int_ivr_corpo_maest_ducyz md WHERE c.cod_clie = md.cod_clie)
       AND c.num_lote = psnumerolote;

    ---OBTENIENDO DOCUMENTO IDENTIDAD
    IF psindCambiarCodigo = 'S' THEN
      UPDATE /*+PARALLEL(3)*/int_ivr_corpo_clien c
         SET ( c.cod_clie
              ,c.val_num_doc_iden
              ) = ( SELECT mci.num_docu_iden
                          ,mci.num_docu_iden
                      FROM mae_clien mc
                          ,mae_clien_ident mci
                     WHERE mc.oid_clie = mci.clie_oid_clie
                       AND mci.val_iden_docu_prin = 1
                       AND mc.cod_clie = c.cod_clie
                   );
    END IF;

    --Eliminando posiciones de codigo consultora
    IF lsElimPosiCodigo > 0 THEN
      SELECT td.val_long INTO lnTamanio FROM mae_tipo_docum td WHERE td.cod_tipo_docu = '01';
      lsElimPosiCodigo := lnTamanio - lsElimPosiCodigo;
      UPDATE /*+PARALLEL(3)*/int_ivr_corpo_clien c
         SET c.cod_clie = SUBSTR(c.cod_clie,-lnTamanio,lsElimPosiCodigo);
    END IF;


    --Eliminando posiciones de Documento Identidad
    IF lsElimPosiDocIde > 0 THEN
      SELECT td.val_long INTO lnTamanioDocId FROM mae_tipo_docum td WHERE td.cod_tipo_docu = '01';
      lsElimPosiDocIde := lnTamanioDocId - lsElimPosiDocIde;
      UPDATE int_ivr_corpo_clien c
         SET c.val_num_doc_iden = SUBSTR(c. val_num_doc_iden,-lnTamanioDocId,lsElimPosiDocIde)
         WHERE length(c.val_num_doc_iden) =  lnTamanioDocId;
    END IF;

    -- ELiminando Letras de Documento Identidad
    IF lsElimLetrasDocIde = 1 THEN
        UPDATE int_ivr_corpo_clien c
           SET c.val_num_doc_iden = TRANSLATE(c.val_num_doc_iden , 'aABCDEFGHIJKLMNOPQRSTUVWXYZ', ' ');  
    END IF;

    --Parametro de Tipo Clasificación a enviar como TOP
    BEGIN
        SELECT b.val_pain INTO lsTipoClasificacion
          FROM bas_param_inter b
         WHERE b.inte_cod_inte = pscodigointerfaz AND
               b.nom_pain = 'tipoClasificacionTop' AND
               b.est_pain = 1;
      EXCEPTION
         WHEN NO_DATA_FOUND THEN
              lsTipoClasificacion := 0;
    END;

    --Parametro de Lista Clasificación a enviar como TOP
    BEGIN
        SELECT b.val_pain INTO lsListaClasificacion
          FROM bas_param_inter b
         WHERE b.inte_cod_inte = pscodigointerfaz AND
               b.nom_pain = 'clasificacionTop' AND
               b.est_pain = 1;
      EXCEPTION
         WHEN NO_DATA_FOUND THEN
              lsListaClasificacion := 0;
    END;



   /*ACTUALIZANDO CONSULTORAS TOP*/
   /*
   UPDATE /*+PARALLEL(3)*/ /*int_ivr_corpo_clien c
      SET c.val_segm_clie = 'T'
    WHERE EXISTS (
                  SELECT NULL
                    FROM mae_clien_tipo_subti mcts,
                         mae_clien_clasi mcc
                   WHERE mcts.oid_clie_tipo_subt = mcc.ctsu_oid_clie_tipo_subt
                     AND mcts.ticl_oid_tipo_clie = 2
                     AND mcts.sbti_oid_subt_clie = 1
                     AND TO_CHAR(mcc.tccl_oid_tipo_clasi) IN (
                                                              SELECT b.val_pain
                                                                FROM bas_param_inter b
                                                               WHERE b.inte_cod_inte = pscodigointerfaz AND
                                                                     b.nom_pain = 'tipoClasificacionTop' AND
                                                                     b.est_pain = 1
                                                             )
                     AND instr(lsListaClasificacion,mcc.clas_oid_clas) >0
                     AND mcts.clie_oid_clie = c.oid_clie
                  );
  */
  
    UPDATE /*+PARALLEL(3)*/int_ivr_corpo_clien c
      SET c.val_segm_clie = 'T'
    WHERE EXISTS (
                  SELECT NULL
                  FROM mae_clien_tipo_subti mcts,
                         mae_clien_clasi mcc
                         
                   WHERE mcts.oid_clie_tipo_subt = mcc.ctsu_oid_clie_tipo_subt
                     AND mcts.ticl_oid_tipo_clie = 2
                     AND mcts.sbti_oid_subt_clie = 1
                     
                     AND (SELECT COUNT(*) FROM MAE_CLASI cx
                          WHERE cx.tccl_oid_tipo_clas = mcc.tccl_oid_tipo_clasi
                          AND cx.OID_CLAS = mcc.clas_oid_clas
                          AND cx.IND_ACTI = 1 
                          AND cx.IND_IVR = 1) > 0
                          
                     AND mcts.clie_oid_clie = c.oid_clie
                  );


    OPEN c_marcasituasion;
      LOOP
        FETCH c_marcasituasion BULK COLLECT INTO v_oidclie, v_marcsitu, v_desbloq;

        IF v_oidclie.COUNT > 0 THEN
          FORALL i IN 1 .. v_oidclie.COUNT
            UPDATE int_ivr_corpo_clien
               SET ind_marc_situ = v_marcsitu(i),
                   des_bloq      = v_desbloq(i)
             WHERE oid_clie = v_oidclie(i)
               AND num_lote = psnumerolote;
        END IF;

        EXIT WHEN c_marcasituasion%NOTFOUND;
      END LOOP;
    CLOSE c_marcasituasion;

EXCEPTION
   WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM,1,1000);
    raise_application_error(-20123,'ERROR INT_PR_IVR_CORPO_GENER_CLIEN: ' || ls_sqlerrm);

END INT_PR_IVR_CORPO_GENER_CLIEN;

/***************************************************************************
Descripcion       : Genera Interfaz Enviar Bonos - IVR Corporativo
  Fecha Creacion    : 29/10/2009
  Autor             : Dennys Oliva Iriarte
***************************************************************************/
PROCEDURE INT_PR_IVR_CORPO_BONOS
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    psindproceso     VARCHAR2,
    psnumerolote     VARCHAR2,
    psnombrecontrol  VARCHAR2,
    indnuevaversion  VARCHAR2
  ) IS

    CURSOR c_temporal IS

      SELECT cc.cod_ejec,
             nvl(cc.val_moto_comi, 0) + nvl(cc.val_bono, 0),
             cc.cod_camp
        FROM com_comis_ejcal_cabec cc
       WHERE cc.cod_tipo_comi = '03'
         AND EXISTS  (
                      SELECT zs.clie_oid_clie
                        FROM zon_secci zs,
                             mae_clien mae
                       WHERE zs.ind_acti = '1'
                         AND zs.ind_borr = '0'
                         AND zs.clie_oid_clie = mae.oid_clie
                         AND mae.cod_clie = cc.cod_ejec
                     )
       ORDER BY cc.cod_ejec, cc.cod_camp DESC;

    TYPE t_cod_ejec IS TABLE OF com_comis_ejcal_cabec.cod_ejec%TYPE;
    TYPE t_val_moto_comi IS TABLE OF com_comis_ejcal_cabec.val_moto_comi%TYPE;
    TYPE t_cod_camp IS TABLE OF com_comis_ejcal_cabec.cod_camp%TYPE;

    v_cod_ejec      t_cod_ejec;
    v_val_moto_comi t_val_moto_comi;
    v_cod_camp      t_cod_camp;

    CURSOR c_interfaz(vnmaxcarcodclie NUMBER) IS

      SELECT pscodigopais,
             cod_comp,
             cod_tipo_clie,
             lpad(cod_clie,vnmaxcarcodclie, '0'),
             TRIM(to_char(val_bono, '9999999990.99')),
             val_anio_camp,
             val_nume_camp
        FROM int_ivr_corpo_bonos;

    TYPE interfazrec IS RECORD
    (
      codigopais         bas_pais.cod_pais%TYPE,
      codigocia          int_ivr_corpo_bonos.cod_comp%TYPE,
      codigotipocliente  int_ivr_corpo_bonos.cod_tipo_clie%TYPE,
      codigocliente      int_ivr_corpo_bonos.cod_clie%TYPE,
      valorbono          VARCHAR2(14),
      valoraniocampana   int_ivr_corpo_bonos.val_anio_camp%TYPE,
      valornumerocampana int_ivr_corpo_bonos.val_nume_camp%TYPE
    );

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;

    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         NUMBER := 1000;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);

    lscodigocia     int_ivr_corpo_bonos.cod_comp%TYPE;
    lsauxcodclie    int_ivr_corpo_bonos.cod_clie%TYPE := ' ';
    lstipocliente   int_ivr_corpo_bonos.cod_tipo_clie%TYPE := 'LD';
    lnnumregistros  NUMBER := 0;
    lnmaxcarcodclie NUMBER := 15;
    lbabrirutlfile  BOOLEAN;
    rows            NATURAL := 1000; -- Number of rows to process at a time
    lsvalpain bas_param_inter.val_pain%TYPE;

    --parametro para eliminar posiciones de codigo cliente
    lsElimPosiCodigo bas_param_pais.val_para%TYPE;
    lnTamanio NUMBER;

BEGIN

    /* Obtiene el codigo de Compañia */
    SELECT int_ivr_param_compa.cod_comp
      INTO lscodigocia
      FROM int_ivr_param_compa
     WHERE int_ivr_param_compa.cod_pais = pscodigopais;

    --variable para cambiar el codigo de cliente por numero de documento
    SELECT nvl((select b.val_pain
                  from bas_param_inter b
                 where b.inte_cod_inte = pscodigointerfaz
                   and b.cod_pain = '04'
               ), 'N') INTO lsvalpain FROM dual;


    --parametros de interface
    --variable para eliminar ultimas posiciones de codigo Consultora
    BEGIN
        --variable para cambiar el codigo de cliente por numero de documento
        SELECT b.val_pain INTO lsElimPosiCodigo
          FROM bas_param_inter b
         WHERE b.inte_cod_inte = pscodigointerfaz AND
               b.nom_pain = 'indElimPosiCod' AND
               b.est_pain = 1;
      EXCEPTION
         WHEN NO_DATA_FOUND THEN
              lsElimPosiCodigo := 0;
    END;


    EXECUTE IMMEDIATE 'TRUNCATE TABLE INT_IVR_CORPO_BONOS';

    OPEN c_temporal;
        LOOP
          FETCH c_temporal BULK COLLECT
            INTO v_cod_ejec,
                 v_val_moto_comi,
                 v_cod_camp LIMIT rows;
          IF v_cod_ejec.COUNT > 0 THEN
            FOR j IN v_cod_ejec.first .. v_cod_ejec.last
              LOOP
                IF lsauxcodclie != v_cod_ejec(j) THEN
                  lsauxcodclie := v_cod_ejec(j);
                  -- SOLO INSERTA EN CASO DE QUIEBRE DE CLIENTE
                  INSERT INTO int_ivr_corpo_bonos
                    (cod_comp,
                     cod_tipo_clie,
                     cod_clie,
                     val_bono,
                     val_anio_camp,
                     val_nume_camp)
                  VALUES
                    (lscodigocia,
                     lstipocliente,
                     v_cod_ejec(j),
                     v_val_moto_comi(j),
                     substr(v_cod_camp(j),1,4),
                     substr(v_cod_camp(j),5,2));
                END IF;
              END LOOP;
          END IF;
          EXIT WHEN c_temporal%NOTFOUND;
        END LOOP;
    CLOSE c_temporal;

    ---SI LA INTERFAZ REQUIERE EL CAMBION DE COD CLIENTE POR DOCUMENTO IDENTIDAD
    IF lsvalpain = 'S' THEN
      UPDATE int_ivr_corpo_bonos c
         SET c.cod_clie = ( SELECT mci.num_docu_iden
                              FROM mae_clien mc
                                  ,mae_clien_ident mci
                             WHERE mc.oid_clie = mci.clie_oid_clie
                               AND mci.val_iden_docu_prin = 1
                               AND mc.cod_clie = c.cod_clie
                           );
    END IF;

    --Eliminando posiciones de codigo consultora
    IF lsElimPosiCodigo > 0 THEN
      SELECT td.val_long INTO lnTamanio FROM mae_tipo_docum td WHERE td.cod_tipo_docu = '01';
      lsElimPosiCodigo := lnTamanio - lsElimPosiCodigo;
      UPDATE int_ivr_corpo_bonos c
         SET c.cod_clie = SUBSTR(c.cod_clie,-lnTamanio,lsElimPosiCodigo);
    END IF;


    lnnumregistros := 0;
    lbabrirutlfile := TRUE;

    /* Generando Archivo de Texto (Detalle) */
    OPEN c_interfaz(lnmaxcarcodclie);
      LOOP
        FETCH c_interfaz BULK COLLECT INTO interfazrecord LIMIT w_filas;

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
          FOR x IN interfazrecord.first .. interfazrecord.last
            LOOP
              lslinea :='';
              IF indnuevaversion = 'S' THEN
                lslinea := interfazrecord(x).codigopais || ';';
              END IF;
              lslinea := lslinea ||
                         interfazrecord(x).codigocia           || ';' ||
                         interfazrecord(x).codigotipocliente   || ';' ||
                         interfazrecord(x).codigocliente       || ';' ||
                         interfazrecord(x).valorbono           || ';' ||
                         interfazrecord(x).valoraniocampana    || ';' ||
                         interfazrecord(x).valornumerocampana;

              utl_file.put_line(v_handle,
                                lslinea);
              lnnumregistros := lnnumregistros + 1;
            END LOOP;
        END IF;
        EXIT WHEN c_interfaz%NOTFOUND;
      END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
       utl_file.fclose(v_handle);
    END IF;

    INT_PR_IVR_CORPO_REGIS_CNTRL(lscodigocia,
                                 psnombrecontrol,
                                 lnnumregistros,
                                 pscodigoperiodo);

    IF NOT lbabrirutlfile THEN
       /* Procedimiento final para generar interfaz */
       gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR', lsdirtempo, psnombrearchivo, lsnombrearchivo);
    END IF;

    RETURN;

EXCEPTION
   WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,'ERROR INT_PR_IVR_CORPO_BONOS: ' || ls_sqlerrm);

END INT_PR_IVR_CORPO_BONOS;


 /***************************************************************************
  Descripcion       : Genera Interfaz Enviar Tabla de Bonos
  Fecha Creacion    : 12/04/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_ivr_actua_fecha_venci
  (
    pscodpais        VARCHAR2,
    pscodmarca       VARCHAR2,
    pscodcanal       VARCHAR2,
    pnoidpais        NUMBER,
    pnoidmarca       NUMBER,
    pnoidcanal       NUMBER,
    pnoidperiodo     NUMBER,
    pscodperiodo     VARCHAR2,
    pscodperiodosgte VARCHAR2,
    psnumerolote     VARCHAR2
  ) IS
    CURSOR c_clientespedido(vsactividad VARCHAR2) IS
      SELECT c.oid_clie,
             gen_pkg_gener.gen_fn_recup_fecha_activ(pnoidpais,
                                                    pnoidmarca,
                                                    pnoidcanal,
                                                    c.cod_zona,
                                                    pscodperiodosgte,
                                                    vsactividad) fechavenc
        FROM int_ivr_corpo_clien c,
             ped_solic_cabec     p
       WHERE num_lote = psnumerolote
         AND p.clie_oid_clie = c.oid_clie
         AND p.perd_oid_peri              = pnoidperiodo
         AND p.ind_oc = 1
         AND p.fec_fact is not null
         AND p.grpr_oid_grup_proc = 5;

    TYPE t_oidclie IS TABLE OF int_ivr_corpo_clien.oid_clie%TYPE;
    TYPE t_fecvenc IS TABLE OF int_ivr_corpo_clien.fec_venc%TYPE;

    v_oidclie t_oidclie;
    v_fecvenc t_fecvenc;

    CURSOR c_regionzona IS
      SELECT cod_regi,
             cod_zona,
             gen_pkg_gener.gen_fn_obtie_fecha_venci_rezon(pscodpais,
                                                          pscodmarca,
                                                          pscodcanal,
                                                          pscodperiodo,
                                                          cod_regi,
                                                          cod_zona),
             -- Calcula la fecha de entrega
             gen_pkg_gener.gen_fn_obtie_fecha_entre_rezon(pscodpais,
                                                          pscodmarca,
                                                          pscodcanal,
                                                          pscodperiodo,
                                                          cod_regi,
                                                          cod_zona)

        FROM (SELECT DISTINCT cod_regi,
                              cod_zona
                FROM int_ivr_corpo_clien c
               WHERE c.ind_pedi = '0'
                 AND num_lote = psnumerolote) a;

    TYPE t_codregi IS TABLE OF int_ivr_corpo_clien.cod_regi%TYPE;
    TYPE t_codzona IS TABLE OF int_ivr_corpo_clien.cod_zona%TYPE;

    TYPE t_fecentre IS TABLE OF int_ivr_corpo_clien.fec_entr_pedi%TYPE;

    v_codregi t_codregi;
    v_codzona t_codzona;

    v_fecentre t_fecentre;

    v_oid_acti        cra_activ.oid_acti%TYPE;
    v_oid_periodo_sig cra_crono.perd_oid_peri%TYPE;
    lnidpais          NUMBER;
    lsactividad       cra_activ.cod_acti%TYPE;

  BEGIN

    lnidpais := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodpais);

    -- Se obtiene el oid de actividad
    BEGIN
      SELECT ca.oid_acti
        INTO v_oid_acti
        FROM cra_activ ca
       WHERE ca.cod_acti =
             (SELECT p.cod_oper FROM int_ivr_corpo_param_poven p WHERE p.cod_acce = 'REP')
         AND ca.pais_oid_pais = lnidpais;
    EXCEPTION
      WHEN no_data_found THEN
        v_oid_acti := 0;
    END;

    -- Se calcula el oid de periodo siguiente al actual
    v_oid_periodo_sig := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(gen_fn_calcu_perio(pscodperiodo,
                                                                                        1));
     -- Se obtiene el codigo actividad Fecha Vencimiento
    BEGIN
        SELECT p.cod_oper INTO lsactividad FROM int_ivr_corpo_param_poven p WHERE p.cod_acce = 'FVP';
    EXCEPTION
      WHEN no_data_found THEN
        lsactividad := 'CV';
    END;

    OPEN c_clientespedido(lsactividad);
    LOOP
      FETCH c_clientespedido BULK COLLECT
        INTO v_oidclie,
             v_fecvenc;

      IF v_oidclie.count > 0 THEN

        -- Setea el indicador de todas las consultoras que pasaron pedido
        -- y se calcula la fecha de entrega de pedido
        FORALL i IN 1 .. v_oidclie.count
          UPDATE int_ivr_corpo_clien tmp
             SET tmp.ind_pedi      = '1',
                 tmp.fec_venc      = v_fecvenc(i),
                 tmp.fec_entr_pedi =
                 (SELECT cc.fec_inic
                    FROM cra_crono cc,
                         zon_zona  zz
                   WHERE zz.oid_zona = cc.zzon_oid_zona
                     AND zz.cod_zona = tmp.cod_zona
                     AND cc.cact_oid_acti = v_oid_acti
                     AND cc.perd_oid_peri = v_oid_periodo_sig)
           WHERE tmp.oid_clie = v_oidclie(i)
             AND tmp.num_lote = psnumerolote;

      END IF;
      EXIT WHEN c_clientespedido%NOTFOUND;
    END LOOP;
    CLOSE c_clientespedido;

 /*   OPEN c_regionzona;
    LOOP
      FETCH c_regionzona BULK COLLECT
        INTO v_codregi,
             v_codzona,
             v_fecvenc,
             v_fecentre;

      IF v_codregi.count > 0 THEN
        FORALL i IN 1 .. v_codregi.count
          UPDATE int_ivr_corpo_clien
             SET fec_venc      = v_fecvenc(i),
                 fec_entr_pedi = v_fecentre(i)
           WHERE cod_regi = v_codregi(i)
             AND cod_zona = v_codzona(i)
             AND num_lote = psnumerolote
             AND ind_pedi = '0';

      END IF;
      EXIT WHEN c_regionzona%NOTFOUND;
    END LOOP;
    CLOSE c_regionzona;*/

    --se actualiza la fecha de vencimiento de los que no tienen pedido
       UPDATE int_ivr_corpo_clien iicc
             SET iicc.fec_venc      = gen_pkg_gener.gen_fn_recup_fecha_activ(
                                      pnoidpais,
                                      pnoidmarca,
                                      pnoidcanal,
                                      iicc.cod_zona,
                                      per_pkg_repor_perce.per_fn_obtie_perio(
                                           iicc.cod_peri_ulti_pedi,
                                           pnoidpais,
                                           pnoidmarca,
                                           pnoidcanal,
                                           1),
                                      lsactividad)
           WHERE iicc.num_lote = psnumerolote
             AND iicc.ind_pedi = '0';

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR int_IVR_actua_fecha_venci: ' || ls_sqlerrm);

  END int_ivr_actua_fecha_venci;

  /***************************************************************************
  Descripcion       : Genera Interfaz Enviar Tabla de Cronograma de actividades
  Fecha Creacion    : 12/10/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
PROCEDURE INT_PR_IVR_CORPO_CRONO
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    psindproceso     VARCHAR2,
    psnumerolote     VARCHAR2,
    psnombrecontrol  VARCHAR2,
    psmarca          VARCHAR2,
    pscanal          VARCHAR2,
    indnuevaversion  VARCHAR2
  ) IS

    CURSOR c_interfaz IS

      SELECT pscodigopais,
             cod_comp,
             cod_regi,
             cod_zona,
             cod_acti,
             fec_acti
        FROM int_ivr_corpo_crono;

    TYPE interfazrec IS RECORD(
      codigopais      bas_pais.cod_pais%TYPE,
      codigocia       int_ivr_corpo_crono.cod_comp%TYPE,
      codigoregion    int_ivr_corpo_crono.cod_regi%TYPE,
      codigozona      int_ivr_corpo_crono.cod_zona%TYPE,
      codigoactividad int_ivr_corpo_crono.cod_acti%TYPE,
      fechaactividad  int_ivr_corpo_crono.fec_acti%TYPE);

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         NUMBER := 1000;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);

    lscodigocia int_ivr_corpo_crono.cod_comp%TYPE;

    lnidpais       seg_pais.oid_pais%TYPE;
    lnnumregistros NUMBER := 0;
    lbabrirutlfile BOOLEAN;

  BEGIN

    /* Obtiene el oid del pais*/
    lnidpais := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);

    /* Obtiene el codigo de Compañia */
    SELECT int_ivr_param_compa.cod_comp
      INTO lscodigocia
      FROM int_ivr_param_compa
     WHERE int_ivr_param_compa.cod_pais = pscodigopais;

    EXECUTE IMMEDIATE 'TRUNCATE TABLE int_ivr_corpo_crono';

    INSERT INTO int_ivr_corpo_crono
      (
       cod_comp,
       cod_regi,
       cod_zona,
       cod_acti,
       fec_acti
      )
      (
       SELECT lscodigocia,
              zr.cod_regi,
              zz.cod_zona,
              p.cod_oper_homo,
              to_char(gen_pkg_gener.gen_fn_obtie_fecha_entre_rezon(pscodigopais,
                                                                   psmarca,
                                                                   pscanal,
                                                                   pscodigoperiodo,
                                                                   zr.cod_regi,
                                                                   zz.cod_zona),
                      'YYYYMMDD')
         FROM zon_regio                 zr,
              zon_zona                  zz,
              cra_activ                 ca,
              int_ivr_corpo_param_poven p
        WHERE zr.ind_acti = 1
          AND zr.ind_borr = 0
          AND zz.zorg_oid_regi = zr.oid_regi
          AND ca.cod_acti = p.cod_oper
          AND p.cod_acce = 'CRA'
          AND ca.pais_oid_pais = lnidpais);

    lnnumregistros := 0;
    lbabrirutlfile := TRUE;

    /* Generando Archivo de Texto (Detalle) */
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
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea :='';
          IF indnuevaversion = 'S' THEN
            lslinea := interfazrecord(x).codigopais || ';';
          END IF;
          lslinea := lslinea ||
                     interfazrecord(x).codigocia || ';' ||
                     interfazrecord(x).codigoregion || ';' ||
                     interfazrecord(x).codigozona || ';' ||
                     interfazrecord(x).codigoactividad;
          IF indnuevaversion = 'S' THEN
            lslinea := lslinea || ';' ||
                     substr(interfazrecord(x).fechaactividad,1,4) || ';' ||
                     substr(interfazrecord(x).fechaactividad,5,2) || ';' ||
                     substr(interfazrecord(x).fechaactividad,7,2);
          ELSE
            lslinea := lslinea || ';' ||
                     interfazrecord(x).fechaactividad;
          END IF;



          utl_file.put_line(v_handle,
                            lslinea);
          lnnumregistros := lnnumregistros + 1;
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
    END IF;

    INT_PR_IVR_CORPO_REGIS_CNTRL(lscodigocia,
                                 psnombrecontrol,
                                 lnnumregistros,
                                 pscodigoperiodo);

    IF NOT lbabrirutlfile THEN
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR', lsdirtempo, psnombrearchivo, lsnombrearchivo);
    END IF;

    RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123, 'ERROR INT_PR_IVR_CORPO_CRONO: ' || ls_sqlerrm);

END INT_PR_IVR_CORPO_CRONO;


    /***************************************************************************
  Descripcion       : Genera Interfaz Enviar Matriz de Facturación de IVR dependiendo
                      de psIndProceso:
                      Si psIndProceso='T' se borran los datos de la tabla
                      int_ivr_matri y se cargan todos los datos
                      Si psIndProceso='N' se cargan solo los datos modificados
  Fecha Creacion    : 10/10/2011
  Autor             : Dany Romero
 ****************************************************************************/

PROCEDURE INT_PR_IVR_CORPO_MATRI
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigomarca     VARCHAR2,
    pscodigocanal     VARCHAR2,
    pscodigoperiodo   VARCHAR2,
    psindproceso      VARCHAR2,
    psnumerolote     VARCHAR2,
    psnombrecontrol  VARCHAR2
  ) IS

    CURSOR c_interfaz IS

    SELECT pscodigopais,
           cod_comp,
           cam_proc,
           val_codi_vent,
           ind_inve,
           num_maxi_vent,
           fec_acum
    FROM int_ivr_corpo_matri;

    TYPE interfazrec IS RECORD(
      codigopais        bas_pais.cod_pais%TYPE,
      codigocia         int_ivr_corpo_matri.cod_comp%TYPE,
      campanaProceso    int_ivr_corpo_matri.cam_proc%TYPE,
      codigoVenta       int_ivr_corpo_matri.val_codi_vent%TYPE,
      indInventario     int_ivr_corpo_matri.ind_inve%TYPE,
      ctdadMaxVenta     int_ivr_corpo_matri.num_maxi_vent%TYPE,
      fechaProceso      int_ivr_corpo_matri.fec_acum%TYPE
      );

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;

    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 10000;	--c1
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);

    lnidpais          NUMBER;
    lnidcanal         NUMBER;
    lnidmarca         NUMBER;
    lnidperiodo       NUMBER;
    lnidperiodoSig    NUMBER;
    lscodigocia       int_ivr_param_compa.cod_comp%TYPE;
    lnnumregistros    NUMBER := 0;
    ldfechaproceso    DATE;
    lbabrirutlfile    BOOLEAN;
    lsPeriodosig      seg_perio_corpo.cod_peri%TYPE;

  BEGIN

    /* Obtiene el codigo de Compañia */
    SELECT cod_comp
      INTO lscodigocia
      FROM int_ivr_param_compa
     WHERE int_ivr_param_compa.cod_pais = pscodigopais;

    /* obteniendo id's */
    lnidpais    := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);
    lnidmarca   := gen_pkg_gener.gen_fn_devuelve_id_marca(pscodigomarca);
    lnidcanal   := gen_pkg_gener.gen_fn_devuelve_id_canal(pscodigocanal);
    lnidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoperiodo);
    lnidperiodoSig := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(
                                        per_pkg_repor_perce.per_fn_obtie_perio(pscodigoperiodo,
                                                                                lnidpais,
                                                                                lnidmarca,
                                                                                lnidcanal,
                                                                                1));
    lsPeriodosig :=gen_pkg_gener.gen_fn_perio_nsigu(pscodigopais,pscodigoperiodo,1);

    -- Obtiene Fecha de Sistema --
    SELECT SYSDATE
      INTO ldfechaproceso
      FROM dual;

    IF psindproceso = 'T' THEN

      gen_pkg_gener.gen_pr_trunc_table('int_ivr_corpo_matri');

    ELSE
     DELETE FROM int_ivr_corpo_matri ivr
      WHERE EXISTS
      (
       SELECT 1
         FROM (
               SELECT cod_peri codigocampana,
                val_codi_vent codigoventa,
                CASE
                  WHEN val_limi_ctrl_vent IS NULL AND val_unid IS NULL THEN 'E'
                  WHEN val_limi_ctrl_vent = 0 THEN 'F'
                  WHEN val_unid = 0 THEN 'A'
                  WHEN (val_limi_ctrl_vent IS NOT NULL AND val_limi_ctrl_vent <> 0) OR
                       (val_unid IS NOT NULL AND val_unid <> 0) THEN 'L'
                  ELSE 'E'
                END indicador_inventario,
                nvl(val_limi_ctrl_vent, 0) + nvl(val_unid, 0) cantidad_maxima
                 FROM (
                       SELECT e.cod_peri,
                        b.val_codi_vent,
                              (
                               SELECT MIN(val_limi_ctrl_vent)
                           FROM ped_gesti_stock
                                WHERE ofde_oid_deta_ofer = b.oid_deta_ofer
                              ) val_limi_ctrl_vent,
                        (SELECT MIN(val_unid) FROM ped_gesti_stock WHERE ofde_oid_deta_ofer = b.oid_deta_ofer) val_unid
                   FROM pre_ofert a, pre_ofert_detal b, pre_matri_factu_cabec c, cra_perio d, seg_perio_corpo e
                  WHERE a.oid_ofer = b.ofer_oid_ofer
                    AND a.mfca_oid_cabe = c.oid_cabe
                    AND c.perd_oid_peri = d.oid_peri
                    AND d.peri_oid_peri = e.oid_peri
                          AND d.oid_peri in (lnidperiodo, lnidperiodosig)
                      )
              ) mat,
        int_ivr_corpo_matri ivrm
      WHERE ivrm.cam_proc = mat.codigocampana
        AND ivrm.val_codi_vent = mat.codigoventa
        AND (ivrm.ind_inve <> mat.indicador_inventario
               OR ivrm.num_maxi_vent <> mat.cantidad_maxima)
      );
    END IF;

    INSERT INTO int_ivr_corpo_matri
      (cod_comp, cam_proc, val_codi_vent, ind_inve, num_maxi_vent, fec_acum, num_lote)
      SELECT lscodigocia codigocia,
             mat.codigocampana,
             mat.codigoventa,
             mat.indicador_inventario,
             mat.cantidad_maxima,
             ldfechaproceso,
             psnumerolote
        FROM (
              SELECT cod_peri codigocampana,
                     val_codi_vent codigoventa,
                     CASE
                        WHEN val_limi_ctrl_vent IS NULL AND val_unid IS NULL THEN 'E'
                        WHEN val_limi_ctrl_vent = 0 THEN 'F'
                        WHEN val_unid = 0 THEN 'A'
                        WHEN (val_limi_ctrl_vent IS NOT NULL AND val_limi_ctrl_vent <> 0) OR
                             (val_unid IS NOT NULL AND val_unid <> 0) THEN 'L'
                        ELSE 'E'
                     END indicador_inventario,
                     nvl(val_limi_ctrl_vent, 0) + nvl(val_unid, 0) cantidad_maxima
                FROM (
                      SELECT e.cod_peri,
                             b.val_codi_vent,
                             (SELECT MIN(val_limi_ctrl_vent) FROM ped_gesti_stock WHERE ofde_oid_deta_ofer = b.oid_deta_ofer) val_limi_ctrl_vent,
                             (SELECT MIN(val_unid) FROM ped_gesti_stock WHERE ofde_oid_deta_ofer = b.oid_deta_ofer) val_unid
                        FROM pre_ofert a, pre_ofert_detal b, pre_matri_factu_cabec c, cra_perio d, seg_perio_corpo e
                       WHERE a.oid_ofer = b.ofer_oid_ofer
                         AND a.mfca_oid_cabe = c.oid_cabe
                         AND c.perd_oid_peri = d.oid_peri
                         AND d.peri_oid_peri = e.oid_peri
                         AND d.oid_peri in (lnidperiodo, lnidperiodosig)
                     )
             ) mat
       WHERE psindproceso = 'T'
          OR NOT EXISTS(
                        SELECT 1
                FROM int_ivr_corpo_matri ivrm
               WHERE ivrm.cam_proc = mat.codigocampana
                           AND ivrm.val_codi_vent = mat.codigoventa
                       );

    INSERT INTO int_ivr_corpo_matri
      (cod_comp, cam_proc, val_codi_vent, ind_inve, num_maxi_vent, fec_acum, num_lote)
      SELECT lscodigocia codigocia,
             mat.codigocampana,
             mat.codigoventa,
             mat.indicador_inventario,
             mat.cantidad_maxima,
             ldfechaproceso,
             psnumerolote
        FROM (
              SELECT pais.cod_pais,
                     perio.cod_peri codigocampana,
                     a.cod_vent_fict codigoventa,
                     'E' indicador_inventario,
                     ( SELECT ptsp.num_unid_alar
                         FROM ped_tipo_solic_pais ptsp,
                              ped_tipo_solic pts
                        WHERE ptsp.pais_oid_pais      = pais.oid_pais AND
                              ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli AND
                              pts.cod_tipo_soli       = 'SOC'
                     ) as cantidad_maxima
              FROM inc_artic_lote a,
                   inc_lote_premi_artic b,
                   inc_premi_artic c,
                   inc_param_nivel_premi d,
                   inc_param_gener_premi e,
                   inc_concu_param_gener f,
                   SEG_PAIS pais,
                   mae_produ prod,
                   seg_marca_produ smp,
                   (SELECT a.cod_peri, b.oid_peri
                      FROM seg_perio_corpo a,
                           cra_perio       b
                     WHERE (a.cod_peri >= pscodigoperiodo AND a.cod_peri <= lsPeriodosig)
                       AND a.oid_peri = b.peri_oid_peri) perio
             WHERE a.lopa_oid_lote_prem_arti = b.oid_lote_prem_arti AND
                   a.prod_oid_prod           = prod.oid_prod AND
                   prod.pais_oid_pais        = pais.oid_pais AND
                   prod.mapr_oid_marc_prod   = smp.oid_marc_prod AND
                   b.prar_oid_prem_arti      = c.oid_prem_arti AND
                   c.panp_oid_para_nive_prem = d.oid_para_nive_prem AND
                   d.pagp_oid_para_gene_prem = e.oid_para_gene_prem AND
                   e.copa_oid_para_gral      = f.oid_para_gral AND
                   f.ind_acti                = 1 AND
                   ( f.bcal_oid_base_calc      in (1,2) OR (f.bcal_oid_base_calc = 4 and e.tprm_oid_tipo_pion = 1 )) AND
                   length(a.cod_vent_fict)   = 5 AND
                   e.ind_prem_elec           = 1 AND
                   f.perd_oid_peri_desd     <= perio.oid_peri and
                   e.perd_oid_peri          >= perio.oid_peri
                   ) mat
       WHERE psindproceso = 'T'
          OR NOT EXISTS(
                        SELECT 1
                FROM int_ivr_corpo_matri ivrm
               WHERE ivrm.cam_proc = mat.codigocampana
                           AND ivrm.val_codi_vent = mat.codigoventa
                       );


    gen_pkg_gener.gen_pr_gener_stats('int_ivr_corpo_matri',USER);

    lnnumregistros := 0;
    lbabrirutlfile := TRUE;

    /* Generando Archivo de Texto (Detalle) */
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
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x).codigopais                 || ';' ||
                     interfazrecord(x).codigocia                  || ';' ||
                     substr(interfazrecord(x).campanaProceso,1,4) || ';' ||
                     substr(interfazrecord(x).campanaProceso,5,2) || ';' ||
                     interfazrecord(x).codigoVenta                || ';' ||
                     interfazrecord(x).indInventario              || ';' ||
                     interfazrecord(x).ctdadMaxVenta;

          utl_file.put_line(v_handle, lslinea);
          lnnumregistros := lnnumregistros + 1;
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
    END IF;

    int_pr_ivr_corpo_regis_cntrl(lscodigocia,
                                 psnombrecontrol,
                                 lnnumregistros,
                                 pscodigoperiodo);

    IF NOT lbabrirutlfile THEN
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR', lsdirtempo, psnombrearchivo, lsnombrearchivo);
    END IF;

    RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123, 'ERROR INT_PR_IVR_CORPO_MATRI: ' || ls_sqlerrm);

END INT_PR_IVR_CORPO_MATRI;


/*******************************************************************************
  Descripcion       : Interfaz que Recepciona Pedidos  -  IVR Corporativo
                      inserta en las tablas INT_SOLIC_CABEC e INT_SOLIC_POSIC
                      los pedidos que lleguan de la interface de IVR-60
  Fecha Creacion    : 15/11/2011
  Autor             : Jose Luis Rodriguez
********************************************************************************/
PROCEDURE INT_PR_IVR_RECEP_PEDID
(
    pscodigoPais      VARCHAR2,
    pscodigoInterfaz  VARCHAR2,
    pscodigoTipoDocu  VARCHAR2,
    pscodigousuario   VARCHAR2,
    psindicadorOrigen VARCHAR2,
    psnumeroLoteSTO   VARCHAR2
)
IS

  CURSOR c_pedido(lonCodigo bas_pais.lon_codi_clie%TYPE) IS

    SELECT pscodigoPais          CodPais,
           cod_comp              CodCompania,
           substr(cod_clie,-lonCodigo,length(cod_clie)) CodCliente,
           anh_camp || cod_camp  CodPeriodo,
           cod_vent              CodVenta,
           to_number(val_unid)   Unidades,
           num_lote_sto          NumLoteSTO
      FROM ivr_tmp_recep_pedid a
     WHERE a.num_lote_sto = psnumeroLoteSTO
  ORDER BY cod_clie;

  TYPE interfazrec IS RECORD(
    codPais         int_solic_cabec.cod_pais%TYPE,
    codCompania     int_solic_cabec.cod_comp%TYPE,
    codCliente      int_solic_cabec.cod_clie%TYPE,
    codPeriodo      int_solic_cabec.cam_soli%TYPE,
    codVenta        int_solic_posic.cod_vent%TYPE,
    unidades        int_solic_posic.uni_dema%TYPE,
    numLoteSTO      int_solic_cabec.num_lote_sto%TYPE
  );

  TYPE interfazrectab IS TABLE OF interfazrec;
  interfazrecord interfazrectab;

  vsNumLote     bas_ctrl_fact.num_lote%TYPE;
  vsCodRegion   zon_regio.cod_regi%TYPE;
  vsCodZona     zon_zona.cod_zona%TYPE;
  vsCodClieAnt  int_solic_cabec.cod_clie%TYPE;
  lsLonCodClie  bas_pais.lon_codi_clie%TYPE;

BEGIN

    -- Obteniendo el numero de lote --
  SELECT a.num_lote
    INTO vsNumLote
    FROM bas_ctrl_fact a
   WHERE a.cod_pais = pscodigoPais
     AND a.sta_camp = 0
     AND a.ind_camp_act = 1;

     --Obteniendo el Tamaño de Código de Cliente
  BEGIN
       SELECT bp.lon_codi_clie
         INTO lsLonCodClie
         FROM bas_pais bp
        WHERE bp.cod_pais = pscodigopais
          AND bp.est_pais = '1';
       EXCEPTION
         WHEN NO_DATA_FOUND
         THEN lsLonCodClie := '15';
  END;

  vsCodClieAnt := NULL;

  OPEN c_pedido(lsLonCodClie);
    LOOP

      FETCH c_pedido BULK COLLECT
      INTO interfazrecord LIMIT w_filas;

        IF interfazrecord.COUNT > 0 THEN
          FOR x IN interfazrecord.first .. interfazrecord.LAST LOOP

            IF (vsCodClieAnt IS NULL OR interfazrecord(x).codCliente <> vsCodClieAnt) THEN
              --Obteniedo el codigo de region y zona del cliente
              vsCodRegion :=  gen_pkg_gener.gen_fn_clien_datos_codig(interfazrecord(x).codCliente, 'COD_REGI');
              vsCodZona   :=  gen_pkg_gener.gen_fn_clien_datos_codig(interfazrecord(x).codCliente, 'COD_ZONA');

              --Se inserta la cabecera
                INSERT INTO int_solic_cabec
                (
                cod_pais,
                cam_soli,
                cod_clie,
                num_clie,
                tip_soli,
                cod_suba,
                cod_acce,
                tip_desp,
                fec_soli,
                sta_proc,
                oid_cab,
                num_lote,
                cod_comp,
                num_docu,
                cod_regi_arri,
                cod_zona_arri,
                cod_moti_rech,
                ind_proc,
                fec_proc,
                fec_fact,
                num_lote_dd,
                ind_clie_vali,
                ind_docu_iden,
                num_lote_sto,
                cod_inte,
                num_lote_inte
              )
                VALUES
                (
                interfazrecord(x).codPais,
                interfazrecord(x).codPeriodo,
                interfazrecord(x).codCliente,
                1,
                'SOC',
                '000',
                '01',
                'PD',
                trunc(SYSDATE),
                'A',
                seq_solic_cab.nextval,
                vsNumLote,
                interfazrecord(x).codCompania,
                vsNumLote,
                vsCodRegion,
                vsCodZona,
                NULL,
                psindicadorOrigen,
                trunc(SYSDATE),
                trunc(SYSDATE),
                NULL,
                NULL,
                NULL,
                psnumeroLoteSTO,
                pscodigoInterfaz,
                vsNumLote
              );

            END IF;

            --Se inserta el detalle
              INSERT INTO int_solic_posic
              (
              cod_pais,
              cam_soli,
              cod_clie,
              tip_posi,
              cod_prod,
              uni_dema,
              sta_proc,
              oid_posic,
              num_lote,
              cod_comp,
              num_docu,
              cod_moti_rech,
              num_line_oc,
              ind_proc,
              cod_vent,
              num_lote_dd,
              num_lote_sto,
              cod_inte,
              num_lote_inte
            )
              VALUES
              (
              interfazrecord(x).codPais,
              interfazrecord(x).codPeriodo,
              interfazrecord(x).codCliente,
              'OC',
              interfazrecord(x).codVenta,
              interfazrecord(x).unidades,
              'A',
              seq_solic_pos.nextval,
              vsNumLote,
              interfazrecord(x).codCompania,
              vsNumLote,
              NULL,
              NULL,
              psindicadorOrigen,
              interfazrecord(x).codVenta,
              NULL,
              psnumeroLoteSTO,
              pscodigoInterfaz,
              vsNumLote
            );

            vsCodClieAnt := interfazrecord(x).codCliente;

          END LOOP;
        END IF;

      EXIT WHEN c_pedido%NOTFOUND;

    END LOOP;
  CLOSE c_pedido;

  --Se llama a la consolidacion de pedidos
  int_pkg_occrr.ocr_pr_solic_conso_pedid(pscodigoPais,
                                         pscodigoTipoDocu,
                                         pscodigousuario,
                                         psnumeroLoteSTO,
                                         psindicadorOrigen);

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM,1,1000);
      raise_application_error(-20123,'ERROR INT_PR_IVR_RECEP_PEDID: ' || ls_sqlerrm);

END INT_PR_IVR_RECEP_PEDID;

END INT_PKG_IVVRR;
/
