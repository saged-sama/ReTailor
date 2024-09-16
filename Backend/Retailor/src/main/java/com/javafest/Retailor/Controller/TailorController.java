package com.javafest.Retailor.Controller;

import com.javafest.Retailor.Config.JwtService;
import com.javafest.Retailor.Dto.TailorDto;
import com.javafest.Retailor.Entity.Portfolio;
import com.javafest.Retailor.Entity.Tailor;
import com.javafest.Retailor.Entity.Users;
import com.javafest.Retailor.Enum.Role;
import com.javafest.Retailor.Enum.TailorStatus;
import com.javafest.Retailor.Repository.TailorRepo;
import com.javafest.Retailor.Repository.UsersRepo;
import com.javafest.Retailor.Service.FileService;
import com.javafest.Retailor.Service.PortfolioService;
import com.javafest.Retailor.Service.TailorService;
import com.javafest.Retailor.Service.UsersService;
import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/collections/tailors")
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
    private PortfolioService portfolioService;
    @Autowired
    private FileService fileService;

    @PostMapping("/")
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

    @GetMapping("/status")
    public ResponseEntity<List<TailorDto>> getTailorsByStatus(){
        return ResponseEntity.ok(tailorService.getAllByStatus(TailorStatus.PENDING));
    }

    @GetMapping("/all")
    public ResponseEntity<List<TailorDto>> getAllTailors(){
        return ResponseEntity.ok(tailorService.getAll());
    }

    @PutMapping("/adminResponse")
    public ResponseEntity<TailorDto> updateTailorAdmin(@ModelAttribute TailorDto tailorDto) throws Exception{
        try {
            Tailor tailor =tailorService.getById(tailorDto.getId());
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

    @PutMapping("/update")
    public ResponseEntity<TailorDto> updateTailor(@ModelAttribute TailorDto tailorDto) throws Exception{
        try {
            Tailor tailor =tailorService.getById(tailorDto.getId());
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

    @PostMapping("/addPortfolio")
    @Transactional
    public ResponseEntity<Portfolio> savePortfolio(@ModelAttribute Portfolio portfolio,
                                                   @RequestParam("avatars") MultipartFile[] files,
                                                   @RequestHeader("Authorization") String authHeader)throws Exception{
        String email;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7); // Remove "Bearer " prefix
            email= jwtService.extractUsername(jwt);
        } else {
            throw new Exception("Invalid Authorization header.");
        }
        Users users= usersService.getByEmail(email);
        Tailor tailor= tailorService.getByUser(users);
        List<String>images=fileService.saveFiles(files);
        portfolio.setTailor(tailor);
        portfolio.setImages(images);
        tailor.setPortfolio(portfolio);
        //tailorService.save(tailor);
        return ResponseEntity.ok(portfolioService.savePortfolio(portfolio));
    }

    @PutMapping("/updatePortfolio")
    public ResponseEntity<Portfolio> updatePortfolio(@ModelAttribute Portfolio portfolio,
                                                     @RequestParam("avatars") MultipartFile[] files)throws Exception{
        return ResponseEntity.ok(portfolioService.updatePortfolio(portfolio,files));
    }

    @DeleteMapping("/deletePortfolio/{portfolioId}")
    public ResponseEntity<String> deletePortfolio(@PathVariable String portfolioId) throws IOException {
        return ResponseEntity.ok(portfolioService.deletePortfolio(portfolioId));
    }

    @GetMapping("/getPortfolio/{tailorId}")
    public ResponseEntity<Portfolio> getPortfolioByTailorsId(@PathVariable String tailorId){
        return ResponseEntity.ok(portfolioService.getPortfolioByTailorsId(tailorId));
    }
}
