package rbpo.task2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rbpo.task2.model.ParkingSession;
import rbpo.task2.service.ParkingSessionService;

import java.util.List;

@RestController //спринг находит этот класс при запуске и запоминает адреса
@RequestMapping("/api/parking-sessions")
public class ParkingSessionController {
    private final ParkingSessionService service;

    public ParkingSessionController(ParkingSessionService service) {
        this.service = service;
    }

    @GetMapping
    public List<ParkingSession> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ParkingSession getById(@PathVariable Long id) {
        return service.getById(id);
    }

    //бизнес-операция: въезд
    @PostMapping("/entry")
    @ResponseStatus(HttpStatus.CREATED) //201
    public ParkingSession enter(@RequestParam Long vehicleId, @RequestParam Long zoneId) {
        return service.enter(vehicleId, zoneId);
    }

    //бизнес-операция: выезд, тело запроса не нужно
    @PostMapping("/{id}/exit")
    public ParkingSession exit(@PathVariable Long id) {
        return service.exit(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //204
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
