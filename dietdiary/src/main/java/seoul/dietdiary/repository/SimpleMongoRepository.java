package seoul.dietdiary.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.FluentQuery;
import seoul.dietdiary.model.GroceryItem;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class SimpleMongoRepository implements MongoRepository<GroceryItem, String> {
    @Override
    public <S extends GroceryItem> S save(S entity) {
        return null;
    }

    @Override
    public <S extends GroceryItem> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<GroceryItem> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<GroceryItem> findAll() {
        return null;
    }

    @Override
    public Iterable<GroceryItem> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(GroceryItem entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends GroceryItem> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<GroceryItem> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<GroceryItem> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends GroceryItem> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends GroceryItem> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends GroceryItem> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends GroceryItem> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends GroceryItem> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends GroceryItem> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends GroceryItem> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends GroceryItem> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends GroceryItem, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

}
