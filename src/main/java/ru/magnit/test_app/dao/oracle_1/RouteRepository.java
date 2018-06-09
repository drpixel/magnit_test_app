package ru.magnit.test_app.dao.oracle_1;

import java.math.BigInteger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.magnit.test_app.model.Route;

/**
 * Класс для работы с сущностью Route (БД - Oracle 1)
 *
 * @author Dima Pixel
 * @version 1.0
 */
@Repository
@Service("oracle1RR")
public interface RouteRepository extends CrudRepository<Route, BigInteger>  {
    // not need to implement anything
}
