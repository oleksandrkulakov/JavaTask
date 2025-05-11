package org.example.model;

import java.time.LocalTime;

public class ScheduleEntry {
  private LocalTime time;
  private int bitmask;

  public ScheduleEntry(LocalTime time, int bitmask) {
    this.time = time;
    this.bitmask = bitmask;
  }

  public LocalTime getTime() {
    return time;
  }

  public int getBitmask() {
    return bitmask;
  }
}
