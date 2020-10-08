package ch.heigvd.amt.project.infrastructure.persistence.jdbc;

import ch.heigvd.amt.project.application.core.domain.entity.IEntity;
import ch.heigvd.amt.project.application.core.domain.entity.IRepository;
import ch.heigvd.amt.project.application.core.domain.entity.Id;
import ch.heigvd.amt.project.infrastructure.persistence.exceptions.DataCorruptionException;
import ch.heigvd.amt.project.infrastructure.persistence.exceptions.IntegrityConstraintViolationException;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public abstract class JdbcPgSqRepository<ENTITY extends IEntity<ENTITY, ID>, ID extends Id> implements IRepository<ENTITY, ID> {


}
