package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Propagator;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface PropagatorRepository extends PagingAndSortingRepository<Propagator, String> {
    Propagator findById(@Param("id") Integer id);
    List<Propagator> findByUsernameContaining(@Param("text") String text);
}
