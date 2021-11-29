package com.sai.car.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sai.car.model.Car;

@Repository
@Transactional
public interface CarRepository extends JpaRepository<Car, Long> {

	// EntityManager entityManager =
	// Persistence.createEntityManagerFactory("com.sai.car_persistence_configuration")
	// .createEntityManager();

	public Optional<Car> findByName(String name);

	// Example of native query but does not return anything
	@Query(value = "insert into cars values(:id,:name, :type)", nativeQuery = true)
	public void addCarDetails(@Param("name") String name, @Param("type") String type);

	@Modifying
	@Query(value = "update cars set type = :type where name =:name", nativeQuery = true)
	public int updateCarDetails(String name, String type);

	@Modifying
	@Query(value = "delete from cars where name=:name", nativeQuery = true)
	public Optional<Integer> deleteCarDetails(String name);

//	public default Optional<List<Car>> addMultipleCarsDetails(List<Car> cars) throws CarNotAddedException {
//		cars.forEach(c -> entityManager.persist(c));
//		return Optional.of(cars);
//	}

}
