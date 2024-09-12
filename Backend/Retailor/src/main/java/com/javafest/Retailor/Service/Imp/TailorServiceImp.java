package com.javafest.Retailor.Service.Imp;

import com.javafest.Retailor.Dto.ProductDto;
import com.javafest.Retailor.Dto.TailorDto;
import com.javafest.Retailor.Entity.Product;
import com.javafest.Retailor.Entity.Tailor;
import com.javafest.Retailor.Entity.Users;
import com.javafest.Retailor.Enum.TailorStatus;
import com.javafest.Retailor.Repository.TailorRepo;
import com.javafest.Retailor.Service.TailorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TailorServiceImp implements TailorService {

    @Autowired
    private TailorRepo tailorRepo;
    @Override
    public TailorDto save(Tailor tailor1) {
        Tailor tailor= tailorRepo.save(tailor1);
        TailorDto tailorDto = new TailorDto();
        tailorDto.setId(tailor.getId());
        tailorDto.setName(tailor.getName());
        tailorDto.setBio(tailor.getBio());
        tailorDto.setSkills(tailor.getSkills());
        tailorDto.setLocation(tailor.getLocation());
        tailorDto.setNationalId(tailor.getNationalId());
        tailorDto.setTailorStatus(tailor.getTailorStatus());
        tailorDto.setTradeLicense(tailor.getTradeLicense());
        tailorDto.setUsers(tailor.getUsers());
        tailorDto.setApprovalDate(tailor.getApprovalDate());
        tailorDto.setSubmissionDate(tailor.getSubmissionDate());

        return tailorDto;
    }

    @Override
    public List<TailorDto> getAllByStatus(TailorStatus tailorStatus) {
        List <Tailor> tailors = tailorRepo.findAllByTailorStatus(tailorStatus);
        List<TailorDto> tailorDtoList=new ArrayList<>();
        for(var tailor: tailors){
            TailorDto tailorDto = new TailorDto();
            tailorDto.setId(tailor.getId());
            tailorDto.setName(tailor.getName());
            tailorDto.setBio(tailor.getBio());
            tailorDto.setSkills(tailor.getSkills());
            tailorDto.setLocation(tailor.getLocation());
            tailorDto.setNationalId(tailor.getNationalId());
            tailorDto.setTailorStatus(tailor.getTailorStatus());
            tailorDto.setTradeLicense(tailor.getTradeLicense());
            tailorDto.setUsers(tailor.getUsers());
            tailorDto.setApprovalDate(tailor.getApprovalDate());
            tailorDto.setSubmissionDate(tailor.getSubmissionDate());
            tailorDtoList.add(tailorDto);
        }

        return tailorDtoList;
    }

    @Override
    public List<TailorDto> getAll() {
        List <Tailor> tailors = tailorRepo.findAll();
        List<TailorDto> tailorDtoList=new ArrayList<>();
        for(var tailor: tailors){
            TailorDto tailorDto = new TailorDto();
            tailorDto.setId(tailor.getId());
            tailorDto.setName(tailor.getName());
            tailorDto.setBio(tailor.getBio());
            tailorDto.setSkills(tailor.getSkills());
            tailorDto.setLocation(tailor.getLocation());
            tailorDto.setNationalId(tailor.getNationalId());
            tailorDto.setTailorStatus(tailor.getTailorStatus());
            tailorDto.setTradeLicense(tailor.getTradeLicense());
            tailorDto.setUsers(tailor.getUsers());
            tailorDto.setApprovalDate(tailor.getApprovalDate());
            tailorDto.setSubmissionDate(tailor.getSubmissionDate());
            tailorDtoList.add(tailorDto);
        }

        return tailorDtoList;
    }

    @Override
    public Tailor getByUser(Users users) {
        return tailorRepo.findByUsers(users);
    }

    @Override
    public Tailor getById(Long id) {
        return tailorRepo.findById(id).orElseThrow(() -> new RuntimeException("Tailor not found"));
    }
}
