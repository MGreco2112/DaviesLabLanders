package com.davies.lab.lander;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ExcelTesting {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(new File("C:\\Users\\Owner\\IdeaProjects\\lander\\src\\main\\resources\\20230914_1400_A7CT-USB_0723_222928_A - Copy - Copy.csv"));
        scanner.useDelimiter(",");
        String temp = "";

        while (!Objects.equals(temp, "[Item]")) {
            temp = scanner.nextLine();

            System.out.println(temp);
        }


    }
}
