package bo.custom.impl;

import Entity.Item;
import bo.custom.ItemBo;
import contoller.ItemController;

import java.sql.*;
import java.util.ArrayList;

public class ItemBoImpl implements ItemBo {
    @Override
    public boolean add(Item item) throws SQLException, ClassNotFoundException {

        Connection connection = ItemController.dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Item VALUES (?,?,?,?,?,?,?,?)");
        preparedStatement.setObject(1,item.getItemCode());
        preparedStatement.setObject(2,item.getItemName());
        preparedStatement.setObject(3,item.getBrandName());
        preparedStatement.setObject(4,item.getDescription());
        preparedStatement.setObject(5,item.getPrice());
        preparedStatement.setObject(6,item.getDrugType());
        preparedStatement.setObject(7,item.getQuantity());
        preparedStatement.setObject(8,item.getExpireDate());

        if(preparedStatement.executeUpdate()>0){
            connection.close();
            return  true;

        }else{
            connection.close();
            return  false;

        }


    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Item item) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Item search(String s) throws SQLException, ClassNotFoundException {
        Connection connection = ItemController.dataSource.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM item WHERE itemCode =? ");
        preparedStatement.setObject(1,s);
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            String itemCode = resultSet.getString(1);
            String itemName = resultSet.getString(2);
            String brandName = resultSet.getString(3);
            String description = resultSet.getString(4);
            String price = resultSet.getString(5);
            String drugType = resultSet.getString(6);
            String quantity = resultSet.getString(7);
            String expireDate = resultSet.getString(8);


            return  new Item(
                    itemCode,
                    itemName,
                    brandName,
                    description,
                    Double.parseDouble(price),
                    drugType,
                    Double.parseDouble(quantity),
                    Date.valueOf(expireDate)

            );

        }




        return null;


    }

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> getAllItemId() throws SQLException {
        Connection connection = ItemController.dataSource.getConnection();
        ArrayList<String> stringArrayList = new ArrayList<>();

        PreparedStatement preparedStatement =
                connection.prepareStatement("SELECT itemCode FROM item");

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            stringArrayList.add(resultSet.getString(1));
        }

        return stringArrayList;

    }
}
