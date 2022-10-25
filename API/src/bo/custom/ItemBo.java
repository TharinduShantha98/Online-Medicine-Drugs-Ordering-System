package bo.custom;

import Entity.Item;
import bo.Crud;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBo extends Crud<Item, String> {

    public ArrayList<String> getAllItemId() throws SQLException;


}
