package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Take;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TakeRepository extends PagingAndSortingRepository<Take, Long> {

    //This is not void, when merge uncomment Batch
    Take findTakeFulfilledById(@Param("id") Integer batchId);
    Take findTakeTakenById(@Param("id") Integer batchId);

}
