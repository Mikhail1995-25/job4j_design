package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<File> source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(target))) {
            for (File file : source) {
                zip.putNextEntry(new ZipEntry(file.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<File> distribution(ArgZip argZip) throws IOException {
        SearchToFile searchToFile = new SearchToFile(p -> !p.toFile().getName().endsWith(argZip.exclude()));
        Path path = Paths.get(argZip.directory());
        Files.walkFileTree(path, searchToFile);
        return searchToFile.getFiles();
    }

        public static void main(String[] args) throws IOException {
            new Zip().packSingleFile(
                    new File("./chapter_003/pom.xml"),
                    new File("./chapter_003/pom.zip")
            );
            ArgZip argZip = new ArgZip(args);
            argZip.separator(args);
            Zip zip = new Zip();
            File file = new File(argZip.outPut());
            List<File> list = zip.distribution(argZip);
            zip.packFiles(list, file);
        }
}
