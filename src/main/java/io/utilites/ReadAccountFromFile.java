package io.utilites;

import io.entity.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReadAccountFromFile {

    private List<Account> accountList = new ArrayList<>();
    private Logger logger = LoggerFactory.getLogger(ReadAccountFromFile.class);

    public List readAccFromFile(String path) throws ClassNotFoundException {
        File file = new File(path);
        for(File files : Objects.requireNonNull(file.listFiles())){
            try (ObjectInputStream objectOS = new ObjectInputStream(new FileInputStream(files))) {
                accountList.add((Account)objectOS.readObject());
            } catch (IOException e) {
                logger.info("Файл не существует!");
            }
        }
        return accountList;
    }
}
