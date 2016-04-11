package com.xulf.util.common;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
	
	/**循环找到一个文件夹中的所有文件*/
	public static List<File> listAllFiles(File dir) throws IOException{
		//判断所给参数是否是存在的目录
		//创建存储数据结构， List files
		//获得所有的文件， 放到files
		//对于文件夹，将其返回的结果放到files中
		
		
		if(!dir.exists()){ //文件不存在 
			throw new IOException("No such directory");
		}
		
		List<File> files = new ArrayList<File>();
		//如果是文件则， 添加到files中， 并返回files
		if(dir.isFile()){
			files.add(dir);
			return files;
		}
		
		//否则遍历文件夹， 将内容加入到files
		File[] fs = dir.listFiles();
		for(File f: fs){
			if(f.isFile()){//是文件
				files.add(f);
			}
			
			if(f.isDirectory()){ //是文件夹， 就将其中文件全部加入
				files.addAll(listAllFiles(f));
			}
		}
		
		return files;
	}

	/**找到一个文件夹中的所有文件， 返回过滤后的文件List*/
	public static List<File> listAllFiles(File dir, FileFilter filter) throws IOException{
		List<File> files = listAllFiles(dir);
		List<File> result = new ArrayList<File>();
		
		for(File f: files){
			if(filter.accept(f)){
				result.add(f);
			}
		}
			
		return result;
	}
	
	/**源文件转码后写入目标文件*/
	public static void changeEncode(File in, String srcCharset, File out, String dstCharset){
		
	}
	
	/**赋值文件(夹)到指定文件夹*/
	public static void copy(String src, String dst) throws IOException{
		copy(new File(src), new File(dst), 0);
	}
	
	/**复制文件（夹）到指定文件夹*/
	public static void copy(File src, File dstDirectory) throws IOException{
		copy(src, dstDirectory, 0);
	}
	
	
	/**
	 * 将文件或者文件夹， 复制到指定文件夹
	 * @param src  源文件或者文件夹
	 * @param to 目标文件夹
	 * @param level 用来标志是否是最上层的复制
	 * 		如果level =0 ， 则是最一层复制， 检查文件夹是否存在
	 * 		如果level>0，则是文件夹内部的赋值， 不需要检查文件存在性问题
	 * @throws FileNotFoundException
	 * 		 如果源文件（夹）或者目标文件夹， 不存在， 抛出文件未找到异常
	 * @throws IOException
	 * 		如果目标不是文件夹， 抛出IOException异常
	 * 		如果目标地址已经有同名文件（夹）， 抛出IOExcepiton异常
	 */
	private static void copy(File src, File to, int level) throws FileNotFoundException, IOException{	
		//入口参数检查
		if(!src.exists()){ //如果源有一个不存在， 抛出FileNotFoundException
			throw new FileNotFoundException("源文件不存在！");
		}
		
		if(!to.exists()){ //如果目标有一个不存在， 抛出FileNotFoundException
			throw new FileNotFoundException("目标文件不存在！");
		}
		
		if(!to.isDirectory()){//如果目标不是文件夹
			throw new IOException("目标地址不是文件夹！");
		}
		
		File dst = new File(to, src.getName()); // 在目标地址创建文件对象
		if(level==0 && dst.exists()){
			throw new IOException("文件（夹）已经存在！");
		}
		
		//复制文件
		//如果源是文件， 则直接复制到目标目录, 并结束方法
		if(src.isFile()){
			copyFile(src, dst);
			return;
		}
		
		
		//如果源是目录， 在目标目录建立相应目录（如果已经存在， 抛出IOException文件夹已经存在
		if(src.isDirectory()){
			//在目的地建立目录
			dst.mkdir();
			//遍历源目录， 复制到目标目录
			File[] srcs = src.listFiles();
			for(File file:srcs){
				//如果是文件， 则复制src中的文件 到 dst目录之下
				if(file.isFile()){
					copyFile(file, new File(dst, file.getName()));
				}
				//如果是目录， 则递归
				copy(file, dst, ++level); //复制src下的目录，到dst中
			}
		}
	}
	
	
	/**
	 *  复制文件
	 * @param srcFile 源文件
	 * @param dstFile 目标文件
	 * @throws SourceFileException 
	 * 		如果源文件不存在， 或者源文件不是文件时， 抛出源文件异常
	 * @throws AimFileException
	 * 		如果目标文件已经存在， 抛出文件目标文件异常
	 * @throws IOException
	 * 		如果发生读写权限等其它IO异常， 抛出IOException
	 */
	public static void copyFile(File srcFile, File dstFile) throws SourceFileException, AimFileException, IOException{
		//检查入口参数
		if(!srcFile.exists()){
			System.out.println("源文件不存在！");
			throw new AimFileException("源文件不存在！");
		}
		
		if(!srcFile.isFile()){ 
			System.out.println("只能复制文件！");
			throw new SourceFileException("要复制的对象不是文件！");
		}
		
		if(dstFile.exists()){
			System.out.println("目标文件已存在！");
			throw new AimFileException("目标文件已存在");
		}
		
		
		//复制文件
		FileInputStream src = null;
		FileOutputStream dst = null;
		
		try {
			//如果目标文件所在文件夹不存在， 先创建文件夹
			File dstDir = dstFile.getParentFile();
			if(!dstDir.exists()){ //创建目标目录
				dstDir.mkdirs();
			}
			
			src = new FileInputStream(srcFile);
			dst = new FileOutputStream(dstFile);
			// 遍历源文件字节
			byte[] buf = new byte[1024 * 8];
			int n;
			while ((n = src.read(buf)) != -1) {// 读出字节到buf
				// 将buf写到目标文件
				dst.write(buf, 0, n);
			}
		}catch(IOException e){//文件复制出错
			System.out.println("------------------------------");
			System.out.println("文件复制过程出错！");
			e.printStackTrace();
			System.out.println("------------------------------");
			throw e;
		}finally{ //安全关闭文件
			if(src != null){
				try{
					src.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
			
			if(dst != null){
				try{
					src.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}

	}
	
	/**复制文件*/
	public  static void copyFile(String srcFile, String dstFile) throws IOException{
		copyFile(new File(srcFile), new File(dstFile));
	}
}


//TODO
	//测试copyFile  ------------ 完全测试通过， 书写已经规范化
	//测试copy
	//测试listAllFiles
