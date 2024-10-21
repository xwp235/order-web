package com.xweb.starter.modules.security.mapper.cust;

import com.xweb.starter.common.dbmappingtypes.MultiLanguage;
import com.xweb.starter.common.typehandler.MultiLanguageTypeHandler;
import com.xweb.starter.common.typehandler.TimestamptzTypeHandler;
import com.xweb.starter.modules.security.domain.bo.Permission;
import com.xweb.starter.modules.security.domain.bo.RequestPermission;
import com.xweb.starter.modules.security.domain.bo.Role;
import com.xweb.starter.modules.security.domain.entity.MastAccount;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
import java.util.Set;

public interface AccountMapper {

    @Select("""
       SELECT
         id, 
         avatar, 
         nickname, 
         username, 
         mobile, 
         email, 
         "password", 
         account_expired, 
         account_locked,
         password_expired, 
         mfa_key, 
         using_mfa, 
         "enabled", 
         create_time, 
         update_time
       FROM
         public.mast_account
       WHERE
          username = #{value}
          OR mobile = #{value}
          OR email = #{value}
    """)
    @Results({
            @Result(property = "id", column = "id", jdbcType = JdbcType.BIGINT),
            @Result(property = "avatar", column = "avatar", jdbcType = JdbcType.VARCHAR),
            @Result(property = "nickname", column = "nickname", jdbcType = JdbcType.VARCHAR),
            @Result(property = "username", column = "username", jdbcType = JdbcType.VARCHAR),
            @Result(property = "mobile", column = "mobile", jdbcType = JdbcType.VARCHAR),
            @Result(property = "email", column = "email", jdbcType = JdbcType.VARCHAR),
            @Result(property = "password", column = "password", jdbcType = JdbcType.VARCHAR),
            @Result(property = "accountExpired", column = "account_expired", jdbcType = JdbcType.BIT),
            @Result(property = "accountLocked", column = "account_locked", jdbcType = JdbcType.BIT),
            @Result(property = "passwordExpired", column = "password_expired", jdbcType = JdbcType.BIT),
            @Result(property = "mfaKey", column = "mfa_key", jdbcType = JdbcType.VARCHAR),
            @Result(property = "usingMfa", column = "using_mfa", jdbcType = JdbcType.BIT),
            @Result(property = "enabled", column = "enabled", jdbcType = JdbcType.BIT),
            @Result(property = "createTime", column = "create_time", jdbcType = JdbcType.OTHER,typeHandler = TimestamptzTypeHandler.class),
            @Result(property = "updateTime", column = "update_time", jdbcType = JdbcType.OTHER,typeHandler = TimestamptzTypeHandler.class),
    })
    MastAccount selectByLoginAccount(String account);

    @Select("""
       SELECT
         id,
         mr_id,
         mr_name,
         mr_weight,
         mr_enable_delete built_in
       FROM
         public.mast_role r
         LEFT JOIN public.mast_account_mapping_role mamr ON r.mr_id = mamr.role_id
       WHERE
         mamr.account_id = #{value}
    """)
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "mrId", column = "mr_id"),
            @Result(property = "mrName", column = "mr_name", javaType = MultiLanguage.class,typeHandler = MultiLanguageTypeHandler.class),
            @Result(property = "mrWeight", column = "mr_weight"),
            @Result(property = "builtIn", column = "built_in")
    })
    Set<Role> selectAccountRelatedRoles(Long accountId);

    @Select("""
       <script>
           SELECT
             id,
             mr_id,
             mr_name,
             mr_weight,
             mr_enable_delete built_in
           FROM
             public.mast_role r
             LEFT JOIN public.mast_account_mapping_role mamr ON r.mr_id = mamr.role_id
           WHERE
             r.mr_id IN
            <foreach collection="roleSet" item="itemId" open="(" separator="," close=")">
                  #{itemId}
            </foreach>
       </script>
    """)
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "mrId", column = "mr_id"),
            @Result(property = "mrName", column = "mr_name", javaType = MultiLanguage.class,typeHandler = MultiLanguageTypeHandler.class),
            @Result(property = "mrWeight", column = "mr_weight"),
            @Result(property = "builtIn", column = "built_in"),
            @Result(property = "permissions", javaType = Set.class, column = "mr_id",
                    many = @Many(select = "selectPermissionsByRoleId"))
    })
    Set<Role> selectAccountRelatedPermissions(Set<String> roleSet);

    @Select("""
           SELECT
             mm.mm_name name,
             mm.mm_code permission_key
           FROM
             mast_menu mm
             LEFT JOIN public.mast_role_mapping_permission mrmp ON mm.mm_code = mrmp.permission_key
           WHERE
             mrmp.role_id = #{value}
    """)
    @Results({
            @Result(property = "permissionKey", column = "permission_key"),
            @Result(property = "name", column = "name", javaType = MultiLanguage.class,typeHandler = MultiLanguageTypeHandler.class)
    })
    Set<Permission> selectPermissionsByRoleId(String roleId);

    @Select("""
          SELECT
            mm_path,
            mm_method,
            mm_code
          FROM
            public.mast_menu
          WHERE
            mm_path IS NOT NULL
            AND mm_state = 1
            AND mm_require_auth = true
    """)
    @Results({
            @Result(property = "permissionKey", column = "mm_code",jdbcType = JdbcType.VARCHAR),
            @Result(property = "requestUrl", column = "mm_path", jdbcType = JdbcType.VARCHAR),
            @Result(property = "requestMethod", column = "mm_method", jdbcType = JdbcType.VARCHAR)
    })
    List<RequestPermission> selectPermissions();

}
