package order.mm.com.mapper;

import order.mm.com.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from t_user where  user_name=#{userName}")
    public User  selectUser(@Param("userName") String userName);
}
