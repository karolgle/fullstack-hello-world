package com.example.backend.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DataService {
    private final ObjectMapper mapper;
    private final Path dataFile;
    private final List<DataItem> records = new ArrayList<>();
    private final java.util.concurrent.atomic.AtomicInteger lastId = new java.util.concurrent.atomic.AtomicInteger();

    @org.springframework.beans.factory.annotation.Autowired
    public DataService(@Value("${data.file.path:src/main/resources/data.json}") String dataFilePath, ObjectMapper mapper) {
        this.mapper = mapper;
        this.dataFile = Path.of(dataFilePath);
    }

    DataService(Path dataFile, ObjectMapper mapper) {
        this.mapper = mapper;
        this.dataFile = dataFile;
    }

    @PostConstruct
    public void init() throws IOException {
        load();
        int maxId = records.stream().mapToInt(DataItem::getId).max().orElse(0);
        lastId.set(maxId);
    }

    public synchronized List<DataItem> getAll() {
        return new ArrayList<>(records);
    }

    public synchronized DataItem add(DataItem record) throws IOException {
        int newId = lastId.incrementAndGet();
        record.setId(newId);
        records.add(record);
        save();
        return record;
    }

    public synchronized Optional<DataItem> update(int id, DataItem record) throws IOException {
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).getId() == id) {
                record.setId(id);
                records.set(i, record);
                save();
                return Optional.of(record);
            }
        }
        return Optional.empty();
    }

    public synchronized boolean delete(int id) throws IOException {
        boolean removed = records.removeIf(r -> r.getId() == id);
        if (removed) {
            save();
        }
        return removed;
    }

    private void load() throws IOException {
        if (Files.exists(dataFile)) {
            try (InputStream in = Files.newInputStream(dataFile)) {
                records.clear();
                records.addAll(mapper.readValue(in, new TypeReference<List<DataItem>>() {}));
            }
        }
    }

    private void save() throws IOException {
        try (OutputStream out = Files.newOutputStream(dataFile)) {
            mapper.writerWithDefaultPrettyPrinter().writeValue(out, records);
        }
    }
}
