package contoller;

import Entity.OrderDetail;
import Entity.Orders;
import bo.BOFactory;
import bo.custom.OrderBo;
import bo.custom.impl.OrderBoImpl;

import javax.annotation.Resource;
import javax.json.*;
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

@WebServlet(urlPatterns = "/orders")
public class OrderController extends HttpServlet {

    @Resource(name = "java:comp/env/jdbc/pool")
    public  static DataSource dataSource;

    //OrderBo orderBo = new OrderBoImpl();

    private final  OrderBo orderBo = (OrderBo) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.ORDER);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();
        resp.setContentType("application/json");

        String orderId = jsonObject.getString("orderId");
        Date orderDate  =Date.valueOf(jsonObject.getString("OrderDate"));
        double orderTotal = Double.parseDouble(jsonObject.getString("orderTotal"));
        String patientId = jsonObject.getString("patientId");
        JsonArray itemDetails = jsonObject.getJsonArray("itemDetails");


        ArrayList<OrderDetail> orderDetails = new ArrayList<>();
        for(JsonValue jsonValue : itemDetails){

            String orderId2 = jsonValue.asJsonObject().getString("orderId");
            String  itemCode  = jsonValue.asJsonObject().getString("itemCode");
            String  quantity = jsonValue.asJsonObject().getString("quantity");
            String  price = jsonValue.asJsonObject().getString("price");

            orderDetails.add(new OrderDetail(
                    orderId2,
                    itemCode,
                    Double.parseDouble(quantity),
                    Double.parseDouble(price)
                    ));


        }

        System.out.println(orderDetails.size());
        Orders orders = new Orders(
                orderId,
                orderDate,
                orderTotal,
                patientId,
                orderDetails);
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        PrintWriter writer = resp.getWriter();
        try {
            boolean add = orderBo.addOrder(orders);
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
        }


    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String option = req.getParameter("option");
        resp.setContentType("application/json");
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        PrintWriter writer = resp.getWriter();

        switch (option){
            case "NEXTID":

                try {
                    String nextId = orderBo.nextId();


                    objectBuilder.add("data",nextId );
                    objectBuilder.add("message", "done");
                    objectBuilder.add("status", "200");
                    writer.print(objectBuilder.build());


                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


                break;


        }


    }
}
