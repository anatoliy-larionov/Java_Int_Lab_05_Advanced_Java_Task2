package io.utilites;

import io.entity.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Random;

public class WriteAccountInFile {

    private Logger logger = LoggerFactory.getLogger(WriteAccountInFile.class);
    private Random random = new Random();

    public void writeAccountInFile(String path, Account account) throws IOException {
        File file = new File(path);
        try (ObjectOutputStream objectOS = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOS.writeObject(account);
        } catch (FileNotFoundException e) {
            logger.info("Файл не найден");
        }
    }

    public void getRandomAccount() throws IOException {
        for (int i = 1; i < 20; i++) {
            writeAccountInFile(".\\src\\main\\resources\\" + i + " .account",
                    new Account(i,"аккаунта: " + i, random.nextInt(9000)));
        }
    }
}
