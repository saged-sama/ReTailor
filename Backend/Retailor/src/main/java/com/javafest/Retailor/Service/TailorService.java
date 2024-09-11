package com.javafest.Retailor.Service;

import com.javafest.Retailor.Dto.ProductDto;
import com.javafest.Retailor.Dto.TailorDto;
import com.javafest.Retailor.Entity.Tailor;
import com.javafest.Retailor.Entity.Users;
import com.javafest.Retailor.Enum.TailorStatus;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TailorService {
    public TailorDto save(Tailor tailor);
    public List<TailorDto> getAllByStatus(TailorStatus tailorStatus);
    public List<TailorDto> getAll();
    public Tailor getByUser(Users users);

    public Tailor getById(Long id);
    public Page<ProductDto> displayTailorProduct(int offset, int pageSize, Long id);
}
