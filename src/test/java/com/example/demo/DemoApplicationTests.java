//package com.example.demo;
//
//import com.example.demo.family.Family;
//import com.example.demo.model.Comic;
//import com.example.demo.model.ComicRepository;
//import com.example.demo.services.ComicService;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.persistence.TypedQuery;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Example;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.repository.query.FluentQuery;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.function.Function;
//
//@SpringBootTest
//class DemoApplicationTests {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Test
//    void test() {
//        String q = "select DISTINCT f from Family f"
//                + " LEFT JOIN FETCH f.persons";
//        TypedQuery<Family> query = entityManager.createQuery(q, Family.class);
//        List<Family> eagerFamilies = query.getResultList();
//    }
//
//    private static final ComicService comicService = new ComicService(new RestTemplate(), new ComicRepository() {
//        @Override
//        public boolean existsByNum(int num) {
//            return false;
//        }
//
//        @Override
//        public Optional<Comic> findByNum(int num) {
//            return Optional.empty();
//        }
//
//        @Override
//        public void flush() {
//
//        }
//
//        @Override
//        public <S extends Comic> S saveAndFlush(S entity) {
//            return null;
//        }
//
//        @Override
//        public <S extends Comic> List<S> saveAllAndFlush(Iterable<S> entities) {
//            return List.of();
//        }
//
//        @Override
//        public void deleteAllInBatch(Iterable<Comic> entities) {
//
//        }
//
//        @Override
//        public void deleteAllByIdInBatch(Iterable<Integer> integers) {
//
//        }
//
//        @Override
//        public void deleteAllInBatch() {
//
//        }
//
//        @Override
//        public Comic getOne(Integer integer) {
//            return null;
//        }
//
//        @Override
//        public Comic getById(Integer integer) {
//            return null;
//        }
//
//        @Override
//        public Comic getReferenceById(Integer integer) {
//            return null;
//        }
//
//        @Override
//        public <S extends Comic> List<S> findAll(Example<S> example) {
//            return List.of();
//        }
//
//        @Override
//        public <S extends Comic> List<S> findAll(Example<S> example, Sort sort) {
//            return List.of();
//        }
//
//        @Override
//        public <S extends Comic> List<S> saveAll(Iterable<S> entities) {
//            return List.of();
//        }
//
//        @Override
//        public List<Comic> findAll() {
//            return List.of();
//        }
//
//        @Override
//        public List<Comic> findAllById(Iterable<Integer> integers) {
//            return List.of();
//        }
//
//        @Override
//        public <S extends Comic> S save(S entity) {
//            return null;
//        }
//
//        @Override
//        public Optional<Comic> findById(Integer integer) {
//            return Optional.empty();
//        }
//
//        @Override
//        public boolean existsById(Integer integer) {
//            return false;
//        }
//
//        @Override
//        public long count() {
//            return 0;
//        }
//
//        @Override
//        public void deleteById(Integer integer) {
//
//        }
//
//        @Override
//        public void delete(Comic entity) {
//
//        }
//
//        @Override
//        public void deleteAllById(Iterable<? extends Integer> integers) {
//
//        }
//
//        @Override
//        public void deleteAll(Iterable<? extends Comic> entities) {
//
//        }
//
//        @Override
//        public void deleteAll() {
//
//        }
//
//        @Override
//        public List<Comic> findAll(Sort sort) {
//            return List.of();
//        }
//
//        @Override
//        public Page<Comic> findAll(Pageable pageable) {
//            return null;
//        }
//
//        @Override
//        public <S extends Comic> Optional<S> findOne(Example<S> example) {
//            return Optional.empty();
//        }
//
//        @Override
//        public <S extends Comic> Page<S> findAll(Example<S> example, Pageable pageable) {
//            return null;
//        }
//
//        @Override
//        public <S extends Comic> long count(Example<S> example) {
//            return 0;
//        }
//
//        @Override
//        public <S extends Comic> boolean exists(Example<S> example) {
//            return false;
//        }
//
//        @Override
//        public <S extends Comic, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
//            return null;
//        }
//    });
//
//    @Test
//    void temp() {
//        System.out.println(comicService.getComic(1).toString());
//
//		System.out.println("test");
//
//    }
//
//    @Test
//    void contextLoads() {
//    }
//
//
//}
