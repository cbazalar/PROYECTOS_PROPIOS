CREATE OR REPLACE PACKAGE EDU_PKG_INTER  IS

   /* Declaracion de Tipos */
   TYPE TIPOCURSOR IS  REF CURSOR;

   /* Declaracion de constantes */
   INDICADOR_PENDIENTE      VARCHAR2(2) := 'PC';
   INDICADOR_POR_PROGRAMAR  VARCHAR2(2) := 'PP';
   INDICADOR_PROGRAMADA     VARCHAR2(2) := 'PR';
   INDICADOR_CAPACITADA     VARCHAR2(2) := 'CP';

   /* Declaracion de Variables */
   ln_sqlcode   NUMBER(10);
   ls_sqlerrm   VARCHAR2(150);
   W_FILAS      NUMBER:=1000;

/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Paises
Fecha Creacion    : 21/08/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
***************************************************************************/
PROCEDURE INT_PR_DAT_PAIS
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2
);

/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Empresas
Fecha Creacion    : 21/08/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
***************************************************************************/
PROCEDURE INT_PR_DAT_EMPRE
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2
);

/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Programas
Fecha Creacion    : 21/08/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
***************************************************************************/
PROCEDURE INT_PR_DAT_PROGR
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2
);

/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Cursos
Fecha Creacion    : 21/08/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
***************************************************************************/
PROCEDURE INT_PR_DAT_CURSO
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2
);

/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Instructoras
Fecha Creacion    : 21/08/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
***************************************************************************/
PROCEDURE INT_PR_DAT_INSTR
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2
);

/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Instructoras x Geografia
Fecha Creacion    : 21/08/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
***************************************************************************/
PROCEDURE INT_PR_DAT_INSTR_GEOGR
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2
);

/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Cursos x ambito
Fecha Creacion    : 21/08/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
***************************************************************************/
PROCEDURE INT_PR_DAT_CURSO_AMBIT
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2
);


/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Clientes x Geografia
Fecha Creacion    : 21/08/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion     : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_CLIEN_GEOGR
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
);

/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Clientes x Geografia (REENVIO)
Fecha Creacion    : 27/09/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion     : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_CLIEN_GEOGR_REENV
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
);

/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Capacitacion de Aptas
Fecha Creacion    : 21/08/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion     : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_CAPAC_APTAS
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
);

/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Capacitacion de Aptas (Reenvio)
Fecha Creacion    : 28/08/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion     : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_CAPAC_APTAS_REENV
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
);

/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Capacitadas Programadas
Fecha Creacion    : 21/08/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion     : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_CAPAC_PROGR
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
);

/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Capacitadas Programadas
                    Reenvio
Fecha Creacion    : 28/08/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion     : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_CAPAC_PROGR_REENV
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
);


/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Cursos Dictados
Fecha Creacion    : 27/08/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion     : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_CURSO_DICTA
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
);

/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Cursos Dictados (Reenvio)
Fecha Creacion    : 27/08/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion     : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_CURSO_DICTA_REENV
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
);


/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Cursos Dictados Historico
Fecha Creacion    : 27/08/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion     : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_CURSO_DICTA_HISTO
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
);

/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Cursos Dictados Historico
                    (Reenvio)
Fecha Creacion    : 27/08/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion     : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_CURSO_DICTA_HISTO_R
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
);


/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Capacitacion Cliente
Fecha Creacion    : 27/08/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion     : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_CAPAC_CLIEN
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
);

/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Capacitacion Cliente
                    (Reenvio)
Fecha Creacion    : 27/08/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion     : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_CAPAC_CLIEN_REENV
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
);

/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Capacitacion Cliente
Fecha Creacion    : 27/08/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion      : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_CAPAC_CURSO_CLIEN
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
);

/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Capacitacion Cliente
                    (REENVIO)
Fecha Creacion    : 27/08/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion      : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_CAPAC_CURSO_CLIEN_R
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
);

/***************************************************************************
Descripcion       : Actualiza Interfase Datamart
Fecha Creacion    : 24/09/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion      : Codigo de Region
            psUsuario        : Usuario
***************************************************************************/
PROCEDURE EDU_PR_ACTUA_INTER_DATAM
  (psCodigoPais               VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2,
   psUsuario                  VARCHAR2
);

/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Objetivos Asistencia Anual
Fecha Creacion    : 15/10/2007
Autor             : Robinson Vela Bardales
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion      : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_OBJET_PAIS_ANUAL
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
);

/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Objetivos Asistencia Periodo
Fecha Creacion    : 15/10/2007
Autor             : Robinson Vela Bardales
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion      : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_OBJET_PAIS_PERIO
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
);

/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Objetivos Asistencia Región Anual
Fecha Creacion    : 15/10/2007
Autor             : Robinson Vela Bardales
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion      : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_OBJET_REGIO_ANUAL
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
);
/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Objetivos Asistencia Región Periodo
Fecha Creacion    : 15/10/2007
Autor             : Robinson Vela Bardales
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion      : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_OBJET_REGIO_PERIO
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
);

/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Objetivos Asistencia Zona Anual
Fecha Creacion    : 15/10/2007
Autor             : Robinson Vela Bardales
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion      : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_OBJET_ZONA_ANUAL
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
);
/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Objetivos Asistencia Región Periodo
Fecha Creacion    : 15/10/2007
Autor             : Robinson Vela Bardales
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion      : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_OBJET_ZONA_PERIO
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
);
/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar File Control
Fecha Creacion    : 15/10/2007
Autor             : Robinson Vela Bardales
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion      : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_FILE_CNTRL
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
);
/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Archivos de Control
Fecha Creacion    : 30/06/2010
Autor             : Carlos Diaz Valverde
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion      : Codigo de Region
            psTipoEnvio      : Tipo de envio
***************************************************************************/
PROCEDURE INT_PR_DAT_CNTRL
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psIndSistema               VARCHAR2,
   psIndCierre                VARCHAR2
);

END EDU_PKG_INTER;
/

CREATE OR REPLACE PACKAGE BODY EDU_PKG_INTER
IS

/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Paises
Fecha Creacion    : 21/08/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
***************************************************************************/
PROCEDURE INT_PR_DAT_PAIS
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2
)
IS
   CURSOR c_interfaz IS
      SELECT
           COD_PAIS,
           DES_PAIS,
         '0' AS FLA_CONT
      FROM BAS_PAIS
      WHERE COD_PAIS = psCodigoPais
        AND EST_PAIS <> '9'
      ORDER BY COD_PAIS;

   TYPE interfaz IS RECORD   (
     codigoPais          VARCHAR2(3),
     descripcionPais     VARCHAR2(50),
     flagControl         VARCHAR2(1)
   );

   TYPE interfazTab  IS TABLE OF interfaz ;
   interfazRecord interfazTab;
   lsCodigoPaisData    EDU_PARAM_PROGR_CAPAC.COD_PAIS_DATA%TYPE;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;
   W_DESC              VARCHAR2(200);
   lsLinea             VARCHAR2(1000);
   lsLineaCabecera     VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);


BEGIN
   /* Procedimiento inicial para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
        psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

    lsCodigoPaisData := EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_PAIS_DATAM(psCodigoPais, psCodEmpresa, '01');

    /* Generando Archivo de Texto (Detalle) */
    OPEN c_interfaz;
    LOOP
       FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          lsLinea :=  lsCodigoPaisData                ||';'||
                      interfazRecord(x).descripcionPais                 ||';'||
                      interfazRecord(x).flagControl ;
              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    utl_file.fclose(V_HANDLE);

    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);

    RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_DAT_PAIS: '||ls_sqlerrm);
END INT_PR_DAT_PAIS;


/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Empresas
Fecha Creacion    : 21/08/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
***************************************************************************/
PROCEDURE INT_PR_DAT_EMPRE
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2
)
IS
   CURSOR c_interfaz IS
      SELECT
        PAIS_COD_PAIS,
        COD_EMPR_COME,
     DES_EMPR_COME,
      '0' AS FLA_CONT
      FROM
           EDU_EMPRE_COMER
      WHERE PAIS_COD_PAIS = psCodigoPais
        AND COD_EMPR_COME = psCodEmpresa
        AND EST_REGI <> '9'
      ORDER BY PAIS_COD_PAIS, COD_EMPR_COME;

   TYPE interfaz IS RECORD   (
     codigoPais          VARCHAR2(3),
     codigoEmpresa       VARCHAR2(4),
     descripcionEmpresa  VARCHAR2(100),
     flagControl         VARCHAR2(1)
   );

   TYPE interfazTab  IS TABLE OF interfaz ;
   interfazRecord interfazTab;
   lsCodigoPaisData    EDU_PARAM_PROGR_CAPAC.COD_PAIS_DATA%TYPE;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;
   W_DESC              VARCHAR2(200);
   lsLinea             VARCHAR2(1000);
   lsLineaCabecera     VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

BEGIN
    /* Procedimiento inicial para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
        psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

    /* Generando Archivo de Texto (Detalle) */
    lsCodigoPaisData := EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_PAIS_DATAM(psCodigoPais, psCodEmpresa, '01');
    OPEN c_interfaz;
    LOOP
       FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          lsLinea :=  lsCodigoPaisData                    ||';'||
                      interfazRecord(x).codigoEmpresa                 ||';'||
                      interfazRecord(x).descripcionEmpresa            ||';'||
                      interfazRecord(x).flagControl ;
              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    utl_file.fclose(V_HANDLE);

    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);

    RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_DAT_EMPRE: '||ls_sqlerrm);
END INT_PR_DAT_EMPRE;


/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Programas
Fecha Creacion    : 21/08/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
***************************************************************************/
PROCEDURE INT_PR_DAT_PROGR
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2
)
IS
   CURSOR c_interfaz IS
     SELECT
       PAIS_COD_PAIS,
       EMCO_COD_EMPR_COME,
     COD_PROG_CAPA,
    DES_PROG_CAPA,
     '0' AS FLA_CONT
      FROM EDU_PARAM_PROGR_CAPAC
      WHERE EST_REGI <> '9'
        AND PAIS_COD_PAIS = psCodigoPais
        AND EMCO_COD_EMPR_COME = psCodEmpresa
      ORDER BY PAIS_COD_PAIS, EMCO_COD_EMPR_COME, COD_PROG_CAPA;

   TYPE interfaz IS RECORD   (
     codigoPais          VARCHAR2(3),
     codigoEmpresa       VARCHAR2(4),
     codigoPrograma      VARCHAR2(3),
     descripcionPrograma VARCHAR2(60),
     flagControl         VARCHAR2(1)
   );

   TYPE interfazTab  IS TABLE OF interfaz ;
   interfazRecord interfazTab;
   lsCodigoPaisData    EDU_PARAM_PROGR_CAPAC.COD_PAIS_DATA%TYPE;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;
   W_DESC              VARCHAR2(200);
   lsLinea             VARCHAR2(1000);
   lsLineaCabecera     VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

BEGIN
    /* Procedimiento inicial para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
        psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

    /* Generando Archivo de Texto (Detalle) */
    lsCodigoPaisData := EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_PAIS_DATAM(psCodigoPais, psCodEmpresa, '01');
    OPEN c_interfaz;
    LOOP
       FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          lsLinea :=  lsCodigoPaisData                   ||';'||
                      interfazRecord(x).codigoEmpresa                 ||';'||
                      interfazRecord(x).codigoPrograma                ||';'||
                      interfazRecord(x).descripcionPrograma           ||';'||
                      interfazRecord(x).flagControl ;
              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    utl_file.fclose(V_HANDLE);

    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);

    RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_DAT_PROGR: '||ls_sqlerrm);
END INT_PR_DAT_PROGR;


/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Cursos
Fecha Creacion    : 21/08/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
***************************************************************************/
PROCEDURE INT_PR_DAT_CURSO
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2
)
IS
   CURSOR c_interfaz IS
   SELECT
      A.PAIS_COD_PAIS,
      A.EMCO_COD_EMPR_COME,
      A.COD_CURS_CAPA,
      A.NOM_CURS_CAPA,
      A.SIG_CURS_CAPA,
      SUBSTR(A.OBJ_CURS_CAPA,1,200),
      C.DES_FREC_CALI,
      A.CAM_INIC,
      A.NUM_CAMP,
   A.NUM_CAMP_PREV_CALI,
   B.DES_AMBI_DICT,
   A.DUR_CURS_SESI,
   A.PRE_REQU_CAPA,
   A.NUM_CAMP_CUMP_PREQ,
   A.IND_CALI_APTA_PRIM_PEDI,
   E.DES_SECU_PEDI,
   A.NUM_CAMP_EVAL,
   A.NUM_PEDI_REQU,
   A.IND_CTRL_MORO,
      D.DES_NIVE_VENT,
   A.IND_EXON_ASIS,
   A.NUM_CAMP_MAXI_ASRE,
   A.IND_DICT_EXMA,
   A.IND_COST_CURS,
   A.IND_INVI_CURS,
   A.IND_REGA_ASIS_CURS,
   A.IND_CALI_CTDA,
   A.IND_CALI_CDRA,
   A.EST_CURS_CAPA,
      A.NUM_INVI,
      A.VAL_CURS,
      A.NUM_CUOT_PAGA_CURS,
    '0' AS FLA_CONT
   FROM  EDU_PARAM_CURSO_CAPAC A,
       EDU_AMBIT_DICTA B,
      EDU_FRECU_CALIF C,
      EDU_NIVEL_VENTA D,
      EDU_SECUE_PEDID E

    WHERE A.EST_REGI <> '9'
      AND A.PAIS_COD_PAIS = psCodigoPais
      AND A.EMCO_COD_EMPR_COME = psCodEmpresa
    AND B.COD_AMBI_DICT = A.AMDI_COD_AMBI_DICT
    AND C.COD_FREC_CALI = A.FRCA_COD_FREC_CALI
    AND D.COD_NIVE_VENT = A.NIVE_COD_NIVE_VENT
    AND E.COD_SECU_PEDI = A.SEPE_COD_SECU_PEDI
    ORDER BY A.PAIS_COD_PAIS, A.EMCO_COD_EMPR_COME, A.COD_CURS_CAPA;


   TYPE interfaz IS RECORD   (
      codigoPais                   VARCHAR2(3),
      codigoEmpresa                VARCHAR2(4),
      codigoCurso                  VARCHAR2(3),
      descripcionCurso             VARCHAR2(60),
      siglaCurso                   VARCHAR2(20),
      objetivoCurso                VARCHAR2(200),
      desFrecuencia                VARCHAR2(60),
      annoCampannaInicioCurso      VARCHAR2(6),
      numCampanna                  NUMBER(4),
      numCampaPreviaApta           NUMBER(4),
      desAmbitoDictado             VARCHAR2(60),
      duracionSesion               NUMBER(4),
      requisito                    VARCHAR2(3),
      numCampaRequisito            NUMBER(4),
      indicadorAptaPrimerPedido    VARCHAR2(1),
      desSecuenciaPedido           VARCHAR2(60),
      numCampannaEvaluar           NUMBER(4),
      numPedidoRequisito           NUMBER(4),
      indicadorControlMorosidad    VARCHAR2(1),
      desNivelVenta                VARCHAR2(60),
      indicadorExonerada           VARCHAR2(1),
      numCampaMaxAsistencia        NUMBER(4),
      indicadorExtemporaneo        VARCHAR2(1),
      indicadorCostoCurso          VARCHAR2(1),
      indicadorInvitacion          VARCHAR2(1),
      indicadorRegalo              VARCHAR2(1),
      indicadorCaliCapacitada      VARCHAR2(1),
      indicadorCaliCapacitadora    VARCHAR2(1),
      estadoCurso                  VARCHAR2(1),
      numInvi                      NUMBER(4),
      valorCurso                   NUMBER(10,2),
      nroCoutaPagar                NUMBER(4),
      flagControl                  VARCHAR2(1)
   );

   TYPE interfazTab  IS TABLE OF interfaz ;
   interfazRecord interfazTab;
   lsCodigoPaisData    EDU_PARAM_PROGR_CAPAC.COD_PAIS_DATA%TYPE;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;
   W_DESC              VARCHAR2(200);
   lsLinea             VARCHAR2(1000);
   lsLineaCabecera     VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

BEGIN
    /* Procedimiento inicial para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
        psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

    /* Generando Archivo de Texto (Detalle) */
    lsCodigoPaisData := EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_PAIS_DATAM(psCodigoPais, psCodEmpresa, '01');
    OPEN c_interfaz;
    LOOP
       FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
             lsLinea :=   lsCodigoPaisData                    ||';'||
                          interfazRecord(x).codigoEmpresa                ||';'||
                          interfazRecord(x).codigoCurso                  ||';'||
                          interfazRecord(x).descripcionCurso             ||';'||
                          interfazRecord(x).siglaCurso                   ||';'||
                          interfazRecord(x).objetivoCurso                ||';'||
                          interfazRecord(x).desFrecuencia                ||';'||
                          interfazRecord(x).annoCampannaInicioCurso      ||';'||
                          TRIM(TO_CHAR(interfazRecord(x).numCampanna,'9999'))         ||';'||
                          TRIM(TO_CHAR(interfazRecord(x).numCampaPreviaApta,'9999'))       ||';'||
                          interfazRecord(x).desAmbitoDictado             ||';'||
                          TRIM(TO_CHAR(interfazRecord(x).duracionSesion,'9999'))         ||';'||
                          interfazRecord(x).requisito                    ||';'||
                          TRIM(TO_CHAR(interfazRecord(x).numCampaRequisito,'9999'))    ||';'||
                          interfazRecord(x).indicadorAptaPrimerPedido    ||';'||
                          interfazRecord(x).desSecuenciaPedido           ||';'||
                          TRIM(TO_CHAR(interfazRecord(x).numCampannaEvaluar,'9999'))   ||';'||
                          TRIM(TO_CHAR(interfazRecord(x).numPedidoRequisito,'9999'))   ||';'||
                          interfazRecord(x).indicadorControlMorosidad    ||';'||
                          interfazRecord(x).desNivelVenta                ||';'||
                          interfazRecord(x).indicadorExonerada           ||';'||
                          TRIM(TO_CHAR(interfazRecord(x).numCampaMaxAsistencia,'9999'))    ||';'||
                          interfazRecord(x).indicadorExtemporaneo        ||';'||
                          interfazRecord(x).indicadorCostoCurso          ||';'||
                          interfazRecord(x).indicadorInvitacion          ||';'||
                          interfazRecord(x).indicadorRegalo              ||';'||
                          interfazRecord(x).indicadorCaliCapacitada      ||';'||
                          interfazRecord(x).indicadorCaliCapacitadora    ||';'||
                          interfazRecord(x).estadoCurso                  ||';'||
                          TRIM(TO_CHAR(interfazRecord(x).numInvi,'9999'))    ||';'||
                          TRIM(TO_CHAR(interfazRecord(x).valorCurso,'99999.99'))    ||';'||
                          TRIM(TO_CHAR(interfazRecord(x).nroCoutaPagar,'9999'))    ||';'||
                          interfazRecord(x).flagControl;
             UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    utl_file.fclose(V_HANDLE);

    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);

    RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_DAT_CURSO: '||ls_sqlerrm);
END INT_PR_DAT_CURSO;


/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Instructoras
Fecha Creacion    : 21/08/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
***************************************************************************/
PROCEDURE INT_PR_DAT_INSTR
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2
)
IS
   CURSOR c_interfaz IS
     SELECT
      PAIS_COD_PAIS,
      EMCO_COD_EMPR_COME,
      COD_INST,
   APE_PATE,
   APE_MATE,
   PRI_NOMB,
      SEG_NOMB,
   CAM_INGR,
   TO_CHAR(FEC_INGR,'YYYYMMDD'),
   CAM_RETI,
   TO_CHAR(FEC_RETI,'YYYYMMDD'),
   SIT_INST,
   EMA_INST,
   EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_REGIO_INSTR(PAIS_COD_PAIS, EMCO_COD_EMPR_COME, COD_INST)  AS COD_REGI,
   TEJE_COD_TIPO_EJEC,
      '0' AS FLA_CONT
    FROM EDU_MAEST_INSTR
    WHERE EST_REGI <> '9'
      AND PAIS_COD_PAIS = psCodigoPais
      AND EMCO_COD_EMPR_COME = psCodEmpresa
    ORDER BY PAIS_COD_PAIS, EMCO_COD_EMPR_COME, COD_INST;

   TYPE interfaz IS RECORD   (
     codigoPais          VARCHAR2(3),
     codigoEmpresa       VARCHAR2(4),
     codigoEjecutiva     VARCHAR2(15),
     apellidoPaterno     VARCHAR2(25),
     apellidoMaterno     VARCHAR2(25),
     nombre              VARCHAR2(25),
     segundoNombre       VARCHAR2(25),
     annoCampannaIngreso VARCHAR2(6),
     fechaIngreso        VARCHAR2(8),
     annoCampannaRetiro  VARCHAR2(6),
     fechaRetiro         VARCHAR2(8),
     estadoEjecutiva     VARCHAR2(2),
     email               VARCHAR2(100),
     codigoRegion        VARCHAR2(2),
  tipoEjecutiva   VARCHAR2(2),
     flagControl         VARCHAR2(1)
   );

   TYPE interfazTab  IS TABLE OF interfaz ;
   interfazRecord interfazTab;
   lsCodigoPaisData    EDU_PARAM_PROGR_CAPAC.COD_PAIS_DATA%TYPE;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;
   W_DESC              VARCHAR2(200);
   lsLinea             VARCHAR2(1000);
   lsLineaCabecera     VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

BEGIN
    /* Procedimiento inicial para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
        psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

    /* Generando Archivo de Texto (Detalle) */
    lsCodigoPaisData := EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_PAIS_DATAM(psCodigoPais, psCodEmpresa, '01');
    OPEN c_interfaz;
    LOOP
       FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          lsLinea :=  lsCodigoPaisData       ||';'||
                      interfazRecord(x).codigoEmpresa       ||';'||
                      interfazRecord(x).codigoEjecutiva     ||';'||
                      interfazRecord(x).apellidoPaterno     ||';'||
                      interfazRecord(x).apellidoMaterno     ||';'||
                      interfazRecord(x).nombre              ||';'||
                      interfazRecord(x).segundoNombre       ||';'||
                      interfazRecord(x).annoCampannaIngreso ||';'||
                      interfazRecord(x).fechaIngreso        ||';'||
                      interfazRecord(x).annoCampannaRetiro  ||';'||
                      interfazRecord(x).fechaRetiro         ||';'||
                      interfazRecord(x).estadoEjecutiva  ||';'||
                      interfazRecord(x).email               ||';'||
                      interfazRecord(x).codigoRegion        ||';'||
       interfazRecord(x).tipoEjecutiva       ||';'||
                      interfazRecord(x).flagControl    ;
              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    utl_file.fclose(V_HANDLE);

    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);

    RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_DAT_INSTR: '||ls_sqlerrm);
END INT_PR_DAT_INSTR;


/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Instructoras x Geografia
Fecha Creacion    : 21/08/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
***************************************************************************/
PROCEDURE INT_PR_DAT_INSTR_GEOGR
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2
)
IS
   CURSOR c_interfaz IS
    SELECT
       A.PAIS_COD_PAIS,
       A.EMCO_COD_EMPR_COME,
       A.INST_COD_INST,
       A.COD_REGI,
      '0' AS FLA_CONT
    FROM EDU_REGIO A, EDU_MAEST_INSTR B
    WHERE A.EMCO_COD_EMPR_COME=B.EMCO_COD_EMPR_COME
    AND A.PAIS_COD_PAIS=B.PAIS_COD_PAIS
    AND A.EST_REGI <> '9'
    AND A.INST_COD_INST  = B.COD_INST
    AND A.PAIS_COD_PAIS = psCodigoPais
    AND A.EMCO_COD_EMPR_COME = psCodEmpresa
    ORDER BY PAIS_COD_PAIS, EMCO_COD_EMPR_COME, COD_REGI;



   TYPE interfaz IS RECORD   (
     codigoPais          VARCHAR2(3),
     codigoEmpresa       VARCHAR2(4),
     codigoEjecutiva     VARCHAR2(15),
     codigoRegion        VARCHAR2(2),
     flagControl         VARCHAR2(1)
   );

   TYPE interfazTab  IS TABLE OF interfaz ;
   interfazRecord interfazTab;
   lsCodigoPaisData    EDU_PARAM_PROGR_CAPAC.COD_PAIS_DATA%TYPE;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;
   W_DESC              VARCHAR2(200);
   lsLinea             VARCHAR2(1000);
   lsLineaCabecera     VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

BEGIN
    /* Procedimiento inicial para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
        psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

    /* Generando Archivo de Texto (Detalle) */
    lsCodigoPaisData := EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_PAIS_DATAM(psCodigoPais, psCodEmpresa, '01');
    OPEN c_interfaz;
    LOOP
       FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          lsLinea :=  lsCodigoPaisData         ||';'||
                      interfazRecord(x).codigoEmpresa       ||';'||
                      interfazRecord(x).codigoEjecutiva    ||';'||
                      interfazRecord(x).codigoRegion        ||';'||
                      interfazRecord(x).flagControl    ;
              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    utl_file.fclose(V_HANDLE);

    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);

    RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_DAT_INSTR_GEOGR: '||ls_sqlerrm);
END INT_PR_DAT_INSTR_GEOGR;



/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Cursos x Ambito
Fecha Creacion    : 21/08/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
***************************************************************************/
PROCEDURE INT_PR_DAT_CURSO_AMBIT
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2
)
IS
   CURSOR c_interfaz IS
     SELECT
       A.PAIS_COD_PAIS,
       A.EMCO_COD_EMPR_COME,
     A.CCAP_COD_CURS_CAPA,
       A.COD_REGI,
       A.COD_ZONA,
       B.COD_AMBI_DICT,
       B.DES_AMBI_DICT,
     '0' AS FLA_CONT
    FROM EDU_PARAM_CURSO_CAPAC_AMBIT A,
         EDU_AMBIT_DICTA B
    WHERE A.EST_REGI <> '9'
    AND A.PAIS_COD_PAIS = psCodigoPais
    AND A.EMCO_COD_EMPR_COME = psCodEmpresa
      AND B.COD_AMBI_DICT = A.AMDI_COD_AMBI_DICT
    ORDER BY A.PAIS_COD_PAIS, A.EMCO_COD_EMPR_COME, A.CCAP_COD_CURS_CAPA;

   TYPE interfaz IS RECORD   (
     codigoPais              VARCHAR2(3),
     codigoEmpresa           VARCHAR2(4),
     codigoCurso             VARCHAR2(3),
     codigoRegion            VARCHAR2(2),
     codigoZona              VARCHAR2(4),
     codigoAmbito            VARCHAR2(2),
     descripcionAmbito       VARCHAR2(60),
     flagControl             VARCHAR2(1)
   );

   TYPE interfazTab  IS TABLE OF interfaz ;
   interfazRecord interfazTab;
   lsCodigoPaisData    EDU_PARAM_PROGR_CAPAC.COD_PAIS_DATA%TYPE;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;
   W_DESC              VARCHAR2(200);
   lsLinea             VARCHAR2(1000);
   lsLineaCabecera     VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

BEGIN
    /* Procedimiento inicial para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
        psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

    /* Generando Archivo de Texto (Detalle) */
    lsCodigoPaisData := EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_PAIS_DATAM(psCodigoPais, psCodEmpresa, '01');
    OPEN c_interfaz;
    LOOP
       FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          lsLinea :=  lsCodigoPaisData         ||';'||
                      interfazRecord(x).codigoEmpresa           ||';'||
                      interfazRecord(x).codigoCurso             ||';'||
                      interfazRecord(x).codigoRegion            ||';'||
                      interfazRecord(x).codigoZona              ||';'||
                      interfazRecord(x).codigoAmbito            ||';'||
                      interfazRecord(x).descripcionAmbito       ||';'||
                      interfazRecord(x).flagControl  ;
              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    utl_file.fclose(V_HANDLE);

    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);

    RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_DAT_CURSO_AMBIT: '||ls_sqlerrm);
END INT_PR_DAT_CURSO_AMBIT;

/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Clientes x Geografia
Fecha Creacion    : 21/08/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion      : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_CLIEN_GEOGR
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
)
IS
   CURSOR c_interfaz IS
     SELECT DISTINCT
       A.PAIS_COD_PAIS,
       A.EMCO_COD_EMPR_COME,
       A.COD_CLIE,
       A.COD_REGI,
       A.COD_ZONA,
       A.APE_PATE,
       A.APE_MATE,
       A.APE_CASA,
       A.PRI_NOMB,
       A.SEG_NOMB,
       A.NUM_DOCU,
       TO_CHAR(A.FEC_NACI,'YYYYMMDD'),
       A.IND_MORO,
       A.NUM_TELE_PART,
       A.SAL_CLIE,
     '0' AS FLA_CONT
    FROM EDU_MAEST_CLIEN A,EDU_HISTO_CALIF_APTAS X
    WHERE A.EST_REGI <> '9'
      AND A.PAIS_COD_PAIS = psCodigoPais
      AND A.EMCO_COD_EMPR_COME = psCodEmpresa
	  AND X.CAM_proc = psCodPeriodo
      AND (psCodRegion IS NULL OR A.COD_REGI = psCodRegion)
      AND X.PAIS_COD_PAIS = A.PAIS_COD_PAIS
          AND X.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
            AND X.CLIE_COD_CLIE = A.COD_CLIE
            AND X.EST_REGI <> '9'
      AND NOT EXISTS
        (SELECT X.COD_CLIE
           FROM
             EDU_TMP_INTER_DATAM_CONSU X
           WHERE X.CAM_PROC = psCodPeriodo
             AND X.COD_PAIS = A.PAIS_COD_PAIS
             AND X.COD_EMPR_COME = A.EMCO_COD_EMPR_COME
             AND X.COD_REGI = A.COD_REGI
             AND X.COD_CLIE = A.COD_CLIE
             AND ((X.EST_CAPA = INDICADOR_PENDIENTE) OR
                 (X.EST_CAPA = INDICADOR_PROGRAMADA AND X.ULT_CAMP_DICT = psCodPeriodo ))
        )
    ORDER BY A.PAIS_COD_PAIS, A.EMCO_COD_EMPR_COME, A.COD_CLIE;

   TYPE interfaz IS RECORD   (
     codigoPais              VARCHAR2(3),
     codigoEmpresa           VARCHAR2(4),
     codigoConsultora        VARCHAR2(15),
     codigoRegion            VARCHAR2(2),
     codigoZona              VARCHAR2(4),
     apellidoPaterno         EDU_MAEST_CLIEN.APE_PATE%TYPE,
     apellidoMaterno         EDU_MAEST_CLIEN.APE_MATE%TYPE,
     apellidoCasada          EDU_MAEST_CLIEN.APE_CASA%TYPE,
     nombre                  EDU_MAEST_CLIEN.PRI_NOMB%TYPE,
     segundoNombre           EDU_MAEST_CLIEN.SEG_NOMB%TYPE,
     numeroDocumento         EDU_MAEST_CLIEN.NUM_DOCU%TYPE,
     fechaNacimiento         VARCHAR2(8),
     indicadorMorosidad      VARCHAR2(1),
     telefono                EDU_MAEST_CLIEN.NUM_TELE_PART%TYPE,
     saldoCliente            NUMBER(10,2),
     flagControl             VARCHAR2(1)
   );

   TYPE interfazTab  IS TABLE OF interfaz ;
   interfazRecord interfazTab;
   lsCodigoPaisData    EDU_PARAM_PROGR_CAPAC.COD_PAIS_DATA%TYPE;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;
   W_DESC              VARCHAR2(200);
   lsLinea             VARCHAR2(1000);
   lsLineaCabecera     VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

BEGIN
    /* Procedimiento inicial para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
        psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

    /* Generando Archivo de Texto (Detalle) */
    lsCodigoPaisData := EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_PAIS_DATAM(psCodigoPais, psCodEmpresa, '01');
    OPEN c_interfaz;
    LOOP
       FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          lsLinea :=  lsCodigoPaisData             ||';'||
                      interfazRecord(x).codigoEmpresa           ||';'||
                      interfazRecord(x).codigoConsultora        ||';'||
                      interfazRecord(x).codigoRegion            ||';'||
                      interfazRecord(x).codigoZona              ||';'||
                      interfazRecord(x).apellidoPaterno         ||';'||
                      interfazRecord(x).apellidoMaterno         ||';'||
                      interfazRecord(x).apellidoCasada          ||';'||
                      interfazRecord(x).nombre                  ||';'||
                      interfazRecord(x).segundoNombre           ||';'||
                      interfazRecord(x).numeroDocumento         ||';'||
                      interfazRecord(x).fechaNacimiento         ||';'||
                      interfazRecord(x).indicadorMorosidad      ||';'||
                      interfazRecord(x).telefono      ||';'||
                      TRIM(TO_CHAR(interfazRecord(x).saldoCliente,'99999999.99'))  ||';'||
                      interfazRecord(x).flagControl  ;
              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    utl_file.fclose(V_HANDLE);

    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);

    RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_DAT_CLIEN_GEOGR: '||ls_sqlerrm);
END INT_PR_DAT_CLIEN_GEOGR;


/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Clientes x Geografia (REENVIO) YA NO SE USA
Fecha Creacion    : 27/09/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion      : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_CLIEN_GEOGR_REENV
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
)
IS
   CURSOR c_interfaz IS
     SELECT DISTINCT
       A.PAIS_COD_PAIS,
       A.EMCO_COD_EMPR_COME,
       A.COD_CLIE,
       A.COD_REGI,
       A.COD_ZONA,
       A.APE_PATE,
       A.APE_MATE,
       A.APE_CASA,
       A.PRI_NOMB,
       A.SEG_NOMB,
       A.NUM_DOCU,
       TO_CHAR(A.FEC_NACI,'YYYYMMDD'),
       A.IND_MORO,
       A.NUM_TELE_PART,
       A.SAL_CLIE,
     '0' AS FLA_CONT
    FROM EDU_MAEST_CLIEN A,EDU_HISTO_CALIF_APTAS X
    WHERE A.EST_REGI <> '9'
      AND A.PAIS_COD_PAIS = psCodigoPais
      AND A.EMCO_COD_EMPR_COME = psCodEmpresa
	  AND X.CAM_proc = psCodPeriodo
      AND (psCodRegion IS NULL OR A.COD_REGI = psCodRegion)
      AND X.PAIS_COD_PAIS = A.PAIS_COD_PAIS
          AND X.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
            AND X.CLIE_COD_CLIE = A.COD_CLIE
            AND X.EST_REGI <> '9'
      AND EXISTS
        (SELECT X.COD_CLIE
           FROM
             EDU_TMP_INTER_DATAM_CONSU X
           WHERE X.CAM_PROC = psCodPeriodo
             AND X.COD_PAIS = A.PAIS_COD_PAIS
             AND X.COD_EMPR_COME = A.EMCO_COD_EMPR_COME
             AND X.COD_REGI = A.COD_REGI
             AND X.COD_CLIE = A.COD_CLIE
             AND ((X.EST_CAPA = INDICADOR_PENDIENTE) OR
                 (X.EST_CAPA = INDICADOR_PROGRAMADA AND X.ULT_CAMP_DICT = psCodPeriodo ))
        )
    ORDER BY A.PAIS_COD_PAIS, A.EMCO_COD_EMPR_COME, A.COD_CLIE;

   TYPE interfaz IS RECORD   (
     codigoPais              VARCHAR2(3),
     codigoEmpresa           VARCHAR2(4),
     codigoConsultora        VARCHAR2(15),
     codigoRegion            VARCHAR2(2),
     codigoZona              VARCHAR2(4),
     apellidoPaterno         EDU_MAEST_CLIEN.APE_PATE%TYPE,
     apellidoMaterno         EDU_MAEST_CLIEN.APE_MATE%TYPE,
     apellidoCasada          EDU_MAEST_CLIEN.APE_CASA%TYPE,
     nombre                  EDU_MAEST_CLIEN.PRI_NOMB%TYPE,
     segundoNombre           EDU_MAEST_CLIEN.SEG_NOMB%TYPE,
     numeroDocumento         EDU_MAEST_CLIEN.NUM_DOCU%TYPE,
     fechaNacimiento         VARCHAR2(8),
     indicadorMorosidad      VARCHAR2(1),
     telefono                EDU_MAEST_CLIEN.NUM_TELE_PART%TYPE,
     saldoCliente            NUMBER(10,2),
     flagControl             VARCHAR2(1)
   );

   TYPE interfazTab  IS TABLE OF interfaz ;
   interfazRecord interfazTab;
   lsCodigoPaisData    EDU_PARAM_PROGR_CAPAC.COD_PAIS_DATA%TYPE;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;
   W_DESC              VARCHAR2(200);
   lsLinea             VARCHAR2(1000);
   lsLineaCabecera     VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

BEGIN
    /* Procedimiento inicial para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
        psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

    /* Generando Archivo de Texto (Detalle) */
    lsCodigoPaisData := EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_PAIS_DATAM(psCodigoPais, psCodEmpresa, '01');
    OPEN c_interfaz;
    LOOP
       FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          lsLinea :=  lsCodigoPaisData          ||';'||
                      interfazRecord(x).codigoEmpresa           ||';'||
                      interfazRecord(x).codigoConsultora        ||';'||
                      interfazRecord(x).codigoRegion            ||';'||
                      interfazRecord(x).codigoZona              ||';'||
                      interfazRecord(x).apellidoPaterno         ||';'||
                      interfazRecord(x).apellidoMaterno         ||';'||
                      interfazRecord(x).apellidoCasada          ||';'||
                      interfazRecord(x).nombre                  ||';'||
                      interfazRecord(x).segundoNombre           ||';'||
                      interfazRecord(x).numeroDocumento         ||';'||
                      interfazRecord(x).fechaNacimiento         ||';'||
                      interfazRecord(x).indicadorMorosidad      ||';'||
                      interfazRecord(x).telefono      ||';'||
                      TRIM(TO_CHAR(interfazRecord(x).saldoCliente,'99999999.99'))  ||';'||
                      interfazRecord(x).flagControl  ;
              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    utl_file.fclose(V_HANDLE);

    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);

    RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_DAT_CLIEN_GEOGR_REENV: '||ls_sqlerrm);
END INT_PR_DAT_CLIEN_GEOGR_REENV;


/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Capacitacion de Aptas
Fecha Creacion    : 21/08/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion      : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_CAPAC_APTAS
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
)
IS
   CURSOR c_interfaz IS
    SELECT
       A.PAIS_COD_PAIS,
       A.EMCO_COD_EMPR_COME,
       A.CCAP_COD_CURS_CAPA,
       A.CLIE_COD_CLIE,
       A.COD_CURS_DICT,
       A.COD_PLAN_PROG,
       A.CAM_PRIM_CALI_APTA,
       A.CAM_ULTI_CALI_APTA,
       A.TIP_CALI_APTA,
       A.NUM_INVI,
       A.IND_INIC_CALI_APTA,
       A.IND_FINA_CALI_APTA,
       A.IND_CURS_COST,
       A.IND_CURS_FACT,
       A.CAM_FACT_CURS,
       A.CAM_ACEP,
       A.ULT_CAMP_PROG_DICT,
       A.EST_CAPA,
       '0' AS FLA_CONT
   FROM EDU_HISTO_CALIF_APTAS A,
        EDU_MAEST_CLIEN B
   WHERE A.PAIS_COD_PAIS = psCodigoPais
     AND A.EMCO_COD_EMPR_COME = psCodEmpresa
     AND A.CAM_PROC = psCodPeriodo
	 AND A.NUM_INVI > 0
     AND A.EST_REGI <> '9'
     AND (psCodRegion IS NULL OR B.COD_REGI = psCodRegion)
     AND B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
     AND B.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
     AND B.COD_CLIE = A.CLIE_COD_CLIE
     AND B.EST_REGI <> '9'
     AND NOT EXISTS
          (SELECT X.COD_CLIE
           FROM
             EDU_TMP_INTER_DATAM_CONSU X
           WHERE X.CAM_PROC = psCodPeriodo
             AND X.COD_PAIS = A.PAIS_COD_PAIS
             AND X.COD_EMPR_COME = A.EMCO_COD_EMPR_COME
             AND X.COD_REGI = B.COD_REGI
             AND X.COD_CLIE = B.COD_CLIE)
   ORDER BY A.PAIS_COD_PAIS, A.EMCO_COD_EMPR_COME, A.CCAP_COD_CURS_CAPA, A.CLIE_COD_CLIE;

   TYPE interfaz IS RECORD   (
     codigoPais              VARCHAR2(3),
     codigoEmpresa           VARCHAR2(4),
     codigoCurso             VARCHAR2(3),
     codigoConsultora        VARCHAR2(15),
     codigoCursoDictado      VARCHAR2(6),
     codigoPlanPrograma      VARCHAR2(10),
     campaPrimeraCaliApta    VARCHAR2(6),
     campaUltimaCaliApta     VARCHAR2(6),
     tipoCaliApta            VARCHAR2(1),
     numInvi                 NUMBER(4),
     indicadorIniCaliApta    VARCHAR2(1),
     indicadorFinCaliApta    VARCHAR2(1),
     indicadorCostoCurso     VARCHAR2(1),
     indicadorCursoFactu     VARCHAR2(1),
     campaCursoFactu         VARCHAR2(6),
     campaAceptacion         VARCHAR2(6),
     ultiCampaProgDicta      VARCHAR2(6),
     estadoCapacitacion      VARCHAR2(2),
     flagControl             VARCHAR2(1)
   );

   TYPE interfazTab  IS TABLE OF interfaz ;
   interfazRecord interfazTab;
   lsCodigoPaisData    EDU_PARAM_PROGR_CAPAC.COD_PAIS_DATA%TYPE;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;
   W_DESC              VARCHAR2(200);
   lsLinea             VARCHAR2(1000);
   lsLineaCabecera     VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

BEGIN
    /* Procedimiento inicial para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
        psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

    /* Generando Archivo de Texto (Detalle) */
    lsCodigoPaisData := EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_PAIS_DATAM(psCodigoPais, psCodEmpresa, '01');
    OPEN c_interfaz;
    LOOP
       FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          lsLinea :=  lsCodigoPaisData             ||';'||
                      interfazRecord(x).codigoEmpresa           ||';'||
                      interfazRecord(x).codigoCurso             ||';'||
                      interfazRecord(x).codigoConsultora        ||';'||
                      interfazRecord(x).codigoCursoDictado      ||';'||
                      interfazRecord(x).codigoPlanPrograma      ||';'||
                      interfazRecord(x).campaPrimeraCaliApta    ||';'||
                      interfazRecord(x).campaUltimaCaliApta     ||';'||
                      interfazRecord(x).tipoCaliApta            ||';'||
                      TRIM(TO_CHAR(interfazRecord(x).numInvi,'9999'))    ||';'||
                      interfazRecord(x).indicadorIniCaliApta    ||';'||
                      interfazRecord(x).indicadorFinCaliApta    ||';'||
                      interfazRecord(x).indicadorCostoCurso     ||';'||
                      interfazRecord(x).indicadorCursoFactu     ||';'||
                      interfazRecord(x).campaCursoFactu         ||';'||
                      interfazRecord(x).campaAceptacion         ||';'||
                      interfazRecord(x).ultiCampaProgDicta      ||';'||
                      interfazRecord(x).estadoCapacitacion      ||';'||
                      interfazRecord(x).flagControl  ;
              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    utl_file.fclose(V_HANDLE);

    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);

    RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_DAT_CAPAC_APTAS: '||ls_sqlerrm);
END INT_PR_DAT_CAPAC_APTAS;


/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Capacitacion de Aptas (Reenvio) YA NO SE USA
Fecha Creacion    : 28/08/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion      : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_CAPAC_APTAS_REENV
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
)
IS
   CURSOR c_interfaz IS
SELECT
       A.PAIS_COD_PAIS,
       A.EMCO_COD_EMPR_COME,
       A.CCAP_COD_CURS_CAPA,
       A.CLIE_COD_CLIE,
       A.COD_CURS_DICT,
       A.COD_PLAN_PROG,
       A.CAM_PRIM_CALI_APTA,
       A.CAM_ULTI_CALI_APTA,
       A.TIP_CALI_APTA,
       A.NUM_INVI,
       A.IND_INIC_CALI_APTA,
       A.IND_FINA_CALI_APTA,
       A.IND_CURS_COST,
       A.IND_CURS_FACT,
       A.CAM_FACT_CURS,
       A.CAM_ACEP,
       A.ULT_CAMP_PROG_DICT,
       A.EST_CAPA,
       '0' AS FLA_CONT
   FROM EDU_HISTO_CALIF_APTAS A,
        EDU_MAEST_CLIEN B
   WHERE A.PAIS_COD_PAIS = psCodigoPais
     AND A.EMCO_COD_EMPR_COME = psCodEmpresa
     AND A.CAM_PROC = psCodPeriodo
     AND A.EST_REGI <> '9'

     AND (psCodRegion IS NULL OR B.COD_REGI = psCodRegion )
     AND B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
     AND B.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
     AND B.COD_CLIE = A.CLIE_COD_CLIE
     AND B.EST_REGI <> '9'
     AND EXISTS
          (SELECT X.COD_CLIE
           FROM
             EDU_TMP_INTER_DATAM_CONSU X
           WHERE X.CAM_PROC = psCodPeriodo
             AND X.COD_PAIS = A.PAIS_COD_PAIS
             AND X.COD_EMPR_COME = A.EMCO_COD_EMPR_COME
             AND X.COD_REGI = B.COD_REGI
             AND X.COD_CLIE = B.COD_CLIE)
   ORDER BY A.PAIS_COD_PAIS, A.EMCO_COD_EMPR_COME, A.CCAP_COD_CURS_CAPA, A.CLIE_COD_CLIE;

   TYPE interfaz IS RECORD   (
     codigoPais              VARCHAR2(3),
     codigoEmpresa           VARCHAR2(4),
     codigoCurso             VARCHAR2(3),
     codigoConsultora        VARCHAR2(15),
     codigoCursoDictado      VARCHAR2(6),
     codigoPlanPrograma      VARCHAR2(10),
     campaPrimeraCaliApta    VARCHAR2(6),
     campaUltimaCaliApta     VARCHAR2(6),
     tipoCaliApta            VARCHAR2(1),
     numInvi                 NUMBER(4),
     indicadorIniCaliApta    VARCHAR2(1),
     indicadorFinCaliApta    VARCHAR2(1),
     indicadorCostoCurso     VARCHAR2(1),
     indicadorCursoFactu     VARCHAR2(1),
     campaCursoFactu         VARCHAR2(6),
     campaAceptacion         VARCHAR2(6),
     ultiCampaProgDicta      VARCHAR2(6),
     estadoCapacitacion      VARCHAR2(2),
     flagControl             VARCHAR2(1)
   );

   TYPE interfazTab  IS TABLE OF interfaz ;
   interfazRecord interfazTab;
   lsCodigoPaisData    EDU_PARAM_PROGR_CAPAC.COD_PAIS_DATA%TYPE;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;
   W_DESC              VARCHAR2(200);
   lsLinea             VARCHAR2(1000);
   lsLineaCabecera     VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

BEGIN
    /* Procedimiento inicial para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
        psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

    /* Generando Archivo de Texto (Detalle) */
    lsCodigoPaisData := EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_PAIS_DATAM(psCodigoPais, psCodEmpresa, '01');
    OPEN c_interfaz;
    LOOP
       FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          lsLinea :=  lsCodigoPaisData             ||';'||
                      interfazRecord(x).codigoEmpresa           ||';'||
                      interfazRecord(x).codigoCurso             ||';'||
                      interfazRecord(x).codigoConsultora        ||';'||
                      interfazRecord(x).codigoCursoDictado        ||';'||
                      interfazRecord(x).codigoPlanPrograma        ||';'||
                      interfazRecord(x).campaPrimeraCaliApta    ||';'||
                      interfazRecord(x).campaUltimaCaliApta     ||';'||
                      interfazRecord(x).tipoCaliApta            ||';'||
                      TRIM(TO_CHAR(interfazRecord(x).numInvi,'9999'))    ||';'||
                      interfazRecord(x).indicadorIniCaliApta            ||';'||
                      interfazRecord(x).indicadorFinCaliApta            ||';'||
                      interfazRecord(x).indicadorCostoCurso     ||';'||
                      interfazRecord(x).indicadorCursoFactu     ||';'||
                      interfazRecord(x).campaCursoFactu         ||';'||
                      interfazRecord(x).campaAceptacion         ||';'||
                      interfazRecord(x).ultiCampaProgDicta      ||';'||
                      interfazRecord(x).estadoCapacitacion      ||';'||
                      interfazRecord(x).flagControl  ;
              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    utl_file.fclose(V_HANDLE);

    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);

    RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_DAT_CAPAC_APTAS_REENV: '||ls_sqlerrm);
END INT_PR_DAT_CAPAC_APTAS_REENV;


/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Capacitadas Programadas
Fecha Creacion    : 21/08/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion      : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_CAPAC_PROGR
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
)
IS
   CURSOR c_interfaz IS
     SELECT
       A.PAIS_COD_PAIS,
       A.EMCO_COD_EMPR_COME,
       A.CCAP_COD_CURS_CAPA,
       A.COD_PLAN_PROG,
       A.CLIE_COD_CLIE,
       A.TIP_CALI_APTA,
       A.IND_CURS_COST,
       A.IND_CURS_FACT,
       A.CAM_FACT_CURS,
       '0' AS FLA_CONT
    FROM EDU_PLANI_PROGR_CURSO A,
         EDU_MAEST_CLIEN B
    WHERE A.PAIS_COD_PAIS = psCodigoPais
      AND A.EMCO_COD_EMPR_COME = psCodEmpresa
      AND A.CAM_PROC = psCodPeriodo
      AND A.EST_REGI <> '9'

      AND ( psCodRegion IS NULL OR B.COD_REGI = psCodRegion)
      AND B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
      AND B.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
      AND B.COD_CLIE = A.CLIE_COD_CLIE
      AND B.EST_REGI <> '9'
      AND NOT EXISTS
          (SELECT X.COD_CLIE
           FROM
             EDU_TMP_INTER_DATAM_CONSU X
           WHERE X.CAM_PROC = psCodPeriodo
             AND X.COD_PAIS = A.PAIS_COD_PAIS
             AND X.COD_EMPR_COME = A.EMCO_COD_EMPR_COME
             AND X.COD_REGI = B.COD_REGI
             AND X.COD_CLIE = B.COD_CLIE)
    ORDER BY A.PAIS_COD_PAIS, A.EMCO_COD_EMPR_COME, A.CCAP_COD_CURS_CAPA, A.CLIE_COD_CLIE;

   TYPE interfaz IS RECORD   (
     codigoPais              VARCHAR2(3),
     codigoEmpresa           VARCHAR2(4),
     codigoCurso             VARCHAR2(3),
     codigoPlanilla          VARCHAR2(10),
     codigoConsultora        VARCHAR2(15),
     tipoCaliApta            VARCHAR2(1),
     indicadorCostoCurso     VARCHAR2(1),
     indicadorCursoFactu     VARCHAR2(1),
     campaCursoFactu         VARCHAR2(6),
     flagControl             VARCHAR2(1)
   );

   TYPE interfazTab  IS TABLE OF interfaz ;
   interfazRecord interfazTab;
   lsCodigoPaisData    EDU_PARAM_PROGR_CAPAC.COD_PAIS_DATA%TYPE;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;
   W_DESC              VARCHAR2(200);
   lsLinea             VARCHAR2(1000);
   lsLineaCabecera     VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

BEGIN
    /* Procedimiento inicial para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
        psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

    /* Generando Archivo de Texto (Detalle) */
    lsCodigoPaisData := EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_PAIS_DATAM(psCodigoPais, psCodEmpresa, '01');
    OPEN c_interfaz;
    LOOP
       FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          lsLinea :=  lsCodigoPaisData              ||';'||
                      interfazRecord(x).codigoEmpresa           ||';'||
                      interfazRecord(x).codigoCurso             ||';'||
                      interfazRecord(x).codigoPlanilla          ||';'||
                      interfazRecord(x).codigoConsultora        ||';'||
                      interfazRecord(x).tipoCaliApta            ||';'||
                      interfazRecord(x).indicadorCostoCurso     ||';'||
                      interfazRecord(x).indicadorCursoFactu     ||';'||
                      interfazRecord(x).campaCursoFactu         ||';'||
                      interfazRecord(x).flagControl  ;
              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    utl_file.fclose(V_HANDLE);

    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);

    RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_DAT_CAPAC_PROGR: '||ls_sqlerrm);
END INT_PR_DAT_CAPAC_PROGR;


/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Capacitadas Programadas YA NO SE USA
                    Reenvio
Fecha Creacion    : 28/08/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion      : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_CAPAC_PROGR_REENV
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
)
IS
   CURSOR c_interfaz IS
     SELECT
       A.PAIS_COD_PAIS,
       A.EMCO_COD_EMPR_COME,
       A.CCAP_COD_CURS_CAPA,
       A.COD_PLAN_PROG,
       A.CLIE_COD_CLIE,
       A.TIP_CALI_APTA,
       A.IND_CURS_COST,
       A.IND_CURS_FACT,
       A.CAM_FACT_CURS,
       '0' AS FLA_CONT
    FROM EDU_PLANI_PROGR_CURSO  A,
         EDU_MAEST_CLIEN B
    WHERE A.PAIS_COD_PAIS = psCodigoPais
      AND A.EMCO_COD_EMPR_COME = psCodEmpresa
      AND A.CAM_PROC = psCodPeriodo
      AND A.EST_REGI <> '9'

      AND (psCodRegion IS NULL OR B.COD_REGI = psCodRegion)
      AND B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
      AND B.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
      AND B.COD_CLIE = A.CLIE_COD_CLIE
      AND B.EST_REGI <> '9'
      AND EXISTS
          (SELECT X.COD_CLIE
           FROM
             EDU_TMP_INTER_DATAM_CONSU X
           WHERE X.CAM_PROC = psCodPeriodo
             AND X.COD_PAIS = A.PAIS_COD_PAIS
             AND X.COD_EMPR_COME = A.EMCO_COD_EMPR_COME
             AND X.COD_REGI = B.COD_REGI
             AND X.COD_CLIE = B.COD_CLIE)
    ORDER BY A.PAIS_COD_PAIS, A.EMCO_COD_EMPR_COME, A.CCAP_COD_CURS_CAPA, A.CLIE_COD_CLIE;


   TYPE interfaz IS RECORD   (
     codigoPais              VARCHAR2(3),
     codigoEmpresa           VARCHAR2(4),
     codigoCurso             VARCHAR2(3),
     codigoPlanilla          VARCHAR2(10),
     codigoConsultora        VARCHAR2(15),
     tipoCaliApta            VARCHAR2(1),
     indicadorCostoCurso     VARCHAR2(1),
     indicadorCursoFactu     VARCHAR2(1),
     campaCursoFactu         VARCHAR2(6),
     flagControl             VARCHAR2(1)
   );

   TYPE interfazTab  IS TABLE OF interfaz ;
   interfazRecord interfazTab;
   lsCodigoPaisData    EDU_PARAM_PROGR_CAPAC.COD_PAIS_DATA%TYPE;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;
   W_DESC              VARCHAR2(200);
   lsLinea             VARCHAR2(1000);
   lsLineaCabecera     VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

BEGIN
    /* Procedimiento inicial para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
        psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

    /* Generando Archivo de Texto (Detalle) */
    lsCodigoPaisData := EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_PAIS_DATAM(psCodigoPais, psCodEmpresa, '01');
    OPEN c_interfaz;
    LOOP
       FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          lsLinea :=  lsCodigoPaisData             ||';'||
                      interfazRecord(x).codigoEmpresa           ||';'||
                      interfazRecord(x).codigoCurso             ||';'||
                      interfazRecord(x).codigoPlanilla          ||';'||
                      interfazRecord(x).codigoConsultora        ||';'||
                      interfazRecord(x).tipoCaliApta            ||';'||
                      interfazRecord(x).indicadorCostoCurso     ||';'||
                      interfazRecord(x).indicadorCursoFactu     ||';'||
                      interfazRecord(x).campaCursoFactu         ||';'||
                      interfazRecord(x).flagControl  ;
              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    utl_file.fclose(V_HANDLE);

    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);

    RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_DAT_CAPAC_PROGR_REENV: '||ls_sqlerrm);
END INT_PR_DAT_CAPAC_PROGR_REENV;


/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Cursos Dictados
Fecha Creacion    : 27/08/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion      : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_CURSO_DICTA
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
)
IS
  CURSOR c_interfaz IS
    SELECT DISTINCT
       A.CCAP_COD_CURS_CAPA,
       A.CDIC_COD_CURS_DICT
    FROM EDU_HISTO_CURSO_DICTA_DETAL A,
         EDU_HISTO_CURSO_DICTA_CABEC B

    WHERE A.PAIS_COD_PAIS = psCodigoPais
      AND A.EMCO_COD_EMPR_COME = psCodEmpresa
      AND A.EST_REGI <> '9'

      AND (psCodRegion IS NULL OR B.COD_REGI = psCodRegion)
      AND B.CAM_INIC_CURS = psCodPeriodo
      AND B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
      AND B.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
      AND B.CCAP_COD_CURS_CAPA = A.CCAP_COD_CURS_CAPA
      AND B.COD_CURS_DICT = A.CDIC_COD_CURS_DICT
      AND B.EST_REGI <> '9'
      AND NOT EXISTS
          (SELECT X.COD_CLIE
           FROM
             EDU_TMP_INTER_DATAM_CONSU X
           WHERE X.CAM_PROC = psCodPeriodo
             AND X.COD_PAIS = A.PAIS_COD_PAIS
             AND X.COD_EMPR_COME = A.EMCO_COD_EMPR_COME
             AND X.COD_REGI = B.COD_REGI
             AND X.COD_CLIE = A.CLIE_COD_CLIE)
   ORDER BY A.CCAP_COD_CURS_CAPA, TO_NUMBER(A.CDIC_COD_CURS_DICT);

   codigoPais              VARCHAR2(3);
   codigoCursoDictado      VARCHAR2(6);
   codigoEmpresa           VARCHAR2(4);
   codigoCurso             VARCHAR2(3);
   codigoInstructora       VARCHAR2(15);
   annoCampannaInicioCurso VARCHAR2(6);
   fechaIniCurso           VARCHAR2(8);
   numRealDuraSesiones     NUMBER(4);
   codigoRegion            VARCHAR2(2);
   codigoZona              VARCHAR2(4);
   lugarCapacitacion       VARCHAR2(100);
   categoria               VARCHAR2(2);
   indicadorEvalCurso      VARCHAR2(1);
   calPromCursoDictado     VARCHAR2(9);
   estadoCursoDictado      VARCHAR2(1);
   flagControl             VARCHAR2(1);

   TYPE t_codCurso     IS TABLE OF       EDU_HISTO_CURSO_DICTA_DETAL.CCAP_COD_CURS_CAPA%TYPE ;
   TYPE t_codDictado   IS TABLE OF       EDU_HISTO_CURSO_DICTA_DETAL.Cdic_Cod_Curs_Dict%TYPE ;

   v_codCurso    t_codCurso;
   v_codDictado  t_codDictado;
   lsCodigoPaisData    EDU_PARAM_PROGR_CAPAC.COD_PAIS_DATA%TYPE;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;
   W_DESC              VARCHAR2(200);
   lsLinea             VARCHAR2(1000);
   lsLineaCabecera     VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

BEGIN
    /* Procedimiento inicial para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
        psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

    /* Generando Archivo de Texto (Detalle) */
    lsCodigoPaisData := EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_PAIS_DATAM(psCodigoPais, psCodEmpresa, '01');
    OPEN c_interfaz;
    LOOP
       FETCH c_interfaz BULK COLLECT INTO
             v_codCurso, v_codDictado  LIMIT W_FILAS;
       IF v_codCurso.COUNT > 0 THEN
          FOR x IN v_codCurso.FIRST .. v_codCurso.LAST LOOP
              SELECT
                 A.PAIS_COD_PAIS,
                 A.COD_CURS_DICT,
                 A.EMCO_COD_EMPR_COME,
                 A.CCAP_COD_CURS_CAPA,
                 A.INST_COD_INST,
                 A.CAM_INIC_CURS,
                 TO_CHAR(A.FEC_INIC_CURS,'YYYYMMDD'),
                 A.NUM_REAL_DURA_SESI,
                 A.COD_REGI,
                 A.COD_ZONA,
                 (SELECT NVL(X.DES_LOCA,'')
				  FROM EDU_LOCAL X
				  WHERE X.PAIS_COD_PAIS=A.PAIS_COD_PAIS
				   AND X.EMCO_COD_EMPR_COME=A.EMCO_COD_EMPR_COME
				   AND X.COD_LOCA=A.LOCA_COD_LOCA) AS LUG_CAPA,
                 A.CAT_LUGA_CAPA,
                 A.IND_EVAL_CURS,
                 A.CAL_PROM_CURS_DICT,
                 A.EST_CURS_DICT,
                 '0' AS FLA_CONT
              INTO
                  codigoPais              ,
                  codigoCursoDictado      ,
                  codigoEmpresa           ,
                  codigoCurso             ,
                  codigoInstructora       ,
                  annoCampannaInicioCurso ,
                  fechaIniCurso           ,
                  numRealDuraSesiones     ,
                  codigoRegion            ,
                  codigoZona              ,
                  lugarCapacitacion       ,
                  categoria               ,
                  indicadorEvalCurso      ,
                  calPromCursoDictado     ,
                  estadoCursoDictado      ,
                  flagControl
              FROM EDU_HISTO_CURSO_DICTA_CABEC A

              WHERE A.PAIS_COD_PAIS = psCodigoPais
              AND A.EMCO_COD_EMPR_COME = psCodEmpresa
                AND A.CAM_INIC_CURS = psCodPeriodo
                AND (psCodRegion IS NULL OR A.COD_REGI = psCodRegion)
                AND A.CCAP_COD_CURS_CAPA = v_codCurso(x)
                AND A.COD_CURS_DICT = v_codDictado(x)
                AND A.EST_REGI <> '9';

              lsLinea :=  lsCodigoPaisData              ||';'||
                      codigoCursoDictado        ||';'||
                      codigoEmpresa             ||';'||
                      codigoCurso               ||';'||
                      codigoInstructora         ||';'||
                      annoCampannaInicioCurso   ||';'||
                      fechaIniCurso             ||';'||
                      trim(to_char(numRealDuraSesiones,'9999'))     ||';'||
                      codigoRegion              ||';'||
                      codigoZona                ||';'||
                      lugarCapacitacion         ||';'||
                      categoria                 ||';'||
                      indicadorEvalCurso        ||';'||
                      calPromCursoDictado       ||';'||
                      estadoCursoDictado        ||';'||
                      flagControl  ;
              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    utl_file.fclose(V_HANDLE);

    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);

    RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_DAT_CURSO_DICTA: '||ls_sqlerrm);
END INT_PR_DAT_CURSO_DICTA;


/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Cursos Dictados (Reenvio) YA NO SE USA
Fecha Creacion    : 27/08/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion      : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_CURSO_DICTA_REENV
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
)
IS
  CURSOR c_interfaz IS
     SELECT DISTINCT
       A.CCAP_COD_CURS_CAPA,
       A.CDIC_COD_CURS_DICT
    FROM EDU_HISTO_CURSO_DICTA_DETAL A,
         EDU_HISTO_CURSO_DICTA_CABEC B

    WHERE A.PAIS_COD_PAIS = psCodigoPais
      AND A.EMCO_COD_EMPR_COME = psCodEmpresa
      AND A.EST_REGI <> '9'

      AND (psCodRegion IS NULL OR B.COD_REGI = psCodRegion)
      AND B.CAM_INIC_CURS = psCodPeriodo
      AND B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
      AND B.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
      AND B.CCAP_COD_CURS_CAPA = A.CCAP_COD_CURS_CAPA
      AND B.COD_CURS_DICT = A.CDIC_COD_CURS_DICT
      AND B.EST_REGI <> '9'
      AND EXISTS
          (SELECT X.COD_CLIE
           FROM
             EDU_TMP_INTER_DATAM_CONSU X
           WHERE X.CAM_PROC = psCodPeriodo
             AND X.COD_PAIS = A.PAIS_COD_PAIS
             AND X.COD_EMPR_COME = A.EMCO_COD_EMPR_COME
             AND X.COD_REGI = B.COD_REGI
             AND X.COD_CLIE = A.CLIE_COD_CLIE)
   ORDER BY A.CCAP_COD_CURS_CAPA, TO_NUMBER(A.CDIC_COD_CURS_DICT);

   codigoPais              VARCHAR2(3);
   codigoCursoDictado      VARCHAR2(6);
   codigoEmpresa           VARCHAR2(4);
   codigoCurso             VARCHAR2(3);
   codigoInstructora       VARCHAR2(15);
   annoCampannaInicioCurso VARCHAR2(6);
   fechaIniCurso           VARCHAR2(8);
   numRealDuraSesiones     NUMBER(4);
   codigoRegion            VARCHAR2(2);
   codigoZona              VARCHAR2(4);
   lugarCapacitacion       VARCHAR2(100);
   categoria               VARCHAR2(2);
   indicadorEvalCurso      VARCHAR2(1);
   calPromCursoDictado     VARCHAR2(9);
   estadoCursoDictado      VARCHAR2(1);
   flagControl             VARCHAR2(1);

   TYPE t_codCurso     IS TABLE OF       EDU_HISTO_CURSO_DICTA_DETAL.CCAP_COD_CURS_CAPA%TYPE ;
   TYPE t_codDictado   IS TABLE OF       EDU_HISTO_CURSO_DICTA_DETAL.Cdic_Cod_Curs_Dict%TYPE ;

   v_codCurso    t_codCurso;
   v_codDictado  t_codDictado;
   lsCodigoPaisData    EDU_PARAM_PROGR_CAPAC.COD_PAIS_DATA%TYPE;


   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;
   W_DESC              VARCHAR2(200);
   lsLinea             VARCHAR2(1000);
   lsLineaCabecera     VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

BEGIN
    /* Procedimiento inicial para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
        psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

    /* Generando Archivo de Texto (Detalle) */
    lsCodigoPaisData := EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_PAIS_DATAM(psCodigoPais, psCodEmpresa, '01');
    OPEN c_interfaz;
    LOOP
       FETCH c_interfaz BULK COLLECT INTO
             v_codCurso, v_codDictado  LIMIT W_FILAS;
       IF v_codCurso.COUNT > 0 THEN
          FOR x IN v_codCurso.FIRST .. v_codCurso.LAST LOOP
              SELECT
                 A.PAIS_COD_PAIS,
                 A.COD_CURS_DICT,
                 A.EMCO_COD_EMPR_COME,
                 A.CCAP_COD_CURS_CAPA,
                 A.INST_COD_INST,
                 A.CAM_INIC_CURS,
                 TO_CHAR(A.FEC_INIC_CURS,'YYYYMMDD'),
                 A.NUM_REAL_DURA_SESI,
                 A.COD_REGI,
                 A.COD_ZONA,
                 A.LUG_CAPA,
                 A.CAT_LUGA_CAPA,
                 A.IND_EVAL_CURS,
                 A.CAL_PROM_CURS_DICT,
                 A.EST_CURS_DICT,
                 '0' AS FLA_CONT
              INTO
                  codigoPais              ,
                  codigoCursoDictado      ,
                  codigoEmpresa           ,
                  codigoCurso             ,
                  codigoInstructora       ,
                  annoCampannaInicioCurso ,
                  fechaIniCurso           ,
                  numRealDuraSesiones     ,
                  codigoRegion            ,
                  codigoZona              ,
                  lugarCapacitacion       ,
                  categoria               ,
                  indicadorEvalCurso      ,
                  calPromCursoDictado     ,
                  estadoCursoDictado      ,
                  flagControl
              FROM EDU_HISTO_CURSO_DICTA_CABEC A

              WHERE A.PAIS_COD_PAIS = psCodigoPais
              AND A.EMCO_COD_EMPR_COME = psCodEmpresa
                AND A.CAM_INIC_CURS = psCodPeriodo
                AND (psCodRegion IS NULL OR A.COD_REGI = psCodRegion)
                AND A.CCAP_COD_CURS_CAPA = v_codCurso(x)
                AND A.COD_CURS_DICT = v_codDictado(x)
                AND A.EST_REGI <> '9';

              lsLinea :=  lsCodigoPaisData              ||';'||
                      codigoCursoDictado        ||';'||
                      codigoEmpresa             ||';'||
                      codigoCurso               ||';'||
                      codigoInstructora         ||';'||
                      annoCampannaInicioCurso   ||';'||
                      fechaIniCurso             ||';'||
                      trim(to_char(numRealDuraSesiones,'9999'))     ||';'||
                      codigoRegion              ||';'||
                      codigoZona                ||';'||
                      lugarCapacitacion         ||';'||
                      categoria                 ||';'||
                      indicadorEvalCurso        ||';'||
                      calPromCursoDictado       ||';'||
                      estadoCursoDictado        ||';'||
                      flagControl  ;
              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    utl_file.fclose(V_HANDLE);

    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);

    RETURN;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_DAT_CURSO_DICTA_REENV: '||ls_sqlerrm);
END INT_PR_DAT_CURSO_DICTA_REENV;


/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Cursos Dictados Historico
Fecha Creacion    : 27/08/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion      : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_CURSO_DICTA_HISTO
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
)
IS
   CURSOR c_interfaz IS
     SELECT
       A.PAIS_COD_PAIS,
       A.EMCO_COD_EMPR_COME,
       A.CCAP_COD_CURS_CAPA,
       A.CDIC_COD_CURS_DICT,
       A.COD_PLAN_PROG,
       A.CLIE_COD_CLIE,
       A.IND_ASIS,
       A.CAL_CONS,
       A.CAL_INST,
       '0' AS FLA_CONT
    FROM EDU_HISTO_CURSO_DICTA_DETAL A,
         EDU_HISTO_CURSO_DICTA_CABEC B

    WHERE A.PAIS_COD_PAIS = psCodigoPais
    AND A.EMCO_COD_EMPR_COME = psCodEmpresa
      AND A.EST_REGI <> '9'

      AND (psCodRegion IS NULL OR B.COD_REGI = psCodRegion)
      AND B.CAM_INIC_CURS = psCodPeriodo
      AND B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
      AND B.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
      AND B.CCAP_COD_CURS_CAPA = A.CCAP_COD_CURS_CAPA
      AND B.COD_CURS_DICT = A.CDIC_COD_CURS_DICT
      AND B.EST_REGI <> '9'
      AND NOT EXISTS
          (SELECT X.COD_CLIE
           FROM
             EDU_TMP_INTER_DATAM_CONSU X
           WHERE X.CAM_PROC = psCodPeriodo
             AND X.COD_PAIS = A.PAIS_COD_PAIS
             AND X.COD_EMPR_COME = A.EMCO_COD_EMPR_COME
             AND X.COD_REGI = B.COD_REGI
             AND X.COD_CLIE = A.CLIE_COD_CLIE)

    ORDER BY A.PAIS_COD_PAIS, A.EMCO_COD_EMPR_COME, A.CCAP_COD_CURS_CAPA,
             TO_NUMBER(A.CDIC_COD_CURS_DICT), TO_NUMBER(A.COD_PLAN_PROG);

   TYPE interfaz IS RECORD   (
      codigoPais       VARCHAR2(3),
      codigoEmpresa           VARCHAR2(4),
      codigoCurso             VARCHAR2(3),
      codigoCursoDictado      VARCHAR2(6),
      codigoPlanilla          VARCHAR2(10),
      codigoConsultora        VARCHAR2(15),
      indAsistencia           VARCHAR2(1),
      calConsultora           VARCHAR2(9),
      calInstructora          VARCHAR2(9),
      flagControl             VARCHAR2(1)
   );

   TYPE interfazTab  IS TABLE OF interfaz ;
   interfazRecord interfazTab;
   lsCodigoPaisData    EDU_PARAM_PROGR_CAPAC.COD_PAIS_DATA%TYPE;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;
   W_DESC              VARCHAR2(200);
   lsLinea             VARCHAR2(1000);
   lsLineaCabecera     VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

   TYPE t_codCurso     IS TABLE OF       EDU_HISTO_APTAS.CCAP_COD_CURS_CAPA%TYPE ;
   TYPE t_codCons      IS TABLE OF       EDU_HISTO_APTAS.CLIE_COD_CLIE%TYPE ;
   TYPE t_codDictado   IS TABLE OF       EDU_HISTO_APTAS.CLIE_COD_CLIE%TYPE ;

   v_codCurso   t_codCurso;
   v_codCons    t_codCons;
   v_codDictado t_codDictado;


BEGIN
    /* Procedimiento inicial para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
        psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

    /* Obteniendo lista */
    lsCodigoPaisData := EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_PAIS_DATAM(psCodigoPais, psCodEmpresa, '01');
    OPEN c_interfaz;
    LOOP
       FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          lsLinea :=  lsCodigoPaisData            ||';'||
                      interfazRecord(x).codigoEmpresa         ||';'||
                      interfazRecord(x).codigoCurso           ||';'||
                      interfazRecord(x).codigoCursoDictado    ||';'||
                      interfazRecord(x).codigoPlanilla        ||';'||
                      interfazRecord(x).codigoConsultora      ||';'||
                      interfazRecord(x).indAsistencia         ||';'||
                      interfazRecord(x).calConsultora         ||';'||
                      interfazRecord(x).calInstructora        ||';'||
                      interfazRecord(x).flagControl           ;

              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    utl_file.fclose(V_HANDLE);

    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);

    RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_DAT_CURSO_DICTA_HISTO: '||ls_sqlerrm);
END INT_PR_DAT_CURSO_DICTA_HISTO;


/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Cursos Dictados Historico
                    (REENVIO) YA NO SE USA
Fecha Creacion    : 27/08/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion      : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_CURSO_DICTA_HISTO_R
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
)
IS
   CURSOR c_interfaz IS
     SELECT
       A.PAIS_COD_PAIS,
       A.EMCO_COD_EMPR_COME,
       A.CCAP_COD_CURS_CAPA,
       A.CDIC_COD_CURS_DICT,
       A.COD_PLAN_PROG,
       A.CLIE_COD_CLIE,
       A.IND_ASIS,
       A.CAL_CONS,
       A.CAL_INST,
       '0' AS FLA_CONT
    FROM EDU_HISTO_CURSO_DICTA_DETAL A,
         EDU_HISTO_CURSO_DICTA_CABEC B

    WHERE A.PAIS_COD_PAIS = psCodigoPais
    AND A.EMCO_COD_EMPR_COME = psCodEmpresa
      AND A.EST_REGI <> '9'

      AND (psCodRegion IS NULL OR B.COD_REGI = psCodRegion)
      AND B.CAM_INIC_CURS = psCodPeriodo
      AND B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
      AND B.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
      AND B.CCAP_COD_CURS_CAPA = A.CCAP_COD_CURS_CAPA
      AND B.COD_CURS_DICT = A.CDIC_COD_CURS_DICT
      AND B.EST_REGI <> '9'
      AND EXISTS
          (SELECT X.COD_CLIE
           FROM
             EDU_TMP_INTER_DATAM_CONSU X
           WHERE X.CAM_PROC = psCodPeriodo
             AND X.COD_PAIS = A.PAIS_COD_PAIS
             AND X.COD_EMPR_COME = A.EMCO_COD_EMPR_COME
             AND X.COD_REGI = B.COD_REGI
             AND X.COD_CLIE = A.CLIE_COD_CLIE)

    ORDER BY A.PAIS_COD_PAIS, A.EMCO_COD_EMPR_COME, A.CCAP_COD_CURS_CAPA,
             TO_NUMBER(A.CDIC_COD_CURS_DICT), TO_NUMBER(A.COD_PLAN_PROG);

   TYPE interfaz IS RECORD   (
      codigoPais       VARCHAR2(3),
      codigoEmpresa           VARCHAR2(4),
      codigoCurso             VARCHAR2(3),
      codigoCursoDictado      VARCHAR2(6),
      codigoPlanilla          VARCHAR2(10),
      codigoConsultora        VARCHAR2(15),
      indAsistencia           VARCHAR2(1),
      calConsultora           VARCHAR2(9),
      calInstructora          VARCHAR2(9),
      flagControl             VARCHAR2(1)
   );

   TYPE interfazTab  IS TABLE OF interfaz ;
   interfazRecord interfazTab;
   lsCodigoPaisData    EDU_PARAM_PROGR_CAPAC.COD_PAIS_DATA%TYPE;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;
   W_DESC              VARCHAR2(200);
   lsLinea             VARCHAR2(1000);
   lsLineaCabecera     VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

BEGIN
    /* Procedimiento inicial para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
        psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

    /* Generando Archivo de Texto (Detalle) */
    lsCodigoPaisData := EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_PAIS_DATAM(psCodigoPais, psCodEmpresa, '01');
    OPEN c_interfaz;
    LOOP
       FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          lsLinea :=  lsCodigoPaisData       ||';'||
                      interfazRecord(x).codigoEmpresa         ||';'||
                      interfazRecord(x).codigoCurso           ||';'||
                      interfazRecord(x).codigoCursoDictado    ||';'||
                      interfazRecord(x).codigoPlanilla        ||';'||
                      interfazRecord(x).codigoConsultora      ||';'||
                      interfazRecord(x).indAsistencia         ||';'||
                      interfazRecord(x).calConsultora         ||';'||
                      interfazRecord(x).calInstructora        ||';'||
                      interfazRecord(x).flagControl           ;

              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    utl_file.fclose(V_HANDLE);

    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);

    RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_DAT_CURSO_DICTA_HISTO_R: '||ls_sqlerrm);
END INT_PR_DAT_CURSO_DICTA_HISTO_R;


/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Capacitacion Cliente
Fecha Creacion    : 27/08/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion      : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_CAPAC_CLIEN
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
)
IS
   CURSOR c_interfaz IS
     SELECT DISTINCT
       A.PAIS_COD_PAIS,
       A.EMCO_COD_EMPR_COME,
       A.CLIE_COD_CLIE,
       A.CCAP_COD_CURS_CAPA,
       B.CAM_INGR_COME,
       A.CAM_CAPA,
     '0' AS FLA_CONT
    FROM
         EDU_HISTO_CAPAC_DETAL A,
         EDU_MAEST_CLIEN B

    WHERE A.PAIS_COD_PAIS = psCodigoPais
    AND A.EMCO_COD_EMPR_COME = psCodEmpresa
      AND A.CAM_CAPA = psCodPeriodo
      AND A.EST_REGI <> '9'

      AND (psCodRegion IS NULL OR B.COD_REGI = psCodRegion)
      AND B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
      AND B.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
      AND B.COD_CLIE = A.CLIE_COD_CLIE
      AND B.EST_REGI <> '9'

      AND NOT EXISTS
          (SELECT X.COD_CLIE
           FROM
             EDU_TMP_INTER_DATAM_CONSU X
           WHERE X.CAM_PROC = psCodPeriodo
             AND X.COD_PAIS = psCodigoPais
             AND X.COD_EMPR_COME = psCodEmpresa
             AND (psCodRegion IS NULL OR X.COD_REGI = psCodRegion)
             AND X.COD_CLIE = B.COD_CLIE)

    ORDER BY A.PAIS_COD_PAIS, A.EMCO_COD_EMPR_COME, A.CLIE_COD_CLIE;

   TYPE interfaz IS RECORD   (
      codigoPais       VARCHAR2(3),
      codigoEmpresa           VARCHAR2(4),
      codigoConsultora        VARCHAR2(15),
      nivelAlcanzado          VARCHAR2(3),
      annoCampannaIngreso     VARCHAR2(6),
      annoCampannaUltCapa     VARCHAR2(6),
      flagControl             VARCHAR2(1)
   );

   TYPE interfazTab  IS TABLE OF interfaz ;
   interfazRecord interfazTab;
   lsCodigoPaisData    EDU_PARAM_PROGR_CAPAC.COD_PAIS_DATA%TYPE;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;
   W_DESC              VARCHAR2(200);
   lsLinea             VARCHAR2(1000);
   lsLineaCabecera     VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

BEGIN
    /* Procedimiento inicial para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
        psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

    /* Generando Archivo de Texto (Detalle) */
    lsCodigoPaisData := EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_PAIS_DATAM(psCodigoPais, psCodEmpresa, '01');
    OPEN c_interfaz;
    LOOP
       FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          lsLinea :=  lsCodigoPaisData             ||';'||
                      interfazRecord(x).codigoEmpresa         ||';'||
                      interfazRecord(x).codigoConsultora        ||';'||
                      interfazRecord(x).nivelAlcanzado          ||';'||
                      interfazRecord(x).annoCampannaIngreso     ||';'||
                      interfazRecord(x).annoCampannaUltCapa     ||';'||
                      interfazRecord(x).flagControl           ;

              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    utl_file.fclose(V_HANDLE);

    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);

    RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_DAT_CAPAC_CLIEN: '||ls_sqlerrm);
END INT_PR_DAT_CAPAC_CLIEN;

/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Capacitacion Cliente
                    (REENVIO) YA NO SE USA
Fecha Creacion    : 27/08/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion      : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_CAPAC_CLIEN_REENV
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
)
IS
   CURSOR c_interfaz IS
     SELECT DISTINCT
       A.PAIS_COD_PAIS,
       A.EMCO_COD_EMPR_COME,
       A.CLIE_COD_CLIE,
       A.CCAP_COD_CURS_CAPA,
       B.CAM_INGR_COME,
       A.CAM_CAPA,
     '0' AS FLA_CONT
    FROM
         EDU_HISTO_CAPAC_DETAL A,
         EDU_MAEST_CLIEN B

    WHERE A.PAIS_COD_PAIS = psCodigoPais
    AND A.EMCO_COD_EMPR_COME = psCodEmpresa
      AND A.CAM_CAPA = psCodPeriodo
      AND A.EST_REGI <> '9'

      AND (psCodRegion IS NULL OR B.COD_REGI = psCodRegion)
      AND B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
      AND B.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
      AND B.COD_CLIE = A.CLIE_COD_CLIE
      AND B.EST_REGI <> '9'

      AND EXISTS
          (SELECT X.COD_CLIE
           FROM
             EDU_TMP_INTER_DATAM_CONSU X
           WHERE X.CAM_PROC = psCodPeriodo
             AND X.COD_PAIS = psCodigoPais
             AND X.COD_EMPR_COME = psCodEmpresa
             AND (psCodRegion IS NULL OR X.COD_REGI = psCodRegion)
             AND X.COD_CLIE = B.COD_CLIE)

    ORDER BY A.PAIS_COD_PAIS, A.EMCO_COD_EMPR_COME, A.CLIE_COD_CLIE;

   TYPE interfaz IS RECORD   (
      codigoPais       VARCHAR2(3),
      codigoEmpresa           VARCHAR2(4),
      codigoConsultora        VARCHAR2(15),
      nivelAlcanzado          VARCHAR2(3),
      annoCampannaIngreso     VARCHAR2(6),
      annoCampannaUltCapa     VARCHAR2(6),
      flagControl             VARCHAR2(1)
   );

   TYPE interfazTab  IS TABLE OF interfaz ;
   interfazRecord interfazTab;
   lsCodigoPaisData    EDU_PARAM_PROGR_CAPAC.COD_PAIS_DATA%TYPE;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;
   W_DESC              VARCHAR2(200);
   lsLinea             VARCHAR2(1000);
   lsLineaCabecera     VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

BEGIN
    /* Procedimiento inicial para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
        psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

    /* Generando Archivo de Texto (Detalle) */
    lsCodigoPaisData := EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_PAIS_DATAM(psCodigoPais, psCodEmpresa, '01');
    OPEN c_interfaz;
    LOOP
       FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          lsLinea :=  lsCodigoPaisData             ||';'||
                      interfazRecord(x).codigoEmpresa         ||';'||
                      interfazRecord(x).codigoConsultora        ||';'||
                      interfazRecord(x).nivelAlcanzado          ||';'||
                      interfazRecord(x).annoCampannaIngreso     ||';'||
                      interfazRecord(x).annoCampannaUltCapa     ||';'||
                      interfazRecord(x).flagControl           ;

              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    utl_file.fclose(V_HANDLE);

    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);

    RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_DAT_CAPAC_CLIEN_REENV: '||ls_sqlerrm);
END INT_PR_DAT_CAPAC_CLIEN_REENV;


/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Capacitacion Cliente
Fecha Creacion    : 27/08/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion      : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_CAPAC_CURSO_CLIEN
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
)
IS
   CURSOR c_interfaz IS
     SELECT
       A.PAIS_COD_PAIS,
       A.EMCO_COD_EMPR_COME,
       A.CCAP_COD_CURS_CAPA,
       A.CLIE_COD_CLIE,
       A.COD_CURS_DICT,
       A.COD_PLAN_PROG,
       A.INST_COD_INST,
       C.DES_TIPO_ASIS_CURS,
       D.DES_TIPO_ASTT_CURS,
       A.NUM_INVI,
       A.IND_PAGO_CURS,
       A.CAM_PRIM_CALI_APTA,
       A.CAM_ULTI_CALI_APTA,
       A.CAM_CAPA,
       A.CAM_REGI_ASIS,
       A.IND_EVAL_CURS,
       A.CAL_EVAL_CURS,
       A.IND_EVAL_CURS,
       A.CAL_EVAL_INST,
       '0' AS FLA_CONT
    FROM EDU_HISTO_CAPAC_DETAL A,
          EDU_HISTO_CURSO_DICTA_CABEC B,
         EDU_TIPO_ASIST_CURSO C,
         EDU_TIPO_ASITT_CURSO D

    WHERE A.PAIS_COD_PAIS = psCodigoPais
    AND A.EMCO_COD_EMPR_COME = psCodEmpresa
      AND A.CAM_CAPA = psCodPeriodo
      AND A.EST_REGI <> '9'

      AND (psCodRegion IS NULL OR B.COD_REGI = psCodRegion)
      AND B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
      AND B.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
      AND B.CCAP_COD_CURS_CAPA = A.CCAP_COD_CURS_CAPA
      AND B.COD_CURS_DICT = A.COD_CURS_DICT
      AND B.EST_REGI <> '9'

      AND C.COD_TIPO_ASIS_CURS = A.TASI_COD_TIPO_ASIS_CURS
      AND D.COD_TIPO_ASTT_CURS = A.ASTT_COD_TIPO_ASTT_CURS

      AND NOT EXISTS
          (SELECT X.COD_CLIE
           FROM
             EDU_TMP_INTER_DATAM_CONSU X
           WHERE X.CAM_PROC = psCodPeriodo
             AND X.COD_PAIS = A.PAIS_COD_PAIS
             AND X.COD_EMPR_COME = A.EMCO_COD_EMPR_COME
             AND X.COD_REGI = B.COD_REGI
             AND X.COD_CLIE = A.CLIE_COD_CLIE)

    ORDER BY A.PAIS_COD_PAIS, A.EMCO_COD_EMPR_COME, A.CCAP_COD_CURS_CAPA, A.CLIE_COD_CLIE;

   TYPE interfaz IS RECORD   (
      codigoPais             VARCHAR2(3),
      codigoEmpresa                 VARCHAR2(4),
      codCurso                      VARCHAR2(3),
      codigoConsultora              VARCHAR2(15),
      codCursoDictado               VARCHAR2(6),
      codPlanPrograma               VARCHAR2(10),
      codigoInstructora             VARCHAR2(15),
      tipoAsistencia                VARCHAR2(60),
      tipoAsistente                 VARCHAR2(60),
      numInvitaciones               NUMBER(4),
      indicadorPagoCurso            VARCHAR2(1),
      annoCampannaPrimCalApta       VARCHAR2(6),
      annoCampannaUltCalApta        VARCHAR2(6),
      annoCampannaCapa              VARCHAR2(6),
      annoCampannaRegAsist          VARCHAR2(6),
      indicadorEvalCurso            VARCHAR2(1),
      calificacionEvalCurso         NUMBER(4),
      indicadorEvalInstruc          VARCHAR2(1),
      calificacionEvalInstruc       NUMBER(6,2),
      flagControl                   VARCHAR2(1)
   );

   TYPE interfazTab  IS TABLE OF interfaz ;
   interfazRecord interfazTab;
   lsCodigoPaisData    EDU_PARAM_PROGR_CAPAC.COD_PAIS_DATA%TYPE;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;
   W_DESC              VARCHAR2(200);
   lsLinea             VARCHAR2(1000);
   lsLineaCabecera     VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

BEGIN
    /* Procedimiento inicial para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
        psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

    /* Generando Archivo de Texto (Detalle) */
    lsCodigoPaisData := EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_PAIS_DATAM(psCodigoPais, psCodEmpresa, '01');
    OPEN c_interfaz;
    LOOP
       FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          lsLinea :=  lsCodigoPaisData             ||';'||
                      interfazRecord(x).codigoEmpresa                 ||';'||
                      interfazRecord(x).codCurso                      ||';'||
                      interfazRecord(x).codigoConsultora              ||';'||
                      interfazRecord(x).codCursoDictado               ||';'||
                      interfazRecord(x).codPlanPrograma               ||';'||
                      interfazRecord(x).codigoInstructora             ||';'||
                      interfazRecord(x).tipoAsistencia                ||';'||
                      interfazRecord(x).tipoAsistente                 ||';'||
                      interfazRecord(x).numInvitaciones               ||';'||
                      interfazRecord(x).indicadorPagoCurso            ||';'||
                      interfazRecord(x).annoCampannaPrimCalApta       ||';'||
                      interfazRecord(x).annoCampannaUltCalApta        ||';'||
                      interfazRecord(x).annoCampannaCapa              ||';'||
                      interfazRecord(x).annoCampannaRegAsist          ||';'||
                      interfazRecord(x).indicadorEvalCurso            ||';'||
                      interfazRecord(x).calificacionEvalCurso         ||';'||
                      interfazRecord(x).indicadorEvalInstruc          ||';'||
                      interfazRecord(x).calificacionEvalInstruc       ||';'||
                      interfazRecord(x).flagControl           ;

              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    utl_file.fclose(V_HANDLE);

   /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);

    RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_DAT_CAPAC_CURSO_CLIEN: '||ls_sqlerrm);
END INT_PR_DAT_CAPAC_CURSO_CLIEN;

/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Capacitacion Cliente
                    (REENVIO) YA NO SE USA
Fecha Creacion    : 27/08/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion      : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_CAPAC_CURSO_CLIEN_R
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
)
IS
   CURSOR c_interfaz IS
     SELECT
       A.PAIS_COD_PAIS,
       A.EMCO_COD_EMPR_COME,
       A.CCAP_COD_CURS_CAPA,
       A.CLIE_COD_CLIE,
       A.COD_CURS_DICT,
       A.COD_PLAN_PROG,
       A.INST_COD_INST,
       C.DES_TIPO_ASIS_CURS,
       D.DES_TIPO_ASTT_CURS,
       A.NUM_INVI,
       A.IND_PAGO_CURS,
       A.CAM_PRIM_CALI_APTA,
       A.CAM_ULTI_CALI_APTA,
       A.CAM_CAPA,
       A.CAM_REGI_ASIS,
       A.IND_EVAL_CURS,
       A.CAL_EVAL_CURS,
       A.IND_EVAL_CURS,
       A.CAL_EVAL_INST,
      '0' AS FLA_CONT
    FROM EDU_HISTO_CAPAC_DETAL A,
          EDU_HISTO_CURSO_DICTA_CABEC B,
         EDU_TIPO_ASIST_CURSO C,
         EDU_TIPO_ASITT_CURSO D

    WHERE A.PAIS_COD_PAIS = psCodigoPais
    AND A.EMCO_COD_EMPR_COME = psCodEmpresa
      AND A.CAM_CAPA = psCodPeriodo
      AND A.EST_REGI <> '9'

      AND (psCodRegion IS NULL OR B.COD_REGI = psCodRegion)
      AND B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
      AND B.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
      AND B.CCAP_COD_CURS_CAPA = A.CCAP_COD_CURS_CAPA
      AND B.COD_CURS_DICT = A.COD_CURS_DICT
      AND B.EST_REGI <> '9'

      AND C.COD_TIPO_ASIS_CURS = A.TASI_COD_TIPO_ASIS_CURS
      AND D.COD_TIPO_ASTT_CURS = A.ASTT_COD_TIPO_ASTT_CURS

      AND EXISTS
          (SELECT X.COD_CLIE
           FROM
             EDU_TMP_INTER_DATAM_CONSU X
           WHERE X.CAM_PROC = psCodPeriodo
             AND X.COD_PAIS = A.PAIS_COD_PAIS
             AND X.COD_EMPR_COME = A.EMCO_COD_EMPR_COME
             AND X.COD_REGI = B.COD_REGI
             AND X.COD_CLIE = A.CLIE_COD_CLIE)

    ORDER BY A.PAIS_COD_PAIS, A.EMCO_COD_EMPR_COME, A.CCAP_COD_CURS_CAPA, A.CLIE_COD_CLIE;

   TYPE interfaz IS RECORD   (
      codigoPais             VARCHAR2(3),
      codigoEmpresa                 VARCHAR2(4),
      codCurso                      VARCHAR2(3),
      codigoConsultora              VARCHAR2(15),
      codCursoDictado               VARCHAR2(6),
      codPlanPrograma               VARCHAR2(10),
      codigoInstructora             VARCHAR2(15),
      tipoAsistencia                VARCHAR2(60),
      tipoAsistente                 VARCHAR2(60),
      numInvitaciones               NUMBER(4),
      indicadorPagoCurso            VARCHAR2(1),
      annoCampannaPrimCalApta       VARCHAR2(6),
      annoCampannaUltCalApta        VARCHAR2(6),
      annoCampannaCapa              VARCHAR2(6),
      annoCampannaRegAsist          VARCHAR2(6),
      indicadorEvalCurso            VARCHAR2(1),
      calificacionEvalCurso         NUMBER(4),
      indicadorEvalInstruc          VARCHAR2(1),
      calificacionEvalInstruc       NUMBER(6,2),
      flagControl                   VARCHAR2(1)
   );

   TYPE interfazTab  IS TABLE OF interfaz ;
   interfazRecord interfazTab;
   lsCodigoPaisData    EDU_PARAM_PROGR_CAPAC.COD_PAIS_DATA%TYPE;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;
   W_DESC              VARCHAR2(200);
   lsLinea             VARCHAR2(1000);
   lsLineaCabecera     VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

BEGIN
    /* Procedimiento inicial para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
        psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

    /* Generando Archivo de Texto (Detalle) */
    lsCodigoPaisData := EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_PAIS_DATAM(psCodigoPais, psCodEmpresa, '01');
    OPEN c_interfaz;
    LOOP
       FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          lsLinea :=  lsCodigoPaisData            ||';'||
                      interfazRecord(x).codigoEmpresa                 ||';'||
                      interfazRecord(x).codCurso                      ||';'||
                      interfazRecord(x).codigoConsultora              ||';'||
                      interfazRecord(x).codCursoDictado               ||';'||
                      interfazRecord(x).codPlanPrograma               ||';'||
                      interfazRecord(x).codigoInstructora             ||';'||
                      interfazRecord(x).tipoAsistencia                ||';'||
                      interfazRecord(x).tipoAsistente                 ||';'||
                      interfazRecord(x).numInvitaciones               ||';'||
                      interfazRecord(x).indicadorPagoCurso            ||';'||
                      interfazRecord(x).annoCampannaPrimCalApta       ||';'||
                      interfazRecord(x).annoCampannaUltCalApta        ||';'||
                      interfazRecord(x).annoCampannaCapa              ||';'||
                      interfazRecord(x).annoCampannaRegAsist          ||';'||
                      interfazRecord(x).indicadorEvalCurso            ||';'||
                      interfazRecord(x).calificacionEvalCurso         ||';'||
                      interfazRecord(x).indicadorEvalInstruc          ||';'||
                      interfazRecord(x).calificacionEvalInstruc       ||';'||
                      interfazRecord(x).flagControl           ;

              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    utl_file.fclose(V_HANDLE);

    -- Comprimimos el archivo
    GEN_PKG_INTER_ARCHI.GEN_PR_COMPR_ZIP(lsDirTempo, psNombreArchivo);

    -- Eliminamos el archivo temporal
    UTL_FILE.FREMOVE('SICC_DIR', lsNombreArchivo);

    RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_DAT_CAPAC_CURSO_CLIEN_R: '||ls_sqlerrm);
END INT_PR_DAT_CAPAC_CURSO_CLIEN_R;


/***************************************************************************
Descripcion       : Actualiza Interfase Datamart
Fecha Creacion    : 24/09/2007
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion      : Codigo de Region
            psUsuario        : Usuario
***************************************************************************/
PROCEDURE EDU_PR_ACTUA_INTER_DATAM
  (psCodigoPais               VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2,
   psUsuario                  VARCHAR2
)
IS
   /* Consultoras Aptas */
   CURSOR c_loteApta IS
     SELECT DISTINCT
       A.CLIE_COD_CLIE,
       A.ULT_CAMP_PROG_DICT,
	   B.COD_REGI

    FROM EDU_HISTO_CALIF_APTAS A,
         EDU_MAEST_CLIEN B
    WHERE A.PAIS_COD_PAIS = psCodigoPais
    AND A.EMCO_COD_EMPR_COME = psCodEmpresa
      AND A.EST_CAPA = INDICADOR_PENDIENTE
      AND A.CAM_PROC = psCodPeriodo
      AND A.EST_REGI <> '9'

      AND (psCodRegion IS NULL OR B.COD_REGI = psCodRegion)
      AND B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
      AND B.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
      AND B.COD_CLIE = A.CLIE_COD_CLIE
      AND B.EST_REGI <> '9'
      AND NOT EXISTS
          (SELECT X.COD_CLIE
           FROM
             EDU_GTT_INTER_DATAM X
           WHERE X.CAM_PROC = psCodPeriodo
             AND X.COD_PAIS = psCodigoPais
             AND X.COD_EMPR_COME = psCodEmpresa
             AND (psCodRegion IS NULL OR X.COD_REGI = psCodRegion)
             AND X.COD_CLIE = B.COD_CLIE);

    /* Consultoras Programadas */
    CURSOR c_loteProgra IS
     SELECT DISTINCT
       A.CLIE_COD_CLIE ,
       A.CAM_proc,
	   B.COD_REGI
    FROM EDU_PLANI_PROGR_CURSO A,
         EDU_MAEST_CLIEN B
    WHERE A.PAIS_COD_PAIS = psCodigoPais
    AND A.EMCO_COD_EMPR_COME = psCodEmpresa
      --AND A.EST_CAPA = INDICADOR_PROGRAMADA
      --AND A.ULT_CAMP_PROG_DICT = psCodPeriodo
	  AND A.CAM_proc = psCodPeriodo
      AND A.EST_REGI <> '9'

      AND (psCodRegion IS NULL OR B.COD_REGI = psCodRegion)
      AND B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
      AND B.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
      AND B.COD_CLIE = A.CLIE_COD_CLIE
      AND B.EST_REGI <> '9'
      AND NOT EXISTS
          (SELECT X.COD_CLIE
           FROM
             EDU_GTT_INTER_DATAM X
           WHERE X.CAM_PROC = psCodPeriodo
             AND X.COD_PAIS = psCodigoPais
             AND X.COD_EMPR_COME = psCodEmpresa
             AND ( psCodRegion IS NULL OR X.COD_REGI = psCodRegion)
             AND X.COD_CLIE = B.COD_CLIE);

   /* Consultoras Capacitadas */
   CURSOR c_loteCapa IS
   SELECT DISTINCT
       A.CLIE_COD_CLIE,
       C.ULT_CAMP_PROG_DICT,
	   B.COD_REGI
    FROM
         EDU_HISTO_CAPAC_DETAL A,
         EDU_MAEST_CLIEN B,
         EDU_HISTO_APTAS C

    WHERE A.PAIS_COD_PAIS = psCodigoPais
      AND A.EMCO_COD_EMPR_COME = psCodEmpresa
      AND A.CAM_CAPA = psCodPeriodo
      AND A.EST_REGI <> '9'

      AND (psCodRegion IS NULL OR B.COD_REGI = psCodRegion)
      AND B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
      AND B.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
      AND B.COD_CLIE = A.CLIE_COD_CLIE
      AND B.EST_REGI <> '9'

      AND C.PAIS_COD_PAIS = psCodigoPais
      AND C.EMCO_COD_EMPR_COME = psCodEmpresa
      AND C.CCAP_COD_CURS_CAPA = A.CCAP_COD_CURS_CAPA
      AND C.CLIE_COD_CLIE = B.COD_CLIE
      AND C.EST_CAPA = INDICADOR_CAPACITADA
      AND C.EST_REGI <> '9'
      AND NOT EXISTS
          (SELECT X.COD_CLIE
           FROM
             EDU_GTT_INTER_DATAM X
           WHERE X.CAM_PROC = psCodPeriodo
             AND X.COD_PAIS = psCodigoPais
             AND X.COD_EMPR_COME = psCodEmpresa
             AND (psCodRegion IS NULL OR X.COD_REGI = psCodRegion)
             AND X.COD_CLIE = B.COD_CLIE);

   /* Consultoras con dictado en campaña de proceso */
   CURSOR c_loteDictado IS
   SELECT DISTINCT
       A.CLIE_COD_CLIE,
       C.ULT_CAMP_PROG_DICT,
	   D.COD_REGI
    FROM EDU_HISTO_CURSO_DICTA_DETAL A,
         EDU_HISTO_CURSO_DICTA_CABEC B,
         EDU_HISTO_APTAS C,
		 EDU_MAEST_CLIEN D
    WHERE A.PAIS_COD_PAIS = psCodigoPais
    AND A.EMCO_COD_EMPR_COME = psCodEmpresa
      AND A.EST_REGI <> '9'

      AND (psCodRegion IS NULL OR B.COD_REGI = psCodRegion)
      AND B.CAM_INIC_CURS = psCodPeriodo
      AND B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
      AND B.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
      AND B.CCAP_COD_CURS_CAPA = A.CCAP_COD_CURS_CAPA
      AND B.COD_CURS_DICT = A.CDIC_COD_CURS_DICT
      AND B.EST_REGI <> '9'

      AND C.PAIS_COD_PAIS = A.PAIS_COD_PAIS
      AND C.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
      AND C.CCAP_COD_CURS_CAPA = B.CCAP_COD_CURS_CAPA
      AND C.CLIE_COD_CLIE = A.CLIE_COD_CLIE

      AND D.PAIS_COD_PAIS = A.PAIS_COD_PAIS
      AND D.EMCO_COD_EMPR_COME = A.EMCO_COD_EMPR_COME
      AND D.COD_CLIE = A.CLIE_COD_CLIE


      AND NOT EXISTS
          (SELECT X.COD_CLIE
           FROM
             EDU_GTT_INTER_DATAM X
           WHERE X.CAM_PROC = psCodPeriodo
             AND X.COD_PAIS = psCodigoPais
             AND X.COD_EMPR_COME = psCodEmpresa
             AND (psCodRegion IS NULL OR X.COD_REGI = psCodRegion)
             AND X.COD_CLIE = A.CLIE_COD_CLIE);

   TYPE t_ultima  IS TABLE OF       EDU_HISTO_APTAS.ULT_CAMP_PROG_DICT%TYPE ;
   TYPE t_codCons   IS TABLE OF       EDU_HISTO_APTAS.CLIE_COD_CLIE%TYPE ;
   --se agrego region puese ser todas
   TYPE t_codRegion IS TABLE OF  EDU_REGIO.COD_REGI%TYPE ;
   v_codRegion    t_codRegion;

   v_codCons    t_codCons;
   v_ultima     t_ultima;
   lbEncontro   BOOLEAN;
   lbError      BOOLEAN := FALSE;

BEGIN
    DELETE FROM EDU_GTT_INTER_DATAM;
    lbEncontro := FALSE;

    /* Guardando clientes registrados en la interfaz DataMart en tabla temporal*/
    INSERT INTO EDU_GTT_INTER_DATAM(
      CAM_PROC, COD_PAIS, COD_EMPR_COME,
      COD_REGI, COD_CLIE, EST_CAPA,
      ULT_CAMP_DICT)
    SELECT
      A.CAM_PROC,
      A.COD_PAIS,
      A.COD_EMPR_COME,
      A.COD_REGI,
      A.COD_CLIE,
      A.EST_CAPA,
      A.ULT_CAMP_DICT
    FROM EDU_TMP_INTER_DATAM_CONSU A
    WHERE A.CAM_PROC = psCodPeriodo
      AND A.COD_PAIS =  psCodigoPais
      AND A.COD_EMPR_COME = psCodEmpresa
      AND (psCodRegion IS NULL OR A.COD_REGI = psCodRegion);

    /* Eliminando clientes registrados en la interfaz DataMart */
    DELETE FROM EDU_TMP_INTER_DATAM_CONSU A
    WHERE A.COD_PAIS =  psCodigoPais
      AND A.COD_EMPR_COME = psCodEmpresa
      AND (psCodRegion IS NULL OR A.COD_REGI = psCodRegion);

    /* Actualizando el nro de lote Datamart (APTAS)*/
    OPEN c_loteApta;
    LOOP
       FETCH c_loteApta BULK COLLECT INTO
             v_codCons, v_ultima,v_codRegion LIMIT W_FILAS;
       IF v_codCons.COUNT > 0 THEN
          lbEncontro := TRUE;
          FOR x IN v_codCons.FIRST .. v_codCons.LAST LOOP
             BEGIN
               INSERT INTO EDU_TMP_INTER_DATAM_CONSU(
                 CAM_PROC, COD_PAIS, COD_EMPR_COME,
                 COD_REGI, COD_CLIE, EST_CAPA, ULT_CAMP_DICT)
               VALUES (
                 psCodPeriodo, psCodigoPais, psCodEmpresa,
                 v_codRegion(x), v_codCons(x), INDICADOR_PENDIENTE,
                 v_ultima(x)
                 );
             EXCEPTION
             WHEN OTHERS THEN
                  lbError := TRUE;
             END ;
          END LOOP;
       END IF;
       EXIT WHEN c_loteApta%NOTFOUND;
    END LOOP;
    CLOSE c_loteApta;

    /* Actualizando el nro de lote Datamart (PROGRAMADAS)*/
    OPEN c_loteProgra;
    LOOP
       FETCH c_loteProgra BULK COLLECT INTO
             v_codCons, v_ultima,v_codRegion LIMIT W_FILAS;
       IF v_codCons.COUNT > 0 THEN
          lbEncontro := TRUE;
          FOR x IN v_codCons.FIRST .. v_codCons.LAST LOOP
             BEGIN
             INSERT INTO EDU_TMP_INTER_DATAM_CONSU(
               CAM_PROC, COD_PAIS, COD_EMPR_COME,
               COD_REGI, COD_CLIE, EST_CAPA, ULT_CAMP_DICT)
             VALUES (
               psCodPeriodo, psCodigoPais, psCodEmpresa,
               v_codRegion(x), v_codCons(x), INDICADOR_PROGRAMADA,
               v_ultima(x)
             );
             EXCEPTION
             WHEN OTHERS THEN
                  lbError := TRUE;
             END ;
          END LOOP;
       END IF;
       EXIT WHEN c_loteProgra%NOTFOUND;
    END LOOP;
    CLOSE c_loteProgra;

    /* Actualizando el nro de lote Datamart (CAPACITADAS) */
    OPEN c_loteCapa;
    LOOP
       FETCH c_loteCapa BULK COLLECT INTO
             v_codCons, v_ultima,v_codRegion LIMIT W_FILAS;
       IF v_codCons.COUNT > 0 THEN
          lbEncontro := TRUE;
          FOR x IN v_codCons.FIRST .. v_codCons.LAST LOOP
             BEGIN
             INSERT INTO EDU_TMP_INTER_DATAM_CONSU(
               CAM_PROC, COD_PAIS, COD_EMPR_COME,
               COD_REGI, COD_CLIE, EST_CAPA, ULT_CAMP_DICT)
             VALUES (
               psCodPeriodo, psCodigoPais, psCodEmpresa,
               v_codRegion(x), v_codCons(x), INDICADOR_CAPACITADA,
               v_ultima(x)
             );
             EXCEPTION
             WHEN OTHERS THEN
                  lbError := TRUE;
             END ;
          END LOOP;
       END IF;
       EXIT WHEN c_loteCapa%NOTFOUND;
    END LOOP;
    CLOSE c_loteCapa;

    /* Actualizando el nro de lote Datamart (Cursos Dictados) */
    OPEN c_loteDictado;
    LOOP
       FETCH c_loteDictado BULK COLLECT INTO
             v_codCons, v_ultima ,v_codRegion LIMIT W_FILAS;
       IF v_codCons.COUNT > 0 THEN
          lbEncontro := TRUE;
          FOR x IN v_codCons.FIRST .. v_codCons.LAST LOOP
             BEGIN
             INSERT INTO EDU_TMP_INTER_DATAM_CONSU(
               CAM_PROC, COD_PAIS, COD_EMPR_COME,
               COD_REGI, COD_CLIE, EST_CAPA, ULT_CAMP_DICT)
             VALUES (
               psCodPeriodo, psCodigoPais, psCodEmpresa,
               v_codRegion(x), v_codCons(x), INDICADOR_PROGRAMADA,
               v_ultima(x)
             );
             EXCEPTION
             WHEN OTHERS THEN
                  lbError := TRUE;
             END ;
          END LOOP;
       END IF;
       EXIT WHEN c_loteDictado%NOTFOUND;
    END LOOP;
    CLOSE c_loteDictado;


    /* En caso no haya grabado ningun registro */
    IF NOT lbEncontro THEN
       INSERT INTO EDU_TMP_INTER_DATAM_CONSU(
               CAM_PROC, COD_PAIS, COD_EMPR_COME,
               COD_REGI, COD_CLIE, EST_CAPA, ULT_CAMP_DICT)
       (SELECT CAM_PROC, COD_PAIS, COD_EMPR_COME,
              COD_REGI, COD_CLIE, EST_CAPA, ULT_CAMP_DICT
       FROM EDU_GTT_INTER_DATAM);
    END IF;

    RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR EDU_PR_ACTUA_INTER_DATAM: '||ls_sqlerrm);
END EDU_PR_ACTUA_INTER_DATAM;


/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Objetivos Asistencia Anual
Fecha Creacion    : 15/10/2007
Autor             : Robinson Vela Bardales
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion      : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_OBJET_PAIS_ANUAL
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
)
IS
   CURSOR c_interfaz IS
     SELECT
       A.PAIS_COD_PAIS,
       A.EMCO_COD_EMPR_COME,
       A.CCAP_COD_CURS_CAPA,
       A.COD_ANNO,
       A.VAL_PORC,
      '0' AS FLA_CONT
    FROM EDU_OBJET_ASIST_PAIS_ANUAL A
    WHERE A.PAIS_COD_PAIS = psCodigoPais
    AND A.EMCO_COD_EMPR_COME = psCodEmpresa
    AND A.COD_ANNO = SUBSTR(psCodPeriodo,1,4)
    AND A.EST_REGI <> '9'
    ORDER BY A.PAIS_COD_PAIS, A.EMCO_COD_EMPR_COME, A.CCAP_COD_CURS_CAPA;

   TYPE interfaz IS RECORD   (
      codigoPais              VARCHAR2(3),
      codigoEmpresa           VARCHAR2(4),
      codigoCurso             VARCHAR2(3),
      codigoAnho              VARCHAR2(4),
      valorPorcentaje         NUMBER(6,2),
      flagControl             VARCHAR2(1)
   );

   TYPE interfazTab  IS TABLE OF interfaz ;
   interfazRecord interfazTab;
   lsCodigoPaisData    EDU_PARAM_PROGR_CAPAC.COD_PAIS_DATA%TYPE;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;
   W_DESC              VARCHAR2(200);
   lsLinea             VARCHAR2(1000);
   lsLineaCabecera     VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

BEGIN
    /* Procedimiento inicial para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
        psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

    /* Obteniendo lista */
    lsCodigoPaisData := EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_PAIS_DATAM(psCodigoPais, psCodEmpresa, '01');
    OPEN c_interfaz;
    LOOP
       FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          lsLinea :=  lsCodigoPaisData              ||';'||
                      interfazRecord(x).codigoEmpresa         ||';'||
                      interfazRecord(x).codigoCurso           ||';'||
                      interfazRecord(x).codigoAnho            ||';'||
                      trim(to_char(interfazRecord(x).valorPorcentaje,'9999.00'))||';'||
                      interfazRecord(x).flagControl        ;
              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    utl_file.fclose(V_HANDLE);

    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);


    RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_DAT_OBJET_PAIS_PERIO: '||ls_sqlerrm);
END INT_PR_DAT_OBJET_PAIS_ANUAL;


/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Objetivos Asistencia Periodo
Fecha Creacion    : 15/10/2007
Autor             : Robinson Vela Bardales
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion      : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_OBJET_PAIS_PERIO
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
)
IS
   CURSOR c_interfaz IS
     SELECT
       A.PAIS_COD_PAIS,
       A.EMCO_COD_EMPR_COME,
       A.CCAP_COD_CURS_CAPA,
       A.COD_PERI,
       A.VAL_PORC,
       '0' AS FLA_CONT
    FROM EDU_OBJET_ASIST_PAIS_PERIO A
    WHERE A.PAIS_COD_PAIS = psCodigoPais
    AND A.EMCO_COD_EMPR_COME = psCodEmpresa
    AND SUBSTR(A.COD_PERI,1,4) = SUBSTR(psCodPeriodo,1,4)
    AND A.EST_REGI <> '9'
    ORDER BY A.PAIS_COD_PAIS, A.EMCO_COD_EMPR_COME, A.CCAP_COD_CURS_CAPA, A.COD_PERI;


   TYPE interfaz IS RECORD   (
      codigoPais              VARCHAR2(3),
      codigoEmpresa           VARCHAR2(4),
      codigoCurso             VARCHAR2(3),
      codigoPeriodo           VARCHAR2(6),
      valorPorcentaje         NUMBER(6,2),
      flagControl             VARCHAR2(1)
   );

   TYPE interfazTab  IS TABLE OF interfaz ;
   interfazRecord interfazTab;
   lsCodigoPaisData    EDU_PARAM_PROGR_CAPAC.COD_PAIS_DATA%TYPE;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;
   W_DESC              VARCHAR2(200);
   lsLinea             VARCHAR2(1000);
   lsLineaCabecera     VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

BEGIN
    /* Procedimiento inicial para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
        psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

    /* Obteniendo lista */
    lsCodigoPaisData := EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_PAIS_DATAM(psCodigoPais, psCodEmpresa, '01');
    OPEN c_interfaz;
    LOOP
       FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          lsLinea :=  lsCodigoPaisData            ||';'||
                      interfazRecord(x).codigoEmpresa         ||';'||
                      interfazRecord(x).codigoCurso           ||';'||
                      interfazRecord(x).codigoPeriodo         ||';'||
                      trim(to_char(interfazRecord(x).valorPorcentaje,'9999.00'))||';'||
                      interfazRecord(x).flagControl        ;
              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    utl_file.fclose(V_HANDLE);

    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);


    RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_DAT_OBJET_PAIS_PERIO: '||ls_sqlerrm);
END INT_PR_DAT_OBJET_PAIS_PERIO;


/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Objetivos Asistencia Región Anual
Fecha Creacion    : 15/10/2007
Autor             : Robinson Vela Bardales
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion      : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_OBJET_REGIO_ANUAL
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
)
IS
   CURSOR c_interfaz IS
     SELECT
       A.PAIS_COD_PAIS,
       A.EMCO_COD_EMPR_COME,
       A.CCAP_COD_CURS_CAPA,
       A.REGI_COD_REGI,
       A.COD_ANNO,
       A.VAL_PORC,
       '0' AS FLA_CONT
    FROM EDU_OBJET_ASIST_REGIO_ANUAL A
    WHERE A.PAIS_COD_PAIS    = psCodigoPais
    AND A.EMCO_COD_EMPR_COME = psCodEmpresa
    AND (psCodRegion IS NULL OR A.REGI_COD_REGI      = psCodRegion)
    AND A.COD_ANNO = SUBSTR(psCodPeriodo,1,4)
    AND A.EST_REGI <> '9'
    ORDER BY A.PAIS_COD_PAIS, A.EMCO_COD_EMPR_COME, A.CCAP_COD_CURS_CAPA;


   TYPE interfaz IS RECORD   (
      codigoPais              VARCHAR2(3),
      codigoEmpresa           VARCHAR2(4),
      codigoCurso             VARCHAR2(3),
      codigoRegion            VARCHAR2(2),
      codigoAnho              VARCHAR2(4),
      valorPorcentaje         NUMBER(6,2),
      flagControl             VARCHAR2(1)
   );

   TYPE interfazTab  IS TABLE OF interfaz ;
   interfazRecord interfazTab;
   lsCodigoPaisData    EDU_PARAM_PROGR_CAPAC.COD_PAIS_DATA%TYPE;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;
   W_DESC              VARCHAR2(200);
   lsLinea             VARCHAR2(1000);
   lsLineaCabecera     VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

BEGIN
    /* Procedimiento inicial para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
        psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

    /* Obteniendo lista */
    lsCodigoPaisData := EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_PAIS_DATAM(psCodigoPais, psCodEmpresa, '01');
    OPEN c_interfaz;
    LOOP
       FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          lsLinea :=  lsCodigoPaisData           ||';'||
                      interfazRecord(x).codigoEmpresa         ||';'||
                      interfazRecord(x).codigoCurso           ||';'||
                      interfazRecord(x).codigoRegion          ||';'||
                      interfazRecord(x).codigoAnho            ||';'||
                      trim(to_char(interfazRecord(x).valorPorcentaje,'9999.00'))||';'||
                      interfazRecord(x).flagControl        ;
              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    utl_file.fclose(V_HANDLE);

    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);


    RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_DAT_OBJET_REGIO_ANUAL: '||ls_sqlerrm);
END INT_PR_DAT_OBJET_REGIO_ANUAL;

/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Objetivos Asistencia Región Periodo
Fecha Creacion    : 15/10/2007
Autor             : Robinson Vela Bardales
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion      : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_OBJET_REGIO_PERIO
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
)
IS
   CURSOR c_interfaz IS
     SELECT
       A.PAIS_COD_PAIS,
       A.EMCO_COD_EMPR_COME,
       A.CCAP_COD_CURS_CAPA,
       A.REGI_COD_REGI,
       A.COD_PERI,
       A.VAL_PORC,
       '0' AS FLA_CONT
    FROM EDU_OBJET_ASIST_REGIO_PERIO A
    WHERE A.PAIS_COD_PAIS = psCodigoPais
    AND A.EMCO_COD_EMPR_COME = psCodEmpresa
    AND (psCodRegion IS NULL OR A.REGI_COD_REGI = psCodRegion)
    AND SUBSTR(A.COD_PERI,1,4) = SUBSTR(psCodPeriodo,1,4)
    AND A.EST_REGI <> '9'
    ORDER BY A.PAIS_COD_PAIS, A.EMCO_COD_EMPR_COME, A.CCAP_COD_CURS_CAPA,
             A.REGI_COD_REGI, A.COD_PERI;

   TYPE interfaz IS RECORD   (
      codigoPais              VARCHAR2(3),
      codigoEmpresa           VARCHAR2(4),
      codigoCurso             VARCHAR2(3),
      codigoRegion            VARCHAR2(2),
      codigoPeriodo           VARCHAR2(6),
      valorPorcentaje         NUMBER(6,2),
      flagControl             VARCHAR2(1)
   );

   TYPE interfazTab  IS TABLE OF interfaz ;
   interfazRecord interfazTab;
   lsCodigoPaisData    EDU_PARAM_PROGR_CAPAC.COD_PAIS_DATA%TYPE;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;
   W_DESC              VARCHAR2(200);
   lsLinea             VARCHAR2(1000);
   lsLineaCabecera     VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

BEGIN
    /* Procedimiento inicial para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
        psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

    /* Obteniendo lista */
    lsCodigoPaisData := EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_PAIS_DATAM(psCodigoPais, psCodEmpresa, '01');
    OPEN c_interfaz;
    LOOP
       FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          lsLinea :=  lsCodigoPaisData           ||';'||
                      interfazRecord(x).codigoEmpresa         ||';'||
                      interfazRecord(x).codigoCurso           ||';'||
                      interfazRecord(x).codigoRegion          ||';'||
                      interfazRecord(x).codigoPeriodo         ||';'||
                      trim(to_char(interfazRecord(x).valorPorcentaje,'9999.00'))||';'||
                      interfazRecord(x).flagControl        ;
              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    utl_file.fclose(V_HANDLE);

    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);


    RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_DAT_OBJET_REGIO_PERIO: '||ls_sqlerrm);
END INT_PR_DAT_OBJET_REGIO_PERIO;

/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Objetivos Asistencia Zona Anual
Fecha Creacion    : 15/10/2007
Autor             : Robinson Vela Bardales
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion      : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_OBJET_ZONA_ANUAL
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
)
IS
   CURSOR c_interfaz IS
     SELECT
       A.PAIS_COD_PAIS,
       A.EMCO_COD_EMPR_COME,
       A.CCAP_COD_CURS_CAPA,
       A.REGI_COD_REGI,
       A.ZONA_COD_ZONA,
       A.COD_ANNO,
       A.VAL_PORC,
       '0' AS FLA_CONT
    FROM EDU_OBJET_ASIST_ZONA_ANUAL A
    WHERE A.PAIS_COD_PAIS    = psCodigoPais
    AND A.EMCO_COD_EMPR_COME = psCodEmpresa
    AND (psCodRegion IS NULL OR A.REGI_COD_REGI      = psCodRegion)
    AND A.COD_ANNO           = SUBSTR(psCodPeriodo,1,4)
    AND A.EST_REGI <> '9'
    ORDER BY A.PAIS_COD_PAIS, A.EMCO_COD_EMPR_COME, A.CCAP_COD_CURS_CAPA;


   TYPE interfaz IS RECORD   (
      codigoPais              VARCHAR2(3),
      codigoEmpresa           VARCHAR2(4),
      codigoCurso             VARCHAR2(3),
      codigoRegion            VARCHAR2(2),
      codigoZona              VARCHAR2(4),
      codigoAnho              VARCHAR2(4),
      valorPorcentaje         NUMBER(6,2),
      flagControl             VARCHAR2(1)
   );

   TYPE interfazTab  IS TABLE OF interfaz ;
   interfazRecord interfazTab;
   lsCodigoPaisData    EDU_PARAM_PROGR_CAPAC.COD_PAIS_DATA%TYPE;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;
   W_DESC              VARCHAR2(200);
   lsLinea             VARCHAR2(1000);
   lsLineaCabecera     VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

BEGIN
    /* Procedimiento inicial para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
        psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

    /* Obteniendo lista */
    lsCodigoPaisData := EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_PAIS_DATAM(psCodigoPais, psCodEmpresa, '01');
    OPEN c_interfaz;
    LOOP
       FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          lsLinea :=  lsCodigoPaisData            ||';'||
                      interfazRecord(x).codigoEmpresa         ||';'||
                      interfazRecord(x).codigoCurso           ||';'||
                      interfazRecord(x).codigoRegion          ||';'||
                      interfazRecord(x).codigoZona            ||';'||
                      interfazRecord(x).codigoAnho            ||';'||
                      trim(to_char(interfazRecord(x).valorPorcentaje,'9999.00'))||';'||
                      interfazRecord(x).flagControl        ;
              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    utl_file.fclose(V_HANDLE);

    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);


    RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_DAT_OBJET_ZONA_ANUAL: '||ls_sqlerrm);
END INT_PR_DAT_OBJET_ZONA_ANUAL;

/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Objetivos Asistencia Zona Periodo
Fecha Creacion    : 15/10/2007
Autor             : Robinson Vela Bardales
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion      : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_OBJET_ZONA_PERIO
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
)
IS
   CURSOR c_interfaz IS
     SELECT
       A.PAIS_COD_PAIS,
       A.EMCO_COD_EMPR_COME,
       A.CCAP_COD_CURS_CAPA,
       A.REGI_COD_REGI,
       A.ZONA_COD_ZONA,
       A.COD_PERI,
       A.VAL_PORC,
       '0' AS FLA_CONT
    FROM EDU_OBJET_ASIST_ZONA_PERIO A
    WHERE A.PAIS_COD_PAIS = psCodigoPais
    AND A.EMCO_COD_EMPR_COME = psCodEmpresa
    AND (psCodRegion IS NULL OR A.REGI_COD_REGI = psCodRegion)
    AND SUBSTR(A.COD_PERI,1,4) = SUBSTR(psCodPeriodo,1,4)
    AND A.EST_REGI <> '9'
    ORDER BY A.PAIS_COD_PAIS, A.EMCO_COD_EMPR_COME, A.CCAP_COD_CURS_CAPA,
             A.REGI_COD_REGI, A.ZONA_COD_ZONA, A.COD_PERI;


   TYPE interfaz IS RECORD   (
      codigoPais              VARCHAR2(3),
      codigoEmpresa           VARCHAR2(4),
      codigoCurso             VARCHAR2(3),
      codigoRegion            VARCHAR2(2),
      codigoZona              VARCHAR2(4),
      codigoPeriodo           VARCHAR2(6),
      valorPorcentaje         NUMBER(6,2),
      flagControl             VARCHAR2(1)
   );

   TYPE interfazTab  IS TABLE OF interfaz ;
   interfazRecord interfazTab;
   lsCodigoPaisData    EDU_PARAM_PROGR_CAPAC.COD_PAIS_DATA%TYPE;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;
   W_DESC              VARCHAR2(200);
   lsLinea             VARCHAR2(1000);
   lsLineaCabecera     VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

BEGIN
    /* Procedimiento inicial para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
        psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

    /* Obteniendo lista */
    lsCodigoPaisData := EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_PAIS_DATAM(psCodigoPais, psCodEmpresa, '01');
    OPEN c_interfaz;
    LOOP
       FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          lsLinea :=  lsCodigoPaisData            ||';'||
                      interfazRecord(x).codigoEmpresa         ||';'||
                      interfazRecord(x).codigoCurso           ||';'||
                      interfazRecord(x).codigoRegion          ||';'||
                      interfazRecord(x).codigoZona            ||';'||
                      interfazRecord(x).codigoPeriodo         ||';'||
                      trim(to_char(interfazRecord(x).valorPorcentaje,'9999.00'))||';'||
                      interfazRecord(x).flagControl        ;
              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    utl_file.fclose(V_HANDLE);

    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);


    RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_DAT_OBJET_ZONA_PERIO: '||ls_sqlerrm);
END INT_PR_DAT_OBJET_ZONA_PERIO;

/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar File Control
Fecha Creacion    : 15/10/2007
Autor             : Robinson Vela Bardales
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion      : Codigo de Region
***************************************************************************/
PROCEDURE INT_PR_DAT_FILE_CNTRL
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psCodRegion                VARCHAR2
)
IS
   /*cursor de regiones */
   CURSOR c_interfaz IS
    SELECT A.COD_REGI
    FROM EDU_REGIO A
	WHERE A.PAIS_COD_PAIS=psCodigoPais
		AND A.EMCO_COD_EMPR_COME=psCodEmpresa
		AND (psCodRegion IS NULL OR A.COD_REGI=psCodRegion)
    ORDER BY A.COD_REGI;

   TYPE t_codRegion IS TABLE OF  EDU_REGIO.COD_REGI%TYPE ;
   v_codRegion    t_codRegion;

   lsCodigoPaisData    EDU_PARAM_PROGR_CAPAC.COD_PAIS_DATA%TYPE;
   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;
   W_DESC              VARCHAR2(200);
   lsLinea             VARCHAR2(1000);
   lsLineaCabecera     VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);
   flagControl         VARCHAR2(1):='0';

BEGIN
    /* Procedimiento inicial para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
        psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

    lsCodigoPaisData := EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_PAIS_DATAM(psCodigoPais, psCodEmpresa, '01');


	OPEN c_interfaz;
    LOOP
       FETCH c_interfaz BULK COLLECT INTO v_codRegion  LIMIT W_FILAS;
       IF v_codRegion.COUNT > 0 THEN
          FOR x IN v_codRegion.FIRST .. v_codRegion.LAST LOOP
    lsLinea :=  lsCodigoPaisData        ||';'||
                psCodEmpresa            ||';'||
                psCodPeriodo            ||';'||
				          v_codRegion(x)          ||';'||
                flagControl;
    UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;


    utl_file.fclose(V_HANDLE);

    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);


    RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_DAT_FILE_CNTRL: '||ls_sqlerrm);
END INT_PR_DAT_FILE_CNTRL;

/***************************************************************************
Descripcion       : Genera Interfase Datamart Enviar Archivos de Control(DAT-59)
Fecha Creacion    : 30/06/2010
Autor             : Carlos Diaz Valverde
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
            psCodEmpresa     : Codigo de Empresa
            psCodPeriodo     : Codigo de Periodo
            psCodRegion      : Codigo de Region
            psTipoEnvio      : Tipo de envio
***************************************************************************/
PROCEDURE INT_PR_DAT_CNTRL
(  psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psCodEmpresa               VARCHAR2,
   psCodPeriodo               VARCHAR2,
   psIndSistema               VARCHAR2,
   psIndCierre                VARCHAR2)

IS
   CURSOR c_interfaz IS
    SELECT pscodigopais codigopais,
           pscodempresa codigoempresa,
           coci.cod_peri codigoperiodo,
           to_char(coci.fec_cier,
                   'YYYYMMDD') fecha,
       CASE
             WHEN coci.tcie_oid_tipo_cier = 3 THEN
              '1'
             ELSE
              '0'
       END cierre
      FROM (SELECT peri.cod_peri,
                   coci.fec_cier,
                   coci.tcie_oid_tipo_cier
          FROM fac_contr_cierr coci,
               cra_perio perd,
               seg_perio_corpo peri
         WHERE coci.perd_oid_peri = perd.oid_peri
           AND perd.peri_oid_peri = peri.oid_peri
               AND peri.cod_peri = pscodperiodo
             GROUP BY peri.cod_peri,
                      coci.fec_cier,
                      coci.tcie_oid_tipo_cier
             ORDER BY peri.cod_peri           DESC,
                      coci.fec_cier           DESC,
                      coci.tcie_oid_tipo_cier DESC) coci
     WHERE rownum = 1
       AND psindsistema = '1'
    UNION ALL
    SELECT a.pais_cod_pais codigopais,
           a.emco_cod_empr_come codigoempresa,
           a.cod_peri codigoperiodo,
           to_char(a.fec_proc,
                   'YYYYMMDD') fecha,
           psindcierre cierre -- 0: Si no es cierre campaña, 1: Si es cierre campaña
      FROM edu_contr_factu a
     WHERE a.PAIS_COD_PAIS = psCodigoPais
       AND a.cod_peri = pscodperiodo
       AND psindsistema != '1';


   TYPE interfaz IS RECORD   (
      codigoPais              VARCHAR2(3),
      codigoEmpresa           VARCHAR2(4),
      codigoPeriodo           VARCHAR2(6),
      fecha                   VARCHAR2(8),
      cierre                  VARCHAR2(1)
   );

   TYPE interfazTab  IS TABLE OF interfaz ;
   interfazRecord interfazTab;
   lsCodigoPaisData    EDU_PARAM_PROGR_CAPAC.COD_PAIS_DATA%TYPE;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;
   W_DESC              VARCHAR2(200);
   lsLinea             VARCHAR2(1000);
   lsLineaCabecera     VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

BEGIN
    /* Procedimiento inicial para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
        psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

    /* Obteniendo lista */
    lsCodigoPaisData := EDU_PKG_PROCE_GENER.EDU_FN_DEVUE_PAIS_DATAM(psCodigoPais, psCodEmpresa, '01');
    OPEN c_interfaz;
    LOOP
       FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          lsLinea :=  lsCodigoPaisData                        ||';'||
                      interfazRecord(x).codigoEmpresa         ||';'||
                      interfazRecord(x).codigoPeriodo         ||';'||
                      interfazRecord(x).fecha                 ||';'||
                      interfazRecord(x).cierre                ;
              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    utl_file.fclose(V_HANDLE);

    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);


    RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_DAT_CNTRL: '||ls_sqlerrm);

END INT_PR_DAT_CNTRL;


END EDU_PKG_INTER;
/

