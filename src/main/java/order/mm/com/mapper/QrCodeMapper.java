package order.mm.com.mapper;

import order.mm.com.bean.Qrcode;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 *
 */
@Mapper
public interface QrCodeMapper {
    @Insert("insert into t_qrcode(qrcode_account,back,attach_id,status,today_amount,total_amount,update_at,create_at,last_order_at,current_order_exp,type)" +
            "values(#{qrcodeAccount},#{back},#{attachId},#{status},#{todayAmount},#{totalAmount},#{updateAt},#{createAt},#{lastOrderAt},#{currentOrderExp},#{type})")
    void insert(Qrcode qrcode);

    @Select("select * from t_qrcode where status=#{status}")
    List<Qrcode> slectByStatus(@Param("status") int status);

    @Select("select * from t_qrcode ")
    List<Qrcode> slectAll();

    @Select("select * from t_qrcode where id=#{id}")
    Qrcode selectById(@Param("id") int id);

    @Select("update  t_qrcode set status=#{status}  where id=#{id}")
    void updateStatus(@Param("status") int status, @Param("id") int id);
}
