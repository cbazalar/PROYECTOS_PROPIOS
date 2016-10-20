CREATE OR REPLACE PACKAGE EDU_PKG_CALIF IS

  /* Declaracion de Tipos */
  TYPE TIPOCURSOR IS REF CURSOR;
  TYPE TCODIGO_REGION IS TABLE OF VARCHAR(4);
  TYPE TCODIGO_ZONA IS TABLE OF VARCHAR(4);
  TYPE TPORCENTAJE_CAMPANNA IS TABLE OF VARCHAR(6);

  /* Declaracion de constantes */
  INDICADOR_PRIMER_PEDIDO     VARCHAR2(1) := '1';
  INDICADOR_SI                VARCHAR2(1) := 'S';
  INDICADOR_NO                VARCHAR2(1) := 'N';
  INDICADOR_ACTIVO            VARCHAR2(1) := '1';
  INDICADOR_PENDIENTE         VARCHAR2(2) := 'PC';
  INDICADOR_POR_PROGRAMAR     VARCHAR2(2) := 'PP';
  INDICADOR_POR_CONFIRMAR     VARCHAR2(2) := 'PO';
  INDICADOR_FACTURA_CONFIRMAR VARCHAR2(2) := 'FC';
  INDICADOR_PROGRAMADA        VARCHAR2(2) := 'PR';
  INDICADOR_CAPACITADA        VARCHAR2(2) := 'CP';
  INVITACION_CURSO            VARCHAR2(2) := '01';
  SECUENCIA_SIN_CONDICION     VARCHAR2(2) := '01';
  RANGO_CAMPANA               NUMBER := 18;
  TIPO_CURSO_MIXTO            VARCHAR2(2) := '03';
  TIPO_CURSO_SIN_COSTO        VARCHAR2(2) := '01';

  /* Declaracion de Variables */
  LN_SQLCODE NUMBER(10);
  LS_SQLERRM VARCHAR2(150);
  W_FILAS    NUMBER := 1000;

  /***************************************************************************
  Descripcion       : Funcion que devuelve la campaña Actual de Proceso
  Fecha Creacion    : 09/07/2007
  Autor             : Carlos Bazalar
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodEmpresa : Codigo de Empresa
  ***************************************************************************/
  FUNCTION EDU_FN_DEVUE_CAMPA_PROCE_ACTUA(PSCODPAIS    VARCHAR2,
                                          PSCODEMPRESA VARCHAR2)
    RETURN VARCHAR2;

  /***************************************************************************
  Descripcion       : Funcion que devuelve la Fecha de Facturación de la
                      Campaña de Proceso
  Fecha Creacion    : 09/07/2007
  Autor             : Carlos Bazalar
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodEmpresa : Codigo de Empresa
  ***************************************************************************/
  FUNCTION EDU_FN_DEVUE_FECHA_FACTU_ACTUA(PSCODPAIS    VARCHAR2,
                                          PSCODEMPRESA VARCHAR2)
    RETURN VARCHAR2;

  /***************************************************************************
  Descripcion       : Funcion que devuelve si el curso es valido para la
                      calificación automatica
  Fecha Creacion    : 09/07/2007
  Autor             : Carlos Bazalar
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodEmpresa : Codigo de Empresa
              psCodCurso   : Codigo de Curso de Capacitación
              psCodPeriodo : Campaña de Proceso
  Retorno:          1  Curso Valido
                   -1  Curso no valido
  ***************************************************************************/
  FUNCTION EDU_FN_DEVUE_CURSO_CALIF_AUTOM(PSCODPAIS    VARCHAR2,
                                          PSCODEMPRESA VARCHAR2,
                                          PSCODCURSO   VARCHAR2,
                                          PSCODPERIODO VARCHAR2)
    RETURN NUMBER;

  /***************************************************************************
  Descripcion       : Procedimiento que realiza el proceso de calificacion de
                      Aptas a Demanda
  Fecha Creacion    : 03/10/2007
  Autor             : Carlos Bazalar
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodEmpresa : Codigo de Empresa
              psCodCurso   : Codigo de Curso de Capacitación
              psCodPeriodo : Campaña de Proceso
              psUsuario    : Usuario
  ***************************************************************************/
  PROCEDURE EDU_PR_CALIF_APTAS_DEMAM(PSCODIGOPAIS VARCHAR2,
                                     PSCODEMPRESA VARCHAR2,
                                     PSCODCURSO   VARCHAR2,
                                     PSCODPERIODO VARCHAR2,
                                     PSUSUARIO    VARCHAR2);

  /***************************************************************************
  Descripcion       : Procedimiento que realiza CARGA de Consultoras de calificacion de
                      Aptas a Demanda para ser mostradas en la Consulta PREVIA a
                      la Ejecucion de la Calificacion
  Fecha Creacion    : 03/10/2007
  Autor             : Carlos Bazalar
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodEmpresa : Codigo de Empresa
              psCodPeriodo : Campaña de Proceso
  ***************************************************************************/
  PROCEDURE EDU_PR_CARGA_APTAS_DEMAM(PSCODIGOPAIS         VARCHAR2,
                                     PSCODEMPRESA         VARCHAR2,
                                     PSCODPERIODO         VARCHAR2,
                                     PSCODPROCESO         VARCHAR2,
                                     PSCODPARAM           VARCHAR2,
                                     PSREGISTROSOBTENIDOS OUT VARCHAR2);

  /***************************************************************************
  Descripcion       : Procedimiento que realiza el proceso de Carga de Consultoras
                      APTAS para la calificacion de aptas a demanda
  Fecha Creacion    : 05/10/2007
  Autor             : Carlos Bazalar
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodEmpresa : Codigo de Empresa
              psCodCurso   : Codigo de Curso de Capacitación
              psCodPeriodo : Campaña de Proceso
  ***************************************************************************/
  PROCEDURE EDU_PR_CARGA_CONSU_APTAS_DEMAM(PSCODIGOPAIS VARCHAR2,
                                           PSCODEMPRESA VARCHAR2,
                                           PSCODCURSO   VARCHAR2,
                                           PSCODPERIODO VARCHAR2);

  /***************************************************************************
  Descripcion       : Procedimiento que realiza el proceso de calificacion de
                      Aptas Automatico
  Fecha Creacion    : 09/07/2007
  Autor             : Carlos Bazalar
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodEmpresa : Codigo de Empresa
              psCodCurso   : Codigo de Curso de Capacitación
              psCodPeriodo : Campaña de Proceso
              psIndicadorBloqueo : Indicador de Bloqueo (1 Efectuar Bloqueo  0 No Efectuar Bloqueo)
              psUsuario    : Usuario
  ***************************************************************************/
  PROCEDURE EDU_PR_CALIF_APTAS_AUTOM(PSCODIGOPAIS       VARCHAR2,
                                     PSCODEMPRESA       VARCHAR2,
                                     PSCODCURSO         VARCHAR2,
                                     PSCODPERIODO       VARCHAR2,
                                     PSINDICADORBLOQUEO VARCHAR2,
                                     PSUSUARIO          VARCHAR2);

  /***************************************************************************
  Descripcion       : Procedimiento que borra aquellas consultoras que hayan
                      sido bloqueadas previamente
  Fecha Creacion    : 03/03/2008
  Autor             : Carlos Bazalar
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodEmpresa : Codigo de Empresa
  ***************************************************************************/
  PROCEDURE EDU_PR_FILTR_CLIEN_BLOQU(PSCODIGOPAIS VARCHAR2,
                                     PSCODEMPRESA VARCHAR2);

  /***************************************************************************
  Descripcion       : Procedimiento que borra aquellas consultoras que hayan sido invitadas y
                      pendientes de capacitacion para la campaña de proceso.
                      Es decir ya no se volvera a calificar aquellas CONSULTORAS
                      que hayan sido CALIFICADAS en la misma CAMPAÑA y con Nro de Invitacion Mayor a 0
  Fecha Creacion    : 02/10/2007
  Autor             : Carlos Bazalar
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodEmpresa : Codigo de Empresa
              psCodPeriodo : Campaña de Proceso
              psCodCurso   : Codigo de Curso
  ***************************************************************************/
  PROCEDURE EDU_PR_FILTR_CALIF_CAMPA(PSCODIGOPAIS VARCHAR2,
                                     PSCODEMPRESA VARCHAR2,
                                     PSCODPERIODO VARCHAR2,
                                     PSCODCURSO   VARCHAR2);

  /***************************************************************************
  Descripcion       : Procedimiento que borra aquellas consultoras que haya
                      sido capacitada en el curso en anteriores campañas
  Fecha Creacion    : 02/10/2007
  Autor             : Carlos Bazalar
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodEmpresa : Codigo de Empresa
              psCodCurso   : Codigo de Curso de Capacitacion
  ***************************************************************************/
  PROCEDURE EDU_PR_FILTR_CALIF_ANTER_CAMPA(PSCODIGOPAIS VARCHAR2,
                                           PSCODEMPRESA VARCHAR2,
                                           PSCODCURSO   VARCHAR2);

  /***************************************************************************
  Descripcion       : Procedimiento que borra a aquellas consultoras que no hayan cumplido
                      curso pre-requisito de capacitacion
  Fecha Creacion    : 02/10/2007
  Autor             : Carlos Bazalar
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodEmpresa : Codigo de Empresa
              psCodPeriodo : Codigo de Periodo (Campaña de Proceso)
              psCodCurso   : Codigo de Curso
              psCursoPreRequisito    : Codigo de Curso de Capacitacion Pre-Requisito
              pnNumCampaPreRequisito : Numero de Campañas maxima de Pre-requisito
  ***************************************************************************/
  PROCEDURE EDU_PR_FILTR_PRERE_CAPAC(PSCODIGOPAIS           VARCHAR2,
                                     PSCODEMPRESA           VARCHAR2,
                                     PSCODPERIODO           VARCHAR2,
                                     PSCODCURSO             VARCHAR2,
                                     PSCURSOPREREQUISITO    VARCHAR2,
                                     PNNUMCAMPAPREREQUISITO NUMBER);

  /***************************************************************************
  Descripcion       : Procedimiento que borra a aquellas consultoras
                      cuyo numero de invitacion haya excedido al valor
                      parametrizado
  Fecha Creacion    : 02/10/2007
  Autor             : Carlos Bazalar
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodEmpresa : Codigo de Empresa
              psCodCurso   : Codigo de Curso
              pnNumCampaCursoInvitacion : Numero maximo de Invitaciones
  ***************************************************************************/
  PROCEDURE EDU_PR_FILTR_NUMER_INVIT(PSCODIGOPAIS              VARCHAR2,
                                     PSCODEMPRESA              VARCHAR2,
                                     PSCODCURSO                VARCHAR2,
                                     PNNUMCAMPACURSOINVITACION NUMBER);

  /***************************************************************************
  Descripcion       : Procedimiento que borra a aquellas consultoras
                      cuyo numero de invitacion sean menores al valor parametrizado
                      (Calificacion a Demanda)
  Fecha Creacion    : 02/10/2007
  Autor             : Carlos Bazalar
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodEmpresa : Codigo de Empresa
              psCodCurso   : Codigo de Curso
              pnNumCampaCursoInvitacion : Numero maximo de Invitaciones
  ***************************************************************************/
  PROCEDURE EDU_PR_FILTR_NUMER_INVIT_DEMAN(PSCODIGOPAIS              VARCHAR2,
                                           PSCODEMPRESA              VARCHAR2,
                                           PSCODCURSO                VARCHAR2,
                                           PNNUMCAMPACURSOINVITACION NUMBER);

  /***************************************************************************
  Descripcion       : Procedimiento que borra a aquellas consultoras
                      que hayan excedido la secuencia de Pedido
  Fecha Creacion    : 02/10/2007
  Autor             : Carlos Bazalar
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodEmpresa : Codigo de Empresa
              psCodCurso   : Codigo de curso
              psCodPeriodo : Codigo de Periodo
              psCodSecuencia : Codigo de Secuencia de Pedido
              pnNumPediReque : Numero de Pedidos requeridos
              pnNumCampaEvaluar : Numero de campañas a evaluar
  ***************************************************************************/
  PROCEDURE EDU_PR_FILTR_SECUE_PEDID(PSCODIGOPAIS      VARCHAR2,
                                     PSCODEMPRESA      VARCHAR2,
                                     PSCODCURSO        VARCHAR2,
                                     PSCODPERIODO      VARCHAR2,
                                     PSCODSECUENCIA    VARCHAR2,
                                     PNNUMPEDIREQUE    NUMBER,
                                     PNNUMCAMPAEVALUAR NUMBER);

  /***************************************************************************
  Descripcion       : Procedimiento que realiza el Registro en el Historico
                      de Aptas tanto para Calificacion Automatica como a Demanda
  Fecha Creacion    : 09/07/2007
  Autor             : Carlos Bazalar
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodEmpresa : Codigo de Empresa
              psCodCurso   : Codigo de Curso de Capacitación
              psCodPeriodo : Campaña de Proceso
              psTipo       : 'A': Calificacion Automatica   'D': Calificacion a Demanda
              psUsuario    : Usuario
  ***************************************************************************/
  PROCEDURE EDU_PR_REGIS_CALIF_APTAS(PSCODIGOPAIS VARCHAR2,
                                     PSCODEMPRESA VARCHAR2,
                                     PSCODCURSO   VARCHAR2,
                                     PSCODPERIODO VARCHAR2,
                                     PSTIPO       VARCHAR2,
                                     PSUSUARIO    VARCHAR2);

  /***************************************************************************
  Descripcion       : Procedimiento que realiza el Proceso de Bloqueo de Consultoras
                      para aquellas que han pasado Pedido
                      de Aptas
  Fecha Creacion    : 08/11/2007
  Autor             : Carlos Bazalar
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodEmpresa : Codigo de Empresa
              psCodPeriodo : Campaña de Proceso
              psCodCurso   : Codigo de Curso de Capacitación
              pnNumCampaCursoInvitacion : Numero de Invitacion
              psUsuario    : Usuario
  ***************************************************************************/
  PROCEDURE EDU_PR_PROCE_BLOQU_CONSU(PSCODIGOPAIS              VARCHAR2,
                                     PSCODEMPRESA              VARCHAR2,
                                     PSCODPERIODO              VARCHAR2,
                                     PSCODCURSO                VARCHAR2,
                                     PNNUMCAMPACURSOINVITACION NUMBER,
                                     PSUSUARIO                 VARCHAR2);

  /***************************************************************************
  Descripcion       : Procedimiento que realiza el Proceso de Bloqueo de Consultoras
                      (Para Consultoras en Estado PENDIENTE)
  Fecha Creacion    : 15/01/2008
  Autor             : Carlos Bazalar
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodEmpresa : Codigo de Empresa
              psCodPeriodo : Campaña de Proceso
              psCodCurso   : Codigo de Curso de Capacitación
              pnNumCampaCursoInvitacion : Numero de Invitacion
              psUsuario    : Usuario
  ***************************************************************************/
  PROCEDURE EDU_PR_PROCE_BLOQU_CONSU_PENDI(PSCODIGOPAIS              VARCHAR2,
                                           PSCODEMPRESA              VARCHAR2,
                                           PSCODPERIODO              VARCHAR2,
                                           PSCODCURSO                VARCHAR2,
                                           PNNUMCAMPACURSOINVITACION NUMBER,
                                           PSUSUARIO                 VARCHAR2);

  /***************************************************************************
  Descripcion       : Procedimiento que realiza el Proceso de Bloqueo de Consultoras
                      (Para Consultoras en Estado PROGRAMADA)
  Fecha Creacion    : 15/01/2008
  Autor             : Carlos Bazalar
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodEmpresa : Codigo de Empresa
              psCodPeriodo : Campaña de Proceso
              psCodCurso   : Codigo de Curso de Capacitación
              pnNumCampaCursoInvitacion : Numero de Invitacion
              psUsuario    : Usuario
  ***************************************************************************/
  PROCEDURE EDU_PR_PROCE_BLOQU_CONSU_PROGR(PSCODIGOPAIS              VARCHAR2,
                                           PSCODEMPRESA              VARCHAR2,
                                           PSCODPERIODO              VARCHAR2,
                                           PSCODCURSO                VARCHAR2,
                                           PNNUMCAMPACURSOINVITACION NUMBER,
                                           PSUSUARIO                 VARCHAR2);

  /***************************************************************************
  Descripcion       : Procedimiento que identifica aquellas consultoras que tienen
                      codigo de planilla y no se ha relacionado con ningun codigo de
                      curso dictado
  Fecha Creacion    : 02/02/2008
  Autor             : Carlos Bazalar
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodEmpresa : Codigo de Empresa
              psCodCurso   : Codigo de Curso de Capacitación
              psCodPeriodo : Campaña de Proceso
              psUsuario    : Usuario
  ***************************************************************************/
  PROCEDURE EDU_PR_CONSU_PLANI_NREGIS(PSCODIGOPAIS VARCHAR2,
                                      PSCODEMPRESA VARCHAR2,
                                      PSCODCURSO   VARCHAR2,
                                      PSCODPERIODO VARCHAR2,
                                      PSUSUARIO    VARCHAR2);

  /***************************************************************************
  Descripcion       : Procedimiento que adiciona aquellas consultoras con posible
                      egreso en planilla
  Fecha Creacion    : 25/02/2008
  Autor             : Carlos Bazalar
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodEmpresa : Codigo de Empresa
              psCodPeriodo : Codigo de Periodo (Campaña de Proceso)
              psCodCurso   : Codigo de Curso
              psCursoPreRequisito    : Codigo de Curso de Capacitacion Pre-Requisito
              psIndicadorCostoCurso  : Indicador Curso con costo
  ***************************************************************************/
  PROCEDURE EDU_PR_PROCE_POSIB_EGRESO(PSCODIGOPAIS              VARCHAR2,
                                      PSCODEMPRESA              VARCHAR2,
                                      PSCODPERIODO              VARCHAR2,
                                      PSCODCURSO                VARCHAR2,
                                      PSCODCURSOPREREQUISITO    VARCHAR2,
                                      PSINDICADORCOSTOCURSO     VARCHAR2,
                                      PNNUMCAMPACURSOINVITACION NUMBER);

  /***************************************************************************
  Descripcion       : Procedimiento que genera consultoras APTAS para cursos mixtos
                      (cursos sin costo). Son aquellas consultoras que han pasado
                      el número máximo de invitaciones pero que van a comprar el curso
                      en forma extemporanea
  Fecha Creacion    : 06/03/2008
  Autor             : Carlos Bazalar
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodEmpresa : Codigo de Empresa
              psCodPeriodo : Codigo de Periodo (Campaña de Proceso)
  ***************************************************************************/
  PROCEDURE EDU_PR_PROCE_APTAS_MIXTO_BLOQU(PSCODIGOPAIS              VARCHAR2,
                                           PSCODEMPRESA              VARCHAR2,
                                           PSCODPERIODO              VARCHAR2,
                                           PSINDICADOREQUIMENSAJE    VARCHAR2,
                                           PSINDICADORNOMBRECOMPLETO VARCHAR2,
                                           PSNUMEROLOTE              VARCHAR2,
                                           PSUSUARIO                 VARCHAR2);

  /***************************************************************************
  Descripcion       : Funcion que devuelve si el curso es valido para los cursos mixtos
  Fecha Creacion    : 06/03/2008
  Autor             : Carlos Bazalar
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodEmpresa : Codigo de Empresa
              psCodCurso   : Codigo de Curso de Capacitación
              psIndicadorProcesoBloqueo : Indicador de Proceso de Bloqueo
  ***************************************************************************/
  FUNCTION EDU_FN_CURSO_APTAS_MIXTO_BLOQU(PSCODPAIS                 VARCHAR2,
                                          PSCODEMPRESA              VARCHAR2,
                                          PSCODCURSO                VARCHAR2,
                                          PSINDICADORPROCESOBLOQUEO VARCHAR2)
    RETURN NUMBER;

  /***************************************************************************
  Descripcion       : Procedimiento para centralizar los procesos post ejecucion
                      de calificacion y envio de aptas
  Fecha Creacion    : 27/05/2008
  Autor             : Rafael Romero
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodEmpresa : Codigo de Empresa
              psCodPeriodo : Codigo de Periodo (Campaña de Proceso)
              psCodCurso   : Codigo de usuario
              psmensajeerror : Mensaje de retorno
  ***************************************************************************/
  PROCEDURE EDU_PR_PROCE_AFTER_CALIF_ENVAP(PSCODIGOPAIS   IN VARCHAR2,
                                           PSCODEMPRESA   IN VARCHAR2,
                                           PSCODPERIODO   IN VARCHAR2,
                                           PSUSUARIO      IN VARCHAR2,
                                           PSMENSAJEERROR OUT VARCHAR2);

  /***************************************************************************
  Descripcion       : Procedure que Actualiza los cursos Mixtos de aquellas consultoras que han pedido un cuv
                       en el historico de aptas
  Fecha Creacion    : 26/09/2009
  Autor             : Sergio Buchelli Silva
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodEmpresa : Codigo de Empresa
              psCodigoPeriodo : Codigo Periodo
              psCodCurso : Codigo Curso
              psUsuario : Codigo de Usuario
  ***************************************************************************/

  PROCEDURE EDU_PR_ACTUA_CURSO_MIXTO(PSCODIGOPAIS    VARCHAR2,
                                     PSCODEMPRESA    VARCHAR2,
                                     PSCODIGOPERIODO VARCHAR2,
                                     PSCODCURSO      VARCHAR2,
                                     PSUSUARIO       VARCHAR2);

  /***************************************************************************
  Descripcion       : Funcion que devuelve la campanha de inicio de pedidos dependiendo del numero dei nvitaciones
              y si se encuentra en apta
  Fecha Creacion    : 11/11/2008
  Autor             : Sergio Buchelli
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodEmpresa : Codigo de Empresa
              psCodCurso   : Codigo de Curso de Capacitación
              psIndicadorProcesoBloqueo : Indicador de Proceso de Bloqueo
  ***************************************************************************/
  FUNCTION EDU_FN_INICI_PEDID_APTAS(PSCODPAIS       VARCHAR2,
                                    PSCODEMPRESA    VARCHAR2,
                                    PSCODCURSO      VARCHAR2,
                                    PSCODCONSULTORA VARCHAR2,
                                    PSCODPERIODO    VARCHAR2) RETURN VARCHAR2;

  /***************************************************************************
  Descripcion       : Procedimiento que realiza el Proceso de DesBloqueo de Consultoras
                      para aquellas que han pasado Pedido
                      de Aptas
  Fecha Creacion    : 26/10/2009
  Autor             : Sergio Buchelli
  Parametros        :
              psCodigoPais : Codigo de Pais
              psCodEmpresa : Codigo de Empresa
              psCodPeriodo : Campaña de Proceso
        psCodCurso   : Codigo Curso
              psUsuario    : Usuario
  ***************************************************************************/
  PROCEDURE EDU_PR_PROCE_DESBL_CONSU(PSCODIGOPAIS VARCHAR2,
                                     PSCODEMPRESA VARCHAR2,
                                     PSCODPERIODO VARCHAR2,
                                     PSCODCURSO   VARCHAR2,
                                     PSTIPOCURSO  VARCHAR2,
                                     PSUSUARIO    VARCHAR2);

  /***************************************************************************
  descripcion       : procedimiento que realiza la calificacion de aquellas consultoras
              han sido exoneradas cuya secuencia de pedidos ha expirado
  fecha creacion    : 29/10/2009
  autor             : Sergio Buchelli
  parametros        :
              pscodigopais : codigo de pais
              pscodempresa : codigo de empresa
              pscodperiodo : campaña de proceso
        pscodcurso   : codigo curso
              psusuario    : usuario
  ***************************************************************************/
  PROCEDURE EDU_PR_PROCE_CALIF_CONSU_EXONE(PSCODIGOPAIS VARCHAR2,
                                           PSCODEMPRESA VARCHAR2,
                                           PSCODPERIODO VARCHAR2,
                                           PSCODCURSO   VARCHAR2,
                                           PSTIPOCURSO  VARCHAR2,
                                           PSUSUARIO    VARCHAR2);

/***************************************************************************
Descripcion       : Devuelve Nro de Campa?as entre un rango de periodo
                    Inicial y final
Fecha Creacion    : 04/10/2012
Autor             : Sergio Buchelli
***************************************************************************/
FUNCTION EDU_FN_DEVUE_NUME_CAMPA(
   psCodPeriodoIni VARCHAR2,
   psCodPeriodoFin VARCHAR2,
   psCodPais       VARCHAR2
)RETURN NUMBER;

END EDU_PKG_CALIF;
/

CREATE OR REPLACE PACKAGE BODY "EDU_PKG_CALIF" IS

  /***************************************************************************
  Descripcion : Funcion que devuelve la campaña Actual de Proceso
  Fecha Creacion : 09/07/2007
  Autor : Carlos Bazalar
  Parametros :
   psCodigoPais : Codigo de Pais
   psCodEmpresa : Codigo de Empresa
  ***************************************************************************/
  FUNCTION EDU_FN_DEVUE_CAMPA_PROCE_ACTUA(PSCODPAIS    VARCHAR2,
                                          PSCODEMPRESA VARCHAR2)
    RETURN VARCHAR2 IS
    LSCODPERI EDU_CONTR_FACTU.COD_PERI%TYPE;
  BEGIN
    SELECT A.COD_PERI
      INTO LSCODPERI
      FROM EDU_CONTR_FACTU A
     WHERE A.PAIS_COD_PAIS = PSCODPAIS
       AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
       AND A.EST_CAMP = INDICADOR_ACTIVO
       AND A.EST_REGI = INDICADOR_ACTIVO;
    RETURN LSCODPERI;

  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RETURN NULL;
    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR EDU_FN_DEVUE_CAMPA_PROCE_ACTUA: ' ||
                              LS_SQLERRM);
  END EDU_FN_DEVUE_CAMPA_PROCE_ACTUA;

  /***************************************************************************
  Descripcion : Funcion que devuelve la Fecha de Facturación de la
   Campaña de Proceso
  Fecha Creacion : 09/07/2007
  Autor : Carlos Bazalar
  Parametros :
   psCodigoPais : Codigo de Pais
   psCodEmpresa : Codigo de Empresa
  ***************************************************************************/
  FUNCTION EDU_FN_DEVUE_FECHA_FACTU_ACTUA(PSCODPAIS    VARCHAR2,
                                          PSCODEMPRESA VARCHAR2)
    RETURN VARCHAR2 IS
    LSFECHAFACTU VARCHAR2(10);
  BEGIN
    SELECT TO_CHAR(A.FEC_PROC, 'dd/mm/yyyy')
      INTO LSFECHAFACTU
      FROM EDU_CONTR_FACTU A
     WHERE A.PAIS_COD_PAIS = PSCODPAIS
       AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
       AND A.EST_CAMP = INDICADOR_ACTIVO
       AND A.EST_REGI = INDICADOR_ACTIVO;
    RETURN LSFECHAFACTU;

  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RETURN NULL;
    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR EDU_FN_DEVUE_FECHA_FACTU_ACTUA: ' ||
                              LS_SQLERRM);
  END EDU_FN_DEVUE_FECHA_FACTU_ACTUA;

  /***************************************************************************
  Descripcion : Funcion que devuelve si el curso es valido para la
   calificación automatica
  Fecha Creacion : 09/07/2007
  Autor : Carlos Bazalar
  Parametros :
   psCodigoPais : Codigo de Pais
   psCodEmpresa : Codigo de Empresa
   psCodCurso : Codigo de Curso de Capacitación
   psCodPeriodo : Campaña de Proceso
  ***************************************************************************/
  FUNCTION EDU_FN_DEVUE_CURSO_CALIF_AUTOM(PSCODPAIS    VARCHAR2,
                                          PSCODEMPRESA VARCHAR2,
                                          PSCODCURSO   VARCHAR2,
                                          PSCODPERIODO VARCHAR2)
    RETURN NUMBER IS
    REGPARAMCURSOS    EDU_PARAM_CURSO_CAPAC%ROWTYPE;
    LSCAMPANNAINICIAL EDU_PARAM_CURSO_CAPAC.CAM_INIC%TYPE;
    LSNROCAMPAPREVIAS EDU_PARAM_CURSO_CAPAC.NUM_CAMP_PREV_CALI%TYPE;
    LSCODPERIODO      VARCHAR2(6);
  BEGIN

    /* Obteniendo parametrizacion del curso */
    BEGIN
      SELECT *
        INTO REGPARAMCURSOS
        FROM EDU_PARAM_CURSO_CAPAC A
       WHERE A.PAIS_COD_PAIS = PSCODPAIS
         AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
         AND A.COD_CURS_CAPA = PSCODCURSO
         AND A.EST_REGI = INDICADOR_ACTIVO;
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        RETURN - 1;
    END;

    IF REGPARAMCURSOS.FRCA_COD_FREC_CALI = '01' THEN
      RETURN 1;
    END IF;
    IF REGPARAMCURSOS.FRCA_COD_FREC_CALI = '03' THEN
      RETURN - 1;
    END IF;
    IF REGPARAMCURSOS.FRCA_COD_FREC_CALI = '02' THEN
      LSCAMPANNAINICIAL := REGPARAMCURSOS.CAM_INIC;
      LSNROCAMPAPREVIAS := (-1) * REGPARAMCURSOS.NUM_CAMP_PREV_CALI;
      LSCODPERIODO      := EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_CODIG_PERIO(LSCAMPANNAINICIAL,
                                                                        LSNROCAMPAPREVIAS);
      IF LSCODPERIODO <> PSCODPERIODO THEN
        RETURN - 1;
      END IF;
    END IF;
    RETURN 1;

  EXCEPTION
    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR EDU_FN_DEVUE_CURSO_CALIF_AUTOM: ' ||
                              LS_SQLERRM);

  END EDU_FN_DEVUE_CURSO_CALIF_AUTOM;

  /***************************************************************************
  Descripcion : Procedimiento que realiza el proceso de calificacion de
   Aptas a Demanda
  Fecha Creacion : 03/10/2007
  Autor : Carlos Bazalar
  Parametros :
   psCodigoPais : Codigo de Pais
   psCodEmpresa : Codigo de Empresa
   psCodCurso : Codigo de Curso de Capacitación
   psCodPeriodo : Campaña de Proceso
   psUsuario : Usuario
  ***************************************************************************/
  PROCEDURE EDU_PR_CALIF_APTAS_DEMAM(PSCODIGOPAIS VARCHAR2,
                                     PSCODEMPRESA VARCHAR2,
                                     PSCODCURSO   VARCHAR2,
                                     PSCODPERIODO VARCHAR2,
                                     PSUSUARIO    VARCHAR2) IS

  BEGIN
    /* borrando tablas temporales */
    DELETE FROM EDU_GTT_CLIEN_PAIS;
    DELETE FROM EDU_GTT_CLIEN;

    /* Obtiene Lista de Consultoras APTAS para la calificacion de aptas a demanda */
    EDU_PR_CARGA_CONSU_APTAS_DEMAM(PSCODIGOPAIS,
                                   PSCODEMPRESA,
                                   PSCODCURSO,
                                   PSCODPERIODO);

    /* Invocando al Proceso que actualiza el Registro Historico de Aptas */
    EDU_PR_REGIS_CALIF_APTAS(PSCODIGOPAIS,
                             PSCODEMPRESA,
                             PSCODCURSO,
                             PSCODPERIODO,
                             'D',
                             PSUSUARIO);

    /* borrando tablas temporales */
    DELETE FROM EDU_GTT_CLIEN_PAIS;
    DELETE FROM EDU_GTT_CLIEN;

    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR EDU_PR_CALIF_APTAS_DEMAM: ' ||
                              LS_SQLERRM);
  END EDU_PR_CALIF_APTAS_DEMAM;

  /***************************************************************************
  Descripcion : Procedimiento que realiza CARGA de Consultoras de calificacion de
   Aptas a Demanda para ser mostradas en la Consulta PREVIA a
   la Ejecucion de la Calificacion
  Fecha Creacion : 03/10/2007
  Autor : Carlos Bazalar
  Parametros :
   psCodigoPais : Codigo de Pais
   psCodEmpresa : Codigo de Empresa
   psCodPeriodo : Campaña de Proceso
   psCodProceso : Codigo de Proceso
   psCodParam : Codigo de Parametro
  ***************************************************************************/
  PROCEDURE EDU_PR_CARGA_APTAS_DEMAM(PSCODIGOPAIS         VARCHAR2,
                                     PSCODEMPRESA         VARCHAR2,
                                     PSCODPERIODO         VARCHAR2,
                                     PSCODPROCESO         VARCHAR2,
                                     PSCODPARAM           VARCHAR2,
                                     PSREGISTROSOBTENIDOS OUT VARCHAR2) IS
    LNCONTADOR NUMBER;
    CURSOR CCURSOS IS
      SELECT X.VAL_PARA_VARC
        FROM EDU_GTT_PARAM_PROCE X
       WHERE X.COD_PROC = PSCODPROCESO
         AND X.COD_PARA = PSCODPARAM;

  BEGIN
    DELETE FROM EDU_GTT_CLIEN_PAIS;

    /* Recorriendo Lista de Cursos */
    FOR VCURCURSOS IN CCURSOS LOOP
      DELETE FROM EDU_GTT_CLIEN;

      /* Obtiene Lista de Consultoras APTAS para la calificacion de aptas a demanda */
      EDU_PR_CARGA_CONSU_APTAS_DEMAM(PSCODIGOPAIS,
                                     PSCODEMPRESA,
                                     VCURCURSOS.VAL_PARA_VARC,
                                     PSCODPERIODO);
    END LOOP;

    /* borrando tablas temporales */
    DELETE FROM EDU_GTT_CLIEN;

    SELECT COUNT(1) INTO LNCONTADOR FROM EDU_GTT_CLIEN_PAIS A;

    PSREGISTROSOBTENIDOS := TO_CHAR(LNCONTADOR);

    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR EDU_PR_CARGA_APTAS_DEMAM: ' ||
                              LS_SQLERRM);
  END EDU_PR_CARGA_APTAS_DEMAM;

  /***************************************************************************
  Descripcion : Procedimiento que realiza el proceso de Carga de Consultoras
   APTAS para la calificacion de aptas a demanda
  Fecha Creacion : 05/10/2007
  Autor : Carlos Bazalar
  Parametros :
   psCodigoPais : Codigo de Pais
   psCodEmpresa : Codigo de Empresa
   psCodCurso : Codigo de Curso de Capacitación
   psCodPeriodo : Campaña de Proceso
  ***************************************************************************/
  PROCEDURE EDU_PR_CARGA_CONSU_APTAS_DEMAM(PSCODIGOPAIS VARCHAR2,
                                           PSCODEMPRESA VARCHAR2,
                                           PSCODCURSO   VARCHAR2,
                                           PSCODPERIODO VARCHAR2) IS
    REGPARAMCURSOS            EDU_PARAM_CURSO_CAPAC%ROWTYPE;
    LNNUMCAMPACURSOINVITACION NUMBER;
  BEGIN

    /* Obteniendo consultoras en base al ambito DE REGION */
    INSERT INTO EDU_GTT_CLIEN
      (COD_CLIE)
      SELECT A.CLIE_COD_CLIE
        FROM EDU_HISTO_APTAS             A,
             EDU_MAEST_CLIEN             B,
             EDU_PARAM_CURSO_DEMAN_REGIO C
       WHERE C.PAIS_COD_PAIS = PSCODIGOPAIS
         AND C.EMCO_COD_EMPR_COME = PSCODEMPRESA
         AND C.CCAP_COD_CURS_CAPA = PSCODCURSO
         AND C.COD_CAMP_PROC = PSCODPERIODO
         AND C.EST_REGI = INDICADOR_ACTIVO

         AND A.EST_CAPA = INDICADOR_PENDIENTE
         AND A.PAIS_COD_PAIS = C.PAIS_COD_PAIS
         AND A.EMCO_COD_EMPR_COME = C.EMCO_COD_EMPR_COME
         AND A.CCAP_COD_CURS_CAPA = C.CCAP_COD_CURS_CAPA
         AND A.CAM_ULTI_CALI_APTA < C.COD_CAMP_PROC
         AND A.EST_REGI = INDICADOR_ACTIVO

         AND B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
         AND B.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
         AND B.COD_CLIE = A.CLIE_COD_CLIE
         AND B.COD_REGI = C.REGI_COD_REGI
         AND B.EST_REGI = INDICADOR_ACTIVO;

    /* Obteniendo consultoras en base al ambito DE ZONA */
    INSERT INTO EDU_GTT_CLIEN
      (COD_CLIE)
      SELECT A.CLIE_COD_CLIE
        FROM EDU_HISTO_APTAS            A,
             EDU_MAEST_CLIEN            B,
             EDU_PARAM_CURSO_DEMAN_ZONA C
       WHERE C.PAIS_COD_PAIS = PSCODIGOPAIS
         AND C.EMCO_COD_EMPR_COME = PSCODEMPRESA
         AND C.CCAP_COD_CURS_CAPA = PSCODCURSO
         AND C.COD_CAMP_PROC = PSCODPERIODO
         AND C.EST_REGI = INDICADOR_ACTIVO

         AND A.EST_CAPA = INDICADOR_PENDIENTE
         AND A.PAIS_COD_PAIS = C.PAIS_COD_PAIS
         AND A.EMCO_COD_EMPR_COME = C.EMCO_COD_EMPR_COME
         AND A.CCAP_COD_CURS_CAPA = C.CCAP_COD_CURS_CAPA
         AND A.CAM_ULTI_CALI_APTA < C.COD_CAMP_PROC
         AND A.EST_REGI = INDICADOR_ACTIVO

         AND B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
         AND B.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
         AND B.COD_CLIE = A.CLIE_COD_CLIE
         AND B.COD_REGI = C.REGI_COD_REGI
         AND B.COD_ZONA = C.ZONA_COD_ZONA
         AND B.EST_REGI = INDICADOR_ACTIVO;

    /* Obteniendo consultoras en base al ambito DE CLIENTE */
    INSERT INTO EDU_GTT_CLIEN
      (COD_CLIE)
      SELECT A.CLIE_COD_CLIE
        FROM EDU_HISTO_APTAS A, EDU_PARAM_CURSO_DEMAN_CLIEN C
       WHERE C.PAIS_COD_PAIS = PSCODIGOPAIS
         AND C.EMCO_COD_EMPR_COME = PSCODEMPRESA
         AND C.CCAP_COD_CURS_CAPA = PSCODCURSO
         AND C.COD_CAMP_PROC = PSCODPERIODO
         AND C.EST_REGI = INDICADOR_ACTIVO

         AND A.EST_CAPA = INDICADOR_PENDIENTE
         AND A.PAIS_COD_PAIS = C.PAIS_COD_PAIS
         AND A.EMCO_COD_EMPR_COME = C.EMCO_COD_EMPR_COME
         AND A.CCAP_COD_CURS_CAPA = C.CCAP_COD_CURS_CAPA
         AND A.CAM_ULTI_CALI_APTA < C.COD_CAMP_PROC
         AND A.CLIE_COD_CLIE = C.CLIE_COD_CLIE
         AND A.EST_REGI = INDICADOR_ACTIVO;

    /* Obteniendo consultoras en base al ambito DE CAMPAÑA */
    INSERT INTO EDU_GTT_CLIEN
      (COD_CLIE)
      SELECT A.CLIE_COD_CLIE
        FROM EDU_HISTO_APTAS             A,
             EDU_MAEST_CLIEN             B,
             EDU_PARAM_CURSO_DEMAN_CAMPA C
       WHERE C.PAIS_COD_PAIS = PSCODIGOPAIS
         AND C.EMCO_COD_EMPR_COME = PSCODEMPRESA
         AND C.CCAP_COD_CURS_CAPA = PSCODCURSO
         AND C.COD_CAMP_PROC = PSCODPERIODO
         AND C.EST_REGI = INDICADOR_ACTIVO

         AND A.EST_CAPA = INDICADOR_PENDIENTE
         AND A.PAIS_COD_PAIS = C.PAIS_COD_PAIS
         AND A.EMCO_COD_EMPR_COME = C.EMCO_COD_EMPR_COME
         AND A.CCAP_COD_CURS_CAPA = C.CCAP_COD_CURS_CAPA
         AND A.CAM_ULTI_CALI_APTA < C.COD_CAMP_PROC
         AND A.EST_REGI = INDICADOR_ACTIVO

         AND B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
         AND B.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
         AND B.COD_CLIE = A.CLIE_COD_CLIE
         AND B.CAM_INGR = C.COD_CAMP_INGR
         AND B.EST_REGI = INDICADOR_ACTIVO;

    /* Pasando lista sin duplicados de clientes a tabla temporal */
    INSERT INTO EDU_GTT_CLIEN_PAIS
      (COD_PAIS, COD_EMPR_COME, COD_CLIE, COD_CURS_CAPA)
      SELECT DISTINCT PSCODIGOPAIS, PSCODEMPRESA, A.COD_CLIE, PSCODCURSO
        FROM EDU_GTT_CLIEN A;

    --RETURN;

    /* Obteniendo parametrizacion del curso */
    SELECT *
      INTO REGPARAMCURSOS
      FROM EDU_PARAM_CURSO_CAPAC A
     WHERE A.PAIS_COD_PAIS = PSCODIGOPAIS
       AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
       AND A.COD_CURS_CAPA = PSCODCURSO
       AND A.EST_REGI = INDICADOR_ACTIVO;

    /* Obteniendo parametrixacion de Numero de Campañas para cursar invitacion */
    IF REGPARAMCURSOS.IND_COST_CURS = 'N' THEN
      LNNUMCAMPACURSOINVITACION := REGPARAMCURSOS.NUM_CAMP_MAXI_ASRE;
      IF LNNUMCAMPACURSOINVITACION IS NULL THEN
        LNNUMCAMPACURSOINVITACION := 0;
      END IF;
    ELSE
      LNNUMCAMPACURSOINVITACION := REGPARAMCURSOS.NUM_CAMP_CUMP_PREQ;
    END IF;

    /* Borrando aquellas consultoras que hayan sido bloqueadas previamente */
    EDU_PR_FILTR_CLIEN_BLOQU(PSCODIGOPAIS, PSCODEMPRESA);

    /* BORRANDO aquellas consultoras que hayan sido invitadas y pendientes de capacitacion
    para la campaña de proceso. Es decir ya no se volvera a calificar aquellas CONSULTORAS
    que hayan sido CALIFICADAS en la misma CAMPAÑA */
    EDU_PR_FILTR_CALIF_CAMPA(PSCODIGOPAIS,
                             PSCODEMPRESA,
                             PSCODPERIODO,
                             PSCODCURSO);

    /* Borrando aquellas consultoras que haya sido capacitada en el curso en anteriores campañas */
    EDU_PR_FILTR_CALIF_ANTER_CAMPA(PSCODIGOPAIS, PSCODEMPRESA, PSCODCURSO);

    /* Verificando pre-requisito de capacitacion */
    EDU_PR_FILTR_PRERE_CAPAC(PSCODIGOPAIS,
                             PSCODEMPRESA,
                             PSCODPERIODO,
                             PSCODCURSO,
                             REGPARAMCURSOS.PRE_REQU_CAPA,
                             REGPARAMCURSOS.NUM_CAMP_CUMP_PREQ);

    /* Borrando consultoras cuyo numero de invitacion NO haya excedido al valor parametrizado */
    EDU_PR_FILTR_NUMER_INVIT_DEMAN(PSCODIGOPAIS,
                                   PSCODEMPRESA,
                                   PSCODCURSO,
                                   LNNUMCAMPACURSOINVITACION);

    /* Borrando consultoras que hayan excedido la secuencia de Pedido */
    EDU_PR_FILTR_SECUE_PEDID(PSCODIGOPAIS,
                             PSCODEMPRESA,
                             PSCODCURSO,
                             PSCODPERIODO,
                             REGPARAMCURSOS.SEPE_COD_SECU_PEDI,
                             REGPARAMCURSOS.NUM_PEDI_REQU,
                             REGPARAMCURSOS.NUM_CAMP_EVAL);

    /* Verificar Indicador de Control de Morosidad
    -- Invocar a TablaMS

    /* Verificar Nivel minimo de Ventas
    Ver Constancia o Actividad */
    -- Invocar a TablaMS

    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR EDU_PR_CARGA_CONSU_APTAS_DEMAM: ' ||
                              LS_SQLERRM);
  END EDU_PR_CARGA_CONSU_APTAS_DEMAM;

  /***************************************************************************
  Descripcion : Procedimiento que realiza el proceso de calificacion de
   Aptas Automatico
  Fecha Creacion : 09/07/2007
  Autor : Carlos Bazalar
  Parametros :
   psCodigoPais : Codigo de Pais
   psCodEmpresa : Codigo de Empresa
   psCodCurso : Codigo de Curso de Capacitación
   psCodPeriodo : Campaña de Proceso
   psIndicadorBloqueo : Indicador de Bloqueo (1 Efectuar Bloqueo 0 No Efectuar Bloqueo)
   psUsuario : Usuario
  ***************************************************************************/
  PROCEDURE EDU_PR_CALIF_APTAS_AUTOM(PSCODIGOPAIS       VARCHAR2,
                                     PSCODEMPRESA       VARCHAR2,
                                     PSCODCURSO         VARCHAR2,
                                     PSCODPERIODO       VARCHAR2,
                                     PSINDICADORBLOQUEO VARCHAR2,
                                     PSUSUARIO          VARCHAR2) IS
    REGPARAMCURSOS            EDU_PARAM_CURSO_CAPAC%ROWTYPE;
    REGPARAMPROGRAMA          EDU_PARAM_PROGR_CAPAC%ROWTYPE;
    LNNUMCAMPACURSOINVITACION NUMBER;
    LNCONTADOR                NUMBER;

    LSMENSAJEERROR VARCHAR2(250) := '';
    LSINDLBEL      BAS_PAIS.IND_PAIS_MARC%TYPE;
    CONDICIONSALIR BOOLEAN := TRUE;
    CURSOR CURSORAMBITO(VSCODAMBITO VARCHAR2) IS
      SELECT A.COD_REGI, A.COD_ZONA, A.COD_CLAS
        FROM EDU_PARAM_CURSO_CAPAC_AMBIT A
       WHERE A.PAIS_COD_PAIS = PSCODIGOPAIS
         AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
         AND A.CCAP_COD_CURS_CAPA = PSCODCURSO
         AND A.AMDI_COD_AMBI_DICT = VSCODAMBITO
         AND A.EST_REGI = INDICADOR_ACTIVO;

  BEGIN
    /* borrando tablas temporales */
    DELETE FROM EDU_GTT_CLIEN_PAIS;
    DELETE FROM EDU_GTT_CLIEN;
    DELETE FROM EDU_GTT_PARAM_PROCE;

    /* obteniendo parametrizacion de programa capacitacion */
    SELECT *
      INTO REGPARAMPROGRAMA
      FROM EDU_PARAM_PROGR_CAPAC A
     WHERE A.PAIS_COD_PAIS = PSCODIGOPAIS
       AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
       AND A.COD_PROG_CAPA = '01';

    /* Obteniendo parametrizacion del curso */
    SELECT *
      INTO REGPARAMCURSOS
      FROM EDU_PARAM_CURSO_CAPAC A
     WHERE A.PAIS_COD_PAIS = PSCODIGOPAIS
       AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
       AND A.COD_CURS_CAPA = PSCODCURSO
       AND A.EST_REGI = INDICADOR_ACTIVO;

    /* Obteniendo parametrixacion de Numero de Campañas para cursar invitacion */
    IF REGPARAMCURSOS.IND_COST_CURS = 'N' THEN
      LNNUMCAMPACURSOINVITACION := REGPARAMCURSOS.NUM_CAMP_MAXI_ASRE;
      IF LNNUMCAMPACURSOINVITACION IS NULL THEN
        LNNUMCAMPACURSOINVITACION := 0;
      END IF;
    ELSE
      LNNUMCAMPACURSOINVITACION := REGPARAMCURSOS.NUM_CAMP_CUMP_PREQ;
    END IF;

    /* Verificando si el curso tiene ambito nacional */
    BEGIN
      SELECT COUNT(1)
        INTO LNCONTADOR
        FROM EDU_PARAM_CURSO_CAPAC_AMBIT A
       WHERE A.PAIS_COD_PAIS = PSCODIGOPAIS
         AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
         AND A.CCAP_COD_CURS_CAPA = PSCODCURSO
         AND A.AMDI_COD_AMBI_DICT <> '01'
         AND A.EST_REGI = INDICADOR_ACTIVO;
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        LNCONTADOR := 0;
    END;

    /* VALIDACION PREVIA A LA CALIFICACION PARA AQUELLOS PEDIDOS QUE PIDEN UN CUV CORRESPONDIENTE 23/09/2008 SB*/

    /* Valida la marca LBEL*/
    BEGIN
      SELECT A.IND_PAIS_MARC
        INTO LSINDLBEL
        FROM BAS_PAIS A
       WHERE A.COD_PAIS = PSCODIGOPAIS;
    EXCEPTION
      WHEN OTHERS THEN
        LSINDLBEL := INDICADOR_NO;

    END;

    IF (REGPARAMCURSOS.STA_CLIE = 'N' AND
       LSINDLBEL = 'LB' AND
       REGPARAMCURSOS.TICC_COD_TIPO_COST_CURS = TIPO_CURSO_MIXTO) THEN

      EDU_PKG_PROCE_COMER.EDU_PR_RECEP_CONSU_SOLIC_CURSO(PSCODIGOPAIS,
                                                         PSCODEMPRESA,
                                                         PSCODPERIODO,
                                                         PSUSUARIO);
    END IF;

    /*despues de cargar las consultoras que han solicitado CUV cargamos las establecidas
      una vez cargada ya no se vuelve a cargar
     */

          EDU_PKG_PROCE_COMER.EDU_PR_RECEP_PEDID_CONSU_ESTAB(PSCODIGOPAIS,
                                                         PSCODEMPRESA,
                                                         PSCODPERIODO,
                                                         PSUSUARIO);



    /**********************************************************************************************************/

    /* Filtrando consultoras por el AMBITO */
    IF LNCONTADOR > 0 THEN

      /* Verificando el indicador de Consultoras de Primer Pedido */
      IF REGPARAMCURSOS.STA_CLIE = 'N' OR  REGPARAMCURSOS.STA_CLIE = 'T' THEN

        /* Pasando informacion de Tabla Temporal a Tabla Global Temporary por ambito de Region */
        FOR CAMBITO IN CURSORAMBITO('02') LOOP

          IF (REGPARAMCURSOS.IND_CALI_AMBI = '1') THEN
            --iNCLUYE ALA CALIFICACION LAS REGIONES SELECIONADAS

            INSERT INTO EDU_GTT_CLIEN
              (COD_CLIE)
              SELECT A.COD_CLIE
                FROM EDU_HISTO_PEDID_CONSU A, EDU_MAEST_CLIEN B
               WHERE A.CAM_PROC = PSCODPERIODO
                 AND A.PAIS_COD_PAIS = PSCODIGOPAIS
                 AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
                 AND A.IND_PRIM_PEDI = INDICADOR_PRIMER_PEDIDO
                 AND A.EST_REGI = INDICADOR_ACTIVO
                 AND B.COD_REGI = CAMBITO.COD_REGI
                 AND B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
                 AND B.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
                 AND B.COD_CLIE = A.COD_CLIE
                 AND B.EST_REGI = INDICADOR_ACTIVO;

            /* Pasando consultoras que a pesar que ha facturado en campaña anterior no asistieron
            a la clase respectiva */
            INSERT INTO EDU_GTT_CLIEN
              (COD_CLIE)
              SELECT A.CLIE_COD_CLIE
                FROM EDU_HISTO_APTAS       A,
                     EDU_HISTO_PEDID_CONSU B,
                     EDU_MAEST_CLIEN       C
               WHERE A.CAM_ULTI_CALI_APTA < PSCODPERIODO
                 AND A.CCAP_COD_CURS_CAPA = PSCODCURSO
                 AND A.PAIS_COD_PAIS = PSCODIGOPAIS
                 AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
                 AND (A.EST_CAPA = INDICADOR_PENDIENTE OR
                     A.EST_CAPA = INDICADOR_PROGRAMADA)
                 AND A.NUM_INVI < LNNUMCAMPACURSOINVITACION
                 AND A.EST_REGI = INDICADOR_ACTIVO
                 AND B.CAM_PROC = PSCODPERIODO
                 AND B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
                 AND B.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
                 AND B.COD_CLIE = A.CLIE_COD_CLIE
                 AND B.IND_PEDI = '1'
                 AND B.IND_FACT = '0'
                 AND B.EST_REGI = INDICADOR_ACTIVO
                 AND C.PAIS_COD_PAIS = B.PAIS_COD_PAIS
                 AND C.EMCO_COD_EMPR_COME = B.EMCO_COD_EMPR_COME
                 AND C.COD_CLIE = B.COD_CLIE
                 AND C.COD_REGI = CAMBITO.COD_REGI
                 AND C.EST_REGI = INDICADOR_ACTIVO;

            /* Pasando aquellas consultoras que habiendo hecho pedido en la campaña actual no facturaron y tienen
            Numero de Invitaciones = 0 */
            INSERT INTO EDU_GTT_CLIEN
              (COD_CLIE)
              SELECT A.CLIE_COD_CLIE
                FROM EDU_HISTO_APTAS       A,
                     EDU_HISTO_PEDID_CONSU B,
                     EDU_MAEST_CLIEN       C
               WHERE A.CAM_ULTI_CALI_APTA = PSCODPERIODO
                 AND A.CCAP_COD_CURS_CAPA = PSCODCURSO
                 AND A.PAIS_COD_PAIS = PSCODIGOPAIS
                 AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
                 AND A.EST_CAPA = INDICADOR_PENDIENTE
                 AND A.NUM_INVI = 0
                 AND A.EST_REGI = INDICADOR_ACTIVO

                 AND B.CAM_PROC = PSCODPERIODO
                 AND B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
                 AND B.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
                 AND B.COD_CLIE = A.CLIE_COD_CLIE
                 AND B.IND_PEDI = '1'
                 AND B.IND_FACT = '0'
                 AND B.EST_REGI = INDICADOR_ACTIVO

                 AND C.COD_REGI = CAMBITO.COD_REGI
                 AND C.COD_CLIE = A.CLIE_COD_CLIE
                 AND C.PAIS_COD_PAIS = A.PAIS_COD_PAIS
                 AND C.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
                 AND C.EST_REGI = INDICADOR_ACTIVO;
          ELSE
            --LA EXCLUISON ES EN BLOQUE
            CONDICIONSALIR := TRUE;
            --NO INCLUYAS EN CALIFICACION REGIONES SELECIONADAS
            INSERT INTO EDU_GTT_CLIEN
              (COD_CLIE)
              SELECT A.COD_CLIE
                FROM EDU_HISTO_PEDID_CONSU A, EDU_MAEST_CLIEN B
               WHERE A.CAM_PROC = PSCODPERIODO
                 AND A.PAIS_COD_PAIS = PSCODIGOPAIS
                 AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
                 AND A.IND_PRIM_PEDI = INDICADOR_PRIMER_PEDIDO
                 AND A.EST_REGI = INDICADOR_ACTIVO
                 AND B.COD_CLIE = A.COD_CLIE
                 AND B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
                 AND B.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
                 AND NOT EXISTS
               (SELECT NULL
                        FROM EDU_PARAM_CURSO_CAPAC_AMBIT Y
                       WHERE Y.PAIS_COD_PAIS = PSCODIGOPAIS
                         AND Y.EMCO_COD_EMPR_COME = PSCODEMPRESA
                         AND B.COD_REGI = Y.COD_REGI
                         AND Y.EST_REGI = '1')
                 AND B.EST_REGI = INDICADOR_ACTIVO;

            /* Pasando consultoras que a pesar que ha facturado en campaña anterior no asistieron
            a la clase respectiva */
            INSERT INTO EDU_GTT_CLIEN
              (COD_CLIE)
              SELECT A.CLIE_COD_CLIE
                FROM EDU_HISTO_APTAS       A,
                     EDU_HISTO_PEDID_CONSU B,
                     EDU_MAEST_CLIEN       C
               WHERE A.CAM_ULTI_CALI_APTA < PSCODPERIODO
                 AND A.CCAP_COD_CURS_CAPA = PSCODCURSO
                 AND A.PAIS_COD_PAIS = PSCODIGOPAIS
                 AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
                 AND (A.EST_CAPA = INDICADOR_PENDIENTE OR
                     A.EST_CAPA = INDICADOR_PROGRAMADA)
                 AND A.NUM_INVI < LNNUMCAMPACURSOINVITACION
                 AND A.EST_REGI = INDICADOR_ACTIVO
                 AND B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
                 AND B.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
                 AND B.CAM_PROC = PSCODPERIODO
                 AND B.COD_CLIE = A.CLIE_COD_CLIE
                 AND B.IND_PEDI = '1'
                 AND B.IND_FACT = '0'
                 AND B.EST_REGI = INDICADOR_ACTIVO
                 AND C.COD_CLIE = B.COD_CLIE
                 AND C.PAIS_COD_PAIS = B.PAIS_COD_PAIS
                 AND C.EMCO_COD_EMPR_COME = B.EMCO_COD_EMPR_COME
                 AND NOT EXISTS
               (SELECT NULL
                        FROM EDU_PARAM_CURSO_CAPAC_AMBIT Y
                       WHERE Y.PAIS_COD_PAIS = PSCODIGOPAIS
                         AND Y.EMCO_COD_EMPR_COME = PSCODEMPRESA
                         AND C.COD_REGI = Y.COD_REGI
                         AND Y.EST_REGI = '1')
                 AND C.EST_REGI = INDICADOR_ACTIVO;

            /* Pasando aquellas consultoras que habiendo hecho pedido en la campaña actual no facturaron y tienen
            Numero de Invitaciones = 0 */
            INSERT INTO EDU_GTT_CLIEN
              (COD_CLIE)
              SELECT A.CLIE_COD_CLIE
                FROM EDU_HISTO_APTAS       A,
                     EDU_HISTO_PEDID_CONSU B,
                     EDU_MAEST_CLIEN       C
               WHERE A.CAM_ULTI_CALI_APTA = PSCODPERIODO
                 AND A.CCAP_COD_CURS_CAPA = PSCODCURSO
                 AND A.PAIS_COD_PAIS = PSCODIGOPAIS
                 AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
                 AND A.EST_CAPA = INDICADOR_PENDIENTE
                 AND A.NUM_INVI = 0
                 AND A.EST_REGI = INDICADOR_ACTIVO
                 AND B.CAM_PROC = PSCODPERIODO
                 AND B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
                 AND B.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
                 AND B.COD_CLIE = A.CLIE_COD_CLIE
                 AND B.IND_PEDI = '1'
                 AND B.IND_FACT = '0'
                 AND B.EST_REGI = INDICADOR_ACTIVO

                 AND C.PAIS_COD_PAIS = A.PAIS_COD_PAIS
                 AND C.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
                 AND C.COD_CLIE = A.CLIE_COD_CLIE
                    --AND C.COD_REGI <> cAmbito.Cod_Regi
                 AND NOT EXISTS
               (SELECT NULL
                        FROM EDU_PARAM_CURSO_CAPAC_AMBIT Y
                       WHERE Y.PAIS_COD_PAIS = PSCODIGOPAIS
                         AND Y.EMCO_COD_EMPR_COME = PSCODEMPRESA
                         AND C.COD_REGI = Y.COD_REGI
                         AND Y.EST_REGI = '1')
                 AND C.EST_REGI = INDICADOR_ACTIVO;

            EXIT; --when condicionSalir;--LA EXCLUISON ES EN BLOQUE

          END IF;

        END LOOP; --FIN LOOP AMBITO REGION

        /* Pasando informacion de Tabla Temporal a Tabla Global Temporary por ambito de Zona */
        FOR CAMBITO IN CURSORAMBITO('03') LOOP

          IF (REGPARAMCURSOS.IND_CALI_AMBI = '1') THEN
            --iNCLUYE ALA CALIFICACION LAS REGIONES/ZONAS SELECIONADAS
            INSERT INTO EDU_GTT_CLIEN
              (COD_CLIE)
              SELECT A.COD_CLIE
                FROM EDU_HISTO_PEDID_CONSU A, EDU_MAEST_CLIEN B
               WHERE A.CAM_PROC = PSCODPERIODO
                 AND A.PAIS_COD_PAIS = PSCODIGOPAIS
                 AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
                 AND A.IND_PRIM_PEDI = INDICADOR_PRIMER_PEDIDO
                 AND A.EST_REGI = INDICADOR_ACTIVO
                 AND B.COD_REGI = CAMBITO.COD_REGI
                 AND B.COD_ZONA = CAMBITO.COD_ZONA
                 AND B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
                 AND B.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
                 AND B.COD_CLIE = A.COD_CLIE
                 AND B.EST_REGI = INDICADOR_ACTIVO;

            /* Pasando consultoras que a pesar que ha facturado en campaña anterior no asistieron
            a la clase respectiva */
            INSERT INTO EDU_GTT_CLIEN
              (COD_CLIE)
              SELECT A.CLIE_COD_CLIE
                FROM EDU_HISTO_APTAS       A,
                     EDU_HISTO_PEDID_CONSU B,
                     EDU_MAEST_CLIEN       C
               WHERE A.CCAP_COD_CURS_CAPA = PSCODCURSO
                 AND A.PAIS_COD_PAIS = PSCODIGOPAIS
                 AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
                 AND (A.EST_CAPA = INDICADOR_PENDIENTE OR
                     A.EST_CAPA = INDICADOR_PROGRAMADA)
                 AND A.EST_CAPA = INDICADOR_PROGRAMADA
                 AND A.NUM_INVI < LNNUMCAMPACURSOINVITACION
                 AND A.EST_REGI = INDICADOR_ACTIVO
                 AND B.CAM_PROC = PSCODPERIODO
                 AND B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
                 AND B.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
                 AND B.COD_CLIE = A.CLIE_COD_CLIE
                 AND B.IND_PEDI = '1'
                 AND B.IND_FACT = '0'
                 AND B.EST_REGI = INDICADOR_ACTIVO
                 AND C.PAIS_COD_PAIS = B.PAIS_COD_PAIS
                 AND C.EMCO_COD_EMPR_COME = B.EMCO_COD_EMPR_COME
                 AND C.COD_CLIE = B.COD_CLIE
                 AND C.COD_REGI = CAMBITO.COD_REGI
                 AND C.COD_ZONA = CAMBITO.COD_ZONA
                 AND C.EST_REGI = INDICADOR_ACTIVO;

            /* Pasando aquellas consultoras que habiendo hecho pedido en la campaña actual no facturaron y tienen
            Numero de Invitaciones = 0 */
            INSERT INTO EDU_GTT_CLIEN
              (COD_CLIE)
              SELECT A.CLIE_COD_CLIE
                FROM EDU_HISTO_APTAS       A,
                     EDU_HISTO_PEDID_CONSU B,
                     EDU_MAEST_CLIEN       C
               WHERE A.CAM_ULTI_CALI_APTA = PSCODPERIODO
                 AND A.CCAP_COD_CURS_CAPA = PSCODCURSO
                 AND A.PAIS_COD_PAIS = PSCODIGOPAIS
                 AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
                 AND A.EST_CAPA = INDICADOR_PENDIENTE
                 AND A.NUM_INVI = 0
                 AND A.EST_REGI = INDICADOR_ACTIVO
                 AND B.CAM_PROC = PSCODPERIODO
                 AND B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
                 AND B.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
                 AND B.COD_CLIE = A.CLIE_COD_CLIE
                 AND B.IND_PEDI = '1'
                 AND B.IND_FACT = '0'
                 AND B.EST_REGI = INDICADOR_ACTIVO

                 AND C.PAIS_COD_PAIS = A.PAIS_COD_PAIS
                 AND C.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
                 AND C.COD_CLIE = A.CLIE_COD_CLIE
                 AND C.COD_REGI = CAMBITO.COD_REGI
                 AND C.COD_ZONA = CAMBITO.COD_ZONA
                 AND C.EST_REGI = INDICADOR_ACTIVO;
          ELSE
            --NO INCLUYAS EN CALIFICACION REGIONES / ZONAS SELECIONADAS
            INSERT INTO EDU_GTT_CLIEN
              (COD_CLIE)
              SELECT A.COD_CLIE
                FROM EDU_HISTO_PEDID_CONSU A, EDU_MAEST_CLIEN B
               WHERE A.CAM_PROC = PSCODPERIODO
                 AND A.PAIS_COD_PAIS = PSCODIGOPAIS
                 AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
                 AND A.IND_PRIM_PEDI = INDICADOR_PRIMER_PEDIDO
                 AND A.EST_REGI = INDICADOR_ACTIVO
                    -- AND B.COD_REGI <> cAmbito.Cod_Regi
                    --AND B.COD_ZONA <> cAmbito.Cod_Zona
                 AND NOT EXISTS
               (SELECT NULL
                        FROM EDU_PARAM_CURSO_CAPAC_AMBIT Y
                       WHERE Y.PAIS_COD_PAIS = PSCODIGOPAIS
                         AND Y.EMCO_COD_EMPR_COME = PSCODEMPRESA
                         AND B.COD_ZONA = Y.COD_ZONA
                         AND Y.EST_REGI = '1')
                 AND B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
                 AND B.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
                 AND B.COD_CLIE = A.COD_CLIE
                 AND B.EST_REGI = INDICADOR_ACTIVO;

            /* Pasando consultoras que a pesar que ha facturado en campaña anterior no asistieron
            a la clase respectiva */
            INSERT INTO EDU_GTT_CLIEN
              (COD_CLIE)
              SELECT A.CLIE_COD_CLIE
                FROM EDU_HISTO_APTAS       A,
                     EDU_HISTO_PEDID_CONSU B,
                     EDU_MAEST_CLIEN       C
               WHERE A.CCAP_COD_CURS_CAPA = PSCODCURSO
                 AND A.PAIS_COD_PAIS = PSCODIGOPAIS
                 AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
                 AND (A.EST_CAPA = INDICADOR_PENDIENTE OR
                     A.EST_CAPA = INDICADOR_PROGRAMADA)
                 AND A.EST_CAPA = INDICADOR_PROGRAMADA
                 AND A.NUM_INVI < LNNUMCAMPACURSOINVITACION
                 AND A.EST_REGI = INDICADOR_ACTIVO
                 AND B.CAM_PROC = PSCODPERIODO
                 AND B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
                 AND B.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
                 AND B.COD_CLIE = A.CLIE_COD_CLIE
                 AND B.IND_PEDI = '1'
                 AND B.IND_FACT = '0'
                 AND B.EST_REGI = INDICADOR_ACTIVO
                 AND C.PAIS_COD_PAIS = B.PAIS_COD_PAIS
                 AND C.EMCO_COD_EMPR_COME = B.EMCO_COD_EMPR_COME
                 AND C.COD_CLIE = B.COD_CLIE
                    -- AND C.COD_REGI <> cAmbito.Cod_Regi
                    --AND C.COD_ZONA <> cAmbito.Cod_Zona
                 AND NOT EXISTS
               (SELECT NULL
                        FROM EDU_PARAM_CURSO_CAPAC_AMBIT Y
                       WHERE Y.PAIS_COD_PAIS = PSCODIGOPAIS
                         AND Y.EMCO_COD_EMPR_COME = PSCODEMPRESA
                         AND C.COD_ZONA = Y.COD_ZONA
                         AND Y.EST_REGI = '1')
                 AND C.EST_REGI = INDICADOR_ACTIVO;

            /* Pasando aquellas consultoras que habiendo hecho pedido en la campaña actual no facturaron y tienen
            Numero de Invitaciones = 0 */
            INSERT INTO EDU_GTT_CLIEN
              (COD_CLIE)
              SELECT A.CLIE_COD_CLIE
                FROM EDU_HISTO_APTAS       A,
                     EDU_HISTO_PEDID_CONSU B,
                     EDU_MAEST_CLIEN       C
               WHERE A.CAM_ULTI_CALI_APTA = PSCODPERIODO
                 AND A.CCAP_COD_CURS_CAPA = PSCODCURSO
                 AND A.PAIS_COD_PAIS = PSCODIGOPAIS
                 AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
                 AND A.EST_CAPA = INDICADOR_PENDIENTE
                 AND A.NUM_INVI = 0
                 AND A.EST_REGI = INDICADOR_ACTIVO
                 AND B.CAM_PROC = PSCODPERIODO
                 AND B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
                 AND B.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
                 AND B.COD_CLIE = A.CLIE_COD_CLIE
                 AND B.IND_PEDI = '1'
                 AND B.IND_FACT = '0'
                 AND B.EST_REGI = INDICADOR_ACTIVO

                 AND C.PAIS_COD_PAIS = A.PAIS_COD_PAIS
                 AND C.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
                 AND C.COD_CLIE = A.CLIE_COD_CLIE
                    -- AND C.COD_REGI <> cAmbito.Cod_Regi
                    --AND C.COD_ZONA <> cAmbito.Cod_Zona
                 AND NOT EXISTS
               (SELECT NULL
                        FROM EDU_PARAM_CURSO_CAPAC_AMBIT Y
                       WHERE Y.PAIS_COD_PAIS = PSCODIGOPAIS
                         AND Y.EMCO_COD_EMPR_COME = PSCODEMPRESA
                         AND C.COD_ZONA = Y.COD_ZONA
                         AND Y.EST_REGI = '1')
                 AND C.EST_REGI = INDICADOR_ACTIVO;

            EXIT; --SALIMOS DEL FOR

          END IF;

        END LOOP;

      --ELSE
      END IF;-- NUEVAS O TODAS
        /* En caso sea curso sin indicador de Consultoras de Primer Pedido */
      IF (REGPARAMCURSOS.STA_CLIE = 'R' OR  REGPARAMCURSOS.STA_CLIE = 'T') THEN
        /* Pasando informacion de Tabla Temporal a Tabla Global Temporary por ambito de Region */
        FOR CAMBITO IN CURSORAMBITO('02') LOOP

          IF (REGPARAMCURSOS.IND_CALI_AMBI = '1') THEN
            --iNCLUYE ALA CALIFICACION LAS REGIONES SELECIONADAS
            INSERT INTO EDU_GTT_CLIEN
              (COD_CLIE)
              SELECT A.COD_CLIE
                FROM EDU_HISTO_PEDID_CONSU A, EDU_MAEST_CLIEN B
               WHERE A.CAM_PROC = PSCODPERIODO
                 AND A.PAIS_COD_PAIS = PSCODIGOPAIS
                 AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
                 AND A.IND_PEDI = '1'
                 AND A.IND_FACT = '0'
                 AND A.EST_REGI = INDICADOR_ACTIVO
                 AND B.COD_REGI = CAMBITO.COD_REGI
                 AND B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
                 AND B.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
                 AND B.COD_CLIE = A.COD_CLIE
                 AND B.EST_REGI = INDICADOR_ACTIVO
                 AND IND_PRIM_PEDI = '0';

          ELSE
            --EXCLUYE ALA CALIFICACION LAS REGIONES SELECIONADAS
            INSERT INTO EDU_GTT_CLIEN
              (COD_CLIE)
              SELECT A.COD_CLIE
                FROM EDU_HISTO_PEDID_CONSU A, EDU_MAEST_CLIEN B
               WHERE A.CAM_PROC = PSCODPERIODO
                 AND A.PAIS_COD_PAIS = PSCODIGOPAIS
                 AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
                 AND A.IND_PEDI = '1'
                 AND A.IND_FACT = '0'
                 AND A.EST_REGI = INDICADOR_ACTIVO
                 AND A.IND_PRIM_PEDI ='0'
                    --AND B.COD_REGI <> cAmbito.Cod_Regi
                 AND NOT EXISTS
               (SELECT NULL
                        FROM EDU_PARAM_CURSO_CAPAC_AMBIT Y
                       WHERE Y.PAIS_COD_PAIS = PSCODIGOPAIS
                         AND Y.EMCO_COD_EMPR_COME = PSCODEMPRESA
                         AND B.COD_REGI = Y.COD_REGI
                         AND Y.EST_REGI = '1')
                 AND B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
                 AND B.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
                 AND B.COD_CLIE = A.COD_CLIE
                 AND B.EST_REGI = INDICADOR_ACTIVO;

            EXIT; --SALIMOS DEL FOR

          END IF;
        END LOOP;

        /* Pasando informacion de Tabla Temporal a Tabla Global Temporary por ambito de Zona */
        FOR CAMBITO IN CURSORAMBITO('03') LOOP

          IF (REGPARAMCURSOS.IND_CALI_AMBI = '1') THEN
            --iNCLUYE ALA CALIFICACION LAS REGIONES/ZONAS SELECIONADAS
            INSERT INTO EDU_GTT_CLIEN
              (COD_CLIE)
              SELECT A.COD_CLIE
                FROM EDU_HISTO_PEDID_CONSU A, EDU_MAEST_CLIEN B
               WHERE A.CAM_PROC = PSCODPERIODO
                 AND A.PAIS_COD_PAIS = PSCODIGOPAIS
                 AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
                 AND A.IND_PEDI = '1'
                 AND A.IND_FACT = '0'
                 AND A.EST_REGI = INDICADOR_ACTIVO
                 AND B.COD_REGI = CAMBITO.COD_REGI
                 AND B.COD_ZONA = CAMBITO.COD_ZONA
                 AND B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
                 AND B.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
                 AND B.COD_CLIE = A.COD_CLIE
                 AND B.EST_REGI = INDICADOR_ACTIVO
                 AND A.IND_PRIM_PEDI ='0';
          ELSE
            --EXCLUYE ALA CALIFICACION LAS REGIONES/ZONAS SELECIONADAS
            INSERT INTO EDU_GTT_CLIEN
              (COD_CLIE)
              SELECT A.COD_CLIE
                FROM EDU_HISTO_PEDID_CONSU A, EDU_MAEST_CLIEN B
               WHERE A.CAM_PROC = PSCODPERIODO
                 AND A.PAIS_COD_PAIS = PSCODIGOPAIS
                 AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
                 AND A.IND_PEDI = '1'
                 AND A.IND_FACT = '0'
                 AND A.EST_REGI = INDICADOR_ACTIVO
                 AND A.IND_PRIM_PEDI ='0'
                    -- AND B.COD_REGI <> cAmbito.Cod_Regi
                    -- AND B.COD_ZONA <> cAmbito.Cod_Zona
                 AND NOT EXISTS
               (SELECT NULL
                        FROM EDU_PARAM_CURSO_CAPAC_AMBIT Y
                       WHERE Y.PAIS_COD_PAIS = PSCODIGOPAIS
                         AND Y.EMCO_COD_EMPR_COME = PSCODEMPRESA
                         AND B.COD_ZONA = Y.COD_ZONA
                         AND Y.EST_REGI = '1')
                 AND B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
                 AND B.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
                 AND B.COD_CLIE = A.COD_CLIE
                 AND B.EST_REGI = INDICADOR_ACTIVO;

            EXIT;
          END IF;

        END LOOP;

      END IF;--de regulares todas

    ELSE
      /* SIN FILTRO DE AMBITO */
      /* Verificando el indicador de Consultoras de Primer Pedido */
      IF REGPARAMCURSOS.STA_CLIE = 'N' OR REGPARAMCURSOS.STA_CLIE = 'T' THEN

        /* 1.- Pasando informacion de consultoras a tabla temporal */
        INSERT INTO EDU_GTT_CLIEN
          (COD_CLIE)
          SELECT A.COD_CLIE
            FROM EDU_HISTO_PEDID_CONSU A
           WHERE A.CAM_PROC = PSCODPERIODO
             AND A.PAIS_COD_PAIS = PSCODIGOPAIS
             AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
             AND A.IND_PRIM_PEDI = '1'
             AND A.IND_FACT = '0'
             AND A.EST_REGI = INDICADOR_ACTIVO;

        /* 2.- Pasando consultoras que a pesar que ha facturado en campaña anterior no asistieron
        a la clase respectiva */
        INSERT INTO EDU_GTT_CLIEN
          (COD_CLIE)
          SELECT A.CLIE_COD_CLIE
            FROM EDU_HISTO_APTAS A, EDU_HISTO_PEDID_CONSU B
           WHERE A.CAM_ULTI_CALI_APTA < PSCODPERIODO
             AND A.CCAP_COD_CURS_CAPA = PSCODCURSO
             AND A.PAIS_COD_PAIS = PSCODIGOPAIS
             AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
             AND (A.EST_CAPA = INDICADOR_PENDIENTE OR
                 A.EST_CAPA = INDICADOR_PROGRAMADA)
             AND A.NUM_INVI < LNNUMCAMPACURSOINVITACION
             AND A.EST_REGI = INDICADOR_ACTIVO
             AND B.CAM_PROC = PSCODPERIODO
             AND B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
             AND B.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
             AND B.COD_CLIE = A.CLIE_COD_CLIE
             AND B.IND_PEDI = '1'
             AND B.IND_FACT = '0'
                --AND B.IND_PRIM_PEDI = '1'
             AND EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_CODIG_PERIO(EDU_PKG_CALIF.EDU_FN_INICI_PEDID_APTAS(PSCODIGOPAIS,
                                                                                                     PSCODEMPRESA,
                                                                                                     PSCODCURSO,
                                                                                                     A.CLIE_COD_CLIE,
                                                                                                     PSCODPERIODO),
                                                              1) >=
                 PSCODPERIODO
             AND B.EST_REGI = INDICADOR_ACTIVO;

        /* 3.- Pasando aquellas consultoras que habiendo hecho pedido en la campaña actual no facturaron y tienen
        Numero de Invitaciones = 0 */
        INSERT INTO EDU_GTT_CLIEN
          (COD_CLIE)
          SELECT A.CLIE_COD_CLIE
            FROM EDU_HISTO_APTAS A, EDU_HISTO_PEDID_CONSU B
           WHERE A.CAM_ULTI_CALI_APTA = PSCODPERIODO
             AND A.CCAP_COD_CURS_CAPA = PSCODCURSO
             AND A.PAIS_COD_PAIS = PSCODIGOPAIS
             AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
             AND A.EST_CAPA = INDICADOR_PENDIENTE
             AND A.NUM_INVI = 0
             AND A.EST_REGI = INDICADOR_ACTIVO
             AND B.CAM_PROC = PSCODPERIODO
             AND B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
             AND B.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
             AND B.COD_CLIE = A.CLIE_COD_CLIE
             AND B.IND_PEDI = '1'
             AND B.IND_FACT = '0'
             AND B.EST_REGI = INDICADOR_ACTIVO;

      --ELSE
      end if;
      --si es regular o todas
      IF REGPARAMCURSOS.STA_CLIE = 'R' OR REGPARAMCURSOS.STA_CLIE = 'T' THEN
        /* Pasando informacion de consultoras a tabla temporal */
        INSERT INTO EDU_GTT_CLIEN
          (COD_CLIE)
          SELECT A.COD_CLIE
            FROM EDU_HISTO_PEDID_CONSU A
           WHERE A.CAM_PROC = PSCODPERIODO
             AND A.PAIS_COD_PAIS = PSCODIGOPAIS
             AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
             AND A.IND_PEDI = '1'
             AND A.IND_FACT = '0'
             AND A.IND_PRIM_PEDI ='0'
             AND A.EST_REGI = INDICADOR_ACTIVO;
      END IF;

    END IF;

    /* Pasando lista sin duplicados de clientes a tabla temporal */
    INSERT INTO EDU_GTT_CLIEN_PAIS
      (COD_PAIS, COD_EMPR_COME, COD_CLIE, COD_CURS_CAPA)
      SELECT DISTINCT PSCODIGOPAIS, PSCODEMPRESA, A.COD_CLIE, PSCODCURSO
        FROM EDU_GTT_CLIEN A;

    /* Si esta activado el indicador de Posible Egreso en Planilla */
    IF (REGPARAMPROGRAMA.IND_POSI_EGRE = INDICADOR_ACTIVO) THEN
      EDU_PR_PROCE_POSIB_EGRESO(PSCODIGOPAIS,
                                PSCODEMPRESA,
                                PSCODPERIODO,
                                PSCODCURSO,
                                REGPARAMCURSOS.PRE_REQU_CAPA,
                                REGPARAMCURSOS.IND_COST_CURS,
                                LNNUMCAMPACURSOINVITACION);
    END IF;

    /*Proceso de registro de planillas aun no procesadas marcandolas en cusro dictado*/
    IF (REGPARAMPROGRAMA.IND_REGI_PLAN_NPRO = INDICADOR_ACTIVO) THEN
      EDU_PKG_PROCE_GENER.EDU_PR_REGIS_PLANI_NPROC(PSCODIGOPAIS,
                                                   PSCODEMPRESA,
                                                   PSCODPERIODO,
                                                   REGPARAMPROGRAMA.NUM_MINI_PEDI_PLAN_NPRO,
                                                   PSUSUARIO,
                                                   LSMENSAJEERROR);
    END IF;

    --Cierra Cursos de Dictados vigentes segun cronograma de regiones uq inican facturacion
    EDU_PKG_PROCE_GENER.EDU_PR_CIERR_CURSO_DICTA(PSCODIGOPAIS,
                                                 PSCODEMPRESA,
                                                 PSCODPERIODO,
                                                 PSUSUARIO);

    --Cierra Cronograma vigentes segun cronograma de regiones uq inican facturacion
    EDU_PKG_PROCE_GENER.EDU_PR_CIERR_CRONO_DICTA(PSCODIGOPAIS,
                                                 PSCODEMPRESA,
                                                 PSCODPERIODO,
                                                 PSUSUARIO);

    /* INVOCANDO PROCESO DE BLOQUEO
    /* Solo para cursos con indicador activo de Primer Pedido (NUEVAS)Y MARCA LBEL */
    IF (REGPARAMCURSOS.STA_CLIE = 'N' AND
       LSINDLBEL = 'LB' AND REGPARAMPROGRAMA.IND_PROC_BLOQ = '1') THEN
      EDU_PR_PROCE_BLOQU_CONSU(PSCODIGOPAIS,
                               PSCODEMPRESA,
                               PSCODPERIODO,
                               PSCODCURSO,
                               LNNUMCAMPACURSOINVITACION,
                               PSUSUARIO);
    END IF;

    --Si el curso no tine prerequisito e indicador de desbloque activo, invocar proceso de desbloqueo
    IF (REGPARAMCURSOS.STA_CLIE = 'N' AND
       LSINDLBEL = 'LB' AND REGPARAMPROGRAMA.IND_PROC_DESB = '1') THEN
      EDU_PR_PROCE_DESBL_CONSU(PSCODIGOPAIS,
                               PSCODEMPRESA,
                               PSCODPERIODO,
                               PSCODCURSO,
                               REGPARAMCURSOS.TICC_COD_TIPO_COST_CURS,
                               PSUSUARIO);
    END IF;

    /* Borrando aquellas consultoras que hayan sido bloqueadas previamente */
    EDU_PR_FILTR_CLIEN_BLOQU(PSCODIGOPAIS, PSCODEMPRESA);

    /* BORRANDO aquellas consultoras que hayan sido invitadas y pendientes de capacitacion
    para la campaña de proceso. Es decir ya no se volvera a calificar aquellas CONSULTORAS
    que hayan sido CALIFICADAS en la misma CAMPAÑA con Nro de Invitacion Mayor a 0 */
    EDU_PR_FILTR_CALIF_CAMPA(PSCODIGOPAIS,
                             PSCODEMPRESA,
                             PSCODPERIODO,
                             PSCODCURSO);

    /* Borrando aquellas consultoras que haya sido capacitada en el curso en anteriores campañas */
    EDU_PR_FILTR_CALIF_ANTER_CAMPA(PSCODIGOPAIS, PSCODEMPRESA, PSCODCURSO);

    /* Verificando pre-requisito de capacitacion */
    EDU_PR_FILTR_PRERE_CAPAC(PSCODIGOPAIS,
                             PSCODEMPRESA,
                             PSCODPERIODO,
                             PSCODCURSO,
                             REGPARAMCURSOS.PRE_REQU_CAPA,
                             REGPARAMCURSOS.NUM_CAMP_CUMP_PREQ);

    /* Borrando consultoras cuyo numero de invitacion haya excedido al valor parametrizado */
    EDU_PR_FILTR_NUMER_INVIT(PSCODIGOPAIS,
                             PSCODEMPRESA,
                             PSCODCURSO,
                             LNNUMCAMPACURSOINVITACION);

    /* Borrando consultoras que hayan excedido la secuencia de Pedido */
    EDU_PR_FILTR_SECUE_PEDID(PSCODIGOPAIS,
                             PSCODEMPRESA,
                             PSCODCURSO,
                             PSCODPERIODO,
                             REGPARAMCURSOS.SEPE_COD_SECU_PEDI,
                             REGPARAMCURSOS.NUM_PEDI_REQU,
                             REGPARAMCURSOS.NUM_CAMP_EVAL);

    /* Verificar Indicador de Control de Morosidad
    -- Invocar a TablaMS

    /* Verificar Nivel minimo de Ventas
    Ver Constancia o Actividad */
    -- Invocar a TablaMS

    /* Invocando proceso que busca consultoras con planillas huerfanas */
    EDU_PR_CONSU_PLANI_NREGIS(PSCODIGOPAIS,
                              PSCODEMPRESA,
                              PSCODCURSO,
                              PSCODPERIODO,
                              PSUSUARIO);

    /* Invocando al Proceso que actualiza el Registro Historico de Aptas */
    EDU_PR_REGIS_CALIF_APTAS(PSCODIGOPAIS,
                             PSCODEMPRESA,
                             PSCODCURSO,
                             PSCODPERIODO,
                             'A',
                             PSUSUARIO);

    /* Proceso que actualiza el curso Mixo para aquellas consultoras que han solicitado un CUV*/
    --SOLO SI ES LE PRIMER CURSO
    IF (REGPARAMCURSOS.IND_CALI_APTA_PRIM_PEDI = INDICADOR_SI AND
       LSINDLBEL = 'LB' AND
       REGPARAMCURSOS.TICC_COD_TIPO_COST_CURS = TIPO_CURSO_MIXTO) THEN
      EDU_PKG_CALIF.EDU_PR_ACTUA_CURSO_MIXTO(PSCODIGOPAIS,
                                             PSCODEMPRESA,
                                             PSCODPERIODO,
                                             PSCODCURSO,
                                             PSUSUARIO);
    END IF;

    --PROCESO Q SE ENCARGA DE CALIFICAAR ALAS EXONERADAS , CUYA SECUENCIA HA SIDO EXONERADO
    --Y CUYO CURSO ES MIXTO
    EDU_PR_PROCE_CALIF_CONSU_EXONE(PSCODIGOPAIS,
                                   PSCODEMPRESA,
                                   PSCODPERIODO,
                                   PSCODCURSO,
                                   REGPARAMCURSOS.TICC_COD_TIPO_COST_CURS,
                                   PSUSUARIO);
    /**/
    /* borrando tablas temporales */
    DELETE FROM EDU_GTT_CLIEN_PAIS;
    DELETE FROM EDU_GTT_CLIEN;

    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR EDU_PR_CALIF_APTAS_AUTOM: ' ||
                              LS_SQLERRM);
  END EDU_PR_CALIF_APTAS_AUTOM;

  /***************************************************************************
  Descripcion : Procedimiento que borra aquellas consultoras que hayan
   sido bloqueadas previamente
  Fecha Creacion : 03/03/2008
  Autor : Carlos Bazalar
  Parametros :
   psCodigoPais : Codigo de Pais
   psCodEmpresa : Codigo de Empresa
  ***************************************************************************/
  PROCEDURE EDU_PR_FILTR_CLIEN_BLOQU(PSCODIGOPAIS VARCHAR2,
                                     PSCODEMPRESA VARCHAR2) IS

  BEGIN
    DELETE FROM EDU_GTT_CLIEN_PAIS A
     WHERE A.COD_PAIS = PSCODIGOPAIS
       AND A.COD_EMPR_COME = PSCODEMPRESA
       AND EXISTS (SELECT X.CLIE_COD_CLIE
              FROM EDU_HISTO_BLOQU_CONSU X
             WHERE X.PAIS_COD_PAIS = PSCODIGOPAIS
               AND X.EMCO_COD_EMPR_COME = PSCODEMPRESA
               AND X.CLIE_COD_CLIE = A.COD_CLIE
               AND X.EST_REGI = INDICADOR_ACTIVO
               AND X.EST_BLOQ = 'B');
  EXCEPTION
    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR EDU_PR_FILTR_CLIEN_BLOQU: ' ||
                              LS_SQLERRM);
  END EDU_PR_FILTR_CLIEN_BLOQU;

  /***************************************************************************
  Descripcion : Procedimiento que borra aquellas consultoras que hayan sido invitadas y
   pendientes de capacitacion para la campaña de proceso.
   Es decir ya no se volvera a calificar aquellas CONSULTORAS
   que hayan sido CALIFICADAS en la misma CAMPAÑA y con Nro de Invitacion Mayor a 0
  Fecha Creacion : 02/10/2007
  Autor : Carlos Bazalar
  Parametros :
   psCodigoPais : Codigo de Pais
   psCodEmpresa : Codigo de Empresa
   psCodPeriodo : Campaña de Proceso
   psCodCurso : Codigo de Curso
  ***************************************************************************/
  PROCEDURE EDU_PR_FILTR_CALIF_CAMPA(PSCODIGOPAIS VARCHAR2,
                                     PSCODEMPRESA VARCHAR2,
                                     PSCODPERIODO VARCHAR2,
                                     PSCODCURSO   VARCHAR2) IS

  BEGIN
    DELETE FROM EDU_GTT_CLIEN_PAIS A
     WHERE A.COD_PAIS = PSCODIGOPAIS
       AND A.COD_EMPR_COME = PSCODEMPRESA
       AND A.COD_CURS_CAPA = PSCODCURSO
       AND EXISTS (SELECT X.CLIE_COD_CLIE
              FROM EDU_HISTO_APTAS X
             WHERE X.CAM_ULTI_CALI_APTA >= PSCODPERIODO
               AND X.CCAP_COD_CURS_CAPA = PSCODCURSO
               AND X.PAIS_COD_PAIS = PSCODIGOPAIS
               AND X.EMCO_COD_EMPR_COME = PSCODEMPRESA
               AND X.CLIE_COD_CLIE = A.COD_CLIE
               AND X.NUM_INVI > 0
               AND X.EST_REGI = INDICADOR_ACTIVO);
  EXCEPTION
    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR EDU_PR_FILTR_CALIF_CAMPA: ' ||
                              LS_SQLERRM);
  END EDU_PR_FILTR_CALIF_CAMPA;

  /***************************************************************************
  Descripcion : Procedimiento que borra aquellas consultoras que haya sido
   capacitada en el curso en anteriores campañas
  Fecha Creacion : 02/10/2007
  Autor : Carlos Bazalar
  Parametros :
   psCodigoPais : Codigo de Pais
   psCodEmpresa : Codigo de Empresa
   psCodCurso : Codigo de Curso de Capacitacion
  ***************************************************************************/
  PROCEDURE EDU_PR_FILTR_CALIF_ANTER_CAMPA(PSCODIGOPAIS VARCHAR2,
                                           PSCODEMPRESA VARCHAR2,
                                           PSCODCURSO   VARCHAR2) IS

  BEGIN
    /* Borrando aquellas consultoras que haya sido capacitada en el curso en anteriores campañas */
    DELETE FROM EDU_GTT_CLIEN_PAIS A
     WHERE A.COD_PAIS = PSCODIGOPAIS
       AND A.COD_EMPR_COME = PSCODEMPRESA
       AND A.COD_CURS_CAPA = PSCODCURSO
       AND EXISTS (SELECT X.CLIE_COD_CLIE
              FROM EDU_HISTO_CAPAC_DETAL X
             WHERE X.CLIE_COD_CLIE = A.COD_CLIE
               AND X.CCAP_COD_CURS_CAPA = PSCODCURSO
               AND X.PAIS_COD_PAIS = PSCODIGOPAIS
               AND X.EMCO_COD_EMPR_COME = PSCODEMPRESA
               AND X.EST_REGI = INDICADOR_ACTIVO);

  EXCEPTION
    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR EDU_PR_FILTR_CALIF_ANTER_CAMPA: ' ||
                              LS_SQLERRM);
  END EDU_PR_FILTR_CALIF_ANTER_CAMPA;

  /***************************************************************************
  Descripcion : Procedimiento que borra a aquellas consultoras que no hayan cumplido
   curso pre-requisito de capacitacion
  Fecha Creacion : 02/10/2007
  Autor : Carlos Bazalar
  Parametros :
   psCodigoPais : Codigo de Pais
   psCodEmpresa : Codigo de Empresa
   psCodPeriodo : Codigo de Periodo (Campaña de Proceso)
   psCodCurso : Codigo de Curso
   psCursoPreRequisito : Codigo de Curso de Capacitacion Pre-Requisito
   pnNumCampaPreRequisito : Numero de Campañas maxima de Pre-requisito
  ***************************************************************************/
  PROCEDURE EDU_PR_FILTR_PRERE_CAPAC(PSCODIGOPAIS           VARCHAR2,
                                     PSCODEMPRESA           VARCHAR2,
                                     PSCODPERIODO           VARCHAR2,
                                     PSCODCURSO             VARCHAR2,
                                     PSCURSOPREREQUISITO    VARCHAR2,
                                     PNNUMCAMPAPREREQUISITO NUMBER) IS

  BEGIN
    IF PSCURSOPREREQUISITO IS NOT NULL AND PSCURSOPREREQUISITO <> '00' THEN
      DELETE FROM EDU_GTT_CLIEN_PAIS A
       WHERE A.COD_PAIS = PSCODIGOPAIS
         AND A.COD_EMPR_COME = PSCODEMPRESA
         AND A.COD_CURS_CAPA = PSCODCURSO
         AND NOT EXISTS
       (SELECT X.CLIE_COD_CLIE
                FROM EDU_HISTO_CAPAC_DETAL X
               WHERE X.PAIS_COD_PAIS = PSCODIGOPAIS
                 AND X.EMCO_COD_EMPR_COME = PSCODEMPRESA
                 AND X.CCAP_COD_CURS_CAPA = PSCURSOPREREQUISITO
                 AND X.CLIE_COD_CLIE = A.COD_CLIE
                 AND X.EST_REGI = INDICADOR_ACTIVO);

      -- Verificando nro de campañas de cumplido el pre-requisito
      IF PNNUMCAMPAPREREQUISITO IS NOT NULL AND PNNUMCAMPAPREREQUISITO <> 0 THEN
        DELETE FROM EDU_GTT_CLIEN_PAIS A
         WHERE A.COD_PAIS = PSCODIGOPAIS
           AND A.COD_EMPR_COME = PSCODEMPRESA
           AND A.COD_CURS_CAPA = PSCODCURSO
           AND EXISTS
         (SELECT X.CLIE_COD_CLIE
                  FROM EDU_HISTO_CAPAC_DETAL X
                 WHERE X.PAIS_COD_PAIS = PSCODIGOPAIS
                   AND X.EMCO_COD_EMPR_COME = PSCODEMPRESA
                   AND X.CCAP_COD_CURS_CAPA = PSCURSOPREREQUISITO
                   AND X.CLIE_COD_CLIE = A.COD_CLIE
                   AND EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_CODIG_PERIO(X.CAM_CAPA,
                                                                    PNNUMCAMPAPREREQUISITO) >
                       PSCODPERIODO
                   AND X.EST_REGI = INDICADOR_ACTIVO);
      END IF;
    END IF;
  EXCEPTION
    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR EDU_PR_FILTR_PRERE_CAPAC: ' ||
                              LS_SQLERRM);
  END EDU_PR_FILTR_PRERE_CAPAC;

  /***************************************************************************
  Descripcion : Procedimiento que borra a aquellas consultoras
   cuyo numero de invitacion haya excedido al valor parametrizado
  Fecha Creacion : 02/10/2007
  Autor : Carlos Bazalar
  Parametros :
   psCodigoPais : Codigo de Pais
   psCodEmpresa : Codigo de Empresa
   psCodCurso : Codigo de Curso
   pnNumCampaCursoInvitacion : Numero maximo de Invitaciones
  ***************************************************************************/
  PROCEDURE EDU_PR_FILTR_NUMER_INVIT(PSCODIGOPAIS              VARCHAR2,
                                     PSCODEMPRESA              VARCHAR2,
                                     PSCODCURSO                VARCHAR2,
                                     PNNUMCAMPACURSOINVITACION NUMBER) IS
    LSINDICADORPROCESOBLOQUEO EDU_PARAM_PROGR_CAPAC.IND_PROC_BLOQ%TYPE;
    LSINDICADORPRIMERPEDIDO   EDU_PARAM_CURSO_CAPAC.IND_CALI_APTA_PRIM_PEDI%TYPE;
  BEGIN

    SELECT A.IND_PROC_BLOQ
      INTO LSINDICADORPROCESOBLOQUEO
      FROM EDU_PARAM_PROGR_CAPAC A
     WHERE A.PAIS_COD_PAIS = PSCODIGOPAIS
       AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
       AND A.COD_PROG_CAPA = '01';

    SELECT A.STA_CLIE
      INTO LSINDICADORPRIMERPEDIDO
      FROM EDU_PARAM_CURSO_CAPAC A
     WHERE A.PAIS_COD_PAIS = PSCODIGOPAIS
       AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
       AND A.COD_CURS_CAPA = PSCODCURSO
       AND A.EST_REGI = INDICADOR_ACTIVO;

    IF PNNUMCAMPACURSOINVITACION > 0 THEN

      IF (LSINDICADORPROCESOBLOQUEO = '0') THEN
        --si no hay bloquo
        DELETE FROM EDU_GTT_CLIEN_PAIS A
         WHERE A.COD_PAIS = PSCODIGOPAIS
           AND A.COD_EMPR_COME = PSCODEMPRESA
           AND A.COD_CURS_CAPA = PSCODCURSO
           AND EXISTS (SELECT X.CLIE_COD_CLIE
                  FROM EDU_HISTO_APTAS X
                 WHERE X.CCAP_COD_CURS_CAPA = PSCODCURSO
                   AND X.CLIE_COD_CLIE = A.COD_CLIE
                   AND X.PAIS_COD_PAIS = PSCODIGOPAIS
                   AND X.EMCO_COD_EMPR_COME = PSCODEMPRESA
                   AND X.NUM_INVI >= PNNUMCAMPACURSOINVITACION
                   AND X.EST_REGI = INDICADOR_ACTIVO);
      ELSE
        --si hay bloqueo, elimina las bloquedas de primer pedido
        IF (LSINDICADORPRIMERPEDIDO = 'N') THEN--NUEVAS
          DELETE FROM EDU_GTT_CLIEN_PAIS A
           WHERE A.COD_PAIS = PSCODIGOPAIS
             AND A.COD_EMPR_COME = PSCODEMPRESA
             AND A.COD_CURS_CAPA = PSCODCURSO
             AND EXISTS
           (SELECT X.CLIE_COD_CLIE
                    FROM EDU_HISTO_APTAS X, EDU_HISTO_BLOQU_CONSU Y
                   WHERE X.CCAP_COD_CURS_CAPA = PSCODCURSO
                     AND X.CLIE_COD_CLIE = A.COD_CLIE
                     AND X.PAIS_COD_PAIS = PSCODIGOPAIS
                     AND X.EMCO_COD_EMPR_COME = PSCODEMPRESA
                     AND X.NUM_INVI >= PNNUMCAMPACURSOINVITACION
                        --AND (X.IND_CURS_COST != INDICADOR_SI OR (X.IND_CURS_COST = INDICADOR_SI AND NVL(pnNumOpcionesCapacitacion,0) > 0 AND NVL(X.NUM_OPCI_ASIS_CAPA,0) > 0 AND NVL(X.NUM_OPCI_ASIS_CAPA,0) < pnNumOpcionesCapacitacion ))
                     AND X.EST_REGI = INDICADOR_ACTIVO
                     AND X.PAIS_COD_PAIS = Y.PAIS_COD_PAIS
                     AND X.EMCO_COD_EMPR_COME = Y.EMCO_COD_EMPR_COME
                     AND X.CLIE_COD_CLIE = Y.CLIE_COD_CLIE
                     AND Y.EST_BLOQ = 'B'
                     AND Y.EST_REGI = INDICADOR_ACTIVO);
        ELSE
          DELETE FROM EDU_GTT_CLIEN_PAIS A
           WHERE A.COD_PAIS = PSCODIGOPAIS
             AND A.COD_EMPR_COME = PSCODEMPRESA
             AND A.COD_CURS_CAPA = PSCODCURSO
             AND EXISTS
           (SELECT X.CLIE_COD_CLIE
                    FROM EDU_HISTO_APTAS X
                   WHERE X.PAIS_COD_PAIS = PSCODIGOPAIS
                     AND X.EMCO_COD_EMPR_COME = PSCODEMPRESA
                     AND X.CCAP_COD_CURS_CAPA = PSCODCURSO
                     AND X.CLIE_COD_CLIE = A.COD_CLIE
                     AND X.NUM_INVI >= PNNUMCAMPACURSOINVITACION
                        --AND (X.IND_CURS_COST != INDICADOR_SI OR (X.IND_CURS_COST = INDICADOR_SI AND NVL(pnNumOpcionesCapacitacion,0) > 0 AND NVL(X.NUM_OPCI_ASIS_CAPA,0) > 0 AND NVL(X.NUM_OPCI_ASIS_CAPA,0) < pnNumOpcionesCapacitacion ))
                     AND X.EST_REGI = INDICADOR_ACTIVO);
        END IF;
      END IF;

    END IF;
  EXCEPTION
    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR EDU_PR_FILTR_NUMER_INVIT: ' ||
                              LS_SQLERRM);
  END EDU_PR_FILTR_NUMER_INVIT;

  /***************************************************************************
  Descripcion : Procedimiento que borra a aquellas consultoras
   cuyo numero de invitacion sean menores al valor parametrizado
   (Calificacion a Demanda)
  Fecha Creacion : 02/10/2007
  Autor : Carlos Bazalar
  Parametros :
   psCodigoPais : Codigo de Pais
   psCodEmpresa : Codigo de Empresa
   psCodCurso : Codigo de Curso
   pnNumCampaCursoInvitacion : Numero maximo de Invitaciones
  ***************************************************************************/
  PROCEDURE EDU_PR_FILTR_NUMER_INVIT_DEMAN(PSCODIGOPAIS              VARCHAR2,
                                           PSCODEMPRESA              VARCHAR2,
                                           PSCODCURSO                VARCHAR2,
                                           PNNUMCAMPACURSOINVITACION NUMBER) IS

  BEGIN
    IF PNNUMCAMPACURSOINVITACION > 0 THEN
      DELETE FROM EDU_GTT_CLIEN_PAIS A
       WHERE A.COD_CURS_CAPA = PSCODCURSO
         AND EXISTS (SELECT X.CLIE_COD_CLIE
                FROM EDU_HISTO_APTAS X
               WHERE X.CCAP_COD_CURS_CAPA = PSCODCURSO
                 AND X.CLIE_COD_CLIE = A.COD_CLIE
                 AND X.PAIS_COD_PAIS = PSCODIGOPAIS
                 AND X.EMCO_COD_EMPR_COME = PSCODEMPRESA
                 AND X.NUM_INVI < PNNUMCAMPACURSOINVITACION
                 AND X.EST_REGI = INDICADOR_ACTIVO);
    END IF;
  EXCEPTION
    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR EDU_PR_FILTR_NUMER_INVIT_DEMAN: ' ||
                              LS_SQLERRM);
  END EDU_PR_FILTR_NUMER_INVIT_DEMAN;

  /***************************************************************************
  Descripcion : Procedimiento que borra a aquellas consultoras
   que hayan excedido la secuencia de Pedido
  Fecha Creacion : 02/10/2007
  Autor : Carlos Bazalar
  Parametros :
   psCodigoPais : Codigo de Pais
   psCodEmpresa : Codigo de Empresa
   psCodCurso : Codigo de curso
   psCodPeriodo : Codigo de Periodo
   psCodSecuencia : Codigo de Secuencia de Pedido
   pnNumPediReque : Numero de Pedidos requeridos
   pnNumCampaEvaluar : Numero de campañas a evaluar
  ***************************************************************************/
  PROCEDURE EDU_PR_FILTR_SECUE_PEDID(PSCODIGOPAIS      VARCHAR2,
                                     PSCODEMPRESA      VARCHAR2,
                                     PSCODCURSO        VARCHAR2,
                                     PSCODPERIODO      VARCHAR2,
                                     PSCODSECUENCIA    VARCHAR2,
                                     PNNUMPEDIREQUE    NUMBER,
                                     PNNUMCAMPAEVALUAR NUMBER) IS
    LSCAMPAINIEVALUAR VARCHAR2(6);
    LNNUMINVITACION   EDU_PARAM_CURSO_CAPAC.NUM_INVI%TYPE;
    REGPARAMCURSOS    EDU_PARAM_CURSO_CAPAC%ROWTYPE;
    LNCONTADOR        NUMBER;
    lsConexion        BAS_PAIS.TCON_COD_TCON%TYPE;
    oidPeriodoInicial NUMBER;
    oidPeriodoFinal   NUMBER;
  BEGIN
    /* Borrando consultoras que hayan excedido la secuencia de Pedido */
    IF PSCODSECUENCIA = '02' THEN
      --Constancia
      LSCAMPAINIEVALUAR := EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_CODIG_PERIO(PSCODPERIODO,
                                                                        (PNNUMCAMPAEVALUAR - 1) * -1);

      IF (PNNUMPEDIREQUE <> PNNUMCAMPAEVALUAR) THEN
        --SI CAMPANHAS A EVALUAR ES DISTINMTO DE PEDIDOS REQUERIDOS

        DELETE FROM EDU_GTT_CLIEN_PAIS A
         WHERE A.COD_PAIS = PSCODIGOPAIS
           AND A.COD_EMPR_COME = PSCODEMPRESA
           AND A.COD_CURS_CAPA = PSCODCURSO
           AND NOT EXISTS
         (SELECT X.COD_CLIE
                  FROM EDU_HISTO_PEDID_CONSU X
                 WHERE X.COD_CLIE = A.COD_CLIE
                   AND X.PAIS_COD_PAIS = PSCODIGOPAIS
                   AND X.EMCO_COD_EMPR_COME = PSCODEMPRESA
                   AND TO_NUMBER(X.COD_ULTI_NIVE) >= PNNUMCAMPAEVALUAR
                   AND X.CAM_INIC_PEDI >= LSCAMPAINIEVALUAR
                   AND X.CAM_ULTI_PEDI <= PSCODPERIODO
                   AND X.EST_REGI = INDICADOR_ACTIVO);
      ELSE
        DELETE FROM EDU_GTT_CLIEN_PAIS A
         WHERE A.COD_PAIS = PSCODIGOPAIS
           AND A.COD_EMPR_COME = PSCODEMPRESA
           AND A.COD_CURS_CAPA = PSCODCURSO
           AND NOT EXISTS
         (SELECT X.COD_CLIE
                  FROM EDU_HISTO_PEDID_CONSU X
                 WHERE X.COD_CLIE = A.COD_CLIE
                   AND X.PAIS_COD_PAIS = PSCODIGOPAIS
                   AND X.EMCO_COD_EMPR_COME = PSCODEMPRESA
                   AND TO_NUMBER(X.COD_ULTI_NIVE) >= PNNUMCAMPAEVALUAR
                   /*AND EDU_PKG_CALIF.EDU_FN_DEVUE_NUME_CAMPA(EDU_PKG_CALIF.EDU_FN_INICI_PEDID_APTAS(PSCODIGOPAIS,
                                                                                                           PSCODEMPRESA,
                                                                                                           PSCODCURSO,
                                                                                                           A.COD_CLIE,
                                                                                                           PSCODPERIODO),
                                                             PSCODPERIODO,
                                                             PSCODIGOPAIS)>= PNNUMCAMPAEVALUAR*/
                   AND EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_CODIG_PERIO(EDU_PKG_CALIF.EDU_FN_INICI_PEDID_APTAS(PSCODIGOPAIS,
                                                                                                           PSCODEMPRESA,
                                                                                                           PSCODCURSO,
                                                                                                           A.COD_CLIE,
                                                                                                           PSCODPERIODO),
                                                                    PNNUMCAMPAEVALUAR - 1) >=
                       PSCODPERIODO
                   AND X.CAM_ULTI_PEDI <= PSCODPERIODO
                   AND X.EST_REGI = INDICADOR_ACTIVO);

      END IF;

    END IF; -- fin secuencia '02'

    IF PSCODSECUENCIA = '03' THEN
      -- Actividad

      LSCAMPAINIEVALUAR := EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_CODIG_PERIO(PSCODPERIODO,
                                                                        (PNNUMCAMPAEVALUAR - 1) * -1);

      IF (PNNUMPEDIREQUE <> PNNUMCAMPAEVALUAR) THEN
        --SI CAMPANHAS A EVALUAR ES DISTINMTO DE PEDIDOS REQUERIDOS

        --se elimina aquella consultoras que no esten programdas en el curso campanha anterior y no cumplna su secuencia
        DELETE FROM EDU_GTT_CLIEN_PAIS A
         WHERE A.COD_PAIS = PSCODIGOPAIS
           AND A.COD_EMPR_COME = PSCODEMPRESA
           AND A.COD_CURS_CAPA = PSCODCURSO
           AND NOT EXISTS ( --si existe ya no se elimina
                SELECT Y.CLIE_COD_CLIE
                  FROM EDU_PLANI_PROGR_CURSO Y
                 WHERE Y.PAIS_COD_PAIS = PSCODIGOPAIS
                   AND Y.EMCO_COD_EMPR_COME = PSCODEMPRESA
                   AND Y.CCAP_COD_CURS_CAPA = PSCODCURSO
                   AND Y.CLIE_COD_CLIE = A.COD_CLIE
                   AND Y.CAM_PROC =
                       EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_CODIG_PERIO(PSCODPERIODO,
                                                                    -1))
           AND NOT EXISTS
         (SELECT X.COD_CLIE
                  FROM EDU_HISTO_PEDID_CONSU X
                 WHERE X.COD_CLIE = A.COD_CLIE
                   AND X.PAIS_COD_PAIS = PSCODIGOPAIS
                   AND X.EMCO_COD_EMPR_COME = PSCODEMPRESA
                   AND TO_NUMBER(X.COD_ULTI_NIVE) >= PNNUMPEDIREQUE
                   AND X.CAM_INIC_PEDI >= LSCAMPAINIEVALUAR
                   AND X.CAM_ULTI_PEDI <= PSCODPERIODO
                   AND X.EST_REGI = INDICADOR_ACTIVO);
      ELSE
        --obteniendo pais sicc
       begin
        SELECT TCON_COD_TCON into lsConexion
        FROM BAS_PAIS
        WHERE COD_PAIS= PSCODIGOPAIS;
       exception
        when others then
          lsConexion:='';
       end;

        IF(lsConexion = 'ORA') THEN

        --obteniendo perioodos inical y final
        --oidPeriodoInicial:= GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(LSCAMPAINIEVALUAR);
        --oidPeriodoFinal :=  GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(PSCODPERIODO);

        DELETE FROM EDU_GTT_CLIEN_PAIS A --SI EXISTE LA COLSUTORA NO SE ELIMINA , se califica
         WHERE A.COD_PAIS = PSCODIGOPAIS
           AND A.COD_EMPR_COME = PSCODEMPRESA
           AND A.COD_CURS_CAPA = PSCODCURSO
           AND NOT EXISTS(
                  SELECT clie.cod_clie
                  FROM mae_clien_datos_adici clda,
                       mae_clien clie,
                       mae_clien_tipo_subti ctsu,
                       (
                        SELECT soca.clie_oid_clie,
                               COUNT(*) tot_regi
                          FROM ped_solic_cabec soca,
                               ped_solic_cabec conso,
                               ped_tipo_solic_pais tspa,
                               ped_tipo_solic tsol
                         WHERE soca.soca_oid_soli_cabe = conso.oid_soli_cabe(+)
                           AND soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
                           AND tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
                           --
                           AND soca.perd_oid_peri IN (SELECT perd.oid_peri
                                                        FROM cra_perio perd,
                                                             seg_perio_corpo peri
                                                       WHERE perd.peri_oid_peri = peri.oid_peri
                                                         AND peri.cod_peri >= LSCAMPAINIEVALUAR
                                                         AND peri.cod_peri <= PSCODPERIODO
                                                      ) -- oid del periodo inicial
                           AND tsol.cod_tipo_soli = 'SOC' -- Tipo de solicitud OC
                           AND soca.ind_oc = 1 -- Indicador de OC = 1
                           AND soca.grpr_oid_grup_proc <= 5 -- Grupo de proceso
                         GROUP BY soca.clie_oid_clie
                        HAVING COUNT(*) = PNNUMPEDIREQUE
                       ) pedi
                WHERE clda.clie_oid_clie = clie.oid_clie
                   AND clie.oid_clie = ctsu.clie_oid_clie
               AND clie.COD_CLIE = A.COD_CLIE
                   AND clie.oid_clie = pedi.clie_oid_clie(+)
                   AND ctsu.ticl_oid_tipo_clie = 2
                   AND ctsu.sbti_oid_subt_clie = 1
                   AND pedi.clie_oid_clie IS NOT NULL);
        ELSE

        DELETE FROM EDU_GTT_CLIEN_PAIS A --SI EXISTE LA COLSUTORA NO SE ELIMINA , se califica
         WHERE A.COD_PAIS = PSCODIGOPAIS
           AND A.COD_EMPR_COME = PSCODEMPRESA
           AND A.COD_CURS_CAPA = PSCODCURSO
           AND NOT EXISTS
         (SELECT X.COD_CLIE
                  FROM EDU_HISTO_PEDID_CONSU X
                 WHERE X.COD_CLIE = A.COD_CLIE
                   AND X.PAIS_COD_PAIS = PSCODIGOPAIS
                   AND X.EMCO_COD_EMPR_COME = PSCODEMPRESA
                   AND TO_NUMBER(X.COD_ULTI_NIVE) >= PNNUMPEDIREQUE
                  /* AND EDU_PKG_CALIF.EDU_FN_DEVUE_NUME_CAMPA(EDU_PKG_CALIF.EDU_FN_INICI_PEDID_APTAS(PSCODIGOPAIS,
                                                                                                           PSCODEMPRESA,
                                                                                                           PSCODCURSO,
                                                                                                           A.COD_CLIE,
                                                                                                           PSCODPERIODO),
                                                             PSCODPERIODO,
                                                             PSCODIGOPAIS)>= PNNUMCAMPAEVALUAR*/
                   AND EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_CODIG_PERIO(EDU_PKG_CALIF.EDU_FN_INICI_PEDID_APTAS(PSCODIGOPAIS,
                                                                                                           PSCODEMPRESA,
                                                                                                           PSCODCURSO,
                                                                                                           A.COD_CLIE,
                                                                                                           PSCODPERIODO),
                                                                    PNNUMCAMPAEVALUAR - 1) >=
                       PSCODPERIODO
                   AND X.CAM_ULTI_PEDI <= PSCODPERIODO
                   AND X.EST_REGI = INDICADOR_ACTIVO);
      END IF;

      END IF;
    END IF; --fin secuencia 03

    --Nueva secuencia de pedido que permitira calificar aquellas consultoras que no hayan pasado su utltimo pedido
    --en su n campañas para evaluar, es decir pasaron en n-1, y en la campanha n no pasaron, aquellas seran insertdas
    --en gtt para su calificacion
    IF PSCODSECUENCIA = '04' THEN
      -- Sin ultimo Pedido

      --OBTENEOS SI SE TIENE AMBITO

      BEGIN
        SELECT COUNT(1)
          INTO LNCONTADOR
          FROM EDU_PARAM_CURSO_CAPAC_AMBIT A
         WHERE A.PAIS_COD_PAIS = PSCODIGOPAIS
           AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
           AND A.CCAP_COD_CURS_CAPA = PSCODCURSO
           AND A.AMDI_COD_AMBI_DICT <> '01'
           AND A.EST_REGI = INDICADOR_ACTIVO;
      EXCEPTION
        WHEN NO_DATA_FOUND THEN
          LNCONTADOR := 0;
      END;
      --

      LSCAMPAINIEVALUAR := EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_CODIG_PERIO(PSCODPERIODO,
                                                                        (PNNUMCAMPAEVALUAR) * -1);

      SELECT Z.NUM_INVI
        INTO LNNUMINVITACION
        FROM EDU_PARAM_CURSO_CAPAC Z
       WHERE Z.PAIS_COD_PAIS = PSCODIGOPAIS
         AND Z.EMCO_COD_EMPR_COME = PSCODEMPRESA
         AND Z.COD_CURS_CAPA = PSCODCURSO;

      DELETE FROM EDU_GTT_CLIEN_PAIS A
       WHERE A.COD_PAIS = PSCODIGOPAIS
         AND A.COD_EMPR_COME = PSCODEMPRESA
         AND A.COD_CURS_CAPA = PSCODCURSO
         AND NOT EXISTS
       (SELECT X.COD_CLIE
                FROM EDU_HISTO_PEDID_CONSU X
               WHERE X.COD_CLIE = A.COD_CLIE
                 AND X.PAIS_COD_PAIS = PSCODIGOPAIS
                 AND X.EMCO_COD_EMPR_COME = PSCODEMPRESA
                 AND TO_NUMBER(X.COD_ULTI_NIVE) >= PNNUMCAMPAEVALUAR
                    --AND X.CAM_INIC_PEDI >= lsCampaIniEvaluar
                 AND X.CAM_ULTI_PEDI <= PSCODPERIODO
                 AND X.EST_REGI = INDICADOR_ACTIVO);

      /* Filtrando consultoras por el AMBITO */
      IF LNCONTADOR > 0 THEN

        --PARAMETRIA DEL CURSO
        SELECT *
          INTO REGPARAMCURSOS
          FROM EDU_PARAM_CURSO_CAPAC A
         WHERE A.PAIS_COD_PAIS = PSCODIGOPAIS
           AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
           AND A.COD_CURS_CAPA = PSCODCURSO
           AND A.EST_REGI = INDICADOR_ACTIVO;

        IF (REGPARAMCURSOS.AMDI_COD_AMBI_DICT = '02') THEN
          --REGION

          IF (REGPARAMCURSOS.IND_CALI_AMBI = '1') THEN
            --INCLUYE
            INSERT INTO EDU_GTT_CLIEN_PAIS
              (COD_PAIS, COD_EMPR_COME, COD_CLIE, COD_CURS_CAPA)
              SELECT X.PAIS_COD_PAIS,
                     X.EMCO_COD_EMPR_COME,
                     X.COD_CLIE,
                     PSCODCURSO
                FROM EDU_HISTO_PEDID_CONSU X
               WHERE X.PAIS_COD_PAIS = PSCODIGOPAIS
                 AND X.EMCO_COD_EMPR_COME = PSCODEMPRESA
                 AND NOT EXISTS
               (SELECT NULL
                        FROM EDU_HISTO_APTAS Z
                       WHERE Z.CCAP_COD_CURS_CAPA = PSCODCURSO
                         AND Z.CLIE_COD_CLIE = X.COD_CLIE
                         AND Z.PAIS_COD_PAIS = X.PAIS_COD_PAIS
                         AND Z.EMCO_COD_EMPR_COME = X.EMCO_COD_EMPR_COME
                         AND Z.CAM_ULTI_CALI_APTA = PSCODPERIODO)
                 AND TO_NUMBER(X.COD_ULTI_NIVE) = PNNUMCAMPAEVALUAR - 1
                    --AND X.CAM_INIC_PEDI >= lsCampaIniEvaluar
                 AND X.NUM_PEDI_FACT >= 1
                 AND EDU_PKG_CALIF.EDU_FN_INICI_PEDID_APTAS(PSCODIGOPAIS,
                                                            PSCODEMPRESA,
                                                            PSCODCURSO,
                                                            X.COD_CLIE,
                                                            PSCODPERIODO) >=
                     LSCAMPAINIEVALUAR
                 AND NVL((SELECT HA.NUM_INVI
                           FROM EDU_HISTO_APTAS HA
                          WHERE HA.PAIS_COD_PAIS = PSCODIGOPAIS
                            AND HA.EMCO_COD_EMPR_COME = PSCODEMPRESA
                            AND HA.CLIE_COD_CLIE = X.COD_CLIE
                            AND HA.CCAP_COD_CURS_CAPA = PSCODCURSO),
                         0) < LNNUMINVITACION
                 AND X.CAM_ULTI_PEDI < PSCODPERIODO
                 AND X.EST_REGI = INDICADOR_ACTIVO
                 AND EXISTS
               (SELECT NULL
                        FROM EDU_MAEST_CLIEN             Z,
                             EDU_PARAM_CURSO_CAPAC_AMBIT Y
                       WHERE Z.PAIS_COD_PAIS = PSCODIGOPAIS
                         AND Z.EMCO_COD_EMPR_COME = PSCODEMPRESA
                         AND Z.COD_CLIE = X.COD_CLIE
                         AND Z.PAIS_COD_PAIS = Y.PAIS_COD_PAIS
                         AND Z.EMCO_COD_EMPR_COME = Y.EMCO_COD_EMPR_COME
                         AND Y.CCAP_COD_CURS_CAPA = PSCODCURSO
                         AND Z.COD_REGI = Y.COD_REGI);

            --INSERTANDO UN REGISTRO FICTICCO EN EL TEMPORAL DE PEDIDOS PARA EL ENVIO A COMERCIAL

            INSERT INTO EDU_TMP_PEDID_CONSU
              (COD_PAIS, COD_EMPR_COME, CAM_PROC, COD_CLIE)
              SELECT X.PAIS_COD_PAIS,
                     X.EMCO_COD_EMPR_COME,
                     PSCODPERIODO,
                     X.COD_CLIE
                FROM EDU_HISTO_PEDID_CONSU X
               WHERE X.PAIS_COD_PAIS = PSCODIGOPAIS
                 AND X.EMCO_COD_EMPR_COME = PSCODEMPRESA
                 AND TO_NUMBER(X.COD_ULTI_NIVE) = PNNUMCAMPAEVALUAR - 1
                    --AND X.CAM_INIC_PEDI >= lsCampaIniEvaluar
                 AND X.NUM_PEDI_FACT >= 1
                 AND EDU_PKG_CALIF.EDU_FN_INICI_PEDID_APTAS(PSCODIGOPAIS,
                                                            PSCODEMPRESA,
                                                            PSCODCURSO,
                                                            X.COD_CLIE,
                                                            PSCODPERIODO) >=
                     LSCAMPAINIEVALUAR
                 AND NVL((SELECT HA.NUM_INVI
                           FROM EDU_HISTO_APTAS HA
                          WHERE HA.PAIS_COD_PAIS = PSCODIGOPAIS
                            AND HA.EMCO_COD_EMPR_COME = PSCODEMPRESA
                            AND HA.CLIE_COD_CLIE = X.COD_CLIE
                            AND HA.CCAP_COD_CURS_CAPA = PSCODCURSO),
                         0) < LNNUMINVITACION
                 AND X.CAM_ULTI_PEDI < PSCODPERIODO
                 AND X.EST_REGI = INDICADOR_ACTIVO
                 AND X.COD_CLIE NOT IN
                     (SELECT Z.CLIE_COD_CLIE
                        FROM EDU_HISTO_APTAS Z
                       WHERE Z.PAIS_COD_PAIS = X.PAIS_COD_PAIS
                         AND Z.EMCO_COD_EMPR_COME = X.EMCO_COD_EMPR_COME
                         AND Z.CCAP_COD_CURS_CAPA = PSCODCURSO
                         AND Z.CLIE_COD_CLIE = X.COD_CLIE
                         AND Z.CAM_ULTI_CALI_APTA = PSCODPERIODO)
                 AND EXISTS
               (SELECT NULL
                        FROM EDU_MAEST_CLIEN             Z,
                             EDU_PARAM_CURSO_CAPAC_AMBIT Y
                       WHERE Z.PAIS_COD_PAIS = PSCODIGOPAIS
                         AND Z.EMCO_COD_EMPR_COME = PSCODEMPRESA
                         AND Z.COD_CLIE = X.COD_CLIE
                         AND Z.PAIS_COD_PAIS = Y.PAIS_COD_PAIS
                         AND Z.EMCO_COD_EMPR_COME = Y.EMCO_COD_EMPR_COME
                         AND Y.CCAP_COD_CURS_CAPA = PSCODCURSO
                         AND Z.COD_REGI = Y.COD_REGI);

          ELSE
            --EXCLUYE

            INSERT INTO EDU_GTT_CLIEN_PAIS
              (COD_PAIS, COD_EMPR_COME, COD_CLIE, COD_CURS_CAPA)
              SELECT X.PAIS_COD_PAIS,
                     X.EMCO_COD_EMPR_COME,
                     X.COD_CLIE,
                     PSCODCURSO
                FROM EDU_HISTO_PEDID_CONSU X
               WHERE X.PAIS_COD_PAIS = PSCODIGOPAIS
                 AND X.EMCO_COD_EMPR_COME = PSCODEMPRESA
                 AND NOT EXISTS
               (SELECT NULL
                        FROM EDU_HISTO_APTAS Z
                       WHERE Z.CAM_ULTI_CALI_APTA = PSCODPERIODO
                         AND Z.CCAP_COD_CURS_CAPA = PSCODCURSO
                         AND Z.CLIE_COD_CLIE = X.COD_CLIE
                         AND Z.PAIS_COD_PAIS = X.PAIS_COD_PAIS
                         AND Z.EMCO_COD_EMPR_COME = X.EMCO_COD_EMPR_COME
                         )
                 AND TO_NUMBER(X.COD_ULTI_NIVE) = PNNUMCAMPAEVALUAR - 1
                    --AND X.CAM_INIC_PEDI >= lsCampaIniEvaluar
                 AND X.NUM_PEDI_FACT >= 1
                 AND EDU_PKG_CALIF.EDU_FN_INICI_PEDID_APTAS(PSCODIGOPAIS,
                                                            PSCODEMPRESA,
                                                            PSCODCURSO,
                                                            X.COD_CLIE,
                                                            PSCODPERIODO) >=
                     LSCAMPAINIEVALUAR
                 AND NVL((SELECT HA.NUM_INVI
                           FROM EDU_HISTO_APTAS HA
                          WHERE HA.CLIE_COD_CLIE = X.COD_CLIE
                            AND HA.CCAP_COD_CURS_CAPA = PSCODCURSO
                            AND HA.PAIS_COD_PAIS = PSCODIGOPAIS
                            AND HA.EMCO_COD_EMPR_COME = PSCODEMPRESA),
                         0) < LNNUMINVITACION
                 AND X.CAM_ULTI_PEDI < PSCODPERIODO
                 AND X.EST_REGI = INDICADOR_ACTIVO
                 AND NOT EXISTS
               (SELECT NULL
                        FROM EDU_MAEST_CLIEN             Z,
                             EDU_PARAM_CURSO_CAPAC_AMBIT Y
                       WHERE Z.PAIS_COD_PAIS = PSCODIGOPAIS
                         AND Z.EMCO_COD_EMPR_COME = PSCODEMPRESA
                         AND Z.COD_CLIE = X.COD_CLIE
                         AND Z.PAIS_COD_PAIS = Y.PAIS_COD_PAIS
                         AND Z.EMCO_COD_EMPR_COME = Y.EMCO_COD_EMPR_COME
                         AND Y.CCAP_COD_CURS_CAPA = PSCODCURSO
                         AND Z.COD_REGI = Y.COD_REGI);

            --INSERTANDO UN REGISTRO FICTICCO EN EL TEMPORAL DE PEDIDOS PARA EL ENVIO A COMERCIAL

            INSERT INTO EDU_TMP_PEDID_CONSU
              (COD_PAIS, COD_EMPR_COME, CAM_PROC, COD_CLIE)
              SELECT X.PAIS_COD_PAIS,
                     X.EMCO_COD_EMPR_COME,
                     PSCODPERIODO,
                     X.COD_CLIE
                FROM EDU_HISTO_PEDID_CONSU X
               WHERE X.PAIS_COD_PAIS = PSCODIGOPAIS
                 AND X.EMCO_COD_EMPR_COME = PSCODEMPRESA
                 AND TO_NUMBER(X.COD_ULTI_NIVE) = PNNUMCAMPAEVALUAR - 1
                    --AND X.CAM_INIC_PEDI >= lsCampaIniEvaluar
                 AND X.NUM_PEDI_FACT >= 1
                 AND EDU_PKG_CALIF.EDU_FN_INICI_PEDID_APTAS(PSCODIGOPAIS,
                                                            PSCODEMPRESA,
                                                            PSCODCURSO,
                                                            X.COD_CLIE,
                                                            PSCODPERIODO) >=
                     LSCAMPAINIEVALUAR
                 AND NVL((SELECT HA.NUM_INVI
                           FROM EDU_HISTO_APTAS HA
                          WHERE HA.CCAP_COD_CURS_CAPA = PSCODCURSO
                            AND HA.CLIE_COD_CLIE = X.COD_CLIE
                            AND HA.PAIS_COD_PAIS = PSCODIGOPAIS
                            AND HA.EMCO_COD_EMPR_COME = PSCODEMPRESA),
                         0) < LNNUMINVITACION
                 AND X.CAM_ULTI_PEDI < PSCODPERIODO
                 AND X.EST_REGI = INDICADOR_ACTIVO
                 AND X.COD_CLIE NOT IN
                     (SELECT Z.CLIE_COD_CLIE
                        FROM EDU_HISTO_APTAS Z
                       WHERE Z.CAM_ULTI_CALI_APTA = PSCODPERIODO
                         AND Z.CCAP_COD_CURS_CAPA = PSCODCURSO
                         AND Z.PAIS_COD_PAIS = X.PAIS_COD_PAIS
                         AND Z.EMCO_COD_EMPR_COME = X.EMCO_COD_EMPR_COME
                         AND Z.CLIE_COD_CLIE = X.COD_CLIE
                         )
                 AND NOT EXISTS
               (SELECT NULL
                        FROM EDU_MAEST_CLIEN             Z,
                             EDU_PARAM_CURSO_CAPAC_AMBIT Y
                       WHERE Z.PAIS_COD_PAIS = PSCODIGOPAIS
                         AND Z.EMCO_COD_EMPR_COME = PSCODEMPRESA
                         AND Z.COD_CLIE = X.COD_CLIE
                         AND Z.PAIS_COD_PAIS = Y.PAIS_COD_PAIS
                         AND Z.EMCO_COD_EMPR_COME = Y.EMCO_COD_EMPR_COME
                         AND Y.CCAP_COD_CURS_CAPA = PSCODCURSO
                         AND Z.COD_REGI = Y.COD_REGI);

          END IF;

        END IF; --FIN AMBITO REGION

        IF (REGPARAMCURSOS.AMDI_COD_AMBI_DICT = '03') THEN
          --ZONA

          IF (REGPARAMCURSOS.IND_CALI_AMBI = '1') THEN
            --INCLUYE
            INSERT INTO EDU_GTT_CLIEN_PAIS
              (COD_PAIS, COD_EMPR_COME, COD_CLIE, COD_CURS_CAPA)
              SELECT X.PAIS_COD_PAIS,
                     X.EMCO_COD_EMPR_COME,
                     X.COD_CLIE,
                     PSCODCURSO
                FROM EDU_HISTO_PEDID_CONSU X
               WHERE X.PAIS_COD_PAIS = PSCODIGOPAIS
                 AND X.EMCO_COD_EMPR_COME = PSCODEMPRESA
                 AND NOT EXISTS
               (SELECT NULL
                        FROM EDU_HISTO_APTAS Z
                       WHERE Z.CAM_ULTI_CALI_APTA = PSCODPERIODO
                         AND Z.CCAP_COD_CURS_CAPA = PSCODCURSO
                         AND Z.CLIE_COD_CLIE = X.COD_CLIE
                         AND Z.PAIS_COD_PAIS = X.PAIS_COD_PAIS
                         AND Z.EMCO_COD_EMPR_COME = X.EMCO_COD_EMPR_COME
                         )
                 AND TO_NUMBER(X.COD_ULTI_NIVE) = PNNUMCAMPAEVALUAR - 1
                    --AND X.CAM_INIC_PEDI >= lsCampaIniEvaluar
                 AND X.NUM_PEDI_FACT >= 1
                 AND EDU_PKG_CALIF.EDU_FN_INICI_PEDID_APTAS(PSCODIGOPAIS,
                                                            PSCODEMPRESA,
                                                            PSCODCURSO,
                                                            X.COD_CLIE,
                                                            PSCODPERIODO) >=
                     LSCAMPAINIEVALUAR
                 AND NVL((SELECT HA.NUM_INVI
                           FROM EDU_HISTO_APTAS HA
                          WHERE HA.CLIE_COD_CLIE = X.COD_CLIE
                            AND HA.CCAP_COD_CURS_CAPA = PSCODCURSO
                            AND HA.PAIS_COD_PAIS = PSCODIGOPAIS
                            AND HA.EMCO_COD_EMPR_COME = PSCODEMPRESA),
                         0) < LNNUMINVITACION
                 AND X.CAM_ULTI_PEDI < PSCODPERIODO
                 AND X.EST_REGI = INDICADOR_ACTIVO
                 AND EXISTS
               (SELECT NULL
                        FROM EDU_MAEST_CLIEN             Z,
                             EDU_PARAM_CURSO_CAPAC_AMBIT Y
                       WHERE Z.PAIS_COD_PAIS = PSCODIGOPAIS
                         AND Z.EMCO_COD_EMPR_COME = PSCODEMPRESA
                         AND Z.COD_CLIE = X.COD_CLIE
                         AND Z.PAIS_COD_PAIS = Y.PAIS_COD_PAIS
                         AND Z.EMCO_COD_EMPR_COME = Y.EMCO_COD_EMPR_COME
                         AND Y.CCAP_COD_CURS_CAPA = PSCODCURSO
                         AND Z.COD_ZONA = Y.COD_ZONA);

            --INSERTANDO UN REGISTRO FICTICCO EN EL TEMPORAL DE PEDIDOS PARA EL ENVIO A COMERCIAL

            INSERT INTO EDU_TMP_PEDID_CONSU
              (COD_PAIS, COD_EMPR_COME, CAM_PROC, COD_CLIE)
              SELECT X.PAIS_COD_PAIS,
                     X.EMCO_COD_EMPR_COME,
                     PSCODPERIODO,
                     X.COD_CLIE
                FROM EDU_HISTO_PEDID_CONSU X
               WHERE X.PAIS_COD_PAIS = PSCODIGOPAIS
                 AND X.EMCO_COD_EMPR_COME = PSCODEMPRESA
                 AND TO_NUMBER(X.COD_ULTI_NIVE) = PNNUMCAMPAEVALUAR - 1
                    --AND X.CAM_INIC_PEDI >= lsCampaIniEvaluar
                 AND X.NUM_PEDI_FACT >= 1
                 AND EDU_PKG_CALIF.EDU_FN_INICI_PEDID_APTAS(PSCODIGOPAIS,
                                                            PSCODEMPRESA,
                                                            PSCODCURSO,
                                                            X.COD_CLIE,
                                                            PSCODPERIODO) >=
                     LSCAMPAINIEVALUAR
                 AND NVL((SELECT HA.NUM_INVI
                           FROM EDU_HISTO_APTAS HA
                          WHERE HA.CLIE_COD_CLIE = X.COD_CLIE
                            AND HA.CCAP_COD_CURS_CAPA = PSCODCURSO
                            AND HA.PAIS_COD_PAIS = PSCODIGOPAIS
                            AND HA.EMCO_COD_EMPR_COME = PSCODEMPRESA),
                         0) < LNNUMINVITACION
                 AND X.CAM_ULTI_PEDI < PSCODPERIODO
                 AND X.EST_REGI = INDICADOR_ACTIVO
                 AND X.COD_CLIE NOT IN
                     (SELECT Z.CLIE_COD_CLIE
                        FROM EDU_HISTO_APTAS Z
                       WHERE Z.CAM_ULTI_CALI_APTA = PSCODPERIODO
                         AND Z.CCAP_COD_CURS_CAPA = PSCODCURSO
                         AND Z.CLIE_COD_CLIE = X.COD_CLIE
                         AND Z.PAIS_COD_PAIS = X.PAIS_COD_PAIS
                         AND Z.EMCO_COD_EMPR_COME = X.EMCO_COD_EMPR_COME
                         )
                 AND EXISTS
               (SELECT NULL
                        FROM EDU_MAEST_CLIEN             Z,
                             EDU_PARAM_CURSO_CAPAC_AMBIT Y
                       WHERE Z.PAIS_COD_PAIS = PSCODIGOPAIS
                         AND Z.EMCO_COD_EMPR_COME = PSCODEMPRESA
                         AND Z.COD_CLIE = X.COD_CLIE
                         AND Z.PAIS_COD_PAIS = Y.PAIS_COD_PAIS
                         AND Z.EMCO_COD_EMPR_COME = Y.EMCO_COD_EMPR_COME
                         AND Y.CCAP_COD_CURS_CAPA = PSCODCURSO
                         AND Z.COD_ZONA = Y.COD_ZONA);

          ELSE
            --EXCLUYE

            INSERT INTO EDU_GTT_CLIEN_PAIS
              (COD_PAIS, COD_EMPR_COME, COD_CLIE, COD_CURS_CAPA)
              SELECT X.PAIS_COD_PAIS,
                     X.EMCO_COD_EMPR_COME,
                     X.COD_CLIE,
                     PSCODCURSO
                FROM EDU_HISTO_PEDID_CONSU X
               WHERE X.PAIS_COD_PAIS = PSCODIGOPAIS
                 AND X.EMCO_COD_EMPR_COME = PSCODEMPRESA
                 AND NOT EXISTS
               (SELECT NULL
                        FROM EDU_HISTO_APTAS Z
                       WHERE Z.CAM_ULTI_CALI_APTA = PSCODPERIODO
                         AND Z.CCAP_COD_CURS_CAPA = PSCODCURSO
                         AND Z.CLIE_COD_CLIE = X.COD_CLIE
                         AND Z.PAIS_COD_PAIS = X.PAIS_COD_PAIS
                         AND Z.EMCO_COD_EMPR_COME = X.EMCO_COD_EMPR_COME
                         )
                 AND TO_NUMBER(X.COD_ULTI_NIVE) = PNNUMCAMPAEVALUAR - 1
                    --AND X.CAM_INIC_PEDI >= lsCampaIniEvaluar
                 AND X.NUM_PEDI_FACT >= 1
                 AND EDU_PKG_CALIF.EDU_FN_INICI_PEDID_APTAS(PSCODIGOPAIS,
                                                            PSCODEMPRESA,
                                                            PSCODCURSO,
                                                            X.COD_CLIE,
                                                            PSCODPERIODO) >=
                     LSCAMPAINIEVALUAR
                 AND NVL((SELECT HA.NUM_INVI
                           FROM EDU_HISTO_APTAS HA
                          WHERE HA.CLIE_COD_CLIE = X.COD_CLIE
                            AND HA.CCAP_COD_CURS_CAPA = PSCODCURSO
                            AND HA.PAIS_COD_PAIS = PSCODIGOPAIS
                            AND HA.EMCO_COD_EMPR_COME = PSCODEMPRESA),
                         0) < LNNUMINVITACION
                 AND X.CAM_ULTI_PEDI < PSCODPERIODO
                 AND X.EST_REGI = INDICADOR_ACTIVO
                 AND NOT EXISTS
               (SELECT NULL
                        FROM EDU_MAEST_CLIEN             Z,
                             EDU_PARAM_CURSO_CAPAC_AMBIT Y
                       WHERE Z.PAIS_COD_PAIS = PSCODIGOPAIS
                         AND Z.EMCO_COD_EMPR_COME = PSCODEMPRESA
                         AND Z.COD_CLIE = X.COD_CLIE
                         AND Z.PAIS_COD_PAIS = Y.PAIS_COD_PAIS
                         AND Z.EMCO_COD_EMPR_COME = Y.EMCO_COD_EMPR_COME
                         AND Y.CCAP_COD_CURS_CAPA = PSCODCURSO
                         AND Z.COD_ZONA = Y.COD_ZONA);

            --INSERTANDO UN REGISTRO FICTICCO EN EL TEMPORAL DE PEDIDOS PARA EL ENVIO A COMERCIAL

            INSERT INTO EDU_TMP_PEDID_CONSU
              (COD_PAIS, COD_EMPR_COME, CAM_PROC, COD_CLIE)
              SELECT X.PAIS_COD_PAIS,
                     X.EMCO_COD_EMPR_COME,
                     PSCODPERIODO,
                     X.COD_CLIE
                FROM EDU_HISTO_PEDID_CONSU X
               WHERE X.PAIS_COD_PAIS = PSCODIGOPAIS
                 AND X.EMCO_COD_EMPR_COME = PSCODEMPRESA
                 AND TO_NUMBER(X.COD_ULTI_NIVE) = PNNUMCAMPAEVALUAR - 1
                    --AND X.CAM_INIC_PEDI >= lsCampaIniEvaluar
                 AND X.NUM_PEDI_FACT >= 1
                 AND EDU_PKG_CALIF.EDU_FN_INICI_PEDID_APTAS(PSCODIGOPAIS,
                                                            PSCODEMPRESA,
                                                            PSCODCURSO,
                                                            X.COD_CLIE,
                                                            PSCODPERIODO) >=
                     LSCAMPAINIEVALUAR
                 AND NVL((SELECT HA.NUM_INVI
                           FROM EDU_HISTO_APTAS HA
                          WHERE HA.CLIE_COD_CLIE = X.COD_CLIE
                            AND HA.CCAP_COD_CURS_CAPA = PSCODCURSO
                            AND HA.PAIS_COD_PAIS = PSCODIGOPAIS
                            AND HA.EMCO_COD_EMPR_COME = PSCODEMPRESA),
                         0) < LNNUMINVITACION
                 AND X.CAM_ULTI_PEDI < PSCODPERIODO
                 AND X.EST_REGI = INDICADOR_ACTIVO
                 AND X.COD_CLIE NOT IN
                     (SELECT Z.CLIE_COD_CLIE
                        FROM EDU_HISTO_APTAS Z
                       WHERE Z.CCAP_COD_CURS_CAPA = PSCODCURSO
                         AND Z.CLIE_COD_CLIE = X.COD_CLIE
                         AND Z.PAIS_COD_PAIS = X.PAIS_COD_PAIS
                         AND Z.EMCO_COD_EMPR_COME = X.EMCO_COD_EMPR_COME
                         AND Z.CAM_ULTI_CALI_APTA = PSCODPERIODO)
                 AND NOT EXISTS
               (SELECT NULL
                        FROM EDU_MAEST_CLIEN             Z,
                             EDU_PARAM_CURSO_CAPAC_AMBIT Y
                       WHERE Z.PAIS_COD_PAIS = PSCODIGOPAIS
                         AND Z.EMCO_COD_EMPR_COME = PSCODEMPRESA
                         AND Z.COD_CLIE = X.COD_CLIE
                         AND Z.PAIS_COD_PAIS = Y.PAIS_COD_PAIS
                         AND Z.EMCO_COD_EMPR_COME = Y.EMCO_COD_EMPR_COME
                         AND Y.CCAP_COD_CURS_CAPA = PSCODCURSO
                         AND Z.COD_ZONA = Y.COD_ZONA);

          END IF;

        END IF;

      ELSE
        --SI NO HAY AMBITO
        INSERT INTO EDU_GTT_CLIEN_PAIS
          (COD_PAIS, COD_EMPR_COME, COD_CLIE, COD_CURS_CAPA)
          SELECT X.PAIS_COD_PAIS,
                 X.EMCO_COD_EMPR_COME,
                 X.COD_CLIE,
                 PSCODCURSO
            FROM EDU_HISTO_PEDID_CONSU X
           WHERE X.PAIS_COD_PAIS = PSCODIGOPAIS
             AND X.EMCO_COD_EMPR_COME = PSCODEMPRESA
             AND TO_NUMBER(X.COD_ULTI_NIVE) = PNNUMCAMPAEVALUAR - 1
                --AND X.CAM_INIC_PEDI >= lsCampaIniEvaluar
             AND X.NUM_PEDI_FACT >= 1
             AND EDU_PKG_CALIF.EDU_FN_INICI_PEDID_APTAS(PSCODIGOPAIS,
                                                        PSCODEMPRESA,
                                                        PSCODCURSO,
                                                        X.COD_CLIE,
                                                        PSCODPERIODO) >=
                 LSCAMPAINIEVALUAR
             AND NVL((SELECT HA.NUM_INVI
                       FROM EDU_HISTO_APTAS HA
                      WHERE HA.PAIS_COD_PAIS = PSCODIGOPAIS
                        AND HA.EMCO_COD_EMPR_COME = PSCODEMPRESA
                        AND HA.CLIE_COD_CLIE = X.COD_CLIE
                        AND HA.CCAP_COD_CURS_CAPA = PSCODCURSO),
                     0) < LNNUMINVITACION
             AND X.CAM_ULTI_PEDI < PSCODPERIODO
             AND X.EST_REGI = INDICADOR_ACTIVO
             AND X.COD_CLIE NOT IN
                 (SELECT Z.CLIE_COD_CLIE
                    FROM EDU_HISTO_APTAS Z
                   WHERE Z.CAM_ULTI_CALI_APTA = PSCODPERIODO
                     AND Z.CCAP_COD_CURS_CAPA = PSCODCURSO
                     AND Z.CLIE_COD_CLIE = X.COD_CLIE
                     AND Z.PAIS_COD_PAIS = X.PAIS_COD_PAIS
                     AND Z.EMCO_COD_EMPR_COME = X.EMCO_COD_EMPR_COME
                     );

        --INSERTANDO UN REGISTRO FICTICCO EN EL TEMPORAL DE PEDIDOS PARA EL ENVIO A COMERCIAL

        INSERT INTO EDU_TMP_PEDID_CONSU
          (COD_PAIS, COD_EMPR_COME, CAM_PROC, COD_CLIE)
          SELECT X.PAIS_COD_PAIS,
                 X.EMCO_COD_EMPR_COME,
                 PSCODPERIODO,
                 X.COD_CLIE
            FROM EDU_HISTO_PEDID_CONSU X
           WHERE X.PAIS_COD_PAIS = PSCODIGOPAIS
             AND X.EMCO_COD_EMPR_COME = PSCODEMPRESA
             AND TO_NUMBER(X.COD_ULTI_NIVE) = PNNUMCAMPAEVALUAR - 1
                --AND X.CAM_INIC_PEDI >= lsCampaIniEvaluar
             AND X.NUM_PEDI_FACT >= 1
             AND EDU_PKG_CALIF.EDU_FN_INICI_PEDID_APTAS(PSCODIGOPAIS,
                                                        PSCODEMPRESA,
                                                        PSCODCURSO,
                                                        X.COD_CLIE,
                                                        PSCODPERIODO) >=
                 LSCAMPAINIEVALUAR
             AND NVL((SELECT HA.NUM_INVI
                       FROM EDU_HISTO_APTAS HA
                      WHERE HA.CCAP_COD_CURS_CAPA = PSCODCURSO
                        AND HA.PAIS_COD_PAIS = PSCODIGOPAIS
                        AND HA.EMCO_COD_EMPR_COME = PSCODEMPRESA
                        AND HA.CLIE_COD_CLIE = X.COD_CLIE
                        ),
                     0) < LNNUMINVITACION
             AND X.CAM_ULTI_PEDI < PSCODPERIODO
             AND X.EST_REGI = INDICADOR_ACTIVO
             AND X.COD_CLIE NOT IN
                 (SELECT Z.CLIE_COD_CLIE
                    FROM EDU_HISTO_APTAS Z
                   WHERE Z.CAM_ULTI_CALI_APTA = PSCODPERIODO
                     AND Z.CCAP_COD_CURS_CAPA = PSCODCURSO
                     AND Z.CLIE_COD_CLIE = X.COD_CLIE
                     AND Z.PAIS_COD_PAIS = X.PAIS_COD_PAIS
                     AND Z.EMCO_COD_EMPR_COME = X.EMCO_COD_EMPR_COME
                     );
      END IF; --SETENCIA NO HAY AMBITO

    END IF; --FIN SECUENCIA 04

  EXCEPTION
    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR EDU_PR_FILTR_SECUE_PEDID: ' ||
                              LS_SQLERRM);
  END EDU_PR_FILTR_SECUE_PEDID;

  /***************************************************************************
  Descripcion : Procedimiento que realiza el Registro en el Historico
   de Aptas
  Fecha Creacion : 09/07/2007
  Autor : Carlos Bazalar
  Parametros :
   psCodigoPais : Codigo de Pais
   psCodEmpresa : Codigo de Empresa
   psCodCurso : Codigo de Curso de Capacitación
   psCodPeriodo : Campaña de Proceso
   psTipo : 'A': Calificacion Automatica 'D': Calificacion a Demanda
   psUsuario : Usuario
  ***************************************************************************/
  PROCEDURE EDU_PR_REGIS_CALIF_APTAS(PSCODIGOPAIS VARCHAR2,
                                     PSCODEMPRESA VARCHAR2,
                                     PSCODCURSO   VARCHAR2,
                                     PSCODPERIODO VARCHAR2,
                                     PSTIPO       VARCHAR2,
                                     PSUSUARIO    VARCHAR2) IS
    CURSOR CURSORAPTAS IS
      SELECT *
        FROM EDU_GTT_CLIEN_PAIS A
       WHERE A.COD_PAIS = PSCODIGOPAIS
         AND A.COD_EMPR_COME = PSCODEMPRESA
         AND A.COD_CURS_CAPA = PSCODCURSO;

    TYPE TTABLAAPTAS IS TABLE OF EDU_GTT_CLIEN_PAIS%ROWTYPE;
    TABLAREGISTROAPTAS        TTABLAAPTAS;
    REGISTROAPTAS             EDU_GTT_CLIEN_PAIS%ROWTYPE;
    LSCODCLIEN                EDU_HISTO_APTAS.CLIE_COD_CLIE%TYPE;
    REGPARAMCURSOS            EDU_PARAM_CURSO_CAPAC%ROWTYPE;
    REGPARAMPROGRAMA          EDU_PARAM_PROGR_CAPAC%ROWTYPE;
    LBINSERTAR                BOOLEAN;
    LBPROCESAR                BOOLEAN;
    LBCALIFICAR               BOOLEAN;
    LNPAGINA                  NUMBER;
    LNX                       NUMBER;
    LNNROPERIODOSSIGUIENTES   NUMBER;
    LSCODIGOPERIODOULTIMACALI VARCHAR2(6);
    LSINDICADORENVIO          VARCHAR2(1);
    LSESTADOAPTAS             VARCHAR2(2);

  BEGIN
    /* Obteniendo parametrizacion del curso */
    SELECT *
      INTO REGPARAMCURSOS
      FROM EDU_PARAM_CURSO_CAPAC A
     WHERE A.PAIS_COD_PAIS = PSCODIGOPAIS
       AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
       AND A.COD_CURS_CAPA = PSCODCURSO
       AND A.EST_REGI = INDICADOR_ACTIVO;

    /* Invocando proceso de Registro de Aptas */
    OPEN CURSORAPTAS;
    LNPAGINA    := 0;
    LBCALIFICAR := FALSE;
    LOOP
      FETCH CURSORAPTAS BULK COLLECT
        INTO TABLAREGISTROAPTAS LIMIT W_FILAS;
      IF TABLAREGISTROAPTAS.COUNT > 0 THEN
        LNPAGINA := LNPAGINA + 1;
        FOR X IN TABLAREGISTROAPTAS.FIRST .. TABLAREGISTROAPTAS.LAST LOOP
          REGISTROAPTAS := TABLAREGISTROAPTAS(X);
          LBINSERTAR    := FALSE;
          LBPROCESAR    := TRUE;

          IF PSTIPO = 'A' THEN
            /* CALIFICACION AUTOMATICA */
            BEGIN
              SELECT A.COD_CLIE
                INTO LSCODCLIEN
                FROM EDU_MAEST_CLIEN A
               WHERE A.PAIS_COD_PAIS = PSCODIGOPAIS
                 AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
                 AND A.COD_CLIE = REGISTROAPTAS.COD_CLIE;
            EXCEPTION
              WHEN NO_DATA_FOUND THEN
                LBPROCESAR := FALSE;
            END;

            /* seteando variables */
            LSINDICADORENVIO := INDICADOR_NO;
            LSESTADOAPTAS    := INDICADOR_PENDIENTE;
            -- IF registroAptas.Ind_Posi_Egre = INDICADOR_SI THEN
            --Ingresan aquellas consultoras que son realmente posible egreso
            IF REGISTROAPTAS.IND_POSI_EGRE_TEMP = INDICADOR_SI THEN
              LSINDICADORENVIO := INDICADOR_NO;
              -- Debido q no han pasado pedido
              IF REGPARAMCURSOS.IND_COST_CURS = INDICADOR_NO THEN
                LSESTADOAPTAS := INDICADOR_POR_PROGRAMAR;
              ELSE
                LSESTADOAPTAS := INDICADOR_POR_CONFIRMAR;
              END IF;
            END IF;

            /* si existe el cliente en Maestro de Clientes */
            IF LBPROCESAR THEN
              BEGIN
                SELECT A.CLIE_COD_CLIE
                  INTO LSCODCLIEN
                  FROM EDU_HISTO_APTAS A
                 WHERE A.CCAP_COD_CURS_CAPA = PSCODCURSO
                   AND A.PAIS_COD_PAIS = PSCODIGOPAIS
                   AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
                   AND A.CLIE_COD_CLIE = REGISTROAPTAS.COD_CLIE;
              EXCEPTION
                WHEN NO_DATA_FOUND THEN
                  LBINSERTAR := TRUE;
                  INSERT INTO EDU_HISTO_APTAS
                    (PAIS_COD_PAIS,
                     EMCO_COD_EMPR_COME,
                     CCAP_COD_CURS_CAPA,
                     CLIE_COD_CLIE,
                     CAM_PRIM_CALI_APTA,
                     CAM_ULTI_CALI_APTA,
                     TIP_CALI_APTA,
                     NUM_INVI,
                     IND_INIC_CALI_APTA,
                     IND_FINA_CALI_APTA,
                     IND_CURS_COST,
                     IND_ENVI,
                     IND_ENVI_PROG,
                     EST_CAPA,
                     USU_DIGI,
                     FEC_DIGI,
                     EST_REGI,
                     IND_POSI_EGRE)
                  VALUES
                    (PSCODIGOPAIS,
                     PSCODEMPRESA,
                     PSCODCURSO,
                     REGISTROAPTAS.COD_CLIE,
                     PSCODPERIODO,
                     PSCODPERIODO,
                     'R',
                     1,
                     'A',
                     'A',
                     REGPARAMCURSOS.IND_COST_CURS,
                     LSINDICADORENVIO,
                     'N',
                     LSESTADOAPTAS,
                     PSUSUARIO,
                     SYSDATE,
                     INDICADOR_ACTIVO,
                     REGISTROAPTAS.IND_POSI_EGRE);

                  LBCALIFICAR := TRUE;

                  /* Actualizando en el Historico de Calificacion de Aptas */
                  INSERT INTO EDU_HISTO_CALIF_APTAS
                    (PAIS_COD_PAIS,
                     EMCO_COD_EMPR_COME,
                     CCAP_COD_CURS_CAPA,
                     CAM_PROC,
                     CLIE_COD_CLIE,
                     CAM_PRIM_CALI_APTA,
                     CAM_ULTI_CALI_APTA,
                     TIP_CALI_APTA,
                     NUM_INVI,
                     IND_INIC_CALI_APTA,
                     IND_FINA_CALI_APTA,
                     IND_CURS_COST,
                     IND_ENVI,
                     EST_CAPA,
                     USU_DIGI,
                     FEC_DIGI,
                     EST_REGI,
                     IND_POSI_EGRE)
                  VALUES
                    (PSCODIGOPAIS,
                     PSCODEMPRESA,
                     PSCODCURSO,
                     PSCODPERIODO,
                     REGISTROAPTAS.COD_CLIE,
                     PSCODPERIODO,
                     PSCODPERIODO,
                     'R',
                     1,
                     'A',
                     'A',
                     REGPARAMCURSOS.IND_COST_CURS,
                     LSINDICADORENVIO,
                     'PC',
                     PSUSUARIO,
                     SYSDATE,
                     INDICADOR_ACTIVO,
                     REGISTROAPTAS.IND_POSI_EGRE);

              END;

              /* El update solo se efectuará a las consultoras que hayan sido calificadas como Aptas en una
              CAMPAÑA menor a la campaña de proceso. El filtro de dichas consultoras se realizó previamente en
              en la funcion EDU_PR_CALIF_APTAS_AUTOM */
              IF (NOT LBINSERTAR) THEN
                UPDATE EDU_HISTO_APTAS A
                   SET CAM_ULTI_CALI_APTA = PSCODPERIODO,
                       IND_ENVI           = LSINDICADORENVIO,
                       NUM_INVI           = NUM_INVI + 1,
                       IND_ENVI_PROG      = 'N',
                       TIP_CALI_APTA      = 'R',
                       IND_FINA_CALI_APTA = 'A',
                       -- IND_CURS_COST = regParamCursos.Ind_Cost_Curs,
                       EST_CAPA      = LSESTADOAPTAS,
                       USU_MODI      = PSUSUARIO,
                       FEC_MODI      = SYSDATE,
                       EST_REGI      = INDICADOR_ACTIVO,
                       IND_POSI_EGRE = REGISTROAPTAS.IND_POSI_EGRE
                 WHERE A.CCAP_COD_CURS_CAPA = PSCODCURSO
                   AND A.PAIS_COD_PAIS = PSCODIGOPAIS
                   AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
                   AND A.CLIE_COD_CLIE = REGISTROAPTAS.COD_CLIE;
                LBCALIFICAR := TRUE;

                /* Actualizando en el Historico de Calificacion de Aptas */
                BEGIN
                  INSERT INTO EDU_HISTO_CALIF_APTAS
                    (PAIS_COD_PAIS,
                     EMCO_COD_EMPR_COME,
                     CCAP_COD_CURS_CAPA,
                     CAM_PROC,
                     CLIE_COD_CLIE,
                     CAM_PRIM_CALI_APTA,
                     CAM_ULTI_CALI_APTA,
                     TIP_CALI_APTA,
                     NUM_INVI,
                     IND_INIC_CALI_APTA,
                     IND_FINA_CALI_APTA,
                     IND_CURS_COST,
                     IND_ENVI,
                     EST_CAPA,
                     USU_DIGI,
                     FEC_DIGI,
                     EST_REGI,
                     IND_POSI_EGRE)
                    SELECT PAIS_COD_PAIS,
                           EMCO_COD_EMPR_COME,
                           CCAP_COD_CURS_CAPA,
                           PSCODPERIODO,
                           CLIE_COD_CLIE,
                           CAM_PRIM_CALI_APTA,
                           CAM_ULTI_CALI_APTA,
                           TIP_CALI_APTA,
                           NUM_INVI,
                           IND_INIC_CALI_APTA,
                           IND_FINA_CALI_APTA,
                           IND_CURS_COST,
                           IND_ENVI,
                           'PC',
                           USU_DIGI,
                           FEC_DIGI,
                           EST_REGI,
                           IND_POSI_EGRE
                      FROM EDU_HISTO_APTAS A
                     WHERE A.CCAP_COD_CURS_CAPA = PSCODCURSO
                       AND A.PAIS_COD_PAIS = PSCODIGOPAIS
                       AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
                       AND A.CLIE_COD_CLIE = REGISTROAPTAS.COD_CLIE;
                EXCEPTION
                  WHEN DUP_VAL_ON_INDEX THEN
                    UPDATE EDU_HISTO_CALIF_APTAS A
                       SET CAM_ULTI_CALI_APTA = PSCODPERIODO,
                           IND_ENVI           = LSINDICADORENVIO,
                           NUM_INVI           = NUM_INVI + 1,
                           TIP_CALI_APTA      = 'R',
                           IND_FINA_CALI_APTA = 'A',
                           --IND_CURS_COST = regParamCursos.Ind_Cost_Curs,
                           --EST_CAPA = lsEstadoAptas,
                           USU_MODI      = PSUSUARIO,
                           FEC_MODI      = SYSDATE,
                           EST_REGI      = INDICADOR_ACTIVO,
                           IND_POSI_EGRE = REGISTROAPTAS.IND_POSI_EGRE
                     WHERE A.CLIE_COD_CLIE = REGISTROAPTAS.COD_CLIE
                       AND A.CAM_PROC = PSCODPERIODO
                       AND A.CCAP_COD_CURS_CAPA = PSCODCURSO
                       AND A.PAIS_COD_PAIS = PSCODIGOPAIS
                       AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA;
                END;

              END IF;
            END IF;
          ELSE
            /* CALIFICACION A DEMANDA */
            UPDATE EDU_HISTO_APTAS A
               SET CAM_ULTI_CALI_APTA = PSCODPERIODO,
                   IND_ENVI           = 'N',
                   NUM_INVI           = NUM_INVI + 1,
                   IND_ENVI_PROG      = 'N',
                   TIP_CALI_APTA      = 'E',
                   IND_FINA_CALI_APTA = 'D',
                   IND_CURS_COST      = 'S',
                   EST_CAPA           = INDICADOR_PENDIENTE,
                   USU_MODI           = PSUSUARIO,
                   FEC_MODI           = SYSDATE,
                   EST_REGI           = INDICADOR_ACTIVO,
                   IND_POSI_EGRE      = REGISTROAPTAS.IND_POSI_EGRE

             WHERE A.CCAP_COD_CURS_CAPA = PSCODCURSO
               AND A.CLIE_COD_CLIE = REGISTROAPTAS.COD_CLIE
               AND A.PAIS_COD_PAIS = PSCODIGOPAIS
               AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA;
            LBCALIFICAR := TRUE;

            /* Actualizando en el Historico de Calificacion de Aptas */
            BEGIN
              INSERT INTO EDU_HISTO_CALIF_APTAS
                (PAIS_COD_PAIS,
                 EMCO_COD_EMPR_COME,
                 CCAP_COD_CURS_CAPA,
                 CAM_PROC,
                 CLIE_COD_CLIE,
                 CAM_PRIM_CALI_APTA,
                 CAM_ULTI_CALI_APTA,
                 TIP_CALI_APTA,
                 NUM_INVI,
                 IND_INIC_CALI_APTA,
                 IND_FINA_CALI_APTA,
                 IND_CURS_COST,
                 IND_ENVI,
                 EST_CAPA,
                 USU_DIGI,
                 FEC_DIGI,
                 EST_REGI,
                 IND_POSI_EGRE)
                SELECT PAIS_COD_PAIS,
                       EMCO_COD_EMPR_COME,
                       CCAP_COD_CURS_CAPA,
                       PSCODPERIODO,
                       CLIE_COD_CLIE,
                       CAM_PRIM_CALI_APTA,
                       CAM_ULTI_CALI_APTA,
                       TIP_CALI_APTA,
                       NUM_INVI,
                       IND_INIC_CALI_APTA,
                       IND_FINA_CALI_APTA,
                       IND_CURS_COST,
                       IND_ENVI,
                       EST_CAPA,
                       USU_DIGI,
                       FEC_DIGI,
                       EST_REGI,
                       IND_POSI_EGRE
                  FROM EDU_HISTO_APTAS A
                 WHERE A.CCAP_COD_CURS_CAPA = PSCODCURSO
                   AND A.CLIE_COD_CLIE = REGISTROAPTAS.COD_CLIE
                   AND A.PAIS_COD_PAIS = PSCODIGOPAIS
                   AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA;
            EXCEPTION
              WHEN DUP_VAL_ON_INDEX THEN
                UPDATE EDU_HISTO_CALIF_APTAS A
                   SET CAM_ULTI_CALI_APTA = PSCODPERIODO,
                       IND_ENVI           = 'N',
                       NUM_INVI           = NUM_INVI + 1,
                       TIP_CALI_APTA      = 'E',
                       IND_FINA_CALI_APTA = 'D',
                       IND_CURS_COST      = 'S',
                       EST_CAPA           = INDICADOR_PENDIENTE,
                       USU_MODI           = PSUSUARIO,
                       FEC_MODI           = SYSDATE,
                       EST_REGI           = INDICADOR_ACTIVO,
                       IND_POSI_EGRE      = REGISTROAPTAS.IND_POSI_EGRE
                 WHERE A.CLIE_COD_CLIE = REGISTROAPTAS.COD_CLIE
                   AND A.CAM_PROC = PSCODPERIODO
                   AND A.CCAP_COD_CURS_CAPA = PSCODCURSO
                   AND A.PAIS_COD_PAIS = PSCODIGOPAIS
                   AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA;
            END;
          END IF;
        END LOOP;
      END IF;
      EXIT WHEN CURSORAPTAS%NOTFOUND;
    END LOOP;
    CLOSE CURSORAPTAS;

    /* Actualizando el estado del curso a ACTIVO */
    IF LBCALIFICAR THEN
      UPDATE EDU_PARAM_CURSO_CAPAC A
         SET A.EST_CURS_CAPA = 'A'
       WHERE A.PAIS_COD_PAIS = PSCODIGOPAIS
         AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
         AND A.COD_CURS_CAPA = PSCODCURSO;
    END IF;

    /* borrando tablas temporales */
    DELETE FROM EDU_GTT_CLIEN_PAIS;
    DELETE FROM EDU_GTT_CLIEN;

    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR EDU_PR_REGIS_CALIF_APTAS: ' ||
                              LS_SQLERRM || ' Pagina: ' || LNPAGINA || ' ' ||
                              ' Reg: ' || LNX);
  END EDU_PR_REGIS_CALIF_APTAS;

  /***************************************************************************
  Descripcion : Procedimiento que realiza el Proceso de Bloqueo de Consultoras
   para aquellas que han pasado Pedido
   de Aptas
  Fecha Creacion : 08/11/2007
  Autor : Carlos Bazalar
  Parametros :
   psCodigoPais : Codigo de Pais
   psCodEmpresa : Codigo de Empresa
   psCodPeriodo : Campaña de Proceso
   psCodCurso : Codigo de Curso de Capacitación
   pnNumCampaCursoInvitacion : Numero de Invitacion
   psUsuario : Usuario
  ***************************************************************************/
  PROCEDURE EDU_PR_PROCE_BLOQU_CONSU(PSCODIGOPAIS              VARCHAR2,
                                     PSCODEMPRESA              VARCHAR2,
                                     PSCODPERIODO              VARCHAR2,
                                     PSCODCURSO                VARCHAR2,
                                     PNNUMCAMPACURSOINVITACION NUMBER,
                                     PSUSUARIO                 VARCHAR2) IS

  BEGIN
    EDU_PR_PROCE_BLOQU_CONSU_PENDI(PSCODIGOPAIS,
                                   PSCODEMPRESA,
                                   PSCODPERIODO,
                                   PSCODCURSO,
                                   PNNUMCAMPACURSOINVITACION,
                                   PSUSUARIO);
    EDU_PR_PROCE_BLOQU_CONSU_PROGR(PSCODIGOPAIS,
                                   PSCODEMPRESA,
                                   PSCODPERIODO,
                                   PSCODCURSO,
                                   PNNUMCAMPACURSOINVITACION,
                                   PSUSUARIO);
  EXCEPTION
    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR EDU_PR_PROCE_BLOQU_CONSU: ' ||
                              LS_SQLERRM);
  END EDU_PR_PROCE_BLOQU_CONSU;

  /***************************************************************************
  Descripcion : Procedimiento que realiza el Proceso de Bloqueo de Consultoras
   (Para Consultoras en Estado PENDIENTE)
  Fecha Creacion : 15/01/2008
  Autor : Carlos Bazalar
  Parametros :
   psCodigoPais : Codigo de Pais
   psCodEmpresa : Codigo de Empresa
   psCodPeriodo : Campaña de Proceso
   psCodCurso : Codigo de Curso de Capacitación
   pnNumCampaCursoInvitacion : Numero de Invitacion
   psUsuario : Usuario
  ***************************************************************************/
  PROCEDURE EDU_PR_PROCE_BLOQU_CONSU_PENDI(PSCODIGOPAIS              VARCHAR2,
                                           PSCODEMPRESA              VARCHAR2,
                                           PSCODPERIODO              VARCHAR2,
                                           PSCODCURSO                VARCHAR2,
                                           PNNUMCAMPACURSOINVITACION NUMBER,
                                           PSUSUARIO                 VARCHAR2) IS
    CURSOR CCONSULTORAS(VSPRIMERACALI VARCHAR2) IS
      SELECT A.CLIE_COD_CLIE, A.CAM_PRIM_CALI_APTA, C.COD_REGI
        FROM EDU_HISTO_APTAS A, EDU_MAEST_CLIEN C
       WHERE A.CCAP_COD_CURS_CAPA = PSCODCURSO
         AND A.PAIS_COD_PAIS = PSCODIGOPAIS
         AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
         AND A.EST_CAPA = INDICADOR_PENDIENTE
         AND A.CAM_PRIM_CALI_APTA = VSPRIMERACALI
         AND A.CAM_ULTI_CALI_APTA < PSCODPERIODO
         AND A.NUM_INVI = PNNUMCAMPACURSOINVITACION
         AND A.EST_REGI = INDICADOR_ACTIVO

         AND C.IND_BLOQ <> INDICADOR_SI
         AND C.PAIS_COD_PAIS = A.PAIS_COD_PAIS
         AND C.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
         AND C.COD_CLIE = A.CLIE_COD_CLIE;

    TYPE T_CODCONSU IS TABLE OF EDU_HISTO_APTAS.CLIE_COD_CLIE%TYPE;
    TYPE T_CAMPPRIM IS TABLE OF EDU_HISTO_APTAS.CAM_PRIM_CALI_APTA%TYPE;
    TYPE T_CODREGI IS TABLE OF EDU_MAEST_CLIEN.COD_REGI%TYPE;

    V_CODCONSU   T_CODCONSU;
    V_CAMPPRIM   T_CAMPPRIM;
    V_CODREGI    T_CODREGI;
    LBDUPLICADO  BOOLEAN;
    LSCAMPAPRIMA VARCHAR2(6);

  BEGIN
    LSCAMPAPRIMA := EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_CODIG_PERIO(PSCODPERIODO,
                                                                 (-1) *
                                                                 PNNUMCAMPACURSOINVITACION);

    /* Recorriendo Lista de Consultoras */
    OPEN CCONSULTORAS(LSCAMPAPRIMA);
    LOOP
      FETCH CCONSULTORAS BULK COLLECT
        INTO V_CODCONSU, V_CAMPPRIM, V_CODREGI LIMIT W_FILAS;
      IF V_CODCONSU.COUNT > 0 THEN
        FOR X IN V_CODCONSU.FIRST .. V_CODCONSU.LAST LOOP

          /* Grabando en la tabla Historica de Bloqueo */
          BEGIN
            INSERT INTO EDU_HISTO_BLOQU_CONSU
              (PAIS_COD_PAIS,
               EMCO_COD_EMPR_COME,
               CLIE_COD_CLIE,
               CAM_PROC,
               CAM_PRIM_CALI_APTA,
               NUM_INVI,
               REGI_COD_REGI,
               USU_DIGI,
               FEC_DIGI,
               EST_REGI)
            VALUES
              (PSCODIGOPAIS,
               PSCODEMPRESA,
               V_CODCONSU(X),
               PSCODPERIODO,
               V_CAMPPRIM(X),
               PNNUMCAMPACURSOINVITACION,
               V_CODREGI(X),
               PSUSUARIO,
               SYSDATE,
               '1');
          EXCEPTION
            WHEN DUP_VAL_ON_INDEX THEN
              UPDATE EDU_HISTO_BLOQU_CONSU
                 SET CAM_PRIM_CALI_APTA = V_CAMPPRIM(X),
                     NUM_INVI           = PNNUMCAMPACURSOINVITACION,
                     REGI_COD_REGI      = V_CODREGI(X),
                     USU_MODI           = PSUSUARIO,
                     FEC_MODI           = SYSDATE,
                     EST_REGI           = INDICADOR_ACTIVO
               WHERE PAIS_COD_PAIS = PSCODIGOPAIS
                 AND EMCO_COD_EMPR_COME = PSCODEMPRESA
                 AND CLIE_COD_CLIE = V_CODCONSU(X)
                 AND CAM_PROC = PSCODPERIODO;
          END;

          /* Actualizando indicador en el Maestro de cliente */
          UPDATE EDU_MAEST_CLIEN A
             SET A.IND_BLOQ = INDICADOR_SI,
                 A.USU_MODI = PSUSUARIO,
                 A.FEC_MODI = SYSDATE
           WHERE A.PAIS_COD_PAIS = PSCODIGOPAIS
             AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
             AND A.COD_CLIE = V_CODCONSU(X);

          /* Insertando en la tabla temporal para luego ser enviada al proceso comercial */
          LBDUPLICADO := FALSE;
          BEGIN
            INSERT INTO EDU_GTT_HISTO_BLOQU_CONSU
              (COD_PAIS, COD_EMPR_COME, COD_CLIE, CAM_PROC)
            VALUES
              (PSCODIGOPAIS, PSCODEMPRESA, V_CODCONSU(X), PSCODPERIODO);
          EXCEPTION
            WHEN DUP_VAL_ON_INDEX THEN
              LBDUPLICADO := TRUE;
          END;

        END LOOP;
      END IF;
      EXIT WHEN CCONSULTORAS%NOTFOUND;
    END LOOP;
    CLOSE CCONSULTORAS;

  EXCEPTION
    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR EDU_PR_PROCE_BLOQU_CONSU_PENDI: ' ||
                              LS_SQLERRM);
  END EDU_PR_PROCE_BLOQU_CONSU_PENDI;

  /***************************************************************************
  Descripcion : Procedimiento que realiza el Proceso de Bloqueo de Consultoras
   (Para Consultoras en Estado PROGRAMADA)
  Fecha Creacion : 15/01/2008
  Autor : Carlos Bazalar
  Parametros :
   psCodigoPais : Codigo de Pais
   psCodEmpresa : Codigo de Empresa
   psCodPeriodo : Campaña de Proceso
   psCodCurso : Codigo de Curso de Capacitación
   pnNumCampaCursoInvitacion : Numero de Invitacion
   psUsuario : Usuario
  ***************************************************************************/
  PROCEDURE EDU_PR_PROCE_BLOQU_CONSU_PROGR(PSCODIGOPAIS              VARCHAR2,
                                           PSCODEMPRESA              VARCHAR2,
                                           PSCODPERIODO              VARCHAR2,
                                           PSCODCURSO                VARCHAR2,
                                           PNNUMCAMPACURSOINVITACION NUMBER,
                                           PSUSUARIO                 VARCHAR2) IS

    CURSOR CCONSULTORASPR(VSPRIMERACALI VARCHAR2) IS
      SELECT A.CLIE_COD_CLIE, A.CAM_PRIM_CALI_APTA, C.COD_REGI
        FROM EDU_HISTO_APTAS A, EDU_MAEST_CLIEN C, EDU_HISTO_PEDID_CONSU D
       WHERE A.CCAP_COD_CURS_CAPA = PSCODCURSO
         AND A.PAIS_COD_PAIS = PSCODIGOPAIS
         AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
         AND A.EST_CAPA = INDICADOR_PROGRAMADA
         AND A.CAM_PRIM_CALI_APTA = VSPRIMERACALI
         AND A.CAM_ULTI_CALI_APTA < PSCODPERIODO
         AND A.NUM_INVI = PNNUMCAMPACURSOINVITACION
         AND A.EST_REGI = INDICADOR_ACTIVO

         AND C.IND_BLOQ <> INDICADOR_SI
         AND C.PAIS_COD_PAIS = A.PAIS_COD_PAIS
         AND C.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
         AND C.COD_CLIE = A.CLIE_COD_CLIE

         AND D.PAIS_COD_PAIS = A.PAIS_COD_PAIS
         AND D.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
         AND D.COD_CLIE = A.CLIE_COD_CLIE
         AND D.CAM_PROC = PSCODPERIODO
         AND D.IND_PEDI = '1';

    TYPE T_CODCONSU IS TABLE OF EDU_HISTO_APTAS.CLIE_COD_CLIE%TYPE;
    TYPE T_CAMPPRIM IS TABLE OF EDU_HISTO_APTAS.CAM_PRIM_CALI_APTA%TYPE;
    TYPE T_CODREGI IS TABLE OF EDU_MAEST_CLIEN.COD_REGI%TYPE;

    V_CODCONSU   T_CODCONSU;
    V_CAMPPRIM   T_CAMPPRIM;
    V_CODREGI    T_CODREGI;
    LBDUPLICADO  BOOLEAN;
    LSCAMPAPRIMA VARCHAR2(6);

  BEGIN
    LSCAMPAPRIMA := EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_CODIG_PERIO(PSCODPERIODO,
                                                                 (-1) *
                                                                 PNNUMCAMPACURSOINVITACION);

    /* Recorriendo Lista de Consultoras PROGRAMADAS*/
    OPEN CCONSULTORASPR(LSCAMPAPRIMA);
    LOOP
      FETCH CCONSULTORASPR BULK COLLECT
        INTO V_CODCONSU, V_CAMPPRIM, V_CODREGI LIMIT W_FILAS;
      IF V_CODCONSU.COUNT > 0 THEN
        FOR X IN V_CODCONSU.FIRST .. V_CODCONSU.LAST LOOP

          /* Grabando en la tabla Historica de Bloqueo */
          BEGIN
            INSERT INTO EDU_HISTO_BLOQU_CONSU
              (PAIS_COD_PAIS,
               EMCO_COD_EMPR_COME,
               CLIE_COD_CLIE,
               CAM_PROC,
               CAM_PRIM_CALI_APTA,
               NUM_INVI,
               REGI_COD_REGI,
               USU_DIGI,
               FEC_DIGI,
               EST_REGI)
            VALUES
              (PSCODIGOPAIS,
               PSCODEMPRESA,
               V_CODCONSU(X),
               PSCODPERIODO,
               V_CAMPPRIM(X),
               PNNUMCAMPACURSOINVITACION,
               V_CODREGI(X),
               PSUSUARIO,
               SYSDATE,
               '1');
          EXCEPTION
            WHEN DUP_VAL_ON_INDEX THEN
              UPDATE EDU_HISTO_BLOQU_CONSU
                 SET CAM_PRIM_CALI_APTA = V_CAMPPRIM(X),
                     NUM_INVI           = PNNUMCAMPACURSOINVITACION,
                     REGI_COD_REGI      = V_CODREGI(X),
                     USU_MODI           = PSUSUARIO,
                     FEC_MODI           = SYSDATE,
                     EST_REGI           = INDICADOR_ACTIVO
               WHERE CLIE_COD_CLIE = V_CODCONSU(X)
                 AND CAM_PROC = PSCODPERIODO
                 AND PAIS_COD_PAIS = PSCODIGOPAIS
                 AND EMCO_COD_EMPR_COME = PSCODEMPRESA;
          END;

          /* Actualizando indicador en el Maestro de cliente */
          UPDATE EDU_MAEST_CLIEN A
             SET A.IND_BLOQ = INDICADOR_SI,
                 A.USU_MODI = PSUSUARIO,
                 A.FEC_MODI = SYSDATE
           WHERE A.PAIS_COD_PAIS = PSCODIGOPAIS
             AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
             AND A.COD_CLIE = V_CODCONSU(X);

          /* Cambiando el estado a PENDIENTE */
          UPDATE EDU_HISTO_APTAS X
             SET X.EST_CAPA = INDICADOR_PENDIENTE,
                 X.USU_MODI = PSUSUARIO,
                 X.FEC_MODI = SYSDATE
           WHERE X.CCAP_COD_CURS_CAPA = PSCODCURSO
             AND X.CLIE_COD_CLIE = V_CODCONSU(X)
             AND X.PAIS_COD_PAIS = PSCODIGOPAIS
             AND X.EMCO_COD_EMPR_COME = PSCODEMPRESA ;

          /* Insertando en la tabla temporal para luego ser enviada al proceso comercial */
          LBDUPLICADO := FALSE;
          BEGIN
            INSERT INTO EDU_GTT_HISTO_BLOQU_CONSU
              (COD_PAIS, COD_EMPR_COME, COD_CLIE, CAM_PROC)
            VALUES
              (PSCODIGOPAIS, PSCODEMPRESA, V_CODCONSU(X), PSCODPERIODO);
          EXCEPTION
            WHEN DUP_VAL_ON_INDEX THEN
              LBDUPLICADO := TRUE;
          END;

        END LOOP;
      END IF;
      EXIT WHEN CCONSULTORASPR%NOTFOUND;
    END LOOP;
    CLOSE CCONSULTORASPR;
  EXCEPTION
    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR EDU_PR_PROCE_BLOQU_CONSU_PROGR: ' ||
                              LS_SQLERRM);
  END EDU_PR_PROCE_BLOQU_CONSU_PROGR;

  /***************************************************************************
  Descripcion : Procedimiento que identifica aquellas consultoras que tienen
   codigo de planilla y no se ha relacionado con ningun codigo de
   curso dictado
  Fecha Creacion : 02/02/2008
  Autor : Carlos Bazalar
  Parametros :
   psCodigoPais : Codigo de Pais
   psCodEmpresa : Codigo de Empresa
   psCodCurso : Codigo de Curso de Capacitación
   psCodPeriodo : Campaña de Proceso
   psUsuario : Usuario
  ***************************************************************************/
  PROCEDURE EDU_PR_CONSU_PLANI_NREGIS(PSCODIGOPAIS VARCHAR2,
                                      PSCODEMPRESA VARCHAR2,
                                      PSCODCURSO   VARCHAR2,
                                      PSCODPERIODO VARCHAR2,
                                      PSUSUARIO    VARCHAR2) IS
    CURSOR CURSORAPTAS IS
      SELECT A.COD_CLIE, B.COD_PLAN_PROG, B.EST_CAPA
        FROM EDU_GTT_CLIEN_PAIS A, EDU_HISTO_APTAS B
       WHERE A.COD_CURS_CAPA = PSCODCURSO
         AND A.COD_PAIS = PSCODIGOPAIS
         AND A.COD_EMPR_COME = PSCODEMPRESA


         AND B.PAIS_COD_PAIS = A.COD_PAIS
         AND B.EMCO_COD_EMPR_COME = A.COD_EMPR_COME
         AND B.CCAP_COD_CURS_CAPA = A.COD_CURS_CAPA
         AND B.CLIE_COD_CLIE = A.COD_CLIE
         AND B.COD_PLAN_PROG IS NOT NULL;

    TYPE T_CODPLAN IS TABLE OF EDU_HISTO_APTAS.COD_PLAN_PROG%TYPE;
    TYPE T_CODCONS IS TABLE OF EDU_HISTO_APTAS.CLIE_COD_CLIE%TYPE;
    TYPE T_ESTCONS IS TABLE OF EDU_HISTO_APTAS.CLIE_COD_CLIE%TYPE;

    V_ESTCONS     T_ESTCONS;
    V_CODCONS     T_CODCONS;
    V_CODPLAN     T_CODPLAN;
    LSCODCLIEN    EDU_HISTO_APTAS.CLIE_COD_CLIE%TYPE;
    LSCODPLANILLA EDU_HISTO_PLANI_INSTR.COD_PLAN_PROG%TYPE;
    LBENCONTRO    BOOLEAN;
    LNCONTADOR    NUMBER;

  BEGIN
    /* Invocando cursor con lista de consultoras a ser calificadas */
    OPEN CURSORAPTAS;
    LOOP
      FETCH CURSORAPTAS BULK COLLECT
        INTO V_CODCONS, V_CODPLAN, V_ESTCONS LIMIT W_FILAS;
      IF V_CODCONS.COUNT > 0 THEN
        FOR X IN V_CODCONS.FIRST .. V_CODCONS.LAST LOOP
          /* Verificando que la planilla se encuentre en el rango de Planillas
          para la campaña de proceso */
          LNCONTADOR := 0;
          SELECT COUNT(1)
            INTO LNCONTADOR
            FROM EDU_CONTR_GENER_PLANI A
           WHERE A.PAIS_COD_PAIS = PSCODIGOPAIS
             AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
             AND A.CCAP_COD_CURS_CAPA = PSCODCURSO
             AND A.PERI_COD_PERI = PSCODPERIODO
             AND A.COD_PLAN_PROG_INIC >= V_CODPLAN(X)
             AND A.COD_PLAN_PROG_FINA <= V_CODPLAN(X);

          /* Verificando que la planilla no posea curso de dictado */
          LBENCONTRO := TRUE;
          IF LNCONTADOR > 0 THEN
            BEGIN
              SELECT A.COD_PLAN_PROG
                INTO LSCODPLANILLA
                FROM EDU_HISTO_PLANI_INSTR A
               WHERE A.PAIS_COD_PAIS = PSCODIGOPAIS
                 AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
                 AND A.CCAP_COD_CURS_CAPA = PSCODCURSO
                 AND A.COD_PLAN_PROG = V_CODPLAN(X)
                 AND A.SIT_PLAN_PROG = 'A'
                 AND A.CDIC_COD_CURS_DICT IS NULL;
            EXCEPTION
              WHEN NO_DATA_FOUND THEN
                LBENCONTRO := FALSE;
            END;
          ELSE
            LBENCONTRO := FALSE;
          END IF;

          /* Actualizando la Tabla de Consultoras de Planillas no registradas */
          IF LBENCONTRO THEN
            BEGIN
              INSERT INTO EDU_PLANI_PROGR_CONSU_NOREG
                (PAIS_COD_PAIS,
                 EMCO_COD_EMPR_COME,
                 CAM_PROC,
                 CCAP_COD_CURS_CAPA,
                 CLIE_COD_CLIE,
                 COD_PLAN_PROG,
                 EST_CAPA,
                 USU_DIGI,
                 FEC_DIGI)
              VALUES
                (PSCODIGOPAIS,
                 PSCODEMPRESA,
                 PSCODPERIODO,
                 PSCODCURSO,
                 V_CODCONS(X),
                 V_CODPLAN(X),
                 V_ESTCONS(X),
                 PSUSUARIO,
                 SYSDATE);
            EXCEPTION
              WHEN DUP_VAL_ON_INDEX THEN
                UPDATE EDU_PLANI_PROGR_CONSU_NOREG A
                   SET COD_PLAN_PROG = V_CODPLAN(X),
                       EST_CAPA      = V_ESTCONS(X),
                       USU_MODI      = PSUSUARIO,
                       FEC_MODI      = SYSDATE
                 WHERE A.PAIS_COD_PAIS = PSCODIGOPAIS
                   AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
                   AND A.CAM_PROC = PSCODPERIODO
                   AND A.CCAP_COD_CURS_CAPA = PSCODCURSO
                   AND A.CLIE_COD_CLIE = V_CODCONS(X);
            END;
          END IF;

        END LOOP;
      END IF;
      EXIT WHEN CURSORAPTAS%NOTFOUND;
    END LOOP;
    CLOSE CURSORAPTAS;
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR EDU_PR_CONSU_PLANI_NREGIS: ' ||
                              LS_SQLERRM);
  END EDU_PR_CONSU_PLANI_NREGIS;

  /***************************************************************************
  Descripcion : Procedimiento que adiciona aquellas consultoras con posible
   egreso en planilla
  Fecha Creacion : 25/02/2008
  Autor : Carlos Bazalar
  Parametros :
   psCodigoPais : Codigo de Pais
   psCodEmpresa : Codigo de Empresa
   psCodPeriodo : Codigo de Periodo (Campaña de Proceso)
   psCodCurso : Codigo de Curso
   psCursoPreRequisito : Codigo de Curso de Capacitacion Pre-Requisito
   psIndicadorCostoCurso : Indicador Curso con costo
  ***************************************************************************/
  PROCEDURE EDU_PR_PROCE_POSIB_EGRESO(PSCODIGOPAIS              VARCHAR2,
                                      PSCODEMPRESA              VARCHAR2,
                                      PSCODPERIODO              VARCHAR2,
                                      PSCODCURSO                VARCHAR2,
                                      PSCODCURSOPREREQUISITO    VARCHAR2,
                                      PSINDICADORCOSTOCURSO     VARCHAR2,
                                      PNNUMCAMPACURSOINVITACION NUMBER)

   IS
    LSCODIGOPERIODOANTERIOR VARCHAR2(6);
    LNNUMPEDIDOSMINIMO      EDU_PARAM_PROGR_CAPAC.NUM_MINI_PEDI_PLAN_NPRO%TYPE;

  BEGIN
    LSCODIGOPERIODOANTERIOR := EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_CODIG_PERIO(PSCODPERIODO,
                                                                            -1);
    /* Se obtiene el numero de pedidos minimos */
    BEGIN
      SELECT A.NUM_MINI_PEDI_PLAN_NPRO
        INTO LNNUMPEDIDOSMINIMO
        FROM EDU_PARAM_PROGR_CAPAC A
       WHERE A.PAIS_COD_PAIS = PSCODIGOPAIS
         AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
         AND A.COD_PROG_CAPA = '01';
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        LNNUMPEDIDOSMINIMO := '0';
      WHEN OTHERS THEN
        LNNUMPEDIDOSMINIMO := '0';
    END;

    /* Se valida numero de pedidos minimos por region que se va porcesar */
    /* solose inserta regiones q pasan la validacion
    SI EL NUM PEDIDOS MINIMO > 0 SE OBTENDRAS LOS PEDIDOS DEL TEMPORAL DE PEDIDOS
     SI ES CERO SE TOMRAN DEL GTT DE REGION */
    DELETE FROM EDU_GTT_PARAM_PROCE;
    IF (LNNUMPEDIDOSMINIMO > 0) THEN
      INSERT INTO EDU_GTT_PARAM_PROCE
        (COD_PROC, COD_PARA, VAL_PARA_VARC)
        SELECT '01', '01', C.COD_REGI
          FROM EDU_TMP_PEDID_CONSU C
         WHERE C.COD_PAIS = PSCODIGOPAIS
           AND C.COD_EMPR_COME = PSCODEMPRESA
           AND C.CAM_PROC = PSCODPERIODO
         GROUP BY C.COD_REGI
        HAVING COUNT(1) >= TO_NUMBER(LNNUMPEDIDOSMINIMO);
    ELSE
      --SI EL NUM PEDIDDOS MINIMOS = 0 SE UTILIZA EL GTT DE CIERRE DE REGIONES
      INSERT INTO EDU_GTT_PARAM_PROCE
        (COD_PROC, COD_PARA, VAL_PARA_VARC)
        SELECT '01', '01', C.COD_REGI
          FROM EDU_GTT_REGIO_CIERR C
         WHERE C.COD_PAIS = PSCODIGOPAIS
           AND C.COD_EMPR_COME = PSCODEMPRESA;
    END IF;
    /* Fin de validacion */

    /* Pasando lista de consultoras */
    IF PSINDICADORCOSTOCURSO = INDICADOR_SI THEN
      /* Si el curso tiene Costo */
      INSERT INTO EDU_GTT_CLIEN_PAIS
        (COD_PAIS, COD_EMPR_COME, COD_CURS_CAPA, COD_CLIE, IND_POSI_EGRE)
        SELECT A.PAIS_COD_PAIS,
               A.EMCO_COD_EMPR_COME,
               PSCODCURSO,
               A.CLIE_COD_CLIE,
               'S'
          FROM EDU_HISTO_APTAS       A,
               EDU_HISTO_PEDID_CONSU B,
               EDU_MAEST_CLIEN       C,
               EDU_GTT_PARAM_PROCE   D
         WHERE A.CCAP_COD_CURS_CAPA = PSCODCURSOPREREQUISITO
           AND A.PAIS_COD_PAIS = PSCODIGOPAIS
           AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
           AND A.CAM_CAPA <= LSCODIGOPERIODOANTERIOR
           AND A.EST_CAPA = INDICADOR_CAPACITADA
           AND A.EST_REGI = INDICADOR_ACTIVO

           AND B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
           AND B.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
           AND B.COD_CLIE = A.CLIE_COD_CLIE
           AND B.CAM_ULTI_PEDI = LSCODIGOPERIODOANTERIOR
           AND B.IND_FACT = '1'
           AND B.IND_PEDI = '1'

           AND C.PAIS_COD_PAIS = A.PAIS_COD_PAIS
           AND C.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
           AND C.COD_CLIE = A.CLIE_COD_CLIE

           AND D.COD_PROC = '01'
           AND D.COD_PARA = '01'
           AND D.VAL_PARA_VARC = C.COD_REGI;

    ELSE
      /* Si el curso no tiene Costo */
      /* Aquellas q facturaron la campaña anterior y actualmente no estan pasando pedido */
      INSERT INTO EDU_GTT_CLIEN_PAIS
        (COD_PAIS,
         COD_EMPR_COME,
         COD_CURS_CAPA,
         COD_CLIE,
         IND_POSI_EGRE,
         IND_POSI_EGRE_TEMP)
        SELECT A.PAIS_COD_PAIS,
               A.EMCO_COD_EMPR_COME,
               A.CCAP_COD_CURS_CAPA,
               A.CLIE_COD_CLIE,
               'S',
               'S'
          FROM EDU_HISTO_APTAS       A,
               EDU_HISTO_PEDID_CONSU B,
               EDU_MAEST_CLIEN       C,
               EDU_GTT_PARAM_PROCE   D
         WHERE A.CAM_ULTI_CALI_APTA = LSCODIGOPERIODOANTERIOR
           AND A.CCAP_COD_CURS_CAPA = PSCODCURSO
           AND A.PAIS_COD_PAIS = PSCODIGOPAIS
           AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA

           AND A.EST_CAPA <> INDICADOR_CAPACITADA
           AND A.NUM_INVI < PNNUMCAMPACURSOINVITACION
           AND A.IND_CURS_COST = INDICADOR_NO
           AND A.EST_REGI = INDICADOR_ACTIVO

           AND B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
           AND B.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
           AND B.COD_CLIE = A.CLIE_COD_CLIE
           AND B.CAM_ULTI_PEDI = LSCODIGOPERIODOANTERIOR
           AND B.IND_PEDI = '1'
           AND B.IND_FACT = '1'

           AND C.PAIS_COD_PAIS = A.PAIS_COD_PAIS
           AND C.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
           AND C.COD_CLIE = A.CLIE_COD_CLIE

           AND D.COD_PROC = '01'
           AND D.COD_PARA = '01'
           AND D.VAL_PARA_VARC = C.COD_REGI;

      /* Aquellas q facturaron la campaña anterior y actualmente estan pasando pedido */
      INSERT INTO EDU_GTT_CLIEN_PAIS
        (COD_PAIS,
         COD_EMPR_COME,
         COD_CURS_CAPA,
         COD_CLIE,
         IND_POSI_EGRE,
         IND_POSI_EGRE_TEMP)
        SELECT A.PAIS_COD_PAIS,
               A.EMCO_COD_EMPR_COME,
               A.CCAP_COD_CURS_CAPA,
               A.CLIE_COD_CLIE,
               'S',
               'N'
          FROM EDU_HISTO_APTAS       A,
               EDU_HISTO_PEDID_CONSU B,
               EDU_MAEST_CLIEN       C,
               EDU_GTT_PARAM_PROCE   D
         WHERE A.CCAP_COD_CURS_CAPA = PSCODCURSO
           AND A.CAM_ULTI_CALI_APTA = LSCODIGOPERIODOANTERIOR
           AND A.PAIS_COD_PAIS = PSCODIGOPAIS
           AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
           AND A.EST_CAPA <> INDICADOR_CAPACITADA
           AND A.NUM_INVI < PNNUMCAMPACURSOINVITACION
           AND A.IND_CURS_COST = INDICADOR_NO
           AND A.EST_REGI = INDICADOR_ACTIVO

           AND B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
           AND B.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
           AND B.COD_CLIE = A.CLIE_COD_CLIE
           AND B.CAM_ULTI_PEDI = PSCODPERIODO
           AND B.IND_PEDI = '1'
           AND B.IND_FACT = '0'
           AND B.NUM_PEDI_FACT >= 1

           AND C.PAIS_COD_PAIS = A.PAIS_COD_PAIS
           AND C.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
           AND C.COD_CLIE = A.CLIE_COD_CLIE

           AND D.COD_PROC = '01'
           AND D.COD_PARA = '01'
           AND D.VAL_PARA_VARC = C.COD_REGI;

    END IF;

    /* BORRANDO DUPLICADOS */
    DELETE FROM EDU_GTT_CLIEN_PAIS A
     WHERE (A.IND_POSI_EGRE IS NULL OR A.IND_POSI_EGRE = 'N')
       AND EXISTS (SELECT X.COD_PAIS
              FROM EDU_GTT_CLIEN_PAIS X
             WHERE X.COD_PAIS = A.COD_PAIS
               AND X.COD_EMPR_COME = A.COD_EMPR_COME
               AND X.COD_CURS_CAPA = A.COD_CURS_CAPA
               AND X.COD_CLIE = A.COD_CLIE
               AND X.IND_POSI_EGRE = 'S');

    /* borrando tabla temporal */
    DELETE FROM EDU_GTT_PARAM_PROCE;

  EXCEPTION
    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR EDU_PR_PROCE_POSIB_EGRESO: ' ||
                              LS_SQLERRM);
  END EDU_PR_PROCE_POSIB_EGRESO;

  /***************************************************************************
  Descripcion : Procedimiento que genera consultoras APTAS para cursos mixtos
   (cursos sin costo). Son aquellas consultoras que han pasado
   el número máximo de invitaciones pero que van a comprar el curso
   en forma extemporanea
  Fecha Creacion : 06/03/2008
  Autor : Carlos Bazalar
  Parametros :
   psCodigoPais : Codigo de Pais
   psCodEmpresa : Codigo de Empresa
   psCodPeriodo : Codigo de Periodo (Campaña de Proceso)
  ***************************************************************************/
  PROCEDURE EDU_PR_PROCE_APTAS_MIXTO_BLOQU(PSCODIGOPAIS              VARCHAR2,
                                           PSCODEMPRESA              VARCHAR2,
                                           PSCODPERIODO              VARCHAR2,
                                           PSINDICADOREQUIMENSAJE    VARCHAR2,
                                           PSINDICADORNOMBRECOMPLETO VARCHAR2,
                                           PSNUMEROLOTE              VARCHAR2,
                                           PSUSUARIO                 VARCHAR2) IS
    TYPE T_CODCURSO IS TABLE OF EDU_HISTO_APTAS.CCAP_COD_CURS_CAPA%TYPE;
    TYPE T_CODCLIEN IS TABLE OF EDU_HISTO_APTAS.CLIE_COD_CLIE%TYPE;
    TYPE T_NOMCLIEN IS TABLE OF VARCHAR2(200);
    TYPE T_CODMENSA IS TABLE OF EDU_PARAM_MENSA.COD_MENS%TYPE;
    TYPE T_CODCLASI IS TABLE OF EDU_PARAM_CLASI_BENEF_CAPAC.COD_CLAS%TYPE;
    ---

    TYPE T_ESTADOCOMPRA IS TABLE OF EDU_PARAM_CLASI_BENEF_CAPAC.EST_REGI%TYPE;
    --

    V_CODCURSO    T_CODCURSO;
    V_CODCLIEN    T_CODCLIEN;
    V_NOMCLIEN    T_NOMCLIEN;
    V_CODMENSAGEN T_CODMENSA;
    V_CODMENSAESP T_CODMENSA;
    V_CODCLASI    T_CODCLASI;
    ---
    V_ESTADOCOMPRA T_ESTADOCOMPRA;
    --

    LSINDICADORPROCESOBLOQUEO    EDU_PARAM_PROGR_CAPAC.IND_PROC_BLOQ%TYPE;
    LSINDICADOREQUIVALENCIACLASI EDU_PARAM_PROGR_CAPAC.IND_EQUI_CLAS%TYPE;

    CURSOR CAPTAS(VSINDICADORPROCESOBLOQUEO VARCHAR2) IS
    /*WITH TEMPORAL AS
     (SELECT DISTINCT
     Y.COD_REGI
     FROM EDU_HISTO_APTAS X,
     EDU_MAEST_CLIEN Y
     WHERE X.PAIS_COD_PAIS = psCodigoPais
     AND X.EMCO_COD_EMPR_COME = psCodEmpresa
     AND X.CAM_ULTI_CALI_APTA = psCodPeriodo
     AND X.EST_CAPA = INDICADOR_PENDIENTE
     AND X.IND_ENVI = 'N'

     AND Y.PAIS_COD_PAIS = X.PAIS_COD_PAIS
     AND Y.EMCO_COD_EMPR_COME = X.EMCO_COD_EMPR_COME
     AND Y.COD_CLIE = X.CLIE_COD_CLIE
     )*/
      SELECT A.CCAP_COD_CURS_CAPA,
             A.CLIE_COD_CLIE,
             EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_NOMBR_CONSU(A.PAIS_COD_PAIS,
                                                          A.EMCO_COD_EMPR_COME,
                                                          A.CLIE_COD_CLIE,
                                                          PSINDICADORNOMBRECOMPLETO) AS NOMCON,
             EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_CODIG_MENSA(A.PAIS_COD_PAIS,
                                                          A.EMCO_COD_EMPR_COME,
                                                          PSINDICADOREQUIMENSAJE,
                                                          '2',
                                                          A.CCAP_COD_CURS_CAPA,
                                                          A.EST_CAPA,
                                                          A.NUM_INVI,
                                                          A.CLIE_COD_CLIE) AS MSGESP,
             EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_CODIG_MENSA(A.PAIS_COD_PAIS,
                                                          A.EMCO_COD_EMPR_COME,
                                                          PSINDICADOREQUIMENSAJE,
                                                          '1',
                                                          A.CCAP_COD_CURS_CAPA,
                                                          A.EST_CAPA,
                                                          A.NUM_INVI,
                                                          A.CLIE_COD_CLIE) AS MSGGEN,
             EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_CODIG_CLASI(A.PAIS_COD_PAIS,
                                                          A.EMCO_COD_EMPR_COME,
                                                          A.CCAP_COD_CURS_CAPA,
                                                          'I',
                                                          'C') AS COD_CLAS,
             DECODE(1, B.NUM_CAMP_PREV_CALI, 'C', 'E') AS EST_COMP
        FROM EDU_HISTO_APTAS       A,
             EDU_PARAM_CURSO_CAPAC B,
             EDU_MAEST_CLIEN       C,
             EDU_TMP_PEDID_CONSU   E
      -- TEMPORAL D
       WHERE A.PAIS_COD_PAIS = PSCODIGOPAIS
         AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
         AND (A.CAM_ULTI_CALI_APTA < PSCODPERIODO OR
             (A.CAM_ULTI_CALI_APTA = PSCODPERIODO AND
             A.IND_CURS_COST = 'S'))
         AND (A.EST_CAPA = INDICADOR_PENDIENTE OR
             A.EST_CAPA = INDICADOR_PROGRAMADA)
         AND A.NUM_INVI >= B.NUM_INVI
         AND A.PAIS_COD_PAIS = B.PAIS_COD_PAIS
         AND A.EMCO_COD_EMPR_COME = B.EMCO_COD_EMPR_COME
         AND A.CCAP_COD_CURS_CAPA = B.COD_CURS_CAPA
         AND EDU_FN_CURSO_APTAS_MIXTO_BLOQU(B.PAIS_COD_PAIS,
                                            B.EMCO_COD_EMPR_COME,
                                            B.COD_CURS_CAPA,
                                            VSINDICADORPROCESOBLOQUEO) = 1
         AND C.PAIS_COD_PAIS = A.PAIS_COD_PAIS
         AND C.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
         AND C.COD_CLIE = A.CLIE_COD_CLIE
         AND E.COD_PAIS = A.PAIS_COD_PAIS
         AND E.COD_EMPR_COME = A.EMCO_COD_EMPR_COME
         AND E.COD_CLIE = A.CLIE_COD_CLIE
         AND E.CAM_PROC = PSCODPERIODO
      UNION ALL
      SELECT A.CCAP_COD_CURS_CAPA,
             A.CLIE_COD_CLIE,
             EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_NOMBR_CONSU(A.PAIS_COD_PAIS,
                                                          A.EMCO_COD_EMPR_COME,
                                                          A.CLIE_COD_CLIE,
                                                          PSINDICADORNOMBRECOMPLETO) AS NOMCON,
             EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_CODIG_MENSA(A.PAIS_COD_PAIS,
                                                          A.EMCO_COD_EMPR_COME,
                                                          PSINDICADOREQUIMENSAJE,
                                                          '2',
                                                          A.CCAP_COD_CURS_CAPA,
                                                          A.EST_CAPA,
                                                          A.NUM_INVI,
                                                          A.CLIE_COD_CLIE) AS MSGESP,
             EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_CODIG_MENSA(A.PAIS_COD_PAIS,
                                                          A.EMCO_COD_EMPR_COME,
                                                          PSINDICADOREQUIMENSAJE,
                                                          '1',
                                                          A.CCAP_COD_CURS_CAPA,
                                                          A.EST_CAPA,
                                                          A.NUM_INVI,
                                                          A.CLIE_COD_CLIE) AS MSGGEN,
             EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_CODIG_CLASI(A.PAIS_COD_PAIS,
                                                          A.EMCO_COD_EMPR_COME,
                                                          A.CCAP_COD_CURS_CAPA,
                                                          'I',
                                                          'C') AS COD_CLAS,
             DECODE(1, B.NUM_CAMP_PREV_CALI, 'C', 'E') AS EST_COMP
        FROM EDU_HISTO_APTAS       A,
             EDU_PARAM_CURSO_CAPAC B,
             EDU_MAEST_CLIEN       C,
             EDU_TMP_PEDID_CONSU   E
       WHERE A.CAM_ULTI_CALI_APTA < = PSCODPERIODO
         AND A.PAIS_COD_PAIS = PSCODIGOPAIS
         AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
         AND (A.EST_CAPA = INDICADOR_PENDIENTE OR A.EST_CAPA = 'PO')
         AND A.NUM_INVI < B.NUM_INVI
         AND A.PAIS_COD_PAIS = B.PAIS_COD_PAIS
         AND A.EMCO_COD_EMPR_COME = B.EMCO_COD_EMPR_COME
         AND A.CCAP_COD_CURS_CAPA = B.COD_CURS_CAPA
         AND EDU_FN_CURSO_APTAS_MIXTO_BLOQU(B.PAIS_COD_PAIS,
                                            B.EMCO_COD_EMPR_COME,
                                            B.COD_CURS_CAPA,
                                            VSINDICADORPROCESOBLOQUEO) = 1
         AND A.IND_CURS_COST = 'S'
         AND C.PAIS_COD_PAIS = A.PAIS_COD_PAIS
         AND C.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
         AND C.COD_CLIE = A.CLIE_COD_CLIE
         AND E.COD_PAIS = A.PAIS_COD_PAIS
         AND E.COD_EMPR_COME = A.EMCO_COD_EMPR_COME
         AND E.COD_CLIE = A.CLIE_COD_CLIE
         AND E.CAM_PROC = PSCODPERIODO;

  BEGIN
    DELETE FROM EDU_GTT_APTAS_MIXTO_BLOQU;

    /* Obteniendo valor del indicador de Proceso de Bloqueo y Proceso Equivalencia*/
    SELECT A.IND_PROC_BLOQ, A.IND_EQUI_CLAS
      INTO LSINDICADORPROCESOBLOQUEO, LSINDICADOREQUIVALENCIACLASI
      FROM EDU_PARAM_PROGR_CAPAC A
     WHERE A.PAIS_COD_PAIS = PSCODIGOPAIS
       AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
       AND A.COD_PROG_CAPA = '01'
       AND A.EST_REGI = INDICADOR_ACTIVO;

    /* Recorriendo cursor */
    OPEN CAPTAS(LSINDICADORPROCESOBLOQUEO);
    LOOP
      FETCH CAPTAS BULK COLLECT
        INTO V_CODCURSO,
             V_CODCLIEN,
             V_NOMCLIEN,
             V_CODMENSAESP,
             V_CODMENSAGEN,
             V_CODCLASI,
             V_ESTADOCOMPRA LIMIT W_FILAS;
      IF V_CODCURSO.COUNT > 0 THEN
        FOR X IN V_CODCURSO.FIRST .. V_CODCURSO.LAST LOOP
          /* Insertando en tabla temporal */
          BEGIN
            INSERT INTO EDU_GTT_APTAS_MIXTO_BLOQU
              (COD_PAIS,
               COD_EMPR_COME,
               CAM_PROC,
               COD_CLIE,
               NOM_CLIE,
               COD_CURS_CAPA,
               CAM_INIC,
               CAM_FINA,
               COD_MENS_ESPE,
               COD_MENS_GENE,
               COD_CLAS,
               COD_VENT,
               COD_USUA,
               EST_COMP)
            VALUES
              (PSCODIGOPAIS,
               PSCODEMPRESA,
               PSCODPERIODO,
               V_CODCLIEN(X),
               V_NOMCLIEN(X),
               V_CODCURSO(X),
               PSCODPERIODO,
               PSCODPERIODO,
               V_CODMENSAESP(X),
               V_CODMENSAGEN(X),
               V_CODCLASI(X),
               ' ',
               PSUSUARIO,
               V_ESTADOCOMPRA(X));
          EXCEPTION
            WHEN OTHERS THEN
              NULL;
          END;

          IF (LSINDICADOREQUIVALENCIACLASI = '1') THEN
            /*Insertando en el gtt pra ser enviadas */
            EDU_PKG_PROCE_COMER.EDU_PR_INSER_EQUIV_CLASI_TEMPO(PSCODIGOPAIS,
                                                               PSCODEMPRESA,
                                                               PSCODPERIODO,
                                                               V_CODCLIEN(X),
                                                               V_CODCURSO(X),
                                                               V_CODCLASI(X),
                                                               '1');
          END IF;

          /* Actualizando en el Historico de Aptas*/
          UPDATE EDU_HISTO_APTAS A
             SET A.CAM_ULTI_CALI_APTA = PSCODPERIODO,
                 A.EST_CAPA           = INDICADOR_POR_CONFIRMAR,
                 A.IND_CURS_COST      = INDICADOR_SI,
                 A.NUM_LOTE_DIAR      = PSNUMEROLOTE,
                 A.FEC_MODI           = SYSDATE,
                 A.USU_MODI           = PSUSUARIO
           WHERE A.CCAP_COD_CURS_CAPA = V_CODCURSO(X)
             AND A.CLIE_COD_CLIE = V_CODCLIEN(X)
             AND A.PAIS_COD_PAIS = PSCODIGOPAIS
             AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA;

          /* Actualizando en el Historico de Calificacion de Aptas */
          BEGIN
            INSERT INTO EDU_HISTO_CALIF_APTAS
              (PAIS_COD_PAIS,
               EMCO_COD_EMPR_COME,
               CCAP_COD_CURS_CAPA,
               CAM_PROC,
               CLIE_COD_CLIE,
               CAM_PRIM_CALI_APTA,
               CAM_ULTI_CALI_APTA,
               TIP_CALI_APTA,
               NUM_INVI,
               IND_INIC_CALI_APTA,
               IND_FINA_CALI_APTA,
               IND_CURS_COST,
               IND_ENVI,
               EST_CAPA,
               USU_DIGI,
               FEC_DIGI,
               EST_REGI,
               IND_POSI_EGRE)
              SELECT PAIS_COD_PAIS,
                     EMCO_COD_EMPR_COME,
                     CCAP_COD_CURS_CAPA,
                     PSCODPERIODO,
                     CLIE_COD_CLIE,
                     CAM_PRIM_CALI_APTA,
                     CAM_ULTI_CALI_APTA,
                     TIP_CALI_APTA,
                     NUM_INVI,
                     IND_INIC_CALI_APTA,
                     IND_FINA_CALI_APTA,
                     IND_CURS_COST,
                     IND_ENVI,
                     INDICADOR_PENDIENTE,
                     USU_DIGI,
                     FEC_DIGI,
                     EST_REGI,
                     IND_POSI_EGRE
                FROM EDU_HISTO_APTAS A
               WHERE A.CCAP_COD_CURS_CAPA = V_CODCURSO(X)
                 AND A.CLIE_COD_CLIE = V_CODCLIEN(X)
                 AND A.PAIS_COD_PAIS = PSCODIGOPAIS
                 AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA ;
          EXCEPTION
            WHEN DUP_VAL_ON_INDEX THEN
              UPDATE EDU_HISTO_CALIF_APTAS A
                 SET A.CAM_ULTI_CALI_APTA = PSCODPERIODO,
                     A.EST_CAPA           = INDICADOR_PENDIENTE,
                     A.IND_CURS_COST      = INDICADOR_SI,
                     A.NUM_LOTE_DIAR      = PSNUMEROLOTE,
                     A.FEC_MODI           = SYSDATE,
                     A.USU_MODI           = PSUSUARIO
               WHERE A.CLIE_COD_CLIE = V_CODCLIEN(X)
                 AND A.CAM_PROC = PSCODPERIODO
                 AND A.CCAP_COD_CURS_CAPA = V_CODCURSO(X)
                 AND A.PAIS_COD_PAIS = PSCODIGOPAIS
                 AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA;
          END;
        END LOOP;
      END IF;
      EXIT WHEN CAPTAS%NOTFOUND;
    END LOOP;
    CLOSE CAPTAS;

  EXCEPTION
    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR EDU_PR_PROCE_APTAS_MIXTO_BLOQU: ' ||
                              LS_SQLERRM);
  END EDU_PR_PROCE_APTAS_MIXTO_BLOQU;

  /***************************************************************************
  Descripcion : Funcion que devuelve si el curso es valido para los cursos mixtos
  Fecha Creacion : 06/03/2008
  Autor : Carlos Bazalar
  Parametros :
   psCodigoPais : Codigo de Pais
   psCodEmpresa : Codigo de Empresa
   psCodCurso : Codigo de Curso de Capacitación
   psIndicadorProcesoBloqueo : Indicador de Proceso de Bloqueo
  ***************************************************************************/
  FUNCTION EDU_FN_CURSO_APTAS_MIXTO_BLOQU(PSCODPAIS                 VARCHAR2,
                                          PSCODEMPRESA              VARCHAR2,
                                          PSCODCURSO                VARCHAR2,
                                          PSINDICADORPROCESOBLOQUEO VARCHAR2)
    RETURN NUMBER IS
    REGPARAMCURSOS EDU_PARAM_CURSO_CAPAC%ROWTYPE;

  BEGIN
    /* Obteniendo parametrizacion del curso */
    SELECT *
      INTO REGPARAMCURSOS
      FROM EDU_PARAM_CURSO_CAPAC A
     WHERE A.PAIS_COD_PAIS = PSCODPAIS
       AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
       AND A.COD_CURS_CAPA = PSCODCURSO
       AND A.IND_COST_CURS = INDICADOR_NO
       AND A.TICC_COD_TIPO_COST_CURS = TIPO_CURSO_MIXTO
       AND A.EST_REGI = INDICADOR_ACTIVO;

    /* IF (psIndicadorProcesoBloqueo = '1') THEN
    IF regParamCursos.Ind_Cali_Apta_Prim_Pedi = INDICADOR_SI THEN
    RETURN -1;
    END IF;
    END IF;*/
    RETURN 1;

  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RETURN - 1;
    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR EDU_FN_CURSO_APTAS_MIXTO_BLOQU: ' ||
                              LS_SQLERRM);
  END EDU_FN_CURSO_APTAS_MIXTO_BLOQU;

  /***************************************************************************
  Descripcion : Procedimiento para centralizar los procesos post ejecucion
   de calificacion y envio de aptas
  Fecha Creacion : 27/05/2008
  Autor : Rafael Romero
  Parametros :
   psCodigoPais : Codigo de Pais
   psCodEmpresa : Codigo de Empresa
   psCodPeriodo : Codigo de Periodo (Campaña de Proceso)
   psCodCurso : Codigo de usuario
   psmensajeerror : Mensaje de retorno
  ***************************************************************************/
  PROCEDURE EDU_PR_PROCE_AFTER_CALIF_ENVAP(PSCODIGOPAIS   IN VARCHAR2,
                                           PSCODEMPRESA   IN VARCHAR2,
                                           PSCODPERIODO   IN VARCHAR2,
                                           PSUSUARIO      IN VARCHAR2,
                                           PSMENSAJEERROR OUT VARCHAR2) IS
    REGPARAMPROGRAMA EDU_PARAM_PROGR_CAPAC%ROWTYPE;
    LSMENSAJEERROR   VARCHAR2(200) := '';
  BEGIN
    /* obteniendo parametrizacion de programa capacitacion */
    SELECT *
      INTO REGPARAMPROGRAMA
      FROM EDU_PARAM_PROGR_CAPAC A
     WHERE A.PAIS_COD_PAIS = PSCODIGOPAIS
       AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
       AND A.COD_PROG_CAPA = '01';

    /* Si esta activado el indicador de registro de Planillas NO PROCESADAS
    IF (regParamPrograma.ind_regi_plan_npro = INDICADOR_ACTIVO) THEN
    EDU_PKG_PROCE_GENER.EDU_PR_REGIS_PLANI_NPROC(psCodigoPais, psCodEmpresa, psCodPeriodo, psUsuario, regParamPrograma.NUM_MINI_PEDI_PLAN_NPRO, lsMensajeError);
    END IF;
    psmensajeerror := psmensajeerror || lsMensajeError;*/

    /* Si esta activado el indicador de registro de Consultoras con pedidos extemporaneos */
    IF (REGPARAMPROGRAMA.IND_CONS_PEDI_EXTE = INDICADOR_ACTIVO) THEN
      -- Todas las regiones
      EDU_PKG_PROCE_GENER.EDU_PR_REGIS_CONSU_PEDID_EXTEM(PSCODIGOPAIS,
                                                         PSCODEMPRESA,
                                                         PSCODPERIODO,
                                                         NULL,
                                                         PSUSUARIO,
                                                         LSMENSAJEERROR);
    END IF; /*FIN DE INDICADOR CONSULTORAS CON PEDIDOS EXTEMPORANEAS*/

    PSMENSAJEERROR := PSMENSAJEERROR || LSMENSAJEERROR;

  EXCEPTION
    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'EDU_PR_PROCE_AFTER_CALIF_ENVAP:' ||
                              LS_SQLERRM);
  END EDU_PR_PROCE_AFTER_CALIF_ENVAP;

  /***************************************************************************
  Descripcion : Procedure que Actualiza los cursos Mixtos de aquellas consultoras que han pedido un cuv
                   en el historico de aptas
  Fecha Creacion : 26/09/2009
  Autor : Sergio Buchelli Silva
  Parametros :
   psCodigoPais : Codigo de Pais
   psCodEmpresa : Codigo de Empresa
   psCodigoPeriodo : Codigo Periodo
   psUsuario : Codigo de Usuario
  ***************************************************************************/

  PROCEDURE EDU_PR_ACTUA_CURSO_MIXTO(PSCODIGOPAIS    VARCHAR2,
                                     PSCODEMPRESA    VARCHAR2,
                                     PSCODIGOPERIODO VARCHAR2,
                                     PSCODCURSO      VARCHAR2,
                                     PSUSUARIO       VARCHAR2) AS

    -- consultotras que pasaron pedidos pero no compraron el curso y su num previo de calificacion es 1
    CURSOR CURUPDAPTAS IS
      SELECT A.COD_CLIE
        FROM EDU_TMP_CONSU_SOLIC_CURSO A, EDU_HISTO_APTAS B
       WHERE A.CAM_PROC = PSCODIGOPERIODO
         AND A.COD_PAIS = PSCODIGOPAIS
         AND A.COD_EMPR_COME = PSCODEMPRESA
         AND A.COD_PAIS = B.PAIS_COD_PAIS
         AND A.COD_EMPR_COME = B.EMCO_COD_EMPR_COME
         AND A.COD_CLIE = B.CLIE_COD_CLIE
         AND A.FLA_REGI_PEDI = '1';

    TYPE T_CODCONS IS TABLE OF EDU_HISTO_APTAS.CLIE_COD_CLIE%TYPE;
    V_CODCONS T_CODCONS;

    ROWS        NATURAL := 1000; -- Number of rows to process at a time
    I           BINARY_INTEGER := 0;
    V_ROW_COUNT NUMBER := 0;

  BEGIN

    OPEN CURUPDAPTAS;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH CURUPDAPTAS BULK COLLECT
        INTO V_CODCONS LIMIT ROWS;

      EXIT WHEN V_ROW_COUNT = CURUPDAPTAS%ROWCOUNT;
      V_ROW_COUNT := CURUPDAPTAS%ROWCOUNT;

      -- Bulk bind of data in memory table...
      FORALL I IN 1 .. V_CODCONS.COUNT
        UPDATE EDU_HISTO_APTAS APT
           SET APT.EST_CAPA      = 'PO', -- Estado Confirmar
               APT.IND_CURS_COST = 'S',
               APT.FEC_MODI      = SYSDATE,
               APT.USU_MODI      = PSUSUARIO
         WHERE APT.CCAP_COD_CURS_CAPA = PSCODCURSO
           AND APT.CLIE_COD_CLIE = V_CODCONS(I)
           AND APT.PAIS_COD_PAIS = PSCODIGOPAIS
           AND APT.EMCO_COD_EMPR_COME = PSCODEMPRESA ;

    END LOOP;
    CLOSE CURUPDAPTAS;

    RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR EDU_PR_ACTUA_CURSO_MIXTO: ' ||
                              LS_SQLERRM);

  END EDU_PR_ACTUA_CURSO_MIXTO;

  /***************************************************************************
  Descripcion : Funcion que devuelve la campanha de inicio de pedidos dependiendo del numero dei nvitaciones
            y si se encuentra en apta
  Fecha Creacion : 11/11/2008
  Autor : Sergio Buchelli
  Parametros :
   psCodigoPais : Codigo de Pais
   psCodEmpresa : Codigo de Empresa
   psCodCurso : Codigo de Curso de Capacitación
   psIndicadorProcesoBloqueo : Indicador de Proceso de Bloqueo
  ***************************************************************************/
  FUNCTION EDU_FN_INICI_PEDID_APTAS(PSCODPAIS       VARCHAR2,
                                    PSCODEMPRESA    VARCHAR2,
                                    PSCODCURSO      VARCHAR2,
                                    PSCODCONSULTORA VARCHAR2,
                                    PSCODPERIODO    VARCHAR2) RETURN VARCHAR2 IS
    LNNUMINVITACION NUMBER := 0;
    LSCAMPAINICIO   EDU_HISTO_APTAS.CAM_ULTI_CALI_APTA%TYPE;

  BEGIN
    /* Obteniendo NUM INVITACION DEL CURSO del curso */
    BEGIN
      SELECT B.NUM_INVI
        INTO LNNUMINVITACION
        FROM EDU_PARAM_CURSO_CAPAC B
       WHERE B.PAIS_COD_PAIS = PSCODPAIS
         AND B.EMCO_COD_EMPR_COME = PSCODEMPRESA
         AND B.COD_CURS_CAPA = PSCODCURSO
         AND B.EST_REGI = INDICADOR_ACTIVO;

      IF LNNUMINVITACION IS NULL THEN
        LNNUMINVITACION := -1;
      END IF;
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        LNNUMINVITACION := -1;
    END;

    /* */

    BEGIN
      SELECT X.CAM_ULTI_CALI_APTA
        INTO LSCAMPAINICIO
        FROM EDU_HISTO_APTAS X
       WHERE X.CCAP_COD_CURS_CAPA = PSCODCURSO
         AND X.CLIE_COD_CLIE = PSCODCONSULTORA
         AND X.PAIS_COD_PAIS = PSCODPAIS
         AND X.EMCO_COD_EMPR_COME = PSCODEMPRESA
         AND X.NUM_INVI > 0
         AND X.NUM_INVI < LNNUMINVITACION;
    EXCEPTION
      WHEN OTHERS THEN
        SELECT X.CAM_INIC_PEDI
          INTO LSCAMPAINICIO
          FROM EDU_HISTO_PEDID_CONSU X
         WHERE X.PAIS_COD_PAIS = PSCODPAIS
           AND X.EMCO_COD_EMPR_COME = PSCODEMPRESA
           AND X.COD_CLIE = PSCODCONSULTORA
           AND X.CAM_ULTI_PEDI <= PSCODPERIODO
           AND X.EST_REGI = INDICADOR_ACTIVO;

    END;

    RETURN LSCAMPAINICIO;

  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RETURN NULL;
    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR EDU_FN_INICI_PEDID_APTAS: ' ||
                              LS_SQLERRM);
  END EDU_FN_INICI_PEDID_APTAS;

  /***************************************************************************
  Descripcion : Procedimiento que realiza el Proceso de DesBloqueo de Consultoras
   para aquellas que han pasado Pedido
   de Aptas
  Fecha Creacion : 26/10/2009
  Autor : Sergio Buchelli
  Parametros :
   psCodigoPais : Codigo de Pais
   psCodEmpresa : Codigo de Empresa
   psCodPeriodo : Campaña de Proceso
        psCodCurso : Codigo Curso
   psUsuario : Usuario
  ***************************************************************************/
  PROCEDURE EDU_PR_PROCE_DESBL_CONSU(PSCODIGOPAIS VARCHAR2,
                                     PSCODEMPRESA VARCHAR2,
                                     PSCODPERIODO VARCHAR2,
                                     PSCODCURSO   VARCHAR2,
                                     PSTIPOCURSO  VARCHAR2,
                                     PSUSUARIO    VARCHAR2) IS
  BEGIN
    IF (PSTIPOCURSO != TIPO_CURSO_SIN_COSTO) THEN
      --Desbloquea aquellas consultoras bloqueadas q estan pasando pedido en la campanha
      --de proceso para luego ser calificadas como aptas
      UPDATE EDU_HISTO_BLOQU_CONSU Z
         SET Z.EST_BLOQ = 'D',
             Z.CAM_DESB = PSCODPERIODO,
             Z.USU_MODI = PSUSUARIO,
             Z.FEC_MODI = SYSDATE
       WHERE Z.PAIS_COD_PAIS = PSCODIGOPAIS
         AND Z.EMCO_COD_EMPR_COME = PSCODEMPRESA
         AND Z.EST_BLOQ = 'B'
         AND Z.CLIE_COD_CLIE IN
             (SELECT A.COD_CLIE
                FROM EDU_TMP_PEDID_CONSU A
               WHERE A.CAM_PROC = PSCODPERIODO
                 AND A.COD_PAIS = Z.PAIS_COD_PAIS
                 AND A.COD_EMPR_COME = Z.EMCO_COD_EMPR_COME
                 )
         AND Z.CLIE_COD_CLIE IN
             (SELECT X.CLIE_COD_CLIE
                FROM EDU_HISTO_CAPAC_DETAL X
               WHERE X.CCAP_COD_CURS_CAPA = PSCODCURSO
                 AND X.CLIE_COD_CLIE = Z.CLIE_COD_CLIE
                 AND X.PAIS_COD_PAIS = Z.PAIS_COD_PAIS
                 AND X.EMCO_COD_EMPR_COME = Z.EMCO_COD_EMPR_COME);

      --Actualiza a desbloqueadas aquellas conultoras que han pasado pasado peiddo y han estado bloqueadas
      UPDATE EDU_MAEST_CLIEN Z
         SET Z.IND_BLOQ = 'N', Z.USU_MODI = PSUSUARIO, Z.FEC_MODI = SYSDATE
       WHERE Z.PAIS_COD_PAIS = PSCODIGOPAIS
         AND Z.EMCO_COD_EMPR_COME = PSCODEMPRESA
         AND Z.IND_BLOQ = 'S'
         AND Z.COD_CLIE IN (SELECT A.COD_CLIE
                              FROM EDU_TMP_PEDID_CONSU A
                             WHERE A.COD_PAIS = Z.PAIS_COD_PAIS
                               AND A.COD_EMPR_COME = Z.EMCO_COD_EMPR_COME
                               AND A.CAM_PROC = PSCODPERIODO)
         AND Z.COD_CLIE IN
             (SELECT X.CLIE_COD_CLIE
                FROM EDU_HISTO_CAPAC_DETAL X
               WHERE X.CLIE_COD_CLIE = Z.COD_CLIE
                 AND X.CCAP_COD_CURS_CAPA = PSCODCURSO
                 AND X.PAIS_COD_PAIS = Z.PAIS_COD_PAIS
                 AND X.EMCO_COD_EMPR_COME = Z.EMCO_COD_EMPR_COME);

      --limpiando gtt que se envia a comercial
      DELETE FROM EDU_GTT_HISTO_BLOQU_CONSU X
       WHERE X.COD_PAIS = PSCODIGOPAIS
         AND X.COD_EMPR_COME = PSCODEMPRESA
         AND X.CAM_PROC = PSCODPERIODO
         AND X.COD_CLIE IN
             (SELECT Z.CLIE_COD_CLIE
                FROM EDU_HISTO_BLOQU_CONSU Z, EDU_TMP_PEDID_CONSU A
               WHERE Z.PAIS_COD_PAIS = PSCODIGOPAIS
                 AND Z.EMCO_COD_EMPR_COME = PSCODEMPRESA
                 AND Z.EST_BLOQ = 'D'
                 AND A.COD_PAIS = Z.PAIS_COD_PAIS
                 AND A.COD_EMPR_COME = Z.EMCO_COD_EMPR_COME
                 AND Z.CLIE_COD_CLIE = A.COD_CLIE
                 AND A.CAM_PROC = PSCODPERIODO);
    END IF;
  EXCEPTION
    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR EDU_PR_PROCE_DESBL_CONSU: ' ||
                              LS_SQLERRM);
  END EDU_PR_PROCE_DESBL_CONSU;

  /***************************************************************************
  descripcion : procedimiento que realiza la calificacion de aquellas consultoras
            han sido exoneradas cuya secuencia de pedidos ha expirado
  fecha creacion : 29/10/2009
  autor : Sergio Buchelli
  parametros :
   pscodigopais : codigo de pais
   pscodempresa : codigo de empresa
   pscodperiodo : campaña de proceso
        pscodcurso : codigo curso
   psusuario : usuario
  ***************************************************************************/
  PROCEDURE EDU_PR_PROCE_CALIF_CONSU_EXONE(PSCODIGOPAIS VARCHAR2,
                                           PSCODEMPRESA VARCHAR2,
                                           PSCODPERIODO VARCHAR2,
                                           PSCODCURSO   VARCHAR2,
                                           PSTIPOCURSO  VARCHAR2,
                                           PSUSUARIO    VARCHAR2) IS
    LSCURSOPREREQ EDU_PARAM_CURSO_CAPAC.PRE_REQU_CAPA%TYPE;
  BEGIN
    IF (PSTIPOCURSO = TIPO_CURSO_MIXTO) THEN

      SELECT A.PRE_REQU_CAPA
        INTO LSCURSOPREREQ
        FROM EDU_PARAM_CURSO_CAPAC A
       WHERE A.PAIS_COD_PAIS = PSCODIGOPAIS
         AND A.EMCO_COD_EMPR_COME = PSCODEMPRESA
         AND A.COD_CURS_CAPA = PSCODCURSO
         AND A.EST_REGI = INDICADOR_ACTIVO;

      IF (LSCURSOPREREQ <> '00') THEN

        BEGIN
          --INSERTANTO LAS QUE NO EXITEN EN APTAS PARA EL CURSO
          INSERT INTO EDU_HISTO_APTAS
            (PAIS_COD_PAIS,
             EMCO_COD_EMPR_COME,
             CCAP_COD_CURS_CAPA,
             CLIE_COD_CLIE,
             CAM_PRIM_CALI_APTA,
             CAM_ULTI_CALI_APTA,
             TIP_CALI_APTA,
             NUM_INVI,
             IND_INIC_CALI_APTA,
             IND_FINA_CALI_APTA,
             IND_CURS_COST,
             IND_ENVI,
             IND_ENVI_PROG,
             EST_CAPA,
             USU_DIGI,
             FEC_DIGI,
             EST_REGI)
            SELECT PSCODIGOPAIS,
                   PSCODEMPRESA,
                   PSCODCURSO,
                   B.CLIE_COD_CLIE,
                   PSCODPERIODO,
                   PSCODPERIODO,
                   'R',
                   1,
                   'A',
                   'A',
                   'S',
                   INDICADOR_NO,
                   'N',
                   'PO',
                   PSUSUARIO,
                   SYSDATE,
                   '1'
              FROM EDU_TMP_PEDID_CONSU A, EDU_HISTO_CAPAC_DETAL B
             WHERE B.PAIS_COD_PAIS = PSCODIGOPAIS
               AND B.EMCO_COD_EMPR_COME = PSCODEMPRESA
               AND B.CCAP_COD_CURS_CAPA = LSCURSOPREREQ
               AND A.COD_PAIS = B.PAIS_COD_PAIS
               AND A.COD_EMPR_COME = B.EMCO_COD_EMPR_COME
               AND A.COD_CLIE = B.CLIE_COD_CLIE
               AND B.CLIE_COD_CLIE NOT IN
                   (SELECT X.CLIE_COD_CLIE
                      FROM EDU_HISTO_APTAS X
                     WHERE X.PAIS_COD_PAIS = B.PAIS_COD_PAIS
                       AND X.EMCO_COD_EMPR_COME = B.EMCO_COD_EMPR_COME
                       AND X.CCAP_COD_CURS_CAPA = PSCODCURSO
                       AND X.CLIE_COD_CLIE = B.CLIE_COD_CLIE);
        EXCEPTION
          WHEN OTHERS THEN
            NULL;
        END;
        --INSERTANDO EN EL HISTORICO DE CALIFICACIONES AQUELLAS Q NO EXISTEN
        BEGIN
          INSERT INTO EDU_HISTO_CALIF_APTAS
            (PAIS_COD_PAIS,
             EMCO_COD_EMPR_COME,
             CCAP_COD_CURS_CAPA,
             CAM_PROC,
             CLIE_COD_CLIE,
             CAM_PRIM_CALI_APTA,
             CAM_ULTI_CALI_APTA,
             TIP_CALI_APTA,
             NUM_INVI,
             IND_INIC_CALI_APTA,
             IND_FINA_CALI_APTA,
             IND_CURS_COST,
             IND_ENVI,
             EST_CAPA,
             USU_DIGI,
             FEC_DIGI,
             EST_REGI)
            SELECT PSCODIGOPAIS,
                   PSCODEMPRESA,
                   PSCODCURSO,
                   PSCODPERIODO,
                   B.CLIE_COD_CLIE PSCODPERIODO,
                   PSCODPERIODO,
                   'R',
                   1,
                   'A',
                   'A',
                   'S',
                   INDICADOR_NO,
                   'N',
                   'PC',
                   PSUSUARIO,
                   SYSDATE,
                   '1'
              FROM EDU_TMP_PEDID_CONSU A, EDU_HISTO_CAPAC_DETAL B
             WHERE B.PAIS_COD_PAIS = PSCODIGOPAIS
               AND B.EMCO_COD_EMPR_COME = PSCODEMPRESA
               AND B.CCAP_COD_CURS_CAPA = LSCURSOPREREQ
               AND A.COD_PAIS = B.PAIS_COD_PAIS
               AND A.COD_EMPR_COME = B.EMCO_COD_EMPR_COME
               AND A.COD_CLIE = B.CLIE_COD_CLIE
               AND B.CLIE_COD_CLIE NOT IN
                   (SELECT X.CLIE_COD_CLIE
                      FROM EDU_HISTO_CALIF_APTAS X
                     WHERE X.PAIS_COD_PAIS = B.PAIS_COD_PAIS
                       AND X.EMCO_COD_EMPR_COME = B.EMCO_COD_EMPR_COME
                       AND X.CCAP_COD_CURS_CAPA = PSCODCURSO
                       AND X.CLIE_COD_CLIE = B.CLIE_COD_CLIE);

        EXCEPTION
          WHEN OTHERS THEN
            NULL;
        END;

      END IF;

    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR EDU_PR_PROCE_CALIF_CONSU_EXONE: ' ||
                              LS_SQLERRM);
  END EDU_PR_PROCE_CALIF_CONSU_EXONE;


/***************************************************************************
Descripcion       : Devuelve Nro de Campa?as entre un rango de periodo
                    Inicial y final
Fecha Creacion    : 04/10/2012
Autor             : Sergio Buchelli
***************************************************************************/
FUNCTION EDU_FN_DEVUE_NUME_CAMPA(
   psCodPeriodoIni VARCHAR2,
   psCodPeriodoFin VARCHAR2,
   psCodPais       VARCHAR2
)
RETURN NUMBER
IS
  lsCodPeri        seg_perio_corpo.cod_peri%TYPE;
  X                NUMBER;
BEGIN
  X := 0;
  IF psCodPeriodoIni > psCodPeriodoFin THEN
     RETURN -1;
  END IF;
  lsCodPeri := psCodPeriodoIni;
  while true
  loop
      lsCodPeri := EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_CODIG_PERIO(lsCodPeri, 1);
      IF lsCodPeri <= psCodPeriodoFin THEN
         X := X + 1;
      ELSE
         EXIT;
      END IF;
  end loop;
  RETURN X + 1;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_FN_DEVUE_NUME_CAMPA: '||ls_sqlerrm);
END EDU_FN_DEVUE_NUME_CAMPA;

END EDU_PKG_CALIF;
/

