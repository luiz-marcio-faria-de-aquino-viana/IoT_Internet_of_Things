/*
 * COPPE / UFRJ - Universidade Federal do Rio de Janeiro
 * PESC - Programa de Engenharia de Sistemas e Computacao
 *
 * Disciplina: CPS730 - Internet das Coisas
 * Professor: Claudio Miceli
 *
 * Trabalho Final - Emergency Sound Detection
 * Data: 25/AGO/2022
 *
 * Nome: Luiz Marcio Faria de Aquino Viana, M.Sc.
 * Formacao: Engenheiro Eletricista com Enfase em Engenharia de Sistemas e Computação
 * DRE: 120048833
 * CPF: 024.723.347-10
 * RG: 08855128-8 IFP-RJ
 * Registro: 2000103581 CREA-RJ
 * E-mail: luiz.marcio.viana@gmail.com
 *         lmarcio@cos.ufrj.br
 * Tel.: +55-21-99983-7207
 *
 * FileUtil.java
 */

package br.com.tlmv.emsdapp.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.tlmv.emsdapp.data.FileData;

public class FileUtil 
{
//Public
	
	public static void listFileDir(
		List<FileData> lsFileData, 
		String curFullFilePath, 
		String[] extFilter, 
		boolean filesOnly)
	{
		File fCurFullFilePath = new File(curFullFilePath);
		
		String[] arrFilePath = fCurFullFilePath.list();
		for(String newFilePath : arrFilePath)
		{
			String newFullFilePath = String.format("%s/%s", curFullFilePath, newFilePath);
			
			File fNewFullFilePath = new File(newFullFilePath);			
			if( fNewFullFilePath.isDirectory() )
			{
				String newFileName = FileUtil.getFileName(newFullFilePath);
				String newFileExt = FileUtil.getFileExtension(newFullFilePath);
				Date newFileDataModificacao = new Date(fNewFullFilePath.lastModified());
				
				if( !filesOnly )
				{					
					FileData fileData = new FileData(
							newFullFilePath,
							newFileName,
							newFileExt,
							newFileDataModificacao,
							false);
					lsFileData.add(fileData);
				}				
				listFileDir(lsFileData, newFullFilePath, extFilter, filesOnly);
			}
			else
			{
				String newFileName = FileUtil.getFileName(newFullFilePath);
				String newFileExt = FileUtil.getFileExtension(newFullFilePath);
				Date newFileDataModificacao = new Date(fNewFullFilePath.lastModified());
				
				if(ListUtil.findPosInList(extFilter, newFileExt) != -1)
				{
					FileData fileData = new FileData(
						newFullFilePath,
						newFileName,
						newFileExt,
						newFileDataModificacao,
						true);
					lsFileData.add(fileData);
				}
			}
		}
	}
	
	public static String getFileNameWithoutExt(String fullFileName)
	{
		File fFullFileName = new File(fullFileName);

		String fileName = fFullFileName.getName();

		String[] arrFileName = StringUtil.split(fileName, '.');
		String resFileName = "";		
		if(arrFileName.length > 0) {	
			resFileName = arrFileName[0];
			for(int i = 1; i < arrFileName.length - 1; i++)
				resFileName = resFileName + "." + arrFileName[i];
		}
		return resFileName;
	}
	
	public static String getFileName(String fullFileName)
	{
		File fFullFileName = new File(fullFileName);

		String fileName = fFullFileName.getName();
		return fileName;
	}
	
	public static String getFileExtension(String fullFileName)
	{
		File fFullFileName = new File(fullFileName);

		String fileName = fFullFileName.getName();
		
		String[] arrFileExt = StringUtil.split(fileName, '.');
		String fileExt = "";
		if(arrFileExt.length > 0)
			fileExt = arrFileExt[arrFileExt.length - 1];
		return fileExt;
	}
	
	public static boolean isExistFile(String fileName)
	{
		File f = new File(fileName);
		return f.exists();
	}

	public static void renameFile(String srcFileName, String dstFileName)
	{
		try 
		{
			File srcFile = new File(srcFileName);
			File dstFile = new File(srcFileName);
			
			if( srcFile.exists() )
			{
				if( srcFile.isFile() )
					srcFile.renameTo(dstFile);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void copyFile(String srcFileName, String dstFileName)
	{
		FileInputStream sin = null;
		FileOutputStream sout = null;
		
		try 
		{
			File srcFile = new File(srcFileName);
			File dstFile = new File(dstFileName);
			
			if( srcFile.exists() )
			{
				if( srcFile.isFile() )
				{
					sin = new FileInputStream(srcFile);
			        sout = new FileOutputStream(dstFile);

			        byte[] buf = new byte[4096];
			        int numread = -1;
			        while((numread = sin.read(buf, 0, 4096)) != -1) 
			        	sout.write(buf, 0, numread);
			        sout.flush();
			        
					sout.close();					
					sin.close();
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static byte[] readBytes(String fileName)
	{
		byte[] bytes = null;
		try {
			InputStream fin = new FileInputStream(fileName);
			bytes = new byte[fin.available()];
			fin.read(bytes);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}
	
	public static String readData(String fileName)
	{
		StringBuilder sb = new StringBuilder();
		
		BufferedReader fin = null;		
		try
		{
			fin = new BufferedReader(new FileReader(fileName));
			String sbuf = null;
			while((sbuf = fin.readLine()) != null)
				sb.append(sbuf);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				if(fin != null) fin.close();				
			}
			catch(Exception e1)
			{
				
			}
		}
		
		return sb.toString();
	}
	
	public static String readData(File f)
	{
		StringBuilder sb = new StringBuilder();
		
		BufferedReader fin = null;		
		try
		{
			fin = new BufferedReader(new FileReader(f));
			String sbuf = null;
			while((sbuf = fin.readLine()) != null)
				sb.append(sbuf);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				if(fin != null) fin.close();				
			}
			catch(Exception e1)
			{
				
			}
		}
		
		return sb.toString();
	}
	
	public static boolean writeData(String fileName, String strData, boolean removeLines)
	{
		boolean rscode = false;
		
		if( removeLines )
			strData = StringUtil.removeLines(strData);
		
		BufferedWriter fout = null;		
		try
		{
			fout = new BufferedWriter(new FileWriter(fileName));
			fout.write(strData);

			rscode = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				if(fout != null) fout.close();				
			}
			catch(Exception e1)
			{
				
			}
		}
		
		return rscode;
	}
		
	public static boolean writeData(String fileName, byte[] arrData, int arrSz)
	{
		boolean rscode = false;
		
		FileOutputStream fout = null;		
		try
		{
			fout = new FileOutputStream(new File(fileName));
			fout.write(arrData, 0, arrSz);

			rscode = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				if(fout != null) fout.close();				
			}
			catch(Exception e1)
			{
				
			}
		}
		
		return rscode;
	}
	
	public static boolean writeData(String fileName, ArrayList<String> lsData)
	{
		boolean rscode = false;
		
		BufferedWriter fout = null;		
		try
		{
			fout = new BufferedWriter(new FileWriter(fileName));
			for(String strData : lsData)
				fout.write(strData);

			rscode = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				if(fout != null) fout.close();				
			}
			catch(Exception e1)
			{
				
			}
		}
		
		return rscode;
	}
	
	public static boolean writeData(File f, String strData)
	{
		boolean rscode = false;
		
		BufferedWriter fout = null;		
		try
		{
			fout = new BufferedWriter(new FileWriter(f));
			fout.write(strData);

			rscode = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				if(fout != null) fout.close();				
			}
			catch(Exception e1)
			{
				
			}
		}
		
		return rscode;
	}
	
	public static boolean writeData(File f, byte[] arrData, int arrSz)
	{
		boolean rscode = false;
		
		FileOutputStream fout = null;		
		try
		{
			fout = new FileOutputStream(f);
			fout.write(arrData, 0, arrSz);

			rscode = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				if(fout != null) fout.close();				
			}
			catch(Exception e1)
			{
				
			}
		}
		
		return rscode;
	}
	
	public static boolean writeData(File f, ArrayList<String> lsData)
	{
		boolean rscode = false;
		
		BufferedWriter fout = null;		
		try
		{
			fout = new BufferedWriter(new FileWriter(f));
			for(String strData : lsData)
				fout.write(strData);

			rscode = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				if(fout != null) fout.close();				
			}
			catch(Exception e1)
			{
				
			}
		}
		
		return rscode;
	}
	
}
