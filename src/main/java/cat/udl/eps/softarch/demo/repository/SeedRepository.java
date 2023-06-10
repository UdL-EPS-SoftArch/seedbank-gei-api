package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Seed;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface SeedRepository extends PagingAndSortingRepository<Seed, Long> {
    Optional<Seed> findByScientificName(@Param("scientificName") String scientificName);
    List<Seed> findByScientificNameAsList(@Param("text") String text);
    
}
