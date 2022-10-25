package contoller;

import Entity.Item;
import Entity.Patient;
import bo.custom.ItemBo;
import bo.custom.impl.ItemBoImpl;

import javax.annotation.Resource;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/item")
public class ItemController extends HttpServlet {

    ItemBo itemBo = new ItemBoImpl();

    @Resource(name = "java:comp/env/jdbc/pool")
    public  static DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String option = req.getParameter("option");
        resp.setContentType("application/json");

        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        PrintWriter writer = resp.getWriter();

        switch (option){
            case "GETALL":
                break;
            case "SEARCH":

                String itemCode = req.getParameter("ItemCode");

                try {
                    Item search = itemBo.search(itemCode);
                    JsonObjectBuilder objectBuilder1 = Json.createObjectBuilder();
                    objectBuilder1.add("itemCode",search.getItemCode());
                    objectBuilder1.add("itemName",search.getItemName());
                    objectBuilder1.add("brandName",search.getBrandName());
                    objectBuilder1.add("description",search.getDescription());
                    objectBuilder1.add("price",search.getPrice());
                    objectBuilder1.add("drugType",search.getDrugType());
                    objectBuilder1.add("quantity",search.getQuantity());
                    objectBuilder1.add("expireDate", String.valueOf(search.getExpireDate()));


                    if(search != null){
                        objectBuilder.add("data",objectBuilder1.build());
                        objectBuilder.add("message", "Search Successfully !!");
                        objectBuilder.add("status", "200");
                        writer.print(objectBuilder.build());

                    }else{
                        objectBuilder.add("data", "");
                        objectBuilder.add("message", "Search Not Successfully !!");
                        objectBuilder.add("status", "500");
                        writer.print(objectBuilder.build());

                    }




                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


                break;
            case "GETALLID":
                JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
                try {
                    ArrayList<String> allItemId = itemBo.getAllItemId();


                    for(int i=0; i < allItemId.size(); i++){
                        arrayBuilder.add(allItemId.get(i));

                    }

                    objectBuilder.add("data",arrayBuilder.build() );
                    objectBuilder.add("message", "done");
                    objectBuilder.add("status", "200");
                    writer.print(objectBuilder.build());




                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


                break;

        }



    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        Date date = Date.valueOf(req.getParameter("expireDate"));


        try {
            boolean add = itemBo.add(new Item(
                    req.getParameter("itemCode"),
                    req.getParameter("itemName"),
                    req.getParameter("brandName"),
                    req.getParameter("description"),
                    Double.parseDouble(req.getParameter("price")),
                    req.getParameter("drugType"),
                    Double.parseDouble(req.getParameter("quantity")),
                    date


            ));
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            PrintWriter writer = resp.getWriter();

            if(add){
                objectBuilder.add("data", "");
                objectBuilder.add("message", "Successfully added!!");
                objectBuilder.add("status", "200");
                writer.print(objectBuilder.build());
            }else{
                objectBuilder.add("data", "");
                objectBuilder.add("message", "NOT Successfully added!!");
                objectBuilder.add("status", "500");
                writer.print(objectBuilder.build());
            }





        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
