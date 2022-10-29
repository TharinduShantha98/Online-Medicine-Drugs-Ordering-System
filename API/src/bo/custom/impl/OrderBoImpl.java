package bo.custom.impl;

import Entity.OrderDetail;
import Entity.Orders;
import bo.custom.OrderBo;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import contoller.ItemController;
import contoller.OrderController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBoImpl implements OrderBo {
    @Override
    public boolean addOrder(Orders order) throws SQLException {

        Connection connection = OrderController.dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO orders VALUES (?,?,?,?)");
        preparedStatement.setObject(1,order.getOrderId());
        preparedStatement.setObject(2,order.getOrderDate());
        preparedStatement.setObject(3,order.getOrderTotal());
        preparedStatement.setObject(4,order.getPatientId());

        if(preparedStatement.executeUpdate()>0){

            ArrayList<OrderDetail> orderDetails = order.getOrderDetails();
            System.out.println(orderDetails.size());

            for(int i=0; i < orderDetails.size(); i++){
                PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO orderDetail VALUES (?,?,?,?)");

                preparedStatement1.setObject(1,orderDetails.get(i).getOrderId());
                preparedStatement1.setObject(2,orderDetails.get(i).getItemCode());
                preparedStatement1.setObject(3,orderDetails.get(i).getQuantity());
                preparedStatement1.setObject(4,orderDetails.get(i).getPrice());

                if(preparedStatement1.executeUpdate()>0){

                }
            }



            return  true;





        }


        return false;
    }

    @Override
    public String nextId() throws SQLException {

        Connection connection = OrderController.dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT orderId  FROM orders  ORDER BY  orderId  DESC LIMIT 1");
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            int tempId = Integer.parseInt(resultSet.getString(1).split("-")[1]);
            tempId = tempId +1;
            return  "o-" + tempId;
        }

        return "o-100";
    }

    @Override
    public boolean add(Orders orders) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Orders orders) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Orders search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Orders> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
