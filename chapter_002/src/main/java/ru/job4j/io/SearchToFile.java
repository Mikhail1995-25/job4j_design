package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import static java.nio.file.FileVisitResult.CONTINUE;

public class SearchToFile extends SimpleFileVisitor<Path> {

    private final Function<Path, Boolean> function;
    private final List<File> files = new ArrayList<>();

    public SearchToFile(Function<Path, Boolean> function) {
        this.function = function;
    }

    public List<File> getFiles() {
        return files;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        boolean b = function.apply(file);
        if (b) {
            files.add(file.toFile());
        }
        return CONTINUE;
    }
}
