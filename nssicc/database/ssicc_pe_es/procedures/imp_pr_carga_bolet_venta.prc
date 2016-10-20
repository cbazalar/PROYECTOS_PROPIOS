CREATE OR REPLACE PROCEDURE "IMP_PR_CARGA_BOLET_VENTA"
(p_nombreArchivo IN VARCHAR2,
 p_directorio IN VARCHAR2
) AS
-- Variables usadas para la carga del archivo
l_FileLocator           BFILE;
l_CLOBSrc               CLOB := EMPTY_CLOB();
l_CLOBDest              CLOB;
l_CLOBLength            NUMBER;

l_correlativo               NUMBER := 0;
l_contador                  NUMBER := 0;
l_inicioArchivo             VARCHAR2(50);
l_finArchivo                VARCHAR2(50);
l_saltoPagina               CHAR(1) := CHR(12);
l_cambioLinea               CHAR(1) := CHR(10);
l_codigoCliente             VARCHAR2(15);

-- Contadores usados para determinar el offset
l_OffsetInicial NUMBER := 1;
l_OffsetFinal   NUMBER := 1;

-- Valores usados para la carga del CLOB desde el archivo
src_offset  NUMBER := 1;
dst_offset  NUMBER := 1;
charset_id  NUMBER := DBMS_LOB.default_csid;
lang_ctx    NUMBER := DBMS_LOB.default_lang_ctx;
warning     NUMBER;

-- Variable a contener el mensaje de la excepcion a lanzar
l_mensajeError VARCHAR2(500);

BEGIN

  EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_TMP_BOLET_VENTA';

  -- Creamos el objeto DIRECTORY para utilizarlo en las funciones
  EXECUTE IMMEDIATE 'CREATE OR REPLACE DIRECTORY BOLE_VENT_DIR AS ''' || p_directorio || '''';

  -- Inicializamos el BFILE locator para lectura
  l_FileLocator := BFILENAME('BOLE_VENT_DIR', p_nombreArchivo);
  DBMS_LOB.FILEOPEN(l_FileLocator, DBMS_LOB.FILE_READONLY);

  -- Usamos un CLOB temporal para cargar la informacion del archivo
  DBMS_LOB.CREATETEMPORARY(l_CLOBSrc, TRUE);

  -- Cargamos todo el archivo en un character LOB Temporal.
  DBMS_LOB.LOADCLOBFROMFILE(l_CLOBSrc,
                            l_FileLocator,
                            DBMS_LOB.GETLENGTH(l_FileLocator),
                            src_offset,
                            dst_offset,
                            charset_id,
                            lang_ctx,
                            warning);

  -- Cerramos el archivo
  DBMS_LOB.FILECLOSE(l_FileLocator);

  -- Obtenemos el tama?o del CLOB cargado
  l_CLOBLength := DBMS_LOB.GETLENGTH(l_CLOBSrc);

  -- Buscamos el numero de documento interno
  -- Obtenemos las cadenas de inicio y fin del archivo, asi como los que nos permiten
  -- extraer el codigo interno de la nota de credito
--  l_inicioArchivo := IMP_PKG_PROCE_COMPA.IMP_FN_OBTIE_PARAM_IMPRE('NOT', 'inicioArchivo');
--  l_finArchivo    := IMP_PKG_PROCE_COMPA.IMP_FN_OBTIE_PARAM_IMPRE('NOT', 'finArchivo');
  l_inicioArchivo := CHR(27) || CHR(48) || CHR(27) || CHR(80) || CHR(12);
  l_finArchivo    := CHR(27) || CHR(67) || CHR(66) || CHR(18);

  -- Obtenemos el offset inicial
  l_OffsetInicial := DBMS_LOB.INSTR(l_CLOBSrc,
                                    l_inicioArchivo,
                                    1);
        
  l_OffsetInicial := l_OffsetInicial + LENGTH(l_inicioArchivo);

  -- LOOP
  WHILE l_CLOBLength > 0 AND l_OffsetFinal != 0
  LOOP

  l_OffsetFinal := DBMS_LOB.INSTR(l_CLOBSrc,
                                  l_saltoPagina,
                                  l_OffsetInicial);

  IF l_OffsetFinal = 0 THEN
      l_OffsetFinal := DBMS_LOB.INSTR(l_CLOBSrc,
                                      l_finArchivo,
                                      l_OffsetInicial);
  END IF;
  IF (l_OffsetFinal = 0) OR (l_OffsetInicial = l_OffsetFinal) THEN
      EXIT;
  END IF;

  l_codigoCliente := NULL;
  l_correlativo := l_correlativo + 1;

  INSERT INTO IMP_TMP_BOLET_VENTA (
  COR_BOLE_VENT,
  COD_CLIE,
  TEX_BOLE_VENT
  )
  VALUES(
  l_correlativo,
  l_codigoCliente,
  EMPTY_CLOB()
  )
  RETURNING TEX_BOLE_VENT INTO l_CLOBDest;

  DBMS_LOB.COPY(l_CLOBDest,
                l_CLOBSrc,
                l_OffsetFinal - l_OffsetInicial,
--                l_OffsetFinal - (l_OffsetInicial + LENGTH(l_saltoPagina)),
                1,
--                (l_OffsetInicial + LENGTH(l_saltoPagina)));
    l_OffsetInicial);

  l_codigoCliente := TRIM(DBMS_LOB.SUBSTR(l_CLOBDest, 15, DBMS_LOB.INSTR(l_CLOBDest, 'CUENTA:', 1) + 7));

  UPDATE IMP_TMP_BOLET_VENTA X
  SET X.COD_CLIE = NVL(l_codigoCliente, '0')
  WHERE X.COR_BOLE_VENT = l_correlativo;

  l_OffsetInicial := l_OffsetFinal + 1;
  l_contador := l_contador + 1;

  END LOOP;-- END LOOP

  -- Liberamos los rescursos usados por el CLOB temporal
  DBMS_LOB.FREETEMPORARY(l_CLOBSrc);

  EXCEPTION
 WHEN UTL_FILE.INTERNAL_ERROR THEN
     l_mensajeError:='ERROR INTERNO DEL MANEJADOR DE ARCHIVOS';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.INVALID_FILEHANDLE THEN
     l_mensajeError:='EL ARCHIVO NO ESTA ABIERTO O NO ES VALIDO';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.INVALID_MODE THEN
     l_mensajeError:='MODO INVALIDO AL ABRIR EL ARCHIVO ' || p_nombreArchivo;
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.INVALID_FILENAME THEN
     l_mensajeError:='EL ARCHIVO ' || p_nombreArchivo || ' NO EXISTE EN LA RUTA ESPECIFICADA.';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.INVALID_OPERATION THEN
     l_mensajeError:='MODO INVALIDO AL ABRIR EL ARCHIVO';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.INVALID_PATH THEN
     l_mensajeError:='ERROR EN LA RUTA DEL ARCHIVO, ARCHIVO NO ES ACCESIBLE';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN OTHERS THEN
         -- Close the cursor and file, and reraise.
         DBMS_LOB.FILECLOSE(l_FileLocator);
         DBMS_LOB.FREETEMPORARY(l_CLOBSrc);
      RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_CARGA_BOLET_VENTA: '||substr(sqlerrm,1,250));
END IMP_PR_CARGA_BOLET_VENTA;
/

