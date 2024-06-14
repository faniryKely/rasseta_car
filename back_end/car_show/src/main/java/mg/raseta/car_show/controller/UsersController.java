package mg.raseta.car_show.controller;

import lombok.AllArgsConstructor;
import mg.raseta.car_show.model.User;
import mg.raseta.car_show.service.implementations.UserService;
import mg.raseta.car_show.specification.GenericModelSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {
    
    private final UserService userService;
    private final GenericModelSpecification<User> genericModelSpecification;

    @GetMapping
    public ResponseEntity<List<User>> searchUser(
            @RequestParam(name = "_start", defaultValue = "0") int start,
            @RequestParam(name = "_end", defaultValue = "25") int end,
            @RequestParam(name = "_order", defaultValue = "ASC") String order,
            @RequestParam(name = "_sort", defaultValue = "id") String sort,
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String password,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int limit
    )
    {
        Specification<User> specification = Specification.where(null);

        if (userId != null) {
            specification = specification.and(genericModelSpecification.hasInteger(userId, "userId"));
        }
        if (name != null) {
            specification = specification.and(genericModelSpecification.hasString(name, "name"));
        }
        if (email != null) {
            specification = specification.and(genericModelSpecification.hasString(email, "email"));
        }
        if (password != null) {
            specification = specification.and(genericModelSpecification.hasString(password, "password"));
        }

        Pageable pageable = PageRequest.of(page, limit);
        Page<User> userPage = userService.searchUser(specification, pageable);

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", Long.toString(userPage.getTotalElements()));

        return ResponseEntity.ok().headers(headers).body(userPage.getContent());
    }
    
}
