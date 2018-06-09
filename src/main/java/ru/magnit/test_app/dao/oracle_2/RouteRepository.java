package ru.magnit.test_app.dao.oracle_2;

import java.math.BigInteger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.magnit.test_app.model.Route;

@Repository
@Service("oracle2RR")
public interface RouteRepository extends CrudRepository<Route, BigInteger>  {
    
}
