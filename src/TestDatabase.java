import Model.Database;
import Model.MemberInfo;
import Model.Type;

import java.sql.SQLException;

public class TestDatabase {
    public static void main(String[] args) {

        Database db = new Database();
        System.out.println("Running database");
        try {
            db.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.addMember(new MemberInfo("tutu","Saikat","dssf@ghhh","tyttc", Type.VIP));
        db.addMember(new MemberInfo("124","SaikatDas","dssf@ghhh","tyttc", Type.VIP));
        try {
            db.save();
            db.codeLoad();
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.disconnect();
    }
}
