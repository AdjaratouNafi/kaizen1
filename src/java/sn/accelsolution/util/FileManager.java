 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.http.Part;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author mac
 */
public final class FileManager {

    private final static String PRODUCTION_FILE_DIR = "/var/www/html/";

    //private final static String PRODUCTION_FILE_DIR = "C:\\Users\\DV7\\Desktop\\";

    public static String FILE_DIR = FileManager.PRODUCTION_FILE_DIR;

    public static String getRootDirectory() {
        FILE_DIR = PRODUCTION_FILE_DIR;
        return FILE_DIR;
    }

    public static String getImageRootDirectory() {
        String root = getRootDirectory();
        return root + "InitLogo/";
    }

    public static String getIconDirectory() {
        String root = FileManager.getRootDirectory();
        return root + "InitLogo/";
    }

    /**
     * Représente la récupération du nom du fichier.
     *
     * @param filePart L'image téléchargé à copier (name, byte[], ...).
     * @return le nom du fichier .
     */
    public static String getSubmittedFileName(Part filePart) {
        String header = filePart.getHeader("content-disposition");
        if (header == null) {
            return null;
        }
        for (String headerPart : header.split(";")) {
            if (headerPart.trim().startsWith("filename")) {
                return headerPart.substring(headerPart.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    public static String CopyAvatar(Part file, String filename) throws IOException {
        if (null == filename || filename.length() == 0) {
            String result = CopyFile(file, FileManager.getImageRootDirectory(), "Contrat001");
            return result;
        } else {
            String fileDest = FileManager.getImageRootDirectory() + filename;
            Path destination = Paths.get(fileDest);
            if (Files.exists(destination)) {
                // replace file
                if (!Files.deleteIfExists(destination)) {
                    return "error";
                }
            }

            String result = CopyFile(file, FileManager.getImageRootDirectory(), FilenameUtils.getName(filename));
            return result;
        }
    }

    /**
     * Représente la copie de fichier.
     *
     * @param file Le fichier téléchargé à copier.
     * @param path Répertoire de destination.
     * @param filename Le nom du fichier de destination.
     * @return
     */
    public static String CopyFile(Part file, String path, String filename) throws IOException {
        // Récupère le nom du fichier téléchargé
        String lFilenameTelecharge = getSubmittedFileName(file);
        // Normalisé le nom du fichier téléchargé
        lFilenameTelecharge = FilenameUtils.getName(lFilenameTelecharge);
        // Récupère le nom du fichier
        if (null == filename || filename.length() == 0) {
            filename = lFilenameTelecharge;
        } else {
            // Récupère l'extension du fichier téléchargé
//            String lExtention = FilenameUtils.getExtension(lFilenameTelecharge);
            filename = lFilenameTelecharge;
        }

        // Création du nom de fichier complét
        path = path + filename;

        Path destination = Paths.get(path);
        InputStream bytes = file.getInputStream();
        long result = Files.copy(bytes, destination);
        if (result > 0) {
            return "succes";
        } else {
            return "error";
        }
    }

//
    /**
     * Représente la copie de fichier.
     *
     * @param from Le ficher à copié.
     * @param to Le nom du fichier de destination.
     * @return La 'vrai' si la copie est ok, sinon 'faux'.
     *
     * public boolean CopyFile(String from, String to) { if (from == to) {
     * return false; } // TODO // ... final Path destination=null; InputStream
     * bytes = null; boolean result = Files.copy(bytes,destination); if (copyOk)
     * { return true; } return false; }
     */
//
//    public boolean CopyDirectory(String from, String to) {
//        // TODO
//        // ...		
//        if (okkkkk) {
//            return true;
//        }
//        return false;
//    }
}
