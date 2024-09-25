package com.javafest.Retailor.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javafest.Retailor.Config.JwtService;
import com.javafest.Retailor.Entity.Customer;
import com.javafest.Retailor.Entity.Measurements;
import com.javafest.Retailor.Entity.Users;
import com.javafest.Retailor.Service.CustomerService;
import com.javafest.Retailor.Service.MeasurementsService;
import com.javafest.Retailor.Service.UsersService;
import com.javafest.Retailor.utils.EntityUpdate;



@RestController
@RequestMapping("/api/collections/measurements")
public class MeasurementsController {
    @Autowired
    private MeasurementsService measurementsService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private JwtService jwtService;

    EntityUpdate entityUpdate = new EntityUpdate();

    @GetMapping("/records")
    public ResponseEntity<Iterable<Measurements>> getAllMeasurements(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int perPage,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(defaultValue = "") String filter
    ) {
        System.out.println("page: " + page + " perPage: " + perPage + " sortBy: " + sortBy + " sortDir: " + sortDir + " filter: " + filter);
        return ResponseEntity.ok(measurementsService.getAll(page, perPage, sortBy, sortDir, filter));
    }

    @GetMapping("/records/{id}")
    public ResponseEntity<Measurements> getMeasurementsById(@PathVariable String id) {
        return ResponseEntity.ok(measurementsService.getById(id));
    }

    @PostMapping("/records")
    public ResponseEntity<Measurements> saveMeasurements(
            @RequestBody Measurements measurements,
            @RequestHeader("Authorization") String authHeader) throws Exception {
        String email = "";

        System.out.println("authHeader: " + authHeader);
        System.out.println("measurements: " + measurements.getGender());

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7); // Remove "Bearer " prefix
            email = jwtService.extractUsername(jwt);
        } else {
            throw new Exception("Invalid Authorization header.");
        }

        Users user = usersService.getByEmail(email);
        Customer customer = customerService.getByUsers(user);
        measurements.setCustomerId(customer);
        return ResponseEntity.ok(measurementsService.save(measurements));
    }
    
    @SuppressWarnings("static-access")
    @PatchMapping("/records/{id}")
    public ResponseEntity<Measurements> updateMeasurements(@RequestBody Measurements measurements, @PathVariable String id) {
        Measurements measurement = measurementsService.getById(id);
        entityUpdate.merge(measurement, measurements);
        return ResponseEntity.ok(measurementsService.save(measurement));
    }

    @DeleteMapping("/records/{id}")
    public ResponseEntity<String> deleteMeasurements(@PathVariable String id) {
        measurementsService.delete(id);
        return ResponseEntity.ok("Deleted");
    }
}
