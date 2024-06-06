-- 1.参数列表
-- 1.1.优惠券id
local couponId = ARGV[1]
-- 1.2 下单数量
local number = ARGV[2]

-- 2.数据key
-- 2.1.库存key
local stockKey = 'coupon:stock:' .. couponId

-- 3.脚本业务
-- 3.1.加库存 incrby stockKey number
redis.call('incrby', stockKey, tonumber(number))
return 0