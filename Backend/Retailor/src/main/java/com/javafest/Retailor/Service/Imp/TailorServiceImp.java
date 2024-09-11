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
    @Override
    public Page<ProductDto> displayTailorProduct(int offset, int pageSize, Long id) {
       Tailor tailor = getById(id);
       List<ProductDto> productDtoList = tailor.getProducts().stream()
                .filter(Product::getAvailability)  // Only include products where availability is true
                .map(p -> {
                    ProductDto productDto = new ProductDto();
                    productDto.setId(p.getId());
                    productDto.setName(p.getName());
                    productDto.setAvailability(p.getAvailability());
                    productDto.setDescription(p.getDescription());
                    productDto.setCategory(p.getCategory());

                    // Get the first image if available
                    if (!p.getImages().isEmpty()) {
                        productDto.setImage(p.getImages().get(0));
                    }

                    productDto.setBasePrice(p.getBasePrice());
                    productDto.setCreatedAt(p.getCreatedAt());
                    productDto.setIsCustomizable(p.getIsCustomizable());
                    productDto.setUpdatedAt(p.getUpdatedAt());

                    // Get the first tailor if available
                    if (!p.getTailors().isEmpty()) {
                        productDto.setTailors(p.getTailors().iterator().next());
                    }

                    productDto.setSoldAt(p.getSoldAt());
                    return productDto;
                })
                .collect(Collectors.toList());

        // Return the DTO list as a paginated result (PageImpl)
        Pageable pageable = PageRequest.of(offset, pageSize);
        int start = Math.min((int) pageable.getOffset(), productDtoList.size());
        int end = Math.min((start + pageable.getPageSize()), productDtoList.size());

        return new PageImpl<>(productDtoList.subList(start, end), pageable, productDtoList.size());
    }
}
