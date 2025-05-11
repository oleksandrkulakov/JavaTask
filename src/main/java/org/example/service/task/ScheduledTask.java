package org.example.service.task;

import org.example.model.ScheduleEntry;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.example.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {
  private static final ZoneId LAGOS_ZONE = ZoneId.of("Africa/Lagos");

  private ScheduleService scheduleService;

  @Scheduled(fixedRateString = "${scheduler.rate.ms}")
  public void schedule() {
    ZonedDateTime nowInLagos = ZonedDateTime.now(LAGOS_ZONE);
    LocalTime currentTime = nowInLagos.toLocalTime().withSecond(0).withNano(0);
    int currentDayBit = 1 << nowInLagos.getDayOfWeek().getValue() % 7;

    for (ScheduleEntry entry : scheduleService.loadSchedule()) {
      if (entry.getTime().equals(currentTime) && (entry.getBitmask() & currentDayBit) != 0) {
        System.out.println("Executing action at " + currentTime + " on " + nowInLagos.getDayOfWeek());
      }
    }
  }
}
