package com.example.backend.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final ObjectMapper mapper = new ObjectMapper();
    private List<DataRecord> records = new ArrayList<>();
    private Path dataFile;
    private final java.util.concurrent.atomic.AtomicInteger lastId = new java.util.concurrent.atomic.AtomicInteger();

    @PostConstruct
    public void init() throws IOException {
        dataFile = Path.of("src/main/resources/data.json");
        load();
        int maxId = records.stream().mapToInt(DataRecord::getId).max().orElse(0);
        lastId.set(maxId);
    }

    public synchronized List<DataRecord> getAll() {
        return new ArrayList<>(records);
    }

    public synchronized DataRecord add(DataRecord record) throws IOException {
        int newId = lastId.incrementAndGet();
        record.setId(newId);
        records.add(record);
        save();
        return record;
    }

    public synchronized Optional<DataRecord> update(int id, DataRecord record) throws IOException {
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
                records = mapper.readValue(in, new TypeReference<List<DataRecord>>() {});
            }
        }
    }

    private void save() throws IOException {
        try (OutputStream out = Files.newOutputStream(dataFile)) {
            mapper.writerWithDefaultPrettyPrinter().writeValue(out, records);
        }
    }
}
