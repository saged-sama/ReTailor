package com.javafest.Retailor.Controller;

import com.javafest.Retailor.Config.JwtService;
import com.javafest.Retailor.Dto.TailorDto;
import com.javafest.Retailor.Entity.Tailor;
import com.javafest.Retailor.Entity.Users;
import com.javafest.Retailor.Enum.Role;
import com.javafest.Retailor.Enum.TailorStatus;
import com.javafest.Retailor.Repository.TailorRepo;
import com.javafest.Retailor.Repository.UsersRepo;
import com.javafest.Retailor.Service.TailorService;
import com.javafest.Retailor.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/collections")
public class TailorController {
    @Autowired
    private TailorService tailorService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private UsersService usersService;
    @Autowired
    private TailorRepo tailorRepo;

    @PostMapping("/tailor/save")
    public ResponseEntity<?> saveTailor(@ModelAttribute TailorDto tailorDto, @RequestHeader("Authorization") String authHeader) throws Exception {
        String email;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7); // Remove "Bearer " prefix
            email= jwtService.extractUsername(jwt);
        } else {
            throw new Exception("Invalid Authorization header.");
        }

        Optional<Users> users = usersRepo.findByEmail(email);
        try {
            Tailor tailor =new Tailor();
            tailor.setName(tailorDto.getName());
            tailor.setBio(tailorDto.getBio());
            tailor.setTailorStatus(TailorStatus.PENDING);
            if(users.isPresent())
                tailor.setUsers(users.get());
            else return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            tailor.setLocation(tailorDto.getLocation());
            tailor.setNationalId(tailorDto.getNationalId());
            tailor.setTradeLicense(tailorDto.getTradeLicense());
            tailor.setSkills(tailorDto.getSkills());
            TailorDto savedTailor = tailorService.save(tailor);

            return new ResponseEntity<>(savedTailor, HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle any exceptions and return a 500 (INTERNAL SERVER ERROR) status
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tailor/status")
    public ResponseEntity<List<TailorDto>> getTailorsByStatus(){
        return ResponseEntity.ok(tailorService.getAllByStatus(TailorStatus.PENDING));
    }

    @GetMapping("/tailor/all")
    public ResponseEntity<List<TailorDto>> getAllTailors(){
        return ResponseEntity.ok(tailorService.getAll());
    }

    @PutMapping("/tailor/adminResponse")
    public ResponseEntity<TailorDto> updateTailorAdmin(@ModelAttribute TailorDto tailorDto) throws Exception{
        try {
            Tailor tailor =tailorRepo.findById(tailorDto.getId())
                    .orElseThrow(() -> new RuntimeException("Tailor not found"));
            //System.out.println(tailor.getTailorStatus());
            tailor.setName(tailorDto.getName());
            tailor.setBio(tailorDto.getBio());
            tailor.setTailorStatus(tailorDto.getTailorStatus());
            //System.out.println(tailor.getTailorStatus());
            if(tailorDto.getTailorStatus()==TailorStatus.APPROVED){
                //System.out.println(tailor.getUsers().getId());
                Users users=usersRepo.findById(tailor.getUsers().getId())
                        .orElseThrow(() -> new RuntimeException("User not found"));
                //System.out.println(users);
                Set<Role>roles=new HashSet<>();
                roles.add(Role.ROLE_TAILOR);
                roles.add(Role.ROLE_CUSTOMER);
                users.setRoles(roles);
                System.out.println(users.getRoles());
                tailor.setUsers(usersService.save(users));
                tailor.setApprovalDate(LocalDateTime.now());
                System.out.println(tailor.getUsers());
            }
            tailor.setLocation(tailorDto.getLocation());
            tailor.setNationalId(tailorDto.getNationalId());
            tailor.setTradeLicense(tailorDto.getTradeLicense());
            tailor.setSkills(tailorDto.getSkills());
            TailorDto savedTailor = tailorService.save(tailor);

            return new ResponseEntity<>(savedTailor, HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle any exceptions and return a 500 (INTERNAL SERVER ERROR) status
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/tailor/update")
    public ResponseEntity<TailorDto> updateTailor(@ModelAttribute TailorDto tailorDto) throws Exception{
        try {
            Tailor tailor =tailorRepo.findById(tailorDto.getId())
                    .orElseThrow(() -> new RuntimeException("Tailor not found"));
            //System.out.println(tailor.getTailorStatus());
            tailor.setName(tailorDto.getName());
            tailor.setBio(tailorDto.getBio());
            //System.out.println(tailor.getTailorStatus());
            tailor.setLocation(tailorDto.getLocation());
            tailor.setNationalId(tailorDto.getNationalId());
            tailor.setTradeLicense(tailorDto.getTradeLicense());
            tailor.setSkills(tailorDto.getSkills());
            TailorDto savedTailor = tailorService.save(tailor);

            return new ResponseEntity<>(savedTailor, HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle any exceptions and return a 500 (INTERNAL SERVER ERROR) status
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
