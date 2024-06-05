-- 1.参数列表
-- 1.1.优惠券id
local voucherId = ARGV[1]
-- 1.2 下单数量
local number = ARGV[2]

-- 2.数据key
-- 2.1.库存key
local stockKey = 'cunpon:stock:' .. voucherId

-- 3.脚本业务
-- 3.1.判断库存是否充足 get stockKey
if(tonumber(redis.call('get', stockKey)) < number) then
    -- 3.2.库存不足，返回1
    return 1
end
-- 3.3.扣库存 incrby stockKey -1
redis.call('incrby', stockKey, -1)
return 0