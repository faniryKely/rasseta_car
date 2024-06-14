package mg.raseta.car_show.controller;

import lombok.AllArgsConstructor;
import mg.raseta.car_show.model.Brand;
import mg.raseta.car_show.service.implementations.BrandService;
import mg.raseta.car_show.specification.GenericModelSpecification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/brand.brands")
@AllArgsConstructor
public class BrandsController {

    private final BrandService brandService;
    private final GenericModelSpecification<Brand> genericModelSpecification;
    private final BrandController brandController;

    @GetMapping
    public ResponseEntity<List<Brand>> getAllBrands(
            @RequestParam(required = false) Integer brandId,
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int limit
    ) {
        return brandController.searchBrand(brandId, name, page, limit);
    }

}
