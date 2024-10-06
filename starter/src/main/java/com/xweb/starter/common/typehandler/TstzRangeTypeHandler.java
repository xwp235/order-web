package com.xweb.starter.common.typehandler;

import com.xweb.starter.common.dbmappingtypes.TstzRange;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.postgresql.util.PGobject;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TstzRangeTypeHandler extends BaseTypeHandler<TstzRange> {


    private static final String regex = "\\[\\s*\"(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\+\\d{2})\"\\s*,\\s*\"(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\+\\d{2})\"\\s*]";
    private static final Pattern pattern = Pattern.compile(regex);

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, TstzRange parameter, JdbcType jdbcType) throws SQLException {
        PGobject pgObject = new PGobject();
        pgObject.setType("tstzrange");
        pgObject.setValue(String.format("[%s,%s]", parameter.start(), parameter.end()));
        ps.setObject(i, pgObject);
    }

    @Override
    public TstzRange getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return parseRange(rs.getString(columnName));
    }

    @Override
    public TstzRange getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return parseRange(rs.getString(columnIndex));
    }

    @Override
    public TstzRange getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return parseRange(cs.getString(columnIndex));
    }

    private TstzRange parseRange(String rangeString) {
        // 解析范围字符串，这里需要根据 PostgreSQL 的范围字符串格式进行解析
        // 例如："[2022-03-01 10:00:00,2022-03-01 12:00:00]"
        // 解析出范围内的起始时间和结束时间
        // 这里只是一个简单示例，你可能需要根据实际情况进行修改
        // 你也可以使用第三方库进行解析，比如 Jackson、Gson 等
        // 此处仅作示例，实际处理可能更加复杂

        Matcher matcher = pattern.matcher(rangeString);
        if (matcher.matches()) {
            ZonedDateTime start = ZonedDateTime.parse(matcher.group(1).replace(" ","T"));
            ZonedDateTime end = ZonedDateTime.parse(matcher.group(2).replace(" ","T"));
            return new TstzRange(start,end);
        } else {
            throw new IllegalArgumentException("Invalid range string: " + rangeString);
        }
    }

}
