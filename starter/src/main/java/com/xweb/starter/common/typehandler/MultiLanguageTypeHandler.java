package com.xweb.starter.common.typehandler;

import com.xweb.starter.common.dbmappingtypes.MultiLanguage;
import com.xweb.starter.utils.SimpleJsonUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MultiLanguageTypeHandler extends BaseTypeHandler<MultiLanguage> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, MultiLanguage parameter, JdbcType jdbcType) throws SQLException {

      ps.setString(i, SimpleJsonUtil.toJson(parameter));
    }

    @Override
    public MultiLanguage getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return getMenuName(rs.getString(columnName));
    }

    @Override
    public MultiLanguage getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return getMenuName(rs.getString(columnIndex));
    }

    @Override
    public MultiLanguage getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getMenuName(cs.getString(columnIndex));
    }

    private MultiLanguage getMenuName(String jsonString) {
        if (jsonString == null) {
            return null;
        }
        return SimpleJsonUtil.fromJson(jsonString, MultiLanguage.class);
    }
    
}
