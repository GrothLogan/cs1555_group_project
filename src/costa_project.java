import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.RecursiveAction;
import java.util.*;
import javax.naming.spi.DirStateFactory.Result;
public class costa_project {
    public static void main(String args[]) throws
    SQLException, ClassNotFoundException {
        //Scanner myObj = new Scanner(System.in);
        while (true){
            Scanner myObj = new Scanner(System.in);
            System.out.println("Welcome to costa express are you WHat is your username and password");
            System.out.print("username: ");
            String user = myObj.nextLine();
            System.out.println();
            System.out.print("password: ");
            String pass = myObj.nextLine();
            if(user.equals("username") && pass.equals("password"))
            {
                System.out.println("are you a \n0) exit\n1) database admin \n2) passenger service operator");
                System.out.println("input as int");
                int choice = myObj.nextInt();
                
                if(choice == 0)
                {
                    System.exit(0);
                }
                else if (choice == 1)
                {
                    System.out.println("admin");
                    admin();
                    // call to admin code
                }
                else if (choice == 2)
                {
                    System.out.println("service");
                    operator();
                    //call to passenger service operator code
                }
                /*
                else if (choice == 3)
                {
                    Class.forName("org.postgresql.Driver");
                    String url = "jdbc:postgresql://localhost:5432/postgres";
                    Properties props = new Properties();
                    props.setProperty("user", "username"); //made this a superuser
                    props.setProperty("password", "password"); 
                    Connection conn =
                            DriverManager.getConnection(url, props);
                    Statement st = conn.createStatement();
                    CallableStatement cstmt = conn.prepareCall("{? = call int_equal(?,?)}");
                    cstmt.registerOutParameter(1, Types.BOOLEAN);
                    cstmt.setInt(1, 1);
                    cstmt.setInt(2, 1);
                    cstmt.execute();
                    if(cstmt.getBoolean(1))
                    {
                        //something
                    }
                    //test stuff
                }
                */
                else
                {
                    System.out.println("not an option");
                }
                System.out.println("");
            }
            else
            {
                System.out.print("incorrect username or password (note the username is username and the password is password) ");
            }

        }

    }

    // hub for admin functions
    public static void admin()throws
    SQLException, ClassNotFoundException 
    {
        //System.out.println("in admin");
        while(true)
        {
            System.out.println("Would you like to \n0) exit\n1) import data \n2) export data \n3) delete database");
            Scanner myObj = new Scanner(System.in);
            int choice = myObj.nextInt();
            if(choice == 0)
            {
                return;
            }
            else if (choice == 1)
            {
                System.out.println("import data");
                import_data();
                // call to admin code
            }
            else if (choice == 2)
            {
                System.out.println("export data");
                export_data();
                //call to passenger service operator code
            }
            else if (choice == 3)
            {
                System.out.println("delete database");
                delete_database();
                //call to passenger service operator code
            }
            else
            {
                System.out.println("not an option");
            }
            System.out.println("");

        }

    }
    //
    

    // hub for operator functions
    public static void operator()throws
    SQLException, ClassNotFoundException 
    {
        while(true)
        {
            System.out.println("Would you like to look for \n 0) exit \n 1) customer form \n 2) trip search \n 3) add reservation\n 4) get ticket \n 5) advanced search");
            Scanner myObj = new Scanner(System.in);
            int choice = myObj.nextInt();
            if(choice == 0)
            {
                return;
            }
            else if (choice == 1)
            {
                System.out.println("customer");
                customer();
                // call to admin code
            }
            else if (choice == 2)
            {
                System.out.println("trip_search");
                trip_search();
                //call to passenger service operator code
            }
            else if (choice == 3)
            {
                System.out.println("add reservation");
                add_reservation();
                //call to passenger service operator code
            }
            else if (choice == 4)
            {
                System.out.println("get ticket");
                get_ticket();
                //call to passenger service operator code
            }
            else if (choice == 5)
            {
                System.out.println("advanced search");
                advanced_search();
                //call to passenger service operator code
            }
            else
            {
                System.out.println("not an option");
            }
            System.out.println("");

        }
    }

    public static void customer()throws
    SQLException, ClassNotFoundException 
    {

            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/postgres";
            Properties props = new Properties();
            props.setProperty("user", "username"); //made this a superuser
            props.setProperty("password", "password"); 
            Connection conn =
                    DriverManager.getConnection(url, props);
            Statement st = conn.createStatement();
        while(true)
        {
            System.out.println("in customer \nWould you like \n 0) back \n 1) add \n 2) edit \n 3) view");
            Scanner myObj = new Scanner(System.in);
            int choice = myObj.nextInt();
            if(choice == 0)
            {
                return;
            }
            else if (choice == 1)
            {
                System.out.println("add customer");
                myObj.nextLine();
                System.out.println("customer fname:");
                String fname =  myObj.nextLine();
                System.out.println("customer lname:");
                String lname =  myObj.nextLine();
                System.out.println("customer street:");
                String street = myObj.nextLine();
                System.out.println("customer town:");
                String town = myObj.nextLine();
                System.out.println("customer postal code:");
                String postal_code = myObj.nextLine();
                ResultSet last_id = st.executeQuery("SELECT max(customers_id) FROM PASSENGERS");
                try
                {
                    last_id.next();
                    int big  = last_id.getInt(1);
                    big++;
                    String k = String.format("INSERT INTO PASSENGERS(customers_id, fname, lname, street, town, postal_code ) VALUES (%s, '%s', '%s', '%s', '%s','%s');", big,fname,lname,street,town,postal_code );
                 conn.setAutoCommit(false);
                 st.executeUpdate(k);
                 conn.commit();
                 System.out.println("successfully added passenger with id:" + big);
                }
                catch (SQLException e1) {
                    System.out.println(e1.toString());
                 try {
                     conn.rollback();
                 } catch (SQLException e2) {
                     System.out.println(e2.toString());
                 }

                }


                // code for add
            }
            else if (choice == 2)
            {
                //code for edit
                System.out.println("edit customer");
                System.out.println("id of who is edited:");
                int id =  myObj.nextInt();
                myObj.nextLine();
                System.out.println("new customer fname:");
                String fname =  myObj.nextLine();
                System.out.println("new customer lname:");
                String lname =  myObj.nextLine();
                System.out.println("new customer street:");
                String street = myObj.nextLine();
                System.out.println("new customer town:");
                String town = myObj.nextLine();
                System.out.println("new customer postal code:");
                String postal_code = myObj.nextLine();
                try
                {
                String k = String.format("UPDATE PASSENGERS SET fname = '%s', lname = '%s', street = '%s', town = '%s', postal_code = '%s' WHERE customers_id = %d",fname,lname,street,town,postal_code, id );
                 conn.setAutoCommit(false);
                 st.executeUpdate(k);
                 conn.commit();
                 System.out.println("successfully updated passenger with id:" + id);
                 System.out.println("if they exist");
                }
                catch (SQLException e1) {
                    System.out.println(e1.toString());
                 try {
                     conn.rollback();
                 } catch (SQLException e2) {
                     System.out.println(e2.toString());
                 }

                }

            }
            else if (choice == 3)
            {
                System.out.println("view customer");
                System.out.println("id of who is to be viewed:");
                int id =  myObj.nextInt();
                ResultSet person = st.executeQuery("SELECT * FROM PASSENGERS WHERE customers_id = " + id);
                try {
                    person.next();
                    String fname = person.getString("fname");
                    String lname = person.getString("lname");
                    String street = person.getString("street");
                    String town = person.getString("town");
                    String postal = person.getString("postal_code");
                    System.out.println("Viewing passenger with \n id:" + id + "\n name:" + fname + " " + lname + "\n street:" + street + "\n town: "+town +"\n postal_code" + postal);
                }
                catch (SQLException e1) {
                    System.out.println(e1.toString());


                }
                
                //call to passenger service operator code
            }
            else
            {
                System.out.println("not an option");
            }
        }


    }
    public static void trip_search()throws
    SQLException, ClassNotFoundException 
    {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/postgres";
        Properties props = new Properties();
        props.setProperty("user", "username"); //made this a superuser
        props.setProperty("password", "password"); 
        Connection conn =
                DriverManager.getConnection(url, props);
        Statement st = conn.createStatement();
        while(true)
        {
            System.out.println("in trip search");
            Scanner myObj = new Scanner(System.in);
            String day = "";
            while (day.equals("Monday")  == false && day.equals("Tuesday") == false && day.equals("Wednesday") == false && day.equals("Thursday") == false && day.equals("Friday") == false && day.equals("Saturday") == false && day.equals("Sunday") == false)
            {
                System.out.println("What day (FIrst letter capital the rest lower case example 'Saturday')");
                //myObj.nextLine();
                day = myObj.nextLine();
            }
            System.out.println("where are you leaving from? (station id)");
            int start = myObj.nextInt();
            System.out.println("where are you going? (station id)");
            int dest = myObj.nextInt();
            System.out.println("start:" + start + " dest:" + dest + " day: " + day);
            System.out.println("how would you like to search \n 0) back \n 1) single route \n 2) combination of routes (WARNING DO NOT RUN THIS IT WORKS BUT IT MAY TAKE HOURS)");
            //Scanner myObj = new Scanner(System.in);
            int choice = myObj.nextInt();
            System.out.println("start:" + start + " dest:" + dest + " day: " + day);
            try
            {
                conn.setAutoCommit(false);
                st.executeUpdate("DELETE FROM PATHY");
                conn.commit();
            }
            catch(SQLException e)
            {
                System.out.println(e.toString());
            }
            if(choice == 0)
            {
                return;
            }
            else if(choice == 1)
            {
                //System.out.println("routes: ");
                ResultSet legs = st.executeQuery("SELECT * FROM LEGS WHERE station_start = " + start + " AND start_day = '" + day + "' AND end_day = '" + day + "' AND available_seats > 0 AND working = 'true'");
                legs.next();
                try
                {
                    while(legs.isAfterLast() != true)
                    {
                        Time timstar = legs.getTime("departure_time");
                        int route_id = legs.getInt("route_id");
                        int train_id = legs.getInt("train_id");
                        int curr = legs.getInt("station_end");
                        Time arrival_time= legs.getTime("arrival_time");
                        int stops = 1;
                        int passes = legs.getInt("stations_passed");
                        int cost = legs.getInt("cost");
                        int leg_id = legs.getInt("legs_id");
                        String legy = leg_id + "";
                        int depth = 30;
                        CallableStatement cstmt = conn.prepareCall("call single_route("+route_id+",'"+day+"',"+curr+","+train_id+","+stops+","+cost+",'"+arrival_time+"',"+passes+","+dest+",'"+legy+"','"+timstar+"',"+depth+")");
                        //recursive_search(route_id, day, curr, train_id, stops, cost, arrival_time, passes, dest, legy, timstar, conn, st, depth);
                        cstmt.execute();
                        legs.next();
                    }
                }
                catch(SQLException e1)
                {
                    //System.out.print("no routes");
                    System.out.print(e1.toString());
                }
                System.out.println("ordered by? \n1) price \n2) total time\n3) stops\n4)stations");
                int choice2 = myObj.nextInt();
                if(choice2 == 1)
                {
                    ResultSet legs2 = st.executeQuery("SELECT * FROM PATHY order by cost ASC ");
                    legs2.next();
                    int j = 0;
                    try
                    {
                        while(j < 10 && legs2.isAfterLast() != true)
                        {
                            System.out.println("cost: " + legs2.getInt("cost"));
                            System.out.println("stations_passed: " + legs2.getInt("stations_passed"));
                            System.out.println("stops: " + legs2.getInt("stops"));
                            System.out.println("start_time " + legs2.getString("start_time"));
                            System.out.println("arrival_time " + legs2.getString("arrival_time"));
                            System.out.println("legs: " + legs2.getString("legs").trim());
                            System.out.println();
                            j++;
                            legs2.next();
                        }
                    }
                    catch(SQLException e1)
                    {
                        System.out.print("no routes");
                        System.out.print(e1.toString());
                    }
                    catch(Exception e)
                    {
                        System.out.print("no routes");
                        //System.out.print(e1.toString());
                    }
                    //order by price
                }
                if(choice2 == 2)
                {
                    ResultSet legs2 = st.executeQuery("SELECT * FROM PATHY order by (arrival_time - start_time) ASC ");
                    legs2.next();
                    int j = 0;
                    try
                    {
                        while(j < 10 && legs2.isAfterLast() != true)
                        {
                            System.out.println("cost: " + legs2.getInt("cost"));
                            System.out.println("stations_passed: " + legs2.getInt("stations_passed"));
                            System.out.println("stops: " + legs2.getInt("stops"));
                            System.out.println("start_time " + legs2.getString("start_time"));
                            System.out.println("arrival_time " + legs2.getString("arrival_time"));
                            System.out.println("legs: " + legs2.getString("legs").trim());
                            System.out.println();
                            j++;
                            legs2.next();
                        }
                    }
                    catch(SQLException e1)
                    {
                        System.out.print("no routes");
                        System.out.print(e1.toString());
                    }
                    catch(Exception e)
                    {
                        System.out.print("no routes");
                        //System.out.print(e1.toString());
                    }
                    //just assume it always travels at max speed
                    //order by time 
                }
                if(choice2 == 3)
                {
                    ResultSet legs2 = st.executeQuery("SELECT * FROM PATHY order by stops ASC ");
                    legs2.next();
                    int j = 0;
                    try
                    {
                        while(j < 10 && legs2.isAfterLast() != true)
                        {
                            System.out.println("cost: " + legs2.getInt("cost"));
                            System.out.println("stations_passed: " + legs2.getInt("stations_passed"));
                            System.out.println("stops: " + legs2.getInt("stops"));
                            System.out.println("start_time " + legs2.getString("start_time"));
                            System.out.println("arrival_time " + legs2.getString("arrival_time"));
                            System.out.println("legs: " + legs2.getString("legs").trim());
                            System.out.println();
                            j++;
                            legs2.next();
                        }
                    }
                    catch(SQLException e1)
                    {
                        System.out.print("no routes");
                        System.out.print(e1.toString());
                    }
                    catch(Exception e)
                    {
                        System.out.print("no routes");
                        //System.out.print(e1.toString());
                    }
                    //order by stops
                }
                if(choice2 == 4)
                {
                    ResultSet legs2 = st.executeQuery("SELECT * FROM PATHY order by stations_passed ASC ");
                    legs2.next();
                    int j = 0;
                    try
                    {
                        while(j < 10 && legs2.isAfterLast() != true)
                        {
                            System.out.println("cost: " + legs2.getInt("cost"));
                            System.out.println("stations_passed: " + legs2.getInt("stations_passed"));
                            System.out.println("stops: " + legs2.getInt("stops"));
                            System.out.println("start_time " + legs2.getString("start_time"));
                            System.out.println("arrival_time " + legs2.getString("arrival_time"));
                            System.out.println("legs: " + legs2.getString("legs").trim());
                            System.out.println();
                            j++;
                            legs2.next();
                        }
                    }
                    catch(SQLException e1)
                    {
                        System.out.println("no routes");
                        System.out.println(e1.toString());
                    }
                    catch(Exception e)
                    {
                        System.out.print("no routes");
                        //System.out.print(e1.toString());
                    }
                    //order by stations
                }
                try
                {
                    conn.setAutoCommit(false);
                    st.executeUpdate("DELETE FROM PATHY");
                    conn.commit();
                }
                catch(SQLException e)
                {
                    System.out.println(e.toString());
                }
                //compute it
                System.out.println("search again? \n 0) no  \n other yes");
                int c2 = myObj.nextInt();
                if(c2 == 0)
                {
                    return;
                }

            }
            else if(choice == 2)
            {
                System.out.println("Max number of stops (WARNING KEEP THIS VERY LOW OR IT WILL RUN FOR A LONG TIME \n WHEN SET TO 5 ON SATURDAY starting at station 1 dest station 2 it produces 1946 paths  )");
                int depth = myObj.nextInt();
                //System.out.println("routes: ");
                ResultSet legs = st.executeQuery("SELECT * FROM LEGS WHERE station_start = " + start + " AND start_day = '" + day + "' AND end_day = '" + day +"' AND available_seats > 0 AND working = 'true'");
                legs.next();
                try
                {
                    while(legs.isAfterLast() != true)
                    {
                        Time timstar = legs.getTime("departure_time");
                        int curr = legs.getInt("station_end");
                        Time arrival_time= legs.getTime("arrival_time");
                        int stops = 1;
                        int passes = legs.getInt("stations_passed");
                        int cost = legs.getInt("cost");
                        int leg_id = legs.getInt("legs_id");
                        String legy = leg_id + "";
                        //System.out.println(legy);
                        CallableStatement cstmt2 = conn.prepareCall("call multi_route('" +day+ "',"+curr+","+stops+","+cost+",'"+arrival_time+"',"+passes+","+dest+",'"+legy+"','"+timstar+"',"+depth+")");
                        //recursive_search2( day, curr, stops, cost, arrival_time, passes, dest, legy, timstar, conn, st, depth);
                        cstmt2.execute();
                        legs.next();
                    }

                }
                catch(SQLException e1)
                {
                    //System.out.print("no routes");
                    System.out.print(e1.toString());
                }
                System.out.println("ordered by? \n1) price \n2) total time\n3) stops\n4)stations");
                int choice2 = myObj.nextInt();
                if(choice2 == 1)
                {
                    ResultSet legs2 = st.executeQuery("SELECT * FROM PATHY order by cost ASC ");
                    legs2.next();
                    int j = 0;
                    try
                    {
                        while(j < 10 && legs2.isAfterLast() != true)
                        {
                            System.out.println("cost: " + legs2.getInt("cost"));
                            System.out.println("stations_passed: " + legs2.getInt("stations_passed"));
                            System.out.println("stops: " + legs2.getInt("stops"));
                            System.out.println("start_time " + legs2.getString("start_time"));
                            System.out.println("arrival_time " + legs2.getString("arrival_time"));
                            System.out.println("legs: " + legs2.getString("legs").trim());
                            System.out.println();
                            j++;
                            legs2.next();
                        }
                    }
                    catch(SQLException e1)
                    {
                        System.out.print("no routes");
                        System.out.print(e1.toString());
                    }
                    catch(Exception e)
                    {
                        System.out.print("no routes");
                        //System.out.print(e1.toString());
                    }
                    //order by price
                }
                if(choice2 == 2)
                {
                    ResultSet legs2 = st.executeQuery("SELECT * FROM PATHY order by (arrival_time - start_time) ASC ");
                    legs2.next();
                    int j = 0;
                    try
                    {
                        while(j < 10 && legs2.isAfterLast() != true)
                        {
                            
                            System.out.println("cost: " + legs2.getInt("cost"));
                            System.out.println("stations_passed: " + legs2.getInt("stations_passed"));
                            System.out.println("stops: " + legs2.getInt("stops"));
                            System.out.println("start_time " + legs2.getString("start_time"));
                            System.out.println("arrival_time " + legs2.getString("arrival_time"));
                            System.out.println("legs: " + legs2.getString("legs").trim());
                            System.out.println();
                            j++;
                            legs2.next();
                        }
                    }
                    catch(SQLException e1)
                    {
                        System.out.print("no routes");
                        System.out.print(e1.toString());
                    }
                    catch(Exception e)
                    {
                        System.out.print("no routes");
                        //System.out.print(e1.toString());
                    }
                    //just assume it always travels at max speed
                    //order by time 
                }
                if(choice2 == 3)
                {
                    ResultSet legs2 = st.executeQuery("SELECT * FROM PATHY order by stops ASC ");
                    legs2.next();
                    int j = 0;
                    try
                    {
                        while(j < 10 && legs2.isAfterLast() != true)
                        {
                            System.out.println("cost: " + legs2.getInt("cost"));
                            System.out.println("stations_passed: " + legs2.getInt("stations_passed"));
                            System.out.println("stops: " + legs2.getInt("stops"));
                            System.out.println("start_time " + legs2.getString("start_time"));
                            System.out.println("arrival_time " + legs2.getString("arrival_time"));
                            System.out.println("legs: " + legs2.getString("legs").trim());
                            System.out.println();
                            j++;
                            legs2.next();
                        }
                    }
                    catch(SQLException e1)
                    {
                        System.out.print("no routes");
                        System.out.print(e1.toString());
                    }
                    catch(Exception e)
                    {
                        System.out.print("no routes");
                        //System.out.print(e1.toString());
                    }
                    //order by stops
                }
                if(choice2 == 4)
                {
                    ResultSet legs2 = st.executeQuery("SELECT * FROM PATHY order by stations_passed ASC ");
                    legs2.next();
                    int j = 0;
                    try
                    {
                        while(j < 10 && legs2.isAfterLast() != true)
                        {
                            System.out.println("cost: " + legs2.getInt("cost"));
                            System.out.println("stations_passed: " + legs2.getInt("stations_passed"));
                            System.out.println("stops: " + legs2.getInt("stops"));
                            System.out.println("start_time " + legs2.getString("start_time"));
                            System.out.println("arrival_time " + legs2.getString("arrival_time"));
                            System.out.println("legs: " + legs2.getString("legs").trim());
                            System.out.println();
                            j++;
                            legs2.next();
                        }
                    }
                    catch(SQLException e1)
                    {
                        System.out.println("no routes");
                        System.out.println(e1.toString());
                    }
                    catch(Exception e)
                    {
                        System.out.print("no routes");
                        //System.out.print(e1.toString());
                    }
                    //order by stations
                }
                try
                {
                    conn.setAutoCommit(false);
                    st.executeUpdate("DELETE FROM PATHY");
                    conn.commit();
                }
                catch(SQLException e)
                {
                    System.out.println(e.toString());
                }
                //compute it
                System.out.println("search again? \n 0) no  \n other yes");
                int c2 = myObj.nextInt();
                if(c2 == 0)
                {
                    return;
                }
            }
            else
            {
                System.out.println("not an option");
            }
        }

    }
    public static void recursive_search(int route_id, String day, int curr, int train_id, int stops, int cost, Time arrival_time, int passes, int dest, String legy, Time start_time, Connection conn, Statement st, int depth)throws
    SQLException, ClassNotFoundException 
    {
        //System.out.println(legy);

        ResultSet next = st.executeQuery("SELECT * FROM LEGS WHERE station_start = " + curr + " AND start_day = '" + day + "' AND end_day = '" + day +"' AND available_seats > 0 AND working = 'true' AND train_id = " + train_id + " AND route_id = " + route_id );
        next.next();

        if(curr == dest)
        {
            try
            {
             conn.setAutoCommit(false);
             st.executeUpdate(String.format("INSERT INTO PATHY(cost, stops, arrival_time, stations_passed, legs, start_time) VALUES(%d, %d ,'%s', %d, '%s', '%s')", cost, stops, arrival_time, passes, legy, start_time ));
             conn.commit();
             return;
            }
            catch (SQLException e1) {
             System.out.println(e1.toString());
             try {
                 conn.rollback();
             } catch (SQLException e2) {
                 System.out.println(e2.toString());
             }
            }
        }
        if(depth < 1 )
        {
            return;
        }
        try
        {
            while(next.isAfterLast() != true)
            {
                Time departure_time = next.getTime("departure_time");
                int h1 = arrival_time.getHours();
                int h2 = departure_time.getHours();
                int m1 = arrival_time.getMinutes();
                int m2 = departure_time.getMinutes();
                int f = 0;
                if(h1 < h2)
                {
                    f = 1;
                }
                if(h1 == h2 && m1 <= m2)
                {
                    f = 1;
                }
                if(f == 1)
                {
                    try
                    {
                        System.out.println(legy);
                        recursive_search(route_id, day, next.getInt("station_end"), train_id, stops + 1, cost +next.getInt("cost") , next.getTime("arrival_time"), passes + next.getInt("stations_passed"), dest, legy + ", "+ next.getInt("legs_id"), start_time, conn, st, depth - 1);
                    }
                    catch(SQLException e)
                    {
                        System.out.println(e.toString());
                    }
                }
                next.next();
            }
        }
        catch(SQLException e)
        {
            
        }

    }
    public static void recursive_search2(String day, int curr, int stops, int cost, Time arrival_time, int passes, int dest, String legy, Time start_time, Connection conn, Statement st, int depth)throws
    SQLException, ClassNotFoundException 
    {
        //System.out.println(legy);

        ResultSet next = st.executeQuery("SELECT * FROM LEGS WHERE station_start = " + curr + " AND start_day = '" + day + "' AND end_day = '" + day + "' AND available_seats > 0 AND working = 'true'" );
        next.next();
        if(curr == dest)
        {
            try
            {
             conn.setAutoCommit(false);
             st.executeUpdate(String.format("INSERT INTO PATHY(cost, stops, arrival_time, stations_passed, legs, start_time) VALUES(%d, %d ,'%s', %d, '%s', '%s')", cost, stops, arrival_time, passes, legy, start_time ));
             conn.commit();
             return;
            }
            catch (SQLException e1) {
             System.out.println(e1.toString());
             try {
                 conn.rollback();
             } catch (SQLException e2) {
                 System.out.println(e2.toString());
             }
            }
        }
        if(depth < 1) // so it doesnt go forever
        {
            return;
        }
        try
        {
            while(next.isAfterLast() != true)
            {
                Time departure_time = next.getTime("departure_time");
                int h1 = arrival_time.getHours();
                int h2 = departure_time.getHours();
                int m1 = arrival_time.getMinutes();
                int m2 = departure_time.getMinutes();
                int f = 0;
                if(h1 < h2)
                {
                    f = 1;
                }
                if(h1 == h2 && m1 <= m2)
                {
                    f = 1;
                }
                if(f == 1)
                {
                    try
                    {
                        recursive_search2( day, next.getInt("station_end"), stops + 1, cost +next.getInt("cost") , next.getTime("arrival_time"), passes + next.getInt("stations_passed"), dest, legy + ", "+ next.getInt("legs_id"), start_time, conn, st, depth - 1);
                    }
                    catch(SQLException e)
                    {
                        System.out.println(e.toString());
                    }
                }
                next.next();
            }
        }
        catch(SQLException e)
        {
            
        }

    }

    //public static void rebook()
    public static void add_reservation()throws
    SQLException, ClassNotFoundException 
    {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/postgres";
        Properties props = new Properties();
        props.setProperty("user", "username"); //made this a superuser
        props.setProperty("password", "password"); 
        Connection conn =
                DriverManager.getConnection(url, props);
        Statement st = conn.createStatement();
        while(true)
        {
            Scanner myObj = new Scanner(System.in);

            System.out.println("id of customer being booked for:");
            int cust = myObj.nextInt();
            System.out.println("id of start station");
            int start = myObj.nextInt();
            String day = "";
            while (day.equals("Monday")  == false && day.equals("Tuesday") == false && day.equals("Wednesday") == false && day.equals("Thursday") == false && day.equals("Friday") == false && day.equals("Saturday") == false && day.equals("Sunday") == false)
            {
                System.out.println("What day (FIrst letter capital the rest lower case example 'Saturday')");
                //myObj.nextLine();
                day = myObj.nextLine();
            }
            System.out.println("id of end station");
            int end = myObj.nextInt();
            System.out.println("do you want your ticket to be auto adjusted: \n 0) no \n 1) yes");
            int adj = myObj.nextInt();
            try{
                conn.setAutoCommit(false);
                st.executeUpdate(String.format("INSERT INTO TICKET(station_start, station_end, customer_id, adj, day) VALUES( %d, %d, %d, '%d', '%s')",start,end,cust, adj, day));
                conn.commit();
                ResultSet newticket = st.executeQuery("SELECT * FROM TICKET WHERE ticket_id IN (SELECT MAX(ticket_id) FROM TICKET )");
                newticket.next();
                int ticket_id = newticket.getInt(1);
                while(true)
                {
                    System.out.println("id of leg being booked for:");
                    int leg = myObj.nextInt();
                    ResultSet legs = st.executeQuery("SELECT * FROM LEGS WHERE legs_id = " + leg );
                    legs.next();
                    try
                    {

                        String day2 = legs.getString("start_day");
                        Time time = legs.getTime("departure_time");
                        int cost = legs.getInt("cost");
                        int available = legs.getInt("available_seats");
                        boolean working = legs.getBoolean("working");
                        if(available <= 0 || working != true)
                        {
                            System.out.println("no available seats");
                            System.out.println("or line not in service");
                        }
                        else
                        {
                            String k = String.format("INSERT INTO RESERVATION(customer_id, day, time, cost, legs_id, ticket_id) VALUES (%d, '%s', '%s', %d, %d, %d);", cust, day2, time, cost, leg, ticket_id );
                            // System.out.println(k);
                            try
                            {
                                conn.setAutoCommit(false);
                                st.executeUpdate(k);
                                st.executeUpdate("UPDATE LEGS SET available_seats = available_seats - 1 WHERE legs_id = " + leg);
                                conn.commit();
                            
                            }
                            catch (SQLException e1) {
                                System.out.println(e1.toString());
                                try {
                                    conn.rollback();
                                } 
                                catch (SQLException e2) {
                                    System.out.println(e2.toString());
                                }
                            }
                        }
                    }
                    catch (SQLException e1) {
                        System.out.println(e1.toString());
                        try {
                            conn.rollback();
                        } 
                        catch (SQLException e2) {
                            System.out.println(e2.toString());
                        }
                    }
                    System.out.println("book another leg \n 0) no \n other yes");
                    int choice2 = myObj.nextInt();
                    if(choice2 == 0)
                    {
                        return;
                    }
                }

            }
            catch (SQLException e1) {
                System.out.println(e1.toString());
                try {
                    conn.rollback();
                } 
                catch (SQLException e2) {
                    System.out.println(e2.toString());
                }
            }
            


            
        }
    }
    public static void get_ticket()throws
    SQLException, ClassNotFoundException 
    {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/postgres";
        Properties props = new Properties();
        props.setProperty("user", "username"); //made this a superuser
        props.setProperty("password", "password"); 
        Connection conn =
                DriverManager.getConnection(url, props);
        Statement st = conn.createStatement();
        while(true)
        {
            Scanner myObj = new Scanner(System.in);
            System.out.println("What ticket is being ticketed");
            int res_id = myObj.nextInt();
            try
            {
                conn.setAutoCommit(false);
                st.executeUpdate("UPDATE TICKET SET payed = true WHERE ticket_id = " + res_id);
                conn.commit();
                st.executeUpdate("UPDATE RESERVATION SET ticketed = true WHERE ticket_id = " + res_id);
                conn.commit();
            }
            catch (SQLException e1) {
                System.out.println(e1.toString());
                try {
                    conn.rollback();
                } 
                catch (SQLException e2) {
                    System.out.println(e2.toString());
                }
            }
            System.out.println("ticket again? \n 0)no \n other) yes");
            int choice = myObj.nextInt();
            if(choice == 0)
            {
                return;
            }

            
        }

        // just change the ticketed value from false to true for a given reservation id
    }
    //
    public static void advanced_search()throws
    SQLException, ClassNotFoundException 
    {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/postgres";
        Properties props = new Properties();
        props.setProperty("user", "username"); //made this a superuser
        props.setProperty("password", "password"); 
        Connection conn =
                DriverManager.getConnection(url, props);
        Statement st = conn.createStatement();
        while(true)
        {
            
            System.out.println("Search for? \n0) back \n1)  Find all trains that pass through a specific station at a specific day/time \n2)  Find the routes that travel more than one rail line\n3)  Rank the trains that are scheduled for more than one route.\n4) Find routes that pass through the same stations but do not have the same stops (WARNING TAKES A LONG TIME) \n5)  Find any stations through which all trains pass through \n6)  Find all the trains that do not stop at a specific station\n7)  Find routes that stop at least at XX% of the Stations they visit:\n8)  Display the schedule of a route\n9)  Find the availability of a route at every stop on a specific day and time");
            Scanner myObj = new Scanner(System.in);
            int choice = myObj.nextInt();
            if(choice == 0)
            {
                return;
            }
            else if (choice == 1)
            {

                System.out.println("what station");
                int station = myObj.nextInt();
                //System.out.println("what day");
                String day = "";
                while (day.equals("Monday")  == false && day.equals("Tuesday") == false && day.equals("Wednesday") == false && day.equals("Thursday") == false && day.equals("Friday") == false && day.equals("Saturday") == false && day.equals("Sunday") == false)
                {
                    System.out.println("What day (FIrst letter capital the rest lower case example 'Saturday')");
                    //myObj.nextLine();
                    day = myObj.nextLine();
                }
                System.out.println("what hour 0-23");
                int time = myObj.nextInt();
                ResultSet trains = st.executeQuery("SELECT * FROM LEGS WHERE start_day = '" + day +"' AND station_start = " + station );
                int i = 0;
                trains.next();
                try
                {
                    System.out.println("the list of trains is");
                    while(trains.isAfterLast() != true)
                    {   
                        Time departure_time = trains.getTime("departure_time");
                        int w = departure_time.getHours();
                        CallableStatement cstmt = conn.prepareCall("{? = call int_equal("+w+","+time+")}");
                        cstmt.registerOutParameter(1, Types.BOOLEAN);
                        //cstmt.setInt(1, (int)percentage);
                        //cstmt.setInt(2, percent);
                        cstmt.execute();
                        if(cstmt.getBoolean(1))
                        {
                            System.out.println(trains.getInt("train_id"));
                        }
                        trains.next();
                    }

                }
                catch(SQLException e1)
                {
                    System.out.println("no trains");
                    System.out.print(e1.toString());
                }
                //String k = String.format("", )
                //%d 
                // ask for day then time
                // write code to Find all trains that pass through a specific station at a specific day/time combination
            }
            else if (choice == 2)
            {
                
                //Find the routes that travel more than one rail line\
                ResultSet multi_rail = st.executeQuery("SELECT route_id, COUNT(DISTINCT line_id) from STATION_ROUTE_RELATION GROUP BY route_id");
                multi_rail.next();
                try
                {
                    System.out.println("routes:");
                    while(multi_rail.isAfterLast() != true)
                    {
                        int a = multi_rail.getInt(2);
                        CallableStatement cstmt = conn.prepareCall("{? = call greater_than("+a+","+1+")}");
                        cstmt.registerOutParameter(1, Types.BOOLEAN);
                        //cstmt.setInt(1, (int)percentage);
                        //cstmt.setInt(2, percent);
                        cstmt.execute();
                        if(cstmt.getBoolean(1))
                        {
                            System.out.println(multi_rail.getInt(1));
                        }
                        multi_rail.next();
                    }
                }
                catch(SQLException e)
                {
                    System.out.print(e.toString());
                }
            }
            else if (choice == 3)
            {

                //Rank the trains that are scheduled for more than one route.
                ResultSet rank = st.executeQuery("SELECT train_id, count(route_id), rank() OVER (ORDER BY COUNT(route_id)  DESC) FROM train_schedule GROUP BY train_id");
                rank.next();
                try
                {
                    System.out.println("the ranks are");
                    while(rank.isAfterLast() != true)
                    {   
                        System.out.println("train id = " + rank.getInt(1) + " count: "+ rank.getInt(2)+  " rank: " + rank.getInt(3));
                        rank.next();
                    }

                }
                catch(SQLException e1)
                {
                    //System.out.println("no trains");
                    System.out.print(e1.toString());
                }
                //System.out.println("add reservation");
                //call to passenger service operator code
            }
            else if (choice == 4)
            {
                System.out.println("matches:");
                try
                {
                    ResultSet routes = st.executeQuery("SELECT * FROM ROUTE ORDER BY route_id ASC");
                    routes.next();
                    while(routes.isAfterLast() != true)
                    {
                        int rid = routes.getInt("route_id");
                        ResultSet routes2 = st.executeQuery("SELECT * FROM ROUTE WHERE route_id !=" + rid); //doing this will make it so we never repeat a comparison
                        routes2.next();
                        ResultSet c1 = st.executeQuery("SELECT COUNT(*) FROM (SELECT DISTINCT station_num FROM STATION_ROUTE_RELATION WHERE route_id = " + rid + " ORDER BY station_num) as a");
                        ResultSet c3 = st.executeQuery("SELECT COUNT(*) FROM (SELECT DISTINCT station_num FROM STATION_ROUTE_RELATION WHERE route_id = " + rid + " AND stop = 'true' ORDER BY station_num) as c");
                        c1.next();
                        c3.next();
                        String f = "route " + rid + " matches with:";
                        while(routes2.isAfterLast() != true)
                        {
                            int rid2 = routes2.getInt("route_id");
                            try
                            {
 
                                ResultSet c5 = st.executeQuery("SELECT COUNT(*) FROM (SELECT DISTINCT station_num FROM STATION_ROUTE_RELATION WHERE route_id = " + rid2 + " ORDER BY station_num) as e");
                                ResultSet c6 = st.executeQuery("SELECT COUNT(*) FROM (SELECT DISTINCT station_num FROM STATION_ROUTE_RELATION WHERE route_id = " + rid2 + " AND stop = 'true' ORDER BY station_num) as f");
                                //ResultSet c1 = st.executeQuery("SELECT COUNT(*) FROM (SELECT station_num FROM STATION_ROUTE_RELATION WHERE route_id = " + rid + ") as a");
                                ResultSet c2 = st.executeQuery("SELECT COUNT(*) FROM ((SELECT DISTINCT station_num FROM STATION_ROUTE_RELATION WHERE route_id = " + rid + " ORDER BY station_num) UNION (SELECT DISTINCT station_num FROM STATION_ROUTE_RELATION WHERE route_id = " + rid2 + " ORDER BY station_num)) AS b");
                                //ResultSet c3 = st.executeQuery("SELECT COUNT(*) FROM (SELECT station_num FROM STATION_ROUTE_RELATION WHERE route_id = " + rid + " AND stop = 'true' ) as c");
                                ResultSet c4 = st.executeQuery("SELECT COUNT(*) FROM ((SELECT DISTINCT station_num FROM STATION_ROUTE_RELATION WHERE route_id = " + rid + " AND stop = 'true' ORDER BY station_num ) UNION (SELECT DISTINCT station_num FROM STATION_ROUTE_RELATION WHERE route_id = " + rid2 + " AND stop = 'true' ORDER BY station_num)) AS d");
                                //c1.next();
                                c2.next();
                               // c3.next();
                                c4.next();
                                c5.next();
                                c6.next();
                                int a1 = c1.getInt(1);
                                int a2 = c2.getInt(1);
                                int a3 = c3.getInt(1);
                                int a4 = c4.getInt(1);
                                int a5 = c5.getInt(1);
                                int a6 = c6.getInt(1);
                                try
                                {
                                    
                                    CallableStatement cstmt = conn.prepareCall("{? = call int_equal("+a1+","+a2+")}");
                                    cstmt.registerOutParameter(1, Types.BOOLEAN);
                                    //cstmt.setInt(1, a1);
                                    //cstmt.setInt(2, a2);
                                    cstmt.execute();
                                    if(cstmt.getBoolean(1))
                                    {
                                        CallableStatement cstmt2 = conn.prepareCall("{? = call int_equal("+a5+","+a2+")}");
                                        cstmt2.registerOutParameter(1, Types.BOOLEAN);
                                        //cstmt2.setInt(1, a5);
                                        //cstmt2.setInt(2, a2);
                                        cstmt2.execute();
                                        if(cstmt2.getBoolean(1))
                                        {

                                        
                                            CallableStatement cstmt3 = conn.prepareCall("{? = call int_equal("+a3+","+a4+")}");
                                            cstmt3.registerOutParameter(1, Types.BOOLEAN);
                                            //cstmt3.setInt(1, a3);
                                            //cstmt3.setInt(2, a4);
                                            cstmt3.execute();
                                            if(cstmt3.getBoolean(1) == false)
                                            {
                                                CallableStatement cstmt4 = conn.prepareCall("{? = call int_equal("+a6+","+a4+")}");
                                                cstmt4.registerOutParameter(1, Types.BOOLEAN);
                                                //cstmt4.setInt(1, a6);
                                                //cstmt4.setInt(2, a4);
                                                cstmt4.execute();
                                                if(cstmt4.getBoolean(1) == false)
                                                {
                                                    f = f + " " + rid2;
                                                }
                                            }
                                        }
                                    }
                                    //cstmt4.execute();
                                    //System.out.println(f)

                                    //if(cstmt.getBoolean(1) == true && cstmt2.getBoolean(1) == true &&cstmt3.getBoolean(1) != true && cstmt4.getBoolean(1) != true)
                                    /*
                                    if(c1.getInt(1) == c2.getInt(1) && c3.getInt(1) != c4.getInt(1) && c5.getInt(1) == c2.getInt(1) && c6.getInt(1) != c4.getInt(1) )
                                    {

                                        f = f + " " + rid2;
                                    }
                                    */
                                }
                                catch(SQLException e)
                                {

                                }


                            }
                            catch (SQLException e1) {
                                System.out.println(e1.toString());
                            }
                            routes2.next();
                        }
                        System.out.println(f);
                        routes.next();
                    }
                }
                catch (SQLException e1) {
                    System.out.println(e1.toString());
                    try {
                        conn.rollback();
                    } 
                    catch (SQLException e2) {
                        System.out.println(e2.toString());
                    }
                }

                //Find routes that pass through the same stations but don’t have the same stops
                //System.out.println("get ticket");
                //call to passenger service operator code
            }
            else if (choice == 5)
            {
                try
                {
                    System.out.println("Stations every train passes through");
                    ResultSet stations = st.executeQuery("SELECT DISTINCT station_num FROM STATION ORDER BY station_num");
                    ResultSet train_count = st.executeQuery("SELECT COUNT( DISTINCT train_id) FROM TRAIN");
                    train_count.next();
                    stations.next();
                    
                    try
                    {
                        int tc = train_count.getInt(1);
                        while(stations.isAfterLast() != true)
                        {
                            int sid = stations.getInt("station_num");
                            ResultSet temp = st.executeQuery("SELECT COUNT(DISTINCT train_id) FROM TRAIN_SCHEDULE WHERE route_id in (SELECT route_id FROM STATION_ROUTE_RELATION WHERE station_num = "+sid+" )");
                            temp.next();
                            try {
                                CallableStatement cstmt = conn.prepareCall("{? = call int_equal("+temp.getInt(1)+","+tc+")}");
                                cstmt.registerOutParameter(1, Types.BOOLEAN);
                                //cstmt.setInt(1, (int)percentage);
                                //cstmt.setInt(2, percent);
                                cstmt.execute();
                                if(cstmt.getBoolean(1))
                                {
                                    System.out.println(sid);
                                }
                            } 
                            catch (SQLException e) {
                                //TODO: handle exception
                            }
    
                            stations.next();
                        }
                    }
                    catch(SQLException e)
                    {
                        System.out.println(e.toString());
                    }
                }
                catch(SQLException e)
                {
                    System.out.println(e.toString());
                }

                //Find any stations through which all trains pass through
                //call to passenger service operator code
            }
            else if (choice == 6)
            {
                System.out.println("What station:");
                int station = myObj.nextInt();   
                ResultSet train = st.executeQuery(String.format("SELECT train_id from TRAIN WHERE train_id not in (SELECT train_id FROM LEGS WHERE station_start = %d) AND train_id not in (SELECT train_id FROM LEGS WHERE station_end = %d)",station, station));
                train.next();
                try
                {
                    System.out.println("trains:");
                    while(train.isAfterLast() != true)
                    {
                        System.out.println(train.getInt(1));
                        train.next();
                    }
                }
                catch(SQLException e)
                {
                    System.out.println(e.toString());
                }
                //Find all the trains that do not stop at a specific station
                //System.out.println("advanced search");
                //call to passenger service operator code
            }
            else if (choice == 7)
            {
                System.out.println("What percent (in form 12 = 12%)");
                int percent = myObj.nextInt();
                myObj.nextLine();
                ResultSet perc = st.executeQuery("SELECT route_id, count(CASE WHEN stop = 'true' THEN 1 END), count(DISTINCT station_num) FROM STATION_ROUTE_RELATION GROUP BY route_id ORDER BY route_id");
                perc.next();
                try
                {
                    while(perc.isAfterLast() != true)
                    {
                        double tcount = perc.getInt(2);
                        double scount = perc.getInt(3);
                        double percentage = tcount / scount;
                        percentage = percentage * 100;
                        CallableStatement cstmt = conn.prepareCall("{? = call greater_than("+(int) percentage+","+percent+")}");
                        cstmt.registerOutParameter(1, Types.BOOLEAN);
                        //cstmt.setInt(1, (int)percentage);
                        //cstmt.setInt(2, percent);
                        cstmt.execute();
                        if(cstmt.getBoolean(1))
                        {
                            System.out.println("route_id = "  + perc.getInt(1) + " stopping " + perc.getInt(2) + " times at "  + perc.getInt(3) + "stations or " + percentage +"% of the time");
                        }
                        perc.next();
                    }
                }
                catch(SQLException e)
                {
                    System.out.println(e.toString());
                }
                //Find routes that stop at least at XX% of the Stations they visit:
                
                //System.out.println("advanced search");
                //call to passenger service operator code
            }
            else if (choice == 8)
            {
                System.out.println("What is the route_id");
                int rid = myObj.nextInt();
                ResultSet legs = st.executeQuery("SELECT * FROM LEGS WHERE route_id = " + rid);
                legs.next();
                try
                {
                    while(legs.isAfterLast() != true)
                    {
                        System.out.println("Train " + legs.getInt("train_id") + " following route " + rid + " leaves station " + legs.getInt("station_start") + " at " + legs.getTime("departure_time") + " on " + legs.getString("start_day").trim() + " and arrives at station " + legs.getInt("station_end") + " at " + legs.getTime("arrival_time") + " on " + legs.getString("end_day").trim());
                        legs.next();
                    }
                }
                catch(SQLException e)
                {
                    System.out.println(e.toString());
                }
                //Display the schedule of a route:
                //System.out.println("advanced search");
                //call to passenger service operator code
            }
            else if (choice == 9)
            {
                //Find the availability of a route at every stop on a specific day and time6

                System.out.println("What is the route id");
                int rid = myObj.nextInt();
                String day = "";
                while (day.equals("Monday")  == false && day.equals("Tuesday") == false && day.equals("Wednesday") == false && day.equals("Thursday") == false && day.equals("Friday") == false && day.equals("Saturday") == false && day.equals("Sunday") == false)
                {
                    System.out.println("What day (FIrst letter capital the rest lower case example 'Saturday')");
                    //myObj.nextLine();
                    day = myObj.nextLine();
                }
                System.out.println("What is the time (0-23)");
                int tim = myObj.nextInt();
                ResultSet avail = st.executeQuery("SELECT * FROM LEGS WHERE route_id = " + rid + " AND start_day = '" + day +"' AND working = 'true' AND available_seats > 0");
                avail.next();
                System.out.println("available at: ");
                try
                {
                    while(avail.isAfterLast() != true)
                    {
                        Time depart_time = avail.getTime("departure_time");
                        int h = depart_time.getHours();
                        //CallableStatement cstmt = conn.prepareCall("{? = call int_equal(?,?)}");
                        //cstmt.registerOutParameter(1, Types.BOOLEAN);
                        //cstmt.setInt(1, tim);
                        //cstmt.setInt(2, h);
                        //cstmt.execute();
                        if(tim == h)
                        {
                            System.out.println(avail.getInt("station_start"));
                        }
                        avail.next();
                    }
                }
                catch(SQLException e)
                {
                    System.out.println(e.toString());
                }
                //call to passenger service operator code
            }
            else
            {
                System.out.println("not an option");
            }
            System.out.println("");

        }
    }
    //import the data
    public static void import_data()throws
    SQLException, ClassNotFoundException 
    {

        while(true)
        {
            Scanner myObj = new Scanner(System.in);
            System.out.println("where are you inputting this file? \n 0) back \n 1)stations \n 2) rail_lines \n 3) trains \n 4) routes \n 5)route_schedule \n 6)customers \n 7) build legs");
            int choice = myObj.nextInt();
            if(choice == 0)
            {
                return;
            }
            else if (choice == 2)
            {
                import_rail_lines();
            }
            else if (choice == 4)
            {
                import_routes();
            }
            else if (choice == 5)
            {
                import_route_schedule();
            }
            else if (choice == 1)
            {
                import_stations();
            }
            else if (choice == 3)
            {
                import_trains();
            }
            else if (choice == 6)
            {
                import_customers();
            }
            else if (choice == 7)
            {
                build_legs();
            }
            else
            {
                System.out.println("not an option");
            }
            System.out.println("");


        }
    }
    public static void import_rail_lines()throws
    SQLException, ClassNotFoundException 
    {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/postgres";
        Properties props = new Properties();
        props.setProperty("user", "username"); //made this a superuser
        props.setProperty("password", "password"); 
        Connection conn =
                DriverManager.getConnection(url, props);
        Statement st = conn.createStatement();
        Scanner myObj = new Scanner(System.in);
        System.out.println("whats the file name");
        String fname = myObj.nextLine();
        try(BufferedReader br = new BufferedReader(new FileReader(fname))) {
            String line;
            //System.out.println(line);
            while ((line = br.readLine()) != null)
            {
                //System.out.println(line);
                String[] u = line.split("Distances:");
                String[] distances = u[1].split(",");
                //System.out.println(distances);
                String[] v = u[0].split("Stations:");
                //System.out.println(v[0]);
                //System.out.println(v[1]);
                String[] stations = v[1].split(",");
                String[] w = v[0].split("Speed Limit:");
                //System.out.println(w[0]);
                //System.out.println(w[1]);
                String[] x = w[0].split("Line ID:");
                //System.out.println(x[1]);
                //List<String> stoplist = new ArrayList<>(Arrays.asList(stops));
                for(int i = 0; i < stations.length; i++)
                {
                    if(i == 0)
                    {
                        //System.out.println("first");
                        String k = String.format("INSERT INTO RAIL_LINES(line_id, speed_limit, station_num, distance_prev, distance_next, next_station) VALUES (%s, %s, %s, %s, %s, %s)", x[1],w[1],stations[i],distances[i],distances[i+1],stations[i+1]);
                        //System.out.println(k);
                        try
                        {
                         conn.setAutoCommit(false);
                         st.executeUpdate(k);
                         conn.commit();
                        }
                        catch (SQLException e1) {
                            System.out.println(e1.toString());
                         try {
                             conn.rollback();
                         } catch (SQLException e2) {
                             System.out.println(e2.toString());
                         }
                     }
                    }
                    else if(i == (stations.length - 1))
                    {
                        //System.out.println("last");
                        String k = String.format("INSERT INTO RAIL_LINES(line_id,speed_limit,station_num,distance_prev, prev_station, distance_next) VALUES (%s, %s, %s, %s, %s, 0)", x[1],w[1],stations[i],distances[i],stations[i-1]);
                        //System.out.println(k);
                        try
                        {
                         conn.setAutoCommit(false);
                         st.executeUpdate(k);
                         conn.commit();
                        }
                        catch (SQLException e1) {
                            System.out.println(e1.toString());
                         try {
                             conn.rollback();
                         } catch (SQLException e2) {
                             System.out.println(e2.toString());
                         }
                     }
                    }
                    else
                    {
                        //System.out.println("other");
                        String k = String.format("INSERT INTO RAIL_LINES(line_id,speed_limit,station_num,distance_prev, prev_station, distance_next, next_station) VALUES (%s, %s, %s, %s, %s, %s, %s)", x[1],w[1],stations[i],distances[i],stations[i-1],distances[i+1],stations[i+1]);
                        //System.out.println(k);
                        try
                        {
                         conn.setAutoCommit(false);
                         st.executeUpdate(k);
                         conn.commit();
                        }
                        catch (SQLException e1) {
                            System.out.println(e1.toString());
                            try {
                             conn.rollback();
                            } 
                            catch (SQLException e2) {
                             System.out.println(e2.toString());
                            }
                        }
                    }

                }
            }
        }  catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }

    public static void import_routes()throws
    SQLException, ClassNotFoundException 
    {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/postgres";
        Properties props = new Properties();
        props.setProperty("user", "username"); //made this a superuser
        props.setProperty("password", "password"); 
        Connection conn =
                DriverManager.getConnection(url, props);
        Statement st = conn.createStatement();
        Scanner myObj = new Scanner(System.in);
        System.out.println("whats the file name");
        String fname = myObj.nextLine();
        try(BufferedReader br = new BufferedReader(new FileReader(fname))) {
            String line;
            //System.out.println(line);
            while ((line = br.readLine()) != null)
            {
                //System.out.println(line);
                String[] v = line.split("Stops:");
                //System.out.println(v[0]);
                //System.out.println(v[1]);
                String[] stops = v[1].split(",");
                String[] w = v[0].split("Stations:");
                //System.out.println(w[0]);
                //System.out.println(w[1]);
                String[] stations = w[1].split(",");
                String[] x = w[0].split("Route:");
                //System.out.println(x[1]);
                String a = String.format("INSERT INTO ROUTE(route_id) VALUES (%s)", x[1]);
                try
                {
                 conn.setAutoCommit(false);
                 st.executeUpdate(a);
                 conn.commit();
                }
                catch (SQLException e1) {
                    System.out.println(e1.toString());
                 try {
                     conn.rollback();
                 } catch (SQLException e2) {
                     System.out.println(e2.toString());
                 }
                }
                //List<String> stoplist = new ArrayList<>(Arrays.asList(stops));
                int order = 0;
                for(int i = 0; i < stations.length; i++)
                {
                    if(i == stations.length - 1)
                    {
                        String k = String.format("INSERT INTO STATION_ROUTE_RELATION(route_id,station_num,stop,station_order, station_next, distance, speed_limit ) VALUES (%s, %s, TRUE, %d, 0, 0, 0)", x[1],stations[i],order);
                        for(int j = 0; j < stops.length; j++)
                        {
                            if(stops[j].equals(stations[i]))
                            {
                                k = String.format("INSERT INTO STATION_ROUTE_RELATION(route_id,station_num,stop,station_order,station_next, distance, speed_limit) VALUES (%s, %s, TRUE, %d, 0, 0, 0)", x[1],stations[i],order);
                            }
                        }
                        if(i == (stations.length - 1)) // a bit of trouble with the last one 
                        {
                            k = String.format("INSERT INTO STATION_ROUTE_RELATION(route_id,station_num,stop,station_order,station_next, distance, speed_limit) VALUES (%s, %s, TRUE, %d, 0, 0, 0)", x[1],stations[i],order);
                        }
                        try
                        {
                         conn.setAutoCommit(false);
                         st.executeUpdate(k);
                         conn.commit();
                        }
                        catch (SQLException e1) {
                            System.out.println(e1.toString());
                         try {
                             conn.rollback();
                         } catch (SQLException e2) {
                             System.out.println(e2.toString());
                         }
                     }
                    }
                    else
                    {
                        int q = i;
                        int speed = 0;
                        int dist = 0;
                        int line_id = 0;
                        try
                        {
                            ResultSet rail = st.executeQuery("SELECT * FROM RAIL_LINES WHERE station_num = " + stations[i] + "  AND next_station = " + stations[i + 1] );

                            rail.next();
                             speed = rail.getInt("speed_limit");
                             dist = rail.getInt ("distance_next");
                             line_id = rail.getInt("line_id");

                        }
                        catch(SQLException e1)
                        {
                            try
                            {
                                ResultSet rail = st.executeQuery("SELECT * FROM RAIL_LINES WHERE station_num = " + stations[i+1] + "  AND next_station = " + stations[i] );
                                rail.next();
                                speed = rail.getInt("speed_limit");
                                dist = rail.getInt ("distance_next");
                                line_id = rail.getInt("line_id");

                            }
                            catch(SQLException e2)
                            {
                                // so apparently it double back 
                                int z = 1;
                                //int q = i;
                                while(z == 1 && q >= 0)
                                {
                                    try
                                    {
                                        ResultSet rail = st.executeQuery("SELECT * FROM RAIL_LINES WHERE station_num = " + stations[q-1] + "  AND next_station = " + stations[i+1] );
                                        rail.next();
                                        speed = rail.getInt("speed_limit");
                                        dist = rail.getInt ("distance_next");
                                        line_id = rail.getInt("line_id");
                                        z = 0;
                                        order++;

                                    }
                                    catch(SQLException e3)
                                    {
                                        try
                                        {
                                            ResultSet rail = st.executeQuery("SELECT * FROM RAIL_LINES WHERE station_num = " + stations[i+1] + "  AND next_station = " + stations[q-1] );
                                            rail.next();
                                            speed = rail.getInt("speed_limit");
                                            dist = rail.getInt ("distance_next");
                                            line_id = rail.getInt("line_id");
                                            z = 0;
                                            order++;
                                        }
                                        catch(SQLException e4)
                                        {
                                            //System.out.println(e4.toString());
                                        }

                                    }
                                    if(z == 1)
                                    {
                                        try
                                    {
                                        ResultSet rail = st.executeQuery("SELECT * FROM RAIL_LINES WHERE station_num = " + stations[q] + "  AND next_station = " + stations[q-1] );
                                        rail.next();
                                        speed = rail.getInt("speed_limit");
                                        dist = rail.getInt ("distance_next");
                                        line_id = rail.getInt("line_id");
                                    }
                                    catch(SQLException e3)
                                    {
                                        ResultSet rail = st.executeQuery("SELECT * FROM RAIL_LINES WHERE station_num = " + stations[q-1] + "  AND next_station = " + stations[q] );
                                        rail.next();
                                        speed = rail.getInt("speed_limit");
                                        dist = rail.getInt ("distance_next");
                                        line_id = rail.getInt("line_id");
                                    }
                                    String b = String.format("INSERT INTO STATION_ROUTE_RELATION(route_id,station_num,stop,station_order,station_next, distance, speed_limit, line_id) VALUES (%s, %s, FALSE, %d, %s, %d, %d, %d )", x[1],stations[q],order, stations[q-1], dist, speed, line_id);
                                    try
                                    {
                                     conn.setAutoCommit(false);
                                     st.executeUpdate(b);
                                     conn.commit();
                                    }
                                    catch (SQLException e3) {
                                        System.out.println(e3.toString());
                                     try {
                                         conn.rollback();
                                     } catch (SQLException e4) {
                                         System.out.println(e4.toString());
                                     }
                                    }

                                    q--;
                                    order++;
                                    }
                                    
                                }
                                
                            }
                        }
                        //System.out.println("check speed:" + speed + " dist: " + dist + );
                        String k = String.format("INSERT INTO STATION_ROUTE_RELATION(route_id,station_num,stop,station_order,station_next, distance, speed_limit, line_id) VALUES (%s, %s, FALSE, %d, %s, %d, %d, %d )", x[1],stations[q],order, stations[i+1], dist, speed, line_id);
                        for(int j = 0; j < stops.length; j++)
                        {
                            if(stops[j].equals(stations[q]))
                            {
                                k = String.format("INSERT INTO STATION_ROUTE_RELATION(route_id,station_num,stop,station_order,station_next, distance, speed_limit, line_id) VALUES (%s, %s, TRUE, %d, %s, %d, %d, %d )", x[1],stations[q],order, stations[i+1], dist, speed, line_id);
                            }
                        }
                        if(q == (stations.length - 1)) // a bit of trouble with the last one 
                        {
                            k = String.format("INSERT INTO STATION_ROUTE_RELATION(route_id,station_num,stop,station_order) VALUES (%s, %s, TRUE, %d, 0, 0)", x[1],stations[q],order);
                        }
                        
                        try
                        {
                         conn.setAutoCommit(false);
                         st.executeUpdate(k);
                         conn.commit();
                        }
                        catch (SQLException e1) {
                            System.out.println(e1.toString());
                         try {
                             conn.rollback();
                         } catch (SQLException e2) {
                             System.out.println(e2.toString());
                         }

                        }


                    }
                    order++;
                }
            }
        } catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }


    public static void import_route_schedule()throws
    SQLException, ClassNotFoundException 
    {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/postgres";
        Properties props = new Properties();
        props.setProperty("user", "username"); //made this a superuser
        props.setProperty("password", "password"); 
        Connection conn =
                DriverManager.getConnection(url, props);
        Statement st = conn.createStatement();
        Scanner myObj = new Scanner(System.in);
        System.out.println("whats the file name");
        String fname = myObj.nextLine();
        try(BufferedReader br = new BufferedReader(new FileReader(fname))) {
            String line;
            //System.out.println(line);
            while ((line = br.readLine()) != null)
            {
                //System.out.println(line);
                String[] v = line.split(";");
                String k = String.format("INSERT INTO TRAIN_SCHEDULE(route_id, day, time, train_id) VALUES ( %s, '%s', '%s', %s);", v[0],v[1],v[2],v[3] );
               // System.out.println(k);
               try
               {
                conn.setAutoCommit(false);
                st.executeUpdate(k);
                conn.commit();
               }
               catch (SQLException e1) {
                   System.out.println(e1.toString());
                try {
                    conn.rollback();
                } catch (SQLException e2) {
                    System.out.println(e2.toString());
                    }
                }

                //System.out.println(k);
            }
        }  catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }


    public static void import_stations() throws
    SQLException, ClassNotFoundException 
    {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/postgres";
        Properties props = new Properties();
        props.setProperty("user", "username"); //made this a superuser
        props.setProperty("password", "password"); 
        Connection conn =
                DriverManager.getConnection(url, props);
        Statement st = conn.createStatement();
        Scanner myObj = new Scanner(System.in);
        System.out.println("whats the file name");
        String fname = myObj.nextLine();
        try(BufferedReader br = new BufferedReader(new FileReader(fname))) {
            String line;
            //System.out.println(line);
            while ((line = br.readLine()) != null)
            {
                //System.out.println(line);
                String[] v = line.split(";");
                String k = String.format("INSERT INTO STATION(station_num, name, opening_time, closing_time, stop_delay, street, town, postal_code ) VALUES (%s, '%s', '%s', '%s', %s,'%s','%s','%s');", v[0],v[1],v[2],v[3],v[4],v[5],v[6],v[7]  );
               // System.out.println(k);
               try
               {
                conn.setAutoCommit(false);
                st.executeUpdate(k);
                conn.commit();
               }
               catch (SQLException e1) {
                System.out.println(e1.toString());
                try {
                    conn.rollback();
                } catch (SQLException e2) {
                    System.out.println(e2.toString());
                }
            }
                //System.out.println(k);
            }
        } catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }

    public static void import_trains()throws
    SQLException, ClassNotFoundException 
    {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/postgres";
        Properties props = new Properties();
        props.setProperty("user", "username"); //made this a superuser
        props.setProperty("password", "password"); 
        Connection conn =
                DriverManager.getConnection(url, props);
        Statement st = conn.createStatement();
        Scanner myObj = new Scanner(System.in);
        System.out.println("whats the file name");
        String fname = myObj.nextLine();
        //parse trains
        try(BufferedReader br = new BufferedReader(new FileReader(fname))) {
            String line;
            //System.out.println(line);
            while ((line = br.readLine()) != null)
            {
                //System.out.println(line);
                String[] v = line.split(";");
                String k = String.format("INSERT INTO TRAIN(train_id, train_name, description, seats, top_speed, price_per_km ) VALUES (%s, '%s', '%s', %s, %s , %s);", v[0],v[1],v[2],v[3],v[4],v[5] );
               // System.out.println(k);
                try
                {
                    conn.setAutoCommit(false);
                    st.executeUpdate(k);
                    conn.commit();
                }
                catch (SQLException e1) {
                    System.out.println(e1.toString());
                    try {
                        conn.rollback();
                    } 
                    catch (SQLException e2) {
                        System.out.println(e2.toString());
                    }
                }
                //System.out.println(k);
            }
        }catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }

    public static void import_customers()throws
    SQLException, ClassNotFoundException 
    {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/postgres";
        Properties props = new Properties();
        props.setProperty("user", "username"); //made this a superuser
        props.setProperty("password", "password"); 
        Connection conn =
                DriverManager.getConnection(url, props);
        Statement st = conn.createStatement();
        Scanner myObj = new Scanner(System.in);
        System.out.println("whats the file name");
        String fname = myObj.nextLine();
        try(BufferedReader br = new BufferedReader(new FileReader(fname))) {
            String line;
            //System.out.println(line);
            while ((line = br.readLine()) != null)
            {
                //System.out.println(line);
                String[] v = line.split(";");
                String k = String.format("INSERT INTO PASSENGERS(customers_id, fname, lname, street, town, postal_code ) VALUES (%s, '%s', '%s', '%s', '%s','%s');", v[0],v[1],v[2],v[3],v[4],v[5] );
               // System.out.println(k);
               try
               {
                conn.setAutoCommit(false);
                st.executeUpdate(k);
                conn.commit();
               }
               catch (SQLException e1) {
                System.out.println(e1.toString());
                try {
                    conn.rollback();
                } catch (SQLException e2) {
                    System.out.println(e2.toString());
                }
            }
                //System.out.println(k);
            }
        } 
         catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }


    public static void build_legs()throws
    SQLException, ClassNotFoundException
    {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/postgres";
        Properties props = new Properties();
        props.setProperty("user", "username"); //made this a superuser
        props.setProperty("password", "password"); 
        Connection conn =
                DriverManager.getConnection(url, props);
        Statement st = conn.createStatement();
        ResultSet sched = st.executeQuery("SELECT * FROM TRAIN_SCHEDULE");
  //it should now point to the first row
        while(sched.next())
        {
            int rid = sched.getInt("route_id");
            int tid = sched.getInt("train_id");
            String day = sched.getString("day");
            day = day.trim();
            String day2 = day;
            Time time = sched.getTime("time");
            ResultSet route = st.executeQuery("SELECT * FROM STATION_ROUTE_RELATION WHERE route_id = " + rid + "ORDER BY station_order ASC;");
            ResultSet train = st.executeQuery("SELECT * FROM TRAIN WHERE train_id = " + tid + ";" );
            
            train.next();
            int top_speed = train.getInt("top_speed");
            int available_seats = train.getInt("seats");
            int cost_per_km = train.getInt("price_per_km");
            Time temp_start = time;
            route.next();
            while(route.isAfterLast() != true && route.getInt("speed_limit") != 0)
            {
                int start  = route.getInt("station_num");
                int i = 1; // i is stations
                //int speedlim = route.getInt("speed_limit");
                int speedlim = route.getInt("speed_limit");
                if(speedlim > top_speed)
                {
                    speedlim = top_speed;
                }
                int dist = route.getInt("distance");
                int travel_time = (int)(((double)dist/speedlim) * 60);
                int hour = temp_start.getHours();
                int minute = temp_start.getMinutes();
                ResultSet route2 = st.executeQuery("SELECT * FROM STATION_ROUTE_RELATION WHERE route_id = " + rid + " AND station_order >"+route.getInt("station_order") +"ORDER BY station_order ASC;");
                route2.next();
                while(route2.isAfterLast() != true)
                {
                    boolean stop = route2.getBoolean("stop");
                    if(stop)
                    {
                        break;
                    }
                    speedlim = route2.getInt("speed_limit");
                    if(speedlim > top_speed)
                    {
                        speedlim = top_speed;
                    }
                    dist = dist + route2.getInt("distance");
                    travel_time = travel_time + (int)(((double)dist/speedlim) * 60);
                    i++;
                    route2.next();
                }
                int end = route2.getInt("station_num");
                ResultSet station = st.executeQuery("SELECT * FROM STATION WHERE station_num = " + end + ";");
                station.next();
                int cost = cost_per_km * dist;
                int delay = station.getInt("stop_delay");
                hour = (hour + ((minute + travel_time) / 60)) % 24;
                minute = (minute + travel_time) % 60;
                Time arrive_time = new Time(hour, minute, 0);
                arrive_time.setHours(hour);
                arrive_time.setMinutes(minute);
                if(arrive_time.getHours() < temp_start.getHours() )
                {

                    if(day.equals("Monday"))
                    {
                        day2 = "Tuesday";
                    }
                    else if (day.equals("Tuesday"))
                    {
                        day2 = "Wednesday";
                    }
                    else if (day.equals("Wednesday"))
                    {
                        day2 = "Thursday";
                    }
                    else if (day.equals("Thursday"))
                    {
                        day2 = "Friday";
                    }
                    else if (day.equals("Friday"))
                    {
                        day2 = "Saturday";
                    }
                    else if (day.equals("Saturday"))
                    {
                        day2 = "Sunday";
                    }
                    else if (day.equals("Sunday"))
                    {
                        day2 = "Monday";
                    }
                }
                String k = String.format("INSERT INTO LEGS(route_id, train_id, station_start, station_end,  start_day, end_day, distance, stations_passed, departure_time, arrival_time, available_seats, cost ) VALUES (%d, %d, %d, %d,'%s','%s',%d, %d,'%s','%s', %d, %d);", rid,tid,start,end,day, day2, dist, i, temp_start,arrive_time,available_seats, cost);
                //System.out.println("it takes: " + travel_time + " minutes of travel starting time: " + temp_start + "arriving at:" + arrive_time);
                try
                {
                 conn.setAutoCommit(false);
                 st.executeUpdate(k);
                 conn.commit();
                }
                catch (SQLException e1) {
                 System.out.println(e1.toString());
                 try {
                     conn.rollback();
                 } catch (SQLException e2) {
                     System.out.println(e2.toString());
                 }
                }
                hour = (hour + ((minute + delay) / 60)) % 24;
                minute = (minute + delay) % 60;
                temp_start.setHours(hour);
                temp_start.setMinutes(minute);
                route.next();
                while(route.getBoolean("stop") != true && route.isAfterLast() != true)
                {
                    route.next();
                }
            }
        }

    }


    // export the data
    public static void export_data()throws
    SQLException, ClassNotFoundException 
    {
        while(true)
        {
            Scanner myObj = new Scanner(System.in);
            System.out.println("what table are you exporting? \n 0) back \n 1)stations \n 2) rail_lines \n 3) trains \n 4) routes \n 5)route_schedule \n 6)customers");
            int choice = myObj.nextInt();
            if(choice == 0)
            {
                return;
            }
            else if (choice == 2)
            {
                export_rail_lines();
            }
            else if (choice == 4)
            {
                // should actually export Station_route_relation
                export_routes();
            }
            else if (choice == 5)
            {
                export_route_schedule();
            }
            else if (choice == 1)
            {
                export_stations();
            }
            else if (choice == 3)
            {
                export_trains();
            }
            else if (choice == 6)
            {
                export_customers();
            }
            else
            {
                System.out.println("not an option");
            }
            System.out.println("");
        }

    }

    public static void export_customers()throws
    SQLException, ClassNotFoundException 
    {
        
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/postgres";
        Properties props = new Properties();
        props.setProperty("user", "username"); //made this a superuser
        props.setProperty("password", "password"); 
        Connection conn =
                DriverManager.getConnection(url, props);
        Statement st = conn.createStatement();
        ResultSet cust = st.executeQuery("SELECT * FROM PASSENGERS");
        Scanner myObj = new Scanner(System.in);
        System.out.println("Type the filename ");
        String fname = myObj.nextLine() +".csv";
        try
        {
        BufferedWriter fw = new BufferedWriter(new FileWriter(fname));
        fw.write("id, fname, lname, street, town, postal");
        cust.next();
        while(cust.isAfterLast() != true)
        {
            int id = 0;
            String first_name = "";
            String lname = "";
            String street = "";
            String town = "";
            String postal = "";
            try
            {
                id = cust.getInt("customers_id");
                first_name = cust.getString("fname");
                lname = cust.getString("lname");
                street = cust.getString("street");
                town = cust.getString("town");
                postal = cust.getString("postal_code");
            }
            catch(SQLException e)
            {
                System.out.println(e.toString());
            }
            
            String line = String.format("%d,%s,%s,%s,%s,%s", id, first_name.trim(), lname.trim(), street.trim(), town.trim(), postal.trim());
            try
            {
                fw.newLine();
                fw.write(line);
            }
            catch(Exception e)
            {
                System.out.println(e.toString());
            }
            cust.next();
        }
        fw.close();
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
        
    }



    public static void export_trains()throws
    SQLException, ClassNotFoundException 
    {
        
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/postgres";
        Properties props = new Properties();
        props.setProperty("user", "username"); //made this a superuser
        props.setProperty("password", "password"); 
        Connection conn =
                DriverManager.getConnection(url, props);
        Statement st = conn.createStatement();
        ResultSet trains = st.executeQuery("SELECT * FROM TRAIN");
        Scanner myObj = new Scanner(System.in);
        System.out.println("Type the filename ");
        String fname = myObj.nextLine() +".csv";
        try
        {
        BufferedWriter fw = new BufferedWriter(new FileWriter(fname));
        fw.write("train_id,name,description,top_speed,seats,price_per_km");
        trains.next();
        while(trains.isAfterLast() != true)
        {
            int train_id = 0;
            String name = "";
            int top_speed = 0;
            int seats = 0;
            int price = 0;
            String description = "";
            try
            {
                train_id = trains.getInt("train_id");
                name = trains.getString("name");
                top_speed = trains.getInt("top_speed");
                seats = trains.getInt("seats");
                price = trains.getInt("price_per_km");
                description = trains.getString("description");
            }
            catch(SQLException e)
            {
                System.out.println(e.toString());
            }
            
            String line = String.format("%d,%s,%s,%d,%d,%d", train_id, name.trim(), description.trim(), top_speed, seats, price);
            try
            {
                fw.newLine();
                fw.write(line);
            }
            catch(Exception e)
            {
                System.out.println(e.toString());
            }
            trains.next();
        }
        fw.close();
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
        
    }
    public static void export_stations()throws
    SQLException, ClassNotFoundException 
    {
        
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/postgres";
        Properties props = new Properties();
        props.setProperty("user", "username"); //made this a superuser
        props.setProperty("password", "password"); 
        Connection conn =
                DriverManager.getConnection(url, props);
        Statement st = conn.createStatement();
        ResultSet stat = st.executeQuery("SELECT * FROM STATION");
        Scanner myObj = new Scanner(System.in);
        System.out.println("Type the filename ");
        String fname = myObj.nextLine() +".csv";
        try
        {
        BufferedWriter fw = new BufferedWriter(new FileWriter(fname));
        fw.write("station_num,name,opening_time,closing_time,stop_delay,street,town,postal_code");
        stat.next();
        while(stat.isAfterLast() != true)
        {
            int station_num = 0;
            String name = "";
            Time open = new Time(1, 1, 1);
            Time close = new Time(1, 1, 1);
            int stop_delay = 0;
            String street = "";
            String town = "";
            String postal = "";
            try
            {
                station_num = stat.getInt("station_num");
                name = stat.getString("name");
                open = stat.getTime("opening_time");
                close = stat.getTime("closing_time");
                stop_delay = stat.getInt("stop_delay");
                street = stat.getString("street");
                town = stat.getString("town");
                postal = stat.getString("postal_code");
            }
            catch(SQLException e)
            {
                System.out.println(e.toString());
            }
            
            String line = String.format("%d,%s,%s,%s,%d,%s,%s,%s", station_num, name.trim(), open, close, stop_delay, street.trim(), town.trim(),postal.trim());
            try
            {
                fw.newLine();
                fw.write(line);
                stat.next();
            }
            catch(Exception e)
            {
                System.out.println(e.toString());
            }
            stat.next();
        }
        fw.close();
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
        
    }

    public static void export_rail_lines()throws
    SQLException, ClassNotFoundException 
    {
        
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/postgres";
        Properties props = new Properties();
        props.setProperty("user", "username"); //made this a superuser
        props.setProperty("password", "password"); 
        Connection conn =
                DriverManager.getConnection(url, props);
        Statement st = conn.createStatement();
        ResultSet rail = st.executeQuery("SELECT * FROM RAIL_LINES");
        Scanner myObj = new Scanner(System.in);
        System.out.println("Type the filename ");
        String fname = myObj.nextLine() +".csv";
        try
        {
        BufferedWriter fw = new BufferedWriter(new FileWriter(fname));
        fw.write("line_id,speed_limit,station_num,distance_prev,prev_station,distance_next,next_station");
        rail.next();
        while(rail.isAfterLast() != true)
        {
            int line_id = 0;
            int speed = 0;
            int station_num = 0;
            int distancep = 0;
            int prev_station = 0;
            int distancen = 0;
            int next_station = 0;
            try
            {
                distancep = rail.getInt("distance_prev");
                prev_station = rail.getInt("prev_station");
            }
            catch(SQLException e)
            {
                System.out.println(e.toString());
            }
            try
            {
                distancen = rail.getInt("distance_next");
                next_station = rail.getInt("next_station");
            }
            catch(SQLException e)
            {
                System.out.println(e.toString());
            }
            try
            {
                line_id = rail.getInt("line_id");
                speed = rail.getInt("speed_limit");
                station_num = rail.getInt("station_num");
            }
            catch(SQLException e)
            {
                System.out.println(e.toString());
            }
            String line = String.format("%d,%d,%d,%d,%d,%d,%d", line_id, speed, station_num, distancep, prev_station, distancen, next_station);
            try
            {
                fw.newLine();
                fw.write(line);
            }
            catch(Exception e)
            {
                System.out.println(e.toString());
            }
            rail.next();
        }
        fw.close();
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
        //fw.close();
        
    }
    public static void export_routes()throws
    SQLException, ClassNotFoundException 
    {
        
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/postgres";
        Properties props = new Properties();
        props.setProperty("user", "username"); //made this a superuser
        props.setProperty("password", "password"); 
        Connection conn =
                DriverManager.getConnection(url, props);
        Statement st = conn.createStatement();
        ResultSet route = st.executeQuery("SELECT * FROM STATION_ROUTE_RELATION");
        Scanner myObj = new Scanner(System.in);
        System.out.println("Type the filename ");
        String fname = myObj.nextLine() +".csv";
        try
        {
        BufferedWriter fw = new BufferedWriter(new FileWriter(fname));
        fw.write("line_id,route_id,station_num,stop,station_next,distance,speed_limit,order");
        route.next();
        while(route.isAfterLast() != true)
        {
            int line_id = 0;
            int speed = 0;
            int station_num = 0;
            int distance = 0;
            int route_id = 0;
            int order = 0;
            int next_station = 0;
            Boolean stop = false;
            try
            {
                order = route.getInt("order");
                
            }
            catch(SQLException e)
            {
                System.out.println(e.toString());
            }
            try
            {
                distance = route.getInt("distance");
                next_station = route.getInt("station_next");
                
            }
            catch(SQLException e)
            {
                System.out.println(e.toString());
            }
            try
            {
                route_id = route.getInt("route_id");
                line_id = route.getInt("line_id");
                speed = route.getInt("speed_limit");
                station_num = route.getInt("station_num");
                stop = route.getBoolean("stop");
            }
            catch(SQLException e)
            {
                System.out.println(e.toString());
            }
            String line = String.format("%d,%d,%d,%b,%d,%d,%d,%d", line_id, route_id, station_num, stop, next_station, distance, speed, order);
            try
            {
                fw.newLine();
                fw.write(line);
            }
            catch(Exception e)
            {
                System.out.println(e.toString());
            }
            route.next();
        }
        fw.close();
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
        
    }

    public static void export_route_schedule()throws
    SQLException, ClassNotFoundException 
    {
        
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/postgres";
        Properties props = new Properties();
        props.setProperty("user", "username"); //made this a superuser
        props.setProperty("password", "password"); 
        Connection conn =
                DriverManager.getConnection(url, props);
        Statement st = conn.createStatement();
        ResultSet sched = st.executeQuery("SELECT * FROM train_schedule");
        Scanner myObj = new Scanner(System.in);
        System.out.println("Type the filename ");
        String fname = myObj.nextLine() +".csv";
        try
        {
        BufferedWriter fw = new BufferedWriter(new FileWriter(fname));
        fw.write("schedule_id,route_id,train_id,day,time");
        sched.next();
        while(sched.isAfterLast() != true)
        {
            int schedule_id = 0;
            int route_id = 0;
            int train_id = 0;
            String day = "";
            Time tim = new Time(1, 1, 1);
            try
            {
                route_id = sched.getInt("route_id");
                schedule_id = sched.getInt("schedule_id");
                train_id = sched.getInt("train_id");
                day = sched.getString("day");
                tim = sched.getTime("time");
            }
            catch(SQLException e)
            {
                System.out.println(e.toString());
            }
            String line = String.format("%d,%d,%d,%s,%s", route_id, schedule_id, train_id, day.trim(), tim);
            try
            {
                fw.newLine();
                fw.write(line);
            }
            catch(Exception e)
            {
                System.out.println(e.toString());
            }
            sched.next();
        }
        fw.close();
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
    }
    //delete the database
    public static void delete_database()throws
    SQLException, ClassNotFoundException 
    {
        
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/postgres";
        Properties props = new Properties();
        props.setProperty("user", "username"); //made this a superuser
        props.setProperty("password", "password"); 
        Connection conn =
                DriverManager.getConnection(url, props);
        Statement st = conn.createStatement();
        try
        {
            st.executeUpdate("DELETE FROM STATION");
        }
        catch(SQLException e)
        {
            System.out.println(e.toString());
        }
        try
        {
            st.executeUpdate("DELETE FROM RAIL_LINES");
        }
        catch(SQLException e)
        {
            System.out.println(e.toString());
        }
        try
        {
            st.executeUpdate("DELETE FROM ROUTE");
        }
        catch(SQLException e)
        {
            System.out.println(e.toString());
        }
        try
        {
            st.executeUpdate("DELETE FROM TRAIN_SCHEDULE");
        }
        catch(SQLException e)
        {
            System.out.println(e.toString());
        }
        try
        {
            st.executeUpdate("DELETE FROM TRAIN");
        }
        catch(SQLException e)
        {
            System.out.println(e.toString());
        }
        try
        {
            st.executeUpdate("DELETE FROM PASSENGERS");
        }
        catch(SQLException e)
        {
            System.out.println(e.toString());
        }
        try
        {
            st.executeUpdate("DELETE FROM CLOCK");
        }
        catch(SQLException e)
        {
            System.out.println(e.toString());
        }
        try
        {
            st.executeUpdate("DELETE FROM STATION_ROUTE_RELATION");
        }
        catch(SQLException e)
        {
            System.out.println(e.toString());
        }
        try
        {
            st.executeUpdate("DELETE FROM RESERVATION");
        }
        catch(SQLException e)
        {
            System.out.println(e.toString());
        }
        try
        {
            st.executeUpdate("DELETE FROM LEGS");
        }
        catch(SQLException e)
        {
            System.out.println(e.toString());
        }

    }
}
