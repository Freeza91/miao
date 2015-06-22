package helpers;

import android.os.Environment;


public interface StatusCode {

	public final int POST_MESSAGE_SUCCESS = 0;
	public final int QUIT_POST_MESSAGE = 1;
	public final int POST_CAMERA_SUCCESS = 2;
	public final int QUIT_POST_CAMERA = 3;

	public final int CAMERA_REQUEST = 100;
	public final int NO_CAMERA_PERMISSION = 102;
	
	public final String SDCARD = Environment.getExternalStorageDirectory()
			+ "/miao/";
}
