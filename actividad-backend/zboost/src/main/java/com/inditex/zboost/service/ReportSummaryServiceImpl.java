package com.inditex.zboost.service;

import com.inditex.zboost.entity.ReportSummary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ReportSummaryServiceImpl implements ReportSummaryService {

    private NamedParameterJdbcTemplate jdbcTemplate;
    private ProductService productService;

    public ReportSummaryServiceImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ReportSummary reportSummary() {
        /**
         * TODO: EJERCICIO 3. Reporte sumarizado
         */

        String sql = "";
        ReportSummary reportSummary = jdbcTemplate.queryForObject(sql, Map.of(), new BeanPropertyRowMapper<>(ReportSummary.class));

        String totalProductsByCategorySql = "";
        Map<String, Integer> totalProductsByCategory = new HashMap<>();
        jdbcTemplate.query(totalProductsByCategorySql, rs -> {
            totalProductsByCategory.put(rs.getString("category"), rs.getInt("count"));
        });

        reportSummary.setTotalProductsByCategory(totalProductsByCategory);
        return reportSummary;
    }
}
