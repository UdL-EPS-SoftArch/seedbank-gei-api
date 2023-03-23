package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Admin;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AdminRepository extends PagingAndSortingRepository<Admin, String> {
}
