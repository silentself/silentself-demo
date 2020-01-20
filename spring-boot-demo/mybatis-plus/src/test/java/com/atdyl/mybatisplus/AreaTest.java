package com.atdyl.mybatisplus;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.atdyl.mybatisplus.dao.CategaryTypeDao;
import com.atdyl.mybatisplus.dao.CpyTypeDao;
import com.atdyl.mybatisplus.dao.ProductTypeDao;
import com.atdyl.mybatisplus.dao.SnowDao;
import com.atdyl.mybatisplus.entities.*;
import com.atdyl.mybatisplus.service.CityService;
import com.atdyl.mybatisplus.service.CountyService;
import com.atdyl.mybatisplus.service.ProvinceService;
import com.atdyl.mybatisplus.service.SnowService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Dong YL
 * @version V1.0 2020/1/7 11:24
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AreaTest {

    @Autowired
    CityService cityService;

    @Autowired
    ProvinceService provinceService;

    @Autowired
    CountyService countyService;

    @Autowired
    SnowService snowService;

    @Test
    public void test() {
        List<AreaPO> areaPO1 = cityService.getList();
        List<AreaPO> areaPO2 = provinceService.getList();
        List<AreaPO> areaPO3 = countyService.getList();
        List<AreaPO> areaPOS = new ArrayList<>();

        areaPOS.addAll(areaPO2);
        areaPOS.addAll(areaPO1);
        areaPOS.addAll(areaPO3);

        areaPOS.forEach(snowService::add);

    }


    @Resource
    CpyTypeDao cpyTypeDao;

    @Resource
    ProductTypeDao productTypeDao;

    @Test
    public void testCpyType() {
        List<CpyTypePO> cpyTypePOS = cpyTypeDao.selectList(new QueryWrapper<>());

        Set<String> as = new HashSet<>();
        Set<String> bs = new HashSet<>();
        Set<String> bs1 = new HashSet<>();
        Set<String> cs = new HashSet<>();

        cpyTypePOS.forEach(cpyTypePO -> {
            as.add(cpyTypePO.getA());
            bs1.add(cpyTypePO.getB());
            bs.add(cpyTypePO.getB());
            if (StrUtil.isNotBlank(cpyTypePO.getC()))
                cs.add(cpyTypePO.getC());
        });

        List<ProductTypePO> productTypePOS = CollUtil.newArrayList();

        as.forEach(a -> {
            ProductTypePO productTypePO = ProductTypePO.builder().name(a).parentId(0L).build();
            productTypeDao.insert(productTypePO);

//            productTypePOS.add(productTypePO);

            cpyTypePOS.stream().filter(cpyTypePO -> cpyTypePO.getA().equals(a)).forEach(cpyTypePO -> {
                ProductTypePO productTypePOB = ProductTypePO.builder().name(cpyTypePO.getB()).parentId(productTypePO.getId()).build();
                if (bs.contains(cpyTypePO.getB())) {
                    productTypeDao.insert(productTypePOB);
                    bs.remove(cpyTypePO.getB());
                    cpyTypePOS.stream().filter(cpyTypePO1 -> cpyTypePO1.getB().equals(cpyTypePO.getB())).forEach(cpyTypePO1 -> {
                        if (StrUtil.isNotBlank(cpyTypePO1.getC())) {
                            ProductTypePO productTypePOC = ProductTypePO.builder().name(cpyTypePO1.getC()).parentId(productTypePOB.getId()).build();
                            productTypeDao.insert(productTypePOC);
//                        bs.remove(cpyTypePO.getB());
                        }
                    });
                }

            });
        });

        System.err.println(as.size() + bs1.size() + cs.size());

    }

    @Resource
    CategaryTypeDao categaryTypeDao;

    @Test
    public void spiltChinese() {
        List<ProductTypePO> productTypePOS = productTypeDao.selectList(new QueryWrapper<>());


        productTypePOS.forEach(productTypePO -> {
            CategaryTypePO build = CategaryTypePO.builder().build();
            String name = productTypePO.getName();
            List<Integer> list = A.spiltChinese(name);
            Integer next = list.get(list.size() - 1);
            String cnName = StrUtil.sub(name, 0, next);

            String enName = StrUtil.sub(name, next, name.length());


            BeanUtil.copyProperties(productTypePO, build);
            build.setName(StrUtil.trim(cnName));
            build.setEnName(StrUtil.trim(enName));

            categaryTypeDao.insert(build);
        });
    }

    @Resource
    SnowDao snowDao;

    @Test
    public void modifyArea(){
        List<AreaPO> areaPOS = snowDao.selectList(new QueryWrapper<>());
        List<AreaPO> cityPOS = CollUtil.newArrayList();
        List<AreaPO> countyPOS = CollUtil.newArrayList();
        List<AreaPO> countyPOS2 = CollUtil.newArrayList();
        areaPOS.stream().filter(areaPO -> areaPO.getParentCode() == 0).forEach(areaPO -> {
            areaPO.setLevel(1);
            cityPOS.add(areaPO);

            areaPOS.stream().filter(areaPO1 -> areaPO1.getParentCode().equals(areaPO.getCode())).forEach(areaPO1 -> {
                areaPO1.setLevel(2);
                countyPOS.add(areaPO1);

                areaPOS.stream().filter(areaPO2 -> areaPO2.getParentCode().equals(areaPO1.getCode())).forEach(areaPO2 -> {
                    areaPO2.setLevel(3);
                    countyPOS2.add(areaPO2);
                });
            });
        });
        cityPOS.forEach(snowDao::updateById);

        countyPOS.forEach(snowDao::updateById);
        countyPOS2.forEach(snowDao::updateById);
    }


}
