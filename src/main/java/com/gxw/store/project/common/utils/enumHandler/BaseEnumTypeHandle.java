package com.gxw.store.project.common.utils.enumHandler;


import com.gxw.store.project.common.utils.enums.ExpressType;
import com.gxw.store.project.order.entity.OrderStatus;
import com.gxw.store.project.sale.entity.Mode;
import com.gxw.store.project.user.entity.admin.AdminStatus;
import com.gxw.store.project.user.entity.business.BusinessScale;
import com.gxw.store.project.user.entity.business.BusinessStatus;
import com.gxw.store.project.user.entity.business.StaffStatus;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(value = {BusinessScale.class, BusinessStatus.class, StaffStatus.class, AdminStatus.class, ExpressType.class, OrderStatus.class, Mode.class})
public class BaseEnumTypeHandle<E extends Enum<E> & BaseEnum> extends BaseTypeHandler<E> {
    private final Class<E> type;

    public BaseEnumTypeHandle(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, E e, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, e.getIndex());
    }

    @Override
    public E getNullableResult(ResultSet resultSet, String s) throws SQLException {
        Integer index = resultSet.getInt(s);
        return resultSet.wasNull() ? null : EnumUtils.indexOf(this.type, index);
    }

    @Override
    public E getNullableResult(ResultSet resultSet, int i) throws SQLException {
        Integer index = resultSet.getInt(i);
        return resultSet.wasNull() ? null : EnumUtils.indexOf(this.type, index);
    }

    @Override
    public E getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        Integer index = callableStatement.getInt(i);
        return callableStatement.wasNull() ? null : EnumUtils.indexOf(this.type, index);
    }
}
