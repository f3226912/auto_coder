package com.gdeng.code.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class FileUtil {
	public static String getFilePath(String source_filename, String pointFilePath) {
		String ext = "";
		String source_filename_no_ext = source_filename;
		if (source_filename.indexOf(".") > 0) {
			ext = source_filename.substring(source_filename.lastIndexOf("."));
			source_filename_no_ext = source_filename.substring(0, source_filename.lastIndexOf("."));
		}

		String uploadRefPath = ((pointFilePath == null) || (pointFilePath.trim().length() == 0)) ? getSystemTempPath() : pointFilePath;
		if ((uploadRefPath != null) && (!(uploadRefPath.endsWith(System.getProperty("file.separator")))) && (!(uploadRefPath.endsWith("/"))) && (!(uploadRefPath.endsWith("\\")))) {
			uploadRefPath = uploadRefPath + System.getProperty("file.separator");
		}
		String uploadPath = uploadRefPath;
		File uploadDir = new File(uploadPath);
		if (!(uploadDir.exists()))
			uploadDir.mkdirs();
		Date today = new Date();
		String formatString = "yyyyMMddHHmmss";
		SimpleDateFormat dateformat = new SimpleDateFormat(formatString);
		String filename = source_filename_no_ext + dateformat.format(today);
		for (int i = 0; i < 10000; ++i) {
			String real_filename = filename + "_" + i + ext;
			File file = new File(uploadDir, real_filename);
			if (!(file.exists())) {
				return uploadRefPath + real_filename;
			}
		}
		return null;
	}

	public static String getSystemTempPath() {
		return System.getProperty("java.io.tmpdir");
	}

	public static Properties getProperties(String fileName) throws Exception {
		Properties initProps = new Properties();
		InputStream in = null;
		try {
			in = getInputStream(fileName);
			initProps.load(in);
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (Exception localException) {
			}
		}
		return initProps;
	}

	public static InputStream getInputStream(String fileName) throws Exception {
		return getInputStream(getFile(getFileURL(fileName).getFile()));
	}

	public static InputStream getInputStream(File file) throws Exception {
		return new BufferedInputStream(new FileInputStream(file));
	}

	public static URL getFileURL(String fileName) throws Exception {
		URL fileURL = FileUtil.class.getClassLoader().getResource(fileName);

		if (fileURL == null) {
			FileUtil.class.getClassLoader().getResource("/" + fileName);
		}

		if (fileURL == null) {
			Thread.currentThread().getContextClassLoader().getResource(fileName);
		}

		if (fileURL == null) {
			fileURL = ClassLoader.getSystemResource(fileName);
		}

		if (fileURL == null) {
			fileURL = new File(fileName).toURL();
		}

		return fileURL;
	}

	public static void setProperties(String fileName, Properties prop) throws Exception {
		prop.store(new FileOutputStream(getFileURL(fileName).getFile()), "store at " + new Date().toLocaleString());
	}

	public static boolean needReload(String fileName, long lastModify) throws Exception {
		return (getLastModify(fileName) > lastModify);
	}

	public static File getFile(String fileName) throws Exception {
		return new File(getFileURL(fileName).getFile());
	}

	public static long getLastModify(String fileName) throws Exception {
		return getFile(fileName).lastModified();
	}

	public static boolean createDir(String dir, boolean ignoreIfExitst) throws Exception {
		File file = getFile(dir);

		if ((ignoreIfExitst) && (file.exists())) {
			return false;
		}

		return file.mkdir();
	}

	public static boolean createDirs(String dir, boolean ignoreIfExitst) throws Exception {
		File file = getFile(dir);

		if ((ignoreIfExitst) && (file.exists())) {
			return false;
		}

		return file.mkdirs();
	}

	public static boolean deleteFile(String filename) throws Exception {
		File file = getFile(filename);

		return deleteFile(file);
	}

	public static boolean deleteFile(File file) throws Exception {
		if (file.isDirectory()) {
			return deleteDir(file);
		}

		if (!(file.exists())) {
			return false;
		}

		return file.delete();
	}

	public static boolean deleteDir(File dir) throws Exception {
		if (dir.isFile()) {
			deleteFile(dir);
		}

		File[] files = dir.listFiles();

		if (files != null) {
			for (int i = 0; i < files.length; ++i) {
				File file = files[i];

				if (file.isFile())
					file.delete();
				else {
					deleteDir(file);
				}
			}
		}

		return dir.delete();
	}

	public static File createFile(InputStream stream, String fileName) throws Exception {
		File newFile = new File(fileName);
		OutputStream fileOut = new BufferedOutputStream(new FileOutputStream(newFile));
		byte[] buffer = new byte[8192];
		int bytesRead = 0;

		while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
			fileOut.write(buffer, 0, bytesRead);
		}

		fileOut.close();
		stream.close();

		return newFile;
	}

	public static void createFile(String output, String content) throws Exception {
		try {
			OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(output));
			PrintWriter out = new PrintWriter(fw);
			out.print(content);
			out.close();
			fw.close();
		} catch (Exception ex) {
			throw new Exception(ex);
		}
	}

	public static Writer openWithWrite(String file, boolean append) throws Exception {
		return new BufferedWriter(new FileWriter(getFile(file), append));
	}

	public static Reader openWithRead(String file) throws Exception {
		return new BufferedReader(new FileReader(getFile(file)));
	}

	public static byte[] getFileBytes(InputStream inputStream) throws Exception {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
		byte[] block = new byte[512];
		while (true) {
			int readLength = inputStream.read(block);

			if (readLength == -1) {
				break;
			}

			byteArrayOutputStream.write(block, 0, readLength);
		}

		byte[] retValue = byteArrayOutputStream.toByteArray();

		byteArrayOutputStream.close();

		return retValue;
	}

	public static byte[] getFileBytes(String file) throws Exception {
		return getFileBytes(getInputStream(file));
	}

	public static void move(String input, String output) throws Exception {
		File inputFile = new File(input);
		File outputFile = new File(output);
		try {
			inputFile.renameTo(outputFile);
		} catch (Exception ex) {
			throw new Exception("Can not mv" + input + " to " + output + ex.getMessage());
		}
	}

//	public static boolean copy(String input, String output) throws Exception {
//		int BUFSIZE = 65536;
//		FileInputStream fis = new FileInputStream(input);
//		FileOutputStream fos = new FileOutputStream(output);
//		try {
//			byte[] buf = new byte[BUFSIZE];
//			int i;
//			while ((i = fis.read(buf)) > -1) {
//				int i;
//				fos.write(buf, 0, i);
//			}
//		} catch (Exception ex) {
//			throw new Exception("makeHome" + ex.getMessage());
//		} finally {
//			fis.close();
//			fos.close();
//		}
//		return true;
//	}

	public static void makeHome(String home) throws Exception {
		File homedir = new File(home);
		if (homedir.exists())
			return;
		try {
			homedir.mkdirs();
		} catch (Exception ex) {
			throw new Exception("Can not mkdir :" + home + " Maybe include special charactor!");
		}
	}

//	public static void copyDir(String sourcedir, String destdir) throws Exception {
//		File dest = new File(destdir);
//		File source = new File(sourcedir);
//		String[] files = source.list();
//		try {
//			makeHome(destdir);
//		} catch (Exception ex) {
//			throw new Exception("CopyDir:" + ex.getMessage());
//		}
//		for (int i = 0; i < files.length; ++i) {
//			String sourcefile = source + File.separator + files[i];
//			String destfile = dest + File.separator + files[i];
//			File temp = new File(sourcefile);
//			if (!(temp.isFile()))
//				continue;
//			try {
//				copy(sourcefile, destfile);
//			} catch (Exception ex) {
//				throw new Exception("CopyDir:" + ex.getMessage());
//			}
//		}
//	}

	public static void recursiveRemoveDir(File directory) throws Exception {
		if (!(directory.exists())) {
			throw new IOException(directory.toString() + " do not exist!");
		}
		String[] filelist = directory.list();
		File tmpFile = null;
		for (int i = 0; i < filelist.length; ++i) {
			tmpFile = new File(directory.getAbsolutePath(), filelist[i]);
			if (tmpFile.isDirectory()) {
				recursiveRemoveDir(tmpFile);
			} else {
				if (!(tmpFile.isFile()))
					continue;
				try {
					tmpFile.delete();
				} catch (Exception ex) {
					throw new Exception(tmpFile.toString() + " can not be deleted " + ex.getMessage());
				}
			}
		}
		try {
			directory.delete();
		} catch (Exception ex) {
			throw new Exception(directory.toString() + " can not be deleted " + ex.getMessage());
		} finally {
			filelist = (String[]) null;
		}
	}

	public static boolean remoteFileRead(String sUrl, String path) {
		try {
			URL url = new URL(sUrl);
			URLConnection conn = url.openConnection();
			conn.connect();
			HttpURLConnection httpConn = (HttpURLConnection) conn;
			if (httpConn.getResponseCode() == 200) {
				System.out.println("Connect to " + sUrl + " failed,return code:" + httpConn.getResponseCode());
				return false;
			}
			File file = createFile(conn.getInputStream(), path);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public static String getFileString(String fileName) throws Exception {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		String line = null;
		while ((line = br.readLine()) != null) {
			sb.append(line + "\n");
		}
		return sb.toString();
	}

	public static String getFileString(String fileName, String charSet) throws Exception {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), charSet));
		String line = null;
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		return sb.toString();
	}

	public static InputStream stringToInputStream(String str) throws UnsupportedEncodingException {
		ByteArrayInputStream stream = new ByteArrayInputStream(str.getBytes("UTF-8"));
		return stream;
	}
}