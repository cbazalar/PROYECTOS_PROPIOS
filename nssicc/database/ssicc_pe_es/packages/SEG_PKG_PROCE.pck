CREATE OR REPLACE PACKAGE SEG_PKG_PROCE IS

   W_FILAS                    NUMBER := 5000;
   ln_sqlcode                 NUMBER(10);
   ls_sqlerrm                 VARCHAR2(1000);


/**************************************************************************
Descripcion       : Proceso de Bloqueo y Eliminacion Masiva de Usuarios
Fecha Creacion    : 02/09/2015
Parametros Entrada:
  psCodigoPais    :  Codigo de pais

Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE SEG_PR_PROCE_BLOQU_ELIMI_MASIV(psCodigoPais           VARCHAR2);

/**************************************************************************
Descripcion       : Proceso de Bloqueo Manual de Usuarios
Fecha Creacion    : 02/09/2015
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE SEG_PR_PROCE_BLOQU_MANUA(
   psNroRCR VARCHAR2
   );

/**************************************************************************
Descripcion       : Proceso de DesBloqueo Manual de Usuarios
Fecha Creacion    : 02/09/2015
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE SEG_PR_PROCE_DBLOQ_MANUA(
   psNroRCR VARCHAR2
   );

/**************************************************************************
Descripcion       : Proceso de Eliminación Manual de Usuarios
Fecha Creacion    : 02/09/2015
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE SEG_PR_PROCE_ELIMI_MANUA(
   psNroRCR VARCHAR2
   );

END SEG_PKG_PROCE;
/
CREATE OR REPLACE PACKAGE BODY SEG_PKG_PROCE IS

 INTERVALO_MAXIMO_BLOQUEO NUMBER:= 30;
 INTERVALO_MAXIMO_ELIMINACION NUMBER:= 45;
 ESTADO_CODIGO_BLOQUEO VARCHAR2(1) := '3';

/**************************************************************************
Descripcion       : Proceso de Eliminacion de Usuarios
Fecha Creacion    : 02/09/2015
Parametros Entrada:
  psCodigoPais    : Codigo de pais
  psCodigoUsuario : Codigo de Usuario
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE SEG_PR_PROCE_ELIMI(
    psCodigoPais           VARCHAR2,
    psCodigoUsuario        VARCHAR2,
    psTipo                 VARCHAR2,
    psNroRCR               VARCHAR2:=NULL)
IS
    lnIntervaloDias  NUMBER;

BEGIN
      /* Aplicando Eliminación */
      BEGIN
       INSERT INTO ssicc_comun.SEG_USUAR_BORRA(
          cod_usua,
          pais_cod_pais,
          idio_cod_idio,
          nom_usua,
          ape_usua,
          use_usua,
          pas_usua,
          ema_usua,
          usu_digi,
          fec_digi,
          usu_modi,
          fec_modi,
          est_usua,
          val_ip,
          fec_ulti_ingr,
          cod_unic_belc,
          cen_cost,
          fec_ulti_modi_pass,
          val_inte_fall_pass,
          fec_bloq_segu,
          fec_elim_segu,
          ind_tipo_elim,
          nro_rcr_elim
          )
     SELECT cod_usua,
          pais_cod_pais,
          idio_cod_idio,
          nom_usua,
          ape_usua,
          use_usua,
          pas_usua,
          ema_usua,
          usu_digi,
          fec_digi,
          usu_modi,
          fec_modi,
          est_usua,
          val_ip,
          fec_ulti_ingr,
          cod_unic_belc,
          cen_cost,
          fec_ulti_modi_pass,
          val_inte_fall_pass,
          fec_bloq_segu,
          SYSDATE,
          psTipo,
          psNroRCR
     FROM SEG_USUAR X
     WHERE X.PAIS_COD_PAIS = psCodigoPais
       AND X.COD_USUA = psCodigoUsuario;

     EXCEPTION
     WHEN DUP_VAL_ON_INDEX THEN
         UPDATE ssicc_comun.SEG_USUAR_BORRA X
         SET X.fec_elim_segu = SYSDATE,
             x.nro_rcr_elim = psNroRCR
         WHERE X.PAIS_COD_PAIS = psCodigoPais
           AND X.COD_USUA = psCodigoUsuario;
     END;

     BEGIN
       INSERT INTO ssicc_comun.SEG_USUAR_ROL_BORRA(
            pais_cod_pais,
            rol_cod_rol,
            usua_cod_usua,
            usu_digi,
            fec_digi,
            usu_modi,
            fec_modi,
            est_urol,
            fec_elim_segu,
            ind_tipo_elim )
       SELECT
            pais_cod_pais,
            rol_cod_rol,
            usua_cod_usua,
            usu_digi,
            fec_digi,
            usu_modi,
            fec_modi,
            est_urol,
            SYSDATE,
            psTipo
       FROM SEG_USUAR_ROL x
       WHERE X.USUA_COD_USUA = psCodigoUsuario;
     EXCEPTION
     WHEN OTHERS THEN
          lnIntervaloDias := 45;
     END;

     DELETE FROM seg_usuar_bloqu x
     WHERE X.USUA_COD_USUA = psCodigoUsuario;

     DELETE FROM SEG_USUAR_ROL x
     WHERE X.USUA_COD_USUA = psCodigoUsuario;

     DELETE FROM SEG_USUAR x
     WHERE X.PAIS_COD_PAIS = psCodigoPais
       AND X.COD_USUA = psCodigoUsuario;


EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR SEG_PR_PROCE_ELIMI: '||ls_sqlerrm);
END SEG_PR_PROCE_ELIMI;



/**************************************************************************
Descripcion       : Proceso de Bloqueo y Eliminacion Masiva de Usuarios
Fecha Creacion    : 02/09/2015
Parametros Entrada:
  psCodigoPais    :  Codigo de pais

Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE SEG_PR_PROCE_BLOQU_ELIMI_MASIV(
    psCodigoPais           VARCHAR2)
IS

  -- Se obtienen los datos
  CURSOR c_usuario(vdFechaActual DATE) IS
    select
        x.cod_usua,
        trunc(x.fec_ulti_ingr) fec_ulti_ingr,
        x.est_usua,
        vdFechaActual - trunc(x.fec_ulti_ingr) VAL_INTE_DIAS
    FROM seg_usuar x
    WHERE x.pais_cod_pais = psCodigoPais
      AND vdFechaActual - trunc(x.fec_ulti_ingr) > INTERVALO_MAXIMO_BLOQUEO
      AND x.use_usua NOT LIKE  '%ADMIN%' 
      AND x.use_usua NOT LIKE  '%MAYUDA%'
      AND x.IND_USUA_SIST != '1'
      
    UNION

    select
        x.cod_usua,
        trunc(x.fec_digi) fec_ulti_ingr,
        x.est_usua,
        vdFechaActual - trunc(x.fec_digi) VAL_INTE_DIAS
    FROM seg_usuar x
    WHERE x.pais_cod_pais = psCodigoPais
      AND x.fec_ulti_ingr IS NULL
      AND vdFechaActual - trunc(x.fec_digi) > INTERVALO_MAXIMO_BLOQUEO
      AND x.use_usua NOT LIKE  '%ADMIN%' 
      AND x.use_usua NOT LIKE  '%MAYUDA%'
      AND x.IND_USUA_SIST != '1'
      ;


  TYPE tRecordUsuario IS RECORD(
    codigoUsuario            seg_usuar.cod_usua%TYPE,
    fechaUltimoIngreso       DATE,
    estadoUsuario            seg_usuar.est_usua%TYPE,
    intervaloDias            NUMBER
  );

  TYPE tTableUsuario IS TABLE OF tRecordUsuario;
  usuarioRecord      tTableUsuario;
  ldFechaActual      DATE;
  lnIntervaloDias    NUMBER;
  lsTipo             VARCHAR2(1):='A';

BEGIN
  /* obteniendo id's */
  ldFechaActual := TRUNC(SYSDATE);

  BEGIN
    SELECT TO_NUMBER(X.VAL_POLI)
    INTO INTERVALO_MAXIMO_BLOQUEO
    FROM SEG_PARAM_POLIT X
    WHERE X.COD_POLI = '006'
      AND X.EST_POLI = '1';
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
         RETURN;
  END;

  BEGIN
    SELECT TO_NUMBER(X.VAL_POLI)
    INTO INTERVALO_MAXIMO_ELIMINACION
    FROM SEG_PARAM_POLIT X
    WHERE X.COD_POLI = '007'
      AND X.EST_POLI = '1';
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
         RETURN;
  END;

  -- Se procesan los datos recuperados en el cursor
  OPEN c_usuario(ldFechaActual);
  LOOP
    FETCH c_usuario BULK COLLECT INTO usuarioRecord LIMIT W_FILAS;
    IF usuarioRecord.COUNT > 0 THEN

      FOR x IN usuarioRecord.FIRST .. usuarioRecord.LAST LOOP
          lnIntervaloDias := usuarioRecord(x).intervaloDias ;

          /* Aplicando Bloqueo */
          IF lnIntervaloDias > INTERVALO_MAXIMO_BLOQUEO AND lnIntervaloDias <= INTERVALO_MAXIMO_ELIMINACION THEN

             UPDATE SEG_USUAR X
             SET X.EST_USUA = ESTADO_CODIGO_BLOQUEO,
                 X.FEC_BLOQ_SEGU = SYSDATE,
                 X.IND_TIPO_BLOQ = lsTipo,
                 X.FEC_MODI = SYSDATE
             WHERE X.PAIS_COD_PAIS = psCodigoPais
               AND X.COD_USUA = usuarioRecord(x).codigoUsuario;

          ELSIF lnIntervaloDias > INTERVALO_MAXIMO_ELIMINACION THEN

              /* Aplicando Eliminación */
              SEG_PR_PROCE_ELIMI(psCodigoPais, usuarioRecord(x).codigoUsuario, lsTipo);

          END IF;
      END LOOP;

    END IF;
    EXIT WHEN c_usuario%NOTFOUND;
  END LOOP;
  CLOSE c_usuario;


EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR SEG_PR_PROCE_BLOQU_ELIMI_MASIV: '||ls_sqlerrm);
END SEG_PR_PROCE_BLOQU_ELIMI_MASIV;


/**************************************************************************
Descripcion       : Proceso de Bloqueo Manual de Usuarios
Fecha Creacion    : 02/09/2015
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE SEG_PR_PROCE_BLOQU_MANUA(
   psNroRCR VARCHAR2
   )
IS

  -- Se obtienen los datos
  CURSOR c_usuario(vdFechaActual DATE) IS
    select
        x.pais_cod_pais,
        x.cod_usua,
        trunc(x.fec_ulti_ingr) fec_ulti_ingr,
        x.est_usua,
        vdFechaActual - trunc(x.fec_ulti_ingr) VAL_INTE_DIAS
    FROM seg_usuar x,
         ssicc_comun.seg_gtt_usuar y
    WHERE x.pais_cod_pais = y.cod_pais
      AND x.cod_usua = y.cod_usua    ;

  TYPE tRecordUsuario IS RECORD(
    codigoPais               seg_usuar.pais_cod_pais%TYPE,
    codigoUsuario            seg_usuar.cod_usua%TYPE,
    fechaUltimoIngreso       DATE,
    estadoUsuario            seg_usuar.est_usua%TYPE,
    intervaloDias            NUMBER
  );

  TYPE tTableUsuario IS TABLE OF tRecordUsuario;
  usuarioRecord      tTableUsuario;
  ldFechaActual      DATE;
  lnIntervaloDias    NUMBER;
  lsTipo             VARCHAR2(1):='M';

BEGIN
  /* obteniendo id's */
  ldFechaActual := TRUNC(SYSDATE);

  -- Se procesan los datos recuperados en el cursor
  OPEN c_usuario(ldFechaActual);
  LOOP
    FETCH c_usuario BULK COLLECT INTO usuarioRecord LIMIT W_FILAS;
    IF usuarioRecord.COUNT > 0 THEN

      FOR x IN usuarioRecord.FIRST .. usuarioRecord.LAST LOOP
         /* Aplicando Bloqueo */
         UPDATE SEG_USUAR X
             SET X.EST_USUA = ESTADO_CODIGO_BLOQUEO,
                 X.FEC_BLOQ_SEGU = SYSDATE,
                 X.IND_TIPO_BLOQ = lsTipo,
                 X.FEC_MODI = SYSDATE,
                 X.NRO_RCR_BLOQ = psNroRCR
             WHERE X.PAIS_COD_PAIS = usuarioRecord(x).codigoPais
               AND X.COD_USUA = usuarioRecord(x).codigoUsuario;
      END LOOP;

    END IF;
    EXIT WHEN c_usuario%NOTFOUND;
  END LOOP;
  CLOSE c_usuario;


EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR SEG_PR_PROCE_BLOQU_MANUA: '||ls_sqlerrm);
END SEG_PR_PROCE_BLOQU_MANUA;


/**************************************************************************
Descripcion       : Proceso de DesBloqueo Manual de Usuarios
Fecha Creacion    : 02/09/2015
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE SEG_PR_PROCE_DBLOQ_MANUA(
   psNroRCR VARCHAR2
   )
IS

  -- Se obtienen los datos
  CURSOR c_usuario(vdFechaActual DATE) IS
    select
        x.pais_cod_pais,
        x.cod_usua,
        trunc(x.fec_ulti_ingr) fec_ulti_ingr,
        x.est_usua,
        vdFechaActual - trunc(x.fec_ulti_ingr) VAL_INTE_DIAS
    FROM seg_usuar x,
         ssicc_comun.seg_gtt_usuar y
    WHERE x.pais_cod_pais = y.cod_pais
      AND x.cod_usua = y.cod_usua    ;

  TYPE tRecordUsuario IS RECORD(
    codigoPais               seg_usuar.pais_cod_pais%TYPE,
    codigoUsuario            seg_usuar.cod_usua%TYPE,
    fechaUltimoIngreso       DATE,
    estadoUsuario            seg_usuar.est_usua%TYPE,
    intervaloDias            NUMBER
  );

  TYPE tTableUsuario IS TABLE OF tRecordUsuario;
  usuarioRecord      tTableUsuario;
  ldFechaActual      DATE;
  lnIntervaloDias    NUMBER;
  lsTipo             VARCHAR2(1):='M';

BEGIN
  /* obteniendo id's */
  ldFechaActual := TRUNC(SYSDATE);

  -- Se procesan los datos recuperados en el cursor
  OPEN c_usuario(ldFechaActual);
  LOOP
    FETCH c_usuario BULK COLLECT INTO usuarioRecord LIMIT W_FILAS;
    IF usuarioRecord.COUNT > 0 THEN

      FOR x IN usuarioRecord.FIRST .. usuarioRecord.LAST LOOP
         /* Aplicando desBloqueo */
         UPDATE SEG_USUAR X
             SET X.EST_USUA = '2',
                 X.FEC_BLOQ_SEGU = NULL,
                 X.IND_TIPO_BLOQ = '',
                 X.FEC_MODI = SYSDATE,
                 X.NRO_RCR_DESB = psNroRCR
             WHERE X.PAIS_COD_PAIS = usuarioRecord(x).codigoPais
               AND X.COD_USUA = usuarioRecord(x).codigoUsuario;
      END LOOP;

    END IF;
    EXIT WHEN c_usuario%NOTFOUND;
  END LOOP;
  CLOSE c_usuario;


EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR SEG_PR_PROCE_DBLOQ_MANUA: '||ls_sqlerrm);
END SEG_PR_PROCE_DBLOQ_MANUA;

/**************************************************************************
Descripcion       : Proceso de Eliminación Manual de Usuarios
Fecha Creacion    : 02/09/2015
Parametros Entrada:
  psCodigoPais    :  Codigo de pais

Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE SEG_PR_PROCE_ELIMI_MANUA(
   psNroRCR VARCHAR2
   )
IS

  -- Se obtienen los datos
  CURSOR c_usuario(vdFechaActual DATE) IS
    select
        x.pais_cod_pais,
        x.cod_usua,
        trunc(x.fec_ulti_ingr) fec_ulti_ingr,
        x.est_usua,
        vdFechaActual - trunc(x.fec_ulti_ingr) VAL_INTE_DIAS
    FROM seg_usuar x,
         ssicc_comun.seg_gtt_usuar y
    WHERE x.pais_cod_pais = y.cod_pais
      AND x.cod_usua = y.cod_usua    ;

  TYPE tRecordUsuario IS RECORD(
    codigoPais               seg_usuar.pais_cod_pais%TYPE,
    codigoUsuario            seg_usuar.cod_usua%TYPE,
    fechaUltimoIngreso       DATE,
    estadoUsuario            seg_usuar.est_usua%TYPE,
    intervaloDias            NUMBER
  );

  TYPE tTableUsuario IS TABLE OF tRecordUsuario;
  usuarioRecord      tTableUsuario;
  ldFechaActual      DATE;
  lnIntervaloDias    NUMBER;
  lsTipo             VARCHAR2(1):='M';

BEGIN
  /* obteniendo id's */
  ldFechaActual := TRUNC(SYSDATE);

  -- Se procesan los datos recuperados en el cursor
  OPEN c_usuario(ldFechaActual);
  LOOP
    FETCH c_usuario BULK COLLECT INTO usuarioRecord LIMIT W_FILAS;
    IF usuarioRecord.COUNT > 0 THEN

      FOR x IN usuarioRecord.FIRST .. usuarioRecord.LAST LOOP
          /* Aplicando Eliminación */
          SEG_PR_PROCE_ELIMI(usuarioRecord(x).codigoPais, usuarioRecord(x).codigoUsuario, lsTipo, psNroRCR);
      END LOOP;

    END IF;
    EXIT WHEN c_usuario%NOTFOUND;
  END LOOP;
  CLOSE c_usuario;


EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR SEG_PR_PROCE_ELIMI_MANUA: '||ls_sqlerrm);
END SEG_PR_PROCE_ELIMI_MANUA;



END SEG_PKG_PROCE;
/
