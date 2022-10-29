package bo.custom;

import Entity.Orders;
import bo.Crud;
import com.mysql.cj.x.protobuf.MysqlxCrud;

import java.sql.SQLException;

public interface OrderBo extends Crud<Orders , String> {

    boolean addOrder(Orders orders) throws SQLException;
    public  String nextId() throws SQLException;



}
