package com.cesarschool.autoline_honda.repository.implementation;

import com.cesarschool.autoline_honda.domain.Specification;
import com.cesarschool.autoline_honda.repository.SpecificationRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Optional;

@Repository
public class SpecificationRepositoryImpl implements SpecificationRepository {

    private final JdbcTemplate jdbcTemplate;

    public SpecificationRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int saveSpecification(Specification specification) {
        String sql = "INSERT INTO Specification (category, model, version, car_chassis) " +
                "VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, specification.getCategory(), specification.getModel(),
                specification.getVersion(), specification.getCarChassis());
    }

    @Override
    public int updateSpecification(Specification specification) { //trocar caso precise fazer update
        String sql = "UPDATE Specification SET version = ?, car_chassis = ? WHERE category = ? AND model = ?";
        return jdbcTemplate.update(sql, specification.getVersion(), specification.getCarChassis(),
                specification.getCategory(), specification.getModel());
    }

    @Override
    public int deleteSpecificationByCarChassis(String carChassis) {
        String sql = "DELETE FROM Specification WHERE car_chassis = ?";
        return jdbcTemplate.update(sql, carChassis);
    }

    @Override
    public Optional<Specification> findSpecificationByCarChassis(String carChassis) {
        String sql = "SELECT * FROM Specification WHERE car_chassis = ?";
        try {
            Specification specification = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Specification(
                    rs.getString("category"),
                    rs.getString("model"),
                    rs.getString("version"),
                    rs.getString("car_chassis")
            ), carChassis);
            return Optional.ofNullable(specification);
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Specification> findAllSpecifications() {
        String sql = "SELECT * FROM Specification";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Specification(
                rs.getString("category"),
                rs.getString("model"),
                rs.getString("version"),
                rs.getString("car_chassis")
        ));
    }
}
