package com.icytis.icytisuser.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.icytis.icytisuser.common.Result;
import com.icytis.icytisuser.entity.News;
import com.icytis.icytisuser.mapper.NewsMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Resource
    NewsMapper newsMapper;

    @PostMapping("/save")
    public Result<?> save(@RequestBody News news) {
        return Result.success(newsMapper.insert(news));
    }

    @PostMapping("/delete")
    public Result<?> delete(@RequestBody News news) {
        return Result.success(newsMapper.deleteById(news.getId()));
    }

    @PostMapping("/update")
    public Result<?> update(@RequestBody News news) {
        return Result.success(newsMapper.updateById(news));
    }

    @PostMapping("/query")
    public Result<?> query(@RequestParam String search,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        LambdaQueryWrapper<News> wrapper = Wrappers.<News>lambdaQuery();
        if(StringUtils.isNotBlank(search)){
            wrapper.like(News::getContent, search);
        }
        return Result.success(newsMapper.selectPage(new Page<>(pageNum, pageSize), wrapper));
    }
}
