CREATE OR REPLACE PROCEDURE "IMP_PR_GUIA_REMIS_TO_FILE"
(p_nombreArchivo IN VARCHAR2, p_directorio IN VARCHAR2) AS
l_output         UTL_FILE.file_type;
l_amt            NUMBER DEFAULT 4000;
l_offset         NUMBER DEFAULT 1;
position         INTEGER := 1;
l_length         NUMBER :=0 ;
x                VARCHAR2(32000);
t_Clob           CLOB;
-- Variable a contener el mensaje de la excepcion a lanzar
l_mensajeError VARCHAR2(500);
CURSOR C_GUIA_REMI IS
SELECT
TEX_GUIA_REMI
FROM IMP_TMP_GUIA_REMIS A
ORDER BY A.VAL_NUME_SOLI, A.NUM_PAGI;
BEGIN
  -- Creamos el objeto DIRECTORY para utilizarlo en las funciones
  EXECUTE IMMEDIATE 'CREATE OR REPLACE DIRECTORY GUIA_REMI_OUT_DIR AS ''' || p_directorio || '''';
  l_output := UTL_FILE.fopen ('GUIA_REMI_OUT_DIR', p_nombreArchivo, 'wb', 32760);
  -- Escribimos los caracteres de inicio de impresio
  UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(CHR(27) || CHR(48) || CHR(27) || CHR(80)), TRUE);
  -- Salto de pagina
  UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(CHR(12)), TRUE);
  -- Iteramos sobre el cursor
  OPEN C_GUIA_REMI;
  LOOP
    FETCH C_GUIA_REMI INTO t_clob;
    EXIT WHEN C_GUIA_REMI%NOTFOUND;
    l_length := DBMS_LOB.GETLENGTH(T_CLOB);
    position := 1;
    l_offset := 1;
    l_amt := 4000;
    -- Escribimos los bloques en el archivo
    WHILE (l_offset < l_length) LOOP
        IF (l_amt > (l_length - l_offset)) THEN l_amt := l_length - l_offset + 1; END IF;
        dbms_lob.read (t_clob, l_amt, l_offset, x);
        UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(x), TRUE);
        l_offset := l_offset + l_amt;
        position := position + 4000;
        x := null;
    END LOOP;
  -- Salto de pagina
  UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(CHR(12)), TRUE);
  END LOOP;
  -- Cerramos el cursor
  CLOSE C_GUIA_REMI;
  -- Cerramos el archivo
  UTL_FILE.fclose (l_output);
  EXCEPTION
 WHEN UTL_FILE.INTERNAL_ERROR THEN
     l_mensajeError:='ERROR INTERNO DEL MANEJADOR DE ARCHIVOS';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);
 WHEN UTL_FILE.INVALID_FILEHANDLE THEN
     l_mensajeError:='EL ARCHIVO NO ESTA ABIERTO O NO ES VALIDO';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);
 WHEN UTL_FILE.INVALID_MODE THEN
     l_mensajeError:='MODO INVALIDO AL ABRIR EL ARCHIVO';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);
 WHEN UTL_FILE.WRITE_ERROR THEN
        l_mensajeError:='ERROR AL ESCRIBIR EN EL ARCHIVO O NO HAY ESPACIO EN DISCO';
        RAISE_APPLICATION_ERROR(-20123, l_mensajeError);
 WHEN UTL_FILE.INVALID_OPERATION THEN
     l_mensajeError:='MODO INVALIDO AL ABRIR EL ARCHIVO';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);
 WHEN UTL_FILE.INVALID_PATH THEN
     l_mensajeError:='ERROR EN LA RUTA DEL ARCHIVO, ARCHIVO NO ES ACCESIBLE';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);
 WHEN OTHERS THEN
      RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_GUIA_REMIS_TO_FILE: '||substr(sqlerrm,1,250));
END IMP_PR_GUIA_REMIS_TO_FILE;
/

