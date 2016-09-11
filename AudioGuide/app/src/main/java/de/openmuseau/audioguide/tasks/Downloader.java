package de.openmuseau.audioguide.tasks;

import android.os.AsyncTask;
import android.os.Environment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Helps to download resources from the web
 *
 * Created by david on 06.09.16.
 */
public class Downloader extends AsyncTask<Object, Object, Object> {

    public static final String FOLDER = ".openmuseau";

    private String requestUrl;
    private String name;

    private FileOutputStream fos;


    public Downloader(String requestUrl, String name) {
        this.requestUrl = requestUrl;
        this.name = name;
    }


    private File createFolder() {

        File sdcard = Environment.getExternalStorageDirectory() ;
        File folder = new File(sdcard.getAbsoluteFile(), FOLDER);
        folder.mkdir();
        return folder;
    }


    @Override
    protected Object doInBackground(Object... objects) {

        try {

            //Read from the URL
            URL url = new URL(requestUrl);
            URLConnection conn = url.openConnection();
            InputStream in = conn.getInputStream();


            //Write to the file
            File folder = createFolder();
            File f = new File(folder.getAbsoluteFile(), name ) ;
            OutputStream out = new FileOutputStream(f);


            byte[] buffer = new byte[4096];
            int n;

            while ((n = in.read(buffer)) > 0) {
                out.write(buffer, 0, n);
            }

            out.close();


        } catch (Exception ex) {

           ex.printStackTrace();
        }

        //Return nothing?
        return null;
    }


    /**
     * Get the  file
     * @param name
     * @return
     */
    public static File getFile(String name) {

        try {

            File sdcard = Environment.getExternalStorageDirectory() ;
            File folder = new File(sdcard.getAbsoluteFile(), FOLDER);
            File f = new File(folder.getAbsolutePath(), name);

            if (f.exists()) return f;


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}
