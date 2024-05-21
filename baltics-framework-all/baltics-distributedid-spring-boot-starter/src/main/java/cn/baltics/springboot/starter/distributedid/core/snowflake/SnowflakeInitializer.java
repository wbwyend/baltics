/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.baltics.springboot.starter.distributedid.core.snowflake;

import cn.baltics.springboot.starter.distributedid.SnowflakeIdUtil;
import cn.hutool.core.date.SystemClock;

/**
 * 雪花算法模板生成
 *
 * @author wbwyend
 * @date 2024/05/20
 */
public class SnowflakeInitializer {

    /**
     * 是否使用 {@link SystemClock} 获取当前时间戳
     */
    private final boolean isUseSystemClock;

    private final long workId;

    private final long dataCenterId;

    public SnowflakeInitializer(Long workId, Long dataCenterId, Boolean isUseSystemClock) {
        this.workId = workId != null ? workId : (long) (Math.random() * 32);
        this.dataCenterId = dataCenterId != null ? dataCenterId : (long) (Math.random() * 32);
        this.isUseSystemClock = isUseSystemClock != null ? isUseSystemClock : false;
    }

    /**
     * 选择 WorkId 并初始化雪花
     */
    public void init() {
        Snowflake snowflake = new Snowflake(workId, dataCenterId, isUseSystemClock);
        SnowflakeIdUtil.initSnowflake(snowflake);
    }
}
