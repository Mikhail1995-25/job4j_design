package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class SearchByCriterion {
    public static void main(String[] args) throws IOException {
        SearchByCriterion searchByCriterion = new SearchByCriterion();
        Args args1 = new Args(args);
        args1.attributes(args);
        List<File> file = searchByCriterion.expected(args1);
        searchByCriterion.write(file, args1);
    }

    public Predicate<Path> attributeLookUp(Args args) {
       Predicate<Path> predicate = null;
       if (args.getValues().containsKey(args.full())) {
           predicate = new RegexSearch(args.name());
       } else if (args.getValues().containsKey(args.mask())) {
           predicate = new RegexSearch(pattern(args.name()));
       } else if (args.getValues().containsKey(args.name())) {
           predicate = new RegexSearch(args.name());
       }
       return predicate;
    }

    public List<File> expected(Args args) throws IOException {
        Searchs searchs = new Searchs(attributeLookUp(args));
        Path path = Paths.get(args.directory());
        Files.walkFileTree(path, searchs);
        return searchs.getFiles();
    }

    public void write(List<File> files, Args line) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(line.write())))) {
            for (File file : files) {
                out.print(file.getName() + System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String pattern(String line) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            char a = line.charAt(i);
            if (a == '*') {
                str.append(".*");
            } else if (a == '.') {
                str.append("\\.");
            } else {
                str.append(a);
            }
        }
        return str.toString();
    }

    private static class RegexSearch implements Predicate<Path> {
        private Pattern pattern;

        public RegexSearch(String name) {
            this.pattern = Pattern.compile(name);
        }

        @Override
        public boolean test(Path path) {
            return pattern.matcher(path.toFile().getName()).matches();
        }
    }
}
