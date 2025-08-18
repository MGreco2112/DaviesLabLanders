package com.davies.lab.lander;

import com.davies.lab.lander.FormattedModels.ResponseBody.Head.CTDHeadResponse;
import com.davies.lab.lander.HelperClasses.StringFormatting;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ExcelTesting {

    public static void main(String[] args) throws IOException {
        List<String> output = new ArrayList<>();
        List<String> keyNames = new ArrayList<>();
        HashMap<String, String> valuesMap = new HashMap<>();

        Scanner scanner = new Scanner(new File("C:\\Users\\Owner\\IdeaProjects\\lander\\src\\main\\resources\\20230914_1400_A7CT-USB_0723_222928_A - Copy - Copy.csv"));
        scanner.useDelimiter(",");
        String temp = "";

        while (!Objects.equals(temp, "[Item]")) {
            temp = scanner.nextLine();

            if (temp.charAt(0) != '/' && temp.charAt(0) != '[') {
                output.add(temp);
            }
        }

        for (String datapoint : output) {
            String[] hold = datapoint.split("=");
            keyNames.add(hold[0]);

            valuesMap.put(hold[0], hold[1].stripTrailing());
        }

        for (String key : valuesMap.keySet()) {
            System.out.println(key + ": " +valuesMap.get(key));
        }

        CTDHeadResponse testResponse = new CTDHeadResponse(
                null,
                valuesMap.get(keyNames.get(0)),
                valuesMap.get(keyNames.get(1)),
                valuesMap.get(keyNames.get(2)),
                Integer.parseInt(valuesMap.get(keyNames.get(3))),
                Integer.parseInt(valuesMap.get(keyNames.get(4))),
                Integer.parseInt(valuesMap.get(keyNames.get(5))),
                Integer.parseInt(valuesMap.get(keyNames.get(6))),
                Integer.parseInt(valuesMap.get(keyNames.get(7))),
                Integer.parseInt(valuesMap.get(keyNames.get(8))),
                Integer.parseInt(valuesMap.get(keyNames.get(9))),
                Integer.parseInt(valuesMap.get(keyNames.get(10))),
                StringFormatting.formatDateString(valuesMap.get(keyNames.get(11))),
                StringFormatting.formatDateString(valuesMap.get(keyNames.get(12))),
                Double.parseDouble(valuesMap.get(keyNames.get(13))),
                Integer.parseInt(valuesMap.get(keyNames.get(14))),
                Integer.parseInt(valuesMap.get(keyNames.get(15))),
                Integer.parseInt(valuesMap.get(keyNames.get(16))),
                Double.parseDouble(valuesMap.get(keyNames.get(17))),
                StringFormatting.formatCoefDateString(valuesMap.get(keyNames.get(18))),
                Double.parseDouble(valuesMap.get(keyNames.get(19)).split(",")[0]),
                Double.parseDouble(valuesMap.get(keyNames.get(20)).split(",")[0]),
                Double.parseDouble(valuesMap.get(keyNames.get(21)).split(",")[0]),
                Double.parseDouble(valuesMap.get(keyNames.get(22)).split(",")[0]),
                Integer.parseInt(valuesMap.get(keyNames.get(23))),
                Integer.parseInt(valuesMap.get(keyNames.get(24))),
                valuesMap.get(keyNames.get(25)),
                valuesMap.get(keyNames.get(26)),
                Integer.parseInt(valuesMap.get(keyNames.get(27))),
                Integer.parseInt(valuesMap.get(keyNames.get(28))),
                Integer.parseInt(valuesMap.get(keyNames.get(29))),
                null
        );
    }
}
