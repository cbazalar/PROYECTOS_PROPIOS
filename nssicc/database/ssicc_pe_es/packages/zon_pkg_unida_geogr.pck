CREATE OR REPLACE PACKAGE "ZON_PKG_UNIDA_GEOGR" IS

/* Declaracion de variables */
ln_sqlcode   NUMBER(10);
ls_sqlerrm   VARCHAR2(150);
W_FILAS      NUMBER:=1000;

/***************************************************************************
Descripcion       : Elimina la informacion de las tablas que seran utilizadas
                    en el proceso de Actualizar Unidades Geograficas
Fecha Creacion    : 01/04/2008
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE ZON_PR_ELIMI_TABLA_GEOGR;

/***************************************************************************
Descripcion       : Valida la informacion cargada en las tablas de Unidades
                    geograficas segun reglas de negocio y devolva un flag
                    indica si se encontro erorres o no
Fecha Creacion    : 02/04/2008
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE ZON_PR_VALID_ESTRU_GEOGR (psError OUT VARCHAR2);

/***************************************************************************
Descripcion       : Clasifica si los datos geograficos ingresados corresponden
                    a nuevas unidades o modificacion de ya existente para
                    todos los niveles de estructura geopolitica de un pais
Fecha Creacion    : 03/04/2008
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE ZON_PR_VALID_DATOS_GEOGR (psCodigoPais VARCHAR2);

/***************************************************************************
Descripcion       : Actualiza los datos de unidades geograficas en la tabla
                    Valor Estructura Geopolictica del SICC, sea una nueva unidad
                    geografica o actualizacion de uno ya existente
Fecha Creacion    : 04/04/2008
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE ZON_PR_ACTUA_UNIDA_GEOGR (psCodigoPais VARCHAR2, psUsuarioAprobacion VARCHAR2);

/***************************************************************************
Descripcion       : Devuelve el oid de la subEstructura relacionada a la
                    Estructura geopolitica pasada como parametro
Fecha Creacion    : 01/04/2008
Autor             : Sergio Apaza
***************************************************************************/
FUNCTION ZON_FN_DEVUE_COD_SUBES
  (psOidEstrGeopo    NUMBER) RETURN NUMBER;

/***************************************************************************
Descripcion       : Devuelve la descripcion de la subEstructura relacionada a la
                    Estructura geopolitica pasada como parametro
Fecha Creacion    : 01/04/2008
Autor             : Sergio Apaza
***************************************************************************/
FUNCTION ZON_FN_DEVUE_DES_SUBES
  (pnOidEstrGeopo    NUMBER) RETURN VARCHAR2;

/***************************************************************************
Descripcion       : Devuelve el oid de la Estructura Geopolitica de un
                    determinado pais y nivel de Orden Geografico
Fecha Creacion    : 03/04/2008
Autor             : Sergio Apaza
***************************************************************************/
FUNCTION ZON_FN_DEVUE_OID_ESTRU_GEOPO
  (pnOidPais    NUMBER,
   pnOrden      NUMBER) RETURN NUMBER;

/***************************************************************************
Descripcion       : Devuelve el oid de la SubEstructura Geopolitica de un
                    determinado estructura Geopolitica y codigo SubEstructura
Fecha Creacion    : 03/04/2008
Autor             : Sergio Apaza
***************************************************************************/
FUNCTION ZON_FN_DEVUE_OID_SUBES_GEOPO
  (pnOidEstruGeopo        NUMBER,
   pnCodigoSubesGeopo     NUMBER) RETURN NUMBER;

/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte zon Unidades Geograficas
Fecha Creacion    : 19/08/2010
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE ZON_PR_GENER_REPOR_UNIGE_CSV(
    psCodigoPais            VARCHAR2,
    psNombreArchivo         VARCHAR2,
    psTitulo                VARCHAR2,
    psDirectorio       OUT  VARCHAR2
    );

/***************************************************************************
Descripcion       : Genera archivo Excel correspondiente al Reporte direcciones consultas
Fecha Creacion    : 13/08/2013
Autor             : Yahir Rivas Luna
***************************************************************************/
PROCEDURE ZON_PR_REPOR_DIREC_CONSU(
     psCodigoPais            VARCHAR2,
     psCodigoMarca           VARCHAR2,
     psCodigoCanal           VARCHAR2,
     psCodigoTipoCliente     VARCHAR2,
     psCodigoPeriodoInicio   VARCHAR2,
     psEstatus               VARCHAR2,
     psCodigoRegion          VARCHAR2,
     psCondicionUA           VARCHAR2
    );

/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte GIZ Direcciones
                    Consultoras
Fecha Creacion    : 22/08/2013
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE ZON_PR_REPOR_DIREC_CONSU_CSV(
    psCodigoPais            VARCHAR2,
    psNombreArchivo         VARCHAR2,
    psTitulo                VARCHAR2,
    psTitulo2               VARCHAR2,
    psEstrucGeopo1          VARCHAR2,
    psEstrucGeopo2          VARCHAR2,
    psEstrucGeopo3          VARCHAR2,
    psCondicionUA           VARCHAR2,
    psDirectorio       OUT  VARCHAR2
    );

/**************************************************************************
  Descripcion           : Devuelve Ubigeo del DomicilioEntrega del cliente
  Fecha Creacion        : 03/09/2013
  Parametros Entrada    :
        vsOidClie       : Codigo Cliente
  Autor : Juan Altamirano.
  ***************************************************************************/
  FUNCTION fn_retu_ubi_domi_entr
  (
    vsOidClie        NUMBER
  ) RETURN VARCHAR2;

/**************************************************************************
  Descripcion           : Devuelve Ubigeo del Domicilio del cliente
  Fecha Creacion        : 03/09/2013
  Parametros Entrada    :
        vsOidClie       : Codigo Cliente
  Autor : Juan Altamirano.
  ***************************************************************************/
  FUNCTION fn_retu_ubi_domi
  (
    vsOidClie        NUMBER
  ) RETURN VARCHAR2;

/***************************************************************************
Descripcion       : Devuelve la direccion de DOMICILIO/DESPACHO para los reportes de DV
Parametros  :
                    psCodigoCliente: CODIGO DEL CLIENTE
                    psTipoDireccion: Tipo, 1=DOMICILIO, 2=DESPACHO
Fecha Creacion    : 11/09/2013
Autor             : Ivan Tocto Jaimes
***************************************************************************/
FUNCTION ZON_FN_DEVUE_DIREC_CONSU
  (
    psCodigoCliente    VARCHAR2,
    psTipoDireccion VARCHAR2
  ) RETURN VARCHAR2;

END ZON_PKG_UNIDA_GEOGR;
/
CREATE OR REPLACE PACKAGE BODY "ZON_PKG_UNIDA_GEOGR" IS

/***************************************************************************
Descripcion       : Elimina la informacion de las tablas que seran utilizadas
                    en el proceso de Actualizar Unidades Geograficas
Fecha Creacion    : 01/04/2008
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE ZON_PR_ELIMI_TABLA_GEOGR
IS
BEGIN

  DELETE FROM ZON_TMP_ESTRU_GEOGR;

  --DELETE FROM ZON_HISTO_VALID_GEOGR;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR ZON_PR_ELIMI_TABLA_GEOGR: '||ls_sqlerrm);
END;

/***************************************************************************
Descripcion       : Valida la informacion cargada en las tablas de Unidades
                    geograficas segun reglas de negocio y devolva un flag
                    indica si se encontro erorres o no
Fecha Creacion    : 02/04/2008
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE ZON_PR_VALID_ESTRU_GEOGR (psError OUT VARCHAR2)
IS
  CURSOR cursorEstruGeo IS
    SELECT *
    FROM   ZON_TMP_ESTRU_GEOGR
    ORDER  BY VAL_FILA;

  TYPE tTablaEstruGeo  IS TABLE OF ZON_TMP_ESTRU_GEOGR%ROWTYPE;
  tablaRegistroEstruGeo            tTablaEstruGeo;
  registroEstruGeo                 ZON_TMP_ESTRU_GEOGR%ROWTYPE;

  lnNumCodigo                      NUMBER;
  lnFila                           NUMBER := 0;
  lsCodigoCompleto                 VARCHAR2(54);
  lnTotalOcurrencias               NUMBER;
  lsEncontroErrores                VARCHAR2(1);
BEGIN

  --Limpiamos la tabla de Errores
  DELETE FROM ZON_TMP_ESTRU_ERROR;

  OPEN cursorEstruGeo;
  LOOP
    FETCH cursorEstruGeo BULK COLLECT INTO tablaRegistroEstruGeo LIMIT W_FILAS;
    IF tablaRegistroEstruGeo.COUNT > 0 THEN

      FOR x IN tablaRegistroEstruGeo.FIRST .. tablaRegistroEstruGeo.LAST LOOP
        registroEstruGeo := tablaRegistroEstruGeo(x);
        lnFila := lnFila + 1;

        /*1) Validamos que la longitud de los Codigos de Nivel Ingresados
             debe ser de 6 digitos */
        IF (((registroEstruGeo.Cod_Niv1 IS NOT NULL) AND (length(registroEstruGeo.Cod_Niv1) <> 6)) OR
           ((registroEstruGeo.Cod_Niv2 IS NOT NULL) AND (length(registroEstruGeo.Cod_Niv2) <> 6)) OR
           ((registroEstruGeo.Cod_Niv3 IS NOT NULL) AND (length(registroEstruGeo.Cod_Niv3) <> 6)) OR
           ((registroEstruGeo.Cod_Niv4 IS NOT NULL) AND (length(registroEstruGeo.Cod_Niv4) <> 6)) OR
           ((registroEstruGeo.Cod_Niv5 IS NOT NULL) AND (length(registroEstruGeo.Cod_Niv5) <> 6)) OR
           ((registroEstruGeo.Cod_Niv6 IS NOT NULL) AND (length(registroEstruGeo.Cod_Niv6) <> 6)) OR
           ((registroEstruGeo.Cod_Niv7 IS NOT NULL) AND (length(registroEstruGeo.Cod_Niv7) <> 6)) OR
           ((registroEstruGeo.Cod_Niv8 IS NOT NULL) AND (length(registroEstruGeo.Cod_Niv8) <> 6)) OR
           ((registroEstruGeo.Cod_Niv9 IS NOT NULL) AND (length(registroEstruGeo.Cod_Niv9) <> 6))) THEN

          INSERT INTO ZON_TMP_ESTRU_ERROR VALUES(lnFila, '1', null);
        END IF;


        /*2) En los campos codigo de Nivel solo deben recibir Numeros */
        BEGIN

          IF (registroEstruGeo.Cod_Niv1 IS NOT NULL) THEN
            lnNumCodigo := TO_NUMBER(registroEstruGeo.Cod_Niv1);
          END IF;
          IF (registroEstruGeo.Cod_Niv2 IS NOT NULL) THEN
            lnNumCodigo := TO_NUMBER(registroEstruGeo.Cod_Niv2);
          END IF;
          IF (registroEstruGeo.Cod_Niv3 IS NOT NULL) THEN
            lnNumCodigo := TO_NUMBER(registroEstruGeo.Cod_Niv3);
          END IF;
          IF (registroEstruGeo.Cod_Niv4 IS NOT NULL) THEN
            lnNumCodigo := TO_NUMBER(registroEstruGeo.Cod_Niv4);
          END IF;
          IF (registroEstruGeo.Cod_Niv5 IS NOT NULL) THEN
            lnNumCodigo := TO_NUMBER(registroEstruGeo.Cod_Niv5);
          END IF;
          IF (registroEstruGeo.Cod_Niv6 IS NOT NULL) THEN
            lnNumCodigo := TO_NUMBER(registroEstruGeo.Cod_Niv6);
          END IF;
          IF (registroEstruGeo.Cod_Niv7 IS NOT NULL) THEN
            lnNumCodigo := TO_NUMBER(registroEstruGeo.Cod_Niv7);
          END IF;
          IF (registroEstruGeo.Cod_Niv8 IS NOT NULL) THEN
            lnNumCodigo := TO_NUMBER(registroEstruGeo.Cod_Niv8);
          END IF;
          IF (registroEstruGeo.Cod_Niv9 IS NOT NULL) THEN
            lnNumCodigo := TO_NUMBER(registroEstruGeo.Cod_Niv9);
          END IF;

        EXCEPTION
          WHEN VALUE_ERROR THEN
            INSERT INTO ZON_TMP_ESTRU_ERROR VALUES (lnFila, '2', null);
        END;


        /*3) Al concatenar los codigos (CODN1 + CODN2 + CODN3 + ... + COD_NIV9)
             no se deben encontrar repetidos */
        lsCodigoCompleto := registroEstruGeo.COD_NIV1 || registroEstruGeo.COD_NIV2 ||
                            registroEstruGeo.COD_NIV3 || registroEstruGeo.COD_NIV4 ||
                            registroEstruGeo.COD_NIV5 || registroEstruGeo.COD_NIV6 ||
                            registroEstruGeo.COD_NIV7 || registroEstruGeo.COD_NIV8 ||
                            registroEstruGeo.COD_NIV9;

        SELECT COUNT(*)
        INTO   lnTotalOcurrencias
        FROM   ZON_TMP_ESTRU_GEOGR
        WHERE  COD_NIV1 || COD_NIV2 || COD_NIV3 || COD_NIV4 || COD_NIV5 || COD_NIV6
               || COD_NIV7 || COD_NIV8 || COD_NIV9 = lsCodigoCompleto;

        IF (lnTotalOcurrencias > 1) THEN
          INSERT INTO ZON_TMP_ESTRU_ERROR VALUES(lnFila, '3', null);
        END IF;


        /*4) No deben existir descripciones en blanco*/
        IF (((registroEstruGeo.Cod_Niv1 IS NOT NULL) AND (registroEstruGeo.Des_Niv1 IS NULL)) OR
           ((registroEstruGeo.Cod_Niv2 IS NOT NULL) AND (registroEstruGeo.Des_Niv2 IS NULL)) OR
           ((registroEstruGeo.Cod_Niv3 IS NOT NULL) AND (registroEstruGeo.Des_Niv3 IS NULL)) OR
           ((registroEstruGeo.Cod_Niv4 IS NOT NULL) AND (registroEstruGeo.Des_Niv4 IS NULL)) OR
           ((registroEstruGeo.Cod_Niv5 IS NOT NULL) AND (registroEstruGeo.Des_Niv5 IS NULL)) OR
           ((registroEstruGeo.Cod_Niv6 IS NOT NULL) AND (registroEstruGeo.Des_Niv6 IS NULL)) OR
           ((registroEstruGeo.Cod_Niv7 IS NOT NULL) AND (registroEstruGeo.Des_Niv7 IS NULL)) OR
           ((registroEstruGeo.Cod_Niv8 IS NOT NULL) AND (registroEstruGeo.Des_Niv8 IS NULL)) OR
           ((registroEstruGeo.Cod_Niv9 IS NOT NULL) AND (registroEstruGeo.Des_Niv9 IS NULL))) THEN

          INSERT INTO ZON_TMP_ESTRU_ERROR VALUES(lnFila, '4', null);
        END IF;

        /* 6) El indicador de georeferencia solo debe de ser 1, 0 o nulo */

        IF (NOT((registroEstruGeo.IND_GEOR IS NULL) OR (registroEstruGeo.IND_GEOR = '0') OR (registroEstruGeo.IND_GEOR = '1'))) THEN

            INSERT INTO ZON_TMP_ESTRU_ERROR VALUES(lnFila, '6', null);

        END IF;

      END LOOP;
    END IF;

    EXIT WHEN cursorEstruGeo%NOTFOUND;
  END LOOP;
  CLOSE cursorEstruGeo;

  /*5) Las descripciones no deben variar para un mismo Nivel. Por Ejemplo:    */
  /*          - Todos los registros que tengan el mismo COD_NIV1 deben tener la */
  /*            misma descripcion en el campo DESC_N1                         */
  /*          - Todos los registros que tengan el mismo COD_NIV1+COD_NIV2 deben   */
  /*            tener la misma descripcion en DESC_N2                         */
  INSERT INTO ZON_TMP_ESTRU_ERROR(VAL_FILA, COD_ERRO, COD_UNID_GEOG)
  SELECT 0,'5',cod
  FROM ((SELECT   cod_niv1 cod, COUNT (DISTINCT des_niv1)
             FROM ZON_TMP_ESTRU_GEOGR
         GROUP BY cod_niv1
           HAVING COUNT (DISTINCT des_niv1) > 1)
        UNION
        (SELECT   cod_niv1 || '-' || cod_niv2 cod, COUNT (DISTINCT des_niv2)
             FROM ZON_TMP_ESTRU_GEOGR
         GROUP BY cod_niv1, cod_niv2
           HAVING COUNT (DISTINCT des_niv2) > 1)
        UNION
        (SELECT   cod_niv1 || '-' || cod_niv2 || '-' || cod_niv3 cod, COUNT (DISTINCT des_niv3)
             FROM ZON_TMP_ESTRU_GEOGR
         GROUP BY cod_niv1, cod_niv2, cod_niv3
           HAVING COUNT (DISTINCT des_niv3) > 1)
        UNION
        (SELECT   cod_niv1 || '-' || cod_niv2 || '-' || cod_niv3 || '-' || cod_niv4 cod,
                  COUNT (DISTINCT des_niv4)
             FROM ZON_TMP_ESTRU_GEOGR
         GROUP BY cod_niv1, cod_niv2, cod_niv3, cod_niv4
           HAVING COUNT (DISTINCT des_niv4) > 1)
        UNION
        (SELECT   cod_niv1 || '-' || cod_niv2 || '-' || cod_niv3 || '-' || cod_niv4 || '-' || cod_niv5 cod,
                  COUNT (DISTINCT des_niv5)
             FROM ZON_TMP_ESTRU_GEOGR
         GROUP BY cod_niv1, cod_niv2, cod_niv3, cod_niv4, cod_niv5
           HAVING COUNT (DISTINCT des_niv5) > 1)
        UNION
        (SELECT   cod_niv1 || '-' || cod_niv2 || '-' || cod_niv3 || '-' || cod_niv4 || '-' || cod_niv5
      || '-' || cod_niv6 cod,
                  COUNT (DISTINCT des_niv6)
             FROM ZON_TMP_ESTRU_GEOGR
         GROUP BY cod_niv1, cod_niv2, cod_niv3, cod_niv4, cod_niv5, cod_niv6
           HAVING COUNT (DISTINCT des_niv6) > 1)
        UNION
        (SELECT   cod_niv1 || '-' || cod_niv2 || '-' || cod_niv3 || '-' || cod_niv4 || '-' || cod_niv5
      || '-' || cod_niv6 || '-' || cod_niv7 cod,
                  COUNT (DISTINCT des_niv7)
             FROM ZON_TMP_ESTRU_GEOGR
         GROUP BY cod_niv1, cod_niv2, cod_niv3, cod_niv4, cod_niv5, cod_niv6, cod_niv7
           HAVING COUNT (DISTINCT des_niv7) > 1)
        UNION
        (SELECT   cod_niv1 || '-' || cod_niv2 || '-' || cod_niv3 || '-' || cod_niv4 || '-' || cod_niv5
            || '-' || cod_niv6 || '-' || cod_niv7 || '-' || cod_niv8 cod,
      COUNT (DISTINCT des_niv8)
             FROM ZON_TMP_ESTRU_GEOGR
         GROUP BY cod_niv1,cod_niv2,cod_niv3,cod_niv4,cod_niv5,cod_niv6,
                  cod_niv7,cod_niv8
           HAVING COUNT (DISTINCT des_niv8) > 1)
        UNION
        (SELECT   cod_niv1 || '-' || cod_niv2 || '-' || cod_niv3 || '-' || cod_niv4 || '-' || cod_niv5
            || '-' || cod_niv6 || '-' || cod_niv7 || '-' || cod_niv8 || '-' || cod_niv9 cod,
                  COUNT (DISTINCT des_niv9)
             FROM ZON_TMP_ESTRU_GEOGR
         GROUP BY cod_niv1,  cod_niv2,  cod_niv3, cod_niv4, cod_niv5, cod_niv6,
            cod_niv7,  cod_niv8, cod_niv9
           HAVING COUNT (DISTINCT des_niv9) > 1));


  /*Consultamos la cantidad de errores encontrados y lo devolvemos como respuesta*/
  SELECT DECODE(COUNT(*), 0,'0','1')
  INTO  lsEncontroErrores
  FROM  ZON_TMP_ESTRU_ERROR;


  psError := lsEncontroErrores;
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR ZON_PR_VALID_ESTRU_GEOGR: '||ls_sqlerrm);
END;

/***************************************************************************
Descripcion       : Clasifica si los datos geograficos ingresados corresponden
                    a nuevas unidades o modificacion de ya existente para
                    todos los niveles de estructura geopolitica de un pais
Fecha Creacion    : 03/04/2008
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE ZON_PR_VALID_DATOS_GEOGR (psCodigoPais VARCHAR2)
IS
  CURSOR cursorEstruGeoNivel1(nivel number) IS
    SELECT DISTINCT cod_niv1, des_niv1, cod_sube_niv1, decode(1, nivel, nvl(ind_geor, '0'), '0') ind_geor_niv1
    FROM   ZON_TMP_ESTRU_GEOGR
   WHERE   cod_niv1 is not null;

  CURSOR cursorEstruGeoNivel2(nivel number) IS
    SELECT DISTINCT cod_niv1, cod_niv2, des_niv2, cod_sube_niv2, decode(2, nivel, nvl(ind_geor, '0'), '0') ind_geor_niv2
    FROM   ZON_TMP_ESTRU_GEOGR
    WHERE  cod_niv2 is not null;

  CURSOR cursorEstruGeoNivel3(nivel number) IS
    SELECT DISTINCT cod_niv1, cod_niv2, cod_niv3, des_niv3, cod_sube_niv3, decode(3, nivel, nvl(ind_geor, '0'), '0') ind_geor_niv3
    FROM   ZON_TMP_ESTRU_GEOGR
    WHERE  cod_niv3 is not null;

  CURSOR cursorEstruGeoNivel4(nivel number) IS
    SELECT DISTINCT cod_niv1, cod_niv2, cod_niv3, cod_niv4, des_niv4, cod_sube_niv4, decode(4, nivel, nvl(ind_geor, '0'), '0') ind_geor_niv4
    FROM   ZON_TMP_ESTRU_GEOGR
    WHERE  cod_niv4 is not null;

  CURSOR cursorEstruGeoNivel5(nivel number) IS
    SELECT DISTINCT cod_niv1, cod_niv2, cod_niv3, cod_niv4, cod_niv5,
           des_niv5, cod_sube_niv5, decode(5, nivel, nvl(ind_geor, '0'), '0') ind_geor_niv5
    FROM   ZON_TMP_ESTRU_GEOGR
    WHERE  cod_niv5 is not null;

  CURSOR cursorEstruGeoNivel6(nivel number) IS
    SELECT DISTINCT cod_niv1, cod_niv2, cod_niv3, cod_niv4, cod_niv5,
           cod_niv6, des_niv6, cod_sube_niv6, decode(6, nivel, nvl(ind_geor, '0'), '0') ind_geor_niv6
    FROM   ZON_TMP_ESTRU_GEOGR
    WHERE  cod_niv6 is not null;

  CURSOR cursorEstruGeoNivel7(nivel number) IS
    SELECT DISTINCT cod_niv1, cod_niv2, cod_niv3, cod_niv4, cod_niv5,
           cod_niv6, cod_niv7, des_niv7, cod_sube_niv7, decode(7, nivel, nvl(ind_geor, '0'), '0') ind_geor_niv7
    FROM   ZON_TMP_ESTRU_GEOGR
    WHERE  cod_niv7 is not null;

  CURSOR cursorEstruGeoNivel8(nivel number) IS
    SELECT DISTINCT cod_niv1, cod_niv2, cod_niv3, cod_niv4, cod_niv5,
           cod_niv6, cod_niv7, cod_niv8, des_niv8, cod_sube_niv8, decode(8, nivel, nvl(ind_geor, '0'), '0') ind_geor_niv8
    FROM   ZON_TMP_ESTRU_GEOGR
    WHERE  cod_niv8 is not null;

  CURSOR cursorEstruGeoNivel9(nivel number) IS
    SELECT DISTINCT cod_niv1, cod_niv2, cod_niv3, cod_niv4, cod_niv5,
           cod_niv6, cod_niv7, cod_niv8, cod_niv9, des_niv9, cod_sube_niv9, decode(9, nivel, nvl(ind_geor, '0'), '0') ind_geor_niv9
    FROM   ZON_TMP_ESTRU_GEOGR
    WHERE  cod_niv9 is not null;

  TYPE estruGeopoNivel1 IS RECORD
  (
    cod_niv1                    ZON_TMP_ESTRU_GEOGR.COD_NIV1%TYPE,
    des_niv1                    ZON_TMP_ESTRU_GEOGR.DES_NIV1%TYPE,
    cod_sube_niv1               ZON_TMP_ESTRU_GEOGR.COD_SUBE_NIV1%TYPE,
    ind_geor_niv1               ZON_TMP_ESTRU_GEOGR.IND_GEOR%TYPE
   );
  TYPE estruGeopoNivelTab1         IS TABLE OF estruGeopoNivel1;
  tablaEstruGeopo1                 estruGeopoNivelTab1;
  registroEstruGeopo1              estruGeopoNivel1;

  TYPE estruGeopoNivel2 IS RECORD
  (
    cod_niv1                    ZON_TMP_ESTRU_GEOGR.COD_NIV1%TYPE,
    cod_niv2                    ZON_TMP_ESTRU_GEOGR.COD_NIV2%TYPE,
    des_niv2                    ZON_TMP_ESTRU_GEOGR.DES_NIV2%TYPE,
    cod_sube_niv2               ZON_TMP_ESTRU_GEOGR.COD_SUBE_NIV2%TYPE,
    ind_geor_niv2               ZON_TMP_ESTRU_GEOGR.IND_GEOR%TYPE
  );
  TYPE estruGeopoNivelTab2         IS TABLE OF estruGeopoNivel2;
  tablaEstruGeopo2                 estruGeopoNivelTab2;
  registroEstruGeopo2              estruGeopoNivel2;

  TYPE estruGeopoNivel3 IS RECORD
  (
    cod_niv1                    ZON_TMP_ESTRU_GEOGR.COD_NIV1%TYPE,
    cod_niv2                    ZON_TMP_ESTRU_GEOGR.COD_NIV2%TYPE,
    cod_niv3                    ZON_TMP_ESTRU_GEOGR.COD_NIV3%TYPE,
    des_niv3                    ZON_TMP_ESTRU_GEOGR.DES_NIV3%TYPE,
    cod_sube_niv3               ZON_TMP_ESTRU_GEOGR.COD_SUBE_NIV3%TYPE,
    ind_geor_niv3               ZON_TMP_ESTRU_GEOGR.IND_GEOR%TYPE
  );
  TYPE estruGeopoNivelTab3         IS TABLE OF estruGeopoNivel3;
  tablaEstruGeopo3                 estruGeopoNivelTab3;
  registroEstruGeopo3              estruGeopoNivel3;

  TYPE estruGeopoNivel4 IS RECORD
  (
    cod_niv1                    ZON_TMP_ESTRU_GEOGR.COD_NIV1%TYPE,
    cod_niv2                    ZON_TMP_ESTRU_GEOGR.COD_NIV2%TYPE,
    cod_niv3                    ZON_TMP_ESTRU_GEOGR.COD_NIV3%TYPE,
    cod_niv4                    ZON_TMP_ESTRU_GEOGR.COD_NIV4%TYPE,
    des_niv4                    ZON_TMP_ESTRU_GEOGR.DES_NIV4%TYPE,
    cod_sube_niv4               ZON_TMP_ESTRU_GEOGR.COD_SUBE_NIV4%TYPE,
    ind_geor_niv4               ZON_TMP_ESTRU_GEOGR.IND_GEOR%TYPE
  );
  TYPE estruGeopoNivelTab4         IS TABLE OF estruGeopoNivel4;
  tablaEstruGeopo4                 estruGeopoNivelTab4;
  registroEstruGeopo4              estruGeopoNivel4;

  TYPE estruGeopoNivel5 IS RECORD
  (
    cod_niv1                    ZON_TMP_ESTRU_GEOGR.COD_NIV1%TYPE,
    cod_niv2                    ZON_TMP_ESTRU_GEOGR.COD_NIV2%TYPE,
    cod_niv3                    ZON_TMP_ESTRU_GEOGR.COD_NIV3%TYPE,
    cod_niv4                    ZON_TMP_ESTRU_GEOGR.COD_NIV4%TYPE,
    cod_niv5                    ZON_TMP_ESTRU_GEOGR.COD_NIV5%TYPE,
    des_niv5                    ZON_TMP_ESTRU_GEOGR.DES_NIV5%TYPE,
    cod_sube_niv5               ZON_TMP_ESTRU_GEOGR.COD_SUBE_NIV5%TYPE,
    ind_geor_niv5               ZON_TMP_ESTRU_GEOGR.IND_GEOR%TYPE
  );
  TYPE estruGeopoNivelTab5         IS TABLE OF estruGeopoNivel5;
  tablaEstruGeopo5                 estruGeopoNivelTab5;
  registroEstruGeopo5              estruGeopoNivel5;

  TYPE estruGeopoNivel6 IS RECORD
  (
    cod_niv1                    ZON_TMP_ESTRU_GEOGR.COD_NIV1%TYPE,
    cod_niv2                    ZON_TMP_ESTRU_GEOGR.COD_NIV2%TYPE,
    cod_niv3                    ZON_TMP_ESTRU_GEOGR.COD_NIV3%TYPE,
    cod_niv4                    ZON_TMP_ESTRU_GEOGR.COD_NIV4%TYPE,
    cod_niv5                    ZON_TMP_ESTRU_GEOGR.COD_NIV5%TYPE,
    cod_niv6                    ZON_TMP_ESTRU_GEOGR.COD_NIV6%TYPE,
    des_niv6                    ZON_TMP_ESTRU_GEOGR.DES_NIV6%TYPE,
    cod_sube_niv6               ZON_TMP_ESTRU_GEOGR.COD_SUBE_NIV6%TYPE,
    ind_geor_niv6               ZON_TMP_ESTRU_GEOGR.IND_GEOR%TYPE
  );
  TYPE estruGeopoNivelTab6         IS TABLE OF estruGeopoNivel6;
  tablaEstruGeopo6                 estruGeopoNivelTab6;
  registroEstruGeopo6              estruGeopoNivel6;

  TYPE estruGeopoNivel7 IS RECORD
  (
    cod_niv1                    ZON_TMP_ESTRU_GEOGR.COD_NIV1%TYPE,
    cod_niv2                    ZON_TMP_ESTRU_GEOGR.COD_NIV2%TYPE,
    cod_niv3                    ZON_TMP_ESTRU_GEOGR.COD_NIV3%TYPE,
    cod_niv4                    ZON_TMP_ESTRU_GEOGR.COD_NIV4%TYPE,
    cod_niv5                    ZON_TMP_ESTRU_GEOGR.COD_NIV5%TYPE,
    cod_niv6                    ZON_TMP_ESTRU_GEOGR.COD_NIV6%TYPE,
    cod_niv7                    ZON_TMP_ESTRU_GEOGR.COD_NIV7%TYPE,
    des_niv7                    ZON_TMP_ESTRU_GEOGR.DES_NIV7%TYPE,
    cod_sube_niv7               ZON_TMP_ESTRU_GEOGR.COD_SUBE_NIV7%TYPE,
    ind_geor_niv7               ZON_TMP_ESTRU_GEOGR.IND_GEOR%TYPE
  );
  TYPE estruGeopoNivelTab7         IS TABLE OF estruGeopoNivel7;
  tablaEstruGeopo7                 estruGeopoNivelTab7;
  registroEstruGeopo7              estruGeopoNivel7;

  TYPE estruGeopoNivel8 IS RECORD
  (
    cod_niv1                    ZON_TMP_ESTRU_GEOGR.COD_NIV1%TYPE,
    cod_niv2                    ZON_TMP_ESTRU_GEOGR.COD_NIV2%TYPE,
    cod_niv3                    ZON_TMP_ESTRU_GEOGR.COD_NIV3%TYPE,
    cod_niv4                    ZON_TMP_ESTRU_GEOGR.COD_NIV4%TYPE,
    cod_niv5                    ZON_TMP_ESTRU_GEOGR.COD_NIV5%TYPE,
    cod_niv6                    ZON_TMP_ESTRU_GEOGR.COD_NIV6%TYPE,
    cod_niv7                    ZON_TMP_ESTRU_GEOGR.COD_NIV7%TYPE,
    cod_niv8                    ZON_TMP_ESTRU_GEOGR.COD_NIV8%TYPE,
    des_niv8                    ZON_TMP_ESTRU_GEOGR.DES_NIV8%TYPE,
    cod_sube_niv8               ZON_TMP_ESTRU_GEOGR.COD_SUBE_NIV8%TYPE,
    ind_geor_niv8               ZON_TMP_ESTRU_GEOGR.IND_GEOR%TYPE
  );
  TYPE estruGeopoNivelTab8         IS TABLE OF estruGeopoNivel8;
  tablaEstruGeopo8                 estruGeopoNivelTab8;
  registroEstruGeopo8              estruGeopoNivel8;

  TYPE estruGeopoNivel9 IS RECORD
  (
    cod_niv1                    ZON_TMP_ESTRU_GEOGR.COD_NIV1%TYPE,
    cod_niv2                    ZON_TMP_ESTRU_GEOGR.COD_NIV2%TYPE,
    cod_niv3                    ZON_TMP_ESTRU_GEOGR.COD_NIV3%TYPE,
    cod_niv4                    ZON_TMP_ESTRU_GEOGR.COD_NIV4%TYPE,
    cod_niv5                    ZON_TMP_ESTRU_GEOGR.COD_NIV5%TYPE,
    cod_niv6                    ZON_TMP_ESTRU_GEOGR.COD_NIV6%TYPE,
    cod_niv7                    ZON_TMP_ESTRU_GEOGR.COD_NIV7%TYPE,
    cod_niv8                    ZON_TMP_ESTRU_GEOGR.COD_NIV8%TYPE,
    cod_niv9                    ZON_TMP_ESTRU_GEOGR.COD_NIV9%TYPE,
    des_niv9                    ZON_TMP_ESTRU_GEOGR.DES_NIV9%TYPE,
    cod_sube_niv9               ZON_TMP_ESTRU_GEOGR.COD_SUBE_NIV9%TYPE,
    ind_geor_niv9               ZON_TMP_ESTRU_GEOGR.IND_GEOR%TYPE
  );
  TYPE estruGeopoNivelTab9         IS TABLE OF estruGeopoNivel9;
  tablaEstruGeopo9                 estruGeopoNivelTab9;
  registroEstruGeopo9              estruGeopoNivel9;


  lnTotalOcurrencias               NUMBER;
  lsDesGeog                        ZON_VALOR_ESTRU_GEOPO.DES_GEOG%TYPE;
  lsCodSube                        ZON_SUBES_GEOPO.COD_SUBE%TYPE;
  lnFila                           NUMBER;

  lsOidPais                        SEG_PAIS.OID_PAIS%TYPE;
  lsOidEstruGeopo                  ZON_ESTRU_GEOPO.OID_ESTR_GEOP%TYPE;
  lsOidSubesGeopo                  ZON_SUBES_GEOPO.OID_SUBE_GEOP%TYPE;
  lnNivelIndicadorGeoRef           NUMBER;

BEGIN
  COMMIT; --Para guardar los datos cargados del archivo en BD

  --Limpiamos la tabla de Validaciones Geograficas, que no tengan usuario de aprobacion
  DELETE FROM ZON_HISTO_VALID_GEOGR
  WHERE USU_APRO IS NULL
    AND FEC_PROC IS NULL;

   --Recuperamos el oid Pais
  lsOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnFila := 0;

  --Determinamos en que nivel va ir el Indicador de georeferencia, ULTIMO NIVEK
  select max(cod_orde) into lnNivelIndicadorGeoRef from zon_estru_geopo;
  -- --

  --VALIDAMOS LOS CODIGOS DEL PRIMER NIVEL (1)
  OPEN cursorEstruGeoNivel1(lnNivelIndicadorGeoRef);
  LOOP
    FETCH cursorEstruGeoNivel1 BULK COLLECT INTO tablaEstruGeopo1 LIMIT W_FILAS;
    IF tablaEstruGeopo1.COUNT > 0 THEN

      FOR x IN tablaEstruGeopo1.FIRST .. tablaEstruGeopo1.LAST LOOP
        registroEstruGeopo1 := tablaEstruGeopo1(x);

        SELECT count(orde_1)
        INTO   lnTotalOcurrencias
        FROM   ZON_VALOR_ESTRU_GEOPO a
        WHERE  a.orde_1 = registroEstruGeopo1.cod_niv1;

        IF (lnTotalOcurrencias = 0) THEN
          --Nueva Valor de Estructura Geopolitica (Nivel1)
          lsOidEstruGeopo:= ZON_FN_DEVUE_OID_ESTRU_GEOPO(lsOidPais, 1);
          lsOidSubesGeopo:= ZON_FN_DEVUE_OID_SUBES_GEOPO(lsOidEstruGeopo, registroEstruGeopo1.cod_sube_niv1);
          lnFila := lnFila + 1;

          INSERT INTO ZON_HISTO_VALID_GEOGR(COD_NIV1, OID_ESTR, OID_SUBE_ESTR,
                 DES_GEOG, COD_SUBE, COD_OPER, VAL_FILA, IND_GEOR)
          VALUES (registroEstruGeopo1.cod_niv1, lsOidEstruGeopo, lsOidSubesGeopo,
                  registroEstruGeopo1.des_niv1, registroEstruGeopo1.cod_sube_niv1, 'A', lnFila, registroEstruGeopo1.ind_geor_niv1);

        ELSE
          --Posible Modificacion de Valor de Estructura Geopolitica (Nivel1)
          SELECT des_geog, sub.cod_sube
          INTO   lsDesGeog, lsCodSube
          FROM   zon_valor_estru_geopo val, zon_subes_geopo sub
          WHERE  val.orde_1 = registroEstruGeopo1.cod_niv1
            AND  val.orde_2 IS NULL
            AND  val.sgeo_oid_sube_geop = sub.oid_sube_geop;

          --Valida si ha cambiado la descripcion o codig de SubEstructura Geopolitica
          IF((registroEstruGeopo1.des_niv1 <> lsDesGeog ) OR (registroEstruGeopo1.cod_sube_niv1 <> lsCodSube )) THEN
            lsOidEstruGeopo:= ZON_FN_DEVUE_OID_ESTRU_GEOPO(lsOidPais, 1);
            lsOidSubesGeopo:= ZON_FN_DEVUE_OID_SUBES_GEOPO(lsOidEstruGeopo, registroEstruGeopo1.cod_sube_niv1);
            lnFila := lnFila + 1;

            INSERT INTO ZON_HISTO_VALID_GEOGR(COD_NIV1, OID_ESTR, OID_SUBE_ESTR,
                   DES_GEOG, COD_SUBE, COD_OPER, VAL_FILA, IND_GEOR)
            VALUES (registroEstruGeopo1.cod_niv1, lsOidEstruGeopo, lsOidSubesGeopo,
                    registroEstruGeopo1.des_niv1, registroEstruGeopo1.cod_sube_niv1, 'M', lnFila, registroEstruGeopo1.ind_geor_niv1);
          END IF;
        END IF;

      END LOOP;
    END IF;

    EXIT WHEN cursorEstruGeoNivel1%NOTFOUND;
  END LOOP;
  CLOSE cursorEstruGeoNivel1;


  --VALIDAMOS LOS CODIGOS DEL SEGUNDO NIVEL (2)
  OPEN cursorEstruGeoNivel2(lnNivelIndicadorGeoRef);
  LOOP
    FETCH cursorEstruGeoNivel2 BULK COLLECT INTO tablaEstruGeopo2 LIMIT W_FILAS;
    IF tablaEstruGeopo2.COUNT > 0 THEN

      FOR x IN tablaEstruGeopo2.FIRST .. tablaEstruGeopo2.LAST LOOP
        registroEstruGeopo2 := tablaEstruGeopo2(x);

        SELECT count(orde_1)
        INTO   lnTotalOcurrencias
        FROM   ZON_VALOR_ESTRU_GEOPO a
        WHERE  a.orde_1 = registroEstruGeopo2.cod_niv1
          AND  a.orde_2 = registroEstruGeopo2.cod_niv2;

        IF (lnTotalOcurrencias = 0) THEN
          --Nueva Valor de Estructura Geopolitica (Nivel1)
          lsOidEstruGeopo:= ZON_FN_DEVUE_OID_ESTRU_GEOPO(lsOidPais, 2);
          lsOidSubesGeopo:= ZON_FN_DEVUE_OID_SUBES_GEOPO(lsOidEstruGeopo, registroEstruGeopo2.cod_sube_niv2);
          lnFila := lnFila + 1;

          INSERT INTO ZON_HISTO_VALID_GEOGR(COD_NIV1, COD_NIV2, OID_ESTR, OID_SUBE_ESTR,
                 DES_GEOG, COD_SUBE, COD_OPER, VAL_FILA, IND_GEOR)
          VALUES (registroEstruGeopo2.cod_niv1, registroEstruGeopo2.cod_niv2, lsOidEstruGeopo, lsOidSubesGeopo,
                  registroEstruGeopo2.des_niv2, registroEstruGeopo2.cod_sube_niv2, 'A', lnFila, registroEstruGeopo2.ind_geor_niv2);

        ELSE
          --Posible Modificacion de Valor de Estructura Geopolitica (Nivel2)
          SELECT des_geog, sub.cod_sube
          INTO   lsDesGeog, lsCodSube
          FROM   zon_valor_estru_geopo val, zon_subes_geopo sub
          WHERE  val.orde_1 = registroEstruGeopo2.cod_niv1
            AND  val.orde_2 = registroEstruGeopo2.cod_niv2
            AND  val.orde_3 IS NULL
            AND  val.sgeo_oid_sube_geop = sub.oid_sube_geop;

          --Valida si ha cambiado la descripcion o codigo de SubEstructura Geopolitica
          IF((registroEstruGeopo2.des_niv2 <> lsDesGeog ) OR (registroEstruGeopo2.cod_sube_niv2 <> lsCodSube )) THEN
            lsOidEstruGeopo:= ZON_FN_DEVUE_OID_ESTRU_GEOPO(lsOidPais, 2);
            lsOidSubesGeopo:= ZON_FN_DEVUE_OID_SUBES_GEOPO(lsOidEstruGeopo, registroEstruGeopo2.cod_sube_niv2);
            lnFila := lnFila + 1;

            INSERT INTO ZON_HISTO_VALID_GEOGR(COD_NIV1, COD_NIV2, OID_ESTR, OID_SUBE_ESTR,
                   DES_GEOG, COD_SUBE, COD_OPER, VAL_FILA, IND_GEOR)
            VALUES (registroEstruGeopo2.cod_niv1, registroEstruGeopo2.cod_niv2, lsOidEstruGeopo, lsOidSubesGeopo,
                    registroEstruGeopo2.des_niv2, registroEstruGeopo2.cod_sube_niv2, 'M', lnFila, registroEstruGeopo2.ind_geor_niv2);
          END IF;
        END IF;

      END LOOP;
    END IF;

    EXIT WHEN cursorEstruGeoNivel2%NOTFOUND;
  END LOOP;
  CLOSE cursorEstruGeoNivel2;


  --VALIDAMOS LOS CODIGOS DEL TERCER NIVEL (3)
  OPEN cursorEstruGeoNivel3(lnNivelIndicadorGeoRef);
  LOOP
    FETCH cursorEstruGeoNivel3 BULK COLLECT INTO tablaEstruGeopo3 LIMIT W_FILAS;
    IF tablaEstruGeopo3.COUNT > 0 THEN

      FOR x IN tablaEstruGeopo3.FIRST .. tablaEstruGeopo3.LAST LOOP
        registroEstruGeopo3 := tablaEstruGeopo3(x);

        SELECT count(orde_1)
        INTO   lnTotalOcurrencias
        FROM   ZON_VALOR_ESTRU_GEOPO a
        WHERE  a.orde_1 = registroEstruGeopo3.cod_niv1
          AND  a.orde_2 = registroEstruGeopo3.cod_niv2
          AND  a.orde_3 = registroEstruGeopo3.cod_niv3;

        IF (lnTotalOcurrencias = 0) THEN
          --Nueva Valor de Estructura Geopolitica (Nivel1)
          lsOidEstruGeopo:= ZON_FN_DEVUE_OID_ESTRU_GEOPO(lsOidPais, 3);
          lsOidSubesGeopo:= ZON_FN_DEVUE_OID_SUBES_GEOPO(lsOidEstruGeopo, registroEstruGeopo3.cod_sube_niv3);
          lnFila := lnFila + 1;

          INSERT INTO ZON_HISTO_VALID_GEOGR(COD_NIV1, COD_NIV2, COD_NIV3, OID_ESTR, OID_SUBE_ESTR,
                 DES_GEOG, COD_SUBE, COD_OPER, VAL_FILA, IND_GEOR)
          VALUES (registroEstruGeopo3.cod_niv1, registroEstruGeopo3.cod_niv2, registroEstruGeopo3.cod_niv3,
        lsOidEstruGeopo, lsOidSubesGeopo,
                  registroEstruGeopo3.des_niv3, registroEstruGeopo3.cod_sube_niv3, 'A', lnFila, registroEstruGeopo3.ind_geor_niv3);

        ELSE
          --Posible Modificacion de Valor de Estructura Geopolitica (Nivel3)
          SELECT des_geog, sub.cod_sube
          INTO   lsDesGeog, lsCodSube
          FROM   zon_valor_estru_geopo val, zon_subes_geopo sub
          WHERE  val.orde_1 = registroEstruGeopo3.cod_niv1
            AND  val.orde_2 = registroEstruGeopo3.cod_niv2
            AND  val.orde_3 = registroEstruGeopo3.cod_niv3
            AND  val.orde_4 IS NULL
            AND  val.sgeo_oid_sube_geop = sub.oid_sube_geop;

          --Valida si ha cambiado la descripcion o codigo de SubEstructura Geopolitica
          IF((registroEstruGeopo3.des_niv3 <> lsDesGeog ) OR (registroEstruGeopo3.cod_sube_niv3 <> lsCodSube )) THEN
            lsOidEstruGeopo:= ZON_FN_DEVUE_OID_ESTRU_GEOPO(lsOidPais, 3);
            lsOidSubesGeopo:= ZON_FN_DEVUE_OID_SUBES_GEOPO(lsOidEstruGeopo, registroEstruGeopo3.cod_sube_niv3);
            lnFila := lnFila + 1;

            INSERT INTO ZON_HISTO_VALID_GEOGR(COD_NIV1, COD_NIV2, COD_NIV3, OID_ESTR, OID_SUBE_ESTR,
                   DES_GEOG, COD_SUBE, COD_OPER, VAL_FILA, IND_GEOR)
            VALUES (registroEstruGeopo3.cod_niv1, registroEstruGeopo3.cod_niv2, registroEstruGeopo3.cod_niv3,
           lsOidEstruGeopo, lsOidSubesGeopo,
                    registroEstruGeopo3.des_niv3, registroEstruGeopo3.cod_sube_niv3, 'M', lnFila, registroEstruGeopo3.ind_geor_niv3);
          END IF;
        END IF;

      END LOOP;
    END IF;

    EXIT WHEN cursorEstruGeoNivel3%NOTFOUND;
  END LOOP;
  CLOSE cursorEstruGeoNivel3;


  --VALIDAMOS LOS CODIGOS DEL CUARTO NIVEL (4)
  OPEN cursorEstruGeoNivel4(lnNivelIndicadorGeoRef);
  LOOP
    FETCH cursorEstruGeoNivel4 BULK COLLECT INTO tablaEstruGeopo4 LIMIT W_FILAS;
    IF tablaEstruGeopo4.COUNT > 0 THEN

      FOR x IN tablaEstruGeopo4.FIRST .. tablaEstruGeopo4.LAST LOOP
        registroEstruGeopo4 := tablaEstruGeopo4(x);

        SELECT count(orde_1)
        INTO   lnTotalOcurrencias
        FROM   ZON_VALOR_ESTRU_GEOPO a
        WHERE  a.orde_1 = registroEstruGeopo4.cod_niv1
          AND  a.orde_2 = registroEstruGeopo4.cod_niv2
          AND  a.orde_3 = registroEstruGeopo4.cod_niv3
          AND  a.orde_4 = registroEstruGeopo4.cod_niv4;

        IF (lnTotalOcurrencias = 0) THEN
          --Nueva Valor de Estructura Geopolitica (Nivel1)
          lsOidEstruGeopo:= ZON_FN_DEVUE_OID_ESTRU_GEOPO(lsOidPais, 4);
          lsOidSubesGeopo:= ZON_FN_DEVUE_OID_SUBES_GEOPO(lsOidEstruGeopo, registroEstruGeopo4.cod_sube_niv4);
          lnFila := lnFila + 1;

          INSERT INTO ZON_HISTO_VALID_GEOGR(COD_NIV1, COD_NIV2, COD_NIV3, COD_NIV4, OID_ESTR, OID_SUBE_ESTR,
                 DES_GEOG, COD_SUBE, COD_OPER, VAL_FILA, IND_GEOR)
          VALUES (registroEstruGeopo4.cod_niv1, registroEstruGeopo4.cod_niv2, registroEstruGeopo4.cod_niv3,
                  registroEstruGeopo4.cod_niv4, lsOidEstruGeopo, lsOidSubesGeopo,
                  registroEstruGeopo4.des_niv4, registroEstruGeopo4.cod_sube_niv4, 'A', lnFila, registroEstruGeopo4.ind_geor_niv4);

        ELSE
          --Posible Modificacion de Valor de Estructura Geopolitica (Nivel4)
          SELECT des_geog, sub.cod_sube
          INTO   lsDesGeog, lsCodSube
          FROM   zon_valor_estru_geopo val, zon_subes_geopo sub
          WHERE  val.orde_1 = registroEstruGeopo4.cod_niv1
            AND  val.orde_2 = registroEstruGeopo4.cod_niv2
            AND  val.orde_3 = registroEstruGeopo4.cod_niv3
            AND  val.orde_4 = registroEstruGeopo4.cod_niv4
            AND  val.orde_5 IS NULL
            AND  val.sgeo_oid_sube_geop = sub.oid_sube_geop;

          --Valida si ha cambiado la descripcion o codigo de SubEstructura Geopolitica
          IF((registroEstruGeopo4.des_niv4 <> lsDesGeog ) OR (registroEstruGeopo4.cod_sube_niv4 <> lsCodSube )) THEN
            lsOidEstruGeopo:= ZON_FN_DEVUE_OID_ESTRU_GEOPO(lsOidPais, 4);
            lsOidSubesGeopo:= ZON_FN_DEVUE_OID_SUBES_GEOPO(lsOidEstruGeopo, registroEstruGeopo4.cod_sube_niv4);
            lnFila := lnFila + 1;

            INSERT INTO ZON_HISTO_VALID_GEOGR(COD_NIV1, COD_NIV2, COD_NIV3, COD_NIV4, OID_ESTR, OID_SUBE_ESTR,
                   DES_GEOG, COD_SUBE, COD_OPER, VAL_FILA, IND_GEOR)
            VALUES (registroEstruGeopo4.cod_niv1, registroEstruGeopo4.cod_niv2, registroEstruGeopo4.cod_niv3,
                    registroEstruGeopo4.cod_niv4, lsOidEstruGeopo, lsOidSubesGeopo,
                    registroEstruGeopo4.des_niv4, registroEstruGeopo4.cod_sube_niv4, 'M', lnFila, registroEstruGeopo4.ind_geor_niv4);
          END IF;
        END IF;

      END LOOP;
    END IF;

    EXIT WHEN cursorEstruGeoNivel4%NOTFOUND;
  END LOOP;
  CLOSE cursorEstruGeoNivel4;


  --VALIDAMOS LOS CODIGOS DEL QUINTO NIVEL (5)
  OPEN cursorEstruGeoNivel5(lnNivelIndicadorGeoRef);
  LOOP
    FETCH cursorEstruGeoNivel5 BULK COLLECT INTO tablaEstruGeopo5 LIMIT W_FILAS;
    IF tablaEstruGeopo5.COUNT > 0 THEN

      FOR x IN tablaEstruGeopo5.FIRST .. tablaEstruGeopo5.LAST LOOP
        registroEstruGeopo5 := tablaEstruGeopo5(x);

        SELECT count(orde_1)
        INTO   lnTotalOcurrencias
        FROM   ZON_VALOR_ESTRU_GEOPO a
        WHERE  a.orde_1 = registroEstruGeopo5.cod_niv1
          AND  a.orde_2 = registroEstruGeopo5.cod_niv2
          AND  a.orde_3 = registroEstruGeopo5.cod_niv3
          AND  a.orde_4 = registroEstruGeopo5.cod_niv4
          AND  a.orde_5 = registroEstruGeopo5.cod_niv5;

        IF (lnTotalOcurrencias = 0) THEN
          --Nueva Valor de Estructura Geopolitica (Nivel1)
          lsOidEstruGeopo:= ZON_FN_DEVUE_OID_ESTRU_GEOPO(lsOidPais, 5);
          lsOidSubesGeopo:= ZON_FN_DEVUE_OID_SUBES_GEOPO(lsOidEstruGeopo, registroEstruGeopo5.cod_sube_niv5);
          lnFila := lnFila + 1;

          INSERT INTO ZON_HISTO_VALID_GEOGR(COD_NIV1, COD_NIV2, COD_NIV3, COD_NIV4, COD_NIV5, OID_ESTR, OID_SUBE_ESTR,
                 DES_GEOG, COD_SUBE, COD_OPER, VAL_FILA, IND_GEOR)
          VALUES (registroEstruGeopo5.cod_niv1, registroEstruGeopo5.cod_niv2, registroEstruGeopo5.cod_niv3,
                  registroEstruGeopo5.cod_niv4, registroEstruGeopo5.cod_niv5, lsOidEstruGeopo, lsOidSubesGeopo,
                  registroEstruGeopo5.des_niv5, registroEstruGeopo5.cod_sube_niv5, 'A', lnFila, registroEstruGeopo5.ind_geor_niv5);

        ELSE
          --Posible Modificacion de Valor de Estructura Geopolitica (Nivel5)
          SELECT des_geog, sub.cod_sube
          INTO   lsDesGeog, lsCodSube
          FROM   zon_valor_estru_geopo val, zon_subes_geopo sub
          WHERE  val.orde_1 = registroEstruGeopo5.cod_niv1
            AND  val.orde_2 = registroEstruGeopo5.cod_niv2
            AND  val.orde_3 = registroEstruGeopo5.cod_niv3
            AND  val.orde_4 = registroEstruGeopo5.cod_niv4
            AND  val.orde_5 = registroEstruGeopo5.cod_niv5
            AND  val.orde_6 IS NULL
            AND  val.sgeo_oid_sube_geop = sub.oid_sube_geop;

          --Valida si ha cambiado la descripcion o codigo de SubEstructura Geopolitica
          IF((registroEstruGeopo5.des_niv5 <> lsDesGeog ) OR (registroEstruGeopo5.cod_sube_niv5 <> lsCodSube )) THEN
            lsOidEstruGeopo:= ZON_FN_DEVUE_OID_ESTRU_GEOPO(lsOidPais, 5);
            lsOidSubesGeopo:= ZON_FN_DEVUE_OID_SUBES_GEOPO(lsOidEstruGeopo, registroEstruGeopo5.cod_sube_niv5);
            lnFila := lnFila + 1;

            INSERT INTO ZON_HISTO_VALID_GEOGR(COD_NIV1, COD_NIV2, COD_NIV3, COD_NIV4, COD_NIV5, OID_ESTR, OID_SUBE_ESTR,
                   DES_GEOG, COD_SUBE, COD_OPER, VAL_FILA, IND_GEOR)
            VALUES (registroEstruGeopo5.cod_niv1, registroEstruGeopo5.cod_niv2, registroEstruGeopo5.cod_niv3,
                    registroEstruGeopo5.cod_niv4, registroEstruGeopo5.cod_niv5, lsOidEstruGeopo, lsOidSubesGeopo,
                    registroEstruGeopo5.des_niv5, registroEstruGeopo5.cod_sube_niv5, 'M', lnFila, registroEstruGeopo5.ind_geor_niv5);
          END IF;
        END IF;

      END LOOP;
    END IF;

    EXIT WHEN cursorEstruGeoNivel5%NOTFOUND;
  END LOOP;
  CLOSE cursorEstruGeoNivel5;


  --VALIDAMOS LOS CODIGOS DEL SEXTO NIVEL (6)
  OPEN cursorEstruGeoNivel6(lnNivelIndicadorGeoRef);
  LOOP
    FETCH cursorEstruGeoNivel6 BULK COLLECT INTO tablaEstruGeopo6 LIMIT W_FILAS;
    IF tablaEstruGeopo6.COUNT > 0 THEN

      FOR x IN tablaEstruGeopo6.FIRST .. tablaEstruGeopo6.LAST LOOP
        registroEstruGeopo6 := tablaEstruGeopo6(x);

        SELECT count(orde_1)
        INTO   lnTotalOcurrencias
        FROM   ZON_VALOR_ESTRU_GEOPO a
        WHERE  a.orde_1 = registroEstruGeopo6.cod_niv1
          AND  a.orde_2 = registroEstruGeopo6.cod_niv2
          AND  a.orde_3 = registroEstruGeopo6.cod_niv3
          AND  a.orde_4 = registroEstruGeopo6.cod_niv4
          AND  a.orde_5 = registroEstruGeopo6.cod_niv5
          AND  a.orde_6 = registroEstruGeopo6.cod_niv6;

        IF (lnTotalOcurrencias = 0) THEN
          --Nueva Valor de Estructura Geopolitica (Nivel1)
          lsOidEstruGeopo:= ZON_FN_DEVUE_OID_ESTRU_GEOPO(lsOidPais, 6);
          lsOidSubesGeopo:= ZON_FN_DEVUE_OID_SUBES_GEOPO(lsOidEstruGeopo, registroEstruGeopo6.cod_sube_niv6);
          lnFila := lnFila + 1;

          INSERT INTO ZON_HISTO_VALID_GEOGR(COD_NIV1, COD_NIV2, COD_NIV3, COD_NIV4, COD_NIV5, COD_NIV6,
           OID_ESTR, OID_SUBE_ESTR, DES_GEOG, COD_SUBE, COD_OPER, VAL_FILA, IND_GEOR)
          VALUES (registroEstruGeopo6.cod_niv1, registroEstruGeopo6.cod_niv2, registroEstruGeopo6.cod_niv3,
                  registroEstruGeopo6.cod_niv4, registroEstruGeopo6.cod_niv5, registroEstruGeopo6.cod_niv6, lsOidEstruGeopo, lsOidSubesGeopo,
                  registroEstruGeopo6.des_niv6, registroEstruGeopo6.cod_sube_niv6, 'A', lnFila, registroEstruGeopo6.ind_geor_niv6);

        ELSE
          --Posible Modificacion de Valor de Estructura Geopolitica (Nivel6)
          SELECT des_geog, sub.cod_sube
          INTO   lsDesGeog, lsCodSube
          FROM   zon_valor_estru_geopo val, zon_subes_geopo sub
          WHERE  val.orde_1 = registroEstruGeopo6.cod_niv1
            AND  val.orde_2 = registroEstruGeopo6.cod_niv2
            AND  val.orde_3 = registroEstruGeopo6.cod_niv3
            AND  val.orde_4 = registroEstruGeopo6.cod_niv4
            AND  val.orde_5 = registroEstruGeopo6.cod_niv5
            AND  val.orde_6 = registroEstruGeopo6.cod_niv6
            AND  val.orde_7 IS NULL
            AND  val.sgeo_oid_sube_geop = sub.oid_sube_geop;

          --Valida si ha cambiado la descripcion o codigo de SubEstructura Geopolitica
          IF((registroEstruGeopo6.des_niv6 <> lsDesGeog ) OR (registroEstruGeopo6.cod_sube_niv6 <> lsCodSube )) THEN
            lsOidEstruGeopo:= ZON_FN_DEVUE_OID_ESTRU_GEOPO(lsOidPais, 6);
            lsOidSubesGeopo:= ZON_FN_DEVUE_OID_SUBES_GEOPO(lsOidEstruGeopo, registroEstruGeopo6.cod_sube_niv6);
            lnFila := lnFila + 1;

            INSERT INTO ZON_HISTO_VALID_GEOGR(COD_NIV1, COD_NIV2, COD_NIV3, COD_NIV4, COD_NIV5, COD_NIV6,
                   OID_ESTR, OID_SUBE_ESTR, DES_GEOG, COD_SUBE, COD_OPER, VAL_FILA, IND_GEOR)
            VALUES (registroEstruGeopo6.cod_niv1, registroEstruGeopo6.cod_niv2, registroEstruGeopo6.cod_niv3,
                    registroEstruGeopo6.cod_niv4, registroEstruGeopo6.cod_niv5, registroEstruGeopo6.cod_niv6,lsOidEstruGeopo, lsOidSubesGeopo,
                    registroEstruGeopo6.des_niv6, registroEstruGeopo6.cod_sube_niv6, 'M', lnFila, registroEstruGeopo6.ind_geor_niv6);
          END IF;
        END IF;

      END LOOP;
    END IF;

    EXIT WHEN cursorEstruGeoNivel6%NOTFOUND;
  END LOOP;
  CLOSE cursorEstruGeoNivel6;


  --VALIDAMOS LOS CODIGOS DEL SEPTIMO NIVEL (7)
  OPEN cursorEstruGeoNivel7(lnNivelIndicadorGeoRef);
  LOOP
    FETCH cursorEstruGeoNivel7 BULK COLLECT INTO tablaEstruGeopo7 LIMIT W_FILAS;
    IF tablaEstruGeopo7.COUNT > 0 THEN

      FOR x IN tablaEstruGeopo7.FIRST .. tablaEstruGeopo7.LAST LOOP
        registroEstruGeopo7 := tablaEstruGeopo7(x);

        SELECT count(orde_1)
        INTO   lnTotalOcurrencias
        FROM   ZON_VALOR_ESTRU_GEOPO a
        WHERE  a.orde_1 = registroEstruGeopo7.cod_niv1
          AND  a.orde_2 = registroEstruGeopo7.cod_niv2
          AND  a.orde_3 = registroEstruGeopo7.cod_niv3
          AND  a.orde_4 = registroEstruGeopo7.cod_niv4
          AND  a.orde_5 = registroEstruGeopo7.cod_niv5
          AND  a.orde_6 = registroEstruGeopo7.cod_niv6
          AND  a.orde_7 = registroEstruGeopo7.cod_niv7;

        IF (lnTotalOcurrencias = 0) THEN
          --Nueva Valor de Estructura Geopolitica (Nivel1)
          lsOidEstruGeopo:= ZON_FN_DEVUE_OID_ESTRU_GEOPO(lsOidPais, 7);
          lsOidSubesGeopo:= ZON_FN_DEVUE_OID_SUBES_GEOPO(lsOidEstruGeopo, registroEstruGeopo7.cod_sube_niv7);
          lnFila := lnFila + 1;

          INSERT INTO ZON_HISTO_VALID_GEOGR(COD_NIV1, COD_NIV2, COD_NIV3, COD_NIV4, COD_NIV5, COD_NIV6, COD_NIV7,
           OID_ESTR, OID_SUBE_ESTR, DES_GEOG, COD_SUBE, COD_OPER, VAL_FILA, IND_GEOR)
          VALUES (registroEstruGeopo7.cod_niv1, registroEstruGeopo7.cod_niv2, registroEstruGeopo7.cod_niv3,
                  registroEstruGeopo7.cod_niv4, registroEstruGeopo7.cod_niv5, registroEstruGeopo7.cod_niv6, registroEstruGeopo7.cod_niv7,
      lsOidEstruGeopo, lsOidSubesGeopo, registroEstruGeopo7.des_niv7, registroEstruGeopo7.cod_sube_niv7, 'A', lnFila, registroEstruGeopo7.ind_geor_niv7);

        ELSE
          --Posible Modificacion de Valor de Estructura Geopolitica (Nivel7)
          SELECT des_geog, sub.cod_sube
          INTO   lsDesGeog, lsCodSube
          FROM   zon_valor_estru_geopo val, zon_subes_geopo sub
          WHERE  val.orde_1 = registroEstruGeopo7.cod_niv1
            AND  val.orde_2 = registroEstruGeopo7.cod_niv2
            AND  val.orde_3 = registroEstruGeopo7.cod_niv3
            AND  val.orde_4 = registroEstruGeopo7.cod_niv4
            AND  val.orde_5 = registroEstruGeopo7.cod_niv5
            AND  val.orde_6 = registroEstruGeopo7.cod_niv6
            AND  val.orde_7 = registroEstruGeopo7.cod_niv7
            AND  val.orde_8 IS NULL
            AND  val.sgeo_oid_sube_geop = sub.oid_sube_geop;

          --Valida si ha cambiado la descripcion o codigo de SubEstructura Geopolitica
          IF((registroEstruGeopo7.des_niv7 <> lsDesGeog ) OR (registroEstruGeopo7.cod_sube_niv7 <> lsCodSube )) THEN
            lsOidEstruGeopo:= ZON_FN_DEVUE_OID_ESTRU_GEOPO(lsOidPais, 7);
            lsOidSubesGeopo:= ZON_FN_DEVUE_OID_SUBES_GEOPO(lsOidEstruGeopo, registroEstruGeopo7.cod_sube_niv7);
            lnFila := lnFila + 1;

            INSERT INTO ZON_HISTO_VALID_GEOGR(COD_NIV1, COD_NIV2, COD_NIV3, COD_NIV4, COD_NIV5, COD_NIV6, COD_NIV7,
          OID_ESTR, OID_SUBE_ESTR, DES_GEOG, COD_SUBE, COD_OPER, VAL_FILA, IND_GEOR)
            VALUES (registroEstruGeopo7.cod_niv1, registroEstruGeopo7.cod_niv2, registroEstruGeopo7.cod_niv3,
                    registroEstruGeopo7.cod_niv4, registroEstruGeopo7.cod_niv5, registroEstruGeopo7.cod_niv6, registroEstruGeopo7.cod_niv7,
               lsOidEstruGeopo, lsOidSubesGeopo,
                    registroEstruGeopo7.des_niv7, registroEstruGeopo7.cod_sube_niv7, 'M', lnFila, registroEstruGeopo7.ind_geor_niv7);
          END IF;
        END IF;

      END LOOP;
    END IF;

    EXIT WHEN cursorEstruGeoNivel7%NOTFOUND;
  END LOOP;
  CLOSE cursorEstruGeoNivel7;


  --VALIDAMOS LOS CODIGOS DEL OCTAVO NIVEL (8)
  OPEN cursorEstruGeoNivel8(lnNivelIndicadorGeoRef);
  LOOP
    FETCH cursorEstruGeoNivel8 BULK COLLECT INTO tablaEstruGeopo8 LIMIT W_FILAS;
    IF tablaEstruGeopo8.COUNT > 0 THEN

      FOR x IN tablaEstruGeopo8.FIRST .. tablaEstruGeopo8.LAST LOOP
        registroEstruGeopo8 := tablaEstruGeopo8(x);

        SELECT count(orde_1)
        INTO   lnTotalOcurrencias
        FROM   ZON_VALOR_ESTRU_GEOPO a
        WHERE  a.orde_1 = registroEstruGeopo8.cod_niv1
          AND  a.orde_2 = registroEstruGeopo8.cod_niv2
          AND  a.orde_3 = registroEstruGeopo8.cod_niv3
          AND  a.orde_4 = registroEstruGeopo8.cod_niv4
          AND  a.orde_5 = registroEstruGeopo8.cod_niv5
          AND  a.orde_6 = registroEstruGeopo8.cod_niv6
          AND  a.orde_7 = registroEstruGeopo8.cod_niv7
          AND  a.orde_8 = registroEstruGeopo8.cod_niv8;

        IF (lnTotalOcurrencias = 0) THEN
          --Nueva Valor de Estructura Geopolitica (Nivel1)
          lsOidEstruGeopo:= ZON_FN_DEVUE_OID_ESTRU_GEOPO(lsOidPais, 8);
          lsOidSubesGeopo:= ZON_FN_DEVUE_OID_SUBES_GEOPO(lsOidEstruGeopo, registroEstruGeopo8.cod_sube_niv8);
          lnFila := lnFila + 1;

          INSERT INTO ZON_HISTO_VALID_GEOGR(COD_NIV1, COD_NIV2, COD_NIV3, COD_NIV4, COD_NIV5, COD_NIV6, COD_NIV7, COD_NIV8,
           OID_ESTR, OID_SUBE_ESTR, DES_GEOG, COD_SUBE, COD_OPER, VAL_FILA, IND_GEOR)
          VALUES (registroEstruGeopo8.cod_niv1, registroEstruGeopo8.cod_niv2, registroEstruGeopo8.cod_niv3, registroEstruGeopo8.cod_niv4,
        registroEstruGeopo8.cod_niv5, registroEstruGeopo8.cod_niv6, registroEstruGeopo8.cod_niv7, registroEstruGeopo8.cod_niv8,
      lsOidEstruGeopo, lsOidSubesGeopo, registroEstruGeopo8.des_niv8, registroEstruGeopo8.cod_sube_niv8, 'A', lnFila, registroEstruGeopo8.ind_geor_niv8);

        ELSE
          --Posible Modificacion de Valor de Estructura Geopolitica (Nivel8)
          SELECT des_geog, sub.cod_sube
          INTO   lsDesGeog, lsCodSube
          FROM   zon_valor_estru_geopo val, zon_subes_geopo sub
          WHERE  val.orde_1 = registroEstruGeopo8.cod_niv1
            AND  val.orde_2 = registroEstruGeopo8.cod_niv2
            AND  val.orde_3 = registroEstruGeopo8.cod_niv3
            AND  val.orde_4 = registroEstruGeopo8.cod_niv4
            AND  val.orde_5 = registroEstruGeopo8.cod_niv5
            AND  val.orde_6 = registroEstruGeopo8.cod_niv6
            AND  val.orde_7 = registroEstruGeopo8.cod_niv7
            AND  val.orde_8 = registroEstruGeopo8.cod_niv8
            AND  val.orde_9 IS NULL
            AND  val.sgeo_oid_sube_geop = sub.oid_sube_geop;

          --Valida si ha cambiado la descripcion o codigo de SubEstructura Geopolitica
          IF((registroEstruGeopo8.des_niv8 <> lsDesGeog ) OR (registroEstruGeopo8.cod_sube_niv8 <> lsCodSube )) THEN
            lsOidEstruGeopo:= ZON_FN_DEVUE_OID_ESTRU_GEOPO(lsOidPais, 8);
            lsOidSubesGeopo:= ZON_FN_DEVUE_OID_SUBES_GEOPO(lsOidEstruGeopo, registroEstruGeopo8.cod_sube_niv8);
            lnFila := lnFila + 1;

            INSERT INTO ZON_HISTO_VALID_GEOGR(COD_NIV1, COD_NIV2, COD_NIV3, COD_NIV4, COD_NIV5, COD_NIV6, COD_NIV7, COD_NIV8,
          OID_ESTR, OID_SUBE_ESTR, DES_GEOG, COD_SUBE, COD_OPER, VAL_FILA, IND_GEOR)
            VALUES (registroEstruGeopo8.cod_niv1, registroEstruGeopo8.cod_niv2, registroEstruGeopo8.cod_niv3, registroEstruGeopo8.cod_niv4,
          registroEstruGeopo8.cod_niv5, registroEstruGeopo8.cod_niv6, registroEstruGeopo8.cod_niv7, registroEstruGeopo8.cod_niv8,
         lsOidEstruGeopo, lsOidSubesGeopo,
              registroEstruGeopo8.des_niv8, registroEstruGeopo8.cod_sube_niv8, 'M', lnFila, registroEstruGeopo8.ind_geor_niv8);
          END IF;
        END IF;

      END LOOP;
    END IF;

    EXIT WHEN cursorEstruGeoNivel8%NOTFOUND;
  END LOOP;
  CLOSE cursorEstruGeoNivel8;


  --VALIDAMOS LOS CODIGOS DEL NOVENO NIVEL (9)
  OPEN cursorEstruGeoNivel9(lnNivelIndicadorGeoRef);
  LOOP
    FETCH cursorEstruGeoNivel9 BULK COLLECT INTO tablaEstruGeopo9 LIMIT W_FILAS;
    IF tablaEstruGeopo9.COUNT > 0 THEN

      FOR x IN tablaEstruGeopo9.FIRST .. tablaEstruGeopo9.LAST LOOP
        registroEstruGeopo9 := tablaEstruGeopo9(x);

        SELECT count(orde_1)
        INTO   lnTotalOcurrencias
        FROM   ZON_VALOR_ESTRU_GEOPO a
        WHERE  a.orde_1 = registroEstruGeopo9.cod_niv1
          AND  a.orde_2 = registroEstruGeopo9.cod_niv2
          AND  a.orde_3 = registroEstruGeopo9.cod_niv3
          AND  a.orde_4 = registroEstruGeopo9.cod_niv4
          AND  a.orde_5 = registroEstruGeopo9.cod_niv5
          AND  a.orde_6 = registroEstruGeopo9.cod_niv6
          AND  a.orde_7 = registroEstruGeopo9.cod_niv7
          AND  a.orde_8 = registroEstruGeopo9.cod_niv8
          AND  a.orde_9 = registroEstruGeopo9.cod_niv9;

        IF (lnTotalOcurrencias = 0) THEN
          --Nueva Valor de Estructura Geopolitica (Nivel1)
          lsOidEstruGeopo:= ZON_FN_DEVUE_OID_ESTRU_GEOPO(lsOidPais, 9);
          lsOidSubesGeopo:= ZON_FN_DEVUE_OID_SUBES_GEOPO(lsOidEstruGeopo, registroEstruGeopo9.cod_sube_niv9);
          lnFila := lnFila + 1;

          INSERT INTO ZON_HISTO_VALID_GEOGR(COD_NIV1, COD_NIV2, COD_NIV3, COD_NIV4, COD_NIV5, COD_NIV6, COD_NIV7, COD_NIV8, COD_NIV9,
           OID_ESTR, OID_SUBE_ESTR, DES_GEOG, COD_SUBE, COD_OPER, VAL_FILA, IND_GEOR)
          VALUES (registroEstruGeopo9.cod_niv1, registroEstruGeopo9.cod_niv2, registroEstruGeopo9.cod_niv3, registroEstruGeopo9.cod_niv4,
        registroEstruGeopo9.cod_niv5, registroEstruGeopo9.cod_niv6, registroEstruGeopo9.cod_niv7, registroEstruGeopo9.cod_niv8, registroEstruGeopo9.cod_niv9,
        lsOidEstruGeopo, lsOidSubesGeopo, registroEstruGeopo9.des_niv9, registroEstruGeopo9.cod_sube_niv9, 'A', lnFila, registroEstruGeopo9.ind_geor_niv9);

        ELSE
          --Posible Modificacion de Valor de Estructura Geopolitica (Nivel9)
          SELECT des_geog, sub.cod_sube
          INTO   lsDesGeog, lsCodSube
          FROM   zon_valor_estru_geopo val, zon_subes_geopo sub
          WHERE  val.orde_1 = registroEstruGeopo9.cod_niv1
            AND  val.orde_2 = registroEstruGeopo9.cod_niv2
            AND  val.orde_3 = registroEstruGeopo9.cod_niv3
            AND  val.orde_4 = registroEstruGeopo9.cod_niv4
            AND  val.orde_5 = registroEstruGeopo9.cod_niv5
            AND  val.orde_6 = registroEstruGeopo9.cod_niv6
            AND  val.orde_7 = registroEstruGeopo9.cod_niv7
            AND  val.orde_8 = registroEstruGeopo9.cod_niv8
            AND  val.orde_9 = registroEstruGeopo9.cod_niv9
            AND  val.sgeo_oid_sube_geop = sub.oid_sube_geop;

          --Valida si ha cambiado la descripcion o codigo de SubEstructura Geopolitica
          IF((registroEstruGeopo9.des_niv9 <> lsDesGeog ) OR (registroEstruGeopo9.cod_sube_niv9 <> lsCodSube )) THEN
            lsOidEstruGeopo:= ZON_FN_DEVUE_OID_ESTRU_GEOPO(lsOidPais, 9);
            lsOidSubesGeopo:= ZON_FN_DEVUE_OID_SUBES_GEOPO(lsOidEstruGeopo, registroEstruGeopo9.cod_sube_niv9);
            lnFila := lnFila + 1;

            INSERT INTO ZON_HISTO_VALID_GEOGR(COD_NIV1, COD_NIV2, COD_NIV3, COD_NIV4, COD_NIV5, COD_NIV6, COD_NIV7, COD_NIV8, COD_NIV9,
          OID_ESTR, OID_SUBE_ESTR, DES_GEOG, COD_SUBE, COD_OPER, VAL_FILA, IND_GEOR)
            VALUES (registroEstruGeopo9.cod_niv1, registroEstruGeopo9.cod_niv2, registroEstruGeopo9.cod_niv3, registroEstruGeopo9.cod_niv4,
          registroEstruGeopo9.cod_niv5, registroEstruGeopo9.cod_niv6, registroEstruGeopo9.cod_niv7, registroEstruGeopo9.cod_niv8, registroEstruGeopo9.cod_niv9,
          lsOidEstruGeopo, lsOidSubesGeopo,
              registroEstruGeopo9.des_niv9, registroEstruGeopo9.cod_sube_niv9, 'M', lnFila, registroEstruGeopo9.ind_geor_niv9);
          END IF;
        END IF;

      END LOOP;
    END IF;

    EXIT WHEN cursorEstruGeoNivel9%NOTFOUND;
  END LOOP;
  CLOSE cursorEstruGeoNivel9;


EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR ZON_PR_VALID_DATOS_GEOGR: '||ls_sqlerrm);
END;

/***************************************************************************
Descripcion       : Actualiza los datos de unidades geograficas en la tabla
                    Valor Estructura Geopolictica del SICC, sea una nueva unidad
                    geografica o actualizacion de uno ya existente
Fecha Creacion    : 04/04/2008
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE ZON_PR_ACTUA_UNIDA_GEOGR (psCodigoPais VARCHAR2, psUsuarioAprobacion VARCHAR2)
IS
  CURSOR cursorValidGeo IS
    SELECT *
    FROM   ZON_HISTO_VALID_GEOGR
    WHERE USU_APRO IS NULL
      AND FEC_PROC IS NULL;

  TYPE tTablaValidGeo  IS TABLE OF ZON_HISTO_VALID_GEOGR%ROWTYPE;
  tablaRegistroValidGeo            tTablaValidGeo;
  registroValidGeo                 ZON_HISTO_VALID_GEOGR%ROWTYPE;

  lsOidPais                        SEG_PAIS.OID_PAIS%TYPE;
  lsUltCodGeo                      ZON_VALOR_ESTRU_GEOPO.COD_UNID_GEOG%TYPE;
  lnOidValorEstruGeopo             ZON_VALOR_ESTRU_GEOPO.OID_VALO_ESTR_GEOP%TYPE;
  ldFechaActual                    DATE;
BEGIN

   --Recuperamos el oid Pais
  lsOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);

  --RECUPERAMOS EL ULTIMO VALOR DE CODIGO DE UNIDAD GEOGRAFICA
  SELECT NVL(MAX(COD_UNID_GEOG), 1)
  INTO   lsUltCodGeo
  FROM   ZON_VALOR_ESTRU_GEOPO;

  OPEN cursorValidGeo;
  LOOP
    FETCH cursorValidGeo BULK COLLECT INTO tablaRegistroValidGeo LIMIT W_FILAS;
    IF tablaRegistroValidGeo.COUNT > 0 THEN

      FOR x IN tablaRegistroValidGeo.FIRST .. tablaRegistroValidGeo.LAST LOOP
        registroValidGeo := tablaRegistroValidGeo(x);

        --SE A?ADE NUEVO REGISTRO EN TABLA VALOR ESTRUCTURA GEOPOLITICA
        IF (registroValidGeo.Cod_Oper = 'A') THEN
          lsUltCodGeo := lsUltCodGeo + 1;
          SELECT ZON_VEPO_SEQ.NEXTVAL
          INTO   lnOidValorEstruGeopo
          FROM   DUAL;

          INSERT INTO ZON_VALOR_ESTRU_GEOPO
            (PAIS_OID_PAIS, OID_VALO_ESTR_GEOP, COD_UNID_GEOG,DES_GEOG,
             ORDE_1, ORDE_2, ORDE_3,
             ORDE_4, ORDE_5, ORDE_6,
             ORDE_7, ORDE_8, ORDE_9,
             COD_NSE1,COD_NSE2,COD_NSE3,IND_ACTI,IND_BORR,EGEO_OID_ESTR_GEOP,SGEO_OID_SUBE_GEOP,FEC_ACTU, IND_GEOR)
          VALUES
            (lsOidPais, lnOidValorEstruGeopo, lsUltCodGeo, registroValidGeo.Des_Geog,
            registroValidGeo.Cod_Niv1, registroValidGeo.Cod_Niv2, registroValidGeo.Cod_Niv3,
            registroValidGeo.Cod_Niv4, registroValidGeo.Cod_Niv5, registroValidGeo.Cod_Niv6,
            registroValidGeo.Cod_Niv7, registroValidGeo.Cod_Niv8, registroValidGeo.Cod_Niv9,
            NULL, NULL, NULL, '1', '0', registroValidGeo.Oid_Estr, registroValidGeo.Oid_Sube_Estr, SYSDATE, registroValidGeo.Ind_Geor);

        END IF;

        --SE MODIFICA REGISTRO EN TABLA VALOR ESTRUCTURA GEOPOLITICA
        IF (registroValidGeo.Cod_Oper = 'M') THEN
          UPDATE ZON_VALOR_ESTRU_GEOPO
             SET DES_GEOG = registroValidGeo.Des_Geog,
                 COD_NSE1 = NULL,
                 COD_NSE2 = NULL,
                 COD_NSE3 = NULL,
                 SGEO_OID_SUBE_GEOP = registroValidGeo.Oid_Sube_Estr,
                 FEC_ACTU = SYSDATE
          WHERE  PAIS_OID_PAIS = lsOidPais
            AND  ORDE_1 || ORDE_2 || ORDE_3 || ORDE_4 || ORDE_5 ||
                 ORDE_6 || ORDE_7 || ORDE_8 || ORDE_9 = registroValidGeo.cod_niv1 || registroValidGeo.cod_niv2 ||
              registroValidGeo.cod_niv3 || registroValidGeo.cod_niv4 || registroValidGeo.cod_niv5 ||
                 registroValidGeo.cod_niv6 || registroValidGeo.cod_niv7 || registroValidGeo.cod_niv8 || registroValidGeo.cod_niv9;
        END IF;

      END LOOP;
    END IF;

    EXIT WHEN cursorValidGeo%NOTFOUND;
  END LOOP;
  CLOSE cursorValidGeo;

  --Actualizamos la tabla de Validaciones de Unidades Geograficas, el usuario que ejecuto el proceso y la fecha
  SELECT TRUNC(SYSDATE) INTO ldFechaActual FROM DUAL;

  UPDATE ZON_HISTO_VALID_GEOGR
     SET USU_APRO = psUsuarioAprobacion,
         FEC_PROC = ldFechaActual
   WHERE USU_APRO IS NULL
     AND FEC_PROC IS NULL;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR ZON_PR_ACTUA_UNIDA_GEOGR: '||ls_sqlerrm);
END;

/***************************************************************************
Descripcion       : Devuelve el oid de la subEstructura relacionada a la
                    Estructura geopolitica pasada como parametro
Fecha Creacion    : 01/04/2008
Autor             : Sergio Apaza
***************************************************************************/
FUNCTION ZON_FN_DEVUE_COD_SUBES
  (psOidEstrGeopo    NUMBER) RETURN NUMBER
IS
  lnOidSubesGeopo     NUMBER;
BEGIN

  SELECT cod_sube
    INTO lnOidSubesGeopo
    FROM ZON_SUBES_GEOPO
   WHERE egeo_oid_estr_geop = psOidEstrGeopo;

  RETURN lnOidSubesGeopo;

EXCEPTION
  WHEN NO_DATA_FOUND THEN
    RETURN -1;
  WHEN TOO_MANY_ROWS THEN
    RETURN -1;
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR ZON_FN_DEVUE_COD_SUBES: '||ls_sqlerrm);
END;

/***************************************************************************
Descripcion       : Devuelve la descripcion de la subEstructura relacionada a la
                    Estructura geopolitica pasada como parametro
Fecha Creacion    : 01/04/2008
Autor             : Sergio Apaza
***************************************************************************/
FUNCTION ZON_FN_DEVUE_DES_SUBES
  (pnOidEstrGeopo    NUMBER) RETURN VARCHAR2
IS
  lsDesSubesGeopo     VARCHAR2(40);
BEGIN

  SELECT PQ_APL_AUX.VALOR_GEN_I18N_SICC(1, oid_sube_geop, 'ZON_SUBES_GEOPO')
    INTO lsDesSubesGeopo
    FROM ZON_SUBES_GEOPO
   WHERE egeo_oid_estr_geop = pnOidEstrGeopo;

  RETURN lsDesSubesGeopo;

EXCEPTION
  WHEN NO_DATA_FOUND THEN
    RETURN '';
  WHEN TOO_MANY_ROWS THEN
    RETURN '';
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR ZON_FN_DEVUE_DES_SUBES: '||ls_sqlerrm);
END;

/***************************************************************************
Descripcion       : Devuelve el oid de la Estructura Geopolitica de un
                    determinado pais y nivel de Orden Geografico
Fecha Creacion    : 03/04/2008
Autor             : Sergio Apaza
***************************************************************************/
FUNCTION ZON_FN_DEVUE_OID_ESTRU_GEOPO
  (pnOidPais    NUMBER,
   pnOrden      NUMBER) RETURN NUMBER
IS
  lsOidEstruGeopo     ZON_ESTRU_GEOPO.OID_ESTR_GEOP%TYPE;
BEGIN

  SELECT a.oid_estr_geop
    INTO lsOidEstruGeopo
    FROM ZON_ESTRU_GEOPO a
   WHERE a.pais_oid_pais = pnOidPais
     AND a.cod_orde = pnOrden;

  RETURN lsOidEstruGeopo;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR ZON_FN_DEVUE_OID_ESTRU_GEOPO: '||ls_sqlerrm);
END;

/***************************************************************************
Descripcion       : Devuelve el oid de la SubEstructura Geopolitica de un
                    determinado estructura Geopolitica y codigo SubEstructura
Fecha Creacion    : 03/04/2008
Autor             : Sergio Apaza
***************************************************************************/
FUNCTION ZON_FN_DEVUE_OID_SUBES_GEOPO
  (pnOidEstruGeopo        NUMBER,
   pnCodigoSubesGeopo     NUMBER) RETURN NUMBER
IS
  lnOidSubesGeopo    ZON_SUBES_GEOPO.OID_SUBE_GEOP%TYPE;
BEGIN

  SELECT a.oid_sube_geop
    INTO lnOidSubesGeopo
    FROM ZON_SUBES_GEOPO a
   WHERE a.egeo_oid_estr_geop = pnOidEstruGeopo
     AND a.cod_sube = pnCodigoSubesGeopo;

  RETURN lnOidSubesGeopo;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR ZON_FN_DEVUE_OID_SUBES_GEOPO: '||ls_sqlerrm);
END;

/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte zon Unidades Geograficas
Fecha Creacion    : 19/08/2010
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE ZON_PR_GENER_REPOR_UNIGE_CSV(
    psCodigoPais            VARCHAR2,
    psNombreArchivo         VARCHAR2,
    psTitulo                VARCHAR2,
    psDirectorio       OUT  VARCHAR2
    )
IS

  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 5000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  W_DESC              VARCHAR2(200);
  lsLinea             VARCHAR2(4000);
  lsNombreArchivo     VARCHAR2(50);
  lsCodigoPais        VARCHAR2(3);
  CURSOR c_interfaz IS
  SELECT
       N3.ORDE_1,
       N1.DES_GEOG AS DES_GEOG1,
       N3.ORDE_2,
       N2.DES_GEOG AS DES_GEOG2,
       N3.ORDE_3,
       N3.DES_GEOG AS DES_GEOG3,
       N3.ORDE_4,
       CASE
         WHEN (N3.ORDE_4 IS NULL) THEN
          NULL
         ELSE
          (SELECT NI4.DES_GEOG
             FROM ZON_VALOR_ESTRU_GEOPO NI4
            WHERE N3.ORDE_1 = NI4.ORDE_1
              AND N3.ORDE_2 = NI4.ORDE_2
              AND N3.ORDE_3 = NI4.ORDE_3
              AND N3.ORDE_4 = NI4.ORDE_4
              AND ROWNUM = 1)
       END AS DESCRIPCIONCENTROPOBLADO,
       N3.IND_GEOR
  FROM ZON_VALOR_ESTRU_GEOPO N3,
       ZON_VALOR_ESTRU_GEOPO N2,
       ZON_VALOR_ESTRU_GEOPO N1
 WHERE N3.ORDE_1 = N1.ORDE_1
   AND N1.ORDE_2 IS NULL
   AND N1.ORDE_3 IS NULL
   AND N3.ORDE_1 = N2.ORDE_1
   AND N3.ORDE_2 = N2.ORDE_2
   AND N2.ORDE_3 IS NULL
   AND N3.IND_BORR = 0
   AND N3.ORDE_3 IS NOT NULL
 ORDER BY N3.ORDE_1, N3.ORDE_2, N3.ORDE_3;

TYPE interfazTipo IS RECORD (
 orde1               ZON_VALOR_ESTRU_GEOPO.ORDE_1%TYPE,
 desGeog1            ZON_VALOR_ESTRU_GEOPO.DES_GEOG%TYPE,
 orde2               ZON_VALOR_ESTRU_GEOPO.ORDE_2%TYPE,
 desGeog2            ZON_VALOR_ESTRU_GEOPO.DES_GEOG%TYPE,
 orde3               ZON_VALOR_ESTRU_GEOPO.ORDE_3%TYPE,
 desGeog3            ZON_VALOR_ESTRU_GEOPO.DES_GEOG%TYPE,
 orde4               ZON_VALOR_ESTRU_GEOPO.ORDE_4%TYPE,
 desCentroPoblado    ZON_VALOR_ESTRU_GEOPO.DES_GEOG%TYPE,
 indGeografico       ZON_VALOR_ESTRU_GEOPO.IND_GEOR%TYPE
);

   TYPE interfazTab  IS TABLE OF interfazTipo ;
   interfazRecord interfazTab;
   lbAbrirUtlFile  BOOLEAN;

BEGIN
  lbAbrirUtlFile := TRUE;
  EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
  OPEN c_interfaz;
  LOOP
   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
   IF lbAbrirUtlFile THEN
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.csv', psTitulo, lsDirTempo, V_HANDLE);
      psDirectorio := lsDirTempo;
      lbAbrirUtlFile := FALSE;
   END IF ;
   IF interfazRecord.COUNT > 0 THEN
      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          lsLinea := '=T("'||interfazRecord(x).orde1||'")' ||','||
                    interfazRecord(x).desGeog1 ||','||
                    '=T("'||interfazRecord(x).orde2||'")' ||','||
                    interfazRecord(x).desGeog2 ||','||
                    '=T("'||interfazRecord(x).orde3||'")' ||','||
                    interfazRecord(x).desGeog3 ||','||
                    '=T("'||interfazRecord(x).orde4||'")' ||','||
                   interfazRecord(x).desCentroPoblado ||','||
                    interfazRecord(x).indGeografico  ;

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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR ZON_PR_GENER_REPOR_UNIGE_CSV: '||ls_sqlerrm);
END ZON_PR_GENER_REPOR_UNIGE_CSV;

/***************************************************************************
Descripcion       : Genera archivo Excel correspondiente al Reporte direcciones consultas
Fecha Creacion    : 13/08/2013
Autor             : Yahir Rivas Luna
***************************************************************************/
PROCEDURE ZON_PR_REPOR_DIREC_CONSU(
     psCodigoPais            VARCHAR2,
     psCodigoMarca           VARCHAR2,
     psCodigoCanal           VARCHAR2,
     psCodigoTipoCliente     VARCHAR2,
     psCodigoPeriodoInicio   VARCHAR2,
     psEstatus               VARCHAR2,
     psCodigoRegion          VARCHAR2,
     psCondicionUA           VARCHAR2
    )
IS
searchStr  VARCHAR2(100) := ',';
replaceStr VARCHAR2(100) := ' ';

lsSelect varchar2(4000);
CURSOR c_base IS
  SELECT *
    FROM ZON_GTT_DIREC_CONSU_03 ;

TYPE c_base_record IS TABLE OF c_base%ROWTYPE INDEX BY BINARY_INTEGER;
cBaseRec c_base_record;

lnOk INTEGER := 1;
lnFilas INTEGER := 10000;
lnOidPeriodoInicio INTEGER;
lsCampaUltiPedi seg_perio_corpo.cod_peri%TYPE;
lnContador NUMBER;

BEGIN
    /*
      Inicializar variables de trabajo
    */
    lnOidPeriodoInicio := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO( psCodigoPeriodoInicio );

    /*
      Limpiar tabla de trabajo
    */
    EXECUTE IMMEDIATE 'TRUNCATE TABLE ZON_REPOR_DIREC_CONSU';
    DELETE FROM ZON_GTT_DIREC_CONSU;
    DELETE FROM ZON_GTT_DIREC_CONSU_02;
    DELETE FROM ZON_GTT_DIREC_CONSU_03;
    
    INSERT INTO ZON_GTT_DIREC_CONSU(
        cod_pais, 
        cod_marc, 
        cod_cana, 
        cod_regi, 
        cod_zona, 
        cod_secc, 
        cod_terr, 
        codter, 
        ind_ctrl_inte_geor, 
        cod_clie, 
        num_docu_iden, 
        val_ape1, 
        ape_mate, 
        nom_clie, 
        fec_cont, 
        cod_peri, 
        ult_pedi, 
        mon_cata, 
        mon_neto, 
        sal_tota, 
        cod_esta_clie, 
        des_esta, 
        cod_tipo_via, 
        DES_ABRV_TIPO_VIA,
        val_nomb_via, 
        num_ppal, 
        val_obse, 
        val_barr, 
        val_inte, 
        val_mann, 
        val_lott, 
        val_kill, 
        cod_unid_geog, 
        des_dept, 
        des_prov, 
        des_dist, 
        cod_tcpp, 
        des_tcpp, 
        des_cenp, 
        val_tele_casa, 
        val_tele_celu, 
        val_tele_traa, 
        oid_clie, 
        perd_oid_peri_ulti_pedi, 
        val_naci, 
        val_sexo, 
        tipo_docu_iden, 
        val_esta_civi, 
        tipo_pers, 
        val_orig_ingr, 
        val_nomb_barri, 
        val_manz_letra, 
        val_etap_conj, 
        val_call_princ, 
        val_call_secu
    )
    SELECT psCodigoPais COD_PAIS,
         psCodigoMarca COD_MARC,
         psCodigoCanal COD_CANA,
         zorg.cod_regi COD_REGI,
         zzon.cod_zona COD_ZONA,
         zscc.cod_secc COD_SECC,
         terr.cod_terr COD_TERR,
         LPAD( terr.cod_terr, 6, '0') CODTER,
         dire.ind_ctrl_inte_geor IND_CTRL_INTE_GEOR,
         clie.cod_clie COD_CLIE,
         TRANSLATE(clid.num_docu_iden, searchStr, replaceStr ) NUM_DOCU_IDEN,
         TRANSLATE(clie.val_ape1, searchStr, replaceStr )  VAL_APE1,
         TRANSLATE(clie.val_ape2, searchStr, replaceStr )  APE_MATE,
         TRANSLATE(clie.val_nom1 || DECODE(clie.val_nom2,NULL,'',' '||clie.val_nom2), searchStr, replaceStr )   NOM_CLIE,
         TO_CHAR( cprc.fec_cont,'DD/MM/YYYY') FEC_CONT,
         peri.cod_peri COD_PERI,
         NULL ULT_PEDI,
         NULL MON_CATA,
         NULL MON_NETO,
         clie.sal_deud_ante SAL_TOTA,
         esta.cod_esta_clie COD_ESTA_CLIE,
         TRANSLATE(( SELECT val_i18n
             FROM gen_i18n_sicc_comun
            WHERE val_oid = clda.esta_oid_esta_clie
              AND attr_enti = 'MAE_ESTAT_CLIEN'  ), searchStr, replaceStr ) DES_ESTA,
         dire.cod_tipo_via COD_TIPO_VIA,
         dire.des_abrv_tipo_via DES_ABRV_TIPO_VIA,
         TRANSLATE(dire.val_nomb_via, searchStr, replaceStr ) VAL_NOMB_VIA,
         dire.num_ppal NUM_PPAL,
         TRANSLATE(dire.val_obse, searchStr, replaceStr ) VAL_OBSE,
         TRANSLATE(dire.val_barr, searchStr, replaceStr ) VAL_BARR,
         NULL VAL_INTE,
         NULL VAL_MANN,
         NULL VAL_LOTT,
         NULL VAL_KILL,
         dire.cod_unid_geog COD_UNID_GEOG,
         TRANSLATE(dire.nivel_1, searchStr, replaceStr ) DES_DEPT,
         TRANSLATE(dire.nivel_2, searchStr, replaceStr ) DES_PROV,
         TRANSLATE(dire.nivel_3, searchStr, replaceStr ) DES_DIST,
         dire.codigotipoEstrucGeopo4 COD_TCPP,
         TRANSLATE(dire.descripciontipoEstrucGeopo4, searchStr, replaceStr ) DES_TCPP,
         TRANSLATE(dire.codigoEstrucGeopo4 || ' - ' || dire.descripcionEstrucGeopo4, searchStr, replaceStr ) DES_CENP,
         TRANSLATE((
           SELECT clco.val_text_comu
           FROM mae_clien_comun clco,
                mae_tipo_comun ticm
          WHERE clco.ticm_oid_tipo_comu = ticm.oid_tipo_comu
            AND cod_tipo_comu = 'TF'
            AND clco.clie_oid_clie = clda.clie_oid_clie
            AND rownum = 1
         ), searchStr, replaceStr ) VAL_TELE_CASA,
         TRANSLATE((
           SELECT clco.val_text_comu
           FROM mae_clien_comun clco,
                mae_tipo_comun ticm
          WHERE clco.ticm_oid_tipo_comu = ticm.oid_tipo_comu
            AND cod_tipo_comu = 'TM'
            AND clco.clie_oid_clie = clda.clie_oid_clie
            AND rownum = 1
         ), searchStr, replaceStr ) VAL_TELE_CELU,
         TRANSLATE((
           SELECT clco.val_text_comu
           FROM mae_clien_comun clco,
                mae_tipo_comun ticm
          WHERE clco.ticm_oid_tipo_comu = ticm.oid_tipo_comu
            AND cod_tipo_comu = 'TT'
            AND clco.clie_oid_clie = clda.clie_oid_clie
            AND rownum = 1
         ), searchStr, replaceStr ) VAL_TELE_TRAA,
         clie.oid_clie,
         '' perd_oid_peri_ulti_pedi,
         '' nacionalidad,
   
   (decode(clie.cod_sexo, 'F', 'Femenino', 'Masculino' )) AS sexo,
   
   (select mtd.val_ocr_tdoc from
   MAE_TIPO_DOCUM mtd, mae_clien_ident mci
   where mtd.oid_tipo_docu=mci.tdoc_oid_tipo_docu
   and mci.clie_oid_clie = clie.oid_clie and rownum=1) as tipo_documento_identidad ,     
   (SELECT MEC.VAL_OCR_ESTA FROM MAE_ESTAD_CIVIL MEC , mae_clien_datos_adici MCDA
    WHERE MEC.OID_ESTA_CIVI=MCDA.ESCV_OID_ESTA_CIVI
    AND MCDA.CLIE_OID_CLIE= clie.oid_clie and rownum=1) AS estado_civil ,
    (SELECT  I18N.VAL_I18N 
        FROM mae_clien_datos_adici mcda, MAE_TIPO_PERSO TP, V_GEN_I18N_SICC I18N
       WHERE mcda.TPES_OID_TIPO_PERS=tp.oid_tipo_pers
         and TP.OID_TIPO_PERS = I18N.VAL_OID
         AND I18N.IDIO_OID_IDIO = 1
         AND I18N.ATTR_ENTI = 'MAE_TIPO_PERSO'
         and mcda.clie_oid_clie= clie.oid_clie and rownum=1) AS tipo_persona,
     (SELECT  I18N.VAL_I18N 
	      FROM mae_clien_datos_adici mcda, MAE_ORIG_INGRE OI, V_GEN_I18N_SICC I18N
	     WHERE mcda.ORIN_OID_ORIG_INGR=OI.OID_ORIG_INGR
       AND OI.OID_ORIG_INGR = I18N.VAL_OID
	       AND I18N.IDIO_OID_IDIO = 1
	       AND I18N.ATTR_ENTI = 'MAE_ORIG_INGRE'
	    and mcda.clie_oid_clie= clie.oid_clie and rownum=1) AS origen_ingreso,
      ( select MCD.VAL_NOM_BARR from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE = clie.oid_clie and rownum=1) AS nombre_barrio,
      (select MCD.VAL_NOM_MANZ from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE = clie.oid_clie and rownum=1) as manzana_letra,
      (select MCD.VAL_ETA_CONJ from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE =  clie.oid_clie and rownum=1) as etapa_conjunto, 
      (select MCD.VAL_CAL_PRIN from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE =clie.oid_clie and rownum=1) as calle_principal,
       (select MCD.VAL_CAL_SECU from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE =clie.oid_clie and rownum=1)  as calle_secundaria
         
    FROM mae_clien_datos_adici clda,
         mae_estat_clien esta,
         mae_clien clie,
         mae_clien_tipo_subti ctsu,
         mae_tipo_clien ticl,
         mae_clien_unida_admin cuad,
         mae_clien_ident clid,
         mae_clien_prime_conta cprc,
         cra_perio perd,
         seg_perio_corpo peri,
         zon_terri_admin ztad,
         zon_regio zorg,
         zon_zona zzon,
         zon_secci zscc,
         zon_terri terr,
       (
           SELECT a.clie_oid_clie,
                  c.cod_tipo_via,
                  c.des_abrv_tipo_via,
                  a.val_nomb_via,
                  a.num_ppal,
                  a.val_obse,
                  a.val_barr,
                  a.cod_unid_geog,
                  a.ind_ctrl_inte_geor,
                (
                 SELECT des_geog
                   FROM ZON_VALOR_ESTRU_GEOPO
                  WHERE pais_oid_pais = d.pais_oid_pais
                    AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
                    AND orde_2 IS NULL
                ) AS nivel_1,
                (
                 SELECT des_geog
                   FROM ZON_VALOR_ESTRU_GEOPO
                  WHERE pais_oid_pais = d.pais_oid_pais
                    AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
                    AND orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
                    AND orde_3 IS NULL
                ) AS nivel_2,
                (
                 SELECT des_geog
                   FROM ZON_VALOR_ESTRU_GEOPO
                  WHERE pais_oid_pais = d.pais_oid_pais
                    AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
                    AND orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
                    AND orde_3 = SUBSTR (a.cod_unid_geog, 13, 6)
                    AND orde_4 IS NULL
                ) AS nivel_3,
                    (
                         SELECT pq_apl_aux.valor_gen_i18n_sicc
                                   (1,
                                    (
                                     SELECT nivel.sgeo_oid_sube_geop
                                       FROM zon_valor_estru_geopo nivel
                                    WHERE nivel.orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
                                      AND nivel.orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
                                      AND nivel.orde_3 = SUBSTR (a.cod_unid_geog, 13, 6)
                                      AND nivel.orde_4 = SUBSTR (a.cod_unid_geog, 19, 6)
                                        AND nivel.orde_5 IS NULL
                                        AND nivel.pais_oid_pais = d.pais_oid_pais
                                        AND ROWNUM = 1),
                                    'ZON_SUBES_GEOPO'
                                   )
                         FROM DUAL )
                  AS descripciontipoEstrucGeopo4,
                   (
                         SELECT nivel.des_geog
                           FROM zon_valor_estru_geopo nivel
                            WHERE nivel.orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
                              AND nivel.orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
                              AND nivel.orde_3 = SUBSTR (a.cod_unid_geog, 13, 6)
                              AND nivel.orde_4 = SUBSTR (a.cod_unid_geog, 19, 6)
                            AND nivel.orde_5 IS NULL
                            AND nivel.pais_oid_pais = d.pais_oid_pais
                            AND ROWNUM = 1
                   ) AS descripcionEstrucGeopo4,
                (
                 SELECT zsg.cod_sube
                   FROM zon_subes_geopo zsg
                  WHERE zsg.oid_sube_geop =
                           (
                            SELECT nivel.sgeo_oid_sube_geop
                              FROM zon_valor_estru_geopo nivel
                               WHERE nivel.orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
                                 AND nivel.orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
                                 AND nivel.orde_3 = SUBSTR (a.cod_unid_geog, 13, 6)
                                 AND nivel.orde_4 = SUBSTR (a.cod_unid_geog, 19, 6)
                               AND nivel.orde_5 IS NULL
                               AND nivel.pais_oid_pais = d.pais_oid_pais
                               AND ROWNUM = 1
                            )
                ) AS codigotipoEstrucGeopo4,
                (
                 SELECT nivel.orde_4
                   FROM zon_valor_estru_geopo nivel
                    WHERE nivel.orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
                      AND nivel.orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
                      AND nivel.orde_3 = SUBSTR (a.cod_unid_geog, 13, 6)
                      AND nivel.orde_4 = SUBSTR (a.cod_unid_geog, 19, 6)
                    AND nivel.orde_5 IS NULL
                    AND nivel.pais_oid_pais = d.pais_oid_pais
                    AND ROWNUM = 1
                  ) AS codigoEstrucGeopo4
           FROM mae_clien_direc a,
                mae_tipo_direc b,
                seg_tipo_via c,
                mae_clien d,
                zon_terri t,
                zon_valor_estru_geopo vepo
          WHERE d.OID_CLIE = a.CLIE_OID_CLIE
            AND b.OID_TIPO_DIRE = a.TIDC_OID_TIPO_DIRE
            AND c.OID_TIPO_VIA = a.TIVI_OID_TIPO_VIA
            AND a.TERR_OID_TERR = t.OID_TERR (+)
            AND t.vepo_oid_valo_estr_geop = vepo.oid_valo_estr_geop(+)
            AND a.IND_ELIM = 0
            AND a.IND_DIRE_PPAL  = 1
         ) dire
   WHERE clda.clie_oid_clie = clie.oid_clie
     AND clda.clie_oid_clie = ctsu.clie_oid_clie
     AND clda.clie_oid_clie = cuad.clie_oid_clie
     AND clda.clie_oid_clie = clid.clie_oid_clie
     AND clda.clie_oid_clie = cprc.clie_oid_clie(+)
     AND clda.esta_oid_esta_clie = esta.oid_esta_clie
     AND clda.clie_oid_clie = dire.clie_oid_clie(+)
     AND ctsu.ticl_oid_tipo_clie = ticl.oid_tipo_clie
     AND cprc.perd_oid_peri = perd.oid_peri
     AND perd.peri_oid_peri = peri.oid_peri
     AND cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
     AND ztad.terr_oid_terr = terr.oid_terr
     AND ztad.zscc_oid_secc = zscc.oid_secc
     AND zscc.zzon_oid_zona = zzon.oid_zona
     AND zzon.zorg_oid_regi = zorg.oid_regi
     --
     AND clid.val_iden_docu_prin = 1
     AND cuad.ind_acti = 1
     AND zorg.cod_regi = psCodigoRegion
     AND esta.cod_esta_clie = DECODE( psEstatus,NULL,esta.cod_esta_clie,psEstatus)
     AND ticl.cod_tipo_clie = DECODE( psCodigoTipoCliente,NULL,ticl.cod_tipo_clie,psCodigoTipoCliente)
       ;
    
    COMMIT;
    SELECT COUNT(1) 
    INTO lnContador
    FROM ZON_GTT_DIREC_CONSU;
    
    lsSelect := 'INSERT INTO ZON_GTT_DIREC_CONSU_03 SELECT * FROM ZON_GTT_DIREC_CONSU ';
    IF psCondicionUA IS NOT NULL THEN
       lsSelect := lsSelect||psCondicionUA;
    END IF;
    EXECUTE IMMEDIATE lsSelect;
    
    
    INSERT INTO ZON_GTT_DIREC_CONSU_02(
       clie_oid_clie,
       perd_oid_peri,
       val_mont_tota)
    SELECT sca2.clie_oid_clie,
           sca2.perd_oid_peri,
           sca2.val_mont_tota
    FROM ped_solic_cabec_acum2 sca2,
         ZON_GTT_DIREC_CONSU_03 x
    WHERE sca2.clie_oid_clie = x.oid_clie;   
    
    COMMIT;
    SELECT COUNT(1) 
    INTO lnContador
    FROM ZON_GTT_DIREC_CONSU_02;
    
    SELECT COUNT(1) 
    INTO lnContador
    FROM ZON_GTT_DIREC_CONSU_03;

    /*
      PROCESAR CONSULTORAS SELECCIONADAS SEGUN PARAMETROS
    */
    OPEN C_BASE;
      LOOP
       FETCH c_base BULK COLLECT INTO cBaseRec LIMIT lnFilas;
       IF cBaseRec.COUNT > 0 THEN
          FOR a IN cBaseRec.FIRST .. cBaseRec.LAST LOOP
          
              -- Ultimo Pedido --
              BEGIN 
                  SELECT MAX( sca2.perd_oid_peri ) perd_oid_peri
                  INTO cBaseRec(a).perd_oid_peri_ulti_pedi
                  FROM ZON_GTT_DIREC_CONSU_02 sca2
                  WHERE sca2.clie_oid_clie = cBaseRec(a).oid_clie;
                   
              EXCEPTION 
                WHEN no_data_found THEN
                  cBaseRec(a).perd_oid_peri_ulti_pedi := '';
              END;
             
              -- Obtiene la campa?a de ultimo pedido
              cBaseRec(a).ult_pedi := FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO( cBaseRec(a).perd_oid_peri_ulti_pedi );
              
              -- Obtiene monto catalogo ultimo pedido
            BEGIN
                SELECT sca2.val_mont_tota
                  INTO cBaseRec(a).mon_cata
                  FROM ZON_GTT_DIREC_CONSU_02 sca2
                 WHERE sca2.clie_oid_clie = cBaseRec(a).oid_clie
                   AND sca2.perd_oid_peri = cBaseRec(a).perd_oid_peri_ulti_pedi
                   AND rownum = 1;
              EXCEPTION WHEN NO_DATA_FOUND THEN
                cBaseRec(a).mon_cata := 0.0;
            END;

              -- Obtiene monto neto ultimo pedido
            BEGIN
               SELECT NVL (SUM (cons.val_tota_paga_loca), 0)
                  INTO cBaseRec(a).mon_neto
                  FROM ped_solic_cabec soca,
                      ped_solic_cabec cons,
                       ped_tipo_solic_pais tspa,
                       ped_tipo_solic tsol
                 WHERE soca.soca_oid_soli_cabe = cons.oid_soli_cabe(+)
                   AND soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
                   AND tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
                   AND tsol.cod_tipo_soli = 'SOC'
                   AND soca.perd_oid_peri = cBaseRec(a).perd_oid_peri_ulti_pedi
                   AND soca.clie_oid_clie = cBaseRec(a).oid_clie
                   AND soca.ind_oc = 1
                   AND soca.grpr_oid_grup_proc = 5
                   AND cons.esso_oid_esta_soli != 4;
              EXCEPTION WHEN NO_DATA_FOUND THEN
                    cBaseRec(a).mon_neto := 0.0;
              END;
              
              -- Nacionalidad --
              BEGIN 
                  SELECT  I18N.VAL_I18N AS DESCRIPCION
                  INTO cBaseRec(a).val_naci
                  FROM mae_clien_datos_adici mda, 
                      SEG_NACIO NAC, 
                      V_GEN_I18N_SICC I18N
                  WHERE mda.snon_oid_naci = nac.oid_naci
                     AND NAC.OID_NACI = I18N.VAL_OID
                     AND I18N.IDIO_OID_IDIO = 1
                     AND I18N.ATTR_ENTI = 'SEG_NACIO'
                     AND MDA.CLIE_OID_CLIE = cBaseRec(a).oid_clie 
                     AND rownum=1;
                   
              EXCEPTION 
                WHEN no_data_found THEN
                  cBaseRec(a).val_naci := '';
              END;
    
              BEGIN
                  INSERT INTO ZON_REPOR_DIREC_CONSU
                  VALUES(cBaseRec(a).cod_pais,
                         cBaseRec(a).cod_marc,
                         cBaseRec(a).cod_cana,
                         cBaseRec(a).cod_regi,
                         cBaseRec(a).cod_zona,
                         cBaseRec(a).cod_secc,
                         cBaseRec(a).cod_terr,
                         cBaseRec(a).ind_ctrl_inte_geor,
                         cBaseRec(a).cod_clie,
                         cBaseRec(a).num_docu_iden,
                         cBaseRec(a).val_ape1,
                         cBaseRec(a).ape_mate,
                         cBaseRec(a).nom_clie,
                         cBaseRec(a).fec_cont,
                         cBaseRec(a).cod_peri,
                         cBaseRec(a).ult_pedi,
                         cBaseRec(a).mon_cata,
                         cBaseRec(a).mon_neto,
                         cBaseRec(a).sal_tota,
                         cBaseRec(a).cod_esta_clie,
                         cBaseRec(a).des_esta,
                         cBaseRec(a).cod_tipo_via,
                         cBaseRec(a).des_abrv_tipo_via,
                         cBaseRec(a).val_nomb_via,
                         cBaseRec(a).num_ppal,
                         cBaseRec(a).val_obse,
                         cBaseRec(a).val_barr,
                         cBaseRec(a).val_inte,
                         cBaseRec(a).val_mann,
                         cBaseRec(a).val_lott,
                         cBaseRec(a).val_kill,
                         cBaseRec(a).cod_unid_geog,
                         cBaseRec(a).des_dept,
                         cBaseRec(a).des_prov,
                         cBaseRec(a).des_dist,
                         cBaseRec(a).cod_tcpp,
                         cBaseRec(a).des_tcpp,
                         cBaseRec(a).des_cenp,
                         SUBSTR( cBaseRec(a).val_tele_casa,1,50),
                         SUBSTR( cBaseRec(a).val_tele_celu,1,50),
                         SUBSTR( cBaseRec(a).val_tele_traa,1,50),
                         cBaseRec(a).val_naci,
                         cBaseRec(a).val_sexo,
                         cBaseRec(a).tipo_docu_iden,
                         cBaseRec(a).val_esta_civi,
                         cBaseRec(a).tipo_pers,
                         cBaseRec(a).val_orig_ingr,
                         cBaseRec(a).val_nomb_barri,
                         cBaseRec(a).val_manz_letra,
                         cBaseRec(a).val_etap_conj,
                         cBaseRec(a).val_call_princ,
                         cBaseRec(a).val_call_secu )
                       ;
              EXCEPTION WHEN dup_val_on_index THEN
                  UPDATE ZON_REPOR_DIREC_CONSU
                     SET cod_pais = cBaseRec(a).cod_pais,
                         cod_marc = cBaseRec(a).cod_marc,
                         cod_cana = cBaseRec(a).cod_cana,
                         cod_regi = cBaseRec(a).cod_regi,
                         cod_zona = cBaseRec(a).cod_zona,
                         cod_secc = cBaseRec(a).cod_secc,
                         cod_terr = cBaseRec(a).cod_terr,
                         ind_ctrl_inte_geor = cBaseRec(a).ind_ctrl_inte_geor,
                         num_docu_iden = cBaseRec(a).num_docu_iden,
                         val_ape1 = cBaseRec(a).val_ape1,
                         ape_mate = cBaseRec(a).ape_mate,
                         nom_clie = cBaseRec(a).nom_clie,
                         fec_cont = cBaseRec(a).fec_cont,
                         cod_peri = cBaseRec(a).cod_peri,
                         ult_pedi = cBaseRec(a).ult_pedi,
                         mon_cata = cBaseRec(a).mon_cata,
                         mon_neto = cBaseRec(a).mon_neto,
                         sal_tota = cBaseRec(a).sal_tota,
                         cod_esta_clie = cBaseRec(a).cod_esta_clie,
                         des_esta = cBaseRec(a).des_esta,
                         cod_tipo_via = cBaseRec(a).cod_tipo_via,
                         des_abrv_tipo_via = cBaseRec(a).des_abrv_tipo_via,
                         val_nomb_via = cBaseRec(a).val_nomb_via,
                         num_ppal = cBaseRec(a).num_ppal,
                         val_obse = cBaseRec(a).val_obse,
                         val_barr = cBaseRec(a).val_barr,
                         val_inte = cBaseRec(a).val_inte,
                         val_mann = cBaseRec(a).val_mann,
                         val_lott = cBaseRec(a).val_lott,
                         val_kill = cBaseRec(a).val_kill,
                         cod_unid_geog = cBaseRec(a).cod_unid_geog,
                         des_dept = cBaseRec(a).des_dept,
                         des_prov = cBaseRec(a).des_prov,
                         des_dist = cBaseRec(a).des_dist,
                         cod_tcpp = cBaseRec(a).cod_tcpp,
                         des_tcpp = cBaseRec(a).des_tcpp,
                         des_cenp = cBaseRec(a).des_cenp,
                         val_tele_casa = SUBSTR( cBaseRec(a).val_tele_casa,1,50),
                         val_tele_celu = SUBSTR( cBaseRec(a).val_tele_celu,1,50),
                         val_tele_traa = SUBSTR( cBaseRec(a).val_tele_traa,1,50),                         
                         DESC_NAC = cBaseRec(a).val_naci,
                         COD_SEXO = cBaseRec(a).val_sexo,
                         val_ocr_tdoc = cBaseRec(a).tipo_docu_iden,
                         VAL_OCR_ESTA = cBaseRec(a).val_esta_civi,
                         DESC_TIPO_PERSONA = cBaseRec(a).tipo_pers,
                         DESC_ORIGEN_INGRESO = cBaseRec(a).val_orig_ingr,
                         VAL_NOM_BARR = cBaseRec(a).val_nomb_barri,
                         VAL_NOM_MANZ = cBaseRec(a).val_manz_letra,
                         VAL_ETA_CONJ = cBaseRec(a).val_etap_conj,
                         VAL_CAL_PRIN = cBaseRec(a).val_call_princ,
                         VAL_CAL_SECU = cBaseRec(a).val_call_secu
                         
                   WHERE cod_clie = cBaseRec(a).cod_clie
                       ;
            END;
            
         END LOOP;
         COMMIT;
       END IF;
       EXIT WHEN c_base%NOTFOUND;
     END LOOP;
    CLOSE c_base;

   EXCEPTION
    WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR ZON_PR_REPOR_DIREC_CONSU_2: '||ls_sqlerrm);
  END ZON_PR_REPOR_DIREC_CONSU;


/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte GIZ Direcciones
                    Consultoras
Fecha Creacion    : 22/08/2013
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE ZON_PR_REPOR_DIREC_CONSU_CSV(
    psCodigoPais            VARCHAR2,
    psNombreArchivo         VARCHAR2,
    psTitulo                VARCHAR2,
    psTitulo2               VARCHAR2,
    psEstrucGeopo1          VARCHAR2,
    psEstrucGeopo2          VARCHAR2,
    psEstrucGeopo3          VARCHAR2,
    psCondicionUA           VARCHAR2,
    psDirectorio       OUT  VARCHAR2
    )
IS

   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   W_FILAS             NUMBER := 5000 ;
   v_handle            UTL_FILE.FILE_TYPE;
   W_DESC              VARCHAR2(200);
   lsLinea             VARCHAR2(4000);
   lsNombreArchivo     VARCHAR2(50);
   lsCodigoPais        VARCHAR2(3);
   lsSelect            VARCHAR2(4000);

   TYPE interfazTab  IS TABLE OF ZON_REPOR_DIREC_CONSU%ROWTYPE ;
   interfazRecord interfazTab;
   lbAbrirUtlFile  BOOLEAN;
   c_interfaz      SYS_REFCURSOR;
   lsTitulo        VARCHAR2(4000);
   v_para varchar2(150);
BEGIN
  lbAbrirUtlFile := TRUE;
  EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
  lsSelect := 'SELECT * FROM ZON_REPOR_DIREC_CONSU ';
  IF psCondicionUA IS NOT NULL THEN
     lsSelect := lsSelect||psCondicionUA;
  END IF;
  lsTitulo := psTitulo||', '||psEstrucGeopo1||', '||psEstrucGeopo2||', '||psEstrucGeopo3||', '||psTitulo2;

  BEGIN
           SELECT val_para INTO v_para
           FROM BAS_PARAM_PAIS
           WHERE COD_SIST = 'MAE'
           AND NOM_PARA = 'indCamposAdicionales'
           AND cod_pais = pscodigopais;
   EXCEPTION WHEN NO_DATA_FOUND THEN
          v_para:=NULL;
   END;

  OPEN c_interfaz FOR lsSelect;
  LOOP
   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
   IF lbAbrirUtlFile THEN
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.csv', lsTitulo, lsDirTempo, V_HANDLE);
      psDirectorio := lsDirTempo;
      lbAbrirUtlFile := FALSE;
   END IF ;
   IF interfazRecord.COUNT > 0 THEN
      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
      
       IF (v_para = '1' ) THEN
         lsLinea :=
                    interfazRecord(x).cod_pais ||','||
                    interfazRecord(x).cod_marc ||','||
                    interfazRecord(x).cod_cana ||','||
                    '=T("'||interfazRecord(x).cod_regi||'")' ||','||
                    '=T("'||interfazRecord(x).cod_zona||'")' ||','||
                    interfazRecord(x).cod_secc ||','||
                    '=T("'||interfazRecord(x).cod_terr||'")' ||','||
                    interfazRecord(x).ind_ctrl_inte_geor ||','||
                    '=T("'||interfazRecord(x).cod_clie||'")' ||','||
                    '=T("'||interfazRecord(x).num_docu_iden||'")' ||','||
                    interfazRecord(x).val_ape1 ||','||
                    interfazRecord(x).ape_mate ||','||
                    interfazRecord(x).nom_clie ||','||
                    interfazRecord(x).fec_cont ||','||
                    interfazRecord(x).cod_peri ||','||
                    interfazRecord(x).ult_pedi ||','||
                    interfazRecord(x).mon_cata ||','||
                    interfazRecord(x).mon_neto ||','||
                    interfazRecord(x).sal_tota ||','||
                    '=T("'||interfazRecord(x).cod_esta_clie||'")' ||','||
                    interfazRecord(x).des_esta ||','||
                    '=T("'||interfazRecord(x).cod_tipo_via||'")' ||','||
                    interfazRecord(x).des_abrv_tipo_via ||','||
                    interfazRecord(x).val_nomb_via ||','||
                    interfazRecord(x).num_ppal ||','||
                    interfazRecord(x).val_obse ||','||
                    interfazRecord(x).val_barr ||','||
                    '=T("'||interfazRecord(x).cod_unid_geog||'")' ||','||
                    interfazRecord(x).des_dept ||','||
                    interfazRecord(x).des_prov ||','||
                    interfazRecord(x).des_dist ||','||
                    interfazRecord(x).cod_tcpp ||','||
                    interfazRecord(x).des_tcpp ||','||
                    interfazRecord(x).des_cenp ||','||
                    interfazRecord(x).val_tele_casa ||','||
                    interfazRecord(x).val_tele_celu ||','||
                    interfazRecord(x).val_tele_traa  ||','||
                    
                    interfazRecord(x).DESC_NAC ||','||
                    interfazRecord(x).COD_SEXO ||','||
                    interfazRecord(x).val_ocr_tdoc ||','||
                    interfazRecord(x).VAL_OCR_ESTA ||','||
                    interfazRecord(x).DESC_TIPO_PERSONA ||','||
                    interfazRecord(x).DESC_ORIGEN_INGRESO ||','||
                    interfazRecord(x).VAL_NOM_BARR ||','||
                    interfazRecord(x).VAL_NOM_MANZ ||','||
                    interfazRecord(x).VAL_ETA_CONJ ||','||
                    interfazRecord(x).VAL_CAL_PRIN ||','||
                    interfazRecord(x).VAL_CAL_SECU ||','||
                    
                    nvl(interfazRecord(x).VAL_NOM_BARR,'') || ' ' ||
                    nvl(interfazRecord(x).VAL_NOM_MANZ,'') || ' ' ||
                    nvl(interfazRecord(x).VAL_ETA_CONJ,'') || ' ' ||
                    nvl(interfazRecord(x).VAL_CAL_PRIN,'') || ' ' ||
                    nvl(interfazRecord(x).VAL_CAL_SECU,'') || ' ' ||
                    nvl(interfazRecord(x).VAL_OBSE,'') 
                     ;
         ELSE
           lsLinea :=
                    interfazRecord(x).cod_pais ||','||
                    interfazRecord(x).cod_marc ||','||
                    interfazRecord(x).cod_cana ||','||
                    '=T("'||interfazRecord(x).cod_regi||'")' ||','||
                    '=T("'||interfazRecord(x).cod_zona||'")' ||','||
                    interfazRecord(x).cod_secc ||','||
                    '=T("'||interfazRecord(x).cod_terr||'")' ||','||
                    interfazRecord(x).ind_ctrl_inte_geor ||','||
                    '=T("'||interfazRecord(x).cod_clie||'")' ||','||
                    '=T("'||interfazRecord(x).num_docu_iden||'")' ||','||
                    interfazRecord(x).val_ape1 ||','||
                    interfazRecord(x).ape_mate ||','||
                    interfazRecord(x).nom_clie ||','||
                    interfazRecord(x).fec_cont ||','||
                    interfazRecord(x).cod_peri ||','||
                    interfazRecord(x).ult_pedi ||','||
                    interfazRecord(x).mon_cata ||','||
                    interfazRecord(x).mon_neto ||','||
                    interfazRecord(x).sal_tota ||','||
                    '=T("'||interfazRecord(x).cod_esta_clie||'")' ||','||
                    interfazRecord(x).des_esta ||','||
                    '=T("'||interfazRecord(x).cod_tipo_via||'")' ||','||
                    interfazRecord(x).des_abrv_tipo_via ||','||
                    interfazRecord(x).val_nomb_via ||','||
                    interfazRecord(x).num_ppal ||','||
                    interfazRecord(x).val_obse ||','||
                    interfazRecord(x).val_barr ||','||
                    '=T("'||interfazRecord(x).cod_unid_geog||'")' ||','||
                    interfazRecord(x).des_dept ||','||
                    interfazRecord(x).des_prov ||','||
                    interfazRecord(x).des_dist ||','||
                    interfazRecord(x).cod_tcpp ||','||
                    interfazRecord(x).des_tcpp ||','||
                    interfazRecord(x).des_cenp ||','||
                    interfazRecord(x).val_tele_casa ||','||
                    interfazRecord(x).val_tele_celu ||','||
                    interfazRecord(x).val_tele_traa  
                    
                     ;
         
       END IF;        
      
          

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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR ZON_PR_REPOR_DIREC_CONSU_CSV: '||ls_sqlerrm);
END ZON_PR_REPOR_DIREC_CONSU_CSV;

/**************************************************************************
  Descripcion           : Devuelve Ubigeo del DomicilioEntrega del cliente
  Fecha Creacion        : 03/09/2013
  Parametros Entrada    :
        vsOidClie       : Codigo Cliente
  Autor : Juan Altamirano.
  ***************************************************************************/
 FUNCTION fn_retu_ubi_domi_entr(vsOidClie NUMBER) RETURN VARCHAR2 IS
    val_ubig VARCHAR2(256);
  BEGIN

  SELECT
			nivel_1
           || DECODE (nivel_2, NULL, '', '/' || nivel_2)
           || DECODE (nivel_3, NULL, '', '/' || nivel_3)
           || DECODE (nivel_4, NULL, '', '/' || nivel_4)
           || DECODE (nivel_5, NULL, '', '/' || nivel_5)
           || DECODE (nivel_6, NULL, '', '/' || nivel_6)
           || DECODE (nivel_7, NULL, '', '/' || nivel_7)
           || DECODE (nivel_8, NULL, '', '/' || nivel_8)
           || DECODE (nivel_9, NULL, '', '/' || nivel_9)  INTO val_ubig

      FROM (SELECT
                     (SELECT DES_CIUD FROM ZON_CIUDA WHERE COD_UGEO_REGI = a.CIUD_COD_UGEO_REGI AND COD_CIUD = a.CIUD_COD_CIUD) DES_CIUD,
                         a.DES_VILLA_POBL,
                     (SELECT des_geog
                        FROM zon_valor_estru_geopo
                       WHERE pais_oid_pais = d.pais_oid_pais
                         AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
                         AND orde_2 IS NULL) AS nivel_1,
                     (SELECT des_geog
                        FROM zon_valor_estru_geopo
                       WHERE pais_oid_pais = d.pais_oid_pais
                         AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
                         AND orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
                         AND orde_3 IS NULL) AS nivel_2,
                     (SELECT des_geog
                        FROM zon_valor_estru_geopo
                       WHERE pais_oid_pais = d.pais_oid_pais
                         AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
                         AND orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
                         AND orde_3 = SUBSTR (a.cod_unid_geog, 13, 6)
                         AND orde_4 IS NULL) AS nivel_3,
                     CASE
                        WHEN LENGTH (a.cod_unid_geog) > 18
                           THEN (SELECT des_geog
                                   FROM zon_valor_estru_geopo
                                  WHERE pais_oid_pais =
                                                       d.pais_oid_pais
                                    AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
                                    AND orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
                                    AND orde_3 = SUBSTR (a.cod_unid_geog, 13, 6)
                                    AND orde_4 = SUBSTR (a.cod_unid_geog, 19, 6)
                                    AND orde_5 IS NULL)
                        ELSE NULL
                     END AS nivel_4,
                     CASE
                        WHEN LENGTH (a.cod_unid_geog) > 24
                           THEN (SELECT des_geog
                                   FROM zon_valor_estru_geopo
                                  WHERE pais_oid_pais =
                                                       d.pais_oid_pais
                                    AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
                                    AND orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
                                    AND orde_3 = SUBSTR (a.cod_unid_geog, 13, 6)
                                    AND orde_4 = SUBSTR (a.cod_unid_geog, 19, 6)
                                    AND orde_5 = SUBSTR (a.cod_unid_geog, 25, 6)
                                    AND orde_6 IS NULL)
                        ELSE NULL
                     END AS nivel_5,
                     CASE
                        WHEN LENGTH (a.cod_unid_geog) > 30
                           THEN (SELECT des_geog
                                   FROM zon_valor_estru_geopo
                                  WHERE pais_oid_pais =
                                                       d.pais_oid_pais
                                    AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
                                    AND orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
                                    AND orde_3 = SUBSTR (a.cod_unid_geog, 13, 6)
                                    AND orde_4 = SUBSTR (a.cod_unid_geog, 19, 6)
                                    AND orde_5 = SUBSTR (a.cod_unid_geog, 25, 6)
                                    AND orde_6 = SUBSTR (a.cod_unid_geog, 31, 6)
                                    AND orde_7 IS NULL)
                        ELSE NULL
                     END AS nivel_6,
                     CASE
                        WHEN LENGTH (a.cod_unid_geog) > 36
                           THEN (SELECT des_geog
                                   FROM zon_valor_estru_geopo
                                  WHERE pais_oid_pais =
                                                       d.pais_oid_pais
                                    AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
                                    AND orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
                                    AND orde_3 = SUBSTR (a.cod_unid_geog, 13, 6)
                                    AND orde_4 = SUBSTR (a.cod_unid_geog, 19, 6)
                                    AND orde_5 = SUBSTR (a.cod_unid_geog, 25, 6)
                                    AND orde_6 = SUBSTR (a.cod_unid_geog, 31, 6)
                                    AND orde_7 = SUBSTR (a.cod_unid_geog, 37, 6)
                                    AND orde_8 IS NULL)
                        ELSE NULL
                     END AS nivel_7,
                     CASE
                        WHEN LENGTH (a.cod_unid_geog) > 42
                           THEN (SELECT des_geog
                                   FROM zon_valor_estru_geopo
                                  WHERE pais_oid_pais =
                                                       d.pais_oid_pais
                                    AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
                                    AND orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
                                    AND orde_3 = SUBSTR (a.cod_unid_geog, 13, 6)
                                    AND orde_4 = SUBSTR (a.cod_unid_geog, 19, 6)
                                    AND orde_5 = SUBSTR (a.cod_unid_geog, 25, 6)
                                    AND orde_6 = SUBSTR (a.cod_unid_geog, 31, 6)
                                    AND orde_7 = SUBSTR (a.cod_unid_geog, 37, 6)
                                    AND orde_8 = SUBSTR (a.cod_unid_geog, 43, 6)
                                    AND orde_9 IS NULL)
                        ELSE NULL
                     END AS nivel_8,
                     CASE
                        WHEN LENGTH (a.cod_unid_geog) > 48
                           THEN (SELECT des_geog
                                   FROM zon_valor_estru_geopo
                                  WHERE pais_oid_pais =
                                                       d.pais_oid_pais
                                    AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
                                    AND orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
                                    AND orde_3 = SUBSTR (a.cod_unid_geog, 13, 6)
                                    AND orde_4 = SUBSTR (a.cod_unid_geog, 19, 6)
                                    AND orde_5 = SUBSTR (a.cod_unid_geog, 25, 6)
                                    AND orde_6 = SUBSTR (a.cod_unid_geog, 31, 6)
                                    AND orde_7 = SUBSTR (a.cod_unid_geog, 37, 6)
                                    AND orde_8 = SUBSTR (a.cod_unid_geog, 43, 6)
                                    AND orde_9 = SUBSTR (a.cod_unid_geog, 49, 6))
                        ELSE NULL
                     END AS nivel_9
                FROM MAE_CLIEN_DIREC a,
                     MAE_TIPO_DIREC b,
                     SEG_TIPO_VIA c,
                     MAE_CLIEN d,
                     ZON_TERRI t
               WHERE d.OID_CLIE = vsOidClie
                 AND d.OID_CLIE = a.CLIE_OID_CLIE
                 AND a.IND_ELIM = 0
                 AND b.OID_TIPO_DIRE = a.TIDC_OID_TIPO_DIRE
                 AND c.OID_TIPO_VIA = a.TIVI_OID_TIPO_VIA
                 AND b.COD_TIPO_DIRE = '07'
                     AND a.TERR_OID_TERR = t.OID_TERR (+)
            ORDER BY a.OID_CLIE_DIRE DESC)
     WHERE ROWNUM = 1;

    RETURN val_ubig;
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
         val_ubig := '';
         RETURN val_ubig;


  END fn_retu_ubi_domi_entr;


/**************************************************************************
  Descripcion           : Devuelve Ubigeo del DomicilioEntrega del cliente
  Fecha Creacion        : 03/09/2013
  Parametros Entrada    :
        vsOidClie       : Codigo Cliente
  Autor : Juan Altamirano.
  ***************************************************************************/
 FUNCTION fn_retu_ubi_domi(vsOidClie NUMBER) RETURN VARCHAR2 IS
    val_ubig VARCHAR2(256);
  BEGIN
  SELECT
		       nivel_1
		       || DECODE (nivel_2, NULL, '', '/' || nivel_2)
		       || DECODE (nivel_3, NULL, '', '/' || nivel_3)
		       || DECODE (nivel_4, NULL, '', '/' || nivel_4)
		       || DECODE (nivel_5, NULL, '', '/' || nivel_5)
		       || DECODE (nivel_6, NULL, '', '/' || nivel_6)
		       || DECODE (nivel_7, NULL, '', '/' || nivel_7)
		       || DECODE (nivel_8, NULL, '', '/' || nivel_8)
		       || DECODE (nivel_9, NULL, '', '/' || nivel_9) INTO val_ubig

		  FROM (SELECT
						(SELECT DES_CIUD FROM ZON_CIUDA WHERE COD_UGEO_REGI = a.CIUD_COD_UGEO_REGI AND COD_CIUD = a.CIUD_COD_CIUD) DES_CIUD,
                          a.DES_VILLA_POBL,
		                 (SELECT des_geog
		                    FROM zon_valor_estru_geopo
		                   WHERE pais_oid_pais = d.pais_oid_pais
		                     AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
		                     AND orde_2 IS NULL) AS nivel_1,
		                 (SELECT des_geog
		                    FROM zon_valor_estru_geopo
		                   WHERE pais_oid_pais = d.pais_oid_pais
		                     AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
		                     AND orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
		                     AND orde_3 IS NULL) AS nivel_2,
		                 (SELECT des_geog
		                    FROM zon_valor_estru_geopo
		                   WHERE pais_oid_pais = d.pais_oid_pais
		                     AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
		                     AND orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
		                     AND orde_3 = SUBSTR (a.cod_unid_geog, 13, 6)
		                     AND orde_4 IS NULL) AS nivel_3,
		                 CASE
		                    WHEN LENGTH (a.cod_unid_geog) > 18
		                       THEN (SELECT des_geog
		                               FROM zon_valor_estru_geopo
		                              WHERE pais_oid_pais =
		                                                   d.pais_oid_pais
		                                AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
		                                AND orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
		                                AND orde_3 = SUBSTR (a.cod_unid_geog, 13, 6)
		                                AND orde_4 = SUBSTR (a.cod_unid_geog, 19, 6)
		                                AND orde_5 IS NULL)
		                    ELSE NULL
		                 END AS nivel_4,
		                 CASE
		                    WHEN LENGTH (a.cod_unid_geog) > 24
		                       THEN (SELECT des_geog
		                               FROM zon_valor_estru_geopo
		                              WHERE pais_oid_pais =
		                                                   d.pais_oid_pais
		                                AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
		                                AND orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
		                                AND orde_3 = SUBSTR (a.cod_unid_geog, 13, 6)
		                                AND orde_4 = SUBSTR (a.cod_unid_geog, 19, 6)
		                                AND orde_5 = SUBSTR (a.cod_unid_geog, 25, 6)
		                                AND orde_6 IS NULL)
		                    ELSE NULL
		                 END AS nivel_5,
		                 CASE
		                    WHEN LENGTH (a.cod_unid_geog) > 30
		                       THEN (SELECT des_geog
		                               FROM zon_valor_estru_geopo
		                              WHERE pais_oid_pais =
		                                                   d.pais_oid_pais
		                                AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
		                                AND orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
		                                AND orde_3 = SUBSTR (a.cod_unid_geog, 13, 6)
		                                AND orde_4 = SUBSTR (a.cod_unid_geog, 19, 6)
		                                AND orde_5 = SUBSTR (a.cod_unid_geog, 25, 6)
		                                AND orde_6 = SUBSTR (a.cod_unid_geog, 31, 6)
		                                AND orde_7 IS NULL)
		                    ELSE NULL
		                 END AS nivel_6,
		                 CASE
		                    WHEN LENGTH (a.cod_unid_geog) > 36
		                       THEN (SELECT des_geog
		                               FROM zon_valor_estru_geopo
		                              WHERE pais_oid_pais =
		                                                   d.pais_oid_pais
		                                AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
		                                AND orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
		                                AND orde_3 = SUBSTR (a.cod_unid_geog, 13, 6)
		                                AND orde_4 = SUBSTR (a.cod_unid_geog, 19, 6)
		                                AND orde_5 = SUBSTR (a.cod_unid_geog, 25, 6)
		                                AND orde_6 = SUBSTR (a.cod_unid_geog, 31, 6)
		                                AND orde_7 = SUBSTR (a.cod_unid_geog, 37, 6)
		                                AND orde_8 IS NULL)
		                    ELSE NULL
		                 END AS nivel_7,
		                 CASE
		                    WHEN LENGTH (a.cod_unid_geog) > 42
		                       THEN (SELECT des_geog
		                               FROM zon_valor_estru_geopo
		                              WHERE pais_oid_pais =
		                                                   d.pais_oid_pais
		                                AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
		                                AND orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
		                                AND orde_3 = SUBSTR (a.cod_unid_geog, 13, 6)
		                                AND orde_4 = SUBSTR (a.cod_unid_geog, 19, 6)
		                                AND orde_5 = SUBSTR (a.cod_unid_geog, 25, 6)
		                                AND orde_6 = SUBSTR (a.cod_unid_geog, 31, 6)
		                                AND orde_7 = SUBSTR (a.cod_unid_geog, 37, 6)
		                                AND orde_8 = SUBSTR (a.cod_unid_geog, 43, 6)
		                                AND orde_9 IS NULL)
		                    ELSE NULL
		                 END AS nivel_8,
		                 CASE
		                    WHEN LENGTH (a.cod_unid_geog) > 48
		                       THEN (SELECT des_geog
		                               FROM zon_valor_estru_geopo
		                              WHERE pais_oid_pais =
		                                                   d.pais_oid_pais
		                                AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
		                                AND orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
		                                AND orde_3 = SUBSTR (a.cod_unid_geog, 13, 6)
		                                AND orde_4 = SUBSTR (a.cod_unid_geog, 19, 6)
		                                AND orde_5 = SUBSTR (a.cod_unid_geog, 25, 6)
		                                AND orde_6 = SUBSTR (a.cod_unid_geog, 31, 6)
		                                AND orde_7 = SUBSTR (a.cod_unid_geog, 37, 6)
		                                AND orde_8 = SUBSTR (a.cod_unid_geog, 43, 6)
		                                AND orde_9 = SUBSTR (a.cod_unid_geog, 49, 6))
		                    ELSE NULL
		                 END AS nivel_9
		            FROM MAE_CLIEN_DIREC a,
		                 MAE_TIPO_DIREC b,
		                 SEG_TIPO_VIA c,
		                 MAE_CLIEN d,
		                 ZON_TERRI t
		           WHERE d.OID_CLIE = vsOidClie
		             AND d.OID_CLIE = a.CLIE_OID_CLIE
		             AND a.IND_ELIM = 0
		             AND b.OID_TIPO_DIRE = a.TIDC_OID_TIPO_DIRE
		             AND c.OID_TIPO_VIA = a.TIVI_OID_TIPO_VIA
		             AND a.IND_DIRE_PPAL  = 1
		             AND a.TERR_OID_TERR = t.OID_TERR (+)
		        ORDER BY a.OID_CLIE_DIRE DESC)
		 WHERE ROWNUM = 1;

    RETURN val_ubig;
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
         val_ubig := '';
         RETURN val_ubig;


  END fn_retu_ubi_domi;

/***************************************************************************
Descripcion       : Devuelve la direccion de DOMICILIO/DESPACHO para los reportes de DV
Parametros  :
                    psCodigoCliente: OID DEL CLIENTE
                    psTipoDireccion: Tipo, 1=DOMICILIO, 2=DESPACHO
Fecha Creacion    : 11/09/2013
Autor             : Ivan Tocto Jaimes
***************************************************************************/
FUNCTION ZON_FN_DEVUE_DIREC_CONSU
  (
    psCodigoCliente    VARCHAR2,
    psTipoDireccion VARCHAR2
  ) RETURN VARCHAR2
IS
    val_dire VARCHAR2(1024);
BEGIN

    IF psTipoDireccion = '1' THEN

               SELECT DIRE
               INTO val_dire
               FROM(
                    SELECT (NVL(TRIM(C.DES_ABRV_TIPO_VIA),' ') ||' '||  NVL(TRIM(A.VAL_NOMB_VIA),' ') ) ||' '|| TRIM(A.NUM_PPAL)||' '||TRIM(A.VAL_OBSE) DIRE
                        FROM MAE_CLIEN_DIREC a,
                        MAE_TIPO_DIREC b,
                        SEG_TIPO_VIA c,
                        MAE_CLIEN d
                        WHERE d.OID_CLIE = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(psCodigoCliente)
                        AND d.OID_CLIE = a.CLIE_OID_CLIE
                        AND a.IND_ELIM = 0
                        AND b.OID_TIPO_DIRE = a.TIDC_OID_TIPO_DIRE
                        AND c.OID_TIPO_VIA = a.TIVI_OID_TIPO_VIA
                        AND a.IND_DIRE_PPAL  = 1
                        ORDER BY a.OID_CLIE_DIRE DESC)
                WHERE ROWNUM = 1;

    ELSIF psTipoDireccion = '2' THEN

               SELECT DIRE
               INTO val_dire
               FROM(
                      SELECT  (NVL(TRIM(c.DES_ABRV_TIPO_VIA),' ') ||' '|| NVL(TRIM(A.VAL_NOMB_VIA),' ') ) ||' '||TRIM(A.NUM_PPAL)||' '||TRIM(A.VAL_OBSE) DIRE
                            FROM MAE_CLIEN_DIREC a,
                                 MAE_TIPO_DIREC b,
                                 SEG_TIPO_VIA c,
                                 MAE_CLIEN d
                           WHERE d.OID_CLIE = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(psCodigoCliente)
                             AND d.OID_CLIE = a.CLIE_OID_CLIE
                             AND a.IND_ELIM = 0
                             AND b.OID_TIPO_DIRE = a.TIDC_OID_TIPO_DIRE
                             AND c.OID_TIPO_VIA = a.TIVI_OID_TIPO_VIA
                             AND b.COD_TIPO_DIRE = '07'
                        ORDER BY a.OID_CLIE_DIRE DESC)
                WHERE ROWNUM = 1;

    END IF;

    return val_dire;

END ZON_FN_DEVUE_DIREC_CONSU;

END ZON_PKG_UNIDA_GEOGR;
/
