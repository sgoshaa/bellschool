package com.bell.bellschooll.controller;

import com.bell.bellschooll.dto.response.OfficeOutDto;
import com.bell.bellschooll.mapper.OfficeMapper;
import com.bell.bellschooll.service.OfficeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/office/")
public class OfficeController {
    private final OfficeService officeService;

    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @GetMapping("{id}")
    public ResponseEntity<OfficeOutDto> getById(@PathVariable Integer id){
        return officeService.getOfficeById(id);
    }
}
