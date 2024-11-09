//package com.cesarschool.autoline_honda.repository;
//
//import com.cesarschool.autoline_honda.domain.Car;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class CarRepositoryImpl implements CarRepository {
//    private final JdbcTemplate jdbcTemplate;
//
//    public CarRepositoryImpl(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @Override
//    public List<Car> findAll() {
//        // Write SQL and map result to Car objects
//        String sql = "SELECT * FROM cars";
//        return jdbcTemplate.query(sql, (rs, rowNum) -> new Car(
//                rs.getString("chassis"),
//                rs.getString("make"),
//                rs.getString("model")
//        ));
//    }
//
//    @Override
//    public Car findById(String chassis) {
//        // Write SQL to find Car by chassis
//        String sql = "SELECT * FROM cars WHERE chassis = ?";
//        return jdbcTemplate.queryForObject(sql, new Object[]{chassis}, (rs, rowNum) -> new Car(
//                rs.getString("chassis"),
//                rs.getString("make"),
//                rs.getString("model")
//        ));
//    }
//
//    @Override
//    public void save(Car car) {
//        // SQL to insert a car
//        String sql = "INSERT INTO cars (chassis, make, model) VALUES (?, ?, ?)";
//        jdbcTemplate.update(sql, car.getChassis(), car.getMake(), car.getModel());
//    }
//
//    @Override
//    public void update(Car car) {
//        // SQL to update car information
//        String sql = "UPDATE cars SET make = ?, model = ? WHERE chassis = ?";
//        jdbcTemplate.update(sql, car.getMake(), car.getModel(), car.getChassis());
//    }
//
//    @Override
//    public void deleteById(String chassis) {
//        // SQL to delete a car by chassis
//        String sql = "DELETE FROM cars WHERE chassis = ?";
//        jdbcTemplate.update(sql, chassis);
//    }
//}
