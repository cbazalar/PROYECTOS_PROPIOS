package biz.belcorp.ssicc.service.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfWriter;

/**
 * Metodos utilitarios para recuperar imagenes y pasarlos en PDF sea en directorio local o por FTP.
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
public class ImagenPDFUtil {

	protected final Log log = LogFactory.getLog(getClass());
	
	/**
	 * Genera un archivo PDF tomando las imagenes de una carpeta de la Maquina Local
	 * 
	 * @param rutaImagen
	 * 			Directorio donde esta almacenado las imagenes
	 * @param extensionArchivo
	 * 			Extension de las imagenes
	 * @param rutaDestino
	 * 			Directorio donde se almacenar el PDF
	 * @param nombrePDF
	 * 			Nombre del Archivo PDF
	 * @param scaleFit
	 * 			Escala a la cual se ajusta la imagen a ser aadida al contenido del PDF
	 * @throws Exception
	 */
	public void generarPdfLocal(String rutaImagen, String extensionArchivo, String rutaDestino, 
									   String nombrePDF, long scaleFit) throws Exception {
		log.debug("Entering 'generarPdfLocal' method");
		
		Document document=new Document();
		PdfWriter.getInstance(document,new FileOutputStream(rutaDestino + "\\" + nombrePDF + ".pdf"));
		document.open();

		//obtenemos las imagenes
		File dir = new File(FileUtil.formatDirectory(rutaImagen));
		FilenameFilter filenameFilter = new Filtro("." + extensionArchivo);

		String[] fileNames = dir.list(filenameFilter);
		if (fileNames != null && fileNames.length > 0) {
			//lo ordenamos por nombre
			Arrays.sort(fileNames);
		}

		//lo vamos aadiendo al PDF
		for(int i=0; i < fileNames.length; i++) {	
			if (log.isDebugEnabled())
				log.debug("fileNames :" + fileNames[i]);
			Image image = Image.getInstance (rutaImagen + "\\" + fileNames[i]);
			image.scaleToFit(scaleFit, scaleFit);
			document.add(image);
		}	
		
		document.close();
	}
	
	/**
	 * Genera un archivo PDF tomando las imagenes de una carpeta de un servidor FTP y lo deja en un
	 * 		directorio de la Maquina Local
	 * 
	 * @param servidorFtp
	 * 			Servidor FTP
	 * @param puertoFtp
	 * 			Puerto FTP
	 * @param directorioFtp
	 * 			Directorio FTP raiz donde esta almacenados las imagenes de los clientes 
	 * @param usuarioFtp
	 * 			usuario de conexion al servidor FTP
	 * @param passwordFtp
	 * 			password de conexion al servidor FTP
	 * @param extensionArchivo
	 * 			extension de las imagenes
	 * @param rutaTemporal
	 * 			Directorio donde seran almacendas temporalmente las imagenes y PDFs en el servidor local
	 * @param nombrePDF
	 * 			Nombre del PDF
	 * @param scaleFit
	 * 			Escala a la cual se ajusta la imagen a ser aadida al contenido del PDF
	 * @throws Exception
	 */
	public void generarPdfFtpToLocal(String servidorFtp, String puertoFtp, String directorioFtp, String usuarioFtp, 
											String passwordFtp,	String extensionArchivo, String rutaTemporal, 
											String nombrePDF, long scaleFit) throws Exception {
		
		log.debug("Entering 'generarPdfFtpToLocal' method");
		
		Interfaz inter = new Interfaz();
		inter.setServidorFtp(servidorFtp);
		inter.setPuertoFtp(puertoFtp);
		inter.setUsuarioFtp(usuarioFtp);
		inter.setPasswordFtp(passwordFtp);

		//nos conectamos al servidor FTP
		FTPUtil ftpUtil = new FTPUtil();
		ftpUtil.loguearFTPPasivo(inter);
		ArrayList listaArchivos = ftpUtil.buscarListaArchivo(directorioFtp, "", extensionArchivo, Constants.ARCHIVO_VARIABLE);
		
		Document document=new Document();
		PdfWriter.getInstance(document,new FileOutputStream(rutaTemporal + nombrePDF + ".pdf"));
		document.open();

		String[] fileNames = (String[]) listaArchivos.toArray(new String[listaArchivos.size()]);
		if (fileNames != null && fileNames.length > 0) {
			//lo ordenamos por nombre
			Arrays.sort(fileNames);
		}
		
		if(fileNames.length > 0) {
			String fileNamesAux[] = new String[fileNames.length];
			int contador = 0;
			                                   
            for(int j=fileNames.length; j > 0; j--) {
    			fileNamesAux[contador] = fileNames[j-1];
    			contador = contador + 1;
            }
            
            fileNames = fileNamesAux;
		}

		//lo vamos aadiendo al PDF
		for(int i=0; i < fileNames.length; i++) {
			String nombreArchivoImg = nombrePDF + "_" + fileNames[i];
			
			ftpUtil.copiarArchivoFTPaRed(directorioFtp, 
					fileNames[i], rutaTemporal,
					nombreArchivoImg);
							
			if (log.isDebugEnabled())
				log.debug("fileNames :" + fileNames[i]);
			
			Image image = Image.getInstance (rutaTemporal + nombreArchivoImg);
			image.scaleToFit(scaleFit, scaleFit);
			document.add(image);
		}	
		document.close();
		
		//borramos las imagenes del directorio temporal
		for(int i=0; i < fileNames.length; i++) {
			String nombreArchivoImg = nombrePDF + "_" + fileNames[i];
			//borramos el archivo imagen
			File archivoImagen = new File(rutaTemporal, nombreArchivoImg);
			
			try {
				boolean borrarFichero= archivoImagen.delete();
			
				if(!borrarFichero)
					log.debug("No se pudo eliminar el archivo : " + fileNames[i]);
			} catch (Exception e) {
				log.error("No se pudo eliminar el archivo : " + fileNames[i]);
			}
		}	
		
		ftpUtil.cerrarFTP();
		
	}	

	public static void main(String [] args) throws Exception {
		ImagenPDFUtil aux = new ImagenPDFUtil();
		//aux.generarPdfLocal("D:\\escaneo\\cliente", "jpg",  "D:\\escaneo\\tmp", "cliente", 750);
		
		/*aux.generarPdfFtpToLocal("pelnx45", "21", "/int/zon/pe/out/cliente/", "ftpverint", "verintQAS", "jpg", 
					"D:\\escaneo\\tmp\\", "clienteFtp", 750);*/
	}

}

class Filtro implements FilenameFilter{
    String extension;
    Filtro(String extension){
        this.extension=extension;
    }
    public boolean accept(File dir, String name){
        return name.endsWith(extension);
    }
}
