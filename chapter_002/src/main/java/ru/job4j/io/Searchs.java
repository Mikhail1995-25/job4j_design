package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

public class Searchs extends SimpleFileVisitor<Path> {
    private List<File> files = new ArrayList<>();
    private Predicate predicate;

    public Searchs(Predicate predicate) {
        this.predicate = predicate;
    }

    public List<File> getFiles() {
        return files;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        boolean job = true;
        if (job) {
            files.add(file.toFile());
        }
        return CONTINUE;
    }

}
