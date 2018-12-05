package io;

import io.service.DemoService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        DemoService.start();
    }
}
