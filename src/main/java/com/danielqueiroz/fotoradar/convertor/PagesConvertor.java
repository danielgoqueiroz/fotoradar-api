package com.danielqueiroz.fotoradar.convertor;

import com.danielqueiroz.fotoradar.model.Company;
import com.danielqueiroz.fotoradar.model.Image;
import com.danielqueiroz.fotoradar.model.Page;
import com.danielqueiroz.fotoradar.model.Process;
import org.bson.Document;

import java.util.List;
import java.util.stream.Collectors;

public class PagesConvertor {


    public static List<Page> convertPagesBsonListToPOJO(String processNumber, List pagesJson) {
         List<Page> pages = (List<Page>) pagesJson.stream().map(page -> {
            Document pageJson = (Document) page;
            return Page.builder()
                    .id(pageJson.getObjectId("_id").toString())
                    .url(pageJson.getString("url"))
                    .image(Image.builder().build())
                    .process(Process.builder().processNumber(processNumber).build())
                    .company(Company.builder().build())
                    .build();
        }).collect(Collectors.toList());

         return pages;
    }
}
