package ru.magnit.test_app.dao.oracle_1;

import java.math.BigInteger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.magnit.test_app.model.PointGraph;

@Repository
@Service("oracle1PGR")
public interface PointGraphRepository extends CrudRepository<PointGraph, BigInteger>  {
    
}
