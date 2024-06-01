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

        // Leer los usuarios del archivo Excel
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

    /**
     * Método que permite obtener un usuario por su ID
     * @param id ID del usuario
     * @return Devuelve el usuario si existe, en caso contrario devuelve un Optional vacío
     */
    public Optional<User> getUserID(Integer id) {
        Optional<User> optional = Optional.empty();

        for (User user : userList)
        {
            if (user.getId() == id)
                optional = Optional.of(user);
        }
        return optional;
    }

    /**
     * Método que permite obtener la lista de usuarios
     * @return Devuelve la lista de usuarios
     */
    public List<User> getUserList() {
        return userList;
    }


    /**
     * Método que permite añadir un usuario a la lista de usuarios
     * @param username Nombre de usuario
     * @param password Contraseña
     * @param email Correo electrónico
     * @param name Nombre
     * @param surname1 Primer apellido
     * @param surname2 Segundo apellido
     * @param phone Teléfono
     * @return Devuelve true si el usuario se ha añadido correctamente, false en caso contrario
     */
    public boolean addUser(String username, String password, String email, String name, String surname1, String surname2, int phone) {
        if (InUserList(username, email))
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

    /**
     * Método que permite comprobar si un usuario existe en la lista de usuarios
     * @param username Nombre de usuario
     * @param email Correo electrónico
     * @return Devuelve true si el usuario existe, false en caso contrario
     */
    private boolean InUserList(String username, String email) {
        for (User user : userList) {
            if (user.getUsername().equals(username) || user.getEmail().equals(email))
                return true;
        }
        return false;
    }

    /**
     * Método que permite a comprobar si un usuario y una contraseña son correctos
     * @param username Nombre de usuario
     * @param password Contraseña
     * @return Devuelve true si el usuario exite y su contraseña es correcta, false en caso contrario
     */
    public boolean logIn(String username, String password) {
        for (User user : userList) {
            if (user.getUsername().equals(username))
                return user.getPassword().equals(password);
        }
        return false;
    }
}
