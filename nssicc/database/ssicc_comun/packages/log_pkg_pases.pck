CREATE OR REPLACE PACKAGE LOG_PKG_PASES IS

  /* Declaracion de Variables */
  ln_sqlcode   NUMBER(10);
  ls_sqlerrm   VARCHAR2(150);
  W_FILAS      NUMBER:=1000;
  ERROR_DATA_MANIPULATION   CONSTANT VARCHAR2(256) := 'ORA-01732';

/***************************************************************************
Descripcion       : Procedimiento que realiza la Activacion del Trigger de Log
                    de Errores en el Servidor
Fecha Creacion    : 16/02/2010
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE LOG_PR_ACTIV_TRIGG_ERROR(psPaisMarca VARCHAR2);

/***************************************************************************
Descripcion       : Procedimiento que realiza la desactivacion del Trigger de Log
                    de Errores en el Servidor
Fecha Creacion    : 16/02/2010
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE LOG_PR_DESAC_TRIGG_ERROR(psPaisMarca VARCHAR2);

/***************************************************************************
Descripcion       : Procedimiento que realiza los procesos iniciales antes del
                    Pase a Productivo
Fecha Creacion    : 16/02/2010
Autor             : Carlos Bazalar
Parametros        :
         psCodPase   : Codigo de Pase a Productivo
         psPaisMarca : Esquema Pais Marca
***************************************************************************/
PROCEDURE LOG_PR_INICI_PASES_PRODU(psCodPase VARCHAR2, psPaisMarca VARCHAR2);

/***************************************************************************
Descripcion       : Procedimiento que realiza los procesos finales despues de
                    realizado el Pase a Productivo
Fecha Creacion    : 16/02/2010
Autor             : Carlos Bazalar
Parametros        :
         psCodPase   : Codigo de Pase a Productivo
         psPaisMarca : Esquema Pais Marca
***************************************************************************/
PROCEDURE LOG_PR_FINAL_PASES_PRODU(psCodPase VARCHAR2, psPaisMarca VARCHAR2);

/**************************************************************************
  Descripcion       : Devuelve si un pase se a ejecutado en un esquema de bd
  Fecha Creacion    : 05/03/2010
  Parametros Entrada:
      psCodigoPase       : Codigo de pase
      psCodigoEsquema   : Codigo de Esquema
  Autor             : José Luis Rodríguez
***************************************************************************/
FUNCTION INT_FN_DEVUE_EJECU_PASES
  (
    psCodPase      VARCHAR2,
    psCodEsquema  VARCHAR2
  ) RETURN VARCHAR2;

/**************************************************************************
  Descripcion       : Devuelve el numero de errores de una pase en un esquema
                      de bd
  Fecha Creacion    : 05/03/2010
  Parametros Entrada:
      psCodigoPase       : Codigo de pase
      psCodigoEsquema   : Codigo de Esquema
  Autor             : José Luis Rodríguez
***************************************************************************/
FUNCTION INT_FN_DEVUE_ERROR_PASES
  (
    psCodPase      VARCHAR2,
    psCodEsquema  VARCHAR2
  ) RETURN NUMBER;

END LOG_PKG_PASES;
/

CREATE OR REPLACE PACKAGE BODY LOG_PKG_PASES IS

/***************************************************************************
Descripcion       : Procedimiento que realiza la Activacion del Trigger de Log
                    de Errores en el Servidor
Fecha Creacion    : 16/02/2010
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE LOG_PR_ACTIV_TRIGG_ERROR(psPaisMarca VARCHAR2)
IS
  lsPaisMarca   VARCHAR2(15);
BEGIN
  lsPaisMarca := TRIM(psPaisMarca);
  EXECUTE IMMEDIATE 'alter trigger ssicc_comun.LOG_TRG_SERVE_ERROR_COMUN enable';
  EXECUTE IMMEDIATE 'alter trigger '||lsPaisMarca||'.LOG_TRG_SERVE_ERROR enable';
  EXECUTE IMMEDIATE 'alter trigger ssicc_comun.LOG_TRG_SHARE_EXACT_COMUN enable';
  EXECUTE IMMEDIATE 'alter trigger '||lsPaisMarca||'.LOG_TRG_SHARE_EXACT enable';
END LOG_PR_ACTIV_TRIGG_ERROR;

/***************************************************************************
Descripcion       : Procedimiento que realiza la desactivacion del Trigger de Log
                    de Errores en el Servidor
Fecha Creacion    : 16/02/2010
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE LOG_PR_DESAC_TRIGG_ERROR(psPaisMarca VARCHAR2)
IS
  lsPaisMarca   VARCHAR2(15);
BEGIN
  lsPaisMarca := TRIM(psPaisMarca);
  EXECUTE IMMEDIATE 'alter trigger ssicc_comun.LOG_TRG_SERVE_ERROR_COMUN DISABLE';
  EXECUTE IMMEDIATE 'alter trigger '||lsPaisMarca||'.LOG_TRG_SERVE_ERROR DISABLE';
  EXECUTE IMMEDIATE 'alter trigger ssicc_comun.LOG_TRG_SHARE_EXACT_COMUN DISABLE';
  EXECUTE IMMEDIATE 'alter trigger '||lsPaisMarca||'.LOG_TRG_SHARE_EXACT DISABLE';
END LOG_PR_DESAC_TRIGG_ERROR;

/***************************************************************************
Descripcion       : Procedimiento que realiza los procesos iniciales antes del
                    Pase a Productivo
Fecha Creacion    : 16/02/2010
Autor             : Carlos Bazalar
Parametros        :
         psCodPase   : Codigo de Pase a Productivo
         psPaisMarca : Esquema Pais Marca
***************************************************************************/
PROCEDURE LOG_PR_INICI_PASES_PRODU(psCodPase VARCHAR2, psPaisMarca VARCHAR2)
IS
BEGIN
   DELETE FROM SSICC_COMUN.LOG_TMP_PASES_SSICC;
   INSERT INTO SSICC_COMUN.LOG_TMP_PASES_SSICC(COD_PASE, COD_PAIS_MARC)
   VALUES (psCodPase, psPaisMarca);

   DELETE FROM SSICC_COMUN.LOG_PASES_SSICC A
   WHERE A.COD_PASE = psCodPase
     AND A.COD_PAIS_MARC = psPaisMarca;

   commit;
END LOG_PR_INICI_PASES_PRODU;

/***************************************************************************
Descripcion       : Procedimiento que realiza los procesos finales despues de
                    realizado el Pase a Productivo
Fecha Creacion    : 16/02/2010
Autor             : Carlos Bazalar
Parametros        :
         psCodPase   : Codigo de Pase a Productivo
         psPaisMarca : Esquema Pais Marca
***************************************************************************/
PROCEDURE LOG_PR_FINAL_PASES_PRODU(psCodPase VARCHAR2, psPaisMarca VARCHAR2)
IS
 ls_captured_sql VARCHAR2(1000);
 lnContador      NUMBER := 0;
 lnEncontro      NUMBER := 0;
 lnSecuencia     NUMBER;
 lsCodPase       ssicc_comun.log_tmp_pases_ssicc.cod_pase%TYPE;
 lsCodPaisMarca  ssicc_comun.log_tmp_pases_ssicc.cod_pais_marc%TYPE;
 l_mail_conn     UTL_SMTP.connection;
 ls_from         VARCHAR2(50) :='Pase_Aplicacion_SSICC@belcorp.biz';
 lsEmail         VARCHAR2(500);
 lsSubject       VARCHAR2(500);
 lsLinea         VARCHAR2(2000);
 lbEnviarCorreo  BOOLEAN;
 lbError         BOOLEAN;
 CURSOR cInvalido IS
  select owner, object_type, object_name
  from dba_objects
  where owner in (psPaisMarca,'SSICC_COMUN')
    and status= 'INVALID'
    and object_type IN ('PROCEDURE','FUNCTION','PACKAGE')
  union
  select owner, object_type, object_name
  from dba_objects where owner in (psPaisMarca,'SSICC_COMUN')
   and status= 'INVALID'
   and object_type IN ('PACKAGE BODY');

 CURSOR CLogErrores IS
 SELECT  err_gene, err_sent_sql, ind_erro, num_sequ
 FROM ssicc_comun.log_pases_ssicc a
 WHERE cod_pase = psCodPase
   AND cod_pais_marc = psPaisMarca
 ORDER BY err_gene;

 CURSOR CLogErroresCorreo IS
 SELECT  err_gene, err_sent_sql, ind_erro, num_sequ
 FROM ssicc_comun.log_pases_ssicc a
 WHERE cod_pase = psCodPase
   AND cod_pais_marc = psPaisMarca
   AND ind_erro <> 'W'
 ORDER BY err_gene;

BEGIN
  /* Revisando objetos invalidos */
  FOR cInva IN cInvalido LOOP
       SELECT ssicc_comun.SEQ_LOG_PASES_SSICC.NEXTVAL
       INTO lnSecuencia
       FROM DUAL;
       INSERT INTO ssicc_comun.log_pases_ssicc(
          cod_pase, cod_pais_marc, num_sequ,fec_date_ejec, err_esqu, nom_bd, err_gene, err_sent_sql, ind_erro)
       VALUES
          (psCodPase, psPaisMarca, lnsecuencia, SYSDATE, cInva.Owner, 'SICC', ' '||cInva.Object_Type||' Invalido',
            cInva.object_name, 'S');

  END LOOP;

  /* Validando si existen Warning */
  FOR cLog IN CLogErrores LOOP
      IF clog.Ind_Erro = 'S' THEN
         lnEncontro := INSTR(clog.err_gene,ERROR_DATA_MANIPULATION);
         IF lnEncontro > 0 THEN
            UPDATE ssicc_comun.log_pases_ssicc a
            SET ind_erro = 'W'
            WHERE a.cod_pase = psCodPase
              AND a.cod_pais_marc = psPaisMarca
              AND a.num_sequ = clog.num_sequ;
         END IF;
      END IF;
  END LOOP;

  /* Revisando si hay errores */
  SELECT COUNT(1)
  INTO lnContador
  FROM ssicc_comun.log_pases_ssicc a
  WHERE A.COD_PASE = psCodPase
    AND A.COD_PAIS_MARC = psPaisMarca
    AND A.IND_ERRO = 'S';

  IF lnContador = 0 THEN
     ls_captured_sql := 'SIN ERROR';
     DELETE FROM ssicc_comun.log_pases_ssicc
     WHERE cod_pase = psCodPase
       AND cod_pais_marc = psPaisMarca
       AND err_sent_sql = ls_captured_sql
       AND ind_erro = 'N';

     SELECT ssicc_comun.SEQ_LOG_PASES_SSICC.NEXTVAL
     INTO lnSecuencia
     FROM DUAL;

     INSERT INTO ssicc_comun.log_pases_ssicc(
        cod_pase, cod_pais_marc, num_sequ,fec_date_ejec, err_sent_sql, ind_erro)
      VALUES
        (psCodPase, psPaisMarca, lnSecuencia, SYSDATE, ls_captured_sql, 'N');
  END IF;
  commit;

  /* Enviando correo de pase a productivo */
  BEGIN
    SELECT upper(trim(substr(val_url,length(val_url) - 2)))
    INTO lsSubject
    FROM ssicc_comun.bas_pais a
    WHERE a.Cod_Esqu_Bd = psPaisMarca
    AND rownum = 1;
  EXCEPTION
  WHEN no_data_found THEN
       lsSubject := 'OTROS AMBIENTES';
  END;
  lbEnviarCorreo:= TRUE;
  BEGIN
    SELECT a.ema_dest_pase
    INTO lsEmail
    FROM ssicc_comun.bas_servi_mail a;
  EXCEPTION
  WHEN no_data_found THEN
       lbEnviarCorreo:= FALSE;
  END;
  IF NOT lbEnviarCorreo THEN
     RETURN;
  END IF;

  lsSubject := 'LOG PASE SSICC '||psCodPase||' - '||psPaisMarca||' ('||lsSubject||')';
  l_mail_conn := ssicc_comun.log_email.begin_mail(
          sender => ls_from,
          recipients => lsEmail,
          subject => lsSubject,
          mime_type => 'text/html');
  ssicc_comun.log_email.write_text(l_mail_conn,'<html><body>');
  ssicc_comun.log_email.write_text(l_mail_conn,'<table style="text-align: left; width: 90%" border="0" cellpadding="0" cellspacing="0">');
  ssicc_comun.log_email.write_text(l_mail_conn,'<tr><td style="height: 172px; width: 256px;"><font color="#4188b2" face="Arial" size="4"><b>');
  ssicc_comun.log_email.write_text(l_mail_conn, lsSubject );
  ssicc_comun.log_email.write_text(l_mail_conn,'</b></font></td></tr></table>');
  ssicc_comun.log_email.write_text(l_mail_conn,'<br>');
  ssicc_comun.log_email.write_text(l_mail_conn,'<font face="Arial" size="1"><br><b>NOTA: Por favor no responda a este mensaje, es generado automáticamente desde una cuenta no monitoreada.</b><br></font>');
  ssicc_comun.log_email.write_text(l_mail_conn,'<br>');
  ssicc_comun.log_email.write_text(l_mail_conn,'<table style="text-align: left; width: 90%" border="1" cellpadding="0" cellspacing="0">');
  ssicc_comun.log_email.write_text(l_mail_conn,'<tr width="80%"><td width="50%"><font face="Arial" size="2"><b>ERROR</b></font></td><td width="50%"><font face="Arial" size="2"><b>SENTENCIA SQL</b></font></td></tr>');
  FOR cLog IN CLogErroresCorreo LOOP
      ssicc_comun.log_email.write_text(l_mail_conn,'<tr>');
      IF clog.err_gene IS NOT NULL THEN
         lsLinea := '<td width="60%"><font face="Arial" size="2">'||TRIM(clog.err_gene)||'</font></td>';
         ssicc_comun.log_email.write_text( l_mail_conn, lsLinea || utl_tcp.CRLF);
      END IF;
      IF clog.err_sent_sql IS NOT NULL THEN
         lsLinea := '<td width="40%"><font face="Arial" size="2">'||TRIM(clog.err_sent_sql)||'</font></td>';
         ssicc_comun.log_email.write_text( l_mail_conn, lsLinea);
      END IF;
      ssicc_comun.log_email.write_text(l_mail_conn,'</tr>');
  END LOOP;

  ssicc_comun.log_email.write_text(l_mail_conn,'</table>');
  ssicc_comun.log_email.write_text(l_mail_conn,'<br>');
  ssicc_comun.log_email.write_text(l_mail_conn,'<font face="Arial" size="1"><br><b>NOTA: Por favor no responda a este mensaje, es generado automáticamente desde una cuenta no monitoreada.</b><br><br><br></font><br><br><br></body></html>');
  ssicc_comun.log_email.end_mail( conn => l_mail_conn );

END LOG_PR_FINAL_PASES_PRODU;

/**************************************************************************
  Descripcion       : Devuelve si un pase se a ejecutado en un esquema de bd
  Fecha Creacion    : 05/03/2010
  Parametros Entrada:
      psCodigoPase       : Codigo de pase
      psCodigoEsquema   : Codigo de Esquema
  Autor             : José Luis Rodríguez
***************************************************************************/
FUNCTION INT_FN_DEVUE_EJECU_PASES
  (
    psCodPase     VARCHAR2,
    psCodEsquema  VARCHAR2
  ) RETURN VARCHAR2 IS

    lnEjecucion NUMBER;

BEGIN

  lnEjecucion := 0;
  SELECT COUNT(1)
    INTO lnEjecucion
    FROM log_pases_ssicc l
   WHERE l.cod_pais_marc = psCodEsquema
     AND l.cod_pase = psCodPase;

  IF lnEjecucion>0 THEN
    RETURN 'SI';
  END IF;

  RETURN 'N0';

EXCEPTION
  WHEN no_data_found THEN
    RETURN '';
  WHEN OTHERS THEN
    RETURN '';

END INT_FN_DEVUE_EJECU_PASES;

/**************************************************************************
  Descripcion       : Devuelve el numero de errores de una pase en un esquema
                      de bd
  Fecha Creacion    : 05/03/2010
  Parametros Entrada:
      psCodigoPase       : Codigo de pase
      psCodigoEsquema   : Codigo de Esquema
  Autor             : José Luis Rodríguez
***************************************************************************/
FUNCTION INT_FN_DEVUE_ERROR_PASES
  (
    psCodPase     VARCHAR2,
    psCodEsquema  VARCHAR2
  ) RETURN NUMBER IS

    lnErrores NUMBER;

BEGIN

  lnErrores:= 0;
  SELECT COUNT(1)
    INTO lnErrores
    FROM log_pases_ssicc l
   WHERE l.cod_pais_marc = psCodEsquema
     AND l.cod_pase = psCodPase
     AND l.ind_erro = 'S';

  RETURN lnErrores;

EXCEPTION
  WHEN no_data_found THEN
    RETURN 0;
  WHEN OTHERS THEN
    RETURN 0;

END INT_FN_DEVUE_ERROR_PASES;

END LOG_PKG_PASES;
/

