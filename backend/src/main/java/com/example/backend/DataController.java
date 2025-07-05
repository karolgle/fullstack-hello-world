package com.example.backend;

import com.example.backend.data.DataRecord;
import com.example.backend.data.DataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/data")
@CrossOrigin(origins = "http://localhost:5173")
public class DataController {
    private final DataService service;

    public DataController(DataService service) {
        this.service = service;
    }

    @GetMapping
    public List<DataRecord> all() {
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity<DataRecord> add(@RequestBody DataRecord record) throws IOException {
        return ResponseEntity.ok(service.add(record));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataRecord> update(@PathVariable int id, @RequestBody DataRecord record) throws IOException {
        return service.update(id, record)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) throws IOException {
        return service.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
