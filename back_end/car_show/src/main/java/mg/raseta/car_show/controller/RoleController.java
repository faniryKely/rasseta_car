package mg.raseta.car_show.controller;

import lombok.AllArgsConstructor;
import mg.raseta.car_show.model.auth.Role;
import mg.raseta.car_show.service.implementations.RoleService;
import mg.raseta.car_show.specification.GenericModelSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@AllArgsConstructor
public class RoleController {

    private final RoleService roleService;
    private final GenericModelSpecification<Role> genericModelSpecification;

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Role createRole = roleService.save(role);
        return ResponseEntity.ok(createRole);
    }

    @GetMapping
    public ResponseEntity<List<Role>> searchRole(
            @RequestParam(name = "id", defaultValue = "6") Long id,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "perPage", defaultValue = "25") int perPage,
            @RequestParam(required = false) Integer roleId,
            @RequestParam(required = false) String roleName
    )
    {
        if (id != null) {
            return ResponseEntity.ok(roleService.findRoleByRoleId(id));
        }

        Specification<Role> specification = Specification.where(null);

        if (roleId != null) {
            specification = specification.and(genericModelSpecification.hasInteger(roleId, "roleId"));
        }
        if (roleName != null) {
            specification = specification.and(genericModelSpecification.hasString(roleName, "roleName"));
        }

        Pageable pageable = PageRequest.of(page, perPage);
        Page<Role> rolePage = roleService.searchRole(specification, pageable);

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", Long.toString(rolePage.getTotalElements()));

        return ResponseEntity.ok().headers(headers).body(rolePage.getContent());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(
            @PathVariable int id,
            @RequestBody Role role
    )
    {
        Role toUpdateRole = roleService.update(id, role);
        return ResponseEntity.ok(toUpdateRole);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable int id) {
        roleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}