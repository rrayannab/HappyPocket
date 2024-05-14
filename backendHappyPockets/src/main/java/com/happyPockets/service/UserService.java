package com.happyPockets.service;

import com.happyPockets.api.model.User;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private List<User> userList;
    private int newInsert = 1;
    private final String rutaArchivo = ".\\src\\main\\java\\com\\happyPockets\\service\\usuarios.xlsx";

    public UserService() {
        userList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(new File(rutaArchivo)))
        {
            // Abrir el libro de Excel
            Workbook libro = WorkbookFactory.create(fis);

            Sheet hoja = libro.getSheetAt(0);

            int i = 0;

            for (Row fila : hoja) {
                if (fila.getRowNum() < 1) continue;
                if (fila.getCell(0) == null) break;


                try {
                    String username = fila.getCell(0).getStringCellValue();
                    String password = fila.getCell(1).getStringCellValue();
                    String email = fila.getCell(2).getStringCellValue();
                    String name = fila.getCell(3).getStringCellValue();
                    String surname1 = fila.getCell(4).getStringCellValue();
                    String surname2 = fila.getCell(5).getStringCellValue();
                    int phone = (int)fila.getCell(6).getNumericCellValue();
                    int id = ++i;

                    User user = new User(id,username,password,email,name,surname1,surname2,phone);

                    userList.add(user);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            libro.close();
            newInsert = i+1;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public Optional<User> getUserID(Integer id) {
        Optional<User> optional = Optional.empty();

        for (User user : userList)
        {
            if (user.getId() == id)
                optional = Optional.of(user);
        }
        return optional;
    }

    public List<User> getUserList() {
        return userList;
    }


    public boolean addUser(String username, String password, String email, String name, String surname1, String surname2, int phone) {
        if (InUserList(username))
            return false;
        try (FileInputStream fis = new FileInputStream(new File(rutaArchivo)))
        {
            Workbook libro = WorkbookFactory.create(fis);

            Sheet hoja = libro.getSheetAt(0);

            Row fila = hoja.createRow(newInsert);

            int id = newInsert;
            fila.createCell(0).setCellValue(username);
            fila.createCell(1).setCellValue(password);
            fila.createCell(2).setCellValue(email);
            fila.createCell(3).setCellValue(name);
            fila.createCell(4).setCellValue(surname1);
            fila.createCell(5).setCellValue(surname2);
            fila.createCell(6).setCellValue(phone);

            FileOutputStream fos = new FileOutputStream(rutaArchivo);
            libro.write(fos);

            User user = new User(id,username,password,email,name,surname1,surname2,phone);

            userList.add(user);

            libro.close();
            newInsert++;

            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    private boolean InUserList(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username))
                return true;
        }
        return false;
    }
}
