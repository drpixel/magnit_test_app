package ru.magnit.test_app.dao.oracle_1;

import java.math.BigInteger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.magnit.test_app.model.PointGraph;

/**
 * Класс для работы с сущностью PointGraph (БД - Oracle 1)
 *
 * @author Dima Pixel
 * @version 1.0
 */
@Repository
@Service("oracle1PGR")
public interface PointGraphRepository extends CrudRepository<PointGraph, BigInteger> {
    // not need to implement anything
}
