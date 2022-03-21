package com.bell.bellschooll.controller;

import com.bell.bellschooll.dto.request.OfficeInListDto;
import com.bell.bellschooll.dto.request.OfficeInSaveDto;
import com.bell.bellschooll.dto.response.OfficeListOutDto;
import com.bell.bellschooll.dto.response.OfficeOutDto;
import com.bell.bellschooll.dto.response.SuccessDto;
import com.bell.bellschooll.mapper.OfficeMapper;
import com.bell.bellschooll.service.OfficeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

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

    @PostMapping("save")
    public ResponseEntity<SuccessDto> addOffice(@Valid @RequestBody OfficeInSaveDto office){
        return officeService.addOffice(office);
    }

    @PostMapping("list")
    public ResponseEntity<List<OfficeListOutDto>> listOffice(@Valid @RequestBody OfficeInListDto office){
        return officeService.listOffice(office);
    }
}
