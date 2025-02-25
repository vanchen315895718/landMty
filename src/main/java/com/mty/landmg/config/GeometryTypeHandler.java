package com.mty.landmg.config;

import net.postgis.jdbc.PGgeometry;
import net.postgis.jdbc.geometry.Geometry;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes({String.class})
public class GeometryTypeHandler extends BaseTypeHandler<String> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String str, JdbcType jdbcType) throws SQLException {
        PGgeometry pGgeometry = new PGgeometry(str);
        Geometry geometry = pGgeometry.getGeometry();
        geometry.setSrid(4326);
        ps.setObject(i, pGgeometry);
    }

    @Override
    public String getNullableResult(ResultSet rs, String colName) throws SQLException {
        PGgeometry pGgeometry = new PGgeometry(rs.getString(colName));
        if (pGgeometry == null) {
            return null;
        }
        return pGgeometry.toString();
    }

    @Override
    public String getNullableResult(ResultSet rs, int colIdx) throws SQLException {
        PGgeometry pGgeometry = new PGgeometry(rs.getString(colIdx));
        if (pGgeometry == null) {
            return null;
        }
        return pGgeometry.toString();
    }

    @Override
    public String getNullableResult(CallableStatement cs, int colIdx) throws SQLException {
        PGgeometry pGgeometry = new PGgeometry(cs.getString(colIdx));
        if (pGgeometry == null) {
            return null;
        }
        return pGgeometry.toString();
    }

    /**
     * 将 PGgeometry 的字符串表示转换为 WKT 格式（去除 SRID 部分）
     */
    private String toWKT(PGgeometry pg) {
        if (pg == null) {
            return null;
        }
        String geoStr = pg.toString();
        // 如果包含 "SRID=4326;" 这样的前缀，则截取 ";" 后面的部分作为 WKT
        int index = geoStr.indexOf(";");
        if (index != -1) {
            return geoStr.substring(index + 1);
        }
        return geoStr;
    }
}
