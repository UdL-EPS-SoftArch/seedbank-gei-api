package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Take;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.net.http.HttpResponse;

public interface TakeRepository extends PagingAndSortingRepository<Take, String> {

    //This is not void, when merge uncomment Batch
    /*Batch*/ void findBatchById(@Param("id") Integer id);

    HttpResponse <String> deleteBatchById(@Param("id") Integer id);
}
