package ru.hogwarts.school.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.hogwarts.school.model.Avatar;

import java.util.Collection;
import java.util.Optional;


public interface AvatarRepository extends PagingAndSortingRepository<Avatar, Long> {
    Optional<Avatar> findByStudentId(Long id);
    Collection<Avatar> findAll();
}

