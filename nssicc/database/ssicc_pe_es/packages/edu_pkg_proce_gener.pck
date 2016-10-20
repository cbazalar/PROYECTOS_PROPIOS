create or replace package EDU_PKG_PROCE_GENER is

 -- Author : PEEXTRVELA
 -- Created : 12/09/2007 10:49:34 a.m.
 -- Purpose : Encapsula los procesos generales del Módulo de Educación.

 /* Declaracion de Tipos */
 TYPE TIPOCURSOR IS REF CURSOR;
 Type TCODIGO_REGION IS Table of VARCHAR(4) ;
 Type TCODIGO_ZONA IS Table of VARCHAR(4) ;
 Type TPORCENTAJE_CAMPANNA IS Table of VARCHAR(6);


 /* Declaracion de constantes */
 INDICADOR_PRIMER_PEDIDO VARCHAR2(1) := '1';
 INDICADOR_ACTIVO VARCHAR2(1) := '1';
 INDICADOR_PENDIENTE VARCHAR2(2) := 'PC';
 INDICADOR_POR_PROGRAMAR VARCHAR2(2) := 'PP';
 INDICADOR_PROGRAMADA VARCHAR2(2) := 'PR';
 INDICADOR_CAPACITADA VARCHAR2(2) := 'CP';
 INDICADOR_SI VARCHAR2(1) := 'S';
 INDICADOR_NO VARCHAR2(1) := 'N';
 INDICADOR_AM VARCHAR2(2) := 'AM';
 INDICADOR_PM VARCHAR2(2) := 'PM';
 INVITACION_CURSO VARCHAR2(2) := '01';
 INDICADOR_EQUIVALENCIA_SI VARCHAR2(1) := '1';
 INDICADOR_EQUIVALENCIA_NO VARCHAR2(1) := '0';
 SECUENCIA_SIN_CONDICION VARCHAR2(2) := '01';
 RANGO_CAMPANA NUMBER:=18;
 CODIGO_PROGRAMA_CAPACITACION VARCHAR2(2) := '01';
 PARAM_CURSO_NIVEL_ADM_REGION VARCHAR2(2) := '01';
 PARAM_CURSO_NIVEL_ADM_ZONA VARCHAR2(2) := '02';
 PARAM_CURSO_NIVEL_ADM_SECCION VARCHAR2(2) := '03';
 PARAM_CURSO_NIVEL_ADM_TERR VARCHAR2(2) := '04';
 ESTADO_REGISTRO_ACTIVO VARCHAR2(1) := '1';
 ESTADO_REGISTRO_ELIMINADO VARCHAR2(1) := '9';
 SITUACION_PLANILLA_ACTIVO VARCHAR2(1) := 'A';
 SITUACION_PLANILLA_PROCESADO VARCHAR2(1) := 'P';
 LOCAL_FICTICIO VARCHAR2(3) := '999';

 /* Declaracion de Variables */
 ln_sqlcode NUMBER(10);
 ls_sqlerrm VARCHAR2(150);
 W_FILAS NUMBER:=1000;

/***************************************************************************
Descripcion : Devuelve numero de Semana correspondiente a una fecha
Fecha Creacion : 14/01/2007
Autor : Carlos Bazalar
***************************************************************************/
FUNCTION EDU_FN_DEVUE_NUMER_SEMAN
 ( pnAnno NUMBER,
 pnMes NUMBER,
 pnDia NUMBER )
RETURN NUMBER ;


/***************************************************************************
Descripcion : Funcion que devuelve el Pais Datamart en base a los
 parametros ingresados
Fecha Creacion : 17/10/2007
Autor : Carlos Bazalar
Parametros:
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodPrograma : Codigo de Programa
***************************************************************************/
FUNCTION EDU_FN_DEVUE_PAIS_DATAM(
 psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodPrograma VARCHAR2
)
RETURN VARCHAR2;

/***************************************************************************
Descripcion : Funcion que devuelve si el curso tiene ambito por el parametro de
 ambito ingresado
Fecha Creacion : 09/07/2007
Autor : Carlos Bazalar
Parametros:
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodPeriodo : Codigo de Periodo
 psUsuario : Codigo de Ambito
***************************************************************************/
FUNCTION EDU_FN_POSEE_AMBIT_CURSO(
 psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodCurso VARCHAR2,
 psCodAmbito VARCHAR2)
RETURN NUMBER;

/***************************************************************************
Descripcion : Funcion que devuelve Periodo en base al valor de Rango
 ingresado
Fecha Creacion : 09/07/2007
Autor : Carlos Bazalar
Parametros:
 psCodPeriodo : Codigo de Periodo
 pnValor : Rango ingresado a buscar (puede ser negativo o positvo)
***************************************************************************/
FUNCTION EDU_FN_DEVUE_CODIG_PERIO(
 psCodPeriodo VARCHAR2,
 pnValor NUMBER)
RETURN VARCHAR2;

/***************************************************************************
Descripcion : Procedimiento que realiza el proceso de Actualización
 de Planillas Programacion
Fecha Creacion : 08/08/2007
Autor : Carlos Bazalar
Parametros:
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psUsuario : Codigo de Ambito
***************************************************************************/
PROCEDURE EDU_PR_ACTUA_PLANI_PROGR(
 psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psUsuario VARCHAR2
);

/***************************************************************************
Descripcion : Procedimiento que realiza el proceso de Cierre de Campanna
- Actualización de Parametros de curso
- Aumentar Nro de Invitaciones
- Pasar Consultoras a Estado PENDIENTE (PC)
- Bloqueo de Conusltoras al Cierre de Campaña
Fecha Creacion : 08/08/2007
Autor : Carlos Bazalar
Parametros:
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodPeriodo : Codigo de Periodo
 psUsuario : Usuario
***************************************************************************/
PROCEDURE EDU_PR_PROCE_CIERR_CAMPA(
 psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodPeriodo VARCHAR2,
 psUsuario VARCHAR2
);

/***************************************************************************
Descripcion : Procedimiento que cambiar el estado de consultoras
 que no asistieron al curso de Programadas a Pendientes
 (Proceso de cierre de Campaña)
Fecha Creacion : 16/01/2008
Autor : Carlos Bazalar
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodPeriodo : Campaña de Proceso
 psUsuario : Usuario
***************************************************************************/
PROCEDURE EDU_PR_PROCE_CONSU_PROGR_PENDI(
 psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodPeriodo VARCHAR2,
 psUsuario VARCHAR2
);

/***************************************************************************
Descripcion : Procedimiento que cambiar el estado de consultoras
 que no asistieron al curso de Programadas a Pendientes
 (PROCESO DE CIERRE DE REGION)
Fecha Creacion : 16/01/2008
Autor : Carlos Bazalar
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodPeriodo : Campaña de Proceso
 psCodProceso : Codigo de Proceso
 psCodParam : Codigo de Parametro
 psUsuario : Usuario
***************************************************************************/
PROCEDURE EDU_PR_PROCE_CONSU_PROGR_PENDI(
 psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodPeriodo VARCHAR2,
 psCodProceso VARCHAR2,
 psCodParam VARCHAR2,
 psUsuario VARCHAR2
);

/***************************************************************************
Descripcion : Procedimiento que genera lista de status Consultora que luego
 sera enviada al Sistema Comercioa
Fecha Creacion : 19/01/2008
Autor : Carlos Bazalar
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodPeriodo : Campaña de Periodo
 psCodProceso : Codigo de Proceso
 psCodParam : Codigo de Parametro
 psUsuario : Usuario
***************************************************************************/
PROCEDURE EDU_PR_LISTA_STATU_CONSU(
 psCodigoPais VARCHAR2,
 psCodigoEmpresa VARCHAR2,
 psCodigoPeriodo VARCHAR2,
 psCodProceso VARCHAR2,
 psCodParam VARCHAR2
);

/***************************************************************************
Descripcion : Funcion que valida que el codigo de Servicio se encuentre
 dentro del Rango y que otro producto no posea el mismo codigo
Fecha Creacion : 09/07/2007
Autor : Carlos Bazalar
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoEmpresa : Codigo de Empresa
 psCodigoCurso : Codigo de Curso de Capacitacion
 psCodigoPrograma : Codigo de Programa
 psCodigoServicio : Codigo de Servicio
Retorno :
 1 OK
 -1 Codigo de Servicio fuera de Rango
 -2 Codigo de Servicio se encuentra registrado en otro producto
***************************************************************************/
FUNCTION EDU_FN_VALID_RANGO_CODIG_SERVI(
 psCodigoPais VARCHAR2,
 psCodigoEmpresa VARCHAR2,
 psCodigoCurso VARCHAR2,
 psCodigoPrograma VARCHAR2,
 psCodigoServicio VARCHAR2)
RETURN NUMBER;


/***************************************************************************
Descripcion : Funcion que devuelve el codigo de Region para una instructura
Fecha Creacion : 21/07/2007
Autor : Carlos Bazalar
Parametros:
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodInstructora : Codigo de Instructora
***************************************************************************/
FUNCTION EDU_FN_DEVUE_REGIO_INSTR(
 psCodPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodInstructora VARCHAR2)
RETURN VARCHAR2;

/***************************************************************************
Descripcion : Procedimiento que efectua el Proceso de Cierre de
 Cursos Dictados
 (Proceso de CIERRE DE REGION)
Fecha Creacion : 20/09/2007
Autor : Carlos Bazalar
Parametros:
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodPeriodo : Codigo de Periodo
 psCodProceso : Codigo de Proceso
 psCodProceso : Codigo de Parametro
 psUsuario : Usuario
***************************************************************************/
PROCEDURE EDU_PR_CIERR_CURSO_DICTA(
 psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodPeriodo VARCHAR2,
 psCodProceso VARCHAR2,
 psCodParam VARCHAR2,
 psUsuario VARCHAR2
);

/***************************************************************************
Descripcion : Procedimiento que efectua el Proceso de Cierre de
 Cronograma Dictado para la campaña de Proceso
 (Proceso de CIERRE DE REGION)
Fecha Creacion : 11/12/2007
Autor : Carlos Bazalar
Parametros:
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodPeriodo : Codigo de Periodo
 psCodProceso : Codigo de Proceso
 psCodParametro: Codigo de Parametro
 psUsuario : Usuario
***************************************************************************/
PROCEDURE EDU_PR_CIERR_CRONO_DICTA(
 psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodPeriodo VARCHAR2,
 psCodProceso VARCHAR2,
 psCodParam VARCHAR2,
 psUsuario VARCHAR2
);


/***************************************************************************
Descripcion : Procedimiento previo que llena la informacion de Objetivos
 de Asistencia en la tabla temporal para luego ser displayado
Fecha Creacion : 07/09/2007
Autor : Carlos Bazalar
Parametros:
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodCurso : Codigo de Curso
 psCodAnno : Año
 psCodRegion : Codigo de Region
 psTipo : 'P': Pais 'R' : Region 'Z': Zona
***************************************************************************/
PROCEDURE EDU_PR_INSER_OBJET_ASIST_TEMPO(
 psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodCurso VARCHAR2,
 psCodAnno VARCHAR2,
 psCodRegion VARCHAR2,
 psTipo VARCHAR2,
 psUsuario VARCHAR2
);

/***************************************************************************
Descripcion : Procedimiento principal que actualiza los porcentajes de
 objetivos de asistencia en las tablas respectivas
Fecha Creacion : 10/09/2007
Autor : Carlos Bazalar
Parametros:
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodCurso : Codigo de Curso
 psCodAnno : Año
 psTipo : 'P': Pais 'R' : Region 'Z': Zona
 psUsuario : Usuario
 psCodRegion : Codigo de Region
 psCodZona : Codigo de Zona
 psPorcentaje01 : % Campaña C01
 psPorcentaje02 : % Campaña C02
 psPorcentaje03 : % Campaña C03
 ...
 psPorcentaje18 : % Campaña C18
 psPorcentajeTotal : % Campaña Total
***************************************************************************/
PROCEDURE EDU_PR_ACTUA_OBJET_ASIST(
 psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodCurso VARCHAR2,
 psCodAnno VARCHAR2,
 psTipo VARCHAR2,
 psUsuario VARCHAR2,
 psCodRegion VARCHAR2,
 psCodZona VARCHAR2,
 psPorcentaje01 VARCHAR2,
 psPorcentaje02 VARCHAR2,
 psPorcentaje03 VARCHAR2,
 psPorcentaje04 VARCHAR2,
 psPorcentaje05 VARCHAR2,
 psPorcentaje06 VARCHAR2,
 psPorcentaje07 VARCHAR2,
 psPorcentaje08 VARCHAR2,
 psPorcentaje09 VARCHAR2,
 psPorcentaje10 VARCHAR2,
 psPorcentaje11 VARCHAR2,
 psPorcentaje12 VARCHAR2,
 psPorcentaje13 VARCHAR2,
 psPorcentaje14 VARCHAR2,
 psPorcentaje15 VARCHAR2,
 psPorcentaje16 VARCHAR2,
 psPorcentaje17 VARCHAR2,
 psPorcentaje18 VARCHAR2,
 psPorcentajeTotal VARCHAR2
);


/***************************************************************************************************
 Descripcion : Procedimiento que realiza la generaciòn de planillas de
 las consultoras
Fecha Creacion : 12/09/2007
Fecha Modificacion : 02/10/2007
Parametros Entrada : psCodigoPais : Codigo del Pais del Usuario Logueado (ejm: PE es de PERU)
 : psCodEmpresa : Código de Empresa del Usuario Logeado
 : psUsuario : Código de Usuario Logeado
 : psTipoProceso: N Proceso Normal R:Reproceso
 : psCodProceso : Código de Proceso
 : psCodParam : Código de Parámetro

Parametros Salida : psMensajeError: Mensaje de Error

Autor : Robinson Vela Bardales - rvela@belcorp.biz
Version : Final (Beta|Final)
Cambios Importantes :
****************************************************************************************************/
 PROCEDURE EDU_PR_GENER_PLANI_PROGR(
 psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psUsuario VARCHAR2,
 psTipoProceso VARCHAR2, /*N Proceso Normal R:Reproceso*/
 psCodProceso VARCHAR2,
 psCodParam VARCHAR2,
 psMensajeError OUT VARCHAR2
 );

/***************************************************************************
Descripcion : Funcion que devuelve la cantidad de consultoras para una
 determinada planilla de una región.
Fecha Creacion : 31/10/2007
Autor : Robinson Vela Bardales
Parametros:
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodRegion : Codigo de Region
 psCodZona : Codigo de Zona (Opcional)
 psCampanha : Campaña de Proceso
***************************************************************************/
FUNCTION EDU_FN_CANTI_PLANI_REGION(
 psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodRegion VARCHAR2,
 psCodZona VARCHAR2,
 psCampanha VARCHAR2
)
RETURN NUMBER;

/***************************************************************************
Descripcion : Proceso que cambia el código del cliente, se requiere el
 código nuevo y el antiguo. se transfieren todos los movimientos
 encontrados del antiguo al cliente nuevo.
 :Las Tablas Relacionadas son:
EDU_CONSU_EXONE_CAPAC
EDU_HISTO_APTAS
EDU_HISTO_CAPAC_CABEC
EDU_HISTO_CAPAC_DETAL
EDU_HISTO_CLASI_BENEF_DETAL
EDU_HISTO_CURSO_DICTA_DETAL
EDU_PARAM_CURSO_DEMAN_CLIEN
EDU_PLANI_PROGR_CURSO
EDU_HISTO_PEDID_CONSU

Fecha Creacion : 31/10/2007
Autor : Robinson Vela Bardales
Parametros:
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psUsuario : Usuario de Proceso
 psClienteOld : Código del Cliente Antiguo
 psClienteNew : Código Nuevo del Cliente
Parametros Salida : psMensajeError: Mensaje de Error
***************************************************************************/

PROCEDURE EDU_PR_RECOD_CONSU(
 psCodigoPais VARCHAR2,
 psCodigoEmpresa VARCHAR2,
 psUsuario VARCHAR2,
 psClienteOld VARCHAR2,
 psClienteNew VARCHAR2,
 psMensajeError OUT VARCHAR2
 );


/***************************************************************************
Descripcion : Proceso que se encarga de efectuar la eliminacion de asitencia
 de una consultora a un curso de capacitacion
Fecha Creacion : 27/11/2007
Autor : Sergio Buchelli Silva
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoEmpresa : Codigo de Empresa
 psCodigoCurso : Codigo de curso
 psCodigoPlanilla : Codigo de Planilla
 psCodigoConsultora : Codigo de Consultora
 psUsuario : Usuario de Proceso
Parametros Salida : psMensajeError: Mensaje de Error
***************************************************************************/

PROCEDURE EDU_PR_ELIMI_ASIST(
 psCodigoPais VARCHAR2,
 psCodigoEmpresa VARCHAR2,
 psCodigoCurso VARCHAR2,
 psCodigoPlanilla VARCHAR2,
 psCodigoConsultora VARCHAR2,
 psUsuario VARCHAR2,
 psMensajeError OUT VARCHAR2
 );



/***************************************************************************
Descripcion : Proceso que se encarga de efectuar la confirmacion de asitencia
 de una consultora a un curso de capacitacion
Fecha Creacion : 27/11/2007
Autor : Sergio Buchelli Silva
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoEmpresa : Codigo de Empresa
 psCodigoCurso : Codigo de curso
 psCodigoPlanilla : Codigo de Planilla
 psCodigoConsultora : Codigo de Consultora
 psUsuario : Usuario de Proceso
Parametros Salida : psMensajeError: Mensaje de Error
***************************************************************************/

PROCEDURE EDU_PR_CONFI_ASIST(
 psCodigoPais VARCHAR2,
 psCodigoEmpresa VARCHAR2,
 psCodigoCurso VARCHAR2,
 psCodigoPlanilla VARCHAR2,
 psCodigoConsultora VARCHAR2,
 psUsuario VARCHAR2,
 psMensajeError OUT VARCHAR2
 );

/***************************************************************************
Descripcion : Procedimiento que realiza el Proceso de Bloqueo de Consultoras
 al Cierre de Campaña
Fecha Creacion : 03/12/2007
Autor : Carlos Bazalar
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodPeriodo : Campaña de Proceso
 psUsuario : Usuario
***************************************************************************/
PROCEDURE EDU_PR_PROCE_BLOQU_CONSU_CAMPA(
 psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodPeriodo VARCHAR2,
 psUsuario VARCHAR2
);

/***************************************************************************
Descripcion : Procedimiento que efectua la generación del Reporte
 de Cronograma Dictado
Fecha Creacion : 11/12/2007
Autor : Carlos Bazalar
Parametros:
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodPeriodo : Codigo de Periodo
 psCodRegion : Codigo de Region
		 psCodZona : Codigo de Zona
 psUsuario : Usuario
***************************************************************************/
PROCEDURE EDU_PR_GENER_REPOR_CRONO_DICTA(
 psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodPeriodo VARCHAR2,
 psCodRegion VARCHAR2,
 psCodZona			VARCHAR2,
 psUsuario VARCHAR2
);

/***************************************************************************
Descripcion : Funcion que devuelve las zonas corresponidnete al
 Oid de Cronograma dictado ingresado como parametro
Fecha Creacion : 13/12/2007
Autor : Carlos Bazalar
Modificado		 : Sergio Buchelli
Parametros:
 pnOidCronograma : Oid Cronograma Dictado
		 pscodzona		 : Codigo Zona
***************************************************************************/
FUNCTION EDU_FN_DEVUE_CRONO_DICTA_ZONA(
 pnOidCronograma NUMBER,
 pscodzona VARCHAR2
)
RETURN VARCHAR2;

/***************************************************************************
Descripcion : Funcion que devuelve la descripcion de la Sala
Fecha Creacion : 18/12/2007
Autor : Carlos Bazalar
Parametros:
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodLocal : Codigo de Local
 psCodSala : Codigo de Sala
***************************************************************************/
FUNCTION EDU_FN_DEVUE_DESCR_SALA(
 psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodLocal VARCHAR2,
 psCodSala VARCHAR2
)
RETURN VARCHAR2;

/***************************************************************************
Descripcion : Funcion que devuelve una cadena concatenada
Fecha Creacion : 13/12/2007
Autor : Carlos Bazalar
Parametros:
 psCadenaOrigen Cadena Origen
 psCadenaConcatenar Cadena que se desea concatenar a la Cadena Origen
 psSeparador Separador entre la Cadena Origen y lo que se desea concatenar
***************************************************************************/
FUNCTION EDU_FN_CONCA_CADEN(
 psCadenaOrigen VARCHAR2,
 psCadenaConcatenar VARCHAR2,
 psSeparador VARCHAR2
)
RETURN VARCHAR2;


/***************************************************************************
Descripcion : Funcion que devuelve numero de campanhas entre periodos
Fecha Creacion : 10/01/2008
Autor : Sergio Buchelli
Parametros:
 psCodPeriodoInicial Codigo Periodo Inicial
 psCodPeriodoFinal Codigo Perido Final

***************************************************************************/
FUNCTION EDU_FN_DEVUE_NUMER_CAMPA(
 psCodPeriodoInicial VARCHAR2,
 psCodPeriodoFinal VARCHAR2
)
RETURN NUMBER;


/***************************************************************************
Descripcion : Funcion que devuelve numero de invitaciones
Fecha Creacion : 11/01/2008
Autor : Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoEmpresa : Codigo de Empresa
 psCodigoCurso : Codigo de Curso

***************************************************************************/
FUNCTION EDU_FN_DEVUE_NUMER_INVIT(
 psCodigoPais VARCHAR2,
 psCodigoEmpresa VARCHAR2,
 psCodigoCurso VARCHAR2
)
RETURN NUMBER;

/***************************************************************************
Descripcion : Proceso que se encarga de validar el numero de invitaciones
 en una campanha de proceso con estado pendiente de capacitación
Fecha Creacion : 10/01/2008
Autor : Sergio Buchelli Silva
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoEmpresa : Codigo de Empresa
 psSentencia : Sentencia
 psUsuario : Usuario de Proceso
Parametros Salida : psMensajeError: Mensaje de Error
***************************************************************************/

PROCEDURE EDU_PR_VALID_INVIT_PENDI_CAPAC(
 psCodigoPais VARCHAR2,
 psCodigoEmpresa VARCHAR2,
 psSentencia VARCHAR2,
 psCampInicial VARCHAR2,
 psUsuario VARCHAR2,
 psMensajeError OUT VARCHAR2
 );


/***************************************************************************
Descripcion : Proceso que se encarga de recorrer la tabla de query
 y ejecutarlos dinamicamente
Fecha Creacion : 11/01/2008
Autor : Sergio Buchelli Silva
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoEmpresa : Codigo de Empresa
 psCampanha : Campanha Inicial
 psUsuario : Usuario de Proceso
Parametros Salida : psMensajeError: Mensaje de Error
***************************************************************************/

PROCEDURE EDU_PR_PROC_QUERY(
 psCodigoPais VARCHAR2,
 psCodigoEmpresa VARCHAR2,
 psCampanha VARCHAR2,
 psUsuario VARCHAR2,
 psMensajeError OUT VARCHAR2
 );


/***************************************************************************
Descripcion : Proceso que se encarga de validar que sólo para el proceso
 de campanha actual se encuentren en estado 'PR '

Fecha Creacion : 10/01/2008
Autor : Sergio Buchelli Silva
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoEmpresa : Codigo de Empresa
 psSentencia : Sentencia
 psCampanha : Campanha Inicial
 psUsuario : Usuario de Proceso
Parametros Salida : psMensajeError: Mensaje de Error
***************************************************************************/

PROCEDURE EDU_PR_VALID_PROGR(
 psCodigoPais VARCHAR2,
 psCodigoEmpresa VARCHAR2,
 psSentencia VARCHAR2,
 psCampanha VARCHAR2,
 psUsuario VARCHAR2,
 psMensajeError OUT VARCHAR2
 );


 /***************************************************************************
Descripcion : Proceso que se encarga de validar los registros
 Bloqueados

Fecha Creacion : 15/01/2008
Autor : Sergio Buchelli Silva
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoEmpresa : Codigo de Empresa
 psCampanha : Campanha Inicial
 psSentencia : Setencia,
 psUsuario : Usuario de Proceso
Parametros Salida : psMensajeError: Mensaje de Error
***************************************************************************/

PROCEDURE EDU_PR_VALID_BLOQU(
 psCodigoPais VARCHAR2,
 psCodigoEmpresa VARCHAR2,
 psSentencia VARCHAR2,
 psCampanha VARCHAR2,
 psUsuario VARCHAR2,
 psMensajeError OUT VARCHAR2
 );


 /***************************************************************************
Descripcion : Proceso que se encarga de validar el estado
 de las consultoras por curso
Fecha Creacion : 15/01/2008
Autor : Sergio Buchelli Silva
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoEmpresa : Codigo de Empresa
 psCampanha : Campanha Inicial
 psSentencia : Setencia,
 psUsuario : Usuario de Proceso
Parametros Salida : psMensajeError: Mensaje de Error
***************************************************************************/

PROCEDURE EDU_PR_VALID_ESTAD_CONSU(
 psCodigoPais VARCHAR2,
 psCodigoEmpresa VARCHAR2,
 psSentencia VARCHAR2,
 psCampanha VARCHAR2,
 psUsuario VARCHAR2,
 psMensajeError OUT VARCHAR2
 );

/***************************************************************************
Descripcion : Funcion que devuelve Codigo de Mensaje en base a los
 parametros ingresados
Fecha Creacion : 17/10/2007
Autor : Carlos Bazalar
Parametros:
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psIndicadorEqui: 1: Obtener codigo de equivalencia de Mensaje
 0: Obtener codigo de mensaje
 psTipoMensaje: 1: Mensaje General
 2: Mensaje Especifico
 psCodCurso: Codigo de curso
 psEstadoCapa: Estado de Capacitacion
 pnOpcionCapa: Opcion de Capaciotacion (Nivel)
 psCodClien: Codigo de Cliente
***************************************************************************/
FUNCTION EDU_FN_DEVUE_CODIG_MENSA(
 psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psIndicadorEqui VARCHAR2,
 psTipoMensaje VARCHAR2,
 psCodCurso VARCHAR2,
 psEstadoCapa VARCHAR2,
 pnOpcionCapa NUMBER,
 psCodClien VARCHAR2
)
RETURN VARCHAR2;

/***************************************************************************
Descripcion : Funcion que devuelve Tipo de Clasificacion Equivalencia
Fecha Creacion : 17/10/2007
Autor : Carlos Bazalar
Parametros:
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodClas : Codigo de Clasificacion
***************************************************************************/
FUNCTION EDU_FN_DEVUE_TIPO_EQUIV_CLASI(
 psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodClasi VARCHAR2
)
RETURN VARCHAR2;

/***************************************************************************
Descripcion : Funcion que devuelve Codigo de Clasificacion Equivalencia
Fecha Creacion : 17/10/2007
Autor : Carlos Bazalar
Parametros:
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodClas : Codigo de Clasificacion
***************************************************************************/
FUNCTION EDU_FN_DEVUE_CODIG_EQUIV_CLASI(
 psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodClasi VARCHAR2
)
RETURN VARCHAR2;

/***************************************************************************
Descripcion : Funcion que devuelve el Nombre de la Consultora
 en base al indicador de Nombre completo
Fecha Creacion : 25/01/2008
Autor : Carlos Bazalar
Parametros:
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodClien : Codigo de Cliente
 psIndicadorNombreCompleto: Indicador de Nombre Completo
***************************************************************************/
FUNCTION EDU_FN_DEVUE_NOMBR_CONSU(
 psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodCliente VARCHAR2,
 psIndicadorNombreCompleto VARCHAR2
)
RETURN VARCHAR2;

/***************************************************************************
Descripcion : Funcion que devuelve Nivel Actual de la consultora
Fecha Creacion : 29/01/2008
Autor : Carlos Bazalar
Parametros:
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodClien : Codigo de Cliente
***************************************************************************/
FUNCTION EDU_FN_DEVUE_NIVEL_ACTUA_CONSU(
 psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodClien VARCHAR2
)
RETURN VARCHAR2;

/***************************************************************************
Descripcion : Funcion que devuelve Nivel Siguiente
Fecha Creacion : 29/01/2008
Autor : Carlos Bazalar
Parametros:
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodPrograma : Codigo de Programa
 psCodCurso : Codigo de Curso
***************************************************************************/
FUNCTION EDU_FN_DEVUE_NIVEL_SIGTE(
 psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodPrograma VARCHAR2,
 psCodCurso VARCHAR2
)
RETURN VARCHAR2;

/***************************************************************************
Descripcion : Funcion que devuelve Codigo de Clasificacion en base a los
 parametros ingresados
Fecha Creacion : 06/03/2008
Autor : Carlos Bazalar
Parametros:
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodCurso : Codigo de curso
 psTipoClasi : Tipo de Clasificacion (I: Invitacion, B: Beneficio)
 psTipoCurso : Tipo de Curso (S: Sin costo, C: Con costo)
***************************************************************************/
FUNCTION EDU_FN_DEVUE_CODIG_CLASI(
 psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodCurso VARCHAR2,
 psTipoClasi VARCHAR2,
 psTipoCurso VARCHAR2
)
RETURN VARCHAR2;


/***************************************************************************
Descripcion : Proceso que se encarga de validar que consultoras no se le ha registrado asitencia

Fecha Creacion : 02/02/2008
Autor : Sergio Buchelli Silva
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoEmpresa : Codigo de Empresa
 psCampanha : Campanha Inicial
 psSentencia :Setencia,
 psUsuario : Usuario de Proceso
Parametros Salida : psMensajeError: Mensaje de Error
***************************************************************************/

PROCEDURE EDU_PR_VALID_REGIS_ASIST(
 psCodigoPais VARCHAR2,
 psCodigoEmpresa VARCHAR2,
 psSentencia VARCHAR2,
 psCampanha VARCHAR2,
 psUsuario VARCHAR2,
 psMensajeError OUT VARCHAR2
 );


 /***************************************************************************************************
 Descripcion : Procedimiento que genera en una tabla temporal el resumen de programadas en planillaa
 las consultoras
 Fecha Creacion : 05/03/2008
 Parametros Entrada : psCodigoPais : Codigo del Pais del Usuario Logueado (ejm: PE es de PERU)
 : psCodEmpresa : Código de Empresa del Usuario Logeado
 : psCampanha : Campanha de Proceso
 : psCodigoRegion: Código de Region
 Autor : Sergio Buchelli Silva - sbuchelli@belcorp.biz
 Version : Final (Beta|Final)
 Cambios Importantes :
 ****************************************************************************************************/

 PROCEDURE EDU_PR_GENER_RESUM_PROGR_PLANI
 (
 psCodigoPais VARCHAR2,
 psCodigoEmpresa VARCHAR2,
 psCampanha VARCHAR2,
 psCodigoRegion VARCHAR2,
 psCodigoZona VARCHAR2
 );


/***************************************************************************************************
 Descripcion : Procedimiento que registra Planillas no procesadas
 Fecha Creacion : 14/03/2008
 Parametros Entrada : psCodPais : Codigo del Pais del Usuario
 : psCodEmpresa : Código de Empresa del Usuario Logeado
 : psCodPeriodo : Campanha de Proceso
 : psCodRegion : Código de Region
 Parametro Salida : psMensajeError :Mensaje error
 Autor : Carlos Bazalar
****************************************************************************************************/
PROCEDURE EDU_PR_REGIS_PLANI_NPROG (
 psCodPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodPeriodo VARCHAR2,
 psCodRegion VARCHAR2,
 psUsuario VARCHAR2,
	psMensajeError OUT VARCHAR2
);

/***************************************************************************
Descripcion : Funcion que devuelve el siguiente Codigo de Curso Dictado
Fecha Creacion : 17/03/2008
Autor : Carlos Bazalar
Parametros:
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodCurso : Codigo de curso
***************************************************************************/
FUNCTION EDU_FN_SIGTE_CODIG_CURSO_DICTA(
 psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodCurso VARCHAR2
)
RETURN NUMBER;


/***************************************************************************
Descripcion : Proceso que se encarga de validar que no existan pendientes de facturar

Fecha Creacion : 24/03/2008
Autor : Sergio Buchelli Silva
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoEmpresa : Codigo de Empresa
 psCampanha : Campanha Inicial
 psSentencia :Setencia,
 psUsuario : Usuario de Proceso
Parametros Salida : psMensajeError: Mensaje de Error
***************************************************************************/

PROCEDURE EDU_PR_VALID_ESTAD_PENDI_FACTU(
 psCodigoPais VARCHAR2,
 psCodigoEmpresa VARCHAR2,
 psSentencia VARCHAR2,
 psCampanha VARCHAR2,
 psUsuario VARCHAR2,
 psMensajeError OUT VARCHAR2
 );


/***************************************************************************
Descripcion : Proceso que se encraga d evalidar que no exista pendiente de programar para
 	 planillas que ya han sido generadas

Fecha Creacion : 24/03/2008
Autor : Sergio Buchelli Silva
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoEmpresa : Codigo de Empresa
 psCampanha : Campanha Inicial
 psSentencia :Setencia,
 psUsuario : Usuario de Proceso
Parametros Salida : psMensajeError: Mensaje de Error
***************************************************************************/

PROCEDURE EDU_PR_VALID_ESTAD_PENDI_PROGA(
 psCodigoPais VARCHAR2,
 psCodigoEmpresa VARCHAR2,
 psSentencia VARCHAR2,
 psCampanha VARCHAR2,
 psUsuario VARCHAR2,
 psMensajeError OUT VARCHAR2
 );

/***************************************************************************
Descripcion : Proceso que se encarga de efectuar el proceso de registro de asistencia
Fecha Creacion : 17/04/2008
Autor : Sergio Buchelli Silva
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoEmpresa : Codigo de Empresa
 psCodigoCurso : Codigo de curso
 psCodigoConsultora : Codigo de Consultora
 psUsuario : Usuario de Proceso
 psCodigoAsistenteCurso : Asistente al Curso
 psCodigoAsistenciaCurso : Asistencia al curso
 psTipoNormal : Indicador de tipo Normal
Parametros Salida : psMensajeError: Mensaje de Error
***************************************************************************/
PROCEDURE EDU_PR_REGIS_ASIST(
 psCodigoPais 		 VARCHAR2,
 psCodigoEmpresa 		 VARCHAR2,
 psCodigoCurso VARCHAR2,
 psCodigoConsultora VARCHAR2,
 psUsuario VARCHAR2,
 psCodigoAsistenteCurso VARCHAR2,
 psCodigoAsistenciaCurso VARCHAR2,
 psTipoNormal VARCHAR2,
 	psMensajeError 		OUT VARCHAR2
 );



/***************************************************************************
Descripcion : Proceso que se encarga de efectuar el proceso de registro de asistencia
				 	en el cierre de una campanha
Fecha Creacion : 17/04/2008
Autor : Sergio Buchelli Silva
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoEmpresa : Codigo de Empresa
Parametros Salida : psMensajeError: Mensaje de Error
***************************************************************************/

PROCEDURE EDU_PR_REGIS_ASIST_CIERR_CAMPA(
 psCodigoPais 		 VARCHAR2,
 psCodigoEmpresa 		 VARCHAR2,
 	psMensajeError 		OUT VARCHAR2
 );


/***************************************************************************
Descripcion : Proceso que se encarga de validar que no existan planillas activas
 cuando se han registrado nuevas planillas para el periodo
 siguiente en la misma region

Fecha Creacion : 23/04/2008
Autor : Rafael Romero
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoEmpresa : Codigo de Empresa
 psCampanha : Campanha Inicial
 psSentencia :Setencia,
 psUsuario : Usuario de Proceso
Parametros Salida : psMensajeError: Mensaje de Error
***************************************************************************/

PROCEDURE EDU_PR_VALID_PLANI_ACTIV(
 psCodigoPais VARCHAR2,
 psCodigoEmpresa VARCHAR2,
 psSentencia VARCHAR2,
 psCampanha VARCHAR2,
 psUsuario VARCHAR2,
 psMensajeError OUT VARCHAR2
 );

/***************************************************************************
Descripcion : Proceso que se encarga de validar que una consultora programada para un curso
 se encuentre tambien en el detalle del curso dictado

Fecha Creacion : 24/04/2008
Autor : Rafael Romero
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoEmpresa : Codigo de Empresa
 psCampanha : Campanha Inicial
 psSentencia :Setencia,
 psUsuario : Usuario de Proceso
Parametros Salida : psMensajeError: Mensaje de Error
***************************************************************************/

PROCEDURE EDU_PR_VALID_PROGR_DETAL_DICTA(
 psCodigoPais VARCHAR2,
 psCodigoEmpresa VARCHAR2,
 psSentencia VARCHAR2,
 psCampanha VARCHAR2,
 psUsuario VARCHAR2,
 psMensajeError OUT VARCHAR2
 );


/***************************************************************************
Descripcion : Funcion que devuelve el codigo de planilla de programacion final
Fecha Creacion : 09/05/2008
Autor : Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodCurso : Codigo de Curso
 psCampanha : Periodo
 psCodRegion : Codigo Region
 psUsuario			: Usuario
***************************************************************************/
FUNCTION edu_fn_devue_codig_plani_final(
 psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodCurso VARCHAR2,
 psCampanha VARCHAR2,
 psCodRegion VARCHAR2,
 psUsuario VARCHAR2
)
RETURN VARCHAR2;

/***************************************************************************
Descripcion : Funcion que devuelve el codigo de instructora dada una region
Fecha Creacion : 09/05/2008
Autor : Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodPeriodo : Codigo de Region
***************************************************************************/
 FUNCTION edu_fn_devue_codig_instr (
 pscodigopais VARCHAR2,
 pscodempresa VARCHAR2,
 pscodregion VARCHAR2
 )
 RETURN VARCHAR2;


/***************************************************************************************************
 Descripcion : Procedimiento que registra Consultoras con Pedidos Extemporaneos
 Fecha Creacion : 22/05/2008
 Parametros Entrada : psCodPais : Codigo del Pais del Usuario
 : psCodEmpresa : Código de Empresa del Usuario Logeado
 : psCodPeriodo : Campanha de Proceso
 : psCodRegion : Código de Region
 Parametro Salida : psMensajeError :Mensaje error
 Autor : Sergio Buchelli
****************************************************************************************************/
PROCEDURE EDU_PR_REGIS_CONSU_PEDID_EXTEM (
 psCodPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodPeriodo VARCHAR2,
 psCodRegion VARCHAR2,
 psUsuario VARCHAR2,
	psMensajeError OUT VARCHAR2
);


/***************************************************************************
Descripcion : Procedimiento que registra las planillas no procesadas
 Determina las regiones y luego hace el cierre de planillas
 en cada region
Fecha Creacion : 27/05/2008
Autor : Rafael Romero
Modificado		 : Sergio Buchelli
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodPeriodo : Codigo de Periodo (Campaña de Proceso)
			psNumPedidoMinimos : Numerod epedidos minimos para procesar la region
			 psUsuario : Código Usuario
 psmensajeerror : Mensaje de retorno
***************************************************************************/
PROCEDURE EDU_PR_REGIS_PLANI_NPROC(
 psCodPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodPeriodo VARCHAR2,
 psNumPedidosMinimo			VARCHAR2,
 psUsuario VARCHAR2,
 psmensajeerror OUT VARCHAR2
);

/***************************************************************************
Descripcion : Proceso previo antes de la regularizacion de asistencia
										se encarga de poner a 'PR' a las consultoras que se quiere regularizar
										estas se encuantran en PC O PR
Fecha Creacion : 05/06/2008
Autor : Sergio Buchelli Silva
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoEmpresa : Codigo de Empresa
 psCodigoCurso : Codigo de Curso
		 psCodigoConsultora : Codigo de Consultora
		 psTipo : 1:Regularizcion 0:Eliminacion de Asistencia
		 psUsuario : Usuario de Proceso
***************************************************************************/

PROCEDURE EDU_PR_PREVI_REGUL_ASIST(
 psCodigoPais VARCHAR2,
 psCodigoEmpresa VARCHAR2,
	psCodigoCurso VARCHAR2,
 psCodigoConsultora VARCHAR2,
	psTipo				VARCHAR2,
 psUsuario VARCHAR2
);

/***************************************************************************
Descripcion : Proceso que se encarga d eactualizar la tabla de benefiicos, con la
				 consultora que se ha regularizado, para la campana de asistencia
Fecha Creacion : 02/0/72008
Autor : Sergio Buchelli Silva
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoEmpresa : Codigo de Empresa
		 psCampanaCapac	 :	Campaña Capacitación
 psCodigoCurso : Codigo de Curso
		 psCodigoConsultora : Codigo de Consultora
	 	 psIndConfirmarAsist : Indicador si e s una confirmacion (1) o eliminacion (0 )
		 					 	de Asistencia,
		 psUsuario : Usuario de Proceso
***************************************************************************/

PROCEDURE EDU_PR_ACTUA_BENEF_CONSU(
 psCodigoPais VARCHAR2,
 psCodigoEmpresa VARCHAR2,
	psCampanaCapac		VARCHAR2,
	psCodigoCurso VARCHAR2,
 psCodigoConsultora VARCHAR2,
	psIndConfirmarAsist VARCHAR2,
 psUsuario VARCHAR2
);

/***************************************************************************
Descripcion : Funcion que devuelve el NOMBRE de instructora dado su codigo
Fecha Creacion : 02/07/2008
Autor : Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodInstructora : Codigo de Instructora
***************************************************************************/
 FUNCTION edu_fn_devue_nombr_instr (
 pscodigopais 	 VARCHAR2,
 pscodempresa 	 VARCHAR2,
 psCodInstructora VARCHAR2
 )
 RETURN VARCHAR2;


/***************************************************************************
Descripcion : Funcion que devuelve la descripcion de la Sala
Fecha Creacion : 02/07/2008
Autor : Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodLocal : Codigo de Local

***************************************************************************/
 FUNCTION edu_fn_devue_descr_local (
 pscodigopais VARCHAR2,
 pscodempresa VARCHAR2,
 pscodlocal VARCHAR2
 )
 RETURN VARCHAR2;

/***************************************************************************
Descripcion : Funcion que devuelve la primera campaña de facturacion
				 de una consultora
Fecha Creacion : 05/09/2008
Autor : Dennys Oliva Iriarte
Parametros:
 psoidpais : OID de Pais
 psoidcliente : OID de Consultora

***************************************************************************/
 FUNCTION edu_fn_devue_prime_campa_factu (
 psoidpais NUMBER,
 psoidcliente NUMBER
 )
 RETURN VARCHAR2;

/***************************************************************************
Descripcion : Proceso que se encarga de efectuar el proceso de exoneracion
Fecha Creacion : 25/09/2008
Autor : Dennys Oliva Iriarte
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoEmpresa : Codigo de Empresa
		 pscodigocliente : Codigo de Consultora
		 pscurso : Curso
		 psusuario : Usuario
Parametros Salida : psMensajeError: Mensaje de Error
***************************************************************************/
 PROCEDURE EDU_PR_EXONE_ASIST (
 pscodigopais VARCHAR2,
 pscodigoempresa VARCHAR2,
	 pscodigocliente VARCHAR2,
	 pscurso VARCHAR2,
	 psusuario VARCHAR2,
 psmensajeerror OUT VARCHAR2
 );

/***************************************************************************
Descripcion : Procedimiento que efectua el Proceso de Cierre de
 Cursos Dictados segun cronograma de regiones que inician facturacion
					para el periodo dado
 (Proceso Calificacion)
Fecha Creacion : 13/11/2008
Autor : Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodPeriodo : Codigo de Periodo
 psUsuario : Usuario
***************************************************************************/
 PROCEDURE EDU_PR_CIERR_CURSO_DICTA(
 pscodigopais VARCHAR2,
 pscodempresa VARCHAR2,
 pscodperiodo VARCHAR2,
 psusuario VARCHAR2
 );


/***************************************************************************
Descripcion : Procedimiento que efectua el Proceso de Cierre de
 Cronograma Dictado para la campaña de Proceso segun cronograma de regiones que inician facturacion
					para el periodo dado
 (Proceso Calificacion)
Fecha Creacion : 13/11/2008
Autor : Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodPeriodo : Codigo de Periodo
 psUsuario : Usuario
***************************************************************************/
 PROCEDURE EDU_PR_CIERR_CRONO_DICTA(
 pscodigopais VARCHAR2,
 pscodempresa VARCHAR2,
 pscodperiodo VARCHAR2,
 psusuario VARCHAR2
 );

/***************************************************************************
Descripcion : Procedimiento que cambiar el estado de consultoras
 que hicieron pedido rezagadoo , no tinen planilla asociada
					de PP a PC
					(Proceso de cierre de Campaña)
Fecha Creacion : 23/02/2009
Autor : Sergio Buchelli
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodPeriodo : Campaña de Proceso
 psUsuario : Usuario
***************************************************************************/
PROCEDURE EDU_PR_PROCE_PENDI_PROGR_PENDI(
 psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodPeriodo VARCHAR2,
 psUsuario VARCHAR2
);

/***************************************************************************
Descripcion : Proceso que se encarga de actualizar la tabla de beneficios (unico y nuevo), con la
				 consultora que se ha regularizado, creando su historial de despachos desde la campana de asistencia
Fecha Creacion : 17/02/2009
Autor : Sergio Buchelli Silva
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoEmpresa : Codigo de Empresa
 psCodigoCurso : Codigo de Curso
		 psCodigoConsultora : Codigo de Consultora
		 psIndConfirmarAsist : Indicador Confirmacion Asistencia
		 psTipoEnvio		 : Tipo Envio
		 psUsuario : Usuario de Proceso
***************************************************************************/

PROCEDURE EDU_PR_REGUL_BENEF_CONSU(
 psCodigoPais VARCHAR2,
 psCodigoEmpresa VARCHAR2,
	psCampanaCapac		VARCHAR2,
	psCodigoCurso VARCHAR2,
 psCodigoConsultora VARCHAR2,
	psIndConfirmarAsist VARCHAR2,
	psTipoEnvio			VARCHAR2,
 psUsuario VARCHAR2
);


/***************************************************************************
Descripcion : Procedimiento que actualiza el estatus de la consultora a
 01:Activa 02:Posible Egreso 03: Egresada
Fecha Creacion : 26/02/2009
Autor : Sergio Buchelli
Parametros :
 psCodigoPais : Codigo de Pais
 psCodEmpresa : Codigo de Empresa
 psCodPeriodo : Campaña de Proceso
 psCodProceso : codigo Proceso
			psCodParam : codigo Parametro
 psUsuario : Usuario
***************************************************************************/
PROCEDURE EDU_PR_PROCE_ACTUA_STATU_CONSU(
 psCodigoPais VARCHAR2,
 psCodEmpresa VARCHAR2,
 psCodPeriodo VARCHAR2,
 psCodProceso VARCHAR2,
 psCodParam VARCHAR2,
 psUsuario VARCHAR2
);

/***************************************************************************
Descripcion : Proceso que se encarga de validar las consultoras
										capacitadas
Fecha Creacion : 12/03/2009
Autor : Sergio Buchelli Silva
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoEmpresa : Codigo de Empresa
 psCampanha : Campanha Inicial
 	 psSentencia :Setencia,
 psUsuario : Usuario de Proceso
Parametros Salida : psMensajeError: Mensaje de Error
***************************************************************************/
 PROCEDURE edu_pr_valid_capac (
 pscodigopais VARCHAR2,
 pscodigoempresa VARCHAR2,
 pssentencia VARCHAR2,
 pscampanha VARCHAR2,
 psusuario VARCHAR2,
 psmensajeerror OUT VARCHAR2
 );

/***************************************************************************
Descripcion : Proceso que se encarga de validar Indicadores Curso Con Costo

Fecha Creacion : 19/03/2009
Autor : Sergio Buchelli Silva
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoEmpresa : Codigo de Empresa
 psCampanha : Campanha Inicial
 	 psSentencia :Setencia,
 psUsuario : Usuario de Proceso
Parametros Salida : psMensajeError: Mensaje de Error
***************************************************************************/
 PROCEDURE edu_pr_valid_indic_curso_costo (
 pscodigopais VARCHAR2,
 pscodigoempresa VARCHAR2,
 pssentencia VARCHAR2,
 pscampanha VARCHAR2,
 psusuario VARCHAR2,
 psmensajeerror OUT VARCHAR2
 );

/***************************************************************************
Descripcion : Proceso que se encarga de validar Invitacion Curso Sin Costo

Fecha Creacion : 25/03/2009
Autor : Sergio Buchelli Silva
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoEmpresa : Codigo de Empresa
 psCampanha : Campanha Inicial
 	 psSentencia :Setencia,
 psUsuario : Usuario de Proceso
Parametros Salida : psMensajeError: Mensaje de Error
***************************************************************************/
 PROCEDURE edu_pr_valid_curso_sin_costo (
 pscodigopais VARCHAR2,
 pscodigoempresa VARCHAR2,
 pssentencia VARCHAR2,
 pscampanha VARCHAR2,
 psusuario VARCHAR2,
 psmensajeerror OUT VARCHAR2
 );


/***************************************************************************
Descripcion : Proceso que se encarga de validar Invitacion Curso Con Costo

Fecha Creacion : 25/03/2009
Autor : Sergio Buchelli Silva
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoEmpresa : Codigo de Empresa
 psCampanha : Campanha Inicial
 	 psSentencia :Setencia,
 psUsuario : Usuario de Proceso
Parametros Salida : psMensajeError: Mensaje de Error
***************************************************************************/
 PROCEDURE edu_pr_valid_curso_con_costo (
 pscodigopais VARCHAR2,
 pscodigoempresa VARCHAR2,
 pssentencia VARCHAR2,
 pscampanha VARCHAR2,
 psusuario VARCHAR2,
 psmensajeerror OUT VARCHAR2
 );

/***************************************************************************
Descripcion : Proceso que se encarga de efectuar el proceso de exoneracion en forma masiva
Fecha Creacion : 14/04/2010
Autor : Sergio Buchelli Silva
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoEmpresa : Codigo de Empresa
		 pscurso : Curso
		 psusuario : Usuario
Parametros Salida : psMensajeError: Mensaje de Error
***************************************************************************/
 PROCEDURE EDU_PR_EXONE_ASIST_MASIV (
 pscodigopais VARCHAR2,
 pscodigoempresa VARCHAR2,
	 pscurso VARCHAR2,
	 psusuario VARCHAR2,
 psmensajeerror OUT VARCHAR2
 );

END EDU_PKG_PROCE_GENER;
/

CREATE OR REPLACE PACKAGE BODY "EDU_PKG_PROCE_GENER"
IS
/***************************************************************************
Descripcion       : Devuelve numero de Semana correspondiente a una fecha
Fecha Creacion    : 14/01/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION EDU_FN_DEVUE_NUMER_SEMAN( pnAnno NUMBER, pnMes NUMBER, pnDia NUMBER )
RETURN NUMBER
AS
   language java
   name 'Calendario.getNumeroSemana(int, int, int) return int';


/***************************************************************************
Descripcion       : Funcion que devuelve el Pais Datamart en base a los
                    parametros ingresados
Fecha Creacion    : 17/10/2007
Autor             : Carlos Bazalar
Parametros:
          psCodigoPais  : Codigo de Pais
          psCodEmpresa  : Codigo de Empresa
          psCodPrograma : Codigo de Programa
***************************************************************************/
FUNCTION EDU_FN_DEVUE_PAIS_DATAM(
  psCodigoPais      VARCHAR2,
  psCodEmpresa      VARCHAR2,
  psCodPrograma     VARCHAR2
)
RETURN VARCHAR2
IS
  lsRetorno    EDU_PARAM_PROGR_CAPAC.COD_PAIS_DATA%TYPE;
BEGIN
  SELECT A.COD_PAIS_DATA
  INTO lsRetorno
  FROM EDU_PARAM_PROGR_CAPAC A
  WHERE A.PAIS_COD_PAIS = psCodigoPais
    AND A.EMCO_COD_EMPR_COME = psCodEmpresa
    AND A.COD_PROG_CAPA = psCodPrograma;
  RETURN lsRetorno;
EXCEPTION
WHEN NO_DATA_FOUND THEN
     RETURN '';
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_FN_DEVUE_PAIS_DATAM: '||ls_sqlerrm);
END EDU_FN_DEVUE_PAIS_DATAM;


/***************************************************************************
Descripcion       : Funcion que devuelve si el curso tiene ambito por el parametro de a
                    ambito ingresado
Fecha Creacion    : 09/07/2007
Autor             : Carlos Bazalar
Parametros:
          psCodigoPais  : Codigo de Pais
          psCodEmpresa  : Codigo de Empresa
          psCodPeriodo  : Codigo de Periodo
          psUsuario     : Codigo de Ambito
***************************************************************************/
FUNCTION EDU_FN_POSEE_AMBIT_CURSO(
     psCodigoPais    VARCHAR2,
     psCodEmpresa    VARCHAR2,
     psCodCurso      VARCHAR2,
     psCodAmbito     VARCHAR2)
RETURN NUMBER
IS
    lnContador NUMBER;
BEGIN
     /* Verificando si el curso tiene ambito */
     SELECT COUNT(1)
     INTO lncontador
     FROM EDU_PARAM_CURSO_CAPAC_AMBIT A
     WHERE A.PAIS_COD_PAIS = psCodigoPais
       AND A.EMCO_COD_EMPR_COME = psCodEmpresa
       AND A.CCAP_COD_CURS_CAPA = psCodCurso
       AND A.AMDI_COD_AMBI_DICT = psCodAmbito
       AND A.EST_REGI = '1';
    RETURN  lncontador;
EXCEPTION
WHEN NO_DATA_FOUND THEN
     RETURN 0;
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_CALIF_APTAS_AUTOM: '||ls_sqlerrm);
END EDU_FN_POSEE_AMBIT_CURSO;


/***************************************************************************
Descripcion       : Funcion que devuelve Periodo en base al valor de Rango
                    ingresado
Fecha Creacion    : 09/07/2007
Autor             : Carlos Bazalar
Parametros:
          psCodPeriodo  : Codigo de Periodo
          pnValor       : Rango ingresado a buscar (puede ser negativo o positvo)
***************************************************************************/
FUNCTION EDU_FN_DEVUE_CODIG_PERIO(
     psCodPeriodo  VARCHAR2,
     pnValor       NUMBER)
RETURN VARCHAR2
IS
  lsAnno   VARCHAR2(4);
  lsMes    VARCHAR2(2);
  lnMes    NUMBER;
  lnContador  NUMBER;
  MES_INICIAL NUMBER:=1;
  MES_FINAL   NUMBER:=18;
BEGIN
   lsAnno := TRIM(substr(psCodPeriodo, 1, 4));
   lsMes  := TRIM(substr(psCodPeriodo, 5, 2));
   lnContador := 0;

   WHILE TRUE LOOP
     IF pnValor > 0 THEN
        lnContador := lnContador + 1;
        lnMes := TO_number(lsMes);
        IF lnMes = MES_FINAL THEN
           lnMes := MES_INICIAL;
           lsAnno := trim(to_char(to_number(lsAnno) + 1));
        ELSE
           lnMes := lnMes + 1;
        END IF;
        lsMes := trim(to_char(lnMes,'00'));
     ELSE
        lnContador := lnContador - 1;
        lnMes := TO_number(lsMes);
        IF lnMes = MES_INICIAL THEN
           lnMes := MES_FINAL;
           lsAnno := trim(to_char(to_number(lsAnno) - 1));
        ELSE
           lnMes := lnMes - 1;
        END IF;
        lsMes := trim(to_char(lnMes,'00'));
     END IF;
     IF lnContador = pnValor THEN
        EXIT;
     END IF;
   END LOOP;
   RETURN lsAnno || lsMes;

END EDU_FN_DEVUE_CODIG_PERIO;



/***************************************************************************
Descripcion       : Procedimiento que realiza EL PROCESO de Actualización
                    de Planillas Programacion
Fecha Creacion    : 08/08/2007
Autor             : Carlos Bazalar
Parametros:
          psCodigoPais  : Codigo de Pais
          psCodEmpresa  : Codigo de Empresa
          psUsuario     : Codigo de Ambito
***************************************************************************/
PROCEDURE EDU_PR_ACTUA_PLANI_PROGR(
  psCodigoPais    VARCHAR2,
  psCodEmpresa    VARCHAR2,
  psUsuario       VARCHAR2
)
IS
  TYPE tRegTemporal IS RECORD (
       COD_CURS_CAPA  EDU_TMP_PLANI_PROGR_CURSO.COD_CURS_CAPA%TYPE,
       COD_CLIE       EDU_TMP_PLANI_PROGR_CURSO.COD_CLIE%TYPE,
       COD_PLAN_PROG  EDU_TMP_PLANI_PROGR_CURSO.COD_PLAN_PROG%TYPE
   );
  TYPE tRegPlanilla IS RECORD (
       COD_CURS_CAPA  EDU_TMP_PLANI_PROGR_CURSO.COD_CURS_CAPA%TYPE,
       COD_PLAN_PROG  EDU_TMP_PLANI_PROGR_CURSO.COD_PLAN_PROG%TYPE,
       COD_REGI       EDU_REGIO.COD_REGI%TYPE,
       COD_INST       EDU_REGIO.INST_COD_INST%TYPE
   );
  TYPE TABLA_TEMPORAL IS TABLE OF tRegTemporal;
  TYPE TABLA_PLANILLA IS TABLE OF tRegPlanilla;
  regRegistro            tRegTemporal;
  tablaRegistro          TABLA_TEMPORAL;
  regPlanilla            tRegPlanilla;
  tablaPlanilla          TABLA_PLANILLA;
  lsCodPeriodo           VARCHAR2(6);
  lbInsertar             BOOLEAN;
  lsCodInstructora       EDU_HISTO_PLANI_INSTR.INST_COD_INST%TYPE;

  CURSOR cursorRegistro
  IS
     SELECT
        A.COD_CURS_CAPA,
        A.COD_CLIE,
        A.COD_PLAN_PROG
     FROM EDU_TMP_PLANI_PROGR_CURSO A,
          EDU_HISTO_APTAS B
     WHERE A.COD_PAIS = psCodigoPais
       AND A.COD_EMPR_COME = psCodEmpresa
       AND B.EST_CAPA = INDICADOR_POR_PROGRAMAR
       AND B.PAIS_COD_PAIS = A.COD_PAIS
       AND B.EMCO_COD_EMPR_COME = A.COD_EMPR_COME
       AND B.CCAP_COD_CURS_CAPA = A.COD_CURS_CAPA
       AND B.CLIE_COD_CLIE = A.COD_CLIE;

 CURSOR cursorPlanilla
  IS
     SELECT DISTINCT
        A.COD_CURS_CAPA,
        A.COD_PLAN_PROG,
        C.COD_REGI,
        D.INST_COD_INST
     FROM EDU_TMP_PLANI_PROGR_CURSO A,
          EDU_HISTO_APTAS B,
          EDU_MAEST_CLIEN C,
          EDU_REGIO D
     WHERE A.COD_PAIS = psCodigoPais
       AND A.COD_EMPR_COME = psCodEmpresa
       AND B.EST_CAPA = INDICADOR_POR_PROGRAMAR
       AND B.PAIS_COD_PAIS = A.COD_PAIS
       AND B.EMCO_COD_EMPR_COME = A.COD_EMPR_COME
       AND B.CCAP_COD_CURS_CAPA = A.COD_CURS_CAPA
       AND B.CLIE_COD_CLIE = A.COD_CLIE
       AND C.PAIS_COD_PAIS = A.COD_PAIS
       AND C.EMCO_COD_EMPR_COME = A.COD_EMPR_COME
       AND C.COD_CLIE = A.COD_CLIE
       AND D.PAIS_COD_PAIS = C.PAIS_COD_PAIS
       AND D.EMCO_COD_EMPR_COME = C.EMCO_COD_EMPR_COME
       AND D.COD_REGI = C.COD_REGI;

BEGIN
  lsCodPeriodo := EDU_PKG_CALIF.EDU_FN_DEVUE_CAMPA_PROCE_ACTUA(psCodigoPais, psCodEmpresa);

  /* Proceso de Actualizar Tabla de historico x Instructora */
  OPEN cursorPlanilla;
  LOOP
      FETCH cursorPlanilla BULK COLLECT INTO tablaPlanilla LIMIT W_FILAS;
      IF tablaPlanilla.COUNT > 0 THEN
        FOR x IN tablaPlanilla.FIRST .. tablaPlanilla.LAST LOOP
             regPlanilla := tablaPlanilla(x);
             lbInsertar := FALSE;
             BEGIN
                 SELECT A.INST_COD_INST
                 INTO
                      lsCodInstructora
                 FROM EDU_HISTO_PLANI_INSTR A
                 WHERE A.PAIS_COD_PAIS = psCodigoPais
                   AND A.EMCO_COD_EMPR_COME = psCodEmpresa
                   AND A.CCAP_COD_CURS_CAPA = regPlanilla.COD_CURS_CAPA
                   AND A.COD_PLAN_PROG = regPlanilla.COD_PLAN_PROG;
             EXCEPTION
             WHEN no_Data_found THEN
                  lbInsertar := TRUE;
             END;

             /* Actualizando Tabla de Historico x Instructora */
             IF lbInsertar THEN
                INSERT INTO EDU_HISTO_PLANI_INSTR (
                    PAIS_COD_PAIS, EMCO_COD_EMPR_COME, CCAP_COD_CURS_CAPA,
                    COD_PLAN_PROG, REGI_COD_REGI, INST_COD_INST,
                    SIT_PLAN_PROG,
                    USU_DIGI, FEC_DIGI, EST_REGI)
                VALUES
                    (psCodigoPais, psCodEmpresa, regPlanilla.COD_CURS_CAPA,
                     regPlanilla.COD_PLAN_PROG, regPlanilla.COD_REGI, regPlanilla.COD_INST,
                     'A',
                     psUsuario, SYSDATE, '1');
             ELSE
                 UPDATE EDU_HISTO_PLANI_INSTR A
                 SET
                     A.REGI_COD_REGI = regPlanilla.COD_REGI,
                     A.INST_COD_INST = regPlanilla.COD_INST,
                     A.SIT_PLAN_PROG = 'A',
                     A.USU_MODI = psUsuario,
                     A.FEC_MODI = SYSDATE
                 WHERE A.PAIS_COD_PAIS = psCodigoPais
                   AND A.EMCO_COD_EMPR_COME = psCodEmpresa
                   AND A.CCAP_COD_CURS_CAPA = regPlanilla.COD_CURS_CAPA
                   AND A.COD_PLAN_PROG = regPlanilla.COD_PLAN_PROG;
             END IF;
         END LOOP;
      END IF;
      EXIT WHEN cursorPlanilla%NOTFOUND;
  END LOOP;
  CLOSE cursorPlanilla;

  /* Proceso de Actualizar los indicadores respectivos */
  OPEN cursorRegistro;
  LOOP
      FETCH cursorRegistro BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
      IF tablaRegistro.COUNT > 0 THEN
        FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
             regRegistro := tablaRegistro(x);

             /* Actualizando Tabla de Historico de Aptas */
             UPDATE EDU_HISTO_APTAS A
             SET A.COD_PLAN_PROG = regRegistro.COD_PLAN_PROG,
                 A.ULT_CAMP_PROG_DICT = lsCodPeriodo,
                 A.EST_CAPA = INDICADOR_PROGRAMADA,
                 A.USU_MODI = psUsuario,
                 A.FEC_MODI = SYSDATE
             WHERE A.PAIS_COD_PAIS = psCodigoPais
               AND A.EMCO_COD_EMPR_COME = psCodEmpresa
               AND A.CCAP_COD_CURS_CAPA = regRegistro.COD_CURS_CAPA
               AND A.CLIE_COD_CLIE = regRegistro.COD_CLIE;

            /* Actualizando Tabla Planilla Programacion de curso */
             UPDATE EDU_PLANI_PROGR_CURSO A
             SET A.COD_PLAN_PROG = regRegistro.COD_PLAN_PROG,
                 A.USU_MODI = psUsuario,
                 A.FEC_MODI = SYSDATE
             WHERE A.PAIS_COD_PAIS = psCodigoPais
               AND A.EMCO_COD_EMPR_COME = psCodEmpresa
               AND A.CCAP_COD_CURS_CAPA = regRegistro.COD_CURS_CAPA
               AND A.CLIE_COD_CLIE = regRegistro.COD_CLIE
               AND A.CAM_PROC = lsCodPeriodo ;

         END LOOP;
      END IF;
      EXIT WHEN cursorRegistro%NOTFOUND;
  END LOOP;
  CLOSE cursorRegistro;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_ACTUA_PLANI_PROGR: '||ls_sqlerrm);
END EDU_PR_ACTUA_PLANI_PROGR;


/***************************************************************************
Descripcion       : Procedimiento que realiza el proceso de Cierre de Campanna
- Actualización de Parametros de curso
- Aumentar Nro de Invitaciones
- Pasar Consultoras a Estado PENDIENTE (PC)
- Bloqueo de Conusltoras al Cierre de Campaña
Fecha Creacion    : 08/08/2007
Autor             : Carlos Bazalar
Parametros:
          psCodigoPais  : Codigo de Pais
          psCodEmpresa  : Codigo de Empresa
          psCodPeriodo  : Codigo de Periodo
          psUsuario     : Usuario
***************************************************************************/
   PROCEDURE edu_pr_proce_cierr_campa (
      pscodigopais   VARCHAR2,
      pscodempresa   VARCHAR2,
      pscodperiodo   VARCHAR2,
      psusuario      VARCHAR2
)
IS
      TYPE t_cod_curso IS TABLE OF edu_histo_curso_dicta_cabec.ccap_cod_curs_capa%TYPE;

      TYPE t_cod_clien IS TABLE OF edu_histo_curso_dicta_detal.clie_cod_clie%TYPE;

      TYPE t_num_invi IS TABLE OF edu_param_curso_capac.num_invi%TYPE;

      regcurso     t_cod_curso;
      regcliente   t_cod_clien;
      regnuminvi   t_num_invi;

 /* Cursor para aumentar nro de invitaciones en aquellas consultoras que no asistieron
   al curso en la campaña de proceso */
      CURSOR cnroinvi
      IS
 /* Dicho proceso se efectuará en el proceso de Calificación de Aptas Automatico
 SELECT DISTINCT
        Y.CCAP_COD_CURS_CAPA,
        Y.CLIE_COD_CLIE,
        Z.NUM_INVI
  FROM
     EDU_HISTO_CURSO_DICTA_CABEC X,
     EDU_HISTO_CURSO_DICTA_DETAL Y,
     EDU_PARAM_CURSO_CAPAC Z
  WHERE X.PAIS_COD_PAIS = psCodigoPais
    AND X.EMCO_COD_EMPR_COME = psCodEmpresa
    AND X.CAM_INIC_CURS = psCodPeriodo
    AND X.EST_REGI <> '9'

    AND Y.IND_ASIS = 'N'
    AND Y.PAIS_COD_PAIS = X.PAIS_COD_PAIS
    AND Y.EMCO_COD_EMPR_COME = X.EMCO_COD_EMPR_COME
    AND Y.CCAP_COD_CURS_CAPA = X.CCAP_COD_CURS_CAPA
    AND Y.CDIC_COD_CURS_DICT = X.COD_CURS_DICT
    AND Y.EST_REGI <> '9'

    AND Z.PAIS_COD_PAIS = X.PAIS_COD_PAIS
    AND Z.EMCO_COD_EMPR_COME = X.EMCO_COD_EMPR_COME
    AND Z.COD_CURS_CAPA = X.CCAP_COD_CURS_CAPA

 UNION
 */

  -- SELECT DE nro de invitaciones en aquellas consultoras que quedaron pendientes
  --  en la campaña de proceso y no pasaron pedido
         SELECT x.ccap_cod_curs_capa, x.clie_cod_clie, y.num_invi
           FROM edu_histo_aptas x, edu_param_curso_capac y
          WHERE x.pais_cod_pais = pscodigopais
            AND x.emco_cod_empr_come = pscodempresa
            AND x.est_capa = indicador_pendiente
            AND x.ult_camp_prog_dict IS NOT NULL
            AND x.ult_camp_prog_dict < pscodperiodo
            AND x.cam_ulti_cali_apta < pscodperiodo
            AND x.est_regi <> '9'
            AND y.pais_cod_pais = x.pais_cod_pais
            AND y.emco_cod_empr_come = x.emco_cod_empr_come
            AND y.cod_curs_capa = x.ccap_cod_curs_capa
            AND x.num_invi < y.num_invi
 /*
 UNION
  -- SELECT DE nro de invitaciones en aquellas consultoras que estan Programadas
  --  en la campaña de proceso
  -- Dicha actualizacion se esta efectuando en la Generacion de Planilla
 SELECT X.CCAP_COD_CURS_CAPA,
        X.CLIE_COD_CLIE,
        Y.NUM_INVI
 FROM EDU_HISTO_APTAS X,
      EDU_PARAM_CURSO_CAPAC Y
 WHERE X.PAIS_COD_PAIS = psCodigoPais
   AND X.EMCO_COD_EMPR_COME = psCodEmpresa
   AND X.EST_CAPA = INDICADOR_PROGRAMADA
   AND X.ULT_CAMP_PROG_DICT = psCodPeriodo
   AND X.EST_REGI <> '9'
   AND Y.PAIS_COD_PAIS = X.PAIS_COD_PAIS
   AND Y.EMCO_COD_EMPR_COME = X.EMCO_COD_EMPR_COME
   AND Y.COD_CURS_CAPA = X.CCAP_COD_CURS_CAPA
   AND X.NUM_INVI < Y.NUM_INVI
                                        */
      ;
BEGIN
 /* Actualizando parametrizacion de cursos */
      UPDATE edu_param_curso_capac a
         SET a.cam_inic = edu_fn_devue_codig_perio (pscodperiodo, a.num_camp),
             a.fec_modi = SYSDATE,
             a.usu_modi = psusuario
       WHERE a.pais_cod_pais = pscodigopais
         AND a.emco_cod_empr_come = pscodempresa
         AND a.est_regi <> '9'
         AND a.frca_cod_frec_cali = '02'
         AND a.cam_inic = pscodperiodo;

 /* cambia el estado de consultoras que no asistieron al curso de Programadas a Pendientes */
      edu_pr_proce_consu_progr_pendi (pscodigopais,
                                      pscodempresa,
                                      pscodperiodo,
                                      psusuario
                                     );

      /* cambia el estado de consultoras de pedidos rezagados que no tienen planilla de PP a PC */
      edu_pr_proce_pendi_progr_pendi (pscodigopais,
                                      pscodempresa,
                                      pscodperiodo,
                                      psusuario
                                     );

 /* Actualizando numero de Invitaciones para aquellas consultoras que esten
    programadas o capacitadas en la campaña de proceso  */
      OPEN cnroinvi;

 LOOP
         FETCH cnroinvi
         BULK COLLECT INTO regcurso, regcliente, regnuminvi LIMIT w_filas;

         IF regcurso.COUNT > 0
         THEN
            FORALL i IN 1 .. regcurso.COUNT
               UPDATE edu_histo_aptas a
               SET a.num_invi = a.num_invi,-- + 1,
                      a.usu_modi = psusuario,
                      a.fec_modi = SYSDATE
                WHERE a.pais_cod_pais = pscodigopais
                  AND a.emco_cod_empr_come = pscodempresa
                  AND a.ccap_cod_curs_capa = regcurso (i)
                  AND a.clie_cod_clie = regcliente (i)
                  AND a.num_invi < regnuminvi (i)
                  AND a.est_regi <> '9';
    END IF;

         EXIT WHEN cnroinvi%NOTFOUND;
 END LOOP;

      CLOSE cnroinvi;
EXCEPTION
      WHEN OTHERS
      THEN
     ln_sqlcode := SQLCODE;
         ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
         raise_application_error (-20123,
                                     'ERROR EDU_PR_PROCE_CIERR_CAMPA: '
                                  || ls_sqlerrm
                                 );
   END edu_pr_proce_cierr_campa;

/***************************************************************************
Descripcion       : Procedimiento que cambiar el estado de consultoras
                    que no asistieron al curso de Programadas a Pendientes
                    (Proceso de cierre de Campaña)
Fecha Creacion    : 16/01/2008
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psCodPeriodo : Campaña de Proceso
            psUsuario    : Usuario
***************************************************************************/
PROCEDURE EDU_PR_PROCE_CONSU_PROGR_PENDI(
  psCodigoPais               VARCHAR2,
  psCodEmpresa               VARCHAR2,
  psCodPeriodo               VARCHAR2,
  psUsuario                  VARCHAR2
)
IS
 TYPE t_cod_curso  IS TABLE OF EDU_HISTO_CURSO_DICTA_CABEC.CCAP_COD_CURS_CAPA%TYPE ;
 TYPE t_cod_clien  IS TABLE OF EDU_HISTO_CURSO_DICTA_DETAL.CLIE_COD_CLIE%TYPE ;
 TYPE t_num_invi   IS TABLE OF EDU_PARAM_CURSO_CAPAC.NUM_INVI%TYPE ;

 regCurso    t_cod_curso ;
 regCliente  t_cod_clien ;
 regNumInvi  t_num_invi ;

 /* Cursos para cambiar el estado de consultoras que no asistieron al curso
     de Programadas a Pendientes */
      CURSOR cpasarapendientes
      IS
         SELECT DISTINCT y.ccap_cod_curs_capa, y.clie_cod_clie, z.num_invi
                    FROM edu_histo_curso_dicta_cabec x,
                         edu_histo_curso_dicta_detal y,
                         edu_param_curso_capac z
                   WHERE x.pais_cod_pais = pscodigopais
                     AND x.emco_cod_empr_come = pscodempresa
                     AND x.cam_inic_curs = pscodperiodo
                     AND x.est_regi <> '9'
                     AND y.ind_asis = 'N'
                     AND y.pais_cod_pais = x.pais_cod_pais
                     AND y.emco_cod_empr_come = x.emco_cod_empr_come
                     AND y.ccap_cod_curs_capa = x.ccap_cod_curs_capa
                     AND y.cdic_cod_curs_dict = x.cod_curs_dict
                     AND y.est_regi <> '9'
                     AND z.pais_cod_pais = x.pais_cod_pais
                     AND z.emco_cod_empr_come = x.emco_cod_empr_come
                     AND z.cod_curs_capa = x.ccap_cod_curs_capa
                         AND Y.CLIE_COD_CLIE NOT IN (--VALIDACION POR LAS EXONERADAS
                               SELECT R.CLIE_COD_CLIE
                                FROM EDU_HISTO_APTAS R
                                WHERE R.PAIS_COD_PAIS =pscodigopais
                                     AND R.EMCO_COD_EMPR_COME = pscodempresa
                                     AND R.CAM_CAPA =pscodperiodo
                                     AND Y.CCAP_COD_CURS_CAPA = R.CCAP_COD_CURS_CAPA
                                    AND R.EST_CAPA ='CP');
BEGIN
 /* Cambiando estado de consultoras que no asistieron al curso
    de Programadas a Pendientes */
 OPEN cPasarAPendientes;
 LOOP
    FETCH cPasarAPendientes BULK COLLECT INTO
          regCurso,
          regCliente,
          regNumInvi
          LIMIT W_FILAS;
    IF regCurso.COUNT > 0 THEN
       FORALL i IN 1..regCurso.COUNT
          UPDATE EDU_HISTO_APTAS A
          SET A.EST_CAPA = INDICADOR_PENDIENTE,
              A.USU_MODI = psUsuario,
              A.FEC_MODI = SYSDATE
          WHERE A.PAIS_COD_PAIS = psCodigoPais
            AND A.EMCO_COD_EMPR_COME = psCodEmpresa
            AND A.CCAP_COD_CURS_CAPA = regCurso(i)
            AND A.CLIE_COD_CLIE = regCliente(i)
            AND A.ULT_CAMP_PROG_DICT < psCodPeriodo
            AND A.EST_REGI <> '9';
    END IF;
    EXIT WHEN cPasarAPendientes%NOTFOUND;
 END LOOP;
 CLOSE cPasarAPendientes;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_PROCE_CONSU_PROGR_PENDI: '||ls_sqlerrm );
END EDU_PR_PROCE_CONSU_PROGR_PENDI;


/***************************************************************************
Descripcion       : Procedimiento que cambiar el estado de consultoras
                    que no asistieron al curso de Programadas a Pendientes
                    (PROCESO DE CIERRE DE REGION)
Fecha Creacion    : 16/01/2008
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psCodPeriodo : Campaña de Proceso
            psCodProceso  : Codigo de Proceso
            psCodParam    : Codigo de Parametro
            psUsuario    : Usuario
***************************************************************************/
PROCEDURE EDU_PR_PROCE_CONSU_PROGR_PENDI(
  psCodigoPais               VARCHAR2,
  psCodEmpresa               VARCHAR2,
  psCodPeriodo               VARCHAR2,
  psCodProceso               VARCHAR2,
  psCodParam                 VARCHAR2,
  psUsuario                  VARCHAR2
)
IS
 TYPE t_cod_curso  IS TABLE OF EDU_HISTO_CURSO_DICTA_CABEC.CCAP_COD_CURS_CAPA%TYPE ;
 TYPE t_cod_clien  IS TABLE OF EDU_HISTO_CURSO_DICTA_DETAL.CLIE_COD_CLIE%TYPE ;
 TYPE t_num_invi   IS TABLE OF EDU_PARAM_CURSO_CAPAC.NUM_INVI%TYPE ;

 regCurso    t_cod_curso ;
 regCliente  t_cod_clien ;
 regNumInvi  t_num_invi ;

 /* Cursos para cambiar el estado de consultoras que no asistieron al curso
     de Programadas a Pendientes */
      CURSOR cpasarapendientes
      IS
         SELECT DISTINCT y.ccap_cod_curs_capa, y.clie_cod_clie, z.num_invi
                    FROM edu_histo_curso_dicta_cabec x,
                         edu_histo_curso_dicta_detal y,
                         edu_param_curso_capac z,
                         edu_maest_clien a,
                         edu_gtt_param_proce w
                   WHERE x.pais_cod_pais = pscodigopais
                     AND x.emco_cod_empr_come = pscodempresa
                     AND x.cam_inic_curs = pscodperiodo
                     AND x.est_regi <> '9'
                     AND y.ind_asis = 'N'
                     AND y.pais_cod_pais = x.pais_cod_pais
                     AND y.emco_cod_empr_come = x.emco_cod_empr_come
                     AND y.ccap_cod_curs_capa = x.ccap_cod_curs_capa
                     AND y.cdic_cod_curs_dict = x.cod_curs_dict
                     AND y.est_regi <> '9'
                     AND z.pais_cod_pais = x.pais_cod_pais
                     AND z.emco_cod_empr_come = x.emco_cod_empr_come
                     AND z.cod_curs_capa = x.ccap_cod_curs_capa
                     AND a.pais_cod_pais = x.pais_cod_pais
                     AND a.emco_cod_empr_come = y.emco_cod_empr_come
                     AND a.cod_clie = y.clie_cod_clie
                     AND a.est_regi <> '9'
                     AND w.cod_proc = pscodproceso
                     AND w.cod_para = pscodparam
                     AND w.val_para_varc = a.cod_regi
                     AND Y.CLIE_COD_CLIE NOT IN (--VALIDACION POR LAS EXONERADAS
                               SELECT R.CLIE_COD_CLIE
                                FROM EDU_HISTO_APTAS R
                                WHERE R.PAIS_COD_PAIS =pscodigopais
                                     AND R.EMCO_COD_EMPR_COME = pscodempresa
                                     AND R.CAM_CAPA =pscodperiodo
                                     AND Y.CCAP_COD_CURS_CAPA = R.CCAP_COD_CURS_CAPA
                                    AND R.EST_CAPA ='CP');

BEGIN
 /* Cambiando estado de consultoras que no asistieron al curso
    de Programadas a Pendientes */
 OPEN cPasarAPendientes;
 LOOP
    FETCH cPasarAPendientes BULK COLLECT INTO
          regCurso,
          regCliente,
          regNumInvi
          LIMIT W_FILAS;
    IF regCurso.COUNT > 0 THEN
       FORALL i IN 1..regCurso.COUNT
          UPDATE EDU_HISTO_APTAS A
          SET A.EST_CAPA = INDICADOR_PENDIENTE,
              A.USU_MODI = psUsuario,
              A.FEC_MODI = SYSDATE
          WHERE A.PAIS_COD_PAIS = psCodigoPais
            AND A.EMCO_COD_EMPR_COME = psCodEmpresa
            AND A.CCAP_COD_CURS_CAPA = regCurso(i)
            AND A.CLIE_COD_CLIE = regCliente(i)
            AND A.ULT_CAMP_PROG_DICT < psCodPeriodo
            AND A.EST_REGI <> '9';
    END IF;
    EXIT WHEN cPasarAPendientes%NOTFOUND;
 END LOOP;
 CLOSE cPasarAPendientes;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_PROCE_CONSU_PROGR_PENDI: '||ls_sqlerrm );
END EDU_PR_PROCE_CONSU_PROGR_PENDI;



/***************************************************************************
Descripcion       : Procedimiento que realiza el Proceso de Bloqueo de Consultoras
                    al Cierre de Campaña
Fecha Creacion    : 03/12/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psCodPeriodo : Campaña de Proceso
            psUsuario    : Usuario
***************************************************************************/
PROCEDURE EDU_PR_PROCE_BLOQU_CONSU_CAMPA(
  psCodigoPais               VARCHAR2,
  psCodEmpresa               VARCHAR2,
  psCodPeriodo               VARCHAR2,
  psUsuario                  VARCHAR2
)
IS
  CURSOR cursorCurso IS
     SELECT A.COD_CURS_CAPA,
            A.NUM_INVI
     FROM EDU_PARAM_CURSO_CAPAC A
     WHERE A.PAIS_COD_PAIS = psCodigoPais
 AND A.EMCO_COD_EMPR_COME = NVL(psCodEmpresa,(select COD_EMPR_COME from EDU_EMPRE_COMER where PAIS_COD_PAIS = psCodigoPais))
 AND A.STA_CLIE = 'N' -- NUEVAS ,INDICADOR_SI
       AND A.EST_REGI = INDICADOR_ACTIVO;

 vsCodEmpresa EDU_EMPRE_COMER.Cod_Empr_Come%TYPE;
BEGIN

 vsCodEmpresa := psCodEmpresa;

 if psCodEmpresa is null then

   select COD_EMPR_COME
   into vsCodEmpresa
   from EDU_EMPRE_COMER
   where PAIS_COD_PAIS = psCodigoPais;
 end if;

  DELETE FROM EDU_GTT_HISTO_BLOQU_CONSU;

  FOR cCurso IN cursorCurso LOOP
      EDU_PKG_CALIF.EDU_PR_PROCE_BLOQU_CONSU_PENDI(
 psCodigoPais, vsCodEmpresa, psCodPeriodo,
          cCurso.Cod_Curs_Capa, cCurso.Num_Invi, psUsuario);
  END LOOP;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_PROCE_BLOQU_CONSU_CAMPA: '||ls_sqlerrm );
END EDU_PR_PROCE_BLOQU_CONSU_CAMPA;


/***************************************************************************
Descripcion       : Funcion que valida que el codigo de Servicio se encuentre
                    dentro del Rango y que otro producto no posea el mismo codigo
Fecha Creacion    : 09/07/2007
Autor             : Carlos Bazalar
Parametros:
          psCodigoPais     : Codigo de Pais
          psCodigoEmpresa  : Codigo de Empresa
          psCodigoCurso    : Codigo de Curso de Capacitacion
          psCodigoPrograma : Codigo de Programa
          psCodigoServicio : Codigo de Servicio
Retorno           :
         1 OK
     -1 Codigo de Servicio fuera de Rango
     -2 Codigo de Servicio se encuentra registrado en otro producto
        -3 No existe Rango para el curso
***************************************************************************/
FUNCTION EDU_FN_VALID_RANGO_CODIG_SERVI(
  psCodigoPais     VARCHAR2,
  psCodigoEmpresa  VARCHAR2,
  psCodigoCurso    VARCHAR2,
  psCodigoPrograma VARCHAR2,
  psCodigoServicio VARCHAR2)
RETURN NUMBER
IS
  lnCodServicioIni         NUMBER;
  lnCodServicioFin         NUMBER;
  lnCodServicio            NUMBER;
  lsCodServicioIni         EDU_PARAM_PROGR_CAPAC.Cod_Serv_Inic%TYPE;
  lsCodServicioFin         EDU_PARAM_PROGR_CAPAC.Cod_Serv_Fina%TYPE;
  lsCodCurso               EDU_PARAM_CURSO_CAPAC.Cod_Curs_Capa%TYPE;

BEGIN

  /* Verificando el Rango del codigo de Servicio */
  BEGIN
    SELECT A.COD_SERV_INIC, A.COD_SERV_FINA
    INTO lsCodServicioIni, lsCodServicioFin
    FROM EDU_PARAM_PROGR_CAPAC A
    WHERE A.PAIS_COD_PAIS = psCodigoPais
      AND A.EMCO_COD_EMPR_COME = psCodigoEmpresa
      AND A.COD_PROG_CAPA = psCodigoPrograma;
  EXCEPTION
  WHEN no_data_found THEN
    RETURN -3;
  END;

  lnCodServicioIni := to_number(lsCodServicioIni);
  lnCodServicioFin := to_number(lsCodServicioFin);
  lnCodServicio    := to_number(psCodigoServicio);

  IF NOT (lnCodServicioIni <= lnCodServicio AND lnCodServicio <= lnCodServicioFin) THEN
     RETURN -1;
  END IF;

  /* Verificando que no exista mismo codigo Servicio en otro producto */
  BEGIN
     SELECT A.COD_CURS_CAPA
     INTO lsCodCurso
     FROM EDU_PARAM_CURSO_CAPAC A
     WHERE A.PAIS_COD_PAIS = psCodigoPais
       AND A.EMCO_COD_EMPR_COME = psCodigoEmpresa
       AND A.COD_CURS_CAPA <> psCodigoCurso
       AND A.COD_PROD_CURS = psCodigoServicio;
  EXCEPTION
  WHEN no_data_found THEN
       RETURN 1;
  END;
  RETURN -2;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_FN_VALID_RANGO_CODIG_SERVI: '||ls_sqlerrm);
END EDU_FN_VALID_RANGO_CODIG_SERVI;


/***************************************************************************
Descripcion       : Funcion que devuelve el codigo de Region para una instructura
Fecha Creacion    : 21/07/2007
Autor             : Carlos Bazalar
Parametros:
          psCodigoPais     : Codigo de Pais
          psCodEmpresa     : Codigo de Empresa
          psCodInstructora : Codigo de Instructora
***************************************************************************/
FUNCTION EDU_FN_DEVUE_REGIO_INSTR(
     psCodPais         VARCHAR2,
     psCodEmpresa      VARCHAR2,
     psCodInstructora  VARCHAR2)
RETURN VARCHAR2
IS
  lsCodRegi              EDU_REGIO.COD_REGI%TYPE;
BEGIN

  /* Obteniendo parametrizacion del curso */
  SELECT A.COD_REGI
  INTO lsCodRegi
  FROM EDU_REGIO A
  WHERE A.PAIS_COD_PAIS = psCodPais
    AND A.EMCO_COD_EMPR_COME = psCodEmpresa
    AND A.INST_COD_INST = psCodInstructora
    AND ROWNUM = 1
    AND A.EST_REGI = INDICADOR_ACTIVO;

  RETURN lsCodRegi;

EXCEPTION
WHEN NO_DATA_FOUND THEN
     RETURN NULL;
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_FN_DEVUE_REGIO_INSTR: '||ls_sqlerrm);
END EDU_FN_DEVUE_REGIO_INSTR;


/***************************************************************************
Descripcion       : Procedimiento que efectua el Proceso de Cierre de
                    Cursos Dictados
                    (Proceso de CIERRE DE REGION)
Fecha Creacion    : 20/09/2007
Autor             : Carlos Bazalar
Parametros:
          psCodigoPais  : Codigo de Pais
          psCodEmpresa  : Codigo de Empresa
          psCodPeriodo  : Codigo de Periodo
          psCodProceso  : Codigo de Proceso
          psCodParam    : Codigo de Parametro
          psUsuario     : Usuario
***************************************************************************/
PROCEDURE EDU_PR_CIERR_CURSO_DICTA(
  psCodigoPais      VARCHAR2,
  psCodEmpresa      VARCHAR2,
  psCodPeriodo      VARCHAR2,
  psCodProceso      VARCHAR2,
  psCodParam        VARCHAR2,
  psUsuario         VARCHAR2
)
IS

BEGIN
  /* Actualizando Estado de Cursos Dictados */
  UPDATE EDU_HISTO_CURSO_DICTA_CABEC A
  SET
      A.EST_CURS_DICT = 'C',
     A.USU_MODI = psUsuario,
     A.FEC_MODI = SYSDATE
  WHERE A.PAIS_COD_PAIS = psCodigoPais
    AND A.EMCO_COD_EMPR_COME = psCodEmpresa
    AND A.EST_CURS_DICT = 'V'
    AND A.EST_REGI = INDICADOR_ACTIVO
    AND EXISTS
       (SELECT X.COD_PROC
        FROM EDU_GTT_PARAM_PROCE X
        WHERE X.COD_PROC = psCodProceso
          AND X.COD_PARA = psCodParam
          AND X.VAL_PARA_VARC = A.COD_REGI);

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_CIERR_CURSO_DICTA: '||ls_sqlerrm);
END EDU_PR_CIERR_CURSO_DICTA;


/***************************************************************************
Descripcion       : Procedimiento que efectua el Proceso de Cierre de
                    Cronograma Dictado para la campaña de Proceso
                    (Proceso de CIERRE DE REGION)
Fecha Creacion    : 11/12/2007
Autor             : Carlos Bazalar
Parametros:
          psCodigoPais  : Codigo de Pais
          psCodEmpresa  : Codigo de Empresa
          psCodPeriodo  : Codigo de Periodo
          psCodProceso  : Codigo de Proceso
          psCodParametro: Codigo de Parametro
          psUsuario     : Usuario
***************************************************************************/
PROCEDURE EDU_PR_CIERR_CRONO_DICTA(
  psCodigoPais      VARCHAR2,
  psCodEmpresa      VARCHAR2,
  psCodPeriodo      VARCHAR2,
  psCodProceso      VARCHAR2,
  psCodParam        VARCHAR2,
  psUsuario         VARCHAR2
)
IS

BEGIN
  /* Actualizando Estado de Cronograma Dictado */
  UPDATE EDU_CRONO_DICTA A
  SET
     A.EST_ACTI = 'I',
     A.USU_MODI = psUsuario,
     A.FEC_MODI = SYSDATE
  WHERE A.PAIS_COD_PAIS = psCodigoPais
    AND A.EMCO_COD_EMPR_COME = psCodEmpresa
    AND A.CAM_CRON = psCodPeriodo
    AND A.EST_ACTI = 'A'
    AND A.EST_REGI = INDICADOR_ACTIVO
    AND EXISTS
       (SELECT X.COD_PROC
        FROM EDU_GTT_PARAM_PROCE X
        WHERE X.COD_PROC = psCodProceso
          AND X.COD_PARA = psCodParam
          AND X.VAL_PARA_VARC = A.REGI_COD_REGI);
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_CIERR_CRONO_DICTA: '||ls_sqlerrm);
END EDU_PR_CIERR_CRONO_DICTA;



/***************************************************************************
Descripcion       : Procedimiento previo que llena la informacion de Objetivos
                    de Asistencia en la tabla temporal para luego ser displayado
Fecha Creacion    : 07/09/2007
Autor             : Carlos Bazalar
Parametros:
          psCodigoPais  : Codigo de Pais
          psCodEmpresa  : Codigo de Empresa
          psCodCurso    : Codigo de Curso
          psCodAnno     : Año
          psCodRegion   : Codigo de Region
          psTipo        : 'P': Pais  'R' : Region   'Z': Zona
***************************************************************************/
   PROCEDURE edu_pr_inser_objet_asist_tempo (
      pscodigopais   VARCHAR2,
      pscodempresa   VARCHAR2,
      pscodcurso     VARCHAR2,
      pscodanno      VARCHAR2,
      pscodregion    VARCHAR2,
      pstipo         VARCHAR2,
      psusuario      VARCHAR2
)
IS
      TYPE tipoporcentaje IS VARRAY (18) OF NUMBER;

      lsdescripcionregion   edu_regio.des_regi%TYPE;
      lsdescripcionpais     VARCHAR2 (60);
      lnporcen              tipoporcentaje;
      lnporcentotal         NUMBER;
      lnporcentempo         NUMBER;
      lscodperiodo          VARCHAR2 (6);

      CURSOR cursorzona
  IS
         SELECT a.cod_zona, a.cod_zona ||' - '||a.des_zona
           FROM edu_zona a
          WHERE a.pais_cod_pais = pscodigopais
            AND a.emco_cod_empr_come = pscodempresa
            AND a.regi_cod_regi = pscodregion
            AND a.est_regi <> '9'
			AND (a.est_zona is null or a.est_zona<>'9');

      TYPE tipoobjetivozona IS RECORD (
         codigozona        VARCHAR2 (4),
         descripcionzona   VARCHAR2 (60)
   );

      TYPE tobjetivozona IS TABLE OF tipoobjetivozona;

      tablaobjetivozona     tobjetivozona;
      regobjetivozona       tipoobjetivozona;
BEGIN
      DELETE FROM edu_tmp_objet_asist a
            WHERE a.cod_usua = psusuario
              AND a.cod_pais = pscodigopais
              AND a.cod_empr_come = pscodempresa
              AND a.cod_curs_capa = pscodcurso;

      lnporcen := tipoporcentaje ();
      lnporcen.EXTEND (rango_campana);

  /* Encontrando la descripcion de Region */
      SELECT a.cod_regi ||' - '|| a.des_regi
        INTO lsdescripcionregion
        FROM edu_regio a
       WHERE a.pais_cod_pais = pscodigopais
         AND a.emco_cod_empr_come = pscodempresa
         AND a.cod_regi = pscodregion
         AND a.est_regi <> '9';

      IF pstipo = 'P'
      THEN                                                 -- En caso sea Pais
     /* Encontrando la descripcion de Pais */
         SELECT a.des_pais
           INTO lsdescripcionpais
           FROM bas_pais a
          WHERE a.cod_pais = pscodigopais AND a.est_pais <> '9';

     /* Porcentaje total */
     BEGIN
            SELECT a.val_porc
              INTO lnporcentotal
              FROM edu_objet_asist_pais_anual a
             WHERE a.pais_cod_pais = pscodigopais
               AND a.emco_cod_empr_come = pscodempresa
               AND a.ccap_cod_curs_capa = pscodcurso
               AND a.cod_anno = pscodanno
               AND a.est_regi <> '9';
     EXCEPTION
            WHEN NO_DATA_FOUND
            THEN
               lnporcentotal := 0.00;
     END;

     /* Porcentaje Detalle */
         FOR i IN 1 .. rango_campana
     LOOP
            lscodperiodo := pscodanno || TRIM (TO_CHAR (i, '00'));

        BEGIN
               SELECT a.val_porc
                 INTO lnporcentempo
                 FROM edu_objet_asist_pais_perio a
                WHERE a.pais_cod_pais = pscodigopais
                  AND a.emco_cod_empr_come = pscodempresa
                  AND a.ccap_cod_curs_capa = pscodcurso
                  AND a.cod_peri = lscodperiodo
                  AND a.est_regi <> '9';
        EXCEPTION
               WHEN NO_DATA_FOUND
               THEN
                  lnporcentempo := 0.00;
        END;

            lnporcen (i) := lnporcentempo;
     END LOOP;

     /* Insertando en la tabla temporal */
         INSERT INTO edu_tmp_objet_asist
                     (cod_usua, cod_pais, cod_empr_come, cod_curs_capa,
                      des_pais, cod_regi, cod_zona, des_regi, des_zona,
                      val_ca01, val_ca02, val_ca03, val_ca04,
                      val_ca05, val_ca06, val_ca07, val_ca08,
                      val_ca09, val_ca10, val_ca11,
                      val_ca12, val_ca13, val_ca14,
                      val_ca15, val_ca16, val_ca17,
                      val_ca18, val_tota
                     )
              VALUES (psusuario, pscodigopais, pscodempresa, pscodcurso,
                      lsdescripcionpais, NULL, NULL, NULL, NULL,
                      lnporcen (1), lnporcen (2), lnporcen (3), lnporcen (4),
                      lnporcen (5), lnporcen (6), lnporcen (7), lnporcen (8),
                      lnporcen (9), lnporcen (10), lnporcen (11),
                      lnporcen (12), lnporcen (13), lnporcen (14),
                      lnporcen (15), lnporcen (16), lnporcen (17),
                      lnporcen (18), lnporcentotal
                     );
      ELSIF pstipo = 'R'
      THEN                                               -- En caso sea Region
     /* Porcentaje total */
     BEGIN
            SELECT a.val_porc
              INTO lnporcentotal
              FROM edu_objet_asist_regio_anual a
             WHERE a.pais_cod_pais = pscodigopais
               AND a.emco_cod_empr_come = pscodempresa
               AND a.ccap_cod_curs_capa = pscodcurso
               AND a.cod_anno = pscodanno
               AND a.regi_cod_regi = pscodregion
               AND a.est_regi <> '9';
     EXCEPTION
            WHEN NO_DATA_FOUND
            THEN
               lnporcentotal := 0.00;
     END;

     /* Porcentaje Detalle */
         FOR i IN 1 .. rango_campana
     LOOP
            lscodperiodo := pscodanno || TRIM (TO_CHAR (i, '00'));

        BEGIN
               SELECT a.val_porc
                 INTO lnporcentempo
                 FROM edu_objet_asist_regio_perio a
                WHERE a.pais_cod_pais = pscodigopais
                  AND a.emco_cod_empr_come = pscodempresa
                  AND a.ccap_cod_curs_capa = pscodcurso
                  AND a.regi_cod_regi = pscodregion
                  AND a.cod_peri = lscodperiodo
                  AND a.est_regi <> '9';
        EXCEPTION
               WHEN NO_DATA_FOUND
               THEN
                  lnporcentempo := 0.00;
        END;

            lnporcen (i) := lnporcentempo;
     END LOOP;

     /* Insertando en la tabla temporal */
         INSERT INTO edu_tmp_objet_asist
                     (cod_usua, cod_pais, cod_empr_come, cod_curs_capa,
                      cod_regi, cod_zona, des_regi, des_zona,
                      val_ca01, val_ca02, val_ca03, val_ca04,
                      val_ca05, val_ca06, val_ca07, val_ca08,
                      val_ca09, val_ca10, val_ca11,
                      val_ca12, val_ca13, val_ca14,
                      val_ca15, val_ca16, val_ca17,
                      val_ca18, val_tota
                     )
              VALUES (psusuario, pscodigopais, pscodempresa, pscodcurso,
                      pscodregion, NULL, lsdescripcionregion, NULL,
                      lnporcen (1), lnporcen (2), lnporcen (3), lnporcen (4),
                      lnporcen (5), lnporcen (6), lnporcen (7), lnporcen (8),
                      lnporcen (9), lnporcen (10), lnporcen (11),
                      lnporcen (12), lnporcen (13), lnporcen (14),
                      lnporcen (15), lnporcen (16), lnporcen (17),
                      lnporcen (18), lnporcentotal
                     );
      ELSIF pstipo = 'Z'
      THEN                                                 -- En caso sea Zona
         OPEN cursorzona;

         LOOP
            FETCH cursorzona
            BULK COLLECT INTO tablaobjetivozona LIMIT w_filas;

            IF tablaobjetivozona.COUNT > 0
            THEN
               FOR x IN tablaobjetivozona.FIRST .. tablaobjetivozona.LAST
     LOOP
                  regobjetivozona := tablaobjetivozona (x);

              /* Porcentaje Total */
              BEGIN
                     SELECT a.val_porc
                       INTO lnporcentotal
                       FROM edu_objet_asist_zona_anual a
                      WHERE a.pais_cod_pais = pscodigopais
                        AND a.emco_cod_empr_come = pscodempresa
                        AND a.ccap_cod_curs_capa = pscodcurso
                        AND a.cod_anno = pscodanno
                        AND a.regi_cod_regi = pscodregion
                        AND a.zona_cod_zona = regobjetivozona.codigozona
                        AND a.est_regi <> '9';
               EXCEPTION
                     WHEN NO_DATA_FOUND
                     THEN
                        lnporcentotal := 0.00;
               END;

               /* Porcentaje Detalle */
                  FOR i IN 1 .. rango_campana
               LOOP
                     lscodperiodo := pscodanno || TRIM (TO_CHAR (i, '00'));

                  BEGIN
                        SELECT a.val_porc
                          INTO lnporcentempo
                          FROM edu_objet_asist_zona_perio a
                         WHERE a.pais_cod_pais = pscodigopais
                           AND a.emco_cod_empr_come = pscodempresa
                           AND a.ccap_cod_curs_capa = pscodcurso
                           AND a.regi_cod_regi = pscodregion
                           AND a.zona_cod_zona = regobjetivozona.codigozona
                           AND a.cod_peri = lscodperiodo
                           AND a.est_regi <> '9';
                  EXCEPTION
                        WHEN NO_DATA_FOUND
                        THEN
                           lnporcentempo := 0.00;
                  END;

                     lnporcen (i) := lnporcentempo;
               END LOOP;

               /* insertando en tabla temporal */
                  INSERT INTO edu_tmp_objet_asist
                              (cod_usua, cod_pais, cod_empr_come,
                               cod_curs_capa, cod_regi,
                               cod_zona,
                               des_regi,
                               des_zona, val_ca01,
                               val_ca02, val_ca03, val_ca04,
                               val_ca05, val_ca06, val_ca07,
                               val_ca08, val_ca09, val_ca10,
                               val_ca11, val_ca12, val_ca13,
                               val_ca14, val_ca15, val_ca16,
                               val_ca17, val_ca18, val_tota
                              )
                       VALUES (psusuario, pscodigopais, pscodempresa,
                               pscodcurso, pscodregion,
                               regobjetivozona.codigozona,
                               lsdescripcionregion,
                               regobjetivozona.descripcionzona, lnporcen (1),
                               lnporcen (2), lnporcen (3), lnporcen (4),
                               lnporcen (5), lnporcen (6), lnporcen (7),
                               lnporcen (8), lnporcen (9), lnporcen (10),
                               lnporcen (11), lnporcen (12), lnporcen (13),
                               lnporcen (14), lnporcen (15), lnporcen (16),
                               lnporcen (17), lnporcen (18), lnporcentotal
                              );
          END LOOP;
       END IF;

            EXIT WHEN cursorzona%NOTFOUND;
     END LOOP;

         CLOSE cursorzona;
  END IF;
EXCEPTION
      WHEN OTHERS
      THEN
     ln_sqlcode := SQLCODE;
         ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
         raise_application_error (-20123,
                                     'ERROR EDU_PR_INSER_OBJET_ASIST_TEMPO: '
                                  || ls_sqlerrm
                                 );
   END edu_pr_inser_objet_asist_tempo;

/***************************************************************************
Descripcion       : Procedimiento principal que actualiza los porcentajes de
                    objetivos de asistencia en las tablas respectivas
Fecha Creacion    : 10/09/2007
Autor             : Carlos Bazalar
Parametros:
          psCodigoPais  : Codigo de Pais
          psCodEmpresa  : Codigo de Empresa
          psCodCurso    : Codigo de Curso
          psCodAnno     : Año
          psTipo        : 'P': Pais    'R' : Region   'Z': Zona
          psUsuario     : Usuario
          psCodRegion   : Codigo de Region
          psCodZona     : Codigo de Zona
          psPorcentaje01 : % Campaña C01
          psPorcentaje02 : % Campaña C02
          psPorcentaje03 : % Campaña C03
               ...
          psPorcentaje18 : % Campaña C18
          psPorcentajeTotal : % Campaña Total
***************************************************************************/
PROCEDURE EDU_PR_ACTUA_OBJET_ASIST(
  psCodigoPais      VARCHAR2,
  psCodEmpresa      VARCHAR2,
  psCodCurso        VARCHAR2,
  psCodAnno         VARCHAR2,
  psTipo            VARCHAR2,
  psUsuario         VARCHAR2,
  psCodRegion       VARCHAR2,
  psCodZona         VARCHAR2,
  psPorcentaje01    VARCHAR2,
  psPorcentaje02    VARCHAR2,
  psPorcentaje03    VARCHAR2,
  psPorcentaje04    VARCHAR2,
  psPorcentaje05    VARCHAR2,
  psPorcentaje06    VARCHAR2,
  psPorcentaje07    VARCHAR2,
  psPorcentaje08    VARCHAR2,
  psPorcentaje09    VARCHAR2,
  psPorcentaje10    VARCHAR2,
  psPorcentaje11    VARCHAR2,
  psPorcentaje12    VARCHAR2,
  psPorcentaje13    VARCHAR2,
  psPorcentaje14    VARCHAR2,
  psPorcentaje15    VARCHAR2,
  psPorcentaje16    VARCHAR2,
  psPorcentaje17    VARCHAR2,
  psPorcentaje18    VARCHAR2,
  psPorcentajeTotal VARCHAR2
)
IS
  lsCodPeriodo VARCHAR2(6);
  lsValor      VARCHAR2(10);
  lnValor      NUMBER;
  lbInsertar   BOOLEAN;
  lsTemporal   VARCHAR2(10);
BEGIN
  BEGIN
    lnValor := TO_NUMBER(psPorcentajeTotal,'999.00');
  EXCEPTION
  WHEN OTHERS THEN
    lnValor := 0;
  END;

  /* Actualizando año por Periodo */
  IF psTipo = 'P' THEN -- CASO DE PAIS

     /* Verificando si existe Registro */
     lbInsertar := FALSE;
     BEGIN
          SELECT A.PAIS_COD_PAIS
          INTO lsTemporal
          FROM EDU_OBJET_ASIST_PAIS_ANUAL A
          WHERE A.PAIS_COD_PAIS = psCodigoPais
            AND A.EMCO_COD_EMPR_COME = psCodEmpresa
            AND A.CCAP_COD_CURS_CAPA = psCodCurso
            AND A.COD_ANNO = psCodAnno;
     EXCEPTION
     WHEN NO_DATA_FOUND THEN
         lbInsertar := TRUE;
     END;

     /* Actualizando Registro del Porcentaje Anual */
     IF lbInsertar THEN
        INSERT INTO EDU_OBJET_ASIST_PAIS_ANUAL(
            PAIS_COD_PAIS, EMCO_COD_EMPR_COME,
            CCAP_COD_CURS_CAPA, COD_ANNO,
            VAL_PORC,
            USU_DIGI, FEC_DIGI,
            USU_MODI, FEC_MODI,
            EST_REGI)
         VALUES (
           psCodigoPais, psCodEmpresa,
           psCodCurso, psCodAnno,
           lnValor,
           psUsuario, SYSDATE,
           NULL, NULL,
           '1');
     ELSE
        UPDATE EDU_OBJET_ASIST_PAIS_ANUAL A
        SET A.VAL_PORC = lnValor,
            A.USU_MODI = psUsuario,
            A.FEC_MODI = SYSDATE
        WHERE A.PAIS_COD_PAIS = psCodigoPais
          AND A.EMCO_COD_EMPR_COME = psCodEmpresa
          AND A.CCAP_COD_CURS_CAPA = psCodCurso
          AND A.COD_ANNO = psCodAnno;
     END IF;

  ELSIF psTipo = 'R' THEN -- CASO DE REGION

     /* Verificando si existe Registro */
     lbInsertar := FALSE;
     BEGIN
          SELECT A.REGI_COD_REGI
          INTO lsTemporal
          FROM EDU_OBJET_ASIST_REGIO_ANUAL A
          WHERE A.PAIS_COD_PAIS = psCodigoPais
            AND A.EMCO_COD_EMPR_COME = psCodEmpresa
            AND A.CCAP_COD_CURS_CAPA = psCodCurso
            AND A.REGI_COD_REGI = psCodRegion
            AND A.COD_ANNO = psCodAnno;
     EXCEPTION
     WHEN NO_DATA_FOUND THEN
         lbInsertar := TRUE;
     END;

     /* Actualizando Registro del Porcentaje Anual */
     IF lbInsertar THEN
        INSERT INTO EDU_OBJET_ASIST_REGIO_ANUAL(
            PAIS_COD_PAIS, EMCO_COD_EMPR_COME,
            CCAP_COD_CURS_CAPA,
            REGI_COD_REGI, COD_ANNO,
            VAL_PORC,
            USU_DIGI, FEC_DIGI,
            USU_MODI, FEC_MODI,
            EST_REGI)
         VALUES (
           psCodigoPais, psCodEmpresa,
           psCodCurso,
           psCodRegion,  psCodAnno,
           lnValor,
           psUsuario, SYSDATE,
           NULL, NULL,
           '1');
     ELSE
        UPDATE EDU_OBJET_ASIST_REGIO_ANUAL A
        SET A.VAL_PORC = lnValor,
            A.USU_MODI = psUsuario,
            A.FEC_MODI = SYSDATE
        WHERE A.PAIS_COD_PAIS = psCodigoPais
          AND A.EMCO_COD_EMPR_COME = psCodEmpresa
          AND A.CCAP_COD_CURS_CAPA = psCodCurso
          AND A.REGI_COD_REGI = psCodRegion
          AND A.COD_ANNO = psCodAnno;
     END IF;

  ELSIF psTipo = 'Z' THEN  --CASO DE ZONA

     /* Verificando si existe Registro */
     lbInsertar := FALSE;
     BEGIN
          SELECT A.REGI_COD_REGI
          INTO lsTemporal
          FROM EDU_OBJET_ASIST_ZONA_ANUAL A
          WHERE A.PAIS_COD_PAIS = psCodigoPais
            AND A.EMCO_COD_EMPR_COME = psCodEmpresa
            AND A.CCAP_COD_CURS_CAPA = psCodCurso
            AND A.REGI_COD_REGI = psCodRegion
            AND A.ZONA_COD_ZONA = psCodZona
            AND A.COD_ANNO = psCodAnno;
     EXCEPTION
     WHEN NO_DATA_FOUND THEN
         lbInsertar := TRUE;
     END;

     /* Actualizando Registro del Porcentaje Anual */
     IF lbInsertar THEN
         INSERT INTO EDU_OBJET_ASIST_ZONA_ANUAL(
            PAIS_COD_PAIS, EMCO_COD_EMPR_COME,
            CCAP_COD_CURS_CAPA,
            REGI_COD_REGI, ZONA_COD_ZONA,
            COD_ANNO, VAL_PORC,
            USU_DIGI, FEC_DIGI,
            USU_MODI, FEC_MODI,
            EST_REGI)
         VALUES (
           psCodigoPais, psCodEmpresa,
           psCodCurso,
           psCodRegion,  psCodZona,
           psCodAnno, lnValor,
           psUsuario, SYSDATE,
           NULL, NULL,
           '1');
     ELSE
        UPDATE EDU_OBJET_ASIST_ZONA_ANUAL A
        SET A.VAL_PORC = lnValor,
            A.USU_MODI = psUsuario,
            A.FEC_MODI = SYSDATE
        WHERE A.PAIS_COD_PAIS = psCodigoPais
          AND A.EMCO_COD_EMPR_COME = psCodEmpresa
          AND A.CCAP_COD_CURS_CAPA = psCodCurso
          AND A.REGI_COD_REGI = psCodRegion
          AND A.ZONA_COD_ZONA = psCodZona
          AND A.COD_ANNO = psCodAnno;
     END IF;
  END IF;

  /* Actualizando detalle por Periodo */
  FOR Y IN 1..RANGO_CAMPANA
  LOOP
    lsCodPeriodo := psCodAnno || trim(TO_CHAR(Y,'00'));
    IF Y = 1 THEN
       lsValor := psPorcentaje01;
    ELSIF Y = 2 THEN
       lsValor := psPorcentaje02;
    ELSIF Y = 3 THEN
       lsValor := psPorcentaje03;
    ELSIF Y = 4 THEN
       lsValor := psPorcentaje04;
    ELSIF Y = 5 THEN
       lsValor := psPorcentaje05;
    ELSIF Y = 6 THEN
       lsValor := psPorcentaje06;
    ELSIF Y = 7 THEN
       lsValor := psPorcentaje07;
    ELSIF Y = 8 THEN
       lsValor := psPorcentaje08;
    ELSIF Y = 9 THEN
       lsValor := psPorcentaje09;
    ELSIF Y = 10 THEN
       lsValor := psPorcentaje10;
    ELSIF Y = 11 THEN
       lsValor := psPorcentaje11;
    ELSIF Y = 12 THEN
       lsValor := psPorcentaje12;
    ELSIF Y = 13 THEN
       lsValor := psPorcentaje13;
    ELSIF Y = 14 THEN
       lsValor := psPorcentaje14;
    ELSIF Y = 15 THEN
       lsValor := psPorcentaje15;
    ELSIF Y = 16 THEN
       lsValor := psPorcentaje16;
    ELSIF Y = 17 THEN
       lsValor := psPorcentaje17;
    ELSIF Y = 18 THEN
       lsValor := psPorcentaje18;
    END IF;

    BEGIN
    lnValor := TO_NUMBER(lsValor,'999.00');
    EXCEPTION
    WHEN OTHERS THEN
      lnValor := 0;
    END;

    IF psTipo = 'P' THEN --CASO DE PAIS

       /* Verificando si existe Registro */
       lbInsertar := FALSE;
       BEGIN
            SELECT A.PAIS_COD_PAIS
            INTO lsTemporal
            FROM EDU_OBJET_ASIST_PAIS_PERIO A
            WHERE A.PAIS_COD_PAIS = psCodigoPais
              AND A.EMCO_COD_EMPR_COME = psCodEmpresa
              AND A.CCAP_COD_CURS_CAPA = psCodCurso
              AND A.COD_PERI = lsCodPeriodo;
       EXCEPTION
       WHEN NO_DATA_FOUND THEN
           lbInsertar := TRUE;
       END;

       /* Actualizando Registro del Porcentaje x Periodo */
       IF lbInsertar THEN
           INSERT INTO EDU_OBJET_ASIST_PAIS_PERIO(
              PAIS_COD_PAIS, EMCO_COD_EMPR_COME,
              CCAP_COD_CURS_CAPA,
              COD_PERI,
              VAL_PORC,
              USU_DIGI, FEC_DIGI,
              USU_MODI, FEC_MODI,
              EST_REGI)
           VALUES (
             psCodigoPais, psCodEmpresa,
             psCodCurso,
             lsCodPeriodo,
             lnValor,
             psUsuario, SYSDATE,
             NULL, NULL,
             '1');
       ELSE
          UPDATE EDU_OBJET_ASIST_PAIS_PERIO A
          SET A.VAL_PORC = lnValor,
              A.USU_MODI = psUsuario,
              A.FEC_MODI = SYSDATE
          WHERE A.PAIS_COD_PAIS = psCodigoPais
            AND A.EMCO_COD_EMPR_COME = psCodEmpresa
            AND A.CCAP_COD_CURS_CAPA = psCodCurso
            AND A.COD_PERI = lsCodPeriodo;
       END IF;

    ELSIF psTipo = 'R' THEN --CASO DE REGION

       /* Verificando si existe Registro */
       lbInsertar := FALSE;
       BEGIN
            SELECT A.REGI_COD_REGI
            INTO lsTemporal
            FROM EDU_OBJET_ASIST_REGIO_PERIO A
            WHERE A.PAIS_COD_PAIS = psCodigoPais
              AND A.EMCO_COD_EMPR_COME = psCodEmpresa
              AND A.CCAP_COD_CURS_CAPA = psCodCurso
              AND A.REGI_COD_REGI = psCodRegion
              AND A.COD_PERI = lsCodPeriodo;
       EXCEPTION
       WHEN NO_DATA_FOUND THEN
           lbInsertar := TRUE;
       END;

       /* Actualizando Registro del Porcentaje x Periodo */
       IF lbInsertar THEN
           INSERT INTO EDU_OBJET_ASIST_REGIO_PERIO(
              PAIS_COD_PAIS, EMCO_COD_EMPR_COME,
              CCAP_COD_CURS_CAPA,
              REGI_COD_REGI, COD_PERI,
              VAL_PORC,
              USU_DIGI, FEC_DIGI,
              USU_MODI, FEC_MODI,
              EST_REGI)
           VALUES (
             psCodigoPais, psCodEmpresa,
             psCodCurso,
             psCodRegion,  lsCodPeriodo,
             lnValor,
             psUsuario, SYSDATE,
             NULL, NULL,
             '1');
       ELSE
          UPDATE EDU_OBJET_ASIST_REGIO_PERIO A
          SET A.VAL_PORC = lnValor,
              A.USU_MODI = psUsuario,
              A.FEC_MODI = SYSDATE
          WHERE A.PAIS_COD_PAIS = psCodigoPais
            AND A.EMCO_COD_EMPR_COME = psCodEmpresa
            AND A.CCAP_COD_CURS_CAPA = psCodCurso
            AND A.REGI_COD_REGI = psCodRegion
            AND A.COD_PERI = lsCodPeriodo;
       END IF;

    ELSIF psTipo = 'Z' THEN  --CASO DE ZONA
       /* Verificando si existe Registro */
       lbInsertar := FALSE;
       BEGIN
            SELECT A.REGI_COD_REGI
            INTO lsTemporal
            FROM EDU_OBJET_ASIST_ZONA_PERIO A
            WHERE A.PAIS_COD_PAIS = psCodigoPais
              AND A.EMCO_COD_EMPR_COME = psCodEmpresa
              AND A.CCAP_COD_CURS_CAPA = psCodCurso
              AND A.REGI_COD_REGI = psCodRegion
              AND A.ZONA_COD_ZONA = psCodZona
              AND A.COD_PERI = lsCodPeriodo;
       EXCEPTION
       WHEN NO_DATA_FOUND THEN
           lbInsertar := TRUE;
       END;

       /* Actualizando Registro del Porcentaje x Periodo */
       IF lbInsertar THEN
           INSERT INTO EDU_OBJET_ASIST_ZONA_PERIO(
              PAIS_COD_PAIS, EMCO_COD_EMPR_COME,
              CCAP_COD_CURS_CAPA,
              REGI_COD_REGI, ZONA_COD_ZONA,
              COD_PERI, VAL_PORC,
              USU_DIGI, FEC_DIGI,
              USU_MODI, FEC_MODI,
              EST_REGI)
           VALUES (
             psCodigoPais, psCodEmpresa,
             psCodCurso,
             psCodRegion,  psCodZona,
             lsCodPeriodo, lnValor,
             psUsuario, SYSDATE,
             NULL, NULL,
             '1');
       ELSE
          UPDATE EDU_OBJET_ASIST_ZONA_PERIO A
          SET A.VAL_PORC = lnValor,
              A.USU_MODI = psUsuario,
              A.FEC_MODI = SYSDATE
          WHERE A.PAIS_COD_PAIS = psCodigoPais
            AND A.EMCO_COD_EMPR_COME = psCodEmpresa
            AND A.CCAP_COD_CURS_CAPA = psCodCurso
            AND A.REGI_COD_REGI = psCodRegion
            AND A.ZONA_COD_ZONA = psCodZona
            AND A.COD_PERI = lsCodPeriodo;
       END IF;
    END IF;
  END LOOP;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_ACTUA_OBJET_ASIST: '||ls_sqlerrm);
END EDU_PR_ACTUA_OBJET_ASIST;


  /***************************************************************************************************
   Descripcion       : Procedimiento que realiza la generaciòn de planillas de
                       las consultoras
  Fecha Creacion      : 12/09/2007
  Fecha Modificacion  : 02/10/2007
  Parametros Entrada  : psCodigoPais : Codigo del Pais del Usuario Logueado (ejm: PE es de PERU)
                      : psCodEmpresa : Código de Empresa del Usuario Logeado
                      : psUsuario    : Código de Usuario Logeado
                      : psTipoProceso: N Proceso Normal R:Reproceso
                      : psCodProceso : Código de Proceso
                      : psCodParam   : Código de Parámetro

  Parametros Salida   : psMensajeError: Mensaje de Error

  Autor               : Robinson Vela Bardales - rvela@belcorp.biz
  Modificado por       : Sergio Buchelli Silva  - sbuchelli@belcorp.biz
  Version             : Final (Beta|Final)
  Cambios Importantes :
  ****************************************************************************************************/
   PROCEDURE edu_pr_gener_plani_progr (
      pscodigopais           VARCHAR2,
      pscodempresa           VARCHAR2,
      psusuario              VARCHAR2,
      pstipoproceso          VARCHAR2,        /*N Proceso Normal R:Reproceso*/
      pscodproceso           VARCHAR2,
      pscodparam             VARCHAR2,
      psmensajeerror   OUT   VARCHAR2
    )
    IS
      TYPE tregunidadadm IS RECORD (
         cod_pais             edu_histo_aptas.pais_cod_pais%TYPE,
         cod_empr_come        edu_histo_aptas.emco_cod_empr_come%TYPE,
         cod_clie             edu_maest_clien.cod_clie%TYPE,
         cod_regi             edu_maest_clien.cod_regi%TYPE,
         cod_zona             edu_maest_clien.cod_zona%TYPE,
         cod_secc             edu_maest_clien.cod_secc%TYPE,
         cod_terr             edu_maest_clien.cod_terr%TYPE,
         cod_curs_capa        edu_histo_aptas.ccap_cod_curs_capa%TYPE,
         cod_inst             edu_regio.inst_cod_inst%TYPE,
         tip_cali_apta        edu_histo_aptas.tip_cali_apta%TYPE,
         ind_curs_cost        edu_histo_aptas.ind_curs_cost%TYPE,
         ind_curs_fact        edu_histo_aptas.ind_curs_fact%TYPE,
         cam_fact_curs        edu_histo_aptas.cam_fact_curs%TYPE,
         ult_camp_prog_dict   edu_histo_aptas.ult_camp_prog_dict%TYPE,
         cod_plan_prog        edu_histo_aptas.cod_plan_prog%TYPE
      );

      TYPE tabla_maestra IS TABLE OF tregunidadadm;

      tablaregistro          tabla_maestra;
      regdetalle             tregunidadadm;
      regdetalleaux          tregunidadadm;
      regdetalleauxregion    tregunidadadm;
      lbinsertar             BOOLEAN                         := FALSE;
      tablaparametro         edu_param_progr_capac%ROWTYPE;
      tablacontrolplanilla   edu_contr_gener_plani%ROWTYPE;
      lbflagfirstrow         BOOLEAN                         := TRUE;
      lscodperiodo           VARCHAR2 (06);
      lncontador             NUMBER;
      lnnuminvi              edu_histo_aptas.num_invi%TYPE;
	  lnContPlani			 NUMBER;

         /* Historico de Planillas para la reprogramaciòn*/
      CURSOR cursorhistoricoplanilla (piv_periodo VARCHAR2)
         IS
         SELECT DISTINCT a.pais_cod_pais, a.emco_cod_empr_come,
                         a.ccap_cod_curs_capa, a.cod_plan_prog,
                         a.regi_cod_regi
                    FROM edu_histo_plani_instr a, edu_histo_aptas b
                   WHERE a.pais_cod_pais = pscodigopais
                     AND a.emco_cod_empr_come = pscodempresa
                     AND b.ult_camp_prog_dict = piv_periodo
                     AND a.sit_plan_prog = situacion_planilla_activo
                     AND b.est_capa = indicador_programada
                     AND b.pais_cod_pais = a.pais_cod_pais
                     AND b.emco_cod_empr_come = a.emco_cod_empr_come
                     AND b.ccap_cod_curs_capa = a.ccap_cod_curs_capa
                     AND b.cod_plan_prog = a.cod_plan_prog
                     AND EXISTS (
                            SELECT x.cod_proc
                              FROM edu_gtt_param_proce x
                             WHERE x.cod_proc = pscodproceso
                               AND x.cod_para = pscodparam
                               AND (   (UPPER (x.val_para_varc) =
                                                               UPPER ('TODOS')
                    )
                                    OR (TRIM (x.val_para_varc) =
                                                               a.regi_cod_regi
                   )
                                   ))
                ORDER BY a.regi_cod_regi, a.cod_plan_prog;

        /*  Cursor de Aptas   */
      CURSOR cursorregistro (
         piv_pais      VARCHAR,
         piv_empresa   VARCHAR,
         piv_curso     VARCHAR2
      )
        IS
         SELECT   a.pais_cod_pais, a.emco_cod_empr_come, a.clie_cod_clie,
                  NVL (b.cod_regi, ''), NVL (b.cod_zona, ''),
                  NVL (b.cod_secc, ''), NVL (b.cod_terr, ''),
                  a.ccap_cod_curs_capa, c.inst_cod_inst, a.tip_cali_apta,
                  a.ind_curs_cost, a.ind_curs_fact, a.cam_fact_curs,
                  a.ult_camp_prog_dict, a.cod_plan_prog
             FROM edu_histo_aptas a, edu_maest_clien b, edu_regio c
            WHERE a.pais_cod_pais = piv_pais
              AND a.emco_cod_empr_come = piv_empresa
              AND a.ccap_cod_curs_capa = piv_curso
              AND a.est_capa = indicador_por_programar
              AND b.pais_cod_pais = a.pais_cod_pais
              AND b.emco_cod_empr_come = a.emco_cod_empr_come
              AND b.cod_clie = a.clie_cod_clie
              AND c.pais_cod_pais = b.pais_cod_pais
              AND c.emco_cod_empr_come = b.emco_cod_empr_come
              AND c.cod_regi = b.cod_regi
              AND EXISTS (
                     SELECT x.cod_proc
                       FROM edu_gtt_param_proce x
                      WHERE x.cod_proc = pscodproceso
                        AND x.cod_para = pscodparam
                        AND (   (UPPER (x.val_para_varc) = UPPER ('TODOS'))
                             OR (TRIM (x.val_para_varc) = b.cod_regi)
                            ))
         ORDER BY b.pais_cod_pais,
                  b.emco_cod_empr_come,
                  b.cod_regi,
                  b.cod_zona,
                  b.cod_secc,
                  b.cod_terr;

        /*Lista de Cursos a Programar por pais*/
      CURSOR cursorcursos
        IS
         SELECT   a.pais_cod_pais, a.emco_cod_empr_come, a.cod_curs_capa,
                  a.nom_curs_capa
             FROM edu_param_curso_capac a
            WHERE a.pais_cod_pais = pscodigopais
              AND a.emco_cod_empr_come = pscodempresa
         ORDER BY a.pais_cod_pais, a.emco_cod_empr_come, a.cod_curs_capa;
BEGIN
      psmensajeerror := '';
    --DBMS_OUTPUT.PUT_LINE('INICIANDO ' || tablaParametro.NIV_UNID_ADMI);
    /*Obtenemos la Campaña de Programaciòn*/
      lscodperiodo :=
         edu_pkg_calif.edu_fn_devue_campa_proce_actua (pscodigopais,
                                                       pscodempresa
                                                      );

 --DBMS_OUTPUT.PUT_LINE('PERIODO  ' || lsCodPeriodo );
      IF (pstipoproceso = 'R')
      THEN
         /* Realizamos las siguientes Validaciones Para tipo Reproceso */
         DBMS_OUTPUT.put_line ('REPROCESO  ');

         SELECT COUNT (1)
           INTO lncontador
           FROM edu_histo_plani_instr a, edu_histo_aptas b
          WHERE a.pais_cod_pais = pscodigopais
            AND a.emco_cod_empr_come = pscodempresa
            AND b.ult_camp_prog_dict = lscodperiodo
            AND a.sit_plan_prog = situacion_planilla_activo
            AND b.est_capa = indicador_programada
            AND b.pais_cod_pais = a.pais_cod_pais
            AND b.emco_cod_empr_come = a.emco_cod_empr_come
            AND b.ccap_cod_curs_capa = a.ccap_cod_curs_capa
            AND b.cod_plan_prog = a.cod_plan_prog
            AND EXISTS (
                   SELECT x.cod_proc
                     FROM edu_gtt_param_proce x
                    WHERE x.cod_proc = pscodproceso
                      AND x.cod_para = pscodparam
                      AND (   (UPPER (x.val_para_varc) = UPPER ('TODOS'))
                           OR (TRIM (x.val_para_varc) = a.regi_cod_regi)
                          ));

  -- DBMS_OUTPUT.PUT_LINE('lnContador ' || lnContador);

         IF (lncontador = 0 )
         THEN
            ls_sqlerrm :=
               'procesoEDUGenerarPlanillaProgramacion.msg.validarPlanillasProgramadas';
            psmensajeerror := ls_sqlerrm;
           ROLLBACK;
           RETURN;
        END IF;

        /* Realizamos las siguientes Validaciones */
         SELECT COUNT (1)
           INTO lncontador
           FROM edu_histo_plani_instr a, edu_histo_aptas b
          WHERE a.pais_cod_pais = pscodigopais
            AND a.emco_cod_empr_come = pscodempresa
            AND b.ult_camp_prog_dict = lscodperiodo
            AND a.sit_plan_prog = situacion_planilla_procesado
         --AND B.EST_CAPA             = INDICADOR_CAPACITADA
            AND b.pais_cod_pais = a.pais_cod_pais
            AND b.emco_cod_empr_come = a.emco_cod_empr_come
            AND b.ccap_cod_curs_capa = a.ccap_cod_curs_capa
            AND b.cod_plan_prog = a.cod_plan_prog
            AND EXISTS (
                   SELECT x.cod_proc
                     FROM edu_gtt_param_proce x
                    WHERE x.cod_proc = pscodproceso
                      AND x.cod_para = pscodparam
                      AND (   (UPPER (x.val_para_varc) = UPPER ('TODOS'))
                           OR (TRIM (x.val_para_varc) = a.regi_cod_regi)
                          ));

         IF (lncontador > 0)
         THEN
            ls_sqlerrm :=
               'procesoEDUGenerarPlanillaProgramacion.msg.validarPlanillasProcesadas';
            psmensajeerror := ls_sqlerrm;
           ROLLBACK;
           RETURN;
        END IF;

       /*Eliminamos los registros generados en la tabla de control de planilla*/
         FOR vcursorhistoricoplanilla IN cursorhistoricoplanilla (lscodperiodo)
         LOOP
             /*Restauramos Historico de Aptas */
            UPDATE edu_histo_aptas a
               SET a.cod_plan_prog = NULL,
                   a.ult_camp_prog_dict = NULL,
                   a.est_capa = indicador_por_programar,
              --   A.NUM_INVI = (NVL(A.NUM_INVI,0) - 1),
                   a.usu_modi = psusuario,
                   a.fec_modi = SYSDATE
             WHERE a.pais_cod_pais = vcursorhistoricoplanilla.pais_cod_pais
               AND a.emco_cod_empr_come =
                                   vcursorhistoricoplanilla.emco_cod_empr_come
               AND a.ccap_cod_curs_capa =
                                   vcursorhistoricoplanilla.ccap_cod_curs_capa
               AND a.cod_plan_prog = vcursorhistoricoplanilla.cod_plan_prog
               AND a.ult_camp_prog_dict = lscodperiodo
               AND a.est_capa = indicador_programada;

               /* Eliminamos la planilla por cliente*/
            DELETE FROM edu_plani_progr_curso b
                  WHERE b.pais_cod_pais =
                                        vcursorhistoricoplanilla.pais_cod_pais
                    AND b.emco_cod_empr_come =
                                   vcursorhistoricoplanilla.emco_cod_empr_come
                    AND b.ccap_cod_curs_capa =
                                   vcursorhistoricoplanilla.ccap_cod_curs_capa
                    AND b.cam_proc = lscodperiodo
                    AND b.cod_plan_prog =
                                        vcursorhistoricoplanilla.cod_plan_prog;

               /*Por ultimo eliminamos los registros del archivo de Control*/
            DELETE FROM edu_contr_gener_plani c
                  WHERE c.pais_cod_pais =
                                        vcursorhistoricoplanilla.pais_cod_pais
                    AND c.emco_cod_empr_come =
                                   vcursorhistoricoplanilla.emco_cod_empr_come
                    AND c.ccap_cod_curs_capa =
                                   vcursorhistoricoplanilla.ccap_cod_curs_capa
                    AND c.peri_cod_peri = lscodperiodo
                    AND c.regi_cod_regi =
                                        vcursorhistoricoplanilla.regi_cod_regi;

               /*Tambien eliminamos los registro de planillas por Instructora*/
            DELETE FROM edu_histo_plani_instr d
                  WHERE d.pais_cod_pais =
                                        vcursorhistoricoplanilla.pais_cod_pais
                    AND d.emco_cod_empr_come =
                                   vcursorhistoricoplanilla.emco_cod_empr_come
                    AND d.ccap_cod_curs_capa =
                                   vcursorhistoricoplanilla.ccap_cod_curs_capa
                    AND d.cod_plan_prog =
                                        vcursorhistoricoplanilla.cod_plan_prog;
       END LOOP;
    ELSE
  --DBMS_OUTPUT.PUT_LINE('PROCESO NORMAL');
      /*Validamos que no se haya ejecutado ya un proceso normal, dado que luego deberá ejecutarse un re-proceso */
      /*   SELECT COUNT (1)
           INTO lncontador
           FROM edu_histo_aptas a, edu_maest_clien b, edu_regio c
          WHERE a.pais_cod_pais = pscodigopais
            AND a.emco_cod_empr_come = pscodempresa
            AND a.ult_camp_prog_dict = lscodperiodo
            AND a.est_capa = indicador_programada
            AND b.pais_cod_pais = a.pais_cod_pais
            AND b.emco_cod_empr_come = a.emco_cod_empr_come
            AND b.cod_clie = a.clie_cod_clie
            AND c.pais_cod_pais = b.pais_cod_pais
            AND c.emco_cod_empr_come = b.emco_cod_empr_come
            AND c.cod_regi = b.cod_regi
            AND EXISTS (
                   SELECT x.cod_proc
                     FROM edu_gtt_param_proce x
                    WHERE x.cod_proc = pscodproceso
                      AND x.cod_para = pscodparam
                      AND (   (UPPER (x.val_para_varc) = UPPER ('TODOS'))
                           OR (TRIM (x.val_para_varc) = b.cod_regi)
                          ));*/


 -- Valida que exista planillas en la region en proceso
		 SELECT COUNT(1) into lnContPlani
		 FROM EDU_CONTR_GENER_PLANI A
		     WHERE A.PAIS_COD_PAIS = pscodigopais
            AND A.EMCO_COD_EMPR_COME = pscodempresa
            AND A.PERI_COD_PERI = lscodperiodo
			AND EXISTS (  SELECT x.cod_proc
                              FROM edu_gtt_param_proce x
                             WHERE x.cod_proc = pscodproceso
                               AND x.cod_para = pscodparam
                               AND (   (UPPER (x.val_para_varc) =
                                                               UPPER ('TODOS')
                                       )
                                    OR (TRIM (x.val_para_varc) = a.regi_cod_regi
                    )
                               ));

		--lncontador > 0 AND
         IF (lnContPlani > 0) --ESTAN PROGRAMDAS Y YA EXISTEN PLANILLAS
         THEN
            ls_sqlerrm :=
               'procesoEDUGenerarPlanillaProgramacion.msg.validarProcesoNormal';
            psmensajeerror := ls_sqlerrm;
           RETURN;
        END IF;

      /*Validamos que existan registros a procesar */
         SELECT COUNT (1)
           INTO lncontador
           FROM edu_histo_aptas a, edu_maest_clien b, edu_regio c
          WHERE a.pais_cod_pais = pscodigopais
            AND a.emco_cod_empr_come = pscodempresa
            AND a.est_capa = indicador_por_programar
            AND b.pais_cod_pais = a.pais_cod_pais
            AND b.emco_cod_empr_come = a.emco_cod_empr_come
            AND b.cod_clie = a.clie_cod_clie
            AND c.pais_cod_pais = b.pais_cod_pais
            AND c.emco_cod_empr_come = b.emco_cod_empr_come
            AND c.cod_regi = b.cod_regi
            AND EXISTS (
                   SELECT x.cod_proc
                     FROM edu_gtt_param_proce x
                    WHERE x.cod_proc = pscodproceso
                      AND x.cod_para = pscodparam
                      AND (   (UPPER (x.val_para_varc) = UPPER ('TODOS'))
                           OR (TRIM (x.val_para_varc) = b.cod_regi)
                          ));

         IF (lncontador = 0)
         THEN
            ls_sqlerrm :=
               'procesoEDUGenerarPlanillaProgramacion.msg.validarConsultoras';
            psmensajeerror := ls_sqlerrm;
           ROLLBACK;
           RETURN;
        END IF;
      END IF;               --fin de las validaciones , ahora corremos proceso

    /*Obtenemos los Parametros del Curso */
      SELECT a.pais_cod_pais, a.emco_cod_empr_come,
             a.cod_prog_capa, a.des_prog_capa,
             a.cod_plan_prog_actu, a.niv_unid_admi
        INTO tablaparametro.pais_cod_pais, tablaparametro.emco_cod_empr_come,
             tablaparametro.cod_prog_capa, tablaparametro.des_prog_capa,
             tablaparametro.cod_plan_prog_actu, tablaparametro.niv_unid_admi
        FROM edu_param_progr_capac a
       WHERE pais_cod_pais = pscodigopais
         AND emco_cod_empr_come = pscodempresa
         AND cod_prog_capa = codigo_programa_capacitacion;

 --Se recorre por curso ,
    /* Proceso de Actualizar Tabla de historico x Intructora */
      FOR vcursorcursos IN cursorcursos
      LOOP
         lbflagfirstrow := TRUE;           --INICIAMOS EL RECORRIDO POR CURSO

         OPEN cursorregistro (vcursorcursos.pais_cod_pais,
                              vcursorcursos.emco_cod_empr_come,
                              vcursorcursos.cod_curs_capa
                             );

         LOOP
            FETCH cursorregistro
            BULK COLLECT INTO tablaregistro LIMIT w_filas;

            IF tablaregistro.COUNT > 0
            THEN
               FOR x IN tablaregistro.FIRST .. tablaregistro.LAST
  LOOP
                  regdetalle := tablaregistro (x);
                  lbinsertar := FALSE;

             /*Solo para el Primer Registro*/
    /*
      Se marca que registro se insertara en la TABLA EDU_HISTO_PLANI_INSTR
      SE busca filas distintas , es decir si la unidad adm es zona , se maracarn las
      que las regiones  asi como sus zonas distintas

    */
                  IF (lbflagfirstrow)
                  THEN
                     lbflagfirstrow := FALSE;
                     regdetalleaux := tablaregistro (x);
                     regdetalleauxregion := tablaregistro (x);
                     lbinsertar := TRUE;

                     IF (NOT TRIM (tablaparametro.cod_plan_prog_actu) IS NULL
                        )
                     THEN
                        tablaparametro.cod_plan_prog_actu :=
                           TO_CHAR
                               (  TO_NUMBER (tablaparametro.cod_plan_prog_actu)
                                + 1
                               );
                  ELSE
                        tablaparametro.cod_plan_prog_actu := '1';
                  END IF;

                     tablacontrolplanilla.cod_plan_prog_inic :=
                                             tablaparametro.cod_plan_prog_actu;
                     tablacontrolplanilla.cod_plan_prog_fina :=
                                             tablaparametro.cod_plan_prog_actu;
             ELSE
                     IF (tablaparametro.niv_unid_admi =
                                                  param_curso_nivel_adm_region
                        )
                     THEN
                        IF (regdetalle.cod_regi <> regdetalleaux.cod_regi)
                        THEN
                           tablaparametro.cod_plan_prog_actu :=
                              TO_CHAR
                                 (  TO_NUMBER
                                            (tablaparametro.cod_plan_prog_actu)
                                  + 1
                                 );
                           tablacontrolplanilla.cod_plan_prog_fina :=
                                             tablaparametro.cod_plan_prog_actu;
                           lbinsertar := TRUE;
                           regdetalleaux := regdetalle;
                      END IF;
                     ELSIF (tablaparametro.niv_unid_admi =
                                                    param_curso_nivel_adm_zona
                           )
                     THEN
                        IF (regdetalle.cod_regi = regdetalleaux.cod_regi)
                        THEN
                           IF (regdetalle.cod_zona <> regdetalleaux.cod_zona
                              )
                           THEN
                              tablaparametro.cod_plan_prog_actu :=
                                 TO_CHAR
                                    (  TO_NUMBER
                                            (tablaparametro.cod_plan_prog_actu)
                                     + 1
                                    );
                              tablacontrolplanilla.cod_plan_prog_fina :=
                                             tablaparametro.cod_plan_prog_actu;
                              lbinsertar := TRUE;
                              regdetalleaux := regdetalle;
                         END IF;
                      ELSE
                           tablaparametro.cod_plan_prog_actu :=
                              TO_CHAR
                                 (  TO_NUMBER
                                            (tablaparametro.cod_plan_prog_actu)
                                  + 1
                                 );
                           tablacontrolplanilla.cod_plan_prog_fina :=
                                             tablaparametro.cod_plan_prog_actu;
                           lbinsertar := TRUE;
                           regdetalleaux := regdetalle;
                      END IF;
                     ELSIF (tablaparametro.niv_unid_admi =
                                                 param_curso_nivel_adm_seccion
                           )
                     THEN
                        IF (regdetalle.cod_regi = regdetalleaux.cod_regi)
                        THEN
                           IF (regdetalle.cod_zona = regdetalleaux.cod_zona
                              )
                           THEN
                              IF (regdetalle.cod_secc <>
                                                        regdetalleaux.cod_secc
                                 )
                              THEN
                                 tablaparametro.cod_plan_prog_actu :=
                                    TO_CHAR
                                       (  TO_NUMBER
                                             (tablaparametro.cod_plan_prog_actu
                                             )
                                        + 1
                                       );
                                 tablacontrolplanilla.cod_plan_prog_fina :=
                                             tablaparametro.cod_plan_prog_actu;
                                 lbinsertar := TRUE;
                                 regdetalleaux := regdetalle;
                           END IF;
                        ELSE
                              tablaparametro.cod_plan_prog_actu :=
                                 TO_CHAR
                                    (  TO_NUMBER
                                            (tablaparametro.cod_plan_prog_actu)
                                     + 1
                                    );
                              tablacontrolplanilla.cod_plan_prog_fina :=
                                             tablaparametro.cod_plan_prog_actu;
                              lbinsertar := TRUE;
                              regdetalleaux := regdetalle;
                        END IF;
                     ELSE
                           tablaparametro.cod_plan_prog_actu :=
                              TO_CHAR
                                 (  TO_NUMBER
                                            (tablaparametro.cod_plan_prog_actu)
                                  + 1
                                 );
                           tablacontrolplanilla.cod_plan_prog_fina :=
                                             tablaparametro.cod_plan_prog_actu;
                           lbinsertar := TRUE;
                           regdetalleaux := regdetalle;
                      END IF;
                     ELSIF (tablaparametro.niv_unid_admi =
                                                    param_curso_nivel_adm_terr
                           )
                     THEN
                        IF (regdetalle.cod_regi = regdetalleaux.cod_regi)
                        THEN
                           IF (regdetalle.cod_zona = regdetalleaux.cod_zona
                              )
                           THEN
                              IF (regdetalle.cod_secc = regdetalleaux.cod_secc
                                 )
                              THEN
                                 IF (regdetalle.cod_terr <>
                                                        regdetalleaux.cod_terr
                                    )
                                 THEN
                                    tablaparametro.cod_plan_prog_actu :=
                                       TO_CHAR
                                          (  TO_NUMBER
                                                (tablaparametro.cod_plan_prog_actu
                                                )
                                           + 1
                                          );
                                    tablacontrolplanilla.cod_plan_prog_fina :=
                                             tablaparametro.cod_plan_prog_actu;
                                    lbinsertar := TRUE;
                                    regdetalleaux := regdetalle;
                               END IF;
                            ELSE
                                 tablaparametro.cod_plan_prog_actu :=
                                    TO_CHAR
                                       (  TO_NUMBER
                                             (tablaparametro.cod_plan_prog_actu
                                             )
                                        + 1
                                       );
                                 tablacontrolplanilla.cod_plan_prog_fina :=
                                             tablaparametro.cod_plan_prog_actu;
                                 lbinsertar := TRUE;
                                 regdetalleaux := regdetalle;
                            END IF;
                          ELSE
                              tablaparametro.cod_plan_prog_actu :=
                                 TO_CHAR
                                    (  TO_NUMBER
                                            (tablaparametro.cod_plan_prog_actu)
                                     + 1
                                    );
                              tablacontrolplanilla.cod_plan_prog_fina :=
                                             tablaparametro.cod_plan_prog_actu;
                              lbinsertar := TRUE;
                              regdetalleaux := regdetalle;
                          END IF;
                      ELSE
                           tablaparametro.cod_plan_prog_actu :=
                              TO_CHAR
                                 (  TO_NUMBER
                                            (tablaparametro.cod_plan_prog_actu)
                                  + 1
                                 );
                           tablacontrolplanilla.cod_plan_prog_fina :=
                                             tablaparametro.cod_plan_prog_actu;
                           lbinsertar := TRUE;
                           regdetalleaux := regdetalle;
                      END IF;
                   ELSE
                        ls_sqlerrm :=
                           'procesoEDUGenerarPlanillaProgramacion.msg.validarNivelAdm';
                        psmensajeerror := ls_sqlerrm;
                       ROLLBACK;
                       RETURN;
                   END IF;
             END IF;

             /* Insertamos la Planilla por Instructora según el FLAG */
                  IF tablaparametro.niv_unid_admi =
                                                 param_curso_nivel_adm_seccion
                  THEN
                     regdetalle.cod_terr := NULL;
                  ELSIF tablaparametro.niv_unid_admi =
                                                    param_curso_nivel_adm_zona
                  THEN
                     regdetalle.cod_secc := NULL;
                     regdetalle.cod_terr := NULL;
                  ELSIF tablaparametro.niv_unid_admi =
                                                  param_curso_nivel_adm_region
                  THEN
                     regdetalle.cod_zona := NULL;
                     regdetalle.cod_secc := NULL;
                     regdetalle.cod_terr := NULL;
             END IF;

    /*
      Solo se insertara registros cumplan con la unidad administrativa , es decir
      si unidad administrativa es por zona , se insertara la region , y las zonas distintas
      obtenidas por el cursor
    */
                  IF (lbinsertar)
                  THEN
         -- DBMS_OUTPUT.PUT_LINE('INSERTANDO ZONAS DISTITAS '|| tablaParametro.COD_PLAN_PROG_ACTU || ' POS ' || x);
                   BEGIN
                     INSERT INTO edu_histo_plani_instr
                                 (pais_cod_pais, emco_cod_empr_come,
                                  ccap_cod_curs_capa,
                                  cod_plan_prog,
                                  regi_cod_regi, zona_cod_zona,
                                  cod_secc, cod_terr,
                                  inst_cod_inst,
                                  sit_plan_prog, usu_digi,
                                  fec_digi, est_regi
                                 )
                          VALUES (pscodigopais, pscodempresa,
                                  regdetalle.cod_curs_capa,
                                  tablaparametro.cod_plan_prog_actu,
                                  regdetalle.cod_regi, regdetalle.cod_zona,
                                  regdetalle.cod_secc, regdetalle.cod_terr,
                                  regdetalle.cod_inst,
                                  situacion_planilla_activo, psusuario,
                                  SYSDATE, estado_registro_activo
                                 );
                  EXCEPTION
                  	WHEN OTHERS
                  	THEN
                        ls_sqlerrm := 'procesoEDUGenerarPlanillaProgramacion.msg.validarZona';
                        psmensajeerror := ls_sqlerrm;
                        rollback;
                        RETURN;
									END;

                    /* Por Cada Planilla o Correlativo se debe Actualizar el parametro */
                     UPDATE edu_param_progr_capac a
                        SET a.cod_plan_prog_actu =
                                             tablaparametro.cod_plan_prog_actu,
                            a.usu_modi = psusuario,
                            a.fec_modi = SYSDATE
                      WHERE pais_cod_pais = pscodigopais
                        AND emco_cod_empr_come = pscodempresa
                        AND cod_prog_capa = codigo_programa_capacitacion;
             END IF;

    /* se ingresa en este punto cuando se encuntar regiones distintas
        inserta en la tabla EDU_CONTR_GENER_PLANI , nos dice en donde inicia
     una plainilla y en dodne termina para ese periodo
    */
                  IF (regdetalle.cod_regi <> regdetalleauxregion.cod_regi)
                  THEN
        --DBMS_OUTPUT.PUT_LINE('QUIEBRE '|| tablaParametro.COD_PLAN_PROG_ACTU );
                     SELECT COUNT (1)
                       INTO lncontador
                       FROM edu_contr_gener_plani
                      WHERE pais_cod_pais = pscodigopais
                        AND emco_cod_empr_come = pscodempresa
                        AND ccap_cod_curs_capa =
                                             regdetalleauxregion.cod_curs_capa
                        AND peri_cod_peri = lscodperiodo
                        AND regi_cod_regi = regdetalleauxregion.cod_regi;

                     IF (lncontador > 0)
                     THEN
        -- DBMS_OUTPUT.PUT_LINE('UPDATE EDU_CONTR_GENER_PLANI '|| tablaParametro.COD_PLAN_PROG_ACTU );
                        UPDATE edu_contr_gener_plani
                           SET cod_plan_prog_fina =
                                       tablacontrolplanilla.cod_plan_prog_fina,
                               usu_modi = psusuario,     --CAMPOS DE AUDITORIA
                               fec_modi = SYSDATE
                         WHERE pais_cod_pais = pscodigopais
                           AND emco_cod_empr_come = pscodempresa
                           AND ccap_cod_curs_capa =
                                             regdetalleauxregion.cod_curs_capa
                           AND peri_cod_peri = lscodperiodo
                           AND regi_cod_regi = regdetalleauxregion.cod_regi;
               ELSE
                        INSERT INTO edu_contr_gener_plani
                                    (pais_cod_pais, emco_cod_empr_come,
                                     ccap_cod_curs_capa,
                                     peri_cod_peri,
                                     regi_cod_regi,
                                     cod_plan_prog_inic,
                                     cod_plan_prog_fina,
                                     usu_digi, fec_digi,
                                     est_regi
                                    )
                             VALUES (pscodigopais, pscodempresa,
                                     regdetalleauxregion.cod_curs_capa,
                                     lscodperiodo,
                                     regdetalleauxregion.cod_regi,
                                     tablacontrolplanilla.cod_plan_prog_inic,
                                     (  TO_NUMBER
                                           (tablacontrolplanilla.cod_plan_prog_fina
                            )
                                      - 1
                                     ),
                                     psusuario, SYSDATE,
                                     estado_registro_activo
                                    );
                END IF;

               /*Ponemos los Valores del Nuevo Registro  */
      --Siguiente Registro de del mismo periodo con distinta region debe empezar donde se quedo
      --la ultima planilla para ese periodo
                     regdetalleauxregion := regdetalle;
                     tablacontrolplanilla.cod_plan_prog_inic :=
                                       tablacontrolplanilla.cod_plan_prog_fina;
             END IF;

             /* Actualizando Tabla de Historico de Aptas */
             SELECT --(NVL(A.NUM_INVI,0) + 1)
                         NVL (a.num_invi, 0)
                    INTO lnnuminvi
                    FROM edu_histo_aptas a
                   WHERE a.pais_cod_pais = pscodigopais
                     AND a.emco_cod_empr_come = pscodempresa
                     AND a.ccap_cod_curs_capa = regdetalle.cod_curs_capa
                     AND a.clie_cod_clie = regdetalle.cod_clie;

                  UPDATE edu_histo_aptas a
                     SET a.cod_plan_prog = tablaparametro.cod_plan_prog_actu,
                         a.ult_camp_prog_dict = lscodperiodo,
                         a.est_capa = indicador_programada,             --'PR'
                -- A.NUM_INVI = lnNumInvi,
                         a.usu_modi = psusuario,         --Campos de AUDITORIA
                         a.fec_modi = SYSDATE
                   WHERE a.pais_cod_pais = pscodigopais
                     AND a.emco_cod_empr_come = pscodempresa
                     AND a.ccap_cod_curs_capa = regdetalle.cod_curs_capa
                     AND a.clie_cod_clie = regdetalle.cod_clie;

            /* Actualizando Tabla Planilla Programacion de curso */
    /*
      Se ingresa toda la data que se generara por planilla
      para cada cliente

    */
    --DBMS_OUTPUT.PUT_LINE('CLIENTE   '|| regDetalle.COD_CLIE );
                  INSERT INTO edu_plani_progr_curso
                              (pais_cod_pais, emco_cod_empr_come,
                               ccap_cod_curs_capa, clie_cod_clie,
                               cod_plan_prog,
                               cam_proc, tip_cali_apta,
                               ind_curs_cost,
                               ind_curs_fact,
                               cam_fact_curs, num_invi,
                               usu_digi, fec_digi, est_regi
                  )
                       VALUES (regdetalle.cod_pais, regdetalle.cod_empr_come,
                               regdetalle.cod_curs_capa, regdetalle.cod_clie,
                               tablaparametro.cod_plan_prog_actu,
                               lscodperiodo, regdetalle.tip_cali_apta,
                               regdetalle.ind_curs_cost,
                               regdetalle.ind_curs_fact,
                               regdetalle.cam_fact_curs, lnnuminvi,
                               psusuario, SYSDATE, estado_registro_activo
                  );
         END LOOP;

       /*Antes de Cerrar actualizamos el ultimo quiebre de la region con la ultima planilla*/
        --Actualiza la ultima palnilla en EDU_CONTR_GENER_PLANI
         SELECT COUNT(1)
                 INTO lncontador
                 FROM edu_contr_gener_plani
                WHERE pais_cod_pais = pscodigopais
                  AND emco_cod_empr_come = pscodempresa
                  AND ccap_cod_curs_capa = regdetalle.cod_curs_capa
                  AND peri_cod_peri = lscodperiodo
                  AND regi_cod_regi = regdetalle.cod_regi;

               IF (lncontador > 0)
               THEN
                  UPDATE edu_contr_gener_plani
                     SET cod_plan_prog_fina =
                                       tablacontrolplanilla.cod_plan_prog_fina,
                         usu_modi = psusuario,
                         fec_modi = SYSDATE
                   WHERE pais_cod_pais = pscodigopais
                     AND emco_cod_empr_come = pscodempresa
                     AND ccap_cod_curs_capa = regdetalle.cod_curs_capa
                     AND peri_cod_peri = lscodperiodo
                     AND regi_cod_regi = regdetalle.cod_regi;
           ELSE
                  INSERT INTO edu_contr_gener_plani
                              (pais_cod_pais, emco_cod_empr_come,
                               ccap_cod_curs_capa, peri_cod_peri,
                               regi_cod_regi,
                               cod_plan_prog_inic,
                               cod_plan_prog_fina,
                               usu_digi, fec_digi, est_regi
                        )
                       VALUES (pscodigopais, pscodempresa,
                               regdetalle.cod_curs_capa, lscodperiodo,
                               regdetalle.cod_regi,
                               tablacontrolplanilla.cod_plan_prog_inic,
                               tablacontrolplanilla.cod_plan_prog_fina,
                               psusuario, SYSDATE, estado_registro_activo
                              );
         END IF;
      END IF;

            EXIT WHEN cursorregistro%NOTFOUND;
    END LOOP;

         CLOSE cursorregistro;
  END LOOP;

  RETURN;
EXCEPTION
      WHEN OTHERS
      THEN
      ln_sqlcode := SQLCODE;
         ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
         raise_application_error (-20123,
                                     'ERROR EDU_PR_GENER_PLANI_PROGR: '
                                  || ls_sqlerrm
                                 );
END;

/***************************************************************************
Descripcion       : Funcion que devuelve la cantidad de consultoras para una
                    determinada planilla de una región.
Fecha Creacion    : 31/10/2007
Autor             : Robinson Vela Bardales
Parametros:
          psCodigoPais  : Codigo de Pais
          psCodEmpresa  : Codigo de Empresa
          psCodRegion   : Codigo de Region
          psCodZona     : Codigo de Zona (Opcional)
          psCampanha    : Campaña de Proceso
***************************************************************************/
FUNCTION EDU_FN_CANTI_PLANI_REGION(
  psCodigoPais      VARCHAR2,
  psCodEmpresa      VARCHAR2,
  psCodRegion       VARCHAR2,
  psCodZona         VARCHAR2,
  psCampanha        VARCHAR2
)
RETURN NUMBER
IS
  lsRetorno    NUMBER;
BEGIN
  SELECT COUNt(1)
  INTO lsRetorno
  FROM EDU_PLANI_PROGR_CURSO A,
       EDU_EMPRE_COMER       B,
       EDU_PARAM_CURSO_CAPAC C,
       EDU_MAEST_CLIEN       D,
       EDU_HISTO_PLANI_INSTR E,
       EDU_PARAM_PROGR_CAPAC PPC
  WHERE B.PAIS_COD_PAIS      = A.PAIS_COD_PAIS
  AND  B.COD_EMPR_COME       = A.EMCO_COD_EMPR_COME
  AND  PPC.PAIS_COD_PAIS      = A.PAIS_COD_PAIS
  AND  PPC.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
  AND  PPC.COD_PROG_CAPA = '01'
  AND  A.PAIS_COD_PAIS      = C.PAIS_COD_PAIS
  AND  A.EMCO_COD_EMPR_COME = C.EMCO_COD_EMPR_COME
  AND  A.CCAP_COD_CURS_CAPA = C.COD_CURS_CAPA
  AND  D.PAIS_COD_PAIS      = A.PAIS_COD_PAIS
  AND  D.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
  AND  D.COD_CLIE           = A.CLIE_COD_CLIE
  AND   E.PAIS_COD_PAIS      = A.PAIS_COD_PAIS
  AND   E.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
  AND   E.CCAP_COD_CURS_CAPA = A.CCAP_COD_CURS_CAPA
  AND   E.COD_PLAN_PROG      = A.COD_PLAN_PROG
  AND   A.PAIS_COD_PAIS = psCodigoPais
  AND  A.EMCO_COD_EMPR_COME = psCodEmpresa
  AND  E.REGI_COD_REGI      = psCodRegion
  AND  (psCodZona IS NULL OR E.ZONA_COD_ZONA = psCodZona)
  AND  A.CAM_PROC = psCampanha;
  RETURN lsRetorno;
EXCEPTION
WHEN NO_DATA_FOUND THEN
     RETURN '';
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_FN_CANT_REG_PLANILLA: '||ls_sqlerrm);
END EDU_FN_CANTI_PLANI_REGION;

/***************************************************************************
Descripcion       : Proceso que cambia el código del cliente, se requiere el
                    código nuevo y el antiguo. se transfieren todos los movimientos
                    encontrados del antiguo al cliente nuevo.
                  :Las Tablas Relacionadas son:
EDU_CONSU_EXONE_CAPAC
EDU_HISTO_APTAS
EDU_HISTO_CAPAC_CABEC
EDU_HISTO_CAPAC_DETAL
EDU_HISTO_CLASI_BENEF_DETAL
EDU_HISTO_CURSO_DICTA_DETAL
EDU_PARAM_CURSO_DEMAN_CLIEN
EDU_PLANI_PROGR_CURSO
EDU_HISTO_PEDID_CONSU

EDU_HISTO_BLOQU_CONSU --

Fecha Creacion    : 31/10/2007
Autor             : Robinson Vela Bardales
Modificado por    :Sergio Buchelli
Parametros Entrada:
             psCodigoPais  : Codigo de Pais
             psCodEmpresa  : Codigo de Empresa
             psUsuario     : Usuario de Proceso
             psClienteOld  : Código del Cliente Antiguo
             psClienteNew  : Código Nuevo del Cliente
Parametros Salida   : psMensajeError: Mensaje de Error
***************************************************************************/
   PROCEDURE edu_pr_recod_consu (
      pscodigopais            VARCHAR2,
      pscodigoempresa         VARCHAR2,
      psusuario               VARCHAR2,
      psclienteold            VARCHAR2,
      psclientenew            VARCHAR2,
      psmensajeerror    OUT   VARCHAR2
    )
    IS
   ls_sqlerrm         VARCHAR2(150);
      lncorrelativo   NUMBER;
      lncontador      NUMBER;
   BEGIN
      psmensajeerror := '';

      /** Validamos que no exista el nuevo Codigo a recodificar*/
    SELECT COUNT(1)
        INTO lncontador
        FROM edu_maest_clien
       WHERE pais_cod_pais = pscodigopais
         AND emco_cod_empr_come = pscodigoempresa
         AND cod_clie = psclientenew;

      IF (lncontador > 0)
      THEN
         ls_sqlerrm :=
                     'procesoEDUInicioProcesosDiarios.msg.validarCódigoNuevo';
         psmensajeerror := ls_sqlerrm;
       RETURN;
    END IF;

      /*Insertamos el Nuevo Cliente */
      INSERT INTO edu_maest_clien
                  (pais_cod_pais, emco_cod_empr_come, cod_clie, cod_regi,
                   cod_zona, cod_secc, cod_terr, cam_ingr, ape_pate, ape_mate,
                   ape_casa, pri_nomb, seg_nomb, num_docu, fec_naci,
                   num_tele_part, ind_moro, sal_clie, usu_digi, fec_digi,
                   usu_modi, fec_modi, est_regi, num_celu, ape_nomb_comp)
         SELECT pais_cod_pais, emco_cod_empr_come, psclientenew, cod_regi,
                cod_zona, cod_secc, cod_terr, cam_ingr, ape_pate, ape_mate,
                ape_casa, pri_nomb, seg_nomb, num_docu, fec_naci,
                num_tele_part, ind_moro, sal_clie, usu_digi, fec_digi,
                usu_modi, fec_modi, est_regi, num_celu, ape_nomb_comp
           FROM edu_maest_clien
          WHERE pais_cod_pais = pscodigopais
            AND emco_cod_empr_come = pscodigoempresa
            AND cod_clie = psclienteold;



     /*Insertamos en la Tabla de
     Historico de Aptas */
      INSERT INTO edu_histo_aptas
                  (pais_cod_pais, emco_cod_empr_come, ccap_cod_curs_capa,
                   clie_cod_clie, cod_curs_dict, cod_plan_prog,
                   cam_prim_cali_apta, cam_ulti_cali_apta, tip_cali_apta,
                   num_invi, ind_inic_cali_apta, ind_fina_cali_apta,
                   ind_curs_cost, ind_curs_fact, cam_fact_curs, cam_acep,
                   ult_camp_prog_dict, est_capa, ind_envi, ind_envi_prog,
                   num_lote_diar, num_lote_regi, usu_digi, fec_digi, usu_modi,
                   fec_modi, est_regi, cam_capa)
         SELECT pais_cod_pais, emco_cod_empr_come, ccap_cod_curs_capa,
                psclientenew, cod_curs_dict, cod_plan_prog,
                cam_prim_cali_apta, cam_ulti_cali_apta, tip_cali_apta,
                num_invi, ind_inic_cali_apta, ind_fina_cali_apta,
                ind_curs_cost, ind_curs_fact, cam_fact_curs, cam_acep,
                ult_camp_prog_dict, est_capa, ind_envi, ind_envi_prog,
                num_lote_diar, num_lote_regi, usu_digi, fec_digi, usu_modi,
                fec_modi, est_regi, cam_capa
           FROM edu_histo_aptas
          WHERE pais_cod_pais = pscodigopais
            AND emco_cod_empr_come = pscodigoempresa
            AND clie_cod_clie = psclienteold;


			  /*Insetamos en la Tabla de Exoneraciones
   de Capacitación */
      INSERT INTO edu_consu_exone_capac
                  (pais_cod_pais, emco_cod_empr_come, ccap_cod_curs_capa,
                   clie_cod_clie, cam_exon, est_exon, usu_digi, fec_digi,
                   usu_modi, fec_modi, est_regi)
         SELECT pais_cod_pais, emco_cod_empr_come, ccap_cod_curs_capa,
                psclientenew, cam_exon, est_exon, usu_digi, fec_digi,
                usu_modi, fec_modi, est_regi
           FROM edu_consu_exone_capac
          WHERE pais_cod_pais = pscodigopais
            AND emco_cod_empr_come = pscodigoempresa
            AND clie_cod_clie = psclienteold;

 /*Insertamos en la Tabla Calificacion
      de Aptas */
      INSERT INTO EDU_HISTO_CALIF_APTAS
                  (pais_cod_pais, emco_cod_empr_come, ccap_cod_curs_capa,cam_proc,
                   clie_cod_clie, cod_curs_dict, cod_plan_prog,
                   cam_prim_cali_apta, cam_ulti_cali_apta, tip_cali_apta,
                   num_invi, ind_inic_cali_apta, ind_fina_cali_apta,
                   ind_curs_cost, ind_curs_fact, cam_fact_curs, cam_acep,
                   ult_camp_prog_dict,CAM_CAPA, est_capa, ind_envi,
                   num_lote_diar,usu_digi, fec_digi, usu_modi,
                   fec_modi, est_regi)
         SELECT pais_cod_pais, emco_cod_empr_come, ccap_cod_curs_capa,cam_ulti_cali_apta,
                psclientenew, cod_curs_dict, cod_plan_prog,
                cam_prim_cali_apta, cam_ulti_cali_apta, tip_cali_apta,
                num_invi, ind_inic_cali_apta, ind_fina_cali_apta,
                ind_curs_cost, ind_curs_fact, cam_fact_curs, cam_acep,
                ult_camp_prog_dict,cam_capa, est_capa, ind_envi,
                num_lote_diar,usu_digi, fec_digi, usu_modi,
                fec_modi, est_regi
           FROM edu_histo_aptas
          WHERE pais_cod_pais = pscodigopais
            AND emco_cod_empr_come = pscodigoempresa
            AND clie_cod_clie = psclienteold;


     /*Insertamos en la Tabla de Historico de
    Capacitadas Cabecera */
      INSERT INTO edu_histo_capac_cabec
                  (pais_cod_pais, emco_cod_empr_come, clie_cod_clie,
                   niv_capa_alca, cam_ingr, ult_camp_capa, usu_digi, fec_digi,
                   usu_modi, fec_modi, est_regi)
         SELECT pais_cod_pais, emco_cod_empr_come, psclientenew,
                niv_capa_alca, cam_ingr, ult_camp_capa, usu_digi, fec_digi,
                usu_modi, fec_modi, est_regi
           FROM edu_histo_capac_cabec
          WHERE pais_cod_pais = pscodigopais
            AND emco_cod_empr_come = pscodigoempresa
            AND clie_cod_clie = psclienteold;

     /*Insertamos en la Tabla de Historico de
    Capacitadas Detalle
   */
      INSERT INTO edu_histo_capac_detal
                  (pais_cod_pais, emco_cod_empr_come, clie_cod_clie,
                   ccap_cod_curs_capa, tasi_cod_tipo_asis_curs,
                   astt_cod_tipo_astt_curs, num_invi, ind_pago_curs,
                   cod_curs_dict, cod_plan_prog, inst_cod_inst,
                   cam_prim_cali_apta, cam_ulti_cali_apta, cam_capa,
                   cam_regi_asis, ind_eval_curs, cal_eval_curs, ind_eval_inst,
                   cal_eval_inst, usu_digi, fec_digi, usu_modi, fec_modi,
                   est_regi)
         SELECT pais_cod_pais, emco_cod_empr_come, psclientenew,
                ccap_cod_curs_capa, tasi_cod_tipo_asis_curs,
                astt_cod_tipo_astt_curs, num_invi, ind_pago_curs,
                cod_curs_dict, cod_plan_prog, inst_cod_inst,
                cam_prim_cali_apta, cam_ulti_cali_apta, cam_capa,
                cam_regi_asis, ind_eval_curs, cal_eval_curs, ind_eval_inst,
                cal_eval_inst, usu_digi, fec_digi, usu_modi, fec_modi,
                est_regi
           FROM edu_histo_capac_detal
          WHERE pais_cod_pais = pscodigopais
            AND emco_cod_empr_come = pscodigoempresa
            AND clie_cod_clie = psclienteold;

     /*Insertamos en la Tabla de Clasificación
    Beneficio Detalle
   */
      INSERT INTO edu_histo_clasi_benef_detal
                  (pais_cod_pais, emco_cod_empr_come, clas_cod_clas,
                   hbec_cam_proc, clie_cod_clie, niv_capa_alca, cam_dict_curs,
                   est_resp_nive, ind_envi, usu_digi, fec_digi, fec_modi,
                   usu_modi, est_regi)
         SELECT pais_cod_pais, emco_cod_empr_come, clas_cod_clas,
                hbec_cam_proc, psclientenew, niv_capa_alca, cam_dict_curs,
                est_resp_nive, ind_envi, usu_digi, fec_digi, fec_modi,
                usu_modi, est_regi
           FROM edu_histo_clasi_benef_detal
          WHERE pais_cod_pais = pscodigopais
            AND emco_cod_empr_come = pscodigoempresa
            AND clie_cod_clie = psclienteold;

     /*Insertamos en la Tabla de
    Dictados Detalle
   */
      INSERT INTO edu_histo_curso_dicta_detal
                  (pais_cod_pais, emco_cod_empr_come, ccap_cod_curs_capa,
                   cdic_cod_curs_dict, cod_plan_prog, clie_cod_clie, ind_asis,
                   cal_cons, cal_inst, usu_digi, fec_digi, usu_modi, fec_modi,
                   est_regi)
         SELECT pais_cod_pais, emco_cod_empr_come, ccap_cod_curs_capa,
                cdic_cod_curs_dict, cod_plan_prog, psclientenew, ind_asis,
                cal_cons, cal_inst, usu_digi, fec_digi, usu_modi, fec_modi,
                est_regi
           FROM edu_histo_curso_dicta_detal
          WHERE pais_cod_pais = pscodigopais
            AND emco_cod_empr_come = pscodigoempresa
            AND clie_cod_clie = psclienteold;

    /*Insertamos en la Tabla de
   Parametros de Calificación a Demanda
  */
      INSERT INTO edu_param_curso_deman_clien
                  (pais_cod_pais, emco_cod_empr_come, ccap_cod_curs_capa,
                   cod_camp_proc, clie_cod_clie, usu_digi, fec_digi, usu_modi,
                   fec_modi, est_regi)
         SELECT pais_cod_pais, emco_cod_empr_come, ccap_cod_curs_capa,
                cod_camp_proc, psclientenew, usu_digi, fec_digi, usu_modi,
                fec_modi, est_regi
           FROM edu_param_curso_deman_clien
          WHERE pais_cod_pais = pscodigopais
            AND emco_cod_empr_come = pscodigoempresa
            AND clie_cod_clie = psclienteold;

    /*Insertamos en la
   Tabla de Planilla de Programación
   del Curso
  */
      INSERT INTO edu_plani_progr_curso
                  (pais_cod_pais, emco_cod_empr_come, ccap_cod_curs_capa,
                   clie_cod_clie, cam_proc, cod_plan_prog, tip_cali_apta,
                   ind_curs_cost, ind_curs_fact, cam_fact_curs, usu_digi,
                   fec_digi, usu_modi, fec_modi, est_regi, num_invi)
         SELECT pais_cod_pais, emco_cod_empr_come, ccap_cod_curs_capa,
                psclientenew, cam_proc, cod_plan_prog, tip_cali_apta,
                ind_curs_cost, ind_curs_fact, cam_fact_curs, usu_digi,
                fec_digi, usu_modi, fec_modi, est_regi, num_invi
           FROM edu_plani_progr_curso
          WHERE pais_cod_pais = pscodigopais
            AND emco_cod_empr_come = pscodigoempresa
            AND clie_cod_clie = psclienteold;

     /*Insertamos en la Tabla de Histórico de
    Pedidos de Consumo
  */
      INSERT INTO edu_histo_pedid_consu
                  (pais_cod_pais, emco_cod_empr_come, cod_clie, cam_inic_pedi,
                   cam_ulti_pedi, cam_proc, cod_ulti_nive, num_pedi_fact,
                   ind_pedi, ind_fact, ind_nuev, ind_cons, ind_prim_pedi,
                   usu_digi, fec_digi, usu_modi, fec_modi, est_regi)
         SELECT pais_cod_pais, emco_cod_empr_come, psclientenew,
                cam_inic_pedi, cam_ulti_pedi, cam_proc, cod_ulti_nive,
                num_pedi_fact, ind_pedi, ind_fact, ind_nuev, ind_cons,
                ind_prim_pedi, usu_digi, fec_digi, usu_modi, fec_modi,
                est_regi
           FROM edu_histo_pedid_consu
          WHERE pais_cod_pais = pscodigopais
            AND emco_cod_empr_come = pscodigoempresa
            AND cod_clie = psclienteold;

 /*Insertamos en la Tabla de Bloqueo

  */
      INSERT INTO edu_histo_bloqu_consu
                  (pais_cod_pais, emco_cod_empr_come, clie_cod_clie, cam_proc,
                   cam_prim_cali_apta, num_invi, regi_cod_regi, usu_digi,
                   fec_digi, usu_modi, fec_modi, est_regi)
         SELECT pais_cod_pais, emco_cod_empr_come, psclientenew, cam_proc,
                cam_prim_cali_apta, num_invi, regi_cod_regi, usu_digi,
                fec_digi, usu_modi, fec_modi, est_regi
           FROM edu_histo_bloqu_consu
          WHERE pais_cod_pais = pscodigopais
            AND emco_cod_empr_come = pscodigoempresa
            AND clie_cod_clie = psclienteold;

 /***************** Se elimina el registro del Antiguo Cliente ****************************************/

    /*Eliminamos el Cliente Antiguo*/
      DELETE FROM edu_histo_pedid_consu
            WHERE pais_cod_pais = pscodigopais
              AND emco_cod_empr_come = pscodigoempresa
              AND cod_clie = psclienteold;

    /*Quitamos las planillas del cliente */
      UPDATE edu_histo_aptas
         SET cod_plan_prog = NULL
       WHERE pais_cod_pais = pscodigopais
         AND emco_cod_empr_come = pscodigoempresa
         AND clie_cod_clie = psclienteold;

     /* Eliminamos las planillas de programcion por curso*/
      DELETE FROM edu_plani_progr_curso
            WHERE pais_cod_pais = pscodigopais
              AND emco_cod_empr_come = pscodigoempresa
              AND clie_cod_clie = psclienteold;

  /* Eliminamos de la demanda del curso por cliente*/
      DELETE FROM edu_param_curso_deman_clien
            WHERE pais_cod_pais = pscodigopais
              AND emco_cod_empr_come = pscodigoempresa
              AND clie_cod_clie = psclienteold;

  /* Eliminamos del historico de detalle del curso*/
      DELETE FROM edu_histo_curso_dicta_detal
            WHERE pais_cod_pais = pscodigopais
              AND emco_cod_empr_come = pscodigoempresa
              AND clie_cod_clie = psclienteold;

  /* Eliminamos del historico  de clasificacion*/
      DELETE FROM edu_histo_clasi_benef_detal
            WHERE pais_cod_pais = pscodigopais
              AND emco_cod_empr_come = pscodigoempresa
              AND clie_cod_clie = psclienteold;

   /* Eliminamos del historico  de capacitacion*/
      DELETE FROM edu_histo_capac_detal
            WHERE pais_cod_pais = pscodigopais
              AND emco_cod_empr_come = pscodigoempresa
              AND clie_cod_clie = psclienteold;

  /* Eliminamos del historico  de capacitacion*/
      DELETE FROM edu_histo_capac_cabec
            WHERE pais_cod_pais = pscodigopais
              AND emco_cod_empr_come = pscodigoempresa
              AND clie_cod_clie = psclienteold;

  /* ELIMINAMOS DEL HISTORICO DE CALIFICACIONES*/

	  DELETE FROM EDU_HISTO_CALIF_APTAS
            WHERE pais_cod_pais = pscodigopais
              AND emco_cod_empr_come = pscodigoempresa
              AND clie_cod_clie = psclienteold;

 /* Eliminamos de la tabla exoneracion */
      DELETE FROM edu_consu_exone_capac
            WHERE pais_cod_pais = pscodigopais
              AND emco_cod_empr_come = pscodigoempresa
              AND clie_cod_clie = psclienteold;

  /* Eliminamos del historico  de aptas*/
      DELETE FROM edu_histo_aptas
            WHERE pais_cod_pais = pscodigopais
              AND emco_cod_empr_come = pscodigoempresa
              AND clie_cod_clie = psclienteold;


  /* Eliminamos de la tabla de bloqueo*/
      DELETE FROM edu_histo_bloqu_consu
            WHERE pais_cod_pais = pscodigopais
              AND emco_cod_empr_come = pscodigoempresa
              AND clie_cod_clie = psclienteold;

  /* Eliminamos de la tabla de maestro de cliente*/
      DELETE FROM edu_maest_clien
            WHERE pais_cod_pais = pscodigopais
              AND emco_cod_empr_come = pscodigoempresa
              AND cod_clie = psclienteold;

 /* Insertamos en la tabla historico de recodificacion de
    Consultoras
  */
      SELECT edu_seq_histo_recod_consu.NEXTVAL
        INTO lncorrelativo
        FROM DUAL;

      INSERT INTO edu_histo_recod_consu
                  (pais_cod_pais, emco_cod_empr_come, cor_reco_cons,
                   cod_clie_actu, cod_clie_nuev, usu_digi, fec_digi,
                   usu_modi, fec_modi, est_regi
        )
           VALUES (pscodigopais, pscodigoempresa, lncorrelativo,
                   psclienteold, psclientenew, psusuario, SYSDATE,
                   NULL, NULL, '1'
                  );

  RETURN;
EXCEPTION
      WHEN OTHERS
      THEN
         ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
         raise_application_error (-20123,
                                     'ERROR EDU_PR_GENER_PLANI_PROGR: '
                                  || ls_sqlerrm
                                 );
   END edu_pr_recod_consu;

/***************************************************************************
Descripcion       : Proceso que se encarga de efectuar la confirmacion de asitencia
       de una consultora a un curso de capacitacion
Fecha Creacion    : 27/11/2007
Autor             : Sergio Buchelli Silva
Parametros:
          psCodigoPais    : Codigo de Pais
          psCodigoEmpresa    : Codigo de Empresa
    psCodigoCurso      : Codigo de curso
    psCodigoPlanilla   : Codigo de Planilla
    psCodigoConsultora : Codigo de Consultora
          psUsuario       : Usuario de Proceso
Parametros Salida   : psMensajeError: Mensaje de Error
***************************************************************************/
   PROCEDURE edu_pr_confi_asist (
      pscodigopais               VARCHAR2,
      pscodigoempresa            VARCHAR2,
      pscodigocurso              VARCHAR2,
      pscodigoplanilla           VARCHAR2,
      pscodigoconsultora         VARCHAR2,
      psusuario                  VARCHAR2,
      psmensajeerror       OUT   VARCHAR2
   )
   IS
   ls_sqlerrm         VARCHAR2(150);
      lnincremento     NUMBER;
      lnnivel          NUMBER;
      lscampcapac      edu_histo_curso_dicta_cabec.cam_inic_curs%TYPE;
      lscursodictado   edu_histo_curso_dicta_cabec.cod_curs_dict%TYPE;
      lscodigoinst     edu_histo_curso_dicta_cabec.inst_cod_inst%TYPE;

	  lsindbloq		   edu_param_progr_capac.IND_PROC_BLOQ%TYPE;
	  lnContBloq	   NUMBER;
	  ls_ind_curs_cost edu_histo_aptas.IND_CURS_COST%TYPE;
	  lsCampActual     edu_histo_aptas.CAM_ULTI_CALI_APTA%TYPE;
BEGIN
      psmensajeerror := '';

	  /* Obtenemos la campaña de procesos*/
		lsCampActual:=EDU_PKG_CALIF.EDU_FN_DEVUE_CAMPA_PROCE_ACTUA(pscodigopais,pscodigoempresa);

	  --se valida si esta bloqueada

	   SELECT a.IND_PROC_BLOQ
        INTO lsindbloq
        FROM edu_param_progr_capac a
       WHERE a.pais_cod_pais = pscodigopais
         AND a.emco_cod_empr_come = pscodigoempresa
         AND a.cod_prog_capa = '01';

		 IF( lsindbloq = '1') THEN
		   --SE BUSCA EN LA TABLA DE BOLOQUEO SI EXISTE
		    SELECT COUNT(1) into lnContBloq
			FROM EDU_HISTO_BLOQU_CONSU a
			 WHERE a.pais_cod_pais = pscodigopais
			 AND a.emco_cod_empr_come = pscodigoempresa
			 AND A.CLIE_COD_CLIE = pscodigoconsultora
			 AND A.EST_REGI='1';

			IF( lnContBloq > 0) THEN
				ls_sqlerrm :=
                        'No se puede Regularizar : Consultora Bloqueada' ;
				psmensajeerror := ls_sqlerrm;
				RETURN;
			END IF;

		 END IF;


	  -- Se ejecuta proceso previo a la regularizacion
	  EDU_PKG_PROCE_GENER.EDU_PR_PREVI_REGUL_ASIST(pscodigopais,pscodigoempresa,pscodigocurso,pscodigoconsultora,'1',psusuario);
  --Se obtiene el codigo curso dictado, instructura , campanha de capacitacion
 BEGIN
         SELECT d.cam_inic_curs, cod_curs_dict, inst_cod_inst
           INTO lscampcapac, lscursodictado, lscodigoinst
           FROM edu_histo_curso_dicta_cabec d
          WHERE pais_cod_pais = pscodigopais
            AND emco_cod_empr_come = pscodigoempresa
            AND ccap_cod_curs_capa = pscodigocurso
            AND cod_curs_dict =
                   (SELECT p.cdic_cod_curs_dict
                      FROM edu_histo_plani_instr p
                     WHERE p.pais_cod_pais = pscodigopais
                       AND p.emco_cod_empr_come = pscodigoempresa
                       AND p.cod_plan_prog = pscodigoplanilla
                       AND p.ccap_cod_curs_capa = pscodigocurso);
   EXCEPTION
         WHEN OTHERS
         THEN
            ls_sqlerrm :=
                        'procesoEDURegularizacionAsistencia.msg.validarCurso';
            psmensajeerror := ls_sqlerrm;
    RETURN;
  END;

   --se actualiza el Historico Curso Dicatado Detalle a Asistido
      UPDATE edu_histo_curso_dicta_detal
         SET ind_asis = 'S',         --lo ponemos que asistio ala capacitacion
             usu_modi = psusuario,
             fec_modi = SYSDATE
       WHERE pais_cod_pais = pscodigopais
         AND emco_cod_empr_come = pscodigoempresa
         AND ccap_cod_curs_capa = pscodigocurso
         AND cdic_cod_curs_dict = lscursodictado
         AND cod_plan_prog = pscodigoplanilla
         AND clie_cod_clie = pscodigoconsultora;

 --Como se trata de una regularizacion de asitencia
 --Se crea un nuevo registro o actualiza en Historica de cabecera como en su detalle

 --CABECERA


         --INSERTAMOS EL ULTIMO REGULARIZADO
		BEGIN
         INSERT INTO edu_histo_capac_cabec
                     (pais_cod_pais, emco_cod_empr_come, clie_cod_clie,
                      niv_capa_alca, cam_ingr, ult_camp_capa, usu_digi,
                      fec_digi, usu_modi, fec_modi, est_regi)
            SELECT a.pais_cod_pais, a.emco_cod_empr_come, a.clie_cod_clie,
                   a.ccap_cod_curs_capa, a.cam_prim_cali_apta, lscampcapac,
                   psusuario, SYSDATE, NULL, NULL, '1'
              FROM edu_histo_aptas a
             WHERE a.pais_cod_pais = pscodigopais
               AND a.emco_cod_empr_come = pscodigoempresa
               AND a.ccap_cod_curs_capa = pscodigocurso
               AND a.cod_plan_prog = pscodigoplanilla
               AND a.clie_cod_clie = pscodigoconsultora;
		EXCEPTION
		 WHEN OTHERS THEN
		     UPDATE edu_histo_capac_cabec
            SET niv_capa_alca = pscodigocurso,                         --nuevo nivel
				ult_camp_capa = lscampcapac,
                usu_modi = psusuario,
                fec_modi = SYSDATE
		    WHERE pais_cod_pais = pscodigopais
            AND emco_cod_empr_come = pscodigoempresa
            AND clie_cod_clie = pscodigoconsultora;
		END ;


    --DETALLE
      INSERT INTO edu_histo_capac_detal
                  (pais_cod_pais, emco_cod_empr_come, clie_cod_clie,
                   ccap_cod_curs_capa, tasi_cod_tipo_asis_curs,
                   astt_cod_tipo_astt_curs, num_invi, ind_pago_curs,
                   cod_curs_dict, cod_plan_prog, inst_cod_inst,
                   cam_prim_cali_apta, cam_ulti_cali_apta, cam_capa,
                   cam_regi_asis, ind_eval_curs, cal_eval_curs, ind_eval_inst,
                   cal_eval_inst, usu_digi, fec_digi, usu_modi, fec_modi,
                   est_regi,cam_regu)
         SELECT a.pais_cod_pais, a.emco_cod_empr_come, a.clie_cod_clie,
                a.ccap_cod_curs_capa, 'P', 'R', a.num_invi, a.ind_curs_cost,
                lscursodictado, a.cod_plan_prog, lscodigoinst,
                a.cam_prim_cali_apta, a.cam_ulti_cali_apta, lscampcapac,
                lscampcapac, c.ind_cali_ctda, 0, c.ind_cali_cdra, NULL,
                psusuario, SYSDATE, NULL, NULL, '1',lsCampActual
           FROM edu_histo_aptas a, edu_param_curso_capac c
          WHERE a.pais_cod_pais = c.pais_cod_pais
            AND a.emco_cod_empr_come = c.emco_cod_empr_come
            AND a.ccap_cod_curs_capa = c.cod_curs_capa
            AND a.pais_cod_pais = pscodigopais
            AND a.emco_cod_empr_come = pscodigoempresa
            AND a.ccap_cod_curs_capa = pscodigocurso
            AND a.cod_plan_prog = pscodigoplanilla
            AND a.clie_cod_clie = pscodigoconsultora;

 -- ACTUALIZAMOS EL HISTORICO DE APTAS
      UPDATE edu_histo_aptas
         SET cod_curs_dict = lscursodictado,
             est_capa = 'CP',
             cam_capa = lscampcapac,
			 cam_regu = lsCampActual,
             usu_modi = psusuario,
             fec_modi = SYSDATE
       WHERE pais_cod_pais = pscodigopais
         AND emco_cod_empr_come = pscodigoempresa
         AND ccap_cod_curs_capa = pscodigocurso
         AND clie_cod_clie = pscodigoconsultora
         AND cod_plan_prog = pscodigoplanilla;

		 --ACTUALIZAMOS PROGRAMACION DICTADO solo si curso con costo

		 SELECT IND_CURS_COST into ls_ind_curs_cost
		 FROM EDU_HISTO_APTAS
         WHERE pais_cod_pais = pscodigopais
          AND emco_cod_empr_come = pscodigoempresa
          AND ccap_cod_curs_capa = pscodigocurso
          AND clie_cod_clie = pscodigoconsultora;

		 IF(ls_ind_curs_cost='S') THEN

		      UPDATE edu_histo_aptas
		         SET  IND_CURS_FACT = 'F',
		             usu_modi = psusuario,
		             fec_modi = SYSDATE
		       WHERE pais_cod_pais = pscodigopais
		         AND emco_cod_empr_come = pscodigoempresa
		         AND ccap_cod_curs_capa = pscodigocurso
		         AND clie_cod_clie = pscodigoconsultora
		         AND cod_plan_prog = pscodigoplanilla;

			 UPDATE EDU_PLANI_PROGR_CURSO x
			 SET x.CAM_FACT_CURS = x.CAM_PROC,
			     x.IND_CURS_FACT='F',
		         x.usu_modi = psusuario,
		         x.fec_modi = SYSDATE
 	         WHERE x.pais_cod_pais = pscodigopais
	          AND x.emco_cod_empr_come = pscodigoempresa
	          AND x.ccap_cod_curs_capa = pscodigocurso
	          AND x.clie_cod_clie = pscodigoconsultora
			  AND x.IND_CURS_COST='S'
			  AND x.cod_plan_prog = pscodigoplanilla
			  AND x.cam_proc = edu_pkg_proce_gener.edu_fn_devue_codig_perio (lscampcapac, -1);
		 END IF;


  -- FINALMNETE SE INSERTA EN EL TRANSACCIONAL DE REGULARIZACIONES
  --TIPO OPERACION :CA :CONFIRMACION ASISTENCIA
  -- SE ELMINA EL REGISTRO ANTERIOI
      DELETE FROM edu_trans_regul
            WHERE pais_cod_pais = pscodigopais
              AND emco_cod_empr_come = pscodigoempresa
              AND ccap_cod_curs_capa = pscodigocurso
              AND clie_cod_clie = pscodigoconsultora;

  --INSERTAMOS EL REGISTRO ACTUAL PARA LA CONSULTORA
      INSERT INTO edu_trans_regul
                  (pais_cod_pais, emco_cod_empr_come, ccap_cod_curs_capa,
                   clie_cod_clie, tip_oper, usu_digi, fec_digi, usu_modi,
                   fec_modi, est_regi,CAM_REGU
                  )
           VALUES (pscodigopais, pscodigoempresa, pscodigocurso,
                   pscodigoconsultora, 'CA', psusuario, SYSDATE, NULL,
                   NULL, '1',lsCampActual
                  );

	 	  -- ACTUALIZAMOS LA TABLA DE  BENEFICIOS PARA LA COLSULTORA PARA SU CAMPANHA DE CAPACITACION
	  EDU_PKG_PROCE_GENER.EDU_PR_REGUL_BENEF_CONSU(pscodigopais,pscodigoempresa,lscampcapac,pscodigocurso,pscodigoconsultora,'1','R',psusuario);

  RETURN;
EXCEPTION
      WHEN OTHERS
      THEN
         ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
         raise_application_error (-20123,
                                  'EDU_PR_CONFI_ASIST: ' || ls_sqlerrm
                                 );
   END edu_pr_confi_asist;

/***************************************************************************
Descripcion       : Proceso que se encarga de efectuar la confirmacion de asitencia
       de una consultora a un curso de capacitacion
Fecha Creacion    : 27/11/2007
Autor             : Sergio Buchelli Silva
Parametros:
          psCodigoPais    : Codigo de Pais
          psCodigoEmpresa    : Codigo de Empresa
    psCodigoCurso      : Codigo de curso
    psCodigoPlanilla   : Codigo de Planilla
    psCodigoConsultora : Codigo de Consultora
          psUsuario       : Usuario de Proceso
Parametros Salida   : psMensajeError: Mensaje de Error
***************************************************************************/
   PROCEDURE edu_pr_elimi_asist (
      pscodigopais               VARCHAR2,
      pscodigoempresa            VARCHAR2,
      pscodigocurso              VARCHAR2,
      pscodigoplanilla           VARCHAR2,
      pscodigoconsultora         VARCHAR2,
      psusuario                  VARCHAR2,
      psmensajeerror       OUT   VARCHAR2
   )
   IS
   ls_sqlerrm         VARCHAR2(150);
      lscampcapac      edu_histo_curso_dicta_cabec.cam_inic_curs%TYPE;
      lscursodictado   edu_histo_curso_dicta_cabec.cod_curs_dict%TYPE;
      lscodigoinst     edu_histo_curso_dicta_cabec.inst_cod_inst%TYPE;
      lnincremento     NUMBER;
      lnnivel          NUMBER;
	  lnCont		   NUMBER;
	  lsindbloq		   edu_param_progr_capac.IND_PROC_BLOQ%TYPE;
	  lnContBloq	   NUMBER;
	  lsCampCapa       edu_histo_curso_dicta_cabec.cam_inic_curs%TYPE;
	  lsCampActual     edu_histo_aptas.CAM_ULTI_CALI_APTA%TYPE;
BEGIN
      psmensajeerror := '';

  /* Obtenemos la campaña de procesos*/
		lsCampActual:=EDU_PKG_CALIF.EDU_FN_DEVUE_CAMPA_PROCE_ACTUA(pscodigopais,pscodigoempresa);

	  --se valida si esta bloqueada

	   SELECT a.IND_PROC_BLOQ
        INTO lsindbloq
        FROM edu_param_progr_capac a
       WHERE a.pais_cod_pais = pscodigopais
         AND a.emco_cod_empr_come = pscodigoempresa
         AND a.cod_prog_capa = '01';

		 IF( lsindbloq = '1') THEN
		   --SE BUSCA EN LA TABLA DE BOLOQUEO SI EXISTE
		    SELECT COUNT(1) into lnContBloq
			FROM EDU_HISTO_BLOQU_CONSU a
			 WHERE a.pais_cod_pais = pscodigopais
			 AND a.emco_cod_empr_come = pscodigoempresa
			 AND A.CLIE_COD_CLIE = pscodigoconsultora
			 AND A.EST_REGI='1';

			IF( lnContBloq > 0) THEN
				ls_sqlerrm :=
                        'No se puede Regularizar : Consultora Bloqueada' ;
				psmensajeerror := ls_sqlerrm;
				RETURN;
			END IF;

		 END IF;


	    -- Se ejecuta proceso previo a la regularizacion
	  EDU_PKG_PROCE_GENER.EDU_PR_PREVI_REGUL_ASIST(pscodigopais,pscodigoempresa,pscodigocurso,pscodigoconsultora,'0',psusuario);

  --Se obtiene el codigo curso dictado, instructura , campanha de capacitacion
      SELECT d.cam_inic_curs, cod_curs_dict, inst_cod_inst
        INTO lscampcapac, lscursodictado, lscodigoinst
        FROM edu_histo_curso_dicta_cabec d
       WHERE pais_cod_pais = pscodigopais
         AND emco_cod_empr_come = pscodigoempresa
         AND ccap_cod_curs_capa = pscodigocurso
         AND cod_curs_dict =
                (SELECT p.cdic_cod_curs_dict
                   FROM edu_histo_plani_instr p
                  WHERE p.pais_cod_pais = pscodigopais
                    AND p.emco_cod_empr_come = pscodigoempresa
                    AND p.cod_plan_prog = pscodigoplanilla
                    AND p.ccap_cod_curs_capa = pscodigocurso);

   --se actualiza el Historico Curso Dicatado Detalle a NO Asistio
      UPDATE edu_histo_curso_dicta_detal
         SET ind_asis = 'N',      --lo ponemos que NO asistio ala capacitacion
             usu_modi = psusuario,
             fec_modi = SYSDATE
       WHERE pais_cod_pais = pscodigopais
         AND emco_cod_empr_come = pscodigoempresa
         AND ccap_cod_curs_capa = pscodigocurso
         AND cdic_cod_curs_dict = lscursodictado
         AND cod_plan_prog = pscodigoplanilla
         AND clie_cod_clie = pscodigoconsultora;

 -- Se elimima la asistencia a la consultora del
 --Historico de capacitacion detalle y se actualiza o borra la cabecera segun
 --el nivel alcanzado

  --DETALLE
      DELETE FROM edu_histo_capac_detal
            WHERE pais_cod_pais = pscodigopais
              AND emco_cod_empr_come = pscodigoempresa
              AND clie_cod_clie = pscodigoconsultora
              AND ccap_cod_curs_capa = pscodigocurso;


 --CABECERA
	  SELECT COUNT(1)
	  INTO lnCont
	  FROM edu_histo_capac_detal
            WHERE pais_cod_pais = pscodigopais
              AND emco_cod_empr_come = pscodigoempresa
              AND clie_cod_clie = pscodigoconsultora;

	 IF( lnCont = 0) THEN
	 	   DELETE FROM edu_histo_capac_cabec
               WHERE pais_cod_pais = pscodigopais
                 AND emco_cod_empr_come = pscodigoempresa
                 AND clie_cod_clie = pscodigoconsultora;
	 END IF;


      -- se obtine el nuevo nivel
      SELECT val_incr_codi
        INTO lnincremento
        FROM edu_param_progr_capac c
       WHERE pais_cod_pais = pscodigopais
         AND emco_cod_empr_come = pscodigoempresa
         AND cod_prog_capa = '01';

      lnnivel := TO_NUMBER (pscodigocurso) - lnincremento;

      IF (lnnivel > 0)
      THEN
	     --SE OBTINE LA CAMPANHA DE CAPACITACION DEK NIVEL ANTERIOR
	    SELECT CAM_CAPA  INTO lsCampCapa
	    FROM edu_histo_capac_detal
            WHERE pais_cod_pais = pscodigopais
              AND emco_cod_empr_come = pscodigoempresa
              AND clie_cod_clie = pscodigoconsultora
			  AND ccap_cod_curs_capa = lnnivel;

         --ACTUALIZAMOS ESTA TABLA QUEDA EN DESUSO PARA ESIKA
         UPDATE edu_histo_capac_cabec
            SET niv_capa_alca = lnnivel,                         --nuevo nivel
			    ULT_CAMP_CAPA = lsCampCapa,
                usu_modi = psusuario,
                fec_modi = SYSDATE
          WHERE pais_cod_pais = pscodigopais
            AND emco_cod_empr_come = pscodigoempresa
            AND clie_cod_clie = pscodigoconsultora;
      END IF;

 -- ACTUALIZAMOS EL HISTORICO DE APTAS
      UPDATE edu_histo_aptas
         SET cod_curs_dict = NULL,
             est_capa = 'PR',
             cam_capa = NULL,
			 cam_regu = NULL,
             usu_modi = psusuario,
             fec_modi = SYSDATE
       WHERE pais_cod_pais = pscodigopais
         AND emco_cod_empr_come = pscodigoempresa
         AND ccap_cod_curs_capa = pscodigocurso
         AND clie_cod_clie = pscodigoconsultora
         AND cod_plan_prog = pscodigoplanilla;

 --FINALMNETE SE INSERTA EN EL TRANSACCIONAL DE REGULARIZACIONES
 --TIPO OPERACION :EA :ELIMINAR ASISTENCIA
 --ELIMINAMOS EL REGISTRO ANTERIOR
      DELETE FROM edu_trans_regul
            WHERE pais_cod_pais = pscodigopais
              AND emco_cod_empr_come = pscodigoempresa
              AND ccap_cod_curs_capa = pscodigocurso
              AND clie_cod_clie = pscodigoconsultora;

  --INSERTAMOS EL REGISTRO ACTUAL PRA ESA CONSULTORA
      INSERT INTO edu_trans_regul
                  (pais_cod_pais, emco_cod_empr_come, ccap_cod_curs_capa,
                   clie_cod_clie, tip_oper, usu_digi, fec_digi, usu_modi,
                   fec_modi, est_regi,CAM_REGU
                  )
           VALUES (pscodigopais, pscodigoempresa, pscodigocurso,
                   pscodigoconsultora, 'EA', psusuario, SYSDATE, NULL,
                   NULL, '1',lsCampActual
                  );

	  -- ACTUALIZAMOS LA TABLA DE  BENEFICIOS ELIMINANDO REGISTROS PARA LA COLSULTORA PARA SU CAMPANHA DE CAPACITACION
	  EDU_PKG_PROCE_GENER.EDU_PR_REGUL_BENEF_CONSU(pscodigopais,pscodigoempresa,lscampcapac,pscodigocurso,pscodigoconsultora,'0','R',psusuario);
  RETURN;
EXCEPTION
      WHEN OTHERS
      THEN
         ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
         raise_application_error (-20123,
                                  'EDU_PR_ELIMI_ASIST: ' || ls_sqlerrm
                                 );
   END edu_pr_elimi_asist;

/***************************************************************************
Descripcion       : Procedimiento que efectua la generación del Reporte
                    de Cronograma Dictado
Fecha Creacion    : 11/12/2007
Autor             : Carlos Bazalar
Parametros:
          psCodigoPais  : Codigo de Pais
          psCodEmpresa  : Codigo de Empresa
          psCodPeriodo  : Codigo de Periodo
          psCodRegion   : Codigo de Region
          psUsuario     : Usuario
***************************************************************************/
   PROCEDURE edu_pr_gener_repor_crono_dicta (
      pscodigopais   VARCHAR2,
      pscodempresa   VARCHAR2,
      pscodperiodo   VARCHAR2,
      pscodregion    VARCHAR2,
	  pscodzona		 VARCHAR2,
      psusuario      VARCHAR2
)
IS
      lnnumminimosemana   NUMBER;
      lnnummaximosemana   NUMBER;
      ldminfechadictado   DATE;
      ldmaxfechadictado   DATE;
      ldminfechasemana    DATE;
      lsindicadortiempo   VARCHAR2 (2);
      lndia               NUMBER;
      lnsemana            NUMBER;
      lnhoraini           NUMBER;
      lsdescripcion       VARCHAR2 (200);
      lslistazonas        VARCHAR2 (100);
      lsseparador         VARCHAR2 (10);
      lssaltolinea        VARCHAR2 (10);
      lssala              edu_local_sala.des_sala%TYPE;
      regtempo            edu_tmp_repor_crono_dicta%ROWTYPE;

  /* Cursor apuntando a la Tabla temporal */
      CURSOR curtempo
      IS
    SELECT *
           FROM edu_tmp_repor_crono_dicta a
          WHERE a.cod_usua = psusuario AND a.ind_tiem = indicador_am;

  /* Cursor apuntando a la Tabla de Cronograna Dictado */
      CURSOR curcrono
      IS
         SELECT   a.oid_cron, a.fec_dict, a.hor_inic, a.hor_fina,
                  a.loca_cod_loca, b.des_loca, c.sig_curs_capa,
                  a.sala_cod_sala
             FROM edu_crono_dicta a, edu_local b, edu_param_curso_capac c
            WHERE a.pais_cod_pais = pscodigopais
              AND a.emco_cod_empr_come = pscodempresa
              AND a.cam_cron = pscodperiodo
              AND a.regi_cod_regi = pscodregion
              AND a.est_regi = indicador_activo
              AND b.pais_cod_pais = a.pais_cod_pais
              AND b.emco_cod_empr_come = a.emco_cod_empr_come
              AND b.cod_loca = a.loca_cod_loca
              AND c.pais_cod_pais = a.pais_cod_pais
              AND c.emco_cod_empr_come = a.emco_cod_empr_come
              AND c.cod_curs_capa = a.ccap_cod_curs_capa
         ORDER BY a.fec_dict, a.hor_inic;
   BEGIN
      lssaltolinea := CHR (10);
      lsseparador := CHR (10) || CHR (10);

  EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';

      DELETE FROM edu_tmp_repor_crono_dicta a
            WHERE a.cod_usua = psusuario;

  /* obteniendo la menor semana, mayor semana, menor fecha dictado */
  BEGIN
         SELECT MIN (a.fec_dict), MAX (a.fec_dict)
           INTO ldminfechadictado, ldmaxfechadictado
           FROM edu_crono_dicta a
          WHERE a.pais_cod_pais = pscodigopais
            AND a.emco_cod_empr_come = pscodempresa
            AND a.cam_cron = pscodperiodo
            AND a.regi_cod_regi = pscodregion
            AND a.est_regi = indicador_activo;

         lnnumminimosemana :=
            edu_pkg_proce_gener.edu_fn_devue_numer_seman
                               (TO_NUMBER (TRIM (TO_CHAR (ldminfechadictado,
                                                          'YYYY'
                                                         )
                                                )
                                          ),
                                  TO_NUMBER (TRIM (TO_CHAR (ldminfechadictado,
                                                            'MM'
                                                           )
                                                  )
                                            )
                                - 1,
                                TO_NUMBER (TRIM (TO_CHAR (ldminfechadictado,
                                                          'DD'
                                                         )
                                                )
                                          )
                               );
         lnnummaximosemana :=
            edu_pkg_proce_gener.edu_fn_devue_numer_seman
                               (TO_NUMBER (TRIM (TO_CHAR (ldmaxfechadictado,
                                                          'YYYY'
                                                         )
                                                )
                                          ),
                                  TO_NUMBER (TRIM (TO_CHAR (ldmaxfechadictado,
                                                            'MM'
                                                           )
                                                  )
                                            )
                                - 1,
                                TO_NUMBER (TRIM (TO_CHAR (ldmaxfechadictado,
                                                          'DD'
                                                         )
                                                )
                                          )
                               );

         IF lnnumminimosemana IS NULL OR lnnumminimosemana = 0
         THEN
       RETURN;
    END IF;

         IF lnnummaximosemana IS NULL OR lnnummaximosemana = 0
         THEN
       RETURN;
    END IF;
  EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
       RETURN;
  END;

  /* Insertando registros de semanas en la tabla temporal */
      FOR i IN lnnumminimosemana .. lnnummaximosemana
      LOOP
         INSERT INTO edu_tmp_repor_crono_dicta
                     (cod_usua, num_sema, ind_tiem
                     )
              VALUES (psusuario, i, indicador_am
                     );

         INSERT INTO edu_tmp_repor_crono_dicta
                     (cod_usua, num_sema, ind_tiem
                     )
              VALUES (psusuario, i, indicador_pm
                     );
  END LOOP;

  /* obteniendo fechas respectivas */
      lndia := TO_NUMBER (TO_CHAR (ldminfechadictado, 'D'));

      IF lndia = 1
      THEN
         ldminfechasemana := ldminfechadictado - 6;
  ELSE
         ldminfechasemana := ldminfechadictado - (lndia - 2);
  END IF;

      FOR ctempo IN curtempo
      LOOP
         ctempo.fec_lune := ldminfechasemana;
         ctempo.fec_mart := ldminfechasemana + 1;
         ctempo.fec_mier := ldminfechasemana + 2;
         ctempo.fec_juev := ldminfechasemana + 3;
         ctempo.fec_vier := ldminfechasemana + 4;
         ctempo.fec_saba := ldminfechasemana + 5;
         ctempo.fec_domi := ldminfechasemana + 6;
         ldminfechasemana := ldminfechasemana + 7;

         UPDATE edu_tmp_repor_crono_dicta a
            SET a.fec_lune = ctempo.fec_lune,
                a.fec_mart = ctempo.fec_mart,
                a.fec_mier = ctempo.fec_mier,
                a.fec_juev = ctempo.fec_juev,
                a.fec_vier = ctempo.fec_vier,
                a.fec_saba = ctempo.fec_saba,
                a.fec_domi = ctempo.fec_domi
          WHERE a.cod_usua = psusuario AND a.num_sema = ctempo.num_sema;
  END LOOP;

  /* Recorriendo cronograma de dictado */
      FOR ccrono IN curcrono
      LOOP
         lndia := TO_NUMBER (TO_CHAR (ccrono.fec_dict, 'D'));
         lnsemana :=
            edu_pkg_proce_gener.edu_fn_devue_numer_seman
                                (TO_NUMBER (TRIM (TO_CHAR (ccrono.fec_dict,
                                                           'YYYY'
                                                          )
                                                 )
                                           ),
                                   TO_NUMBER (TRIM (TO_CHAR (ccrono.fec_dict,
                                                             'MM'
                                                            )
                                                   )
                                             )
                                 - 1,
                                 TO_NUMBER (TRIM (TO_CHAR (ccrono.fec_dict,
                                                           'DD'
                                                          )
                                                 )
                                           )
                                );
         lnhoraini := TO_NUMBER (TRIM (SUBSTR (ccrono.hor_inic, 1, 2)));

         IF lnhoraini <= 11
         THEN
            lsindicadortiempo := indicador_am;
      ELSE
            lsindicadortiempo := indicador_pm;
      END IF;

         lsdescripcion := ccrono.des_loca || lssaltolinea;

         IF ccrono.sala_cod_sala IS NOT NULL
         THEN
            lssala :=
               edu_fn_devue_descr_sala (pscodigopais,
                                        pscodempresa,
                                        ccrono.loca_cod_loca,
                                        ccrono.sala_cod_sala
                                       );

            IF lssala IS NOT NULL
            THEN
               lsdescripcion := lsdescripcion || lssala || lssaltolinea;
         END IF;
      END IF;

         lsdescripcion :=
               lsdescripcion
            || ccrono.sig_curs_capa
            || lssaltolinea
            || 'DE '
            || ccrono.hor_inic
            || ' A '
            || ccrono.hor_fina;
         lslistazonas := edu_fn_devue_crono_dicta_zona (ccrono.oid_cron,pscodzona);

         lsdescripcion :=
                    lsdescripcion || lssaltolinea || 'ZONAS: ' || lslistazonas;

      /* Obteniendo datos del registro de la tabla Temporal */
      SELECT *
           INTO regtempo
           FROM edu_tmp_repor_crono_dicta a
          WHERE a.cod_usua = psusuario
            AND a.num_sema = lnsemana
            AND a.ind_tiem = lsindicadortiempo;

	  IF (lslistazonas IS NOT NULL) THEN
	    IF lndia = 1
         THEN
            regtempo.des_domi :=
               edu_fn_conca_caden (regtempo.des_domi,
                                   lsdescripcion,
                                   lsseparador
                                  );
         ELSIF lndia = 2
         THEN
            regtempo.des_lune :=
               edu_fn_conca_caden (regtempo.des_lune,
                                   lsdescripcion,
                                   lsseparador
                                  );
         ELSIF lndia = 3
         THEN
            regtempo.des_mart :=
               edu_fn_conca_caden (regtempo.des_mart,
                                   lsdescripcion,
                                   lsseparador
                                  );
         ELSIF lndia = 4
         THEN
            regtempo.des_mier :=
               edu_fn_conca_caden (regtempo.des_mier,
                                   lsdescripcion,
                                   lsseparador
                                  );
         ELSIF lndia = 5
         THEN
            regtempo.des_juev :=
               edu_fn_conca_caden (regtempo.des_juev,
                                   lsdescripcion,
                                   lsseparador
                                  );
         ELSIF lndia = 6
         THEN
            regtempo.des_vier :=
               edu_fn_conca_caden (regtempo.des_vier,
                                   lsdescripcion,
                                   lsseparador
                                  );
         ELSIF lndia = 7
         THEN
            regtempo.des_saba :=
               edu_fn_conca_caden (regtempo.des_saba,
                                   lsdescripcion,
                                   lsseparador
                                  );
      END IF;
      END IF; --FIN DEL IF DE DESCRIPCION DE ZONAS
      /* Grabando valores en la tabla temporal */
         UPDATE edu_tmp_repor_crono_dicta a
            SET a.des_lune = regtempo.des_lune,
                a.des_mart = regtempo.des_mart,
                a.des_mier = regtempo.des_mier,
                a.des_juev = regtempo.des_juev,
                a.des_vier = regtempo.des_vier,
                a.des_saba = regtempo.des_saba,
                a.des_domi = regtempo.des_domi
          WHERE a.cod_usua = psusuario
            AND a.num_sema = lnsemana
            AND a.ind_tiem = lsindicadortiempo;
  END LOOP;
EXCEPTION
      WHEN OTHERS
      THEN
     ln_sqlcode := SQLCODE;
         ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
         raise_application_error (-20123,
                                     'ERROR EDU_PR_GENER_REPOR_CRONO_DICTA: '
                                  || ls_sqlerrm
                                 );
   END edu_pr_gener_repor_crono_dicta;

/***************************************************************************
Descripcion       : Funcion que devuelve las zonas correspondientes al
                    Oid de Cronograma dictado ingresado como parametro
Fecha Creacion    : 13/12/2007
Autor             : Carlos Bazalar
Modificado		  : Sergio Buchelli
Parametros:
          pnOidCronograma : Oid Cronograma Dictado
		  pscodzona		  : Codigo Zona
***************************************************************************/
   FUNCTION edu_fn_devue_crono_dicta_zona (pnoidcronograma NUMBER,pscodzona VARCHAR2)
RETURN VARCHAR2
IS
      lsretorno   VARCHAR2 (100);

      CURSOR curdicta
      IS
         SELECT a.zona_cod_zona
           FROM edu_crono_dicta_zona a
          WHERE a.cron_oid_cron = pnoidcronograma
		   AND (pscodzona is null or pscodzona = a.ZONA_COD_ZONA);
BEGIN
      lsretorno := '';

      FOR cdicta IN curdicta
      LOOP
         lsretorno :=
                   edu_fn_conca_caden (lsretorno, cdicta.zona_cod_zona, ', ');
  END LOOP;

      RETURN lsretorno;
EXCEPTION
      WHEN OTHERS
      THEN
     ln_sqlcode := SQLCODE;
         ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
         raise_application_error (-20123,
                                     'ERROR EDU_FN_DEVUE_CRONO_DICTA_ZONA: '
                                  || ls_sqlerrm
                                 );
   END edu_fn_devue_crono_dicta_zona;

/***************************************************************************
Descripcion       : Funcion que devuelve la descripcion de la Sala
Fecha Creacion    : 18/12/2007
Autor             : Carlos Bazalar
Parametros:
          psCodigoPais  : Codigo de Pais
          psCodEmpresa  : Codigo de Empresa
          psCodLocal    : Codigo de Local
          psCodSala     : Codigo de Sala
***************************************************************************/
FUNCTION EDU_FN_DEVUE_DESCR_SALA(
  psCodigoPais  VARCHAR2,
  psCodEmpresa  VARCHAR2,
  psCodLocal    VARCHAR2,
  psCodSala     VARCHAR2
)
RETURN VARCHAR2
IS
  lsRetorno    EDU_LOCAL_SALA.DES_SALA%TYPE;

BEGIN
  lsRetorno := '';
  SELECT A.DES_SALA
  INTO lsRetorno
  FROM EDU_LOCAL_SALA A
  WHERE A.PAIS_COD_PAIS = psCodigoPais
    AND A.EMCO_COD_EMPR_COME = psCodEmpresa
    AND A.LOCA_COD_LOCA = psCodLocal
    AND A.COD_SALA = psCodSala
    AND A.COD_SALA IS NOT NULL;
  RETURN lsRetorno;
EXCEPTION
WHEN NO_DATA_FOUND THEN
     RETURN '';
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_FN_DEVUE_DESCR_SALA: '||ls_sqlerrm);
END EDU_FN_DEVUE_DESCR_SALA;

/***************************************************************************
Descripcion       : Funcion que devuelve una cadena concatenada
Fecha Creacion    : 13/12/2007
Autor             : Carlos Bazalar
Parametros:
     psCadenaOrigen        Cadena Origen
     psCadenaConcatenar    Cadena que se desea concatenar a la Cadena Origen
     psSeparador           Separador entre la Cadena Origen y lo que se desea concatenar
***************************************************************************/
FUNCTION EDU_FN_CONCA_CADEN(
  psCadenaOrigen       VARCHAR2,
  psCadenaConcatenar   VARCHAR2,
  psSeparador          VARCHAR2
)
RETURN VARCHAR2
IS
  lsRetorno    VARCHAR2(1000);
BEGIN
  IF psCadenaOrigen IS NULL THEN
     RETURN psCadenaConcatenar;
  END IF;
  lsRetorno := psCadenaOrigen;
  IF psSeparador IS NOT NULL THEN
     lsRetorno := lsRetorno || psSeparador;
  END IF;
  lsRetorno := lsRetorno || psCadenaConcatenar;
  RETURN lsRetorno;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_FN_CONCA_CADEN: '||ls_sqlerrm);
END EDU_FN_CONCA_CADEN;


/***************************************************************************
Descripcion       : Funcion que devuelve numero de campanhas entre periodos
Fecha Creacion    : 10/01/2008
Autor             : Sergio Buchelli
Parametros:
     psCodPeriodoInicial  Codigo Periodo Inicial
     psCodPeriodoFinal    Codigo Perido Final

***************************************************************************/
FUNCTION EDU_FN_DEVUE_NUMER_CAMPA(
  psCodPeriodoInicial  VARCHAR2,
  psCodPeriodoFinal    VARCHAR2
)
RETURN NUMBER
IS
  lsRetorno         VARCHAR2(1000);
  lnCodPeriodoInicial  NUMBER;
  lnCodPeriodoFinal    NUMBER;
  lnNumPerido     NUMBER;
  lnContador     NUMBER;
  lnNumIncremento    NUMBER;
  lsCodPeriodo     VARCHAR2(6);
BEGIN
  lnContador:=1;
  lnNumIncremento:=1;
  lsCodPeriodo:=psCodPeriodoInicial;
    --VALIDAMOS Q NO SEA NULLOS LOS PARAMETROS DE ENTADA ASI COMO Q EL PERIDO INICIAL MENOR PERIODO FINAL
  IF ( psCodPeriodoInicial IS NULL OR
     psCodPeriodoFinal   IS NULL   ) THEN
    RETURN -1;
  END IF ;

    lnCodPeriodoInicial:=TO_NUMBER(psCodPeriodoInicial);
  lnCodPeriodoFinal:=TO_NUMBER(psCodPeriodoFinal);

  IF(lnCodPeriodoInicial > lnCodPeriodoFinal) THEN
    RETURN -1;
  END IF;

  IF(lnCodPeriodoInicial = lnCodPeriodoFinal) THEN
    RETURN 0;
  END IF;
  --SE COMIENZA A RECORRER A BUSCAR EL PERIDO FINAL
   LOOP
    lsCodPeriodo:=EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_CODIG_PERIO(lsCodPeriodo,lnNumIncremento);

   IF(lsCodPeriodo=psCodPeriodoFinal) THEN
      RETURN lnContador;
   END IF;
   lnContador:=lnContador+1;
  END LOOP;

  RETURN -1;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_FN_DEVUE_NUMER_CAMPA: '||ls_sqlerrm);
END EDU_FN_DEVUE_NUMER_CAMPA;



/***************************************************************************
Descripcion       : Funcion que devuelve numero de invitaciones
Fecha Creacion    : 11/01/2008
Autor             : Sergio Buchelli
Parametros:
          psCodigoPais    : Codigo de Pais
          psCodigoEmpresa : Codigo de Empresa
    psCodigoCurso   : Codigo de Curso

***************************************************************************/
FUNCTION EDU_FN_DEVUE_NUMER_INVIT(
  psCodigoPais    VARCHAR2,
  psCodigoEmpresa    VARCHAR2,
  psCodigoCurso   VARCHAR2
)
RETURN NUMBER
IS
  lnNumInvitacion  NUMBER:=0;
BEGIN
 BEGIN
  SELECT B.NUM_INVI INTO lnNumInvitacion
  FROM EDU_PARAM_CURSO_CAPAC B
  WHERE  B.PAIS_COD_PAIS=psCodigoPais
      AND B.EMCO_COD_EMPR_COME=psCodigoEmpresa
      AND B.COD_CURS_CAPA=psCodigoCurso;

  IF lnNumInvitacion IS NULL THEN
    RETURN -1;
  END IF;

   EXCEPTION
   WHEN NO_DATA_FOUND THEN
        RETURN -1;
   END;

  RETURN lnNumInvitacion;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_FN_DEVUE_NUMER_INVIT: '||ls_sqlerrm);
END EDU_FN_DEVUE_NUMER_INVIT;


/***************************************************************************
Descripcion       : Procedimiento que genera lista de status Consultora que luego
                    sera enviada al Sistema Comercioa
Fecha Creacion    : 19/01/2008
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psCodPeriodo : Campaña de Periodo
            psCodProceso  : Codigo de Proceso
            psCodParam    : Codigo de Parametro
            psUsuario    : Usuario
***************************************************************************/
   PROCEDURE edu_pr_lista_statu_consu (
      pscodigopais      VARCHAR2,
      pscodigoempresa   VARCHAR2,
      pscodigoperiodo   VARCHAR2,
      pscodproceso      VARCHAR2,
      pscodparam        VARCHAR2
)
IS
      lsincrementocodigo   edu_param_progr_capac.val_incr_codi%TYPE;
      lncontador           NUMBER;
BEGIN
      SELECT a.val_incr_codi
        INTO lsincrementocodigo
        FROM edu_param_progr_capac a
       WHERE a.pais_cod_pais = pscodigopais
         AND a.emco_cod_empr_come = pscodigoempresa
         AND a.cod_prog_capa = '01';

      DELETE FROM edu_gtt_statu_consu;
  /* Insertando Lista de Consultoras Calificadas */
      INSERT INTO edu_gtt_statu_consu
                  (cod_pais, cod_empr_come, cod_clie, est_capa, des_capa,
                   niv_actu, niv_sgte, CAM_PROC,COD_REGI )
         SELECT pscodigopais, pscodigoempresa, a.clie_cod_clie, a.est_capa,
                c.des_esta_capa,
                NVL
                   (edu_pkg_proce_gener.edu_fn_devue_nivel_actua_consu
                                                             (pscodigopais,
                                                              pscodigoempresa,
                                                              a.clie_cod_clie
                                                             ),
                    ' '
                   ),
                a.ccap_cod_curs_capa,pscodigoperiodo,b.cod_regi
           FROM edu_histo_aptas a,
                edu_maest_clien b,
                edu_estad_capac c,
                edu_gtt_param_proce x
          WHERE a.pais_cod_pais = pscodigopais
            AND a.emco_cod_empr_come = pscodigoempresa
--            AND a.cam_ulti_cali_apta = pscodigoperiodo
            AND a.est_regi = indicador_activo
            AND b.pais_cod_pais = a.pais_cod_pais
            AND b.emco_cod_empr_come = a.emco_cod_empr_come
            AND b.cod_clie = a.clie_cod_clie
            AND c.cod_esta_capa = a.est_capa
			AND a.EST_CAPA <> 'CP'
            AND x.cod_proc = pscodproceso
            AND x.cod_para = pscodparam
            AND x.val_para_varc = b.cod_regi;

  /* Insertando Lista de Consultoras Capacitadas */
      INSERT INTO edu_gtt_statu_consu
                  (cod_pais, cod_empr_come, cod_clie, niv_sgte, est_capa,
                   des_capa, niv_actu , CAM_PROC,COD_REGI)
         SELECT pscodigopais, pscodigoempresa, a.clie_cod_clie,
                TRIM (TO_CHAR (  TO_NUMBER (a.niv_capa_alca)
                               + TO_NUMBER (lsincrementocodigo)
                              )
                     ),
                'CP', c.des_esta_capa, a.niv_capa_alca,pscodigoperiodo,b.cod_regi
           FROM edu_histo_capac_cabec a,
                edu_maest_clien b,
                edu_estad_capac c,
                edu_gtt_param_proce x
          WHERE a.pais_cod_pais = pscodigopais
            AND a.emco_cod_empr_come = pscodigoempresa
--            AND a.ult_camp_capa = pscodigoperiodo
            AND a.est_regi = indicador_activo
            AND b.pais_cod_pais = a.pais_cod_pais
            AND b.emco_cod_empr_come = a.emco_cod_empr_come
            AND b.cod_clie = a.clie_cod_clie
            AND c.cod_esta_capa = 'CP'
            AND x.cod_proc = pscodproceso
            AND x.cod_para = pscodparam
            AND x.val_para_varc = b.cod_regi
			AND NOT EXISTS(
				SELECT Z.COD_CLIE
				FROM edu_gtt_statu_consu Z
				WHERE Z.COD_PAIS=pscodigopais
					  AND Z.COD_EMPR_COME=pscodigoempresa
					  AND Z.COD_CLIE = a.clie_cod_clie
				);
EXCEPTION
      WHEN OTHERS
      THEN
     ln_sqlcode := SQLCODE;
         ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
         raise_application_error (-20123,
                                     'ERROR EDU_PR_LISTA_STATU_CONSU: '
                                  || ls_sqlerrm
                                 );
   END edu_pr_lista_statu_consu;

/***************************************************************************
Descripcion       : Proceso que se encarga de validar el numero de invitaciones
           en una campanha de proceso con estado pendiente de capacitación
Fecha Creacion    : 10/01/2008
Autor             : Sergio Buchelli Silva
Parametros:
          psCodigoPais    : Codigo de Pais
          psCodigoEmpresa : Codigo de Empresa
    psSentencia     : Sentencia
    psCampInicial   : Campanha primera apta de calificación
          psUsuario       : Usuario de Proceso
Parametros Salida   : psMensajeError: Mensaje de Error
***************************************************************************/

PROCEDURE EDU_PR_VALID_INVIT_PENDI_CAPAC(
    psCodigoPais        VARCHAR2,
    psCodigoEmpresa     VARCHAR2,
    psSentencia         VARCHAR2,
    psCampInicial  VARCHAR2,
    psUsuario           VARCHAR2,
    psMensajeError   OUT VARCHAR2
    )
   IS
   ls_sqlerrm         VARCHAR2(150);
   lsEstadoCapac    VARCHAR2(2):='PC';
   lnNumRegistros   NUMBER;
   lsSql         VARCHAR2(6):='SQL001';
   lsEstadoActivo     VARCHAR2(1):='1';
   lnContErrors    NUMBER:=0;
   lnContExito    NUMBER:=0;
   lnNumInvTeorica    NUMBER:=0;
   lnDifCamp    NUMBER:=0;
   lsCampActual    VARCHAR2(6);
   lsCampInicial   VARCHAR2(6);
   lnNumItem    NUMBER:=0;
   lsEstadoOk    VARCHAR2(1):='1';
   lsEstadoNOk    VARCHAR2(1):='0';
   lsObservacionOk    VARCHAR2(50):='Aplica Validación';


         TYPE tRegHistoAptas IS RECORD (
             CLIE_COD_CLIE           EDU_HISTO_APTAS.CLIE_COD_CLIE%TYPE,
             CCAP_COD_CURS_CAPA            EDU_HISTO_APTAS.CCAP_COD_CURS_CAPA%TYPE,
             CAM_PROC_ACTU            EDU_HISTO_APTAS.CAM_PRIM_CALI_APTA%TYPE,
             CAM_PRIM_CALI_APTA            EDU_HISTO_APTAS.CAM_PRIM_CALI_APTA%TYPE,
             CAM_ULTI_CALI_APTA            EDU_HISTO_APTAS.CAM_ULTI_CALI_APTA%TYPE,
             ULT_CAMP_PROG_DICT            EDU_HISTO_APTAS.ULT_CAMP_PROG_DICT%TYPE,
             EST_CAPA                 EDU_HISTO_APTAS.EST_CAPA%TYPE,
             INVI_CURS                 EDU_HISTO_APTAS.NUM_INVI%TYPE,
             NUM_INVI             EDU_HISTO_APTAS.NUM_INVI%TYPE
         );

        TYPE TABLA_MAESTRA IS TABLE OF tRegHistoAptas;
        tablaRegistro       TABLA_MAESTRA;

       TYPE tNumInv IS REF CURSOR;
     cNumInv tnumInv;

BEGIN

  psMensajeError := '';
  OPEN cNumInv FOR
      psSentencia
  USING psCodigoPais,psCodigoEmpresa,psCodigoPais,psCodigoEmpresa,psCodigoPais,psCodigoEmpresa,lsEstadoCapac;

  --Se valida que el registro de nose encuentre ya en la tabla de auditoria
  SELECT COUNT(1) INTO lnNumRegistros
  FROM EDU_AUDIT_CABEC A
  WHERE A.PAIS_COD_PAIS=psCodigoPais
      AND A.EMCO_COD_EMPR_COME=psCodigoEmpresa
   AND A.AUQU_COD_AUDI_QUER=lsSql;
  -- AND A.CAM_PROC=psCampInicial;


  --se borran los detalles
  DELETE FROM EDU_AUDIT_DETAL
  WHERE PAIS_COD_PAIS=psCodigoPais
      AND EMCO_COD_EMPR_COME=psCodigoEmpresa
   AND AUQU_COD_AUDI_QUER=lsSql;
--   AND AUCA_CAM_PROC=psCampInicial;

  --CAMPANHA DE PROCESO ACTUAL
  lsCampActual:=EDU_PKG_CALIF.EDU_FN_DEVUE_CAMPA_PROCE_ACTUA(psCodigoPais,psCodigoEmpresa);

  IF(lnNumRegistros=0)THEN
   --INSERT EN LA TABLA EDU_AUDIT_CABEC
   --psCampInicial ya no se usa , s ecambia por la campanha actual
     INSERT INTO EDU_AUDIT_CABEC(PAIS_COD_PAIS, EMCO_COD_EMPR_COME, AUQU_COD_AUDI_QUER,USU_DIGI,FEC_DIGI,EST_REGI,OBS_PROC,EST_PROC)
  VALUES(psCodigoPais,psCodigoEmpresa,lsSql,psUsuario,SYSDATE,lsEstadoActivo,lsObservacionOk,lsEstadoOk);
  END IF;

  LOOP
   FETCH cNumInv BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
   IF tablaRegistro.COUNT > 0 THEN
   FOR i IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
         --Por cada Registro se Procesa
			   IF(tablaRegistro(i).ULT_CAMP_PROG_DICT IS NOT NULL ) THEN
			    lsCampInicial:=tablaRegistro(i).CAM_PRIM_CALI_APTA;
			    lnDifCamp:=EDU_FN_DEVUE_NUMER_CAMPA(lsCampInicial,lsCampActual);

			    IF(lnDifCamp>=tablaRegistro(i).INVI_CURS)THEN
			     lnNumInvTeorica:=tablaRegistro(i).INVI_CURS;
			    ELSE
			     lnNumInvTeorica:=lnDifCamp;
			    END IF;

			   ELSE
			     lnNumInvTeorica:=0;
			   END IF;

 			     lnNumItem:=lnNumItem+1;
			   IF(lnNumInvTeorica=tablaRegistro(i).NUM_INVI)THEN
			    --REGISTRO EXITOSO
			    lnContExito:=lnContExito+1;
			    INSERT INTO EDU_AUDIT_DETAL(
			        PAIS_COD_PAIS,EMCO_COD_EMPR_COME, AUQU_COD_AUDI_QUER, CAM_PROC, NUM_ITEM,
			           COD_CLIE, COD_CURS_CAPA, EST_REGI_PROC, USU_DIGI, FEC_DIGI,EST_REGI,
			        ULT_CAMP_PROG_DICT,EST_CAPA)
			    VALUES(psCodigoPais,psCodigoEmpresa,lsSql,lsCampInicial,lnNumItem,
			      tablaRegistro(i).CLIE_COD_CLIE,tablaRegistro(i).CCAP_COD_CURS_CAPA,lsEstadoOk,psUsuario,SYSDATE,lsEstadoActivo,tablaRegistro(i).ULT_CAMP_PROG_DICT,lsEstadoCapac);


			   ELSE
			       --REGISTRO ERROR
			    lnContErrors:=lnContErrors+1;
			    INSERT INTO EDU_AUDIT_DETAL(
			        PAIS_COD_PAIS,EMCO_COD_EMPR_COME, AUQU_COD_AUDI_QUER, CAM_PROC, NUM_ITEM,
			           COD_CLIE, COD_CURS_CAPA, EST_REGI_PROC, USU_DIGI, FEC_DIGI,EST_REGI,
			        ULT_CAMP_PROG_DICT,EST_CAPA)
			    VALUES(psCodigoPais,psCodigoEmpresa,lsSql,lsCampInicial,lnNumItem,
			      tablaRegistro(i).CLIE_COD_CLIE,tablaRegistro(i).CCAP_COD_CURS_CAPA,lsEstadoNOk,psUsuario,SYSDATE,lsEstadoActivo,tablaRegistro(i).ULT_CAMP_PROG_DICT,lsEstadoCapac);

			   END IF;

       END LOOP;
      END IF;
    EXIT WHEN cNumInv%NOTFOUND;
  END LOOP;
    CLOSE cNumInv;

    --UPDATE AUDITORIA CABECERA

  UPDATE EDU_AUDIT_CABEC A
  SET A.NUM_REGI_CORR=lnContExito,
      A.NUM_REGI_ERRO=lnContErrors,
   A.NUM_REGI_PROC=lnContExito+lnContErrors,
   A.EST_PROC=DECODE(lnContErrors,0,lsEstadoOk,lsEstadoNOk),
   A.USU_MODI=psUsuario,
   A.FEC_MODI=SYSDATE
  WHERE A.PAIS_COD_PAIS=psCodigoPais
      AND A.EMCO_COD_EMPR_COME=psCodigoEmpresa
   AND A.AUQU_COD_AUDI_QUER=lsSql;
 --  AND A.CAM_PROC=psCampInicial;

  RETURN;
EXCEPTION
WHEN OTHERS THEN
      ls_sqlerrm := substr(sqlerrm,1,250);
      RAISE_APPLICATION_ERROR(-20123, 'EDU_PR_VALID_INVIT_PENDI_CAPAC: '||ls_sqlerrm);

END EDU_PR_VALID_INVIT_PENDI_CAPAC;


/***************************************************************************
Descripcion       : Proceso que se encarga de recorrer la tabla de query
          y ejecutarlos dinamicamente
Fecha Creacion    : 11/01/2008
Autor             : Sergio Buchelli Silva
Parametros:
          psCodigoPais    : Codigo de Pais
          psCodigoEmpresa : Codigo de Empresa
          psCampanha   : Campanha Inicial
          psUsuario       : Usuario de Proceso
Parametros Salida   : psMensajeError: Mensaje de Error
***************************************************************************/
PROCEDURE edu_pr_proc_query (
      pscodigopais            VARCHAR2,
      pscodigoempresa         VARCHAR2,
      pscampanha              VARCHAR2,
      psusuario               VARCHAR2,
      psmensajeerror    OUT   VARCHAR2
    )
   IS
        ls_sqlerrm    VARCHAR2(150);

      CURSOR cursorquery
        IS
         SELECT   a.cod_audi_quer, a.val_sent_quer
             FROM edu_audit_query a
            WHERE a.est_regi = '1'
   ORDER BY 1;
   BEGIN
      psmensajeerror := '';

      FOR vcursorquery IN cursorquery
      LOOP
   --valida el numero de invitaciones
         IF (vcursorquery.cod_audi_quer = 'SQL001')
         THEN
            edu_pr_valid_invit_pendi_capac (pscodigopais,
                                            pscodigoempresa,
                                            vcursorquery.val_sent_quer,
                                            pscampanha,
                                            psusuario,
                                            psmensajeerror
                                           );
   END IF;

            -- valida las aptas programadas
         IF (vcursorquery.cod_audi_quer = 'SQL002')
         THEN
            edu_pr_valid_progr (pscodigopais,
                                pscodigoempresa,
                                vcursorquery.val_sent_quer,
                                pscampanha,
                                psusuario,
                                psmensajeerror
                               );
   END IF;

     -- valida los registros bloquedos
         IF (vcursorquery.cod_audi_quer = 'SQL003')
         THEN
            edu_pr_valid_bloqu (pscodigopais,
                                pscodigoempresa,
                                vcursorquery.val_sent_quer,
                                pscampanha,
                                psusuario,
                                psmensajeerror
                               );
   END IF;

     -- Valida el estadod ela consutora por curso
         IF (vcursorquery.cod_audi_quer = 'SQL004')
         THEN
            edu_pr_valid_estad_consu (pscodigopais,
                                      pscodigoempresa,
                                      vcursorquery.val_sent_quer,
                                      pscampanha,
                                      psusuario,
                                      psmensajeerror
                                     );
   END IF;

     -- Valida consultars q no le han registrado asistencia
         IF (vcursorquery.cod_audi_quer = 'SQL005')
         THEN
            edu_pr_valid_regis_asist (pscodigopais,
                                      pscodigoempresa,
                                      vcursorquery.val_sent_quer,
                                      pscampanha,
                                      psusuario,
                                      psmensajeerror
                                     );
   END IF;

      -- Valida que no haya registros pendientes de facturacion
         IF (vcursorquery.cod_audi_quer = 'SQL006')
         THEN
            edu_pr_valid_estad_pendi_factu (pscodigopais,
                                            pscodigoempresa,
                                            vcursorquery.val_sent_quer,
                                            pscampanha,
                                            psusuario,
                                            psmensajeerror
                                           );
   END IF;

         -- Valida que no haya registros pendientes de Programacion
         IF (vcursorquery.cod_audi_quer = 'SQL007')
         THEN
            edu_pr_valid_estad_pendi_proga (pscodigopais,
                                            pscodigoempresa,
                                            vcursorquery.val_sent_quer,
                                            pscampanha,
                                            psusuario,
                                            psmensajeerror
                                           );
         END IF;

         -- Valida que no existan planillas acitvas cuando hay registro de planillas para el periodo siguientre en la misma region
         IF (vcursorquery.cod_audi_quer = 'SQL008')
         THEN
            edu_pr_valid_plani_activ (pscodigopais,
                                      pscodigoempresa,
                                      vcursorquery.val_sent_quer,
                                      pscampanha,
                                      psusuario,
                                      psmensajeerror
                                     );
   END IF;

         -- Valida que las consultoras registradas en una plnilla, se encuentren en el detalle del curso dictado
         IF (vcursorquery.cod_audi_quer = 'SQL009')
         THEN
            edu_pr_valid_progr_detal_dicta (pscodigopais,
                                            pscodigoempresa,
                                            vcursorquery.val_sent_quer,
                                            pscampanha,
                                            psusuario,
                                            psmensajeerror
                                           );
         END IF;

      -- Valida que las consultoras registradas en una plnilla, se encuentren en el detalle del curso dictado
         IF (vcursorquery.cod_audi_quer = 'SQL010')
         THEN
            edu_pr_valid_capac (pscodigopais,
                                            pscodigoempresa,
                                            vcursorquery.val_sent_quer,
                                            pscampanha,
                                            psusuario,
                                            psmensajeerror
                                           );
         END IF;

     -- Valida Indocador curso costo
         IF (vcursorquery.cod_audi_quer = 'SQL011')
         THEN
            edu_pr_valid_indic_curso_costo(pscodigopais,
                                            pscodigoempresa,
                                            vcursorquery.val_sent_quer,
                                            pscampanha,
                                            psusuario,
                                            psmensajeerror
                                           );
         END IF;

  -- Valida Indocador curso costo
         IF (vcursorquery.cod_audi_quer = 'SQL012')
         THEN
            edu_pr_valid_curso_sin_costo(pscodigopais,
                                            pscodigoempresa,
                                            vcursorquery.val_sent_quer,
                                            pscampanha,
                                            psusuario,
                                            psmensajeerror
                                           );
         END IF;


  -- Valida Indocador curso costo
         IF (vcursorquery.cod_audi_quer = 'SQL013')
         THEN
            edu_pr_valid_curso_con_costo(pscodigopais,
                                            pscodigoempresa,
                                            vcursorquery.val_sent_quer,
                                            pscampanha,
                                            psusuario,
                                            psmensajeerror
                                           );
         END IF;

  END LOOP;

  RETURN;
EXCEPTION
      WHEN OTHERS
      THEN
         ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
         raise_application_error (-20123,
                                  'EDU_PR_PROC_QUERY: ' || ls_sqlerrm);
   END edu_pr_proc_query;

/***************************************************************************
Descripcion       : Proceso que se encarga de validar que sólo para el proceso
       de campanha actual se encuentren en estado 'PR '

Fecha Creacion    : 10/01/2008
Autor             : Sergio Buchelli Silva
Parametros:
          psCodigoPais    : Codigo de Pais
          psCodigoEmpresa : Codigo de Empresa
          psCampanha   : Campanha Inicial
    psSentencia      :Setencia,
          psUsuario       : Usuario de Proceso
Parametros Salida   : psMensajeError: Mensaje de Error
***************************************************************************/

PROCEDURE EDU_PR_VALID_PROGR(
    psCodigoPais        VARCHAR2,
    psCodigoEmpresa     VARCHAR2,
    psSentencia         VARCHAR2,
 psCampanha   VARCHAR2,
    psUsuario           VARCHAR2,
 psMensajeError   OUT VARCHAR2
    )IS
     ls_sqlerrm         VARCHAR2(150);

     lsSql        VARCHAR2(6):='SQL002';
     lsEstadoActivo       VARCHAR2(1):='1';
     lnContErrors    NUMBER:=0;
  lnContExitos    NUMBER:=0;

     lnNumRegisProc    NUMBER:=0;
  lnNumRegistros       NUMBER:=0;
     lsCampActual    VARCHAR2(6);
     lnNumItem       NUMBER:=0;
     lsEstadoOk       VARCHAR2(1):='1';
     lsEstadoNOk    VARCHAR2(1):='0';
  lsObservacionOk      VARCHAR2(50):='Aplica Validación';
  lsObservacionNOk     VARCHAR2(70):='No Aplica Validación para campaña menores o mayores de la Actual';
  lsEstadoCapac    VARCHAR(2):='PR';

  TYPE tRegHistoAptas IS RECORD (
     CLIE_COD_CLIE           EDU_HISTO_APTAS.CLIE_COD_CLIE%TYPE,
             CCAP_COD_CURS_CAPA            EDU_HISTO_APTAS.CCAP_COD_CURS_CAPA%TYPE,
             ULT_CAMP_PROG_DICT            EDU_HISTO_APTAS.ULT_CAMP_PROG_DICT%TYPE,
             EST_CAPA                 EDU_HISTO_APTAS.EST_CAPA%TYPE
         );

        TYPE TABLA_MAESTRA IS TABLE OF tRegHistoAptas;
        tablaRegistro       TABLA_MAESTRA;

  TYPE tValidProg IS REF CURSOR;
  cValidProg tValidProg;

BEGIN

  --Se valida que el registro de nose encuentre ya en la tabla de auditoria
  SELECT COUNT(1) INTO lnNumRegistros
  FROM EDU_AUDIT_CABEC A
  WHERE A.PAIS_COD_PAIS=psCodigoPais
      AND A.EMCO_COD_EMPR_COME=psCodigoEmpresa
   AND A.AUQU_COD_AUDI_QUER=lsSql;
   --AND A.CAM_PROC=psCampanha;
  --se borran los detalles
  DELETE FROM EDU_AUDIT_DETAL
  WHERE PAIS_COD_PAIS=psCodigoPais
      AND EMCO_COD_EMPR_COME=psCodigoEmpresa
   AND AUQU_COD_AUDI_QUER=lsSql;
--   AND CAM_PROC=psCampanha;


  IF(lnNumRegistros=0)THEN
    --INSERT EN LA TABLA EDU_AUDIT_CABEC
     INSERT INTO EDU_AUDIT_CABEC(PAIS_COD_PAIS, EMCO_COD_EMPR_COME, AUQU_COD_AUDI_QUER,USU_DIGI,FEC_DIGI,EST_REGI,OBS_PROC,EST_PROC)
     VALUES(psCodigoPais,psCodigoEmpresa,lsSql,psUsuario,SYSDATE,lsEstadoActivo,lsObservacionOk,lsEstadoOk);
  END IF;

  --CAMPANHA DE PROCESO ACTUAL
  lsCampActual:=EDU_PKG_CALIF.EDU_FN_DEVUE_CAMPA_PROCE_ACTUA(psCodigoPais,psCodigoEmpresa);

  --Cantidad de Registros a procesar

  SELECT COUNT(1) INTO lnNumRegisProc
  FROM  EDU_HISTO_APTAS A
  WHERE A.PAIS_COD_PAIS=psCodigoPais
     AND A.EMCO_COD_EMPR_COME=psCodigoEmpresa
     AND A.EST_CAPA=lsEstadoCapac;


     OPEN cValidProg FOR
        psSentencia
     USING psCodigoPais,psCodigoEmpresa,lsEstadoCapac,psCodigoPais,psCodigoEmpresa;
     --TODOS LOS REGISTROS A RECORRER SON ERROR
     LOOP
     FETCH cValidProg BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
       IF tablaRegistro.COUNT > 0 THEN
       FOR i IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP

        lnNumItem:=lnNumItem+1;
        lnContErrors:=lnContErrors+1;
	      INSERT INTO EDU_AUDIT_DETAL(
		        PAIS_COD_PAIS,EMCO_COD_EMPR_COME, AUQU_COD_AUDI_QUER, CAM_PROC, NUM_ITEM,
		        COD_CLIE, COD_CURS_CAPA, EST_REGI_PROC, USU_DIGI, FEC_DIGI,EST_REGI,
		        ULT_CAMP_PROG_DICT,EST_CAPA)
	      VALUES(psCodigoPais,psCodigoEmpresa,lsSql,tablaRegistro(i).ULT_CAMP_PROG_DICT,lnNumItem,
		      tablaRegistro(i).CLIE_COD_CLIE,tablaRegistro(i).CCAP_COD_CURS_CAPA,lsEstadoNOk,psUsuario,SYSDATE,lsEstadoActivo,tablaRegistro(i).ULT_CAMP_PROG_DICT,tablaRegistro(i).EST_CAPA);

        END LOOP;
       END IF;
       EXIT WHEN cValidProg%NOTFOUND;
     END LOOP;
    CLOSE cValidProg;

   --UPDATE AUDITORIA CABECERA

  UPDATE EDU_AUDIT_CABEC A
  SET A.NUM_REGI_CORR=lnNumRegisProc-lnContErrors,
      A.NUM_REGI_ERRO=lnContErrors,
   A.NUM_REGI_PROC=lnNumRegisProc,
   A.EST_PROC=DECODE(lnContErrors,0,lsEstadoOk,lsEstadoNOk),
   A.USU_MODI=psUsuario,
   A.FEC_MODI=SYSDATE
  WHERE A.PAIS_COD_PAIS=psCodigoPais
      AND A.EMCO_COD_EMPR_COME=psCodigoEmpresa
   AND A.AUQU_COD_AUDI_QUER=lsSql;
  -- AND A.CAM_PROC=psCampanha;

 RETURN;
EXCEPTION
WHEN OTHERS THEN
      ls_sqlerrm := substr(sqlerrm,1,250);
      RAISE_APPLICATION_ERROR(-20123, 'EDU_PR_VALID_PROGR: '||ls_sqlerrm);

END EDU_PR_VALID_PROGR;

 /***************************************************************************
Descripcion       : Proceso que se encarga de validar los registros
          bloqueados

Fecha Creacion    : 15/01/2008
Autor             : Sergio Buchelli Silva
Parametros:
          psCodigoPais    : Codigo de Pais
          psCodigoEmpresa : Codigo de Empresa
          psCampanha    : Campanha Inicial
      psSentencia     : Setencia,
          psUsuario       : Usuario de Proceso
Parametros Salida   : psMensajeError: Mensaje de Error
***************************************************************************/

PROCEDURE EDU_PR_VALID_BLOQU(
    psCodigoPais        VARCHAR2,
    psCodigoEmpresa     VARCHAR2,
    psSentencia         VARCHAR2,
 psCampanha   VARCHAR2,
    psUsuario           VARCHAR2,
 psMensajeError    OUT VARCHAR2
    )IS
     ls_sqlerrm          VARCHAR2(150);

     lsSql         VARCHAR2(6):='SQL003';
     lsEstadoActivo        VARCHAR2(1):='1';
     lnContErrors      NUMBER:=0;
  lnContExitos     NUMBER:=0;

     lnNumRegisProc     NUMBER:=0;
  lnNumRegistros      NUMBER:=0;
     lsCampActual      VARCHAR2(6);
     lnNumItem        NUMBER:=0;
     lsEstadoOk        VARCHAR2(1):='1';
     lsEstadoNOk     VARCHAR2(1):='0';
  lsObservacionOk        VARCHAR2(50):='Aplica Validación';
  lsEstadoCapac     VARCHAR2(2):='PC';
  lsEstadoPO        VARCHAR2(2):='PO';
  lsCodCurso    EDU_HISTO_APTAS.CCAP_COD_CURS_CAPA%TYPE;
  lsPrimeraCali   EDU_HISTO_APTAS.CAM_PRIM_CALI_APTA%TYPE;
  lnNumInvi    EDU_HISTO_APTAS.NUM_INVI%TYPE;


  TYPE tRegHistoAptas IS RECORD (
     CLIE_COD_CLIE           EDU_HISTO_APTAS.CLIE_COD_CLIE%TYPE,
    CCAP_COD_CURS_CAPA            EDU_HISTO_APTAS.CCAP_COD_CURS_CAPA%TYPE,
    ULT_CAMP_PROG_DICT          EDU_HISTO_APTAS.ULT_CAMP_PROG_DICT%TYPE,
    EST_CAPA                 EDU_HISTO_APTAS.EST_CAPA%TYPE
         );

        TYPE TABLA_MAESTRA IS TABLE OF tRegHistoAptas;
        tablaRegistro       TABLA_MAESTRA;

  TYPE tValidBloq IS REF CURSOR;
  cValidBloq tValidBloq;

   --Se obtiene aquellas consultoras que deben ser bloquedas y no se
   -- han registrado en la tabla de bloqueo
  CURSOR cursorBloq(piv_pais VARCHAR2,piv_empresa VARCHAR2,piv_curso VARCHAR2,piv_estadoPC VARCHAR2,piv_estadoPO VARCHAR2,
  		  			piv_camp VARCHAR2,pv_numinv NUMBER,pv_estadoreg VARCHAR2)
  IS
  SELECT A.CLIE_COD_CLIE, A.CCAP_COD_CURS_CAPA,A.ULT_CAMP_PROG_DICT,A.EST_CAPA
        FROM EDU_HISTO_APTAS A,
             EDU_MAEST_CLIEN C
        WHERE A.PAIS_COD_PAIS = piv_pais
          AND A.EMCO_COD_EMPR_COME = piv_empresa
          AND A.CCAP_COD_CURS_CAPA = piv_curso
          AND A.EST_CAPA IN (piv_estadoPC,piv_estadoPO)
          AND A.CAM_ULTI_CALI_APTA < piv_camp
          AND A.NUM_INVI = pv_numinv
          AND A.EST_REGI = pv_estadoreg
          AND C.PAIS_COD_PAIS = A.PAIS_COD_PAIS
          AND C.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
          AND C.COD_CLIE = A.CLIE_COD_CLIE
    AND NOT EXISTS
            (SELECT * FROM edu_histo_bloqu_consu B
                    WHERE A.PAIS_COD_PAIS=B.PAIS_COD_PAIS
     AND A.EMCO_COD_EMPR_COME=B.EMCO_COD_EMPR_COME
     AND A.CAM_ULTI_CALI_APTA<B.CAM_proc
     AND A.CLIE_COD_CLIE=B.CLIE_COD_CLIE
    );

BEGIN

  --Se valida que el registro de no se encuentre ya en la tabla de auditoria
  SELECT COUNT(1) INTO lnNumRegistros
  FROM EDU_AUDIT_CABEC A
  WHERE A.PAIS_COD_PAIS=psCodigoPais
      AND A.EMCO_COD_EMPR_COME=psCodigoEmpresa
   AND A.AUQU_COD_AUDI_QUER=lsSql;
  -- AND A.CAM_PROC=psCampanha;

  --se borran los detalles
  DELETE FROM EDU_AUDIT_DETAL
  WHERE PAIS_COD_PAIS=psCodigoPais
      AND EMCO_COD_EMPR_COME=psCodigoEmpresa
   AND AUQU_COD_AUDI_QUER=lsSql;
 --  AND AUCA_CAM_PROC=psCampanha;


  BEGIN
   SELECT A.COD_CURS_CAPA,A.NUM_INVI INTO lsCodCurso,lnNumInvi
   FROM EDU_PARAM_CURSO_CAPAC A
   WHERE A.PAIS_COD_PAIS=psCodigoPais
     AND A.EMCO_COD_EMPR_COME=psCodigoEmpresa
	 AND A.STA_CLIE = 'N'; -- NUEVAS,INDICADOR_SI

  EXCEPTION
   WHEN OTHERS THEN
      ls_sqlerrm := substr(sqlerrm,1,250);
      RAISE_APPLICATION_ERROR(-20123, 'EDU_PR_VALID_BLOQU: '||ls_sqlerrm);
	RETURN ;
  END;



  IF(lnNumRegistros=0)THEN
    --INSERT EN LA TABLA EDU_AUDIT_CABEC
     INSERT INTO EDU_AUDIT_CABEC(PAIS_COD_PAIS, EMCO_COD_EMPR_COME, AUQU_COD_AUDI_QUER,USU_DIGI,FEC_DIGI,EST_REGI,OBS_PROC,EST_PROC)
     VALUES(psCodigoPais,psCodigoEmpresa,lsSql,psUsuario,SYSDATE,lsEstadoActivo,lsObservacionOk,lsEstadoOk);
  END IF;

    --CAMPANHA DE PROCESO ACTUAL
  lsCampActual:=EDU_PKG_CALIF.EDU_FN_DEVUE_CAMPA_PROCE_ACTUA(psCodigoPais,psCodigoEmpresa);

  --Cantidad de Registros a procesar SIN ERROR
  --Query Dinamico nos da todas las consultoras bloqueadas que si han sido colocadas en la tabla de bloqueo
     OPEN cValidBloq FOR
        psSentencia
     USING psCodigoPais,psCodigoEmpresa,lsCodCurso,lsEstadoCapac,lsEstadoPO,lsCampActual,lnNumInvi,lsEstadoActivo;

     LOOP
       FETCH cValidBloq BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
       IF tablaRegistro.COUNT > 0 THEN

          FOR i IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
		      lnNumItem:=lnNumItem+1;
		      lnContExitos:=lnContExitos+1;
		      INSERT INTO EDU_AUDIT_DETAL(
		         PAIS_COD_PAIS,EMCO_COD_EMPR_COME, AUQU_COD_AUDI_QUER, CAM_PROC, NUM_ITEM,
		            COD_CLIE, COD_CURS_CAPA, EST_REGI_PROC, USU_DIGI, FEC_DIGI,EST_REGI,
		         ULT_CAMP_PROG_DICT,EST_CAPA)
		      VALUES(psCodigoPais,psCodigoEmpresa,lsSql,lsCampActual,lnNumItem,
		         tablaRegistro(i).CLIE_COD_CLIE,tablaRegistro(i).CCAP_COD_CURS_CAPA,lsEstadoOk,psUsuario,SYSDATE,lsEstadoActivo,tablaRegistro(i).ULT_CAMP_PROG_DICT,tablaRegistro(i).EST_CAPA);

       END LOOP;
      END IF;
    EXIT WHEN cValidBloq%NOTFOUND;
    END LOOP;
    CLOSE cValidBloq;


    --Cantidad de Registros a Procesar Con error
	--Consultoras que no han sido colocadas en la tabla de bloueo ERROR
    OPEN cursorBloq(psCodigoPais,psCodigoEmpresa,lsCodCurso,lsEstadoCapac,lsEstadoPO,lsCampActual,lnNumInvi,lsEstadoActivo);
     LOOP
      FETCH cursorBloq BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
       IF tablaRegistro.COUNT > 0 THEN

          FOR i IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
	      lnNumItem:=lnNumItem+1;
	      lnContErrors:=lnContErrors+1;
	       INSERT INTO EDU_AUDIT_DETAL(
	         PAIS_COD_PAIS,EMCO_COD_EMPR_COME, AUQU_COD_AUDI_QUER, CAM_PROC, NUM_ITEM,
	            COD_CLIE, COD_CURS_CAPA, EST_REGI_PROC, USU_DIGI, FEC_DIGI,EST_REGI,
	         ULT_CAMP_PROG_DICT,EST_CAPA)
	       VALUES(psCodigoPais,psCodigoEmpresa,lsSql,lsCampActual,lnNumItem,
	          tablaRegistro(i).CLIE_COD_CLIE,tablaRegistro(i).CCAP_COD_CURS_CAPA,lsEstadoNOk,psUsuario,SYSDATE,lsEstadoActivo,tablaRegistro(i).ULT_CAMP_PROG_DICT,tablaRegistro(i).EST_CAPA);

       END LOOP;
      END IF;
     EXIT WHEN cursorBloq%NOTFOUND;
     END LOOP;
    CLOSE cursorBloq;


   --UPDATE AUDITORIA CABECERA

  UPDATE EDU_AUDIT_CABEC A
  SET A.NUM_REGI_CORR=lnContExitos,
      A.NUM_REGI_ERRO=lnContErrors,
    A.NUM_REGI_PROC=lnContExitos+lnContErrors,
     A.EST_PROC=DECODE(lnContErrors,0,lsEstadoOk,lsEstadoNOk),
     A.USU_MODI=psUsuario,
     A.FEC_MODI=SYSDATE
  WHERE A.PAIS_COD_PAIS=psCodigoPais
      AND A.EMCO_COD_EMPR_COME=psCodigoEmpresa
   AND A.AUQU_COD_AUDI_QUER=lsSql;
--   AND A.CAM_PROC=psCampanha;

 RETURN;
EXCEPTION
WHEN OTHERS THEN
      ls_sqlerrm := substr(sqlerrm,1,250);
      RAISE_APPLICATION_ERROR(-20123, 'EDU_PR_VALID_BLOQU: '||ls_sqlerrm);

END EDU_PR_VALID_BLOQU;


/***************************************************************************
Descripcion       : Proceso que se encarga de validar el estado
          de las consultoras por curso
Fecha Creacion    : 15/01/2008
Autor             : Sergio Buchelli Silva
Parametros:
          psCodigoPais    : Codigo de Pais
          psCodigoEmpresa : Codigo de Empresa
          psCampanha    : Campanha Inicial
      psSentencia     : Setencia,
          psUsuario       : Usuario de Proceso
Parametros Salida   : psMensajeError: Mensaje de Error
***************************************************************************/

PROCEDURE EDU_PR_VALID_ESTAD_CONSU(
    psCodigoPais        VARCHAR2,
    psCodigoEmpresa     VARCHAR2,
    psSentencia         VARCHAR2,
	psCampanha   		VARCHAR2,
    psUsuario           VARCHAR2,
    psMensajeError    OUT VARCHAR2
    )IS
     ls_sqlerrm         VARCHAR2(150);

     lsSql        		VARCHAR2(6):='SQL004';
     lsEstadoActivo     VARCHAR2(1):='1';

   lnContErrors     NUMBER:=0;
   lnContExitos     NUMBER:=0;
   lnNumRegistros   NUMBER:=0;
   lnNumItem        NUMBER:=0;

   lsEstadoOk       VARCHAR2(1):='1';
   lsEstadoNOk      VARCHAR2(1):='0';
   lsObservacionOk  VARCHAR2(50):='Aplica Validación';

   lsEstadoCapac    VARCHAR(2):='CP';

   lbFlagFirstRow   BOOLEAN;
   codClienAnt    EDU_HISTO_APTAS.CLIE_COD_CLIE%TYPE;
   codClienAct    EDU_HISTO_APTAS.CLIE_COD_CLIE%TYPE;

  TYPE tRegHistoAptas IS RECORD (
  	   		 CLIE_COD_CLIE     EDU_HISTO_APTAS.CLIE_COD_CLIE%TYPE,
             CCAP_COD_CURS_CAPA      EDU_HISTO_APTAS.CCAP_COD_CURS_CAPA%TYPE,
             ULT_CAMP_PROG_DICT      EDU_HISTO_APTAS.ULT_CAMP_PROG_DICT%TYPE,
             EST_CAPA                EDU_HISTO_APTAS.EST_CAPA%TYPE,
			 CAM_ULTI_CALI_APTA      EDU_HISTO_APTAS.CAM_ULTI_CALI_APTA%TYPE
         );

        TYPE TABLA_MAESTRA IS TABLE OF tRegHistoAptas;
        tablaRegistro       TABLA_MAESTRA;

  TYPE tValidEstCons IS REF CURSOR;
  cValidEstCons tValidEstCons;


BEGIN

  --Se valida que el registro no se encuentre ya en la tabla de auditoria
  SELECT COUNT(1) INTO lnNumRegistros
  FROM EDU_AUDIT_CABEC A
  WHERE A.PAIS_COD_PAIS=psCodigoPais
      AND A.EMCO_COD_EMPR_COME=psCodigoEmpresa
   AND A.AUQU_COD_AUDI_QUER=lsSql;
--   AND A.CAM_PROC=psCampanha;

  --se borran los detalles
  DELETE FROM EDU_AUDIT_DETAL
  WHERE PAIS_COD_PAIS=psCodigoPais
      AND EMCO_COD_EMPR_COME=psCodigoEmpresa
   AND AUQU_COD_AUDI_QUER=lsSql;
 --  AND CAM_PROC=psCampanha;


  IF(lnNumRegistros=0)THEN
    --INSERT EN LA TABLA EDU_AUDIT_CABEC
     INSERT INTO EDU_AUDIT_CABEC(PAIS_COD_PAIS, EMCO_COD_EMPR_COME, AUQU_COD_AUDI_QUER,USU_DIGI,FEC_DIGI,EST_REGI,OBS_PROC,EST_PROC)
     VALUES(psCodigoPais,psCodigoEmpresa,lsSql,psUsuario,SYSDATE,lsEstadoActivo,lsObservacionOk,lsEstadoOk);
  END IF;


  --Abrimos el cursos dinamicamemte
     OPEN cValidEstCons FOR
        psSentencia
     USING psCodigoPais,psCodigoEmpresa;

  lbFlagFirstRow:=TRUE;
     LOOP
       FETCH cValidEstCons BULK COLLECT INTO tablaRegistro;
       IF tablaRegistro.COUNT > 0 THEN

       FOR i IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP

      --PRIMER REGISTRO NO SE PROCESA
     IF(lbFlagFirstRow) THEN

     lbFlagFirstRow:=FALSE;
     ELSE
        codClienAnt:=tablaRegistro(i-1).CLIE_COD_CLIE;
        codClienAct:=tablaRegistro(i).CLIE_COD_CLIE;

        IF(codClienAct=codClienAnt)THEN
          lnNumItem:=lnNumItem+1;

         --VALIDAMOS EL ESTADO CP
          IF(tablaRegistro(i).EST_CAPA=lsEstadoCapac)THEN
           --registro correcto
             lnContExitos:=lnContExitos+1;
            INSERT INTO EDU_AUDIT_DETAL(
           PAIS_COD_PAIS,EMCO_COD_EMPR_COME, AUQU_COD_AUDI_QUER, CAM_PROC, NUM_ITEM,
               COD_CLIE, COD_CURS_CAPA, EST_REGI_PROC, USU_DIGI, FEC_DIGI,EST_REGI,
            ULT_CAMP_PROG_DICT,EST_CAPA)
           VALUES(psCodigoPais,psCodigoEmpresa,lsSql,tablaRegistro(i).CAM_ULTI_CALI_APTA,lnNumItem,
              tablaRegistro(i).CLIE_COD_CLIE,tablaRegistro(i).CCAP_COD_CURS_CAPA,lsEstadoOk,
              psUsuario,SYSDATE,lsEstadoActivo,tablaRegistro(i).ULT_CAMP_PROG_DICT,tablaRegistro(i).EST_CAPA);
          ELSE
            --registro con error
            lnContErrors:=lnContErrors+1;
             INSERT INTO EDU_AUDIT_DETAL(
           PAIS_COD_PAIS,EMCO_COD_EMPR_COME, AUQU_COD_AUDI_QUER, CAM_PROC, NUM_ITEM,
               COD_CLIE, COD_CURS_CAPA, EST_REGI_PROC, USU_DIGI, FEC_DIGI,EST_REGI,
            ULT_CAMP_PROG_DICT,EST_CAPA)
           VALUES(psCodigoPais,psCodigoEmpresa,lsSql,tablaRegistro(i).CAM_ULTI_CALI_APTA,lnNumItem,
              tablaRegistro(i).CLIE_COD_CLIE,tablaRegistro(i).CCAP_COD_CURS_CAPA,lsEstadoNOk,
              psUsuario,SYSDATE,lsEstadoActivo,tablaRegistro(i).ULT_CAMP_PROG_DICT,tablaRegistro(i).EST_CAPA);

          END IF;
        END IF;
      END IF;--if del primer registro
         END LOOP;
       END IF;
    EXIT WHEN cValidEstCons%NOTFOUND;
    END LOOP;
    CLOSE cValidEstCons;

   --UPDATE AUDITORIA CABECERA

  UPDATE EDU_AUDIT_CABEC A
  SET A.NUM_REGI_CORR=lnContExitos,
      A.NUM_REGI_ERRO=lnContErrors,
   A.NUM_REGI_PROC=lnContExitos+lnContErrors,
   A.EST_PROC=DECODE(lnContErrors,0,lsEstadoOk,lsEstadoNOk),
   A.USU_MODI=psUsuario,
   A.FEC_MODI=SYSDATE
  WHERE A.PAIS_COD_PAIS=psCodigoPais
      AND A.EMCO_COD_EMPR_COME=psCodigoEmpresa
   AND A.AUQU_COD_AUDI_QUER=lsSql;
   --AND A.CAM_PROC=psCampanha;

 RETURN;
EXCEPTION
WHEN OTHERS THEN
      ls_sqlerrm := substr(sqlerrm,1,250);
      RAISE_APPLICATION_ERROR(-20123, 'EDU_PR_VALID_ESTAD_CONSU: '||ls_sqlerrm);

END EDU_PR_VALID_ESTAD_CONSU;


/***************************************************************************
Descripcion       : Funcion que devuelve Codigo de Mensaje en base a los
                    parametros ingresados
Fecha Creacion    : 17/10/2007
Autor             : Carlos Bazalar
Parametros:
          psCodigoPais  : Codigo de Pais
          psCodEmpresa  : Codigo de Empresa
          psIndicadorEqui: 1: Obtener codigo de equivalencia de Mensaje
                           0: Obtener codigo de mensaje
          psTipoMensaje:   1: Mensaje General
                           2: Mensaje Especifico
          psCodCurso: Codigo de curso
          psEstadoCapa: Estado de Capacitacion
          pnOpcionCapa: Opcion de Capaciotacion (Nivel)
          psCodClien:   Codigo de Cliente
***************************************************************************/
   FUNCTION edu_fn_devue_codig_mensa (
      pscodigopais      VARCHAR2,
      pscodempresa      VARCHAR2,
      psindicadorequi   VARCHAR2,
      pstipomensaje     VARCHAR2,
      pscodcurso        VARCHAR2,
      psestadocapa      VARCHAR2,
      pnopcioncapa      NUMBER,
      pscodclien        VARCHAR2
)
RETURN VARCHAR2
IS
      lscodmensaje       VARCHAR2 (20);
      lsnivelalcanzado   edu_histo_capac_cabec.niv_capa_alca%TYPE;
	  lsIndicadorCursoCosto VARCHAR2(1);
   BEGIN
      /*Se obtine el indicador de curso que posee en el aptas la consultora en mencion*/

BEGIN
		 	 SELECT A.IND_CURS_COST INTO lsIndicadorCursoCosto
			 FROM EDU_HISTO_APTAS A
			 WHERE A.PAIS_COD_PAIS = pscodigopais
			  AND A.EMCO_COD_EMPR_COME = pscodempresa
			  AND A.CCAP_COD_CURS_CAPA = pscodcurso
			  AND A.CLIE_COD_CLIE =	pscodclien;
		 EXCEPTION
		  WHEN OTHERS THEN
		    lsIndicadorCursoCosto:='';
		 END;


 /* En caso se devuelva el codigo de Mensaje de la tabla EDU_PARAM_MENSA */
      IF psindicadorequi = indicador_equivalencia_no
      THEN
         IF pstipomensaje = '1'---Mensaje general
         THEN
            SELECT a.niv_capa_alca
              INTO lsnivelalcanzado
              FROM edu_histo_capac_cabec a
             WHERE a.pais_cod_pais = pscodigopais
               AND a.emco_cod_empr_come = pscodempresa
               AND a.clie_cod_clie = pscodclien
               AND a.est_regi = indicador_activo;

            SELECT ms.cod_mens
              INTO lscodmensaje
              FROM edu_param_mensa ms
             WHERE ms.pais_cod_pais = pscodigopais
               AND ms.emco_cod_empr_come = pscodempresa
               AND ms.ccap_cod_curs_capa = lsnivelalcanzado
               AND ms.tip_mens = pstipomensaje
               AND ms.est_mens = 'A'
			   AND ms.IND_CURS_COST = lsIndicadorCursoCosto
               AND ms.est_regi = indicador_activo;
    ELSE
            IF psestadocapa <> indicador_capacitada
            THEN
               SELECT ms.cod_mens
                 INTO lscodmensaje
                 FROM edu_param_mensa ms
                WHERE ms.pais_cod_pais = pscodigopais
                  AND ms.emco_cod_empr_come = pscodempresa
                  AND ms.ccap_cod_curs_capa = pscodcurso
                  AND ms.esca_cod_esta_capa = psestadocapa
                  AND ms.opc_capa = pnopcioncapa
                  AND ms.tip_mens = pstipomensaje
                  AND ms.est_mens = 'A'
				  AND ms.IND_CURS_COST = lsIndicadorCursoCosto
                  AND ms.est_regi = indicador_activo;
       ELSE
               SELECT ms.cod_mens
                 INTO lscodmensaje
                 FROM edu_param_mensa ms
                WHERE ms.pais_cod_pais = pscodigopais
                  AND ms.emco_cod_empr_come = pscodempresa
                  AND ms.ccap_cod_curs_capa = pscodcurso
                  AND ms.esca_cod_esta_capa = psestadocapa
                  AND ms.tip_mens = pstipomensaje
                  AND ms.est_mens = 'A'
  				  AND ms.IND_CURS_COST = lsIndicadorCursoCosto
                  AND ms.est_regi = indicador_activo;
       END IF;
    END IF;
 ELSE
 /* En caso se devuelva el codigo de Mensaje de la tabla de Equivalencia de Mensajes */
         IF pstipomensaje = '1'
         THEN
            SELECT a.niv_capa_alca
              INTO lsnivelalcanzado
              FROM edu_histo_capac_cabec a
             WHERE a.pais_cod_pais = pscodigopais
               AND a.emco_cod_empr_come = pscodempresa
               AND a.clie_cod_clie = pscodclien
               AND a.est_regi = indicador_activo;

            SELECT a.cod_mens_equi
              INTO lscodmensaje
              FROM edu_mensa_equiv a
             WHERE a.pais_cod_pais = pscodigopais
               AND a.emco_cod_empr_come = pscodempresa
               AND a.tip_mens = pstipomensaje
               AND a.ccap_cod_curs_capa = lsnivelalcanzado
			   AND a.EST_REGI = indicador_activo
			   AND a.IND_CURS_COST = lsIndicadorCursoCosto
			   and a.EST_MENS = 'A';
   ELSE
            IF psestadocapa <> indicador_capacitada
            THEN
               SELECT a.cod_mens_equi
                 INTO lscodmensaje
                 FROM edu_mensa_equiv a
                WHERE a.pais_cod_pais = pscodigopais
                  AND a.emco_cod_empr_come = pscodempresa
                  AND a.tip_mens = pstipomensaje
                  AND a.ccap_cod_curs_capa = pscodcurso
                  AND a.esca_cod_esta_capa = indicador_pendiente
                  AND a.opc_capa = pnopcioncapa
				  AND a.EST_REGI=indicador_activo
  			      AND a.IND_CURS_COST = lsIndicadorCursoCosto
				  AND a.EST_MENS = 'A';
      ELSE
               SELECT a.cod_mens_equi
                 INTO lscodmensaje
                 FROM edu_mensa_equiv a
                WHERE a.pais_cod_pais = pscodigopais
                  AND a.emco_cod_empr_come = pscodempresa
                  AND a.tip_mens = pstipomensaje
                  AND a.ccap_cod_curs_capa = pscodcurso
                  AND a.esca_cod_esta_capa = indicador_capacitada
				  AND a.EST_REGI=indicador_activo
				  AND a.IND_CURS_COST = lsIndicadorCursoCosto
				  AND a.EST_MENS = 'A';

      END IF;
   END IF;
 END IF;

      RETURN lscodmensaje;
EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
     RETURN ' ';
      WHEN OTHERS
      THEN
     ln_sqlcode := SQLCODE;
         ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
         raise_application_error (-20123,
                                     'ERROR EDU_FN_DEVUE_CODIG_MENSA: '
                                  || ls_sqlerrm
                                 );
   END edu_fn_devue_codig_mensa;

/***************************************************************************
Descripcion       : Funcion que devuelve Codigo de Clasificacion Equivalencia
Fecha Creacion    : 17/10/2007
Autor             : Carlos Bazalar
Parametros:
          psCodigoPais  : Codigo de Pais
          psCodEmpresa  : Codigo de Empresa
          psCodClas     : Codigo de Clasificacion
***************************************************************************/
FUNCTION EDU_FN_DEVUE_CODIG_EQUIV_CLASI(
  psCodigoPais      VARCHAR2,
  psCodEmpresa      VARCHAR2,
  psCodClasi        VARCHAR2
)
RETURN VARCHAR2
IS
 lsRetorno  EDU_PARAM_CLASI_EQUIV.Cod_Clas_Equi%TYPE;
BEGIN
  SELECT A.COD_CLAS_EQUI
  INTO lsRetorno
  FROM EDU_PARAM_CLASI_EQUIV A
  WHERE A.PAIS_COD_PAIS = psCodigoPais
    AND A.EMCO_COD_EMPR_COME = psCodEmpresa
    AND A.COD_CLAS = psCodClasi;
  RETURN lsRetorno;
EXCEPTION
WHEN NO_DATA_FOUND THEN
     RETURN NULL;
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_FN_DEVUE_CODIG_EQUIV_CLASI: '||ls_sqlerrm);
END EDU_FN_DEVUE_CODIG_EQUIV_CLASI;

/***************************************************************************
Descripcion       : Funcion que devuelve Tipo de Clasificacion Equivalencia
Fecha Creacion    : 17/10/2007
Autor             : Carlos Bazalar
Parametros:
          psCodigoPais  : Codigo de Pais
          psCodEmpresa  : Codigo de Empresa
          psCodClas     : Codigo de Clasificacion
***************************************************************************/
FUNCTION EDU_FN_DEVUE_TIPO_EQUIV_CLASI(
  psCodigoPais      VARCHAR2,
  psCodEmpresa      VARCHAR2,
  psCodClasi        VARCHAR2
)
RETURN VARCHAR2
IS
 lsRetorno  EDU_PARAM_CLASI_EQUIV.TIP_CLAS_EQUI%TYPE;
BEGIN
  SELECT A.TIP_CLAS_EQUI
  INTO lsRetorno
  FROM EDU_PARAM_CLASI_EQUIV A
  WHERE A.PAIS_COD_PAIS = psCodigoPais
    AND A.EMCO_COD_EMPR_COME = psCodEmpresa
    AND A.COD_CLAS = psCodClasi;
  RETURN lsRetorno;
EXCEPTION
WHEN NO_DATA_FOUND THEN
     RETURN NULL;
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_FN_DEVUE_TIPO_EQUIV_CLASI: '||ls_sqlerrm);
END EDU_FN_DEVUE_TIPO_EQUIV_CLASI;


/***************************************************************************
Descripcion       : Funcion que devuelve el Nombre de la Consultora
                    en base al indicador de Nombre completo
Fecha Creacion    : 25/01/2008
Autor             : Carlos Bazalar
Parametros:
          psCodigoPais  : Codigo de Pais
          psCodEmpresa  : Codigo de Empresa
          psCodClien    : Codigo de Cliente
          psIndicadorNombreCompleto: Indicador de Nombre Completo
***************************************************************************/
FUNCTION EDU_FN_DEVUE_NOMBR_CONSU(
  psCodigoPais      VARCHAR2,
  psCodEmpresa      VARCHAR2,
  psCodCliente      VARCHAR2,
  psIndicadorNombreCompleto   VARCHAR2
)
RETURN VARCHAR2
IS
  lsNombre  VARCHAR2(200);
BEGIN
 IF psIndicadorNombreCompleto  = INDICADOR_NO THEN
     SELECT TRIM(NVL(A.PRI_NOMB, ' ') || ' ' || NVL(A.SEG_NOMB, ' ') || ' ' || NVL(A.APE_PATE,' ') || ' ' || NVL(A.APE_MATE, ' '))
     INTO lsNombre
     FROM EDU_MAEST_CLIEN A
     WHERE A.PAIS_COD_PAIS = psCodigoPais
       AND A.EMCO_COD_EMPR_COME = psCodEmpresa
       AND A.COD_CLIE = psCodCliente
       AND A.EST_REGI = INDICADOR_ACTIVO;
 ELSE
     SELECT TRIM(NVL(A.APE_NOMB_COMP, ' '))
     INTO lsNombre
     FROM EDU_MAEST_CLIEN A
     WHERE A.PAIS_COD_PAIS = psCodigoPais
       AND A.EMCO_COD_EMPR_COME = psCodEmpresa
       AND A.COD_CLIE = psCodCliente
       AND A.EST_REGI = INDICADOR_ACTIVO;
 END IF;
 RETURN lsNombre;

EXCEPTION
WHEN NO_DATA_FOUND THEN
     RETURN ' ';
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_FN_DEVUE_NOMBR_CONSU: '||ls_sqlerrm);
END EDU_FN_DEVUE_NOMBR_CONSU;


/***************************************************************************
Descripcion       : Funcion que devuelve Nivel Actual de la consultora
Fecha Creacion    : 29/01/2008
Autor             : Carlos Bazalar
Parametros:
          psCodigoPais  : Codigo de Pais
          psCodEmpresa  : Codigo de Empresa
          psCodClien    : Codigo de Cliente
***************************************************************************/
FUNCTION EDU_FN_DEVUE_NIVEL_ACTUA_CONSU(
  psCodigoPais      VARCHAR2,
  psCodEmpresa      VARCHAR2,
  psCodClien        VARCHAR2
)
RETURN VARCHAR2
IS
  lsNivel                   VARCHAR2(3);
BEGIN
  SELECT A.NIV_CAPA_ALCA
  INTO lsNivel
  FROM EDU_HISTO_CAPAC_CABEC A
  WHERE A.PAIS_COD_PAIS = psCodigoPais
    AND A.EMCO_COD_EMPR_COME = psCodEmpresa
    AND A.CLIE_COD_CLIE = psCodClien;
  RETURN lsNivel;
EXCEPTION
WHEN NO_DATA_FOUND THEN
     RETURN '';
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_FN_DEVUE_NIVEL_ACTUA_CONSU: '||ls_sqlerrm);
END EDU_FN_DEVUE_NIVEL_ACTUA_CONSU;




/***************************************************************************
Descripcion       : Funcion que devuelve Nivel Siguiente
Fecha Creacion    : 29/01/2008
Autor             : Carlos Bazalar
Fecha Modificacion : 05/08/2008
Modificadi 		   : Sergio Buchelli
Parametros:
          psCodigoPais  : Codigo de Pais
          psCodEmpresa  : Codigo de Empresa
          psCodPrograma : Codigo de Programa
          psCodCurso    : Codigo de Curso
***************************************************************************/
   FUNCTION edu_fn_devue_nivel_sigte (
      pscodigopais    VARCHAR2,
      pscodempresa    VARCHAR2,
      pscodprograma   VARCHAR2,
      pscodcurso      VARCHAR2
)
RETURN VARCHAR2
IS
      lsnivel              VARCHAR2 (3);
      lsincrementocodigo   VARCHAR2 (3);
	  lnCont			   NUMBER;
BEGIN
      SELECT a.val_incr_codi
        INTO lsincrementocodigo
        FROM edu_param_progr_capac a
       WHERE a.pais_cod_pais = pscodigopais
         AND a.emco_cod_empr_come = pscodempresa
         AND a.cod_prog_capa = pscodprograma;

      lsnivel :=
              TO_CHAR (TO_NUMBER (pscodcurso) + TO_NUMBER (lsincrementocodigo));

	  --sb SE PREGUNTA SI EXISTE EL NIVEL
	  SELECT COUNT(1) INTO lnCont
       FROM edu_param_curso_capac a
       WHERE a.pais_cod_pais = pscodigopais
         AND a.emco_cod_empr_come = pscodempresa
		 AND a.COD_CURS_CAPA = lsnivel
		 AND a.EST_REGI='1';
	  -- si no existe ya no hay mas nivel por alcanzar
	   IF (lnCont = 0) THEN
       	  lsnivel := '';
       END IF;

      RETURN lsnivel;
EXCEPTION
      WHEN OTHERS
      THEN
     ln_sqlcode := SQLCODE;
         ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
         raise_application_error (-20123,
                                     'ERROR EDU_FN_DEVUE_NIVEL_SIGTE: '
                                  || ls_sqlerrm
                                 );
   END edu_fn_devue_nivel_sigte;

/***************************************************************************
Descripcion       : Proceso que se encarga de validar que consultoras no se le ha registrado asitencia

Fecha Creacion    : 02/02/2008
Autor             : Sergio Buchelli Silva
Parametros:
          psCodigoPais    : Codigo de Pais
          psCodigoEmpresa : Codigo de Empresa
          psCampanha   : Campanha Inicial
    psSentencia      :Setencia,
          psUsuario       : Usuario de Proceso
Parametros Salida   : psMensajeError: Mensaje de Error
***************************************************************************/
   PROCEDURE edu_pr_valid_regis_asist (
      pscodigopais            VARCHAR2,
      pscodigoempresa         VARCHAR2,
      pssentencia             VARCHAR2,
      pscampanha              VARCHAR2,
      psusuario               VARCHAR2,
      psmensajeerror    OUT   VARCHAR2
   )
   IS
     ls_sqlerrm         VARCHAR2(150);
      lssql              VARCHAR2 (6)   := 'SQL005';
      lsestadoactivo     VARCHAR2 (1)   := '1';
      lnconterrors       NUMBER         := 0;
      lncontexitos       NUMBER         := 0;
      lnnumregisproc     NUMBER         := 0;
      lnnumregistros     NUMBER         := 0;
      lscampactual       VARCHAR2 (6);
      lnnumitem          NUMBER         := 0;
      lsestadook         VARCHAR2 (1)   := '1';
      lsestadonok        VARCHAR2 (1)   := '0';
      lsobservacionok    VARCHAR2 (50)  := 'Aplica Validación';
      lsobservacionnok   VARCHAR2 (70)
         := 'No Aplica Validación para campaña menores o mayores de la Actual';

      TYPE treghistoaptas IS RECORD (
         clie_cod_clie        edu_histo_aptas.clie_cod_clie%TYPE,
         ccap_cod_curs_capa   edu_histo_aptas.ccap_cod_curs_capa%TYPE,
         est_capa             edu_histo_aptas.est_capa%TYPE,
         cod_plan_prog        edu_histo_aptas.cod_plan_prog%TYPE,
         cam_proc             edu_histo_aptas.cam_capa%TYPE
      );

      TYPE tabla_maestra IS TABLE OF treghistoaptas;

      tablaregistro      tabla_maestra;

      TYPE tvalidprog IS REF CURSOR;

      cvalidprog         tvalidprog;
BEGIN
      --Se valida que el registro de nose encuentre ya en la tabla de auditoria
      SELECT COUNT (1)
        INTO lnnumregistros
        FROM edu_audit_cabec a
       WHERE a.pais_cod_pais = pscodigopais
         AND a.emco_cod_empr_come = pscodigoempresa
         AND a.auqu_cod_audi_quer = lssql;

 --  AND A.CAM_PROC=psCampanha;
  --se borran los detalles
      DELETE FROM edu_audit_detal
            WHERE pais_cod_pais = pscodigopais
              AND emco_cod_empr_come = pscodigoempresa
              AND auqu_cod_audi_quer = lssql;

  -- AND CAM_PROC=psCampanha;
      IF (lnnumregistros = 0)
      THEN
    --INSERT EN LA TABLA EDU_AUDIT_CABEC
         INSERT INTO edu_audit_cabec
                     (pais_cod_pais, emco_cod_empr_come, auqu_cod_audi_quer,
                      usu_digi, fec_digi, est_regi, obs_proc,
                      est_proc
                     )
              VALUES (pscodigopais, pscodigoempresa, lssql,
                      psusuario, SYSDATE, lsestadoactivo, lsobservacionok,
                      lsestadook
                     );
  END IF;

    --Cantidad de Registros a procesar
      OPEN cvalidprog FOR pssentencia USING pscodigopais, pscodigoempresa;

      LOOP
         FETCH cvalidprog
         BULK COLLECT INTO tablaregistro;

         IF tablaregistro.COUNT > 0
         THEN
            FOR i IN tablaregistro.FIRST .. tablaregistro.LAST
     LOOP
               lnnumitem := lnnumitem + 1;
               lnconterrors := lnconterrors + 1;

               INSERT INTO edu_audit_detal
                           (pais_cod_pais, emco_cod_empr_come,
                            auqu_cod_audi_quer, cam_proc, num_item,
                            cod_clie,
                            cod_curs_capa,
                            est_regi_proc, usu_digi, fec_digi, est_regi,
                            est_capa,
                            cod_plan_prog
                           )
                    VALUES (pscodigopais, pscodigoempresa,
                            lssql, tablaregistro (i).cam_proc, lnnumitem,
                            tablaregistro (i).clie_cod_clie,
                            tablaregistro (i).ccap_cod_curs_capa,
                            lsestadonok, psusuario, SYSDATE, lsestadoactivo,
                            tablaregistro (i).est_capa,
                            tablaregistro (i).cod_plan_prog
                           );
        END LOOP;
       END IF;

         EXIT WHEN cvalidprog%NOTFOUND;
    END LOOP;

      CLOSE cvalidprog;

   --UPDATE AUDITORIA CABECERA
      UPDATE edu_audit_cabec a
         SET a.num_regi_corr = lncontexitos,
             a.num_regi_erro = lnconterrors,
             a.num_regi_proc = lncontexitos + lnconterrors,
             a.est_proc = DECODE (lnconterrors, 0, lsestadook, lsestadonok),
             a.usu_modi = psusuario,
             a.fec_modi = SYSDATE
       WHERE a.pais_cod_pais = pscodigopais
         AND a.emco_cod_empr_come = pscodigoempresa
         AND a.auqu_cod_audi_quer = lssql;

  -- AND A.CAM_PROC=psCampanha;
 RETURN;
EXCEPTION
      WHEN OTHERS
      THEN
         ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
         raise_application_error (-20123,
                                  'EDU_PR_VALID_REGIS_ASIST: ' || ls_sqlerrm
                                 );
   END edu_pr_valid_regis_asist;

/***************************************************************************
Descripcion       : Funcion que devuelve Codigo de Clasificacion en base a los
                    parametros ingresados
Fecha Creacion    : 06/03/2008
Autor             : Carlos Bazalar
Parametros:
          psCodigoPais  : Codigo de Pais
          psCodEmpresa  : Codigo de Empresa
          psCodCurso    : Codigo de curso
          psTipoClasi   : Tipo de Clasificacion (I: Invitacion, B: Beneficio)
          psTipoCurso   : Tipo de Curso (S: Sin costo, C: Con costo)
***************************************************************************/
   FUNCTION edu_fn_devue_codig_clasi (
      pscodigopais   VARCHAR2,
      pscodempresa   VARCHAR2,
      pscodcurso     VARCHAR2,
      pstipoclasi    VARCHAR2,
      pstipocurso    VARCHAR2
)
RETURN VARCHAR2
IS
      lscodclasificacion   edu_param_clasi_benef_capac.cod_clas%TYPE;
BEGIN
      SELECT t.cod_clas
        INTO lscodclasificacion
        FROM edu_param_clasi_benef_capac t
       WHERE t.pais_cod_pais = pscodigopais
         AND t.emco_cod_empr_come = pscodempresa
         AND t.niv_capa_alca = pscodcurso
         AND t.tip_clas = pstipoclasi
         AND t.tip_curs = pstipocurso
         AND t.est_clas = 'A'
         AND t.est_regi != 9;

      RETURN lscodclasificacion;
EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
     RETURN ' ';
      WHEN OTHERS
      THEN
     ln_sqlcode := SQLCODE;
         ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
         raise_application_error (-20123,
                                     'ERROR EDU_FN_DEVUE_CODIG_CLASI: '
                                  || ls_sqlerrm
                                 );
   END edu_fn_devue_codig_clasi;

/***************************************************************************************************
   Descripcion       : Procedimiento que genera en una tabla temporal el resumen de programadas en planillaa
                       las consultoras
  Fecha Creacion      : 05/03/2008
  Parametros Entrada  : psCodigoPais  : Codigo del Pais del Usuario Logueado (ejm: PE es de PERU)
                      : psCodEmpresa  : Código de Empresa del Usuario Logeado
                      : psCampanha    : Campanha de Proceso
                      : psCodigoRegion: Código de Region
  Autor               : Sergio Buchelli Silva  - sbuchelli@belcorp.biz
  Version             : Final (Beta|Final)
  Cambios Importantes :
  ****************************************************************************************************/
   PROCEDURE edu_pr_gener_resum_progr_plani (
      pscodigopais      VARCHAR2,
      pscodigoempresa   VARCHAR2,
      pscampanha        VARCHAR2,
      pscodigoregion    VARCHAR2,
      pscodigozona      VARCHAR2
   )
IS
      vchcodigozona     edu_maest_clien.cod_zona%TYPE;
 i NUMBER;
      lnnuminvitacion   NUMBER;
      lnnumreg          NUMBER                          := 0;
      lnrow             NUMBER                          := 0;

      TYPE tregtemporal IS RECORD (
         cod_curs_capa   edu_param_curso_capac.cod_curs_capa%TYPE,
         nom_curs_capa   edu_param_curso_capac.nom_curs_capa%TYPE,
         num_invi        edu_param_curso_capac.num_invi%TYPE
   );

      TYPE tabla_temporal IS TABLE OF tregtemporal;

      tablaregistro     tabla_temporal;
      regregistro       tregtemporal;

      CURSOR cursorregistro
  IS
         SELECT   x.cod_curs_capa, x.nom_curs_capa, x.num_invi
             FROM edu_param_curso_capac x
            WHERE x.pais_cod_pais = pscodigopais
              AND x.emco_cod_empr_come = pscodigoempresa
			  AND x.EST_REGI = '1'
    ORDER BY 1;
BEGIN
--Numerod e registros
      SELECT COUNT (1)
        INTO lnnumreg
        FROM edu_plani_progr_curso a,
             edu_maest_clien b,
             edu_zona c,
             edu_param_curso_capac d
       WHERE a.pais_cod_pais = b.pais_cod_pais
         AND a.emco_cod_empr_come = b.emco_cod_empr_come
         AND a.clie_cod_clie = b.cod_clie
         AND b.pais_cod_pais = c.pais_cod_pais
         AND b.emco_cod_empr_come = c.emco_cod_empr_come
         AND b.cod_regi = c.regi_cod_regi
         AND b.cod_zona = c.cod_zona
         AND c.pais_cod_pais = d.pais_cod_pais
         AND c.emco_cod_empr_come = d.emco_cod_empr_come
         AND a.ccap_cod_curs_capa = d.cod_curs_capa
         AND a.pais_cod_pais = pscodigopais
         AND a.emco_cod_empr_come = pscodigoempresa
         AND a.cam_proc = pscampanha
         AND b.cod_regi = pscodigoregion
         AND A.EST_REGI != '9'
	 AND A.COD_PLAN_PROG != '9999999999';--sacamos del listado a las planillas ficticias

--Se obtine la Zona
      vchcodigozona := pscodigozona;

      IF (vchcodigozona IS NULL OR vchcodigozona = '')
      THEN
         SELECT   NVL (MIN (b.cod_zona), '')
             INTO vchcodigozona
             FROM edu_plani_progr_curso a,
                  edu_maest_clien b,
                  edu_zona c,
                  edu_param_curso_capac d
            WHERE a.pais_cod_pais = b.pais_cod_pais
              AND a.emco_cod_empr_come = b.emco_cod_empr_come
              AND a.clie_cod_clie = b.cod_clie
              AND b.pais_cod_pais = c.pais_cod_pais
              AND b.emco_cod_empr_come = c.emco_cod_empr_come
              AND b.cod_regi = c.regi_cod_regi
              AND b.cod_zona = c.cod_zona
              AND c.pais_cod_pais = d.pais_cod_pais
              AND c.emco_cod_empr_come = d.emco_cod_empr_come
              AND a.ccap_cod_curs_capa = d.cod_curs_capa
              AND a.pais_cod_pais = pscodigopais
              AND a.emco_cod_empr_come = pscodigoempresa
              AND a.cam_proc = pscampanha
              AND b.cod_regi = pscodigoregion
              AND A.EST_REGI != '9'
	      AND A.COD_PLAN_PROG != '9999999999'--sacamos del listado a las planillas ficticias
         ORDER BY b.cod_zona;
 END IF;

--Borramos los registros en la tabla temporar
      DELETE FROM edu_tmp_resum_progr_plani a
            WHERE a.cod_pais = pscodigopais
              AND a.cod_empr_come = pscodigoempresa
              AND a.cam_proc = pscampanha
              AND a.cod_regi = pscodigoregion;

   --INSERTAMOS LA DATA VALIDA
      IF (lnnumreg <> 0)
      THEN
         INSERT INTO edu_tmp_resum_progr_plani
                     (cod_pais, cod_empr_come, cam_proc, cod_regi, cod_zona,
                      cod_curs_capa, nom_curs_capa, cod_clie,
                      num_invi, fla_nume_invi)
            SELECT   pscodigopais, pscodigoempresa, pscampanha,
                     pscodigoregion, b.cod_zona, a.ccap_cod_curs_capa,
                     d.nom_curs_capa, a.clie_cod_clie,
					 CASE WHEN (d.IND_COST_CURS = 'S' AND A.NUM_INVI > d.NUM_INVI) THEN
					   TRIM(TO_CHAR(d.NUM_INVI))
 					 ELSE
	 				   TRIM(TO_CHAR(A.NUM_INVI))
					 END AS NUM_INVI,
					 '1'
                FROM edu_plani_progr_curso a,
                     edu_maest_clien b,
                     edu_zona c,
                     edu_param_curso_capac d
               WHERE a.pais_cod_pais = b.pais_cod_pais
                 AND a.emco_cod_empr_come = b.emco_cod_empr_come
                 AND a.clie_cod_clie = b.cod_clie
                 AND b.pais_cod_pais = c.pais_cod_pais
                 AND b.emco_cod_empr_come = c.emco_cod_empr_come
                 AND b.cod_regi = c.regi_cod_regi
                 AND b.cod_zona = c.cod_zona
                 AND c.pais_cod_pais = d.pais_cod_pais
                 AND c.emco_cod_empr_come = d.emco_cod_empr_come
                 AND a.ccap_cod_curs_capa = d.cod_curs_capa
                 AND a.pais_cod_pais = pscodigopais
                 AND a.emco_cod_empr_come = pscodigoempresa
                 AND a.cam_proc = pscampanha
                 AND b.cod_regi = pscodigoregion
                 AND A.EST_REGI != '9'
		 AND A.COD_PLAN_PROG != '9999999999'--sacamos del listado a las planillas ficticias
            ORDER BY b.cod_zona, a.ccap_cod_curs_capa, a.num_invi;
   END IF;

      IF (vchcodigozona IS NOT NULL AND LENGTH (vchcodigozona) > 0)
      THEN
         OPEN cursorregistro;

         LOOP
            FETCH cursorregistro
            BULK COLLECT INTO tablaregistro LIMIT w_filas;

            IF tablaregistro.COUNT > 0
            THEN
               FOR x IN tablaregistro.FIRST .. tablaregistro.LAST
    LOOP
                  regregistro := tablaregistro (x);
        --Obtenemos el numero de Invitacion
                  lnnuminvitacion :=
                     edu_fn_devue_numer_invit (pscodigopais,
                                               pscodigoempresa,
                                               regregistro.cod_curs_capa
                                              );

              --por cada invitacion se carga un registro
                  FOR i IN 1 .. lnnuminvitacion
                  LOOP
                     SELECT COUNT (1)
                       INTO lnrow
                       FROM edu_tmp_resum_progr_plani
                      WHERE cod_pais = pscodigopais
                        AND cod_empr_come = pscodigoempresa
                        AND cam_proc = pscampanha
                        AND cod_regi = pscodigoregion
                        AND (pscodigozona IS NULL OR cod_zona = pscodigozona
                            )
                        AND cod_curs_capa = regregistro.cod_curs_capa
                        AND num_invi = i;

                     IF (lnrow = 0)
                     THEN                                          --no existe
                        INSERT INTO edu_tmp_resum_progr_plani
                                    (cod_pais, cod_empr_come,
                                     cam_proc, cod_regi,
                                     cod_zona,
                                     cod_curs_capa,
                                     nom_curs_capa, cod_clie, num_invi,
                                     fla_nume_invi
                                    )
                             VALUES (pscodigopais, pscodigoempresa,
                                     pscampanha, pscodigoregion,
                                     vchcodigozona,
                                     regregistro.cod_curs_capa,
                                     regregistro.nom_curs_capa, '99999', i,
                                     '0'
                                    );   --REGISTRO Q SOLO SIRVE PARA RELLENAR
            --DINAMICAMNETE LAS OPCIONES EN EL CROSSTAB
        END IF;
             END LOOP;
           END LOOP;
        END IF;

            EXIT WHEN cursorregistro%NOTFOUND;
     END LOOP;

         CLOSE cursorregistro;
 END IF;
EXCEPTION
      WHEN OTHERS
      THEN
         ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
         raise_application_error (-20123,
                                     'EDU_PR_GENER_RESUM_PROGR_PLANI: '
                                  || ls_sqlerrm
                                 );
   END edu_pr_gener_resum_progr_plani;

/***************************************************************************************************
 Descripcion       : Procedimiento que registra Planillas no procesadas
 Fecha Creacion      : 14/03/2008
 Parametros Entrada  : psCodPais       : Codigo del Pais del Usuario
                     : psCodEmpresa    : Código de Empresa del Usuario Logeado
                     : psCodPeriodo    : Campanha de Proceso
                     : psCodRegion     : Código de Region
 Parametro Salida     : psMensajeError   :Mensaje error
 Autor               : Carlos Bazalar
****************************************************************************************************/
 PROCEDURE EDU_PR_REGIS_PLANI_NPROG (
      pscodpais              VARCHAR2,
      pscodempresa           VARCHAR2,
      pscodperiodo           VARCHAR2,
      pscodregion            VARCHAR2,
      psusuario              VARCHAR2,
      psmensajeerror   OUT   VARCHAR2
)
IS
      TYPE t_codplani IS TABLE OF edu_histo_plani_instr.cod_plan_prog%TYPE;

      TYPE t_codinstr IS TABLE OF edu_histo_plani_instr.inst_cod_inst%TYPE;

      TYPE t_codcurso IS TABLE OF edu_histo_plani_instr.ccap_cod_curs_capa%TYPE;

      TYPE t_codregio IS TABLE OF edu_histo_plani_instr.regi_cod_regi%TYPE;

      TYPE t_codzona IS TABLE OF edu_histo_plani_instr.zona_cod_zona%TYPE;

      TYPE t_codclien IS TABLE OF edu_plani_progr_curso.clie_cod_clie%TYPE;

      TYPE t_camingre IS TABLE OF edu_maest_clien.cam_ingr%TYPE;

      TYPE t_numinvi IS TABLE OF edu_histo_aptas.num_invi%TYPE;

      TYPE t_indcosto IS TABLE OF edu_histo_aptas.ind_curs_cost%TYPE;

      TYPE t_primcali IS TABLE OF edu_histo_aptas.cam_prim_cali_apta%TYPE;

      TYPE t_ulticali IS TABLE OF edu_histo_aptas.cam_ulti_cali_apta%TYPE;

      v_codplani          t_codplani;
      v_codinstr          t_codinstr;
      v_codcurso          t_codcurso;
      v_codregio          t_codregio;
      v_codzona           t_codzona;
      v_codclien          t_codclien;
      v_camingre          t_camingre;
      v_numinvi           t_numinvi;
      v_indcosto          t_indcosto;
      v_primcali          t_primcali;
      v_ulticali          t_ulticali;
      lscampaplanilla     VARCHAR2 (6);
      lsmensajeerror      VARCHAR2 (250);
      lscodcursodictado   VARCHAR2 (6);
      lscodlocal          edu_local.cod_loca%TYPE;
      lscodcurso          edu_histo_plani_instr.ccap_cod_curs_capa%TYPE;
      lscodinstr          edu_histo_plani_instr.inst_cod_inst%TYPE;

	  lsCodPlanillaInicial edu_histo_plani_instr.COD_PLAN_PROG%TYPE;
	  lsCodPlanillaFinal   edu_histo_plani_instr.COD_PLAN_PROG%TYPE;

      CURSOR cplanilla (vscodperiodoplani VARCHAR2)
      IS
         SELECT   a.ccap_cod_curs_capa, a.inst_cod_inst, a.cod_plan_prog,
                  a.regi_cod_regi, a.zona_cod_zona
             FROM edu_histo_plani_instr a,
                  (SELECT   *
                       FROM edu_contr_gener_plani a
                      WHERE a.peri_cod_peri = vscodperiodoplani
                        AND a.pais_cod_pais = pscodpais
 AND a.emco_cod_empr_come = NVL(pscodempresa,(select COD_EMPR_COME from EDU_EMPRE_COMER where PAIS_COD_PAIS = pscodpais))
                        AND a.regi_cod_regi = pscodregion
                   ORDER BY a.pais_cod_pais, TO_NUMBER (a.cod_plan_prog_inic)) b
            WHERE a.pais_cod_pais = pscodpais
 AND a.emco_cod_empr_come = NVL(pscodempresa,(select COD_EMPR_COME from EDU_EMPRE_COMER where PAIS_COD_PAIS = pscodpais))
              AND a.sit_plan_prog = 'A'
              AND a.pais_cod_pais = b.pais_cod_pais
              AND a.emco_cod_empr_come = b.emco_cod_empr_come
              AND a.ccap_cod_curs_capa = b.ccap_cod_curs_capa
              AND a.regi_cod_regi = b.regi_cod_regi
              AND TO_NUMBER (a.cod_plan_prog)
                     BETWEEN TO_NUMBER (b.cod_plan_prog_inic)
                         AND TO_NUMBER (b.cod_plan_prog_fina)
         ORDER BY a.ccap_cod_curs_capa, a.inst_cod_inst, a.cod_plan_prog;

      CURSOR cplanilladetalle (vscodcurso VARCHAR2, vscodplanilla VARCHAR2)
      IS
         SELECT a.clie_cod_clie, b.cam_ingr, c.num_invi, c.ind_curs_cost,
                c.cam_prim_cali_apta, c.cam_ulti_cali_apta
           FROM edu_plani_progr_curso a, edu_maest_clien b, edu_histo_aptas c
          WHERE a.pais_cod_pais = pscodpais
 AND a.emco_cod_empr_come = NVL(pscodempresa,(select COD_EMPR_COME from EDU_EMPRE_COMER where PAIS_COD_PAIS = pscodpais))
            AND a.ccap_cod_curs_capa = vscodcurso
            AND a.cod_plan_prog = vscodplanilla
            AND b.pais_cod_pais = a.pais_cod_pais
            AND b.emco_cod_empr_come = a.emco_cod_empr_come
            AND b.cod_clie = a.clie_cod_clie
            AND c.pais_cod_pais = a.pais_cod_pais
            AND c.emco_cod_empr_come = a.emco_cod_empr_come
            AND c.ccap_cod_curs_capa = a.ccap_cod_curs_capa
            AND c.clie_cod_clie = a.clie_cod_clie;

 vsCodEmpresa EDU_EMPRE_COMER.COD_EMPR_COME%TYPE;
BEGIN

 vsCodEmpresa := pscodempresa;

 if pscodempresa is null then
    select COD_EMPR_COME
    INTO vsCodEmpresa
    from EDU_EMPRE_COMER
    where PAIS_COD_PAIS = pscodpais;
 end if;

      /* obteniendo campaña previa*/
      lscodlocal := NULL;
      lscampaplanilla :=
               edu_pkg_proce_gener.edu_fn_devue_codig_perio (pscodperiodo,
                                                             -1);
      /* Recorriendo lista */
      lscodcurso := 'XXX';
      lscodinstr := 'XXX';
      lscodcursodictado := '-1';


	/* OBTENIENDO LAS PLANILLA INICIAL Y FINAL*/
	   BEGIN
	     SELECT   A.COD_PLAN_PROG_INIC , A.COD_PLAN_PROG_FINA
		INTO  lsCodPlanillaInicial , lsCodPlanillaFinal
             FROM EDU_CONTR_GENER_PLANI A
             WHERE A.PERI_COD_PERI = lscampaplanilla
                   AND A.PAIS_COD_PAIS = pscodpais
 AND A.EMCO_COD_EMPR_COME = vsCodEmpresa
    			   AND A.REGI_COD_REGI = pscodregion
             ORDER BY A.PAIS_COD_PAIS, TO_NUMBER (A.COD_PLAN_PROG_INIC);
         EXCEPTION
          WHEN OTHERS THEN
           NULL;
         END;


      OPEN cplanilla (lscampaplanilla);

  LOOP
         FETCH cplanilla
         BULK COLLECT INTO v_codcurso, v_codinstr, v_codplani, v_codregio,
                v_codzona LIMIT w_filas;

         IF v_codcurso.COUNT > 0
         THEN
            FOR x IN v_codcurso.FIRST .. v_codcurso.LAST
            LOOP
               IF (lscodcurso <> v_codcurso (x)
                   OR lscodinstr <> v_codinstr (x)
                  )
               THEN
                  lscodcurso := v_codcurso (x);
                  lscodinstr := v_codinstr (x);
                  lscodcursodictado :=
                     edu_fn_sigte_codig_curso_dicta (pscodpais,
 vsCodEmpresa,
                                                     lscodcurso
                                                    );

				/*OBTENIENDO EL LOCAL */

				/*	 BEGIN
					    SELECT NVL(A.COD_LOCA,'') INTO  lscodlocal
						FROM EDU_LOCAL A
						WHERE A.PAIS_COD_PAIS=pscodpais
						  AND A.EMCO_COD_EMPR_COME=pscodempresa
						  AND A.REGI_COD_REGI=v_codregio (x)
						  AND ROWNUM=1;
					  EXCEPTION
					    WHEN NO_DATA_FOUND THEN
						  lscodlocal:='';
					  END;
				 */
		 		--El local antes se obtenia , ahora se decidio ingresar null 06/10/08
				 lscodlocal:='';

              /* Insertando en cabecera Curso Dictado */
                  INSERT INTO edu_histo_curso_dicta_cabec
                              (pais_cod_pais, emco_cod_empr_come,
                               ccap_cod_curs_capa, cod_curs_dict,
                               inst_cod_inst, cam_inic_curs, fec_inic_curs,
                               num_real_dura_sesi, cod_regi, cod_zona,
                               lug_capa, cat_luga_capa, ind_eval_curs,
                               cal_prom_curs_dict, est_curs_dict, usu_digi,
                               fec_digi, est_regi, loca_cod_loca,
                               sala_cod_sala,SIST_COD_SIST
                              )
 VALUES (pscodpais, vsCodEmpresa,
                               lscodcurso, lscodcursodictado,
                               lscodinstr, pscodperiodo, TRUNC (SYSDATE),
                               1, v_codregio (x), v_codzona (x),
                               NULL, '',                       --EN VEZ DE LO
                                         'N',
                               NULL, 'C', psusuario,
                               SYSDATE, '1', lscodlocal,
                               NULL,'EDU'
                              );
           END IF;

           /* Recorriendo lista de Consultoras asociadas */
               OPEN cplanilladetalle (v_codcurso (x), v_codplani (x));

           LOOP
                  FETCH cplanilladetalle
                  BULK COLLECT INTO v_codclien, v_camingre, v_numinvi,
                         v_indcosto, v_primcali, v_ulticali LIMIT w_filas;

                  IF v_codclien.COUNT > 0
                  THEN
                /* Insertando en Detalle Curso Dictado */
                     FORALL y IN 1 .. v_codclien.COUNT
                        INSERT INTO edu_histo_curso_dicta_detal
                                    (pais_cod_pais, emco_cod_empr_come,
                                     ccap_cod_curs_capa, cdic_cod_curs_dict,
                                     clie_cod_clie, cod_plan_prog,
                                     ind_asis, cal_cons, cal_inst, usu_digi,
                                     fec_digi, est_regi
                                    )
 VALUES (pscodpais, vsCodEmpresa,
                                     v_codcurso (x), lscodcursodictado,
                                     v_codclien (y), v_codplani (x),
                                     indicador_no, 0.00, NULL, psusuario,
                                     SYSDATE, '1'
                                    );
                  END IF;

                  EXIT WHEN cplanilladetalle%NOTFOUND;
               END LOOP;

               CLOSE cplanilladetalle;

   -- AGREGAR ACTUALIZACON HISTORICO PLANILLA INSTRUCTORA, INIAL PLANILLAS - CONSULTORAS
   -- CURSO DICTADO
   -- SIT PLANILL PROCESADA
   -- AUDITORIA
               UPDATE edu_histo_plani_instr a
                  SET cdic_cod_curs_dict = lscodcursodictado,
                      sit_plan_prog = 'P',
                      fec_modi = SYSDATE,
                      usu_modi = psusuario
                WHERE a.pais_cod_pais = pscodpais
 AND a.emco_cod_empr_come = vsCodEmpresa
                  AND a.ccap_cod_curs_capa = v_codcurso (x)
                  AND a.cod_plan_prog = v_codplani (x);
           END LOOP;
         END IF;

         EXIT WHEN cplanilla%NOTFOUND;
  END LOOP;

      CLOSE cplanilla;


	  --cerrando los cursos vigentes correspondiente a LA REGION DADA

	  UPDATE EDU_HISTO_CURSO_DICTA_CABEC CB
	  SET  CB.EST_CURS_DICT='C'
	  WHERE CB.PAIS_COD_PAIS=pscodpais
	 		 AND CB.EMCO_COD_EMPR_COME=vsCodEmpresa
			 AND CB.COD_CURS_DICT IN(
			  	 select DISTINCT a.CDIC_COD_CURS_DICT
				 from EDU_HISTO_CURSO_DICTA_DETAL a, EDU_HISTO_CURSO_DICTA_CABEC B
				 where A.PAIS_COD_PAIS=pscodpais
				 	 AND A.EMCO_COD_EMPR_COME=vsCodEmpresa
						AND B.EST_CURS_DICT = 'V'
						AND a.COD_PLAN_PROG BETWEEN TO_NUMBER ( lsCodPlanillaInicial)
						AND TO_NUMBER (lsCodPlanillaFinal)
				 AND A.PAIS_COD_PAIS = B.PAIS_COD_PAIS
				 AND A.EMCO_COD_EMPR_COME = B.EMCO_COD_EMPR_COME
				 AND A.CDIC_COD_CURS_DICT = B.COD_CURS_DICT
				 AND A.CCAP_COD_CURS_CAPA =  CB.CCAP_COD_CURS_CAPA
				 AND A.CCAP_COD_CURS_CAPA = B.CCAP_COD_CURS_CAPA);

EXCEPTION
      WHEN OTHERS
      THEN
         ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
         raise_application_error (-20123,
                                  'EDU_PR_REGIS_PLANI_NPROG: ' || ls_sqlerrm
                                 );
   END edu_pr_regis_plani_nprog;

/***************************************************************************
Descripcion       : Funcion que devuelve el siguiente Codigo de Curso Dictado
Fecha Creacion    : 17/03/2008
Autor             : Carlos Bazalar
Parametros:
          psCodigoPais  : Codigo de Pais
          psCodEmpresa  : Codigo de Empresa
          psCodCurso    : Codigo de curso
***************************************************************************/
   FUNCTION edu_fn_sigte_codig_curso_dicta (
      pscodigopais   VARCHAR2,
      pscodempresa   VARCHAR2,
      pscodcurso     VARCHAR2
)
RETURN NUMBER
IS
      lndevuelve   NUMBER;
BEGIN

	  SELECT NVL(MAX(TO_NUMBER(COD_CURS_DICT)),'0')
        INTO lndevuelve
        FROM edu_histo_curso_dicta_cabec
       WHERE pais_cod_pais = pscodigopais
         AND emco_cod_empr_come = pscodempresa
         AND ccap_cod_curs_capa = pscodcurso;

      lndevuelve := lndevuelve + 1;
      RETURN lndevuelve;
EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
     RETURN 1;
      WHEN OTHERS
      THEN
         ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
         raise_application_error (-20123,
                                     'EDU_FN_SIGTE_CODIG_CURSO_DICTA: '
                                  || ls_sqlerrm
                                 );
   END edu_fn_sigte_codig_curso_dicta;

/***************************************************************************
Descripcion       : Proceso que se encarga de validar que no existan pendientes de facturar

Fecha Creacion    : 24/03/2008
Autor             : Sergio Buchelli Silva
Parametros:
          psCodigoPais    : Codigo de Pais
          psCodigoEmpresa : Codigo de Empresa
          psCampanha   : Campanha Inicial
          psSentencia      :Setencia,
          psUsuario       : Usuario de Proceso
Parametros Salida   : psMensajeError: Mensaje de Error
***************************************************************************/
   PROCEDURE edu_pr_valid_estad_pendi_factu (
      pscodigopais            VARCHAR2,
      pscodigoempresa         VARCHAR2,
      pssentencia             VARCHAR2,
      pscampanha              VARCHAR2,
      psusuario               VARCHAR2,
      psmensajeerror    OUT   VARCHAR2
   )
   IS
     ls_sqlerrm         VARCHAR2(150);
      lssql              VARCHAR2 (6)   := 'SQL006';
      lsestadoactivo     VARCHAR2 (1)   := '1';
      lnconterrors       NUMBER         := 0;
      lncontexitos       NUMBER         := 0;
      lsestadocapac      VARCHAR (2)    := 'PF';
      lnnumregisproc     NUMBER         := 0;
      lnnumregistros     NUMBER         := 0;
      lscampactual       VARCHAR2 (6);
      lnnumitem          NUMBER         := 0;
      lsestadook         VARCHAR2 (1)   := '1';
      lsestadonok        VARCHAR2 (1)   := '0';
      lsobservacionok    VARCHAR2 (50)  := 'Aplica Validación';
      lsobservacionnok   VARCHAR2 (70)
         := 'No Aplica Validación para campaña menores o mayores de la Actual';

      TYPE treghistoaptas IS RECORD (
         clie_cod_clie        edu_histo_aptas.clie_cod_clie%TYPE,
         ccap_cod_curs_capa   edu_histo_aptas.ccap_cod_curs_capa%TYPE,
         est_capa             edu_histo_aptas.est_capa%TYPE,
         ult_camp_prog_dict   edu_histo_aptas.ult_camp_prog_dict%TYPE,
         cam_ulti_cali_apta   edu_histo_aptas.cam_ulti_cali_apta%TYPE
      );

      TYPE tabla_maestra IS TABLE OF treghistoaptas;

      tablaregistro      tabla_maestra;

      TYPE tvalidprog IS REF CURSOR;

      cvalidprog         tvalidprog;
BEGIN
      --Se valida que el registro de nose encuentre ya en la tabla de auditoria
      SELECT COUNT (1)
        INTO lnnumregistros
        FROM edu_audit_cabec a
       WHERE a.pais_cod_pais = pscodigopais
         AND a.emco_cod_empr_come = pscodigoempresa
         AND a.auqu_cod_audi_quer = lssql;

 --  AND A.CAM_PROC=psCampanha;
  --se borran los detalles
      DELETE FROM edu_audit_detal
            WHERE pais_cod_pais = pscodigopais
              AND emco_cod_empr_come = pscodigoempresa
              AND auqu_cod_audi_quer = lssql;

  -- AND CAM_PROC=psCampanha;
      IF (lnnumregistros = 0)
      THEN
    --INSERT EN LA TABLA EDU_AUDIT_CABEC
         INSERT INTO edu_audit_cabec
                     (pais_cod_pais, emco_cod_empr_come, auqu_cod_audi_quer,
                      usu_digi, fec_digi, est_regi, obs_proc,
                      est_proc
                     )
              VALUES (pscodigopais, pscodigoempresa, lssql,
                      psusuario, SYSDATE, lsestadoactivo, lsobservacionok,
                      lsestadook
                     );
  END IF;

    --Cantidad de Registros a procesar
      OPEN cvalidprog FOR pssentencia
      USING pscodigopais, pscodigoempresa, lsestadocapac;

      LOOP
         FETCH cvalidprog
         BULK COLLECT INTO tablaregistro;

         IF tablaregistro.COUNT > 0
         THEN
            FOR i IN tablaregistro.FIRST .. tablaregistro.LAST
     LOOP
               lnnumitem := lnnumitem + 1;
               lnconterrors := lnconterrors + 1;

               INSERT INTO edu_audit_detal
                           (pais_cod_pais, emco_cod_empr_come,
                            auqu_cod_audi_quer, cam_proc,
                            num_item, cod_clie,
                            cod_curs_capa,
                            est_regi_proc, usu_digi, fec_digi, est_regi,
                            ult_camp_prog_dict,
                            est_capa
                           )
                    VALUES (pscodigopais, pscodigoempresa,
                            lssql, tablaregistro (i).cam_ulti_cali_apta,
                            lnnumitem, tablaregistro (i).clie_cod_clie,
                            tablaregistro (i).ccap_cod_curs_capa,
                            lsestadonok, psusuario, SYSDATE, lsestadoactivo,
                            tablaregistro (i).ult_camp_prog_dict,
                            tablaregistro (i).est_capa
                           );
        END LOOP;
       END IF;

         EXIT WHEN cvalidprog%NOTFOUND;
    END LOOP;

      CLOSE cvalidprog;

   --UPDATE AUDITORIA CABECERA
      UPDATE edu_audit_cabec a
         SET a.num_regi_corr = lncontexitos,
             a.num_regi_erro = lnconterrors,
             a.num_regi_proc = lncontexitos + lnconterrors,
             a.est_proc = DECODE (lnconterrors, 0, lsestadook, lsestadonok),
             a.usu_modi = psusuario,
             a.fec_modi = SYSDATE
       WHERE a.pais_cod_pais = pscodigopais
         AND a.emco_cod_empr_come = pscodigoempresa
         AND a.auqu_cod_audi_quer = lssql;

  -- AND A.CAM_PROC=psCampanha;
 RETURN;
EXCEPTION
      WHEN OTHERS
      THEN
         ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
         raise_application_error (-20123,
                                     'EDU_PR_VALID_ESTAD_PENDI_FACTU: '
                                  || ls_sqlerrm
                                 );
   END edu_pr_valid_estad_pendi_factu;

/***************************************************************************
Descripcion       : Proceso que se encraga d evalidar que no exista pendiente de programar para
 	                  planillas que ya han sido generadas

Fecha Creacion    : 24/03/2008
Autor             : Sergio Buchelli Silva
Parametros:
          psCodigoPais    : Codigo de Pais
          psCodigoEmpresa : Codigo de Empresa
          psCampanha   : Campanha Inicial
          psSentencia      :Setencia,
          psUsuario       : Usuario de Proceso
Parametros Salida   : psMensajeError: Mensaje de Error
***************************************************************************/
   PROCEDURE edu_pr_valid_estad_pendi_proga (
      pscodigopais            VARCHAR2,
      pscodigoempresa         VARCHAR2,
      pssentencia             VARCHAR2,
      pscampanha              VARCHAR2,
      psusuario               VARCHAR2,
      psmensajeerror    OUT   VARCHAR2
   )
   IS
     ls_sqlerrm         VARCHAR2(150);
      lssql              VARCHAR2 (6)   := 'SQL007';
      lsestadoactivo     VARCHAR2 (1)   := '1';
      lnconterrors       NUMBER         := 0;
      lncontexitos       NUMBER         := 0;
      lsestadocapac      VARCHAR (2)    := 'PP';
      lnnumregisproc     NUMBER         := 0;
      lnnumregistros     NUMBER         := 0;
      lscampactual       VARCHAR2 (6);
      lnnumitem          NUMBER         := 0;
      lncont             NUMBER         := 0;
      lsestadook         VARCHAR2 (1)   := '1';
      lsestadonok        VARCHAR2 (1)   := '0';
      lsobservacionok    VARCHAR2 (50)  := 'Aplica Validación';
      lsobservacionnok   VARCHAR2 (70)
         := 'No Aplica Validación para campaña menores o mayores de la Actual';

      TYPE treghistoaptas IS RECORD (
         cod_regi             edu_maest_clien.cod_regi%TYPE,
         ccap_cod_curs_capa   edu_histo_aptas.ccap_cod_curs_capa%TYPE,
         est_capa             edu_histo_aptas.est_capa%TYPE,
         clie_cod_clie        edu_histo_aptas.clie_cod_clie%TYPE,
         cam_ulti_cali_apta   edu_histo_aptas.cam_ulti_cali_apta%TYPE,
         ult_camp_prog_dict   edu_histo_aptas.ult_camp_prog_dict%TYPE
      );

      TYPE tabla_maestra IS TABLE OF treghistoaptas;

      tablaregistro      tabla_maestra;

      TYPE tvalidprog IS REF CURSOR;

      cvalidprog         tvalidprog;
BEGIN
      --Se valida que el registro de nose encuentre ya en la tabla de auditoria
      SELECT COUNT (1)
        INTO lnnumregistros
        FROM edu_audit_cabec a
       WHERE a.pais_cod_pais = pscodigopais
         AND a.emco_cod_empr_come = pscodigoempresa
         AND a.auqu_cod_audi_quer = lssql;

 --  AND A.CAM_PROC=psCampanha;
  --se borran los detalles
      DELETE FROM edu_audit_detal
            WHERE pais_cod_pais = pscodigopais
              AND emco_cod_empr_come = pscodigoempresa
              AND auqu_cod_audi_quer = lssql;

  -- AND CAM_PROC=psCampanha;
      IF (lnnumregistros = 0)
      THEN
    --INSERT EN LA TABLA EDU_AUDIT_CABEC
         INSERT INTO edu_audit_cabec
                     (pais_cod_pais, emco_cod_empr_come, auqu_cod_audi_quer,
                      usu_digi, fec_digi, est_regi, obs_proc,
                      est_proc
                     )
              VALUES (pscodigopais, pscodigoempresa, lssql,
                      psusuario, SYSDATE, lsestadoactivo, lsobservacionok,
                      lsestadook
                     );
  END IF;

      --CAMPANHA DE PROCESO ACTUAL
      lscampactual :=
         edu_pkg_calif.edu_fn_devue_campa_proce_actua (pscodigopais,
                                                       pscodigoempresa
                                                      );

    --Cantidad de Registros a procesar
      OPEN cvalidprog FOR pssentencia
      USING pscodigopais, pscodigoempresa, lsestadocapac;

      LOOP
         FETCH cvalidprog
         BULK COLLECT INTO tablaregistro;

         IF tablaregistro.COUNT > 0
         THEN
            FOR i IN tablaregistro.FIRST .. tablaregistro.LAST
     LOOP
               lnnumitem := lnnumitem + 1;

               SELECT COUNT (1)
                 INTO lncont
                 FROM edu_contr_gener_plani a
                WHERE a.pais_cod_pais = pscodigopais
                  AND a.emco_cod_empr_come = pscodigoempresa
                  AND a.regi_cod_regi IN (tablaregistro (i).cod_regi)
                  AND a.ccap_cod_curs_capa = tablaregistro (i).ccap_cod_curs_capa
				  AND a.PERI_COD_PERI = tablaregistro (i).cam_ulti_cali_apta;

               IF (lncont > 0)
               THEN
                  lnconterrors := lnconterrors + 1;

                  INSERT INTO edu_audit_detal
                              (pais_cod_pais, emco_cod_empr_come,
                               auqu_cod_audi_quer, cam_proc,
                               num_item, cod_clie,
                               cod_curs_capa,
                               ult_camp_prog_dict,
                               est_regi_proc, usu_digi, fec_digi,
                               est_regi, est_capa,
                               cod_regi
                              )
                       VALUES (pscodigopais, pscodigoempresa,
                               lssql, tablaregistro (i).cam_ulti_cali_apta,
                               lnnumitem, tablaregistro (i).clie_cod_clie,
                               tablaregistro (i).ccap_cod_curs_capa,
                               tablaregistro (i).ult_camp_prog_dict,
                               lsestadonok, psusuario, SYSDATE,
                               lsestadoactivo, tablaregistro (i).est_capa,
                               tablaregistro (i).cod_regi
                              );
	  		  ELSE
                  lncontexitos := lncontexitos + 1;

                  INSERT INTO edu_audit_detal
                              (pais_cod_pais, emco_cod_empr_come,
                               auqu_cod_audi_quer, cam_proc,
                               num_item, cod_clie,
                               cod_curs_capa,
                               ult_camp_prog_dict,
                               est_regi_proc, usu_digi, fec_digi,
                               est_regi, est_capa,
                               cod_regi
                              )
                       VALUES (pscodigopais, pscodigoempresa,
                               lssql, tablaregistro (i).cam_ulti_cali_apta,
                               lnnumitem, tablaregistro (i).clie_cod_clie,
                               tablaregistro (i).ccap_cod_curs_capa,
                               tablaregistro (i).ult_camp_prog_dict,
                               lsestadook, psusuario, SYSDATE,
                               lsestadoactivo, tablaregistro (i).est_capa,
                               tablaregistro (i).cod_regi
                              );
		      END IF;
       END LOOP;
       END IF;

         EXIT WHEN cvalidprog%NOTFOUND;
    END LOOP;

      CLOSE cvalidprog;

   --UPDATE AUDITORIA CABECERA
      UPDATE edu_audit_cabec a
         SET a.num_regi_corr = lncontexitos,
             a.num_regi_erro = lnconterrors,
             a.num_regi_proc = lncontexitos + lnconterrors,
             a.est_proc = DECODE (lnconterrors, 0, lsestadook, lsestadonok),
             a.usu_modi = psusuario,
             a.fec_modi = SYSDATE
       WHERE a.pais_cod_pais = pscodigopais
         AND a.emco_cod_empr_come = pscodigoempresa
         AND a.auqu_cod_audi_quer = lssql;

  -- AND A.CAM_PROC=psCampanha;

 RETURN;
EXCEPTION
WHEN OTHERS THEN
      ls_sqlerrm := substr(sqlerrm,1,250);
      RAISE_APPLICATION_ERROR(-20123, 'EDU_PR_VALID_ESTAD_PENDI_PROGA: '||ls_sqlerrm);
END EDU_PR_VALID_ESTAD_PENDI_PROGA;

/***************************************************************************
Descripcion       : Proceso que se encarga de efectuar el proceso de registro de asistencia
Fecha Creacion    : 17/04/2008
Autor             : Sergio Buchelli Silva
Parametros:
          psCodigoPais              : Codigo de Pais
          psCodigoEmpresa           : Codigo de Empresa
           psCodigoCurso               : Codigo de curso
          psCodigoConsultora        : Codigo de Consultora
          psUsuario                 : Usuario de Proceso
          psCodigoAsistenteCurso  : Asistente al Curso
          psCodigoAsistenciaCurso : Asistencia al curso
          psTipoNormal            : Indicador de tipo Normal
Parametros Salida   : psMensajeError: Mensaje de Error
***************************************************************************/
   PROCEDURE edu_pr_regis_asist (
      pscodigopais                    VARCHAR2,
      pscodigoempresa                 VARCHAR2,
      pscodigocurso                   VARCHAR2,
      pscodigoconsultora              VARCHAR2,
      psusuario                       VARCHAR2,
      pscodigoasistentecurso          VARCHAR2,
      pscodigoasistenciacurso         VARCHAR2,
      pstiponormal                    VARCHAR2,
      psmensajeerror            OUT   VARCHAR2
   )
   IS
      ls_sqlerrm            VARCHAR2 (150);
      lscampcapac           edu_histo_curso_dicta_cabec.cam_inic_curs%TYPE;
      lscursodictado        edu_histo_curso_dicta_cabec.cod_curs_dict%TYPE;
      lscampactual          VARCHAR2 (6)                              := NULL;
      lscodigoplanilla      edu_histo_aptas.cod_plan_prog%TYPE
                                                              := '9999999999';
      lscodigoinstructora   edu_histo_plani_instr.inst_cod_inst%TYPE    := '';
                                           --null tiene integridad referncial
      lscodigoregion        edu_maest_clien.cod_regi%TYPE             := '99';
      lscodigozona          edu_maest_clien.cod_zona%TYPE             := '99';
      lnincremento          NUMBER;
      lnnivel               NUMBER;
	  lscodlocal          edu_local.cod_loca%TYPE;
   BEGIN
      psmensajeerror := '';

      IF (pstiponormal = 'SI')
      THEN
          --Se obtiene el codigo curso dictado, instructura , campanha de capacitacion
         --CODIGO PLANILLA,INSTRUCTORA
         SELECT a.cod_plan_prog, b.inst_cod_inst
           INTO lscodigoplanilla, lscodigoinstructora
           FROM edu_histo_aptas a, edu_histo_plani_instr b
          WHERE a.pais_cod_pais = b.pais_cod_pais
            AND a.emco_cod_empr_come = b.emco_cod_empr_come
            AND a.ccap_cod_curs_capa = b.ccap_cod_curs_capa
            AND a.cod_plan_prog = b.cod_plan_prog
            AND a.pais_cod_pais = pscodigopais
            AND a.emco_cod_empr_come = pscodigoempresa
            AND a.ccap_cod_curs_capa = pscodigocurso
            AND a.clie_cod_clie = pscodigoconsultora
            AND a.est_capa = 'PR';


         --OBTENIENDO EL CURSO DE DICTADO
         lscursodictado :=
            edu_fn_sigte_codig_curso_dicta (pscodigopais,
                                            pscodigoempresa,
                                            pscodigocurso
                                           );
      ELSE
         /*SE OBTIENE EL CUROS DE DICTADO DE HA*/
         SELECT a.cod_curs_dict,a.cod_plan_prog
           INTO lscursodictado,lscodigoplanilla
           FROM edu_histo_aptas a
          WHERE a.pais_cod_pais = pscodigopais
            AND a.emco_cod_empr_come = pscodigoempresa
            AND a.ccap_cod_curs_capa = pscodigocurso
            AND a.clie_cod_clie = pscodigoconsultora;
      END IF;


       --REGION Y ZONA
         SELECT a.cod_regi, a.cod_zona
           INTO lscodigoregion, lscodigozona
           FROM edu_maest_clien a
          WHERE a.pais_cod_pais = pscodigopais
            AND a.emco_cod_empr_come = pscodigoempresa
            AND a.cod_clie = pscodigoconsultora;



      --CAMPANHA DE PROCESO ACTUAL
      lscampactual :=
         edu_pkg_calif.edu_fn_devue_campa_proce_actua (pscodigopais,
                                                       pscodigoempresa
                                                      );
      --CAMPANHA CAPACITACION
      lscampcapac :=
                edu_pkg_proce_gener.edu_fn_devue_codig_perio (lscampactual, 1);

		--El local antes se obtenia , ahora se decidio ingresar null 06/10/08
          lscodlocal:='';
      --INSERTAMOS EN CABECERA CURSO DICTADO
      IF (pstiponormal = 'SI')  THEN
        INSERT INTO edu_histo_curso_dicta_cabec
                  (pais_cod_pais, emco_cod_empr_come, ccap_cod_curs_capa,
                   cod_curs_dict, inst_cod_inst, cam_inic_curs,
                   fec_inic_curs, num_real_dura_sesi, cod_regi, cod_zona,
                   ind_eval_curs, est_curs_dict, usu_digi, fec_digi, est_regi,LOCA_COD_LOCA,sist_cod_sist
                  )
           VALUES (pscodigopais, pscodigoempresa, pscodigocurso,
                   lscursodictado, lscodigoinstructora, lscampactual,
                   TRUNC (SYSDATE), 1, lscodigoregion, lscodigozona,
                   'S', 'V', psusuario, SYSDATE, '1',lscodlocal,'EDU'
                  );
       ELSE
	     BEGIN

		 /* Obtenemos la instructora de la region */

				lscodigoinstructora:=
				     edu_fn_devue_codig_instr(pscodigopais,pscodigoempresa,lscodigoregion);

           INSERT INTO edu_histo_curso_dicta_cabec
                  (pais_cod_pais, emco_cod_empr_come, ccap_cod_curs_capa,
                   cod_curs_dict, inst_cod_inst, cam_inic_curs,
                   fec_inic_curs, num_real_dura_sesi, cod_regi, cod_zona,
                   ind_eval_curs, est_curs_dict, usu_digi, fec_digi, est_regi,LOCA_COD_LOCA,sist_cod_sist
                  )
           VALUES (pscodigopais, pscodigoempresa, pscodigocurso,
                   lscursodictado, lscodigoinstructora, lscampactual,
                   TRUNC (SYSDATE), 1, lscodigoregion, lscodigozona,
                   'S', 'C', psusuario, SYSDATE, '1',lscodlocal,'EDU'
                  );
		  EXCEPTION
		   WHEN OTHERS THEN --EN ESTE CASO SE CAPTURA SOLO LA EXECPCION DE LA PLANILLA QUE YA FUE REGISTRADA ANTERIORMENTE
		     NULL;
		  END;
	   END IF;

      --INSERTA EN EL DETALLE
      INSERT INTO edu_histo_curso_dicta_detal
                  (pais_cod_pais, emco_cod_empr_come, ccap_cod_curs_capa,
                   cdic_cod_curs_dict, clie_cod_clie, cod_plan_prog,
                   ind_asis, cal_cons, cal_inst, usu_digi, fec_digi,
                   usu_modi, fec_modi, est_regi
                  )
           VALUES (pscodigopais, pscodigoempresa, pscodigocurso,
                   lscursodictado, pscodigoconsultora, lscodigoplanilla,
                   'S', 0, 0, psusuario, SYSDATE,
                   NULL, NULL, '1'
                  );


         --UPDATE LA TABLA HISTORICO DE PLANILLA POR INSTRUCTORA
         UPDATE edu_histo_plani_instr p
            SET p.cdic_cod_curs_dict = lscursodictado
          WHERE p.pais_cod_pais = pscodigopais
            AND p.emco_cod_empr_come = pscodigoempresa
            AND p.cod_plan_prog = lscodigoplanilla
            AND p.ccap_cod_curs_capa = pscodigocurso;


      --INSERTAMOS O ACTUALIZAMOS EN LAS TABLAS CORRESPONDIENTES A LA CAPACITACION
        -- se obtine el nuevo nivel
      SELECT val_incr_codi
        INTO lnincremento
        FROM edu_param_progr_capac c
       WHERE pais_cod_pais = pscodigopais
         AND emco_cod_empr_come = pscodigoempresa
         AND cod_prog_capa = '01';

    --  lnnivel := TO_NUMBER (pscodigocurso) - lnincremento;

     -- IF (lnnivel > 0)
     -- THEN
         --ACTUALIZAMOS

     -- ELSE
         --INSERTAMOS CABECERA CAPAC
		BEGIN
         INSERT INTO edu_histo_capac_cabec
                     (pais_cod_pais, emco_cod_empr_come, clie_cod_clie,
                      niv_capa_alca, cam_ingr, ult_camp_capa, usu_digi,
                      fec_digi, usu_modi, fec_modi, est_regi)
            SELECT a.pais_cod_pais, a.emco_cod_empr_come, a.clie_cod_clie,
                   a.ccap_cod_curs_capa, a.cam_prim_cali_apta, lscampactual,
                   psusuario, SYSDATE, NULL, NULL, '1'
              FROM edu_histo_aptas a
             WHERE a.pais_cod_pais = pscodigopais
               AND a.emco_cod_empr_come = pscodigoempresa
               AND a.ccap_cod_curs_capa = pscodigocurso
               AND a.clie_cod_clie = pscodigoconsultora;
      --     (lsCodigoPlanilla IS NULL OR A.COD_PLAN_PROG =lsCodigoPlanilla) AND
	     EXCEPTION
		  WHEN OTHERS THEN

		 IF (pstiponormal = 'SI')  THEN -- FLUJO DE REGULARIZACION
         --ACTUALIZAMOS
         UPDATE edu_histo_capac_cabec
            SET niv_capa_alca = pscodigocurso,
		        ult_camp_capa = lscampcapac,
                usu_modi = psusuario,
                fec_modi = SYSDATE
          WHERE pais_cod_pais = pscodigopais
            AND emco_cod_empr_come = pscodigoempresa
            AND clie_cod_clie = pscodigoconsultora;

        ELSE -- SI VIENE DEL FLUJO DE EXONERACION
         --ACTUALIZAMOS
         UPDATE edu_histo_capac_cabec
            SET niv_capa_alca = pscodigocurso,
        		ult_camp_capa = lscampactual,
                usu_modi = psusuario,
                fec_modi = SYSDATE
          WHERE pais_cod_pais = pscodigopais
            AND emco_cod_empr_come = pscodigoempresa
            AND clie_cod_clie = pscodigoconsultora;

      END IF;
		 END;
    --  END IF;


      IF (pstiponormal = 'SI')
      THEN

	        --DETALLE CAPACITACION
       INSERT INTO edu_histo_capac_detal
                  (pais_cod_pais, emco_cod_empr_come, clie_cod_clie,
                   ccap_cod_curs_capa, tasi_cod_tipo_asis_curs,
                   astt_cod_tipo_astt_curs, num_invi, ind_pago_curs,
                   cod_curs_dict, cod_plan_prog, inst_cod_inst,
                   cam_prim_cali_apta, cam_ulti_cali_apta, cam_capa,
                   cam_regi_asis, ind_eval_curs, cal_eval_curs, ind_eval_inst,
                   cal_eval_inst, usu_digi, fec_digi, usu_modi, fec_modi,
                   est_regi)
         SELECT a.pais_cod_pais, a.emco_cod_empr_come, a.clie_cod_clie,
                a.ccap_cod_curs_capa, pscodigoasistenciacurso,
                pscodigoasistentecurso, a.num_invi, a.ind_curs_cost,
                lscursodictado, a.cod_plan_prog, lscodigoinstructora,
                a.cam_prim_cali_apta, a.cam_ulti_cali_apta, lscampcapac,
                lscampcapac, 'N', 0, c.ind_cali_cdra, 0, psusuario, SYSDATE,
                NULL, NULL, '1'
           FROM edu_histo_aptas a, edu_param_curso_capac c
          WHERE a.pais_cod_pais = c.pais_cod_pais
            AND a.emco_cod_empr_come = c.emco_cod_empr_come
            AND a.ccap_cod_curs_capa = c.cod_curs_capa
            AND a.pais_cod_pais = pscodigopais
            AND a.emco_cod_empr_come = pscodigoempresa
            AND a.ccap_cod_curs_capa = pscodigocurso
            AND  a.clie_cod_clie = pscodigoconsultora;
                -- A.COD_PLAN_PROG =lsCodigoPlanilla


         -- ACTUALIZAMOS EL HISTORICO DE APTAS
         UPDATE edu_histo_aptas
            SET cod_curs_dict = lscursodictado,
                est_capa = 'CP',
                cam_capa = lscampactual,
                usu_modi = psusuario,
                fec_modi = SYSDATE
          WHERE pais_cod_pais = pscodigopais
            AND emco_cod_empr_come = pscodigoempresa
            AND ccap_cod_curs_capa = pscodigocurso
            AND clie_cod_clie = pscodigoconsultora;
      --AND         COD_PLAN_PROG= lsCodigoPlanilla ;

	  ELSE

	         --DETALLE CAPACITACION
       INSERT INTO edu_histo_capac_detal
                  (pais_cod_pais, emco_cod_empr_come, clie_cod_clie,
                   ccap_cod_curs_capa, tasi_cod_tipo_asis_curs,
                   astt_cod_tipo_astt_curs, num_invi, ind_pago_curs,
                   cod_curs_dict, cod_plan_prog, inst_cod_inst,
                   cam_prim_cali_apta, cam_ulti_cali_apta, cam_capa,
                   cam_regi_asis, ind_eval_curs, cal_eval_curs, ind_eval_inst,
                   cal_eval_inst, usu_digi, fec_digi, usu_modi, fec_modi,
                   est_regi)
         SELECT a.pais_cod_pais, a.emco_cod_empr_come, a.clie_cod_clie,
                a.ccap_cod_curs_capa, pscodigoasistenciacurso,
                pscodigoasistentecurso, a.num_invi, a.ind_curs_cost,
                lscursodictado, a.cod_plan_prog, lscodigoinstructora,
                a.cam_prim_cali_apta, a.cam_ulti_cali_apta, lscampactual,
                lscampactual, 'N', 0, c.ind_cali_cdra, 0, psusuario, SYSDATE,
                NULL, NULL, '1'
           FROM edu_histo_aptas a, edu_param_curso_capac c
          WHERE a.pais_cod_pais = c.pais_cod_pais
            AND a.emco_cod_empr_come = c.emco_cod_empr_come
            AND a.ccap_cod_curs_capa = c.cod_curs_capa
            AND a.pais_cod_pais = pscodigopais
            AND a.emco_cod_empr_come = pscodigoempresa
            AND a.ccap_cod_curs_capa = pscodigocurso
            AND  a.clie_cod_clie = pscodigoconsultora;
                -- A.COD_PLAN_PROG =lsCodigoPlanilla

      END IF;

      RETURN;
   EXCEPTION
      WHEN OTHERS
      THEN
         ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
         raise_application_error (-20123,
                                  'EDU_PR_REGIS_ASIST: ' || ls_sqlerrm
                                 );
   END edu_pr_regis_asist;

/***************************************************************************
Descripcion       : Proceso que se encarga de efectuar el proceso de registro de asistencia
Fecha Creacion    : 17/04/2008
Autor             : Sergio Buchelli Silva
Parametros:
          psCodigoPais              : Codigo de Pais
          psCodigoEmpresa           : Codigo de Empresa
Parametros Salida   : psMensajeError: Mensaje de Error
***************************************************************************/
   PROCEDURE edu_pr_regis_asist_cierr_campa (
      pscodigopais            VARCHAR2,
      pscodigoempresa         VARCHAR2,
      psmensajeerror    OUT   VARCHAR2
   )
   IS
      ls_sqlerrm                VARCHAR2 (150);

      TYPE tregtemporal IS RECORD (
         cod_pais        edu_tmp_recep_clien_estab.cod_pais%TYPE,
         cod_empr_come   edu_tmp_recep_clien_estab.cod_empr_come%TYPE,
         cod_clie        edu_tmp_recep_clien_estab.cod_clie%TYPE,
         cod_curso       edu_param_curso_capac.cod_curs_capa%TYPE,
         ind_clie_top    edu_tmp_recep_clien_estab.ind_clie_top%TYPE,
         usu_digi        edu_tmp_recep_clien_estab.usu_digi%TYPE,
		 cod_regi		 edu_maest_clien.cod_regi%TYPE,
		 cod_zona		 edu_maest_clien.cod_zona%TYPE,
		 cod_secc		 edu_maest_clien.cod_secc%TYPE,
		 cod_terr		 edu_maest_clien.cod_terr%TYPE
      );

      TYPE tabla_temporal IS TABLE OF tregtemporal;

      tablaregistro             tabla_temporal;
      regregistro               tregtemporal;

      CURSOR cursorregistro
      IS
         SELECT   a.cod_pais, a.cod_empr_come, a.cod_clie, '10' AS cod_curso,
		           ind_clie_top, a.usu_digi,b.cod_regi,b.cod_zona,b.cod_secc,b.cod_terr
		  FROM edu_tmp_recep_clien_estab a , edu_maest_clien b
            WHERE a.cod_pais = pscodigopais
              AND a.cod_empr_come = pscodigoempresa
              AND a.est_regi = '1'
		   AND a.cod_pais=b.pais_cod_pais
		   AND a.cod_empr_come=b.emco_cod_empr_come
		   AND a.cod_clie=b.cod_clie
         UNION ALL
         SELECT   a.cod_pais, a.cod_empr_come, a.cod_clie, '20' AS cod_curso,
		           ind_clie_top, a.usu_digi,b.cod_regi,b.cod_zona,b.cod_secc,b.cod_terr
		  FROM edu_tmp_recep_clien_estab a , edu_maest_clien b
            WHERE a.cod_pais = pscodigopais
              AND a.cod_empr_come = pscodigoempresa
              AND a.est_regi = '1'
		   AND a.cod_pais=b.pais_cod_pais
		   AND a.cod_empr_come=b.emco_cod_empr_come
		   AND a.cod_clie=b.cod_clie
		  ORDER BY  cod_pais, cod_empr_come, cod_curso,cod_regi;

      pscodigoasistentecurso    edu_tipo_asitt_curso.cod_tipo_astt_curs%TYPE;
      pscodigoasistenciacurso   edu_tipo_asist_curso.cod_tipo_asis_curs%TYPE;
      lscampactual              VARCHAR2 (6);
      lncontador                NUMBER;
      lscursodictado            edu_histo_curso_dicta_cabec.cod_curs_dict%TYPE;
      lstipo                    VARCHAR2 (2)                           := 'NO';
	  lscampcapac				VARCHAR2 (6);
	  lscodregionant			edu_maest_clien.cod_regi%TYPE;
	  lscodregionact			edu_maest_clien.cod_regi%TYPE;
	  lscodigoplanilla			edu_contr_gener_plani.cod_plan_prog_fina%TYPE;
      lscodiginstr   edu_regio.inst_cod_inst%TYPE;

      --excepciones
      clien_exist               EXCEPTION;
   BEGIN
--INICIALIZANDO
      psmensajeerror := '';
      pscodigoasistentecurso := 'E';
      pscodigoasistenciacurso := 'E';
      --SE inserta o actulaiza en maestro clientes,historico de pedidos  ,Historico de aptas y luego se hizo registro de capacitacion

      --Campanha
        --CAMPANHA DE PROCESO ACTUAL , SE CORRE AL FINAL DE LA CAMPNHA ANT  ,
      lscampactual :=
         edu_pkg_calif.edu_fn_devue_campa_proce_actua (pscodigopais,
                                                       pscodigoempresa
                                                      );

      OPEN cursorregistro;

      LOOP
         FETCH cursorregistro
         BULK COLLECT INTO tablaregistro LIMIT w_filas;

         IF tablaregistro.COUNT > 0
         THEN
		    lscodregionant:='XX';
            FOR x IN tablaregistro.FIRST .. tablaregistro.LAST
            LOOP
			  -- DBMS_OUTPUT.PUT_LINE('X   '|| x);
               regregistro := tablaregistro (x);
               /* Insertando o actulazaMS  en Maestro de Clientes  YA NO VA*/
			   /* Se tiene dos registros para el mismo cleinte para el curso 10 y 20 , la validacion se hace solo par un curso
			    tomaremos el primer nivel 10 */
				lscodregionact:=regregistro.cod_regi;

			 IF(regregistro.cod_curso='10') THEN

               /* Insertando en EDU_HISTO_PEDID*/

               IF (regregistro.ind_clie_top = 'S')
               THEN
			     /* Actualizando el Maestro de Clientes */
   				 UPDATE EDU_MAEST_CLIEN A
				 SET A.NIV_CONS='02', --CONSULTORA TOP
				 	 USU_MODI=regregistro.usu_digi,
				 	 FEC_MODI=SYSDATE
				 WHERE 	A.PAIS_COD_PAIS=pscodigopais
				   AND A.EMCO_COD_EMPR_COME=pscodigoempresa
				   AND A.COD_CLIE=regregistro.cod_clie;


                  INSERT INTO edu_histo_pedid_consu
                              (pais_cod_pais, emco_cod_empr_come,
                               cod_clie, cam_inic_pedi,
                               cam_ulti_pedi, cam_proc, cod_ulti_nive,
                               num_pedi_fact, ind_pedi, ind_fact, ind_nuev,
                               ind_cons, ind_prim_pedi, usu_digi, fec_digi,
                               est_regi
                              )
                       VALUES (pscodigopais, pscodigoempresa,
                               regregistro.cod_clie, lscampactual,
                               lscampactual, lscampactual, '00',
                               0, 0, 0, 0,
                               0, 0, regregistro.usu_digi, SYSDATE,
                               '1'
                              );
               ELSE
 	            /* Actualizando el Maestro de Clientes */
   				 UPDATE EDU_MAEST_CLIEN A
				 SET A.NIV_CONS='01', --CONSULTORA NORMAL
				 	 USU_MODI=regregistro.usu_digi,
				 	 FEC_MODI=SYSDATE
				 WHERE 	A.PAIS_COD_PAIS=pscodigopais
				   AND A.EMCO_COD_EMPR_COME=pscodigoempresa
				   AND A.COD_CLIE=regregistro.cod_clie;


                  INSERT INTO edu_histo_pedid_consu
                              (pais_cod_pais, emco_cod_empr_come,
                               cod_clie, cam_inic_pedi,
                               cam_ulti_pedi, cam_proc, cod_ulti_nive,
                               num_pedi_fact, ind_pedi, ind_fact, ind_nuev,
                               ind_cons, ind_prim_pedi, usu_digi, fec_digi,
                               est_regi
                              )
                       VALUES (pscodigopais, pscodigoempresa,
                               regregistro.cod_clie, lscampactual,
                               lscampactual, lscampactual, '00',
                               0, 0, 0, 0,
                               0, 0, regregistro.usu_digi, SYSDATE,
                               '1'
                              );
               END IF;


            END IF;
               /* Insertando  en la Tabla de Historico de Aptas Y CALIFICACION DE APTAS */
               --OBTENIENDO EL CURSO DE DICTADO POR REGION DISTINTA

			   IF(lscodregionact <> lscodregionant)THEN
               lscursodictado :=
                  edu_fn_sigte_codig_curso_dicta (pscodigopais,
                                                  pscodigoempresa,
                                                  regregistro.cod_curso
                                                 );

			   lscodigoplanilla:=
			      edu_fn_devue_codig_plani_final(pscodigopais,
                                                  pscodigoempresa,
                                                  regregistro.cod_curso,
				  								  lscampactual,
												  lscodregionact,
												  regregistro.usu_digi
												  ) ;
			   END IF;

                  --CAMPANHA CAPACITACION = PROCESO PARA ESTE CASO
               lscampcapac := lscampactual;--edu_pkg_proce_gener.edu_fn_devue_codig_perio (lscampactual, 1);


				/* Insertamos en el H.A*/


               INSERT INTO edu_histo_aptas
                           (pais_cod_pais, emco_cod_empr_come,
                            ccap_cod_curs_capa, clie_cod_clie,
                            cod_curs_dict, cod_plan_prog, cam_prim_cali_apta,
                            cam_ulti_cali_apta, tip_cali_apta, num_invi,
                            ind_inic_cali_apta, ind_fina_cali_apta,
                            ind_curs_cost, cam_acep, cam_capa, est_capa,
                            ind_envi, ind_envi_prog, num_lote_diar,
                            num_lote_regi, usu_digi, fec_digi, est_regi
                           )
                    VALUES (pscodigopais, pscodigoempresa,
                            regregistro.cod_curso, regregistro.cod_clie,
                            lscursodictado, lscodigoplanilla, lscampactual,
                            lscampactual, 'E', 1,
                            'D', 'D',
                            'N', NULL, lscampcapac, 'CP',
                            'N', 'N', NULL,
                            NULL, regregistro.usu_digi, SYSDATE, '1'
                           );

               INSERT INTO edu_histo_calif_aptas
                           (pais_cod_pais, emco_cod_empr_come,
                            ccap_cod_curs_capa, cam_proc,
                            clie_cod_clie, cod_curs_dict,
                            cod_plan_prog, cam_prim_cali_apta,
                            cam_ulti_cali_apta, tip_cali_apta, num_invi,
                            ind_inic_cali_apta, ind_fina_cali_apta,
                            ind_curs_cost, cam_capa, est_capa, ind_envi,
                            num_lote_diar, usu_digi, fec_digi, est_regi
                           )
                    VALUES (pscodigopais, pscodigoempresa,
                            regregistro.cod_curso, lscampactual,
                            regregistro.cod_clie, lscursodictado,
                            lscodigoplanilla, lscampactual,
                            lscampactual, 'E', 1,
                            'D', 'D',
                            'N', lscampactual, 'PC', 'N',
                            NULL, regregistro.usu_digi, SYSDATE, '1'
                           );


			   /* INSERTAMOS EN PLANILLA PROGRAMACION CURSO */

			   INSERT INTO EDU_PLANI_PROGR_CURSO (
					   PAIS_COD_PAIS, EMCO_COD_EMPR_COME, CCAP_COD_CURS_CAPA,
					   CLIE_COD_CLIE, CAM_PROC, COD_PLAN_PROG,
					   TIP_CALI_APTA, NUM_INVI, IND_CURS_COST,
					   USU_DIGI,FEC_DIGI,EST_REGI)
					VALUES ( pscodigopais, pscodigoempresa,regregistro.cod_curso ,
					    regregistro.cod_clie,lscampactual ,lscodigoplanilla ,
					    'E', 1, 'N',regregistro.usu_digi,SYSDATE,'1');


				/* Obtenemos la instructora de la region */

				lscodiginstr:=
				     edu_fn_devue_codig_instr(pscodigopais,pscodigoempresa,lscodregionact);

				/* Insertamos en la tabla Historico Planilla Instructora */
			   IF(lscodregionact <> lscodregionant)THEN
				INSERT INTO EDU_HISTO_PLANI_INSTR (
						   PAIS_COD_PAIS, EMCO_COD_EMPR_COME, CCAP_COD_CURS_CAPA,
						   COD_PLAN_PROG, REGI_COD_REGI, ZONA_COD_ZONA,
						   COD_SECC, COD_TERR, INST_COD_INST,
						   CDIC_COD_CURS_DICT, SIT_PLAN_PROG, USU_DIGI,
						   FEC_DIGI,EST_REGI)
						VALUES ( pscodigopais,pscodigoempresa , regregistro.cod_curso,
						    lscodigoplanilla,lscodregionact ,regregistro.cod_zona ,
						    regregistro.cod_secc,regregistro.cod_terr ,lscodiginstr ,
						    NULL,'P' , regregistro.usu_digi,SYSDATE,'1');
				END IF;
				/* ACTULAIZANDO LA REGION ANTERIOR A LA ACTUAL*/
				lscodregionant:=	lscodregionact;
               /*FINALMENTE REGISTRAMOS ASISTENCIA*/
               edu_pkg_proce_gener.edu_pr_regis_asist
                                                     (pscodigopais,
                                                      pscodigoempresa,
                                                      regregistro.cod_curso,
                                                      regregistro.cod_clie,
                                                      regregistro.usu_digi,
                                                      pscodigoasistentecurso,
                                                      pscodigoasistenciacurso,
                                                      psmensajeerror,
                                                      lstipo
                                                     );
            END LOOP;
         END IF;

         EXIT WHEN cursorregistro%NOTFOUND;
      END LOOP;

      CLOSE cursorregistro;

      RETURN;
   EXCEPTION
      WHEN clien_exist
      THEN
         ls_sqlerrm := 'CLIEN_EXIST ' || regregistro.COD_CLIE ;
         raise_application_error (-20123,
                                     'EDU_PR_REGIS_ASIST_CIERR_CAMPA: '
                                  || ls_sqlerrm
                                 );
      WHEN OTHERS
      THEN
         ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
         raise_application_error (-20123,
                                     'EDU_PR_REGIS_ASIST_CIERR_CAMPA: '
                                  || ls_sqlerrm
                                 );
   END edu_pr_regis_asist_cierr_campa;

/***************************************************************************
Descripcion       : Proceso que se encarga de validar que no existan planillas activas
                    cuando se han registrado nuevas planillas para el periodo
                    siguiente en la misma region

Fecha Creacion    : 23/04/2008
Autor             : Rafael Romero
Parametros:
          psCodigoPais    : Codigo de Pais
          psCodigoEmpresa : Codigo de Empresa
          psCampanha   : Campanha Inicial
          psSentencia      :Setencia,
          psUsuario       : Usuario de Proceso
Parametros Salida   : psMensajeError: Mensaje de Error
***************************************************************************/
   PROCEDURE edu_pr_valid_plani_activ (
      pscodigopais            VARCHAR2,
      pscodigoempresa         VARCHAR2,
      pssentencia             VARCHAR2,
      pscampanha              VARCHAR2,
      psusuario               VARCHAR2,
      psmensajeerror    OUT   VARCHAR2
   )
   IS
      TYPE registro_val IS RECORD (
         clie_cod_clie        edu_histo_aptas.clie_cod_clie%TYPE,
         cod_plan_prog        edu_histo_plani_instr.cod_plan_prog%TYPE,
         ult_camp_prog_dict   edu_histo_aptas.ult_camp_prog_dict%TYPE,
         ccap_cod_curs_capa   edu_histo_aptas.ccap_cod_curs_capa%TYPE,
         cod_regi        edu_contr_gener_plani.regi_cod_regi%TYPE
      );

      TYPE tabla_temporal IS TABLE OF registro_val;
      tablaregistro      tabla_temporal;

      ls_sqlerrm         VARCHAR2 (150);
      lssql              VARCHAR2 (6)   := 'SQL008';
      lsestadoactivo     VARCHAR2 (1)   := '1';
      lnconterrors       NUMBER         := 0;
      lncontexitos       NUMBER         := 0;
      lsestadocapac      VARCHAR (2)    := 'PP';
      lnnumregisproc     NUMBER         := 0;
      lnnumregistros     NUMBER         := 0;
      lscampaplanilla    VARCHAR2 (6);
      lnnumitem          NUMBER         := 0;
      lncontador         NUMBER         := 0;
      lsestadook         VARCHAR2 (1)   := '1';
      lsestadonok        VARCHAR2 (1)   := '0';
      lsobservacionok    VARCHAR2 (50)  := 'Aplica Validación';
      lsobservacionnok   VARCHAR2 (70)
         := 'No Aplica Validación para campaña menores o mayores de la Actual';
      procesar           BOOLEAN;

	  --este cursos nos da las consultoras que s eencuentran en la planilla Actva del peridod anterior y deben estar cerradas
      CURSOR cursorplanillaactiva(vscodplanilla VARCHAR2, vsCodCurso VARCHAR2)
      IS
         SELECT   a.clie_cod_clie, a.cod_plan_prog,a.CAM_PROC,
                  a.ccap_cod_curs_capa,b.cod_regi
         FROM EDU_PLANI_PROGR_CURSO A ,EDU_MAEST_CLIEN B
		 WHERE A.PAIS_COD_PAIS = pscodigopais
		  AND A.EMCO_COD_EMPR_COME = pscodigoempresa
		  AND A.CCAP_COD_CURS_CAPA =vsCodCurso
		  AND A.COD_PLAN_PROG = vscodplanilla
		  AND A.PAIS_COD_PAIS=B.PAIS_COD_PAIS
		  AND A.EMCO_COD_EMPR_COME=B.EMCO_COD_EMPR_COME
		  AND A.CLIE_COD_CLIE=B.COD_CLIE ;


	  TYPE r_cabPlanilla IS RECORD (
        codinstr edu_histo_plani_instr.inst_cod_inst%TYPE,
        codplani edu_histo_plani_instr.cod_plan_prog%TYPE,
        codregio edu_histo_plani_instr.regi_cod_regi%TYPE,
        codzona edu_histo_plani_instr.zona_cod_zona%TYPE,
        codCurso edu_histo_plani_instr.CCAP_COD_CURS_CAPA%TYPE
     );
    TYPE t_cabPlanilla IS TABLE OF r_cabPlanilla;

	lt_cabPlanilla        t_cabPlanilla;

	  CURSOR cplanilla (vscodperiodoplani VARCHAR2)
    IS
       SELECT   a.inst_cod_inst, a.cod_plan_prog,
                a.regi_cod_regi, a.zona_cod_zona,
                a.CCAP_COD_CURS_CAPA
           FROM edu_histo_plani_instr a,
                (SELECT   *
                     FROM edu_contr_gener_plani a1
                    WHERE a1.peri_cod_peri = vscodperiodoplani
                      AND a1.pais_cod_pais = pscodigopais
                      AND a1.emco_cod_empr_come = psCodigoEmpresa
                 ORDER BY a1.pais_cod_pais, TO_NUMBER (a1.cod_plan_prog_inic)) b
          WHERE a.pais_cod_pais = psCodigoPais
            AND a.emco_cod_empr_come = psCodigoEmpresa
            AND a.sit_plan_prog = 'A'
            AND a.inst_cod_inst IS NOT NULL
            AND a.pais_cod_pais = b.pais_cod_pais
            AND a.emco_cod_empr_come = b.emco_cod_empr_come
            AND a.ccap_cod_curs_capa = b.ccap_cod_curs_capa
            AND a.regi_cod_regi = b.regi_cod_regi
            AND TO_NUMBER (a.cod_plan_prog)
                   BETWEEN TO_NUMBER (b.cod_plan_prog_inic)
                       AND TO_NUMBER (b.cod_plan_prog_fina)
       ORDER BY a.ccap_cod_curs_capa, a.inst_cod_inst, a.cod_plan_prog;

   BEGIN
      --Se valida que el registro de nose encuentre ya en la tabla de auditoria
      SELECT COUNT (1)
        INTO lnnumregistros
        FROM edu_audit_cabec a
       WHERE a.pais_cod_pais = pscodigopais
         AND a.emco_cod_empr_come = pscodigoempresa
         AND a.auqu_cod_audi_quer = lssql;

      --  AND A.CAM_PROC=psCampanha;
       --se borran los detalles
      DELETE FROM edu_audit_detal
            WHERE pais_cod_pais = pscodigopais
              AND emco_cod_empr_come = pscodigoempresa
              AND auqu_cod_audi_quer = lssql;

      -- AND CAM_PROC=psCampanha;
      IF (lnnumregistros = 0)
      THEN
         --INSERT EN LA TABLA EDU_AUDIT_CABEC
         INSERT INTO edu_audit_cabec
                     (pais_cod_pais, emco_cod_empr_come, auqu_cod_audi_quer,
                      usu_digi, fec_digi, est_regi, obs_proc,
                      est_proc
                     )
              VALUES (pscodigopais, pscodigoempresa, lssql,
                      psusuario, SYSDATE, lsestadoactivo, lsobservacionok,
                      lsestadook
                     );
      END IF;

	  lscampaplanilla := edu_pkg_proce_gener.edu_fn_devue_codig_perio (pscampanha, -1);

      OPEN cplanilla(lscampaplanilla);

      LOOP
         FETCH cplanilla BULK COLLECT INTO lt_cabPlanilla  LIMIT w_filas;

         EXIT WHEN lt_cabPlanilla.COUNT = 0;

         FOR x IN lt_cabPlanilla.FIRST .. lt_cabPlanilla.LAST
         LOOP

		    SELECT count(1) into lncontador
			FROM edu_contr_gener_plani A
			 WHERE A.PAIS_COD_PAIS =psCodigoPais
			  AND A.EMCO_COD_EMPR_COME = pscodigoempresa
 	          AND A.PERI_COD_PERI =pscampanha
			  AND A.CCAP_COD_CURS_CAPA =lt_cabPlanilla(x).codCurso
		      AND A.REGI_COD_REGI = lt_cabPlanilla(x).codregio;

		     -- si esxite palnilla ya generada en el periodo actual sin cerrar la anterior es error
		   	 IF ( lncontador > 0) THEN
				   --se abre el cursos , estos registros son de error

				     --Cantidad de Registros a procesar
		      OPEN cursorplanillaactiva (lt_cabPlanilla(x).codplani,lt_cabPlanilla(x).codCurso);

		      LOOP
		         FETCH cursorplanillaactiva
		         BULK COLLECT INTO tablaregistro LIMIT w_filas;

		         IF tablaregistro.COUNT > 0
            THEN
		            FOR i IN tablaregistro.FIRST .. tablaregistro.LAST
		            LOOP
               lnnumitem := lnnumitem + 1;
                  lnconterrors := lnconterrors + 1;

                  INSERT INTO edu_audit_detal
                              (pais_cod_pais, emco_cod_empr_come,
                               auqu_cod_audi_quer, cam_proc,
                               num_item, cod_clie,
                               cod_curs_capa,
		                            est_regi_proc, usu_digi, fec_digi, est_regi,
		                            ult_camp_prog_dict
                              )
                       VALUES (pscodigopais, pscodigoempresa,
		                            lssql, pscampanha,
                               lnnumitem, tablaregistro (i).clie_cod_clie,
                               tablaregistro (i).ccap_cod_curs_capa,
		                            lsestadonok, psusuario, SYSDATE, lsestadoactivo,
		                            tablaregistro (i).ult_camp_prog_dict
                              );
		            END LOOP;
		         END IF;

		         EXIT WHEN cursorplanillaactiva%NOTFOUND;
		       END LOOP;

     		   CLOSE cursorplanillaactiva;

            END IF;

         END LOOP;
      END LOOP;

      CLOSE cplanilla;

      --UPDATE AUDITORIA CABECERA
      UPDATE edu_audit_cabec a
         SET a.num_regi_corr = lncontexitos,
             a.num_regi_erro = lnconterrors,
             a.num_regi_proc = lncontexitos + lnconterrors,
             a.est_proc = DECODE (lnconterrors, 0, lsestadook, lsestadonok),
             a.usu_modi = psusuario,
             a.fec_modi = SYSDATE
       WHERE a.pais_cod_pais = pscodigopais
         AND a.emco_cod_empr_come = pscodigoempresa
         AND a.auqu_cod_audi_quer = lssql;

      RETURN;
   EXCEPTION
      WHEN OTHERS
      THEN
         ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
         raise_application_error (-20123,
                                  'EDU_PR_VALID_PLANI_ACTIV: ' || ls_sqlerrm
                                 );
   END edu_pr_valid_plani_activ;

/***************************************************************************
Descripcion       : Proceso que se encarga de validar que una consultora programada para un curso , y cuya planilla
				    este procesada se encuentre tambien reggistrado en el detalle del curso dictado

Fecha Creacion    : 24/04/2008
Autor             : Rafael Romero
Parametros:
          psCodigoPais    : Codigo de Pais
          psCodigoEmpresa : Codigo de Empresa
          psCampanha   : Campanha Inicial
          psSentencia      :Setencia,
          psUsuario       : Usuario de Proceso
Parametros Salida   : psMensajeError: Mensaje de Error
***************************************************************************/
   PROCEDURE edu_pr_valid_progr_detal_dicta (
      pscodigopais            VARCHAR2,
      pscodigoempresa         VARCHAR2,
      pssentencia             VARCHAR2,
      pscampanha              VARCHAR2,
      psusuario               VARCHAR2,
      psmensajeerror    OUT   VARCHAR2
   )
   IS
      TYPE registro_val IS RECORD (
         ccap_cod_curs_capa   edu_histo_aptas.ccap_cod_curs_capa%TYPE,
         cod_plan_prog        edu_histo_plani_instr.cod_plan_prog%TYPE,
         clie_cod_clie        edu_histo_aptas.clie_cod_clie%TYPE,
         ult_camp_prog_dict   edu_histo_aptas.ult_camp_prog_dict%TYPE,
         regi_cod_regi        edu_contr_gener_plani.regi_cod_regi%TYPE
      );

      TYPE tabla_temporal IS TABLE OF registro_val;

      tablaregistro      tabla_temporal;
      ls_sqlerrm         VARCHAR2 (150);
      lssql              VARCHAR2 (6)   := 'SQL009';
      lsestadoactivo     VARCHAR2 (1)   := '1';
      lnconterrors       NUMBER         := 0;
      lncontexitos       NUMBER         := 0;
      lsestadocapac      VARCHAR (2)    := 'PP';
      lnnumregisproc     NUMBER         := 0;
      lnnumregistros     NUMBER         := 0;
      lscampactual       VARCHAR2 (6);
      lnnumitem          NUMBER         := 0;
      lnccont            NUMBER         := 0;
      lsestadook         VARCHAR2 (1)   := '1';
      lsestadonok        VARCHAR2 (1)   := '0';
      lsobservacionok    VARCHAR2 (50)  := 'Aplica Validación';
      lsobservacionnok   VARCHAR2 (70)
         := 'No Aplica Validación para campaña menores o mayores de la Actual';
      procesar           BOOLEAN;

      CURSOR cursorevaluacion
      IS
        SELECT A.CCAP_COD_CURS_CAPA,A.COD_PLAN_PROG,A.CLIE_COD_CLIE,A.CAM_proc,B.REGI_COD_REGI
		  FROM edu_histo_plani_instr B, EDU_PLANI_PROGR_CURSO A
		  WHERE
		    B.PAIS_COD_PAIS =pscodigopais
		   AND B.EMCO_COD_EMPR_COME =pscodigoempresa
		   AND   B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
		   AND B.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
		   AND B.COD_PLAN_PROG = A.COD_PLAN_PROG
		   AND B.CCAP_COD_CURS_CAPA = A.CCAP_COD_CURS_CAPA
		   AND B.SIT_PLAN_PROG ='P'
		   AND A.CAM_PROC =pscampanha
		   AND A.CLIE_COD_CLIE NOT IN(
			   SELECT b.CLIE_COD_CLIE
			   FROM edu_histo_curso_dicta_detal b
			   WHERE b.PAIS_COD_PAIS =A.PAIS_COD_PAIS
			     and b.EMCO_COD_EMPR_COME = a.EMCO_COD_EMPR_COME
				 and b.CCAP_COD_CURS_CAPA =a.CCAP_COD_CURS_CAPA
				 and b.COD_PLAN_PROG =a.COD_PLAN_PROG)
		ORDER BY A.COD_PLAN_PROG;
   BEGIN
      --Se valida que el registro de nose encuentre ya en la tabla de auditoria
      SELECT COUNT (1)
        INTO lnnumregistros
        FROM edu_audit_cabec a
       WHERE a.pais_cod_pais = pscodigopais
         AND a.emco_cod_empr_come = pscodigoempresa
         AND a.auqu_cod_audi_quer = lssql;

      --  AND A.CAM_PROC=psCampanha;
       --se borran los detalles
      DELETE FROM edu_audit_detal
            WHERE pais_cod_pais = pscodigopais
              AND emco_cod_empr_come = pscodigoempresa
              AND auqu_cod_audi_quer = lssql;

      -- AND CAM_PROC=psCampanha;
      IF (lnnumregistros = 0)
      THEN
         --INSERT EN LA TABLA EDU_AUDIT_CABEC
         INSERT INTO edu_audit_cabec
                     (pais_cod_pais, emco_cod_empr_come, auqu_cod_audi_quer,
                      usu_digi, fec_digi, est_regi, obs_proc,
                      est_proc
                     )
              VALUES (pscodigopais, pscodigoempresa, lssql,
                      psusuario, SYSDATE, lsestadoactivo, lsobservacionok,
                      lsestadook
                     );
      END IF;

      OPEN cursorevaluacion;

      LOOP
         FETCH cursorevaluacion
         BULK COLLECT INTO tablaregistro;

         EXIT WHEN tablaregistro.COUNT = 0;

         FOR i IN tablaregistro.FIRST .. tablaregistro.LAST
         LOOP

               lnnumitem := lnnumitem + 1;

                  lnconterrors := lnconterrors + 1;

                  INSERT INTO edu_audit_detal
                              (pais_cod_pais, emco_cod_empr_come,
                               auqu_cod_audi_quer, cam_proc,
                               num_item, cod_clie,
                               cod_curs_capa,
                               ult_camp_prog_dict,
                               est_regi_proc, usu_digi, fec_digi,
                               est_regi,cod_regi
                              )
                       VALUES (pscodigopais, pscodigoempresa,
                               lssql, pscampanha,
                               lnnumitem, tablaregistro (i).clie_cod_clie,
                               tablaregistro (i).ccap_cod_curs_capa,
                               tablaregistro (i).ult_camp_prog_dict,
                               lsestadonok, psusuario, SYSDATE,
                               lsestadoactivo,tablaregistro (i).regi_cod_regi
                              );

         END LOOP;
      END LOOP;

      CLOSE cursorevaluacion;

      --UPDATE AUDITORIA CABECERA
      UPDATE edu_audit_cabec a
         SET a.num_regi_corr = lncontexitos,
             a.num_regi_erro = lnconterrors,
             a.num_regi_proc = lncontexitos + lnconterrors,
             a.est_proc = DECODE (lnconterrors, 0, lsestadook, lsestadonok),
             a.usu_modi = psusuario,
             a.fec_modi = SYSDATE
       WHERE a.pais_cod_pais = pscodigopais
         AND a.emco_cod_empr_come = pscodigoempresa
         AND a.auqu_cod_audi_quer = lssql;

      RETURN;
   EXCEPTION
      WHEN OTHERS
      THEN
         ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
         raise_application_error (-20123,
                                     'EDU_PR_VALID_PROGR_DETAL_DICTA: '
                                  || ls_sqlerrm
                                 );
   END edu_pr_valid_progr_detal_dicta;


/***************************************************************************
Descripcion       : Funcion que devuelve el codigo de planilla de programacion final
Fecha Creacion    : 09/05/2008
Autor             : Sergio Buchelli
Parametros:
          psCodigoPais  : Codigo de Pais
          psCodEmpresa  : Codigo de Empresa
          psCodCurso    : Codigo de Curso
          psCampanha    : Periodo
          psCodRegion   : Codigo Region
          psUsuario			: Usuario
***************************************************************************/
FUNCTION edu_fn_devue_codig_plani_final(
  psCodigoPais  VARCHAR2,
  psCodEmpresa  VARCHAR2,
  psCodCurso    VARCHAR2,
  psCampanha     VARCHAR2,
  psCodRegion    VARCHAR2,
  psUsuario     VARCHAR2
)
RETURN VARCHAR2
IS
 lscodplanilla   edu_contr_gener_plani.cod_plan_prog_fina%TYPE:='1';
BEGIN

   SELECT MAX(TO_NUMBER(COD_PLAN_PROG_FINA))+1 INTO lscodplanilla
	   FROM EDU_CONTR_GENER_PLANI
	   WHERE PAIS_COD_PAIS = psCodigoPais
     AND EMCO_COD_EMPR_COME = psCodEmpresa
     AND CCAP_COD_CURS_CAPA =psCodCurso;

		INSERT INTO EDU_CONTR_GENER_PLANI (
		   PAIS_COD_PAIS, EMCO_COD_EMPR_COME, CCAP_COD_CURS_CAPA,
		   PERI_COD_PERI, REGI_COD_REGI, COD_PLAN_PROG_INIC,
		   COD_PLAN_PROG_FINA, USU_DIGI, FEC_DIGI, EST_REGI)
		VALUES (psCodigoPais ,psCodEmpresa ,psCodCurso ,psCampanha
		    ,psCodRegion , lscodplanilla,lscodplanilla,psUsuario ,SYSDATE,'1');

 RETURN lscodplanilla;

EXCEPTION
	 WHEN DUP_VAL_ON_INDEX THEN

 SELECT TO_NUMBER(COD_PLAN_PROG_FINA) INTO lscodplanilla
	   FROM EDU_CONTR_GENER_PLANI
	   WHERE PAIS_COD_PAIS = psCodigoPais
     AND EMCO_COD_EMPR_COME = psCodEmpresa
	 AND PERI_COD_PERI= psCampanha
	 AND REGI_COD_REGI= psCodRegion
     AND CCAP_COD_CURS_CAPA =psCodCurso;

	  --ANHADIMOS A ESA PLANILLA
    UPDATE EDU_CONTR_GENER_PLANI
     SET COD_PLAN_PROG_FINA = lscodplanilla,
         USU_MODI = psUsuario,     --CAMPOS DE AUDITORIA
         FEC_MODI = SYSDATE
   	WHERE PAIS_COD_PAIS = psCodigoPais
     AND EMCO_COD_EMPR_COME = psCodEmpresa
     AND CCAP_COD_CURS_CAPA =psCodCurso
     AND PERI_COD_PERI = psCampanha
     AND REGI_COD_REGI = psCodRegion;

  RETURN lscodplanilla;
END edu_fn_devue_codig_plani_final;


/***************************************************************************
Descripcion       : Funcion que devuelve el codigo de instructora dada una region
Fecha Creacion    : 09/05/2008
Autor             : Sergio Buchelli
Parametros:
          psCodigoPais  : Codigo de Pais
          psCodEmpresa  : Codigo de Empresa
          psCodPeriodo  : Codigo de Region
***************************************************************************/
   FUNCTION edu_fn_devue_codig_instr (
      pscodigopais   VARCHAR2,
      pscodempresa   VARCHAR2,
      pscodregion     VARCHAR2
   )
      RETURN VARCHAR2
   IS
      lscodiginstr   edu_regio.inst_cod_inst%TYPE;
   BEGIN
     SELECT A.INST_COD_INST INTO lscodiginstr
	 FROM EDU_REGIO A
	 WHERE A.PAIS_COD_PAIS=pscodigopais
	  AND A.EMCO_COD_EMPR_COME=pscodempresa
	  AND A.COD_REGI=pscodregion;

      RETURN lscodiginstr;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         ln_sqlcode := SQLCODE;
         ls_sqlerrm := 'NOT EXIST INSTRUCT';
         raise_application_error (-20123,
                                     'ERROR EDU_FN_DEVUE_CODIG_INSTR: '
                                  || ls_sqlerrm
                                 );
      WHEN OTHERS
      THEN
         ln_sqlcode := SQLCODE;
         ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
         raise_application_error (-20123,
                                     'ERROR EDU_PR_CALIF_APTAS_AUTOM: '
                                  || ls_sqlerrm
                                 );


   END edu_fn_devue_codig_instr;


/***************************************************************************************************
 Descripcion       : Procedimiento que registra Consultoras con Pedidos Extemporaneos
 Fecha Creacion      : 22/05/2008
 Parametros Entrada  : psCodPais       : Codigo del Pais del Usuario
                     : psCodEmpresa    : Código de Empresa del Usuario Logeado
                     : psCodPeriodo    : Campanha de Proceso
                     : psCodRegion     : Código de Region
 Parametro Salida     : psMensajeError   :Mensaje error
 Autor               : Sergio Buchelli
****************************************************************************************************/
PROCEDURE EDU_PR_REGIS_CONSU_PEDID_EXTEM (
    psCodPais       VARCHAR2,
    psCodEmpresa    VARCHAR2,
    psCodPeriodo    VARCHAR2,
    psCodRegion     VARCHAR2,
    psUsuario       VARCHAR2,
	psMensajeError   OUT VARCHAR2
)IS
 TYPE tRegTemporal IS RECORD (
	   CLIE_COD_CLIE        EDU_HISTO_CALIF_APTAS.CLIE_COD_CLIE%TYPE,
	   EST_CAPA             EDU_HISTO_CALIF_APTAS.EST_CAPA%TYPE,
	   NUM_INVI             EDU_HISTO_CALIF_APTAS.NUM_INVI%TYPE,
	   COD_PLAN_PROG        EDU_HISTO_PLANI_INSTR.COD_PLAN_PROG%TYPE,
	   COD_REGI 			EDU_MAEST_CLIEN.COD_REGI%TYPE,
	   CCAP_COD_CURS_CAPA   EDU_HISTO_APTAS.CCAP_COD_CURS_CAPA%TYPE,
	   CAM_ULTI_CALI_APTA   EDU_HISTO_APTAS.CAM_ULTI_CALI_APTA%TYPE,
	   IND_CURS_COST		EDU_HISTO_APTAS.IND_CURS_COST%TYPE,
	   IND_CURS_FACT		EDU_HISTO_APTAS.IND_CURS_FACT%TYPE,
	   CAM_FACT_CURS		EDU_HISTO_APTAS.CAM_FACT_CURS%TYPE,
	   CAM_ACEP				EDU_HISTO_APTAS.CAM_ACEP%TYPE
   );

  TYPE TABLA_TEMPORAL IS TABLE OF tRegTemporal;
  tablaRegistro          TABLA_TEMPORAL;
  regRegistro            tRegTemporal;

 CURSOR cursorRegistro (codigoPais VARCHAR2 ,codigoEmpresa VARCHAR2, codigoRegion VARCHAR2 , codigoPeriodo VARCHAR2)
  IS
   SELECT A.CLIE_COD_CLIE, A.EST_CAPA, A.NUM_INVI, C.COD_PLAN_PROG,B.COD_REGI,A.CCAP_COD_CURS_CAPA,A.CAM_ULTI_CALI_APTA,
   		  A.IND_CURS_COST,A.IND_CURS_FACT,A.CAM_FACT_CURS,A.CAM_ACEP
				FROM EDU_HISTO_APTAS A, EDU_MAEST_CLIEN B, EDU_HISTO_PLANI_INSTR C
				WHERE A.PAIS_COD_PAIS = codigoPais
				 AND A.EMCO_COD_EMPR_COME=codigoEmpresa
				 AND A.CAM_ULTI_CALI_APTA = codigoPeriodo
				 AND	  A.EST_CAPA ='PP'
				 AND	  C.SIT_PLAN_PROG = 'A'
				 AND  ( codigoRegion IS NULL OR B.COD_REGI=codigoRegion)
				AND	  A.PAIS_COD_PAIS = B.PAIS_COD_PAIS
				AND   A.EMCO_COD_EMPR_COME = B.EMCO_COD_EMPR_COME
				AND	  A.CLIE_COD_CLIE = B.COD_CLIE
				AND	  A.PAIS_COD_PAIS = C.PAIS_COD_PAIS
				AND	  A.EMCO_COD_EMPR_COME = C.EMCO_COD_EMPR_COME
				AND	  A.CCAP_COD_CURS_CAPA = C.CCAP_COD_CURS_CAPA
				AND	  B.COD_REGI = C.REGI_COD_REGI
				AND	  B.COD_ZONA = C.ZONA_COD_ZONA;
	-- ORDER BY B.COD_REGI, B.COD_ZONA, A.CLIE_COD_CLIE;

lsPeriodo		 EDU_CONTR_GENER_PLANI.PERI_COD_PERI%TYPE;
lsCampActual	 EDU_CONTR_GENER_PLANI.PERI_COD_PERI%TYPE;
regParamPrograma          EDU_PARAM_PROGR_CAPAC%ROWTYPE;
vsCodEmpresa EDU_EMPRE_COMER.COD_EMPR_COME%TYPE;
BEGIN

 vsCodEmpresa := psCodEmpresa;

 IF psCodEmpresa IS NULL THEN

    SELECT COD_EMPR_COME
    INTO vsCodEmpresa
    FROM EDU_EMPRE_COMER
    WHERE PAIS_COD_PAIS = psCodPais;

 END IF;

 SELECT *
    INTO regParamPrograma
    FROM EDU_PARAM_PROGR_CAPAC A
    WHERE A.PAIS_COD_PAIS = psCodPais
 AND A.EMCO_COD_EMPR_COME = vsCodEmpresa
      AND A.COD_PROG_CAPA = '01';

    /* Si esta activado el indicador de registro de Consultoras  con pedidos extemporaneos */
    IF (regParamPrograma.ind_cons_pedi_exte = INDICADOR_ACTIVO) THEN

		/* Obtenemos la campaña de procesos*/
		lsCampActual:=EDU_PKG_CALIF.EDU_FN_DEVUE_CAMPA_PROCE_ACTUA(psCodPais,vsCodEmpresa);

		OPEN cursorRegistro(psCodPais,vsCodEmpresa,psCodRegion,lsCampActual);
		  LOOP
		      FETCH cursorRegistro BULK COLLECT INTO tablaRegistro LIMIT 1000;
		      IF tablaRegistro.COUNT > 0 THEN
		        FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
		          regRegistro := tablaRegistro(x);


				  	  /*OBTENEMOS EL PERIDO DE LA PLANILA PARA ESA REGION*/

						SELECT A.PERI_COD_PERI INTO lsPeriodo
						FROM EDU_CONTR_GENER_PLANI A
						WHERE A.PAIS_COD_PAIS=psCodPais
						AND A.EMCO_COD_EMPR_COME=vsCodEmpresa
						AND A.REGI_COD_REGI=regRegistro.COD_REGI
						AND A.CCAP_COD_CURS_CAPA=regRegistro.CCAP_COD_CURS_CAPA
						AND TO_NUMBER(regRegistro.COD_PLAN_PROG) BETWEEN TO_NUMBER(COD_PLAN_PROG_INIC) AND TO_NUMBER(COD_PLAN_PROG_FINA);

					    /* Obtenemos la campaña de procesos*/
						--lsCampActual:=EDU_PKG_CALIF.EDU_FN_DEVUE_CAMPA_PROCE_ACTUA(psCodPais,vsCodEmpresa);

						IF lsPeriodo = lsCampActual THEN
						BEGIN
							INSERT INTO EDU_PLANI_PROGR_CURSO ( PAIS_COD_PAIS, EMCO_COD_EMPR_COME, CCAP_COD_CURS_CAPA,
							CLIE_COD_CLIE, CAM_PROC, COD_PLAN_PROG, TIP_CALI_APTA, NUM_INVI, IND_CURS_COST, IND_CURS_FACT,
							CAM_FACT_CURS, USU_DIGI, FEC_DIGI, USU_MODI, FEC_MODI, EST_REGI)
							VALUES(psCodPais,vsCodEmpresa,regRegistro.CCAP_COD_CURS_CAPA,regRegistro.CLIE_COD_CLIE,
									regRegistro.CAM_ULTI_CALI_APTA,regRegistro.COD_PLAN_PROG,'R',regRegistro.NUM_INVI,
									regRegistro.IND_CURS_COST,regRegistro.IND_CURS_FACT,regRegistro.CAM_FACT_CURS,
									psUsuario,SYSDATE,NULL,NULL,'1');
						EXCEPTION
						 WHEN DUP_VAL_ON_INDEX THEN
						    UPDATE EDU_PLANI_PROGR_CURSO
								SET NUM_INVI=regRegistro.NUM_INVI,
									COD_PLAN_PROG = regRegistro.COD_PLAN_PROG,
									USU_MODI = psUsuario,
									FEC_MODI = SYSDATE
							WHERE PAIS_COD_PAIS=psCodPais
							 AND EMCO_COD_EMPR_COME=vsCodEmpresa
							 AND CCAP_COD_CURS_CAPA=regRegistro.CCAP_COD_CURS_CAPA
							 AND CLIE_COD_CLIE=regRegistro.CLIE_COD_CLIE
							 AND CAM_PROC=regRegistro.CAM_ULTI_CALI_APTA;
						END;

								/*ACTULAIZAMOS EL HA*/

								UPDATE EDU_HISTO_APTAS A
								SET A.EST_CAPA = 'PR',
								     A.ULT_CAMP_PROG_DICT = lsPeriodo,
									 A.COD_PLAN_PROG = regRegistro.COD_PLAN_PROG,
									 A.USU_MODI=psUsuario,
									 A.FEC_MODI=SYSDATE
								WHERE  A.PAIS_COD_PAIS = psCodPais
								AND A.EMCO_COD_EMPR_COME=vsCodEmpresa
								AND	   A.EST_CAPA = 'PP'
								AND    A.CCAP_COD_CURS_CAPA=regRegistro.CCAP_COD_CURS_CAPA
								AND	   A.CLIE_COD_CLIE = regRegistro.CLIE_COD_CLIE;
					 END IF;--FIN DEL IF  CAMP ACTUAL IGUAL AL PERIDOO
		         END LOOP;
		      END IF;
		      EXIT WHEN cursorRegistro%NOTFOUND;
		  END LOOP;
		  CLOSE cursorRegistro;
   end if;

EXCEPTION
      WHEN OTHERS
      THEN
         ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
         raise_application_error (-20123,
                                  'EDU_PR_REGIS_CONSU_PEDID_EXTEM: ' || ls_sqlerrm
                                 );
END EDU_PR_REGIS_CONSU_PEDID_EXTEM;


/***************************************************************************
Descripcion       : Procedimiento que registra las planillas no procesadas
                    Determina las regiones y luego hace el cierre de planillas
                    en cada region
Fecha Creacion    : 27/05/2008
Autor             : Rafael Romero
Modificado		  : Sergio Buchelli
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psCodPeriodo : Codigo de Periodo (Campaña de Proceso)
			psNumPedidoMinimos : Numerod epedidos minimos para procesar la region
			 psUsuario    : Código Usuario
            psmensajeerror : Mensaje de retorno
***************************************************************************/
PROCEDURE EDU_PR_REGIS_PLANI_NPROC(
 psCodPais                  VARCHAR2,
 psCodEmpresa               VARCHAR2,
 psCodPeriodo               VARCHAR2,
 psNumPedidosMinimo			VARCHAR2,
 psUsuario                  VARCHAR2,
 psmensajeerror   OUT   VARCHAR2
)
IS
    TYPE r_cabPlanilla IS RECORD (
        codinstr edu_histo_plani_instr.inst_cod_inst%TYPE,
        codplani edu_histo_plani_instr.cod_plan_prog%TYPE,
        codregio edu_histo_plani_instr.regi_cod_regi%TYPE,
        codzona edu_histo_plani_instr.zona_cod_zona%TYPE,
        codCurso edu_histo_plani_instr.CCAP_COD_CURS_CAPA%TYPE
    );
    TYPE t_cabPlanilla IS TABLE OF r_cabPlanilla;
    TYPE t_codclien IS TABLE OF edu_plani_progr_curso.clie_cod_clie%TYPE;
    TYPE t_regiones IS TABLE OF EDU_MAEST_CLIEN.COD_REGI%TYPE;
    lt_cabPlanilla        t_cabPlanilla;
    lt_codclien           t_codclien;
    lt_regiones  t_regiones;
    lscodplani          edu_histo_plani_instr.cod_plan_prog%TYPE;
    lscampaplanilla     VARCHAR2 (6);
    lsmensajeerror      VARCHAR2 (250);
    lscodcursodictado   VARCHAR2 (6);
    lscodlocal          edu_local.cod_loca%TYPE;
    lscodcurso          edu_histo_plani_instr.ccap_cod_curs_capa%TYPE;
    lscodinstr          edu_histo_plani_instr.inst_cod_inst%TYPE;
    lsMsgRetorno VARCHAR2(100);
    i NUMBER;

    CURSOR cplanilla (vscodperiodoplani VARCHAR2, vsCodRegion VARCHAR2)
    IS
       SELECT   a.inst_cod_inst, a.cod_plan_prog,
                a.regi_cod_regi, a.zona_cod_zona,
                a.CCAP_COD_CURS_CAPA
           FROM edu_histo_plani_instr a,
                (SELECT   *
                     FROM edu_contr_gener_plani a1
                    WHERE a1.peri_cod_peri = vscodperiodoplani
                      AND a1.pais_cod_pais = psCodPais
                      AND a1.emco_cod_empr_come = psCodEmpresa
                      AND a1.regi_cod_regi = vsCodRegion
                 ORDER BY a1.pais_cod_pais, TO_NUMBER (a1.cod_plan_prog_inic)) b
          WHERE a.pais_cod_pais = psCodPais
            AND a.emco_cod_empr_come = psCodEmpresa
            AND a.sit_plan_prog = 'A'
            AND a.inst_cod_inst IS NOT NULL
            AND a.pais_cod_pais = b.pais_cod_pais
            AND a.emco_cod_empr_come = b.emco_cod_empr_come
            AND a.ccap_cod_curs_capa = b.ccap_cod_curs_capa
            AND a.regi_cod_regi = b.regi_cod_regi
            AND TO_NUMBER (a.cod_plan_prog)
                   BETWEEN TO_NUMBER (b.cod_plan_prog_inic)
                       AND TO_NUMBER (b.cod_plan_prog_fina)
       ORDER BY a.ccap_cod_curs_capa, a.inst_cod_inst, a.cod_plan_prog;

    CURSOR cplanilladetalle (vscodplanilla VARCHAR2, vsCodCurso VARCHAR2)
    IS
       SELECT a.clie_cod_clie
         FROM edu_plani_progr_curso a, edu_maest_clien b, edu_histo_aptas c
        WHERE a.pais_cod_pais = psCodPais
          AND a.emco_cod_empr_come = pscodempresa
          AND a.ccap_cod_curs_capa = vsCodCurso
          AND a.cod_plan_prog = vscodplanilla
          AND b.pais_cod_pais = a.pais_cod_pais
          AND b.emco_cod_empr_come = a.emco_cod_empr_come
          AND b.cod_clie = a.clie_cod_clie
          AND c.pais_cod_pais = a.pais_cod_pais
          AND c.emco_cod_empr_come = a.emco_cod_empr_come
          AND c.ccap_cod_curs_capa = a.ccap_cod_curs_capa
          AND c.clie_cod_clie = a.clie_cod_clie;

  --- CON NUM_PEDIDOS MINIMOS > 0
    CURSOR cRegiones IS
		 SELECT   c.cod_regi
		   FROM  edu_tmp_pedid_consu c
		   WHERE c.COD_PAIS = psCodPais
		     AND c.COD_EMPR_COME = psCodEmpresa
		     AND c.cam_proc = psCodPeriodo
		   GROUP BY c.cod_regi
		   HAVING COUNT (1) >= TO_NUMBER (psNumPedidosMinimo);

   -- SE TOMAN LAS REGIONES DEL GTT SOLO SI NUMERO PEDIDOS MINIMOS = 0 PARA COLOMBIA
    CURSOR cRegionesGtt IS
		 SELECT  c.cod_regi
		   FROM  EDU_GTT_REGIO_CIERR c
		   WHERE C.COD_PAIS = psCodPais
		     AND C.COD_EMPR_COME =psCodEmpresa;


BEGIN

 IF(psNumPedidosMinimo > 0) THEN
    OPEN cRegiones;
    LOOP
       FETCH cRegiones
           BULK COLLECT INTO lt_regiones LIMIT w_filas;
       EXIT WHEN lt_regiones.COUNT = 0;

       FOR i IN 1 .. lt_regiones.COUNT
       LOOP
            lscodlocal := NULL;
            lscampaplanilla := edu_pkg_proce_gener.edu_fn_devue_codig_perio (pscodperiodo, -1);

            lscodcurso := 'XXX';
            lscodinstr := 'XXX';
            lscodcursodictado := '-1';

            OPEN cplanilla (lscampaplanilla, lt_regiones(i));

            LOOP
              FETCH cplanilla
              BULK COLLECT INTO lt_cabPlanilla LIMIT w_filas;

              EXIT WHEN lt_cabPlanilla.COUNT = 0;

              FOR x IN lt_cabPlanilla.FIRST .. lt_cabPlanilla.LAST
              LOOP
                 IF (lscodinstr <> lt_cabPlanilla(x).codinstr OR lscodcurso <> lt_cabPlanilla(x).codCurso)
                 THEN
                    lscodcurso := lt_cabPlanilla(x).codCurso;
                    lscodinstr := lt_cabPlanilla(x).codinstr;
                    lscodcursodictado := edu_fn_sigte_codig_curso_dicta (psCodPais, pscodempresa, lsCodCurso);

              		--El local antes se obtenia , ahora se decidio ingresar null 06/10/08
                      lscodlocal:='';

                    /* Insertando en cabecera Curso Dictado */
                    INSERT INTO edu_histo_curso_dicta_cabec
                                (pais_cod_pais, emco_cod_empr_come,
                                 ccap_cod_curs_capa, cod_curs_dict,
                                 inst_cod_inst, cam_inic_curs, fec_inic_curs,
                                 num_real_dura_sesi, cod_regi, cod_zona,
                                 lug_capa, cat_luga_capa, ind_eval_curs,
                                 cal_prom_curs_dict, est_curs_dict, usu_digi,
                                 fec_digi, est_regi, loca_cod_loca,
                                 sala_cod_sala,SIST_COD_SIST
                                )
                         VALUES (psCodPais, pscodempresa,
                                 lsCodCurso, lscodcursodictado,
                                 lscodinstr, pscodperiodo, TRUNC (SYSDATE),
                                 1, lt_cabPlanilla(x).codregio, lt_cabPlanilla(x).codzona,
                                 NULL, '',                       --EN VEZ DE LO
                                           'N',
                                 NULL, 'C', psusuario,
                                 SYSDATE, '1', lscodlocal,
                                 NULL,'EDU'
                                );
                 END IF;

                 /* Recorriendo lista de Consultoras asociadas */
                 lscodplani := lt_cabPlanilla(x).codplani;

                 OPEN cplanilladetalle (lscodplani, lsCodCurso);

                 LOOP
                      FETCH cplanilladetalle
                      BULK COLLECT INTO lt_codclien LIMIT w_filas;

                      EXIT WHEN lt_codclien.COUNT = 0;

                      /* Insertando en Detalle Curso Dictado */

                      FORALL y IN 1 .. lt_codclien.COUNT
                         INSERT INTO edu_histo_curso_dicta_detal
                                     (pais_cod_pais, emco_cod_empr_come,
                                      ccap_cod_curs_capa, cdic_cod_curs_dict,
                                      clie_cod_clie, cod_plan_prog,
                                      ind_asis, cal_cons, cal_inst, usu_digi,
                                      fec_digi, est_regi
                                     )
                              VALUES (psCodPais, pscodempresa,
                                      lsCodCurso, lscodcursodictado,
                                      lt_codclien(y), lscodplani,
                                      indicador_no, 0.00, NULL, psusuario,
                                      SYSDATE, '1'
                                     );

                 END LOOP;

                 CLOSE cplanilladetalle;

                 -- AGREGAR ACTUALIZACON HISTORICO PLANILLA INSTRUCTORA, INICIAL PLANILLAS - CONSULTORAS
                 -- CURSO DICTADO
                 -- SIT PLANILL PROCESADA
                 -- AUDITORIA
                 UPDATE edu_histo_plani_instr a
                    SET cdic_cod_curs_dict = lscodcursodictado,
                        sit_plan_prog = 'P',
                        fec_modi = SYSDATE,
                        usu_modi = psusuario
                  WHERE a.pais_cod_pais = psCodPais
                    AND a.emco_cod_empr_come = pscodempresa
                    AND a.ccap_cod_curs_capa = lscodcurso
                    AND a.cod_plan_prog = lt_cabPlanilla(x).codplani;
              END LOOP;

            END LOOP;

            CLOSE cplanilla;

	   END LOOP;

    END LOOP;
    CLOSE cRegiones;

  ELSE
    --CASO DEL QUE NUM_PEDIOS SEA CERO SE TOMA EL CURSOR DE REGIONES DEL GTT

	OPEN cRegionesGtt;
    LOOP
       FETCH cRegionesGtt
           BULK COLLECT INTO lt_regiones LIMIT w_filas;
       EXIT WHEN lt_regiones.COUNT = 0;

       FOR i IN 1 .. lt_regiones.COUNT
       LOOP
            lscodlocal := NULL;
            lscampaplanilla := edu_pkg_proce_gener.edu_fn_devue_codig_perio (pscodperiodo, -1);

            lscodcurso := 'XXX';
            lscodinstr := 'XXX';
            lscodcursodictado := '-1';

            OPEN cplanilla (lscampaplanilla, lt_regiones(i));

            LOOP
              FETCH cplanilla
              BULK COLLECT INTO lt_cabPlanilla LIMIT w_filas;

              EXIT WHEN lt_cabPlanilla.COUNT = 0;

              FOR x IN lt_cabPlanilla.FIRST .. lt_cabPlanilla.LAST
              LOOP
                 IF (lscodinstr <> lt_cabPlanilla(x).codinstr OR lscodcurso <> lt_cabPlanilla(x).codCurso)
                 THEN
                    lscodcurso := lt_cabPlanilla(x).codCurso;
                    lscodinstr := lt_cabPlanilla(x).codinstr;
                    lscodcursodictado := edu_fn_sigte_codig_curso_dicta (psCodPais, pscodempresa, lsCodCurso);

              		--El local antes se obtenia , ahora se decidio ingresar null 06/10/08
					lscodlocal:='';

                    /* Insertando en cabecera Curso Dictado */
                    INSERT INTO edu_histo_curso_dicta_cabec
                                (pais_cod_pais, emco_cod_empr_come,
                                 ccap_cod_curs_capa, cod_curs_dict,
                                 inst_cod_inst, cam_inic_curs, fec_inic_curs,
                                 num_real_dura_sesi, cod_regi, cod_zona,
                                 lug_capa, cat_luga_capa, ind_eval_curs,
                                 cal_prom_curs_dict, est_curs_dict, usu_digi,
                                 fec_digi, est_regi, loca_cod_loca,
                                 sala_cod_sala,SIST_COD_SIST
                                )
                         VALUES (psCodPais, pscodempresa,
                                 lsCodCurso, lscodcursodictado,
                                 lscodinstr, pscodperiodo, TRUNC (SYSDATE),
                                 1, lt_cabPlanilla(x).codregio, lt_cabPlanilla(x).codzona,
                                 NULL, '',                       --EN VEZ DE LO
                                           'N',
                                 NULL, 'C', psusuario,
                                 SYSDATE, '1', lscodlocal,
                                 NULL,'EDU'
                                );
                 END IF;

                 /* Recorriendo lista de Consultoras asociadas */
                 lscodplani := lt_cabPlanilla(x).codplani;

                 OPEN cplanilladetalle (lscodplani, lsCodCurso);

                 LOOP
                      FETCH cplanilladetalle
                      BULK COLLECT INTO lt_codclien LIMIT w_filas;

                      EXIT WHEN lt_codclien.COUNT = 0;

                      /* Insertando en Detalle Curso Dictado */

                      FORALL y IN 1 .. lt_codclien.COUNT
                         INSERT INTO edu_histo_curso_dicta_detal
                                     (pais_cod_pais, emco_cod_empr_come,
                                      ccap_cod_curs_capa, cdic_cod_curs_dict,
                                      clie_cod_clie, cod_plan_prog,
                                      ind_asis, cal_cons, cal_inst, usu_digi,
                                      fec_digi, est_regi
                                     )
                              VALUES (psCodPais, pscodempresa,
                                      lsCodCurso, lscodcursodictado,
                                      lt_codclien(y), lscodplani,
                                      indicador_no, 0.00, NULL, psusuario,
                                      SYSDATE, '1'
                                     );

                 END LOOP;

                 CLOSE cplanilladetalle;

                 -- AGREGAR ACTUALIZACON HISTORICO PLANILLA INSTRUCTORA, INICIAL PLANILLAS - CONSULTORAS
                 -- CURSO DICTADO
                 -- SIT PLANILL PROCESADA
                 -- AUDITORIA
                 UPDATE edu_histo_plani_instr a
                    SET cdic_cod_curs_dict = lscodcursodictado,
                        sit_plan_prog = 'P',
                        fec_modi = SYSDATE,
                        usu_modi = psusuario
                  WHERE a.pais_cod_pais = psCodPais
                    AND a.emco_cod_empr_come = pscodempresa
                    AND a.ccap_cod_curs_capa = lscodcurso
                    AND a.cod_plan_prog = lt_cabPlanilla(x).codplani;
              END LOOP;

            END LOOP;

            CLOSE cplanilla;

	   END LOOP;

    END LOOP;
    CLOSE cRegionesGtt;
  END IF;	--fin del if

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_REGIS_PLANI_NPROC:' || ls_sqlerrm);
END EDU_PR_REGIS_PLANI_NPROC;



/***************************************************************************
Descripcion       : Proceso previo antes de la regularizacion de asistencia
										se encarga de poner a 'PR'  a las consultoras que se quiere regularizar
										estas se encuantran en PC O PR
Fecha Creacion    : 05/06/2008
Autor             : Sergio Buchelli Silva
Parametros:
          psCodigoPais    : Codigo de Pais
          psCodigoEmpresa    : Codigo de Empresa
          psCodigoCurso      : Codigo de Curso
			    psCodigoConsultora : Codigo de Consultora
		  psTipo : 1:Regularizcion 0:Eliminacion de Asistencia
			    psUsuario       : Usuario de Proceso
***************************************************************************/

PROCEDURE EDU_PR_PREVI_REGUL_ASIST(
    psCodigoPais        VARCHAR2,
    psCodigoEmpresa     VARCHAR2,
		psCodigoCurso       VARCHAR2,
    psCodigoConsultora  VARCHAR2,
	psTipo				VARCHAR2,
    psUsuario           VARCHAR2
    ) IS
   ls_sqlerrm         VARCHAR2(150);
   lsUltimaProgDict		EDU_HISTO_APTAS.ULT_CAMP_PROG_DICT%TYPE;
   lnNumInvitacion    NUMBER;
   lnNumCampPrev	  NUMBER;
   lsIndCursoCost	  VARCHAR2(1);
   lsIndCursFact 	  VARCHAR2(6);
   lsCamFactu		  VARCHAR2(6);
   lsCamAceptacion	  VARCHAR2(6);
BEGIN
 /*
 SE OBTIENE EL PARAMETRO CURSO QUE NOS DICE LA CAMPANHA PREVIA CALIFICACION

 */

 SELECT A.NUM_CAMP_PREV_CALI INTO lnNumCampPrev
 FROM EDU_PARAM_CURSO_CAPAC A
	WHERE A.PAIS_COD_PAIS = psCodigoPais
    AND  A.EMCO_COD_EMPR_COME = psCodigoEmpresa
		AND A.COD_CURS_CAPA = psCodigoCurso;


  SELECT ULT_CAMP_PROG_DICT INTO lsUltimaProgDict
  FROM EDU_HISTO_APTAS A
	WHERE A.PAIS_COD_PAIS = psCodigoPais
    AND  A.EMCO_COD_EMPR_COME = psCodigoEmpresa
		AND A.CCAP_COD_CURS_CAPA = psCodigoCurso
		AND A.CLIE_COD_CLIE = psCodigoConsultora;

 BEGIN
  SELECT A.NUM_INVI,A.IND_CURS_COST into lnNumInvitacion,lsIndCursoCost
  FROM EDU_HISTO_CALIF_APTAS A
	WHERE A.PAIS_COD_PAIS = psCodigoPais
     AND  A.EMCO_COD_EMPR_COME = psCodigoEmpresa
		AND A.CCAP_COD_CURS_CAPA = psCodigoCurso
		AND A.CAM_PROC =  EDU_PKG_PROCE_COMER.GEN_FN_DEVUE_NSGTE_CAMPA(lsUltimaProgDict,-1*(lnNumCampPrev-1))
		AND A.CLIE_COD_CLIE = psCodigoConsultora;
 EXCEPTION
   WHEN OTHERS THEN --HAY CASOS QUE HAN SIDO CALIFCADAS CON NUM_CAMP_PREVIAS A 2 y lo han cambiado en el camino y se quiere
      --regularizar y siempre saldra el error debido a que esta buscando en el perodo incorrecto
	  BEGIN
      SELECT A.NUM_INVI,A.IND_CURS_COST into lnNumInvitacion,lsIndCursoCost
      FROM EDU_HISTO_CALIF_APTAS A
	  WHERE A.PAIS_COD_PAIS = psCodigoPais
       AND  A.EMCO_COD_EMPR_COME = psCodigoEmpresa
		AND A.CCAP_COD_CURS_CAPA = psCodigoCurso
		AND A.CAM_PROC =  EDU_PKG_PROCE_COMER.GEN_FN_DEVUE_NSGTE_CAMPA(lsUltimaProgDict,-1)
		AND A.CLIE_COD_CLIE = psCodigoConsultora;
	   EXCEPTION
	    WHEN OTHERS THEN
		  lnNumInvitacion:=1;--ERROR DE MARCA EN LA REGULARIZACION
		  lsIndCursoCost:='N';--ERROR DE MARCA EN LA REGULARIZACION
	   END;


 END;

IF(psTipo='1') THEN --REGULARIZACION
 IF(lsIndCursoCost='S') THEN

  UPDATE EDU_HISTO_APTAS A
  SET A.CAM_ULTI_CALI_APTA =  A.ULT_CAMP_PROG_DICT,
      A.EST_CAPA = 'PR',
	  A.IND_CURS_COST = lsIndCursoCost,
	  A.NUM_INVI = lnNumInvitacion,
	  A.USU_MODI = psUsuario,
	  A.FEC_MODI = SYSDATE
	WHERE A.PAIS_COD_PAIS = psCodigoPais
     AND  A.EMCO_COD_EMPR_COME = psCodigoEmpresa
		AND A.CCAP_COD_CURS_CAPA = psCodigoCurso
		AND A.CLIE_COD_CLIE = psCodigoConsultora;

 ELSE--INDICADOR N

 	 UPDATE EDU_HISTO_APTAS A
     SET A.CAM_ULTI_CALI_APTA =  A.ULT_CAMP_PROG_DICT,
      A.EST_CAPA = 'PR',
	  A.IND_CURS_COST = lsIndCursoCost,
	  A.IND_CURS_FACT='',
	  A.CAM_FACT_CURS='',
	  A.CAM_ACEP='',
	  A.NUM_INVI = lnNumInvitacion,
	  A.USU_MODI = psUsuario,
	  A.FEC_MODI = SYSDATE
	WHERE A.PAIS_COD_PAIS = psCodigoPais
     AND  A.EMCO_COD_EMPR_COME = psCodigoEmpresa
		AND A.CCAP_COD_CURS_CAPA = psCodigoCurso
		AND A.CLIE_COD_CLIE = psCodigoConsultora;

 END IF;

	 UPDATE  EDU_HISTO_CALIF_APTAS A
	  SET A.EST_REGI = '9',
	  	  A.USU_MODI = psUsuario,
		  A.FEC_MODI = SYSDATE
	  WHERE A.PAIS_COD_PAIS = psCodigoPais
	    AND  A.EMCO_COD_EMPR_COME = psCodigoEmpresa
		AND A.CCAP_COD_CURS_CAPA = psCodigoCurso
		AND A.CLIE_COD_CLIE = psCodigoConsultora
		AND A.CAM_PROC >lsUltimaProgDict;

ELSE --ELIMINACION DE ASISTENCIA revertimos


    SELECT A.NUM_INVI,A.IND_CURS_COST,A.IND_CURS_FACT,A.CAM_FACT_CURS,
		    A.CAM_ACEP into lnNumInvitacion,lsIndCursoCost,lsIndCursFact,lsCamFactu,lsCamAceptacion
	FROM EDU_HISTO_CALIF_APTAS A
	WHERE A.PAIS_COD_PAIS=psCodigoPais
     AND  A.EMCO_COD_EMPR_COME = psCodigoEmpresa
	 AND A.CLIE_COD_CLIE=psCodigoConsultora
	  AND A.CCAP_COD_CURS_CAPA=psCodigoCurso
	  AND A.CAM_PROC = (
	  	  SELECT MAX(CAM_PROC)
			FROM EDU_HISTO_CALIF_APTAS X
			WHERE X.PAIS_COD_PAIS=psCodigoPais
		     AND  A.EMCO_COD_EMPR_COME = psCodigoEmpresa
			 AND X.CLIE_COD_CLIE=psCodigoConsultora
			  AND X.CCAP_COD_CURS_CAPA=psCodigoCurso);


		 UPDATE EDU_HISTO_APTAS A
	     SET A.CAM_ULTI_CALI_APTA =  A.ULT_CAMP_PROG_DICT,
	      A.EST_CAPA = 'PC',
		  A.IND_CURS_COST = lsIndCursoCost,
		  A.IND_CURS_FACT=lsIndCursFact,
		  A.CAM_FACT_CURS=lsCamFactu,
		  A.CAM_ACEP=lsCamAceptacion,
		  A.NUM_INVI = lnNumInvitacion,
		  A.USU_MODI = psUsuario,
		  A.FEC_MODI = SYSDATE
		WHERE A.PAIS_COD_PAIS = psCodigoPais
	     AND  A.EMCO_COD_EMPR_COME = psCodigoEmpresa
			AND A.CCAP_COD_CURS_CAPA = psCodigoCurso
			AND A.CLIE_COD_CLIE = psCodigoConsultora;



 UPDATE  EDU_HISTO_CALIF_APTAS A
  SET A.EST_REGI = '1',
  	  A.USU_MODI = psUsuario,
	  A.FEC_MODI = SYSDATE
  WHERE A.PAIS_COD_PAIS = psCodigoPais
    AND  A.EMCO_COD_EMPR_COME = psCodigoEmpresa
	AND A.CCAP_COD_CURS_CAPA = psCodigoCurso
	AND A.CLIE_COD_CLIE = psCodigoConsultora
	AND A.CAM_PROC >lsUltimaProgDict;

END IF;


  RETURN;
EXCEPTION
WHEN OTHERS THEN
      ls_sqlerrm := substr(sqlerrm,1,250);
      RAISE_APPLICATION_ERROR(-20123, 'EDU_PR_PREVI_REGUL_ASIST : '||ls_sqlerrm);

END EDU_PR_PREVI_REGUL_ASIST;

/***************************************************************************
Descripcion       : Proceso que se encarga d eactualizar la tabla de benefiicos, con la
				   consultora que se ha regularizado, para la campana de asistencia
Fecha Creacion    : 02/07/2008
Autor             : Sergio Buchelli Silva
Parametros:
          psCodigoPais    : Codigo de Pais
          psCodigoEmpresa    : Codigo de Empresa
		  psCampanaCapac	  :	Campana Capacitacion
          psCodigoCurso      : Codigo de Curso
		  psCodigoConsultora : Codigo de Consultora
  	  	  psIndConfirmarAsist : Indicador si e s una confirmacion  (1) o eliminacion (0 )
		   					  	de Asistencia,
		  psUsuario       : Usuario de Proceso
***************************************************************************/

PROCEDURE EDU_PR_ACTUA_BENEF_CONSU(
    psCodigoPais        VARCHAR2,
    psCodigoEmpresa     VARCHAR2,
    psCampanaCapac		VARCHAR2,
	psCodigoCurso       VARCHAR2,
    psCodigoConsultora  VARCHAR2,
	psIndConfirmarAsist VARCHAR2,
    psUsuario           VARCHAR2
)
IS
 -- obtine loas clasificaciones que hay para esa campanha d e capacitacion
   CURSOR  curClasif IS
       select
          dp.COD_CLAS   ,
          dp.NIV_CAPA_ALCA  ,
		  ca.cam_proc,
          dp.ERNI_COD_ESTA_RESP_NIVE ,
          dp.ASTT_COD_TIPO_ASTT_CURS
        from edu_param_clasi_benef_capac dp,edu_histo_clasi_benef_cabec ca
        where dp.pais_cod_pais = psCodigoPais and
              dp.emco_cod_empr_come = psCodigoEmpresa and
              -- se agrego el tipo de clasificacion, para este caso son  B: Beneficios
              dp.tip_clas = 'B' and
			  dp.NIV_CAPA_ALCA= psCodigoCurso and
 			  dp.ASTT_COD_TIPO_ASTT_CURS='R' and --la regularizacion los asiste como regular
              ca.pais_cod_pais = dp.pais_cod_pais and
              ca.emco_cod_empr_come = dp.emco_cod_empr_come and
              ca.clas_cod_clas = dp.cod_clas and
              ca.cam_proc = psCampanaCapac and
              ca.est_clas = 'A'  and
              dp.est_clas = 'A' ;

   ls_sqlerrm         VARCHAR2(150);
   lsUltimaProgDict		EDU_HISTO_APTAS.ULT_CAMP_PROG_DICT%TYPE;

   row_cursor curClasif%ROWTYPE;
BEGIN
    OPEN curClasif; -- open the cursor
    LOOP
    FETCH curClasif INTO row_cursor ;
    EXIT WHEN curClasif%notfound; -- exit condition
      begin
		 --POR CADA CLASIIFCACION LO ANHADIMOS EN LA TABLA DE BENEFICION
		 --SIEMPRE Y CUANDO SE CONFIRMAR ASISTENCIA CASO CONTRAIO SE ELMININA
		 IF ( psIndConfirmarAsist ='1') THEN
		  --SE INSERTA
		   BEGIN
		   		INSERT INTO EDU_HISTO_CLASI_BENEF_DETAL(
	             PAIS_COD_PAIS, EMCO_COD_EMPR_COME, CLAS_COD_CLAS, HBEC_CAM_PROC,
	             CLIE_COD_CLIE, NIV_CAPA_ALCA, CAM_DICT_CURS,
	             EST_RESP_NIVE, IND_ENVI,
	             USU_DIGI, FEC_DIGI, EST_REGI, IND_DESP_CLAS,NUM_DESP)
			   VALUES (psCodigoPais, psCodigoEmpresa, row_cursor.COD_CLAS, psCampanaCapac,
	              psCodigoConsultora, psCodigoCurso, psCampanaCapac,
	              row_cursor.ERNI_COD_ESTA_RESP_NIVE, 'N',
	              psUsuario, SYSDATE, '1', '0',0);

		   EXCEPTION
		    WHEN OTHERS THEN --REGISTRO YA EXISTE
			  NULL;
		   END;

		 ELSE
		   --SE ELIMINA
		    DELETE FROM EDU_HISTO_CLASI_BENEF_DETAL A
			 WHERE  PAIS_COD_PAIS= psCodigoPais
			   AND EMCO_COD_EMPR_COME = psCodigoEmpresa
			   AND CLAS_COD_CLAS = row_cursor.COD_CLAS
			   AND HBEC_CAM_proc = psCampanaCapac
			   AND CLIE_COD_CLIE = psCodigoConsultora;

		 END IF;

       end ;
    END LOOP;
    CLOSE curClasif;
  RETURN;
EXCEPTION
WHEN OTHERS THEN
      ls_sqlerrm := substr(sqlerrm,1,250);
      RAISE_APPLICATION_ERROR(-20123, 'EDU_PR_ACTUA_BENEF_CONSU : '||ls_sqlerrm);

END EDU_PR_ACTUA_BENEF_CONSU;


/***************************************************************************
Descripcion       : Funcion que devuelve el NOMBRE de instructora dado su codigo
Fecha Creacion    : 02/07/2008
Autor             : Sergio Buchelli
Parametros:
          psCodigoPais  : Codigo de Pais
          psCodEmpresa  : Codigo de Empresa
          psCodInstructora : Codigo de Instructora
***************************************************************************/
   FUNCTION edu_fn_devue_nombr_instr (
      pscodigopais   	   VARCHAR2,
      pscodempresa   	   VARCHAR2,
      psCodInstructora     VARCHAR2
   )
      RETURN VARCHAR2
   IS
      lsnombrinstr  VARCHAR2(200);
   BEGIN
     SELECT A.APE_PATE || ' ' || A.APE_MATE || ' ' || A.PRI_NOMB || ' ' || A.SEG_NOMB INTO lsnombrinstr
	 FROM EDU_MAEST_INSTR A
	 WHERE A.PAIS_COD_PAIS=pscodigopais
	  AND A.EMCO_COD_EMPR_COME=pscodempresa
	  AND A.COD_INST=psCodInstructora;

      RETURN lsnombrinstr;
   EXCEPTION
      WHEN OTHERS
      THEN
	      lsnombrinstr:='';

END edu_fn_devue_nombr_instr;



/***************************************************************************
Descripcion       : Funcion que devuelve la descripcion de la Sala
Fecha Creacion    : 02/07/2008
Autor             : Sergio Buchelli
Parametros:
          psCodigoPais  : Codigo de Pais
          psCodEmpresa  : Codigo de Empresa
          psCodLocal    : Codigo de Local

***************************************************************************/
   FUNCTION edu_fn_devue_descr_local (
      pscodigopais   VARCHAR2,
      pscodempresa   VARCHAR2,
      pscodlocal     VARCHAR2
   )
      RETURN VARCHAR2
   IS
      lsretorno   edu_local.des_loca%TYPE;
   BEGIN
      lsretorno := '';

      SELECT a.des_loca
        INTO lsretorno
        FROM edu_local a
       WHERE a.pais_cod_pais = pscodigopais
         AND a.emco_cod_empr_come = pscodempresa
         AND a.cod_loca = pscodlocal;

      RETURN lsretorno;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         RETURN '';
      WHEN OTHERS
      THEN
         ln_sqlcode := SQLCODE;
         ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
         raise_application_error (-20123,
                                     'ERROR EDU_FN_DEVUE_DESCR_LOCA: '
                                  || ls_sqlerrm
                                 );
   END edu_fn_devue_descr_local;

/***************************************************************************
Descripcion       : Funcion que devuelve la primera campaña de facturacion
				    de una consultora
Fecha Creacion    : 05/09/2008
Autor             : Dennys Oliva Iriarte
Parametros:
          psoidpais     : OID de Pais
          psoidcliente  : OID de Consultora

***************************************************************************/
FUNCTION edu_fn_devue_prime_campa_factu (
      psoidpais       NUMBER,
      psoidcliente    NUMBER
   )
   RETURN VARCHAR2
   IS
      lsretorno   seg_perio_corpo.COD_PERI%TYPE;
   BEGIN
		SELECT MIN(pcor.COD_PERI) cod
		INTO lsretorno
        FROM ped_solic_cabec psc,
		     cra_perio crp,
			 seg_perio_corpo pcor
        WHERE psc.pais_oid_pais = psoidpais
		  AND psc.CLIE_OID_CLIE = psoidcliente
          AND psc.ind_pedi_prue = 0
          AND psc.ind_oc = 1
		  AND psc.fec_fact IS NOT NULL
		  AND psc.perd_oid_peri = crp.oid_peri
          AND crp.peri_oid_peri = pcor.oid_peri;

      RETURN lsretorno;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         RETURN '';
      WHEN OTHERS
      THEN
         ln_sqlcode := SQLCODE;
         ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
         raise_application_error (-20123,
                                     'ERROR edu_fn_devue_prime_campa_factu: '
                                  || ls_sqlerrm
                                 );
   END edu_fn_devue_prime_campa_factu;

/***************************************************************************
Descripcion       : Proceso que se encarga de efectuar el proceso de exoneracion
Fecha Creacion    : 25/09/2008
Autor             : Dennys Oliva Iriarte
Parametros:
          psCodigoPais              : Codigo de Pais
          psCodigoEmpresa           : Codigo de Empresa
		  pscodigocliente           : Codigo de Consultora
		  pscurso                   : Curso
		  psusuario                 : Usuario
Parametros Salida   : psMensajeError: Mensaje de Error
***************************************************************************/
   PROCEDURE EDU_PR_EXONE_ASIST(
      pscodigopais            VARCHAR2,
      pscodigoempresa         VARCHAR2,
	    pscodigocliente         VARCHAR2,
	    pscurso                 VARCHAR2,
	    psusuario               VARCHAR2,
      psmensajeerror    OUT   VARCHAR2
   )
   IS

   lscampanhasaptas            EDU_HISTO_APTAS.CAM_CAPA%TYPE;
   lscampanhascalifaptas       EDU_HISTO_CALIF_APTAS.CAM_PROC%TYPE;
   lscampanhapedido            edu_histo_pedid_consu.CAM_PROC%TYPE;
   lscampactual                VARCHAR2(6);
   lscampcapac                 VARCHAR2(6);
   lscursodictado		           NUMBER;
   lscodigoplanilla            VARCHAR2(10);
   lstipo                      VARCHAR2 (2)          := 'NO';
   pscodigoasistentecurso      edu_tipo_asitt_curso.cod_tipo_astt_curs%TYPE;
   pscodigoasistenciacurso     edu_tipo_asist_curso.cod_tipo_asis_curs%TYPE;

	 ls_cam_prim_cali_apta  EDU_HISTO_APTAS.CAM_PRIM_CALI_APTA%TYPE;
   ls_cam_ulti_cali_apta  EDU_HISTO_APTAS.CAM_ULTI_CALI_APTA%TYPE;
   ls_tip_cali_apta       EDU_HISTO_APTAS.TIP_CALI_APTA%TYPE;
   ls_num_invi            EDU_HISTO_APTAS.NUM_INVI%TYPE;
   ls_ind_inic_cali_apta  EDU_HISTO_APTAS.IND_INIC_CALI_APTA%TYPE;
   ls_ind_fina_cali_apta  EDU_HISTO_APTAS.IND_FINA_CALI_APTA%TYPE;
   ls_ind_curs_cost       EDU_HISTO_APTAS.IND_CURS_COST%TYPE;
   --
 ls_ind_curs_fact		 EDU_HISTO_APTAS.IND_CURS_FACT%TYPE;
 ls_cam_fact			 EDU_HISTO_APTAS.CAM_FACT_CURS%TYPE;
   regParamCursos            EDU_PARAM_CURSO_CAPAC%ROWTYPE;
  BEGIN

	lscampactual := edu_pkg_calif.edu_fn_devue_campa_proce_actua (pscodigopais,pscodigoempresa);
	lscursodictado := edu_pkg_proce_gener.edu_fn_sigte_codig_curso_dicta (pscodigopais,
																		  pscodigoempresa,
																		  pscurso
																		  );
	lscodigoplanilla:='9999999999';
	pscodigoasistentecurso := 'E';
    pscodigoasistenciacurso := 'E';
	lscampcapac := lscampactual;
	psmensajeerror := '';

	 /* Obteniendo parametrizacion del curso */
   SELECT *
   INTO regParamCursos
   FROM EDU_PARAM_CURSO_CAPAC A
   WHERE A.PAIS_COD_PAIS = psCodigoPais
     AND A.EMCO_COD_EMPR_COME = pscodigoempresa
     AND A.COD_CURS_CAPA = pscurso
     AND A.EST_REGI = INDICADOR_ACTIVO;

	IF(regParamCursos.TICC_COD_TIPO_COST_CURS='03') THEN--MIXTO
	  ls_ind_curs_cost:='N';
	END IF;

	IF(regParamCursos.TICC_COD_TIPO_COST_CURS='02')THEN--COSTO
	  ls_ind_curs_cost:='S';
	END IF;

	IF(regParamCursos.TICC_COD_TIPO_COST_CURS='01')THEN --SIN COSTO
	  ls_ind_curs_cost:='N';
	END IF;

    BEGIN
	   select CAM_PROC INTO lscampanhapedido
	   from edu_histo_pedid_consu
       where PAIS_COD_PAIS = pscodigopais AND
             EMCO_COD_EMPR_COME = pscodigoempresa AND
             COD_CLIE = pscodigocliente;
	EXCEPTION
      WHEN no_data_found THEN
           lscampanhapedido := '';
      END;

	-- Veo si esta en la tabla  edu_histo_pedid_consu
	IF (lscampanhapedido is null) THEN
		INSERT INTO edu_histo_pedid_consu
	            (pais_cod_pais, emco_cod_empr_come,
	             cod_clie, cam_inic_pedi,
	             cam_ulti_pedi, cam_proc, cod_ulti_nive,
	             num_pedi_fact, ind_pedi, ind_fact, ind_nuev,
	             ind_cons, ind_prim_pedi, usu_digi, fec_digi,
	             est_regi
	            )
	    VALUES (pscodigopais, pscodigoempresa,
	             pscodigocliente, lscampactual,
	             lscampactual, lscampactual, '01',
	             0, '1', 0, 0,
	             0, 0, psusuario, SYSDATE,
	             '1'
	            );
	ELSE
		UPDATE edu_histo_pedid_consu pedcn
		SET    pedcn.cam_ulti_pedi = lscampactual,
			     pedcn.cam_proc = lscampactual,
		       usu_modi = psusuario ,
		       fec_modi = sysdate
		WHERE  PAIS_COD_PAIS      = pscodigopais AND
			     EMCO_COD_EMPR_COME = pscodigoempresa AND
			     COD_CLIE           = pscodigocliente;
	END IF;

	BEGIN
	   select NVL(CAM_CAPA,'*') INTO lscampanhasaptas
	   from EDU_HISTO_APTAS
	   where PAIS_COD_PAIS      = pscodigopais AND
	         EMCO_COD_EMPR_COME = pscodigoempresa AND
	         CCAP_COD_CURS_CAPA = pscurso AND
	         CLIE_COD_CLIE      = pscodigocliente;
	EXCEPTION
      WHEN no_data_found THEN
           lscampanhasaptas := '';
      END;

	-- Veo si esta en la tabla  edu_histo_aptas
	IF (lscampanhasaptas is null) THEN
		-- Si no es apta
		INSERT INTO edu_histo_aptas
                    (pais_cod_pais, emco_cod_empr_come,
                     ccap_cod_curs_capa, clie_cod_clie,
                     cod_curs_dict, cod_plan_prog, cam_prim_cali_apta,
                     cam_ulti_cali_apta, tip_cali_apta, num_invi,
                     ind_inic_cali_apta, ind_fina_cali_apta,
                     ind_curs_cost, cam_acep, cam_capa, est_capa,
                     ind_envi, ind_envi_prog, num_lote_diar,
                     num_lote_regi, usu_digi, fec_digi, est_regi
                    )
        VALUES (pscodigopais, pscodigoempresa,
                pscurso, pscodigocliente,
                lscursodictado, lscodigoplanilla, lscampactual,
                lscampactual, 'E', 1,
                'D', 'D',
                ls_ind_curs_cost, NULL, lscampcapac, 'CP',
                'N', 'N', NULL,
                 NULL, psusuario, SYSDATE, '1'
                );
	ELSE
		-- Si es apta
		UPDATE edu_histo_aptas
		SET   cod_curs_dict = lscursodictado,
          cod_plan_prog = lscodigoplanilla,
          CAM_ULTI_CALI_APTA = lscampactual,
			    TIP_CALI_APTA = 'E',
          EST_CAPA = 'CP',
          CAM_CAPA = lscampactual,
 IND_FINA_CALI_APTA = 'D' ,
 NUM_INVI = DECODE(NUM_INVI,0,1,NUM_INVI)
         -- IND_CURS_COST = ls_ind_curs_cost
		WHERE  PAIS_COD_PAIS      = pscodigopais AND
		       EMCO_COD_EMPR_COME = pscodigoempresa AND
		       CCAP_COD_CURS_CAPA = pscurso AND
		       CLIE_COD_CLIE      = pscodigocliente;

	END IF;

	BEGIN
		SELECT CAM_PROC INTO lscampanhascalifaptas
	    FROM EDU_HISTO_CALIF_APTAS
	    WHERE PAIS_COD_PAIS      = pscodigopais AND
	          EMCO_COD_EMPR_COME = pscodigoempresa AND
	          CCAP_COD_CURS_CAPA = pscurso AND
	          CLIE_COD_CLIE      = pscodigocliente AND
            CAM_PROC           = lscampactual;
	EXCEPTION
      WHEN no_data_found THEN
           lscampanhascalifaptas := '';
      END;

	-- Veo si esta en la tabla  edu_histo_calif_aptas
	IF (lscampanhascalifaptas is null) THEN
		-- Si no esta en  edu_histo_calif_aptas
     select CAM_PRIM_CALI_APTA,
            CAM_ULTI_CALI_APTA,
            TIP_CALI_APTA,
            NUM_INVI,
            IND_INIC_CALI_APTA,
            IND_FINA_CALI_APTA,
            IND_CURS_COST
     into   ls_cam_prim_cali_apta,
            ls_cam_ulti_cali_apta,
            ls_tip_cali_apta,
            ls_num_invi,
            ls_ind_inic_cali_apta,
            ls_ind_fina_cali_apta,
            ls_ind_curs_cost
     from EDU_HISTO_APTAS
     where PAIS_COD_PAIS      = pscodigopais AND
           EMCO_COD_EMPR_COME = pscodigoempresa AND
           CCAP_COD_CURS_CAPA = pscurso AND
           CLIE_COD_CLIE      = pscodigocliente;

		INSERT INTO edu_histo_calif_aptas
                    (pais_cod_pais, emco_cod_empr_come,
                     ccap_cod_curs_capa, cam_proc,
                     clie_cod_clie, cod_curs_dict,
                     cod_plan_prog, cam_prim_cali_apta,
                     cam_ulti_cali_apta, tip_cali_apta, num_invi,
                     ind_inic_cali_apta, ind_fina_cali_apta,
                     ind_curs_cost, cam_capa, est_capa, ind_envi,
                     num_lote_diar, usu_digi, fec_digi, est_regi
                    )
        VALUES (pscodigopais, pscodigoempresa,
                pscurso, lscampactual,
                pscodigocliente, null,
                null, ls_cam_prim_cali_apta,
                ls_cam_ulti_cali_apta, ls_tip_cali_apta, ls_num_invi,
                ls_ind_inic_cali_apta, ls_ind_fina_cali_apta,
                ls_ind_curs_cost, NULL, 'PC', 'N',
                NULL, psusuario, SYSDATE, '1'
               );
	/*ELSE
		UPDATE EDU_HISTO_CALIF_APTAS
		SET    cod_curs_dict =  lscursodictado,
		       --CAM_ULTI_CALI_APTA = lscampactual,
			   --num_invi = to_number(nvl(num_invi,0)+1)
               cod_plan_prog      =  lscodigoplanilla
		WHERE  PAIS_COD_PAIS      = pscodigopais AND
		       EMCO_COD_EMPR_COME = pscodigoempresa AND
		       CCAP_COD_CURS_CAPA = pscurso AND
		       CLIE_COD_CLIE      = pscodigocliente AND
			   CAM_PROC           = lscampanhascalifaptas;
  */
	END IF;



	  select
            NUM_INVI,
 IND_CURS_COST,
			IND_CURS_FACT,
			CAM_FACT_CURS
     into   ls_num_invi,
 ls_ind_curs_cost ,
			ls_ind_curs_fact,
			ls_cam_fact
     from EDU_HISTO_APTAS
     where PAIS_COD_PAIS      = pscodigopais AND
           EMCO_COD_EMPR_COME = pscodigoempresa AND
           CCAP_COD_CURS_CAPA = pscurso AND
           CLIE_COD_CLIE      = pscodigocliente;



	INSERT INTO EDU_PLANI_PROGR_CURSO (PAIS_COD_PAIS, EMCO_COD_EMPR_COME, CCAP_COD_CURS_CAPA,
									   CLIE_COD_CLIE, CAM_PROC, COD_PLAN_PROG,
									 TIP_CALI_APTA, NUM_INVI, IND_CURS_COST,IND_CURS_FACT,CAM_FACT_CURS ,
									   USU_DIGI,FEC_DIGI,EST_REGI)
	VALUES ( pscodigopais, pscodigoempresa,pscurso ,
			 pscodigocliente,lscampactual ,lscodigoplanilla ,
			 'E', ls_num_invi, ls_ind_curs_cost,ls_ind_curs_fact,ls_cam_fact,psusuario,SYSDATE,'1');

    /*REGISTRAMOS ASISTENCIA*/
    edu_pkg_proce_gener.edu_pr_regis_asist(pscodigopais,
                                           pscodigoempresa,
                                           pscurso,
                                           pscodigocliente,
                                           psusuario,
                                           pscodigoasistentecurso,
                                           pscodigoasistenciacurso,
                                           psmensajeerror,
                                           lstipo);

	/* Guardo un historial de la exoneracion*/
	insert into EDU_CONSU_EXONE_CAPAC
	       (PAIS_COD_PAIS,
	        EMCO_COD_EMPR_COME,
	        CCAP_COD_CURS_CAPA,
	        CLIE_COD_CLIE,
	        CAM_EXON,
	        EST_EXON,
	        USU_DIGI,
	        FEC_DIGI,
	        EST_REGI)
	values(pscodigopais,
	       pscodigoempresa,
	       pscurso,
	       pscodigocliente,
	       lscampcapac,
	       'I',
	       psusuario,
	       SYSDATE,
	       '1'
	       );


	 --Desbloqueamos si consultora bloqueada

	 UPDATE EDU_HISTO_BLOQU_CONSU Z
	 SET Z.EST_BLOQ = 'D',
	     Z.CAM_DESB=lscampcapac,
		 Z.USU_MODI=psUsuario,
		 Z.FEC_MODI =SYSDATE
	 WHERE Z.PAIS_COD_PAIS=psCodigoPais
	   AND Z.EMCO_COD_EMPR_COME=pscodigoempresa
	   AND Z.EST_BLOQ='B'
	   AND Z.CLIE_COD_CLIE =pscodigocliente;

	--Actualiza a desbloqueadas aquellas conultoras que han pasado pasado peiddo y han estado bloqueadas
     UPDATE EDU_MAEST_CLIEN Z
	 SET Z.IND_BLOQ = 'N',
		 Z.USU_MODI=psUsuario,
		 Z.FEC_MODI =SYSDATE
	 WHERE Z.PAIS_COD_PAIS=psCodigoPais
	   AND Z.EMCO_COD_EMPR_COME=pscodigoempresa
	   AND Z.IND_BLOQ = 'S'
	   AND Z.COD_CLIE =pscodigocliente;

	  -- ACTUALIZAMOS LA TABLA DE  BENEFICIOS PARA LA COLSULTORA PARA SU CAMPANHA DE CAPACITACION
	  EDU_PKG_PROCE_GENER.EDU_PR_REGUL_BENEF_CONSU(pscodigopais,pscodigoempresa,lscampcapac,pscurso,pscodigocliente,'1','E',psusuario);

  EXCEPTION
      WHEN OTHERS
      THEN
         ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
         psmensajeerror := ls_sqlerrm;
         raise_application_error (-20123,
                                     'EDU_PR_EXONE_ASIST: '
                                  || ls_sqlerrm
                                 );

  END EDU_PR_EXONE_ASIST;

/***************************************************************************
Descripcion       : Procedimiento que efectua el Proceso de Cierre de
                    Cursos Dictados segun cronograma de regiones que inician facturacion
					para el periodo dado
                    (Proceso Calificacion)
Fecha Creacion    : 13/11/2008
Autor             : Sergio Buchelli
Parametros:
          psCodigoPais  : Codigo de Pais
          psCodEmpresa  : Codigo de Empresa
          psCodPeriodo  : Codigo de Periodo
          psUsuario     : Usuario
***************************************************************************/
   PROCEDURE EDU_PR_CIERR_CURSO_DICTA(
      pscodigopais   VARCHAR2,
      pscodempresa   VARCHAR2,
      pscodperiodo   VARCHAR2,
      psusuario      VARCHAR2
   )IS

   lnNumPedidosMinimo 	  EDU_PARAM_PROGR_CAPAC.NUM_MINI_PEDI_PLAN_NPRO%TYPE;
   BEGIN

    /* Se obtiene el numero de pedidos minimos */
   BEGIN
 	SELECT A.NUM_MINI_PEDI_PLAN_NPRO INTO lnNumPedidosMinimo
	FROM EDU_PARAM_PROGR_CAPAC A
	WHERE A.PAIS_COD_PAIS=psCodigoPais
	  AND A.EMCO_COD_EMPR_COME=psCodEmpresa
	  AND A.COD_PROG_CAPA='01';
   EXCEPTION
    WHEN NO_DATA_FOUND THEN
	  lnNumPedidosMinimo:='0';
	WHEN OTHERS THEN
	  lnNumPedidosMinimo:='0';
   END;

 /*  Se valida numero de pedidos minimos por region que se va porcesar */
 /* solose inserta regiones q pasan la validacion
    SI EL NUM PEDIDOS MINIMO > 0 SE OBTENDRAS LOS PEDIDOS DEL TEMPORAL DE PEDIDOS
	SI ES CERO SE TOMRAN DEL GTT DE REGION */
 DELETE FROM EDU_GTT_PARAM_PROCE;
 IF(lnNumPedidosMinimo > 0) THEN
	 INSERT INTO EDU_GTT_PARAM_PROCE
	 (cod_proc, cod_para, val_para_varc)
	   SELECT  '01','01', c.cod_regi
	   FROM  edu_tmp_pedid_consu c
	   WHERE c.COD_PAIS = psCodigoPais
	     AND c.COD_EMPR_COME = psCodEmpresa
	     AND c.cam_proc = psCodPeriodo
	   GROUP BY c.cod_regi
	   HAVING COUNT (1) >= TO_NUMBER (lnNumPedidosMinimo);
  ELSE
   --SI EL NUM PEDIDDOS MINIMOS = 0 SE UTILIZA EL GTT DE CIERRE DE REGIONES
   INSERT INTO EDU_GTT_PARAM_PROCE
	 (cod_proc, cod_para, val_para_varc)
	   SELECT  '01','01', c.cod_regi
	    FROM  EDU_GTT_REGIO_CIERR c
		   WHERE C.COD_PAIS = psCodigoPais
		     AND C.COD_EMPR_COME =psCodEmpresa;
  END IF;
 /* Fin de validacion */

   --si  se inica facturacion de la region dada hay q cerrar los curos de dictados del periodo
     UPDATE edu_histo_curso_dicta_cabec a
         SET a.est_curs_dict = 'C',
		 	-- a.CAM_INIC_CURS = pscodperiodo,
             a.usu_modi = psusuario,
             a.fec_modi = SYSDATE
       WHERE a.pais_cod_pais = pscodigopais
         AND a.emco_cod_empr_come = pscodempresa
         AND a.est_curs_dict = 'V'
         AND a.est_regi = indicador_activo
         AND EXISTS (
                SELECT x.cod_proc
                  FROM edu_gtt_param_proce x
                 WHERE x.cod_proc = '01'
                   AND x.cod_para = '01'
                   AND x.val_para_varc = a.cod_regi);


   EXCEPTION
      WHEN OTHERS
      THEN
         ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
          raise_application_error (-20123,
                                     'EDU_PR_CIERR_CURSO_DICTA_ANTER: '
                                  || ls_sqlerrm
                                 );
   END EDU_PR_CIERR_CURSO_DICTA;

/***************************************************************************
Descripcion       : Procedimiento que efectua el Proceso de Cierre de
                    Cronograma Dictado para la campaña de Proceso segun cronograma de regiones que inician facturacion
					para el periodo dado
                    (Proceso Calificacion)
Fecha Creacion    : 13/11/2008
Autor             : Sergio Buchelli
Parametros:
          psCodigoPais  : Codigo de Pais
          psCodEmpresa  : Codigo de Empresa
          psCodPeriodo  : Codigo de Periodo
          psUsuario     : Usuario
***************************************************************************/
   PROCEDURE EDU_PR_CIERR_CRONO_DICTA(
      pscodigopais   VARCHAR2,
      pscodempresa   VARCHAR2,
      pscodperiodo   VARCHAR2,
      psusuario      VARCHAR2
   )
IS
   lsCodigoPeriodoAnterior  VARCHAR2(6);
   lnNumPedidosMinimo 	  EDU_PARAM_PROGR_CAPAC.NUM_MINI_PEDI_PLAN_NPRO%TYPE;
   BEGIN

   lsCodigoPeriodoAnterior := EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_CODIG_PERIO(psCodPeriodo, -1);
    /* Se obtiene el numero de pedidos minimos */
   BEGIN
 	SELECT A.NUM_MINI_PEDI_PLAN_NPRO INTO lnNumPedidosMinimo
	FROM EDU_PARAM_PROGR_CAPAC A
	WHERE A.PAIS_COD_PAIS=psCodigoPais
	  AND A.EMCO_COD_EMPR_COME=psCodEmpresa
	  AND A.COD_PROG_CAPA='01';
   EXCEPTION
    WHEN NO_DATA_FOUND THEN
	  lnNumPedidosMinimo:='0';
	WHEN OTHERS THEN
	  lnNumPedidosMinimo:='0';
   END;

 /*  Se valida numero de pedidos minimos por region que se va porcesar */
 /* solose inserta regiones q pasan la validacion
    SI EL NUM PEDIDOS MINIMO > 0 SE OBTENDRAS LOS PEDIDOS DEL TEMPORAL DE PEDIDOS
	SI ES CERO SE TOMRAN DEL GTT DE REGION */
 DELETE FROM EDU_GTT_PARAM_PROCE;
 IF(lnNumPedidosMinimo > 0) THEN
	 INSERT INTO EDU_GTT_PARAM_PROCE
	 (cod_proc, cod_para, val_para_varc)
	   SELECT '01','01', c.cod_regi
	   FROM  edu_tmp_pedid_consu c
	   WHERE c.COD_PAIS = psCodigoPais
	     AND c.COD_EMPR_COME = psCodEmpresa
	     AND c.cam_proc = psCodPeriodo
	   GROUP BY c.cod_regi
	   HAVING COUNT (1) >= TO_NUMBER (lnNumPedidosMinimo);
  ELSE
   --SI EL NUM PEDIDDOS MINIMOS = 0 SE UTILIZA EL GTT DE CIERRE DE REGIONES
   INSERT INTO EDU_GTT_PARAM_PROCE
	 (cod_proc, cod_para, val_para_varc)
	   SELECT  '01','01', c.cod_regi
	    FROM  EDU_GTT_REGIO_CIERR c
		   WHERE C.COD_PAIS = psCodigoPais
		     AND C.COD_EMPR_COME =psCodEmpresa;
  END IF;
 /* Fin de validacion */


      /* Actualizando Estado de Cronograma Dictado cerramos cronogramas de campanha anterior debido q inicia facturacion en peridod actual*/
      UPDATE edu_crono_dicta a
         SET a.est_acti = 'I',
             a.usu_modi = psusuario,
             a.fec_modi = SYSDATE
       WHERE a.pais_cod_pais = pscodigopais
         AND a.emco_cod_empr_come = pscodempresa
         AND a.cam_cron = lsCodigoPeriodoAnterior
         AND a.est_acti = 'A'
         AND a.est_regi = indicador_activo
         AND EXISTS (
                SELECT x.cod_proc
                  FROM edu_gtt_param_proce x
                 WHERE x.cod_proc = '01'
                   AND x.cod_para = '01'
                   AND x.val_para_varc = a.regi_cod_regi);


   EXCEPTION
      WHEN OTHERS
      THEN
         ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
          raise_application_error (-20123,
                                     'EDU_PR_CIERR_CRONO_DICTA: '
                                  || ls_sqlerrm
                                 );
   END EDU_PR_CIERR_CRONO_DICTA;


/***************************************************************************
Descripcion       : Procedimiento que cambiar el estado de consultoras
                    que hicieron pedido rezagadoo , no tinen planilla asociada
					de PP a PC
					(Proceso de cierre de Campaña)
Fecha Creacion    : 23/02/2009
Autor             : Sergio Buchelli
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psCodPeriodo : Campaña de Proceso
            psUsuario    : Usuario
***************************************************************************/
PROCEDURE EDU_PR_PROCE_PENDI_PROGR_PENDI(
  psCodigoPais               VARCHAR2,
  psCodEmpresa               VARCHAR2,
  psCodPeriodo               VARCHAR2,
  psUsuario                  VARCHAR2
)  IS
   BEGIN
                UPDATE edu_histo_aptas a
                  SET a.est_capa = indicador_pendiente,
                      a.usu_modi = psusuario,
                      a.fec_modi = SYSDATE
                WHERE a.pais_cod_pais = pscodigopais
                  AND a.emco_cod_empr_come = pscodempresa
				  AND A.CAM_ULTI_CALI_APTA <=psCodPeriodo
				  AND A.EST_CAPA='PP'
                  AND a.est_regi <> '9';

   EXCEPTION
      WHEN OTHERS
      THEN
         ln_sqlcode := SQLCODE;
         ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
         raise_application_error (-20123,
                                     'ERROR EDU_PR_PROCE_PENDI_PROGR_PENDI: '
                                  || ls_sqlerrm
                                 );
   END EDU_PR_PROCE_PENDI_PROGR_PENDI;

/***************************************************************************
Descripcion       : Proceso que se encarga de actualizar la tabla de beneficios (unico y nuevo),  con la
				   consultora que se ha regularizado, creando su historial de despachos desde la campana de asistencia
Fecha Creacion    : 17/02/2009
Autor             : Sergio Buchelli Silva
Parametros:
          psCodigoPais    : Codigo de Pais
          psCodigoEmpresa    : Codigo de Empresa
          psCodigoCurso      : Codigo de Curso
		  psCodigoConsultora : Codigo de Consultora
		  psUsuario       : Usuario de Proceso
***************************************************************************/

PROCEDURE EDU_PR_REGUL_BENEF_CONSU(
    psCodigoPais        VARCHAR2,
    psCodigoEmpresa     VARCHAR2,
	psCampanaCapac		VARCHAR2,
	psCodigoCurso       VARCHAR2,
    psCodigoConsultora  VARCHAR2,
	psIndConfirmarAsist VARCHAR2,
	psTipoEnvio			VARCHAR2,
    psUsuario           VARCHAR2
)IS

 -- obtine loas clasificaciones que hay para esa campanha d e capacitacion
   CURSOR  curClasif IS
       select
          dp.COD_CLAS   ,
          dp.NIV_CAPA_ALCA  ,
		  ca.cam_proc,
          dp.ERNI_COD_ESTA_RESP_NIVE ,
          dp.ASTT_COD_TIPO_ASTT_CURS,
		  dp.IND_DESP_CLAS,
		  dp.NUM_CAMP_MAXI_DESP
        from edu_param_clasi_benef_capac dp,edu_histo_clasi_benef_cabec ca
        where dp.pais_cod_pais = psCodigoPais and
              dp.emco_cod_empr_come = psCodigoEmpresa and
              -- se agrego el tipo de clasificacion, para este caso son  B: Beneficios
              dp.tip_clas = 'B' and
			  dp.NIV_CAPA_ALCA= psCodigoCurso and
              ca.pais_cod_pais = dp.pais_cod_pais and
              ca.emco_cod_empr_come = dp.emco_cod_empr_come and
              ca.clas_cod_clas = dp.cod_clas and
              ca.cam_proc = psCampanaCapac and
--              ca.est_clas = 'A'  and
--              dp.est_clas = 'A' and
			  dp.IND_SELE_CAPA='U' and
			  dp.ERNI_COD_ESTA_RESP_NIVE='N';

   ls_sqlerrm         VARCHAR2(150);
   lsUltimaProgDict		EDU_HISTO_APTAS.ULT_CAMP_PROG_DICT%TYPE;

   row_cursor curClasif%ROWTYPE;
   lsCampaIni	  VARCHAR2(6);
   lsPeriodoActual VARCHAR2(6);
   lnNumDespacho NUMBER;
   lnContClasif NUMBER;

BEGIN
    --Peridod Actual

	lsPeriodoActual:= EDU_PKG_CALIF.EDU_FN_DEVUE_CAMPA_PROCE_ACTUA(psCodigoPais,psCodigoEmpresa);

    OPEN curClasif; -- open the cursor
    LOOP
    FETCH curClasif INTO row_cursor ;
    EXIT WHEN curClasif%notfound; -- exit condition
      begin
		 --POR CADA CLASIIFCACION SE GENERARA EL HISTORICO D EBENEFICOS PARA LA CONSULTORA
		 --SIEMPRE Y CUANDO EL INDICADOR DESPACHO ESTE ACTIVO

          IF(row_cursor.IND_DESP_CLAS='1')THEN

	         lnNumDespacho:=1;

	 		   FOR i IN 0..row_cursor.NUM_CAMP_MAXI_DESP-1  LOOP
				    lsCampaIni:= EDU_PKG_PROCE_COMER.GEN_FN_DEVUE_NSGTE_CAMPA(psCampanaCapac,i);
				   --Verificamdo que exista  la clasificacion para la Campnha en juego
				   --NO se verificara que este Activa ni Eliminada Solo q exista o haya existido
				   		 select COUNT(1) INTO lnContClasif
				        from edu_param_clasi_benef_capac dp,edu_histo_clasi_benef_cabec ca
				        where dp.pais_cod_pais = psCodigoPais and
				              dp.emco_cod_empr_come = psCodigoEmpresa and
				              dp.tip_clas = 'B' and
							  dp.NIV_CAPA_ALCA= psCodigoCurso and
				              ca.pais_cod_pais = dp.pais_cod_pais and
				              ca.emco_cod_empr_come = dp.emco_cod_empr_come and
				              ca.clas_cod_clas = dp.cod_clas and
				              ca.cam_proc = lsCampaIni and
							  dp.IND_SELE_CAPA='U' and
							  dp.ERNI_COD_ESTA_RESP_NIVE='N'and
							  dp.cod_clas =row_cursor.COD_CLAS;

                   IF(lnContClasif>0 AND lsCampaIni <= lsPeriodoActual) THEN
				     IF(psIndConfirmarAsist='1') THEN
						   --SE INSERTA
						   BEGIN
						   		INSERT INTO EDU_HISTO_CLASI_BENEF_DETAL(
					             PAIS_COD_PAIS, EMCO_COD_EMPR_COME, CLAS_COD_CLAS, HBEC_CAM_PROC,
					             CLIE_COD_CLIE, NIV_CAPA_ALCA, CAM_DICT_CURS,
					             EST_RESP_NIVE, IND_ENVI,
					             USU_DIGI, FEC_DIGI, EST_REGI, IND_DESP_CLAS,NUM_DESP,TIP_ENVI_CLAS)
							   VALUES (psCodigoPais, psCodigoEmpresa, row_cursor.COD_CLAS, lsCampaIni,
					              psCodigoConsultora, psCodigoCurso, lsCampaIni,
					              row_cursor.ERNI_COD_ESTA_RESP_NIVE, 'S',
					              psUsuario, SYSDATE, '1', '0',lnNumDespacho,psTipoEnvio);

						   EXCEPTION
						    WHEN OTHERS THEN --REGISTRO YA EXISTE
							  NULL;
						   END;
					  ELSE
					     --SE ELIMINA REVERTIMOS
						    DELETE FROM EDU_HISTO_CLASI_BENEF_DETAL A
							 WHERE  PAIS_COD_PAIS= psCodigoPais
							   AND EMCO_COD_EMPR_COME = psCodigoEmpresa
							   AND CLAS_COD_CLAS = row_cursor.COD_CLAS
							   AND HBEC_CAM_proc = lsCampaIni
							   AND CLIE_COD_CLIE = psCodigoConsultora;
					  END IF;
				   	 lnNumDespacho:=lnNumDespacho + 1;

			      END IF;

			   END LOOP ;
		 END IF;

       end ;
    END LOOP;
    CLOSE curClasif;
  RETURN;
EXCEPTION
WHEN OTHERS THEN
      ls_sqlerrm := substr(sqlerrm,1,250);
      RAISE_APPLICATION_ERROR(-20123, 'EDU_PR_ACTUA_BENEF_CONSU : '||ls_sqlerrm);
END EDU_PR_REGUL_BENEF_CONSU;


/***************************************************************************
Descripcion       : Procedimiento que actualiza el estatus de la consultora a
                    01:Activa 02:Posible Egreso 03: Egresada
Fecha Creacion    : 26/02/2009
Autor             : Sergio Buchelli
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodEmpresa : Codigo de Empresa
            psCodPeriodo : Campaña de Proceso
            psCodProceso  : codigo Proceso
			psCodParam    : codigo Parametro
            psUsuario    : Usuario
***************************************************************************/
PROCEDURE EDU_PR_PROCE_ACTUA_STATU_CONSU(
  psCodigoPais               VARCHAR2,
  psCodEmpresa               VARCHAR2,
  psCodPeriodo               VARCHAR2,
  psCodProceso               VARCHAR2,
  psCodParam                 VARCHAR2,
  psUsuario                  VARCHAR2
)  IS
   BEGIN
						--- Consultoras ACTIVAS --
				----- Consultoras que SI facturaron en campaña de proceso -----
							UPDATE EDU_MAEST_CLIEN C
							SET C.STA_CLIE='01',
							    C.FEC_MODI=SYSDATE,
								  C.USU_MODI=psUsuario
							WHERE C.PAIS_COD_PAIS=psCodigoPais
							  AND C.EMCO_COD_EMPR_COME = psCodEmpresa
							  AND C.COD_CLIE IN(
							  	  SELECT B.COD_CLIE
								  FROM EDU_HISTO_PEDID_CONSU A, EDU_MAEST_CLIEN B ,EDU_GTT_PARAM_PROCE W
								  WHERE A.PAIS_COD_PAIS = psCodigoPais
								  AND A.EMCO_COD_EMPR_COME = psCodEmpresa
								  AND A.CAM_PROC = psCodPeriodo
								  AND A.CAM_ULTI_PEDI = A.CAM_PROC --CAMPAÑA DE PROCESO
								  AND A.IND_FACT = '1'
								  AND A.PAIS_COD_PAIS = B.PAIS_COD_PAIS
								  AND A.EMCO_COD_EMPR_COME = B.EMCO_COD_EMPR_COME
								  AND A.COD_CLIE = B.COD_CLIE
							   	  AND A.COD_CLIE =C.COD_CLIE
								  AND W.COD_PROC = PSCODPROCESO
								  AND W.COD_PARA = PSCODPARAM
                     			  AND W.VAL_PARA_VARC = B.COD_REGI);


						   --- Consultoras POSIBLE EGRESO --
						----- Consultoras que han pasado pedido pero NO han facturado en campaña de proceso -----
						UPDATE EDU_MAEST_CLIEN C
						SET C.STA_CLIE='02',
						  C.FEC_MODI=SYSDATE,
							C.USU_MODI=psUsuario
						WHERE C.PAIS_COD_PAIS=psCodigoPais
						  AND C.EMCO_COD_EMPR_COME = psCodEmpresa
						  AND C.COD_CLIE IN(
								SELECT A.COD_CLIE
								FROM EDU_HISTO_PEDID_CONSU A, EDU_MAEST_CLIEN B,EDU_GTT_PARAM_PROCE W
								WHERE A.PAIS_COD_PAIS = psCodigoPais
								AND   A.EMCO_COD_EMPR_COME = psCodEmpresa
				  			    AND	  A.CAM_PROC = psCodPeriodo
								AND	  A.CAM_ULTI_PEDI = A.CAM_PROC --CAMPAÑA DE PROCESO
								AND	  A.IND_FACT = '0'
								AND	  A.PAIS_COD_PAIS = B.PAIS_COD_PAIS
								AND	  A.EMCO_COD_EMPR_COME = B.EMCO_COD_EMPR_COME
								AND	  A.COD_CLIE = B.COD_CLIE
								AND   A.COD_CLIE= C.COD_CLIE
								AND   W.COD_PROC = PSCODPROCESO
								AND   W.COD_PARA = PSCODPARAM
                     			AND   W.VAL_PARA_VARC = B.COD_REGI);

						----- Consultoras que SI facturaron en campaña anterior -----
						UPDATE EDU_MAEST_CLIEN C
						SET C.STA_CLIE='02',
						    C.FEC_MODI=SYSDATE,
							C.USU_MODI=psUsuario
						WHERE C.PAIS_COD_PAIS=psCodigoPais
						  AND C.EMCO_COD_EMPR_COME = psCodEmpresa
						  AND C.COD_CLIE IN(
								SELECT A.COD_CLIE
								FROM EDU_HISTO_PEDID_CONSU A, EDU_MAEST_CLIEN B ,EDU_GTT_PARAM_PROCE W
								WHERE A.PAIS_COD_PAIS = psCodigoPais
								AND   A.EMCO_COD_EMPR_COME = psCodEmpresa
				  			    AND	  A.CAM_PROC = EDU_PKG_PROCE_COMER.GEN_FN_DEVUE_NSGTE_CAMPA(psCodPeriodo,-1)
								AND	  A.CAM_ULTI_PEDI = A.CAM_PROC --CAMPAÑA DE PROCESO - 1
								AND	  A.IND_FACT = '1'
								AND	  A.PAIS_COD_PAIS = B.PAIS_COD_PAIS
								AND	  A.EMCO_COD_EMPR_COME = B.EMCO_COD_EMPR_COME
								AND	  A.COD_CLIE = B.COD_CLIE
								AND   A.COD_CLIE= C.COD_CLIE
								AND   W.COD_PROC = PSCODPROCESO
								AND   W.COD_PARA = PSCODPARAM
								AND   W.VAL_PARA_VARC = B.COD_REGI);

					--- Consultoras EGRESADAS ---
					----- Consultoras que han pasado pedido pero NO han facturado en campaña anterior -----
						UPDATE EDU_MAEST_CLIEN C
						SET C.STA_CLIE='03',
						    C.FEC_MODI=SYSDATE,
							C.USU_MODI=psUsuario
						WHERE C.PAIS_COD_PAIS=psCodigoPais
						  AND C.EMCO_COD_EMPR_COME = psCodEmpresa
						  AND C.COD_CLIE IN(
								SELECT A.COD_CLIE
								FROM EDU_HISTO_PEDID_CONSU A, EDU_MAEST_CLIEN B, EDU_GTT_PARAM_PROCE W
								WHERE A.PAIS_COD_PAIS = psCodigoPais
								AND   A.EMCO_COD_EMPR_COME=psCodEmpresa
								AND	  A.CAM_PROC = EDU_PKG_PROCE_COMER.GEN_FN_DEVUE_NSGTE_CAMPA(psCodPeriodo,-1)
								AND	  A.CAM_ULTI_PEDI = A.CAM_PROC --CAMPAÑA DE PROCESO - 1
								AND	  A.IND_FACT = '0'
								AND	  A.PAIS_COD_PAIS = B.PAIS_COD_PAIS
								AND	  A.EMCO_COD_EMPR_COME = B.EMCO_COD_EMPR_COME
								AND	  A.COD_CLIE = B.COD_CLIE
								AND   A.COD_CLIE=C.COD_CLIE
								AND   W.COD_PROC = PSCODPROCESO
								AND   W.COD_PARA = PSCODPARAM
                     			AND   W.VAL_PARA_VARC = B.COD_REGI);

----- Consultoras que NO han facturado 2 campañas anteriores -----
						UPDATE EDU_MAEST_CLIEN C
						SET C.STA_CLIE='03',
						    C.FEC_MODI=SYSDATE,
							C.USU_MODI=psUsuario
						WHERE C.PAIS_COD_PAIS=psCodigoPais
						  AND C.EMCO_COD_EMPR_COME = psCodEmpresa
						  AND C.COD_CLIE IN(
								SELECT A.COD_CLIE
								FROM EDU_HISTO_PEDID_CONSU A, EDU_MAEST_CLIEN B ,EDU_GTT_PARAM_PROCE W
								WHERE A.PAIS_COD_PAIS = psCodigoPais
								AND   A.EMCO_COD_EMPR_COME=psCodEmpresa
								AND	  A.CAM_PROC <= EDU_PKG_PROCE_COMER.GEN_FN_DEVUE_NSGTE_CAMPA(psCodPeriodo,-2)
								AND	  A.CAM_ULTI_PEDI <= A.CAM_PROC --CAMPAÑA DE PROCESO - 2
								AND	  A.PAIS_COD_PAIS = B.PAIS_COD_PAIS
								AND	  A.EMCO_COD_EMPR_COME = B.EMCO_COD_EMPR_COME
								AND	  A.COD_CLIE = B.COD_CLIE
								AND A.COD_CLIE = C.COD_CLIE
								AND W.COD_PROC = PSCODPROCESO
                                AND W.COD_PARA = PSCODPARAM
                                AND W.VAL_PARA_VARC = B.COD_REGI);



   EXCEPTION
      WHEN OTHERS
      THEN
         ln_sqlcode := SQLCODE;
         ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
         raise_application_error (-20123,
                                     'ERROR EDU_PR_PROCE_ACTUA_STATU_CONSU: '
                                  || ls_sqlerrm
                                 );
   END EDU_PR_PROCE_ACTUA_STATU_CONSU;


/***************************************************************************
Descripcion       : Proceso que se encarga de validar las consultoras
										capacitadas
Fecha Creacion    : 12/03/2009
Autor             : Sergio Buchelli Silva
Parametros:
          psCodigoPais    : Codigo de Pais
          psCodigoEmpresa : Codigo de Empresa
          psCampanha   : Campanha Inicial
    	  psSentencia      :Setencia,
          psUsuario       : Usuario de Proceso
Parametros Salida   : psMensajeError: Mensaje de Error
***************************************************************************/
   PROCEDURE edu_pr_valid_capac (
      pscodigopais            VARCHAR2,
      pscodigoempresa         VARCHAR2,
      pssentencia             VARCHAR2,
      pscampanha              VARCHAR2,
      psusuario               VARCHAR2,
      psmensajeerror    OUT   VARCHAR2
   )
   IS
      TYPE registro_val IS RECORD (
       	 clie_cod_clie        edu_histo_aptas.clie_cod_clie%TYPE,
         ccap_cod_curs_capa   edu_histo_aptas.ccap_cod_curs_capa%TYPE,
         est_capa             edu_histo_aptas.est_capa%TYPE,
         cam_ulti_cali_apta   edu_histo_aptas.cam_ulti_cali_apta%TYPE,
         ult_camp_prog_dict   edu_histo_aptas.ult_camp_prog_dict%TYPE

      );

      TYPE tabla_temporal IS TABLE OF registro_val;

      tablaregistro      tabla_temporal;
      ls_sqlerrm         VARCHAR2 (150);
      lssql              VARCHAR2 (6)   := 'SQL010';
      lsestadoactivo     VARCHAR2 (1)   := '1';
      lnconterrors       NUMBER         := 0;
      lncontexitos       NUMBER         := 0;
      lsestadocapac      VARCHAR (2)    := 'CP';
      lnnumregisproc     NUMBER         := 0;
      lnnumregistros     NUMBER         := 0;
      lscampactual       VARCHAR2 (6);
      lnnumitem          NUMBER         := 0;
      lnccont            NUMBER         := 0;
      lsestadook         VARCHAR2 (1)   := '1';
      lsestadonok        VARCHAR2 (1)   := '0';
      lsobservacionok    VARCHAR2 (50)  := 'Aplica Validación';
      lsobservacionnok   VARCHAR2 (70)
         := 'No Aplica Validación para campaña menores o mayores de la Actual';
      procesar           BOOLEAN;

      CURSOR cursorcapac
      IS
						SELECT B.CLIE_COD_CLIE,B.CCAP_COD_CURS_CAPA,B.EST_CAPA,B.CAM_ULTI_CALI_APTA,B.ULT_CAMP_PROG_DICT
						FROM EDU_HISTO_APTAS B
						Where
						  B.PAIS_COD_PAIS =pscodigopais
						  AND B.EMCO_COD_EMPR_COME=pscodigoempresa
						  and B.est_capa='PC'
						  AND B.CAM_CAPA IS NOT NULL
	  					UNION ALL
						SELECT B.CLIE_COD_CLIE,B.CCAP_COD_CURS_CAPA,B.EST_CAPA,B.CAM_ULTI_CALI_APTA,B.ULT_CAMP_PROG_DICT
						FROM EDU_HISTO_CURSO_DICTA_DETAL A , EDU_HISTO_APTAS B
						Where A.PAIS_COD_PAIS  = pscodigopais
						 AND A.EMCO_COD_EMPR_COME = pscodigoempresa
						 AND A.IND_ASIS  ='S'
						AND A.PAIS_COD_PAIS = B.PAIS_COD_PAIS
						AND A.EMCO_COD_EMPR_COME = B.EMCO_COD_EMPR_COME
						AND A.CCAP_COD_CURS_CAPA = B.CCAP_COD_CURS_CAPA
						AND A.CLIE_COD_CLIE = B.CLIE_COD_CLIE
						AND A.CLIE_COD_CLIE NOT IN (
						   SELECT CD.CLIE_COD_CLIE
						   FROM EDU_HISTO_CAPAC_DETAL CD
						   WHERE CD.PAIS_COD_PAIS = A.PAIS_COD_PAIS
						    AND CD.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
							AND CD.CCAP_COD_CURS_CAPA = A.CCAP_COD_CURS_CAPA
							AND CD.CLIE_COD_CLIE = A.CLIE_COD_CLIE)
						UNION ALL
						SELECT B.CLIE_COD_CLIE,B.CCAP_COD_CURS_CAPA,B.EST_CAPA,B.CAM_ULTI_CALI_APTA,B.ULT_CAMP_PROG_DICT
						FROM EDU_HISTO_APTAS B
						Where
						  B.PAIS_COD_PAIS =pscodigopais
						  AND B.EMCO_COD_EMPR_COME=pscodigoempresa
						  and B.est_capa=lsestadocapac
						  AND B.CLIE_COD_CLIE NOT IN (
						  	  SELECT X.CLIE_COD_CLIE
							  FROM EDU_HISTO_CURSO_DICTA_DETAL X
							  Where X.PAIS_COD_PAIS =B.PAIS_COD_PAIS
						        AND X.EMCO_COD_EMPR_COME = B.EMCO_COD_EMPR_COME
								AND X.CCAP_COD_CURS_CAPA = B.CCAP_COD_CURS_CAPA
								AND X.IND_ASIS='S')	;

   BEGIN
      --Se valida que el registro de nose encuentre ya en la tabla de auditoria
      SELECT COUNT (1)
        INTO lnnumregistros
        FROM edu_audit_cabec a
       WHERE a.pais_cod_pais = pscodigopais
         AND a.emco_cod_empr_come = pscodigoempresa
         AND a.auqu_cod_audi_quer = lssql;

      --  AND A.CAM_PROC=psCampanha;
       --se borran los detalles
      DELETE FROM edu_audit_detal
            WHERE pais_cod_pais = pscodigopais
              AND emco_cod_empr_come = pscodigoempresa
              AND auqu_cod_audi_quer = lssql;

      -- AND CAM_PROC=psCampanha;
      IF (lnnumregistros = 0)
      THEN
         --INSERT EN LA TABLA EDU_AUDIT_CABEC
         INSERT INTO edu_audit_cabec
                     (pais_cod_pais, emco_cod_empr_come, auqu_cod_audi_quer,
                      usu_digi, fec_digi, est_regi, obs_proc,
                      est_proc
                     )
              VALUES (pscodigopais, pscodigoempresa, lssql,
                      psusuario, SYSDATE, lsestadoactivo, lsobservacionok,
                      lsestadook
                     );
      END IF;

      OPEN cursorcapac;

      LOOP
         FETCH cursorcapac
         BULK COLLECT INTO tablaregistro LIMIT w_filas;

         EXIT WHEN tablaregistro.COUNT = 0;

         FOR i IN tablaregistro.FIRST .. tablaregistro.LAST
         LOOP

               lnnumitem := lnnumitem + 1;

                  lnconterrors := lnconterrors + 1;

                  INSERT INTO edu_audit_detal
                              (pais_cod_pais, emco_cod_empr_come,
                               auqu_cod_audi_quer, cam_proc,
                               num_item, cod_clie,
                               cod_curs_capa,
                               ult_camp_prog_dict,
                               est_regi_proc, usu_digi, fec_digi,
                               est_regi,
							   est_capa
                              )
                       VALUES (pscodigopais, pscodigoempresa,
                               lssql, tablaregistro (i).cam_ulti_cali_apta,
                               lnnumitem, tablaregistro (i).clie_cod_clie,
                               tablaregistro (i).ccap_cod_curs_capa,
                               tablaregistro (i).ult_camp_prog_dict,
                               lsestadonok, psusuario, SYSDATE,
                               lsestadoactivo,
                               tablaregistro (i).est_capa
                              );

         END LOOP;
      END LOOP;

      CLOSE cursorcapac;

      --UPDATE AUDITORIA CABECERA
      UPDATE edu_audit_cabec a
         SET a.num_regi_corr = lncontexitos,
             a.num_regi_erro = lnconterrors,
             a.num_regi_proc = lncontexitos + lnconterrors,
             a.est_proc = DECODE (lnconterrors, 0, lsestadook, lsestadonok),
             a.usu_modi = psusuario,
             a.fec_modi = SYSDATE
       WHERE a.pais_cod_pais = pscodigopais
         AND a.emco_cod_empr_come = pscodigoempresa
         AND a.auqu_cod_audi_quer = lssql;

      RETURN;
   EXCEPTION
      WHEN OTHERS
      THEN
         ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
         raise_application_error (-20123,
                                     'EDU_PR_VALID_CAPAC: '
                                  || ls_sqlerrm
                                 );
   END edu_pr_valid_capac;



/***************************************************************************
Descripcion       : Proceso que se encarga de validar Indicadores Curso Con Costo

Fecha Creacion    : 19/03/2009
Autor             : Sergio Buchelli Silva
Parametros:
          psCodigoPais    : Codigo de Pais
          psCodigoEmpresa : Codigo de Empresa
          psCampanha   : Campanha Inicial
    	  psSentencia      :Setencia,
          psUsuario       : Usuario de Proceso
Parametros Salida   : psMensajeError: Mensaje de Error
***************************************************************************/
   PROCEDURE edu_pr_valid_indic_curso_costo (
      pscodigopais            VARCHAR2,
      pscodigoempresa         VARCHAR2,
      pssentencia             VARCHAR2,
      pscampanha              VARCHAR2,
      psusuario               VARCHAR2,
      psmensajeerror    OUT   VARCHAR2
   )
   IS
      TYPE registro_val IS RECORD (
       	 clie_cod_clie        edu_histo_aptas.clie_cod_clie%TYPE,
         ccap_cod_curs_capa   edu_histo_aptas.ccap_cod_curs_capa%TYPE,
         est_capa             edu_histo_aptas.est_capa%TYPE,
         cam_ulti_cali_apta   edu_histo_aptas.cam_ulti_cali_apta%TYPE,
         ult_camp_prog_dict   edu_histo_aptas.ult_camp_prog_dict%TYPE

      );

      TYPE tabla_temporal IS TABLE OF registro_val;

      tablaregistro      tabla_temporal;
      ls_sqlerrm         VARCHAR2 (150);
      lssql              VARCHAR2 (6)   := 'SQL011';
      lsestadoactivo     VARCHAR2 (1)   := '1';
      lnconterrors       NUMBER         := 0;
      lncontexitos       NUMBER         := 0;
      lsestadocapac      VARCHAR (2)    := 'CP';
      lnnumregisproc     NUMBER         := 0;
      lnnumregistros     NUMBER         := 0;
      lscampactual       VARCHAR2 (6);
      lnnumitem          NUMBER         := 0;
      lnccont            NUMBER         := 0;
      lsestadook         VARCHAR2 (1)   := '1';
      lsestadonok        VARCHAR2 (1)   := '0';
      lsobservacionok    VARCHAR2 (50)  := 'Aplica Validación';
      lsobservacionnok   VARCHAR2 (70)
         := 'No Aplica Validación para campaña menores o mayores de la Actual';
      procesar           BOOLEAN;

      CURSOR cursorcapac
      IS
      			SELECT A.CLIE_COD_CLIE,A.CCAP_COD_CURS_CAPA,A.EST_CAPA,A.CAM_ULTI_CALI_APTA,A.ULT_CAMP_PROG_DICT
						FROM EDU_HISTO_APTAS A
						WHERE A.PAIS_COD_PAIS=pscodigopais
						  AND A.EMCO_COD_EMPR_COME =pscodigoempresa
						  AND A.CAM_FACT_CURS <> A.CAM_ACEP
						UNION
						SELECT A.CLIE_COD_CLIE,A.CCAP_COD_CURS_CAPA,A.EST_CAPA,A.CAM_ULTI_CALI_APTA,A.ULT_CAMP_PROG_DICT
						FROM EDU_HISTO_APTAS A
						WHERE A.PAIS_COD_PAIS=pscodigopais
						 AND A.EMCO_COD_EMPR_COME =pscodigoempresa
						 and a.IND_CURS_COST='S'
						 AND A.IND_CURS_FACT ='N'
						 AND A.COD_PLAN_PROG <> '9999999999'
						 AND A.EST_CAPA='CP'
						UNION
						SELECT A.CLIE_COD_CLIE,A.CCAP_COD_CURS_CAPA,A.EST_CAPA,A.CAM_ULTI_CALI_APTA,A.ULT_CAMP_PROG_DICT
							FROM EDU_HISTO_APTAS A
						WHERE A.PAIS_COD_PAIS=pscodigopais
						 AND A.EMCO_COD_EMPR_COME =pscodigoempresa
							 AND A.IND_CURS_COST ='S'
							 and a.ULT_CAMP_PROG_DICT is not null
							 AND A.COD_PLAN_PROG <> '9999999999'
							 and a.TIP_CALI_APTA='R'
							 AND A.IND_CURS_COST ='F'
							 AND A.EST_CAPA='CP'
							 and a.clie_cod_clie in(
									SELECT x.clie_cod_clie
									FROM EDU_HISTO_CAPAC_DETAL x
									WHERE x.PAIS_COD_PAIS = A.PAIS_COD_PAIS
									 AND X.CLIE_COD_CLIE = A.CLIE_COD_CLIE
									 AND X.CCAP_COD_CURS_CAPA=A.CCAP_COD_CURS_CAPA
									 AND X.IND_PAGO_CURS='N')
						UNION
						SELECT A.CLIE_COD_CLIE,A.CCAP_COD_CURS_CAPA,A.EST_CAPA,A.CAM_ULTI_CALI_APTA,A.ULT_CAMP_PROG_DICT
						FROM EDU_HISTO_APTAS A
						WHERE A.PAIS_COD_PAIS=pscodigopais
						 AND A.EMCO_COD_EMPR_COME =pscodigoempresa
						 AND A.IND_CURS_COST ='S'
						 and a.ULT_CAMP_PROG_DICT is not null
						 AND A.COD_PLAN_PROG <> '9999999999'
						 and a.TIP_CALI_APTA='R'
						 AND A.IND_CURS_COST ='F'
						 and a.clie_cod_clie in(
								SELECT x.clie_cod_clie
								FROM EDU_PLANI_PROGR_CURSO x
								WHERE x.PAIS_COD_PAIS = A.PAIS_COD_PAIS
								 AND x.IND_CURS_COST ='N'
								 AND X.COD_PLAN_PROG<>'9999999999'
								 AND X.CCAP_COD_CURS_CAPA=A.CCAP_COD_CURS_CAPA
								 AND X.CAM_PROC = A.ULT_CAMP_PROG_DICT)
						UNION
						SELECT A.CLIE_COD_CLIE,A.CCAP_COD_CURS_CAPA,A.EST_CAPA,A.CAM_ULTI_CALI_APTA,A.ULT_CAMP_PROG_DICT
						FROM EDU_HISTO_APTAS A
						WHERE A.PAIS_COD_PAIS=pscodigopais
						 AND A.EMCO_COD_EMPR_COME =pscodigoempresa
						 AND A.IND_CURS_COST ='S'
						 and a.ULT_CAMP_PROG_DICT is not null
						 AND A.COD_PLAN_PROG <> '9999999999'
						 and a.TIP_CALI_APTA='R'
						 AND A.IND_CURS_FACT='F'
						 and a.EST_CAPA = 'CP'
						  and a.clie_cod_clie in(
								SELECT x.clie_cod_clie
								FROM EDU_PLANI_PROGR_CURSO x
								WHERE x.PAIS_COD_PAIS = A.PAIS_COD_PAIS
								 AND x.IND_CURS_COST ='S'
								 AND X.COD_PLAN_PROG<>'9999999999'
								 AND X.CCAP_COD_CURS_CAPA=A.CCAP_COD_CURS_CAPA
								 AND X.CAM_PROC = A.ULT_CAMP_PROG_DICT
								 AND X.CAM_FACT_CURS IS NULL);

   BEGIN
      --Se valida que el registro de nose encuentre ya en la tabla de auditoria
      SELECT COUNT (1)
        INTO lnnumregistros
        FROM edu_audit_cabec a
       WHERE a.pais_cod_pais = pscodigopais
         AND a.emco_cod_empr_come = pscodigoempresa
         AND a.auqu_cod_audi_quer = lssql;

      --  AND A.CAM_PROC=psCampanha;
       --se borran los detalles
      DELETE FROM edu_audit_detal
            WHERE pais_cod_pais = pscodigopais
              AND emco_cod_empr_come = pscodigoempresa
              AND auqu_cod_audi_quer = lssql;

      -- AND CAM_PROC=psCampanha;
      IF (lnnumregistros = 0)
      THEN
         --INSERT EN LA TABLA EDU_AUDIT_CABEC
         INSERT INTO edu_audit_cabec
                     (pais_cod_pais, emco_cod_empr_come, auqu_cod_audi_quer,
                      usu_digi, fec_digi, est_regi, obs_proc,
                      est_proc
                     )
              VALUES (pscodigopais, pscodigoempresa, lssql,
                      psusuario, SYSDATE, lsestadoactivo, lsobservacionok,
                      lsestadook
                     );
      END IF;

      OPEN cursorcapac;

      LOOP
         FETCH cursorcapac
         BULK COLLECT INTO tablaregistro LIMIT w_filas;

         EXIT WHEN tablaregistro.COUNT = 0;

         FOR i IN tablaregistro.FIRST .. tablaregistro.LAST
         LOOP

               lnnumitem := lnnumitem + 1;

                  lnconterrors := lnconterrors + 1;

                  INSERT INTO edu_audit_detal
                              (pais_cod_pais, emco_cod_empr_come,
                               auqu_cod_audi_quer, cam_proc,
                               num_item, cod_clie,
                               cod_curs_capa,
                               ult_camp_prog_dict,
                               est_regi_proc, usu_digi, fec_digi,
                               est_regi,
							   est_capa
                              )
                       VALUES (pscodigopais, pscodigoempresa,
                               lssql, tablaregistro (i).cam_ulti_cali_apta,
                               lnnumitem, tablaregistro (i).clie_cod_clie,
                               tablaregistro (i).ccap_cod_curs_capa,
                               tablaregistro (i).ult_camp_prog_dict,
                               lsestadonok, psusuario, SYSDATE,
                               lsestadoactivo,
                               tablaregistro (i).est_capa
                              );

         END LOOP;
      END LOOP;

      CLOSE cursorcapac;

      --UPDATE AUDITORIA CABECERA
      UPDATE edu_audit_cabec a
         SET a.num_regi_corr = lncontexitos,
             a.num_regi_erro = lnconterrors,
             a.num_regi_proc = lncontexitos + lnconterrors,
             a.est_proc = DECODE (lnconterrors, 0, lsestadook, lsestadonok),
             a.usu_modi = psusuario,
             a.fec_modi = SYSDATE
       WHERE a.pais_cod_pais = pscodigopais
         AND a.emco_cod_empr_come = pscodigoempresa
         AND a.auqu_cod_audi_quer = lssql;

      RETURN;
   EXCEPTION
      WHEN OTHERS
      THEN
         ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
         raise_application_error (-20123,
                                     'EDU_PR_VALID_CAPAC: '
                                  || ls_sqlerrm
                                 );
   END edu_pr_valid_indic_curso_costo;


/***************************************************************************
Descripcion       : Proceso que se encarga de validar Invitacion Curso Sin Costo

Fecha Creacion    : 25/03/2009
Autor             : Sergio Buchelli Silva
Parametros:
          psCodigoPais    : Codigo de Pais
          psCodigoEmpresa : Codigo de Empresa
          psCampanha   : Campanha Inicial
    	  psSentencia      :Setencia,
          psUsuario       : Usuario de Proceso
Parametros Salida   : psMensajeError: Mensaje de Error
***************************************************************************/
   PROCEDURE edu_pr_valid_curso_sin_costo (
      pscodigopais            VARCHAR2,
      pscodigoempresa         VARCHAR2,
      pssentencia             VARCHAR2,
      pscampanha              VARCHAR2,
      psusuario               VARCHAR2,
      psmensajeerror    OUT   VARCHAR2
   )
   IS
      TYPE registro_val IS RECORD (
       	 clie_cod_clie        edu_histo_aptas.clie_cod_clie%TYPE,
         ccap_cod_curs_capa   edu_histo_aptas.ccap_cod_curs_capa%TYPE,
         cam_prim_cali_apta   edu_histo_aptas.cam_prim_cali_apta%TYPE,
         cam_ulti_cali_apta   edu_histo_aptas.cam_ulti_cali_apta%TYPE,
         ult_camp_prog_dict   edu_histo_aptas.ult_camp_prog_dict%TYPE,
         est_capa             edu_histo_aptas.est_capa%TYPE,
         cam_capa							edu_histo_aptas.CAM_CAPA%TYPE,
         num_invi             edu_histo_aptas.num_invi%TYPE,
         COD_PLAN_PROG  			edu_histo_aptas.COD_PLAN_PROG%TYPE
      );

      TYPE tabla_temporal IS TABLE OF registro_val;

      tablaregistro      tabla_temporal;
      ls_sqlerrm         VARCHAR2 (150);
      lssql              VARCHAR2 (6)   := 'SQL012';
      lsestadoactivo     VARCHAR2 (1)   := '1';
      lnconterrors       NUMBER         := 0;
      lncontexitos       NUMBER         := 0;
      lsestadocapac      VARCHAR (2)    := 'CP';
      lnnumregisproc     NUMBER         := 0;
      lnnumregistros     NUMBER         := 0;
      lscampactual       VARCHAR2 (6);
      lnnumitem          NUMBER         := 0;
      lnccont            NUMBER         := 0;
      lsestadook         VARCHAR2 (1)   := '1';
      lsestadonok        VARCHAR2 (1)   := '0';
      lsobservacionok    VARCHAR2 (50)  := 'Aplica Validación';
      lsobservacionnok   VARCHAR2 (70)
         := 'No Aplica Validación para campaña menores o mayores de la Actual';
      valor           BOOLEAN;

	  lnCont		  NUMBER:=0;
      CURSOR cursorinvsincosto
      IS
    		SELECT  A.CLIE_COD_CLIE,
						    A.CCAP_COD_CURS_CAPA,
						  A.CAM_PRIM_CALI_APTA,
						  A.CAM_ULTI_CALI_APTA,
						  A.ULT_CAMP_PROG_DICT,
						  A.EST_CAPA,
						  A.CAM_CAPA,
						  A.NUM_INVI,
						  A.COD_PLAN_PROG
						  FROM EDU_HISTO_APTAS A,EDU_PARAM_CURSO_CAPAC B
						  WHERE A.PAIS_COD_PAIS= pscodigopais
						        AND A.EMCO_COD_EMPR_COME= pscodigoempresa
								AND A.PAIS_COD_PAIS = B.PAIS_COD_PAIS
								AND A.EMCO_COD_EMPR_COME = B.EMCO_COD_EMPR_COME
								AND A.CCAP_COD_CURS_CAPA = B.COD_CURS_CAPA
								AND B.TICC_COD_TIPO_COST_CURS='01'
								AND A.COD_PLAN_PROG <> '9999999999'
						  ORDER BY A.CAM_PRIM_CALI_APTA;

   BEGIN
      --Se valida que el registro de nose encuentre ya en la tabla de auditoria
      SELECT COUNT (1)
        INTO lnnumregistros
        FROM edu_audit_cabec a
       WHERE a.pais_cod_pais = pscodigopais
         AND a.emco_cod_empr_come = pscodigoempresa
         AND a.auqu_cod_audi_quer = lssql;

      --  AND A.CAM_PROC=psCampanha;
       --se borran los detalles
      DELETE FROM edu_audit_detal
            WHERE pais_cod_pais = pscodigopais
              AND emco_cod_empr_come = pscodigoempresa
              AND auqu_cod_audi_quer = lssql;

      -- AND CAM_PROC=psCampanha;
      IF (lnnumregistros = 0)
      THEN
         --INSERT EN LA TABLA EDU_AUDIT_CABEC
         INSERT INTO edu_audit_cabec
                     (pais_cod_pais, emco_cod_empr_come, auqu_cod_audi_quer,
                      usu_digi, fec_digi, est_regi, obs_proc,
                      est_proc
                     )
              VALUES (pscodigopais, pscodigoempresa, lssql,
                      psusuario, SYSDATE, lsestadoactivo, lsobservacionok,
                      lsestadook
                     );
      END IF;


      OPEN cursorinvsincosto;

      LOOP
         FETCH cursorinvsincosto
         BULK COLLECT INTO tablaregistro LIMIT w_filas;

         EXIT WHEN tablaregistro.COUNT = 0;

         FOR i IN tablaregistro.FIRST .. tablaregistro.LAST
         LOOP

               lnnumitem := lnnumitem + 1;

     					--Por cada Registro se Procesa
						  valor:=TRUE;--NO HAY ERROR
								--N REGISDTROS + 1
							 SELECT COUNT(1) INTO lnCont
							  FROM EDU_HISTO_CALIF_APTAS C
							  WHERE C.PAIS_COD_PAIS=pscodigopais
							   AND C.EMCO_COD_EMPR_COME=pscodigoempresa
							   AND C.CCAP_COD_CURS_CAPA=tablaregistro (i).ccap_cod_curs_capa
							   AND C.CLIE_COD_CLIE=tablaregistro (i).clie_cod_clie
							   AND C.TIP_CALI_APTA<>'E'
							   AND C.EST_REGI='1'
							   AND C.NUM_INVI<>0;

							   valor:=valor AND (lnCont=tablaregistro (i).num_invi);

							   FOR j IN 1 .. tablaregistro (i).num_invi LOOP
     							 SELECT COUNT(1) INTO lnCont
								  FROM EDU_HISTO_CALIF_APTAS C
								  WHERE C.PAIS_COD_PAIS=pscodigopais
								   AND C.EMCO_COD_EMPR_COME=pscodigoempresa
								   AND C.CCAP_COD_CURS_CAPA=tablaregistro (i).ccap_cod_curs_capa
								   AND C.CLIE_COD_CLIE=tablaregistro (i).clie_cod_clie
								   AND C.TIP_CALI_APTA<>'E'
								   AND C.EST_REGI='1'
								   AND C.NUM_INVI=j;
							       valor:=valor AND (lnCont=1);
							  END LOOP;

					     --EXISTE EL REGISTRO VALIDO
					     SELECT COUNT(1) into lnCont
						  FROM EDU_HISTO_CALIF_APTAS C
						  WHERE C.PAIS_COD_PAIS=pscodigopais
						   AND C.EMCO_COD_EMPR_COME=pscodigoempresa
						   AND C.CAM_proc = tablaregistro (i).CAM_ULTI_CALI_APTA
						   AND C.CCAP_COD_CURS_CAPA=tablaregistro (i).ccap_cod_curs_capa
						   AND C.CLIE_COD_CLIE=tablaregistro (i).clie_cod_clie
						   AND C.NUM_INVI=tablaregistro (i).num_invi
						   AND C.TIP_CALI_APTA<>'E'
						   AND C.EST_REGI='1';

						   valor:=valor AND (lnCont=1);

    --NO DEBERIA EXISTRI REGISTROS
				        SELECT COUNT(1) into lnCont
				  			FROM EDU_HISTO_CALIF_APTAS C
				  			WHERE C.PAIS_COD_PAIS=pscodigopais
				   			AND C.EMCO_COD_EMPR_COME=pscodigoempresa
				   			AND C.CAM_proc > tablaregistro (i).CAM_ULTI_CALI_APTA
				   			AND C.CCAP_COD_CURS_CAPA=tablaregistro (i).ccap_cod_curs_capa
				   			AND C.CLIE_COD_CLIE=tablaregistro (i).clie_cod_clie
							AND C.TIP_CALI_APTA<>'E'
				   			AND C.EST_REGI='1';

   							valor:=valor AND (lnCont=0);

--ha sido programada alguna vez
					     IF(tablaregistro (i).ULT_CAMP_PROG_DICT is not null)THEN
					     	 SELECT COUNT(1) into lnCont
							   FROM EDU_PLANI_PROGR_CURSO P
							   WHERE P.PAIS_COD_PAIS=pscodigopais
							    AND P.EMCO_COD_EMPR_COME=pscodigoempresa
								  AND P.CAM_proc =tablaregistro (i).ULT_CAMP_PROG_DICT
								  AND P.CCAP_COD_CURS_CAPA=tablaregistro(i).ccap_cod_curs_capa
								  AND P.COD_PLAN_PROG <> '9999999999'
								  AND P.CLIE_COD_CLIE = tablaregistro(i).clie_cod_clie;

					        valor:=valor AND (lnCont=1);
					     END IF;

    --SI ES PR
					     IF(tablaregistro (i).EST_CAPA='PR')THEN
					     	 SELECT COUNT(1) into lnCont
							   FROM EDU_PLANI_PROGR_CURSO P
							   WHERE P.PAIS_COD_PAIS=pscodigopais
							    AND P.EMCO_COD_EMPR_COME=pscodigoempresa
								  AND P.CAM_proc =tablaregistro (i).ULT_CAMP_PROG_DICT
								  AND P.CCAP_COD_CURS_CAPA=tablaregistro(i).ccap_cod_curs_capa
								  AND P.CLIE_COD_CLIE = tablaregistro(i).clie_cod_clie
								  AND P.COD_PLAN_PROG <> '9999999999'
								  AND P.NUM_INVI=tablaregistro(i).num_invi;

					        valor:=valor AND (lnCont=1);
					     END IF;

		--SI ES CP

				     IF(tablaregistro (i).EST_CAPA='CP')THEN
								SELECT COUNT(1) into lnCont
								FROM EDU_HISTO_CAPAC_DETAL X
								WHERE X.PAIS_COD_PAIS=pscodigopais
								 AND X.EMCO_COD_EMPR_COME=pscodigoempresa
								 AND X.CLIE_COD_CLIE=tablaregistro (i).clie_cod_clie
								 AND X.CCAP_COD_CURS_CAPA=tablaregistro (i).ccap_cod_curs_capa
								 AND X.COD_PLAN_PROG <> '9999999999'
								 AND X.NUM_INVI=tablaregistro(i).num_invi;

								 valor:=valor AND (lnCont=1);
							END IF;


				IF(VALOR=TRUE)THEN
						 lncontexitos := lncontexitos + 1;

     						INSERT INTO edu_audit_detal
                              (pais_cod_pais, emco_cod_empr_come,
                               auqu_cod_audi_quer, cam_proc,
                               num_item, cod_clie,
                               cod_curs_capa,
                               ult_camp_prog_dict,
                               est_regi_proc, usu_digi, fec_digi,
                               est_regi,
							   est_capa
                              )
                       VALUES (pscodigopais, pscodigoempresa,
                               lssql, tablaregistro (i).cam_ulti_cali_apta,
                               lnnumitem, tablaregistro (i).clie_cod_clie,
                               tablaregistro (i).ccap_cod_curs_capa,
                               tablaregistro (i).ult_camp_prog_dict,
                               lsestadook, psusuario, SYSDATE,
                               lsestadoactivo,
                               tablaregistro (i).est_capa
                              );


				ELSE --HAY ERROR
						 lnconterrors := lnconterrors + 1;

                  INSERT INTO edu_audit_detal
                              (pais_cod_pais, emco_cod_empr_come,
                               auqu_cod_audi_quer, cam_proc,
                               num_item, cod_clie,
                               cod_curs_capa,
                               ult_camp_prog_dict,
                               est_regi_proc, usu_digi, fec_digi,
                               est_regi,
							   est_capa
                              )
                       VALUES (pscodigopais, pscodigoempresa,
                               lssql, tablaregistro (i).cam_ulti_cali_apta,
                               lnnumitem, tablaregistro (i).clie_cod_clie,
                               tablaregistro (i).ccap_cod_curs_capa,
                               tablaregistro (i).ult_camp_prog_dict,
                               lsestadonok, psusuario, SYSDATE,
                               lsestadoactivo,
                               tablaregistro (i).est_capa
                              );

				END IF;



        END LOOP;
      END LOOP;

      CLOSE cursorinvsincosto;


      --UPDATE AUDITORIA CABECERA
      UPDATE edu_audit_cabec a
         SET a.num_regi_corr = lncontexitos,
             a.num_regi_erro = lnconterrors,
             a.num_regi_proc = lncontexitos + lnconterrors,
             a.est_proc = DECODE (lnconterrors, 0, lsestadook, lsestadonok),
             a.usu_modi = psusuario,
             a.fec_modi = SYSDATE
       WHERE a.pais_cod_pais = pscodigopais
         AND a.emco_cod_empr_come = pscodigoempresa
         AND a.auqu_cod_audi_quer = lssql;

      RETURN;
   EXCEPTION
      WHEN OTHERS
      THEN
         ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
         raise_application_error (-20123,
                                     'EDU_PR_VALID_INVIT_CURSO_SIN_COSTO: '
                                  || ls_sqlerrm
                                 );
   END edu_pr_valid_curso_sin_costo;


/***************************************************************************
Descripcion       : Proceso que se encarga de validar Invitacion Curso Sin Costo

Fecha Creacion    : 25/03/2009
Autor             : Sergio Buchelli Silva
Parametros:
          psCodigoPais    : Codigo de Pais
          psCodigoEmpresa : Codigo de Empresa
          psCampanha   : Campanha Inicial
    	  psSentencia      :Setencia,
          psUsuario       : Usuario de Proceso
Parametros Salida   : psMensajeError: Mensaje de Error
***************************************************************************/
   PROCEDURE edu_pr_valid_curso_con_costo (
      pscodigopais            VARCHAR2,
      pscodigoempresa         VARCHAR2,
      pssentencia             VARCHAR2,
      pscampanha              VARCHAR2,
      psusuario               VARCHAR2,
      psmensajeerror    OUT   VARCHAR2
   )
   IS
      TYPE registro_val IS RECORD (
       	 clie_cod_clie        edu_histo_aptas.clie_cod_clie%TYPE,
         ccap_cod_curs_capa   edu_histo_aptas.ccap_cod_curs_capa%TYPE,
         cam_prim_cali_apta   edu_histo_aptas.cam_prim_cali_apta%TYPE,
         cam_ulti_cali_apta   edu_histo_aptas.cam_ulti_cali_apta%TYPE,
         ult_camp_prog_dict   edu_histo_aptas.ult_camp_prog_dict%TYPE,
         est_capa             edu_histo_aptas.est_capa%TYPE,
         cam_capa							edu_histo_aptas.CAM_CAPA%TYPE,
         num_invi             edu_histo_aptas.num_invi%TYPE,
         COD_PLAN_PROG  			edu_histo_aptas.COD_PLAN_PROG%TYPE
      );

      TYPE tabla_temporal IS TABLE OF registro_val;

      tablaregistro      tabla_temporal;
      ls_sqlerrm         VARCHAR2 (150);
      lssql              VARCHAR2 (6)   := 'SQL013';
      lsestadoactivo     VARCHAR2 (1)   := '1';
      lnconterrors       NUMBER         := 0;
      lncontexitos       NUMBER         := 0;
      lsestadocapac      VARCHAR (2)    := 'CP';
      lnnumregisproc     NUMBER         := 0;
      lnnumregistros     NUMBER         := 0;
      lscampactual       VARCHAR2 (6);
      lnnumitem          NUMBER         := 0;
      lnccont            NUMBER         := 0;
      lsestadook         VARCHAR2 (1)   := '1';
      lsestadonok        VARCHAR2 (1)   := '0';
      lsobservacionok    VARCHAR2 (50)  := 'Aplica Validación';
      lsobservacionnok   VARCHAR2 (70)
         := 'No Aplica Validación para campaña menores o mayores de la Actual';
      valor           BOOLEAN;

	  lnCont		  NUMBER:=0;
      CURSOR cursorinvconcosto
      IS
    		SELECT  A.CLIE_COD_CLIE,
						    A.CCAP_COD_CURS_CAPA,
						  A.CAM_PRIM_CALI_APTA,
						  A.CAM_ULTI_CALI_APTA,
						  A.ULT_CAMP_PROG_DICT,
						  A.EST_CAPA,
						  A.CAM_CAPA,
						  A.NUM_INVI,
						  A.COD_PLAN_PROG
						  FROM EDU_HISTO_APTAS A,EDU_PARAM_CURSO_CAPAC B
						  WHERE A.PAIS_COD_PAIS= pscodigopais
						        AND A.EMCO_COD_EMPR_COME= pscodigoempresa
								AND A.PAIS_COD_PAIS = B.PAIS_COD_PAIS
								AND A.EMCO_COD_EMPR_COME = B.EMCO_COD_EMPR_COME
								AND A.CCAP_COD_CURS_CAPA = B.COD_CURS_CAPA
								AND B.TICC_COD_TIPO_COST_CURS='02'
								AND A.COD_PLAN_PROG <> '9999999999'
						  ORDER BY A.CAM_PRIM_CALI_APTA;

   BEGIN
      --Se valida que el registro de nose encuentre ya en la tabla de auditoria
      SELECT COUNT (1)
        INTO lnnumregistros
        FROM edu_audit_cabec a
       WHERE a.pais_cod_pais = pscodigopais
         AND a.emco_cod_empr_come = pscodigoempresa
         AND a.auqu_cod_audi_quer = lssql;

      --  AND A.CAM_PROC=psCampanha;
       --se borran los detalles
      DELETE FROM edu_audit_detal
            WHERE pais_cod_pais = pscodigopais
              AND emco_cod_empr_come = pscodigoempresa
              AND auqu_cod_audi_quer = lssql;

      -- AND CAM_PROC=psCampanha;
      IF (lnnumregistros = 0)
      THEN
         --INSERT EN LA TABLA EDU_AUDIT_CABEC
         INSERT INTO edu_audit_cabec
                     (pais_cod_pais, emco_cod_empr_come, auqu_cod_audi_quer,
                      usu_digi, fec_digi, est_regi, obs_proc,
                      est_proc
                     )
              VALUES (pscodigopais, pscodigoempresa, lssql,
                      psusuario, SYSDATE, lsestadoactivo, lsobservacionok,
                      lsestadook
                     );
      END IF;


      OPEN cursorinvconcosto;

      LOOP
         FETCH cursorinvconcosto
         BULK COLLECT INTO tablaregistro LIMIT w_filas;

         EXIT WHEN tablaregistro.COUNT = 0;

         FOR i IN tablaregistro.FIRST .. tablaregistro.LAST
         LOOP

               lnnumitem := lnnumitem + 1;

     					--Por cada Registro se Procesa
						  valor:=TRUE;--NO HAY ERROR
								--N REGISDTROS + 1
							 SELECT COUNT(1) INTO lnCont
							  FROM EDU_HISTO_CALIF_APTAS C
							  WHERE C.PAIS_COD_PAIS=pscodigopais
							   AND C.EMCO_COD_EMPR_COME=pscodigoempresa
							   AND C.CCAP_COD_CURS_CAPA=tablaregistro (i).ccap_cod_curs_capa
							   AND C.CLIE_COD_CLIE=tablaregistro (i).clie_cod_clie
							   AND C.TIP_CALI_APTA<>'E'
							   AND C.NUM_INVI<>0
							   AND C.EST_REGI='1';

							 valor:=valor AND (lnCont=tablaregistro (i).num_invi);


						     FOR j IN 1 .. tablaregistro (i).num_invi LOOP
     							 SELECT COUNT(1) INTO lnCont
								  FROM EDU_HISTO_CALIF_APTAS C
								  WHERE C.PAIS_COD_PAIS=pscodigopais
								   AND C.EMCO_COD_EMPR_COME=pscodigoempresa
								   AND C.CCAP_COD_CURS_CAPA=tablaregistro (i).ccap_cod_curs_capa
								   AND C.CLIE_COD_CLIE=tablaregistro (i).clie_cod_clie
								   AND C.TIP_CALI_APTA<>'E'
								   AND C.EST_REGI='1'
								   AND C.NUM_INVI=j;
							       valor:=valor AND (lnCont=1);
							  END LOOP;


					     --EXISTE EL REGISTRO VALIDO
					     SELECT COUNT(1) into lnCont
						  FROM EDU_HISTO_CALIF_APTAS C
						  WHERE C.PAIS_COD_PAIS=pscodigopais
						   AND C.EMCO_COD_EMPR_COME=pscodigoempresa
						   AND C.CAM_proc = tablaregistro (i).CAM_ULTI_CALI_APTA
						   AND C.CCAP_COD_CURS_CAPA=tablaregistro (i).ccap_cod_curs_capa
						   AND C.CLIE_COD_CLIE=tablaregistro (i).clie_cod_clie
						   AND C.NUM_INVI=tablaregistro (i).num_invi
						   AND C.TIP_CALI_APTA<>'E'
						   AND C.EST_REGI='1';

						   valor:=valor AND (lnCont=1);

    --NO DEBERIA EXISTRI REGISTROS
				        SELECT COUNT(1) into lnCont
				  			FROM EDU_HISTO_CALIF_APTAS C
				  			WHERE C.PAIS_COD_PAIS=pscodigopais
				   			AND C.EMCO_COD_EMPR_COME=pscodigoempresa
				   			AND C.CAM_proc > tablaregistro (i).CAM_ULTI_CALI_APTA
				   			AND C.CCAP_COD_CURS_CAPA=tablaregistro (i).ccap_cod_curs_capa
				   			AND C.CLIE_COD_CLIE=tablaregistro (i).clie_cod_clie
							AND C.TIP_CALI_APTA<>'E'
				   			AND C.EST_REGI='1';

   							valor:=valor AND (lnCont=0);


--ha sido programada alguna vez
					     IF(tablaregistro (i).ULT_CAMP_PROG_DICT is not null)THEN
					     	 SELECT COUNT(1) into lnCont
							   FROM EDU_PLANI_PROGR_CURSO P
							   WHERE P.PAIS_COD_PAIS=pscodigopais
							    AND P.EMCO_COD_EMPR_COME=pscodigoempresa
								  AND P.CAM_proc =tablaregistro (i).ULT_CAMP_PROG_DICT
								  AND P.CCAP_COD_CURS_CAPA=tablaregistro(i).ccap_cod_curs_capa
								  AND P.COD_PLAN_PROG <> '9999999999'
								  AND P.CLIE_COD_CLIE = tablaregistro(i).clie_cod_clie;

					        valor:=valor AND (lnCont=1);
					     END IF;


    --SI ES PR
					     IF(tablaregistro (i).EST_CAPA='PR')THEN
					     	 SELECT COUNT(1) into lnCont
							   FROM EDU_PLANI_PROGR_CURSO P
							   WHERE P.PAIS_COD_PAIS=pscodigopais
							    AND P.EMCO_COD_EMPR_COME=pscodigoempresa
								  AND P.CAM_proc =tablaregistro (i).ULT_CAMP_PROG_DICT
								  AND P.CCAP_COD_CURS_CAPA=tablaregistro(i).ccap_cod_curs_capa
								  AND P.CLIE_COD_CLIE = tablaregistro(i).clie_cod_clie
								  AND P.COD_PLAN_PROG <> '9999999999'
								  AND P.NUM_INVI=tablaregistro(i).num_invi;

					        valor:=valor AND (lnCont=1);
					     END IF;

		--SI ES CP

				     IF(tablaregistro (i).EST_CAPA='CP')THEN
								SELECT COUNT(1) into lnCont
								FROM EDU_HISTO_CAPAC_DETAL X
								WHERE X.PAIS_COD_PAIS=pscodigopais
								 AND X.EMCO_COD_EMPR_COME=pscodigoempresa
								 AND X.CLIE_COD_CLIE=tablaregistro (i).clie_cod_clie
								 AND X.CCAP_COD_CURS_CAPA=tablaregistro (i).ccap_cod_curs_capa
								 AND X.COD_PLAN_PROG <> '9999999999'
								 AND X.NUM_INVI=tablaregistro(i).num_invi;

								 valor:=valor AND (lnCont=1);
							END IF;


				IF(VALOR)THEN
						 lncontexitos := lncontexitos + 1;

     						INSERT INTO edu_audit_detal
                              (pais_cod_pais, emco_cod_empr_come,
                               auqu_cod_audi_quer, cam_proc,
                               num_item, cod_clie,
                               cod_curs_capa,
                               ult_camp_prog_dict,
                               est_regi_proc, usu_digi, fec_digi,
                               est_regi,
							   est_capa
                              )
                       VALUES (pscodigopais, pscodigoempresa,
                               lssql, tablaregistro (i).cam_ulti_cali_apta,
                               lnnumitem, tablaregistro (i).clie_cod_clie,
                               tablaregistro (i).ccap_cod_curs_capa,
                               tablaregistro (i).ult_camp_prog_dict,
                               lsestadook, psusuario, SYSDATE,
                               lsestadoactivo,
                               tablaregistro (i).est_capa
                              );


				ELSE --HAY ERROR
						 lnconterrors := lnconterrors + 1;

                  INSERT INTO edu_audit_detal
                              (pais_cod_pais, emco_cod_empr_come,
                               auqu_cod_audi_quer, cam_proc,
                               num_item, cod_clie,
                               cod_curs_capa,
                               ult_camp_prog_dict,
                               est_regi_proc, usu_digi, fec_digi,
                               est_regi,
							   est_capa
                              )
                       VALUES (pscodigopais, pscodigoempresa,
                               lssql, tablaregistro (i).cam_ulti_cali_apta,
                               lnnumitem, tablaregistro (i).clie_cod_clie,
                               tablaregistro (i).ccap_cod_curs_capa,
                               tablaregistro (i).ult_camp_prog_dict,
                               lsestadonok, psusuario, SYSDATE,
                               lsestadoactivo,
                               tablaregistro (i).est_capa
                              );

				END IF;



        END LOOP;
      END LOOP;

      CLOSE cursorinvconcosto;


      --UPDATE AUDITORIA CABECERA
      UPDATE edu_audit_cabec a
         SET a.num_regi_corr = lncontexitos,
             a.num_regi_erro = lnconterrors,
             a.num_regi_proc = lncontexitos + lnconterrors,
             a.est_proc = DECODE (lnconterrors, 0, lsestadook, lsestadonok),
             a.usu_modi = psusuario,
             a.fec_modi = SYSDATE
       WHERE a.pais_cod_pais = pscodigopais
         AND a.emco_cod_empr_come = pscodigoempresa
         AND a.auqu_cod_audi_quer = lssql;

      RETURN;
   EXCEPTION
      WHEN OTHERS
      THEN
         ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
         raise_application_error (-20123,
                                     'EDU_PR_VALID_INVIT_CURSO_CON_COSTO: '
                                  || ls_sqlerrm
                                 );
   END edu_pr_valid_curso_con_costo;



/***************************************************************************
Descripcion       : Proceso que se encarga de efectuar el proceso de exoneracion en forma masiva
Fecha Creacion    : 14/04/2010
Autor             : Sergio Buchelli Silva
Parametros:
          psCodigoPais              : Codigo de Pais
          psCodigoEmpresa           : Codigo de Empresa
		  pscurso                   : Curso
		  psusuario                 : Usuario
Parametros Salida   : psMensajeError: Mensaje de Error
***************************************************************************/
   PROCEDURE EDU_PR_EXONE_ASIST_MASIV(
      pscodigopais            VARCHAR2,
      pscodigoempresa         VARCHAR2,
	    pscurso                 VARCHAR2,
	    psusuario               VARCHAR2,
      psmensajeerror    OUT   VARCHAR2
   )
   IS

    CURSOR cursorregistro
      IS
         SELECT  a.cod_pais, a.cod_empr_come, a.cod_clie
		  FROM edu_tmp_recep_clien_estab a
            WHERE a.cod_pais = pscodigopais
              AND a.cod_empr_come = pscodigoempresa
              AND a.est_regi = '1';


    TYPE tregtemporal IS RECORD (
         cod_pais        edu_tmp_recep_clien_estab.cod_pais%TYPE,
         cod_empr_come   edu_tmp_recep_clien_estab.cod_empr_come%TYPE,
         cod_clie        edu_tmp_recep_clien_estab.cod_clie%TYPE
      );

   TYPE tabla_temporal IS TABLE OF tregtemporal;
   tablaregistro             tabla_temporal;
   regregistro               tregtemporal;

   lnExisteClient NUMBER:=0;
   lsretorno BOOLEAN:=FALSE;
   lscont NUMBER:=0;
   lsprereque EDU_PARAM_CURSO_CAPAC.COD_CURS_CAPA%TYPE;
   lsEstadoCapac EDU_ESTAD_CAPAC.COD_ESTA_CAPA%TYPE;

  BEGIN
	-- SE OBTINE PREREQUISITO

    SELECT pre_requ_capa
        INTO lsprereque
        FROM edu_param_curso_capac a
       WHERE a.pais_cod_pais = pscodigopais
         AND a.emco_cod_empr_come = pscodigoempresa
         AND a.cod_curs_capa = pscurso
         AND a.est_curs_capa = 'A'
         AND a.est_regi = '1';


    --recorre cursor
    OPEN cursorregistro;

      LOOP
         FETCH cursorregistro
         BULK COLLECT INTO tablaregistro LIMIT w_filas;

         IF tablaregistro.COUNT > 0
         THEN
            FOR x IN tablaregistro.FIRST .. tablaregistro.LAST
                LOOP
                   regregistro := tablaregistro (x);
                   --validar que cliente existe
                   SELECT COUNT(1) INTO lnExisteClient
                   FROM EDU_MAEST_CLIEN A
                   WHERE A.PAIS_COD_PAIS = pscodigopais
                    AND A.EMCO_COD_EMPR_COME = pscodigoempresa
                    AND A.COD_CLIE = regregistro.cod_clie;

                    IF(lnExisteClient > 0) THEN

                      --SITUACION ACTUAL EN EL CURSO
                      -- SE EXONERA SI ESTA EN PC O NO EXISTE EN APTAS Y CAPACITADA EN EL PREREQUISITO

                      --SE VERIFICA ESTADO EN PREREQUISITO
                          IF (lsprereque IS NOT NULL AND lsprereque = '00') THEN
                             lsretorno := TRUE;
                          ELSE
                             --SE VALIDA QUE EL ESTADO EN EL PREREQUISITO SEA CAPACITADO
                             SELECT COUNT (1)
                               INTO lscont
                               FROM edu_histo_aptas a
                              WHERE a.pais_cod_pais = pscodigopais
                                AND a.emco_cod_empr_come = pscodigoempresa
                                AND a.clie_cod_clie = regregistro.cod_clie
                                AND a.ccap_cod_curs_capa = lsprereque
                                AND a.est_capa = 'CP';

                             IF (lscont > 0)
                             THEN
                                --se encuentra capcitado
                                lsretorno := TRUE;
                             ELSE
                                lsretorno := FALSE;
                             END IF;

                           END IF;--FIN DEL PREREQUISITO

                      --SE VERIFICA SITUACION EN EL APTAS
                         BEGIN
                          SELECT X.EST_CAPA INTO lsEstadoCapac
                          FROM EDU_HISTO_APTAS X
                          WHERE X.PAIS_COD_PAIS = pscodigopais
                           AND X.EMCO_COD_EMPR_COME = pscodigoempresa
                           AND X.CCAP_COD_CURS_CAPA = pscurso
                           AND X.CLIE_COD_CLIE = regregistro.cod_clie;
                         EXCEPTION
                          WHEN NO_DATA_FOUND THEN
                            lsEstadoCapac:='PC';
                         END;

                      IF(lsEstadoCapac = 'PC' AND lsretorno ) THEN

                          EDU_PR_EXONE_ASIST(
                            pscodigopais,
                            pscodigoempresa,
                            regregistro.cod_clie,
                            pscurso,
                            psusuario,
                            psmensajeerror);

                       END IF;

                    END IF;    --FIN CLIENT EXISTE


                END LOOP;
         END IF;

         EXIT WHEN cursorregistro%NOTFOUND;
      END LOOP;

    CLOSE cursorregistro; --FIN EXONERACION

  EXCEPTION
      WHEN OTHERS
      THEN
         ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
         psmensajeerror := ls_sqlerrm;
         raise_application_error (-20123,
                                     'EDU_PR_EXONE_ASIST_MASIV: '
                                  || ls_sqlerrm
                                 );

  END EDU_PR_EXONE_ASIST_MASIV;

END edu_pkg_proce_gener;
/

