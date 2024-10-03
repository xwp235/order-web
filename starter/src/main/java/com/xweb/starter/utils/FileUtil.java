package com.xweb.starter.utils;

import org.apache.commons.lang3.StringUtils;
import org.mozilla.universalchardet.UniversalDetector;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {

    public static String getFileEncoding(String filepath) {
        byte[] buf = new byte[4096];
        try(var fis = Files.newInputStream(Paths.get(filepath))) {
            var detector = new UniversalDetector();
            int nread;
            while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
                detector.handleData(buf, 0, nread);
            }
            detector.dataEnd();
            var encoding = detector.getDetectedCharset();
            if (StringUtils.isNotBlank(encoding)) {
                System.out.println("Detected encoding = " + encoding);
            } else {
                System.out.println("No encoding detected.");
            }
            detector.reset();
        } catch (IOException ex) {
            return null;
        }
        return null;
    }

}
