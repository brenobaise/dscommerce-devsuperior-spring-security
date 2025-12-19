    package com.brenobaise.dscommerce.services;

    import com.brenobaise.dscommerce.dtos.CategoryDTO;
    import com.brenobaise.dscommerce.dtos.ProductDTO;
    import com.brenobaise.dscommerce.dtos.ProductMinDTO;
    import com.brenobaise.dscommerce.entities.Category;
    import com.brenobaise.dscommerce.entities.Product;
    import com.brenobaise.dscommerce.repositories.CategoryRepository;
    import com.brenobaise.dscommerce.repositories.ProductRepository;
    import com.brenobaise.dscommerce.services.exceptions.DatabaseException;
    import com.brenobaise.dscommerce.services.exceptions.ResourceNotFoundException;
    import org.modelmapper.ModelMapper;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.dao.DataIntegrityViolationException;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Propagation;
    import org.springframework.transaction.annotation.Transactional;

    import java.util.HashSet;
    import java.util.List;
    import java.util.Set;

    @Service
    public class CategoryService {

        @Autowired
        private CategoryRepository categoryRepository;

        @Transactional(readOnly = true)
        public List<CategoryDTO> findAll(){
            List<Category> result = categoryRepository.findAll();
            return result.stream().map(CategoryDTO::new).toList();
        }

    }
