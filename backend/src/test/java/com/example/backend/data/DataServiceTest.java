package com.example.backend.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DataServiceTest {

    private Path tempFile;
    private DataService service;

    @BeforeEach
    void setUp() throws IOException {
        tempFile = Files.createTempFile("data", ".json");
        Files.writeString(tempFile, "[]");
        service = new DataService(tempFile, new ObjectMapper());
        service.init();
    }

    @Test
    void addAssignsIncrementingIds() throws IOException {
        DataRecord record = new DataRecord(0, "test", 123);
        DataRecord saved = service.add(record);
        assertEquals(1, saved.getId());
        assertEquals(saved, record);
    }

    @Test
    void getAllReturnsCopy() throws IOException {
        service.add(new DataRecord(0, "a", 1));
        List<DataRecord> list = service.getAll();
        list.clear();
        assertEquals(1, service.getAll().size());
    }

    @Test
    void updateReturnsUpdatedRecordWhenExists() throws IOException {
        DataRecord record = service.add(new DataRecord(0, "a", 1));
        DataRecord updated = new DataRecord(0, "b", 2);
        Optional<DataRecord> result = service.update(record.getId(), updated);
        assertTrue(result.isPresent());
        assertEquals("b", result.get().getLabel());
        assertEquals(record.getId(), result.get().getId());
    }

    @Test
    void updateReturnsEmptyWhenRecordMissing() throws IOException {
        Optional<DataRecord> result = service.update(42, new DataRecord());
        assertTrue(result.isEmpty());
    }

    @Test
    void deleteRemovesRecord() throws IOException {
        DataRecord record = service.add(new DataRecord(0, "a", 1));
        assertTrue(service.delete(record.getId()));
        assertFalse(service.delete(record.getId()));
    }
}
