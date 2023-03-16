package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Request;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RequestRepository extends PagingAndSortingRepository<Request, Long> {
}
