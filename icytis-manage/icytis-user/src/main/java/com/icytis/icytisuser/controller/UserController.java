package com.icytis.icytisuser.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.icytis.icytisuser.common.Result;
import com.icytis.icytisuser.entity.User;
import com.icytis.icytisuser.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserMapper userMapper;

    @PostMapping("/login")
    public Result<?> login(@RequestBody User user) {
        User get_user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));
        if (get_user == null) {
            return Result.error("u10000","用户不存在");
        }
        if (get_user.getPassword() == null) {
            return Result.error("u10001","请重置密码");
        }else if (!get_user.getPassword().equals(user.getPassword())){
            return Result.error("u10002","密码错误");
        }
        return Result.success(get_user);
    }

    @PostMapping("/register")
    public Result<?> register(@RequestBody User user) {
        User get_user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));
        if (get_user != null) {
            return Result.error("u10003","用户已存在");
        }
        userMapper.insert(user);
        return Result.success(user);

    }

    @PostMapping("/save")
    public Result<?> save(@RequestBody User user) {
        return Result.success(userMapper.insert(user));
    }

    @GetMapping("/query")
    public Result<?> query(@RequestParam String search,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        if(StringUtils.isNotBlank(search)){
            wrapper.like(User::getNickName, search);
        }
        return Result.success(userMapper.selectPage(new Page<>(pageNum, pageSize), wrapper));
    }

    @PutMapping("/update")
    public Result<?> update(@RequestBody User user) {
        return Result.success(userMapper.updateById(user));
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable Integer id) {
        return Result.success(userMapper.deleteById(id));
    }
}
