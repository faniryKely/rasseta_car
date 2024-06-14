package mg.raseta.car_show.service.implementations;

import mg.raseta.car_show.model.auth.Role;
import mg.raseta.car_show.repository.RoleRepository;
import mg.raseta.car_show.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService extends GenericService<Role, Integer> {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        super(roleRepository);
        this.roleRepository = roleRepository;
    }

    public Page<Role> searchRole(Specification<Role> specification, Pageable pageable) {
        return roleRepository.findAll(specification, pageable);
    }

    public List<Role> findRoleByRoleId(Long roleId) {
        return roleRepository.findByRoleId(roleId);
    }

}