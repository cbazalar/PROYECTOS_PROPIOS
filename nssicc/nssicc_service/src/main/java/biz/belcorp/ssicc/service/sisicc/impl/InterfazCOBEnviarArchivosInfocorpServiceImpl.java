package biz.belcorp.ssicc.service.sisicc.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.InterfazCOBDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;
import biz.belcorp.ssicc.service.util.FileUtil;
import biz.belcorp.ssicc.service.util.ZipUtil;

@Service("sisicc.interfazCOBEnviarArchivosInfocorpService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazCOBEnviarArchivosInfocorpServiceImpl extends BaseInterfazProcesoAbstractService
{
	@Resource(name="sisicc.interfazCOBDAO")
	protected InterfazCOBDAO interfazCOBDAO;

	@Override
	protected void executeStoreProcedure(Map params) throws InterfazException, Exception 
	{
		// TODO Auto-generated method stub
		System.out.println("------------ Entrooooo a Interfaz --------");
		
		Interfaz interfaz = (Interfaz)params.get("interfazEjecucion");
		List archivos = new ArrayList();
		List archivos1 = new ArrayList();
		String codigoPais = (String)params.get("codigoPais");
		
		// llamamos a los metodos que generaran los archivos .txt
		interfazCOBDAO.executeInterfazEnvioArchivoInicioEsika(params);
		interfazCOBDAO.executeInterfazEnvioArchivoMorosasEsika(params);
		interfazCOBDAO.executeInterfazEnvioArchivoCastigadasEsika(params);
		
		if(codigoPais.equals("PE"))
		{
			params.put("codigoPaisLbel", "PEL");
			interfazCOBDAO.executeInterfazEnvioArchivoInicioLbel(params);
			interfazCOBDAO.executeInterfazEnvioArchivoMorosasLbel(params);
			interfazCOBDAO.executeInterfazEnvioArchivoCastigadasLbel(params);
		}
		
		// archivo cetco1
		archivos.add("esika-infocorp-ccd-masde5.txt");
		archivos.add("esika-infocorp-csd.txt");
		archivos.add("infocorp-esm-masde5.txt");
		archivos.add("infocorp-lbm-masde5.txt");
		archivos.add("lbel-infocorp-ccd-masde5.txt");
		archivos.add("lbel-infocorp-csd.txt");
		
		// archivo sicom
		archivos1.add("esika-infocorp-ccd.txt");
		archivos1.add("infocorp-esm.txt");
		archivos1.add("infocorp-lbm.txt");
		archivos1.add("lbel-infocorp-ccd.txt");
					

		try {
            //Juntar los archivos para formar CETCO1
			ByteBuffer[] buffers = new ByteBuffer[archivos.size()];

			for (int i = 0; i < archivos.size(); i++) 
			{
				log.debug("archivo N:"+i+" lista 1");
				if (!FileUtil.validarUltimaLineaArchivo(FileUtil.formatDirectory(interfaz.getDirectorioTemporal()) +"/"+ archivos.get(i).toString())) {
					this.log.warn("El archivo no pudo ser preprocesado (Posible omision del primer registro del siguiente archivo.)");
				}

				RandomAccessFile raf = new RandomAccessFile(FileUtil.formatDirectory(interfaz.getDirectorioTemporal()) +"/" + archivos.get(i).toString(), "r");
				FileChannel channel = raf.getChannel();
				buffers[i] = channel.map(FileChannel.MapMode.READ_ONLY, 0, raf.length());

				channel.close();
				raf.close();
			}

			String nombreArchivoConsolidado = String.format("%s-%s.%s", "cetco1", DateUtil.getDateTimeNow(Constants.PATRON_FECHA_DDMMAAAA, new Date()), Constants.EXTENSION_ARCHIVO_TXT);
			FileOutputStream outFile = new FileOutputStream(FileUtil.formatDirectory(interfaz.getDirectorioTemporal()) +"/" + nombreArchivoConsolidado);
			FileChannel out = outFile.getChannel();
			out.write(buffers);
			out.close();
			
			/* Zipiando archivos */
			String nombreArchivoZipeado = nombreArchivoConsolidado.substring(0, nombreArchivoConsolidado.length()-3);
			String filesAZipiar = interfaz.getDirectorioEntradaSalida() +"/"+ nombreArchivoConsolidado;
			String filePathFinal = interfaz.getDirectorioEntradaSalida() +"/"+ nombreArchivoZipeado + Constants.EXTENSION_ZIP;
			
			ZipUtil.zipFile(filesAZipiar,filePathFinal);
			
			// Enviamos el consolidado al directorio de E/S
			File copiaDestino = new File(FileUtil.formatDirectory(interfaz.getDirectorioEntradaSalida()) +"/" + nombreArchivoConsolidado);
						
			if(copiaDestino.exists())
				copiaDestino.delete();
			
			//Juntar los archivos para formar SICOM
			ByteBuffer[] buffers1 = new ByteBuffer[archivos1.size()];

			for (int i = 0; i < archivos1.size(); i++) 
			{
				log.debug("archivo N:"+i+" lista 2");
				if (!FileUtil.validarUltimaLineaArchivo(FileUtil.formatDirectory(interfaz.getDirectorioTemporal()) +"/" + archivos1.get(i).toString())) 
				{
					this.log.warn("El archivo no pudo ser preprocesado (Posible omision del primer registro del siguiente archivo.)");
				}

				RandomAccessFile raf = new RandomAccessFile(FileUtil.formatDirectory(interfaz.getDirectorioTemporal())+"/" + archivos1.get(i).toString(), "r");
				FileChannel channel = raf.getChannel();
				buffers1[i] = channel.map(FileChannel.MapMode.READ_ONLY, 0, raf.length());

				channel.close();
				raf.close();
			}

			String nombreArchivoConsolidado1 = String.format("%s-%s.%s", "sicom", DateUtil.getDateTimeNow( Constants.PATRON_FECHA_DDMMAAAA, new Date()), Constants.EXTENSION_ARCHIVO_TXT);
			FileOutputStream outFile1 = new FileOutputStream(FileUtil.formatDirectory(interfaz.getDirectorioTemporal()) +"/" + nombreArchivoConsolidado1);
			FileChannel out1 = outFile1.getChannel();
			out1.write(buffers1);
			out1.close();
			
			/* Zipiando archivos*/ 
			String nombreArchivoZipeado1 = nombreArchivoConsolidado1.substring(0, nombreArchivoConsolidado1.length()-3);
			String filesAZipiar1 = interfaz.getDirectorioEntradaSalida() +"/"+ nombreArchivoConsolidado1;
			String filePathFinal1 = interfaz.getDirectorioEntradaSalida() +"/"+ nombreArchivoZipeado1 + Constants.EXTENSION_ZIP;
			
			ZipUtil.zipFile(filesAZipiar1,filePathFinal1);
			
			// Enviamos el consolidado al directorio de E/;
			File copiaDestino1= new File(FileUtil.formatDirectory(interfaz.getDirectorioEntradaSalida()) +"/"+ nombreArchivoConsolidado1);
			
			if(copiaDestino1.exists())
				copiaDestino1.delete();
			
		} catch (Exception ex) {
			this.log.error(ex.getMessage(), ex);
		}
	}
}