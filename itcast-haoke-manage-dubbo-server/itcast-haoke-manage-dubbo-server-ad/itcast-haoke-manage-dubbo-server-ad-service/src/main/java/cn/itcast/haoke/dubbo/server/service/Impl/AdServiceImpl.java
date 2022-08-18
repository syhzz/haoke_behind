package cn.itcast.haoke.dubbo.server.service.Impl;

import cn.itcast.haoke.dubbo.pojo.PageInfo;
import cn.itcast.haoke.dubbo.server.mapper.AdMapper;
import cn.itcast.haoke.dubbo.server.pojo.Ad;
import cn.itcast.haoke.dubbo.server.service.AdService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdServiceImpl extends ServiceImpl<AdMapper, Ad> implements AdService {

    @Autowired
    private AdMapper adMapper;

    @Override
    public PageInfo<Ad> queryAdList(Ad ad, Integer page, Integer pageSize) {
        QueryWrapper<Ad> adQueryWrapper = new QueryWrapper<>(ad);
        IPage<Ad> adPage = adMapper.selectPage(new Page<>(page, pageSize), adQueryWrapper);
        return new PageInfo<Ad>(Long.valueOf(adPage.getTotal()).intValue(), page, pageSize, adPage.getRecords());
    }
}
