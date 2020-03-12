package com.cove.safetyplan.controller;

import com.cove.safetyplan.service.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/resources", produces = MediaType.APPLICATION_JSON_VALUE)
public class ResourcesController {
    @Autowired
    private ResourcesService resourcesService;


}
