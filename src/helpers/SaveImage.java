package helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.util.Log;

public class SaveImage implements StatusCode {

	String path;

	public SaveImage() {
		this.path = SDCARD + "images/";
	}
	public SaveImage(String path) {
		this.path = SDCARD + path + "/";
	}

	@SuppressLint("SimpleDateFormat")
	public boolean save(Bitmap bmp) {
		Date now = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("yyMMdd_HHmmss");

		String filePath = path + formater.format(now) + "rudy" + ".jpg";
		File dir = new File(path);

		Log.d("filepath", filePath);
		Log.d("path", path);

		File file = new File(filePath);
		FileOutputStream oStream = null;
		
		if (!dir.exists() && !dir.isDirectory()) {
			dir.mkdirs();
		}

		try {
			file.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			oStream = new FileOutputStream(file);
			bmp.compress(Bitmap.CompressFormat.JPEG, 100, oStream); // 100是照片质量，0-100，越大越好
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			try {
				oStream.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			try {
				oStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}

		return true;
	}
}
