package com.sec.ax.restful.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.log4j.Logger;

/**
 *
 * @author heesik.jeon
 *
 */

public class FileHelper {
    
    private static final Logger logger = Logger.getLogger(FileHelper.class);

    private static final int RANDOM_SEED = 1132164512;
    private static final int RANDOM_MAX = 0x7fff;

    private static final int ALPHA_MAX = 26;
    private static final int ALPHA_START = 97;

    /**
     * @param classpath
     * @return
     * @throws IOException
     */
    public static Reader getStream(String classpath) throws IOException {
        
        logger.debug("..");
        
        InputStream is = FileHelper.class.getClass().getResourceAsStream(classpath);
        
        Reader reader = new InputStreamReader(is, "utf-8");
        
        return reader;

    }

    /**
     * @param base
     * @param input
     * @param depth
     * @return
     */
    public static String hashdir(String base, String input, int depth) {
        
        logger.debug("..");

        StringBuffer folder;

        if (base.endsWith("/")) {
            folder = new StringBuffer(base);
        } else {
            folder = new StringBuffer(base + "/");
        }

        long hash = input.hashCode() & 0xffffffffL;
        
        for (int i = 0; i < depth; i++) {

            int mod = (int) (hash % ALPHA_MAX);
            folder.append((char) (ALPHA_START + mod));
            folder.append('/');
            
            hash = (hash * RANDOM_SEED) / (2 * (RANDOM_MAX+1)) % (RANDOM_MAX +1);

        }

        return folder.toString();
        
    }
    
    /**
     * @param foldername
     */
    public static void mkdir(String foldername) {
        
        logger.debug("..");

        File folder = new File(foldername);

        if (!folder.exists()) {
            folder.mkdirs();
        }

    }

    /**
     * @param foldername
     */
    public static void rmdir(String pathname) {
        
        logger.debug("..");

        File f = new File(pathname);

        for (String filename : f.list()) {

            String path = new StringBuffer(pathname + File.separator + filename).toString();
            
            File file = new File(path);
            
            if (file.isDirectory()) {
                rmdir(path);
            } else {
                file.delete();
            }
            
        }
        
        f.delete();

    }

    /**
     * @param foldername
     * @param filename
     * @return
     * @throws IOException
     */
    public static List<String> unzip(String foldername, String filename) throws IOException {
        
        logger.debug("..");
        
        List<String> list = new ArrayList<String>();
        
        byte[] buffer = new byte[1024];

        ZipInputStream zis = new ZipInputStream(new FileInputStream(new StringBuffer(foldername + File.separator + filename).toString()));
        ZipEntry ze;
        
        while ((ze = zis.getNextEntry()) != null) {
            
            File unzip = new File(foldername + File.separator + ze.getName());
            
            if (ze.getName().endsWith("/")) {
                
                String folder = new StringBuffer(foldername + File.separator + ze.getName()).toString();

                mkdir(folder);
                
            } else {
                
                int len;
                
                FileOutputStream fos = new FileOutputStream(unzip);

                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                
                fos.close();
                
                list.add(unzip.getAbsolutePath());
                
            }
            
        }

        zis.closeEntry();
        zis.close();
        
        return list;

    }

}
