package org.marchello.service;

import org.apache.poi.xwpf.usermodel.*;
import org.marchello.model.Person;
import org.marchello.model.Svc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DocService {
    public static void createDoc(Person person, boolean flag, List<Svc> selectedServices) throws IOException {
        FileInputStream fis = new FileInputStream("files/pattern.docx");
        XWPFDocument doc = new XWPFDocument(fis);

        int result = 0;
        for (Svc scv : selectedServices)
            result += Integer.parseInt(scv.getCost().replace(",",""));
        String costr =String.valueOf(result).substring(0, String.valueOf(result).length()-2);
        String costk = String.valueOf(result).substring(String.valueOf(result).length()-2);

        XWPFTable table = doc.getTables().get(0);
        XWPFTableRow tablerow;

        int i = 1;
        for (Svc svc : selectedServices) {
            tablerow = table.createRow();
            tablerow.getCell(0).setText(i + ".");
            tablerow.getCell(1).setText(svc.getName());
            tablerow.getCell(2).setText(svc.getCost());
            i++;
        }

        CTHMerge hMerge1 = CTHMerge.Factory.newInstance();
        hMerge1.setVal(STMerge.RESTART);
        CTHMerge hMerge2 = CTHMerge.Factory.newInstance();
        hMerge2.setVal(STMerge.CONTINUE);

        tablerow = table.createRow();
        tablerow.getCell(0).getCTTc().addNewTcPr().setHMerge(hMerge1);
        tablerow.getCell(1).getCTTc().addNewTcPr().setHMerge(hMerge2);

        tablerow.getCell(0).getParagraphs().get(0).createRun().setText("Итого:");
        tablerow.getCell(0).getParagraphs().get(0).getRuns().get(0).setBold(true);
        tablerow.getCell(2).getParagraphs().get(0).createRun().setText(costr + "," + costk);
        tablerow.getCell(2).getParagraphs().get(0).getRuns().get(0).setBold(true);


        //По абзацам
        for (XWPFParagraph p : doc.getParagraphs())
            for (XWPFRun r : p.getRuns()) {
                String text = r.getText(0);
                if (text == null) continue;
                if (text.contains("{$date}"))
                    text = text.replace("{$date}", new SimpleDateFormat("«dd» MM yyyy г.").format(new Date()));
                if (text.contains("{$post_employee}"))
                    text = text.replace("{$post_employee}", flag ? "заместителя заведующего" : "ведущего научного сотрудника");
                if (text.contains("{$fio_employee}"))
                    text = text.replace("{$fio_employee}", flag ? "Бадыгиной Натальи Александровны," : "Руденковой Татьяны Владимировны,");
                if (text.contains("{$from_employee}"))
                    text = text.replace("{$from_employee}", flag ? "29.08.2017 № 14-10/2473," : "02.07.2019 № 14-09/2109,");
                if (text.contains("{$fio_client}"))
                    text = text.replace("{$fio_client}", person.getSurname() + " " + person.getName()
                            + " " + person.getPatronymic() + ",");
                if (text.contains("{$passport_client}"))
                    text = text.replace("{$passport_client}", person.getSeriesPassport()
                            + " №" + person.getNumberPassport() + ",  идентификационный номер " + person.getIdNumberPassport()
                            + ", " + person.getWhoIssuedPassport() + ", " + person.getWhenIssuedPassport() + ",");
                if (text.contains("{$result}")) {
                    text = text.replace("{$result}", costr + " рублей " + costk + " коп.");
                    r.setBold(true);
                }

                r.setText(text, 0);
            }
        //По таблицам
        for (XWPFTable tbl : doc.getTables())
            for (XWPFTableRow row : tbl.getRows())
                for (XWPFTableCell cell : row.getTableCells())
                    for (XWPFParagraph p : cell.getParagraphs())
                        for (XWPFRun r : p.getRuns()) {
                            String text = r.getText(0);
                            if (text == null) continue;
                            if (text.contains("{$post_employee2}"))
                                text = text.replace("{$post_employee2}", flag ? "Заместитель заведующего" : "Ведущий научный сотрудник");
                            if (text.contains("{$fio_employee2}"))
                                text = text.replace("{$fio_employee2}", flag ? "Н.А.Бадыгина" : "Т.В.Руденкова");
                            if (text.contains("{$fio_client2}"))
                                text = text.replace("{$fio_client2}", createLine(person.getSurname() + " "
                                        + person.getName() + " " + person.getPatronymic()));
                            if (text.contains("{$address_client}"))
                                text = text.replace("{$address_client}", createLine(person.getAddress()));
                            if (text.contains("{$phone_client}"))
                                text = text.replace("{$phone_client}", createLine(person.getPhone()));
                            r.setText(text,0);
                        }
        FileOutputStream fos = new FileOutputStream("files/result.docx");
        doc.write(fos);
        fis.close();
        fos.close();
    }

    private static StringBuilder createLine(String text) {
        StringBuilder rep = new StringBuilder(text);
        if (rep.length() == 0) rep.append("\u2000 \u2000");//
        else {
            int count = 0;
            for (int i = 0; i < rep.length(); i++)
                if(rep.substring(i, i + 1).equals(".")||rep.substring(i, i + 1).equals(" ")) count++;
            int n = 40 - rep.length() + (count/2) - 1;
            for (int i = 0; i < n; i++) rep.append("\u2000");//
            rep.append(" \u2000");//
        }
        return rep;
    }
}
