package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CharsetEcd {
	String ReaderEcd; // 读取文件的字符编码设置
	String outEcd; // 写入文件的字符编码设置

	/**
	 * 功能：通过构造方法设置编码方式
	 * 
	 * @param ReaderEcd
	 * @param outEcd
	 */
	public CharsetEcd(String ReaderEcd, String outEcd) {
		this.ReaderEcd = ReaderEcd;
		this.outEcd = outEcd;
	}

	/**
	 * 功能：更改单文件字符编码
	 * 
	 * @param file 文件
	 * @throws IOException
	 */
	public void charsetEnc(File file) throws IOException {
		String context = "";
//File file = new File(arg0);
		InputStreamReader is = new InputStreamReader(new FileInputStream(file), ReaderEcd);
		BufferedReader bdf = new BufferedReader(is);
//
		String str = null;
		while ((str = bdf.readLine()) != null) {
			context += str + "\n";
		}
		OutputStreamWriter os = new OutputStreamWriter(new FileOutputStream(file), outEcd);
		BufferedWriter bdw = new BufferedWriter(os);
		bdw.write(context);
		bdw.close();
		os.close();
		bdf.close();
		is.close();
	}

	/**
	 * 功能：更改多文件字符编码
	 * 
	 * @param nameSuffix 文件后缀名
	 * @param folder     文件夹
	 * @throws IOException
	 */
	public void doFilesEcd(File folder, String nameSuffix) throws IOException {
		File[] files = folder.listFiles();
		for (File f : files) {
			if (f.isDirectory()) {
				doFilesEcd(f, nameSuffix);
			} else {
				if (f.getName().endsWith(nameSuffix)) {
					charsetEnc(f);
					System.out.println(f.getPath());
				}
			}
		}
	}
}