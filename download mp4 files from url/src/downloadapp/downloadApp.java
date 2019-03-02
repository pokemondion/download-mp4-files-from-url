package downloadapp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class downloadApp {

	public static void main(String[] args) throws MalformedURLException, IOException {
		// TODO Auto-generated method stub
		
		String destinationDir = "W:\\diont\\Desktop\\Test Folder";
		String localFileName = "vie.mp4";

		URL url;
		byte[] buf;
		int byteRead, byteWrite = 0;
		url = new URL(getFinalLocation("https://youtu.be/Qv306u_G6EI"));
		
		BufferedOutputStream outStream = new BufferedOutputStream(new FileOutputStream(destinationDir + "\\" + localFileName));
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		InputStream is = conn.getInputStream(); 
		
		buf = new byte[1024];
		while ((byteRead = is.read(buf)) != -1) {
            outStream.write(buf, 0, byteRead);
            byteWrite += byteRead;
        }
		
		is.close();
		outStream.close();
	}

	public static String getFinalLocation(String address) throws IOException{
	    URL url = new URL(address);
	    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	    int status = conn.getResponseCode();
	    if (status != HttpURLConnection.HTTP_OK) 
	    {
	        if (status == HttpURLConnection.HTTP_MOVED_TEMP
	            || status == HttpURLConnection.HTTP_MOVED_PERM
	            || status == HttpURLConnection.HTTP_SEE_OTHER)
	        {
	            String newLocation = conn.getHeaderField("Location");
	            return getFinalLocation(newLocation);
	        }
	    }
	    return address;
	}
}
