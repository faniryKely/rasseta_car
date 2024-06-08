package mg.raseta.car_show.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
public abstract class GenericService<T, ID> {

    private final JpaRepository<T, ID> repository;

    public Page<T> findAll(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        return repository.findAll(pageable);
    }

    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Transactional
    public T save(T entity) {
        return repository.save(entity);
    }

    @Transactional
    public void deleteById(ID id) {
        Optional<T> toDelete = repository.findById(id);
        if (toDelete.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Entity not found with id: " + id);
        }
    }

    @Transactional
    public T update(ID id, T entity) {
        if (repository.existsById(id)) {
            return repository.save(entity);
        } else {
            throw new IllegalArgumentException("Entity not found with id: " + id);
        }
    }

}