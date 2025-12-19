package com.brenobaise.dscommerce.controllers;

import com.brenobaise.dscommerce.dtos.CategoryDTO;
import com.brenobaise.dscommerce.dtos.ProductDTO;
import com.brenobaise.dscommerce.dtos.ProductMinDTO;
import com.brenobaise.dscommerce.services.CategoryService;
import com.brenobaise.dscommerce.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll(){
        List<CategoryDTO> dtoList = categoryService.findAll();
        return ResponseEntity.ok(dtoList);
    }


}
