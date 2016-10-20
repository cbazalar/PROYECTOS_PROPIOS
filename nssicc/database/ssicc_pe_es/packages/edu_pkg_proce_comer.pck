create or replace package EDU_PKG_PROCE_COMER is

  -- Author  : PEEXTMSILVA
  -- Created : 24/07/2007 04:53:44 p.m.
  -- Purpose : Procesos Comerciales para Modulo Educacion

  -- Public type declarations
  W_FILAS      NUMBER:=1000;


/***************************************************************************
Descripcion       : Procedure que Recepciona los Primeros Pedidos
Fecha Creacion    : 09/07/2007
Autor             : Marco Silva
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psCodigoPeriodo : Codigo de Periodo
            psUsuario : Codigo de Usuario
***************************************************************************/
 PROCEDURE EDU_PR_RECEP_PRIME_PEDID
  (psCodigoPais VARCHAR2,
   psCodEmpresa    VARCHAR2,
   psCodigoPeriodo VARCHAR2,
   psUsuario       VARCHAR2
   );


/***************************************************************************
Descripcion       : Procedure que Recepciona los Pedidos
Fecha Creacion    : 09/07/2007
Autor             : Marco Silva
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psCodigoPeriodo : Codigo de Periodo
            psUsuario : Codigo de Usuario
***************************************************************************/
  PROCEDURE EDU_PR_RECEP_PEDID
  (psCodigoPais VARCHAR2,
   psCodEmpresa    VARCHAR2,
   psCodigoPeriodo VARCHAR2,
   psUsuario       VARCHAR2
   );

/***************************************************************************
Descripcion       : Procedure que Procesa Carga de Regiones
Fecha Creacion    : 09/07/2007
Autor             : Marco Silva
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psUsuario : Codigo de Usuario
***************************************************************************/
  PROCEDURE EDU_PR_CARGA_REGIO
  (psCodigoPais VARCHAR2,
   psCodEmpresa    VARCHAR2,
   psUsuario       VARCHAR2
   );

/***************************************************************************
Descripcion       : Procedure que Procesa Carga de Zonas
Fecha Creacion    : 09/07/2007
Autor             : Marco Silva
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psUsuario : Codigo de Usuario
***************************************************************************/
  PROCEDURE EDU_PR_CARGA_ZONAS
  (psCodigoPais VARCHAR2,
   psCodEmpresa    VARCHAR2,
   psUsuario       VARCHAR2
   );

/***************************************************************************
Descripcion       : Procedure que Procesa Carga Archivo de Control
Fecha Creacion    : 09/07/2007
Autor             : Marco Silva
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psUsuario : Codigo de Usuario
***************************************************************************/
  PROCEDURE EDU_PR_CARGA_CONTR_FACTU
  (psCodigoPais VARCHAR2,
   psCodEmpresa    VARCHAR2,
   psUsuario       VARCHAR2
   );

/***************************************************************************
Descripcion       : Procedure que Procesa Actualizacion de Envio de Aptas
Fecha Creacion    : 09/07/2007
Autor             : Marco Silva
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
			   psCodPeriodo	  :Codigo Periodo
            psNumeroLote  : Numero de Lote
            psUsuario : Codigo de Usuario
***************************************************************************/
 PROCEDURE EDU_PR_ACTUA_ENVIO_APTAS
  (psCodigoPais VARCHAR2,
   psCodEmpresa    VARCHAR2,
   psCodPeriodo	   VARCHAR2,
   psNumeroLote    VARCHAR2,
   psUsuario       VARCHAR2
   );

/***************************************************************************
Descripcion       : Procedure que Procesa Actualizacion de Envio de Aptas C.Costo
Fecha Creacion    : 09/07/2007
Autor             : Marco Silva
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
			psCodPeriodo	:Codigo Periodo
            psNumeroLote  : Numero de Lote
            psUsuario : Codigo de Usuario
***************************************************************************/
PROCEDURE EDU_PR_ACTUA_ENVIO_APTAS_COSTO
  (psCodigoPais VARCHAR2,
   psCodEmpresa    VARCHAR2,
   psCodPeriodo	   VARCHAR2,
   psNumeroLote    VARCHAR2,
   psUsuario       VARCHAR2
   );

/***************************************************************************
Descripcion       : Procedure que Procesa Actutlzacion de Aptas por Programar
Fecha Creacion    : 09/07/2007
Autor             : Marco Silva
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psCodigoPeriodo : Codigo Periodo
            psNumeroLote   :  Numero de Lote
            psUsuario : Codigo de Usuario
***************************************************************************/
 PROCEDURE EDU_PR_ACTUA_ENVIO_APTAS_PROGR
  (psCodigoPais VARCHAR2,
   psCodEmpresa    VARCHAR2,
   psCodPeriodo    VARCHAR2,
   psNumeroLote    VARCHAR2,
   psUsuario       VARCHAR2
   );

/***************************************************************************
Descripcion       : Funcion que Devuelve el Sgte Numero de Lote en base al pais
Fecha Creacion    : 09/07/2007
Autor             : Marco Silva
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psTipoLote   :  Tipo de Lote
            psUsuario : Codigo de Usuario
***************************************************************************/
  PROCEDURE EDU_PR_ACTUA_NUMER_LOTE_SGNTE(psCodigoPais     VARCHAR2, psEmpresa VARCHAR2 ,psTipoLote VARCHAR2, psCodUsuario VARCHAR2);

/***************************************************************************
Descripcion       : Funcion que Devuelve El Parametro Numero de Lote de Educación.
Fecha Creacion    : 09/07/2007
Autor             : Marco Silva
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psTipoLote   :  Tipo de Lote
***************************************************************************/
 FUNCTION GEN_FN_NUMER_LOTE_SGNTE(psCodigoPais VARCHAR2, psEmpresa VARCHAR2 , psTipoLote VARCHAR2)
 RETURN VARCHAR2;

/***************************************************************************
Descripcion       : Procedure que Recepciona los Primeros de las Cons. No Constantes
Fecha Creacion    : 09/07/2007
Autor             : Marco Silva
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psCodigoPeriodo : Codigo de Periodo
            psUsuario : Codigo de Usuario
***************************************************************************/
 PROCEDURE EDU_PR_RECEP_PEDID_NOCON
  (psCodigoPais VARCHAR2,
   psCodEmpresa    VARCHAR2,
   psCodigoPeriodo VARCHAR2,
   psUsuario       VARCHAR2
   );

/***************************************************************************
Descripcion       : Procedure que Recepciona los Primeros de las Cons. Constantes
Fecha Creacion    : 09/07/2007
Autor             : Marco Silva
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psCodigoPeriodo : Codigo de Periodo
            psUsuario : Codigo de Usuario
***************************************************************************/
 PROCEDURE EDU_PR_RECEP_PEDID_CONST
  (psCodigoPais VARCHAR2,
   psCodEmpresa    VARCHAR2,
   psCodigoPeriodo VARCHAR2,
   psUsuario       VARCHAR2
   );

/***************************************************************************
Descripcion       : Procedure que Recepciona Factura de Cons. Constantes
Fecha Creacion    : 09/07/2007
Autor             : Marco Silva
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psCodigoPeriodo : Codigo de Periodo
            psUsuario : Codigo de Usuario
***************************************************************************/
PROCEDURE EDU_PR_RECEP_FACTU_CONST
  (psCodigoPais VARCHAR2,
   psCodEmpresa    VARCHAR2,
   psCodigoPeriodo VARCHAR2,
   psUsuario       VARCHAR2
   );

/***************************************************************************
Descripcion       : Procedure que Recepciona Facturacion de Cons. No Constantes
Fecha Creacion    : 09/07/2007
Autor             : Marco Silva
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psCodigoPeriodo : Codigo de Periodo
            psUsuario : Codigo de Usuario
***************************************************************************/
PROCEDURE EDU_PR_RECEP_FACTU_NOCON
  (psCodigoPais VARCHAR2,
   psCodEmpresa    VARCHAR2,
   psCodigoPeriodo VARCHAR2,
   psUsuario       VARCHAR2
   );

/***************************************************************************
Descripcion       : Procedure que Recepciona Maestro Clientes
Fecha Creacion    : 09/07/2007
Autor             : Marco Silva
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psCodigoPeriodo : Codigo de Periodo
            psUsuario : Codigo de Usuario
***************************************************************************/
 PROCEDURE EDU_PR_RECEP_MAEST_CLIEN
  (psCodigoPais VARCHAR2,
   psCodEmpresa    VARCHAR2,
   psCodigoPeriodo VARCHAR2,
   psUsuario       VARCHAR2
   ) ;

/***************************************************************************
Descripcion       : Procedure que Recepciona Facturacion
Fecha Creacion    : 09/07/2007
Autor             : Marco Silva
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psCodigoPeriodo : Codigo de Periodo
            psUsuario : Codigo de Usuario
***************************************************************************/
  PROCEDURE EDU_PR_RECEP_PEDID_FACTU
  (psCodigoPais VARCHAR2,
   psCodEmpresa    VARCHAR2,
   psCodigoPeriodo VARCHAR2,
   psUsuario       VARCHAR2
   );

/***************************************************************************
Descripcion       : Procedure que Recepciona Factura de Cons. Nuevas
Fecha Creacion    : 09/07/2007
Autor             : Marco Silva
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psCodigoPeriodo : Codigo de Periodo
            psUsuario : Codigo de Usuario
***************************************************************************/
 PROCEDURE EDU_PR_RECEP_FACTU_NUEVA
  (psCodigoPais VARCHAR2,
   psCodEmpresa    VARCHAR2,
   psCodigoPeriodo VARCHAR2,
   psUsuario       VARCHAR2
   );

/***************************************************************************
Descripcion       : Procedure que Procesa Aptas por Programar
Fecha Creacion    : 09/07/2007
Autor             : Marco Silva
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psCodigoPeriodo : Codigo Periodo
            psUsuario : Codigo de Usuario
***************************************************************************/
PROCEDURE EDU_PR_ACTUA_APTAS_POR_PROGR
  (psCodigoPais VARCHAR2,
   psCodEmpresa    VARCHAR2,
   psCodigoPeriodo    VARCHAR2,
   psUsuario       VARCHAR2
   );

/***************************************************************************
Descripcion       : Procedure que Procesa Aptas Costo por Confirmar
Fecha Creacion    : 09/07/2007
Autor             : Marco Silva
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psCodigoPeriodo : Codigo Periodo
            psUsuario : Codigo de Usuario
***************************************************************************/

PROCEDURE EDU_PR_ACTUA_APTAS_POR_COMFI
  (psCodigoPais VARCHAR2,
   psCodEmpresa    VARCHAR2,
   psCodigoPeriodo    VARCHAR2,
   psUsuario       VARCHAR2
   );

/***************************************************************************
Descripcion       : Procedure que Procesa Facturar Cursos Costo a Por Prog
Fecha Creacion    : 09/07/2007
Autor             : Marco Silva
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psCodigoPeriodo : Codigo Periodo
            psUsuario : Codigo de Usuario
***************************************************************************/

PROCEDURE EDU_PR_ACTUA_COMPR_CURSO
  (psCodigoPais VARCHAR2,
   psCodEmpresa    VARCHAR2,
   psCodigoPeriodo    VARCHAR2,
   psUsuario       VARCHAR2
   );

/***************************************************************************
Descripcion       : Procedure que Procesa Cursos con Costo No comprados
Fecha Creacion    : 09/07/2007
Autor             : Marco Silva
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psCodigoPeriodo : Codigo Periodo
            psUsuario : Codigo de Usuario
***************************************************************************/

PROCEDURE EDU_PR_ACTUA_NO_COMPR_CURSO
  (psCodigoPais VARCHAR2,
   psCodEmpresa    VARCHAR2,
   psCodigoPeriodo    VARCHAR2,
   psUsuario       VARCHAR2
   );

/***************************************************************************
Descripcion       : Procedure que Recepciona los Primeros de las Cons. Nuevas
Fecha Creacion    : 09/07/2007
Autor             : Marco Silva
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psCodigoPeriodo : Codigo de Periodo
            psUsuario : Codigo de Usuario
***************************************************************************/
 PROCEDURE EDU_PR_RECEP_PEDID_NUEVA
  (psCodigoPais VARCHAR2,
   psCodEmpresa    VARCHAR2,
   psCodigoPeriodo VARCHAR2,
   psUsuario       VARCHAR2
   );

/***************************************************************************
Descripcion       : Funcion que Devuelve la siguiente campanha
Fecha Creacion    : 09/07/2007
Autor             : Marco Silva
Parametros        :
            psCodigoPeriodo : Codigo de Periodo
            numCampanhas : Numero de Campanhas
***************************************************************************/
 FUNCTION GEN_FN_DEVUE_NSGTE_CAMPA(psCodPeriodo VARCHAR2, numCampanhas number ) RETURN varchar2  ;

/***************************************************************************
Descripcion       : Procedure que Recepciona Factura de Cons. con 2do Pedido
Fecha Creacion    : 09/07/2007
Autor             : Marco Silva
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psCodigoPeriodo : Codigo de Periodo
            psUsuario : Codigo de Usuario
***************************************************************************/
PROCEDURE EDU_PR_RECEP_FACTU_SEGDO_PEDID
  (psCodigoPais VARCHAR2,
   psCodEmpresa    VARCHAR2,
   psCodigoPeriodo VARCHAR2,
   psUsuario       VARCHAR2
   );

/***************************************************************************
Descripcion       : Procedure que Actutlzacion Beneficios a Clasificadas de Tipo Unica-Nueva
Fecha Creacion    : 09/07/2007
Autor             : Marco Silva
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psCodigoPeriodo : Codigo Periodo
            psCodigoClasi : Codigo de Clasificacion
            psCodigoCurso : Codigo de Curso
            psTipoAsis : Tipo  de Asistencia
            psEstResNiv : Estado Respecto a Nivel
            psUsuario : Codigo de Usuario
***************************************************************************/
PROCEDURE EDU_PR_ACTUA_CLASI_UNICA_NUEVA
  (psCodigoPais    VARCHAR2,
   psCodEmpresa    VARCHAR2,
   psCodigoPeriodo VARCHAR2,
   psCodigoClasi   VARCHAR2,
   psCodigoCurso   VARCHAR2 ,
   psTipoAsis      VARCHAR2 ,
   psEstResNiv     VARCHAR2 ,
   psIndDespacho   		 VARCHAR2 ,
   psNumCampMaxDespacho  VARCHAR2 ,
   psUsuario       VARCHAR2
   );

/***************************************************************************
Descripcion       : Procedure que Actutlzacion Beneficios a Clasificadas de Tipo Unica-N Campanhas
Fecha Creacion    : 09/07/2007
Autor             : Marco Silva
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psCodigoPeriodo : Codigo Periodo
            psCodigoClasi : Codigo de Clasificacion
            psCodigoCurso : Codigo de Curso
            psTipoAsis : Tipo  de Asistencia
            psEstResNiv : Estado Respecto a Nivel
            psNumCamp : Numero Campanhas
            psUsuario : Codigo de Usuario
***************************************************************************/
PROCEDURE EDU_PR_ACTUA_CLASI_UNICA_NCAMP
  (psCodigoPais VARCHAR2,
   psCodEmpresa    VARCHAR2,
   psCodigoPeriodo VARCHAR2,
   psCodigoClasi   VARCHAR2,
   psCodigoCurso   VARCHAR2 ,
   psTipoAsis      VARCHAR2 ,
   psEstResNiv     VARCHAR2 ,
   psNumCamp     number ,
   psIndDespacho   		 VARCHAR2 ,
   psNumCampMaxDespacho  VARCHAR2 ,
   psUsuario       VARCHAR2
   );

/***************************************************************************
Descripcion       : Procedure que Procesa Actutlzacion Beneficios a Clasificadas
Fecha Creacion    : 09/07/2007
Autor             : Marco Silva
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psCodigoPeriodo : Codigo Periodo
            psUsuario : Codigo de Usuario
***************************************************************************/
PROCEDURE EDU_PR_ACTUA_BENEF_CLASI
    (psCodigoPais VARCHAR2,
     psCodEmpresa    VARCHAR2,
     psCodigoPeriodo VARCHAR2,
     psUsuario       VARCHAR2
     );


/***************************************************************************
Descripcion       : Procedure que Actualizacion Envio de Clasificaciones
Fecha Creacion    : 09/07/2007
Autor             : Marco Silva
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psCodigoPeriodo : Codigo Periodo
            psUsuario : Codigo de Usuario
***************************************************************************/
PROCEDURE EDU_PR_ACTUA_ENVIO_CLASI
  (psCodigoPais VARCHAR2,
   psCodEmpresa    VARCHAR2,
   psCodigoPeriodo    VARCHAR2,
   psUsuario       VARCHAR2
   );

/***************************************************************************
Descripcion       : Procedure que inserta Equivalencia de Clasificaciones
                    en Tabla Temporal
Fecha Creacion    : 01/03/2008
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psCodigoPeriodo : Codigo Periodo
            psCodCliente : Codigo de Cliente
            psCodCurso   : Codigo de Curso
            psCodigoClasi: codigo de Clasificacion
  		    psindicadorEnvio   : Indicador de envio Primera Invitacion
***************************************************************************/
PROCEDURE EDU_PR_INSER_EQUIV_CLASI_TEMPO (
   psCodigoPais       VARCHAR2,
   psCodEmpresa       VARCHAR2,
   psCodigoPeriodo    VARCHAR2,
   psCodCliente       VARCHAR2,
   psCodCurso         VARCHAR2,
   psCodigoClasi      VARCHAR2,
   psindicadorEnvio   VARCHAR2);


/***************************************************************************
Descripcion       : Procedure que efectua el Envio de Equivalencia de
                    Clasificaciones
Fecha Creacion    : 29/02/2008
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psCodigoPeriodo : Codigo Periodo
***************************************************************************/
PROCEDURE EDU_PR_ENVIO_EQUIV_CLASI(
   psCodPais      VARCHAR2,
   psCodEmpresa   VARCHAR2,
   psCodPeriodo   VARCHAR2
);

/***************************************************************************
Descripcion       : Devuelve SEQUENCIAL SIGUIENTE
                    de Tabla MAE_CLIEN_CLASI para campo OID_CLIE_CLAS
Fecha Creacion    : 25/01/2008
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION EDU_FN_CLIEN_CLASI_SEQUE_NEXT
RETURN NUMBER;

/***************************************************************************
Descripcion       : Procedure que efectua el Envio de Mensajes para las
                    Capacitadas
Fecha Creacion    : 13/03/2008
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psCodigoPeriodo : Codigo Periodo
***************************************************************************/
PROCEDURE EDU_PR_ENVIO_MENSA_CAPA(
   psCodPais     VARCHAR2,
   psCodEmpresa  VARCHAR2,
   psCodPeriodo  VARCHAR2);

/***************************************************************************
Descripcion       : Procedure que efectua el borrado de Mensajes en el comercial
                    de los mensajes no impresos antes del Envio de Mensajes de Equivalencia
Fecha Creacion    : 27/03/2008
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
***************************************************************************/
PROCEDURE EDU_PR_BORRA_MENSA_NIMPRE(
   psCodPais     VARCHAR2,
   psCodEmpresa  VARCHAR2);

/***************************************************************************
Descripcion       : Procedure que Actualiza el indicador de despacho de
                    clasificacion en la tabla historica de clasificaciones
Fecha Creacion    : 02/04/2008
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psCodPeriodo : Codigo Periodo
            psUsuario    : Codigo de Usuario
***************************************************************************/
PROCEDURE EDU_PR_ACTUA_CLASI_PEDID_FACTU(
   psCodPais     VARCHAR2,
   psCodEmpresa  VARCHAR2,
   psCodPeriodo  VARCHAR2,
   psUsuario     VARCHAR2);


/***************************************************************************
Descripcion       : Procedimiento que realiza el Proceso de Envio de Bloqueo de Consultoras
Fecha Creacion    : 06/05/2008
Autor             : Sergio Buchelli
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psCodPeriodo : Campaña de Proceso
            psUsuario    : Usuario
***************************************************************************/
   PROCEDURE EDU_PR_PROCE_ENVIO_BLOQU_CONSU(
      pscodigopais   VARCHAR2,
      pscodempresa   VARCHAR2,
      pscodperiodo   VARCHAR2,
      psusuario      VARCHAR2
   );

/***************************************************************************
Descripcion       : Procedure que Actutlzacion Beneficios a Clasificadas de Tipo Unica-N Campanhas
 				    Con el indicador Todo
Fecha Creacion    :  12/05/2008
Autor             : Sergio Buchelli
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psCodigoPeriodo : Codigo Periodo
            psCodigoClasi : Codigo de Clasificacion
            psCodigoCurso : Codigo de Curso
            psTipoAsis : Tipo  de Asistencia
            psEstResNiv : Estado Respecto a Nivel
            psUsuario : Codigo de Usuario
***************************************************************************/
PROCEDURE EDU_PR_CLASI_UNICA_NCAMP_TODOS
  (psCodigoPais VARCHAR2,
   psCodEmpresa    VARCHAR2,
   psCodigoPeriodo VARCHAR2,
   psCodigoClasi   VARCHAR2,
   psCodigoCurso   VARCHAR2 ,
   psTipoAsis      VARCHAR2 ,
   psEstResNiv     VARCHAR2 ,
   psIndDespacho   		 VARCHAR2 ,
   psNumCampMaxDespacho  VARCHAR2 ,
   psUsuario       VARCHAR2
   );


/***************************************************************************
Descripcion       : Procedure que Actutlzacion Beneficios a Clasificadas de Tipo Unica-N Campanhas
 				    Con el indicador Campanha Anteriores
Fecha Creacion    :  12/05/2008
Autor             : Sergio Buchelli
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psCodigoPeriodo : Codigo Periodo
            psCodigoClasi : Codigo de Clasificacion
            psCodigoCurso : Codigo de Curso
            psTipoAsis : Tipo  de Asistencia
            psEstResNiv : Estado Respecto a Nivel
			psNumCamp   : Numero de campanhas
            psUsuario : Codigo de Usuario
***************************************************************************/
PROCEDURE EDU_PR_CLASI_UNICA_NCAMP_ANTER
  (psCodigoPais VARCHAR2,
   psCodEmpresa    VARCHAR2,
   psCodigoPeriodo VARCHAR2,
   psCodigoClasi   VARCHAR2,
   psCodigoCurso   VARCHAR2 ,
   psTipoAsis      VARCHAR2 ,
   psEstResNiv     VARCHAR2 ,
   psNumCamp       number ,
     psIndDespacho   		 VARCHAR2 ,
   psNumCampMaxDespacho  VARCHAR2 ,
   psUsuario    		 VARCHAR2
   );
/***************************************************************************
Descripcion       : Procedure que Actualiza los beneficoa en el periodo de proceso
                    con consultoras que aun no se le han despachado en sus N campanhas
					que tine como maximo paraq se le haga el despacho
Fecha Creacion    :  26/06/2008
Autor             : Sergio Buchelli
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psCodigoPeriodo : Codigo Periodo
            psCodigoClasi : Codigo de Clasificacion
            psCodigoCurso : Codigo de Curso
			psIndClasificacion   	  Indicador Despacho ,
			psNumCampMaxDespacho  Numero Campaña Maximo Despacho,
            psUsuario : Codigo de Usuario
***************************************************************************/
PROCEDURE EDU_PR_ACTUA_NO_DESPA_CLASI
  (psCodigoPais 		 VARCHAR2,
   psCodEmpresa    		 VARCHAR2,
   psCodigoPeriodo 		 VARCHAR2,
   psCodigoClasi   		 VARCHAR2,
   psCodigoCurso   		 VARCHAR2 ,
   psIndClasificacion    VARCHAR2 ,
   psNumCampMaxDespacho  VARCHAR2 ,
   psUsuario    		 VARCHAR2
   );


/***************************************************************************
Descripcion       : Proceso solo para SICC quese encarga de obtener consultoras con curso costo del
										periodo anterior y enviarlas en  el periodo actual
Fecha Creacion    :  18/07/2008
Autor             : Sergio Buchelli
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psCodigoPeriodo : Codigo Periodo
***************************************************************************/
PROCEDURE EDU_PR_ENVIO_CONSU_COMPR_CURSO
  (psCodigoPais 		 VARCHAR2,
   psCodEmpresa    		 VARCHAR2,
   psCodigoPeriodo 		 VARCHAR2
  );


/***************************************************************************
Descripcion       : Procedure que Inserta en un Global Tempory aquellas consultoras
										que han comprado el curso
Fecha Creacion    :  21/07/2008
Autor             : Sergio Buchelli
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
***************************************************************************/
PROCEDURE EDU_PR_INSER_CURSO_FACTU_TEMPO
  (psCodigoPais 		 VARCHAR2,
   psCodEmpresa    		 VARCHAR2
   );


/***************************************************************************
Descripcion       : Procedure que Actualiza aquellas consultoras que no han comprado el curso
 				  	dependiendo del numero previo de campaña para la calificacion donde sea 1
Fecha Creacion    : 24/09/2009
Autor             : Sergio Buchelli Silva
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psCodigoPeriodo : Codigo Periodo
            psUsuario : Codigo de Usuario
***************************************************************************/

PROCEDURE EDU_PR_ACTUA_CONSU_PREV_CAMPA
  (psCodigoPais VARCHAR2,
   psCodEmpresa    VARCHAR2,
   psCodigoPeriodo    VARCHAR2,
   psUsuario       VARCHAR2
   );


/***************************************************************************
Descripcion       : Procedure que Recepciona los Pedidos no existentes en aptas pero
					que son contantes
Fecha Creacion    : 18/09/2008
Autor             : Sergio Buchelli
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psCodigoPeriodo : Codigo de Periodo
            psUsuario : Codigo de Usuario
***************************************************************************/
 PROCEDURE EDU_PR_RECEP_CONSU_SOLIC_CURSO
  (psCodigoPais    VARCHAR2,
   psCodEmpresa    VARCHAR2,
   psCodigoPeriodo VARCHAR2,
   psUsuario       VARCHAR2
   );


/***************************************************************************
Descripcion       : Procedure que Recepciona los Primeros de las Cons. No Constantes, que ingresaron a
 				  	educacion como antiguas regulares , y se volviveron no constantes
Fecha Creacion    : 06/11/2008
Autor             : Sergio Buchelli Silva
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psCodigoPeriodo : Codigo de Periodo
            psUsuario : Codigo de Usuario
***************************************************************************/

 PROCEDURE EDU_PR_RECEP_PEDID_NOCON_CUV
  (psCodigoPais VARCHAR2,
   psCodEmpresa    VARCHAR2,
   psCodigoPeriodo VARCHAR2,
   psUsuario       VARCHAR2
   );

/***************************************************************************
Descripcion       : Procedure que Procesa Actualiza Archivo de Control
Fecha Creacion    : 27/02/2009
Autor             : Sergio Buchelli Silva
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psUsuario : Codigo de Usuario
***************************************************************************/
  PROCEDURE EDU_PR_ACTUA_CONTR_FACTU
  (psCodigoPais VARCHAR2,
   psCodEmpresa    VARCHAR2,
   psUsuario       VARCHAR2
   );


/***************************************************************************
Descripcion       : Procedure que Recepciona los Pedidos de tods las consultoras ya
                    establecidas se ejecutara luego de cargas las cuv
Fecha Creacion    : 30/01/2012
Autor             : Sergio Buchelli Silva
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psCodigoPeriodo : Codigo de Periodo
            psUsuario : Codigo de Usuario
***************************************************************************/

 PROCEDURE EDU_PR_RECEP_PEDID_CONSU_ESTAB
  (psCodigoPais VARCHAR2,
   psCodEmpresa    VARCHAR2,
   psCodigoPeriodo VARCHAR2,
   psUsuario       VARCHAR2
   );


end EDU_PKG_PROCE_COMER;
/

CREATE OR REPLACE PACKAGE BODY "EDU_PKG_PROCE_COMER" is

 /* Declaracion de Variables */
 ln_sqlcode NUMBER(10);
 ls_sqlerrm VARCHAR2(1000);

/***************************************************************************
Descripcion : Procedure que Recepciona los Primeros Pedidos
Fecha Creacion : 09/07/2007
Autor : Marco Silva
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodigoPeriodo : Codigo de Periodo
 psUsuario : Codigo de Usuario
***************************************************************************/
 PROCEDURE EDU_PR_RECEP_PRIME_PEDID
 (psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psUsuario VARCHAR2
 )
 AS
 CURSOR curINSConsuNuevas
 IS
 SELECT distinct cons.cod_pais ,
 cons.cod_empr_come ,
 cons.cod_clie ,
 cons.cam_proc
 FROM EDU_TMP_PEDID_CONSU cons
 WHERE cons.COD_PAIS = psCodigoPais and
 cons.cod_empr_come = psCodEmpresa and
 cons.cam_proc = psCodigoPeriodo and
 cons.ind_fact = '0' and
 cons.ind_pedi = '1' and -- Extraen los Primer Pedidos desde el Comercial
 -- Insertan los primeros pedidos
 not exists ( select nu.cod_clie from edu_histo_pedid_consu nu where nu.pais_cod_pais = psCodigoPais and
 nu.emco_cod_empr_come = psCodEmpresa and
 nu.cod_clie = cons.cod_clie ) ;

 CURSOR curUPDConsuNuevas
 IS
 SELECT cons.cod_pais ,
 cons.cod_empr_come ,
 cons.cod_clie ,
 cons.cam_proc
 FROM EDU_TMP_PEDID_CONSU cons
 WHERE cons.COD_PAIS = psCodigoPais and
 cons.cod_empr_come = psCodEmpresa and
 cons.cam_proc = psCodigoPeriodo and
 cons.ind_fact = '0' and
 cons.ind_pedi = '1' and -- Extraen los Primer Pedidos desde el Comercial
 -- Actualizan: no facturaron en campanha actual o no facturaron en (alguna) campanha anterior
 exists ( select nu.cod_clie from edu_histo_pedid_consu nu where nu.pais_cod_pais = psCodigoPais and
 nu.emco_cod_empr_come = psCodEmpresa and
 nu.cod_clie = cons.cod_clie ) ;

 TYPE t_cod_pais IS TABLE OF edu_histo_pedid_consu.pais_cod_pais%TYPE;
 TYPE t_cod_empr_come IS TABLE OF edu_histo_pedid_consu.emco_cod_empr_come%TYPE;
 TYPE t_cod_clie IS TABLE OF edu_histo_pedid_consu.cod_clie%TYPE;
 TYPE t_camp_proc IS TABLE OF edu_histo_pedid_consu.cam_proc%TYPE;

 v_cod_pais t_cod_pais ;
 v_cod_empr_come t_cod_empr_come ;
 v_cod_clie t_cod_clie ;
 v_camp_proc t_camp_proc ;

 rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;
 j BINARY_INTEGER := 0;
 v_row_count NUMBER := 0;
 v_row_count_ins NUMBER := 0;

begin

 OPEN curUPDConsuNuevas;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
 FETCH curUPDConsuNuevas BULK COLLECT INTO
 v_cod_pais ,
 v_cod_empr_come ,
 v_cod_clie ,
 v_camp_proc LIMIT rows;

 EXIT WHEN v_row_count = curUPDConsuNuevas%ROWCOUNT;
 v_row_count := curUPDConsuNuevas%ROWCOUNT;

 -- Bulk bind of data in memory table...
 FORALL i IN 1..v_cod_pais.count
 update edu_histo_pedid_consu pedcn
 set pedcn.cam_ulti_pedi = v_camp_proc(i) ,
 pedcn.cam_proc = v_camp_proc(i) ,
 pedcn.cod_ulti_nive = '1' ,
 pedcn.num_pedi_fact = 0 ,
 pedcn.ind_pedi = '1' ,
 pedcn.ind_fact = '0' ,
 pedcn.ind_nuev = '0' ,
 pedcn.ind_cons = '0' ,
 pedcn.ind_prim_pedi = '1' ,
 usu_modi = psUsuario ,
 fec_modi = sysdate
 where pais_cod_pais = v_cod_pais(i)
 and emco_cod_empr_come = v_cod_empr_come(i)
 and cod_clie = v_cod_clie(i);

 END LOOP;
 CLOSE curUPDConsuNuevas;

 --PUEDEN VENIR DUPLICADAS SE HACE EL BEGIN EXCEPTION

 OPEN curINSConsuNuevas;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
 FETCH curINSConsuNuevas BULK COLLECT INTO
 v_cod_pais ,
 v_cod_empr_come ,
 v_cod_clie ,
 v_camp_proc LIMIT rows;
            --EXIT WHEN v_row_count_ins = curINSConsuNuevas%ROWCOUNT;
            --v_row_count_ins := curINSConsuNuevas%ROWCOUNT;
           IF v_cod_pais.COUNT > 0 THEN
 -- Bulk bind of data in memory table...
             --FORALL j IN 1..v_cod_pais.count
             FOR j IN v_cod_pais.FIRST .. v_cod_pais.LAST LOOP
               BEGIN
insert into edu_histo_pedid_consu
 ( pais_cod_pais,
 emco_cod_empr_come,
 cod_clie,
 cam_inic_pedi,
 cam_ulti_pedi,
 cam_proc,
 cod_ulti_nive,
 num_pedi_fact,
 ind_pedi,
 ind_fact,
 ind_nuev,
 ind_cons,
 ind_prim_pedi,
 usu_digi,
 fec_digi,
 usu_modi,
 fec_modi,
 est_regi
 )
values
 ( v_cod_pais(j) ,
 v_cod_empr_come(j) ,
 v_cod_clie(j) ,
 v_camp_proc(j) ,
 v_camp_proc(j) ,
 v_camp_proc(j) ,
                     '1' , -- consultoras inician en el primer nivel
 0,
 1, -- Paso Pedido
 0,
 0,
 0,
 1, -- es Primer Pedido
 psUsuario,
 sysdate,
 null,
 null,
 1
 ) ;
                 EXCEPTION
                 WHEN OTHERS THEN
                   NULL;
                 END;
             END LOOP;
           END IF;
          EXIT WHEN curINSConsuNuevas%NOTFOUND;
 END LOOP;
 CLOSE curINSConsuNuevas;


 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_RECEP_PRIME_PEDID: '||ls_sqlerrm);

 END EDU_PR_RECEP_PRIME_PEDID;

/***************************************************************************
Descripcion : Procedure que Recepciona los Primeros de las Cons. Nuevas
Fecha Creacion : 09/07/2007
Autor : Marco Silva
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodigoPeriodo : Codigo de Periodo
 psUsuario : Codigo de Usuario
***************************************************************************/

 PROCEDURE EDU_PR_RECEP_PEDID_NUEVA
 (psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psUsuario VARCHAR2
 )
 AS
 CURSOR curUPDConsuNuevas
 IS
 SELECT cons.cod_pais ,
 cons.cod_empr_come ,
 cons.cod_clie ,
 cons.cam_proc
 FROM EDU_TMP_PEDID_CONSU cons
 WHERE cons.COD_PAIS = psCodigoPais and
 cons.cod_empr_come = psCodEmpresa and
 cons.cam_proc = psCodigoPeriodo and
 cons.ind_fact = '0' and
 cons.ind_pedi = '2' and -- Extraen los Pedidos Normales desde el Comercial
 -- Actualizan: facturaron en campanha anterior (pasa a 2do nivel y se mantiene indicador nueva hasta q facture)
 exists ( select nu.cod_clie
 from edu_histo_pedid_consu nu
 where nu.pais_cod_pais = psCodigoPais and
 nu.emco_cod_empr_come = psCodEmpresa and
 nu.cod_clie = cons.cod_clie and
 nu.ind_nuev = '1' and -- nuevas
 nu.ind_fact = 1 and -- nuevas q han facturado, pueden existir de la camp.anterior las q no facturaron
 nu.cam_ulti_pedi = GEN_FN_DEVUE_NSGTE_CAMPA(psCodigoPeriodo,-1) and
 -- campnaha ultimo pedido + nivel actual = campanha proceso (soo valido para las nuevas)
 GEN_FN_DEVUE_NSGTE_CAMPA(nu.cam_ulti_pedi ,to_number(nu.cod_ulti_nive)) = psCodigoPeriodo --and
 ) ;

 TYPE t_cod_pais IS TABLE OF edu_histo_pedid_consu.pais_cod_pais%TYPE;
 TYPE t_cod_empr_come IS TABLE OF edu_histo_pedid_consu.emco_cod_empr_come%TYPE;
 TYPE t_cod_clie IS TABLE OF edu_histo_pedid_consu.cod_clie%TYPE;
 TYPE t_camp_proc IS TABLE OF edu_histo_pedid_consu.cam_proc%TYPE;

 v_cod_pais t_cod_pais ;
 v_cod_empr_come t_cod_empr_come ;
 v_cod_clie t_cod_clie ;
 v_camp_proc t_camp_proc ;

 rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;
 v_row_count NUMBER := 0;

begin

 OPEN curUPDConsuNuevas;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
 FETCH curUPDConsuNuevas BULK COLLECT INTO
 v_cod_pais ,
 v_cod_empr_come ,
 v_cod_clie ,
 v_camp_proc LIMIT rows;

 EXIT WHEN v_row_count = curUPDConsuNuevas%ROWCOUNT;
 v_row_count := curUPDConsuNuevas%ROWCOUNT;

 -- Bulk bind of data in memory table...
 FORALL i IN 1..v_cod_pais.count
 update edu_histo_pedid_consu pedcn
 set pedcn.cam_ulti_pedi = psCodigoPeriodo, -- cambia la recepcion para Campanah de Ultimo Pedido
 pedcn.cam_proc = v_camp_proc(i) ,
 pedcn.cod_ulti_nive = pedcn.num_pedi_fact+1 , -- ultimo nivel = num ped facutrados +1
-- pedcn.num_pedi_fact = 0 ,
 pedcn.ind_pedi = '1' ,
 pedcn.ind_fact = '0' ,
-- pedcn.ind_nuev = '0' ,
-- pedcn.ind_cons = '0' ,
-- pedcn.ind_prim_pedi = '1' ,
 usu_modi = psUsuario ,
 fec_modi = sysdate
 where pais_cod_pais = v_cod_pais(i)
 and emco_cod_empr_come = v_cod_empr_come(i)
 and cod_clie = v_cod_clie(i);

 END LOOP;
 CLOSE curUPDConsuNuevas;

 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_RECEP_PEDID_NUEVA: '||ls_sqlerrm);

 END EDU_PR_RECEP_PEDID_NUEVA;

/***************************************************************************
Descripcion : Procedure que Recepciona los Primeros de las Cons. Constantes
Fecha Creacion : 09/07/2007
Autor : Marco Silva
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodigoPeriodo : Codigo de Periodo
 psUsuario : Codigo de Usuario
***************************************************************************/

 PROCEDURE EDU_PR_RECEP_PEDID_CONST
 (psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psUsuario VARCHAR2
 )
 AS
 CURSOR curUPDConsuNuevas
 IS
 SELECT cons.cod_pais ,
 cons.cod_empr_come ,
 cons.cod_clie ,
 cons.cam_proc
 FROM EDU_TMP_PEDID_CONSU cons
 WHERE cons.COD_PAIS = psCodigoPais and
 cons.cod_empr_come = psCodEmpresa and
 cons.cam_proc = psCodigoPeriodo and
 cons.ind_fact = '0' and
 cons.ind_pedi = '2' and -- Extraen los Pedidos Normales desde el Comercial
 -- Actualizan: facturaron en campanha anterior (pasa a 2do nivel y se mantiene indicador nueva hasta q facture)
 exists ( select nu.cod_clie
 from edu_histo_pedid_consu nu
 where nu.pais_cod_pais = psCodigoPais and
 nu.emco_cod_empr_come = psCodEmpresa and
 nu.cod_clie = cons.cod_clie and
 nu.ind_cons = '1' and -- constantes
 nu.ind_fact = 1 and -- constantes q han facturado, pueden existir de la camp.anterior las q no facturaron
 nu.cam_ulti_pedi = GEN_FN_DEVUE_NSGTE_CAMPA(psCodigoPeriodo,-1)
 ) ;

 TYPE t_cod_pais IS TABLE OF edu_histo_pedid_consu.pais_cod_pais%TYPE;
 TYPE t_cod_empr_come IS TABLE OF edu_histo_pedid_consu.emco_cod_empr_come%TYPE;
 TYPE t_cod_clie IS TABLE OF edu_histo_pedid_consu.cod_clie%TYPE;
 TYPE t_camp_proc IS TABLE OF edu_histo_pedid_consu.cam_proc%TYPE;

 v_cod_pais t_cod_pais ;
 v_cod_empr_come t_cod_empr_come ;
 v_cod_clie t_cod_clie ;
 v_camp_proc t_camp_proc ;

 rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;
 v_row_count NUMBER := 0;

begin

 OPEN curUPDConsuNuevas;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
 FETCH curUPDConsuNuevas BULK COLLECT INTO
 v_cod_pais ,
 v_cod_empr_come ,
 v_cod_clie ,
 v_camp_proc LIMIT rows;

 EXIT WHEN v_row_count = curUPDConsuNuevas%ROWCOUNT;
 v_row_count := curUPDConsuNuevas%ROWCOUNT;

 -- Bulk bind of data in memory table...
 FORALL i IN 1..v_cod_pais.count
 update edu_histo_pedid_consu pedcn
 set pedcn.cam_ulti_pedi = psCodigoPeriodo, -- cambia la recepcion para Campanah de Ultimo Pedido
 pedcn.cam_proc = v_camp_proc(i) ,
 pedcn.cod_ulti_nive = pedcn.num_pedi_fact+1 , -- ultimo nivel = num ped facutrados +1
-- pedcn.num_pedi_fact = 0 ,
 pedcn.ind_pedi = '1' ,
 pedcn.ind_fact = '0' ,
-- pedcn.ind_nuev = '0' ,
-- pedcn.ind_cons = '0' ,
-- pedcn.ind_prim_pedi = '1' ,
 usu_modi = psUsuario ,
 fec_modi = sysdate
 where pais_cod_pais = v_cod_pais(i)
 and emco_cod_empr_come = v_cod_empr_come(i)
 and cod_clie = v_cod_clie(i);

 END LOOP;
 CLOSE curUPDConsuNuevas;

 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_RECEP_PEDID_CONST: '||ls_sqlerrm);

 END EDU_PR_RECEP_PEDID_CONST;

/***************************************************************************
Descripcion : Procedure que Recepciona los Primeros de las Cons. No Constantes
Fecha Creacion : 09/07/2007
Autor : Marco Silva
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodigoPeriodo : Codigo de Periodo
 psUsuario : Codigo de Usuario
***************************************************************************/

 PROCEDURE EDU_PR_RECEP_PEDID_NOCON
 (psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psUsuario VARCHAR2
 )
 AS
 CURSOR curUPDConsuNuevas
 IS
 SELECT cons.cod_pais ,
 cons.cod_empr_come ,
 cons.cod_clie ,
 cons.cam_proc
 FROM EDU_TMP_PEDID_CONSU cons
 WHERE cons.COD_PAIS = psCodigoPais and
 cons.cod_empr_come = psCodEmpresa and
 cons.cam_proc = psCodigoPeriodo and
 cons.ind_fact = '0' and
 cons.ind_pedi = '2' and -- Extraen los Pedidos Normales desde el Comercial
 exists ( select null
 from edu_histo_pedid_consu nu
 where nu.pais_cod_pais = psCodigoPais and
 nu.emco_cod_empr_come = psCodEmpresa and
 nu.cod_clie = cons.cod_clie and
 nu.ind_prim_pedi = 0 and -- no son primeros pedidos
 -- No pasaron pedido la campanha anterior
 ( ((nu.cam_ulti_pedi <> EDU_PKG_PROCE_COMER.GEN_FN_DEVUE_NSGTE_CAMPA(psCodigoPeriodo,-1)) and (nu.cam_ulti_pedi<>psCodigoPeriodo)) or
 -- pasaron pedido la campanha anterior pero no facturaron
 (nu.cam_ulti_pedi = EDU_PKG_PROCE_COMER.GEN_FN_DEVUE_NSGTE_CAMPA(psCodigoPeriodo,-1) and nu.ind_fact=0 ) or
 -- pasaron pedido la campanha anterior y facturaron pero son no constantes
 (nu.cam_ulti_pedi = EDU_PKG_PROCE_COMER.GEN_FN_DEVUE_NSGTE_CAMPA(psCodigoPeriodo,-1) and nu.ind_fact=1 and nu.ind_nuev=0 and nu.ind_cons=0 )
 )
 ) ;

 TYPE t_cod_pais IS TABLE OF edu_histo_pedid_consu.pais_cod_pais%TYPE;
 TYPE t_cod_empr_come IS TABLE OF edu_histo_pedid_consu.emco_cod_empr_come%TYPE;
 TYPE t_cod_clie IS TABLE OF edu_histo_pedid_consu.cod_clie%TYPE;
 TYPE t_camp_proc IS TABLE OF edu_histo_pedid_consu.cam_proc%TYPE;

 v_cod_pais t_cod_pais ;
 v_cod_empr_come t_cod_empr_come ;
 v_cod_clie t_cod_clie ;
 v_camp_proc t_camp_proc ;

 rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;
 v_row_count NUMBER := 0;

begin

 OPEN curUPDConsuNuevas;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
 FETCH curUPDConsuNuevas BULK COLLECT INTO
 v_cod_pais ,
 v_cod_empr_come ,
 v_cod_clie ,
 v_camp_proc LIMIT rows;

 EXIT WHEN v_row_count = curUPDConsuNuevas%ROWCOUNT;
 v_row_count := curUPDConsuNuevas%ROWCOUNT;

 -- Bulk bind of data in memory table...
 FORALL i IN 1..v_cod_pais.count
 update edu_histo_pedid_consu pedcn
 set pedcn.cam_ulti_pedi = psCodigoPeriodo, -- cambia la recepcion para Campanah de Ultimo Pedido
 pedcn.cam_proc = v_camp_proc(i) ,
 pedcn.cod_ulti_nive = pedcn.num_pedi_fact+1 , -- ultimo nivel = num ped facutrados +1
-- pedcn.num_pedi_fact = 0 ,
 pedcn.ind_pedi = '1' ,
 pedcn.ind_fact = '0' ,
 pedcn.ind_nuev = '0' ,
 pedcn.ind_cons = '0' ,
-- pedcn.ind_prim_pedi = '1' ,
 usu_modi = psUsuario ,
 fec_modi = sysdate
 where pais_cod_pais = v_cod_pais(i)
 and emco_cod_empr_come = v_cod_empr_come(i)
 and cod_clie = v_cod_clie(i);

 END LOOP;
 CLOSE curUPDConsuNuevas;

 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_RECEP_PEDID_NOCON: '||ls_sqlerrm);

 END EDU_PR_RECEP_PEDID_NOCON;

/***************************************************************************
Descripcion : Procedure que Recepciona Maestro Clientes
Fecha Creacion : 09/07/2007
Autor : Marco Silva
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodigoPeriodo : Codigo de Periodo
 psUsuario : Codigo de Usuario
***************************************************************************/

 PROCEDURE EDU_PR_RECEP_MAEST_CLIEN
 (psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psUsuario VARCHAR2
 )
 AS
 CURSOR curINSConsuNuevas
 IS
 SELECT
 cons.cod_pais,
 cons.cod_empr_come,
 cons.cod_clie,
 cons.cod_regi,
 cons.cod_zona,
 cons.cod_secc ,
 cons.cod_terr ,
 cons.ape_pate,
 cons.ape_mate,
 cons.ape_casa,
 cons.pri_nomb,
 cons.seg_nomb,
 cons.num_docu,
 cons.fec_naci,
 cons.num_tele_part,
 cons.ind_moro,
 cons.sal_clie,
 cons.num_celu, -- Se agrego numero de telefono celular
 cons.ape_nomb_comp,
	 cons.COD_DEPA,
	 cons.COD_MUNI,
	 cons.DES_POBL,
	 cons.CAM_INGR_COME -- Se agrego la campana de ingreso
 FROM EDU_TMP_PEDID_CONSU cons
 WHERE cons.COD_PAIS = psCodigoPais and
 cons.cod_empr_come = psCodEmpresa and
 cons.cam_proc = psCodigoPeriodo and
 not exists ( select nu.cod_clie from edu_maest_clien nu where nu.pais_cod_pais = psCodigoPais and
 nu.emco_cod_empr_come = psCodEmpresa and
 nu.cod_clie = cons.cod_clie ) ;

 CURSOR curUPDConsuNuevas
 IS
 SELECT
 cons.cod_pais,
 cons.cod_empr_come,
 cons.cod_clie,
 cons.cod_regi,
 cons.cod_zona,
 cons.cod_secc ,
 cons.cod_terr ,
 cons.ape_pate,
 cons.ape_mate,
 cons.ape_casa,
 cons.pri_nomb,
 cons.seg_nomb,
 cons.num_docu,
 cons.fec_naci,
 cons.num_tele_part,
 cons.ind_moro,
 cons.sal_clie ,
 cons.num_celu, -- Se agrego numero de telofono celular
 cons.ape_nomb_comp,
	 cons.COD_DEPA,
	 cons.COD_MUNI,
	 cons.DES_POBL,
	 cons.CAM_INGR_COME -- Se agrego la campana de ingreso
 FROM EDU_TMP_PEDID_CONSU cons
 WHERE cons.COD_PAIS = psCodigoPais and
 cons.cod_empr_come = psCodEmpresa and
 cons.cam_proc = psCodigoPeriodo and
 exists ( select nu.cod_clie from edu_maest_clien nu where nu.pais_cod_pais = psCodigoPais and
 nu.emco_cod_empr_come = psCodEmpresa and
 nu.cod_clie = cons.cod_clie ) ;

 TYPE t_cod_pais IS TABLE OF edu_maest_clien.pais_cod_pais%TYPE;
 TYPE t_cod_empr_come IS TABLE OF edu_maest_clien.emco_cod_empr_come%TYPE;
 TYPE t_cod_clie IS TABLE OF edu_maest_clien.cod_clie%TYPE;
 TYPE t_cod_regi IS TABLE OF edu_maest_clien.cod_regi%TYPE;
 TYPE t_cod_zona IS TABLE OF edu_maest_clien.cod_zona%TYPE;
 TYPE t_cod_secc IS TABLE OF edu_maest_clien.cod_secc%TYPE;
 TYPE t_cod_terr IS TABLE OF edu_maest_clien.cod_terr%TYPE;
 TYPE t_ape_pate IS TABLE OF edu_maest_clien.ape_pate%TYPE;
 TYPE t_ape_mate IS TABLE OF edu_maest_clien.ape_mate%TYPE;
 TYPE t_ape_casa IS TABLE OF edu_maest_clien.ape_casa%TYPE;
 TYPE t_pri_nomb IS TABLE OF edu_maest_clien.pri_nomb%TYPE;
 TYPE t_seg_nomb IS TABLE OF edu_maest_clien.seg_nomb%TYPE;
 TYPE t_num_docu IS TABLE OF edu_maest_clien.num_docu%TYPE;
 TYPE t_fec_naci IS TABLE OF edu_maest_clien.fec_naci%TYPE;
 TYPE t_num_tele_part IS TABLE OF edu_maest_clien.num_tele_part%TYPE;
 TYPE t_ind_moro IS TABLE OF edu_maest_clien.ind_moro%TYPE;
 TYPE t_sal_clie IS TABLE OF edu_maest_clien.sal_clie%TYPE;
 TYPE t_num_celu IS TABLE OF edu_maest_clien.num_celu%TYPE;
 TYPE t_nom_comp IS TABLE OF edu_maest_clien.ape_nomb_comp%TYPE;

 --poblacion
 TYPE t_cod_depa IS TABLE OF edu_maest_clien.COD_DEPA%TYPE;
 TYPE t_cod_muni IS TABLE OF edu_maest_clien.COD_MUNI%TYPE;
 TYPE t_des_pobl IS TABLE OF edu_maest_clien.DES_POBL%TYPE;

 --Campana ingreso
 TYPE t_cam_ingr_come IS TABLE OF			edu_maest_clien.CAM_INGR_COME%TYPE;

 v_cod_pais t_cod_pais ;
 v_cod_empr_come t_cod_empr_come ;
 v_cod_clie t_cod_clie ;
 v_cod_regi t_cod_regi ;
 v_cod_zona t_cod_zona ;
 v_cod_secc t_cod_secc ;
 v_cod_terr t_cod_terr ;
 v_ape_pate t_ape_pate ;
 v_ape_mate t_ape_mate ;
 v_ape_casa t_ape_casa ;
 v_pri_nomb t_pri_nomb ;
 v_seg_nomb t_seg_nomb ;
 v_num_docu t_num_docu ;
 v_fec_naci t_fec_naci ;
 v_num_tele_part t_num_tele_part ;
 v_ind_moro t_ind_moro ;
 v_sal_clie t_sal_clie ;
 v_num_celu t_num_celu ;
 v_nom_comp t_nom_comp ;

 --POBLACION
 v_cod_depa t_cod_depa ;
 v_cod_muni t_cod_muni ;
 v_des_pobl t_des_pobl		;

 --Campana Ingreso
 v_cam_ingr_come t_cam_ingr_come		;

-- Se cambio la forma de llenado de campos desde el bilk collect de insercion
-- TYPE solic_Det_tab_t IS TABLE OF edu_maest_clien%ROWTYPE INDEX BY BINARY_INTEGER;
-- sol_det_tab solic_det_tab_t; -- In-memory table

 rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;
 j BINARY_INTEGER := 0;
 v_row_count NUMBER := 0;

 v_row_count_ins NUMBER := 0;
 lscadena VARCHAR2(100) := 'a'||chr(10) || chr(13) || chr(20);
 lsreplace VARCHAR2(100) := 'a ';
begin


 OPEN curUPDConsuNuevas;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
 FETCH curUPDConsuNuevas BULK COLLECT INTO
 v_cod_pais ,
 v_cod_empr_come ,
 v_cod_clie ,
 v_cod_regi ,
 v_cod_zona ,
 v_cod_secc ,
 v_cod_terr ,
 v_ape_pate ,
 v_ape_mate ,
 v_ape_casa ,
 v_pri_nomb ,
 v_seg_nomb ,
 v_num_docu ,
 v_fec_naci ,
 v_num_tele_part ,
 v_ind_moro ,
 v_sal_clie ,
 v_num_celu,
 v_nom_comp 				,
							v_cod_depa				,
							v_cod_muni				,
							v_des_pobl ,
							v_cam_ingr_come
 LIMIT rows;

 EXIT WHEN v_row_count = curUPDConsuNuevas%ROWCOUNT;
 v_row_count := curUPDConsuNuevas%ROWCOUNT;

 -- Bulk bind of data in memory table...
 FORALL i IN 1..v_cod_pais.count
 update edu_maest_clien
 set pais_cod_pais = v_cod_pais(i),
 emco_cod_empr_come = v_cod_empr_come(i),
 cod_clie = v_cod_clie(i),
 cod_regi = v_cod_regi(i),
 cod_zona = v_cod_zona(i),
 cod_secc = v_cod_secc(i),
 cod_terr = v_cod_terr(i),
 ape_pate = v_ape_pate(i),
 ape_mate = v_ape_mate(i),
 ape_casa = v_ape_casa(i),
 pri_nomb = v_pri_nomb(i),
 seg_nomb = v_seg_nomb(i),
 num_docu = v_num_docu(i),
 fec_naci = v_fec_naci(i),
 num_tele_part = TRIM(translate(v_num_tele_part(i), lscadena,lsreplace)),
 ind_moro = v_ind_moro(i),
 sal_clie = v_sal_clie(i) ,
 num_celu = v_num_celu(i) , -- Se agrego numero de telofono celular
 ape_nomb_comp = v_nom_comp(i),
		 COD_DEPA = v_cod_depa(i), -- campos dpto. muni. poblacion
		 COD_MUNI = v_cod_muni(i),
		 DES_POBL = v_des_pobl(i),
 usu_modi = psUsuario ,
 fec_modi = sysdate ,
		 CAM_INGR_COME = v_cam_ingr_come(i) --Se agrego campana ingreso
 where pais_cod_pais = v_cod_pais(i)
 and emco_cod_empr_come = v_cod_empr_come(i)
 and cod_clie = v_cod_clie(i);

 END LOOP;
 CLOSE curUPDConsuNuevas;



         --PUEDEN VENIR DUPLICADAS SE HACE EL BEGIN EXCEPTION

 OPEN curINSConsuNuevas;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
          FETCH curINSConsuNuevas  BULK COLLECT INTO          v_cod_pais ,
 v_cod_empr_come ,
 v_cod_clie ,
 v_cod_regi ,
 v_cod_zona ,
 v_cod_secc ,
 v_cod_terr ,
 v_ape_pate ,
 v_ape_mate ,
 v_ape_casa ,
 v_pri_nomb ,
 v_seg_nomb ,
 v_num_docu ,
 v_fec_naci ,
 v_num_tele_part ,
 v_ind_moro ,
 v_sal_clie ,
 v_num_celu ,
 v_nom_comp 				,
							v_cod_depa				,
							v_cod_muni				,
							v_des_pobl ,
							v_cam_ingr_come
							LIMIT rows;
            --EXIT WHEN v_row_count_ins = curINSConsuNuevas%ROWCOUNT;
            --v_row_count_ins := curINSConsuNuevas%ROWCOUNT;
           IF v_cod_pais.COUNT > 0 THEN
 -- Bulk bind of data in memory table...
             --FORALL j IN 1..v_cod_pais.count
             FOR j IN v_cod_pais.FIRST .. v_cod_pais.LAST LOOP
               BEGIN
insert into edu_maest_clien
 (pais_cod_pais,
 emco_cod_empr_come,
 cod_clie,
 cod_regi,
 cod_zona,
 cod_secc,
 cod_terr,
 ape_pate,
 ape_mate,
 ape_casa,
 pri_nomb,
 seg_nomb,
 num_docu,
 fec_naci,
 num_tele_part,
 ind_moro,
 sal_clie,
 ape_nomb_comp,
 usu_digi,
 fec_digi,
 est_regi,
 num_celu,
 COD_DEPA,
 COD_MUNI,
 DES_POBL,
 CAM_INGR_COME
 )
values
 ( v_cod_pais(j),
 v_cod_empr_come(j),
 v_cod_clie(j),
 v_cod_regi(j),
 v_cod_zona(j),
 v_cod_secc(j),
 v_cod_terr(j),
 v_ape_pate(j),
 v_ape_mate(j),
 v_ape_casa(j),
 v_pri_nomb(j),
 v_seg_nomb(j),
 v_num_docu(j),
 v_fec_naci(j),
 TRIM(translate(v_num_tele_part(j), lscadena,lsreplace)),
 v_ind_moro(j),
 v_sal_clie(j) ,
 v_nom_comp(j),
 psUsuario ,
 sysdate ,
 1 ,
 v_num_celu(j),-- Se agrego numero de telofono celular
	v_cod_depa(j),-- Dpto,Muni,Pobla
	v_cod_muni(j),
	v_des_pobl(j),
	v_cam_ingr_come(j) --Se agrego campana ingreso
	);
                 EXCEPTION
                 WHEN OTHERS THEN
                   NULL;
                 END;
             END LOOP;
           END IF;
          EXIT WHEN curINSConsuNuevas%NOTFOUND;
 END LOOP;
 CLOSE curINSConsuNuevas;

/*
 OPEN curINSConsuNuevas;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
 FETCH curINSConsuNuevas BULK COLLECT INTO sol_det_tab LIMIT rows;
 EXIT WHEN sol_det_tab.COUNT = 0;

 -- Bulk bind of data in memory table...
 FORALL i in sol_det_tab.FIRST..sol_det_tab.LAST
 INSERT INTO edu_maest_clien VALUES sol_det_tab(i);

 END LOOP;
 CLOSE curINSConsuNuevas;
*/

 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_RECEP_MAEST_CLIEN: '||ls_sqlerrm);

 END EDU_PR_RECEP_MAEST_CLIEN;


/***************************************************************************
Descripcion : Procedure que Recepciona los Pedidos
Fecha Creacion : 09/07/2007
Autor : Marco Silva
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodigoPeriodo : Codigo de Periodo
 psUsuario : Codigo de Usuario
***************************************************************************/

 PROCEDURE EDU_PR_RECEP_PEDID
 (psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psUsuario VARCHAR2
 )
 IS
 BEGIN

 EDU_PR_RECEP_PRIME_PEDID
 (psCodigoPais ,
 psCodEmpresa ,
 psCodigoPeriodo ,
 psUsuario
 ) ;

 EDU_PR_RECEP_PEDID_NUEVA
 (psCodigoPais ,
 psCodEmpresa ,
 psCodigoPeriodo ,
 psUsuario
 ) ;

 EDU_PR_RECEP_PEDID_CONST
 (psCodigoPais ,
 psCodEmpresa ,
 psCodigoPeriodo ,
 psUsuario
 ) ;

 EDU_PR_RECEP_PEDID_NOCON
 (psCodigoPais ,
 psCodEmpresa ,
 psCodigoPeriodo ,
 psUsuario
 );

 EDU_PR_RECEP_PEDID_NOCON_CUV
 (psCodigoPais ,
 psCodEmpresa ,
 psCodigoPeriodo ,
 psUsuario
 );


 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_RECEP_PEDID: '||ls_sqlerrm);

 END EDU_PR_RECEP_PEDID;

/***************************************************************************
Descripcion : Procedure que Recepciona Factura de Cons. Nuevas
Fecha Creacion : 09/07/2007
Autor : Marco Silva
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodigoPeriodo : Codigo de Periodo
 psUsuario : Codigo de Usuario
***************************************************************************/

 PROCEDURE EDU_PR_RECEP_FACTU_NUEVA
 (psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psUsuario VARCHAR2
 )
 AS
 CURSOR curUPDConsuNuevas
 IS
 SELECT cons.pais_cod_pais ,
 cons.emco_cod_empr_come ,
 cons.cod_clie ,
 cons.num_pedi_fact + 1 , -- numero pedidos facturados
 1, -- indicador facturo pedido
 1, -- indicador nueva
 0, -- indicador constante
 0 -- indicador primer pedido
 FROM edu_histo_pedid_consu cons
 WHERE cons.pais_cod_pais = psCodigoPais and
 cons.emco_cod_empr_come = psCodEmpresa and
 cons.cam_proc = psCodigoPeriodo and
 cons.ind_prim_pedi = 1 and -- Primer Pedido campanha actual y aun no facturaron
 exists ( select nu.cod_clie from EDU_TMP_PEDID_CONSU nu where nu.cod_pais = cons.pais_cod_pais and
 nu.cod_empr_come = cons.emco_cod_empr_come and
 nu.cod_clie = cons.cod_clie and
 nu.cam_proc = cons.cam_proc and
 nu.ind_fact = 1) ;

 TYPE t_cod_pais IS TABLE OF edu_histo_pedid_consu.pais_cod_pais%TYPE;
 TYPE t_cod_empr_come IS TABLE OF edu_histo_pedid_consu.emco_cod_empr_come%TYPE;
 TYPE t_cod_clie IS TABLE OF edu_histo_pedid_consu.cod_clie%TYPE;
 TYPE t_num_pedi_fact IS TABLE OF edu_histo_pedid_consu.num_pedi_fact%TYPE;
 TYPE t_ind_fact IS TABLE OF edu_histo_pedid_consu.ind_fact%TYPE;
 TYPE t_ind_nuev IS TABLE OF edu_histo_pedid_consu.ind_nuev%TYPE;
 TYPE t_ind_cons IS TABLE OF edu_histo_pedid_consu.ind_cons%TYPE;
 TYPE t_ind_prim_pedi IS TABLE OF edu_histo_pedid_consu.ind_prim_pedi%TYPE;

 v_cod_pais t_cod_pais ;
 v_cod_empr_come t_cod_empr_come ;
 v_cod_clie t_cod_clie ;
 v_num_pedi_fact t_num_pedi_fact ;
 v_ind_fact t_ind_fact ;
 v_ind_nuev t_ind_nuev ;
 v_ind_cons t_ind_cons ;
 v_ind_prim_pedi t_ind_prim_pedi ;

 rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;
 v_row_count NUMBER := 0;

begin

 OPEN curUPDConsuNuevas;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
 FETCH curUPDConsuNuevas BULK COLLECT INTO
 v_cod_pais ,
 v_cod_empr_come ,
 v_cod_clie ,
 v_num_pedi_fact ,
 v_ind_fact ,
 v_ind_nuev ,
 v_ind_cons ,
 v_ind_prim_pedi LIMIT rows;

 EXIT WHEN v_row_count = curUPDConsuNuevas%ROWCOUNT;
 v_row_count := curUPDConsuNuevas%ROWCOUNT;

 -- Bulk bind of data in memory table...
 FORALL i IN 1..v_cod_pais.count
 update edu_histo_pedid_consu fact
 set fact.num_pedi_fact = v_num_pedi_fact(i) ,
 fact.ind_fact = v_ind_fact(i) ,
 fact.ind_nuev = v_ind_nuev(i) ,
 fact.ind_cons = v_ind_cons(i) ,
 fact.ind_prim_pedi = v_ind_prim_pedi(i) ,
 fact.usu_modi = psUsuario ,
 fact.fec_modi = sysdate
 where fact.pais_cod_pais = v_cod_pais(i)
 and fact.emco_cod_empr_come = v_cod_empr_come(i)
 and fact.cod_clie = v_cod_clie(i);

 END LOOP;
 CLOSE curUPDConsuNuevas;


 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_RECEP_FACTU_NUEVA: '||ls_sqlerrm);

 END EDU_PR_RECEP_FACTU_NUEVA;

/***************************************************************************
Descripcion : Procedure que Recepciona Factura de Cons. con 2do Pedido
Fecha Creacion : 09/07/2007
Autor : Marco Silva
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodigoPeriodo : Codigo de Periodo
 psUsuario : Codigo de Usuario
***************************************************************************/
PROCEDURE EDU_PR_RECEP_FACTU_SEGDO_PEDID
 (psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psUsuario VARCHAR2
 )
 AS
 CURSOR curUPDConsuNuevas
 IS
 SELECT cons.pais_cod_pais ,
 cons.emco_cod_empr_come ,
 cons.cod_clie ,
 cons.num_pedi_fact + 1 , -- numero pedidos facturados
 1, -- indicador facturo pedido
 0, -- indicador nueva, Ya NO es NUEVA
 1, -- indicador constante, Pasa a ser CONSTANTE
 0 -- indicador primer pedido
 FROM edu_histo_pedid_consu cons
 WHERE cons.pais_cod_pais = psCodigoPais and
 cons.emco_cod_empr_come = psCodEmpresa and
 cons.cam_proc = psCodigoPeriodo and
 cons.ind_nuev = 1 and -- nuevas de la campanha anterior
 cons.ind_fact = 0 and -- aun no facturaron, tambien existen Nuevas de la campanha actual q SI facturaron
 exists ( select nu.cod_clie from EDU_TMP_PEDID_CONSU nu where nu.cod_pais = cons.pais_cod_pais and
 nu.cod_empr_come = cons.emco_cod_empr_come and
 nu.cod_clie = cons.cod_clie and
 nu.cam_proc = cons.cam_proc and
 nu.ind_fact = 1) ;

 TYPE t_cod_pais IS TABLE OF edu_histo_pedid_consu.pais_cod_pais%TYPE;
 TYPE t_cod_empr_come IS TABLE OF edu_histo_pedid_consu.emco_cod_empr_come%TYPE;
 TYPE t_cod_clie IS TABLE OF edu_histo_pedid_consu.cod_clie%TYPE;
 TYPE t_num_pedi_fact IS TABLE OF edu_histo_pedid_consu.num_pedi_fact%TYPE;
 TYPE t_ind_fact IS TABLE OF edu_histo_pedid_consu.ind_fact%TYPE;
 TYPE t_ind_nuev IS TABLE OF edu_histo_pedid_consu.ind_nuev%TYPE;
 TYPE t_ind_cons IS TABLE OF edu_histo_pedid_consu.ind_cons%TYPE;
 TYPE t_ind_prim_pedi IS TABLE OF edu_histo_pedid_consu.ind_prim_pedi%TYPE;

 v_cod_pais t_cod_pais ;
 v_cod_empr_come t_cod_empr_come ;
 v_cod_clie t_cod_clie ;
 v_num_pedi_fact t_num_pedi_fact ;
 v_ind_fact t_ind_fact ;
 v_ind_nuev t_ind_nuev ;
 v_ind_cons t_ind_cons ;
 v_ind_prim_pedi t_ind_prim_pedi ;

 rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;
 v_row_count NUMBER := 0;

begin

 OPEN curUPDConsuNuevas;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
 FETCH curUPDConsuNuevas BULK COLLECT INTO
 v_cod_pais ,
 v_cod_empr_come ,
 v_cod_clie ,
 v_num_pedi_fact ,
 v_ind_fact ,
 v_ind_nuev ,
 v_ind_cons ,
 v_ind_prim_pedi LIMIT rows;

 EXIT WHEN v_row_count = curUPDConsuNuevas%ROWCOUNT;
 v_row_count := curUPDConsuNuevas%ROWCOUNT;

 -- Bulk bind of data in memory table...
 FORALL i IN 1..v_cod_pais.count
 update edu_histo_pedid_consu fact
 set fact.num_pedi_fact = v_num_pedi_fact(i) ,
 fact.ind_fact = v_ind_fact(i) ,
 fact.ind_nuev = v_ind_nuev(i) ,
 fact.ind_cons = v_ind_cons(i) ,
 fact.ind_prim_pedi = v_ind_prim_pedi(i) ,
 fact.usu_modi = psUsuario ,
 fact.fec_modi = sysdate
 where fact.pais_cod_pais = v_cod_pais(i)
 and fact.emco_cod_empr_come = v_cod_empr_come(i)
 and fact.cod_clie = v_cod_clie(i);

 END LOOP;
 CLOSE curUPDConsuNuevas;


 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_RECEP_FACTU_SEGDO_PEDID: '||ls_sqlerrm);

 END EDU_PR_RECEP_FACTU_SEGDO_PEDID;

/***************************************************************************
Descripcion : Procedure que Recepciona Factura de Cons. Constantes
Fecha Creacion : 09/07/2007
Autor : Marco Silva
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodigoPeriodo : Codigo de Periodo
 psUsuario : Codigo de Usuario
***************************************************************************/
PROCEDURE EDU_PR_RECEP_FACTU_CONST
 (psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psUsuario VARCHAR2
 )
 AS
 CURSOR curUPDConsuNuevas
 IS
 SELECT cons.pais_cod_pais ,
 cons.emco_cod_empr_come ,
 cons.cod_clie ,
 cons.num_pedi_fact + 1 , -- numero pedidos facturados
 1, -- indicador facturo pedido
 0, -- indicador nueva, NO es NUEVA
 1, -- indicador constante, Sigue siendo CONSTANTE
 0 -- indicador primer pedido
 FROM edu_histo_pedid_consu cons
 WHERE cons.pais_cod_pais = psCodigoPais and
 cons.emco_cod_empr_come = psCodEmpresa and
 cons.cam_proc = psCodigoPeriodo and
 cons.ind_cons = 1 and -- constantes de la campanha anterior
 cons.ind_fact = 0 and -- aun no facturaron, tambien existen constantes de la campanha actual q SI facturaron
 exists ( select nu.cod_clie from EDU_TMP_PEDID_CONSU nu where nu.cod_pais = cons.pais_cod_pais and
 nu.cod_empr_come = cons.emco_cod_empr_come and
 nu.cod_clie = cons.cod_clie and
 nu.cam_proc = cons.cam_proc and
 nu.ind_fact = 1) ;

 TYPE t_cod_pais IS TABLE OF edu_histo_pedid_consu.pais_cod_pais%TYPE;
 TYPE t_cod_empr_come IS TABLE OF edu_histo_pedid_consu.emco_cod_empr_come%TYPE;
 TYPE t_cod_clie IS TABLE OF edu_histo_pedid_consu.cod_clie%TYPE;
 TYPE t_num_pedi_fact IS TABLE OF edu_histo_pedid_consu.num_pedi_fact%TYPE;
 TYPE t_ind_fact IS TABLE OF edu_histo_pedid_consu.ind_fact%TYPE;
 TYPE t_ind_nuev IS TABLE OF edu_histo_pedid_consu.ind_nuev%TYPE;
 TYPE t_ind_cons IS TABLE OF edu_histo_pedid_consu.ind_cons%TYPE;
 TYPE t_ind_prim_pedi IS TABLE OF edu_histo_pedid_consu.ind_prim_pedi%TYPE;

 v_cod_pais t_cod_pais ;
 v_cod_empr_come t_cod_empr_come ;
 v_cod_clie t_cod_clie ;
 v_num_pedi_fact t_num_pedi_fact ;
 v_ind_fact t_ind_fact ;
 v_ind_nuev t_ind_nuev ;
 v_ind_cons t_ind_cons ;
 v_ind_prim_pedi t_ind_prim_pedi ;

 rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;
 v_row_count NUMBER := 0;

begin

 OPEN curUPDConsuNuevas;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
 FETCH curUPDConsuNuevas BULK COLLECT INTO
 v_cod_pais ,
 v_cod_empr_come ,
 v_cod_clie ,
 v_num_pedi_fact ,
 v_ind_fact ,
 v_ind_nuev ,
 v_ind_cons ,
 v_ind_prim_pedi LIMIT rows;

 EXIT WHEN v_row_count = curUPDConsuNuevas%ROWCOUNT;
 v_row_count := curUPDConsuNuevas%ROWCOUNT;

 -- Bulk bind of data in memory table...
 FORALL i IN 1..v_cod_pais.count
 update edu_histo_pedid_consu fact
 set fact.num_pedi_fact = v_num_pedi_fact(i) ,
 fact.ind_fact = v_ind_fact(i) ,
 fact.ind_nuev = v_ind_nuev(i) ,
 fact.ind_cons = v_ind_cons(i) ,
 fact.ind_prim_pedi = v_ind_prim_pedi(i) ,
 fact.usu_modi = psUsuario ,
 fact.fec_modi = sysdate
 where fact.pais_cod_pais = v_cod_pais(i)
 and fact.emco_cod_empr_come = v_cod_empr_come(i)
 and fact.cod_clie = v_cod_clie(i);

 END LOOP;
 CLOSE curUPDConsuNuevas;


 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_RECEP_FACTU_CONST: '||ls_sqlerrm);

 END EDU_PR_RECEP_FACTU_CONST;

/***************************************************************************
Descripcion : Procedure que Recepciona Facturacion de Cons. No Constantes
Fecha Creacion : 09/07/2007
Autor : Marco Silva
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodigoPeriodo : Codigo de Periodo
 psUsuario : Codigo de Usuario
***************************************************************************/
PROCEDURE EDU_PR_RECEP_FACTU_NOCON
 (psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psUsuario VARCHAR2
 )
 AS
 CURSOR curUPDConsuNuevas
 IS
 SELECT cons.pais_cod_pais ,
 cons.emco_cod_empr_come ,
 cons.cod_clie ,
 cons.num_pedi_fact + 1 , -- numero pedidos facturados
 1, -- indicador facturo pedido
 0, -- indicador nueva, NO es NUEVA
 0, -- indicador constante, No es CONSTANTE
 0 -- indicador primer pedido
 FROM edu_histo_pedid_consu cons
 WHERE cons.pais_cod_pais = psCodigoPais and
 cons.emco_cod_empr_come = psCodEmpresa and
 cons.cam_proc = psCodigoPeriodo and
 cons.ind_nuev = 0 and -- no nuevas
 cons.ind_prim_pedi = 0 and -- no primer ped
 cons.ind_cons = 0 and -- no constantes
 cons.ind_fact = 0 and -- aun no facturaron
 exists ( select nu.cod_clie from EDU_TMP_PEDID_CONSU nu where nu.cod_pais = cons.pais_cod_pais and
 nu.cod_empr_come = cons.emco_cod_empr_come and
 nu.cod_clie = cons.cod_clie and
 nu.cam_proc = cons.cam_proc and
 nu.ind_fact = 1) ;

 TYPE t_cod_pais IS TABLE OF edu_histo_pedid_consu.pais_cod_pais%TYPE;
 TYPE t_cod_empr_come IS TABLE OF edu_histo_pedid_consu.emco_cod_empr_come%TYPE;
 TYPE t_cod_clie IS TABLE OF edu_histo_pedid_consu.cod_clie%TYPE;
 TYPE t_num_pedi_fact IS TABLE OF edu_histo_pedid_consu.num_pedi_fact%TYPE;
 TYPE t_ind_fact IS TABLE OF edu_histo_pedid_consu.ind_fact%TYPE;
 TYPE t_ind_nuev IS TABLE OF edu_histo_pedid_consu.ind_nuev%TYPE;
 TYPE t_ind_cons IS TABLE OF edu_histo_pedid_consu.ind_cons%TYPE;
 TYPE t_ind_prim_pedi IS TABLE OF edu_histo_pedid_consu.ind_prim_pedi%TYPE;

 v_cod_pais t_cod_pais ;
 v_cod_empr_come t_cod_empr_come ;
 v_cod_clie t_cod_clie ;
 v_num_pedi_fact t_num_pedi_fact ;
 v_ind_fact t_ind_fact ;
 v_ind_nuev t_ind_nuev ;
 v_ind_cons t_ind_cons ;
 v_ind_prim_pedi t_ind_prim_pedi ;

 rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;
 v_row_count NUMBER := 0;

begin

 OPEN curUPDConsuNuevas;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
 FETCH curUPDConsuNuevas BULK COLLECT INTO
 v_cod_pais ,
 v_cod_empr_come ,
 v_cod_clie ,
 v_num_pedi_fact ,
 v_ind_fact ,
 v_ind_nuev ,
 v_ind_cons ,
 v_ind_prim_pedi LIMIT rows;

 EXIT WHEN v_row_count = curUPDConsuNuevas%ROWCOUNT;
 v_row_count := curUPDConsuNuevas%ROWCOUNT;

 -- Bulk bind of data in memory table...
 FORALL i IN 1..v_cod_pais.count
 update edu_histo_pedid_consu fact
 set fact.num_pedi_fact = v_num_pedi_fact(i) ,
 fact.ind_fact = v_ind_fact(i) ,
 fact.ind_nuev = v_ind_nuev(i) ,
 fact.ind_cons = v_ind_cons(i) ,
 fact.ind_prim_pedi = v_ind_prim_pedi(i) ,
 fact.usu_modi = psUsuario ,
 fact.fec_modi = sysdate
 where fact.pais_cod_pais = v_cod_pais(i)
 and fact.emco_cod_empr_come = v_cod_empr_come(i)
 and fact.cod_clie = v_cod_clie(i);

 END LOOP;
 CLOSE curUPDConsuNuevas;


 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_RECEP_FACTU_NOCON: '||ls_sqlerrm);

 END EDU_PR_RECEP_FACTU_NOCON;

/***************************************************************************
Descripcion : Procedure que Recepciona Facturacion
Fecha Creacion : 09/07/2007
Autor : Marco Silva
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodigoPeriodo : Codigo de Periodo
 psUsuario : Codigo de Usuario
***************************************************************************/
PROCEDURE EDU_PR_RECEP_PEDID_FACTU (
 psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psUsuario VARCHAR2
)
IS
BEGIN

 EDU_PR_RECEP_FACTU_NUEVA
 (psCodigoPais ,
 psCodEmpresa ,
 psCodigoPeriodo ,
 psUsuario
 ) ;

 EDU_PR_RECEP_FACTU_SEGDO_PEDID
 (psCodigoPais ,
 psCodEmpresa ,
 psCodigoPeriodo ,
 psUsuario
 ) ;

 EDU_PR_RECEP_FACTU_CONST
 (psCodigoPais ,
 psCodEmpresa ,
 psCodigoPeriodo ,
 psUsuario
 ) ;

 EDU_PR_RECEP_FACTU_NOCON
 (psCodigoPais ,
 psCodEmpresa ,
 psCodigoPeriodo ,
 psUsuario
 );

 EDU_PR_ACTUA_APTAS_POR_PROGR
 (psCodigoPais ,
 psCodEmpresa ,
 psCodigoPeriodo ,
 psUsuario
 ) ;

 EDU_PR_ACTUA_APTAS_POR_COMFI
 (psCodigoPais ,
 psCodEmpresa ,
 psCodigoPeriodo ,
 psUsuario
 ) ;

 /* Actualiza el indicador de despacho de clasificacion en la tabla historia de
 clasificaciones */
 EDU_PR_ACTUA_CLASI_PEDID_FACTU(
 psCodigoPais ,
 psCodEmpresa ,
 psCodigoPeriodo ,
 psUsuario
 ) ;
 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_RECEP_PEDID_FACTU: '||ls_sqlerrm);

 END EDU_PR_RECEP_PEDID_FACTU;

/***************************************************************************
Descripcion : Procedure que Procesa Carga de Regiones
Fecha Creacion : 09/07/2007
Autor : Marco Silva
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psUsuario : Codigo de Usuario
***************************************************************************/

 PROCEDURE EDU_PR_CARGA_REGIO
 (psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psUsuario VARCHAR2
 )
 AS
 CURSOR curINSregiones
 IS
 select reg.cod_pais,
 reg.cod_empr_come ,
 reg.cod_regi ,
 reg.des_regi,
			 reg.NOM_GERE_REGI,
			 reg.EMA_GERE_REGI,
			 reg.COD_GERE_REGI,
 reg.IND_ACTI
 from EDU_TMP_REGIO reg
 WHERE reg.cod_pais = psCodigoPais and
 reg.cod_empr_come = psCodEmpresa and
 not exists ( select nu.cod_regi from edu_regio nu where nu.pais_cod_pais = psCodigoPais and
 nu.emco_cod_empr_come = psCodEmpresa and
 nu.cod_regi = reg.cod_regi ) ;

 CURSOR curUPDregiones
 IS
 select reg.cod_pais,
 reg.cod_empr_come ,
 reg.cod_regi ,
 reg.des_regi,
			 reg.NOM_GERE_REGI,
			 reg.EMA_GERE_REGI,
 			 reg.COD_GERE_REGI,
 REG.IND_ACTI
 from EDU_TMP_REGIO reg
 WHERE reg.cod_pais = psCodigoPais and
 reg.cod_empr_come = psCodEmpresa and
 exists ( select nu.cod_regi from edu_regio nu where nu.pais_cod_pais = psCodigoPais and
 nu.emco_cod_empr_come = psCodEmpresa and
 nu.cod_regi = reg.cod_regi ) ;
 TYPE t_cod_pais IS TABLE OF edu_regio.pais_cod_pais%TYPE ;
 TYPE t_cod_emp IS TABLE OF edu_regio.emco_cod_empr_come%TYPE ;
 TYPE t_cod_regi IS TABLE OF edu_regio.cod_regi%TYPE ;
 TYPE t_des_regi IS TABLE OF edu_regio.des_regi%TYPE ;

--GERENTE REGIONAL
 TYPE t_nom_gere IS TABLE OF edu_regio.NOM_GERE_REGI%TYPE;
 TYPE t_ema_gere IS TABLE OF edu_regio.EMA_GERE_REGI%TYPE;
 TYPE t_cod_gere IS TABLE OF edu_regio.COD_GERE_REGI%TYPE;

 --INDICADOR ACTIVIDAD
 TYPE t_ind_acti IS TABLE OF edu_regio.IND_ACTI%TYPE;


 v_cod_pais t_cod_pais ;
 v_cod_emp t_cod_emp ;
 v_cod_regi t_cod_regi ;
 v_des_regi t_des_regi ;
 --GERENTE REGIONAL
 v_nom_gere 	 t_nom_gere						;
 v_ema_gere		 t_ema_gere						;
 v_cod_gere		 t_cod_gere						;

--INDICADOR ACTIVIDAD
 v_ind_acti		 t_ind_acti						;

 rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;
 j BINARY_INTEGER := 0;
 v_row_count NUMBER := 0;
 v_row_count_ins NUMBER := 0;

begin


 OPEN curUPDregiones;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
 FETCH curUPDregiones BULK COLLECT INTO
 v_cod_pais ,
 v_cod_emp ,
 v_cod_regi ,
 v_des_regi,
							v_nom_gere,
							v_ema_gere,
							v_cod_gere,
 v_ind_acti LIMIT rows;

 EXIT WHEN v_row_count = curUPDregiones%ROWCOUNT;
 v_row_count := curUPDregiones%ROWCOUNT;

 -- Bulk bind of data in memory table...
 FORALL i IN 1..v_cod_pais.count
 UPDATE edu_regio regio
 SET regio.cod_regi = v_cod_regi(i) ,
 regio.des_regi = v_des_regi(i) ,
				 regio.NOM_GERE_REGI = v_nom_gere(i),
				 regio.EMA_GERE_REGI = v_ema_gere(i),
				 regio.COD_GERE_REGI = v_cod_gere(i),
 regio.IND_ACTI = v_ind_acti(i),
 regio.usu_modi = psUsuario ,
 regio.fec_modi = sysdate
 where regio.pais_cod_pais = v_cod_pais(i) and
 regio.emco_cod_empr_come = v_cod_emp(i) and
 regio.cod_regi = v_cod_regi(i) ;
 END LOOP;
 CLOSE curUPDregiones;

 OPEN curINSregiones;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
 FETCH curINSregiones BULK COLLECT INTO
 v_cod_pais ,
 v_cod_emp ,
 v_cod_regi ,
 v_des_regi		 ,
							v_nom_gere		 ,
							v_ema_gere		 ,
							v_cod_gere ,
 v_ind_acti LIMIT rows;
 EXIT WHEN v_row_count_ins = curINSregiones%ROWCOUNT;
 v_row_count_ins := curINSregiones%ROWCOUNT;

 -- Bulk bind of data in memory table...
 FORALL j IN 1..v_cod_pais.count
 insert into edu_regio
 (pais_cod_pais,
 emco_cod_empr_come,
 cod_regi,
 des_regi,
 cod_regi_ante,
 inst_cod_inst,
 usu_digi,
 fec_digi,
 usu_modi,
 fec_modi,
 est_regi,
		 NOM_GERE_REGI,
		 EMA_GERE_REGI,
		 COD_GERE_REGI,
 IND_ACTI
		 )
 values (
 v_cod_pais(j),
 v_cod_emp(j) ,
 v_cod_regi(j) ,
 v_des_regi(j) ,
 null,
 '',
 psUsuario,
 sysdate,
 '',
 null ,
 '1' ,
			 v_nom_gere(j),
			 v_ema_gere(j),
			 v_cod_gere(j),
 v_ind_acti(j)
 ) ;
 END LOOP;
 CLOSE curINSregiones;

 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_CARGA_REGIO: '||ls_sqlerrm);

 END EDU_PR_CARGA_REGIO;

/***************************************************************************
Descripcion : Procedure que Procesa Carga de Zonas
Fecha Creacion : 09/07/2007
Autor : Marco Silva
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psUsuario : Codigo de Usuario
***************************************************************************/

 PROCEDURE EDU_PR_CARGA_ZONAS
 (psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psUsuario VARCHAR2
 )
 AS
 CURSOR curINSzonas
 IS
 select zon.cod_pais ,
 zon.cod_empr_come ,
 zon.cod_regi ,
 zon.cod_zona ,
 zon.des_zona ,
 zon.ema_gere_zona,
			 zon.est_zona
 from EDU_TMP_ZONA zon
 WHERE zon.cod_pais = psCodigoPais and
 zon.cod_empr_come = psCodEmpresa and
 not exists ( select nu.cod_zona from edu_zona nu where nu.pais_cod_pais = psCodigoPais and
 nu.emco_cod_empr_come = psCodEmpresa and
 nu.regi_cod_regi = zon.cod_regi and
 nu.cod_zona = zon.cod_zona ) ;
 CURSOR curUPDzonas
 IS
 select zon.cod_pais ,
 zon.cod_empr_come ,
 zon.cod_regi ,
 zon.cod_zona ,
 zon.des_zona ,
 zon.ema_gere_zona,
			 zon.est_zona
 from EDU_TMP_ZONA zon
 WHERE zon.cod_pais = psCodigoPais and
 zon.cod_empr_come = psCodEmpresa and
 exists ( select nu.cod_zona from edu_zona nu where nu.pais_cod_pais = psCodigoPais and
 nu.emco_cod_empr_come = psCodEmpresa and
 nu.regi_cod_regi = zon.cod_regi and
 nu.cod_zona = zon.cod_zona ) ;

 TYPE t_cod_pais IS TABLE OF edu_regio.pais_cod_pais%TYPE ;
 TYPE t_cod_emp IS TABLE OF edu_regio.emco_cod_empr_come%TYPE ;
 TYPE t_cod_regi IS TABLE OF edu_regio.cod_regi%TYPE ;
 TYPE t_cod_zona IS TABLE OF edu_zona.cod_zona%TYPE ;
 TYPE t_des_zona IS TABLE OF edu_zona.des_zona%TYPE ;
 TYPE t_ema_gere_zona IS TABLE OF edu_zona.ema_gere_zona%TYPE ;
 --status d ezona
 TYPE t_est_zona IS TABLE OF edu_zona.est_zona%TYPE ;

 v_cod_pais t_cod_pais ;
 v_cod_emp t_cod_emp ;
 v_cod_regi t_cod_regi ;
 v_cod_zona t_cod_zona ;
 v_des_zona t_des_zona ;
 v_ema_gere_zona t_ema_gere_zona ;
 --variable de status de zona
 v_est_zona	 	 t_est_zona 						;


 v_row_count NUMBER := 0;
 v_row_count_ins NUMBER := 0;
 rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;
 j BINARY_INTEGER := 0;

begin

 OPEN curUPDzonas;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
 FETCH curUPDzonas BULK COLLECT INTO
 v_cod_pais ,
 v_cod_emp ,
 v_cod_regi ,
 v_cod_zona ,
 v_des_zona ,
 v_ema_gere_zona ,
							v_est_zona	LIMIT rows;

 EXIT WHEN v_row_count = curUPDzonas%ROWCOUNT;
 v_row_count := curUPDzonas%ROWCOUNT;

 -- Bulk bind of data in memory table...
 FORALL i IN 1..v_cod_pais.count
 UPDATE edu_zona zona
 SET zona.des_zona = v_des_zona(i),
 zona.ema_gere_zona = v_ema_gere_zona(i) ,
				 zona.est_zona = v_est_zona(i),
 zona.usu_modi = psUsuario ,
 zona.fec_modi = sysdate
 where zona.pais_cod_pais = v_cod_pais(i) and
 zona.emco_cod_empr_come = v_cod_emp(i) and
 zona.regi_cod_regi = v_cod_regi(i) and
 zona.cod_zona = v_cod_zona(i) ;
 END LOOP;
 CLOSE curUPDzonas;

 OPEN curINSzonas;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
 FETCH curINSzonas BULK COLLECT INTO
 v_cod_pais ,
 v_cod_emp ,
 v_cod_regi ,
 v_cod_zona ,
 v_des_zona ,
 v_ema_gere_zona ,
							v_est_zona LIMIT rows;

 EXIT WHEN v_row_count_ins = curINSzonas%ROWCOUNT;
 v_row_count_ins := curINSzonas%ROWCOUNT;

 -- Bulk bind of data in memory table...
 FORALL j IN 1..v_cod_pais.count
 insert into edu_zona
 (pais_cod_pais,
 emco_cod_empr_come,
 regi_cod_regi,
 cod_zona,
 des_zona,
 cod_zona_ante,
 usu_digi,
 fec_digi,
 usu_modi,
 fec_modi,
 est_regi ,
 ema_gere_zona ,
		 est_zona)
 values (
 v_cod_pais(j) ,
 v_cod_emp(j) ,
 v_cod_regi(j) ,
 v_cod_zona(j) ,
 v_des_zona(j) ,
 '',
 psUsuario,
 sysdate,
 '',
 null ,
 '1' ,
 v_ema_gere_zona(j),
			 v_est_zona(j)
 ) ;

 END LOOP;
 CLOSE curINSzonas;

 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_CARGA_ZONAS: '||ls_sqlerrm);

 END EDU_PR_CARGA_ZONAS;

/***************************************************************************
Descripcion : Procedure que Procesa Carga Archivo de Control
Fecha Creacion : 09/07/2007
Autor : Marco Silva
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psUsuario : Codigo de Usuario
***************************************************************************/

 PROCEDURE EDU_PR_CARGA_CONTR_FACTU
 (psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psUsuario VARCHAR2
 )
 AS
 CURSOR curINScontrol
 IS
 select fac.cod_pais ,
 fac.cod_empr_come ,
 fac.cod_peri,
 fac.fec_proc,
 fac.est_camp,
 fac.ind_camp_acti,
 psUsuario,
 sysdate,
 '',
 null ,
 '1'
 from EDU_TMP_CONTR_FACTU fac
 WHERE fac.cod_pais = psCodigoPais and
 fac.cod_empr_come = psCodEmpresa and
 not exists ( select nu.cod_peri from edu_contr_factu nu where nu.pais_cod_pais = psCodigoPais and
 nu.emco_cod_empr_come = psCodEmpresa and
 nu.cod_peri = fac.cod_peri );

 CURSOR curUPDcontrol
 IS
 select fac.cod_pais ,
 fac.cod_empr_come ,
 fac.cod_peri,
 fac.fec_proc
 from EDU_TMP_CONTR_FACTU fac
 WHERE fac.cod_pais = psCodigoPais and
 fac.cod_empr_come = psCodEmpresa and
 exists ( select nu.cod_peri from edu_contr_factu nu where nu.pais_cod_pais = psCodigoPais and
 nu.emco_cod_empr_come = psCodEmpresa and
 nu.cod_peri = fac.cod_peri );
 TYPE t_cod_pais IS TABLE OF edu_contr_factu.pais_cod_pais%TYPE ;
 TYPE t_cod_emp IS TABLE OF edu_contr_factu.emco_cod_empr_come%TYPE ;
 TYPE t_cod_peri IS TABLE OF edu_contr_factu.cod_peri%TYPE ;
 TYPE t_fec_proc IS TABLE OF edu_contr_factu.fec_proc%TYPE ;

 v_cod_pais t_cod_pais ;
 v_cod_emp t_cod_emp ;
 v_cod_peri t_cod_peri ;
 v_fec_proc t_fec_proc ;

 TYPE edu_factu_tab_t IS TABLE OF edu_contr_factu%ROWTYPE INDEX BY BINARY_INTEGER;
 edu_factu_tab edu_factu_tab_t; -- In-memory table

 v_row_count NUMBER := 0;
 rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;
 v_row_insert number := 0 ;
begin

-- Cerramos todas las campanhas
 UPDATE edu_contr_factu fact
 SET fact.ind_camp_acti = 0 ,
 fact.est_camp = 0 ,
 fact.usu_modi = psUsuario ,
 fact.fec_modi = sysdate
 where fact.pais_cod_pais = psCodigoPais and
 fact.emco_cod_empr_come = psCodEmpresa ;


 OPEN curUPDcontrol;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
 FETCH curUPDcontrol BULK COLLECT INTO
 v_cod_pais ,
 v_cod_emp ,
 v_cod_peri ,
 v_fec_proc LIMIT rows;

 EXIT WHEN v_row_count = curUPDcontrol%ROWCOUNT;
 v_row_count := curUPDcontrol%ROWCOUNT;

 -- Bulk bind of data in memory table...
 FORALL i IN 1..v_cod_pais.count
 UPDATE edu_contr_factu fac
 SET fac.fec_proc = v_fec_proc(i),
 fac.usu_modi = psUsuario ,
 fac.fec_modi = sysdate ,
 fac.ind_camp_acti = 1 ,
 fac.est_camp = 1
 where fac.pais_cod_pais = v_cod_pais(i) and
 fac.emco_cod_empr_come = v_cod_emp(i) and
 fac.cod_peri = v_cod_peri(i) ;
 END LOOP;
 CLOSE curUPDcontrol;

-- Inserta en edu_zona
 OPEN curINScontrol;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
 FETCH curINScontrol BULK COLLECT INTO edu_factu_tab LIMIT rows;
 EXIT WHEN edu_factu_tab.COUNT = 0;
 v_row_insert := 1 ;

 -- Bulk bind of data in memory table...
 FORALL i in edu_factu_tab.FIRST..edu_factu_tab.LAST
 INSERT INTO edu_contr_factu VALUES edu_factu_tab(i);

 END LOOP;
 CLOSE curINScontrol;

 dbms_output.put_line('rowcount ' || v_row_count || ' rows ' || rows );

 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_CARGA_CONTR_FACTU: '||ls_sqlerrm);

 END EDU_PR_CARGA_CONTR_FACTU;

/***************************************************************************
Descripcion : Procedure que Procesa Actualizacion de Envio de Aptas
Fecha Creacion : 09/07/2007
Autor : Marco Silva
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
			psCodPeriodo	:Codigo Periodo
 psNumeroLote : Numero de Lote
 psUsuario : Codigo de Usuario
***************************************************************************/

 PROCEDURE EDU_PR_ACTUA_ENVIO_APTAS
 (psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodPeriodo	 VARCHAR2,
 psNumeroLote VARCHAR2,
 psUsuario VARCHAR2
 )
 AS
 CURSOR curUPDAptas
 IS
 SELECT aptas.pais_cod_pais ,
 aptas.emco_cod_empr_come ,
 CAPAC.COD_CURS_CAPA CURSO ,
 APTAS.CLIE_COD_CLIE CLIENTE
 FROM EDU_PARAM_CURSO_CAPAC CAPAC,
 EDU_HISTO_APTAS APTAS
 WHERE aptas.pais_cod_pais = psCodigoPais and
 aptas.emco_cod_empr_come = psCodEmpresa and
 CAPAC.PAIS_COD_PAIS=APTAS.PAIS_COD_PAIS and
					 APTAS.cam_ulti_cali_apta = psCodPeriodo and
 -- Filtro por campanha activa
 exists (select null from edu_contr_factu ct where ct.pais_cod_pais = aptas.pais_cod_pais and ct.emco_cod_empr_come = aptas.emco_cod_empr_come and
 aptas.cam_ulti_cali_apta = ct.cod_peri and ct.est_camp = 1 and ct.ind_camp_acti = 1 )
 AND CAPAC.EMCO_COD_EMPR_COME=APTAS.EMCO_COD_EMPR_COME
 AND CAPAC.COD_CURS_CAPA=APTAS.CCAP_COD_CURS_CAPA
-- La Calificacion Controla el numero de invitaciones
-- AND CAPAC.NUM_INVI > APTAS.NUM_INVI
 AND aptas.ind_curs_cost = 'N'
 AND APTAS.EST_CAPA='PC'
 AND APTAS.IND_ENVI='N' ;

TYPE t_codPais IS TABLE OF EDU_HISTO_APTAS.PAIS_COD_PAIS%TYPE ;
TYPE t_codEmp IS TABLE OF EDU_HISTO_APTAS.EMCO_COD_EMPR_COME%TYPE ;
TYPE t_codCurso IS TABLE OF EDU_HISTO_APTAS.CCAP_COD_CURS_CAPA%TYPE ;
TYPE t_codCons IS TABLE OF EDU_HISTO_APTAS.CLIE_COD_CLIE%TYPE ;


 v_codPais t_codPais ;
 v_codEmp t_codEmp ;
 v_codCons t_codCons ;
 v_codCurso t_codCurso ;

 rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;
 v_row_count NUMBER := 0;

begin

 OPEN curUPDAptas;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
 FETCH curUPDAptas BULK COLLECT INTO
 v_codPais ,
 v_codEmp ,
 v_codCurso ,
 v_codCons LIMIT rows;

 EXIT WHEN v_row_count = curUPDAptas%ROWCOUNT;
 v_row_count := curUPDAptas%ROWCOUNT;

 -- Bulk bind of data in memory table...
 FORALL i IN 1..v_codPais.count
 UPDATE edu_histo_aptas apt
 SET --apt.num_invi = apt.num_invi + 1,
 --apt.ind_envi = 'S' ,
 apt.est_capa = 'PF' , -- Estado Por Facturar: SI Factura -> PP (Por Programar), NO Factura -> PC
 apt.num_lote_diar = psNumeroLote ,
 apt.fec_modi = sysdate ,
 apt.usu_modi = psUsuario
 where apt.pais_cod_pais = v_codPais(i) and
 apt.emco_cod_empr_come = v_codEmp(i) and
 apt.ccap_cod_curs_capa = v_codCurso(i) and
 apt.clie_cod_clie = v_codCons(i) ;

 END LOOP;
 CLOSE curUPDAptas;

 return ;

 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_ACTUA_ENVIO_APTAS: '||ls_sqlerrm);

 END EDU_PR_ACTUA_ENVIO_APTAS;

/***************************************************************************
Descripcion : Procedure que Procesa Actualizacion de Envio de Aptas C.Costo
Fecha Creacion : 09/07/2007
Autor : Marco Silva
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
			psCodPeriodo	:Codigo Periodo
 psNumeroLote : Numero de Lote
 psUsuario : Codigo de Usuario
***************************************************************************/

PROCEDURE EDU_PR_ACTUA_ENVIO_APTAS_COSTO
 (psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodPeriodo	 VARCHAR2,
 psNumeroLote VARCHAR2,
 psUsuario VARCHAR2
 )
 AS
 CURSOR curUPDAptas
 IS
 SELECT aptas.pais_cod_pais ,
 aptas.emco_cod_empr_come ,
 CAPAC.COD_CURS_CAPA CURSO ,
 APTAS.CLIE_COD_CLIE CLIENTE
 FROM EDU_PARAM_CURSO_CAPAC CAPAC,
 EDU_HISTO_APTAS APTAS
 WHERE aptas.pais_cod_pais = psCodigoPais and
 aptas.emco_cod_empr_come = psCodEmpresa and
 CAPAC.PAIS_COD_PAIS=APTAS.PAIS_COD_PAIS and
					 APTAS.cam_ulti_cali_apta = psCodPeriodo and
 -- Filtro por campanha activa
 exists (select null from edu_contr_factu ct where ct.pais_cod_pais = aptas.pais_cod_pais and ct.emco_cod_empr_come = aptas.emco_cod_empr_come and
 aptas.cam_ulti_cali_apta = ct.cod_peri and ct.est_camp = 1 and ct.ind_camp_acti = 1 )
 AND CAPAC.EMCO_COD_EMPR_COME=APTAS.EMCO_COD_EMPR_COME
 AND CAPAC.COD_CURS_CAPA=APTAS.CCAP_COD_CURS_CAPA
-- La Calificacion Controla el numero de invitaciones
-- AND CAPAC.NUM_INVI > APTAS.NUM_INVI
 AND aptas.ind_curs_cost = 'S'
 AND APTAS.EST_CAPA='PC'
 AND APTAS.IND_ENVI='N' ;

TYPE t_codPais IS TABLE OF EDU_HISTO_APTAS.PAIS_COD_PAIS%TYPE ;
TYPE t_codEmp IS TABLE OF EDU_HISTO_APTAS.EMCO_COD_EMPR_COME%TYPE ;
TYPE t_codCurso IS TABLE OF EDU_HISTO_APTAS.CCAP_COD_CURS_CAPA%TYPE ;
TYPE t_codCons IS TABLE OF EDU_HISTO_APTAS.CLIE_COD_CLIE%TYPE ;


 v_codPais t_codPais ;
 v_codEmp t_codEmp ;
 v_codCons t_codCons ;
 v_codCurso t_codCurso ;

 rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;
 v_row_count NUMBER := 0;

begin

 OPEN curUPDAptas;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
 FETCH curUPDAptas BULK COLLECT INTO
 v_codPais ,
 v_codEmp ,
 v_codCurso ,
 v_codCons LIMIT rows;

 EXIT WHEN v_row_count = curUPDAptas%ROWCOUNT;
 v_row_count := curUPDAptas%ROWCOUNT;

 -- Bulk bind of data in memory table...
 FORALL i IN 1..v_codPais.count
 UPDATE edu_histo_aptas apt
 SET --apt.num_invi = apt.num_invi + 1,
 --apt.ind_envi = 'S' ,
 apt.est_capa = 'FC' , -- Estado Por Facturar: SI Factura -> PO (Por Cinfirmar), NO Factura -> PC
 apt.num_lote_diar = psNumeroLote ,
 apt.fec_modi = sysdate ,
 apt.usu_modi = psUsuario
 where apt.pais_cod_pais = v_codPais(i) and
 apt.emco_cod_empr_come = v_codEmp(i) and
 apt.ccap_cod_curs_capa = v_codCurso(i) and
 apt.clie_cod_clie = v_codCons(i) ;

 END LOOP;
 CLOSE curUPDAptas;

 return ;

 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_ACTUA_ENVIO_APTAS_COSTO: '||ls_sqlerrm);

 END EDU_PR_ACTUA_ENVIO_APTAS_COSTO;

/***************************************************************************
Descripcion : Procedure que Procesa Aptas por Programar
Fecha Creacion : 09/07/2007
Autor : Marco Silva
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodigoPeriodo : Codigo Periodo
 psUsuario : Codigo de Usuario
***************************************************************************/

PROCEDURE EDU_PR_ACTUA_APTAS_POR_PROGR
 (psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psUsuario VARCHAR2
 )
 AS

 -- consultotras aptas con cursos sin costo pasan automaticamente a estado por programar
 CURSOR curUPDAptas
 IS
 SELECT aptas.pais_cod_pais ,
 aptas.emco_cod_empr_come ,
 aptas.ccap_cod_curs_capa CURSO ,
 APTAS.CLIE_COD_CLIE CLIENTE
 FROM
 EDU_HISTO_APTAS APTAS
 WHERE aptas.pais_cod_pais = psCodigoPais and
 aptas.emco_cod_empr_come = psCodEmpresa and
 APTAS.EST_CAPA='PF' and -- Por Facturar
 aptas.ind_curs_cost = 'N' and -- Curso sin costo
 aptas.cam_ulti_cali_apta = psCodigoPeriodo and
 exists (select null from edu_histo_pedid_consu ped where ped.pais_cod_pais = aptas.pais_cod_pais and
 ped.emco_cod_empr_come = aptas.emco_cod_empr_come and aptas.clie_cod_clie = ped.cod_clie and
 ped.cam_proc = aptas.cam_ulti_cali_apta and
 ped.ind_fact = 1 ) ;


	 -- consultotras aptas con cursos sin costo pasan automaticamente a estado por programar
 CURSOR curUPDAptasSinPedido
 IS
 SELECT aptas.pais_cod_pais ,
 aptas.emco_cod_empr_come ,
 aptas.ccap_cod_curs_capa CURSO ,
 APTAS.CLIE_COD_CLIE CLIENTE
 FROM
 EDU_HISTO_APTAS APTAS
 WHERE aptas.pais_cod_pais = psCodigoPais and
 aptas.emco_cod_empr_come = psCodEmpresa and
 APTAS.EST_CAPA='PF' and -- Por Facturar
 aptas.ind_curs_cost = 'N' and -- Curso sin costo
 aptas.cam_ulti_cali_apta = psCodigoPeriodo and
 exists ( SELECT null
 			 		 FROM EDU_HISTO_PEDID_CONSU X
 					 WHERE X.PAIS_COD_PAIS = psCodigoPais
 				 AND X.EMCO_COD_EMPR_COME = psCodEmpresa
 				 AND TO_NUMBER(X.COD_ULTI_NIVE) +1 >= NVL((SELECT Z.NUM_CAMP_EVAL
			 				 	 							 FROM EDU_PARAM_CURSO_CAPAC Z
			 												 WHERE Z.PAIS_COD_PAIS = psCodigoPais
			 												 AND Z.EMCO_COD_EMPR_COME =psCodEmpresa
			 													AND Z.COD_CURS_CAPA=aptas.ccap_cod_curs_capa
			 													AND Z.SEPE_COD_SECU_PEDI='04'),0)
 				 AND X.EST_REGI = '1' )
					 AND NVL((SELECT Z.SEPE_COD_SECU_PEDI
			 				 	 							 FROM EDU_PARAM_CURSO_CAPAC Z
			 												 WHERE Z.PAIS_COD_PAIS = psCodigoPais
			 												 AND Z.EMCO_COD_EMPR_COME =psCodEmpresa
			 					 AND Z.COD_CURS_CAPA=aptas.ccap_cod_curs_capa),'') ='04';


TYPE t_codPais IS TABLE OF EDU_HISTO_APTAS.PAIS_COD_PAIS%TYPE ;
TYPE t_codEmp IS TABLE OF EDU_HISTO_APTAS.EMCO_COD_EMPR_COME%TYPE ;
TYPE t_codCurso IS TABLE OF EDU_HISTO_APTAS.CCAP_COD_CURS_CAPA%TYPE ;
TYPE t_codCons IS TABLE OF EDU_HISTO_APTAS.CLIE_COD_CLIE%TYPE ;


 v_codPais t_codPais ;
 v_codEmp t_codEmp ;
 v_codCons t_codCons ;
 v_codCurso t_codCurso ;

 rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;
 v_row_count NUMBER := 0;

begin

 OPEN curUPDAptas;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
 FETCH curUPDAptas BULK COLLECT INTO
 v_codPais ,
 v_codEmp ,
 v_codCurso ,
 v_codCons LIMIT rows;

 EXIT WHEN v_row_count = curUPDAptas%ROWCOUNT;
 v_row_count := curUPDAptas%ROWCOUNT;

 -- Bulk bind of data in memory table...
 FORALL i IN 1..v_codPais.count
 UPDATE edu_histo_aptas apt
 SET --apt.num_invi = apt.num_invi + 1,
 apt.ind_envi = 'S' ,
 apt.est_capa = 'PP' , -- Estado Por Programar
-- apt.num_lote_diar = psNumeroLote ,
 apt.ind_posi_egre = 'N',
 apt.fec_modi = sysdate ,
 apt.usu_modi = psUsuario
 where apt.pais_cod_pais = v_codPais(i) and
 apt.emco_cod_empr_come = v_codEmp(i) and
 apt.ccap_cod_curs_capa = v_codCurso(i) and
 apt.clie_cod_clie = v_codCons(i) ;

 END LOOP;
 CLOSE curUPDAptas;


 --SE RECCOREE LAS DE SECUENCIA 04
 v_row_count:=0;
 OPEN curUPDAptasSinPedido;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
 FETCH curUPDAptasSinPedido BULK COLLECT INTO
 v_codPais ,
 v_codEmp ,
 v_codCurso ,
 v_codCons LIMIT rows;

 EXIT WHEN v_row_count = curUPDAptasSinPedido%ROWCOUNT;
 v_row_count := curUPDAptasSinPedido%ROWCOUNT;

 -- Bulk bind of data in memory table...
 FORALL i IN 1..v_codPais.count
 UPDATE edu_histo_aptas apt
 SET --apt.num_invi = apt.num_invi + 1,
 apt.ind_envi = 'S' ,
 apt.est_capa = 'PP' , -- Estado Por Programar
-- apt.num_lote_diar = psNumeroLote ,
 apt.ind_posi_egre = 'N',
 apt.fec_modi = sysdate ,
 apt.usu_modi = psUsuario
 where apt.pais_cod_pais = v_codPais(i) and
 apt.emco_cod_empr_come = v_codEmp(i) and
 apt.ccap_cod_curs_capa = v_codCurso(i) and
 apt.clie_cod_clie = v_codCons(i) ;

 END LOOP;
 CLOSE curUPDAptasSinPedido;

 -- Se actualizan num.invitacion a 0 de las q no facturaron y su num.invitacion sea 1
 update edu_histo_aptas aptNoFact
 set aptNoFact.Num_Invi = 0
 where aptNoFact.Pais_Cod_Pais = psCodigoPais and
 aptNoFact.Emco_Cod_Empr_Come = psCodEmpresa and
 aptNoFact.Cam_Ulti_Cali_Apta = psCodigoPeriodo and
 aptNoFact.Est_Capa = 'PF' and
 aptNoFact.Num_Invi = 1 ;

 -- Se actualizan las q no facturaron
 -- Y aquellas que no son posible egreso
 update edu_histo_aptas aptNoFact
 set aptNoFact.Est_Capa = 'PC' ,
 aptNoFact.Num_Lote_Diar = null ,
 aptNoFact.fec_modi = sysdate ,
 aptNoFact.usu_modi = psUsuario
 where aptNoFact.Pais_Cod_Pais = psCodigoPais and
 aptNoFact.Emco_Cod_Empr_Come = psCodEmpresa and
 aptNoFact.Cam_Ulti_Cali_Apta = psCodigoPeriodo and
 aptNoFact.Est_Capa = 'PF' and
		aptNoFact.IND_POSI_EGRE = 'N';

-- Se actualizan las que no factuarron y son posible egreso ;

 update edu_histo_aptas aptNoFact
 set aptNoFact.Est_Capa = 'PP' ,
 	 aptNoFact.IND_ENVI='S',
 aptNoFact.fec_modi = sysdate ,
 aptNoFact.usu_modi = psUsuario
 where aptNoFact.Pais_Cod_Pais = psCodigoPais and
 aptNoFact.Emco_Cod_Empr_Come = psCodEmpresa and
 aptNoFact.Cam_Ulti_Cali_Apta = psCodigoPeriodo and
 aptNoFact.Est_Capa = 'PF' and
		aptNoFact.IND_POSI_EGRE = 'S' and
		aptNoFact.Num_Invi <> 0;

-- Se actualizan las que no factuarron y son posible egreso E INVITACION A CERO;

 update edu_histo_aptas aptNoFact
 set aptNoFact.Est_Capa = 'PP' ,
 	 aptNoFact.IND_ENVI='S',
	 aptNoFact.Num_Invi = 1,
 aptNoFact.fec_modi = sysdate ,
 aptNoFact.usu_modi = psUsuario
 where aptNoFact.Pais_Cod_Pais = psCodigoPais and
 aptNoFact.Emco_Cod_Empr_Come = psCodEmpresa and
 aptNoFact.Cam_Ulti_Cali_Apta = psCodigoPeriodo and
 aptNoFact.Est_Capa = 'PF' and
		aptNoFact.IND_POSI_EGRE = 'S' and
		aptNoFact.Num_Invi = 0;

--actulaizamos el num invitacion a cero en historico de calificaciones para aquellas que no han facturado del aptas
 update edu_histo_calif_aptas a
 set a.Num_Invi = 0,
 	 a.fec_modi = sysdate ,
 a.usu_modi = psUsuario
 where a.Pais_Cod_Pais = psCodigoPais and
 a.Emco_Cod_Empr_Come = psCodEmpresa and
 a.Cam_Ulti_Cali_Apta = psCodigoPeriodo and
		EXISTS (select aptNoFact.CLIE_COD_CLIE
			 from edu_histo_aptas aptNoFact
 				 where aptNoFact.Pais_Cod_Pais = psCodigoPais and
				 aptNoFact.Emco_Cod_Empr_Come = psCodEmpresa and
				 aptNoFact.Cam_Ulti_Cali_Apta = psCodigoPeriodo and
						a.CLIE_COD_CLIE = aptNoFact.CLIE_COD_CLIE and
						a.CCAP_COD_CURS_CAPA = aptNoFact.CCAP_COD_CURS_CAPA and
						aptNoFact.Est_Capa = 'PC' and
				 aptNoFact.Num_Invi = 0 );

--Actualizamos la campanha de ingreso de la consultora a quellas que estan ingresando a Educacion
 update edu_maest_clien nv
 set nv.Cam_Ingr = psCodigoPeriodo
 where nv.Pais_Cod_Pais = psCodigoPais and
 nv.Emco_Cod_Empr_Come = psCodEmpresa and
 exists (select null from edu_histo_pedid_consu pd where pd.pais_cod_pais = nv.pais_cod_pais and
 pd.emco_cod_empr_come = nv.emco_cod_empr_come and
 pd.cod_clie = nv.cod_clie and
 to_number(pd.cod_ulti_nive) = 1 and
 pd.cam_ulti_pedi = psCodigoPeriodo and
 pd.ind_fact = '1' );

-- Actualizamos la campanha de ingreso comercial de la consultora, aquellas que vengan con null y sean nuevas
-- Las que piden CUV se quedaria con el ingreso comercial que viene
 update edu_maest_clien nv
 set nv.CAM_INGR_COME = psCodigoPeriodo
 where nv.Pais_Cod_Pais = psCodigoPais and
 nv.Emco_Cod_Empr_Come = psCodEmpresa and
 exists (select null from edu_histo_pedid_consu pd where pd.pais_cod_pais = nv.pais_cod_pais and
 pd.emco_cod_empr_come = nv.emco_cod_empr_come and
 pd.cod_clie = nv.cod_clie and
 to_number(pd.cod_ulti_nive) = 1 and
 pd.cam_ulti_pedi = psCodigoPeriodo and
 pd.ind_fact = '1' )
		and nv.CAM_INGR_COME IS NULL OR	nv.CAM_INGR_COME='';
 return ;

 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_ACTUA_APTAS_POR_PROGR: '||ls_sqlerrm);

 END EDU_PR_ACTUA_APTAS_POR_PROGR;

/***************************************************************************
Descripcion : Procedure que Procesa Facturar Cursos Costo a Por Prog
Fecha Creacion : 09/07/2007
Autor : Marco Silva
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodigoPeriodo : Codigo Periodo
 psUsuario : Codigo de Usuario
***************************************************************************/

PROCEDURE EDU_PR_ACTUA_COMPR_CURSO
 (psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psUsuario VARCHAR2
 )
 AS

 -- consultotras aptas con cursos sin costo pasan automaticamente a estado por programar
 CURSOR curUPDAptas
 IS
 SELECT curs.cod_pais ,
 curs.cod_empr_come ,
 curs.cod_curs_capa ,
 curs.cod_clie ,
 curs.cam_acep
 FROM edu_tmp_confi_curso_costo curs
 WHERE curs.cod_pais = psCodigoPais and
 curs.cod_empr_come = psCodEmpresa and
 curs.cam_acep = psCodigoPeriodo and
 curs.ind_comp = 1 and
-- Campanah Anterior fue caliifcada como apta
-- aptas.cam_ulti_cali_apta = psCodigoPeriodo and
 exists (select null from edu_histo_aptas aptas where curs.cod_pais = aptas.pais_cod_pais and
 curs.cod_empr_come = aptas.emco_cod_empr_come and
 aptas.clie_cod_clie = curs.cod_clie and
 aptas.ccap_cod_curs_capa = curs.cod_curs_capa and
 APTAS.EST_CAPA='PO' and -- Por Confirmar Curso Costo
 aptas.ind_curs_cost = 'S' -- Curso con costo
 ) ;




TYPE t_codPais IS TABLE OF EDU_HISTO_APTAS.PAIS_COD_PAIS%TYPE ;
TYPE t_codEmp IS TABLE OF EDU_HISTO_APTAS.EMCO_COD_EMPR_COME%TYPE ;
TYPE t_codCurso IS TABLE OF EDU_HISTO_APTAS.CCAP_COD_CURS_CAPA%TYPE ;
TYPE t_codCons IS TABLE OF EDU_HISTO_APTAS.CLIE_COD_CLIE%TYPE ;
TYPE t_campAcep IS TABLE OF EDU_HISTO_APTAS.CAM_ACEP%TYPE ;


 v_codPais t_codPais ;
 v_codEmp t_codEmp ;
 v_codCons t_codCons ;
 v_codCurso t_codCurso ;
 v_campAcep t_campAcep ;

 rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;
 v_row_count NUMBER := 0;

begin

 OPEN curUPDAptas;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
 FETCH curUPDAptas BULK COLLECT INTO
 v_codPais ,
 v_codEmp ,
 v_codCurso ,
 v_codCons ,
 v_campAcep LIMIT rows;

 EXIT WHEN v_row_count = curUPDAptas%ROWCOUNT;
 v_row_count := curUPDAptas%ROWCOUNT;

 -- Bulk bind of data in memory table...
 FORALL i IN 1..v_codPais.count
 UPDATE edu_histo_aptas apt
 SET
 apt.est_capa = 'PP' , -- Estado Por Programar
 apt.cam_acep = v_campAcep(i) ,
 apt.cam_fact_curs = v_campAcep(i) ,
 apt.ind_curs_fact = 'F' ,
 apt.ind_envi = 'S',
 apt.fec_modi = sysdate ,
 apt.usu_modi = psUsuario
 where apt.pais_cod_pais = v_codPais(i) and
 apt.emco_cod_empr_come = v_codEmp(i) and
 apt.ccap_cod_curs_capa = v_codCurso(i) and
 apt.clie_cod_clie = v_codCons(i) ;


 FORALL j IN 1..v_codPais.count
 UPDATE edu_histo_calif_aptas apt
 SET apt.cam_acep = v_campAcep(j) ,
 apt.cam_fact_curs = v_campAcep(j),
 apt.ind_curs_fact = 'F' ,
 apt.fec_modi = sysdate ,
 apt.usu_modi = psUsuario
 where apt.pais_cod_pais = v_codPais(j) and
 apt.emco_cod_empr_come = v_codEmp(j) and
 apt.ccap_cod_curs_capa = v_codCurso(j) and
 apt.clie_cod_clie = v_codCons(j) and
 				 apt.CAM_PROC = v_campAcep(j);

 END LOOP;
 CLOSE curUPDAptas;

 -- Proceso que se encargara d eactualizar aquella consultoras cuyo previa campaña de calificacion se a 1
 EDU_PR_ACTUA_CONSU_PREV_CAMPA(psCodigoPais,psCodEmpresa,psCodigoPeriodo,psUsuario);

 return ;

 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_ACTUA_COMPR_CURSO: '||ls_sqlerrm);

 END EDU_PR_ACTUA_COMPR_CURSO;

/***************************************************************************
Descripcion : Procedure que Procesa Cursos con Costo No comprados
Fecha Creacion : 09/07/2007
Autor : Marco Silva
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodigoPeriodo : Codigo Periodo
 psUsuario : Codigo de Usuario
***************************************************************************/

PROCEDURE EDU_PR_ACTUA_NO_COMPR_CURSO
 (psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psUsuario VARCHAR2
 )
 AS

 -- consultotras aptas con cursos sin costo pasan automaticamente a estado por programar
 CURSOR curUPDAptas
 IS
 SELECT curs.cod_pais ,
 curs.cod_empr_come ,
 curs.cod_curs_capa ,
 curs.cod_clie ,
 curs.cam_acep
 FROM edu_tmp_confi_curso_costo curs
 WHERE curs.cod_pais = psCodigoPais and
 curs.cod_empr_come = NVL(psCodEmpresa,(select COD_EMPR_COME from EDU_EMPRE_COMER where PAIS_COD_PAIS = psCodigoPais)) and
 curs.cam_acep = psCodigoPeriodo and
 curs.ind_comp = 0 and
-- Campanah Anterior fue caliifcada como apta
-- aptas.cam_ulti_cali_apta = psCodigoPeriodo and
 exists (select null from edu_histo_aptas aptas where curs.cod_pais = aptas.pais_cod_pais and
 curs.cod_empr_come = aptas.emco_cod_empr_come and
 aptas.clie_cod_clie = curs.cod_clie and
 aptas.ccap_cod_curs_capa = curs.cod_curs_capa and
 APTAS.EST_CAPA='PO' and -- Por Confirmar Curso Costo
 aptas.ind_curs_cost = 'S' -- Curso con costo
 ) ;


 --Cursosr que obtien las que no han facturado y su num invitacion debe ser cero, si es qes uno el num_invi

	 CURSOR curAptasNoFactu
 IS
 SELECT aptas.pais_cod_pais ,
 aptas.emco_cod_empr_come ,
 aptas.ccap_cod_curs_capa CURSO ,
 APTAS.CLIE_COD_CLIE CLIENTE
 FROM
 EDU_HISTO_APTAS APTAS
 WHERE aptas.pais_cod_pais = psCodigoPais and
 aptas.emco_cod_empr_come = NVL(psCodEmpresa,(select COD_EMPR_COME from EDU_EMPRE_COMER where PAIS_COD_PAIS = psCodigoPais)) and
 APTAS.EST_CAPA='PO' and -- Por Facturar Curso Costo
 aptas.ind_curs_cost = 'S' and -- Curso con costo
 aptas.cam_ulti_cali_apta = psCodigoPeriodo and
 exists (select null from edu_histo_pedid_consu ped where ped.pais_cod_pais = aptas.pais_cod_pais and
 ped.emco_cod_empr_come = aptas.emco_cod_empr_come and aptas.clie_cod_clie = ped.cod_clie and
 ped.cam_proc = aptas.cam_ulti_cali_apta and
 ped.ind_fact = 0 ) ;

TYPE t_codPais IS TABLE OF EDU_HISTO_APTAS.PAIS_COD_PAIS%TYPE ;
TYPE t_codEmp IS TABLE OF EDU_HISTO_APTAS.EMCO_COD_EMPR_COME%TYPE ;
TYPE t_codCurso IS TABLE OF EDU_HISTO_APTAS.CCAP_COD_CURS_CAPA%TYPE ;
TYPE t_codCons IS TABLE OF EDU_HISTO_APTAS.CLIE_COD_CLIE%TYPE ;
TYPE t_campAcep IS TABLE OF EDU_HISTO_APTAS.CAM_ACEP%TYPE ;


 v_codPais t_codPais ;
 v_codEmp t_codEmp ;
 v_codCons t_codCons ;
 v_codCurso t_codCurso ;
 v_campAcep t_campAcep ;

 rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;
 v_row_count NUMBER := 0;
 vsCodEmpresa EDU_EMPRE_COMER.COD_EMPR_COME%TYPE;
begin

 vsCodEmpresa := psCodEmpresa;

 if psCodEmpresa is null then
    select COD_EMPR_COME
    into vsCodEmpresa
    from EDU_EMPRE_COMER
    where PAIS_COD_PAIS = psCodigoPais;
 end if;

 OPEN curAptasNoFactu;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
 FETCH curAptasNoFactu BULK COLLECT INTO
 v_codPais ,
 v_codEmp ,
 v_codCurso ,
 v_codCons LIMIT rows;

 EXIT WHEN v_row_count = curAptasNoFactu%ROWCOUNT;
 v_row_count := curAptasNoFactu%ROWCOUNT;

 -- Bulk bind of data in memory table...
 FORALL i IN 1..v_codPais.count
 UPDATE edu_histo_aptas apt
 SET apt.NUM_INVI = 0,
 apt.ind_curs_fact = 'N'
 where apt.pais_cod_pais = v_codPais(i) and
 apt.emco_cod_empr_come = v_codEmp(i) and
 apt.ccap_cod_curs_capa = v_codCurso(i) and
 apt.clie_cod_clie = v_codCons(i) and
				 apt.CAM_ULTI_CALI_APTA = psCodigoPeriodo and
				 apt.NUM_INVI = 1;

	 --SE ACTUALIZA TB LA CALI
	 FORALL i IN 1..v_codPais.count
 UPDATE edu_histo_calif_aptas apt
 SET apt.NUM_INVI = 0,
 apt.ind_curs_fact = 'N'
 where apt.pais_cod_pais = v_codPais(i) and
 apt.emco_cod_empr_come = v_codEmp(i) and
 apt.ccap_cod_curs_capa = v_codCurso(i) and
 apt.clie_cod_clie = v_codCons(i) and
				 apt.CAM_PROC = psCodigoPeriodo and
				 apt.NUM_INVI = 1;

 END LOOP;
 CLOSE curAptasNoFactu;

 --INICILAIZAMOS VARIABLE
 v_row_count :=0;
 OPEN curUPDAptas;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
 FETCH curUPDAptas BULK COLLECT INTO
 v_codPais ,
 v_codEmp ,
 v_codCurso ,
 v_codCons ,
 v_campAcep LIMIT rows;

 EXIT WHEN v_row_count = curUPDAptas%ROWCOUNT;
 v_row_count := curUPDAptas%ROWCOUNT;

 -- Bulk bind of data in memory table...
 FORALL i IN 1..v_codPais.count
 UPDATE edu_histo_aptas apt
 SET
 apt.est_capa = 'PC' , -- Estado Por Capacitar
 apt.ind_curs_fact = 'N' ,
				 apt.ind_envi = 'S' ,
 apt.fec_modi = sysdate ,
 apt.usu_modi = psUsuario
 where apt.pais_cod_pais = v_codPais(i) and
 apt.emco_cod_empr_come = v_codEmp(i) and
 apt.ccap_cod_curs_capa = v_codCurso(i) and
 apt.clie_cod_clie = v_codCons(i) ;

 END LOOP;
 CLOSE curUPDAptas;

 -- Proceso que se encargara d eactualizar aquella consultoras cuyo previa campaña de calificacion se a 1
 EDU_PR_ACTUA_CONSU_PREV_CAMPA(psCodigoPais,vsCodEmpresa,psCodigoPeriodo,psUsuario);
 return ;

 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_ACTUA_NO_COMPR_CURSO: '||ls_sqlerrm);

 END EDU_PR_ACTUA_NO_COMPR_CURSO;

/***************************************************************************
Descripcion : Procedure que Procesa Aptas Costo por Confirmar
Fecha Creacion : 09/07/2007
Autor : Marco Silva
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodigoPeriodo : Codigo Periodo
 psUsuario : Codigo de Usuario
***************************************************************************/

PROCEDURE EDU_PR_ACTUA_APTAS_POR_COMFI
 (psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psUsuario VARCHAR2
 )
 AS

 -- consultotras aptas con cursos sin costo pasan automaticamente a estado por programar
 CURSOR curUPDAptas
 IS
 SELECT aptas.pais_cod_pais ,
 aptas.emco_cod_empr_come ,
 aptas.ccap_cod_curs_capa CURSO ,
 APTAS.CLIE_COD_CLIE CLIENTE
 FROM
 EDU_HISTO_APTAS APTAS
 WHERE aptas.pais_cod_pais = psCodigoPais and
 aptas.emco_cod_empr_come = psCodEmpresa and
 APTAS.EST_CAPA='FC' and -- Por Facturar Curso Costo
 aptas.ind_curs_cost = 'S' and -- Curso con costo
 aptas.cam_ulti_cali_apta = psCodigoPeriodo and
 exists (select null from edu_histo_pedid_consu ped where ped.pais_cod_pais = aptas.pais_cod_pais and
 ped.emco_cod_empr_come = aptas.emco_cod_empr_come and aptas.clie_cod_clie = ped.cod_clie and
 ped.cam_proc = aptas.cam_ulti_cali_apta and
 ped.ind_fact = 1 ) ;

TYPE t_codPais IS TABLE OF EDU_HISTO_APTAS.PAIS_COD_PAIS%TYPE ;
TYPE t_codEmp IS TABLE OF EDU_HISTO_APTAS.EMCO_COD_EMPR_COME%TYPE ;
TYPE t_codCurso IS TABLE OF EDU_HISTO_APTAS.CCAP_COD_CURS_CAPA%TYPE ;
TYPE t_codCons IS TABLE OF EDU_HISTO_APTAS.CLIE_COD_CLIE%TYPE ;


 v_codPais t_codPais ;
 v_codEmp t_codEmp ;
 v_codCons t_codCons ;
 v_codCurso t_codCurso ;

 rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;
 v_row_count NUMBER := 0;

begin

 OPEN curUPDAptas;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
 FETCH curUPDAptas BULK COLLECT INTO
 v_codPais ,
 v_codEmp ,
 v_codCurso ,
 v_codCons LIMIT rows;

 EXIT WHEN v_row_count = curUPDAptas%ROWCOUNT;
 v_row_count := curUPDAptas%ROWCOUNT;

 -- Bulk bind of data in memory table...
 FORALL i IN 1..v_codPais.count
 UPDATE edu_histo_aptas apt
 SET --apt.num_invi = apt.num_invi + 1,
 apt.ind_envi = 'S' ,
 apt.est_capa = 'PO' , -- Estado Por Confirmar
-- apt.num_lote_diar = psNumeroLote ,
 apt.fec_modi = sysdate ,
 apt.usu_modi = psUsuario
 where apt.pais_cod_pais = v_codPais(i) and
 apt.emco_cod_empr_come = v_codEmp(i) and
 apt.ccap_cod_curs_capa = v_codCurso(i) and
 apt.clie_cod_clie = v_codCons(i) ;

 END LOOP;
 CLOSE curUPDAptas;

 -- Se actualizan num.invitacion a 0 de las q no facturaron y su num.invitacion sea 1
 update edu_histo_aptas aptNoFact
 set aptNoFact.Num_Invi = 0
 where aptNoFact.Pais_Cod_Pais = psCodigoPais and
 aptNoFact.Emco_Cod_Empr_Come = psCodEmpresa and
 aptNoFact.Cam_Ulti_Cali_Apta = psCodigoPeriodo and
 aptNoFact.Est_Capa = 'FC' and
 aptNoFact.Num_Invi = 1 ;


 -- Se actualizan las q no facturaron ,modif : no comparon curso previo 1
 update edu_histo_aptas aptNoFact
 set aptNoFact.Est_Capa = 'PC' ,
 	 aptNoFact.ind_curs_fact = 'N' ,
 aptNoFact.Num_Lote_Diar = null ,
 aptNoFact.fec_modi = sysdate ,
 aptNoFact.usu_modi = psUsuario
 where aptNoFact.Pais_Cod_Pais = psCodigoPais and
 aptNoFact.Emco_Cod_Empr_Come = psCodEmpresa and
 aptNoFact.Cam_Ulti_Cali_Apta = psCodigoPeriodo and
 aptNoFact.Est_Capa = 'FC' ;


--actulaizamos el num invitacion a cero en historico de calificaciones para aquellas que no han facturado del aptas
 update edu_histo_calif_aptas a
 set a.Num_Invi = 0,
 	 a.fec_modi = sysdate,
 a.usu_modi = psUsuario
 where a.Pais_Cod_Pais = psCodigoPais and
 a.Emco_Cod_Empr_Come = psCodEmpresa and
 a.Cam_Ulti_Cali_Apta = psCodigoPeriodo and
		EXISTS (select aptNoFact.CLIE_COD_CLIE
			 from edu_histo_aptas aptNoFact
 where aptNoFact.Pais_Cod_Pais = psCodigoPais and
 aptNoFact.Emco_Cod_Empr_Come = psCodEmpresa and
 aptNoFact.Cam_Ulti_Cali_Apta = psCodigoPeriodo and
						a.CLIE_COD_CLIE = aptNoFact.CLIE_COD_CLIE and
						a.CCAP_COD_CURS_CAPA = aptNoFact.CCAP_COD_CURS_CAPA and
						aptNoFact.Est_Capa = 'PC' and
				 aptNoFact.Num_Invi = 0 );

 return ;

 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_ACTUA_APTAS_POR_COMFI: '||ls_sqlerrm);

 END EDU_PR_ACTUA_APTAS_POR_COMFI;

/***************************************************************************
Descripcion : Procedure que Procesa Actutlzacion de Aptas por Programar
Fecha Creacion : 09/07/2007
Autor : Marco Silva
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodigoPeriodo : Codigo Periodo
 psNumeroLote : Numero de Lote
 psUsuario : Codigo de Usuario
***************************************************************************/

 PROCEDURE EDU_PR_ACTUA_ENVIO_APTAS_PROGR
 (psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodPeriodo VARCHAR2,
 psNumeroLote VARCHAR2,
 psUsuario VARCHAR2
 )
 AS
 CURSOR curUPDAptas
 IS
 SELECT aptas.pais_cod_pais ,
 aptas.emco_cod_empr_come ,
 aptas.ccap_cod_curs_capa ,
 APTAS.CLIE_COD_CLIE CLIENTE
 FROM EDU_HISTO_APTAS APTAS
 WHERE
 exists (select null from edu_plani_progr_curso cur where
 cur.pais_cod_pais = psCodigoPais and
 cur.emco_cod_empr_come = psCodEmpresa and
 cur.pais_cod_pais = aptas.pais_cod_pais and
 cur.emco_cod_empr_come = aptas.emco_cod_empr_come and
 cur.ccap_cod_curs_capa = aptas.ccap_cod_curs_capa and
 cur.clie_cod_clie = aptas.clie_cod_clie and
 cur.cam_proc = psCodPeriodo and
 cur.est_regi = 'P' ) ; -- estado intermedio 'P' por programar

TYPE t_codPais IS TABLE OF EDU_HISTO_APTAS.PAIS_COD_PAIS%TYPE ;
TYPE t_codEmp IS TABLE OF EDU_HISTO_APTAS.EMCO_COD_EMPR_COME%TYPE ;
TYPE t_codCurso IS TABLE OF EDU_HISTO_APTAS.CCAP_COD_CURS_CAPA%TYPE ;
TYPE t_codCons IS TABLE OF EDU_HISTO_APTAS.CLIE_COD_CLIE%TYPE ;


 v_codPais t_codPais ;
 v_codEmp t_codEmp ;
 v_codCons t_codCons ;
 v_codCurso t_codCurso ;

 rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;
 v_row_count NUMBER := 0;

begin

 OPEN curUPDAptas;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
 FETCH curUPDAptas BULK COLLECT INTO
 v_codPais ,
 v_codEmp ,
 v_codCurso ,
 v_codCons LIMIT rows;

 EXIT WHEN v_row_count = curUPDAptas%ROWCOUNT;
 v_row_count := curUPDAptas%ROWCOUNT;

 -- Bulk bind of data in memory table...
 FORALL i IN 1..v_codPais.count
 UPDATE edu_histo_aptas apt
 SET
 apt.IND_ENVI_PROG= 'S' ,
 apt.num_lote_regi = psNumeroLote ,
 apt.fec_modi = sysdate ,
 apt.usu_modi = psUsuario
 where apt.pais_cod_pais = v_codPais(i) and
 apt.emco_cod_empr_come = v_codEmp(i) and
 apt.ccap_cod_curs_capa = v_codCurso(i) and
 apt.clie_cod_clie = v_codCons(i) ;

 END LOOP;
 CLOSE curUPDAptas;

 update edu_plani_progr_curso pro set pro.est_regi = '1' -- reset estado inicial
 where pro.pais_cod_pais = psCodigoPais and
 pro.emco_cod_empr_come = psCodEmpresa and
 pro.est_regi = 'P' ;

 return ;

 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_ACTUA_ENVIO_APTAS_PROGR: '||ls_sqlerrm);

 END EDU_PR_ACTUA_ENVIO_APTAS_PROGR;

/***************************************************************************
Descripcion : Funcion que Devuelve el Sgte Numero de Lote en base al pais
Fecha Creacion : 09/07/2007
Autor : Marco Silva
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psTipoLote : Tipo de Lote
 psUsuario : Codigo de Usuario
***************************************************************************/
 PROCEDURE EDU_PR_ACTUA_NUMER_LOTE_SGNTE(psCodigoPais VARCHAR2, psEmpresa VARCHAR2 ,psTipoLote VARCHAR2, psCodUsuario VARCHAR2)
 AS
 p_NUMERO number(10);
 p_NUMEROFINAL VARCHAR2(10);
 BEGIN

IF psTipoLote='D' THEN
BEGIN
 select nvl(max(c.num_lote_diar),0) into p_NUMERO
 from edu_param_progr_capac c
 where c.pais_cod_pais = psCodigoPais and
 c.emco_cod_empr_come = psEmpresa ;
 select SUBSTR(TO_CHAR((p_NUMERO) + 100000001), 2)
 into p_NUMEROFINAL
 FROM dual;

 update edu_param_progr_capac ctr
 set ctr.num_lote_diar = p_NUMEROFINAL ,
 ctr.usu_modi = psCodUsuario ,
 ctr.fec_modi = sysdate
 where ctr.pais_cod_pais = psCodigoPais and
 ctr.emco_cod_empr_come = psEmpresa ;
END;
ELSE
BEGIN
 select nvl(max(c.num_lote_regi),0) into p_NUMERO
 from edu_param_progr_capac c
 where c.pais_cod_pais = psCodigoPais and
 c.emco_cod_empr_come = psEmpresa ;
 select SUBSTR(TO_CHAR((p_NUMERO) + 100000001), 2)
 into p_NUMEROFINAL
 FROM dual;

 update edu_param_progr_capac ctr
 set ctr.num_lote_regi = p_NUMEROFINAL ,
 ctr.usu_modi = psCodUsuario ,
 ctr.fec_modi = sysdate
 where ctr.pais_cod_pais = psCodigoPais and
 ctr.emco_cod_empr_come = psEmpresa ;
 END;
 END IF;


 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_ACTUA_NUMER_LOTE_SGNTE: '||ls_sqlerrm);
 END EDU_PR_ACTUA_NUMER_LOTE_SGNTE;


/***************************************************************************
Descripcion : Funcion que Devuelve El Parametro Numero de Lote de Educación.
Fecha Creacion : 09/07/2007
Autor : Marco Silva
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psTipoLote : Tipo de Lote
***************************************************************************/
 FUNCTION GEN_FN_NUMER_LOTE_SGNTE(psCodigoPais VARCHAR2, psEmpresa VARCHAR2 ,psTipoLote VARCHAR2)
 RETURN VARCHAR2 IS
 ls_numeroLote VARCHAR2(12);
 BEGIN

IF psTipoLote='D' THEN

 select nvl(max(c.num_lote_diar),0) into ls_numeroLote
 from edu_param_progr_capac c
 where c.pais_cod_pais = psCodigoPais and
 c.emco_cod_empr_come = psEmpresa ;
ELSE

 select nvl(max(c.num_lote_regi),0) into ls_numeroLote
 from edu_param_progr_capac c
 where c.pais_cod_pais = psCodigoPais and
 c.emco_cod_empr_come = psEmpresa ;
END IF;

 RETURN ls_numeroLote;
 END GEN_FN_NUMER_LOTE_SGNTE;

/***************************************************************************
Descripcion : Funcion que Devuelve la siguiente campanha
Fecha Creacion : 09/07/2007
Autor : Marco Silva
Parametros :
 psCodigoPeriodo : Codigo de Periodo
 numCampanhas : Numero de Campanhas
***************************************************************************/

 FUNCTION GEN_FN_DEVUE_NSGTE_CAMPA(psCodPeriodo VARCHAR2, numCampanhas number ) RETURN varchar2
 IS
 ls_result varchar2(100);
 ls_temp varchar2(100);
 campanha number;
 anho number;
 total number;
 numCampanhasNumber number ;
 BEGIN
 numCampanhasNumber := numCampanhas ;
 SELECT psCodPeriodo INTO ls_temp FROM dual ;

 IF ls_temp is null THEN
 RETURN null;
 ELSE
 SELECT to_number(SUBSTR(ls_temp, 1, 4)) into anho
 FROM dual;

 SELECT to_number(SUBSTR(ls_temp, 5, 2))+ numCampanhasNumber into total
 FROM dual;

 SELECT mod(to_number(SUBSTR(ls_temp, 5, 2))+ numCampanhasNumber ,18) into campanha
 FROM dual;

 if total > 18 then
 anho:= anho + 1 ;
 end if;
 if total < 1 then
 anho:= anho - 1 ;
 end if;


 if campanha = 0 then
 campanha:= 18;
 end if;
 if campanha < 0 then
 campanha := 18 + campanha ;
 end if;

 select trim(to_char(anho,'0000')) || trim(to_char(campanha,'00')) into ls_result from dual ;

 RETURN ls_result;
 END IF;

 END GEN_FN_DEVUE_NSGTE_CAMPA;

/***************************************************************************
Descripcion : Procedure que Procesa Actutlzacion Beneficios a Clasificadas
Fecha Creacion : 09/07/2007
Autor : Marco Silva
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodigoPeriodo : Codigo Periodo
 psUsuario : Codigo de Usuario
***************************************************************************/
PROCEDURE EDU_PR_ACTUA_BENEF_CLASI
 (psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psUsuario VARCHAR2
 )
 AS
 CURSOR curClasif
 IS
 select
 dp.PAIS_COD_PAIS ,
 dp.EMCO_COD_EMPR_COME ,
 dp.COD_CLAS ,
 dp.NIV_CAPA_ALCA ,
 dp.NOM_CLAS ,
 dp.IND_SELE_CAPA ,
 dp.NUM_CAMP_TOMA_CURS ,
 dp.ERNI_COD_ESTA_RESP_NIVE ,
 dp.ASTT_COD_TIPO_ASTT_CURS ,
 dp.EST_CLAS ,
 dp.IND_CAMP_TODO,
		 dp.IND_CAMP_ANTE,
		 dp.IND_DESP_CLAS,
		 dp.NUM_CAMP_MAXI_DESP,
 dp.USU_DIGI ,
 dp.FEC_DIGI ,
 dp.USU_MODI ,
 dp.FEC_MODI ,
 dp.EST_REGI
 from edu_param_clasi_benef_capac dp
 where dp.pais_cod_pais = psCodigoPais and
 dp.emco_cod_empr_come = psCodEmpresa and
 -- se agrego el tipo de clasificacion, para este caso son B: Beneficios
 dp.tip_clas = 'B' and
 exists (select null from edu_histo_clasi_benef_cabec ca
 where ca.pais_cod_pais = dp.pais_cod_pais and
 ca.emco_cod_empr_come = dp.emco_cod_empr_come and
 ca.clas_cod_clas = dp.cod_clas and
 ca.cam_proc = psCodigoPeriodo and
 ca.est_clas = 'A'
 ) and
 dp.est_clas = 'A' ;

 row_cursor curClasif%ROWTYPE;
begin

 OPEN curClasif; -- open the cursor

 LOOP
 FETCH curClasif INTO row_cursor ;

 EXIT WHEN curClasif%notfound; -- exit condition
 begin

 		if(row_cursor.astt_cod_tipo_astt_curs='T') then
				 row_cursor.astt_cod_tipo_astt_curs:=null;
				 		end if;

 -- Parametros Unica , Nuevas
 if ( row_cursor.ind_sele_capa = 'U' and row_cursor.erni_cod_esta_resp_nive = 'N' ) then
 EDU_PR_ACTUA_CLASI_UNICA_NUEVA(row_cursor.pais_cod_pais,
 row_cursor.emco_cod_empr_come,
 psCodigoPeriodo,
 row_cursor.cod_clas ,
 row_cursor.niv_capa_alca ,
 row_cursor.astt_cod_tipo_astt_curs ,
 row_cursor.erni_cod_esta_resp_nive ,
													row_cursor.IND_DESP_CLAS,
													row_cursor.NUM_CAMP_MAXI_DESP,
 psUsuario );
			 end if;
 -- Parametros Unica , N campanhas
 if ( row_cursor.ind_sele_capa = 'U' and row_cursor.erni_cod_esta_resp_nive = 'C' ) then

			 --Si no es todas las campanhas y no es campanh anterior , hace el proceso de siemrpe

			 if(row_cursor.ind_camp_todo='0' AND row_cursor.ind_camp_ante='0') then

 EDU_PR_ACTUA_CLASI_UNICA_NCAMP(row_cursor.pais_cod_pais,
 row_cursor.emco_cod_empr_come,
 psCodigoPeriodo,
 row_cursor.cod_clas ,
 row_cursor.niv_capa_alca ,
 row_cursor.astt_cod_tipo_astt_curs ,
 row_cursor.erni_cod_esta_resp_nive ,
 row_cursor.num_camp_toma_curs ,
													row_cursor.IND_DESP_CLAS,
													row_cursor.NUM_CAMP_MAXI_DESP,
 psUsuario );
 end if;

 if(row_cursor.ind_camp_todo='1') then
 EDU_PR_CLASI_UNICA_NCAMP_TODOS(row_cursor.pais_cod_pais,
 row_cursor.emco_cod_empr_come,
 psCodigoPeriodo,
 row_cursor.cod_clas ,
 row_cursor.niv_capa_alca ,
 row_cursor.astt_cod_tipo_astt_curs ,
 row_cursor.erni_cod_esta_resp_nive ,
													row_cursor.IND_DESP_CLAS,
													row_cursor.NUM_CAMP_MAXI_DESP,
 psUsuario );
				 end if;

				 if(row_cursor.ind_camp_ante='1') then
 EDU_PR_CLASI_UNICA_NCAMP_ANTER(row_cursor.pais_cod_pais,
 row_cursor.emco_cod_empr_come,
 psCodigoPeriodo,
 row_cursor.cod_clas ,
 row_cursor.niv_capa_alca ,
 row_cursor.astt_cod_tipo_astt_curs ,
 row_cursor.erni_cod_esta_resp_nive ,
													row_cursor.num_camp_toma_curs ,
													row_cursor.IND_DESP_CLAS,
													row_cursor.NUM_CAMP_MAXI_DESP,
 psUsuario );
				 end if;

 end if;--FIN UNICA Y NCAMPANHA

 end ;
 END LOOP;
 CLOSE curClasif;

 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_ACTUA_BENEF_CLASI: '||ls_sqlerrm);

 END EDU_PR_ACTUA_BENEF_CLASI;

/***************************************************************************
Descripcion : Procedure que Actutlzacion Beneficios a Clasificadas de Tipo Unica-Nueva
Fecha Creacion : 09/07/2007
Autor : Marco Silva
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodigoPeriodo : Codigo Periodo
 psCodigoClasi : Codigo de Clasificacion
 psCodigoCurso : Codigo de Curso
 psTipoAsis : Tipo de Asistencia
 psEstResNiv : Estado Respecto a Nivel
 psUsuario : Codigo de Usuario
***************************************************************************/
PROCEDURE EDU_PR_ACTUA_CLASI_UNICA_NUEVA
 (psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psCodigoClasi VARCHAR2,
 psCodigoCurso VARCHAR2 ,
 psTipoAsis VARCHAR2 ,
 psEstResNiv VARCHAR2 ,
 psIndDespacho 		 VARCHAR2 ,
 psNumCampMaxDespacho VARCHAR2 ,
 psUsuario VARCHAR2
 )
 AS

 CURSOR curEquivalencia IS
 SELECT
 asis.clie_cod_clie,
 asis.ccap_cod_curs_capa
 FROM edu_histo_capac_detal asis
 WHERE asis.pais_cod_pais = psCodigoPais
 AND asis.emco_cod_empr_come = psCodEmpresa
 AND asis.ccap_cod_curs_capa = psCodigoCurso
 AND asis.cam_regi_asis = psCodigoPeriodo
 AND (psTipoAsis IS NULL OR asis.astt_cod_tipo_astt_curs = psTipoAsis)
 AND NOT EXISTS ( SELECT null FROM edu_histo_clasi_benef_detal de
 WHERE de.pais_cod_pais = psCodigoPais and
 de.emco_cod_empr_come = psCodEmpresa and
 de.clas_cod_clas = psCodigoClasi and
 de.hbec_cam_proc = asis.cam_regi_asis and
 de.clie_cod_clie = asis.clie_cod_clie) ;

 CURSOR curINSAsist
 IS
 select
 asis.clie_cod_clie,
 asis.ccap_cod_curs_capa ,
 asis.cam_regi_asis -- campanha asistencia = campanha dictado = cam_capa
 FROM edu_histo_capac_detal asis
 WHERE asis.pais_cod_pais = psCodigoPais and
 asis.emco_cod_empr_come = psCodEmpresa and
 asis.ccap_cod_curs_capa = psCodigoCurso and
 (psTipoAsis IS NULL OR asis.astt_cod_tipo_astt_curs = psTipoAsis) and
 asis.cam_regi_asis = psCodigoPeriodo and
 not exists ( select null from edu_histo_clasi_benef_detal de
 where de.pais_cod_pais = psCodigoPais and
 de.emco_cod_empr_come = psCodEmpresa and
 de.clas_cod_clas = psCodigoClasi and
 de.hbec_cam_proc = asis.cam_regi_asis and
 de.clie_cod_clie = asis.clie_cod_clie ) ;

 TYPE t_cam_regi_asis IS TABLE OF edu_histo_capac_detal.cam_regi_asis%TYPE;
 TYPE t_codclien IS TABLE OF edu_histo_capac_detal.clie_cod_clie%TYPE ;
 TYPE t_codcurso IS TABLE OF edu_histo_capac_detal.ccap_cod_curs_capa%TYPE ;

 v_codclien t_codclien;
 v_codcurso t_codcurso;
 v_codclienClasi t_codclien;
 v_codcursoClasi t_codcurso;
 v_cam_regi_asisClasi t_cam_regi_asis;

 lsIndicadorClasificacion EDU_PARAM_PROGR_CAPAC.IND_EQUI_CLAS%TYPE;
 lstipoClasificacionEquiv EDU_PARAM_CLASI_EQUIV.TIP_CLAS_EQUI%TYPE;
 lsCodiClasificacionEquiv EDU_PARAM_CLASI_EQUIV.COD_CLAS_EQUI%TYPE;
 lsIndicadorDespachoClasi EDU_PARAM_PROGR_CAPAC.IND_DESP_CLAS%TYPE;
 lbEjecutar BOOLEAN;
 rows NATURAL := 1000;
 i BINARY_INTEGER := 0;

begin




 /* INSERTANDO CONSULTORAS NUEVAS EN TABLA DE BENEFICIO */
 OPEN curINSAsist;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
 FETCH curINSAsist BULK COLLECT INTO v_codclienClasi, v_codcursoClasi, v_cam_regi_asisClasi LIMIT rows;
 EXIT WHEN v_codclienClasi.COUNT = 0;

 -- Bulk bind of data in memory table...
 FORALL i in v_codclienClasi.FIRST..v_codclienClasi.LAST
 INSERT INTO EDU_HISTO_CLASI_BENEF_DETAL(
 PAIS_COD_PAIS, EMCO_COD_EMPR_COME, CLAS_COD_CLAS, HBEC_CAM_PROC,
 CLIE_COD_CLIE, NIV_CAPA_ALCA, CAM_DICT_CURS,
 EST_RESP_NIVE, IND_ENVI,
 USU_DIGI, FEC_DIGI, EST_REGI, IND_DESP_CLAS,NUM_DESP)
 VALUES (psCodigoPais, psCodEmpresa, psCodigoClasi, psCodigoPeriodo,
 v_codclienClasi(i), v_codcursoClasi(i), v_cam_regi_asisClasi(i),
 psEstResNiv, 'N',
 psUsuario, SYSDATE, '1', '0',1);

 END LOOP;
 CLOSE curINSAsist;

 -- Se inserta ahora las consultoras que todavian no han sido despachadas del periodo
 -- Anterior(S) respetando las campañas maximas que se le puede despachar y el indicador de despacho
 IF (psIndDespacho = '1') THEN
 EDU_PR_ACTUA_NO_DESPA_CLASI(psCodigoPais,psCodEmpresa,psCodigoPeriodo,psCodigoClasi,psCodigoCurso,
 lsIndicadorClasificacion,psNumCampMaxDespacho,psUsuario);
 END IF;


 /* Validando si tiene el indicador activo de Equivalencia de Clasificación */
 SELECT A.IND_EQUI_CLAS--, A.IND_DESP_CLAS este indicador no se usa
 INTO lsIndicadorClasificacion--, lsIndicadorDespachoClasi
 FROM EDU_PARAM_PROGR_CAPAC A
 WHERE A.PAIS_COD_PAIS = psCodigoPais
 AND A.EMCO_COD_EMPR_COME = psCodEmpresa
 AND A.COD_PROG_CAPA = '01';

 /* En caso este activado el indicador de Equivalencia de Clasificación */
 IF lsIndicadorClasificacion = '1' THEN

 /* Obteniendo tipo y codigo de clasificacion de equivalencia */
 lbEjecutar := TRUE;
 BEGIN
 SELECT A.TIP_CLAS_EQUI,
 A.COD_CLAS_EQUI
 INTO
 lstipoClasificacionEquiv,
 lsCodiClasificacionEquiv
 FROM EDU_PARAM_CLASI_EQUIV A
 WHERE A.PAIS_COD_PAIS = psCodigoPais
 AND A.EMCO_COD_EMPR_COME = psCodEmpresa
 AND A.COD_CLAS = psCodigoClasi
		 AND A.EST_REGI = '1'
		 AND A.EST_CLAS = 'A';
 EXCEPTION
 WHEN no_data_found THEN
 lbEjecutar := FALSE;
 END;

 /* Insertando clientes en tabla temporal */
 IF lbEjecutar THEN
 /* Insertando consultoras q todavia no se le despacha la clasificacion */
 --IF psIndDespacho = '1' THEN
 INSERT INTO EDU_GTT_CLIEN_CLASI(COD_PAIS, COD_CLIE, COD_PERI, COD_CURS_CAPA,
 TIP_CLAS_EQUI, COD_CLAS_EQUI)
 SELECT A.PAIS_COD_PAIS, A.CLIE_COD_CLIE, psCodigoPeriodo, A.NIV_CAPA_ALCA,
 lstipoClasificacionEquiv, lsCodiClasificacionEquiv
 FROM EDU_HISTO_CLASI_BENEF_DETAL A
 WHERE A.PAIS_COD_PAIS = psCodigoPais
 AND A.EMCO_COD_EMPR_COME = psCodEmpresa
 AND A.CLAS_COD_CLAS = psCodigoClasi
				AND A.HBEC_CAM_PROC = psCodigoPeriodo
				--AND A.NUM_DESP < = NVL(TO_NUMBER(psNumCampMaxDespacho),0);
 AND A.IND_DESP_CLAS = '0';
 --END IF;
 END IF;
 END IF;

 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_ACTUA_CLASI_UNICA_NUEVA: '||ls_sqlerrm);

 END EDU_PR_ACTUA_CLASI_UNICA_NUEVA;

/***************************************************************************
Descripcion : Procedure que Actutlzacion Beneficios a Clasificadas de Tipo Unica-N Campanhas
Fecha Creacion : 09/07/2007
Autor : Marco Silva
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodigoPeriodo : Codigo Periodo
 psCodigoClasi : Codigo de Clasificacion
 psCodigoCurso : Codigo de Curso
 psTipoAsis : Tipo de Asistencia
 psEstResNiv : Estado Respecto a Nivel
 psNumCamp : Numero Campanhas
 psUsuario : Codigo de Usuario
***************************************************************************/
PROCEDURE EDU_PR_ACTUA_CLASI_UNICA_NCAMP
 (psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psCodigoClasi VARCHAR2,
 psCodigoCurso VARCHAR2 ,
 psTipoAsis VARCHAR2 ,
 psEstResNiv VARCHAR2 ,
 psNumCamp number ,
 psIndDespacho 		 VARCHAR2 ,
 psNumCampMaxDespacho VARCHAR2 ,
 psUsuario VARCHAR2
 )
 AS
 CURSOR curINSAsist
 IS
 select
 asis.clie_cod_clie,
 asis.ccap_cod_curs_capa ,
 asis.cam_regi_asis

 FROM edu_histo_capac_detal asis
 WHERE asis.pais_cod_pais = psCodigoPais and
 asis.emco_cod_empr_come = psCodEmpresa and
 asis.ccap_cod_curs_capa = psCodigoCurso and
 (psTipoAsis IS NULL OR asis.astt_cod_tipo_astt_curs = psTipoAsis) and
 GEN_FN_DEVUE_NSGTE_CAMPA(asis.cam_regi_asis,psNumCamp) = psCodigoPeriodo and
 not exists ( select null from edu_histo_clasi_benef_detal de
 where de.pais_cod_pais = psCodigoPais and
 de.emco_cod_empr_come = psCodEmpresa and
 de.clas_cod_clas = psCodigoClasi and
 de.hbec_cam_proc = psCodigoPeriodo and
 de.clie_cod_clie = asis.clie_cod_clie ) ;

 CURSOR curEquivalencia IS
 SELECT
 asis.clie_cod_clie,
 asis.ccap_cod_curs_capa
 FROM edu_histo_capac_detal asis
 WHERE asis.pais_cod_pais = psCodigoPais
 AND asis.emco_cod_empr_come = psCodEmpresa
 AND asis.ccap_cod_curs_capa = psCodigoCurso
 --AND asis.cam_regi_asis = psCodigoPeriodo
 AND (psTipoAsis IS NULL OR asis.astt_cod_tipo_astt_curs = psTipoAsis)
	AND GEN_FN_DEVUE_NSGTE_CAMPA(asis.cam_regi_asis,psNumCamp) = psCodigoPeriodo
 AND NOT EXISTS ( SELECT null FROM edu_histo_clasi_benef_detal de
 WHERE de.pais_cod_pais = psCodigoPais and
 de.emco_cod_empr_come = psCodEmpresa and
 de.clas_cod_clas = psCodigoClasi and
 de.hbec_cam_proc = asis.cam_regi_asis and
 de.clie_cod_clie = asis.clie_cod_clie) ;


 TYPE t_cam_regi_asis IS TABLE OF edu_histo_capac_detal.cam_regi_asis%TYPE;
 TYPE t_codclien IS TABLE OF edu_histo_capac_detal.clie_cod_clie%TYPE ;
 TYPE t_codcurso IS TABLE OF edu_histo_capac_detal.ccap_cod_curs_capa%TYPE ;

 v_codclienClasi t_codclien;
 v_codcursoClasi t_codcurso;
 v_cam_regi_asisClasi t_cam_regi_asis;

 v_codclien t_codclien;
 v_codcurso t_codcurso;
 lsIndicadorClasificacion EDU_PARAM_PROGR_CAPAC.IND_EQUI_CLAS%TYPE;
 lsIndicadorDespachoClasi EDU_PARAM_PROGR_CAPAC.IND_DESP_CLAS%TYPE;
 lstipoClasificacionEquiv EDU_PARAM_CLASI_EQUIV.TIP_CLAS_EQUI%TYPE;
 lsCodiClasificacionEquiv EDU_PARAM_CLASI_EQUIV.COD_CLAS_EQUI%TYPE;
 lbEjecutar BOOLEAN;
 rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;


begin

 /* Validando si tiene el indicador activo de Equivalencia de Clasificación */
 SELECT A.IND_EQUI_CLAS, psIndDespacho
 INTO lsIndicadorClasificacion, lsIndicadorDespachoClasi
 FROM EDU_PARAM_PROGR_CAPAC A
 WHERE A.PAIS_COD_PAIS = psCodigoPais
 AND A.EMCO_COD_EMPR_COME = psCodEmpresa
 AND A.COD_PROG_CAPA = '01';

 /* En caso este activado el indicador de Equivalencia de Clasificación */
 IF lsIndicadorClasificacion = '1' THEN

 /* Obteniendo tipo y codigo de clasificacion de equivalencia */
 lbEjecutar := TRUE;
 BEGIN
 SELECT A.TIP_CLAS_EQUI,
 A.COD_CLAS_EQUI
 INTO
 lstipoClasificacionEquiv,
 lsCodiClasificacionEquiv
 FROM EDU_PARAM_CLASI_EQUIV A
 WHERE A.PAIS_COD_PAIS = psCodigoPais
 AND A.EMCO_COD_EMPR_COME = psCodEmpresa
 AND A.COD_CLAS = psCodigoClasi
 		 AND A.EST_REGI = '1'
		 AND A.EST_CLAS = 'A';
 EXCEPTION
 WHEN no_data_found THEN
 lbEjecutar := FALSE;
 END;

 /* Insertando clientes en tabla temporal */
 IF lbEjecutar THEN
 OPEN curEquivalencia;
 LOOP
 FETCH curEquivalencia BULK COLLECT INTO
 v_codclien, v_codcurso LIMIT W_FILAS;
 IF v_codclien.COUNT > 0 THEN
 FORALL i in v_codclien.FIRST..v_codclien.LAST
 INSERT INTO EDU_GTT_CLIEN_CLASI
 (COD_PAIS, COD_CLIE, COD_PERI, COD_CURS_CAPA,
 TIP_CLAS_EQUI, COD_CLAS_EQUI)
 VALUES (psCodigoPais, v_codclien(i), psCodigoPeriodo, v_codcurso(i),
 lstipoClasificacionEquiv, lsCodiClasificacionEquiv);
 END IF;
 EXIT WHEN curEquivalencia%NOTFOUND;
 END LOOP;
 CLOSE curEquivalencia;

 /* Insertando consultoras q todavia no se le despacha la clasificacion */
 IF lsIndicadorDespachoClasi = '1' THEN
 INSERT INTO EDU_GTT_CLIEN_CLASI(COD_PAIS, COD_CLIE, COD_PERI, COD_CURS_CAPA,
 TIP_CLAS_EQUI, COD_CLAS_EQUI)
 SELECT A.PAIS_COD_PAIS, A.CLIE_COD_CLIE, psCodigoPeriodo, A.NIV_CAPA_ALCA,
 lstipoClasificacionEquiv, lsCodiClasificacionEquiv
 FROM EDU_HISTO_CLASI_BENEF_DETAL A
 WHERE A.PAIS_COD_PAIS = psCodigoPais
 AND A.EMCO_COD_EMPR_COME = psCodEmpresa
 AND A.CLAS_COD_CLAS = psCodigoClasi
 AND A.IND_DESP_CLAS = '0';
 END IF;
 END IF;
 END IF;

 -- Inserta en edu_histo_clasi_benef_detal
 OPEN curINSAsist;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
 FETCH curINSAsist BULK COLLECT INTO v_codclienClasi, v_codcursoClasi, v_cam_regi_asisClasi LIMIT rows;
 EXIT WHEN v_codclienClasi.COUNT = 0;

 -- Bulk bind of data in memory table...
 FORALL i in v_codclienClasi.FIRST..v_codclienClasi.LAST
 INSERT INTO EDU_HISTO_CLASI_BENEF_DETAL(
 PAIS_COD_PAIS, EMCO_COD_EMPR_COME, CLAS_COD_CLAS, HBEC_CAM_PROC,
 CLIE_COD_CLIE, NIV_CAPA_ALCA, CAM_DICT_CURS,
 EST_RESP_NIVE, IND_ENVI,
 USU_DIGI, FEC_DIGI, EST_REGI, IND_DESP_CLAS)
 VALUES (psCodigoPais, psCodEmpresa, psCodigoClasi, psCodigoPeriodo,
 v_codclienClasi(i), v_codcursoClasi(i), v_cam_regi_asisClasi(i),
 psEstResNiv, 'N',
 psUsuario, SYSDATE, '1', '0');

 END LOOP;
 CLOSE curINSAsist;



EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_ACTUA_CLASI_UNICA_NCAMP: '||ls_sqlerrm);

END EDU_PR_ACTUA_CLASI_UNICA_NCAMP;

/***************************************************************************
Descripcion : Procedure que Actualizacion Envio de Clasificaciones
Fecha Creacion : 09/07/2007
Autor : Marco Silva
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodigoPeriodo : Codigo Periodo
 psUsuario : Codigo de Usuario
***************************************************************************/
PROCEDURE EDU_PR_ACTUA_ENVIO_CLASI
 (psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psUsuario VARCHAR2
 )
 AS
 CURSOR curUPDClasif
 IS
 select t.pais_cod_pais,
 t.emco_cod_empr_come,
 t.clas_cod_clas,
 t.hbec_cam_proc,
 t.clie_cod_clie
 from edu_histo_clasi_benef_detal t
 where t.pais_cod_pais = psCodigoPais and
 t.emco_cod_empr_come = psCodEmpresa and
 t.hbec_cam_proc = psCodigoPeriodo and
 t.ind_envi = 'N' ;

TYPE t_codPais IS TABLE OF edu_histo_clasi_benef_detal.PAIS_COD_PAIS%TYPE ;
TYPE t_codEmp IS TABLE OF edu_histo_clasi_benef_detal.EMCO_COD_EMPR_COME%TYPE ;
TYPE t_codClasif IS TABLE OF edu_histo_clasi_benef_detal.clas_cod_clas%TYPE ;
TYPE t_codPeriodo IS TABLE OF edu_histo_clasi_benef_detal.hbec_cam_proc%TYPE ;
TYPE t_codCliente IS TABLE OF edu_histo_clasi_benef_detal.clie_cod_clie%TYPE ;


 v_codPais t_codPais ;
 v_codEmp t_codEmp ;
 v_codClasif t_codClasif ;
 v_codPeriodo t_codPeriodo ;
 v_codCliente t_codCliente ;

 rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;
 v_row_count NUMBER := 0;

begin

 OPEN curUPDClasif;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
 FETCH curUPDClasif BULK COLLECT INTO
 v_codPais ,
 v_codEmp ,
 v_codClasif ,
 v_codPeriodo ,
 v_codCliente LIMIT rows;

 EXIT WHEN v_row_count = curUPDClasif%ROWCOUNT;
 v_row_count := curUPDClasif%ROWCOUNT;

 -- Bulk bind of data in memory table...
 FORALL i IN 1..v_codPais.count
 UPDATE edu_histo_clasi_benef_detal apt
 SET
 apt.ind_envi = 'S' ,
 apt.fec_modi = sysdate ,
 apt.usu_modi = psUsuario
 where apt.pais_cod_pais = v_codPais(i) and
 apt.emco_cod_empr_come = v_codEmp(i) and
 apt.clas_cod_clas = v_codClasif(i) and
 apt.hbec_cam_proc = v_codPeriodo(i) and
 apt.clie_cod_clie = v_codCliente(i) ;

 END LOOP;
 CLOSE curUPDClasif;

 return ;

 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_ACTUA_ENVIO_CLASI: '||ls_sqlerrm);

END EDU_PR_ACTUA_ENVIO_CLASI;

/***************************************************************************
Descripcion : Procedure que inserta Equivalencia de Clasificaciones
 en Tabla Temporal
Fecha Creacion : 01/03/2008
Autor : Carlos Bazalar
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodigoPeriodo : Codigo Periodo
 psCodCliente : Codigo de Cliente
 psCodCurso : Codigo de Curso
 psCodigoClasi: codigo de Clasificacion
			psindicadorEnvio : Indicador de envio Primera Invitacion
***************************************************************************/
PROCEDURE EDU_PR_INSER_EQUIV_CLASI_TEMPO (
 psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psCodCliente VARCHAR2,
 psCodCurso VARCHAR2,
 psCodigoClasi VARCHAR2,
 psindicadorEnvio VARCHAR2)
IS
 lstipoClasificacionEquiv EDU_PARAM_CLASI_EQUIV.TIP_CLAS_EQUI%TYPE;
 lsCodiClasificacionEquiv EDU_PARAM_CLASI_EQUIV.COD_CLAS_EQUI%TYPE;
 lbEjecutar BOOLEAN;

BEGIN
 /* Obteniendo tipo y codigo de clasificacion de equivalencia */
 lbEjecutar := TRUE;
 BEGIN
 SELECT A.TIP_CLAS_EQUI,
 A.COD_CLAS_EQUI
 INTO
 lstipoClasificacionEquiv,
 lsCodiClasificacionEquiv
 FROM EDU_PARAM_CLASI_EQUIV A
 WHERE A.PAIS_COD_PAIS = psCodigoPais
 AND A.EMCO_COD_EMPR_COME = psCodEmpresa
 AND A.COD_CLAS = psCodigoClasi;
 EXCEPTION
 WHEN no_data_found THEN
 lbEjecutar := FALSE;
 END;

	IF (psIndicadorEnvio='1') THEN
	begin
 INSERT INTO EDU_GTT_CLIEN_CLASI
 (COD_PAIS, COD_CLIE, COD_PERI, COD_CURS_CAPA,
 TIP_CLAS_EQUI, COD_CLAS_EQUI)
 VALUES (psCodigoPais, psCodCliente, psCodigoPeriodo, psCodCurso,
 lstipoClasificacionEquiv, lsCodiClasificacionEquiv);
	exception
	 when others then
	 null;
	end;
 END IF;
EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_INSER_EQUIV_CLASI_TEMPO: '||ls_sqlerrm);
END EDU_PR_INSER_EQUIV_CLASI_TEMPO;


/***************************************************************************
Descripcion : Procedure que efectua el Envio de Equivalencia de
 Clasificaciones
Fecha Creacion : 29/02/2008
Autor : Carlos Bazalar
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodigoPeriodo : Codigo Periodo
***************************************************************************/
PROCEDURE EDU_PR_ENVIO_EQUIV_CLASI(
 psCodPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodPeriodo VARCHAR2)
IS
 TYPE t_codCliente IS TABLE OF EDU_GTT_CLIEN_CLASI.COD_CLIE%TYPE;
 TYPE t_tipoClasi IS TABLE OF EDU_GTT_CLIEN_CLASI.TIP_CLAS_EQUI%TYPE;
 TYPE t_codClasi IS TABLE OF EDU_GTT_CLIEN_CLASI.COD_CLAS_EQUI%TYPE;
 TYPE t_idTipo IS TABLE OF MAE_TIPO_CLASI_CLIEN.OID_TIPO_CLAS%TYPE;
 TYPE t_idClasi IS TABLE OF MAE_CLASI.OID_CLAS%TYPE;

 --
 TYPE t_codCurso IS TABLE OF EDU_GTT_CLIEN_CLASI.COD_CURS_CAPA%TYPE;
 --
 v_codCliente t_codCliente;
 v_tipoClasi t_tipoClasi;
 v_codClasi t_codClasi;
 v_idTipo t_idTipo;
 v_idClasi t_idClasi;
 v_codCurso 	 t_codCurso;

 -- cursor equiv
 v_idTipo_clas t_idTipo;
 v_idClasi_clas t_idClasi;
 --
 lnIdpais NUMBER;
 lnIdMarca NUMBER;
 lnIdCanal NUMBER;
 lnIdCliente NUMBER;
 lnIdTipoSubtipo NUMBER;

 lnIdPeriodo NUMBER;
 lbEjecutar BOOLEAN;
 lbInsertar BOOLEAN;
 ldFecha DATE;

 CURSOR curClasifica IS
 SELECT DISTINCT
 B.OID_TIPO_CLAS,
 D.OID_CLAS
 FROM EDU_PARAM_CLASI_EQUIV A,
 MAE_TIPO_CLASI_CLIEN B,
 MAE_SUBTI_CLIEN C,
 MAE_CLASI D
 WHERE A.PAIS_COD_PAIS = psCodPais
 AND A.EMCO_COD_EMPR_COME = psCodEmpresa
 AND C.COD_SUBT_CLIE = '04'
 AND B.SBTI_OID_SUBT_CLIE = C.OID_SUBT_CLIE
 AND B.COD_TIPO_CLAS = A.TIP_CLAS_EQUI
 AND D.TCCL_OID_TIPO_CLAS = B.OID_TIPO_CLAS
 AND D.COD_CLAS = A.COD_CLAS_EQUI
	 AND A.EST_REGI = '1'
	 AND A.EST_CLAS = 'A';

 CURSOR curEquivalencia IS
 SELECT A.COD_CLIE,
 A.TIP_CLAS_EQUI,
 A.COD_CLAS_EQUI,
 B.OID_TIPO_CLAS,
 D.OID_CLAS,
		 A.COD_CURS_CAPA
 FROM EDU_GTT_CLIEN_CLASI A,
 MAE_TIPO_CLASI_CLIEN B,
 MAE_SUBTI_CLIEN C,
 MAE_CLASI D,
		EDU_TMP_PEDID_CONSU E
 WHERE A.COD_PAIS = psCodPais
 AND A.COD_PERI = psCodPeriodo
 AND C.COD_SUBT_CLIE = '04'
 AND B.SBTI_OID_SUBT_CLIE = C.OID_SUBT_CLIE
 AND B.COD_TIPO_CLAS = A.TIP_CLAS_EQUI
 AND D.TCCL_OID_TIPO_CLAS = B.OID_TIPO_CLAS
 AND D.COD_CLAS = A.COD_CLAS_EQUI
	 AND E.COD_PAIS = A.COD_PAIS
 AND E.COD_EMPR_COME = psCodEmpresa
	 AND E.COD_CLIE = A.COD_CLIE
	 AND E.CAM_PROC = A.COD_PERI;

BEGIN
 lnIdpais := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodPais);
 lnIdMarca := gen_pkg_gener.gen_fn_devuelve_id_marca('T');
 lnIdCanal := gen_pkg_gener.gen_fn_devuelve_id_canal('VD');
 lnIdPeriodo := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodPeriodo, lnIdMarca, lnIdCanal);

 /* Recorriendo lista de clientes para borrar las clasifcaciones de EDU */
 OPEN curEquivalencia;
 LOOP
 FETCH curEquivalencia BULK COLLECT INTO
 v_codCliente, v_tipoClasi, v_codClasi,
 v_idTipo, v_idClasi ,v_codCurso LIMIT W_FILAS;
 IF v_codCliente.COUNT > 0 THEN
 FOR i IN v_codCliente.FIRST .. v_codCliente.LAST LOOP
 lbEjecutar := TRUE;
 BEGIN
 SELECT A.OID_CLIE
 INTO lnIdCliente
 FROM MAE_CLIEN A
 WHERE A.PAIS_OID_PAIS = lnIdpais
 AND A.COD_CLIE = v_codCliente(i);

 SELECT A.OID_CLIE_TIPO_SUBT
 INTO lnIdTipoSubtipo
 FROM MAE_CLIEN_TIPO_SUBTI A,
 MAE_TIPO_CLIEN B,
 MAE_SUBTI_CLIEN C
 WHERE A.CLIE_OID_CLIE = lnIdCliente
 AND B.COD_TIPO_CLIE = '02'
 AND C.COD_SUBT_CLIE = '04'
 AND B.OID_TIPO_CLIE = A.TICL_OID_TIPO_CLIE
 AND C.OID_SUBT_CLIE = A.SBTI_OID_SUBT_CLIE;
 EXCEPTION
 WHEN no_data_found THEN
 lbEjecutar := FALSE;
 END;

 /* En caso se hayan encontrado los valores previos */
 IF lbEjecutar THEN

			 /* eliminando las clasificaciones previas DE EDUCACION por CADA cliente 18/09/2008*/
				 OPEN curClasifica;
				 LOOP
				 FETCH curClasifica BULK COLLECT INTO
				 v_idTipo_clas, v_idClasi_clas LIMIT W_FILAS;
				 IF v_idTipo_clas.COUNT > 0 THEN
				 FORALL j in v_idTipo_clas.FIRST..v_idTipo_clas.LAST

				 DELETE FROM MAE_CLIEN_CLASI A
				 WHERE A.TCCL_OID_TIPO_CLASI = v_idTipo_clas(j)
				 AND A.CLAS_OID_CLAS = v_idClasi_clas(j)
							 AND A.CTSU_OID_CLIE_TIPO_SUBT = lnIdTipoSubtipo;
				 END IF;
				 EXIT WHEN curClasifica%NOTFOUND;
				 END LOOP;
				 CLOSE curClasifica;

 END IF;
 END LOOP;
 END IF;
 EXIT WHEN curEquivalencia%NOTFOUND;
 END LOOP;
 CLOSE curEquivalencia;


 /* Recorriendo lista de clientes para insertas las clasificaciones de EDUCACION*/
 OPEN curEquivalencia;
 LOOP
 FETCH curEquivalencia BULK COLLECT INTO
 v_codCliente, v_tipoClasi, v_codClasi,
 v_idTipo, v_idClasi ,v_codCurso LIMIT W_FILAS;
 IF v_codCliente.COUNT > 0 THEN
 FOR i IN v_codCliente.FIRST .. v_codCliente.LAST LOOP
 lbEjecutar := TRUE;
 BEGIN
 SELECT A.OID_CLIE
 INTO lnIdCliente
 FROM MAE_CLIEN A
 WHERE A.PAIS_OID_PAIS = lnIdpais
 AND A.COD_CLIE = v_codCliente(i);

 SELECT A.OID_CLIE_TIPO_SUBT
 INTO lnIdTipoSubtipo
 FROM MAE_CLIEN_TIPO_SUBTI A,
 MAE_TIPO_CLIEN B,
 MAE_SUBTI_CLIEN C
 WHERE A.CLIE_OID_CLIE = lnIdCliente
 AND B.COD_TIPO_CLIE = '02'
 AND C.COD_SUBT_CLIE = '04'
 AND B.OID_TIPO_CLIE = A.TICL_OID_TIPO_CLIE
 AND C.OID_SUBT_CLIE = A.SBTI_OID_SUBT_CLIE;
				EXCEPTION
 WHEN no_data_found THEN
 lbEjecutar := FALSE;
			 	END;

 /* En caso se hayan encontrado los valores previos */
 IF lbEjecutar THEN
 ldFecha := GEN_PKG_GENER.GEN_FN_FECHA_ACTUAL_SIN_HORA();
			 BEGIN
 INSERT INTO MAE_CLIEN_CLASI (
 OID_CLIE_CLAS, CTSU_OID_CLIE_TIPO_SUBT,
 CLAS_OID_CLAS, PERD_OID_PERI,
 TCCL_OID_TIPO_CLASI, FEC_CLAS,
 IND_PPAL, FEC_ULTI_ACTU)
 VALUES
 (EDU_FN_CLIEN_CLASI_SEQUE_NEXT(), lnIdTipoSubtipo,
 v_idClasi(i), lnIdPeriodo,
 v_idTipo(i), ldFecha,
 '0', SYSDATE);
				EXCEPTION
				 WHEN OTHERS THEN
				 	 INSERT INTO EDU_TMP_CLIEN_CLASI
					 (COD_PAIS, COD_CLIE, COD_PERI, COD_CURS_CAPA,TIP_CLAS_EQUI, COD_CLAS_EQUI , FEC_DIGI)
					 VALUES (psCodPais, v_codCliente(i), psCodPeriodo, v_codCurso(i),v_tipoClasi(i), v_codClasi(i), SYSDATE);
				END;

 END IF;
 END LOOP;
 END IF;
 EXIT WHEN curEquivalencia%NOTFOUND;
 END LOOP;
 CLOSE curEquivalencia;

 /* Envio de Mensajes para las Capacitadas*/
 EDU_PR_ENVIO_MENSA_CAPA(psCodPais, psCodEmpresa, psCodPeriodo );

 /* Borrando tabla temporal */
 DELETE FROM edu_gtt_clien_clasi;
EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_ENVIO_EQUIV_CLASI: '||ls_sqlerrm);
END EDU_PR_ENVIO_EQUIV_CLASI;


/***************************************************************************
Descripcion : Devuelve SEQUENCIAL SIGUIENTE
 de Tabla MAE_CLIEN_CLASI para campo OID_CLIE_CLAS
Fecha Creacion : 03/03/2008
Autor : Carlos Bazalar
***************************************************************************/
FUNCTION EDU_FN_CLIEN_CLASI_SEQUE_NEXT
RETURN NUMBER
IS
 lnSequence NUMBER;
BEGIN
 SELECT MAE_CLCL_SEQ.NEXTVAL
 INTO lnSequence
 FROM DUAL;
 RETURN lnSequence;
EXCEPTION
WHEN NO_DATA_FOUND THEN
 RETURN NULL;
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_FN_CLIEN_CLASI_SEQUE_NEXT: '||ls_sqlerrm);
END EDU_FN_CLIEN_CLASI_SEQUE_NEXT;

/***************************************************************************
Descripcion : Procedure que efectua el borrado de Mensajes en el comercial
 de los mensajes no impresos antes del Envio de Mensajes de Equivalencia
Fecha Creacion : 27/03/2008
Autor : Carlos Bazalar
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
***************************************************************************/
PROCEDURE EDU_PR_BORRA_MENSA_NIMPRE(
 psCodPais VARCHAR2,
 psCodEmpresa VARCHAR2)
IS
 CURSOR cMensaje(vnIdPais NUMBER) IS
 SELECT A.OID_BUZO_MENS
 FROM
 MSG_BUZON_MENSA A,
 MSG_MENSA B,
 EDU_MENSA_EQUIV C
 WHERE B.PAIS_OID_PAIS = vnIdPais
 AND B.OID_MENS = A.MENS_OID_MENS
 AND C.COD_MENS_EQUI = B.COD_MENS
 AND A.NUM_LOTE_IMPR IS NULL;

 TYPE t_oidMensaje IS TABLE OF MSG_BUZON_MENSA.OID_BUZO_MENS%TYPE;
 v_oidMensaje t_oidMensaje;
 lnIdPais NUMBER;
BEGIN
 lnIdPais := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodPais);

 /* eliminando los mensajes sin imprimir */
 OPEN cMensaje(lnIdPais);
 LOOP
 FETCH cMensaje BULK COLLECT INTO
 v_oidMensaje LIMIT W_FILAS;
 IF v_oidMensaje.COUNT > 0 THEN
 FORALL i in v_oidMensaje.FIRST..v_oidMensaje.LAST
 DELETE FROM MSG_BUZON_MENSA A
 WHERE A.OID_BUZO_MENS = v_oidMensaje(i);
 END IF;
 EXIT WHEN cMensaje%NOTFOUND;
 END LOOP;
 CLOSE cMensaje;
EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_BORRA_MENSA_NIMPRE: '||ls_sqlerrm);
END EDU_PR_BORRA_MENSA_NIMPRE;


/***************************************************************************
Descripcion : Procedure que efectua el Envio de Mensajes para las
 Capacitadas
Fecha Creacion : 13/03/2008
Autor : Carlos Bazalar
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodigoPeriodo : Codigo Periodo
***************************************************************************/
PROCEDURE EDU_PR_ENVIO_MENSA_CAPA(
 psCodPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodPeriodo VARCHAR2)
IS
 TYPE t_codclien IS TABLE OF EDU_GTT_CLIEN_CLASI.COD_CLIE%TYPE ;
 TYPE t_nomclien IS TABLE OF VARCHAR2(200);
 TYPE t_codcurso IS TABLE OF EDU_GTT_CLIEN_CLASI.COD_CURS_CAPA%TYPE ;
 v_codclien t_codclien;
 v_codcurso t_codcurso;
 v_nomclien t_nomclien;

 CURSOR curMensaje(vsIndicadorNombreCompleto VARCHAR2)
 IS
 SELECT DISTINCT
 A.COD_CLIE,
 A.COD_CURS_CAPA,
 EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_NOMBR_CONSU(
								psCodPais,
								psCodEmpresa,
								A.COD_CLIE,
								vsIndicadorNombreCompleto)
 FROM EDU_GTT_CLIEN_CLASI A,
 EDU_HISTO_CAPAC_DETAL B
 WHERE A.COD_PAIS = psCodPais
 AND A.COD_PERI = psCodPeriodo

 AND B.PAIS_COD_PAIS = A.COD_PAIS
 AND B.EMCO_COD_EMPR_COME = psCodEmpresa
 AND B.CCAP_COD_CURS_CAPA = A.COD_CURS_CAPA
 AND B.CLIE_COD_CLIE = A.COD_CLIE ;

 lsIndicadorEquiMensaje VARCHAR2(1);
 lsIndicadorNombreCompleto VARCHAR2(1);
 lsMensaje VARCHAR2(10);
 lnIdMensaje NUMBER;
BEGIN
 /* Verificando el indicador Equivalencia de mensajes */
 SELECT A.IND_EQUI_MENS, A.IND_NOMB_COMP
 INTO
 lsIndicadorEquiMensaje, lsIndicadorNombreCompleto
 FROM EDU_PARAM_PROGR_CAPAC A
 WHERE A.PAIS_COD_PAIS = psCodPais
 AND A.EMCO_COD_EMPR_COME = psCodEmpresa
 AND A.COD_PROG_CAPA = '01';

 /* insertando mensajes respectivos */
 OPEN curMensaje(lsIndicadorNombreCompleto);
 LOOP
 FETCH curMensaje BULK COLLECT INTO
 v_codclien, v_codcurso, v_nomclien LIMIT W_FILAS;
 IF v_codclien.COUNT > 0 THEN
 FOR i IN v_codclien.FIRST .. v_codclien.LAST LOOP
 lsMensaje := EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_CODIG_MENSA(
								psCodPais,
								psCodEmpresa,
								'1',
								'2',
								v_codcurso(i),
								'CP',
								NULL,
								v_codclien(i));
 lnIdMensaje := GEN_PKG_GENER.GEN_FN_DEVUE_OID_MENSA(lsMensaje);

			 IF (lnIdMensaje IS NOT NULL ) THEN
						 INSERT INTO MSG_BUZON_MENSA (
 				OID_BUZO_MENS,
 				NUM_SECU,
 				CLIE_OID_CLIE,
 				MENS_OID_MENS,
 				MODU_OID_MODU_ORIG,
 				DATO_VARI_01,
 				FEC_GRAB, IND_LIST_CONS, IND_ACTI
 )
 VALUES (
 				GEN_PKG_GENER.GEN_FN_BUZON_MENSA_SEQUE_NEXT(),
 				GEN_PKG_GENER.GEN_FN_MENSA_NSECU_SEQUE_NEXT(),
 				GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(v_codclien(i)),
 				lnIdMensaje,
 				GEN_PKG_GENER.GEN_FN_DEVUE_MENSA_DATOS(
 						lnIdMensaje,
 						'MODU_OID_MODU'),
 				v_nomclien(i),
 				SYSDATE, 1, 1);
			 END IF;

 END LOOP;
 END IF;
 EXIT WHEN curMensaje%NOTFOUND;
 END LOOP;
 CLOSE curMensaje;

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_ENVIO_MENSA_CAPA: '||ls_sqlerrm);
END EDU_PR_ENVIO_MENSA_CAPA;


/***************************************************************************
Descripcion : Procedure que Actualiza el indicador de despacho de
 clasificacion en la tabla historica de clasificaciones
Fecha Creacion : 02/04/2008
Autor : Carlos Bazalar
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodPeriodo : Codigo Periodo
 psUsuario : Codigo de Usuario
***************************************************************************/
PROCEDURE EDU_PR_ACTUA_CLASI_PEDID_FACTU(
 psCodPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodPeriodo VARCHAR2,
 psUsuario VARCHAR2)
IS
BEGIN
 UPDATE EDU_HISTO_CLASI_BENEF_DETAL A
 SET
 A.IND_DESP_CLAS = '1',
 A.TIP_ENVI_CLAS = 'N',
 A.USU_MODI = psUsuario,
 A.FEC_MODI = SYSDATE
 WHERE A.PAIS_COD_PAIS = psCodPais
 AND A.EMCO_COD_EMPR_COME = psCodEmpresa
 AND A.IND_DESP_CLAS = '0'
	 AND A.HBEC_CAM_PROC = psCodPeriodo
 AND EXISTS (SELECT X.PAIS_COD_PAIS
 FROM EDU_HISTO_PEDID_CONSU X
 WHERE X.PAIS_COD_PAIS = A.PAIS_COD_PAIS
 AND X.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
 AND X.CAM_PROC = psCodPeriodo
 AND X.COD_CLIE = A.CLIE_COD_CLIE
 AND X.IND_FACT = '1');

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_ACTUA_CLASI_PEDID_FACTU: '||ls_sqlerrm);
END EDU_PR_ACTUA_CLASI_PEDID_FACTU;


/***************************************************************************
Descripcion : Procedimiento que realiza el Proceso de Envio de Bloqueo de Consultoras
Fecha Creacion : 06/05/2008
Autor : Sergio Buchelli
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodPeriodo : Campaña de Proceso
 psUsuario : Usuario
***************************************************************************/
 PROCEDURE EDU_PR_PROCE_ENVIO_BLOQU_CONSU(
 pscodigopais VARCHAR2,
 pscodempresa VARCHAR2,
 pscodperiodo VARCHAR2,
 psusuario VARCHAR2
 )
 IS
 TYPE tRegTemporal IS RECORD (
 COD_PAIS EDU_GTT_HISTO_BLOQU_CONSU.COD_PAIS%TYPE,
 COD_CLIE EDU_GTT_HISTO_BLOQU_CONSU.COD_CLIE%TYPE,
 CAM_PROC EDU_GTT_HISTO_BLOQU_CONSU.CAM_PROC%TYPE,
	 EST_BLOQ EDU_HISTO_BLOQU_CONSU.EST_BLOQ%TYPE);

 TYPE TABLA_TEMPORAL IS TABLE OF tRegTemporal;
 tablaRegistro TABLA_TEMPORAL;
 regRegistro tRegTemporal;

 lnOidTipoBloq 		MAE_TIPO_BLOQU.OID_TIPO_BLOQ%TYPE;
 lnOidValorAccion 		MAE_TIPO_BLOQU.MAAB_OID_VALO_ACCI_BLOQ%TYPE;

 MOTIVO_BLOQUEO		VARCHAR2(200):='NO HA LLEVADO TALLER FI';
 MOTIVO_DESBLOQUEO		VARCHAR2(200):='CAPACITADA TALLER FI';

 COD_BLOQUEO VARCHAR2(15):='EDU10';

 lnIdCliente MAE_CLIEN.OID_CLIE%TYPE;
 lnContClient NUMBER;
 CURSOR cursorRegistro
 IS
 	 SELECT DISTINCT PAIS_COD_PAIS COD_PAIS,
				CLIE_COD_CLIE COD_CLIE,
 CAM_PROC,
				EST_BLOQ
				FROM EDU_HISTO_BLOQU_CONSU T
				WHERE T.PAIS_COD_PAIS = pscodigopais
				 AND T.EMCO_COD_EMPR_COME = NVL(pscodempresa,(SELECT COD_EMPR_COME FROM EDU_EMPRE_COMER WHERE PAIS_COD_PAIS = pscodigopais))
				 AND T.EST_REGI='1';
 BEGIN
 --OBTENEMOS EL OID DE TIPO BLOQUEO
 	SELECT A.OID_TIPO_BLOQ INTO lnOidTipoBloq
		 FROM MAE_TIPO_BLOQU A
	 WHERE A.COD_TIPO_BLOQ='01';

 -- obtenemos el oid
	 	select a.OID_VALO_ACCI_BLOQ into lnOidValorAccion
		from mae_valor_accio_bloqu a
		where cod_valo_bloq='A';


 --Abrimos el cursor
 OPEN cursorRegistro;
 LOOP
 FETCH cursorRegistro BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
 IF tablaRegistro.COUNT > 0 THEN
 FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
 regRegistro := tablaRegistro(x);

			 lnIdCliente := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(regRegistro.COD_CLIE);

			 IF(regRegistro.EST_BLOQ='B') THEN

			 SELECT COUNT(1) INTO lnContClient
				FROM MAE_CLIEN_BLOQU X
				WHERE X.CLIE_OID_CLIE = lnIdCliente
				 AND X.TIBQ_OID_TIPO_BLOQ = lnOidTipoBloq
				 AND X.VAL_MOTI_BLOQ = COD_BLOQUEO
				 AND X.FEC_DESB IS NULL;
				--si no existe hay q insertarla
				IF(lnContClient=0) then

					INSERT INTO MAE_CLIEN_BLOQU (
					 OID_BLOQ, CLIE_OID_CLIE, TIBQ_OID_TIPO_BLOQ,
					 FEC_BLOQ, VAL_MOTI_BLOQ, VAL_USUA_BLOQ,
					 OBS_BLOQ,MAAB_OID_VALO_ACCI_BLOQ)
					VALUES (GEN_PKG_GENER.GEN_FN_CLIEN_BLOQU_SEQUE_NEXT(),
							lnIdCliente,
							lnOidTipoBloq,
					 	TRUNC(SYSDATE),
					 	COD_BLOQUEO,
					 	psusuario,
					 	MOTIVO_BLOQUEO,lnOidValorAccion);
				end if;

			 ELSE

			 UPDATE MAE_CLIEN_BLOQU X
			 SET X.FEC_DESB = TRUNC(SYSDATE),
			 X.VAL_USUA_DESB=psusuario,
				 X.MAAB_OID_VALO_ACCI_DESB=lnOidValorAccion,
				 X.OBS_DESB=MOTIVO_DESBLOQUEO
			 WHERE X.CLIE_OID_CLIE = lnIdCliente
			 AND X.TIBQ_OID_TIPO_BLOQ=lnOidTipoBloq
				 AND X.VAL_MOTI_BLOQ=COD_BLOQUEO
				 AND X.FEC_DESB IS NULL;

			 END IF;
 END LOOP;
 END IF;
 EXIT WHEN cursorRegistro%NOTFOUND;
 END LOOP;
 CLOSE cursorRegistro;--Cerramos el cursor

 EXCEPTION
 WHEN OTHERS
 THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
 raise_application_error (-20123,
 'EDU_PR_PROCE_ENVIO_BLOQU_CONSU: '
 || ls_sqlerrm
 );
 END EDU_PR_PROCE_ENVIO_BLOQU_CONSU;



/***************************************************************************
Descripcion : Procedure que Actutlzacion Beneficios a Clasificadas de Tipo Unica-N Campanhas
 				 Con el indicador Todo
Fecha Creacion : 12/05/2008
Autor : Sergio Buchelli
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodigoPeriodo : Codigo Periodo
 psCodigoClasi : Codigo de Clasificacion
 psCodigoCurso : Codigo de Curso
 psTipoAsis : Tipo de Asistencia
 psEstResNiv : Estado Respecto a Nivel
 psNumCamp : Numero Campanhas
 psUsuario : Codigo de Usuario
***************************************************************************/
PROCEDURE EDU_PR_CLASI_UNICA_NCAMP_TODOS
 (psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psCodigoClasi VARCHAR2,
 psCodigoCurso VARCHAR2 ,
 psTipoAsis VARCHAR2 ,
 psEstResNiv VARCHAR2 ,
 psIndDespacho 		 VARCHAR2,
 psNumCampMaxDespacho VARCHAR2,
 psUsuario VARCHAR2
 )
 AS
 CURSOR curINSAsist
 IS
 select
 asis.clie_cod_clie,
 asis.ccap_cod_curs_capa ,
 asis.cam_regi_asis
 FROM edu_histo_capac_detal asis
 WHERE asis.pais_cod_pais = psCodigoPais and
 asis.emco_cod_empr_come = psCodEmpresa and
 asis.ccap_cod_curs_capa = psCodigoCurso and
 (psTipoAsis IS NULL OR asis.astt_cod_tipo_astt_curs = psTipoAsis) and
 asis.cam_capa <= psCodigoPeriodo and
 not exists ( select null from edu_histo_clasi_benef_detal de
 where de.pais_cod_pais = psCodigoPais and
 de.emco_cod_empr_come = psCodEmpresa and
 de.clas_cod_clas = psCodigoClasi and
											 de.hbec_cam_proc = psCodigoPeriodo and
												 de.NIV_CAPA_ALCA = psCodigoCurso and
 de.clie_cod_clie = asis.clie_cod_clie ) ;

 CURSOR curEquivalencia IS
 SELECT
 asis.clie_cod_clie,
 asis.ccap_cod_curs_capa
 FROM edu_histo_capac_detal asis
 WHERE asis.pais_cod_pais = psCodigoPais
 AND asis.emco_cod_empr_come = psCodEmpresa
 AND asis.ccap_cod_curs_capa = psCodigoCurso
 AND (psTipoAsis IS NULL OR asis.astt_cod_tipo_astt_curs = psTipoAsis)
 AND asis.cam_capa <= psCodigoPeriodo
 AND NOT EXISTS ( SELECT null FROM edu_histo_clasi_benef_detal de
 WHERE de.pais_cod_pais = psCodigoPais and
 de.emco_cod_empr_come = psCodEmpresa and
 de.clas_cod_clas = psCodigoClasi and
						 de.hbec_cam_proc = psCodigoPeriodo and
						 de.NIV_CAPA_ALCA = psCodigoCurso and
 de.clie_cod_clie = asis.clie_cod_clie) ;


 TYPE t_cam_regi_asis IS TABLE OF edu_histo_capac_detal.cam_regi_asis%TYPE;
 TYPE t_codclien IS TABLE OF edu_histo_capac_detal.clie_cod_clie%TYPE ;
 TYPE t_codcurso IS TABLE OF edu_histo_capac_detal.ccap_cod_curs_capa%TYPE ;

 v_codclienClasi t_codclien;
 v_codcursoClasi t_codcurso;
 v_cam_regi_asisClasi t_cam_regi_asis;

 v_codclien t_codclien;
 v_codcurso t_codcurso;
 lsIndicadorClasificacion EDU_PARAM_PROGR_CAPAC.IND_EQUI_CLAS%TYPE;
 lsIndicadorDespachoClasi EDU_PARAM_PROGR_CAPAC.IND_DESP_CLAS%TYPE;
 lstipoClasificacionEquiv EDU_PARAM_CLASI_EQUIV.TIP_CLAS_EQUI%TYPE;
 lsCodiClasificacionEquiv EDU_PARAM_CLASI_EQUIV.COD_CLAS_EQUI%TYPE;
 lbEjecutar BOOLEAN;
 rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;


begin

 /* Validando si tiene el indicador activo de Equivalencia de Clasificación */
 SELECT A.IND_EQUI_CLAS, psIndDespacho
 INTO lsIndicadorClasificacion, lsIndicadorDespachoClasi
 FROM EDU_PARAM_PROGR_CAPAC A
 WHERE A.PAIS_COD_PAIS = psCodigoPais
 AND A.EMCO_COD_EMPR_COME = psCodEmpresa
 AND A.COD_PROG_CAPA = '01';

 /* En caso este activado el indicador de Equivalencia de Clasificación */
 IF lsIndicadorClasificacion = '1' THEN

 /* Obteniendo tipo y codigo de clasificacion de equivalencia */
 lbEjecutar := TRUE;
 BEGIN
 SELECT A.TIP_CLAS_EQUI,
 A.COD_CLAS_EQUI
 INTO
 lstipoClasificacionEquiv,
 lsCodiClasificacionEquiv
 FROM EDU_PARAM_CLASI_EQUIV A
 WHERE A.PAIS_COD_PAIS = psCodigoPais
 AND A.EMCO_COD_EMPR_COME = psCodEmpresa
 AND A.COD_CLAS = psCodigoClasi
 		 AND A.EST_REGI = '1'
		 AND A.EST_CLAS = 'A';
 EXCEPTION
 WHEN no_data_found THEN
 lbEjecutar := FALSE;
 END;

 /* Insertando clientes en tabla temporal */
 IF lbEjecutar THEN
 OPEN curEquivalencia;
 LOOP
 FETCH curEquivalencia BULK COLLECT INTO
 v_codclien, v_codcurso LIMIT W_FILAS;
 IF v_codclien.COUNT > 0 THEN
 FORALL i in v_codclien.FIRST..v_codclien.LAST
 INSERT INTO EDU_GTT_CLIEN_CLASI
 (COD_PAIS, COD_CLIE, COD_PERI, COD_CURS_CAPA,
 TIP_CLAS_EQUI, COD_CLAS_EQUI)
 VALUES (psCodigoPais, v_codclien(i), psCodigoPeriodo, v_codcurso(i),
 lstipoClasificacionEquiv, lsCodiClasificacionEquiv);
 END IF;
 EXIT WHEN curEquivalencia%NOTFOUND;
 END LOOP;
 CLOSE curEquivalencia;

 /* Insertando consultoras q todavia no se le despacha la clasificacion */
 -- IF lsIndicadorDespachoClasi = '1' THEN
 BEGIN
 INSERT INTO EDU_GTT_CLIEN_CLASI(COD_PAIS, COD_CLIE, COD_PERI, COD_CURS_CAPA,
 TIP_CLAS_EQUI, COD_CLAS_EQUI)
 SELECT DISTINCT A.PAIS_COD_PAIS, A.CLIE_COD_CLIE, psCodigoPeriodo, A.NIV_CAPA_ALCA,
 lstipoClasificacionEquiv, lsCodiClasificacionEquiv
 FROM EDU_HISTO_CLASI_BENEF_DETAL A
 WHERE A.PAIS_COD_PAIS = psCodigoPais
 AND A.EMCO_COD_EMPR_COME = psCodEmpresa
 AND A.CLAS_COD_CLAS = psCodigoClasi
 AND A.IND_DESP_CLAS = '0';
 EXCEPTION
 WHEN OTHERS THEN
 NULL;
 END;
 --END IF;
 END IF;
 END IF;

 -- Inserta en edu_histo_clasi_benef_detal
 OPEN curINSAsist;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
 FETCH curINSAsist BULK COLLECT INTO v_codclienClasi, v_codcursoClasi, v_cam_regi_asisClasi LIMIT rows;
 EXIT WHEN v_codclienClasi.COUNT = 0;

 -- Bulk bind of data in memory table...
 FORALL i in v_codclienClasi.FIRST..v_codclienClasi.LAST
 INSERT INTO EDU_HISTO_CLASI_BENEF_DETAL(
 PAIS_COD_PAIS, EMCO_COD_EMPR_COME, CLAS_COD_CLAS, HBEC_CAM_PROC,
 CLIE_COD_CLIE, NIV_CAPA_ALCA, CAM_DICT_CURS,
 EST_RESP_NIVE, IND_ENVI,
 USU_DIGI, FEC_DIGI, EST_REGI, IND_DESP_CLAS)
 VALUES (psCodigoPais, psCodEmpresa, psCodigoClasi, psCodigoPeriodo,
 v_codclienClasi(i), v_codcursoClasi(i), v_cam_regi_asisClasi(i),
 psEstResNiv, 'N',
 psUsuario, SYSDATE, '1', '0');

 END LOOP;
 CLOSE curINSAsist;



EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_CLASI_UNICA_NCAMP_TODOS: '||ls_sqlerrm);

END EDU_PR_CLASI_UNICA_NCAMP_TODOS;


/***************************************************************************
Descripcion : Procedure que Actutlzacion Beneficios a Clasificadas de Tipo Unica-N Campanhas
 				 Con el indicador Campanha Anteriores
Fecha Creacion : 12/05/2008
Autor : Sergio Buchelli
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodigoPeriodo : Codigo Periodo
 psCodigoClasi : Codigo de Clasificacion
 psCodigoCurso : Codigo de Curso
 psTipoAsis : Tipo de Asistencia
 psEstResNiv : Estado Respecto a Nivel
 psUsuario : Codigo de Usuario
***************************************************************************/
PROCEDURE EDU_PR_CLASI_UNICA_NCAMP_ANTER
 (psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psCodigoClasi VARCHAR2,
 psCodigoCurso VARCHAR2 ,
 psTipoAsis VARCHAR2 ,
 psEstResNiv VARCHAR2 ,
 psNumCamp number ,
 psIndDespacho 		 VARCHAR2 ,
 psNumCampMaxDespacho VARCHAR2 ,
 psUsuario VARCHAR2
 )
 AS
 CURSOR curINSAsist
 IS
 select
 asis.clie_cod_clie,
 asis.ccap_cod_curs_capa ,
 asis.cam_regi_asis
 FROM edu_histo_capac_detal asis
 WHERE asis.pais_cod_pais = psCodigoPais and
 asis.emco_cod_empr_come = psCodEmpresa and
 asis.ccap_cod_curs_capa = psCodigoCurso and
 (psTipoAsis IS NULL OR asis.astt_cod_tipo_astt_curs = psTipoAsis) and
		 TO_NUMBER(asis.cam_capa) >= TO_NUMBER(GEN_FN_DEVUE_NSGTE_CAMPA(psCodigoPeriodo,psNumCamp*-1)) and
	 TO_NUMBER(asis.cam_capa) < TO_NUMBER(psCodigoPeriodo) and
 not exists ( select null from edu_histo_clasi_benef_detal de
 where de.pais_cod_pais = psCodigoPais and
 de.emco_cod_empr_come = psCodEmpresa and
 de.clas_cod_clas = psCodigoClasi and
											 de.hbec_cam_proc = psCodigoPeriodo and
												 de.NIV_CAPA_ALCA = psCodigoCurso and
 de.clie_cod_clie = asis.clie_cod_clie ) ;

 CURSOR curEquivalencia IS
 SELECT
 asis.clie_cod_clie,
 asis.ccap_cod_curs_capa
 FROM edu_histo_capac_detal asis
 WHERE asis.pais_cod_pais = psCodigoPais
 AND asis.emco_cod_empr_come = psCodEmpresa
 AND asis.ccap_cod_curs_capa = psCodigoCurso
 AND (psTipoAsis IS NULL OR asis.astt_cod_tipo_astt_curs = psTipoAsis)
	AND TO_NUMBER(asis.cam_capa) >= TO_NUMBER(GEN_FN_DEVUE_NSGTE_CAMPA(psCodigoPeriodo,psNumCamp*-1))
	AND TO_NUMBER(asis.cam_capa) < TO_NUMBER(psCodigoPeriodo)
 AND NOT EXISTS ( SELECT null FROM edu_histo_clasi_benef_detal de
 WHERE de.pais_cod_pais = psCodigoPais and
 de.emco_cod_empr_come = psCodEmpresa and
 de.clas_cod_clas = psCodigoClasi and
						 de.hbec_cam_proc = psCodigoPeriodo and
						 de.NIV_CAPA_ALCA = psCodigoCurso and
 de.clie_cod_clie = asis.clie_cod_clie) ;


 TYPE t_cam_regi_asis IS TABLE OF edu_histo_capac_detal.cam_regi_asis%TYPE;
 TYPE t_codclien IS TABLE OF edu_histo_capac_detal.clie_cod_clie%TYPE ;
 TYPE t_codcurso IS TABLE OF edu_histo_capac_detal.ccap_cod_curs_capa%TYPE ;

 v_codclienClasi t_codclien;
 v_codcursoClasi t_codcurso;
 v_cam_regi_asisClasi t_cam_regi_asis;

 v_codclien t_codclien;
 v_codcurso t_codcurso;
 lsIndicadorClasificacion EDU_PARAM_PROGR_CAPAC.IND_EQUI_CLAS%TYPE;
 lsIndicadorDespachoClasi EDU_PARAM_PROGR_CAPAC.IND_DESP_CLAS%TYPE;
 lstipoClasificacionEquiv EDU_PARAM_CLASI_EQUIV.TIP_CLAS_EQUI%TYPE;
 lsCodiClasificacionEquiv EDU_PARAM_CLASI_EQUIV.COD_CLAS_EQUI%TYPE;
 lbEjecutar BOOLEAN;
 rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;


begin

 /* Validando si tiene el indicador activo de Equivalencia de Clasificación */
 SELECT A.IND_EQUI_CLAS, psIndDespacho
 INTO lsIndicadorClasificacion, lsIndicadorDespachoClasi
 FROM EDU_PARAM_PROGR_CAPAC A
 WHERE A.PAIS_COD_PAIS = psCodigoPais
 AND A.EMCO_COD_EMPR_COME = psCodEmpresa
 AND A.COD_PROG_CAPA = '01';

 /* En caso este activado el indicador de Equivalencia de Clasificación */
 IF lsIndicadorClasificacion = '1' THEN

 /* Obteniendo tipo y codigo de clasificacion de equivalencia */
 lbEjecutar := TRUE;
 BEGIN
 SELECT A.TIP_CLAS_EQUI,
 A.COD_CLAS_EQUI
 INTO
 lstipoClasificacionEquiv,
 lsCodiClasificacionEquiv
 FROM EDU_PARAM_CLASI_EQUIV A
 WHERE A.PAIS_COD_PAIS = psCodigoPais
 AND A.EMCO_COD_EMPR_COME = psCodEmpresa
 AND A.COD_CLAS = psCodigoClasi
 		 AND A.EST_REGI = '1'
		 AND A.EST_CLAS = 'A';
 EXCEPTION
 WHEN no_data_found THEN
 lbEjecutar := FALSE;
 END;

 /* Insertando clientes en tabla temporal */
 IF lbEjecutar THEN
 OPEN curEquivalencia;
 LOOP
 FETCH curEquivalencia BULK COLLECT INTO
 v_codclien, v_codcurso LIMIT W_FILAS;
 IF v_codclien.COUNT > 0 THEN
 FORALL i in v_codclien.FIRST..v_codclien.LAST
 INSERT INTO EDU_GTT_CLIEN_CLASI
 (COD_PAIS, COD_CLIE, COD_PERI, COD_CURS_CAPA,
 TIP_CLAS_EQUI, COD_CLAS_EQUI)
 VALUES (psCodigoPais, v_codclien(i), psCodigoPeriodo, v_codcurso(i),
 lstipoClasificacionEquiv, lsCodiClasificacionEquiv);
 END IF;
 EXIT WHEN curEquivalencia%NOTFOUND;
 END LOOP;
 CLOSE curEquivalencia;

 /* Insertando consultoras q todavia no se le despacha la clasificacion */
 IF lsIndicadorDespachoClasi = '1' THEN
 INSERT INTO EDU_GTT_CLIEN_CLASI(COD_PAIS, COD_CLIE, COD_PERI, COD_CURS_CAPA,
 TIP_CLAS_EQUI, COD_CLAS_EQUI)
 SELECT A.PAIS_COD_PAIS, A.CLIE_COD_CLIE, psCodigoPeriodo, A.NIV_CAPA_ALCA,
 lstipoClasificacionEquiv, lsCodiClasificacionEquiv
 FROM EDU_HISTO_CLASI_BENEF_DETAL A
 WHERE A.PAIS_COD_PAIS = psCodigoPais
 AND A.EMCO_COD_EMPR_COME = psCodEmpresa
 AND A.CLAS_COD_CLAS = psCodigoClasi
 AND A.IND_DESP_CLAS = '0';
 END IF;
 END IF;
 END IF;

 -- Inserta en edu_histo_clasi_benef_detal
 OPEN curINSAsist;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
 FETCH curINSAsist BULK COLLECT INTO v_codclienClasi, v_codcursoClasi, v_cam_regi_asisClasi LIMIT rows;
 EXIT WHEN v_codclienClasi.COUNT = 0;

 -- Bulk bind of data in memory table...
 FORALL i in v_codclienClasi.FIRST..v_codclienClasi.LAST
 INSERT INTO EDU_HISTO_CLASI_BENEF_DETAL(
 PAIS_COD_PAIS, EMCO_COD_EMPR_COME, CLAS_COD_CLAS, HBEC_CAM_PROC,
 CLIE_COD_CLIE, NIV_CAPA_ALCA, CAM_DICT_CURS,
 EST_RESP_NIVE, IND_ENVI,
 USU_DIGI, FEC_DIGI, EST_REGI, IND_DESP_CLAS)
 VALUES (psCodigoPais, psCodEmpresa, psCodigoClasi, psCodigoPeriodo,
 v_codclienClasi(i), v_codcursoClasi(i), v_cam_regi_asisClasi(i),
 psEstResNiv, 'N',
 psUsuario, SYSDATE, '1', '0');

 END LOOP;
 CLOSE curINSAsist;



EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_CLASI_UNICA_NCAMP_ANTER: '||ls_sqlerrm);

END EDU_PR_CLASI_UNICA_NCAMP_ANTER;

/***************************************************************************
Descripcion : Procedure que Actualiza los beneficoa en el periodo de proceso
 con consultoras que aun no se le han despachado en sus N campanhas
					que tine como maximo paraq se le haga el despacho
Fecha Creacion : 26/06/2008
Autor : Sergio Buchelli
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodigoPeriodo : Codigo Periodo
 psCodigoClasi : Codigo de Clasificacion
 psCodigoCurso : Codigo de Curso
			psIndClasificacion 	 Indicador Clasificacion ,
			psNumCampMaxDespacho Numero Campaña Maximo Despacho,
 psUsuario : Codigo de Usuario
***************************************************************************/
PROCEDURE EDU_PR_ACTUA_NO_DESPA_CLASI
 (psCodigoPais 		 VARCHAR2,
 psCodEmpresa 		 VARCHAR2,
 psCodigoPeriodo 		 VARCHAR2,
 psCodigoClasi 		 VARCHAR2,
 psCodigoCurso 		 VARCHAR2 ,
 psIndClasificacion VARCHAR2 ,
 psNumCampMaxDespacho VARCHAR2 ,
 psUsuario 		 VARCHAR2
 )
 IS

 TYPE tRegTemporal IS RECORD (
	 CLIE_COD_CLIE EDU_HISTO_CALIF_APTAS.CLIE_COD_CLIE%TYPE,
	 NIV_CAPA_ALCA 		 EDU_HISTO_APTAS.CCAP_COD_CURS_CAPA%TYPE,
	 CAM_DICT_CURS EDU_HISTO_CLASI_BENEF_DETAL.CAM_DICT_CURS%TYPE,
	 EST_RESP_NIVE EDU_HISTO_CLASI_BENEF_DETAL.EST_RESP_NIVE%TYPE,
	 IND_ENVI 		 EDU_HISTO_CLASI_BENEF_DETAL.IND_ENVI%TYPE,
	 NUM_DESP				 EDU_HISTO_CLASI_BENEF_DETAL.NUM_DESP%TYPE
 );

 TYPE TABLA_TEMPORAL IS TABLE OF tRegTemporal;
 tablaRegistro TABLA_TEMPORAL;
 regRegistro tRegTemporal;

 lnNumDespacho		 number;

 lstipoClasificacionEquiv EDU_PARAM_CLASI_EQUIV.TIP_CLAS_EQUI%TYPE;
 lsCodiClasificacionEquiv EDU_PARAM_CLASI_EQUIV.COD_CLAS_EQUI%TYPE;

 lbEjecutar BOOLEAN;

 CURSOR cursorRegistro
 IS --obtine aquellas consultoras que no se le han despachado aun ,
	 SELECT A.CLIE_COD_CLIE,A.NIV_CAPA_ALCA, A.CAM_DICT_CURS,A.EST_RESP_NIVE, A.IND_ENVI, A.NUM_DESP
 FROM EDU_HISTO_CLASI_BENEF_DETAL A
 WHERE A.PAIS_COD_PAIS = psCodigoPais
 AND A.EMCO_COD_EMPR_COME = psCodEmpresa
 AND A.CLAS_COD_CLAS = psCodigoClasi
 AND A.NIV_CAPA_ALCA=psCodigoCurso
			AND A.HBEC_CAM_proc= GEN_FN_DEVUE_NSGTE_CAMPA(psCodigoPeriodo,-1) --perido anteriorn
			AND A.EST_REGI='1'
 AND A.IND_DESP_CLAS = '0';

 BEGIN

		 OPEN cursorRegistro;
		 LOOP
		 FETCH cursorRegistro BULK COLLECT INTO tablaRegistro LIMIT 1000;
		 IF tablaRegistro.COUNT > 0 THEN
		 FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
		 regRegistro := tablaRegistro(x);
				 lnNumDespacho:= NVL(regRegistro.NUM_DESP,0);

				 IF(lnNumDespacho< NVL(TO_NUMBER(psNumCampMaxDespacho),0)) THEN
 				 		lnNumDespacho:= lnNumDespacho + 1;
				 BEGIN

					 INSERT INTO EDU_HISTO_CLASI_BENEF_DETAL(
					 PAIS_COD_PAIS, EMCO_COD_EMPR_COME, CLAS_COD_CLAS, HBEC_CAM_PROC,
								 CLIE_COD_CLIE, NIV_CAPA_ALCA, CAM_DICT_CURS,
								 EST_RESP_NIVE, IND_ENVI,
					 			 USU_DIGI, FEC_DIGI, EST_REGI, IND_DESP_CLAS,NUM_DESP)
					 VALUES (psCodigoPais, psCodEmpresa, psCodigoClasi, psCodigoPeriodo,
					 regRegistro.CLIE_COD_CLIE, regRegistro.NIV_CAPA_ALCA, regRegistro.CAM_DICT_CURS,
							 regRegistro.EST_RESP_NIVE, 'N',
							 psUsuario, SYSDATE, '1', '0',lnNumDespacho);
					 EXCEPTION
					 --EL REGISTRO YA FUE INSERTADA EN ESE PERIODO
					 WHEN OTHERS THEN NULL;
					 END;

				 END IF;
				END LOOP;
		 END IF;
		 EXIT WHEN cursorRegistro%NOTFOUND;
		 END LOOP;
		 CLOSE cursorRegistro;

 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_ACTUA_NO_DESPA_CLASI: '||ls_sqlerrm);


 END EDU_PR_ACTUA_NO_DESPA_CLASI;


/***************************************************************************
Descripcion : Proceso solo para SICC quese encarga de obtener consultoras con curso costo
					y enviarlas en el periodo actual
Fecha Creacion : 18/07/2008
Autor : Sergio Buchelli
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodigoPeriodo : Codigo Periodo
***************************************************************************/
PROCEDURE EDU_PR_ENVIO_CONSU_COMPR_CURSO
 (psCodigoPais 		 VARCHAR2,
 psCodEmpresa 		 VARCHAR2,
 psCodigoPeriodo 		 VARCHAR2
 )
 IS

 TYPE tRegTemporal IS RECORD (
	 CLIE_COD_CLIE EDU_HISTO_CALIF_APTAS.CLIE_COD_CLIE%TYPE,
	 CCAP_COD_CURS_CAPA		EDU_HISTO_APTAS.CCAP_COD_CURS_CAPA%TYPE,
	 COD_CLAS							EDU_PARAM_CLASI_BENEF_CAPAC.COD_CLAS%TYPE
 );

 TYPE TABLA_TEMPORAL IS TABLE OF tRegTemporal;
 tablaRegistro TABLA_TEMPORAL;

 regRegistro tRegTemporal;

 lsCodClasi								EDU_PARAM_CLASI_BENEF_CAPAC.COD_CLAS%TYPE;

 --excepciones
 cod_clasi_noexist EXCEPTION;
 CURSOR cursorRegistro
 IS --Obtine aquellas consultoras con curso costo de campaña anterior
	 SELECT 		A.CLIE_COD_CLIE,
						 		A.CCAP_COD_CURS_CAPA,
								 NVL((select t.cod_clas from edu_param_clasi_benef_capac t where
 							t.pais_cod_pais = a.pais_cod_pais and
 							t.emco_cod_empr_come = a.emco_cod_empr_come and
 							t.niv_capa_alca = a.ccap_cod_curs_capa and
 							t.tip_clas = 'I' and
 							t.tip_curs = 'C' and
 							t.est_clas = 'A' and
 							t.est_regi != 9 ),' ') as CODCLAS
					 FROM EDU_HISTO_APTAS A , EDU_PARAM_CURSO_CAPAC C,
						 	 EDU_TMP_PEDID_CONSU E
					 WHERE
							A.PAIS_COD_PAIS = psCodigoPais
							AND A.EMCO_COD_EMPR_COME = psCodEmpresa
							AND (A.EST_CAPA='PO' OR A.EST_CAPA='FC')
							AND C.TICC_COD_TIPO_COST_CURS = '02'--CURSO CON COSTO
							AND EDU_PKG_PROCE_COMER.GEN_FN_DEVUE_NSGTE_CAMPA(psCodigoPeriodo,DECODE(1,C.NUM_CAMP_PREV_CALI,0,-1))=
									EDU_PKG_PROCE_COMER.GEN_FN_DEVUE_NSGTE_CAMPA(C.CAM_INIC,-1*TO_NUMBER(C.NUM_CAMP_PREV_CALI))
							AND A.PAIS_COD_PAIS = C.PAIS_COD_PAIS
							AND A.EMCO_COD_EMPR_COME = C.EMCO_COD_EMPR_COME
							AND C.COD_CURS_CAPA= A.CCAP_COD_CURS_CAPA
							AND A.EST_REGI='1'

							AND E.COD_PAIS = A.PAIS_COD_PAIS
						 AND E.COD_EMPR_COME = A.EMCO_COD_EMPR_COME
							AND E.COD_CLIE = A.CLIE_COD_CLIE
							AND E.CAM_PROC = psCodigoPeriodo;


 BEGIN

		OPEN cursorRegistro;
		 LOOP
		 FETCH cursorRegistro BULK COLLECT INTO tablaRegistro LIMIT 1000;
		 IF tablaRegistro.COUNT > 0 THEN
		 FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
		 regRegistro := tablaRegistro(x);
		 lsCodClasi := NVL(TRIM(regRegistro.COD_CLAS),'0');

		 --Verificamos clasificacion que exista
		 IF(lsCodClasi IS NOT NULL AND lsCodClasi<>'0' )THEN

		 EDU_PR_INSER_EQUIV_CLASI_TEMPO(psCodigoPais,psCodEmpresa,psCodigoPeriodo,
		 								regRegistro.CLIE_COD_CLIE,regRegistro.CCAP_COD_CURS_CAPA,lsCodClasi,'1');

		 ELSE

		 	RAISE cod_clasi_noexist	;

		 END IF;

				END LOOP;
		 END IF;
		 EXIT WHEN cursorRegistro%NOTFOUND;
		 END LOOP;
		 CLOSE cursorRegistro;


 EXCEPTION

 WHEN cod_clasi_noexist THEN
 	ls_sqlerrm := 'No existe clasificacion de Tipo Invitación para el curso CON COSTO respectivo : ' || regRegistro.CCAP_COD_CURS_CAPA ;
 	RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_ACTUA_ENVIO_COMPR: '||ls_sqlerrm);

 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_ACTUA_ENVIO_COMPR: '||ls_sqlerrm);

 END EDU_PR_ENVIO_CONSU_COMPR_CURSO;

/***************************************************************************
Descripcion : Procedure que Inserta en un Global Tempory aquellas consultoras
										que han comprado el curso
Fecha Creacion : 21/07/2008
Autor : Sergio Buchelli
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
***************************************************************************/
PROCEDURE EDU_PR_INSER_CURSO_FACTU_TEMPO
 (psCodigoPais 		 VARCHAR2,
 psCodEmpresa 		 VARCHAR2
 )
 IS

 TYPE tRegTemporal IS RECORD (
	 CLIE_COD_CLIE EDU_TMP_CONFI_CURSO_COSTO.COD_CLIE%TYPE,
	 CCAP_COD_CURS_CAPA EDU_TMP_CONFI_CURSO_COSTO.COD_CURS_CAPA%TYPE,
	 COD_PERI 	 EDU_TMP_CONFI_CURSO_COSTO.CAM_ACEP%TYPE,
	 FEC_FACT			 EDU_TMP_CONFI_CURSO_COSTO.FEC_FACT%TYPE
 );

 TYPE TABLA_TEMPORAL IS TABLE OF tRegTemporal;
 tablaRegistro TABLA_TEMPORAL;
 regRegistro tRegTemporal;


 	CURSOR curEquivCodigoVenta
 IS
 SELECT CCAP_COD_CURS_CAPA, CAM_FACT, COD_VENT_CUV
 FROM EDU_EQUIV_CODIG_VENTA A
 WHERE A.PAIS_COD_PAIS = psCodigoPais
 	AND A.EMCO_COD_EMPR_COME = psCodEmpresa;


 row_cursor curEquivCodigoVenta%ROWTYPE;

 CURSOR cursorRegistro (lsCodigoCurso VARCHAR2, lsCampFactu VARCHAR2, lsCodigoVenta VARCHAR2)
 IS
 		SELECT DISTINCT GEN_PKG_GENER.GEN_FN_DEVUELVE_COD_CLIE(A.CLIE_OID_CLIE) AS CLIE_OID_CLIE
			 ,lsCodigoCurso,G.COD_PERI,A.FEC_FACT
			FROM PED_SOLIC_CABEC A,
					 PED_SOLIC_POSIC B,
					 PED_TIPO_SOLIC_PAIS C,
					 PED_TIPO_SOLIC D ,
					 CRA_PERIO E,
					SEG_PERIO_CORPO G
			WHERE
			 C.PAIS_OID_PAIS = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais)
			AND	G.COD_PERI = lsCampFactu
			AND  D.COD_TIPO_SOLI = 'SOC'
			AND EXISTS (SELECT CF.FEC_PROC
			 FROM EDU_CONTR_FACTU CF
						WHERE CF.PAIS_COD_PAIS =psCodigoPais
							 AND CF.EMCO_COD_EMPR_COME=psCodEmpresa
							 AND CF.EST_CAMP='1'
							 AND CF.IND_CAMP_ACTI='1'
							 AND CF.COD_PERI = lsCampFactu
							 AND TO_CHAR(CF.FEC_PROC ,'dd/MM/yyyy') = TO_CHAR(A.FEC_PROG_FACT,'dd/MM/yyyy') )
			AND B.VAL_CODI_VENT=lsCodigoVenta
			AND A.GRPR_OID_GRUP_PROC=5
			AND A.ESSO_OID_ESTA_SOLI IN (1,5)
			AND A.OID_SOLI_CABE=B.SOCA_OID_SOLI_CABE
			AND A.TSPA_OID_TIPO_SOLI_PAIS=C.OID_TIPO_SOLI_PAIS
			AND C.TSOL_OID_TIPO_SOLI=D.OID_TIPO_SOLI
			AND C.PAIS_OID_PAIS = E.PAIS_OID_PAIS
			AND A.PAIS_OID_PAIS = C.PAIS_OID_PAIS
			AND G.OID_PERI = E.PERI_OID_PERI
			AND A.PERD_OID_PERI= E.OID_PERI
			AND B.ESPO_OID_ESTA_POSI = '4'
			AND A.FEC_FACT IS NOT NULL;

 BEGIN

 DELETE FROM EDU_GTT_CONFI_CURSO_COSTO;

 OPEN curEquivCodigoVenta; -- open the cursor
 LOOP
 FETCH curEquivCodigoVenta INTO row_cursor ;
 EXIT WHEN curEquivCodigoVenta%notfound; -- exit condition
 BEGIN
		 				--POR CADA CODIGO DE VENTA EQUIVALENTE SE ABRE UN CURSOR CON LAS CONUSLTOARS A INSERTAR EN EL GTT
		 					 OPEN cursorRegistro(row_cursor.CCAP_COD_CURS_CAPA,row_cursor.CAM_FACT,row_cursor.COD_VENT_CUV);
									 LOOP
									 FETCH cursorRegistro BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
									 IF tablaRegistro.COUNT > 0 THEN
									 FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
													 regRegistro := tablaRegistro(x);

													INSERT INTO EDU_GTT_CONFI_CURSO_COSTO (
														COD_PAIS, COD_EMPR_COME, COD_CURS_CAPA,
													 	COD_CLIE, CAM_ACEP, FEC_FACT,
													 	IND_COMP)
													VALUES ( psCodigoPais,psCodEmpresa ,regRegistro.CCAP_COD_CURS_CAPA,regRegistro.CLIE_COD_CLIE,
																	regRegistro.COD_PERI ,regRegistro.FEC_FACT,'1');

												END LOOP;
										 END IF;
									EXIT WHEN cursorRegistro%NOTFOUND;
									END LOOP;
									CLOSE cursorRegistro;

 END ;
 END LOOP;
 CLOSE curEquivCodigoVenta;

 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_INSER_CURSO_FACTU_TEMPO: '||ls_sqlerrm);


 END EDU_PR_INSER_CURSO_FACTU_TEMPO;

/***************************************************************************
Descripcion : Procedure que Actualiza aquellas consultoras que no han comprado el curso
 				 	dependiendo del numero previo de campaña para la calificacion donde sea 1
Fecha Creacion : 24/09/2009
Autor : Sergio Buchelli Silva
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodigoPeriodo : Codigo Periodo
 psUsuario : Codigo de Usuario
***************************************************************************/

PROCEDURE EDU_PR_ACTUA_CONSU_PREV_CAMPA
 (psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psUsuario VARCHAR2
 )
 AS

 -- consultotras que pasaron pedidos pero no compraron el curso y su num previo de calificacion es 1
 CURSOR curUPDAptas
 IS
 	 	SELECT A.CLIE_COD_CLIE ,A.CCAP_COD_CURS_CAPA
		FROM EDU_HISTO_APTAS A , EDU_PARAM_CURSO_CAPAC B
		WHERE A.PAIS_COD_PAIS=psCodigoPais
			 AND A.EMCO_COD_EMPR_COME=psCodEmpresa
			 AND A.PAIS_COD_PAIS = B.PAIS_COD_PAIS
			 AND A.EMCO_COD_EMPR_COME = B.EMCO_COD_EMPR_COME
			 AND A.CCAP_COD_CURS_CAPA = B.COD_CURS_CAPA
			 AND A.EST_CAPA = 'PO'
			 AND B.NUM_CAMP_PREV_CALI = 1;


 --Cursosr que obtien las que no han facturado y su num invitacion debe ser cero, si es qes uno el num_invi

	 CURSOR curAptasNoFactu
 IS
 SELECT aptas.pais_cod_pais ,
 aptas.emco_cod_empr_come ,
 aptas.ccap_cod_curs_capa CURSO ,
 APTAS.CLIE_COD_CLIE CLIENTE
 FROM
 EDU_HISTO_APTAS APTAS
 WHERE aptas.pais_cod_pais = psCodigoPais and
 aptas.emco_cod_empr_come = psCodEmpresa and
 APTAS.EST_CAPA='PO' and -- Por Facturar Curso Costo
 aptas.ind_curs_cost = 'S' and -- Curso con costo
 aptas.cam_ulti_cali_apta = psCodigoPeriodo and
 exists (select null from edu_histo_pedid_consu ped where ped.pais_cod_pais = aptas.pais_cod_pais and
 ped.emco_cod_empr_come = aptas.emco_cod_empr_come and aptas.clie_cod_clie = ped.cod_clie and
 ped.cam_proc = aptas.cam_ulti_cali_apta and
 ped.ind_fact = 0 ) ;


TYPE t_codPais IS TABLE OF EDU_HISTO_APTAS.PAIS_COD_PAIS%TYPE ;
TYPE t_codEmp IS TABLE OF EDU_HISTO_APTAS.EMCO_COD_EMPR_COME%TYPE ;
TYPE t_codCurso IS TABLE OF EDU_HISTO_APTAS.CCAP_COD_CURS_CAPA%TYPE ;
TYPE t_codCons IS TABLE OF EDU_HISTO_APTAS.CLIE_COD_CLIE%TYPE ;
TYPE t_campAcep IS TABLE OF EDU_HISTO_APTAS.CAM_ACEP%TYPE ;


 v_codPais t_codPais ;
 v_codEmp t_codEmp ;
 v_codCons t_codCons ;
 v_codCurso t_codCurso;
 v_campAcep t_campAcep ;


 rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;
 v_row_count NUMBER := 0;

begin


 OPEN curAptasNoFactu;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
 FETCH curAptasNoFactu BULK COLLECT INTO
 v_codPais ,
 v_codEmp ,
 v_codCurso ,
 v_codCons LIMIT rows;

 EXIT WHEN v_row_count = curAptasNoFactu%ROWCOUNT;
 v_row_count := curAptasNoFactu%ROWCOUNT;

 -- Bulk bind of data in memory table...
 FORALL i IN 1..v_codPais.count
 UPDATE edu_histo_aptas apt
 SET apt.NUM_INVI = 0,
 apt.ind_curs_fact = 'N',
 apt.fec_modi = sysdate ,
 apt.usu_modi = psUsuario
 where apt.pais_cod_pais = v_codPais(i) and
 apt.emco_cod_empr_come = v_codEmp(i) and
 apt.ccap_cod_curs_capa = v_codCurso(i) and
 apt.clie_cod_clie = v_codCons(i) and
				 apt.CAM_ULTI_CALI_APTA = psCodigoPeriodo and
				 apt.NUM_INVI = 1;

	 --SE ACTUALIZA TB LA CALI
	 FORALL i IN 1..v_codPais.count
 UPDATE edu_histo_calif_aptas apt
 SET apt.NUM_INVI = 0,
 apt.ind_curs_fact = 'N',
 apt.fec_modi = sysdate ,
 apt.usu_modi = psUsuario
 where apt.pais_cod_pais = v_codPais(i) and
 apt.emco_cod_empr_come = v_codEmp(i) and
 apt.ccap_cod_curs_capa = v_codCurso(i) and
 apt.clie_cod_clie = v_codCons(i) and
				 apt.CAM_PROC = psCodigoPeriodo and
				 apt.NUM_INVI = 1;

 END LOOP;
 CLOSE curAptasNoFactu;

 v_row_count:=0;--Inicializando la variable

 OPEN curUPDAptas;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
 FETCH curUPDAptas BULK COLLECT INTO
 v_codCons,v_codCurso
 LIMIT rows;

 EXIT WHEN v_row_count = curUPDAptas%ROWCOUNT;
 v_row_count := curUPDAptas%ROWCOUNT;

 -- Bulk bind of data in memory table...
 FORALL i IN 1..v_codCons.count
 UPDATE edu_histo_aptas apt
 SET
 apt.est_capa = 'PC' , -- Estado Pendiente
		 apt.ind_envi = 'S',
				 apt.ind_curs_fact = 'N',
 apt.fec_modi = sysdate ,
 apt.usu_modi = psUsuario
 where apt.pais_cod_pais = psCodigoPais and
 apt.emco_cod_empr_come = psCodEmpresa and
				 apt.CLIE_COD_CLIE = v_codCons(i) and
				 apt.CCAP_COD_CURS_CAPA = v_codCurso(i);

 END LOOP;
 CLOSE curUPDAptas;

 return ;

 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_ACTUA_CONSU_PREV_CAMPA: '||ls_sqlerrm);

 END EDU_PR_ACTUA_CONSU_PREV_CAMPA;



/***************************************************************************
Descripcion : Procedure que Recepciona los Pedidos no existentes en aptas pero
										que son contantes
Fecha Creacion : 18/09/2008
Autor : Sergio Buchelli
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodigoPeriodo : Codigo de Periodo
 psUsuario : Codigo de Usuario
***************************************************************************/
 PROCEDURE EDU_PR_RECEP_CONSU_SOLIC_CURSO
 (psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psUsuario VARCHAR2
 )
 AS
 CURSOR curINSConsuNoExist
 IS
 SELECT distinct cons.cod_pais ,
 cons.cod_empr_come ,
 cons.cod_clie ,
 cons.cam_proc
 FROM EDU_TMP_CONSU_SOLIC_CURSO cons
 WHERE cons.COD_PAIS = psCodigoPais and
 cons.cod_empr_come = psCodEmpresa and
 cons.cam_proc = psCodigoPeriodo and
 cons.ind_fact = '0' and
 cons.ind_pedi = '2' and -- Extraen los Pedidos desde el Comercial
 not exists ( select nu.cod_clie from edu_histo_pedid_consu nu where nu.pais_cod_pais = psCodigoPais and
 nu.emco_cod_empr_come = psCodEmpresa and
 nu.cod_clie = cons.cod_clie ) ;

 TYPE t_cod_pais IS TABLE OF edu_histo_pedid_consu.pais_cod_pais%TYPE;
 TYPE t_cod_empr_come IS TABLE OF edu_histo_pedid_consu.emco_cod_empr_come%TYPE;
 TYPE t_cod_clie IS TABLE OF edu_histo_pedid_consu.cod_clie%TYPE;
 TYPE t_camp_proc IS TABLE OF edu_histo_pedid_consu.cam_proc%TYPE;

 v_cod_pais t_cod_pais ;
 v_cod_empr_come t_cod_empr_come ;
 v_cod_clie t_cod_clie ;
 v_camp_proc t_camp_proc ;

 rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;
 j BINARY_INTEGER := 0;
 v_row_count NUMBER := 0;
 v_row_count_ins NUMBER := 0;

begin


 OPEN curINSConsuNoExist;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
 FETCH curINSConsuNoExist BULK COLLECT INTO
 v_cod_pais ,
 v_cod_empr_come ,
 v_cod_clie ,
 v_camp_proc LIMIT rows;

 EXIT WHEN v_row_count_ins = curINSConsuNoExist%ROWCOUNT;
 v_row_count_ins := curINSConsuNoExist%ROWCOUNT;

 -- Bulk bind of data in memory table...
 FORALL j IN 1..v_cod_pais.count
	insert into edu_histo_pedid_consu
			 ( pais_cod_pais,
			 emco_cod_empr_come,
			 cod_clie,
			 cam_inic_pedi,
			 cam_ulti_pedi,
			 cam_proc,
			 cod_ulti_nive,
			 num_pedi_fact,
			 ind_pedi,
			 ind_fact,
			 ind_nuev,
			 ind_cons,
			 ind_prim_pedi,
			 usu_digi,
			 fec_digi,
			 usu_modi,
			 fec_modi,
			 est_regi
			 )
			values
			 ( v_cod_pais(j) ,
			 v_cod_empr_come(j) ,
			 v_cod_clie(j) ,
			 v_camp_proc(j) ,
			 v_camp_proc(j) ,
			 v_camp_proc(j) ,
			 '1' , -- consultoras inician en el primer nivel
			 0,
			 1, -- Paso Pedido
			 0,
			 0,
			 0, -- constante
			 1, -- es Primer Pedido
			 psUsuario,
			 sysdate,
			 null,
			 null,
			 1
			 ) ;


 FORALL j IN 1..v_cod_pais.count
 --ACTUALIZAMOS EL TEMPORAL DE SOLICITUD CURSO
 	 update EDU_TMP_CONSU_SOLIC_CURSO
 set FLA_REGI_PEDI='1'--REGISTRADO
 where COD_PAIS = v_cod_pais(j)
 and COD_EMPR_COME = v_cod_empr_come(j)
 and COD_CLIE = v_cod_clie(j);


 END LOOP;

 CLOSE curINSConsuNoExist;

 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_RECEP_CONSU_SOLIC_CURSO: '||ls_sqlerrm);

 END EDU_PR_RECEP_CONSU_SOLIC_CURSO;



/***************************************************************************
Descripcion : Procedure que Recepciona los Primeros de las Cons. No Constantes, que ingresaron a
 				 	educacion como antiguas regulares , y se volviveron no constantes
Fecha Creacion : 06/11/2008
Autor : Sergio Buchelli Silva
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodigoPeriodo : Codigo de Periodo
 psUsuario : Codigo de Usuario
***************************************************************************/

 PROCEDURE EDU_PR_RECEP_PEDID_NOCON_CUV
 (psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psUsuario VARCHAR2
 )
 AS
 CURSOR curUPDConsuNuevas
 IS
 SELECT cons.cod_pais ,
 cons.cod_empr_come ,
 cons.cod_clie ,
 cons.cam_proc
 FROM EDU_TMP_PEDID_CONSU cons
 WHERE cons.COD_PAIS = psCodigoPais and
 cons.cod_empr_come = psCodEmpresa and
 cons.cam_proc = psCodigoPeriodo and
 cons.ind_fact = '0' and
 cons.ind_pedi = '2' and -- Extraen los Pedidos Normales desde el Comercial
 exists ( select null
 from edu_histo_pedid_consu nu
 where nu.pais_cod_pais = psCodigoPais and
 nu.emco_cod_empr_come = psCodEmpresa and
 nu.cod_clie = cons.cod_clie and
 nu.ind_prim_pedi = 1 and -- son primeros pedidos CUV
 -- No pasaron pedido la campanha anterior
 ( ((nu.cam_ulti_pedi <> EDU_PKG_PROCE_COMER.GEN_FN_DEVUE_NSGTE_CAMPA(psCodigoPeriodo,-1)) and (nu.cam_ulti_pedi<>psCodigoPeriodo)) or
 -- pasaron pedido la campanha anterior pero no facturaron
 (nu.cam_ulti_pedi = EDU_PKG_PROCE_COMER.GEN_FN_DEVUE_NSGTE_CAMPA(psCodigoPeriodo,-1) and nu.ind_fact=0 ) or
 -- pasaron pedido la campanha anterior y facturaron pero son no constantes
 (nu.cam_ulti_pedi = EDU_PKG_PROCE_COMER.GEN_FN_DEVUE_NSGTE_CAMPA(psCodigoPeriodo,-1) and nu.ind_fact=1 and nu.ind_nuev=0 and nu.ind_cons=0 )
 )
 ) ;

 TYPE t_cod_pais IS TABLE OF edu_histo_pedid_consu.pais_cod_pais%TYPE;
 TYPE t_cod_empr_come IS TABLE OF edu_histo_pedid_consu.emco_cod_empr_come%TYPE;
 TYPE t_cod_clie IS TABLE OF edu_histo_pedid_consu.cod_clie%TYPE;
 TYPE t_camp_proc IS TABLE OF edu_histo_pedid_consu.cam_proc%TYPE;

 v_cod_pais t_cod_pais ;
 v_cod_empr_come t_cod_empr_come ;
 v_cod_clie t_cod_clie ;
 v_camp_proc t_camp_proc ;

 rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;
 v_row_count NUMBER := 0;

begin

 OPEN curUPDConsuNuevas;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
 FETCH curUPDConsuNuevas BULK COLLECT INTO
 v_cod_pais ,
 v_cod_empr_come ,
 v_cod_clie ,
 v_camp_proc LIMIT rows;

 EXIT WHEN v_row_count = curUPDConsuNuevas%ROWCOUNT;
 v_row_count := curUPDConsuNuevas%ROWCOUNT;

 -- Bulk bind of data in memory table...
 FORALL i IN 1..v_cod_pais.count
 update edu_histo_pedid_consu pedcn
 set pedcn.cam_ulti_pedi = psCodigoPeriodo, -- cambia la recepcion para Campanah de Ultimo Pedido
 pedcn.cam_proc = v_camp_proc(i) ,
 pedcn.cod_ulti_nive = pedcn.num_pedi_fact+1 , -- ultimo nivel = num ped facutrados +1
-- pedcn.num_pedi_fact = 0 ,
 pedcn.ind_pedi = '1' ,
 pedcn.ind_fact = '0' ,
 pedcn.ind_nuev = '0' ,
 pedcn.ind_cons = '0' ,
-- pedcn.ind_prim_pedi = '1' ,
 usu_modi = psUsuario ,
 fec_modi = sysdate
 where pais_cod_pais = v_cod_pais(i)
 and emco_cod_empr_come = v_cod_empr_come(i)
 and cod_clie = v_cod_clie(i);

 END LOOP;
 CLOSE curUPDConsuNuevas;

 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_RECEP_PEDID_NOCON: '||ls_sqlerrm);

 END EDU_PR_RECEP_PEDID_NOCON_CUV;


/***************************************************************************
Descripcion : Procedure que Procesa Actualiza Archivo de Control
Fecha Creacion : 27/02/2009
Autor : Sergio Buchelli Silva
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psUsuario : Codigo de Usuario
***************************************************************************/

 PROCEDURE EDU_PR_ACTUA_CONTR_FACTU
 (psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psUsuario VARCHAR2
 )
 AS


 CURSOR curUPDcontrol
 IS
 select fac.cod_pais ,
 fac.cod_empr_come ,
 fac.cod_peri,
 fac.fec_proc
 from EDU_TMP_CONTR_FACTU fac
 WHERE fac.cod_pais = psCodigoPais and
 fac.cod_empr_come = psCodEmpresa and
 exists ( select nu.cod_peri from edu_contr_factu nu where nu.pais_cod_pais = psCodigoPais and
 nu.emco_cod_empr_come = psCodEmpresa and
 nu.cod_peri = fac.cod_peri );
 TYPE t_cod_pais IS TABLE OF edu_contr_factu.pais_cod_pais%TYPE ;
 TYPE t_cod_emp IS TABLE OF edu_contr_factu.emco_cod_empr_come%TYPE ;
 TYPE t_cod_peri IS TABLE OF edu_contr_factu.cod_peri%TYPE ;
 TYPE t_fec_proc IS TABLE OF edu_contr_factu.fec_proc%TYPE ;

 v_cod_pais t_cod_pais ;
 v_cod_emp t_cod_emp ;
 v_cod_peri t_cod_peri ;
 v_fec_proc t_fec_proc ;

 TYPE edu_factu_tab_t IS TABLE OF edu_contr_factu%ROWTYPE INDEX BY BINARY_INTEGER;
 edu_factu_tab edu_factu_tab_t; -- In-memory table

 v_row_count NUMBER := 0;
 rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;
 v_row_insert number := 0 ;
begin

-- Cerramos todas las campanhas
 UPDATE edu_contr_factu fact
 SET fact.ind_camp_acti = 0 ,
 fact.est_camp = 0 ,
 fact.usu_modi = psUsuario ,
 fact.fec_modi = sysdate
 where fact.pais_cod_pais = psCodigoPais and
 fact.emco_cod_empr_come = psCodEmpresa ;


 OPEN curUPDcontrol;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
 FETCH curUPDcontrol BULK COLLECT INTO
 v_cod_pais ,
 v_cod_emp ,
 v_cod_peri ,
 v_fec_proc LIMIT rows;

 EXIT WHEN v_row_count = curUPDcontrol%ROWCOUNT;
 v_row_count := curUPDcontrol%ROWCOUNT;

 -- Bulk bind of data in memory table...
 FORALL i IN 1..v_cod_pais.count
 UPDATE edu_contr_factu fac
 SET fac.fec_proc = v_fec_proc(i),
 fac.usu_modi = psUsuario ,
 fac.fec_modi = sysdate ,
 fac.ind_camp_acti = 1 ,
 fac.est_camp = 1
 where fac.pais_cod_pais = v_cod_pais(i) and
 fac.emco_cod_empr_come = v_cod_emp(i) and
 fac.cod_peri = v_cod_peri(i) ;
 END LOOP;
 CLOSE curUPDcontrol;


 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_ACTUA_CONTR_FACTU: '||ls_sqlerrm);

 END EDU_PR_ACTUA_CONTR_FACTU;


/***************************************************************************
Descripcion       : Procedure que Recepciona los Pedidos de tods las consultoras ya
                    establecidas se ejecutara luego de cargas las cuv
Fecha Creacion    : 30/01/2012
Autor             : Sergio Buchelli Silva
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psCodigoPeriodo : Codigo de Periodo
            psUsuario : Codigo de Usuario
***************************************************************************/
 PROCEDURE EDU_PR_RECEP_PEDID_CONSU_ESTAB
 (psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psUsuario VARCHAR2
 )
 AS
 CURSOR curINSConsuNoExist
 IS
 SELECT distinct cons.cod_pais ,
 cons.cod_empr_come ,
 cons.cod_clie ,
 cons.cam_proc,
 NVL(X.CAM_INGR_COME,psCodigoPeriodo) CAM_INGR_COME
 FROM EDU_TMP_PEDID_CONSU cons,EDU_MAEST_CLIEN x
 WHERE cons.COD_PAIS = psCodigoPais and
 cons.cod_empr_come = psCodEmpresa and
 cons.cam_proc = psCodigoPeriodo and
 cons.COD_PAIS = x.pais_cod_pais and
 cons.cod_empr_come = X.EMCO_COD_EMPR_COME and
 CONS.COD_CLIE = X.COD_CLIE and
 cons.ind_fact = '0' and
 cons.ind_pedi = '2' and -- Extraen los Pedidos desde el Comercial
 not exists ( select nu.cod_clie from edu_histo_pedid_consu nu where nu.pais_cod_pais = psCodigoPais and
 nu.emco_cod_empr_come = psCodEmpresa and
 nu.cod_clie = cons.cod_clie ) ;

 TYPE t_cod_pais IS TABLE OF edu_histo_pedid_consu.pais_cod_pais%TYPE;
 TYPE t_cod_empr_come IS TABLE OF edu_histo_pedid_consu.emco_cod_empr_come%TYPE;
 TYPE t_cod_clie IS TABLE OF edu_histo_pedid_consu.cod_clie%TYPE;
 TYPE t_camp_proc IS TABLE OF edu_histo_pedid_consu.cam_proc%TYPE;
 --
 TYPE t_camp_ing_comer IS TABLE OF edu_maest_clien.cam_ingr_come%TYPE;

 v_cod_pais t_cod_pais ;
 v_cod_empr_come t_cod_empr_come ;
 v_cod_clie t_cod_clie ;
 v_camp_proc t_camp_proc ;
 v_camp_ing_comer t_camp_ing_comer;

 rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;
 j BINARY_INTEGER := 0;
 v_row_count NUMBER := 0;
 v_row_count_ins NUMBER := 0;

begin

 OPEN curINSConsuNoExist;
 LOOP
 -- Bulk collect data into memory table - X rows at a time
 FETCH curINSConsuNoExist BULK COLLECT INTO
 v_cod_pais ,
 v_cod_empr_come ,
 v_cod_clie ,
 v_camp_proc, v_camp_ing_comer LIMIT rows;

 EXIT WHEN v_row_count_ins = curINSConsuNoExist%ROWCOUNT;
 v_row_count_ins := curINSConsuNoExist%ROWCOUNT;

 -- Bulk bind of data in memory table...
 FORALL j IN 1..v_cod_pais.count
	insert into edu_histo_pedid_consu
			 ( pais_cod_pais,
			 emco_cod_empr_come,
			 cod_clie,
			 cam_inic_pedi,
			 cam_ulti_pedi,
			 cam_proc,
			 cod_ulti_nive,
			 num_pedi_fact,
			 ind_pedi,
			 ind_fact,
			 ind_nuev,
			 ind_cons,
			 ind_prim_pedi,
			 usu_digi,
			 fec_digi,
			 usu_modi,
			 fec_modi,
			 est_regi
			 )
			values
			 ( v_cod_pais(j) ,
			 v_cod_empr_come(j) ,
			 v_cod_clie(j) ,
			 v_camp_ing_comer(j),--v_camp_proc(j) ,
			 v_camp_proc(j) ,
			 v_camp_proc(j) ,
			 ( decode(v_camp_ing_comer(j),v_camp_proc(j),'1',
                edu_pkg_proce_gener.EDU_FN_DEVUE_NUMER_CAMPA(v_camp_ing_comer(j),v_camp_proc(j)))) , -- consultoras inician en el primer nivel
			 ( decode(v_camp_ing_comer(j),v_camp_proc(j),0,
                (edu_pkg_proce_gener.EDU_FN_DEVUE_NUMER_CAMPA(v_camp_ing_comer(j),v_camp_proc(j))-1) )),
			 1, -- Paso Pedido
			 0, -- ind facturado
			 0, --ind nueva
			 1, -- constante
			 0, -- es Primer Pedido
			 psUsuario,
			 sysdate,
			 null,
			 null,
			 1
			 ) ;



 END LOOP;

 CLOSE curINSConsuNoExist;

 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_RECEP_PEDID_CONSU_ESTAB: '||ls_sqlerrm);
END EDU_PR_RECEP_PEDID_CONSU_ESTAB;

END EDU_PKG_PROCE_COMER;
/

