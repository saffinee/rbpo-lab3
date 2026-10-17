package rbpo.task2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rbpo.task2.model.ParkingZone;
import rbpo.task2.service.ParkingZoneService;

import java.util.List;

@RestController //спринг находит этот класс при запуске и запоминает адреса
@RequestMapping("/api/parking-zones")
public class ParkingZoneController {
    private final ParkingZoneService service;

    public ParkingZoneController(ParkingZoneService service) {
        this.service = service;
    }

    @GetMapping
    public List<ParkingZone> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ParkingZone getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //201
    public ParkingZone create(@RequestBody ParkingZone request) {
        return service.create(request);
    }

    @PatchMapping("/{id}")
    public ParkingZone update(@PathVariable Long id, @RequestBody ParkingZone request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //204
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
