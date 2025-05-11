package org.example.service;

import org.example.model.ScheduleEntry;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

  @Value("${csv.file.path}")
  private String csvFilePath;

  public List<ScheduleEntry> loadSchedule() {
    List<ScheduleEntry> entries = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
      reader.readLine();
      String line;
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split(",");
        LocalTime time = LocalTime.parse(parts[0]);
        int bitmask = Integer.parseInt(parts[1]);
        entries.add(new ScheduleEntry(time, bitmask));
      }
    } catch (IOException e) {
    }
    return entries;
  }
}
