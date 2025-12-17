    package com.brenobaise.dscommerce.services;

    import com.brenobaise.dscommerce.dtos.ProductDTO;
    import com.brenobaise.dscommerce.dtos.ProductMinDTO;
    import com.brenobaise.dscommerce.entities.Product;
    import com.brenobaise.dscommerce.repositories.ProductRepository;
    import com.brenobaise.dscommerce.services.exceptions.DatabaseException;
    import com.brenobaise.dscommerce.services.exceptions.ResourceNotFoundException;

    import jakarta.persistence.EntityNotFoundException;
    import org.modelmapper.ModelMapper;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.dao.DataIntegrityViolationException;
    import org.springframework.dao.EmptyResultDataAccessException;
    import org.springframework.data.domain.Page;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Propagation;
    import org.springframework.transaction.annotation.Transactional;

    import org.springframework.data.domain.Pageable;
    import org.springframework.web.bind.annotation.RequestParam;


    import java.util.Optional;

    @Service
    public class ProductService {

        @Autowired
        private ProductRepository productRepository;

        @Autowired
        private ModelMapper modelMapper;

        @Transactional(readOnly = true)
        public ProductDTO findById(Long id){
            Product result = productRepository.findById(id)
                    .orElseThrow(()-> new ResourceNotFoundException("Entity not found"));
            return modelMapper.map(result, ProductDTO.class);

        }

        @Transactional(readOnly = true)
        public Page<ProductMinDTO> findAll(String name, Pageable pageable){
            Page<Product> result = productRepository.searchByName(name, pageable);
            return result.map(ProductMinDTO::new);
        }


        @Transactional
        public ProductDTO insert(ProductDTO dto){
                Product product = modelMapper.map(dto, Product.class);

                product = productRepository.save(product);
                return modelMapper.map(product, ProductDTO.class);


        }

        @Transactional
        public ProductDTO update(Long id, ProductDTO dto) {
            Product entity = productRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Entity not found: id " + id));

            modelMapper.map(dto, entity);

            Product saved = productRepository.save(entity);
            return modelMapper.map(saved, ProductDTO.class);
        }


        @Transactional(propagation = Propagation.SUPPORTS)
        public void delete(Long id) {
            if (!productRepository.existsById(id)) {
                throw new ResourceNotFoundException("Recurso não encontrado");
            }
            try {
                productRepository.deleteById(id);
            }
            catch (DataIntegrityViolationException e) {
                throw new DatabaseException("Referential Integrity restraint, trying to delete used entity.");
            }
        }


    }
