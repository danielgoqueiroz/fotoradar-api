package com.danielqueiroz.fotoradar.service;

import com.danielqueiroz.fotoradar.convertor.ProcessConvertor;
import com.danielqueiroz.fotoradar.model.Payment;
import com.danielqueiroz.fotoradar.model.Process;
import com.danielqueiroz.fotoradar.model.User;
import com.danielqueiroz.fotoradar.repository.ProcessRepo;
import com.danielqueiroz.fotoradar.repository.UserRepo;
import com.google.common.base.Strings;
import com.mongodb.DBRef;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.codecs.DocumentCodec;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.danielqueiroz.fotoradar.convertor.ProcessConvertor.convertProcessBsonToPOJO;

@Transactional
@Slf4j
@Service
@AllArgsConstructor
public class ProcessSevice {

    private final ProcessRepo processRepo;
    private final UserRepo userRepo;

    private final MongoTemplate mongoTemplate;

    private final UserService userService;

    public List<Process> getProcesses() {

        User user = userService.getCurrentUser();

        MongoCollection<Document> collection = mongoTemplate.getCollection(mongoTemplate.getCollectionName(Process.class));

        FindIterable<Document> documents = collection
                .find(
                        Criteria
                                .where("pages.image.user")
                                .is(new DBRef("user", new ObjectId(user.getId())))
                                .getCriteriaObject()
                );

        CodecRegistry codecRegistry = MongoClientSettings.getDefaultCodecRegistry();
        DocumentCodec codec = new DocumentCodec(codecRegistry);

        List<Process> processess = new ArrayList<>();
        MongoCursor<Document> iterator = documents.iterator();
        while (iterator.hasNext()) {
            Document next = iterator.next();
            Process process = convertProcessBsonToPOJO(next);
            processess.add(process);
        }

        return processess;
    }

    public Process addPayment(String processId, String value, String date) throws ParseException {

        User user = userService.getCurrentUser();

        Process process = processRepo.findById(processId).get();
        process.getPayments().add(Payment.builder()
                .value(new BigDecimal(value))
                .date(getDate(date))

                .build());

        return processRepo.save(process);
    }

    private Date getDate(String date) throws ParseException {
        if (Strings.isNullOrEmpty(date)) {
            return new Date();
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            return formatter.parse(date);
        }
    }

    public Process updateProcessNumber(String id, String processNumber) {

        User user = userService.getCurrentUser();

        Optional<Process> processOpt = processRepo.findOne(Example.of(Process.builder()
                .user(user)
                .id(id)
                .build()));
        if (processOpt.isPresent()) {
            Process process = processOpt.get();
            process.setProcessNumber(processNumber);
            processRepo.save(process);
            return process;
        }
        return null;

    }

    public Process getProcess(String id) {
        User user = userService.getCurrentUser();

        Process build = Process.builder()
                .id(id)
                .pages(null)
                .attorney(null)
                .payments(null)
                .user(user)
                .build();

        Example<Process> example = Example.of(
                build
        );
        Process process = processRepo.findOne(example).get();
        return process;
    }

}
