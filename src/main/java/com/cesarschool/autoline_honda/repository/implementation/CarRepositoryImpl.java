package com.cesarschool.autoline_honda.repository.implementation;

import com.cesarschool.autoline_honda.domain.Car;
import com.cesarschool.autoline_honda.repository.CarRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Optional;

@Repository
public class CarRepositoryImpl implements CarRepository {

    private final JdbcTemplate jdbcTemplate;

    public CarRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int saveCar(Car car) {
        String sql = "INSERT INTO Car (chassis, price, color, wheel_size, fuel_type, year, engine, transmission, mileage) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, car.getChassis(), car.getPrice(), car.getColor(), car.getWheelSize(),
                car.getFuelType(), car.getYear(), car.getEngine(), car.getTransmission(), car.getMileage());
    }

    @Override
    public int updateCar(Car car) {
        String sql = "UPDATE Car SET price = ?, color = ?, wheel_size = ?, fuel_type = ?, year = ?, engine = ?, " +
                "transmission = ?, mileage = ? WHERE chassis = ?";
        return jdbcTemplate.update(sql, car.getPrice(), car.getColor(), car.getWheelSize(), car.getFuelType(),
                car.getYear(), car.getEngine(), car.getTransmission(), car.getMileage(), car.getChassis());
    }

    @Override
    public int deleteCarByChassis(String chassis) {
        String sql = "DELETE FROM Car WHERE chassis = ?";
        return jdbcTemplate.update(sql, chassis);
    }

    @Override
    public Optional<Car> findCarByChassis(String chassis) {
        String sql = "SELECT * FROM Car WHERE chassis = ?";
        try {
            Car car = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Car(
                    rs.getString("chassis"),
                    rs.getFloat("price"),
                    rs.getString("color"),
                    rs.getInt("wheel_size"),
                    rs.getString("fuel_type"),
                    rs.getInt("year"),
                    rs.getString("engine"),
                    rs.getString("transmission"),
                    rs.getInt("mileage"),
                    rs.getString("car_condition")
            ), chassis);
            return Optional.ofNullable(car);
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Car> findAllCars() {
        String sql = "SELECT * FROM Car";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Car(
                rs.getString("chassis"),
                rs.getFloat("price"),
                rs.getString("color"),
                rs.getInt("wheel_size"),
                rs.getString("fuel_type"),
                rs.getInt("year"),
                rs.getString("engine"),
                rs.getString("transmission"),
                rs.getInt("mileage"),
                rs.getString("car_condition")
        ));
    }
}