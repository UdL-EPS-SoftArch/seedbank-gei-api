package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Request;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

public interface RequestRepository extends PagingAndSortingRepository<Request, Long> {
    List<Request> findByAmount(@Param("Amount") Integer amount);
    List<Request> findByWeight(@Param("Weight") DecimalFormat weight);
    List<Request> findByLocation(@Param("Location") String location);
    List<Request> findByDate(@Param("Date") Date date);
}
