package org.rw.challenges.snip.util;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.StringTokenizer;


public class InputReader implements Closeable {
    private final BufferedReader reader;
    private StringTokenizer stringTokenizer;

    public InputReader() {
        this(new InputStreamReader(System.in));
    }

    public InputReader(Reader in) {
        reader = new BufferedReader(in);
    }

    public int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    public String next() throws IOException {
        while ((stringTokenizer == null) || !stringTokenizer.hasMoreElements()) {
            stringTokenizer = new StringTokenizer(reader.readLine());
        }
        return stringTokenizer.nextToken();
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
