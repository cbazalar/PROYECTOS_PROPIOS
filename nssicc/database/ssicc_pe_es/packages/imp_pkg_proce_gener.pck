CREATE OR REPLACE PACKAGE "IMP_PKG_PROCE_GENER" AS

/***************************************************************************
Descripcion       : Funcion que retorna el parametro de impresion de un
                    determinado proceso de impresion en base al nombre del
                    parametro, retorna NULL en caso no encuentre el valor.

Fecha Creacion    : 07/03/2008
Autor             : Carlos Hurtado
***************************************************************************/
FUNCTION IMP_FN_OBTIE_PARAM_IMPRE(psCodigoProceso VARCHAR2,
                                  psNombreParametro VARCHAR2) RETURN VARCHAR2;

/**************************************************************************
Descripcion         : Reemplaza una cadena por otra dentro de un CLOB.
Fecha Creación      : 20/11/2006
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Versión             : Final (Alfa|Final)
Cambios Importantes : Inclusión del comentario
***************************************************************************/
PROCEDURE IMP_PR_LOB_REPLACE(p_lob IN OUT CLOB,
                             p_what IN VARCHAR2,
                             p_with IN VARCHAR2);

/**************************************************************************
Descripcion         : Reemplaza una cadena por otra dentro de un CLOB buscando
                      a partir de la posicion pasada como parametro.
Fecha Creación      : 10/01/2007
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Versión             : Final (Alfa|Final)
Cambios Importantes : Inclusión del comentario
***************************************************************************/
PROCEDURE IMP_PR_LOB_REPLACE(p_lob IN OUT CLOB,
                             p_what IN VARCHAR2,
                             p_with IN VARCHAR2 ,
                             p_start NUMBER);

END;
/

CREATE OR REPLACE PACKAGE BODY "IMP_PKG_PROCE_GENER" AS

/***************************************************************************
Descripcion       : Funcion que retorna el parametro de impresion de un
                    determinado proceso de impresion en base al nombre del
                    parametro, retorna NULL en caso no encuentre el valor.

Fecha Creacion    : 07/03/2008
Autor             : Carlos Hurtado
***************************************************************************/
FUNCTION IMP_FN_OBTIE_PARAM_IMPRE(psCodigoProceso VARCHAR2,
                                  psNombreParametro VARCHAR2) RETURN VARCHAR2 IS

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

/**************************************************************************
Descripcion         : Reemplaza una cadena por otra dentro de un CLOB.
Fecha Creación      : 20/11/2006
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Versión             : Final (Alfa|Final)
Cambios Importantes : Inclusión del comentario
***************************************************************************/
PROCEDURE IMP_PR_LOB_REPLACE(p_lob IN OUT CLOB,
                             p_what IN VARCHAR2,
                             p_with IN VARCHAR2) IS

BEGIN
    -- Invocamos al procedimiento que recibe el
    -- parametro de posicion inicial con valor 1
    IMP_PR_LOB_REPLACE(p_lob, p_what, p_with, 1);
END;

/**************************************************************************
Descripcion         : Reemplaza una cadena por otra dentro de un CLOB buscando
                      a partir de la posicion pasada como parametro.
Fecha Creación      : 10/01/2007
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Versión             : Final (Alfa|Final)
Cambios Importantes : Inclusión del comentario
***************************************************************************/
PROCEDURE IMP_PR_LOB_REPLACE(p_lob IN OUT CLOB,
                             p_what IN VARCHAR2,
                             p_with IN VARCHAR2 ,
                             p_start NUMBER) IS

n    NUMBER;

BEGIN
    n := DBMS_LOB.INSTR(p_lob, p_what, p_start);
    IF( NVL(n, 0) > 0 ) THEN
        DBMS_LOB.COPY(p_lob,
                        p_lob,
                        DBMS_LOB.GETLENGTH(p_lob),
                        n + LENGTH(p_with),
                        n + LENGTH(p_what));

        DBMS_LOB.WRITE(p_lob, LENGTH(p_with), n, p_with);
        IF ( LENGTH(p_what) > LENGTH(p_with) ) THEN
            DBMS_LOB.TRIM(p_lob,
                           DBMS_LOB.GETLENGTH(p_lob) - (LENGTH(p_what) - LENGTH(p_with)));
        END IF;
    END IF;

END;

END;
/

