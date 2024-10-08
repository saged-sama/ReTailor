package com.javafest.Retailor.Service.Imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javafest.Retailor.Dto.TailorDto;
import com.javafest.Retailor.Entity.Tailor;
import com.javafest.Retailor.Entity.Users;
import com.javafest.Retailor.Enum.TailorStatus;
import com.javafest.Retailor.Repository.TailorRepo;
import com.javafest.Retailor.Service.TailorService;

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
    public Tailor getById(String id) {
        return tailorRepo.findById(id).orElseThrow(() -> new RuntimeException("Tailor not found"));
    }

    @Override
    public List<Tailor> getAllByStatusAndPattern(TailorStatus status, String pattern) {
        return tailorRepo.findByStatusAndByUserPattern(status, pattern);
    }
}
