package com.xu.yan.employee_management.mapper;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;
import java.time.LocalDate;

public class LocalDateTypeHandler extends BaseTypeHandler<LocalDate> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, LocalDate parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter != null ? parameter.toString() : null);
    }

    @Override
    public LocalDate getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String date = rs.getString(columnName);
        return date != null ? LocalDate.parse(date) : null;
    }

    @Override
    public LocalDate getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String date = rs.getString(columnIndex);
        return date != null ? LocalDate.parse(date) : null;
    }

    @Override
    public LocalDate getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String date = cs.getString(columnIndex);
        return date != null ? LocalDate.parse(date) : null;
    }
}