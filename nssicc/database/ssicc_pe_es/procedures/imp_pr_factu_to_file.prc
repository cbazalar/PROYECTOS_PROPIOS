CREATE OR REPLACE PROCEDURE "IMP_PR_FACTU_TO_FILE"
(p_nombreArchivo IN VARCHAR2,
 p_directorio IN VARCHAR2) AS

l_output         UTL_FILE.file_type;
l_amt            NUMBER DEFAULT 4000;
l_offset         NUMBER DEFAULT 1;
position         INTEGER := 1;
l_length         NUMBER :=0 ;
x                VARCHAR2(32000);

-- Variable a contener el mensaje de la excepcion a lanzar
l_mensajeError   VARCHAR2(500);
l_inicioArchivo  VARCHAR2(50);
l_finArchivo     VARCHAR2(50);
l_saltoPagina    VARCHAR2(50) := CHR(12);

CURSOR c_docum_matri IS
SELECT
COR_BOLE_VENT,
TEX_BOLE_VENT
FROM IMP_TMP_BOLET_VENTA
ORDER BY COR_BOLE_VENT;

r_docum_matri c_docum_matri%ROWTYPE;

BEGIN

  -- Creamos el objeto DIRECTORY para utilizarlo en las funciones
  EXECUTE IMMEDIATE 'CREATE OR REPLACE DIRECTORY DOCUM_MATRI_OUT_DIR AS ''' || p_directorio || '''';

  l_output := UTL_FILE.fopen ('DOCUM_MATRI_OUT_DIR', p_nombreArchivo, 'wb', 32760);

  -- Obtenemos las cadenas de inicio y fin del archivo, y el salto de pagina
  l_inicioArchivo := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('MAT', 'inicioArchivoFacturaModificado');
  l_finArchivo    := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('MAT', 'finArchivoFacturaModificado');

  -- Escribimos los caracteres de inicio de impresio
  UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(l_inicioArchivo), TRUE);

   -- Iteramos sobre el cursor
   OPEN c_docum_matri;
   LOOP
     FETCH c_docum_matri INTO r_docum_matri;
     EXIT WHEN c_docum_matri%NOTFOUND;
     l_length := DBMS_LOB.GETLENGTH(r_docum_matri.TEX_BOLE_VENT);
     position := 1;
     l_offset := 1;
     l_amt := 4000;

     -- Escribimos los bloques en el archivo
     WHILE (l_offset < l_length) LOOP
         IF (l_amt > (l_length - l_offset)) THEN l_amt := l_length - l_offset + 1; END IF;
         dbms_lob.read (r_docum_matri.TEX_BOLE_VENT, l_amt, l_offset, x);
         UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(x), TRUE);
         l_offset := l_offset + l_amt;
         position := position + 4000;
         x := null;
     END LOOP;

     -- Escribimos el salto de pagina
     UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(l_saltoPagina), TRUE);

   END LOOP;

   -- Cerramos el cursor
   CLOSE c_docum_matri;

  -- Escribimos los caracteres de fin de archivo
  UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(l_finArchivo), TRUE);

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
      RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_FACTU_TO_FILE: '||substr(sqlerrm,1,250));

END IMP_PR_FACTU_TO_FILE;
/

