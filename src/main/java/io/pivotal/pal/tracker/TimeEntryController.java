package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(path = "/time-entries")
public class TimeEntryController {
    private TimeEntryRepository timeEntryRepository;
    private long id;
    private TimeEntry timeEntry;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry createdTimeEntry = timeEntryRepository.create((timeEntryToCreate));

        //System.out.println("POST: TimeEntryController.create:");

        return new ResponseEntity<>(createdTimeEntry, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        TimeEntry readTimeEntry = timeEntryRepository.find(id);
        if(readTimeEntry != null)
            return new ResponseEntity<>(readTimeEntry, HttpStatus.OK);
        else
            return new ResponseEntity<>(readTimeEntry, HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> timeEntryList = timeEntryRepository.list();
        //System.out.println("GET: TimeEntryController.list contains " + timeEntryList.size() + " records");
        return new ResponseEntity<>(timeEntryList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody TimeEntry timeEntry) {
        this.id = id;
        this.timeEntry = timeEntry;
        TimeEntry updatedTimeEntry = timeEntryRepository.update(id, timeEntry);

        //System.out.println("PUT: TimeEntryController.update:");

        if(updatedTimeEntry == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(updatedTimeEntry, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        timeEntryRepository.delete(id);
        //System.out.println("DELETE: TimeEntryController.delete:");

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
