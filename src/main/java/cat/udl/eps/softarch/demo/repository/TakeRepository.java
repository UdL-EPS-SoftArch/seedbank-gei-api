package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Take;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface TakeRepository extends PagingAndSortingRepository<Take, String> {

    //This is not void, when merge uncomment Batch
    Take findTakeFulfilledByBatchId(@Param("id") Integer batchId);
    Take findTakeTakenByBatchId(@Param("id") Integer batchId);
    Take findById(@Param("id") Integer takeId);
    Boolean existsById(@Param("id") Integer takeId);
}
