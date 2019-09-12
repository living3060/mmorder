package order.mm.com.mapper;

import order.mm.com.bean.Order;
import order.mm.com.bean.OrderFilter;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Insert({"insert into t_order(game_channel,game_id,amount,order_no,status,exp_time,create_at,update_at,qr_code_id,qr_code_account,hit,game_order_no,attach_id,type,game_account_id,notify_url) " +
            "values(#{gameChannel},#{gameId},#{amount},#{orderNo},#{status},#{expTime},#{createAt},#{updateAt},#{qrCodeId},#{qrCodeAccount},#{hit},#{gameOrderNo},#{attachId},#{type},#{gameAccountId},#{notifyUrl})"})
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Order order);

    @SelectProvider(type = OrderProvider.class, method = "selectByFilterSql")
    List<Order> selectByFilter(OrderFilter of);

    @Update("update t_order set status=#{toStatus} where  order_no=#{orderNo} and status=#{fromStatus}")
    void updateStatus(@Param("fromStatus") int fromStatus, @Param("toStatus") int toStatus, @Param("orderNo") String orderNo);

    @Select("select * from t_order where  order_no=#{orderNo}")
    Order selectByOrderNo(@Param("orderNo") String orderNo);

    @Select("select * from t_order where  id=#{id}")
    Order selectById(@Param("id") int id);

    public class OrderProvider {
        public String selectByFilterSql(OrderFilter orderFilter) {
            SQL sql = new SQL().SELECT(" * ");
            sql = sql.FROM(" t_order ").WHERE(" 1=1 ");
            if (orderFilter.amount != null) {
                sql = sql.WHERE("   amount=#{amount}");
            }
            if (orderFilter.qrCodeAccount != null) {
                sql = sql.WHERE("   qr_code_account like concat('%',#{qrCodeAccount},'%')");
            }
            if (orderFilter.gameAccountId != null) {
                sql = sql.WHERE("   game_account_id like concat('%',#{gameAccountId},'%')");
            }
            if (orderFilter.status != null) {
                sql = sql.WHERE("   status=#{status}");
            }
            if(orderFilter.startExpTime!=null){
                sql=sql.WHERE(" exp_time>=#{startExpTime}");
            }
            if(orderFilter.endExptime!=null){
                sql=sql.WHERE(" exp_time<=#{endExptime}");
            }
            if(orderFilter.type!=null){
                sql=sql.WHERE(" type=#{type}");
            }
            return sql.toString();
        }

    }
}
