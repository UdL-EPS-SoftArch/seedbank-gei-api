package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Batch;
import cat.udl.eps.softarch.demo.domain.Request;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

@RepositoryRestResource
public interface RequestRepository extends PagingAndSortingRepository<Request, Long> {

    Request findRequestByAmountAndWeight(Integer amount, BigDecimal weight);
}
