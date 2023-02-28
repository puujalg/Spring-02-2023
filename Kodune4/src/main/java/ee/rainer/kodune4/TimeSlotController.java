package ee.rainer.kodune4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
public class TimeSlotController {

    @Autowired
    TimeSlotRepository  timeSlotRepository;
    @Autowired
    LocationRepository locationRepository;

    /**
     {
     "id":3,
     "date":"2023-04-21 10:00:00",
     "taken":true,
     "location":{"id": 1}
     }
     */

    @PostMapping("addtimeslot")
    public String addTimeSlot(@RequestBody TimeSlot timeSlot) throws ParseException {

        if (timeSlotRepository.findById(timeSlot.getId()).isEmpty()) {

            SimpleDateFormat sdFormat1 = new SimpleDateFormat("HH:mm");
            SimpleDateFormat sdFormat2 = new SimpleDateFormat("HH:mm");
            sdFormat2.setTimeZone(TimeZone.getTimeZone("UTC+2"));

            Long reqTimeLong = sdFormat1.parse(sdFormat1.format(timeSlot.getDate())).getTime();
            Long openTime = sdFormat2.parse(locationRepository.findById(timeSlot.getLocation().getId()).get().getEarliestTime()).getTime();
            Long closeTime= sdFormat2.parse(locationRepository.findById(timeSlot.getLocation().getId()).get().getLatestTime()).getTime();

            Date reqTime = new Date(timeSlot.getDate().getTime() - 3600 * 2000);
            timeSlot.setDate(reqTime);

            // location check võiks ka olla

            if (reqTimeLong > closeTime) {
                return "Time passed closing time, please choose earlier time between "
                        + locationRepository.findById(timeSlot.getLocation().getId()).get().getEarliestTime() + " - "
                        + locationRepository.findById(timeSlot.getLocation().getId()).get().getLatestTime() + ".";
            } else if (reqTimeLong < openTime) {
                return "Time before opening time, please choose earlier time between "
                        + locationRepository.findById(timeSlot.getLocation().getId()).get().getEarliestTime() + " - "
                        + locationRepository.findById(timeSlot.getLocation().getId()).get().getLatestTime() + ".";
            } else {
                timeSlotRepository.save(timeSlot);
                return "New timeslot added with an ID of " + timeSlot.getId();
            }

        } else {
            return "TimeSlot with an ID of " + timeSlot.getId() + " already exists.";
        }

    }

    @DeleteMapping("deletetimeslot/{id}")
    public String deleteTimeSlot(@PathVariable Long id) {

        if (timeSlotRepository.findById(id).isEmpty()) {
            return "This ID doesn't exist.";
        } else {
            timeSlotRepository.deleteById(id);
            return "Timeslot with an ID of " + id + " deleted.";
        }

    }

    @PutMapping("edittimeslot")
    public String editTimeSlot(@RequestBody TimeSlot timeSlot) throws ParseException {

        if (timeSlotRepository.findById(timeSlot.getId()).isEmpty()) {

            return "This ID doesn't exist.";

        } else {

            SimpleDateFormat sdFormat1 = new SimpleDateFormat("HH:mm");
            SimpleDateFormat sdFormat2 = new SimpleDateFormat("HH:mm");
            sdFormat2.setTimeZone(TimeZone.getTimeZone("UTC+2"));

            Long reqTimeLong = sdFormat1.parse(sdFormat1.format(timeSlot.getDate())).getTime();
            Long openTime = sdFormat2.parse(locationRepository.findById(timeSlot.getLocation().getId()).get().getEarliestTime()).getTime();
            Long closeTime= sdFormat2.parse(locationRepository.findById(timeSlot.getLocation().getId()).get().getLatestTime()).getTime();

            Date reqTime = new Date(timeSlot.getDate().getTime() - 3600 * 2000);
            timeSlot.setDate(reqTime);

            if (reqTimeLong > closeTime) {
                return "Time passed closing time, please choose earlier time between "
                        + locationRepository.findById(timeSlot.getLocation().getId()).get().getEarliestTime() + " - "
                        + locationRepository.findById(timeSlot.getLocation().getId()).get().getLatestTime() + ".";
            } else if (reqTimeLong < openTime) {
                return "Time before opening time, please choose earlier time between "
                        + locationRepository.findById(timeSlot.getLocation().getId()).get().getEarliestTime() + " - "
                        + locationRepository.findById(timeSlot.getLocation().getId()).get().getLatestTime() + ".";
            } else {
                timeSlotRepository.save(timeSlot);
                return "New timeslot added with an ID of " + timeSlot.getId();
            }

        }

    }

    @GetMapping("gettimeslot/({id}")
    public Optional<TimeSlot> getLocation(@PathVariable Long id) {

        if (timeSlotRepository.findById(id).isEmpty()) {
            return null;
        } else {
            return timeSlotRepository.findById(id);
        }

    }

    @GetMapping("listactivetimeslots")
    public List<TimeSlot> listActiveTimeSlots() {
        return timeSlotRepository.findAllByTakenTrue();
    }

}
