CREATE OR REPLACE PROCEDURE "IMP_PR_CARGA_NUMER_BOLET"
(p_directorio IN VARCHAR2, p_patron VARCHAR2) AS
-- Variables usadas para la carga del archivo
l_FileLocator           BFILE;
l_CLOBSrc               CLOB := EMPTY_CLOB();
l_CLOBLength            NUMBER;
l_textoBoletaDespacho    VARCHAR2(20) := 'BOLETA DESPACHO:';
l_numBoletaDespacho     NUMBER;
-- Valores usados para la carga del CLOB desde el archivo
src_offset  NUMBER := 1;
dst_offset  NUMBER := 1;
charset_id  NUMBER := DBMS_LOB.default_csid;
lang_ctx    NUMBER := DBMS_LOB.default_lang_ctx;
warning     NUMBER;
-- Variable a contener el mensaje de la excepcion a lanzar
l_mensajeError VARCHAR2(500);
CURSOR c_archivos IS
SELECT NOM_ARCH
FROM IMP_ARCHI_NOTA_CREDI_TEMP;
r_archivos c_archivos%ROWTYPE;
BEGIN
  -- Creamos el objeto DIRECTORY para utilizarlo en las funciones
  EXECUTE IMMEDIATE 'CREATE OR REPLACE DIRECTORY NOTA_CRED_DIR AS ''' || p_directorio || '''';
  EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_NUME_BOLET_DESPA_TEMP';
  GEN_PR_LISTA_DIREC(p_directorio, p_patron);
  -- LOOP
  OPEN c_archivos;
  LOOP
  FETCH c_archivos INTO r_archivos;
  EXIT WHEN c_archivos%NOTFOUND;
  BEGIN
   src_offset := 1;
   dst_offset := 1;
   -- Inicializamos el BFILE locator para lectura
   l_FileLocator := BFILENAME('NOTA_CRED_DIR', TRIM(r_archivos.NOM_ARCH));
   DBMS_LOB.FILEOPEN(l_FileLocator, DBMS_LOB.FILE_READONLY);
   -- Usamos un CLOB temporal para cargar la informacion del archivo
   DBMS_LOB.CREATETEMPORARY(l_CLOBSrc, TRUE);
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
   -- Una vez que tenemos todo el archivo en una CLOB temporal
   -- podemos usar las funciones del paquete DBMS_LOB para obtener
   -- la informacion del numero de documento interno
   -- Buscamos el numero de documento interno
   l_numBoletaDespacho := TO_NUMBER(TRIM(DBMS_LOB.SUBSTR(l_CLOBSrc, 14, DBMS_LOB.INSTR(l_CLOBSrc, l_textoBoletaDespacho, 1) + LENGTH(l_textoBoletaDespacho))));
   IF l_numBoletaDespacho IS NOT NULL THEN
    INSERT INTO IMP_TMP_NUME_BOLET_DESPA
    VALUES(l_numBoletaDespacho, NULL);
   END IF;
   -- Liberamos los rescursos usados por el CLOB temporal
   DBMS_LOB.FREETEMPORARY(l_CLOBSrc);
   l_CLOBSrc := EMPTY_CLOB();
  END;
  END LOOP;
  CLOSE c_archivos;
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
 WHEN UTL_FILE.INVALID_FILENAME THEN
     l_mensajeError:='EL ARCHIVO NO EXISTE EN LA RUTA ESPECIFICADA.';
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
      RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_CARGA_NUMER_BOLET: '||substr(sqlerrm,1,250));
END IMP_PR_CARGA_NUMER_BOLET;
/

