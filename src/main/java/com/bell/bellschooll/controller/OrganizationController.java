package com.bell.bellschooll.controller;

import com.bell.bellschooll.dto.request.OrganizationSaveInDto;
import com.bell.bellschooll.dto.request.OrganizationUpdateInDto;
import com.bell.bellschooll.service.OrganizationService;
import com.bell.bellschooll.dto.request.OrganisationDtoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("api/organization/")
public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping("list")
    public ResponseEntity getListOrganization(@Valid @RequestBody OrganisationDtoRequest organisationDTO){
        return organizationService.getOrganizationByName(organisationDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity getOrganizationById(@PathVariable Integer id){
        return organizationService.getOrganizationById(id);
    }

    @PostMapping("save")
    public ResponseEntity addOrganization( @Valid @RequestBody OrganizationSaveInDto organizationSaveInDto){
        return organizationService.addOrganization(organizationSaveInDto);
    }
    @PostMapping("update")
    public ResponseEntity updateOrganization(@Valid @RequestBody OrganizationUpdateInDto organizationUpdateInDto){

        return organizationService.updateOrganization(organizationUpdateInDto);
    }
}
