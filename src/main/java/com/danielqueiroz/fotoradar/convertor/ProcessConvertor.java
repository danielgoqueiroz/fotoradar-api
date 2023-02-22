package com.danielqueiroz.fotoradar.convertor;

import com.danielqueiroz.fotoradar.model.Process;
import org.bson.Document;

public class ProcessConvertor {

    public static Process convertProcessBsonToPOJO(Document processJson) {

        String processNumber = processJson.getString("processNumber");
        Process process = Process.builder()
                .id(processJson.getObjectId("_id").toString())
                .createdAt(processJson.getDate("createdAt"))
                .description(processJson.getString("description"))
                .processNumber(processNumber)
                .pages(PagesConvertor.convertPagesBsonListToPOJO(processNumber, processJson.getList("pages", Document.class)))
                .build();

        return process;
    }
}
