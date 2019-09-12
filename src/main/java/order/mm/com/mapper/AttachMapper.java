package order.mm.com.mapper;

import order.mm.com.bean.Attach;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AttachMapper {

    @Insert("insert into t_attach(id,file_name,update_at,create_at,body) values(#{id},#{fileName},#{updateAt},#{createAt},#{body})")
     void insert(Attach attach);

    @Select("select * from  t_attach where id=#{id}")
    Attach select(@Param("id")String id);
}
