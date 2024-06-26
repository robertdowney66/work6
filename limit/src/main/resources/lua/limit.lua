local key = KEYS[1] --限流KEY
local limit = tonumber(ARGV[1]) --限流大小
local current = tonumber(redis.call('get', key) or "0")
if current + 1 > limit then --如果超出限流大小
    return false
else --请求数+1，并设置n秒过期
    redis.call("INCRBY", key, "1")
    redis.call("expire", key, ARGV[2])
end
return true