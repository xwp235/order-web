package com.xweb.starter.modules.security.mapper.cust;

import com.xweb.starter.modules.security.domain.entity.HisClientLoginLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

public interface CustHisClientLoginLogMapper {

    @Insert("""
       <script>
       insert into his_client_login_log
          <trim prefix="(" suffix=")" suffixOverrides=",">
            <if test="username != null">
              username,
            </if>
            <if test="sessionId != null">
              session_id,
            </if>
            <if test="device != null">
              device,
            </if>
            <if test="ip != null">
              ip,
            </if>
            <if test="accountId != null">
              account_id,
            </if>
            <if test="isOffline != null">
              is_offline,
            </if>
            login_time,
          </trim>
          <trim prefix="values (" suffix=")" suffixOverrides=",">
            <if test="username != null">
              #{username,jdbcType=VARCHAR},
            </if>
            <if test="sessionId != null">
              #{sessionId,jdbcType=VARCHAR},
            </if>
            <if test="device != null">
              #{device,jdbcType=VARCHAR},
            </if>
            <if test="ip != null">
              #{ip,jdbcType=VARCHAR},
            </if>
            <if test="accountId != null">
              #{accountId,jdbcType=BIGINT},
            </if>
            <if test="isOffline != null">
              #{isOffline,jdbcType=BIT},
            </if>
            current_timestamp(6),
          </trim>
         </script>
    """)
    int saveUserLoginInfo(HisClientLoginLog entity);

    @Update("""
        <script>
            update his_client_login_log
            <set>
              login_time = current_timestamp(6),
              ip = #{ip,jdbcType=VARCHAR},
              is_offline = #{isOffline,jdbcType=BIT},
              logout_time = null
            </set>
            where username = #{username,jdbcType=VARCHAR}
              and session_id = #{sessionId,jdbcType=VARCHAR}
              and device = #{device,jdbcType=VARCHAR}
        </script>
    """)
    int updateUserLoginInfo(HisClientLoginLog entity);

    @Update("""
        <script>
            update his_client_login_log
            <set>
              logout_time = current_timestamp(6),
              is_offline = #{isOffline,jdbcType=BIT},
              ip = #{ip,jdbcType=VARCHAR},
            </set>
            where username = #{username,jdbcType=VARCHAR}
              and session_id = #{sessionId,jdbcType=VARCHAR}
              and device = #{device,jdbcType=VARCHAR}
        </script>
    """)
    int saveUserLogoutInfo(HisClientLoginLog clientLoginLog);

}
