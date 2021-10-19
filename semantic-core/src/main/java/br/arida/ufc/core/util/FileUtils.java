package br.arida.ufc.core.util;

import java.io.*;
import java.net.URL;
import java.util.logging.Logger;

/**
 * Created by gabriellopes9102 on 30/11/2016.
 */
public class FileUtils {
    private static final Logger log = Logger.getLogger(FileUtils.class.getName());

    public static File[] listFiles(File dir, final String ext) {
        if(dir.isDirectory()) {
            File[] filesXML = dir.listFiles(new FilenameFilter() {

                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith("."+ext);
                }
            });
            return filesXML;
        } else {
            //Não é um diretório
            return null;
        }
    }

    public static void createDirIfNotExists(String dir) {
        File tmp = new File(dir);
        boolean result = false;

        if(!tmp.exists()) {
            result = tmp.mkdir();

            if(!result) log.warning("Error while creating the ["+dir+"] Directory");
            else        log.info("Directory ["+tmp.getAbsolutePath()+"] created. . .");
        }
    }

    public static String readFile(File file) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader(file));
        StringBuffer sb = new StringBuffer();

        while(bf.ready()) {
            sb.append(bf.readLine() + "\n");
        }

        return sb.toString();
    }

    public static File getFileFromResource(Class _class, String file) throws FileNotFoundException {
        log.info("Criando arquivo ["+file+"] requisitado pela Classe ["+_class.getName()+"]. . .");
        URL tmp = _class.getClassLoader().getResource(file);
        File f = new File(tmp.getFile());
        if(!f.exists()) {
            log.warning("Classe [+"+_class.getName()+"] requisitou o arquivo ["+file+"], mas o arquivo não foi encontrado");
            throw new FileNotFoundException("File ["+file+"] not found.");
        }
        log.info("Arquivo ["+file+"] retornado com sucesso. . .");
        return f;
    }
}
