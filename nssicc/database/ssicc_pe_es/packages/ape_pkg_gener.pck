create or replace package APE_PKG_GENER is

  -- Author  : PEEXTDOLIVA
  -- Created : 30/03/2009 11:04:24 a.m.
  -- Purpose : Funciones genericas del modulo APE

  /**************************************************************************
  Descripcion       : Devuelve el valor del parametro de la tabla
                      APE_PARAM_GENER
   Fecha Creacion    : 30/03/2009
  Parametros Entrada:
      PS_COD_PAIS   : Codigo de pais
      PS_COD_PARAM   : Codigo de parametro
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  FUNCTION ape_fn_obten_param
  (
    pscodigopais      VARCHAR2,
    pscodigoparametro VARCHAR2
  ) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion       : Devuelve el tipo de consultora
   Fecha Creacion    : 29/04/2009
  Parametros Entrada:
      PS_COD_PAIS   : Codigo de pais
      PS_COD_PARAM   : Codigo de parametro
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  FUNCTION ape_fn_obten_tipo_consu
  (
    pscodigopais      VARCHAR2,
    psfechaproceso    VARCHAR2,
    psoidcliente      VARCHAR2
  ) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion       : Devuelve la descripción de una tabla. Para esta funcion
                      solo se trabaja con la tabla GEN_I18N_SICC_PAIS
  Fecha Creacion    : 18/06/2010
  Parametros Entrada:
      vchNOMTABLA    : Nombre de la Tabla
      intVALOID      : Campo Oid de la Tabla
      vchTIPOGEN     : Valor que indica la tabla de Idioma a utilizar
  Autor              : Nicolás López
  ***************************************************************************/
  FUNCTION APE_FN_OBTEN_DESCRIP_IDIOMA
  (
    vchNOMTABLA   IN VARCHAR2, -- Nombre de la Tabla a obtener su Descripción
    intVALOID     IN NUMBER,   -- Campo Oid de la Tabla a obtener su Descripción
    vchTIPOGEN    IN VARCHAR2  -- Indicador (Flag) que determina la tabla de Idioma
                               -- correspondiente a utilizar. Valor (P): GEN_I18N_SICC_PAIS,
                               --                             Valor (C): GEN_I18N_SICC_COMUN.
  )  RETURN VARCHAR2;


end APE_PKG_GENER;
/

create or replace package body APE_PKG_GENER is

CLASIFICACION_ESTRELLA CONSTANT VARCHAR2(50)      := 'APE_CLAS_ESTR';
CLASIFICACION_CIRCULO_HONOR CONSTANT VARCHAR2(50) := 'APE_CLAS_CIRC_HONO';

FLAG_ESTRELLA			                 CONSTANT VARCHAR2(1) := '0';
FLAG_CIRCULO_DE_HONOR	             CONSTANT VARCHAR2(1) := '0';
FLAG_PRIMER_PEDIDO		             CONSTANT VARCHAR2(1) := '1';
FLAG_ESTRELLA_REINCIDENTE          CONSTANT VARCHAR2(1) := '2';
FLAG_CIRCULO_DE_HONOR_REINCI       CONSTANT VARCHAR2(1) := '3';
FLAG_REINCIDENTE			             CONSTANT VARCHAR2(1) := '4';
FLAG_TODAS_LAS_DEMAS		           CONSTANT VARCHAR2(1) := '5';

/**************************************************************************
  Descripcion        : Devuelve el valor del parametro de la tabla
                       APE_PARAM_GENER
  Fecha Creacion     : 30/03/2009
  Parametros Entrada
  psCodigoPais       : Codigo de pais
  psCodigoParametro  : Codigo de parametro
  Autor              : Dennys Oliva Iriarte
  ***************************************************************************/
  FUNCTION ape_fn_obten_param
  (
    pscodigopais      VARCHAR2,
    pscodigoparametro VARCHAR2
  ) RETURN VARCHAR2 IS

    lsvalor VARCHAR2(15);

  BEGIN

    SELECT a.val_param
      INTO lsvalor
      FROM ape_param_gener a
     WHERE a.cod_pais = pscodigopais
       AND a.cod_para = pscodigoparametro;

    RETURN lsvalor;

  EXCEPTION
    WHEN no_data_found THEN
      RETURN '';
    WHEN OTHERS THEN
      RETURN '';

  END ape_fn_obten_param;

  /**************************************************************************
  Descripcion       : Devuelve el tipo de consultora
   Fecha Creacion    : 29/04/2009
  Parametros Entrada:
      PS_COD_PAIS   : Codigo de pais
      PS_COD_PARAM   : Codigo de parametro
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  FUNCTION ape_fn_obten_tipo_consu
  (
    pscodigopais      VARCHAR2,
    psfechaproceso    VARCHAR2,
    psoidcliente      VARCHAR2
  )  RETURN VARCHAR2 IS

    lsvalor        number;
    lrresult       varchar2(1);
    isreincidente  boolean := false;
    isprimerpedido boolean := false;
    isestrella     boolean := false;
    iscirculohonor boolean := false;
    lsvalorParam   VARCHAR2(15);
  BEGIN
    lsvalorParam := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,'STO_DPFM');

    -- Determinar si una consultora es reincidente
    SELECT COUNT(1)
     INTO lsvalor
     FROM rec_cabec_recla a,
          rec_opera_recla b,
          rec_tipos_opera e,
          rec_opera       f
    WHERE a.oid_cabe_recl = b.care_oid_cabe_recl
      AND b.tiop_oid_tipo_oper = e.oid_tipo_oper
      AND e.rope_oid_oper = f.oid_oper
      AND f.ind_falt_merc = 1
      AND a.clie_oid_clie = psoidcliente
      AND trunc(to_date(psfechaproceso,'dd/MM/YYYY')) - a.fec_ingr <= TO_NUMBER(lsvalorParam);

      IF lsvalor >= sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,'STO_NUM_FM') then
         isreincidente := true;
      end if;

      -- Determinar si una consultora es Primer Pedido
       Select count(1)
         INTO lsvalor
         from mae_clien_datos_adici
        where clie_oid_clie = psoidcliente
          and esta_oid_esta_clie=1;

       IF lsvalor >= 0 then
          isprimerpedido := true;
       end if;

       -- Determina si la consultora es estrella
       lsvalorParam := ape_fn_obten_param(pscodigopais,CLASIFICACION_ESTRELLA);
       select count(1)
        INTO lsvalor
        from mae_clien_tipo_subti a,
             mae_clien_clasi b
        where a.CLIE_OID_CLIE = psoidcliente
          and a.OID_CLIE_TIPO_SUBT = b.CTSU_OID_CLIE_TIPO_SUBT
          and b.TCCL_OID_TIPO_CLASI = TO_NUMBER(lsvalorParam);

       IF lsvalor >= 0 then
          isestrella := true;
       end if;

       -- Determina si la consultora es circulo de honor
       lsvalorParam := ape_fn_obten_param(pscodigopais,CLASIFICACION_CIRCULO_HONOR);
       select count(1)
        INTO lsvalor
        from mae_clien_tipo_subti a,
             mae_clien_clasi b
        where a.CLIE_OID_CLIE = psoidcliente
          and a.OID_CLIE_TIPO_SUBT = b.CTSU_OID_CLIE_TIPO_SUBT
          and b.TCCL_OID_TIPO_CLASI = TO_NUMBER(lsvalorParam);

       IF lsvalor >= 0 then
          iscirculohonor := true;
       end if;

       IF isreincidente = true THEN
          lrresult := FLAG_REINCIDENTE;
          IF isestrella = true THEN
             lrresult := FLAG_ESTRELLA_REINCIDENTE;
          ELSE
             IF iscirculohonor = true THEN
                lrresult := FLAG_CIRCULO_DE_HONOR_REINCI;
             END IF;
          END IF;
       ELSE
          IF isestrella = true THEN
             lrresult := FLAG_ESTRELLA;
          ELSE
              IF iscirculohonor = true THEN
                 lrresult := FLAG_CIRCULO_DE_HONOR;
              ELSE
                 IF isprimerpedido = true THEN
                    lrresult := FLAG_PRIMER_PEDIDO;
                 ELSE
                    lrresult := FLAG_TODAS_LAS_DEMAS;
                 END IF;
              END IF;
          END IF;
       END IF;

    RETURN lrresult;

  EXCEPTION
    WHEN no_data_found THEN
      RETURN '';
    WHEN OTHERS THEN
      RETURN '';

  END ape_fn_obten_tipo_consu;

  /**************************************************************************
  Descripcion       : Devuelve la descripción de una tabla. Para esta funcion
                      solo se trabaja con la tabla GEN_I18N_SICC_PAIS
  Fecha Creacion    : 18/06/2010
  Parametros Entrada:
      vchNOMTABLA    : Nombre de la Tabla
      intVALOID      : Campo Oid de la Tabla
      vchTIPOGEN     : Valor que indica la tabla de Idioma a utilizar
  Autor              : Nicolás López
  ***************************************************************************/
  FUNCTION APE_FN_OBTEN_DESCRIP_IDIOMA
  (
    vchNOMTABLA   IN VARCHAR2, -- Nombre de la Tabla a obtener su Descripción
    intVALOID     IN NUMBER,   -- Campo Oid de la Tabla a obtener su Descripción
    vchTIPOGEN    IN VARCHAR2  -- Indicador (Flag) que determina la tabla de Idioma
                               -- correspondiente a utilizar. Valor (P): GEN_I18N_SICC_PAIS,
                               --                             Valor (C): GEN_I18N_SICC_COMUN.
  )  RETURN VARCHAR2 IS

    V_vch_DESCRIPCION_P  GEN_I18N_SICC_PAIS.VAL_I18N%TYPE;  -- Variable temporal para obtener la descripción de la
                                                            -- tabla de idioma Pais (GEN_I18N_SICC_PAIS).
    V_vch_DESCRIPCION_C  GEN_I18N_SICC_COMUN.VAL_I18N%TYPE; -- Variable temporal para obtener la descripción de la
                                                            -- tabla de idioma Pais (GEN_I18N_SICC_COMUN).
  BEGIN
    V_vch_DESCRIPCION_P := '';
    V_vch_DESCRIPCION_C := '';

    -- Se realiza la busqueda y asignación de la descripcion en la variable temporal
    IF (vchTIPOGEN = 'P') THEN

      SELECT p.val_i18n INTO V_vch_DESCRIPCION_P
      FROM   gen_i18n_sicc_pais p
      WHERE  p.val_oid = intVALOID
      AND    p.attr_enti = vchNOMTABLA;

      RETURN V_vch_DESCRIPCION_P;

    END IF;

    IF (vchTIPOGEN = 'C') THEN

      SELECT c.val_i18n INTO V_vch_DESCRIPCION_C
      FROM   gen_i18n_sicc_comun c
      WHERE  c.val_oid = intVALOID
      AND    c.attr_enti = vchNOMTABLA;

      RETURN V_vch_DESCRIPCION_C;

    END IF;

  EXCEPTION
    WHEN no_data_found THEN
      RETURN '';
    WHEN OTHERS THEN
      RETURN '';

  END APE_FN_OBTEN_DESCRIP_IDIOMA;




end APE_PKG_GENER;
/

