package com.xweb.starter.common.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimestamptzTypeHandler extends BaseTypeHandler<ZonedDateTime> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, ZonedDateTime parameter, JdbcType jdbcType) throws SQLException {
        ps.setObject(i, parameter.withZoneSameInstant(ZoneId.of("UTC")).toOffsetDateTime());
    }

    @Override
    public ZonedDateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return toZonedDateTime(rs.getObject(columnName));
    }

    @Override
    public ZonedDateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return toZonedDateTime(rs.getObject(columnIndex));
    }

    @Override
    public ZonedDateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return toZonedDateTime(cs.getObject(columnIndex));
    }

    private ZonedDateTime toZonedDateTime(Object object) {
        if (object instanceof Timestamp timestamp) {
            return ZonedDateTime.ofInstant(timestamp.toInstant(), ZoneId.systemDefault());
        } else if (object instanceof OffsetDateTime offsetDateTime) {
            return offsetDateTime.toZonedDateTime();
        }
        return null;
    }
}
