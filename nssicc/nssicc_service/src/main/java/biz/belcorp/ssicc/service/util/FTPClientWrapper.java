package biz.belcorp.ssicc.service.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FTPClientWrapper {
	protected final Log log = LogFactory.getLog(getClass());

	private FTPClient ftpClient;

	public FTPClientWrapper(String server, int port, String user,
			String password) {
		try {
			ftpClient = new FTPClient();
			ftpClient.connect(server, port);
			ftpClient.login(user, password);
			if (ftpClient.isConnected())
				log.info("Succesfully logged to " + server);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void retrieveFile(String localPath, String remoteDirectory,
			String remoteFileName) {
		if (log.isDebugEnabled()) {
			log.debug("Retrieving file from FTP");
			log.debug("remoteDirectory=" + remoteDirectory);
			log.debug("remoteFileName=" + remoteFileName);
		}
		FileOutputStream localStream = null;
		try {
			ftpClient.changeWorkingDirectory(remoteDirectory);
			localStream = new FileOutputStream(localPath);
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClient.retrieveFile(remoteFileName, localStream);

			// Verificamos que se haya podido copiar el archivo en la Red
			if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
				log.debug("Succesfully retrieved file: " + localPath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeQuietly(localStream);
		}
	}

    public void sendFile(File localFile, String remoteDirectory,
            String remoteFileName) {
        if (log.isDebugEnabled()) {
            log.debug("Retrieving file from FTP");
            log.debug("remoteDirectory=" + remoteDirectory);
            log.debug("remoteFileName=" + remoteFileName);
        }
        FileInputStream localStream = null;
        try {
            ftpClient.changeWorkingDirectory(remoteDirectory);
            localStream = new FileInputStream(localFile);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.storeFile(remoteFileName, localStream);

            // Verificamos que se haya podido copiar el archivo en la Red
            if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                log.debug("Succesfully sent file: " + localFile.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeQuietly(localStream);
        }
    }

	public void deleteFile(String remoteDirectory, String remoteFileName) {
		if (log.isDebugEnabled()) {
			log.debug("Deleting file from FTP");
			log.debug("remoteDirectory=" + remoteDirectory);
			log.debug("remoteFileName=" + remoteFileName);
		}

		try {
			ftpClient.changeWorkingDirectory(remoteDirectory);
			ftpClient.deleteFile(remoteFileName);

			// Verificamos que se haya podido eliminar el archivo
			if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
				log.debug("Succesfully deleted file: " + remoteFileName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			ftpClient.logout();
			ftpClient.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public FTPClient getFtpClient() {
		return ftpClient;
	}

	private void closeQuietly(OutputStream stream) {
		try {
			stream.flush();
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    private void closeQuietly(InputStream stream) {
        try {
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
