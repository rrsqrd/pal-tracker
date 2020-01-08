package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    Map<Long, TimeEntry> mapOfTimeEntries = new HashMap<>();
    private Long nextId=0L;

    @Override
    public TimeEntry create(TimeEntry someTime) {
        nextId++;
        TimeEntry newTimeEntry = new TimeEntry(nextId, someTime.getProjectId(), someTime.getUserId(), someTime.getDate(), someTime.getHours());
        mapOfTimeEntries.put(newTimeEntry.getId(), newTimeEntry);
        return newTimeEntry;
    }

    @Override
    public TimeEntry find(long timeEntryId) {
        return mapOfTimeEntries.get(timeEntryId);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(mapOfTimeEntries.values());
    }

    @Override
    public TimeEntry update(long id, TimeEntry someTime) {
        if(mapOfTimeEntries.get(id) ==null )
            return null;

        TimeEntry newTimeEntry = new TimeEntry(id, someTime.getProjectId(), someTime.getUserId(), someTime.getDate(), someTime.getHours());
        mapOfTimeEntries.put(id, newTimeEntry);
        return newTimeEntry;
    }

    @Override
    public void delete(long id) {
        if(mapOfTimeEntries.get(id) != null){
            mapOfTimeEntries.remove(id);
        }
    }
}
