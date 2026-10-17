package rbpo.task2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rbpo.task2.model.ParkingPass;
import rbpo.task2.service.ParkingPassService;

import java.util.List;

@RestController //спринг находит этот класс при запуске и запоминает адреса
@RequestMapping("/api/parking-passes")
public class ParkingPassController {
    private final ParkingPassService service;

    public ParkingPassController(ParkingPassService service) {
        this.service = service;
    }

    @GetMapping
    public List<ParkingPass> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ParkingPass getById(@PathVariable Long id) {
        return service.getById(id);
    }

    //бизнес-операция: оформить абонемент на days дней
    @PostMapping("/purchase")
    @ResponseStatus(HttpStatus.CREATED) //201
    public ParkingPass purchase(@RequestParam Long vehicleId, @RequestParam Long zoneId, @RequestParam int days) {
        return service.purchase(vehicleId, zoneId, days);
    }

    //бизнес-операция: продлить абонемент на days дней
    @PostMapping("/{id}/extend")
    public ParkingPass extend(@PathVariable Long id, @RequestParam int days) {
        return service.extend(id, days);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //204
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
